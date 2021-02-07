package com.awr.autotrustproxy.dto;

import com.awr.autotrustproxy.dto.ActiveServiceObject;

public class CarImportedFromAWRObject {
    public CarImportedFromAWRObject() {
        super();
    }
    private String displayName;
    private String bookingDisplayName;
    private boolean isImported;
    private ActiveServiceObject activeService;


    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setBookingDisplayName(String bookingDisplayName) {
        this.bookingDisplayName = bookingDisplayName;
    }

    public String getBookingDisplayName() {
        return bookingDisplayName;
    }

    public void setIsImported(boolean isImported) {
        this.isImported = isImported;
    }

    public boolean isIsImported() {
        return isImported;
    }

    public void setActiveService(ActiveServiceObject activeService) {
        this.activeService = activeService;
    }

    public ActiveServiceObject getActiveService() {
        return activeService;
    }
}
