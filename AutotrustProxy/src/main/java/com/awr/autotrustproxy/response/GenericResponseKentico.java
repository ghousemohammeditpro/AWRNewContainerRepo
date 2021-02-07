package com.awr.autotrustproxy.response;

public class GenericResponseKentico {
    public GenericResponseKentico() {
        super();
    }
    private boolean Status;
    private String Message;


    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getMessage() {
        return Message;
    }
}
