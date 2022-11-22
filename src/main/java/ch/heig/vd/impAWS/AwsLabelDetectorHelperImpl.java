package ch.heig.vd.impAWS;

import ch.heig.vd.impAWS.json.*;
import ch.heig.vd.ILabelDetector;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AwsLabelDetectorHelperImpl implements ILabelDetector {
    RekognitionClient client;

    public AwsLabelDetectorHelperImpl() {
    }

    // params contient 2 paramètres : Le premier paramètre est le nombre d'étiquettes max,
    // le deuxième est la precision minimum (0-100)
    @Override
    public String execute(URL imageUri, int[] params) throws IOException {
        SdkBytes sourcesBytes = SdkBytes.fromInputStream(imageUri.openStream());

        return callReckognition(sourcesBytes, params);
    }

    // params contient 2 paramètres : Le premier paramètre est le nombre d'étiquettes max,
    // le deuxième est la precision minimum (0-100)
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
                instances.add(new AwsInstance(ins.confidence(), new AwsBoundingBox(ins.boundingBox().width(),
                        ins.boundingBox().height(), ins.boundingBox().left(), ins.boundingBox().top())));
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

    // params contient 2 paramètres : Le premier paramètre est le nombre d'étiquettes max,
    // le deuxième est la precision minimum (0-100)
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