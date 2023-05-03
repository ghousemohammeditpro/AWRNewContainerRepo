package com.awr.autotrustservice.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

public class UtilityClass {
    public UtilityClass() {
        super();
    }

    public static boolean checkNullInt(Integer i) {
        if (i != null) {
            return false;
        } else
            return true;
    }


    public static boolean checkNullDate(java.util.Date date) {
        if (date != null)
            return false;
        else
            return true;


    }

    public static java.util.Date getJavaDate(java.sql.Date sqldate) {
        java.util.Date newDate = null;

        try {
            newDate = new java.util.Date(sqldate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }


    // This method converts Java date into SQL date format
    public static Date getSQLdate(java.util.Date date) {
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        return sqldate;
    }

    // Method to convert string to date

        public static Date getDateFromString(String s) {
    
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
    
            java.util.Date javadate = null;
            try {
    
    
                javadate = formatter.parse(s);
    
    
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return (Date) javadate;
        }
        
        //Method to convert date to string 
        public static String getStringFromDate(java.util.Date date) {
        
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        
            String  dateString = null;
            try {
        
        
                dateString = formatter.format(date);
        
        
            } catch (Exception e) {
                e.printStackTrace();
            }
            return dateString;
        }

    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    /*++++++++++++++++++++++++    GET SQL SYSDATETIME     +++++++++++++++++++++++++*/
    /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    public static String getSysdatetime() {


        java.util.Date date = new java.util.Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String sysdatetimne = "[" + dateFormat.format(date) + "] ";

        return sysdatetimne;
    }
    
    public String stringArrayToString(String[] arr){
        String str = null;
        if(arr!=null){
        for(int i=0;i<arr.length;i++){
            if(str==null){
                str = arr[i]+",";
            }else{
            str += arr[i]+",";
            }
        }
        }
        
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ',') {
                str = str.substring(0, str.length() - 1);
            }
        
        return str;
    }
    
    public String modifyArray(String[] test){
        String result = null;
        if(test!=null){
            for(int i=0;i<test.length;i++){
                if(result==null){
                    result = "'"+test[i]+"',";
                }else{
                    result = result+"'"+test[i]+"',";
                }
            }
        }
        
        if (result != null && result.length() > 0 && result.charAt(result.length() - 1) == ',') {
                result = result.substring(0, result.length() - 1);
            }
        
        if(result!=null){
            result="("+result+")";
        }
        return result;
    }


}
