package com.awr.autotrustproxy.dto;

public class ActiveServiceObject {
    public ActiveServiceObject() {
        super();
    }
    private String incidentNumber;
    private String location;
    private String advisorName;
    private String advisorContactNumber;
    private String appointmentDate;

    public void setIncidentNumber(String incidentNumber) {
        this.incidentNumber = incidentNumber;
    }

    public String getIncidentNumber() {
        return incidentNumber;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setAdvisorName(String advisorName) {
        this.advisorName = advisorName;
    }

    public String getAdvisorName() {
        return advisorName;
    }

    public void setAdvisorContactNumber(String advisorContactNumber) {
        this.advisorContactNumber = advisorContactNumber;
    }

    public String getAdvisorContactNumber() {
        return advisorContactNumber;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }
}
