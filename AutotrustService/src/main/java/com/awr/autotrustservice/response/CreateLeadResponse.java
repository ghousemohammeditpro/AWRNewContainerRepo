package com.awr.autotrustservice.response;

public class CreateLeadResponse {
    public CreateLeadResponse() {
        super();
    }
    private String reference_number;
    private String return_status;
    private String return_message;

    public void setReference_number(String reference_number) {
        this.reference_number = reference_number;
    }

    public String getReference_number() {
        return reference_number;
    }

    public void setReturn_status(String return_status) {
        this.return_status = return_status;
    }

    public String getReturn_status() {
        return return_status;
    }

    public void setReturn_message(String return_message) {
        this.return_message = return_message;
    }

    public String getReturn_message() {
        return return_message;
    }
}
