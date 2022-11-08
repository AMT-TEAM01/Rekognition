package ch.heig.vd;

import ch.heig.vd.AWSImpl.AwsCloudClient;

//TODO REVIEW Remove unused import statement
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Scanner;

public class App
{
    //TODO Review should not be hard-coded
    private static String imagePath = "/street.jpg";

    public static void main( String[] args ) throws IOException {
        AwsCloudClient client = AwsCloudClient.getInstance();
        if (args.length == 1) {
            client.setProfile(args[0]);
        }
        client.connectHelpers();

        byte[] bytes = App.class.getResourceAsStream(imagePath).readAllBytes();
        String base64 = Base64.getEncoder().encodeToString(bytes);

        String result = client.execute(base64, new int[]{200, 90});

        client.uploadObjectWithData("result.json", result);

        System.out.println(result);
    }
}