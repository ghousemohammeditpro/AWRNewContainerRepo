package com.awr.autotrustproxy.response;

public class InstalledBaseCreateUpdateResponse {
    public InstalledBaseCreateUpdateResponse() {
        super();
    }
    private String successFlag;
    private String errorMessage;
    private String instanceID;
    private String retCode;
    private String retMsg;

    public void setSuccessFlag(String successFlag) {
        this.successFlag = successFlag;
    }

    public String getSuccessFlag() {
        return successFlag;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setInstanceID(String instanceID) {
        this.instanceID = instanceID;
    }

    public String getInstanceID() {
        return instanceID;
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
