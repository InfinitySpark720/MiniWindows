package ventanaprincipal;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.IOException;
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
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

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

    JButton botonEditorTextoSalir = new JButton();
    JButton botonVisorImagenesSalir = new JButton();
    JButton botonConsolaComandosSalir = new JButton();
    JButton botonReproductorMusicalSalir = new JButton();

    JLabel etiquetaFecha = new JLabel();
    Calendar fechaActual;

    public JButton pestañaNavegador = new JButton("Navegador");
    public JButton pestañaEditorTexto = new JButton("Editor de Texto");
    public JButton pestañaVisorImagenes = new JButton("Visor de Imágenes");
    public JButton pestañaConsolaComandos = new JButton("Consola de Comandos");
    public JButton pestañaReproductorMusical = new JButton("Reproductor Musical");

    //Panel del editor de texto.
    public JPanel panelEditorTexto = new JPanel();
    public JPanel panelHerramientas = new JPanel();
    public JButton botonGuardar = new JButton();

    public JPanel panelGuardarTexto = new JPanel();
    public JTextField nombreArchivoTexto = new JTextField();
    public JLabel ingreseNombre = new JLabel("Nombre que desea para el archivo:");
    public JButton guardarConfirmar = new JButton("GUARDAR");
    public JButton cancelarGuardar = new JButton("CANCELAR");

    public JTextArea areaEditor = new JTextArea();
    JScrollPane scrollEditor;

    JLabel etiquetaFuente = new JLabel("Fuente");
    String tipoFuentes[];
    public JComboBox comboFuenteEditor;

    JLabel etiquetaTamañoLetra = new JLabel("Tamaño de letra");
    String numeroLetra[] = {"8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "30", "40", "46", "50", "60", "72"};
    public JComboBox comboTamañoLetra = new JComboBox(numeroLetra);

    JLabel etiquetaColorLetra = new JLabel("Color de la letra");
    String colorLetra[] = {"Negro", "Gris Claro", "Gris", "Rojo", "Verde", "Azul", "Amarillo", "Celeste", "Magenta", "Blanco", "Gris Oscuro", "Anaranjado", "Rosado"};
    public JComboBox comboColorLetra = new JComboBox(colorLetra);

    JLabel etiquetaFondoColor = new JLabel("Color de Fondo");
    String colorFondo[] = {"Blanco", "Gris Claro", "Gris", "Rojo", "Verde", "Azul", "Amarillo", "Celeste", "Magenta", "Gris Oscuro", "Negro", "Anaranjado", "Rosado"};
    public JComboBox comboColorFondo = new JComboBox(colorFondo);

    JLabel etiquetaEstilo = new JLabel("Estilo");
    String estilo[] = {"Normal", "Negrita", "Itálica"};
    public JComboBox comboEstilo = new JComboBox(estilo);

    //Consola de comandos.
    public JPanel panelConsola = new JPanel();
    public JTextArea espacioConsola = new JTextArea();
    public JButton enter = new JButton("Listo");
    public JButton listo = new JButton("Siguiente");
    public JTextArea registro = new JTextArea();

    //Visor de imágenes.
    public JPanel panelVisor = new JPanel();
    public JLabel espacioImagen = new JLabel();
    public JButton botonAtras = new JButton();
    public JButton botonAdelante = new JButton();

    //Reproductor musical.
    public JPanel panelReproductor = new JPanel();
    public JButton botonAdelante1 = new JButton();
    public JButton botonAtras1 = new JButton();
    public JButton botonPlay = new JButton();
    public JButton botonPausa = new JButton();
    public JTextField cancionActual = new JTextField();
    public JLabel etiquetaCancion = new JLabel("Canción Actual");

    //JInternalFrame interno = new JInternalFrame();
    public VentanaEscritorio() {

        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);

        //Editor ComboBox de las fuentes disponibles.
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        tipoFuentes = environment.getAvailableFontFamilyNames();
        comboFuenteEditor = new JComboBox(tipoFuentes);
        comboFuenteEditor.setBounds(30, 50, 150, 30);
        panelHerramientas.add(comboFuenteEditor);

        etiquetaFuente.setBounds(80, 20, 150, 30);
        etiquetaFuente.setForeground(Color.BLACK);
        panelHerramientas.add(etiquetaFuente);

        comboTamañoLetra.setBounds(220, 50, 45, 30);
        panelHerramientas.add(comboTamañoLetra);

        etiquetaTamañoLetra.setBounds(205, 20, 150, 30);
        etiquetaTamañoLetra.setForeground(Color.BLACK);
        panelHerramientas.add(etiquetaTamañoLetra);

        comboColorLetra.setBounds(325, 50, 150, 30);
        panelHerramientas.add(comboColorLetra);

        etiquetaColorLetra.setBounds(345, 20, 150, 30);
        etiquetaColorLetra.setForeground(Color.BLACK);
        panelHerramientas.add(etiquetaColorLetra);

        comboColorFondo.setBounds(505, 50, 130, 30);
        panelHerramientas.add(comboColorFondo);

        etiquetaFondoColor.setBounds(525, 20, 150, 30);
        etiquetaFondoColor.setForeground(Color.BLACK);
        panelHerramientas.add(etiquetaFondoColor);

        comboEstilo.setBounds(660, 50, 100, 30);
        panelHerramientas.add(comboEstilo);

        etiquetaEstilo.setBounds(680, 20, 150, 30);
        etiquetaEstilo.setForeground(Color.BLACK);
        panelHerramientas.add(etiquetaEstilo);

        botonGuardar.setBounds(10, 18, 25, 25);
        panelHerramientas.add(botonGuardar);

        try {
            Image imagenGuardar = ImageIO.read(getClass().getResource("/imagenes/imagenGuardar.png"));
            botonGuardar.setIcon(new ImageIcon(imagenGuardar.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
        } catch (IOException ex) {
            Logger.getLogger(VentanaEscritorio.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Pestañas
        pestañaNavegador.setBounds(200, 0, 200, 50);
        pestañaNavegador.setForeground(Color.WHITE);
        pestañaNavegador.setBackground(Color.BLACK);
        pestañaNavegador.setBorderPainted(false);
        panelInferior.add(pestañaNavegador);

        pestañaEditorTexto.setBounds(400, 0, 200, 50);
        pestañaEditorTexto.setForeground(Color.WHITE);
        pestañaEditorTexto.setBackground(Color.BLACK);
        pestañaEditorTexto.setBorderPainted(false);
        panelInferior.add(pestañaEditorTexto);

        pestañaVisorImagenes.setBounds(600, 0, 200, 50);
        pestañaVisorImagenes.setForeground(Color.WHITE);
        pestañaVisorImagenes.setBackground(Color.BLACK);
        pestañaVisorImagenes.setBorderPainted(false);
        panelInferior.add(pestañaVisorImagenes);

        pestañaConsolaComandos.setBounds(800, 0, 200, 50);
        pestañaConsolaComandos.setForeground(Color.WHITE);
        pestañaConsolaComandos.setBackground(Color.BLACK);
        pestañaConsolaComandos.setBorderPainted(false);
        panelInferior.add(pestañaConsolaComandos);

        pestañaReproductorMusical.setBounds(1000, 0, 170, 50);
        pestañaReproductorMusical.setForeground(Color.WHITE);
        pestañaReproductorMusical.setBackground(Color.BLACK);
        pestañaReproductorMusical.setBorderPainted(false);
        panelInferior.add(pestañaReproductorMusical);

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
        panelLateralIzquierdo.add(botonVisorImagenesSalir);
        panelLateralIzquierdo.add(botonReproductorMusical);
        panelLateralIzquierdo.add(botonReproductorMusicalSalir);
        panelLateralIzquierdo.add(etiquetaReproductorMusical);
        panelLateralIzquierdo.add(botonConsolaComandos);
        panelLateralIzquierdo.add(botonConsolaComandosSalir);
        panelLateralIzquierdo.add(etiquetaConsolaComandos);
        add(panelLateralIzquierdo);

        panelLateralDerecho.setLayout(null);
        panelLateralDerecho.setBounds(1170, 30, 200, 700);
        panelLateralDerecho.add(botonNavegador);
        panelLateralDerecho.add(botonNavegadorSalir);
        panelLateralDerecho.add(botonCrearUsuarios);
        panelLateralDerecho.add(etiquetaNavegador);
        panelLateralDerecho.add(botonEditorTexto);
        panelLateralDerecho.add(botonEditorTextoSalir);
        panelLateralDerecho.add(etiquetaEditorTexto);
        add(panelLateralDerecho);

        panelSuperior.setLayout(null);
        panelSuperior.setBounds(0, 0, 1500, 30);
        panelSuperior.add(etiquetaFecha);
        add(panelSuperior);

        panelInferior.setLayout(null);
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
        botonCerrarSesion.setBounds(0, 600, 200, 30);
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
            botonEditorTextoSalir.setIcon(new ImageIcon(imagenEditorTexto.getScaledInstance(180, 150, java.awt.Image.SCALE_SMOOTH)));
        } catch (IOException ex) {
            Logger.getLogger(VentanaEscritorio.class.getName()).log(Level.SEVERE, null, ex);
        }

        botonEditorTexto.setBounds(10, 390, 180, 150);
        botonEditorTexto.setBackground(Color.GRAY);
        botonEditorTexto.setVisible(true);

        botonEditorTextoSalir.setBounds(10, 390, 180, 150);
        botonEditorTextoSalir.setBackground(Color.GRAY);
        botonEditorTextoSalir.setVisible(false);

        etiquetaEditorTexto.setBounds(48, 530, 200, 50);
        etiquetaEditorTexto.setForeground(Color.BLACK);
        etiquetaEditorTexto.setFont(fuenteEtiquetas);

        try {
            Image imagenVisorImagenes = ImageIO.read(getClass().getResource("/imagenes/visorImagenes.jpg"));
            botonVisorImagenes.setIcon(new ImageIcon(imagenVisorImagenes.getScaledInstance(180, 150, java.awt.Image.SCALE_SMOOTH)));
            botonVisorImagenesSalir.setIcon(new ImageIcon(imagenVisorImagenes.getScaledInstance(180, 150, java.awt.Image.SCALE_SMOOTH)));
        } catch (IOException ex) {
            Logger.getLogger(VentanaEscritorio.class.getName()).log(Level.SEVERE, null, ex);
        }

        botonVisorImagenes.setBounds(10, 20, 180, 150);
        botonVisorImagenes.setBackground(Color.GRAY);

        botonVisorImagenesSalir.setBounds(10, 20, 180, 150);
        botonVisorImagenesSalir.setBackground(Color.GRAY);
        botonVisorImagenesSalir.setVisible(false);

        etiquetaVisorImagenes.setForeground(Color.BLACK);
        etiquetaVisorImagenes.setFont(fuenteEtiquetas);
        etiquetaVisorImagenes.setBounds(30, 155, 200, 50);

        try {
            Image imagenReproductorMusical = ImageIO.read(getClass().getResource("/imagenes/reproductorMusical.jfif"));
            botonReproductorMusical.setIcon(new ImageIcon(imagenReproductorMusical.getScaledInstance(180, 150, java.awt.Image.SCALE_SMOOTH)));
            botonReproductorMusicalSalir.setIcon(new ImageIcon(imagenReproductorMusical.getScaledInstance(180, 150, java.awt.Image.SCALE_SMOOTH)));
        } catch (IOException ex) {
            Logger.getLogger(VentanaEscritorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        botonReproductorMusical.setBounds(10, 220, 180, 150);
        botonReproductorMusical.setBackground(Color.GRAY);

        botonReproductorMusicalSalir.setBounds(10, 220, 180, 150);
        botonReproductorMusicalSalir.setBackground(Color.GRAY);
        botonReproductorMusicalSalir.setVisible(false);

        etiquetaReproductorMusical.setBounds(20, 355, 200, 50);
        etiquetaReproductorMusical.setForeground(Color.BLACK);
        etiquetaReproductorMusical.setFont(fuenteEtiquetas);

        try {
            Image imagenConsolaComandos = ImageIO.read(getClass().getResource("/imagenes/consolaImagen.png"));
            botonConsolaComandos.setIcon(new ImageIcon(imagenConsolaComandos.getScaledInstance(180, 150, java.awt.Image.SCALE_SMOOTH)));
            botonConsolaComandosSalir.setIcon(new ImageIcon(imagenConsolaComandos.getScaledInstance(180, 150, java.awt.Image.SCALE_SMOOTH)));
        } catch (IOException ex) {
            Logger.getLogger(VentanaEscritorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        botonConsolaComandos.setBounds(10, 415, 180, 150);
        botonConsolaComandos.setBackground(Color.GRAY);

        botonConsolaComandosSalir.setBounds(10, 415, 180, 150);
        botonConsolaComandosSalir.setBackground(Color.GRAY);
        botonConsolaComandosSalir.setVisible(false);

        etiquetaConsolaComandos.setBounds(20, 550, 200, 50);
        etiquetaConsolaComandos.setForeground(Color.BLACK);
        etiquetaConsolaComandos.setFont(fuenteEtiquetas);

        //Panel para las herramientas del editor de texto.
        panelEditorTexto.setLayout(null);
        panelEditorTexto.setBackground(Color.WHITE);
        panelEditorTexto.setBounds(300, 80, 770, 550);
        panelEditorTexto.setVisible(false);
        add(panelEditorTexto);

        //Panel del editor de texto.
        LineBorder bordeEditorTexto = new LineBorder(Color.BLACK);
        TitledBorder titulo = new TitledBorder("Editor de Texto");
        CompoundBorder borde = BorderFactory.createCompoundBorder(bordeEditorTexto, titulo);
        panelHerramientas.setLayout(null);
        panelHerramientas.setBorder(borde);
        panelHerramientas.setBackground(Color.BLUE);
        panelHerramientas.setBounds(0, 0, 770, 100);
        panelEditorTexto.add(panelHerramientas);

        scrollEditor = new JScrollPane();
        scrollEditor.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollEditor.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollEditor.setBounds(0, 100, 770, 450);
        scrollEditor.setViewportView(areaEditor);

        areaEditor.setBounds(0, 100, 770, 450);
        panelEditorTexto.add(scrollEditor);

        //Panel Consola.
        panelConsola.setLayout(null);
        panelConsola.setBounds(300, 80, 770, 550);
        panelConsola.setVisible(false);
        add(panelConsola);

        LineBorder bordeConsola = new LineBorder(Color.BLACK);
        TitledBorder tituloConsola = new TitledBorder("Consola de Comandos");
        CompoundBorder borde2 = BorderFactory.createCompoundBorder(bordeConsola, tituloConsola);
        panelConsola.setBorder(borde2);

        espacioConsola.setBounds(20, 20, 715, 20);
        espacioConsola.setBackground(Color.BLACK);
        espacioConsola.setForeground(Color.WHITE);
        panelConsola.add(espacioConsola);

        registro.setBounds(20, 60, 730, 450);
        registro.setBackground(Color.BLACK);
        registro.setForeground(Color.WHITE);
        JScrollPane scrollConsola = new JScrollPane();
        scrollConsola.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollConsola.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollConsola.setBounds(20, 55, 730, 450);
        scrollConsola.setViewportView(registro);
        panelConsola.add(scrollConsola);

        enter.setBounds(30, 515, 100, 30);
        enter.setForeground(Color.BLACK);
        enter.setBackground(Color.YELLOW);
        panelConsola.add(enter);

        listo.setBounds(240, 515, 100, 30);
        listo.setForeground(Color.BLACK);
        listo.setBackground(Color.YELLOW);
        panelConsola.add(listo);

        //Visor de imágenes.
        panelVisor.setLayout(null);
        panelVisor.setBounds(300, 80, 770, 550);
        panelVisor.setBackground(Color.BLACK);
        panelVisor.setVisible(false);
        add(panelVisor);

        espacioImagen.setBounds(40, 40, 690, 450);
        espacioImagen.setBackground(Color.RED);
        panelVisor.add(espacioImagen);

        LineBorder bordeVisor = new LineBorder(Color.BLACK);
        TitledBorder tituloVisor = new TitledBorder("Visor de Imágenes");
        tituloVisor.setTitleColor(Color.WHITE);
        CompoundBorder borde3 = BorderFactory.createCompoundBorder(bordeVisor, tituloVisor);
        panelVisor.setBorder(borde3);

        try {
            Image imagenDerecha = ImageIO.read(getClass().getResource("/imagenes/derecha2.PNG"));
            botonAdelante.setIcon(new ImageIcon(imagenDerecha.getScaledInstance(70, 30, java.awt.Image.SCALE_SMOOTH)));
            botonAdelante1.setIcon(new ImageIcon(imagenDerecha.getScaledInstance(50, 30, java.awt.Image.SCALE_SMOOTH)));

            Image imagenIzquierda = ImageIO.read(getClass().getResource("/imagenes/izquierda1.PNG"));
            botonAtras.setIcon(new ImageIcon(imagenIzquierda.getScaledInstance(70, 30, java.awt.Image.SCALE_SMOOTH)));
            botonAtras1.setIcon(new ImageIcon(imagenIzquierda.getScaledInstance(50, 30, java.awt.Image.SCALE_SMOOTH)));

            Image imagenPlay = ImageIO.read(getClass().getResource("/imagenes/play.PNG"));
            botonPlay.setIcon(new ImageIcon(imagenPlay.getScaledInstance(50, 30, java.awt.Image.SCALE_SMOOTH)));

            Image imagenStop = ImageIO.read(getClass().getResource("/imagenes/stop.PNG"));
            botonPausa.setIcon(new ImageIcon(imagenStop.getScaledInstance(60, 30, java.awt.Image.SCALE_SMOOTH)));

        } catch (IOException ex) {
            Logger.getLogger(VentanaEscritorio.class.getName()).log(Level.SEVERE, null, ex);
        }

        botonAtras.setBounds(240, 505, 70, 30);
        botonAtras.setForeground(Color.BLACK);
        botonAtras.setBackground(Color.WHITE);
        panelVisor.add(botonAtras);

        botonAdelante.setBounds(440, 505, 70, 30);
        botonAdelante.setForeground(Color.BLACK);
        botonAdelante.setBackground(Color.WHITE);
        panelVisor.add(botonAdelante);

        //Parte del reproductor musical.
        panelReproductor.setLayout(null);
        panelReproductor.setBounds(445, 200, 500, 200);
        panelReproductor.setVisible(false);
        panelReproductor.setBackground(Color.BLACK);
        add(panelReproductor);

        LineBorder bordeReproductor = new LineBorder(Color.BLACK);
        TitledBorder tituloReproductor = new TitledBorder("Reproductor Musical");
        tituloReproductor.setTitleColor(Color.WHITE);
        CompoundBorder bordeMusical = BorderFactory.createCompoundBorder(bordeReproductor, tituloReproductor);
        panelReproductor.setBorder(bordeMusical);

        botonAdelante1.setBounds(370, 155, 50, 30);
        panelReproductor.add(botonAdelante1);

        botonAtras1.setBounds(50, 155, 50, 30);
        panelReproductor.add(botonAtras1);

        botonPlay.setBounds(170, 155, 50, 30);
        panelReproductor.add(botonPlay);

        botonPausa.setBounds(245, 155, 60, 30);
        botonPausa.setVisible(true);
        panelReproductor.add(botonPausa);

        cancionActual.setBounds(10, 90, 480, 20);
        cancionActual.setBackground(Color.GRAY);
        cancionActual.setForeground(Color.WHITE);
        cancionActual.setFont(new Font("Arial", Font.BOLD, 15));
        panelReproductor.add(cancionActual);

        etiquetaCancion.setForeground(Color.GRAY);
        etiquetaCancion.setFont(new Font("Arial", Font.BOLD, 20));
        etiquetaCancion.setBounds(180, 40, 200, 25);
        panelReproductor.add(etiquetaCancion);

        //Panel para guardar el archivo de texto.
        panelGuardarTexto.setLayout(null);
        panelGuardarTexto.setBounds(545, 200, 300, 300);
        panelGuardarTexto.setBackground(Color.BLACK);
        panelGuardarTexto.setVisible(false);
        add(panelGuardarTexto);

        LineBorder bordeGuardar = new LineBorder(Color.BLACK);
        TitledBorder tituloGuardar = new TitledBorder("Guardar");
        tituloGuardar.setTitleColor(Color.WHITE);
        CompoundBorder bordeGuardador = BorderFactory.createCompoundBorder(bordeGuardar, tituloGuardar);
        panelGuardarTexto.setBorder(bordeGuardador);

        ingreseNombre.setForeground(Color.WHITE);
        ingreseNombre.setBounds(50, 80, 250, 30);
        panelGuardarTexto.add(ingreseNombre);

        nombreArchivoTexto.setBounds(80, 120, 140, 30);
        panelGuardarTexto.add(nombreArchivoTexto);

        guardarConfirmar.setBounds(100, 200, 100, 30);
        guardarConfirmar.setForeground(Color.WHITE);
        guardarConfirmar.setBackground(Color.GRAY);
        panelGuardarTexto.add(guardarConfirmar);

        cancelarGuardar.setBounds(100, 250, 100, 30);
        cancelarGuardar.setForeground(Color.WHITE);
        cancelarGuardar.setBackground(Color.RED);
        panelGuardarTexto.add(cancelarGuardar);

    }

}
