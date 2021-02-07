package com.awr.autotrustservice.response;

public class InstalledBaseCreateUpdateResponse {
    public InstalledBaseCreateUpdateResponse() {
        super();
    }
    private String successFlag;
    private String errorMessage;
    private String instanceID;

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
}
