package com.awr.autotrustproxy.dto;

public class DeviceObject {
    public DeviceObject() {
        super();
    }
    private String tokenId;
    private String deviceType;
    private String userId;

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getTokenId() {
        return tokenId;
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

}
