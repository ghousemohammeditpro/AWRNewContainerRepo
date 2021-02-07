package com.awr.autotrustproxy.response;

import com.awr.autotrustproxy.dto.CpoDealerVehicle;

public class CpoDealerVehiclesResponse {
    public CpoDealerVehiclesResponse() {
        super();
    }
    private CpoDealerVehicle[] vehicles;
    private String message;
    private String status;


    public void setVehicles(CpoDealerVehicle[] vehicles) {
        this.vehicles = vehicles;
    }

    public CpoDealerVehicle[] getVehicles() {
        return vehicles;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
