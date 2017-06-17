/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.ControladorJuego;
import Controladores.ControladorNivel;
import Controladores.ControladorUserLogin;
import Controladores.VistaUserNivel;
import Dominio.Jugador;
import javax.swing.JOptionPane;

/**
 *
 * @author Owner
 */
public class VentanaNivel extends javax.swing.JFrame implements VistaUserNivel {

    /**
     * Creates new form VentanaNivel
     */
    private ControladorNivel controlador;
    private Jugador jugador;
    
    public VentanaNivel(Jugador j) {
        initComponents();
        controlador = new ControladorNivel();
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
        tamano = new javax.swing.JTextField();
        btnNivel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Elija el tamaño de su Buscaminas:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(60, 40, 250, 40);

        tamano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tamanoActionPerformed(evt);
            }
        });
        getContentPane().add(tamano);
        tamano.setBounds(150, 90, 50, 30);

        btnNivel.setText("comenzar");
        btnNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNivelActionPerformed(evt);
            }
        });
        getContentPane().add(btnNivel);
        btnNivel.setBounds(130, 150, 90, 30);

        setBounds(0, 0, 416, 339);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNivelActionPerformed
        // TODO add your handling code here:
        valores();
        cerrar();
    }//GEN-LAST:event_btnNivelActionPerformed

    private void tamanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tamanoActionPerformed
        // TODO add your handling code here:
        valores();
        cerrar();
    }//GEN-LAST:event_tamanoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNivel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField tamano;
    // End of variables declaration//GEN-END:variables

    private void ingresar(int size) {
//        controlador.setSize(jugador, size);
    }
    
    private void valores(){
        int size = 0;
        size = Integer.parseInt(this.tamano.getText());
        ingresar(size);
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
