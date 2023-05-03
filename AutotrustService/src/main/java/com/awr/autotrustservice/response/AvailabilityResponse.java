package com.awr.autotrustservice.response;

import com.awr.autotrustservice.dto.Availability;

public class AvailabilityResponse {
    public AvailabilityResponse() {
        super();
    }
    private String available;
    private Availability[] slots;
    private String retCode;
    private String retMsg;

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getAvailable() {
        return available;
    }

    public void setSlots(Availability[] slots) {
        this.slots = slots;
    }

    public Availability[] getSlots() {
        return slots;
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
