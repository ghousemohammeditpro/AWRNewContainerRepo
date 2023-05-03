package com.awr.autotrustproxy.util;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.json.JSONObject;

public class RestUtil {
    public RestUtil() {
        super();
    }
    private static Response response;
    private static String authorization;

    private static final MediaType MediaTypeJSON = MediaType.parse("application/json; charset=utf-8");

    public String propertyConfigAuthenticationToken() {
        try {
            // FileInputStream propertyFile = null;


            // if (System.getProperty("os.name").contains("Windows")) {
            //     propertyFile = new FileInputStream(System.getProperty("user.dir") + "\\proxyConfigAutotrust.properties");

            // } else {
            //     propertyFile = new FileInputStream(System.getProperty("user.dir") + "/proxyConfigAutotrust.properties");

            // }
            // Properties properties = new Properties();
            // properties.load(propertyFile);
            // propertyFile.close();

            // authorization = properties.getProperty("authorization");

            //added new code to get authorization from App.YAML
            authorization = System.getenv("authorization");
            System.out.println("authorization: "+authorization);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return authorization;
    }

    public static String callGetService(String url) {

        try {
            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(1260, TimeUnit.SECONDS)
                                                            .writeTimeout(1260, TimeUnit.SECONDS)
                                                            .readTimeout(1260, TimeUnit.SECONDS)
                                                            .build();

            Request request = new Request.Builder().header("auth", new RestUtil().propertyConfigAuthenticationToken())
                                                   .url(url)
                                                   .build();
            response = client.newCall(request).execute();

            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject callPostService(String url, RequestBody requestBody) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().header("auth", new RestUtil().propertyConfigAuthenticationToken())
                                               .url(url)
                                               .method("POST", RequestBody.create(null, new byte[0]))
                                               .post(requestBody)
                                               .build();

        try {
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }

    }
    
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
    public static String callGetServiceWithAppName(String url, String appName) {

        try {
            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(1260, TimeUnit.SECONDS)
                                                            .writeTimeout(1260, TimeUnit.SECONDS)
                                                            .readTimeout(1260, TimeUnit.SECONDS)
                                                            .build();

            Request request = new Request.Builder().header("auth", new RestUtil().propertyConfigAuthenticationToken())
                                                   .header("appName", appName)
                                                   .url(url)
                                                   .build();
            response = client.newCall(request).execute();

            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

        //for source parameter
        public static String callPutServiceWithSource(String url, String jsonString, String appName, String source) {
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(1260, TimeUnit.SECONDS)
                                                            .writeTimeout(1260, TimeUnit.SECONDS)
                                                            .readTimeout(1260, TimeUnit.SECONDS)
                                                            .build();
        
        RequestBody reqBody = RequestBody.create(MediaTypeJSON, jsonString);
        Request request = new Request.Builder().header("appName", appName)
                                               .header("source", source)
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
    public static String callGetServiceWithSource(String url, String appName, String source) {

        try {
            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(1260, TimeUnit.SECONDS)
                                                            .writeTimeout(1260, TimeUnit.SECONDS)
                                                            .readTimeout(1260, TimeUnit.SECONDS)
                                                            .build();

            Request request = new Request.Builder().header("auth", new RestUtil().propertyConfigAuthenticationToken())
                                                   .header("appName", appName)
                                                   .header("source", source)
                                                   .url(url)
                                                   .build();
            response = client.newCall(request).execute();

            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response);
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String callPostServiceAutotrust(String url, String jsonString, String appName) {
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(1260, TimeUnit.SECONDS)
                                                            .writeTimeout(1260, TimeUnit.SECONDS)
                                                            .readTimeout(1260, TimeUnit.SECONDS)
                                                            .build();
        
        RequestBody reqBody = RequestBody.create(MediaTypeJSON, jsonString);
        Request request = new Request.Builder().header("appName", appName)
                                               .url(url)
                                               .post(reqBody)
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
