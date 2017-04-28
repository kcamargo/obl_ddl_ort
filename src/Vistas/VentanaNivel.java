/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.ControladorUserLogin;
import Controladores.VistaUserNivel;
import Dominio.Juego;
import Dominio.Juego.Eventos;
import Dominio.Jugador;
import java.util.Observable;
import javax.swing.JOptionPane;

/**
 *
 * @author Owner
 */
public class VentanaNivel extends javax.swing.JFrame implements VistaUserNivel {

    /**
     * Creates new form VentanaNivel
     */
    private ControladorUserLogin controlador;
    private Jugador jugador;

    public VentanaNivel(Jugador j) {
        initComponents();
        controlador = new ControladorUserLogin();
        jugador = j;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nCol = new javax.swing.JTextField();
        nFila = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnNivel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Elija el tamaño de su Buscaminas:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(60, 40, 250, 40);
        getContentPane().add(nCol);
        nCol.setBounds(220, 90, 50, 30);
        getContentPane().add(nFila);
        nFila.setBounds(100, 90, 50, 30);

        jLabel2.setText("X");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(180, 100, 10, 10);

        btnNivel.setText("comenzar");
        btnNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNivelActionPerformed(evt);
            }
        });
        getContentPane().add(btnNivel);
        btnNivel.setBounds(140, 180, 90, 30);

        setBounds(0, 0, 416, 339);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNivelActionPerformed
        // TODO add your handling code here:
        valores();
    }//GEN-LAST:event_btnNivelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNivel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField nCol;
    private javax.swing.JTextField nFila;
    // End of variables declaration//GEN-END:variables

    private void ingresar(int fila, int columna) {
        new VentanaJuego(jugador,fila, columna).setVisible(true);
    }
    
    private void valores(){
        int fila, columna;
        fila = Integer.parseInt(this.nFila.getText());
        columna = Integer.parseInt(this.nCol.getText());
        ingresar(fila,columna);
    }

    @Override
    public void error(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void cerrar() {
        this.dispose();
    }


}
