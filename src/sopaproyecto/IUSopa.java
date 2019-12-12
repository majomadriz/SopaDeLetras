/**
 * Nombre del programa: Sopa de letras Descripcion: Crear programa que permita a
 * los usuarios jugar sopa de letras Autores: Maria Jose Madriz, Jaime Escobar,
 * Diego Perez Fecha de creacion: 02/10/2019 Fecha de modificacion: 03/12/2019
 * Modificado por: Maria Jose Madriz, Jaime Escobar, Diego Perez
 */
package sopaproyecto;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IUSopa {

    public static Scanner sc = new Scanner(System.in);

    public static JFrame frame = new JFrame("Sopa de letras");
    
    public static JTextField palabra = new JTextField(12);

    public static void main(String[] args) {
        mostrarMenu();

    }//Fin del main

    //Inicio  mostrar menu
    public static void mostrarMenu() {

        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));

        JPanel panelMenuOpciones = new JPanel();
        panelMenuOpciones.setLayout(new BoxLayout(panelMenuOpciones, BoxLayout.Y_AXIS));

        JLabel tituloMenu = new JLabel("MENU");
        tituloMenu.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panelMenu.add(tituloMenu);

        JButton configurar = new JButton("Configurar Juego");
        configurar.addActionListener(menuActionListener);
        configurar.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        configurar.setActionCommand("configurar_juego");

        JButton inicializar = new JButton("Inicializar Juego");
        inicializar.addActionListener(menuActionListener);
        inicializar.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        inicializar.setActionCommand("inicializar_juego");

        JButton jugar = new JButton("Jugar");
        jugar.addActionListener(menuActionListener);
        jugar.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        jugar.setActionCommand("jugar");

        JButton salir = new JButton("Salir");
        salir.addActionListener(menuActionListener);
        salir.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        salir.setActionCommand("salir");

        panelMenuOpciones.add(configurar);
        panelMenuOpciones.add(inicializar);
        panelMenuOpciones.add(jugar);
        panelMenuOpciones.add(salir);
        panelMenu.add(panelMenuOpciones);

        frame.setSize(500, 500);
        frame.setContentPane(panelMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }//Fin mostrar menu

    public static KeyListener keyListener = new KeyListener() {
        public void keyPressed(KeyEvent keyEvent) {
            // printIt("Pressed", keyEvent);
        }

        public void keyReleased(KeyEvent keyEvent) {
            // printIt("Released", keyEvent);
        }

        public void keyTyped(KeyEvent keyEvent) {
            // printIt("Typed", keyEvent);
        }

        // private void printIt(String title, KeyEvent keyEvent) {
        // int keyCode = keyEvent.getKeyCode();
        // String keyText = KeyEvent.getKeyText(keyCode);
        // System.out.println(title + " : " + keyText + " / " + keyEvent.getKeyChar());
        // }
    };

    public static ActionListener menuActionListener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            // Aquí está accesible unaVariable
            String expression = e.getActionCommand();
            switch (expression) {
                case "configurar_juego":
                    System.out.println("configurar_juego");
                    configurarJuego();
                    break;
                case "inicializar_juego":
                    System.out.println("inicializar_juego");
                    incializarJuego();
                    break;
                case "jugar":
                    System.out.println("jugar");
                    jugar();
                    break;
                case "salir":
                    System.exit(0);
                    break;
                default:
                    System.out.println("oopss");
                    break;
            }
        }
    };

    public static ActionListener configActionListener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            // Aquí está accesible unaVariable
            String expression = e.getActionCommand();
            switch (expression) {
                case "agregar_palabra":
                    System.out.println("agregar_palabra");
                    String ePalabra = palabra.getText();
                    if (ePalabra.length() > 12 || ePalabra.length() < 3) {
                        System.out.println(
                                "La palabra no puede tener mas de 12 letras ni menos de 2");
                    } else {
                        palabra.setText("");
                        palabra.requestFocus();
                        if(RutinasSopa.insertarPalabraConfig(ePalabra)){
                            mostrarMenu();
                        }
                    }
                    break;
                default:
                    System.out.println("oopss");
                    break;
            }
        }
    };

    //Inicio seleccoinar opcion
    public static int seleccionarOpcion() {
        int opcion;
        System.out.println("Seleccione una de las opciones anteriores");
        opcion = sc.nextInt();
        return opcion;

    }//Fin seleccionar opcion

    public static void configurarJuego() {

        JPanel panelConfig = new JPanel();
        panelConfig.setLayout(new BoxLayout(panelConfig, BoxLayout.Y_AXIS));

        JLabel tituloMenu = new JLabel("Agregue una palabra:");
        tituloMenu.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JPanel palabraPanel = new JPanel();
        palabraPanel.setLayout(new FlowLayout()); 
    
        palabra.setHorizontalAlignment(JTextField.LEFT);
        palabra.addKeyListener(keyListener);

        JButton agregarBtn = new JButton("Agregar Palabra");
        agregarBtn.addActionListener(configActionListener);
        agregarBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        agregarBtn.setActionCommand("agregar_palabra");
        
        palabraPanel.add(palabra);
        palabraPanel.add(agregarBtn);

        panelConfig.add(tituloMenu);
        panelConfig.add(palabraPanel);

        frame.setSize(500, 200);
        frame.setContentPane(panelConfig);
        
        RutinasSopa.resetPalabraConfigIndex();
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
            System.out.println("Digite 0 si es horizontal, 1 si es vertical, 2 "
                    + "si es diagonal hacia arriba o 3 si es diagonal hacia abajo");
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
