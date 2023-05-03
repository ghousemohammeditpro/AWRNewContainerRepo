package com.awr.autotrustservice.dto;

public class GarageVehicleObject {
    public GarageVehicleObject() {
        super();
    }
    
    private String CarId;
    private String CarTitle;
    private String OwnedSince;
    private String RegistrationNbr;
    private String VIN;
    private String CarImage;
    private String Brand;
    private String Model;
    private String Year;
    private String OrganizationId;
    private String LastMileage;
    private String AccessoriesLink;
    private String TiresLink;
    private InsuranceExpiryObject InsuranceExpiry;
    private LastServiceObject LastService;
    private CarImportedFromAWRObject CarImportedFromAWR;

    public void setCarId(String CarId) {
        this.CarId = CarId;
    }

    public String getCarId() {
        return CarId;
    }

    public void setCarTitle(String CarTitle) {
        this.CarTitle = CarTitle;
    }

    public String getCarTitle() {
        return CarTitle;
    }

    public void setOwnedSince(String OwnedSince) {
        this.OwnedSince = OwnedSince;
    }

    public String getOwnedSince() {
        return OwnedSince;
    }

    public void setRegistrationNbr(String RegistrationNbr) {
        this.RegistrationNbr = RegistrationNbr;
    }

    public String getRegistrationNbr() {
        return RegistrationNbr;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getVIN() {
        return VIN;
    }

    public void setCarImage(String CarImage) {
        this.CarImage = CarImage;
    }

    public String getCarImage() {
        return CarImage;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public String getBrand() {
        return Brand;
    }

    public void setOrganizationId(String OrganizationId) {
        this.OrganizationId = OrganizationId;
    }

    public String getOrganizationId() {
        return OrganizationId;
    }

    public void setLastMileage(String LastMileage) {
        this.LastMileage = LastMileage;
    }

    public String getLastMileage() {
        return LastMileage;
    }

    public void setAccessoriesLink(String AccessoriesLink) {
        this.AccessoriesLink = AccessoriesLink;
    }

    public String getAccessoriesLink() {
        return AccessoriesLink;
    }

    public void setTiresLink(String TiresLink) {
        this.TiresLink = TiresLink;
    }

    public String getTiresLink() {
        return TiresLink;
    }

    public void setInsuranceExpiry(InsuranceExpiryObject InsuranceExpiry) {
        this.InsuranceExpiry = InsuranceExpiry;
    }

    public InsuranceExpiryObject getInsuranceExpiry() {
        return InsuranceExpiry;
    }

    public void setLastService(LastServiceObject LastService) {
        this.LastService = LastService;
    }

    public LastServiceObject getLastService() {
        return LastService;
    }

    public void setCarImportedFromAWR(CarImportedFromAWRObject CarImportedFromAWR) {
        this.CarImportedFromAWR = CarImportedFromAWR;
    }

    public CarImportedFromAWRObject getCarImportedFromAWR() {
        return CarImportedFromAWR;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getModel() {
        return Model;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public String getYear() {
        return Year;
    }
}
