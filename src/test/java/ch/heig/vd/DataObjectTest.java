package ch.heig.vd;

import ch.heig.vd.AWSImpl.AwsDataObjectHelperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class DataObjectTest {
    AwsDataObjectHelperImpl objImpl;
    private String bucketPath = "amt.team01.diduno.education";
    private String testImage;
    private String imagesFolderPath;
    private String imageTestPath;
    private String downLoadPath;
    private String relativePathImages = "/src/test/java/ch/heig/vd/images/";
    private String relativePathDownload = "/src/test/java/ch/heig/vd/download/";

    @BeforeEach
    public void init() {
        objImpl = new AwsDataObjectHelperImpl(bucketPath);
        objImpl.connectS3Client("default");

        testImage = "aws.png";
        imagesFolderPath = new File("").getAbsolutePath() + relativePathImages;
        imageTestPath = imagesFolderPath + testImage;
        downLoadPath = new File("").getAbsolutePath() + relativePathDownload;
    }

    @AfterEach
    public void cleanup() {
        if (objImpl.bucketExists(bucketPath)) {
            objImpl.deleteObject(testImage);
        }

        File[] files = new File(downLoadPath).listFiles();
        if (files != null) {
            for (File f : files) {
                f.delete();
            }
        }
    }

    @Test
    public void testCheckObjectNotExit_Success() {
        //given
        String notExistFile = "fileNotExist.jpg";
        Boolean actualResult;

        //when
        actualResult = objImpl.objectExists(notExistFile);

        //then
        assertFalse(actualResult);
    }

    @Test
    public void testGenerateURL_Success() {
        //given
        assertFalse(objImpl.objectExists(testImage));
        objImpl.uploadObject(testImage, imageTestPath);
        URL url;

        //when
        url = objImpl.generateURL(testImage, 1);

        assertNotNull(url);
    }

    @Test
    public void testDeleteObject_Success() {
        //given
        assertFalse(objImpl.objectExists(testImage));
        objImpl.uploadObject(testImage, imageTestPath);

        //when
        objImpl.deleteObject(testImage);

        //then
        assertFalse(objImpl.objectExists(testImage));
    }

    @Test
    public void testBaseBucketExist_Success() {
        //given
        Boolean actualResult;

        //when
        actualResult = objImpl.bucketExists(bucketPath);

        //then
        assertTrue(actualResult);
    }

    @Test
    public void testUploadFile_Success() {
        //given
        assertFalse(objImpl.objectExists(testImage));
        objImpl.uploadObject(testImage, imageTestPath);
        Boolean actualResult;

        //when
        actualResult = objImpl.objectExists(testImage);

        //then
        assertTrue(actualResult);
    }

    @Test
    public void testDownloadFile_Success() {
        //given
        assertFalse(objImpl.objectExists(testImage));
        objImpl.uploadObject(testImage, imageTestPath);

        //when
        assertDoesNotThrow(() -> {
            objImpl.downloadObject(testImage, downLoadPath);
        });

        //then
        assertTrue(new File(downLoadPath + testImage).exists());
    }
}
