package ch.heig.vd.AWSImpl;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;

public class AwsCloudClient {

    private static AwsCloudClient instance;

    public AwsBasicCredentials awsCreds;

    private AwsCloudClient(){
        awsCreds = AwsBasicCredentials.create(Credentials.id, Credentials.key);
    }

    public static AwsCloudClient getInstance() {
        if (instance == null) {
            instance = new AwsCloudClient();
        }
        return instance;
    }
}
