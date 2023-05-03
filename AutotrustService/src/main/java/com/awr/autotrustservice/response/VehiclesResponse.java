package com.awr.autotrustservice.response;

import com.awr.autotrustservice.dto.Vehicle;

public class VehiclesResponse {
    public VehiclesResponse() {
        super();
    }
    
    private Vehicle[] vehicles;

    public void setVehicles(Vehicle[] vehicles) {
        this.vehicles = vehicles;
    }

    public Vehicle[] getVehicles() {
        return vehicles;
    }
}
