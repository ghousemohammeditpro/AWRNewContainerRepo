package com.awr.autotrustservice.svc;

import com.awr.autotrustservice.request.UserRegStep1Request;
import com.awr.autotrustservice.request.UserRegStep2Request;
import com.awr.autotrustservice.request.UserRegStep3Request;
import com.awr.autotrustservice.response.GenericResponse;
import com.awr.autotrustservice.response.UserRegStep1Response;
import com.awr.autotrustservice.response.UserRegStep2Response;
import com.awr.autotrustservice.response.UserRegStep3Response;
import com.awr.autotrustservice.util.DBConnect;
import com.awr.autotrustservice.util.LogData;
import com.awr.autotrustservice.util.NotificationAuditLogger;
import com.awr.autotrustservice.util.NotificationExceptionLogger;

import com.awr.autotrustservice.util.UserRegStep1Util;

import com.awr.autotrustservice.util.UserRegStep2Util;

import com.awr.autotrustservice.util.UserRegStep3Util;

import com.google.gson.Gson;

import java.sql.Connection;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("userRegistration")
@Consumes("application/json")
@Produces("application/json")
public class UserRegistration {
    public UserRegistration() {
        super();
    }
    private static String key = "€%$Enrich*&€";
    NotificationAuditLogger logger = new NotificationAuditLogger();
    NotificationExceptionLogger exceptionLogger = new NotificationExceptionLogger();
    SuccessService successService = new SuccessService();
    
    @PUT
    @Path("step1")
    public UserRegStep1Response userRegStep1(UserRegStep1Request req, @HeaderParam("appName") String appName, @HeaderParam("source") String source){
        Connection conn = null;
        UserRegStep1Response response = new UserRegStep1Response();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal userRegStep1 ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal userRegStep1 is : "+appName);
        
        try{
//        successResponse = successService.excuteService("userRegStep1", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Registration Step1", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("userRegStep1");
            log.setUserName(req.getEmail());
            log.setAttribute1("fName:"+req.getFName());
            
        result = UserRegStep1Util.step1(req, source);
        
        if(result!=null){
            System.out.println("result is : "+result);
            response = gson.fromJson(result, UserRegStep1Response.class);
            if(response!=null)
                System.out.println("response is : "+response.toString());

        }
                log.setAttribute2("mobile:"+req.getMobile());
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
    @Path("step2")
    public UserRegStep2Response userRegStep2(UserRegStep2Request req, @HeaderParam("appName") String appName, @HeaderParam("source") String source){
        Connection conn = null;
        UserRegStep2Response response = new UserRegStep2Response();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal userRegStep2 ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal userRegStep2 is : "+appName);
        
        try{
//        successResponse = successService.excuteService("userRegStep2", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Registration Step2", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S")) 
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("userRegStep2");
            log.setUserName(req.getEmail());
            log.setAttribute1("fName:"+req.getFirstName());
            
        result = UserRegStep2Util.step2(req, source);
        
        if(result!=null){
            System.out.println("result is : "+result);
            response = gson.fromJson(result, UserRegStep2Response.class);
            if(response!=null)
                System.out.println("response is : "+response.toString());

        }
                log.setAttribute2("mobile:"+req.getMobile());
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
    @Path("step3")
    public UserRegStep3Response userRegStep3(UserRegStep3Request req, @HeaderParam("appName") String appName, @HeaderParam("source") String source){
        Connection conn = null;
        UserRegStep3Response response = new UserRegStep3Response();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal userRegStep3 ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal userRegStep3 is : "+appName);
        
        try{
//        successResponse = successService.excuteService("userRegStep3", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Registration Step3", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S")) 
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("userRegStep3");
            log.setUserName(req.getBuId());
            log.setAttribute1("profession:"+req.getProfession());
            
        result = UserRegStep3Util.step3(req, source);
        
        if(result!=null){
            System.out.println("result is : "+result);
            response = gson.fromJson(result, UserRegStep3Response.class);
            if(response!=null)
                System.out.println("response is : "+response.toString());

        }
//                log.setAttribute2("mobile:"+req.getMobile());
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
}
