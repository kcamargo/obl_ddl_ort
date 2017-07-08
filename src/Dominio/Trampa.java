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
    public static final String NOMBRE = "T";

    public Trampa(String nombre) {
        super(nombre);
        this.valorDesc = .25;
        this.nombre = NOMBRE;
    }

    public double getValorDesc() {
        return valorDesc;
    }

    public void setValorDesc(double valorDesc) {
        this.valorDesc = valorDesc;
    }

    public void ValorTrampa(Jugador jActual, Jugador j2) {
        double valorTrampa = 0;

        valorTrampa = jActual.getSaldo() * valorDesc;

        jActual.setSaldo(jActual.getSaldo() - valorTrampa);
        j2.setSaldo(j2.getSaldo() + valorTrampa);

    }

}
