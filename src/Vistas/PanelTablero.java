/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.ControladorJuego;
import Dominio.ICasillero;
import Dominio.Jugador;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Owner
 */
public class PanelTablero extends javax.swing.JPanel implements ActionListener {

    /**
     * Creates new form PanelTablero
     */
    private ControladorJuego controlador;
<<<<<<< HEAD
    private Jugador jugador;
=======

    private Jugador jugador;

>>>>>>> 932e3d9c28be3de6cbef035fbb7113d55647b7b0
    public PanelTablero(ControladorJuego c, Jugador j) {
        initComponents();
        controlador = c;
        jugador = j;
    }

    public void mostrar(int tamaño, ArrayList<ICasillero> casilleros) {
        GridLayout gl = new GridLayout(tamaño, tamaño);
        setLayout(gl);
        for (ICasillero c : casilleros) {
            BotonCasillero b = new BotonCasillero(c);
            b.addActionListener(this);
            add(b);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void actionPerformed(ActionEvent e) {
        BotonCasillero b = (BotonCasillero) e.getSource();
<<<<<<< HEAD
        controlador.destaparTablero(b.getCasillero(),jugador);
=======

        controlador.destapar(b.getCasillero(), jugador);

>>>>>>> 932e3d9c28be3de6cbef035fbb7113d55647b7b0
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
