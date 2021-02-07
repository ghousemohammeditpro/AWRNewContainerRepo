package com.awr.autotrustservice.svc;

import com.awr.autotrustservice.response.GenericResponse;
import com.awr.autotrustservice.response.SuccessResponse;

import com.awr.autotrustservice.util.DBConnect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("successResponse")
@Consumes("application/json")
@Produces("application/json")

public class SuccessService {
    public SuccessService() {
        super();
    }
    @GET
    @Produces("application/json")
    public String excuteService(@QueryParam("p_object_name") String serviceName, @QueryParam("p_lang") String lang) {

        String p_application_name = "AUTOTRUST";
        String p_version_number = "1";
        SuccessResponse successResponseObj = new SuccessResponse();
        Connection conn = null;
        CallableStatement stmt = null;
        conn = DBConnect.getConnection();

        try {


            stmt = conn.prepareCall(String.format("{CALL XXDOP.xxdmp_dop_pkg_pub.ok_to_execute(?,?,?,?,?,?,?)}"));

            stmt.setString(1, p_application_name);
            stmt.setString(2, serviceName);
            stmt.setString(3, lang);
            stmt.setLong(4, Long.parseLong(p_version_number));

            stmt.registerOutParameter(5, Types.VARCHAR);
            stmt.registerOutParameter(6, Types.VARCHAR);
            stmt.registerOutParameter(7, Types.VARCHAR);
            stmt.execute();

            successResponseObj.setRetCode("S");
            successResponseObj.setRetMsg("Ok to execute");
            successResponseObj.setRetStatus("Success");
            return "S";

        } catch (Exception e) {
            e.printStackTrace();
            successResponseObj.setRetCode("E");
            successResponseObj.setRetMsg("Unable to execute");
            successResponseObj.setRetStatus("Fail");
            return "E";

        }finally {
                   try {
                       stmt.close();
                   }catch (Exception con) {
                       con.printStackTrace();
                   }  
                   try {
                       DBConnect.closeConnection(conn);
                   } catch (Exception con) {
                       con.printStackTrace();
                   }
               }


    }
    
    @GET
    @Path("okToExcuteService")
    public GenericResponse okToExcuteService(@QueryParam("token") String token, @QueryParam("webService") String webService, @QueryParam("userId") String userId) {

        Connection conn = null;
        CallableStatement stmt = null;
        conn = DBConnect.getConnection();
        GenericResponse response = new GenericResponse();

        try {


            stmt = conn.prepareCall(String.format("{CALL apps.xxweb_access_control_pkg.application_access_allowed(?,?,?,?,?)}"));

            stmt.setString(1, token);
            stmt.setString(2, webService);
            stmt.setString(3, userId);

            stmt.registerOutParameter(4, Types.VARCHAR);
            stmt.registerOutParameter(5, Types.VARCHAR);
            stmt.execute();
            
            response.setStatus(stmt.getString(4));
            response.setMessage(stmt.getString(5));
            System.out.println("okToExcuteService response status: "+response.getStatus()+" message: "+response.getMessage());


            return response;

        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Exception");
            response.setStatus("E");
            return response;

        }finally {
                   try {
                       stmt.close();
                   }catch (Exception con) {
                       con.printStackTrace();
                   }  
                   try {
                       DBConnect.closeConnection(conn);
                   } catch (Exception con) {
                       con.printStackTrace();
                   }
               }


    }
    
    @GET
    @Path("okToExcuteService1")
    public GenericResponse okToExcuteService1(@QueryParam("token") String token,
                                              @QueryParam("webService") String webService,
                                              @QueryParam("userId") String userId,
                                              @QueryParam("attribute1") String attribute1,
                                              @QueryParam("attribute2") String attribute2,
                                              @QueryParam("attribute3") String attribute3,
                                              @QueryParam("attribute4") String attribute4,
                                              @QueryParam("attribute5") String attribute5,
                                              @QueryParam("requestToken") String requestToken) {

        Connection conn = null;
        CallableStatement stmt = null;
        conn = DBConnect.getConnection();
        GenericResponse response = new GenericResponse();

        try {
            stmt = conn.prepareCall(String.format("{CALL apps.xxweb_access_control_pkg.application_access_allowed(?,?,?,?,?,?,?,?,?,?,?)}"));

            stmt.setString(1, token);
            stmt.setString(2, webService);
            stmt.setString(3, userId);
            stmt.setString(4, attribute1);
            stmt.setString(5, attribute2);
            stmt.setString(6, attribute3);
            stmt.setString(7, attribute4);
            stmt.setString(8, attribute5);
            stmt.setString(9, requestToken);

            stmt.registerOutParameter(10, Types.VARCHAR);
            stmt.registerOutParameter(11, Types.VARCHAR);
            stmt.execute();
            
            response.setStatus(stmt.getString(10));
            response.setMessage(stmt.getString(11));
            System.out.println("okToExcuteService response status: "+response.getStatus()+" message: "+response.getMessage());


            return response;

        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Exception");
            response.setStatus("E");
            return response;

        }finally {
                   try {
                       stmt.close();
                   }catch (Exception con) {
                       con.printStackTrace();
                   }  
                   try {
                       DBConnect.closeConnection(conn);
                   } catch (Exception con) {
                       con.printStackTrace();
                   }
               }


    }
}
