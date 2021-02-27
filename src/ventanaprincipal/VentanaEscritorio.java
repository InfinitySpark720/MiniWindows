package ventanaprincipal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class VentanaEscritorio extends JPanel {
    
    //Objetos/atributos.
    Font fuenteLogin = new Font("Elephant", Font.BOLD, 50);
    Font usuarioContraseña = new Font("Elephant", Font.BOLD, 30);
    Font fuenteEtiquetas = new Font("Elephant", 0 , 15); 
    
    ImageIcon fondoEscritorio = new ImageIcon(getClass().getResource("/imagenes/fondoEscritorio.jpg"));
    JLabel etiquetaFondo = new JLabel();
    
    JButton botonCrearUsuarios = new JButton("Crear usuario");
    JButton botonCerrarSesion = new JButton("Cerrar Sesión");
    
    
    //JLabel etiquetasCarpetasUsuario = new JLabel("Texto prueba.  \r\nTexto prueba2!.  \r\nTexto prueba3.");
    JTextArea espacioCarpetasUsuario = new JTextArea();
    JLabel crearCarpeta = new JLabel("Crear Carpeta");
    JButton botonCrearCarpeta = new JButton("Crear");
    
    public VentanaEscritorio(){
        
        this.setLayout(null);
        this.setBackground(Color.BLUE);
        
        //etiquetaFondo.setIcon(new ImageIcon(fondoEscritorio.getImage().getScaledInstance(etiquetaFondo.getWidth(), etiquetaFondo.getHeight(), Image.SCALE_SMOOTH)));
        //add(etiquetaFondo);
        
        botonCrearUsuarios.setBounds(1150,30,200,50);
        botonCrearUsuarios.setBackground(Color.GREEN);
        botonCrearUsuarios.setFont(new Font("Elephant", Font.BOLD, 20));
        botonCrearUsuarios.setForeground(Color.BLACK);
        add(botonCrearUsuarios);

        
        //Botón de regresar.
        botonCerrarSesion.setFont(new Font("Elephant", Font.BOLD, 20));
        botonCerrarSesion.setForeground(Color.BLACK);
        botonCerrarSesion.setBackground(Color.GREEN);
        botonCerrarSesion.setBounds(30, 600, 200, 50);
        add(botonCerrarSesion);
        
        espacioCarpetasUsuario.setForeground(Color.BLACK);
        espacioCarpetasUsuario.setFont(fuenteEtiquetas);
        espacioCarpetasUsuario.setBounds(300, 100, 500, 500);
        add(espacioCarpetasUsuario);
        
        crearCarpeta.setForeground(Color.BLACK);
        crearCarpeta.setFont(fuenteEtiquetas);
        crearCarpeta.setBounds(930, 100, 200, 100);
        add(crearCarpeta);
        
        botonCrearCarpeta.setForeground(Color.BLACK);
        botonCrearCarpeta.setFont(new Font("Elephant", Font.BOLD, 20));
        botonCrearCarpeta.setBackground(Color.GREEN);
        botonCrearCarpeta.setBounds(900, 200, 200, 50);
        add(botonCrearCarpeta);
    }
    
}
