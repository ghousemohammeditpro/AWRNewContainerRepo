package com.awr.autotrustproxy.proxysvc;

import com.awr.autotrustproxy.response.IdashboardResponse;
import com.awr.autotrustproxy.util.HexString;
import com.awr.autotrustproxy.util.PropertiesUtil;
import com.awr.autotrustproxy.util.RestUtil;
import com.awr.autotrustproxy.util.Token;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("iDashboardProxy")
@Consumes("application/json")
@Produces("application/json")
public class IdashboardProxy {
    public IdashboardProxy() {
        super();
    }
    
    @GET
    @Path("iDashboardDetailsProxy")
    @Produces("application/json")
    public IdashboardResponse callGetiDashboardProxyService(@HeaderParam("auth") String authorization, @HeaderParam("appName") String appName){
        IdashboardResponse response = new IdashboardResponse();
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
            System.out.println("appName in dmz callGetiDashboardProxyService is : "+appName);
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
                
                    System.out.println("in dmz service");

                    String serviceURL = System.getenv("MAIN_URL")+"/iDashboard/iDashboardDetails";
                    System.out.println("internal service url is : "+serviceURL);
                    String responseString = RestUtil.callGetServiceWithAppName(serviceURL, appName);
                    if(responseString!=null){
                        response = gson.fromJson(responseString, IdashboardResponse.class);
                    }else{
                        response.setRetMsg("error in communication to internal callGetiDashboard service");
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
}
