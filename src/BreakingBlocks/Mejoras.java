
package BreakingBlocks;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Mejoras {
    
    Image imagenMejoras, imagenSuelo, imagenBala;
    private int x;
    private int y;
    public static int ANCHO_IMAGEN;
    public static int ALTURA_IMAGEN;
    public static int ANCHO_IMAGEN_SUELO = 550;
    public static int ALTURA_IMAGEN_SUELO = 5;
    public static int ANCHO_IMAGEN_BALA = 5;
    public static int ALTURA_IMAGEN_BALA = 20;
    private boolean capsulaAbsorbida;
    private boolean formarSuelo;
    private boolean balaDestruida;
    private boolean pararBala;
    
    
    public Mejoras(int x, int y, int i){
        
        this.x = x;
        this.y = y;
        
        capsulaAbsorbida = false;
        cargarImagen(i);
        dimensionImagen(); 
    }
    
    public Mejoras(int y, int i){
        
        this.y = y;
        balaDestruida = false;
        pararBala = false; 
        cargarImagenesAdicionales(i);
    }
    
    public Mejoras(int i){
        
        capsulaAbsorbida = false;
        formarSuelo = false;
        cargarImagenesAdicionales(i);
    }
    private void cargarImagen(int i){
        
        if(i==77 || i==82 || i==115){
            BufferedImage imagenAR = null;
            try {
               imagenAR = ImageIO.read(new File("src/Imagenes/Capsula Alargar Raqueta.png"));
            } catch (IOException ex) {
               Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
            }
            imagenMejoras = imagenAR;
        }
        
        if(i==64 || i==70 || i==117){
            BufferedImage imagenBX2 = null;
            try {
               imagenBX2 = ImageIO.read(new File("src/Imagenes/Capsula Bolas x2.png"));
            } catch (IOException ex) {
               Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
            }
            imagenMejoras = imagenBX2;
        }
        
        if(i==90 || i==112 || i==129){
            BufferedImage imagenCapL = null;
            try {
               imagenCapL = ImageIO.read(new File("src/Imagenes/Capsula Suelo.png"));
            } catch (IOException ex) {
               Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
            }
            imagenMejoras = imagenCapL;
        }
        
        if(i==99){
            
            BufferedImage imagenCapD = null;
            try {
               imagenCapD = ImageIO.read(new File("src/Imagenes/Capsula de Disparos.png"));
            } catch (IOException ex) {
               Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
            }
            imagenMejoras = imagenCapD;
        }
    }
    
    Image getImagenMejoras(){
        
        return imagenMejoras;
    }
    
    void cargarImagenesAdicionales(int i){
        
        if(i==90 || i==112 || i==129){
            
            BufferedImage imagenS = null;
            try {
               imagenS = ImageIO.read(new File("src/Imagenes/Suelo.png"));
            } catch (IOException ex) {
               Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
            }
            imagenSuelo = imagenS;
        }
        
        if(i==99){
            
            BufferedImage imagenB = null;
            try {
               imagenB = ImageIO.read(new File("src/Imagenes/Bala.png"));
            } catch (IOException ex) {
               Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
            }
            imagenBala = imagenB;
        }
    }
    
    Image getImagenSuelo(){
        
        return imagenSuelo;
    }
    
    Image getImagenBala(){
        
        return imagenBala;
    }
   
    void movimientoCapsula(){
        
        this.y += 1; //Mueve la capsula hacia abajo
    }
    
    void movimientoBala(){
        
        this.y -=8; //Mueve la bala hacia arriba
    }
    
    private void dimensionImagen(){
        
        ANCHO_IMAGEN = 55;
        ALTURA_IMAGEN = 20;
    }
    
    void setCapsulaAbsorbida(boolean val){
        
        capsulaAbsorbida = val;
    }
    
    boolean esAbsorbida(){
        
        return capsulaAbsorbida;
    }
    
    void setFormarSuelo(boolean val){
        
        formarSuelo = val;
    }
    
    boolean sueloFormado(){
        
        return formarSuelo;
    }
    
    void setBalaDestruida(boolean val){
        
        balaDestruida = val;
    }
    
    boolean esDestruidaLaBala(){
        
        return balaDestruida;
    }
    void setPararBala(boolean val){
        
        pararBala = val;
    }
    
    boolean balaDetenida(){
        
        return pararBala;
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
    
    Rectangle getRectanguloSuelo(){
        
        return new Rectangle(0, Raqueta.Y_RAQUETA+45, ANCHO_IMAGEN_SUELO, ALTURA_IMAGEN_SUELO);
    }
    
    Rectangle getRectanguloBala(){
        
        return new Rectangle(x, y, ANCHO_IMAGEN_BALA, ALTURA_IMAGEN_BALA);
    }
}
