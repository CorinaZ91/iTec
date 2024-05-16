/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pantalla1;
<<<<<<< Updated upstream
=======
import Conexion.ConfigBD;
>>>>>>> Stashed changes
import java.sql.*;
import javax.swing.JOptionPane;



/**
 *
 * @author Taller
 */
<<<<<<< Updated upstream
public class Bdconexion {
    Connection conectar=null;
    public Connection conectar(){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conectar=DriverManager.getConnection("jdbc:mysql://localhost:3306/impretec","root","");
                JOptionPane.showMessageDialog(null,"Conexion exitosa","Conexion",JOptionPane.INFORMATION_MESSAGE);
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null,"Sin conexion"+e,"Conexion",JOptionPane.ERROR);
            }
            return conectar;
        }
=======
@SuppressWarnings("empty-statement")
public class Bdconexion {
    private Connection conexion;
    private Statement comando;
    
    
    Connection conectar=null;
    public boolean conectar(){
            try {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            this.conexion = DriverManager.getConnection("jdbc:mysql://" + ConfigBD.Servidor + "/" + ConfigBD.BaseDatos,
                    ConfigBD.Usuario, ConfigBD.Contrasenna);
            this.comando = conexion.createStatement();
            return true;
        } catch (Exception error) {
            System.out.println("Error " + error.toString());
            return false;
        }
    }

    public Connection getConexion() {
        return conexion;
    }
    
    
  
    public int ejecutarActualizacion(String SQL) throws SQLException {
        try {
            int FilasAfectadas;
            FilasAfectadas = this.comando.executeUpdate(SQL);
            System.out.println(SQL + " Ejecutada");
            return FilasAfectadas;
        } catch (Exception error) {
            System.out.println("Error " + error.toString());
            return -1;
        }
    }
    public ResultSet ejecutarConsulta(String SQL) {
        try {
            ResultSet resultado = this.comando.executeQuery(SQL);
            System.out.println(SQL + " Ejecutada");
            return resultado;
        } catch (Exception error) {
            System.out.println("Error " + error.toString());
            return null;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
>>>>>>> Stashed changes
    
}
