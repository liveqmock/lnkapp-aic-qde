package org.fbi.aicqde.domain.aicqde.T1021Response;

import org.fbi.linking.codec.dataformat.annotation.DataField;
import org.fbi.linking.codec.dataformat.annotation.FixedLengthTextMessage;

/**
 * User: zhanrui
 * Date: 13-12-6
 */
@FixedLengthTextMessage(mainClass = true)
public class AICTOA1021 {
    @DataField(seq = 1, length = 2)
    private String rntCode;

    @DataField(seq = 2, length = 72)
    private String entName;

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

    @Override
    public String toString() {
        return "AICTOA1020{" +
                "rntCode='" + rntCode + '\'' +
                ", entName='" + entName + '\'' +
                '}';
    }
}
