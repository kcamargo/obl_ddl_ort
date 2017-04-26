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
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Owner
 */
public class ControladorJuego implements Observer {

    private Jugador jugador;
    private Jugador oponente;
    private Juego juego;

    private VistaJuego vista;

    public ControladorJuego() {

    }

    private void initJuego() {
        this.oponente = juego.getOponente(jugador);
        vista.habilitar();
        refreshVista();
    }

    public void setJugador(Jugador j) {
        this.jugador = j;
        this.juego = jugador.getJuegoActivo();
        this.oponente = juego.getOponente(jugador);
        juego.addObserver(this);
        if (juego.comenzo()) {
            refreshVista();
        } else {
            vista.deshabilitar();
        }
    }

    public void setVista(VistaJuego vista) {
        this.vista = vista;
    }

    private void refreshVista() {

    }

    private void juegoTerminado() {
        juego.deleteObserver(this);
        vista.error("¡Ganó el jugador " + juego.getGanador().getNombreCompleto()
                + " con un premio de $" + juego.getApuestaActual() + "!");
        Fachada.getInstancia().logoutJugador(jugador);
        vista.cerrar();
    }

    public void apostar(float monto) {
        try {
            juego.aumentarApuesta(monto, jugador);
        } catch (BuscaminaException | IllegalArgumentException ex) {
            vista.error(ex.getMessage());
        }
    }

    private void crearNuevaApuesta() {
        refreshVista();
        if (!jugador.equals(juego.getUltApuesta())) {
            vista.confirmarApuesta(juego.getApuestaPendiente());
        }
    }

    public void contestarApuesta(boolean ok) {
        try {
            juego.contestarApuesta(ok, jugador);
            if (ok) {
                refreshVista();
            }
        } catch (BuscaminaException ex) {
            vista.error(ex.getMessage());
        }

    }

    public void abandonarJuego() {
        juego.deleteObserver(this);
        Fachada.getInstancia().logoutJugador(jugador);
        juego.abandonarJuego(jugador);
    }

    @Override
    public void update(Observable o, Object arg) {
        switch ((Juego.Eventos) arg) {
            case JuegoTerminado:
                juegoTerminado();
                break;
            case Bajo:
                initJuego();
                break;
            case Medio:
                initJuego();
                break;
            case Avanzado:
                initJuego();
                break;
            default:
                throw new AssertionError(((Juego.Eventos) arg).name());

        }
    }

}
