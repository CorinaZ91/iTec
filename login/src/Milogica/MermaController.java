package Milogica;

import DatosGN.merma;
import Pantalla1.Bdconexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MermaController {

    public ArrayList getMermas() {
        ArrayList Mermas = new ArrayList();
        String SQL = "select * from merma";
        ResultSet result;
        Bdconexion mConexion = new Bdconexion();
        mConexion.conectar();
        result = mConexion.ejecutarConsulta(SQL);

        try {
            while (result.next()) {
                merma mMerma = new merma();
                mMerma.setId_merma(result.getInt("id_merma"));
                mMerma.setCantidad(result.getInt("cantidad"));
                Mermas.add(mMerma);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Mermas;
    }

    public int Agregar(merma mMerma) {
        String SQL = "insert into merma (cantidad) values (?)";
        PreparedStatement preparedStatement = null;
        int last_inserted_id = -1;

        try {
            Bdconexion mConexion = new Bdconexion();
            mConexion.conectar();
            preparedStatement = mConexion.getConexion().prepareStatement(SQL);

            preparedStatement.setInt(1, mMerma.getCantidad());
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
        public boolean Actualizar(merma mMerma) {
        String SQL = "UPDATE merma SET cantidad = cantidad + ? WHERE id_merma = ?";
        PreparedStatement preparedStatement = null;
        boolean isUpdated = false;

        try {
            Bdconexion mConexion = new Bdconexion();
            mConexion.conectar();
            preparedStatement = mConexion.getConexion().prepareStatement(SQL);

            preparedStatement.setInt(1, mMerma.getCantidad());
            preparedStatement.setInt(2, mMerma.getId_merma());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                isUpdated = true;
            }

        } catch (SQLException e) {
            Logger.getLogger(MermaController.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    Logger.getLogger(MermaController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return isUpdated;
    }
}
