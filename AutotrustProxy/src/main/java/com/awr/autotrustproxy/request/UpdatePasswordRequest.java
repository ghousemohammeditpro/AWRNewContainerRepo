package com.awr.autotrustproxy.request;

public class UpdatePasswordRequest {
    public UpdatePasswordRequest() {
        super();
    }
    private String buId;
    private String oldPswd;
    private String newPswd;
//    private String source;

    public void setBuId(String buId) {
        this.buId = buId;
    }

    public String getBuId() {
        return buId;
    }

    public void setOldPswd(String oldPswd) {
        this.oldPswd = oldPswd;
    }

    public String getOldPswd() {
        return oldPswd;
    }

    public void setNewPswd(String newPswd) {
        this.newPswd = newPswd;
    }

    public String getNewPswd() {
        return newPswd;
    }

//    public void setSource(String source) {
//        this.source = source;
//    }
//
//    public String getSource() {
//        return source;
//    }
}
