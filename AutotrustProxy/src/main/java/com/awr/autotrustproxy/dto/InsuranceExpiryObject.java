package com.awr.autotrustproxy.dto;

public class InsuranceExpiryObject {
    public InsuranceExpiryObject() {
        super();
    }
    private String expiryDate;
    private boolean showClockWithAlert;

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setShowClockWithAlert(boolean showClockWithAlert) {
        this.showClockWithAlert = showClockWithAlert;
    }

    public boolean isShowClockWithAlert() {
        return showClockWithAlert;
    }
}
