/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author Owner
 */
public class Administrador extends Usuario {

    public Administrador(String nombreUsuario, String password, String nombreCompleto) {
        super(nombreUsuario, password,nombreCompleto);
    }
    
    public Administrador(String nombreUsuario, String password) {
        super(nombreUsuario, password);
    }

    public Administrador() {
    }
}
