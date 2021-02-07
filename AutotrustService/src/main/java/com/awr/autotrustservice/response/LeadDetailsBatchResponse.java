package com.awr.autotrustservice.response;

import com.awr.autotrustservice.dto.LeadDetailsBatch;

import java.util.List;

public class LeadDetailsBatchResponse {
    public LeadDetailsBatchResponse() {
        super();
    }
    private String return_status;
    private String return_message;
    private LeadDetailsBatch[] LeadsDetailsBatch;


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

    public void setLeadsDetailsBatch(LeadDetailsBatch[] LeadsDetailsBatch) {
        this.LeadsDetailsBatch = LeadsDetailsBatch;
    }

    public LeadDetailsBatch[] getLeadsDetailsBatch() {
        return LeadsDetailsBatch;
    }

}
