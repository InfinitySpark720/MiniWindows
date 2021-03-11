package ventanaprincipal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.sql.Time;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.Timer;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VentanaEscritorio extends JPanel {

    //Objetos/atributos.
    Font fuenteLogin = new Font("Elephant", Font.BOLD, 50);
    Font usuarioContraseña = new Font("Elephant", Font.BOLD, 30);
    Font fuenteEtiquetas = new Font("Elephant", 0, 15);

    //ImageIcon fondoEscritorio = new ImageIcon(getClass().getResource("/imagenes/fondoEscritorio.jpg"));
    JLabel etiquetaFondo = new JLabel();

    JButton botonCrearUsuarios = new JButton("Crear usuario");
    JButton botonCerrarSesion = new JButton("Cerrar Sesión");

    //Botón del navegador.
    public JButton botonNavegador = new JButton();
    public JButton botonNavegadorSalir = new JButton();

    //JTree.
    public JTree arbol = new JTree();
    public JScrollPane scroll;
    public DefaultMutableTreeNode z;

    //Método para ver las carpetas del usuario logged.
    public JTextField usuarioVer = new JTextField();

    //Diseño del escritorio.
    JPanel panelLateralDerecho = new JPanel();
    JPanel panelLateralIzquierdo = new JPanel();
    JPanel panelSuperior = new JPanel();
    JPanel panelInferior = new JPanel();

    JLabel etiquetaNavegador = new JLabel("Navegador");
    JLabel etiquetaEditorTexto = new JLabel("Editor de Texto");
    JLabel etiquetaVisorImagenes = new JLabel("Visor de Imágenes");
    JLabel etiquetaConsolaComandos = new JLabel("Consola de Comandos");
    JLabel etiquetaReproductorMusical = new JLabel("Reproductor Musical");

    JButton botonEditorTexto = new JButton();
    JButton botonVisorImagenes = new JButton();
    JButton botonConsolaComandos = new JButton();
    JButton botonReproductorMusical = new JButton();

    JLabel etiquetaFecha = new JLabel();
    Calendar fechaActual;

    public VentanaEscritorio() {

        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);

        fechaActual = Calendar.getInstance();
        int dia = fechaActual.getTime().getDate();
        int mes = (fechaActual.getTime().getMonth()) + 1;
        //int año = fechaActual.getTime().getYear();
        String fecha = "Fecha: " + dia + "/" + mes + "/2021";
        etiquetaFecha.setText(fecha);
        etiquetaFecha.setForeground(Color.WHITE);
        etiquetaFecha.setBounds(55, 0, 300, 30);

        panelLateralDerecho.setBackground(Color.GRAY);
        panelLateralIzquierdo.setBackground(Color.GRAY);
        panelSuperior.setBackground(Color.BLACK);
        panelInferior.setBackground(Color.BLACK);

        panelLateralIzquierdo.setLayout(null);
        panelLateralIzquierdo.setBounds(0, 30, 200, 700);
        panelLateralIzquierdo.add(botonCerrarSesion);
        panelLateralIzquierdo.add(botonVisorImagenes);
        panelLateralIzquierdo.add(etiquetaVisorImagenes);
        panelLateralIzquierdo.add(botonReproductorMusical);
        panelLateralIzquierdo.add(etiquetaReproductorMusical);
        panelLateralIzquierdo.add(botonConsolaComandos);
        panelLateralIzquierdo.add(etiquetaConsolaComandos);
        add(panelLateralIzquierdo);

        panelLateralDerecho.setLayout(null);
        panelLateralDerecho.setBounds(1170, 30, 200, 700);
        panelLateralDerecho.add(botonNavegador);
        panelLateralDerecho.add(botonNavegadorSalir);
        panelLateralDerecho.add(botonCrearUsuarios);
        panelLateralDerecho.add(etiquetaNavegador);
        panelLateralDerecho.add(botonEditorTexto);
        panelLateralDerecho.add(etiquetaEditorTexto);
        add(panelLateralDerecho);

        panelSuperior.setLayout(null);
        panelSuperior.setBounds(0, 0, 1500, 30);
        panelSuperior.add(etiquetaFecha);
        add(panelSuperior);

        panelInferior.setBounds(0, 659, 1500, 50);
        add(panelInferior);

        botonCrearUsuarios.setBounds(10, 30, 170, 50);
        botonCrearUsuarios.setBackground(Color.WHITE);
        botonCrearUsuarios.setFont(new Font("Elephant", Font.BOLD, 15));
        botonCrearUsuarios.setForeground(Color.BLACK);
        //add(botonCrearUsuarios);

        //Botón de regresar.
        botonCerrarSesion.setFont(new Font("Elephant", Font.BOLD, 20));
        botonCerrarSesion.setForeground(Color.BLACK);
        botonCerrarSesion.setBackground(Color.RED);
        botonCerrarSesion.setBounds(0, 640, 200, 30);
        //add(botonCerrarSesion);

        //Le asignamos una imágen como ícono al botón.
        try {
            Image imagenNavegador = ImageIO.read(getClass().getResource("/imagenes/navegadorImagen.png"));
            botonNavegador.setIcon(new ImageIcon(imagenNavegador.getScaledInstance(180, 150, java.awt.Image.SCALE_SMOOTH)));
            botonNavegadorSalir.setIcon(new ImageIcon(imagenNavegador.getScaledInstance(180, 150, java.awt.Image.SCALE_SMOOTH)));
        } catch (IOException ex) {
            Logger.getLogger(VentanaEscritorio.class.getName()).log(Level.SEVERE, null, ex);
        }

        botonNavegador.setBounds(10, 100, 180, 150);
        botonNavegador.setBackground(Color.GRAY);
        botonNavegador.setFont(new Font("Elephant", Font.BOLD, 20));
        botonNavegador.setForeground(Color.BLACK);

        etiquetaNavegador.setForeground(Color.BLACK);
        etiquetaNavegador.setFont(fuenteEtiquetas);
        etiquetaNavegador.setBounds(50, 235, 100, 50);

        botonNavegadorSalir.setBounds(10, 100, 180, 150);
        botonNavegadorSalir.setBackground(Color.GRAY);
        botonNavegadorSalir.setFont(new Font("Elephant", Font.BOLD, 20));
        botonNavegadorSalir.setForeground(Color.BLACK);
        botonNavegadorSalir.setVisible(false);

        try {
            Image imagenEditorTexto = ImageIO.read(getClass().getResource("/imagenes/editorTextoImagen.png"));
            botonEditorTexto.setIcon(new ImageIcon(imagenEditorTexto.getScaledInstance(180, 150, java.awt.Image.SCALE_SMOOTH)));
        } catch (IOException ex) {
            Logger.getLogger(VentanaEscritorio.class.getName()).log(Level.SEVERE, null, ex);
        }

        botonEditorTexto.setBounds(10, 390, 180, 150);
        botonEditorTexto.setBackground(Color.GRAY);

        etiquetaEditorTexto.setBounds(48, 530, 200, 50);
        etiquetaEditorTexto.setForeground(Color.BLACK);
        etiquetaEditorTexto.setFont(fuenteEtiquetas);

        try {
            Image imagenVisorImagenes = ImageIO.read(getClass().getResource("/imagenes/visorImagenes.jpg"));
            botonVisorImagenes.setIcon(new ImageIcon(imagenVisorImagenes.getScaledInstance(180, 150, java.awt.Image.SCALE_SMOOTH)));
        } catch (IOException ex) {
            Logger.getLogger(VentanaEscritorio.class.getName()).log(Level.SEVERE, null, ex);
        }

        botonVisorImagenes.setBounds(10, 20, 180, 150);
        botonVisorImagenes.setBackground(Color.GRAY);

        etiquetaVisorImagenes.setForeground(Color.BLACK);
        etiquetaVisorImagenes.setFont(fuenteEtiquetas);
        etiquetaVisorImagenes.setBounds(30, 155, 200, 50);

        try {
            Image imagenReproductorMusical = ImageIO.read(getClass().getResource("/imagenes/reproductorMusical.jfif"));
            botonReproductorMusical.setIcon(new ImageIcon(imagenReproductorMusical.getScaledInstance(180, 150, java.awt.Image.SCALE_SMOOTH)));
        } catch (IOException ex) {
            Logger.getLogger(VentanaEscritorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        botonReproductorMusical.setBounds(10, 220, 180, 150);
        botonReproductorMusical.setBackground(Color.GRAY);

        etiquetaReproductorMusical.setBounds(20, 355, 200, 50);
        etiquetaReproductorMusical.setForeground(Color.BLACK);
        etiquetaReproductorMusical.setFont(fuenteEtiquetas);

        try {
            Image imagenConsolaComandos = ImageIO.read(getClass().getResource("/imagenes/consolaImagen.png"));
            botonConsolaComandos.setIcon(new ImageIcon(imagenConsolaComandos.getScaledInstance(180, 150, java.awt.Image.SCALE_SMOOTH)));
        } catch (IOException ex) {
            Logger.getLogger(VentanaEscritorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        botonConsolaComandos.setBounds(10, 415, 180, 150);
        botonConsolaComandos.setBackground(Color.GRAY);

        etiquetaConsolaComandos.setBounds(20, 550, 200, 50);
        etiquetaConsolaComandos.setForeground(Color.BLACK);
        etiquetaConsolaComandos.setFont(fuenteEtiquetas);

        //JPanel que rodeará al JTree para darle un mejor diseño.
        JPanel bordesTree = new JPanel();
        bordesTree.setBounds(550, 200, 350, 350);
        bordesTree.setBackground(Color.BLACK);
        //add(bordesTree);

        //horaLocal();
    }

//    JLabel clockLabel;
//    public Timer timer;
//    public final static int ONE_SECOND = 1000;
//    private final SimpleDateFormat clockFormat = new SimpleDateFormat("H:mm:ss");

//    public void horaLocal() {
//        clockLabel = new JLabel();
//        clockLabel.setForeground(Color.WHITE);
//        clockLabel.setBounds(660, 0, 300, 30);
//        panelSuperior.add(clockLabel);
//
//        show();
//    }

//    @Override
//    public void show() {
//        timer = new Timer(ONE_SECOND, new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                clockLabel.setText(clockFormat.format(new Date()));
//                clockLabel.repaint();
//            }
//        });
//        clockLabel.setText(clockFormat.format(new Date()));
//        timer.start();
//    }

}
