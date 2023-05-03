package com.awr.autotrustproxy.dto;

public class IdashboardDataObject {
    public IdashboardDataObject() {
        super();
    }
    private String actual;
    private String target;
    private String period;
    private String description;
    private String vehSalesValue;
    private String model;
    private String year;
    private String month;
    private String monthNum;

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getActual() {
        return actual;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setVehSalesValue(String vehSalesValue) {
        this.vehSalesValue = vehSalesValue;
    }

    public String getVehSalesValue() {
        return vehSalesValue;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMonth() {
        return month;
    }

    public void setMonthNum(String monthNum) {
        this.monthNum = monthNum;
    }

    public String getMonthNum() {
        return monthNum;
    }
}
