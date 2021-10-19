package com.awr.autotrustservice.svc;

import javax.ws.rs.GET;
import java.sql.Connection;
import com.awr.autotrustservice.util.DBConnect;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

@Path("heartbeat")
@Consumes("application/json")
@Produces("application/json")
public class Heartbeat {
    public Heartbeat() {
        super();
    }

    @GET
    @Produces("text/plain")
    @Path("dbping")
    public String getDBPing(){
    
    // System.out.println("getDBPing service call started");
    Connection con=null;
    try{
    con = DBConnect.getConnection();
    //step3 create the statement object
    Statement stmt1=con.createStatement();
    //step4 execute query
    /* ResultSet rs1=stmt1.executeQuery("select * from XXDMV_CPO_DEALER_VEHICLES_V2");
    while(rs1.next())
    System.out.println(rs1.getString("item_code")); */
    ResultSet rs1=stmt1.executeQuery("select * from dual");
    while(rs1.next()) {
    // System.out.println("DB query executed successfully");
    }
    //step5 close the connection object
    // con.close();
    System.out.println("getDBPing service call finished");
    return "Completed";
    }catch(Exception e){ System.out.println(e);}
    return "Not Completed";
    }
}

