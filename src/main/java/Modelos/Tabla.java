//Autor: MARIA ISABEL ROMANO
package Modelos;

import java.awt.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class Tabla extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(
        JTable tabla, Object valor, boolean isSelected, boolean hasFocus, int fila, int columna) {
        super.getTableCellRendererComponent(tabla, valor, isSelected, hasFocus, fila, columna);
        
        //Establecer color de fondo y color de fuente para las celdas que no pertenecen a estado
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);

        //Obtener el índice de la columna que siempre es la última
        int estadoColumnIndex = tabla.getColumnCount() - 1; 
        
        //Aplicar colores solo a la columna estado
        if (columna == estadoColumnIndex && valor != null) {
            String cellValue = valor.toString();
            if (cellValue.equals("Inactivo")) {
                //Rojo si es inactivo
                setBackground(Color.RED);
                setForeground(Color.BLACK);
            } else { //Verde si es activo
                setBackground(Color.GREEN);
                setForeground(Color.BLACK);
            }
        }
        return this;
    }
}
