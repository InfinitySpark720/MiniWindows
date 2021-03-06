package ventanaprincipal;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import miniwindows.MiniWindows;
import opcionesescritorio.CrearUsuario;
import usuarios.Usuarios;

public class VentanaPrincipal extends JFrame implements ActionListener, TreeSelectionListener {

    //Objetos/atributos.
    MiniWindows b = new MiniWindows();
    
    VentanaLogin panelLogin = new VentanaLogin();
    VentanaEscritorio panelEscritorio = new VentanaEscritorio();
    CrearUsuario panelCrearUsuario = new CrearUsuario();
    Usuarios users = new Usuarios();
    public String usuarioLogged = null;

   
    
    //Constructor.
    public VentanaPrincipal() throws FileNotFoundException{
        
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
                    
                    usuarioLogged=usuario;
                    panelEscritorio.setUserLogged(usuario);
                    panelEscritorio.arbol.setVisible(false);
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
                
                usuarioLogged="admin";
                panelEscritorio.setUserLogged("admin");
                panelEscritorio.arbol.setVisible(false);
                
            }else{
                panelLogin.noExisteEquivocado.setVisible(true);
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
            panelEscritorio.arbol.setVisible(false);
        }
        
        //Botón para confirmar la creación del usuario nuevo.
        if (botonSeleccionado == panelCrearUsuario.botonCrear) {
            try {
                add(panelLogin);
                
                String usuario = panelCrearUsuario.campoUsuario.getText();
                String contraseña = panelCrearUsuario.campoContraseña.getText();
                
                if((users.findUser(usuario, contraseña)==false) && (usuario.equals("admin")==false)){ //Comprobamos que el usuario no existe.
                   users.almacenar(usuario, contraseña);
                   users.crearEmpleadoFolder(usuario);
                   add(panelLogin);
                   panelLogin.setVisible(true);
                   panelLogin.campoContraseña.setText(null);
                   panelLogin.campoUsuario.setText(null);
                   panelLogin.noExisteEquivocado.setVisible(false);
                   panelCrearUsuario.setVisible(false);
                }else{
                    panelCrearUsuario.usuarioExiste.setVisible(true);
                }
                
            } catch (IOException ex) {
                
            }
            
            
        }
        
        
        
        //Botón para el navegador de carpetas.
        if (botonSeleccionado == panelEscritorio.botonNavegador) {
            panelEscritorio.botonNavegadorSalir.setVisible(true);
            panelEscritorio.botonNavegador.setVisible(false);
            panelEscritorio.arbol.setVisible(true);
        }
        
        //Botón para el navegador para salir.
        if (botonSeleccionado == panelEscritorio.botonNavegadorSalir) {
            panelEscritorio.botonNavegador.setVisible(true);
            panelEscritorio.botonNavegadorSalir.setVisible(false);
            panelEscritorio.arbol.setVisible(false);
        }
        
        
        
    }
    
    
    
    //Creación de la carpeta raíz: Z.
    File file = new File("Z");
    public void crearCarpetaZ() throws IOException{
        file.mkdirs();
    }

    
    //Método oyente del JTree.
    @Override
    public void valueChanged(TreeSelectionEvent tse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
