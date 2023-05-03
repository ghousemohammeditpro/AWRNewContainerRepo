package com.awr.autotrustproxy.response;

import com.awr.autotrustproxy.dto.MyGarageDataObject;

public class MyGarageVehiclesResponse {
    public MyGarageVehiclesResponse() {
        super();
    }
    private String status;
    private String message;
    private MyGarageDataObject data;

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

    public void setData(MyGarageDataObject data) {
        this.data = data;
    }

    public MyGarageDataObject getData() {
        return data;
    }
}
