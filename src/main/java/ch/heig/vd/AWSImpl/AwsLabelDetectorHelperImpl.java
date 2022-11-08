package ch.heig.vd.AWSImpl;

import ch.heig.vd.AWSImpl.JSON.*;
import ch.heig.vd.ILabelDetector;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.http.SdkHttpResponse;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AwsLabelDetectorHelperImpl implements ILabelDetector {
    RekognitionClient client;

    public AwsLabelDetectorHelperImpl() {}

    @Override
    public String execute(URL imageUri, int[] params) throws IOException {
        SdkBytes sourcesBytes = SdkBytes.fromInputStream(imageUri.openStream());

        return callReckognition(sourcesBytes, params);
    }

    private String callReckognition(SdkBytes sources, int[] params) throws IOException {
        Image img = Image.builder()
                .bytes(sources)
                .build();

        DetectLabelsRequest request = DetectLabelsRequest.builder()
                .image(img)
                .maxLabels(params[0])
                .minConfidence((float) params[1])
                .build();

        DetectLabelsResponse response = client.detectLabels(request);

        List<AwsLabel> labels = new ArrayList<>();
        for (Label label : response.labels()) {
            List<AwsInstance> instances = new ArrayList<>();
            for (Instance ins : label.instances()) {
                instances.add(new AwsInstance(ins.confidence(), new AwsBoundingBox(ins.boundingBox().width(), ins.boundingBox().height(), ins.boundingBox().left(), ins.boundingBox().top())));
            }

            List<AwsParent> parents = new ArrayList<>();
            for (Parent parent : label.parents()) {
                parents.add(new AwsParent(parent.name()));
            }

            labels.add(new AwsLabel(label.name(), label.confidence(), instances, parents));
        }
        AwsLabels lab = new AwsLabels(labels);
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(lab);
    }

    @Override
    public String execute(String base64, int[] params) throws IOException {
        return callReckognition(SdkBytes.fromByteArray(Base64.getDecoder().decode(base64)), params);
    }

    public void connectReckognitionClient(String profile) {
        client = RekognitionClient.builder()
                .credentialsProvider(ProfileCredentialsProvider.create(profile))
                .build();
    }
}
