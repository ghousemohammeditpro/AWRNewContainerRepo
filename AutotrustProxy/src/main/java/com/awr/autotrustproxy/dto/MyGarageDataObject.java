package com.awr.autotrustproxy.dto;

public class MyGarageDataObject {
    public MyGarageDataObject() {
        super();
    }
    private GarageVehicleObject[] cars;

    public void setCars(GarageVehicleObject[] cars) {
        this.cars = cars;
    }

    public GarageVehicleObject[] getCars() {
        return cars;
    }
}
