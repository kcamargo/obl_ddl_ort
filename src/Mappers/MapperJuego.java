/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mappers;

import Dominio.Fachada;
import Dominio.Juego;
import Dominio.Movimiento;
import Persistencia.Mapeador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Owner
 */
public class MapperJuego implements Mapeador {

    private Juego j;

    public MapperJuego() {
    }

    public MapperJuego(Juego j) {
        this.j = j;
    }

    public void setJuego(Juego j) {
        this.j = j;
    }

    @Override
    public int getOid() {
        return j.getOid();
    }

    @Override
    public void setOid(int oid) {
        j.setOid(oid);
    }

    @Override
    public ArrayList<String> getSqlInsert() {
        ArrayList<String> s = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = null;
        if(j.ultDescarte() !=  null){
            date = sdf.format(j.ultDescarte());
        }else{
            date = sdf.format(new Date());
        }        
        s.add("INSERT INTO juego VALUES(" + getOid() + ",'" + j.getJug1().getNombreUsuario()
                + "','" + j.getJug2().getNombreUsuario() + "','"
                + j.getGanador().getNombreUsuario() + "'," + j.getApuestaActual()
                + ",'" + date + "'," + j.getApuestaInicial() + ");");
        generarLineas(s);
        return s;
    }

    private void generarLineas(ArrayList<String> sqls) {
        int nro = 1;
        for (Movimiento m : j.getMovimientos()) {
            sqls.add("INSERT INTO movimiento VALUES('" + m.getJugador().getNombreUsuario()
                    + "'," + j.getOid() + "," + nro + "," + m.getApuesta() + ")");
            nro++;
        }
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
        if (getOid() == 0) {
            cargarCabezal(rs);
        }
        if (rs.getString("jugador") != null) {
            cargarLinea(rs);
        }
    }

    private void cargarCabezal(ResultSet rs) throws SQLException {
        setOid(rs.getInt("oid"));
        j.setApuestaActual(rs.getInt("apuestaActual"));
        j.setUltDescarte(new Date(rs.getTimestamp("ultDescarte").getTime()));
        j.setJug1(Fachada.getInstancia().getJugadorByUser(rs.getString("jug1")));
        j.setJug2(Fachada.getInstancia().getJugadorByUser(rs.getString("jug2")));
        j.setGanador(Fachada.getInstancia().getJugadorByUser(rs.getString("ganador")));
        j.setApuestaInicial(rs.getFloat("apuestaInicial"));
    }

    private void cargarLinea(ResultSet rs) throws SQLException {
        Movimiento m = new Movimiento(Fachada.getInstancia().getJugadorByUser(rs.getString("jugador")));
        m.setApuesta(rs.getFloat("apuesta"));
        j.addTurno(m);
    }

    @Override
    public String getSqlSelect() {
        return "SELECT * FROM juego\n"
                + "LEFT OUTER JOIN movimiento \n"
                + "ON movimiento.OID = juego.OID \n"
                + "ORDER BY juego.oid, numturno;";
    }

    @Override
    public void crearNuevo() {
        j = new Juego();
    }

    @Override
    public Object getObjeto() {
        return j;
    }

}
