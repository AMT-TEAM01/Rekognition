package ch.heig.vd;

import ch.heig.vd.impAWS.AwsCloudClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LabelDetectorTest {
    private AwsCloudClient client;
    // TODO (pas pénalisé) ça devrait être une variable d'environnement pour que je
    // puisse l'adapter au bucket que j'ai personnellement à disposition.
    // Actuellement ça m'enpêche de run les tests et donc de compiler
    private String bucketPath = "amt.team01.diduno.education";
    private String imageTestPath;
    private String imageName = "street.jpg";
    private String relativePathImages = "/src/test/java/ch/heig/vd/images/";

    @BeforeEach
    public void init() {
        client = AwsCloudClient.getInstance();
        client.setBucketPath("amt.team01.diduno.education");
        client.connectHelpers();

        imageTestPath = new File("").getAbsolutePath() + relativePathImages;
    }

    @AfterEach
    public void cleanup() {
        if (client.objectExists(bucketPath)) {
            client.deleteObject(imageName);
        }
    }

    // TODO (pas pénalisé) seulement 2 tests c'est un poil léger

    @Test
    public void execute_ExecuteWithBase64File_Success() throws IOException {
        // given
        byte[] bytes = Files.readAllBytes(Path.of(imageTestPath + imageName));
        String base64 = Base64.getEncoder().encodeToString(bytes);
        String response;

        // when
        response = client.execute(base64, new int[] { 200, 90 });

        // then
        assertNotNull(response);
    }

    @Test
    public void execute_ExecuteWithURI_Success() throws IOException {
        // given
        assertFalse(client.objectExists(imageName));
        client.uploadObject(imageName, new File(imageTestPath + imageName));
        URL url = client.generateURL(imageName, 1);
        String response;

        // when
        response = client.execute(url, new int[] { 200, 90 });

        // then
        assertNotNull(response);
    }
}