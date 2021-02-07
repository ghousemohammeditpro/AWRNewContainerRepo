package com.awr.autotrustproxy.request;

public class MyStatementRequest {
    public MyStatementRequest() {
        super();
    }
    
    private String buId;
    private String fromDate;
    private String toDate;

    public void setBuId(String buId) {
        this.buId = buId;
    }

    public String getBuId() {
        return buId;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getToDate() {
        return toDate;
    }
}
