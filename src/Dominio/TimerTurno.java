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
public class TimerTurno extends Timer {

    public TimerTurno(int limite, Juego j) {
        super(limite, j);
    }

    @Override
    void terminado() {
        j.tiempoFueraTurno();
    }

    @Override
    void avisar() {
        j.avisar(Juego.Eventos.TimerTurno);
    }
}
