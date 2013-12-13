package org.fbi.aicqde.domain.starring.T1071Request;


import org.fbi.linking.codec.dataformat.annotation.DataField;
import org.fbi.linking.codec.dataformat.annotation.SeperatedTextMessage;

/**
 * User: zhanrui
 * Date: 13-11-22
 */
@SeperatedTextMessage(separator = "\\|", mainClass = true)
public class TIA1071 {
    @DataField(seq = 1)
    private String bankCode;    //银行代码

    @DataField(seq = 2)
    private String areaCode;  //地区码

    @DataField(seq = 3)
    private String aicCode; //工商局编号

    @DataField(seq = 4)
    private String regNo; //企业注册号

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
}
