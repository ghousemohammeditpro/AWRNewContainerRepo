package com.awr.autotrustservice.dto;

public class LastServiceObject {
    public LastServiceObject() {
        super();
    }
    private String LastServiceDate;
    private String BookButton;
    private boolean ShowClockWthAlert;
    private boolean ShowBookButton;

    public void setLastServiceDate(String LastServiceDate) {
        this.LastServiceDate = LastServiceDate;
    }

    public String getLastServiceDate() {
        return LastServiceDate;
    }

    public void setBookButton(String BookButton) {
        this.BookButton = BookButton;
    }

    public String getBookButton() {
        return BookButton;
    }

    public void setShowClockWthAlert(boolean ShowClockWthAlert) {
        this.ShowClockWthAlert = ShowClockWthAlert;
    }

    public boolean isShowClockWthAlert() {
        return ShowClockWthAlert;
    }

    public void setShowBookButton(boolean ShowBookButton) {
        this.ShowBookButton = ShowBookButton;
    }

    public boolean isShowBookButton() {
        return ShowBookButton;
    }
}
