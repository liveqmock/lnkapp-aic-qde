package org.fbi.aicqde.domain.aicqde.T1010Response;

import org.fbi.linking.codec.dataformat.annotation.DataField;
import org.fbi.linking.codec.dataformat.annotation.FixedLengthTextMessage;

/**
 * User: zhanrui
 * Date: 13-12-6
 */
@FixedLengthTextMessage(mainClass = true)
public class AICTOA1010 {
    @DataField(seq = 1, length = 2)
    private String rntCode;

    @DataField(seq = 2, length = 32)
    private String pregNo;

    @DataField(seq = 3, length = 15)
    private String vchNos;

    @DataField(seq = 4, length = 14)
    private String bankHostSn;

    public String getRntCode() {
        return rntCode;
    }

    public void setRntCode(String rntCode) {
        this.rntCode = rntCode;
    }

    public String getPregNo() {
        return pregNo;
    }

    public void setPregNo(String pregNo) {
        this.pregNo = pregNo;
    }

    public String getVchNos() {
        return vchNos;
    }

    public void setVchNos(String vchNos) {
        this.vchNos = vchNos;
    }

    public String getBankHostSn() {
        return bankHostSn;
    }

    public void setBankHostSn(String bankHostSn) {
        this.bankHostSn = bankHostSn;
    }
}
