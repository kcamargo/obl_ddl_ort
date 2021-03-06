/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Mappers.MapperJuego;
import Mappers.MapperJugador;
import Persistencia.BaseDatos;
import Persistencia.Persistencia;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
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
    private ArrayList<Movimiento> movimientos;
    private ArrayList<ICasillero> casilleros;
    private Movimiento turnoActual;
    private float apuestaActual;
    private float apuestaPendiente;
    private int size;
    private boolean comenzo;
    private boolean apuestaAceptada;

    private Date ultDescarte;

    private TimerApuesta timerApuesta;
    private TimerTurno timerTurno;

    private int oid;

    public enum Eventos {

        JuegoTerminado, JuegoComenzado, NuevaApuesta, juego, casilleroSelect, TimerApuesta, TimerTurno
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

    public Date ultDescarte() {
        return ultDescarte;
    }

    public void setUltDescarte(Date ultDescarte) {
        this.ultDescarte = ultDescarte;
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

    public Dominio.TimerApuesta getTimerApuesta() {
        return timerApuesta;
    }

    public Dominio.TimerTurno getTimerTurno() {
        return timerTurno;
    }

//</editor-fold>
    private void initJuego() {
        comenzo = true;
        startTimerTurno();
        avisar(Eventos.JuegoComenzado);
    }

    private void startTimerTurno() {
        timerTurno = new TimerTurno(100, this);
        timerTurno.start();
    }

    private void startTimerApuesta() {
        timerApuesta = new TimerApuesta(100, this);
        timerApuesta.start();
    }

    public void addTurno(Movimiento t) {
        movimientos.add(t);
    }

    public void aumentarApuesta(float apuesta, Jugador j) throws BuscaminaException { //sin poder probarlo
        if (ultApuesta != null && j.equals(ultApuesta)) {
            throw new BuscaminaException("Este jugador no puede realizar una apuesta.");
        }

        if (!apuestaAceptada) {
            throw new BuscaminaException("No puede apostar. Existe una apuesta pendiente.");
        }

        double apuestaMaxima = Math.min(jug1.getSaldo(), jug2.getSaldo());

        if (apuesta > apuestaMaxima) {
            throw new BuscaminaException("La apuesta no puede ser mayor a " + apuestaMaxima);
        }

        turnoActual.aumentarApuesta(apuesta);
        ultApuesta = j;
        j.agregarSaldo(apuesta * -1);
        apuestaActual += apuesta;
        apuestaAceptada = false;
        timerTurno.pause();
        startTimerApuesta();
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
                timerApuesta.stop();
                timerTurno.start();
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
        jug1.setJuegoActivo(null);
        jug2.setJuegoActivo(null);
        actualizarDatosBD();
        avisar(Eventos.JuegoTerminado);

    }
//

    private void initJugador(Jugador j) {
        j.setJuegoActivo(this);
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
            if (c.getMina() != null) {
                switch ((c.getMina().getNombre())) {
                    case "E":
                        asiganrGanador(j);
                        this.terminarJuego();
                        break;
                    case "T":
                        Trampa t = (Trampa) c.getMina();
                        if (j.equals(jug1)) {
                            t.ValorTrampa(jug1, jug2);
                        } else {
                            t.ValorTrampa(jug2, jug1);
                        }
                        SiguienteTurno(j, c);
                        break;
                    case "S":
                        Suerte s = (Suerte) c.getMina();
                        s.ValorSuerte(j);
                        SiguienteTurno(j, c);
                        break;
                    default:
                        throw new AssertionError(c.getMina().getNombre());
                }
            } else if (c.getEstado() == 1) {
                SiguienteTurno(j, c);
                timerTurno.stop();
                startTimerTurno();
            }
        } else {
            System.out.println("no es tu turno");
        }
    }

    private void SiguienteTurno(Jugador j, ICasillero c) {
        Movimiento mov = new Movimiento(j, c.getUbicacion());
        c.destapar(j);

        //movimientos.add(turnoActual);
        movimientos.add(mov);
        cambiarTurno(j);
        avisar(Eventos.juego);
    }

//    private void ValorTrampa(Jugador j) {
//        double valorTrampa = 0;
//        Trampa unaT = new Trampa();
//        valorTrampa = j.getSaldo() * unaT.getValorDesc();
//        if (j.equals(jug1)) {
//            jug1.setSaldo(jug1.getSaldo() - valorTrampa);
//            jug2.setSaldo(jug2.getSaldo() + valorTrampa);
//        } else {
//            jug1.setSaldo(jug1.getSaldo() + valorTrampa);
//            jug2.setSaldo(jug2.getSaldo() - valorTrampa);
//        }
//    }
//
//    private void ValorSuerte(Jugador j) {
//        Suerte unaS = new Suerte();
//        j.setSaldo(j.getSaldo() * unaS.getValorIncrem());
//    }
    public void destaparReplay(ICasillero c) {
//        if (j == turnoActual.getJugador()) {
//            if (c.getEstado() == 3) {
//                System.out.println("EXPLOTO TODOOOOO");
//                // MostrarMina();
//                asiganrGanador(j);
//                this.terminarJuego();
//            } else if (c.getEstado() == 1) {
//                
//                Movimiento mov = new Movimiento(j, c.getUbicacion());
//                c.destapar(j);
//                
//                //movimientos.add(turnoActual);
//                movimientos.add(mov);
//                cambiarTurno(j);
//                avisar(Eventos.juego);
//            }
//        } else {
//            System.out.println("no es tu turno");
//        }

    }

    public void cambiarTurno(Jugador actual) {
        boolean banderaMina = false;
        if (actual.equalsUser(this.jug1.getNombreUsuario())) {
            this.turnoActual.setJugador(this.jug2);
            if (LugaresDisponible() <= 2) {
                AgregarMinaExplosiva();
            }
        } else {
            this.turnoActual.setJugador(this.jug1);

            while (banderaMina == false && LugaresDisponible() > 2) {
                banderaMina = AgregarNuevaMina();
            }
            if (LugaresDisponible() <= 2) {
                AgregarMinaExplosiva();
            }

        }
        System.out.println("Lugares dispo: " + LugaresDisponible());

    }

    private int LugaresDisponible() {
        int bandera = 0;
        for (ICasillero c : casilleros) {
            if (c.getEstado() == 1 && c.getMina() == null) {
                bandera++;
            }
        }
        return bandera;
    }

    public Mina GenerarMina(int cant) {
        Mina unaMina = CrearMina();
        unaMina.ubicacion = (int) (Math.random() * (cant * cant)) + 1;

        return unaMina;

    }

    public int GenerarExplosiva() {

        return (int) (Math.random() * (2)) + 1;

    }

    public boolean AgregarNuevaMina() {
        Mina uM = GenerarMina(size);
        int cont = 0;
        boolean bandera = false;

        for (ICasillero c : casilleros) {
            cont++;
            if (c.getEstado() == 1 && c.getMina() == null && cont == uM.ubicacion) {
                c.setMina(uM);

                c.setColor(Color.BLACK);
                bandera = true;
                System.out.println("NUEVA MINA " + uM.ubicacion + uM.nombre);
            }
        }
        return bandera;
    }

    public void AgregarMinaExplosiva() {

        Explosiva e = new Explosiva("E");
        int lugarDisponible = GenerarExplosiva();

        int cont = 1;
        int ubicacion = 0;
        for (ICasillero c : casilleros) {
            ubicacion++;
            if (c.getEstado() == 1 && c.getMina() == null) {
                if (cont == lugarDisponible) {
                    e.ubicacion = ubicacion;
                    c.setMina(e);
                    System.out.println("NUEVA MINA " + e.ubicacion + e.nombre);
                }
                cont++;
            }

        }

    }

    public ArrayList<ICasillero> casilleros(int t) {

        if (casilleros == null) {
            t = size;
            Mina uM = GenerarMina(t);
            System.out.println("NUMERO MINA " + uM.ubicacion + uM.nombre);
            ArrayList<ICasillero> lista = new ArrayList();
            for (int x = 1; x <= (t * t); x++) {
                Casillero c = new Casillero();
                c.setUbicacion(x);
                if (x == uM.ubicacion) {
                    c.setMina(uM);

                    c.setColor(Color.BLACK);
                }
                lista.add(c);
            }
            casilleros = lista;

        }
        return casilleros;
    }

    private Mina CrearMina() {
        Mina unaMina;
        int tipoMina = (int) (Math.random() * (3)) + 1;
        switch ((tipoMina)) {
            case 1:
                unaMina = new Explosiva("E");
                break;
            case 2:
                unaMina = new Trampa("T");
                break;
            case 3:
                unaMina = new Suerte("S");
                break;
            default:
                throw new AssertionError(tipoMina);

        }
        return unaMina;
    }

    public ArrayList<ICasillero> getCasilleros() {
        return casilleros;
    }

    private void asiganrGanador(Jugador j) {
        if (this.jug1 == j) {
            ganador = this.jug2;
        } else {
            ganador = this.jug1;
        }

    }

    private void actualizarDatosBD() {
        BaseDatos bd = BaseDatos.getInstancia();
        bd.conectar(SistemaUsuarios.URL, "root", "root");
        Persistencia p = new Persistencia();
        MapperJugador m = new MapperJugador(jug1);
        p.guardar(m);
        m.setU(jug2);
        p.guardar(m);
        MapperJuego j = new MapperJuego(this);
        p.guardar(j);
        bd.desconectar();
    }

}
