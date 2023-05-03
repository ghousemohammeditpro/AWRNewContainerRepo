package com.awr.autotrustservice.request;

public class CreateSFLeadRequest {
    public CreateSFLeadRequest() {
        super();
    }
    private String appSourceName; //not null
    private String sourceCategory;  //not null
    private String leadAction;  //not null
    private String custType;  //not null
    private String title;
    private String fname;
    private String lname;
    private String fullName;
    private String email;
    private String mobile;
    private String street;
    private String city;
    private String state;
    private String country;
    private String poBox;
    private String brand;
    private String model;
    private String prefLocation; //branch code
    private String regNo;
    private String vinNo;
    private String existingCust;
    private String comments;
    private String latitude;
    private String longitude;
    private String attrCategory; //same as appSourceName
    private String attr1; //'Created by XYZ App'


    public void setAppSourceName(String appSourceName) {
        this.appSourceName = appSourceName;
    }

    public String getAppSourceName() {
        return appSourceName;
    }

    public void setSourceCategory(String sourceCategory) {
        this.sourceCategory = sourceCategory;
    }

    public String getSourceCategory() {
        return sourceCategory;
    }

    public void setLeadAction(String leadAction) {
        this.leadAction = leadAction;
    }

    public String getLeadAction() {
        return leadAction;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getCustType() {
        return custType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFname() {
        return fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLname() {
        return lname;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    public String getPoBox() {
        return poBox;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setPrefLocation(String prefLocation) {
        this.prefLocation = prefLocation;
    }

    public String getPrefLocation() {
        return prefLocation;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    public String getVinNo() {
        return vinNo;
    }

    public void setExistingCust(String existingCust) {
        this.existingCust = existingCust;
    }

    public String getExistingCust() {
        return existingCust;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setAttrCategory(String attrCategory) {
        this.attrCategory = attrCategory;
    }

    public String getAttrCategory() {
        return attrCategory;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr1() {
        return attr1;
    }
}
