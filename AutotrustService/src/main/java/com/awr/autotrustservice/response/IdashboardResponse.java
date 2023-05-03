package com.awr.autotrustservice.response;

import com.awr.autotrustservice.dto.IdashboardDataObject;

public class IdashboardResponse {
    public IdashboardResponse() {
        super();
    }
    private IdashboardDataObject[] data;

    public void setData(IdashboardDataObject[] data) {
        this.data = data;
    }

    public IdashboardDataObject[] getData() {
        return data;
    }
}
