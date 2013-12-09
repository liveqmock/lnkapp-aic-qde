package org.fbi.aicqde.helper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * 工商E线通测试 client
 * 注：未作通讯粘包处理
 * User: zhanrui
 * Date: 13-11-27
 */
public class AicqdeClient {
    private String ip;
    private int port;

    public AicqdeClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public byte[] call(byte[] sendbuf) throws Exception {
        byte[] recvbuf = null;

        InetAddress addr = InetAddress.getByName(ip);
        Socket socket = new Socket();
        try {
            //socket = new Socket(ip, port);
            socket.connect(new InetSocketAddress(addr, port), 10000);
            //socket.setSendBufferSize(100);

            socket.setSoTimeout(10000);
            //BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            OutputStream os = socket.getOutputStream();
            os.write(sendbuf);
            os.flush();

            InputStream is = socket.getInputStream();
            recvbuf = new byte[4];
            int readNum = is.read(recvbuf);
            if (readNum < 4) {
                throw new RuntimeException("报文长度错误...");
            }
            int msgLen = Integer.parseInt(new String(recvbuf).trim());
            recvbuf = new byte[msgLen - 4];

            readNum = is.read(recvbuf);
            if (readNum != msgLen - 4) {
                throw new RuntimeException("报文长度错误...");
            }
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                //
            }
        }
        return recvbuf;
    }


    public static void main(String... argv) throws UnsupportedEncodingException {
        AicqdeClient mock = new AicqdeClient("127.0.0.1", 60001);

        //1070：入资登记预交易
        String msg = "" +
                "1070" + //交易码
                "02" + //银行代码	2	CHAR	中行代码统一使用01
                "1111111" + //柜员号	7	CHAR	右补空格
                "22222" +  //机构号	5	CHAR	右补空格
                "3333" +   //地区码	4	CHAR	右补空格
                "44" +  //工商局编号	2	CHAR
                "12345678901234567890123456789012"; //预登记号	32	CHAR	右补空格

        String strLen = null;
        strLen = "" + (msg.getBytes("GBK").length + 4);
        String lpad = "";
        for (int i = 0; i < 4 - strLen.length(); i++) {
            lpad += "0";
        }
        strLen = lpad + strLen;


        byte[] recvbuf = new byte[0];
        try {
            recvbuf = mock.call((strLen + msg).getBytes("GBK"));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.printf("服务器返回：%s\n", new String(recvbuf, "GBK"));
    }
}
