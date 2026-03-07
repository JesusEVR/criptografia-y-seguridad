import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que implementa el Test de Kasiski.
 *
 * Este método permite estimar la longitud de la clave usada en el cifrado Vigenere.
 * 
 * @author Jesús Elías Vázquez Reyes
 */
public class CifradoVig {

    /** Mensaje cifrado a analizar. */
    static final String MENSAJE = "ECISCRVSWVLGDDWUEFHFNGESXUVTICOKQOTAJPHWAKFBNA" +
                                "EUONOJFHONCPHRZNSCOKEWLSUFPFEEUWOMHPQFAEEDOLDB" +
                                "QROKFZLNQBSXVMFZZNMQQSACESDDVMONHBROUEBGMOCVI" +
                                "SLZAOXDGTJDAQVZLDRTOVAKDDWOKJTFEJBBFNHBGLCRJRLS" +
                                "KVEVUDBXOPVDVZADBGSLCPOKUWSSJCRQWCOLFOKUC";

    /**
     * Calcula el máximo común divisor de dos números enteros.
     *
     * @param a primer número
     * @param b segundo número
     * @return el MCD de a y b
     */
    static int mcd(int a, int b) {
        return b == 0 ? a : mcd(b, a % b);
    }

    /**
     * Calcula el máximo común divisor de una lista de números enteros.
     *
     * @param numeros lista de enteros
     * @return el MCD de todos los elementos de la lista
     */
    static int mcdLista(List<Integer> numeros) {
        int resultado = numeros.get(0);
        for (int i = 1; i < numeros.size(); i++) {
            resultado = mcd(resultado, numeros.get(i));
        }
        return resultado;
    }

    /**
     * Aplica Test de Kasiski al mensaje cifrado para estimar la longitud de la clave.
     */
    public static void kasiski() {
        // Buscamos trigramas repetidos y registro sus posiciones
        Map<String, List<Integer>> trigramas = new HashMap<>();
        for (int i = 0; i <= MENSAJE.length() - 3; i++) {
            String tri = MENSAJE.substring(i, i + 3);
            trigramas.computeIfAbsent(tri, k -> new ArrayList<>()).add(i);
        }

        // Calculo distancias entre apariciones repetidas del mismo trigrama
        List<Integer> distancias = new ArrayList<>();
        for (List<Integer> posiciones : trigramas.values()) {
            if (posiciones.size() > 1) {
                for (int i = 0; i < posiciones.size() - 1; i++) {
                    distancias.add(posiciones.get(i + 1) - posiciones.get(i));
                }
            }
        }

        System.out.println("\nLista de las distancias: " + distancias);

        int mcdTotal = mcdLista(distancias);
        System.out.println("Máximo común divisor: " + mcdTotal);

        if (mcdTotal == 1) {
            System.out.println("La longitud de la clave es 1\n");
        } else {
            List<Integer> posibles = new ArrayList<>();
            for (int d = 2; d <= mcdTotal; d++) {
                if (mcdTotal % d == 0) {
                    posibles.add(d);
                }
            }
            System.out.println("Las longitudes probables para la lave: " + posibles + "\n");
        }
    }

    /**
     * Método main
     */
    public static void main(String[] args) {
        kasiski();
    }
}
