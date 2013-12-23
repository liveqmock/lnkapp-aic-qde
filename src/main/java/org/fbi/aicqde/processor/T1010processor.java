package org.fbi.aicqde.processor;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.fbi.aicqde.domain.aicqde.T1010Request.AICTIA1010;
import org.fbi.aicqde.domain.aicqde.T1010Request.AICTIA1010Item;
import org.fbi.aicqde.domain.aicqde.T1010Response.AICTOA1010;
import org.fbi.aicqde.domain.starring.T1010Request.TIA1010;
import org.fbi.aicqde.domain.starring.T1010Request.TIA1010Item;
import org.fbi.aicqde.domain.starring.T1010Response.TOA1010;
import org.fbi.aicqde.enums.TxnRtnCode;
import org.fbi.aicqde.helper.AicqdeClient;
import org.fbi.aicqde.helper.MybatisFactory;
import org.fbi.aicqde.helper.ProjectConfigManager;
import org.fbi.aicqde.repository.dao.AicQdeEntMapper;
import org.fbi.aicqde.repository.dao.AicQdeInvesterMapper;
import org.fbi.aicqde.repository.model.AicQdeEnt;
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
 * 1561010入资登记
 * zhanrui
 */
public class T1010processor extends AbstractTxnProcessor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doRequest(Stdp10ProcessorRequest request, Stdp10ProcessorResponse response) throws ProcessorException, IOException {
        TIA1010 tia;
        try {
            tia = getStarringTia(request.getRequestBody());
            logger.info("特色业务平台请求报文TIA:" + tia.toString());
        } catch (Exception e) {
            logger.error("特色业务平台请求报文解析错误.", e);
            response.setHeader("rtnCode", TxnRtnCode.CBSMSG_UNMARSHAL_FAILED.getCode());
            return;
        }

        //工商局通讯处理 -
        AICTIA1010 aictia1010 = assembleAictia1010(tia, request);
        aictia1010.setTxnCode("1010");
        AICTOA1010 aictoa1010 = null;

        String sendMsgForAic = null;
        try {
            sendMsgForAic = getSendMsgForAic(aictia1010);
        } catch (Exception e) {
            logger.error("生成工商请求报文时出错.", e);
            response.setHeader("rtnCode", TxnRtnCode.TPSMSG_MARSHAL_FAILED.getCode());
            return;
        }

        try {
            aictoa1010 = sendAndRecvForAic(sendMsgForAic);
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
            String aicRntCode = aictoa1010.getRntCode();
            if (!"00".equals(aicRntCode)) {
                starringRespMsg = getErrorRespMsgForStarring(aicRntCode);
                response.setHeader("rtnCode", TxnRtnCode.TXN_EXECUTE_FAILED.getCode());
            } else {
                //processTxn(aictia1010, aictoa1010, tia, request);
                TOA1010 toa = new TOA1010();
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
    private TIA1010 getStarringTia(byte[] body) throws Exception {
        TIA1010 tia = new TIA1010();
        SeperatedTextDataFormat starringDataFormat = new SeperatedTextDataFormat(tia.getClass().getPackage().getName());
        tia = (TIA1010) starringDataFormat.fromMessage(new String(body, "GBK"), "TIA1010");
        return tia;
    }

    //生成工商请求报文对应BEAN
    private AICTIA1010 assembleAictia1010(TIA1010 tia, Stdp10ProcessorRequest request) {
        AICTIA1010 aictia1010 = new AICTIA1010();

        aictia1010.setTxnCode(request.getHeader("txnCode"));
        aictia1010.setTellerId(request.getHeader("tellerId"));
        aictia1010.setBranchId(request.getHeader("branchId"));
        aictia1010.setBankHostSn(request.getHeader("serialNo"));

        List<AICTIA1010Item> aictia1010Items = new ArrayList<>();
        try {
            for (TIA1010Item item : tia.getItems()) {
                AICTIA1010Item aictia1010Item = new AICTIA1010Item();
                BeanUtils.copyProperties(aictia1010Item, item);
                aictia1010Items.add(aictia1010Item);
            }
        } catch (Exception e) {
            throw new RuntimeException("Bean copy error!");
        }
        aictia1010.setItems(aictia1010Items);
        return aictia1010;
    }

    //生成工商请求报文
    private String getSendMsgForAic(AICTIA1010 aictia1010) throws Exception {
        Map<String, Object> modelObjectsMap = new HashMap<String, Object>();
        modelObjectsMap.put(aictia1010.getClass().getName(), aictia1010);
        FixedLengthTextDataFormat aicReqDataFormat = new FixedLengthTextDataFormat(aictia1010.getClass().getPackage().getName());

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
    private AICTOA1010 sendAndRecvForAic(String sendMsg) throws Exception {
        String servIp = ProjectConfigManager.getInstance().getProperty("aic.server.ip");
        int servPort = Integer.parseInt(ProjectConfigManager.getInstance().getProperty("aic.server.port"));

        AicqdeClient client = new AicqdeClient(servIp, servPort);
        byte[] recvbuf = client.call(sendMsg.getBytes("GBK"));

        String recvMsg = new String(recvbuf, "GBK");
        logger.info("工商返回：" + recvMsg);

        AICTOA1010 aictoa1010 = new AICTOA1010();
        FixedLengthTextDataFormat aicRespDataFormat = new FixedLengthTextDataFormat(aictoa1010.getClass().getPackage().getName());
        aictoa1010 = (AICTOA1010) aicRespDataFormat.fromMessage(recvMsg.getBytes("GBK"), "AICTOA1010");
        return aictoa1010;
    }

    //处理工商返回报文
    private String getRespMsgForStarring(TOA1010 toa) throws Exception {
        String starringRespMsg;
        Map<String, Object> modelObjectsMap = new HashMap<String, Object>();
        modelObjectsMap.put(toa.getClass().getName(), toa);
        SeperatedTextDataFormat starringDataFormat = new SeperatedTextDataFormat(toa.getClass().getPackage().getName());
        starringRespMsg = (String) starringDataFormat.toMessage(modelObjectsMap);
        return starringRespMsg;
    }


    //业务逻辑处理
    private void processTxn(AICTIA1010 aictia, AICTOA1010 aictoa, TIA1010 tia, Stdp10ProcessorRequest request) {
        SqlSessionFactory sqlSessionFactory = MybatisFactory.ORACLE.getInstance();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AicQdeEnt aicQdeEnt = new AicQdeEnt();
            aicQdeEnt.setPregNo(tia.getPregNo());
            aicQdeEnt.setAreaCode(tia.getAreaCode());
            aicQdeEnt.setAicCode(tia.getAicCode());
            aicQdeEnt.setAicName(tia.getAicName());
            aicQdeEnt.setTellerId(request.getHeader("TellerId"));
            aicQdeEnt.setBranchId(request.getHeader("BranchId"));
            aicQdeEnt.setActNo(tia.getActNo());
            aicQdeEnt.setActBal(new BigDecimal(tia.getActBal()));

            AicQdeEntMapper entMapper = session.getMapper(AicQdeEntMapper.class);
            entMapper.insert(aicQdeEnt);

            AicQdeInvesterMapper investerMapper = session.getMapper(AicQdeInvesterMapper.class);
            int i = 0;
            for (AICTIA1010Item item : aictia.getItems()) {
                String vchSn = aictoa.getVchNos().substring(0 + i * 3, 3 + i * 3);
                AicQdeInvester record = new AicQdeInvester();
                record.setRegNo(aictoa.getPregNo());
                record.setVchSn(vchSn);
                record.setInvesterName(item.getInvesterName());
                record.setActNo(item.getActNo());
                record.setInvAmt(new BigDecimal(item.getInvAmt()));
                record.setActBankName(item.getActBankName());
                record.setCertId(item.getCertId());
                record.setInvDate(request.getHeader("txnTime").substring(0, 8));
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
