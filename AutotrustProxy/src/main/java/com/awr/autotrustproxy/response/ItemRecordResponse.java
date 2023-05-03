package com.awr.autotrustproxy.response;

import com.awr.autotrustproxy.dto.ItemRecord;

public class ItemRecordResponse {
    public ItemRecordResponse() {
        super();
    }
    private String retCode;
    private String retMsg;
    private ItemRecord[] itemRecordList;

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

    public void setItemRecordList(ItemRecord[] itemRecordList) {
        this.itemRecordList = itemRecordList;
    }

    public ItemRecord[] getItemRecordList() {
        return itemRecordList;
    }
}
