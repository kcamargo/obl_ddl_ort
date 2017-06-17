/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Dominio.Usuario;

/**
 *
 * @author docenteFI
 */
public class Persistencia {
    
    private final BaseDatos bd = BaseDatos.getInstancia();
    
     public int proximoOid(){
        String sql = "SELECT valor FROM parametros WHERE nombre = 'oid'";
        ResultSet rs = bd.consultar(sql);
        try {
            if(rs.next()){
                int oidActual = rs.getInt("valor");
                sql = "UPDATE parametros set valor = " + (oidActual+1) + 
                      " WHERE nombre = 'oid'";
                bd.modificar(sql);
                return oidActual;
            }else System.out.println("FALTA REGISTRO OID");
        } catch (SQLException ex) {
            System.out.println("Error al obtener proximo oid");
        }
        return -1;
    }
    
    public void guardar(Mapeador p){
        if(p.getOid()==0){
            insertar(p);
        }else actualizar(p);
    }

    private void insertar(Mapeador p) {
        int oid = proximoOid();
        p.setOid(oid);
        ArrayList<String> sqls = p.getSqlInsert();
        if(!bd.transaccion(sqls)){
            System.out.println("Error al insertar");
            p.setOid(0);
        }
    }

    private void actualizar(Mapeador p) {
        ArrayList<String> sqls = p.getSqlUpdate();
        if(!bd.transaccion(sqls)){
            System.out.println("Error al actualizar");
        }
    }
    public void borrar(Mapeador p){
        ArrayList<String> sqls = p.getSqlDelete();
        if(bd.transaccion(sqls)){
            p.setOid(0);
        }else System.out.println("Error al borrar");
    }
    
    
    public void restaurar(Mapeador p){
        String sql = p.getSqlRestaurar();
        ResultSet rs = bd.consultar(sql);
        try {
            while(rs.next()){
                 p.leer(rs);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error al restaurar: " + ex.getMessage());
        }
    }
    public ArrayList obtenerTodos(Mapeador p){
        return buscar(p,"");
    }
    public ArrayList buscar(Mapeador p, String where){
        String sql = p.getSqlSelect() + " " + where;
        ResultSet rs = bd.consultar(sql);
        ArrayList resultado=new ArrayList();
        int oid,oidAnt=-1;
        try {
            Object o;
            while(rs.next()){
                oid = rs.getInt("oid"); //restriccion
                if(oid!=oidAnt){
                    p.crearNuevo();
                    o = p.getObjeto();
                    resultado.add(o);
                    oidAnt = oid;
                }
                p.leer(rs);
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener todos:" + ex.getMessage());
        }
        return resultado;
    }
}
