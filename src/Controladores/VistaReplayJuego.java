/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Dominio.ICasillero;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Owner
 */
public interface VistaReplayJuego {
    
    public void error(String message);
    
    public void cargarDatos(String jugador, Date fecha, float apuesta, List<ICasillero> casilleros);

    public void cargarHora(Date d);
}
