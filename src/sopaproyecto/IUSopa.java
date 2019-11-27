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

import java.util.Scanner;

public class IUSopa {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = seleccionarOpcion();
            continuar = ejecutarAccion(opcion);
        }
        System.out.println("Juego finalizado. Hasta luego!!");

    }//Fin del main

    //Inicio  mostrar menu
    public static void mostrarMenu() {
        System.out.println("Digite 1 para Configurar juego");
        System.out.println("Digite 2 para Inicializar juego");
        System.out.println("Digite 3 para Jugar");
        System.out.println("Digite 4 para Salir");
    }//Fin mostrar menu

    //Inicio seleccoinar opcion
    public static int seleccionarOpcion() {
        int opcion;
        System.out.println("Seleccione una de las opciones anteriores");
        opcion = sc.nextInt();
        return opcion;

    }//Fin seleccionar opcion

    //Inicio ejecutar accion
    public static boolean ejecutarAccion(int pOpcion) {
        boolean continuar = true;

        switch (pOpcion) {
            case 1:
                configurarJuego();
                break;

            case 2:
                incializarJuego();
                break;

            case 3:
                jugar();
                break;

            case 4:
                continuar = false;
                break;

            default:
                System.out.println("Opcion invalida");
                break;
        }
        return continuar;

    }//Fin ejecutar accion

    public static void configurarJuego() {
        String[] palabrasInit = RutinasSopa.obtenerPalabrasConfig();
        int i = 0;
        while (i < palabrasInit.length) {
            System.out.println("Ingrese una palabra");
            String palabra = sc.next();
            if (palabra.length() > 12) {
                System.out.println(
                        "La palabra no puede tener mas de 12 letras");
                continue;
            } else {
                RutinasSopa.insertarPalabraConfig(i, palabra);
                i++;
            }
        }
        System.out.println("Juego Configurado");
    }//Fin configurarJuego

    public static void incializarJuego() {
        System.out.println("Incializando Juego");
        RutinasSopa.inicializarJuego();
        System.out.println("Juego Incializado");

    }//Fin incializarJuego

    public static void jugar() {
        RutinasSopa.iniciarJuego();
        System.out.println(RutinasSopa.obtenerJuego());
        boolean jugar = true;
        while (jugar) {
            System.out.println("Digite numero de fila");
            int fila = sc.nextInt() - 1;
            System.out.println("Digite numero de columna");
            int columna = sc.nextInt() - 1;
            System.out.println("Digite 0 si es horizontal o 1 si es vertical");
            int orientacion = sc.nextInt();
            System.out.println("Digite 0 si esta normal o 1 si esta invertida");
            int direccion = sc.nextInt();
            System.out.println("Digite la palabra");
            String palabra = sc.next();
            int esCorrecta = RutinasSopa.jugarVerificarPalabra(fila, columna, orientacion, direccion, palabra);
            switch (esCorrecta) {
                case -1:
                    System.out.println("La palabra ya fue agregada");
                    break;

                case 0:
                    System.out.println("La palabra es incorrecta");
                    break;

                case 1:
                    System.out.println("La palabra es correcta");
                    RutinasSopa.agregarPalabraCorrecta(palabra);
                    jugar = !RutinasSopa.verificarJuegoGanado();
                    
                    break;

                default:
                    System.out.println("Hubo un error");
                    break;
            }
            System.out.println(RutinasSopa.obtenerJuego());
            if (!jugar) {
                System.out.println("Has ganado el juego");
            }

        }
    }//Fin jugar

}//Fin de class
