package com.awr.autotrustproxy.dto;

public class LastServiceObject {
    public LastServiceObject() {
        super();
    }
    private String lastServiceDate;
    private String bookButton;
    private boolean showClockWthAlert;
    private boolean showBookButton;

    public void setLastServiceDate(String lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    public String getLastServiceDate() {
        return lastServiceDate;
    }

    public void setBookButton(String bookButton) {
        this.bookButton = bookButton;
    }

    public String getBookButton() {
        return bookButton;
    }

    public void setShowClockWthAlert(boolean showClockWthAlert) {
        this.showClockWthAlert = showClockWthAlert;
    }

    public boolean isShowClockWthAlert() {
        return showClockWthAlert;
    }

    public void setShowBookButton(boolean showBookButton) {
        this.showBookButton = showBookButton;
    }

    public boolean isShowBookButton() {
        return showBookButton;
    }
}
