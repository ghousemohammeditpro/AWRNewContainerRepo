package com.awr.autotrustservice.dto;

public class LoginResponseDataObject {
    public LoginResponseDataObject() {
        super();
    }
    private String BuId;
    private String Username;
    private String FirstName;
    private String LastName;
    private String FullName;
    private String Email;
    private String Image;
    private String Points;
    private String Gender;
    private String DateOfBirth;

    public void setBuId(String BuId) {
        this.BuId = BuId;
    }

    public String getBuId() {
        return BuId;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getUsername() {
        return Username;
    }

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

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getEmail() {
        return Email;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getImage() {
        return Image;
    }

    public void setPoints(String Points) {
        this.Points = Points;
    }

    public String getPoints() {
        return Points;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getGender() {
        return Gender;
    }

    public void setDateOfBirth(String DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }
}
