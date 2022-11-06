package ch.heig.vd.AWSImpl;

public class AwsCloudClient {

    private static AwsCloudClient instance;
    private AwsDataObjectHelperImpl objImpl;
    private AwsLabelDetectorHelperImpl labelImpl;


    private String bucketPath = "amt.team01.diduno.education";
    public String profile = "default";

    private AwsCloudClient(){
        objImpl = new AwsDataObjectHelperImpl(bucketPath);
        labelImpl = new AwsLabelDetectorHelperImpl();
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void connectHelpers() {
        objImpl.connectS3Client(profile);
    }

    public static AwsCloudClient getInstance() {
        if (instance == null) {
            instance = new AwsCloudClient();
        }
        return instance;
    }
}
