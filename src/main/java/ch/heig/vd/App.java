package ch.heig.vd;

import ch.heig.vd.AWSImpl.AwsCloudClient;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class App 
{

    private static String imageName = "street.jpg";
    private static String relativePathImages = "/src/main/java/ch/heig/vd/images/";

    public static void main( String[] args ) throws IOException {
        AwsCloudClient client = AwsCloudClient.getInstance();
        if (args.length == 1) {
            client.setProfile(args[0]);
        }
        client.connectHelpers();

        String path = new File("").getAbsolutePath() + relativePathImages;
        byte[] bytes = Files.readAllBytes(Path.of(path + imageName));
        String base64 = Base64.getEncoder().encodeToString(bytes);

        String result = client.execute(base64, new int[]{200, 90});

        client.uploadObjectWithData("result.json", result);
    }
}
