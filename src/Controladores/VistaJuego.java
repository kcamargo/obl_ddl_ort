/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.util.List;

/**
 *
 * @author Owner
 */
public interface VistaJuego {

    public void mostrarDatos(String jugador, String oponente, float saldo, float apuesta, int cantFichas);

    public void error(String message);

    public void cerrar();

    public void confirmarApuesta(float apuestaPendiente);

    public void deshabilitar();

    public void habilitar();

}
