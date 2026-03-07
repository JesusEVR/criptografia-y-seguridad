/**
 * Clase que implementa el ejercicio 1, utilizando un alfabeto de 27 letras con la ñ.
 *
 * @author Jesús Elías Vázquez Reyes
 */
public class CifradoCesar {

    /** Alfabeto de 27 letras usado para el cifrado y descifrado. */
    static final String ALFABETO = "abcdefghijklmnñopqrstuvwxyz";

    /**
     * Descifra un mensaje cifrado con el método César dado un desplazamiento.
     *
     * @param cifrado el texto cifrado a descifrar
     * @param desplazamiento el número de posiciones que se restará a cada letra
     * @return el texto descifrado
     */
    public static String descifrar(String cifrado, int desplazamiento) {
        StringBuilder descifrado = new StringBuilder();
        for (char c : cifrado.toCharArray()) {
            char lower = Character.toLowerCase(c);
            int idx = ALFABETO.indexOf(lower);
            if (idx != -1) {
                int nuevaIdx = ((idx - desplazamiento) % 27 + 27) % 27;
                char nuevaLetra = ALFABETO.charAt(nuevaIdx);
                if (Character.isUpperCase(c)) {
                    descifrado.append(Character.toUpperCase(nuevaLetra));
                } else {
                    descifrado.append(nuevaLetra);
                }
            } else {
                descifrado.append(c);
            }
        }
        return descifrado.toString();
    }

    /**
     * Prueba los 27 desplazamientos posibles (0-26) e imprime el resultado de cada uno.
     */
    public static void fuerzaBruta() {
        String cifrado = "Nc xkfc gu dgnnc";
        for (int i = 0; i < 27; i++) {
            System.out.println("Clave " + i + ": " + descifrar(cifrado, i));
        }
    }

    /**
     * Ataque basado en conocimiento adicional
     */
    public static void conocimiento() {
        String cifrado = "Zo qgweidugotwo sh jb hsqgsid";
        // La última letra cifrada es 'd' y corresponde a 'o' sin rotar
        int despl = ((ALFABETO.indexOf('d') - ALFABETO.indexOf('o')) % 27 + 27) % 27;
        System.out.println("El mensaje descifrado es: " + descifrar(cifrado, despl));
    }

    /**
     * Cuenta la frecuencia de cada letra en el texto cifrado, se asume que la más
     * frecuente corresponde a 'e', y se calcula el desplazamiento a partir de eso.
     */
    public static void frecuencia() {
        String cifrado = "Jx qzd kfhnp mjwnw f ptx ijqfx xnr ifwxj hzjryf xtgwj ytit hzfrit jwjx ñtajr";

        // Cuenta la frecuencia de cada letra del alfabeto en el texto cifrado
        int[] frecuencias = new int[27];
        for (char c : cifrado.toLowerCase().toCharArray()) {
            int idx = ALFABETO.indexOf(c);
            if (idx != -1) {
                frecuencias[idx]++;
            }
        }

        // Para encontrar la letra con mayor frecuencia
        int maxIdx = 0;
        for (int i = 1; i < 27; i++) {
            if (frecuencias[i] > frecuencias[maxIdx]) {
                maxIdx = i;
            }
        }
        char masRecurr = ALFABETO.charAt(maxIdx);

        // Calcular desplazamiento asumiendo que la letra más frecuente es 'e'
        int despl = ((ALFABETO.indexOf(masRecurr) - ALFABETO.indexOf('e')) % 27 + 27) % 27;
        System.out.println("El mensaje descifrado es: " + descifrar(cifrado, despl));
    }

    /**
     * Método main que ejecuta las tres formas
     */
    public static void main(String[] args) {
        System.out.println("\n-> Fuerza bruta \n");
        fuerzaBruta();
        System.out.println("\n-> Conocimiento adicional \n");
        conocimiento();
        System.out.println("\n-> Análisis de frecuencias \n");
        frecuencia();
    }
}