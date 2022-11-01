package ch.heig.vd;

import ch.heig.vd.AWSImpl.AwsDataObjectHelperImpl;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AwsDataObjectHelperImpl obj = new AwsDataObjectHelperImpl();
    }
}
