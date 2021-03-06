package miniwindows;

import java.io.FileNotFoundException;
import java.io.IOException;
import ventanaprincipal.VentanaPrincipal;

public class MiniWindows {

    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.crearCarpetaZ();
        ventana.setVisible(true);
        
    }
}
