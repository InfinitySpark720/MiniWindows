package usuarios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Usuarios {

    //Atributos.
    RandomAccessFile users;
    String user;
    String password;

    public File direccion;
    public File carpetaImagenes;
    public File carpetaDocumentos;
    public File carpetaMusica;

    //CONSTRUCTOR.
    //Definimos al usuario admin en el constructor.
    public Usuarios() throws FileNotFoundException {
        /*
        Primero va el nombre del archivo.
        Segundo puede ir r que es read o rw que es para read and write.
         */
        users = new RandomAccessFile("usuarios.usr", "rw");
    }

    //Función que me lleva el puntero al final del archivo.
    public long getFinal() throws IOException {
        if (users.length() != 0) { //Verificamos que el archivo no esté vacío.
            users.seek(0); //Posicionamos el puntero en 0.
            while (users.getFilePointer() < users.length()) {
                users.readUTF(); //Leemos el usuario.
                users.readUTF(); //Leemos la contraseña.
            }
        }

        return users.getFilePointer();
    }

    public void almacenar(String user, String password) throws IOException {
        users.seek(getFinal()); //Posicionamos el puntero en la posición final.
        users.writeUTF(user);
        users.writeUTF(password);
    }

    //Los parámetros serían las contraseñas deseadas para iniciar sesión.
    public boolean findUser(String usuario, String contraseña) throws IOException {
        users.seek(0);
        String user, password; //Estas serían las contraseñas almacenadas.
        while (users.getFilePointer() < users.length()) {
            user = users.readUTF();
            password = users.readUTF();
            if (user.equals(usuario) && password.equals(contraseña)) {
                return true;
            }
        }
        return false;
    }

    //Función para la creación de folder al crear un nuevo usuario.
    public String usuarioFolder(String usuario) {
        return "Z/" + usuario;
    }

    //Función para dar la dirección de la carpeta de las imágenes.
    public String carpetaImagenes(String usuario) {
        return usuarioFolder(usuario) + "/Mis Imágenes";
    }

    //Función para dar la dirección de la carpeta de sus documentos.
    public String carpetaDocumentos(String usuario) {
        return usuarioFolder(usuario) + "/Mis Documentos";
    }

    //Función para la creación de la carpeta de la música.
    public String carpetaMusica(String usuario) {
        return usuarioFolder(usuario) + "/Mi Música";
    }

    //Creación final de todas las carpetas por defecto de los usuarios.
    public void crearEmpleadoFolder(String usuario) throws IOException {
        direccion = new File(usuarioFolder(usuario));
        direccion.mkdir();

        carpetaImagenes = new File(carpetaImagenes(usuario));
        carpetaImagenes.mkdir();

        carpetaDocumentos = new File(carpetaDocumentos(usuario));
        carpetaDocumentos.mkdir();

        carpetaMusica = new File(carpetaMusica(usuario));
        carpetaMusica.mkdir();

    }

}
