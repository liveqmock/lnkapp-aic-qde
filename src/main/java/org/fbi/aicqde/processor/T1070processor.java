package org.fbi.aicqde.processor;


import org.fbi.aicqde.domain.starring.T1070Request.TIA1070;
import org.fbi.aicqde.domain.starring.T1070Response.TOA1070;
import org.fbi.aicqde.helper.AicqdeClient;
import org.fbi.aicqde.helper.ProjectConfigManager;
import org.fbi.linking.codec.dataformat.SeperatedTextDataFormat;
import org.fbi.linking.processor.ProcessorException;
import org.fbi.linking.processor.standprotocol10.Stdp10Processor;
import org.fbi.linking.processor.standprotocol10.Stdp10ProcessorRequest;
import org.fbi.linking.processor.standprotocol10.Stdp10ProcessorResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试交易
 */
public class T1070processor extends Stdp10Processor {

    @Override
    public void service(Stdp10ProcessorRequest request, Stdp10ProcessorResponse response) throws ProcessorException, IOException {
        byte[] body = request.getRequestBody();

        SeperatedTextDataFormat dataFormat = new SeperatedTextDataFormat("org.fbi.aicqde.domain.starring.T1070Request");
        TIA1070 tia = new TIA1070();
        try {
            tia = (TIA1070) dataFormat.fromMessage(new String(body, "GBK"), "TIA1070");
        } catch (Exception e) {
            e.printStackTrace();
//            logger.error("报文解析错误:", e);
//            sendMsg.rtnCode = TxnRtnCode.TXN_EXECUTE_FAILED.getCode();
//            sendMsg.msgBody =  "报文解析错误.".getBytes(THIRDPARTY_SERVER_CODING);
//            return sendMsg;
        }

        //1070：入资登记预交易
        String sendMsg = "" +
                "1070" + //交易码
                tia.getBankCode() + //银行代码	2	CHAR	中行代码统一使用01
                "1111111" + //柜员号	7	CHAR	右补空格
                "22222" +  //机构号	5	CHAR	右补空格
                "3333" +   //地区码	4	CHAR	右补空格
                "44" +  //工商局编号	2	CHAR
                "12345678901234567890123456789012"; //预登记号	32	CHAR	右补空格

        String strLen = "" + (sendMsg.getBytes("GBK").length + 4);
        String lpad = "";
        for (int i = 0; i < 4 - strLen.length(); i++) {
            lpad += "0";
        }
        strLen = lpad + strLen;
        sendMsg = strLen + sendMsg;


        //----
        String servIp = ProjectConfigManager.getInstance().getProperty("aic.server.ip");
        int servPort = Integer.parseInt(ProjectConfigManager.getInstance().getProperty("aic.server.port"));

        AicqdeClient client = new AicqdeClient(servIp, servPort);
        byte[] recvbuf = client.call(sendMsg.getBytes("GBK"));

        String recvMsg = new String(recvbuf, "GBK");
        System.out.printf("==========工商返回：===%s\n", recvMsg);

        //处理工商局返回报文--
        String rtnCode = byteToString(recvbuf,0,2);
        if (!"00".equals(rtnCode)) {
            //TODO
        }

        TOA1070 toa = new TOA1070();
        toa.setActNo(byteToString(recvbuf, 2, 22).trim());
        toa.setEntName(byteToString(recvbuf, 24, 72).trim());
        toa.setBankName(byteToString(recvbuf, 72, 50).trim());


        //组特色平台响应报文--
        Map<String, Object> modelObjectsMap = new HashMap<String, Object>();
        modelObjectsMap.put(toa.getClass().getName(), toa);
        dataFormat = new SeperatedTextDataFormat("org.fbi.aicqde.domain.starring.T1070Response");
        String result = null;

        try {
            result = (String) dataFormat.toMessage(modelObjectsMap);
            System.out.printf("特色平台响应报文：%s\n", result);
        } catch (Exception e) {
            throw new RuntimeException("响应报文处理错误", e);
        }


        response.setResponseBody(result.getBytes(response.getCharacterEncoding()));

        response.setHeader("rtnCode", "0000");
    }

    private String byteToString(byte[] buf, int offset, int length) throws UnsupportedEncodingException {
        byte[] t = new byte[length];
        System.arraycopy(buf,offset,t,0,length);
        return new String(t,"GBK");
    }
}
