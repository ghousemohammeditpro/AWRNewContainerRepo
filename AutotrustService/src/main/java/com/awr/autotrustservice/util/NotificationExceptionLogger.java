package com.awr.autotrustservice.util;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

public class NotificationExceptionLogger {
    public NotificationExceptionLogger() {
        super();
    }
    public void callAudit(LogData logData) {
      
        
        PropertiesUtil propUtil= new PropertiesUtil();
        String soapEndpointUrl = propUtil.getPropertyValue("soapUrlException");
      
    
        callSoapWebService(soapEndpointUrl,logData);
    }
    
    
    
    private static void callSoapWebService(String soapEndpointUrl,LogData logData) {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
    
            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(logData), soapEndpointUrl);
    
            // Print the SOAP Response
    //            AuditLogger.printMessage("Response SOAP Message:");
    //            soapResponse.writeTo(System.out);
    //            AuditLogger.printMessage();
    
            soapConnection.close();
        } catch (Exception e) {
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }
    
    private static void createSoapEnvelope(SOAPMessage soapMessage,LogData logData) throws SOAPException {
           SOAPPart soapPart = soapMessage.getSOAPPart();
    
           String ser = "ser";
           String myNamespaceURI = "http://service.model.exception.awr.com/";
          
         
    
           // SOAP Envelope
           SOAPEnvelope envelope = soapPart.getEnvelope();
           envelope.addNamespaceDeclaration(ser, myNamespaceURI);
    
         
           SOAPBody soapBody = envelope.getBody();
           SOAPElement soapBodyElem = soapBody.addChildElement("insertIntoExceptionHandler", ser);
         //  soapBodyElem.addTextNode("New York");
           SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("exceptionBean");
           SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("appCode");
            soapBodyElem2.addTextNode(logData.getAppCode());
           SOAPElement soapBodyElem15 = soapBodyElem1.addChildElement("appName");
            soapBodyElem15.addTextNode(logData.getAppName());
           SOAPElement soapBodyElem3 = soapBodyElem1.addChildElement("arrtibuteCategory");
            soapBodyElem3.addTextNode("");
           SOAPElement soapBodyElem4 = soapBodyElem1.addChildElement("attribute1");
            soapBodyElem4.addTextNode(logData.getAttribute1());
           SOAPElement soapBodyElem5 = soapBodyElem1.addChildElement("attribute2");
            soapBodyElem5.addTextNode(logData.getUserName());
           SOAPElement soapBodyElem6 = soapBodyElem1.addChildElement("attribute3");
            soapBodyElem6.addTextNode("");
           SOAPElement soapBodyElem7 = soapBodyElem1.addChildElement("attribute4");
            soapBodyElem7.addTextNode("");
           SOAPElement soapBodyElem8 = soapBodyElem1.addChildElement("attribute5");
            soapBodyElem8.addTextNode("");
           SOAPElement soapBodyElem9 = soapBodyElem1.addChildElement("createdBy");
            soapBodyElem9.addTextNode("");
           SOAPElement soapBodyElem10 = soapBodyElem1.addChildElement("documentCode");
            soapBodyElem10.addTextNode(logData.getDocCode());/*  */
            SOAPElement soapBodyElem11 = soapBodyElem1.addChildElement("documentId");
             soapBodyElem11.addTextNode(logData.getDocId());
           SOAPElement soapBodyElem12 = soapBodyElem1.addChildElement("exceptionTrace");
            soapBodyElem12.addTextNode(logData.getErrorMessage());
           SOAPElement soapBodyElem13 = soapBodyElem1.addChildElement("inputParams");
            soapBodyElem13.addTextNode("");
      
       }
    
    private static SOAPMessage createSOAPRequest(LogData logData) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        createSoapEnvelope(soapMessage,logData);
        soapMessage.saveChanges();
        soapMessage.writeTo(System.out);
    
        return soapMessage;
    }
}
