package com.awr.autotrustproxy.response;

import com.awr.autotrustproxy.dto.LoginResponseDataObject;

public class LoginResponse {
    public LoginResponse() {
        super();
    }
    private String status;
    private String message;
    //    private String[] data;
//    private String data;
//    private String name;
//    private String points;
//    private String buId;
//    private LoginResponseDataObject dataObjects;
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
    private String mobile;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    //    public void setData(String[] data) {
    //        this.data = data;
    //    }
    //
    //    public String[] getData() {
    //        return data;
    //    }

//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setPoints(String points) {
//        this.points = points;
//    }
//
//    public String getPoints() {
//        return points;
//    }
//
//    public void setBuId(String buId) {
//        this.buId = buId;
//    }
//
//    public String getBuId() {
//        return buId;
//    }
//
//    public void setData(String data) {
//        this.data = data;
//    }
//
//    public String getData() {
//        return data;
//    }
//
//    public void setDataObjects(LoginResponseDataObject dataObjects) {
//        this.dataObjects = dataObjects;
//    }
//
//    public LoginResponseDataObject getDataObjects() {
//        return dataObjects;
//    }

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

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }
}
