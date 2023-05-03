package com.awr.autotrustproxy.response;

import com.awr.autotrustproxy.dto.Vehicle;

public class VehiclesResponse {
    public VehiclesResponse() {
        super();
    }
    
    private Vehicle[] vehicles;
    private String message;
    private String status;

    public void setVehicles(Vehicle[] vehicles) {
        this.vehicles = vehicles;
    }

    public Vehicle[] getVehicles() {
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
