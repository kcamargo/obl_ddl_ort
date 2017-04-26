/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.ControladorHome;
import Controladores.VistaHome;
import javax.swing.JOptionPane;

/**
 *
 * @author Owner
 */
public class VentanaHome extends javax.swing.JFrame implements VistaHome{

    private final ControladorHome controlador;
    
    public VentanaHome() {
      initComponents();
        controlador = new ControladorHome();
        setLocationRelativeTo(null);
        controlador.setVista(this);
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
        btnUserLogin = new javax.swing.JButton();
        btnAdminLogin = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Home");
        getContentPane().setLayout(null);

        jLabel1.setText("Menú Principal");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(150, 20, 80, 30);

        btnUserLogin.setText("Login Usuario");
        btnUserLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnUserLogin);
        btnUserLogin.setBounds(130, 70, 110, 30);

        btnAdminLogin.setText("Login Administrador");
        btnAdminLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdminLogin);
        btnAdminLogin.setBounds(110, 120, 150, 30);

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCerrar);
        btnCerrar.setBounds(150, 230, 73, 23);

        setBounds(0, 0, 416, 339);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUserLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserLoginActionPerformed
        // TODO add your handling code here:
        controlador.loginUsuario();
    }//GEN-LAST:event_btnUserLoginActionPerformed

    private void btnAdminLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminLoginActionPerformed
        // TODO add your handling code here:
         controlador.loginAdmin();
    }//GEN-LAST:event_btnAdminLoginActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
         controlador.cerrar();
    }//GEN-LAST:event_btnCerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdminLogin;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnUserLogin;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
 @Override
    public void error(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void cerrar() {
        this.dispose();
    }

}
