package com.awr.autotrustservice.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TestUtil {
    public TestUtil() {
        super();
    }
    public static void main(String[] args) throws Exception {
//        String buid = "32371";
//        String key = "€%$Enrich*&€";
//        System.out.println("encrypted : "+encrypt(buid, key));
//        System.out.println("decrypted : "+decrypt(encrypt(buid, key), key));
        
//        String testString = "{\"BuId\":\"32371\",\"Username\":\"mail@bennyjoseph.com\",\"FirstName\":\"Benny\",\"LastName\":\"Joseph\",\"FullName\":\"Benny Joseph\",\"Email\":\"mail@bennyjoseph.com\",\"Image\":\"https://static.xx.fbcdn.net/rsrc.php/v1/y0/r/XsEg9L6Ie5_.jpg\",\"Points\":\"17008\",\"Gender\":\"Male\",\"DateOfBirth\":\"8/1/1963\"}";
//        String testString = "{\"BuId\":\"32371\",\"Username\":\"mail@bennyjoseph.com\"}";
//
//        if(testString!=null)
//        testString = testString.replaceAll("\\\\", ""); 
//        System.out.println("testString is : "+testString);
//        testPdfFileGeneration();
//        long currDateTime = System.currentTimeMillis();
//        System.out.println("millisec : "+currDateTime);
//        Date thisDate = new Date(Long.parseLong("253402286399997"));
//        System.out.println("date is : "+thisDate);

//        System.out.println("long val is : "+Long.parseLong("-1"));
        
//        String[] test = new String[1];
//        test[0] = "123";
//        test[1] = "456";
//        test[2] = "789";
//        
//        String appName = "testApp";
//        String devicetype = "ios";
//        test(appName, test, devicetype);
        
//        System.out.println("long val of 0 is : "+Long.parseLong("0"));
        
//        String pattern = "https://awr.exavault.com/p/CPO_Images/1N4AL3A99HC101063=14";
//        String pattern = "";
//        String pattern = "https://awr.exavault.com/p/CPO_Images/1N4AL3A99HC101063";
//        if(pattern.contains("=")){
//                        String[] parts = pattern.split("=");
//                        String part1 = parts[0]; //pattern
//                        String part2 = parts[1]; //noOfImages
//                        int count = Integer.parseInt(part2);
//                        
//                        System.out.println("part1 is : "+part1);
//        System.out.println("part2 is : "+part2);
//        }
//        else{
//            System.out.println("NA");
//        }
        
        String inputDate = "2018-05-31 13:12:35.0";
        SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat toFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = fromFormat.parse(inputDate);
        System.out.println(date);
        
        String newformatDate = toFormat.format(date);
        System.out.println("new format : "+newformatDate);
        
    }
    
    public static String encrypt(String text, String key)
    throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    byte[] keyBytes= new byte[16];
    byte[] b= key.getBytes("UTF-8");
    int len= b.length;
    if (len > keyBytes.length) len = keyBytes.length;
    System.arraycopy(b, 0, keyBytes, 0, len);
    SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
    IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
    cipher.init(Cipher.ENCRYPT_MODE,keySpec,ivSpec);

    byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
    BASE64Encoder encoder = new BASE64Encoder();
    return encoder.encode(results);
     
    }
    
    public static String decrypt(String text, String key) throws Exception{
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    byte[] keyBytes= new byte[16];
    byte[] b= key.getBytes("UTF-8");
    int len= b.length;
    if (len > keyBytes.length) len = keyBytes.length;
    System.arraycopy(b, 0, keyBytes, 0, len);
    SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
    IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
    cipher.init(Cipher.DECRYPT_MODE,keySpec,ivSpec);

    BASE64Decoder decoder = new BASE64Decoder();
    byte [] results = cipher.doFinal(decoder.decodeBuffer(text));
    return new String(results,"UTF-8");
    }
    
    public static void testPdfFileGeneration(){
        InputStream inputStream = null;
        OutputStream outputStream = null;

                try {
                        // read this file into InputStream
                        inputStream = new FileInputStream(getLocalDirectoryFilePath("183.pdf"));

                        // write the inputStream to a FileOutputStream
//                        outputStream =
//                            new FileOutputStream(new File(getLocalDirectoryFilePath("183_CopiedFile.pdf")));
                          outputStream =
                            new FileOutputStream(getLocalDirectoryFilePath("183_CopiedFile.pdf"));

                        int read = 0;
                        byte[] bytes = new byte[1024];

                        while ((read = inputStream.read(bytes)) != -1) {
                                outputStream.write(bytes, 0, read);
                        }

                        System.out.println("Done!");

                } catch (IOException e) {
                        e.printStackTrace();
                } finally {
                        if (inputStream != null) {
                                try {
                                        inputStream.close();
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                        }
                        if (outputStream != null) {
                                try {
                                        outputStream.close();
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }

                        }
                }
            }
    
    public static String getLocalDirectoryFilePath(String fileName){
        String path = null;
        
        String os=System.getProperty("os.name");
        if( os.contains("Windows")){
             path=System.getProperty("user.dir")+ "\\"+fileName;
        }else{
             path=System.getProperty("user.dir")+ "/"+fileName;
               
        }
        
        return path;
    }
    
    public static String modifyArray(String[] test){
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
    
    public static void test(String appName, String[] userId, String deviceType){
        String query = null;
        query = "SELECT token_id, device_type, user_id \n" + 
                    "FROM \n" + 
                    "xxaac.xxdmv_ioms_push_noti_lines \n" +
                    "WHERE \n" + 
                    "application_name = '" + appName + "'"; 
        
        if(userId!=null && userId.length>0){
            UtilityClass util = new UtilityClass();
            String userIdList = util.modifyArray(userId);
            query = query + "AND \n" + 
                    "user_id IN " + userIdList + " \n";
        }
        if(deviceType!=null){
            query = query + "AND \n" + 
                    "UPPER(device_type) = " + "UPPER('" + deviceType + "')";
        }
        
        System.out.println(" result : \n"+query);
    }

}
