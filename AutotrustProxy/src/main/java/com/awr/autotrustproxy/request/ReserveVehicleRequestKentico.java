package com.awr.autotrustproxy.request;

public class ReserveVehicleRequestKentico {
    public ReserveVehicleRequestKentico() {
        super();
    }
    private String ApplicationName;
    private String InventoryItemId;
    private String ReserveCarFlag;
    private String UnreserveCarFlag;

    public void setApplicationName(String ApplicationName) {
        this.ApplicationName = ApplicationName;
    }

    public String getApplicationName() {
        return ApplicationName;
    }

    public void setInventoryItemId(String InventoryItemId) {
        this.InventoryItemId = InventoryItemId;
    }

    public String getInventoryItemId() {
        return InventoryItemId;
    }

    public void setReserveCarFlag(String ReserveCarFlag) {
        this.ReserveCarFlag = ReserveCarFlag;
    }

    public String getReserveCarFlag() {
        return ReserveCarFlag;
    }

    public void setUnreserveCarFlag(String UnreserveCarFlag) {
        this.UnreserveCarFlag = UnreserveCarFlag;
    }

    public String getUnreserveCarFlag() {
        return UnreserveCarFlag;
    }
}
