package com.awr.autotrustservice.dto;

public class Lookup {
    public Lookup() {
        super();
    }
    
    private String lookupCode;
    private String lookupMeaning;


    public void setLookupCode(String lookupCode) {
        this.lookupCode = lookupCode;
    }

    public String getLookupCode() {
        return lookupCode;
    }

    public void setLookupMeaning(String lookupMeaning) {
        this.lookupMeaning = lookupMeaning;
    }

    public String getLookupMeaning() {
        return lookupMeaning;
    }
}
