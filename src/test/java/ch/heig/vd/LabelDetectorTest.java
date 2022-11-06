package ch.heig.vd;

import ch.heig.vd.AWSImpl.AwsDataObjectHelperImpl;
import ch.heig.vd.AWSImpl.AwsLabelDetectorHelperImpl;
import com.fasterxml.jackson.databind.ser.Serializers;
import io.netty.handler.codec.base64.Base64;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Array;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LabelDetectorTest {
    AwsLabelDetectorHelperImpl labelImpl;
    AwsDataObjectHelperImpl objImpl;
    private String bucketPath = "amt.team01.diduno.education";
    private String downLoadPath;
    private String imageName = "street.jpg";
    private String relativePathDownload = "\\src\\test\\java\\ch\\heig\\vd\\download\\";

    @BeforeEach
    public void init() {
        labelImpl = new AwsLabelDetectorHelperImpl();
        labelImpl.connectReckognitionClient("default");

        objImpl = new AwsDataObjectHelperImpl(bucketPath);
        objImpl.connectS3Client("default");

        downLoadPath = new File("").getAbsolutePath() + relativePathDownload;
    }

    @Test
    public void testExecuteWithUri_Success() throws IOException {
        //given
        byte[] bytes = Files.readAllBytes(Path.of(downLoadPath + imageName));
        String response;

        //when
        response = labelImpl.execute(bytes, new int[]{200, 90});

        //then
        assertNotNull(response);
    }
}
