/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.awt.Color;
import Dominio.Jugador;

/**
 *
 * @author docenteFI
 */
public interface ICasillero {
    public void setColor(Color c);
    public Color getColor();
    public int getEstado();
    public void setEstado(int estado);
    public void destapar(Jugador j);
    public int getUbicacion();
    public void setUbicacion(int ubicacion);
    public Mina getMina();
    public void setMina(Mina uM);
}
