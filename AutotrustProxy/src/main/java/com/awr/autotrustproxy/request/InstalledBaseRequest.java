package com.awr.autotrustproxy.request;

import com.awr.autotrustproxy.dto.InstalledBase;

public class InstalledBaseRequest {
    public InstalledBaseRequest() {
        super();
    }
    private String instanceID;
    private String userID;
    private String serviceAdvisorID;
    
    //For Create and Update Request
    private String devicID;
    private InstalledBase[] installedBaseList;

    public void setInstanceID(String instanceID) {
        this.instanceID = instanceID;
    }

    public String getInstanceID() {
        return instanceID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setServiceAdvisorID(String serviceAdvisorID) {
        this.serviceAdvisorID = serviceAdvisorID;
    }

    public String getServiceAdvisorID() {
        return serviceAdvisorID;
    }

    public void setDevicID(String devicID) {
        this.devicID = devicID;
    }

    public String getDevicID() {
        return devicID;
    }

    public void setInstalledBaseList(InstalledBase[] installedBaseList) {
        this.installedBaseList = installedBaseList;
    }

    public InstalledBase[] getInstalledBaseList() {
        return installedBaseList;
    }
}
