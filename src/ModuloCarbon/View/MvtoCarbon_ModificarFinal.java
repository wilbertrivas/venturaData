package ModuloCarbon.View;
  
import Catalogo.Controller.ControlDB_CentroCostoSubCentro;
import Catalogo.Controller.ControlDB_CentroOperacion;
import Catalogo.Controller.ControlDB_Cliente;
import Catalogo.Controller.ControlDB_Equipo;
import Catalogo.Controller.ControlDB_LaborRealizada;
import Catalogo.Controller.ControlDB_MotivoNoLavado;
import Catalogo.Controller.ControlDB_MotivoParada;
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import Catalogo.Model.Cliente;
import Catalogo.Model.LaborRealizada;
import Catalogo.Model.MotivoNoLavado;
import Catalogo.Model.MotivoParada;
import Catalogo.Model.TipoEquipo;
import ModuloCarbon.Controller2.ControlDB_MvtoCarbon;
import ModuloCarbon.Model.MvtoCarbon;
import ModuloCarbon.Model.MvtoCarbon_ListadoEquipos;
import ModuloEquipo.Controller.ControlDB_MvtoEquipo;
import ModuloEquipo.Model.AsignacionEquipo;
import ModuloEquipo.Model.CausaInactividad;
import ModuloEquipo.Model.MvtoEquipo;
import ModuloEquipo.Model.Recobro;
import ModuloEquipo.View.MvtoEquipo_ModificarFinal;
import ModuloEquipo.View.Solicitud_Equipos_Registrar;
import Sistema.Controller.ControlDB_Usuario;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utilities.ModeloDeTabla;
import utilities.PaginadorDeTabla;
import utilities.ProveedorDeDatosDePaginacion;
 
public final class MvtoCarbon_ModificarFinal extends javax.swing.JPanel implements ActionListener, TableModelListener{
    
    ArrayList<MvtoCarbon_ListadoEquipos> listado=null;
    private Usuario user;
    private String tipoConexion;
    private PaginadorDeTabla<MvtoCarbon_ListadoEquipos> paginadorDeTabla;  
    ProveedorDeDatosDePaginacion<MvtoCarbon_ListadoEquipos> proveedorDeDatos;
    ArrayList<String> encabezadoTabla=null;
    MvtoCarbon mvtoCarbon;
    MvtoCarbon mvtoCarbon_Actualizar;
    MvtoEquipo mvtoEquipo=null;
    ArrayList<MvtoCarbon_ListadoEquipos> listadoMvtoCarbon_ListadoEquipos=null;
    MvtoCarbon_ListadoEquipos mvtoCarbon_ListadoEquipos= null;
    ArrayList<CausaInactividad> listadoCausaInactividad = new ArrayList();
    ArrayList<MotivoNoLavado> listadoMotivoNolavadoVehiculo = new ArrayList();
    ArrayList<Usuario> listadoUsuarioInicioRegistro_mvtoCarbon= new ArrayList();
    ArrayList<Usuario> listadoUsuarioCierraRegistro_mvtoCarbon= new ArrayList();        
    ArrayList<Usuario> listadoUsuarioInicioRegistro_mvtoEquipo= new ArrayList();
    ArrayList<Usuario> listadoUsuarioCierraRegistro_mvtoEquipo= new ArrayList();        
     //Variables interfaces Modificar_MvtoEquipo
    private ArrayList<CentroOperacion> listadoCentroOperacion_mvtoCarbon = null;
    private ArrayList<CentroCostoSubCentro> listadoCentroCostoSubCentro_mvtoCarbon = null;
    private ArrayList<LaborRealizada> listadoLaborRealizada_mvtoCarbon = null;
    private ArrayList<CentroCostoAuxiliar> listadoCentroCostoAuxiliar_mvtoCarbon =null;
    private ArrayList<CentroCostoAuxiliar> listadoCentroCostoAuxiliarDestino_mvtoCarbon = null;
    private ArrayList<Recobro> listadoRecobros_mvtoEquipo= null;
    private ArrayList<TipoEquipo> listadoTipoEquipos_mvtoEquipo = null;
    private ArrayList<String > listadoMarcaEquipos_mvtoEquipo = null;
    private ArrayList<AsignacionEquipo> listadoEquipos_mvtoEquipo = null;
    private ArrayList<MotivoParada> listadoMotivoParada_mvtoEquipo = null;
     
    private Cliente cliente = null;
    private Usuario usuario = null;
    private ArrayList<Cliente> listadoCliente=null;
    private ArrayList<Usuario> listadoUsuario=null;
    
    private ArrayList<MvtoCarbon> listadoMvtoCarbon = null;
     
    public MvtoCarbon_ModificarFinal(Usuario user, String tipoConexion) {
        initComponents();
        //Ocultamos opci??n de exportar
        //icon_exportar.show(false);
        //label_exportar.show(false); 
        if(select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()==0){
            label_QuienRealizaLavadoVeh??culo.show(false);
            select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.show(false);

            label_motivoNoLavado.show(true);
            select_MvtoCarbon_motivoNoLavadoVehiculo.show(true);
        }else{
            if(select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()==1){
                label_QuienRealizaLavadoVeh??culo.show(true);
                select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.show(true);

                label_motivoNoLavado.show(false);
                select_MvtoCarbon_motivoNoLavadoVehiculo.show(false);
            }
        }
        
        this.user= user;
        this.tipoConexion= tipoConexion;
        InternaFrame_VisualizarMvtoCarbon.getContentPane().setBackground(Color.WHITE);
        InternaFrame_VisualizarMvtoCarbon.show(false);
        InternalFrameSelectorCampos.getContentPane().setBackground(Color.WHITE);
        InternalFrameSelectorCampos.show(false);
        
        
        InternaFrame_buscarCliente.getContentPane().setBackground(Color.WHITE);
        InternaFrame_buscarCliente.show(false);
        
        
        InternaFrame_buscarUsuario.getContentPane().setBackground(Color.WHITE);
        InternaFrame_buscarUsuario.show(false);
        
        
        VentanaInterna_PesoCero.getContentPane().setBackground(Color.WHITE);
        VentanaInterna_PesoCero.show(false);
        
        
        //Ocultamos cierto componentes de  la interfaz
        label_placa.show(false);
        icon_buscarCliente.show(false);
        label_cliente.show(false);
        icon_buscarUsuarioQuienRegistra.show(false);
        label_usuarioQuienRegistra.show(false);    
        
        limpiar_placa.show(false);
        limpiar_cliente.show(false);
        limpiar_usuario.show(false);
        
        for(int i = 0; i<= 23; i++){
            if(i<=9){
                horaInicio.addItem("0"+i);
                horaFin.addItem("0"+i);
               
                select_MvtoCarbon_HoraInicial.addItem("0"+i);
                select_MvtoCarbon_HoraFinal.addItem("0"+i);
                
                select_MvtoEquipo_HoraInicial.addItem("0"+i);
                select_MvtoEquipo_HoraFinal.addItem("0"+i);
                
                horaInicio_destareVehiculo.addItem("0"+i);
                horaFin_destareVehiculo.addItem("0"+i);
            }else{
                horaInicio.addItem(""+i);
                horaFin.addItem(""+i);
                
                select_MvtoCarbon_HoraInicial.addItem(""+i);
                select_MvtoCarbon_HoraFinal.addItem(""+i);       
                select_MvtoEquipo_HoraInicial.addItem(""+i);
                select_MvtoEquipo_HoraFinal.addItem(""+i);
                
                horaInicio_destareVehiculo.addItem(""+i);  
                horaFin_destareVehiculo.addItem(""+i);  
            }
        }
        for(int i = 0; i<= 59; i++){
            if(i<=9){
                minutoInicio.addItem("0"+i);
                minutoFin.addItem("0"+i);
                select_MvtoCarbon_MinutosInicial.addItem("0"+i);
                select_MvtoCarbon_MinutosFinal.addItem("0"+i);
                select_MvtoEquipo_MinutosInicial.addItem("0"+i);
                select_MvtoEquipo_MinutosFinal.addItem("0"+i);
                
                minutoInicio_destareVehiculo.addItem("0"+i);
                minutoFin_destareVehiculo.addItem("0"+i);
            }else{
                minutoInicio.addItem(""+i);
                minutoFin.addItem(""+i);
                select_MvtoCarbon_MinutosInicial.addItem(""+i);
                select_MvtoCarbon_MinutosFinal.addItem(""+i);
                select_MvtoEquipo_MinutosInicial.addItem(""+i); 
                select_MvtoEquipo_MinutosFinal.addItem(""+i);
                
                minutoInicio_destareVehiculo.addItem(""+i);
                minutoFin_destareVehiculo.addItem(""+i);
            }
        }
         
        selectorCampoPorDefecto();
        Dimension dim=super.getToolkit().getScreenSize();      
        selectorCampoPorDefecto();
        encabezadoTabla= new ArrayList<String>();
        pageJComboBox.show(false);
        horaInicio.setSelectedIndex(0);
        horaInicio_destareVehiculo.setSelectedIndex(0);
        minutoInicio.setSelectedIndex(0);
        minutoInicio_destareVehiculo.setSelectedIndex(0);
        horaFin.setSelectedIndex(23);
        horaFin_destareVehiculo.setSelectedIndex(23);
        minutoFin.setSelectedIndex(59);
        minutoFin_destareVehiculo.setSelectedIndex(59);
        
        //Cargamos en la interfaz los centros de Operaciones
        try {
            listadoCentroOperacion_mvtoCarbon=new ControlDB_CentroOperacion(tipoConexion).buscarActivos();
            //listadoCentroOperacion_mvtoEquipo=listadoCentroOperacion_mvtoCarbon;
            if(listadoCentroOperacion_mvtoCarbon != null){  
                String datosObjeto[]= new String[listadoCentroOperacion_mvtoCarbon.size()];
                int contador=0;
                for(CentroOperacion listadoCentroOperacion1 : listadoCentroOperacion_mvtoCarbon){ 
                    datosObjeto[contador]=listadoCentroOperacion1.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                select_MvtoCarbon_CO.setModel(model);
                //select_MvtoEquipo_CO.setModel(model);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        //Cargamos en la interfaz los Subcentro de costos seg??n el centro de Operaci??n
        try {
            listadoCentroCostoSubCentro_mvtoCarbon=new ControlDB_CentroCostoSubCentro(tipoConexion).buscarActivos(listadoCentroOperacion_mvtoCarbon.get(select_MvtoCarbon_CO.getSelectedIndex()));
        } catch (SQLException ex) {
            Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
        //listadoCentroCostoSubCentro_mvtoEquipo=listadoCentroCostoSubCentro_mvtoEquipo;
        if(listadoCentroCostoSubCentro_mvtoCarbon != null){
            String datosObjeto[]= new String[listadoCentroCostoSubCentro_mvtoCarbon.size()];
            int contador=0;
            for(CentroCostoSubCentro Objeto : listadoCentroCostoSubCentro_mvtoCarbon){
                datosObjeto[contador]=Objeto.getDescripcion();
                contador++;
            }
            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
            select_MvtoCarbon_SubcentroCosto.setModel(model);
            //select_MvtoEquipo_SubcentroCosto.setModel(model);
        } 
        
        //Cargamos los la interfaz los auxiliares de costos seg??n selecci??n de SubCentro de costos
        try {
            if(listadoCentroCostoSubCentro_mvtoCarbon!= null){
                listadoCentroCostoAuxiliar_mvtoCarbon=new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro_mvtoCarbon.get(select_MvtoCarbon_SubcentroCosto.getSelectedIndex()).getCodigo());
                //listadoCentroCostoAuxiliar_mvtoEquipo=listadoCentroCostoAuxiliar_mvtoCarbon;
                if(listadoCentroCostoAuxiliar_mvtoCarbon != null){
                    String datosObjeto[]= new String[listadoCentroCostoAuxiliar_mvtoCarbon.size()];
                    int contador=0;
                    for(CentroCostoAuxiliar Objeto : listadoCentroCostoAuxiliar_mvtoCarbon){ 
                        datosObjeto[contador]=Objeto.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    //select_MvtoCarbon_CCAuxiliar.setModel(model);
                    select_MvtoCarbon_CCAuxiliar.setModel(model);
                } 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Cargamos en la interfaz las labores realizada
        try {
            listadoLaborRealizada_mvtoCarbon=new ControlDB_LaborRealizada(tipoConexion).buscarPorSubcentroCosto(listadoCentroCostoSubCentro_mvtoCarbon.get(select_MvtoCarbon_SubcentroCosto.getSelectedIndex()));
            //listadoLaborRealizada_mvtoEquipo=listadoLaborRealizada_mvtoEquipo;
            if(listadoLaborRealizada_mvtoCarbon != null){
                String datosObjeto[]= new String[listadoLaborRealizada_mvtoCarbon.size()];
                int contador=0;
                for(LaborRealizada Objeto : listadoLaborRealizada_mvtoCarbon){ 
                    datosObjeto[contador]=Objeto.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                select_MvtoCarbon_laborRealizada.setModel(model);
                //select_MvtoEquipo_laborRealizada.setModel(model);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Cargamos en la interfaz los centro auxiliares Destino seg??n labor realizada
        try{
            if(listadoLaborRealizada_mvtoCarbon != null){
                if(listadoLaborRealizada_mvtoCarbon.get(select_MvtoCarbon_laborRealizada.getSelectedIndex()).getBodegaDestino().equals("1")){//Fue seleccionada una labor Realizada que tiene marcada Bodega destino por tal motivo cargalos las Bodega destino
                   // listadoCentroCostoAuxiliarDestino_mvtoEquipo
                    if(listadoCentroCostoSubCentro_mvtoCarbon != null){
                        try {
                            listadoCentroCostoAuxiliarDestino_mvtoCarbon = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro_mvtoCarbon.get(select_MvtoCarbon_SubcentroCosto.getSelectedIndex()).getCodigo());
                            if(listadoCentroCostoAuxiliarDestino_mvtoCarbon != null){
                                String datosObjeto[] = new String[listadoCentroCostoAuxiliarDestino_mvtoCarbon.size()];
                                int contador = 0;
                                for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliarDestino_mvtoCarbon) {
                                    datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                                    contador++;
                                }
                                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                select_MvtoCarbon_CCAuxiliarDestino.setModel(model);
                            }else{
                                select_MvtoCarbon_CCAuxiliarDestino.removeAllItems();
                            } 
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }            
                }else{
                   select_MvtoCarbon_CCAuxiliarDestino.removeAllItems(); 
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //Cargamos en la interfaz los Recobros
        try {
            listadoRecobros_mvtoEquipo=new ControlDB_MvtoEquipo(tipoConexion).listarRecobroTodos();
            //listadoRecobros_mvtoEquipo=listadoRecobros_mvtoEquipo;
            if(listadoRecobros_mvtoEquipo != null){
                String datosObjeto[]= new String[listadoRecobros_mvtoEquipo.size()];
                int contador=0;
                for(Recobro Objeto : listadoRecobros_mvtoEquipo){ 
                    datosObjeto[contador]=Objeto.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                select_MvtoEquipo_recobro.setModel(model);
                //select_MvtoEquipo_recobro.setModel(model);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }       
        //Cargamos en la interfaz los motivos de parada
        try {
            listadoMotivoParada_mvtoEquipo=new ControlDB_MotivoParada(tipoConexion).buscarActivos();
            //listadoRecobros_mvtoEquipo=listadoRecobros_mvtoEquipo;
            if(listadoMotivoParada_mvtoEquipo != null){
                String datosObjeto[]= new String[listadoMotivoParada_mvtoEquipo.size()];
                int contador=0;
                for(MotivoParada Objeto : listadoMotivoParada_mvtoEquipo){ 
                    datosObjeto[contador]=Objeto.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                mvtoEquipo_selectRazonFinalzaci??n.setModel(model);
                
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
        //Cargamos en la interfaz los motivos de no lavado de veh??culos
        try {
            listadoMotivoNolavadoVehiculo=new ControlDB_MotivoNoLavado(tipoConexion).buscar("");
            //listadoRecobros_mvtoEquipo=listadoRecobros_mvtoEquipo;
            if(listadoMotivoNolavadoVehiculo != null){
                String datosObjeto[]= new String[listadoMotivoNolavadoVehiculo.size()];
                int contador=0;
                for(MotivoNoLavado Objeto : listadoMotivoNolavadoVehiculo){ 
                    datosObjeto[contador]=Objeto.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                select_MvtoCarbon_motivoNoLavadoVehiculo.setModel(model);
                
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        //Cargamos en la interfaz el listado de usuarios
        try {
            listadoUsuarioInicioRegistro_mvtoCarbon=new ControlDB_Usuario(tipoConexion).buscar("");
            listadoUsuarioCierraRegistro_mvtoCarbon=listadoUsuarioInicioRegistro_mvtoCarbon;
            listadoUsuarioInicioRegistro_mvtoEquipo=listadoUsuarioInicioRegistro_mvtoCarbon;
            listadoUsuarioCierraRegistro_mvtoEquipo=listadoUsuarioInicioRegistro_mvtoCarbon;
            
            if(listadoUsuarioInicioRegistro_mvtoCarbon != null){
                String datosObjeto[]= new String[listadoUsuarioInicioRegistro_mvtoCarbon.size()];
                int contador=0;
                for(Usuario Objeto : listadoUsuarioInicioRegistro_mvtoCarbon){ 
                    datosObjeto[contador]=Objeto.getNombres()+ " "+ Objeto.getApellidos();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                select_MvtoCarbon_UsuarioIniciaRegistro.setModel(model);
            }
            
            if(listadoUsuarioCierraRegistro_mvtoCarbon != null){
                String datosObjeto[]= new String[listadoUsuarioCierraRegistro_mvtoCarbon.size()];
                int contador=0;
                for(Usuario Objeto : listadoUsuarioCierraRegistro_mvtoCarbon){ 
                    datosObjeto[contador]=Objeto.getNombres()+ " "+ Objeto.getApellidos();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                select_MvtoCarbon_UsuarioCierraRegistro.setModel(model);
            }
            
            if(listadoUsuarioInicioRegistro_mvtoEquipo != null){
                String datosObjeto[]= new String[listadoUsuarioInicioRegistro_mvtoEquipo.size()];
                int contador=0;
                for(Usuario Objeto : listadoUsuarioInicioRegistro_mvtoEquipo){ 
                    datosObjeto[contador]=Objeto.getNombres()+ " "+ Objeto.getApellidos();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                select_MvtoEquipo_UsuarioIniciaRegistro.setModel(model);
            }
            if(listadoUsuarioCierraRegistro_mvtoEquipo != null){
                String datosObjeto[]= new String[listadoUsuarioCierraRegistro_mvtoEquipo.size()];
                int contador=0;
                for(Usuario Objeto : listadoUsuarioCierraRegistro_mvtoEquipo){ 
                    datosObjeto[contador]=Objeto.getNombres()+ " "+ Objeto.getApellidos();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                select_MvtoEquipo_UsuarioCierraRegistro.setModel(model);
            }    
        } catch (SQLException ex) {
            Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        
         //##################################################
        fechaInicialActividad_MvtoCarbon.show(false);
        label_MvtoCarbon_HoraInicial.show(false);
        select_MvtoCarbon_HoraInicial.show(false);
        label_MvtoCarbon_separadorInicial.show(false);
        select_MvtoCarbon_MinutosInicial.show(false);
        label_MvtoCarbon_ZonaHorariaInicial.show(false);
        
        //##################################################
        fechaFinActividad_MvtoCarbon.show(false);
        label_MvtoCarbon_HoraFinal.show(false);
        select_MvtoCarbon_HoraFinal.show(false);
        label_MvtoCarbon_separadorFinal.show(false);
        select_MvtoCarbon_MinutosFinal.show(false);
        label_MvtoCarbon_ZonaHorariaFinal.show(false); 
        
        
        
         //##################################################
        fechaInicialActividad_MvtoEquipo.show(false);
        label_MvtoEquipo_HoraInicial.show(false);
        select_MvtoEquipo_HoraInicial.show(false);
        label_MvtoEquipo_separadorInicial.show(false);
        select_MvtoEquipo_MinutosInicial.show(false);
        label_MvtoEquipo_ZonaHorariaInicial.show(false);
        
        //##################################################
        fechaFinActividad_MvtoEquipo.show(false);
        label_MvtoEquipo_HoraFinal.show(false);
        select_MvtoEquipo_HoraFinal.show(false);
        label_MvtoEquipo_separadorFinal.show(false);
        select_MvtoEquipo_MinutosFinal.show(false);
        label_MvtoEquipo_ZonaHorariaFinal.show(false);  
        
        //ocultamos los select de usuarios de inicio y de cierre
        select_MvtoCarbon_UsuarioIniciaRegistro.show(false);
        select_MvtoCarbon_UsuarioCierraRegistro.show(false);
        select_MvtoEquipo_UsuarioIniciaRegistro.show(false);
        select_MvtoEquipo_UsuarioCierraRegistro.show(false);
        
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Inactivar = new javax.swing.JPopupMenu();
        Enable = new javax.swing.JMenuItem();
        PesoCero1 = new javax.swing.JMenuItem();
        InternaFrame_VisualizarMvtoCarbon = new javax.swing.JInternalFrame();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        mvtEquipo_estado = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        MvtCarbon_equipoLavadoC??digo = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jSeparator26 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        MvtCarbon_observacion = new javax.swing.JTextArea();
        jLabel77 = new javax.swing.JLabel();
        mvtEquipo_laborRealizada = new javax.swing.JLabel();
        mvtEquipo_FechaInicioActividad = new javax.swing.JLabel();
        mvtEquipo_FechaFinalActividad = new javax.swing.JLabel();
        mvtEquipo_Minutos = new javax.swing.JLabel();
        mvtEquipo_CantidadHoras = new javax.swing.JLabel();
        mvtEquipo_ActividadFinalizada = new javax.swing.JLabel();
        mvtEquipo_MotivoFinalizaci??n = new javax.swing.JLabel();
        MvtCarbon_FechaRegistro = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        MvtCarbon_fechaEntradaVehiculo = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        MvtEquipo_codigo = new javax.swing.JLabel();
        MvtEquipo_tipo = new javax.swing.JLabel();
        mvtEquipo_Equipo_producto = new javax.swing.JLabel();
        mvtEquipo_Equipo_marca = new javax.swing.JLabel();
        mvtEquipo_Equipo_modelo = new javax.swing.JLabel();
        mvtEquipo_Equipo_serial = new javax.swing.JLabel();
        mvtEquipo_Equipo_descripci??n = new javax.swing.JLabel();
        mvtEquipo_Equipo_proveedor = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        mvtEquipo_Equipo_pertenencia = new javax.swing.JLabel();
        MvtCarbon_conexionCcarga = new javax.swing.JLabel();
        MvtCarbon_fechaFinalDescargue = new javax.swing.JLabel();
        MvtCarbon_fechaInicioDescargue = new javax.swing.JLabel();
        MvtCarbon_fechaSalidaVehiculo = new javax.swing.JLabel();
        MvtCarbon_placa = new javax.swing.JLabel();
        MvtCarbon_pesoNeto = new javax.swing.JLabel();
        MvtCarbon_pesoLleno = new javax.swing.JLabel();
        MvtCarbon_pesoVacio = new javax.swing.JLabel();
        MvtCarbon_consecutivo = new javax.swing.JLabel();
        MvtCarbon_deposito = new javax.swing.JLabel();
        MvtCarbon_transportadora = new javax.swing.JLabel();
        MvtCarbon_cliente = new javax.swing.JLabel();
        MvtCarbon_articulo = new javax.swing.JLabel();
        MvtCarbon_auxiliarCentroCostoOrigen = new javax.swing.JLabel();
        MvtCarbon_subCentroOperacion = new javax.swing.JLabel();
        MvtCarbon_centroOperacion = new javax.swing.JLabel();
        MvtCarbon_codigo = new javax.swing.JLabel();
        listadoEquiposDescargue = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        check_MvtoCarbon_InicioActividad = new javax.swing.JRadioButton();
        check_MvtoCarbon_FinalizacionActividad = new javax.swing.JRadioButton();
        fechaInicialActividad_MvtoCarbon = new com.toedter.calendar.JDateChooser();
        label_MvtoCarbon_HoraInicial = new javax.swing.JLabel();
        select_MvtoCarbon_HoraInicial = new javax.swing.JComboBox<>();
        label_MvtoCarbon_separadorInicial = new javax.swing.JLabel();
        select_MvtoCarbon_MinutosInicial = new javax.swing.JComboBox<>();
        label_MvtoCarbon_ZonaHorariaInicial = new javax.swing.JLabel();
        fechaFinActividad_MvtoCarbon = new com.toedter.calendar.JDateChooser();
        label_MvtoCarbon_HoraFinal = new javax.swing.JLabel();
        select_MvtoCarbon_HoraFinal = new javax.swing.JComboBox<>();
        label_MvtoCarbon_separadorFinal = new javax.swing.JLabel();
        select_MvtoCarbon_MinutosFinal = new javax.swing.JComboBox<>();
        label_MvtoCarbon_ZonaHorariaFinal = new javax.swing.JLabel();
        titulo29 = new javax.swing.JLabel();
        select_MvtoCarbon_CO = new javax.swing.JComboBox<>();
        titulo25 = new javax.swing.JLabel();
        select_MvtoCarbon_SubcentroCosto = new javax.swing.JComboBox<>();
        titulo45 = new javax.swing.JLabel();
        select_MvtoCarbon_CCAuxiliar = new javax.swing.JComboBox<>();
        titulo46 = new javax.swing.JLabel();
        select_MvtoCarbon_laborRealizada = new javax.swing.JComboBox<>();
        titulo49 = new javax.swing.JLabel();
        select_MvtoCarbon_CCAuxiliarDestino = new javax.swing.JComboBox<>();
        titulo47 = new javax.swing.JLabel();
        select_MvtoEquipo_recobro = new javax.swing.JComboBox<>();
        listadoEquiposDescargue1 = new javax.swing.JComboBox<>();
        MvtCarbon_laborRealizada = new javax.swing.JLabel();
        select_MvtoCarbon_lavadoVeh??culo = new javax.swing.JComboBox<>();
        jLabel60 = new javax.swing.JLabel();
        check_MvtoCarbon_UsuarioCierraActividad = new javax.swing.JRadioButton();
        check_MvtoCarbon_UsuarioIniciaActividad = new javax.swing.JRadioButton();
        check_MvtoEquipo_UsuarioIniciaActividad = new javax.swing.JRadioButton();
        check_MvtoEquipo_UsuarioCierraActividad = new javax.swing.JRadioButton();
        jLabel18 = new javax.swing.JLabel();
        titulo48 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        MvtCarbon_auxiliarCentroCostoDestino = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        MvtCarbon_lavadoVeh??culo = new javax.swing.JLabel();
        check_MvtoEquipo_InicioActividad = new javax.swing.JRadioButton();
        check_MvtoEquipo_FinalizacionActividad = new javax.swing.JRadioButton();
        fechaFinActividad_MvtoEquipo = new com.toedter.calendar.JDateChooser();
        fechaInicialActividad_MvtoEquipo = new com.toedter.calendar.JDateChooser();
        label_MvtoEquipo_HoraInicial = new javax.swing.JLabel();
        label_MvtoEquipo_HoraFinal = new javax.swing.JLabel();
        select_MvtoEquipo_HoraInicial = new javax.swing.JComboBox<>();
        select_MvtoEquipo_HoraFinal = new javax.swing.JComboBox<>();
        label_MvtoEquipo_separadorInicial = new javax.swing.JLabel();
        select_MvtoCarbon_UsuarioIniciaRegistro = new javax.swing.JComboBox<>();
        label_MvtoEquipo_separadorFinal = new javax.swing.JLabel();
        select_MvtoEquipo_MinutosFinal = new javax.swing.JComboBox<>();
        select_MvtoEquipo_MinutosInicial = new javax.swing.JComboBox<>();
        label_MvtoEquipo_ZonaHorariaInicial = new javax.swing.JLabel();
        label_QuienRealizaLavadoVeh??culo = new javax.swing.JLabel();
        select_MvtoCarbon_UsuarioCierraRegistro = new javax.swing.JComboBox<>();
        label_MvtoEquipo_ZonaHorariaFinal = new javax.swing.JLabel();
        select_MvtoEquipo_laborRealizada = new javax.swing.JLabel();
        select_MvtoEquipo_tipoEquipo = new javax.swing.JComboBox<>();
        titulo61 = new javax.swing.JLabel();
        select_MvtoEquipo_UsuarioIniciaRegistro = new javax.swing.JComboBox<>();
        select_MvtoEquipo_UsuarioCierraRegistro = new javax.swing.JComboBox<>();
        select_MvtoEquipo_marcaEquipo = new javax.swing.JComboBox<>();
        titulo62 = new javax.swing.JLabel();
        select_MvtoEquipo_Equipo = new javax.swing.JComboBox<>();
        titulo30 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        MvtCarbon_costoLavadoVeh??culo = new javax.swing.JLabel();
        MvtCarbon_observaci??nLavadoVeh??culo = new javax.swing.JLabel();
        MvtCarbon_valorRecaudoEmpresa = new javax.swing.JLabel();
        MvtCarbon_valorRecaudoEquipo = new javax.swing.JLabel();
        MvtCarbon_motivoNoLavadoVehiculo = new javax.swing.JLabel();
        MvtCarbon_usuarioQuienInicia = new javax.swing.JLabel();
        MvtCarbon_usuarioQuienCierra = new javax.swing.JLabel();
        registrar_MvtoEquipo = new javax.swing.JButton();
        mvtoEquipo_selectRazonFinalzaci??n = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        titulo59 = new javax.swing.JLabel();
        titulo60 = new javax.swing.JLabel();
        titulo64 = new javax.swing.JLabel();
        titulo50 = new javax.swing.JLabel();
        titulo51 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        textArea_MvtoCarbon_razonModificacion = new javax.swing.JTextArea();
        titulo52 = new javax.swing.JLabel();
        titulo63 = new javax.swing.JLabel();
        titulo53 = new javax.swing.JLabel();
        MvtCarbon_equipoLavadoDescripci??n = new javax.swing.JLabel();
        registrar_MvtoCarbon = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        textArea_MvtoEquipo_razonModificacion = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();
        select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo = new javax.swing.JComboBox<>();
        label_motivoNoLavado = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        MvtEquipo_usuarioQuienInicia = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        MvtEquipo_usuarioQuienCierra = new javax.swing.JLabel();
        select_MvtoCarbon_motivoNoLavadoVehiculo = new javax.swing.JComboBox<>();
        jLabel69 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        VentanaInterna_PesoCero = new javax.swing.JInternalFrame();
        jLabel19 = new javax.swing.JLabel();
        buscadorPlaca = new javax.swing.JTextField();
        btnConsultar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        PesoCero_placa = new javax.swing.JLabel();
        fechaInicio_destareVehiculo = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        horaInicio_destareVehiculo = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        minutoInicio_destareVehiculo = new javax.swing.JComboBox<>();
        horarioTiempoInicio_destareVehiculo = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        fechaFin_destareVehiculo = new com.toedter.calendar.JDateChooser();
        jLabel29 = new javax.swing.JLabel();
        horaFin_destareVehiculo = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        minutoFin_destareVehiculo = new javax.swing.JComboBox<>();
        horarioTiempoIFinal_destareVehiculo = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        PesoCero_cliente = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        PesoCero_pesoVacio = new javax.swing.JLabel();
        PesoCero_articulo = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        PesoCero_deposito = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        PesoCero_pesoLleno = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        PesoCero_pesoNeto = new javax.swing.JLabel();
        PesoCero_fechaEntrada = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        PesoCero_fechaSalida = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        PesoCero_consecutivo = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        PesoCero_transportadora = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        PesoCero_placa_nuevo = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        PesoCero_cliente_nuevo = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        PesoCero_pesoVacio_nuevo = new javax.swing.JLabel();
        PesoCero_articulo_nuevo = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        PesoCero_deposito_nuevo = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        PesoCero_pesoLleno_nuevo = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        PesoCero_pesoNeto_nuevo = new javax.swing.JLabel();
        PesoCero_fechaEntrada_nuevo = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        PesoCero_fechaSalida_nuevo = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        PesoCero_consecutivo_nuevo = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        PesoCero_transportadora_nuevo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        InternaFrame_buscarUsuario = new javax.swing.JInternalFrame();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaUsuario = new javax.swing.JTable();
        valorBusquedaUsuario = new javax.swing.JTextField();
        btnConsultarUsuariosQuienRegistra = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnCancelarUsuariosQuienRegistra = new javax.swing.JButton();
        btnLimpiarUsuariosQuienRegistra = new javax.swing.JButton();
        InternaFrame_buscarCliente = new javax.swing.JInternalFrame();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaCliente = new javax.swing.JTable();
        valorBusquedaCliente = new javax.swing.JTextField();
        btnConsultarCliente = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnCancelarCliente = new javax.swing.JButton();
        btnLimpiarCliente = new javax.swing.JButton();
        InternalFrameSelectorCampos = new javax.swing.JInternalFrame();
        jButton2 = new javax.swing.JButton();
        selectAll = new javax.swing.JRadioButton();
        selectDescargue_codigo = new javax.swing.JRadioButton();
        selectDescargue_fechaRegistro = new javax.swing.JRadioButton();
        selectDescargue_deposito = new javax.swing.JRadioButton();
        selectDescargue_consecutivo = new javax.swing.JRadioButton();
        selectDescargue_placa = new javax.swing.JRadioButton();
        selectDescargue_pesoVacio = new javax.swing.JRadioButton();
        selectDescargue_pesoLleno = new javax.swing.JRadioButton();
        selectDescargue_pesoNeto = new javax.swing.JRadioButton();
        selectDescargue_numOrden = new javax.swing.JRadioButton();
        selectDescargue_fechaEntradaVehiculo = new javax.swing.JRadioButton();
        selectDescargue_registroManual = new javax.swing.JRadioButton();
        selectDescargue_clienteCodigo = new javax.swing.JRadioButton();
        selectDescargue_transportadoraNit = new javax.swing.JRadioButton();
        selectDescargue_transportadoraCodigo = new javax.swing.JRadioButton();
        selectDescargue_usuarioRegistroManualCodigo = new javax.swing.JRadioButton();
        selectDescargue_usuarioRegistroManualNombre = new javax.swing.JRadioButton();
        selectDescargue_fechaSalidaVehiculo = new javax.swing.JRadioButton();
        selectDescargue_fechaInicioDescargue = new javax.swing.JRadioButton();
        selectDescargue_cantidadMinutosDescargue = new javax.swing.JRadioButton();
        titulo2 = new javax.swing.JLabel();
        titulo13 = new javax.swing.JLabel();
        jSeparator21 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        selectDescargue_mes = new javax.swing.JRadioButton();
        selectDescargue_clienteNombre = new javax.swing.JRadioButton();
        selectDescargue_articuloCodigo = new javax.swing.JRadioButton();
        selectDescargue_centroOperacion = new javax.swing.JRadioButton();
        selectDescargue_centroCostoMayor = new javax.swing.JRadioButton();
        selectDescargue_observaci??n = new javax.swing.JRadioButton();
        selectDescargue_estado = new javax.swing.JRadioButton();
        selectDescargue_conexionPesoCcarga = new javax.swing.JRadioButton();
        selectDescargue_fechaFinDescargue = new javax.swing.JRadioButton();
        selectDescargue_centroCostoAuxiliarOrigen = new javax.swing.JRadioButton();
        selectDescargue_centroCosto = new javax.swing.JRadioButton();
        selectDescargue_articuloUnidadNegocio = new javax.swing.JRadioButton();
        selectDescargue_laborRealizada = new javax.swing.JRadioButton();
        selectDescargue_articuloNombre = new javax.swing.JRadioButton();
        selectDescargue_articuloCodigoERP = new javax.swing.JRadioButton();
        selectDescargue_articuloTipo = new javax.swing.JRadioButton();
        selectDescargue_transportadoraNombre = new javax.swing.JRadioButton();
        selectDescargue_usuarioQuienRegistraVehiculoCodigo = new javax.swing.JRadioButton();
        selectDescargue_usuarioQuienRegistraVehiculoNombre = new javax.swing.JRadioButton();
        selectDescargue_usuarioRegistroManualCorreo = new javax.swing.JRadioButton();
        selectMvtoEquipo_clienteCodigo = new javax.swing.JRadioButton();
        selectAsignacion_equipoCodigo = new javax.swing.JRadioButton();
        selectAsignacion_equipoMarca = new javax.swing.JRadioButton();
        selectAsignacion_equipoModelo = new javax.swing.JRadioButton();
        selectMvtoEquipo_fechaRegistro = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioRegistraMvto_nombre = new javax.swing.JRadioButton();
        selectMvtoEquipo_cantidadMinutosDescargue = new javax.swing.JRadioButton();
        selectMvtoEquipo_numeroOrden = new javax.swing.JRadioButton();
        selectAsignacion_equipoTipo = new javax.swing.JRadioButton();
        selectMvtoEquipo_clienteNombre = new javax.swing.JRadioButton();
        selectMvtoEquipo_motonaveDescripci??n = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroOperacion = new javax.swing.JRadioButton();
        selectMvtoEquipo_subcentroCosto = new javax.swing.JRadioButton();
        selectAsignacion_fechaInicio = new javax.swing.JRadioButton();
        selectAsignacion_fechaFinalizacion = new javax.swing.JRadioButton();
        selectAsignacion_cantidadMinutos_Programados = new javax.swing.JRadioButton();
        selectAsignacion_equipoPertenencia = new javax.swing.JRadioButton();
        selectMvtoEquipo_proveedorEquipoNit = new javax.swing.JRadioButton();
        selectMvtoEquipo_proveedorEquipoNombre = new javax.swing.JRadioButton();
        selectMvtoEquipo_laborRealizada = new javax.swing.JRadioButton();
        selectMvtoEquipo_fechaInicioDescargue = new javax.swing.JRadioButton();
        selectMvtoEquipo_fechaFinDescargue = new javax.swing.JRadioButton();
        selectMvtoEquipo_ValorHoraEquipo = new javax.swing.JRadioButton();
        selectMvtoEquipo_costoTotal = new javax.swing.JRadioButton();
        selectMvtoEquipo_Recobro = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioRegistraMvto_codigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_CausalInactividad = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre = new javax.swing.JRadioButton();
        selectMvtoEquipo_autorizacionRecobro = new javax.swing.JRadioButton();
        selectMvtoEquipo_MotivoParada = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion = new javax.swing.JRadioButton();
        selectMvtoEquipo_Inactividad = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioInactividad_codigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_DesdeCarbon = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioInactividad_nombre = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroCostoMayor = new javax.swing.JRadioButton();
        selectMvtoEquipo_observacion = new javax.swing.JRadioButton();
        selectMvtoEquipo_estado = new javax.swing.JRadioButton();
        selectAsignacion_equipoDescripcion = new javax.swing.JRadioButton();
        selectAsignacion_codigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroCosto = new javax.swing.JRadioButton();
        selectAsignacion_fechaRegistro = new javax.swing.JRadioButton();
        selectMvtoEquipo_articuloCodigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_articuloDescripcion = new javax.swing.JRadioButton();
        selectMvtoEquipo_motonaveCodigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroCostoAxiliarOrigen = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroCostoAuxiliarDestino = new javax.swing.JRadioButton();
        selectMvtoEquipo_mes = new javax.swing.JRadioButton();
        selectMvtoEquipo_codigo = new javax.swing.JRadioButton();
        selectDescargue_usuarioQuienRegistraVehiculoCorreo = new javax.swing.JRadioButton();
        selectDescargue_centroCostoAuxiliarDestino = new javax.swing.JRadioButton();
        selectDescargue_subcentroCosto = new javax.swing.JRadioButton();
        selectDescargue_lavadoVehiculoObservacion = new javax.swing.JRadioButton();
        selectDescargue_lavadoVehiculo = new javax.swing.JRadioButton();
        Motivo_No_LavadoVeh??culo = new javax.swing.JRadioButton();
        Valor_Recaudo_Equipo_LavadoVeh??culos = new javax.swing.JRadioButton();
        Valor_Recaudo_Empresa_LavadoVeh??culos = new javax.swing.JRadioButton();
        Equipo_Quien_Realiza_LavadoVeh??culo_C??digo = new javax.swing.JRadioButton();
        Costo_Lavado_Veh??culo = new javax.swing.JRadioButton();
        Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo = new javax.swing.JRadioButton();
        Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n = new javax.swing.JRadioButton();
        selectDescargue_dia = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioCierraMvto_nombre = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioCierraMvto_codigo = new javax.swing.JRadioButton();
        selectDescargue_usuarioQuienCierraVehiculoNombre = new javax.swing.JRadioButton();
        selectDescargue_usuarioQuienCierraVehiculoCodigo = new javax.swing.JRadioButton();
        titulo12 = new javax.swing.JLabel();
        titulo21 = new javax.swing.JLabel();
        titulo17 = new javax.swing.JLabel();
        titulo20 = new javax.swing.JLabel();
        titulo23 = new javax.swing.JLabel();
        titulo19 = new javax.swing.JLabel();
        titulo24 = new javax.swing.JLabel();
        titulo26 = new javax.swing.JLabel();
        titulo27 = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        consultar = new javax.swing.JButton();
        fechaInicio = new com.toedter.calendar.JDateChooser();
        minutoInicio = new javax.swing.JComboBox<>();
        horaInicio = new javax.swing.JComboBox<>();
        limpiar_usuario = new javax.swing.JLabel();
        limpiar_placa = new javax.swing.JLabel();
        limpiar_cliente = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        fechaFin = new com.toedter.calendar.JDateChooser();
        minutoFin = new javax.swing.JComboBox<>();
        horaFin = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        alerta_fechaFinal = new javax.swing.JLabel();
        alerta_fechaInicio = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        icon_exportar = new javax.swing.JLabel();
        label_exportar = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        horarioTiempoIFinalMvtoCarbon_Procesar = new javax.swing.JLabel();
        horarioTiempoInicioMvtoCarbon_Procesar = new javax.swing.JLabel();
        paginationPanel = new javax.swing.JPanel();
        pageJComboBox = new javax.swing.JComboBox<>();
        checkPesoCero = new javax.swing.JRadioButton();
        checkUsuarioQuienRegistra = new javax.swing.JRadioButton();
        icon_buscarUsuarioQuienRegistra = new javax.swing.JLabel();
        label_usuarioQuienRegistra = new javax.swing.JLabel();
        checkPlaca = new javax.swing.JRadioButton();
        icon_buscarCliente = new javax.swing.JLabel();
        label_cliente = new javax.swing.JLabel();
        checkCliente = new javax.swing.JRadioButton();
        label_placa = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();

        Enable.setText("Seleccionar");
        Enable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnableActionPerformed(evt);
            }
        });
        Inactivar.add(Enable);

        PesoCero1.setText("Peso Cero");
        PesoCero1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PesoCero1ActionPerformed(evt);
            }
        });
        Inactivar.add(PesoCero1);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InternaFrame_VisualizarMvtoCarbon.setBackground(new java.awt.Color(255, 255, 255));
        InternaFrame_VisualizarMvtoCarbon.setClosable(true);
        InternaFrame_VisualizarMvtoCarbon.setVisible(false);
        InternaFrame_VisualizarMvtoCarbon.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(0, 51, 51));
        jLabel55.setText("Centro Operaci??n:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 200, 150, 20));

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(0, 51, 51));
        jLabel56.setText("SubCentro Costo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 200, 130, 20));

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 51, 51));
        jLabel57.setText("Fecha Salida Veh??culo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 140, 160, 20));

        mvtEquipo_estado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mvtEquipo_estado.setForeground(new java.awt.Color(51, 51, 51));
        mvtEquipo_estado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 300, 190, 20));

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 51, 51));
        jLabel61.setText("Movito Finalizaci??n:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 970, 150, 20));

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(0, 51, 51));
        jLabel63.setText("Descripci??n:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 910, 130, -1));

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 51, 51));
        jLabel65.setText("Fecha Inicio:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 930, 130, -1));

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 51, 51));
        jLabel66.setText("Fecha Finalizaci??n:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 950, 130, -1));

        MvtCarbon_equipoLavadoC??digo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_equipoLavadoC??digo.setForeground(new java.awt.Color(0, 51, 51));
        MvtCarbon_equipoLavadoC??digo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_equipoLavadoC??digo, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 400, 100, 20));

        jLabel72.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(0, 51, 51));
        jLabel72.setText("Cantidad Horas:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 930, 150, 20));

        jLabel73.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(0, 51, 51));
        jLabel73.setText("Conectado Ccarga:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 300, 140, 20));

        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(0, 51, 51));
        jLabel74.setText("Estado:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 300, 100, 20));

        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(0, 51, 51));
        jLabel75.setText("Actividad Finalizada:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 950, 150, 20));

        jLabel76.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(0, 51, 51));
        jLabel76.setText("Pertenencia:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 760, 130, 20));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jSeparator26, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 330, -1, -1));

        MvtCarbon_observacion.setEditable(false);
        MvtCarbon_observacion.setColumns(20);
        MvtCarbon_observacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_observacion.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_observacion.setRows(5);
        jScrollPane3.setViewportView(MvtCarbon_observacion);

        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 500, 780, 80));

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(0, 51, 51));
        jLabel77.setText("C??digo Mvto:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 60, 110, 20));

        mvtEquipo_laborRealizada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mvtEquipo_laborRealizada.setForeground(new java.awt.Color(51, 51, 51));
        mvtEquipo_laborRealizada.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_laborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 910, 600, 20));

        mvtEquipo_FechaInicioActividad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mvtEquipo_FechaInicioActividad.setForeground(new java.awt.Color(51, 51, 51));
        mvtEquipo_FechaInicioActividad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_FechaInicioActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 930, 190, 20));

        mvtEquipo_FechaFinalActividad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mvtEquipo_FechaFinalActividad.setForeground(new java.awt.Color(51, 51, 51));
        mvtEquipo_FechaFinalActividad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_FechaFinalActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 950, 190, 20));

        mvtEquipo_Minutos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mvtEquipo_Minutos.setForeground(new java.awt.Color(51, 51, 51));
        mvtEquipo_Minutos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_Minutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 970, 190, 20));

        mvtEquipo_CantidadHoras.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mvtEquipo_CantidadHoras.setForeground(new java.awt.Color(51, 51, 51));
        mvtEquipo_CantidadHoras.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_CantidadHoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 930, 240, 20));

        mvtEquipo_ActividadFinalizada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mvtEquipo_ActividadFinalizada.setForeground(new java.awt.Color(51, 51, 51));
        mvtEquipo_ActividadFinalizada.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_ActividadFinalizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 950, 240, 20));

        mvtEquipo_MotivoFinalizaci??n.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mvtEquipo_MotivoFinalizaci??n.setForeground(new java.awt.Color(51, 51, 51));
        mvtEquipo_MotivoFinalizaci??n.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_MotivoFinalizaci??n, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 970, 240, 20));

        MvtCarbon_FechaRegistro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_FechaRegistro.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_FechaRegistro.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_FechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 100, 190, 20));

        jLabel80.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(0, 51, 51));
        jLabel80.setText("C??digo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 680, 130, 20));

        MvtCarbon_fechaEntradaVehiculo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_fechaEntradaVehiculo.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_fechaEntradaVehiculo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_fechaEntradaVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 120, 190, 20));

        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(0, 51, 51));
        jLabel81.setText("Tipo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 680, 80, 20));

        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(0, 51, 51));
        jLabel82.setText("Producto:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 700, 130, 20));

        jLabel83.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(0, 51, 51));
        jLabel83.setText("Marca:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 700, 80, 20));

        jLabel84.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(0, 51, 51));
        jLabel84.setText("Modelo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 720, 130, 20));

        jLabel85.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(0, 51, 51));
        jLabel85.setText("Serial:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 720, 80, 20));

        jLabel86.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(0, 51, 51));
        jLabel86.setText("Descripci??n:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 740, 130, 20));

        jLabel87.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(0, 51, 51));
        jLabel87.setText("Proveedor:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 740, 80, 20));

        MvtEquipo_codigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtEquipo_codigo.setForeground(new java.awt.Color(51, 51, 51));
        MvtEquipo_codigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtEquipo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 680, 270, 20));

        MvtEquipo_tipo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtEquipo_tipo.setForeground(new java.awt.Color(51, 51, 51));
        MvtEquipo_tipo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtEquipo_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 680, 270, 20));

        mvtEquipo_Equipo_producto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mvtEquipo_Equipo_producto.setForeground(new java.awt.Color(51, 51, 51));
        mvtEquipo_Equipo_producto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_Equipo_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 700, 270, 20));

        mvtEquipo_Equipo_marca.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mvtEquipo_Equipo_marca.setForeground(new java.awt.Color(51, 51, 51));
        mvtEquipo_Equipo_marca.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_Equipo_marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 700, 270, 20));

        mvtEquipo_Equipo_modelo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mvtEquipo_Equipo_modelo.setForeground(new java.awt.Color(51, 51, 51));
        mvtEquipo_Equipo_modelo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_Equipo_modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 720, 270, 20));

        mvtEquipo_Equipo_serial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mvtEquipo_Equipo_serial.setForeground(new java.awt.Color(51, 51, 51));
        mvtEquipo_Equipo_serial.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_Equipo_serial, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 720, 270, 20));

        mvtEquipo_Equipo_descripci??n.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mvtEquipo_Equipo_descripci??n.setForeground(new java.awt.Color(51, 51, 51));
        mvtEquipo_Equipo_descripci??n.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_Equipo_descripci??n, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 740, 270, 20));

        mvtEquipo_Equipo_proveedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mvtEquipo_Equipo_proveedor.setForeground(new java.awt.Color(51, 51, 51));
        mvtEquipo_Equipo_proveedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_Equipo_proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 740, 270, 20));

        jLabel88.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(0, 51, 51));
        jLabel88.setText("Labor Realizada:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 280, 150, 20));

        jLabel89.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(0, 51, 51));
        jLabel89.setText("Articulo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 220, 90, 20));

        jLabel90.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(0, 51, 51));
        jLabel90.setText("Cliente:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 60, 90, 20));

        jLabel91.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(0, 51, 51));
        jLabel91.setText("Placa:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 80, 150, 20));

        jLabel93.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(0, 51, 51));
        jLabel93.setText("Fecha Inicio Descargue:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 160, 200, 20));

        jLabel95.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(0, 51, 51));
        jLabel95.setText("Fecha Entrada Veh??culo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 120, 170, 20));

        jLabel96.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(0, 51, 51));
        jLabel96.setText("Fecha Fin Descargue:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 180, 160, 20));

        jLabel98.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(0, 51, 51));
        jLabel98.setText("Transportadora:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 80, 130, 20));

        jLabel99.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(0, 51, 51));
        jLabel99.setText("Deposito:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 100, 90, 20));

        jLabel100.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(0, 51, 51));
        jLabel100.setText("Peso Neto:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 180, 90, 20));

        jLabel101.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(0, 51, 51));
        jLabel101.setText("Consecutivo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 120, 90, 20));

        jLabel102.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(0, 51, 51));
        jLabel102.setText("Peso Vacio:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 140, 90, 20));

        jLabel103.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(0, 51, 51));
        jLabel103.setText("Peso Lleno:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 160, 90, 20));

        jLabel104.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(0, 51, 51));
        jLabel104.setText("Observaci??n del registro:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 480, 320, 20));

        jLabel106.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(0, 51, 51));
        jLabel106.setText("Fecha Registro");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 100, 150, 20));

        mvtEquipo_Equipo_pertenencia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mvtEquipo_Equipo_pertenencia.setForeground(new java.awt.Color(51, 51, 51));
        mvtEquipo_Equipo_pertenencia.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_Equipo_pertenencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 760, 270, 20));

        MvtCarbon_conexionCcarga.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_conexionCcarga.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_conexionCcarga.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_conexionCcarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 300, 220, 20));

        MvtCarbon_fechaFinalDescargue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_fechaFinalDescargue.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_fechaFinalDescargue.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_fechaFinalDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 180, 190, 20));

        MvtCarbon_fechaInicioDescargue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_fechaInicioDescargue.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_fechaInicioDescargue.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_fechaInicioDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 160, 190, 20));

        MvtCarbon_fechaSalidaVehiculo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_fechaSalidaVehiculo.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_fechaSalidaVehiculo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_fechaSalidaVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 140, 190, 20));

        MvtCarbon_placa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_placa.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_placa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_placa, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 80, 190, 20));

        MvtCarbon_pesoNeto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_pesoNeto.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_pesoNeto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_pesoNeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 180, 220, 20));

        MvtCarbon_pesoLleno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_pesoLleno.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_pesoLleno.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_pesoLleno, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 160, 220, 20));

        MvtCarbon_pesoVacio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_pesoVacio.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_pesoVacio.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_pesoVacio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 140, 220, 20));

        MvtCarbon_consecutivo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_consecutivo.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_consecutivo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_consecutivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 120, 220, 20));

        MvtCarbon_deposito.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_deposito.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_deposito.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_deposito, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 100, 220, 20));

        MvtCarbon_transportadora.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_transportadora.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_transportadora.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_transportadora, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 80, 290, 20));

        MvtCarbon_cliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_cliente.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_cliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 60, 290, 20));

        MvtCarbon_articulo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_articulo.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_articulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_articulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 220, 280, 20));

        MvtCarbon_auxiliarCentroCostoOrigen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_auxiliarCentroCostoOrigen.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_auxiliarCentroCostoOrigen.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_auxiliarCentroCostoOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 220, 190, 20));

        MvtCarbon_subCentroOperacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_subCentroOperacion.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_subCentroOperacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_subCentroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 200, 280, 20));

        MvtCarbon_centroOperacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_centroOperacion.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_centroOperacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 200, 190, 20));

        MvtCarbon_codigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_codigo.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_codigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 60, 190, 20));

        listadoEquiposDescargue.setToolTipText("");
        listadoEquiposDescargue.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                listadoEquiposDescargueItemStateChanged(evt);
            }
        });
        listadoEquiposDescargue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listadoEquiposDescargueActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(listadoEquiposDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 620, 610, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("INFORMACI??N DE VEH??CULO ");
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 30, 800, 20));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("LISTADO DE EQUIPOS QUE PARTICIPARON EN LA OPERACI??N");
        jLabel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, 800, 20));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("INFORMACI??N DE LA ACTIVIDAD REALIZADA");
        jLabel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 890, 800, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("INFORMACI??N DEL USUARIO QUIEN INICI?? Y CERR?? LA ACTIVIDAD");
        jLabel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 790, 800, 20));

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 153, 153));
        jLabel58.setText("DATOS DEL REGISTRO A MODIFICAR EN MODULO CARB??N");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, -1, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("INFORMACI??N DE EQUIPO QUE DESCARG??");
        jLabel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 660, 800, -1));

        check_MvtoCarbon_InicioActividad.setBackground(new java.awt.Color(255, 255, 255));
        check_MvtoCarbon_InicioActividad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_MvtoCarbon_InicioActividad.setForeground(new java.awt.Color(51, 51, 51));
        check_MvtoCarbon_InicioActividad.setText("FECHA DE INICIO DESCARGUE:");
        check_MvtoCarbon_InicioActividad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                check_MvtoCarbon_InicioActividadItemStateChanged(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(check_MvtoCarbon_InicioActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 270, 30));

        check_MvtoCarbon_FinalizacionActividad.setBackground(new java.awt.Color(255, 255, 255));
        check_MvtoCarbon_FinalizacionActividad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_MvtoCarbon_FinalizacionActividad.setForeground(new java.awt.Color(51, 51, 51));
        check_MvtoCarbon_FinalizacionActividad.setText("FECHA DE FINALIZACI??N DESCARGUE:");
        check_MvtoCarbon_FinalizacionActividad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                check_MvtoCarbon_FinalizacionActividadItemStateChanged(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(check_MvtoCarbon_FinalizacionActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 30));

        fechaInicialActividad_MvtoCarbon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicialActividad_MvtoCarbonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicialActividad_MvtoCarbonMouseEntered(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(fechaInicialActividad_MvtoCarbon, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 170, 30));

        label_MvtoCarbon_HoraInicial.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_MvtoCarbon_HoraInicial.setForeground(new java.awt.Color(51, 51, 51));
        label_MvtoCarbon_HoraInicial.setText("Hora");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(label_MvtoCarbon_HoraInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 40, 30));

        select_MvtoCarbon_HoraInicial.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoCarbon_HoraInicialItemStateChanged(evt);
            }
        });
        select_MvtoCarbon_HoraInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoCarbon_HoraInicialActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoCarbon_HoraInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 50, 30));

        label_MvtoCarbon_separadorInicial.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        label_MvtoCarbon_separadorInicial.setText(":");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(label_MvtoCarbon_separadorInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, -1, 30));

        select_MvtoCarbon_MinutosInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoCarbon_MinutosInicialActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoCarbon_MinutosInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 50, 30));

        label_MvtoCarbon_ZonaHorariaInicial.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        label_MvtoCarbon_ZonaHorariaInicial.setForeground(new java.awt.Color(102, 102, 102));
        label_MvtoCarbon_ZonaHorariaInicial.setText("AM");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(label_MvtoCarbon_ZonaHorariaInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, 50, 30));

        fechaFinActividad_MvtoCarbon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFinActividad_MvtoCarbonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFinActividad_MvtoCarbonMouseEntered(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(fechaFinActividad_MvtoCarbon, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 170, 30));

        label_MvtoCarbon_HoraFinal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_MvtoCarbon_HoraFinal.setForeground(new java.awt.Color(51, 51, 51));
        label_MvtoCarbon_HoraFinal.setText("Hora");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(label_MvtoCarbon_HoraFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 40, 30));

        select_MvtoCarbon_HoraFinal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoCarbon_HoraFinalItemStateChanged(evt);
            }
        });
        select_MvtoCarbon_HoraFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoCarbon_HoraFinalActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoCarbon_HoraFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, 50, 30));

        label_MvtoCarbon_separadorFinal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        label_MvtoCarbon_separadorFinal.setText(":");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(label_MvtoCarbon_separadorFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, -1, 30));

        select_MvtoCarbon_MinutosFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoCarbon_MinutosFinalActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoCarbon_MinutosFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 50, 30));

        label_MvtoCarbon_ZonaHorariaFinal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        label_MvtoCarbon_ZonaHorariaFinal.setForeground(new java.awt.Color(102, 102, 102));
        label_MvtoCarbon_ZonaHorariaFinal.setText("AM");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(label_MvtoCarbon_ZonaHorariaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, 50, 30));

        titulo29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo29.setForeground(new java.awt.Color(51, 51, 51));
        titulo29.setText("CENTRO OPERACI??N:");
        titulo29.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(titulo29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 170, 30));

        select_MvtoCarbon_CO.setToolTipText("");
        select_MvtoCarbon_CO.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoCarbon_COItemStateChanged(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoCarbon_CO, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 210, 30));

        titulo25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo25.setForeground(new java.awt.Color(51, 51, 51));
        titulo25.setText("SUBCENTRO COSTO:");
        titulo25.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(titulo25, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, 130, 30));

        select_MvtoCarbon_SubcentroCosto.setToolTipText("");
        select_MvtoCarbon_SubcentroCosto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoCarbon_SubcentroCostoItemStateChanged(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoCarbon_SubcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 140, 240, 30));

        titulo45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo45.setForeground(new java.awt.Color(51, 51, 51));
        titulo45.setText("C.C. AUXILIAR ORIGEN:");
        titulo45.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(titulo45, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 150, 30));

        select_MvtoCarbon_CCAuxiliar.setToolTipText("");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoCarbon_CCAuxiliar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 210, 30));

        titulo46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo46.setForeground(new java.awt.Color(51, 51, 51));
        titulo46.setText("LABOR A REALIZAR:");
        titulo46.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(titulo46, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 170, 30));

        select_MvtoCarbon_laborRealizada.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoCarbon_laborRealizadaItemStateChanged(evt);
            }
        });
        select_MvtoCarbon_laborRealizada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoCarbon_laborRealizadaActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoCarbon_laborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 210, 30));

        titulo49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo49.setForeground(new java.awt.Color(51, 51, 51));
        titulo49.setText("C.C. AUXILIAR DESTINO:");
        titulo49.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(titulo49, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 160, 30));

        select_MvtoCarbon_CCAuxiliarDestino.setToolTipText("");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoCarbon_CCAuxiliarDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 180, 240, 30));

        titulo47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo47.setForeground(new java.awt.Color(51, 51, 51));
        titulo47.setText("LAVADO DE VEH??CULO:");
        titulo47.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(titulo47, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 160, 30));

        select_MvtoEquipo_recobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_recobroActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoEquipo_recobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 900, 510, 30));

        listadoEquiposDescargue1.setToolTipText("");
        listadoEquiposDescargue1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                listadoEquiposDescargue1ItemStateChanged(evt);
            }
        });
        listadoEquiposDescargue1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listadoEquiposDescargue1ActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(listadoEquiposDescargue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 600, 550, 30));

        MvtCarbon_laborRealizada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_laborRealizada.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_laborRealizada.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_laborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 280, 550, 20));

        select_MvtoCarbon_lavadoVeh??culo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "SI" }));
        select_MvtoCarbon_lavadoVeh??culo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoCarbon_lavadoVeh??culoItemStateChanged(evt);
            }
        });
        select_MvtoCarbon_lavadoVeh??culo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoCarbon_lavadoVeh??culoActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoCarbon_lavadoVeh??culo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 60, 30));

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel60.setText("CAMPOS MODIFICABLES DE LA OPERACI??N VEH??CULO");
        jLabel60.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 800, 20));

        check_MvtoCarbon_UsuarioCierraActividad.setBackground(new java.awt.Color(255, 255, 255));
        check_MvtoCarbon_UsuarioCierraActividad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_MvtoCarbon_UsuarioCierraActividad.setForeground(new java.awt.Color(51, 51, 51));
        check_MvtoCarbon_UsuarioCierraActividad.setText("USUARIO QUIEN CIERRA ACTIVIDAD:");
        check_MvtoCarbon_UsuarioCierraActividad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                check_MvtoCarbon_UsuarioCierraActividadItemStateChanged(evt);
            }
        });
        check_MvtoCarbon_UsuarioCierraActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_MvtoCarbon_UsuarioCierraActividadActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(check_MvtoCarbon_UsuarioCierraActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 250, 30));

        check_MvtoCarbon_UsuarioIniciaActividad.setBackground(new java.awt.Color(255, 255, 255));
        check_MvtoCarbon_UsuarioIniciaActividad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_MvtoCarbon_UsuarioIniciaActividad.setForeground(new java.awt.Color(51, 51, 51));
        check_MvtoCarbon_UsuarioIniciaActividad.setText("USUARIO QUIEN  INICIA ACTIVIDAD:");
        check_MvtoCarbon_UsuarioIniciaActividad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                check_MvtoCarbon_UsuarioIniciaActividadItemStateChanged(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(check_MvtoCarbon_UsuarioIniciaActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 250, 30));

        check_MvtoEquipo_UsuarioIniciaActividad.setBackground(new java.awt.Color(255, 255, 255));
        check_MvtoEquipo_UsuarioIniciaActividad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_MvtoEquipo_UsuarioIniciaActividad.setForeground(new java.awt.Color(51, 51, 51));
        check_MvtoEquipo_UsuarioIniciaActividad.setText("USUARIO QUIEN  INICIA ACTIVIDAD:");
        check_MvtoEquipo_UsuarioIniciaActividad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                check_MvtoEquipo_UsuarioIniciaActividadItemStateChanged(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(check_MvtoEquipo_UsuarioIniciaActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 980, 250, 30));

        check_MvtoEquipo_UsuarioCierraActividad.setBackground(new java.awt.Color(255, 255, 255));
        check_MvtoEquipo_UsuarioCierraActividad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_MvtoEquipo_UsuarioCierraActividad.setForeground(new java.awt.Color(51, 51, 51));
        check_MvtoEquipo_UsuarioCierraActividad.setText("USUARIO QUIEN CIERRA ACTIVIDAD:");
        check_MvtoEquipo_UsuarioCierraActividad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                check_MvtoEquipo_UsuarioCierraActividadItemStateChanged(evt);
            }
        });
        check_MvtoEquipo_UsuarioCierraActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_MvtoEquipo_UsuarioCierraActividadActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(check_MvtoEquipo_UsuarioCierraActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 1020, 250, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("LISTADO DE EQUIPOS QUE PARTICIPARON EN LA OPERACI??N");
        jLabel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 590, 800, 30));

        titulo48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo48.setForeground(new java.awt.Color(51, 51, 51));
        titulo48.setText("RECOBRO:");
        titulo48.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(titulo48, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 900, 170, 30));

        jLabel92.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(0, 51, 51));
        jLabel92.setText("Lavado Veh??culo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 320, 120, 20));

        jLabel105.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(0, 51, 51));
        jLabel105.setText("Sitio Origen:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 220, 150, 20));

        MvtCarbon_auxiliarCentroCostoDestino.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_auxiliarCentroCostoDestino.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_auxiliarCentroCostoDestino.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_auxiliarCentroCostoDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 240, 190, 20));

        jLabel108.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(0, 51, 51));
        jLabel108.setText("Sitio Destino:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 240, 150, 20));

        MvtCarbon_lavadoVeh??culo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_lavadoVeh??culo.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_lavadoVeh??culo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_lavadoVeh??culo, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 320, 190, 20));

        check_MvtoEquipo_InicioActividad.setBackground(new java.awt.Color(255, 255, 255));
        check_MvtoEquipo_InicioActividad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_MvtoEquipo_InicioActividad.setForeground(new java.awt.Color(51, 51, 51));
        check_MvtoEquipo_InicioActividad.setText("FECHA DE INICIO:");
        check_MvtoEquipo_InicioActividad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                check_MvtoEquipo_InicioActividadItemStateChanged(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(check_MvtoEquipo_InicioActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 640, 180, 30));

        check_MvtoEquipo_FinalizacionActividad.setBackground(new java.awt.Color(255, 255, 255));
        check_MvtoEquipo_FinalizacionActividad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_MvtoEquipo_FinalizacionActividad.setForeground(new java.awt.Color(51, 51, 51));
        check_MvtoEquipo_FinalizacionActividad.setText("FECHA DE FINALIZACI??N:");
        check_MvtoEquipo_FinalizacionActividad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                check_MvtoEquipo_FinalizacionActividadItemStateChanged(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(check_MvtoEquipo_FinalizacionActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 680, 180, 30));

        fechaFinActividad_MvtoEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFinActividad_MvtoEquipoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFinActividad_MvtoEquipoMouseEntered(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(fechaFinActividad_MvtoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 680, 170, 30));

        fechaInicialActividad_MvtoEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicialActividad_MvtoEquipoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicialActividad_MvtoEquipoMouseEntered(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(fechaInicialActividad_MvtoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 640, 170, 30));

        label_MvtoEquipo_HoraInicial.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_MvtoEquipo_HoraInicial.setForeground(new java.awt.Color(51, 51, 51));
        label_MvtoEquipo_HoraInicial.setText("Hora");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(label_MvtoEquipo_HoraInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 640, -1, 30));

        label_MvtoEquipo_HoraFinal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_MvtoEquipo_HoraFinal.setForeground(new java.awt.Color(51, 51, 51));
        label_MvtoEquipo_HoraFinal.setText("Hora");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(label_MvtoEquipo_HoraFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 680, -1, 30));

        select_MvtoEquipo_HoraInicial.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoEquipo_HoraInicialItemStateChanged(evt);
            }
        });
        select_MvtoEquipo_HoraInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_HoraInicialActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoEquipo_HoraInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 640, 50, 30));

        select_MvtoEquipo_HoraFinal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoEquipo_HoraFinalItemStateChanged(evt);
            }
        });
        select_MvtoEquipo_HoraFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_HoraFinalActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoEquipo_HoraFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 680, 50, 30));

        label_MvtoEquipo_separadorInicial.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        label_MvtoEquipo_separadorInicial.setText(":");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(label_MvtoEquipo_separadorInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 640, 10, 30));

        select_MvtoCarbon_UsuarioIniciaRegistro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoCarbon_UsuarioIniciaRegistroItemStateChanged(evt);
            }
        });
        select_MvtoCarbon_UsuarioIniciaRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoCarbon_UsuarioIniciaRegistroActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoCarbon_UsuarioIniciaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 520, 30));

        label_MvtoEquipo_separadorFinal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        label_MvtoEquipo_separadorFinal.setText(":");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(label_MvtoEquipo_separadorFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 680, 10, 30));

        select_MvtoEquipo_MinutosFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_MinutosFinalActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoEquipo_MinutosFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 680, 50, 30));

        select_MvtoEquipo_MinutosInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_MinutosInicialActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoEquipo_MinutosInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 640, 50, 30));

        label_MvtoEquipo_ZonaHorariaInicial.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        label_MvtoEquipo_ZonaHorariaInicial.setForeground(new java.awt.Color(102, 102, 102));
        label_MvtoEquipo_ZonaHorariaInicial.setText("AM");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(label_MvtoEquipo_ZonaHorariaInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 640, 50, 30));

        label_QuienRealizaLavadoVeh??culo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label_QuienRealizaLavadoVeh??culo.setForeground(new java.awt.Color(51, 51, 51));
        label_QuienRealizaLavadoVeh??culo.setText("EQUIPO QUIEN REALIZA EL LAVADO DEL VEHICULO:");
        label_QuienRealizaLavadoVeh??culo.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(label_QuienRealizaLavadoVeh??culo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 320, 30));

        select_MvtoCarbon_UsuarioCierraRegistro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoCarbon_UsuarioCierraRegistroItemStateChanged(evt);
            }
        });
        select_MvtoCarbon_UsuarioCierraRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoCarbon_UsuarioCierraRegistroActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoCarbon_UsuarioCierraRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 520, 30));

        label_MvtoEquipo_ZonaHorariaFinal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        label_MvtoEquipo_ZonaHorariaFinal.setForeground(new java.awt.Color(102, 102, 102));
        label_MvtoEquipo_ZonaHorariaFinal.setText("AM");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(label_MvtoEquipo_ZonaHorariaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 680, 50, 30));

        select_MvtoEquipo_laborRealizada.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        select_MvtoEquipo_laborRealizada.setForeground(new java.awt.Color(51, 51, 51));
        select_MvtoEquipo_laborRealizada.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        select_MvtoEquipo_laborRealizada.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoEquipo_laborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 720, 380, 30));

        select_MvtoEquipo_tipoEquipo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        select_MvtoEquipo_tipoEquipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoEquipo_tipoEquipoItemStateChanged(evt);
            }
        });
        select_MvtoEquipo_tipoEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                select_MvtoEquipo_tipoEquipoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                select_MvtoEquipo_tipoEquipoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                select_MvtoEquipo_tipoEquipoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                select_MvtoEquipo_tipoEquipoMousePressed(evt);
            }
        });
        select_MvtoEquipo_tipoEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_tipoEquipoActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoEquipo_tipoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 810, 220, 30));

        titulo61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo61.setForeground(new java.awt.Color(51, 51, 51));
        titulo61.setText("MARCA:");
        titulo61.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(titulo61, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 810, 80, 30));

        select_MvtoEquipo_UsuarioIniciaRegistro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoEquipo_UsuarioIniciaRegistroItemStateChanged(evt);
            }
        });
        select_MvtoEquipo_UsuarioIniciaRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_UsuarioIniciaRegistroActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoEquipo_UsuarioIniciaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 980, 510, 30));

        select_MvtoEquipo_UsuarioCierraRegistro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoEquipo_UsuarioCierraRegistroItemStateChanged(evt);
            }
        });
        select_MvtoEquipo_UsuarioCierraRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_UsuarioCierraRegistroActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoEquipo_UsuarioCierraRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 1020, 510, 30));

        select_MvtoEquipo_marcaEquipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoEquipo_marcaEquipoItemStateChanged(evt);
            }
        });
        select_MvtoEquipo_marcaEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                select_MvtoEquipo_marcaEquipoMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                select_MvtoEquipo_marcaEquipoMouseExited(evt);
            }
        });
        select_MvtoEquipo_marcaEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_marcaEquipoActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoEquipo_marcaEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 810, 220, 30));

        titulo62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo62.setForeground(new java.awt.Color(51, 51, 51));
        titulo62.setText("EQUIPO:");
        titulo62.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(titulo62, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 850, 190, 30));

        select_MvtoEquipo_Equipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_EquipoActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoEquipo_Equipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 850, 540, 30));

        titulo30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo30.setForeground(new java.awt.Color(51, 51, 51));
        titulo30.setText("RAZ??N DE FINALIZACI??N:");
        titulo30.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(titulo30, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 940, 190, 30));

        jLabel78.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(0, 51, 51));
        jLabel78.setText("Observaci??n del Lavado Veh??culo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 340, 210, 20));

        jLabel109.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(0, 51, 51));
        jLabel109.setText("Descripci??n:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 400, 100, 20));

        jLabel110.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(0, 51, 51));
        jLabel110.setText("Costo Lavado Veh??culo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 420, 160, 20));

        jLabel111.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(0, 51, 51));
        jLabel111.setText("Valor Recaudo Empresa:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 440, 160, 20));

        jLabel112.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(0, 51, 51));
        jLabel112.setText("Valor Recaudo Equipo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 460, 160, 20));

        jLabel113.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(0, 51, 51));
        jLabel113.setText("Motivo del No Lavado Veh??culo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 320, 200, 20));

        jLabel114.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(0, 51, 51));
        jLabel114.setText("Usuario Quien Registra:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 260, 150, 20));

        jLabel115.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(0, 51, 51));
        jLabel115.setText("Usuario Quien Cierra:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 260, 140, 20));

        MvtCarbon_costoLavadoVeh??culo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_costoLavadoVeh??culo.setForeground(new java.awt.Color(0, 51, 51));
        MvtCarbon_costoLavadoVeh??culo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_costoLavadoVeh??culo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 420, 190, 20));

        MvtCarbon_observaci??nLavadoVeh??culo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_observaci??nLavadoVeh??culo.setForeground(new java.awt.Color(0, 51, 51));
        MvtCarbon_observaci??nLavadoVeh??culo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_observaci??nLavadoVeh??culo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 340, 580, 20));

        MvtCarbon_valorRecaudoEmpresa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_valorRecaudoEmpresa.setForeground(new java.awt.Color(0, 51, 51));
        MvtCarbon_valorRecaudoEmpresa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_valorRecaudoEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 440, 190, 20));

        MvtCarbon_valorRecaudoEquipo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_valorRecaudoEquipo.setForeground(new java.awt.Color(0, 51, 51));
        MvtCarbon_valorRecaudoEquipo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_valorRecaudoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 460, 190, 20));

        MvtCarbon_motivoNoLavadoVehiculo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_motivoNoLavadoVehiculo.setForeground(new java.awt.Color(0, 51, 51));
        MvtCarbon_motivoNoLavadoVehiculo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_motivoNoLavadoVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 320, 220, 20));

        MvtCarbon_usuarioQuienInicia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_usuarioQuienInicia.setForeground(new java.awt.Color(0, 51, 51));
        MvtCarbon_usuarioQuienInicia.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_usuarioQuienInicia, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 260, 190, 20));

        MvtCarbon_usuarioQuienCierra.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_usuarioQuienCierra.setForeground(new java.awt.Color(0, 51, 51));
        MvtCarbon_usuarioQuienCierra.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_usuarioQuienCierra, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 260, 280, 20));

        registrar_MvtoEquipo.setBackground(new java.awt.Color(255, 255, 255));
        registrar_MvtoEquipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        registrar_MvtoEquipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save.png"))); // NOI18N
        registrar_MvtoEquipo.setText("REGISTRAR MODIFICACI??N");
        registrar_MvtoEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrar_MvtoEquipoActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(registrar_MvtoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 1140, 250, 50));

        mvtoEquipo_selectRazonFinalzaci??n.setToolTipText("");
        mvtoEquipo_selectRazonFinalzaci??n.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mvtoEquipo_selectRazonFinalzaci??nItemStateChanged(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtoEquipo_selectRazonFinalzaci??n, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 940, 510, 30));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 890, 740, 10));

        titulo59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo59.setForeground(new java.awt.Color(0, 153, 153));
        titulo59.setText("CAMBIAR EQUIPO:");
        titulo59.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(titulo59, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 770, 150, 30));

        titulo60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo60.setForeground(new java.awt.Color(51, 51, 51));
        titulo60.setText("SELECCIONAR REGISTRO:");
        titulo60.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(titulo60, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, 180, 30));

        titulo64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo64.setForeground(new java.awt.Color(51, 51, 51));
        titulo64.setText("TIPO:");
        titulo64.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(titulo64, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 810, 150, 30));

        titulo50.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        titulo50.setForeground(new java.awt.Color(255, 51, 51));
        titulo50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo50.setText("*");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(titulo50, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, 20, 20));

        titulo51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo51.setForeground(new java.awt.Color(51, 51, 51));
        titulo51.setText("RAZON DE MODIFICACI??N:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(titulo51, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 190, 30));

        textArea_MvtoCarbon_razonModificacion.setColumns(20);
        textArea_MvtoCarbon_razonModificacion.setRows(5);
        jScrollPane7.setViewportView(textArea_MvtoCarbon_razonModificacion);

        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, 450, 80));

        titulo52.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        titulo52.setForeground(new java.awt.Color(255, 51, 51));
        titulo52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo52.setText("*");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(titulo52, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 1070, 20, 20));

        titulo63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo63.setForeground(new java.awt.Color(51, 51, 51));
        titulo63.setText("LABOR REALIZADA:");
        titulo63.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(titulo63, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 720, 150, 30));

        titulo53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo53.setForeground(new java.awt.Color(51, 51, 51));
        titulo53.setText("RAZON DE MODIFICACI??N:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(titulo53, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 1060, 210, 30));

        MvtCarbon_equipoLavadoDescripci??n.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_equipoLavadoDescripci??n.setForeground(new java.awt.Color(0, 51, 51));
        MvtCarbon_equipoLavadoDescripci??n.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_equipoLavadoDescripci??n, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 400, 510, 20));

        registrar_MvtoCarbon.setBackground(new java.awt.Color(255, 255, 255));
        registrar_MvtoCarbon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        registrar_MvtoCarbon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save.png"))); // NOI18N
        registrar_MvtoCarbon.setText("REGISTRAR MODIFICACI??N");
        registrar_MvtoCarbon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrar_MvtoCarbonActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(registrar_MvtoCarbon, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 510, 240, 50));

        textArea_MvtoEquipo_razonModificacion.setColumns(20);
        textArea_MvtoEquipo_razonModificacion.setRows(5);
        jScrollPane8.setViewportView(textArea_MvtoEquipo_razonModificacion);

        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 1060, 510, 70));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 480, 800, 10));

        select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.setToolTipText("");
        select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culoItemStateChanged(evt);
            }
        });
        select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culoActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 380, 450, 30));

        label_motivoNoLavado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label_motivoNoLavado.setForeground(new java.awt.Color(51, 51, 51));
        label_motivoNoLavado.setText("MOTIVO DEL NO LAVADO DEL VEH??CULO:");
        label_motivoNoLavado.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(label_motivoNoLavado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 320, 30));

        jLabel117.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(0, 51, 51));
        jLabel117.setText("Usuario Quien Registra:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 820, 160, 20));

        MvtEquipo_usuarioQuienInicia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtEquipo_usuarioQuienInicia.setForeground(new java.awt.Color(0, 51, 51));
        MvtEquipo_usuarioQuienInicia.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtEquipo_usuarioQuienInicia, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 820, 630, 20));

        jLabel118.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel118.setForeground(new java.awt.Color(0, 51, 51));
        jLabel118.setText("Usuario Quien Cierra:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 840, 160, 20));

        MvtEquipo_usuarioQuienCierra.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtEquipo_usuarioQuienCierra.setForeground(new java.awt.Color(0, 51, 51));
        MvtEquipo_usuarioQuienCierra.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtEquipo_usuarioQuienCierra, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 840, 630, 20));

        select_MvtoCarbon_motivoNoLavadoVehiculo.setToolTipText("");
        select_MvtoCarbon_motivoNoLavadoVehiculo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoCarbon_motivoNoLavadoVehiculoItemStateChanged(evt);
            }
        });
        select_MvtoCarbon_motivoNoLavadoVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoCarbon_motivoNoLavadoVehiculoActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(select_MvtoCarbon_motivoNoLavadoVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 380, 450, 30));

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(0, 51, 51));
        jLabel69.setText("C??digo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 400, 70, 20));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 800, 1170));

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(0, 51, 51));
        jLabel68.setText("Cantidad Minutos:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 970, 130, -1));

        jLabel116.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel116.setForeground(new java.awt.Color(0, 51, 51));
        jLabel116.setText("Equipo Quien Realiza Lavado");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 370, 230, 20));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 800, 740, 10));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 370, 800, 10));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 50, 800, 1150));

        add(InternaFrame_VisualizarMvtoCarbon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1660, 1240));

        VentanaInterna_PesoCero.setClosable(true);
        VentanaInterna_PesoCero.setVisible(false);
        VentanaInterna_PesoCero.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 153, 51));
        jLabel19.setText("INFORMACI??N DEL VEH??CULO NUEVO:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, 850, 30));

        buscadorPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscadorPlacaActionPerformed(evt);
            }
        });
        VentanaInterna_PesoCero.getContentPane().add(buscadorPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 360, 40));

        btnConsultar.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        btnConsultar.setToolTipText("CONSULTAR MOTONAVES");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        VentanaInterna_PesoCero.getContentPane().add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, 60, 40));

        btnLimpiar.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/clean.png"))); // NOI18N
        btnLimpiar.setToolTipText("BORRAR RESULTADOS");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        VentanaInterna_PesoCero.getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 60, 40));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btnCancelar.setToolTipText("CANCELAR BUSQUEDA");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        VentanaInterna_PesoCero.getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, 60, 40));

        tabla1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabla1);

        VentanaInterna_PesoCero.getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 1260, 160));

        PesoCero_placa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_placa.setForeground(new java.awt.Color(153, 0, 51));
        PesoCero_placa.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_placa, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 230, 20));

        fechaInicio_destareVehiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicio_destareVehiculoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicio_destareVehiculoMouseEntered(evt);
            }
        });
        VentanaInterna_PesoCero.getContentPane().add(fechaInicio_destareVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 170, 30));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Hora");
        VentanaInterna_PesoCero.getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, -1, 30));

        horaInicio_destareVehiculo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                horaInicio_destareVehiculoItemStateChanged(evt);
            }
        });
        VentanaInterna_PesoCero.getContentPane().add(horaInicio_destareVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 50, 30));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setText(":");
        VentanaInterna_PesoCero.getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 20, 30));

        minutoInicio_destareVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoInicio_destareVehiculoActionPerformed(evt);
            }
        });
        VentanaInterna_PesoCero.getContentPane().add(minutoInicio_destareVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 50, 30));

        horarioTiempoInicio_destareVehiculo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoInicio_destareVehiculo.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoInicio_destareVehiculo.setText("AM");
        VentanaInterna_PesoCero.getContentPane().add(horarioTiempoInicio_destareVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, 40, 30));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(102, 102, 102));
        jLabel28.setText("FINALIZACI??N:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, 90, 30));

        fechaFin_destareVehiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFin_destareVehiculoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFin_destareVehiculoMouseEntered(evt);
            }
        });
        VentanaInterna_PesoCero.getContentPane().add(fechaFin_destareVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 170, 30));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("Hora");
        VentanaInterna_PesoCero.getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 70, -1, 30));

        horaFin_destareVehiculo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                horaFin_destareVehiculoItemStateChanged(evt);
            }
        });
        horaFin_destareVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horaFin_destareVehiculoActionPerformed(evt);
            }
        });
        VentanaInterna_PesoCero.getContentPane().add(horaFin_destareVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 70, 50, 30));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel30.setText(":");
        VentanaInterna_PesoCero.getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 70, 20, 30));

        minutoFin_destareVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoFin_destareVehiculoActionPerformed(evt);
            }
        });
        VentanaInterna_PesoCero.getContentPane().add(minutoFin_destareVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 70, 50, 30));

        horarioTiempoIFinal_destareVehiculo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoIFinal_destareVehiculo.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoIFinal_destareVehiculo.setText("AM");
        VentanaInterna_PesoCero.getContentPane().add(horarioTiempoIFinal_destareVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 70, 50, 30));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 102, 102));
        jLabel31.setText("CONSULTAR VEH??CULOS:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 190, 30));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 102, 102));
        jLabel32.setText("Seleccione la fecha del destare del veh??culo:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 410, 20));
        VentanaInterna_PesoCero.getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 1260, 10));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 102, 102));
        jLabel33.setText("Seleccione la placa:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 140, 40));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(102, 102, 102));
        jLabel34.setText("INICIO:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 60, 30));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 51, 51));
        jLabel35.setText("Placa:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 100, 20));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 51, 51));
        jLabel36.setText("Fecha Entrada:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 100, 20));

        PesoCero_cliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_cliente.setForeground(new java.awt.Color(153, 0, 51));
        PesoCero_cliente.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 440, 230, 20));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 51, 51));
        jLabel40.setText("Peso Vacio:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 100, 20));

        PesoCero_pesoVacio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_pesoVacio.setForeground(new java.awt.Color(153, 0, 51));
        PesoCero_pesoVacio.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_pesoVacio, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 230, 20));

        PesoCero_articulo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_articulo.setForeground(new java.awt.Color(153, 0, 51));
        PesoCero_articulo.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_articulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 440, 230, 20));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 51, 51));
        jLabel43.setText("Art??culo:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 440, 110, 20));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 51, 51));
        jLabel46.setText("Deposito:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 380, 110, 20));

        PesoCero_deposito.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_deposito.setForeground(new java.awt.Color(153, 0, 51));
        PesoCero_deposito.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_deposito, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, 230, 20));

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 51, 51));
        jLabel48.setText("Peso Lleno:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 400, 110, 20));

        PesoCero_pesoLleno.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_pesoLleno.setForeground(new java.awt.Color(153, 0, 51));
        PesoCero_pesoLleno.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_pesoLleno, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 400, 230, 20));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(0, 51, 51));
        jLabel53.setText("Peso Neto:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 400, 110, 20));

        PesoCero_pesoNeto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_pesoNeto.setForeground(new java.awt.Color(153, 0, 51));
        PesoCero_pesoNeto.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_pesoNeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 400, 230, 20));

        PesoCero_fechaEntrada.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_fechaEntrada.setForeground(new java.awt.Color(153, 0, 51));
        PesoCero_fechaEntrada.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_fechaEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 420, 230, 20));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 51, 51));
        jLabel37.setText("Cliente:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 100, 20));

        PesoCero_fechaSalida.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_fechaSalida.setForeground(new java.awt.Color(153, 0, 51));
        PesoCero_fechaSalida.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_fechaSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 420, 230, 20));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 51, 51));
        jLabel38.setText("Fecha Salida:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, 110, 20));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 51, 51));
        jLabel39.setText("Consecutivo:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 380, 110, 20));

        PesoCero_consecutivo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_consecutivo.setForeground(new java.awt.Color(153, 0, 51));
        PesoCero_consecutivo.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_consecutivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 380, 230, 20));

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 51, 51));
        jLabel42.setText("Transportadora:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 420, 110, 20));

        PesoCero_transportadora.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_transportadora.setForeground(new java.awt.Color(153, 0, 51));
        PesoCero_transportadora.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_transportadora, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 420, 230, 20));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(153, 0, 51));
        jLabel23.setText("INFORMACI??N DEL VEH??CULO ANTERIOR:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 840, 30));

        PesoCero_placa_nuevo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_placa_nuevo.setForeground(new java.awt.Color(0, 153, 51));
        PesoCero_placa_nuevo.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_placa_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 530, 230, 20));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 51, 51));
        jLabel44.setText("Placa:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 100, 20));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 51, 51));
        jLabel45.setText("Fecha Entrada:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 100, 20));

        PesoCero_cliente_nuevo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_cliente_nuevo.setForeground(new java.awt.Color(0, 153, 51));
        PesoCero_cliente_nuevo.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_cliente_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 590, 230, 20));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 51, 51));
        jLabel47.setText("Peso Vacio:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 100, 20));

        PesoCero_pesoVacio_nuevo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_pesoVacio_nuevo.setForeground(new java.awt.Color(0, 153, 51));
        PesoCero_pesoVacio_nuevo.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_pesoVacio_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 550, 230, 20));

        PesoCero_articulo_nuevo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_articulo_nuevo.setForeground(new java.awt.Color(0, 153, 51));
        PesoCero_articulo_nuevo.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_articulo_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 590, 230, 20));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 51, 51));
        jLabel49.setText("Art??culo:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 590, 110, 20));

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(0, 51, 51));
        jLabel50.setText("Deposito:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 530, 110, 20));

        PesoCero_deposito_nuevo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_deposito_nuevo.setForeground(new java.awt.Color(0, 153, 51));
        PesoCero_deposito_nuevo.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_deposito_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 530, 230, 20));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 51, 51));
        jLabel51.setText("Peso Lleno:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 550, 110, 20));

        PesoCero_pesoLleno_nuevo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_pesoLleno_nuevo.setForeground(new java.awt.Color(0, 153, 51));
        PesoCero_pesoLleno_nuevo.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_pesoLleno_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 550, 230, 20));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(0, 51, 51));
        jLabel54.setText("Peso Neto:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 550, 110, 20));

        PesoCero_pesoNeto_nuevo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_pesoNeto_nuevo.setForeground(new java.awt.Color(0, 153, 51));
        PesoCero_pesoNeto_nuevo.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_pesoNeto_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 550, 230, 20));

        PesoCero_fechaEntrada_nuevo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_fechaEntrada_nuevo.setForeground(new java.awt.Color(0, 153, 51));
        PesoCero_fechaEntrada_nuevo.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_fechaEntrada_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 570, 230, 20));

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 51, 51));
        jLabel52.setText("Cliente:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, 100, 20));

        PesoCero_fechaSalida_nuevo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_fechaSalida_nuevo.setForeground(new java.awt.Color(0, 153, 51));
        PesoCero_fechaSalida_nuevo.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_fechaSalida_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 570, 230, 20));

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(0, 51, 51));
        jLabel59.setText("Fecha Salida:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 570, 110, 20));

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(0, 51, 51));
        jLabel62.setText("Consecutivo:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 530, 110, 20));

        PesoCero_consecutivo_nuevo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_consecutivo_nuevo.setForeground(new java.awt.Color(0, 153, 51));
        PesoCero_consecutivo_nuevo.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_consecutivo_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 530, 230, 20));

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(0, 51, 51));
        jLabel67.setText("Transportadora:");
        VentanaInterna_PesoCero.getContentPane().add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 570, 110, 20));

        PesoCero_transportadora_nuevo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PesoCero_transportadora_nuevo.setForeground(new java.awt.Color(0, 153, 51));
        PesoCero_transportadora_nuevo.setText("...........................................");
        VentanaInterna_PesoCero.getContentPane().add(PesoCero_transportadora_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 570, 230, 20));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Ingresar.png"))); // NOI18N
        jButton1.setText("ACTUALIZAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        VentanaInterna_PesoCero.getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 630, 220, 40));

        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        VentanaInterna_PesoCero.getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 1020, 290));
        VentanaInterna_PesoCero.getContentPane().add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 1020, 10));
        VentanaInterna_PesoCero.getContentPane().add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 1020, 10));
        VentanaInterna_PesoCero.getContentPane().add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 1020, 10));
        VentanaInterna_PesoCero.getContentPane().add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 1020, 10));

        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        VentanaInterna_PesoCero.getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 1260, 110));

        add(VentanaInterna_PesoCero, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1430, 800));

        InternaFrame_buscarUsuario.setClosable(true);
        InternaFrame_buscarUsuario.setPreferredSize(new java.awt.Dimension(1106, 714));
        InternaFrame_buscarUsuario.setVisible(false);
        InternaFrame_buscarUsuario.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaUsuario = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tablaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuarioMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tablaUsuario);

        InternaFrame_buscarUsuario.getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 1120, 510));
        InternaFrame_buscarUsuario.getContentPane().add(valorBusquedaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 290, 40));

        btnConsultarUsuariosQuienRegistra.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultarUsuariosQuienRegistra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultarUsuariosQuienRegistra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        btnConsultarUsuariosQuienRegistra.setToolTipText("CONSULTAR MOTONAVES");
        btnConsultarUsuariosQuienRegistra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarUsuariosQuienRegistraActionPerformed(evt);
            }
        });
        InternaFrame_buscarUsuario.getContentPane().add(btnConsultarUsuariosQuienRegistra, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 60, 40));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 102));
        jLabel10.setText("CONSULTAR USUARIOS QUIEN REGISTRA INFORMACI??N:");
        InternaFrame_buscarUsuario.getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 430, 40));

        btnCancelarUsuariosQuienRegistra.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelarUsuariosQuienRegistra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancelarUsuariosQuienRegistra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btnCancelarUsuariosQuienRegistra.setToolTipText("CANCELAR BUSQUEDA");
        btnCancelarUsuariosQuienRegistra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarUsuariosQuienRegistraActionPerformed(evt);
            }
        });
        InternaFrame_buscarUsuario.getContentPane().add(btnCancelarUsuariosQuienRegistra, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, 60, 40));

        btnLimpiarUsuariosQuienRegistra.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiarUsuariosQuienRegistra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLimpiarUsuariosQuienRegistra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/clean.png"))); // NOI18N
        btnLimpiarUsuariosQuienRegistra.setToolTipText("BORRAR RESULTADOS");
        btnLimpiarUsuariosQuienRegistra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarUsuariosQuienRegistraActionPerformed(evt);
            }
        });
        InternaFrame_buscarUsuario.getContentPane().add(btnLimpiarUsuariosQuienRegistra, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 60, 40));

        add(InternaFrame_buscarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1340, 740));

        InternaFrame_buscarCliente.setClosable(true);
        InternaFrame_buscarCliente.setVisible(false);
        InternaFrame_buscarCliente.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaCliente = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tablaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClienteMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tablaCliente);

        InternaFrame_buscarCliente.getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 1070, 620));
        InternaFrame_buscarCliente.getContentPane().add(valorBusquedaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 310, 40));

        btnConsultarCliente.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultarCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        btnConsultarCliente.setToolTipText("CONSULTAR MOTONAVES");
        btnConsultarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarClienteActionPerformed(evt);
            }
        });
        InternaFrame_buscarCliente.getContentPane().add(btnConsultarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 60, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("CONSULTAR CLIENTE:");
        InternaFrame_buscarCliente.getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 170, 40));

        btnCancelarCliente.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelarCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancelarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btnCancelarCliente.setToolTipText("CANCELAR BUSQUEDA");
        btnCancelarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarClienteActionPerformed(evt);
            }
        });
        InternaFrame_buscarCliente.getContentPane().add(btnCancelarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 60, 40));

        btnLimpiarCliente.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiarCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLimpiarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/clean.png"))); // NOI18N
        btnLimpiarCliente.setToolTipText("BORRAR RESULTADOS");
        btnLimpiarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarClienteActionPerformed(evt);
            }
        });
        InternaFrame_buscarCliente.getContentPane().add(btnLimpiarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 60, 40));

        add(InternaFrame_buscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1340, 740));

        InternalFrameSelectorCampos.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrameSelectorCampos.setClosable(true);
        InternalFrameSelectorCampos.setVisible(false);
        InternalFrameSelectorCampos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCamposAplicarConfig.png"))); // NOI18N
        jButton2.setText("Aplicar Configuraci??n");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 610, 230, 40));

        selectAll.setBackground(new java.awt.Color(255, 255, 255));
        selectAll.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        selectAll.setText("Todos");
        selectAll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectAll.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectAllItemStateChanged(evt);
            }
        });
        selectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAllActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, 110, 20));

        selectDescargue_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_codigo.setText("C??digo");
        selectDescargue_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectDescargue_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectDescargue_codigoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 190, -1));

        selectDescargue_fechaRegistro.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_fechaRegistro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_fechaRegistro.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_fechaRegistro.setText("Fecha_Registro");
        selectDescargue_fechaRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_fechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 190, -1));

        selectDescargue_deposito.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_deposito.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_deposito.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_deposito.setText("Deposito");
        selectDescargue_deposito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_deposito, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 190, -1));

        selectDescargue_consecutivo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_consecutivo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_consecutivo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_consecutivo.setText("Consecutivo");
        selectDescargue_consecutivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_consecutivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 190, -1));

        selectDescargue_placa.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_placa.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_placa.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_placa.setText("Placa");
        selectDescargue_placa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_placa, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 190, -1));

        selectDescargue_pesoVacio.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_pesoVacio.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_pesoVacio.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_pesoVacio.setText("Peso_Vacio");
        selectDescargue_pesoVacio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectDescargue_pesoVacio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectDescargue_pesoVacioActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_pesoVacio, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, 190, -1));

        selectDescargue_pesoLleno.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_pesoLleno.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_pesoLleno.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_pesoLleno.setText("Peso_Lleno");
        selectDescargue_pesoLleno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_pesoLleno, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 280, 190, -1));

        selectDescargue_pesoNeto.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_pesoNeto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_pesoNeto.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_pesoNeto.setText("Peso_Neto");
        selectDescargue_pesoNeto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_pesoNeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, 190, -1));

        selectDescargue_numOrden.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_numOrden.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_numOrden.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_numOrden.setText("N??mero Orden");
        selectDescargue_numOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_numOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, 190, -1));

        selectDescargue_fechaEntradaVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_fechaEntradaVehiculo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_fechaEntradaVehiculo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_fechaEntradaVehiculo.setText("Fecha_Entrada_Vehiculo");
        selectDescargue_fechaEntradaVehiculo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_fechaEntradaVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, 190, -1));

        selectDescargue_registroManual.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_registroManual.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_registroManual.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_registroManual.setText("RegistroManual");
        selectDescargue_registroManual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_registroManual, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 540, 190, -1));

        selectDescargue_clienteCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_clienteCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_clienteCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_clienteCodigo.setText("Cliente_C??digo");
        selectDescargue_clienteCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_clienteCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 190, -1));

        selectDescargue_transportadoraNit.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_transportadoraNit.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_transportadoraNit.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_transportadoraNit.setText("Transportadora_NIT");
        selectDescargue_transportadoraNit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_transportadoraNit, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 190, -1));

        selectDescargue_transportadoraCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_transportadoraCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_transportadoraCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_transportadoraCodigo.setText("Transportadora_C??digo");
        selectDescargue_transportadoraCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_transportadoraCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 190, -1));

        selectDescargue_usuarioRegistroManualCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_usuarioRegistroManualCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_usuarioRegistroManualCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_usuarioRegistroManualCodigo.setText("UsuarioRegistr??Manual_C??digo");
        selectDescargue_usuarioRegistroManualCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioRegistroManualCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 560, 190, -1));

        selectDescargue_usuarioRegistroManualNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_usuarioRegistroManualNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_usuarioRegistroManualNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_usuarioRegistroManualNombre.setText("UsuarioRegistr??Manual_Nombre");
        selectDescargue_usuarioRegistroManualNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioRegistroManualNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 580, 190, -1));

        selectDescargue_fechaSalidaVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_fechaSalidaVehiculo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_fechaSalidaVehiculo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_fechaSalidaVehiculo.setText("Fecha_Salida_Vehiculo");
        selectDescargue_fechaSalidaVehiculo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_fechaSalidaVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, 190, -1));

        selectDescargue_fechaInicioDescargue.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_fechaInicioDescargue.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_fechaInicioDescargue.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_fechaInicioDescargue.setText("Fecha_Inicio_Descargue");
        selectDescargue_fechaInicioDescargue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_fechaInicioDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 190, -1));

        selectDescargue_cantidadMinutosDescargue.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_cantidadMinutosDescargue.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_cantidadMinutosDescargue.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_cantidadMinutosDescargue.setText("Cantidad_Minutos_Descargue");
        selectDescargue_cantidadMinutosDescargue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_cantidadMinutosDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 400, 190, -1));

        titulo2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo2.setText("SELECTOR DE CAMPOS");
        titulo2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1320, 40));

        titulo13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo13.setText("DESCARGUE DE CARBON");
        titulo13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 600, 20));

        jSeparator21.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternalFrameSelectorCampos.getContentPane().add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 0, 430));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCamposDefauls.png"))); // NOI18N
        jButton3.setText("Campos Predeterminados");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 610, 230, 40));

        selectDescargue_mes.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_mes.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_mes.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_mes.setText("Mes");
        selectDescargue_mes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 190, -1));

        selectDescargue_clienteNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_clienteNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_clienteNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_clienteNombre.setText("Cliente_Nombre");
        selectDescargue_clienteNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_clienteNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 190, -1));

        selectDescargue_articuloCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_articuloCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_articuloCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_articuloCodigo.setText("Articulo_C??digo");
        selectDescargue_articuloCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectDescargue_articuloCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectDescargue_articuloCodigoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_articuloCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 190, -1));

        selectDescargue_centroOperacion.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_centroOperacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_centroOperacion.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_centroOperacion.setText("Centro Operaci??n");
        selectDescargue_centroOperacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 190, -1));

        selectDescargue_centroCostoMayor.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_centroCostoMayor.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_centroCostoMayor.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_centroCostoMayor.setText("CentroCosto_Mayor");
        selectDescargue_centroCostoMayor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectDescargue_centroCostoMayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectDescargue_centroCostoMayorActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_centroCostoMayor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 190, -1));

        selectDescargue_observaci??n.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_observaci??n.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_observaci??n.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_observaci??n.setText("Observaci??n");
        selectDescargue_observaci??n.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_observaci??n, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 480, 190, -1));

        selectDescargue_estado.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_estado.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_estado.setText("Estado");
        selectDescargue_estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 500, 190, -1));

        selectDescargue_conexionPesoCcarga.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_conexionPesoCcarga.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_conexionPesoCcarga.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_conexionPesoCcarga.setText("Conexion_Peso_Ccarga");
        selectDescargue_conexionPesoCcarga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_conexionPesoCcarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 520, 190, -1));

        selectDescargue_fechaFinDescargue.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_fechaFinDescargue.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_fechaFinDescargue.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_fechaFinDescargue.setText("Fecha_Final_Descargue");
        selectDescargue_fechaFinDescargue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_fechaFinDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, 190, -1));

        selectDescargue_centroCostoAuxiliarOrigen.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_centroCostoAuxiliarOrigen.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_centroCostoAuxiliarOrigen.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_centroCostoAuxiliarOrigen.setText("Centro Costo Auxiliar_Origen");
        selectDescargue_centroCostoAuxiliarOrigen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_centroCostoAuxiliarOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 190, -1));

        selectDescargue_centroCosto.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_centroCosto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_centroCosto.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_centroCosto.setText("Centro Costo");
        selectDescargue_centroCosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_centroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 190, -1));

        selectDescargue_articuloUnidadNegocio.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_articuloUnidadNegocio.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_articuloUnidadNegocio.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_articuloUnidadNegocio.setText("Art??culo_Unidad_Negocio");
        selectDescargue_articuloUnidadNegocio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_articuloUnidadNegocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 190, -1));

        selectDescargue_laborRealizada.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_laborRealizada.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_laborRealizada.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_laborRealizada.setText("Labor Realizada");
        selectDescargue_laborRealizada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectDescargue_laborRealizada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectDescargue_laborRealizadaActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_laborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 190, -1));

        selectDescargue_articuloNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_articuloNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_articuloNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_articuloNombre.setText("Art??culo_Nombre");
        selectDescargue_articuloNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_articuloNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 190, -1));

        selectDescargue_articuloCodigoERP.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_articuloCodigoERP.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_articuloCodigoERP.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_articuloCodigoERP.setText("Art??culo_C??digo_ERP");
        selectDescargue_articuloCodigoERP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_articuloCodigoERP, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 190, -1));

        selectDescargue_articuloTipo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_articuloTipo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_articuloTipo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_articuloTipo.setText("Art??culo_Tipo");
        selectDescargue_articuloTipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_articuloTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 190, -1));

        selectDescargue_transportadoraNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_transportadoraNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_transportadoraNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_transportadoraNombre.setText("Transportadora_Nombre");
        selectDescargue_transportadoraNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_transportadoraNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 190, -1));

        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setText("UsuarioQuienRegistr??Veh??culo_C??digo");
        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioQuienRegistraVehiculoCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 420, 210, -1));

        selectDescargue_usuarioQuienRegistraVehiculoNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_usuarioQuienRegistraVehiculoNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_usuarioQuienRegistraVehiculoNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_usuarioQuienRegistraVehiculoNombre.setText("UsuarioQuienRegistr??Veh??culo_Nombre");
        selectDescargue_usuarioQuienRegistraVehiculoNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioQuienRegistraVehiculoNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 440, 220, -1));

        selectDescargue_usuarioRegistroManualCorreo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_usuarioRegistroManualCorreo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_usuarioRegistroManualCorreo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_usuarioRegistroManualCorreo.setText("UsuarioRegistr??Manual_Correo");
        selectDescargue_usuarioRegistroManualCorreo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectDescargue_usuarioRegistroManualCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectDescargue_usuarioRegistroManualCorreoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioRegistroManualCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 150, 190, -1));

        selectMvtoEquipo_clienteCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_clienteCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_clienteCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_clienteCodigo.setText("Cliente C??digo");
        selectMvtoEquipo_clienteCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_clienteCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 90, 200, -1));

        selectAsignacion_equipoCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoCodigo.setText("Equipo C??digo");
        selectAsignacion_equipoCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 170, 190, -1));

        selectAsignacion_equipoMarca.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoMarca.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoMarca.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoMarca.setText("Equipo Marca");
        selectAsignacion_equipoMarca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 210, 190, -1));

        selectAsignacion_equipoModelo.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoModelo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoModelo.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoModelo.setText("Equipo Modelo");
        selectAsignacion_equipoModelo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 230, 190, -1));

        selectMvtoEquipo_fechaRegistro.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_fechaRegistro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_fechaRegistro.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_fechaRegistro.setText("Fecha_Registro");
        selectMvtoEquipo_fechaRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_fechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 360, 190, -1));

        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setText("Nombre_Usuario_Registra_MvtoEquipo");
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioRegistraMvto_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 350, 220, -1));

        selectMvtoEquipo_cantidadMinutosDescargue.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_cantidadMinutosDescargue.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_cantidadMinutosDescargue.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_cantidadMinutosDescargue.setText("Cantidad_Minutos_Descargue");
        selectMvtoEquipo_cantidadMinutosDescargue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_cantidadMinutosDescargue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_cantidadMinutosDescargueActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_cantidadMinutosDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 250, 190, -1));

        selectMvtoEquipo_numeroOrden.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_numeroOrden.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_numeroOrden.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_numeroOrden.setText("N??mero Orden");
        selectMvtoEquipo_numeroOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_numeroOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 420, 190, -1));

        selectAsignacion_equipoTipo.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoTipo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoTipo.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoTipo.setText("Equipo Tipo");
        selectAsignacion_equipoTipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectAsignacion_equipoTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsignacion_equipoTipoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 190, 190, -1));

        selectMvtoEquipo_clienteNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_clienteNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_clienteNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_clienteNombre.setText("Cliente Nombre");
        selectMvtoEquipo_clienteNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_clienteNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 110, 200, -1));

        selectMvtoEquipo_motonaveDescripci??n.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_motonaveDescripci??n.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_motonaveDescripci??n.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_motonaveDescripci??n.setText("Motonave Descripci??n");
        selectMvtoEquipo_motonaveDescripci??n.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_motonaveDescripci??n.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_motonaveDescripci??nActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_motonaveDescripci??n, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 190, 200, -1));

        selectMvtoEquipo_centroOperacion.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroOperacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroOperacion.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroOperacion.setText("Centro Operaci??n");
        selectMvtoEquipo_centroOperacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 440, 200, -1));

        selectMvtoEquipo_subcentroCosto.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_subcentroCosto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_subcentroCosto.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_subcentroCosto.setText("Subcentro Costo");
        selectMvtoEquipo_subcentroCosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_subcentroCosto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_subcentroCostoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_subcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 460, 200, -1));

        selectAsignacion_fechaInicio.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_fechaInicio.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_fechaInicio.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_fechaInicio.setText("Fecha_inicio");
        selectAsignacion_fechaInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectAsignacion_fechaInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsignacion_fechaInicioActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 270, 190, -1));

        selectAsignacion_fechaFinalizacion.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_fechaFinalizacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_fechaFinalizacion.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_fechaFinalizacion.setText("Fecha_Finalizaci??n");
        selectAsignacion_fechaFinalizacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_fechaFinalizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 290, 190, -1));

        selectAsignacion_cantidadMinutos_Programados.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_cantidadMinutos_Programados.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_cantidadMinutos_Programados.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_cantidadMinutos_Programados.setText("Cantidad_Minutos_Programadas");
        selectAsignacion_cantidadMinutos_Programados.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectAsignacion_cantidadMinutos_Programados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsignacion_cantidadMinutos_ProgramadosActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_cantidadMinutos_Programados, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 310, 190, -1));

        selectAsignacion_equipoPertenencia.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoPertenencia.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoPertenencia.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoPertenencia.setText("Equipo Pertenencia");
        selectAsignacion_equipoPertenencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoPertenencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 270, 190, -1));

        selectMvtoEquipo_proveedorEquipoNit.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_proveedorEquipoNit.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_proveedorEquipoNit.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_proveedorEquipoNit.setText("Proveedor Equipo NIT");
        selectMvtoEquipo_proveedorEquipoNit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_proveedorEquipoNit, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 380, 190, -1));

        selectMvtoEquipo_proveedorEquipoNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_proveedorEquipoNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_proveedorEquipoNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_proveedorEquipoNombre.setText("Proveedor Equipo Nombre");
        selectMvtoEquipo_proveedorEquipoNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_proveedorEquipoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_proveedorEquipoNombreActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_proveedorEquipoNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 400, 190, -1));

        selectMvtoEquipo_laborRealizada.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_laborRealizada.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_laborRealizada.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_laborRealizada.setText("Labor Realizada");
        selectMvtoEquipo_laborRealizada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_laborRealizada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_laborRealizadaActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_laborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 70, 190, -1));

        selectMvtoEquipo_fechaInicioDescargue.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_fechaInicioDescargue.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_fechaInicioDescargue.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_fechaInicioDescargue.setText("Fecha Inicio_Actividad");
        selectMvtoEquipo_fechaInicioDescargue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_fechaInicioDescargue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_fechaInicioDescargueActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_fechaInicioDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 210, 190, -1));

        selectMvtoEquipo_fechaFinDescargue.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_fechaFinDescargue.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_fechaFinDescargue.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_fechaFinDescargue.setText("Fecha Final_Actividad");
        selectMvtoEquipo_fechaFinDescargue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_fechaFinDescargue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_fechaFinDescargueActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_fechaFinDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 230, 190, -1));

        selectMvtoEquipo_ValorHoraEquipo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_ValorHoraEquipo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_ValorHoraEquipo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_ValorHoraEquipo.setText("Valor_Hora_Equipo");
        selectMvtoEquipo_ValorHoraEquipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_ValorHoraEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 270, 190, -1));

        selectMvtoEquipo_costoTotal.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_costoTotal.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_costoTotal.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_costoTotal.setText("Costo_Total");
        selectMvtoEquipo_costoTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_costoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 290, 190, -1));

        selectMvtoEquipo_Recobro.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_Recobro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_Recobro.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_Recobro.setText("Recobro");
        selectMvtoEquipo_Recobro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_Recobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 310, 190, -1));

        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setText("C??digo_Usuario_Registra_MvtoEquipo");
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioRegistraMvto_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 330, 210, -1));

        selectMvtoEquipo_CausalInactividad.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_CausalInactividad.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_CausalInactividad.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_CausalInactividad.setText("Causal_Inactividad");
        selectMvtoEquipo_CausalInactividad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_CausalInactividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 60, 200, -1));

        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setText("C??digo_Usuario_Autoriza_Recobro");
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioAutorizaRecobro_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 370, 210, -1));

        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setText("Nombre_Usuario_Autoriza_Recobro");
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioAutorizaRecobro_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 390, 220, -1));

        selectMvtoEquipo_autorizacionRecobro.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_autorizacionRecobro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_autorizacionRecobro.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_autorizacionRecobro.setText("Autorizaci??n_Recobro");
        selectMvtoEquipo_autorizacionRecobro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_autorizacionRecobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 450, 220, -1));

        selectMvtoEquipo_MotivoParada.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_MotivoParada.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_MotivoParada.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_MotivoParada.setText("Motivo_Parada");
        selectMvtoEquipo_MotivoParada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_MotivoParada, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 120, 200, -1));

        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setText("Observaci??n_Autorizaci??n_Recobro");
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioAutorizaRecobro_observacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 470, 220, -1));

        selectMvtoEquipo_Inactividad.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_Inactividad.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_Inactividad.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_Inactividad.setText("Inactividad");
        selectMvtoEquipo_Inactividad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_Inactividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 490, 220, -1));

        selectMvtoEquipo_UsuarioInactividad_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioInactividad_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioInactividad_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioInactividad_codigo.setText("C??digo_Usuario_Inactividad");
        selectMvtoEquipo_UsuarioInactividad_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_UsuarioInactividad_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_UsuarioInactividad_codigoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioInactividad_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 80, 190, -1));

        selectMvtoEquipo_DesdeCarbon.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_DesdeCarbon.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_DesdeCarbon.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_DesdeCarbon.setText("Desde_Carb??n");
        selectMvtoEquipo_DesdeCarbon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_DesdeCarbon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 180, 200, -1));

        selectMvtoEquipo_UsuarioInactividad_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioInactividad_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioInactividad_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioInactividad_nombre.setText("Nombre_Usuario_Inactividad");
        selectMvtoEquipo_UsuarioInactividad_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioInactividad_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 100, 200, -1));

        selectMvtoEquipo_centroCostoMayor.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCostoMayor.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCostoMayor.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCostoMayor.setText("CentroCosto Mayor");
        selectMvtoEquipo_centroCostoMayor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroCostoMayor, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 540, 200, -1));

        selectMvtoEquipo_observacion.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_observacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_observacion.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_observacion.setText("Observaci??n");
        selectMvtoEquipo_observacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_observacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 140, 200, -1));

        selectMvtoEquipo_estado.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_estado.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_estado.setText("Estado");
        selectMvtoEquipo_estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 160, 200, -1));

        selectAsignacion_equipoDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoDescripcion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoDescripcion.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoDescripcion.setText("Equipo Descripci??n");
        selectAsignacion_equipoDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectAsignacion_equipoDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsignacion_equipoDescripcionActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 250, 190, -1));

        selectAsignacion_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_codigo.setText("C??digo");
        selectAsignacion_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 230, 190, -1));

        selectMvtoEquipo_centroCosto.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCosto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCosto.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCosto.setText("Centro Costo");
        selectMvtoEquipo_centroCosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 520, 190, -1));

        selectAsignacion_fechaRegistro.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_fechaRegistro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_fechaRegistro.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_fechaRegistro.setText("Fecha_Registro");
        selectAsignacion_fechaRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_fechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 250, 190, -1));

        selectMvtoEquipo_articuloCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_articuloCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_articuloCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_articuloCodigo.setText("Articulo c??digo");
        selectMvtoEquipo_articuloCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_articuloCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_articuloCodigoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_articuloCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 130, 200, -1));

        selectMvtoEquipo_articuloDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_articuloDescripcion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_articuloDescripcion.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_articuloDescripcion.setText("Articulo Descripci??n");
        selectMvtoEquipo_articuloDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_articuloDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_articuloDescripcionActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_articuloDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 150, 200, -1));

        selectMvtoEquipo_motonaveCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_motonaveCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_motonaveCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_motonaveCodigo.setText("Motonave C??digo");
        selectMvtoEquipo_motonaveCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_motonaveCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_motonaveCodigoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_motonaveCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 170, 200, -1));

        selectMvtoEquipo_centroCostoAxiliarOrigen.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCostoAxiliarOrigen.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCostoAxiliarOrigen.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCostoAxiliarOrigen.setText("Auxiliar Centro Costo Origen");
        selectMvtoEquipo_centroCostoAxiliarOrigen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroCostoAxiliarOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 480, 200, -1));

        selectMvtoEquipo_centroCostoAuxiliarDestino.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCostoAuxiliarDestino.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCostoAuxiliarDestino.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCostoAuxiliarDestino.setText("Auxiliar Centro Costo Destino");
        selectMvtoEquipo_centroCostoAuxiliarDestino.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroCostoAuxiliarDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 500, 200, -1));

        selectMvtoEquipo_mes.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_mes.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_mes.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_mes.setText("Mes_Registro");
        selectMvtoEquipo_mes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 340, 190, -1));

        selectMvtoEquipo_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_codigo.setText("C??digo");
        selectMvtoEquipo_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 320, 190, -1));

        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setText("UsuarioQuienRegistr??Veh??culo_Correo");
        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioQuienRegistraVehiculoCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 460, 220, -1));

        selectDescargue_centroCostoAuxiliarDestino.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_centroCostoAuxiliarDestino.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_centroCostoAuxiliarDestino.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_centroCostoAuxiliarDestino.setText("Centro Costo Auxiliar_Destino");
        selectDescargue_centroCostoAuxiliarDestino.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_centroCostoAuxiliarDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 190, -1));

        selectDescargue_subcentroCosto.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_subcentroCosto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_subcentroCosto.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_subcentroCosto.setText("Subcentro Costo");
        selectDescargue_subcentroCosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_subcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 190, -1));

        selectDescargue_lavadoVehiculoObservacion.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_lavadoVehiculoObservacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_lavadoVehiculoObservacion.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_lavadoVehiculoObservacion.setText("Lavado Veh??culo Observaci??n");
        selectDescargue_lavadoVehiculoObservacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_lavadoVehiculoObservacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 190, -1));

        selectDescargue_lavadoVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_lavadoVehiculo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_lavadoVehiculo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_lavadoVehiculo.setText("Lavado Veh??culo");
        selectDescargue_lavadoVehiculo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_lavadoVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 190, -1));

        Motivo_No_LavadoVeh??culo.setBackground(new java.awt.Color(255, 255, 255));
        Motivo_No_LavadoVeh??culo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Motivo_No_LavadoVeh??culo.setForeground(new java.awt.Color(51, 51, 51));
        Motivo_No_LavadoVeh??culo.setText("Motivo_No_LavadoVeh??culo");
        Motivo_No_LavadoVeh??culo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Motivo_No_LavadoVeh??culo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 560, 190, -1));

        Valor_Recaudo_Equipo_LavadoVeh??culos.setBackground(new java.awt.Color(255, 255, 255));
        Valor_Recaudo_Equipo_LavadoVeh??culos.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Valor_Recaudo_Equipo_LavadoVeh??culos.setForeground(new java.awt.Color(51, 51, 51));
        Valor_Recaudo_Equipo_LavadoVeh??culos.setText("Valor_Recaudo_Equipo_LavadoVeh??culos");
        Valor_Recaudo_Equipo_LavadoVeh??culos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Valor_Recaudo_Equipo_LavadoVeh??culos, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 260, -1));

        Valor_Recaudo_Empresa_LavadoVeh??culos.setBackground(new java.awt.Color(255, 255, 255));
        Valor_Recaudo_Empresa_LavadoVeh??culos.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Valor_Recaudo_Empresa_LavadoVeh??culos.setForeground(new java.awt.Color(51, 51, 51));
        Valor_Recaudo_Empresa_LavadoVeh??culos.setText("Valor_Recaudo_Empresa_LavadoVeh??culos");
        Valor_Recaudo_Empresa_LavadoVeh??culos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Valor_Recaudo_Empresa_LavadoVeh??culos, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, 260, -1));

        Equipo_Quien_Realiza_LavadoVeh??culo_C??digo.setBackground(new java.awt.Color(255, 255, 255));
        Equipo_Quien_Realiza_LavadoVeh??culo_C??digo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Equipo_Quien_Realiza_LavadoVeh??culo_C??digo.setForeground(new java.awt.Color(51, 51, 51));
        Equipo_Quien_Realiza_LavadoVeh??culo_C??digo.setText("Equipo_Quien_Realiza_LavadoVeh??culo_C??digo");
        Equipo_Quien_Realiza_LavadoVeh??culo_C??digo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Equipo_Quien_Realiza_LavadoVeh??culo_C??digo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 260, -1));

        Costo_Lavado_Veh??culo.setBackground(new java.awt.Color(255, 255, 255));
        Costo_Lavado_Veh??culo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Costo_Lavado_Veh??culo.setForeground(new java.awt.Color(51, 51, 51));
        Costo_Lavado_Veh??culo.setText("Costo_Lavado_Veh??culo");
        Costo_Lavado_Veh??culo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Costo_Lavado_Veh??culo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, 260, -1));

        Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo.setBackground(new java.awt.Color(255, 255, 255));
        Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo.setForeground(new java.awt.Color(51, 51, 51));
        Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo.setText("Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo");
        Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 280, -1));

        Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n.setBackground(new java.awt.Color(255, 255, 255));
        Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n.setForeground(new java.awt.Color(51, 51, 51));
        Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n.setText("Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n");
        Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, 280, -1));

        selectDescargue_dia.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_dia.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_dia.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_dia.setText("D??a");
        selectDescargue_dia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_dia, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 190, -1));

        selectMvtoEquipo_UsuarioCierraMvto_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioCierraMvto_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioCierraMvto_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioCierraMvto_nombre.setText("Nombre_Usuario_Cierra_MvtoEquipo");
        selectMvtoEquipo_UsuarioCierraMvto_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioCierraMvto_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 430, 220, -1));

        selectMvtoEquipo_UsuarioCierraMvto_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioCierraMvto_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioCierraMvto_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioCierraMvto_codigo.setText("C??digo_Usuario_Cierra_MvtoEquipo");
        selectMvtoEquipo_UsuarioCierraMvto_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioCierraMvto_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 410, 210, -1));

        selectDescargue_usuarioQuienCierraVehiculoNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_usuarioQuienCierraVehiculoNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_usuarioQuienCierraVehiculoNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_usuarioQuienCierraVehiculoNombre.setText("UsuarioQuienCierraVeh??culo_Nombre");
        selectDescargue_usuarioQuienCierraVehiculoNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioQuienCierraVehiculoNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 90, 220, -1));

        selectDescargue_usuarioQuienCierraVehiculoCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_usuarioQuienCierraVehiculoCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_usuarioQuienCierraVehiculoCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_usuarioQuienCierraVehiculoCodigo.setText("UsuarioQuienCierraVeh??culo_C??digo");
        selectDescargue_usuarioQuienCierraVehiculoCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioQuienCierraVehiculoCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 210, -1));

        titulo12.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        titulo12.setText("PROGRAMACI??N (ASIGNACI??N)");
        titulo12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 200, 240, 20));

        titulo21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo21, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 50, 240, 550));

        titulo17.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        titulo17.setText("DATOS DEL EQUIPO");
        titulo17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo17, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, 240, 20));

        titulo20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo20, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 70, 240, 0));

        titulo23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo23.setText("Mvto Equipo");
        titulo23.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo23, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 300, 240, 20));

        titulo19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 50, 240, 550));

        titulo24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo24.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo24, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, 240, 550));

        titulo26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo26.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 290, 530));

        titulo27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo27.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo27, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 310, 530));

        add(InternalFrameSelectorCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1560, 760));

        titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo.setForeground(new java.awt.Color(0, 102, 102));
        titulo.setText("MODIFICACI??N DE REGISTROS DEL MODULO DE CARB??N");
        add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 610, 30));

        tabla = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla.setComponentPopupMenu(Inactivar);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 1300, 520));

        consultar.setBackground(new java.awt.Color(255, 255, 255));
        consultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/consultar.png"))); // NOI18N
        consultar.setText("CONSULTAR");
        consultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                consultarMouseExited(evt);
            }
        });
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        add(consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 70, 210, 35));

        fechaInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicioMouseEntered(evt);
            }
        });
        add(fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 170, 30));

        minutoInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoInicioActionPerformed(evt);
            }
        });
        add(minutoInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 50, 30));

        horaInicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                horaInicioItemStateChanged(evt);
            }
        });
        add(horaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 50, 30));

        limpiar_usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/clean.png"))); // NOI18N
        limpiar_usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                limpiar_usuarioMouseClicked(evt);
            }
        });
        add(limpiar_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 120, 40, 30));

        limpiar_placa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/clean.png"))); // NOI18N
        limpiar_placa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                limpiar_placaMouseClicked(evt);
            }
        });
        add(limpiar_placa, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, 40, 30));

        limpiar_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/clean.png"))); // NOI18N
        limpiar_cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                limpiar_clienteMouseClicked(evt);
            }
        });
        add(limpiar_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, 40, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Hora");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("FECHA/HORA FIN");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, -1, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 65)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("|");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 30, 60));

        fechaFin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFinMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFinMouseEntered(evt);
            }
        });
        add(fechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 170, 30));

        minutoFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoFinActionPerformed(evt);
            }
        });
        add(minutoFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 70, 50, 30));

        horaFin.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                horaFinItemStateChanged(evt);
            }
        });
        horaFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horaFinActionPerformed(evt);
            }
        });
        add(horaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, 50, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Hora");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, -1, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("FECHA/HORA INICIO");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, 30));

        alerta_fechaFinal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_fechaFinal.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_fechaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 480, 20));

        alerta_fechaInicio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_fechaInicio.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 430, 20));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1300, 10));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCampo.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 70, 50, 40));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Campos");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 50, 40, 30));

        icon_exportar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_exportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/ExportarExcel.png"))); // NOI18N
        icon_exportar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_exportarMouseClicked(evt);
            }
        });
        add(icon_exportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 70, 50, 40));

        label_exportar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        label_exportar.setForeground(new java.awt.Color(51, 51, 51));
        label_exportar.setText("Exportar");
        add(label_exportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 50, 50, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel24.setText(":");
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 70, 20, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel25.setText(":");
        add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 20, 30));

        horarioTiempoIFinalMvtoCarbon_Procesar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoIFinalMvtoCarbon_Procesar.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoIFinalMvtoCarbon_Procesar.setText("AM");
        add(horarioTiempoIFinalMvtoCarbon_Procesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 70, 50, 30));

        horarioTiempoInicioMvtoCarbon_Procesar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoInicioMvtoCarbon_Procesar.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoInicioMvtoCarbon_Procesar.setText("AM");
        add(horarioTiempoInicioMvtoCarbon_Procesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, 50, 30));

        paginationPanel.setBackground(new java.awt.Color(255, 255, 255));
        add(paginationPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 740, 1300, 80));

        add(pageJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 10, 60, 40));

        checkPesoCero.setBackground(new java.awt.Color(255, 255, 255));
        checkPesoCero.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        checkPesoCero.setForeground(new java.awt.Color(51, 51, 51));
        checkPesoCero.setText("PESO NETO CERO");
        checkPesoCero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        checkPesoCero.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkPesoCeroItemStateChanged(evt);
            }
        });
        checkPesoCero.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                checkPesoCeroCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        checkPesoCero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPesoCeroActionPerformed(evt);
            }
        });
        add(checkPesoCero, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, 200, 30));

        checkUsuarioQuienRegistra.setBackground(new java.awt.Color(255, 255, 255));
        checkUsuarioQuienRegistra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        checkUsuarioQuienRegistra.setForeground(new java.awt.Color(51, 51, 51));
        checkUsuarioQuienRegistra.setText("USUARIO QUIEN REGISTRA:");
        checkUsuarioQuienRegistra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        checkUsuarioQuienRegistra.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkUsuarioQuienRegistraItemStateChanged(evt);
            }
        });
        checkUsuarioQuienRegistra.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                checkUsuarioQuienRegistraCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        checkUsuarioQuienRegistra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkUsuarioQuienRegistraActionPerformed(evt);
            }
        });
        add(checkUsuarioQuienRegistra, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 200, 30));

        icon_buscarUsuarioQuienRegistra.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        icon_buscarUsuarioQuienRegistra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        icon_buscarUsuarioQuienRegistra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_buscarUsuarioQuienRegistraMouseClicked(evt);
            }
        });
        add(icon_buscarUsuarioQuienRegistra, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 120, 30, 30));

        label_usuarioQuienRegistra.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_usuarioQuienRegistra.setForeground(new java.awt.Color(51, 51, 51));
        label_usuarioQuienRegistra.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(label_usuarioQuienRegistra, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 120, 320, 30));

        checkPlaca.setBackground(new java.awt.Color(255, 255, 255));
        checkPlaca.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        checkPlaca.setForeground(new java.awt.Color(51, 51, 51));
        checkPlaca.setText("PLACA:");
        checkPlaca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        checkPlaca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkPlacaItemStateChanged(evt);
            }
        });
        checkPlaca.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                checkPlacaCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        checkPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPlacaActionPerformed(evt);
            }
        });
        add(checkPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 70, 30));

        icon_buscarCliente.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        icon_buscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        icon_buscarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_buscarClienteMouseClicked(evt);
            }
        });
        add(icon_buscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 30, 30));

        label_cliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_cliente.setForeground(new java.awt.Color(51, 51, 51));
        label_cliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(label_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 300, 30));

        checkCliente.setBackground(new java.awt.Color(255, 255, 255));
        checkCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        checkCliente.setForeground(new java.awt.Color(51, 51, 51));
        checkCliente.setText("CLIENTE");
        checkCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        checkCliente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkClienteItemStateChanged(evt);
            }
        });
        checkCliente.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                checkClienteCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        checkCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkClienteActionPerformed(evt);
            }
        });
        add(checkCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 80, 30));

        label_placa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                label_placaActionPerformed(evt);
            }
        });
        add(label_placa, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 300, 30));
        add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 1300, 10));

        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1300, 160));
        add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 1300, 10));
    }// </editor-fold>//GEN-END:initComponents

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        if(checkCliente.isSelected() && cliente==null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente para consultar", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }else{
            if(checkUsuarioQuienRegistra.isSelected() && usuario== null){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario para consultar", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            }else{
                paginationPanel.removeAll();
                validarSeleccionCampos();
                generarListadoMvtoCarbon();
                resizeColumnWidth(tabla);
            }
        }
        
        
    }//GEN-LAST:event_consultarActionPerformed

    private void fechaInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicioMouseClicked
        alerta_fechaInicio.setText("");
    }//GEN-LAST:event_fechaInicioMouseClicked

    private void fechaInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicioMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaInicioMouseEntered

    private void minutoInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minutoInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minutoInicioActionPerformed

    private void fechaFinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFinMouseClicked
       alerta_fechaFinal.setText("");
    }//GEN-LAST:event_fechaFinMouseClicked

    private void fechaFinMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFinMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFinMouseEntered

    private void minutoFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minutoFinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minutoFinActionPerformed

    private void consultarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultarMouseExited
        alerta_fechaInicio.setText("");
        alerta_fechaFinal.setText("");
    }//GEN-LAST:event_consultarMouseExited

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        InternalFrameSelectorCampos.show(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void icon_exportarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_exportarMouseClicked
         if(listado != null){
           //ArrayList<String> colmnas={"Hola", "Hola", "Hola"});
            JFileChooser selecArchivo= new JFileChooser();
            File archivo;
            //selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
            //selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
            if(selecArchivo.showDialog(null, "Exportar") ==JFileChooser.APPROVE_OPTION){
                archivo=new File(selecArchivo.getSelectedFile()+".xlsx");
                //archivo=selecArchivo.getSelectedFile();
                if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
                    //JOptionPane.showMessageDialog(null, Exportar(archivo));
                    Workbook wb;
                    String respuesta="No se realiz?? con exito la exportacion";
                    listado.size();
                   
                    //int numFila=tabla.getRowCount(), numColumna=tabla.getColumnCount();
                    int numFila=listado.size();
                    int numColumna=encabezadoTabla.size();
                    if(archivo.getName().endsWith("xls")){
                        wb = new HSSFWorkbook();
                    }else{
                        wb= new XSSFWorkbook();
                    }
                    Sheet hoja= wb.createSheet("Matriz_Carbon");
                    int costoTotalApuntador=300000;
                    try{
                        for(int i= -1; i < numFila; i++ ){
                            Row fila= hoja.createRow(i+1);
                            for(int j=0; j< numColumna; j++){
                                Cell celda= fila.createCell(j);
                                if(i==-1){
                                    //celda.setCellValue(String.valueOf(tabla.getColumnName(j)));
                                    celda.setCellValue(encabezadoTabla.get(j));
                                    //String nameColumn=String.valueOf(tabla.getColumnName(j));
                                        if(encabezadoTabla.get(j).equals("Costo_Total")){
                                            costoTotalApuntador=j;
                                        }
                                }else{
                                    String data="";
                                    switch(encabezadoTabla.get(j)){
                                        case "C??digo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getCodigo();
                                            break;
                                        }
                                        case "Centro_Operacion":{
                                            data=""+  listado.get(i).getMvtoCarbon().getCentroOperacion().getDescripcion();
                                            break;
                                        }
                                        case "Subcentro_Costo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                                            break;
                                        }
                                        case "Centro_Costo_Auxiliar_Origen":{
                                            data=""+  listado.get(i).getMvtoCarbon().getCentroCostoAuxiliar().getDescripcion();
                                            break;
                                        }
                                        case "Centro_Costo_Auxiliar_Destino":{
                                            data=""+  listado.get(i).getMvtoCarbon().getCentroCostoAuxiliarDestino().getDescripcion();
                                            break;
                                        }
                                        case "Centro_Costo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getCentroCosto().getDescripci??n();
                                            break;
                                        }
                                        case "Centro_Costo_Mayor":{
                                            data=""+  listado.get(i).getMvtoCarbon().getCentroCostoMayor().getDescripcion();
                                            break;
                                        }
                                        case "Labor_Realizada":{
                                            data=""+  listado.get(i).getMvtoCarbon().getLaborRealizada().getDescripcion();
                                            break;
                                        }
                                        case "Art??culo_C??digo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getArticulo().getCodigo();
                                            break;
                                        }
                                        case "Art??culo_Nombre":{
                                            data=""+  listado.get(i).getMvtoCarbon().getArticulo().getDescripcion();
                                            break;
                                        }
                                        case "Art??culo_Tipo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getArticulo().getTipoArticulo().getDescripcion();
                                            break;
                                        }
                                        case "Art??culo_C??digo_ERP":{
                                            data=""+  listado.get(i).getMvtoCarbon().getArticulo().getTipoArticulo().getCodigoERP();
                                            break;
                                        }
                                        case "Art??culo_Unidad_Negocio":{
                                            data=""+  listado.get(i).getMvtoCarbon().getArticulo().getTipoArticulo().getUnidadNegocio();
                                            break;
                                        }
                                        case "Cliente_C??digo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getCliente().getCodigo();
                                            break;
                                        }
                                        case "Cliente_Nombre":{
                                            data=""+  listado.get(i).getMvtoCarbon().getCliente().getDescripcion();
                                            break;
                                        }
                                        case "Transportadora_C??digo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getTransportadora().getCodigo();
                                            break;
                                        }
                                        case "Transportadora_Nit":{
                                            data=""+  listado.get(i).getMvtoCarbon().getTransportadora().getNit();
                                            break;
                                        }
                                        case "Transportadora_Nombre":{
                                            data=""+  listado.get(i).getMvtoCarbon().getTransportadora().getDescripcion();
                                            break;
                                        }
                                        case "Mes":{
                                            data=""+  getMes(listado.get(i).getMvtoCarbon().getMes());
                                            break;
                                        }
                                        case "Fecha_Registro":{
                                            data=""+  listado.get(i).getMvtoCarbon().getFechaRegistro();
                                            break;
                                        }
                                        case "N??mero_Orden":{
                                            data=""+  listado.get(i).getMvtoCarbon().getNumero_orden();
                                            break;
                                        }
                                        case "Deposito":{
                                            data=""+  listado.get(i).getMvtoCarbon().getDeposito();
                                            break;
                                        }
                                        case "Consecutivo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getConsecutivo();
                                            break;
                                        }
                                        case "Placa":{
                                            data=""+  listado.get(i).getMvtoCarbon().getPlaca();
                                            break;
                                        }
                                        case "PesoVacio":{
                                            data=""+  listado.get(i).getMvtoCarbon().getPesoVacio();
                                            break;
                                        }
                                        case "Peso_Lleno":{
                                            data=""+  listado.get(i).getMvtoCarbon().getPesoLleno();
                                            break;
                                        }
                                        case "Peso_Neto":{
                                            data=""+  listado.get(i).getMvtoCarbon().getPesoNeto();
                                            break;
                                        }
                                        case "Fecha_Entrada_Veh??culo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getFechaEntradaVehiculo();
                                            break;
                                        }
                                        case "Fecha_Salida_Veh??culo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getFecha_SalidaVehiculo();
                                            break;
                                        }
                                        case "Fecha_Inicio_Descargue":{
                                            data=""+  listado.get(i).getMvtoCarbon().getFechaInicioDescargue();
                                            break;
                                        }
                                        case "Fecha_Fin_Descargue":{
                                            data=""+  listado.get(i).getMvtoCarbon().getFechaFinDescargue();
                                            break;
                                        }
                                        case "Cantidad_Minutos":{
                                            data=""+  listado.get(i).getMvtoCarbon().getCantidadHorasDescargue();
                                            break;
                                        }
                                        case "Usuario_Quien_Registra_Veh??culo_C??digo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getUsuarioRegistroMovil().getCodigo();
                                            break;
                                        }
                                        case "Usuario_Quien_Registra_Veh??culo_Nombre":{
                                            data=""+  listado.get(i).getMvtoCarbon().getUsuarioRegistroMovil().getNombres()+" "+listado.get(i).getMvtoCarbon().getUsuarioRegistroMovil().getApellidos();
                                            break;
                                        }
                                        case "Usuario_Quien_Registra_Veh??culo_Correo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getUsuarioRegistroMovil().getCorreo();
                                            break;
                                        }
                                        case "Observaci??n":{
                                            data=""+  listado.get(i).getMvtoCarbon().getObservacion();
                                            break;
                                        }
                                        case "Estado":{
                                            data=""+  listado.get(i).getMvtoCarbon().getEstadoMvtoCarbon().getDescripcion();
                                            break;
                                        }
                                        case "Conexi??n_Peso_CCARGA":{
                                            data=""+  listado.get(i).getMvtoCarbon().getConexionPesoCcarga();
                                            break;
                                        }
                                        case "Registro_Manual":{
                                            data=""+  listado.get(i).getMvtoCarbon().getRegistroManual();
                                            break;
                                        }
                                        case "Usuario_Quien_RegistraManual_C??digo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getUsuarioRegistraManual().getCodigo();
                                            break;
                                        }
                                        case "Usuario_Quien_RegistraManual_Nombre":{
                                            data=""+  listado.get(i).getMvtoCarbon().getUsuarioRegistraManual().getNombres()+" "+listado.get(i).getMvtoCarbon().getUsuarioRegistraManual().getApellidos();
                                            break;
                                        }
                                        case "Usuario_Quien_RegistraManual_Correo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getUsuarioRegistraManual().getCorreo();
                                            break;
                                        }
                                        
                                        case "Usuario_Quien_Cierra_Veh??culo_C??digo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getUsuarioCierraRegistro().getCodigo();
                                            break;
                                        }
                                        case "Usuario_Quien_Cierra_Veh??culo_Nombre":{
                                            data=""+  listado.get(i).getMvtoCarbon().getUsuarioCierraRegistro().getNombres()+" "+listado.get(i).getMvtoCarbon().getUsuarioCierraRegistro().getApellidos();
                                            break;
                                        }
                                        
                                        case "MvtoEquipo_C??digo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCodigo();
                                            break;
                                        }
                                        case "MvtoEquipo_MES":{
                                            data=""+  getMes(listado.get(i).getMvtoEquipo().getMes());
                                            break;
                                        }
                                        case "MvtoEquipo_Fecha_Registro":{
                                            data=""+  listado.get(i).getMvtoEquipo().getFechaRegistro();
                                            break;
                                        }
                                        case "MvtoEquipo_ProveedorEquipo_NIT":{
                                            data=""+  listado.get(i).getMvtoEquipo().getProveedorEquipo().getNit();
                                            break;
                                        }
                                        case "MvtoEquipo_ProveedorEquipo_Nombre":{
                                            data=""+  listado.get(i).getMvtoEquipo().getProveedorEquipo().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_N??mero_Orden":{
                                            data=""+  listado.get(i).getMvtoEquipo().getNumeroOrden();
                                            break;
                                        }
                                        case "MvtoEquipo_Centro_Operaci??n":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCentroOperacion().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Subcentro_Costo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Centro_Costo_Auxiliar_Origen":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCentroCostoAuxiliar().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Centro_Costo_Auxiliar_Destino":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCentroCostoAuxiliarDestino().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Centro_Costo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCentroCosto().getDescripci??n();
                                            break;
                                        }
                                        case "MvtoEquipo_Centro_Costo_Mayor":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCentroCostoMayor().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Labor_Realizada":{
                                            data=""+  listado.get(i).getMvtoEquipo().getLaborRealizada().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Cliente_C??digo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCliente().getCodigo();
                                            break;
                                        }
                                        case "MvtoEquipo_Cliente_Nombre":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCliente().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Art??culo_C??digo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getArticulo().getCodigo();
                                            break;
                                        }
                                        case "MvtoEquipo_Art??culo_Nombre":{
                                            data=""+  listado.get(i).getMvtoEquipo().getArticulo().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Motonave_C??digo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getMotonave().getCodigo();
                                            break;
                                        }
                                        case "MvtoEquipo_Motonave_Descripci??n":{
                                            data=""+  listado.get(i).getMvtoEquipo().getMotonave().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_FechaInicio_Actividad":{
                                            data=""+  listado.get(i).getMvtoEquipo().getFechaHoraInicio();
                                            break;
                                        }
                                        case "MvtoEquipo_FechaFinalizaci??n_Actividad":{
                                            data=""+  listado.get(i).getMvtoEquipo().getFechaHoraFin();
                                            break;
                                        }
                                        case "MvtoEquipo_Duraci??n_Actividad":{
                                            data=""+  listado.get(i).getMvtoEquipo().getTotalMinutos();
                                            break;
                                        }
                                        case "MvtoEquipo_Valor_Hora":{
                                            data=""+  listado.get(i).getMvtoEquipo().getValorHora();
                                            break;
                                        }
                                        case "MvtoEquipo_Costo_Total":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCostoTotal();
                                            break;
                                        }
                                        case "MvtoEquipo_Recobro":{
                                            data=""+  listado.get(i).getMvtoEquipo().getRecobro().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_UsuarioQuienRegistra_C??digo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getUsuarioQuieRegistra().getCodigo();
                                            break;
                                        }
                                        case "MvtoEquipo_UsuarioQuienRegistra_Nombre":{
                                            data=""+  listado.get(i).getMvtoEquipo().getUsuarioQuieRegistra().getNombres();
                                            break;
                                        }
                                        case "MvtoEquipo_UsuarioQuienAutoriza_C??digo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getUsuarioAutorizaRecobro().getCodigo();
                                            break;
                                        }
                                        case "MvtoEquipo_UsuarioQuienAutoriza_Nombre":{
                                            data=""+  listado.get(i).getMvtoEquipo().getUsuarioAutorizaRecobro().getNombres();
                                            break;
                                        }
                                        
                                        case "MvtoEquipo_UsuarioCierraMvto_codigo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getUsuarioQuienCierra().getCodigo();
                                            break;
                                        }
                                        case "MvtoEquipo_UsuarioCierraMvto_nombre":{
                                            data=""+  listado.get(i).getMvtoEquipo().getUsuarioQuienCierra().getNombres();
                                            break;
                                        }
                                        
                                        case "MvtoEquipo_Autorizaci??n_Recobro":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAutorizacionRecobro().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Observaci??n_Autorizaci??n_Recobro":{
                                            data=""+  listado.get(i).getMvtoEquipo().getObservacionAutorizacion();
                                            break;
                                        }
                                        case "MvtoEquipo_Inactividad":{
                                            data=""+  listado.get(i).getMvtoEquipo().getInactividad();
                                            break;
                                        }
                                        case "MvtoEquipo_Causal_Inactividad":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCausaInactividad().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_UsuarioQuienInactiva_C??digo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getUsuarioInactividad().getCodigo();
                                            break;
                                        }
                                        case "MvtoEquipo_UsuarioQuienInactiva_Nombre":{
                                            data=""+  listado.get(i).getMvtoEquipo().getUsuarioInactividad().getNombres();
                                            break;
                                        }
                                        case "MvtoEquipo_Motivo_Parada":{
                                            data=""+  listado.get(i).getMvtoEquipo().getMotivoParada().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_MvtoEquipo_Observaci??n":{
                                            data=""+  listado.get(i).getMvtoEquipo().getObservacionMvtoEquipo();
                                            break;
                                        }
                                        case "MvtoEquipo_Estado":{
                                            data=""+  listado.get(i).getMvtoEquipo().getEstado();
                                            break;
                                        }
                                        case "MvtoEquipo_Desde_Carb??n":{
                                            data=""+  listado.get(i).getMvtoEquipo().getDesdeCarbon();
                                            break;
                                        }
                                        case "MvtoEquipo_Equipo_C??digo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getEquipo().getCodigo();
                                            break;
                                        }
                                        case "MvtoEquipo_Equipo_Tipo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Equipo_Marca":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getEquipo().getMarca();
                                            break;
                                        }
                                        case "MvtoEquipo_Equipo_Modelo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getEquipo().getModelo();
                                            break;
                                        }
                                        case "MvtoEquipo_Equipo_Descripci??n":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getEquipo().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Equipo_Pertenencia":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getPertenencia().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Asignaci??n_C??digo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getCodigo();
                                            break;
                                        }
                                        case "MvtoEquipo_Asignaci??n_FechaRegistro":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getFechaRegistro();
                                            break;
                                        }
                                        case "MvtoEquipo_Asignaci??n_FechaInicioActividad":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getFechaHoraInicio();
                                            break;
                                        }
                                        case "MvtoEquipo_Asignaci??n_FechaFinalizaci??nActividad":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getFechaHoraFin();
                                            break;
                                        }
                                        case "MvtoEquipo_Asignaci??n_CantidadMinutosProgramados":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getCantidadMinutosProgramados();
                                            break;
                                        } 
                                    }                              
                                    try{
                                        String[] valor=String.valueOf(data).split("-");
                                        if(valor.length==3){
                                            String[] valor2=valor[2].split(":");
                                           if(valor2.length >= 3){
                                               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                               Date fechaM = dateFormat.parse(String.valueOf(data));
                                               CellStyle cellStyle = wb.createCellStyle();
                                               CreationHelper createHelper = wb.getCreationHelper();
                                               cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yy h:mm:ss"));
                                               celda.setCellValue(fechaM);
                                               celda.setCellStyle(cellStyle);
                                           }else{
                                               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                               Date fechaM = dateFormat.parse(String.valueOf(data));
                                               CellStyle cellStyle = wb.createCellStyle();
                                               CreationHelper createHelper = wb.getCreationHelper();
                                               cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yy"));
                                               //cell = row.createCell(1);
                                               celda.setCellValue(fechaM);
                                               celda.setCellStyle(cellStyle);
                                           }
                                        }else{
                                            try{
                                                celda.setCellValue(Integer.parseInt(data));
                                            }catch(Exception e){
                                                try{
                                                    if(costoTotalApuntador == j){
                                                        celda.setCellValue(Double.parseDouble(String.valueOf(data).replace(",", ".")));
                                                    }else{
                                                        celda.setCellValue(String.valueOf(data));
                                                    }
                                                }catch(Exception ex){
                                                    celda.setCellValue(String.valueOf(data));
                                                }
                                            }
                                        }   
                                    }   
                                    catch(Exception e){
                                        //celda.setCellValue(String.valueOf(tabla.getValueAt(i, j)));
                                        celda.setCellValue(String.valueOf(data));
                                    }
                                }
                            }
                        }
                        for(int j=0; j<=numColumna; j++){
                            hoja.autoSizeColumn(j,true);
                        }
                        wb.write(new FileOutputStream(archivo));
                        respuesta= "Exportacion exitosa";
                    }catch(Exception e){}
                    JOptionPane.showMessageDialog(null, respuesta);
                }else{
                    JOptionPane.showMessageDialog(null,"Elija un formato valido");
                }
            }
       }  
    }//GEN-LAST:event_icon_exportarMouseClicked

    private void horaInicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_horaInicioItemStateChanged
        if(horaInicio.getSelectedIndex()<= 11){
            horarioTiempoInicioMvtoCarbon_Procesar.setText("AM");
        }else{
            horarioTiempoInicioMvtoCarbon_Procesar.setText("PM");
        }
    }//GEN-LAST:event_horaInicioItemStateChanged

    private void horaFinItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_horaFinItemStateChanged
        if(horaFin.getSelectedIndex()<= 11){
            horarioTiempoIFinalMvtoCarbon_Procesar.setText("AM");
        }else{
            horarioTiempoIFinalMvtoCarbon_Procesar.setText("PM");
        }
    }//GEN-LAST:event_horaFinItemStateChanged

    private void horaFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horaFinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_horaFinActionPerformed

    private void EnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnableActionPerformed
        // TODO lo del clik derechoo
        int fila1;
        try{
            fila1=tabla.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ningun Vehiculo");
            }
            else{
                int posicion = paginadorDeTabla.getPosicionador();
                if(posicion != -1){
                    fila1 = fila1 +posicion;
                }
                //Limpiamos campos de la Interfaz
                MvtCarbon_codigo.setText("");
                MvtCarbon_placa.setText("");
                MvtCarbon_FechaRegistro.setText("");
                MvtCarbon_fechaEntradaVehiculo.setText("");
                MvtCarbon_fechaSalidaVehiculo.setText("");
                MvtCarbon_fechaInicioDescargue.setText("");
                MvtCarbon_fechaFinalDescargue.setText("");
                MvtCarbon_centroOperacion.setText("");
                MvtCarbon_auxiliarCentroCostoOrigen.setText("");
                MvtCarbon_auxiliarCentroCostoDestino.setText("");
                MvtCarbon_laborRealizada.setText("");
                mvtEquipo_estado.setText("");
                MvtCarbon_observacion.setText("");
                MvtCarbon_cliente.setText("");
                MvtCarbon_transportadora.setText("");
                MvtCarbon_deposito.setText("");
                MvtCarbon_consecutivo.setText("");
                MvtCarbon_pesoVacio.setText("");
                MvtCarbon_pesoLleno.setText("");
                MvtCarbon_pesoNeto.setText("");
                MvtCarbon_subCentroOperacion.setText("");
                MvtCarbon_articulo.setText("");
                MvtCarbon_lavadoVeh??culo.setText("");
                MvtCarbon_conexionCcarga.setText(""); 
                MvtCarbon_usuarioQuienInicia.setText("");
                MvtCarbon_usuarioQuienCierra.setText("");      
                MvtCarbon_motivoNoLavadoVehiculo.setText("");
                MvtCarbon_observaci??nLavadoVeh??culo.setText("");
                MvtCarbon_equipoLavadoC??digo.setText("");
                MvtCarbon_equipoLavadoDescripci??n.setText("");
                MvtCarbon_costoLavadoVeh??culo.setText("");
                MvtCarbon_valorRecaudoEmpresa.setText("");
                MvtCarbon_valorRecaudoEquipo.setText("");
                
                MvtEquipo_codigo.setText("");
                mvtEquipo_Equipo_producto.setText("");
                mvtEquipo_Equipo_modelo.setText("");
                mvtEquipo_Equipo_descripci??n.setText("");
                mvtEquipo_Equipo_pertenencia.setText("");
                MvtEquipo_tipo.setText("");
                mvtEquipo_Equipo_marca.setText("");
                mvtEquipo_Equipo_serial.setText("");
                mvtEquipo_Equipo_proveedor.setText("");
                mvtEquipo_laborRealizada.setText("");
                mvtEquipo_FechaInicioActividad.setText("");
                mvtEquipo_FechaFinalActividad.setText("");
                mvtEquipo_Minutos.setText("");
                mvtEquipo_CantidadHoras.setText("");
                mvtEquipo_ActividadFinalizada.setText("");
                mvtEquipo_MotivoFinalizaci??n.setText("");
                MvtEquipo_usuarioQuienInicia.setText("");
                MvtEquipo_usuarioQuienCierra.setText("");
              
                select_MvtoEquipo_laborRealizada.setText("");
                mvtoCarbon=null;
                
                InternaFrame_VisualizarMvtoCarbon.show(true);
                ///DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                mvtoCarbon= listado.get(fila1).getMvtoCarbon();
                
                if(mvtoCarbon != null){
                    check_MvtoCarbon_InicioActividad.setSelected(false);
                    check_MvtoCarbon_FinalizacionActividad.setSelected(false);
                    select_MvtoEquipo_laborRealizada.setText("");
                    check_MvtoCarbon_UsuarioIniciaActividad.setSelected(false);
                    check_MvtoCarbon_UsuarioCierraActividad.setSelected(false);
                    textArea_MvtoCarbon_razonModificacion.setText("");

                    check_MvtoEquipo_InicioActividad.setSelected(false);
                    check_MvtoEquipo_FinalizacionActividad.setSelected(false);
                    textArea_MvtoEquipo_razonModificacion.setText("");
                        
                        
                    MvtCarbon_FechaRegistro.setText(mvtoCarbon.getFechaRegistro());
                    MvtCarbon_placa.setText(mvtoCarbon.getPlaca());
                    MvtCarbon_codigo.setText(mvtoCarbon.getCodigo());
                    MvtCarbon_centroOperacion.setText(mvtoCarbon.getCentroOperacion().getDescripcion());
                    MvtCarbon_subCentroOperacion.setText(mvtoCarbon.getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion());
                    MvtCarbon_auxiliarCentroCostoOrigen.setText(mvtoCarbon.getCentroCostoAuxiliar().getDescripcion());
                    MvtCarbon_auxiliarCentroCostoDestino.setText(mvtoCarbon.getCentroCostoAuxiliarDestino().getDescripcion());
                    MvtCarbon_lavadoVeh??culo.setText(mvtoCarbon.getLavadoVehiculo());
                    MvtCarbon_articulo.setText(mvtoCarbon.getArticulo().getDescripcion());
                    MvtCarbon_laborRealizada.setText(mvtoCarbon.getLaborRealizada().getDescripcion());
                    MvtCarbon_cliente.setText(mvtoCarbon.getCliente().getDescripcion());
                    MvtCarbon_transportadora.setText(mvtoCarbon.getTransportadora().getDescripcion());
                    MvtCarbon_deposito.setText(mvtoCarbon.getDeposito());
                    MvtCarbon_consecutivo.setText(mvtoCarbon.getConsecutivo());
                    MvtCarbon_pesoVacio.setText(mvtoCarbon.getPesoVacio());
                    MvtCarbon_pesoLleno.setText(mvtoCarbon.getPesoLleno());
                    MvtCarbon_pesoNeto.setText(mvtoCarbon.getPesoNeto());
                    MvtCarbon_fechaEntradaVehiculo.setText(mvtoCarbon.getFechaEntradaVehiculo());
                    MvtCarbon_fechaSalidaVehiculo.setText(mvtoCarbon.getFecha_SalidaVehiculo());
                    MvtCarbon_fechaInicioDescargue.setText(mvtoCarbon.getFechaInicioDescargue());
                    MvtCarbon_fechaFinalDescargue.setText(mvtoCarbon.getFechaFinDescargue());
                    MvtCarbon_conexionCcarga.setText(mvtoCarbon.getConexionPesoCcarga());
                    MvtCarbon_observacion.setText(mvtoCarbon.getObservacion());
                    
                    //MvtCarbon_equipoLavadoC??digo.setText(mvtoCarbon.getEquipoLavadoVehiculo().getCodigo());
                    
                    MvtCarbon_usuarioQuienInicia.setText(mvtoCarbon.getUsuarioRegistroMovil().getNombres()+" "+mvtoCarbon.getUsuarioRegistroMovil().getApellidos());
                    MvtCarbon_usuarioQuienCierra.setText(mvtoCarbon.getUsuarioCierraRegistro().getNombres()+" "+mvtoCarbon.getUsuarioCierraRegistro().getApellidos());
                    if(mvtoCarbon.getMotivoNoLavado().getDescripcion() != null){
                        MvtCarbon_motivoNoLavadoVehiculo.setText(mvtoCarbon.getMotivoNoLavado().getDescripcion());
                    }
                    
                    MvtCarbon_observaci??nLavadoVeh??culo.setText(mvtoCarbon.getLavadoVehiculo_Observacion());
                    MvtCarbon_equipoLavadoC??digo.setText(mvtoCarbon.getEquipoLavadoVehiculo().getCodigo());
                    MvtCarbon_equipoLavadoDescripci??n.setText(mvtoCarbon.getEquipoLavadoVehiculo().getDescripcion()+ " "+ mvtoCarbon.getEquipoLavadoVehiculo().getModelo());
                    MvtCarbon_costoLavadoVeh??culo.setText(mvtoCarbon.getCostoLavadoVehiculo());
                    MvtCarbon_valorRecaudoEmpresa.setText(mvtoCarbon.getValorRecaudoEmpresa_lavadoVehiculo());
                    MvtCarbon_valorRecaudoEquipo.setText(mvtoCarbon.getValorRecaudoEquipo_lavadoVehiculo());
                   

                    mvtEquipo_estado.setText(mvtoCarbon.getEstadoMvtoCarbon().getDescripcion());
                    
                    if(mvtoCarbon.getLavadoVehiculo() != null){
                        if(mvtoCarbon.getLavadoVehiculo().equals("SI")){
                            select_MvtoCarbon_lavadoVeh??culo.setSelectedIndex(1);
                            label_QuienRealizaLavadoVeh??culo.show(true);
                            select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.show(true);
                            
                            label_motivoNoLavado.show(false);
                            select_MvtoCarbon_motivoNoLavadoVehiculo.show(false);
                        }else{
                            if(mvtoCarbon.getLavadoVehiculo().equals("NO")){
                                select_MvtoCarbon_lavadoVeh??culo.setSelectedIndex(0);
                                
                                label_QuienRealizaLavadoVeh??culo.show(false);
                                select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.show(false);
                                
                                label_motivoNoLavado.show(true);
                                select_MvtoCarbon_motivoNoLavadoVehiculo.show(true);
                            }
                        }
                    }else{
                        select_MvtoCarbon_lavadoVeh??culo.setSelectedIndex(0);
                    }
                   
                    
                    listadoMvtoCarbon_ListadoEquipos= null;
                    listadoEquiposDescargue.removeAllItems();
                    listadoEquiposDescargue1.removeAllItems();
                    select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.removeAllItems();
                    
                    listadoMvtoCarbon_ListadoEquipos=new ControlDB_MvtoCarbon(tipoConexion).buscarMvtoCarbon_Codigo(mvtoCarbon.getCodigo());
                    if(listadoMvtoCarbon_ListadoEquipos !=null){
                        for(MvtoCarbon_ListadoEquipos objeto: listadoMvtoCarbon_ListadoEquipos){
                            listadoEquiposDescargue.addItem(objeto.getAsignacionEquipo().getEquipo().getCodigo()+ " "+ objeto.getAsignacionEquipo().getEquipo().getDescripcion()+" "+objeto.getAsignacionEquipo().getEquipo().getModelo());
                            listadoEquiposDescargue1.addItem(objeto.getAsignacionEquipo().getEquipo().getCodigo()+ " "+ objeto.getAsignacionEquipo().getEquipo().getDescripcion()+" "+objeto.getAsignacionEquipo().getEquipo().getModelo());
                            select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.addItem(objeto.getAsignacionEquipo().getEquipo().getCodigo()+ " "+ objeto.getAsignacionEquipo().getEquipo().getDescripcion()+" "+objeto.getAsignacionEquipo().getEquipo().getModelo());
                        }
                    }
                    //cargamos el equipo quien realiz?? el lavado del veh??culo
                    if(mvtoCarbon.getLavadoVehiculo() !=null){
                        if(mvtoCarbon.getEquipoLavadoVehiculo().getCodigo() != null){
                            if(mvtoCarbon.getLavadoVehiculo().equals("SI")){//
                                if(listadoMvtoCarbon_ListadoEquipos != null){
                                    int contador=0;
                                    for(MvtoCarbon_ListadoEquipos objeto: listadoMvtoCarbon_ListadoEquipos){
                                        if(mvtoCarbon.getEquipoLavadoVehiculo().getCodigo().equals(objeto.getAsignacionEquipo().getEquipo().getCodigo())){
                                            select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.setSelectedIndex(contador);
                                        }
                                        contador++;
                                    }
                                }
                            }
                        }
                    }
                    try{
                        //Cargamos el equipo seleccionado en Interfaz
                        mvtoCarbon_ListadoEquipos=null;
                        mvtoCarbon_ListadoEquipos= listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue.getSelectedIndex());
                        if(mvtoCarbon_ListadoEquipos !=null){
                            MvtEquipo_codigo.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getCodigo());
                            MvtEquipo_tipo.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion());
                            mvtEquipo_Equipo_producto.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getProducto());
                            mvtEquipo_Equipo_marca.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getMarca());
                            mvtEquipo_Equipo_modelo.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getModelo());
                            mvtEquipo_Equipo_serial.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getSerial());
                            mvtEquipo_Equipo_descripci??n.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getDescripcion());
                            mvtEquipo_Equipo_proveedor.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getProveedorEquipo().getDescripcion());
                            mvtEquipo_Equipo_pertenencia.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getPertenenciaEquipo().getDescripcion());

                            mvtEquipo_laborRealizada.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getLaborRealizada().getDescripcion());
                            mvtEquipo_FechaInicioActividad.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getFechaHoraInicio());
                            mvtEquipo_FechaFinalActividad.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getFechaHoraFin());
                            
                            MvtEquipo_usuarioQuienInicia.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getUsuarioQuieRegistra().getNombres()+" "+mvtoCarbon_ListadoEquipos.getMvtoEquipo().getUsuarioQuieRegistra().getApellidos());
                            MvtEquipo_usuarioQuienCierra.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getUsuarioQuienCierra().getNombres()+" "+mvtoCarbon_ListadoEquipos.getMvtoEquipo().getUsuarioQuienCierra().getApellidos());
                    
                   
                                    
                            if(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getTotalMinutos()!=null){
                                try{
                                    mvtEquipo_Minutos.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getTotalMinutos());
                                    double des=Double.parseDouble(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getTotalMinutos());
                                    double totalHoras = Double.parseDouble(""+(des/60));
                                    DecimalFormat formato2 = new DecimalFormat("0.00");
                                    mvtEquipo_CantidadHoras.setText(""+formato2.format(totalHoras));
                                }catch(Exception e){
                                    mvtEquipo_Minutos.setText("");
                                    mvtEquipo_CantidadHoras.setText("");
                                }
                            }else{
                                mvtEquipo_Minutos.setText("");
                                mvtEquipo_CantidadHoras.setText("");
                            }
                            if(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getMotivoParadaEstado() != null){
                                if(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getMotivoParadaEstado().equals("1")){
                                    mvtEquipo_ActividadFinalizada.setText("SI");
                                }else{
                                    mvtEquipo_ActividadFinalizada.setText("NO");
                                }
                            }else{
                                mvtEquipo_ActividadFinalizada.setText("NO");
                            }
                            mvtEquipo_MotivoFinalizaci??n.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getMotivoParada().getDescripcion());
                            mvtEquipo_estado.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getEstado());
                      }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    try{
            //Cargamos la fecha de inicio de la actividad
                        try{
                            String[] fechaA=mvtoCarbon.getFechaInicioDescargue().split(" ");
                            String[] horaB= fechaA[1].split(":");
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date fechaM=dateFormat.parse(fechaA[0]);
                            fechaInicialActividad_MvtoCarbon.setDate(fechaM);
                            select_MvtoCarbon_HoraInicial.setSelectedItem(horaB[0]);
                            select_MvtoCarbon_MinutosInicial.setSelectedItem(horaB[1]);
                        }catch(Exception e){
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, "No se pudo carga la fecha de Inicio del descargue","Error!!.", JOptionPane.ERROR_MESSAGE);
                        }

           //Cargamos la fecha de finalizaci??n de la actividad
                        try{
                            if(!(mvtoCarbon.getFechaFinDescargue() == null)){
                                //System.out.println(""+mvtoEquipo.getFechaHoraFin());
                                String[] fechaA= mvtoCarbon.getFechaFinDescargue().split(" ");
                                String[] horaB= fechaA[1].split(":");
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                Date fechaM=dateFormat.parse(fechaA[0]);
                                fechaFinActividad_MvtoCarbon.setDate(fechaM);
                                select_MvtoCarbon_HoraFinal.setSelectedItem(horaB[0]);
                                select_MvtoCarbon_MinutosFinal.setSelectedItem(horaB[1]);


                            }/*else{//No hay
                                listadoMotivoParada_mvtoEquipo=null;
                                mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();
                            }*/
                        }catch(Exception e){
                            e.printStackTrace();
                            System.out.println(""+mvtoCarbon.getFechaFinDescargue());
                            JOptionPane.showMessageDialog(null, "No se pudo carga la fecha de finalizaci??n de actividad","Error!!.", JOptionPane.ERROR_MESSAGE);
                        }

            //cargamos el Centro de Operaci??n seg??n la consulta
                        try{
                            int contador=0;
                            for(CentroOperacion centroOperacion: listadoCentroOperacion_mvtoCarbon){
                                if(centroOperacion.getDescripcion().equals(mvtoCarbon.getCentroOperacion().getDescripcion())){
                                      select_MvtoCarbon_CO.setSelectedIndex(contador);  
                                }
                                contador++;
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        cambioEstado("Subcentro_Costo");
                        //cargamos el subcentro de costo seg??n la consulta
                        try{
                            int contador=0;
                            for(CentroCostoSubCentro centroCostoSubCentro: listadoCentroCostoSubCentro_mvtoCarbon){
                                if(centroCostoSubCentro.getDescripcion().equals(mvtoCarbon.getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion())){
                                      select_MvtoCarbon_SubcentroCosto.setSelectedIndex(contador);  
                                }
                                contador++;
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
            //cargamos los auxiliares de Centro de costos seg??n la consulta
                        try{
                            int contador=0;
                            for(CentroCostoAuxiliar centroCostoAuxiliar: listadoCentroCostoAuxiliar_mvtoCarbon){
                                if(centroCostoAuxiliar.getDescripcion().equals(mvtoCarbon.getCentroCostoAuxiliar().getDescripcion())){
                                      select_MvtoCarbon_CCAuxiliar.setSelectedIndex(contador);  
                                }
                                contador++;
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        //mvto_listCarbon.getMvtoCarbon().getLaborRealizada().getDescripcion()
            //cargamos las labores realizadas seg??n la consulta
                        try{
                            int contador=0;
                            System.out.println("cargador fue ===========>"+mvtoCarbon.getLaborRealizada().getDescripcion());
                            for(LaborRealizada laborRealizada: listadoLaborRealizada_mvtoCarbon){
                                if(laborRealizada.getDescripcion().equals(mvtoCarbon.getLaborRealizada().getDescripcion())){
                                      select_MvtoCarbon_laborRealizada.setSelectedIndex(contador);  
                                       System.out.println("encontrado fue ===========>"+laborRealizada.getDescripcion());
                                }
                                contador++;
                                System.out.println("consultado fue ===========>"+laborRealizada.getDescripcion());
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }

            //cargamos los centros de costos auxiliares destino seg??n la consulta
                        try{
                            if(listadoCentroCostoAuxiliarDestino_mvtoCarbon !=null){
                                int contador=0;
                                for(CentroCostoAuxiliar centroCostoAuxiliar: listadoCentroCostoAuxiliarDestino_mvtoCarbon){
                                    if(centroCostoAuxiliar.getDescripcion().equals(mvtoCarbon.getCentroCostoAuxiliarDestino().getDescripcion())){
                                          select_MvtoCarbon_CCAuxiliarDestino.setSelectedIndex(contador);  
                                    }
                                    contador++;
                                
                                }
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        
                        
                        //Cargamos los Usuario de inicio y cierre
                        try{
                            if(listadoUsuarioInicioRegistro_mvtoCarbon !=null){
                                if(mvtoCarbon.getUsuarioRegistroMovil().getCodigo() != null){
                                    int contador=0;
                                    for(Usuario Objeto: listadoUsuarioInicioRegistro_mvtoCarbon){
                                        if(Objeto.getCodigo().equals(mvtoCarbon.getUsuarioRegistroMovil().getCodigo())){
                                              select_MvtoCarbon_UsuarioIniciaRegistro.setSelectedIndex(contador);  
                                        }
                                        contador++;
                                    }
                                }else{
                                
                                }
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        try{
                            if(listadoUsuarioCierraRegistro_mvtoCarbon !=null){
                                if(mvtoCarbon.getUsuarioCierraRegistro().getCodigo() != null){
                                    int contador=0;
                                    for(Usuario Objeto: listadoUsuarioCierraRegistro_mvtoCarbon){
                                        if(Objeto.getCodigo().equals(mvtoCarbon.getUsuarioCierraRegistro().getCodigo())){
                                              select_MvtoCarbon_UsuarioCierraRegistro.setSelectedIndex(contador);  
                                        }
                                        contador++;
                                    }
                                }else{
                                    select_MvtoCarbon_UsuarioCierraRegistro.setSelectedIndex(select_MvtoCarbon_UsuarioIniciaRegistro.getSelectedIndex());
                                }
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        
                        
                        
                        
                       
                        
                        
                        
                        /*//cargamos los recobro seg??n la consulta
                        try{
                            int contador=0;
                            for(Recobro recobro: listadoRecobros_mvtoEquipo){
                                if(recobro.getDescripcion().equals(mvtoCarbon.getRecobro().getDescripcion())){
                                      select_MvtoCarbon_recobro.setSelectedIndex(contador);  
                                }
                                contador++;
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }*/



                        /**borrar
                         mvtoCarbon_ListadoEquipos=null;
                        mvtoCarbon_ListadoEquipos= listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue.getSelectedIndex());

                         **/
                        
                        
                         //Cargamos los Usuario de inicio y cierre en MvtoEquipo
                        try{
                            if(listadoUsuarioInicioRegistro_mvtoEquipo !=null){
                                if(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getUsuarioQuieRegistra().getCodigo() != null){
                                    int contador=0;
                                    for(Usuario Objeto: listadoUsuarioInicioRegistro_mvtoEquipo){
                                        if(Objeto.getCodigo().equals(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getUsuarioQuieRegistra().getCodigo())){
                                              select_MvtoEquipo_UsuarioIniciaRegistro.setSelectedIndex(contador);  
                                        }
                                        contador++;
                                    }
                                }else{
                                
                                }
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        try{
                            if(listadoUsuarioCierraRegistro_mvtoEquipo !=null){
                                if(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getUsuarioQuienCierra().getCodigo() != null){
                                    int contador=0;
                                    for(Usuario Objeto: listadoUsuarioCierraRegistro_mvtoEquipo){
                                        if(Objeto.getCodigo().equals(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getUsuarioQuienCierra().getCodigo())){
                                              select_MvtoEquipo_UsuarioCierraRegistro.setSelectedIndex(contador);  
                                        }
                                        contador++;
                                    }
                                }else{
                                    select_MvtoEquipo_UsuarioCierraRegistro.setSelectedIndex(select_MvtoEquipo_UsuarioIniciaRegistro.getSelectedIndex());
                                }
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        
            //Cargamos los equipos disponible en el rango de fecha.
                        listadoTipoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarTipoEquiposProgramados(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getFechaRegistro());
                        if(listadoTipoEquipos_mvtoEquipo != null){
                            String datosObjeto[]= new String[listadoTipoEquipos_mvtoEquipo.size()];
                            int contador=0;
                            for(TipoEquipo Objeto : listadoTipoEquipos_mvtoEquipo){ 
                                datosObjeto[contador]=Objeto.getDescripcion();
                                contador++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            select_MvtoEquipo_tipoEquipo.setModel(model);
                        } 
                        //########################################   
            //Cargamos las marcas de equipos disponible en el rango de fecha.
                        if(listadoTipoEquipos_mvtoEquipo !=null){
                            listadoMarcaEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarMarcaEquiposProgramados(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getFechaRegistro(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion());
                            if(listadoMarcaEquipos_mvtoEquipo != null){
                                String datosObjeto[]= new String[listadoMarcaEquipos_mvtoEquipo.size()];
                                int contador=0;
                                for(String Objeto : listadoMarcaEquipos_mvtoEquipo){ 
                                    datosObjeto[contador]=Objeto;
                                    contador++;
                                }
                                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                select_MvtoEquipo_marcaEquipo.setModel(model);
                            } 
                        }
                        //########################################       
                        if(listadoTipoEquipos_mvtoEquipo !=null && listadoMarcaEquipos_mvtoEquipo !=null){
                            //Cargamos los equipos disponible en el rango de fecha.
                            listadoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarAsignacionEquipos_Por_TipoEquipo_MarcaEquipo(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getFechaRegistro(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion(),listadoMarcaEquipos_mvtoEquipo.get(select_MvtoEquipo_marcaEquipo.getSelectedIndex()));
                            if(listadoEquipos_mvtoEquipo != null){
                                String datosObjeto[]= new String[listadoEquipos_mvtoEquipo.size()];
                                int contador=0;
                                for(AsignacionEquipo Objeto : listadoEquipos_mvtoEquipo){ 
                                    datosObjeto[contador]=Objeto.getEquipo().getDescripcion() + " " + Objeto.getEquipo().getModelo();
                                    contador++;
                                }
                                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                select_MvtoEquipo_Equipo.setModel(model);
                            } 
                        }
                        //########################################     
            //Cargamos el equipo actual
                        int contador=0;
                        for(TipoEquipo Objeto : listadoTipoEquipos_mvtoEquipo){ 
                            if(Objeto.getDescripcion().equals(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion())){
                                select_MvtoEquipo_tipoEquipo.setSelectedIndex(contador);
                            }
                            contador++;
                        }
                        contador=0;
                        for(String Objeto : listadoMarcaEquipos_mvtoEquipo){ 
                            if(Objeto.equals(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getAsignacionEquipo().getEquipo().getMarca())){
                                select_MvtoEquipo_marcaEquipo.setSelectedIndex(contador);
                            }
                            contador++;
                        }
                        contador=0;
                        for(AsignacionEquipo Objeto : listadoEquipos_mvtoEquipo){ 
                            if(Objeto.getCodigo().equals(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getAsignacionEquipo().getCodigo())){
                                select_MvtoEquipo_Equipo.setSelectedIndex(contador);
                            }
                            contador++;
                        }

                        if(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getFechaHoraFin() != null){
                            if(mvtoCarbon.getLaborRealizada().getDescripcion().equalsIgnoreCase("STAND BY")){//Fue un MvtoEquipo donde la labor fue Stand BY, por tal motivo no debe aparecer el motivo de parada de finalizado.
                                try {
                                    if(!(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getFechaHoraFin() == null)){
                                        listadoMotivoParada_mvtoEquipo=new ControlDB_MotivoParada(tipoConexion).buscarActivosSinFinalizado();
                                        if(listadoMotivoParada_mvtoEquipo != null){
                                            String datosObjeto[]= new String[listadoMotivoParada_mvtoEquipo.size()];
                                            int contadorQ=0;
                                            for(MotivoParada Objeto : listadoMotivoParada_mvtoEquipo){ 
                                                datosObjeto[contadorQ]=Objeto.getDescripcion();
                                                contadorQ++;
                                            }
                                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                            mvtoEquipo_selectRazonFinalzaci??n.setModel(model);


                                            //cargamos los motivos de parada seg??n la consulta
                                            try{
                                                if(listadoMotivoParada_mvtoEquipo !=null){
                                                    int contadorX=0;
                                                    for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                                        if(motivoParada.getCodigo().equals(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getMotivoParada().getCodigo())){
                                                              mvtoEquipo_selectRazonFinalzaci??n.setSelectedIndex(contadorX);  
                                                        }
                                                        contadorX++;
                                                    }
                                                }
                                            }catch(Exception e){
                                                e.printStackTrace();
                                            }

                                        } 
                                    }
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                    Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                                } 
                            }else{//Como la actividad fue una diferente de Standy By debe aparecer el motivo de parada de Finalizado tambi??n.
                                try {
                                    if(!(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getFechaHoraFin() == null)){
                                        listadoMotivoParada_mvtoEquipo=new ControlDB_MotivoParada(tipoConexion).buscarActivos();
                                        if(listadoMotivoParada_mvtoEquipo != null){
                                            String datosObjeto[]= new String[listadoMotivoParada_mvtoEquipo.size()];
                                            int contadorQ=0;
                                            for(MotivoParada Objeto : listadoMotivoParada_mvtoEquipo){ 
                                                datosObjeto[contadorQ]=Objeto.getDescripcion();
                                                contadorQ++;
                                            }
                                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                            mvtoEquipo_selectRazonFinalzaci??n.setModel(model);

                                            //cargamos los motivos de parada seg??n la consulta
                                            try{
                                                if(listadoMotivoParada_mvtoEquipo !=null){
                                                    int contadorX=0;
                                                    for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                                        if(motivoParada.getCodigo().equals(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getMotivoParada().getCodigo())){
                                                              mvtoEquipo_selectRazonFinalzaci??n.setSelectedIndex(contadorX);  
                                                        }
                                                        contadorX++;
                                                    }
                                                }
                                            }catch(Exception e){
                                                e.printStackTrace();
                                            }
                                        } 
                                    }
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                    Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                                } 
                            }
                        }else{
                            listadoMotivoParada_mvtoEquipo=null;
                            mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();   
                            titulo30.show(false);
                            mvtoEquipo_selectRazonFinalzaci??n.show(false); 
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_EnableActionPerformed

    private void listadoEquiposDescargueItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_listadoEquiposDescargueItemStateChanged
        try{
            //Cargamos el equipo seleccionado en Interfaz
            mvtoCarbon_ListadoEquipos=null;
            mvtoCarbon_ListadoEquipos= listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue.getSelectedIndex());
            if(mvtoCarbon_ListadoEquipos !=null){
                MvtEquipo_codigo.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getCodigo());
                MvtEquipo_tipo.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion());
                mvtEquipo_Equipo_producto.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getProducto());
                mvtEquipo_Equipo_marca.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getMarca());
                mvtEquipo_Equipo_modelo.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getModelo());
                mvtEquipo_Equipo_serial.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getSerial());
                mvtEquipo_Equipo_descripci??n.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getDescripcion());
                mvtEquipo_Equipo_proveedor.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getProveedorEquipo().getDescripcion());
                mvtEquipo_Equipo_pertenencia.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getPertenenciaEquipo().getDescripcion());

                mvtEquipo_laborRealizada.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getLaborRealizada().getDescripcion());
                mvtEquipo_FechaInicioActividad.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getFechaHoraInicio());
                mvtEquipo_FechaFinalActividad.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getFechaHoraFin());
                if(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getTotalMinutos()!=null){
                    try{
                        mvtEquipo_Minutos.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getTotalMinutos());
                        double des=Double.parseDouble(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getTotalMinutos());
                        double totalHoras = Double.parseDouble(""+(des/60));
                        DecimalFormat formato2 = new DecimalFormat("0.00");
                        mvtEquipo_CantidadHoras.setText(""+formato2.format(totalHoras));
                    }catch(Exception e){
                        mvtEquipo_Minutos.setText("");
                        mvtEquipo_CantidadHoras.setText("");
                    }
                }else{
                    mvtEquipo_Minutos.setText("");
                    mvtEquipo_CantidadHoras.setText("");
                }
                if(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getMotivoParadaEstado().equals("1")){
                    mvtEquipo_ActividadFinalizada.setText("SI");
                }else{
                    mvtEquipo_ActividadFinalizada.setText("NO");
                }
                mvtEquipo_MotivoFinalizaci??n.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getMotivoParada().getDescripcion());
                mvtEquipo_estado.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getEstado());
                MvtEquipo_usuarioQuienInicia.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getUsuarioQuieRegistra().getNombres()+" "+mvtoCarbon_ListadoEquipos.getMvtoEquipo().getUsuarioQuieRegistra().getApellidos());
                MvtEquipo_usuarioQuienCierra.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getUsuarioQuienCierra().getNombres()+" "+mvtoCarbon_ListadoEquipos.getMvtoEquipo().getUsuarioQuienCierra().getApellidos());
            }
        }catch(Exception e){

        }
    }//GEN-LAST:event_listadoEquiposDescargueItemStateChanged

    private void listadoEquiposDescargueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listadoEquiposDescargueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listadoEquiposDescargueActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
         if (evt.getClickCount() == 2) {
            // TODO lo del clik derechoo
            int fila1;
            try{
                fila1=tabla.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ningun Vehiculo");
                }
                else{
                    int posicion = paginadorDeTabla.getPosicionador();
                    if(posicion != -1){
                        fila1 = fila1 +posicion;
                    }
                    //Limpiamos campos de la Interfaz
                    MvtCarbon_codigo.setText("");
                    MvtCarbon_placa.setText("");
                    MvtCarbon_FechaRegistro.setText("");
                    MvtCarbon_fechaEntradaVehiculo.setText("");
                    MvtCarbon_fechaSalidaVehiculo.setText("");
                    MvtCarbon_fechaInicioDescargue.setText("");
                    MvtCarbon_fechaFinalDescargue.setText("");
                    MvtCarbon_centroOperacion.setText("");
                    MvtCarbon_auxiliarCentroCostoOrigen.setText("");
                    MvtCarbon_auxiliarCentroCostoDestino.setText("");
                    MvtCarbon_laborRealizada.setText("");
                    mvtEquipo_estado.setText("");
                    MvtCarbon_observacion.setText("");
                    MvtCarbon_cliente.setText("");
                    MvtCarbon_transportadora.setText("");
                    MvtCarbon_deposito.setText("");
                    MvtCarbon_consecutivo.setText("");
                    MvtCarbon_pesoVacio.setText("");
                    MvtCarbon_pesoLleno.setText("");
                    MvtCarbon_pesoNeto.setText("");
                    MvtCarbon_subCentroOperacion.setText("");
                    MvtCarbon_articulo.setText("");
                    MvtCarbon_lavadoVeh??culo.setText("");
                    MvtCarbon_conexionCcarga.setText(""); 
                    MvtCarbon_usuarioQuienInicia.setText("");
                    MvtCarbon_usuarioQuienCierra.setText("");      
                    MvtCarbon_motivoNoLavadoVehiculo.setText("");
                    MvtCarbon_observaci??nLavadoVeh??culo.setText("");
                    MvtCarbon_equipoLavadoC??digo.setText("");
                    MvtCarbon_equipoLavadoDescripci??n.setText("");
                    MvtCarbon_costoLavadoVeh??culo.setText("");
                    MvtCarbon_valorRecaudoEmpresa.setText("");
                    MvtCarbon_valorRecaudoEquipo.setText("");

                    MvtEquipo_codigo.setText("");
                    mvtEquipo_Equipo_producto.setText("");
                    mvtEquipo_Equipo_modelo.setText("");
                    mvtEquipo_Equipo_descripci??n.setText("");
                    mvtEquipo_Equipo_pertenencia.setText("");
                    MvtEquipo_tipo.setText("");
                    mvtEquipo_Equipo_marca.setText("");
                    mvtEquipo_Equipo_serial.setText("");
                    mvtEquipo_Equipo_proveedor.setText("");
                    mvtEquipo_laborRealizada.setText("");
                    mvtEquipo_FechaInicioActividad.setText("");
                    mvtEquipo_FechaFinalActividad.setText("");
                    mvtEquipo_Minutos.setText("");
                    mvtEquipo_CantidadHoras.setText("");
                    mvtEquipo_ActividadFinalizada.setText("");
                    mvtEquipo_MotivoFinalizaci??n.setText("");
                    MvtEquipo_usuarioQuienInicia.setText("");
                    MvtEquipo_usuarioQuienCierra.setText("");

                    select_MvtoEquipo_laborRealizada.setText("");
                    mvtoCarbon=null;

                    InternaFrame_VisualizarMvtoCarbon.show(true);
                    ///DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                    mvtoCarbon= listado.get(fila1).getMvtoCarbon();

                    if(mvtoCarbon != null){
                        
                        check_MvtoCarbon_InicioActividad.setSelected(false);
                        check_MvtoCarbon_FinalizacionActividad.setSelected(false);
                        select_MvtoEquipo_laborRealizada.setText("");
                        check_MvtoCarbon_UsuarioIniciaActividad.setSelected(false);
                        check_MvtoCarbon_UsuarioCierraActividad.setSelected(false);
                        textArea_MvtoCarbon_razonModificacion.setText("");

                        check_MvtoEquipo_InicioActividad.setSelected(false);
                        check_MvtoEquipo_FinalizacionActividad.setSelected(false);
                        textArea_MvtoEquipo_razonModificacion.setText("");
                    
                    
                        MvtCarbon_FechaRegistro.setText(mvtoCarbon.getFechaRegistro());
                        MvtCarbon_placa.setText(mvtoCarbon.getPlaca());
                        MvtCarbon_codigo.setText(mvtoCarbon.getCodigo());
                        MvtCarbon_centroOperacion.setText(mvtoCarbon.getCentroOperacion().getDescripcion());
                        MvtCarbon_subCentroOperacion.setText(mvtoCarbon.getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion());
                        MvtCarbon_auxiliarCentroCostoOrigen.setText(mvtoCarbon.getCentroCostoAuxiliar().getDescripcion());
                        MvtCarbon_auxiliarCentroCostoDestino.setText(mvtoCarbon.getCentroCostoAuxiliarDestino().getDescripcion());
                        MvtCarbon_lavadoVeh??culo.setText(mvtoCarbon.getLavadoVehiculo());
                        MvtCarbon_articulo.setText(mvtoCarbon.getArticulo().getDescripcion());
                        MvtCarbon_laborRealizada.setText(mvtoCarbon.getLaborRealizada().getDescripcion());
                        MvtCarbon_cliente.setText(mvtoCarbon.getCliente().getDescripcion());
                        MvtCarbon_transportadora.setText(mvtoCarbon.getTransportadora().getDescripcion());
                        MvtCarbon_deposito.setText(mvtoCarbon.getDeposito());
                        MvtCarbon_consecutivo.setText(mvtoCarbon.getConsecutivo());
                        MvtCarbon_pesoVacio.setText(mvtoCarbon.getPesoVacio());
                        MvtCarbon_pesoLleno.setText(mvtoCarbon.getPesoLleno());
                        MvtCarbon_pesoNeto.setText(mvtoCarbon.getPesoNeto());
                        MvtCarbon_fechaEntradaVehiculo.setText(mvtoCarbon.getFechaEntradaVehiculo());
                        MvtCarbon_fechaSalidaVehiculo.setText(mvtoCarbon.getFecha_SalidaVehiculo());
                        MvtCarbon_fechaInicioDescargue.setText(mvtoCarbon.getFechaInicioDescargue());
                        MvtCarbon_fechaFinalDescargue.setText(mvtoCarbon.getFechaFinDescargue());
                        MvtCarbon_conexionCcarga.setText(mvtoCarbon.getConexionPesoCcarga());
                        MvtCarbon_observacion.setText(mvtoCarbon.getObservacion());

                        //MvtCarbon_equipoLavadoC??digo.setText(mvtoCarbon.getEquipoLavadoVehiculo().getCodigo());

                        MvtCarbon_usuarioQuienInicia.setText(mvtoCarbon.getUsuarioRegistroMovil().getNombres()+" "+mvtoCarbon.getUsuarioRegistroMovil().getApellidos());
                        MvtCarbon_usuarioQuienCierra.setText(mvtoCarbon.getUsuarioCierraRegistro().getNombres()+" "+mvtoCarbon.getUsuarioCierraRegistro().getApellidos());
                        if(mvtoCarbon.getMotivoNoLavado().getDescripcion() != null){
                            MvtCarbon_motivoNoLavadoVehiculo.setText(mvtoCarbon.getMotivoNoLavado().getDescripcion());
                        }

                        MvtCarbon_observaci??nLavadoVeh??culo.setText(mvtoCarbon.getLavadoVehiculo_Observacion());
                        MvtCarbon_equipoLavadoC??digo.setText(mvtoCarbon.getEquipoLavadoVehiculo().getCodigo());
                        MvtCarbon_equipoLavadoDescripci??n.setText(mvtoCarbon.getEquipoLavadoVehiculo().getDescripcion()+ " "+ mvtoCarbon.getEquipoLavadoVehiculo().getModelo());
                        MvtCarbon_costoLavadoVeh??culo.setText(mvtoCarbon.getCostoLavadoVehiculo());
                        MvtCarbon_valorRecaudoEmpresa.setText(mvtoCarbon.getValorRecaudoEmpresa_lavadoVehiculo());
                        MvtCarbon_valorRecaudoEquipo.setText(mvtoCarbon.getValorRecaudoEquipo_lavadoVehiculo());


                        mvtEquipo_estado.setText(mvtoCarbon.getEstadoMvtoCarbon().getDescripcion());

                        if(mvtoCarbon.getLavadoVehiculo() != null){
                            if(mvtoCarbon.getLavadoVehiculo().equals("SI")){
                                select_MvtoCarbon_lavadoVeh??culo.setSelectedIndex(1);
                                label_QuienRealizaLavadoVeh??culo.show(true);
                                select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.show(true);

                                label_motivoNoLavado.show(false);
                                select_MvtoCarbon_motivoNoLavadoVehiculo.show(false);
                            }else{
                                if(mvtoCarbon.getLavadoVehiculo().equals("NO")){
                                    select_MvtoCarbon_lavadoVeh??culo.setSelectedIndex(0);

                                    label_QuienRealizaLavadoVeh??culo.show(false);
                                    select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.show(false);

                                    label_motivoNoLavado.show(true);
                                    select_MvtoCarbon_motivoNoLavadoVehiculo.show(true);
                                }
                            }
                        }else{
                            select_MvtoCarbon_lavadoVeh??culo.setSelectedIndex(0);
                        }

                        listadoMvtoCarbon_ListadoEquipos= null;
                        listadoEquiposDescargue.removeAllItems();
                        listadoEquiposDescargue1.removeAllItems();
                        select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.removeAllItems();

                        listadoMvtoCarbon_ListadoEquipos=new ControlDB_MvtoCarbon(tipoConexion).buscarMvtoCarbon_Codigo(mvtoCarbon.getCodigo());
                        if(listadoMvtoCarbon_ListadoEquipos !=null){
                            for(MvtoCarbon_ListadoEquipos objeto: listadoMvtoCarbon_ListadoEquipos){
                                listadoEquiposDescargue.addItem(objeto.getAsignacionEquipo().getEquipo().getCodigo()+ " "+ objeto.getAsignacionEquipo().getEquipo().getDescripcion()+" "+objeto.getAsignacionEquipo().getEquipo().getModelo());
                                listadoEquiposDescargue1.addItem(objeto.getAsignacionEquipo().getEquipo().getCodigo()+ " "+ objeto.getAsignacionEquipo().getEquipo().getDescripcion()+" "+objeto.getAsignacionEquipo().getEquipo().getModelo());
                                select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.addItem(objeto.getAsignacionEquipo().getEquipo().getCodigo()+ " "+ objeto.getAsignacionEquipo().getEquipo().getDescripcion()+" "+objeto.getAsignacionEquipo().getEquipo().getModelo());
                            }
                        }
                        //cargamos el equipo quien realiz?? el lavado del veh??culo
                        if(mvtoCarbon.getLavadoVehiculo().equals("SI")){//
                            if(listadoMvtoCarbon_ListadoEquipos != null){
                                int contador=0;
                                for(MvtoCarbon_ListadoEquipos objeto: listadoMvtoCarbon_ListadoEquipos){
                                    if(mvtoCarbon.getEquipoLavadoVehiculo().getCodigo().equals(objeto.getAsignacionEquipo().getEquipo().getCodigo())){
                                        select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.setSelectedIndex(contador);
                                    }
                                    contador++;
                                }
                            }
                        }
                        try{
                            //Cargamos el equipo seleccionado en Interfaz
                            mvtoCarbon_ListadoEquipos=null;
                            mvtoCarbon_ListadoEquipos= listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue.getSelectedIndex());
                            if(mvtoCarbon_ListadoEquipos !=null){
                                MvtEquipo_codigo.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getCodigo());
                                MvtEquipo_tipo.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion());
                                mvtEquipo_Equipo_producto.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getProducto());
                                mvtEquipo_Equipo_marca.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getMarca());
                                mvtEquipo_Equipo_modelo.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getModelo());
                                mvtEquipo_Equipo_serial.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getSerial());
                                mvtEquipo_Equipo_descripci??n.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getDescripcion());
                                mvtEquipo_Equipo_proveedor.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getProveedorEquipo().getDescripcion());
                                mvtEquipo_Equipo_pertenencia.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getAsignacionEquipo().getEquipo().getPertenenciaEquipo().getDescripcion());

                                mvtEquipo_laborRealizada.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getLaborRealizada().getDescripcion());
                                mvtEquipo_FechaInicioActividad.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getFechaHoraInicio());
                                mvtEquipo_FechaFinalActividad.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getFechaHoraFin());

                                MvtEquipo_usuarioQuienInicia.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getUsuarioQuieRegistra().getNombres()+" "+mvtoCarbon_ListadoEquipos.getMvtoEquipo().getUsuarioQuieRegistra().getApellidos());
                                MvtEquipo_usuarioQuienCierra.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getUsuarioQuienCierra().getNombres()+" "+mvtoCarbon_ListadoEquipos.getMvtoEquipo().getUsuarioQuienCierra().getApellidos());



                                if(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getTotalMinutos()!=null){
                                    try{
                                        mvtEquipo_Minutos.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getTotalMinutos());
                                        double des=Double.parseDouble(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getTotalMinutos());
                                        double totalHoras = Double.parseDouble(""+(des/60));
                                        DecimalFormat formato2 = new DecimalFormat("0.00");
                                        mvtEquipo_CantidadHoras.setText(""+formato2.format(totalHoras));
                                    }catch(Exception e){
                                        mvtEquipo_Minutos.setText("");
                                        mvtEquipo_CantidadHoras.setText("");
                                    }
                                }else{
                                    mvtEquipo_Minutos.setText("");
                                    mvtEquipo_CantidadHoras.setText("");
                                }
                                if(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getMotivoParadaEstado() != null){
                                    if(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getMotivoParadaEstado().equals("1")){
                                        mvtEquipo_ActividadFinalizada.setText("SI");
                                    }else{
                                        mvtEquipo_ActividadFinalizada.setText("NO");
                                    }
                                }else{
                                    mvtEquipo_ActividadFinalizada.setText("NO");
                                }
                                mvtEquipo_MotivoFinalizaci??n.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getMotivoParada().getDescripcion());
                                mvtEquipo_estado.setText(mvtoCarbon_ListadoEquipos.getMvtoEquipo().getEstado());
                          }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        try{
                //Cargamos la fecha de inicio de la actividad
                            try{
                                String[] fechaA=mvtoCarbon.getFechaInicioDescargue().split(" ");
                                String[] horaB= fechaA[1].split(":");
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                Date fechaM=dateFormat.parse(fechaA[0]);
                                fechaInicialActividad_MvtoCarbon.setDate(fechaM);
                                select_MvtoCarbon_HoraInicial.setSelectedItem(horaB[0]);
                                select_MvtoCarbon_MinutosInicial.setSelectedItem(horaB[1]);
                            }catch(Exception e){
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(null, "No se pudo carga la fecha de Inicio del descargue","Error!!.", JOptionPane.ERROR_MESSAGE);
                            }

               //Cargamos la fecha de finalizaci??n de la actividad
                            try{
                                if(!(mvtoCarbon.getFechaFinDescargue() == null)){
                                    //System.out.println(""+mvtoEquipo.getFechaHoraFin());
                                    String[] fechaA= mvtoCarbon.getFechaFinDescargue().split(" ");
                                    String[] horaB= fechaA[1].split(":");
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    Date fechaM=dateFormat.parse(fechaA[0]);
                                    fechaFinActividad_MvtoCarbon.setDate(fechaM);
                                    select_MvtoCarbon_HoraFinal.setSelectedItem(horaB[0]);
                                    select_MvtoCarbon_MinutosFinal.setSelectedItem(horaB[1]);


                                }/*else{//No hay
                                    listadoMotivoParada_mvtoEquipo=null;
                                    mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();
                                }*/
                            }catch(Exception e){
                                e.printStackTrace();
                                System.out.println(""+mvtoCarbon.getFechaFinDescargue());
                                JOptionPane.showMessageDialog(null, "No se pudo carga la fecha de finalizaci??n de actividad","Error!!.", JOptionPane.ERROR_MESSAGE);
                            }

                //cargamos el Centro de Operaci??n seg??n la consulta
                            try{
                                int contador=0;
                                for(CentroOperacion centroOperacion: listadoCentroOperacion_mvtoCarbon){
                                    if(centroOperacion.getDescripcion().equals(mvtoCarbon.getCentroOperacion().getDescripcion())){
                                          select_MvtoCarbon_CO.setSelectedIndex(contador);  
                                    }
                                    contador++;
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                            cambioEstado("Subcentro_Costo");
                            //cargamos el subcentro de costo seg??n la consulta
                            try{
                                int contador=0;
                                for(CentroCostoSubCentro centroCostoSubCentro: listadoCentroCostoSubCentro_mvtoCarbon){
                                    if(centroCostoSubCentro.getDescripcion().equals(mvtoCarbon.getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion())){
                                          select_MvtoCarbon_SubcentroCosto.setSelectedIndex(contador);  
                                    }
                                    contador++;
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                //cargamos los auxiliares de Centro de costos seg??n la consulta
                            try{
                                int contador=0;
                                for(CentroCostoAuxiliar centroCostoAuxiliar: listadoCentroCostoAuxiliar_mvtoCarbon){
                                    if(centroCostoAuxiliar.getDescripcion().equals(mvtoCarbon.getCentroCostoAuxiliar().getDescripcion())){
                                          select_MvtoCarbon_CCAuxiliar.setSelectedIndex(contador);  
                                    }
                                    contador++;
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                            //mvto_listCarbon.getMvtoCarbon().getLaborRealizada().getDescripcion()
                //cargamos las labores realizadas seg??n la consulta
                            try{
                                int contador=0;
                                System.out.println("cargador fue ===========>"+mvtoCarbon.getLaborRealizada().getDescripcion());
                                for(LaborRealizada laborRealizada: listadoLaborRealizada_mvtoCarbon){
                                    if(laborRealizada.getDescripcion().equals(mvtoCarbon.getLaborRealizada().getDescripcion())){
                                          select_MvtoCarbon_laborRealizada.setSelectedIndex(contador);  
                                           System.out.println("encontrado fue ===========>"+laborRealizada.getDescripcion());
                                    }
                                    contador++;
                                    System.out.println("consultado fue ===========>"+laborRealizada.getDescripcion());
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }

                //cargamos los centros de costos auxiliares destino seg??n la consulta
                            try{
                                if(listadoCentroCostoAuxiliarDestino_mvtoCarbon !=null){
                                    int contador=0;
                                    for(CentroCostoAuxiliar centroCostoAuxiliar: listadoCentroCostoAuxiliarDestino_mvtoCarbon){
                                        if(centroCostoAuxiliar.getDescripcion().equals(mvtoCarbon.getCentroCostoAuxiliarDestino().getDescripcion())){
                                              select_MvtoCarbon_CCAuxiliarDestino.setSelectedIndex(contador);  
                                        }
                                        contador++;

                                    }
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }


                            //Cargamos los Usuario de inicio y cierre
                            try{
                                if(listadoUsuarioInicioRegistro_mvtoCarbon !=null){
                                    if(mvtoCarbon.getUsuarioRegistroMovil().getCodigo() != null){
                                        int contador=0;
                                        for(Usuario Objeto: listadoUsuarioInicioRegistro_mvtoCarbon){
                                            if(Objeto.getCodigo().equals(mvtoCarbon.getUsuarioRegistroMovil().getCodigo())){
                                                  select_MvtoCarbon_UsuarioIniciaRegistro.setSelectedIndex(contador);  
                                            }
                                            contador++;
                                        }
                                    }else{

                                    }
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                            try{
                                if(listadoUsuarioCierraRegistro_mvtoCarbon !=null){
                                    if(mvtoCarbon.getUsuarioCierraRegistro().getCodigo() != null){
                                        int contador=0;
                                        for(Usuario Objeto: listadoUsuarioCierraRegistro_mvtoCarbon){
                                            if(Objeto.getCodigo().equals(mvtoCarbon.getUsuarioCierraRegistro().getCodigo())){
                                                  select_MvtoCarbon_UsuarioCierraRegistro.setSelectedIndex(contador);  
                                            }
                                            contador++;
                                        }
                                    }else{
                                        select_MvtoCarbon_UsuarioCierraRegistro.setSelectedIndex(select_MvtoCarbon_UsuarioIniciaRegistro.getSelectedIndex());
                                    }
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }








                            /*//cargamos los recobro seg??n la consulta
                            try{
                                int contador=0;
                                for(Recobro recobro: listadoRecobros_mvtoEquipo){
                                    if(recobro.getDescripcion().equals(mvtoCarbon.getRecobro().getDescripcion())){
                                          select_MvtoCarbon_recobro.setSelectedIndex(contador);  
                                    }
                                    contador++;
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }*/



                            /**borrar
                             mvtoCarbon_ListadoEquipos=null;
                            mvtoCarbon_ListadoEquipos= listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue.getSelectedIndex());

                             **/


                             //Cargamos los Usuario de inicio y cierre en MvtoEquipo
                            try{
                                if(listadoUsuarioInicioRegistro_mvtoEquipo !=null){
                                    if(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getUsuarioQuieRegistra().getCodigo() != null){
                                        int contador=0;
                                        for(Usuario Objeto: listadoUsuarioInicioRegistro_mvtoEquipo){
                                            if(Objeto.getCodigo().equals(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getUsuarioQuieRegistra().getCodigo())){
                                                  select_MvtoEquipo_UsuarioIniciaRegistro.setSelectedIndex(contador);  
                                            }
                                            contador++;
                                        }
                                    }else{

                                    }
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                            try{
                                if(listadoUsuarioCierraRegistro_mvtoEquipo !=null){
                                    if(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getUsuarioQuienCierra().getCodigo() != null){
                                        int contador=0;
                                        for(Usuario Objeto: listadoUsuarioCierraRegistro_mvtoEquipo){
                                            if(Objeto.getCodigo().equals(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getUsuarioQuienCierra().getCodigo())){
                                                  select_MvtoEquipo_UsuarioCierraRegistro.setSelectedIndex(contador);  
                                            }
                                            contador++;
                                        }
                                    }else{
                                        select_MvtoEquipo_UsuarioCierraRegistro.setSelectedIndex(select_MvtoEquipo_UsuarioIniciaRegistro.getSelectedIndex());
                                    }
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }

                //Cargamos los equipos disponible en el rango de fecha.
                            listadoTipoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarTipoEquiposProgramados(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getFechaRegistro());
                            if(listadoTipoEquipos_mvtoEquipo != null){
                                String datosObjeto[]= new String[listadoTipoEquipos_mvtoEquipo.size()];
                                int contador=0;
                                for(TipoEquipo Objeto : listadoTipoEquipos_mvtoEquipo){ 
                                    datosObjeto[contador]=Objeto.getDescripcion();
                                    contador++;
                                }
                                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                select_MvtoEquipo_tipoEquipo.setModel(model);
                            } 
                            //########################################   
                //Cargamos las marcas de equipos disponible en el rango de fecha.
                            if(listadoTipoEquipos_mvtoEquipo !=null){
                                listadoMarcaEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarMarcaEquiposProgramados(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getFechaRegistro(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion());
                                if(listadoMarcaEquipos_mvtoEquipo != null){
                                    String datosObjeto[]= new String[listadoMarcaEquipos_mvtoEquipo.size()];
                                    int contador=0;
                                    for(String Objeto : listadoMarcaEquipos_mvtoEquipo){ 
                                        datosObjeto[contador]=Objeto;
                                        contador++;
                                    }
                                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                    select_MvtoEquipo_marcaEquipo.setModel(model);
                                } 
                            }
                            //########################################       
                            if(listadoTipoEquipos_mvtoEquipo !=null && listadoMarcaEquipos_mvtoEquipo !=null){
                                //Cargamos los equipos disponible en el rango de fecha.
                                listadoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarAsignacionEquipos_Por_TipoEquipo_MarcaEquipo(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getFechaRegistro(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion(),listadoMarcaEquipos_mvtoEquipo.get(select_MvtoEquipo_marcaEquipo.getSelectedIndex()));
                                if(listadoEquipos_mvtoEquipo != null){
                                    String datosObjeto[]= new String[listadoEquipos_mvtoEquipo.size()];
                                    int contador=0;
                                    for(AsignacionEquipo Objeto : listadoEquipos_mvtoEquipo){ 
                                        datosObjeto[contador]=Objeto.getEquipo().getDescripcion() + " " + Objeto.getEquipo().getModelo();
                                        contador++;
                                    }
                                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                    select_MvtoEquipo_Equipo.setModel(model);
                                } 
                            }
                            //########################################     
                            //Cargamos el equipo actual 
                            int contador=0;
                            for(TipoEquipo Objeto : listadoTipoEquipos_mvtoEquipo){ 
                                if(Objeto.getDescripcion().equals(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion())){
                                    select_MvtoEquipo_tipoEquipo.setSelectedIndex(contador);
                                }
                                contador++;
                            }
                            contador=0;
                            for(String Objeto : listadoMarcaEquipos_mvtoEquipo){ 
                                if(Objeto.equals(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getAsignacionEquipo().getEquipo().getMarca())){
                                    select_MvtoEquipo_marcaEquipo.setSelectedIndex(contador);
                                }
                                contador++;
                            }
                            contador=0;
                            for(AsignacionEquipo Objeto : listadoEquipos_mvtoEquipo){ 
                                if(Objeto.getCodigo().equals(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getAsignacionEquipo().getCodigo())){
                                    select_MvtoEquipo_Equipo.setSelectedIndex(contador);
                                }
                                contador++;
                            }

                            if(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getFechaHoraFin() != null){
                                if(mvtoCarbon.getLaborRealizada().getDescripcion().equalsIgnoreCase("STAND BY")){//Fue un MvtoEquipo donde la labor fue Stand BY, por tal motivo no debe aparecer el motivo de parada de finalizado.
                                    try {
                                        if(!(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getFechaHoraFin() == null)){
                                            listadoMotivoParada_mvtoEquipo=new ControlDB_MotivoParada(tipoConexion).buscarActivosSinFinalizado();
                                            if(listadoMotivoParada_mvtoEquipo != null){
                                                String datosObjeto[]= new String[listadoMotivoParada_mvtoEquipo.size()];
                                                int contadorQ=0;
                                                for(MotivoParada Objeto : listadoMotivoParada_mvtoEquipo){ 
                                                    datosObjeto[contadorQ]=Objeto.getDescripcion();
                                                    contadorQ++;
                                                }
                                                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                                mvtoEquipo_selectRazonFinalzaci??n.setModel(model);


                                                //cargamos los motivos de parada seg??n la consulta
                                                try{
                                                    if(listadoMotivoParada_mvtoEquipo !=null){
                                                        int contadorX=0;
                                                        for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                                            if(motivoParada.getCodigo().equals(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getMotivoParada().getCodigo())){
                                                                  mvtoEquipo_selectRazonFinalzaci??n.setSelectedIndex(contadorX);  
                                                            }
                                                            contadorX++;
                                                        }
                                                    }
                                                }catch(Exception e){
                                                    e.printStackTrace();
                                                }

                                            } 
                                        }
                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                        Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                                    } 
                                }else{//Como la actividad fue una diferente de Standy By debe aparecer el motivo de parada de Finalizado tambi??n.
                                    try {
                                        if(!(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getFechaHoraFin() == null)){
                                            listadoMotivoParada_mvtoEquipo=new ControlDB_MotivoParada(tipoConexion).buscarActivos();
                                            if(listadoMotivoParada_mvtoEquipo != null){
                                                String datosObjeto[]= new String[listadoMotivoParada_mvtoEquipo.size()];
                                                int contadorQ=0;
                                                for(MotivoParada Objeto : listadoMotivoParada_mvtoEquipo){ 
                                                    datosObjeto[contadorQ]=Objeto.getDescripcion();
                                                    contadorQ++;
                                                }
                                                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                                mvtoEquipo_selectRazonFinalzaci??n.setModel(model);

                                                //cargamos los motivos de parada seg??n la consulta
                                                try{
                                                    if(listadoMotivoParada_mvtoEquipo !=null){
                                                        int contadorX=0;
                                                        for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                                            if(motivoParada.getCodigo().equals(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getMotivoParada().getCodigo())){
                                                                  mvtoEquipo_selectRazonFinalzaci??n.setSelectedIndex(contadorX);  
                                                            }
                                                            contadorX++;
                                                        }
                                                    }
                                                }catch(Exception e){
                                                    e.printStackTrace();
                                                }
                                            } 
                                        }
                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                        Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                                    } 
                                }
                            }else{
                                listadoMotivoParada_mvtoEquipo=null;
                                mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();   
                                titulo30.show(false);
                                mvtoEquipo_selectRazonFinalzaci??n.show(false); 
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void check_MvtoCarbon_InicioActividadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_check_MvtoCarbon_InicioActividadItemStateChanged
        if(check_MvtoCarbon_InicioActividad.isSelected()){
            fechaInicialActividad_MvtoCarbon.show(true);
            label_MvtoCarbon_HoraInicial.show(true);
            select_MvtoCarbon_HoraInicial.show(true);
            label_MvtoCarbon_separadorInicial.show(true);
            select_MvtoCarbon_MinutosInicial.show(true);
            label_MvtoCarbon_ZonaHorariaInicial.show(true);
        }else{
            fechaInicialActividad_MvtoCarbon.show(false);
            label_MvtoCarbon_HoraInicial.show(false);
            select_MvtoCarbon_HoraInicial.show(false);
            label_MvtoCarbon_separadorInicial.show(false);
            select_MvtoCarbon_MinutosInicial.show(false);
            label_MvtoCarbon_ZonaHorariaInicial.show(false);
        }
    }//GEN-LAST:event_check_MvtoCarbon_InicioActividadItemStateChanged

    private void check_MvtoCarbon_FinalizacionActividadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_check_MvtoCarbon_FinalizacionActividadItemStateChanged
        //validamos si la fecha de finalizaci??n fue marcada
        if(check_MvtoCarbon_FinalizacionActividad.isSelected()){ //Fue marcada cambiar la fecha de finalizaci??n
            fechaFinActividad_MvtoCarbon.show(true);
            label_MvtoCarbon_HoraFinal.show(true);
            select_MvtoCarbon_HoraFinal.show(true);
            label_MvtoCarbon_separadorFinal.show(true);
            select_MvtoCarbon_MinutosFinal.show(true);
            label_MvtoCarbon_ZonaHorariaFinal.show(true);
        }else{
            fechaFinActividad_MvtoCarbon.show(false);
            label_MvtoCarbon_HoraFinal.show(false);
            select_MvtoCarbon_HoraFinal.show(false);
            label_MvtoCarbon_separadorFinal.show(false);
            select_MvtoCarbon_MinutosFinal.show(false);
            label_MvtoCarbon_ZonaHorariaFinal.show(false);
        }
        //Borramos la lista y los select de motivo de Finalizaci??n
        listadoMotivoParada_mvtoEquipo=null;
        mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();

        //validamos el estado inicial de la fecha de finalizaci??n
        if(mvtoEquipo.getFechaHoraFin() == null){
            //Validamos si la fecha de finalizaci??n fue seleccionada
            if(check_MvtoCarbon_FinalizacionActividad.isSelected()){//la fecha de finalizaci??n de la actividad si fue seleccionada
                titulo30.show(true);
                mvtoEquipo_selectRazonFinalzaci??n.show(true);
                if(mvtoEquipo.getLaborRealizada().getDescripcion().equalsIgnoreCase("STAND BY")){//Fue un MvtoEquipo donde la labor fue Stand BY, por tal motivo no debe aparecer el motivo de parada de finalizado.
                    try {
                        listadoMotivoParada_mvtoEquipo=new ControlDB_MotivoParada(tipoConexion).buscarActivosSinFinalizado();
                    } catch (SQLException ex) {
                        Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(listadoMotivoParada_mvtoEquipo != null){
                        String datosObjeto[]= new String[listadoMotivoParada_mvtoEquipo.size()];
                        int contadorQ=0;
                        for(MotivoParada Objeto : listadoMotivoParada_mvtoEquipo){
                            datosObjeto[contadorQ]=Objeto.getDescripcion();
                            contadorQ++;
                        }
                        final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                        mvtoEquipo_selectRazonFinalzaci??n.setModel(model);

                        //cargamos los motivos de parada seg??n la consulta
                        try{
                            if(listadoMotivoParada_mvtoEquipo !=null){
                                int contadorX=0;
                                for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                    if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                        mvtoEquipo_selectRazonFinalzaci??n.setSelectedIndex(contadorX);
                                    }
                                    contadorX++;
                                }
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }else{
                        listadoMotivoParada_mvtoEquipo=null;
                        mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();
                    }
                }else{//La labor realizada es diferente a un Stand By
                    try {
                        listadoMotivoParada_mvtoEquipo=new ControlDB_MotivoParada(tipoConexion).buscarActivos();
                        if(listadoMotivoParada_mvtoEquipo != null){
                            String datosObjeto[]= new String[listadoMotivoParada_mvtoEquipo.size()];
                            int contadorQ=0;
                            for(MotivoParada Objeto : listadoMotivoParada_mvtoEquipo){
                                datosObjeto[contadorQ]=Objeto.getDescripcion();
                                contadorQ++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            mvtoEquipo_selectRazonFinalzaci??n.setModel(model);

                            //cargamos los motivos de parada seg??n la consulta
                            try{
                                if(listadoMotivoParada_mvtoEquipo !=null){
                                    int contadorX=0;
                                    for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                        if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                            mvtoEquipo_selectRazonFinalzaci??n.setSelectedIndex(contadorX);
                                        }
                                        contadorX++;
                                    }
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }else{
                            listadoMotivoParada_mvtoEquipo=null;
                            mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }else{//la fecha de finalizaci??n de la actividad no fue seleccionada
                listadoMotivoParada_mvtoEquipo=null;
                mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();
                titulo30.show(false);
                mvtoEquipo_selectRazonFinalzaci??n.show(false);
            }
        }else{
            if(mvtoEquipo.getFechaHoraFin() != null){
                //Validamos si la fecha de finalizaci??n fue seleccionada
                if(check_MvtoCarbon_FinalizacionActividad.isSelected()){//la fecha de finalizaci??n de la actividad si fue seleccionada
                    titulo30.show(true);
                    mvtoEquipo_selectRazonFinalzaci??n.show(true);
                    if(mvtoEquipo.getLaborRealizada().getDescripcion().equalsIgnoreCase("STAND BY")){//Fue un MvtoEquipo donde la labor fue Stand BY, por tal motivo no debe aparecer el motivo de parada de finalizado.
                        try {
                            listadoMotivoParada_mvtoEquipo=new ControlDB_MotivoParada(tipoConexion).buscarActivosSinFinalizado();
                        } catch (SQLException ex) {
                            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(listadoMotivoParada_mvtoEquipo != null){
                            String datosObjeto[]= new String[listadoMotivoParada_mvtoEquipo.size()];
                            int contadorQ=0;
                            for(MotivoParada Objeto : listadoMotivoParada_mvtoEquipo){
                                datosObjeto[contadorQ]=Objeto.getDescripcion();
                                contadorQ++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            mvtoEquipo_selectRazonFinalzaci??n.setModel(model);

                            //cargamos los motivos de parada seg??n la consulta
                            try{
                                if(listadoMotivoParada_mvtoEquipo !=null){
                                    int contadorX=0;
                                    for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                        if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                            mvtoEquipo_selectRazonFinalzaci??n.setSelectedIndex(contadorX);
                                        }
                                        contadorX++;
                                    }
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }else{
                            listadoMotivoParada_mvtoEquipo=null;
                            mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();
                        }
                    }else{//La labor realizada es diferente a un Stand By
                        try {
                            listadoMotivoParada_mvtoEquipo=new ControlDB_MotivoParada(tipoConexion).buscarActivos();
                            if(listadoMotivoParada_mvtoEquipo != null){
                                String datosObjeto[]= new String[listadoMotivoParada_mvtoEquipo.size()];
                                int contadorQ=0;
                                for(MotivoParada Objeto : listadoMotivoParada_mvtoEquipo){
                                    datosObjeto[contadorQ]=Objeto.getDescripcion();
                                    contadorQ++;
                                }
                                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                mvtoEquipo_selectRazonFinalzaci??n.setModel(model);

                                //cargamos los motivos de parada seg??n la consulta
                                try{
                                    if(listadoMotivoParada_mvtoEquipo !=null){
                                        int contadorX=0;
                                        for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                            if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                                mvtoEquipo_selectRazonFinalzaci??n.setSelectedIndex(contadorX);
                                            }
                                            contadorX++;
                                        }
                                    }
                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }else{
                                listadoMotivoParada_mvtoEquipo=null;
                                mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }else{//la fecha de finalizaci??n de la actividad no fue seleccionada
                    titulo30.show(true);
                    mvtoEquipo_selectRazonFinalzaci??n.show(true);
                    if(mvtoEquipo.getLaborRealizada().getDescripcion().equalsIgnoreCase("STAND BY")){//Fue un MvtoEquipo donde la labor fue Stand BY, por tal motivo no debe aparecer el motivo de parada de finalizado.
                        try {
                            listadoMotivoParada_mvtoEquipo=new ControlDB_MotivoParada(tipoConexion).buscarActivosSinFinalizado();
                        } catch (SQLException ex) {
                            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(listadoMotivoParada_mvtoEquipo != null){
                            String datosObjeto[]= new String[listadoMotivoParada_mvtoEquipo.size()];
                            int contadorQ=0;
                            for(MotivoParada Objeto : listadoMotivoParada_mvtoEquipo){
                                datosObjeto[contadorQ]=Objeto.getDescripcion();
                                contadorQ++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            mvtoEquipo_selectRazonFinalzaci??n.setModel(model);

                            //cargamos los motivos de parada seg??n la consulta
                            try{
                                if(listadoMotivoParada_mvtoEquipo !=null){
                                    int contadorX=0;
                                    for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                        if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                            mvtoEquipo_selectRazonFinalzaci??n.setSelectedIndex(contadorX);
                                        }
                                        contadorX++;
                                    }
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }else{
                            listadoMotivoParada_mvtoEquipo=null;
                            mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();
                        }
                    }else{//La labor realizada es diferente a un Stand By
                        try {
                            listadoMotivoParada_mvtoEquipo=new ControlDB_MotivoParada(tipoConexion).buscarActivos();
                            if(listadoMotivoParada_mvtoEquipo != null){
                                String datosObjeto[]= new String[listadoMotivoParada_mvtoEquipo.size()];
                                int contadorQ=0;
                                for(MotivoParada Objeto : listadoMotivoParada_mvtoEquipo){
                                    datosObjeto[contadorQ]=Objeto.getDescripcion();
                                    contadorQ++;
                                }
                                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                mvtoEquipo_selectRazonFinalzaci??n.setModel(model);

                                //cargamos los motivos de parada seg??n la consulta
                                try{
                                    if(listadoMotivoParada_mvtoEquipo !=null){
                                        int contadorX=0;
                                        for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                            if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                                mvtoEquipo_selectRazonFinalzaci??n.setSelectedIndex(contadorX);
                                            }
                                            contadorX++;
                                        }
                                    }
                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }else{
                                listadoMotivoParada_mvtoEquipo=null;
                                mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_check_MvtoCarbon_FinalizacionActividadItemStateChanged

    private void fechaInicialActividad_MvtoCarbonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicialActividad_MvtoCarbonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaInicialActividad_MvtoCarbonMouseClicked

    private void fechaInicialActividad_MvtoCarbonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicialActividad_MvtoCarbonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaInicialActividad_MvtoCarbonMouseEntered

    private void select_MvtoCarbon_HoraInicialItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_HoraInicialItemStateChanged
        if(select_MvtoCarbon_HoraInicial.getSelectedIndex()<= 11){
            label_MvtoCarbon_ZonaHorariaInicial.setText("AM");
        }else{
            label_MvtoCarbon_ZonaHorariaInicial.setText("PM");
        }
    }//GEN-LAST:event_select_MvtoCarbon_HoraInicialItemStateChanged

    private void select_MvtoCarbon_HoraInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_HoraInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoCarbon_HoraInicialActionPerformed

    private void select_MvtoCarbon_MinutosInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_MinutosInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoCarbon_MinutosInicialActionPerformed

    private void fechaFinActividad_MvtoCarbonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFinActividad_MvtoCarbonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFinActividad_MvtoCarbonMouseClicked

    private void fechaFinActividad_MvtoCarbonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFinActividad_MvtoCarbonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFinActividad_MvtoCarbonMouseEntered

    private void select_MvtoCarbon_HoraFinalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_HoraFinalItemStateChanged
        if(select_MvtoCarbon_HoraFinal.getSelectedIndex()<= 11){
            label_MvtoCarbon_ZonaHorariaFinal.setText("AM");
        }else{
            label_MvtoCarbon_ZonaHorariaFinal.setText("PM");
        }
    }//GEN-LAST:event_select_MvtoCarbon_HoraFinalItemStateChanged

    private void select_MvtoCarbon_HoraFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_HoraFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoCarbon_HoraFinalActionPerformed

    private void select_MvtoCarbon_MinutosFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_MinutosFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoCarbon_MinutosFinalActionPerformed

    private void select_MvtoCarbon_COItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_COItemStateChanged
        //Cargamos en la interfaz los subcentro de costos activos
        listadoCentroCostoAuxiliar_mvtoCarbon=null;
        listadoCentroCostoSubCentro_mvtoCarbon=null;
        listadoLaborRealizada_mvtoCarbon=null;
        listadoCentroCostoAuxiliarDestino_mvtoCarbon=null;
        try {
            if(listadoCentroOperacion_mvtoCarbon != null){
                listadoCentroCostoSubCentro_mvtoCarbon=new ControlDB_CentroCostoSubCentro(tipoConexion).buscarActivos(listadoCentroOperacion_mvtoCarbon.get(select_MvtoCarbon_CO.getSelectedIndex()));
                if(listadoCentroCostoSubCentro_mvtoCarbon != null){
                    String datosObjeto[]= new String[listadoCentroCostoSubCentro_mvtoCarbon.size()];
                    int contador=0;
                    for(CentroCostoSubCentro Objeto : listadoCentroCostoSubCentro_mvtoCarbon){ 
                        datosObjeto[contador]=Objeto.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_MvtoCarbon_SubcentroCosto.setModel(model);
                }else{
                    listadoCentroCostoSubCentro_mvtoCarbon=null;
                    select_MvtoCarbon_SubcentroCosto.removeAllItems();

                    listadoCentroCostoAuxiliar_mvtoCarbon=null;
                    select_MvtoCarbon_CCAuxiliar.removeAllItems();

                    listadoLaborRealizada_mvtoCarbon=null;
                    select_MvtoCarbon_laborRealizada.removeAllItems();

                    listadoCentroCostoAuxiliarDestino_mvtoCarbon=null;
                    select_MvtoCarbon_CCAuxiliarDestino.removeAllItems();
                }
            }else{
                
                listadoCentroOperacion_mvtoCarbon=null;
                select_MvtoCarbon_CO.removeAllItems();
                
                listadoCentroCostoSubCentro_mvtoCarbon=null;
                select_MvtoCarbon_SubcentroCosto.removeAllItems();
                
                listadoCentroCostoAuxiliar_mvtoCarbon=null;
                select_MvtoCarbon_CCAuxiliar.removeAllItems();
                
                listadoLaborRealizada_mvtoCarbon=null;
                select_MvtoCarbon_laborRealizada.removeAllItems();
                
                listadoCentroCostoAuxiliarDestino_mvtoCarbon=null;
                select_MvtoCarbon_CCAuxiliarDestino.removeAllItems();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
        } 
        //Cargamos los auxiliares
        if(listadoCentroCostoSubCentro_mvtoCarbon != null){
            try {
                listadoCentroCostoAuxiliar_mvtoCarbon = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro_mvtoCarbon.get(select_MvtoCarbon_SubcentroCosto.getSelectedIndex()).getCodigo());
                if(listadoCentroCostoAuxiliar_mvtoCarbon != null){
                    String datosObjeto[] = new String[listadoCentroCostoAuxiliar_mvtoCarbon.size()];
                    int contador = 0;
                    for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliar_mvtoCarbon) {
                        datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_MvtoCarbon_CCAuxiliar.setModel(model);
                }else{
                    listadoCentroCostoAuxiliar_mvtoCarbon=null;
                    select_MvtoCarbon_CCAuxiliar.removeAllItems();
                    
                } 
            }catch (SQLException e){ 
            }
            //Cargamos las labores realizadas
            try {
                listadoLaborRealizada_mvtoCarbon=new ControlDB_LaborRealizada(tipoConexion).buscarPorSubcentroCosto(listadoCentroCostoSubCentro_mvtoCarbon.get(select_MvtoCarbon_SubcentroCosto.getSelectedIndex()));
                if(listadoLaborRealizada_mvtoCarbon != null){
                    String datosObjeto[]= new String[listadoLaborRealizada_mvtoCarbon.size()];
                    int contador=0;
                    for(LaborRealizada Objeto : listadoLaborRealizada_mvtoCarbon){ 
                        datosObjeto[contador]=Objeto.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_MvtoCarbon_laborRealizada.setModel(model);
                }else{
                    listadoLaborRealizada_mvtoCarbon=null;
                    select_MvtoCarbon_laborRealizada.removeAllItems();
                    
                    listadoCentroCostoAuxiliarDestino_mvtoCarbon=null;
                    select_MvtoCarbon_CCAuxiliarDestino.removeAllItems();
                    
                } 
            } catch (SQLException ex) {
                Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }else{ 
            listadoCentroCostoSubCentro_mvtoCarbon=null;    
            select_MvtoCarbon_SubcentroCosto.removeAllItems();

            listadoCentroCostoAuxiliar_mvtoCarbon=null;
            select_MvtoCarbon_CCAuxiliar.removeAllItems();

            listadoLaborRealizada_mvtoCarbon=null;    
            select_MvtoCarbon_laborRealizada.removeAllItems();

            listadoCentroCostoAuxiliarDestino_mvtoCarbon=null;
            select_MvtoCarbon_CCAuxiliarDestino.removeAllItems();
        } 
    }//GEN-LAST:event_select_MvtoCarbon_COItemStateChanged

    private void select_MvtoCarbon_SubcentroCostoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_SubcentroCostoItemStateChanged
        if(listadoCentroCostoSubCentro_mvtoCarbon != null){
            try {
                listadoCentroCostoAuxiliar_mvtoCarbon = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro_mvtoCarbon.get(select_MvtoCarbon_SubcentroCosto.getSelectedIndex()).getCodigo());
                if(listadoCentroCostoAuxiliar_mvtoCarbon != null){
                    String datosObjeto[] = new String[listadoCentroCostoAuxiliar_mvtoCarbon.size()];
                    int contador = 0;
                    for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliar_mvtoCarbon) {
                        datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_MvtoCarbon_CCAuxiliar.setModel(model);
                }else{
                    listadoCentroCostoAuxiliar_mvtoCarbon=null;
                    select_MvtoCarbon_CCAuxiliar.removeAllItems();
                } 
                try {
                    listadoLaborRealizada_mvtoCarbon=new ControlDB_LaborRealizada(tipoConexion).buscarPorSubcentroCosto(listadoCentroCostoSubCentro_mvtoCarbon.get(select_MvtoCarbon_SubcentroCosto.getSelectedIndex()));
                    if(listadoLaborRealizada_mvtoCarbon != null){
                        String datosObjeto[]= new String[listadoLaborRealizada_mvtoCarbon.size()];
                        int contador=0;
                        for(LaborRealizada Objeto : listadoLaborRealizada_mvtoCarbon){ 
                            datosObjeto[contador]=Objeto.getDescripcion();
                            contador++;
                        }
                        final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                        select_MvtoCarbon_laborRealizada.setModel(model);
                    }else{
                        listadoLaborRealizada_mvtoCarbon=null;
                        select_MvtoCarbon_laborRealizada.removeAllItems();
                    } 
                } catch (SQLException ex) {
                    Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }catch (SQLException e){ 
            }
        }else{
            listadoCentroCostoSubCentro_mvtoCarbon=null;
            select_MvtoCarbon_SubcentroCosto.removeAllItems();
            
            listadoLaborRealizada_mvtoCarbon=null;
            select_MvtoCarbon_laborRealizada.removeAllItems();
            
            listadoCentroCostoAuxiliar_mvtoCarbon=null;
            select_MvtoCarbon_CCAuxiliar.removeAllItems();
            
            listadoCentroCostoAuxiliarDestino_mvtoCarbon=null;
            select_MvtoCarbon_CCAuxiliarDestino.removeAllItems();
        }
        
        //validamos si la labor Realiza seleccionada tiene bodega Destino
        if(listadoLaborRealizada_mvtoCarbon != null){
            if(listadoLaborRealizada_mvtoCarbon.get(select_MvtoCarbon_laborRealizada.getSelectedIndex()).getBodegaDestino().equals("1")){//Fue seleccionada una labor Realizada que tiene marcada Bodega destino por tal motivo cargalos las Bodega destino
               // listadoCentroCostoAuxiliarDestino_mvtoCarbon
                if(listadoCentroCostoSubCentro_mvtoCarbon != null){
                    try {
                        listadoCentroCostoAuxiliarDestino_mvtoCarbon = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro_mvtoCarbon.get(select_MvtoCarbon_SubcentroCosto.getSelectedIndex()).getCodigo());
                        if(listadoCentroCostoAuxiliarDestino_mvtoCarbon != null){
                            String datosObjeto[] = new String[listadoCentroCostoAuxiliarDestino_mvtoCarbon.size()];
                            int contador = 0;
                            for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliarDestino_mvtoCarbon) {
                                datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                                contador++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            select_MvtoCarbon_CCAuxiliarDestino.setModel(model);
                        }else{
                            select_MvtoCarbon_CCAuxiliarDestino.removeAllItems();
                        } 
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }            
            }else{
               listadoCentroCostoAuxiliarDestino_mvtoCarbon=null;
                select_MvtoCarbon_CCAuxiliarDestino.removeAllItems();
            }
        }else{
            listadoLaborRealizada_mvtoCarbon=null;
            select_MvtoCarbon_laborRealizada.removeAllItems();
            listadoCentroCostoAuxiliarDestino_mvtoCarbon=null;
            select_MvtoCarbon_CCAuxiliarDestino.removeAllItems();
        }
    }//GEN-LAST:event_select_MvtoCarbon_SubcentroCostoItemStateChanged

    private void select_MvtoCarbon_laborRealizadaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_laborRealizadaItemStateChanged
        if(listadoLaborRealizada_mvtoCarbon != null){
            if(listadoLaborRealizada_mvtoCarbon.get(select_MvtoCarbon_laborRealizada.getSelectedIndex()).getBodegaDestino().equals("1")){//Fue seleccionada una labor Realizada que tiene marcada Bodega destino por tal motivo cargalos las Bodega destino
               // listadoCentroCostoAuxiliarDestino_mvtoCarbon
                if(listadoCentroCostoSubCentro_mvtoCarbon != null){
                    try {
                        listadoCentroCostoAuxiliarDestino_mvtoCarbon = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro_mvtoCarbon.get(select_MvtoCarbon_SubcentroCosto.getSelectedIndex()).getCodigo());
                        if(listadoCentroCostoAuxiliarDestino_mvtoCarbon != null){
                            String datosObjeto[] = new String[listadoCentroCostoAuxiliarDestino_mvtoCarbon.size()];
                            int contador = 0;
                            for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliarDestino_mvtoCarbon) {
                                datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                                contador++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            select_MvtoCarbon_CCAuxiliarDestino.setModel(model);
                        }else{
                            listadoCentroCostoAuxiliarDestino_mvtoCarbon=null;
                            select_MvtoCarbon_CCAuxiliarDestino.removeAllItems();
                        } 
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }          
            }else{
                listadoCentroCostoAuxiliarDestino_mvtoCarbon=null;
                select_MvtoCarbon_CCAuxiliarDestino.removeAllItems(); 
            }
            
            /*if(listadoLaborRealizada_mvtoCarbon.get(select_MvtoCarbon_laborRealizada.getSelectedIndex()).getDescripcion().equalsIgnoreCase("STAND BY")){//Fue un MvtoCarbon donde la labor fue Stand BY, por tal motivo no debe aparecer el motivo de parada de finalizado.
                try {
                    listadoMotivoParada_mvtoEquipo=new ControlDB_MotivoParada(tipoConexion).buscarActivosSinFinalizado();
                } catch (SQLException ex) {
                    Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(listadoMotivoParada_mvtoEquipo != null){
                    String datosObjeto[]= new String[listadoMotivoParada_mvtoEquipo.size()];
                    int contadorQ=0;
                    for(MotivoParada Objeto : listadoMotivoParada_mvtoEquipo){ 
                        datosObjeto[contadorQ]=Objeto.getDescripcion();
                        contadorQ++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    mvtoEquipo_selectRazonFinalzaci??n.setModel(model);


                    //cargamos los motivos de parada seg??n la consulta
                    try{
                        if(listadoMotivoParada_mvtoEquipo !=null){
                            int contadorX=0;
                            for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                if(motivoParada.getCodigo().equals(mvtoCarbon.getMotivoParada().getCodigo())){
                                      mvtoCarbon_selectRazonFinalzaci??n.setSelectedIndex(contadorX);  
                                }
                                contadorX++;
                            }
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    listadoMotivoParada_mvtoEquipo=null;
                    mvtoCarbon_selectRazonFinalzaci??n.removeAllItems(); 
                } 
            }else{//La labor realizada es diferente a un Stand By
                try {
                    listadoMotivoParada_mvtoCarbon=new ControlDB_MotivoParada(tipoConexion).buscarActivos();
                    if(listadoMotivoParada_mvtoCarbon != null){
                        String datosObjeto[]= new String[listadoMotivoParada_mvtoCarbon.size()];
                        int contadorQ=0;
                        for(MotivoParada Objeto : listadoMotivoParada_mvtoCarbon){ 
                            datosObjeto[contadorQ]=Objeto.getDescripcion();
                            contadorQ++;
                        }
                        final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                        mvtoCarbon_selectRazonFinalzaci??n.setModel(model);

                        //cargamos los motivos de parada seg??n la consulta
                        try{
                            if(listadoMotivoParada_mvtoCarbon !=null){
                                int contadorX=0;
                                for(MotivoParada motivoParada: listadoMotivoParada_mvtoCarbon){
                                    if(motivoParada.getCodigo().equals(mvtoCarbon.getMotivoParada().getCodigo())){
                                          mvtoCarbon_selectRazonFinalzaci??n.setSelectedIndex(contadorX);  
                                    }
                                    contadorX++;
                                }
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }else{
                        listadoMotivoParada_mvtoCarbon=null;
                        mvtoCarbon_selectRazonFinalzaci??n.removeAllItems(); 
                    }   
                } catch (SQLException ex) {
                    Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }  */
        }else{
            listadoLaborRealizada_mvtoCarbon=null;
            select_MvtoCarbon_laborRealizada.removeAllItems();
                        
            listadoCentroCostoAuxiliarDestino_mvtoCarbon=null;
            select_MvtoCarbon_CCAuxiliarDestino.removeAllItems();
        }

    }//GEN-LAST:event_select_MvtoCarbon_laborRealizadaItemStateChanged

    private void select_MvtoCarbon_laborRealizadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_laborRealizadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoCarbon_laborRealizadaActionPerformed

    private void select_MvtoEquipo_recobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_recobroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_recobroActionPerformed

    private void listadoEquiposDescargue1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_listadoEquiposDescargue1ItemStateChanged
        mvtoEquipo=null;
        if(listadoMvtoCarbon_ListadoEquipos != null){
            mvtoEquipo=listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo();
            if(mvtoEquipo != null){
                select_MvtoEquipo_laborRealizada.setText(mvtoEquipo.getLaborRealizada().getDescripcion());
                
            }
            try {
                //Cargamos los equipos disponible en el rango de fecha.
                listadoTipoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarTipoEquiposProgramados(mvtoEquipo.getFechaRegistro());
            } catch (SQLException ex) {
                Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(listadoTipoEquipos_mvtoEquipo != null){
                String datosObjeto[]= new String[listadoTipoEquipos_mvtoEquipo.size()];
                int contador=0;
                for(TipoEquipo Objeto : listadoTipoEquipos_mvtoEquipo){ 
                    datosObjeto[contador]=Objeto.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                select_MvtoEquipo_tipoEquipo.setModel(model);
            } 
            //########################################   
        //Cargamos las marcas de equipos disponible en el rango de fecha.
            if(listadoTipoEquipos_mvtoEquipo !=null){
                try {
                    listadoMarcaEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarMarcaEquiposProgramados(mvtoEquipo.getFechaRegistro(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion());
                } catch (SQLException ex) {
                    Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(listadoMarcaEquipos_mvtoEquipo != null){
                    String datosObjeto[]= new String[listadoMarcaEquipos_mvtoEquipo.size()];
                    int contador=0;
                    for(String Objeto : listadoMarcaEquipos_mvtoEquipo){ 
                        datosObjeto[contador]=Objeto;
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_MvtoEquipo_marcaEquipo.setModel(model);
                } 
            }
            //########################################       
            if(listadoTipoEquipos_mvtoEquipo !=null && listadoMarcaEquipos_mvtoEquipo !=null){
                try {
                    //Cargamos los equipos disponible en el rango de fecha.
                    listadoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarAsignacionEquipos_Por_TipoEquipo_MarcaEquipo(mvtoEquipo.getFechaRegistro(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion(),listadoMarcaEquipos_mvtoEquipo.get(select_MvtoEquipo_marcaEquipo.getSelectedIndex()));
                } catch (SQLException ex) {
                    Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(listadoEquipos_mvtoEquipo != null){
                    String datosObjeto[]= new String[listadoEquipos_mvtoEquipo.size()];
                    int contador=0;
                    for(AsignacionEquipo Objeto : listadoEquipos_mvtoEquipo){ 
                        datosObjeto[contador]=Objeto.getEquipo().getDescripcion() + " " + Objeto.getEquipo().getModelo();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_MvtoEquipo_Equipo.setModel(model);
                } 
            }
            //########################################     
        //Cargamos el equipo actual
            int contador=0;
            if(listadoTipoEquipos_mvtoEquipo != null){
                for(TipoEquipo Objeto : listadoTipoEquipos_mvtoEquipo){ 
                    if(Objeto.getDescripcion().equals(mvtoEquipo.getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion())){
                        select_MvtoEquipo_tipoEquipo.setSelectedIndex(contador);
                    }
                    contador++;
                }
            }
            if(listadoMarcaEquipos_mvtoEquipo != null){
                contador=0;
                for(String Objeto : listadoMarcaEquipos_mvtoEquipo){ 
                    if(Objeto.equals(mvtoEquipo.getAsignacionEquipo().getEquipo().getMarca())){
                        select_MvtoEquipo_marcaEquipo.setSelectedIndex(contador);
                    }
                    contador++;
                }
             }
            if(listadoEquipos_mvtoEquipo != null){
                contador=0;
                for(AsignacionEquipo Objeto : listadoEquipos_mvtoEquipo){ 
                    if(Objeto.getCodigo().equals(mvtoEquipo.getAsignacionEquipo().getCodigo())){
                        select_MvtoEquipo_Equipo.setSelectedIndex(contador);
                    }
                    contador++;
                }
            }
            
    //cargamos los recobro seg??n la consulta
            if(listadoRecobros_mvtoEquipo != null){
                try{
                    contador=0;
                    for(Recobro recobro: listadoRecobros_mvtoEquipo){
                        if(recobro.getCodigo().equals(mvtoEquipo.getRecobro().getCodigo())){
                              select_MvtoEquipo_recobro.setSelectedIndex(contador);  
                        }
                        contador++;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
    //Cargamos la fecha de inicio de la actividad
            try{
                String[] fechaA=mvtoEquipo.getFechaHoraInicio().split(" ");
                String[] horaB= fechaA[1].split(":");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaM=dateFormat.parse(fechaA[0]);
                fechaInicialActividad_MvtoEquipo.setDate(fechaM);
                select_MvtoEquipo_HoraInicial.setSelectedItem(horaB[0]);
                select_MvtoEquipo_MinutosInicial.setSelectedItem(horaB[1]);
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "No se pudo carga la fecha de Inicio del equipo","Error!!.", JOptionPane.ERROR_MESSAGE);
            }

//Cargamos la fecha de finalizaci??n de la actividad
            try{
                if(!(mvtoEquipo.getFechaHoraFin() == null)){
                    //System.out.println(""+mvtoEquipo.getFechaHoraFin());
                    String[] fechaA= mvtoEquipo.getFechaHoraFin().split(" ");
                    String[] horaB= fechaA[1].split(":");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechaM=dateFormat.parse(fechaA[0]);
                    fechaFinActividad_MvtoEquipo.setDate(fechaM);
                    select_MvtoEquipo_HoraFinal.setSelectedItem(horaB[0]);
                    select_MvtoEquipo_MinutosFinal.setSelectedItem(horaB[1]);


                }/*else{//No hay
                    listadoMotivoParada_mvtoEquipo=null;
                    mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();
                }*/
            }catch(Exception e){
                e.printStackTrace();
                //System.out.println(""+Equipo.getFechaFinDescargue());
                JOptionPane.showMessageDialog(null, "No se pudo carga la fecha de finalizaci??n de actividad","Error!!.", JOptionPane.ERROR_MESSAGE);
            }    
            
            if(mvtoEquipo.getFechaHoraFin() != null){
                if(mvtoCarbon.getLaborRealizada().getDescripcion().equalsIgnoreCase("STAND BY")){//Fue un MvtoEquipo donde la labor fue Stand BY, por tal motivo no debe aparecer el motivo de parada de finalizado.
                    try {
                        if(!(mvtoEquipo.getFechaHoraFin() == null)){
                            listadoMotivoParada_mvtoEquipo=new ControlDB_MotivoParada(tipoConexion).buscarActivosSinFinalizado();
                            if(listadoMotivoParada_mvtoEquipo != null){
                                String datosObjeto[]= new String[listadoMotivoParada_mvtoEquipo.size()];
                                int contadorQ=0;
                                for(MotivoParada Objeto : listadoMotivoParada_mvtoEquipo){ 
                                    datosObjeto[contadorQ]=Objeto.getDescripcion();
                                    contadorQ++;
                                }
                                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                mvtoEquipo_selectRazonFinalzaci??n.setModel(model);


                                //cargamos los motivos de parada seg??n la consulta
                                try{
                                    if(listadoMotivoParada_mvtoEquipo !=null){
                                        int contadorX=0;
                                        for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                            if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                                  mvtoEquipo_selectRazonFinalzaci??n.setSelectedIndex(contadorX);  
                                            }
                                            contadorX++;
                                        }
                                    }
                                }catch(Exception e){
                                    e.printStackTrace();
                                }

                            } 
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }else{//Como la actividad fue una diferente de Standy By debe aparecer el motivo de parada de Finalizado tambi??n.
                    try {
                        if(!(mvtoEquipo.getFechaHoraFin() == null)){
                            listadoMotivoParada_mvtoEquipo=new ControlDB_MotivoParada(tipoConexion).buscarActivos();
                            if(listadoMotivoParada_mvtoEquipo != null){
                                String datosObjeto[]= new String[listadoMotivoParada_mvtoEquipo.size()];
                                int contadorQ=0;
                                for(MotivoParada Objeto : listadoMotivoParada_mvtoEquipo){ 
                                    datosObjeto[contadorQ]=Objeto.getDescripcion();
                                    contadorQ++;
                                }
                                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                mvtoEquipo_selectRazonFinalzaci??n.setModel(model);

                                //cargamos los motivos de parada seg??n la consulta
                                try{
                                    if(listadoMotivoParada_mvtoEquipo !=null){
                                        int contadorX=0;
                                        for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                            if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                                  mvtoEquipo_selectRazonFinalzaci??n.setSelectedIndex(contadorX);  
                                            }
                                            contadorX++;
                                        }
                                    }
                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            } 
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }
            }else{
                listadoMotivoParada_mvtoEquipo=null;
                mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();   
                titulo30.show(false);
                mvtoEquipo_selectRazonFinalzaci??n.show(false); 
            }
        }
    }//GEN-LAST:event_listadoEquiposDescargue1ItemStateChanged

    private void listadoEquiposDescargue1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listadoEquiposDescargue1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listadoEquiposDescargue1ActionPerformed

    private void select_MvtoCarbon_lavadoVeh??culoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_lavadoVeh??culoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoCarbon_lavadoVeh??culoActionPerformed

    private void check_MvtoEquipo_InicioActividadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_check_MvtoEquipo_InicioActividadItemStateChanged
        if(check_MvtoEquipo_InicioActividad.isSelected()){
            fechaInicialActividad_MvtoEquipo.show(true);
            label_MvtoEquipo_HoraInicial.show(true);
            select_MvtoEquipo_HoraInicial.show(true);
            label_MvtoEquipo_separadorInicial.show(true);
            select_MvtoEquipo_MinutosInicial.show(true);
            label_MvtoEquipo_ZonaHorariaInicial.show(true);
        }else{
            fechaInicialActividad_MvtoEquipo.show(false);
            label_MvtoEquipo_HoraInicial.show(false);
            select_MvtoEquipo_HoraInicial.show(false);
            label_MvtoEquipo_separadorInicial.show(false);
            select_MvtoEquipo_MinutosInicial.show(false);
            label_MvtoEquipo_ZonaHorariaInicial.show(false);
        }
    }//GEN-LAST:event_check_MvtoEquipo_InicioActividadItemStateChanged

    private void check_MvtoEquipo_FinalizacionActividadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_check_MvtoEquipo_FinalizacionActividadItemStateChanged
        //validamos si la fecha de finalizaci??n fue marcada
        if(check_MvtoEquipo_FinalizacionActividad.isSelected()){ //Fue marcada cambiar la fecha de finalizaci??n
            fechaFinActividad_MvtoEquipo.show(true);
            label_MvtoEquipo_HoraFinal.show(true);
            select_MvtoEquipo_HoraFinal.show(true);
            label_MvtoEquipo_separadorFinal.show(true);
            select_MvtoEquipo_MinutosFinal.show(true);
            label_MvtoEquipo_ZonaHorariaFinal.show(true);
        }else{
            fechaFinActividad_MvtoEquipo.show(false);
            label_MvtoEquipo_HoraFinal.show(false);
            select_MvtoEquipo_HoraFinal.show(false);
            label_MvtoEquipo_separadorFinal.show(false);
            select_MvtoEquipo_MinutosFinal.show(false);
            label_MvtoEquipo_ZonaHorariaFinal.show(false);
        }
        //Borramos la lista y los select de motivo de Finalizaci??n
        listadoMotivoParada_mvtoEquipo=null;
        mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();

        //validamos el estado inicial de la fecha de finalizaci??n
        if(mvtoEquipo.getFechaHoraFin() == null){
            //Validamos si la fecha de finalizaci??n fue seleccionada
            if(check_MvtoEquipo_FinalizacionActividad.isSelected()){//la fecha de finalizaci??n de la actividad si fue seleccionada
                titulo30.show(true);
                mvtoEquipo_selectRazonFinalzaci??n.show(true);
                if(mvtoEquipo.getLaborRealizada().getDescripcion().equalsIgnoreCase("STAND BY")){//Fue un MvtoEquipo donde la labor fue Stand BY, por tal motivo no debe aparecer el motivo de parada de finalizado.
                    try {
                        listadoMotivoParada_mvtoEquipo=new ControlDB_MotivoParada(tipoConexion).buscarActivosSinFinalizado();
                    } catch (SQLException ex) {
                        Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(listadoMotivoParada_mvtoEquipo != null){
                        String datosObjeto[]= new String[listadoMotivoParada_mvtoEquipo.size()];
                        int contadorQ=0;
                        for(MotivoParada Objeto : listadoMotivoParada_mvtoEquipo){
                            datosObjeto[contadorQ]=Objeto.getDescripcion();
                            contadorQ++;
                        }
                        final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                        mvtoEquipo_selectRazonFinalzaci??n.setModel(model);

                        //cargamos los motivos de parada seg??n la consulta
                        try{
                            if(listadoMotivoParada_mvtoEquipo !=null){
                                int contadorX=0;
                                for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                    if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                        mvtoEquipo_selectRazonFinalzaci??n.setSelectedIndex(contadorX);
                                    }
                                    contadorX++;
                                }
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }else{
                        listadoMotivoParada_mvtoEquipo=null;
                        mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();
                    }
                }else{//La labor realizada es diferente a un Stand By
                    try {
                        listadoMotivoParada_mvtoEquipo=new ControlDB_MotivoParada(tipoConexion).buscarActivos();
                        if(listadoMotivoParada_mvtoEquipo != null){
                            String datosObjeto[]= new String[listadoMotivoParada_mvtoEquipo.size()];
                            int contadorQ=0;
                            for(MotivoParada Objeto : listadoMotivoParada_mvtoEquipo){
                                datosObjeto[contadorQ]=Objeto.getDescripcion();
                                contadorQ++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            mvtoEquipo_selectRazonFinalzaci??n.setModel(model);

                            //cargamos los motivos de parada seg??n la consulta
                            try{
                                if(listadoMotivoParada_mvtoEquipo !=null){
                                    int contadorX=0;
                                    for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                        if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                            mvtoEquipo_selectRazonFinalzaci??n.setSelectedIndex(contadorX);
                                        }
                                        contadorX++;
                                    }
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }else{
                            listadoMotivoParada_mvtoEquipo=null;
                            mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }else{//la fecha de finalizaci??n de la actividad no fue seleccionada
                listadoMotivoParada_mvtoEquipo=null;
                mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();
                titulo30.show(false);
                mvtoEquipo_selectRazonFinalzaci??n.show(false);
            }
        }else{
            if(mvtoEquipo.getFechaHoraFin() != null){
                //Validamos si la fecha de finalizaci??n fue seleccionada
                if(check_MvtoEquipo_FinalizacionActividad.isSelected()){//la fecha de finalizaci??n de la actividad si fue seleccionada
                    titulo30.show(true);
                    mvtoEquipo_selectRazonFinalzaci??n.show(true);
                    if(mvtoEquipo.getLaborRealizada().getDescripcion().equalsIgnoreCase("STAND BY")){//Fue un MvtoEquipo donde la labor fue Stand BY, por tal motivo no debe aparecer el motivo de parada de finalizado.
                        try {
                            listadoMotivoParada_mvtoEquipo=new ControlDB_MotivoParada(tipoConexion).buscarActivosSinFinalizado();
                        } catch (SQLException ex) {
                            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(listadoMotivoParada_mvtoEquipo != null){
                            String datosObjeto[]= new String[listadoMotivoParada_mvtoEquipo.size()];
                            int contadorQ=0;
                            for(MotivoParada Objeto : listadoMotivoParada_mvtoEquipo){
                                datosObjeto[contadorQ]=Objeto.getDescripcion();
                                contadorQ++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            mvtoEquipo_selectRazonFinalzaci??n.setModel(model);

                            //cargamos los motivos de parada seg??n la consulta
                            try{
                                if(listadoMotivoParada_mvtoEquipo !=null){
                                    int contadorX=0;
                                    for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                        if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                            mvtoEquipo_selectRazonFinalzaci??n.setSelectedIndex(contadorX);
                                        }
                                        contadorX++;
                                    }
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }else{
                            listadoMotivoParada_mvtoEquipo=null;
                            mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();
                        }
                    }else{//La labor realizada es diferente a un Stand By
                        try {
                            listadoMotivoParada_mvtoEquipo=new ControlDB_MotivoParada(tipoConexion).buscarActivos();
                            if(listadoMotivoParada_mvtoEquipo != null){
                                String datosObjeto[]= new String[listadoMotivoParada_mvtoEquipo.size()];
                                int contadorQ=0;
                                for(MotivoParada Objeto : listadoMotivoParada_mvtoEquipo){
                                    datosObjeto[contadorQ]=Objeto.getDescripcion();
                                    contadorQ++;
                                }
                                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                mvtoEquipo_selectRazonFinalzaci??n.setModel(model);

                                //cargamos los motivos de parada seg??n la consulta
                                try{
                                    if(listadoMotivoParada_mvtoEquipo !=null){
                                        int contadorX=0;
                                        for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                            if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                                mvtoEquipo_selectRazonFinalzaci??n.setSelectedIndex(contadorX);
                                            }
                                            contadorX++;
                                        }
                                    }
                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }else{
                                listadoMotivoParada_mvtoEquipo=null;
                                mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }else{//la fecha de finalizaci??n de la actividad no fue seleccionada
                    titulo30.show(true);
                    mvtoEquipo_selectRazonFinalzaci??n.show(true);
                    if(mvtoEquipo.getLaborRealizada().getDescripcion().equalsIgnoreCase("STAND BY")){//Fue un MvtoEquipo donde la labor fue Stand BY, por tal motivo no debe aparecer el motivo de parada de finalizado.
                        try {
                            listadoMotivoParada_mvtoEquipo=new ControlDB_MotivoParada(tipoConexion).buscarActivosSinFinalizado();
                        } catch (SQLException ex) {
                            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(listadoMotivoParada_mvtoEquipo != null){
                            String datosObjeto[]= new String[listadoMotivoParada_mvtoEquipo.size()];
                            int contadorQ=0;
                            for(MotivoParada Objeto : listadoMotivoParada_mvtoEquipo){
                                datosObjeto[contadorQ]=Objeto.getDescripcion();
                                contadorQ++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            mvtoEquipo_selectRazonFinalzaci??n.setModel(model);

                            //cargamos los motivos de parada seg??n la consulta
                            try{
                                if(listadoMotivoParada_mvtoEquipo !=null){
                                    int contadorX=0;
                                    for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                        if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                            mvtoEquipo_selectRazonFinalzaci??n.setSelectedIndex(contadorX);
                                        }
                                        contadorX++;
                                    }
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }else{
                            listadoMotivoParada_mvtoEquipo=null;
                            mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();
                        }
                    }else{//La labor realizada es diferente a un Stand By
                        try {
                            listadoMotivoParada_mvtoEquipo=new ControlDB_MotivoParada(tipoConexion).buscarActivos();
                            if(listadoMotivoParada_mvtoEquipo != null){
                                String datosObjeto[]= new String[listadoMotivoParada_mvtoEquipo.size()];
                                int contadorQ=0;
                                for(MotivoParada Objeto : listadoMotivoParada_mvtoEquipo){
                                    datosObjeto[contadorQ]=Objeto.getDescripcion();
                                    contadorQ++;
                                }
                                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                mvtoEquipo_selectRazonFinalzaci??n.setModel(model);

                                //cargamos los motivos de parada seg??n la consulta
                                try{
                                    if(listadoMotivoParada_mvtoEquipo !=null){
                                        int contadorX=0;
                                        for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                            if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                                mvtoEquipo_selectRazonFinalzaci??n.setSelectedIndex(contadorX);
                                            }
                                            contadorX++;
                                        }
                                    }
                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }else{
                                listadoMotivoParada_mvtoEquipo=null;
                                mvtoEquipo_selectRazonFinalzaci??n.removeAllItems();
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_check_MvtoEquipo_FinalizacionActividadItemStateChanged

    private void fechaFinActividad_MvtoEquipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFinActividad_MvtoEquipoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFinActividad_MvtoEquipoMouseClicked

    private void fechaFinActividad_MvtoEquipoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFinActividad_MvtoEquipoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFinActividad_MvtoEquipoMouseEntered

    private void fechaInicialActividad_MvtoEquipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicialActividad_MvtoEquipoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaInicialActividad_MvtoEquipoMouseClicked

    private void fechaInicialActividad_MvtoEquipoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicialActividad_MvtoEquipoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaInicialActividad_MvtoEquipoMouseEntered

    private void select_MvtoEquipo_HoraInicialItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_HoraInicialItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_HoraInicialItemStateChanged

    private void select_MvtoEquipo_HoraInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_HoraInicialActionPerformed
        if(select_MvtoEquipo_HoraInicial.getSelectedIndex()<= 11){
            label_MvtoEquipo_ZonaHorariaInicial.setText("AM");
        }else{
            label_MvtoEquipo_ZonaHorariaInicial.setText("PM");
        }
    }//GEN-LAST:event_select_MvtoEquipo_HoraInicialActionPerformed

    private void select_MvtoEquipo_HoraFinalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_HoraFinalItemStateChanged
        if(select_MvtoEquipo_HoraFinal.getSelectedIndex()<= 11){
            label_MvtoEquipo_ZonaHorariaFinal.setText("AM");
        }else{
            label_MvtoEquipo_ZonaHorariaFinal.setText("PM");
        }
    }//GEN-LAST:event_select_MvtoEquipo_HoraFinalItemStateChanged

    private void select_MvtoEquipo_HoraFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_HoraFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_HoraFinalActionPerformed

    private void select_MvtoEquipo_MinutosFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_MinutosFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_MinutosFinalActionPerformed

    private void select_MvtoEquipo_MinutosInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_MinutosInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_MinutosInicialActionPerformed

    private void select_MvtoEquipo_tipoEquipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_tipoEquipoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_tipoEquipoItemStateChanged

    private void select_MvtoEquipo_tipoEquipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_tipoEquipoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_tipoEquipoMouseClicked

    private void select_MvtoEquipo_tipoEquipoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_tipoEquipoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_tipoEquipoMouseEntered

    private void select_MvtoEquipo_tipoEquipoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_tipoEquipoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_tipoEquipoMouseExited

    private void select_MvtoEquipo_tipoEquipoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_tipoEquipoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_tipoEquipoMousePressed

    private void select_MvtoEquipo_tipoEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_tipoEquipoActionPerformed
        if(mvtoEquipo !=null){
            //Cargamos las marcas de equipos disponible en el rango de fecha.
            if(listadoTipoEquipos_mvtoEquipo !=null){
                try {
                    listadoMarcaEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarMarcaEquiposProgramados(mvtoEquipo.getFechaRegistro(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion());
                } catch (SQLException ex) {
                    Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(listadoMarcaEquipos_mvtoEquipo != null){
                    String datosObjeto[]= new String[listadoMarcaEquipos_mvtoEquipo.size()];
                    int contador=0;
                    for(String Objeto : listadoMarcaEquipos_mvtoEquipo){
                        datosObjeto[contador]=Objeto;
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_MvtoEquipo_marcaEquipo.setModel(model);
                }
            }
            //########################################

            if(listadoTipoEquipos_mvtoEquipo !=null && listadoMarcaEquipos_mvtoEquipo !=null){
                try {
                    //Cargamos los equipos disponible en el rango de fecha.
                    listadoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarAsignacionEquipos_Por_TipoEquipo_MarcaEquipo(mvtoEquipo.getFechaRegistro(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion(),listadoMarcaEquipos_mvtoEquipo.get(select_MvtoEquipo_marcaEquipo.getSelectedIndex()));
                } catch (SQLException ex) {
                    Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(listadoEquipos_mvtoEquipo != null){
                    String datosObjeto[]= new String[listadoEquipos_mvtoEquipo.size()];
                    int contador=0;
                    for(AsignacionEquipo Objeto : listadoEquipos_mvtoEquipo){
                        datosObjeto[contador]=Objeto.getEquipo().getDescripcion() + " " + Objeto.getEquipo().getModelo();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_MvtoEquipo_Equipo.setModel(model);
                }
            }
            //########################################
        }
    }//GEN-LAST:event_select_MvtoEquipo_tipoEquipoActionPerformed

    private void select_MvtoEquipo_marcaEquipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_marcaEquipoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_marcaEquipoItemStateChanged

    private void select_MvtoEquipo_marcaEquipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_marcaEquipoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_marcaEquipoMouseClicked

    private void select_MvtoEquipo_marcaEquipoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_marcaEquipoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_marcaEquipoMouseExited

    private void select_MvtoEquipo_marcaEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_marcaEquipoActionPerformed
        if(mvtoEquipo !=null){
            if(listadoTipoEquipos_mvtoEquipo !=null && listadoMarcaEquipos_mvtoEquipo !=null){
                try {
                    //Cargamos los equipos disponible en el rango de fecha.
                    listadoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarAsignacionEquipos_Por_TipoEquipo_MarcaEquipo(mvtoEquipo.getFechaRegistro(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion(),listadoMarcaEquipos_mvtoEquipo.get(select_MvtoEquipo_marcaEquipo.getSelectedIndex()));
                } catch (SQLException ex) {
                    Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(listadoEquipos_mvtoEquipo != null){
                    String datosObjeto[]= new String[listadoEquipos_mvtoEquipo.size()];
                    int contador=0;
                    for(AsignacionEquipo Objeto : listadoEquipos_mvtoEquipo){
                        datosObjeto[contador]=Objeto.getEquipo().getDescripcion() + " " + Objeto.getEquipo().getModelo();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_MvtoEquipo_Equipo.setModel(model);
                }
            }
            //########################################
        }
    }//GEN-LAST:event_select_MvtoEquipo_marcaEquipoActionPerformed

    private void select_MvtoEquipo_EquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_EquipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_EquipoActionPerformed

    private void mvtoEquipo_selectRazonFinalzaci??nItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mvtoEquipo_selectRazonFinalzaci??nItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_mvtoEquipo_selectRazonFinalzaci??nItemStateChanged

    private void registrar_MvtoEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrar_MvtoEquipoActionPerformed
        //Asignamos un MvtoEquipo temporal para validar campos
        if(!textArea_MvtoEquipo_razonModificacion.getText().equals("")){
            boolean validarCampos= true;
            String scriptDB="";
            String scriptDB_listadoCarbonEquipo="";
            String scriptAuditoria="";
            String scriptMvtoCarbon="";
            try{
                // MvtoEquipo mvtoEquipoTemp = mvtoEquipo;
                //validamos las fechas si son iguales
                String fechaInicio_Actividad="";
                String fechaFinalizacion_Actividad="";

                /**VALIDAMOS LAS FECHAS ##############################################################**/
                if(check_MvtoEquipo_InicioActividad.isSelected() && check_MvtoEquipo_FinalizacionActividad.isSelected()){//Seleccion?? modificar la fecha de Inicio Actividad y la fecha de Finalizaci??n de actividad
                    try{    //Almacenamos la fecha de inicio de actividad
                        Calendar fechaI = fechaInicialActividad_MvtoEquipo.getCalendar();
                        String anoI = ""+fechaI.get(Calendar.YEAR);
                        String mesI = "";
                        if((fechaI.get(Calendar.MONTH) +1) <=9){
                            mesI = "0"+(fechaI.get(Calendar.MONTH) + 1);
                        }else{
                            mesI = ""+(fechaI.get(Calendar.MONTH) + 1);
                        }
                        String diaI = "";
                        if(fechaI.get(Calendar.DAY_OF_MONTH) <=9){
                            diaI = "0"+fechaI.get(Calendar.DAY_OF_MONTH);
                        }else{
                            diaI = ""+fechaI.get(Calendar.DAY_OF_MONTH);
                        }
                        fechaInicio_Actividad=anoI+"-"+mesI+"-"+diaI+" "+select_MvtoEquipo_HoraInicial.getSelectedItem().toString()+":"+select_MvtoEquipo_MinutosInicial.getSelectedItem().toString()+":00.0";
                        try{        //Almacenamos la fecha de finalizaci??n de actividad   
                            Calendar fechaF = fechaFinActividad_MvtoEquipo.getCalendar();
                            String anoF = ""+fechaF.get(Calendar.YEAR);
                            String mesF = "";
                            if((fechaF.get(Calendar.MONTH) +1) <=9){
                                mesF = "0"+(fechaF.get(Calendar.MONTH) + 1);
                            }else{
                                mesF = ""+(fechaF.get(Calendar.MONTH) + 1);
                            }
                            String diaF = "";
                            if(fechaF.get(Calendar.DAY_OF_MONTH) <=9){
                                diaF = "0"+fechaF.get(Calendar.DAY_OF_MONTH);
                            }else{
                                diaF = ""+fechaF.get(Calendar.DAY_OF_MONTH);
                            }
                            fechaFinalizacion_Actividad=anoF+"-"+mesF+"-"+diaF+" "+select_MvtoEquipo_HoraFinal.getSelectedItem().toString()+":"+select_MvtoEquipo_MinutosFinal.getSelectedItem().toString()+":00.0";
                            int resultDosFechas=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorEntreDosFechas("'"+fechaInicio_Actividad+"'","'"+fechaFinalizacion_Actividad+"'"));
                            if(resultDosFechas < 0){
                                validarCampos=false;
                                JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser mayor a la fecha de Finalizaci??n","Advertencia", JOptionPane.ERROR_MESSAGE );
                            }else{
                                if(resultDosFechas ==0){
                                    validarCampos=false;
                                    JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser Igual a la fecha de Finalizaci??n","Advertencia", JOptionPane.ERROR_MESSAGE );
                                }else{
                                    validarCampos=true;
                                    if(scriptDB.equals("")){
                                        scriptDB +="[me_fecha]='"+fechaInicio_Actividad+"' ,[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"' , [me_fecha_hora_fin]='"+fechaFinalizacion_Actividad+"' ,[me_total_minutos]=(SELECT DATEDIFF(minute,'"+fechaInicio_Actividad+"', '"+fechaFinalizacion_Actividad+"'))\n";
                                        scriptAuditoria +="[me_fecha]='"+fechaInicio_Actividad+"' ,[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"' , [me_fecha_hora_fin]='"+fechaFinalizacion_Actividad+"'\n";
                                    }else{
                                        scriptDB +=",[me_fecha]='"+fechaInicio_Actividad+"' ,[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"' , [me_fecha_hora_fin]='"+fechaFinalizacion_Actividad+"' ,[me_total_minutos]=(SELECT DATEDIFF(minute,'"+fechaInicio_Actividad+"', '"+fechaFinalizacion_Actividad+"'))\n";
                                        scriptAuditoria +=",[me_fecha]='"+fechaInicio_Actividad+"' ,[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"' , [me_fecha_hora_fin]='"+fechaFinalizacion_Actividad+"'\n";
                                    }
                                }
                            }
                            }catch(Exception e){
                            validarCampos=false;
                            JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Finalizaci??n","Advertencia",JOptionPane.ERROR_MESSAGE);
                        }
                    }catch(Exception e){
                        validarCampos=false;
                        JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Inicio","Advertencia",JOptionPane.ERROR_MESSAGE);
                    }                        
                }else{
                    if(check_MvtoEquipo_InicioActividad.isSelected()){ //Seleccion?? modificar solo la fecha de inicio de actividad
                        try{    //Almacenamos la fecha de inicio de actividad
                            Calendar fechaI = fechaInicialActividad_MvtoEquipo.getCalendar();
                            String anoI = ""+fechaI.get(Calendar.YEAR);
                            String mesI = "";
                            if((fechaI.get(Calendar.MONTH) +1) <=9){
                                mesI = "0"+(fechaI.get(Calendar.MONTH) + 1);
                            }else{
                                mesI = ""+(fechaI.get(Calendar.MONTH) + 1);
                            }
                            String diaI = "";
                            if(fechaI.get(Calendar.DAY_OF_MONTH) <=9){
                                diaI = "0"+fechaI.get(Calendar.DAY_OF_MONTH);
                            }else{
                                diaI = ""+fechaI.get(Calendar.DAY_OF_MONTH);
                            }
                            fechaInicio_Actividad=anoI+"-"+mesI+"-"+diaI+" "+select_MvtoEquipo_HoraInicial.getSelectedItem().toString()+":"+select_MvtoEquipo_MinutosInicial.getSelectedItem().toString()+":00.0";
                            if(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getFechaHoraFin() != null){//validamos que tenga una fecha de finalizaci??n para hacer la comparaci??n 
                                try{
                                    String[] fechaA= listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getFechaHoraFin().split(" ");
                                    String[] horaB= fechaA[1].split(":");
                                    String fechaFinalizacionTemp=fechaA[0]+" "+horaB[0]+":"+horaB[1]+":00.0";
                                    int resultDosFechas=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorEntreDosFechas("'"+fechaInicio_Actividad+"'","'"+fechaFinalizacionTemp+"'"));
                                    if(resultDosFechas < 0){
                                        validarCampos=false;
                                        JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser mayor a la fecha de Finalizaci??n","Advertencia", JOptionPane.ERROR_MESSAGE );
                                    }else{
                                        if(resultDosFechas ==0){
                                            validarCampos=false;
                                            JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser Igual a la fecha de Finalizaci??n","Advertencia", JOptionPane.ERROR_MESSAGE );
                                        }else{
                                            validarCampos=true;
                                            if(scriptDB.equals("")){
                                                scriptDB +="[me_fecha]='"+fechaInicio_Actividad+"' ,[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"' ,[me_total_minutos]=(SELECT DATEDIFF(minute,'"+fechaInicio_Actividad+"', '"+listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getFechaHoraFin()+"'))\n";
                                                scriptAuditoria +="[me_fecha]='"+fechaInicio_Actividad+"' ,[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"'\n";
                                            }else{
                                                scriptDB +=",[me_fecha]='"+fechaInicio_Actividad+"' ,[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"'\n";
                                                scriptAuditoria +=",[me_fecha]='"+fechaInicio_Actividad+"' ,[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"'\n";
                                            }
                                        }
                                    }
                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }else{// La fecha de finalizaci??n es nula por tal motivo se almacena de manera directa
                                validarCampos=true;
                                if(scriptDB.equals("")){
                                    scriptDB +="[me_fecha]='"+fechaInicio_Actividad+"' ,[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"' \n";
                                    scriptAuditoria +="[me_fecha]='"+fechaInicio_Actividad+"' ,[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"'\n";
                                }else{
                                    scriptDB +=",[me_fecha]='"+fechaInicio_Actividad+"' ,[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"' \n";
                                    scriptAuditoria +=",[me_fecha]='"+fechaInicio_Actividad+"' ,[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"'\n";
                                }

                            }
                        }catch(Exception e){
                            validarCampos=false;
                            JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Inicio","Advertencia",JOptionPane.ERROR_MESSAGE);
                        }   
                    }else{
                        if(check_MvtoEquipo_FinalizacionActividad.isSelected()){//Seleccion?? modificar solo la fecha de finalizaci??n  de la actividad
                            try{    //Almacenamos la fecha de inicio de actividad
                                Calendar fechaF = fechaFinActividad_MvtoEquipo.getCalendar();
                                String anoF = ""+fechaF.get(Calendar.YEAR);
                                String mesF = "";
                                if((fechaF.get(Calendar.MONTH) +1) <=9){
                                    mesF = "0"+(fechaF.get(Calendar.MONTH) + 1);
                                }else{
                                    mesF = ""+(fechaF.get(Calendar.MONTH) + 1);
                                }
                                String diaF = "";
                                if(fechaF.get(Calendar.DAY_OF_MONTH) <=9){
                                    diaF = "0"+fechaF.get(Calendar.DAY_OF_MONTH);
                                }else{
                                    diaF = ""+fechaF.get(Calendar.DAY_OF_MONTH);
                                }
                                fechaFinalizacion_Actividad=anoF+"-"+mesF+"-"+diaF+" "+select_MvtoEquipo_HoraFinal.getSelectedItem().toString()+":"+select_MvtoEquipo_MinutosFinal.getSelectedItem().toString()+":00.0";
                                if(mvtoEquipo.getFechaHoraInicio()!= null){//validamos que tenga una fecha de inicio para hacer la comparaci??n 
                                    try{
                                        String[] fechaA= listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getFechaHoraInicio().split(" ");
                                        String[] horaB= fechaA[1].split(":");
                                        String fechaInicioTemp=fechaA[0]+" "+horaB[0]+":"+horaB[1]+":00.0";
                                        int resultDosFechas=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorEntreDosFechas("'"+fechaInicioTemp+"'","'"+fechaFinalizacion_Actividad+"'"));
                                        if(resultDosFechas < 0){
                                            validarCampos=false;
                                            JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser mayor a la fecha de Finalizaci??n","Advertencia", JOptionPane.ERROR_MESSAGE );
                                        }else{
                                            if(resultDosFechas ==0){
                                                validarCampos=false;
                                                JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser Igual a la fecha de Finalizaci??n","Advertencia", JOptionPane.ERROR_MESSAGE );
                                            }else{
                                                validarCampos=true;
                                                if(scriptDB.equals("")){
                                                    scriptDB +="[me_fecha_hora_fin]='"+fechaFinalizacion_Actividad+"' ,[me_total_minutos]=(SELECT DATEDIFF(minute,'"+listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getFechaHoraInicio()+"', '"+fechaFinalizacion_Actividad+"'))\n";
                                                    scriptAuditoria +="[me_fecha_hora_fin]='"+fechaFinalizacion_Actividad+"'\n";
                                                }else{
                                                    scriptDB +=",[me_fecha_hora_fin]='"+fechaFinalizacion_Actividad+"',[me_total_minutos]=(SELECT DATEDIFF(minute,'"+listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getFechaHoraInicio()+"', '"+fechaFinalizacion_Actividad+"'))\n";
                                                    scriptAuditoria +=",[me_fecha_hora_fin]='"+fechaFinalizacion_Actividad+"'\n";
                                                }
                                            }
                                        }
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }
                                }else{// No habr?? caso donde se tenga fecha de finalizaci??n con fecha de inicio null
                                    /*validarCampos=true;
                                    if(scriptDB.equals("")){
                                        scriptDB +="[me_fecha_hora_fin]='"+fechaFinalizacion_Actividad+"'\n";
                                    }else{
                                        scriptDB +=",[me_fecha_hora_fin]='"+fechaFinalizacion_Actividad+"'\n";
                                    }*/

                                }
                            }catch(Exception e){
                                validarCampos=false;
                                JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Inicio","Advertencia",JOptionPane.ERROR_MESSAGE);
                            }   
                        }
                    }
                }
                /**VALIDAMOS EL EQUIPO ##############################################################**/
                if(validarCampos){// revisamos que el validador sea true
                    if(listadoEquipos_mvtoEquipo != null){//Hay al menos un equipo cargado en la interfaz
                        if(!(listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getCodigo().equals(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getAsignacionEquipo().getCodigo()))){// El equipo que seleccion?? es diferente al registrado en el movimiento de Equipo, por tal motivo se registra dicho cambio
                            if(scriptDB.equals("")){
                                scriptDB +="[me_asignacion_equipo_cdgo]="+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getCodigo()+"\n";
                                //scriptMvtoCarbon +="[me_asignacion_equipo_cdgo]="+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getCodigo()+"\n";
                                scriptDB_listadoCarbonEquipo +="[mcle_asignacion_equipo_cdgo]="+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria +="[me_asignacion_equipo_cdgo]="+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getCodigo()+"- Cod:"+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getEquipo().getCodigo()+" Descripci??n: "+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getEquipo().getDescripcion()+" "+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getEquipo().getModelo()+"\n";
                            }else{
                                scriptDB +=",[me_asignacion_equipo_cdgo]="+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getCodigo()+"\n";
                                scriptDB_listadoCarbonEquipo +="[mcle_asignacion_equipo_cdgo]="+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria +=",[me_asignacion_equipo_cdgo]="+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getCodigo()+"- Cod:"+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getEquipo().getCodigo()+" Descripci??n: "+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getEquipo().getDescripcion()+" "+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getEquipo().getModelo()+"\n";
                            }
                            
                            //if(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoCarbon().getEquipoLavadoVehiculo().get)
                            if(mvtoCarbon.getEquipoLavadoVehiculo().getCodigo() != null){
                                if(mvtoCarbon.getEquipoLavadoVehiculo().getCodigo().equals(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getAsignacionEquipo().getEquipo().getCodigo())){
                                    scriptMvtoCarbon +="[mc_equipo_lavado_cdgo]="+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getEquipo().getCodigo();
                                    scriptMvtoCarbon +=",[mc_costoLavadoVehiculo]=NULL, [mc_valorRecaudoEmpresa_lavadoVehiculo]=NULL,[mc_valorRecaudoEquipo_lavadoVehiculo]=NULL";
                                }
                            }
                        }
                    }
                    else{
                        validarCampos=false;
                        JOptionPane.showMessageDialog(null,"Error!!.. no se encuentran un Equipo asignado en ese periodo de tiempo, Consulte con el administrador del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                    }
                }
                /**VALIDAMOS EL RECOBRO ##############################################################**/
                if(validarCampos){// revisamos que el validador sea true
                    if(listadoRecobros_mvtoEquipo != null){//Hay al menos tenga una opci??n de recobro en la interfaz
                        if(!(listadoRecobros_mvtoEquipo.get(select_MvtoEquipo_recobro.getSelectedIndex()).getCodigo().equals(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getRecobro().getCodigo()))){// El recobro que seleccion?? es diferente al registrado en el movimiento de Equipo, por tal motivo se registra dicho cambio
                            if(scriptDB.equals("")){
                                scriptDB +="[me_recobro_cdgo]="+listadoRecobros_mvtoEquipo.get(select_MvtoEquipo_recobro.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria +="[me_recobro_cdgo]="+listadoRecobros_mvtoEquipo.get(select_MvtoEquipo_recobro.getSelectedIndex()).getCodigo()+"-"+listadoRecobros_mvtoEquipo.get(select_MvtoEquipo_recobro.getSelectedIndex()).getDescripcion()+"\n";
                            }else{
                                scriptDB +=",[me_recobro_cdgo]="+listadoRecobros_mvtoEquipo.get(select_MvtoEquipo_recobro.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria +=",[me_recobro_cdgo]="+listadoRecobros_mvtoEquipo.get(select_MvtoEquipo_recobro.getSelectedIndex()).getCodigo()+"-"+listadoRecobros_mvtoEquipo.get(select_MvtoEquipo_recobro.getSelectedIndex()).getDescripcion()+"\n";
                            }
                        }
                    }else{
                        validarCampos=false;
                       JOptionPane.showMessageDialog(null,"Error!!.. no se encuentran el listado de recobro activo en el sistema, Consulte con el administrador del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                   }
                }
                
                 /**VALIDAMOS EL MOTIVO DE PARADA ##############################################################**/
                if(validarCampos){// revisamos que el validador sea true
                    if(listadoMotivoParada_mvtoEquipo != null){//Hay al menos tenga una opci??n de raz??n de finalizaci??n en la interfaz
                        if(!(listadoMotivoParada_mvtoEquipo.get(mvtoEquipo_selectRazonFinalzaci??n.getSelectedIndex()).getCodigo().equals(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo().getMotivoParada().getCodigo()))){// El recobro que seleccion?? es diferente al registrado en el movimiento de Equipo, por tal motivo se registra dicho cambio
                            if(scriptDB.equals("")){
                                scriptDB +="[me_motivo_parada_estado]=1,[me_motivo_parada_cdgo]="+listadoMotivoParada_mvtoEquipo.get(mvtoEquipo_selectRazonFinalzaci??n.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria +="[me_motivo_parada_estado]=1,[me_motivo_parada_cdgo]="+listadoMotivoParada_mvtoEquipo.get(mvtoEquipo_selectRazonFinalzaci??n.getSelectedIndex()).getCodigo()+"-"+listadoMotivoParada_mvtoEquipo.get(mvtoEquipo_selectRazonFinalzaci??n.getSelectedIndex()).getDescripcion()+"\n";
                            }else{
                                scriptDB +=",[me_motivo_parada_estado]=1,[me_motivo_parada_cdgo]="+listadoMotivoParada_mvtoEquipo.get(mvtoEquipo_selectRazonFinalzaci??n.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria +=",[me_motivo_parada_estado]=1,[me_motivo_parada_cdgo]="+listadoMotivoParada_mvtoEquipo.get(mvtoEquipo_selectRazonFinalzaci??n.getSelectedIndex()).getCodigo()+"-"+listadoMotivoParada_mvtoEquipo.get(mvtoEquipo_selectRazonFinalzaci??n.getSelectedIndex()).getDescripcion()+"\n";
                            }
                        }
                    }else{
                        //validarCampos=false;
                       //JOptionPane.showMessageDialog(null,"Error!!.. no se encuentran el listado de raz??n de motivaci??n activo en el sistema, Consulte con el administrador del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                   }
                }
                if(validarCampos){
                    if(listadoUsuarioInicioRegistro_mvtoEquipo != null){
                        if(check_MvtoEquipo_UsuarioIniciaActividad.isSelected()){
                            if(scriptDB.equals("")){
                                scriptDB +="[me_usuario_registro_cdgo]="+listadoUsuarioInicioRegistro_mvtoEquipo.get(select_MvtoEquipo_UsuarioIniciaRegistro.getSelectedIndex()).getCodigo()+" \n";
                                scriptAuditoria +="[me_usuario_registro_cdgo]="+listadoUsuarioInicioRegistro_mvtoEquipo.get(select_MvtoEquipo_UsuarioIniciaRegistro.getSelectedIndex()).getCodigo()+" \n";
                            }else{
                                scriptDB +=",[me_usuario_registro_cdgo]="+listadoUsuarioInicioRegistro_mvtoEquipo.get(select_MvtoEquipo_UsuarioIniciaRegistro.getSelectedIndex()).getCodigo()+" \n";
                                scriptAuditoria +=",[me_usuario_registro_cdgo]="+listadoUsuarioInicioRegistro_mvtoEquipo.get(select_MvtoEquipo_UsuarioIniciaRegistro.getSelectedIndex()).getCodigo()+" \n"; 
                            }
                        }
                    }
                    if(listadoUsuarioCierraRegistro_mvtoEquipo != null){
                        if(check_MvtoEquipo_UsuarioCierraActividad.isSelected()){
                            if(scriptDB.equals("")){
                                scriptDB +="[me_usuario_cierre_cdgo]="+listadoUsuarioCierraRegistro_mvtoEquipo.get(select_MvtoEquipo_UsuarioCierraRegistro.getSelectedIndex()).getCodigo()+" \n";
                                scriptAuditoria +="[me_usuario_cierre_cdgo]="+listadoUsuarioCierraRegistro_mvtoEquipo.get(select_MvtoEquipo_UsuarioCierraRegistro.getSelectedIndex()).getCodigo()+" \n";
                            }else{
                                scriptDB +=",[me_usuario_cierre_cdgo]="+listadoUsuarioCierraRegistro_mvtoEquipo.get(select_MvtoEquipo_UsuarioCierraRegistro.getSelectedIndex()).getCodigo()+" \n";
                                scriptAuditoria +=",[me_usuario_cierre_cdgo]="+listadoUsuarioCierraRegistro_mvtoEquipo.get(select_MvtoEquipo_UsuarioCierraRegistro.getSelectedIndex()).getCodigo()+" \n";
                            }
                        }
                    }
                }  
                
                
                
                
                
                System.out.println("\n\n");
                System.out.println(scriptDB); 
                System.out.println(scriptAuditoria);
                if(validarCampos){// revisamos que el validador sea true
                    System.out.println("========================>"+scriptMvtoCarbon);
                    int retorno=0;
                    try {
                        if(scriptDB.equals("")){
                            scriptDB +="[me_valor_hora]=NULL, [me_costo_total]=NULL,[me_cntro_cost_cdgo]=NULL,[me_cntro_cost]=NULL,[me_cntro_cost_mayor_cdgo]=NULL \n";
                        }else{
                            scriptDB +=",[me_valor_hora]=NULL, [me_costo_total]=NULL,[me_cntro_cost_cdgo]=NULL,[me_cntro_cost]=NULL,[me_cntro_cost_mayor_cdgo]=NULL \n";
                        }
                        retorno = new ControlDB_MvtoEquipo(tipoConexion).modificarMvtoEquipoCarbon(scriptDB_listadoCarbonEquipo,listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()).getMvtoEquipo(), user,scriptDB ,scriptAuditoria,textArea_MvtoEquipo_razonModificacion.getText());
                        
                    
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SocketException ex) {
                        Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(retorno==1){
                        MvtoEquipo mvtoEquipoTemporal=new ControlDB_MvtoEquipo(tipoConexion).procesarMvtoEquipoParticular(mvtoEquipo);
                        new ControlDB_MvtoEquipo(tipoConexion).Procesar_MvtoEquipo(mvtoEquipoTemporal,user);
                        
                        
                        if(!scriptMvtoCarbon.equals("")){
                            retorno=0;
                            retorno = new ControlDB_MvtoCarbon(tipoConexion).modificarEquipoLavado_MvtoCarbon(mvtoCarbon, user,scriptMvtoCarbon ,scriptMvtoCarbon,textArea_MvtoEquipo_razonModificacion.getText()); 
                            if(retorno==1){
                                System.out.println("Voy a ProcesarEnCcargaGP");
                                if(retorno==1){
                                    try{
                                        ArrayList<MvtoCarbon_ListadoEquipos> mvtoCarbon_ListadoEquiposTemp= new ControlDB_MvtoCarbon(tipoConexion).buscarMvtoCarbonParticular(mvtoCarbon);
                                        for(MvtoCarbon_ListadoEquipos listado : mvtoCarbon_ListadoEquiposTemp){
                                            new ControlDB_MvtoCarbon(tipoConexion).ProcesarEnCcargaGP(listado,user);
                                        }
                                       retorno=1;   
                                    }catch(Exception e){
                                        retorno=0;
                                    }
                                }
                               // new ControlDB_MvtoCarbon(tipoConexion).ProcesarEnCcargaGP(listadoMvtoCarbon_ListadoEquipos.get(listadoEquiposDescargue1.getSelectedIndex()),user);
                            }
                        } 
                        
                        JOptionPane.showMessageDialog(null, "Se registro la modificaci??n del registro de forma exitosa", "Registro Exitoso",JOptionPane.INFORMATION_MESSAGE);
                        check_MvtoCarbon_InicioActividad.setSelected(false);
                        check_MvtoCarbon_FinalizacionActividad.setSelected(false);
                        select_MvtoEquipo_laborRealizada.setText("");
                        check_MvtoCarbon_UsuarioIniciaActividad.setSelected(false);
                        check_MvtoCarbon_UsuarioCierraActividad.setSelected(false);
                        textArea_MvtoCarbon_razonModificacion.setText("");
                        
                        check_MvtoEquipo_InicioActividad.setSelected(false);
                        check_MvtoEquipo_FinalizacionActividad.setSelected(false);
                        textArea_MvtoEquipo_razonModificacion.setText("");
                        
                        paginationPanel.removeAll();
                        validarSeleccionCampos();
                        generarListadoMvtoCarbon();
                        resizeColumnWidth(tabla);
                        InternaFrame_VisualizarMvtoCarbon.show(false);     
                    }else{
                        JOptionPane.showMessageDialog(null, "No se puedo registrar la modificaci??n del registro, valide datos", "Error al registrar",JOptionPane.ERROR_MESSAGE);
                    }    
                }else{
                    JOptionPane.showMessageDialog(null, "No se puedo registrar la modificaci??n del registro, valide datos", "Error al registrar",JOptionPane.ERROR_MESSAGE);    
                }       
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"No se pudo realizar la modificaci??n, verifique informaci??n suministrada","Error!!!",JOptionPane.ERROR_MESSAGE);
            } 
        }else{
            JOptionPane.showMessageDialog(null,"La raz??n de modificaci??n no puede estar vacia, valide la informaci??n suministrada","Error!!!",JOptionPane.ERROR_MESSAGE);  
        }  
    }//GEN-LAST:event_registrar_MvtoEquipoActionPerformed

    private void registrar_MvtoCarbonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrar_MvtoCarbonActionPerformed
       //Asignamos un MvtoEquipo temporal para validar campos
       if(mvtoCarbon != null){
        if(!textArea_MvtoCarbon_razonModificacion.getText().equals("")){
            boolean validarCampos= true;
            String scriptDB_MvtoCarbon="";
            String scriptDB_MvtoEquipo="";
            String scriptAuditoria_mtvoCarbon="";
            String scriptAuditoria_mtvoEquipo="";
            try{
                // MvtoEquipo mvtoEquipoTemp = mvtoEquipo;
                //validamos las fechas si son iguales
                String fechaInicio_Actividad="";
                String fechaFinalizacion_Actividad="";

                /**VALIDAMOS LAS FECHAS ##############################################################**/
                if(check_MvtoCarbon_InicioActividad.isSelected() && check_MvtoCarbon_FinalizacionActividad.isSelected()){//Seleccion?? modificar la fecha de Inicio descargue y la fecha de Finalizaci??n del descargue
                    try{    //Almacenamos la fecha de inicio de actividad
                        Calendar fechaI = fechaInicialActividad_MvtoCarbon.getCalendar();
                        String anoI = ""+fechaI.get(Calendar.YEAR);
                        String mesI = "";
                        if((fechaI.get(Calendar.MONTH) +1) <=9){
                            mesI = "0"+(fechaI.get(Calendar.MONTH) + 1);
                        }else{
                            mesI = ""+(fechaI.get(Calendar.MONTH) + 1);
                        }
                        String diaI = "";
                        if(fechaI.get(Calendar.DAY_OF_MONTH) <=9){
                            diaI = "0"+fechaI.get(Calendar.DAY_OF_MONTH);
                        }else{
                            diaI = ""+fechaI.get(Calendar.DAY_OF_MONTH);
                        }
                        fechaInicio_Actividad=anoI+"-"+mesI+"-"+diaI+" "+select_MvtoCarbon_HoraInicial.getSelectedItem().toString()+":"+select_MvtoCarbon_MinutosInicial.getSelectedItem().toString()+":00.0";
                        try{        //Almacenamos la fecha de finalizaci??n de actividad   
                            Calendar fechaF = fechaFinActividad_MvtoCarbon.getCalendar();
                            String anoF = ""+fechaF.get(Calendar.YEAR);
                            String mesF = "";
                            if((fechaF.get(Calendar.MONTH) +1) <=9){
                                mesF = "0"+(fechaF.get(Calendar.MONTH) + 1);
                            }else{
                                mesF = ""+(fechaF.get(Calendar.MONTH) + 1);
                            }
                            String diaF = "";
                            if(fechaF.get(Calendar.DAY_OF_MONTH) <=9){
                                diaF = "0"+fechaF.get(Calendar.DAY_OF_MONTH);
                            }else{
                                diaF = ""+fechaF.get(Calendar.DAY_OF_MONTH);
                            }
                            fechaFinalizacion_Actividad=anoF+"-"+mesF+"-"+diaF+" "+select_MvtoCarbon_HoraFinal.getSelectedItem().toString()+":"+select_MvtoCarbon_MinutosFinal.getSelectedItem().toString()+":00.0";
                            int resultDosFechas=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorEntreDosFechas("'"+fechaInicio_Actividad+"'","'"+fechaFinalizacion_Actividad+"'"));
                            if(resultDosFechas < 0){
                                validarCampos=false;
                                JOptionPane.showMessageDialog(null, "Error!!.. La fecha de inicio del descargue no puede ser mayor que la fecha de finalizaci??n del descargue","Advertencia", JOptionPane.ERROR_MESSAGE );
                            }else{
                                if(resultDosFechas ==0){
                                    validarCampos=false;
                                    JOptionPane.showMessageDialog(null, "Error!!.. La fecha de inicio del descargue no puede ser Igual a la fecha de finalizaci??n del descargue","Advertencia", JOptionPane.ERROR_MESSAGE );
                                }else{
                                    validarCampos=true;
                                    if(scriptDB_MvtoCarbon.equals("")){
                                        scriptDB_MvtoCarbon +="[mc_fecha]='"+fechaInicio_Actividad+"', [mc_fecha_inicio_descargue]='"+fechaInicio_Actividad+"' , [mc_fecha_fin_descargue]='"+fechaFinalizacion_Actividad+"' \n";
                                        scriptAuditoria_mtvoCarbon +="[mc_fecha]='"+fechaInicio_Actividad+"',[mc_fecha_inicio_descargue]='"+fechaInicio_Actividad+"' , [mc_fecha_fin_descargue]='"+fechaFinalizacion_Actividad+"'\n";
                                    }else{
                                        scriptDB_MvtoCarbon +=",[mc_fecha]='"+fechaInicio_Actividad+"',[mc_fecha_inicio_descargue]='"+fechaInicio_Actividad+"' , [mc_fecha_fin_descargue]='"+fechaFinalizacion_Actividad+"'\n";
                                        scriptAuditoria_mtvoCarbon +=",[mc_fecha]='"+fechaInicio_Actividad+"',[mc_fecha_inicio_descargue]='"+fechaInicio_Actividad+"' , [mc_fecha_fin_descargue]='"+fechaFinalizacion_Actividad+"'\n";
                                    }
                                }
                            }
                            }catch(Exception e){
                            validarCampos=false;
                            JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la fecha de finalizaci??n del descargue","Advertencia",JOptionPane.ERROR_MESSAGE);
                        }
                    }catch(Exception e){
                        validarCampos=false;
                        JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la fecha de inicio del descargue","Advertencia",JOptionPane.ERROR_MESSAGE);
                    }                        
                }else{
                    if(check_MvtoCarbon_InicioActividad.isSelected()){ //Seleccion?? modificar solo la fecha de inicio de actividad
                        try{    //Almacenamos la fecha de inicio de actividad
                            Calendar fechaI = fechaInicialActividad_MvtoCarbon.getCalendar();
                            String anoI = ""+fechaI.get(Calendar.YEAR);
                            String mesI = "";
                            if((fechaI.get(Calendar.MONTH) +1) <=9){
                                mesI = "0"+(fechaI.get(Calendar.MONTH) + 1);
                            }else{
                                mesI = ""+(fechaI.get(Calendar.MONTH) + 1);
                            }
                            String diaI = "";
                            if(fechaI.get(Calendar.DAY_OF_MONTH) <=9){
                                diaI = "0"+fechaI.get(Calendar.DAY_OF_MONTH);
                            }else{
                                diaI = ""+fechaI.get(Calendar.DAY_OF_MONTH);
                            }
                            fechaInicio_Actividad=anoI+"-"+mesI+"-"+diaI+" "+select_MvtoCarbon_HoraInicial.getSelectedItem().toString()+":"+select_MvtoCarbon_MinutosInicial.getSelectedItem().toString()+":00.0";
                            if(mvtoCarbon.getFechaFinDescargue() != null){//validamos que tenga una fecha de finalizaci??n para hacer la comparaci??n 
                                try{
                                    String[] fechaA= mvtoCarbon.getFechaFinDescargue().split(" ");
                                    String[] horaB= fechaA[1].split(":");
                                    String fechaFinalizacionTemp=fechaA[0]+" "+horaB[0]+":"+horaB[1]+":00.0";
                                    int resultDosFechas=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorEntreDosFechas("'"+fechaInicio_Actividad+"'","'"+fechaFinalizacionTemp+"'"));
                                    if(resultDosFechas < 0){
                                        validarCampos=false;
                                        JOptionPane.showMessageDialog(null, "Error!!.. La fecha de inicio del descargue no puede ser mayor a la fecha de finalizaci??n del descargue","Advertencia", JOptionPane.ERROR_MESSAGE );
                                    }else{
                                        if(resultDosFechas ==0){
                                            validarCampos=false;
                                            JOptionPane.showMessageDialog(null, "Error!!.. La fecha de inicio del descargue no puede ser Igual a la fecha de finalizaci??n","Advertencia", JOptionPane.ERROR_MESSAGE );
                                        }else{
                                            validarCampos=true;
                                            if(scriptDB_MvtoCarbon.equals("")){
                                                scriptDB_MvtoCarbon +="[mc_fecha]='"+fechaInicio_Actividad+"',[mc_fecha_inicio_descargue]='"+fechaInicio_Actividad+"'\n";
                                                scriptAuditoria_mtvoCarbon +="[mc_fecha]='"+fechaInicio_Actividad+"',[mc_fecha_inicio_descargue]='"+fechaInicio_Actividad+"'\n";
                                            }else{
                                                scriptDB_MvtoCarbon +=",[mc_fecha]='"+fechaInicio_Actividad+"',[mc_fecha_inicio_descargue]='"+fechaInicio_Actividad+"'\n";
                                                scriptAuditoria_mtvoCarbon +=",[mc_fecha]='"+fechaInicio_Actividad+"',[mc_fecha_inicio_descargue]='"+fechaInicio_Actividad+"'\n";
                                            }
                                        }
                                    }
                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }else{// La fecha de finalizaci??n es nula por tal motivo se almacena de manera directa
                                validarCampos=true;
                                if(scriptDB_MvtoCarbon.equals("")){
                                    scriptDB_MvtoCarbon +="[mc_fecha]='"+fechaInicio_Actividad+"',[mc_fecha_inicio_descargue]='"+fechaInicio_Actividad+"' \n";
                                    scriptAuditoria_mtvoCarbon +="[mc_fecha]='"+fechaInicio_Actividad+"',[mc_fecha_inicio_descargue]='"+fechaInicio_Actividad+"'\n";
                                }else{
                                    scriptDB_MvtoCarbon +=",[mc_fecha]='"+fechaInicio_Actividad+"',[mc_fecha_inicio_descargue]='"+fechaInicio_Actividad+"' \n";
                                    scriptAuditoria_mtvoCarbon +=",[mc_fecha]='"+fechaInicio_Actividad+"',[mc_fecha_inicio_descargue]='"+fechaInicio_Actividad+"'\n";
                                }

                            }
                        }catch(Exception e){
                            validarCampos=false;
                            JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la fecha de Inicio del descargue","Advertencia",JOptionPane.ERROR_MESSAGE);
                        }   
                    }else{
                        if(check_MvtoCarbon_FinalizacionActividad.isSelected()){//Seleccion?? modificar solo la fecha de finalizaci??n  de la actividad
                            try{    //Almacenamos la fecha de inicio de actividad
                                Calendar fechaF = fechaFinActividad_MvtoCarbon.getCalendar();
                                String anoF = ""+fechaF.get(Calendar.YEAR);
                                String mesF = "";
                                if((fechaF.get(Calendar.MONTH) +1) <=9){
                                    mesF = "0"+(fechaF.get(Calendar.MONTH) + 1);
                                }else{
                                    mesF = ""+(fechaF.get(Calendar.MONTH) + 1);
                                }
                                String diaF = "";
                                if(fechaF.get(Calendar.DAY_OF_MONTH) <=9){
                                    diaF = "0"+fechaF.get(Calendar.DAY_OF_MONTH);
                                }else{
                                    diaF = ""+fechaF.get(Calendar.DAY_OF_MONTH);
                                }
                                fechaFinalizacion_Actividad=anoF+"-"+mesF+"-"+diaF+" "+select_MvtoCarbon_HoraFinal.getSelectedItem().toString()+":"+select_MvtoCarbon_MinutosFinal.getSelectedItem().toString()+":00.0";
                                if(mvtoCarbon.getFechaInicioDescargue()!= null){//validamos que tenga una fecha de inicio para hacer la comparaci??n 
                                    try{
                                        String[] fechaA= mvtoCarbon.getFechaInicioDescargue().split(" ");
                                        String[] horaB= fechaA[1].split(":");
                                        String fechaInicioTemp=fechaA[0]+" "+horaB[0]+":"+horaB[1]+":00.0";
                                        int resultDosFechas=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorEntreDosFechas("'"+fechaInicioTemp+"'","'"+fechaFinalizacion_Actividad+"'"));
                                        if(resultDosFechas < 0){
                                            validarCampos=false;
                                            JOptionPane.showMessageDialog(null, "Error!!.. La fecha de inicio de descargue no puede ser mayor a la fecha de finalizaci??n","Advertencia", JOptionPane.ERROR_MESSAGE );
                                        }else{
                                            if(resultDosFechas ==0){
                                                validarCampos=false;
                                                JOptionPane.showMessageDialog(null, "Error!!.. La fecha de inicio de descargue no puede ser igual a la fecha de finalizaci??n","Advertencia", JOptionPane.ERROR_MESSAGE );
                                            }else{
                                                validarCampos=true;
                                                if(scriptDB_MvtoCarbon.equals("")){
                                                    scriptDB_MvtoCarbon +="[mc_fecha_fin_descargue]='"+fechaFinalizacion_Actividad+"'\n";
                                                    scriptAuditoria_mtvoCarbon +="[mc_fecha_fin_descargue]='"+fechaFinalizacion_Actividad+"'\n";
                                                }else{
                                                    scriptDB_MvtoCarbon +=",[mc_fecha_fin_descargue]='"+fechaFinalizacion_Actividad+"'\n";
                                                    scriptAuditoria_mtvoCarbon +=",[mc_fecha_fin_descargue]='"+fechaFinalizacion_Actividad+"'\n";
                                                }
                                            }
                                        }
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }
                                }else{// No habr?? caso donde se tenga fecha de finalizaci??n con fecha de inicio null
                                    /*validarCampos=true;
                                    if(scriptDB.equals("")){
                                        scriptDB +="[me_fecha_hora_fin]='"+fechaFinalizacion_Actividad+"'\n";
                                    }else{
                                        scriptDB +=",[me_fecha_hora_fin]='"+fechaFinalizacion_Actividad+"'\n";
                                    }*/

                                }
                            }catch(Exception e){
                                validarCampos=false;
                                JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Inicio","Advertencia",JOptionPane.ERROR_MESSAGE);
                            }   
                        }
                    }
                }

               
                /**VALIDAMOS EL CENTRO DE OPERACI??N ##############################################################**/
                if(validarCampos){// revisamos que el validador sea true
                    if(listadoCentroOperacion_mvtoCarbon != null){//Hay al menos un Centro de Operaci??n Cargado en la interfaz
                        if(!(listadoCentroOperacion_mvtoCarbon.get(select_MvtoCarbon_CO.getSelectedIndex()).getCodigo() == mvtoCarbon.getCentroOperacion().getCodigo())){// El centro de operaci??n que seleccion?? es diferente al registrado en el movimiento de carb??n, por tal motivo se registra dicho cambio
                            if(scriptDB_MvtoCarbon.equals("")){
                                scriptDB_MvtoCarbon +="[mc_cntro_oper_cdgo]="+listadoCentroOperacion_mvtoCarbon.get(select_MvtoCarbon_CO.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria_mtvoCarbon +="[mc_cntro_oper_cdgo]="+listadoCentroOperacion_mvtoCarbon.get(select_MvtoCarbon_CO.getSelectedIndex()).getCodigo()+"-"+listadoCentroOperacion_mvtoCarbon.get(select_MvtoCarbon_CO.getSelectedIndex()).getDescripcion()+"\n";
                            }else{
                                scriptDB_MvtoCarbon +=",[mc_cntro_oper_cdgo]="+listadoCentroOperacion_mvtoCarbon.get(select_MvtoCarbon_CO.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria_mtvoCarbon +=",[mc_cntro_oper_cdgo]="+listadoCentroOperacion_mvtoCarbon.get(select_MvtoCarbon_CO.getSelectedIndex()).getCodigo()+"-"+listadoCentroOperacion_mvtoCarbon.get(select_MvtoCarbon_CO.getSelectedIndex()).getDescripcion()+"\n";
                            }
                            if(scriptDB_MvtoEquipo.equals("")){
                                scriptDB_MvtoEquipo +="[me_cntro_oper_cdgo]="+listadoCentroOperacion_mvtoCarbon.get(select_MvtoCarbon_CO.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria_mtvoEquipo +="[me_cntro_oper_cdgo]="+listadoCentroOperacion_mvtoCarbon.get(select_MvtoCarbon_CO.getSelectedIndex()).getCodigo()+"-"+listadoCentroOperacion_mvtoCarbon.get(select_MvtoCarbon_CO.getSelectedIndex()).getDescripcion()+"\n";
                            }else{
                                scriptDB_MvtoEquipo +=",[me_cntro_oper_cdgo]="+listadoCentroOperacion_mvtoCarbon.get(select_MvtoCarbon_CO.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria_mtvoEquipo +=",[me_cntro_oper_cdgo]="+listadoCentroOperacion_mvtoCarbon.get(select_MvtoCarbon_CO.getSelectedIndex()).getCodigo()+"-"+listadoCentroOperacion_mvtoCarbon.get(select_MvtoCarbon_CO.getSelectedIndex()).getDescripcion()+"\n";
                            }
                        }
                    }else{
                        validarCampos=false;
                        JOptionPane.showMessageDialog(null,"Error!!.. no se encuentra Centro de Operaci??n activos en el sistema, Consulte con el administrador del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                    }
                }
                /**VALIDAMOS EL AUXILIAR DE CENTRO DE COSTO##############################################################**/
                if(validarCampos){// revisamos que el validador sea true
                    if(listadoCentroCostoAuxiliar_mvtoCarbon != null){//Hay al menos un centro de costo auxiliar cargado en la interfaz
                        if(!(listadoCentroCostoAuxiliar_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliar.getSelectedIndex()).getCodigo() == mvtoCarbon.getCentroCostoAuxiliar().getCodigo())){// El centro de costo auxiliar que seleccion?? es diferente al registrado en el movimiento de Equipo, por tal motivo se registra dicho cambio
                            if(scriptDB_MvtoCarbon.equals("")){
                                scriptDB_MvtoCarbon+="[mc_cntro_cost_auxiliar_cdgo]="+listadoCentroCostoAuxiliar_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliar.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria_mtvoCarbon +="[mc_cntro_cost_auxiliar_cdgo]="+listadoCentroCostoAuxiliar_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliar.getSelectedIndex()).getCodigo()+"-"+listadoCentroCostoAuxiliar_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliar.getSelectedIndex()).getDescripcion()+"\n";
                            }else{
                                scriptDB_MvtoCarbon +=",[mc_cntro_cost_auxiliar_cdgo]="+listadoCentroCostoAuxiliar_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliar.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria_mtvoCarbon +=",[mc_cntro_cost_auxiliar_cdgo]="+listadoCentroCostoAuxiliar_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliar.getSelectedIndex()).getCodigo()+"-"+listadoCentroCostoAuxiliar_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliar.getSelectedIndex()).getDescripcion()+"\n";
                            }
                            if(scriptDB_MvtoEquipo.equals("")){
                                scriptDB_MvtoEquipo+="[me_cntro_cost_auxiliar_cdgo]="+listadoCentroCostoAuxiliar_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliar.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria_mtvoEquipo +="[me_cntro_cost_auxiliar_cdgo]="+listadoCentroCostoAuxiliar_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliar.getSelectedIndex()).getCodigo()+"-"+listadoCentroCostoAuxiliar_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliar.getSelectedIndex()).getDescripcion()+"\n";
                            }else{
                                scriptDB_MvtoEquipo +=",[me_cntro_cost_auxiliar_cdgo]="+listadoCentroCostoAuxiliar_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliar.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria_mtvoEquipo +=",[me_cntro_cost_auxiliar_cdgo]="+listadoCentroCostoAuxiliar_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliar.getSelectedIndex()).getCodigo()+"-"+listadoCentroCostoAuxiliar_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliar.getSelectedIndex()).getDescripcion()+"\n";
                            }
                        }
                    }else{
                        validarCampos=false;
                        JOptionPane.showMessageDialog(null,"Error!!.. no se encuentran un Centro de costo auxiliar activo en el sistema, Consulte con el administrador del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                    }
                }
                /**VALIDAMOS LA LABOR REALIZADA ##############################################################**/
                if(validarCampos){// revisamos que el validador sea true
                    if(listadoLaborRealizada_mvtoCarbon != null){//Hay al menos un Subcentro de costo cargado en la interfaz
                        if(!(listadoLaborRealizada_mvtoCarbon.get(select_MvtoCarbon_laborRealizada.getSelectedIndex()).getCodigo().equals(mvtoCarbon.getLaborRealizada().getCodigo()))){// La labor realizada que seleccion?? es diferente a la registrada en el movimiento de Equipo, por tal motivo se registra dicho cambio
                            if(scriptDB_MvtoCarbon.equals("")){
                                scriptDB_MvtoCarbon +="[mc_labor_realizada_cdgo]="+listadoLaborRealizada_mvtoCarbon.get(select_MvtoCarbon_laborRealizada.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria_mtvoCarbon +="[mc_labor_realizada_cdgo]='"+listadoLaborRealizada_mvtoCarbon.get(select_MvtoCarbon_laborRealizada.getSelectedIndex()).getCodigo()+"-"+listadoLaborRealizada_mvtoCarbon.get(select_MvtoCarbon_laborRealizada.getSelectedIndex()).getDescripcion()+"'\n";
                            }else{
                                scriptDB_MvtoCarbon +=",[mc_labor_realizada_cdgo]="+listadoLaborRealizada_mvtoCarbon.get(select_MvtoCarbon_laborRealizada.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria_mtvoCarbon +=",[mc_labor_realizada_cdgo]='"+listadoLaborRealizada_mvtoCarbon.get(select_MvtoCarbon_laborRealizada.getSelectedIndex()).getCodigo()+"-"+listadoLaborRealizada_mvtoCarbon.get(select_MvtoCarbon_laborRealizada.getSelectedIndex()).getDescripcion()+"'\n";
                            }
                        }
                    }else{
                        validarCampos=false;
                        JOptionPane.showMessageDialog(null,"Error!!.. no se encuentran una labor a Realizar activa en el sistema, Consulte con el administrador del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                    }
                }
                /**VALIDAMOS EL CENTRO DE COSTO AUXILIAR DESTINO ##############################################################**/
                try{
                    System.out.println("listadoCentroCostoAuxiliarDestino_mvtoCarbon="+listadoCentroCostoAuxiliarDestino_mvtoCarbon.size());
                }catch(Exception e){
                    System.out.println("listadoCentroCostoAuxiliarDestino_mvtoCarbon=NULL");
                }
                if(validarCampos){// revisamos que el validador sea true
                    if(listadoCentroCostoAuxiliarDestino_mvtoCarbon != null){//Hay al menos un Subcentro de costo cargado en la interfaz
                        if(mvtoCarbon.getCentroCostoAuxiliarDestino() != null){
                            if(!(listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getCodigo() == mvtoCarbon.getCentroCostoAuxiliarDestino().getCodigo())){// El centro de costo auxiliar destino que seleccion?? es diferente al registrado en el movimiento de Equipo, por tal motivo se registra dicho cambio
                                if(scriptDB_MvtoCarbon.equals("")){
                                    scriptDB_MvtoCarbon +="[mc_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"'\n";
                                    scriptAuditoria_mtvoCarbon +="[mc_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"-"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getDescripcion()+"'\n";
                                }else{
                                    scriptDB_MvtoCarbon +=",[mc_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"'\n";
                                    scriptAuditoria_mtvoCarbon +=",[mc_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"-"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getDescripcion()+"'\n";
                                }
                                if(scriptDB_MvtoEquipo.equals("")){
                                    scriptDB_MvtoEquipo +="[me_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"'\n";
                                    scriptAuditoria_mtvoEquipo +="[me_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"-"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getDescripcion()+"'\n";
                                }else{
                                    scriptDB_MvtoEquipo +=",[me_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"'\n";
                                    scriptAuditoria_mtvoEquipo +=",[me_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"-"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getDescripcion()+"'\n";
                                }
                            }
                        }else{
                            if(scriptDB_MvtoCarbon.equals("")){
                                scriptDB_MvtoCarbon +="[mc_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"'\n";
                                scriptAuditoria_mtvoCarbon +="[mc_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"-"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getDescripcion()+"'\n";
                            }else{
                                scriptDB_MvtoCarbon +=",[mc_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"'\n";
                                scriptAuditoria_mtvoCarbon +=",[mc_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"-"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getDescripcion()+"'\n";
                            }
                            if(scriptDB_MvtoEquipo.equals("")){
                                scriptDB_MvtoEquipo +="[me_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"'\n";
                                scriptAuditoria_mtvoEquipo +="[me_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"-"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getDescripcion()+"'\n";
                            }else{
                                scriptDB_MvtoEquipo +=",[me_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"'\n";
                                scriptAuditoria_mtvoEquipo +=",[me_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"-"+listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()).getDescripcion()+"'\n";
                            }        
                        }
                    }else{
                        if(mvtoCarbon.getCentroCostoAuxiliarDestino().getDescripcion() != null){
                            if(scriptDB_MvtoCarbon.equals("")){
                                scriptDB_MvtoCarbon +="[mc_cntro_cost_auxiliarDestino_cdgo]=NULL \n";
                                scriptAuditoria_mtvoCarbon +="[mc_cntro_cost_auxiliarDestino_cdgo]=NULL \n";
                            }else{
                                scriptDB_MvtoCarbon +=",[mc_cntro_cost_auxiliarDestino_cdgo]=NULL \n";
                                scriptAuditoria_mtvoCarbon +=",[mc_cntro_cost_auxiliarDestino_cdgo]=NULL \n";
                            }
                            if(scriptDB_MvtoEquipo.equals("")){
                                scriptDB_MvtoEquipo +="[me_cntro_cost_auxiliarDestino_cdgo]=NULL \n";
                                scriptAuditoria_mtvoEquipo +="[me_cntro_cost_auxiliarDestino_cdgo]= NULL \n";
                            }else{
                                scriptDB_MvtoEquipo +=",[me_cntro_cost_auxiliarDestino_cdgo]=NULL \n";
                                scriptAuditoria_mtvoEquipo +=",[me_cntro_cost_auxiliarDestino_cdgo]=NULL \n";
                            }
                        }
                    }
                }
                /**VALIDAMOS EL LAVADO DE VEH??CULO ##############################################################**/
                if(validarCampos){// revisamos que el validador sea true
                    if(mvtoCarbon.getLavadoVehiculo() != null){
                        if(!mvtoCarbon.getLavadoVehiculo().equals(select_MvtoCarbon_lavadoVeh??culo.getSelectedItem().toString())){
                            if(select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex() ==0){//Seleccion?? que no se realiz?? lavado de veh??culos
                                if(scriptDB_MvtoCarbon.equals("")){
                                    scriptDB_MvtoCarbon +="[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+"\n";
                                    scriptAuditoria_mtvoCarbon +="[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+" \n";
                                    
                                    scriptDB_MvtoCarbon +=",[mc_motivo_nolavado_vehiculo_cdgo]="+ listadoMotivoNolavadoVehiculo.get(select_MvtoCarbon_motivoNoLavadoVehiculo.getSelectedIndex()).getCodigo()+"\n";
                                    scriptAuditoria_mtvoCarbon +=",[mc_motivo_nolavado_vehiculo_cdgo]="+ listadoMotivoNolavadoVehiculo.get(select_MvtoCarbon_motivoNoLavadoVehiculo.getSelectedIndex()).getCodigo()+"\n";
                                  
                                    scriptDB_MvtoCarbon +=",[mc_equipo_lavado_cdgo]=NULL ,[mc_costoLavadoVehiculo]=NULL ,[mc_valorRecaudoEmpresa_lavadoVehiculo]=NULL,[mc_valorRecaudoEquipo_lavadoVehiculo]=NULL \n";
                                    scriptAuditoria_mtvoCarbon +=",[mc_equipo_lavado_cdgo]=NULL ,[mc_costoLavadoVehiculo]=NULL ,[mc_valorRecaudoEmpresa_lavadoVehiculo]=NULL,[mc_valorRecaudoEquipo_lavadoVehiculo]=NULL \n";   
                                }else{
                                    scriptDB_MvtoCarbon +=",[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+"\n";
                                    scriptAuditoria_mtvoCarbon +=",[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+" \n";
                                    
                                    scriptDB_MvtoCarbon +=",[mc_motivo_nolavado_vehiculo_cdgo]="+ listadoMotivoNolavadoVehiculo.get(select_MvtoCarbon_motivoNoLavadoVehiculo.getSelectedIndex()).getCodigo()+"\n";
                                    scriptAuditoria_mtvoCarbon +=",[mc_motivo_nolavado_vehiculo_cdgo]="+ listadoMotivoNolavadoVehiculo.get(select_MvtoCarbon_motivoNoLavadoVehiculo.getSelectedIndex()).getCodigo()+"\n";
                                  
                                    scriptDB_MvtoCarbon +=",[mc_equipo_lavado_cdgo]=NULL ,[mc_costoLavadoVehiculo]=NULL ,[mc_valorRecaudoEmpresa_lavadoVehiculo]=NULL,[mc_valorRecaudoEquipo_lavadoVehiculo]=NULL \n";
                                    scriptAuditoria_mtvoCarbon +=",[mc_equipo_lavado_cdgo]=NULL ,[mc_costoLavadoVehiculo]=NULL ,[mc_valorRecaudoEmpresa_lavadoVehiculo]=NULL,[mc_valorRecaudoEquipo_lavadoVehiculo]=NULL \n"; 
                               }
                            }else{//El usuario seleccion?? que si se realiz?? lavado de veh??culo por tal motivo validamos que equipo realiz?? el lavado de veh??culo
                                if(scriptDB_MvtoCarbon.equals("")){
                                    scriptDB_MvtoCarbon +="[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+"\n";
                                    scriptAuditoria_mtvoCarbon +="[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+" \n";
                                    
                                    scriptDB_MvtoCarbon +=",[mc_equipo_lavado_cdgo]="+ listadoMvtoCarbon_ListadoEquipos.get(select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.getSelectedIndex()).getAsignacionEquipo().getEquipo().getCodigo()+"\n";
                                    scriptAuditoria_mtvoCarbon +=",[mc_equipo_lavado_cdgo]="+ listadoMvtoCarbon_ListadoEquipos.get(select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.getSelectedIndex()).getAsignacionEquipo().getEquipo().getCodigo()+"\n";
                                 
                                    scriptDB_MvtoCarbon +=",[mc_motivo_nolavado_vehiculo_cdgo]=NULL ,[mc_costoLavadoVehiculo]=NULL ,[mc_valorRecaudoEmpresa_lavadoVehiculo]=NULL,[mc_valorRecaudoEquipo_lavadoVehiculo]=NULL \n";
                                    scriptAuditoria_mtvoCarbon +=",[mc_motivo_nolavado_vehiculo_cdgo]=NULL ,[mc_costoLavadoVehiculo]=NULL ,[mc_valorRecaudoEmpresa_lavadoVehiculo]=NULL,[mc_valorRecaudoEquipo_lavadoVehiculo]=NULL \n";
                                  
                                    
                                }else{
                                    scriptDB_MvtoCarbon +=",[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+"\n";
                                    scriptAuditoria_mtvoCarbon +=",[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+" \n";
                                    
                                    scriptDB_MvtoCarbon +=",[mc_equipo_lavado_cdgo]="+ listadoMvtoCarbon_ListadoEquipos.get(select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.getSelectedIndex()).getAsignacionEquipo().getEquipo().getCodigo()+"\n";
                                    scriptAuditoria_mtvoCarbon +=",[mc_equipo_lavado_cdgo]="+ listadoMvtoCarbon_ListadoEquipos.get(select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.getSelectedIndex()).getAsignacionEquipo().getEquipo().getCodigo()+"\n";
                                 
                                    scriptDB_MvtoCarbon +=",[mc_motivo_nolavado_vehiculo_cdgo]=NULL ,[mc_costoLavadoVehiculo]=NULL ,[mc_valorRecaudoEmpresa_lavadoVehiculo]=NULL,[mc_valorRecaudoEquipo_lavadoVehiculo]=NULL \n";
                                    scriptAuditoria_mtvoCarbon +=",[mc_motivo_nolavado_vehiculo_cdgo]=NULL ,[mc_costoLavadoVehiculo]=NULL ,[mc_valorRecaudoEmpresa_lavadoVehiculo]=NULL,[mc_valorRecaudoEquipo_lavadoVehiculo]=NULL \n";
                                  }   
                            }  
                            /*if(scriptDB_MvtoCarbon.equals("")){
                                scriptDB_MvtoCarbon +="[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+"\n";
                                scriptAuditoria_mtvoCarbon +="[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+" \n";
                             }else{
                                scriptDB_MvtoCarbon +=",[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+"\n";
                                scriptAuditoria_mtvoCarbon +=",[mc_lavado_vehiculo]=[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+" \n";
                            }*/
                        }
                    }else{
                        /*if(scriptDB_MvtoCarbon.equals("")){
                            scriptDB_MvtoCarbon +="[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+" \n";
                            scriptAuditoria_mtvoCarbon +="[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+" \n";
                         }else{
                            scriptDB_MvtoCarbon +=",[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+"\n";
                            scriptAuditoria_mtvoCarbon +=",[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+" \n";
                        } */
                        if(select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex() ==0){//Seleccion?? que no se realiz?? lavado de veh??culos
                            if(scriptDB_MvtoCarbon.equals("")){
                                scriptDB_MvtoCarbon +="[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+"\n";
                                scriptAuditoria_mtvoCarbon +="[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+" \n";

                                scriptDB_MvtoCarbon +=",[mc_motivo_nolavado_vehiculo_cdgo]="+ listadoMotivoNolavadoVehiculo.get(select_MvtoCarbon_motivoNoLavadoVehiculo.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria_mtvoCarbon +=",[mc_motivo_nolavado_vehiculo_cdgo]="+ listadoMotivoNolavadoVehiculo.get(select_MvtoCarbon_motivoNoLavadoVehiculo.getSelectedIndex()).getCodigo()+"\n";

                                scriptDB_MvtoCarbon +=",[mc_equipo_lavado_cdgo]=NULL ,[mc_costoLavadoVehiculo]=NULL ,[mc_valorRecaudoEmpresa_lavadoVehiculo]=NULL,[mc_valorRecaudoEquipo_lavadoVehiculo]=NULL \n";
                                scriptAuditoria_mtvoCarbon +=",[mc_equipo_lavado_cdgo]=NULL ,[mc_costoLavadoVehiculo]=NULL ,[mc_valorRecaudoEmpresa_lavadoVehiculo]=NULL,[mc_valorRecaudoEquipo_lavadoVehiculo]=NULL \n";   
                            }else{
                                scriptDB_MvtoCarbon +=",[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+"\n";
                                scriptAuditoria_mtvoCarbon +=",[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+" \n";

                                scriptDB_MvtoCarbon +=",[mc_motivo_nolavado_vehiculo_cdgo]="+ listadoMotivoNolavadoVehiculo.get(select_MvtoCarbon_motivoNoLavadoVehiculo.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria_mtvoCarbon +=",[mc_motivo_nolavado_vehiculo_cdgo]="+ listadoMotivoNolavadoVehiculo.get(select_MvtoCarbon_motivoNoLavadoVehiculo.getSelectedIndex()).getCodigo()+"\n";

                                scriptDB_MvtoCarbon +=",[mc_equipo_lavado_cdgo]=NULL ,[mc_costoLavadoVehiculo]=NULL ,[mc_valorRecaudoEmpresa_lavadoVehiculo]=NULL,[mc_valorRecaudoEquipo_lavadoVehiculo]=NULL \n";
                                scriptAuditoria_mtvoCarbon +=",[mc_equipo_lavado_cdgo]=NULL ,[mc_costoLavadoVehiculo]=NULL ,[mc_valorRecaudoEmpresa_lavadoVehiculo]=NULL,[mc_valorRecaudoEquipo_lavadoVehiculo]=NULL \n"; 
                           }
                        }else{//El usuario seleccion?? que si se realiz?? lavado de veh??culo por tal motivo validamos que equipo realiz?? el lavado de veh??culo
                            if(scriptDB_MvtoCarbon.equals("")){
                                scriptDB_MvtoCarbon +="[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+"\n";
                                scriptAuditoria_mtvoCarbon +="[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+" \n";

                                scriptDB_MvtoCarbon +=",[mc_equipo_lavado_cdgo]="+ listadoMvtoCarbon_ListadoEquipos.get(select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.getSelectedIndex()).getAsignacionEquipo().getEquipo().getCodigo()+"\n";
                                scriptAuditoria_mtvoCarbon +=",[mc_equipo_lavado_cdgo]="+ listadoMvtoCarbon_ListadoEquipos.get(select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.getSelectedIndex()).getAsignacionEquipo().getEquipo().getCodigo()+"\n";

                                scriptDB_MvtoCarbon +=",[mc_motivo_nolavado_vehiculo_cdgo]=NULL ,[mc_costoLavadoVehiculo]=NULL ,[mc_valorRecaudoEmpresa_lavadoVehiculo]=NULL,[mc_valorRecaudoEquipo_lavadoVehiculo]=NULL \n";
                                scriptAuditoria_mtvoCarbon +=",[mc_motivo_nolavado_vehiculo_cdgo]=NULL ,[mc_costoLavadoVehiculo]=NULL ,[mc_valorRecaudoEmpresa_lavadoVehiculo]=NULL,[mc_valorRecaudoEquipo_lavadoVehiculo]=NULL \n";
                            }else{
                                scriptDB_MvtoCarbon +=",[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+"\n";
                                scriptAuditoria_mtvoCarbon +=",[mc_lavado_vehiculo]="+select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()+" \n";

                                scriptDB_MvtoCarbon +=",[mc_equipo_lavado_cdgo]="+ listadoMvtoCarbon_ListadoEquipos.get(select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.getSelectedIndex()).getAsignacionEquipo().getEquipo().getCodigo()+"\n";
                                scriptAuditoria_mtvoCarbon +=",[mc_equipo_lavado_cdgo]="+ listadoMvtoCarbon_ListadoEquipos.get(select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.getSelectedIndex()).getAsignacionEquipo().getEquipo().getCodigo()+"\n";

                                scriptDB_MvtoCarbon +=",[mc_motivo_nolavado_vehiculo_cdgo]=NULL ,[mc_costoLavadoVehiculo]=NULL ,[mc_valorRecaudoEmpresa_lavadoVehiculo]=NULL,[mc_valorRecaudoEquipo_lavadoVehiculo]=NULL \n";
                                scriptAuditoria_mtvoCarbon +=",[mc_motivo_nolavado_vehiculo_cdgo]=NULL ,[mc_costoLavadoVehiculo]=NULL ,[mc_valorRecaudoEmpresa_lavadoVehiculo]=NULL,[mc_valorRecaudoEquipo_lavadoVehiculo]=NULL \n";
                            }   
                        }  
                    }
                }
                if(validarCampos){
                    if(listadoUsuarioInicioRegistro_mvtoCarbon != null){
                        if(check_MvtoCarbon_UsuarioIniciaActividad.isSelected()){
                            if(scriptDB_MvtoCarbon.equals("")){
                                scriptDB_MvtoCarbon +="[mc_usuario_cdgo]="+listadoUsuarioInicioRegistro_mvtoCarbon.get(select_MvtoCarbon_UsuarioIniciaRegistro.getSelectedIndex()).getCodigo()+" \n";
                                scriptAuditoria_mtvoCarbon +="[mc_usuario_cdgo]="+listadoUsuarioInicioRegistro_mvtoCarbon.get(select_MvtoCarbon_UsuarioIniciaRegistro.getSelectedIndex()).getCodigo()+" \n";
                            }else{
                                scriptDB_MvtoCarbon +=",[mc_usuario_cdgo]="+listadoUsuarioInicioRegistro_mvtoCarbon.get(select_MvtoCarbon_UsuarioIniciaRegistro.getSelectedIndex()).getCodigo()+" \n";
                                scriptAuditoria_mtvoCarbon +=",[mc_usuario_cdgo]="+listadoUsuarioInicioRegistro_mvtoCarbon.get(select_MvtoCarbon_UsuarioIniciaRegistro.getSelectedIndex()).getCodigo()+" \n"; 
                            }
                        }
                    }
                    if(listadoUsuarioCierraRegistro_mvtoCarbon != null){
                        if(check_MvtoCarbon_UsuarioCierraActividad.isSelected()){
                            if(scriptDB_MvtoCarbon.equals("")){
                                scriptDB_MvtoCarbon +="[mc_usuario_cierre_cdgo]="+listadoUsuarioCierraRegistro_mvtoCarbon.get(select_MvtoCarbon_UsuarioCierraRegistro.getSelectedIndex()).getCodigo()+" \n";
                                scriptAuditoria_mtvoCarbon +="[mc_usuario_cierre_cdgo]="+listadoUsuarioCierraRegistro_mvtoCarbon.get(select_MvtoCarbon_UsuarioCierraRegistro.getSelectedIndex()).getCodigo()+" \n";
                            }else{
                                scriptDB_MvtoCarbon +=",[mc_usuario_cierre_cdgo]="+listadoUsuarioCierraRegistro_mvtoCarbon.get(select_MvtoCarbon_UsuarioCierraRegistro.getSelectedIndex()).getCodigo()+" \n";
                                scriptAuditoria_mtvoCarbon +=",[mc_usuario_cierre_cdgo]="+listadoUsuarioCierraRegistro_mvtoCarbon.get(select_MvtoCarbon_UsuarioCierraRegistro.getSelectedIndex()).getCodigo()+" \n";
                            }
                        }
                    }
                }  
                if(validarCampos){// revisamos que el validador sea true
                    int retorno=0;
                    try {
                        if(scriptDB_MvtoCarbon.equals("")){
                            scriptDB_MvtoCarbon +="[mc_cntro_cost_cdgo]=NULL, [mc_cntro_cost_mayor_cdgo]=NULL \n";
                            //scriptDB_MvtoEquipo +="[me_cntro_cost_cdgo]=NULL,[me_cntro_cost]=NULL,[me_cntro_cost_mayor_cdgo]=NULL \n";
                        }else{
                            scriptDB_MvtoCarbon +=",[mc_cntro_cost_cdgo]=NULL, [mc_cntro_cost_mayor_cdgo]=NULL \n";
                            //scriptDB_MvtoEquipo +=",[me_cntro_cost_cdgo]=NULL,[me_cntro_cost]=NULL,[me_cntro_cost_mayor_cdgo]=NULL \n";
                        }
                        if(scriptDB_MvtoEquipo.equals("")){
                            //scriptDB_MvtoCarbon +="[mc_cntro_cost_cdgo]=NULL, [mc_cntro_cost_mayor_cdgo]=NULL \n";
                            scriptDB_MvtoEquipo +="[me_cntro_cost_cdgo]=NULL,[me_cntro_cost]=NULL,[me_cntro_cost_mayor_cdgo]=NULL \n";
                        }else{
                            //scriptDB_MvtoCarbon +=",[mc_cntro_cost_cdgo]=NULL, [mc_cntro_cost_mayor_cdgo]=NULL \n";
                            scriptDB_MvtoEquipo +=",[me_cntro_cost_cdgo]=NULL,[me_cntro_cost]=NULL,[me_cntro_cost_mayor_cdgo]=NULL \n";
                        }
                        System.out.println("\n\n");
                        System.out.println("ScriptCarbon: "+scriptDB_MvtoCarbon+"\n"); 
                        System.out.println("ScriptEquipo: "+scriptDB_MvtoEquipo+"\n"); 
                        System.out.println("ScriptCarbonAuditoria: "+scriptAuditoria_mtvoCarbon+"\n");
                        System.out.println("ScriptEquipoAuditoria: "+scriptAuditoria_mtvoEquipo+"\n");
                        retorno = new ControlDB_MvtoCarbon(tipoConexion).modificarMvtoCarbon(mvtoCarbon, listadoMvtoCarbon_ListadoEquipos, user,scriptDB_MvtoCarbon ,scriptDB_MvtoEquipo,scriptAuditoria_mtvoCarbon,scriptAuditoria_mtvoEquipo,textArea_MvtoEquipo_razonModificacion.getText());
                        if(retorno==1){
                            try{
                                ArrayList<MvtoCarbon_ListadoEquipos> mvtoCarbon_ListadoEquiposTemp= new ControlDB_MvtoCarbon(tipoConexion).buscarMvtoCarbonParticular(mvtoCarbon);
                                for(MvtoCarbon_ListadoEquipos listado : mvtoCarbon_ListadoEquiposTemp){
                                    System.out.println("Hemos cargado el ProcesarEncargoGP===============================================================================>");
                                    
                                    new ControlDB_MvtoCarbon(tipoConexion).ProcesarEnCcargaGP(listado,user);
                                }
                               retorno=1;   
                            }catch(Exception e){
                                retorno=0;
                            }
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SocketException ex) {
                        Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(retorno==1){
                        for(MvtoCarbon_ListadoEquipos listado : listadoMvtoCarbon_ListadoEquipos){
                            MvtoEquipo mvtoEquipoTemporal=new ControlDB_MvtoEquipo(tipoConexion).procesarMvtoEquipoParticular(listado.getMvtoEquipo());
                            
                            new ControlDB_MvtoEquipo(tipoConexion).Procesar_MvtoEquipo(mvtoEquipoTemporal,user);
                            //new ControlDB_MvtoCarbon(tipoConexion).ProcesarEnCcargaGP(listado,user);
                        }
                        JOptionPane.showMessageDialog(null, "Se registro la modificaci??n del registro de forma exitosa", "Registro Exitoso",JOptionPane.INFORMATION_MESSAGE);

                        check_MvtoCarbon_InicioActividad.setSelected(false);
                        check_MvtoCarbon_FinalizacionActividad.setSelected(false);
                        select_MvtoEquipo_laborRealizada.setText("");
                        check_MvtoCarbon_UsuarioIniciaActividad.setSelected(false);
                        check_MvtoCarbon_UsuarioCierraActividad.setSelected(false);
                        textArea_MvtoCarbon_razonModificacion.setText("");
                        
                        check_MvtoEquipo_InicioActividad.setSelected(false);
                        check_MvtoEquipo_FinalizacionActividad.setSelected(false);
                        textArea_MvtoEquipo_razonModificacion.setText("");
                        
                        paginationPanel.removeAll();
                        validarSeleccionCampos();
                        generarListadoMvtoCarbon();
                        resizeColumnWidth(tabla);
                        InternaFrame_VisualizarMvtoCarbon.show(false);    
                    }else{
                        JOptionPane.showMessageDialog(null, "No se puedo registrar la modificaci??n del registro, valide datos", "Error al registrar",JOptionPane.ERROR_MESSAGE);
                    }   
                }else{
                    JOptionPane.showMessageDialog(null, "No se puedo registrar la modificaci??n del registro, valide datos", "Error al registrar",JOptionPane.ERROR_MESSAGE);    
                }       
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"No se pudo realizar la modificaci??n, verifique informaci??n suministrada","Error!!!",JOptionPane.ERROR_MESSAGE);
            } 
        }else{
            JOptionPane.showMessageDialog(null,"La raz??n de modificaci??n no puede estar vacia, valide la informaci??n suministrada","Error!!!",JOptionPane.ERROR_MESSAGE);  
        }  
       }else{
            JOptionPane.showMessageDialog(null,"Debe cargar un registro de carb??n para ser modificado","Error!!!",JOptionPane.ERROR_MESSAGE);  
        }  
    }//GEN-LAST:event_registrar_MvtoCarbonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        InternalFrameSelectorCampos.show(false);
        if(listado != null){
            paginationPanel.removeAll();
            validarSeleccionCampos();
            tabla.setModel(crearModeloDeTabla());
            //listado=new ControlDB_MvtoEquipo(tipoConexion).buscar_mvtoEquiposActivosGeneral(data1, data2);
            proveedorDeDatos = crearProveedorDeDatos(listado);
            paginadorDeTabla = new PaginadorDeTabla(tabla, proveedorDeDatos, new int[]{5, 10, 20, 50, 75, 100}, 10);
            paginadorDeTabla.crearListadoDeFilasPermitidas(this.paginationPanel);
            pageJComboBox = paginadorDeTabla.getComboBoxPage();
            events();
            pageJComboBox.setSelectedItem(Integer.parseInt("50"));
            resizeColumnWidth(tabla);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void selectAllItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectAllItemStateChanged
        if(selectAll.isSelected()){
            selectorCampoTodos();
        }else{
            selectorCampoNinguno();
        }
    }//GEN-LAST:event_selectAllItemStateChanged

    private void selectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAllActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAllActionPerformed

    private void selectDescargue_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDescargue_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectDescargue_codigoActionPerformed

    private void selectDescargue_pesoVacioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDescargue_pesoVacioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectDescargue_pesoVacioActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        selectorCampoPorDefecto();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void selectDescargue_articuloCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDescargue_articuloCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectDescargue_articuloCodigoActionPerformed

    private void selectDescargue_centroCostoMayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDescargue_centroCostoMayorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectDescargue_centroCostoMayorActionPerformed

    private void selectDescargue_laborRealizadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDescargue_laborRealizadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectDescargue_laborRealizadaActionPerformed

    private void selectDescargue_usuarioRegistroManualCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDescargue_usuarioRegistroManualCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectDescargue_usuarioRegistroManualCorreoActionPerformed

    private void selectMvtoEquipo_cantidadMinutosDescargueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_cantidadMinutosDescargueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_cantidadMinutosDescargueActionPerformed

    private void selectAsignacion_equipoTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAsignacion_equipoTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAsignacion_equipoTipoActionPerformed

    private void selectMvtoEquipo_motonaveDescripci??nActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_motonaveDescripci??nActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_motonaveDescripci??nActionPerformed

    private void selectMvtoEquipo_subcentroCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_subcentroCostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_subcentroCostoActionPerformed

    private void selectAsignacion_fechaInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAsignacion_fechaInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAsignacion_fechaInicioActionPerformed

    private void selectAsignacion_cantidadMinutos_ProgramadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAsignacion_cantidadMinutos_ProgramadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAsignacion_cantidadMinutos_ProgramadosActionPerformed

    private void selectMvtoEquipo_proveedorEquipoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_proveedorEquipoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_proveedorEquipoNombreActionPerformed

    private void selectMvtoEquipo_laborRealizadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_laborRealizadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_laborRealizadaActionPerformed

    private void selectMvtoEquipo_fechaInicioDescargueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_fechaInicioDescargueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_fechaInicioDescargueActionPerformed

    private void selectMvtoEquipo_fechaFinDescargueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_fechaFinDescargueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_fechaFinDescargueActionPerformed

    private void selectMvtoEquipo_UsuarioInactividad_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_UsuarioInactividad_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_UsuarioInactividad_codigoActionPerformed

    private void selectAsignacion_equipoDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAsignacion_equipoDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAsignacion_equipoDescripcionActionPerformed

    private void selectMvtoEquipo_articuloCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_articuloCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_articuloCodigoActionPerformed

    private void selectMvtoEquipo_articuloDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_articuloDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_articuloDescripcionActionPerformed

    private void selectMvtoEquipo_motonaveCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_motonaveCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_motonaveCodigoActionPerformed

    private void select_MvtoCarbon_UsuarioIniciaRegistroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_UsuarioIniciaRegistroItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoCarbon_UsuarioIniciaRegistroItemStateChanged

    private void select_MvtoCarbon_UsuarioIniciaRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_UsuarioIniciaRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoCarbon_UsuarioIniciaRegistroActionPerformed

    private void select_MvtoCarbon_UsuarioCierraRegistroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_UsuarioCierraRegistroItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoCarbon_UsuarioCierraRegistroItemStateChanged

    private void select_MvtoCarbon_UsuarioCierraRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_UsuarioCierraRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoCarbon_UsuarioCierraRegistroActionPerformed

    private void select_MvtoEquipo_UsuarioIniciaRegistroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_UsuarioIniciaRegistroItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_UsuarioIniciaRegistroItemStateChanged

    private void select_MvtoEquipo_UsuarioIniciaRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_UsuarioIniciaRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_UsuarioIniciaRegistroActionPerformed

    private void select_MvtoEquipo_UsuarioCierraRegistroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_UsuarioCierraRegistroItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_UsuarioCierraRegistroItemStateChanged

    private void select_MvtoEquipo_UsuarioCierraRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_UsuarioCierraRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_UsuarioCierraRegistroActionPerformed

    private void select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culoItemStateChanged

    private void select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culoActionPerformed

    private void select_MvtoCarbon_motivoNoLavadoVehiculoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_motivoNoLavadoVehiculoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoCarbon_motivoNoLavadoVehiculoItemStateChanged

    private void select_MvtoCarbon_motivoNoLavadoVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_motivoNoLavadoVehiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoCarbon_motivoNoLavadoVehiculoActionPerformed

    private void check_MvtoCarbon_UsuarioCierraActividadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_check_MvtoCarbon_UsuarioCierraActividadItemStateChanged
       if(check_MvtoCarbon_UsuarioCierraActividad.isSelected()){
            select_MvtoCarbon_UsuarioCierraRegistro.show(true);
        }else{
            select_MvtoCarbon_UsuarioCierraRegistro.show(false);
        }
    }//GEN-LAST:event_check_MvtoCarbon_UsuarioCierraActividadItemStateChanged

    private void check_MvtoCarbon_UsuarioIniciaActividadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_check_MvtoCarbon_UsuarioIniciaActividadItemStateChanged
        if(check_MvtoCarbon_UsuarioIniciaActividad.isSelected()){
            select_MvtoCarbon_UsuarioIniciaRegistro.show(true);
        }else{
            select_MvtoCarbon_UsuarioIniciaRegistro.show(false);
        }
            
            
    }//GEN-LAST:event_check_MvtoCarbon_UsuarioIniciaActividadItemStateChanged

    private void check_MvtoCarbon_UsuarioCierraActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_MvtoCarbon_UsuarioCierraActividadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_MvtoCarbon_UsuarioCierraActividadActionPerformed

    private void check_MvtoEquipo_UsuarioIniciaActividadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_check_MvtoEquipo_UsuarioIniciaActividadItemStateChanged
        if(check_MvtoEquipo_UsuarioIniciaActividad.isSelected()){
            select_MvtoEquipo_UsuarioIniciaRegistro.show(true);
        }else{
            select_MvtoEquipo_UsuarioIniciaRegistro.show(false);
        }
    }//GEN-LAST:event_check_MvtoEquipo_UsuarioIniciaActividadItemStateChanged

    private void check_MvtoEquipo_UsuarioCierraActividadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_check_MvtoEquipo_UsuarioCierraActividadItemStateChanged
        if(check_MvtoEquipo_UsuarioCierraActividad.isSelected()){
            select_MvtoEquipo_UsuarioCierraRegistro.show(true);
        }else{
            select_MvtoEquipo_UsuarioCierraRegistro.show(false);
        }
    }//GEN-LAST:event_check_MvtoEquipo_UsuarioCierraActividadItemStateChanged

    private void check_MvtoEquipo_UsuarioCierraActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_MvtoEquipo_UsuarioCierraActividadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_MvtoEquipo_UsuarioCierraActividadActionPerformed

    private void select_MvtoCarbon_lavadoVeh??culoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_lavadoVeh??culoItemStateChanged
        if(select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()==0){
            label_QuienRealizaLavadoVeh??culo.show(false);
            select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.show(false);

            label_motivoNoLavado.show(true);
            select_MvtoCarbon_motivoNoLavadoVehiculo.show(true);
        }else{
            if(select_MvtoCarbon_lavadoVeh??culo.getSelectedIndex()==1){
                label_QuienRealizaLavadoVeh??culo.show(true);
                select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.show(true);

                label_motivoNoLavado.show(false);
                select_MvtoCarbon_motivoNoLavadoVehiculo.show(false);
            }
        }
        
        
        
        
        /** if(mvtoCarbon.getLavadoVehiculo().equals("SI")){
                            select_MvtoCarbon_lavadoVeh??culo.setSelectedIndex(1);
                            label_QuienRealizaLavadoVeh??culo.show(true);
                            select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.show(true);
                            
                            label_motivoNoLavado.show(false);
                            select_MvtoCarbon_motivoNoLavadoVehiculo.show(false);
                        }else{
                            if(mvtoCarbon.getLavadoVehiculo().equals("NO")){
                                select_MvtoCarbon_lavadoVeh??culo.setSelectedIndex(0);
                                
                                label_QuienRealizaLavadoVeh??culo.show(false);
                                select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo.show(false);
                                
                                label_motivoNoLavado.show(true);
                                select_MvtoCarbon_motivoNoLavadoVehiculo.show(true);
                            }
                        }*/
    }//GEN-LAST:event_select_MvtoCarbon_lavadoVeh??culoItemStateChanged

    private void checkUsuarioQuienRegistraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkUsuarioQuienRegistraItemStateChanged
        if(checkUsuarioQuienRegistra.isSelected()){
            icon_buscarUsuarioQuienRegistra.show(true);
            label_usuarioQuienRegistra.show(true);
            limpiar_usuario.show(true);
        }else{
            icon_buscarUsuarioQuienRegistra.show(false);
            label_usuarioQuienRegistra.show(false);
            limpiar_usuario.show(false);
        }
    }//GEN-LAST:event_checkUsuarioQuienRegistraItemStateChanged

    private void checkUsuarioQuienRegistraCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_checkUsuarioQuienRegistraCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_checkUsuarioQuienRegistraCaretPositionChanged

    private void checkUsuarioQuienRegistraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkUsuarioQuienRegistraActionPerformed
        
    }//GEN-LAST:event_checkUsuarioQuienRegistraActionPerformed

    private void icon_buscarUsuarioQuienRegistraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_buscarUsuarioQuienRegistraMouseClicked
        InternaFrame_buscarUsuario.show(true);
    }//GEN-LAST:event_icon_buscarUsuarioQuienRegistraMouseClicked

    private void checkPlacaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkPlacaItemStateChanged
        if(checkPlaca.isSelected()){
            label_placa.show(true);
            limpiar_placa.show(true);
        }else{
            label_placa.show(false);
            limpiar_placa.show(false);
        }
    }//GEN-LAST:event_checkPlacaItemStateChanged

    private void checkPlacaCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_checkPlacaCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_checkPlacaCaretPositionChanged

    private void checkPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPlacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkPlacaActionPerformed

    private void icon_buscarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_buscarClienteMouseClicked
        InternaFrame_buscarCliente.show(true);
    }//GEN-LAST:event_icon_buscarClienteMouseClicked

    private void checkClienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkClienteItemStateChanged
        if(checkCliente.isSelected()){
            icon_buscarCliente.show(true);
            label_cliente.show(true);
            limpiar_cliente.show(true);
        }else{
            icon_buscarCliente.show(false);
            label_cliente.show(false);
            limpiar_cliente.show(false);
        }
    }//GEN-LAST:event_checkClienteItemStateChanged

    private void checkClienteCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_checkClienteCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_checkClienteCaretPositionChanged

    private void checkClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkClienteActionPerformed
        
    }//GEN-LAST:event_checkClienteActionPerformed

    private void label_placaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_label_placaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_label_placaActionPerformed

    private void tablaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClienteMouseClicked
        if (evt.getClickCount() == 2) {
            int fila1;
            try{
                fila1=tablaCliente.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    cliente= listadoCliente.get(fila1);
                    label_cliente.setText(cliente.getDescripcion());
                    InternaFrame_buscarCliente.show(false);
                }
            }catch(Exception e){
            }
        }
    }//GEN-LAST:event_tablaClienteMouseClicked

    private void btnConsultarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarClienteActionPerformed
        try {
            DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"C??digo", "Nombre","Estado","BaseDatos"});
            listadoCliente=new ControlDB_Cliente(tipoConexion).buscarCliente_EstadoActivo(valorBusquedaCliente.getText());
            for(Cliente listadoObjetos1:listadoCliente){
                String []registro = new String[4];
                registro[0]=""+listadoObjetos1.getCodigo();
                registro[1]=""+listadoObjetos1.getDescripcion();
                registro[2]=""+listadoObjetos1.getEstado();
                registro[3]=""+listadoObjetos1.getBaseDatos().getDescripcion();
                modelo.addRow(registro);
            }
            tablaCliente.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConsultarClienteActionPerformed

    private void btnCancelarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarClienteActionPerformed
        InternaFrame_buscarCliente.show(false);
    }//GEN-LAST:event_btnCancelarClienteActionPerformed

    private void btnLimpiarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarClienteActionPerformed
        valorBusquedaCliente.setText("");
    }//GEN-LAST:event_btnLimpiarClienteActionPerformed

    private void tablaUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuarioMouseClicked
        if (evt.getClickCount() == 2) {
            int fila1;
            try{
                fila1=tablaUsuario.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    usuario= listadoUsuario.get(fila1);
                    label_usuarioQuienRegistra.setText(usuario.getNombres()+ " "+ usuario.getApellidos());
                    InternaFrame_buscarUsuario.show(false);
                }
            }catch(Exception e){
            }
        }
    }//GEN-LAST:event_tablaUsuarioMouseClicked

    private void btnConsultarUsuariosQuienRegistraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarUsuariosQuienRegistraActionPerformed
        try {
            DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"C??digo", "Nombre","Estado"});
            listadoUsuario=new ControlDB_Usuario(tipoConexion).buscar(valorBusquedaUsuario.getText());
            for(Usuario listadoObjetos1:listadoUsuario){
                String []registro = new String[3];
                registro[0]=""+listadoObjetos1.getCodigo();
                registro[1]=""+listadoObjetos1.getNombres()+" "+listadoObjetos1.getApellidos();
                registro[2]=""+listadoObjetos1.getEstado();
                modelo.addRow(registro);
            }
            tablaUsuario.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConsultarUsuariosQuienRegistraActionPerformed

    private void btnCancelarUsuariosQuienRegistraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarUsuariosQuienRegistraActionPerformed
        InternaFrame_buscarUsuario.show(false);
    }//GEN-LAST:event_btnCancelarUsuariosQuienRegistraActionPerformed

    private void btnLimpiarUsuariosQuienRegistraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarUsuariosQuienRegistraActionPerformed
        valorBusquedaUsuario.setText("");
    }//GEN-LAST:event_btnLimpiarUsuariosQuienRegistraActionPerformed

    private void limpiar_placaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_limpiar_placaMouseClicked
        label_placa.setText("");
    }//GEN-LAST:event_limpiar_placaMouseClicked

    private void limpiar_clienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_limpiar_clienteMouseClicked
        label_cliente.setText("");
        cliente= null;
    }//GEN-LAST:event_limpiar_clienteMouseClicked

    private void limpiar_usuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_limpiar_usuarioMouseClicked
        label_usuarioQuienRegistra.setText("");
        usuario=null;
    }//GEN-LAST:event_limpiar_usuarioMouseClicked

    private void buscadorPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscadorPlacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscadorPlacaActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        //Eliminar todos los elementos de una tabla Asignaci??n temporal
        DefaultTableModel modeloEliminar =(DefaultTableModel)tabla1.getModel();
        int CantEliminar= tabla1.getRowCount() -1;
        for(int i =CantEliminar; i>=0; i--){
            modeloEliminar.removeRow(i);
        }

        String fechaInicio_consultaVehiculosDestarados="";
        try{
            Calendar fechaI = fechaInicio_destareVehiculo.getCalendar();
            String anoI = ""+fechaI.get(Calendar.YEAR);
            String mesI = "";
            if((fechaI.get(Calendar.MONTH) +1) <=9){
                mesI = "0"+(fechaI.get(Calendar.MONTH) + 1);
            }else{
                mesI = ""+(fechaI.get(Calendar.MONTH) + 1);
            }
            String diaI = "";
            if(fechaI.get(Calendar.DAY_OF_MONTH) <=9){
                diaI = "0"+fechaI.get(Calendar.DAY_OF_MONTH);
            }else{
                diaI = ""+fechaI.get(Calendar.DAY_OF_MONTH);
            }
            //try{
                //  int valorFechaI=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorFechaActual(anoI, mesI, diaI, horaInicio_destareVehiculo.getSelectedItem().toString(), minutoInicio_destareVehiculo.getSelectedItem().toString()));
                //if(valorFechaI<0){
                    fechaInicio_consultaVehiculosDestarados=anoI+"-"+mesI+"-"+diaI+" "+horaInicio_destareVehiculo.getSelectedItem().toString()+":"+minutoInicio_destareVehiculo.getSelectedItem().toString()+":00.0";
                    //Cargamos la fecha de inicio de movimientos de la solicitud
                    String fechaFin_consultaVehiculosDestarado="";
                    try{
                        Calendar fechaF = fechaFin_destareVehiculo.getCalendar();
                        String anoF = ""+fechaF.get(Calendar.YEAR);
                        String mesF = "";
                        if((fechaF.get(Calendar.MONTH) +1) <=9){
                            mesF = "0"+(fechaF.get(Calendar.MONTH) + 1);
                        }else{
                            mesF = ""+(fechaF.get(Calendar.MONTH) + 1);
                        }
                        String diaF = "";
                        if(fechaF.get(Calendar.DAY_OF_MONTH) <=9){
                            diaF = "0"+fechaF.get(Calendar.DAY_OF_MONTH);
                        }else{
                            diaF = ""+fechaF.get(Calendar.DAY_OF_MONTH);
                        }
                        try{
                            //int valorFechaF=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorFechaActual(anoF, mesF, diaF, horaFin_destareVehiculo.getSelectedItem().toString(), minutoFin_destareVehiculo.getSelectedItem().toString()));
                            //if(valorFechaF<0){
                                fechaFin_consultaVehiculosDestarado=anoF+"-"+mesF+"-"+diaF+" "+horaFin_destareVehiculo.getSelectedItem().toString()+":"+minutoFin_destareVehiculo.getSelectedItem().toString()+":00.0";
                                int resultDosFechas=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorEntreDosFechas(anoI, mesI, diaI, horaInicio_destareVehiculo.getSelectedItem().toString(), minutoInicio_destareVehiculo.getSelectedItem().toString(), anoF, mesF, diaF, horaFin_destareVehiculo.getSelectedItem().toString(), minutoFin_destareVehiculo.getSelectedItem().toString()));
                                if(resultDosFechas < 0){
                                    JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser mayor a la fecha de Finalizaci??n","Advertencia", JOptionPane.ERROR_MESSAGE );
                                }else{
                                    if(resultDosFechas ==0){
                                        JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser Igual a la fecha de Finalizaci??n","Advertencia", JOptionPane.ERROR_MESSAGE );
                                    }else{
                                        //Validamos que cargue una placa
                                        //if(buscadorPlaca.getText().equals("")){
                                            // JOptionPane.showMessageDialog(null, "Error!!.. Digite una placa, no puede ir un campo vacio","Advertencia", JOptionPane.ERROR_MESSAGE );
                                            //}else{
                                            // DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"C??digo", "Nombre","Estado","BaseDeDatos"});
                                            DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"PLACA", "FECHA_TARA","FECHA_DESTARE","PESO VACIO","PESO LLENO","PESO NETO","DEPOSITO","ARTICULO","CLIENTE","TRANSPORTADORA","MOTONAVE"});
                                            listadoMvtoCarbon=new ControlDB_MvtoCarbon(tipoConexion).buscarVehiculosAnadirVehiculos(buscadorPlaca.getText(), fechaInicio_consultaVehiculosDestarados, fechaFin_consultaVehiculosDestarado);
                                            if(listadoMvtoCarbon != null){
                                                for(MvtoCarbon  Objeto:listadoMvtoCarbon){
                                                    String []registro = new String[11];
                                                    registro[0]=""+Objeto.getPlaca();
                                                    registro[1]=""+Objeto.getFechaEntradaVehiculo();
                                                    registro[2]=""+Objeto.getFecha_SalidaVehiculo();
                                                    registro[3]=""+Objeto.getPesoVacio();
                                                    registro[4]=""+Objeto.getPesoLleno();
                                                    registro[5]=""+Objeto.getPesoNeto();
                                                    registro[6]=""+Objeto.getDeposito();
                                                    registro[7]=""+Objeto.getArticulo().getDescripcion();
                                                    registro[8]=""+Objeto.getCliente().getDescripcion();
                                                    registro[9]=""+Objeto.getTransportadora().getDescripcion();
                                                    registro[10]=""+Objeto.getMotonave().getDescripcion();
                                                    modelo.addRow(registro);
                                                }
                                                tabla1.setModel(modelo);
                                            }

                                            //}
                                    }
                                }
                                //}else{
                                //  JOptionPane.showMessageDialog(null,"Error!!.. No puede cargar una fecha en el pasado, la fecha de Finalizaci??n de la Operaci??n debe ser futura, verifique fecha","Advertencia",JOptionPane.ERROR_MESSAGE);
                                //}
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(null,"Error!!.. Al trata de validar fecha actual del registro con fecha del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                        }
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Finalizaci??n","Advertencia",JOptionPane.ERROR_MESSAGE);
                    }
                    //}else{
                    //  JOptionPane.showMessageDialog(null,"Error!!.. No puede cargar una fecha en el pasado, la fecha de Inicio de Operaci??n debe ser futura, verifique fecha","Advertencia",JOptionPane.ERROR_MESSAGE);
                    //}
                //}catch(Exception e){
                //  JOptionPane.showMessageDialog(null,"Error!!.. Al trata de validar fecha actual del registro con fecha del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                //}
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Inicio","Advertencia",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        buscadorPlaca.setText("");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        VentanaInterna_PesoCero.show(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla1MouseClicked
        if (evt.getClickCount() == 2) {
            int opcion  =JOptionPane.showConfirmDialog(null,"Est?? seguro que desea carga el registro seleccionado?.");
            if(opcion==0){
                int fila1;
                try{
                    fila1=tabla1.getSelectedRow();
                    if(fila1==-1){
                        JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                    }
                    else{
                        if(listadoMvtoCarbon != null){
                            mvtoCarbon_Actualizar=null;
                            mvtoCarbon_Actualizar=listadoMvtoCarbon.get(fila1);
                            PesoCero_placa_nuevo.setText(mvtoCarbon_Actualizar.getPlaca());
                            PesoCero_deposito_nuevo.setText(mvtoCarbon_Actualizar.getDeposito());       
                            PesoCero_consecutivo_nuevo.setText(mvtoCarbon_Actualizar.getConsecutivo());       
                            PesoCero_pesoVacio_nuevo.setText(mvtoCarbon_Actualizar.getPesoVacio());
                            PesoCero_pesoLleno_nuevo.setText(mvtoCarbon_Actualizar.getPesoLleno());
                            PesoCero_pesoNeto_nuevo.setText(mvtoCarbon_Actualizar.getPesoNeto());
                            PesoCero_fechaEntrada_nuevo.setText(mvtoCarbon_Actualizar.getFechaEntradaVehiculo());       
                            PesoCero_fechaSalida_nuevo.setText(mvtoCarbon_Actualizar.getFecha_SalidaVehiculo());       
                            PesoCero_transportadora_nuevo.setText(mvtoCarbon_Actualizar.getTransportadora().getDescripcion());       
                            PesoCero_cliente_nuevo.setText(mvtoCarbon_Actualizar.getCliente().getDescripcion());
                            PesoCero_articulo_nuevo.setText(mvtoCarbon_Actualizar.getArticulo().getDescripcion());
                            
                            
                            
                            
                            
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_tabla1MouseClicked

    private void fechaInicio_destareVehiculoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicio_destareVehiculoMouseClicked
        //  alerta_fechaInicio.setText("");
    }//GEN-LAST:event_fechaInicio_destareVehiculoMouseClicked

    private void fechaInicio_destareVehiculoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicio_destareVehiculoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaInicio_destareVehiculoMouseEntered

    private void horaInicio_destareVehiculoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_horaInicio_destareVehiculoItemStateChanged
        if(horaInicio_destareVehiculo.getSelectedIndex()<= 11){
            horarioTiempoInicio_destareVehiculo.setText("AM");
        }else{
            horarioTiempoInicio_destareVehiculo.setText("PM");
        }
    }//GEN-LAST:event_horaInicio_destareVehiculoItemStateChanged

    private void minutoInicio_destareVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minutoInicio_destareVehiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minutoInicio_destareVehiculoActionPerformed

    private void fechaFin_destareVehiculoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFin_destareVehiculoMouseClicked
        //alerta_fechaFinal.setText("");
    }//GEN-LAST:event_fechaFin_destareVehiculoMouseClicked

    private void fechaFin_destareVehiculoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFin_destareVehiculoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFin_destareVehiculoMouseEntered

    private void horaFin_destareVehiculoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_horaFin_destareVehiculoItemStateChanged
        if(horaFin_destareVehiculo.getSelectedIndex()<= 11){
            horarioTiempoIFinal_destareVehiculo.setText("AM");
        }else{
            horarioTiempoIFinal_destareVehiculo.setText("PM");
        }
    }//GEN-LAST:event_horaFin_destareVehiculoItemStateChanged

    private void horaFin_destareVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horaFin_destareVehiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_horaFin_destareVehiculoActionPerformed

    private void minutoFin_destareVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minutoFin_destareVehiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minutoFin_destareVehiculoActionPerformed

    private void PesoCero1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesoCero1ActionPerformed
        int fila1;
        try{
            fila1=tabla.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ningun Vehiculo");
            }
            else{
                
                int posicion = paginadorDeTabla.getPosicionador();
                if(posicion != -1){
                    fila1 = fila1 +posicion;
                }
                mvtoCarbon= listado.get(fila1).getMvtoCarbon();
                if(mvtoCarbon.getPesoNeto().equals("0")){  
                    PesoCero_placa.setText(mvtoCarbon.getPlaca());
                    PesoCero_deposito.setText(mvtoCarbon.getDeposito());       
                    PesoCero_consecutivo.setText(mvtoCarbon.getConsecutivo());       
                    PesoCero_pesoVacio.setText(mvtoCarbon.getPesoVacio());
                    PesoCero_pesoLleno.setText(mvtoCarbon.getPesoLleno());
                    PesoCero_pesoNeto.setText(mvtoCarbon.getPesoNeto());
                    PesoCero_fechaEntrada.setText(mvtoCarbon.getFechaEntradaVehiculo());       
                    PesoCero_fechaSalida.setText(mvtoCarbon.getFecha_SalidaVehiculo());       
                    PesoCero_transportadora.setText(mvtoCarbon.getTransportadora().getDescripcion());       
                    PesoCero_cliente.setText(mvtoCarbon.getCliente().getDescripcion());
                    PesoCero_articulo.setText(mvtoCarbon.getArticulo().getDescripcion());     

                    try{
                        String[] fechaA=mvtoCarbon.getFechaRegistro().split(" ");
                        //String[] horaB= fechaA[1].split(":");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date fechaM=dateFormat.parse(fechaA[0]);
                        fechaInicio_destareVehiculo.setDate(fechaM);
                        fechaFin_destareVehiculo.setDate(fechaM);
                        //select_MvtoCarbon_HoraInicial.setSelectedItem(horaB[0]);
                        //select_MvtoCarbon_MinutosInicial.setSelectedItem(horaB[1]);
                    }catch(Exception e){
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "No se pudo carga la fecha de Inicio del descargue","Error!!.", JOptionPane.ERROR_MESSAGE);
                    }            
                    horaInicio_destareVehiculo.setSelectedIndex(0);
                    minutoInicio_destareVehiculo.setSelectedIndex(0);
                    horaFin_destareVehiculo.setSelectedIndex(23);
                    minutoFin_destareVehiculo.setSelectedIndex(59);

                    buscadorPlaca.setText("");
                    //Eliminar los registros
                    DefaultTableModel modeloEliminar =(DefaultTableModel)tabla1.getModel();
                    int CantEliminar= tabla1.getRowCount() -1;
                    for(int i =CantEliminar; i>=0; i--){
                        modeloEliminar.removeRow(i);
                    }
                    listadoMvtoCarbon=null;
                    VentanaInterna_PesoCero.show(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un registro que contenga Peso Neto en Cero","Advertencia!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_PesoCero1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(mvtoCarbon_Actualizar != null){
            /*if (new ControlDB_MvtoCarbon(tipoConexion).validarExistenciaMvtoCarbon(mvtoCarbon_Actualizar)) {
                JOptionPane.showMessageDialog(null,"Ya se registro este veh??culo en el sistema.", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            }else{*/
                /*int fila3;
                try{
                    fila3=tabla.getSelectedRow();
                    if(fila3==-1){
                        JOptionPane.showMessageDialog(null,"no se ha seleccionando ningun Vehiculo");
                    }
                    else{*/
                        /*int posicion = paginadorDeTabla.getPosicionador();
                        if(posicion != -1){
                            fila3 = fila3 +posicion;
                        }
                        mvtoCarbon= listado.get(fila3).getMvtoCarbon();*/
                        if(mvtoCarbon != null){
                            int validator = 0;   
                            try {
                                validator = new ControlDB_MvtoCarbon(tipoConexion).actualizarVeh??culosPesoCero(mvtoCarbon, mvtoCarbon_Actualizar, user);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (UnknownHostException ex) {
                                Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SocketException ex) {
                                Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if(validator==1){
                                ArrayList<MvtoCarbon_ListadoEquipos> listadoMvtoCarbon_ListadoEquipos = null;
                                try {
                                    listadoMvtoCarbon_ListadoEquipos = new ControlDB_MvtoCarbon(tipoConexion).buscarMvtoCarbonParticular(mvtoCarbon);
                                } catch (SQLException ex) {
                                    Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                int contadorT=0;
                                if(listadoMvtoCarbon_ListadoEquipos != null){
                                    System.out.println(""+listadoMvtoCarbon_ListadoEquipos.get(contadorT).getMvtoCarbon().getPlaca());
                                    contadorT++;
                                    for(MvtoCarbon_ListadoEquipos objeto : listadoMvtoCarbon_ListadoEquipos){
                                        try {
                                            new ControlDB_MvtoCarbon(tipoConexion).ProcesarEnCcargaGP(objeto,user);
                                        } catch (UnknownHostException ex) {
                                            Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (SocketException ex) {
                                            Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (SQLException ex) {
                                            Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                                
                                JOptionPane.showMessageDialog(null, "Se actualiz?? la placa "+mvtoCarbon_Actualizar.getPlaca()+" de forma exitosa", "Registro Exitoso",JOptionPane.INFORMATION_MESSAGE);
                                mvtoCarbon_Actualizar=null;
                                mvtoCarbon=null;
                                
                                //Limpiamos los campos
                                PesoCero_placa.setText("...........................................");
                                PesoCero_deposito.setText("...........................................");
                                PesoCero_consecutivo.setText("...........................................");
                                PesoCero_pesoVacio.setText("...........................................");
                                PesoCero_pesoLleno.setText("...........................................");
                                PesoCero_pesoNeto.setText("...........................................");
                                PesoCero_fechaEntrada.setText("...........................................");    
                                PesoCero_fechaSalida.setText("...........................................");  
                                PesoCero_transportadora.setText("...........................................");   
                                PesoCero_cliente.setText("...........................................");
                                PesoCero_articulo.setText("..........................................."); 
                                
                                
                                PesoCero_placa_nuevo.setText("...........................................");
                                PesoCero_deposito_nuevo.setText("...........................................");
                                PesoCero_consecutivo_nuevo.setText("...........................................");
                                PesoCero_pesoVacio_nuevo.setText("...........................................");
                                PesoCero_pesoLleno_nuevo.setText("...........................................");
                                PesoCero_pesoNeto_nuevo.setText("...........................................");
                                PesoCero_fechaEntrada_nuevo.setText("...........................................");    
                                PesoCero_fechaSalida_nuevo.setText("...........................................");  
                                PesoCero_transportadora_nuevo.setText("...........................................");   
                                PesoCero_cliente_nuevo.setText("...........................................");
                                PesoCero_articulo_nuevo.setText("..........................................."); 
                                
                                VentanaInterna_PesoCero.show(false);
                                
                                buscadorPlaca.setText("");
                                //Eliminar los registros
                                DefaultTableModel modeloEliminar =(DefaultTableModel)tabla1.getModel();
                                int CantEliminar= tabla1.getRowCount() -1;
                                for(int i =CantEliminar; i>=0; i--){
                                    modeloEliminar.removeRow(i);
                                }
                                listadoMvtoCarbon=null;
        
        
                                validarSeleccionCampos();
                                generarListadoMvtoCarbon();
                                resizeColumnWidth(tabla);
                            }else{
                                JOptionPane.showMessageDialog(null, "Error al actualizar registro", "Error!!",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    //}
                /*}catch(Exception e){
                    e.printStackTrace();
                } */  
            //}
        }else{
            JOptionPane.showMessageDialog(null, "Debe cargar un veh??culo para ser reemplazado por el registr?? actual", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void checkPesoCeroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkPesoCeroItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_checkPesoCeroItemStateChanged

    private void checkPesoCeroCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_checkPesoCeroCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_checkPesoCeroCaretPositionChanged

    private void checkPesoCeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPesoCeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkPesoCeroActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Costo_Lavado_Veh??culo;
    private javax.swing.JMenuItem Enable;
    private javax.swing.JRadioButton Equipo_Quien_Realiza_LavadoVeh??culo_C??digo;
    private javax.swing.JRadioButton Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n;
    private javax.swing.JRadioButton Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo;
    private javax.swing.JPopupMenu Inactivar;
    private javax.swing.JInternalFrame InternaFrame_VisualizarMvtoCarbon;
    private javax.swing.JInternalFrame InternaFrame_buscarCliente;
    private javax.swing.JInternalFrame InternaFrame_buscarUsuario;
    private javax.swing.JInternalFrame InternalFrameSelectorCampos;
    private javax.swing.JRadioButton Motivo_No_LavadoVeh??culo;
    private javax.swing.JLabel MvtCarbon_FechaRegistro;
    private javax.swing.JLabel MvtCarbon_articulo;
    private javax.swing.JLabel MvtCarbon_auxiliarCentroCostoDestino;
    private javax.swing.JLabel MvtCarbon_auxiliarCentroCostoOrigen;
    private javax.swing.JLabel MvtCarbon_centroOperacion;
    private javax.swing.JLabel MvtCarbon_cliente;
    private javax.swing.JLabel MvtCarbon_codigo;
    private javax.swing.JLabel MvtCarbon_conexionCcarga;
    private javax.swing.JLabel MvtCarbon_consecutivo;
    private javax.swing.JLabel MvtCarbon_costoLavadoVeh??culo;
    private javax.swing.JLabel MvtCarbon_deposito;
    private javax.swing.JLabel MvtCarbon_equipoLavadoC??digo;
    private javax.swing.JLabel MvtCarbon_equipoLavadoDescripci??n;
    private javax.swing.JLabel MvtCarbon_fechaEntradaVehiculo;
    private javax.swing.JLabel MvtCarbon_fechaFinalDescargue;
    private javax.swing.JLabel MvtCarbon_fechaInicioDescargue;
    private javax.swing.JLabel MvtCarbon_fechaSalidaVehiculo;
    private javax.swing.JLabel MvtCarbon_laborRealizada;
    private javax.swing.JLabel MvtCarbon_lavadoVeh??culo;
    private javax.swing.JLabel MvtCarbon_motivoNoLavadoVehiculo;
    private javax.swing.JTextArea MvtCarbon_observacion;
    private javax.swing.JLabel MvtCarbon_observaci??nLavadoVeh??culo;
    private javax.swing.JLabel MvtCarbon_pesoLleno;
    private javax.swing.JLabel MvtCarbon_pesoNeto;
    private javax.swing.JLabel MvtCarbon_pesoVacio;
    private javax.swing.JLabel MvtCarbon_placa;
    private javax.swing.JLabel MvtCarbon_subCentroOperacion;
    private javax.swing.JLabel MvtCarbon_transportadora;
    private javax.swing.JLabel MvtCarbon_usuarioQuienCierra;
    private javax.swing.JLabel MvtCarbon_usuarioQuienInicia;
    private javax.swing.JLabel MvtCarbon_valorRecaudoEmpresa;
    private javax.swing.JLabel MvtCarbon_valorRecaudoEquipo;
    private javax.swing.JLabel MvtEquipo_codigo;
    private javax.swing.JLabel MvtEquipo_tipo;
    private javax.swing.JLabel MvtEquipo_usuarioQuienCierra;
    private javax.swing.JLabel MvtEquipo_usuarioQuienInicia;
    private javax.swing.JMenuItem PesoCero1;
    private javax.swing.JLabel PesoCero_articulo;
    private javax.swing.JLabel PesoCero_articulo_nuevo;
    private javax.swing.JLabel PesoCero_cliente;
    private javax.swing.JLabel PesoCero_cliente_nuevo;
    private javax.swing.JLabel PesoCero_consecutivo;
    private javax.swing.JLabel PesoCero_consecutivo_nuevo;
    private javax.swing.JLabel PesoCero_deposito;
    private javax.swing.JLabel PesoCero_deposito_nuevo;
    private javax.swing.JLabel PesoCero_fechaEntrada;
    private javax.swing.JLabel PesoCero_fechaEntrada_nuevo;
    private javax.swing.JLabel PesoCero_fechaSalida;
    private javax.swing.JLabel PesoCero_fechaSalida_nuevo;
    private javax.swing.JLabel PesoCero_pesoLleno;
    private javax.swing.JLabel PesoCero_pesoLleno_nuevo;
    private javax.swing.JLabel PesoCero_pesoNeto;
    private javax.swing.JLabel PesoCero_pesoNeto_nuevo;
    private javax.swing.JLabel PesoCero_pesoVacio;
    private javax.swing.JLabel PesoCero_pesoVacio_nuevo;
    private javax.swing.JLabel PesoCero_placa;
    private javax.swing.JLabel PesoCero_placa_nuevo;
    private javax.swing.JLabel PesoCero_transportadora;
    private javax.swing.JLabel PesoCero_transportadora_nuevo;
    private javax.swing.JRadioButton Valor_Recaudo_Empresa_LavadoVeh??culos;
    private javax.swing.JRadioButton Valor_Recaudo_Equipo_LavadoVeh??culos;
    private javax.swing.JInternalFrame VentanaInterna_PesoCero;
    private javax.swing.JLabel alerta_fechaFinal;
    private javax.swing.JLabel alerta_fechaInicio;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarCliente;
    private javax.swing.JButton btnCancelarUsuariosQuienRegistra;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnConsultarCliente;
    private javax.swing.JButton btnConsultarUsuariosQuienRegistra;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLimpiarCliente;
    private javax.swing.JButton btnLimpiarUsuariosQuienRegistra;
    private javax.swing.JTextField buscadorPlaca;
    private javax.swing.JRadioButton checkCliente;
    private javax.swing.JRadioButton checkPesoCero;
    private javax.swing.JRadioButton checkPlaca;
    private javax.swing.JRadioButton checkUsuarioQuienRegistra;
    private javax.swing.JRadioButton check_MvtoCarbon_FinalizacionActividad;
    private javax.swing.JRadioButton check_MvtoCarbon_InicioActividad;
    private javax.swing.JRadioButton check_MvtoCarbon_UsuarioCierraActividad;
    private javax.swing.JRadioButton check_MvtoCarbon_UsuarioIniciaActividad;
    private javax.swing.JRadioButton check_MvtoEquipo_FinalizacionActividad;
    private javax.swing.JRadioButton check_MvtoEquipo_InicioActividad;
    private javax.swing.JRadioButton check_MvtoEquipo_UsuarioCierraActividad;
    private javax.swing.JRadioButton check_MvtoEquipo_UsuarioIniciaActividad;
    private javax.swing.JButton consultar;
    private com.toedter.calendar.JDateChooser fechaFin;
    private com.toedter.calendar.JDateChooser fechaFinActividad_MvtoCarbon;
    private com.toedter.calendar.JDateChooser fechaFinActividad_MvtoEquipo;
    private com.toedter.calendar.JDateChooser fechaFin_destareVehiculo;
    private com.toedter.calendar.JDateChooser fechaInicialActividad_MvtoCarbon;
    private com.toedter.calendar.JDateChooser fechaInicialActividad_MvtoEquipo;
    private com.toedter.calendar.JDateChooser fechaInicio;
    private com.toedter.calendar.JDateChooser fechaInicio_destareVehiculo;
    private javax.swing.JComboBox<String> horaFin;
    private javax.swing.JComboBox<String> horaFin_destareVehiculo;
    private javax.swing.JComboBox<String> horaInicio;
    private javax.swing.JComboBox<String> horaInicio_destareVehiculo;
    private javax.swing.JLabel horarioTiempoIFinalMvtoCarbon_Procesar;
    private javax.swing.JLabel horarioTiempoIFinal_destareVehiculo;
    private javax.swing.JLabel horarioTiempoInicioMvtoCarbon_Procesar;
    private javax.swing.JLabel horarioTiempoInicio_destareVehiculo;
    private javax.swing.JLabel icon_buscarCliente;
    private javax.swing.JLabel icon_buscarUsuarioQuienRegistra;
    private javax.swing.JLabel icon_exportar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel label_MvtoCarbon_HoraFinal;
    private javax.swing.JLabel label_MvtoCarbon_HoraInicial;
    private javax.swing.JLabel label_MvtoCarbon_ZonaHorariaFinal;
    private javax.swing.JLabel label_MvtoCarbon_ZonaHorariaInicial;
    private javax.swing.JLabel label_MvtoCarbon_separadorFinal;
    private javax.swing.JLabel label_MvtoCarbon_separadorInicial;
    private javax.swing.JLabel label_MvtoEquipo_HoraFinal;
    private javax.swing.JLabel label_MvtoEquipo_HoraInicial;
    private javax.swing.JLabel label_MvtoEquipo_ZonaHorariaFinal;
    private javax.swing.JLabel label_MvtoEquipo_ZonaHorariaInicial;
    private javax.swing.JLabel label_MvtoEquipo_separadorFinal;
    private javax.swing.JLabel label_MvtoEquipo_separadorInicial;
    private javax.swing.JLabel label_QuienRealizaLavadoVeh??culo;
    private javax.swing.JLabel label_cliente;
    private javax.swing.JLabel label_exportar;
    private javax.swing.JLabel label_motivoNoLavado;
    private javax.swing.JTextField label_placa;
    private javax.swing.JLabel label_usuarioQuienRegistra;
    private javax.swing.JLabel limpiar_cliente;
    private javax.swing.JLabel limpiar_placa;
    private javax.swing.JLabel limpiar_usuario;
    private javax.swing.JComboBox<String> listadoEquiposDescargue;
    private javax.swing.JComboBox<String> listadoEquiposDescargue1;
    private javax.swing.JComboBox<String> minutoFin;
    private javax.swing.JComboBox<String> minutoFin_destareVehiculo;
    private javax.swing.JComboBox<String> minutoInicio;
    private javax.swing.JComboBox<String> minutoInicio_destareVehiculo;
    private javax.swing.JLabel mvtEquipo_ActividadFinalizada;
    private javax.swing.JLabel mvtEquipo_CantidadHoras;
    private javax.swing.JLabel mvtEquipo_Equipo_descripci??n;
    private javax.swing.JLabel mvtEquipo_Equipo_marca;
    private javax.swing.JLabel mvtEquipo_Equipo_modelo;
    private javax.swing.JLabel mvtEquipo_Equipo_pertenencia;
    private javax.swing.JLabel mvtEquipo_Equipo_producto;
    private javax.swing.JLabel mvtEquipo_Equipo_proveedor;
    private javax.swing.JLabel mvtEquipo_Equipo_serial;
    private javax.swing.JLabel mvtEquipo_FechaFinalActividad;
    private javax.swing.JLabel mvtEquipo_FechaInicioActividad;
    private javax.swing.JLabel mvtEquipo_Minutos;
    private javax.swing.JLabel mvtEquipo_MotivoFinalizaci??n;
    private javax.swing.JLabel mvtEquipo_estado;
    private javax.swing.JLabel mvtEquipo_laborRealizada;
    private javax.swing.JComboBox<String> mvtoEquipo_selectRazonFinalzaci??n;
    private javax.swing.JComboBox<Integer> pageJComboBox;
    public javax.swing.JPanel paginationPanel;
    private javax.swing.JButton registrar_MvtoCarbon;
    private javax.swing.JButton registrar_MvtoEquipo;
    private javax.swing.JRadioButton selectAll;
    private javax.swing.JRadioButton selectAsignacion_cantidadMinutos_Programados;
    private javax.swing.JRadioButton selectAsignacion_codigo;
    private javax.swing.JRadioButton selectAsignacion_equipoCodigo;
    private javax.swing.JRadioButton selectAsignacion_equipoDescripcion;
    private javax.swing.JRadioButton selectAsignacion_equipoMarca;
    private javax.swing.JRadioButton selectAsignacion_equipoModelo;
    private javax.swing.JRadioButton selectAsignacion_equipoPertenencia;
    private javax.swing.JRadioButton selectAsignacion_equipoTipo;
    private javax.swing.JRadioButton selectAsignacion_fechaFinalizacion;
    private javax.swing.JRadioButton selectAsignacion_fechaInicio;
    private javax.swing.JRadioButton selectAsignacion_fechaRegistro;
    private javax.swing.JRadioButton selectDescargue_articuloCodigo;
    private javax.swing.JRadioButton selectDescargue_articuloCodigoERP;
    private javax.swing.JRadioButton selectDescargue_articuloNombre;
    private javax.swing.JRadioButton selectDescargue_articuloTipo;
    private javax.swing.JRadioButton selectDescargue_articuloUnidadNegocio;
    private javax.swing.JRadioButton selectDescargue_cantidadMinutosDescargue;
    private javax.swing.JRadioButton selectDescargue_centroCosto;
    private javax.swing.JRadioButton selectDescargue_centroCostoAuxiliarDestino;
    private javax.swing.JRadioButton selectDescargue_centroCostoAuxiliarOrigen;
    private javax.swing.JRadioButton selectDescargue_centroCostoMayor;
    private javax.swing.JRadioButton selectDescargue_centroOperacion;
    private javax.swing.JRadioButton selectDescargue_clienteCodigo;
    private javax.swing.JRadioButton selectDescargue_clienteNombre;
    private javax.swing.JRadioButton selectDescargue_codigo;
    private javax.swing.JRadioButton selectDescargue_conexionPesoCcarga;
    private javax.swing.JRadioButton selectDescargue_consecutivo;
    private javax.swing.JRadioButton selectDescargue_deposito;
    private javax.swing.JRadioButton selectDescargue_dia;
    private javax.swing.JRadioButton selectDescargue_estado;
    private javax.swing.JRadioButton selectDescargue_fechaEntradaVehiculo;
    private javax.swing.JRadioButton selectDescargue_fechaFinDescargue;
    private javax.swing.JRadioButton selectDescargue_fechaInicioDescargue;
    private javax.swing.JRadioButton selectDescargue_fechaRegistro;
    private javax.swing.JRadioButton selectDescargue_fechaSalidaVehiculo;
    private javax.swing.JRadioButton selectDescargue_laborRealizada;
    private javax.swing.JRadioButton selectDescargue_lavadoVehiculo;
    private javax.swing.JRadioButton selectDescargue_lavadoVehiculoObservacion;
    private javax.swing.JRadioButton selectDescargue_mes;
    private javax.swing.JRadioButton selectDescargue_numOrden;
    private javax.swing.JRadioButton selectDescargue_observaci??n;
    private javax.swing.JRadioButton selectDescargue_pesoLleno;
    private javax.swing.JRadioButton selectDescargue_pesoNeto;
    private javax.swing.JRadioButton selectDescargue_pesoVacio;
    private javax.swing.JRadioButton selectDescargue_placa;
    private javax.swing.JRadioButton selectDescargue_registroManual;
    private javax.swing.JRadioButton selectDescargue_subcentroCosto;
    private javax.swing.JRadioButton selectDescargue_transportadoraCodigo;
    private javax.swing.JRadioButton selectDescargue_transportadoraNit;
    private javax.swing.JRadioButton selectDescargue_transportadoraNombre;
    private javax.swing.JRadioButton selectDescargue_usuarioQuienCierraVehiculoCodigo;
    private javax.swing.JRadioButton selectDescargue_usuarioQuienCierraVehiculoNombre;
    private javax.swing.JRadioButton selectDescargue_usuarioQuienRegistraVehiculoCodigo;
    private javax.swing.JRadioButton selectDescargue_usuarioQuienRegistraVehiculoCorreo;
    private javax.swing.JRadioButton selectDescargue_usuarioQuienRegistraVehiculoNombre;
    private javax.swing.JRadioButton selectDescargue_usuarioRegistroManualCodigo;
    private javax.swing.JRadioButton selectDescargue_usuarioRegistroManualCorreo;
    private javax.swing.JRadioButton selectDescargue_usuarioRegistroManualNombre;
    private javax.swing.JRadioButton selectMvtoEquipo_CausalInactividad;
    private javax.swing.JRadioButton selectMvtoEquipo_DesdeCarbon;
    private javax.swing.JRadioButton selectMvtoEquipo_Inactividad;
    private javax.swing.JRadioButton selectMvtoEquipo_MotivoParada;
    private javax.swing.JRadioButton selectMvtoEquipo_Recobro;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioAutorizaRecobro_codigo;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioAutorizaRecobro_nombre;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioAutorizaRecobro_observacion;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioCierraMvto_codigo;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioCierraMvto_nombre;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioInactividad_codigo;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioInactividad_nombre;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioRegistraMvto_codigo;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioRegistraMvto_nombre;
    private javax.swing.JRadioButton selectMvtoEquipo_ValorHoraEquipo;
    private javax.swing.JRadioButton selectMvtoEquipo_articuloCodigo;
    private javax.swing.JRadioButton selectMvtoEquipo_articuloDescripcion;
    private javax.swing.JRadioButton selectMvtoEquipo_autorizacionRecobro;
    private javax.swing.JRadioButton selectMvtoEquipo_cantidadMinutosDescargue;
    private javax.swing.JRadioButton selectMvtoEquipo_centroCosto;
    private javax.swing.JRadioButton selectMvtoEquipo_centroCostoAuxiliarDestino;
    private javax.swing.JRadioButton selectMvtoEquipo_centroCostoAxiliarOrigen;
    private javax.swing.JRadioButton selectMvtoEquipo_centroCostoMayor;
    private javax.swing.JRadioButton selectMvtoEquipo_centroOperacion;
    private javax.swing.JRadioButton selectMvtoEquipo_clienteCodigo;
    private javax.swing.JRadioButton selectMvtoEquipo_clienteNombre;
    private javax.swing.JRadioButton selectMvtoEquipo_codigo;
    private javax.swing.JRadioButton selectMvtoEquipo_costoTotal;
    private javax.swing.JRadioButton selectMvtoEquipo_estado;
    private javax.swing.JRadioButton selectMvtoEquipo_fechaFinDescargue;
    private javax.swing.JRadioButton selectMvtoEquipo_fechaInicioDescargue;
    private javax.swing.JRadioButton selectMvtoEquipo_fechaRegistro;
    private javax.swing.JRadioButton selectMvtoEquipo_laborRealizada;
    private javax.swing.JRadioButton selectMvtoEquipo_mes;
    private javax.swing.JRadioButton selectMvtoEquipo_motonaveCodigo;
    private javax.swing.JRadioButton selectMvtoEquipo_motonaveDescripci??n;
    private javax.swing.JRadioButton selectMvtoEquipo_numeroOrden;
    private javax.swing.JRadioButton selectMvtoEquipo_observacion;
    private javax.swing.JRadioButton selectMvtoEquipo_proveedorEquipoNit;
    private javax.swing.JRadioButton selectMvtoEquipo_proveedorEquipoNombre;
    private javax.swing.JRadioButton selectMvtoEquipo_subcentroCosto;
    private javax.swing.JComboBox<String> select_MvtoCarbon_CCAuxiliar;
    private javax.swing.JComboBox<String> select_MvtoCarbon_CCAuxiliarDestino;
    private javax.swing.JComboBox<String> select_MvtoCarbon_CO;
    private javax.swing.JComboBox<String> select_MvtoCarbon_EquipoQuienRealizaLavadoVeh??culo;
    private javax.swing.JComboBox<String> select_MvtoCarbon_HoraFinal;
    private javax.swing.JComboBox<String> select_MvtoCarbon_HoraInicial;
    private javax.swing.JComboBox<String> select_MvtoCarbon_MinutosFinal;
    private javax.swing.JComboBox<String> select_MvtoCarbon_MinutosInicial;
    private javax.swing.JComboBox<String> select_MvtoCarbon_SubcentroCosto;
    private javax.swing.JComboBox<String> select_MvtoCarbon_UsuarioCierraRegistro;
    private javax.swing.JComboBox<String> select_MvtoCarbon_UsuarioIniciaRegistro;
    private javax.swing.JComboBox<String> select_MvtoCarbon_laborRealizada;
    private javax.swing.JComboBox<String> select_MvtoCarbon_lavadoVeh??culo;
    private javax.swing.JComboBox<String> select_MvtoCarbon_motivoNoLavadoVehiculo;
    private javax.swing.JComboBox<String> select_MvtoEquipo_Equipo;
    private javax.swing.JComboBox<String> select_MvtoEquipo_HoraFinal;
    private javax.swing.JComboBox<String> select_MvtoEquipo_HoraInicial;
    private javax.swing.JComboBox<String> select_MvtoEquipo_MinutosFinal;
    private javax.swing.JComboBox<String> select_MvtoEquipo_MinutosInicial;
    private javax.swing.JComboBox<String> select_MvtoEquipo_UsuarioCierraRegistro;
    private javax.swing.JComboBox<String> select_MvtoEquipo_UsuarioIniciaRegistro;
    private javax.swing.JLabel select_MvtoEquipo_laborRealizada;
    private javax.swing.JComboBox<String> select_MvtoEquipo_marcaEquipo;
    private javax.swing.JComboBox<String> select_MvtoEquipo_recobro;
    private javax.swing.JComboBox<String> select_MvtoEquipo_tipoEquipo;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla1;
    private javax.swing.JTable tablaCliente;
    private javax.swing.JTable tablaUsuario;
    private javax.swing.JTextArea textArea_MvtoCarbon_razonModificacion;
    private javax.swing.JTextArea textArea_MvtoEquipo_razonModificacion;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel titulo12;
    private javax.swing.JLabel titulo13;
    private javax.swing.JLabel titulo17;
    private javax.swing.JLabel titulo19;
    private javax.swing.JLabel titulo2;
    private javax.swing.JLabel titulo20;
    private javax.swing.JLabel titulo21;
    private javax.swing.JLabel titulo23;
    private javax.swing.JLabel titulo24;
    private javax.swing.JLabel titulo25;
    private javax.swing.JLabel titulo26;
    private javax.swing.JLabel titulo27;
    private javax.swing.JLabel titulo29;
    private javax.swing.JLabel titulo30;
    private javax.swing.JLabel titulo45;
    private javax.swing.JLabel titulo46;
    private javax.swing.JLabel titulo47;
    private javax.swing.JLabel titulo48;
    private javax.swing.JLabel titulo49;
    private javax.swing.JLabel titulo50;
    private javax.swing.JLabel titulo51;
    private javax.swing.JLabel titulo52;
    private javax.swing.JLabel titulo53;
    private javax.swing.JLabel titulo59;
    private javax.swing.JLabel titulo60;
    private javax.swing.JLabel titulo61;
    private javax.swing.JLabel titulo62;
    private javax.swing.JLabel titulo63;
    private javax.swing.JLabel titulo64;
    private javax.swing.JTextField valorBusquedaCliente;
    private javax.swing.JTextField valorBusquedaUsuario;
    // End of variables declaration//GEN-END:variables
    public void cambioEstado (String data){
        switch(data){
            case "Centro_Operacion":{
                //Cargamos en la interfaz los subcentro de costos activos
                try {
                    if(listadoCentroOperacion_mvtoCarbon !=null){
                        listadoCentroCostoSubCentro_mvtoCarbon=new ControlDB_CentroCostoSubCentro(tipoConexion).buscarActivos(listadoCentroOperacion_mvtoCarbon.get(select_MvtoCarbon_CO.getSelectedIndex()));
                        if(listadoCentroCostoSubCentro_mvtoCarbon != null){
                            String datosObjeto[]= new String[listadoCentroCostoSubCentro_mvtoCarbon.size()];
                            int contador=0;
                            for(CentroCostoSubCentro Objeto : listadoCentroCostoSubCentro_mvtoCarbon){ 
                                datosObjeto[contador]=Objeto.getDescripcion();
                                contador++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            select_MvtoCarbon_SubcentroCosto.setModel(model);
                        }else{
                            select_MvtoCarbon_SubcentroCosto.removeAllItems();
                        }
                    }else{
                        select_MvtoCarbon_CO.removeAllItems();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                } 
                //Cargaos los auxiliares
                if(listadoCentroCostoSubCentro_mvtoCarbon != null){
                    try {
                        listadoCentroCostoAuxiliar_mvtoCarbon = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro_mvtoCarbon.get(select_MvtoCarbon_SubcentroCosto.getSelectedIndex()).getCodigo());
                        if(listadoCentroCostoAuxiliar_mvtoCarbon != null){
                            String datosObjeto[] = new String[listadoCentroCostoAuxiliar_mvtoCarbon.size()];
                            int contador = 0;
                            for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliar_mvtoCarbon) {
                                datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                                contador++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            select_MvtoCarbon_CCAuxiliar.setModel(model);
                        }else{
                            select_MvtoCarbon_CCAuxiliar.removeAllItems();
                        } 
                    }catch (SQLException e){ 
                    }
                    //Cargamos las labores realizadas
                    try {
                        listadoLaborRealizada_mvtoCarbon=new ControlDB_LaborRealizada(tipoConexion).buscarPorSubcentroCosto(listadoCentroCostoSubCentro_mvtoCarbon.get(select_MvtoCarbon_SubcentroCosto.getSelectedIndex()));
                        if(listadoLaborRealizada_mvtoCarbon != null){
                            String datosObjeto[]= new String[listadoLaborRealizada_mvtoCarbon.size()];
                            int contador=0;
                            for(LaborRealizada Objeto : listadoLaborRealizada_mvtoCarbon){ 
                                datosObjeto[contador]=Objeto.getDescripcion();
                                contador++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            select_MvtoCarbon_laborRealizada.setModel(model);
                        }else{
                            select_MvtoCarbon_laborRealizada.removeAllItems();
                        } 
                    } catch (SQLException ex) {
                        Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                }else{
                    select_MvtoCarbon_SubcentroCosto.removeAllItems();
                    select_MvtoCarbon_CCAuxiliar.removeAllItems();
                    //select_MvtoCarbon_laborRealizada.removeAllItems();//a??adido para verificar
                } 
                break;
            }
            case "Subcentro_Costo":{
                /*if(listadoCentroCostoSubCentro_mvtoCarbon != null){
                    try {
                        listadoCentroCostoAuxiliar_mvtoCarbon = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro_mvtoCarbon.get(select_MvtoCarbon_SubcentroCosto.getSelectedIndex()).getCodigo());
                        if(listadoCentroCostoAuxiliar_mvtoCarbon != null){
                            String datosObjeto[] = new String[listadoCentroCostoAuxiliar_mvtoCarbon.size()];
                            int contador = 0;
                            for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliar_mvtoCarbon) {
                                datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                                contador++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            select_MvtoCarbon_CCAuxiliar.setModel(model);
                        }else{
                            select_MvtoCarbon_CCAuxiliar.removeAllItems();
                        } 
                        try {
                            listadoLaborRealizada_mvtoCarbon=new ControlDB_LaborRealizada(tipoConexion).buscarPorSubcentroCosto(listadoCentroCostoSubCentro_mvtoCarbon.get(select_MvtoCarbon_SubcentroCosto.getSelectedIndex()));
                            if(listadoLaborRealizada_mvtoCarbon != null){
                                String datosObjeto[]= new String[listadoLaborRealizada_mvtoCarbon.size()];
                                int contador=0;
                                for(LaborRealizada Objeto : listadoLaborRealizada_mvtoCarbon){ 
                                    datosObjeto[contador]=Objeto.getDescripcion();
                                    contador++;
                                }
                                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                select_MvtoCarbon_laborRealizada.setModel(model);
                            }else{
                                select_MvtoCarbon_laborRealizada.removeAllItems();
                            } 
                        } catch (SQLException ex) {
                            Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                        }  
                    }catch (SQLException e){ 
                    }
                }else{
                    select_MvtoCarbon_SubcentroCosto.removeAllItems();
                    select_MvtoCarbon_laborRealizada.removeAllItems();
                    select_MvtoCarbon_CCAuxiliar.removeAllItems();
                }*/
                if(listadoCentroCostoSubCentro_mvtoCarbon != null){
                    try {
                        listadoCentroCostoAuxiliar_mvtoCarbon = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro_mvtoCarbon.get(select_MvtoCarbon_SubcentroCosto.getSelectedIndex()).getCodigo());
                        if(listadoCentroCostoAuxiliar_mvtoCarbon != null){
                            String datosObjeto[] = new String[listadoCentroCostoAuxiliar_mvtoCarbon.size()];
                            int contador = 0;
                            for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliar_mvtoCarbon) {
                                datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                                contador++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            select_MvtoCarbon_CCAuxiliar.setModel(model);
                        }else{
                            select_MvtoCarbon_CCAuxiliar.removeAllItems();
                        } 
                        try {
                            listadoLaborRealizada_mvtoCarbon=new ControlDB_LaborRealizada(tipoConexion).buscarPorSubcentroCosto(listadoCentroCostoSubCentro_mvtoCarbon.get(select_MvtoCarbon_SubcentroCosto.getSelectedIndex()));
                            if(listadoLaborRealizada_mvtoCarbon != null){
                                String datosObjeto[]= new String[listadoLaborRealizada_mvtoCarbon.size()];
                                int contador=0;
                                for(LaborRealizada Objeto : listadoLaborRealizada_mvtoCarbon){ 
                                    datosObjeto[contador]=Objeto.getDescripcion();
                                    contador++;
                                }
                                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                select_MvtoCarbon_laborRealizada.setModel(model);
                            }else{
                                select_MvtoCarbon_laborRealizada.removeAllItems();
                            } 
                        } catch (SQLException ex) {
                            Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                        }  
                    }catch (SQLException e){ 
                    }
                }else{
                    select_MvtoCarbon_SubcentroCosto.removeAllItems();
                    select_MvtoCarbon_laborRealizada.removeAllItems();
                    select_MvtoCarbon_CCAuxiliar.removeAllItems();
                }

                //validamos si la labor Realiza seleccionada tiene bodega Destino
                if(listadoLaborRealizada_mvtoCarbon != null){
                    if(listadoLaborRealizada_mvtoCarbon.get(select_MvtoCarbon_laborRealizada.getSelectedIndex()).getBodegaDestino().equals("1")){//Fue seleccionada una labor Realizada que tiene marcada Bodega destino por tal motivo cargalos las Bodega destino
                       // listadoCentroCostoAuxiliarDestino_mvtoCarbon
                        if(listadoCentroCostoSubCentro_mvtoCarbon != null){
                            try {
                                listadoCentroCostoAuxiliarDestino_mvtoCarbon = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro_mvtoCarbon.get(select_MvtoCarbon_SubcentroCosto.getSelectedIndex()).getCodigo());
                                if(listadoCentroCostoAuxiliarDestino_mvtoCarbon != null){
                                    String datosObjeto[] = new String[listadoCentroCostoAuxiliarDestino_mvtoCarbon.size()];
                                    int contador = 0;
                                    for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliarDestino_mvtoCarbon) {
                                        datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                                        contador++;
                                    }
                                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                    select_MvtoCarbon_CCAuxiliarDestino.setModel(model);
                                }else{
                                    select_MvtoCarbon_CCAuxiliarDestino.removeAllItems();
                                } 
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }            
                    }else{
                       select_MvtoCarbon_CCAuxiliarDestino.removeAllItems(); 
                    }
                }else{
                    select_MvtoCarbon_laborRealizada.removeAllItems();
                    select_MvtoCarbon_CCAuxiliarDestino.removeAllItems(); 
                }
                break;
            }
            case "CentroCosto_Auxiliar":{
                break;
                
            }
        }
    }
    public void tabla_Listar(String data1, String data2) throws SQLException{
        tabla.setModel(crearModeloDeTabla());
        String script = " ";
        //validamos los select
        script += "[mc_fecha] BETWEEN '"+data1+"' AND '"+data2+"' ";
        if(checkPlaca.isSelected()){
            script += " AND [mc_placa_vehiculo] LIKE '%"+label_placa.getText()+"%' ";
        }
        if(checkCliente.isSelected()){
            script += " AND [mc_cliente_cdgo] LIKE '"+cliente.getCodigo()+"' AND [mc_cliente_base_datos_cdgo]="+cliente.getBaseDatos().getCodigo()+" ";
        }
        if(checkUsuarioQuienRegistra.isSelected()){
            script += " AND [mc_usuario_cdgo] LIKE '"+usuario.getCodigo()+"' ";
        }
        if(checkPesoCero.isSelected()){
            script += " AND [mc_peso_neto] = 0 ";
        }
        script += " AND [me_inactividad]=0 AND [mc_estad_mvto_carbon_cdgo]=1 "; 
        
        
        
        
        listado=new ControlDB_MvtoCarbon(tipoConexion).buscarMvtoCarbon_GenerarMatriz_Modificar(script);
        proveedorDeDatos = crearProveedorDeDatos(listado); 
        paginadorDeTabla = new PaginadorDeTabla(tabla, proveedorDeDatos, new int[]{5, 10, 20, 50, 75, 100}, 10);
        paginadorDeTabla.crearListadoDeFilasPermitidas(this.paginationPanel);
        pageJComboBox = paginadorDeTabla.getComboBoxPage();
        events();
        pageJComboBox.setSelectedItem(Integer.parseInt("50"));
    }
    public void generarListadoMvtoCarbon(){
        try{
            String fechaMvtoCarbon_Inicial="";
            String fechaMvtoCarbon_Final="";
            int contador=0;
            try{
                Calendar fechaI = fechaInicio.getCalendar();
                String anoI = ""+fechaI.get(Calendar.YEAR);
                String mesI = "";
                if((fechaI.get(Calendar.MONTH) +1) <=9){
                    mesI = "0"+(fechaI.get(Calendar.MONTH) + 1);
                }else{
                    mesI = ""+(fechaI.get(Calendar.MONTH) + 1);
                }
                String diaI = "";
                if(fechaI.get(Calendar.DAY_OF_MONTH) <=9){
                    diaI = "0"+fechaI.get(Calendar.DAY_OF_MONTH);
                }else{
                    diaI = ""+fechaI.get(Calendar.DAY_OF_MONTH);
                }
                fechaMvtoCarbon_Inicial=anoI+"-"+mesI+"-"+diaI;
                contador++;
            }catch(Exception e){
                alerta_fechaInicio.setText("Verifique la fecha de Inicio");
            }
            try{
                Calendar fechaF = fechaFin.getCalendar();
                String anoF = ""+fechaF.get(Calendar.YEAR);
                String mesF = "";
                if((fechaF.get(Calendar.MONTH) +1) <=9){
                                mesF = "0"+(fechaF.get(Calendar.MONTH) + 1);
                }else{
                    mesF = ""+(fechaF.get(Calendar.MONTH) + 1);
                }
                String diaF = "";
                if(fechaF.get(Calendar.DAY_OF_MONTH) <=9){
                    diaF = "0"+fechaF.get(Calendar.DAY_OF_MONTH);
                }else{
                    diaF = ""+fechaF.get(Calendar.DAY_OF_MONTH);
                }
                fechaMvtoCarbon_Final=anoF+"-"+mesF+"-"+diaF;
                contador++;
            }catch(Exception e){
                alerta_fechaFinal.setText("Verifique la fecha Final");
            } 
            if(contador==2){//Se validaron las dos fechas y contienen formato correcto
                fechaMvtoCarbon_Inicial = fechaMvtoCarbon_Inicial+" "+horaInicio.getSelectedItem().toString()+":"+minutoInicio.getSelectedItem().toString();
                fechaMvtoCarbon_Final = fechaMvtoCarbon_Final+" "+horaFin.getSelectedItem().toString()+":"+minutoFin.getSelectedItem().toString();
                try {
                    tabla_Listar(fechaMvtoCarbon_Inicial,fechaMvtoCarbon_Final);
                } catch (SQLException ex) {
                    Logger.getLogger(MvtoCarbon_LavadoVeh??culo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"No se puedo procesar el descargue de Carbon", "Advertencia",JOptionPane.ERROR_MESSAGE);
        }   
    }
    public void selectorCampoPorDefecto(){
        selectDescargue_codigo.setSelected(true);
        selectDescargue_centroOperacion.setSelected(true);
        selectDescargue_subcentroCosto.setSelected(true);
        selectDescargue_centroCostoAuxiliarOrigen.setSelected(true);
        selectDescargue_centroCostoAuxiliarDestino.setSelected(true);
        selectDescargue_centroCosto.setSelected(false);
        selectDescargue_centroCostoMayor.setSelected(false);
        selectDescargue_laborRealizada.setSelected(true);
        selectDescargue_articuloCodigo.setSelected(false);
        selectDescargue_articuloNombre.setSelected(true);
        selectDescargue_articuloTipo.setSelected(true);
        selectDescargue_articuloCodigoERP.setSelected(true);
        selectDescargue_articuloUnidadNegocio.setSelected(true);
        selectDescargue_clienteCodigo.setSelected(false);
        selectDescargue_clienteNombre.setSelected(true);
        selectDescargue_transportadoraCodigo.setSelected(false);
        selectDescargue_transportadoraNit.setSelected(false);
        selectDescargue_transportadoraNombre.setSelected(true);
        selectDescargue_mes.setSelected(true);
        selectDescargue_dia.setSelected(false);
        selectDescargue_fechaRegistro.setSelected(true);
        selectDescargue_lavadoVehiculo.setSelected(true);
        selectDescargue_lavadoVehiculoObservacion.setSelected(false); 
        Motivo_No_LavadoVeh??culo.setSelected(true);
        Equipo_Quien_Realiza_LavadoVeh??culo_C??digo.setSelected(true);
        Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo.setSelected(false);
        Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n.setSelected(true);
        Costo_Lavado_Veh??culo.setSelected(true);
        Valor_Recaudo_Empresa_LavadoVeh??culos.setSelected(true);
        Valor_Recaudo_Equipo_LavadoVeh??culos.setSelected(true);
        selectDescargue_numOrden.setSelected(true);
        selectDescargue_deposito.setSelected(true);
        selectDescargue_consecutivo.setSelected(false);
        selectDescargue_placa.setSelected(true);
        selectDescargue_pesoVacio.setSelected(false);
        selectDescargue_pesoLleno.setSelected(false);
        selectDescargue_pesoNeto.setSelected(true);
        selectDescargue_fechaEntradaVehiculo.setSelected(true);
        selectDescargue_fechaSalidaVehiculo.setSelected(true);
        selectDescargue_fechaInicioDescargue.setSelected(true);
        selectDescargue_fechaFinDescargue.setSelected(true);
        selectDescargue_cantidadMinutosDescargue.setSelected(true);
        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setSelected(false);
        selectDescargue_usuarioQuienRegistraVehiculoNombre.setSelected(true);
        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setSelected(false);
        selectDescargue_observaci??n.setSelected(false);
        selectDescargue_estado.setSelected(false);
        selectDescargue_conexionPesoCcarga.setSelected(false);
        selectDescargue_registroManual.setSelected(false);
        selectDescargue_usuarioRegistroManualCodigo.setSelected(false);
        selectDescargue_usuarioRegistroManualNombre.setSelected(false);
        selectDescargue_usuarioRegistroManualCorreo.setSelected(false);
        selectAsignacion_equipoCodigo.setSelected(false);
        selectAsignacion_equipoTipo.setSelected(false);
        selectAsignacion_equipoMarca.setSelected(false);
        selectAsignacion_equipoModelo.setSelected(false);
        selectAsignacion_equipoDescripcion.setSelected(true);
        selectAsignacion_equipoPertenencia.setSelected(true);
        selectAsignacion_codigo.setSelected(false);
        selectAsignacion_fechaRegistro.setSelected(false);
        selectAsignacion_fechaInicio.setSelected(false);
        selectAsignacion_fechaFinalizacion.setSelected(false);
        selectAsignacion_cantidadMinutos_Programados.setSelected(false);
        selectMvtoEquipo_codigo.setSelected(false);
        selectMvtoEquipo_mes.setSelected(false);
        selectMvtoEquipo_fechaRegistro.setSelected(false);
        selectMvtoEquipo_proveedorEquipoNit.setSelected(false);
        selectMvtoEquipo_proveedorEquipoNombre.setSelected(false);
        selectMvtoEquipo_numeroOrden.setSelected(false);
        selectMvtoEquipo_centroOperacion.setSelected(false);
        selectMvtoEquipo_subcentroCosto.setSelected(false);
        selectMvtoEquipo_centroCostoAxiliarOrigen.setSelected(false);
        selectMvtoEquipo_centroCostoAuxiliarDestino.setSelected(false);
        selectMvtoEquipo_centroCosto.setSelected(false);
        selectMvtoEquipo_centroCostoMayor.setSelected(false);
        selectMvtoEquipo_laborRealizada.setSelected(false);
        selectMvtoEquipo_clienteCodigo.setSelected(false);
        selectMvtoEquipo_clienteNombre.setSelected(false);
        selectMvtoEquipo_articuloCodigo.setSelected(false);
        selectMvtoEquipo_articuloDescripcion.setSelected(false);
        selectMvtoEquipo_motonaveCodigo.setSelected(false);
        selectMvtoEquipo_motonaveDescripci??n.setSelected(false);
        selectMvtoEquipo_fechaInicioDescargue.setSelected(false);
        selectMvtoEquipo_fechaFinDescargue.setSelected(false);
        selectMvtoEquipo_cantidadMinutosDescargue.setSelected(false);
        selectMvtoEquipo_ValorHoraEquipo.setSelected(false);
        selectMvtoEquipo_costoTotal.setSelected(false);
        selectMvtoEquipo_Recobro.setSelected(false);
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setSelected(false);
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setSelected(false);
        
        selectDescargue_usuarioQuienCierraVehiculoCodigo.setSelected(false);
        selectDescargue_usuarioQuienCierraVehiculoNombre.setSelected(false);
        selectMvtoEquipo_UsuarioCierraMvto_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioCierraMvto_nombre.setSelected(false);

        selectMvtoEquipo_autorizacionRecobro.setSelected(false);
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setSelected(false);
        selectMvtoEquipo_Inactividad.setSelected(false);
        selectMvtoEquipo_CausalInactividad.setSelected(false);
        selectMvtoEquipo_UsuarioInactividad_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioInactividad_nombre.setSelected(false);
        selectMvtoEquipo_MotivoParada.setSelected(false);
        selectMvtoEquipo_observacion.setSelected(false);
        selectMvtoEquipo_estado.setSelected(false);
        selectMvtoEquipo_DesdeCarbon.setSelected(false);
    }
    public void selectorCampoTodos(){
        selectDescargue_codigo.setSelected(true);
        selectDescargue_centroOperacion.setSelected(true);
        selectDescargue_subcentroCosto.setSelected(true);
        selectDescargue_centroCostoAuxiliarOrigen.setSelected(true);
        selectDescargue_centroCostoAuxiliarDestino.setSelected(true);
        selectDescargue_centroCosto.setSelected(true);
        selectDescargue_centroCostoMayor.setSelected(true);
        selectDescargue_laborRealizada.setSelected(true);
        selectDescargue_articuloCodigo.setSelected(true);
        selectDescargue_articuloNombre.setSelected(true);
        selectDescargue_articuloTipo.setSelected(true);
        selectDescargue_articuloCodigoERP.setSelected(true);
        selectDescargue_articuloUnidadNegocio.setSelected(true);
        selectDescargue_clienteCodigo.setSelected(true);
        selectDescargue_clienteNombre.setSelected(true);
        selectDescargue_transportadoraCodigo.setSelected(true);
        selectDescargue_transportadoraNit.setSelected(true);
        selectDescargue_transportadoraNombre.setSelected(true);
        selectDescargue_mes.setSelected(true);
        selectDescargue_dia.setSelected(true);
        selectDescargue_fechaRegistro.setSelected(true);
        selectDescargue_lavadoVehiculo.setSelected(true);
        selectDescargue_lavadoVehiculoObservacion.setSelected(true); 
        Motivo_No_LavadoVeh??culo.setSelected(true);
        Equipo_Quien_Realiza_LavadoVeh??culo_C??digo.setSelected(true);
        Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo.setSelected(true);
        Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n.setSelected(true);
        Costo_Lavado_Veh??culo.setSelected(true);
        Valor_Recaudo_Empresa_LavadoVeh??culos.setSelected(true);
        Valor_Recaudo_Equipo_LavadoVeh??culos.setSelected(true);
        selectDescargue_numOrden.setSelected(true);
        selectDescargue_deposito.setSelected(true);
        selectDescargue_consecutivo.setSelected(true);
        selectDescargue_placa.setSelected(true);
        selectDescargue_pesoVacio.setSelected(true);
        selectDescargue_pesoLleno.setSelected(true);
        selectDescargue_pesoNeto.setSelected(true);
        selectDescargue_fechaEntradaVehiculo.setSelected(true);
        selectDescargue_fechaSalidaVehiculo.setSelected(true);
        selectDescargue_fechaInicioDescargue.setSelected(true);
        selectDescargue_fechaFinDescargue.setSelected(true);
        selectDescargue_cantidadMinutosDescargue.setSelected(true);
        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setSelected(true);
        selectDescargue_usuarioQuienRegistraVehiculoNombre.setSelected(true);
        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setSelected(true);
        selectDescargue_observaci??n.setSelected(true);
        selectDescargue_estado.setSelected(true);
        selectDescargue_conexionPesoCcarga.setSelected(true);
        selectDescargue_registroManual.setSelected(true);
        selectDescargue_usuarioRegistroManualCodigo.setSelected(true);
        selectDescargue_usuarioRegistroManualNombre.setSelected(true);
        selectDescargue_usuarioRegistroManualCorreo.setSelected(true);
        selectAsignacion_equipoCodigo.setSelected(true);
        selectAsignacion_equipoTipo.setSelected(true);
        selectAsignacion_equipoMarca.setSelected(true);
        selectAsignacion_equipoModelo.setSelected(true);
        selectAsignacion_equipoDescripcion.setSelected(true);
        selectAsignacion_equipoPertenencia.setSelected(true);
        selectAsignacion_codigo.setSelected(true);
        selectAsignacion_fechaRegistro.setSelected(true);
        selectAsignacion_fechaInicio.setSelected(true);
        selectAsignacion_fechaFinalizacion.setSelected(true);
        selectAsignacion_cantidadMinutos_Programados.setSelected(true);
        selectMvtoEquipo_codigo.setSelected(true);
        selectMvtoEquipo_mes.setSelected(true);
        selectMvtoEquipo_fechaRegistro.setSelected(true);
        selectMvtoEquipo_proveedorEquipoNit.setSelected(true);
        selectMvtoEquipo_proveedorEquipoNombre.setSelected(true);
        selectMvtoEquipo_numeroOrden.setSelected(true);
        selectMvtoEquipo_centroOperacion.setSelected(true);
        selectMvtoEquipo_subcentroCosto.setSelected(true);
        selectMvtoEquipo_centroCostoAxiliarOrigen.setSelected(true);
        selectMvtoEquipo_centroCostoAuxiliarDestino.setSelected(true);
        selectMvtoEquipo_centroCosto.setSelected(true);
        selectMvtoEquipo_centroCostoMayor.setSelected(true);
        selectMvtoEquipo_laborRealizada.setSelected(true);
        selectMvtoEquipo_clienteCodigo.setSelected(true);
        selectMvtoEquipo_clienteNombre.setSelected(true);
        selectMvtoEquipo_articuloCodigo.setSelected(true);
        selectMvtoEquipo_articuloDescripcion.setSelected(true);
        selectMvtoEquipo_motonaveCodigo.setSelected(true);
        selectMvtoEquipo_motonaveDescripci??n.setSelected(true);
        selectMvtoEquipo_fechaInicioDescargue.setSelected(true);
        selectMvtoEquipo_fechaFinDescargue.setSelected(true);
        selectMvtoEquipo_cantidadMinutosDescargue.setSelected(true);
        selectMvtoEquipo_ValorHoraEquipo.setSelected(true);
        selectMvtoEquipo_costoTotal.setSelected(true);
        selectMvtoEquipo_Recobro.setSelected(true);
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setSelected(true);
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setSelected(true);
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setSelected(true);
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setSelected(true);
        
        
        selectDescargue_usuarioQuienCierraVehiculoCodigo.setSelected(true);
        selectDescargue_usuarioQuienCierraVehiculoNombre.setSelected(true);
        selectMvtoEquipo_UsuarioCierraMvto_codigo.setSelected(true);
        selectMvtoEquipo_UsuarioCierraMvto_nombre.setSelected(true);
        
        
        selectMvtoEquipo_autorizacionRecobro.setSelected(true);
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setSelected(true);
        selectMvtoEquipo_Inactividad.setSelected(true);
        selectMvtoEquipo_CausalInactividad.setSelected(true);
        selectMvtoEquipo_UsuarioInactividad_codigo.setSelected(true);
        selectMvtoEquipo_UsuarioInactividad_nombre.setSelected(true);
        selectMvtoEquipo_MotivoParada.setSelected(true);
        selectMvtoEquipo_observacion.setSelected(true);
        selectMvtoEquipo_estado.setSelected(true);
        selectMvtoEquipo_DesdeCarbon.setSelected(true);
        
    }
    public void selectorCampoNinguno(){
        selectDescargue_codigo.setSelected(false);
        selectDescargue_centroOperacion.setSelected(false);
        selectDescargue_subcentroCosto.setSelected(false);
        selectDescargue_centroCostoAuxiliarOrigen.setSelected(false);
        selectDescargue_centroCostoAuxiliarDestino.setSelected(false);
        selectDescargue_centroCosto.setSelected(false);
        selectDescargue_centroCostoMayor.setSelected(false);
        selectDescargue_laborRealizada.setSelected(false);
        selectDescargue_articuloCodigo.setSelected(false);
        selectDescargue_articuloNombre.setSelected(false);
        selectDescargue_articuloTipo.setSelected(false);
        selectDescargue_articuloCodigoERP.setSelected(false);
        selectDescargue_articuloUnidadNegocio.setSelected(false);
        selectDescargue_clienteCodigo.setSelected(false);
        selectDescargue_clienteNombre.setSelected(false);
        selectDescargue_transportadoraCodigo.setSelected(false);
        selectDescargue_transportadoraNit.setSelected(false);
        selectDescargue_transportadoraNombre.setSelected(false);
        selectDescargue_mes.setSelected(false);
        selectDescargue_dia.setSelected(false);
        selectDescargue_fechaRegistro.setSelected(false);
        selectDescargue_lavadoVehiculo.setSelected(false);
        selectDescargue_lavadoVehiculoObservacion.setSelected(false);
        Motivo_No_LavadoVeh??culo.setSelected(false);
        Equipo_Quien_Realiza_LavadoVeh??culo_C??digo.setSelected(false);
        Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo.setSelected(false);
        Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n.setSelected(false);
        Costo_Lavado_Veh??culo.setSelected(false);
        Valor_Recaudo_Empresa_LavadoVeh??culos.setSelected(false);
        Valor_Recaudo_Equipo_LavadoVeh??culos.setSelected(false);
        selectDescargue_numOrden.setSelected(false);
        selectDescargue_deposito.setSelected(false);
        selectDescargue_consecutivo.setSelected(false);
        selectDescargue_placa.setSelected(false);
        selectDescargue_pesoVacio.setSelected(false);
        selectDescargue_pesoLleno.setSelected(false);
        selectDescargue_pesoNeto.setSelected(false);
        selectDescargue_fechaEntradaVehiculo.setSelected(false);
        selectDescargue_fechaSalidaVehiculo.setSelected(false);
        selectDescargue_fechaInicioDescargue.setSelected(false);
        selectDescargue_fechaFinDescargue.setSelected(false);
        selectDescargue_cantidadMinutosDescargue.setSelected(false);
        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setSelected(false);
        selectDescargue_usuarioQuienRegistraVehiculoNombre.setSelected(false);
        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setSelected(false);
        selectDescargue_observaci??n.setSelected(false);
        selectDescargue_estado.setSelected(false);
        selectDescargue_conexionPesoCcarga.setSelected(false);
        selectDescargue_registroManual.setSelected(false);
        selectDescargue_usuarioRegistroManualCodigo.setSelected(false);
        selectDescargue_usuarioRegistroManualNombre.setSelected(false);
        selectDescargue_usuarioRegistroManualCorreo.setSelected(false);
        selectAsignacion_equipoCodigo.setSelected(false);
        selectAsignacion_equipoTipo.setSelected(false);
        selectAsignacion_equipoMarca.setSelected(false);
        selectAsignacion_equipoModelo.setSelected(false);
        selectAsignacion_equipoDescripcion.setSelected(false);
        selectAsignacion_equipoPertenencia.setSelected(false);
        selectAsignacion_codigo.setSelected(false);
        selectAsignacion_fechaRegistro.setSelected(false);
        selectAsignacion_fechaInicio.setSelected(false);
        selectAsignacion_fechaFinalizacion.setSelected(false);
        selectAsignacion_cantidadMinutos_Programados.setSelected(false);
        selectMvtoEquipo_codigo.setSelected(false);
        selectMvtoEquipo_mes.setSelected(false);
        selectMvtoEquipo_fechaRegistro.setSelected(false);
        selectMvtoEquipo_proveedorEquipoNit.setSelected(false);
        selectMvtoEquipo_proveedorEquipoNombre.setSelected(false);
        selectMvtoEquipo_numeroOrden.setSelected(false);
        selectMvtoEquipo_centroOperacion.setSelected(false);
        selectMvtoEquipo_subcentroCosto.setSelected(false);
        selectMvtoEquipo_centroCostoAxiliarOrigen.setSelected(false);
        selectMvtoEquipo_centroCostoAuxiliarDestino.setSelected(false);
        selectMvtoEquipo_centroCosto.setSelected(false);
        selectMvtoEquipo_centroCostoMayor.setSelected(false);
        selectMvtoEquipo_laborRealizada.setSelected(false);
        selectMvtoEquipo_clienteCodigo.setSelected(false);
        selectMvtoEquipo_clienteNombre.setSelected(false);
        selectMvtoEquipo_articuloCodigo.setSelected(false);
        selectMvtoEquipo_articuloDescripcion.setSelected(false);
        selectMvtoEquipo_motonaveCodigo.setSelected(false);
        selectMvtoEquipo_motonaveDescripci??n.setSelected(false);
        selectMvtoEquipo_fechaInicioDescargue.setSelected(false);
        selectMvtoEquipo_fechaFinDescargue.setSelected(false);
        selectMvtoEquipo_cantidadMinutosDescargue.setSelected(false);
        selectMvtoEquipo_ValorHoraEquipo.setSelected(false);
        selectMvtoEquipo_costoTotal.setSelected(false);
        selectMvtoEquipo_Recobro.setSelected(false);
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setSelected(false);
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setSelected(false);
        
        selectDescargue_usuarioQuienCierraVehiculoCodigo.setSelected(false);
        selectDescargue_usuarioQuienCierraVehiculoNombre.setSelected(false);
        selectMvtoEquipo_UsuarioCierraMvto_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioCierraMvto_nombre.setSelected(false);
        
        selectMvtoEquipo_autorizacionRecobro.setSelected(false);
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setSelected(false);
        selectMvtoEquipo_Inactividad.setSelected(false);
        selectMvtoEquipo_CausalInactividad.setSelected(false);
        selectMvtoEquipo_UsuarioInactividad_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioInactividad_nombre.setSelected(false);
        selectMvtoEquipo_MotivoParada.setSelected(false);
        selectMvtoEquipo_observacion.setSelected(false);
        selectMvtoEquipo_estado.setSelected(false);
        selectMvtoEquipo_DesdeCarbon.setSelected(false);
    } 
    public final void events(){
       pageJComboBox.addActionListener(this);
       tabla.getModel().addTableModelListener(this);
    }
    private ModeloDeTabla crearModeloDeTabla() {
        return new ModeloDeTabla<MvtoCarbon_ListadoEquipos>() {            
            @Override
            public Object getValueAt(MvtoCarbon_ListadoEquipos listado1, int columnas) {
               switch(encabezadoTabla.get(columnas)){
                    case "C??digo":{
                       return listado1.getMvtoCarbon().getCodigo();
                    }
                    case "Centro_Operacion":{
                       return listado1.getMvtoCarbon().getCentroOperacion().getDescripcion();
                    }
                    case "Subcentro_Costo":{
                       return listado1.getMvtoCarbon().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                    }
                    case "Centro_Costo_Auxiliar_Origen":{
                       return listado1.getMvtoCarbon().getCentroCostoAuxiliar().getDescripcion();
                    }
                    case "Centro_Costo_Auxiliar_Destino":{
                       return listado1.getMvtoCarbon().getCentroCostoAuxiliarDestino().getDescripcion();
                    }
                    case "Centro_Costo":{
                       return listado1.getMvtoCarbon().getCentroCosto().getDescripci??n();
                    }
                    case "Centro_Costo_Mayor":{
                       return listado1.getMvtoCarbon().getCentroCostoMayor().getDescripcion();
                    }
                    case "Labor_Realizada":{
                       return listado1.getMvtoCarbon().getLaborRealizada().getDescripcion();
                    }
                    case "Art??culo_C??digo":{
                       return listado1.getMvtoCarbon().getArticulo().getCodigo();
                    }
                    case "Art??culo_Nombre":{
                       return listado1.getMvtoCarbon().getArticulo().getDescripcion();
                    }
                    case "Art??culo_Tipo":{
                       return listado1.getMvtoCarbon().getArticulo().getTipoArticulo().getDescripcion();
                    }
                    case "Art??culo_C??digo_ERP":{
                       return listado1.getMvtoCarbon().getArticulo().getTipoArticulo().getCodigoERP();
                    }
                    case "Art??culo_Unidad_Negocio":{
                       return listado1.getMvtoCarbon().getArticulo().getTipoArticulo().getUnidadNegocio();
                    }
                    case "Cliente_C??digo":{
                       return listado1.getMvtoCarbon().getCliente().getCodigo();
                    }
                    case "Cliente_Nombre":{
                       return listado1.getMvtoCarbon().getCliente().getDescripcion();
                    }
                    case "Transportadora_C??digo":{
                       return listado1.getMvtoCarbon().getTransportadora().getCodigo();
                    }
                    case "Transportadora_Nit":{
                       return listado1.getMvtoCarbon().getTransportadora().getNit();
                    }
                    case "Transportadora_Nombre":{
                       return listado1.getMvtoCarbon().getTransportadora().getDescripcion();
                    }
                    case "Mes":{
                       return getMes(listado1.getMvtoCarbon().getMes());
                    }
                    case "Fecha_Registro":{
                       return listado1.getMvtoCarbon().getFechaRegistro();
                    }
                    case "Lavado_Veh??culo":{
                       return listado1.getMvtoCarbon().getLavadoVehiculo();
                    }
                    case "Lavado_Veh??culo_Observaci??n":{
                       return listado1.getMvtoCarbon().getLavadoVehiculo_Observacion();
                    }
                    case "N??mero_Orden":{
                       return listado1.getMvtoCarbon().getNumero_orden();
                    }
                    case "Deposito":{
                       return listado1.getMvtoCarbon().getDeposito();
                    }
                    case "Consecutivo":{
                       return listado1.getMvtoCarbon().getConsecutivo();
                    }
                    case "Placa":{
                       return listado1.getMvtoCarbon().getPlaca();
                    }
                    case "PesoVacio":{
                       return listado1.getMvtoCarbon().getPesoVacio();
                    }
                    case "Peso_Lleno":{
                       return listado1.getMvtoCarbon().getPesoLleno();
                    }
                    case "Peso_Neto":{
                       return listado1.getMvtoCarbon().getPesoNeto();
                    }
                    case "Fecha_Entrada_Veh??culo":{
                       return listado1.getMvtoCarbon().getFechaEntradaVehiculo();
                    }
                    case "Fecha_Salida_Veh??culo":{
                       return listado1.getMvtoCarbon().getFecha_SalidaVehiculo();
                    }
                    case "Fecha_Inicio_Descargue":{
                       return listado1.getMvtoCarbon().getFechaInicioDescargue();
                    }
                    case "Fecha_Fin_Descargue":{
                       return listado1.getMvtoCarbon().getFechaFinDescargue();
                    }
                    case "Cantidad_Minutos":{
                       return listado1.getMvtoCarbon().getCantidadHorasDescargue();
                    }
                    case "Usuario_Quien_Registra_Veh??culo_C??digo":{
                       return listado1.getMvtoCarbon().getUsuarioRegistroMovil().getCodigo();
                    }
                    case "Usuario_Quien_Registra_Veh??culo_Nombre":{
                       return listado1.getMvtoCarbon().getUsuarioRegistroMovil().getNombres();
                    }
                    case "Usuario_Quien_Registra_Veh??culo_Correo":{
                       return listado1.getMvtoCarbon().getUsuarioRegistroMovil().getCorreo();
                    }
                    case "Observaci??n":{
                       return listado1.getMvtoCarbon().getObservacion();
                    }
                    case "Estado":{
                       return listado1.getMvtoCarbon().getEstadoMvtoCarbon().getDescripcion();
                    }
                    case "Conexi??n_Peso_CCARGA":{
                       return listado1.getMvtoCarbon().getConexionPesoCcarga();
                    }
                    case "Registro_Manual":{
                       return listado1.getMvtoCarbon().getRegistroManual();
                    }
                    case "Usuario_Quien_RegistraManual_C??digo":{
                       return listado1.getMvtoCarbon().getUsuarioRegistraManual().getCodigo();
                    }
                    case "Usuario_Quien_RegistraManual_Nombre":{
                       return listado1.getMvtoCarbon().getUsuarioRegistraManual().getNombres();
                    }
                    case "Usuario_Quien_RegistraManual_Correo":{
                       return listado1.getMvtoCarbon().getUsuarioRegistraManual().getCorreo();
                    }
                    case "Usuario_Quien_Cierra_Veh??culo_C??digo":{
                       return listado1.getMvtoCarbon().getUsuarioCierraRegistro().getCodigo();
                    }
                    case "Usuario_Quien_Cierra_Veh??culo_Nombre":{
                       return listado1.getMvtoCarbon().getUsuarioCierraRegistro().getNombres();
                    }
                    
                    case "MvtoEquipo_C??digo":{
                       return listado1.getMvtoEquipo().getCodigo();
                    }
                    case "MvtoEquipo_MES":{
                       return getMes(listado1.getMvtoEquipo().getMes());
                    }
                    case "MvtoEquipo_Fecha_Registro":{
                       return listado1.getMvtoEquipo().getFechaRegistro();
                    }
                    case "MvtoEquipo_ProveedorEquipo_NIT":{
                       return listado1.getMvtoEquipo().getProveedorEquipo().getNit();
                    }
                    case "MvtoEquipo_ProveedorEquipo_Nombre":{
                       return listado1.getMvtoEquipo().getProveedorEquipo().getDescripcion();
                    }
                    case "MvtoEquipo_N??mero_Orden":{
                       return listado1.getMvtoEquipo().getNumeroOrden();
                    }
                    case "MvtoEquipo_Centro_Operaci??n":{
                       return listado1.getMvtoEquipo().getCentroOperacion().getDescripcion();
                    }
                    case "MvtoEquipo_Subcentro_Costo":{
                       return listado1.getMvtoEquipo().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                    }
                    case "MvtoEquipo_Centro_Costo_Auxiliar_Origen":{
                       return listado1.getMvtoEquipo().getCentroCostoAuxiliar().getDescripcion();
                    }
                    case "MvtoEquipo_Centro_Costo_Auxiliar_Destino":{
                       return listado1.getMvtoEquipo().getCentroCostoAuxiliarDestino().getDescripcion();
                    }
                    case "MvtoEquipo_Centro_Costo":{
                       return listado1.getMvtoEquipo().getCentroCosto().getDescripci??n();
                    }
                    case "MvtoEquipo_Centro_Costo_Mayor":{
                       return listado1.getMvtoEquipo().getCentroCostoMayor().getDescripcion();
                    }
                    case "MvtoEquipo_Labor_Realizada":{
                       return listado1.getMvtoEquipo().getLaborRealizada().getDescripcion();
                    }
                    case "MvtoEquipo_Cliente_C??digo":{
                       return listado1.getMvtoEquipo().getCliente().getCodigo();
                    }
                    case "MvtoEquipo_Cliente_Nombre":{
                       return listado1.getMvtoEquipo().getCliente().getDescripcion();
                    }
                    case "MvtoEquipo_Art??culo_C??digo":{
                       return listado1.getMvtoEquipo().getArticulo().getCodigo();
                    }
                    case "MvtoEquipo_Art??culo_Nombre":{
                       return listado1.getMvtoEquipo().getArticulo().getDescripcion();
                    }
                    case "MvtoEquipo_Motonave_C??digo":{
                       return listado1.getMvtoEquipo().getMotonave().getCodigo();
                    }
                    case "MvtoEquipo_Motonave_Descripci??n":{
                       return listado1.getMvtoEquipo().getMotonave().getDescripcion();
                    }
                    case "MvtoEquipo_FechaInicio_Actividad":{
                       return listado1.getMvtoEquipo().getFechaHoraInicio();
                    }
                    case "MvtoEquipo_FechaFinalizaci??n_Actividad":{
                       return listado1.getMvtoEquipo().getFechaHoraFin();
                    }
                    case "MvtoEquipo_Duraci??n_Actividad(Minutos)":{
                       return listado1.getMvtoEquipo().getTotalMinutos();
                    }
                    case "MvtoEquipo_Valor_Hora":{
                       return listado1.getMvtoEquipo().getValorHora();
                    }
                    case "MvtoEquipo_Costo_Total":{
                       return listado1.getMvtoEquipo().getCostoTotal();
                    }
                    case "MvtoEquipo_Recobro":{
                       return listado1.getMvtoEquipo().getRecobro().getDescripcion();
                    }
                    case "MvtoEquipo_UsuarioQuienRegistra_C??digo":{
                       return listado1.getMvtoEquipo().getUsuarioQuieRegistra().getCodigo();
                    }
                    case "MvtoEquipo_UsuarioQuienRegistra_Nombre":{
                       return listado1.getMvtoEquipo().getUsuarioQuieRegistra().getNombres();
                    }
                    case "MvtoEquipo_UsuarioQuienAutoriza_C??digo":{
                       return listado1.getMvtoEquipo().getUsuarioAutorizaRecobro().getCodigo();
                    }
                    case "MvtoEquipo_UsuarioQuienAutoriza_Nombre":{
                       return listado1.getMvtoEquipo().getUsuarioAutorizaRecobro().getNombres();
                    }
                    
                    case "MvtoEquipo_UsuarioCierraMvto_codigo":{
                       return listado1.getMvtoEquipo().getUsuarioQuienCierra().getCodigo();
                    }
                    case "MvtoEquipo_UsuarioCierraMvto_nombre":{
                       return listado1.getMvtoEquipo().getUsuarioQuienCierra().getNombres();
                    }
                    
                    case "MvtoEquipo_Autorizaci??n_Recobro":{
                       return listado1.getMvtoEquipo().getAutorizacionRecobro().getDescripcion();
                    }
                    case "MvtoEquipo_Observaci??n_Autorizaci??n_Recobro":{
                       return listado1.getMvtoEquipo().getObservacionAutorizacion();
                    }
                    case "MvtoEquipo_Inactividad":{
                       return listado1.getMvtoEquipo().getInactividad();
                    }
                    case "MvtoEquipo_Causal_Inactividad":{
                       return listado1.getMvtoEquipo().getCausaInactividad().getDescripcion();
                    }
                    case "MvtoEquipo_UsuarioQuienInactiva_C??digo":{
                       return listado1.getMvtoEquipo().getUsuarioInactividad().getCodigo();
                    }
                    case "MvtoEquipo_UsuarioQuienInactiva_Nombre":{
                       return listado1.getMvtoEquipo().getUsuarioInactividad().getNombres();
                    }
                    case "MvtoEquipo_Motivo_Parada":{
                       return listado1.getMvtoEquipo().getMotivoParada().getDescripcion();
                    }
                    case "MvtoEquipo_MvtoEquipo_Observaci??n":{
                       return listado1.getMvtoEquipo().getObservacionMvtoEquipo();
                    }
                    case "MvtoEquipo_Estado":{
                       return listado1.getMvtoEquipo().getEstado();
                    }
                    case "MvtoEquipo_Desde_Carb??n":{
                       return listado1.getMvtoEquipo().getDesdeCarbon();
                    }
                    case "MvtoEquipo_Equipo_C??digo":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getEquipo().getCodigo();
                    }
                    case "MvtoEquipo_Equipo_Tipo":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion();
                    }
                    case "MvtoEquipo_Equipo_Marca":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getEquipo().getMarca();
                    }
                    case "MvtoEquipo_Equipo_Modelo":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getEquipo().getModelo();
                    }
                    case "MvtoEquipo_Equipo_Descripci??n":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getEquipo().getDescripcion();
                    }
                    case "MvtoEquipo_Equipo_Pertenencia":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getPertenencia().getDescripcion();
                    }
                    case "MvtoEquipo_Asignaci??n_C??digo":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getCodigo();
                    }
                    case "MvtoEquipo_Asignaci??n_FechaRegistro":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getFechaRegistro();
                    }
                    case "MvtoEquipo_Asignaci??n_FechaInicioActividad":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getFechaHoraInicio();
                    }
                    case "MvtoEquipo_Asignaci??n_FechaFinalizaci??nActividad":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getFechaHoraFin();
                    }
                    case "MvtoEquipo_Asignaci??n_CantidadMinutosProgramados":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getCantidadMinutosProgramados();
                    }      
               }
               return null;
            }

            @Override
            public String getColumnName(int columnas) {
                return encabezadoTabla.get(columnas);
            }

            @Override
            public int getColumnCount() {
                return encabezadoTabla.size();
            }

        }; 
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object evt = e.getSource();
        paginadorDeTabla.eventCombobBox(pageJComboBox);
    }
    @Override
    public void tableChanged(TableModelEvent e) {
        paginadorDeTabla.refreshPageButtonPanel();
    }
    private ProveedorDeDatosDePaginacion<MvtoCarbon_ListadoEquipos> crearProveedorDeDatos(final ArrayList<MvtoCarbon_ListadoEquipos> model) {
        //Obtenemos el listado de registros existentes haciendo una consulta a la base de datos.
        
        //Retornamos un interfaz de tipo ProveedorDeDatosDePaginacion en la cual sobreescribimos sus metodos abtractos
        //1 metodo: obtenemos el numero total de registros agregados al JTable.
        //2 metodo: obtenemos una subLista la cual ser?? mostrada en el JTable, seria nuestra pagina actual.
        return new ProveedorDeDatosDePaginacion<MvtoCarbon_ListadoEquipos>() {
            @Override
            public int getTotalRowCount() {
                return model.size();
            }

            @Override
            public List<MvtoCarbon_ListadoEquipos> getRows(int startIndex, int endIndex) {
                return model.subList(startIndex, endIndex);
            }
        };
    }
    public void validarSeleccionCampos(){
        encabezadoTabla= new ArrayList<>();
        if(selectDescargue_codigo.isSelected()){
            encabezadoTabla.add("C??digo");
        }
        if(selectDescargue_mes.isSelected()){
            encabezadoTabla.add("Mes");
        }
         if(selectDescargue_dia.isSelected()){
            encabezadoTabla.add("D??a");
        }
        if(selectDescargue_fechaRegistro.isSelected()){
            encabezadoTabla.add("Fecha_Registro");
        }
         if(selectDescargue_clienteCodigo.isSelected()){
            encabezadoTabla.add("Cliente_C??digo");
        }
        if(selectDescargue_clienteNombre.isSelected()){
            encabezadoTabla.add("Cliente_Nombre");
        }
        if(selectDescargue_centroOperacion.isSelected()){
            encabezadoTabla.add("Centro_Operacion");
        }
        if(selectDescargue_subcentroCosto.isSelected()){
            encabezadoTabla.add("Subcentro_Costo");
        }
        if(selectDescargue_centroCostoAuxiliarOrigen.isSelected()){
            encabezadoTabla.add("Centro_Costo_Auxiliar_Origen");
        }
        if(selectDescargue_centroCostoAuxiliarDestino.isSelected()){
            encabezadoTabla.add("Centro_Costo_Auxiliar_Destino");
        }
        if(selectDescargue_centroCosto.isSelected()){
            encabezadoTabla.add("Centro_Costo");
        }
        if(selectDescargue_centroCostoMayor.isSelected()){
            encabezadoTabla.add("Centro_Costo_Mayor");
        }
        if(selectDescargue_laborRealizada.isSelected()){
            encabezadoTabla.add("Labor_Realizada");
        }
        if(selectDescargue_deposito.isSelected()){
            encabezadoTabla.add("Deposito");
        }
        if(selectDescargue_placa.isSelected()){
            encabezadoTabla.add("Placa");
        }
        if(selectAsignacion_equipoDescripcion.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Equipo_Descripci??n");
        }
        if(selectDescargue_lavadoVehiculo.isSelected()){
            encabezadoTabla.add("Lavado_Veh??culo");
        }
        if(Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n.isSelected()){
            encabezadoTabla.add("Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n");
        }
        
        if(selectDescargue_articuloCodigo.isSelected()){
            encabezadoTabla.add("Art??culo_C??digo");
        }
        if(selectDescargue_articuloNombre.isSelected()){
            encabezadoTabla.add("Art??culo_Nombre");
        }
        if(selectDescargue_articuloTipo.isSelected()){
            encabezadoTabla.add("Art??culo_Tipo");
        }
        if(selectDescargue_articuloCodigoERP.isSelected()){
            encabezadoTabla.add("Art??culo_C??digo_ERP");
        }
        if(selectDescargue_articuloUnidadNegocio.isSelected()){
            encabezadoTabla.add("Art??culo_Unidad_Negocio");
        }     
        if(selectDescargue_transportadoraCodigo.isSelected()){
            encabezadoTabla.add("Transportadora_C??digo");
        }
        if(selectDescargue_transportadoraNit.isSelected()){
            encabezadoTabla.add("Transportadora_Nit");
        }
        if(selectDescargue_transportadoraNombre.isSelected()){
            encabezadoTabla.add("Transportadora_Nombre");
        }
        
        if(selectDescargue_lavadoVehiculoObservacion.isSelected()){
            encabezadoTabla.add("Lavado_Veh??culo_Observaci??n");
        }     
        if(Motivo_No_LavadoVeh??culo.isSelected()){
            encabezadoTabla.add("Motivo_No_LavadoVeh??culo");
        }
        if(Equipo_Quien_Realiza_LavadoVeh??culo_C??digo.isSelected()){
            encabezadoTabla.add("Equipo_Quien_Realiza_LavadoVeh??culo_C??digo");
        }
        if(Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo.isSelected()){
            encabezadoTabla.add("Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo");
        }
        
        if(Costo_Lavado_Veh??culo.isSelected()){
            encabezadoTabla.add("Costo_Lavado_Veh??culo");
        }
        if(Valor_Recaudo_Empresa_LavadoVeh??culos.isSelected()){
            encabezadoTabla.add("Valor_Recaudo_Empresa_LavadoVeh??culos");
        }
        if(Valor_Recaudo_Equipo_LavadoVeh??culos.isSelected()){
            encabezadoTabla.add("Valor_Recaudo_Equipo_LavadoVeh??culos");
        }
        if(selectDescargue_numOrden.isSelected()){
            encabezadoTabla.add("N??mero_Orden");
        }
        
        if(selectDescargue_consecutivo.isSelected()){
            encabezadoTabla.add("Consecutivo");
        }
        
        if(selectAsignacion_equipoCodigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Equipo_C??digo");
        }
        if(selectAsignacion_equipoTipo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Equipo_Tipo");
        }
        if(selectAsignacion_equipoMarca.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Equipo_Marca");
        }
        if(selectAsignacion_equipoModelo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Equipo_Modelo");
        }
        
        if(selectDescargue_pesoVacio.isSelected()){
            encabezadoTabla.add("PesoVacio");
        }
        if(selectDescargue_pesoLleno.isSelected()){
            encabezadoTabla.add("Peso_Lleno");
        }
        if(selectDescargue_pesoNeto.isSelected()){
            encabezadoTabla.add("Peso_Neto");
        }
        if(selectDescargue_fechaEntradaVehiculo.isSelected()){
            encabezadoTabla.add("Fecha_Entrada_Veh??culo");
        }
        if(selectDescargue_fechaSalidaVehiculo.isSelected()){
            encabezadoTabla.add("Fecha_Salida_Veh??culo");
        }
        if(selectDescargue_fechaInicioDescargue.isSelected()){
            encabezadoTabla.add("Fecha_Inicio_Descargue");
        }
        if(selectDescargue_fechaFinDescargue.isSelected()){
            encabezadoTabla.add("Fecha_Fin_Descargue");
        }
        if(selectDescargue_cantidadMinutosDescargue.isSelected()){
            encabezadoTabla.add("Cantidad_Minutos");
        }
        if(selectDescargue_usuarioQuienRegistraVehiculoCodigo.isSelected()){
            encabezadoTabla.add("Usuario_Quien_Registra_Veh??culo_C??digo");
        }
        if(selectDescargue_usuarioQuienRegistraVehiculoNombre.isSelected()){
            encabezadoTabla.add("Usuario_Quien_Registra_Veh??culo_Nombre");
        }
        if(selectDescargue_usuarioQuienRegistraVehiculoCorreo.isSelected()){
            encabezadoTabla.add("Usuario_Quien_Registra_Veh??culo_Correo");
        }
        if(selectDescargue_observaci??n.isSelected()){
            encabezadoTabla.add("Observaci??n");
        }
        if(selectDescargue_estado.isSelected()){
            encabezadoTabla.add("Estado");
        }
        if(selectDescargue_conexionPesoCcarga.isSelected()){
            encabezadoTabla.add("Conexi??n_Peso_CCARGA");
        }
        if(selectDescargue_registroManual.isSelected()){
            encabezadoTabla.add("Registro_Manual");
        }
        if(selectDescargue_usuarioRegistroManualCodigo.isSelected()){
            encabezadoTabla.add("Usuario_Quien_RegistraManual_C??digo");
        }
        if(selectDescargue_usuarioRegistroManualNombre.isSelected()){
            encabezadoTabla.add("Usuario_Quien_RegistraManual_Nombre");
        }
        if(selectDescargue_usuarioRegistroManualCorreo.isSelected()){
            encabezadoTabla.add("Usuario_Quien_RegistraManual_Correo");
        }
        if(selectDescargue_usuarioQuienCierraVehiculoCodigo.isSelected()){
            encabezadoTabla.add("Usuario_Quien_Cierra_Veh??culo_C??digo");
        }
        if(selectDescargue_usuarioQuienCierraVehiculoNombre.isSelected()){
            encabezadoTabla.add("Usuario_Quien_Cierra_Veh??culo_Nombre");
        }
        if(selectMvtoEquipo_codigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_C??digo");
        }
        if(selectMvtoEquipo_mes.isSelected()){
            encabezadoTabla.add("MvtoEquipo_MES");
        }
        if(selectMvtoEquipo_fechaRegistro.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Fecha_Registro");
        }
        if(selectMvtoEquipo_proveedorEquipoNit.isSelected()){
            encabezadoTabla.add("MvtoEquipo_ProveedorEquipo_NIT");
        }
        if(selectMvtoEquipo_proveedorEquipoNombre.isSelected()){
            encabezadoTabla.add("MvtoEquipo_ProveedorEquipo_Nombre");
        }
        if(selectMvtoEquipo_numeroOrden.isSelected()){
            encabezadoTabla.add("MvtoEquipo_N??mero_Orden");
        }
        if(selectMvtoEquipo_centroOperacion.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Centro_Operaci??n");
        }
        if(selectMvtoEquipo_subcentroCosto.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Subcentro_Costo");
        }
        if(selectMvtoEquipo_centroCostoAxiliarOrigen.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Centro_Costo_Auxiliar_Origen");
        }
        if(selectMvtoEquipo_centroCostoAuxiliarDestino.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Centro_Costo_Auxiliar_Destino");
        }
        if(selectMvtoEquipo_centroCosto.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Centro_Costo");
        }
        if(selectMvtoEquipo_centroCostoMayor.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Centro_Costo_Mayor");
        }
        if(selectMvtoEquipo_laborRealizada.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Labor_Realizada");
        }
        if(selectMvtoEquipo_clienteCodigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Cliente_C??digo");
        }
        if(selectMvtoEquipo_clienteNombre.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Cliente_Nombre");
        }
        if(selectMvtoEquipo_articuloCodigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Art??culo_C??digo");
        }
        if(selectMvtoEquipo_articuloDescripcion.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Art??culo_Nombre");
        }
        if(selectMvtoEquipo_motonaveCodigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Motonave_C??digo");
        }
        if(selectMvtoEquipo_motonaveDescripci??n.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Motonave_Descripci??n");
        }
        if(selectMvtoEquipo_fechaInicioDescargue.isSelected()){
            encabezadoTabla.add("MvtoEquipo_FechaInicio_Actividad");
        }
        if(selectMvtoEquipo_fechaFinDescargue.isSelected()){
            encabezadoTabla.add("MvtoEquipo_FechaFinalizaci??n_Actividad");
        }
        if(selectMvtoEquipo_cantidadMinutosDescargue.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Duraci??n_Actividad(Minutos)");
        }
        if(selectMvtoEquipo_ValorHoraEquipo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Valor_Hora");
        }
        if(selectMvtoEquipo_costoTotal.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Costo_Total");
        }
        if(selectMvtoEquipo_Recobro.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Recobro");
        }
        if(selectMvtoEquipo_UsuarioRegistraMvto_codigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioQuienRegistra_C??digo");
        }
        if(selectMvtoEquipo_UsuarioRegistraMvto_nombre.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioQuienRegistra_Nombre");
        }
        if(selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioQuienAutoriza_C??digo");
        }
        if(selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioQuienAutoriza_Nombre");
        }
        if(selectMvtoEquipo_UsuarioCierraMvto_codigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioCierraMvto_codigo");
        }
        if(selectMvtoEquipo_UsuarioCierraMvto_nombre.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioCierraMvto_nombre");
        }
        if(selectMvtoEquipo_autorizacionRecobro.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Autorizaci??n_Recobro");
        }
        if(selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Observaci??n_Autorizaci??n_Recobro");
        }
        if(selectMvtoEquipo_Inactividad.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Inactividad");
        }
        if(selectMvtoEquipo_CausalInactividad.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Causal_Inactividad");
        }
        if(selectMvtoEquipo_UsuarioInactividad_codigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioQuienInactiva_C??digo");
        }
        if(selectMvtoEquipo_UsuarioInactividad_nombre.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioQuienInactiva_Nombre");
        }
        if(selectMvtoEquipo_MotivoParada.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Motivo_Parada");
        }
        if(selectMvtoEquipo_observacion.isSelected()){
            encabezadoTabla.add("MvtoEquipo_MvtoEquipo_Observaci??n");
        }
        if(selectMvtoEquipo_estado.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Estado");
        }
        if(selectMvtoEquipo_DesdeCarbon.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Desde_Carb??n");
        }     
        if(selectAsignacion_equipoPertenencia.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Equipo_Pertenencia");
        }
        if(selectAsignacion_codigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Asignaci??n_C??digo");
        }
        if(selectAsignacion_fechaRegistro.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Asignaci??n_FechaRegistro");
        }
        if(selectAsignacion_fechaInicio.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Asignaci??n_FechaInicioActividad");
        }
        if(selectAsignacion_fechaFinalizacion.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Asignaci??n_FechaFinalizaci??nActividad");
        }
        if(selectAsignacion_cantidadMinutos_Programados.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Asignaci??n_CantidadMinutosProgramados");
        }    
    }
    public String getMes(String mes){
        switch(mes){
            case "January":{
                mes="Enero";
                break;
            }
            case "February":{
                mes="Febrero";
                break;
            }
            case "March":{
                mes="Marzo";
                break;
            }
            case "April":{
                mes="Abril";
                break;
            }
            case "May":{
                mes="Marzo";
                break;
            }
            case "June":{
                mes="Junio";
                break;
            }
            case "July":{
                mes="Julio";
                break;
            }
            case "August":{
                mes="Agosto";
                break;
            }
            case "September":{
                mes="Septiembre";
                break;
            }
            case "October":{
                mes="Octubre";
                break;
            }
            case "November":{
                mes="Noviembre";
                break;
            }
            case "December":{
                mes="Diciembre";
                break;
            }           
        }
        return mes;
    }
    //Ajustar aNcho de las tablas de acuerdo al contenido
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            if(width > 300)
                width=300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
