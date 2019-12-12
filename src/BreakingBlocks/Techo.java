
package BreakingBlocks;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Techo {
    
    Image imagenFondoMetalico;
    
    public Techo(){

        cargarImagen();
    }

    private void cargarImagen(){

        BufferedImage imagenFM = null;
        try {
            imagenFM = ImageIO.read(new File("src/Imagenes/Fondo Metalico.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
        }
        imagenFondoMetalico = imagenFM; 
    }    
    
    Image getImagenFondoMetalico(){
        
        return imagenFondoMetalico;
    }
    
    Rectangle getRectangulo(){

        return new Rectangle( 0, 53, 565, 7);
    }
}
