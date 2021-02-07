package com.awr.autotrustservice.util;

import com.awr.autotrustservice.request.SaveAddressRequest;

import com.awr.autotrustservice.request.SaveProfileRequest;

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

public class SaveProfileUtil {
    public SaveProfileUtil() {
        super();
    }
    public static String saveProfile(SaveProfileRequest req, String source) {
        PropertiesUtil propUtil = new PropertiesUtil();
      
        String retVal = null;
        String soapEndpointUrl = propUtil.getPropertyValue("ENRICH_WSDL");
            //"http://awrbloooomdevserver.com/enrich/ws/EnrichAPI.asmx";
            
        
        String soapAction = propUtil.getPropertyValue("SAVE_PROFILE_ACTION");
            //"http://tempuri.org/login";
        System.out.println("soapAction is : "+soapAction);
            
                
        
        retVal = callSoapWebService(soapEndpointUrl,soapAction, req, source);
        return retVal;
    }
    
    
    
    private static String callSoapWebService(String soapEndpointUrl, String soapAction, SaveProfileRequest req, String source) {
        String retVal = null;
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
    
            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction, req, source), soapEndpointUrl);
                
                
                    SOAPBody soapBody = soapResponse.getSOAPBody();
                  
                    NodeList nodes = soapBody.getElementsByTagName("updateProfileResult");

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
    
    private static void createSoapEnvelope(SOAPMessage soapMessage,SaveProfileRequest req, String source) throws SOAPException {
           SOAPPart soapPart = soapMessage.getSOAPPart();
    
           String myNamespace = "tem";
           String myNamespaceURI = "http://tempuri.org/";
           
    
           // SOAP Envelope
           SOAPEnvelope envelope = soapPart.getEnvelope();
           envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
    
         
           SOAPBody soapBody = envelope.getBody();
           SOAPElement soapBodyElem = soapBody.addChildElement("updateProfile", myNamespace);
           SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("buId", myNamespace);
           soapBodyElem1.addTextNode(req.getBuId());
           SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("gender", myNamespace);
           soapBodyElem2.addTextNode(req.getGender());
           SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("nationality", myNamespace);
           soapBodyElem3.addTextNode(req.getNationality());
           SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("dob", myNamespace);
           soapBodyElem4.addTextNode(req.getDob());
           SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("city", myNamespace);
           soapBodyElem5.addTextNode(req.getCity());
           SOAPElement soapBodyElem6 = soapBodyElem.addChildElement("area", myNamespace);
           soapBodyElem6.addTextNode(req.getArea());
           SOAPElement soapBodyElem7 = soapBodyElem.addChildElement("profession", myNamespace);
           soapBodyElem7.addTextNode(req.getProfession());
           SOAPElement soapBodyElem8 = soapBodyElem.addChildElement("make", myNamespace);
           soapBodyElem8.addTextNode(req.getMake());
           SOAPElement soapBodyElem9 = soapBodyElem.addChildElement("model", myNamespace);
           soapBodyElem9.addTextNode(req.getModel());
           SOAPElement soapBodyElem10 = soapBodyElem.addChildElement("fbookId", myNamespace);
           soapBodyElem10.addTextNode(req.getFbookId());
           SOAPElement soapBodyElem11 = soapBodyElem.addChildElement("source", myNamespace);
           soapBodyElem11.addTextNode(source);
      
       }
    
    private static SOAPMessage createSOAPRequest(String soapAction, SaveProfileRequest req, String source) throws Exception {
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
