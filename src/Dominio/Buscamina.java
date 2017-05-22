/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;

/**
 *
 * @author Owner
 */
public class Buscamina {

    private ArrayList<Juego> juegos;
    private Juego juegoEnEspera;
    private static final float APUESTAINICIAL = 5000;

    public Buscamina() {
        juegos = new ArrayList<>();
        juegoEnEspera = new Juego(APUESTAINICIAL);
        //cargarJuegos();
    }

    public void agregarAJuego(Jugador j) throws BuscaminaException {
        if(juegoEnEspera!= null){
            juegoEnEspera.addJugador(j);
            if (juegoEnEspera.getJug2() != null) {
                juegos.add(juegoEnEspera);
                juegoEnEspera = null;
            }
        }
        //juegoEnEspera = new Juego(APUESTAINICIAL);
    }

    public void cargarJuegos() {

    }

    public ArrayList<Juego> getJuegos() {
        return cargarJugosPrueba();
    }

    public boolean juegosActivos() {
        for (Juego j : juegos) {
            if (j.getGanador() == null) {
                return true;
            }
        }
        return false;
    }

    public Juego getJuegoByOid(int oid) {
        for (Juego j : juegos) {
            if (j.getOid() == oid) {
                return j;
            }
        }
        return null;
    }

    public Juego getJuegoDisponible(Jugador j) {
        for (Juego a : juegos) {
            if (a.getJug1().equals(j) || a.getJug2().equals(j)) {
                return a;
            }
        }
        return null;
    }

    public Juego getJuegoEnEspera() {
        return juegoEnEspera;
    }

    private ArrayList<Juego> cargarJugosPrueba() {// para borrar
        juegos.add(new Juego(2000,new Jugador("pepe","1234"),new Jugador("pepe","1234"),new Jugador("jorge","1234")));
        juegos.add(new Juego(5000,new Jugador("jorge","1234"),new Jugador("pepe","1234"),new Jugador("jorge","1234")));
        return juegos;
    }
}
