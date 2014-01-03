package org.fbi.aicqde.processor;


import org.fbi.aicqde.domain.aicqde.T1021Request.AICTIA1021;
import org.fbi.aicqde.domain.aicqde.T1021Response.AICTOA1021;
import org.fbi.aicqde.domain.starring.T1021Request.TIA1021;
import org.fbi.aicqde.domain.starring.T1021Response.TOA1021;
import org.fbi.aicqde.enums.TxnRtnCode;
import org.fbi.linking.codec.dataformat.FixedLengthTextDataFormat;
import org.fbi.linking.codec.dataformat.SeperatedTextDataFormat;
import org.fbi.linking.processor.ProcessorException;
import org.fbi.linking.processor.standprotocol10.Stdp10ProcessorRequest;
import org.fbi.linking.processor.standprotocol10.Stdp10ProcessorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

/**
 * 1561021	增资划转登记交易-
 * zhanrui
 */
public class T1021Processor extends AbstractTxnProcessor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doRequest(Stdp10ProcessorRequest request, Stdp10ProcessorResponse response) throws ProcessorException, IOException {
        TIA1021 tia;

        try {
            tia = getStarringTia(request.getRequestBody());
        } catch (Exception e) {
            logger.error("特色业务平台请求报文解析错误.", e);
            response.setHeader("rtnCode", TxnRtnCode.CBSMSG_UNMARSHAL_FAILED.getCode());
            return;
        }

        //工商局通讯处理 -
        AICTIA1021 aictia1021 = assembleAictia1021(tia, request);
        AICTOA1021 aictoa1021 = null;
        String sendMsgForAic = null;
        try {
            sendMsgForAic = getSendMsgForAic(aictia1021);
        } catch (Exception e) {
            logger.error("生成工商请求报文时出错.", e);
            response.setHeader("rtnCode", TxnRtnCode.TPSMSG_MARSHAL_FAILED.getCode());
            return;
        }

        try {
            aictoa1021 = sendAndRecvForAic(sendMsgForAic);
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
            String aicRntCode = aictoa1021.getRntCode();
            if (!"00".equals(aicRntCode)) {
                response.setHeader("rtnCode", TxnRtnCode.TXN_EXECUTE_FAILED.getCode());
                starringRespMsg = getErrorRespMsgForStarring(aicRntCode);
            } else {
                TOA1021 toa = new TOA1021();
                toa.setEntName(aictoa1021.getEntName());

                //processTxn(aictia1021, aictoa1021);
                starringRespMsg = getRespMsgForStarring(toa);
                response.setHeader("rtnCode", "0000");
            }
        } catch (Exception e) {
            logger.error("特色平台响应报文处理失败.", e);
            throw new RuntimeException(e);
        }

        response.setResponseBody(starringRespMsg.getBytes(response.getCharacterEncoding()));
    }

    //处理Starring请求报文-
    private TIA1021 getStarringTia(byte[] body) throws Exception {
        TIA1021 tia = new TIA1021();
        SeperatedTextDataFormat starringDataFormat = new SeperatedTextDataFormat(tia.getClass().getPackage().getName());
        tia = (TIA1021) starringDataFormat.fromMessage(new String(body, "GBK"), "TIA1021");
        return tia;
    }

    //生成工商请求报文对应BEAN
    private AICTIA1021 assembleAictia1021(TIA1021 tia, Stdp10ProcessorRequest request) {
        AICTIA1021 aictia1021 = new AICTIA1021();
        aictia1021.setTxnCode("1021");
        aictia1021.setBankCode(tia.getBankCode());
        aictia1021.setTellerId(request.getHeader("tellerId"));
        aictia1021.setBranchId(request.getHeader("branchId"));
        aictia1021.setAreaCode(tia.getAreaCode());
        aictia1021.setAicCode(tia.getAicCode());
        aictia1021.setRegNo(tia.getRegNo());
        return aictia1021;
    }

    //生成工商请求报文-
    private String getSendMsgForAic(AICTIA1021 aictia1021) throws Exception {
        Map<String, Object> modelObjectsMap = new HashMap<String, Object>();
        modelObjectsMap.put(aictia1021.getClass().getName(), aictia1021);
        FixedLengthTextDataFormat aicReqDataFormat = new FixedLengthTextDataFormat(aictia1021.getClass().getPackage().getName());

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
    private AICTOA1021 sendAndRecvForAic(String sendMsg) throws Exception {
        String recvMsg = processThirdPartyServer(sendMsg);
        logger.info("工商返回：" + recvMsg);

        AICTOA1021 aictoa1021 = new AICTOA1021();
        FixedLengthTextDataFormat aicRespDataFormat = new FixedLengthTextDataFormat(aictoa1021.getClass().getPackage().getName());
        aictoa1021 = (AICTOA1021) aicRespDataFormat.fromMessage(recvMsg.getBytes("GBK"), "AICTOA1021");
        return aictoa1021;
    }

    //处理工商返回报文-
    private String getRespMsgForStarring(TOA1021 toa) throws Exception {
        String starringRespMsg;
        Map<String, Object> modelObjectsMap = new HashMap<String, Object>();
        modelObjectsMap.put(toa.getClass().getName(), toa);
        SeperatedTextDataFormat starringDataFormat = new SeperatedTextDataFormat(toa.getClass().getPackage().getName());
        starringRespMsg = (String) starringDataFormat.toMessage(modelObjectsMap);
        return starringRespMsg;
    }

    //业务逻辑处理-
/*
    private void processTxn(AICTIA1021 tia, AICTOA1021 toa) {
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
