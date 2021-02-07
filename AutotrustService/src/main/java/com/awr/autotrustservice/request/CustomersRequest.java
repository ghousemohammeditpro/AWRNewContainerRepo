package com.awr.autotrustservice.request;

import com.awr.autotrustservice.dto.Customer;

import com.awr.autotrustservice.dto.CustomerContact;


public class CustomersRequest {
    public CustomersRequest() {
        super();
    }
    private String empID;
    private String orgID;
    private Customer customer;
    private CustomerContact customerContact;
    private String mode;

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpID() {
        return empID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    public String getOrgID() {
        return orgID;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomerContact(CustomerContact customerContact) {
        this.customerContact = customerContact;
    }

    public CustomerContact getCustomerContact() {
        return customerContact;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
