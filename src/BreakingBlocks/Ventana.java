
package BreakingBlocks;

public class Ventana extends javax.swing.JFrame {

    public static int ANCHO_VENTANA = 565;
    public static int  ALTURA_VENTANA = 730;
    
    public Ventana(String nivel) {
        
        initComponents();
        switch(nivel){
            case "facil": 
               
                Tablero.DELAY = 40;
                Tablero.N_MILISEGUNDOS = 20000;
                break;
            case "normal":
                Tablero.DELAY = 20;
                Tablero.N_MILISEGUNDOS = 10000;
                break;
            case "dificil":
                Tablero.DELAY = 1;
                Tablero.N_MILISEGUNDOS = 1000;
                break;
        }
        Tablero tablero = new Tablero(this);
        add(tablero);
        setSize(ANCHO_VENTANA,ALTURA_VENTANA);
        setLocationRelativeTo(null);
        setTitle("Destrucción de Bloques");
        setResizable(false);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(0, 0));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

