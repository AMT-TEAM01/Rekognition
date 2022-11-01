package ch.heig.vd;

import ch.heig.vd.AWSImpl.AwsDataObjectHelperImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DataObjectTest {

    AwsDataObjectHelperImpl objImpl;
    private String bucketPath = "amt.team01.diduno.education";
    private String testName = "test.jpg";

    private String imageUrl = bucketPath + "/" + testName;

    @Before
    public void init() {
        objImpl = new AwsDataObjectHelperImpl(bucketPath);
        objImpl.connectS3Client("default");
    }

    @Test
    public void testCreateObjectSuccess() {
        Assert.assertTrue(objImpl.bucketExists(bucketPath));

        objImpl.uploadObject(testName, "asd");

        Assert.assertTrue(objImpl.objectExists(bucketPath, testName));
    }
}
