package ch.heig.vd.impAWS.json;

import java.util.List;

public class AwsLabel {
    public String name;
    public float confidence;
    public List<AwsInstance> instances;
    public List<AwsParent> parents;

    public AwsLabel(String name, float confidence, List<AwsInstance> instances, List<AwsParent> parents) {
        this.name = name;
        this.confidence = confidence;
        this.instances = instances;
        this.parents = parents;
    }
}
