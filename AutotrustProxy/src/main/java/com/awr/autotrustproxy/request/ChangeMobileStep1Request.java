package com.awr.autotrustproxy.request;

public class ChangeMobileStep1Request {
    public ChangeMobileStep1Request() {
        super();
    }
    private String buId;
    private String pswd;
//    private String source;

    public void setBuId(String buId) {
        this.buId = buId;
    }

    public String getBuId() {
        return buId;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getPswd() {
        return pswd;
    }

//    public void setSource(String source) {
//        this.source = source;
//    }
//
//    public String getSource() {
//        return source;
//    }
}
