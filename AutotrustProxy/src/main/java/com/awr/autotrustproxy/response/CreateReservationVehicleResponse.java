package com.awr.autotrustproxy.response;

public class CreateReservationVehicleResponse {
    public CreateReservationVehicleResponse() {
        super();
    }
//    private String reservationId;
    private String enquiryId;
    private String retCode;
    private String retMsg;

//    public void setReservationId(String reservationId) {
//        this.reservationId = reservationId;
//    }
//
//    public String getReservationId() {
//        return reservationId;
//    }

    public void setEnquiryId(String enquiryId) {
        this.enquiryId = enquiryId;
    }

    public String getEnquiryId() {
        return enquiryId;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getRetMsg() {
        return retMsg;
    }
}
