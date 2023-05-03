package com.awr.autotrustservice.util;

import com.awr.autotrustservice.dto.VehicleImage;

import java.util.ArrayList;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FtpUtil {
    public FtpUtil() {
        super();
    }
    public static void main(String[] args) throws Exception {
        /*ArrayList<VehicleImage> list = new ArrayList<VehicleImage>();
        
//        list = getImages("VF1HSRCHXHA531889");
        list = getImages("VF1HSRCHXHA531");
        if(list.size()>0){
            System.out.println("list returned not null");
        for(int i=0;i<list.size();i++){
            VehicleImage imgUrl = new VehicleImage();
            imgUrl = list.get(i);
            System.out.println("image url at "+i+" is : "+imgUrl.getImageUrl());
        }
        }else{
            System.out.println("list returned is null");
        }*/
        
    }
    
    public static ArrayList<VehicleImage> getImages(String vinNumber){
        String server = "awr.exavault.com";
        int port = 21;
        String user = "awr";
        String pass = "welcome$123";
        ArrayList<VehicleImage> imgArray = new ArrayList<VehicleImage>();
        
        FTPClient ftpClient = new FTPClient();
        try {
        
            ftpClient.connect(server, port);
            ftpClient.enterLocalPassiveMode();
            ftpClient.login(user, pass);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            
            for(int i =0; i<16;i++){
            
            String remoteFilePath = "/CPO_Images/"+vinNumber+"_"+i+".jpg";
                System.out.println("remote file path is : "+remoteFilePath);
        
            FTPFile ftpFile = ftpClient.mlistFile(remoteFilePath);
            if(ftpFile!=null){
                VehicleImage img = new VehicleImage();
                String imgUrl = "https://awr.exavault.com/p"+ftpFile.getName();
                System.out.println("image url is : "+imgUrl);
                img.setImageUrl(imgUrl);
                imgArray.add(img);
            }else{
                break;
            }
                
        }
            
        }catch(Exception e){
                    e.printStackTrace();
        }
        
        return imgArray;
    }
}
