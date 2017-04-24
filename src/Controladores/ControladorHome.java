/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Dominio.Fachada;
import Vistas.VentanaAdminLogin;
import Vistas.VentanaUserLogin;

/**
 *
 * @author Owner
 */
public class ControladorHome {
    private VistaHome vista;
    private Fachada modelo;

    public ControladorHome() {
        this.modelo = Fachada.getInstancia();
    }
    
    public void setVista(VistaHome vista) {
        this.vista = vista;
    }
    
    public void loginUsuario() {
        new VentanaUserLogin().setVisible(true);
    }
    
    public void loginAdmin() {
        new VentanaAdminLogin().setVisible(true);
    }

    public void cerrar() {
        if(modelo.juegosActivos()) {
            vista.error("Hay juegos activos. No se puede cerrar el programa.");
        }else {
            vista.cerrar();
            System.exit(0);
        }
    }
}
