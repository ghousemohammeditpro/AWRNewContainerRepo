package com.awr.autotrustservice.request;

public class ServiceAdvisorRequest {
    public ServiceAdvisorRequest() {
        super();
    }
    private String locationCode;
    private String groupId;
    private String orgId;
    private String customerWaiting;
    private String serviceAdvisorQuota;
    private String serviceDateTime;

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setCustomerWaiting(String customerWaiting) {
        this.customerWaiting = customerWaiting;
    }

    public String getCustomerWaiting() {
        return customerWaiting;
    }

    public void setServiceAdvisorQuota(String serviceAdvisorQuota) {
        this.serviceAdvisorQuota = serviceAdvisorQuota;
    }

    public String getServiceAdvisorQuota() {
        return serviceAdvisorQuota;
    }

    public void setServiceDateTime(String serviceDateTime) {
        this.serviceDateTime = serviceDateTime;
    }

    public String getServiceDateTime() {
        return serviceDateTime;
    }
}
