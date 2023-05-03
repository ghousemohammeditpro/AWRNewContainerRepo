package com.awr.autotrustproxy.util;

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
//        System.out.println("encrypted : "+encryptString(buid, key));
//        System.out.println("decrypted : "+decrypt(encryptString(buid, key), key));
        long currDateTime = System.currentTimeMillis();
        System.out.println("millisec : "+currDateTime);
        Date thisDate = new Date(currDateTime);
        System.out.println("date is : "+thisDate);
    }
    
    public static String modifyString(String abc){
        String modified = null;
        modified = abc.replaceAll(";", ",");
        return modified;
    }
    public static String encryptString(String text, String key)
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
}
