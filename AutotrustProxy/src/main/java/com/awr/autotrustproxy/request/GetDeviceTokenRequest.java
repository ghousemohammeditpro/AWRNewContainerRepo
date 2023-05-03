package com.awr.autotrustproxy.request;

public class GetDeviceTokenRequest {
    public GetDeviceTokenRequest() {
        super();
    }
    private String applicationName;
    private String[] userId;
    private String deviceType;

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setUserId(String[] userId) {
        this.userId = userId;
    }

    public String[] getUserId() {
        return userId;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceType() {
        return deviceType;
    }
}
