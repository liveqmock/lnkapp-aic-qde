package org.fbi.aicqde.processor;


import org.fbi.aicqde.domain.aicqde.T1020Request.AICTIA1020;
import org.fbi.aicqde.domain.aicqde.T1020Response.AICTOA1020;
import org.fbi.aicqde.domain.starring.T1020Request.TIA1020;
import org.fbi.aicqde.domain.starring.T1020Response.TOA1020;
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
 * 1561020	入资划转登记交易-
 * zhanrui
 */
public class T1020Processor extends AbstractTxnProcessor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doRequest(Stdp10ProcessorRequest request, Stdp10ProcessorResponse response) throws ProcessorException, IOException {
        TIA1020 tia;

        try {
            tia = getStarringTia(request.getRequestBody());
        } catch (Exception e) {
            logger.error("特色业务平台请求报文解析错误.", e);
            response.setHeader("rtnCode", TxnRtnCode.CBSMSG_UNMARSHAL_FAILED.getCode());
            return;
        }

        //工商局通讯处理 -
        AICTIA1020 aictia1020 = assembleAictia1020(tia, request);
        AICTOA1020 aictoa1020 = null;
        String sendMsgForAic = null;
        try {
            sendMsgForAic = getSendMsgForAic(aictia1020);
        } catch (Exception e) {
            logger.error("生成工商请求报文时出错.", e);
            response.setHeader("rtnCode", TxnRtnCode.TPSMSG_MARSHAL_FAILED.getCode());
            return;
        }

        try {
            aictoa1020 = sendAndRecvForAic(sendMsgForAic);
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
            String aicRntCode = aictoa1020.getRntCode();
            if (!"00".equals(aicRntCode)) {
                response.setHeader("rtnCode", TxnRtnCode.TXN_EXECUTE_FAILED.getCode());
                starringRespMsg = getErrorRespMsgForStarring(aicRntCode);
            } else {
                TOA1020 toa = new TOA1020();
                toa.setEntName(aictoa1020.getEntName());
                toa.setRegNo(aictoa1020.getRegNo());

                //processTxn(aictia1020, aictoa1020);
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
    private TIA1020 getStarringTia(byte[] body) throws Exception {
        TIA1020 tia = new TIA1020();
        SeperatedTextDataFormat starringDataFormat = new SeperatedTextDataFormat(tia.getClass().getPackage().getName());
        tia = (TIA1020) starringDataFormat.fromMessage(new String(body, "GBK"), "TIA1020");
        return tia;
    }

    //生成工商请求报文对应BEAN
    private AICTIA1020 assembleAictia1020(TIA1020 tia, Stdp10ProcessorRequest request) {
        AICTIA1020 aictia1020 = new AICTIA1020();

        aictia1020.setTxnCode("1020");
        aictia1020.setBankCode(tia.getBankCode());
        aictia1020.setTellerId(request.getHeader("tellerId"));
        aictia1020.setBranchId(request.getHeader("branchId"));
        aictia1020.setAreaCode(tia.getAreaCode());
        aictia1020.setAicCode(tia.getAicCode());
        aictia1020.setPregNo(tia.getPregNo());
        return aictia1020;
    }

    //生成工商请求报文-
    private String getSendMsgForAic(AICTIA1020 aictia1020) throws Exception {
        Map<String, Object> modelObjectsMap = new HashMap<String, Object>();
        modelObjectsMap.put(aictia1020.getClass().getName(), aictia1020);
        FixedLengthTextDataFormat aicReqDataFormat = new FixedLengthTextDataFormat(aictia1020.getClass().getPackage().getName());

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
    private AICTOA1020 sendAndRecvForAic(String sendMsg) throws Exception {
        String recvMsg = processThirdPartyServer(sendMsg);
        logger.info("工商返回：" + recvMsg);

        AICTOA1020 aictoa1020 = new AICTOA1020();
        FixedLengthTextDataFormat aicRespDataFormat = new FixedLengthTextDataFormat(aictoa1020.getClass().getPackage().getName());
        aictoa1020 = (AICTOA1020) aicRespDataFormat.fromMessage(recvMsg.getBytes("GBK"), "AICTOA1020");
        return aictoa1020;
    }

    //处理工商返回报文-
    private String getRespMsgForStarring(TOA1020 toa) throws Exception {
        String starringRespMsg;
        Map<String, Object> modelObjectsMap = new HashMap<String, Object>();
        modelObjectsMap.put(toa.getClass().getName(), toa);
        SeperatedTextDataFormat starringDataFormat = new SeperatedTextDataFormat(toa.getClass().getPackage().getName());
        starringRespMsg = (String) starringDataFormat.toMessage(modelObjectsMap);
        return starringRespMsg;
    }

    //业务逻辑处理-
/*
    private void processTxn(AICTIA1020 tia, AICTOA1020 toa) {
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
