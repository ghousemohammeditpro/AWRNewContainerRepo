package com.awr.autotrustservice.request;

public class ReserveVehicleRequest {
    public ReserveVehicleRequest() {
        super();
    }
    private String applicationName;
    private String inventoryItemId;
    private String reserveCarFlag;
    private String unreserveCarFlag;


    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setInventoryItemId(String inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public String getInventoryItemId() {
        return inventoryItemId;
    }

    public void setReserveCarFlag(String reserveCarFlag) {
        this.reserveCarFlag = reserveCarFlag;
    }

    public String getReserveCarFlag() {
        return reserveCarFlag;
    }

    public void setUnreserveCarFlag(String unreserveCarFlag) {
        this.unreserveCarFlag = unreserveCarFlag;
    }

    public String getUnreserveCarFlag() {
        return unreserveCarFlag;
    }
}
