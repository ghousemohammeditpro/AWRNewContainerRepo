package com.awr.autotrustproxy.request;

import com.awr.autotrustproxy.dto.AddonObject;

public class SubmitAddonRequest {
    public SubmitAddonRequest() {
        super();
    }
    private AddonObject[] addons;

    public void setAddons(AddonObject[] addons) {
        this.addons = addons;
    }

    public AddonObject[] getAddons() {
        return addons;
    }
}
