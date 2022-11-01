package ch.heig.vd;

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
        Region region = Region.EU_WEST_2;
        S3Client s3 = S3Client.builder().region(region).build();

        List<Bucket> after = s3.listBuckets().buckets();
        System.out.println("Your {S3} buckets are:");
        for (Bucket b : after) {
            System.out.println("* " + b.name());
        }
    }
}
