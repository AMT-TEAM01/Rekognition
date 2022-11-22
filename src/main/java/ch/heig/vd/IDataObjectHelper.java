package ch.heig.vd;

import java.io.IOException;
import java.net.URL;

public interface IDataObjectHelper {
    void uploadObject(String objectName, String from);
    void deleteObject(String objectName);
    void downloadObject(String objectName, String path) throws IOException;
    URL generateURL(String objectName, int expireTimeMinute);
    void uploadObjectWithData(String objectName, String data);

    //TODO REVIEW Overload uploadObject only one method to uploadAnObject
}
