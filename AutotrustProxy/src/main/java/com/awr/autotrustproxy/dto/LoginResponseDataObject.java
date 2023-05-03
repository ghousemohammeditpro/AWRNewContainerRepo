package com.awr.autotrustproxy.dto;

public class LoginResponseDataObject {
    public LoginResponseDataObject() {
        super();
    }
    private String buId;
    private String username;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String image;
    private String points;
    private String gender;
    private String dateOfBirth;


    public void setBuId(String buId) {
        this.buId = buId;
    }

    public String getBuId() {
        return buId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

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

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getPoints() {
        return points;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
