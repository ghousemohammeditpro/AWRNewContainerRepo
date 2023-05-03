package com.awr.autotrustservice.response;

import com.awr.autotrustservice.dto.VehicleImage;

public class VehicleImagesResponse {
    public VehicleImagesResponse() {
        super();
    }
    private String status;
    private String message;
    private VehicleImage[] images;

    public void setImages(VehicleImage[] images) {
        this.images = images;
    }

    public VehicleImage[] getImages() {
        return images;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
