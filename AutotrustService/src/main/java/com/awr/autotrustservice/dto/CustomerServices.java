package com.awr.autotrustservice.dto;

import com.awr.autotrustservice.response.CreateRetailCustomerResponse;
import com.awr.autotrustservice.util.DBConnect;

import com.awr.autotrustservice.util.Utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class CustomerServices {
    public CustomerServices() {
        super();
    }
    public CreateRetailCustomerResponse insertRetailCustomers(CustomerDetails customerDetails, int operatingUnitId) {
        CreateRetailCustomerResponse response = new CreateRetailCustomerResponse();
        String strMessage = null;
        if (customerDetails.getParty_type() == null) {
            strMessage = "All value mediatory cutomer type";
        } else {
            Connection connection = null;

            try {
                connection = DBConnect.getConnection();
                if (connection != null) {
                    CallableStatement cstmt;
                    //cstmt = connection.prepareCall("{call XXDMV_MOBILE_OMS_COMMON_PKG.ws_party_creation" + 
                    cstmt = connection.prepareCall("{call apps.xxdms_vr_party_pkg.ws_party_account_creation" + 
                                                   "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

                    cstmt.registerOutParameter(1, Types.VARCHAR);
                    cstmt.registerOutParameter(2, Types.INTEGER);
                    cstmt.registerOutParameter(3, Types.VARCHAR);
                    cstmt.registerOutParameter(4, Types.INTEGER);
                    //p_party_type
                    cstmt.setString(5, customerDetails.getParty_type());

                    //p_organization_name
                    cstmt.setString(6, null);
                    //p_title
                    cstmt.setString(7, customerDetails.getPerson_pre_name_adjunct());
                    //p_first_name
                    cstmt.setString(8, customerDetails.getPerson_first_name().toUpperCase());
                    //p_last_name
                    cstmt.setString(9, customerDetails.getPerson_last_name().toUpperCase());

                    //p_fp_country_code
                    cstmt.setString(10, customerDetails.getFix_country_code());
                    //p_fp_area_code
                    cstmt.setString(11, customerDetails.getFix_area_code());
                    //p_fp_number
                    cstmt.setString(12, customerDetails.getFix_phone_number());
                    //p_mb_country_code
                    cstmt.setString(13, customerDetails.getPrimary_phone_country_code());
                    //p_mb_area_code
                    cstmt.setString(14, customerDetails.getPrimary_phone_area_code());
                    //p_mb_number
                    cstmt.setString(15, customerDetails.getPrimary_phone_number());
                   //p_fx_country_code
                    cstmt.setString(16, null);
                    //p_fx_area_code
                    cstmt.setString(17, null);
                    //p_fx_number
                    cstmt.setString(18, null);
                    //p_email
                    cstmt.setString(19, customerDetails.getEmail_address());
                    //p_ad_address1
                    cstmt.setString(20, customerDetails.getAddress1());
                    //p_ad_pobox
                    cstmt.setString(21, customerDetails.getPostal_code());
                    //p_ad_emirate
                    cstmt.setString(22, customerDetails.getState());
                    //p_validation
                    cstmt.setString(23, null);
                    //          DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                    //          java.util.Date uDate = dateFormat.parse(customerDetails.getDob());
                    //          java.sql.Date sDate = new java.sql.Date(uDate.getTime());
                    //          System.out.println(uDate.getTime()+":::::sDate::::::"+sDate);
                    //          System.out.println(String.valueOf(sDate));
                    //p_dob
                    cstmt.setString(24, Utils.getSQLDate(customerDetails.getDob()));
                    //p_gender
                    cstmt.setString(25, customerDetails.getGender());
                    //p_nationality
                    cstmt.setString(26, customerDetails.getNationality());
                    //p_cust_category
                    cstmt.setString(27, customerDetails.getCustomer_category());
                    //p_org_id
                    cstmt.setInt(28, operatingUnitId);

                    //p_trade_license_number
                    cstmt.setString(29, null);
                    //p_account_option
                    cstmt.setString(30, null);

                    //p_industry_type
                    cstmt.setString(31, null);

                    //p_loyalty_rating
                    cstmt.setString(32, null);
                    //p_customer_potential
                    cstmt.setString(33, null);
                    //p_account_type
                    cstmt.setString(34, null);
                    //p_replacement_per_year
                    cstmt.setString(35, null);
                    //p_customer_classification IN  VARCHAR2
                    cstmt.setString(36, null);
                    //P_customer_profile
                    cstmt.setString(37, null);
                    //p_acct_type_approval
                    cstmt.setString(38, null);
                    cstmt.setString(39, customerDetails.getCreated_by());
                    cstmt.execute();

                    String x_err_buf = cstmt.getString(1); // x_err_buf
                    String x_retcode = cstmt.getString(2); //x_retcode
                    String x_party_number = cstmt.getString(3); //x_party_number
                    String x_party_id = cstmt.getString(4); //x_party_id
                    //System.out.println("createCustomer: x_party_id:::::" + x_party_id);
                    //System.out.println("createCustomer: x_err_buf:::::" + x_err_buf);
                    System.out.println("x_err_buf: " + x_err_buf);
                    System.out.println("x_retcode: " + x_retcode);
                    System.out.println("x_party_number: " + x_party_number);
                    System.out.println("x_party_id: " + x_party_id);
                    if (x_party_id != null) {
                        strMessage = x_party_id;
                        response.setPartyId(strMessage);
                    } else {
                        strMessage = "error|" + x_err_buf;
                        response.setPartyId(strMessage);
                    }
                    cstmt.close();
                }
            } catch (SQLException e) {
                strMessage = "error|" + e.getMessage();
                response.setPartyId(strMessage);
                e.printStackTrace();
            } catch (Exception e) {
                strMessage = "error|" + e.getMessage();
                response.setPartyId(strMessage);
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    strMessage = "error|" + e.getMessage();
                    response.setPartyId(strMessage);
                    e.printStackTrace();
                }
            }
        }
        return response;
    }
}
