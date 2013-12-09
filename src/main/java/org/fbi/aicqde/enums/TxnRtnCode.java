package org.fbi.aicqde.enums;

import java.util.Hashtable;

/**
 * 业务交易返回码
 */
public enum TxnRtnCode implements EnumApp {

    TXN_EXECUTE_SECCESS("0000", "交易完成"),
    TXN_PAY_REPEATED("0001", "该票据已缴款"),  //与交易完成相同，便于特色平台进行重复缴款

    TXN_EXECUTE_FAILED("1100", "交易失败"),

    MSG_AUTHENTICATE_ILLEGAL("2000", "报文验证失败"),
    MSG_PARSE_FAILED("2001", "报文解析错误"),

    MSG_AMT_ERROR("4001", "金额错误"),
    TXN_REFUND_NOT_ALLOWED("4010", "系统已对账，不能取消缴款。"),
    TXN_CHKFILE_NOT_UPLOADED("4020", "对账文件上传失败。"),
    TXN_BMK_NOT_EXIST("4030", "未上传该笔不明款交易。"),
    TXN_RECHKACT_NOT_ALLOWED("4040", "不能重复对账。"),
    TXN_CHKACT_NOT_SUC("4050", "企业对账不平。"),

    MSG_RECV_TIMEOUT("3000", "通信超时"),
    MSG_COMM_ERROR("3001", "通信异常"),

    UNKNOWN_EXCEPTION("9000", "其他未知异常");

    private String code = null;
    private String title = null;
    private static Hashtable<String, TxnRtnCode> aliasEnums;

    TxnRtnCode(String code, String title) {
        this.init(code, title);
    }

    @SuppressWarnings("unchecked")
    private void init(String code, String title) {
        this.code = code;
        this.title = title;
        synchronized (this.getClass()) {
            if (aliasEnums == null) {
                aliasEnums = new Hashtable();
            }
        }
        aliasEnums.put(code, this);
        aliasEnums.put(title, this);
    }

    public static TxnRtnCode valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }

    public String toRtnMsg() {
        return this.code + "|" + this.title;
    }
}
