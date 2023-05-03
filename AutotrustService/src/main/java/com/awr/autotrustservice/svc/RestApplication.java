package com.awr.autotrustservice.svc;

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/AutotrustService/resources/")
public class RestApplication extends Application {
    public RestApplication() {}
}