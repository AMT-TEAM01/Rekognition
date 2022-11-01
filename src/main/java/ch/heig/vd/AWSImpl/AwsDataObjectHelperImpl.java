package ch.heig.vd.AWSImpl;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class AwsDataObjectHelperImpl {

    private S3Client s3;

    private String bucketPath;

    public AwsDataObjectHelperImpl(String bucketPath) {
        this.bucketPath = bucketPath;
    }

    public void uploadObject(String objectName, String data) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketPath)
                .key(objectName)
                .build();

        s3.putObject(objectRequest, RequestBody.fromString(data));
    }

    public void deleteObject(String objectName) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketPath)
                .key(objectName)
                .build();

        s3.deleteObject(deleteObjectRequest);
    }

    public void connectS3Client(String profile) {
        s3 = S3Client.builder()
                .credentialsProvider(ProfileCredentialsProvider.create(profile))
                .build();
    }

    public boolean exists(String bucketUrl) {
        return s3.do
    }
}
