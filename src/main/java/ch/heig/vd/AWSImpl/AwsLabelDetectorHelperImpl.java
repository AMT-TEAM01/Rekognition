package ch.heig.vd.AWSImpl;

import ch.heig.vd.ILabelDetector;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.DetectLabelsRequest;
import software.amazon.awssdk.services.rekognition.model.DetectLabelsResponse;
import software.amazon.awssdk.services.rekognition.model.Image;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AwsLabelDetectorHelperImpl implements ILabelDetector {
    RekognitionClient client;

    public AwsLabelDetectorHelperImpl() {}

    @Override
    public String execute(URL imageUri, int[] params) throws IOException {
        SdkBytes sourcesBytes = SdkBytes.fromInputStream(imageUri.openStream());

        return callReckognition(sourcesBytes, params);
    }

    private String callReckognition(SdkBytes sources, int[] params) {
        Image img = Image.builder()
                .bytes(sources)
                .build();

        DetectLabelsRequest request = DetectLabelsRequest.builder()
                .image(img)
                .maxLabels(params[0])
                .minConfidence((float) params[1])
                .build();

        DetectLabelsResponse response = client.detectLabels(request);
        return response.toString();
    }

    @Override
    public String execute(byte[] base64, int[] params) {
        return callReckognition(SdkBytes.fromByteArray(base64), params);
    }

    public void connectReckognitionClient(String profile) {
        client = RekognitionClient.builder()
                .credentialsProvider(ProfileCredentialsProvider.create(profile))
                .build();
    }
}
