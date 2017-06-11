/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Dominio.BuscaminaException;
import Dominio.Fachada;
import Dominio.Juego;
import Dominio.Jugador;
import Vistas.VentanaJuego;
import Vistas.VentanaNivel;

/**
 *
 * @author Owner
 */
public class ControladorUserLogin implements ControladorLogin{

    private VistaUserLogin vista;
    private Fachada modelo;
    private ControladorJuego controlador;
    
    public ControladorUserLogin() {
        this.modelo = Fachada.getInstancia();
    }

    public void setVista(VistaUserLogin vista) {
        this.vista = vista;
    }

    @Override
    public void login(String user, String pass) {
        try {
            Jugador j = modelo.loginJugador(new Jugador(user, pass));
            if (j == null) {
                vista.error("El usuario y la contraseña no coinciden.");
            } else {
                vista.cerrar();
                Juego juego = modelo.getJuegoDisponible(j);
                if(juego == null) {
                    new VentanaNivel(j).setVisible(true);//hay que cambiarlo
                } else {
                   new VentanaJuego(j, juego.getSize()).setVisible(true);
                }     
            }
        } catch (BuscaminaException ex) {
            vista.error(ex.getMessage());
        }

    }
}
