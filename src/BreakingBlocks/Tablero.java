
package BreakingBlocks;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;

public class Tablero extends JPanel implements ActionListener{
    
    Image imagenFondoJuego, imagenFondoOscuro, imagenFondoBarra, imagenCorazonDeVidas, imagenPausa, 
            imagenFondoPausa, imagenBotonReanudar,imagenBotonReinicio, imagenBotonRegrInic;
    private Timer reloj;
    private String mensaje = "Game Over :(";
    private Bola[] bola, vidas;
    private Raqueta raqueta;
    private Techo techo;
    private Bloques[] bloques;
    private Mejoras[] mejoras, imagenesM;
    private Mejoras bala1, bala2;
    private boolean dentroDelJuego = true;
    public static int DELAY = 10;
    private static int BORDE_INFERIOR = 700;
    private int numeroDeVidas = 3;
    private boolean comenzandoMovimiento = false;
    private boolean bolaDuplicada = false;
    private int numeroDeBalas1;
    private int numeroDeBalas2;
    private int IniciaAlagarRaqueta = 0;
    private int sueloInicia = 0;
    public static int N_MILISEGUNDOS = 5000;
    private JFrame c = null;
    private boolean ponerPausa = false;
    private boolean juegoFinalizado = false;
    private int puntaje = 0;
    private String strPuntaje = "0";
    
    public Tablero(JFrame j){
        c = j;
        addKeyListener(new TAdapter()); //Escuchar teclado
        setFocusable(true); //Establecer la focalización
        setFocusTraversalKeysEnabled(false);//No establecer teclas de enfoque transversal
        cargarImagenesDeFondo();
        inicializandoJuego();
        addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent me) { 
            
                int x = me.getX();
                int y = me.getY();
                //El juego se pone en pausa y aparece el menu para selecionar tre opciones
                if (x>=500 && x <=535 && y>=9 && y<=44 && reloj.isRunning()){
                    
                    ponerPausa = true;
                    dentroDelJuego = false;
                    repaint();
                    reloj.stop();
                }
                //Activando el botón de reiniciar juego
                if((ponerPausa && x>=230 && x <=330 && y>=160 && y<=260 && !reloj.isRunning()) ||
                        (juegoFinalizado && x>=230 && x <=330 && y>=320 && y<=420 && !reloj.isRunning())){

                    ponerPausa = false;
                    dentroDelJuego = true;
                    comenzandoMovimiento = false;
                    numeroDeVidas = 3;
                    juegoFinalizado = false;
                    puntaje = 0;
                    strPuntaje = "0";
                    inicializandoJuego();
                    reloj.start();
                }
                //Activando el botón de reaundar juego
                if(ponerPausa && x>=230 && x <=330 && y>=280 && y<=380 && !reloj.isRunning() ){

                    ponerPausa = false;
                    dentroDelJuego = true;
                    reloj.start();
                }  
                //Activando el botón regresar al inicio 
                if((ponerPausa && x>=230 && x <=330 && y>=400 && y<=500) || (juegoFinalizado 
                        && x>=230 && x <=330 && y>=450 && y<=550 && !reloj.isRunning())){
                    reloj.stop();
                    Inicio inic = new Inicio();
                    inic.setVisible(true);
                    c.dispose();    
                }
            } 
        }); 
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
    
    //Cargar el fondo del juego
    private void cargarImagenesDeFondo(){
        
        BufferedImage imagenF = null;
        try {
            imagenF = ImageIO.read(new File("src/Imagenes/Fondo Juego.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
        }
        imagenFondoJuego = imagenF;  
        
        BufferedImage imagenFO = null;
        try {
            imagenFO = ImageIO.read(new File("src/Imagenes/Fondo Oscuro.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
        }
        imagenFondoOscuro = imagenFO;  
        
        BufferedImage imagenCorazon = null;
        try {
            imagenCorazon = ImageIO.read(new File("src/Imagenes/Corazón de Vidas.png"));
        } catch (IOException ex) {
            Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
        }
        imagenCorazonDeVidas = imagenCorazon;  
        
        BufferedImage imagenP = null;
        try {
            imagenP = ImageIO.read(new File("src/Imagenes/Botón Pausa.png"));
        } catch (IOException ex) {
            Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
        }
        imagenPausa = imagenP; 
        
        BufferedImage imagenFondoJ = null;
        try {
            imagenFondoJ  = ImageIO.read(new File("src/Imagenes/Fondo Pausa.png"));
        } catch (IOException ex) {
            Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
        }
        imagenFondoPausa = imagenFondoJ; 
        
        BufferedImage imagenReinicio = null;
        try {
            imagenReinicio = ImageIO.read(new File("src/Imagenes/Botón Reinicio.png"));
        } catch (IOException ex) {
            Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
        }
        imagenBotonReinicio = imagenReinicio; 
        
        BufferedImage imagenReanudar = null;
        try {
            imagenReanudar = ImageIO.read(new File("src/Imagenes/Botón Reanudar.png"));
        } catch (IOException ex) {
            Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
        }
        imagenBotonReanudar = imagenReanudar;

        BufferedImage imagenBotReIn = null;
        try {
            imagenBotReIn = ImageIO.read(new File("src/Imagenes/Botón Regresar Inicio.png"));
        } catch (IOException ex) {
            Logger.getLogger(Bola.class.getName()).log(Level.SEVERE, null, ex);
        }
        imagenBotonRegrInic = imagenBotReIn;          
    }
    
    private void inicializandoJuego(){
        
        this.bloques = new Bloques[Bloques.NUMERO_DE_BLOQUES]; //Inicializando un arreglo de bloques
        this.bola = new Bola[2]; //Inicializando dos bolas
        this.raqueta = new Raqueta(); //Inicializando y creando un raqueta
        this.techo = new Techo(); //Inicializando y creando el techo
        this.mejoras = new Mejoras[130];
        this.imagenesM = new Mejoras[130];
        
        //Creando bola original
        bola[0] = new Bola();
        
        //Creando una copia de la bola original, pero moviendose en otro sentido
        bola[1] = new Bola();
        
        //Creando los bloques
        int a = 0;
        for(int i = 0; i < 15; i++){ 
            for(int j = 0; j < 10; j++){
                
                if(a>=0 && a<=29){
                    
                    bloques[a] = null;
                }
                if(a>=30 && a<=149){
                    
                    bloques[a] = new Bloques(j*55,i*20,a);
                }
                a++;
            }
        }
        //Creando las mejoras
        int c = 0;
        for(int i = 0; i < 13; i++){
            for(int j = 0; j <10; j++){
                
                if((c>=0 && c<=63) || (c>=65 && c<=69) || (c>=71 && c<=76) || (c>=78 && c<=82) || (c>=84 && c<=89) || 
                        (c>=91 && c<=98) || (c>=100 && c<=111) || (c>=113 && c<=114) || c==116 || (c>=118 && c<=128)){
                    
                    mejoras[c] = null;
                    imagenesM[c] = null;
                    
                }else if(c==64 || c==70 || c==77 || c==83 || c==90 || c==99 || c==112 || c==115 || c==117 || c==129){
                    
                    mejoras[c] = new Mejoras(j*55,i*20,c);
                    if(c==90 || c==112 || c==129){
                        //Creando el dibujo del suelo
                        imagenesM[c] = new Mejoras(c);
                    }else{
                        imagenesM[c] = null;
                    }
                    
                    if(c==99){
                        //Creando los dibujos de las balas 
                        this.bala1 = new Mejoras(Raqueta.Y_RAQUETA, c);
                        this.bala2 = new Mejoras(Raqueta.Y_RAQUETA, c);
                    }    
                }
                c++;
            }
        }
        
        reloj = new Timer(DELAY,this); //Inicializar temporizador
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

        if (dentroDelJuego) {

            dibujarObjetos(g2d);
        }else if(ponerPausa){
            
            menuDePausa(g2d);
        }else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
            }
            juegoTerminado(g2d);
        }
    }
    
    private void dibujarObjetos(Graphics2D g2d){
        
        //Se dibuja el fondo principal del juego
        g2d.drawImage(imagenFondoJuego, 0, 60, Ventana.ANCHO_VENTANA-10, Ventana.ALTURA_VENTANA-80, this);
        
        //Se dibuja el fondo Oscuro y secundario del juego
        g2d.drawImage(imagenFondoOscuro, 0, 0, Ventana.ANCHO_VENTANA-10, 60, this);
        
        //Se dibuja los corazones de las vidas
        if(numeroDeVidas == 3 || numeroDeVidas == 2 || numeroDeVidas == 1){
            g2d.drawImage(imagenCorazonDeVidas, 90, 13, 25, 25, this);
        }
        if(numeroDeVidas == 3 || numeroDeVidas == 2){
            g2d.drawImage(imagenCorazonDeVidas, 125, 13, 25, 25, this);
        }
        if(numeroDeVidas == 3){
            g2d.drawImage(imagenCorazonDeVidas, 160, 13, 25, 25, this);
        }
        
        //Se dibuja las palabras de "Score:" y "Vidas"
        Font fuente = new Font("Verdana", Font.BOLD, 20); //Se define la fuente
        FontMetrics fontMetrics = this.getFontMetrics(fuente);

        g2d.setColor(Color.WHITE);
        g2d.setFont(fuente);
        g2d.drawString("Puntaje:",250,35);
        g2d.drawString("Vidas:",15,35);
        
        //Se dibuja la cantidad de puntaje
        g2d.setColor(Color.YELLOW);
        g2d.drawString(strPuntaje,360,35);
        
        //Se dibuja la imagen de pausa
        g2d.drawImage(imagenPausa, 500, 9, 35, 35, this);
        
        //Se dibuja el fondo metalico
        g2d.drawImage(techo.getImagenFondoMetalico(), 0, 53, 565, 7, this);
        
        //Se dibuja la bola original
        g2d.drawImage(bola[0].getImagenBola(), bola[0].getX(), bola[0].getY(), 
                Bola.ANCHO_IMAGEN, Bola.ALTURA_IMAGEN, this);
        
        //Se dibuja una copia de la bola original
        if(bolaDuplicada == true){
            g2d.drawImage(bola[1].getImagenBola(), bola[1].getX(), bola[1].getY(), 
                    Bola.ANCHO_IMAGEN, Bola.ALTURA_IMAGEN, this);
        }      
        //Se dibuja la raqueta
        g2d.drawImage(raqueta.getImagenRaqueta(), raqueta.getX(), Raqueta.Y_RAQUETA, 
                Raqueta.ANCHO_IMAGEN, Raqueta.ALTURA_IMAGEN, this);
        
        //Se dibuja los bloques que no están destruidos
        for(int i=30; i< Bloques.NUMERO_DE_BLOQUES; i++){
            
            if(!bloques[i].esDestruido()){
                g2d.drawImage(bloques[i].getImagenBloques(), bloques[i].getX(), bloques[i].getY(), 
                        bloques[i].ANCHO_IMAGEN, bloques[i].ALTURA_IMAGEN, this);
            }
        }       
        //Se dibuja las capsulas de mejora que no han sido absorbidas por la raqueta
        for(int i=64; i<130; i++){
            if(bloques[i].esDestruido() && (i==64 || i==70 || i==77 || i==83 || i==90 || i==99 || i==112 
                    || i==115 || i==117 || i==129) && !mejoras[i].esAbsorbida()){
                g2d.drawImage(mejoras[i].getImagenMejoras(), mejoras[i].getX(), mejoras[i].getY(), 
                        mejoras[i].ANCHO_IMAGEN, mejoras[i].ALTURA_IMAGEN, this);
            }
        }      
        //Dibujando el suelo
        for(int i=90; i<130; i++){
            if((i==90 || i==112 || i==129) && mejoras[i].esAbsorbida() && mejoras[i].sueloFormado()){
                
                g2d.drawImage(imagenesM[i].getImagenSuelo(), 0, Raqueta.Y_RAQUETA+45, 
                        imagenesM[i].ANCHO_IMAGEN_SUELO, imagenesM[i].ALTURA_IMAGEN_SUELO, this);
            }
        }
        //Creando el dibujo de las balas
        if(mejoras[99].esAbsorbida()){
            if(!bala1.esDestruidaLaBala()){
                g2d.drawImage(bala1.getImagenBala(), bala1.getX(), bala1.getY(), 
                        bala1.ANCHO_IMAGEN_BALA, bala1.ALTURA_IMAGEN_BALA, this);
            }
            if(!bala2.esDestruidaLaBala()){
                g2d.drawImage(bala2.getImagenBala(), bala2.getX(), bala2.getY(), 
                        bala2.ANCHO_IMAGEN_BALA, bala2.ALTURA_IMAGEN_BALA, this);
            }
        }
    }
    
    private void menuDePausa(Graphics2D g2d){
        
        g2d.drawImage(imagenFondoPausa, 0, 0, Ventana.ANCHO_VENTANA, Ventana.ALTURA_VENTANA, this);
        g2d.drawImage(imagenBotonReinicio, 230, 160, 100, 100, this);
        g2d.drawImage(imagenBotonReanudar, 230, 280, 100, 100, this);
        g2d.drawImage(imagenBotonRegrInic, 230, 400, 100, 100, this);
    }
    
    private void juegoTerminado(Graphics2D g2d){
        
        Font fuente = new Font("Verdana", Font.BOLD, 50); //Se define la fuente
        FontMetrics fontMetrics = this.getFontMetrics(fuente);

        g2d.drawImage(imagenFondoPausa, 0, 0, Ventana.ANCHO_VENTANA, Ventana.ALTURA_VENTANA, this);
        g2d.drawImage(imagenBotonReinicio, 230, 360, 100, 100, this);
        g2d.drawImage(imagenBotonRegrInic, 230, 490, 100, 100, this);
        
        g2d.setColor(Color.RED);
        g2d.setFont(fuente);
        g2d.drawString(mensaje,110,200);
        
        Font fuente2 = new Font("Verdana", Font.BOLD, 30); //Se define la fuente
        FontMetrics fontMetrics2 = this.getFontMetrics(fuente2);
        g2d.setFont(fuente2);
        g2d.setColor(Color.YELLOW);
        g2d.drawString("Puntuje: "+strPuntaje,170,300);
        
        juegoFinalizado = true;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) { //Ciclo del juego

        //Movimiento de la bola original
        if(comenzandoMovimiento){
            
            bola[0].movimientoBola();
        }
        //Movimiento de la copia de la bola original
        if(bolaDuplicada == true){
            
            bola[1].movimientoBola();
        }
        
        raqueta.movimientoRaqueta();
        
        //Movimiento para la capsula de alagar la raqueta
        if(bloques[77].esDestruido() && !mejoras[77].esAbsorbida()){

            mejoras[77].movimientoCapsula();
        }

        if(bloques[83].esDestruido() && !mejoras[83].esAbsorbida()){

            mejoras[83].movimientoCapsula();
        }
        
        if(bloques[115].esDestruido() && !mejoras[115].esAbsorbida()){

            mejoras[115].movimientoCapsula();
        }

        //Movimiento para la capsula de la bola x2
        if(bloques[64].esDestruido() && !mejoras[64].esAbsorbida()){

            mejoras[64].movimientoCapsula();
        }

        if(bloques[70].esDestruido() && !mejoras[70].esAbsorbida()){

            mejoras[70].movimientoCapsula();
        }
        
        if(bloques[117].esDestruido() && !mejoras[117].esAbsorbida()){

            mejoras[117].movimientoCapsula();
        }

        //Movimiento para la capsula de capsula suelo
        if(bloques[90].esDestruido() && !mejoras[90].esAbsorbida()){

            mejoras[90].movimientoCapsula();
        }

        if(bloques[112].esDestruido() && !mejoras[112].esAbsorbida()){

            mejoras[112].movimientoCapsula();
        }
        
        if(bloques[129].esDestruido() && !mejoras[129].esAbsorbida()){

            mejoras[129].movimientoCapsula();
        }

        //Movimiento para la capsula de disparos
        if(bloques[99].esDestruido() && !mejoras[99].esAbsorbida()){

            mejoras[99].movimientoCapsula();
        }

        //Movimiento del par de balas
        if(mejoras[99].esAbsorbida() && !bala1.balaDetenida()){

            bala1.movimientoBala();
        }    
        if(mejoras[99].esAbsorbida() && !bala2.balaDetenida()){
            
            bala2.movimientoBala(); 
        }

        //Contar tiempo para el alargamiento de la raqueta
        if (IniciaAlagarRaqueta > 0){
            
            IniciaAlagarRaqueta += DELAY;
        }        
        
        //Contar tiempo para suelo
        for(int i=90; i<130; i++){
            if (sueloInicia > 0 && (i==90 || i==112 || i==129) && mejoras[i].sueloFormado()){

                sueloInicia += DELAY;
            }
        }
        vidas();
        comprobarColision();
        repaint();  
    }
    
    private void vidas(){

        Boolean pierdeBola = false; 
        if (bola[0].getRectangulo().getMaxY() > BORDE_INFERIOR) { 
            if (bolaDuplicada) {
                if (bola[1].getRectangulo().getMaxY() > BORDE_INFERIOR){
                    bolaDuplicada = false;
                    bola[1].setBolaDesaparecida(true);
                    pierdeBola = true;

                }else{

                    bolaDuplicada = false;
                    bola[1].setBolaDesaparecida(true);
                    bola[0].setX(bola[1].getX());
                    bola[0].setY(bola[1].getY());                    
                    bola[0].setXDir(bola[1].getXDir());
                    bola[0].setYDir(bola[1].getYDir());
                }
            }else{
                pierdeBola = true;
            }
        }

        if (pierdeBola){

            bola[0].setX(raqueta.getX()+ raqueta.ANCHO_IMAGEN/2 - bola[0].ANCHO_IMAGEN/2);//La bola se posiciona en medio de la raqueta 
            bola[0].setY(raqueta.Y_RAQUETA - bola[0].ALTURA_IMAGEN);// La  bola se posiciona por encima de la raqueta
            comenzandoMovimiento = false;
            bola[0].setXDir(4);
            bola[0].setYDir(-4);
            numeroDeVidas -= 1;

            if (numeroDeVidas == 0){

                detenerJuego();
            }
        }
    }

    private void comprobarColision() { 
        
        //Interseccion de la bola con la raqueta, cambia la direccion de la bola
        for(int i = 0; i < 2; i++){
           if ((bola[i].getRectangulo()).intersects(raqueta.getRectangulo())) {

                if (bola[i].getY() + Bola.ALTURA_IMAGEN >= Raqueta.Y_RAQUETA
                        && bola[i].getY() + Bola.ALTURA_IMAGEN <= Raqueta.Y_RAQUETA + 4
                        && raqueta.getX() - Bola.ANCHO_IMAGEN-2 <= bola[i].getX()
                        && bola[i].getX() <= raqueta.getX() + Raqueta.ANCHO_IMAGEN) { //Para el lado superior de la raqueta

                     bola[i].setYDir(-4);

                }else if (bola[i].getY() <= Raqueta.Y_RAQUETA + Raqueta.ALTURA_IMAGEN/2
                        && bola[i].getY() >= Raqueta.Y_RAQUETA + Raqueta.ALTURA_IMAGEN/2 - 4
                        && raqueta.getX() - Bola.ANCHO_IMAGEN-2 <= bola[i].getX()
                        && bola[i].getX() <= raqueta.getX() + Raqueta.ANCHO_IMAGEN) { //Para el lado inferior de la raqueta

                     bola[i].setYDir(4);

                }else if (bola[i].getX() <= raqueta.getX() + Raqueta.ANCHO_IMAGEN
                        && bola[i].getX() >= raqueta.getX() + Raqueta.ANCHO_IMAGEN -4
                        && Raqueta.Y_RAQUETA - Bola.ALTURA_IMAGEN-2 <= bola[i].getY()
                        && bola[i].getY() <= Raqueta.Y_RAQUETA + Raqueta.ALTURA_IMAGEN/2) { //Para el lado derecho de la raqueta

                     bola[i].setXDir(4);

                }else if (bola[i].getX() + Bola.ANCHO_IMAGEN - 1 >= raqueta.getX()
                        && bola[i].getX() + Bola.ANCHO_IMAGEN - 1 <= raqueta.getX() +3
                        && Raqueta.Y_RAQUETA - Bola.ALTURA_IMAGEN-2 <= bola[i].getY() 
                        && bola[i].getY() <= Raqueta.Y_RAQUETA+ Raqueta.ALTURA_IMAGEN/2) { //Para el lado izquierdo de la raqueta

                     bola[i].setXDir(-4);
                }
            }
        }
        
        //Interseccion de la bola con el techo, cambia la direccion de la bola
        for(int i = 0; i < 2; i++){
           if ((bola[i].getRectangulo()).intersects(techo.getRectangulo())) {
            
                bola[i].setYDir(4);
            }
        }
    
        for (int i = 30; i < Bloques.NUMERO_DE_BLOQUES; i++) {
            for(int j = 0; j < 2; j++){
                //Cuando se intersectan la bola con los bloques
                if ((bola[j].getRectangulo()).intersects(bloques[i].getRectangulo())) {
                    
                    //Cambia la direcion la bola al colisionar con los bloques
                    if (!bloques[i].esDestruido()){
                        
                        if (bola[j].getY() + Bola.ALTURA_IMAGEN >= bloques[i].getY()
                                && bola[j].getY() + Bola.ALTURA_IMAGEN <= bloques[i].getY() + 4
                                && bloques[i].getX() - Bola.ANCHO_IMAGEN-2 <= bola[j].getX()
                                && bola[j].getX() <= bloques[i].getX() + Bloques.ANCHO_IMAGEN) { //Para el lado superior del bloque

                             bola[j].setYDir(-4);

                        }else if (bola[j].getY() <= bloques[i].getY() + Bloques.ALTURA_IMAGEN
                                && bola[j].getY() >= bloques[i].getY() + Bloques.ALTURA_IMAGEN - 4
                                && bloques[i].getX() - Bola.ANCHO_IMAGEN-2 <= bola[j].getX()
                                && bola[j].getX() <= bloques[i].getX() + Bloques.ANCHO_IMAGEN) { //Para el lado inferior del bloque

                             bola[j].setYDir(4);
                        }else if (bola[j].getX() <= bloques[i].getX() + Bloques.ANCHO_IMAGEN
                                && bola[j].getX() >= bloques[i].getX() + Bloques.ANCHO_IMAGEN -4
                                && bloques[i].getY() - Bola.ALTURA_IMAGEN-2 <= bola[j].getY() 
                                && bola[j].getY() <= bloques[i].getY() + Bloques.ALTURA_IMAGEN) { //Para el lado derecho del bloque

                             bola[j].setXDir(4);

                        }else if (bola[j].getX() + Bola.ANCHO_IMAGEN >= bloques[i].getX()
                                && bola[j].getX() + Bola.ANCHO_IMAGEN <= bloques[i].getX() + 4
                                && bloques[i].getY() - Bola.ALTURA_IMAGEN-2 <= bola[j].getY() 
                                && bola[j].getY() <= bloques[i].getY()+ Bloques.ALTURA_IMAGEN) { //Para el lado izquierdo del bloque

                             bola[j].setXDir(-4);
                        }

                        //Se establece que bloques se destruyen
                        if ((i>=30 && i<=51) || i==54 || i==55 || (i>=58 && i<=92) || (i>=97 && i<=102) || i==104
                                || i==105 || (i>=107 && i<=112) || i==114 || i==115 || (i>=117 && i<=122) || i==124
                                || i==125 || (i>=127 && i<=149)){

                            bloques[i].setDestruido(true);
                            puntaje += 100;
                            strPuntaje = String.valueOf(puntaje);
                        }
                    }                                
                }
            }
            
            if(i==77 || i==83 || i==115){
                //Cuando se interceptan la capsula de mejora con la raqueta, esta ultima obtiene la mejora de alargarse
                if((raqueta.getRectangulo()).intersects(mejoras[i].getRectangulo()) && IniciaAlagarRaqueta==0 && 
                        !mejoras[i].esAbsorbida()){
            
                    raqueta.alargarRaqueta();
                    mejoras[i].setCapsulaAbsorbida(true);
                    IniciaAlagarRaqueta = 1;
                }
                
                if((raqueta.getRectangulo()).intersects(mejoras[i].getRectangulo()) && IniciaAlagarRaqueta>0 && 
                        !mejoras[i].esAbsorbida()){
                    
                    mejoras[i].setCapsulaAbsorbida(true);
                    IniciaAlagarRaqueta = 1;
                }
                if(IniciaAlagarRaqueta > N_MILISEGUNDOS && mejoras[i].esAbsorbida()){
                    
                    IniciaAlagarRaqueta = 0;
                    raqueta.dimensionImagen();
                }
            }
            
            if(i==64 || i==70 || i==117){
                //Al interceptarse la capsula con la raqueta se duplican la bola
                if((raqueta.getRectangulo()).intersects(mejoras[i].getRectangulo()) && !bolaDuplicada && 
                        !mejoras[i].esAbsorbida()){
                    
                    bolaDuplicada = true;
                    bola[1].setBolaDesaparecida(false);
                    bola[1].setX(bola[0].getX());
                    bola[1].setY(bola[0].getY());                    
                    bola[1].setXDir(-bola[0].getXDir());
                    bola[1].setYDir(bola[0].getYDir());
                    mejoras[i].setCapsulaAbsorbida(true);
                }
                if((raqueta.getRectangulo()).intersects(mejoras[i].getRectangulo()) && bolaDuplicada && 
                        !mejoras[i].esAbsorbida() && !bola[1].getBolaDesaparecida()){
                    
                    mejoras[i].setCapsulaAbsorbida(true);
                }
            }
            
            if(i==90 || i==112 || i==129){
                //La raqueta absobe la capsula de mejora del suelo al interceptarse
                if((raqueta.getRectangulo()).intersects(mejoras[i].getRectangulo()) && sueloInicia == 0 && 
                        !mejoras[i].esAbsorbida()){
                    
                    mejoras[i].setCapsulaAbsorbida(true);
                    mejoras[i].setFormarSuelo(true);
                    sueloInicia = 1;
                }
                if((raqueta.getRectangulo()).intersects(mejoras[i].getRectangulo()) && sueloInicia > 0 && 
                        !mejoras[i].esAbsorbida()){
                    
                    if(i==90){
                        mejoras[i].setCapsulaAbsorbida(true);
                        mejoras[i].setFormarSuelo(true);
                        mejoras[112].setFormarSuelo(false);
                        mejoras[129].setFormarSuelo(false);
                        sueloInicia = 1;
                    }
                    if(i==112){
                        mejoras[i].setCapsulaAbsorbida(true);
                        mejoras[i].setFormarSuelo(true);
                        mejoras[90].setFormarSuelo(false);
                        mejoras[129].setFormarSuelo(false);
                        sueloInicia = 1;
                    }
                    if(i==129){
                        mejoras[i].setCapsulaAbsorbida(true);
                        mejoras[i].setFormarSuelo(true);
                        mejoras[90].setFormarSuelo(false);
                        mejoras[112].setFormarSuelo(false);
                        sueloInicia = 1;
                    }
                }                
                if (sueloInicia > N_MILISEGUNDOS && mejoras[i].esAbsorbida() && mejoras[i].sueloFormado()){
                    
                    sueloInicia = 0;
                    mejoras[i].setFormarSuelo(false);
                }
                //La bola rebota al interceptarse esta con el suelo
                if(mejoras[i].sueloFormado()){
                    for(int j = 0; j < 2; j++){
                        if ((bola[j].getRectangulo()).intersects(imagenesM[i].getRectanguloSuelo())) {
            
                            bola[j].setYDir(-4);
                        }
                    }
                }
            }
            //Se obtiene la mejoras de los disparos cuando se intercepata la capsula de mejora con la raqueta
            if((raqueta.getRectangulo()).intersects(mejoras[99].getRectangulo()) && !mejoras[99].esAbsorbida()){

                bala1.setX(raqueta.getX()+10);
                bala2.setX(raqueta.getX()+raqueta.ANCHO_IMAGEN-15);
                mejoras[99].setCapsulaAbsorbida(true);
                numeroDeBalas1 = 1;
                numeroDeBalas2 = 1;
            }
            /*Cuando la bala1 o bala2 se intercepta en los bloques o en el techo, se destruyen como 
            tambien los bloques, y las balas se dibujan de nuevo a la altura de la raqueta*/
            if(mejoras[99].esAbsorbida()==true){
                if(((((bala1.getRectanguloBala()).intersects(bloques[i].getRectangulo()) 
                        && bloques[i].esDestruido()==false)) || bala1.getY()<=60)){

                    if (((i>=30 && i<=51) || i==54 || i==55 || (i>=58 && i<=92) || (i>=97 && i<=102) || i==104
                                || i==105 || (i>=107 && i<=112) || i==114 || i==115 || (i>=117 && i<=122) || i==124
                                || i==125 || (i>=127 && i<=149)) && bala1.getY()>60 && numeroDeBalas1<=20){

                        bloques[i].setDestruido(true);
                        puntaje += 100;
                        strPuntaje = String.valueOf(puntaje);
                    }
                    if(numeroDeBalas1==20){
                        
                        bala1.setBalaDestruida(true);
                        bala1.setPararBala(true);
                    }
                    if(numeroDeBalas1 < 20){
                        
                        bala1.setX(raqueta.getX()+10);
                        bala1.setY(Raqueta.Y_RAQUETA);
                        numeroDeBalas1 += 1;
                    }
                }

                if(((((bala2.getRectanguloBala()).intersects(bloques[i].getRectangulo()) 
                        && bloques[i].esDestruido()==false)) || bala2.getY()<=60)){

                    if (((i>=30 && i<=51) || i==54 || i==55 || (i>=58 && i<=92) || (i>=97 && i<=102) || i==104
                                || i==105 || (i>=107 && i<=112) || i==114 || i==115 || (i>=117 && i<=122) || i==124
                                || i==125 || (i>=127 && i<=149)) && bala2.getY()>60 && numeroDeBalas2<=20){

                        bloques[i].setDestruido(true);
                        strPuntaje = String.valueOf(puntaje);
                        puntaje += 100;
                    }
                    if(numeroDeBalas2==20){
                        
                        bala2.setBalaDestruida(true);
                        bala2.setPararBala(true);
                        
                    } 
                    if(numeroDeBalas2 < 20){
                        
                        bala2.setX(raqueta.getX()+raqueta.ANCHO_IMAGEN-15);
                        bala2.setY(Raqueta.Y_RAQUETA);
                        numeroDeBalas2 += 1;
                    }
                }    
            }
        }
        
        //Verificamos cuantos bloques se destruyen
        for (int i = 30, j = 0; i < Bloques.NUMERO_DE_BLOQUES; i++) {
            //Se almacena la cantidad de ladrillos destruidos en j
            if (bloques[i].esDestruido()) {

                j++;
            }
            
            //En el caso de que sea destruido todo los bloques, saldrá un mensaje de victoria y se dentendra el juego
            if (j == Bloques.NUMERO_DE_BLOQUES - 44) {

                mensaje = "Victoria!!! :)";
                detenerJuego();
            }
        }
    }
    
    private void detenerJuego() {
        
        dentroDelJuego = false;
        reloj.stop();
    }
}

