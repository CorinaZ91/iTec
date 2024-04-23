package Pantalla1;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;

public class Convertidor {

    static public DefaultTableModel convertir(ResultSet tabla) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            ResultSetMetaData rsMd = tabla.getMetaData();
            //La cantidad de columnas que tiene la consulta
            int cantidadColumnas = rsMd.getColumnCount();
            //Establecer como cabezeras el nombre de las colimnas
            for (int i = 1; i <= cantidadColumnas; i++) {
                modelo.addColumn(rsMd.getColumnLabel(i));
            }
            //Creando las filas para el JTable
            while (tabla.next()) {
                Object[] fila = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = tabla.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            return modelo;
        } catch (Exception error) {
            return new DefaultTableModel();
        }
    }
}
