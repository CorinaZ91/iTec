package Milogica;

import DatosGN.detalleventa;
import Pantalla1.Bdconexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerDetalleVenta {

    public ArrayList getDetallesVentas() {
        ArrayList detallesVentas = new ArrayList();
        String SQL = "select * from detalleventa";
        ResultSet result;
        Bdconexion mConexion = new Bdconexion();
        mConexion.conectar();
        result = mConexion.ejecutarConsulta(SQL);
        
        try {
            while (result.next()) {
                detalleventa mDetVenta = new detalleventa();
                mDetVenta.setId_detalleVenta(result.getInt("id_detalleVenta"));
                mDetVenta.setId_venta(result.getInt("id_venta"));
                mDetVenta.setId_merma(result.getInt("id_merma"));
                mDetVenta.setCantidad(result.getInt("cantidad"));
                mDetVenta.setPrecio(result.getFloat("precio"));
                
                detallesVentas.add(mDetVenta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detallesVentas;
    }
    
    public int Agregar(detalleventa mDetVenta) {
        String SQL = "insert into detalleventa (id_venta, id_merma, cantidad, precio) values (?,?,?,?)";
        PreparedStatement preparedStatement = null;
        int last_inserted_id = -1;

        try {
            Bdconexion mConexion = new Bdconexion();
            mConexion.conectar();
            preparedStatement = mConexion.getConexion().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, mDetVenta.getId_venta());
            preparedStatement.setInt(2, 1);
            preparedStatement.setInt(3, mDetVenta.getCantidad());
            preparedStatement.setFloat(4, mDetVenta.getPrecio());
            
            preparedStatement.execute();
            ResultSet Resultado = (ResultSet) preparedStatement.getGeneratedKeys();
            if (Resultado.next()) {
                last_inserted_id = Resultado.getInt(1);
            }
            
            return last_inserted_id;
            
        } catch (Exception e) {
            return last_inserted_id;
        }
    }
}
