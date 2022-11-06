package ch.heig.vd;

import java.io.IOException;
import java.net.URL;

public interface ILabelDetector {
    String execute(byte[] base64, int[] params);

    String execute(URL imageUri, int[] params) throws IOException;
}
