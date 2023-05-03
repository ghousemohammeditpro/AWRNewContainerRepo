package com.awr.autotrustservice.request;

public class UserRegStep1Request {
    public UserRegStep1Request() {
        super();
    }
    private String fName;
    private String lName;
    private String email;
    private String mobile;
    private String code;
    private String inv;
    private String by;
    private String receiveOffers;
    private String FBId;
//    private String source;

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getFName() {
        return fName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getLName() {
        return lName;
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

    public void setInv(String inv) {
        this.inv = inv;
    }

    public String getInv() {
        return inv;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setReceiveOffers(String receiveOffers) {
        this.receiveOffers = receiveOffers;
    }

    public String getReceiveOffers() {
        return receiveOffers;
    }

    public void setFBId(String FBId) {
        this.FBId = FBId;
    }

    public String getFBId() {
        return FBId;
    }

//    public void setSource(String source) {
//        this.source = source;
//    }
//
//    public String getSource() {
//        return source;
//    }
}
