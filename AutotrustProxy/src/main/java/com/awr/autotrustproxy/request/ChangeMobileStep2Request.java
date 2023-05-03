package com.awr.autotrustproxy.request;

public class ChangeMobileStep2Request {
    public ChangeMobileStep2Request() {
        super();
    }
    private String buId;
    private String mobile;
//    private String source;

    public void setBuId(String buId) {
        this.buId = buId;
    }

    public String getBuId() {
        return buId;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

//    public void setSource(String source) {
//        this.source = source;
//    }
//
//    public String getSource() {
//        return source;
//    }
}
