package org.fbi.aicqde.domain.aicqde.T1070Response;

import org.fbi.linking.codec.dataformat.annotation.DataField;
import org.fbi.linking.codec.dataformat.annotation.FixedLengthTextMessage;

/**
 * User: zhanrui
 * Date: 13-12-6
 */
@FixedLengthTextMessage(mainClass = true)
public class AICTOA1070 {
    @DataField(seq = 1, length = 2)
    private String rntCode;

    @DataField(seq = 2, length = 22)
    private String actNo;

    @DataField(seq = 3, length = 72)
    private String entName;

    @DataField(seq = 4, length = 50)
    private String bankName;

    public String getRntCode() {
        return rntCode;
    }

    public void setRntCode(String rntCode) {
        this.rntCode = rntCode;
    }

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
}
