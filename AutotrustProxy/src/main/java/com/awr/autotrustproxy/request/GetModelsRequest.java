package com.awr.autotrustproxy.request;

public class GetModelsRequest {
    public GetModelsRequest() {
        super();
    }
    private String stockType;
    private String make;

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
}
