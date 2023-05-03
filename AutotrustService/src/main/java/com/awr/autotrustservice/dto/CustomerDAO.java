package com.awr.autotrustservice.dto;

import com.awr.autotrustservice.request.CustomersRequest;
import com.awr.autotrustservice.response.ResponseObject;

import com.awr.autotrustservice.util.DBConnect;
import com.awr.autotrustservice.util.Utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import oracle.jdbc.OracleConnection;

import oracle.jdbc.OracleTypes;

import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

public class CustomerDAO {
    public CustomerDAO() {
        super();
    }
    public ResponseObject insertOrganizationCustomers(CustomersRequest request, String mode) {
        ResponseObject obj = new ResponseObject();
        Connection conn = null;
        CallableStatement stmt = null;
        conn = DBConnect.getConnection();
        OracleConnection oconn = null;
        oconn = DBConnect.getConnection(conn);
      try {
          //Customer

            @SuppressWarnings("deprecation")
            STRUCT struct = null;
//            Customer customer = request.getCustomers() == null ? null : request.getCustomers().get(0);
          Customer customer = request.getCustomer();

            if (customer != null) {
                Object[] customer_array   = new Object[33]; 
                Integer id = Utils.isNullOrEmpty(customer.getCustID()) ? null : Integer.valueOf(customer.getCustID());
                customer_array[0] =  id;  //Customer ID
                customer_array[1] = Utils.isNullOrEmpty(customer.getCustNo()) ? null : customer.getCustNo().trim(); //Customer Number
                customer_array[2] = Utils.isNullOrEmpty(customer.getName()) ? null : customer.getName().trim(); //Name
                customer_array[3] = Utils.isNullOrEmpty(customer.getLandlineCtryCode()) ? null : customer.getLandlineCtryCode().trim(); //Telephone Country Code
                customer_array[4] = Utils.isNullOrEmpty(customer.getLandlineAreaCode()) ? null : customer.getLandlineAreaCode().trim(); //Telephone Area Code
                customer_array[5] = Utils.isNullOrEmpty(customer.getLandlineNumber()) ? null : customer.getLandlineNumber().trim(); //Telephone Number
                customer_array[6] =  Utils.isNullOrEmpty(customer.getLandlineContactPointId()) ? null : customer.getLandlineContactPointId().trim(); // Telephone Contact Point ID NUMBER
                customer_array[7] = Utils.isNullOrEmpty(customer.getMobileCtryCode()) ? null : customer.getMobileCtryCode().trim(); //Mobile Country Code
                customer_array[8] = Utils.isNullOrEmpty(customer.getMobileAreaCode()) ? null : customer.getMobileAreaCode().trim(); //Mobile Area Code
                customer_array[9] = Utils.isNullOrEmpty(customer.getMobileNumber()) ? null : customer.getMobileNumber().trim(); //Mobile Number
                customer_array[10] =  Utils.isNullOrEmpty(customer.getMobileContactPointId()) ? null : customer.getMobileContactPointId().trim() ; //Mobile Contact Point ID
                customer_array[11] = Utils.isNullOrEmpty(customer.getEmail()) ? null : customer.getEmail().trim(); //Email
                customer_array[12] = Utils.isNullOrEmpty(customer.getEmailContactPointId()) ? null : customer.getEmailContactPointId().trim(); //Email Contact Point ID
                customer_array[13] = Utils.isNullOrEmpty(customer.getAddress()) ? null : customer.getAddress().trim(); //Address
                customer_array[14] = Utils.isNullOrEmpty(customer.getPoBox()) ? null : customer.getPoBox().trim(); //POBOX  
                customer_array[15] = Utils.isNullOrEmpty(customer.getCityCode()) ? null : customer.getCityCode().trim(); //Emirate
                customer_array[16] = Utils.isNullOrEmpty(customer.getTradeLicenseNum()) ? null : customer.getTradeLicenseNum().trim(); //Trade License Number
                customer_array[17] = Utils.isNullOrEmpty(customer.getBtCode()) ? null : customer.getBtCode().trim(); //BT Code
                customer_array[18] = Utils.isNullOrEmpty(customer.getAtCode()) ? null : customer.getAtCode().trim(); //AT Code
                customer_array[19] = Utils.isNullOrEmpty(customer.getYearlyReplacements()) ? 0 : Integer.valueOf(customer.getYearlyReplacements().trim()); //Yearly Replacements
                customer_array[20] = Utils.isNullOrEmpty(customer.getCustClassCode()) ? null : customer.getCustClassCode().trim(); //Customer Class Code
                customer_array[21] = Utils.isNullOrEmpty(customer.getPotCode()) ? null : customer.getPotCode().trim();//POT Code
                customer_array[22] = Utils.isNullOrEmpty(customer.getLrCode()) ? null : customer.getLrCode().trim(); //LR Code
                customer_array[23] = Utils.isNullOrEmpty(customer.getAoCode()) ? null : customer.getAoCode().trim(); //AO Code
                customer_array[24] = Utils.isNullOrEmpty(customer.getAssignedTo()) ? null : Integer.valueOf(customer.getAssignedTo().trim()); //Assigned To (Emp ID)
                customer_array[25] = Utils.isNullOrEmpty(customer.getCreatedAt()) ? null : customer.getCreatedAt().trim(); //Creation Date
                customer_array[26] = Utils.isNullOrEmpty(customer.getCreatedBy()) ? null : Integer.valueOf(customer.getCreatedBy().trim()); //Created By                
                customer_array[27] = Utils.isNullOrEmpty(customer.getLastUpdated()) ? null : customer.getLastUpdated().trim(); //Last Updated Date                
                customer_array[28] = Utils.isNullOrEmpty(customer.getLastUpdatedBy()) ? null : Integer.valueOf(customer.getLastUpdatedBy().trim()); //Last Updated By
                customer_array[29] = Utils.isNullOrEmpty(customer.getIsActiveFlag()) ? null : customer.getIsActiveFlag().trim(); //IS ACTIVE Flag
                customer_array[30] = Utils.isNullOrEmpty(customer.getOwnerName()) ? null : customer.getOwnerName().trim(); //Owner Name
                customer_array[31] = Utils.isNullOrEmpty(customer.getLatitude()) ? 0 : Double.valueOf(customer.getLatitude().trim()); //Customer Latitude
                customer_array[32] = Utils.isNullOrEmpty(customer.getLongitude()) ? 0 : Double.valueOf(customer.getLongitude().trim());//Customer Longitude

                 
                @SuppressWarnings("deprecation")
                StructDescriptor structdesc = StructDescriptor.createDescriptor("XXFO_CUSTOMER_REC", oconn);
                struct = new STRUCT(structdesc, oconn, customer_array);                                
                /* ArrayDescriptor arrayDescriptor = ArrayDescriptor.createDescriptor("XXFO_CUSTOMER_REC", oconn);
                ARRAY oracle_array = new ARRAY(arrayDescriptor, oconn, customer_array); */

            }
              // Customer Conacts
              STRUCT structContact = null;
//              CustomerContact contact = request.getCustomerContact() == null ? null : request.getCustomerContact().get(0);
          CustomerContact contact = request.getCustomerContact();

              if (contact != null) {
                  Object[] contact_array   = new Object[21]; 
                  Integer id = Utils.isNullOrEmpty(contact.getContactID()) ? null : Integer.valueOf(contact.getContactID());
                  contact_array[0] = id; //Contact ID
                  contact_array[1] = Utils.isNullOrEmpty(contact.getCustID()) ? null : contact.getCustID(); //Customer ID
                  contact_array[2] = Utils.isNullOrEmpty(contact.getTitle()) ? null : contact.getTitle(); //Title
                  contact_array[3] = Utils.isNullOrEmpty(contact.getFirstName()) ? null : contact.getFirstName(); //First Name
                  contact_array[4] = Utils.isNullOrEmpty(contact.getLastName()) ? null : contact.getLastName(); //Last Name
                  contact_array[5] = Utils.isNullOrEmpty(contact.getDesignation()) ? null : contact.getDesignation(); //Designation
                  contact_array[6] = Utils.isNullOrEmpty(contact.getTelCtryCode()) ? null : contact.getTelCtryCode(); //Telephone Country Code
                  contact_array[7] = Utils.isNullOrEmpty(contact.getTelAreaCode()) ? null : contact.getTelAreaCode();//Telephone Area Code
                  contact_array[8] =  Utils.isNullOrEmpty(contact.getTelNumber()) ? null : contact.getTelNumber(); //Telephone Number
                  contact_array[9] =  Utils.isNullOrEmpty(contact.getTelContactPointId()) ? null : contact.getTelContactPointId(); // Telephone Contact Point ID NUMBER

              
                  contact_array[10] = Utils.isNullOrEmpty(contact.getEmail()) ? null : contact.getEmail(); //Email
                  contact_array[11] =  Utils.isNullOrEmpty(contact.getEmailContactPointId()) ? null : contact.getEmailContactPointId();//Email Contact Point ID
                  contact_array[12] =  Utils.isNullOrEmpty(contact.getAddress()) ? null : contact.getAddress(); //Address
                  contact_array[13] =  Utils.isNullOrEmpty(contact.getPoBox()) ? null : contact.getPoBox(); //POBOX

                  contact_array[14] = Utils.isNullOrEmpty(contact.getCityCode()) ? null : contact.getCityCode(); //Emirate
                  
                  
                  contact_array[15] =  Utils.isNullOrEmpty(contact.getCreatedAt()) ? null : contact.getCreatedAt(); //Creation Date
                  contact_array[16] =  Utils.isNullOrEmpty(contact.getCreatedBy()) ? null : Integer.valueOf(contact.getCreatedBy()); //Created By

                  contact_array[17] = Utils.isNullOrEmpty(contact.getLastUpdatedAt()) ? null : contact.getLastUpdatedAt(); //Last Updated Date
                  contact_array[18] = Utils.isNullOrEmpty(contact.getLastUpdatedBy()) ? null : Integer.valueOf(contact.getLastUpdatedBy()); //Last Updated By
                  contact_array[19] = Utils.isNullOrEmpty(contact.getContactLat()) ? null : Double.valueOf(contact.getContactLat()); //Contact Latitude
                  contact_array[20] = Utils.isNullOrEmpty(contact.getContactLong()) ? null : Double.valueOf(contact.getContactLong());//Contact Longitude

                  StructDescriptor structContactdesc = StructDescriptor.createDescriptor("XXFO_CUST_CONTACT_REC", oconn);
                  structContact = new STRUCT(structContactdesc, oconn, contact_array);
          }
           
           if (mode.equals("INSERT")) {
              
              //String.format("{CALL XXDMV_FLEET_OMS_UTILS_PKG.CREATE_PARTY(?,?,?,?,?,?,?,?)}"));
                                                        // xxdms_vr_party_pkg.create_party_account
              stmt = conn.prepareCall(String.format("{CALL apps.xxdms_vr_party_pkg.create_party_account(?,?,?,?,?,?,?,?)}"));
              System.out.println("-----------------------------");
              System.out.println("Ammad: I'm in Customer Insert Method");
              System.out.println("-----------------------------");
              
              stmt.setInt("p_emp_id", Integer.valueOf(request.getEmpID()));
              stmt.setInt("p_org_id", Integer.valueOf(request.getOrgID()));
              stmt.setObject("p_customer", struct);
              stmt.setObject("p_cust_contact", structContact);
              
              
              stmt.registerOutParameter("x_cust_id", OracleTypes.NUMBER);
              stmt.registerOutParameter("x_cust_contact_id", OracleTypes.NUMBER);                         
              stmt.registerOutParameter("x_result", OracleTypes.VARCHAR);
              stmt.registerOutParameter("x_err_message", OracleTypes.VARCHAR);
          }

             
            stmt.execute();

            obj.setResponseCode("S");
            obj.setResponseMessage("Success");
            obj.setErrorDescription(stmt.getString("x_err_message"));
          
          
          //We need Cust_ID and Cust_Contact_ID, in case we are inserting the Customer
          if (mode.equals("INSERT")) {
              System.out.println("We need Cust_ID and Cust_Contact_ID, in case we are inserting the Customer");
              obj.setCustId(stmt.getInt("x_cust_id"));
              obj.setCustContactId(stmt.getInt("x_cust_contact_id"));
              
              System.out.println("obj.getCustContactId(): " + obj.getCustContactId());
              System.out.println("obj.getCustId(): " + obj.getCustId());

          }
      } 
      catch(Exception e){
           obj.setResponseCode("E");
           obj.setResponseMessage("Unexpected error while posting customer.");
           e.printStackTrace();
           obj.setErrorDescription(e.toString());
           
      }finally{
      }
     return obj;
    }
}
