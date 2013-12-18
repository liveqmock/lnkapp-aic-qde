package org.fbi.aicqde.domain.aicqde.T1020Response;

import org.fbi.linking.codec.dataformat.annotation.DataField;
import org.fbi.linking.codec.dataformat.annotation.FixedLengthTextMessage;

/**
 * User: zhanrui
 * Date: 13-12-6
 */
@FixedLengthTextMessage(mainClass = true)
public class AICTOA1020 {
    @DataField(seq = 1, length = 2)
    private String rntCode;

    @DataField(seq = 2, length = 72)
    private String entName;

    @DataField(seq = 3, length = 32)
    private String regNo;

    public String getRntCode() {
        return rntCode;
    }

    public void setRntCode(String rntCode) {
        this.rntCode = rntCode;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    @Override
    public String toString() {
        return "AICTOA1020{" +
                "rntCode='" + rntCode + '\'' +
                ", entName='" + entName + '\'' +
                ", regNo='" + regNo + '\'' +
                '}';
    }
}
