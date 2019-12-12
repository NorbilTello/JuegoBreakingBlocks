
package BreakingBlocks;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Ajustes extends javax.swing.JDialog {
    public Ajustes(java.awt.Frame parent, boolean modal) {
        
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        
        fondoAjustes.setIcon(setImagenFondo("/Imagenes/Fondo Ajustes.png"));
        botonFacil.setIcon(setImagenDif("/Imagenes/Botón Fácil.png"));
        botonNormal.setIcon(setImagenDif("/Imagenes/Botón Normal.png"));
        botonDificil.setIcon(setImagenDif("/Imagenes/Botón Dificil.png"));
        botonRegresar.setIcon(setImagenRegresar("/Imagenes/Botón Regresar.png"));
        
    }
    
    String nivel = "";
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonFacil = new javax.swing.JButton();
        botonNormal = new javax.swing.JButton();
        botonDificil = new javax.swing.JButton();
        botonRegresar = new javax.swing.JButton();
        fondoAjustes = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonFacil.setBorderPainted(false);
        botonFacil.setContentAreaFilled(false);
        botonFacil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonFacilActionPerformed(evt);
            }
        });
        getContentPane().add(botonFacil, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 300, 90));

        botonNormal.setBorderPainted(false);
        botonNormal.setContentAreaFilled(false);
        botonNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNormalActionPerformed(evt);
            }
        });
        getContentPane().add(botonNormal, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 300, 90));

        botonDificil.setBorderPainted(false);
        botonDificil.setContentAreaFilled(false);
        botonDificil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDificilActionPerformed(evt);
            }
        });
        getContentPane().add(botonDificil, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 300, 90));

        botonRegresar.setBorderPainted(false);
        botonRegresar.setContentAreaFilled(false);
        botonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresarActionPerformed(evt);
            }
        });
        getContentPane().add(botonRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 100, 70));
        getContentPane().add(fondoAjustes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 565, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public Icon setImagenFondo(String URL){
        
        ImageIcon icon = new ImageIcon(getClass().getResource(URL));
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(565,720, Image.SCALE_DEFAULT));
        return icono;
    }    
    public Icon setImagenDif(String URL){
        
        ImageIcon icon = new ImageIcon(getClass().getResource(URL));
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(280, 80, Image.SCALE_DEFAULT));
        return icono;
    }
    public Icon setImagenRegresar(String URL){
        
        ImageIcon icon = new ImageIcon(getClass().getResource(URL));
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        return icono;
    }
    
    
    private void botonFacilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonFacilActionPerformed
        nivel = "facil";
    }//GEN-LAST:event_botonFacilActionPerformed

    private void botonNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNormalActionPerformed
        nivel = "normal";
    }//GEN-LAST:event_botonNormalActionPerformed

    private void botonDificilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDificilActionPerformed
        nivel = "dificil";
    }//GEN-LAST:event_botonDificilActionPerformed

    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed

        this.dispose();
    }//GEN-LAST:event_botonRegresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonDificil;
    private javax.swing.JButton botonFacil;
    private javax.swing.JButton botonNormal;
    private javax.swing.JButton botonRegresar;
    private javax.swing.JLabel fondoAjustes;
    // End of variables declaration//GEN-END:variables
}

