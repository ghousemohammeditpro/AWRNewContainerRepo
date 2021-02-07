package com.awr.autotrustservice.svc;

import com.awr.autotrustservice.dto.IdashboardDataObject;
import com.awr.autotrustservice.response.IdashboardResponse;
import com.awr.autotrustservice.util.DBConnect;
import com.awr.autotrustservice.util.LogData;
import com.awr.autotrustservice.util.NotificationAuditLogger;
import com.awr.autotrustservice.util.NotificationExceptionLogger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("iDashboard")
@Consumes("application/json")
@Produces("application/json")
public class Idashboard {
    public Idashboard() {
        super();
    }
    NotificationAuditLogger logger = new NotificationAuditLogger();
    NotificationExceptionLogger exceptionLogger = new NotificationExceptionLogger();
    SuccessService successService = new SuccessService();
    
    @GET
    @Path("iDashboardDetails")
    @Produces("application/json")
    public IdashboardResponse getiDashboardDetails(@HeaderParam("appName") String appName) 
    {
        IdashboardResponse response = new IdashboardResponse();
        ArrayList<IdashboardDataObject> dataList = new ArrayList<IdashboardDataObject>();
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        LogData log = new LogData();
        String successResponse = null;

        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal getiDashboardDetails is : "+appName);

        try {
            successResponse = successService.excuteService("getiDashboardDetails", "EN");
            if (successResponse.equals("S")) {
            
            log.setAppCode("IDASHBOARD");
            log.setAppName("iDashboard");
            log.setDocCode("getiDashboardDetails");
            log.setUserName("");
            log.setAttribute1("");
            log.setServiceName("iDashboardDetails");
            log.setVendorName("VendorVal:"+appName);
            
            String idashQuery = "SELECT SUM (NVL (TG.SALES, 0)) ACTUAL,\n" + 
            "         SUM (NVL (TG.MGMT_TARGET, 0)) Target,\n" + 
            "         TG.PERIOD,\n" + 
            "         TG.DESCRIPTION,\n" + 
            "         SUM (NVL (TG.VEH_SALES_VAL, 0)) VEH_SALES_VAL,\n" + 
            "         (CASE\n" + 
            "             WHEN LK.ATTRIBUTE3 IN ('ZNA', 'DFM', 'SAIC') THEN 'OTHER'\n" + 
            "             ELSE LK.ATTRIBUTE3\n" + 
            "          END)\n" + 
            "            MODEL,\n" + 
            "         TO_CHAR (TO_DATE (1 || period, 'DD-MON-RRRR'), 'RRRR') year_derived,\n" + 
            "         TO_CHAR (TO_DATE (1 || period, 'DD-MON-RRRR'), 'MON') month_derived,\n" + 
            "         TO_CHAR (TO_DATE (1 || period, 'DD-MON-RRRR'), 'MM') month_num_derived\n" + 
            "    FROM apps.xxom_sl_stk_tgt_rng_mtx_v TG, apps.fnd_lookup_values LK\n" + 
            "   WHERE LK.lookup_code = TG.RANGE_CODE\n" + 
            "         AND LK.lookup_type = 'XX_OIC_RANGE_DESC'\n" + 
            "         AND TO_CHAR (TO_DATE (1 || period, 'DD-MON-RRRR'), 'RRRR') BETWEEN TO_CHAR (\n" + 
            "                                                                               SYSDATE,\n" + 
            "                                                                               'RRRR')\n" + 
            "                                                                            - 2\n" + 
            "                                                                        AND TO_CHAR (\n" + 
            "                                                                               SYSDATE,\n" + 
            "                                                                               'RRRR')\n" + 
            "GROUP BY TG.Period, LK.ATTRIBUTE3, TG.DESCRIPTION";
               
            stmt = conn.createStatement();
            rs = stmt.executeQuery(idashQuery);
                if(rs!=null){
                    
                    while(rs.next()){
                        IdashboardDataObject data = new IdashboardDataObject();
                        
                        data.setActual(rs.getString("actual")!=null ? rs.getString("actual") : "");
                        data.setTarget(rs.getString("target")!=null ? rs.getString("target") : "");
                        data.setPeriod(rs.getString("period")!=null ? rs.getString("period") : "");
                        data.setDescription(rs.getString("description")!=null ? rs.getString("description") : "");
                        data.setVehSalesValue(rs.getString("veh_sales_val")!=null ? rs.getString("veh_sales_val") : "");
                        data.setModel(rs.getString("model")!=null ? rs.getString("model") : "");
                        data.setYear(rs.getString("year_derived")!=null ? rs.getString("year_derived") : "");
                        data.setMonth(rs.getString("month_derived")!=null ? rs.getString("month_derived") : "");
                        data.setMonthNum(rs.getString("month_num_derived")!=null ? rs.getString("month_num_derived") : "");
                        
                        dataList.add(data);
                    }
                }
                response.setData(dataList.toArray(new IdashboardDataObject[dataList.size()]));
                
            log.setAttribute2("");
            log.setAttribute3("");
            logger.callAudit(log);
            
            }
            
        } catch (Exception e) {
            log.setErrorMessage(e.toString());
            exceptionLogger.callAudit(log);
            e.printStackTrace();

        } finally {
            try {
                if(rs!=null){
                rs.close();
                }
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
}
