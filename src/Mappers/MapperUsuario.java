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
public class MapperUsuario implements Mapeador{
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
    public ArrayList<String> getSqlInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> getSqlUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> getSqlDelete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSqlRestaurar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void leer(ResultSet rs) throws SQLException {
        u.setOid(rs.getInt("oid"));
        u.setNombreCompleto(rs.getString("nomCompleto"));
        u.setNombreUsuario(rs.getString("user"));
        u.setPassword(rs.getString("pass"));
    }

    @Override
    public String getSqlSelect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
