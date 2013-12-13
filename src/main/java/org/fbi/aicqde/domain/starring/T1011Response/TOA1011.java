package org.fbi.aicqde.domain.starring.T1011Response;

import org.fbi.linking.codec.dataformat.annotation.DataField;
import org.fbi.linking.codec.dataformat.annotation.SeperatedTextMessage;

@SeperatedTextMessage(separator = "\\|",  mainClass = true)
public class TOA1011 {
    @DataField(seq = 1)
    private String regNo; //预登记号
    @DataField(seq = 2)
    private String vchSn1; //入资凭证序号
    @DataField(seq = 3)
    private String vchSn2; //入资凭证序号
    @DataField(seq = 4)
    private String vchSn3; //入资凭证序号
    @DataField(seq = 5)
    private String vchSn4; //入资凭证序号
    @DataField(seq = 6)
    private String vchSn5; //入资凭证序号

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getVchSn1() {
        return vchSn1;
    }

    public void setVchSn1(String vchSn1) {
        this.vchSn1 = vchSn1;
    }

    public String getVchSn2() {
        return vchSn2;
    }

    public void setVchSn2(String vchSn2) {
        this.vchSn2 = vchSn2;
    }

    public String getVchSn3() {
        return vchSn3;
    }

    public void setVchSn3(String vchSn3) {
        this.vchSn3 = vchSn3;
    }

    public String getVchSn4() {
        return vchSn4;
    }

    public void setVchSn4(String vchSn4) {
        this.vchSn4 = vchSn4;
    }

    public String getVchSn5() {
        return vchSn5;
    }

    public void setVchSn5(String vchSn5) {
        this.vchSn5 = vchSn5;
    }

    @Override
    public String toString() {
        return "TOA1011{" +
                "regNo='" + regNo + '\'' +
                ", vchSn1='" + vchSn1 + '\'' +
                ", vchSn2='" + vchSn2 + '\'' +
                ", vchSn3='" + vchSn3 + '\'' +
                ", vchSn4='" + vchSn4 + '\'' +
                ", vchSn5='" + vchSn5 + '\'' +
                '}';
    }
}
