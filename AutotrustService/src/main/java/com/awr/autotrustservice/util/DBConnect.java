package com.awr.autotrustservice.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

public class DBConnect {
    public DBConnect() {
        super();
    }
    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    /*++++++++++++++++++++++++   ESTABLISH DATABASE CONNECTION    +++++++++++++++++++++++++*/
    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

    public static Connection getConnection() {

        Connection conn = null;

        try {

            // Context ctx = new InitialContext();
            // Context envCtx = (Context) ctx.lookup("");
            // DataSource ds = (DataSource) envCtx.lookup("jdbc/autotrustDS");
            // if (ds != null) {
            //     conn = ds.getConnection();
            // }

            //Changing the Databse connection from Datasource to JDBC
            //step1 load the driver class
				Class.forName("oracle.jdbc.driver.OracleDriver");
            //step2 create  the connection object
				conn=DriverManager.getConnection(
				"jdbc:oracle:thin:@172.22.15.39:1521:ATST1","XXMOBOMS","XXMOBOMS");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }


    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    /*++++++++++++++++++++++++   CLOSE DATABASE CONNECTION    +++++++++++++++++++++++++*/
    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
    //            System.out.println("Conection is closed");
        }
    }

}
