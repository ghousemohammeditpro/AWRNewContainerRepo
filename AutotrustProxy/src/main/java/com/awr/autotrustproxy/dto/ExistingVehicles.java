package com.awr.autotrustproxy.dto;

public class ExistingVehicles {
    public ExistingVehicles() {
        super();
    }
    private String existing_brand;
    private String existing_model;
    private String existing_model_age;

    public void setExisting_brand(String existing_brand) {
        this.existing_brand = existing_brand;
    }

    public String getExisting_brand() {
        return existing_brand;
    }

    public void setExisting_model(String existing_model) {
        this.existing_model = existing_model;
    }

    public String getExisting_model() {
        return existing_model;
    }

    public void setExisting_model_age(String existing_model_age) {
        this.existing_model_age = existing_model_age;
    }

    public String getExisting_model_age() {
        return existing_model_age;
    }
}
