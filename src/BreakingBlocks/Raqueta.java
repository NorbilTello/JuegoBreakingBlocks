
package BreakingBlocks;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Raqueta {
    
    Image imagenRaqueta;
    private int x;
    public static int Y_RAQUETA = 560;
    private int xDirRaqueta;
    public static int ANCHO_IMAGEN;
    public static int ALTURA_IMAGEN;
    
    public Raqueta(){
        
        cargarImagen();
        dimensionImagen();
        restablecerEstado();
    }
    
    private void cargarImagen(){

        BufferedImage imagenR = null;
        try {
            imagenR = ImageIO.read(new File("src/Imagenes/raqueta.png"));
        } catch (IOException ex) {
            Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
        }
        imagenRaqueta = imagenR;
    }
    
    Image getImagenRaqueta(){
        
        return imagenRaqueta;
    }
    
    void dimensionImagen(){
        
        ANCHO_IMAGEN = 100;
        ALTURA_IMAGEN = 40;
    }
    
    void teclaPresionada(KeyEvent e){
        
        int tecla = e.getKeyCode(); //código de la tecla presionada
        
        if(tecla == KeyEvent.VK_LEFT){ //se da cuando el codigo de la tecla es igual a la tecla de la izquierda
            
            xDirRaqueta = -5;
        }
        
        if(tecla == KeyEvent.VK_RIGHT){//se da cuando el codigo de la tecla es igual a la tecla de la derecha
            
            xDirRaqueta = 5;
            System.out.println("derecha");
        }  
    }
    
    void teclaLiberada(KeyEvent e){
        
        int tecla = e.getKeyCode();
        
        if(tecla == KeyEvent.VK_LEFT){
            
            xDirRaqueta = 0;
        }
        
        if(tecla == KeyEvent.VK_RIGHT){
            
            xDirRaqueta = 0;
        }               
    }
    
    void movimientoRaqueta(){
        
        x += xDirRaqueta; //posicion de la raqueta, para el moviemiento hacia la derecha o izqueirda
        
        if(x <= 0){ //para que la raqueta no pase el borde de la izquierda de la ventana
            
            x = 0; 
        } 
        
        if(x >= Ventana.ANCHO_VENTANA - ANCHO_IMAGEN-15){ //para que la raqueta no pase el borde de la derecho de la ventana
            
            x = Ventana.ANCHO_VENTANA - ANCHO_IMAGEN-15;
        }
    }
    
    void restablecerEstado(){ //Se vuelve a la posición original de la raqueta
        
        x = 232;
    }
    
    void alargarRaqueta(){
        
        ANCHO_IMAGEN = 180;
    }
    
    public void setX(int x){
        
        this.x = x;
    }
    
    int getX(){
        
        return x;
    }
    
    Rectangle getRectangulo(){
        
        return new Rectangle(x, Y_RAQUETA, ANCHO_IMAGEN, ALTURA_IMAGEN - 20);
    }
}
