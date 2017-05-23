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
class Movimiento {

<<<<<<< HEAD
    private Juego juego;
    
=======
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
    public Movimiento(){
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

>>>>>>> 932e3d9c28be3de6cbef035fbb7113d55647b7b0
    Color getColor() {
    
     
           return Color.BLUE;
      
    }

}
