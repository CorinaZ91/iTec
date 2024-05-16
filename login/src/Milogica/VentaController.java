package Milogica;

import DatosGN.venta;
import Pantalla1.Bdconexion;
import Pantalla1.VentasRealizadas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VentaController {

    public ArrayList getVentas() {
        ArrayList ventas = new ArrayList();
        String SQL = "select * from venta";
        ResultSet result;
        Bdconexion mConexion = new Bdconexion();
        mConexion.conectar();
        result = mConexion.ejecutarConsulta(SQL);

        try {
            while (result.next()) {
                venta mVenta = new venta();
                mVenta.setId_venta(result.getInt("id_venta"));
                mVenta.setId_usuario(result.getInt("id_usuario"));
                java.sql.Date sqlDate = result.getDate("fecha");

                java.time.LocalDate localDate = sqlDate.toLocalDate();
                mVenta.setFecha(localDate);

                mVenta.setTotal(result.getFloat("total"));

                ventas.add(mVenta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ventas;
    }

    public ArrayList getVentasRealizada() {
        ArrayList ventas = new ArrayList();
        String SQL = "SELECT v.fecha,v.id_venta,u.nombre_usuario, v.total FROM venta v LEFT JOIN usuario u on (u.id_usuario = v.id_usuario); ";
        ResultSet result;
        Bdconexion mConexion = new Bdconexion();
        mConexion.conectar();
        result = mConexion.ejecutarConsulta(SQL);

        try {
            while (result.next()) {
                VentasRealizadas mVenta = new VentasRealizadas();
                LocalDate fecha = result.getDate("fecha").toLocalDate();
                mVenta.setFecha(fecha);
                mVenta.setId_venta(result.getInt("id_venta"));
                mVenta.setNombre_usuario(result.getString("nombre_usuario"));
                mVenta.setTotal(result.getFloat("total"));
                ventas.add(mVenta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ventas;
    }

    public int Agregar(venta mVenta) {

        String SQL = "insert into venta (id_usuario, fecha, total) values (?,?,?)";
        PreparedStatement preparedStatement = null;
        int last_inserted_id = -1;
        try {
            Bdconexion mConexion = new Bdconexion();
            mConexion.conectar();
            preparedStatement = mConexion.getConexion().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());

            // Establecer el parámetro de fecha
            preparedStatement.setInt(1, mVenta.getId_usuario());
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setFloat(3, mVenta.getTotal());

            preparedStatement.execute();
            ResultSet Resultado = (ResultSet) preparedStatement.getGeneratedKeys();

            if (Resultado.next()) {
                last_inserted_id = Resultado.getInt(1);
            }

//            mConexion.ejecutarActualizacion(SQL);
            return last_inserted_id;
        } catch (Exception e) {
            return last_inserted_id;
        }
    }
}
