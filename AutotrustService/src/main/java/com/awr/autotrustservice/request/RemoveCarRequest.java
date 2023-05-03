package com.awr.autotrustservice.request;

public class RemoveCarRequest {
    public RemoveCarRequest() {
        super();
    }
    
    private String buId;
    private String carId;
//    private String source;

    public void setBuId(String buId) {
        this.buId = buId;
    }

    public String getBuId() {
        return buId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarId() {
        return carId;
    }

//    public void setSource(String source) {
//        this.source = source;
//    }
//
//    public String getSource() {
//        return source;
//    }
}
