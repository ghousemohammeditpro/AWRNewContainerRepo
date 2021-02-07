package com.awr.autotrustservice.response;

import com.awr.autotrustservice.dto.StockObject;

public class StockResponse {
    public StockResponse() {
        super();
    }
    private String retCode;
    private String retMsg;
    private StockObject[] stock;

    public void setStock(StockObject[] stock) {
        this.stock = stock;
    }

    public StockObject[] getStock() {
        return stock;
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
