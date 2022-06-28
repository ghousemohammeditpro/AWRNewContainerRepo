package com.awr.autotrustproxy.proxysvc;

import com.awr.autotrustproxy.request.CreateSFLeadRequest;
import com.awr.autotrustproxy.request.CreateSFLeadRequestV1;
import com.awr.autotrustproxy.request.GetModelsRequest;
import com.awr.autotrustproxy.request.GetStockRequest;
import com.awr.autotrustproxy.request.SendMailRequest;
import com.awr.autotrustproxy.response.CommonLookupResponse;
import com.awr.autotrustproxy.response.CreateSFLeadResponse;
import com.awr.autotrustproxy.response.GenericResponse;
import com.awr.autotrustproxy.response.StockResponse;
import com.awr.autotrustproxy.util.HexString;
import com.awr.autotrustproxy.util.PropertiesUtil;
import com.awr.autotrustproxy.util.RestUtil;
import com.awr.autotrustproxy.util.Token;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.regex.Matcher; 
import java.util.regex.Pattern;

@Path("chatbotProxy")
@Consumes("application/json")
@Produces("application/json")
public class ChatbotProxyService {
    public ChatbotProxyService() {
        super();
    }
    @GET
    @Path("getBrands")
    @Produces("application/json")
    public CommonLookupResponse getBrands(@QueryParam("stockType") String stockType, 
                                          @HeaderParam("auth") String authorization, 
                                          @HeaderParam("appName") String appName){
        CommonLookupResponse response = new CommonLookupResponse();
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
                response.setRetMsg("Please pass a valid token !!");
                response.setRetcode("Error");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty()){
            System.out.println("appName in dmz getBrands is : "+appName);
            }else{
                response.setRetMsg("Please pass a valid appName !!");
                response.setRetcode("Error");
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
            response.setRetMsg("Unauthorized Token !!");
            response.setRetcode("Error");
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
                
                if(stockType!=""){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/chatbot/getBrands?stockType="+stockType;
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callGetServiceWithAppName(serviceURL, appName);
                    if(responseString!=null){
//                        System.out.println("response string is : "+responseString);
                        response = gson.fromJson(responseString, CommonLookupResponse.class);
                        System.out.println("response from internal svc is : "+response.toString());
                        
                    }else{
                        response.setRetMsg("error in communication to internal service");
                        response.setRetcode("Error");
                    }
                }else{
                    response.setRetMsg("stockType cannot be null or empty");
                    response.setRetcode("Error");
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
    @Path("getModels")
    @Produces("application/json")
    public CommonLookupResponse getModels(GetModelsRequest req, 
                                          @HeaderParam("auth") String authorization, 
                                          @HeaderParam("appName") String appName){
        CommonLookupResponse response = new CommonLookupResponse();
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
                response.setRetMsg("Please pass a valid token !!");
                response.setRetcode("Error");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty()){
            System.out.println("appName in dmz getModels is : "+appName);
            }else{
                response.setRetMsg("Please pass a valid appName !!");
                response.setRetcode("Error");
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
            response.setRetMsg("Unauthorized Token !!");
            response.setRetcode("Error");
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
                
                if(req.getMake()!="" && req.getStockType()!=null){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/chatbot/getModels";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req), appName);
                    if(responseString!=null){
//                        System.out.println("response string is : "+responseString);
                        response = gson.fromJson(responseString, CommonLookupResponse.class);
                        System.out.println("response from internal svc is : "+response.toString());
                        
                    }else{
                        response.setRetMsg("error in communication to internal service");
                        response.setRetcode("Error");
                    }
                }else{
                    response.setRetMsg("stockType/make cannot be null or empty");
                    response.setRetcode("Error");
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
    @Path("getStock")
    @Produces("application/json")
    public StockResponse getStock(GetStockRequest req, 
                                  @HeaderParam("auth") String authorization, 
                                  @HeaderParam("appName") String appName){
        StockResponse response = new StockResponse();
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
                response.setRetMsg("Please pass a valid token !!");
                response.setRetCode("Error");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty()){
            System.out.println("appName/source in dmz getModels is : "+appName);
            }else{
                response.setRetMsg("Please pass a valid appName and source !!");
                response.setRetCode("Error");
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
            response.setRetMsg("Unauthorized Token !!");
            response.setRetCode("Error");
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
                
                if(req.getMake()!="" && req.getStockType()!=null){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/chatbot/getStock";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req), appName);
                    if(responseString!=null){
//                        System.out.println("response string is : "+responseString);
                        response = gson.fromJson(responseString, StockResponse.class);
                        System.out.println("response from internal svc is : "+response.toString());
                        
                    }else{
                        response.setRetMsg("error in communication to internal service");
                        response.setRetCode("Error");
                    }
                }else{
                    response.setRetMsg("stockType/make cannot be null or empty");
                    response.setRetCode("Error");
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
    @Path("createLead")
    public CreateSFLeadResponse createLead(CreateSFLeadRequest req, @HeaderParam("appName") String appName) {
    CreateSFLeadResponse response = new CreateSFLeadResponse();

    PropertiesUtil propUtil = new PropertiesUtil();
    Gson gson = new Gson();

    try {
            if(appName!=null && appName!="" && !appName.isEmpty()){
            if(req.getAppSourceName()!=null && req.getAppSourceName()!="" && req.getComments()!=null && req.getComments()!=""){
            String serviceURL = System.getenv("MAIN_URL")+"/chatbot/createLead";
            String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req), appName);
            
            System.out.println("response from internal create lead service is : "+responseString);
          
            if(responseString!=null){
                
                response = gson.fromJson(responseString, CreateSFLeadResponse.class);

            }
            else{
                System.out.println("communication error in update deal activity");
                }
            }else{
                response.setRetCode("E");
                response.setRetMsg("mandatory params cannot be null or empty");
            }
            }else{
                response.setRetCode("E");
                response.setRetMsg("appName cannot be null or empty");
            }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
    }

    return response;
    }
    

    @PUT
    @Path("createLeadV1")
    public CreateSFLeadResponse createLeadV1(CreateSFLeadRequestV1 req, @HeaderParam("appName") String appName) {
    CreateSFLeadResponse response = new CreateSFLeadResponse();

    PropertiesUtil propUtil = new PropertiesUtil();
    Gson gson = new Gson();

    try {
        //checking the 
            if(appName!=null && appName!="" && !appName.isEmpty()){
                if(req.getAppSourceName()!=null && req.getAppSourceName()!="" && req.getComments()!=null && req.getComments()!=""){
                    //checking the special character as per PTVA report
                    Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
                    Matcher matcher = pattern.matcher(req.getFname()+req.getLname());
                    boolean isStringContainsSpecialCharacter = matcher.find();
                    if(isStringContainsSpecialCharacter){
                        response.setRetCode("E");
                        response.setRetMsg("Special characters are not allowed. Please provide proper input.");
                    }else{    
                        String serviceURL = System.getenv("MAIN_URL")+"/chatbot/createLeadV1";
                        String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req), appName);
                        
                        System.out.println("response from internal create lead service is : "+responseString);
                    
                        if(responseString!=null){
                            
                            response = gson.fromJson(responseString, CreateSFLeadResponse.class);

                        }
                        else{
                            System.out.println("communication error in update deal activity");
                            }
                    }
                }else{
                    response.setRetCode("E");
                    response.setRetMsg("mandatory params cannot be null or empty");
                }
            }else{
                response.setRetCode("E");
                response.setRetMsg("appName cannot be null or empty");
            }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
    }

    return response;
    }
    

    @PUT
    @Path("sendMail")
    @Produces("application/json")
    public GenericResponse sendMail(SendMailRequest req, 
                                          @HeaderParam("auth") String authorization, 
                                          @HeaderParam("appName") String appName){
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
                response.setStatus("E");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty()){
            System.out.println("appName in dmz sendMail is : "+appName);
            }else{
                response.setMessage("Please pass a valid appName !!");
                response.setStatus("E");
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
            response.setStatus("E");
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
                
                if(req.getSenderEmail()!="" && req.getSenderEmail()!=null &&
                    req.getRecipients()!="" && req.getRecipients()!=null &&
                    req.getSubject()!="" && req.getSubject()!=null){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/chatbot/sendMail";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req), appName);
                    if(responseString!=null){
    //                        System.out.println("response string is : "+responseString);
                        response = gson.fromJson(responseString, GenericResponse.class);
                        System.out.println("response from internal svc is : "+response.toString());
                        
                    }else{
                        response.setMessage("error in communication to internal service");
                        response.setStatus("E");
                    }
                }else{
                    response.setMessage("email params cannot be null or empty");
                    response.setStatus("E");
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
