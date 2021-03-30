package com.awr.autotrustservice.util;

import com.awr.autotrustservice.request.SaveAccountInfoRequest;
import com.awr.autotrustservice.request.UpdatePasswordRequest;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SaveAccountInfoUtil {
    public SaveAccountInfoUtil() {
        super();
    }
    public static String saveAccountInfo(SaveAccountInfoRequest req, String source) {
        PropertiesUtil propUtil = new PropertiesUtil();
      
        String retVal = null;
        String soapEndpointUrl = System.getenv("ENRICH_WSDL");
            //"http://awrbloooomdevserver.com/enrich/ws/EnrichAPI.asmx";
            
        
        String soapAction = System.getenv("SAVE_ACCOUNT_ACTION");
            //"http://tempuri.org/login";
        System.out.println("soapAction is : "+soapAction);
            
                
        
        retVal = callSoapWebService(soapEndpointUrl,soapAction, req, source);
        return retVal;
    }
    
    
    
    private static String callSoapWebService(String soapEndpointUrl, String soapAction, SaveAccountInfoRequest req, String source) {
        String retVal = null;
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
    
            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction, req, source), soapEndpointUrl);
                
                
                    SOAPBody soapBody = soapResponse.getSOAPBody();
                  
                    NodeList nodes = soapBody.getElementsByTagName("saveAccountInfoResult");

                    // check if the node exists and get the value
                    
                    Node node = nodes.item(0);
                    retVal = node != null ? node.getTextContent() : "";

            soapConnection.close();
        } catch (Exception e) {
            System.err.println(e.toString());
            e.printStackTrace();
        }
        return retVal;
    }
    
    private static void createSoapEnvelope(SOAPMessage soapMessage,SaveAccountInfoRequest req, String source) throws SOAPException {
           SOAPPart soapPart = soapMessage.getSOAPPart();
    
           String myNamespace = "tem";
           String myNamespaceURI = "http://tempuri.org/";
           
    
           // SOAP Envelope
           SOAPEnvelope envelope = soapPart.getEnvelope();
           envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
    
         
           SOAPBody soapBody = envelope.getBody();
           SOAPElement soapBodyElem = soapBody.addChildElement("saveAccountInfo", myNamespace);
           SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("buId", myNamespace);
//           System.out.println("buId:"+req.getBuId());
           soapBodyElem1.addTextNode(req.getBuId());
           SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("fName", myNamespace);
//           System.out.println("fname:"+req.getFname());
           soapBodyElem2.addTextNode(req.getFname());
           SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("lName", myNamespace);
//           System.out.println("lname:"+req.getLname());
           soapBodyElem3.addTextNode(req.getLname());
           SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("email", myNamespace);
//           System.out.println("email:"+req.getEmail());
           soapBodyElem4.addTextNode(req.getEmail());
           SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("mobile", myNamespace);
           soapBodyElem5.addTextNode(req.getMobile());
           SOAPElement soapBodyElem6 = soapBodyElem.addChildElement("source", myNamespace);
           soapBodyElem6.addTextNode(source);
      
       }
    
    private static SOAPMessage createSOAPRequest(String soapAction, SaveAccountInfoRequest req, String source) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        createSoapEnvelope(soapMessage,req, source);
        
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);
                
        soapMessage.saveChanges();
        
        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println("\n");
    
        return soapMessage;
    }
}
