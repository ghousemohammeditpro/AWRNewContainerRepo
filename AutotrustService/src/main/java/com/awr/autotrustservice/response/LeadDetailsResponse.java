package com.awr.autotrustservice.response;

import java.util.Date;

public class LeadDetailsResponse {
    public LeadDetailsResponse() {
        super();
    }
    private String lead_status;
    private String status_update_date;
    private String lead_comments;
    private String interested_model ;
    private String sales_person_name;
    private String sales_person_contact_number;
    private String sales_person_email_addres;
    private String showroom_location;
    private String test_drive_status;
    private String return_status;
    private String return_message;

    public void setLead_status(String lead_status) {
        this.lead_status = lead_status;
    }

    public String getLead_status() {
        return lead_status;
    }

    public void setStatus_update_date(String status_update_date) {
        this.status_update_date = status_update_date;
    }

    public String getStatus_update_date() {
        return status_update_date;
    }

    public void setLead_comments(String lead_comments) {
        this.lead_comments = lead_comments;
    }

    public String getLead_comments() {
        return lead_comments;
    }

    public void setInterested_model(String interested_model) {
        this.interested_model = interested_model;
    }

    public String getInterested_model() {
        return interested_model;
    }

    public void setSales_person_name(String sales_person_name) {
        this.sales_person_name = sales_person_name;
    }

    public String getSales_person_name() {
        return sales_person_name;
    }

    public void setSales_person_contact_number(String sales_person_contact_number) {
        this.sales_person_contact_number = sales_person_contact_number;
    }

    public String getSales_person_contact_number() {
        return sales_person_contact_number;
    }

    public void setSales_person_email_addres(String sales_person_email_addres) {
        this.sales_person_email_addres = sales_person_email_addres;
    }

    public String getSales_person_email_addres() {
        return sales_person_email_addres;
    }

    public void setShowroom_location(String showroom_location) {
        this.showroom_location = showroom_location;
    }

    public String getShowroom_location() {
        return showroom_location;
    }

    public void setTest_drive_status(String test_drive_status) {
        this.test_drive_status = test_drive_status;
    }

    public String getTest_drive_status() {
        return test_drive_status;
    }

    public void setReturn_status(String return_status) {
        this.return_status = return_status;
    }

    public String getReturn_status() {
        return return_status;
    }

    public void setReturn_message(String return_message) {
        this.return_message = return_message;
    }

    public String getReturn_message() {
        return return_message;
    }
}
