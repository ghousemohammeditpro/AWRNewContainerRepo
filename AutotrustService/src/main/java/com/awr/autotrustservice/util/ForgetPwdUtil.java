package com.awr.autotrustservice.util;

import com.awr.autotrustservice.request.ForgotPasswordData;

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

public class ForgetPwdUtil {
    public ForgetPwdUtil() {
        super();
    }
    //    public static void main(String[] args){
    //        ForgortPasswordData data = new ForgortPasswordData();
    //        data.setEmailMobile("+971874698145365");
    //        String response = callForgetPasword(data);
    //        System.out.println("response is : "+response);
    //    }
        
        public static String callForgetPasword(ForgotPasswordData logData, String source) {
            PropertiesUtil propUtil = new PropertiesUtil();
          
            String retVal = null;
            String soapEndpointUrl = System.getenv("ENRICH_WSDL");
                //"http://awrbloooomdevserver.com/enrich/ws/EnrichAPI.asmx";
            
            String soapAction = System.getenv("FORGOT_PWD_ACTION");
               // "http://tempuri.org/forgotPassword";
                    
            
            retVal = callSoapWebService(soapEndpointUrl,soapAction, logData, source);
            return retVal;
        }
        
        
        
        private static String callSoapWebService(String soapEndpointUrl, String soapAction, ForgotPasswordData logData, String source) {
            String retVal = null;
            try {
                // Create SOAP Connection
                SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
                SOAPConnection soapConnection = soapConnectionFactory.createConnection();
        
                // Send SOAP Message to SOAP Server
                SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction, logData, source), soapEndpointUrl);
                    
                    
                        SOAPBody soapBody = soapResponse.getSOAPBody();
                      
                        NodeList nodes = soapBody.getElementsByTagName("forgotPasswordResult");

                        // check if the node exists and get the value
                        
                        Node node = nodes.item(0);
                        retVal = node != null ? node.getTextContent() : "";
    //            System.out.println("message content is : "+retVal);
                
                // Print the SOAP Response
    //            System.out.println("Response SOAP Message:");
    //            soapResponse.writeTo(System.out);
        
                soapConnection.close();
            } catch (Exception e) {
                System.err.println(e.toString());
                e.printStackTrace();
            }
            return retVal;
        }
        
        private static void createSoapEnvelope(SOAPMessage soapMessage,ForgotPasswordData logData, String source) throws SOAPException {
               SOAPPart soapPart = soapMessage.getSOAPPart();
        
               String myNamespace = "tem";
               String myNamespaceURI = "http://tempuri.org/";
               
        
               // SOAP Envelope
               SOAPEnvelope envelope = soapPart.getEnvelope();
               envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
        
             
               SOAPBody soapBody = envelope.getBody();
               SOAPElement soapBodyElem = soapBody.addChildElement("forgotPassword", myNamespace);
               SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("emailMobile", myNamespace);
               soapBodyElem1.addTextNode(logData.getEmailMobile());
               SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("source", myNamespace);
               soapBodyElem2.addTextNode(source);

          
           }
        
        private static SOAPMessage createSOAPRequest(String soapAction, ForgotPasswordData logData, String source) throws Exception {
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = messageFactory.createMessage();
            createSoapEnvelope(soapMessage,logData, source);
            
            MimeHeaders headers = soapMessage.getMimeHeaders();
            headers.addHeader("SOAPAction", soapAction);
                    
            soapMessage.saveChanges();
            
            System.out.println("Request SOAP Message:");
            soapMessage.writeTo(System.out);
            System.out.println("\n");
        
            return soapMessage;
        }
}
