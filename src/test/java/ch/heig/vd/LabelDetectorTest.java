package ch.heig.vd;

import ch.heig.vd.AWSImpl.AwsCloudClient;
import ch.heig.vd.AWSImpl.AwsDataObjectHelperImpl;
import ch.heig.vd.AWSImpl.AwsLabelDetectorHelperImpl;
import com.fasterxml.jackson.databind.ser.Serializers;
import io.netty.handler.codec.base64.Base64;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Array;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LabelDetectorTest {
    AwsCloudClient client;
    private String bucketPath = "amt.team01.diduno.education";
    private String imageTestPath;
    private String imageName = "street.jpg";
    private String imageJson = "street.json";
    private String defaultJson = "response.json";
    private String relativePathImages = "/src/test/java/ch/heig/vd/images/";

    @BeforeEach
    public void init() {
        client = AwsCloudClient.getInstance();
        client.connectHelpers();

        imageTestPath = new File("").getAbsolutePath() + relativePathImages;
    }

    @AfterEach
    public void cleanup() {
        if (client.bucketExists(bucketPath)) {
            client.deleteObject(imageJson);
            client.deleteObject(imageName);
            client.deleteObject(defaultJson);
        }
    }

    @Test
    public void testExecuteWithBase64_Success() throws IOException {
        //given
        byte[] bytes = Files.readAllBytes(Path.of(imageTestPath + imageName));
        String response;

        //when
        response = client.execute(bytes, new int[]{200, 90});

        //then
        assertNotNull(response);
    }

    @Test
    public void testExecuteWithURL_Success() throws IOException {
        //given
        assertFalse(client.objectExists(imageName));
        client.uploadObject(imageName, imageTestPath + imageName);
        URL url = client.generateURL(imageName, 1);
        String response;

        //when
        response = client.execute(url, new int[]{200, 90});

        //then
        assertNotNull(response);
    }
}
