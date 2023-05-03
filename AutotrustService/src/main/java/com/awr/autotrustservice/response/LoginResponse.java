package com.awr.autotrustservice.response;

import com.awr.autotrustservice.dto.LoginResponseDataObject;

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
    private String Mobile;


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

//    public void setDataObjects(LoginResponseDataObject dataObjects) {
//        this.dataObjects = dataObjects;
//    }
//
//    public LoginResponseDataObject getDataObjects() {
//        return dataObjects;
//    }

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

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getMobile() {
        return Mobile;
    }
}
