package com.awr.autotrustproxy.dto;

public class OrgMap {
    public OrgMap() {
        super();
    }
    private String orgActCode;
    private String personnelID;
    private String personnelCode;
    private String name;
    private String personnelType;
    private String designation;

    public void setOrgActCode(String orgActCode) {
        this.orgActCode = orgActCode;
    }

    public String getOrgActCode() {
        return orgActCode;
    }

    public void setPersonnelID(String personnelID) {
        this.personnelID = personnelID;
    }

    public String getPersonnelID() {
        return personnelID;
    }

    public void setPersonnelCode(String personnelCode) {
        this.personnelCode = personnelCode;
    }

    public String getPersonnelCode() {
        return personnelCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPersonnelType(String personnelType) {
        this.personnelType = personnelType;
    }

    public String getPersonnelType() {
        return personnelType;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }
}
