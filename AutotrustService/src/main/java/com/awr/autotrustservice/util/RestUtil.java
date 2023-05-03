package com.awr.autotrustservice.util;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestUtil {
    public RestUtil() {
        super();
    }
    private static Response response;
    private static String authorization;

    private static final MediaType MediaTypeJSON = MediaType.parse("application/json; charset=utf-8");


    public static String callPutServiceWithAppName(String url, String jsonString, String appName) {
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(1260, TimeUnit.SECONDS)
                                                            .writeTimeout(1260, TimeUnit.SECONDS)
                                                            .readTimeout(1260, TimeUnit.SECONDS)
                                                            .build();
        
        RequestBody reqBody = RequestBody.create(MediaTypeJSON, jsonString);
        Request request = new Request.Builder().header("appName", appName)
                                               .url(url)
                                               .put(reqBody)
                                               .build();

        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
   
    
    public static void closeConnection() {
        response.body().close();
    }
}
