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
public class Suerte extends Mina {
    double valorIncrem;

    public Suerte() {
        this.valorIncrem = 1.1;
        this.nombre="S";
    }

    public double getValorIncrem() {
        return valorIncrem;
    }

    public void setValorIncrem(double valorIncrem) {
        this.valorIncrem = valorIncrem;
    }
    
}
