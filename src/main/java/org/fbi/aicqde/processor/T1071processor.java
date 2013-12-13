package org.fbi.aicqde.processor;


import org.fbi.aicqde.domain.aicqde.T1071Request.AICTIA1071;
import org.fbi.aicqde.domain.aicqde.T1071Response.AICTOA1071;
import org.fbi.aicqde.domain.starring.T1071Request.TIA1071;
import org.fbi.aicqde.domain.starring.T1071Response.TOA1071;
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
 * 1561071���ʵǼ�Ԥ����
 * zhanrui
 */
public class T1071processor extends Stdp10Processor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void service(Stdp10ProcessorRequest request, Stdp10ProcessorResponse response) throws ProcessorException, IOException {
        TIA1071 tia;

        try {
            tia = getStarringTia(request.getRequestBody());
        } catch (Exception e) {
            logger.error("��ɫҵ��ƽ̨�����Ľ�������.", e);
            response.setHeader("rtnCode", TxnRtnCode.CBSMSG_UNMARSHAL_FAILED.getCode());
            return;
        }

        //���̾�ͨѶ���� -
        AICTIA1071 aictia1071 = assembleAictia1071(tia);
        AICTOA1071 aictoa1071 = null;
        String sendMsgForAic = null;
        try {
            sendMsgForAic = getSendMsgForAic(aictia1071);
        } catch (Exception e) {
            logger.error("���ɹ���������ʱ����.", e);
            response.setHeader("rtnCode", TxnRtnCode.TPSMSG_MARSHAL_FAILED.getCode());
            return;
        }

        try {
            aictoa1071 = sendAndRecvForAic(sendMsgForAic);
        } catch (SocketTimeoutException e) {
            logger.error("�빤�̷�����ͨѶ����ʱ.", e);
            response.setHeader("rtnCode", TxnRtnCode.MSG_RECV_TIMEOUT.getCode());
            return;
        } catch (Exception e) {
            logger.error("�빤�̷�����ͨѶ�����쳣.", e);
            response.setHeader("rtnCode", TxnRtnCode.MSG_COMM_ERROR.getCode());
            return;
        }

        //�����ַ̾��ر���--
        if (!"00".equals(aictoa1071.getRntCode())) {
            response.setHeader("rtnCode", TxnRtnCode.TXN_EXECUTE_FAILED.getCode());
            return;
        }
        TOA1071 toa = new TOA1071();
        toa.setActNo(aictoa1071.getActNo());
        toa.setEntName(aictoa1071.getEntName());
        toa.setBankName(aictoa1071.getBankName());

        //processTxn(aictia1071, aictoa1071);

        //����ɫƽ̨��Ӧ����--
        String starringRespMsg = null;
        try {
            starringRespMsg = getRespMsgForStarring(toa);
        } catch (Exception e) {
            logger.error("��ɫƽ̨��Ӧ���Ĵ���ʧ��.", e);
            throw new RuntimeException(e);
        }

        response.setResponseBody(starringRespMsg.getBytes(response.getCharacterEncoding()));
        response.setHeader("rtnCode", "0000");
    }

    //����Starring������
    private TIA1071 getStarringTia(byte[] body) throws Exception {
        TIA1071 tia = new TIA1071();
        SeperatedTextDataFormat starringDataFormat = new SeperatedTextDataFormat(tia.getClass().getPackage().getName());
        tia = (TIA1071) starringDataFormat.fromMessage(new String(body, "GBK"), "TIA1071");
        return tia;
    }

    //���ɹ��������Ķ�ӦBEAN
    private AICTIA1071 assembleAictia1071(TIA1071 tia) {
        AICTIA1071 aictia1071 = new AICTIA1071();
        aictia1071.setTxnCode("1071");
        aictia1071.setBankCode(tia.getBankCode());
        aictia1071.setTellerId("7777777");
        aictia1071.setBranchId("55555");
        aictia1071.setAreaCode(tia.getAreaCode());
        aictia1071.setAicCode(tia.getAicCode());
        aictia1071.setRegNo(tia.getRegNo());
        return aictia1071;
    }

    //���ɹ���������
    private String getSendMsgForAic(AICTIA1071 aictia1071) throws Exception {
        Map<String, Object> modelObjectsMap = new HashMap<String, Object>();
        modelObjectsMap.put(aictia1071.getClass().getName(), aictia1071);
        FixedLengthTextDataFormat aicReqDataFormat = new FixedLengthTextDataFormat(aictia1071.getClass().getPackage().getName());

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

    //���̷�����ͨѶ
    private AICTOA1071 sendAndRecvForAic(String sendMsg) throws Exception {
        String servIp = ProjectConfigManager.getInstance().getProperty("aic.server.ip");
        int servPort = Integer.parseInt(ProjectConfigManager.getInstance().getProperty("aic.server.port"));

        AicqdeClient client = new AicqdeClient(servIp, servPort);
        byte[] recvbuf = client.call(sendMsg.getBytes("GBK"));

        String recvMsg = new String(recvbuf, "GBK");
        logger.info("���̷��أ�" + recvMsg);

        AICTOA1071 aictoa1071 = new AICTOA1071();
        FixedLengthTextDataFormat aicRespDataFormat = new FixedLengthTextDataFormat(aictoa1071.getClass().getPackage().getName());
        aictoa1071 = (AICTOA1071) aicRespDataFormat.fromMessage(recvMsg.getBytes("GBK"), "AICTOA1071");
        return aictoa1071;
    }

    //�����̷��ر���
    private String getRespMsgForStarring(TOA1071 toa) throws Exception {
        String starringRespMsg;
        Map<String, Object> modelObjectsMap = new HashMap<String, Object>();
        modelObjectsMap.put(toa.getClass().getName(), toa);
        SeperatedTextDataFormat starringDataFormat = new SeperatedTextDataFormat(toa.getClass().getPackage().getName());
        starringRespMsg = (String) starringDataFormat.toMessage(modelObjectsMap);
        return starringRespMsg;
    }

}
