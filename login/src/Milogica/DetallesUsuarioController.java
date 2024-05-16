package Milogica;

import DatosGN.Datos_usuario;
import Pantalla1.Bdconexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DetallesUsuarioController {

    public ArrayList getDetallesUsuarios() {
        ArrayList detallesUsuarios = new ArrayList();
        String SQL = "select * from detalles_usuario";
        ResultSet result;
        Bdconexion mConexion = new Bdconexion();
        mConexion.conectar();
        result = mConexion.ejecutarConsulta(SQL);

        try {
            while (result.next()) {
                Datos_usuario mDetalles = new Datos_usuario();
                mDetalles.setId_detalles(result.getInt("id_detalles"));
                mDetalles.setNombre(result.getString("nombre"));
                mDetalles.setAp_paterno(result.getString("ap_paterno"));
                mDetalles.setAp_materno(result.getString("ap_materno"));
                mDetalles.setCorreo(result.getString("correo"));
                mDetalles.setTelefono(result.getString("telefono"));

                detallesUsuarios.add(mDetalles);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiciosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detallesUsuarios;
    }

    public int Agregar(Datos_usuario mDetalles) {

        String SQL = "insert into detalles_usuario (nombre, ap_paterno, ap_materno, correo, telefono) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        int last_inserted_id = -1;
        try {
            Bdconexion mConexion = new Bdconexion();
            mConexion.conectar();
            preparedStatement = mConexion.getConexion().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, mDetalles.getNombre());
            preparedStatement.setString(2, mDetalles.getAp_paterno());
            preparedStatement.setString(3, mDetalles.getAp_materno());
            preparedStatement.setString(4, mDetalles.getCorreo());
            preparedStatement.setString(5, mDetalles.getTelefono());
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
