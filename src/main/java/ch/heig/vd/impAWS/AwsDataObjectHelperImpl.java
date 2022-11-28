package ch.heig.vd.impAWS;

import ch.heig.vd.IDataObjectHelper;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.time.Duration;

public class AwsDataObjectHelperImpl implements IDataObjectHelper {
    private S3Client s3;
    private String bucketPath;
    public AwsDataObjectHelperImpl() {}

    public void setBucketPath(String bucketPath) {
        this.bucketPath = bucketPath;
    }

    public void uploadObject(String objectName, File from) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketPath)
                .key(objectName)
                .build();

        s3.putObject(objectRequest, RequestBody.fromFile(from));
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

    public void downloadObject(String objectName, String path) throws IOException {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketPath)
                .key(objectName)
                .build();

        ResponseBytes<GetObjectResponse> obj = s3.getObjectAsBytes(getObjectRequest);
        byte[] data = obj.asByteArray();
        File f = new File(path + objectName);
        OutputStream os = Files.newOutputStream(f.toPath());
        os.write(data);
    }

    @Override
    public URL generateURL(String objectName, int expireTimeMinute) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketPath)
                .key(objectName)
                .build();

        GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(expireTimeMinute))
                .getObjectRequest(getObjectRequest)
                .build();

        PresignedGetObjectRequest presignedGetObjectRequest = S3Presigner.create().presignGetObject(getObjectPresignRequest);

        return presignedGetObjectRequest.url();
    }

    public void connectS3Client(String profile) {
        s3 = S3Client.builder()
                .credentialsProvider(ProfileCredentialsProvider.create(profile))
                .build();
    }

    public boolean objectExists(String objectName) {
        HeadBucketRequest req = HeadBucketRequest.builder().bucket(objectName).build();
        try {
            if (s3.headBucket(req).sdkHttpResponse().isSuccessful()) {
                return true;
            }
        } catch (S3Exception e) {
            HeadObjectRequest request = HeadObjectRequest.builder().bucket(bucketPath).key(objectName).build();
            try {
                s3.headObject(request);
                return true;
            } catch (NoSuchKeyException ex) {
                System.out.println(ex);
            }
        }
        return false;
    }
}
