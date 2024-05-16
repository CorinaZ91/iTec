package Milogica;

import Conexion.ConfigBD;
import DatosGN.servicio;
import Pantalla1.Bdconexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiciosController {

    public ArrayList getServices() {
        ArrayList Servicios = new ArrayList();
        String SQL = "select * from servicio";
        ResultSet result;
        Bdconexion mConexion = new Bdconexion();
        mConexion.conectar();
        result = mConexion.ejecutarConsulta(SQL);

        try {
            while (result.next()) {
                servicio mServicios = new servicio();
                mServicios.setId_servicio(result.getInt("id_servicio"));
                mServicios.setId_producto(result.getInt("id_producto"));
                mServicios.setDescripcion(result.getString("descripcion"));
                mServicios.setPrecio(result.getFloat("precio"));
                mServicios.setImgen(result.getString("imagen"));
                Servicios.add(mServicios);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Servicios;
    }

    public boolean Agregar(servicio mServicio) {
        try {
            String SQL = "insert into servicio values (null,'?1','?2','?3','?4')";
            SQL = SQL.replace("?1", "1");
            SQL = SQL.replace("?2", mServicio.getDescripcion());
            SQL = SQL.replace("?3", String.valueOf(mServicio.getPrecio()));
            SQL = SQL.replace("?4", mServicio.getImgen());

            Bdconexion mConexion = new Bdconexion();
            mConexion.conectar();
            mConexion.ejecutarActualizacion(SQL);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
