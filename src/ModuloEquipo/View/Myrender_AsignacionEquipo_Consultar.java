/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ModuloEquipo.View2;

import java.awt.Color;
import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author wilbert
 */
public class Myrender_AsignacionEquipo_Consultar extends DefaultTableCellRenderer{
     private int columna_patron ;
     private String fecha ;
     
    public Myrender_AsignacionEquipo_Consultar(int Colpatron)
    {
        this.columna_patron = Colpatron;
        //this.fecha = fecha;
    }
 
    public Component getTableCellRendererComponent ( JTable table, Object value, boolean selected, boolean focused, int row, int column )
    {
        setBackground(Color.white);//color de fondo
        table.setForeground(Color.black);//color de texto
        if(table.getValueAt(row,columna_patron).toString().equals("ROJO"))  {
            setBackground( new Color(226, 101, 101) );
        }
        if( table.getValueAt(row,columna_patron).toString().equals("AMARILLO"))  {
            setBackground( new Color(241, 242, 166) );
        }
        if( table.getValueAt(row,columna_patron).toString().equals("VERDE"))  {
            setBackground( new Color(173, 232, 156) );
        }
        // SI EN CADA FILA DE LA TABLA LA CELDA 8 ES IGUAL A ACTIVO COLOR AZUL
        /*if( diferenciaFecha(table.getValueAt(row,columna_patron).toString()).equals("ROJO"))  setBackground( new Color(226, 101, 101) );
        if( diferenciaFecha(table.getValueAt(row,columna_patron).toString()).equals("AMARILLO"))  setBackground( new Color(241, 242, 166) );
        if( diferenciaFecha(table.getValueAt(row,columna_patron).toString()).equals("VERDE"))  setBackground( new Color(173, 232, 156) );*/
    
    
    
    super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
 }
    
    public String diferenciaFecha(String fechaMaximaExportacion) throws ParseException{
        //Date fechaActual = new Date(); 
        //String  Fecha =new SimpleDateFormat("yyyy-MM-dd").format(fecha);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaHoy=dateFormat.parse(fecha);
        Date fechaDevo=dateFormat.parse(fechaMaximaExportacion);
        int diasAtraso=(int) ((fechaDevo.getTime() -fechaHoy.getTime())/86400000);
        if(diasAtraso >= 61){
            return "VERDE";
        }else{
            if(diasAtraso >= 31 && diasAtraso <= 60){
                return "AMARILLO";
            }else{
                return "ROJO";
            }
        }        
    }
}
