package com.awr.autotrustservice.util;

import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonUtil {
    public CommonUtil() {
        super();
    }
    public static void main(String[] args){
        try{
    //        System.out.println("formatted date is : "+stringToDate("4-FEB-2018 11:00:00 AM"));
//            stringToTimestamp("12-APR-2018 11:00:00");
//             sysDateStringToTimestampDb();
//            System.out.println(calcFlagOnStringDate("2017-12-30 10:23:58.0"));
//            System.out.println(calcFlagOnStringDate("2017-10-04 10:23:58.0"));
//            stringToTimestampDb("2017-12-27 14:35:56");
//            String dateStr = "/Date(407188800000)/";
                //"/Date(253402286399997)/";
            
//            dateFromMillisec(dateStr);
            stringToDateddmmmyyyy("21-jan-2018");
//            dateStringToTimestamp("21-jan-2018");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static String formatInDate(String rawDate) throws ParseException {
        String formattedDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        SimpleDateFormat stringFormat = new SimpleDateFormat("dd-MMM-yyyy");
        
        if(rawDate!=null && rawDate!=""){
         formattedDate = stringFormat.format(dateFormat.parse(rawDate));
        }
        
        return formattedDate;
    }
    
    public String formatOutDate(String rawDate) throws ParseException {
        String formattedDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        if(rawDate!=null && rawDate!=""){
         formattedDate = formatter.format(dateFormat.parse(rawDate));
        }
        
        return formattedDate;
    }
    
    public static java.sql.Date stringToDate(String dateString) throws ParseException {
        Date formattedDate = null;
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss a");
        System.out.println("input string : "+dateString);
        formattedDate = formatter.parse(dateString);
        System.out.println("formatted date : "+formattedDate);
        java.sql.Date sqlDate = new java.sql.Date(formattedDate.getTime());
        System.out.println("SQL date in Java : " + sqlDate);

        return sqlDate;
    }
    
    public static Timestamp stringToTimestamp(String dateString) throws ParseException {
        Date formattedDate = null;
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        System.out.println("input string : "+dateString);
        formattedDate = formatter.parse(dateString);
        System.out.println("formatted date : "+formattedDate);
        Timestamp sqlDate = new Timestamp(formattedDate.getTime());
        System.out.println("SQL timestamp in Java : " + sqlDate);

        return sqlDate;
    }
    
    public static Timestamp stringToTimestampDb(String dateString) throws ParseException {
        Date formattedDate = null;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("input string : "+dateString);
        formattedDate = formatter.parse(dateString);
        System.out.println("formatted date : "+formattedDate);
        Timestamp sqlDate = new Timestamp(formattedDate.getTime());
        System.out.println("SQL timestamp in Java : " + sqlDate);

        return sqlDate;
    }
    
    public static java.sql.Date stringToDateDb(String dateString) throws ParseException {
        Date formattedDate = null;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("input string : "+dateString);
        formattedDate = formatter.parse(dateString);
        System.out.println("formatted date : "+formattedDate);
        java.sql.Date dbDate = new java.sql.Date(formattedDate.getTime());
        System.out.println("SQL date in Java : " + dbDate);

        return dbDate;
    }
    
    public static Timestamp sysDateStringToTimestampDb() throws ParseException {
        Date sysDate = new Date();
//        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        formattedDate = formatter.parse(dateString);
//        System.out.println("formatted date : "+formattedDate);
        Timestamp sqlDate = new Timestamp(sysDate.getTime());
        System.out.println("SQL timestamp in Java : " + sqlDate);

        return sqlDate;
    }
    
    public static String calcFlagOnStringDate(String dateString) throws ParseException {
        String flag = null;
        Date formattedDate = null;
        Date sysdate = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println("input string : "+dateString);
        formattedDate = formatter.parse(dateString);
//        System.out.println("formatted date : "+formattedDate);
        long difference = sysdate.getTime() - formattedDate.getTime();
        int diff = (int)TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
//        System.out.println ("Days: " + diff);
        if(diff>=180){
            flag = "Y";
        }else{
            flag = "N";
        }

        return flag;
    }
    
    public static String dateFromMillisec(String dateStr) {
        String millisec = dateStr.substring(dateStr.indexOf("(")+1, dateStr.indexOf(")"));
//        System.out.println("millisec is :"+millisec);
        String dobString = null;
        Date dob = new Date(Long.parseLong(millisec));
//        System.out.println("dob is : "+dob);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//        System.out.println("date as string : "+sdf.format(dob));
        dobString = sdf.format(dob);
        return dobString;
    }
    
    //test
    public static Date stringToDateddmmmyyyy(String dateString) throws ParseException {
        Date formattedDate = null;
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        System.out.println("input string : "+dateString);
        formattedDate = formatter.parse(dateString);
        System.out.println("formatted date : "+formattedDate);
        java.sql.Date dbDate = new java.sql.Date(formattedDate.getTime());
        System.out.println("SQL date in Java : " + dbDate);
    

        return formattedDate;
    }
    
//    public static Timestamp dateStringToTimestamp(String dateString) throws ParseException {
//        Date formattedDate = null;
//        DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
//        System.out.println("input string : "+dateString);
//        formattedDate = formatter.parse(dateString);
//        System.out.println("formatted date : "+formattedDate);
//        Timestamp sqlDate = new Timestamp(formattedDate.getTime());
//        System.out.println("SQL timestamp in Java : " + sqlDate);
//
//        return sqlDate;
//    }
}
