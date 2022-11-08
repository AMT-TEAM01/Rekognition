package ch.heig.vd;

import java.io.IOException;
import java.net.URL;

public interface ICloudClient {
    void downloadObject(String objectName, String path) throws IOException;
    URL generateURL(String objectName, int expireTimeMinute);
    void uploadObject(String objectName, String from);
    boolean bucketExists(String bucketUrl);
    void deleteObject(String objectName);
    boolean objectExists(String objectName);
    String execute(URL imageUri, int[] params) throws IOException;
    String execute(byte[] base64, int[] params) throws IOException;
    void uploadObjectWithData(String objectName, String data);
}
