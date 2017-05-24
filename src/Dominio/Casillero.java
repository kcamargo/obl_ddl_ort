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
    public int estado = 1;
    public int ubicacion;

    public Casillero(Movimiento movimiento) {
        this.movimiento = movimiento;
        this.ubicacion = movimiento.getCasilleroDestapado();
    }

    public Casillero() {
    }
    

     @Override
    public int getUbicacion() {
        return ubicacion;
    }

     @Override
    public void setUbicacion(int ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Color color;
    
    @Override
    public void setColor(Color c) {
        this.color = c;
    }

    public int getEstado() {
        return estado;
    }
      @Override
    public void setEstado(int est) {
          estado= est;
        
    }

    @Override
    public Color getColor() {
        if (movimiento == null) {
            return Color.GRAY;
              
        } else {
            return movimiento.getJugador().getColor();
        }
    }

    @Override
    public void destapar(Jugador j) {
        //validar cosas, para eso tal vez delego esto a la partida
        //tal vez miPartida.destapar(this,jugador)  u otra cosa que quieran...
        movimiento = new Movimiento(j,ubicacion);
        
        setEstado(2);

    }

  
}
