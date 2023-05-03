package com.awr.autotrustservice.dto;

public class CarImportedFromAWRObject {
    public CarImportedFromAWRObject() {
        super();
    }
    private String DisplayName;
    private String BookingDisplayName;
    private boolean IsImported;
    private ActiveServiceObject ActiveService;

    public void setDisplayName(String DisplayName) {
        this.DisplayName = DisplayName;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setBookingDisplayName(String BookingDisplayName) {
        this.BookingDisplayName = BookingDisplayName;
    }

    public String getBookingDisplayName() {
        return BookingDisplayName;
    }

    public void setIsImported(boolean IsImported) {
        this.IsImported = IsImported;
    }

    public boolean isIsImported() {
        return IsImported;
    }

    public void setActiveService(ActiveServiceObject ActiveService) {
        this.ActiveService = ActiveService;
    }

    public ActiveServiceObject getActiveService() {
        return ActiveService;
    }
}
