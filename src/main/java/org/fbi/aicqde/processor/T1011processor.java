package org.fbi.aicqde.processor;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.fbi.aicqde.domain.aicqde.T1011Request.AICTIA1011;
import org.fbi.aicqde.domain.aicqde.T1011Request.AICTIA1011Item;
import org.fbi.aicqde.domain.aicqde.T1011Response.AICTOA1011;
import org.fbi.aicqde.domain.starring.T1011Request.TIA1011;
import org.fbi.aicqde.domain.starring.T1011Request.TIA1011Item;
import org.fbi.aicqde.domain.starring.T1011Response.TOA1011;
import org.fbi.aicqde.enums.TxnRtnCode;
import org.fbi.aicqde.helper.AicqdeClient;
import org.fbi.aicqde.helper.MybatisFactory;
import org.fbi.aicqde.helper.ProjectConfigManager;
import org.fbi.aicqde.repository.dao.AicQdeEntMapper;
import org.fbi.aicqde.repository.dao.AicQdeInvesterMapper;
import org.fbi.aicqde.repository.model.AicQdeEnt;
import org.fbi.aicqde.repository.model.AicQdeEntExample;
import org.fbi.aicqde.repository.model.AicQdeInvester;
import org.fbi.linking.codec.dataformat.FixedLengthTextDataFormat;
import org.fbi.linking.codec.dataformat.SeperatedTextDataFormat;
import org.fbi.linking.processor.ProcessorException;
import org.fbi.linking.processor.standprotocol10.Stdp10ProcessorRequest;
import org.fbi.linking.processor.standprotocol10.Stdp10ProcessorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1561011增资登记
 * zhanrui
 */
public class T1011processor extends AbstractTxnProcessor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doRequest(Stdp10ProcessorRequest request, Stdp10ProcessorResponse response) throws ProcessorException, IOException {
        TIA1011 tia;
        try {
            tia = getStarringTia(request.getRequestBody());
        } catch (Exception e) {
            logger.error("特色业务平台请求报文解析错误.", e);
            response.setHeader("rtnCode", TxnRtnCode.CBSMSG_UNMARSHAL_FAILED.getCode());
            return;
        }

        //工商局通讯处理 -
        AICTIA1011 aictia1011 = assembleAictia1011(tia, request);
        aictia1011.setTxnCode("1011");
        AICTOA1011 aictoa1011 = null;

        String sendMsgForAic = null;
        try {
            sendMsgForAic = getSendMsgForAic(aictia1011);
        } catch (Exception e) {
            logger.error("生成工商请求报文时出错.", e);
            response.setHeader("rtnCode", TxnRtnCode.TPSMSG_MARSHAL_FAILED.getCode());
            return;
        }

        try {
            aictoa1011 = sendAndRecvForAic(sendMsgForAic);
        } catch (SocketTimeoutException e) {
            logger.error("与工商服务器通讯处理超时.", e);
            response.setHeader("rtnCode", TxnRtnCode.MSG_RECV_TIMEOUT.getCode());
            return;
        } catch (Exception e) {
            logger.error("与工商服务器通讯处理异常.", e);
            response.setHeader("rtnCode", TxnRtnCode.MSG_COMM_ERROR.getCode());
            return;
        }

        //处理工商局返回报文--
        String starringRespMsg = "";
        try {
            String aicRntCode = aictoa1011.getRntCode();
            if (!"00".equals(aicRntCode)) {
                response.setHeader("rtnCode", TxnRtnCode.TXN_EXECUTE_FAILED.getCode());
                starringRespMsg = getErrorRespMsgForStarring(aicRntCode);
            } else {
                TOA1011 toa = new TOA1011();
                //processTxn(aictia1011, aictoa1011, tia, request);
                //组特色平台响应报文--
                starringRespMsg = getRespMsgForStarring(toa);
                response.setHeader("rtnCode", "0000");
            }
        } catch (Exception e) {
            logger.error("特色平台响应报文处理失败.", e);
            throw new RuntimeException(e);
        }

        response.setResponseBody(starringRespMsg.getBytes(response.getCharacterEncoding()));
    }

    //处理Starring请求报文
    private TIA1011 getStarringTia(byte[] body) throws Exception {
        TIA1011 tia = new TIA1011();
        SeperatedTextDataFormat starringDataFormat = new SeperatedTextDataFormat(tia.getClass().getPackage().getName());
        tia = (TIA1011) starringDataFormat.fromMessage(new String(body, "GBK"), "TIA1011");
        return tia;
    }

    //生成工商请求报文对应BEAN
    private AICTIA1011 assembleAictia1011(TIA1011 tia, Stdp10ProcessorRequest request) {
        AICTIA1011 aictia1011 = new AICTIA1011();

        aictia1011.setTxnCode(request.getHeader("txnCode"));
        aictia1011.setTellerId(request.getHeader("tellerId"));
        aictia1011.setBranchId(request.getHeader("branchId"));
        aictia1011.setBankHostSn(request.getHeader("serialNo"));

        List<AICTIA1011Item> aictia1011Items = new ArrayList<>();
        try {
            for (TIA1011Item item : tia.getItems()) {
                AICTIA1011Item aictia1011Item = new AICTIA1011Item();
                BeanUtils.copyProperties(aictia1011Item, item);
                aictia1011Items.add(aictia1011Item);
            }
        } catch (Exception e) {
            throw new RuntimeException("Bean copy error!");
        }
        aictia1011.setItems(aictia1011Items);
        return aictia1011;
    }

    //生成工商请求报文
    private String getSendMsgForAic(AICTIA1011 aictia1011) throws Exception {
        Map<String, Object> modelObjectsMap = new HashMap<String, Object>();
        modelObjectsMap.put(aictia1011.getClass().getName(), aictia1011);
        FixedLengthTextDataFormat aicReqDataFormat = new FixedLengthTextDataFormat(aictia1011.getClass().getPackage().getName());

        String sendMsg = (String) aicReqDataFormat.toMessage(modelObjectsMap);
        String strLen = "" + (sendMsg.getBytes("GBK").length + 4);
        String lpad = "";
        for (int i = 0; i < 4 - strLen.length(); i++) {
            lpad += "0";
        }
        strLen = lpad + strLen;
        sendMsg = strLen + sendMsg;

        return sendMsg;
    }

    //工商服务器通讯
    private AICTOA1011 sendAndRecvForAic(String sendMsg) throws Exception {
        String servIp = ProjectConfigManager.getInstance().getProperty("aic.server.ip");
        int servPort = Integer.parseInt(ProjectConfigManager.getInstance().getProperty("aic.server.port"));

        AicqdeClient client = new AicqdeClient(servIp, servPort);
        byte[] recvbuf = client.call(sendMsg.getBytes("GBK"));

        String recvMsg = new String(recvbuf, "GBK");
        logger.info("工商返回：" + recvMsg);

        AICTOA1011 aictoa1011 = new AICTOA1011();
        FixedLengthTextDataFormat aicRespDataFormat = new FixedLengthTextDataFormat(aictoa1011.getClass().getPackage().getName());
        aictoa1011 = (AICTOA1011) aicRespDataFormat.fromMessage(recvMsg.getBytes("GBK"), "AICTOA1011");
        return aictoa1011;
    }

    //处理工商返回报文
    private String getRespMsgForStarring(TOA1011 toa) throws Exception {
        String starringRespMsg;
        Map<String, Object> modelObjectsMap = new HashMap<String, Object>();
        modelObjectsMap.put(toa.getClass().getName(), toa);
        SeperatedTextDataFormat starringDataFormat = new SeperatedTextDataFormat(toa.getClass().getPackage().getName());
        starringRespMsg = (String) starringDataFormat.toMessage(modelObjectsMap);
        return starringRespMsg;
    }

    //业务逻辑处理
    private void processTxn(AICTIA1011 aictia, AICTOA1011 aictoa, TIA1011 tia, Stdp10ProcessorRequest request) {
        SqlSessionFactory sqlSessionFactory = MybatisFactory.ORACLE.getInstance();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AicQdeEntExample example = new AicQdeEntExample();
            AicQdeEntMapper entMapper = session.getMapper(AicQdeEntMapper.class);
            example.createCriteria().andActNoEqualTo(tia.getActNo());

            AicQdeEnt aicQdeEnt = entMapper.selectByPrimaryKey(tia.getRegNo());


            aicQdeEnt.setPregNo(tia.getRegNo());
            aicQdeEnt.setAreaCode(tia.getAreaCode());
            aicQdeEnt.setAicCode(tia.getAicCode());
            aicQdeEnt.setAicName(tia.getAicName());
            aicQdeEnt.setTellerId(request.getHeader("TellerId"));
            aicQdeEnt.setBranchId(request.getHeader("BranchId"));
            aicQdeEnt.setActNo(tia.getActNo());
            aicQdeEnt.setActBal(new BigDecimal(tia.getActBal()));

            //entMapper.up(aicQdeEnt);

            AicQdeInvesterMapper investerMapper = session.getMapper(AicQdeInvesterMapper.class);
            int i = 0;
            for (AICTIA1011Item item : aictia.getItems()) {
                String vchSn = aictoa.getVchNos().substring(0 + i * 3, 3 + i * 3);
                AicQdeInvester record = new AicQdeInvester();
                record.setRegNo(aictoa.getPregNo());
                record.setVchSn(vchSn);
                record.setInvesterName(item.getInvesterName());
                record.setActNo(item.getActNo());
                record.setInvAmt(new BigDecimal(item.getInvAmt()));
                record.setActBankName(item.getActBankName());
                record.setCertId(item.getCertId());
                record.setInvDate(request.getHeader("txnTime").substring(0,8));
                record.setInvestType("1");
                record.setBankHostSn(aictoa.getBankHostSn());
                i++;
                investerMapper.insert(record);
            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw new RuntimeException("业务逻辑处理出错。", e);
        } finally {
            session.close();
        }
    }
}
