package com.awr.autotrustservice.util;

import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretVersion;
import com.google.cloud.secretmanager.v1.SecretVersionName;

public class SecretsReader {
    public SecretsReader() {
        super();
    }
    public static String getSecret(String gcpSecretId) {

        String secretData=null;
        String projectId = System.getenv("GCP_PROJECT_SEC_MAN");

    	try (SecretManagerServiceClient client = SecretManagerServiceClient.create()) {

			SecretVersionName name = SecretVersionName.of(projectId, gcpSecretId, "latest");
			SecretVersion secretVersion = client.getSecretVersion(name);

			AccessSecretVersionResponse response = client.accessSecretVersion(secretVersion.getName());
  
			// WARNING: Do not print the secret in a production environment - this
			// snippet is showing how to access the secret material.
			secretData = response.getPayload().getData().toStringUtf8();
            
	  	}
		catch(Exception e){ 
			System.out.println("Exception in SecretsReader:"+e.getMessage());
            e.printStackTrace();
	  	}
        return secretData;
    }
}
