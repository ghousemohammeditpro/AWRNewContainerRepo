package com.awr.autotrustservice.svc;

import com.awr.autotrustservice.dto.CpoDealerVehicle;
import com.awr.autotrustservice.dto.CustomerDAO;
import com.awr.autotrustservice.dto.CustomerDetails;
import com.awr.autotrustservice.dto.CustomerServices;
import com.awr.autotrustservice.dto.InstalledBaseDAO;
import com.awr.autotrustservice.dto.ItemRecord;
import com.awr.autotrustservice.dto.Lookup;
import com.awr.autotrustservice.dto.PartyDetailsObject;
import com.awr.autotrustservice.request.CreateBookingRequest;
import com.awr.autotrustservice.request.CreateMakanakRequest;
import com.awr.autotrustservice.request.CustomersRequest;
import com.awr.autotrustservice.request.InstalledBaseRequest;
import com.awr.autotrustservice.request.ItemRecordRequest;
import com.awr.autotrustservice.request.LoginRequest;
import com.awr.autotrustservice.response.CpoDealerVehiclesResponse;
import com.awr.autotrustservice.response.CreateRetailCustomerResponse;
import com.awr.autotrustservice.response.GenericResponse;
import com.awr.autotrustservice.response.InstalledBaseCreateUpdateResponse;
import com.awr.autotrustservice.response.ItemRecordResponse;
import com.awr.autotrustservice.response.LoginResponse;
import com.awr.autotrustservice.response.PartyDetailsResponse;
import com.awr.autotrustservice.response.ResponseObject;
import com.awr.autotrustservice.util.CommonUtil;
import com.awr.autotrustservice.util.DBConnect;
import com.awr.autotrustservice.util.LogData;
import com.awr.autotrustservice.util.LoginUtil;
import com.awr.autotrustservice.util.NotificationAuditLogger;
import com.awr.autotrustservice.util.NotificationExceptionLogger;

import com.awr.autotrustservice.util.UtilityClass;

import com.awr.autotrustservice.util.Utils;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.QueryParam;

import javax.ws.rs.core.MediaType;

import oracle.jdbc.OracleTypes;

import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

@Path("autotrustService")
@Consumes("application/json")
@Produces("application/json")
public class ServiceApis {
    public ServiceApis() {
        super();
    }
    NotificationAuditLogger logger = new NotificationAuditLogger();
    NotificationExceptionLogger exceptionLogger = new NotificationExceptionLogger();
    SuccessService successService = new SuccessService();
    
    
    @PUT
    @Path("itemRecordService")
    public ItemRecordResponse itemRecordService(ItemRecordRequest req, @HeaderParam("appName") String appName){
        Connection conn = null;
        ItemRecordResponse response = new ItemRecordResponse();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal itemRecordService ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        CallableStatement stmt = null;
        ResultSet rs = null;
        ArrayList<ItemRecord> itemRecList = new ArrayList<ItemRecord>();
        //where to pass vendor value??
        System.out.println("appName in internal itemRecordService is : "+appName);
        
        try{
//        successResponse = successService.excuteService("itemRecordService", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Item Record Service", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("itemRecordService");
            log.setUserName(req.getServiceAdvisorID());
            log.setAttribute1("orgId:"+req.getOrganizationID());
            
            stmt =
                conn.prepareCall("{call apps.XXDMS_VR_PKG2.GET_INVENTORY_ITEM(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            
            stmt.setString(1, req.getOrganizationID());
            stmt.setString(2, req.getInventoryItemID());
            stmt.setString(3, req.getItemType());
            stmt.setString(4, req.getModel());
            stmt.setString(5, req.getColor());
            stmt.setString(6, req.getTrim());
            stmt.setString(7, req.getYear());
            stmt.setString(8, req.getItemCode());
            stmt.setString(9, req.getUserID());
            stmt.setString(10, req.getServiceAdvisorID());
            stmt.registerOutParameter(11, OracleTypes.CURSOR); 
            stmt.registerOutParameter(12, OracleTypes.VARCHAR); 
            stmt.registerOutParameter(13, OracleTypes.VARCHAR); 
           
            rs=stmt.executeQuery();
            
            if(stmt.getString(12)!=null){
            response.setRetCode(stmt.getString(12));
            }else{
            response.setRetCode("");
            }
            if(stmt.getString(13)!=null){
            response.setRetMsg(stmt.getString(13));
            }else{
            response.setRetMsg("");
            }
            
            if (stmt.getObject(11) != null) {
            rs = (ResultSet) stmt.getObject(11);
            while (rs.next()) {
                ItemRecord itemRecord = new ItemRecord();
                if(rs.getString("ITEM_CODE") == null){
                    itemRecord.setItemCode("");
                }else{
                    itemRecord.setItemCode(rs.getString("ITEM_CODE"));
                }
                if(rs.getString("ITEM_DESCRIPTION") == null){
                    itemRecord.setItemDescription("");
                }else{
                    itemRecord.setItemDescription(rs.getString("ITEM_DESCRIPTION"));
                }
                if(rs.getString("ITEM_TYPE") == null){
                    itemRecord.setItemType("");
                }else{
                    itemRecord.setItemType(rs.getString("ITEM_TYPE"));
                }
                if(rs.getString("PRIMARY_UNIT_OF_MEASURE") == null){
                    itemRecord.setPrimaryUnitOfMeasure("");
                }else{
                    itemRecord.setPrimaryUnitOfMeasure(rs.getString("PRIMARY_UNIT_OF_MEASURE"));
                }
                if(rs.getString("MATERIAL_BILLABLE_FLAG") == null){
                    itemRecord.setMaterialBillableFlag("");
                }else{
                    itemRecord.setMaterialBillableFlag(rs.getString("MATERIAL_BILLABLE_FLAG"));
                }
                if(rs.getString("MODEL") == null){
                    itemRecord.setModel("");
                }else{
                    itemRecord.setModel(rs.getString("MODEL"));
                }
                if(rs.getString("COLOR") == null){
                    itemRecord.setColor("");
                }else{
                    itemRecord.setColor(rs.getString("COLOR"));
                }
                if(rs.getString("TRIM") == null){
                    itemRecord.setTrim("");
                }else{
                    itemRecord.setTrim(rs.getString("TRIM"));
                }
                if(rs.getString("YEAR") == null){
                    itemRecord.setYear("");
                }else{
                    itemRecord.setYear(rs.getString("YEAR"));
                }
                if(rs.getString("INVENTORY_ITEM_ID") == null){
                    itemRecord.setInventoryItemID("");
                }else{
                    itemRecord.setInventoryItemID(rs.getString("INVENTORY_ITEM_ID"));
                }
                if(rs.getString("ATTRIBUTE_CATEGORY") == null){
                    itemRecord.setAttributeCategory("");
                }else{
                    itemRecord.setAttributeCategory(rs.getString("ATTRIBUTE_CATEGORY"));
                }
                if(rs.getString("ATTRIBUTE1") == null){
                    itemRecord.setAttribute1("");
                }else{
                    itemRecord.setAttribute1(rs.getString("ATTRIBUTE1"));
                }
                if(rs.getString("ATTRIBUTE2") == null){
                    itemRecord.setAttribute2("");
                }else{
                    itemRecord.setAttribute2(rs.getString("ATTRIBUTE2"));
                }
                if(rs.getString("ATTRIBUTE3") == null){
                    itemRecord.setAttribute3("");
                }else{
                    itemRecord.setAttribute3(rs.getString("ATTRIBUTE3"));
                }
                if(rs.getString("ATTRIBUTE4") == null){
                    itemRecord.setAttribute4("");
                }else{
                    itemRecord.setAttribute4(rs.getString("ATTRIBUTE4"));
                }
                if(rs.getString("ATTRIBUTE5") == null){
                    itemRecord.setAttribute5("");
                }else{
                    itemRecord.setAttribute5(rs.getString("ATTRIBUTE5"));
                }
                if(rs.getString("ATTRIBUTE6") == null){
                    itemRecord.setAttribute6("");
                }else{
                    itemRecord.setAttribute6(rs.getString("ATTRIBUTE6"));
                }
                if(rs.getString("ATTRIBUTE7") == null){
                    itemRecord.setAttribute7("");
                }else{
                    itemRecord.setAttribute7(rs.getString("ATTRIBUTE7"));
                }
                if(rs.getString("ATTRIBUTE8") == null){
                    itemRecord.setAttribute8("");
                }else{
                    itemRecord.setAttribute8(rs.getString("ATTRIBUTE8"));
                }
                if(rs.getString("ATTRIBUTE9") == null){
                    itemRecord.setAttribute9("");
                }else{
                    itemRecord.setAttribute9(rs.getString("ATTRIBUTE9"));
                }
                if(rs.getString("ATTRIBUTE10") == null){
                    itemRecord.setAttribute10("");
                }else{
                    itemRecord.setAttribute10(rs.getString("ATTRIBUTE10"));
                }
                if(rs.getString("ATTRIBUTE11") == null){
                    itemRecord.setAttribute11("");
                }else{
                    itemRecord.setAttribute11(rs.getString("ATTRIBUTE11"));
                }
                if(rs.getString("ATTRIBUTE12") == null){
                    itemRecord.setAttribute12("");
                }else{
                    itemRecord.setAttribute12(rs.getString("ATTRIBUTE12"));
                }
                if(rs.getString("ATTRIBUTE13") == null){
                    itemRecord.setAttribute13("");
                }else{
                    itemRecord.setAttribute13(rs.getString("ATTRIBUTE13"));
                }
                if(rs.getString("ATTRIBUTE14") == null){
                    itemRecord.setAttribute14("");
                }else{
                    itemRecord.setAttribute14(rs.getString("ATTRIBUTE14"));
                }
                if(rs.getString("ATTRIBUTE15") == null){
                    itemRecord.setAttribute15("");
                }else{
                    itemRecord.setAttribute15(rs.getString("ATTRIBUTE15"));
                }
                
                itemRecList.add(itemRecord);
            }
            
            }else{
            
            }
            
            response.setItemRecordList(itemRecList.toArray(new ItemRecord[itemRecList.size()]));
          
    //            log.setAttribute2("instanceId:"+instanceId);
            logger.callAudit(log);
        }else{
            response.setRetCode("E");
            response.setRetMsg(okExecuteResponse.getMessage());
            return response;
        }
        }catch(Exception e){
            log.setErrorMessage(e.toString());
                exceptionLogger.callAudit(log);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally{
            try{
            DBConnect.closeConnection(conn);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    //        System.out.println("response obj data is : "+response.getBuId()+" , "+response.getDateOfBirth());
        return response;
    }

    @GET
    @Path("partyDetails")
    public PartyDetailsResponse getPartyDetails(@QueryParam("partyName") String partyName,
                                                      @QueryParam("partyType") String partyType,
                                                      @QueryParam("partyID") String partyID,
                                                      @QueryParam("contactNo") String contactNo,
                                                     @HeaderParam("appName") String appName){
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        PartyDetailsResponse response = new PartyDetailsResponse();
        ArrayList<PartyDetailsObject> partyDetailsList = new ArrayList<PartyDetailsObject>();
        Gson gson = new Gson();
        LogData log = new LogData();
        System.out.println("in internal getPartyDetails ");
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal getPartyDetails is : "+appName);
        String result = null;
        
        try{
//        successResponse = successService.excuteService("getPartyDetails", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Party Details", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S")) 
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("getPartyDetails");
            log.setUserName("");
            log.setAttribute1("appName:"+appName);
            
            stmt = conn.prepareCall("{call apps.XXDMS_VR_PKG.GET_PARTY_DETAILS(?,?,?,?,?,?,?)}");
            
            stmt.setString(1, partyName);
            stmt.setString(2, partyType);
            stmt.setString(3, partyID);
            stmt.setString(4, contactNo);
            stmt.registerOutParameter(5, OracleTypes.CURSOR);
            stmt.registerOutParameter(6, Types.VARCHAR);
            stmt.registerOutParameter(7, Types.VARCHAR);
            
            rs = stmt.executeQuery();
            
            if(stmt.getString(6)!=null){
            response.setRetCode(stmt.getString(6));
            }else{
            response.setRetCode("");
            }
            if(stmt.getString(7)!=null){
            response.setRetMsg(stmt.getString(7));
            }else{
            response.setRetMsg("");
            }
            
            if (stmt.getObject(5) != null) {
            rs = (ResultSet) stmt.getObject(5);
                while (rs.next()) {
                    PartyDetailsObject partyDetails = new PartyDetailsObject();
                    if(rs.getString("PARTY_NAME") == null){
                        partyDetails.setPartyName("");
                    }else{
                        partyDetails.setPartyName(rs.getString("PARTY_NAME"));
                    }
                    if(rs.getString("ACCOUNT_NUMBER") == null){
                        partyDetails.setAccountNumber("");
                    }else{
                        partyDetails.setAccountNumber(rs.getString("ACCOUNT_NUMBER"));
                    }
                    if(rs.getString("PARTY_ID") == null){
                        partyDetails.setPartyId("");
                    }else{
                        partyDetails.setPartyId(rs.getString("PARTY_ID"));
                    }
                    if(rs.getString("ACCOUNT_ID") == null){
                        partyDetails.setAccountId("");
                    }else{
                        partyDetails.setAccountId(rs.getString("ACCOUNT_ID"));
                    }
                    if(rs.getString("ATTRIBUTE_CATEGORY") == null){
                        partyDetails.setAttributeCategory("");
                    }else{
                        partyDetails.setAttributeCategory(rs.getString("ATTRIBUTE_CATEGORY"));
                    }
                    if(rs.getString("ATTRIBUTE1") == null){
                        partyDetails.setAttribute1("");
                    }else{
                        partyDetails.setAttribute1(rs.getString("ATTRIBUTE1"));
                    }
                    if(rs.getString("ATTRIBUTE2") == null){
                        partyDetails.setAttribute2("");
                    }else{
                        partyDetails.setAttribute2(rs.getString("ATTRIBUTE2"));
                    }
                    if(rs.getString("ATTRIBUTE3") == null){
                        partyDetails.setAttribute3("");
                    }else{
                        partyDetails.setAttribute3(rs.getString("ATTRIBUTE3"));
                    }
                    if(rs.getString("ATTRIBUTE4") == null){
                        partyDetails.setAttribute4("");
                    }else{
                        partyDetails.setAttribute4(rs.getString("ATTRIBUTE4"));
                    }
                    if(rs.getString("ATTRIBUTE5") == null){
                        partyDetails.setAttribute5("");
                    }else{
                        partyDetails.setAttribute5(rs.getString("ATTRIBUTE5"));
                    }
                    if(rs.getString("ATTRIBUTE6") == null){
                        partyDetails.setAttribute6("");
                    }else{
                        partyDetails.setAttribute6(rs.getString("ATTRIBUTE6"));
                    }
                    if(rs.getString("ATTRIBUTE7") == null){
                        partyDetails.setAttribute7("");
                    }else{
                        partyDetails.setAttribute7(rs.getString("ATTRIBUTE7"));
                    }
                    if(rs.getString("ATTRIBUTE8") == null){
                        partyDetails.setAttribute8("");
                    }else{
                        partyDetails.setAttribute8(rs.getString("ATTRIBUTE8"));
                    }
                    if(rs.getString("ATTRIBUTE9") == null){
                        partyDetails.setAttribute9("");
                    }else{
                        partyDetails.setAttribute9(rs.getString("ATTRIBUTE9"));
                    }
                    if(rs.getString("ATTRIBUTE10") == null){
                        partyDetails.setAttribute10("");
                    }else{
                        partyDetails.setAttribute10(rs.getString("ATTRIBUTE10"));
                    }
                    if(rs.getString("ATTRIBUTE11") == null){
                        partyDetails.setAttribute11("");
                    }else{
                        partyDetails.setAttribute11(rs.getString("ATTRIBUTE11"));
                    }
                    if(rs.getString("ATTRIBUTE12") == null){
                        partyDetails.setAttribute12("");
                    }else{
                        partyDetails.setAttribute12(rs.getString("ATTRIBUTE12"));
                    }
                    if(rs.getString("ATTRIBUTE13") == null){
                        partyDetails.setAttribute13("");
                    }else{
                        partyDetails.setAttribute13(rs.getString("ATTRIBUTE13"));
                    }
                    if(rs.getString("ATTRIBUTE14") == null){
                        partyDetails.setAttribute14("");
                    }else{
                        partyDetails.setAttribute14(rs.getString("ATTRIBUTE14"));
                    }
                    if(rs.getString("ATTRIBUTE15") == null){
                        partyDetails.setAttribute15("");
                    }else{
                        partyDetails.setAttribute15(rs.getString("ATTRIBUTE15"));
                    }
                    
                    partyDetailsList.add(partyDetails);
                }
            }
            response.setPartyDetailsList(partyDetailsList.toArray(new PartyDetailsObject[partyDetailsList.size()]));
            logger.callAudit(log);
        }else{
            response.setRetCode("E");
            response.setRetMsg(okExecuteResponse.getMessage());
            return response;
        }
                    
        }catch(Exception e){
            log.setErrorMessage(e.toString());
                    exceptionLogger.callAudit(log);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally{
            try{
            DBConnect.closeConnection(conn);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
        return response;
    }

    @PUT
    @Path("createOrganizationParty")
    @Consumes("application/json")
    @Produces("application/json")
    public String createOrganizaitonCustomer(CustomersRequest req,
                                             @HeaderParam("appName") String appName) {
        String retVal= "";
        String mode= "";
        Connection conn = null;
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal createOrganizaitonCustomer ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        CallableStatement stmt = null;
        ResultSet rs = null;
        Gson oGSON = new GsonBuilder().serializeNulls().create();
        //where to pass vendor value??
        System.out.println("appName in internal createOrganizaitonCustomer is : "+appName);
        
        try{
//        successResponse = successService.excuteService("createOrganizaitonCustomer", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Create Organization Party", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("createOrganizaitonCustomer");
            log.setUserName(req.getEmpID());
            log.setAttribute1("orgId:"+req.getOrgID());
            
            
            
            retVal=  oGSON.toJson(processCustomersPutRequest(req));
            
            oGSON=null;
            //            log.setAttribute2("instanceId:"+instanceId);
                    logger.callAudit(log);
        }else{
            retVal = oGSON.toJson(okExecuteResponse.getMessage());
            return retVal;
        }
        } catch (Exception ex) {
            log.setErrorMessage(ex.toString());
                        exceptionLogger.callAudit(log);
            System.out.println("");
        }
        return retVal;
    }

    private ResponseObject processCustomersPutRequest(CustomersRequest request) {
        CustomerDAO dao = new CustomerDAO();
        ResponseObject retVal = null;
        // We are not validating the Header
        System.out.println("About to Call insertOrganizationCustomers");
        retVal =  dao.insertOrganizationCustomers(request, "INSERT");
    
        return retVal;
    }
    
    @PUT
    @Path("createRetailParty")
    public CreateRetailCustomerResponse createRetailCustomer(CustomerDetails customerDetails, @QueryParam("oprUnitId") String operatingUnitId,
                                             @HeaderParam("appName") String appName) {
        CreateRetailCustomerResponse response = new CreateRetailCustomerResponse();
        String retVal = null;
        Connection conn = null;
        LogData log = new LogData();
        System.out.println("in internal createRetailCustomer ");
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        //where to pass vendor value??
        System.out.println("appName in internal createRetailCustomer is : "+appName);
        
        try {
//            successResponse = successService.excuteService("createRetailCustomer", "EN");
//            if (successResponse.equals("S")) 
            okExecuteResponse = successService.okToExcuteService(appName, "Create Retail Party", "123");
            if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
            {
                System.out.println("Success service executed");
                
                log.setAppCode("AUTOTRUST");
                log.setAppName("AutotrustServices");
                log.setDocCode("createRetailCustomer");
                log.setUserName(operatingUnitId);
//                log.setAttribute1("orgId:"+req.getOrgID());
                
            int operUnitId = Integer.parseInt(operatingUnitId);
            CustomerServices customerServices = new CustomerServices();
            response = customerServices.insertRetailCustomers(customerDetails, operUnitId);
            
                //            log.setAttribute2("instanceId:"+instanceId);
                        logger.callAudit(log);
            }else{
            response.setRetCode("E");
            response.setRetMsg(okExecuteResponse.getMessage());
            return response;
            }
        } catch (Exception e) {
            log.setErrorMessage(e.toString());
                        exceptionLogger.callAudit(log);
            e.printStackTrace();
        }
        return response;
    }
    
    //Lekha New
    @PUT
    @Path("createAndUpdateInstallBase")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createAndUpdateInstallBase(InstalledBaseRequest request, @HeaderParam("appName") String appName) {
        String retVal = "";
        Connection conn = null;
        LogData log = new LogData();
        System.out.println("in internal createAndUpdateInstallBase ");
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        Gson oGSON = new GsonBuilder().serializeNulls().create();
        //where to pass vendor value??
        System.out.println("appName in internal createAndUpdateInstallBase is : "+appName);
        
        try {
//            successResponse = successService.excuteService("createRetailCustomer", "EN");
//            if (successResponse.equals("S"))
            okExecuteResponse = successService.okToExcuteService(appName, "Create And Update Install Base", "123");
            if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
            {
                System.out.println("Success service executed");
                
                log.setAppCode("AUTOTRUST");
                log.setAppName("AutotrustServices");
                log.setDocCode("createAndUpdateInstallBase");
                log.setUserName("");
       
            
        
            retVal=  oGSON.toJson(processInstalledBaseCreateRequest(request));
       
            oGSON=null;
                //            log.setAttribute2("instanceId:"+instanceId);
                        logger.callAudit(log);
            }else{
            retVal = oGSON.toJson(okExecuteResponse.getMessage());
            return retVal;
            }
        } catch (Exception e) {
            log.setErrorMessage(e.toString());
                        exceptionLogger.callAudit(log);
            e.printStackTrace();
        }
        return retVal;
    }
    
    private InstalledBaseCreateUpdateResponse processInstalledBaseCreateRequest(InstalledBaseRequest request) throws SQLException {
        InstalledBaseDAO dao = new InstalledBaseDAO();
        InstalledBaseCreateUpdateResponse response = null;
        
        //We are not creating 2 separate methods for Insert and Update
        //In InstalledBaseRequest, we are checking ActionType (whether Insert Or Update) 
        //inside the createAndUpdateInstalledBase method
        response =  dao.createAndUpdateInstalledBase(request);
        return response;
    }

    @PUT
    @Path("createMakanak")
    public GenericResponse createMakanak(CreateMakanakRequest req, @HeaderParam("appName") String appName){
        Connection conn = null;
        GenericResponse response = new GenericResponse();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal createMakanak ");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        CallableStatement stmt = null;
        ResultSet rs = null;
        //where to pass vendor value??
        System.out.println("appName in internal createMakanak is : "+appName);
        
        try{
//        successResponse = successService.excuteService("createMakanak", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Create Makanak", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S"))
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("createMakanak");
            log.setUserName("Id:"+req.getEmailId());
            log.setAttribute1("fName:"+req.getFirstName());
            
            stmt =
                conn.prepareCall("{call apps.xxdmv_cpo_autotrust_mob_pkg.process_makanak_details(?,?,?)}");
            STRUCT makanakRec = null;
            Object[] makanak_record   = new Object[35];
            if(req!=null){
                
//                makanak_record[0] = Long.parseLong("1"); //Dummy value - as makanak id is autogenerated at db level
                makanak_record[0] = null;
                makanak_record[1] = req.getFirstName();
                makanak_record[2] = req.getLastName();
                makanak_record[3] = req.getMobileNumber();
                makanak_record[4] = req.getPhoneNumber();
                makanak_record[5] = req.getEmailId();
                makanak_record[6] = req.getPickupLocation();
                makanak_record[7] = CommonUtil.stringToDateDb(req.getPreferredDropoffDate());
                makanak_record[8] = req.getDropoffLocation();
                makanak_record[9] = req.getChosenPackage();
                makanak_record[10] = req.getBookingStatus();
                makanak_record[11] = req.getAdditionalNotes();
                makanak_record[12] = req.getAttribute1();
                makanak_record[13] = req.getAttribute2();
                makanak_record[14] = req.getAttribute3();
                makanak_record[15] = req.getAttribute4();
                makanak_record[16] = req.getAttribute5();
                makanak_record[17] = req.getAttribute6();
                makanak_record[18] = req.getAttribute7();
                makanak_record[19] = req.getAttribute8();
                makanak_record[20] = req.getAttribute9();
                makanak_record[21] = req.getAttribute10();
                makanak_record[22] = req.getAttribute11();
                makanak_record[23] = req.getAttribute12();
                makanak_record[24] = req.getAttribute13();
                makanak_record[25] = req.getAttribute14();
                makanak_record[26] = req.getAttribute15();
                makanak_record[27] = CommonUtil.sysDateStringToTimestampDb();
//                System.out.println("last updated by value is : "+req.getLastUpdatedBy());
                if(req.getLastUpdatedBy()!=null && req.getLastUpdatedBy()!="" && !req.getLastUpdatedBy().isEmpty()){
                    makanak_record[28] = Long.parseLong(req.getLastUpdatedBy());
                }else{
                    makanak_record[28] = Long.parseLong("-1");
                }
                
                makanak_record[29] = CommonUtil.sysDateStringToTimestampDb();
                if(req.getCreatedBy()!=null && req.getCreatedBy()!="" && !req.getCreatedBy().isEmpty()){
                    makanak_record[30] = Long.parseLong(req.getCreatedBy());
                }else{
                    makanak_record[30] = Long.parseLong("-1");
                }
                
                if(req.getLastUpdateLogin()!=null && req.getLastUpdateLogin()!="" && !req.getLastUpdateLogin().isEmpty()){
                    makanak_record[31] = Long.parseLong(req.getLastUpdateLogin());
                }else{
                    makanak_record[31] = Long.parseLong("-1");
                }
               
                makanak_record[32] = CommonUtil.stringToDateDb(req.getPreferredDate());
                makanak_record[33] = req.getPreferredDropoffTime();
                makanak_record[34] = req.getPreferredTime();
                    
            }
            StructDescriptor structContactdesc = StructDescriptor.createDescriptor("APPS.AUTOTRUST_MAKANAK_RECORD", conn);
            makanakRec = new STRUCT(structContactdesc, conn, makanak_record);
            
            stmt.setObject(1, makanakRec);
            stmt.registerOutParameter(2, OracleTypes.VARCHAR); 
            stmt.registerOutParameter(3, OracleTypes.VARCHAR); 
           
            rs=stmt.executeQuery();
            
            if(stmt.getString(2)!=null){
                System.out.println("status is :"+stmt.getString(2));
            response.setStatus(stmt.getString(2));
            }else{
            response.setStatus("");
            }
            if(stmt.getString(3)!=null){
                System.out.println("message is :"+stmt.getString(3));
            response.setMessage(stmt.getString(3));
            }else{
            response.setMessage("");
            }
            
          
    //            log.setAttribute2("instanceId:"+instanceId);
            logger.callAudit(log);
        }else{
            response.setStatus("E");
            response.setMessage(okExecuteResponse.getMessage());
            return response;
        }
        }catch(Exception e){
            log.setErrorMessage(e.toString());
                exceptionLogger.callAudit(log);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally{
            try{
            DBConnect.closeConnection(conn);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    //        System.out.println("response obj data is : "+response.getBuId()+" , "+response.getDateOfBirth());
        return response;
    }
    
    @PUT
    @Path("createBooking")
    public GenericResponse createBooking(CreateBookingRequest req, @HeaderParam("appName") String appName){
        Connection conn = null;
        GenericResponse response = new GenericResponse();
        LogData log = new LogData();
        Gson gson = new Gson();
        System.out.println("in internal createBooking");
        String result = null;
        String successResponse = null;
        GenericResponse okExecuteResponse = new GenericResponse();
        conn = DBConnect.getConnection();
        CallableStatement stmt = null;
        ResultSet rs = null;
        //where to pass vendor value??
        System.out.println("appName in internal createBooking is : "+appName);
        
        try{
//        successResponse = successService.excuteService("createBooking", "EN");
//        if (successResponse.equals("S")) 
        okExecuteResponse = successService.okToExcuteService(appName, "Create Booking", "123");
        if (okExecuteResponse.getStatus().equalsIgnoreCase("S")) 
        {
            System.out.println("Success service executed");
            
            log.setAppCode("AUTOTRUST");
            log.setAppName("AutotrustServices");
            log.setDocCode("createBooking");
            log.setUserName("Id:"+req.getEmailId());
            log.setAttribute1("type:"+req.getBookingType());
            
            stmt =
                conn.prepareCall("{call apps.xxdmv_cpo_autotrust_mob_pkg.process_aebooking_details(?,?,?,?)}");
            STRUCT bookingRec = null;
            Object[] booking_record   = new Object[40];
            if(req!=null){
                
//                booking_record[0] = Long.parseLong("1"); //Dummy value - as booking id is autogenerated at db level
                booking_record[0] = null;
                booking_record[1] = req.getFirstName();
                booking_record[2] = req.getLastName();
                booking_record[3] = req.getMobileNumber();
                booking_record[4] = req.getPhoneNumber();
                booking_record[5] = req.getEmailId();
                booking_record[6] = req.getAdditionalNotes();
                booking_record[7] = req.getModeOfContact();
                booking_record[8] = req.getRegisterFlag();
                booking_record[9] = req.getConfirmBookingFlag();
                booking_record[10] = req.getCaptchaCode();
                booking_record[11] = CommonUtil.stringToDateDb(req.getPreferredDate());
                booking_record[12] = req.getAddress();
                booking_record[13] = req.getMake();
                booking_record[14] = req.getModel();
                booking_record[15] = req.getModelYear();
                booking_record[16] = req.getPlateNumber();
                booking_record[17] = req.getTypeOfService();
                booking_record[18] = req.getAttribute1();
                booking_record[19] = req.getAttribute2();
                booking_record[20] = req.getAttribute3();
                booking_record[21] = req.getAttribute4();
                booking_record[22] = req.getAttribute5();
                booking_record[23] = req.getAttribute6();
                booking_record[24] = req.getAttribute7();
                booking_record[25] = req.getAttribute8();
                booking_record[26] = req.getAttribute9();
                booking_record[27] = req.getAttribute10();
                booking_record[28] = req.getAttribute11();
                booking_record[29] = req.getAttribute12();
                booking_record[30] = req.getAttribute13();
                booking_record[31] = req.getAttribute14();
                booking_record[32] = req.getAttribute15();
                booking_record[33] = CommonUtil.sysDateStringToTimestampDb();
                if(req.getLastUpdatedBy()!=null && req.getLastUpdatedBy()!="" && !req.getLastUpdatedBy().isEmpty()){
                    booking_record[34] = Long.parseLong(req.getLastUpdatedBy());
                }else{
                    booking_record[34] = Long.parseLong("-1");
                }
                
                booking_record[35] = CommonUtil.sysDateStringToTimestampDb();
                if(req.getCreatedBy()!=null && req.getCreatedBy()!="" && !req.getCreatedBy().isEmpty()){
                    booking_record[36] = Long.parseLong(req.getCreatedBy());
                }else{
                    booking_record[36] = Long.parseLong("-1");
                }
                if(req.getLastUpdateLogin()!=null && req.getLastUpdateLogin()!="" && !req.getLastUpdateLogin().isEmpty()){
                    booking_record[37] = Long.parseLong(req.getLastUpdateLogin());
                }else{
                    booking_record[37] = Long.parseLong("-1");
                }
                
                booking_record[38] = req.getModeOfBooking();
                booking_record[39] = req.getPreferredTime();
                    
            }
            StructDescriptor structContactdesc = StructDescriptor.createDescriptor("APPS.AUTOTRUST_EABOOKING_RECORD", conn);
            bookingRec = new STRUCT(structContactdesc, conn, booking_record);
            
            stmt.setObject(1, bookingRec);
            stmt.setString(2, req.getBookingType());
            stmt.registerOutParameter(3, OracleTypes.VARCHAR); 
            stmt.registerOutParameter(4, OracleTypes.VARCHAR); 
           
            rs=stmt.executeQuery();
            
            if(stmt.getString(3)!=null){
                System.out.println("status is :"+stmt.getString(3));
            response.setStatus(stmt.getString(3));
            }else{
            response.setStatus("");
            }
            if(stmt.getString(4)!=null){
                System.out.println("message is :"+stmt.getString(4));
            response.setMessage(stmt.getString(4));
            }else{
            response.setMessage("");
            }
            
          
    //            log.setAttribute2("instanceId:"+instanceId);
            logger.callAudit(log);
        }else{
            response.setStatus("E");
            response.setMessage(okExecuteResponse.getMessage());
            return response;
        }
        }catch(Exception e){
            log.setErrorMessage(e.toString());
                exceptionLogger.callAudit(log);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally{
            try{
            DBConnect.closeConnection(conn);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    //        System.out.println("response obj data is : "+response.getBuId()+" , "+response.getDateOfBirth());
        return response;
    }
}
