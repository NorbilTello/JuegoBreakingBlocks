/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DestruccionDeLadrillos;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
/**
 *
 * @author Norbil
 */
public class Ladrillos {

    Image imagenLadrillos;
    private int x;
    private int y;
    public static int ANCHO_IMAGEN;
    public static int ALTURA_IMAGEN;
    public static int NUMERO_DE_LADRILLOS = 70;
    private boolean destruido;

    public Ladrillos(int x, int y){
        
        this.x = x;
        this.y = y;
        
        destruido=false;
        dimensionImagen();
        cargarImagen(); 
    }  

    private void cargarImagen(){
        
        BufferedImage imagenL = null;
        try {
            imagenL = ImageIO.read(new File("src/Imagenes/ladrillo.png"));
        } catch (IOException ex) {
            Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
        }
        imagenLadrillos = imagenL;
        
    }
    
    Image getImagenLadrillos(){
        
        return imagenLadrillos;
    }
    private void dimensionImagen(){
        
        ANCHO_IMAGEN = 55;
        ALTURA_IMAGEN = 20;
    }
     
    boolean esDestruido(){
        
        return destruido;
    }
    
    void setDestruido(boolean val){
        
        destruido = val;
    }
    
    public void setX(int x){
        
        this.x = x;
    }
    
    int getX(){
        
        return x;
    }
    
    public void setY(int y){
        
        this.y = y;
    }
    
    int getY(){
        
        return y;
    }
    
    Rectangle getRectangulo(){
        
        return new Rectangle(x, y, ANCHO_IMAGEN, ALTURA_IMAGEN);
    }
}
