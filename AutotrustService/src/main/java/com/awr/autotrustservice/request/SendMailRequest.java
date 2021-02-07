package com.awr.autotrustservice.request;

public class SendMailRequest {
    public SendMailRequest() {
        super();
    }
    private String senderEmail;
    private String recipients;
    private String subject;
    private String body;
    private String fileName;


    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
