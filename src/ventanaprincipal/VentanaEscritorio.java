package ventanaprincipal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.FileNotFoundException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class VentanaEscritorio extends JPanel {
    
    //Objetos/atributos.
    Font fuenteLogin = new Font("Elephant", Font.BOLD, 50);
    Font usuarioContraseña = new Font("Elephant", Font.BOLD, 30);
    Font fuenteEtiquetas = new Font("Elephant", 0 , 15); 
    
    ImageIcon fondoEscritorio = new ImageIcon(getClass().getResource("/imagenes/fondoEscritorio.jpg"));
    JLabel etiquetaFondo = new JLabel();
    
    JButton botonCrearUsuarios = new JButton("Crear usuario");
    JButton botonCerrarSesion = new JButton("Cerrar Sesión");
    
    
    
    //Botón del navegador.
    public JButton botonNavegador = new JButton("Navegador");
    public JButton botonNavegadorSalir = new JButton("Navegador");
    
    //JTree.
    public JTree arbol = new JTree();
    public JScrollPane scroll;
    DefaultMutableTreeNode z;
    
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
        botonCerrarSesion.setBackground(Color.RED);
        botonCerrarSesion.setBounds(30, 600, 200, 50);
        add(botonCerrarSesion);
        
        
        botonNavegador.setBounds(1150, 100, 200, 50);
        botonNavegador.setBackground(Color.GREEN);
        botonNavegador.setFont(new Font("Elephant", Font.BOLD, 20));
        botonNavegador.setForeground(Color.BLACK);
        add(botonNavegador);
        
        botonNavegadorSalir.setBounds(1150, 100, 200, 50);
        botonNavegadorSalir.setBackground(Color.GREEN);
        botonNavegadorSalir.setFont(new Font("Elephant", Font.BOLD, 20));
        botonNavegadorSalir.setForeground(Color.BLACK);
        botonNavegadorSalir.setVisible(false);
        add(botonNavegadorSalir);
        
        JTree();
        
    }
    
    
    //JTree.
        public void JTree(){
        //Agregamos el JTree.
        arbol.setBounds(550,200,300,300);
        z = new DefaultMutableTreeNode("Z");
        DefaultTreeModel modeloTree = new DefaultTreeModel(z);
        arbol.setModel(modeloTree);
        
        scroll = new JScrollPane();
        scroll.setBounds(550,200,300,300);
        scroll.setViewportView(arbol);
        
        crearJTree();
        add(arbol);
                
    }
    
    public String userLogged=null;

    public void setUserLogged(String userLogged) {
        this.userLogged = userLogged;
    }
    
    public void crearJTree(){
        String u=userLogged;
        DefaultMutableTreeNode carpetaUsuario = new DefaultMutableTreeNode(u);
        z.add(carpetaUsuario);
        
    }
    

}
