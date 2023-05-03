package com.awr.autotrustproxy.request;

public class LoginRequest {
    public LoginRequest() {
        super();
    }
    private String username;
    private String password;
    private String isFBLogin;
//    private String source;


    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setIsFBLogin(String isFBLogin) {
        this.isFBLogin = isFBLogin;
    }

    public String getIsFBLogin() {
        return isFBLogin;
    }

//    public void setSource(String source) {
//        this.source = source;
//    }
//
//    public String getSource() {
//        return source;
//    }
}
