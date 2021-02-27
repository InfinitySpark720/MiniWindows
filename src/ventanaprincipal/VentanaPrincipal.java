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
import opcionesescritorio.CrearUsuario;
import usuarios.Seguridad;
import usuarios.Usuarios;

public class VentanaPrincipal extends JFrame implements ActionListener{

    //Objetos/atributos.
    VentanaLogin panelLogin = new VentanaLogin();
    VentanaEscritorio panelEscritorio = new VentanaEscritorio();
    CrearUsuario panelCrearUsuario = new CrearUsuario();
    
    int posicionUsuario = 1; //Establecemos la posición 1 porque la 0 ya está ocupada.
    
    //Constructor.
    public VentanaPrincipal(){
        
        
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setVisible(true);
        setTitle("MiniWindows");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        //Asignación de actionLister a los botones.
        
        //Login
        panelLogin.botonLogin.addActionListener(this); 
        
        //Escritorio.
        panelEscritorio.botonCrearUsuarios.addActionListener(this);
        panelEscritorio.botonCerrarSesion.addActionListener(this);
         
        //Crear usuario.
        panelCrearUsuario.botonCrear.addActionListener(this);
        panelCrearUsuario.botonRegresar.addActionListener(this);
        
        add(panelLogin);
    }
    
    
    
    //Variables para el login.
    private static Scanner sc;
    private static String user, contraseña;
    private static File almacenUsuarios = new File("usuarios.txt");
    private File file = new File("usuarios.txt");
    int nLineas = 0;
    String usuarios[] = new String[10];
    Seguridad seguridad = new Seguridad();

    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        
        Object botonSeleccionado = ae.getSource();

        
        if (botonSeleccionado == panelLogin.botonLogin) {
            
            FileReader fr = null;
            try {
                int i = 0;
                String linea;
                sc = new Scanner(almacenUsuarios);
                fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                
                try {
                    //Procesamos el archivo.
                    while((linea=br.readLine())!=null){
                        nLineas++; //Aquí contamos las líneas hasta que ya no hayan líneas.
                    }
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                //Así sabemos el tamaño del arreglo.
                usuarios = new String[nLineas];
                
                //Para llenarlo.
                while(sc.hasNextLine()){
                    usuarios[i++] = sc.nextLine(); //Estamos almacenando cada línea en una posición del arreglo.
                }
                
                user = panelLogin.campoUsuario.getText();
                contraseña = panelLogin.campoContraseña.getText();

                if (seguridad.validarUsuario(usuarios, user, contraseña) == true) {
                    add(panelEscritorio);
                    panelEscritorio.setVisible(true);
                    panelLogin.noExisteEquivocado.setVisible(false);
                    panelLogin.setVisible(false);
                }else{
                    panelLogin.noExisteEquivocado.setVisible(true);
                }
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            
        }
       
        
        
        
        //Botón para cerrar sesión.
        if (botonSeleccionado == panelEscritorio.botonCerrarSesion) {
            add(panelLogin);
            panelLogin.setVisible(true);
            panelEscritorio.setVisible(false);
        }
        
        //Botón para ir al panel de creación de usuarios.
        if (botonSeleccionado == panelEscritorio.botonCrearUsuarios) {
            add(panelCrearUsuario);
            panelCrearUsuario.setVisible(true);
            panelEscritorio.setVisible(false);
        }
        
        //Botón para regresar al escritorio desde el panel de creación de usuario.
        if (botonSeleccionado == panelCrearUsuario.botonRegresar) {
            add(panelEscritorio);
            panelEscritorio.setVisible(true);
            panelCrearUsuario.setVisible(false);
        }
        
        //Botón para confirmar la creación del usuario nuevo.
        if (botonSeleccionado == panelCrearUsuario.botonCrear) {
            add(panelLogin);
            
            String usuarioDeseado = panelCrearUsuario.campoUsuario.getText();
            String contraseñaDeseada = panelCrearUsuario.campoContraseña.getText();
            
            boolean usuarioRepetido=false;
            
            for(int i = 0; i<usuarios.length; i=i+2){
                if (usuarioDeseado.equals(usuarios[i])) {
                    usuarioRepetido = true;
                }
                
            }
            
            if (seguridad.creacionUsuario(usuarios, usuarioDeseado, contraseñaDeseada, file)==true && usuarioRepetido == false) {
                panelLogin.setVisible(true);
                panelCrearUsuario.setVisible(false);
            }
            
        }
        
        
        
        
        
    }
    
    
    
}
