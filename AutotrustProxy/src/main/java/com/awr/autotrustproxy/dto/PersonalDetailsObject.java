package com.awr.autotrustproxy.dto;

public class PersonalDetailsObject {
    public PersonalDetailsObject() {
        super();
    }
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String profileImg;
    private String profileStatus;
    private String profileCompletion;
    private String fbookId;
    private String gender;
    private String nationality;
    private String make;
    private String model;
    private String city;
    private String area;
    private String position;
    private AddressObject billingAddress;
    private AddressObject shippingAddress;
    private boolean isTrxExists;
    private boolean isGarageNotificationChecked;
    private boolean showVerificationLink;
    private String dateOfBirth;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileStatus(String profileStatus) {
        this.profileStatus = profileStatus;
    }

    public String getProfileStatus() {
        return profileStatus;
    }

    public void setProfileCompletion(String profileCompletion) {
        this.profileCompletion = profileCompletion;
    }

    public String getProfileCompletion() {
        return profileCompletion;
    }

    public void setFbookId(String fbookId) {
        this.fbookId = fbookId;
    }

    public String getFbookId() {
        return fbookId;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getMake() {
        return make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setBillingAddress(AddressObject billingAddress) {
        this.billingAddress = billingAddress;
    }

    public AddressObject getBillingAddress() {
        return billingAddress;
    }

    public void setShippingAddress(AddressObject shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public AddressObject getShippingAddress() {
        return shippingAddress;
    }

    public void setIsTrxExists(boolean isTrxExists) {
        this.isTrxExists = isTrxExists;
    }

    public boolean isIsTrxExists() {
        return isTrxExists;
    }

    public void setIsGarageNotificationChecked(boolean isGarageNotificationChecked) {
        this.isGarageNotificationChecked = isGarageNotificationChecked;
    }

    public boolean isIsGarageNotificationChecked() {
        return isGarageNotificationChecked;
    }

    public void setShowVerificationLink(boolean showVerificationLink) {
        this.showVerificationLink = showVerificationLink;
    }

    public boolean isShowVerificationLink() {
        return showVerificationLink;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

}
