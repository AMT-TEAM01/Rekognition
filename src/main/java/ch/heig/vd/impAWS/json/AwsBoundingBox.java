package ch.heig.vd.impAWS.json;

public class AwsBoundingBox {
    public float width;
    public float height;
    public float left;
    public float top;

    public AwsBoundingBox(float width, float height, float left, float top) {
        this.width = width;
        this.height = height;
        this.left = left;
        this.top = top;
    }
}
