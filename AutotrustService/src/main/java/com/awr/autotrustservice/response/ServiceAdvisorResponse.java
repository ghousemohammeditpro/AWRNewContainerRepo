package com.awr.autotrustservice.response;

import com.awr.autotrustservice.dto.ServiceAdvisorObject;

public class ServiceAdvisorResponse {
    public ServiceAdvisorResponse() {
        super();
    }
    private ServiceAdvisorObject[] serviceAdvisors;
    private String retCode;
    private String retMsg;

    public void setServiceAdvisors(ServiceAdvisorObject[] serviceAdvisors) {
        this.serviceAdvisors = serviceAdvisors;
    }

    public ServiceAdvisorObject[] getServiceAdvisors() {
        return serviceAdvisors;
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
