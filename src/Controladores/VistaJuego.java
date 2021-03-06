/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.util.ArrayList;
import Dominio.ICasillero;

/**
 *
 * @author Owner
 */
public interface VistaJuego {

    public void mostrarDatos(String jugador, String oponente, double saldo1, double saldo2, float apuesta);

    public void mostrarTablero(int tamaño, ArrayList<ICasillero> casilleros);

    public void error(String message);

    public void cerrar();

    public void confirmarApuesta(float apuestaPendiente);

    public void deshabilitar();

    public void habilitar();

    public void mostrarTiempoTurno(int counter);

    public void mostrarTiempoApuesta(int counter);

}
