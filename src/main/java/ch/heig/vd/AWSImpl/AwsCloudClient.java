package ch.heig.vd.AWSImpl;

public class AwsCloudClient {

    private static AwsCloudClient instance;

    private AwsCloudClient(){

    }

    public static AwsCloudClient getInstance() {
        if (instance == null) {
            instance = new AwsCloudClient();
        }
        return instance;
    }
}
