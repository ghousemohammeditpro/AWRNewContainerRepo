package com.awr.autotrustservice.dto;

public class ActiveServiceObject {
    public ActiveServiceObject() {
        super();
    }
    private String IncidentNumber;
    private String Location;
    private String AdvisorName;
    private String AdvisorContactNumber;
    private String AppointmentDate;

    public void setIncidentNumber(String IncidentNumber) {
        this.IncidentNumber = IncidentNumber;
    }

    public String getIncidentNumber() {
        return IncidentNumber;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getLocation() {
        return Location;
    }

    public void setAdvisorName(String AdvisorName) {
        this.AdvisorName = AdvisorName;
    }

    public String getAdvisorName() {
        return AdvisorName;
    }

    public void setAdvisorContactNumber(String AdvisorContactNumber) {
        this.AdvisorContactNumber = AdvisorContactNumber;
    }

    public String getAdvisorContactNumber() {
        return AdvisorContactNumber;
    }

    public void setAppointmentDate(String AppointmentDate) {
        this.AppointmentDate = AppointmentDate;
    }

    public String getAppointmentDate() {
        return AppointmentDate;
    }
}
