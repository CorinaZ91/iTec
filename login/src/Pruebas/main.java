
package Pruebas;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class main extends JFrame {
    private JTable table;
    private JLabel positionLabel;

    public main() {
        setTitle("Obtener posición de celda en JTable");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Creamos algunos datos de ejemplo para la tabla
        Object[][] data = {
            {"1", "John", "Doe"},
            {"2", "Jane", "Smith"},
            {"3", "Bob", "Johnson"}
        };
        
        // Definimos las columnas
        String[] columns = {"ID", "First Name", "Last Name"};

        // Creamos la tabla con los datos y las columnas
        table = new JTable(data, columns);
        
        // Agregamos un listener para detectar el cambio de selección en la tabla
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Obtenemos el índice de la fila y columna seleccionada
                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();
                
                // Si la selección no es continua o está vacía, no hacemos nada
                if (!e.getValueIsAdjusting() && row != -1 && column != -1) {
                    // Obtenemos el rectángulo que representa la celda seleccionada
                    Rectangle cellRect = table.getCellRect(row, column, true);
                    
                    // Obtenemos las coordenadas x e y del rectángulo
                    int x = cellRect.x;
                    int y = cellRect.y;
                    
                    // Actualizamos la etiqueta con la posición de la celda
                    positionLabel.setText("Posición de la celda seleccionada: (" + x + ", " + y + ")");
                }
            }
        });
        
        // Creamos una etiqueta para mostrar la posición de la celda seleccionada
        positionLabel = new JLabel("Posición de la celda seleccionada: ");
        
        // Agregamos la tabla y la etiqueta al contenedor
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
        getContentPane().add(positionLabel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }
}

