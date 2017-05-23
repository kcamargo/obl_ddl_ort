/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Observable;

/**
 *
 * @author Owner
 */
public class Juego extends Observable {

    private float apuestaInicial;

    private Jugador ganador;
    private Jugador jug1;
    private Jugador jug2;
    private Jugador ultApuesta;
    private boolean mina;

    private ArrayList<Movimiento> movimientos;

    private Movimiento turnoActual;
    private float apuestaActual;
    private float apuestaPendiente;
    private int size;

    private Date ultDescarte;

    private boolean comenzo;
    private boolean apuestaAceptada;

    int contadorMinas = 0;

    private int oid;

    public enum Eventos {

        JuegoTerminado, PiezaMovida, JuegoComenzado, SinFichas, NuevaApuesta

    }

    public Juego(float apuestaInicial) {
        this.apuestaInicial = apuestaInicial;
        this.apuestaAceptada = true;

        ultDescarte = new Date();
        movimientos = new ArrayList<>();
        apuestaActual = apuestaInicial;
    }

    public Juego() {
        movimientos = new ArrayList<>();
        ultDescarte = new Date();
    }

    public Juego(float ai, Jugador g, Jugador j1, Jugador j2) { //es de prueba para cargar las listas del administrador
        apuestaInicial = ai;
        ganador = g;
        jug1 = j1;
        jug2 = j2;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Jugador getOponente(Jugador jugador) {
        return jugador.equals(jug1) ? jug2 : jug1;
    }

    public Jugador getUltApuesta() {
        return ultApuesta;
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public Movimiento getTurnoActual() {
        return turnoActual;
    }

    public float getApuestaActual() {
        return apuestaActual;
    }

    public boolean comenzo() {
        return comenzo;
    }

    public boolean apuestaAceptada() {
        return apuestaAceptada;
    }

    public float getApuestaInicial() {
        return apuestaInicial;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public Jugador getJug1() {
        return jug1;
    }

    public Jugador getJug2() {
        return jug2;
    }

    private Jugador getSiguienteJugador() {
        return turnoActual.getJugador().equals(jug1) ? jug2 : jug1;
    }

    public float getApuestaPendiente() {
        return apuestaPendiente;
    }

    public Date ultDescarte() {
        return ultDescarte;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }

    public void setJug1(Jugador jug1) {
        this.jug1 = jug1;
    }

    public void setJug2(Jugador jug2) {
        this.jug2 = jug2;
    }

    public void setApuestaActual(float apuestaActual) {
        this.apuestaActual = apuestaActual;
    }

    public void setApuestaInicial(float apuestaInicial) {
        this.apuestaInicial = apuestaInicial;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isMina() {
        return mina;
    }

    public void setMina(boolean mina) {
        this.mina = mina;
    }

//</editor-fold>
    private void initJuego() {
        comenzo = true;
        avisar(Eventos.JuegoComenzado);
    }

    public void addTurno(Movimiento t) {
        movimientos.add(t);
    }

    public void aumentarApuesta(float apuesta, Jugador j) throws BuscaminaException {
        if (ultApuesta != null && j.equals(ultApuesta)) {
            throw new BuscaminaException("Este jugador no puede realizar una apuesta.");
        }

        if (!apuestaAceptada) {
            throw new BuscaminaException("No puede apostar. Existe una apuesta pendiente.");
        }

        float apuestaMaxima = Math.min(jug1.getSaldo(), jug2.getSaldo());

        if (apuesta > apuestaMaxima) {
            throw new BuscaminaException("La apuesta no puede ser mayor a " + apuestaMaxima);
        }

        turnoActual.aumentarApuesta(apuesta);
        ultDescarte = new Date();
        ultApuesta = j;
        j.agregarSaldo(apuesta * -1);
        apuestaActual += apuesta;
        apuestaAceptada = false;
        apuestaPendiente = apuesta;
        avisar(Eventos.NuevaApuesta);

    }

    public void contestarApuesta(boolean aceptada, Jugador j) throws BuscaminaException {
        if (ganador == null) {
            if (j.equals(ultApuesta)) {
                throw new BuscaminaException("El jugador contrario debe aceptar su apuesta.");
            }

            if (aceptada) {
                j.agregarSaldo(apuestaPendiente * -1);
                apuestaAceptada = true;
            } else {
                this.abandonarJuego(j);
            }
        }
    }

    public void abandonarJuego(Jugador j) {
        ganador = j.equals(jug1) ? jug2 : jug1;
        terminarJuego();
    }

//    private void verificarGanador(Jugador j) {
//        Jugador oponente = getOponente(j);
//        if (!j.hasFichas() || !puedeDescartar(oponente)) {
//            ganador = j;
//            terminarJuego();
//        }
//    }
    protected void avisar(Juego.Eventos e) {
        this.setChanged();
        this.notifyObservers(e);
        Fachada.getInstancia().avisar(e);
    }

    public void terminarJuego() {
        ganador.agregarSaldo(apuestaActual * 2);
        jug1.setJuegoActivo(null);
        jug2.setJuegoActivo(null);
        avisar(Eventos.JuegoTerminado);

    }

    private void initJugador(Jugador j) {
        j.setJuegoActivo(this);
        j.agregarSaldo(apuestaInicial * -1);
    }

    public void addJugador(Jugador j) throws BuscaminaException {// tienen que machear la cantidad de casilleros
        if (j.getSaldo() < this.apuestaInicial) {
            throw new BuscaminaException("El jugador no tiene el saldo suficiente para esta partida.");
        }
        if (jug1 == null) {
            jug1 = j;
            initJugador(j);
            turnoActual = new Movimiento(j);
        } else if (jug2 == null) {
            jug2 = j;
            initJuego();
            initJugador(j);
        } else {
            throw new BuscaminaException("El juego ya está completo.");
        }
    }

}
