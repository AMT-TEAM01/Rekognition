package ch.heig.vd;

import ch.heig.vd.impAWS.AwsCloudClient;

import java.io.IOException;
import java.util.Base64;

public class App
{
    //TODO Review should not be hard-coded
    private static String imagePath = "/street.jpg";

    public static void main( String[] args ) throws IOException {
        AwsCloudClient client = AwsCloudClient.getInstance();
        if (args.length == 1) {
            client.setProfile(args[0]);
        } else if (args.length == 2) {
            client.setProfile(args[0]);
        }
        client.setBucketPath("amt.team01.diduno.education");
        client.connectHelpers();

        byte[] bytes = App.class.getResourceAsStream(imagePath).readAllBytes();
        String base64 = Base64.getEncoder().encodeToString(bytes);

        String result = client.execute(base64, new int[]{200, 90});

        client.uploadObject("result.json", result);

        System.out.println(result);
    }
}