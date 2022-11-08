package ch.heig.vd;

import ch.heig.vd.AWSImpl.AwsCloudClient;

import java.io.IOException;

public class App 
{

    public static void main( String[] args ) throws IOException {
        AwsCloudClient client = AwsCloudClient.getInstance();
        client.setProfile(args[0]);
        client.connectHelpers();

        String result = client.execute(args[1], new int[]{200, 90});

        client.uploadObjectWithData("result.json", result);
    }
}
