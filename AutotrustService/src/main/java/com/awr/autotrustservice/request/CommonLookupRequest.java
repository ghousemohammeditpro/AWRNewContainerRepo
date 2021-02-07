package com.awr.autotrustservice.request;

public class CommonLookupRequest {
    public CommonLookupRequest() {
        super();
    }
    private String lookupType;
    private String[] dependentVal;


    public void setLookupType(String lookupType) {
        this.lookupType = lookupType;
    }

    public String getLookupType() {
        return lookupType;
    }

    public void setDependentVal(String[] dependentVal) {
        this.dependentVal = dependentVal;
    }

    public String[] getDependentVal() {
        return dependentVal;
    }
}
