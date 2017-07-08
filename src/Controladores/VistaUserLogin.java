/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Dominio.Jugador;

/**
 *
 * @author Owner
 */
public interface VistaUserLogin {

    public void error(String message);

    public void cerrar();
    
    public void ingresar(Jugador j, int size);
    
}
