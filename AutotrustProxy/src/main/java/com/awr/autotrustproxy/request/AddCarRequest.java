package com.awr.autotrustproxy.request;

public class AddCarRequest {
    public AddCarRequest() {
        super();
    }
    private String buId;
    private String plateSource;
    private String plateCode;
    private String plateNbr;
    private String vinNumber;
    private String year;
    private String make;
    private String model;
    private String ownedSince;
    private String insuranceDate;
    private String serviceDate;
//    private String source;

    public void setBuId(String buId) {
        this.buId = buId;
    }

    public String getBuId() {
        return buId;
    }

    public void setPlateSource(String plateSource) {
        this.plateSource = plateSource;
    }

    public String getPlateSource() {
        return plateSource;
    }

    public void setPlateCode(String plateCode) {
        this.plateCode = plateCode;
    }

    public String getPlateCode() {
        return plateCode;
    }

    public void setPlateNbr(String plateNbr) {
        this.plateNbr = plateNbr;
    }

    public String getPlateNbr() {
        return plateNbr;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getMake() {
        return make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setOwnedSince(String ownedSince) {
        this.ownedSince = ownedSince;
    }

    public String getOwnedSince() {
        return ownedSince;
    }

    public void setInsuranceDate(String insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public String getInsuranceDate() {
        return insuranceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getServiceDate() {
        return serviceDate;
    }

//    public void setSource(String source) {
//        this.source = source;
//    }
//
//    public String getSource() {
//        return source;
//    }
}
