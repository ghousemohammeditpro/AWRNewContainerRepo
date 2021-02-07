package com.awr.autotrustservice.dto;

public class TradeinVehicles {
    public TradeinVehicles() {
        super();
    }
    private String trade_in_brand;
    private String trade_in_model;
    private String trade_in_model_year;
    private String trade_in_mileage;
    private String trade_in_expected_amt;


    public void setTrade_in_brand(String trade_in_brand) {
        this.trade_in_brand = trade_in_brand;
    }

    public String getTrade_in_brand() {
        return trade_in_brand;
    }

    public void setTrade_in_model(String trade_in_model) {
        this.trade_in_model = trade_in_model;
    }

    public String getTrade_in_model() {
        return trade_in_model;
    }

    public void setTrade_in_model_year(String trade_in_model_year) {
        this.trade_in_model_year = trade_in_model_year;
    }

    public String getTrade_in_model_year() {
        return trade_in_model_year;
    }

    public void setTrade_in_mileage(String trade_in_mileage) {
        this.trade_in_mileage = trade_in_mileage;
    }

    public String getTrade_in_mileage() {
        return trade_in_mileage;
    }

    public void setTrade_in_expected_amt(String trade_in_expected_amt) {
        this.trade_in_expected_amt = trade_in_expected_amt;
    }

    public String getTrade_in_expected_amt() {
        return trade_in_expected_amt;
    }

}
