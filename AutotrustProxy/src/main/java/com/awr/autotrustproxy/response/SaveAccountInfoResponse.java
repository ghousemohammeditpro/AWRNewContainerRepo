package com.awr.autotrustproxy.response;

public class SaveAccountInfoResponse {
    public SaveAccountInfoResponse() {
        super();
    }
    private String status;
    private String message;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
