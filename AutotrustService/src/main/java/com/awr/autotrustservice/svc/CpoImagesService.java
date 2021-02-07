package com.awr.autotrustservice.svc;

import com.awr.autotrustservice.dto.VehicleImage;
import com.awr.autotrustservice.response.VehicleImagesResponse;
import com.awr.autotrustservice.util.DBConnect;
import com.awr.autotrustservice.util.LogData;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

@Path("cpoImages")
@Consumes("application/json")
@Produces("application/json")
public class CpoImagesService {
    public CpoImagesService() {
        super();
    }
    
    @GET
    @Path("populateImage")
    public String populateImage(@QueryParam("vinNumber") String vinNumber){
        Gson gson = new Gson();
        Connection conn = null;
        String response = "Failed";
        System.out.println("in populateImage ");
        conn = DBConnect.getConnection();
        
        //ftp details
        String server = "awr.exavault.com";
        int port = 21;
        String user = "awr";
        String pass = "welcome$123";
        FTPClient ftpClient = new FTPClient();
        
        try{
            ftpClient.connect(server, port);
            ftpClient.enterLocalPassiveMode();
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.setSoTimeout(200000);
            
            for(int i =0; i<20;i++){
            
            String remoteFilePath = "/CPO_Images/"+vinNumber+"_"+i+".jpg";
            System.out.println("remote file path is : "+remoteFilePath);
            ftpClient.setBufferSize(1024 * 1024);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setSoTimeout(200000);
            ftpClient.setControlKeepAliveTimeout(300);
            ftpClient.setControlKeepAliveReplyTimeout(30000);
                
            FTPFile ftpFile = ftpClient.mlistFile(remoteFilePath);
            if(ftpFile!=null){
                System.out.println("file not null");
                String imgUrl = "https://awr.exavault.com/p"+ftpFile.getName();
                //do nothing - go to next loop
                            
                
                
            }else{
                System.out.println(" i values is : "+i+" file empty");
                //here update no_of_images and image_url_pattern
                String origImagePattern = "https://awr.exavault.com/p/CPO_Images/"+ vinNumber+"="+String.valueOf(i);
                 
                if(i>0){
                    //update values
                    PreparedStatement ps = conn.prepareStatement("UPDATE xxdmv_cpo_dealer_vehicles SET image_url_pattern = ? WHERE serial_number = ?");

                        ps.setString(1,origImagePattern);
                        ps.setString(2,vinNumber);

                        ps.executeUpdate();
                        ps.close();
                    response = "Success";
                }else{
                    //update empty
                    PreparedStatement ps = conn.prepareStatement("UPDATE xxdmv_cpo_dealer_vehicles SET image_url_pattern = ? WHERE serial_number = ?");

                        ps.setString(1,origImagePattern);
                        ps.setString(2,vinNumber);

                        ps.executeUpdate();
                        ps.close();
                    response = "Success";
                }
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
                break;
            }
            
                
            }
           
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally{
            try{
            conn.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
        return gson.toJson(response);
        
    }
}
