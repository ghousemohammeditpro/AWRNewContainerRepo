package com.awr.autotrustservice.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;
import javax.sql.DataSource;
import java.util.TimeZone;
import com.awr.autotrustservice.util.SecretsReader;

public class DBConnect {
    public DBConnect() {
        super();
    }
    static Connection conn=null;
    static String schemaName=System.getenv("DB_SCHEMA_NAME");
    static String dbServerConnectionString=System.getenv("DB_CONNECTION_STRING");;
    public static Connection getConnection() {
        try {
            TimeZone timeZone = TimeZone.getTimeZone("Asia/Dubai");
            TimeZone.setDefault(timeZone);
            //changing the databse connectivity from datasource to JDBC at the time of GCP migration
            //if(conn==null || conn.isClosed()){
                //step1 load the driver class
                Class.forName("oracle.jdbc.driver.OracleDriver");
                //step2 create  the connection object
                String secretPwd=SecretsReader.getSecret(schemaName);
                    conn=DriverManager.getConnection(
                        dbServerConnectionString,schemaName,secretPwd);
            // }else{
            //     return conn;
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
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

}
