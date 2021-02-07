package com.awr.autotrustservice.util;

import com.awr.autotrustservice.request.UserRegStep2Request;

import com.awr.autotrustservice.request.UserRegStep3Request;

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

public class UserRegStep3Util {
    public UserRegStep3Util() {
        super();
    }
    public static String step3(UserRegStep3Request req, String source) {
        PropertiesUtil propUtil = new PropertiesUtil();
      
        String retVal = null;
        String soapEndpointUrl = propUtil.getPropertyValue("ENRICH_WSDL");
            //"http://awrbloooomdevserver.com/enrich/ws/EnrichAPI.asmx";
            
        
        String soapAction = propUtil.getPropertyValue("STEP3_ACTION");
            //"http://tempuri.org/login";
        System.out.println("soapAction is : "+soapAction);
            
                
        
        retVal = callSoapWebService(soapEndpointUrl,soapAction, req, source);
        return retVal;
    }
    
    
    
    private static String callSoapWebService(String soapEndpointUrl, String soapAction, UserRegStep3Request req, String source) {
        String retVal = null;
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
    
            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction, req, source), soapEndpointUrl);
                
                
                    SOAPBody soapBody = soapResponse.getSOAPBody();
                  
                    NodeList nodes = soapBody.getElementsByTagName("createAccountStep2Result");

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
    
    private static void createSoapEnvelope(SOAPMessage soapMessage,UserRegStep3Request req, String source) throws SOAPException {
           SOAPPart soapPart = soapMessage.getSOAPPart();
    
           String myNamespace = "tem";
           String myNamespaceURI = "http://tempuri.org/";
           
    
           // SOAP Envelope
           SOAPEnvelope envelope = soapPart.getEnvelope();
           envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
    
         
           SOAPBody soapBody = envelope.getBody();
           SOAPElement soapBodyElem = soapBody.addChildElement("createAccountStep2", myNamespace);
           SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("gender", myNamespace);
           soapBodyElem1.addTextNode(req.getGender());
           SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("nationality", myNamespace);
           soapBodyElem2.addTextNode(req.getNationality());
           SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("dob", myNamespace);
           soapBodyElem3.addTextNode(req.getDob());
           SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("city", myNamespace);
           soapBodyElem4.addTextNode(req.getCity());
           SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("area", myNamespace);
           soapBodyElem5.addTextNode(req.getArea());
           SOAPElement soapBodyElem6 = soapBodyElem.addChildElement("profession", myNamespace);
           soapBodyElem6.addTextNode(req.getProfession());
           SOAPElement soapBodyElem7 = soapBodyElem.addChildElement("make", myNamespace);
           soapBodyElem7.addTextNode(req.getMake());
           SOAPElement soapBodyElem8 = soapBodyElem.addChildElement("model", myNamespace);
           soapBodyElem8.addTextNode(req.getModel());
           SOAPElement soapBodyElem9 = soapBodyElem.addChildElement("buId", myNamespace);
           soapBodyElem9.addTextNode(req.getBuId());
           SOAPElement soapBodyElem10 = soapBodyElem.addChildElement("source", myNamespace);
           soapBodyElem10.addTextNode(source);
      
       }
    
    private static SOAPMessage createSOAPRequest(String soapAction, UserRegStep3Request req, String source) throws Exception {
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
