package ch.heig.vd;

import ch.heig.vd.AWSImpl.AwsCloudClient;
import ch.heig.vd.AWSImpl.AwsDataObjectHelperImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class DataObjectTest {
    AwsCloudClient client;
    private String bucketPath = "amt.team01.diduno.education";
    private String testImage;
    private String testText;
    private String imagesFolderPath;
    private String imageTestPath;
    private String downLoadPath;
    private String relativePathImages = "/src/test/java/ch/heig/vd/images/";
    private String relativePathDownload = "/src/test/java/ch/heig/vd/download/";

    @BeforeEach
    public void init() {
        client = AwsCloudClient.getInstance();
        client.connectHelpers();

        testImage = "aws.png";
        testText = "test.txt";
        imagesFolderPath = new File("").getAbsolutePath() + relativePathImages;
        imageTestPath = imagesFolderPath + testImage;
        downLoadPath = new File("").getAbsolutePath() + relativePathDownload;
    }

    @AfterEach
    public void cleanup() {
        if (client.bucketExists(bucketPath)) {
            client.deleteObject(testImage);
            client.deleteObject(testText);
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
        actualResult = client.objectExists(notExistFile);

        //then
        assertFalse(actualResult);
    }

    @Test
    public void testGenerateURL_Success() {
        //given
        assertFalse(client.objectExists(testImage));
        client.uploadObject(testImage, imageTestPath);
        URL url;

        //when
        url = client.generateURL(testImage, 1);

        //then
        assertNotNull(url);
    }

    @Test
    public void testDeleteObject_Success() {
        //given
        assertFalse(client.objectExists(testImage));
        client.uploadObject(testImage, imageTestPath);

        //when
        client.deleteObject(testImage);

        //then
        assertFalse(client.objectExists(testImage));
    }

    @Test
    public void testBaseBucketExist_Success() {
        //given
        Boolean actualResult;

        //when
        actualResult = client.bucketExists(bucketPath);

        //then
        assertTrue(actualResult);
    }

    @Test
    public void testUploadFile_Success() {
        //given
        assertFalse(client.objectExists(testImage));
        Boolean actualResult;

        //when
        client.uploadObject(testImage, imageTestPath);

        //then
        actualResult = client.objectExists(testImage);
        assertTrue(actualResult);
    }

    @Test
    public void testUploadObjectWithData_Success() throws IOException {
        //given
        assertFalse(client.objectExists(testText));
        Boolean actualResult;

        //when
        client.uploadObjectWithData(testText, "test1");

        //then
        actualResult = client.objectExists(testText);
        assertTrue(actualResult);
        assertDoesNotThrow(() -> {
            client.downloadObject(testText, downLoadPath);
        });
        File f = new File(downLoadPath + testText);
        assertTrue(f.exists());
        Scanner reader = new Scanner(f);
        assertEquals("test1", reader.nextLine());
    }

    @Test
    public void testDownloadFile_Success() {
        //given
        assertFalse(client.objectExists(testImage));
        client.uploadObject(testImage, imageTestPath);

        //when
        assertDoesNotThrow(() -> {
            client.downloadObject(testImage, downLoadPath);
        });

        //then
        assertTrue(new File(downLoadPath + testImage).exists());
    }
}
