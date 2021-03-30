package com.awr.autotrustservice.svc;

import com.awr.autotrustservice.dto.AddonObject;
import com.awr.autotrustservice.dto.Availability;
import com.awr.autotrustservice.dto.BranchHolidaysObject;
import com.awr.autotrustservice.dto.BranchTimeObject;
import com.awr.autotrustservice.dto.ContactPoints;
import com.awr.autotrustservice.dto.CpoDealerVehicle;
import com.awr.autotrustservice.dto.CustomerVehicle;
import com.awr.autotrustservice.dto.DeviceObject;
import com.awr.autotrustservice.dto.ExistingVehicles;
import com.awr.autotrustservice.dto.IncidentObject;
import com.awr.autotrustservice.dto.InstalledBase;
import com.awr.autotrustservice.dto.InterestedVehicles;
import com.awr.autotrustservice.dto.LeadDetailsBatch;
import com.awr.autotrustservice.dto.LocationObject;
import com.awr.autotrustservice.dto.LoginResponseDataObject;
import com.awr.autotrustservice.dto.Lookup;
import com.awr.autotrustservice.dto.ServiceAdvisorObject;
import com.awr.autotrustservice.dto.TradeinVehicles;
import com.awr.autotrustservice.dto.TransactionObject;
import com.awr.autotrustservice.dto.TxnDetailsObject;
import com.awr.autotrustservice.dto.Vehicle;
import com.awr.autotrustservice.dto.VehicleImage;
import com.awr.autotrustservice.request.CommonLookupRequest;
import com.awr.autotrustservice.request.CreateAppointmentRequest;
import com.awr.autotrustservice.request.CreateLeadRequest;
import com.awr.autotrustservice.request.CreateReservationVehicleRequest;
import com.awr.autotrustservice.request.ForgotPasswordData;
import com.awr.autotrustservice.request.GetBranchesRequest;
import com.awr.autotrustservice.request.GetDeviceTokenRequest;
import com.awr.autotrustservice.request.InsertAddonRequest;
import com.awr.autotrustservice.request.LoginRequest;
import com.awr.autotrustservice.request.MyStatementRequest;
import com.awr.autotrustservice.request.RequestBean;
import com.awr.autotrustservice.request.ReserveVehicleRequest;
import com.awr.autotrustservice.request.SendNotificationRequest;
import com.awr.autotrustservice.request.ServiceAdvisorRequest;
import com.awr.autotrustservice.request.SubmitAddonRequest;
import com.awr.autotrustservice.response.AvailabilityResponse;
import com.awr.autotrustservice.response.BranchesResponse;
import com.awr.autotrustservice.response.CommonLookupResponse;
import com.awr.autotrustservice.response.CpoDealerVehiclesResponse;
import com.awr.autotrustservice.response.CreateAppointmentResponse;
import com.awr.autotrustservice.response.CreateLeadResponse;
import com.awr.autotrustservice.response.CreateReservationVehicleResponse;
import com.awr.autotrustservice.response.CustomerVehicleResponse;
import com.awr.autotrustservice.response.ForgotPasswordResponse;
import com.awr.autotrustservice.response.GenericResponse;
import com.awr.autotrustservice.response.GetReservationNumberResponse;
import com.awr.autotrustservice.response.GetTokenIdResponse;
import com.awr.autotrustservice.response.LeadDetailsBatchResponse;
import com.awr.autotrustservice.response.LeadDetailsResponse;
import com.awr.autotrustservice.response.LoginResponse;

import com.awr.autotrustservice.response.MyStatementResponse;
import com.awr.autotrustservice.response.SalesOrdersResponse;
import com.awr.autotrustservice.response.ServiceAdvisorResponse;
import com.awr.autotrustservice.response.VehicleImagesResponse;
import com.awr.autotrustservice.response.VehiclesResponse;
import com.awr.autotrustservice.util.CommonUtil;
import com.awr.autotrustservice.util.DBConnect;
import com.awr.autotrustservice.util.ForgetPwdUtil;
import com.awr.autotrustservice.util.FtpUtil;
import com.awr.autotrustservice.util.LogData;
import com.awr.autotrustservice.util.LoginUtil;

import com.awr.autotrustservice.util.NotificationAuditLogger;
import com.awr.autotrustservice.util.NotificationExceptionLogger;
import com.awr.autotrustservice.util.PropertiesUtil;
import com.awr.autotrustservice.util.RestUtil;
import com.awr.autotrustservice.util.TestUtil;

import com.awr.autotrustservice.util.UtilityClass;


import com.awr.autotrustservice.util.Utils;

import com.google.gson.Gson;


import com.google.gson.GsonBuilder;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Types;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.QueryParam;

import oracle.jdbc.OracleTypes;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

import org.apache.commons.lang3.exception.ExceptionUtils;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;


@Path("autotrust")
@Consumes("application/json")
@Produces("application/json")
public class AutotrustService {
    public AutotrustService() {
        super();
    }
    private static String key = "€%$Enrich*&€";
    NotificationAuditLogger logger = new NotificationAuditLogger();
    NotificationExceptionLogger exceptionLogger = new NotificationExceptionLogger();
    SuccessService successService = new SuccessService();
    
    @PUT
    @Path("loginService")
    public LoginResponse login(LoginRequest req, @HeaderParam("appName") String appName, @HeaderParam("source") String source){
        Connection conn = null;
        LoginResponse response = new LoginResponse();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal login ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal login is : "+appName);
        
        try{
//        successResponse = successService.excuteService("login", "EN");
        okExecuteResponse = successService.okToExcuteService(appName, "Login Service", "123");
//        if (successResponse.equals("S")) 
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("login");
            log.setUserName(req.getUsername());
            log.setAttribute1("userName:"+req.getUsername());
            log.setServiceName("login");
            log.setVendorName("VendorVal:"+appName);
            
        result = LoginUtil.callLogin(req, source);
        
        if(result!=null){
            response = gson.fromJson(result, LoginResponse.class);
//            if(response.getData()!=null){
//                System.out.println("response is : "+response.toString());
//                String dataString = response.getData();
//                if(dataString!=null)
//                dataString = dataString.replaceAll("\\\\", "");
//                System.out.println("data string is : "+dataString);
//                LoginResponseDataObject dataObject = new LoginResponseDataObject();
//                dataObject = gson.fromJson(dataString, LoginResponseDataObject.class);
//                response.setDataObjects(dataObject);
//                response.setData(null);
//                
//            }
        }
//            log.setAttribute2("instanceId:"+instanceId);
        logger.callAudit(log);
        }else{
            response.setStatus("E");
            response.setMessage(okExecuteResponse.getMessage());
            return response;
        }
        }catch(Exception e){
            log.setErrorMessage(e.toString());
            exceptionLogger.callAudit(log);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally{
            try{
            DBConnect.closeConnection(conn);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
//        System.out.println("response obj data is : "+response.getBuId()+" , "+response.getDateOfBirth());
        return response;
    }
    
    @PUT
    @Path("forgotPwdService")
    public ForgotPasswordResponse forgotPwd(ForgotPasswordData data, @HeaderParam("appName") String appName, @HeaderParam("source") String source){
        Connection conn = null;
        ForgotPasswordResponse response = new ForgotPasswordResponse();
        Gson gson = new Gson();
        LogData log = new LogData();
        System.out.println("in internal login ");
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal login is : "+appName);
        String result = null;
        
        try{
//        successResponse = successService.excuteService("login", "EN");
        okExecuteResponse = successService.okToExcuteService(appName, "Forgot Password", "123");
//        if (successResponse.equals("S")) 
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))     
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("forgotPwd");
            log.setUserName("");
            log.setAttribute1("emailMobile:"+data.getEmailMobile());
            log.setServiceName("forgotPwd");
            log.setVendorName("VendorVal:"+appName);
            
        result = ForgetPwdUtil.callForgetPasword(data, source);
        if(result!=null){
            response = gson.fromJson(result, ForgotPasswordResponse.class);
        }
            logger.callAudit(log);
        }else{
            response.setStatus("E");
            response.setMessage(okExecuteResponse.getMessage());
            return response;
        }
            
        }catch(Exception e){
            log.setErrorMessage(e.toString());
            exceptionLogger.callAudit(log);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally{
            try{
            DBConnect.closeConnection(conn);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
        return response;
    }
    
    @GET
    @Path("getCpoVehicles")
    public CpoDealerVehiclesResponse getCpoVehicles(@HeaderParam("appName") String appName){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        CpoDealerVehiclesResponse response = new CpoDealerVehiclesResponse();
        ArrayList<CpoDealerVehicle> vehs = new ArrayList<CpoDealerVehicle>();
        Gson gson = new Gson();
        LogData log = new LogData();
        System.out.println("in internal getCpoVehicles ");
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal getCpoVehicles is : "+appName);
        String result = null;
        
        try{
//        successResponse = successService.excuteService("getCpoVehicles", "EN");
        okExecuteResponse = successService.okToExcuteService(appName, "Get Stock Vehicles", "123");
//        if (successResponse.equals("S")) 
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("getCpoVehicles");
            log.setUserName("");
            log.setAttribute1("appName:"+appName);
            log.setServiceName("getCpoVehicles");
            log.setVendorName("VendorVal:"+appName);
            
            String cpoVehilcesQuery = "SELECT item_code, \n"+
                                      "description, \n"+
                                      "serial_number, \n"+
                                      "cd, \n"+
                                      "variant, \n"+
                                      "trim_type, \n"+
                                      "power_options, \n"+
                                      "abs, \n"+
                                      "keyless_entry, \n"+
                                      "alloy_wheels, \n"+
                                      "sun_roof, \n"+
                                      "cruise_control, \n"+
                                      "leather_seats, \n"+
                                      "memory_seats, \n"+
                                      "fog_lamps, \n"+
                                      "xenon_lights, \n"+
                                      "navigation, \n"+
                                      "rear_camera, \n"+
                                      "parking_sensors, \n"+
                                      "dvd, \n"+
                                      "mp3_player, \n"+
                                      "bluetooth, \n"+
                                      "roof_rails, \n"+
                                      "differential_lock, \n"+
                                      "rear_spoliler, \n"+
                                      "reg_exp_date, \n"+
                                      "service_history, \n"+
                                      "war_exp_date, \n"+
                                      "brand, \n"+
                                      "web_brand, \n"+
                                      "model_desc, \n"+
                                      "web_model_desc, \n"+
                                      "transmission, \n"+
                                      "engine_size, \n"+
                                      "body_color_desc, \n"+
                                      "web_body_color_desc, \n"+
                                      "trim_color_desc, \n"+
                                      "web_trim_color_desc, \n"+
                                      "model_year, \n"+
                                      "engine_type, \n"+
                                      "body_type, \n"+
                                      "web_body_type, \n"+
                                      "mileage, \n"+
                                      "warranty_type, \n"+
                                      "seller_name, \n"+
                                      "vhl_price, \n"+
                                      "lcv_flag, \n"+
                                      "sale_type, \n"+
                                      "reserved_flag, \n"+
                                      "current_organization_id, \n"+
                                      "current_organization_name, \n"+
                                      "creation_date, \n"+
                                      "last_update_date, \n"+
                                      "rating, \n"+
                                      "kilometers, \n"+
                                      "fuel_type, \n"+
                                      "veh_type, \n"+
                                      "trim_color, \n"+
                                      "model, \n"+
                                      "sub_model_desc, \n"+
                                      "reservation_flag, \n"+
                                      "open_flag, \n"+
                                      "reservation_id, \n"+
                                      "offer_expiry_date, \n"+
                                      "new_offer_price, \n"+
                                      "inventory_item_id, \n"+
                                      "stock_type, \n"+
                                      "image_url_name, \n"+
                                      "thumbnail_url_name, \n"+
                                      "image_respose, \n"+
                                      "attribute1, \n"+
                                      "attribute2, \n"+
                                      "attribute3, \n"+
                                      "attribute4, \n"+
                                      "attribute5, \n"+
                                      "no_of_images, \n"+
                                      "special_offer_flag, \n"+
                                      "inspect_report_url, \n"+
                                      "service_report_url, \n"+
                                      "buy_now_enabled, \n"+
                                      "image_url_pattern, \n"+
                                      "vehicle_location, \n"+
                                      "vehicle_gender, \n"+
                                      "trim \n"+
                                      "FROM \n"+
                                      "XXDMV_CPO_DEALER_VEHICLES_V";
            
//            System.out.println("query is : "+cpoVehilcesQuery);
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(cpoVehilcesQuery);
            if(rs!=null){
                
                while(rs.next()){
                    CpoDealerVehicle veh = new CpoDealerVehicle();
                    veh.setItemCode(rs.getString("item_code")!=null ? rs.getString("item_code") : "");
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
                    veh.setLcvflag(rs.getString("lcv_flag")!=null ? rs.getString("lcv_flag") : "");
                    veh.setSaleType(rs.getString("sale_type")!=null ? rs.getString("sale_type") : "");
                    veh.setReservedFlag(rs.getString("reserved_flag")!=null ? rs.getString("reserved_flag") : "");
                    veh.setCurrentOrganizationId(rs.getString("current_organization_id")!=null ? rs.getString("current_organization_id") : "");
                    veh.setCurrentOrganizationName(rs.getString("current_organization_name")!=null ? rs.getString("current_organization_name") : "");
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
                    veh.setOfferExpiryDate(rs.getString("offer_expiry_date")!=null ? rs.getString("offer_expiry_date") : "");
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
                    veh.setSpecialOffer_Flag(rs.getString("special_offer_flag")!=null ? rs.getString("special_offer_flag") : "");
                    veh.setInspectReportUrl(rs.getString("inspect_report_url")!=null ? rs.getString("inspect_report_url") : "");
                    veh.setServiceReportUrl(rs.getString("service_report_url")!=null ? rs.getString("service_report_url") : "");
                    veh.setBuyNowEnabled(rs.getString("buy_now_enabled")!=null ? rs.getString("buy_now_enabled") : "");
                    
                    veh.setManagersChoice(CommonUtil.calcFlagOnStringDate(veh.getCreationDate()));
                    veh.setVehicleLocation(rs.getString("vehicle_location")!=null ? rs.getString("vehicle_location") : "");
                    
                    veh.setVehicleGender(rs.getString("vehicle_gender")!=null ? rs.getString("vehicle_gender") : "");
                    veh.setTrim(rs.getString("trim")!=null ? rs.getString("trim") : "");
                    
                    if(rs.getString("image_url_pattern")!=null && rs.getString("image_url_pattern")!=""){
                        String pattern = rs.getString("image_url_pattern");
                        
                        if(pattern.contains("=")){
                        String[] parts = pattern.split("=");
                        String part1 = parts[0]; //pattern
                        String part2 = parts[1]; //noOfImages
                        int count = Integer.parseInt(part2);
                        
                        if(count>0){
                            veh.setThumbnailImage(part1+"_0.jpg"); 
                        }else{
                            veh.setThumbnailImage("");
                        }
                        }else{
                            veh.setThumbnailImage("");
                        }
                    }else{
                        veh.setThumbnailImage("");
                    }
                    if(veh.getVhlPrice()!=""){
                        vehs.add(veh);  
                    }
                    
                }
            }
            response.setVehicles(vehs.toArray(new CpoDealerVehicle[vehs.size()]));
            response.setStatus("S");
            response.setMessage("Success");
            logger.callAudit(log);
        }
        else{
            response.setStatus("E");
            response.setMessage(okExecuteResponse.getMessage());
            return response;
        }
                
        }catch(Exception e){
            log.setErrorMessage(e.toString());
                exceptionLogger.callAudit(log);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally{
            try{
            DBConnect.closeConnection(conn);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
        return response;
    }
    
    
    @PUT
    @Path("getCommonLookup")
    public CommonLookupResponse getCommonLookup(CommonLookupRequest req, @HeaderParam("appName") String appName) {
        CommonLookupResponse response = new CommonLookupResponse();
        ArrayList<Lookup> lookupList = new ArrayList<Lookup>();
        
        LogData log = new LogData();
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        String successResponse;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        
        try {
//            successResponse = successService.excuteService("getCommonLookup", "EN");
            okExecuteResponse = successService.okToExcuteService(appName, "Get Common Lookup", "123");
//            if (successResponse.equals("S")) 
            if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
            {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("getCommonLookup");
            log.setUserName("");
            log.setAttribute2("listType:"+req.getLookupType());
            log.setAttribute1("appName:"+appName);
            log.setServiceName("getCommonLookup");
            log.setVendorName("VendorVal:"+appName);
            
                stmt =
                    conn.prepareCall("{call xxdmv_cpo_dealer_mob_pkg.get_list_values(?,?,?,?,?)}");
                
                stmt.setString(1, req.getLookupType());
                UtilityClass util = new UtilityClass();
                stmt.setString(2, util.stringArrayToString(req.getDependentVal()));
            
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
                    if(rs.getString("CODE") == null){
                        lookup.setLookupCode("");
                    }else{
                        lookup.setLookupCode(rs.getString("CODE"));
                    }
                    if(rs.getString("DESCRIPTION") == null){
                        lookup.setLookupMeaning("");
                    }else{
                        lookup.setLookupMeaning(rs.getString("DESCRIPTION"));
                    }
                    lookupList.add(lookup);
                }
                
            }else{
                
            }
            response.setCommonLookup(lookupList.toArray(new Lookup[lookupList.size()]));
            logger.callAudit(log);   
        }else{
                        response.setRetcode("E");
                        response.setRetMsg(okExecuteResponse.getMessage());
                        return response;
                    }
        } catch (Exception e) {
            log.setErrorMessage(e.getMessage());
                exceptionLogger.callAudit(log);
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
    @Path("createReservationVehicle")
    public CreateReservationVehicleResponse createReservationVehicle(CreateReservationVehicleRequest req, @HeaderParam("appName") String appName) {
        CreateReservationVehicleResponse response = new CreateReservationVehicleResponse();
        
        LogData log = new LogData();
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        String successResponse;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        
        try {
//            successResponse = successService.excuteService("createReservationVehicle", "EN");
            okExecuteResponse = successService.okToExcuteService(appName, "Create Reservation Vehicle", "123");
//            if (successResponse.equals("S")) 
            if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
            {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("createReservationVehicle");
            log.setUserName("userId:"+req.getUserId());
            log.setAttribute1("appName:"+appName);
            log.setAttribute2("sourceName:"+req.getSourceName());
            log.setServiceName("createReservationVehicle");
            log.setVendorName("VendorVal:"+appName);
            
                stmt =
                    conn.prepareCall("{call xxdmv_cpo_autotrust_mob_pkg.create_reservation_vehicle(?,?,?,?,?,?,?,?)}");
                
                stmt.setString(1, req.getSourceName());
                stmt.setLong(2, Long.parseLong(req.getUserId()));
                stmt.setLong(3, Long.parseLong(req.getItemId()));
                stmt.setString(4, req.getSourceLeadKey());
                stmt.setString(5, req.getPreferredLocation());
                stmt.registerOutParameter(6, Types.VARCHAR);
                stmt.registerOutParameter(7, Types.VARCHAR);
                stmt.registerOutParameter(8, Types.VARCHAR);
                rs=stmt.executeQuery();
            
            if(stmt.getString(7)!=null){
                response.setRetCode(stmt.getString(7));
            }else{
                response.setRetCode("");
            }
            if(stmt.getString(8)!=null){
                response.setRetMsg(stmt.getString(8));
            }else{
                response.setRetMsg("");
            }
            if(stmt.getString(6)!=null){
                response.setEnquiryId(stmt.getString(6));
            }else{
                response.setEnquiryId("");
            }
            
                logger.callAudit(log);
        }else{
            response.setRetCode("E");
            response.setRetMsg(okExecuteResponse.getMessage());
            return response;
        }
        } catch (Exception e) {
            log.setErrorMessage(e.getMessage());
                    exceptionLogger.callAudit(log);
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
    
    
    @GET
    @Path("getVehicleImages")
    public VehicleImagesResponse getVehicleImages(@QueryParam("vinNumber") String vinNumber, @HeaderParam("appName") String appName){
        Gson gson = new Gson();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        LogData log = new LogData();
        ArrayList<VehicleImage> images = new ArrayList<VehicleImage>();
        VehicleImagesResponse response = new VehicleImagesResponse();
        System.out.println("in internal getVehicleImages ");
        String successResponse = null;
        conn = DBConnect.getConnection();
        GenericResponse okExecuteResponse = new GenericResponse();
        //where to pass vendor value??
        System.out.println("appName in internal getVehicleImages is : "+appName);
        
        try{
//        successResponse = successService.excuteService("getVehicleImages", "EN");
        okExecuteResponse = successService.okToExcuteService(appName, "Get Vehicle Images", "32371");
//        if (successResponse.equals("S")) 
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S")) 
        {
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("getVehicleImages");
            log.setUserName("");
            log.setAttribute1("vinNum:"+vinNumber);
            log.setServiceName("getVehicleImages");
            log.setVendorName("VendorVal:"+appName);
            
            System.out.println("Success service executed");
//            images = FtpUtil.getImages(vinNumber);
            String query = "SELECT image_url_pattern, \n"+
                           "no_of_images \n"+
                           "FROM \n"+
                           "XXDMV_CPO_DEALER_VEHICLES \n"+
                           "WHERE \n"+
                           "serial_number = '"+vinNumber+"'";
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            if(rs!=null){
                
                while(rs.next()){
                    String imageUrlPattern = rs.getString("image_url_pattern");
                    if(imageUrlPattern!=null && imageUrlPattern!="" && !imageUrlPattern.isEmpty()){
                    if(imageUrlPattern.contains("=")){
                    String[] parts = imageUrlPattern.split("=");
                    String part1 = parts[0]; //pattern
                    String part2 = parts[1]; //noOfImages
                    int count = Integer.parseInt(part2);
                    
                    if(part1!=null && count>0){
                    for(int i=0;i<count;i++){
                        VehicleImage img = new VehicleImage();
                        img.setImageUrl(part1+"_"+i+".jpg");
                        images.add(img);
                    }
                    }
                    }
                    }
                }
            }
           response.setImages(images.toArray(new VehicleImage[images.size()]));
           response.setMessage("Success");
           response.setStatus("S");
        logger.callAudit(log);
        }else{
            response.setStatus("E");
            response.setMessage(okExecuteResponse.getMessage());
            return response;
        }
                        
        }catch(Exception e){
            log.setErrorMessage(e.toString());
                        exceptionLogger.callAudit(log);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null && rs!=null && conn!=null){
                    stmt.close();
                    rs.close();
                    conn.close();
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
        return response;
        
    }
    
    @GET
    @Path("getSalesOrderDetails")
    public SalesOrdersResponse getSalesOrderDetails(@QueryParam("buId") String buId, @HeaderParam("appName") String appName) {
        SalesOrdersResponse response = new SalesOrdersResponse();
        
        LogData log = new LogData();
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        String successResponse;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        
        try {
//            successResponse = successService.excuteService("getSalesOrderDetails", "EN");
            okExecuteResponse = successService.okToExcuteService(appName, "Get Sales Order Details", "123");
//            if (successResponse.equals("S"))
            if (okExecuteResponse.getStatus().equalsIgnoreCase("S")) 
            {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("getSalesOrderDetails");
            log.setUserName("buId:"+buId);
            log.setAttribute1("appName:"+appName);
            log.setServiceName("getSalesOrderDetails");
            log.setVendorName("VendorVal:"+appName);
            
                stmt =
                    conn.prepareCall("{call xxdmv_cpo_autotrust_mob_pkg.get_enrich_so_details(?,?,?,?)}");
                
                stmt.setLong(1, Long.parseLong(buId));
                stmt.registerOutParameter(2, OracleTypes.CURSOR);
                stmt.registerOutParameter(3, Types.VARCHAR);
                stmt.registerOutParameter(4, Types.VARCHAR);
                rs=stmt.executeQuery();
            
            if(stmt.getString(3)!=null){
                response.setRetCode(stmt.getString(3));
            }else{
                response.setRetCode("");
            }
            if(stmt.getString(4)!=null){
                response.setRetMsg(stmt.getString(4));
            }else{
                response.setRetMsg("");
            }
            List<TransactionObject> txnList = new ArrayList<TransactionObject>();
            
            if (stmt.getObject(2) != null) {
                rs = (ResultSet) stmt.getObject(2);
                while (rs.next()) {
                    TransactionObject txn = new TransactionObject();
                    if(rs.getString("TXN_ID") == null){
                        txn.setTxnId("");
                    }else{
                        txn.setTxnId(rs.getString("TXN_ID"));
                    }
                    if(rs.getString("TXN_SOURCE") == null){
                        txn.setTxnSource("");
                    }else{
                        txn.setTxnSource(rs.getString("TXN_SOURCE"));
                    }
                    if(rs.getString("LP_CUSTOMER_ID") == null){
                        txn.setLpCustomerId("");
                    }else{
                        txn.setLpCustomerId(rs.getString("LP_CUSTOMER_ID"));
                    }
                    if(rs.getString("CUSTOMER_ID") == null){
                        txn.setCustomerId("");
                    }else{
                        txn.setCustomerId(rs.getString("CUSTOMER_ID"));
                    }
                    if(rs.getString("CUST_ACCOUNT_ID") == null){
                        txn.setCustAccountId("");
                    }else{
                        txn.setCustAccountId(rs.getString("CUST_ACCOUNT_ID"));
                    }
                    if(rs.getString("PARTNER_CODE") == null){
                        txn.setPartnerCode("");
                    }else{
                        txn.setPartnerCode(rs.getString("PARTNER_CODE"));
                    }
//                    if(rs.getString("PARTNER_ID") == null){
//                        txn.setPartnerId("");
//                    }else{
//                        txn.setPartnerId(rs.getString("PARTNER_ID"));
//                    }
                    if(rs.getString("TRX_DATE") == null){
                        txn.setTrxDate("");
                    }else{
                        txn.setTrxDate(rs.getString("TRX_DATE"));
                    }
                    if(rs.getString("SOURCE_TXN_TYPE") == null){
                        txn.setSourceTxnType("");
                    }else{
                        txn.setSourceTxnType(rs.getString("SOURCE_TXN_TYPE"));
                    }
                    if(rs.getString("TXN_AMOUNT") == null){
                        txn.setTxnAmount("");
                    }else{
                        txn.setTxnAmount(rs.getString("TXN_AMOUNT"));
                    }
                    if(rs.getString("PAID_AMOUNT") == null){
                        txn.setPaidAmount("");
                    }else{
                        txn.setPaidAmount(rs.getString("PAID_AMOUNT"));
                    }
                    if(rs.getString("SOURCE_DOC_NUMBER") == null){
                        txn.setSourceDocNumber("");
                    }else{
                        txn.setSourceDocNumber(rs.getString("SOURCE_DOC_NUMBER"));
                    }
                    if(rs.getString("SOURCE_DOC_ID") == null){
                        txn.setSourceDocId("");
                    }else{
                        txn.setSourceDocId(rs.getString("SOURCE_DOC_ID"));
                    }
                    if(rs.getString("TRX_NUMBER") == null){
                        txn.setTrxNumber("");
                    }else{
                        txn.setTrxNumber(rs.getString("TRX_NUMBER"));
                    }
                    if(rs.getString("TOTAL_POINTS") == null){
                        txn.setTotalPoints("");
                    }else{
                        txn.setTotalPoints(rs.getString("TOTAL_POINTS"));
                    }
//                    if(rs.getString("AWARD_STATUS") == null){
//                        txn.setAwardStatus("");
//                    }else{
//                        txn.setAwardStatus(rs.getString("AWARD_STATUS"));
//                    }
//                    if(rs.getString("SUB_LEDGER_STATUS") == null){
//                        txn.setSubLedgerStatus("");
//                    }else{
//                        txn.setSubLedgerStatus(rs.getString("SUB_LEDGER_STATUS"));
//                    }
//                    if(rs.getString("ATTRIBUTE_CATEGORY") == null){
//                        txn.setAttributeCategory("");
//                    }else{
//                        txn.setAttributeCategory(rs.getString("ATTRIBUTE_CATEGORY"));
//                    }
//                    if(rs.getString("ATTRIBUTE1") == null){
//                        txn.setAttribute1("");
//                    }else{
//                        txn.setAttribute1(rs.getString("ATTRIBUTE1"));
//                    }
//                    if(rs.getString("ATTRIBUTE2") == null){
//                        txn.setAttribute2("");
//                    }else{
//                        txn.setAttribute2(rs.getString("ATTRIBUTE2"));
//                    }
//                    if(rs.getString("ATTRIBUTE3") == null){
//                        txn.setAttribute3("");
//                    }else{
//                        txn.setAttribute3(rs.getString("ATTRIBUTE3"));
//                    }
//                    if(rs.getString("ATTRIBUTE4") == null){
//                        txn.setAttribute4("");
//                    }else{
//                        txn.setAttribute4(rs.getString("ATTRIBUTE4"));
//                    }
//                    if(rs.getString("ATTRIBUTE5") == null){
//                        txn.setAttribute5("");
//                    }else{
//                        txn.setAttribute5(rs.getString("ATTRIBUTE5"));
//                    }
//                    if(rs.getString("TRX_ID") == null){
//                        txn.setTrxId("");
//                    }else{
//                        txn.setTrxId(rs.getString("TRX_ID"));
//                    }
//                    if(rs.getString("CURRENCY_CODE") == null){
//                        txn.setCurrencyCode("");
//                    }else{
//                        txn.setCurrencyCode(rs.getString("CURRENCY_CODE"));
//                    }
//                    if(rs.getString("EXCHANGE_RATE") == null){
//                        txn.setExchangeRate("");
//                    }else{
//                        txn.setExchangeRate(rs.getString("EXCHANGE_RATE"));
//                    }
                    if(rs.getString("BATCH_ID") == null){
                        txn.setBatchId("");
                    }else{
                        txn.setBatchId(rs.getString("BATCH_ID"));
                    }
                    if(rs.getString("RECORD_STATUS_FLAG") == null){
                        txn.setRecordStatusFlag("");
                    }else{
                        txn.setRecordStatusFlag(rs.getString("RECORD_STATUS_FLAG"));
                    }
                    if(rs.getString("BATCH_NUMBER") == null){
                        txn.setBatchNumber("");
                    }else{
                        txn.setBatchNumber(rs.getString("BATCH_NUMBER"));
                    }
//                    if(rs.getString("REMARKS") == null){
//                        txn.setRemarks("");
//                    }else{
//                        txn.setRemarks(rs.getString("REMARKS"));
//                    }
//                    if(rs.getString("CUSTOMER_CODE") == null){
//                        txn.setCustomerCode("");
//                    }else{
//                        txn.setCustomerCode(rs.getString("CUSTOMER_CODE"));
//                    }
//                    if(rs.getString("FC_AMOUNT") == null){
//                        txn.setFcAmount("");
//                    }else{
//                        txn.setFcAmount(rs.getString("FC_AMOUNT"));
//                    }
//                    if(rs.getString("GL_TRANSFER_FLAG") == null){
//                        txn.setGlTransferFlag("");
//                    }else{
//                        txn.setGlTransferFlag(rs.getString("GL_TRANSFER_FLAG"));
//                    }
//                    if(rs.getString("GL_TRANSFER_DATE") == null){
//                        txn.setGlTransferDate("");
//                    }else{
//                        txn.setGlTransferDate(rs.getString("GL_TRANSFER_DATE"));
//                    }
//                    if(rs.getString("MOBILE_NUMBER") == null){
//                        txn.setMobileNumber("");
//                    }else{
//                        txn.setMobileNumber(rs.getString("MOBILE_NUMBER"));
//                    }
//                    if(rs.getString("LOB") == null){
//                        txn.setLob("");
//                    }else{
//                        txn.setLob(rs.getString("LOB"));
//                    }
                    if(rs.getString("BRAND") == null){
                        txn.setBrand("");
                    }else{
                        txn.setBrand(rs.getString("BRAND"));
                    }
                    if(rs.getString("BRANCH") == null){
                        txn.setBranch("");
                    }else{
                        txn.setBranch(rs.getString("BRANCH"));
                    }
//                    if(rs.getString("STORE_ID") == null){
//                        txn.setStoreId("");
//                    }else{
//                        txn.setStoreId(rs.getString("STORE_ID"));
//                    }
//                    if(rs.getString("POS_ID") == null){
//                        txn.setPosId("");
//                    }else{
//                        txn.setPosId(rs.getString("POS_ID"));
//                    }
                    if(rs.getString("ORGANIZATION_ID") == null){
                        txn.setOrganizationId("");
                    }else{
                        txn.setOrganizationId(rs.getString("ORGANIZATION_ID"));
                    }
//                    if(rs.getString("ACCOUNT_CATEGORY_TYPE") == null){
//                        txn.setAccountCategoryType("");
//                    }else{
//                        txn.setAccountCategoryType(rs.getString("ACCOUNT_CATEGORY_TYPE"));
//                    }
                    if(rs.getString("POINTS_VALUE") == null){
                        txn.setPointsValue("");
                    }else{
                        txn.setPointsValue(rs.getString("POINTS_VALUE"));
                    }
                    if(rs.getString("BLOOOM_TXN_ID") == null){
                        txn.setBlooomTxnId("");
                    }else{
                        txn.setBlooomTxnId(rs.getString("BLOOOM_TXN_ID"));
                    }
//                    if(rs.getString("AWARDING_STATUS") == null){
//                        txn.setAwardingStatus("");
//                    }else{
//                        txn.setAwardingStatus(rs.getString("AWARDING_STATUS"));
//                    }
                    txnList.add(txn);
                }
            }
            
            response.setSalesOrders(txnList.toArray(new TransactionObject[txnList.size()]));
                logger.callAudit(log);
        }else{
                        response.setRetCode("E");
                        response.setRetMsg(okExecuteResponse.getMessage());
                        return response;
                    }
        } catch (Exception e) {
            log.setErrorMessage(e.getMessage());
                    exceptionLogger.callAudit(log);
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
 
 
    @GET
    @Path("getCustomerVeh")
    public CustomerVehicleResponse getCustomerVeh(@QueryParam("loyaltyPortalId") String loyaltyPortalId,
                                                      @QueryParam("idf") String idf,
                                                      @HeaderParam("appName") String appName) 
    {
        CustomerVehicleResponse response = new CustomerVehicleResponse();
        ArrayList<CustomerVehicle> vehList = new ArrayList<CustomerVehicle>();
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        LogData log = new LogData();
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();

        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal getCustomerVeh is : "+appName);
        System.out.println("input params are:loyaltyPortalId:"+loyaltyPortalId+", idf:"+idf);

        try {
//            successResponse = successService.excuteService("getCustomerVeh", "EN");
            okExecuteResponse = successService.okToExcuteService(appName, "Get Customer Vehicle", "123");
//            if (successResponse.equals("S")) 
            if (okExecuteResponse.getStatus().equalsIgnoreCase("S")) 
            {
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("getCustomerVeh");
            log.setUserName("loyaltyPortalId:"+loyaltyPortalId);
            log.setAttribute1("idf:"+idf);
                log.setServiceName("getCustomerVeh");
                log.setVendorName("VendorVal:"+appName);
            
                stmt =
                    conn.prepareCall("{call xxdmv_cpo_autotrust_mob_pkg.get_customer_veh_details(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
               
            if(loyaltyPortalId!=null){
                stmt.setString(1, loyaltyPortalId);
            }else{
                stmt.setNull(1, Types.VARCHAR);
            }
            if(idf!=null){
                stmt.setString(2, idf);
            }else{
                stmt.setNull(2, Types.VARCHAR);
            }
            
            stmt.setNull(3, Types.VARCHAR);
            stmt.setNull(4, Types.VARCHAR);
            stmt.setNull(5, Types.VARCHAR);
            stmt.setNull(6, Types.VARCHAR);
            stmt.setNull(7, Types.VARCHAR);
            
            
            stmt.registerOutParameter(8, Types.VARCHAR);
            stmt.registerOutParameter(9, Types.VARCHAR);
            stmt.registerOutParameter(10, Types.VARCHAR);
            stmt.registerOutParameter(11, Types.VARCHAR);
            stmt.registerOutParameter(12, Types.VARCHAR);
                
            stmt.registerOutParameter(13, OracleTypes.CURSOR);
            stmt.registerOutParameter(14, Types.VARCHAR);
            stmt.registerOutParameter(15, Types.VARCHAR);
                
            rs=stmt.executeQuery();
            
            if(stmt.getString(8)!=null){
            response.setAttribute6(stmt.getString(8));
            }else{
                response.setAttribute6("");
            }
            if(stmt.getString(9)!=null){
            response.setAttribute7(stmt.getString(9));
            }else{
                response.setAttribute7("");
            }
            if(stmt.getString(10)!=null){
            response.setAttribute8(stmt.getString(10));
            }else{
                response.setAttribute8("");
            }
            if(stmt.getString(11)!=null){
            response.setAttribute9(stmt.getString(11));
            }else{
                response.setAttribute9("");
            }
            if(stmt.getString(12)!=null){
            response.setAttribute10(stmt.getString(12));
            }else{
                response.setAttribute10("");
            }
            
            if(stmt.getString(14)!=null){
                response.setRetStatus(stmt.getString(14));
            }else{
                response.setRetStatus("");
            }
            if(stmt.getString(15)!=null){
            response.setRetMsg(stmt.getString(15));
            }else{
                response.setRetMsg("");
            }
            
            if (stmt.getObject(13) != null) {
                rs = (ResultSet) stmt.getObject(13);
                
                while (rs.next()) {
                    CustomerVehicle veh = new CustomerVehicle();
                    if(rs.getString("instance_id") == null){
                        veh.setInstanceId("");
                    }else{
                        veh.setInstanceId(rs.getString("instance_id"));
                    }
                    if(rs.getString("regn_no") == null){
                        veh.setRegnNo("");
                    }else{
                        veh.setRegnNo(rs.getString("regn_no"));
                    }
                    if(rs.getString("serial_number") == null){
                        veh.setSerialNumber("");
                    }else{
                        veh.setSerialNumber(rs.getString("serial_number"));
                    }
                    if(rs.getString("org_id") == null){
                        veh.setOrgId("");
                    }else{
                        veh.setOrgId(rs.getString("org_id"));
                    }
                    if(rs.getString("vehicle_category_type") == null){
                        veh.setVehicleCategoryType("");
                    }else{
                        veh.setVehicleCategoryType(rs.getString("vehicle_category_type"));
                    }
                    if(rs.getString("range") == null){
                        veh.setRange("");
                    }else{
                        veh.setRange(rs.getString("range"));
                    }
                    if(rs.getString("model") == null){
                        veh.setModel("");
                    }else{
                        veh.setModel(rs.getString("model"));
                    }
                    
                    if(rs.getString("party_name") == null){
                        veh.setPartyName("");
                    }else{
                        veh.setPartyName(rs.getString("party_name"));
                    }
                    if(rs.getString("last_mileage") == null){
                        veh.setLastMileage("");
                    }else{
                        veh.setLastMileage(rs.getString("last_mileage"));
                    }
                    if(rs.getString("last_sr_type") == null){
                        veh.setLastSrType("");
                    }else{
                        veh.setLastSrType(rs.getString("last_sr_type"));
                    }
                    if(rs.getString("last_sr_service_advisor") == null){
                        veh.setLastSrServiceAdvisor("");
                    }else{
                        veh.setLastSrServiceAdvisor(rs.getString("last_sr_service_advisor"));
                    }
                    if(rs.getString("service_advisor_id") == null){
                        veh.setServiceAdvisorId("");
                    }else{
                        veh.setServiceAdvisorId(rs.getString("service_advisor_id"));
                    }
                    if(rs.getString("last_service_branch") == null){
                        veh.setLastServiceBranch("");
                    }else{
                        veh.setLastServiceBranch(rs.getString("last_service_branch"));
                    }
                    if(rs.getString("last_incident_id") == null){
                        veh.setLastIncidentId("");
                    }else{
                        veh.setLastIncidentId(rs.getString("last_incident_id"));
                    }
                    if(rs.getString("next_service_due_sr_type_id") == null){
                        veh.setNextServiceDueSrTypeId("");
                    }else{
                        veh.setNextServiceDueSrTypeId(rs.getString("next_service_due_sr_type_id"));
                    }
                    if(rs.getString("service_interval") == null){
                        veh.setServiceInterval("");
                    }else{
                        veh.setServiceInterval(rs.getString("service_interval"));
                    }
                    if(rs.getString("brand") == null){
                        veh.setBrand("");
                    }else{
                        veh.setBrand(rs.getString("brand"));
                    }
                    if(rs.getString("customer_phone_no") == null){
                        veh.setCustomerPhoneNumber("");
                    }else{
                        veh.setCustomerPhoneNumber(rs.getString("customer_phone_no"));
                    }
                    if(rs.getString("last_received_date") == null){
                        veh.setLastReceivedDate("");
                    }else{
                        veh.setLastReceivedDate(rs.getString("last_received_date"));
                    }
                    if(rs.getString("last_service_branch_code") == null){
                        veh.setLastServiceBranchCode("");
                    }else{
                        veh.setLastServiceBranchCode(rs.getString("last_service_branch_code"));
                    }
                    
                    if(rs.getString("next_service_due_sr_type") == null){
                        veh.setNextServiceDueSrType("");
                    }else{
                        veh.setNextServiceDueSrType(rs.getString("next_service_due_sr_type"));
                    }
                    
                    //new fields - set once procedure changed
                    if(rs.getString("insurance_end_date") == null){
                        veh.setInsuranceExpiryDate("");
                    }else{
                        veh.setInsuranceExpiryDate(rs.getString("insurance_end_date"));
                    }
                    if(rs.getString("owned_since") == null){
                        veh.setOwnedSince("");
                    }else{
                        veh.setOwnedSince(rs.getString("owned_since"));
                    }
                    if(rs.getString("serviceAdvisorContactNumber") == null){
                        veh.setServiceAdvisorContactNumber("");
                    }else{
                        veh.setServiceAdvisorContactNumber(rs.getString("serviceAdvisorContactNumber"));
                    }
                    if(rs.getString("modelYear") == null){
                        veh.setModelYear("");
                    }else{
                        veh.setModelYear(rs.getString("modelYear"));
                    }
                    
                    vehList.add(veh);
                }
            }
            response.setCustomerVehicles(vehList.toArray(new CustomerVehicle[vehList.size()]));
            
    //            log.setAttribute2("instanceId:"+instanceId);
            logger.callAudit(log);
            
            }else{
            response.setRetStatus("E");
            response.setRetMsg(okExecuteResponse.getMessage());
            return response;
        }
            
        } catch (Exception e) {
            log.setErrorMessage(e.toString());
            exceptionLogger.callAudit(log);
            e.printStackTrace();

        } finally {
            try {
                if(rs!=null){
                rs.close();
                }
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

    @GET
    @Path("getAvailability")
    public AvailabilityResponse getAvailability(@QueryParam("instanceId") String instanceId,@QueryParam("serviceAdvisorFlag") String serviceAdvisorFlag,
                                                @QueryParam("serviceAdvisorId") String serviceAdvisorId,@QueryParam("groupId") String groupId,
                                                @QueryParam("locationCode") String locationCode,@QueryParam("appointmentDate") String appointmentDate,
                                                @QueryParam("customerWaiting") String customerWaiting,
                                                @HeaderParam("appName") String appName) 
    {
        AvailabilityResponse response = new AvailabilityResponse();
        ArrayList<Availability> slotsList = new ArrayList<Availability>();
//        System.out.println("data inputs : instanceId - "+instanceId+" , serviceAdvisorFlag - "+serviceAdvisorFlag+" ,serviceAdvisorId - "+serviceAdvisorId+" ,groupId - "+groupId+
//                           " ,locationCode - "+locationCode+" , appointmentDate - "+appointmentDate+" ,customerWaiting - "+customerWaiting);
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        LogData log = new LogData();
        String successResponse=null;
        GenericResponse okExecuteResponse = new GenericResponse();


        conn = DBConnect.getConnection();
        //where to pass vendor value??
//        System.out.println("appName in internal getAvailability is : "+appName);

        try {
//            successResponse = successService.excuteService("getAvailability", "EN");
//            if (successResponse.equals("S")) 
            okExecuteResponse = successService.okToExcuteService(appName, "Get Availability", "123");
            if (okExecuteResponse.getStatus().equalsIgnoreCase("S")) 
            {
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("getAvailability");
            log.setUserName("");
            log.setAttribute1("instanceId:"+instanceId);
            log.setServiceName("getAvailability");
            log.setVendorName("VendorVal:"+appName);
            
                stmt =
                    conn.prepareCall("{call xxlp_wks_pkg.get_availability(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
               
            if(instanceId!=null && instanceId!="null" && instanceId!="" && !instanceId.isEmpty()){
                System.out.println("instanceId : "+Long.parseLong(instanceId));
                stmt.setLong(1, Long.parseLong(instanceId));
            }else{
                System.out.println("instanceId is null");
                stmt.setNull(1, Types.FLOAT);
            }
            if(serviceAdvisorFlag!=null && serviceAdvisorFlag!="" && !serviceAdvisorFlag.isEmpty()){
                System.out.println("serviceAdvisorFlag : "+serviceAdvisorFlag);
                stmt.setString(2, serviceAdvisorFlag);
            }else{
                System.out.println("serviceAdvisorFlag is null");
                stmt.setNull(2, Types.VARCHAR);
            }
            if(serviceAdvisorId!=null && serviceAdvisorId!="null" && serviceAdvisorId!="" && !serviceAdvisorId.isEmpty()){
                System.out.println("serviceAdvisorId : "+Long.parseLong(serviceAdvisorId));
                stmt.setLong(3, Long.parseLong(serviceAdvisorId));
            }else{
                System.out.println("serviceAdvisorId is null");
                stmt.setNull(3, Types.FLOAT);
            }
            if(groupId!=null && groupId!="null" && groupId!="" && !groupId.isEmpty()){
                System.out.println("groupId : "+Long.parseLong(groupId));
                stmt.setLong(4, Long.parseLong(groupId));
            }else{
                System.out.println("groupId is null");
                stmt.setNull(4, Types.FLOAT);
            }
            if(locationCode!=null && locationCode!="" && !locationCode.isEmpty()){
                System.out.println("locationCode : "+locationCode);
                stmt.setString(5, locationCode);
            }else{
                System.out.println("locationCode is null");
                stmt.setNull(5, Types.VARCHAR);
            }
            if(appointmentDate!=null && appointmentDate!="" && !appointmentDate.isEmpty()){
                System.out.println("appointmentDate : "+CommonUtil.stringToTimestamp(appointmentDate));
                stmt.setTimestamp(6, CommonUtil.stringToTimestamp(appointmentDate));
            }else{
                System.out.println("appointmentDate is null");
                stmt.setNull(6, Types.TIMESTAMP);
            }
            if(customerWaiting!=null && customerWaiting!="" && !customerWaiting.isEmpty()){
                System.out.println("customerWaiting : "+customerWaiting);
                stmt.setString(7, customerWaiting);
            }else{
                System.out.println("customerWaiting is null");
                stmt.setNull(7, Types.VARCHAR);
            }
                System.out.println("attr 8,9,10,11,12 is null");
            stmt.setNull(8, Types.VARCHAR);
            stmt.setNull(9, Types.VARCHAR);
            stmt.setNull(10, Types.VARCHAR);
            stmt.setNull(11, Types.VARCHAR);
            stmt.setNull(12, Types.VARCHAR);
            
            
            stmt.registerOutParameter(13, Types.VARCHAR);
            stmt.registerOutParameter(14, OracleTypes.CURSOR);
                
            rs=stmt.executeQuery();
            
            if(stmt.getString(13)!=null){
            response.setAvailable(stmt.getString(13));
            }else{
                response.setAvailable("");
            }
            
            if (stmt.getObject(14) != null) {
                rs = (ResultSet) stmt.getObject(14);
                
                while (rs.next()) {
                    Availability slot = new Availability();
                    if(rs.getString("service_advisor_id") == null){
                        slot.setServiceAdvisorId("");
                    }else{
                        slot.setServiceAdvisorId(rs.getString("service_advisor_id"));
                    }
                    if(rs.getString("service_advior_name") == null){
                        slot.setServiceAdvisorName("");
                    }else{
                        slot.setServiceAdvisorName(rs.getString("service_advior_name"));
                    }
                    if(rs.getString("group_id") == null){
                        slot.setGroupId("");
                    }else{
                        slot.setGroupId(rs.getString("group_id"));
                    }
                    if(rs.getString("appointment_date") == null){
                        slot.setAppointmentDate("");
                    }else{
                        slot.setAppointmentDate(rs.getString("appointment_date"));
                    }
                    if(rs.getString("slots_available") == null){
                        slot.setSlotsAvailable("");
                    }else{
                        slot.setSlotsAvailable(rs.getString("slots_available"));
                    }
                    if(rs.getString("attribute_category") == null){
                        slot.setAttributeCategory("");
                    }else{
                        slot.setAttributeCategory(rs.getString("attribute_category"));
                    }
                    if(rs.getString("attribute1") == null){
                        slot.setAttribute1("");
                    }else{
                        slot.setAttribute1(rs.getString("attribute1"));
                    }
                    if(rs.getString("attribute2") == null){
                        slot.setAttribute2("");
                    }else{
                        slot.setAttribute2(rs.getString("attribute2"));
                    }
                    if(rs.getString("attribute3") == null){
                        slot.setAttribute3("");
                    }else{
                        slot.setAttribute3(rs.getString("attribute3"));
                    }
                    if(rs.getString("attribute4") == null){
                        slot.setAttribute4("");
                    }else{
                        slot.setAttribute4(rs.getString("attribute4"));
                    }
                    if(rs.getString("attribute5") == null){
                        slot.setAttribute5("");
                    }else{
                        slot.setAttribute5(rs.getString("attribute5"));
                    }
                   slotsList.add(slot);
                }
            }
            response.setSlots(slotsList.toArray(new Availability[slotsList.size()]));
            response.setRetCode("S");
            response.setRetMsg("Success");
            
            log.setAttribute2("instanceId:"+instanceId);
            logger.callAudit(log);
            
            }else{
                response.setRetMsg(okExecuteResponse.getMessage());
                response.setRetCode("E");
                return response;
            }
            
        } catch (Exception e) {
            log.setErrorMessage(e.toString());
            exceptionLogger.callAudit(log);
            e.printStackTrace();

        } finally {
            try {
                if(rs!=null){
                rs.close();
                }
            } catch (Exception s) {
            s.printStackTrace();
                }
            try {
                if(stmt!=null){
                stmt.close();
                }
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
    @Path("createAppointment")
    public CreateAppointmentResponse createAppointment(CreateAppointmentRequest req,
                                                       @HeaderParam("appName") String appName) 
    {
//        System.out.println("in create appt internal service");
//        System.out.println("params are: instanceId - "+req.getInstanceId()+" , contractId - "+req.getContractId()+" , incidentTypeId - "+req.getIncidentTypeId());
        CreateAppointmentResponse response = new CreateAppointmentResponse();

        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        LogData log = new LogData();
        String successResponse=null;
        GenericResponse okExecuteResponse = new GenericResponse();


        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal createAppointment is : "+appName);

        try {
//            successResponse = successService.excuteService("createAppointment", "EN");
//            if (successResponse.equals("S")) 
            okExecuteResponse = successService.okToExcuteService(appName, "Create Appointment", "123");
            if (okExecuteResponse.getStatus().equalsIgnoreCase("S")) 
            {
            
                log.setAppCode("AUTOTRUST");
                log.setAppName("AutotrustServices");
                log.setDocCode("createAppointment");
                log.setUserName("");
                log.setAttribute1("instanceId:"+req.getInstanceId());
                log.setAttribute2("incidentTypeID:"+req.getIncidentTypeId());
                log.setServiceName("createAppointment");
                log.setVendorName("VendorVal:"+appName);
            
            stmt =
                conn.prepareCall("{call xxdmv_web_appointments_pkg.create_appointment(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            
            
            if(req.getInstanceId()!=null && req.getInstanceId()!="null" && req.getInstanceId()!="" && !req.getInstanceId().isEmpty()){
                stmt.setLong(1, Long.parseLong(req.getInstanceId()));
            }else{
                stmt.setNull(1, Types.FLOAT);
            }
            if(req.getContractId()!=null && req.getContractId()!="" && !req.getContractId().isEmpty()){
                stmt.setLong(2, Long.parseLong(req.getContractId()));
            }else{
                stmt.setNull(2, Types.FLOAT);
            }
            if(req.getIncidentTypeId()!=null && req.getIncidentTypeId()!="null" && req.getIncidentTypeId()!="" && !req.getIncidentTypeId().isEmpty()){
                stmt.setLong(3, Long.parseLong(req.getIncidentTypeId()));
            }else{
                stmt.setNull(3, Types.FLOAT);
            }
            if(req.getResourceId()!=null && req.getResourceId()!="null" && req.getResourceId()!="" && !req.getResourceId().isEmpty()){
                stmt.setLong(4, Long.parseLong(req.getResourceId()));
            }else{
                stmt.setNull(4, Types.FLOAT);
            }
            if(req.getGroupId()!=null && req.getGroupId()!="" && !req.getGroupId().isEmpty()){
                stmt.setLong(5, Long.parseLong(req.getGroupId()));
            }else{
                stmt.setNull(5, Types.FLOAT);
            }
            if(req.getAppointmentDate()!=null && req.getAppointmentDate()!="" && !req.getAppointmentDate().isEmpty()){
                
                stmt.setDate(6, CommonUtil.stringToDate(req.getAppointmentDate()));
            }else{
                stmt.setNull(6, Types.DATE);
            }
            if(req.getComments()!=null && req.getComments()!="" && !req.getComments().isEmpty()){
                stmt.setString(7, req.getComments());
            }else{
                stmt.setNull(7, Types.VARCHAR);
            }
            if(req.getRentalCar()!=null && req.getRentalCar()!="" && !req.getRentalCar().isEmpty()){
                stmt.setString(8, req.getRentalCar());
            }else{
                stmt.setNull(8, Types.VARCHAR);
            }
            if(req.getCustomerWaiting()!=null && req.getCustomerWaiting()!="" && !req.getCustomerWaiting().isEmpty()){
                stmt.setString(9, req.getCustomerWaiting());
            }else{
                stmt.setNull(9, Types.VARCHAR);
            }
            if(req.getContext()!=null && req.getContext()!="" && !req.getContext().isEmpty()){
                stmt.setString(10, req.getContext());
            }else{
                stmt.setNull(10, Types.VARCHAR);
            }
            if(req.getSendReminder()!=null && req.getSendReminder()!="" && !req.getSendReminder().isEmpty()){
                stmt.setString(11, req.getSendReminder());
            }else{
                stmt.setNull(11, Types.VARCHAR);
            }
            if(req.getCurrentMileage()!=null && req.getCurrentMileage()!="" && !req.getCurrentMileage().isEmpty()){
                stmt.setLong(12, Long.parseLong(req.getCurrentMileage()));
            }else{
                stmt.setNull(12, Types.FLOAT);
            }
            if(req.getPrefServiceAdvisorFlag()!=null && req.getPrefServiceAdvisorFlag()!="" && !req.getPrefServiceAdvisorFlag().isEmpty()){
                stmt.setString(13, req.getPrefServiceAdvisorFlag());
            }else{
                stmt.setNull(13, Types.VARCHAR);
            }
            
            stmt.setNull(14, Types.VARCHAR);
            stmt.setNull(15, Types.VARCHAR);
            stmt.setNull(16, Types.VARCHAR);
            //defaulting attr4 and attr5 to MRS Link and WEBSITE
    //            stmt.setNull(17, Types.VARCHAR);
    //            stmt.setNull(18, Types.VARCHAR);
            stmt.setString(17, "MRS Link");
            stmt.setString(18, "WEBSITE");
            
            stmt.registerOutParameter(19, Types.FLOAT);
            stmt.registerOutParameter(20, Types.VARCHAR);
            stmt.registerOutParameter(21, Types.FLOAT);
            stmt.registerOutParameter(22, Types.VARCHAR);
            stmt.registerOutParameter(23, Types.VARCHAR);
            stmt.registerOutParameter(24, Types.VARCHAR);
            stmt.registerOutParameter(25, Types.VARCHAR);
                
            rs=stmt.executeQuery();
            System.out.println("internal service create appt executed");
            
            if(stmt.getString(19)!=null){
                System.out.println("incident id is : "+stmt.getString(19));
            response.setIncidentId(stmt.getString(19));
            }else{
                response.setIncidentId("");
            }
            if(stmt.getString(20)!=null){
                System.out.println("incident no is : "+stmt.getString(20));
            response.setIncidentNumber(stmt.getString(20));
            }else{
                response.setIncidentNumber("");
            }
            if(stmt.getString(21)!=null){
                System.out.println("service advisor id is : "+stmt.getString(21));
            response.setServiceAdvisorId(stmt.getString(21));
            }else{
                response.setServiceAdvisorId("");
            }
            if(stmt.getString(22)!=null){
                System.out.println("service advisor name is : "+stmt.getString(22));
            response.setServiceAdvisorName(stmt.getString(22));
            }else{
                response.setServiceAdvisorName("");
            }
            if(stmt.getString(23)!=null){
                System.out.println("service advisor contact no is : "+stmt.getString(23));
            response.setServiceAdvisorContactNumber(stmt.getString(23));
            }else{
                response.setServiceAdvisorContactNumber("");
            }
            if(stmt.getString(24)!=null){
                System.out.println("ret code is : "+stmt.getString(24));
            response.setRetCode(stmt.getString(24));
            }else{
                response.setRetCode("");
            }
            if(stmt.getString(25)!=null){
                System.out.println("ret msg is : "+stmt.getString(25));
            response.setRetMsg(stmt.getString(25));
            }else{
                response.setRetMsg("");
            }
            
            
                logger.callAudit(log);
            
            }else{
            response.setRetCode("E");
            response.setRetMsg(okExecuteResponse.getMessage());
            return response;
        }
            
        } catch (Exception e) {
            System.out.println("in catch block of  create appt");
            log.setErrorMessage(e.toString());
                exceptionLogger.callAudit(log);
            e.printStackTrace();

        } finally {
            try {
                if(rs!=null){
                rs.close();
                }
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
    @Path("getBranches")
    public BranchesResponse getBranches(GetBranchesRequest req, @HeaderParam("appName") String appName) 
    {
        BranchesResponse response = new BranchesResponse();
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        LogData log = new LogData();
        String successResponse=null;
        GenericResponse okExecuteResponse = new GenericResponse();
        ArrayList<IncidentObject> incidentList = new ArrayList<IncidentObject>();
        ArrayList<LocationObject> locationList = new ArrayList<LocationObject>();   
        ArrayList<BranchTimeObject> branchTimeList = new ArrayList<BranchTimeObject>(); 
        ArrayList<BranchHolidaysObject> branchHolidayList = new ArrayList<BranchHolidaysObject>();
                                                                                            

        conn = DBConnect.getConnection();

        try {
//            successResponse = successService.excuteService("getBranches", "EN");
//            if (successResponse.equals("S"))
            okExecuteResponse = successService.okToExcuteService(appName, "Get Branches", "123");
            if (okExecuteResponse.getStatus().equalsIgnoreCase("S")) 
            {
                
                log.setAppCode("AUTOTRUST");
                log.setAppName("AutotrustServices");
                log.setDocCode("getBranches");
                log.setUserName("");
                log.setAttribute1("instanceId:"+req.getInstanceId());
                log.setServiceName("getBranches");
                log.setVendorName("VendorVal:"+appName);
                
                stmt = conn.prepareCall("{call xxdmv_cpo_autotrust_mob_pkg.get_branches(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    
                stmt.setLong(1, Long.parseLong(req.getInstanceId()));
                stmt.setString(2, req.getIdentifier());
                stmt.setString(3, req.getAttribute1());
                stmt.setString(4, req.getAttribute2());
                stmt.setString(5, req.getAttribute3());
                stmt.setString(6, req.getAttribute4());
                stmt.setString(7, req.getAttribute5());
                stmt.registerOutParameter(8, OracleTypes.CURSOR);
                stmt.registerOutParameter(9, OracleTypes.CURSOR);
                stmt.registerOutParameter(10, OracleTypes.CURSOR);
                stmt.registerOutParameter(11, OracleTypes.CURSOR);
                stmt.registerOutParameter(12, Types.VARCHAR);
                stmt.registerOutParameter(13, Types.VARCHAR);
                stmt.registerOutParameter(14, Types.VARCHAR);
                stmt.registerOutParameter(15, Types.VARCHAR);
                stmt.registerOutParameter(16, Types.VARCHAR);
                stmt.registerOutParameter(17, Types.VARCHAR);
                stmt.registerOutParameter(18, Types.VARCHAR);
                
                rs = stmt.executeQuery();
                
                if(stmt.getString(12)!=null){
                response.setOutput1(stmt.getString(12));
                }else{
                    response.setOutput1("");
                }
                if(stmt.getString(13)!=null){
                response.setOutput2(stmt.getString(13));
                }else{
                    response.setOutput2("");
                }
                if(stmt.getString(14)!=null){
                response.setOutput3(stmt.getString(14));
                }else{
                    response.setOutput3("");
                }
                if(stmt.getString(15)!=null){
                response.setOutput4(stmt.getString(15));
                }else{
                    response.setOutput4("");
                }
                if(stmt.getString(16)!=null){
                response.setOutput5(stmt.getString(16));
                }else{
                    response.setOutput5("");
                }
                if(stmt.getString(17)!=null){
                response.setRetCode(stmt.getString(17));
                }else{
                    response.setRetCode("");
                }
                if(stmt.getString(18)!=null){
                response.setRetMsg(stmt.getString(18));
                }else{
                    response.setRetMsg("");
                }
                
                //populating incidents
                
                if (stmt.getObject(8) != null) {
                    rs = (ResultSet) stmt.getObject(8);
                    while (rs.next()) {
                        IncidentObject incident = new IncidentObject();
                        if(rs.getString("INCIDENT_TYPE_ID") == null){
                            incident.setIncicdentTypeId("");
                        }else{
                            incident.setIncicdentTypeId(rs.getString("INCIDENT_TYPE_ID"));
                        }
//                        if(rs.getString("INCIDENT_SUBTYPE") == null){
//                            incident.setIncidentSubtype("");
//                        }else{
//                            incident.setIncidentSubtype(rs.getString("INCIDENT_SUBTYPE"));
//                        }
                        if(rs.getString("NAME") == null){
                            incident.setName("");
                        }else{
                            incident.setName(rs.getString("NAME"));
                        }
                        if(rs.getString("DESCRIPTION") == null){
                            incident.setDescription("");
                        }else{
                            incident.setDescription(rs.getString("DESCRIPTION"));
                        }
//                        if(rs.getString("STATUS_GROUP_ID") == null){
//                            incident.setStatusGroupId("");
//                        }else{
//                            incident.setStatusGroupId(rs.getString("STATUS_GROUP_ID"));
//                        }
                        if(rs.getString("ATTRIBUTE1") == null){
                            incident.setAttribute1("");
                        }else{
                            incident.setAttribute1(rs.getString("ATTRIBUTE1"));
                        }
                        if(rs.getString("ATTRIBUTE2") == null){
                            incident.setAttribute2("");
                        }else{
                            incident.setAttribute2(rs.getString("ATTRIBUTE2"));
                        }
                        if(rs.getString("ATTRIBUTE3") == null){
                            incident.setAttribute3("");
                        }else{
                            incident.setAttribute3(rs.getString("ATTRIBUTE3"));
                        }
                        if(rs.getString("ATTRIBUTE4") == null){
                            incident.setAttribute4("");
                        }else{
                            incident.setAttribute4(rs.getString("ATTRIBUTE4"));
                        }
                        if(rs.getString("ATTRIBUTE5") == null){
                            incident.setAttribute5("");
                        }else{
                            incident.setAttribute5(rs.getString("ATTRIBUTE5"));
                        }
                        if(rs.getString("ATTRIBUTE6") == null){
                            incident.setAttribute6("");
                        }else{
                            incident.setAttribute6(rs.getString("ATTRIBUTE6"));
                        }
                        if(rs.getString("ATTRIBUTE7") == null){
                            incident.setAttribute7("");
                        }else{
                            incident.setAttribute7(rs.getString("ATTRIBUTE7"));
                        }
                        if(rs.getString("ATTRIBUTE8") == null){
                            incident.setAttribute8("");
                        }else{
                            incident.setAttribute8(rs.getString("ATTRIBUTE8"));
                        }
                        if(rs.getString("ATTRIBUTE9") == null){
                            incident.setAttribute9("");
                        }else{
                            incident.setAttribute9(rs.getString("ATTRIBUTE9"));
                        }
                        if(rs.getString("ATTRIBUTE10") == null){
                            incident.setAttribute10("");
                        }else{
                            incident.setAttribute10(rs.getString("ATTRIBUTE10"));
                        }
                        incidentList.add(incident);
                    }
                    
                }else{
                    
                }
                response.setIncidents(incidentList.toArray(new IncidentObject[incidentList.size()]));
                
                //populating locations
                
                if (stmt.getObject(9) != null) {
                    rs = (ResultSet) stmt.getObject(9);
                    while (rs.next()) {
                        LocationObject location = new LocationObject();
                        if(rs.getString("LOCATION_NAME") == null){
                            location.setLocationName("");
                        }else{
                            location.setLocationName(rs.getString("LOCATION_NAME"));
                        }
                        if(rs.getString("LOCATION_CODE") == null){
                            location.setLocationCode("");
                        }else{
                            location.setLocationCode(rs.getString("LOCATION_CODE"));
                        }
                        if(rs.getString("ORG_ID") == null){
                            location.setOrgId("");
                        }else{
                            location.setOrgId(rs.getString("ORG_ID"));
                        }
                        if(rs.getString("SLOT_DURATION") == null){
                            location.setSlotDuration("");
                        }else{
                            location.setSlotDuration(rs.getString("SLOT_DURATION"));
                        }
//                        if(rs.getString("TIME_SLOT") == null){
//                            location.setSlotDuration("");
//                        }else{
//                            location.setSlotDuration(rs.getString("TIME_SLOT"));
//                        }
                        if(rs.getString("ATTRIBUTE_CATEGORY") == null){
                            location.setAttributeCategory("");
                        }else{
                            location.setAttributeCategory(rs.getString("ATTRIBUTE_CATEGORY"));
                        }
                        if(rs.getString("ATTRIBUTE1") == null){
                            location.setAttribute1("");
                        }else{
                            location.setAttribute1(rs.getString("ATTRIBUTE1"));
                        }
                        if(rs.getString("ATTRIBUTE2") == null){
                            location.setAttribute2("");
                        }else{
                            location.setAttribute2(rs.getString("ATTRIBUTE2"));
                        }
                        if(rs.getString("ATTRIBUTE3") == null){
                            location.setAttribute3("");
                        }else{
                            location.setAttribute3(rs.getString("ATTRIBUTE3"));
                        }
                        if(rs.getString("ATTRIBUTE4") == null){
                            location.setAttribute4("");
                        }else{
                            location.setAttribute4(rs.getString("ATTRIBUTE4"));
                        }
                        if(rs.getString("ATTRIBUTE5") == null){
                            location.setAttribute5("");
                        }else{
                            location.setAttribute5(rs.getString("ATTRIBUTE5"));
                        }
                        if(rs.getString("ATTRIBUTE6") == null){
                            location.setAttribute6("");
                        }else{
                            location.setAttribute6(rs.getString("ATTRIBUTE6"));
                        }
                        if(rs.getString("ATTRIBUTE7") == null){
                            location.setAttribute7("");
                        }else{
                            location.setAttribute7(rs.getString("ATTRIBUTE7"));
                        }
                        if(rs.getString("ATTRIBUTE8") == null){
                            location.setAttribute8("");
                        }else{
                            location.setAttribute8(rs.getString("ATTRIBUTE8"));
                        }
                        if(rs.getString("ATTRIBUTE9") == null){
                            location.setAttribute9("");
                        }else{
                            location.setAttribute9(rs.getString("ATTRIBUTE9"));
                        }
                        if(rs.getString("ATTRIBUTE10") == null){
                            location.setAttribute10("");
                        }else{
                            location.setAttribute10(rs.getString("ATTRIBUTE10"));
                        }
                        if(rs.getString("GROUP_ID") == null){
                            location.setGroupId("");
                        }else{
                            location.setGroupId(rs.getString("GROUP_ID"));
                        }
                        if(rs.getString("CUSTOMER_WAITING") == null){
                            location.setCustomerWaiting("");
                        }else{
                            location.setCustomerWaiting(rs.getString("CUSTOMER_WAITING"));
                        }
                        if(rs.getString("SERVICE_ADVISOR_QUOTA") == null){
                            location.setServiceAdvisorQuota("");
                        }else{
                            location.setServiceAdvisorQuota(rs.getString("SERVICE_ADVISOR_QUOTA"));
                        }
                        if(rs.getString("START_DATE") == null){
                            location.setStartDate("");
                        }else{
                            location.setStartDate(rs.getString("START_DATE"));
                        }
                        if(rs.getString("END_DATE") == null){
                            location.setEndDate("");
                        }else{
                            location.setEndDate(rs.getString("END_DATE"));
                        }
                        locationList.add(location);
                    }
                    
                }else{
                    
                }
                response.setLocations(locationList.toArray(new LocationObject[locationList.size()]));
                
            
                //populating branch holidays
                
                if (stmt.getObject(10) != null) {
                    rs = (ResultSet) stmt.getObject(10);
                    while (rs.next()) {
                        BranchHolidaysObject branchHoliday = new BranchHolidaysObject();
                        
                        if(rs.getString("LOCATION_CODE") == null){
                            branchHoliday.setLocationCode("");
                        }else{
                            branchHoliday.setLocationCode(rs.getString("LOCATION_CODE"));
                        }
                        if(rs.getString("GROUP_ID") == null){
                            branchHoliday.setGroupId("");
                        }else{
                            branchHoliday.setGroupId(rs.getString("GROUP_ID"));
                        }
                        if(rs.getString("DAY_OF_WEEK") == null){
                            branchHoliday.setDayOfWeek("");
                        }else{
                            branchHoliday.setDayOfWeek(rs.getString("DAY_OF_WEEK"));
                        }
                        if(rs.getString("DAY_NO") == null){
                            branchHoliday.setDayNo("");
                        }else{
                            branchHoliday.setDayNo(rs.getString("DAY_NO"));
                        }
//                        if(rs.getString("DATE") == null){
//                            branchHoliday.setDate("");
//                        }else{
//                            branchHoliday.setDate(rs.getString("DATE"));
//                        }
                        if(rs.getString("HOLIDAY_DATE") == null){
                            branchHoliday.setDate("");
                        }else{
                            branchHoliday.setDate(rs.getString("HOLIDAY_DATE"));
                        }
                        if(rs.getString("DAY_DESCRIPTION") == null){
                            branchHoliday.setDayDescription("");
                        }else{
                            branchHoliday.setDayDescription(rs.getString("DAY_DESCRIPTION"));
                        }
                        if(rs.getString("ATTRIBUTE_CATEGORY") == null){
                            branchHoliday.setAttributeCategory("");
                        }else{
                            branchHoliday.setAttributeCategory(rs.getString("ATTRIBUTE_CATEGORY"));
                        }
                        if(rs.getString("ATTRIBUTE1") == null){
                            branchHoliday.setAttribute1("");
                        }else{
                            branchHoliday.setAttribute1(rs.getString("ATTRIBUTE1"));
                        }
                        if(rs.getString("ATTRIBUTE2") == null){
                            branchHoliday.setAttribute2("");
                        }else{
                            branchHoliday.setAttribute2(rs.getString("ATTRIBUTE2"));
                        }
                        if(rs.getString("ATTRIBUTE3") == null){
                            branchHoliday.setAttribute3("");
                        }else{
                            branchHoliday.setAttribute3(rs.getString("ATTRIBUTE3"));
                        }
                        if(rs.getString("ATTRIBUTE4") == null){
                            branchHoliday.setAttribute4("");
                        }else{
                            branchHoliday.setAttribute4(rs.getString("ATTRIBUTE4"));
                        }
                        if(rs.getString("ATTRIBUTE5") == null){
                            branchHoliday.setAttribute5("");
                        }else{
                            branchHoliday.setAttribute5(rs.getString("ATTRIBUTE5"));
                        }
                        if(rs.getString("ATTRIBUTE6") == null){
                            branchHoliday.setAttribute6("");
                        }else{
                            branchHoliday.setAttribute6(rs.getString("ATTRIBUTE6"));
                        }
                        if(rs.getString("ATTRIBUTE7") == null){
                            branchHoliday.setAttribute7("");
                        }else{
                            branchHoliday.setAttribute7(rs.getString("ATTRIBUTE7"));
                        }
                        if(rs.getString("ATTRIBUTE8") == null){
                            branchHoliday.setAttribute8("");
                        }else{
                            branchHoliday.setAttribute8(rs.getString("ATTRIBUTE8"));
                        }
                        if(rs.getString("ATTRIBUTE9") == null){
                            branchHoliday.setAttribute9("");
                        }else{
                            branchHoliday.setAttribute9(rs.getString("ATTRIBUTE9"));
                        }
                        if(rs.getString("ATTRIBUTE10") == null){
                            branchHoliday.setAttribute10("");
                        }else{
                            branchHoliday.setAttribute10(rs.getString("ATTRIBUTE10"));
                        }
                        branchHolidayList.add(branchHoliday);
                    }
                    
                }else{
                    
                }
                response.setBranchHolidays(branchHolidayList.toArray(new BranchHolidaysObject[branchHolidayList.size()]));
            
                //populating branch times
                      
                if (stmt.getObject(11) != null) {
                    rs = (ResultSet) stmt.getObject(11);
                    while (rs.next()) {
                        BranchTimeObject branchTime = new BranchTimeObject();
                        
                        if(rs.getString("LOCATION_CODE") == null){
                            branchTime.setLocationCode("");
                        }else{
                            branchTime.setLocationCode(rs.getString("LOCATION_CODE"));
                        }
                        if(rs.getString("DAY_OF_WEEK") == null){
                            branchTime.setDayOfWeek("");
                        }else{
                            branchTime.setDayOfWeek(rs.getString("DAY_OF_WEEK"));
                        }
                        if(rs.getString("DAY_NO") == null){
                            branchTime.setDayNo("");
                        }else{
                            branchTime.setDayNo(rs.getString("DAY_NO"));
                        }
                        if(rs.getString("START_TIME") == null){
                            branchTime.setStartTime("");
                        }else{
                            branchTime.setStartTime(rs.getString("START_TIME"));
                        }
                        if(rs.getString("END_TIME") == null){
                            branchTime.setEndtime("");
                        }else{
                            branchTime.setEndtime(rs.getString("END_TIME"));
                        }
                        if(rs.getString("BREAK_FROM") == null){
                            branchTime.setBreakFrom("");
                        }else{
                            branchTime.setBreakFrom(rs.getString("BREAK_FROM"));
                        }
                        if(rs.getString("BREAK_TO") == null){
                            branchTime.setBreakTo("");
                        }else{
                            branchTime.setBreakTo(rs.getString("BREAK_TO"));
                        }
                        if(rs.getString("ATTRIBUTE_CATEGORY") == null){
                            branchTime.setAttributeCategory("");
                        }else{
                            branchTime.setAttributeCategory(rs.getString("ATTRIBUTE_CATEGORY"));
                        }
                        if(rs.getString("ATTRIBUTE1") == null){
                            branchTime.setAttribute1("");
                        }else{
                            branchTime.setAttribute1(rs.getString("ATTRIBUTE1"));
                        }
                        if(rs.getString("ATTRIBUTE2") == null){
                            branchTime.setAttribute2("");
                        }else{
                            branchTime.setAttribute2(rs.getString("ATTRIBUTE2"));
                        }
                        if(rs.getString("ATTRIBUTE3") == null){
                            branchTime.setAttribute3("");
                        }else{
                            branchTime.setAttribute3(rs.getString("ATTRIBUTE3"));
                        }
                        if(rs.getString("ATTRIBUTE4") == null){
                            branchTime.setAttribute4("");
                        }else{
                            branchTime.setAttribute4(rs.getString("ATTRIBUTE4"));
                        }
                        if(rs.getString("ATTRIBUTE5") == null){
                            branchTime.setAttribute5("");
                        }else{
                            branchTime.setAttribute5(rs.getString("ATTRIBUTE5"));
                        }
                        if(rs.getString("ATTRIBUTE6") == null){
                            branchTime.setAttribute6("");
                        }else{
                            branchTime.setAttribute6(rs.getString("ATTRIBUTE6"));
                        }
                        if(rs.getString("ATTRIBUTE7") == null){
                            branchTime.setAttribute7("");
                        }else{
                            branchTime.setAttribute7(rs.getString("ATTRIBUTE7"));
                        }
                        if(rs.getString("ATTRIBUTE8") == null){
                            branchTime.setAttribute8("");
                        }else{
                            branchTime.setAttribute8(rs.getString("ATTRIBUTE8"));
                        }
                        if(rs.getString("ATTRIBUTE9") == null){
                            branchTime.setAttribute9("");
                        }else{
                            branchTime.setAttribute9(rs.getString("ATTRIBUTE9"));
                        }
                        if(rs.getString("ATTRIBUTE10") == null){
                            branchTime.setAttribute10("");
                        }else{
                            branchTime.setAttribute10(rs.getString("ATTRIBUTE10"));
                        }
                        if(rs.getString("GROUP_ID") == null){
                            branchTime.setGroupId("");
                        }else{
                            branchTime.setGroupId(rs.getString("GROUP_ID"));
                        }
                        branchTimeList.add(branchTime);
                    }
                    
                }else{
                    
                }
                response.setBranchTimings(branchTimeList.toArray(new BranchTimeObject[branchTimeList.size()]));
                
                logger.callAudit(log);
            }else{
            response.setRetCode("E");
            response.setRetMsg(okExecuteResponse.getMessage());
            return response;
        }
            
        }catch(Exception e){
            log.setErrorMessage(e.getMessage());
            exceptionLogger.callAudit(log);
            e.printStackTrace();
        }finally {
            try {
                if(rs!=null){
                rs.close();
                }
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
    @Path("serviceAdvisors")
    public ServiceAdvisorResponse serviceAdvisors(ServiceAdvisorRequest req,
                                                       @HeaderParam("appName") String appName) 
    {
    //        System.out.println("in create appt internal service");
    //        System.out.println("params are: instanceId - "+req.getInstanceId()+" , contractId - "+req.getContractId()+" , incidentTypeId - "+req.getIncidentTypeId());
        ServiceAdvisorResponse response = new ServiceAdvisorResponse();
        ArrayList<ServiceAdvisorObject> serviceAdvisorList = new ArrayList<ServiceAdvisorObject>();

        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        LogData log = new LogData();
        String successResponse=null;
        GenericResponse okExecuteResponse = new GenericResponse();


        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal serviceAdvisors is : "+appName);

        try {
//            successResponse = successService.excuteService("serviceAdvisors", "EN");
//            if (successResponse.equals("S")) 
            okExecuteResponse = successService.okToExcuteService(appName, "Get Service Advisors", "123");
            if (okExecuteResponse.getStatus().equalsIgnoreCase("S")) 
            {
            
                log.setAppCode("AUTOTRUST");
                log.setAppName("AutotrustServices");
                log.setDocCode("serviceAdvisors");
                log.setUserName("");
                log.setAttribute1("groupId:"+req.getGroupId());
                log.setAttribute2("orgId:"+req.getOrgId());
                log.setServiceName("serviceAdvisors");
                log.setVendorName("VendorVal:"+appName);
            
            stmt =
                conn.prepareCall("{call xxdmv_cpo_autotrust_mob_pkg.get_service_advisor(?,?,?,?,?,?,?,?,?)}");
            
            
            if(req.getLocationCode()!=null && req.getLocationCode()!="null" && req.getLocationCode()!="" && !req.getLocationCode().isEmpty()){
                stmt.setString(1, req.getLocationCode());
            }else{
                stmt.setNull(1, Types.VARCHAR);
            }
            if(req.getGroupId()!=null && req.getGroupId()!="" && !req.getGroupId().isEmpty()){
                stmt.setLong(2, Long.parseLong(req.getGroupId()));
            }else{
                stmt.setNull(2, Types.FLOAT);
            }
            if(req.getOrgId()!=null && req.getOrgId()!="null" && req.getOrgId()!="" && !req.getOrgId().isEmpty()){
                stmt.setLong(3, Long.parseLong(req.getOrgId()));
            }else{
                stmt.setNull(3, Types.FLOAT);
            }
            if(req.getCustomerWaiting()!=null && req.getCustomerWaiting()!="null" && req.getCustomerWaiting()!="" && !req.getCustomerWaiting().isEmpty()){
                stmt.setString(4, req.getCustomerWaiting());
            }else{
                stmt.setNull(4, Types.VARCHAR);
            }
            if(req.getServiceAdvisorQuota()!=null && req.getServiceAdvisorQuota()!="" && !req.getServiceAdvisorQuota().isEmpty()){
                stmt.setString(5, req.getServiceAdvisorQuota());
            }else{
                stmt.setNull(5, Types.VARCHAR);
            }
            if(req.getServiceDateTime()!=null && req.getServiceDateTime()!="" && !req.getServiceDateTime().isEmpty()){
                
//                stmt.setTimestamp(6, CommonUtil.stringToTimestampDb(req.getServiceDateTime()));
                stmt.setString(6, req.getServiceDateTime());
            }else{
//                stmt.setNull(6, Types.TIMESTAMP);
                stmt.setNull(6, Types.VARCHAR);
            }
            
            stmt.registerOutParameter(7, OracleTypes.CURSOR);
            stmt.registerOutParameter(8, Types.VARCHAR);
            stmt.registerOutParameter(9, Types.VARCHAR);
                
            rs=stmt.executeQuery();
            

            if(stmt.getString(8)!=null){
            response.setRetCode(stmt.getString(8));
            }else{
                response.setRetCode("");
            }
            if(stmt.getString(9)!=null){
            response.setRetMsg(stmt.getString(9));
            }else{
                response.setRetMsg("");
            }
            
                if (stmt.getObject(7) != null) {
                    rs = (ResultSet) stmt.getObject(7);
                    while (rs.next()) {
                        ServiceAdvisorObject servAdvisor = new ServiceAdvisorObject();
//                        if(rs.getString("ADVISOR_ORG_ID") == null){
//                            servAdvisor.setAdvisorOrgId("");
//                        }else{
//                            servAdvisor.setAdvisorOrgId(rs.getString("ADVISOR_ORG_ID"));
//                        }
//                        if(rs.getString("ADVISOR_CODE") == null){
//                            servAdvisor.setAdvisorCode("");
//                        }else{
//                            servAdvisor.setAdvisorCode(rs.getString("ADVISOR_CODE"));
//                        }
//                        if(rs.getString("ADVISOR_ID") == null){
//                            servAdvisor.setAdvisorId("");
//                        }else{
//                            servAdvisor.setAdvisorId(rs.getString("ADVISOR_ID"));
//                        }
//                        if(rs.getString("ADVISOR_NAME") == null){
//                            servAdvisor.setAdvisorName("");
//                        }else{
//                            servAdvisor.setAdvisorName(rs.getString("ADVISOR_NAME"));
//                        }
                        if(rs.getString("SERVICE_ADVISOR_ID") == null){
                            servAdvisor.setServiceAdvisorId("");
                        }else{
                            servAdvisor.setServiceAdvisorId(rs.getString("SERVICE_ADVISOR_ID"));
                        }
                        if(rs.getString("SERVICE_ADVISOR_NAME") == null){
                            servAdvisor.setServiceAdvisorName("");
                        }else{
                            servAdvisor.setServiceAdvisorName(rs.getString("SERVICE_ADVISOR_NAME"));
                        }
                        if(rs.getString("GROUP_ID") == null){
                            servAdvisor.setGroupId("");
                        }else{
                            servAdvisor.setGroupId(rs.getString("GROUP_ID"));                  
                        }
                        if(rs.getString("APPOINTMENT_DATE") == null){
                            servAdvisor.setAppointmentDate("");
                        }else{
                            servAdvisor.setAppointmentDate(rs.getString("APPOINTMENT_DATE"));                  
                        }
                        if(rs.getString("ATTRIBUTE_CATEGORY") == null){
                            servAdvisor.setAttributeCategory("");
                        }else{
                            servAdvisor.setAttributeCategory(rs.getString("ATTRIBUTE_CATEGORY"));
                        }
                        if(rs.getString("ATTRIBUTE1") == null){
                            servAdvisor.setAttribute1("");
                        }else{
                            servAdvisor.setAttribute1(rs.getString("ATTRIBUTE1"));
                        }
                        if(rs.getString("ATTRIBUTE2") == null){
                            servAdvisor.setAttribute2("");
                        }else{
                            servAdvisor.setAttribute2(rs.getString("ATTRIBUTE2"));
                        }
                        if(rs.getString("ATTRIBUTE3") == null){
                            servAdvisor.setAttribute3("");
                        }else{
                            servAdvisor.setAttribute3(rs.getString("ATTRIBUTE3"));
                        }
                        if(rs.getString("ATTRIBUTE4") == null){
                            servAdvisor.setAttribute4("");
                        }else{
                            servAdvisor.setAttribute4(rs.getString("ATTRIBUTE4"));
                        }
                        if(rs.getString("ATTRIBUTE5") == null){
                            servAdvisor.setAttribute5("");
                        }else{
                            servAdvisor.setAttribute5(rs.getString("ATTRIBUTE5"));
                        }
//                        if(rs.getString("ATTRIBUTE6") == null){
//                            servAdvisor.setAttribute6("");
//                        }else{
//                            servAdvisor.setAttribute6(rs.getString("ATTRIBUTE6"));
//                        }
//                        if(rs.getString("ATTRIBUTE7") == null){
//                            servAdvisor.setAttribute7("");
//                        }else{
//                            servAdvisor.setAttribute7(rs.getString("ATTRIBUTE7"));
//                        }
//                        if(rs.getString("ATTRIBUTE8") == null){
//                            servAdvisor.setAttribute8("");
//                        }else{
//                            servAdvisor.setAttribute8(rs.getString("ATTRIBUTE8"));
//                        }
//                        if(rs.getString("ATTRIBUTE9") == null){
//                            servAdvisor.setAttribute9("");
//                        }else{
//                            servAdvisor.setAttribute9(rs.getString("ATTRIBUTE9"));
//                        }
//                        if(rs.getString("ATTRIBUTE10") == null){
//                            servAdvisor.setAttribute10("");
//                        }else{
//                            servAdvisor.setAttribute10(rs.getString("ATTRIBUTE10"));
//                        }
                        serviceAdvisorList.add(servAdvisor);
                    }
                    
                }else{
                    
                }
            response.setServiceAdvisors(serviceAdvisorList.toArray(new ServiceAdvisorObject[serviceAdvisorList.size()]));
            logger.callAudit(log);
            
            }else{
            response.setRetCode("E");
            response.setRetMsg(okExecuteResponse.getMessage());
            return response;
            }
            
        } catch (Exception e) {
            log.setErrorMessage(e.toString());
                exceptionLogger.callAudit(log);
            e.printStackTrace();

        } finally {
            try {
                if(rs!=null){
                rs.close();
                }
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
    
    @GET
    @Path("getReservationNumber")
    public GetReservationNumberResponse getReservationNumber(@QueryParam("enquiryId") String enquiryId, @HeaderParam("appName") String appName){
        GetReservationNumberResponse response = new GetReservationNumberResponse();
        Gson gson = new Gson();
        LogData log = new LogData();
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        conn = DBConnect.getConnection();
        System.out.println("in internal getReservationNumber ");
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal getReservationNumber is : "+appName);
        
        try{
//        successResponse = successService.excuteService("getReservationNumber", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Get Reservation Number", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
        {
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("getReservationNumber");
            log.setUserName("");
            log.setAttribute1("enquiryId:"+enquiryId);
            log.setServiceName("getReservationNumber");
            log.setVendorName("VendorVal:"+appName);
            
            System.out.println("Success service executed");

            stmt =
                conn.prepareCall("{call xxdmv_cpo_autotrust_mob_pkg.get_reservation_number(?,?,?,?,?)}");
            
            if(enquiryId!=null){
            stmt.setLong(1, Long.parseLong(enquiryId));
            }else{
            stmt.setNull(1, Types.FLOAT);
            }
            
            stmt.registerOutParameter(2, Types.VARCHAR);
            stmt.registerOutParameter(3, Types.VARCHAR);
            stmt.registerOutParameter(4, Types.VARCHAR);
            stmt.registerOutParameter(5, Types.VARCHAR);
            
            rs=stmt.executeQuery();
            
            if(stmt.getString(2)!=null){
            response.setReservationNumber(stmt.getString(2));
            }else{
            response.setReservationNumber("");
            }
            if(stmt.getString(3)!=null){
            response.setReservationStatus(stmt.getString(3));
            }else{
            response.setReservationStatus("");
            }
            if(stmt.getString(4)!=null){
            response.setRetCode(stmt.getString(4));
            }else{
            response.setRetCode("");
            }
            if(stmt.getString(5)!=null){
            response.setRetMsg(stmt.getString(5));
            }else{
            response.setRetMsg("");
            }
            logger.callAudit(log);
        }else{
            response.setRetCode("E");
            response.setRetMsg(okExecuteResponse.getMessage());
            return response;
        }
        
        }catch(Exception e){
            log.setErrorMessage(e.toString());
            exceptionLogger.callAudit(log);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null && rs!=null && conn!=null){
            stmt.close();
            rs.close();
            conn.close();
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
        return response;
        
    }
    
    
    @PUT
    @Path("getDeviceToken")
    public GetTokenIdResponse getDeviceToken(GetDeviceTokenRequest req, @HeaderParam("appName") String appName) {
        GetTokenIdResponse response = new GetTokenIdResponse();
        ArrayList<DeviceObject> deviceList = new ArrayList<DeviceObject>();
        
        LogData log = new LogData();
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        String successResponse;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        
        try {
//            successResponse = successService.excuteService("getDeviceToken", "EN");
//            if (successResponse.equals("S")) 
            okExecuteResponse = successService.okToExcuteService(appName, "Get Device Token", "123");
            if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
            {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("getDeviceToken");
            log.setUserName("");
            log.setAttribute2("applicationName:"+req.getApplicationName());
            log.setAttribute1("appName:"+appName);
            log.setAttribute3("deviceType:"+req.getDeviceType());
            log.setServiceName("getDeviceToken");
            log.setVendorName("VendorVal:"+appName);
            
//            String query = null;
//            query = "SELECT token_id, device_type, user_id \n" + 
//                        "FROM \n" + 
//                        "xxaac.xxdmv_ioms_push_noti_lines \n" +
//                        "WHERE \n" + 
//                        "application_name = '" + req.getApplicationName() + "'"; 
//            
//            if(req.getUserId()!=null && req.getUserId().length>0){
//                UtilityClass util = new UtilityClass();
//                String userIdList = util.modifyArray(req.getUserId());
//                query = query + "\n AND \n" + 
//                        "user_id IN " + userIdList + " ";
//            }
//            if(req.getDeviceType()!=null){
//                query = query + "\n AND \n" + 
//                        "UPPER(device_type) = " + "UPPER('" + req.getDeviceType() + "')";
//            }
//            
//            System.out.println("query is : \n"+query);
//            
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery(query);
//            if(rs!=null){
//                
//                while(rs.next()){
//                    DeviceObject device = new DeviceObject();
//                    device.setDeviceType(rs.getString("device_type")!=null ? rs.getString("device_type") : "");
//                    device.setTokenId(rs.getString("token_id")!=null ? rs.getString("token_id") : "");
//                    device.setUserId(rs.getString("user_id")!=null ? rs.getString("user_id") : "");
//                    
//                    deviceList.add(device); 
//                }
//            }
//            response.setDeviceList(deviceList.toArray(new DeviceObject[deviceList.size()]));
//            response.setRetCode("S");
//            response.setRetMsg("Success");
                stmt =
                    conn.prepareCall("{call xxdmv_cpo_dealer_mob_pkg.get_user_token_details(?,?,?,?,?,?)}");
//                System.out.println("appname are : "+req.getApplicationName());
                stmt.setString(1, req.getApplicationName());
                UtilityClass util = new UtilityClass();
//                System.out.println("userIds are : "+util.stringArrayToString(req.getUserId()));
                stmt.setString(2, util.stringArrayToString(req.getUserId()));
//                System.out.println("devicetype are : "+req.getDeviceType());
                stmt.setString(3, req.getDeviceType());
            
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
//                deviceList.clear();
                while (rs.next()) {
                    DeviceObject dev = new DeviceObject();
                    if(rs.getString("TOKEN_ID") == null){
                        dev.setTokenId("");
                    }else{
                        dev.setTokenId(rs.getString("TOKEN_ID"));
                    }
                    if(rs.getString("USER_ID") == null){
                        dev.setUserId("");
                    }else{
                        dev.setUserId(rs.getString("USER_ID"));
                    }
                    if(rs.getString("DEVICE_TYPE") == null){
                        dev.setDeviceType("");
                    }else{
                        dev.setDeviceType(rs.getString("DEVICE_TYPE"));
                    }
                    deviceList.add(dev);
                }
                
            }else{
                
            }
            response.setDeviceList(deviceList.toArray(new DeviceObject[deviceList.size()]));
            logger.callAudit(log);   
        }else{
            response.setRetCode("E");
            response.setRetMsg(okExecuteResponse.getMessage());
            return response;
        }
        } catch (Exception e) {
            log.setErrorMessage(e.getMessage());
                exceptionLogger.callAudit(log);
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
    @Path("deviceRegistration")
    public GenericResponse deviceRegistration(RequestBean requestBean, @HeaderParam("appName") String appName){
        Gson g = new Gson();
        GenericResponse resBean=new GenericResponse();
        String retVal="";
        CallableStatement cstmt = null;
        Connection conn = null;
        String responsePayload = null;
        String successResponse=null;
        GenericResponse okExecuteResponse = new GenericResponse();
        LogData log = new LogData();
        try {
//            successResponse = successService.excuteService("getReservationNumber", "EN");
//            if (successResponse.equals("S")) 
            okExecuteResponse = successService.okToExcuteService(appName, "Device Registration", "123");
            if (okExecuteResponse.getStatus().equalsIgnoreCase("S")) 

            {
                log.setAppCode("AUTOTRUST");
                log.setAppName("AutotrustServices");
                log.setDocCode("deviceRegistration");
                log.setUserName("userId:"+requestBean.getUserId());
                log.setAttribute1("devicetype:"+requestBean.getDeviceType());
                log.setServiceName("deviceRegistration");
                log.setVendorName("VendorVal:"+appName);
                
                System.out.println("Success service executed");
                logger.callAudit(log);
                
            Context ctx = new InitialContext();
            DataSource datasource = (DataSource) ctx.lookup("jdbc/autotrustDS");
            conn = datasource.getConnection();
            if (conn != null) {
                cstmt = conn.prepareCall("{call XXDMV_OMS_NOTIFICATION_PKG.device_registration(?,?,?,?,?,?,?,?)}");
                cstmt.setString(1, String.valueOf(requestBean.getApplicationName()));
                cstmt.setString(2, String.valueOf(requestBean.getApplicationVersion()));
                cstmt.setString(3, String.valueOf(requestBean.getToken()));
                cstmt.setString(4, String.valueOf(requestBean.getDeviceId()));
                cstmt.setString(5, String.valueOf(requestBean.getDeviceType()));
//                if(requestBean.getUserId()!=null && !requestBean.getUserId().isEmpty()){
                    cstmt.setString(6, requestBean.getUserId());
//                    cstmt.setLong(6, Long.parseLong(requestBean.getUserId()));
//                }else{
//                    cstmt.setNull(6, Types.FLOAT);
//                }
                
                cstmt.registerOutParameter(7, Types.VARCHAR);
                cstmt.registerOutParameter(8, Types.VARCHAR);
                cstmt.execute();
                resBean.setStatus(cstmt.getString(7));
                resBean.setMessage(cstmt.getString(8));
                System.out.println("result : \n"+cstmt.getString(7)+ " , "+cstmt.getString(8));
                Gson oGSON = new GsonBuilder().serializeNulls().create();
                retVal = oGSON.toJson(resBean);
                oGSON = null;
                cstmt.close();
            }
                
            }else{
            resBean.setStatus("E");
            resBean.setMessage(okExecuteResponse.getMessage());
            return resBean;
        }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.setErrorMessage(ex.toString());
            exceptionLogger.callAudit(log);
        } finally {
            try {
                if(cstmt!=null)
                cstmt.close();
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
        return resBean;
    }
    
    //TODO - not used
    @PUT
    @Path("submitAddons")
    public GenericResponse submitAddons(SubmitAddonRequest req, @HeaderParam("appName") String appName) {
        Connection conn = null;
        GenericResponse response = new GenericResponse();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal submitAddons ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        CallableStatement stmt = null;
        ResultSet rs = null;
        //where to pass vendor value??
        System.out.println("appName in internal submitAddons is : "+appName);
        
        try{
//        successResponse = successService.excuteService("submitAddons", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Submit Addons", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("submitAddons");
            log.setUserName("");
            if(req.getAddons().length>0)
            {
            log.setAttribute1("orgId:"+req.getAddons()[0].getOrgId());
            log.setAttribute2("reservationId:"+req.getAddons()[0].getReservationId());
            }
            
            stmt =
                conn.prepareCall("{call xxdmv_cpo_autotrust_mob_pkg.insert_extra_fitting(?,?,?)}");
            
            if (req.getAddons() != null && req.getAddons().length>0) {
                System.out.println("req addons not empty");

                    @SuppressWarnings("deprecation")
                    StructDescriptor structdesc = StructDescriptor.createDescriptor("APPS.RESERVATION_VAL_REC_TYPE", conn);
                    @SuppressWarnings("deprecation")
                    ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor("APPS.RES_LINE_TBL", conn);
                int listSize = req.getAddons().length;
                
                
                Object[] rowsArray = new Object[listSize];
                
                for (int i=0; i<listSize; i++) {
                    AddonObject addonObject = req.getAddons()[i];
                    Object[] addonRecord = new Object[3]; 
                    
                    if(addonObject.getOrgId()!=null && addonObject.getOrgId()!="" && !addonObject.getOrgId().isEmpty()){
                        System.out.println("i is : "+i+" Integer org id is : "+ Integer.valueOf(addonObject.getOrgId()));
//                        addonRecord[0] = Long.parseLong(addonObject.getOrgId());
                        addonRecord[0] = Integer.valueOf(addonObject.getOrgId());
                    }else{
                        addonRecord[0] = Long.parseLong("0");
                    }
                    if(addonObject.getReservationId()!=null && addonObject.getReservationId()!="" && !addonObject.getReservationId().isEmpty()){
                        System.out.println("i is : "+i+" Integer reservation id is : "+ Integer.valueOf(addonObject.getReservationId()));
                        addonRecord[1] = Integer.valueOf(addonObject.getReservationId()); 
                    }else{
                        addonRecord[1] = Long.parseLong("0");
                    }
                    if(addonObject.getAddonId()!=null && addonObject.getAddonId()!="" && !addonObject.getAddonId().isEmpty()){
                        System.out.println("i is : "+i+" Integer Addon Id is : "+Integer.valueOf(addonObject.getAddonId()));
                        addonRecord[2] = Integer.valueOf(addonObject.getAddonId());
                    }else{
                        addonRecord[2] = Long.parseLong("0");
                    }

                        @SuppressWarnings("deprecation")
                        STRUCT addon_record = new STRUCT(structdesc, conn, addonRecord);
                    rowsArray[i] = addon_record;
                    addonObject = null;
                }
                
                for(int j=0;j<listSize;j++){
                    System.out.println("j : "+j);
                    System.out.println("row array id : "+rowsArray[j].toString());
                }

                    @SuppressWarnings("deprecation")
                    ARRAY oracleArray = new ARRAY(arraydesc, conn, rowsArray);
                System.out.println("addon array length is : "+rowsArray.length);
                stmt.setObject(1, oracleArray);
//                stmt.setObject("p_res_line_tbl", oracleArray);
                
            }else{
                // this case should not happen while invoking insert addons service
                System.out.println("req addons is EMPTY");
                stmt.setNull(1, Types.ARRAY, "APPS.RES_LINE_TBL");
//                stmt.setNull("p_res_line_tbl", Types.ARRAY, "APPS.RES_LINE_TBL");
            }
            
            stmt.registerOutParameter(2, Types.VARCHAR); 
            stmt.registerOutParameter(3, Types.VARCHAR);
//            stmt.registerOutParameter("x_ret_code", Types.VARCHAR); 
//            stmt.registerOutParameter("x_ret_msg", Types.VARCHAR);
//            stmt.registerOutParameter("x_ret_code", OracleTypes.VARCHAR); 
//            stmt.registerOutParameter("x_ret_msg", OracleTypes.VARCHAR);
           
            rs=stmt.executeQuery();
            
            if(stmt.getString(2)!=null){
                System.out.println("status is :"+stmt.getString(2));
            response.setStatus(stmt.getString(2));
            }else{
            response.setStatus("");
            }
            if(stmt.getString(3)!=null){
                System.out.println("message is :"+stmt.getString(3));
            response.setMessage(stmt.getString(3));
            }else{
            response.setMessage("");
            }
            
            logger.callAudit(log);
        }else{
            response.setStatus("E");
            response.setMessage(okExecuteResponse.getMessage());
            return response;
        }
        }catch(Exception e){
            log.setErrorMessage(e.toString());
                exceptionLogger.callAudit(log);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally{
            try{
            DBConnect.closeConnection(conn);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return response;                    
    }
    
    @PUT
    @Path("insertAddons")
    public GenericResponse insertAddons(InsertAddonRequest req, @HeaderParam("appName") String appName) {
        Connection conn = null;
        GenericResponse response = new GenericResponse();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal insertAddons ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        CallableStatement stmt = null;
        ResultSet rs = null;
        //where to pass vendor value??
        System.out.println("appName in internal insertAddons is : "+appName);
        
        try{
//        successResponse = successService.excuteService("insertAddons", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Insert Addons", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("insertAddons");
            log.setUserName("");
            log.setAttribute1("");
            log.setAttribute2("reservationNo:"+req.getReservationNo());

            
            stmt =
                conn.prepareCall("{call xxdmv_cpo_autotrust_mob_pkg.insert_extra_fitting(?,?,?,?)}");
            
            stmt.setLong(1, Long.parseLong(req.getReservationNo()));
//            stmt.setLong(2, Long.parseLong(req.getOrgId()));
            
            UtilityClass util = new UtilityClass();
            stmt.setString(2, util.stringArrayToString(req.getExtraFittingIds()));
            
            stmt.registerOutParameter(3, Types.VARCHAR); 
            stmt.registerOutParameter(4, Types.VARCHAR);
           
            rs=stmt.executeQuery();
            
            if(stmt.getString(3)!=null){
                System.out.println("status is :"+stmt.getString(3));
            response.setStatus(stmt.getString(3));
            }else{
            response.setStatus("");
            }
            if(stmt.getString(4)!=null){
                System.out.println("message is :"+stmt.getString(4));
            response.setMessage(stmt.getString(4));
            }else{
            response.setMessage("");
            }
            
            logger.callAudit(log);
        }else{
            response.setStatus("E");
            response.setMessage(okExecuteResponse.getMessage());
            return response;
        }
        }catch(Exception e){
            log.setErrorMessage(e.toString());
                exceptionLogger.callAudit(log);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally{
            try{
            DBConnect.closeConnection(conn);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return response;                    
    }
    
    @PUT
    @Path("statement")
    public MyStatementResponse statement(MyStatementRequest req, @HeaderParam("appName") String appName) {
        Connection conn = null;
        MyStatementResponse response = new MyStatementResponse();
        ArrayList<TxnDetailsObject> statementList = new ArrayList<TxnDetailsObject>();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal myStatement ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        CallableStatement stmt = null;
        ResultSet rs = null;
        //where to pass vendor value??
        System.out.println("appName in internal myStatement is : "+appName);
        
        try{
//        successResponse = successService.excuteService("myStatement", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Statements", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S")) 
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("myStatement");
            log.setUserName("id:"+req.getBuId());
            log.setAttribute1("fromDate:"+req.getFromDate());
            log.setAttribute2("toDate:"+req.getToDate());

            
            stmt =
                conn.prepareCall("{call xxdmv_cpo_autotrust_mob_pkg.get_txn_details(?,?,?,?,?,?)}");
            
            stmt.setLong(1, Long.parseLong(req.getBuId()));
            stmt.setString(2, req.getFromDate());
            stmt.setString(3, req.getToDate());
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
                    TxnDetailsObject txnObject = new TxnDetailsObject();
                    if(rs.getString("TXN_TYPE") == null){
                        txnObject.setTxnType("");
                    }else{
                        txnObject.setTxnType(rs.getString("TXN_TYPE"));
                    }
                    if(rs.getString("TXN_NUMBER") == null){
                        txnObject.setTxnNumber("");
                    }else{
                        txnObject.setTxnNumber(rs.getString("TXN_NUMBER"));
                    }
                    if(rs.getString("TXN_ID") == null){
                        txnObject.setTxnId("");
                    }else{
                        txnObject.setTxnId(rs.getString("TXN_ID"));
                    }
                    if(rs.getString("TXN_DATE") == null){
                        txnObject.setTxnDate("");
                    }else{
                        txnObject.setTxnDate(rs.getString("TXN_DATE"));
                    }
                    if(rs.getString("PLATE_CODE") == null){
                        txnObject.setPlateCode("");
                    }else{
                        txnObject.setPlateCode(rs.getString("PLATE_CODE"));
                    }
                    if(rs.getString("CUST_ACCOUNT_ID") == null){
                        txnObject.setCustAccountId("");
                    }else{
                        txnObject.setCustAccountId(rs.getString("CUST_ACCOUNT_ID"));
                    }
                    if(rs.getString("RANGE_CODE") == null){
                        txnObject.setRangeCode("");
                    }else{
                        txnObject.setRangeCode(rs.getString("RANGE_CODE"));
                    }
                    if(rs.getString("RANGE_DESCRIPTION") == null){
                        txnObject.setRangeDescription("");
                    }else{
                        txnObject.setRangeDescription(rs.getString("RANGE_DESCRIPTION"));
                    }
                    if(rs.getString("CUST_ACCOUNT_CODE") == null){
                        txnObject.setCustAccountCode("");
                    }else{
                        txnObject.setCustAccountCode(rs.getString("CUST_ACCOUNT_CODE"));
                    }
                    if(rs.getString("CUST_NAME") == null){
                        txnObject.setCustName("");
                    }else{
                        txnObject.setCustName(rs.getString("CUST_NAME"));
                    }
                    if(rs.getString("TXN_AMOUNT") == null){
                        txnObject.setTxnAmount("");
                    }else{
                        txnObject.setTxnAmount(rs.getString("TXN_AMOUNT"));
                    }
                    if(rs.getString("AMOUNT_PAID") == null){
                        txnObject.setAmountPaid("");
                    }else{
                        txnObject.setAmountPaid(rs.getString("AMOUNT_PAID"));
                    }
                    if(rs.getString("BALANCE_AMOUNT") == null){
                        txnObject.setBalanceAmount("");
                    }else{
                        txnObject.setBalanceAmount(rs.getString("BALANCE_AMOUNT"));
                    }
                    if(rs.getString("ATTRIBUTE1") == null){
                        txnObject.setAttribute1("");
                    }else{
                        txnObject.setAttribute1(rs.getString("ATTRIBUTE1"));
                    }
                    if(rs.getString("ATTRIBUTE2") == null){
                        txnObject.setAttribute2("");
                    }else{
                        txnObject.setAttribute2(rs.getString("ATTRIBUTE2"));
                    }
                    if(rs.getString("ATTRIBUTE3") == null){
                        txnObject.setAttribute3("");
                    }else{
                        txnObject.setAttribute3(rs.getString("ATTRIBUTE3"));
                    }
                    if(rs.getString("ATTRIBUTE4") == null){
                        txnObject.setAttribute4("");
                    }else{
                        txnObject.setAttribute4(rs.getString("ATTRIBUTE4"));
                    }
                    if(rs.getString("ATTRIBUTE5") == null){
                        txnObject.setAttribute5("");
                    }else{
                        txnObject.setAttribute5(rs.getString("ATTRIBUTE5"));
                    }
                    
                    statementList.add(txnObject);
                }
            }
            
            response.setStatement(statementList.toArray(new TxnDetailsObject[statementList.size()]));
            
            logger.callAudit(log);
        }else{
            response.setRetCode("E");
            response.setRetMsg(okExecuteResponse.getMessage());
            return response;
        }
        }catch(Exception e){
            log.setErrorMessage(e.toString());
                exceptionLogger.callAudit(log);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally{
            try{
            DBConnect.closeConnection(conn);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return response;                    
    }
    
    @PUT
    @Path("reserveVehicle")
    public GenericResponse reserveVehicle(ReserveVehicleRequest req, @HeaderParam("appName") String appName){
        GenericResponse response = new GenericResponse();
        PropertiesUtil propUtil = new PropertiesUtil();
        
        Gson gson = new Gson();
        try {
            if(appName!=null && appName!="" && !appName.isEmpty()){
                  if(req.getApplicationName()!=null && req.getApplicationName()!="" && !req.getApplicationName().isEmpty() &&
                  req.getInventoryItemId()!=null && req.getInventoryItemId()!="" && !req.getInventoryItemId().isEmpty()){
                    
                    System.out.println("in internal service reserveVehicle");

                    String serviceURL = System.getenv("DMZ_URL")+"/autotrustProxy/reserveVehicleProxy";
                    System.out.println("ReserveVehicleInKentico service url is : "+serviceURL);
                    System.out.println("request form internal : "+gson.toJson(req));
                    String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req),appName);
                    System.out.println("response is : "+responseString);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, GenericResponse.class);
                    }else{
                        response.setMessage("error in communication to dmz service");
                        response.setStatus("E");
                    }
                    
                }else{
                    response.setMessage("ApplicationName,InventoryItemId cannot be null or empty");
                    response.setStatus("E");
                }
            }else{
                response.setMessage("Header appName is required");
                response.setStatus("E");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        
        return response;
    }
    
    @PUT
    @Path("sendNotification")
    public GenericResponse sendNotification(SendNotificationRequest req, @HeaderParam("appName") String appName){
        GenericResponse response = new GenericResponse();
        PropertiesUtil propUtil = new PropertiesUtil();
        
        Gson gson = new Gson();
        try {
            if(appName!=null && appName!="" && !appName.isEmpty()){
                  if(req.getApplicationName()!=null && req.getApplicationName()!="" && !req.getApplicationName().isEmpty() &&
                  req.getNotificationMessage()!=null && req.getNotificationMessage()!="" && !req.getNotificationMessage().isEmpty()){
                    
                    System.out.println("in internal service sendNotification");

                    String serviceURL = System.getenv("DMZ_URL")+"/autotrustProxy/sendNotificationProxy";
                    System.out.println("SendSandboxNotifications service url is : "+serviceURL);
                    System.out.println("request form internal : "+gson.toJson(req));
                    String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req),appName);
                    System.out.println("response is : "+responseString);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, GenericResponse.class);
                    }else{
                        response.setMessage("error in communication to dmz service");
                        response.setStatus("E");
                    }
                    
                }else{
                    response.setMessage("ApplicationName,NotificationMessage cannot be null or empty");
                    response.setStatus("E");
                }
            }else{
                response.setMessage("Header appName is required");
                response.setStatus("E");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        
        return response;
    }
    
    @GET
    @Path("getVehicles")
    public CpoDealerVehiclesResponse getVehicles(@HeaderParam("appName") String appName){
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        CpoDealerVehiclesResponse response = new CpoDealerVehiclesResponse();
        ArrayList<CpoDealerVehicle> vehs = new ArrayList<CpoDealerVehicle>();
        Gson gson = new Gson();
        LogData log = new LogData();
        System.out.println("in internal getVehicles ");
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal getVehicles is : "+appName);
        String result = null;
        
        try{
    //        successResponse = successService.excuteService("getCpoVehicles", "EN");
        okExecuteResponse = successService.okToExcuteService(appName, "Get Vehicles", "123");
    //        if (successResponse.equals("S"))
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("getVehicles");
            log.setUserName("");
            log.setAttribute1("appName:"+appName);
            log.setServiceName("getVehicles");
            log.setVendorName("VendorVal:"+appName);

            stmt =
                conn.prepareCall("{call xxdmv_cpo_autotrust_mob_pkg.get_cpo_stock_vehicles(?,?,?,?)}");
            
            stmt.setString(1, null);
            stmt.registerOutParameter(2, OracleTypes.CURSOR);
            stmt.registerOutParameter(3, Types.VARCHAR); 
            stmt.registerOutParameter(4, Types.VARCHAR);
            
            rs=stmt.executeQuery();
            
            if(stmt.getString(3)!=null){
            response.setStatus(stmt.getString(3));
            }else{
            response.setStatus("");
            }
            if(stmt.getString(4)!=null){
            response.setMessage(stmt.getString(4));
            }else{
            response.setMessage("");
            }
            
            if (stmt.getObject(2) != null) {
                rs = (ResultSet) stmt.getObject(2);
                while (rs.next()) {
                    CpoDealerVehicle veh = new CpoDealerVehicle();
                    veh.setItemCode(rs.getString("item_code")!=null ? rs.getString("item_code") : "");
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
                    veh.setLcvflag(rs.getString("lcv_flag")!=null ? rs.getString("lcv_flag") : "");
                    veh.setSaleType(rs.getString("sale_type")!=null ? rs.getString("sale_type") : "");
                    veh.setReservedFlag(rs.getString("reserved_flag")!=null ? rs.getString("reserved_flag") : "");
                    veh.setCurrentOrganizationId(rs.getString("current_organization_id")!=null ? rs.getString("current_organization_id") : "");
                    veh.setCurrentOrganizationName(rs.getString("current_organization_name")!=null ? rs.getString("current_organization_name") : "");
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
                    veh.setOfferExpiryDate(rs.getString("offer_expiry_date")!=null ? rs.getString("offer_expiry_date") : "");
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
                    veh.setSpecialOffer_Flag(rs.getString("special_offer_flag")!=null ? rs.getString("special_offer_flag") : "");
                    veh.setInspectReportUrl(rs.getString("inspect_report_url")!=null ? rs.getString("inspect_report_url") : "");
                    veh.setServiceReportUrl(rs.getString("service_report_url")!=null ? rs.getString("service_report_url") : "");
                    veh.setBuyNowEnabled(rs.getString("buy_now_enabled")!=null ? rs.getString("buy_now_enabled") : "");
                    
                    veh.setManagersChoice(CommonUtil.calcFlagOnStringDate(veh.getCreationDate()));
                    veh.setVehicleLocation(rs.getString("vehicle_location")!=null ? rs.getString("vehicle_location") : "");
                    
                    veh.setVehicleGender(rs.getString("vehicle_gender")!=null ? rs.getString("vehicle_gender") : "");
                    veh.setTrim(rs.getString("trim")!=null ? rs.getString("trim") : "");
                    
                    if(rs.getString("image_url_pattern")!=null && rs.getString("image_url_pattern")!=""){
                        String pattern = rs.getString("image_url_pattern");
                        
                        if(pattern.contains("=")){
                        String[] parts = pattern.split("=");
                        String part1 = parts[0]; //pattern
                        String part2 = parts[1]; //noOfImages
                        int count = Integer.parseInt(part2);
                        
                        if(count>0){
                            veh.setThumbnailImage(part1+"_0.jpg"); 
                        }else{
                            veh.setThumbnailImage("");
                        }
                        }else{
                            veh.setThumbnailImage("");
                        }
                    }else{
                        veh.setThumbnailImage("");
                    }
                    if(veh.getVhlPrice()!=""){
                        vehs.add(veh);  
                    }
                }
            }
           
            response.setVehicles(vehs.toArray(new CpoDealerVehicle[vehs.size()]));
//            response.setStatus("S");
//            response.setMessage("Success");
            logger.callAudit(log);
        }
        else{
            response.setStatus("E");
            response.setMessage(okExecuteResponse.getMessage());
            return response;
        }
                
        }catch(Exception e){
            log.setErrorMessage(e.toString());
                exceptionLogger.callAudit(log);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally{
            try{
            DBConnect.closeConnection(conn);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
        return response;
    }
    
    @GET
    @Path("getCpoStock")
    public CpoDealerVehiclesResponse getCpoStock(@HeaderParam("appName") String appName){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        CpoDealerVehiclesResponse response = new CpoDealerVehiclesResponse();
        ArrayList<CpoDealerVehicle> vehs = new ArrayList<CpoDealerVehicle>();
        LogData log = new LogData();
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        
        try{
        okExecuteResponse = successService.okToExcuteService(appName, "Get CPO Stock Vehicles", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("getCpoStock");
            log.setUserName("");
            log.setAttribute1("appName:"+appName);
            log.setServiceName("getCpoStock");
            log.setVendorName("VendorVal:"+appName);
            
            String cpoVehilcesQuery = "SELECT item_code, \n"+
                                      "description, \n"+
                                      "serial_number, \n"+
                                      "cd, \n"+
                                      "variant, \n"+
                                      "trim_type, \n"+
                                      "power_options, \n"+
                                      "abs, \n"+
                                      "keyless_entry, \n"+
                                      "alloy_wheels, \n"+
                                      "sun_roof, \n"+
                                      "cruise_control, \n"+
                                      "leather_seats, \n"+
                                      "memory_seats, \n"+
                                      "fog_lamps, \n"+
                                      "xenon_lights, \n"+
                                      "navigation, \n"+
                                      "rear_camera, \n"+
                                      "parking_sensors, \n"+
                                      "dvd, \n"+
                                      "mp3_player, \n"+
                                      "bluetooth, \n"+
                                      "roof_rails, \n"+
                                      "differential_lock, \n"+
                                      "rear_spoliler, \n"+
                                      "reg_exp_date, \n"+
                                      "service_history, \n"+
                                      "war_exp_date, \n"+
                                      "brand, \n"+
                                      "web_brand, \n"+
                                      "model_desc, \n"+
                                      "web_model_desc, \n"+
                                      "transmission, \n"+
                                      "engine_size, \n"+
                                      "body_color_desc, \n"+
                                      "web_body_color_desc, \n"+
                                      "trim_color_desc, \n"+
                                      "web_trim_color_desc, \n"+
                                      "model_year, \n"+
                                      "engine_type, \n"+
                                      "body_type, \n"+
                                      "web_body_type, \n"+
                                      "mileage, \n"+
                                      "warranty_type, \n"+
                                      "seller_name, \n"+
                                      "vhl_price, \n"+
                                      "lcv_flag, \n"+
                                      "sale_type, \n"+
                                      "reserved_flag, \n"+
                                      "current_organization_id, \n"+
                                      "current_organization_name, \n"+
                                      "creation_date, \n"+
                                      "last_update_date, \n"+
                                      "rating, \n"+
                                      "kilometers, \n"+
                                      "fuel_type, \n"+
                                      "veh_type, \n"+
                                      "trim_color, \n"+
                                      "model, \n"+
                                      "sub_model_desc, \n"+
                                      "reservation_flag, \n"+
                                      "open_flag, \n"+
                                      "reservation_id, \n"+
                                      "offer_expiry_date, \n"+
                                      "new_offer_price, \n"+
                                      "inventory_item_id, \n"+
                                      "stock_type, \n"+
                                      "image_url_name, \n"+
                                      "thumbnail_url_name, \n"+
                                      "image_respose, \n"+
                                      "attribute1, \n"+
                                      "attribute2, \n"+
                                      "attribute3, \n"+
                                      "attribute4, \n"+
                                      "attribute5, \n"+
                                      "no_of_images, \n"+
                                      "special_offer_flag, \n"+
                                      "inspect_report_url, \n"+
                                      "service_report_url, \n"+
                                      "buy_now_enabled, \n"+
                                      "image_url_pattern, \n"+
                                      "vehicle_location, \n"+
                                      "vehicle_gender, \n"+
                                      "trim \n"+
                                      "FROM \n"+
                                      "XXDMV_CPO_DEALER_VEHICLES_V2";
            
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(cpoVehilcesQuery);
            if(rs!=null){
                
                while(rs.next()){
                    CpoDealerVehicle veh = new CpoDealerVehicle();
                    veh.setItemCode(rs.getString("item_code")!=null ? rs.getString("item_code") : "");
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
                    veh.setLcvflag(rs.getString("lcv_flag")!=null ? rs.getString("lcv_flag") : "");
                    veh.setSaleType(rs.getString("sale_type")!=null ? rs.getString("sale_type") : "");
                    veh.setReservedFlag(rs.getString("reserved_flag")!=null ? rs.getString("reserved_flag") : "");
                    veh.setCurrentOrganizationId(rs.getString("current_organization_id")!=null ? rs.getString("current_organization_id") : "");
                    veh.setCurrentOrganizationName(rs.getString("current_organization_name")!=null ? rs.getString("current_organization_name") : "");
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
                    veh.setOfferExpiryDate(rs.getString("offer_expiry_date")!=null ? rs.getString("offer_expiry_date") : "");
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
                    veh.setSpecialOffer_Flag(rs.getString("special_offer_flag")!=null ? rs.getString("special_offer_flag") : "");
                    veh.setInspectReportUrl(rs.getString("inspect_report_url")!=null ? rs.getString("inspect_report_url") : "");
                    veh.setServiceReportUrl(rs.getString("service_report_url")!=null ? rs.getString("service_report_url") : "");
                    veh.setBuyNowEnabled(rs.getString("buy_now_enabled")!=null ? rs.getString("buy_now_enabled") : "");
                    
                    veh.setManagersChoice(CommonUtil.calcFlagOnStringDate(veh.getCreationDate()));
                    veh.setVehicleLocation(rs.getString("vehicle_location")!=null ? rs.getString("vehicle_location") : "");
                    
                    veh.setVehicleGender(rs.getString("vehicle_gender")!=null ? rs.getString("vehicle_gender") : "");
                    veh.setTrim(rs.getString("trim")!=null ? rs.getString("trim") : "");
                    
                    if(rs.getString("image_url_pattern")!=null && rs.getString("image_url_pattern")!=""){
                        String pattern = rs.getString("image_url_pattern");
                        
                        if(pattern.contains("=")){
                        String[] parts = pattern.split("=");
                        String part1 = parts[0]; //pattern
                        String part2 = parts[1]; //noOfImages
                        int count = Integer.parseInt(part2);
                        
                        if(count>0){
                            veh.setThumbnailImage(part1+"_0.jpg"); 
                        }else{
                            veh.setThumbnailImage("");
                        }
                        }else{
                            veh.setThumbnailImage("");
                        }
                    }else{
                        veh.setThumbnailImage("");
                    }
                    vehs.add(veh);  
                    
                }
            }
            response.setVehicles(vehs.toArray(new CpoDealerVehicle[vehs.size()]));
            response.setStatus("S");
            response.setMessage("Success");
            logger.callAudit(log);
        }
        else{
            response.setStatus("E");
            response.setMessage(okExecuteResponse.getMessage());
            return response;
        }
                
        }catch(Exception e){
            log.setErrorMessage(e.toString());
                exceptionLogger.callAudit(log);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally{
            try{
            DBConnect.closeConnection(conn);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
        return response;
    }
}
