package org.fbi.aicqde.domain.starring.T1020Request;


import org.fbi.linking.codec.dataformat.annotation.DataField;
import org.fbi.linking.codec.dataformat.annotation.SeperatedTextMessage;

/**
 * User: zhanrui
 * Date: 13-11-22
 */
@SeperatedTextMessage(separator = "\\|", mainClass = true)
public class TIA1020 {
    @DataField(seq = 1)
    private String bankCode;    //银行代码

    @DataField(seq = 2)
    private String areaCode;  //地区码

    @DataField(seq = 3)
    private String aicCode; //工商局编号

    @DataField(seq = 4)
    private String pregNo; //预登记号

    @DataField(seq = 5)
    private String bankName; //开户行名称

    @DataField(seq = 6)
    private String transinActno; //划转入帐帐号

    @DataField(seq = 7)
    private String transDate; //划转日期

    @DataField(seq = 8)
    private String transBankFlag; //划转行标志	1-划转本行；2-划转他行

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAicCode() {
        return aicCode;
    }

    public void setAicCode(String aicCode) {
        this.aicCode = aicCode;
    }

    public String getPregNo() {
        return pregNo;
    }

    public void setPregNo(String pregNo) {
        this.pregNo = pregNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getTransinActno() {
        return transinActno;
    }

    public void setTransinActno(String transinActno) {
        this.transinActno = transinActno;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getTransBankFlag() {
        return transBankFlag;
    }

    public void setTransBankFlag(String transBankFlag) {
        this.transBankFlag = transBankFlag;
    }

    @Override
    public String toString() {
        return "TIA1020{" +
                "bankCode='" + bankCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", aicCode='" + aicCode + '\'' +
                ", pregNo='" + pregNo + '\'' +
                ", bankName='" + bankName + '\'' +
                ", transinActno='" + transinActno + '\'' +
                ", transDate='" + transDate + '\'' +
                ", transBankFlag='" + transBankFlag + '\'' +
                '}';
    }
}