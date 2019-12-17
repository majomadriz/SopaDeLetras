/**
 * Nombre del programa: Sopa de letras Descripcion: Crear programa que permita a
 * los usuarios jugar sopa de letras Autores: Maria Jose Madriz, Jaime Escobar,
 * Diego Perez Fecha de creacion: 02/10/2019 Fecha de modificacion: 03/12/2019
 * Modificado por: Maria Jose Madriz, Jaime Escobar, Diego Perez
 */
package sopaproyecto;

import java.awt.*;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.border.EmptyBorder;

public class IUSopa {

    public static JFrame frame = new JFrame("Sopa de letras");
    public static JDialog dialogoJuegoInputs = new JDialog();
    public static JDialog dialogoMensajes = new JDialog();

    private static Color color;
    public static JTextField palabraConfigurar = new JTextField(12);
    public static JTextField palabraAdivinar = new JTextField(12);
    public static JTextField filaAdivinar = new JTextField(2);
    public static JTextField columnaAdivinar = new JTextField(2);

    public static void main(String[] args) {
        RutinasSopa.inicializarJuego();
        mostrarMenu();

    }//Fin del main

    public static void mostrarDialogoMensaje(String message, String id) {
        dialogoMensajes.dispose();
        dialogoMensajes = new JDialog();
        JPanel panelMensaje = new JPanel();
        panelMensaje.setLayout(new FlowLayout());
        JLabel mensajeLabel = new JLabel(message);
        JButton button = new JButton("Aceptar");
        button.addActionListener(menuActionListener);
        button.setActionCommand(id);
        panelMensaje.add(mensajeLabel);
        panelMensaje.add(button);
        dialogoMensajes.add(panelMensaje);
        dialogoMensajes.pack();
        dialogoMensajes.setLocationRelativeTo(null);
        dialogoMensajes.setVisible(true);
    }

    //Inicio  mostrar menu
    public static void mostrarMenu() {
        dialogoMensajes.dispose();
        dialogoJuegoInputs.dispose();
        
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

    public static void configurarJuego() {

        JPanel panelConfig = new JPanel();
        panelConfig.setLayout(new BoxLayout(panelConfig, BoxLayout.Y_AXIS));

        JLabel tituloMenu = new JLabel("Agregue una palabra:");
        tituloMenu.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JPanel palabraPanel = new JPanel();
        palabraPanel.setLayout(new FlowLayout());

        palabraConfigurar.setHorizontalAlignment(JTextField.LEFT);
        palabraConfigurar.addKeyListener(keyListener);

        JButton agregarBtn = new JButton("Agregar Palabra");
        agregarBtn.addActionListener(configActionListener);
        agregarBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        agregarBtn.setActionCommand("agregar_palabra");

        palabraPanel.add(palabraConfigurar);
        palabraPanel.add(agregarBtn);

        panelConfig.add(tituloMenu);
        panelConfig.add(palabraPanel);

        frame.setSize(500, 200);
        frame.setContentPane(panelConfig);

        RutinasSopa.resetPalabraConfigIndex();
    }//Fin configurarJuego

    public static void mostrarJuegoInputs() {
        JPanel panelJuego = new JPanel();
        panelJuego.setLayout(new BoxLayout(panelJuego, BoxLayout.Y_AXIS));

        JPanel panelPalabra = new JPanel();
        panelPalabra.setLayout(new BoxLayout(panelPalabra, BoxLayout.Y_AXIS));
        panelPalabra.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel panelOrientacion = new JPanel();
        panelOrientacion.setLayout(new BoxLayout(panelOrientacion, BoxLayout.Y_AXIS));
        panelOrientacion.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel panelDireccion = new JPanel();
        panelDireccion.setLayout(new BoxLayout(panelDireccion, BoxLayout.Y_AXIS));
        panelDireccion.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel palabraAdivinarLabel = new JLabel("Ingrese la palabra:");
//        palabraAdivinar.setHorizontalAlignment(JTextField.LEFT);
        palabraAdivinar.setMaximumSize( palabraAdivinar.getPreferredSize() );
        palabraAdivinar.addKeyListener(keyListener);

        JLabel orientacionLabel = new JLabel("Seleccione la orientacion:");
        JRadioButton horizontal = new JRadioButton("Horizontal");
        horizontal.setMnemonic(KeyEvent.VK_B);
        horizontal.setActionCommand("horizontal");
        horizontal.addActionListener(jugarActionListener);
        horizontal.setSelected(true);

        JRadioButton vertical = new JRadioButton("Vertical");
        vertical.setMnemonic(KeyEvent.VK_C);
        vertical.setActionCommand("vertical");
        vertical.addActionListener(jugarActionListener);

        JRadioButton diagonalArriba = new JRadioButton("Diagonal Arriba");
        diagonalArriba.setMnemonic(KeyEvent.VK_D);
        diagonalArriba.setActionCommand("diagonal_arriba");
        diagonalArriba.addActionListener(jugarActionListener);

        JRadioButton diagonalAbajo = new JRadioButton("Diagonal Abajo");
        diagonalAbajo.setMnemonic(KeyEvent.VK_R);
        diagonalAbajo.setActionCommand("diagonal_abajo");
        diagonalAbajo.addActionListener(jugarActionListener);

        //Group the orientacion buttons.
        ButtonGroup orientacionRadio = new ButtonGroup();
        orientacionRadio.add(horizontal);
        orientacionRadio.add(vertical);
        orientacionRadio.add(diagonalArriba);
        orientacionRadio.add(diagonalAbajo);

        JLabel direccionLabel = new JLabel("Seleccione la direccion:");
        JRadioButton noInvertida = new JRadioButton("normal");
        noInvertida.setMnemonic(KeyEvent.VK_B);
        noInvertida.setActionCommand("no_invertida");
        noInvertida.addActionListener(jugarActionListener);
        noInvertida.setSelected(true);

        JRadioButton invertida = new JRadioButton("invertida");
        invertida.setMnemonic(KeyEvent.VK_C);
        invertida.setActionCommand("invertida");
        invertida.addActionListener(jugarActionListener);

        //Group the radio direccionRadio buttons.
        ButtonGroup direccionRadio = new ButtonGroup();
        direccionRadio.add(noInvertida);
        direccionRadio.add(invertida);

        JButton buscarPalabra = new JButton("Buscar Palabra");
        buscarPalabra.addActionListener(jugarActionListener);
        buscarPalabra.setActionCommand("adivinar_palabra");

        panelPalabra.add(palabraAdivinarLabel);
        panelPalabra.add(palabraAdivinar);

        panelOrientacion.add(orientacionLabel);
        panelOrientacion.add(horizontal);
        panelOrientacion.add(vertical);
        panelOrientacion.add(diagonalArriba);
        panelOrientacion.add(diagonalAbajo);

        panelDireccion.add(direccionLabel);
        panelDireccion.add(noInvertida);
        panelDireccion.add(invertida);

        panelJuego.add(panelPalabra);
        panelJuego.add(panelOrientacion);
        panelJuego.add(panelDireccion);
        panelJuego.add(buscarPalabra);
        dialogoJuegoInputs.setSize(300, 400);
        dialogoJuegoInputs.setContentPane(panelJuego);
        palabraAdivinar.requestFocus();

    }//Fin configurarJuego

    public static void updateSopa() {
        String[][] matrizJuego = RutinasSopa.getMatrizJuego();
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelSopa = new JPanel(new GridLayout(12, 12));
        panelSopa.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panelSopa.setAlignmentY(CENTER_ALIGNMENT);

        panelPrincipal.add(panelSopa, BorderLayout.CENTER);

        for (int j = 0; j < matrizJuego.length; j++) {
            for (int i = 0; i < matrizJuego[j].length; i++) {
                String letra = matrizJuego[j][i];
                String letraMay = letra.toUpperCase();
                
                JLabel letraLabel = new JLabel(letra, javax.swing.SwingConstants.CENTER);
                letraLabel.addMouseListener(mouseListener);
                letraLabel.setAlignmentX(CENTER_ALIGNMENT);
                letraLabel.setAlignmentY(CENTER_ALIGNMENT);
                letraLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                letraLabel.setName(j + "-" + i);
                if(letra.equals(letraMay)){
                    letraLabel.setBackground(Color.GREEN);
                }
                letraLabel.setOpaque(true);
                panelSopa.add(letraLabel);
            }
        }

        frame.setSize(400, 400);
        frame.setContentPane(panelPrincipal);
        frame.setVisible(true);
    }

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

    };

    public static MouseListener mouseListener = new MouseListener() {

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            label.setBackground(color);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            color = label.getBackground();
            label.setBackground(Color.YELLOW);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            int columna, fila, divisor;
            divisor = label.getName().indexOf("-");
            fila = Integer.parseInt(label.getName().substring(0, divisor));
            columna = Integer.parseInt(label.getName().substring(divisor + 1));
            RutinasSopa.setColumna(columna);
            RutinasSopa.setFila(fila);
            dialogoJuegoInputs.setVisible(true);
            palabraAdivinar.requestFocus();
        }
    };

    public static ActionListener menuActionListener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            // Aquí está accesible unaVariable
            String expression = e.getActionCommand();
            switch (expression) {
                case "configurar_juego":
                    configurarJuego();
                    break;
                case "inicializar_juego":
                    RutinasSopa.inicializarJuego();
                    mostrarDialogoMensaje("Juego Inicializado!","cerrar_mensaje");
                    break;
                case "jugar":
                    updateSopa();
                    mostrarJuegoInputs();
                    break;
                case "cerrar_mensaje":
                    dialogoMensajes.dispose();
                    break;
                case "cerrar_juego_ganado":
                    RutinasSopa.inicializarJuego();
                    dialogoMensajes.dispose();
                    mostrarMenu();
                    break;
                case "salir":
                    System.exit(0);
                    break;
                default:
                    mostrarDialogoMensaje("Hubo un error","cerrar_mensaje");
                    mostrarMenu();
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
                    String ePalabra = palabraConfigurar.getText();
                    if (ePalabra.length() > 12 || ePalabra.length() < 3) {
                        mostrarDialogoMensaje("La palabra no puede tener mas de 12 letras ni menos de 2","cerrar_mensaje");
                    } else {
                        palabraConfigurar.setText("");
                        palabraConfigurar.requestFocus();
                        if (RutinasSopa.insertarPalabraConfig(ePalabra)) {
                            mostrarMenu();
                            mostrarDialogoMensaje("Juego Configurado!","cerrar_mensaje");
                        }
                    }
                    break;
                default:
                    mostrarDialogoMensaje("Hubo un error","cerrar_mensaje");
                    mostrarMenu();
                    break;
            }
        }
    };

    public static ActionListener jugarActionListener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            // Aquí está accesible unaVariable
            String expression = e.getActionCommand();
            switch (expression) {
                case "adivinar_palabra":
                    String palabra = palabraAdivinar.getText();
                    palabraAdivinar.setText("");
                    dialogoJuegoInputs.dispose();
                    int result = RutinasSopa.jugarVerificarPalabra(palabra);

                    if (result == 0) {
                        mostrarDialogoMensaje("La palabra es incorrecta", "cerrar_mensaje");
                    }
                    if (result == -1) {
                        mostrarDialogoMensaje("La palabra ya fue agregada", "cerrar_mensaje");
                    }
                    if (result == 1) {
                        RutinasSopa.agregarPalabraCorrecta(palabra);
                        updateSopa();
                        boolean ganado = RutinasSopa.verificarJuegoGanado();
                        if (ganado) {
                            mostrarDialogoMensaje("Haz ganado la partida", "cerrar_juego_ganado");
                            break;
                        }
                        mostrarDialogoMensaje("La palabra es correcta!", "cerrar_mensaje");

                    }

                    break;
                case "horizontal":
                    RutinasSopa.setOrientacion("horizontal");
                    break;
                case "vertical":
                    RutinasSopa.setOrientacion("vertical");
                    break;
                case "diagonal_arriba":
                    RutinasSopa.setOrientacion("diagonal_arriba");
                    break;
                case "diagonal_abajo":
                    RutinasSopa.setOrientacion("diagonal_abajo");
                    break;
                case "invertida":
                    RutinasSopa.setEsInvertida(true);
                    break;
                case "no_invertida":
                    RutinasSopa.setEsInvertida(false);
                    break;
                default:
                    mostrarDialogoMensaje("Hubo un error!", "cerrar_mensaje");
                    mostrarMenu();
                    break;
            }
        }
    };

}//Fin de class
