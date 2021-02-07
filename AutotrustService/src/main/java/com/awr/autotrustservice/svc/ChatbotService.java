package com.awr.autotrustservice.svc;

import com.awr.autotrustservice.dto.Lookup;
import com.awr.autotrustservice.dto.StockObject;
import com.awr.autotrustservice.request.CreateSFLeadRequest;
import com.awr.autotrustservice.request.GetModelsRequest;
import com.awr.autotrustservice.request.GetStockRequest;
import com.awr.autotrustservice.request.SendMailRequest;
import com.awr.autotrustservice.response.CommonLookupResponse;
import com.awr.autotrustservice.response.CreateSFLeadResponse;
import com.awr.autotrustservice.response.GenericResponse;
import com.awr.autotrustservice.response.StockResponse;
import com.awr.autotrustservice.util.DBConnect;
import com.awr.autotrustservice.util.LogData;

import com.awr.autotrustservice.util.NotificationAuditLogger;
import com.awr.autotrustservice.util.NotificationExceptionLogger;


import com.google.gson.Gson;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import java.util.ArrayList;

import java.util.Date;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.QueryParam;

import oracle.jdbc.OracleTypes;

@Path("chatbot")
@Consumes("application/json")
@Produces("application/json")
public class ChatbotService {
    public ChatbotService() {
        super();
    }
    NotificationAuditLogger logger = new NotificationAuditLogger();
    NotificationExceptionLogger exceptionLogger = new NotificationExceptionLogger();
    SuccessService successService = new SuccessService();
    
    @GET
    @Path("getBrands")
    public CommonLookupResponse getBrands(@QueryParam("stockType") String stockType, @HeaderParam("appName") String appName) {
        CommonLookupResponse response = new CommonLookupResponse();
        ArrayList<Lookup> lookupList = new ArrayList<Lookup>();
        
        LogData log = new LogData();
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        
        try {
            okExecuteResponse = successService.okToExcuteService1(appName, "Get Brands", "123", "", "", "", "", "", "");//okToExcuteService(appName, "Get Brands", "123");
            if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
            {
            System.out.println("Success service executed");
            
            log.setAppCode("CHATBOT");
            log.setAppName("ChatbotServices");
            log.setDocCode("getBrands");
            log.setUserName("");
            log.setAttribute2("stockType:"+stockType);
            log.setAttribute1("appName:"+appName);
            log.setServiceName("getBrands");
            log.setVendorName("VendorVal:"+appName);
            
                stmt =
                    conn.prepareCall("{call xxdmv_cpo_autotrust_mob_pkg.get_stock_make_list(?,?,?,?)}");
                
                stmt.setString(1, stockType);
                stmt.registerOutParameter(2, OracleTypes.CURSOR);
                stmt.registerOutParameter(3, Types.VARCHAR);
                stmt.registerOutParameter(4, Types.VARCHAR);
                rs=stmt.executeQuery();
            
            if(stmt.getString(3)!=null){
                response.setRetcode(stmt.getString(3));
            }else{
                response.setRetcode("");
            }
            if(stmt.getString(4)!=null){
                response.setRetMsg(stmt.getString(4));
            }else{
                response.setRetMsg("");
            }
            
            if (stmt.getObject(2) != null) {
                rs = (ResultSet) stmt.getObject(2);
                while (rs.next()) {
                    Lookup lookup = new Lookup();
                    if(rs.getString("BRAND") == null){
                        lookup.setLookupCode("");
                    }else{
                        lookup.setLookupCode(rs.getString("BRAND"));
                    }
                    if(rs.getString("BRAND") == null){
                        lookup.setLookupMeaning("");
                    }else{
                        lookup.setLookupMeaning(rs.getString("BRAND"));
                    }
                    lookupList.add(lookup);
                }
                
            }else{
                
            }
            response.setCommonLookup(lookupList.toArray(new Lookup[lookupList.size()]));
//            logger.callAudit(log);   
        }else{
                        response.setRetcode("E");
                        response.setRetMsg(okExecuteResponse.getMessage());
                        return response;
                    }
        } catch (Exception e) {
            log.setErrorMessage(e.getMessage());
//                exceptionLogger.callAudit(log);
            e.printStackTrace();

        } finally{
            try {
                if(rs!=null)
                rs.close();
            } catch (Exception s) {
            s.printStackTrace();
                }
            try {
                if(stmt!=null)
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try{
            DBConnect.closeConnection(conn);
            }
            catch (Exception con) {
                con.printStackTrace();
                }
        }
           
           return response;                         
    }
    
    @PUT
    @Path("getModels")
    public CommonLookupResponse getModels(GetModelsRequest request, @HeaderParam("appName") String appName) {
        CommonLookupResponse response = new CommonLookupResponse();
        ArrayList<Lookup> lookupList = new ArrayList<Lookup>();
        
        LogData log = new LogData();
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        
        try {
            okExecuteResponse = successService.okToExcuteService1(appName, "Get Models", "123", "", "", "", "", "", "");//okToExcuteService(appName, "Get Models", "123");
            if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
            {
            System.out.println("Success service executed");
            
            log.setAppCode("CHATBOT");
            log.setAppName("ChatbotServices");
            log.setDocCode("getModels");
            log.setUserName("");
            log.setAttribute2("vals:"+request.getStockType()+request.getMake());
            log.setAttribute1("appName:"+appName);
            log.setServiceName("getModels");
            log.setVendorName("VendorVal:"+appName);
            
                stmt =
                    conn.prepareCall("{call xxdmv_cpo_autotrust_mob_pkg.get_stock_model_list(?,?,?,?,?)}");
                
                stmt.setString(1, request.getStockType());
                stmt.setString(2, request.getMake());
                stmt.registerOutParameter(3, OracleTypes.CURSOR);
                stmt.registerOutParameter(4, Types.VARCHAR);
                stmt.registerOutParameter(5, Types.VARCHAR);
                rs=stmt.executeQuery();
            
            if(stmt.getString(4)!=null){
                response.setRetcode(stmt.getString(4));
            }else{
                response.setRetcode("");
            }
            if(stmt.getString(5)!=null){
                response.setRetMsg(stmt.getString(5));
            }else{
                response.setRetMsg("");
            }
            
            if (stmt.getObject(3) != null) {
                rs = (ResultSet) stmt.getObject(3);
                while (rs.next()) {
                    Lookup lookup = new Lookup();
                    if(rs.getString("MODEL_DESC") == null){
                        lookup.setLookupCode("");
                    }else{
                        lookup.setLookupCode(rs.getString("MODEL_DESC"));
                    }
                    if(rs.getString("MODEL_DESC") == null){
                        lookup.setLookupMeaning("");
                    }else{
                        lookup.setLookupMeaning(rs.getString("MODEL_DESC"));
                    }
                    lookupList.add(lookup);
                }
                
            }else{
                
            }
            response.setCommonLookup(lookupList.toArray(new Lookup[lookupList.size()]));
//            logger.callAudit(log);   
        }else{
                        response.setRetcode("E");
                        response.setRetMsg(okExecuteResponse.getMessage());
                        return response;
                    }
        } catch (Exception e) {
            log.setErrorMessage(e.getMessage());
//                exceptionLogger.callAudit(log);
            e.printStackTrace();

        } finally{
            try {
                if(rs!=null)
                rs.close();
            } catch (Exception s) {
            s.printStackTrace();
                }
            try {
                if(stmt!=null)
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try{
            DBConnect.closeConnection(conn);
            }
            catch (Exception con) {
                con.printStackTrace();
                }
        }
           
           return response;                         
    }
    
    @PUT
    @Path("getStock")
    public StockResponse getStock(GetStockRequest request, @HeaderParam("appName") String appName) {
        StockResponse response = new StockResponse();
        ArrayList<StockObject> stockList = new ArrayList<StockObject>();
        
        LogData log = new LogData();
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        
        try {
            okExecuteResponse = successService.okToExcuteService1(appName, "Get Stock", "123", "", "", "", "", "", "");//okToExcuteService(appName, "Get Stock", "123");
            if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
            {
            System.out.println("Success service executed");
            
            log.setAppCode("CHATBOT");
            log.setAppName("ChatbotServices");
            log.setDocCode("getStock");
            log.setUserName("");
            log.setAttribute2("vals:"+request.getStockType()+request.getMake()+request.getModel());
            log.setAttribute1("appName:"+appName);
            log.setServiceName("getStock");
            log.setVendorName("VendorVal:"+appName);
            
                stmt =
                    conn.prepareCall("{call xxdmv_cpo_autotrust_mob_pkg.get_stock_make_model(?,?,?,?,?,?)}");
                
                stmt.setString(1, request.getStockType());
                stmt.setString(2, request.getMake());
                stmt.setString(3, request.getModel());
                stmt.registerOutParameter(4, OracleTypes.CURSOR);
                stmt.registerOutParameter(5, Types.VARCHAR);
                stmt.registerOutParameter(6, Types.VARCHAR);
                rs=stmt.executeQuery();
            
            if(stmt.getString(5)!=null){
                response.setRetCode(stmt.getString(5));
            }else{
                response.setRetCode("");
            }
            if(stmt.getString(6)!=null){
                response.setRetMsg(stmt.getString(6));
            }else{
                response.setRetMsg("");
            }
            
            if (stmt.getObject(4) != null) {
                rs = (ResultSet) stmt.getObject(4);
                while (rs.next()) {
                    StockObject veh = new StockObject();
                    veh.setItemCode(rs.getString("ITEM_CODE")!=null ? rs.getString("ITEM_CODE") : "");
                    veh.setDescription(rs.getString("description")!=null ? rs.getString("description") : "");
                    veh.setSerialNumber(rs.getString("serial_number")!=null ? rs.getString("serial_number") : "");
                    veh.setCd(rs.getString("cd")!=null ? rs.getString("cd") : "");
                    veh.setVariant(rs.getString("variant")!=null ? rs.getString("variant") : "");
                    veh.setTrimType(rs.getString("trim_type")!=null ? rs.getString("trim_type") : "");
                    veh.setPowerOptions(rs.getString("power_options")!=null ? rs.getString("power_options") : "");
                    veh.setAbs(rs.getString("abs")!=null ? rs.getString("abs") : "");
                    veh.setKeylessEntry(rs.getString("keyless_entry")!=null ? rs.getString("keyless_entry") : "");
                    veh.setAlloyWheels(rs.getString("alloy_wheels")!=null ? rs.getString("alloy_wheels") : "");
                    veh.setSunRoof(rs.getString("sun_roof")!=null ? rs.getString("sun_roof") : "");
                    veh.setCruiseControl(rs.getString("cruise_control")!=null ? rs.getString("cruise_control") : "");
                    veh.setLeatherSeats(rs.getString("leather_seats")!=null ? rs.getString("leather_seats") : "");
                    veh.setMemorySeats(rs.getString("memory_seats")!=null ? rs.getString("memory_seats") : "");
                    veh.setFogLamps(rs.getString("fog_lamps")!=null ? rs.getString("fog_lamps") : "");
                    veh.setXenonLights(rs.getString("xenon_lights")!=null ? rs.getString("xenon_lights") : "");
                    veh.setNavigation(rs.getString("navigation")!=null ? rs.getString("navigation") : "");
                    veh.setRearCamera(rs.getString("rear_camera")!=null ? rs.getString("rear_camera") : "");
                    veh.setParkingSensors(rs.getString("parking_sensors")!=null ? rs.getString("parking_sensors") : "");
                    veh.setDvd(rs.getString("dvd")!=null ? rs.getString("dvd") : "");
                    veh.setMp3Player(rs.getString("mp3_player")!=null ? rs.getString("mp3_player") : "");
                    veh.setBluetooth(rs.getString("bluetooth")!=null ? rs.getString("bluetooth") : "");
                    veh.setRoofRails(rs.getString("roof_rails")!=null ? rs.getString("roof_rails") : "");
                    veh.setDifferentialLock(rs.getString("differential_lock")!=null ? rs.getString("differential_lock") : "");
                    veh.setRearSpoiler(rs.getString("rear_spoliler")!=null ? rs.getString("rear_spoliler") : "");
                    veh.setRegExpDate(rs.getString("reg_exp_date")!=null ? rs.getString("reg_exp_date") : "");
                    veh.setServiceHistory(rs.getString("service_history")!=null ? rs.getString("service_history") : "");
                    veh.setWarExpDate(rs.getString("war_exp_date")!=null ? rs.getString("war_exp_date") : "");
                    veh.setBrand(rs.getString("brand")!=null ? rs.getString("brand") : "");
                    veh.setWebBrand(rs.getString("web_brand")!=null ? rs.getString("web_brand") : "");
                    veh.setModelDesc(rs.getString("model_desc")!=null ? rs.getString("model_desc") : "");
                    veh.setWebModelDesc(rs.getString("web_model_desc")!=null ? rs.getString("web_model_desc") : "");
                    veh.setTransmission(rs.getString("transmission")!=null ? rs.getString("transmission") : "");
                    veh.setEngineSize(rs.getString("engine_size")!=null ? rs.getString("engine_size") : "");
                    veh.setBodyColorDesc(rs.getString("body_color_desc")!=null ? rs.getString("body_color_desc") : "");
                    veh.setWebBodyColorDesc(rs.getString("web_body_color_desc")!=null ? rs.getString("web_body_color_desc") : "");
                    veh.setTrimColorDesc(rs.getString("trim_color_desc")!=null ? rs.getString("trim_color_desc") : "");
                    veh.setWebTrimColorDesc(rs.getString("web_trim_color_desc")!=null ? rs.getString("web_trim_color_desc") : "");
                    veh.setModelYear(rs.getString("model_year")!=null ? rs.getString("model_year") : "");
                    veh.setEngineType(rs.getString("engine_type")!=null ? rs.getString("engine_type") : "");
                    veh.setBodyType(rs.getString("body_type")!=null ? rs.getString("body_type") : "");
                    veh.setWebBodyType(rs.getString("web_body_type")!=null ? rs.getString("web_body_type") : "");
                    veh.setMileage(rs.getString("mileage")!=null ? rs.getString("mileage") : "");
                    veh.setWarrantyType(rs.getString("warranty_type")!=null ? rs.getString("warranty_type") : "");
                    veh.setSellerName(rs.getString("seller_name")!=null ? rs.getString("seller_name") : "");
                    veh.setVhlPrice(rs.getString("vhl_price")!=null ? rs.getString("vhl_price") : "");
                    veh.setLcvFlag(rs.getString("lcv_flag")!=null ? rs.getString("lcv_flag") : "");
                    veh.setSaleType(rs.getString("sale_type")!=null ? rs.getString("sale_type") : "");
                    veh.setReservedFlag(rs.getString("reserved_flag")!=null ? rs.getString("reserved_flag") : "");
                    veh.setCurrentOrgId(rs.getString("current_organization_id")!=null ? rs.getString("current_organization_id") : "");
                    veh.setCurrentOrgName(rs.getString("current_organization_name")!=null ? rs.getString("current_organization_name") : "");
                    veh.setCreationDate(rs.getString("creation_date")!=null ? rs.getString("creation_date") : "");
                    veh.setLastUpdateDate(rs.getString("last_update_date")!=null ? rs.getString("last_update_date") : "");
                    veh.setRating(rs.getString("rating")!=null ? rs.getString("rating") : "");
                    veh.setKilometers(rs.getString("kilometers")!=null ? rs.getString("kilometers") : "");
                    veh.setFuelType(rs.getString("fuel_type")!=null ? rs.getString("fuel_type") : "");
                    veh.setVehType(rs.getString("veh_type")!=null ? rs.getString("veh_type") : "");
                    veh.setTrimColor(rs.getString("trim_color")!=null ? rs.getString("trim_color") : "");
                    veh.setModel(rs.getString("model")!=null ? rs.getString("model") : "");
                    veh.setSubModelDesc(rs.getString("sub_model_desc")!=null ? rs.getString("sub_model_desc") : "");
                    veh.setReservationFlag(rs.getString("reservation_flag")!=null ? rs.getString("reservation_flag") : "");
                    veh.setOpenFlag(rs.getString("open_flag")!=null ? rs.getString("open_flag") : "");
                    veh.setReservationId(rs.getString("reservation_id")!=null ? rs.getString("reservation_id") : "");
                    veh.setOfferExpDate(rs.getString("offer_expiry_date")!=null ? rs.getString("offer_expiry_date") : "");
                    veh.setNewOfferPrice(rs.getString("new_offer_price")!=null ? rs.getString("new_offer_price") : "");
                    veh.setInventoryItemId(rs.getString("inventory_item_id")!=null ? rs.getString("inventory_item_id") : "");
                    veh.setStockType(rs.getString("stock_type")!=null ? rs.getString("stock_type") : "");
                    veh.setImageUrlName(rs.getString("image_url_name")!=null ? rs.getString("image_url_name") : "");
                    veh.setThumbnailUrlName(rs.getString("thumbnail_url_name")!=null ? rs.getString("thumbnail_url_name") : "");
                    veh.setImageResponse(rs.getString("image_respose")!=null ? rs.getString("image_respose") : "");
                    veh.setAttribute1(rs.getString("attribute1")!=null? rs.getString("attribute1") : "");
                    veh.setAttribute2(rs.getString("attribute2")!=null ? rs.getString("attribute2") : "");
                    veh.setAttribute3(rs.getString("attribute3")!=null ? rs.getString("attribute3") : "");
                    veh.setAttribute4(rs.getString("attribute4")!=null ? rs.getString("attribute4") : "");
                    veh.setAttribute5(rs.getString("attribute5")!=null ? rs.getString("attribute5") : "");
                    veh.setNoOfImages(rs.getString("no_of_images")!=null ? rs.getString("no_of_images") : "");
                    veh.setSpecialOfferFlag(rs.getString("special_offer_flag")!=null ? rs.getString("special_offer_flag") : "");
                    veh.setInspectReportUrl(rs.getString("inspect_report_url")!=null ? rs.getString("inspect_report_url") : "");
                    veh.setServiceReportUrl(rs.getString("service_report_url")!=null ? rs.getString("service_report_url") : "");
                    veh.setBuyNowEnabled(rs.getString("buy_now_enabled")!=null ? rs.getString("buy_now_enabled") : "");
                    veh.setManagerChoice(rs.getString("MANAGER_CHOICE")!=null ? rs.getString("MANAGER_CHOICE") : "");
                    veh.setVehicleLocation(rs.getString("VEHICLE_LOCATION")!=null ? rs.getString("VEHICLE_LOCATION") : "");
                    veh.setTrim(rs.getString("TRIM")!=null ? rs.getString("TRIM") : "");
                    veh.setVehicleGender(rs.getString("VEHICLE_GENDER")!=null ? rs.getString("VEHICLE_GENDER") : "");
                    stockList.add(veh);
                }
                
            }else{
                
            }
            response.setStock(stockList.toArray(new StockObject[stockList.size()]));
//            logger.callAudit(log);   
        }else{
                        response.setRetCode("E");
                        response.setRetMsg(okExecuteResponse.getMessage());
                        return response;
                    }
        } catch (Exception e) {
            log.setErrorMessage(e.getMessage());
//                exceptionLogger.callAudit(log);
            e.printStackTrace();

        } finally{
            try {
                if(rs!=null)
                rs.close();
            } catch (Exception s) {
            s.printStackTrace();
                }
            try {
                if(stmt!=null)
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try{
            DBConnect.closeConnection(conn);
            }
            catch (Exception con) {
                con.printStackTrace();
                }
        }
           
           return response;                         
    }
    
    @PUT
    @Path("createLead")
    public CreateSFLeadResponse createLead(CreateSFLeadRequest req, @HeaderParam("appName") String appName) {
        CreateSFLeadResponse response = new CreateSFLeadResponse();

        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        String successResponse;
        GenericResponse okExecuteResponse = new GenericResponse();


        conn = DBConnect.getConnection();

        try {
            //            System.out.println("user id is : "+userId+" user profile is : "+userProfile+" list type passed is : "+listType);

            okExecuteResponse =
                successService.okToExcuteService1(appName, "Create Lead", "123", "", "", "", "", "", "");

            if (okExecuteResponse.getStatus().equalsIgnoreCase("S")) {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    
                    int leftLimit = 48; // numeral '0'
                    int rightLimit = 122; // letter 'z'
                    int targetStringLength = 20;
                    Random random = new Random();
                    
                    String generatedSourceLeadId = random.ints(leftLimit, rightLimit + 1)
                      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                      .limit(targetStringLength)
                      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                      .toString();

                    System.out.println("random string is : "+generatedSourceLeadId);
//                utility.callAudit("vehicles", "createLead", generatedSourceLeadId);
                    
                    //call generate lead
                    stmt = conn.prepareCall("{call apps.xxdms_all_source_enquiry_pkg.insert_enquiry_details(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    stmt.setLong(1, Long.parseLong("1")); //p_api_version
                    stmt.setString(2, req.getAppSourceName()); //p_application_source_name
                    stmt.setString(3, generatedSourceLeadId); //source lead id unique string generated here
                    stmt.setNull(4, Types.VARCHAR); //p_orig_source_lead_id
                    stmt.setDate(5, sqlDate); //p_lead_date
                    stmt.setString(6, req.getSourceCategory()); //p_source_category
                    stmt.setString(7, req.getLeadAction()); //p_lead_action
                    stmt.setNull(8, Types.VARCHAR); //p_lead_sales_channel
                    stmt.setNull(9, Types.FLOAT); //p_lead_value
                    stmt.setNull(10, Types.VARCHAR); //p_lead_classification
                    stmt.setNull(11, Types.VARCHAR); //p_lead_division
                    stmt.setString(12, req.getCustType()); //p_customer_type
                    stmt.setNull(13, Types.FLOAT); //p_source_customer_id
                    if(req.getTitle()!=null){
                        stmt.setString(14, req.getTitle());
                    }else{
                        stmt.setNull(14, Types.VARCHAR); 
                    }
                if(req.getFname()!=null){
                    stmt.setString(15, req.getFname());
                }else{
                    stmt.setNull(15, Types.VARCHAR); 
                }
                if(req.getLname()!=null){
                    stmt.setString(16, req.getLname());
                }else{
                    stmt.setNull(16, Types.VARCHAR); 
                }
                if(req.getFullName()!=null){
                    stmt.setString(17, req.getFullName());
                }else{
                    stmt.setNull(17, Types.VARCHAR); 
                }
                if(req.getEmail()!=null){
                    stmt.setString(18, req.getEmail());
                }else{
                    stmt.setNull(18, Types.VARCHAR); 
                }
                stmt.setNull(19, Types.VARCHAR); //landline
                if(req.getMobile()!=null){
                    stmt.setString(20, req.getMobile());
                }else{
                    stmt.setNull(20, Types.VARCHAR); 
                }
                if(req.getStreet()!=null){
                    stmt.setString(21, req.getStreet());
                }else{
                    stmt.setNull(21, Types.VARCHAR); 
                }
                if(req.getCity()!=null){
                    stmt.setString(22, req.getCity());
                }else{
                    stmt.setNull(22, Types.VARCHAR); 
                }
                if(req.getState()!=null){
                    stmt.setString(23, req.getState());
                }else{
                    stmt.setNull(23, Types.VARCHAR); 
                }
                if(req.getCountry()!=null){
                    stmt.setString(24, req.getCountry());
                }else{
                    stmt.setNull(24, Types.VARCHAR); 
                }
                if(req.getPoBox()!=null){
                    stmt.setString(25, req.getPoBox());
                }else{
                    stmt.setNull(25, Types.VARCHAR); 
                }
                stmt.setNull(26, Types.VARCHAR); //p_makani_number
                stmt.setNull(27, Types.DATE); //p_date_of_birth
                stmt.setNull(28, Types.FLOAT); //p_age
                stmt.setNull(29, Types.VARCHAR); //p_gender
                stmt.setNull(30, Types.VARCHAR); //p_nationality
                stmt.setNull(31, Types.VARCHAR); //p_profession
                stmt.setNull(32, Types.VARCHAR); //p_industry_type
                stmt.setNull(33, Types.FLOAT); //p_family_size
                stmt.setNull(34, Types.VARCHAR); //p_fleet_size
                stmt.setString(35, "ENGLISH"); //p_language
                stmt.setNull(36, Types.VARCHAR); //p_preferred_payment_method
                stmt.setNull(37, Types.VARCHAR); //p_preferred_method_of_contact
                stmt.setNull(38, Types.VARCHAR); //p_emirates_id_number
                stmt.setNull(39, Types.VARCHAR); //p_driving_licence_number
                stmt.setNull(40, Types.VARCHAR); //p_trade_license_number
                if(req.getBrand()!=null){
                    stmt.setString(41, req.getBrand());
                }else{
                    stmt.setNull(41, Types.VARCHAR); 
                }
                if(req.getModel()!=null){
                    stmt.setString(42, req.getModel());
                }else{
                    stmt.setNull(42, Types.VARCHAR); 
                }
                stmt.setNull(43, Types.FLOAT); //p_interested_model_year
                stmt.setNull(44, Types.VARCHAR); //p_interested_model_colour
                stmt.setNull(45, Types.FLOAT); //p_no_of_vehicles_interested
                if(req.getPrefLocation()!=null){
                    stmt.setString(46, req.getPrefLocation());
                }else{
                    stmt.setNull(46, Types.VARCHAR); 
                }
                stmt.setNull(47, Types.VARCHAR); //p_preferred_person
                stmt.setNull(48, Types.DATE); //p_preferred_date
                stmt.setNull(49, Types.VARCHAR); //p_preferred_time
                if(req.getRegNo()!=null){
                    stmt.setString(50, req.getRegNo());
                }else{
                    stmt.setNull(50, Types.VARCHAR); 
                }
                if(req.getVinNo()!=null){
                    stmt.setString(51, req.getVinNo());
                }else{
                    stmt.setNull(51, Types.VARCHAR); 
                }
                stmt.setNull(52, Types.VARCHAR); //p_service_required
                if(req.getExistingCust()!=null){
                    stmt.setString(53, req.getExistingCust());
                }else{
                    stmt.setNull(53, Types.VARCHAR); 
                }
                stmt.setNull(54, Types.VARCHAR); //p_existing_brand
                stmt.setNull(55, Types.VARCHAR); //p_existing_model
                stmt.setNull(56, Types.FLOAT); //p_existing_model_age
                stmt.setNull(57, Types.FLOAT); //p_no_of_existing_vehicles
                if(req.getComments()!=null){
                    stmt.setString(58, req.getComments());
                }else{
                    stmt.setNull(58, Types.VARCHAR); 
                }
                stmt.setNull(59, Types.VARCHAR); //p_source_comments
                stmt.setString(60, "OPEN"); //p_lead_status
                stmt.setDate(61, sqlDate); //p_lead_status_date
                stmt.setNull(62, Types.VARCHAR); //p_organization_name
                stmt.setNull(63, Types.VARCHAR); //p_vehicle_change_frequency
                stmt.setNull(64, Types.VARCHAR); //p_test_drive_completed
                stmt.setNull(65, Types.VARCHAR); //p_follow_up_call
                stmt.setNull(66, Types.VARCHAR); //p_emailoptin
                stmt.setNull(67, Types.DATE); //p_email_optin_date
                stmt.setNull(68, Types.VARCHAR); //p_tc_optin
                stmt.setNull(69, Types.DATE); //p_tc_optin_date
                stmt.setNull(70, Types.VARCHAR); //p_all_optin
                stmt.setNull(71, Types.DATE); //p_all_optin_date
                stmt.setNull(72, Types.VARCHAR); //p_phone_optin
                stmt.setNull(73, Types.DATE); //p_phone_optin_date
                if(req.getLatitude()!=null){
                    stmt.setLong(74, Long.parseLong(req.getLatitude()));
                }else{
                    stmt.setNull(74, Types.FLOAT); 
                }
                if(req.getLongitude()!=null){
                    stmt.setLong(75, Long.parseLong(req.getLongitude()));
                }else{
                    stmt.setNull(75, Types.FLOAT); 
                }
                stmt.setNull(76, Types.VARCHAR); //p_lead_orig_location
                stmt.setNull(77, Types.VARCHAR); //p_device_type
                stmt.setNull(78, Types.VARCHAR); //p_trade_in_interested
                stmt.setNull(79, Types.VARCHAR); //p_trade_in_brand
                stmt.setNull(80, Types.VARCHAR); //p_trade_in_model
                stmt.setNull(81, Types.FLOAT); //p_trade_in_model_year
                stmt.setNull(82, Types.FLOAT); //p_trade_in_mileage
                stmt.setNull(83, Types.FLOAT); //p_trade_in_expected_amt
                stmt.setNull(84, Types.FLOAT); //p_no_of_tradein_vehicles
                if(req.getAttrCategory()!=null){
                    stmt.setString(85, req.getAttrCategory());
                }else{
                    stmt.setNull(85, Types.VARCHAR); 
                }
                if(req.getAttr1()!=null){
                    stmt.setString(86, req.getAttr1());
                }else{
                    stmt.setNull(86, Types.VARCHAR); 
                }
                stmt.setNull(87, Types.VARCHAR); //p_attribute2
                stmt.setNull(88, Types.VARCHAR); //p_attribute3
                stmt.setNull(89, Types.VARCHAR); //p_attribute4
                stmt.setNull(90, Types.VARCHAR); //p_attribute5
                stmt.setNull(91, Types.VARCHAR); //p_attribute6
                stmt.setNull(92, Types.VARCHAR); //p_attribute7
                stmt.setNull(93, Types.VARCHAR); //p_attribute8
                stmt.setNull(94, Types.VARCHAR); //p_attribute9
                stmt.setNull(95, Types.VARCHAR); //p_attribute10
                
                stmt.registerOutParameter(96, OracleTypes.FLOAT); //p_reference_number
                stmt.registerOutParameter(97, OracleTypes.VARCHAR); //p_return_status
                stmt.registerOutParameter(98, OracleTypes.VARCHAR); //p_return_message
                rs = stmt.executeQuery();
                
                if (stmt.getString(96) != null) {
                    response.setRefNo(stmt.getString(96));
                } else {
                    response.setRefNo("");
                }
                if (stmt.getString(97) != null) {
                    response.setRetCode(stmt.getString(97));
                } else {
                    response.setRetCode("");
                }
                if (stmt.getString(98) != null) {
                    response.setRetMsg(stmt.getString(98));
                } else {
                    response.setRetMsg("");
                }
                
                
                System.out.println("ref no is : "+response.getRefNo());
                System.out.println("retcode is : "+response.getRetCode());
                System.out.println("retmsg is : "+response.getRetMsg());

            } else {
                response.setRetCode("E");
                response.setRetMsg(okExecuteResponse.getMessage());
                return response;
            }
        } catch (Exception e) {
//            new CallExceptionLogger().callExceptionLog(e.toString());
            e.printStackTrace();

        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (Exception s) {
                s.printStackTrace();
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                DBConnect.closeConnection(conn);
            } catch (Exception con) {
                con.printStackTrace();
            }

        }
        return response;

    }
    
    @PUT
    @Path("sendMail")
    public GenericResponse sendMail(SendMailRequest request, @HeaderParam("appName") String appName) {
        GenericResponse response = new GenericResponse();
        
        LogData log = new LogData();
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        
        try {
            okExecuteResponse = successService.okToExcuteService1(appName, "Send Mail", "123", "", "", "", "", "", "");
            if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
            {
            System.out.println("Success service executed");
            
            log.setAppCode("CHATBOT");
            log.setAppName("ChatbotServices");
            log.setDocCode("sendMail");
            log.setUserName("");
            log.setAttribute2("vals:"+request.getSenderEmail()+request.getSubject());
            log.setAttribute1("appName:"+appName);
            log.setServiceName("sendMail");
            log.setVendorName("VendorVal:"+appName);
            
                stmt = conn.prepareCall("{call XXDMS_CLOUD_INTEGRATION_PKG.send_html_mail(?,?,?,?,?)}");
                
                stmt.setString(1, request.getSenderEmail());
                stmt.setString(2, request.getRecipients());
                stmt.setString(3, request.getSubject());
                stmt.setString(4, request.getBody());
                stmt.setString(5, request.getFileName());
                rs=stmt.executeQuery();
            
            response.setMessage("Success");
            response.setStatus("S");
    //            logger.callAudit(log);
        }else{
                        response.setStatus("E");
                        response.setMessage(okExecuteResponse.getMessage());
                        return response;
                    }
        } catch (Exception e) {
            log.setErrorMessage(e.getMessage());
    //                exceptionLogger.callAudit(log);
            e.printStackTrace();

        } finally{
            try {
                if(rs!=null)
                rs.close();
            } catch (Exception s) {
            s.printStackTrace();
                }
            try {
                if(stmt!=null)
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try{
            DBConnect.closeConnection(conn);
            }
            catch (Exception con) {
                con.printStackTrace();
                }
        }
           
           return response;                         
    }
    
}
