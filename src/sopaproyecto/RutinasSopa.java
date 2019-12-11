/**
 * Nombre del programa: Sopa de letras
 * Descripcion: Crear programa que permita a
 * los usuarios jugar sopa de letras
 * Autores: Maria Jose Madriz, Jaime Escobar
 * Fecha de creacion: 02/10/2019
 * Fecha de modificacion: 04/10/2019
 * Modificado por: Maria Jose Madriz, Jaime Escobar
 */
package sopaproyecto;

public class RutinasSopa {

    private static final int LARGO_JUEGO = 12;
    private static final int LARGO_PALABRAS_JUEGO = 8;
    private static final int PALABRAS_JUEGO_MAX = 20;
    private static int palabrasEncontradasCont = 0;
    private static char[] alphabet = "abcdefghijklmnñopqrstuvwxyz".toCharArray();
    private static String[] palabrasConfig = {"agua", "bueno", "cielo", "dedo",
        "escalera", "gato", "invierno", "jamon", "kiosco", "mano", "pato",
        "oreja", "palo", "queso", "sol", "tarde", "uno", "verano", "xilófono",
        "zapato"};

    private static int[] palabrasInitCheck = new int[PALABRAS_JUEGO_MAX];
    private static String[] palabrasSelec = new String[LARGO_PALABRAS_JUEGO];
    private static String[][] matrizJuego = new String[LARGO_JUEGO][LARGO_JUEGO];
    private static String[] palabrasEncontradas = new String[PALABRAS_JUEGO_MAX];

    public static String[] obtenerPalabrasConfig() {
        return palabrasConfig;
    }// fin obtenerPalabrasConfig

    public static void insertarPalabraConfig(int pIndex, String pPalabra) {
        palabrasConfig[pIndex] = pPalabra;
    }// fin insertarPalabraConfig

    public static void inicializarJuego() {
        matrizJuego = new String[LARGO_JUEGO][LARGO_JUEGO];
        seleccionarPalabras();
        limpiarMatriz();
        for (int i = 0; i < palabrasSelec.length; i++) {
            insertarPalabra(palabrasSelec[i], numeroRandom(0, 1),
                    numeroRandom(0, 3));
        }
        System.out.println(RutinasSopa.verMatriz());
        llenarMatrizRandom();
    }// fin inicializarJuego

    private static void limpiarMatriz() {
        for (int i = 0; i < matrizJuego.length; i++) {
            for (int h = 0; h < matrizJuego[i].length; h++) {
                matrizJuego[i][h] = " ";
            }
        }
    }//fin limpiarMatriz

    static void seleccionarPalabras() {
        int i = 0;
        while (i < palabrasSelec.length) {
            int k = numeroRandom(0, palabrasConfig.length - 1);
            if (palabrasInitCheck[k] != 1) {
                palabrasSelec[i] = palabrasConfig[k];
                palabrasInitCheck[k] = 1;
                i++;
            }
        }
        palabrasInitCheck = new int[palabrasConfig.length];
    }//fin seleccionarPalabras

    static void insertarPalabra(String pPalabra, int pDireccion, int pOrientacion) {
        String palabra = pPalabra;

        if (pDireccion == 1) {
            palabra = volverPalabra(pPalabra);
        }

        switch (pOrientacion) {
            case 0:
                verificarInsertarHorizontal(palabra);
                break;
            case 1:
                verificarInsertarVertical(palabra);
                break;
            case 2:
                verificarInsertarDiagonalArriba(palabra);
                break;
            case 3:
                verificarInsertarDiagonalAbajo(palabra);
                break;
            default:
                verificarInsertarHorizontal(palabra);
                break;
        }
    }// fin insertarPalabra

    private static void verificarInsertarHorizontal(String pPalabra) {

        int fila = numeroRandom(0, LARGO_JUEGO - 1);
        int columna = numeroRandom(0, LARGO_JUEGO - pPalabra.length());
        while (!verificarEspacioHorizontal(pPalabra, fila, columna)) {
            fila = numeroRandom(0, LARGO_JUEGO - 1);
            columna = numeroRandom(0, LARGO_JUEGO - pPalabra.length());
        }
        insertarHorizontal(pPalabra, fila, columna);

    }

    private static void verificarInsertarDiagonalArriba(String pPalabra) {

        int fila = numeroRandom(LARGO_JUEGO - pPalabra.length(), LARGO_JUEGO - 1);
        int columna = numeroRandom(0, LARGO_JUEGO - pPalabra.length());
        while (!verificarEspacioDiagonalArriba(pPalabra, fila, columna)) {
            fila = numeroRandom(LARGO_JUEGO - pPalabra.length(), LARGO_JUEGO - 1);
            columna = numeroRandom(0, LARGO_JUEGO - pPalabra.length());
        }
        insertarDiagonalArriba(pPalabra, fila, columna);

    }

    private static void verificarInsertarDiagonalAbajo(String pPalabra) {

        int fila = numeroRandom(0, LARGO_JUEGO - pPalabra.length());
        int columna = numeroRandom(0, LARGO_JUEGO - pPalabra.length());
        while (!verificarEspacioDiagonalAbajo(pPalabra, fila, columna)) {
            fila = numeroRandom(0, LARGO_JUEGO - pPalabra.length());
            columna = numeroRandom(0, LARGO_JUEGO - pPalabra.length());
        }
        insertarDiagonalAbajo(pPalabra, fila, columna);

    }

    private static void verificarInsertarVertical(String pPalabra) {
        int fila = numeroRandom(0, LARGO_JUEGO - pPalabra.length());
        int columna = numeroRandom(0, LARGO_JUEGO - 1);
        while (!verificarEspacioVertical(pPalabra, fila, columna)) {
            fila = numeroRandom(0, LARGO_JUEGO - pPalabra.length());
            columna = numeroRandom(0, LARGO_JUEGO - 1);
        }
        insertarVertical(pPalabra, fila, columna);
    }

    private static boolean verificarEspacioHorizontal(String pPalabra, int pFila,
            int pColumna) {
        int ocupados = 0;
        boolean colocable = true;
        for (int i = 0; i < pPalabra.length(); i++) {
            String campoMatriz = matrizJuego[pFila][pColumna + i];
            if (!isNullOrEmpty(campoMatriz.trim())) {
                if (!campoMatriz.equals(
                        String.valueOf(pPalabra.charAt(i)))) {
                    colocable = false;
                    break;
                }
                ocupados++;
            }
        }

        return colocable && ocupados <= 1;
    }//fin verificarEspacioHorizontal

    private static boolean verificarEspacioDiagonalArriba(String pPalabra, int pFila,
            int pColumna) {
        int ocupados = 0;
        boolean colocable = true;
        for (int i = 0; i < pPalabra.length(); i++) {
            String campoMatriz = matrizJuego[pFila - i][pColumna + i];
            if (!isNullOrEmpty(campoMatriz.trim())) {
                if (!campoMatriz.equals(
                        String.valueOf(pPalabra.charAt(i)))) {
                    colocable = false;
                    break;
                }
                ocupados++;
            }
        }

        return colocable && ocupados <= 1;
    }//fin verificarEspacioHorizontal

    private static boolean verificarEspacioDiagonalAbajo(String pPalabra, int pFila,
            int pColumna) {
        int ocupados = 0;
        boolean colocable = true;
        for (int i = 0; i < pPalabra.length(); i++) {
            String campoMatriz = matrizJuego[pFila + i][pColumna + i];
            if (!isNullOrEmpty(campoMatriz.trim())) {
                if (!campoMatriz.equals(
                        String.valueOf(pPalabra.charAt(i)))) {
                    colocable = false;
                    break;
                }
                ocupados++;
            }
        }

        return colocable && ocupados <= 1;
    }//fin verificarEspacioHorizontal

    private static boolean verificarEspacioVertical(String pPalabra, int pFila,
            int pColumna) {
        int ocupados = 0;
        boolean colocable = true;
        for (int i = 0; i < pPalabra.length(); i++) {
            String campoMatriz = matrizJuego[pFila + i][pColumna];
            if (!isNullOrEmpty(campoMatriz.trim())) {
                if (!campoMatriz.equals(
                        String.valueOf(pPalabra.charAt(i)))) {
                    colocable = false;
                    break;
                }
                ocupados++;
            }
        }

        return colocable && ocupados <= 1;
    }//fin verificarEspacioVertical

    private static void insertarHorizontal(String pPalabra, int pFila,
            int pColumna) {
        for (int i = 0; i < pPalabra.length(); i++) {
            matrizJuego[pFila][pColumna + i] = String.valueOf(
                    pPalabra.charAt(i));
        }
    } //fin insertarHorizontal

    private static void insertarDiagonalArriba(String pPalabra, int pFila,
            int pColumna) {
        for (int i = 0; i < pPalabra.length(); i++) {
            matrizJuego[pFila - i][pColumna + i] = String.valueOf(
                    pPalabra.charAt(i));
        }
    } //fin insertarHorizontal

    private static void insertarDiagonalAbajo(String pPalabra, int pFila,
            int pColumna) {
        for (int i = 0; i < pPalabra.length(); i++) {
            matrizJuego[pFila + i][pColumna + i] = String.valueOf(
                    pPalabra.charAt(i));
        }
    } //fin insertarHorizontal

    private static void insertarVertical(String pPalabra, int pFila,
            int pColumna) {
        for (int i = 0; i < pPalabra.length(); i++) {
            matrizJuego[pFila + i][pColumna] = String.valueOf(
                    pPalabra.charAt(i));
        }
    }//fin insertarVertical

    private static void llenarMatrizRandom() {
        for (String[] arrayJuego : matrizJuego) {
            for (int h = 0; h < arrayJuego.length; h++) {
                int index = numeroRandom(0, alphabet.length - 1);
                String letter = String.valueOf(alphabet[index]);
                if (isNullOrEmpty(arrayJuego[h].trim())) {
                    arrayJuego[h] = letter;
                }
            }
        }
    }//fin llenarMatrizRandom

    static void iniciarJuego() {
        palabrasEncontradas = new String[PALABRAS_JUEGO_MAX];
        palabrasEncontradasCont = 0;
    }// fin iniciarJuego

    static int jugarVerificarPalabra(int fila, int columna,
            int pOrientacion, int pDireccion, String pPalabra) {
        String palabra = pPalabra;
        int palabraUsada = buscarEnArreglo(pPalabra);
        if (palabraUsada != -1) {
            return -1;
        }

        if (pDireccion == 1) {
            palabra = volverPalabra(pPalabra);
        }

        int resultado = 0;
        switch (pOrientacion) {
            case 0:
                if (jugarVerificarEspacioHorizontal(palabra, fila, columna)) {
                    modificarPalabraHorizontal(palabra, fila, columna);
                    resultado = 1;
                    break;
                }
                resultado = 0;
                break;
            case 1:
                if (jugarVerificarEspacioVertical(palabra, fila, columna)) {
                    modificarPalabraVertical(palabra, fila, columna);
                    resultado = 1;
                    break;
                }
                resultado = 0;
                break;
            case 2:
               if (jugarVerificarEspacioDiagonalArriba(palabra, fila, columna)) {
                    modificarPalabraDiagonalArriba(palabra, fila, columna);
                    resultado = 1;
                    break;
                }
                resultado = 0;
                break;
            case 3:
                if (jugarVerificarEspacioDiagonalAbajo(palabra, fila, columna)) {
                    modificarPalabraDiagonalAbajo(palabra, fila, columna);
                    resultado = 1;
                    break;
                }
                resultado = 0;
                break;
            default:
                if (jugarVerificarEspacioHorizontal(palabra, fila, columna)) {
                    modificarPalabraHorizontal(palabra, fila, columna);
                    resultado = 1;
                    break;
                }
                resultado = 0;
                break;
        }
        return resultado;
    } //fin jugarVerificarPalabra

    private static void modificarPalabraHorizontal(String pPalabra, int pFila,
            int pColumna) {
        for (int i = 0; i < pPalabra.length(); i++) {
            matrizJuego[pFila][pColumna + i] = String.valueOf(
                    pPalabra.charAt(i)).toUpperCase();
        }
    } //fin modificarPalabraHorizontal
    
    private static void modificarPalabraDiagonalArriba(String pPalabra, int pFila,
            int pColumna) {
        for (int i = 0; i < pPalabra.length(); i++) {
            matrizJuego[pFila - i][pColumna + i] = String.valueOf(
                    pPalabra.charAt(i)).toUpperCase();
        }
    } //fin modificarPalabraHorizontal
    
    private static void modificarPalabraDiagonalAbajo(String pPalabra, int pFila,
            int pColumna) {
        for (int i = 0; i < pPalabra.length(); i++) {
            matrizJuego[pFila + i][pColumna + i] = String.valueOf(
                    pPalabra.charAt(i)).toUpperCase();
        }
    } //fin modificarPalabraHorizontal

    private static void modificarPalabraVertical(String pPalabra, int pFila,
            int pColumna) {
        for (int i = 0; i < pPalabra.length(); i++) {
            matrizJuego[pFila + i][pColumna] = String.valueOf(
                    pPalabra.charAt(i)).toUpperCase();
        }
    }//fin modificarPalabraVertical

    private static boolean jugarVerificarEspacioHorizontal(String pPalabra, int pFila,
            int pColumna) {
        boolean esCorrecta = true;
        for (int i = 0; i < pPalabra.length(); i++) {
            if (!matrizJuego[pFila][pColumna + i].equals(
                    String.valueOf(pPalabra.charAt(i)))) {
                esCorrecta = false;
                break;
            }
        }

        return esCorrecta;
    }//fin jugarVerificarEspacioHorizontal
    
    private static boolean jugarVerificarEspacioDiagonalArriba(String pPalabra, int pFila,
            int pColumna) {
        boolean esCorrecta = true;
        for (int i = 0; i < pPalabra.length(); i++) {
            if (!matrizJuego[pFila - i][pColumna + i].equals(
                    String.valueOf(pPalabra.charAt(i)))) {
                esCorrecta = false;
                break;
            }
        }

        return esCorrecta;
    }//fin jugarVerificarEspacioHorizontal
    
    private static boolean jugarVerificarEspacioDiagonalAbajo(String pPalabra, int pFila,
            int pColumna) {
        boolean esCorrecta = true;
        for (int i = 0; i < pPalabra.length(); i++) {
            String letraMatriz = matrizJuego[pFila + i][pColumna + i];
            String letraPalabra =  String.valueOf(pPalabra.charAt(i));
            if (!letraMatriz.equals(letraPalabra)) {
                esCorrecta = false;
                break;
            }
        }

        return esCorrecta;
    }//fin jugarVerificarEspacioHorizontal

    private static boolean jugarVerificarEspacioVertical(String pPalabra, int pFila,
            int pColumna) {
        boolean esCorrecta = true;
        for (int i = 0; i < pPalabra.length(); i++) {
            if (!matrizJuego[pFila + i][pColumna].equals(
                    String.valueOf(pPalabra.charAt(i)))) {
                esCorrecta = false;
                break;
            }
        }

        return esCorrecta;
    }//fin jugarVerificarEspacioVertical

    static boolean verificarJuegoGanado() {
        return palabrasEncontradasCont == 8;
    }

    static void agregarPalabraCorrecta(String pPalabra) {
        palabrasEncontradas[palabrasEncontradasCont] = pPalabra;
        palabrasEncontradasCont++;
    }

    /*
     * Herramientas generales
     */
    static String verArray(String[] arr) {
        String resultado = "[";
        for (int i = 0; i < arr.length; i++) {
            resultado += String.valueOf(arr[i]);
            if (i != arr.length - 1) {
                resultado += " , ";
            }
        }
        resultado += "] \n";
        return resultado;
    }// fin verArray String

    static String verArray(int[] arr) {
        String resultado = "[";
        for (int i = 0; i < arr.length; i++) {
            resultado += String.valueOf(arr[i]);
            if (i != arr.length - 1) {
                resultado += " , ";
            }
        }
        resultado += "] \n";
        return resultado;
    } // fin verArray int

    //Inicio busqueda
    public static int buscarEnArreglo(String pPalabra) {
        for (int i = 0; i < palabrasEncontradas.length; i++) {
            if (!isNullOrEmpty(palabrasEncontradas[i]) && palabrasEncontradas[i].equals(pPalabra)) {
                return i;
            }
        }
        // Si llegamos aqui, entonces el elemento no estaba presente 
        return -1;

    }//Fin de busqueda

    static String verMatriz() {
        String arrayMayor = "";
        for (int j = 0; j < matrizJuego.length; j++) {
            String arrayMenor = "[";
            for (int i = 0; i < matrizJuego[j].length; i++) {
                arrayMenor += String.valueOf(matrizJuego[j][i]);
                if (i != matrizJuego[j].length - 1) {
                    arrayMenor += " , ";
                }
            }
            arrayMayor += arrayMenor + "] \n";
        }
        return arrayMayor;
    }// fin verMatriz

    static String obtenerJuego() {
        String arrayMayor = "    1   2   3   4   5   6   7   8   9  10  11  12 \n";
        for (int j = 0; j < matrizJuego.length; j++) {
            String arrayMenor = (j + 1) + " ";
            if (j < 9) {
                arrayMenor += "  ";
            } else {
                arrayMenor += " ";
            }
            for (int i = 0; i < matrizJuego[j].length; i++) {
                arrayMenor += String.valueOf(matrizJuego[j][i]);
                if (i != matrizJuego[j].length - 1) {
                    arrayMenor += " , ";
                }
            }
            arrayMayor += arrayMenor + " \n";
        }
        return arrayMayor;
    }// fin obtenerJuego

    static int numeroRandom(int min, int max) {
        int range = (max - min) + 1;
        int result = (int) (Math.random() * range) + min;
        return result;
    }// fin numeroRandom

    public static boolean isNullOrEmpty(String str) {
        if (str != null && !str.isEmpty()) {
            return false;
        }
        return true;
    }// fin isNullOrEmpty

    public static String volverPalabra(String str) {
        String reverse = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reverse = reverse + str.charAt(i);
        }
        return reverse;
    }// fin volverPalabra

}
