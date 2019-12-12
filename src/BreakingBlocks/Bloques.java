
package BreakingBlocks;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Bloques {

    Image imagenBloques;
    private int x;
    private int y;
    public static int ANCHO_IMAGEN;
    public static int ALTURA_IMAGEN;
    public static int NUMERO_DE_BLOQUES = 150;
    private boolean destruido;

    public Bloques(int x, int y, int i){
        
        this.x = x;
        this.y = y;
        
        destruido=false;
        dimensionImagen();
        cargarImagen(i); 
    }  

    private void cargarImagen(int i){
        
        if((i>=41 && i<=43) || (i>=46 && i<=48) || i==51 || i==58 || i==61 || i==68 || i==71 ||  i==78 ||
                i==101 || i==108 || i==111 || i==118 || i==121 || i==128){
            
            BufferedImage imagenBM = null;
            try {
                imagenBM = ImageIO.read(new File("src/Imagenes/Bloque Morado.png"));
            } catch (IOException ex) {
                Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
            }
            imagenBloques = imagenBM;      
        }
        
        if((i>=30 && i<=32) || (i>=37 && i<=39) || i==40 || i==49 || i==50 || i==59 || i==60 || i==69 || i==110 ||
                i==119 || i==120 || i==129 || i==130 || i==131 || i==138 || i==139){
            
            BufferedImage imagenBR = null;
            try {
                imagenBR = ImageIO.read(new File("src/Imagenes/Bloque Rojo.png"));
            } catch (IOException ex) {
                Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
            }
            imagenBloques = imagenBR;
        }
        
        if((i>=62 && i<=67) || i==70 || i==79 || i==80 || i==81 || i==88 || i==89 || i==90 || i==91 
                || i==98 || i==99 || i==100 || i==109 || (i>=143 && i<=146)){
            
            BufferedImage imagenBA = null;
            try {
                imagenBA = ImageIO.read(new File("src/Imagenes/Bloque Amarillo.png"));
            } catch (IOException ex) {
                Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
            }
            imagenBloques = imagenBA;
        }
        
        if((i>=33 && i<=36) || i==44 || i==45 || i==54 || i==55 || (i>=72 && i<=77) || i==104 || i==105 ||
                i==114 || i==115 || i==124  || i==125){
            
            BufferedImage imagenBV = null;
            try {
                imagenBV = ImageIO.read(new File("src/Imagenes/Bloque Verde.png"));
            } catch (IOException ex) {
                Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
            }
            imagenBloques = imagenBV;
        }
        
        if((i>=82 && i<=87) || i==92 || i==97 || i==102 || i==107 || i==112 || i==117 
                || i==122 || i==127 || (i>=132 && i<= 137) || (i>=140 && i<=142) || (i>=147 && i<=149)){
            
            BufferedImage imagenBC = null;
               try {
            imagenBC = ImageIO.read(new File("src/Imagenes/Bloque Celeste.png"));
               } catch (IOException ex) {
            Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
            }
            imagenBloques = imagenBC;
        }
        
        if(i==52 || i==53 || i==56 || i==57 || (i>=93 && i<=96) || i==103 || i==106 
                || i==113 || i==116 || i==123 || i==126){
        
            BufferedImage imagenBI = null;
               try {
            imagenBI = ImageIO.read(new File("src/Imagenes/Bloque Irrompible.png"));
               } catch (IOException ex) {
            Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
            }
            imagenBloques = imagenBI;
        }
    }
    
    Image getImagenBloques(){
        
        return imagenBloques;
    }
    void dimensionImagen(){
        
        ANCHO_IMAGEN = 55;
        ALTURA_IMAGEN = 20;
    }
    
    void setDestruido(boolean val){
        
        destruido = val;
    }
    
    boolean esDestruido(){
        
        return destruido;
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
