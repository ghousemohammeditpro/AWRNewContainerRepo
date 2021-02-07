package com.awr.autotrustservice.svc;

import com.awr.autotrustservice.dto.PersonalDetailsObject;
import com.awr.autotrustservice.request.AddCarRequest;
import com.awr.autotrustservice.request.ChangeMobileStep1Request;
import com.awr.autotrustservice.request.ChangeMobileStep2Request;
import com.awr.autotrustservice.request.ChangeMobileStep3Request;
import com.awr.autotrustservice.request.GenericRequest;
import com.awr.autotrustservice.request.RemoveCarRequest;
import com.awr.autotrustservice.request.SaveAccountInfoRequest;
import com.awr.autotrustservice.request.SaveAddressRequest;
import com.awr.autotrustservice.request.SaveProfileRequest;
import com.awr.autotrustservice.request.SaveSubscriptionRequest;
import com.awr.autotrustservice.request.UpdatePasswordRequest;
import com.awr.autotrustservice.request.UserRegStep1Request;
import com.awr.autotrustservice.response.ChangeMobileStep1Response;
import com.awr.autotrustservice.response.ChangeMobileStep2Response;
import com.awr.autotrustservice.response.ChangeMobileStep3Response;
import com.awr.autotrustservice.response.GenericResponse;
import com.awr.autotrustservice.response.LoadPersonalDetailsResponse;
import com.awr.autotrustservice.response.MyGarageVehiclesResponse;
import com.awr.autotrustservice.response.SaveAccountInfoResponse;
import com.awr.autotrustservice.response.SaveAddressResponse;
import com.awr.autotrustservice.response.SaveProfileResponse;
import com.awr.autotrustservice.response.SaveSubscriptionResponse;
import com.awr.autotrustservice.response.SaveSubscriptionUtil;
import com.awr.autotrustservice.response.UpdatePasswordResponse;
import com.awr.autotrustservice.response.UserRegStep1Response;
import com.awr.autotrustservice.util.AddCarUtil;
import com.awr.autotrustservice.util.ChangeMobileStep1Util;
import com.awr.autotrustservice.util.ChangeMobileStep2Util;
import com.awr.autotrustservice.util.ChangeMobileStep3Util;
import com.awr.autotrustservice.util.CommonUtil;
import com.awr.autotrustservice.util.DBConnect;
import com.awr.autotrustservice.util.LoadPersonalDetailsUtil;
import com.awr.autotrustservice.util.LogData;
import com.awr.autotrustservice.util.MyGarageVehiclesUtil;
import com.awr.autotrustservice.util.NotificationAuditLogger;
import com.awr.autotrustservice.util.NotificationExceptionLogger;
import com.awr.autotrustservice.util.RemoveCarUtil;
import com.awr.autotrustservice.util.SaveAccountInfoUtil;
import com.awr.autotrustservice.util.SaveAddressUtil;
import com.awr.autotrustservice.util.SaveProfileUtil;
import com.awr.autotrustservice.util.UpdatePasswordUtil;
import com.awr.autotrustservice.util.UserRegStep1Util;

import com.google.gson.Gson;

import java.sql.Connection;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("user")
@Consumes("application/json")
@Produces("application/json")
public class User {
    public User() {
        super();
    }
    NotificationAuditLogger logger = new NotificationAuditLogger();
    NotificationExceptionLogger exceptionLogger = new NotificationExceptionLogger();
    SuccessService successService = new SuccessService();
    
    @PUT
    @Path("updatePassword")
    public UpdatePasswordResponse updatePassword(UpdatePasswordRequest req, @HeaderParam("appName") String appName, @HeaderParam("source") String source){
        Connection conn = null;
        UpdatePasswordResponse response = new UpdatePasswordResponse();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal updatePassword ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal updatePassword is : "+appName);
        
        try{
//        successResponse = successService.excuteService("updatePassword", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Update Password", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("updatePassword");
            log.setUserName(req.getBuId());
            
        result = UpdatePasswordUtil.updatePswd(req, source);
        
        if(result!=null){
            System.out.println("result is : "+result);
            response = gson.fromJson(result, UpdatePasswordResponse.class);
            if(response!=null)
                System.out.println("response is : "+response.toString());

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
    @Path("changeMobileStep1")
    public ChangeMobileStep1Response changeMobileStep1(ChangeMobileStep1Request req, @HeaderParam("appName") String appName, @HeaderParam("source") String source){
        Connection conn = null;
        ChangeMobileStep1Response response = new ChangeMobileStep1Response();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal changeMobileStep1 ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal changeMobileStep1 is : "+appName);
        
        try{
//        successResponse = successService.excuteService("changeMobileStep1", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Change Mobile Step1", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S")) 
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("changeMobileStep1");
            log.setUserName(req.getBuId());
            
        result = ChangeMobileStep1Util.changeMobilestep1(req, source);
        
        if(result!=null){
            System.out.println("result is : "+result);
            response = gson.fromJson(result, ChangeMobileStep1Response.class);
            if(response!=null)
                System.out.println("response is : "+response.toString());

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
    @Path("changeMobileStep2")
    public ChangeMobileStep2Response changeMobileStep2(ChangeMobileStep2Request req, @HeaderParam("appName") String appName, @HeaderParam("source") String source){
        Connection conn = null;
        ChangeMobileStep2Response response = new ChangeMobileStep2Response();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal changeMobileStep2 ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal changeMobileStep2 is : "+appName);
        
        try{
//        successResponse = successService.excuteService("changeMobileStep2", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Change Mobile Step2", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S")) 
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("changeMobileStep2");
            log.setUserName(req.getBuId());
            
        result = ChangeMobileStep2Util.changeMobilestep2(req, source);
        
        if(result!=null){
            System.out.println("result is : "+result);
            response = gson.fromJson(result, ChangeMobileStep2Response.class);
            if(response!=null)
                System.out.println("response is : "+response.toString());

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
    @Path("changeMobileStep3")
    public ChangeMobileStep3Response changeMobileStep3(ChangeMobileStep3Request req, @HeaderParam("appName") String appName, @HeaderParam("source") String source){
        Connection conn = null;
        ChangeMobileStep3Response response = new ChangeMobileStep3Response();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal changeMobileStep3 ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal changeMobileStep3 is : "+appName);
        
        try{
//        successResponse = successService.excuteService("changeMobileStep3", "EN");
//        if (successResponse.equals("S"))
        okExecuteResponse = successService.okToExcuteService(appName, "Change Mobile Step3", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S")) 
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("changeMobileStep3");
            log.setUserName(req.getBuId());
            
        result = ChangeMobileStep3Util.changeMobilestep3(req, source);
        
        if(result!=null){
            System.out.println("result is : "+result);
            response = gson.fromJson(result, ChangeMobileStep3Response.class);
            if(response!=null)
                System.out.println("response is : "+response.toString());

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
    @Path("saveAccountInfo")
    public SaveAccountInfoResponse saveAccountInfo(SaveAccountInfoRequest req, @HeaderParam("appName") String appName, @HeaderParam("source") String source){
        Connection conn = null;
        SaveAccountInfoResponse response = new SaveAccountInfoResponse();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal updatePassword ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal saveAccountInfo is : "+appName);
        
        try{
//        successResponse = successService.excuteService("saveAccountInfo", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Save Account Info", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("saveAccountInfo");
            log.setUserName(req.getBuId());
//            System.out.println("lname:"+req.getLname());
//            System.out.println("fname:"+req.getFname());
        result = SaveAccountInfoUtil.saveAccountInfo(req, source);
        
        if(result!=null){
            System.out.println("result is : "+result);
            response = gson.fromJson(result, SaveAccountInfoResponse.class);
            if(response!=null)
                System.out.println("response is : "+response.toString());

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
    @Path("saveAddress")
    public SaveAddressResponse saveAddress(SaveAddressRequest req, @HeaderParam("appName") String appName, @HeaderParam("source") String source){
        Connection conn = null;
        SaveAddressResponse response = new SaveAddressResponse();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal saveAddress ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal saveAddress is : "+appName);
        
        try{
//        successResponse = successService.excuteService("saveAddress", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Save Address", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S")) 
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("saveAddress");
            log.setUserName(req.getBuId());
            
        result = SaveAddressUtil.saveAddress(req, source);
        
        if(result!=null){
            System.out.println("result is : "+result);
            response = gson.fromJson(result, SaveAddressResponse.class);
            if(response!=null)
                System.out.println("response is : "+response.toString());

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
    @Path("saveSubscription")
    public SaveSubscriptionResponse saveSubscription(SaveSubscriptionRequest req, @HeaderParam("appName") String appName, @HeaderParam("source") String source){
        Connection conn = null;
        SaveSubscriptionResponse response = new SaveSubscriptionResponse();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal saveSubscription ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal saveSubscription is : "+appName);
        
        try{
//        successResponse = successService.excuteService("saveSubscription", "EN");
//        if (successResponse.equals("S"))
        okExecuteResponse = successService.okToExcuteService(appName, "Save Subscription", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("saveSubscription");
            log.setUserName(req.getBuId());
            
        result = SaveSubscriptionUtil.saveSubscription(req, source);
        
        if(result!=null){
            System.out.println("result is : "+result);
            response = gson.fromJson(result, SaveSubscriptionResponse.class);
            if(response!=null)
                System.out.println("response is : "+response.toString());

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
    @Path("saveProfile")
    public SaveProfileResponse saveProfile(SaveProfileRequest req, @HeaderParam("appName") String appName, @HeaderParam("source") String source){
        Connection conn = null;
        SaveProfileResponse response = new SaveProfileResponse();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal saveProfile ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal saveProfile is : "+appName);
        
        try{
//        successResponse = successService.excuteService("saveProfile", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Save Profile", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("saveProfile");
            log.setUserName(req.getBuId());
            
        result = SaveProfileUtil.saveProfile(req, source);
        
        if(result!=null){
            System.out.println("result is : "+result);
            response = gson.fromJson(result, SaveProfileResponse.class);
            if(response!=null)
                System.out.println("response is : "+response.toString());

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
    @Path("loadPersonalDetails")
    public LoadPersonalDetailsResponse loadPersonalDetails(GenericRequest req, @HeaderParam("appName") String appName, @HeaderParam("source") String source){
        Connection conn = null;
        LoadPersonalDetailsResponse response = new LoadPersonalDetailsResponse();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal loadPersonalDetails ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal loadPersonalDetails is : "+appName);
        
        try{
//        successResponse = successService.excuteService("loadPersonalDetails", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Load Personal Details", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("loadPersonalDetails");
            log.setUserName(req.getBuId());
            
        result = LoadPersonalDetailsUtil.personalDetails(req, source);
        
        if(result!=null){
            System.out.println("result is : "+result);
            response = gson.fromJson(result, LoadPersonalDetailsResponse.class);
            if(response!=null){
                //formatting dob in loadPersonalDetails for input format "\/Date(253402286399997)\/"
                PersonalDetailsObject dataObj = response.getData();
                String dobStr = dataObj.getDateOfBirth();
                if(dobStr!=null && dobStr!="" && dobStr.contains(")")){
                dataObj.setDateOfBirth(CommonUtil.dateFromMillisec(dobStr));
                response.setData(dataObj);
                }
                System.out.println("response is : "+response.toString());
            }

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
    @Path("myGarageVehicles")
    public MyGarageVehiclesResponse getMyGarageVehicles(GenericRequest req, @HeaderParam("appName") String appName, @HeaderParam("source") String source){
        Connection conn = null;
        MyGarageVehiclesResponse response = new MyGarageVehiclesResponse();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal getMyGarageVehicles ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal getMyGarageVehicles is : "+appName);
        
        try{
//        successResponse = successService.excuteService("getMyGarageVehicles", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Get Garage Vehicles", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("getMyGarageVehicles");
            log.setUserName(req.getBuId());
            
        result = MyGarageVehiclesUtil.myGarage(req, source);
        
        if(result!=null){
            System.out.println("result is : "+result);
            response = gson.fromJson(result, MyGarageVehiclesResponse.class);
            if(response!=null)
                System.out.println("response is : "+response.toString());

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
    @Path("addCar")
    public GenericResponse addCar(AddCarRequest req, @HeaderParam("appName") String appName, @HeaderParam("source") String source){
        Connection conn = null;
        GenericResponse response = new GenericResponse();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal addCar ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal addCar is : "+appName);
        
        try{
//        successResponse = successService.excuteService("addCar", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Add Car", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("addCar");
            log.setUserName(req.getBuId());
            log.setAttribute1("vinNo:"+req.getVinNumber());
            log.setAttribute2("appName:"+appName);
            
        result = AddCarUtil.addCar(req, source);
        
        if(result!=null){
            System.out.println("result is : "+result);
            response = gson.fromJson(result, GenericResponse.class);
            if(response!=null)
                System.out.println("response is : "+response.toString());

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
    @Path("removeCar")
    public GenericResponse removeCar(RemoveCarRequest req, @HeaderParam("appName") String appName, @HeaderParam("source") String source){
        Connection conn = null;
        GenericResponse response = new GenericResponse();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal removeCar ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal removeCar is : "+appName);
        
        try{
//        successResponse = successService.excuteService("removeCar", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Remove Car", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("removeCar");
            log.setUserName(req.getBuId());
            log.setAttribute1("carId:"+req.getCarId());
            log.setAttribute2("appName:"+appName);
            
        result = RemoveCarUtil.removeCar(req, source);
        
        if(result!=null){
            System.out.println("result is : "+result);
            response = gson.fromJson(result, GenericResponse.class);
            if(response!=null)
                System.out.println("response is : "+response.toString());

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
}
