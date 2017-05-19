/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.ControladorListaJuegos;
import Dominio.Juego;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Iván
 */
public class PanelLista extends javax.swing.JPanel {

    private ControladorListaJuegos controlador;
    private JList lista;

    public PanelLista(ControladorListaJuegos controlador) {
        initComponents();

        this.controlador = controlador;

        lista = new JList();
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista.setLayoutOrientation(JList.VERTICAL);
        lista.setCellRenderer(new JuegoRenderer());
        setMouseListener();
        JScrollPane listScroller = new JScrollPane(lista);

        this.setLayout(new BorderLayout());
        this.add(listScroller, BorderLayout.CENTER);
    }

    public void cargarLista(Object[] datos) {
        lista.setListData(datos);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents

    private void setMouseListener() {
        lista.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    controlador.cargarRepeticion((Juego)lista.getSelectedValue());
                } 
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

class JuegoRenderer extends JLabel implements ListCellRenderer<Juego> {

    public JuegoRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Juego> list,
            Juego value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        setText(juegoToString(value));

        Color background;
        Color foreground;

        // check if this cell represents the current DnD drop location
        JList.DropLocation dropLocation = list.getDropLocation();
        if (dropLocation != null
                && !dropLocation.isInsert()
                && dropLocation.getIndex() == index) {

            background = Color.BLUE;
            foreground = Color.WHITE;

            // check if this cell is selected
        } else if (isSelected) {
            background = Color.RED;
            foreground = Color.WHITE;

            // unselected, and not the DnD drop location
        } else {
            background = Color.WHITE;
            foreground = Color.BLACK;
        }

        setBackground(background);
        setForeground(foreground);

        return this;
    }

    private static String juegoToString(Juego j) {
        String s = "[%s] %s ($ %s) - %s ($ %s) : $%s => %s";
        ArrayList<Object> args = new ArrayList<>();
        args.add(j.getGanador() == null ? "En Juego" : "Finalizada");
        args.add(j.getJug1().getNombreCompleto());
        args.add(fmt(j.getJug1().getSaldo()));
        args.add(j.getJug2().getNombreCompleto());
        args.add(fmt(j.getJug2().getSaldo()));
        args.add(fmt(j.getApuestaActual()));
        args.add(j.getGanador() != null ? j.getGanador().getNombreCompleto() : "N/A");

        return String.format(s, args.toArray());
    }

    public static String fmt(float d) {
        if (d == (long) d) {
            return String.format("%d", (long) d);
        } else {
            return String.format("%s", d);
        }
    }
}
