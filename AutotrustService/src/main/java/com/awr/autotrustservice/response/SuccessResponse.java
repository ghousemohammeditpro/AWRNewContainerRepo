package com.awr.autotrustservice.response;

public class SuccessResponse {
    String retStatus;
    String retCode;
    String retMsg;

    public SuccessResponse() {
        super();
    }


    public void setRetStatus(String retStatus) {
        this.retStatus = retStatus;
    }

    public String getRetStatus() {
        return retStatus;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getRetMsg() {
        return retMsg;
    }
}
