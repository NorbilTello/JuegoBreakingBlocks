/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DestruccionDeLadrillos;

import javax.swing.JFrame;
/**
 *
 * @author Norbil
 */
public class Ventana{
    
    public static int ANCHO_VENTANA = 565;
    public static int ALTURA_VENTANA = 700;
    
    public static void main(String[] args) {

        JFrame ventana = new JFrame();
        Tablero tablero = new Tablero();
        ventana.add(tablero);
        ventana.setBounds(400,15, ANCHO_VENTANA,ALTURA_VENTANA);
        ventana.setTitle("Destrucción de Ladrillos");
        ventana.setResizable(false);   
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        ventana.setVisible(true);
         
    }   
}
