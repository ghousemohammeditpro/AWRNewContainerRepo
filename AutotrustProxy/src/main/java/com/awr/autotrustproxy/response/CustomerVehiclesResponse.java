package com.awr.autotrustproxy.response;

import com.awr.autotrustproxy.dto.CustomerVehicle;

public class CustomerVehiclesResponse {
    public CustomerVehiclesResponse() {
        super();
    }
    private String retStatus;
    private String retMsg;
    private String attribute6;
    private String attribute7;
    private String attribute8;
    private String attribute9;
    private String attribute10;
    private CustomerVehicle[] customerVehicles;


    public void setRetStatus(String retStatus) {
        this.retStatus = retStatus;
    }

    public String getRetStatus() {
        return retStatus;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    public String getAttribute7() {
        return attribute7;
    }

    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }

    public String getAttribute8() {
        return attribute8;
    }

    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }

    public String getAttribute9() {
        return attribute9;
    }

    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
    }

    public String getAttribute10() {
        return attribute10;
    }

    public void setCustomerVehicles(CustomerVehicle[] customerVehicles) {
        this.customerVehicles = customerVehicles;
    }

    public CustomerVehicle[] getCustomerVehicles() {
        return customerVehicles;
    }
}
