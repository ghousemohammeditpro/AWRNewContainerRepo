package com.awr.autotrustservice.dto;

import com.awr.autotrustservice.request.InstalledBaseRequest;
import com.awr.autotrustservice.response.InstalledBaseCreateUpdateResponse;
import com.awr.autotrustservice.util.DBConnect;
import com.awr.autotrustservice.util.Utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import oracle.jdbc.OracleConnection;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

public class InstalledBaseDAO {
    public InstalledBaseDAO() {
        super();
    }
    @SuppressWarnings("deprecation")
    public InstalledBaseCreateUpdateResponse createAndUpdateInstalledBase(InstalledBaseRequest request) {
        InstalledBaseCreateUpdateResponse response = new InstalledBaseCreateUpdateResponse();
        Connection connection = null;
        CallableStatement stmt = null;
        ResultSet resultSet = null;
        connection = DBConnect.getConnection();
        OracleConnection oconn = null;
        oconn = DBConnect.getConnection(connection);
        try {
            /* if (Utils.isNullOrEmpty(request.getInstanceID())) {
                response.setErrorMessage("Invalid Instance-ID");
                response.setSuccessFlag("N");
            }
            else */
            if (Utils.isNullOrEmpty(request.getUserID())) {
                response.setErrorMessage("Invalid User-ID");
                response.setSuccessFlag("N");
            }
            else if (Utils.isNullOrEmpty(request.getServiceAdvisorID())) {
                response.setErrorMessage("Invalid Service-Advisor-ID");
                response.setSuccessFlag("N");
            }
            else if (Utils.isNullOrEmpty(request.getDevicID())) {
                response.setErrorMessage("Invalid Device-ID");
                response.setSuccessFlag("N");
            }
            else if (request.getInstalledBaseList()==null) {
                response.setErrorMessage("Invalid Installed Base Object");
                response.setSuccessFlag("N");
            }
            else {
                stmt = connection.prepareCall("{call APPS.XXDMS_VR_PKG2.CREATE_UPDATE_IB(?,?,?,?,?,?,?,?)}");
                 
                stmt.setString(1, request.getInstanceID());
                stmt.setString(3, request.getUserID());
                stmt.setString(2, request.getServiceAdvisorID());
                stmt.setString(4, request.getDevicID());
                
                /* STRUCT struct = null; */
                
                if (request.getInstalledBaseList() != null && request.getInstalledBaseList().length>0) {
                    
                    @SuppressWarnings("deprecation")
                    StructDescriptor structdesc = StructDescriptor.createDescriptor("APPS.VRIBRECORD", oconn);
                    @SuppressWarnings("deprecation")
                    ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor("APPS.VRIBTABLE", oconn);
                    int listSize = request.getInstalledBaseList().length;
                    
                    Object[] colunmsArray = new Object[44]; // no of colunms
                    Object[] rowsArray = new Object[listSize]; // no of rows
                    //System.out.println("List Size is :" + listSize);
                    
                    for (int i=0; i<listSize; i++) {
                        InstalledBase IBObject = request.getInstalledBaseList()[i];
                        colunmsArray[0] = Utils.isNullOrEmpty(IBObject.getSerialNumber()) ? null : IBObject.getSerialNumber(); 
                        colunmsArray[1] = Utils.isNullOrEmpty(IBObject.getRegnNumber()) ? null : IBObject.getRegnNumber();                                               
                        colunmsArray[2] = Utils.isNullOrEmpty(IBObject.getItemCode()) ? null : IBObject.getItemCode(); 
                        colunmsArray[3] = Utils.isNullOrEmpty(IBObject.getItemDescription()) ? null : IBObject.getItemDescription(); 
                        colunmsArray[4] = Utils.isNullOrEmpty(IBObject.getLotNo()) ? null : IBObject.getLotNo();                                                                                                            
                        colunmsArray[5] = Utils.isNullOrEmpty(IBObject.getServiceInterval()) ? null : IBObject.getServiceInterval(); 
                        colunmsArray[6] = Utils.isNullOrEmpty(IBObject.getServiceIntervalCode()) ? null : IBObject.getServiceIntervalCode(); 
                        
                        colunmsArray[7] = Utils.isNullOrEmpty(IBObject.getAacSold()) ? null : IBObject.getAacSold(); 
                        colunmsArray[8] = Utils.isNullOrEmpty(IBObject.getAacSoldCode()) ? null : IBObject.getAacSoldCode(); 
                        colunmsArray[9] = Utils.isNullOrEmpty(IBObject.getSaleDate()) ? null : IBObject.getSaleDate(); 
                        colunmsArray[10] = Utils.isNullOrEmpty(IBObject.getEngineNumber()) ? null : IBObject.getEngineNumber(); 
                        colunmsArray[11] = Utils.isNullOrEmpty(IBObject.getRangeGroup()) ? null : IBObject.getRangeGroup(); 
                        colunmsArray[12] = Utils.isNullOrEmpty(IBObject.getRangeGroupCode()) ? null : IBObject.getRangeGroupCode(); 
                        colunmsArray[13] = Utils.isNullOrEmpty(IBObject.getModelGroup()) ? null : IBObject.getModelGroup(); 
                        colunmsArray[14] = Utils.isNullOrEmpty(IBObject.getModelGroupCode()) ? null : IBObject.getModelGroupCode(); 
                        colunmsArray[15] = Utils.isNullOrEmpty(IBObject.getPartyType()) ? null : IBObject.getPartyType(); 
                        colunmsArray[16] = Utils.isNullOrEmpty(IBObject.getPartyNumber()) ? null : IBObject.getPartyNumber(); 
                        colunmsArray[17] = Utils.isNullOrEmpty(IBObject.getAccountNumber()) ? null : IBObject.getAccountNumber(); 
                        colunmsArray[18] = Utils.isNullOrEmpty(IBObject.getInventoryItemID()) ? null : IBObject.getInventoryItemID(); 
                        colunmsArray[19] = Utils.isNullOrEmpty(IBObject.getInvMasterOrganizationID()) ? null : IBObject.getInvMasterOrganizationID(); 
                        colunmsArray[20] = Utils.isNullOrEmpty(IBObject.getOrganizationName()) ? null : IBObject.getOrganizationName(); 
                        colunmsArray[21] = Utils.isNullOrEmpty(IBObject.getOwnerPartyAccountID()) ? null : IBObject.getOwnerPartyAccountID(); 
                        colunmsArray[22] = Utils.isNullOrEmpty(IBObject.getOwnerPartyID()) ? null : IBObject.getOwnerPartyID(); 
                        colunmsArray[23] = Utils.isNullOrEmpty(IBObject.getLocationID()) ? null : IBObject.getLocationID(); 
                        colunmsArray[24] = Utils.isNullOrEmpty(IBObject.getSiteNumber()) ? null : IBObject.getSiteNumber(); 
                        colunmsArray[25] = Utils.isNullOrEmpty(IBObject.getAddress()) ? null : IBObject.getAddress(); 
                        colunmsArray[26] = Utils.isNullOrEmpty(IBObject.getAddressID()) ? null : IBObject.getAddressID(); 
                        colunmsArray[27] = Utils.isNullOrEmpty(IBObject.getObjectVersionNumber()) ? null : IBObject.getObjectVersionNumber(); 
                        colunmsArray[28] = Utils.isNullOrEmpty(IBObject.getAttributeCategory()) ? null : IBObject.getAttributeCategory(); 
                        colunmsArray[29] = Utils.isNullOrEmpty(IBObject.getAttribute1()) ? null : IBObject.getAttribute1(); 
                        colunmsArray[30] = Utils.isNullOrEmpty(IBObject.getAttribute2()) ? null : IBObject.getAttribute2(); 
                        colunmsArray[31] = Utils.isNullOrEmpty(IBObject.getAttribute3()) ? null : IBObject.getAttribute3(); 
                        colunmsArray[32] = Utils.isNullOrEmpty(IBObject.getAttribute4()) ? null : IBObject.getAttribute4(); 
                        colunmsArray[33] = Utils.isNullOrEmpty(IBObject.getAttribute5()) ? null : IBObject.getAttribute5(); 
                        colunmsArray[34] = Utils.isNullOrEmpty(IBObject.getAttribute6()) ? null : IBObject.getAttribute6(); 
                        colunmsArray[35] = Utils.isNullOrEmpty(IBObject.getAttribute7()) ? null : IBObject.getAttribute7(); 
                        colunmsArray[36] = Utils.isNullOrEmpty(IBObject.getAttribute8()) ? null : IBObject.getAttribute8(); 
                        colunmsArray[37] = Utils.isNullOrEmpty(IBObject.getAttribute9()) ? null : IBObject.getAttribute9(); 
                        colunmsArray[38] = Utils.isNullOrEmpty(IBObject.getAttribute10()) ? null : IBObject.getAttribute10(); 
                        colunmsArray[39] = Utils.isNullOrEmpty(IBObject.getAttribute11()) ? null : IBObject.getAttribute11(); 
                        colunmsArray[40] = Utils.isNullOrEmpty(IBObject.getAttribute12()) ? null : IBObject.getAttribute12(); 
                        colunmsArray[41] = Utils.isNullOrEmpty(IBObject.getAttribute13()) ? null : IBObject.getAttribute13(); 
                        colunmsArray[42] = Utils.isNullOrEmpty(IBObject.getAttribute14()) ? null : IBObject.getAttribute14(); 
                        colunmsArray[43] = Utils.isNullOrEmpty(IBObject.getAttribute15()) ? null : IBObject.getAttribute15(); 
                        
                        @SuppressWarnings("deprecation")
                        STRUCT oracle_record = new STRUCT(structdesc, oconn, colunmsArray);
                        rowsArray[i] = oracle_record;
                        IBObject = null;
                    }

                    @SuppressWarnings("deprecation")
                    ARRAY oracleArray = new ARRAY(arraydesc, oconn, rowsArray);
                    stmt.setObject(5, oracleArray);
                    
                }
                
                stmt.registerOutParameter(6, OracleTypes.VARCHAR);
                stmt.registerOutParameter(7, OracleTypes.VARCHAR);
                stmt.registerOutParameter(8, OracleTypes.VARCHAR);
                
                stmt.execute();
                response.setInstanceID(stmt.getString(6));
                response.setSuccessFlag(stmt.getString(7));
                response.setErrorMessage(stmt.getString(8));
                           
            }
        }
        catch (Exception ex) {
            response = new InstalledBaseCreateUpdateResponse();
            response.setErrorMessage("Unexpected error occured while creating/updating Installed Base");
            response.setSuccessFlag("N");
            response.setInstanceID("-1");
            
            ex.printStackTrace();
        }
        finally {
            //Always make sure the connection is Closed
            try{
            stmt.close();
            DBConnect.closeConnection(connection);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return response;
    }
}
