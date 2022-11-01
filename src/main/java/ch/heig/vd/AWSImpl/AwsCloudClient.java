package ch.heig.vd.AWSImpl;

public class AwsCloudClient {

    private static AwsCloudClient instance;
    private AwsDataObjectHelperImpl objImpl;
    private AwsLabelDetectorHelperImpl labelImpl;

    public String profile = "default";

    private AwsCloudClient(){
        objImpl = new AwsDataObjectHelperImpl();
        labelImpl = new AwsLabelDetectorHelperImpl();
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public static AwsCloudClient getInstance() {
        if (instance == null) {
            instance = new AwsCloudClient();
        }
        return instance;
    }
}
