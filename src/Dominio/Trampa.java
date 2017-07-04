/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author User
 */
public class Trampa extends Mina {
     double valorDesc;

    public Trampa() {
        this.valorDesc = .25;
        this.nombre="T";
    }

    public double getValorDesc() {
        return valorDesc;
    }

    public void setValorDesc(double valorDesc) {
        this.valorDesc = valorDesc;
    }
    
}
