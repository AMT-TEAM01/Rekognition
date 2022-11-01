package ch.heig.vd;

import ch.heig.vd.AWSImpl.AwsDataObjectHelperImpl;
import org.junit.Before;
import org.junit.Test;

public class DataObjectTest {

    AwsDataObjectHelperImpl objImpl;
    private String bucketPath = "amt.team01.diduno.education";

    @Before
    public void init() {
        objImpl = new AwsDataObjectHelperImpl(bucketPath);
        objImpl.connectS3Client("default");
    }

    @Test
    public void testCreateObjectSuccess() {
        objImpl.
    }
}
