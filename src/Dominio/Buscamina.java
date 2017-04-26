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
class Buscamina {

    private ArrayList<Juego> juegos;
    private Juego juegoEnEspera;
    private static final float APUESTAINICIAL = 5000;

    public Buscamina() {
        juegos = new ArrayList<>();
        juegoEnEspera = new Juego(APUESTAINICIAL);
        //cargarJuegos();
    }

    public void agregarAJuego(Jugador j) throws BuscaminaException {
        if (juegoEnEspera.getJug1() != null) {
            juegos.add(juegoEnEspera);
            try {
                juegoEnEspera.addJugador(j);
            } catch (BuscaminaException ex) {
                juegos.remove(juegoEnEspera);
                throw ex;
            }
            juegoEnEspera = new Juego(APUESTAINICIAL);
        } else {
            juegoEnEspera.addJugador(j);
        }
    }

    public void cargarJuegos() {

    }

    public ArrayList<Juego> getJuegos() {
        return juegos;
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
}
