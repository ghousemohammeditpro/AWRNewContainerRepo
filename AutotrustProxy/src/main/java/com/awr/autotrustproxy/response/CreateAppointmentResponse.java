package com.awr.autotrustproxy.response;

public class CreateAppointmentResponse {
    public CreateAppointmentResponse() {
        super();
    }
    private String incidentId;
    private String incidentNumber;
    private String serviceAdvisorId;
    private String serviceAdvisorName;
    private String serviceAdvisorContactNumber;
    private String retCode;
    private String retMsg;

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentNumber(String incidentNumber) {
        this.incidentNumber = incidentNumber;
    }

    public String getIncidentNumber() {
        return incidentNumber;
    }

    public void setServiceAdvisorId(String serviceAdvisorId) {
        this.serviceAdvisorId = serviceAdvisorId;
    }

    public String getServiceAdvisorId() {
        return serviceAdvisorId;
    }

    public void setServiceAdvisorName(String serviceAdvisorName) {
        this.serviceAdvisorName = serviceAdvisorName;
    }

    public String getServiceAdvisorName() {
        return serviceAdvisorName;
    }

    public void setServiceAdvisorContactNumber(String serviceAdvisorContactNumber) {
        this.serviceAdvisorContactNumber = serviceAdvisorContactNumber;
    }

    public String getServiceAdvisorContactNumber() {
        return serviceAdvisorContactNumber;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getRetMsg() {
        return retMsg;
    }
}
