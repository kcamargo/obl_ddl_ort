/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.ControladorJuego;
import Controladores.VistaJuego;
import Dominio.ICasillero;
import Dominio.Jugador;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.ArrayList;
import javax.swing.JSplitPane;

/**
 *
 * @author Owner
 */
public class VentanaJuego extends javax.swing.JFrame implements VistaJuego {

    private final ControladorJuego controlador;
    private final Vistas.PanelDatos datos;
    Jugador jugador;
    JSplitPane split;

    public VentanaJuego(Jugador j, int size) {
        initComponents();
        jugador = j;
        controlador = new ControladorJuego();
        controlador.setSize(size);
        controlador.generarCasilleros(size);

        JPanel panel = (JPanel) getContentPane();
        GridLayout layout = new GridLayout(3, 1);
        panel.setLayout(layout);

        datos = new PanelDatos(controlador);
        panel.add(datos);

        split = new JSplitPane();
        split.setTopComponent(new PanelDatos(controlador));

        setContentPane(split);

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
        setTitle("Buscamina");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        setBounds(0, 0, 416, 339);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        controlador.abandonarJuego(jugador);
    }//GEN-LAST:event_formWindowClosing

    @Override
    public void mostrarDatos(String jugador, String oponente, float saldo1, float saldo2, float apuesta) {
        datos.refreshDatos(jugador, oponente, saldo1, saldo2, apuesta);
        this.paintAll(this.getGraphics());
    }

    @Override
    public void error(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void cerrar() {
        this.dispose();
    }

    @Override
    public void confirmarApuesta(float apuestaPendiente) {
        int result = JOptionPane.showConfirmDialog(this, "¿Desea igualar la apuesta de: $" + apuestaPendiente + "?", "¡Atención!", JOptionPane.YES_NO_OPTION);

    }

    @Override
    public void deshabilitar() {
        datos.deshabilitar();
    }

    @Override
    public void habilitar() {
        datos.habilitar();
    }

    @Override
    public void mostrarTablero(int tamaño, ArrayList<ICasillero> casilleros) {

        PanelTablero p = new PanelTablero(controlador, jugador);

        p.mostrar(tamaño, casilleros);

//        setContentPane(p);
        split.setBottomComponent(p);


        validate();
        split.setDividerLocation(200);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
