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
    
    private static Persistencia instancia = new Persistencia();
    private BaseDatos bd = BaseDatos.getInstancia();

    public static Persistencia getInstancia() {
        return instancia;
    }
    private Persistencia() {
    }
    
     public int proximoOid(){ //cambiar string
        String sql = "SELECT valor FROM parametros WHERE nombre = 'oid'";//cambiar string
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
    
    public void guardar(Mapeador obj){
        if(obj.getOid()<1) 
            insertar(obj);
        else modificar(obj);
    }
    
    private void insertar(Mapeador obj) {
      int oid = proximoOid();
      obj.setOid(oid);
      String sql = obj.getSqlInsertar();
      int f = bd.modificar(sql);
      if(f<1){ 
          obj.setOid(0);
          System.out.println("Error al insertar objeto");
      }
            
    }
    private void modificar(Mapeador obj) {
        String sql = obj.getSqlModificar();
        if(bd.modificar(sql)<1){
            System.out.println("Error al modificar objeto");
        }
    }
    public void borrar(Mapeador obj){
        String sql = obj.getSqlBorrar();
        int f = bd.modificar(sql);
        if(f>0) obj.setOid(0); //ya no esta en la base
    }            
    public void restaurar(Mapeador obj){
        if(obj.getOid()>0){
            String sql = obj.getSqlRestaurar();
            ResultSet rs = bd.consultar(sql);
            try {
                rs.next();
                obj.leer(rs);
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Error al restaurar:" + ex.getMessage());
            }
        }
    }
   
    public ArrayList buscar(Mapeador map,String condWhere){
        String sql = map.getSqlSeleccionar();
        if(condWhere!=null) sql += " WHERE " + condWhere;
        
        ResultSet rs = bd.consultar(sql);
        ArrayList lista = new ArrayList();
        try {
            
            while(rs.next()){
                map.crearNuevo();
                map.leer(rs);
                lista.add(map.getObjeto());
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar:" + ex.getMessage());
        }
        return lista;
    }
    public ArrayList obtenerTodos(Mapeador map){
        return buscar(map,null);
    }
            
            
            
    
}
