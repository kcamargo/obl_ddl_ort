/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.awt.Color;

/**
 *
 * @author Owner
 */
public class Movimiento {

    private float apuesta;
    private Jugador jugador;
    private Casillero casillero;

    public Movimiento(Jugador jugador) {
        if (jugador != null) {
            this.jugador = jugador;
        } else {
            throw new NullPointerException();
        }
        apuesta = 0;
    }

    public Movimiento() {
    }

    protected void aumentarApuesta(float num) {
        if (num > 0) {
            apuesta += num;
        } else {
            throw new IllegalArgumentException("La apuesta debe ser mayor a 0.");
        }
    }

    public float getApuesta() {
        return apuesta;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setApuesta(float apuesta) {
        this.apuesta = apuesta;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Casillero getCasilleroDestapado() {
        return casillero;
    }

    Color getColor() {
        return Color.BLUE;
    }

}
