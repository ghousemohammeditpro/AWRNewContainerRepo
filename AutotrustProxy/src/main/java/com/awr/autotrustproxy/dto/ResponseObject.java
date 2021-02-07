package com.awr.autotrustproxy.dto;

public class ResponseObject {
    public ResponseObject() {
        super();
    }
    private String responseCode;
    private String responseMessage; 
    private String errorDescription;
    private String timestamp;
    private int custId = 0;
    private int custContactId = 0;

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustContactId(int custContactId) {
        this.custContactId = custContactId;
    }

    public int getCustContactId() {
        return custContactId;
    }
}
