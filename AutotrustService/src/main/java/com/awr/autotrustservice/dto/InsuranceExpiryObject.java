package com.awr.autotrustservice.dto;

public class InsuranceExpiryObject {
    public InsuranceExpiryObject() {
        super();
    }
    private String ExpiryDate;
    private boolean ShowClockWithAlert;

    public void setExpiryDate(String ExpiryDate) {
        this.ExpiryDate = ExpiryDate;
    }

    public String getExpiryDate() {
        return ExpiryDate;
    }

    public void setShowClockWithAlert(boolean ShowClockWithAlert) {
        this.ShowClockWithAlert = ShowClockWithAlert;
    }

    public boolean isShowClockWithAlert() {
        return ShowClockWithAlert;
    }
}
