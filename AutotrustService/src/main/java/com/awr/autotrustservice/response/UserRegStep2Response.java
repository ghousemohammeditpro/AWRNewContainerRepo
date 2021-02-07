package com.awr.autotrustservice.response;

public class UserRegStep2Response {
    public UserRegStep2Response() {
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
