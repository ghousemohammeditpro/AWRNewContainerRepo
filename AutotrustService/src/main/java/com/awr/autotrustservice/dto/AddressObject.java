package com.awr.autotrustservice.dto;

public class AddressObject {
    public AddressObject() {
        super();
    }
    private String Country;
    private String State;
    private String City;
    private String Street;
    private String Floor;
    private String Building;

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getCountry() {
        return Country;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getState() {
        return State;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getCity() {
        return City;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    public String getStreet() {
        return Street;
    }

    public void setFloor(String Floor) {
        this.Floor = Floor;
    }

    public String getFloor() {
        return Floor;
    }

    public void setBuilding(String Building) {
        this.Building = Building;
    }

    public String getBuilding() {
        return Building;
    }
}
