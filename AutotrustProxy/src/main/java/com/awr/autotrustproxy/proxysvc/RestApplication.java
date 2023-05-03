package com.awr.autotrustproxy.proxysvc;

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/AutotrustProxy/resources/")
public class RestApplication extends Application {
    public RestApplication() {}
}