package usuarios;

public class Seguridad {
    
    
    public boolean validarUsuario(String usuarios[], String user, String contraseña, int intentos){
        
        for (int i = 0; i < usuarios.length; i++) {
            //La contraseña tiene un i+1 porque tiene una posición más en el archivo.
            if (usuarios[i].equals(user) && usuarios[i+1].equals(contraseña)) {
                return true;
            }
        }
        return false;

    }
    
    
    
}
