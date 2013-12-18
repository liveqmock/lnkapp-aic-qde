package org.fbi.aicqde.processor;


import org.fbi.aicqde.domain.aicqde.T1070Request.AICTIA1070;
import org.fbi.aicqde.domain.aicqde.T1070Response.AICTOA1070;
import org.fbi.aicqde.domain.starring.T1070Request.TIA1070;
import org.fbi.aicqde.domain.starring.T1070Response.TOA1070;
import org.fbi.aicqde.enums.TxnRtnCode;
import org.fbi.aicqde.helper.AicqdeClient;
import org.fbi.aicqde.helper.ProjectConfigManager;
import org.fbi.linking.codec.dataformat.FixedLengthTextDataFormat;
import org.fbi.linking.codec.dataformat.SeperatedTextDataFormat;
import org.fbi.linking.processor.ProcessorException;
import org.fbi.linking.processor.standprotocol10.Stdp10Processor;
import org.fbi.linking.processor.standprotocol10.Stdp10ProcessorRequest;
import org.fbi.linking.processor.standprotocol10.Stdp10ProcessorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

/**
 * 1561070入资登记预交易-
 * zhanrui
 */
public class T1070processor extends Stdp10Processor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void service(Stdp10ProcessorRequest request, Stdp10ProcessorResponse response) throws ProcessorException, IOException {
        TIA1070 tia;

        try {
            tia = getStarringTia(request.getRequestBody());
        } catch (Exception e) {
            logger.error("特色业务平台请求报文解析错误.", e);
            response.setHeader("rtnCode", TxnRtnCode.CBSMSG_UNMARSHAL_FAILED.getCode());
            return;
        }

        //工商局通讯处理 -
        AICTIA1070 aictia1070 = assembleAictia1070(tia, request);
        AICTOA1070 aictoa1070 = null;
        String sendMsgForAic = null;
        try {
            sendMsgForAic = getSendMsgForAic(aictia1070);
        } catch (Exception e) {
            logger.error("生成工商请求报文时出错.", e);
            response.setHeader("rtnCode", TxnRtnCode.TPSMSG_MARSHAL_FAILED.getCode());
            return;
        }

        try {
            aictoa1070 = sendAndRecvForAic(sendMsgForAic);
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
        if (!"00".equals(aictoa1070.getRntCode())) {
            response.setHeader("rtnCode", TxnRtnCode.TXN_EXECUTE_FAILED.getCode());
            return;
        }
        TOA1070 toa = new TOA1070();
        toa.setActNo(aictoa1070.getActNo());
        toa.setEntName(aictoa1070.getEntName());
        toa.setBankName(aictoa1070.getBankName());

        //processTxn(aictia1070, aictoa1070);

        //组特色平台响应报文--
        String starringRespMsg = null;
        try {
            starringRespMsg = getRespMsgForStarring(toa);
        } catch (Exception e) {
            logger.error("特色平台响应报文处理失败.", e);
            throw new RuntimeException(e);
        }

        response.setResponseBody(starringRespMsg.getBytes(response.getCharacterEncoding()));
        response.setHeader("rtnCode", "0000");
    }

    //处理Starring请求报文-
    private TIA1070 getStarringTia(byte[] body) throws Exception {
        TIA1070 tia = new TIA1070();
        SeperatedTextDataFormat starringDataFormat = new SeperatedTextDataFormat(tia.getClass().getPackage().getName());
        tia = (TIA1070) starringDataFormat.fromMessage(new String(body, "GBK"), "TIA1070");
        return tia;
    }

    //生成工商请求报文对应BEAN
    private AICTIA1070 assembleAictia1070(TIA1070 tia, Stdp10ProcessorRequest request) {
        AICTIA1070 aictia1070 = new AICTIA1070();
        aictia1070.setTxnCode("1070");
        aictia1070.setBankCode(tia.getBankCode());
        aictia1070.setTellerId(request.getHeader("tellerId"));
        aictia1070.setBranchId(request.getHeader("branchId"));
        aictia1070.setAreaCode(tia.getAreaCode());
        aictia1070.setAicCode(tia.getAicCode());
        aictia1070.setPregNo(tia.getPregNo());
        return aictia1070;
    }

    //生成工商请求报文-
    private String getSendMsgForAic(AICTIA1070 aictia1070) throws Exception {
        Map<String, Object> modelObjectsMap = new HashMap<String, Object>();
        modelObjectsMap.put(aictia1070.getClass().getName(), aictia1070);
        FixedLengthTextDataFormat aicReqDataFormat = new FixedLengthTextDataFormat(aictia1070.getClass().getPackage().getName());

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

    //工商服务器通讯-
    private AICTOA1070 sendAndRecvForAic(String sendMsg) throws Exception {
        String servIp = ProjectConfigManager.getInstance().getProperty("aic.server.ip");
        int servPort = Integer.parseInt(ProjectConfigManager.getInstance().getProperty("aic.server.port"));

        AicqdeClient client = new AicqdeClient(servIp, servPort);
        byte[] recvbuf = client.call(sendMsg.getBytes("GBK"));
        String recvMsg = new String(recvbuf, "GBK");
        logger.info(" 工商返回：" + recvMsg);

        AICTOA1070 aictoa1070 = new AICTOA1070();
        FixedLengthTextDataFormat aicRespDataFormat = new FixedLengthTextDataFormat(aictoa1070.getClass().getPackage().getName());
        aictoa1070 = (AICTOA1070) aicRespDataFormat.fromMessage(recvMsg.getBytes("GBK"), "AICTOA1070");
        return aictoa1070;
    }

    //处理工商返回报文-
    private String getRespMsgForStarring(TOA1070 toa) throws Exception {
        String starringRespMsg;
        Map<String, Object> modelObjectsMap = new HashMap<String, Object>();
        modelObjectsMap.put(toa.getClass().getName(), toa);
        SeperatedTextDataFormat starringDataFormat = new SeperatedTextDataFormat(toa.getClass().getPackage().getName());
        starringRespMsg = (String) starringDataFormat.toMessage(modelObjectsMap);
        return starringRespMsg;
    }

    //业务逻辑处理-
/*
    private void processTxn(AICTIA1070 tia, AICTOA1070 toa) {
        SqlSessionFactory sqlSessionFactory = MybatisFactory.ORACLE.getInstance();
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AicQdeEnt record = new AicQdeEnt();
            BeanUtils.copyProperties(record, toa);
            record.setPregNo(tia.getPregNo());
            record.setAreaCode(tia.getAreaCode());
            record.setAicCode(tia.getAicCode());
            record.setAicName("工商局名称");
            record.setTellerId(tia.getTellerId());
            record.setBranchId(tia.getBranchId());
            record.setActBal(new BigDecimal("0.00"));

            AicQdeEntMapper mapper = session.getMapper(AicQdeEntMapper.class);
            mapper.insert(record);
            session.commit();
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("报文 bean copy 出错。", e);
        }
    }
*/

}
