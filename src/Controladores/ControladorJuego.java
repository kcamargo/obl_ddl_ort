/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Dominio.BuscaminaException;
import Dominio.Casillero;
import Dominio.Buscamina;
import Dominio.Fachada;
import Dominio.ICasillero;
import Dominio.Juego;
import Dominio.Jugador;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import Dominio.ICasillero;
import java.util.Random;

/**
 *
 * @author Owner
 */
public class ControladorJuego implements Observer {

//    private Jugador jugador;
//    private Jugador oponente;
    private Juego juego;
//    private Buscamina buscamina; 
    private int size;

    private VistaJuego vista;

    private ArrayList<ICasillero> casilleros;

    public ControladorJuego() {
        
    }

    private void initJuego() {
//        this.juego.setJug2(juego.getOponente(jugador)); 
        vista.habilitar();
        refreshVista();
    }

    public void setJugador() {
//        this.jugador = j;
//        this.juego = jugador.getJuegoActivo();
//        this.oponente = juego.getOponente(jugador);
        juego.addObserver(this);
        if (juego.comenzo()) {
            refreshVista();
        } else {
            vista.deshabilitar();
        }
    }

    public void setSize(int size) {      
        this.size = size;
          
    }
    
    public void setVista(VistaJuego vista) {
        this.vista = vista;
        this.vista.mostrarTablero(size,casilleros);

    }

    private void refreshVista() {
        vista.mostrarDatos(juego.getJug1().getNombreCompleto(), juego.getJug2().getNombreCompleto(),
                juego.getJug1().getSaldo(), juego.getJug2().getSaldo(), juego.getApuestaActual());
       
    }

    private void juegoTerminado() {
        juego.deleteObserver(this);
        vista.error("¡Ganó el jugador " + juego.getGanador().getNombreCompleto()
                + " con un premio de $" + juego.getApuestaActual() + "!");
        //Fachada.getInstancia().logoutJugador(j);
        vista.cerrar();
    }

    public void apostar(float monto, Jugador j) {
        try {
            juego.aumentarApuesta(monto, j);
        } catch (BuscaminaException | IllegalArgumentException ex) {
            vista.error(ex.getMessage());
        }
    }

    private void crearNuevaApuesta(Jugador j) {
        refreshVista();
        if (!j.equals(juego.getUltApuesta())) {
            vista.confirmarApuesta(juego.getApuestaPendiente());
        }
    }

    public void contestarApuesta(boolean ok, Jugador j) {
        try {
            juego.contestarApuesta(ok, j);
            if (ok) {
                refreshVista();
            }
        } catch (BuscaminaException ex) {
            vista.error(ex.getMessage());
        }

    }

    public void abandonarJuego(Jugador j) {
        juego.deleteObserver(this);
        Fachada.getInstancia().logoutJugador(j);
        juego.abandonarJuego(j);
    }

    @Override
    public void update(Observable o, Object arg) {
        switch ((Juego.Eventos) arg) {
            case JuegoTerminado:
                juegoTerminado();
                break;
            case JuegoComenzado:
                initJuego();
                break;
//            case Bajo:
//                initJuego();
//                break;
//            case Medio:
//                initJuego();
//                break;
//            case Avanzado:
//                initJuego();
//                break;
            case juego:
                initJuego();
                break;
            default:
                throw new AssertionError(((Juego.Eventos) arg).name());

        }
    }

    public void destapar(ICasillero c, Jugador j) {
        if(c.getEstado()==3){
            System.out.println("EXPLOTO TODOOOOO");
            juego.terminarJuego();
        }
        else if (c.getEstado()==1){
            c.destapar();
            vista.mostrarTablero(size, casilleros);    
        }
        
    }
    
    public int GenerarMina()
    {
        int num=size*size;
        
        Random x = new Random();
        
        return x.nextInt(num);

    
    }

    public void generarCasilleros(int t) {
        t = size;
        int n= GenerarMina();
        System.out.println("NUMERO MINA " + n);
        
        ArrayList<ICasillero> lista = new ArrayList();
        for (int x = 1; x <= (t * t); x++) {
            Casillero c = new Casillero();
            if(x==n){
                c.estado=3;
            }
            
            lista.add(c);
            
            
        }
        casilleros = lista;
    }

    public Juego getJuego() {
        return juego;
    }

}
