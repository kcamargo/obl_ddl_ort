/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.ControladorAdminLogin;
import Controladores.VistaAdminLogin;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Owner
 */
public class VentanaAdminLogin extends javax.swing.JFrame implements VistaAdminLogin{

      private final ControladorAdminLogin controlador;
    private final PanelLogin login;
    
    public VentanaAdminLogin() {
        initComponents();
        controlador = new ControladorAdminLogin();
        
        JPanel panel = (JPanel) getContentPane();
        panel.setLayout(new GridLayout(1, 1));
        
        login = new PanelLogin(controlador);
        panel.add(login);
        
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        setBounds(0, 0, 416, 339);
    }// </editor-fold>//GEN-END:initComponents

   @Override
    public void error(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void cerrar() {
        this.dispose();
    }
    
    @Override
    public void abrirListaJuegos() {
        new VentanaListaJuegos().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
