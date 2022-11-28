package ch.heig.vd;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public interface ICloudClient {
    void downloadObject(String objectName, String path) throws IOException;
    URL generateURL(String objectName, int expireTimeMinute);
    void uploadObject(String objectName, File from);
    void deleteObject(String objectName);
    boolean objectExists(String objectName);
    String execute(URL imageUri, int[] params) throws IOException;
    String execute(String base64, int[] params) throws IOException;
    void uploadObject(String objectName, String data);
}