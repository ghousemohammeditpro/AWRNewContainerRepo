package com.awr.autotrustservice.request;

public class InsertAddonRequest {
    public InsertAddonRequest() {
        super();
    }
    private String reservationNo;
//    private String orgId;
    private String[] extraFittingIds;

    public void setReservationNo(String reservationNo) {
        this.reservationNo = reservationNo;
    }

    public String getReservationNo() {
        return reservationNo;
    }
//
//    public void setOrgId(String orgId) {
//        this.orgId = orgId;
//    }
//
//    public String getOrgId() {
//        return orgId;
//    }

    public void setExtraFittingIds(String[] extraFittingIds) {
        this.extraFittingIds = extraFittingIds;
    }

    public String[] getExtraFittingIds() {
        return extraFittingIds;
    }
}
