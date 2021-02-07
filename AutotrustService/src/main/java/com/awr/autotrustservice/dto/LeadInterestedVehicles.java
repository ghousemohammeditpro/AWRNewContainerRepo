package com.awr.autotrustservice.dto;

public class LeadInterestedVehicles {
    public LeadInterestedVehicles() {
        super();
    }
    private String interested_brand;
    private String interested_model;
    private String interested_model_year; //Number
    private String interested_model_colour;


    public void setInterested_brand(String interested_brand) {
        this.interested_brand = interested_brand;
    }

    public String getInterested_brand() {
        return interested_brand;
    }

    public void setInterested_model(String interested_model) {
        this.interested_model = interested_model;
    }

    public String getInterested_model() {
        return interested_model;
    }

    public void setInterested_model_year(String interested_model_year) {
        this.interested_model_year = interested_model_year;
    }

    public String getInterested_model_year() {
        return interested_model_year;
    }

    public void setInterested_model_colour(String interested_model_colour) {
        this.interested_model_colour = interested_model_colour;
    }

    public String getInterested_model_colour() {
        return interested_model_colour;
    }
}
