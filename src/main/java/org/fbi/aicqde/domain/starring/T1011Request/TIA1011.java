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
    private String bankCode;    //���д���

    @DataField(seq = 2)
    private String areaCode;  //������

    @DataField(seq = 3)
    private String aicCode; //���ֱ̾��

    @DataField(seq = 4)
    private String regNo; //Ԥ�ǼǺ�

    @DataField(seq = 5)
    private String aicName; //���̾�����

    @DataField(seq = 6)
    private String actNo; //�����ʺ�

    @DataField(seq = 7)
    private String actBal; //�����ʻ����

    @DataField(seq = 8)
    private String entName; //��ҵԤ��׼����

    @DataField(seq = 9)
    private String bankName; //������������

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
