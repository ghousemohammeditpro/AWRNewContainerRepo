package com.awr.autotrustservice.response;

import com.awr.autotrustservice.dto.TxnDetailsObject;

public class MyStatementResponse {
    public MyStatementResponse() {
        super();
    }
    private String retCode;
    private String retMsg;
    private TxnDetailsObject[] statement;

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

    public void setStatement(TxnDetailsObject[] statement) {
        this.statement = statement;
    }

    public TxnDetailsObject[] getStatement() {
        return statement;
    }
}
