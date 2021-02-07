package com.awr.autotrustservice.svc;

import com.awr.autotrustservice.dto.Lookup;
import com.awr.autotrustservice.request.CommonLookupRequest;
import com.awr.autotrustservice.request.InsertLeadRequest;
import com.awr.autotrustservice.response.CommonLookupResponse;
import com.awr.autotrustservice.util.DBConnect;
import com.awr.autotrustservice.util.LogData;
import com.awr.autotrustservice.util.NotificationAuditLogger;
import com.awr.autotrustservice.util.NotificationExceptionLogger;
import com.awr.autotrustservice.util.UtilityClass;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import oracle.jdbc.OracleTypes;

//@Path("insertLead")
//@Consumes("application/json")
//@Produces("application/json")
public class LeadCreation {
    public LeadCreation() {
        super();
    }
    
    NotificationAuditLogger logger = new NotificationAuditLogger();
    NotificationExceptionLogger exceptionLogger = new NotificationExceptionLogger();
    SuccessService successService = new SuccessService();
    //Lead Creation Api - to be completed if required
    /*
    @PUT
    public CommonLookupResponse insertLeadDetails(InsertLeadRequest ld, @HeaderParam("appName") String appName) {
        CommonLookupResponse response = new CommonLookupResponse();
        ArrayList<Lookup> lookupList = new ArrayList<Lookup>();
        
        LogData log = new LogData();
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        String successResponse;
        conn = DBConnect.getConnection();
        
        try {
            successResponse = successService.excuteService("getCommonLookup", "EN");
            if (successResponse.equals("S")) 
            {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("getCommonLookup");
            log.setUserName("listType:"+req.getLookupType());
            log.setAttribute1("appName:"+appName);
            
                stmt =
                    conn.prepareCall("{call xxdmv_cpo_dealer_mob_pkg.get_list_values(?,?,?,?,?)}");
                
                stmt.setString(1, req.getLookupType());
                UtilityClass util = new UtilityClass();
                stmt.setString(2, util.stringArrayToString(req.getMake()));
            
                stmt.registerOutParameter(3, OracleTypes.CURSOR);
                stmt.registerOutParameter(4, Types.VARCHAR);
                stmt.registerOutParameter(5, Types.VARCHAR);
                rs=stmt.executeQuery();
            
            if(stmt.getString(4)!=null){
                response.setRetcode(stmt.getString(4));
            }else{
                response.setRetcode("");
            }
            if(stmt.getString(5)!=null){
                response.setRetMsg(stmt.getString(5));
            }else{
                response.setRetMsg("");
            }
            
            if (stmt.getObject(3) != null) {
                rs = (ResultSet) stmt.getObject(3);
                while (rs.next()) {
                    Lookup lookup = new Lookup();
                    if(rs.getString("CODE") == null){
                        lookup.setLookupCode("");
                    }else{
                        lookup.setLookupCode(rs.getString("CODE"));
                    }
                    if(rs.getString("DESCRIPTION") == null){
                        lookup.setLookupMeaning("");
                    }else{
                        lookup.setLookupMeaning(rs.getString("DESCRIPTION"));
                    }
                    lookupList.add(lookup);
                }
                
            }else{
                
            }
            response.setCommonLookup(lookupList.toArray(new Lookup[lookupList.size()]));
    //            logger.callAudit(log);
        }
        } catch (Exception e) {
            log.setErrorMessage(e.getMessage());
    //                exceptionLogger.callAudit(log);
            e.printStackTrace();

        } finally{
            try {
                rs.close();
            } catch (Exception s) {
            s.printStackTrace();
                }
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try{
            DBConnect.closeConnection(conn);
            }
            catch (Exception con) {
                con.printStackTrace();
                }
        }
           
           return response;                         
    }
*/
}
