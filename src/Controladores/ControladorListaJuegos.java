/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Dominio.Fachada;
import Dominio.Juego;
import static Dominio.Juego.Eventos.JuegoComenzado;
import static Dominio.Juego.Eventos.JuegoTerminado;
import static Dominio.Juego.Eventos.NuevaApuesta;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Owner
 */
public class ControladorListaJuegos implements Observer {

    private VistaListaJuegos vista;
    private final Fachada modelo;
    
   public ControladorListaJuegos() {
        this.modelo = Fachada.getInstancia();
    }

    public void setVista(VistaListaJuegos vista) {
        this.vista = vista;
        modelo.addObserver(this);
        cargarJuegos();
    }

    @Override
    public void update(Observable o, Object arg) {
        switch((Juego.Eventos)arg) {
            case NuevaApuesta:
            case JuegoComenzado:
            case JuegoTerminado:
                cargarJuegos();
                break;
            default:
                
            
        }
    }
    
    public void cargarJuegos() {
//        ArrayList<String> array = new ArrayList<>();
//        for(Juego j: modelo.getJuegos()) {
//            array.add(juegoToString(j));
//        }
        vista.listar(modelo.getJuegos());
        
    }

    public void cargarRepeticion(Juego juego) {
        vista.cargarVentanaRepe(juego);
    }
    
    public void cargarRepeticion(int oidJuego) {
        Juego j = Fachada.getInstancia().getJuegoByOid(oidJuego);
        vista.cargarVentanaRepe(j);
    }

}
