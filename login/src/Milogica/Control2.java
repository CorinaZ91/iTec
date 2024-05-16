/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Milogica;

import DatosGN.detalles2;
import java.sql.SQLException;
import Pantalla1.Bdconexion;

/**
 *
 * @author Taller
 */
public class Control2 {
    
    public void agregar(detalles2 mcon) throws SQLException {
        String SQL = "insert into usuario values "
                + "(null,'?1',null)";
        SQL = SQL.replace("?1", mcon.Contraseña());
        
        Bdconexion mConexion = new Bdconexion();
        mConexion.conectar();
        mConexion.ejecutarActualizacion(SQL);

    }
    


}


