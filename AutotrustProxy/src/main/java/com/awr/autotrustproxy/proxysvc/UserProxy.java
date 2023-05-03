package com.awr.autotrustproxy.proxysvc;

import com.awr.autotrustproxy.request.AddCarRequest;
import com.awr.autotrustproxy.request.ChangeMobileStep1Request;
import com.awr.autotrustproxy.request.ChangeMobileStep2Request;
import com.awr.autotrustproxy.request.ChangeMobileStep3Request;
import com.awr.autotrustproxy.request.GenericRequest;
import com.awr.autotrustproxy.request.RemoveCarRequest;
import com.awr.autotrustproxy.request.SaveAccountInfoRequest;
import com.awr.autotrustproxy.request.SaveAddressRequest;
import com.awr.autotrustproxy.request.SaveProfileRequest;
import com.awr.autotrustproxy.request.SaveSubscriptionRequest;
import com.awr.autotrustproxy.request.UpdatePasswordRequest;
import com.awr.autotrustproxy.request.UserRegStep1Request;
import com.awr.autotrustproxy.response.ChangeMobileStep1Response;
import com.awr.autotrustproxy.response.ChangeMobileStep2Response;
import com.awr.autotrustproxy.response.ChangeMobileStep3Response;
import com.awr.autotrustproxy.response.GenericResponse;
import com.awr.autotrustproxy.response.LoadPersonalDetailsResponse;
import com.awr.autotrustproxy.response.MyGarageVehiclesResponse;
import com.awr.autotrustproxy.response.SaveAccountInfoResponse;
import com.awr.autotrustproxy.response.SaveAddressResponse;
import com.awr.autotrustproxy.response.SaveProfileResponse;
import com.awr.autotrustproxy.response.SaveSubscriptionResponse;
import com.awr.autotrustproxy.response.UpdatePasswordResponse;
import com.awr.autotrustproxy.response.UserRegStep1Response;
import com.awr.autotrustproxy.util.HexString;
import com.awr.autotrustproxy.util.PropertiesUtil;
import com.awr.autotrustproxy.util.RestUtil;
import com.awr.autotrustproxy.util.Token;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("userProxy")
@Consumes("application/json")
@Produces("application/json")
public class UserProxy {
    public UserProxy() {
        super();
    }
    @PUT
    @Path("updatePassword")
    @Produces("application/json")
    public UpdatePasswordResponse step1(UpdatePasswordRequest req, @HeaderParam("auth") String authorization, @HeaderParam("appName") String appName,
                                        @HeaderParam("source") String source){
        UpdatePasswordResponse response = new UpdatePasswordResponse();
        PropertiesUtil propUtil = new PropertiesUtil();
        
        String auth = null;
        String propertyToken = null;
        String propertyAuthorization = null;
        Gson gson = new Gson();
        byte[] key = new byte[0];
        try {
            if(authorization!=null && authorization !=""){
            key = authorization.getBytes("UTF-8");
            }else{
                response.setMessage("Please pass a valid token !!");
                response.setStatus("Error");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty() &&
               source!=null && source !="" && !source.isEmpty()){
            System.out.println("appName/source in dmz login is : "+appName+", "+source);
            }else{
                response.setMessage("Please pass a valid appName, source !!");
                response.setStatus("Error");
                return response;
            }
            try {
                  propertyAuthorization = System.getenv("authorization");
                  propertyToken = System.getenv("tokenValue");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException uee) {
            // TODO: Add catch code
            uee.printStackTrace();
        }
        if (authorization.equals(propertyAuthorization)) {
            authorization = null;
            auth = HexString.toHexString(key);
        } else {
            response.setMessage("Unauthorized Token !!");
            response.setStatus("Error");
            authorization = null;
            return response;
        }
        authorization = null;
        Token token = new Token();
        String jwt = token.generateToken(auth, key);
        authorization = null;
        try {
            authorization = null;
            if (jwt.equals(propertyToken)) {
                
                if(req.getBuId()!=null && req.getBuId()!="" &&
                    req.getOldPswd()!=null && req.getOldPswd()!="" &&
                    req.getNewPswd()!=null && req.getNewPswd()!=""){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/user/updatePassword";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithSource(serviceURL, gson.toJson(req), appName, source);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, UpdatePasswordResponse.class);
                    }else{
                        response.setMessage("error in communication to internal updatePassword service");
                        response.setStatus("error");
                    }
                }else{
                    response.setMessage("buId,old and new password cannot be null or empty");
                    response.setStatus("error");
                }
                
            }
        } catch (Exception e) {
            authorization = null;
            e.printStackTrace();
        } finally {
            authorization = null;
        }
        
        return response;
    }
    
    @PUT
    @Path("changeMobileStep1")
    @Produces("application/json")
    public ChangeMobileStep1Response changeMobileStep1(ChangeMobileStep1Request req, @HeaderParam("auth") String authorization, @HeaderParam("appName") String appName, 
                                                       @HeaderParam("source") String source){
        ChangeMobileStep1Response response = new ChangeMobileStep1Response();
        PropertiesUtil propUtil = new PropertiesUtil();
        
        String auth = null;
        String propertyToken = null;
        String propertyAuthorization = null;
        Gson gson = new Gson();
        byte[] key = new byte[0];
        try {
            if(authorization!=null && authorization !=""){
            key = authorization.getBytes("UTF-8");
            }else{
                response.setMessage("Please pass a valid token !!");
                response.setStatus("Error");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty() &&
               source!=null && source !="" && !source.isEmpty()){
            System.out.println("appName/source in dmz login is : "+appName+", "+source);
            }else{
                response.setMessage("Please pass a valid appName, source !!");
                response.setStatus("Error");
                return response;
            }
            try {
                  propertyAuthorization = System.getenv("authorization");
                  propertyToken = System.getenv("tokenValue");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException uee) {
            // TODO: Add catch code
            uee.printStackTrace();
        }
        if (authorization.equals(propertyAuthorization)) {
            authorization = null;
            auth = HexString.toHexString(key);
        } else {
            response.setMessage("Unauthorized Token !!");
            response.setStatus("Error");
            authorization = null;
            return response;
        }
        authorization = null;
        Token token = new Token();
        String jwt = token.generateToken(auth, key);
        authorization = null;
        try {
            authorization = null;
            if (jwt.equals(propertyToken)) {
                
                if(req.getBuId()!=null && req.getBuId()!="" &&
                    req.getPswd()!=null && req.getPswd()!=""){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/user/changeMobileStep1";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithSource(serviceURL, gson.toJson(req), appName, source);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, ChangeMobileStep1Response.class);
                    }else{
                        response.setMessage("error in communication to internal changeMobileStep1 service");
                        response.setStatus("error");
                    }
                }else{
                    response.setMessage("buId,password cannot be null or empty");
                    response.setStatus("error");
                }
                
            }
        } catch (Exception e) {
            authorization = null;
            e.printStackTrace();
        } finally {
            authorization = null;
        }
        
        return response;
    }

    @PUT
    @Path("changeMobileStep2")
    @Produces("application/json")
    public ChangeMobileStep2Response changeMobileStep2(ChangeMobileStep2Request req, @HeaderParam("auth") String authorization, @HeaderParam("appName") String appName, 
                                                       @HeaderParam("source") String source){
        ChangeMobileStep2Response response = new ChangeMobileStep2Response();
        PropertiesUtil propUtil = new PropertiesUtil();
        
        String auth = null;
        String propertyToken = null;
        String propertyAuthorization = null;
        Gson gson = new Gson();
        byte[] key = new byte[0];
        try {
            if(authorization!=null && authorization !=""){
            key = authorization.getBytes("UTF-8");
            }else{
                response.setMessage("Please pass a valid token !!");
                response.setStatus("Error");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty() &&
               source!=null && source !="" && !source.isEmpty()){
            System.out.println("appName/source in dmz login is : "+appName+", "+source);
            }else{
                response.setMessage("Please pass a valid appName, source !!");
                response.setStatus("Error");
                return response;
            }
            try {
                  propertyAuthorization = System.getenv("authorization");
                  propertyToken = System.getenv("tokenValue");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException uee) {
            // TODO: Add catch code
            uee.printStackTrace();
        }
        if (authorization.equals(propertyAuthorization)) {
            authorization = null;
            auth = HexString.toHexString(key);
        } else {
            response.setMessage("Unauthorized Token !!");
            response.setStatus("Error");
            authorization = null;
            return response;
        }
        authorization = null;
        Token token = new Token();
        String jwt = token.generateToken(auth, key);
        authorization = null;
        try {
            authorization = null;
            if (jwt.equals(propertyToken)) {
                
                if(req.getBuId()!=null && req.getBuId()!="" &&
                    req.getMobile()!=null && req.getMobile()!=""){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/user/changeMobileStep2";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithSource(serviceURL, gson.toJson(req), appName, source);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, ChangeMobileStep2Response.class);
                    }else{
                        response.setMessage("error in communication to internal changeMobileStep2 service");
                        response.setStatus("error");
                    }
                }else{
                    response.setMessage("buId,mobile cannot be null or empty");
                    response.setStatus("error");
                }
                
            }
        } catch (Exception e) {
            authorization = null;
            e.printStackTrace();
        } finally {
            authorization = null;
        }
        
        return response;
    }
    
    @PUT
    @Path("changeMobileStep3")
    @Produces("application/json")
    public ChangeMobileStep3Response changeMobileStep3(ChangeMobileStep3Request req, @HeaderParam("auth") String authorization, @HeaderParam("appName") String appName, 
                                                       @HeaderParam("source") String source){
        ChangeMobileStep3Response response = new ChangeMobileStep3Response();
        PropertiesUtil propUtil = new PropertiesUtil();
        
        String auth = null;
        String propertyToken = null;
        String propertyAuthorization = null;
        Gson gson = new Gson();
        byte[] key = new byte[0];
        try {
            if(authorization!=null && authorization !=""){
            key = authorization.getBytes("UTF-8");
            }else{
                response.setMessage("Please pass a valid token !!");
                response.setStatus("Error");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty() &&
               source!=null && source !="" && !source.isEmpty()){
            System.out.println("appName/source in dmz login is : "+appName+", "+source);
            }else{
                response.setMessage("Please pass a valid appName, source !!");
                response.setStatus("Error");
                return response;
            }
            try {
                  propertyAuthorization = System.getenv("authorization");
                  propertyToken = System.getenv("tokenValue");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException uee) {
            // TODO: Add catch code
            uee.printStackTrace();
        }
        if (authorization.equals(propertyAuthorization)) {
            authorization = null;
            auth = HexString.toHexString(key);
        } else {
            response.setMessage("Unauthorized Token !!");
            response.setStatus("Error");
            authorization = null;
            return response;
        }
        authorization = null;
        Token token = new Token();
        String jwt = token.generateToken(auth, key);
        authorization = null;
        try {
            authorization = null;
            if (jwt.equals(propertyToken)) {
                
                if(req.getBuId()!=null && req.getBuId()!="" &&
                    req.getMobile()!=null && req.getMobile()!="" &&
                    req.getOtp()!=null && req.getOtp()!=""){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/user/changeMobileStep3";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithSource(serviceURL, gson.toJson(req), appName, source);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, ChangeMobileStep3Response.class);
                    }else{
                        response.setMessage("error in communication to internal changeMobileStep3 service");
                        response.setStatus("error");
                    }
                }else{
                    response.setMessage("buId,mobile,otp cannot be null or empty");
                    response.setStatus("error");
                }
                
            }
        } catch (Exception e) {
            authorization = null;
            e.printStackTrace();
        } finally {
            authorization = null;
        }
        
        return response;
    }
    
    @PUT
    @Path("saveAccountInfo")
    @Produces("application/json")
    public SaveAccountInfoResponse saveAccountInfoProxy(SaveAccountInfoRequest req, @HeaderParam("auth") String authorization, @HeaderParam("appName") String appName, 
                                                        @HeaderParam("source") String source){
        SaveAccountInfoResponse response = new SaveAccountInfoResponse();
        PropertiesUtil propUtil = new PropertiesUtil();
        
        String auth = null;
        String propertyToken = null;
        String propertyAuthorization = null;
        Gson gson = new Gson();
        byte[] key = new byte[0];
        try {
            if(authorization!=null && authorization !=""){
            key = authorization.getBytes("UTF-8");
            }else{
                response.setMessage("Please pass a valid token !!");
                response.setStatus("Error");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty() &&
               source!=null && source !="" && !source.isEmpty()){
            System.out.println("appName/source in dmz saveAccountInfo is : "+appName+","+source);
            }else{
                response.setMessage("Please pass a valid appName, source !!");
                response.setStatus("Error");
                return response;
            }
            try {
                  propertyAuthorization = System.getenv("authorization");
                  propertyToken = System.getenv("tokenValue");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException uee) {
            // TODO: Add catch code
            uee.printStackTrace();
        }
        if (authorization.equals(propertyAuthorization)) {
            authorization = null;
            auth = HexString.toHexString(key);
        } else {
            response.setMessage("Unauthorized Token !!");
            response.setStatus("Error");
            authorization = null;
            return response;
        }
        authorization = null;
        Token token = new Token();
        String jwt = token.generateToken(auth, key);
        authorization = null;
        try {
            authorization = null;
            if (jwt.equals(propertyToken)) {
                
                if(req.getBuId()!=null && req.getBuId()!=""){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/user/saveAccountInfo";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithSource(serviceURL, gson.toJson(req), appName, source);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, SaveAccountInfoResponse.class);
                    }else{
                        response.setMessage("error in communication to internal changeMobileStep3 service");
                        response.setStatus("error");
                    }
                }else{
                    response.setMessage("buId cannot be null or empty");
                    response.setStatus("error");
                }
                
            }
        } catch (Exception e) {
            authorization = null;
            e.printStackTrace();
        } finally {
            authorization = null;
        }
        
        return response;
    }
    
    @PUT
    @Path("saveAddress")
    @Produces("application/json")
    public SaveAddressResponse saveAddressProxy(SaveAddressRequest req, @HeaderParam("auth") String authorization, @HeaderParam("appName") String appName, 
                                                @HeaderParam("source") String source){
        SaveAddressResponse response = new SaveAddressResponse();
        PropertiesUtil propUtil = new PropertiesUtil();
        
        String auth = null;
        String propertyToken = null;
        String propertyAuthorization = null;
        Gson gson = new Gson();
        byte[] key = new byte[0];
        try {
            if(authorization!=null && authorization !=""){
            key = authorization.getBytes("UTF-8");
            }else{
                response.setMessage("Please pass a valid token !!");
                response.setStatus("Error");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty() &&
               source!=null && source !="" && !source.isEmpty()){
            System.out.println("appName/source in dmz saveAccountInfo is : "+appName+", "+source);
            }else{
                response.setMessage("Please pass a valid appName, source !!");
                response.setStatus("Error");
                return response;
            }
            try {
                  propertyAuthorization = System.getenv("authorization");
                  propertyToken = System.getenv("tokenValue");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException uee) {
            // TODO: Add catch code
            uee.printStackTrace();
        }
        if (authorization.equals(propertyAuthorization)) {
            authorization = null;
            auth = HexString.toHexString(key);
        } else {
            response.setMessage("Unauthorized Token !!");
            response.setStatus("Error");
            authorization = null;
            return response;
        }
        authorization = null;
        Token token = new Token();
        String jwt = token.generateToken(auth, key);
        authorization = null;
        try {
            authorization = null;
            if (jwt.equals(propertyToken)) {
                
                if(req.getBuId()!=null && req.getBuId()!=""){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/user/saveAddress";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithSource(serviceURL, gson.toJson(req), appName, source);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, SaveAddressResponse.class);
                    }else{
                        response.setMessage("error in communication to internal saveAddress service");
                        response.setStatus("error");
                    }
                }else{
                    response.setMessage("buId cannot be null or empty");
                    response.setStatus("error");
                }
                
            }
        } catch (Exception e) {
            authorization = null;
            e.printStackTrace();
        } finally {
            authorization = null;
        }
        
        return response;
    }
    
    @PUT
    @Path("saveSubscription")
    @Produces("application/json")
    public SaveSubscriptionResponse saveSubscriptionProxy(SaveSubscriptionRequest req, @HeaderParam("auth") String authorization, @HeaderParam("appName") String appName, 
                                                          @HeaderParam("source") String source){
        SaveSubscriptionResponse response = new SaveSubscriptionResponse();
        PropertiesUtil propUtil = new PropertiesUtil();
        
        String auth = null;
        String propertyToken = null;
        String propertyAuthorization = null;
        Gson gson = new Gson();
        byte[] key = new byte[0];
        try {
            if(authorization!=null && authorization !=""){
            key = authorization.getBytes("UTF-8");
            }else{
                response.setMessage("Please pass a valid token !!");
                response.setStatus("Error");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty() &&
               source!=null && source !="" && !source.isEmpty()){
            System.out.println("appName/source in dmz saveSubscription is : "+appName+", "+source);
            }else{
                response.setMessage("Please pass a valid appName, source !!");
                response.setStatus("Error");
                return response;
            }
            try {
                  propertyAuthorization = System.getenv("authorization");
                  propertyToken = System.getenv("tokenValue");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException uee) {
            // TODO: Add catch code
            uee.printStackTrace();
        }
        if (authorization.equals(propertyAuthorization)) {
            authorization = null;
            auth = HexString.toHexString(key);
        } else {
            response.setMessage("Unauthorized Token !!");
            response.setStatus("Error");
            authorization = null;
            return response;
        }
        authorization = null;
        Token token = new Token();
        String jwt = token.generateToken(auth, key);
        authorization = null;
        try {
            authorization = null;
            if (jwt.equals(propertyToken)) {
                
                if(req.getBuId()!=null && req.getBuId()!=""){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/user/saveSubscription";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithSource(serviceURL, gson.toJson(req), appName, source);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, SaveSubscriptionResponse.class);
                    }else{
                        response.setMessage("error in communication to internal saveSubscription service");
                        response.setStatus("error");
                    }
                }else{
                    response.setMessage("buId cannot be null or empty");
                    response.setStatus("error");
                }
                
            }
        } catch (Exception e) {
            authorization = null;
            e.printStackTrace();
        } finally {
            authorization = null;
        }
        
        return response;
    }
    
    @PUT
    @Path("saveProfile")
    @Produces("application/json")
    public SaveProfileResponse saveProfileProxy(SaveProfileRequest req, @HeaderParam("auth") String authorization, @HeaderParam("appName") String appName, 
                                                @HeaderParam("source") String source){
        SaveProfileResponse response = new SaveProfileResponse();
        PropertiesUtil propUtil = new PropertiesUtil();
        
        String auth = null;
        String propertyToken = null;
        String propertyAuthorization = null;
        Gson gson = new Gson();
        byte[] key = new byte[0];
        try {
            if(authorization!=null && authorization !=""){
            key = authorization.getBytes("UTF-8");
            }else{
                response.setMessage("Please pass a valid token !!");
                response.setStatus("Error");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty() &&
               source!=null && source !="" && !source.isEmpty()){
            System.out.println("appName/source in dmz saveProfileProxy is : "+appName+", "+source);
            }else{
                response.setMessage("Please pass a valid appName, source !!");
                response.setStatus("Error");
                return response;
            }
            try {
                  propertyAuthorization = System.getenv("authorization");
                  propertyToken = System.getenv("tokenValue");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException uee) {
            // TODO: Add catch code
            uee.printStackTrace();
        }
        if (authorization.equals(propertyAuthorization)) {
            authorization = null;
            auth = HexString.toHexString(key);
        } else {
            response.setMessage("Unauthorized Token !!");
            response.setStatus("Error");
            authorization = null;
            return response;
        }
        authorization = null;
        Token token = new Token();
        String jwt = token.generateToken(auth, key);
        authorization = null;
        try {
            authorization = null;
            if (jwt.equals(propertyToken)) {
                
                if(req.getBuId()!=null && req.getBuId()!=""){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/user/saveProfile";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithSource(serviceURL, gson.toJson(req), appName, source);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, SaveProfileResponse.class);
                    }else{
                        response.setMessage("error in communication to internal saveProfile service");
                        response.setStatus("error");
                    }
                }else{
                    response.setMessage("buId cannot be null or empty");
                    response.setStatus("error");
                }
                
            }
        } catch (Exception e) {
            authorization = null;
            e.printStackTrace();
        } finally {
            authorization = null;
        }
        
        return response;
    }

    @PUT
    @Path("loadPersonalDetails")
    @Produces("application/json")
    public LoadPersonalDetailsResponse loadPersonalDetailsProxy(GenericRequest req, @HeaderParam("auth") String authorization, @HeaderParam("appName") String appName, 
                                                                @HeaderParam("source") String source){
        LoadPersonalDetailsResponse response = new LoadPersonalDetailsResponse();
        PropertiesUtil propUtil = new PropertiesUtil();
        
        String auth = null;
        String propertyToken = null;
        String propertyAuthorization = null;
        Gson gson = new Gson();
        byte[] key = new byte[0];
        try {
            if(authorization!=null && authorization !=""){
            key = authorization.getBytes("UTF-8");
            }else{
                response.setMessage("Please pass a valid token !!");
                response.setStatus("Error");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty() &&
               source!=null && source !="" && !source.isEmpty()){
            System.out.println("appName/source in dmz loadPersonalDetailsProxy is : "+appName+", "+source);
            }else{
                response.setMessage("Please pass a valid appName, source !!");
                response.setStatus("Error");
                return response;
            }
            try {
                  propertyAuthorization = System.getenv("authorization");
                  propertyToken = System.getenv("tokenValue");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException uee) {
            // TODO: Add catch code
            uee.printStackTrace();
        }
        if (authorization.equals(propertyAuthorization)) {
            authorization = null;
            auth = HexString.toHexString(key);
        } else {
            response.setMessage("Unauthorized Token !!");
            response.setStatus("Error");
            authorization = null;
            return response;
        }
        authorization = null;
        Token token = new Token();
        String jwt = token.generateToken(auth, key);
        authorization = null;
        try {
            authorization = null;
            if (jwt.equals(propertyToken)) {
                
                if(req.getBuId()!=null && req.getBuId()!=""){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/user/loadPersonalDetails";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithSource(serviceURL, gson.toJson(req), appName, source);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, LoadPersonalDetailsResponse.class);
                    }else{
                        response.setMessage("error in communication to internal loadPersonalDetails service");
                        response.setStatus("error");
                    }
                }else{
                    response.setMessage("buId cannot be null or empty");
                    response.setStatus("error");
                }
                
            }
        } catch (Exception e) {
            authorization = null;
            e.printStackTrace();
        } finally {
            authorization = null;
        }
        
        return response;
    }

    @PUT
    @Path("getMyGarageVehicles")
    @Produces("application/json")
    public MyGarageVehiclesResponse getMyGarageVehiclesProxy(GenericRequest req, @HeaderParam("auth") String authorization, @HeaderParam("appName") String appName, 
                                                             @HeaderParam("source") String source){
        MyGarageVehiclesResponse response = new MyGarageVehiclesResponse();
        PropertiesUtil propUtil = new PropertiesUtil();
        
        String auth = null;
        String propertyToken = null;
        String propertyAuthorization = null;
        Gson gson = new Gson();
        byte[] key = new byte[0];
        try {
            if(authorization!=null && authorization !=""){
            key = authorization.getBytes("UTF-8");
            }else{
                response.setMessage("Please pass a valid token !!");
                response.setStatus("Error");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty() &&
               source!=null && source !="" && !source.isEmpty()){
            System.out.println("appName/source in dmz getMyGarageVehiclesProxy is : "+appName+", "+source);
            }else{
                response.setMessage("Please pass a valid appName, source !!");
                response.setStatus("Error");
                return response;
            }
            try {
                  propertyAuthorization = System.getenv("authorization");
                  propertyToken = System.getenv("tokenValue");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException uee) {
            // TODO: Add catch code
            uee.printStackTrace();
        }
        if (authorization.equals(propertyAuthorization)) {
            authorization = null;
            auth = HexString.toHexString(key);
        } else {
            response.setMessage("Unauthorized Token !!");
            response.setStatus("Error");
            authorization = null;
            return response;
        }
        authorization = null;
        Token token = new Token();
        String jwt = token.generateToken(auth, key);
        authorization = null;
        try {
            authorization = null;
            if (jwt.equals(propertyToken)) {
                
                if(req.getBuId()!=null && req.getBuId()!=""){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/user/myGarageVehicles";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithSource(serviceURL, gson.toJson(req), appName, source);
                    if(responseString!=null){
                        System.out.println("response from internal service is : "+responseString);
                        response = gson.fromJson(responseString, MyGarageVehiclesResponse.class);
                    }else{
                        response.setMessage("error in communication to internal getMyGarageVehicles service");
                        response.setStatus("error");
                    }
                }else{
                    response.setMessage("buId cannot be null or empty");
                    response.setStatus("error");
                }
                
            }
        } catch (Exception e) {
            authorization = null;
            e.printStackTrace();
        } finally {
            authorization = null;
        }
        
        return response;
    }
    
    @PUT
    @Path("addCar")
    @Produces("application/json")
    public GenericResponse addCarProxy(AddCarRequest req, @HeaderParam("auth") String authorization, @HeaderParam("appName") String appName, 
                                       @HeaderParam("source") String source){
        GenericResponse response = new GenericResponse();
        PropertiesUtil propUtil = new PropertiesUtil();
        
        String auth = null;
        String propertyToken = null;
        String propertyAuthorization = null;
        Gson gson = new Gson();
        byte[] key = new byte[0];
        try {
            if(authorization!=null && authorization !=""){
            key = authorization.getBytes("UTF-8");
            }else{
                response.setMessage("Please pass a valid token !!");
                response.setStatus("Error");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty() &&
               source!=null && source !="" && !source.isEmpty()){
            System.out.println("appName/source in dmz addCarProxy is : "+appName+", "+source);
            }else{
                response.setMessage("Please pass a valid appName, source !!");
                response.setStatus("Error");
                return response;
            }
            try {
                  propertyAuthorization = System.getenv("authorization");
                  propertyToken = System.getenv("tokenValue");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException uee) {
            // TODO: Add catch code
            uee.printStackTrace();
        }
        if (authorization.equals(propertyAuthorization)) {
            authorization = null;
            auth = HexString.toHexString(key);
        } else {
            response.setMessage("Unauthorized Token !!");
            response.setStatus("Error");
            authorization = null;
            return response;
        }
        authorization = null;
        Token token = new Token();
        String jwt = token.generateToken(auth, key);
        authorization = null;
        try {
            authorization = null;
            if (jwt.equals(propertyToken)) {
                
                if(req.getBuId()!=null && req.getBuId()!=""){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/user/addCar";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithSource(serviceURL, gson.toJson(req), appName, source);
                    if(responseString!=null){
                        System.out.println("response from internal service is : "+responseString);
                        response = gson.fromJson(responseString, GenericResponse.class);
                    }else{
                        response.setMessage("error in communication to internal addCar service");
                        response.setStatus("error");
                    }
                }else{
                    response.setMessage("buId cannot be null or empty");
                    response.setStatus("error");
                }
                
            }
        } catch (Exception e) {
            authorization = null;
            e.printStackTrace();
        } finally {
            authorization = null;
        }
        
        return response;
    }
    
    @PUT
    @Path("removeCar")
    @Produces("application/json")
    public GenericResponse removeCarProxy(RemoveCarRequest req, @HeaderParam("auth") String authorization, @HeaderParam("appName") String appName, 
                                          @HeaderParam("source") String source){
        GenericResponse response = new GenericResponse();
        PropertiesUtil propUtil = new PropertiesUtil();
        
        String auth = null;
        String propertyToken = null;
        String propertyAuthorization = null;
        Gson gson = new Gson();
        byte[] key = new byte[0];
        try {
            if(authorization!=null && authorization !=""){
            key = authorization.getBytes("UTF-8");
            }else{
                response.setMessage("Please pass a valid token !!");
                response.setStatus("Error");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty() &&
               source!=null && source !="" && !source.isEmpty()){
            System.out.println("appName/source in dmz addCarProxy is : "+appName+", "+source);
            }else{
                response.setMessage("Please pass a valid appName, source !!");
                response.setStatus("Error");
                return response;
            }
            try {
                  propertyAuthorization = System.getenv("authorization");
                  propertyToken = System.getenv("tokenValue");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException uee) {
            // TODO: Add catch code
            uee.printStackTrace();
        }
        if (authorization.equals(propertyAuthorization)) {
            authorization = null;
            auth = HexString.toHexString(key);
        } else {
            response.setMessage("Unauthorized Token !!");
            response.setStatus("Error");
            authorization = null;
            return response;
        }
        authorization = null;
        Token token = new Token();
        String jwt = token.generateToken(auth, key);
        authorization = null;
        try {
            authorization = null;
            if (jwt.equals(propertyToken)) {
                
                if(req.getBuId()!=null && req.getBuId()!="" && !req.getBuId().isEmpty() &&
                   req.getCarId()!=null && req.getCarId()!="" && !req.getCarId().isEmpty()){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/user/removeCar";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithSource(serviceURL, gson.toJson(req), appName, source);
                    if(responseString!=null){
                        System.out.println("response from internal service is : "+responseString);
                        response = gson.fromJson(responseString, GenericResponse.class);
                    }else{
                        response.setMessage("error in communication to internal removeCar service");
                        response.setStatus("Error");
                    }
                }else{
                    response.setMessage("buId and carId cannot be null or empty");
                    response.setStatus("Error");
                }
                
            }
        } catch (Exception e) {
            authorization = null;
            e.printStackTrace();
        } finally {
            authorization = null;
        }
        
        return response;
    }
}
