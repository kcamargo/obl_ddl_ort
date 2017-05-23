/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Owner
 */
public class Jugador extends Usuario{


    private float saldo;
    private boolean logueado;
    private Juego juegoActivo;
    private Color color;

   


    //CONSTRUCTORES
    public Jugador(String usuario, String password) {
        super(usuario, password);
    }

    public Jugador() {
        
    }
    
     public Jugador(String usuario,String password, String nombreCompleto, float saldo) {
        super(usuario, password,nombreCompleto);
        this.saldo = saldo;
    }

    public float getSaldo() {
        return saldo;
    }

    public boolean isLogueado() {
        return logueado;
    }

    public void setLogueado(boolean logueado) {
        this.logueado = logueado;
    }

    public Juego getJuegoActivo() {
        return juegoActivo;
    }

    public void setJuegoActivo(Juego juegoActivo) {
        this.juegoActivo = juegoActivo;
    }

    
    public void agregarSaldo(float saldo) {
        this.saldo += saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    boolean equalsUser(String user) {
        return this.getNombreUsuario().equals(user);
    }
   
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
   

}
