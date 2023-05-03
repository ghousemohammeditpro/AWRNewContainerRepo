package com.awr.autotrustproxy.response;

import com.awr.autotrustproxy.dto.TransactionObject;

public class SalesOrdersResponse {
    public SalesOrdersResponse() {
        super();
    }
    private String retCode;
    private String retMsg;
    private TransactionObject[] salesOrders;

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

    public void setSalesOrders(TransactionObject[] salesOrders) {
        this.salesOrders = salesOrders;
    }

    public TransactionObject[] getSalesOrders() {
        return salesOrders;
    }
}
