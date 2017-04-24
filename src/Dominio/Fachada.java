/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Owner
 */
public class Fachada extends Observable {

    private SistemaUsuarios sistemaUsuarios;
    private Buscamina buscamina;
    private static Fachada instancia;

    private Fachada() {
        sistemaUsuarios = new SistemaUsuarios();
        buscamina = new Buscamina();
    }

    public static Fachada getInstancia() {
        if (instancia == null) {
            instancia = new Fachada();
        }
        return instancia;
    }

    public Administrador loginAdmin(Administrador a) {
        return sistemaUsuarios.loginAdmin(a);
    }

    public Jugador loginJugador(Jugador j) throws DominoException {
        return sistemaUsuarios.loginJugador(j);
    }

    public void agregarAJuego(Jugador j) throws DominoException {
        buscamina.agregarAJuego(j);
    }

    public void logoutJugador(Jugador j) {
        sistemaUsuarios.logoutJugador(j);
    }

    protected void avisar(Juego.Eventos e) {
        setChanged();
        notifyObservers(e);
    }

    public ArrayList<Juego> getJuegos() {
        return buscamina.getJuegos();
    }

    public boolean juegosActivos() {
        return buscamina.juegosActivos();
    }

    public Jugador getJugadorByUser(String user) {
        return sistemaUsuarios.getJugadorByUser(user);
    }

    public void cargarDatos() {
        buscamina.cargarJuegos();
    }

    public Juego getJuegoByOid(int oid) {
        return buscamina.getJuegoByOid(oid);
    }

}
