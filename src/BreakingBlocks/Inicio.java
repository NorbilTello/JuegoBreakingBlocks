
package BreakingBlocks;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Inicio extends javax.swing.JFrame {

    private String nivel = "normal";
    
    public Inicio() {
        
        initComponents();
        this.setLocationRelativeTo(null);
        fondoInicio.setIcon(setImagenFondo("/Imagenes/Fondo Inicio.png"));
        botonJugar.setIcon(setImagenBotones("/Imagenes/Botón Jugar.png"));
        botonAjustes.setIcon(setImagenBotones("/Imagenes/Botón Ajustes.png"));
        botonSalir.setIcon(setImagenBotones("/Imagenes/Botón Salir.png"));
        titulo.setIcon(setImagenTitulo("/Imagenes/Titulo.png"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonJugar = new javax.swing.JButton();
        botonAjustes = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();
        titulo = new javax.swing.JLabel();
        fondoInicio = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(565, 730));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonJugar.setBorderPainted(false);
        botonJugar.setContentAreaFilled(false);
        botonJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonJugarActionPerformed(evt);
            }
        });
        getContentPane().add(botonJugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 320, 90));

        botonAjustes.setBorderPainted(false);
        botonAjustes.setContentAreaFilled(false);
        botonAjustes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAjustesActionPerformed(evt);
            }
        });
        getContentPane().add(botonAjustes, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 410, 320, 90));

        botonSalir.setBorderPainted(false);
        botonSalir.setContentAreaFilled(false);
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });
        getContentPane().add(botonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 520, 320, 90));
        getContentPane().add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 460, 210));
        getContentPane().add(fondoInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 0, 570, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public Icon setImagenFondo(String URL){
        
        ImageIcon icon = new ImageIcon(getClass().getResource(URL));
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(565, 730, Image.SCALE_DEFAULT));
        return icono;
    }
    public Icon setImagenBotones(String URL){
        
        ImageIcon icon = new ImageIcon(getClass().getResource(URL));
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(300, 80, Image.SCALE_DEFAULT));
        return icono;
    }
    public Icon setImagenTitulo(String URL){
        
        ImageIcon icon = new ImageIcon(getClass().getResource(URL));
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(500, 260, Image.SCALE_DEFAULT));
        return icono;
    }
    
    
    private void botonAjustesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAjustesActionPerformed
        Ajustes cambio = new Ajustes(this,true);
        this.setVisible(false);
        cambio.pack();
        cambio.setVisible(true);
        if (cambio.nivel != ""){
            this.nivel = cambio.nivel;
        }
        this.setVisible(true);
    }//GEN-LAST:event_botonAjustesActionPerformed

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        System.out.println("sali");
        this.dispose();
    }//GEN-LAST:event_botonSalirActionPerformed

    private void botonJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonJugarActionPerformed
        
        Ventana ventana = new Ventana(nivel);
        ventana.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botonJugarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAjustes;
    private javax.swing.JButton botonJugar;
    private javax.swing.JButton botonSalir;
    private javax.swing.JLabel fondoInicio;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}

