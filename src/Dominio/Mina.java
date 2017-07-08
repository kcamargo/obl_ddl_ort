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
public abstract class Mina {

    int ubicacion;
    String nombre;

    public String getNombre() {
        return nombre;
    }

    public int getUbicacion() {
        return ubicacion;
    }

    public Mina(String nom) {
        this.nombre = nom;
    }

    public void setUbicacion(int ubicacion) {
        this.ubicacion = ubicacion;
    }

}
