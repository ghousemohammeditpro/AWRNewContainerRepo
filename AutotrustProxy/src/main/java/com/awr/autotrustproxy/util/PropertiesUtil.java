package com.awr.autotrustproxy.util;

import java.io.FileInputStream;

import java.util.Properties;

public class PropertiesUtil {
    public PropertiesUtil() {
        super();
    }
    public String getPropertyValue(String prop){
        String value = null;
        FileInputStream propertyFile = null;
        
        try {
            // if (System.getProperty("os.name").contains("Windows")) {
            //     propertyFile = new FileInputStream(System.getProperty("user.dir") + "\\proxyConfigAutotrust.properties");

            // } else {
            //     propertyFile = new FileInputStream(System.getProperty("user.dir") + "/proxyConfigAutotrust.properties");
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
    
    public String getLocalDirectoryFilePath(String fileName){
        String path = null;
        
        String os=System.getProperty("os.name");
        if( os.contains("Windows")){
             path=System.getProperty("user.dir")+ "\\"+fileName;
        }else{
             path=System.getProperty("user.dir")+ "/"+fileName;
               
        }
        
        return path;
    }
    
    public String getAvailabilityUrl(String instanceId, String serviceAdvisorFlag, String serviceAdvisorId, String groupId,
                                            String locationCode, String appointmentDate, String customerWaiting) {
        
        String availabilityURL = System.getenv("MAIN_URL") + "/autotrust/getAvailability?";
        
        if(instanceId!=null && !instanceId.isEmpty() && instanceId!=""){
            availabilityURL = availabilityURL+"instanceId="+instanceId;
        }
        if(serviceAdvisorFlag!=null && !serviceAdvisorFlag.isEmpty() && serviceAdvisorFlag!=""){
            availabilityURL = availabilityURL+"&serviceAdvisorFlag="+serviceAdvisorFlag;
        }
        if(serviceAdvisorId!=null && !serviceAdvisorId.isEmpty() && serviceAdvisorId!=""){
            availabilityURL = availabilityURL+"&serviceAdvisorId="+serviceAdvisorId;
        }
        if(groupId!=null && !groupId.isEmpty() && groupId!=""){
            availabilityURL = availabilityURL+"&groupId="+groupId;
        }
        if(locationCode!=null && !locationCode.isEmpty() && locationCode!=""){
            availabilityURL = availabilityURL+"&locationCode="+locationCode;
        }
        if(appointmentDate!=null && !appointmentDate.isEmpty() && appointmentDate!=""){
            availabilityURL = availabilityURL+"&appointmentDate="+appointmentDate;
        }
        if(customerWaiting!=null && !customerWaiting.isEmpty() && customerWaiting!=""){
            availabilityURL = availabilityURL+"&customerWaiting="+customerWaiting;
        }
        return availabilityURL;
    }
}
