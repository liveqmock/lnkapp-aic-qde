package org.fbi.aicqde.domain.aicqde.T1021Request;


import org.fbi.linking.codec.dataformat.annotation.DataField;
import org.fbi.linking.codec.dataformat.annotation.FixedLengthTextMessage;

/**
 * User: zhanrui
 * Date: 13-11-22
 */
@FixedLengthTextMessage(mainClass = true)
public class AICTIA1021 {
    @DataField(seq = 1, length = 4)
    private String txnCode;    //交易代码

    @DataField(seq = 2, length = 2)
    private String bankCode;    //银行代码

    @DataField(seq = 3, length = 7)
    private String tellerId;    //柜员号

    @DataField(seq = 4, length = 5)
    private String branchId;    //机构号

    @DataField(seq = 5, length = 4)
    private String areaCode;  //地区码

    @DataField(seq = 6, length = 2)
    private String aicCode; //工商局编号

    @DataField(seq = 7, length = 32)
    private String regNo; //登记号

    @DataField(seq = 8, length = 50)
    private String bankName; //开户行名称

    @DataField(seq = 9, length = 32)
    private String transinActno; //划转入帐帐号

    @DataField(seq = 8, length = 8)
    private String transDate; //划转日期

    @DataField(seq = 9, length = 1)
    private String transBankFlag; //划转行标志	1-划转本行；2-划转他行


    public String getTxnCode() {
        return txnCode;
    }

    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getTellerId() {
        return tellerId;
    }

    public void setTellerId(String tellerId) {
        this.tellerId = tellerId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return "AICTIA1020{" +
                "txnCode='" + txnCode + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", tellerId='" + tellerId + '\'' +
                ", branchId='" + branchId + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", aicCode='" + aicCode + '\'' +
                ", regNo='" + regNo + '\'' +
                ", bankName='" + bankName + '\'' +
                ", transinActno='" + transinActno + '\'' +
                ", transDate='" + transDate + '\'' +
                ", transBankFlag='" + transBankFlag + '\'' +
                '}';
    }
}
