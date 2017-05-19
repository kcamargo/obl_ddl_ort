/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Dominio.Juego;
import java.util.ArrayList;

/**
 *
 * @author Owner
 */
public interface VistaListaJuegos {

    public void listar(ArrayList<Juego> array);

    public void cerrar();

    public void error(String message);

    public void cargarVentanaRepe(Juego juego);
}
