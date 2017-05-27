/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.ControladorReplayJuego;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Iván
 */
public class PanelControlReplay extends javax.swing.JPanel {

    private ControladorReplayJuego controlador;
    
    public PanelControlReplay(ControladorReplayJuego c) {
        initComponents();
        controlador = c;
    }

    public void cargarDatos(String jugador,Date d, float apuesta) {
        lblJugador.setText(jugador);
//        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//        lblHoraDescarte.setText(dateToString(fecha));
        lblApuesta.setText("$" + apuesta);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblJugador = new javax.swing.JLabel();
        lblApuesta = new javax.swing.JLabel();
        btnTurno = new javax.swing.JButton();

        setLayout(new java.awt.GridLayout(1, 4));

        lblJugador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJugador.setText("Jugador");
        add(lblJugador);

        lblApuesta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblApuesta.setText("Monto Apostado");
        add(lblApuesta);

        btnTurno.setText("Próximo Turno");
        btnTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTurnoActionPerformed(evt);
            }
        });
        add(btnTurno);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTurnoActionPerformed
        controlador.nextTurno();
    }//GEN-LAST:event_btnTurnoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTurno;
    private javax.swing.JLabel lblApuesta;
    private javax.swing.JLabel lblJugador;
    // End of variables declaration//GEN-END:variables

    void cargarHora(Date d) {
//        lblHoraDescarte.setText(dateToString(d));
    }
    

}
