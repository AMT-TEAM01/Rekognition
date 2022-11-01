package ch.heig.vd.AWSImpl;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;

public class AwsCloudClient {

    private static AwsCloudClient instance;

    public String profile;

    private AwsCloudClient(){
        profile = "default";
    }

    public static AwsCloudClient getInstance() {
        if (instance == null) {
            instance = new AwsCloudClient();
        }
        return instance;
    }
}
