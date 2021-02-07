package com.awr.autotrustservice.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.List;
import java.util.Queue;
import java.util.TimeZone;

import org.apache.log4j.Logger;

public class Utils {
    public Utils() {
        super();
    }
    private static Logger logger = Logger.getLogger(Utils.class);
    
    private static String DEBUG_MODE = "OFF";
    
    public static String VERSION = "1.2";

    
    public static String prepareErrorInfo(String exceptionInfo, String customResponse)
    {
        if(DEBUG_MODE.equalsIgnoreCase("OFF"))
            return customResponse;
        else
            return exceptionInfo;        
    }
    
    public static void writeExtraDebugInfo(String moduleInfo, String logText)
    {
        if(DEBUG_MODE.equalsIgnoreCase("DTL"))
            logger.debug(String.format("[%s] %s", moduleInfo, logText));
    }
        
    public static String formatException(Exception e)
    {
        if(e!=null)
            return String.format("ErrType: %s | ErrMsg: %s", e.getClass().getCanonicalName(), e.getMessage());
        else
            return null;
    }
    
        public static boolean isNullOrEmpty(String value)
        {
                if(value==null)
                        return true;
                else if(value.trim().equals(""))
                        return true;
                else
                        return false;
        }

        public static boolean isNullOrEmpty(List valueList)
        {
                if(valueList==null)
                        return true;
                else if(valueList.size()==0)
                        return true;
                else
                        return false;
        }

        public static boolean isNullOrEmpty(Queue valueList)
        {
                if(valueList==null)
                        return true;
                else if(valueList.size()==0)
                        return true;
                else
                        return false;
        }

        public static boolean isNullOrEmpty(String[] valueList)
        {
                if(valueList==null)
                        return true;
                else if(valueList.length==0)
                        return true;
                else
                        return false;
        }
    
        public static boolean isValidNumber(String number)
        {
                if(number==null)
                        return false;
                else
                        return number.trim().matches("[0-9]+");
        }
    
        public static boolean isAscii(String strData)
        {
                boolean retVal=true;

                if(strData!=null)
                {
                        char[] arrChars=strData.toCharArray();

            for(int i=arrChars.length-1; i>=0; i--)
                        {
                if(arrChars[i] > 127)
                {
                    retVal = false;
                    break;
                }
                        }
                }
                
                return retVal;
        }
    
        public static boolean isExtAscii(String strData)
        {
                boolean retVal=true;

                if(strData!=null)
                {
                        char[] arrChars=strData.toCharArray();

            for(int i=arrChars.length-1; i>=0; i--)
                        {
                if(arrChars[i] > 255)
                {
                    retVal = false;
                    break;
                }
                        }
                }
                
                return retVal;
        }    
    
        private static String charToHex(char ch)
        {
                String strChar=Integer.toHexString(ch).toUpperCase();

                while(strChar.length()<4)
                        strChar="0"+strChar;

        return strChar;
    }
    
        public static String unicodeToHex(String strText, String strEncType)
        {
                StringBuffer sbHexVal=new StringBuffer("");

                try
                {
                        strText=new String(strText.getBytes(strEncType),"UTF-8");

                        for(int i=0;i<strText.length();i++)
                                sbHexVal.append(charToHex(strText.charAt(i)));
                }
                catch(Exception e)
                {
                        logger.debug(formatException(e));
                }

                return sbHexVal.toString();
        }

    public static String getStackTrace(Throwable t)
    {
        String retVal = null;

        if(t!=null)
        {
            StringWriter sWriter = null;
            PrintWriter pWriter = null;
            try 
            {
                sWriter = new StringWriter();
                pWriter = new PrintWriter(sWriter);
                t.printStackTrace(pWriter);
                retVal = sWriter.toString();

                sWriter.close();  sWriter = null;
                pWriter.close(); pWriter = null;
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            finally
            {
                try { if(sWriter!=null) sWriter.close(); } catch (Exception e) {} sWriter=null;
                try { if(pWriter!=null) pWriter.close(); } catch (Exception e) {} pWriter=null; 

                if(retVal==null && t!=null) retVal=t.toString();
            }
        }
        else
            retVal = "N/A";

        return retVal;
    }  
    
    public static String getUTCTimestamp() {
        Calendar cal_Two = Calendar.getInstance(TimeZone.getTimeZone("UTC"));    
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // you can specify format that you want to get
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String timeStamp = sdf.format(cal_Two.getTime());
        return timeStamp;
    }
    
     public static String getUTCTimestamp(String dateFormat) {
        Calendar cal_Two = Calendar.getInstance(TimeZone.getTimeZone("UTC"));    
        final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat); // you can specify format that you want to get
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String timeStamp = sdf.format(cal_Two.getTime());
        return timeStamp;
    }

    public static void main(String[] args) {
         System.out.println(Utils.getUTCTimestamp("dd-MMM-yyyy HH:mm:ss"));
    }
    
    public static String getSQLDate(String dateForFormat)  {
    String utilDate1 =null;
      try {
    
      DateFormat df1 =null;
        if (dateForFormat != null && !dateForFormat.equalsIgnoreCase("")) {
          if (dateForFormat.length() > 12) {
              if (dateForFormat.length() > 12) {
                  if(dateForFormat.length() ==20){
                    df1 = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");  
                  }else{
                    df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                  }
              }
          } else {
            df1 = new SimpleDateFormat("yyyy-MM-dd");
          }
          java.util.Date convertedDate = df1.parse(dateForFormat);
     
          DateFormat requiredFormat=new SimpleDateFormat("dd-MMM-yyyy");
            utilDate1 =  requiredFormat.format(convertedDate.getTime());

        }
      }
        catch(ParseException pe) {
        
          pe.printStackTrace();
        }
       
    return utilDate1;
    }
}
