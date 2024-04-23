/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Milogica;

import DatosGN.Datos_usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import Pantalla1.Bdconexion;

/**
 *
 * @author Taller
 */
public class Control {
    
    public void Registro(Datos_usuario mSemilla) throws SQLException {
        String SQL = "insert into semilla values "
                + "(null,'?1','?2','?3','?4','?5')";
        SQL = SQL.replace("?1", mSemilla.Nombreu());
        SQL = SQL.replace("?2", mSemilla.Appaterno());
        SQL = SQL.replace("?3", mSemilla.Appamaterno());
        SQL = SQL.replace("?4", mSemilla.correo12());
        SQL = SQL.replace("?5", mSemilla.tel());
        

        Bdconexion mConexion = new Bdconexion();
        mConexion.conectar();
        mConexion.ejecutarActualizacion(SQL);

    }

    public void eliminarSemilla(Datos_usuario mSemilla) throws SQLException {
        String SQL = "delete from semilla where idsemilla='?1'";
        SQL = SQL.replace("?1", String.valueOf(mSemilla.Nombreu()));
        Bdconexion mConexion = new Bdconexion();
        mConexion.conectar();
        mConexion.ejecutarActualizacion(SQL);
    }

    public void modificarSemilla(Semilla mSemilla) throws SQLException {
        String SQL = "update semilla set "
                + "nombre_semilla='?1',"
                + "descripcion='?2',"
                + "precio_kg='?3' "
                + " where idsemilla='?4'";
        SQL = SQL.replace("?1", mSemilla.getNombre_semilla());
        SQL = SQL.replace("?2", mSemilla.getDescripcion());
        SQL = SQL.replace("?3", String.valueOf(mSemilla.getPrecio_kg()));
        SQL = SQL.replace("?4", String.valueOf(mSemilla.getIdsemilla()));
        BDconexion mConexion = new BDconexion();
        mConexion.conectar();
        mConexion.ejecutarActualizacion(SQL);

    }

    public ResultSet consultarTodasSemillas() {
        String SQL = "select * from semilla";
        ResultSet ListaSemillas;
        BDconexion mConexion = new BDconexion();
        mConexion.conectar();
        ListaSemillas = mConexion.ejecutarConsulta(SQL);

        return ListaSemillas;
    }
}
