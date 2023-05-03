package com.awr.autotrustproxy.response;

public class GetReservationNumberResponse {
    public GetReservationNumberResponse() {
        super();
    }
    private String reservationNumber;
    private String reservationStatus;
    private String retCode;
    private String retMsg;

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public String getReservationStatus() {
        return reservationStatus;
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
