package ch.heig.vd;

import java.io.IOException;

public interface IDataObjectHelper {
    void uploadObject(String objectName, String from);
    void deleteObject(String objectName);
    void downloadObject(String objectName, String path) throws IOException;
}
