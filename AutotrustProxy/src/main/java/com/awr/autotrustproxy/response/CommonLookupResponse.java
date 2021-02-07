package com.awr.autotrustproxy.response;

import com.awr.autotrustproxy.dto.Lookup;

public class CommonLookupResponse {
    public CommonLookupResponse() {
        super();
    }
    private Lookup[] commonLookup;
    private String retcode;
    private String retMsg;

    public void setCommonLookup(Lookup[] commonLookup) {
        this.commonLookup = commonLookup;
    }

    public Lookup[] getCommonLookup() {
        return commonLookup;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getRetcode() {
        return retcode;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getRetMsg() {
        return retMsg;
    }
}
