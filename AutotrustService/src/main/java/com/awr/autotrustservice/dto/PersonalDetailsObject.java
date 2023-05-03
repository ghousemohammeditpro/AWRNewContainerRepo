package com.awr.autotrustservice.dto;

public class PersonalDetailsObject {
    public PersonalDetailsObject() {
        super();
    }
    private String FirstName;
    private String LastName;
    private String Mobile;
    private String Email;
    private String ProfileImg;
    private String ProfileStatus;
    private String ProfileCompletion;
    private String FbookId;
    private String Gender;
    private String Nationality;
    private String Make;
    private String Model;
    private String City;
    private String Area;
    private String Position;
    private AddressObject billingAddress;
    private AddressObject shippingAddress;
    private boolean IsTrxExists;
    private boolean IsGarageNotificationChecked;
    private boolean ShowVerificationLink;
    private String DateOfBirth;

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getEmail() {
        return Email;
    }

    public void setProfileImg(String ProfileImg) {
        this.ProfileImg = ProfileImg;
    }

    public String getProfileImg() {
        return ProfileImg;
    }

    public void setProfileStatus(String ProfileStatus) {
        this.ProfileStatus = ProfileStatus;
    }

    public String getProfileStatus() {
        return ProfileStatus;
    }

    public void setProfileCompletion(String ProfileCompletion) {
        this.ProfileCompletion = ProfileCompletion;
    }

    public String getProfileCompletion() {
        return ProfileCompletion;
    }

    public void setFbookId(String FbookId) {
        this.FbookId = FbookId;
    }

    public String getFbookId() {
        return FbookId;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getGender() {
        return Gender;
    }

    public void setNationality(String Nationality) {
        this.Nationality = Nationality;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setMake(String Make) {
        this.Make = Make;
    }

    public String getMake() {
        return Make;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getModel() {
        return Model;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getCity() {
        return City;
    }

    public void setArea(String Area) {
        this.Area = Area;
    }

    public String getArea() {
        return Area;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public String getPosition() {
        return Position;
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

    public void setIsTrxExists(boolean IsTrxExists) {
        this.IsTrxExists = IsTrxExists;
    }

    public boolean isIsTrxExists() {
        return IsTrxExists;
    }

    public void setIsGarageNotificationChecked(boolean IsGarageNotificationChecked) {
        this.IsGarageNotificationChecked = IsGarageNotificationChecked;
    }

    public boolean isIsGarageNotificationChecked() {
        return IsGarageNotificationChecked;
    }

    public void setShowVerificationLink(boolean ShowVerificationLink) {
        this.ShowVerificationLink = ShowVerificationLink;
    }

    public boolean isShowVerificationLink() {
        return ShowVerificationLink;
    }

    public void setDateOfBirth(String DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }
}
