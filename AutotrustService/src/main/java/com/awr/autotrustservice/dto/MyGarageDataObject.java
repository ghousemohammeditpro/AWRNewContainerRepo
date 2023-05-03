package com.awr.autotrustservice.dto;

public class MyGarageDataObject {
    public MyGarageDataObject() {
        super();
    }
    private GarageVehicleObject[] Cars;

    public void setCars(GarageVehicleObject[] Cars) {
        this.Cars = Cars;
    }

    public GarageVehicleObject[] getCars() {
        return Cars;
    }
}
