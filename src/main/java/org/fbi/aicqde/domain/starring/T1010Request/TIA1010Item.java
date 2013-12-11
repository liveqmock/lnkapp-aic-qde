package org.fbi.aicqde.domain.starring.T1010Request;


import org.fbi.linking.codec.dataformat.annotation.DataField;
import org.fbi.linking.codec.dataformat.annotation.OneToManySeperatedTextMessage;

/**
 * Created with IntelliJ IDEA.
 * User: zhanrui
 * Date: 13-9-10
 * Time: ÏÂÎç5:44
 */
@OneToManySeperatedTextMessage(separator = ",")
public class TIA1010Item {
    @DataField(seq = 1)
    private String investerName;

    @DataField(seq = 2)
    private String actNo;

    @DataField(seq = 3)
    private String invAmt;

    @DataField(seq = 4)
    private String invDate;

    @DataField(seq = 5)
    private String actBankName;

    @DataField(seq = 6)
    private String certId;

    public String getInvesterName() {
        return investerName;
    }

    public void setInvesterName(String investerName) {
        this.investerName = investerName;
    }

    public String getActNo() {
        return actNo;
    }

    public void setActNo(String actNo) {
        this.actNo = actNo;
    }

    public String getInvAmt() {
        return invAmt;
    }

    public void setInvAmt(String invAmt) {
        this.invAmt = invAmt;
    }

    public String getInvDate() {
        return invDate;
    }

    public void setInvDate(String invDate) {
        this.invDate = invDate;
    }

    public String getActBankName() {
        return actBankName;
    }

    public void setActBankName(String actBankName) {
        this.actBankName = actBankName;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    @Override
    public String toString() {
        return "Item{" +
                "investerName='" + investerName + '\'' +
                ", actNo='" + actNo + '\'' +
                ", invAmt='" + invAmt + '\'' +
                ", invDate='" + invDate + '\'' +
                ", actBankName='" + actBankName + '\'' +
                ", certId='" + certId + '\'' +
                '}';
    }
}
