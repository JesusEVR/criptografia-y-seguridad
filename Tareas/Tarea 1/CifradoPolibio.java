/**
 * Clase que implementa el Cuadrado de Polibio del ejercicio 2.
 *
 * Se trata de una tabla de 5x5 donde cada letra del alfabeto
 * se representa con un par de coordenadas (fila, columna).
 *
 * @author Jesús Elías Vázquez Reyes
 */
public class CifradoPolibio {

    /**
     * Cuadrado de Polibio de 5x5 con el alfabeto sin las letras Ñ y Z.
     */
    static final char[][] POLIBIO = {
        {'a', 'b', 'c', 'd', 'e'},
        {'f', 'g', 'h', 'i', 'j'},
        {'k', 'l', 'm', 'n', 'o'},
        {'p', 'q', 'r', 's', 't'},
        {'u', 'v', 'w', 'x', 'y'}
    };

    /**
     * Descifra un mensaje numérico usando el Cuadrado de Polibio.
     *
     * Cada par de números representa las coordenadas de una letra en la tabla
     *
     * @param mensaje arreglo de enteros donde cada par representa la fila y columna de una letra en el cuadrado
     * @return el texto descifrado como cadena
     */
    public static String descifrar(int[] mensaje) {
        StringBuilder descifrado = new StringBuilder();
        for (int i = 0; i + 1 < mensaje.length; i += 2) {
            int fila = mensaje[i] - 1;
            int col  = mensaje[i + 1] - 1;
            descifrado.append(POLIBIO[fila][col]);
        }
        return descifrado.toString();
    }

    /**
     * Cifra un mensaje de texto usando el Cuadrado de Polibio.
     *
     * Cada letra del mensaje se busca en la tabla y se reemplaza por sus coordenadas (fila, columna)
     *
     * @param mensaje el texto plano a cifrar
     * @return el texto cifrado como una cadena de pares numéricos separados por espacios
     */
    public static String cifrar(String mensaje) {
        StringBuilder cifrado = new StringBuilder();
        for (char c : mensaje.toCharArray()) {
            boolean encontrar = false;
            for (int i = 0; i < 5 && !encontrar; i++) {
                for (int j = 0; j < 5 && !encontrar; j++) {
                    if (POLIBIO[i][j] == c) {
                        cifrado.append(i + 1).append(j + 1).append(' ');
                        encontrar = true;
                    }
                }
            }
            // Si el caracter no está en la tabla, se copia tal cual (z)
            if (!encontrar) {
                cifrado.append(c);
            }
        }
        return cifrado.toString();
    }

    /**
     * Ejecuta el descifrado del mensaje numérico de a).
     */
    public static void cifradoP() {
        int[] mensaje = {1,5, 3,2, 4,5, 2,4, 1,5, 3,3, 4,1, 3,5, 3,4, 3,5, 1,5, 4,4, 4,1, 1,5, 4,3, 1,1, 1,1, 3,4, 1,1, 1,4, 2,4, 1,5};
        System.out.println("El mensaje descifrado es: " + descifrar(mensaje));
    }

    /**
     * Ejecuta el cifrado del mensaje de texto de b).
     */
    public static void descifradoP() {
        String mensaje = "si la felicidad tuviera una forma tendria forma de cristal porque puede estar a tu alrededor sin que la notes pero si cambias de perspectiva puede reflejar una luz capaz de iluminarlo todo";
        System.out.println(" El mensaje cifrado es: " + cifrar(mensaje));
    }

    /**
     * Método main
     */
    public static void main(String[] args) {
        System.out.println("\n -> Inciso a\n");
        cifradoP();
        System.out.println("\n -> Inciso b\n");
        descifradoP();
    }
}