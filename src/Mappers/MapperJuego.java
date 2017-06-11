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
    public ArrayList<String> getSqlInsertar() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ArrayList<String> s = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(j.ultDescarte());
        s.add("INSERT INTO Juego VALUES(" + getOid() + ",'" + j.getJug1().getNombreUsuario()
                + "','" + j.getJug2().getNombreUsuario() + "','"
                + j.getGanador().getNombreUsuario() + "'," + j.getApuestaActual()
                + ",'" + date + "'," + j.getApuestaInicial() + ");");
        generarLineas(s);
        return s;
    }

    private void generarLineas(ArrayList<String> sqls) {
        int nro = 1;
        for (Movimiento t : j.getMovimientos()) {
            sqls.add("INSERT INTO Movimiento VALUES('" + t.getJugador().getNombreUsuario()
                    + "'," + j.getOid() + "," + nro + "," + t.getApuesta() + "," + t.getCasilleroDestapado() + ")");
            nro++;
        }
    }

    @Override
    public String getSqlModificar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSqlBorrar() {
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
        Movimiento t = new Movimiento(Fachada.getInstancia().getJugadorByUser(rs.getString("jugador")));
        t.setApuesta(rs.getFloat("apuesta"));
        j.addTurno(t);
    }

    @Override
    public String getSqlSeleccionar() {
        return "SELECT * FROM JUEGO\n"
                + "LEFT OUTER JOIN Turno \n"
                + "ON Turno.OID = Juego.OID \n"
                + "ORDER BY Juego.oid, numturno;";
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
