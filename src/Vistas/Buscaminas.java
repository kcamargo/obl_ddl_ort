/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Dominio.Fachada;

/**
 *
 * @author Owner
 */
public class Buscaminas {

    public static void main(String[] args) throws InterruptedException {
        Fachada.getInstancia();
        Fachada.getInstancia().cargarDatos();
        new VentanaHome().setVisible(true);
    }
}
