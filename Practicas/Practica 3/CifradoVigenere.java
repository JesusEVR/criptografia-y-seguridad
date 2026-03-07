/**
 * Clase para descifrar mensajes cifrados con el cifrado Vigenère.
 * Utiliza un alfabeto de 26 letras (no ñ).
 *
 * @author Jesús Elías Vázquez Reyes
 */
public class CifradoVigenere {

    /**
     * Descifra un mensaje cifrado con el cifrado Vigenere con una clave en específico.
     *
     *
     * @param msjCifrado El mensaje cifrado
     * @param clave La clave utilizada para cifrar el mensaje
     * @return El mensaje descifrado
     */
    public static String descifrar(String msjCifrado, String clave) {

        StringBuilder descifrado = new StringBuilder();
        String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int contador = 0;

        clave = clave.toUpperCase();

        for (int i = 0; i < msjCifrado.length(); i++) {
            char caracter = msjCifrado.charAt(i);

            if (alfabeto.indexOf(Character.toUpperCase(caracter)) != -1) {
                int m = alfabeto.indexOf(Character.toUpperCase(caracter));
                int k = alfabeto.indexOf(clave.charAt(contador % clave.length()));
                char original = alfabeto.charAt((m - k + 26) % 26);

                if (Character.isLowerCase(caracter)) {
                    original = Character.toLowerCase(original);
                }

                descifrado.append(original);
                contador++;
            } else {
                descifrado.append(caracter);
            }
        }

        return descifrado.toString();
    }

    /**
     * Método main, se definen el mensaje cifrado y la clave
     */
    public static void main(String[] args) {

        String msj = "laakfhwfmkdiwtmjlaepnxfzftlcqvodwmrlwvikqhfjltrjbnskggekzbnxwoeiquouqpaelltywkezfmhvttrjsgdkzkolyatywlmfcxstjxeegytywvrfoweujxsksnrrfmszlllfnxyvktlcoxrvdhobaggwgkijdhvvxkodkhmvggevdlerjnsyszlrfverlhutztdrfve";
        String clave = "star";

        String descifrado = descifrar(msj, clave);

        System.out.println("\n> Mensaje cifrado: " + msj + "\n");
        System.out.println("> Clave: " + clave + "\n");
        System.out.println("> Texto descifrado: " + descifrado + "\n");
    }
}
