package com.awr.autotrustproxy.request;

public class SendNotificationRequestKentico {
    public SendNotificationRequestKentico() {
        super();
    }
    private String ApplicationName;
    private String DeviceType;
    private String UserIDs;
    private String NotificationMessage;

    public void setApplicationName(String ApplicationName) {
        this.ApplicationName = ApplicationName;
    }

    public String getApplicationName() {
        return ApplicationName;
    }

    public void setDeviceType(String DeviceType) {
        this.DeviceType = DeviceType;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setUserIDs(String UserIDs) {
        this.UserIDs = UserIDs;
    }

    public String getUserIDs() {
        return UserIDs;
    }

    public void setNotificationMessage(String NotificationMessage) {
        this.NotificationMessage = NotificationMessage;
    }

    public String getNotificationMessage() {
        return NotificationMessage;
    }
}
