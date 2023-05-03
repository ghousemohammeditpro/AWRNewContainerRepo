package com.awr.autotrustproxy.dto;

public class GarageVehicleObject {
    public GarageVehicleObject() {
        super();
    }
    
    private String carId;
    private String carTitle;
    private String ownedSince;
    private String registrationNbr;
    private String VIN;
    private String carImage;
    private String brand;
    private String model;
    private String year;
    private String organizationId;
    private String lastMileage;
    private String accessoriesLink;
    private String tiresLink;
    private InsuranceExpiryObject insuranceExpiry;
    private LastServiceObject lastService;
    private CarImportedFromAWRObject carImportedFromAWR;

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarTitle(String carTitle) {
        this.carTitle = carTitle;
    }

    public String getCarTitle() {
        return carTitle;
    }

    public void setOwnedSince(String ownedSince) {
        this.ownedSince = ownedSince;
    }

    public String getOwnedSince() {
        return ownedSince;
    }

    public void setRegistrationNbr(String registrationNbr) {
        this.registrationNbr = registrationNbr;
    }

    public String getRegistrationNbr() {
        return registrationNbr;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getVIN() {
        return VIN;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }

    public String getCarImage() {
        return carImage;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setLastMileage(String lastMileage) {
        this.lastMileage = lastMileage;
    }

    public String getLastMileage() {
        return lastMileage;
    }

    public void setAccessoriesLink(String accessoriesLink) {
        this.accessoriesLink = accessoriesLink;
    }

    public String getAccessoriesLink() {
        return accessoriesLink;
    }

    public void setTiresLink(String tiresLink) {
        this.tiresLink = tiresLink;
    }

    public String getTiresLink() {
        return tiresLink;
    }

    public void setInsuranceExpiry(InsuranceExpiryObject insuranceExpiry) {
        this.insuranceExpiry = insuranceExpiry;
    }

    public InsuranceExpiryObject getInsuranceExpiry() {
        return insuranceExpiry;
    }

    public void setLastService(LastServiceObject lastService) {
        this.lastService = lastService;
    }

    public LastServiceObject getLastService() {
        return lastService;
    }

    public void setCarImportedFromAWR(CarImportedFromAWRObject carImportedFromAWR) {
        this.carImportedFromAWR = carImportedFromAWR;
    }

    public CarImportedFromAWRObject getCarImportedFromAWR() {
        return carImportedFromAWR;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }
}
