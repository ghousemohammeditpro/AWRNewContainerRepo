package com.awr.autotrustservice.response;

public class UserRegStep3Response {
    public UserRegStep3Response() {
        super();
    }
    
    private String status;
    private String message;
    private String data;

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

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
