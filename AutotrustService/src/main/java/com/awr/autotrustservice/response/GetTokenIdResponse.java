package com.awr.autotrustservice.response;

import com.awr.autotrustservice.dto.DeviceObject;

public class GetTokenIdResponse {
    public GetTokenIdResponse() {
        super();
    }
    private DeviceObject[] deviceList;
    private String retCode;
    private String retMsg;

    public void setDeviceList(DeviceObject[] deviceList) {
        this.deviceList = deviceList;
    }

    public DeviceObject[] getDeviceList() {
        return deviceList;
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
