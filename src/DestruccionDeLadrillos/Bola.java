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
public class Bola {
    
    Image imagenBola;
    private int x;
    private int y;
    private int xDirBola;
    private int yDirBola;
    private int xVida;
    private int yVida;
    public static int ANCHO_IMAGEN;
    public static int ALTURA_IMAGEN;
    
    public Bola(){
        
        xDirBola = 2;
        yDirBola = -2;
        
        cargarImagen();
        dimensionImagen();
        restablecerEstado();
    }
    
    public Bola(int xVida, int yVida){
        
        this.xVida = xVida;
        this.yVida = yVida;
        
        cargarImagen();
        restablecerEstado();
        
    }
    
    private void cargarImagen(){
        
        BufferedImage imagenB = null;
        try {
            imagenB = ImageIO.read(new File("src/Imagenes/bola.png"));
        } catch (IOException ex) {
            Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
        }
        imagenBola = imagenB;  
    }
    
    Image getImagenBola(){
        
        return imagenBola;
    }
    
    private void dimensionImagen(){
        ANCHO_IMAGEN = 14;
        ALTURA_IMAGEN = 14;
    
    }
    
    void movimientoBola(){
        
        x += xDirBola; //posición x de la bola(da la apariencia de movimiento, tiene diferente opciones para moverse)
        y += yDirBola; //posición y de la bola(da la apariencia de movimiento, tiene diferente opciones para moverse)
        
        if(x <= 0){
            setXDir(-xDirBola); //cuando se choca con la pared izqueida para mover la bola hacia la derecha
        } 
        if(x >= Ventana.ANCHO_VENTANA - ANCHO_IMAGEN-15){
            setXDir(-xDirBola); //cuando se choca con la pared derecha para mover la bola hacia la izquierda
        }
        
        if(y <= 0){
            setYDir(-yDirBola); //cuando se choca con el techo para mover la bola hacia abajo
        }
    }
    
    void restablecerEstado(){ //se vuelve a la posición original de la bola
        
        x = 275;
        y = 536;     
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
    
     void setXDir(int xDirBola) {

        this.xDirBola = xDirBola;
    }

    void setYDir(int yDirBola) {

        this.yDirBola = yDirBola;
    }
    
    int getYDir(){
        return yDirBola;
    }
    
    int getXVida(){
        
        return xVida;
    }
    
    int getYVida(){
        
        return yVida;
    }
    
    Rectangle getRectangulo(){
        
        return new Rectangle(x, y, ANCHO_IMAGEN, ALTURA_IMAGEN);
    }
}
