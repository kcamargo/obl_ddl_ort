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
        return "SELECT usuario.*, jugador.saldo " +
            "FROM usuario " +
            "JOIN jugador " +
            "ON jugador.user = usuario.user " +
            "WHERE oid = " + getOid();
    }

    @Override
    public void leer(ResultSet rs) throws SQLException {
        super.leer(rs);
        getJug().setSaldo(rs.getFloat("saldo"));
    }

    @Override
    public String getSqlSelect() {
        return "SELECT usuario.*, jugador.saldo " +
            "FROM usuario " +
            "JOIN jugador " +
            "ON jugador.user = usuario.user";
    }

    @Override
    public void crearNuevo() {
        u = new Jugador();
    }
    
    @Override
    public ArrayList<String> getSqlUpdate() {
        ArrayList<String> salida = new ArrayList<>();
        salida.add("UPDATE jugador SET Saldo = " + getJug().getSaldo()
            + " WHERE User = '" + getJug().getNombreUsuario() + "'");
        return salida;
    }
    
    
}
