/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author Owner
 */
public class TimerApuesta extends Timer{

    public TimerApuesta(int limite, Juego j) {
        super(limite, j);
    }

    @Override
    void terminado() {
        j.tiempoFueraApuesta();
    }

    @Override
    void avisar() {
        j.avisar(Juego.Eventos.TimerApuesta);
    }
    
}

