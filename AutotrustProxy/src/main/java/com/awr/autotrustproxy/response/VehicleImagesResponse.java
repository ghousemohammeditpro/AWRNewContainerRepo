package com.awr.autotrustproxy.response;

import com.awr.autotrustproxy.dto.VehicleImage;

public class VehicleImagesResponse {
    public VehicleImagesResponse() {
        super();
    }
    private VehicleImage[] images;
    private String message;
    private String status;

    public void setImages(VehicleImage[] images) {
        this.images = images;
    }

    public VehicleImage[] getImages() {
        return images;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
