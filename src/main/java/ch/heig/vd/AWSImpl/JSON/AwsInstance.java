package ch.heig.vd.AWSImpl.JSON;

import java.util.List;

public class AwsInstance {
    public float confidence;
    public AwsBoundingBox boundingBox;

    public AwsInstance(float confidence, AwsBoundingBox boundingBox) {
        this.confidence = confidence;
        this.boundingBox = boundingBox;
    }
}
