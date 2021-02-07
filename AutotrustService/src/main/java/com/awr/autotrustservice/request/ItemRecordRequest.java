package com.awr.autotrustservice.request;

public class ItemRecordRequest {
    public ItemRecordRequest() {
        super();
    }
    
    private String organizationID;
    private String inventoryItemID;
    private String itemType;
    private String model;
    private String color;
    private String trim;
    private String year;
    private String itemCode;
    private String userID;
    private String serviceAdvisorID;

    public void setOrganizationID(String organizationID) {
        this.organizationID = organizationID;
    }

    public String getOrganizationID() {
        return organizationID;
    }

    public void setInventoryItemID(String inventoryItemID) {
        this.inventoryItemID = inventoryItemID;
    }

    public String getInventoryItemID() {
        return inventoryItemID;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemType() {
        return itemType;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public String getTrim() {
        return trim;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemCode() {
        return itemCode;
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
}
