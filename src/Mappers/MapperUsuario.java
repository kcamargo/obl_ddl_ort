/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mappers;

import Dominio.Usuario;
import Persistencia.Mapeador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Owner
 */
public class MapperUsuario implements Mapeador {

    protected Usuario u;

    public MapperUsuario(Usuario u) {
        this.u = u;
    }

    public MapperUsuario() {
    }

    public void setU(Usuario u) {
        this.u = u;
    }

    @Override
    public int getOid() {
        return u.getOid();
    }

    @Override
    public void setOid(int oid) {
        u.setOid(oid);
    }

    @Override
    public ArrayList<String> getSqlInsertar() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

//        return "INSERT INTO usuario (oid,nombre,password) values "
//                + "(" + getOid() + ",'" + u.getNombreUsuario() + "','" + u.getPassword() + "')";
    }

    @Override
    public String getSqlModificar() {
        return "UPDATE usuario set nombre = '" + u.getNombreUsuario() + "' "
                + ", password = '" + u.getPassword() + "'"
                + " where oid=" + getOid();
    }

    @Override
    public String getSqlBorrar() {
        return "DELETE FROM usuario WHERE oid=" + getOid();
    }

    @Override
    public String getSqlRestaurar() {
        return "SELECT * FROM usuario where oid=" + getOid();
    }

    @Override
    public void leer(ResultSet rs) throws SQLException {
        u.setOid(rs.getInt("oid"));
        u.setNombreCompleto(rs.getString("nomCompleto"));
        u.setNombreUsuario(rs.getString("user"));
        u.setPassword(rs.getString("pass"));
    }

    @Override
    public String getSqlSeleccionar() {
        return "SELECT * FROM usuario";
    }

    @Override
    public void crearNuevo() {
        u = new Usuario();
    }

    @Override
    public Object getObjeto() {
        return u;
    }

}
