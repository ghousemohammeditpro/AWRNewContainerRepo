package com.awr.autotrustproxy.request;

public class SaveSubscriptionRequest {
    public SaveSubscriptionRequest() {
        super();
    }
    private String buId;
    private String check;
    private String source;

    public void setBuId(String buId) {
        this.buId = buId;
    }

    public String getBuId() {
        return buId;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getCheck() {
        return check;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
}
