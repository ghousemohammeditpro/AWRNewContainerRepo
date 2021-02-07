package com.awr.autotrustproxy.response;

import com.awr.autotrustproxy.dto.IdashboardDataObject;

public class IdashboardResponse {
    public IdashboardResponse() {
        super();
    }
    private IdashboardDataObject[] data;
    private String retMsg;
    private String retCode;

    public void setData(IdashboardDataObject[] data) {
        this.data = data;
    }

    public IdashboardDataObject[] getData() {
        return data;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetCode() {
        return retCode;
    }
}
