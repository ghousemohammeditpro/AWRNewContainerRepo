package com.awr.autotrustservice.response;

public class CreateSFLeadResponse {
    public CreateSFLeadResponse() {
        super();
    }
    private String retMsg;
    private String retCode;
    private String refNo;

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getRefNo() {
        return refNo;
    }
}
