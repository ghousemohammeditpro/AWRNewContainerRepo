package com.awr.autotrustservice.dto;

import com.google.gson.annotations.SerializedName;

public class CustomerContact {
    public CustomerContact() {
        super();
    }
    private String contactID;
    private String custID;
    private String title;
    private String firstName;
    private String lastName;
    private String designation;
    private String telCtryCode;
    private String telAreaCode;
    private String telNumber;
    private String email;
    private String address;
    private String poBox;
    private String cityCode;
    private String createdAt;
    private String createdBy;
    private String lastUpdatedAt;
    private String lastUpdatedBy;
    private String contactLat;
    private String contactLong;
    private String telContactPointId;
    private String emailContactPointId;

    public String getContactID() {
        return contactID;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getTelCtryCode() {
        return telCtryCode;
    }

    public void setTelCtryCode(String telCtryCode) {
        this.telCtryCode = telCtryCode;
    }

    public String getTelAreaCode() {
        return telAreaCode;
    }

    public void setTelAreaCode(String telAreaCode) {
        this.telAreaCode = telAreaCode;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPoBox() {
        return poBox;
    }

    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(String lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getContactLat() {
        return contactLat;
    }

    public void setContactLat(String contactLat) {
        this.contactLat = contactLat;
    }

    public String getContactLong() {
        return contactLong;
    }

    public void setContactLong(String contactLong) {
        this.contactLong = contactLong;
    }

    public String getTelContactPointId() {
        return telContactPointId;
    }

    public void setTelContactPointId(String telContactPointId) {
        this.telContactPointId = telContactPointId;
    }

    public String getEmailContactPointId() {
        return emailContactPointId;
    }

    public void setEmailContactPointId(String emailContactPointId) {
        this.emailContactPointId = emailContactPointId;
    }
}
