package ventanaprincipal;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;

public class prueba {

    public static void main(String[] args) {

//        String direccionFotos = "src/Z/jorge" + "/Mis Imágenes";
//        File carpetaImagenes = new File(direccionFotos);
//        //System.out.println(carpetaImagenes.getAbsolutePath());
//
//        String direccion = carpetaImagenes.getPath();
//
//        String rem = direccion.replace("\\", "/");
//        //System.out.println(rem);
//
//        String pru = null;
//        for (File fotos : carpetaImagenes.listFiles()) {
//            pru = fotos.getName();
//        }
//
//        rem = direccion.replace("\\", "/");
//        rem = rem + "/" + pru;
//        System.out.println(rem);
        String prueba = "Rm <hola>";
        String prueba2 = prueba.substring(3).replace("<", "");
        prueba2 = prueba2.replace(">", "");
        System.out.println(prueba2);

        String textoEnviado = "mkdir <holasssssssa>";
        String nombreCarpeta = textoEnviado.substring(6).replace("<", "").replace(">", "");;
        //nombreCarpeta = nombreCarpeta.replace(">", "");
        System.out.print(nombreCarpeta);
    }

}
