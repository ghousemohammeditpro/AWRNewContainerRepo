package com.awr.autotrustproxy.proxysvc;

import com.awr.autotrustproxy.request.LoginRequest;
import com.awr.autotrustproxy.request.UserRegStep1Request;
import com.awr.autotrustproxy.request.UserRegStep2Request;
import com.awr.autotrustproxy.request.UserRegStep3Request;
import com.awr.autotrustproxy.response.LoginResponse;
import com.awr.autotrustproxy.response.UserRegStep1Response;
import com.awr.autotrustproxy.response.UserRegStep2Response;
import com.awr.autotrustproxy.response.UserRegStep3Response;
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

@Path("userRegistrationProxy")
@Consumes("application/json")
@Produces("application/json")
public class UserRegistrationProxy {
    public UserRegistrationProxy() {
        super();
    }
    @PUT
    @Path("step1")
    @Produces("application/json")
    public UserRegStep1Response step1(UserRegStep1Request req, @HeaderParam("auth") String authorization, @HeaderParam("appName") String appName, 
                                      @HeaderParam("source") String source){
        UserRegStep1Response response = new UserRegStep1Response();
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
                
                if(req.getEmail()!=null && req.getEmail()!="" &&
                    req.getMobile()!=null && req.getMobile()!=""){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/userRegistration/step1";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithSource(serviceURL, gson.toJson(req), appName, source);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, UserRegStep1Response.class);
                    }else{
                        response.setMessage("error in communication to internal createAccountStep1 service");
                        response.setStatus("error");
                    }
                }else{
                    response.setMessage("email/mobile cannot be null or empty");
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
    @Path("step2")
    @Produces("application/json")
    public UserRegStep2Response step2(UserRegStep2Request req, @HeaderParam("auth") String authorization, @HeaderParam("appName") String appName, 
                                      @HeaderParam("source") String source){
        UserRegStep2Response response = new UserRegStep2Response();
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
                
                if(req.getEmail()!=null && req.getEmail()!="" &&
                    req.getMobile()!=null && req.getMobile()!="" &&
                    req.getPassword()!=null && req.getPassword()!="" &&
                    req.getBuId()!=null && req.getBuId()!=""){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/userRegistration/step2";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithSource(serviceURL, gson.toJson(req), appName, source);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, UserRegStep2Response.class);
                    }else{
                        response.setMessage("error in communication to internal userRegStep2 service");
                        response.setStatus("error");
                    }
                }else{
                    response.setMessage("email,mobile,Password, buID cannot be null or empty");
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
    @Path("step3")
    @Produces("application/json")
    public UserRegStep3Response step3(UserRegStep3Request req, @HeaderParam("auth") String authorization, @HeaderParam("appName") String appName, 
                                      @HeaderParam("source") String source){
        UserRegStep3Response response = new UserRegStep3Response();
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
                
                if(req.getBuId()!=null && req.getBuId()!=""){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/userRegistration/step3";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithSource(serviceURL, gson.toJson(req), appName, source);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, UserRegStep3Response.class);
                    }else{
                        response.setMessage("error in communication to internal userRegStep3 service");
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
}
