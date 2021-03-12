package ventanaprincipal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import opcionesescritorio.CrearUsuario;
import usuarios.Usuarios;

public class VentanaPrincipal extends JFrame implements ActionListener, TreeSelectionListener {

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

            try {
                if (users.findUser(usuario, contraseña) == true) {
                    add(panelEscritorio);
                    panelEscritorio.setVisible(true);
                    panelEscritorio.botonCrearUsuarios.setEnabled(false);
                    panelLogin.setVisible(false);
                    panelLogin.noExisteEquivocado.setVisible(false);
                    panelEscritorio.arbol.setVisible(false);
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
            panelEscritorio.setVisible(false);
        }

        //Botón para regresar al escritorio desde el panel de creación de usuario.
        if (botonSeleccionado == panelCrearUsuario.botonRegresar) {
            add(panelEscritorio);
            panelEscritorio.setVisible(true);
            panelCrearUsuario.setVisible(false);
            scroll.setVisible(false);
            arbol.setVisible(false);
            panelEscritorio.botonNavegador.setVisible(true);
            panelEscritorio.botonNavegadorSalir.setVisible(false);

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

    }

    //Creación de la carpeta raíz: Z.
    File fileRaiz = new File("Z");

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
            String archivosUsuarioDireccion = "Z/" + getUserLogged();
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

            direccionUsuarios = "Z/" + hijos.getName();
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

    public void listarArchivosUsuarioEspecifico(File direccionRaiz, DefaultMutableTreeNode children) {

    }

    public void listarCarpetasUsuario(File direccion) {

        for (File hijos : direccion.listFiles()) {
            DefaultMutableTreeNode carpetaUsuario = new DefaultMutableTreeNode(hijos.getName());
            carpetaUsuario.add(carpetaUsuario);
        }
    }

    public String getUserLogged() {
        return usuarioL;
    }

    //
    //
    //
    public void listarFilesUserNormal(File direccionRaiz, DefaultMutableTreeNode children) {
        for (File hijos3 : direccionRaiz.listFiles()) {
            DefaultMutableTreeNode hijo3 = new DefaultMutableTreeNode(hijos3.getName());
            children.add(hijo3);

            if (hijos3.isDirectory()) {
                listarFilesUserNormal(hijos3, hijo3);
            }
        }
    }

}
