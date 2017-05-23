/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;

/**
 *
 * @author Owner
 */
public class Juego extends Observable {


    private float apuestaInicial;
    // private Jugador turno;
    private Jugador ganador;
    private Jugador jug1;
    private Jugador jug2;
    private Jugador ultApuesta;
    private ArrayList<Movimiento> movimientos;
    private ArrayList<ICasillero> casilleros;
    private Movimiento turnoActual;
    private float apuestaActual;
    private float apuestaPendiente;
    private int size;
    private boolean comenzo;
    private boolean apuestaAceptada;

    int contadorMinas = 0;

    // private TimerApuesta timerApuesta;
    // private TimerTurno timerTurno;
    private int oid;

    public enum Eventos {

        JuegoTerminado, JuegoComenzado, NuevaApuesta, juego

    }

    public Juego(float apuestaInicial) {
        this.apuestaInicial = apuestaInicial;
        this.apuestaAceptada = true;
        movimientos = new ArrayList<>();

        apuestaActual = apuestaInicial;
    }

    public Juego() {
        movimientos = new ArrayList<>();
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
//     public Jugador getTurno() {
//        return turno;
//    }

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

    protected void tiempoFueraApuesta() {
        abandonarJuego(getOponente(ultApuesta));
    }

    protected void tiempoFueraTurno() {
        abandonarJuego(turnoActual.getJugador());
    }

    public void abandonarJuego(Jugador j) {
        ganador = j.equals(jug1) ? jug2 : jug1;
        terminarJuego();
    }

// TODO
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
        //  ganador.agregarSaldo(apuestaActual * 2);
        jug1.setJuegoActivo(null);
        jug2.setJuegoActivo(null);
        avisar(Eventos.JuegoTerminado);

    }
//

    private void initJugador(Jugador j) {
        j.setJuegoActivo(this);
        j.agregarSaldo(apuestaInicial * -1);
    }

//  
    public void addJugador(Jugador j) throws BuscaminaException {// tienen que machear la cantidad de casilleros
        if (j.getSaldo() < this.apuestaInicial) {
            throw new BuscaminaException("El jugador no tiene el saldo suficiente para esta partida.");
        }
        if (jug1 == null) {
            jug1 = j;
            jug1.setColor(Color.YELLOW);
            initJugador(j);
            turnoActual = new Movimiento(j);
        } else if (jug2 == null) {
            jug2 = j;
            jug2.setColor(Color.RED);
            initJuego();
            initJugador(j);
        } else {
            throw new BuscaminaException("El juego ya está completo.");
        }
    }

    public void destapar(ICasillero c, Jugador j) {
        if (j == turnoActual.getJugador()) {
            if (c.getEstado() == 3) {
                System.out.println("EXPLOTO TODOOOOO");
               // MostrarMina();
                this.terminarJuego();
            } else if (c.getEstado() == 1) {
                c.destapar(j);
                movimientos.add(turnoActual);
                cambiarTurno(j);   
                avisar(Eventos.juego);
            }
        } else {
            System.out.println("no es tu turno");
        }

    }

    public void cambiarTurno(Jugador actual) {
        boolean banderaMina=false;
        if (actual == this.jug1) {
            this.turnoActual.setJugador(this.jug2);    
        } else {
            this.turnoActual.setJugador(this.jug1);
            while (banderaMina== false && LugaresDisponible() == true) {
             banderaMina= AgregarNuevaMina();
            }
        }
    //    System.out.println("turno actual " + this.turnoActual.getJugador().getNombreCompleto());
    }
    
    public void MostrarMina()
    {
        int i=0;
        for (ICasillero c : casilleros) {   
           if(c.getEstado() == 3)
            {
//                c.setColor(Color.BLACK);
            }  
           i++;
            System.out.println("color casillero "+ i + c.getColor());
        }  
    }

    private boolean LugaresDisponible() {
        boolean bandera = false;
        for (ICasillero c : casilleros) {
            if (c.getEstado() == 2) {
                bandera = true;
            }
        }
    return bandera;
    }

    
      public int GenerarMina(int cant) {
        int num;
        num = (int) (Math.random() * (cant*cant)) + 1;
        return num;
        
    }
    
    

    public boolean AgregarNuevaMina() {
        int nm = GenerarMina(size);
        int cont = 0;
        boolean bandera = false;

        for (ICasillero c : casilleros) {
            cont++;
            if (c.getEstado() == 1 && c.getEstado() != 3 && cont == nm) {
                c.setEstado(3);
              
                c.setColor(Color.BLACK);
                bandera = true;
                System.out.println("NUEVA MINA " + nm);
            }
        }
        return bandera;
    }

    public ArrayList<ICasillero> casilleros(int t) {

        if (casilleros == null) {
//            int t = size;
            t=size;
            int n = GenerarMina(t);
            System.out.println("NUMERO MINA " + n);
            ArrayList<ICasillero> lista = new ArrayList();
            for (int x = 1; x <= (t * t); x++) {
                Casillero c = new Casillero();
                if (x == n) {
                    c.setEstado(3);
                  
                    c.setColor(Color.BLACK);
                }
                lista.add(c);
            }
            casilleros = lista;

        }
        return casilleros;
    }

    
}
