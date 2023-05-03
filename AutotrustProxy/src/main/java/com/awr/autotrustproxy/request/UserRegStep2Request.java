package com.awr.autotrustproxy.request;

public class UserRegStep2Request {
    public UserRegStep2Request() {
        super();
    }
    
    private String buId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String code;
    private String receiveOffers;
    private String facebookId;
    private String password;
//    private String source;

    public void setBuId(String buId) {
        this.buId = buId;
    }

    public String getBuId() {
        return buId;
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

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setReceiveOffers(String receiveOffers) {
        this.receiveOffers = receiveOffers;
    }

    public String getReceiveOffers() {
        return receiveOffers;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

//    public void setSource(String source) {
//        this.source = source;
//    }
//
//    public String getSource() {
//        return source;
//    }
}
