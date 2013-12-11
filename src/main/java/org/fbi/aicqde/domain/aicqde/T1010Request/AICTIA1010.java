package org.fbi.aicqde.domain.aicqde.T1010Request;


import org.fbi.linking.codec.dataformat.annotation.DataField;
import org.fbi.linking.codec.dataformat.annotation.FixedLengthTextMessage;
import org.fbi.linking.codec.dataformat.annotation.OneToMany;

import java.util.List;

/**
 * User: zhanrui
 * Date: 13-11-22
 */
@FixedLengthTextMessage(mainClass = true)
public class AICTIA1010 {
    @DataField(seq = 1, length = 4)
    private String txnCode;    //交易码

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

    @DataField(seq = 8, length = 32)
    private String aicName; //工商局名称

    @DataField(seq = 9, length = 22)
    private String actNo; //入资帐号

    @DataField(seq = 10, length = 21, align = "R")
    private String actBal; //入资帐户余额

    @DataField(seq = 11, length = 14)
    private String bankHostSn; //流水号

    @DataField(seq = 12, length = 72)
    private String entName; //企业预核准名称

    @DataField(seq = 13, length = 50)
    private String bankName; //入资银行名称

    @DataField(seq = 14, length = 4)
    private String itemNum;

    @DataField(seq = 15, length = 188)
    @OneToMany(mappedTo = "org.fbi.aicqde.domain.aicqde.T1010Request.AICTIA1010Item", totalNumberField = "itemNum")
    private java.util.List<AICTIA1010Item> items;

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

    public String getAicName() {
        return aicName;
    }

    public void setAicName(String aicName) {
        this.aicName = aicName;
    }

    public String getActNo() {
        return actNo;
    }

    public void setActNo(String actNo) {
        this.actNo = actNo;
    }

    public String getActBal() {
        return actBal;
    }

    public void setActBal(String actBal) {
        this.actBal = actBal;
    }

    public String getBankHostSn() {
        return bankHostSn;
    }

    public void setBankHostSn(String bankHostSn) {
        this.bankHostSn = bankHostSn;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;
    }

    public List<AICTIA1010Item> getItems() {
        return items;
    }

    public void setItems(List<AICTIA1010Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "AICTIA1010{" +
                "txnCode='" + txnCode + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", tellerId='" + tellerId + '\'' +
                ", branchId='" + branchId + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", aicCode='" + aicCode + '\'' +
                ", pregNo='" + pregNo + '\'' +
                ", aicName='" + aicName + '\'' +
                ", actNo='" + actNo + '\'' +
                ", actBal='" + actBal + '\'' +
                ", bankHostSn='" + bankHostSn + '\'' +
                ", entName='" + entName + '\'' +
                ", bankName='" + bankName + '\'' +
                ", itemNum='" + itemNum + '\'' +
                ", items=" + items +
                '}';
    }
}
