package org.fbi.aicqde.domain.starring.T1010Response;

import org.fbi.linking.codec.dataformat.annotation.DataField;
import org.fbi.linking.codec.dataformat.annotation.SeperatedTextMessage;

@SeperatedTextMessage(separator = "\\|",  mainClass = true)
public class TOA1010 {
    @DataField(seq = 1)
    private String actNo; //�����ʺ�

    @DataField(seq = 2)
    private String entName;   //��ҵ����

    @DataField(seq = 3)
    private String bankName;   //������������

    //============================
    public String getActNo() {
        return actNo;
    }

    public void setActNo(String actNo) {
        this.actNo = actNo;
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

    @Override
    public String toString() {
        return "TOA1070{" +
                "actNo='" + actNo + '\'' +
                ", entName='" + entName + '\'' +
                ", bankName='" + bankName + '\'' +
                '}';
    }
}