package ModuloPalero.View;
 
import ConnectionDB.Conexion_DB_costos_vg;
import ModuloCarbon.View.*;
import ModuloCarbon.Controller2.ControlDB_MvtoCarbon;
import ModuloCarbon.Model.MvtoCarbon;
import ModuloCarbon.Model.MvtoCarbon_ListadoEquipos;
import ModuloPalero.Controller.ControlDB_LiquidacionPalero;
import ModuloPalero.Model.MvtoCarbon_ListadoEquipos_LiquidacionPaleros;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
 
public class Worker_PaleroMarcacionPersonas extends SwingWorker{
    private final JProgressBar progreso;
    private  ArrayList<MvtoCarbon_ListadoEquipos_LiquidacionPaleros> listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros;
    private final Usuario us;
    private String tipoConexion;
    private Connection conexion=null;
    private final JButton boton;
    private JTable tabla;
    private JLabel Label_level;
    Worker_PaleroMarcacionPersonas(JLabel Label_level,JProgressBar barra, JButton boton,JTable tabla, ArrayList<MvtoCarbon_ListadoEquipos_LiquidacionPaleros> listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros, Usuario user, String tipoConexion) {
        progreso = barra;
        this.tabla=tabla;
        this.boton=boton;
        progreso.setStringPainted(true);
        this.tipoConexion= tipoConexion;
        this.listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros=listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros;
        this.us=user;
        this.Label_level=Label_level;
    }
    @Override
    public Double doInBackground() throws Exception {
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        
        
        boton.setEnabled(false);
        double value= ((double)100/(double)listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros.size());
        double contador=0;
        
        //Consultamos todas las personas que pertenecen a los equipos según vehiculos
         for(int i=0; i < listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros.size(); i++){
            //for(MvtoCarbon_ListadoEquipos_LiquidacionPaleros objeto : listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros){
            contador += value;
            publish((int)contador);
                MvtoCarbon_ListadoEquipos_LiquidacionPaleros objeto=new ControlDB_LiquidacionPalero(tipoConexion).procesarLiquidacion2(listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros.get(i));
                if(objeto != null){
                    listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros.set(i, objeto);
                }else{
                    listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros.remove(i);
                    i--;
                }
                System.out.println(""+contador);
            //}
         }
        
        if (listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros != null) {
            DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"ITEM", "CÓDIGO", "FECHA_REGISTRO", "AÑO", "MES", "DIA", "CLIENTE", "C.O", "SUBCENTRO", "C.C. AUXILIAR", "EQUIPO", "PERSONA", "PLACA", "PESO_NETO", "PESO_ASIGNADO", "PESO_ENTRADA", "PESO_SALIDA", "FECHA_TARA", "FECHA_DESTARE"});
            int contadorT = 0;
            DecimalFormat formato2 = new DecimalFormat("0.00");
            //Borramos la tabla para recargar los nuevos valores
            for (MvtoCarbon_ListadoEquipos_LiquidacionPaleros objeto : listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros) {
                for (int i = 0; i < objeto.getMvtoEquipo().getAsignacionEquipo().getEquipo().getListadoPersonas().size(); i++) {
                    String[] registro = new String[19];
                    registro[0] = "" + (contadorT + 1);
                    registro[1] = "" + objeto.getMvtoCarbon().getCodigo();
                    registro[2] = "" + objeto.getMvtoCarbon().getFechaRegistro();
                    registro[3] = "" + objeto.getAno();
                    registro[4] = "" + objeto.getMes();
                    registro[5] = "" + objeto.getDia();
                    registro[6] = "" + objeto.getMvtoCarbon().getCliente().getDescripcion();
                    registro[7] = "" + objeto.getMvtoCarbon().getCentroOperacion().getDescripcion();
                    registro[8] = "" + objeto.getMvtoCarbon().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                    registro[9] = "" + objeto.getMvtoCarbon().getCentroCostoAuxiliar().getDescripcion();
                    registro[10] = "" + objeto.getMvtoEquipo().getAsignacionEquipo().getEquipo().getDescripcion() + " " + objeto.getMvtoEquipo().getAsignacionEquipo().getEquipo().getModelo();
                    registro[11] = "" + objeto.getMvtoEquipo().getAsignacionEquipo().getEquipo().getListadoPersonas().get(i).getNombre() + " " + objeto.getMvtoEquipo().getAsignacionEquipo().getEquipo().getListadoPersonas().get(i).getApellido();
                    registro[12] = "" + objeto.getMvtoCarbon().getPlaca();
                    registro[13] = "" + objeto.getMvtoCarbon().getPesoNeto();
                    registro[14] = "" + formato2.format((Double.parseDouble(objeto.getMvtoCarbon().getPesoNeto()) / objeto.getMvtoEquipo().getAsignacionEquipo().getEquipo().getListadoPersonas().size()));   //;//" objeto.getMvtoCarbon().getPesoNeto();
                    registro[15] = "" + objeto.getMvtoCarbon().getPesoVacio();
                    registro[16] = "" + objeto.getMvtoCarbon().getPesoLleno();
                    //      mvto_listEquipo.getMvtoEquipo().setCostoTotal(formato2.format((totalHoras * valorHora )));
                    listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros.get(contadorT).getMvtoEquipo().getAsignacionEquipo().getEquipo().getListadoPersonas().get(i).setPesoTrabajadoKilogramo(formato2.format(Double.parseDouble(objeto.getMvtoCarbon().getPesoNeto()) / objeto.getMvtoEquipo().getAsignacionEquipo().getEquipo().getListadoPersonas().size()));
                    registro[17] = "" + objeto.getMvtoCarbon().getFechaEntradaVehiculo();
                    registro[18] = "" + objeto.getMvtoCarbon().getFecha_SalidaVehiculo();
                    //registro[19] = "" + objeto.getColor();
                    modelo.addRow(registro);
                }
                contadorT++;
            }
            tabla.setModel(modelo);
            //tabla.setDefaultRenderer(Object.class, new Myrender_LiquidacionPalero_Registrar(2));
            resizeColumnWidth(tabla);
            boton.setText("SIGUIENTE");
            Label_level.setText("NIVEL " + 2);
            //boton.setEnabled(true);
        }
        boton.setEnabled(true);
        
       /* for(int i=0; i< listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros.size();i++){
            contador += value;           
            publish((int)contador);
            listadoMvtoCarbon.set(i, new ControlDB_MvtoCarbon(tipoConexion).ProcesarEnCcargaGP(listadoMvtoCarbon.get(i),us));
            System.out.println(""+contador);
        }*/
        
        /*for (Cliente listadoMvtoCarbon1 : listadoMvtoCarbon) {
            contador += value;           
            publish((int)contador);
            try {
                if(listadoCliente1.getCodigo().equalsIgnoreCase("")){
                }else{
                    if(listadoCliente1.getDescripcion().equalsIgnoreCase("")){
                    }else{
                        if(listadoCliente1.getEstado()== null){  
                        }else{
                            System.out.println(""+contador);
                            new ControlDB_Cliente().registrar(listadoCliente1, user);
                        }
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Cliente_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
        return 100.00;
    }

    @Override
    protected void done() {
        progreso.setValue(100);
        //progreso.setString("Sincronización Finalizada");
        progreso.setBackground(Color.GREEN);
        progreso.show(false);
    }
    @Override
    protected void process(List chunks) {
        progreso.setValue((int) chunks.get(0));
    }
    
   /* public ArrayList<MvtoCarbon> retornarListadoMvtoCarbon(){
        return listadoMvtoCarbon;
    }*/

    public ArrayList<MvtoCarbon_ListadoEquipos_LiquidacionPaleros> getListado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros() {
        return listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros;
    }

    public void setListado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros(ArrayList<MvtoCarbon_ListadoEquipos_LiquidacionPaleros> listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros) {
        this.listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros = listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros;
    }
    //Ajustar aNcho de las tablas de acuerdo al contenido
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
   
}
