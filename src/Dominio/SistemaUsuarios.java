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
class SistemaUsuarios {

    private ArrayList<Administrador> administradores;
    private ArrayList<Jugador> jugadores;

    public SistemaUsuarios() {
        administradores = new ArrayList<>();
        jugadores = new ArrayList<>();
        cargarDatosPrueba();
    }

    public Administrador loginAdmin(Administrador admin) {
        for (Administrador a : administradores) {
            if (a.equals(admin)) {
                return a;
            }
        }
        return null;
    }

    public Jugador loginJugador(Jugador jugador) throws DominoException {//cambiar nombre domino
        for (Jugador j : jugadores) {
            if (j.equals(jugador)) {
                if (j.isLogueado()) {
                    throw new DominoException("El jugador ya se encuentra logueado en el sistema.");
                } else {
                    j.setLogueado(true);
                    Fachada.getInstancia().agregarAJuego(j);
                    return j;
                }
            }
        }
        return null;
    }

    public void logoutJugador(Jugador j) {
        j.setLogueado(false);
    }

    private void cargarDatosPrueba() {
        jugadores.add(new Jugador("pepe", "1234", "Pepe Gómez",23400));
        jugadores.add(new Jugador("jorge", "1234", "Jorge Ramírez",30040));
        jugadores.add(new Jugador("toto", "1234", "Toto Pérez",25890));
        jugadores.add(new Jugador("ernesto", "1234", "Ernesto Pérez",5890));
        jugadores.add(new Jugador("luis", "1234", "Luis José Pérez",205890));
        administradores.add(new Administrador("koko", "1234", "Gilberto González"));
        administradores.add(new Administrador("coco", "1234", "Joaquín Severino"));

    }

    public Jugador getJugadorByUser(String user) {
        for (Jugador j : jugadores) {
            if (j.equalsUser(user)) {
                return j;
            }
        }
        return null;
    }

}
