package ch.heig.vd;

import ch.heig.vd.AWSImpl.AwsCloudClient;

public class App 
{
    public static void main( String[] args )
    {
        AwsCloudClient client = AwsCloudClient.getInstance();
        if (args.length == 1) {
            client.setProfile(args[0]);
        }
    }
}
