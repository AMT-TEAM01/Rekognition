package ch.heig.vd.AWSImpl;

import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;

import java.util.List;

public class AwsDataObjectHelperImpl {

    private S3Client s3;
    private AwsCloudClient client;

    public AwsDataObjectHelperImpl() {
        client = AwsCloudClient.getInstance();
        s3 = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(client.awsCreds))
                .region(Region.of(Credentials.region))
                .build();

        List<Bucket> after = s3.listBuckets().buckets();
        System.out.println("Your {S3} buckets are:");
        for (Bucket b : after) {
            System.out.println("* " + b.name());
        }
    }
}
