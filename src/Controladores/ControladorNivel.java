/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Dominio.Fachada;
import Dominio.Juego;
import Dominio.Jugador;
import Vistas.VentanaJuego;

/**
 *
 * @author Owner
 */
public class ControladorNivel {
    private Fachada modelo;
    
    public ControladorNivel() {
        this.modelo = Fachada.getInstancia();
    }
    
    public void setSize(Jugador j, int size) {
        Juego juego = modelo.getJuegoEnEspera();
        juego.setSize(size);
        new VentanaJuego(j, size).setVisible(true);
    }
}
