package org.fbi.aicqde.domain.aicqde.T1070Request;


import org.fbi.linking.codec.dataformat.annotation.DataField;
import org.fbi.linking.codec.dataformat.annotation.FixedLengthTextMessage;

/**
 * User: zhanrui
 * Date: 13-11-22
 */
@FixedLengthTextMessage(mainClass = true)
public class AICTIA1070 {
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
    private String pregNo; //预登记号

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

    public String getPregNo() {
        return pregNo;
    }

    public void setPregNo(String pregNo) {
        this.pregNo = pregNo;
    }
}
