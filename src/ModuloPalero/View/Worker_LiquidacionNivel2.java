package ModuloPalero.View;
 
import ConnectionDB.Conexion_DB_costos_vg;
import ModuloCarbon.View.*;
import ModuloCarbon.Controller2.ControlDB_MvtoCarbon;
import ModuloCarbon.Model.MvtoCarbon;
import ModuloCarbon.Model.MvtoCarbon_ListadoEquipos;
import ModuloPalero.Controller.ControlDB_LiquidacionPalero;
import ModuloPalero.Model.ConfiguracionLiquidacion;
import ModuloPalero.Model.EquipoLiquidacion;
import ModuloPalero.Model.MvtoCarbon_ListadoEquipos_LiquidacionPaleros;
import ModuloPalero.Model.MvtoPaleroPreliquidacionTEMP;
import ModuloPalero.Model.MvtoVehiculoPalerosTEMP;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
 
public class Worker_LiquidacionNivel2 extends SwingWorker{
    private final JProgressBar progreso;
    private  ArrayList<MvtoCarbon_ListadoEquipos_LiquidacionPaleros> listadoMvtoCarbon_ListadoEquipos_LiquidacionPaleros;
    private final Usuario us;
    private String tipoConexion;
    private Connection conexion=null;
    private final JButton boton;
    private JTable tabla;
    private ConfiguracionLiquidacion configuracionLiquidacion;
    ArrayList<String> listadoPreliquidacion=null;
    private JLabel Label_level;
    boolean validar= true;
        int result=0;
        ArrayList<MvtoPaleroPreliquidacionTEMP> listado_MvtoPaleroPreliquidacionTEMP= null;
    Worker_LiquidacionNivel2(JLabel Label_level,JProgressBar barra, JButton boton,JTable tabla,ArrayList<MvtoCarbon_ListadoEquipos_LiquidacionPaleros> listadoMvtoCarbon_ListadoEquipos_LiquidacionPaleros, ConfiguracionLiquidacion configuracionLiquidacion,Usuario user, String tipoConexion) {
        progreso = barra;
        this.tabla=tabla;
        this.boton=boton;
        progreso.setStringPainted(true);
        this.tipoConexion= tipoConexion;
        this.listadoMvtoCarbon_ListadoEquipos_LiquidacionPaleros=listadoMvtoCarbon_ListadoEquipos_LiquidacionPaleros;
        this.us=user;
        this.configuracionLiquidacion= configuracionLiquidacion;
        this.Label_level=Label_level;
    }
    @Override
    public Double doInBackground() throws Exception {
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        
        
        boton.setEnabled(false);
        double value= ((double)100/(double)listadoMvtoCarbon_ListadoEquipos_LiquidacionPaleros.size());
        double contador=0;
        
        //Limpiamos las tablas temporales para proceder con el almanenamiento de los datos
        new ControlDB_LiquidacionPalero(tipoConexion).limpiarRegistroLiquidacion();
        
        
        if(listadoMvtoCarbon_ListadoEquipos_LiquidacionPaleros != null){
            listado_MvtoPaleroPreliquidacionTEMP = new ArrayList<>();
            for (MvtoCarbon_ListadoEquipos_LiquidacionPaleros objeto : listadoMvtoCarbon_ListadoEquipos_LiquidacionPaleros) {
                contador += value;
                publish((int)contador);
                for (int i = 0; i < objeto.getMvtoEquipo().getAsignacionEquipo().getEquipo().getListadoPersonas().size(); i++) {
                    MvtoPaleroPreliquidacionTEMP mvtoPaleroPreliquidacionTEMP = new MvtoPaleroPreliquidacionTEMP();
                    mvtoPaleroPreliquidacionTEMP.setMvtoVehiculoPalerosTEMP(new MvtoVehiculoPalerosTEMP(null, objeto, "1"));
                    mvtoPaleroPreliquidacionTEMP.setConfiguracionLiquidacion(configuracionLiquidacion);
                    mvtoPaleroPreliquidacionTEMP.setFecha(objeto.getMvtoCarbon().getFechaRegistro());
                    mvtoPaleroPreliquidacionTEMP.setPersona(objeto.getMvtoEquipo().getAsignacionEquipo().getEquipo().getListadoPersonas().get(i));
                    mvtoPaleroPreliquidacionTEMP.setEquipoLiquidacion(new EquipoLiquidacion(null, objeto.getMvtoEquipo().getAsignacionEquipo().getEquipo(), "1"));
                    DecimalFormat formato3 = new DecimalFormat("0.00"); 
                    mvtoPaleroPreliquidacionTEMP.setPesoAsignado(""+formato3.format((Double.parseDouble(objeto.getMvtoCarbon().getPesoNeto()) / objeto.getMvtoEquipo().getAsignacionEquipo().getEquipo().getListadoPersonas().size())));
                    mvtoPaleroPreliquidacionTEMP.setEstado("1");
                    listado_MvtoPaleroPreliquidacionTEMP.add(mvtoPaleroPreliquidacionTEMP);
                    result= new ControlDB_LiquidacionPalero(tipoConexion).registrar_Preliquidacion2(mvtoPaleroPreliquidacionTEMP, us);
                    if(result ==0){
                        validar=false;
                    }
                }
            }   
        }
        
        
        return 100.00;
    }

    @Override
    protected void done() {
        progreso.setValue(100);
        //progreso.setString("Sincronización Finalizada");
        
        try {
            //Procedemos a registrar las preliquidaciones en las tablar temporales con el objeto listado_MvtoPaleroPreliquidacionTEMP
            //listado_MvtoPaleroPreliquidacionTEMP

            //value= ((double)100/(double)listado_MvtoPaleroPreliquidacionTEMP.size());
            //contador=0;
            
            
//            for(MvtoPaleroPreliquidacionTEMP objeto: listado_MvtoPaleroPreliquidacionTEMP){
//                contador += value;
//                publish((int)contador);
//                result= new ControlDB_LiquidacionPalero(tipoConexion).registrar_Preliquidacion2(objeto, us);
//                if(result ==0){
//                    validar=false;
//                }
//            }

            if(validar=true){
                result=1;
                new ControlDB_LiquidacionPalero(tipoConexion).registrar_PreliquidacionAuditoria(listado_MvtoPaleroPreliquidacionTEMP.get(0),us);
            }
            //int result= new ControlDB_LiquidacionPalero(tipoConexion).registrar_Preliquidacion(listado_MvtoPaleroPreliquidacionTEMP, user);
            if(result==1){
                //JOptionPane.showMessageDialog(null, "Se registro la preliqudacion de forma exitosa","Registro exitoiso", JOptionPane.INFORMATION_MESSAGE);
                listadoPreliquidacion = new ArrayList<>();
                listadoPreliquidacion=new ControlDB_LiquidacionPalero(tipoConexion).consultarPreliquidacion();
                if(listadoPreliquidacion!= null){
                    //Limpiamos todos los registro de la tabla
                    DefaultTableModel md = (DefaultTableModel) tabla.getModel();
                    int CantEliminar = tabla.getRowCount() - 1;
                    for (int i = CantEliminar; i >= 0; i--) {
                        md.removeRow(i);
                    }
                    DefaultTableModel modeloT = new DefaultTableModel(null, new String[]{"CEDULA", "NOMBRE", "CUADRILLA", "FECHA", "CANTIDAD_VEHÍCULOS_DESCARGADOS","VEHÍCULOS_DESCARGADOS", "TOTAL DESCARGADO EN KG", "TOTAL ASIGNADO EN KG"});

                    //Cargamos la nueva información
                    for(int a=0; a < listadoPreliquidacion.size(); a++){
                        System.out.println("validar=========================>"+listadoPreliquidacion.get(a)); 
                   }
                    for(String data : listadoPreliquidacion){
                        DecimalFormat formato2 = new DecimalFormat("0.00");
                        String[] informacion = data.split("@@");
                        String[] registro = new String[8];
                        registro[0] = "" + informacion[0];
                        registro[1] = "" + informacion[1];
                        registro[2] = "" + informacion[2];
                        registro[3] = "" + informacion[3];
                        registro[4] = "" + informacion[7];
                        registro[5] = "" + informacion[4];
                        registro[6] = "" + formato2.format(Double.parseDouble(informacion[5]));
                        registro[7] = "" + formato2.format(Double.parseDouble(informacion[6]));
                        modeloT.addRow(registro);
                    }
                    tabla.setModel(modeloT);
                    //tabla.setDefaultRenderer(Object.class, new Myrender_LiquidacionPalero_Registrar(2));
                    resizeColumnWidth(tabla);
                    //level = 3;
                    //Label_level.setText("NIVEL " + level); 
                    boton.setText("GENERAR LIQUIDACIÓN");
                    //senMailLabel.show(false);
                    //sendMailIcon.show(false);
                    Label_level.setText("NIVEL " + 3);
                }
            }else{
                if(result==0){
                    JOptionPane.showMessageDialog(null, "No se pudo registrar la preliquidacion en el sistema","Error!!.", JOptionPane.ERROR_MESSAGE);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        boton.setEnabled(true);
        progreso.setBackground(Color.GREEN);
        progreso.show(false);
    }
    @Override
    protected void process(List chunks) {
        progreso.setValue((int) chunks.get(0));
    } 
    public ArrayList<String> getListadoPreliquidacion() {
        return listadoPreliquidacion;
    }

    public void setListadoPreliquidacion(ArrayList<String> listadoPreliquidacion) {
        this.listadoPreliquidacion = listadoPreliquidacion;
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
