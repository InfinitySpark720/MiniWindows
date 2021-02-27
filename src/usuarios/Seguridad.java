package usuarios;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Seguridad {
    
    //Login.
    public boolean validarUsuario(String usuarios[], String user, String contraseña){
        
        for (int i = 0; i < usuarios.length; i++) {
            //La contraseña tiene un i+1 porque tiene una posición más en el archivo.
            if (usuarios[i].equals(user) && usuarios[i+1].equals(contraseña)) {
                return true;
            }
        }
        return false;

    }
    
    
    //Creación de usuario.
    public boolean creacionUsuario(String usuarios[], String usuarioDeseado, String contraseñaDeseada, File archivo){
        
        boolean usuarioUnico = false;
        int i = 0;

        while(usuarios[i]!=null){
            if (usuarios[i].equals(usuarioDeseado)==false) {
                usuarioUnico=true;
            }
            i++;
        }
        
        
        if (usuarioUnico == true) {
            try {
                FileWriter escribir = new FileWriter(archivo, true);
                escribir.write("\r\n"+usuarioDeseado);
                escribir.write("\r\n"+contraseñaDeseada);
                escribir.flush();
                return true;

            } catch (IOException ex) {
                Logger.getLogger(Seguridad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return false;
        
    }
    
    
    
}
