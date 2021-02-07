package com.awr.autotrustservice.svc;

import com.awr.autotrustservice.dto.DeviceObject;
import com.awr.autotrustservice.dto.VehicleImage;
import com.awr.autotrustservice.response.GetReservationNumberResponse;
import com.awr.autotrustservice.response.GetTokenIdResponse;
import com.awr.autotrustservice.util.DBConnect;
import com.awr.autotrustservice.util.LogData;

import com.awr.autotrustservice.util.NotificationAuditLogger;
import com.awr.autotrustservice.util.NotificationExceptionLogger;

import com.google.gson.Gson;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
//
//@Path("deviceDetails")
//@Consumes("application/json")
//@Produces("application/json")
public class DeviceToken {
    public DeviceToken() {
        super();
    }
    NotificationAuditLogger logger = new NotificationAuditLogger();
    NotificationExceptionLogger exceptionLogger = new NotificationExceptionLogger();
    SuccessService successService = new SuccessService();
    
//    @GET
//    @Path("getTokenId")
//    public GetTokenIdResponse getTokenId(@QueryParam("userId") String userId, 
//                                         @QueryParam("applicationName") String applicationName,
//                                         @QueryParam("deviceType") String deviceType,
//                                         @HeaderParam("appName") String appName){
//        GetTokenIdResponse response = new GetTokenIdResponse();
//        ArrayList<DeviceObject> deviceList = new ArrayList<DeviceObject>();
//        Gson gson = new Gson();
//        LogData log = new LogData();
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        conn = DBConnect.getConnection();
//        System.out.println("in internal getTokenId ");
//        String successResponse = null;
//        conn = DBConnect.getConnection();
//        //where to pass vendor value??
//        System.out.println("appName in internal getTokenId is : "+appName);
//        
//        try{
//        successResponse = successService.excuteService("getTokenId", "EN");
//        if (successResponse.equals("S")) 
//        {
//            log.setAppCode("AUTOTRUST");
//            log.setAppName("AutotrustServices");
//            log.setDocCode("getTokenId");
//            log.setUserName("userId:"+userId);
//            log.setAttribute1("applicationName:"+applicationName);
//            log.setAttribute2("userId:"+userId);
//            log.setAttribute3("deviceType:"+deviceType);
//            log.setServiceName("getTokenId");
//            log.setVendorName("VendorVal:"+appName);
//            
//            System.out.println("Success service executed");
//            String query = null;
//            
//            if(userId!=null && applicationName!=null && deviceType!=null){
//                query = "SELECT token_id, device_type, user_id \n" + 
//                        "FROM \n" + 
//                        "xxaac.xxdmv_ioms_push_noti_lines \n" +
//                        "WHERE \n" + 
//                        "application_name = '" + applicationName + "' \n" + 
//                        "AND \n" + 
//                        "user_id = '" + userId + "' \n" + 
//                        "AND \n" + 
//                        "UPPER(device_type) = " + "UPPER('" + deviceType + "')";
//            }else if(userId!=null && applicationName!=null && deviceType==null){
//                query = "SELECT token_id, device_type, user_id \n" + 
//                        "FROM \n" + 
//                        "xxaac.xxdmv_ioms_push_noti_lines \n" +
//                        "WHERE \n" + 
//                        "application_name = '" + applicationName + "' \n" + 
//                        "AND \n" + 
//                        "user_id = '" + userId + "'";
//            }else if(userId!=null && applicationName==null && deviceType!=null){
//                query = "SELECT token_id, device_type, user_id \n" + 
//                        "FROM \n" + 
//                        "xxaac.xxdmv_ioms_push_noti_lines \n" +
//                        "WHERE \n" + 
//                        "user_id = '" + userId + "' \n" + 
//                        "AND \n" + 
//                        "UPPER(device_type) = " + "UPPER('" + deviceType + "')";
//            }else if(userId==null && applicationName!=null && deviceType!=null){
//                query = "SELECT token_id, device_type, user_id \n" + 
//                        "FROM \n" + 
//                        "xxaac.xxdmv_ioms_push_noti_lines \n" +
//                        "WHERE \n" + 
//                        "application_name = '" + applicationName + "' \n" + 
//                        "AND \n" + 
//                        "UPPER(device_type) = " + "UPPER('" + deviceType + "')";
//            }else if(userId!=null && applicationName==null && deviceType==null){
//                query = "SELECT token_id, device_type, user_id \n" + 
//                        "FROM \n" + 
//                        "xxaac.xxdmv_ioms_push_noti_lines \n" +
//                        "WHERE \n" + 
//                        "user_id = '" + userId + "'";
//            }else if(userId==null && applicationName!=null && deviceType==null){
//                query = "SELECT token_id, device_type, user_id \n" + 
//                        "FROM \n" + 
//                        "xxaac.xxdmv_ioms_push_noti_lines \n" +
//                        "WHERE \n" + 
//                        "application_name = '" + applicationName + "'";
//            }else if(userId==null && applicationName==null && deviceType!=null){
//                query = "SELECT token_id, device_type, user_id \n" + 
//                        "FROM \n" + 
//                        "xxaac.xxdmv_ioms_push_noti_lines \n" +
//                        "WHERE \n" + 
//                        "UPPER(device_type) = " + "UPPER('" + deviceType + "')";
//            }
//            
//            System.out.println("query string is : "+query);
//
//         
//            
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery(query);
//            
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
//            
//        }
//        logger.callAudit(log);
//        }catch(Exception e){
//            log.setErrorMessage(e.toString());
//            exceptionLogger.callAudit(log);
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }finally{
//            try{
//            stmt.close();
//            rs.close();
//            conn.close();
//            }catch(Exception ex){
//                ex.printStackTrace();
//            }
//        }
//        
//        return response;
//        
//    }
}
