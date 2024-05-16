package Pantalla1;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.DefaultFormatter;

public class SpinnerEditor extends DefaultCellEditor {

    private JSpinner input;

    public SpinnerEditor() {
        super(new JCheckBox());
        input = new JSpinner();
        SpinnerNumberModel minimo = new SpinnerNumberModel(1,1 , 1000, 1);
       // SpinnerNumberModel minimo = (SpinnerNumberModel) input.getModel();
        minimo.setMinimum(1);
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) input.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) editor.getTextField().getFormatter();
        formatter.setCommitsOnValidEdit(true);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        super.getTableCellEditorComponent(table, value, isSelected, row, column);
        if (value != null) {
            int qty = Integer.parseInt(value.toString());
            input.setValue(qty);
        }
        else{
            input.setValue(1);
        }
        return input;
    }

    @Override
    public Object getCellEditorValue() {
        return input.getValue();
    }
}
