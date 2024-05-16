/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Milogica;

import DatosGN.Datos_usuario;
import DatosGN.detalles2;
import DatosGN.detallesproducto;
import Pantalla1.Bdconexion;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Taller
 */
public class Control3 {
    
    public void agregarproducto(detallesproducto mSemilla) throws SQLException {
        String SQL = "insert into producto values "
                + "(null,'?1')";
        SQL = SQL.replace("?1", mSemilla.descripcion());
        

        Bdconexion mConexion = new Bdconexion();
        mConexion.conectar();
        mConexion.ejecutarActualizacion(SQL);

    }

    public void eliminarSemilla(detallesproducto mSemilla) throws SQLException {
        String SQL = "delete from producto where id_producto='?1'";
        SQL = SQL.replace("?1", String.valueOf(mSemilla.descripcion()));
        Bdconexion mConexion = new Bdconexion();
        mConexion.conectar();
        mConexion.ejecutarActualizacion(SQL);
    }

    public void modificarSemilla(detallesproducto mSemilla) throws SQLException {
        String SQL = "update producto set "
                + "descripcion='?1',";
                
        SQL = SQL.replace("?1", mSemilla.descripcion());
       
        Bdconexion mConexion = new Bdconexion();
        mConexion.conectar();
        mConexion.ejecutarActualizacion(SQL);

    }

    public ResultSet consultarTodasSemillas() {
        String SQL = "select * from detalles_usuario";
        ResultSet ListaSemillas;
        Bdconexion mConexion = new Bdconexion();
        mConexion.conectar();
        ListaSemillas = mConexion.ejecutarConsulta(SQL);

        return ListaSemillas;
    }

    public void Registro(detalles2 mSemilla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
