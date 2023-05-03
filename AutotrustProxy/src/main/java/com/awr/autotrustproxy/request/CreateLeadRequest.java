package com.awr.autotrustproxy.request;

import com.awr.autotrustproxy.dto.ContactPoints;
import com.awr.autotrustproxy.dto.ExistingVehicles;
import com.awr.autotrustproxy.dto.InterestedVehicles;
import com.awr.autotrustproxy.dto.TradeinVehicles;

public class CreateLeadRequest {
    public CreateLeadRequest() {
        super();
    }
    private String api_version;
    private String application_source_name;
    private String source_lead_id;
    private String lead_date;
    private String source_category;
    private String lead_action;
    private String lead_sales_channel;
    private String lead_value;
    private String lead_classification;
    private String lead_division;
    private String customer_type;
    private String source_customer_id;
    private String title;
    private String first_name;
    private String last_name;
    private String full_name;
    private String email_address;
    private String landline_number;
    private String mobile_number;
    private ContactPoints[] contact_points;
    private String street;
    private String city;
    private String state;
    private String country;
    private String po_box;
    private String makani_number;
    private String date_of_birth;
    private String age;
    private String nationality;
    private String profession;
    private String industry_type;
    private String family_size;
    private String language;
    private String preferred_payment_method;
    private String preferred_method_of_contact;
    private String emirates_id_number;
    private String driving_licence_number;
    private String trade_license_number;
    private String interested_brand;
    private String interested_model;
    private String interested_model_year;
    private String interested_model_colour;
    private String no_of_vehicles_interested;
    private InterestedVehicles[] interested_vehicles;
    private String existing_customer;
    private String existing_brand;
    private String existing_model;
    private String existing_model_age;
    private String no_of_existing_vehicles;
    private ExistingVehicles[] existing_vehicles;
    private String customer_comments;
    private String source_comments;
    private String lead_status;
    private String lead_status_date;
    private String organization_name;
    private String vehicle_change_frequency;
    private String test_drive_completed;
    private String follow_up_call;
    private String emailoptin;
    private String email_optin_date;
    private String lattitude;
    private String longitude;
    private String lead_orig_location;
    private String device_type;
    private String trade_in_interested;
    private String trade_in_brand;
    private String trade_in_model;
    private String trade_in_model_year;
    private String trade_in_mileage;
    private String trade_in_expected_amt;
    private String no_of_tradein_vehicles;
    private TradeinVehicles[] tradein_vehicles;
    private String attribute_category;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    private String attribute6;
    private String attribute7;
    private String attribute8;
    private String attribute9;
    private String attribute10;
    private String orig_source_lead_id;
    private String gender;
    private String fleet_size;
    private String preferred_location;
    private String preferred_person;
    private String preferred_date;
    private String preferred_time;
    private String plate_number;
    private String serial_number;
    private String service_required;
    private String tc_optin;
    private String tc_optin_date;
    private String all_optin;
    private String all_optin_date;
    private String phone_optin;
    private String phone_optin_date;

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }

    public String getApi_version() {
        return api_version;
    }

    public void setApplication_source_name(String application_source_name) {
        this.application_source_name = application_source_name;
    }

    public String getApplication_source_name() {
        return application_source_name;
    }

    public void setSource_lead_id(String source_lead_id) {
        this.source_lead_id = source_lead_id;
    }

    public String getSource_lead_id() {
        return source_lead_id;
    }

    public void setLead_date(String lead_date) {
        this.lead_date = lead_date;
    }

    public String getLead_date() {
        return lead_date;
    }

    public void setSource_category(String source_category) {
        this.source_category = source_category;
    }

    public String getSource_category() {
        return source_category;
    }

    public void setLead_action(String lead_action) {
        this.lead_action = lead_action;
    }

    public String getLead_action() {
        return lead_action;
    }

    public void setLead_sales_channel(String lead_sales_channel) {
        this.lead_sales_channel = lead_sales_channel;
    }

    public String getLead_sales_channel() {
        return lead_sales_channel;
    }

    public void setLead_value(String lead_value) {
        this.lead_value = lead_value;
    }

    public String getLead_value() {
        return lead_value;
    }

    public void setLead_classification(String lead_classification) {
        this.lead_classification = lead_classification;
    }

    public String getLead_classification() {
        return lead_classification;
    }

    public void setLead_division(String lead_division) {
        this.lead_division = lead_division;
    }

    public String getLead_division() {
        return lead_division;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setSource_customer_id(String source_customer_id) {
        this.source_customer_id = source_customer_id;
    }

    public String getSource_customer_id() {
        return source_customer_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setLandline_number(String landline_number) {
        this.landline_number = landline_number;
    }

    public String getLandline_number() {
        return landline_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setContact_points(ContactPoints[] contact_points) {
        this.contact_points = contact_points;
    }

    public ContactPoints[] getContact_points() {
        return contact_points;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setPo_box(String po_box) {
        this.po_box = po_box;
    }

    public String getPo_box() {
        return po_box;
    }

    public void setMakani_number(String makani_number) {
        this.makani_number = makani_number;
    }

    public String getMakani_number() {
        return makani_number;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProfession() {
        return profession;
    }

    public void setIndustry_type(String industry_type) {
        this.industry_type = industry_type;
    }

    public String getIndustry_type() {
        return industry_type;
    }

    public void setFamily_size(String family_size) {
        this.family_size = family_size;
    }

    public String getFamily_size() {
        return family_size;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setPreferred_payment_method(String preferred_payment_method) {
        this.preferred_payment_method = preferred_payment_method;
    }

    public String getPreferred_payment_method() {
        return preferred_payment_method;
    }

    public void setPreferred_method_of_contact(String preferred_method_of_contact) {
        this.preferred_method_of_contact = preferred_method_of_contact;
    }

    public String getPreferred_method_of_contact() {
        return preferred_method_of_contact;
    }

    public void setEmirates_id_number(String emirates_id_number) {
        this.emirates_id_number = emirates_id_number;
    }

    public String getEmirates_id_number() {
        return emirates_id_number;
    }

    public void setDriving_licence_number(String driving_licence_number) {
        this.driving_licence_number = driving_licence_number;
    }

    public String getDriving_licence_number() {
        return driving_licence_number;
    }

    public void setTrade_license_number(String trade_license_number) {
        this.trade_license_number = trade_license_number;
    }

    public String getTrade_license_number() {
        return trade_license_number;
    }

    public void setInterested_brand(String interested_brand) {
        this.interested_brand = interested_brand;
    }

    public String getInterested_brand() {
        return interested_brand;
    }

    public void setInterested_model(String interested_model) {
        this.interested_model = interested_model;
    }

    public String getInterested_model() {
        return interested_model;
    }

    public void setInterested_model_year(String interested_model_year) {
        this.interested_model_year = interested_model_year;
    }

    public String getInterested_model_year() {
        return interested_model_year;
    }

    public void setInterested_model_colour(String interested_model_colour) {
        this.interested_model_colour = interested_model_colour;
    }

    public String getInterested_model_colour() {
        return interested_model_colour;
    }

    public void setNo_of_vehicles_interested(String no_of_vehicles_interested) {
        this.no_of_vehicles_interested = no_of_vehicles_interested;
    }

    public String getNo_of_vehicles_interested() {
        return no_of_vehicles_interested;
    }

    public void setInterested_vehicles(InterestedVehicles[] interested_vehicles) {
        this.interested_vehicles = interested_vehicles;
    }

    public InterestedVehicles[] getInterested_vehicles() {
        return interested_vehicles;
    }

    public void setExisting_customer(String existing_customer) {
        this.existing_customer = existing_customer;
    }

    public String getExisting_customer() {
        return existing_customer;
    }

    public void setExisting_brand(String existing_brand) {
        this.existing_brand = existing_brand;
    }

    public String getExisting_brand() {
        return existing_brand;
    }

    public void setExisting_model(String existing_model) {
        this.existing_model = existing_model;
    }

    public String getExisting_model() {
        return existing_model;
    }

    public void setExisting_model_age(String existing_model_age) {
        this.existing_model_age = existing_model_age;
    }

    public String getExisting_model_age() {
        return existing_model_age;
    }

    public void setNo_of_existing_vehicles(String no_of_existing_vehicles) {
        this.no_of_existing_vehicles = no_of_existing_vehicles;
    }

    public String getNo_of_existing_vehicles() {
        return no_of_existing_vehicles;
    }

    public void setExisting_vehicles(ExistingVehicles[] existing_vehicles) {
        this.existing_vehicles = existing_vehicles;
    }

    public ExistingVehicles[] getExisting_vehicles() {
        return existing_vehicles;
    }

    public void setCustomer_comments(String customer_comments) {
        this.customer_comments = customer_comments;
    }

    public String getCustomer_comments() {
        return customer_comments;
    }

    public void setSource_comments(String source_comments) {
        this.source_comments = source_comments;
    }

    public String getSource_comments() {
        return source_comments;
    }

    public void setLead_status(String lead_status) {
        this.lead_status = lead_status;
    }

    public String getLead_status() {
        return lead_status;
    }

    public void setLead_status_date(String lead_status_date) {
        this.lead_status_date = lead_status_date;
    }

    public String getLead_status_date() {
        return lead_status_date;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setVehicle_change_frequency(String vehicle_change_frequency) {
        this.vehicle_change_frequency = vehicle_change_frequency;
    }

    public String getVehicle_change_frequency() {
        return vehicle_change_frequency;
    }

    public void setTest_drive_completed(String test_drive_completed) {
        this.test_drive_completed = test_drive_completed;
    }

    public String getTest_drive_completed() {
        return test_drive_completed;
    }

    public void setFollow_up_call(String follow_up_call) {
        this.follow_up_call = follow_up_call;
    }

    public String getFollow_up_call() {
        return follow_up_call;
    }

    public void setEmailoptin(String emailoptin) {
        this.emailoptin = emailoptin;
    }

    public String getEmailoptin() {
        return emailoptin;
    }

    public void setEmail_optin_date(String email_optin_date) {
        this.email_optin_date = email_optin_date;
    }

    public String getEmail_optin_date() {
        return email_optin_date;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }

    public String getLattitude() {
        return lattitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLead_orig_location(String lead_orig_location) {
        this.lead_orig_location = lead_orig_location;
    }

    public String getLead_orig_location() {
        return lead_orig_location;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setTrade_in_interested(String trade_in_interested) {
        this.trade_in_interested = trade_in_interested;
    }

    public String getTrade_in_interested() {
        return trade_in_interested;
    }

    public void setTrade_in_brand(String trade_in_brand) {
        this.trade_in_brand = trade_in_brand;
    }

    public String getTrade_in_brand() {
        return trade_in_brand;
    }

    public void setTrade_in_model(String trade_in_model) {
        this.trade_in_model = trade_in_model;
    }

    public String getTrade_in_model() {
        return trade_in_model;
    }

    public void setTrade_in_model_year(String trade_in_model_year) {
        this.trade_in_model_year = trade_in_model_year;
    }

    public String getTrade_in_model_year() {
        return trade_in_model_year;
    }

    public void setTrade_in_mileage(String trade_in_mileage) {
        this.trade_in_mileage = trade_in_mileage;
    }

    public String getTrade_in_mileage() {
        return trade_in_mileage;
    }

    public void setTrade_in_expected_amt(String trade_in_expected_amt) {
        this.trade_in_expected_amt = trade_in_expected_amt;
    }

    public String getTrade_in_expected_amt() {
        return trade_in_expected_amt;
    }

    public void setNo_of_tradein_vehicles(String no_of_tradein_vehicles) {
        this.no_of_tradein_vehicles = no_of_tradein_vehicles;
    }

    public String getNo_of_tradein_vehicles() {
        return no_of_tradein_vehicles;
    }

    public void setTradein_vehicles(TradeinVehicles[] tradein_vehicles) {
        this.tradein_vehicles = tradein_vehicles;
    }

    public TradeinVehicles[] getTradein_vehicles() {
        return tradein_vehicles;
    }

    public void setAttribute_category(String attribute_category) {
        this.attribute_category = attribute_category;
    }

    public String getAttribute_category() {
        return attribute_category;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    public String getAttribute7() {
        return attribute7;
    }

    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }

    public String getAttribute8() {
        return attribute8;
    }

    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }

    public String getAttribute9() {
        return attribute9;
    }

    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
    }

    public String getAttribute10() {
        return attribute10;
    }

    public void setOrig_source_lead_id(String orig_source_lead_id) {
        this.orig_source_lead_id = orig_source_lead_id;
    }

    public String getOrig_source_lead_id() {
        return orig_source_lead_id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setFleet_size(String fleet_size) {
        this.fleet_size = fleet_size;
    }

    public String getFleet_size() {
        return fleet_size;
    }

    public void setPreferred_location(String preferred_location) {
        this.preferred_location = preferred_location;
    }

    public String getPreferred_location() {
        return preferred_location;
    }

    public void setPreferred_person(String preferred_person) {
        this.preferred_person = preferred_person;
    }

    public String getPreferred_person() {
        return preferred_person;
    }

    public void setPreferred_date(String preferred_date) {
        this.preferred_date = preferred_date;
    }

    public String getPreferred_date() {
        return preferred_date;
    }

    public void setPreferred_time(String preferred_time) {
        this.preferred_time = preferred_time;
    }

    public String getPreferred_time() {
        return preferred_time;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setService_required(String service_required) {
        this.service_required = service_required;
    }

    public String getService_required() {
        return service_required;
    }

    public void setTc_optin(String tc_optin) {
        this.tc_optin = tc_optin;
    }

    public String getTc_optin() {
        return tc_optin;
    }

    public void setTc_optin_date(String tc_optin_date) {
        this.tc_optin_date = tc_optin_date;
    }

    public String getTc_optin_date() {
        return tc_optin_date;
    }

    public void setAll_optin(String all_optin) {
        this.all_optin = all_optin;
    }

    public String getAll_optin() {
        return all_optin;
    }

    public void setAll_optin_date(String all_optin_date) {
        this.all_optin_date = all_optin_date;
    }

    public String getAll_optin_date() {
        return all_optin_date;
    }

    public void setPhone_optin(String phone_optin) {
        this.phone_optin = phone_optin;
    }

    public String getPhone_optin() {
        return phone_optin;
    }

    public void setPhone_optin_date(String phone_optin_date) {
        this.phone_optin_date = phone_optin_date;
    }

    public String getPhone_optin_date() {
        return phone_optin_date;
    }

}
