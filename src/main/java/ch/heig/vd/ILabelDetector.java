package ch.heig.vd;

import java.io.IOException;
import java.net.URL;

public interface ILabelDetector {
    // TODO ajouter des explications, on comprends pas ce qu'il faut mettre dans
    // params, ici j'utiliserais la javadoc pour que ce soit disponible facilement à
    // l'utilisation et à l'implémentation
    String execute(String base64, int[] params) throws IOException;

    // TODO ajouter des explications, on comprends pas ce qu'il faut mettre dans
    // params, ici j'utiliserais la javadoc pour que ce soit disponible facilement à
    // l'utilisation et à l'implémentation
    String execute(URL imageUri, int[] params) throws IOException;
}