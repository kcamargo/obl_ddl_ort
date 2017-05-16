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
public class Casillero implements ICasillero{

    private Movimiento movimiento;

    @Override
    public Color getColor() {
        if(movimiento==null)
            return Color.GRAY;
        else{
            return movimiento.getColor();
        }
    }

    @Override
    public void destapar(Jugador jugador) {
        //validar cosas, para eso tal vez delego esto a la partida
        //tal vez miPartida.destapar(this,jugador)  u otra cosa que quieran...
        movimiento = new Movimiento();
    }
    
}