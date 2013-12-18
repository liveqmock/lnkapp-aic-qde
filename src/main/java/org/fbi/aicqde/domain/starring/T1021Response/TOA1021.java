package org.fbi.aicqde.domain.starring.T1021Response;


import org.fbi.linking.codec.dataformat.annotation.DataField;
import org.fbi.linking.codec.dataformat.annotation.SeperatedTextMessage;

@SeperatedTextMessage(separator = "\\|",  mainClass = true)
public class TOA1021 {
    @DataField(seq = 1)
    private String entName;   //ÆóÒµÃû³Æ

    @DataField(seq = 2)
    private String regNo;   //×¢²áºÅ

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
        return "TOA1020{" +
                "entName='" + entName + '\'' +
                ", regNo='" + regNo + '\'' +
                '}';
    }
}
