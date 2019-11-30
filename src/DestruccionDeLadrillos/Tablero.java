/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DestruccionDeLadrillos;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 *
 * @author Norbil
 */
public class Tablero extends JPanel implements ActionListener {
    
    private Timer reloj;
    private String mensaje = "Game Over";
    private Bola bola;
    private Bola[] bola1;
    private Raqueta raqueta;
    private Ladrillos[] ladrillos;
    private boolean DentroDelJuego = true;
    private static int DELAY = 2;
    private static int BORDE_INFERIOR = 700;
    private int numeroDeVidas = 3;
    private boolean comenzandoMovimiento = false;
    
    public Tablero(){
    
        addKeyListener(new TAdapter()); //Escuchar teclado
        setFocusable(true); //Establecer la focalización
        setFocusTraversalKeysEnabled(false);//No establecer teclas de enfoque transversal
        inicializandoJuego();
    } 
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            raqueta.teclaPresionada(e);
            comenzandoMovimiento = true;
        }

        @Override
        public void keyReleased(KeyEvent e) {

            raqueta.teclaLiberada(e);
        }
    }
    
    private void inicializandoJuego(){
        
        this.ladrillos = new Ladrillos[Ladrillos.NUMERO_DE_LADRILLOS]; //Inicializando un arreglo de ladrillos
        this.bola = new Bola(); //Inicializando y creando una bola
        this.bola1 = new Bola[2];
        this.raqueta = new Raqueta(); //Inicializando y creando un raqueta
        
        
        //creando los ladrillos
        int a = 0;
        for(int i = 0; i < 7; i++){ 
            for(int j = 0; j < 10; j++){
                
                ladrillos[a] = new Ladrillos(j*55,i*20);
                a++;
            }
        }
        
        //creando las vidas 
        int b = 0;
        for(int i = 0; i < 1; i++){
            for(int j = 0; j <numeroDeVidas-1; j++){
                
                bola1[b] = new Bola(j*20+5,600);
                b++;
            }
        }
        
        reloj = new Timer(DELAY,this); //Inicializar temporizador,(más explicación!!!)
        reloj.start(); //Comenzar Temporizador
    }
    
    @Override
    public void paint(Graphics g){
        
        super.paint(g);
        var g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        if (DentroDelJuego) {

            dibujarObjetos(g2d);
        } else {

            juegoTerminado(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }
    
    private void dibujarObjetos(Graphics2D g2d){
        
        g2d.drawImage(bola.getImagenBola(), bola.getX(), bola.getY(), Bola.ANCHO_IMAGEN, 
                Bola.ALTURA_IMAGEN, this); //Se dibuja la bola
        
        //Se dibujan la bola o las bolas que representan las vidas
        for(int i = 0; i < 1; i++){ 
            for(int j = 0; j <numeroDeVidas-1; j++){
                g2d.drawImage(bola1[j].getImagenBola(), bola1[j].getXVida(), bola1[j].getYVida(), 
                        bola1[j].ANCHO_IMAGEN, bola1[j].ALTURA_IMAGEN, this);
            }
        }
        
        g2d.drawImage(raqueta.getImagenRaqueta(), raqueta.getX(), Raqueta.Y_RAQUETA, Raqueta.ANCHO_IMAGEN, 
                Raqueta.ALTURA_IMAGEN, this); //Se dibuja la raqueta
        
        //Se dibuja los ladrillos que no están destruidos
        for(int j=0; j< Ladrillos.NUMERO_DE_LADRILLOS; j++){
            
            if(!ladrillos[j].esDestruido()){
                g2d.drawImage(ladrillos[j].getImagenLadrillos(), ladrillos[j].getX(), ladrillos[j].getY(), 
                        ladrillos[j].ANCHO_IMAGEN, ladrillos[j].ALTURA_IMAGEN, this);
            }
        }
    }
    
    private void juegoTerminado(Graphics2D g2d){
        
        Font fuente = new Font("Verdana", Font.BOLD, 18); //Se define la fuente
        FontMetrics fontMetrics = this.getFontMetrics(fuente);

        g2d.setColor(Color.BLACK);
        g2d.setFont(fuente);
        g2d.drawString(mensaje,
                (Ventana.ANCHO_VENTANA - fontMetrics.stringWidth(mensaje)) / 2,
                 Ventana.ANCHO_VENTANA / 2);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) { //Ciclo del juego
        
        if(comenzandoMovimiento){
            bola.movimientoBola();
        }
        raqueta.movimientoRaqueta();
        
        vidas();
        comprobarColision();
        repaint();    
    }
    
    private void vidas(){
        
        if (bola.getRectangulo().getMaxY() > BORDE_INFERIOR) {
            
            bola.restablecerEstado();
            raqueta.restablecerEstado();
            comenzandoMovimiento = false;
            bola.setXDir(2);
            bola.setYDir(-2);
            numeroDeVidas -= 1;
            
            if(numeroDeVidas == 0){
                
                detenerJuego();
            }
        }    
    }

    private void comprobarColision() {

        //Verificamos cuantos ladrillos se destruyen
        for (int i = 0, j = 0; i < Ladrillos.NUMERO_DE_LADRILLOS; i++) {
            
            //Se almacena la cantidad de ladrillos destruidos en j
            if (ladrillos[i].esDestruido()) {

                j++;
            }
            
            //En el caso de que sea destruido todo los ladrillos, saldrá un mensaje de victoria y se dentendra el juego
            if (j == Ladrillos.NUMERO_DE_LADRILLOS) {

                mensaje = "Victoria";
                detenerJuego();
            }
        } 

        if ((bola.getRectangulo()).intersects(raqueta.getRectangulo())) {
            bola.setYDir(-2);
        }  
    
        for (int i = 0; i < Ladrillos.NUMERO_DE_LADRILLOS; i++) {

            if ((bola.getRectangulo()).intersects(ladrillos[i].getRectangulo())) {

                int posicionXBola = (int) bola.getRectangulo().getMinX(); //posicion 'x' actual de la bola
                int posicionYBola = (int) bola.getRectangulo().getMinY(); //posicion 'y' actual de la bola
                int posicionXladrillo = (int) ladrillos[i].getRectangulo().getMinX(); //posicion 'x' actual del ladrillos i+1
                int posicionYladrillo = (int) ladrillos[i].getRectangulo().getMinY(); //posicion 'y' actual del ladrillos i+1

                //Cambia la direcion la bola al colisionar con los ladrillos
                if (!ladrillos[i].esDestruido()) {
                    
                    if (posicionYBola + Bola.ALTURA_IMAGEN + 2== ladrillos[i].getY()
                            && posicionXladrillo <= posicionXBola
                            && posicionXBola <= posicionXladrillo + Ladrillos.ANCHO_IMAGEN - 7) { //Para el lado superior del ladrillo
                        
                         bola.setYDir(-2);
                         
                    }else if (posicionYBola == posicionYladrillo + Ladrillos.ALTURA_IMAGEN-2
                            && posicionXladrillo <= posicionXBola
                            && posicionXBola <= posicionXladrillo + Ladrillos.ANCHO_IMAGEN - 7) { //Para el lado inferior del ladrillo
                        
                         bola.setYDir(2);
                    }else if (posicionXBola == posicionXladrillo + Ladrillos.ANCHO_IMAGEN -2
                            && posicionYladrillo <= posicionYBola 
                            && posicionYBola <= posicionYladrillo + Ladrillos.ALTURA_IMAGEN) { //Para el lado derecho del ladrillo
                        
                         bola.setXDir(2);
                         
                    }else if (posicionXBola + Bola.ANCHO_IMAGEN +2 == posicionXladrillo 
                            && posicionYladrillo <= posicionYBola 
                            && posicionYBola <= posicionYladrillo + Ladrillos.ALTURA_IMAGEN) { //Para el lado izquierdo del ladrillo
                        
                         bola.setXDir(-2);
                    }

                    ladrillos[i].setDestruido(true);
                }                                
            }            
        }       
    }
    
    private void detenerJuego() {

        DentroDelJuego = false;
        reloj.stop();
    }
}