package com.awr.autotrustservice.response;

import com.awr.autotrustservice.dto.CpoDealerVehicle;

public class CpoDealerVehiclesResponse {
    public CpoDealerVehiclesResponse() {
        super();
    }
    private String status;
    private String message;
    private CpoDealerVehicle[] vehicles;


    public void setVehicles(CpoDealerVehicle[] vehicles) {
        this.vehicles = vehicles;
    }

    public CpoDealerVehicle[] getVehicles() {
        return vehicles;
    }

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
