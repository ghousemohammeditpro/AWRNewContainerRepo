package com.awr.autotrustproxy.proxysvc;

import com.awr.autotrustproxy.request.CommonLookupRequest;
import com.awr.autotrustproxy.request.CreateAppointmentRequest;
import com.awr.autotrustproxy.request.CreateLeadRequest;
import com.awr.autotrustproxy.request.CreateReservationVehicleRequest;
import com.awr.autotrustproxy.request.ForgotPasswordRequest;
import com.awr.autotrustproxy.request.GetBranchesRequest;
import com.awr.autotrustproxy.request.GetDeviceTokenRequest;
import com.awr.autotrustproxy.request.InsertAddonRequest;
import com.awr.autotrustproxy.request.LoginRequest;
import com.awr.autotrustproxy.request.MyStatementRequest;
import com.awr.autotrustproxy.request.RequestBean;
import com.awr.autotrustproxy.request.ReserveVehicleRequest;
import com.awr.autotrustproxy.request.ReserveVehicleRequestKentico;
import com.awr.autotrustproxy.request.SendNotificationRequest;
import com.awr.autotrustproxy.request.SendNotificationRequestKentico;
import com.awr.autotrustproxy.request.ServiceAdvisorRequest;
import com.awr.autotrustproxy.request.SubmitAddonRequest;
import com.awr.autotrustproxy.response.AvailabilityResponse;
import com.awr.autotrustproxy.response.BranchesResponse;
import com.awr.autotrustproxy.response.CommonLookupResponse;
import com.awr.autotrustproxy.response.CpoDealerVehiclesResponse;
import com.awr.autotrustproxy.response.CreateAppointmentResponse;
import com.awr.autotrustproxy.response.CreateLeadResponse;
import com.awr.autotrustproxy.response.CreateReservationVehicleResponse;
import com.awr.autotrustproxy.response.CustomerVehiclesResponse;
import com.awr.autotrustproxy.response.ForgotPasswordResponse;
import com.awr.autotrustproxy.response.GenericResponse;
import com.awr.autotrustproxy.response.GenericResponseKentico;
import com.awr.autotrustproxy.response.GetReservationNumberResponse;
import com.awr.autotrustproxy.response.GetTokenIdResponse;
import com.awr.autotrustproxy.response.LeadDetailsBatchResponse;
import com.awr.autotrustproxy.response.LeadDetailsResponse;
import com.awr.autotrustproxy.response.LoginResponse;

import com.awr.autotrustproxy.response.MyStatementResponse;
import com.awr.autotrustproxy.response.SalesOrdersResponse;
import com.awr.autotrustproxy.response.ServiceAdvisorResponse;
import com.awr.autotrustproxy.response.VehicleImagesResponse;
import com.awr.autotrustproxy.response.VehiclesResponse;
import com.awr.autotrustproxy.util.HexString;
import com.awr.autotrustproxy.util.LoginUtil;
import com.awr.autotrustproxy.util.PropertiesUtil;

import com.awr.autotrustproxy.util.RestUtil;
import com.awr.autotrustproxy.util.TestUtil;
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


@Path("autotrustProxy")
@Consumes("application/json")
@Produces("application/json")
public class AutotrustProxySvc {
    public AutotrustProxySvc() {
        super();
    }
    
    @PUT
    @Path("loginService")
    @Produces("application/json")
    public LoginResponse login(LoginRequest req, @HeaderParam("auth") String authorization, @HeaderParam("appName") String appName, 
                               @HeaderParam("source") String source){
        LoginResponse response = new LoginResponse();
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
            System.out.println("appName/source in dmz login is : "+appName+" , "+source);
            }else{
                response.setMessage("Please pass a valid appName and source !!");
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
                
                if(req.getUsername()!=null && req.getUsername()!="" &&
                    req.getIsFBLogin()!=null && req.getIsFBLogin()!=""){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/autotrust/loginService";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithSource(serviceURL, gson.toJson(req), appName, source);
                    if(responseString!=null){
                        System.out.println("response string is : "+responseString);
                        response = gson.fromJson(responseString, LoginResponse.class);
//                        System.out.println("response from internal obj data is : "+response.getBuId()+" , "+response.getDateOfBirth());
                        
                    }else{
                        response.setMessage("error in communication to internal autotrust login service");
                        response.setStatus("Error");
                    }
                }else{
                    response.setMessage("username cannot be null or empty");
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
    
    @PUT
    @Path("forgotPwdProxy")
    @Produces("application/json")
    public ForgotPasswordResponse forgotPwdProxy(ForgotPasswordRequest req, @HeaderParam("auth") String authorization, @HeaderParam("appName") String appName, 
                                                 @HeaderParam("source") String source){
        ForgotPasswordResponse response = new ForgotPasswordResponse();
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
            System.out.println("appName/source in dmz forgotPwdProxy is : "+appName+" , "+source);
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
                
                if(req.getEmailMobile()!=null && req.getEmailMobile()!=""){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/autotrust/forgotPwdService";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithSource(serviceURL, gson.toJson(req), appName, source);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, ForgotPasswordResponse.class);
                    }else{
                        response.setMessage("error in communication to internal autotrust forgotPwd service");
                        response.setStatus("Error");
                    }
                }else{
                    response.setMessage("email/mobile cannot be null or empty");
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

    @GET
    @Path("getCpoVehiclesProxy")
    @Produces("application/json")
    public CpoDealerVehiclesResponse getCpoVehiclesProxy(@HeaderParam("auth") String authorization, @HeaderParam("appName") String appName){
        CpoDealerVehiclesResponse response = new CpoDealerVehiclesResponse();
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
            if(appName!=null && appName !="" && !appName.isEmpty()){
            System.out.println("appName in dmz getCpoVehiclesProxy is : "+appName);
            }else{
                response.setMessage("Please pass a valid appName !!");
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
                
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/autotrust/getCpoVehicles";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callGetServiceWithAppName(serviceURL, appName);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, CpoDealerVehiclesResponse.class);
                    }else{
                        response.setMessage("error in communication to internal autotrust getCpoVehicles service");
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
    
    @PUT
    @Path("getCommonLookupProxy")
    public CommonLookupResponse getCommonLookupProxy(CommonLookupRequest req,
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
            System.out.println("appName in dmz getCommonLookupProxy is : "+appName);
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
                if(req.getLookupType()!=null && req.getLookupType()!="" && !req.getLookupType().isEmpty()){
                    
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/autotrust/getCommonLookup";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req), appName);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, CommonLookupResponse.class);
                    }else{
                        response.setRetMsg("error in communication to internal autotrust getCommonLookup service");
                        response.setRetcode("Error");
                    }
                    
                }else{
                    response.setRetMsg("lookupType cannot be null or empty");
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
    @Path("createReservationVehicleProxy")
    public CreateReservationVehicleResponse createReservationVehicleProxy(CreateReservationVehicleRequest req,
                                                    @HeaderParam("auth") String authorization, 
                                                    @HeaderParam("appName") String appName){
        CreateReservationVehicleResponse response = new CreateReservationVehicleResponse();
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
            System.out.println("appName in dmz createReservationVehicleProxy is : "+appName);
            }else{
                response.setRetMsg("Please pass a valid appName !!");
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
                if(req.getItemId()!=null && req.getItemId()!="" && !req.getItemId().isEmpty() &&
                    req.getSourceName()!=null && req.getSourceName()!="" && !req.getSourceName().isEmpty() &&
                    req.getUserId()!=null && req.getUserId()!="" && !req.getUserId().isEmpty() &&
                    req.getSourceLeadKey()!=null && req.getSourceLeadKey()!="" && !req.getSourceLeadKey().isEmpty()){
                    
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/autotrust/createReservationVehicle";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req), appName);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, CreateReservationVehicleResponse.class);
                    }else{
                        response.setRetMsg("error in communication to internal autotrust createReservationVehicle service");
                        response.setRetCode("Error");
                    }
                    
                }else{
                    response.setRetMsg("required parameters missing or empty");
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
 
 
    @GET
    @Path("getVehicleImagesProxy")
    public VehicleImagesResponse getVehicleImagesProxy(@QueryParam("vinNumber") String vinNumber,
                                                           @HeaderParam("auth") String authorization, 
                                                           @HeaderParam("appName") String appName){
        VehicleImagesResponse response = new VehicleImagesResponse();
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
            if(appName!=null && appName !="" && !appName.isEmpty()){
            System.out.println("appName in dmz getVehicleImagesProxy is : "+appName);
            }else{
                response.setMessage("Please pass a valid appName !!");
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
                if(vinNumber!=null && vinNumber!="" && !vinNumber.isEmpty()){
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/autotrust/getVehicleImages?vinNumber="+vinNumber;
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callGetServiceWithAppName(serviceURL, appName);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, VehicleImagesResponse.class);
                    }else{
                        response.setMessage("error in communication to internal autotrust getVehicleImages service");
                        response.setStatus("Error");
                    }
                }else{
                    response.setMessage("vinNumber cannot be null or empty");
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

    @GET
    @Path("getSalesOrdersProxy")
    public SalesOrdersResponse getSalesOrdersProxy(@QueryParam("buId") String buId,
                                                         @HeaderParam("auth") String authorization, 
                                                         @HeaderParam("appName") String appName){
        SalesOrdersResponse response = new SalesOrdersResponse();
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
            System.out.println("appName in dmz getCpoVehiclesProxy is : "+appName);
            }else{
                response.setRetMsg("Please pass a valid appName !!");
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
                
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/autotrust/getSalesOrderDetails?buId="+buId;
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callGetServiceWithAppName(serviceURL, appName);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, SalesOrdersResponse.class);
                    }else{
                        response.setRetMsg("error in communication to internal autotrust getSalesOrderDetails service");
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

    @GET
    @Path("getCustomerVehProxy")
    public CustomerVehiclesResponse callGetCustomerVehDetails(@QueryParam("loyaltyPortalId") String loyaltyPortalId, @QueryParam("idf") String idf, 
                                                             @HeaderParam("auth") String authorization, 
                                                             @HeaderParam("appName") String appName){
        CustomerVehiclesResponse response = new CustomerVehiclesResponse();
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
                response.setRetStatus("Error");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty()){
            System.out.println("appName in dmz callGetCustomerVehDetails is : "+appName);
            }else{
                response.setRetMsg("Please pass a valid appName !!");
                response.setRetStatus("Error");
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
            response.setRetStatus("Error");
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
                
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/autotrust/getCustomerVeh?loyaltyPortalId="+loyaltyPortalId+"&idf="+idf;
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callGetServiceWithAppName(serviceURL, appName);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, CustomerVehiclesResponse.class);
                    }else{
                        response.setRetMsg("error in communication to internal autotrust callGetCustomerVehDetails service");
                        response.setRetStatus("Error");
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
    @Path("getAvailabilityProxy")
    public AvailabilityResponse callGetAvailabilityService(@QueryParam("instanceId") String instanceId,@QueryParam("serviceAdvisorFlag") String serviceAdvisorFlag,
                                                @QueryParam("serviceAdvisorId") String serviceAdvisorId,@QueryParam("groupId") String groupId,
                                                @QueryParam("locationCode") String locationCode,@QueryParam("appointmentDate") String appointmentDate,
                                                @QueryParam("customerWaiting") String customerWaiting,@HeaderParam("auth") String authorization,
                                                @HeaderParam("appName") String appName) {
        AvailabilityResponse response = new AvailabilityResponse();
        System.out.println("data inputs DMZ  : instanceId - "+instanceId+" , serviceAdvisorFlag - "+serviceAdvisorFlag+" ,serviceAdvisorId - "+serviceAdvisorId+" ,groupId - "+groupId+
                           " ,locationCode - "+locationCode+" , appointmentDate - "+appointmentDate+" ,customerWaiting - "+customerWaiting);
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
                authorization = null;
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
                
                if(instanceId!=null && instanceId!=""){
//                String serviceURL = ServiceUtil.getAvailabilityUrl(instanceId, serviceAdvisorFlag, serviceAdvisorId, groupId, locationCode,
//                                                                   appointmentDate, customerWaiting);
                String serviceURL = propUtil.getAvailabilityUrl(instanceId, serviceAdvisorFlag, serviceAdvisorId, groupId, locationCode, appointmentDate, customerWaiting);
                System.out.println("internal service get availability url is : "+serviceURL);
                System.out.println("appName in callGetAvailabilityService DMZ is : "+appName);
                if(appName!=null && appName!="" && !appName.isEmpty())
                {
                String responseString = RestUtil.callGetServiceWithAppName(serviceURL, appName);
              
                if(responseString!=null){
                    
                    response = gson.fromJson(responseString, AvailabilityResponse.class);

                }
                else{
                        response.setRetMsg("communication error get availability");
                        response.setRetCode("E");
                    }
                }else{
                    response.setRetMsg("Header property 'appName' is required!");
                }
                }else{
                    response.setRetMsg("instanceId cannot be null or empty");
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
    @Path("createAppointmentProxy")
    public CreateAppointmentResponse callCreateAppointmentService(CreateAppointmentRequest req, @HeaderParam("auth") String authorization,
                                                                   @HeaderParam("appName") String appName) {
        CreateAppointmentResponse response = new CreateAppointmentResponse();
        PropertiesUtil propUtil = new PropertiesUtil();
        String auth = null;
        String propertyToken = null;
        String propertyAuthorization = null;
        Gson gson = new Gson();
        byte[] key = new byte[0];
        System.out.println("in create appt proxy service");
        try {
            if(authorization!=null && authorization !=""){
            key = authorization.getBytes("UTF-8");
            }else{
                response.setRetMsg("Please pass a valid token !!");
                authorization = null;
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
                
                if(req.getInstanceId()!=null && req.getInstanceId()!=""){
                String serviceURL = System.getenv("MAIN_URL")+"/autotrust/createAppointment";
                System.out.println("internal service url is : "+serviceURL);
    //                String responseString = gson.toJson(req);
                System.out.println("input json string is : "+gson.toJson(req));
                System.out.println("appName in callCreateAppointmentService DMZ is : "+appName);
                if(appName!=null && appName!="" && !appName.isEmpty())
                {
                String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req), appName);
                
                    System.out.println("response from create appt service is : "+responseString);
              
                if(responseString!=null){
                    
                    response = gson.fromJson(responseString, CreateAppointmentResponse.class);

                }
                else{
                        response.setRetMsg("communication error in create appointment");
                        response.setRetCode("E");
                    }
                }else{
                    response.setRetMsg("Header property 'appName' is required!");
                }
                }else{
                    response.setRetMsg("instanceId cannot be null or empty");
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
    @Path("getBranchesProxy")
    public BranchesResponse getBranchesProxyService(GetBranchesRequest req, @HeaderParam("auth") String authorization,
                                                    @HeaderParam("appName") String appName) {
        BranchesResponse response = new BranchesResponse();
        PropertiesUtil propUtil = new PropertiesUtil();
        String auth = null;
        String propertyToken = null;
        String propertyAuthorization = null;
        Gson gson = new Gson();
        byte[] key = new byte[0];
        System.out.println("in create appt proxy service");
        try {
            if(authorization!=null && authorization !=""){
            key = authorization.getBytes("UTF-8");
            }else{
                response.setRetMsg("Please pass a valid token !!");
                response.setRetCode("E");
                authorization = null;
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
                
                if(req.getInstanceId()!=null && req.getInstanceId()!="" && !req.getInstanceId().isEmpty()){
                String serviceURL = System.getenv("MAIN_URL")+"/autotrust/getBranches";
                System.out.println("internal service url is : "+serviceURL);
    //                String responseString = gson.toJson(req);
                System.out.println("input json string is : "+gson.toJson(req));
                System.out.println("appName in getBranchesProxyService DMZ is : "+appName);
                if(appName!=null && appName!="" && !appName.isEmpty())
                {
                String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req), appName);
                
                    System.out.println("response from getBranches service is : "+responseString);
              
                if(responseString!=null){
                    
                    response = gson.fromJson(responseString, BranchesResponse.class);

                }
                else{
                        response.setRetMsg("communication error in getBranchesService");
                        response.setRetCode("E");
                    }
                }else{
                    response.setRetMsg("Header property 'appName' is required!");
                }
                }else{
                    response.setRetMsg("instanceId cannot be null or empty");
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
    @Path("serviceAdvisorsProxy")
    public ServiceAdvisorResponse serviceAdvisors(ServiceAdvisorRequest req, @HeaderParam("auth") String authorization,
                                                                   @HeaderParam("appName") String appName) {
        ServiceAdvisorResponse response = new ServiceAdvisorResponse();
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
                authorization = null;
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
                
                if(req.getServiceDateTime()!=null && req.getServiceDateTime()!=""){
                String serviceURL = System.getenv("MAIN_URL")+"/autotrust/serviceAdvisors";
                System.out.println("internal service url is : "+serviceURL);
                System.out.println("input json string is : "+gson.toJson(req));
                System.out.println("appName in serviceAdvisors DMZ is : "+appName);
                if(appName!=null && appName!="" && !appName.isEmpty())
                {
                String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req), appName);
                
                    System.out.println("response from serviceAdvisors service is : "+responseString);
              
                if(responseString!=null){
                    
                    response = gson.fromJson(responseString, ServiceAdvisorResponse.class);

                }
                else{
                        response.setRetMsg("communication error in serviceAdvisors");
                        response.setRetCode("E");
                    }
                }else{
                    response.setRetMsg("Header property 'appName' is required!");
                }
                }else{
                    response.setRetMsg("servicedatetime cannot be null or empty");
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
    @Path("getReservationNumberProxy")
    public GetReservationNumberResponse getReservationNumber(@QueryParam("enquiryId") String enquiryId,
                                                     @HeaderParam("auth") String authorization,
                                                     @HeaderParam("appName") String appName) {
        GetReservationNumberResponse response = new GetReservationNumberResponse();
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
                authorization = null;
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
                
                if(enquiryId!=null && enquiryId!=""){
                String serviceURL = System.getenv("MAIN_URL")+"/autotrust/getReservationNumber?enquiryId="+enquiryId;
                System.out.println("internal service getReservationNumber url is : "+serviceURL);
                System.out.println("appName in getReservationNumber DMZ is : "+appName);
                if(appName!=null && appName!="" && !appName.isEmpty())
                {
                String responseString = RestUtil.callGetServiceWithAppName(serviceURL, appName);
              
                if(responseString!=null){
                    
                    response = gson.fromJson(responseString, GetReservationNumberResponse.class);

                }
                else{
                        response.setRetMsg("communication error getReservationNumber");
                        response.setRetCode("E");
                    }
                }else{
                    response.setRetMsg("Header property 'appName' is required!");
                }
                }else{
                    response.setRetMsg("enquiryId cannot be null or empty");
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
    @Path("getDeviceTokenProxy")
    public GetTokenIdResponse getDeviceTokenProxy(GetDeviceTokenRequest req,
                                                    @HeaderParam("auth") String authorization, 
                                                    @HeaderParam("appName") String appName){
        GetTokenIdResponse response = new GetTokenIdResponse();
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
            System.out.println("appName in dmz getDeviceTokenProxy is : "+appName);
            }else{
                response.setRetMsg("Please pass a valid appName !!");
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
                if(req.getApplicationName()!=null && req.getApplicationName()!="" && !req.getApplicationName().isEmpty()){
                    
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/autotrust/getDeviceToken";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req), appName);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, GetTokenIdResponse.class);
                    }else{
                        response.setRetMsg("error in communication to internal autotrust getdevicetoken service");
                        response.setRetCode("E");
                    }
                    
                }else{
                    response.setRetMsg("ApplicationName cannot be null or empty");
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
    @Path("deviceRegistrationProxy")
    public GenericResponse deviceRegistrationProxy(RequestBean req,
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
                response.setStatus("Error");
                authorization = null;
                return response;
            }
            if(appName!=null && appName !="" && !appName.isEmpty()){
            System.out.println("appName in dmz deviceRegistrationProxy is : "+appName);
            }else{
                response.setMessage("Please pass a valid appName !!");
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
                if(req.getApplicationName()!=null && req.getApplicationName()!="" && !req.getApplicationName().isEmpty() &&
                   req.getToken()!=null && req.getToken()!="" && !req.getToken().isEmpty()){
                    
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/autotrust/deviceRegistration";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req), appName);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, GenericResponse.class);
                    }else{
                        response.setMessage("error in communication to internal autotrust deviceRegistration service");
                        response.setStatus("E");
                    }
                    
                }else{
                    response.setMessage("ApplicationName, Token cannot be null or empty");
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
    @Path("submitAddonsProxy")
    public GenericResponse submitAddonsProxy(SubmitAddonRequest req,
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
            System.out.println("appName in dmz submitAddonsProxy is : "+appName);
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
                if(req.getAddons().length>0 && req.getAddons()!=null){
                    
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/autotrust/submitAddons";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req), appName);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, GenericResponse.class);
                    }else{
                        response.setMessage("error in communication to internal autotrust submitAddons service");
                        response.setStatus("E");
                    }
                    
                }else{
                    response.setMessage("Addons cannot be null or empty");
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
    @Path("insertAddonsProxy")
    public GenericResponse insertAddonsProxy(InsertAddonRequest req,
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
            System.out.println("appName in dmz insertAddonsProxy is : "+appName);
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
//                if(req.getOrgId()!=null && req.getOrgId()!="" && !req.getOrgId().isEmpty() &&
                  if(req.getReservationNo()!=null && req.getReservationNo()!="" && !req.getReservationNo().isEmpty() &&
                  req.getExtraFittingIds().length>0 && req.getExtraFittingIds()!=null){
                    
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/autotrust/insertAddons";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req), appName);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, GenericResponse.class);
                    }else{
                        response.setMessage("error in communication to internal autotrust insertAddons service");
                        response.setStatus("E");
                    }
                    
                }else{
                    response.setMessage("Addons,ReservationNo cannot be null or empty");
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
    @Path("statementProxy")
    public MyStatementResponse statementProxy(MyStatementRequest req,
                                             @HeaderParam("auth") String authorization, 
                                             @HeaderParam("appName") String appName){
        MyStatementResponse response = new MyStatementResponse();
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
            System.out.println("appName in dmz insertAddonsProxy is : "+appName);
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
                  if(req.getBuId()!=null && req.getBuId()!="" && !req.getBuId().isEmpty() &&
                  req.getFromDate()!=null && req.getFromDate()!="" && !req.getFromDate().isEmpty() &&
                  req.getToDate()!=null && req.getToDate()!="" && !req.getToDate().isEmpty()){
                    
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/autotrust/statement";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callPutServiceWithAppName(serviceURL, gson.toJson(req), appName);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, MyStatementResponse.class);
                    }else{
                        response.setRetMsg("error in communication to internal autotrust insertAddons service");
                        response.setRetCode("E");
                    }
                    
                }else{
                    response.setRetMsg("buId,fromDate,toDate cannot be null or empty");
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
    @Path("reserveVehicleProxy")
    public GenericResponse reserveVehicleProxy(ReserveVehicleRequest req, @HeaderParam("appName") String appName){
        GenericResponse response = new GenericResponse();
        GenericResponseKentico resp = new GenericResponseKentico();
        PropertiesUtil propUtil = new PropertiesUtil();
        
        Gson gson = new Gson();
        try {
            if(appName!=null && appName!="" && !appName.isEmpty()){
                  if(req.getApplicationName()!=null && req.getApplicationName()!="" && !req.getApplicationName().isEmpty() &&
                  req.getInventoryItemId()!=null && req.getInventoryItemId()!="" && !req.getInventoryItemId().isEmpty()){
                    //converting to kenticoReq format due to the InitCap format used in attributes
                    ReserveVehicleRequestKentico kenticoReq = new ReserveVehicleRequestKentico();
                    kenticoReq.setApplicationName(req.getApplicationName());
                    kenticoReq.setInventoryItemId(req.getInventoryItemId());
                    kenticoReq.setReserveCarFlag(req.getReserveCarFlag());
                    kenticoReq.setUnreserveCarFlag(req.getUnreserveCarFlag());
                    
                    System.out.println("in dmz service reserveVehicleProxy");

//                    String serviceURL = System.getenv("AUTOTRUST_SVC_URL")+"/AutotrustKenticoApis/ReserveVehicleInKentico";
                    String serviceURL = System.getenv("AUTOTRUST_SVC_URL_RESERVE");
                    System.out.println("ReserveVehicleInKentico service url is : "+serviceURL);
                    System.out.println("request form dmz : "+gson.toJson(kenticoReq));
                    String responseString = RestUtil.callPostServiceAutotrust(serviceURL, gson.toJson(kenticoReq),appName);
                    System.out.println("response is : "+responseString);
                    
                    if(responseString!=null){
                        resp = gson.fromJson(responseString, GenericResponseKentico.class);
                        System.out.println("resp kentico object is : status :"+String.valueOf(resp.isStatus()) + " : message : "+resp.getMessage());
                        if(resp.isStatus()){
                            response.setStatus("true");
                        }else{
                            response.setStatus("false");
                        }
                        response.setMessage(resp.getMessage());
                    }else{
                        response.setMessage("error in communication to ReserveVehicleInKentico service");
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
    @Path("sendNotificationProxy")
    public GenericResponse sendNotificationProxy(SendNotificationRequest req, @HeaderParam("appName") String appName){
        GenericResponse response = new GenericResponse();
        GenericResponseKentico resp = new GenericResponseKentico();
        PropertiesUtil propUtil = new PropertiesUtil();
        
        Gson gson = new Gson();
        try {
            if(appName!=null && appName!="" && !appName.isEmpty()){
                  if(req.getApplicationName()!=null && req.getApplicationName()!="" && !req.getApplicationName().isEmpty() &&
                  req.getNotificationMessage()!=null && req.getNotificationMessage()!="" && !req.getNotificationMessage().isEmpty()){
                    //converting to kenticoReq format due to the InitCap format used in attributes
                    SendNotificationRequestKentico kenticoReq = new SendNotificationRequestKentico();
                    kenticoReq.setApplicationName(req.getApplicationName());
                    kenticoReq.setDeviceType(req.getDeviceType());
                    kenticoReq.setUserIDs(req.getUserIDs());
                    kenticoReq.setNotificationMessage(req.getNotificationMessage());
                    
                    System.out.println("in dmz service sendNotificationProxy");

//                    String serviceURL = System.getenv("AUTOTRUST_SVC_URL")+"/PushNotifications/SendSandboxNotifications";
                    String serviceURL = System.getenv("AUTOTRUST_SVC_URL_NOTIFICATION");
                    System.out.println("SendSandboxNotifications service url is : "+serviceURL);
                    System.out.println("request form dmz : "+gson.toJson(kenticoReq));
                    String responseString = RestUtil.callPostServiceAutotrust(serviceURL, gson.toJson(kenticoReq),appName);
                    System.out.println("response is : "+responseString);
                    if(responseString!=null){
                        resp = gson.fromJson(responseString, GenericResponseKentico.class);
                        System.out.println("resp kentico object is : status :"+String.valueOf(resp.isStatus()) + " : message : "+resp.getMessage());
                        if(resp.isStatus()){
                            response.setStatus("true");
                        }else{
                            response.setStatus("false");
                        }
                        response.setMessage(resp.getMessage());
                    }else{
                        response.setMessage("error in communication to SendSandboxNotifications service");
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
    @Path("getVehiclesProxy")
    public CpoDealerVehiclesResponse getVehiclesProxy(@HeaderParam("auth") String authorization, @HeaderParam("appName") String appName){
        CpoDealerVehiclesResponse response = new CpoDealerVehiclesResponse();
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
            System.out.println("appName in dmz getVehiclesProxy is : "+appName);
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
                
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/autotrust/getVehicles";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callGetServiceWithAppName(serviceURL, appName);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, CpoDealerVehiclesResponse.class);
                    }else{
                        response.setMessage("error in communication to internal autotrust getVehicles service");
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
    
    @GET
    @Path("getCpoStockProxy")
    @Produces("application/json")
    public CpoDealerVehiclesResponse getCpoStockProxy(@HeaderParam("auth") String authorization, @HeaderParam("appName") String appName){
        CpoDealerVehiclesResponse response = new CpoDealerVehiclesResponse();
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
            if(appName!=null && appName !="" && !appName.isEmpty()){
            }else{
                response.setMessage("Please pass a valid appName !!");
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
                
                    String serviceURL = System.getenv("MAIN_URL")+"/autotrust/getCpoStock";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callGetServiceWithAppName(serviceURL, appName);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, CpoDealerVehiclesResponse.class);
                    }else{
                        response.setMessage("error in communication to internal autotrust getCpoVehicles service");
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
