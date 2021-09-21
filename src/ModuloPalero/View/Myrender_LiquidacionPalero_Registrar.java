package ModuloPalero.View;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Myrender_LiquidacionPalero_Registrar extends DefaultTableCellRenderer{
     private final int columna_patron ;
     private String fecha ;
     
    public Myrender_LiquidacionPalero_Registrar(int Colpatron){
        this.columna_patron = Colpatron;
        //this.fecha = fecha;
    }
 
     @Override
    public Component getTableCellRendererComponent ( JTable table, Object value, boolean selected, boolean focused, int row, int column ){
        setBackground(Color.white);//color de fondo
        table.setForeground(Color.black);//color de texto
        if(table.getValueAt(row,columna_patron).toString().equals("ROJO"))  {
            setBackground( new Color(226, 101, 101) );
        }
        if( table.getValueAt(row,columna_patron).toString().equals("VERDE"))  {
            setBackground( new Color(173, 232, 156) );
        }
    
    super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
    }
}
