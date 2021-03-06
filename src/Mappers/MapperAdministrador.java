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
public class MapperAdministrador extends MapperUsuario{
    
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
        return "SELECT usuario.* " +
            "FROM usuario " +
            "JOIN administrador " +
            "ON administrador.user = usuario.user " +
            "WHERE oid = " + getOid();
    }

    @Override
    public String getSqlSelect() {
        return "SELECT usuario.* " +
            "FROM usuario " +
            "JOIN administrador " +
            "ON administrador.user = usuario.user";
    }

    @Override
    public void crearNuevo() {
        u = new Administrador();
    }
}
