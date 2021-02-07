package com.awr.autotrustproxy.request;

public class GetStockRequest {
    public GetStockRequest() {
        super();
    }
    private String stockType;
    private String make;
    private String model;

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getStockType() {
        return stockType;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getMake() {
        return make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}
