package ch.heig.vd.AWSImpl;

import ch.heig.vd.ICloudClient;

import java.io.IOException;
import java.net.URL;

public class AwsCloudClient implements ICloudClient {

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
        labelImpl.connectReckognitionClient(profile);
    }

    public static AwsCloudClient getInstance() {
        if (instance == null) {
            instance = new AwsCloudClient();
        }
        return instance;
    }

    public void uploadObjectWithData(String objectName, String data) {
        objImpl.uploadObjectWithData(objectName, data);
    }

    public String execute(byte[] base64, int[] params) throws IOException {
        return labelImpl.execute(base64, params);
    }

    public String execute(URL imageUri, int[] params) throws IOException {
        return labelImpl.execute(imageUri, params);
    }

    public boolean objectExists(String objectName) {
        return objImpl.objectExists(objectName);
    }

    public void deleteObject(String objectName) {
        objImpl.deleteObject(objectName);
    }

    public boolean bucketExists(String bucketUrl) {
        return objImpl.bucketExists(bucketUrl);
    }

    public void uploadObject(String objectName, String from) {
        objImpl.uploadObject(objectName, from);
    }

    public URL generateURL(String objectName, int expireTimeMinute) {
        return objImpl.generateURL(objectName, expireTimeMinute);
    }

    public void downloadObject(String objectName, String path) throws IOException {
        objImpl.downloadObject(objectName, path);
    }
}
