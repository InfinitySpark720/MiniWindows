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
    Usuarios usuarios = new Usuarios();
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
    private static int intentos;
    private static String user, contraseña;
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        
        Object botonSeleccionado = ae.getSource();

        
        if (botonSeleccionado == panelLogin.botonLogin) {
            
            FileReader fr = null;
            try {
                int nLineas = 0;
                int i = 0;
                String usuarios[] = null;
                String linea;
                sc = new Scanner(new File("usuarios.txt"));
                File file = new File("usuarios.txt");
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
                
                //Cada vez que presione el botón de login, ha hecho un intento más.
                intentos++;
                user = panelLogin.campoUsuario.getText();
                contraseña = panelLogin.campoContraseña.getText();
                Seguridad seguridad = new Seguridad();

                if (seguridad.validarUsuario(usuarios, user, contraseña, intentos) == true) {
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
       
        
        
        
        
    }
    
    
    
}
