/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mappers;

import Dominio.Jugador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Owner
 */
public class MapperJugador extends MapperUsuario{
     
    public MapperJugador(Jugador j) {
        super(j);
    }

    public MapperJugador() {
    }
    
    public Jugador getJug() {
        return (Jugador) this.u;
    }

    @Override
    public String getSqlRestaurar() {
        return "SELECT Usuario.*, Jugador.saldo " +
            "FROM Usuario " +
            "JOIN Jugador " +
            "ON Jugador.user = Usuario.user " +
            "WHERE oid = " + getOid();
    }

    @Override
    public void leer(ResultSet rs) throws SQLException {
        super.leer(rs);
        getJug().setSaldo(rs.getFloat("saldo"));
    }

    public String getSqlSeleccionar() {
        return "SELECT Usuario.*, Jugador.saldo " +
            "FROM Usuario " +
            "JOIN Jugador " +
            "ON Jugador.user = Usuario.user";
    }

    @Override
    public void crearNuevo() {
        u = new Jugador();
    }
    
   
    public String getSqlModificar() {
        return "UPDATE usuario set nombre = '" + u.getNombreUsuario() + "' "
                + ", password = '" + u.getPassword() + "'"
                + " where oid=" + getOid();
    }
    
}
