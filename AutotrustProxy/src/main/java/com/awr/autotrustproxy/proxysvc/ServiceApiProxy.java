package com.awr.autotrustproxy.proxysvc;

import com.awr.autotrustproxy.dto.ResponseObject;
import com.awr.autotrustproxy.request.CreateBookingRequest;
import com.awr.autotrustproxy.request.CreateMakanakRequest;
import com.awr.autotrustproxy.request.CustomerDetails;
import com.awr.autotrustproxy.request.CustomersRequest;
import com.awr.autotrustproxy.request.InstalledBaseRequest;
import com.awr.autotrustproxy.request.ItemRecordRequest;
import com.awr.autotrustproxy.request.LoginRequest;
import com.awr.autotrustproxy.response.CreateRetailCustomerResponse;
import com.awr.autotrustproxy.response.GenericResponse;
import com.awr.autotrustproxy.response.InstalledBaseCreateUpdateResponse;
import com.awr.autotrustproxy.response.ItemRecordResponse;
import com.awr.autotrustproxy.response.LoginResponse;
import com.awr.autotrustproxy.response.PartyDetailsResponse;
import com.awr.autotrustproxy.util.HexString;
import com.awr.autotrustproxy.util.PropertiesUtil;
import com.awr.autotrustproxy.util.RestUtil;
import com.awr.autotrustproxy.util.Token;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("autotrustServiceProxy")
@Consumes("application/json")
@Produces("application/json")
public class ServiceApiProxy {
    public ServiceApiProxy() {
        super();
    }
    
    @PUT
    @Path("itemRecordService")
    @Produces("application/json")
    public ItemRecordResponse itemRecordServiceProxy(ItemRecordRequest req, @HeaderParam("auth") String authorization, @HeaderParam("appName") String appName){
        ItemRecordResponse response = new ItemRecordResponse();
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
                response.setRetCode("E");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty()){
            System.out.println("appName in dmz login is : "+appName);
            }else{
                response.setRetMsg("Please pass a valid appName !!");
                response.setRetCode("E");
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
            response.setRetCode("E");
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
                
                if(req.getOrganizationID()!=null && req.getOrganizationID()!="" && !req.getOrganizationID().isEmpty() &&
                    req.getItemType()!=null && req.getItemType()!="" && !req.getItemType().isEmpty() &&
                    req.getModel()!=null && req.getModel()!="" && !req.getModel().isEmpty() &&
                    req.getUserID()!=null && req.getUserID()!="" && !req.getUserID().isEmpty() &&
                    req.getServiceAdvisorID()!=null && req.getServiceAdvisorID()!="" && !req.getServiceAdvisorID().isEmpty()){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/autotrustService/itemRecordService";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req), appName);
                    if(responseString!=null){
                        System.out.println("response string is : "+responseString);
                        response = gson.fromJson(responseString, ItemRecordResponse.class);
    //                        System.out.println("response from internal obj data is : "+response.getBuId()+" , "+response.getDateOfBirth());
                        
                    }else{
                        response.setRetMsg("error in communication to internal itemRecordService service");
                        response.setRetCode("E");
                    }
                }else{
                    response.setRetMsg("OrganizationID/ItemType/Model/UserID/ServiceAdvisorID cannot be null or empty");
                    response.setRetCode("E");
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
    
    @GET
    @Path("getPartyDetails")
    @Produces("application/json")
    public PartyDetailsResponse getPartyDetailsProxy(@QueryParam("partyName") String partyName,
                                                      @QueryParam("partyType") String partyType,
                                                      @QueryParam("partyID") String partyID,
                                                      @QueryParam("contactNo") String contactNo, 
                                                     @HeaderParam("auth") String authorization, @HeaderParam("appName") String appName){
        PartyDetailsResponse response = new PartyDetailsResponse();
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
                response.setRetCode("E");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty()){
            System.out.println("appName in dmz getPartyDetailsProxy is : "+appName);
            }else{
                response.setRetMsg("Please pass a valid appName !!");
                response.setRetCode("E");
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
            response.setRetCode("E");
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
                
                if(partyType!=null && partyType!="" && !partyType.isEmpty()){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/autotrustService/partyDetails?";
                    if(partyType!=null){
                        serviceURL = serviceURL +"partyType="+partyType;
                    }
                    if(partyName!=null){
                        serviceURL = serviceURL +"&partyName="+partyName;
                    }
                    if(partyID!=null){
                        serviceURL = serviceURL +"&partyID="+partyID;
                    }
                    if(contactNo!=null){
                        serviceURL = serviceURL +"&contactNo="+contactNo;
                    }
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callGetServiceWithAppName(serviceURL, appName);
                    if(responseString!=null){
                        System.out.println("response string is : "+responseString);
                        response = gson.fromJson(responseString, PartyDetailsResponse.class);
    //                        System.out.println("response from internal obj data is : "+response.getBuId()+" , "+response.getDateOfBirth());
                        
                    }else{
                        response.setRetMsg("error in communication to internal gePartyDetails service");
                        response.setRetCode("E");
                    }
                }else{
                    response.setRetMsg("partyType cannot be null or empty");
                    response.setRetCode("E");
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
    @Path("createOrganizationParty")
    @Produces("application/json")
    public ResponseObject createOrganizaitonCustomer(CustomersRequest req, @HeaderParam("auth") String authorization, @HeaderParam("appName") String appName){
        ResponseObject response = new ResponseObject();
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
                response.setResponseMessage("Please pass a valid token !!");
                response.setResponseCode("E");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty()){
            System.out.println("appName in dmz createOrganizaitonCustomer is : "+appName);
            }else{
                response.setResponseMessage("Please pass a valid appName !!");
                response.setResponseCode("E");
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
            response.setResponseMessage("Unauthorized Token !!");
            response.setResponseCode("E");
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
                
                if(req.getOrgID()!=null && req.getOrgID()!="" && !req.getOrgID().isEmpty()){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/autotrustService/createOrganizationParty";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req), appName);
                    if(responseString!=null){
                        System.out.println("response string is : "+responseString);
                        response = gson.fromJson(responseString, ResponseObject.class);
    //                        System.out.println("response from internal obj data is : "+response.getBuId()+" , "+response.getDateOfBirth());
                        
                    }else{
                        response.setResponseMessage("error in communication to internal itemRecordService service");
                        response.setResponseCode("E");
                    }
                }else{
                    response.setResponseMessage("OrgID cannot be null or empty");
                    response.setResponseCode("E");
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
    @Path("createRetailCustomer")
    @Produces("application/json")
    public CreateRetailCustomerResponse createRetailCustomer(CustomerDetails req, @QueryParam("oprUnitId") String operatingUnitId,
                                                             @HeaderParam("auth") String authorization, @HeaderParam("appName") String appName){
        CreateRetailCustomerResponse response = new CreateRetailCustomerResponse();
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
                response.setRetCode("E");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty()){
            System.out.println("appName in dmz createRetailCustomer is : "+appName);
            }else{
                response.setRetMsg("Please pass a valid appName !!");
                response.setRetCode("E");
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
            response.setRetCode("E");
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
                
                if(operatingUnitId!=null && operatingUnitId!="" && !operatingUnitId.isEmpty()){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/autotrustService/createRetailParty?oprUnitId="+operatingUnitId;
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req), appName);
                    if(responseString!=null){
                        System.out.println("response string is : "+responseString);
                        response = gson.fromJson(responseString, CreateRetailCustomerResponse.class);
    //                        System.out.println("response from internal obj data is : "+response.getBuId()+" , "+response.getDateOfBirth());
                        
                    }else{
                        response.setRetMsg("error in communication to internal createRetailParty service");
                        response.setRetCode("E");
                    }
                }else{
                    response.setRetMsg("operatingUnitId cannot be null or empty");
                    response.setRetCode("E");
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
    @Path("createAndUpdateInstallBase")
    @Produces("application/json")
    public InstalledBaseCreateUpdateResponse createAndUpdateInstallBase(InstalledBaseRequest req, @HeaderParam("auth") String authorization, 
                                                                   @HeaderParam("appName") String appName){
        InstalledBaseCreateUpdateResponse response = new InstalledBaseCreateUpdateResponse();
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
                response.setRetCode("E");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty()){
            System.out.println("appName in dmz createAndUpdateInstallBase is : "+appName);
            }else{
                response.setRetMsg("Please pass a valid appName !!");
                response.setRetCode("E");
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
            response.setRetCode("E");
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
                
                if(req.getUserID()!=null && req.getUserID()!="" && !req.getUserID().isEmpty()){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/autotrustService/createAndUpdateInstallBase";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req), appName);
                    if(responseString!=null){
                        System.out.println("response string is : "+responseString);
                        response = gson.fromJson(responseString, InstalledBaseCreateUpdateResponse.class);
    //                        System.out.println("response from internal obj data is : "+response.getBuId()+" , "+response.getDateOfBirth());
                        
                    }else{
                        response.setRetMsg("error in communication to internal createRetailParty service");
                        response.setRetCode("E");
                    }
                }else{
                    response.setRetMsg("UserID cannot be null or empty");
                    response.setRetCode("E");
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
    @Path("createMakanak")
    @Produces("application/json")
    public GenericResponse createMakanak(CreateMakanakRequest req, @HeaderParam("auth") String authorization, 
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
            System.out.println("appName in dmz createMakanak is : "+appName);
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
                
                if(req.getEmailId()!=null && req.getEmailId()!="" && !req.getEmailId().isEmpty()){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/autotrustService/createMakanak";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req), appName);
                    if(responseString!=null){
                        System.out.println("response string is : "+responseString);
                        response = gson.fromJson(responseString, GenericResponse.class);
    //                        System.out.println("response from internal obj data is : "+response.getBuId()+" , "+response.getDateOfBirth());
                        
                    }else{
                        response.setMessage("error in communication to internal createMakanak service");
                        response.setStatus("E");
                    }
                }else{
                    response.setMessage("required parameters cannot be null or empty");
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
    
    @PUT
    @Path("createBooking")
    @Produces("application/json")
    public GenericResponse createBooking(CreateBookingRequest req, @HeaderParam("auth") String authorization, 
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
            System.out.println("appName in dmz createMakanak is : "+appName);
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
                
                if(req.getBookingType()!=null && req.getBookingType()!="" && !req.getBookingType().isEmpty()){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/autotrustService/createBooking";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req), appName);
                    if(responseString!=null){
                        System.out.println("response string is : "+responseString);
                        response = gson.fromJson(responseString, GenericResponse.class);
    //                        System.out.println("response from internal obj data is : "+response.getBuId()+" , "+response.getDateOfBirth());
                        
                    }else{
                        response.setMessage("error in communication to internal createBooking service");
                        response.setStatus("E");
                    }
                }else{
                    response.setMessage("required parameters cannot be null or empty");
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
