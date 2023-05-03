package com.awr.autotrustservice.response;

public class CreateRetailCustomerResponse {
    public CreateRetailCustomerResponse() {
        super();
    }
    private String retCode;
    private String retMsg;
    private String partyId;

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

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getPartyId() {
        return partyId;
    }
}
