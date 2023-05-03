package com.awr.autotrustservice.request;

import com.awr.autotrustservice.dto.AddonObject;


public class SubmitAddonRequest {
    public SubmitAddonRequest() {
        super();
    }
//    private String reservationNumber;
    private AddonObject[] addons;

//    public void setReservationNumber(String reservationNumber) {
//        this.reservationNumber = reservationNumber;
//    }
//
//    public String getReservationNumber() {
//        return reservationNumber;
//    }

    public void setAddons(AddonObject[] addons) {
        this.addons = addons;
    }

    public AddonObject[] getAddons() {
        return addons;
    }
}
