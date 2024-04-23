/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pantalla1;
import java.sql.*;
import javax.swing.JOptionPane;



/**
 *
 * @author Taller
 */
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
    
}
