package com.awr.autotrustproxy.response;

import com.awr.autotrustproxy.dto.PersonalDetailsObject;

public class LoadPersonalDetailsResponse {
    public LoadPersonalDetailsResponse() {
        super();
    }
    private String status;
    private String message;
    private PersonalDetailsObject data;

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

    public void setData(PersonalDetailsObject data) {
        this.data = data;
    }

    public PersonalDetailsObject getData() {
        return data;
    }
}
