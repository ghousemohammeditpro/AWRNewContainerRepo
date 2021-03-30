package com.awr.autotrustservice.util;

import com.awr.autotrustservice.request.UserRegStep1Request;

import com.awr.autotrustservice.request.UserRegStep2Request;

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

public class UserRegStep2Util {
    public UserRegStep2Util() {
        super();
    }
    public static String step2(UserRegStep2Request req, String source) {
        PropertiesUtil propUtil = new PropertiesUtil();
      
        String retVal = null;
        String soapEndpointUrl = System.getenv("ENRICH_WSDL");
            //"http://awrbloooomdevserver.com/enrich/ws/EnrichAPI.asmx";
            
        
        String soapAction = System.getenv("STEP2_ACTION");
            //"http://tempuri.org/login";
        System.out.println("soapAction is : "+soapAction);
            
                
        
        retVal = callSoapWebService(soapEndpointUrl,soapAction, req, source);
        return retVal;
    }
    
    
    
    private static String callSoapWebService(String soapEndpointUrl, String soapAction, UserRegStep2Request req, String source) {
        String retVal = null;
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
    
            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction, req, source), soapEndpointUrl);
                
                
                    SOAPBody soapBody = soapResponse.getSOAPBody();
                  
                    NodeList nodes = soapBody.getElementsByTagName("loginStep1Result");

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
    
    private static void createSoapEnvelope(SOAPMessage soapMessage,UserRegStep2Request req, String source) throws SOAPException {
           SOAPPart soapPart = soapMessage.getSOAPPart();
    
           String myNamespace = "tem";
           String myNamespaceURI = "http://tempuri.org/";
           
    
           // SOAP Envelope
           SOAPEnvelope envelope = soapPart.getEnvelope();
           envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
    
         
           SOAPBody soapBody = envelope.getBody();
           SOAPElement soapBodyElem = soapBody.addChildElement("loginStep1", myNamespace);
           SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("buId", myNamespace);
           soapBodyElem1.addTextNode(req.getBuId());
           SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("firstName", myNamespace);
           soapBodyElem2.addTextNode(req.getFirstName());
           SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("lastName", myNamespace);
           soapBodyElem3.addTextNode(req.getLastName());
           SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("Email", myNamespace);
           soapBodyElem4.addTextNode(req.getEmail());
           SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("Mobile", myNamespace);
           soapBodyElem5.addTextNode(req.getMobile());
           SOAPElement soapBodyElem6 = soapBodyElem.addChildElement("Code", myNamespace);
           soapBodyElem6.addTextNode(req.getCode());
           SOAPElement soapBodyElem7 = soapBodyElem.addChildElement("ReceiveOffers", myNamespace);
           soapBodyElem7.addTextNode(req.getReceiveOffers());
           SOAPElement soapBodyElem8 = soapBodyElem.addChildElement("FacebookId", myNamespace);
           soapBodyElem8.addTextNode(req.getFacebookId());
           SOAPElement soapBodyElem9 = soapBodyElem.addChildElement("Password", myNamespace);
           soapBodyElem9.addTextNode(req.getPassword());
           SOAPElement soapBodyElem10 = soapBodyElem.addChildElement("source", myNamespace);
           soapBodyElem10.addTextNode(source);
      
       }
    
    private static SOAPMessage createSOAPRequest(String soapAction, UserRegStep2Request req, String source) throws Exception {
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
