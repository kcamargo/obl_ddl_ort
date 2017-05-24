/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Dominio.Casillero;
import Dominio.Fachada;
import Dominio.ICasillero;
import Dominio.Juego;
import Dominio.Movimiento;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Owner
 */
public class ControladorReplayJuego implements Observer{
    
    private VistaReplayJuego vista;
    private Juego modelo;
    private LinkedList<ICasillero> casillerosAMostrar;
    private int posicion;
    private float apuesta;

    public ControladorReplayJuego(Juego modelo) {
        initDatos(modelo);
    }

    public ControladorReplayJuego(int oid) {
        Juego j = Fachada.getInstancia().getJuegoByOid(oid);
        initDatos(j);
    }
    
    

    public void setVista(VistaReplayJuego vista) {
        this.vista = vista;
    }
    
    private void initDatos(Juego j) {
        this.modelo = j;
        casillerosAMostrar = new LinkedList<>();
        apuesta = modelo.getApuestaInicial()*2;
        posicion = 0;
        if(modelo.getGanador() == null) {
            modelo.addObserver(this);
        }
    }

    public void nextTurno() {
       if(posicion < modelo.getMovimientos().size()) {
           
            
            Movimiento m = modelo.getMovimientos().get(posicion);
            
            Casillero c = new Casillero(m);
          //  if(m.getCasilleroDestapado()!= null) {
//                if(m.getDireccion().equals(Casillero.Direcciones.derecha)) {
                    //casillerosAMostrar.addLast(m.getCasilleroDestapado());
                              casillerosAMostrar.addLast(c);
//                }else {
//                    fichasEnMuestra.addFirst(m.getFichaMovida());
//                }
                apuesta += m.getApuesta();
                
//                vista.cargarDatos(m.getJugador().getNombreCompleto(),modelo.ultDescarte(),apuesta, casillerosAMostrar);
                posicion++;
//            }else {
//                vista.error("No hay más turnos disponibles.");
//            }
        }else {
            
            if(modelo.getGanador() == null) {
                vista.error("No hay más turnos disponibles.");
            }else {
                vista.error("Ganó " + modelo.getGanador().getNombreCompleto());
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
//        switch((Juego.Eventos)arg) {
//            case PiezaMovida:
//                vista.cargarHora(modelo.ultDescarte());
//                break;
//        }
    }
//    
//    public void cerrar() {
//        modelo.deleteObserver(this);
//    }
}
