package opcionesescritorio;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CrearUsuario extends JPanel {

    public JButton botonCrear = new JButton("Crear");
    public JTextField campoUsuario = new JTextField();
    public JPasswordField campoContraseña = new JPasswordField();

    JLabel etiquetaCrearUser = new JLabel("Crear Usuario");
    JLabel etiquetaUsuario = new JLabel();
    JLabel etiquetaContraseña = new JLabel();

    Font fuenteLogin = new Font("Elephant", Font.BOLD, 50);
    Font usuarioContraseña = new Font("Elephant", Font.BOLD, 30);
    Font fuenteEtiquetas = new Font("Elephant", 0, 15);

    public JButton botonRegresar = new JButton("Regresar");
    public JLabel usuarioExiste = new JLabel("El usuario que ha ingresado ya existe.");

    public CrearUsuario() {

        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);

        usuarioExiste.setForeground(Color.red);
        usuarioExiste.setFont(fuenteEtiquetas);
        usuarioExiste.setBounds(515, 100, 350, 100);
        add(usuarioExiste);

        //Botón de regresar.
        botonRegresar.setFont(new Font("Elephant", Font.BOLD, 20));
        botonRegresar.setForeground(Color.BLACK);
        botonRegresar.setBackground(Color.RED);
        botonRegresar.setBounds(30, 600, 180, 50);
        add(botonRegresar);

        //Botón de crear usuarios.
        add(botonCrear);
        botonCrear.setBounds(650, 550, 100, 50);
        botonCrear.setBackground(Color.WHITE);
        botonCrear.setFont(new Font("Elephant", Font.BOLD, 20));
        botonCrear.setForeground(Color.BLACK);

        //Escritos.
        etiquetaUsuario.setText("Usuario: ");
        etiquetaContraseña.setText("Contraseña");

        etiquetaCrearUser.setForeground(Color.ORANGE);
        etiquetaUsuario.setForeground(Color.ORANGE);
        etiquetaContraseña.setForeground(Color.ORANGE);

        etiquetaCrearUser.setBounds(460, 20, 500, 100);
        etiquetaUsuario.setBounds(450, 200, 300, 100);
        etiquetaContraseña.setBounds(450, 400, 300, 100);

        etiquetaCrearUser.setFont(fuenteLogin);
        etiquetaUsuario.setFont(usuarioContraseña);
        etiquetaContraseña.setFont(usuarioContraseña);

        add(etiquetaCrearUser);
        add(etiquetaUsuario);
        add(etiquetaContraseña);

        //Espacio para escribiri la contraseña y usuario.
        campoUsuario.setBounds(650, 225, 200, 50);
        campoContraseña.setBounds(650, 425, 200, 50);

        add(campoUsuario);
        add(campoContraseña);

    }

}
