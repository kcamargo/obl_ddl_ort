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
    public static final String NOMBRE = "S";

    public Suerte(String nombre) {
        super(nombre);
        this.valorIncrem = 1.1;
        this.nombre=NOMBRE;
    }

    public double getValorIncrem() {
        return valorIncrem;
    }

    public void setValorIncrem(double valorIncrem) {
        this.valorIncrem = valorIncrem;
    }
    
     public void ValorSuerte(Jugador j) {
        
        j.setSaldo(j.getSaldo() * this.getValorIncrem());
    }

    
}
