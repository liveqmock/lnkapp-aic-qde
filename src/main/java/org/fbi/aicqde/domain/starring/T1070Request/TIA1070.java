package org.fbi.aicqde.domain.starring.T1070Request;


import org.fbi.linking.codec.dataformat.annotation.DataField;
import org.fbi.linking.codec.dataformat.annotation.SeperatedTextMessage;

/**
 * User: zhanrui
 * Date: 13-11-22
 */
@SeperatedTextMessage(separator = "\\|", mainClass = true)
public class TIA1070 {
    @DataField(seq = 1)
    private String bankCode;    //���д���

    @DataField(seq = 2)
    private String areaCode;  //������

    @DataField(seq = 3)
    private String aicCode; //���ֱ̾��

    @DataField(seq = 4)
    private String pregNo; //Ԥ�ǼǺ�

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
}
