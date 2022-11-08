package ch.heig.vd;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.net.URL;

public interface ILabelDetector {
    String execute(String base64, int[] params) throws IOException;

    String execute(URL imageUri, int[] params) throws IOException;
}
