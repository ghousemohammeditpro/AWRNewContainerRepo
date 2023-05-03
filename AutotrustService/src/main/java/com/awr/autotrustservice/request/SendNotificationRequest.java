package com.awr.autotrustservice.request;

public class SendNotificationRequest {
    public SendNotificationRequest() {
        super();
    }
    private String applicationName;
    private String deviceType;
    private String userIDs;
    private String notificationMessage;

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setUserIDs(String userIDs) {
        this.userIDs = userIDs;
    }

    public String getUserIDs() {
        return userIDs;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }
}
