/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author docenteFI
 */
public interface Mapeador {

    public int getOid();
    
    public void setOid(int oid);

    public String getSqlInsertar();

    public String getSqlModificar();

    public String getSqlBorrar();

    public String getSqlRestaurar();

    public void leer(ResultSet rs) throws SQLException;

    public String getSqlSeleccionar();

    public void crearNuevo();

    public Object getObjeto();

    
    
}
