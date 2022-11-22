package ch.heig.vd.impAWS.json;

public class AwsInstance {
    public float confidence;
    public AwsBoundingBox boundingBox;

    public AwsInstance(float confidence, AwsBoundingBox boundingBox) {
        this.confidence = confidence;
        this.boundingBox = boundingBox;
    }
}
