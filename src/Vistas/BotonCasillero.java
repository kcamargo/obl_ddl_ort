/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Dominio.ICasillero;
import javax.swing.JButton;

/**
 *
 * @author docenteFI
 */
public class BotonCasillero extends JButton {
    
    private ICasillero casillero;

    public BotonCasillero(ICasillero casillero) {
        this.casillero = casillero;
        setBackground(casillero.getColor());
    }

    public ICasillero getCasillero() {
        return casillero;
    }
    
    
    
    
    
}
