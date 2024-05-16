package Milogica;

import static Conexion.ConfigBD.Usuario;
import DatosGN.Usuario;
import DatosGN.servicio;
import Pantalla1.Bdconexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioController {

    public ArrayList getUsuarios() {
        ArrayList Usuarios = new ArrayList();
        String SQL = "select * from usuario";
        ResultSet result;
        Bdconexion mConexion = new Bdconexion();
        mConexion.conectar();
        result = mConexion.ejecutarConsulta(SQL);

        try {
            while (result.next()) {
                Usuario mUsuario = new Usuario();
                mUsuario.setId_usuario(result.getInt("id_usuario"));
                mUsuario.setNombre_usuario(result.getString("nombre_usuario"));
                mUsuario.setConstrasenia(result.getString("contrasenia"));
                mUsuario.setId_detalles(result.getInt("id_detalles"));
                Usuarios.add(mUsuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Usuarios;
    }

    public int Agregar(Usuario mUsuario) {
        String SQL = "insert into usuario (nombre_usuario, contrasenia, id_detalles) values (?, ?, ?)";
        PreparedStatement preparedStatement = null;
        int last_inserted_id = -1;
        try {
            Bdconexion mConexion = new Bdconexion();
            mConexion.conectar();
            preparedStatement = mConexion.getConexion().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, mUsuario.getNombre_usuario());
            preparedStatement.setString(2, mUsuario.getConstrasenia());
            preparedStatement.setInt(3, mUsuario.getId_detalles());
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
