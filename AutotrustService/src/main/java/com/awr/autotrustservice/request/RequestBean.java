package com.awr.autotrustservice.request;

public class RequestBean {
    public RequestBean() {
        super();
    }
    private String token;
    private String sendOn;
    private String applicationName;
    private String applicationVersion;
    private String deviceId;
    private String deviceType;
    private String userId;
    private String googleAPIKey;
    private String senderId;
    private String applicationId;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setSendOn(String sendOn) {
        this.sendOn = sendOn;
    }

    public String getSendOn() {
        return sendOn;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setGoogleAPIKey(String googleAPIKey) {
        this.googleAPIKey = googleAPIKey;
    }

    public String getGoogleAPIKey() {
        return googleAPIKey;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationId() {
        return applicationId;
    }
}
