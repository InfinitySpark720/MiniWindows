package ventanaprincipal;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaLogin extends JPanel {

    JButton botonLogin = new JButton("Login");

    JTextField campoUsuario = new JTextField();
    JPasswordField campoContraseña = new JPasswordField();

    JLabel etiquetaLogin = new JLabel();
    JLabel etiquetaUsuario = new JLabel();
    JLabel etiquetaContraseña = new JLabel();

    Font fuenteLogin = new Font("Elephant", Font.BOLD, 50);
    Font usuarioContraseña = new Font("Elephant", Font.BOLD, 30);
    Font fuenteEtiquetas = new Font("Elephant", 0, 15);

    //Etiqueta de aviso que el usuario ingresado, no existe/equivocado.
    public JLabel noExisteEquivocado = new JLabel("El usuario/contraseña que ha ingresado no existe o lo ha ingresado mal.");

    public VentanaLogin() {
        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);

        //Boton Login
        add(botonLogin);
        botonLogin.setBounds(650, 550, 100, 50);
        botonLogin.setBackground(Color.WHITE);
        botonLogin.setFont(new Font("Elephant", Font.BOLD, 20));
        botonLogin.setForeground(Color.BLACK);

        //Escritos.
        etiquetaLogin.setText("LOGIN");
        etiquetaUsuario.setText("Usuario: ");
        etiquetaContraseña.setText("Contraseña:");

        etiquetaLogin.setForeground(Color.ORANGE);
        etiquetaUsuario.setForeground(Color.ORANGE);
        etiquetaContraseña.setForeground(Color.ORANGE);

        etiquetaLogin.setBounds(600, 20, 300, 100);
        etiquetaUsuario.setBounds(440, 200, 300, 100);
        etiquetaContraseña.setBounds(440, 400, 300, 100);

        etiquetaLogin.setFont(fuenteLogin);
        etiquetaUsuario.setFont(usuarioContraseña);
        etiquetaContraseña.setFont(usuarioContraseña);

        add(etiquetaLogin);
        add(etiquetaUsuario);
        add(etiquetaContraseña);

        //Espacio para escribiri la contraseña y usuario.
        campoUsuario.setBounds(650, 225, 200, 50);
        campoContraseña.setBounds(650, 425, 200, 50);

        add(campoUsuario);
        add(campoContraseña);

        //Colocar aviso.
        noExisteEquivocado.setFont(fuenteEtiquetas);
        noExisteEquivocado.setForeground(Color.RED);
        noExisteEquivocado.setVisible(false);
        noExisteEquivocado.setBounds(450, 160, 600, 40);

        add(noExisteEquivocado);

    }

}
