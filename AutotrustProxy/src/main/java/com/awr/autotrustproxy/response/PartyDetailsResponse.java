package com.awr.autotrustproxy.response;

import com.awr.autotrustproxy.dto.PartyDetailsObject;

public class PartyDetailsResponse {
    public PartyDetailsResponse() {
        super();
    }
    private String retCode;
    private String retMsg;
    private PartyDetailsObject[] partyDetailsList;

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

    public void setPartyDetailsList(PartyDetailsObject[] partyDetailsList) {
        this.partyDetailsList = partyDetailsList;
    }

    public PartyDetailsObject[] getPartyDetailsList() {
        return partyDetailsList;
    }
}
