/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Dominio.BuscaminaException;
import Dominio.Casillero;
import Dominio.Buscamina;
import Dominio.Fachada;
import Dominio.ICasillero;
import Dominio.Juego;
import Dominio.Jugador;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import Dominio.ICasillero;
import java.util.Random;

/**
 *
 * @author Owner
 */
public final class ControladorJuego implements Observer {

    private Juego juego;

    private Jugador jugador;
    private int size;
    private VistaJuego vista;
    private ArrayList<ICasillero> casilleros;

    public ControladorJuego(int tamaño, VistaJuego vista, Jugador j) {
        this.vista = vista;
        size = tamaño;
        jugador = j;

        juego = j.getJuegoActivo();
        juego.addObserver(this);
        casilleros = juego.casilleros(size);

    }

    private void initJuego() {
        //    this.juego.setJug2(juego.getOponente(jugador));   
        vista.habilitar();
        refreshVista();

    }

    public void setJugador() {
//        this.jugador = j;
//        this.juego = jugador.getJuegoActivo();
//        this.oponente = juego.getOponente(jugador);
        // juego.addObserver(this);
        if (juego.comenzo()) {
            //  generarCasilleros(size);
            refreshVista();
        } else {
            vista.deshabilitar();
        }
    }

    public Juego getJuego() {
        return juego;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setVista(VistaJuego vista) {
        this.vista = vista;
    }

    private void refreshVista() {
        vista.mostrarDatos(juego.getJug1().getNombreCompleto(), juego.getJug2().getNombreCompleto(),
                juego.getJug1().getSaldo(), juego.getJug2().getSaldo(), juego.getApuestaActual());
        vista.mostrarTablero(size, casilleros);

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
            if(ok) refreshVista();
        } catch (BuscaminaException ex) {
            vista.error(ex.getMessage());
        }

    }

    public void abandonarJuego(Jugador j) {
        juego.deleteObserver(this);
        Fachada.getInstancia().logoutJugador(j);
        juego.abandonarJuego(j);
    }

    @Override
    public void update(Observable o, Object arg) {
        switch ((Juego.Eventos) arg) {
            case NuevaApuesta:
                crearNuevaApuesta();
                break;
            case JuegoTerminado:
                juegoTerminado();
                break;
            case JuegoComenzado:
                initJuego();
                break;
            case juego:
                initJuego();
                break;
            default:
                throw new AssertionError(((Juego.Eventos) arg).name());

        }
    }

    public void destaparTablero(ICasillero casillero, Jugador j) {
        juego.destapar(casillero, j);
        vista.mostrarTablero(size, casilleros);
    }

    public void vistaLista() {

        vista.mostrarTablero(size, casilleros);
    }

}
