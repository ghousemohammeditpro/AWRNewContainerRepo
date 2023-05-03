package com.awr.autotrustproxy.response;

import com.awr.autotrustproxy.dto.BranchHolidaysObject;
import com.awr.autotrustproxy.dto.BranchTimeObject;
import com.awr.autotrustproxy.dto.IncidentObject;
import com.awr.autotrustproxy.dto.LocationObject;

public class BranchesResponse {
    public BranchesResponse() {
        super();
    }
    private String output1;
    private String output2;
    private String output3;
    private String output4;
    private String output5;
    private String retCode;
    private String retMsg;
    private IncidentObject[] incidents;
    private LocationObject[] locations;
    private BranchTimeObject[] branchTimings;
    private BranchHolidaysObject[] branchHolidays;

    public void setOutput1(String output1) {
        this.output1 = output1;
    }

    public String getOutput1() {
        return output1;
    }

    public void setOutput2(String output2) {
        this.output2 = output2;
    }

    public String getOutput2() {
        return output2;
    }

    public void setOutput3(String output3) {
        this.output3 = output3;
    }

    public String getOutput3() {
        return output3;
    }

    public void setOutput4(String output4) {
        this.output4 = output4;
    }

    public String getOutput4() {
        return output4;
    }

    public void setOutput5(String output5) {
        this.output5 = output5;
    }

    public String getOutput5() {
        return output5;
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

    public void setIncidents(IncidentObject[] incidents) {
        this.incidents = incidents;
    }

    public IncidentObject[] getIncidents() {
        return incidents;
    }

    public void setLocations(LocationObject[] locations) {
        this.locations = locations;
    }

    public LocationObject[] getLocations() {
        return locations;
    }

    public void setBranchTimings(BranchTimeObject[] branchTimings) {
        this.branchTimings = branchTimings;
    }

    public BranchTimeObject[] getBranchTimings() {
        return branchTimings;
    }

    public void setBranchHolidays(BranchHolidaysObject[] branchHolidays) {
        this.branchHolidays = branchHolidays;
    }

    public BranchHolidaysObject[] getBranchHolidays() {
        return branchHolidays;
    }
}
