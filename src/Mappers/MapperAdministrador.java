/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mappers;

import Dominio.Administrador;

/**
 *
 * @author Owner
 */
public class MapperAdministrador extends MapperUsuario {

    public MapperAdministrador(Administrador a) {
        super(a);
    }

    public MapperAdministrador() {
    }

    public Administrador getAdmin() {
        return (Administrador) this.u;
    }

    @Override
    public String getSqlRestaurar() {
        return "SELECT Usuario.* "
                + "FROM Usuario "
                + "JOIN Administrador "
                + "ON Administrador.user = Usuario.user "
                + "WHERE oid = " + getOid();
    }

    public String getSqlSeleccionar() {
        return "SELECT Usuario.* "
                + "FROM Usuario "
                + "JOIN Administrador "
                + "ON Administrador.user = Usuario.user";
    }

    @Override
    public void crearNuevo() {
        u = new Administrador();
    }
}
