package ch.heig.vd.impAWS;

import ch.heig.vd.ICloudClient;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class AwsCloudClient implements ICloudClient {
    private static AwsCloudClient instance;
    private AwsDataObjectHelperImpl objImpl;
    private AwsLabelDetectorHelperImpl labelImpl;
    public String profile = "default";

    private AwsCloudClient() {
        objImpl = new AwsDataObjectHelperImpl();
        labelImpl = new AwsLabelDetectorHelperImpl();
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setBucketPath(String bucketPath) {
        objImpl.setBucketPath(bucketPath);
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

    public void uploadObject(String objectName, String data) {
        objImpl.uploadObject(objectName, data);
    }

    /**
     * Fonction qui demande le traitement de l'image
     * @param base64 String en base64 de l'image à traiter
     * @param params Le premier paramètre est le nombre d'étiquettes max, le deuxième est la precision minimum (0-100)
     * @return Liste des etiquettes trouvées
     * @throws IOException
     */
    public String execute(String base64, int[] params) throws IOException {
        return labelImpl.execute(base64, params);
    }

    /**
     * Fonction qui demande le traitement de l'image
            * @param imageUri Url de l'image à traiter
            * @param params Le premier paramètre est le nombre d'étiquettes max, le deuxième est la precision minimum (0-100)
            * @return Liste des etiquettes trouvées
     * @throws IOException
     */
    public String execute(URL imageUri, int[] params) throws IOException {
        return labelImpl.execute(imageUri, params);
    }

    public boolean objectExists(String objectName) {
        return objImpl.objectExists(objectName);
    }

    public void deleteObject(String objectName) {
        objImpl.deleteObject(objectName);
    }

    public void uploadObject(String objectName, File from) {
        objImpl.uploadObject(objectName, from);
    }

    public URL generateURL(String objectName, int expireTimeMinute) {
        return objImpl.generateURL(objectName, expireTimeMinute);
    }

    public void downloadObject(String objectName, String path) throws IOException {
        objImpl.downloadObject(objectName, path);
    }
}