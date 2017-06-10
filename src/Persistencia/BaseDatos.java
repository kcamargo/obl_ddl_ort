/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author docenteFI
 */
public class BaseDatos {
    
    private static final BaseDatos instancia = new BaseDatos();
    private Connection conexion;
    private Statement sentencia;
    
    private BaseDatos() {
    }
    public static BaseDatos getInstancia() {
        return instancia;
    }
    public void conectar(String url,String usr,String pass){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usr,pass);
            sentencia = conexion.createStatement();
        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la base:" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
    public void desconectar(){
        try {
            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {
        }
    }
    public ResultSet consultar(String sql){
        try {
            return sentencia.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("Error al consultar:" + ex.getMessage());
            System.out.println("SQL:" + sql);
        }
        return null;
    }
    public int modificar(String sql){
        try {
            return sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Error al modificar:" + ex.getMessage());
            System.out.println("SQL:" + sql);
        }
        return -1;
    }

    //OJO en mySql las tablas deben ser INNODB no MyISAM
    public boolean transaccion(ArrayList<String> sqls) {
        
        try {
            conexion.setAutoCommit(false); //begin T
            for(String sql:sqls){
                if(modificar(sql)==-1){
                    System.out.println("Rollback!");
                    conexion.rollback();
                    return false;
                }
            }
            conexion.commit(); //todo OK guardo
                
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar transaccion:" + ex.getMessage());
        }finally{
            try {
                conexion.setAutoCommit(true); //end T
            } catch (SQLException ex) { }
        }
        return true;
    }
       
}
