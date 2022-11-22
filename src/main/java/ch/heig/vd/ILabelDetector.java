package ch.heig.vd;

import java.io.IOException;
import java.net.URL;

public interface ILabelDetector {
    /**
     * Fonction qui demande le traitement de l'image
     * @param base64 String en base64 de l'image à traiter
     * @param params Le premier paramètre est le nombre d'étiquettes max, le deuxième est la precision minimum (0-100)
     * @return Liste des etiquettes trouvées
     * @throws IOException
     */
    String execute(String base64, int[] params) throws IOException;

    /**
     * Fonction qui demande le traitement de l'image
     * @param imageUri Url de l'image à traiter
     * @param params Le premier paramètre est le nombre d'étiquettes max, le deuxième est la precision minimum (0-100)
     * @return Liste des etiquettes trouvées
     * @throws IOException
     */
    String execute(URL imageUri, int[] params) throws IOException;
}