package com.awr.autotrustservice.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;
import java.util.TimeZone;
import com.awr.autotrustservice.util.SecretsReader;
import org.apache.commons.dbcp2.*;
import oracle.jdbc.OracleConnection;

public class DBConnect {
    public DBConnect() {
        super();
    }
    // static Connection conn=null;
    // static String schemaName=System.getenv("DB_SCHEMA_NAME");
    // static String dbServerConnectionString=System.getenv("DB_CONNECTION_STRING");;
    // public static Connection getConnection() {
    //     try {
    //         TimeZone timeZone = TimeZone.getTimeZone("Asia/Dubai");
    //         TimeZone.setDefault(timeZone);
    //         //changing the databse connectivity from datasource to JDBC at the time of GCP migration
    //         //if(conn==null || conn.isClosed()){
    //             //step1 load the driver class
    //             Class.forName("oracle.jdbc.driver.OracleDriver");
    //             //step2 create  the connection object
    //             String secretPwd=SecretsReader.getSecret(schemaName);
    //                 conn=DriverManager.getConnection(
    //                     dbServerConnectionString,schemaName,secretPwd);
    //         // }else{
    //         //     return conn;
    //         // }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return conn;
    // }

    // public static void closeConnection(Connection con) {
    //     if (con != null) {
    //         try {
    //             con.close(); //no need to close the connection in GCP to avoid multiple connections from secret manager
    //         } catch (Exception e) {
    //             e.printStackTrace();
    //         }
    //     } else {
    //             //System.out.println("Conection is closed");
    //     }
    // }

    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    /*++++++++++++++++++++++++   DATASOURCE IMPLEMENTATION    +++++++++++++++++++++++++*/
    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

    private static BasicDataSource ds = new BasicDataSource();
    private static String schemaName=System.getenv("DB_SCHEMA_NAME");
    private static String dbServerConnectionString=System.getenv("DB_CONNECTION_STRING");
    private static int dsInitialSize=Integer.parseInt(System.getenv("DS_INITIAL_SIZE").toString());
    private static int dsMinIdle=Integer.parseInt(System.getenv("DS_MIN_IDLE").toString());
    private static int dsMaxIdle=Integer.parseInt(System.getenv("DS_MAX_IDLE").toString());
    private static int dsMaxTotal=Integer.parseInt(System.getenv("DS_MAX_TOTAL").toString());
    private static int dsMaxOpenPreparedStatements=Integer.parseInt(System.getenv("DS_MAX_OPEN_PREP_STATEMENT").toString());
    private static int timeBetweenEvictionRunsMillis=Integer.parseInt(System.getenv("TIME_BETWEEN_EVICTOR_RUNS_IN_MILLIS").toString());
    private static int maxConnLifetimeMillis=Integer.parseInt(System.getenv("MAX_CONN_LIEFTIME_MILLIS").toString());
    private static int maxWaitMillis=Integer.parseInt(System.getenv("MAX_WAIT_MILLIS").toString());
    static String secretPwd=null;
    static {
        try {
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Dubai");
        TimeZone.setDefault(timeZone);
        ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        ds.setUrl(dbServerConnectionString);
        ds.setUsername(schemaName);
        if(secretPwd!=null){
            //do nothing
        }else{
            secretPwd=SecretsReader.getSecret(schemaName);
        }
        ds.setPassword(secretPwd);
        ds.setInitialSize(dsInitialSize);
        ds.setMinIdle(dsMinIdle);
        ds.setMaxIdle(dsMaxIdle);
        ds.setMaxTotal(dsMaxTotal);
        ds.setMaxOpenPreparedStatements(dsMaxOpenPreparedStatements);
        ds.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        ds.setMaxConnLifetimeMillis(maxConnLifetimeMillis);
        ds.setTestWhileIdle(true);
        ds.setMaxWaitMillis(maxWaitMillis);
        ds.setRemoveAbandonedOnMaintenance(true);
        ds.setRemoveAbandonedOnBorrow(true);
        ds.setLogExpiredConnections(false);
    } catch (Exception e) {
        System.out.println("exception while connecting to DB:"+e.getMessage());
        e.printStackTrace();
    }
    }
    
    public static Connection getConnection() {
        Connection conn=null;
        //TimeLogger timeLogger = new TimeLogger();
        try{
            // if (ds.getConnection().isWrapperFor(Connection.class)) {
            //     conn = ds.getConnection().unwrap(Connection.class);
            // }
            //timeLogger.logTime("getConnection", "Initiating connection");
            conn = ds.getConnection();
            //timeLogger.logTime("getConnection", "got the connection");
        }catch (Exception e) {
            System.out.println("exception while creating the connection:"+e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

	public static OracleConnection getConnection(Connection conn) {
        OracleConnection oconn = null;
		try{
            oconn = conn.unwrap(OracleConnection.class);
        }catch (Exception e) {
            System.out.println("exception while unwrapping the Oracle connection:"+e.getMessage());
            e.printStackTrace();
        }
        return oconn;
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close(); //no need to close the connection in GCP to avoid multiple connections from secret manager
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
                //System.out.println("Conection is closed");
        }
    }

     /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    /*++++++++++++++++++++++++   DATASOURCE IMPLEMENTATION    +++++++++++++++++++++++++*/
    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

}
