package com.awr.autotrustproxy.request;

public class CreateAppointmentRequest {
    public CreateAppointmentRequest() {
        super();
    }
    private String instanceId;
    private String contractId;
    private String incidentTypeId;
    private String resourceId;
    private String groupId;
    private String appointmentDate;
    private String comments;
    private String rentalCar;
    private String customerWaiting;
    private String context;
    private String sendReminder;
    private String currentMileage;
    private String prefServiceAdvisorFlag;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    private String servicePackages;

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setIncidentTypeId(String incidentTypeId) {
        this.incidentTypeId = incidentTypeId;
    }

    public String getIncidentTypeId() {
        return incidentTypeId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public void setRentalCar(String rentalCar) {
        this.rentalCar = rentalCar;
    }

    public String getRentalCar() {
        return rentalCar;
    }

    public void setCustomerWaiting(String customerWaiting) {
        this.customerWaiting = customerWaiting;
    }

    public String getCustomerWaiting() {
        return customerWaiting;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    public void setSendReminder(String sendReminder) {
        this.sendReminder = sendReminder;
    }

    public String getSendReminder() {
        return sendReminder;
    }

    public void setCurrentMileage(String currentMileage) {
        this.currentMileage = currentMileage;
    }

    public String getCurrentMileage() {
        return currentMileage;
    }

    public void setPrefServiceAdvisorFlag(String prefServiceAdvisorFlag) {
        this.prefServiceAdvisorFlag = prefServiceAdvisorFlag;
    }

    public String getPrefServiceAdvisorFlag() {
        return prefServiceAdvisorFlag;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setServicePackages(String servicePackages) {
        this.servicePackages = servicePackages;
    }

    public String getServicePackages() {
        return servicePackages;
    }
}
