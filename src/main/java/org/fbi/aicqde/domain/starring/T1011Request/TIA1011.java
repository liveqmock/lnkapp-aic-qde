package org.fbi.aicqde.domain.starring.T1011Request;


import org.fbi.linking.codec.dataformat.annotation.DataField;
import org.fbi.linking.codec.dataformat.annotation.OneToMany;
import org.fbi.linking.codec.dataformat.annotation.SeperatedTextMessage;

import java.util.List;

/**
 * User: zhanrui
 * Date: 13-11-22
 */
@SeperatedTextMessage(separator = "\\|", mainClass = true)
public class TIA1011 {
    @DataField(seq = 1)
    private String bankCode;    //银行代码

    @DataField(seq = 2)
    private String areaCode;  //地区码

    @DataField(seq = 3)
    private String aicCode; //工商局编号

    @DataField(seq = 4)
    private String regNo; //预登记号

    @DataField(seq = 5)
    private String aicName; //工商局名称

    @DataField(seq = 6)
    private String actNo; //入资帐号

    @DataField(seq = 7)
    private String actBal; //入资帐户余额

    @DataField(seq = 8)
    private String entName; //企业预核准名称

    @DataField(seq = 9)
    private String bankName; //入资银行名称

    @DataField(seq = 10)
    private String itemNum;

    @DataField(seq = 11)
    @OneToMany(mappedTo = "org.fbi.aicqde.domain.starring.T1010Request.TIA1010Item", totalNumberField = "itemNum")
    private List<TIA1011Item> items;

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

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
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

    public List<TIA1011Item> getItems() {
        return items;
    }

    public void setItems(List<TIA1011Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "TIA1010{" +
                "bankCode='" + bankCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", aicCode='" + aicCode + '\'' +
                ", regNo='" + regNo + '\'' +
                ", aicName='" + aicName + '\'' +
                ", actNo='" + actNo + '\'' +
                ", actBal='" + actBal + '\'' +
                ", entName='" + entName + '\'' +
                ", bankName='" + bankName + '\'' +
                ", itemNum='" + itemNum + '\'' +
                ", items=" + items +
                '}';
    }
}
