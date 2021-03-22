package ventanaprincipal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import opcionesescritorio.CrearUsuario;
import usuarios.Usuarios;

public class VentanaPrincipal extends JFrame implements ActionListener, TreeSelectionListener {

    public static boolean pausa = false;

    //Objetos/atributos.
    VentanaLogin panelLogin = new VentanaLogin();
    VentanaEscritorio panelEscritorio = new VentanaEscritorio();
    CrearUsuario panelCrearUsuario = new CrearUsuario();
    Usuarios users = new Usuarios();

    //JTree
    public JTree arbol = new JTree();
    public JScrollPane scroll;
    public DefaultMutableTreeNode raiz;
    public DefaultTreeModel modeloTree;
    public String usuarioL;

    //Foto seleccionada.
    int num = 0;
    int primeraFoto = 0;
    String salto = System.lineSeparator();
    int primeraVezImagen = 0;
    boolean noImagen;

    //Editr de texto.
    int tamaño = 12;
    String fuente = null;
    Color colorLetra = null;
    Color colorFondo = null;
    int estilo = 0;

    //Reproductoro Musical.
    int contadorPrimeraVez = 0;
    int numCancion = 0;
    int n1 = 17;
    int contadorPrimeraVez2 = 0;

    //Constructor.
    public VentanaPrincipal() throws FileNotFoundException, IOException {

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setTitle("MiniWindows");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Asignación de actionLister a los botones.
        //Login
        panelLogin.botonLogin.addActionListener(this);

        //Escritorio.
        panelEscritorio.botonCrearUsuarios.addActionListener(this);
        panelEscritorio.botonNavegador.addActionListener(this);
        panelEscritorio.botonNavegadorSalir.addActionListener(this);
        panelEscritorio.botonCerrarSesion.addActionListener(this);
        panelEscritorio.botonEditorTexto.addActionListener(this);
        panelEscritorio.botonConsolaComandos.addActionListener(this);
        panelEscritorio.botonReproductorMusical.addActionListener(this);
        panelEscritorio.botonVisorImagenes.addActionListener(this);
        panelEscritorio.botonEditorTextoSalir.addActionListener(this);
        panelEscritorio.botonConsolaComandosSalir.addActionListener(this);
        panelEscritorio.botonReproductorMusicalSalir.addActionListener(this);
        panelEscritorio.botonVisorImagenesSalir.addActionListener(this);
        panelEscritorio.enter.addActionListener(this);
        panelEscritorio.listo.addActionListener(this);
        panelEscritorio.botonAdelante.addActionListener(this);
        panelEscritorio.botonAtras.addActionListener(this);
        panelEscritorio.comboColorFondo.addActionListener(this);
        panelEscritorio.comboColorLetra.addActionListener(this);
        panelEscritorio.comboFuenteEditor.addActionListener(this);
        panelEscritorio.comboTamañoLetra.addActionListener(this);
        panelEscritorio.comboEstilo.addActionListener(this);
        panelEscritorio.botonAdelante1.addActionListener(this);
        panelEscritorio.botonAtras1.addActionListener(this);
        panelEscritorio.botonPlay.addActionListener(this);
        panelEscritorio.botonPausa.addActionListener(this);
        panelEscritorio.botonGuardar.addActionListener(this);
        panelEscritorio.guardarConfirmar.addActionListener(this);
        panelEscritorio.cancelarGuardar.addActionListener(this);

        //Crear usuario.
        panelCrearUsuario.botonCrear.addActionListener(this);
        panelCrearUsuario.botonRegresar.addActionListener(this);

        add(panelLogin);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        Object botonSeleccionado = ae.getSource();

        if (botonSeleccionado == panelLogin.botonLogin) {

            String usuario = panelLogin.campoUsuario.getText();
            String contraseña = panelLogin.campoContraseña.getText();

            //Creación del folder del usuario administrador.
            try {
                users.crearEmpleadoFolder("admin");
            } catch (IOException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (users.findUser(usuario, contraseña) == true) {
                    add(panelEscritorio);
                    panelEscritorio.setVisible(true);
                    panelEscritorio.botonCrearUsuarios.setEnabled(false);
                    panelLogin.setVisible(false);
                    panelLogin.noExisteEquivocado.setVisible(false);
                    panelEscritorio.arbol.setVisible(false);
                    panelEscritorio.panelEditorTexto.setVisible(false);
                    panelEscritorio.panelConsola.setVisible(false);
                    panelEscritorio.panelVisor.setVisible(false);
                    usuarioL = usuario;

                    panelEscritorio.pestañaConsolaComandos.setBackground(Color.BLACK);
                    panelEscritorio.pestañaEditorTexto.setBackground(Color.BLACK);
                    panelEscritorio.pestañaNavegador.setBackground(Color.BLACK);
                    panelEscritorio.pestañaReproductorMusical.setBackground(Color.BLACK);
                    panelEscritorio.pestañaVisorImagenes.setBackground(Color.BLACK);
                }
            } catch (IOException ex) {
                panelLogin.noExisteEquivocado.setVisible(true);
            }

            if (usuario.equals("admin") && contraseña.equals("123")) {
                add(panelEscritorio);

                panelEscritorio.setVisible(true);
                panelEscritorio.botonCrearUsuarios.setEnabled(true);
                panelLogin.setVisible(false);
                panelLogin.noExisteEquivocado.setVisible(false);
                panelEscritorio.arbol.setVisible(false);
                panelEscritorio.panelEditorTexto.setVisible(false);
                panelEscritorio.panelConsola.setVisible(false);
                panelEscritorio.panelVisor.setVisible(false);
                usuarioL = "admin";

                panelEscritorio.pestañaConsolaComandos.setBackground(Color.BLACK);
                panelEscritorio.pestañaEditorTexto.setBackground(Color.BLACK);
                panelEscritorio.pestañaNavegador.setBackground(Color.BLACK);
                panelEscritorio.pestañaReproductorMusical.setBackground(Color.BLACK);
                panelEscritorio.pestañaVisorImagenes.setBackground(Color.BLACK);

            } else {
                panelLogin.noExisteEquivocado.setVisible(true);
            }

            //arbol.setVisible(false);
            try {
                scroll.setVisible(false);
            } catch (NullPointerException ex) {

            }

            panelEscritorio.botonNavegador.setVisible(true);
            panelEscritorio.botonNavegadorSalir.setVisible(false);
            panelEscritorio.botonEditorTexto.setVisible(true);
            panelEscritorio.botonEditorTextoSalir.setVisible(false);
            panelEscritorio.botonConsolaComandos.setVisible(true);
            panelEscritorio.botonConsolaComandosSalir.setVisible(false);
            panelEscritorio.comboTamañoLetra.setSelectedIndex(4);
            panelEscritorio.panelReproductor.setVisible(false);
            panelEscritorio.botonReproductorMusical.setVisible(true);
            panelEscritorio.botonReproductorMusicalSalir.setVisible(false);
            panelEscritorio.botonVisorImagenes.setVisible(true);
            panelEscritorio.botonVisorImagenesSalir.setVisible(false);
            contadorPrimeraVez = 0;
            numCancion = 0;
            primeraFoto = 0;
            panelEscritorio.comboColorFondo.setSelectedIndex(0);
            panelEscritorio.comboColorLetra.setSelectedIndex(0);
            panelEscritorio.comboEstilo.setSelectedIndex(0);
            panelEscritorio.comboFuenteEditor.setSelectedIndex(2);
            panelEscritorio.comboTamañoLetra.setSelectedIndex(4);

            //Agregamos el try-catch porque sale un la excepción del NullPointerException.
            try {
                modeloTree.reload(); //Actuailizamos nuestro JTree.
            } catch (NullPointerException ex) {

            }
        }

        //Botón para cerrar sesión.
        if (botonSeleccionado == panelEscritorio.botonCerrarSesion) {
            add(panelLogin);
            panelLogin.setVisible(true);
            panelLogin.campoContraseña.setText(null);
            panelLogin.campoUsuario.setText(null);
            panelLogin.noExisteEquivocado.setVisible(false);
            panelEscritorio.setVisible(false);
        }

        //Botón para ir al panel de creación de usuarios.
        if (botonSeleccionado == panelEscritorio.botonCrearUsuarios) {
            add(panelCrearUsuario);
            panelCrearUsuario.setVisible(true);
            panelCrearUsuario.usuarioExiste.setVisible(false);
            panelCrearUsuario.campoContraseña.setText(null);
            panelCrearUsuario.campoUsuario.setText(null);
            panelEscritorio.panelConsola.setVisible(false);
            panelEscritorio.panelReproductor.setVisible(false);
            panelEscritorio.botonReproductorMusical.setVisible(true);
            panelEscritorio.botonReproductorMusicalSalir.setVisible(false);
            panelEscritorio.setVisible(false);
        }

        //Botón para regresar al escritorio desde el panel de creación de usuario.
        if (botonSeleccionado == panelCrearUsuario.botonRegresar) {
            add(panelEscritorio);
            panelEscritorio.setVisible(true);
            panelCrearUsuario.setVisible(false);
            try {
                scroll.setVisible(false);
            } catch (NullPointerException ex) {

            }
            arbol.setVisible(false);
            panelEscritorio.panelEditorTexto.setVisible(false);
            panelEscritorio.botonNavegador.setVisible(true);
            panelEscritorio.botonNavegadorSalir.setVisible(false);
            panelEscritorio.botonEditorTexto.setVisible(true);
            panelEscritorio.botonEditorTextoSalir.setVisible(false);
            panelEscritorio.botonConsolaComandos.setVisible(true);
            panelEscritorio.botonConsolaComandosSalir.setVisible(false);
            panelEscritorio.panelConsola.setVisible(false);
            panelEscritorio.panelVisor.setVisible(false);

            panelEscritorio.pestañaConsolaComandos.setBackground(Color.BLACK);
            panelEscritorio.pestañaEditorTexto.setBackground(Color.BLACK);
            panelEscritorio.pestañaNavegador.setBackground(Color.BLACK);
            panelEscritorio.pestañaReproductorMusical.setBackground(Color.BLACK);
            panelEscritorio.pestañaVisorImagenes.setBackground(Color.BLACK);
        }

        //Botón para confirmar la creación del usuario nuevo.
        if (botonSeleccionado == panelCrearUsuario.botonCrear) {
            try {
                add(panelLogin);

                String usuario = panelCrearUsuario.campoUsuario.getText();
                String contraseña = panelCrearUsuario.campoContraseña.getText();

                if ((users.findUser(usuario, contraseña) == false) && (usuario.equals("admin") == false)) { //Comprobamos que el usuario no existe.
                    users.almacenar(usuario, contraseña);
                    users.crearEmpleadoFolder(usuario);
                    add(panelLogin);
                    panelLogin.setVisible(true);
                    panelLogin.campoContraseña.setText(null);
                    panelLogin.campoUsuario.setText(null);
                    panelLogin.noExisteEquivocado.setVisible(false);
                    panelCrearUsuario.setVisible(false);
                } else {
                    panelCrearUsuario.usuarioExiste.setVisible(true);
                }

            } catch (IOException ex) {

            }

        }

        //Botón para el navegador de carpetas.
        if (botonSeleccionado == panelEscritorio.botonNavegador) {
            panelEscritorio.botonNavegadorSalir.setVisible(true);
            panelEscritorio.botonNavegador.setVisible(false);
            iniciarJTree();
            arbol.setVisible(true);
            scroll.setVisible(true);
            panelEscritorio.pestañaNavegador.setBackground(Color.GREEN);
        }

        //Botón para el navegador para salir.
        if (botonSeleccionado == panelEscritorio.botonNavegadorSalir) {
            panelEscritorio.botonNavegador.setVisible(true);
            panelEscritorio.botonNavegadorSalir.setVisible(false);
            arbol.setVisible(false);
            scroll.setVisible(false);
            panelEscritorio.pestañaNavegador.setBackground(Color.BLACK);
        }

        //Botones para entrar y salir del editor de texto.
        if (botonSeleccionado == panelEscritorio.botonEditorTexto) {
            panelEscritorio.botonEditorTextoSalir.setVisible(true);
            panelEscritorio.pestañaEditorTexto.setBackground(Color.GREEN);
            panelEscritorio.botonEditorTexto.setVisible(false);
            panelEscritorio.panelEditorTexto.setVisible(true);
        }

        if (botonSeleccionado == panelEscritorio.botonEditorTextoSalir) {
            panelEscritorio.botonEditorTexto.setVisible(true);
            panelEscritorio.pestañaEditorTexto.setBackground(Color.BLACK);
            panelEscritorio.botonEditorTextoSalir.setVisible(false);
            panelEscritorio.panelEditorTexto.setVisible(false);
        }

        if (botonSeleccionado == panelEscritorio.botonConsolaComandos) {
            panelEscritorio.botonConsolaComandosSalir.setVisible(true);
            panelEscritorio.botonConsolaComandos.setVisible(false);
            panelEscritorio.pestañaConsolaComandos.setBackground(Color.GREEN);
            panelEscritorio.listo.setEnabled(false);
            panelEscritorio.enter.setEnabled(true);
            panelEscritorio.panelConsola.setVisible(true);
        }

        if (botonSeleccionado == panelEscritorio.botonConsolaComandosSalir) {
            panelEscritorio.botonConsolaComandosSalir.setVisible(false);
            panelEscritorio.botonConsolaComandos.setVisible(true);
            panelEscritorio.pestañaConsolaComandos.setBackground(Color.BLACK);
            panelEscritorio.panelConsola.setVisible(false);
        }

        String textoEnviado = null;
        String actualizarTexto = "";
        //Configuración de las acciones de la consola.
        if (botonSeleccionado == panelEscritorio.enter) {
            panelEscritorio.enter.setEnabled(false);
            panelEscritorio.listo.setEnabled(true);
            textoEnviado = panelEscritorio.espacioConsola.getText();
            textoEnviado = textoEnviado.replace("<", "").replace(">", "");

            if (textoEnviado.equalsIgnoreCase("time")) {
                panelEscritorio.espacioConsola.setText(verFechaActual());
            }
            if (textoEnviado.equalsIgnoreCase("date")) {
                horaActual(panelEscritorio.espacioConsola);
            }
            if (textoEnviado.contains("mkdir") || textoEnviado.contains("Mkdir")) {
                String nombreNueva = textoEnviado.substring(6);
                crearCarpeta(panelEscritorio.espacioConsola, nombreNueva);
            }
            if (textoEnviado.contains("cd") || textoEnviado.contains("Cd")) {
                try {
                    String nombreCarpeta = textoEnviado.substring(4);
                    setFile(nombreCarpeta);
                } catch (StringIndexOutOfBoundsException ex) {

                }
            }
            if (textoEnviado.contains("rm") || textoEnviado.contains("Rm")) {
                String nombreBorrar = textoEnviado.substring(3);
                eliminarCarpeta(panelEscritorio.espacioConsola, nombreBorrar);
            }
            if (textoEnviado.equalsIgnoreCase("dir")) {
                dir(panelEscritorio.espacioConsola);
            }

            actualizarTexto = panelEscritorio.espacioConsola.getText() + "\n" + actualizarTexto;
            panelEscritorio.registro.append(actualizarTexto);
        }

        if (botonSeleccionado == panelEscritorio.listo) {
            panelEscritorio.enter.setEnabled(true);
            panelEscritorio.listo.setEnabled(false);
            limpiarConsola();
        }

        //Botones para el visor de imágenes.
        if (botonSeleccionado == panelEscritorio.botonVisorImagenes) {

            panelEscritorio.botonVisorImagenes.setVisible(false);
            panelEscritorio.botonVisorImagenesSalir.setVisible(true);
            panelEscritorio.pestañaVisorImagenes.setBackground(Color.GREEN);
            panelEscritorio.panelVisor.setVisible(true);

            //agregarFotos();
        }

        if (botonSeleccionado == panelEscritorio.botonVisorImagenesSalir) {
            panelEscritorio.botonVisorImagenes.setVisible(true);
            panelEscritorio.botonVisorImagenesSalir.setVisible(false);
            panelEscritorio.pestañaVisorImagenes.setBackground(Color.BLACK);
            panelEscritorio.panelVisor.setVisible(false);
        }

        //Almacenamiento de las fotos.
        int i = 0;
        String direccionFotos = "src/Z/" + getUserLogged() + "/Mis Imágenes";
        File carpetaImagenes = new File(direccionFotos);
        String almacen[] = null;
        try {
            almacen = new String[carpetaImagenes.listFiles().length];
        } catch (NullPointerException nu) {

        }
        String direccion = carpetaImagenes.getPath();
        String name = null;

        try {
            for (File fotos : carpetaImagenes.listFiles()) {
                name = fotos.getName();
                String direccionSustitucion = direccion.replace("\\", "/");
                almacen[i] = (direccionSustitucion + "/" + name).substring(3);
                i++;
            }
        } catch (NullPointerException nu) {

        } catch (ArrayIndexOutOfBoundsException arr) {

        }

        Image imagenVisor;
        if (primeraFoto == 0) {

            try {
                if (i >= 1) {
                    try {
                        imagenVisor = ImageIO.read(getClass().getResource(almacen[0]));
                        panelEscritorio.espacioImagen.setIcon(new ImageIcon(imagenVisor.getScaledInstance(690, 450, java.awt.Image.SCALE_SMOOTH)));
                    } catch (IOException ex) {
                        Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NullPointerException nu) {
                    } catch (ArrayIndexOutOfBoundsException ar) {

                    }
                    primeraFoto++;

                } else {
                    imagenVisor = ImageIO.read(getClass().getResource("/imagenes/sinImagenes.jpg"));
                    panelEscritorio.espacioImagen.setIcon(new ImageIcon(imagenVisor.getScaledInstance(690, 450, java.awt.Image.SCALE_SMOOTH)));

                }
            } catch (ArrayIndexOutOfBoundsException ex) {

            } catch (IOException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (botonSeleccionado == panelEscritorio.botonAdelante) {
            //siguienteFoto();
            try {
                if (num < almacen.length - 1) {
                    num++;
                }
            } catch (NullPointerException ex) {

            }

            try {
                imagenVisor = ImageIO.read(getClass().getResource(almacen[num]));
                panelEscritorio.espacioImagen.setIcon(new ImageIcon(imagenVisor.getScaledInstance(690, 450, java.awt.Image.SCALE_SMOOTH)));

            } catch (IOException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException nu) {
            } catch (ArrayIndexOutOfBoundsException arr) {

            }

        }

        if (botonSeleccionado == panelEscritorio.botonAtras) {

            if (num > 0) {
                num--;
            }

            try {
                imagenVisor = ImageIO.read(getClass().getResource(almacen[num]));
                panelEscritorio.espacioImagen.setIcon(new ImageIcon(imagenVisor.getScaledInstance(690, 450, java.awt.Image.SCALE_SMOOTH)));

            } catch (IOException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException nu) {
            } catch (ArrayIndexOutOfBoundsException arr) {

            }
        }

        if (botonSeleccionado == panelEscritorio.comboTamañoLetra) {
            String tamañoString = panelEscritorio.comboTamañoLetra.getSelectedItem().toString();
            tamaño = Integer.parseInt(tamañoString);
        }

        if (botonSeleccionado == panelEscritorio.comboFuenteEditor) {
            fuente = panelEscritorio.comboFuenteEditor.getSelectedItem().toString();
        }

        if (botonSeleccionado == panelEscritorio.comboColorLetra) {
            if (panelEscritorio.comboColorLetra.getSelectedItem().toString().equals("Blanco")) {
                colorLetra = Color.WHITE;
            }
            if (panelEscritorio.comboColorLetra.getSelectedItem().toString().equals("Gris Claro")) {
                colorLetra = Color.LIGHT_GRAY;
            }
            if (panelEscritorio.comboColorLetra.getSelectedItem().toString().equals("Gris")) {
                colorLetra = Color.GRAY;
            }
            if (panelEscritorio.comboColorLetra.getSelectedItem().toString().equals("Rojo")) {
                colorLetra = Color.RED;
            }
            if (panelEscritorio.comboColorLetra.getSelectedItem().toString().equals("Verde")) {
                colorLetra = Color.GREEN;
            }
            if (panelEscritorio.comboColorLetra.getSelectedItem().toString().equals("Azul")) {
                colorLetra = Color.BLUE;
            }
            if (panelEscritorio.comboColorLetra.getSelectedItem().toString().equals("Amarillo")) {
                colorLetra = Color.YELLOW;
            }
            if (panelEscritorio.comboColorLetra.getSelectedItem().toString().equals("Celeste")) {
                colorLetra = Color.CYAN;
            }
            if (panelEscritorio.comboColorLetra.getSelectedItem().toString().equals("Magenta")) {
                colorLetra = Color.MAGENTA;
            }
            if (panelEscritorio.comboColorLetra.getSelectedItem().toString().equals("Gris Oscuro")) {
                colorLetra = Color.DARK_GRAY;
            }
            if (panelEscritorio.comboColorLetra.getSelectedItem().toString().equals("Negro")) {
                colorLetra = Color.BLACK;
            }
            if (panelEscritorio.comboColorLetra.getSelectedItem().toString().equals("Anaranjado")) {
                colorLetra = Color.ORANGE;
            }
            if (panelEscritorio.comboColorLetra.getSelectedItem().toString().equals("Rosado")) {
                colorLetra = Color.PINK;
            }

            panelEscritorio.areaEditor.setForeground(colorLetra);
        }

        if (botonSeleccionado == panelEscritorio.comboColorFondo) {
            if (panelEscritorio.comboColorFondo.getSelectedItem().toString().equals("Blanco")) {
                colorFondo = Color.WHITE;
            }
            if (panelEscritorio.comboColorFondo.getSelectedItem().toString().equals("Gris Claro")) {
                colorFondo = Color.LIGHT_GRAY;
            }
            if (panelEscritorio.comboColorFondo.getSelectedItem().toString().equals("Gris")) {
                colorFondo = Color.GRAY;
            }
            if (panelEscritorio.comboColorFondo.getSelectedItem().toString().equals("Rojo")) {
                colorFondo = Color.RED;
            }
            if (panelEscritorio.comboColorFondo.getSelectedItem().toString().equals("Verde")) {
                colorFondo = Color.GREEN;
            }
            if (panelEscritorio.comboColorFondo.getSelectedItem().toString().equals("Azul")) {
                colorFondo = Color.BLUE;
            }
            if (panelEscritorio.comboColorFondo.getSelectedItem().toString().equals("Amarillo")) {
                colorFondo = Color.YELLOW;
            }
            if (panelEscritorio.comboColorFondo.getSelectedItem().toString().equals("Celeste")) {
                colorFondo = Color.CYAN;
            }
            if (panelEscritorio.comboColorFondo.getSelectedItem().toString().equals("Magenta")) {
                colorFondo = Color.MAGENTA;
            }
            if (panelEscritorio.comboColorFondo.getSelectedItem().toString().equals("Gris Oscuro")) {
                colorFondo = Color.DARK_GRAY;
            }
            if (panelEscritorio.comboColorFondo.getSelectedItem().toString().equals("Negro")) {
                colorFondo = Color.BLACK;
            }
            if (panelEscritorio.comboColorFondo.getSelectedItem().toString().equals("Anaranjado")) {
                colorFondo = Color.ORANGE;
            }
            if (panelEscritorio.comboColorFondo.getSelectedItem().toString().equals("Rosado")) {
                colorFondo = Color.PINK;
            }

            panelEscritorio.areaEditor.setBackground(colorFondo);
        }

        if (botonSeleccionado == panelEscritorio.comboEstilo) {
            if (panelEscritorio.comboEstilo.getSelectedItem().toString().equals("Normal")) {
                estilo = Font.PLAIN;
            }
            if (panelEscritorio.comboEstilo.getSelectedItem().toString().equals("Negrita")) {
                estilo = Font.BOLD;
            }
            if (panelEscritorio.comboEstilo.getSelectedItem().toString().equals("Itálica")) {
                estilo = Font.ITALIC;
            }
        }

        panelEscritorio.areaEditor.setFont(new Font(fuente, estilo, tamaño));

        if (botonSeleccionado == panelEscritorio.botonGuardar) {
            panelEscritorio.panelGuardarTexto.setVisible(true);
            panelEscritorio.panelEditorTexto.setVisible(false);
        }

        if (botonSeleccionado == panelEscritorio.guardarConfirmar) {
            try {
                //ACÁ PODRÍA CREAR UNA EXCEPCIÓN POR SI INGRESÓ UN NOMBRE YA EXISTENTE.
                crearArchivo(panelEscritorio.nombreArchivoTexto, panelEscritorio.areaEditor);
            } catch (IOException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

            panelEscritorio.panelGuardarTexto.setVisible(false);
            panelEscritorio.panelEditorTexto.setVisible(true);
            panelEscritorio.areaEditor.setText(null);
            panelEscritorio.comboColorFondo.setSelectedIndex(0);
            panelEscritorio.comboColorLetra.setSelectedIndex(0);
            panelEscritorio.comboEstilo.setSelectedIndex(0);
            panelEscritorio.comboFuenteEditor.setSelectedIndex(2);
            panelEscritorio.comboTamañoLetra.setSelectedIndex(4);
        }

        if (botonSeleccionado == panelEscritorio.cancelarGuardar) {
            panelEscritorio.panelGuardarTexto.setVisible(false);
            panelEscritorio.panelEditorTexto.setVisible(true);
        }

        //Botones para el reproductor musical.
        //Almacenamos todas las canciones el usuario logged.
        int j = 0;
        String direccionMusica = "src/Z/" + getUserLogged() + "/Mi Música";
        File carpetaMusica = new File(direccionMusica);
        String almacenMusica[] = null;
        try {
            almacenMusica = new String[carpetaMusica.listFiles().length];
        } catch (NullPointerException nu) {

        }
        String direccion1 = carpetaMusica.getPath();
        String nameMusica = null;

        try {
            for (File canciones : carpetaMusica.listFiles()) {
                nameMusica = canciones.getName();
                String direccionSustitucion1 = direccion1.replace("\\", "/");
                almacenMusica[j] = (direccionSustitucion1 + "/" + nameMusica);

                j++;

            }
        } catch (NullPointerException nu) {

        } catch (ArrayIndexOutOfBoundsException arr) {

        }

        int n2 = 0;
        try {
            n2 = getUserLogged().length();
        } catch (NullPointerException ex) {

        }

        if (botonSeleccionado == panelEscritorio.botonReproductorMusical) {
            panelEscritorio.panelReproductor.setVisible(true);
            panelEscritorio.botonReproductorMusical.setVisible(false);
            panelEscritorio.botonReproductorMusicalSalir.setVisible(true);
            panelEscritorio.pestañaReproductorMusical.setBackground(Color.GREEN);

            if (contadorPrimeraVez2 == 0) {
                try {
                    if (j >= 1) {
                        panelEscritorio.cancionActual.setText(almacenMusica[0].substring(n1 + n2).replace(".mp3", ""));
                        contadorPrimeraVez++;
                    } else {
                        panelEscritorio.cancionActual.setText("No tiene canciones guardadas.");
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {

                }
            }
        }

        if (botonSeleccionado == panelEscritorio.botonReproductorMusicalSalir) {
            panelEscritorio.panelReproductor.setVisible(false);
            panelEscritorio.botonReproductorMusical.setVisible(true);
            panelEscritorio.botonReproductorMusicalSalir.setVisible(false);
            panelEscritorio.pestañaReproductorMusical.setBackground(Color.BLACK);
        }

        if (botonSeleccionado == panelEscritorio.botonAdelante1) {
            try {
                if (numCancion < almacenMusica.length - 1) {
                    numCancion++;
                }
            } catch (NullPointerException ex) {

            }

            try {
                String cancionNombre = almacenMusica[numCancion].substring(n1 + n2).replace(".mp3", "");
                panelEscritorio.cancionActual.setText(cancionNombre);
            } catch (ArrayIndexOutOfBoundsException ex) {

            }

            contadorPrimeraVez = 0;
        }

        if (botonSeleccionado == panelEscritorio.botonAtras1) {
            if (numCancion > 0) {
                numCancion--;
            }

            try {
                String cancionNombre = almacenMusica[numCancion].substring(n1 + n2).replace(".mp3", "");
                panelEscritorio.cancionActual.setText(cancionNombre);
            } catch (ArrayIndexOutOfBoundsException ex) {

            }

            contadorPrimeraVez = 0;
        }

        if (botonSeleccionado == panelEscritorio.botonPlay) {

            if (contadorPrimeraVez == 0) {
                try {
                    play(almacenMusica[numCancion]);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JavaLayerException ex) {
                    Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ArrayIndexOutOfBoundsException ex) {

                }

                contadorPrimeraVez++;
            } else {
                try {
                    resumen(almacenMusica[numCancion]);
                } catch (JavaLayerException ex) {
                    Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ArrayIndexOutOfBoundsException ex) {

                }
            }

            panelEscritorio.botonAdelante1.setEnabled(false);
            panelEscritorio.botonAtras1.setEnabled(false);
            panelEscritorio.botonPlay.setEnabled(false);
        }

        if (botonSeleccionado == panelEscritorio.botonPausa) {
            panelEscritorio.botonAdelante1.setEnabled(true);
            panelEscritorio.botonAtras1.setEnabled(true);
            panelEscritorio.botonPlay.setEnabled(true);

            try {
                pausar();
            } catch (IOException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Creación de la carpeta raíz: Z.
    File fileRaiz = new File("src/Z");

    public void crearCarpetaZ() throws IOException {
        fileRaiz.mkdirs();
    }

    //Método para darle las acciones al JTree más adelante.
    @Override
    public void valueChanged(TreeSelectionEvent tse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void iniciarJTree() {

        LineBorder lineBorde = new LineBorder(Color.BLACK);
        TitledBorder titleBorder = new TitledBorder("Navegador de Archivos");
        CompoundBorder border = BorderFactory.createCompoundBorder(lineBorde, titleBorder);
        arbol.setBorder(border);

        arbol.setBounds(500, 180, 400, 400);
        raiz = new DefaultMutableTreeNode(fileRaiz.getName());
        modeloTree = new DefaultTreeModel(raiz);
        arbol.setModel(modeloTree);

        scroll = new JScrollPane();
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(500, 180, 400, 400);
        scroll.setViewportView(arbol);

        panelEscritorio.add(scroll);
        //panelEscritorio.add(arbol);
        agregarNodosJTree();
    }

    public void agregarNodosJTree() {

        if (!getUserLogged().equals("admin")) {
            String archivosUsuarioDireccion = "src/Z/" + getUserLogged();
            //Listar las carpetas de un usuario en concreto.
            File archivosUsuario = new File(archivosUsuarioDireccion);
            listarFiles(fileRaiz, getUserLogged(), archivosUsuario);

        } else {
            listarFilesAdmin(fileRaiz);
        }
    }

    //Mostrar las carpetas del usuario logged en caso de que no sean admin.
    public void listarFiles(File direccion, String userLogged, File direccion2) {
        DefaultMutableTreeNode hijo2;
        DefaultMutableTreeNode hijo = null;

        for (File hijos : direccion.listFiles()) {
            if (hijos.getName().equals(userLogged)) { //Nos aseguramos que sólo muestre las carpetas del usuario logged.
                hijo = new DefaultMutableTreeNode(hijos.getName());
                raiz.add(hijo);
            }
            if (hijos.getName().equals(userLogged) && !hijos.getName().equals("admin")) {
                for (File hijos2 : direccion2.listFiles()) {
                    hijo2 = new DefaultMutableTreeNode(hijos2.getName());
                    hijo.add(hijo2); //Aquí lo agregaríamos al nodo del usuario.

                    if (hijos2.isDirectory()) {
                        listarFilesUserNormal(hijos2, hijo2);
                    }
                }
            }
        }
    }

    //Listar a todas las carpetas cuando sea el usuario admin.
    public void listarFilesAdmin(File direccionRaiz) {
        String direccionUsuarios;
        for (File hijos : direccionRaiz.listFiles()) {
            DefaultMutableTreeNode hijo = new DefaultMutableTreeNode(hijos.getName());
            raiz.add(hijo);

            direccionUsuarios = "src/Z/" + hijos.getName();
            File hijosUsuarios = new File(direccionUsuarios);
            for (File hijos2 : hijosUsuarios.listFiles()) {
                DefaultMutableTreeNode hijo2 = new DefaultMutableTreeNode(hijos2.getName());
                hijo.add(hijo2); //Aquí lo agregaríamos al nodo de cada usuario.

                if (hijos2.isDirectory()) {
                    listarFilesAdmin2(hijos2, hijo2);
                }
            }

        }

    }

    public void listarFilesAdmin2(File direccionRaiz, DefaultMutableTreeNode children) {
        for (File hijos3 : direccionRaiz.listFiles()) {
            DefaultMutableTreeNode hijo3 = new DefaultMutableTreeNode(hijos3.getName());
            children.add(hijo3);

            if (hijos3.isDirectory()) {
                listarFilesAdmin2(hijos3, hijo3);
            }
        }
    }

    public String getUserLogged() {
        return usuarioL;
    }

    public void listarFilesUserNormal(File direccionRaiz, DefaultMutableTreeNode children) {
        for (File hijos3 : direccionRaiz.listFiles()) {
            DefaultMutableTreeNode hijo3 = new DefaultMutableTreeNode(hijos3.getName());
            children.add(hijo3);

            //Se hace uso de recursividad para poder ir sacando cada archivo de todas las carpetas.
            if (hijos3.isDirectory()) {
                listarFilesUserNormal(hijos3, hijo3);
            }
        }
    }

    //Funciones para la consola.
    public void limpiarConsola() { //Función que ayudará a reiniciar los comandos enviados por consola.
        panelEscritorio.espacioConsola.setText(null);
    }

    //Método para ver la fecha actual por consola.
    public String verFechaActual() {
        Calendar fechaActual = Calendar.getInstance();
        int dia = fechaActual.getTime().getDate();
        int mes = (fechaActual.getTime().getMonth()) + 1;
        fechaActual.getTime().getYear();
        return "Fecha: " + dia + "/" + mes + "/2021";

    }

    //Método para ver en consola la hora actual.
    public void horaActual(JTextArea area) {
        timer = new Timer(ONE_SECOND, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
//                area.setText(clockFormat.format(new Date()));
//                area.repaint();
            }
        });
        area.setText(clockFormat.format(new Date()));
        timer.start();
    }

    JLabel clockLabel;
    public Timer timer;
    public final static int ONE_SECOND = 1000;
    private final SimpleDateFormat clockFormat = new SimpleDateFormat("H:mm:ss");

    //Método para la creación de carpetas.
    File file;

    public void crearCarpeta(JTextArea texto, String nombreCarpeta) {
        String direccionNuevaCarpeta = file.getPath() + "/" + nombreCarpeta;
        setFile(direccionNuevaCarpeta);
        try {
            if (file.mkdirs()) {
                texto.setText("Se ha creado la carpeta exitosamente.");
            } else {
                texto.setText("No se ha creado la carpeta.");
            }
        } catch (NullPointerException ex) {

        }
    }

    //Método para eliminar la carpeta.
    public void eliminarCarpeta(JTextArea texto, String nombreCarpeta) {
        String direccionNuevaCarpeta = file.getPath() + "/" + nombreCarpeta;
        setFile(direccionNuevaCarpeta);

        if (buscarFile(file)) {
            texto.setText("¡Se ha borrado exitosamente!");
        } else {
            texto.setText("¡No se ha logrado borrar!");
        }
    }

    //Buscar la carpeta.
    private boolean buscarFile(File files) {

        if (files.isDirectory()) {
            for (File hijo : files.listFiles()) {
                buscarFile(hijo);
            }
        }
        return files.delete();
    }

    //Ubicación del archivo o directorio.
    public void setFile(String direccion) {
        file = new File(direccion);
    }

    //Método para listar a todos los archivos.
    public void dir(JTextArea texto) {
        if (file.isDirectory()) {
            texto.append("\nDirectorio de: " + file.getAbsolutePath() + "\n");
            int cFiles = 0; //cantidad de archivos.
            int cdireccion = 0;
            int tbytes = 0; //Para saber el tamaño total de todos los archivos.

            for (File hijo : file.listFiles()) {
                if (!hijo.isHidden()) {
                    Date fecha = new Date(hijo.lastModified());
                    texto.append("\n" + fecha + "\t");

                    if (hijo.isDirectory()) {
                        cdireccion++;
                        texto.append("<DIR>\t\t");
                    } else {
                        cFiles++;
                        tbytes += hijo.length();
                        texto.append("    \t" + hijo.length() + "\t"); //Indica el tamaño de cada uno de los hijos.
                    }
                    texto.append("\n" + hijo.getName());

                }
            }

            texto.append(cFiles + " archivos\t" + tbytes + " bytes.\n");
            texto.append(+cdireccion + " dirs.\t" + file.getFreeSpace() + " free bytes.\n");
        }
    }

    //Funciones para el reproductor de música.
    FileInputStream FIS;
    BufferedInputStream BIS;
    public Player player;
    public long pausaUbicacion;
    public long totalTamaño;

    public void stop() {
        if (player != null) {
            player.close();
        }
    }

    public void pausar() throws IOException {
        if (player != null) {
            try {
                pausaUbicacion = FIS.available();
                player.close();
            } catch (IOException ex) {

            }
        }
    }

    public void play(String direccion) throws FileNotFoundException, JavaLayerException, IOException {
        FIS = new FileInputStream(direccion);
        BIS = new BufferedInputStream(FIS);
        player = new Player(BIS);
        totalTamaño = FIS.available();

        new Thread() {
            @Override
            public void run() {
                try {
                    player.play();
                } catch (JavaLayerException ex) {
                    Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();

    }

    public void resumen(String direccion) throws FileNotFoundException, JavaLayerException, IOException {
        FIS = new FileInputStream(direccion);
        BIS = new BufferedInputStream(FIS);
        player = new Player(BIS);

        try {
            FIS.skip(totalTamaño - pausaUbicacion);
        } catch (IOException ex) {

        }

        new Thread() {
            @Override
            public void run() {
                try {
                    player.play();
                } catch (JavaLayerException ex) {
                    Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();

    }

    //Funciones para los archivos de texto.
    File archivo = null;

    public void crearArchivo(JTextField nombreArchivo, JTextArea texto) throws IOException {
        String direccion1 = "src/Z/" + getUserLogged() + "/Mis Documentos/" + nombreArchivo.getText() + ".txt";
        archivo = new File(direccion1);
        archivo.createNewFile();

        escribirArchivo(texto);
    }

    public void escribirArchivo(JTextArea texto) throws IOException {
        FileWriter escribir = new FileWriter(archivo, true);
        escribir.write(texto.getText());
        escribir.flush();
    }

}
