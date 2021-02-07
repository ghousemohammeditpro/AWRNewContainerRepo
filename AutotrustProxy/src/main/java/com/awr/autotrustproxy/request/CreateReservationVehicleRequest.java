package com.awr.autotrustproxy.request;

public class CreateReservationVehicleRequest {
    public CreateReservationVehicleRequest() {
        super();
    }
    private String sourceName;
    private String userId;
    private String itemId;
    private String sourceLeadKey;
    private String preferredLocation;

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setSourceLeadKey(String sourceLeadKey) {
        this.sourceLeadKey = sourceLeadKey;
    }

    public String getSourceLeadKey() {
        return sourceLeadKey;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }
}
