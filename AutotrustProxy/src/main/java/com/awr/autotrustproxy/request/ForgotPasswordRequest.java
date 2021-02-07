package com.awr.autotrustproxy.request;

public class ForgotPasswordRequest {
    public ForgotPasswordRequest() {
        super();
    }
    private String emailMobile;
//    private String source;

    public void setEmailMobile(String emailMobile) {
        this.emailMobile = emailMobile;
    }

    public String getEmailMobile() {
        return emailMobile;
    }

//    public void setSource(String source) {
//        this.source = source;
//    }
//
//    public String getSource() {
//        return source;
//    }
}
