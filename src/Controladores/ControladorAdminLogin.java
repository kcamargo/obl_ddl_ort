/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Dominio.Administrador;
import Dominio.Fachada;

/**
 *
 * @author Owner
 */
public class ControladorAdminLogin implements ControladorLogin{

    private Fachada modelo;
    private VistaAdminLogin vista;

    public ControladorAdminLogin() {
        modelo = Fachada.getInstancia();
    }

    public void setVista(VistaAdminLogin vista) {
        this.vista = vista;
    }

    @Override
    public void login(String user, String pass) {
        Administrador a = modelo.loginAdmin(new Administrador(user, pass));
        if (a == null) {
            vista.error("El usuario y la contraseña no coinciden.");
        } else {
            vista.abrirListaJuegos();
            vista.cerrar();
        }
    }

    @Override
    public void loginJugador(String user, String pass, int size) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
