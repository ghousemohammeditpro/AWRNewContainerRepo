package com.awr.autotrustservice.util;

import java.io.FileInputStream;

import java.util.Properties;

public class PropertiesUtil {
    public PropertiesUtil() {
        super();
    }
    public String getPropertyValue(String prop){
        String value = null;
        //FileInputStream propertyFile = null;
        
        try {
            // if (System.getProperty("os.name").contains("Windows")) {
            //     propertyFile = new FileInputStream(System.getProperty("user.dir") + "\\serviceConfigAutotrust.properties");

            // } else {
            //     propertyFile = new FileInputStream(System.getProperty("user.dir") + "/serviceConfigAutotrust.properties");
            // }
            // Properties properties = new Properties();
            // properties.load(propertyFile);
            // propertyFile.close();
            
            // value = properties.getProperty(prop);

            value = System.getenv(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return value;
    }
}
