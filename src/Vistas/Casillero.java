/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import javax.swing.JButton;

/**
 *
 * @author Owner
 */
public class Casillero extends JButton {

    private boolean mina;

    public Casillero() {
        super();
        double random = Math.random();
        if (random > 0.9) {
            mina = true;
        } else {
            mina = false;
        }
    }
    
    public boolean estaMinado(){
        return mina;
    }

}
