package ModuloEquipo.View;

import Catalogo.Controller.ControlDB_Articulo;
import Catalogo.Controller.ControlDB_CentroCostoSubCentro;
import Catalogo.Controller.ControlDB_CentroOperacion;
import Catalogo.Controller.ControlDB_Cliente;
import ModuloEquipo.Model.Recobro;
import ModuloEquipo.Model.MvtoEquipo;
import ModuloEquipo.Model.AutorizacionRecobro;
import ModuloCarbon.Controller2.ControlDB_MvtoCarbon;
import ModuloCarbon.Model.MvtoCarbon_ListadoEquipos;
import Catalogo.Controller.ControlDB_Equipo;
import Catalogo.Controller.ControlDB_LaborRealizada;
import Catalogo.Controller.ControlDB_MotivoParada;
import Catalogo.Controller.ControlDB_Motonave;
import Catalogo.Model.Articulo;
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import Catalogo.Model.Cliente;
import Catalogo.Model.LaborRealizada;
import Catalogo.Model.MotivoParada;
import Catalogo.Model.Motonave;
import Catalogo.Model.TipoEquipo;
import ModuloEquipo.Controller2.ControlDB_MvtoEquipo;
import ModuloEquipo.Model.AsignacionEquipo;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.filechooser.FileNameExtensionFilter;
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
 
public final class MvtoEquipo_ModificarFinal extends javax.swing.JPanel implements ActionListener, TableModelListener{
    //ArrayList<MvtoEquipo> listado=null;
    private Usuario user;
    MvtoEquipo mvtoEquipo;
    private ArrayList<AutorizacionRecobro> listadoAutorizacionRecobro= null;
    private ArrayList<Motonave> listadoMotonave= null;
    private ArrayList<Cliente> listadoCliente=null;
    private ArrayList<Articulo> listadoArticulos=null;
    //MvtoCarbon_ListadoEquipos mvto_listEquipo = null;
    private String tipoConexion;
    
    ArrayList<MvtoEquipo> listado=null;
    private Motonave motonave=null;
    private Cliente cliente=null;
    private Articulo articulo=null;
    //private final Usuario user;
    //private final String tipoConexion;
    private PaginadorDeTabla<MvtoEquipo> paginadorDeTabla;  
    ProveedorDeDatosDePaginacion<MvtoEquipo> proveedorDeDatos;
    ArrayList<String> encabezadoTabla=null;
    
    //Variables interfaces Modificar_MvtoEquipo
    private ArrayList<CentroOperacion> listadoCentroOperacion_mvtoEquipo = null;
    private ArrayList<CentroCostoSubCentro> listadoCentroCostoSubCentro_mvtoEquipo = null;
    private ArrayList<LaborRealizada> listadoLaborRealizada_mvtoEquipo = null;
    private ArrayList<CentroCostoAuxiliar> listadoCentroCostoAuxiliar_mvtoEquipo =null;
    private ArrayList<CentroCostoAuxiliar> listadoCentroCostoAuxiliarDestino_mvtoEquipo = null;
    private ArrayList<Recobro> listadoRecobros_mvtoEquipo= null;
    private ArrayList<TipoEquipo> listadoTipoEquipos_mvtoEquipo = null;
    private ArrayList<String > listadoMarcaEquipos_mvtoEquipo = null;
    private ArrayList<AsignacionEquipo> listadoEquipos_mvtoEquipo = null;
    private ArrayList<MotivoParada> listadoMotivoParada_mvtoEquipo = null;
    
    public MvtoEquipo_ModificarFinal(Usuario user, String tipoConexion) {
        initComponents();
        this.tipoConexion= tipoConexion;
        this.user= user;
        mvtoEquipo = null;
        
        //Agregamos color Blanco a los internal Frame
        InternalFrameSelectorCampos.getContentPane().setBackground(Color.WHITE);
        InternaFrame_VisualizarMvtoEquipo.getContentPane().setBackground(Color.WHITE);
        InternaFrame_buscarMotonave.getContentPane().setBackground(Color.WHITE);
        InternaFrame_buscarCliente.getContentPane().setBackground(Color.WHITE);
        InternaFrame_buscarArticulo.getContentPane().setBackground(Color.WHITE);
        
        //Ocultamos los internal frame
        InternalFrameSelectorCampos.show(false);
        InternaFrame_buscarMotonave.show(false);
        InternaFrame_buscarCliente.show(false);
        InternaFrame_VisualizarMvtoEquipo.show(false);
        
        //Cargamos las horas y los minutos en las selección de las interfaces.
        for(int i = 0; i<= 23; i++){
            if(i<=9){
                horaInicio.addItem("0"+i);
                horaFin.addItem("0"+i);
                //select_MvtoCarbon_HoraInicial.addItem("0"+i);
                select_MvtoEquipo_HoraInicial.addItem("0"+i);
                //select_MvtoCarbon_HoraFinal.addItem("0"+i);
                select_MvtoEquipo_HoraFinal.addItem("0"+i);
            }else{
                horaInicio.addItem(""+i);
                horaFin.addItem(""+i);
                //select_MvtoCarbon_HoraInicial.addItem(""+i);
                select_MvtoEquipo_HoraInicial.addItem(""+i);
                //select_MvtoCarbon_HoraFinal.addItem(""+i);
                select_MvtoEquipo_HoraFinal.addItem(""+i);
            }
        }
        for(int i = 0; i<= 59; i++){
            if(i<=9){
                minutoInicio.addItem("0"+i);
                minutoFin.addItem("0"+i);
                //select_MvtoCarbon_MinutosInicial.addItem("0"+i);
                select_MvtoEquipo_MinutosInicial.addItem("0"+i);
                //select_MvtoCarbon_MinutosFinal.addItem("0"+i);
                select_MvtoEquipo_MinutosFinal.addItem("0"+i);
            }else{
                minutoInicio.addItem(""+i);
                minutoFin.addItem(""+i);
                //select_MvtoCarbon_MinutosInicial.addItem(""+i);
                select_MvtoEquipo_MinutosInicial.addItem(""+i);
                //select_MvtoCarbon_MinutosFinal.addItem(""+i);
                select_MvtoEquipo_MinutosFinal.addItem(""+i);
            }
        }
        
        
        selectorCampoPorDefecto();
        encabezadoTabla= new ArrayList<String>();
        pageJComboBox.show(false);
        
        icon_buscarMotonave.show(false);
        icon_buscarClientes.show(false);
        icon_buscarArticulo.show(false);
        
        //jProgressBar1.show(false);
        
        
        //Cargamos en la interfaz los centros de Operaciones
        try {
            listadoCentroOperacion_mvtoEquipo=new ControlDB_CentroOperacion(tipoConexion).buscarActivos();
            //listadoCentroOperacion_mvtoEquipo=listadoCentroOperacion_mvtoCarbon;
            if(listadoCentroOperacion_mvtoEquipo != null){  
                String datosObjeto[]= new String[listadoCentroOperacion_mvtoEquipo.size()];
                int contador=0;
                for(CentroOperacion listadoCentroOperacion1 : listadoCentroOperacion_mvtoEquipo){ 
                    datosObjeto[contador]=listadoCentroOperacion1.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                select_MvtoEquipo_CO.setModel(model);
                //select_MvtoEquipo_CO.setModel(model);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        //Cargamos en la interfaz los Subcentro de costos según el centro de Operación
        try {
            listadoCentroCostoSubCentro_mvtoEquipo=new ControlDB_CentroCostoSubCentro(tipoConexion).buscarActivos(listadoCentroOperacion_mvtoEquipo.get(select_MvtoEquipo_CO.getSelectedIndex()));
        } catch (SQLException ex) {
            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
        //listadoCentroCostoSubCentro_mvtoEquipo=listadoCentroCostoSubCentro_mvtoEquipo;
        if(listadoCentroCostoSubCentro_mvtoEquipo != null){
            String datosObjeto[]= new String[listadoCentroCostoSubCentro_mvtoEquipo.size()];
            int contador=0;
            for(CentroCostoSubCentro Objeto : listadoCentroCostoSubCentro_mvtoEquipo){
                datosObjeto[contador]=Objeto.getDescripcion();
                contador++;
            }
            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
            select_MvtoEquipo_SubcentroCosto.setModel(model);
            //select_MvtoEquipo_SubcentroCosto.setModel(model);
        } 
        
        //Cargamos los la interfaz los auxiliares de costos según selección de SubCentro de costos
        try {
            if(listadoCentroCostoSubCentro_mvtoEquipo!= null){
                listadoCentroCostoAuxiliar_mvtoEquipo=new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro_mvtoEquipo.get(select_MvtoEquipo_SubcentroCosto.getSelectedIndex()).getCodigo());
                //listadoCentroCostoAuxiliar_mvtoEquipo=listadoCentroCostoAuxiliar_mvtoCarbon;
                if(listadoCentroCostoAuxiliar_mvtoEquipo != null){
                    String datosObjeto[]= new String[listadoCentroCostoAuxiliar_mvtoEquipo.size()];
                    int contador=0;
                    for(CentroCostoAuxiliar Objeto : listadoCentroCostoAuxiliar_mvtoEquipo){ 
                        datosObjeto[contador]=Objeto.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    //select_MvtoCarbon_CCAuxiliar.setModel(model);
                    select_MvtoEquipo_CCAuxiliar.setModel(model);
                } 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Cargamos en la interfaz las labores realizada
        try {
            listadoLaborRealizada_mvtoEquipo=new ControlDB_LaborRealizada(tipoConexion).buscarPorSubcentroCosto(listadoCentroCostoSubCentro_mvtoEquipo.get(select_MvtoEquipo_SubcentroCosto.getSelectedIndex()));
            //listadoLaborRealizada_mvtoEquipo=listadoLaborRealizada_mvtoEquipo;
            if(listadoLaborRealizada_mvtoEquipo != null){
                String datosObjeto[]= new String[listadoLaborRealizada_mvtoEquipo.size()];
                int contador=0;
                for(LaborRealizada Objeto : listadoLaborRealizada_mvtoEquipo){ 
                    datosObjeto[contador]=Objeto.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                select_MvtoEquipo_laborRealizada.setModel(model);
                //select_MvtoEquipo_laborRealizada.setModel(model);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Cargamos en la interfaz los centro auxiliares Destino según labor realizada
        try{
            if(listadoLaborRealizada_mvtoEquipo != null){
                if(listadoLaborRealizada_mvtoEquipo.get(select_MvtoEquipo_laborRealizada.getSelectedIndex()).getBodegaDestino().equals("1")){//Fue seleccionada una labor Realizada que tiene marcada Bodega destino por tal motivo cargalos las Bodega destino
                   // listadoCentroCostoAuxiliarDestino_mvtoEquipo
                    if(listadoCentroCostoSubCentro_mvtoEquipo != null){
                        try {
                            listadoCentroCostoAuxiliarDestino_mvtoEquipo = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro_mvtoEquipo.get(select_MvtoEquipo_SubcentroCosto.getSelectedIndex()).getCodigo());
                            if(listadoCentroCostoAuxiliarDestino_mvtoEquipo != null){
                                String datosObjeto[] = new String[listadoCentroCostoAuxiliarDestino_mvtoEquipo.size()];
                                int contador = 0;
                                for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliarDestino_mvtoEquipo) {
                                    datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                                    contador++;
                                }
                                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                select_MvtoEquipo_CCAuxiliarDestino.setModel(model);
                            }else{
                                select_MvtoEquipo_CCAuxiliarDestino.removeAllItems();
                            } 
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }            
                }else{
                   select_MvtoEquipo_CCAuxiliarDestino.removeAllItems(); 
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
                mvtoEquipo_selectRazonFinalzación.setModel(model);
                
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
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
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Visualizar = new javax.swing.JMenuItem();
        InternaFrame_buscarMotonave = new javax.swing.JInternalFrame();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaMotonaves = new javax.swing.JTable();
        valorBusqueda = new javax.swing.JTextField();
        btnConsultar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        InternaFrame_buscarArticulo = new javax.swing.JInternalFrame();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaArticulo = new javax.swing.JTable();
        valorBusquedaArticulo = new javax.swing.JTextField();
        btnConsultarArticulo = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnCancelarArticulo = new javax.swing.JButton();
        btnLimpiarArticulo = new javax.swing.JButton();
        InternaFrame_buscarCliente = new javax.swing.JInternalFrame();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaCliente = new javax.swing.JTable();
        valorBusquedaCliente = new javax.swing.JTextField();
        btnConsultarCliente = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnCancelarCliente = new javax.swing.JButton();
        btnLimpiarCliente = new javax.swing.JButton();
        InternaFrame_VisualizarMvtoEquipo = new javax.swing.JInternalFrame();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        mvtoEquipo_estado = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        mvtoEquipo_Observación = new javax.swing.JTextArea();
        jLabel36 = new javax.swing.JLabel();
        mvtoEquipo_UsuarioRegistro_Correo = new javax.swing.JLabel();
        mvtoEquipo_fechaMvto = new javax.swing.JLabel();
        mvtoEquipo_CentroOperacion = new javax.swing.JLabel();
        mvtoEquipo_Subcentro = new javax.swing.JLabel();
        mvtoEquipo_AuxilarCentroOperacionOrigen = new javax.swing.JLabel();
        mvtoEquipo_laborRealizada = new javax.swing.JLabel();
        mvtoEquipo_FechaInicioActividad = new javax.swing.JLabel();
        mvtoEquipo_FechaFinalActividad = new javax.swing.JLabel();
        mvtoEquipo_Minutos = new javax.swing.JLabel();
        mvtoEquipo_CantidadHoras = new javax.swing.JLabel();
        mvtoEquipo_ActividadFinalizada = new javax.swing.JLabel();
        mvtoEquipo_MotivoFinalización = new javax.swing.JLabel();
        mvtoEquipo_codigo = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        mvtoEquipo_ObservaciónEditar = new javax.swing.JTextArea();
        mvtoEquipo_Cliente_Código = new javax.swing.JLabel();
        mvtoEquipo_Cliente_Nombre = new javax.swing.JLabel();
        mvtoEquipo_UsuarioRegistro_Código = new javax.swing.JLabel();
        mvtoEquipo_UsuarioRegistro_Nombre = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        mvtoEquipo_Equipo_pertenencia = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        mvtoEquipo_Equipo_codigo = new javax.swing.JLabel();
        mvtoEquipo_Equipo_tipo = new javax.swing.JLabel();
        mvtoEquipo_Equipo_marca = new javax.swing.JLabel();
        mvtoEquipo_Equipo_descripción = new javax.swing.JLabel();
        mvtoEquipo_Equipo_proveedor = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        check_MvtoEquipo_InicioActividad = new javax.swing.JRadioButton();
        fechaInicialActividad_MvtoEquipo = new com.toedter.calendar.JDateChooser();
        label_MvtoEquipo_HoraInicial = new javax.swing.JLabel();
        select_MvtoEquipo_HoraInicial = new javax.swing.JComboBox<>();
        label_MvtoEquipo_separadorInicial = new javax.swing.JLabel();
        select_MvtoEquipo_MinutosInicial = new javax.swing.JComboBox<>();
        label_MvtoEquipo_ZonaHorariaInicial = new javax.swing.JLabel();
        label_MvtoEquipo_ZonaHorariaFinal = new javax.swing.JLabel();
        select_MvtoEquipo_MinutosFinal = new javax.swing.JComboBox<>();
        select_MvtoEquipo_HoraFinal = new javax.swing.JComboBox<>();
        label_MvtoEquipo_separadorFinal = new javax.swing.JLabel();
        label_MvtoEquipo_HoraFinal = new javax.swing.JLabel();
        fechaFinActividad_MvtoEquipo = new com.toedter.calendar.JDateChooser();
        check_MvtoEquipo_FinalizacionActividad = new javax.swing.JRadioButton();
        titulo29 = new javax.swing.JLabel();
        select_MvtoEquipo_CO = new javax.swing.JComboBox<>();
        titulo25 = new javax.swing.JLabel();
        select_MvtoEquipo_SubcentroCosto = new javax.swing.JComboBox<>();
        titulo45 = new javax.swing.JLabel();
        select_MvtoEquipo_CCAuxiliar = new javax.swing.JComboBox<>();
        select_MvtoEquipo_laborRealizada = new javax.swing.JComboBox<>();
        titulo46 = new javax.swing.JLabel();
        select_MvtoEquipo_recobro = new javax.swing.JComboBox<>();
        titulo47 = new javax.swing.JLabel();
        titulo58 = new javax.swing.JLabel();
        select_MvtoEquipo_tipoEquipo = new javax.swing.JComboBox<>();
        titulo61 = new javax.swing.JLabel();
        select_MvtoEquipo_marcaEquipo = new javax.swing.JComboBox<>();
        titulo62 = new javax.swing.JLabel();
        select_MvtoEquipo_Equipo = new javax.swing.JComboBox<>();
        titulo48 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        textArea_MvtoEquipo_razonModificacion = new javax.swing.JTextArea();
        jSeparator36 = new javax.swing.JSeparator();
        registrar_MvtoEquipo = new javax.swing.JButton();
        titulo27 = new javax.swing.JLabel();
        checkMotonave = new javax.swing.JRadioButton();
        icon_buscarMotonave = new javax.swing.JLabel();
        label_motonave = new javax.swing.JLabel();
        checkCliente = new javax.swing.JRadioButton();
        icon_buscarClientes = new javax.swing.JLabel();
        label_clientes = new javax.swing.JLabel();
        checkArticulo = new javax.swing.JRadioButton();
        icon_buscarArticulo = new javax.swing.JLabel();
        label_articulo = new javax.swing.JLabel();
        mvtoEquipo_numOrden = new javax.swing.JTextField();
        mvtoEquipo_selectRazonFinalzación = new javax.swing.JComboBox<>();
        titulo28 = new javax.swing.JLabel();
        titulo30 = new javax.swing.JLabel();
        titulo49 = new javax.swing.JLabel();
        select_MvtoEquipo_CCAuxiliarDestino = new javax.swing.JComboBox<>();
        jSeparator37 = new javax.swing.JSeparator();
        mvtoEquipo_articulo = new javax.swing.JLabel();
        mvtoEquipo_AuxilarCentroOperacionDestino = new javax.swing.JLabel();
        mvtoEquipo_recobro = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        mvtoEquipo_motonave = new javax.swing.JLabel();
        mvtoEquipo_NumeroOrden = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        titulo50 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        InternalFrameSelectorCampos = new javax.swing.JInternalFrame();
        jSeparator21 = new javax.swing.JSeparator();
        selectPerfilUsuario_codigo = new javax.swing.JRadioButton();
        selectPerfilUsuario_descripcion = new javax.swing.JRadioButton();
        selectPerfilUsuario_estado = new javax.swing.JRadioButton();
        titulo22 = new javax.swing.JLabel();
        titulo10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        selectAll = new javax.swing.JRadioButton();
        selectMvtoEquipo_clienteCodigo = new javax.swing.JRadioButton();
        titulo2 = new javax.swing.JLabel();
        titulo12 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        selectAsignacion_equipoCodigo = new javax.swing.JRadioButton();
        selectAsignacion_equipoMarca = new javax.swing.JRadioButton();
        selectAsignacion_equipoModelo = new javax.swing.JRadioButton();
        selectMvtoEquipo_fechaRegistro = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioRegistraMvto_nombre = new javax.swing.JRadioButton();
        titulo21 = new javax.swing.JLabel();
        selectMvtoEquipo_cantidadMinutosDescargue = new javax.swing.JRadioButton();
        selectMvtoEquipo_numeroOrden = new javax.swing.JRadioButton();
        selectAsignacion_equipoTipo = new javax.swing.JRadioButton();
        selectMvtoEquipo_clienteNombre = new javax.swing.JRadioButton();
        selectMvtoEquipo_motonaveDescripción = new javax.swing.JRadioButton();
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
        titulo13 = new javax.swing.JLabel();
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
        titulo19 = new javax.swing.JLabel();
        titulo20 = new javax.swing.JLabel();
        titulo23 = new javax.swing.JLabel();
        titulo18 = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        consultar = new javax.swing.JButton();
        fechaInicio = new com.toedter.calendar.JDateChooser();
        minutoInicio = new javax.swing.JComboBox<>();
        horaInicio = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        fechaFin = new com.toedter.calendar.JDateChooser();
        minutoFin = new javax.swing.JComboBox<>();
        horaFin = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        alerta_fechaFinal = new javax.swing.JLabel();
        alerta_fechaInicio = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        horarioTiempoIFinalMvtoCarbon_Procesar = new javax.swing.JLabel();
        horarioTiempoInicioMvtoCarbon_Procesar = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        pageJComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        paginationPanel = new javax.swing.JPanel();

        Visualizar.setText("Seleccionar");
        Visualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VisualizarActionPerformed(evt);
            }
        });
        Seleccionar.add(Visualizar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InternaFrame_buscarMotonave.setClosable(true);
        InternaFrame_buscarMotonave.setVisible(false);
        InternaFrame_buscarMotonave.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaMotonaves = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tablaMotonaves.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaMotonaves.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMotonavesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaMotonaves);

        InternaFrame_buscarMotonave.getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 1090, 640));
        InternaFrame_buscarMotonave.getContentPane().add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 290, 40));

        btnConsultar.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        btnConsultar.setToolTipText("CONSULTAR MOTONAVES");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        InternaFrame_buscarMotonave.getContentPane().add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 60, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("CONSULTAR MOTONAVE:");
        InternaFrame_buscarMotonave.getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 190, 40));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btnCancelar.setToolTipText("CANCELAR BUSQUEDA");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        InternaFrame_buscarMotonave.getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 60, 40));

        btnLimpiar.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/clean.png"))); // NOI18N
        btnLimpiar.setToolTipText("BORRAR RESULTADOS");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        InternaFrame_buscarMotonave.getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 60, 40));

        add(InternaFrame_buscarMotonave, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1410, 760));

        InternaFrame_buscarArticulo.setClosable(true);
        InternaFrame_buscarArticulo.setVisible(false);
        InternaFrame_buscarArticulo.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaArticulo = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tablaArticulo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaArticulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaArticuloMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tablaArticulo);

        InternaFrame_buscarArticulo.getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 1090, 640));
        InternaFrame_buscarArticulo.getContentPane().add(valorBusquedaArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 290, 40));

        btnConsultarArticulo.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultarArticulo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultarArticulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        btnConsultarArticulo.setToolTipText("CONSULTAR MOTONAVES");
        btnConsultarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarArticuloActionPerformed(evt);
            }
        });
        InternaFrame_buscarArticulo.getContentPane().add(btnConsultarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 60, 40));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 102));
        jLabel10.setText("CONSULTAR ARTICULO:");
        InternaFrame_buscarArticulo.getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 190, 40));

        btnCancelarArticulo.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelarArticulo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancelarArticulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btnCancelarArticulo.setToolTipText("CANCELAR BUSQUEDA");
        btnCancelarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarArticuloActionPerformed(evt);
            }
        });
        InternaFrame_buscarArticulo.getContentPane().add(btnCancelarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 60, 40));

        btnLimpiarArticulo.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiarArticulo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLimpiarArticulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/clean.png"))); // NOI18N
        btnLimpiarArticulo.setToolTipText("BORRAR RESULTADOS");
        btnLimpiarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarArticuloActionPerformed(evt);
            }
        });
        InternaFrame_buscarArticulo.getContentPane().add(btnLimpiarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 60, 40));

        add(InternaFrame_buscarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1410, 760));

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

        InternaFrame_buscarCliente.getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 1090, 640));
        InternaFrame_buscarCliente.getContentPane().add(valorBusquedaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 290, 40));

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
        InternaFrame_buscarCliente.getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 190, 40));

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

        add(InternaFrame_buscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1410, 760));

        InternaFrame_VisualizarMvtoEquipo.setBackground(new java.awt.Color(255, 255, 255));
        InternaFrame_VisualizarMvtoEquipo.setClosable(true);
        InternaFrame_VisualizarMvtoEquipo.setVisible(false);
        InternaFrame_VisualizarMvtoEquipo.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 153, 153));
        jLabel17.setText("INFORMACIÓN DEL REGISTRO DEL EQUIPO");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 480, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("CENTRO OPERACIÓN:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 210, 150, 20));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("SUBCENTRO COSTO:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 230, 140, 20));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("C.C. AUXILIAR DESTINO:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 290, 200, 20));

        mvtoEquipo_estado.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_estado.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_estado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 510, 320, 20));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("CLIENTE CÓDIGO:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 130, 120, 20));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("MOTONAVE:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 190, 100, 20));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setText("MOTIVO DE FINALIZACIÓN:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 490, 190, 20));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("LABOR A REALIZAR:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 270, 130, 20));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("FECHA DE REGISTRO MOVIMIENTO:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 220, 20));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setText("FECHA DE INICIO:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 90, 130, 20));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setText("FECHA DE FINALIZACIÓN:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, 160, 20));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("CANTIDAD DE MINUTOS TRABAJADOS:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 430, 240, 20));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setText("USUARIO QUIEN REGISTRA NOMBRE:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 590, 230, 20));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(51, 51, 51));
        jLabel39.setText("NÚMERO DE ORDEN:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 550, 230, 20));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(51, 51, 51));
        jLabel41.setText("CANTIDAD DE HORAS TRABAJADAS:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 450, 240, 20));

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("OBSERVACIÓN:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 630, 240, 20));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(51, 51, 51));
        jLabel43.setText("RECOBRO:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 530, 130, 20));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(51, 51, 51));
        jLabel44.setText("ACTIVIDAD FINALIZADA:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 470, 190, 20));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(51, 51, 51));
        jLabel40.setText("PERTENENCIA DEL EQUIPO:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 410, 200, 20));
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 330, -1, -1));

        jScrollPane2.setForeground(new java.awt.Color(51, 51, 51));

        mvtoEquipo_Observación.setEditable(false);
        mvtoEquipo_Observación.setColumns(20);
        mvtoEquipo_Observación.setRows(5);
        jScrollPane2.setViewportView(mvtoEquipo_Observación);

        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 630, 320, 110));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("CÓDIGO:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 240, 20));

        mvtoEquipo_UsuarioRegistro_Correo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_UsuarioRegistro_Correo.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_UsuarioRegistro_Correo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_UsuarioRegistro_Correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 610, 320, 20));

        mvtoEquipo_fechaMvto.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_fechaMvto.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_fechaMvto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_fechaMvto, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 70, 320, 20));

        mvtoEquipo_CentroOperacion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_CentroOperacion.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_CentroOperacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_CentroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 210, 320, 20));

        mvtoEquipo_Subcentro.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Subcentro.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_Subcentro.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Subcentro, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 230, 320, 20));

        mvtoEquipo_AuxilarCentroOperacionOrigen.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_AuxilarCentroOperacionOrigen.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_AuxilarCentroOperacionOrigen.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_AuxilarCentroOperacionOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 250, 320, 20));

        mvtoEquipo_laborRealizada.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_laborRealizada.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_laborRealizada.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_laborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 270, 320, 20));

        mvtoEquipo_FechaInicioActividad.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_FechaInicioActividad.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_FechaInicioActividad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_FechaInicioActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 90, 320, 20));

        mvtoEquipo_FechaFinalActividad.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_FechaFinalActividad.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_FechaFinalActividad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_FechaFinalActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 110, 320, 20));

        mvtoEquipo_Minutos.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Minutos.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_Minutos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Minutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 430, 320, 20));

        mvtoEquipo_CantidadHoras.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_CantidadHoras.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_CantidadHoras.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_CantidadHoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 450, 320, 20));

        mvtoEquipo_ActividadFinalizada.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_ActividadFinalizada.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_ActividadFinalizada.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_ActividadFinalizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 470, 320, 20));

        mvtoEquipo_MotivoFinalización.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_MotivoFinalización.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_MotivoFinalización.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_MotivoFinalización, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 490, 320, 20));

        mvtoEquipo_codigo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_codigo.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_codigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 50, 320, 20));

        mvtoEquipo_ObservaciónEditar.setEditable(false);
        mvtoEquipo_ObservaciónEditar.setColumns(20);
        mvtoEquipo_ObservaciónEditar.setRows(5);
        jScrollPane4.setViewportView(mvtoEquipo_ObservaciónEditar);

        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 660, 390, 50));

        mvtoEquipo_Cliente_Código.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Cliente_Código.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_Cliente_Código.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Cliente_Código, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 130, 320, 20));

        mvtoEquipo_Cliente_Nombre.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Cliente_Nombre.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_Cliente_Nombre.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Cliente_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 150, 320, 20));

        mvtoEquipo_UsuarioRegistro_Código.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_UsuarioRegistro_Código.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_UsuarioRegistro_Código.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_UsuarioRegistro_Código, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 570, 320, 20));

        mvtoEquipo_UsuarioRegistro_Nombre.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_UsuarioRegistro_Nombre.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_UsuarioRegistro_Nombre.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_UsuarioRegistro_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 590, 320, 20));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(51, 51, 51));
        jLabel45.setText("USUARIO QUIEN REGISTRA CÓDIGO:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 570, 230, 20));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(51, 51, 51));
        jLabel46.setText("EQUIPO CÓDIGO:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 310, 120, 20));

        mvtoEquipo_Equipo_pertenencia.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Equipo_pertenencia.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_Equipo_pertenencia.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Equipo_pertenencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 410, 320, 20));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(51, 51, 51));
        jLabel47.setText("TIPO DE EQUIPO:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 330, 210, 20));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(51, 51, 51));
        jLabel49.setText("MARCA DE EQUIPO:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 350, 210, 20));

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(51, 51, 51));
        jLabel52.setText("DESCRIPCIÓN DEL EQUIPO:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 370, 210, 20));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(51, 51, 51));
        jLabel53.setText("PROVEEDOR DEL EQUIPO:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 390, 160, 20));

        mvtoEquipo_Equipo_codigo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Equipo_codigo.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_Equipo_codigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Equipo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 310, 320, 20));

        mvtoEquipo_Equipo_tipo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Equipo_tipo.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_Equipo_tipo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Equipo_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 330, 320, 20));

        mvtoEquipo_Equipo_marca.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Equipo_marca.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_Equipo_marca.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Equipo_marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 350, 320, 20));

        mvtoEquipo_Equipo_descripción.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Equipo_descripción.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_Equipo_descripción.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Equipo_descripción, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 370, 320, 20));

        mvtoEquipo_Equipo_proveedor.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Equipo_proveedor.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_Equipo_proveedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Equipo_proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 390, 320, 20));

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel60.setText("CAMPOS MODIFICABLES DE LA ACTIVIDAD");
        jLabel60.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 600, 20));

        check_MvtoEquipo_InicioActividad.setBackground(new java.awt.Color(255, 255, 255));
        check_MvtoEquipo_InicioActividad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_MvtoEquipo_InicioActividad.setForeground(new java.awt.Color(51, 51, 51));
        check_MvtoEquipo_InicioActividad.setText("FECHA DE INICIO:");
        check_MvtoEquipo_InicioActividad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                check_MvtoEquipo_InicioActividadItemStateChanged(evt);
            }
        });
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(check_MvtoEquipo_InicioActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 180, 30));

        fechaInicialActividad_MvtoEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicialActividad_MvtoEquipoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicialActividad_MvtoEquipoMouseEntered(evt);
            }
        });
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(fechaInicialActividad_MvtoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 170, 30));

        label_MvtoEquipo_HoraInicial.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_MvtoEquipo_HoraInicial.setForeground(new java.awt.Color(51, 51, 51));
        label_MvtoEquipo_HoraInicial.setText("Hora");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(label_MvtoEquipo_HoraInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, -1, 30));

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
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(select_MvtoEquipo_HoraInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 50, 30));

        label_MvtoEquipo_separadorInicial.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        label_MvtoEquipo_separadorInicial.setText(":");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(label_MvtoEquipo_separadorInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 10, 30));

        select_MvtoEquipo_MinutosInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_MinutosInicialActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(select_MvtoEquipo_MinutosInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, 50, 30));

        label_MvtoEquipo_ZonaHorariaInicial.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        label_MvtoEquipo_ZonaHorariaInicial.setForeground(new java.awt.Color(102, 102, 102));
        label_MvtoEquipo_ZonaHorariaInicial.setText("AM");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(label_MvtoEquipo_ZonaHorariaInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 50, 30));

        label_MvtoEquipo_ZonaHorariaFinal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        label_MvtoEquipo_ZonaHorariaFinal.setForeground(new java.awt.Color(102, 102, 102));
        label_MvtoEquipo_ZonaHorariaFinal.setText("AM");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(label_MvtoEquipo_ZonaHorariaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 50, 30));

        select_MvtoEquipo_MinutosFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_MinutosFinalActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(select_MvtoEquipo_MinutosFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 50, 30));

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
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(select_MvtoEquipo_HoraFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 50, 30));

        label_MvtoEquipo_separadorFinal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        label_MvtoEquipo_separadorFinal.setText(":");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(label_MvtoEquipo_separadorFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 10, 30));

        label_MvtoEquipo_HoraFinal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_MvtoEquipo_HoraFinal.setForeground(new java.awt.Color(51, 51, 51));
        label_MvtoEquipo_HoraFinal.setText("Hora");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(label_MvtoEquipo_HoraFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, -1, 30));

        fechaFinActividad_MvtoEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFinActividad_MvtoEquipoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFinActividad_MvtoEquipoMouseEntered(evt);
            }
        });
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(fechaFinActividad_MvtoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 170, 30));

        check_MvtoEquipo_FinalizacionActividad.setBackground(new java.awt.Color(255, 255, 255));
        check_MvtoEquipo_FinalizacionActividad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_MvtoEquipo_FinalizacionActividad.setForeground(new java.awt.Color(51, 51, 51));
        check_MvtoEquipo_FinalizacionActividad.setText("FECHA DE FINALIZACIÓN:");
        check_MvtoEquipo_FinalizacionActividad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                check_MvtoEquipo_FinalizacionActividadItemStateChanged(evt);
            }
        });
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(check_MvtoEquipo_FinalizacionActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 180, 30));

        titulo29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo29.setForeground(new java.awt.Color(51, 51, 51));
        titulo29.setText("CENTRO OPERACIÓN:");
        titulo29.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(titulo29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 170, 30));

        select_MvtoEquipo_CO.setToolTipText("");
        select_MvtoEquipo_CO.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoEquipo_COItemStateChanged(evt);
            }
        });
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(select_MvtoEquipo_CO, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 390, 30));

        titulo25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo25.setForeground(new java.awt.Color(51, 51, 51));
        titulo25.setText("SUBCENTRO COSTO:");
        titulo25.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(titulo25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 160, 30));

        select_MvtoEquipo_SubcentroCosto.setToolTipText("");
        select_MvtoEquipo_SubcentroCosto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoEquipo_SubcentroCostoItemStateChanged(evt);
            }
        });
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(select_MvtoEquipo_SubcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 390, 30));

        titulo45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo45.setForeground(new java.awt.Color(51, 51, 51));
        titulo45.setText("C.C. AUXILIAR ORIGEN:");
        titulo45.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(titulo45, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 160, 30));

        select_MvtoEquipo_CCAuxiliar.setToolTipText("");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(select_MvtoEquipo_CCAuxiliar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, 390, 30));

        select_MvtoEquipo_laborRealizada.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoEquipo_laborRealizadaItemStateChanged(evt);
            }
        });
        select_MvtoEquipo_laborRealizada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_laborRealizadaActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(select_MvtoEquipo_laborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 390, 30));

        titulo46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo46.setForeground(new java.awt.Color(51, 51, 51));
        titulo46.setText("LABOR A REALIZAR:");
        titulo46.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(titulo46, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 170, 30));

        select_MvtoEquipo_recobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_recobroActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(select_MvtoEquipo_recobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 580, 390, 30));

        titulo47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo47.setForeground(new java.awt.Color(51, 51, 51));
        titulo47.setText("RECOBRO:");
        titulo47.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(titulo47, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, 150, 30));

        titulo58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo58.setForeground(new java.awt.Color(51, 51, 51));
        titulo58.setText("TIPO DE EQUIPO:");
        titulo58.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(titulo58, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 150, 30));

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
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(select_MvtoEquipo_tipoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 460, 390, 30));

        titulo61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo61.setForeground(new java.awt.Color(51, 51, 51));
        titulo61.setText("MARCA DE EQUIPO");
        titulo61.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(titulo61, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 130, 30));

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
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(select_MvtoEquipo_marcaEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 500, 390, 30));

        titulo62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo62.setForeground(new java.awt.Color(51, 51, 51));
        titulo62.setText("SELECCIONAR EQUIPO:");
        titulo62.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(titulo62, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 150, 30));

        select_MvtoEquipo_Equipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_EquipoActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(select_MvtoEquipo_Equipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 540, 390, 30));

        titulo48.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        titulo48.setForeground(new java.awt.Color(255, 51, 51));
        titulo48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo48.setText("*");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(titulo48, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 800, 20, 20));

        textArea_MvtoEquipo_razonModificacion.setColumns(20);
        textArea_MvtoEquipo_razonModificacion.setRows(5);
        jScrollPane7.setViewportView(textArea_MvtoEquipo_razonModificacion);

        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 790, 610, 80));

        jSeparator36.setForeground(new java.awt.Color(51, 51, 51));
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jSeparator36, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 1210, 10));

        registrar_MvtoEquipo.setBackground(new java.awt.Color(255, 255, 255));
        registrar_MvtoEquipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        registrar_MvtoEquipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save.png"))); // NOI18N
        registrar_MvtoEquipo.setText("REGISTRAR MODIFICACIÓN");
        registrar_MvtoEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrar_MvtoEquipoActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(registrar_MvtoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 800, 280, 40));

        titulo27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo27.setForeground(new java.awt.Color(51, 51, 51));
        titulo27.setText("OBSERVACIÓN:");
        titulo27.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(titulo27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 660, 130, 30));

        checkMotonave.setBackground(new java.awt.Color(255, 255, 255));
        checkMotonave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        checkMotonave.setForeground(new java.awt.Color(51, 51, 51));
        checkMotonave.setText("MOTONAVE:");
        checkMotonave.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        checkMotonave.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkMotonaveItemStateChanged(evt);
            }
        });
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(checkMotonave, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 130, 30));

        icon_buscarMotonave.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        icon_buscarMotonave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        icon_buscarMotonave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_buscarMotonaveMouseClicked(evt);
            }
        });
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(icon_buscarMotonave, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 30, 30));

        label_motonave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_motonave.setForeground(new java.awt.Color(51, 51, 51));
        label_motonave.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(label_motonave, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 390, 30));

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
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(checkCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 130, 30));

        icon_buscarClientes.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        icon_buscarClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        icon_buscarClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_buscarClientesMouseClicked(evt);
            }
        });
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(icon_buscarClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 30, 30));

        label_clientes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_clientes.setForeground(new java.awt.Color(51, 51, 51));
        label_clientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(label_clientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 390, 30));

        checkArticulo.setBackground(new java.awt.Color(255, 255, 255));
        checkArticulo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        checkArticulo.setForeground(new java.awt.Color(51, 51, 51));
        checkArticulo.setText("ARTICULO");
        checkArticulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        checkArticulo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkArticuloItemStateChanged(evt);
            }
        });
        checkArticulo.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                checkArticuloCaretPositionChanged(evt);
            }
        });
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(checkArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 130, 30));

        icon_buscarArticulo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        icon_buscarArticulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        icon_buscarArticulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_buscarArticuloMouseClicked(evt);
            }
        });
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(icon_buscarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 30, 30));

        label_articulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_articulo.setForeground(new java.awt.Color(51, 51, 51));
        label_articulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(label_articulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 390, 30));
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_numOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 620, 390, 30));

        mvtoEquipo_selectRazonFinalzación.setToolTipText("");
        mvtoEquipo_selectRazonFinalzación.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mvtoEquipo_selectRazonFinalzaciónItemStateChanged(evt);
            }
        });
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_selectRazonFinalzación, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 720, 390, 30));

        titulo28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo28.setForeground(new java.awt.Color(51, 51, 51));
        titulo28.setText("NÚMERO ORDEN:");
        titulo28.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(titulo28, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, 140, 30));

        titulo30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo30.setForeground(new java.awt.Color(51, 51, 51));
        titulo30.setText("RAZÓN DE FINALIZACIÓN:");
        titulo30.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(titulo30, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 720, 180, 30));

        titulo49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo49.setForeground(new java.awt.Color(51, 51, 51));
        titulo49.setText("C.C. AUXILIAR DESTINO:");
        titulo49.setPreferredSize(new java.awt.Dimension(133, 15));
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(titulo49, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 170, 30));

        select_MvtoEquipo_CCAuxiliarDestino.setToolTipText("");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(select_MvtoEquipo_CCAuxiliarDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, 390, 30));
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jSeparator37, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 780, 600, 10));

        mvtoEquipo_articulo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_articulo.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_articulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_articulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 170, 320, 20));

        mvtoEquipo_AuxilarCentroOperacionDestino.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_AuxilarCentroOperacionDestino.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_AuxilarCentroOperacionDestino.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_AuxilarCentroOperacionDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 290, 320, 20));

        mvtoEquipo_recobro.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_recobro.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_recobro.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_recobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 530, 320, 20));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("C.C. AUXILIAR ORIGEN:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 250, 150, 20));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("CLIENTE NOMBRE:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 150, 120, 20));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setText("ARTÍCULO:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 170, 100, 20));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(51, 51, 51));
        jLabel54.setText("ESTADO");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 510, 130, 20));

        mvtoEquipo_motonave.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_motonave.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_motonave.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_motonave, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 190, 320, 20));

        mvtoEquipo_NumeroOrden.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_NumeroOrden.setForeground(new java.awt.Color(51, 51, 51));
        mvtoEquipo_NumeroOrden.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_NumeroOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 550, 320, 20));

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(51, 51, 51));
        jLabel48.setText("USUARIO QUIEN REGISTRA CORREO:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 610, 230, 20));

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel69.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 50, 340, 730));

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel70.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, 270, 730));

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel61.setText("INFORMACIÓN DEL MOVIMIENTO DEL EQUIPO");
        jLabel61.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, 610, 20));

        titulo50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo50.setForeground(new java.awt.Color(51, 51, 51));
        titulo50.setText("RAZON DE MODIFICACIÓN:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(titulo50, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 790, 170, 30));

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel68.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 1210, 840));

        add(InternaFrame_VisualizarMvtoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1770, 1110));

        InternalFrameSelectorCampos.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrameSelectorCampos.setClosable(true);
        InternalFrameSelectorCampos.setVisible(false);
        InternalFrameSelectorCampos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator21.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternalFrameSelectorCampos.getContentPane().add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 0, 430));

        selectPerfilUsuario_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectPerfilUsuario_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectPerfilUsuario_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectPerfilUsuario_codigo.setText("Perfil Código");
        selectPerfilUsuario_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectPerfilUsuario_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 100, 190, -1));

        selectPerfilUsuario_descripcion.setBackground(new java.awt.Color(255, 255, 255));
        selectPerfilUsuario_descripcion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectPerfilUsuario_descripcion.setForeground(new java.awt.Color(51, 51, 51));
        selectPerfilUsuario_descripcion.setText("Perfil Descripción");
        selectPerfilUsuario_descripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectPerfilUsuario_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 120, 190, -1));

        selectPerfilUsuario_estado.setBackground(new java.awt.Color(255, 255, 255));
        selectPerfilUsuario_estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectPerfilUsuario_estado.setForeground(new java.awt.Color(51, 51, 51));
        selectPerfilUsuario_estado.setText("Perfil Estado");
        selectPerfilUsuario_estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectPerfilUsuario_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 140, 190, -1));

        titulo22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo22.setText("PERFIL USUARIO");
        titulo22.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 70, 240, 20));

        titulo10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 70, 240, 470));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCamposAplicarConfig.png"))); // NOI18N
        jButton2.setText("Aplicar Configuración");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 500, 230, 30));

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
        InternalFrameSelectorCampos.getContentPane().add(selectAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 20, 110, 20));

        selectMvtoEquipo_clienteCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_clienteCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_clienteCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_clienteCodigo.setText("Cliente Código");
        selectMvtoEquipo_clienteCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_clienteCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 340, 200, -1));

        titulo2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo2.setText("SELECTOR DE CAMPOS");
        titulo2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 720, 40));

        titulo12.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        titulo12.setText("PROGRAMACIÓN (ASIGNACIÓN)");
        titulo12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo12, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 210, 240, 20));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCamposDefauls.png"))); // NOI18N
        jButton3.setText("Campos Predeterminados");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 500, 230, 30));

        selectAsignacion_equipoCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoCodigo.setText("Equipo Código");
        selectAsignacion_equipoCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 80, 190, -1));

        selectAsignacion_equipoMarca.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoMarca.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoMarca.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoMarca.setText("Equipo Marca");
        selectAsignacion_equipoMarca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 120, 190, -1));

        selectAsignacion_equipoModelo.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoModelo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoModelo.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoModelo.setText("Equipo Modelo");
        selectAsignacion_equipoModelo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 140, 190, -1));

        selectMvtoEquipo_fechaRegistro.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_fechaRegistro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_fechaRegistro.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_fechaRegistro.setText("Fecha_Registro");
        selectMvtoEquipo_fechaRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_fechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 190, -1));

        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setText("Nombre_Usuario_Registra_MvtoEquipo");
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioRegistraMvto_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, 220, -1));

        titulo21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo21, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 240, 20));

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
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_cantidadMinutosDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 190, -1));

        selectMvtoEquipo_numeroOrden.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_numeroOrden.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_numeroOrden.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_numeroOrden.setText("Número Orden");
        selectMvtoEquipo_numeroOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_numeroOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, 190, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 100, 190, -1));

        selectMvtoEquipo_clienteNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_clienteNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_clienteNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_clienteNombre.setText("Cliente Nombre");
        selectMvtoEquipo_clienteNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_clienteNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 360, 200, -1));

        selectMvtoEquipo_motonaveDescripción.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_motonaveDescripción.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_motonaveDescripción.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_motonaveDescripción.setText("Motonave Descripción");
        selectMvtoEquipo_motonaveDescripción.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_motonaveDescripción.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_motonaveDescripciónActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_motonaveDescripción, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 440, 200, -1));

        selectMvtoEquipo_centroOperacion.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroOperacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroOperacion.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroOperacion.setText("Centro Operación");
        selectMvtoEquipo_centroOperacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, 200, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_subcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, 200, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 280, 190, -1));

        selectAsignacion_fechaFinalizacion.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_fechaFinalizacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_fechaFinalizacion.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_fechaFinalizacion.setText("Fecha_Finalización");
        selectAsignacion_fechaFinalizacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_fechaFinalizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 300, 190, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_cantidadMinutos_Programados, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 320, 190, -1));

        selectAsignacion_equipoPertenencia.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoPertenencia.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoPertenencia.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoPertenencia.setText("Equipo Pertenencia");
        selectAsignacion_equipoPertenencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoPertenencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 180, 190, -1));

        selectMvtoEquipo_proveedorEquipoNit.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_proveedorEquipoNit.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_proveedorEquipoNit.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_proveedorEquipoNit.setText("Proveedor Equipo NIT");
        selectMvtoEquipo_proveedorEquipoNit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_proveedorEquipoNit, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 190, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_proveedorEquipoNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 190, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_laborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, 190, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_fechaInicioDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 460, 190, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_fechaFinDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 190, -1));

        selectMvtoEquipo_ValorHoraEquipo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_ValorHoraEquipo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_ValorHoraEquipo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_ValorHoraEquipo.setText("Valor_Hora_Equipo");
        selectMvtoEquipo_ValorHoraEquipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_ValorHoraEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, 190, -1));

        selectMvtoEquipo_costoTotal.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_costoTotal.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_costoTotal.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_costoTotal.setText("Costo_Total");
        selectMvtoEquipo_costoTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_costoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 190, -1));

        selectMvtoEquipo_Recobro.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_Recobro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_Recobro.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_Recobro.setText("Recobro");
        selectMvtoEquipo_Recobro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_Recobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, 190, -1));

        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setText("Código_Usuario_Registra_MvtoEquipo");
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioRegistraMvto_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, 210, -1));

        selectMvtoEquipo_CausalInactividad.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_CausalInactividad.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_CausalInactividad.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_CausalInactividad.setText("Causal_Inactividad");
        selectMvtoEquipo_CausalInactividad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_CausalInactividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 320, 220, -1));

        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setText("Código_Usuario_Autoriza_Recobro");
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioAutorizaRecobro_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 220, 210, -1));

        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setText("Nombre_Usuario_Autoriza_Recobro");
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioAutorizaRecobro_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 240, 220, -1));

        selectMvtoEquipo_autorizacionRecobro.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_autorizacionRecobro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_autorizacionRecobro.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_autorizacionRecobro.setText("Autorización_Recobro");
        selectMvtoEquipo_autorizacionRecobro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_autorizacionRecobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 260, 220, -1));

        selectMvtoEquipo_MotivoParada.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_MotivoParada.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_MotivoParada.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_MotivoParada.setText("Motivo_Parada");
        selectMvtoEquipo_MotivoParada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_MotivoParada, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 380, 220, -1));

        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setText("Observación_Autorización_Recobro");
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioAutorizaRecobro_observacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 280, 220, -1));

        selectMvtoEquipo_Inactividad.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_Inactividad.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_Inactividad.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_Inactividad.setText("Inactividad");
        selectMvtoEquipo_Inactividad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_Inactividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, 220, -1));

        selectMvtoEquipo_UsuarioInactividad_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioInactividad_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioInactividad_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioInactividad_codigo.setText("Código_Usuario_Inactividad");
        selectMvtoEquipo_UsuarioInactividad_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_UsuarioInactividad_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_UsuarioInactividad_codigoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioInactividad_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 340, 210, -1));

        selectMvtoEquipo_DesdeCarbon.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_DesdeCarbon.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_DesdeCarbon.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_DesdeCarbon.setText("Desde_Carbón");
        selectMvtoEquipo_DesdeCarbon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_DesdeCarbon, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 440, 220, -1));

        selectMvtoEquipo_UsuarioInactividad_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioInactividad_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioInactividad_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioInactividad_nombre.setText("Nombre_Usuario_Inactividad");
        selectMvtoEquipo_UsuarioInactividad_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioInactividad_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 360, 220, -1));

        selectMvtoEquipo_centroCostoMayor.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCostoMayor.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCostoMayor.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCostoMayor.setText("CentroCosto Mayor");
        selectMvtoEquipo_centroCostoMayor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroCostoMayor, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, 200, -1));

        selectMvtoEquipo_observacion.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_observacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_observacion.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_observacion.setText("Observación");
        selectMvtoEquipo_observacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_observacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 400, 220, -1));

        selectMvtoEquipo_estado.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_estado.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_estado.setText("Estado");
        selectMvtoEquipo_estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 420, 220, -1));

        selectAsignacion_equipoDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoDescripcion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoDescripcion.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoDescripcion.setText("Equipo Descripción");
        selectAsignacion_equipoDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectAsignacion_equipoDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsignacion_equipoDescripcionActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 160, 190, -1));

        titulo13.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        titulo13.setText("DATOS DEL EQUIPO");
        titulo13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo13, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, 240, 20));

        selectAsignacion_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_codigo.setText("Código");
        selectAsignacion_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 240, 190, -1));

        selectMvtoEquipo_centroCosto.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCosto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCosto.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCosto.setText("Centro Costo");
        selectMvtoEquipo_centroCosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, 190, -1));

        selectAsignacion_fechaRegistro.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_fechaRegistro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_fechaRegistro.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_fechaRegistro.setText("Fecha_Registro");
        selectAsignacion_fechaRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_fechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 260, 190, -1));

        selectMvtoEquipo_articuloCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_articuloCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_articuloCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_articuloCodigo.setText("Articulo código");
        selectMvtoEquipo_articuloCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_articuloCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_articuloCodigoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_articuloCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, 200, -1));

        selectMvtoEquipo_articuloDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_articuloDescripcion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_articuloDescripcion.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_articuloDescripcion.setText("Articulo Descripción");
        selectMvtoEquipo_articuloDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_articuloDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_articuloDescripcionActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_articuloDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 400, 200, -1));

        selectMvtoEquipo_motonaveCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_motonaveCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_motonaveCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_motonaveCodigo.setText("Motonave Código");
        selectMvtoEquipo_motonaveCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_motonaveCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_motonaveCodigoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_motonaveCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 420, 200, -1));

        selectMvtoEquipo_centroCostoAxiliarOrigen.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCostoAxiliarOrigen.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCostoAxiliarOrigen.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCostoAxiliarOrigen.setText("Auxiliar Centro Costo Origen");
        selectMvtoEquipo_centroCostoAxiliarOrigen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroCostoAxiliarOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, 200, -1));

        selectMvtoEquipo_centroCostoAuxiliarDestino.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCostoAuxiliarDestino.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCostoAuxiliarDestino.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCostoAuxiliarDestino.setText("Auxiliar Centro Costo Destino");
        selectMvtoEquipo_centroCostoAuxiliarDestino.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroCostoAuxiliarDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, 200, -1));

        selectMvtoEquipo_mes.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_mes.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_mes.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_mes.setText("Mes_Registro");
        selectMvtoEquipo_mes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 190, -1));

        selectMvtoEquipo_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_codigo.setText("Código");
        selectMvtoEquipo_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 190, -1));

        titulo19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo19, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, 240, 430));

        titulo20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo20, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 240, 410));

        titulo23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo23.setText("Mvto Equipo");
        titulo23.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo23, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 240, 20));

        titulo18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo18, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 240, 410));

        add(InternalFrameSelectorCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 690));

        titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo.setText("MODIFICACIÓN DE REGISTROS DEL MODULO DE EQUIPOS");
        add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 670, 20));

        tabla = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
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
        tabla.setComponentPopupMenu(Seleccionar);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 1300, 500));

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
        add(consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, 210, 35));

        fechaInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicioMouseEntered(evt);
            }
        });
        add(fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 170, 30));

        minutoInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoInicioActionPerformed(evt);
            }
        });
        add(minutoInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 50, 30));

        horaInicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                horaInicioItemStateChanged(evt);
            }
        });
        add(horaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 50, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Hora");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, -1, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("FECHA/HORA FIN");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, 20));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 65)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("|");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 30, 40));

        fechaFin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFinMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFinMouseEntered(evt);
            }
        });
        add(fechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, 170, 30));

        minutoFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoFinActionPerformed(evt);
            }
        });
        add(minutoFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, 50, 30));

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
        add(horaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 40, 50, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Hora");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, -1, 30));

        alerta_fechaFinal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_fechaFinal.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_fechaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 370, 20));

        alerta_fechaInicio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_fechaInicio.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 370, 20));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 1090, 20));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCampo.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 50, 40, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Campos");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 30, 40, 20));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel24.setText(":");
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, 20, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel25.setText(":");
        add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 20, 30));

        horarioTiempoIFinalMvtoCarbon_Procesar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoIFinalMvtoCarbon_Procesar.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoIFinalMvtoCarbon_Procesar.setText("AM");
        add(horarioTiempoIFinalMvtoCarbon_Procesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 50, 30));

        horarioTiempoInicioMvtoCarbon_Procesar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoInicioMvtoCarbon_Procesar.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoInicioMvtoCarbon_Procesar.setText("AM");
        add(horarioTiempoInicioMvtoCarbon_Procesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 50, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("FECHA/HORA INICIO");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));

        add(pageJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 10, 60, 40));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/ExportarExcel.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 50, 40, 30));

        paginationPanel.setBackground(new java.awt.Color(255, 255, 255));
        add(paginationPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 620, 1300, 70));
    }// </editor-fold>//GEN-END:initComponents

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        paginationPanel.removeAll();
        validarSeleccionCampos();
        generarListadoMvtoCarbon();
        
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

    private void VisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizarActionPerformed
        int fila1;
        try{
            fila1=tabla.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ningun registro");
            }
            else{
                int posicion = paginadorDeTabla.getPosicionador();
                if(posicion != -1){
                    fila1 = fila1 +posicion;
                }
                mvtoEquipo = listado.get(fila1);
                InternaFrame_VisualizarMvtoEquipo.show(true);
                  
                //Limpiamos campos de la Interfaz
                mvtoEquipo_codigo.setText("");
                mvtoEquipo_fechaMvto.setText("");
                mvtoEquipo_FechaInicioActividad.setText("");
                mvtoEquipo_FechaFinalActividad.setText("");
                mvtoEquipo_Cliente_Código.setText("");
                mvtoEquipo_Cliente_Nombre.setText("");
                mvtoEquipo_articulo.setText("");
                mvtoEquipo_motonave.setText("");
                mvtoEquipo_CentroOperacion.setText("");
                mvtoEquipo_Subcentro.setText("");
                mvtoEquipo_AuxilarCentroOperacionOrigen.setText("");
                mvtoEquipo_laborRealizada.setText("");
                mvtoEquipo_AuxilarCentroOperacionDestino.setText("");
                mvtoEquipo_Equipo_codigo.setText("");
                mvtoEquipo_Equipo_tipo.setText("");
                mvtoEquipo_Equipo_marca.setText("");
                mvtoEquipo_Equipo_descripción.setText("");
                mvtoEquipo_Equipo_proveedor.setText("");
                mvtoEquipo_Equipo_pertenencia.setText("");
                mvtoEquipo_Minutos.setText("");
                mvtoEquipo_CantidadHoras.setText("");
                mvtoEquipo_ActividadFinalizada.setText("");
                mvtoEquipo_MotivoFinalización.setText("");
                mvtoEquipo_estado.setText("");
                mvtoEquipo_recobro.setText("");
                mvtoEquipo_UsuarioRegistro_Código.setText("");
                mvtoEquipo_UsuarioRegistro_Nombre.setText("");
                mvtoEquipo_UsuarioRegistro_Correo.setText("");
                mvtoEquipo_Observación.setText("");
                label_clientes.setText("");
                label_articulo.setText("");
                label_motonave.setText("");
                mvtoEquipo_numOrden.setText("");
                mvtoEquipo_ObservaciónEditar.setText("");
                textArea_MvtoEquipo_razonModificacion.setText("");
                
                

    //cargamos los campos de la Interfaz
                mvtoEquipo_codigo.setText(mvtoEquipo.getCodigo());
                mvtoEquipo_fechaMvto.setText(mvtoEquipo.getFechaRegistro());
                mvtoEquipo_FechaInicioActividad.setText(mvtoEquipo.getFechaHoraInicio());
                mvtoEquipo_FechaFinalActividad.setText(mvtoEquipo.getFechaHoraFin());
                mvtoEquipo_Cliente_Código.setText(mvtoEquipo.getCliente().getCodigo());
                mvtoEquipo_Cliente_Nombre.setText(mvtoEquipo.getCliente().getDescripcion());
                mvtoEquipo_articulo.setText(mvtoEquipo.getArticulo().getDescripcion());
                mvtoEquipo_motonave.setText(mvtoEquipo.getMotonave().getDescripcion());
                mvtoEquipo_CentroOperacion.setText(mvtoEquipo.getCentroOperacion().getDescripcion());
                mvtoEquipo_Subcentro.setText(mvtoEquipo.getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion());
                mvtoEquipo_AuxilarCentroOperacionOrigen.setText(mvtoEquipo.getCentroCostoAuxiliar().getDescripcion());
                mvtoEquipo_laborRealizada.setText(mvtoEquipo.getLaborRealizada().getDescripcion());
                mvtoEquipo_AuxilarCentroOperacionDestino.setText(mvtoEquipo.getCentroCostoAuxiliarDestino().getDescripcion());
                mvtoEquipo_Equipo_codigo.setText(mvtoEquipo.getAsignacionEquipo().getEquipo().getCodigo());
                mvtoEquipo_Equipo_tipo.setText(mvtoEquipo.getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion());
                mvtoEquipo_Equipo_marca.setText(mvtoEquipo.getAsignacionEquipo().getEquipo().getMarca());
                mvtoEquipo_Equipo_descripción.setText(mvtoEquipo.getAsignacionEquipo().getEquipo().getDescripcion()+" "+mvtoEquipo.getAsignacionEquipo().getEquipo().getModelo());
                mvtoEquipo_Equipo_proveedor.setText(mvtoEquipo.getProveedorEquipo().getDescripcion());
                mvtoEquipo_Equipo_pertenencia.setText(mvtoEquipo.getAsignacionEquipo().getPertenencia().getDescripcion());
                if(mvtoEquipo.getTotalMinutos()!=null){
                    try{
                        mvtoEquipo_Minutos.setText(mvtoEquipo.getTotalMinutos());
                        double des=Double.parseDouble(mvtoEquipo.getTotalMinutos());
                        double totalHoras = Double.parseDouble(""+(des/60));
                        DecimalFormat formato2 = new DecimalFormat("0.00");
                        mvtoEquipo_CantidadHoras.setText(""+formato2.format(totalHoras));
                    }catch(Exception e){
                        mvtoEquipo_Minutos.setText("");
                        mvtoEquipo_CantidadHoras.setText("");
                    }
                }else{
                    mvtoEquipo_Minutos.setText("");
                        mvtoEquipo_CantidadHoras.setText("");
                }
                if(mvtoEquipo.getMotivoParadaEstado().equals("1")){
                    mvtoEquipo_ActividadFinalizada.setText("SI");
                }else{
                    mvtoEquipo_ActividadFinalizada.setText("NO");
                }
                mvtoEquipo_MotivoFinalización.setText(mvtoEquipo.getMotivoParada().getDescripcion());
                mvtoEquipo_estado.setText(mvtoEquipo.getEstado());
                mvtoEquipo_recobro.setText(mvtoEquipo.getRecobro().getDescripcion());
                mvtoEquipo_UsuarioRegistro_Código.setText(mvtoEquipo.getUsuarioQuieRegistra().getCodigo());
                mvtoEquipo_UsuarioRegistro_Nombre.setText(mvtoEquipo.getUsuarioQuieRegistra().getNombres()+" "+mvtoEquipo.getUsuarioQuieRegistra().getApellidos());
                mvtoEquipo_UsuarioRegistro_Correo.setText(mvtoEquipo.getUsuarioQuieRegistra().getCorreo());
                mvtoEquipo_Observación.setText(mvtoEquipo.getObservacionMvtoEquipo());
                mvtoEquipo_NumeroOrden.setText(mvtoEquipo.getNumeroOrden());
                
    //cargamos el cliente del mvtoEquipo registrado a la variable que almacenerá los cambios para el cliente
                cliente=null;
                cliente =mvtoEquipo.getCliente();
                label_motonave.setText(mvtoEquipo.getMotonave().getDescripcion());
                label_clientes.setText(mvtoEquipo.getCliente().getDescripcion());
                label_articulo.setText(mvtoEquipo.getArticulo().getDescripcion());

                mvtoEquipo_numOrden.setText(mvtoEquipo.getNumeroOrden());
                mvtoEquipo_ObservaciónEditar.setText(mvtoEquipo.getObservacionMvtoEquipo());

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
                    JOptionPane.showMessageDialog(null, "No se pudo carga la fecha de Inicio de actividad","Error!!.", JOptionPane.ERROR_MESSAGE);
                }

   //Cargamos la fecha de finalización de la actividad
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
                        
                        
                    }else{//No hay
                        listadoMotivoParada_mvtoEquipo=null;
                        mvtoEquipo_selectRazonFinalzación.removeAllItems();
                    }
                }catch(Exception e){
                    System.out.println(""+mvtoEquipo.getFechaHoraFin());
                    JOptionPane.showMessageDialog(null, "No se pudo carga la fecha de finalización de actividad","Error!!.", JOptionPane.ERROR_MESSAGE);
                }

    //cargamos el Centro de Operación según la consulta
                try{
                    int contador=0;
                    for(CentroOperacion centroOperacion: listadoCentroOperacion_mvtoEquipo){
                        if(centroOperacion.getDescripcion().equals(mvtoEquipo.getCentroOperacion().getDescripcion())){
                              select_MvtoEquipo_CO.setSelectedIndex(contador);  
                        }
                        contador++;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                cambioEstado("Subcentro_Costo");
                //cargamos el subcentro de costo según la consulta
                try{
                    int contador=0;
                    for(CentroCostoSubCentro centroCostoSubCentro: listadoCentroCostoSubCentro_mvtoEquipo){
                        if(centroCostoSubCentro.getDescripcion().equals(mvtoEquipo.getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion())){
                              select_MvtoEquipo_SubcentroCosto.setSelectedIndex(contador);  
                        }
                        contador++;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
    //cargamos los auxiliares de Centro de costos según la consulta
                try{
                    int contador=0;
                    for(CentroCostoAuxiliar centroCostoAuxiliar: listadoCentroCostoAuxiliar_mvtoEquipo){
                        if(centroCostoAuxiliar.getDescripcion().equals(mvtoEquipo.getCentroCostoAuxiliar().getDescripcion())){
                              select_MvtoEquipo_CCAuxiliar.setSelectedIndex(contador);  
                        }
                        contador++;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                //mvto_listEquipo.getMvtoEquipo().getLaborRealizada().getDescripcion()
    //cargamos las labores realizadas según la consulta
                try{
                    int contador=0;
                    System.out.println("cargador fue ===========>"+mvtoEquipo.getLaborRealizada().getDescripcion());
                    for(LaborRealizada laborRealizada: listadoLaborRealizada_mvtoEquipo){
                        if(laborRealizada.getDescripcion().equals(mvtoEquipo.getLaborRealizada().getDescripcion())){
                              select_MvtoEquipo_laborRealizada.setSelectedIndex(contador);  
                               System.out.println("encontrado fue ===========>"+laborRealizada.getDescripcion());
                        }
                        contador++;
                        System.out.println("consultado fue ===========>"+laborRealizada.getDescripcion());
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }

    //cargamos los centros de costos auxiliares destino según la consulta
                try{
                    if(listadoCentroCostoAuxiliarDestino_mvtoEquipo !=null){
                        int contador=0;
                        for(CentroCostoAuxiliar centroCostoAuxiliar: listadoCentroCostoAuxiliarDestino_mvtoEquipo){
                            if(centroCostoAuxiliar.getDescripcion().equals(mvtoEquipo.getCentroCostoAuxiliarDestino().getDescripcion())){
                                  select_MvtoEquipo_CCAuxiliarDestino.setSelectedIndex(contador);  
                            }
                            contador++;
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                //cargamos los recobro según la consulta
                try{
                    int contador=0;
                    for(Recobro recobro: listadoRecobros_mvtoEquipo){
                        if(recobro.getDescripcion().equals(mvtoEquipo.getRecobro().getDescripcion())){
                              select_MvtoEquipo_recobro.setSelectedIndex(contador);  
                        }
                        contador++;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
    //Cargamos los equipos disponible en el rango de fecha.
                listadoTipoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarTipoEquiposProgramados(mvtoEquipo.getFechaRegistro());
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
                    listadoMarcaEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarMarcaEquiposProgramados(mvtoEquipo.getFechaRegistro(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion());
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
                    listadoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarAsignacionEquipos_Por_TipoEquipo_MarcaEquipo(mvtoEquipo.getFechaRegistro(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion(),listadoMarcaEquipos_mvtoEquipo.get(select_MvtoEquipo_marcaEquipo.getSelectedIndex()));
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
                    if(Objeto.getDescripcion().equals(mvtoEquipo.getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion())){
                        select_MvtoEquipo_tipoEquipo.setSelectedIndex(contador);
                    }
                    contador++;
                }
                contador=0;
                for(String Objeto : listadoMarcaEquipos_mvtoEquipo){ 
                    if(Objeto.equals(mvtoEquipo.getAsignacionEquipo().getEquipo().getMarca())){
                        select_MvtoEquipo_marcaEquipo.setSelectedIndex(contador);
                    }
                    contador++;
                }
                contador=0;
                for(AsignacionEquipo Objeto : listadoEquipos_mvtoEquipo){ 
                    if(Objeto.getCodigo().equals(mvtoEquipo.getAsignacionEquipo().getCodigo())){
                        select_MvtoEquipo_Equipo.setSelectedIndex(contador);
                    }
                    contador++;
                }
                if(mvtoEquipo.getFechaHoraFin() != null){
                    if(mvtoEquipo.getLaborRealizada().getDescripcion().equalsIgnoreCase("STAND BY")){//Fue un MvtoEquipo donde la labor fue Stand BY, por tal motivo no debe aparecer el motivo de parada de finalizado.
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
                                    mvtoEquipo_selectRazonFinalzación.setModel(model);


                                    //cargamos los motivos de parada según la consulta
                                    try{
                                        if(listadoMotivoParada_mvtoEquipo !=null){
                                            int contadorX=0;
                                            for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                                if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                                      mvtoEquipo_selectRazonFinalzación.setSelectedIndex(contadorX);  
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
                    }else{//Como la actividad fue una diferente de Standy By debe aparecer el motivo de parada de Finalizado también.
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
                                    mvtoEquipo_selectRazonFinalzación.setModel(model);

                                    //cargamos los motivos de parada según la consulta
                                    try{
                                        if(listadoMotivoParada_mvtoEquipo !=null){
                                            int contadorX=0;
                                            for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                                if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                                      mvtoEquipo_selectRazonFinalzación.setSelectedIndex(contadorX);  
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
                    mvtoEquipo_selectRazonFinalzación.removeAllItems();   
                    titulo30.show(false);
                    mvtoEquipo_selectRazonFinalzación.show(false); 
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo cargar los datos","Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_VisualizarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() == 2) {
            int fila1;
            try{
                fila1=tabla.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ningun registro");
                }
                else{
                    int posicion = paginadorDeTabla.getPosicionador();
                    if(posicion != -1){
                        fila1 = fila1 +posicion;
                    }
                    mvtoEquipo = listado.get(fila1);
                    InternaFrame_VisualizarMvtoEquipo.show(true);

                    //Limpiamos campos de la Interfaz
                    mvtoEquipo_codigo.setText("");
                    mvtoEquipo_fechaMvto.setText("");
                    mvtoEquipo_FechaInicioActividad.setText("");
                    mvtoEquipo_FechaFinalActividad.setText("");
                    mvtoEquipo_Cliente_Código.setText("");
                    mvtoEquipo_Cliente_Nombre.setText("");
                    mvtoEquipo_articulo.setText("");
                    mvtoEquipo_motonave.setText("");
                    mvtoEquipo_CentroOperacion.setText("");
                    mvtoEquipo_Subcentro.setText("");
                    mvtoEquipo_AuxilarCentroOperacionOrigen.setText("");
                    mvtoEquipo_laborRealizada.setText("");
                    mvtoEquipo_AuxilarCentroOperacionDestino.setText("");
                    mvtoEquipo_Equipo_codigo.setText("");
                    mvtoEquipo_Equipo_tipo.setText("");
                    mvtoEquipo_Equipo_marca.setText("");
                    mvtoEquipo_Equipo_descripción.setText("");
                    mvtoEquipo_Equipo_proveedor.setText("");
                    mvtoEquipo_Equipo_pertenencia.setText("");
                    mvtoEquipo_Minutos.setText("");
                    mvtoEquipo_CantidadHoras.setText("");
                    mvtoEquipo_ActividadFinalizada.setText("");
                    mvtoEquipo_MotivoFinalización.setText("");
                    mvtoEquipo_estado.setText("");
                    mvtoEquipo_recobro.setText("");
                    mvtoEquipo_UsuarioRegistro_Código.setText("");
                    mvtoEquipo_UsuarioRegistro_Nombre.setText("");
                    mvtoEquipo_UsuarioRegistro_Correo.setText("");
                    mvtoEquipo_Observación.setText("");
                    label_clientes.setText("");
                    label_articulo.setText("");
                    label_motonave.setText("");
                    mvtoEquipo_numOrden.setText("");
                    mvtoEquipo_ObservaciónEditar.setText("");
                    textArea_MvtoEquipo_razonModificacion.setText("");



        //cargamos los campos de la Interfaz
                    mvtoEquipo_codigo.setText(mvtoEquipo.getCodigo());
                    mvtoEquipo_fechaMvto.setText(mvtoEquipo.getFechaRegistro());
                    mvtoEquipo_FechaInicioActividad.setText(mvtoEquipo.getFechaHoraInicio());
                    mvtoEquipo_FechaFinalActividad.setText(mvtoEquipo.getFechaHoraFin());
                    mvtoEquipo_Cliente_Código.setText(mvtoEquipo.getCliente().getCodigo());
                    mvtoEquipo_Cliente_Nombre.setText(mvtoEquipo.getCliente().getDescripcion());
                    mvtoEquipo_articulo.setText(mvtoEquipo.getArticulo().getDescripcion());
                    mvtoEquipo_motonave.setText(mvtoEquipo.getMotonave().getDescripcion());
                    mvtoEquipo_CentroOperacion.setText(mvtoEquipo.getCentroOperacion().getDescripcion());
                    mvtoEquipo_Subcentro.setText(mvtoEquipo.getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion());
                    mvtoEquipo_AuxilarCentroOperacionOrigen.setText(mvtoEquipo.getCentroCostoAuxiliar().getDescripcion());
                    mvtoEquipo_laborRealizada.setText(mvtoEquipo.getLaborRealizada().getDescripcion());
                    mvtoEquipo_AuxilarCentroOperacionDestino.setText(mvtoEquipo.getCentroCostoAuxiliarDestino().getDescripcion());
                    mvtoEquipo_Equipo_codigo.setText(mvtoEquipo.getAsignacionEquipo().getEquipo().getCodigo());
                    mvtoEquipo_Equipo_tipo.setText(mvtoEquipo.getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion());
                    mvtoEquipo_Equipo_marca.setText(mvtoEquipo.getAsignacionEquipo().getEquipo().getMarca());
                    mvtoEquipo_Equipo_descripción.setText(mvtoEquipo.getAsignacionEquipo().getEquipo().getDescripcion()+" "+mvtoEquipo.getAsignacionEquipo().getEquipo().getModelo());
                    mvtoEquipo_Equipo_proveedor.setText(mvtoEquipo.getProveedorEquipo().getDescripcion());
                    mvtoEquipo_Equipo_pertenencia.setText(mvtoEquipo.getAsignacionEquipo().getPertenencia().getDescripcion());
                    if(mvtoEquipo.getTotalMinutos()!=null){
                        try{
                            mvtoEquipo_Minutos.setText(mvtoEquipo.getTotalMinutos());
                            double des=Double.parseDouble(mvtoEquipo.getTotalMinutos());
                            double totalHoras = Double.parseDouble(""+(des/60));
                            DecimalFormat formato2 = new DecimalFormat("0.00");
                            mvtoEquipo_CantidadHoras.setText(""+formato2.format(totalHoras));
                        }catch(Exception e){
                            mvtoEquipo_Minutos.setText("");
                            mvtoEquipo_CantidadHoras.setText("");
                        }
                    }else{
                        mvtoEquipo_Minutos.setText("");
                            mvtoEquipo_CantidadHoras.setText("");
                    }
                    if(mvtoEquipo.getMotivoParadaEstado().equals("1")){
                        mvtoEquipo_ActividadFinalizada.setText("SI");
                    }else{
                        mvtoEquipo_ActividadFinalizada.setText("NO");
                    }
                    mvtoEquipo_MotivoFinalización.setText(mvtoEquipo.getMotivoParada().getDescripcion());
                    mvtoEquipo_estado.setText(mvtoEquipo.getEstado());
                    mvtoEquipo_recobro.setText(mvtoEquipo.getRecobro().getDescripcion());
                    mvtoEquipo_UsuarioRegistro_Código.setText(mvtoEquipo.getUsuarioQuieRegistra().getCodigo());
                    mvtoEquipo_UsuarioRegistro_Nombre.setText(mvtoEquipo.getUsuarioQuieRegistra().getNombres()+" "+mvtoEquipo.getUsuarioQuieRegistra().getApellidos());
                    mvtoEquipo_UsuarioRegistro_Correo.setText(mvtoEquipo.getUsuarioQuieRegistra().getCorreo());
                    mvtoEquipo_Observación.setText(mvtoEquipo.getObservacionMvtoEquipo());
                    mvtoEquipo_NumeroOrden.setText(mvtoEquipo.getNumeroOrden());

        //cargamos el cliente del mvtoEquipo registrado a la variable que almacenerá los cambios para el cliente
                    cliente=null;
                    cliente =mvtoEquipo.getCliente();
                    label_motonave.setText(mvtoEquipo.getMotonave().getDescripcion());
                    label_clientes.setText(mvtoEquipo.getCliente().getDescripcion());
                    label_articulo.setText(mvtoEquipo.getArticulo().getDescripcion());

                    mvtoEquipo_numOrden.setText(mvtoEquipo.getNumeroOrden());
                    mvtoEquipo_ObservaciónEditar.setText(mvtoEquipo.getObservacionMvtoEquipo());

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
                        JOptionPane.showMessageDialog(null, "No se pudo carga la fecha de Inicio de actividad","Error!!.", JOptionPane.ERROR_MESSAGE);
                    }

       //Cargamos la fecha de finalización de la actividad
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


                        }else{//No hay
                            listadoMotivoParada_mvtoEquipo=null;
                            mvtoEquipo_selectRazonFinalzación.removeAllItems();
                        }
                    }catch(Exception e){
                        System.out.println(""+mvtoEquipo.getFechaHoraFin());
                        JOptionPane.showMessageDialog(null, "No se pudo carga la fecha de finalización de actividad","Error!!.", JOptionPane.ERROR_MESSAGE);
                    }

        //cargamos el Centro de Operación según la consulta
                    try{
                        int contador=0;
                        for(CentroOperacion centroOperacion: listadoCentroOperacion_mvtoEquipo){
                            if(centroOperacion.getDescripcion().equals(mvtoEquipo.getCentroOperacion().getDescripcion())){
                                  select_MvtoEquipo_CO.setSelectedIndex(contador);  
                            }
                            contador++;
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    cambioEstado("Subcentro_Costo");
                    //cargamos el subcentro de costo según la consulta
                    try{
                        int contador=0;
                        for(CentroCostoSubCentro centroCostoSubCentro: listadoCentroCostoSubCentro_mvtoEquipo){
                            if(centroCostoSubCentro.getDescripcion().equals(mvtoEquipo.getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion())){
                                  select_MvtoEquipo_SubcentroCosto.setSelectedIndex(contador);  
                            }
                            contador++;
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
        //cargamos los auxiliares de Centro de costos según la consulta
                    try{
                        int contador=0;
                        for(CentroCostoAuxiliar centroCostoAuxiliar: listadoCentroCostoAuxiliar_mvtoEquipo){
                            if(centroCostoAuxiliar.getDescripcion().equals(mvtoEquipo.getCentroCostoAuxiliar().getDescripcion())){
                                  select_MvtoEquipo_CCAuxiliar.setSelectedIndex(contador);  
                            }
                            contador++;
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    //mvto_listEquipo.getMvtoEquipo().getLaborRealizada().getDescripcion()
        //cargamos las labores realizadas según la consulta
                    try{
                        int contador=0;
                        System.out.println("cargador fue ===========>"+mvtoEquipo.getLaborRealizada().getDescripcion());
                        for(LaborRealizada laborRealizada: listadoLaborRealizada_mvtoEquipo){
                            if(laborRealizada.getDescripcion().equals(mvtoEquipo.getLaborRealizada().getDescripcion())){
                                  select_MvtoEquipo_laborRealizada.setSelectedIndex(contador);  
                                   System.out.println("encontrado fue ===========>"+laborRealizada.getDescripcion());
                            }
                            contador++;
                            System.out.println("consultado fue ===========>"+laborRealizada.getDescripcion());
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }

        //cargamos los centros de costos auxiliares destino según la consulta
                    try{
                        if(listadoCentroCostoAuxiliarDestino_mvtoEquipo !=null){
                            int contador=0;
                            for(CentroCostoAuxiliar centroCostoAuxiliar: listadoCentroCostoAuxiliarDestino_mvtoEquipo){
                                if(centroCostoAuxiliar.getDescripcion().equals(mvtoEquipo.getCentroCostoAuxiliarDestino().getDescripcion())){
                                      select_MvtoEquipo_CCAuxiliarDestino.setSelectedIndex(contador);  
                                }
                                contador++;
                            }
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    //cargamos los recobro según la consulta
                    try{
                        int contador=0;
                        for(Recobro recobro: listadoRecobros_mvtoEquipo){
                            if(recobro.getDescripcion().equals(mvtoEquipo.getRecobro().getDescripcion())){
                                  select_MvtoEquipo_recobro.setSelectedIndex(contador);  
                            }
                            contador++;
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
        //Cargamos los equipos disponible en el rango de fecha.
                    listadoTipoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarTipoEquiposProgramados(mvtoEquipo.getFechaRegistro());
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
                        listadoMarcaEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarMarcaEquiposProgramados(mvtoEquipo.getFechaRegistro(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion());
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
                        listadoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarAsignacionEquipos_Por_TipoEquipo_MarcaEquipo(mvtoEquipo.getFechaRegistro(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion(),listadoMarcaEquipos_mvtoEquipo.get(select_MvtoEquipo_marcaEquipo.getSelectedIndex()));
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
                        if(Objeto.getDescripcion().equals(mvtoEquipo.getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion())){
                            select_MvtoEquipo_tipoEquipo.setSelectedIndex(contador);
                        }
                        contador++;
                    }
                    contador=0;
                    for(String Objeto : listadoMarcaEquipos_mvtoEquipo){ 
                        if(Objeto.equals(mvtoEquipo.getAsignacionEquipo().getEquipo().getMarca())){
                            select_MvtoEquipo_marcaEquipo.setSelectedIndex(contador);
                        }
                        contador++;
                    }
                    contador=0;
                    for(AsignacionEquipo Objeto : listadoEquipos_mvtoEquipo){ 
                        if(Objeto.getCodigo().equals(mvtoEquipo.getAsignacionEquipo().getCodigo())){
                            select_MvtoEquipo_Equipo.setSelectedIndex(contador);
                        }
                        contador++;
                    }
                    if(mvtoEquipo.getFechaHoraFin() != null){
                        if(mvtoEquipo.getLaborRealizada().getDescripcion().equalsIgnoreCase("STAND BY")){//Fue un MvtoEquipo donde la labor fue Stand BY, por tal motivo no debe aparecer el motivo de parada de finalizado.
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
                                        mvtoEquipo_selectRazonFinalzación.setModel(model);


                                        //cargamos los motivos de parada según la consulta
                                        try{
                                            if(listadoMotivoParada_mvtoEquipo !=null){
                                                int contadorX=0;
                                                for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                                    if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                                          mvtoEquipo_selectRazonFinalzación.setSelectedIndex(contadorX);  
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
                        }else{//Como la actividad fue una diferente de Standy By debe aparecer el motivo de parada de Finalizado también.
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
                                        mvtoEquipo_selectRazonFinalzación.setModel(model);

                                        //cargamos los motivos de parada según la consulta
                                        try{
                                            if(listadoMotivoParada_mvtoEquipo !=null){
                                                int contadorX=0;
                                                for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                                    if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                                          mvtoEquipo_selectRazonFinalzación.setSelectedIndex(contadorX);  
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
                        mvtoEquipo_selectRazonFinalzación.removeAllItems();   
                        titulo30.show(false);
                        mvtoEquipo_selectRazonFinalzación.show(false); 
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "No se pudo cargar los datos","Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        if(listado != null){
            //ArrayList<String> colmnas={"Hola", "Hola", "Hola"});
        JFileChooser selecArchivo= new JFileChooser();
        File archivo;
        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
        if(selecArchivo.showDialog(null, "Exportar") ==JFileChooser.APPROVE_OPTION){
            archivo=selecArchivo.getSelectedFile();
            if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
                //JOptionPane.showMessageDialog(null, Exportar(archivo));
                Workbook wb;
                String respuesta="No se realizó con exito la exportacion";
                listado.size();

                //int numFila=tabla.getRowCount(), numColumna=tabla.getColumnCount();
                int numFila=listado.size();
                int numColumna=encabezadoTabla.size();
                if(archivo.getName().endsWith("xls")){
                    wb = new HSSFWorkbook();
                }else{
                    wb= new XSSFWorkbook();
                }
                Sheet hoja= wb.createSheet("ReporteEquipo");
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
                                    case "Código":{
                                        data=""+listado.get(i).getCodigo();
                                        break;
                                    }
                                    case "MES":{
                                        data=""+getMes(listado.get(i).getMes());
                                        break;
                                    }
                                    case "Fecha_Registro":{
                                        data=""+listado.get(i).getFechaRegistro();
                                        break;
                                    }
                                    case "ProveedorEquipo_NIT":{
                                        data=""+listado.get(i).getProveedorEquipo().getNit();
                                        break;
                                    }
                                    case "ProveedorEquipo_Nombre":{
                                        data=""+listado.get(i).getProveedorEquipo().getDescripcion();
                                        break;
                                    }
                                    case "Número_Orden":{
                                        data=""+listado.get(i).getNumeroOrden();
                                        break;
                                    }
                                    case "Centro_Operación":{
                                        data=""+listado.get(i).getCentroOperacion().getDescripcion();
                                        break;
                                    }
                                    case "Subcentro_Costo":{
                                        data=""+listado.get(i).getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                                        break;
                                    }
                                    case "Centro_Costo_Auxiliar_Origen":{
                                        data=""+listado.get(i).getCentroCostoAuxiliar().getDescripcion();
                                        break;
                                    }
                                    case "Centro_Costo_Auxiliar_Destino":{
                                        data=""+listado.get(i).getCentroCostoAuxiliarDestino().getDescripcion();
                                        break;
                                    }
                                    case "Centro_Costo":{
                                        data=""+listado.get(i).getCentroCosto().getDescripción();
                                        break;
                                    }
                                    case "Centro_Costo_Mayor":{
                                        data=""+listado.get(i).getCentroCostoMayor().getDescripcion();
                                        break;
                                    }
                                    case "Labor_Realizada":{
                                        data=""+listado.get(i).getLaborRealizada().getDescripcion();
                                        break;
                                    }
                                    case "Cliente_Código":{
                                        data=""+listado.get(i).getCliente().getCodigo();
                                        break;
                                    }
                                    case "Cliente_Nombre":{
                                        data=""+listado.get(i).getCliente().getDescripcion();
                                        break;
                                    }
                                    case "Artículo_Código":{
                                        data=""+listado.get(i).getArticulo().getCodigo();
                                        break;
                                    }
                                    case "Artículo_Nombre":{
                                        data=""+listado.get(i).getArticulo().getDescripcion();
                                        break;
                                    }
                                    case "Motonave_Código":{
                                        data=""+listado.get(i).getMotonave().getCodigo();
                                        break;
                                    }
                                    case "Motonave_Descripción":{
                                        data=""+listado.get(i).getMotonave().getDescripcion();
                                        break;
                                    }
                                    case "FechaInicio_Actividad":{
                                        data=""+listado.get(i).getFechaHoraInicio();
                                        break;
                                    }
                                    case "FechaFinalización_Actividad":{
                                        data=""+listado.get(i).getFechaHoraFin();
                                        break;
                                    }
                                    case "Duración_Actividad(Minutos)":{
                                        data=""+listado.get(i).getTotalMinutos();
                                        break;
                                    }
                                    case "Valor_Hora":{
                                        data=""+listado.get(i).getValorHora();
                                        break;
                                    }
                                    case "Costo_Total":{
                                        data=""+listado.get(i).getCostoTotal();
                                        break;
                                    }
                                    case "Recobro":{
                                        data=""+listado.get(i).getRecobro().getDescripcion();
                                        break;
                                    }
                                    case "UsuarioQuienRegistra_Código":{
                                        data=""+listado.get(i).getUsuarioQuieRegistra().getCodigo();
                                        break;
                                    }
                                    case "UsuarioQuienRegistra_Nombre":{
                                        data=""+listado.get(i).getUsuarioQuieRegistra().getNombres()+" "+listado.get(i).getUsuarioQuieRegistra().getApellidos();
                                        break;
                                    }
                                    case "UsuarioQuienAutoriza_Código":{
                                        data=""+listado.get(i).getUsuarioAutorizaRecobro().getCodigo();
                                        break;
                                    }
                                    case "UsuarioQuienAutoriza_Nombre":{
                                        data=""+listado.get(i).getUsuarioAutorizaRecobro().getNombres()+" "+listado.get(i).getUsuarioAutorizaRecobro().getApellidos();
                                        break;
                                    }
                                    case "Autorización_Recobro":{
                                        data=""+listado.get(i).getAutorizacionRecobro().getDescripcion();
                                        break;
                                    }
                                    case "Observación_Autorización_Recobro":{
                                        data=""+listado.get(i).getObservacionAutorizacion();
                                        break;
                                    }
                                    case "Inactividad":{
                                        data=""+listado.get(i).getInactividad();
                                        break;
                                    }
                                    case "Causal_Inactividad":{
                                        data=""+listado.get(i).getCausaInactividad().getDescripcion();
                                        break;
                                    }
                                    case "UsuarioQuienInactiva_Código":{
                                        data=""+listado.get(i).getUsuarioInactividad().getCodigo();
                                        break;
                                    }
                                    case "UsuarioQuienInactiva_Nombre":{
                                        data=""+listado.get(i).getUsuarioInactividad().getNombres();
                                        break;
                                    }
                                    case "Motivo_Parada":{
                                        data=""+listado.get(i).getMotivoParada().getDescripcion();
                                        break;
                                    }
                                    case "MvtoEquipo_Observación":{
                                        data=""+listado.get(i).getObservacionMvtoEquipo();
                                        break;
                                    }
                                    case "Estado":{
                                        data=""+listado.get(i).getEstado();
                                        break;
                                    }
                                    case "Desde_Carbón":{
                                        data=""+listado.get(i).getDesdeCarbon();
                                        break;
                                    }
                                    case "Equipo_Código":{
                                        data=""+listado.get(i).getAsignacionEquipo().getEquipo().getCodigo();
                                        break;
                                    }
                                    case "Equipo_Tipo":{
                                        data=""+listado.get(i).getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion();
                                        break;
                                    }
                                    case "Equipo_Marca":{
                                        data=""+listado.get(i).getAsignacionEquipo().getEquipo().getMarca();
                                        break;
                                    }
                                    case "Equipo_Modelo":{
                                        data=""+listado.get(i).getAsignacionEquipo().getEquipo().getModelo();
                                        break;
                                    }
                                    case "Equipo_Descripción":{
                                        data=""+listado.get(i).getAsignacionEquipo().getEquipo().getDescripcion();
                                        break;
                                    }
                                    case "Equipo_Pertenencia":{
                                        data=""+listado.get(i).getAsignacionEquipo().getPertenencia().getDescripcion();
                                        break;
                                    }
                                    case "Asignación_Código":{
                                        data=""+listado.get(i).getAsignacionEquipo().getCodigo();
                                        break;
                                    }
                                    case "Asignación_FechaRegistro":{
                                        data=""+listado.get(i).getAsignacionEquipo().getFechaRegistro();
                                        break;
                                    }
                                    case "Asignación_FechaInicioActividad":{
                                        data=""+listado.get(i).getAsignacionEquipo().getFechaHoraInicio();
                                        break;
                                    }
                                    case "Asignación_FechaFinalizaciónActividad":{
                                        data=""+listado.get(i).getAsignacionEquipo().getFechaHoraFin();
                                        break;
                                    }
                                    case "Asignación_CantidadMinutosProgramados":{
                                        data=""+listado.get(i).getAsignacionEquipo().getCantidadMinutosProgramados();
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
                                            //cell = row.createCell(1);
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

                                            /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                            Date fechaM = dateFormat.parse(tabla.getValueAt(i, j).toString());
                                            celda.setCellValue(fechaM);*/
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
    }//GEN-LAST:event_jLabel4MouseClicked

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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        selectorCampoPorDefecto();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void selectMvtoEquipo_cantidadMinutosDescargueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_cantidadMinutosDescargueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_cantidadMinutosDescargueActionPerformed

    private void selectAsignacion_equipoTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAsignacion_equipoTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAsignacion_equipoTipoActionPerformed

    private void selectMvtoEquipo_motonaveDescripciónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_motonaveDescripciónActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_motonaveDescripciónActionPerformed

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
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_HoraInicialActionPerformed

    private void select_MvtoEquipo_MinutosInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_MinutosInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_MinutosInicialActionPerformed

    private void select_MvtoEquipo_MinutosFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_MinutosFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_MinutosFinalActionPerformed

    private void select_MvtoEquipo_HoraFinalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_HoraFinalItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_HoraFinalItemStateChanged

    private void select_MvtoEquipo_HoraFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_HoraFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_HoraFinalActionPerformed

    private void fechaFinActividad_MvtoEquipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFinActividad_MvtoEquipoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFinActividad_MvtoEquipoMouseClicked

    private void fechaFinActividad_MvtoEquipoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFinActividad_MvtoEquipoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFinActividad_MvtoEquipoMouseEntered

    private void check_MvtoEquipo_FinalizacionActividadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_check_MvtoEquipo_FinalizacionActividadItemStateChanged
        //validamos si la fecha de finalización fue marcada
        if(check_MvtoEquipo_FinalizacionActividad.isSelected()){ //Fue marcada cambiar la fecha de finalización
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
        //Borramos la lista y los select de motivo de Finalización 
        listadoMotivoParada_mvtoEquipo=null;
        mvtoEquipo_selectRazonFinalzación.removeAllItems();
        
        //validamos el estado inicial de la fecha de finalización
        if(mvtoEquipo.getFechaHoraFin() == null){
            //Validamos si la fecha de finalización fue seleccionada
            if(check_MvtoEquipo_FinalizacionActividad.isSelected()){//la fecha de finalización de la actividad si fue seleccionada
                titulo30.show(true);
                mvtoEquipo_selectRazonFinalzación.show(true);
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
                        mvtoEquipo_selectRazonFinalzación.setModel(model);
                        
                        //cargamos los motivos de parada según la consulta
                        try{
                            if(listadoMotivoParada_mvtoEquipo !=null){
                                int contadorX=0;
                                for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                    if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                          mvtoEquipo_selectRazonFinalzación.setSelectedIndex(contadorX);  
                                    }
                                    contadorX++;
                                }
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }else{
                        listadoMotivoParada_mvtoEquipo=null;
                        mvtoEquipo_selectRazonFinalzación.removeAllItems(); 
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
                            mvtoEquipo_selectRazonFinalzación.setModel(model);

                            //cargamos los motivos de parada según la consulta
                            try{
                                if(listadoMotivoParada_mvtoEquipo !=null){
                                    int contadorX=0;
                                    for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                        if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                              mvtoEquipo_selectRazonFinalzación.setSelectedIndex(contadorX);  
                                        }
                                        contadorX++;
                                    }
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }else{
                            listadoMotivoParada_mvtoEquipo=null;
                            mvtoEquipo_selectRazonFinalzación.removeAllItems(); 
                        }   
                    } catch (SQLException ex) {
                        Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }
              
            }else{//la fecha de finalización de la actividad no fue seleccionada
                listadoMotivoParada_mvtoEquipo=null;
                mvtoEquipo_selectRazonFinalzación.removeAllItems();   
                titulo30.show(false);
                mvtoEquipo_selectRazonFinalzación.show(false);
            }   
        }else{
            if(mvtoEquipo.getFechaHoraFin() != null){
                //Validamos si la fecha de finalización fue seleccionada
                if(check_MvtoEquipo_FinalizacionActividad.isSelected()){//la fecha de finalización de la actividad si fue seleccionada
                    titulo30.show(true);
                    mvtoEquipo_selectRazonFinalzación.show(true);
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
                            mvtoEquipo_selectRazonFinalzación.setModel(model);


                            //cargamos los motivos de parada según la consulta
                            try{
                                if(listadoMotivoParada_mvtoEquipo !=null){
                                    int contadorX=0;
                                    for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                        if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                              mvtoEquipo_selectRazonFinalzación.setSelectedIndex(contadorX);  
                                        }
                                        contadorX++;
                                    }
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }else{
                            listadoMotivoParada_mvtoEquipo=null;
                            mvtoEquipo_selectRazonFinalzación.removeAllItems(); 
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
                                mvtoEquipo_selectRazonFinalzación.setModel(model);

                                //cargamos los motivos de parada según la consulta
                                try{
                                    if(listadoMotivoParada_mvtoEquipo !=null){
                                        int contadorX=0;
                                        for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                            if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                                  mvtoEquipo_selectRazonFinalzación.setSelectedIndex(contadorX);  
                                            }
                                            contadorX++;
                                        }
                                    }
                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }else{
                                listadoMotivoParada_mvtoEquipo=null;
                                mvtoEquipo_selectRazonFinalzación.removeAllItems(); 
                            }   
                        } catch (SQLException ex) {
                            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                    }
                }else{//la fecha de finalización de la actividad no fue seleccionada
                    titulo30.show(true);
                    mvtoEquipo_selectRazonFinalzación.show(true);
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
                            mvtoEquipo_selectRazonFinalzación.setModel(model);


                            //cargamos los motivos de parada según la consulta
                            try{
                                if(listadoMotivoParada_mvtoEquipo !=null){
                                    int contadorX=0;
                                    for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                        if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                              mvtoEquipo_selectRazonFinalzación.setSelectedIndex(contadorX);  
                                        }
                                        contadorX++;
                                    }
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }else{
                            listadoMotivoParada_mvtoEquipo=null;
                            mvtoEquipo_selectRazonFinalzación.removeAllItems(); 
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
                                mvtoEquipo_selectRazonFinalzación.setModel(model);

                                //cargamos los motivos de parada según la consulta
                                try{
                                    if(listadoMotivoParada_mvtoEquipo !=null){
                                        int contadorX=0;
                                        for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                            if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                                  mvtoEquipo_selectRazonFinalzación.setSelectedIndex(contadorX);  
                                            }
                                            contadorX++;
                                        }
                                    }
                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }else{
                                listadoMotivoParada_mvtoEquipo=null;
                                mvtoEquipo_selectRazonFinalzación.removeAllItems(); 
                            }   
                        } catch (SQLException ex) {
                            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                    }
                }
            }
        }
    }//GEN-LAST:event_check_MvtoEquipo_FinalizacionActividadItemStateChanged

    private void select_MvtoEquipo_SubcentroCostoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_SubcentroCostoItemStateChanged
        if(listadoCentroCostoSubCentro_mvtoEquipo != null){
            try {
                listadoCentroCostoAuxiliar_mvtoEquipo = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro_mvtoEquipo.get(select_MvtoEquipo_SubcentroCosto.getSelectedIndex()).getCodigo());
                if(listadoCentroCostoAuxiliar_mvtoEquipo != null){
                    String datosObjeto[] = new String[listadoCentroCostoAuxiliar_mvtoEquipo.size()];
                    int contador = 0;
                    for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliar_mvtoEquipo) {
                        datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_MvtoEquipo_CCAuxiliar.setModel(model);
                }else{
                    listadoCentroCostoAuxiliar_mvtoEquipo=null;
                    select_MvtoEquipo_CCAuxiliar.removeAllItems();
                } 
                try {
                    listadoLaborRealizada_mvtoEquipo=new ControlDB_LaborRealizada(tipoConexion).buscarPorSubcentroCosto(listadoCentroCostoSubCentro_mvtoEquipo.get(select_MvtoEquipo_SubcentroCosto.getSelectedIndex()));
                    if(listadoLaborRealizada_mvtoEquipo != null){
                        String datosObjeto[]= new String[listadoLaborRealizada_mvtoEquipo.size()];
                        int contador=0;
                        for(LaborRealizada Objeto : listadoLaborRealizada_mvtoEquipo){ 
                            datosObjeto[contador]=Objeto.getDescripcion();
                            contador++;
                        }
                        final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                        select_MvtoEquipo_laborRealizada.setModel(model);
                    }else{
                        listadoLaborRealizada_mvtoEquipo=null;
                        select_MvtoEquipo_laborRealizada.removeAllItems();
                    } 
                } catch (SQLException ex) {
                    Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }catch (SQLException e){ 
            }
        }else{
            listadoCentroCostoSubCentro_mvtoEquipo=null;
            select_MvtoEquipo_SubcentroCosto.removeAllItems();
            
            listadoLaborRealizada_mvtoEquipo=null;
            select_MvtoEquipo_laborRealizada.removeAllItems();
            
            listadoCentroCostoAuxiliar_mvtoEquipo=null;
            select_MvtoEquipo_CCAuxiliar.removeAllItems();
            
            listadoCentroCostoAuxiliarDestino_mvtoEquipo=null;
            select_MvtoEquipo_CCAuxiliarDestino.removeAllItems();
        }
        
        //validamos si la labor Realiza seleccionada tiene bodega Destino
        if(listadoLaborRealizada_mvtoEquipo != null){
            if(listadoLaborRealizada_mvtoEquipo.get(select_MvtoEquipo_laborRealizada.getSelectedIndex()).getBodegaDestino().equals("1")){//Fue seleccionada una labor Realizada que tiene marcada Bodega destino por tal motivo cargalos las Bodega destino
               // listadoCentroCostoAuxiliarDestino_mvtoEquipo
                if(listadoCentroCostoSubCentro_mvtoEquipo != null){
                    try {
                        listadoCentroCostoAuxiliarDestino_mvtoEquipo = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro_mvtoEquipo.get(select_MvtoEquipo_SubcentroCosto.getSelectedIndex()).getCodigo());
                        if(listadoCentroCostoAuxiliarDestino_mvtoEquipo != null){
                            String datosObjeto[] = new String[listadoCentroCostoAuxiliarDestino_mvtoEquipo.size()];
                            int contador = 0;
                            for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliarDestino_mvtoEquipo) {
                                datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                                contador++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            select_MvtoEquipo_CCAuxiliarDestino.setModel(model);
                        }else{
                            listadoCentroCostoAuxiliarDestino_mvtoEquipo=null;
                            select_MvtoEquipo_CCAuxiliarDestino.removeAllItems();
                        } 
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }            
            }else{
                listadoCentroCostoAuxiliarDestino_mvtoEquipo=null;
                select_MvtoEquipo_CCAuxiliarDestino.removeAllItems();
            }
        }else{
            listadoLaborRealizada_mvtoEquipo=null;
            select_MvtoEquipo_laborRealizada.removeAllItems();
            listadoCentroCostoAuxiliarDestino_mvtoEquipo=null;
            select_MvtoEquipo_CCAuxiliarDestino.removeAllItems();
        }
    }//GEN-LAST:event_select_MvtoEquipo_SubcentroCostoItemStateChanged

    private void select_MvtoEquipo_laborRealizadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_laborRealizadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_laborRealizadaActionPerformed

    private void select_MvtoEquipo_recobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_recobroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_recobroActionPerformed

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

    private void registrar_MvtoEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrar_MvtoEquipoActionPerformed
        //Asignamos un MvtoEquipo temporal para validar campos
        if(!textArea_MvtoEquipo_razonModificacion.getText().equals("")){
            boolean validarCampos= true;
            String scriptDB="";
            String scriptAuditoria="";
            try{
                // MvtoEquipo mvtoEquipoTemp = mvtoEquipo;
                //validamos las fechas si son iguales
                String fechaInicio_Actividad="";
                String fechaFinalizacion_Actividad="";

                /**VALIDAMOS LAS FECHAS ##############################################################**/
                if(check_MvtoEquipo_InicioActividad.isSelected() && check_MvtoEquipo_FinalizacionActividad.isSelected()){//Seleccionó modificar la fecha de Inicio Actividad y la fecha de Finalización de actividad
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
                        try{        //Almacenamos la fecha de finalización de actividad   
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
                                JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser mayor a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                            }else{
                                if(resultDosFechas ==0){
                                    validarCampos=false;
                                    JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser Igual a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                                }else{
                                    validarCampos=true;
                                    if(scriptDB.equals("")){
                                        scriptDB +="[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"' , [me_fecha_hora_fin]='"+fechaFinalizacion_Actividad+"' ,[me_total_minutos]=(SELECT DATEDIFF(minute,'"+fechaInicio_Actividad+"', '"+fechaFinalizacion_Actividad+"'))\n";
                                        scriptAuditoria +="[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"' , [me_fecha_hora_fin]='"+fechaFinalizacion_Actividad+"'\n";
                                    }else{
                                        scriptDB +=",[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"' , [me_fecha_hora_fin]='"+fechaFinalizacion_Actividad+"' ,[me_total_minutos]=(SELECT DATEDIFF(minute,'"+fechaInicio_Actividad+"', '"+fechaFinalizacion_Actividad+"'))\n";
                                        scriptAuditoria +=",[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"' , [me_fecha_hora_fin]='"+fechaFinalizacion_Actividad+"'\n";
                                    }
                                }
                            }
                            }catch(Exception e){
                            validarCampos=false;
                            JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Finalización","Advertencia",JOptionPane.ERROR_MESSAGE);
                        }
                    }catch(Exception e){
                        validarCampos=false;
                        JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Inicio","Advertencia",JOptionPane.ERROR_MESSAGE);
                    }                        
                }else{
                    if(check_MvtoEquipo_InicioActividad.isSelected()){ //Seleccionó modificar solo la fecha de inicio de actividad
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
                            if(mvtoEquipo.getFechaHoraFin() != null){//validamos que tenga una fecha de finalización para hacer la comparación 
                                try{
                                    String[] fechaA= mvtoEquipo.getFechaHoraFin().split(" ");
                                    String[] horaB= fechaA[1].split(":");
                                    String fechaFinalizacionTemp=fechaA[0]+" "+horaB[0]+":"+horaB[1]+":00.0";
                                    int resultDosFechas=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorEntreDosFechas("'"+fechaInicio_Actividad+"'","'"+fechaFinalizacionTemp+"'"));
                                    if(resultDosFechas < 0){
                                        validarCampos=false;
                                        JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser mayor a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                                    }else{
                                        if(resultDosFechas ==0){
                                            validarCampos=false;
                                            JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser Igual a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                                        }else{
                                            validarCampos=true;
                                            if(scriptDB.equals("")){
                                                scriptDB +="[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"' ,[me_total_minutos]=(SELECT DATEDIFF(minute,'"+fechaInicio_Actividad+"', '"+mvtoEquipo.getFechaHoraFin()+"'))\n";
                                                scriptAuditoria +="[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"'\n";
                                            }else{
                                                scriptDB +=",[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"'\n";
                                                scriptAuditoria +=",[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"'\n";
                                            }
                                        }
                                    }
                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }else{// La fecha de finalización es nula por tal motivo se almacena de manera directa
                                validarCampos=true;
                                if(scriptDB.equals("")){
                                    scriptDB +="[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"' \n";
                                    scriptAuditoria +="[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"'\n";
                                }else{
                                    scriptDB +=",[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"' \n";
                                    scriptAuditoria +=",[me_fecha_hora_inicio]='"+fechaInicio_Actividad+"'\n";
                                }

                            }
                        }catch(Exception e){
                            validarCampos=false;
                            JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Inicio","Advertencia",JOptionPane.ERROR_MESSAGE);
                        }   
                    }else{
                        if(check_MvtoEquipo_FinalizacionActividad.isSelected()){//Seleccionó modificar solo la fecha de finalización  de la actividad
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
                                if(mvtoEquipo.getFechaHoraInicio()!= null){//validamos que tenga una fecha de inicio para hacer la comparación 
                                    try{
                                        String[] fechaA= mvtoEquipo.getFechaHoraInicio().split(" ");
                                        String[] horaB= fechaA[1].split(":");
                                        String fechaInicioTemp=fechaA[0]+" "+horaB[0]+":"+horaB[1]+":00.0";
                                        int resultDosFechas=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorEntreDosFechas("'"+fechaInicioTemp+"'","'"+fechaFinalizacion_Actividad+"'"));
                                        if(resultDosFechas < 0){
                                            validarCampos=false;
                                            JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser mayor a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                                        }else{
                                            if(resultDosFechas ==0){
                                                validarCampos=false;
                                                JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser Igual a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                                            }else{
                                                validarCampos=true;
                                                if(scriptDB.equals("")){
                                                    scriptDB +="[me_fecha_hora_fin]='"+fechaFinalizacion_Actividad+"' ,[me_total_minutos]=(SELECT DATEDIFF(minute,'"+mvtoEquipo.getFechaHoraInicio()+"', '"+fechaFinalizacion_Actividad+"'))\n";
                                                    scriptAuditoria +="[me_fecha_hora_fin]='"+fechaFinalizacion_Actividad+"'\n";
                                                }else{
                                                    scriptDB +=",[me_fecha_hora_fin]='"+fechaFinalizacion_Actividad+"',[me_total_minutos]=(SELECT DATEDIFF(minute,'"+mvtoEquipo.getFechaHoraInicio()+"', '"+fechaFinalizacion_Actividad+"'))\n";
                                                    scriptAuditoria +=",[me_fecha_hora_fin]='"+fechaFinalizacion_Actividad+"'\n";
                                                }
                                            }
                                        }
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }
                                }else{// No habrá caso donde se tenga fecha de finalización con fecha de inicio null
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

                if(validarCampos){// revisamos que el validador sea true
                    /**VALIDAMOS EL CLIENTE ##############################################################**/
                    if(checkCliente.isSelected()){//El usuario seleccionó un cliente para ser modificado
                        if(mvtoEquipo.getCliente() != null){// El moviento de equipo tiene un cliente ya cargado
                            if(!cliente.getCodigo().equals(mvtoEquipo.getCliente().getCodigo())){//el usuario cambio el cliente por otro
                                if(scriptDB.equals("")){
                                    scriptDB +="[me_cliente_cdgo]='"+cliente.getCodigo()+"'\n";
                                    scriptAuditoria +="[me_cliente_cdgo]='"+cliente.getCodigo()+"-"+cliente.getDescripcion()+"'\n";
                                }else{
                                    scriptDB +=",[me_cliente_cdgo]='"+cliente.getCodigo()+"'\n";
                                    scriptAuditoria +=",[me_cliente_cdgo]='"+cliente.getCodigo()+"-"+cliente.getDescripcion()+"'\n";                               
                                }
                            }else{//El usuario no cambio el cliente por tal movito no se registra dicho cambio

                            }
                        }
                    }
                    /**VALIDAMOS EL ARTÍCULO ##############################################################**/
                    if(checkArticulo.isSelected()){//El usuario seleccionó un articulo para ser modificado
                        if(mvtoEquipo.getArticulo()!= null){// El moviento de equipo tiene un articulo ya cargado
                            if(!articulo.getCodigo().equals(mvtoEquipo.getArticulo().getCodigo())){//el usuario cambio el articulo por otro
                                if(scriptDB.equals("")){
                                    scriptDB +="[me_articulo_cdgo]='"+articulo.getCodigo()+"'\n";
                                    scriptAuditoria +="[me_articulo_cdgo]='"+articulo.getCodigo()+"-"+articulo.getDescripcion()+"'\n";
                                }else{
                                    scriptDB +=",[me_articulo_cdgo]='"+articulo.getCodigo()+"'\n";
                                    scriptAuditoria +=",[me_articulo_cdgo]='"+articulo.getCodigo()+"-"+articulo.getDescripcion()+"'\n";
                                }
                            }else{//El usuario no cambio el articulo por tal movito no se registra dicho cambio

                            }
                        }
                    }
                    /**VALIDAMOS LA MOTONAVE ##############################################################**/
                    if(checkMotonave.isSelected()){//El usuario seleccionó una motonave para ser modificada
                        if(mvtoEquipo.getMotonave()!= null){// El moviento de equipo tiene una motonave ya cargada
                            if(!motonave.getCodigo().equals(mvtoEquipo.getMotonave().getCodigo())){//el usuario cambio la motonave por otra
                                if(scriptDB.equals("")){
                                    scriptDB +="[me_motonave_cdgo]='"+motonave.getCodigo()+"'\n";
                                    scriptAuditoria +="[me_motonave_cdgo]='"+motonave.getCodigo()+"-"+motonave.getDescripcion()+"'\n";
                                }else{
                                    scriptDB +=",[me_motonave_cdgo]='"+motonave.getCodigo()+"'\n";
                                    scriptAuditoria +=",[me_motonave_cdgo]='"+motonave.getCodigo()+"-"+motonave.getDescripcion()+"'\n";
                                }
                            }else{//El usuario no cambio la motonave por tal movito no se registra dicho cambio

                            }
                        }
                    }
                }
                /**VALIDAMOS EL CENTRO DE OPERACIÓN ##############################################################**/
                if(validarCampos){// revisamos que el validador sea true
                    if(listadoCentroOperacion_mvtoEquipo != null){//Hay al menos un Centro de Operación Cargado en la interfaz
                        if(!(listadoCentroOperacion_mvtoEquipo.get(select_MvtoEquipo_CO.getSelectedIndex()).getCodigo() == mvtoEquipo.getCentroOperacion().getCodigo())){// El centro de operación que seleccionó es diferente al registrado en el movimiento de Equipo, por tal motivo se registra dicho cambio
                            if(scriptDB.equals("")){
                                scriptDB +="[me_cntro_oper_cdgo]="+listadoCentroOperacion_mvtoEquipo.get(select_MvtoEquipo_CO.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria +="[me_cntro_oper_cdgo]="+listadoCentroOperacion_mvtoEquipo.get(select_MvtoEquipo_CO.getSelectedIndex()).getCodigo()+"-"+listadoCentroOperacion_mvtoEquipo.get(select_MvtoEquipo_CO.getSelectedIndex()).getDescripcion()+"\n";
                            }else{
                                scriptDB +=",[me_cntro_oper_cdgo]="+listadoCentroOperacion_mvtoEquipo.get(select_MvtoEquipo_CO.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria +=",[me_cntro_oper_cdgo]="+listadoCentroOperacion_mvtoEquipo.get(select_MvtoEquipo_CO.getSelectedIndex()).getCodigo()+"-"+listadoCentroOperacion_mvtoEquipo.get(select_MvtoEquipo_CO.getSelectedIndex()).getDescripcion()+"\n";
                            }
                        }
                    }else{
                        validarCampos=false;
                        JOptionPane.showMessageDialog(null,"Error!!.. no se encuentra Centro de Operación activos en el sistema, Consulte con el administrador del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                    }
                }
                /**VALIDAMOS EL AUXILIAR DE CENTRO DE COSTO##############################################################**/
                if(validarCampos){// revisamos que el validador sea true
                    if(listadoCentroCostoAuxiliar_mvtoEquipo != null){//Hay al menos un centro de costo auxiliar cargado en la interfaz
                        if(!(listadoCentroCostoAuxiliar_mvtoEquipo.get(select_MvtoEquipo_CCAuxiliar.getSelectedIndex()).getCodigo() == mvtoEquipo.getCentroCostoAuxiliar().getCodigo())){// El centro de costo auxiliar que seleccionó es diferente al registrado en el movimiento de Equipo, por tal motivo se registra dicho cambio
                            if(scriptDB.equals("")){
                                scriptDB +="[me_cntro_cost_auxiliar_cdgo]="+listadoCentroCostoAuxiliar_mvtoEquipo.get(select_MvtoEquipo_CCAuxiliar.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria +="[me_cntro_cost_auxiliar_cdgo]="+listadoCentroCostoAuxiliar_mvtoEquipo.get(select_MvtoEquipo_CCAuxiliar.getSelectedIndex()).getCodigo()+"-"+listadoCentroCostoAuxiliar_mvtoEquipo.get(select_MvtoEquipo_CCAuxiliar.getSelectedIndex()).getDescripcion()+"\n";
                            }else{
                                scriptDB +=",[me_cntro_cost_auxiliar_cdgo]="+listadoCentroCostoAuxiliar_mvtoEquipo.get(select_MvtoEquipo_CCAuxiliar.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria +=",[me_cntro_cost_auxiliar_cdgo]="+listadoCentroCostoAuxiliar_mvtoEquipo.get(select_MvtoEquipo_CCAuxiliar.getSelectedIndex()).getCodigo()+"-"+listadoCentroCostoAuxiliar_mvtoEquipo.get(select_MvtoEquipo_CCAuxiliar.getSelectedIndex()).getDescripcion()+"\n";
                            }
                        }
                    }else{
                        validarCampos=false;
                        JOptionPane.showMessageDialog(null,"Error!!.. no se encuentran un Centro de costo auxiliar activo en el sistema, Consulte con el administrador del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                    }
                }
                /**VALIDAMOS LA LABOR REALIZADA ##############################################################**/
                if(validarCampos){// revisamos que el validador sea true
                    if(listadoLaborRealizada_mvtoEquipo != null){//Hay al menos un Subcentro de costo cargado en la interfaz
                        if(!(listadoLaborRealizada_mvtoEquipo.get(select_MvtoEquipo_laborRealizada.getSelectedIndex()).getCodigo().equals(mvtoEquipo.getLaborRealizada().getCodigo()))){// La labor realizada que seleccionó es diferente a la registrada en el movimiento de Equipo, por tal motivo se registra dicho cambio
                            if(scriptDB.equals("")){
                                scriptDB +="[me_labor_realizada_cdgo]='"+listadoLaborRealizada_mvtoEquipo.get(select_MvtoEquipo_laborRealizada.getSelectedIndex()).getCodigo()+"'\n";
                                scriptAuditoria +="[me_labor_realizada_cdgo]='"+listadoLaborRealizada_mvtoEquipo.get(select_MvtoEquipo_laborRealizada.getSelectedIndex()).getCodigo()+"-"+listadoLaborRealizada_mvtoEquipo.get(select_MvtoEquipo_laborRealizada.getSelectedIndex()).getDescripcion()+"'\n";
                            }else{
                                scriptDB +=",[me_labor_realizada_cdgo]='"+listadoLaborRealizada_mvtoEquipo.get(select_MvtoEquipo_laborRealizada.getSelectedIndex()).getCodigo()+"'\n";
                                scriptAuditoria +=",[me_labor_realizada_cdgo]='"+listadoLaborRealizada_mvtoEquipo.get(select_MvtoEquipo_laborRealizada.getSelectedIndex()).getCodigo()+"-"+listadoLaborRealizada_mvtoEquipo.get(select_MvtoEquipo_laborRealizada.getSelectedIndex()).getDescripcion()+"'\n";
                            }
                        }
                    }else{
                        validarCampos=false;
                        JOptionPane.showMessageDialog(null,"Error!!.. no se encuentran una labor a Realizar activa en el sistema, Consulte con el administrador del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                    }
                }
                /**VALIDAMOS EL CENTRO DE COSTO AUXILIAR DESTINO ##############################################################**/
                if(validarCampos){// revisamos que el validador sea true
                    if(listadoCentroCostoAuxiliarDestino_mvtoEquipo != null){//Hay al menos un Subcentro de costo cargado en la interfaz
                        if(mvtoEquipo.getCentroCostoAuxiliarDestino() != null){
                            if(!(listadoCentroCostoAuxiliarDestino_mvtoEquipo.get(select_MvtoEquipo_CCAuxiliarDestino.getSelectedIndex()).getCodigo() == mvtoEquipo.getCentroCostoAuxiliarDestino().getCodigo())){// El centro de costo auxiliar destino que seleccionó es diferente al registrado en el movimiento de Equipo, por tal motivo se registra dicho cambio
                                if(scriptDB.equals("")){
                                    scriptDB +="[me_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoEquipo.get(select_MvtoEquipo_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"'\n";
                                    scriptAuditoria +="[me_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoEquipo.get(select_MvtoEquipo_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"-"+listadoCentroCostoAuxiliarDestino_mvtoEquipo.get(select_MvtoEquipo_CCAuxiliarDestino.getSelectedIndex()).getDescripcion()+"'\n";
                                }else{
                                    scriptDB +=",[me_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoEquipo.get(select_MvtoEquipo_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"'\n";
                                    scriptAuditoria +=",[me_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoEquipo.get(select_MvtoEquipo_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"-"+listadoCentroCostoAuxiliarDestino_mvtoEquipo.get(select_MvtoEquipo_CCAuxiliarDestino.getSelectedIndex()).getDescripcion()+"'\n";
                                }
                            }
                        }else{
                            scriptDB +=",[me_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoEquipo.get(select_MvtoEquipo_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"'\n";
                            scriptAuditoria +=",[me_cntro_cost_auxiliarDestino_cdgo]='"+listadoCentroCostoAuxiliarDestino_mvtoEquipo.get(select_MvtoEquipo_CCAuxiliarDestino.getSelectedIndex()).getCodigo()+"-"+listadoCentroCostoAuxiliarDestino_mvtoEquipo.get(select_MvtoEquipo_CCAuxiliarDestino.getSelectedIndex()).getDescripcion()+"'\n";           
                        }
                    }else{
                        if(mvtoEquipo.getCentroCostoAuxiliarDestino() != null){
                            if(scriptDB.equals("")){
                                scriptDB +="[me_cntro_cost_auxiliarDestino_cdgo]=NULL \n";
                                scriptAuditoria +="[me_cntro_cost_auxiliarDestino_cdgo]=NULL \n";
                            }else{
                                scriptDB +=",[me_cntro_cost_auxiliarDestino_cdgo]=NULL \n";
                                scriptAuditoria +=",[me_cntro_cost_auxiliarDestino_cdgo]= NULL \n";
                            }
                        }
                    }
                }
                /**VALIDAMOS EL EQUIPO ##############################################################**/
                if(validarCampos){// revisamos que el validador sea true
                    if(listadoEquipos_mvtoEquipo != null){//Hay al menos un equipo cargado en la interfaz
                        if(!(listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getCodigo().equals(mvtoEquipo.getAsignacionEquipo().getCodigo()))){// El equipo que seleccionó es diferente al registrado en el movimiento de Equipo, por tal motivo se registra dicho cambio
                            if(scriptDB.equals("")){
                                scriptDB +="[me_asignacion_equipo_cdgo]="+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria +="[me_asignacion_equipo_cdgo]="+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getCodigo()+"- Cod:"+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getEquipo().getCodigo()+" Descripción: "+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getEquipo().getDescripcion()+" "+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getEquipo().getModelo()+"\n";
                            }else{
                                scriptDB +=",[me_asignacion_equipo_cdgo]="+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria +=",[me_asignacion_equipo_cdgo]="+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getCodigo()+"- Cod:"+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getEquipo().getCodigo()+" Descripción: "+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getEquipo().getDescripcion()+" "+listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getEquipo().getModelo()+"\n";
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
                    if(listadoRecobros_mvtoEquipo != null){//Hay al menos tenga una opción de recobro en la interfaz
                        if(!(listadoRecobros_mvtoEquipo.get(select_MvtoEquipo_recobro.getSelectedIndex()).getCodigo().equals(mvtoEquipo.getRecobro().getCodigo()))){// El recobro que seleccionó es diferente al registrado en el movimiento de Equipo, por tal motivo se registra dicho cambio
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
                /**VALIDAMOS EL NÚMERO DE ORDEN ##############################################################**/
                if(validarCampos){// revisamos que el validador sea true
                    if(!mvtoEquipo_numOrden.getText().equals("")){//revisamos que el número de orden no esté vacio.
                        if(!(mvtoEquipo_numOrden.getText().equals(mvtoEquipo.getNumeroOrden()))){// El número de orden que digitó es diferente al registrado en el movimiento de Equipo, por tal motivo se registra dicho cambio
                            if(scriptDB.equals("")){
                                scriptDB +="[me_num_orden]='"+mvtoEquipo_numOrden.getText()+"'\n";
                                scriptAuditoria +="[me_num_orden]='"+mvtoEquipo_numOrden.getText()+"'\n";
                            }else{
                                scriptDB +=",[me_num_orden]='"+mvtoEquipo_numOrden.getText()+"'\n";
                                scriptAuditoria +=",[me_num_orden]='"+mvtoEquipo_numOrden.getText()+"'\n";
                            }
                        }
                    }else{
                        if(scriptDB.equals("")){
                            scriptDB +="[me_num_orden]=''\n";
                            scriptAuditoria +="[me_num_orden]=''\n";
                        }else{
                            scriptDB +=",[me_num_orden]=''\n";
                            scriptAuditoria +=",[me_num_orden]=''\n";
                        }
                    }
                }
                 /**VALIDAMOS EL MOTIVO DE PARADA ##############################################################**/
                if(validarCampos){// revisamos que el validador sea true
                    if(listadoMotivoParada_mvtoEquipo != null){//Hay al menos tenga una opción de razón de finalización en la interfaz
                        if(!(listadoMotivoParada_mvtoEquipo.get(mvtoEquipo_selectRazonFinalzación.getSelectedIndex()).getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo()))){// El recobro que seleccionó es diferente al registrado en el movimiento de Equipo, por tal motivo se registra dicho cambio
                            if(scriptDB.equals("")){
                                scriptDB +="[me_motivo_parada_estado]=1,[me_motivo_parada_cdgo]="+listadoMotivoParada_mvtoEquipo.get(mvtoEquipo_selectRazonFinalzación.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria +="[me_motivo_parada_estado]=1,[me_motivo_parada_cdgo]="+listadoMotivoParada_mvtoEquipo.get(mvtoEquipo_selectRazonFinalzación.getSelectedIndex()).getCodigo()+"-"+listadoMotivoParada_mvtoEquipo.get(mvtoEquipo_selectRazonFinalzación.getSelectedIndex()).getDescripcion()+"\n";
                            }else{
                                scriptDB +=",[me_motivo_parada_estado]=1,[me_motivo_parada_cdgo]="+listadoMotivoParada_mvtoEquipo.get(mvtoEquipo_selectRazonFinalzación.getSelectedIndex()).getCodigo()+"\n";
                                scriptAuditoria +=",[me_motivo_parada_estado]=1,[me_motivo_parada_cdgo]="+listadoMotivoParada_mvtoEquipo.get(mvtoEquipo_selectRazonFinalzación.getSelectedIndex()).getCodigo()+"-"+listadoMotivoParada_mvtoEquipo.get(mvtoEquipo_selectRazonFinalzación.getSelectedIndex()).getDescripcion()+"\n";
                            }
                        }
                    }else{
                        //validarCampos=false;
                       //JOptionPane.showMessageDialog(null,"Error!!.. no se encuentran el listado de razón de motivación activo en el sistema, Consulte con el administrador del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                   }
                }
                /**VALIDAMOS OBSERVACIÓN MVTOEQUIPO ##############################################################**/
                if(validarCampos){// revisamos que el validador sea true
                    if(!mvtoEquipo_ObservaciónEditar.getText().equals("")){//revisamos que el número de orden no esté vacio.
                        if(!(mvtoEquipo_ObservaciónEditar.getText().equals(mvtoEquipo.getObservacionMvtoEquipo()))){// El número de orden que digitó es diferente al registrado en el movimiento de Equipo, por tal motivo se registra dicho cambio
                            if(scriptDB.equals("")){
                                scriptDB +="[me_observ]='"+mvtoEquipo_ObservaciónEditar.getText()+"'\n";
                                scriptAuditoria +="[me_observ]='"+mvtoEquipo_ObservaciónEditar.getText()+"'\n";
                            }else{
                                scriptDB +=",[me_observ]='"+mvtoEquipo_ObservaciónEditar.getText()+"'\n";
                                scriptAuditoria +=",[me_observ]='"+mvtoEquipo_ObservaciónEditar.getText()+"'\n";
                            }
                        }
                    }else{
                        if(scriptDB.equals("")){
                            scriptDB +="[me_observ]=''\n";
                            scriptAuditoria +="[me_observ]=''\n";
                        }else{
                            scriptDB +=",[me_observ]=''\n";
                            scriptAuditoria +=",[me_observ]=''\n";
                        }
                    }
                }
                System.out.println("\n\n");
                System.out.println(scriptDB); 
                System.out.println(scriptAuditoria);
                if(validarCampos){// revisamos que el validador sea true
                    int retorno=0;
                    try {
                        if(scriptDB.equals("")){
                            scriptDB +="[me_valor_hora]=NULL, [me_costo_total]=NULL,[me_cntro_cost_cdgo]=NULL,[me_cntro_cost]=NULL,[me_cntro_cost_mayor_cdgo]=NULL \n";
                        }else{
                            scriptDB +=",[me_valor_hora]=NULL, [me_costo_total]=NULL,[me_cntro_cost_cdgo]=NULL,[me_cntro_cost]=NULL,[me_cntro_cost_mayor_cdgo]=NULL \n";
                        }
                        retorno = new ControlDB_MvtoEquipo(tipoConexion).modificarMvtoEquipo(mvtoEquipo, user,scriptDB ,scriptAuditoria,textArea_MvtoEquipo_razonModificacion.getText());
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
                        JOptionPane.showMessageDialog(null, "Se registro la modificación del registro de forma exitosa", "Registro Exitoso",JOptionPane.INFORMATION_MESSAGE);
                        mvtoEquipo_ObservaciónEditar.setText("");
                        paginationPanel.removeAll();
                        validarSeleccionCampos();
                        generarListadoMvtoCarbon();
                        InternaFrame_VisualizarMvtoEquipo.show(false);    
                    }else{
                        JOptionPane.showMessageDialog(null, "No se puedo registrar la modificación del registro, valide datos", "Error al registrar",JOptionPane.ERROR_MESSAGE);
                    }    
                }else{
                    JOptionPane.showMessageDialog(null, "No se puedo registrar la modificación del registro, valide datos", "Error al registrar",JOptionPane.ERROR_MESSAGE);    
                }       
            }catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"No se pudo realizar la modificación, verifique información suministrada","Error!!!",JOptionPane.ERROR_MESSAGE);
            } 
        }else{
            JOptionPane.showMessageDialog(null,"La razón de modificación no puede estar vacia, valide la información suministrada","Error!!!",JOptionPane.ERROR_MESSAGE);  
        }  
    }//GEN-LAST:event_registrar_MvtoEquipoActionPerformed

    private void select_MvtoEquipo_COItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_COItemStateChanged
        //Cargamos en la interfaz los subcentro de costos activos
        listadoCentroCostoAuxiliar_mvtoEquipo=null;
        listadoCentroCostoSubCentro_mvtoEquipo=null;
        listadoLaborRealizada_mvtoEquipo=null;
        listadoCentroCostoAuxiliarDestino_mvtoEquipo=null;
        try {
            if(listadoCentroOperacion_mvtoEquipo != null){
                listadoCentroCostoSubCentro_mvtoEquipo=new ControlDB_CentroCostoSubCentro(tipoConexion).buscarActivos(listadoCentroOperacion_mvtoEquipo.get(select_MvtoEquipo_CO.getSelectedIndex()));
                if(listadoCentroCostoSubCentro_mvtoEquipo != null){
                    String datosObjeto[]= new String[listadoCentroCostoSubCentro_mvtoEquipo.size()];
                    int contador=0;
                    for(CentroCostoSubCentro Objeto : listadoCentroCostoSubCentro_mvtoEquipo){ 
                        datosObjeto[contador]=Objeto.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_MvtoEquipo_SubcentroCosto.setModel(model);
                }else{
                    listadoCentroCostoSubCentro_mvtoEquipo=null;
                    select_MvtoEquipo_SubcentroCosto.removeAllItems();

                    listadoCentroCostoAuxiliar_mvtoEquipo=null;
                    select_MvtoEquipo_CCAuxiliar.removeAllItems();

                    listadoLaborRealizada_mvtoEquipo=null;
                    select_MvtoEquipo_laborRealizada.removeAllItems();

                    listadoCentroCostoAuxiliarDestino_mvtoEquipo=null;
                    select_MvtoEquipo_CCAuxiliarDestino.removeAllItems();
                }
            }else{
                
                listadoCentroOperacion_mvtoEquipo=null;
                select_MvtoEquipo_CO.removeAllItems();
                
                listadoCentroCostoSubCentro_mvtoEquipo=null;
                select_MvtoEquipo_SubcentroCosto.removeAllItems();
                
                listadoCentroCostoAuxiliar_mvtoEquipo=null;
                select_MvtoEquipo_CCAuxiliar.removeAllItems();
                
                listadoLaborRealizada_mvtoEquipo=null;
                select_MvtoEquipo_laborRealizada.removeAllItems();
                
                listadoCentroCostoAuxiliarDestino_mvtoEquipo=null;
                select_MvtoEquipo_CCAuxiliarDestino.removeAllItems();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
        } 
        //Cargamos los auxiliares
        if(listadoCentroCostoSubCentro_mvtoEquipo != null){
            try {
                listadoCentroCostoAuxiliar_mvtoEquipo = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro_mvtoEquipo.get(select_MvtoEquipo_SubcentroCosto.getSelectedIndex()).getCodigo());
                if(listadoCentroCostoAuxiliar_mvtoEquipo != null){
                    String datosObjeto[] = new String[listadoCentroCostoAuxiliar_mvtoEquipo.size()];
                    int contador = 0;
                    for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliar_mvtoEquipo) {
                        datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_MvtoEquipo_CCAuxiliar.setModel(model);
                }else{
                    listadoCentroCostoAuxiliar_mvtoEquipo=null;
                    select_MvtoEquipo_CCAuxiliar.removeAllItems();
                    
                } 
            }catch (SQLException e){ 
            }
            //Cargamos las labores realizadas
            try {
                listadoLaborRealizada_mvtoEquipo=new ControlDB_LaborRealizada(tipoConexion).buscarPorSubcentroCosto(listadoCentroCostoSubCentro_mvtoEquipo.get(select_MvtoEquipo_SubcentroCosto.getSelectedIndex()));
                if(listadoLaborRealizada_mvtoEquipo != null){
                    String datosObjeto[]= new String[listadoLaborRealizada_mvtoEquipo.size()];
                    int contador=0;
                    for(LaborRealizada Objeto : listadoLaborRealizada_mvtoEquipo){ 
                        datosObjeto[contador]=Objeto.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_MvtoEquipo_laborRealizada.setModel(model);
                }else{
                    listadoLaborRealizada_mvtoEquipo=null;
                    select_MvtoEquipo_laborRealizada.removeAllItems();
                    
                    listadoCentroCostoAuxiliarDestino_mvtoEquipo=null;
                    select_MvtoEquipo_CCAuxiliarDestino.removeAllItems();
                    
                } 
            } catch (SQLException ex) {
                Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }else{ 
            listadoCentroCostoSubCentro_mvtoEquipo=null;    
            select_MvtoEquipo_SubcentroCosto.removeAllItems();

            listadoCentroCostoAuxiliar_mvtoEquipo=null;
            select_MvtoEquipo_CCAuxiliar.removeAllItems();

            listadoLaborRealizada_mvtoEquipo=null;    
            select_MvtoEquipo_laborRealizada.removeAllItems();

            listadoCentroCostoAuxiliarDestino_mvtoEquipo=null;
            select_MvtoEquipo_CCAuxiliarDestino.removeAllItems();
        } 
    }//GEN-LAST:event_select_MvtoEquipo_COItemStateChanged

    private void checkMotonaveItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkMotonaveItemStateChanged
        if(checkMotonave.isSelected()){
            //selectMotonave.setEnabled(true);
            icon_buscarMotonave.show(true);
            //label_motonave.show(true);
        }else{
            try{
                motonave=null;
                motonave =mvtoEquipo.getMotonave();
                label_motonave.setText(mvtoEquipo.getMotonave().getDescripcion());
            }catch(Exception e){
            
            }
            icon_buscarMotonave.show(false);
        }
    }//GEN-LAST:event_checkMotonaveItemStateChanged

    private void icon_buscarMotonaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_buscarMotonaveMouseClicked
        InternaFrame_buscarMotonave.show(true);
        InternaFrame_buscarMotonave.repaint();
    }//GEN-LAST:event_icon_buscarMotonaveMouseClicked

    private void tablaMotonavesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMotonavesMouseClicked
        if (evt.getClickCount() == 2) {
            int fila1;
            try{
                fila1=tablaMotonaves.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    motonave= listadoMotonave.get(fila1);
                    label_motonave.setText(motonave.getDescripcion());
                    InternaFrame_buscarMotonave.show(false);
                }
            }catch(Exception e){
            }
        }
    }//GEN-LAST:event_tablaMotonavesMouseClicked

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        try {
            DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "Nombre","Estado"});  
            listadoMotonave=new ControlDB_Motonave(tipoConexion).buscar(valorBusqueda.getText());
            for(Motonave listadoObjetos1:listadoMotonave){
                String []registro = new String[3];
                registro[0]=""+listadoObjetos1.getCodigo();
                registro[1]=""+listadoObjetos1.getDescripcion();
                registro[2]=""+listadoObjetos1.getEstado();            
                modelo.addRow(registro);
            }
            tablaMotonaves.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        InternaFrame_buscarMotonave.show(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void checkClienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkClienteItemStateChanged
        if(checkCliente.isSelected()){
            icon_buscarClientes.show(true);
            //label_clientes.show(true);
        }else{
            try{
                cliente=null;
                cliente =mvtoEquipo.getCliente();
                label_clientes.setText(mvtoEquipo.getCliente().getDescripcion());
            }catch(Exception e){
            
            }
            icon_buscarClientes.show(false);
            //label_clientes.show(false);
        }
    }//GEN-LAST:event_checkClienteItemStateChanged

    private void icon_buscarClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_buscarClientesMouseClicked
        InternaFrame_buscarCliente.show(true);
    }//GEN-LAST:event_icon_buscarClientesMouseClicked

    private void checkArticuloItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkArticuloItemStateChanged
        if(checkArticulo.isSelected()){
            icon_buscarArticulo.show(true);
            //label_articulo.show(true);
        }else{
            try{
                articulo=null;
                articulo =mvtoEquipo.getArticulo();
                label_articulo.setText(mvtoEquipo.getArticulo().getDescripcion());
            }catch(Exception e){
            
            }
            icon_buscarArticulo.show(false);
        }
    }//GEN-LAST:event_checkArticuloItemStateChanged

    private void icon_buscarArticuloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_buscarArticuloMouseClicked
        InternaFrame_buscarArticulo.show(true);
    }//GEN-LAST:event_icon_buscarArticuloMouseClicked

    private void mvtoEquipo_selectRazonFinalzaciónItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mvtoEquipo_selectRazonFinalzaciónItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_mvtoEquipo_selectRazonFinalzaciónItemStateChanged

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
                    label_clientes.setText(cliente.getDescripcion());
                    InternaFrame_buscarCliente.show(false);
                }
            }catch(Exception e){
            }
        }
    }//GEN-LAST:event_tablaClienteMouseClicked

    private void btnConsultarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarClienteActionPerformed
        try {
            DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "Nombre","Estado"});  
            listadoCliente=new ControlDB_Cliente(tipoConexion).buscarCliente_EstadoActivo(valorBusquedaCliente.getText());
            for(Cliente listadoObjetos1:listadoCliente){
                String []registro = new String[3];
                registro[0]=""+listadoObjetos1.getCodigo();
                registro[1]=""+listadoObjetos1.getDescripcion();
                registro[2]=""+listadoObjetos1.getEstado();            
                modelo.addRow(registro);
            }
            tablaCliente.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }//GEN-LAST:event_btnConsultarClienteActionPerformed

    private void btnCancelarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarClienteActionPerformed

    private void btnLimpiarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarClienteActionPerformed

    private void tablaArticuloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaArticuloMouseClicked
        if (evt.getClickCount() == 2) {
            int fila1;
            try{
                fila1=tablaArticulo.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    articulo= listadoArticulos.get(fila1);
                    label_articulo.setText(articulo.getDescripcion());
                    InternaFrame_buscarArticulo.show(false);
                }
            }catch(Exception e){
            }
        }
    }//GEN-LAST:event_tablaArticuloMouseClicked

    private void btnConsultarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarArticuloActionPerformed
        try {
            DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "Nombre","Estado"});  
            listadoArticulos=new ControlDB_Articulo(tipoConexion).buscarActivosConParametros(valorBusqueda.getText());
            for(Articulo listadoObjetos1:listadoArticulos){
                String []registro = new String[3];
                registro[0]=""+listadoObjetos1.getCodigo();
                registro[1]=""+listadoObjetos1.getDescripcion();
                registro[2]=""+listadoObjetos1.getEstado();            
                modelo.addRow(registro);
            }
            tablaArticulo.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }//GEN-LAST:event_btnConsultarArticuloActionPerformed

    private void btnCancelarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarArticuloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarArticuloActionPerformed

    private void btnLimpiarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarArticuloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarArticuloActionPerformed

    private void checkArticuloCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_checkArticuloCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_checkArticuloCaretPositionChanged

    private void select_MvtoEquipo_laborRealizadaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_laborRealizadaItemStateChanged
        if(listadoLaborRealizada_mvtoEquipo != null){
            if(listadoLaborRealizada_mvtoEquipo.get(select_MvtoEquipo_laborRealizada.getSelectedIndex()).getBodegaDestino().equals("1")){//Fue seleccionada una labor Realizada que tiene marcada Bodega destino por tal motivo cargalos las Bodega destino
               // listadoCentroCostoAuxiliarDestino_mvtoEquipo
                if(listadoCentroCostoSubCentro_mvtoEquipo != null){
                    try {
                        listadoCentroCostoAuxiliarDestino_mvtoEquipo = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro_mvtoEquipo.get(select_MvtoEquipo_SubcentroCosto.getSelectedIndex()).getCodigo());
                        if(listadoCentroCostoAuxiliarDestino_mvtoEquipo != null){
                            String datosObjeto[] = new String[listadoCentroCostoAuxiliarDestino_mvtoEquipo.size()];
                            int contador = 0;
                            for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliarDestino_mvtoEquipo) {
                                datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                                contador++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            select_MvtoEquipo_CCAuxiliarDestino.setModel(model);
                        }else{
                            listadoCentroCostoAuxiliarDestino_mvtoEquipo=null;
                            select_MvtoEquipo_CCAuxiliarDestino.removeAllItems();
                        } 
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }          
            }else{
                listadoCentroCostoAuxiliarDestino_mvtoEquipo=null;
                select_MvtoEquipo_CCAuxiliarDestino.removeAllItems(); 
            }
            
            if(listadoLaborRealizada_mvtoEquipo.get(select_MvtoEquipo_laborRealizada.getSelectedIndex()).getDescripcion().equalsIgnoreCase("STAND BY")){//Fue un MvtoEquipo donde la labor fue Stand BY, por tal motivo no debe aparecer el motivo de parada de finalizado.
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
                    mvtoEquipo_selectRazonFinalzación.setModel(model);


                    //cargamos los motivos de parada según la consulta
                    try{
                        if(listadoMotivoParada_mvtoEquipo !=null){
                            int contadorX=0;
                            for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                      mvtoEquipo_selectRazonFinalzación.setSelectedIndex(contadorX);  
                                }
                                contadorX++;
                            }
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    listadoMotivoParada_mvtoEquipo=null;
                    mvtoEquipo_selectRazonFinalzación.removeAllItems(); 
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
                        mvtoEquipo_selectRazonFinalzación.setModel(model);

                        //cargamos los motivos de parada según la consulta
                        try{
                            if(listadoMotivoParada_mvtoEquipo !=null){
                                int contadorX=0;
                                for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                    if(motivoParada.getCodigo().equals(mvtoEquipo.getMotivoParada().getCodigo())){
                                          mvtoEquipo_selectRazonFinalzación.setSelectedIndex(contadorX);  
                                    }
                                    contadorX++;
                                }
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }else{
                        listadoMotivoParada_mvtoEquipo=null;
                        mvtoEquipo_selectRazonFinalzación.removeAllItems(); 
                    }   
                } catch (SQLException ex) {
                    Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }  
        }else{
            listadoLaborRealizada_mvtoEquipo=null;
            select_MvtoEquipo_laborRealizada.removeAllItems();
                        
            listadoCentroCostoAuxiliarDestino_mvtoEquipo=null;
            select_MvtoEquipo_CCAuxiliarDestino.removeAllItems();
        }
        
    }//GEN-LAST:event_select_MvtoEquipo_laborRealizadaItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame InternaFrame_VisualizarMvtoEquipo;
    private javax.swing.JInternalFrame InternaFrame_buscarArticulo;
    private javax.swing.JInternalFrame InternaFrame_buscarCliente;
    private javax.swing.JInternalFrame InternaFrame_buscarMotonave;
    private javax.swing.JInternalFrame InternalFrameSelectorCampos;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JMenuItem Visualizar;
    private javax.swing.JLabel alerta_fechaFinal;
    private javax.swing.JLabel alerta_fechaInicio;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarArticulo;
    private javax.swing.JButton btnCancelarCliente;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnConsultarArticulo;
    private javax.swing.JButton btnConsultarCliente;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLimpiarArticulo;
    private javax.swing.JButton btnLimpiarCliente;
    private javax.swing.JRadioButton checkArticulo;
    private javax.swing.JRadioButton checkCliente;
    private javax.swing.JRadioButton checkMotonave;
    private javax.swing.JRadioButton check_MvtoEquipo_FinalizacionActividad;
    private javax.swing.JRadioButton check_MvtoEquipo_InicioActividad;
    private javax.swing.JButton consultar;
    private com.toedter.calendar.JDateChooser fechaFin;
    private com.toedter.calendar.JDateChooser fechaFinActividad_MvtoEquipo;
    private com.toedter.calendar.JDateChooser fechaInicialActividad_MvtoEquipo;
    private com.toedter.calendar.JDateChooser fechaInicio;
    private javax.swing.JComboBox<String> horaFin;
    private javax.swing.JComboBox<String> horaInicio;
    private javax.swing.JLabel horarioTiempoIFinalMvtoCarbon_Procesar;
    private javax.swing.JLabel horarioTiempoInicioMvtoCarbon_Procesar;
    private javax.swing.JLabel icon_buscarArticulo;
    private javax.swing.JLabel icon_buscarClientes;
    private javax.swing.JLabel icon_buscarMotonave;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator36;
    private javax.swing.JSeparator jSeparator37;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel label_MvtoEquipo_HoraFinal;
    private javax.swing.JLabel label_MvtoEquipo_HoraInicial;
    private javax.swing.JLabel label_MvtoEquipo_ZonaHorariaFinal;
    private javax.swing.JLabel label_MvtoEquipo_ZonaHorariaInicial;
    private javax.swing.JLabel label_MvtoEquipo_separadorFinal;
    private javax.swing.JLabel label_MvtoEquipo_separadorInicial;
    private javax.swing.JLabel label_articulo;
    private javax.swing.JLabel label_clientes;
    private javax.swing.JLabel label_motonave;
    private javax.swing.JComboBox<String> minutoFin;
    private javax.swing.JComboBox<String> minutoInicio;
    private javax.swing.JLabel mvtoEquipo_ActividadFinalizada;
    private javax.swing.JLabel mvtoEquipo_AuxilarCentroOperacionDestino;
    private javax.swing.JLabel mvtoEquipo_AuxilarCentroOperacionOrigen;
    private javax.swing.JLabel mvtoEquipo_CantidadHoras;
    private javax.swing.JLabel mvtoEquipo_CentroOperacion;
    private javax.swing.JLabel mvtoEquipo_Cliente_Código;
    private javax.swing.JLabel mvtoEquipo_Cliente_Nombre;
    private javax.swing.JLabel mvtoEquipo_Equipo_codigo;
    private javax.swing.JLabel mvtoEquipo_Equipo_descripción;
    private javax.swing.JLabel mvtoEquipo_Equipo_marca;
    private javax.swing.JLabel mvtoEquipo_Equipo_pertenencia;
    private javax.swing.JLabel mvtoEquipo_Equipo_proveedor;
    private javax.swing.JLabel mvtoEquipo_Equipo_tipo;
    private javax.swing.JLabel mvtoEquipo_FechaFinalActividad;
    private javax.swing.JLabel mvtoEquipo_FechaInicioActividad;
    private javax.swing.JLabel mvtoEquipo_Minutos;
    private javax.swing.JLabel mvtoEquipo_MotivoFinalización;
    private javax.swing.JLabel mvtoEquipo_NumeroOrden;
    private javax.swing.JTextArea mvtoEquipo_Observación;
    private javax.swing.JTextArea mvtoEquipo_ObservaciónEditar;
    private javax.swing.JLabel mvtoEquipo_Subcentro;
    private javax.swing.JLabel mvtoEquipo_UsuarioRegistro_Correo;
    private javax.swing.JLabel mvtoEquipo_UsuarioRegistro_Código;
    private javax.swing.JLabel mvtoEquipo_UsuarioRegistro_Nombre;
    private javax.swing.JLabel mvtoEquipo_articulo;
    private javax.swing.JLabel mvtoEquipo_codigo;
    private javax.swing.JLabel mvtoEquipo_estado;
    private javax.swing.JLabel mvtoEquipo_fechaMvto;
    private javax.swing.JLabel mvtoEquipo_laborRealizada;
    private javax.swing.JLabel mvtoEquipo_motonave;
    private javax.swing.JTextField mvtoEquipo_numOrden;
    private javax.swing.JLabel mvtoEquipo_recobro;
    private javax.swing.JComboBox<String> mvtoEquipo_selectRazonFinalzación;
    private javax.swing.JComboBox<Integer> pageJComboBox;
    public javax.swing.JPanel paginationPanel;
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
    private javax.swing.JRadioButton selectMvtoEquipo_CausalInactividad;
    private javax.swing.JRadioButton selectMvtoEquipo_DesdeCarbon;
    private javax.swing.JRadioButton selectMvtoEquipo_Inactividad;
    private javax.swing.JRadioButton selectMvtoEquipo_MotivoParada;
    private javax.swing.JRadioButton selectMvtoEquipo_Recobro;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioAutorizaRecobro_codigo;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioAutorizaRecobro_nombre;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioAutorizaRecobro_observacion;
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
    private javax.swing.JRadioButton selectMvtoEquipo_motonaveDescripción;
    private javax.swing.JRadioButton selectMvtoEquipo_numeroOrden;
    private javax.swing.JRadioButton selectMvtoEquipo_observacion;
    private javax.swing.JRadioButton selectMvtoEquipo_proveedorEquipoNit;
    private javax.swing.JRadioButton selectMvtoEquipo_proveedorEquipoNombre;
    private javax.swing.JRadioButton selectMvtoEquipo_subcentroCosto;
    private javax.swing.JRadioButton selectPerfilUsuario_codigo;
    private javax.swing.JRadioButton selectPerfilUsuario_descripcion;
    private javax.swing.JRadioButton selectPerfilUsuario_estado;
    private javax.swing.JComboBox<String> select_MvtoEquipo_CCAuxiliar;
    private javax.swing.JComboBox<String> select_MvtoEquipo_CCAuxiliarDestino;
    private javax.swing.JComboBox<String> select_MvtoEquipo_CO;
    private javax.swing.JComboBox<String> select_MvtoEquipo_Equipo;
    private javax.swing.JComboBox<String> select_MvtoEquipo_HoraFinal;
    private javax.swing.JComboBox<String> select_MvtoEquipo_HoraInicial;
    private javax.swing.JComboBox<String> select_MvtoEquipo_MinutosFinal;
    private javax.swing.JComboBox<String> select_MvtoEquipo_MinutosInicial;
    private javax.swing.JComboBox<String> select_MvtoEquipo_SubcentroCosto;
    private javax.swing.JComboBox<String> select_MvtoEquipo_laborRealizada;
    private javax.swing.JComboBox<String> select_MvtoEquipo_marcaEquipo;
    private javax.swing.JComboBox<String> select_MvtoEquipo_recobro;
    private javax.swing.JComboBox<String> select_MvtoEquipo_tipoEquipo;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tablaArticulo;
    private javax.swing.JTable tablaCliente;
    private javax.swing.JTable tablaMotonaves;
    private javax.swing.JTextArea textArea_MvtoEquipo_razonModificacion;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel titulo10;
    private javax.swing.JLabel titulo12;
    private javax.swing.JLabel titulo13;
    private javax.swing.JLabel titulo18;
    private javax.swing.JLabel titulo19;
    private javax.swing.JLabel titulo2;
    private javax.swing.JLabel titulo20;
    private javax.swing.JLabel titulo21;
    private javax.swing.JLabel titulo22;
    private javax.swing.JLabel titulo23;
    private javax.swing.JLabel titulo25;
    private javax.swing.JLabel titulo27;
    private javax.swing.JLabel titulo28;
    private javax.swing.JLabel titulo29;
    private javax.swing.JLabel titulo30;
    private javax.swing.JLabel titulo45;
    private javax.swing.JLabel titulo46;
    private javax.swing.JLabel titulo47;
    private javax.swing.JLabel titulo48;
    private javax.swing.JLabel titulo49;
    private javax.swing.JLabel titulo50;
    private javax.swing.JLabel titulo58;
    private javax.swing.JLabel titulo61;
    private javax.swing.JLabel titulo62;
    private javax.swing.JTextField valorBusqueda;
    private javax.swing.JTextField valorBusquedaArticulo;
    private javax.swing.JTextField valorBusquedaCliente;
    // End of variables declaration//GEN-END:variables
    public void cambioEstado (String data){
        switch(data){
            case "Centro_Operacion":{
                //Cargamos en la interfaz los subcentro de costos activos
                try {
                    if(listadoCentroOperacion_mvtoEquipo !=null){
                        listadoCentroCostoSubCentro_mvtoEquipo=new ControlDB_CentroCostoSubCentro(tipoConexion).buscarActivos(listadoCentroOperacion_mvtoEquipo.get(select_MvtoEquipo_CO.getSelectedIndex()));
                        if(listadoCentroCostoSubCentro_mvtoEquipo != null){
                            String datosObjeto[]= new String[listadoCentroCostoSubCentro_mvtoEquipo.size()];
                            int contador=0;
                            for(CentroCostoSubCentro Objeto : listadoCentroCostoSubCentro_mvtoEquipo){ 
                                datosObjeto[contador]=Objeto.getDescripcion();
                                contador++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            select_MvtoEquipo_SubcentroCosto.setModel(model);
                        }else{
                            select_MvtoEquipo_SubcentroCosto.removeAllItems();
                        }
                    }else{
                        select_MvtoEquipo_CO.removeAllItems();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                } 
                //Cargaos los auxiliares
                if(listadoCentroCostoSubCentro_mvtoEquipo != null){
                    try {
                        listadoCentroCostoAuxiliar_mvtoEquipo = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro_mvtoEquipo.get(select_MvtoEquipo_SubcentroCosto.getSelectedIndex()).getCodigo());
                        if(listadoCentroCostoAuxiliar_mvtoEquipo != null){
                            String datosObjeto[] = new String[listadoCentroCostoAuxiliar_mvtoEquipo.size()];
                            int contador = 0;
                            for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliar_mvtoEquipo) {
                                datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                                contador++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            select_MvtoEquipo_CCAuxiliar.setModel(model);
                        }else{
                            select_MvtoEquipo_CCAuxiliar.removeAllItems();
                        } 
                    }catch (SQLException e){ 
                    }
                    //Cargamos las labores realizadas
                    try {
                        listadoLaborRealizada_mvtoEquipo=new ControlDB_LaborRealizada(tipoConexion).buscarPorSubcentroCosto(listadoCentroCostoSubCentro_mvtoEquipo.get(select_MvtoEquipo_SubcentroCosto.getSelectedIndex()));
                        if(listadoLaborRealizada_mvtoEquipo != null){
                            String datosObjeto[]= new String[listadoLaborRealizada_mvtoEquipo.size()];
                            int contador=0;
                            for(LaborRealizada Objeto : listadoLaborRealizada_mvtoEquipo){ 
                                datosObjeto[contador]=Objeto.getDescripcion();
                                contador++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            select_MvtoEquipo_laborRealizada.setModel(model);
                        }else{
                            select_MvtoEquipo_laborRealizada.removeAllItems();
                        } 
                    } catch (SQLException ex) {
                        Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                }else{
                    select_MvtoEquipo_SubcentroCosto.removeAllItems();
                    select_MvtoEquipo_CCAuxiliar.removeAllItems();
                    //select_MvtoCarbon_laborRealizada.removeAllItems();//añadido para verificar
                } 
                break;
            }
            case "Subcentro_Costo":{
                /*if(listadoCentroCostoSubCentro_mvtoEquipo != null){
                    try {
                        listadoCentroCostoAuxiliar_mvtoEquipo = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro_mvtoEquipo.get(select_MvtoEquipo_SubcentroCosto.getSelectedIndex()).getCodigo());
                        if(listadoCentroCostoAuxiliar_mvtoEquipo != null){
                            String datosObjeto[] = new String[listadoCentroCostoAuxiliar_mvtoEquipo.size()];
                            int contador = 0;
                            for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliar_mvtoEquipo) {
                                datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                                contador++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            select_MvtoEquipo_CCAuxiliar.setModel(model);
                        }else{
                            select_MvtoEquipo_CCAuxiliar.removeAllItems();
                        } 
                        try {
                            listadoLaborRealizada_mvtoEquipo=new ControlDB_LaborRealizada(tipoConexion).buscarPorSubcentroCosto(listadoCentroCostoSubCentro_mvtoEquipo.get(select_MvtoEquipo_SubcentroCosto.getSelectedIndex()));
                            if(listadoLaborRealizada_mvtoEquipo != null){
                                String datosObjeto[]= new String[listadoLaborRealizada_mvtoEquipo.size()];
                                int contador=0;
                                for(LaborRealizada Objeto : listadoLaborRealizada_mvtoEquipo){ 
                                    datosObjeto[contador]=Objeto.getDescripcion();
                                    contador++;
                                }
                                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                select_MvtoEquipo_laborRealizada.setModel(model);
                            }else{
                                select_MvtoEquipo_laborRealizada.removeAllItems();
                            } 
                        } catch (SQLException ex) {
                            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                        }  
                    }catch (SQLException e){ 
                    }
                }else{
                    select_MvtoEquipo_SubcentroCosto.removeAllItems();
                    select_MvtoEquipo_laborRealizada.removeAllItems();
                    select_MvtoEquipo_CCAuxiliar.removeAllItems();
                }*/
                if(listadoCentroCostoSubCentro_mvtoEquipo != null){
                    try {
                        listadoCentroCostoAuxiliar_mvtoEquipo = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro_mvtoEquipo.get(select_MvtoEquipo_SubcentroCosto.getSelectedIndex()).getCodigo());
                        if(listadoCentroCostoAuxiliar_mvtoEquipo != null){
                            String datosObjeto[] = new String[listadoCentroCostoAuxiliar_mvtoEquipo.size()];
                            int contador = 0;
                            for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliar_mvtoEquipo) {
                                datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                                contador++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            select_MvtoEquipo_CCAuxiliar.setModel(model);
                        }else{
                            select_MvtoEquipo_CCAuxiliar.removeAllItems();
                        } 
                        try {
                            listadoLaborRealizada_mvtoEquipo=new ControlDB_LaborRealizada(tipoConexion).buscarPorSubcentroCosto(listadoCentroCostoSubCentro_mvtoEquipo.get(select_MvtoEquipo_SubcentroCosto.getSelectedIndex()));
                            if(listadoLaborRealizada_mvtoEquipo != null){
                                String datosObjeto[]= new String[listadoLaborRealizada_mvtoEquipo.size()];
                                int contador=0;
                                for(LaborRealizada Objeto : listadoLaborRealizada_mvtoEquipo){ 
                                    datosObjeto[contador]=Objeto.getDescripcion();
                                    contador++;
                                }
                                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                select_MvtoEquipo_laborRealizada.setModel(model);
                            }else{
                                select_MvtoEquipo_laborRealizada.removeAllItems();
                            } 
                        } catch (SQLException ex) {
                            Logger.getLogger(MvtoEquipo_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
                        }  
                    }catch (SQLException e){ 
                    }
                }else{
                    select_MvtoEquipo_SubcentroCosto.removeAllItems();
                    select_MvtoEquipo_laborRealizada.removeAllItems();
                    select_MvtoEquipo_CCAuxiliar.removeAllItems();
                }

                //validamos si la labor Realiza seleccionada tiene bodega Destino
                if(listadoLaborRealizada_mvtoEquipo != null){
                    if(listadoLaborRealizada_mvtoEquipo.get(select_MvtoEquipo_laborRealizada.getSelectedIndex()).getBodegaDestino().equals("1")){//Fue seleccionada una labor Realizada que tiene marcada Bodega destino por tal motivo cargalos las Bodega destino
                       // listadoCentroCostoAuxiliarDestino_mvtoEquipo
                        if(listadoCentroCostoSubCentro_mvtoEquipo != null){
                            try {
                                listadoCentroCostoAuxiliarDestino_mvtoEquipo = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro_mvtoEquipo.get(select_MvtoEquipo_SubcentroCosto.getSelectedIndex()).getCodigo());
                                if(listadoCentroCostoAuxiliarDestino_mvtoEquipo != null){
                                    String datosObjeto[] = new String[listadoCentroCostoAuxiliarDestino_mvtoEquipo.size()];
                                    int contador = 0;
                                    for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliarDestino_mvtoEquipo) {
                                        datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                                        contador++;
                                    }
                                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                                    select_MvtoEquipo_CCAuxiliarDestino.setModel(model);
                                }else{
                                    select_MvtoEquipo_CCAuxiliarDestino.removeAllItems();
                                } 
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }            
                    }else{
                       select_MvtoEquipo_CCAuxiliarDestino.removeAllItems(); 
                    }
                }else{
                    select_MvtoEquipo_laborRealizada.removeAllItems();
                    select_MvtoEquipo_CCAuxiliarDestino.removeAllItems(); 
                }
                break;
            }
            case "CentroCosto_Auxiliar":{
                break;
                
            }
        }
    
    }
    public void generarListadoMvtoCarbon(){
        //try{
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
                    Logger.getLogger(MvtoEquipo_Procesar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
       // }catch(Exception e){
            //JOptionPane.showMessageDialog(null,"No se puedo procesar los movimientos de equipos", "Advertencia",JOptionPane.ERROR_MESSAGE);
        //}   
    }
    public void selectorCampoPorDefecto(){
        selectMvtoEquipo_codigo.setSelected(true);
        selectMvtoEquipo_mes.setSelected(true);
        selectMvtoEquipo_fechaRegistro.setSelected(true);
        selectMvtoEquipo_proveedorEquipoNit.setSelected(false);
        selectMvtoEquipo_proveedorEquipoNombre.setSelected(true);
        selectMvtoEquipo_numeroOrden.setSelected(true);
        selectMvtoEquipo_centroOperacion.setSelected(true);
        selectMvtoEquipo_subcentroCosto.setSelected(true);
        selectMvtoEquipo_centroCostoAxiliarOrigen.setSelected(true);
        selectMvtoEquipo_centroCostoAuxiliarDestino.setSelected(true);
        selectMvtoEquipo_centroCosto.setSelected(true);
        selectMvtoEquipo_centroCostoMayor.setSelected(true);
        selectMvtoEquipo_laborRealizada.setSelected(true);
        selectMvtoEquipo_clienteCodigo.setSelected(false);
        selectMvtoEquipo_clienteNombre.setSelected(true);
        selectMvtoEquipo_articuloCodigo.setSelected(false);
        selectMvtoEquipo_articuloDescripcion.setSelected(true);
        selectMvtoEquipo_motonaveCodigo.setSelected(false);
        selectMvtoEquipo_motonaveDescripción.setSelected(true);
        selectMvtoEquipo_fechaInicioDescargue.setSelected(true);
        selectMvtoEquipo_fechaFinDescargue.setSelected(true);
        selectMvtoEquipo_cantidadMinutosDescargue.setSelected(true);
        selectMvtoEquipo_ValorHoraEquipo.setSelected(true);
        selectMvtoEquipo_costoTotal.setSelected(true);
        selectMvtoEquipo_Recobro.setSelected(true);
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setSelected(false);
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setSelected(false);
        selectMvtoEquipo_autorizacionRecobro.setSelected(false);
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setSelected(false);
        selectMvtoEquipo_Inactividad.setSelected(false);
        selectMvtoEquipo_CausalInactividad.setSelected(false);
        selectMvtoEquipo_UsuarioInactividad_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioInactividad_nombre.setSelected(false);
        selectMvtoEquipo_MotivoParada.setSelected(false);
        selectMvtoEquipo_observacion.setSelected(false);
        selectMvtoEquipo_estado.setSelected(true);
        selectMvtoEquipo_DesdeCarbon.setSelected(false);
        selectAsignacion_equipoCodigo.setSelected(true);
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
    }
    public void selectorCampoTodos(){
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
        selectMvtoEquipo_motonaveDescripción.setSelected(true);
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

    }
    public void selectorCampoNinguno(){
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
        selectMvtoEquipo_motonaveDescripción.setSelected(false);
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
    }   
    /**
     * @param data1
     * @param data2
     * @throws java.sql.SQLException*/
    public void tabla_Listar(String data1, String data2) throws SQLException{
        tabla.setModel(crearModeloDeTabla());
        listado=new ControlDB_MvtoEquipo(tipoConexion).buscar_MvtoEquipo_Activos(data1, data2);
        if(listado != null){
            proveedorDeDatos = crearProveedorDeDatos(listado); 
            paginadorDeTabla = new PaginadorDeTabla(tabla, proveedorDeDatos, new int[]{5, 10, 20, 50, 75, 100}, 10);
            paginadorDeTabla.crearListadoDeFilasPermitidas(this.paginationPanel);
            pageJComboBox = paginadorDeTabla.getComboBoxPage();
            events();
            pageJComboBox.setSelectedItem(Integer.parseInt("50"));
            resizeColumnWidth(tabla);
        }
    }
    public final void events(){
       pageJComboBox.addActionListener(this);
       tabla.getModel().addTableModelListener(this);
    }
    private ModeloDeTabla crearModeloDeTabla() {
        return new ModeloDeTabla<MvtoEquipo>() {            
            @Override
            public Object getValueAt(MvtoEquipo listado1, int columnas) {
               switch(encabezadoTabla.get(columnas)){
                    case "Código":{
                       return listado1.getCodigo();
                    }
                    case "MES":{
                       return getMes(listado1.getMes());
                    }
                    case "Fecha_Registro":{
                       return listado1.getFechaRegistro();
                    }
                    case "ProveedorEquipo_NIT":{
                       return listado1.getProveedorEquipo().getNit();
                    }
                    case "ProveedorEquipo_Nombre":{
                       return listado1.getProveedorEquipo().getDescripcion();
                    }
                    case "Número_Orden":{
                       return listado1.getNumeroOrden();
                    }
                    case "Centro_Operación":{
                       return listado1.getCentroOperacion().getDescripcion();
                    }
                    case "Subcentro_Costo":{
                       return listado1.getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                    }
                    case "Centro_Costo_Auxiliar_Origen":{
                       return listado1.getCentroCostoAuxiliar().getDescripcion();
                    }
                    case "Centro_Costo_Auxiliar_Destino":{
                       return listado1.getCentroCostoAuxiliarDestino().getDescripcion();
                    }
                    case "Centro_Costo":{
                       return listado1.getCentroCosto().getDescripción();
                    }
                    case "Centro_Costo_Mayor":{
                       return listado1.getCentroCostoMayor().getDescripcion();
                    }
                    case "Labor_Realizada":{
                       return listado1.getLaborRealizada().getDescripcion();
                    }
                    case "Cliente_Código":{
                       return listado1.getCliente().getCodigo();
                    }
                    case "Cliente_Nombre":{
                       return listado1.getCliente().getDescripcion();
                    }
                    case "Artículo_Código":{
                       return listado1.getArticulo().getCodigo();
                    }
                    case "Artículo_Nombre":{
                       return listado1.getArticulo().getDescripcion();
                    }
                    case "Motonave_Código":{
                       return listado1.getMotonave().getCodigo();
                    }
                    case "Motonave_Descripción":{
                       return listado1.getMotonave().getDescripcion();
                    }
                    case "FechaInicio_Actividad":{
                       return listado1.getFechaHoraInicio();
                    }
                    case "FechaFinalización_Actividad":{
                       return listado1.getFechaHoraFin();
                    }
                    case "Duración_Actividad(Minutos)":{
                       return listado1.getTotalMinutos();
                    }
                    case "Valor_Hora":{
                       return listado1.getValorHora();
                    }
                    case "Costo_Total":{
                       return listado1.getCostoTotal();
                    }
                    case "Recobro":{
                       return listado1.getRecobro().getDescripcion();
                    }
                    case "UsuarioQuienRegistra_Código":{
                       return listado1.getUsuarioQuieRegistra().getCodigo();
                    }
                    case "UsuarioQuienRegistra_Nombre":{
                       return listado1.getUsuarioQuieRegistra().getNombres()+" "+listado1.getUsuarioQuieRegistra().getApellidos();
                    }
                    case "UsuarioQuienAutoriza_Código":{
                       return listado1.getUsuarioAutorizaRecobro().getCodigo();
                    }
                    case "UsuarioQuienAutoriza_Nombre":{
                       return listado1.getUsuarioAutorizaRecobro().getNombres()+" "+listado1.getUsuarioAutorizaRecobro().getApellidos();
                    }
                    case "Autorización_Recobro":{
                       return listado1.getAutorizacionRecobro().getDescripcion();
                    }
                    case "Observación_Autorización_Recobro":{
                       return listado1.getObservacionAutorizacion();
                    }
                    case "Inactividad":{
                       return listado1.getInactividad();
                    }
                    case "Causal_Inactividad":{
                       return listado1.getCausaInactividad().getDescripcion();
                    }
                    case "UsuarioQuienInactiva_Código":{
                       return listado1.getUsuarioInactividad().getCodigo();
                    }
                    case "UsuarioQuienInactiva_Nombre":{
                       return listado1.getUsuarioInactividad().getNombres();
                    }
                    case "Motivo_Parada":{
                       return listado1.getMotivoParada().getDescripcion();
                    }
                    case "MvtoEquipo_Observación":{
                       return listado1.getObservacionMvtoEquipo();
                    }
                    case "Estado":{
                       return listado1.getEstado();
                    }
                    case "Desde_Carbón":{
                       return listado1.getDesdeCarbon();
                    }
                    case "Equipo_Código":{
                       return listado1.getAsignacionEquipo().getEquipo().getCodigo();
                    }
                    case "Equipo_Tipo":{
                       return listado1.getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion();
                    }
                    case "Equipo_Marca":{
                       return listado1.getAsignacionEquipo().getEquipo().getMarca();
                    }
                    case "Equipo_Modelo":{
                       return listado1.getAsignacionEquipo().getEquipo().getModelo();
                    }
                    case "Equipo_Descripción":{
                       return listado1.getAsignacionEquipo().getEquipo().getDescripcion()+" "+listado1.getAsignacionEquipo().getEquipo().getModelo();
                    }
                    case "Equipo_Pertenencia":{
                       return listado1.getAsignacionEquipo().getPertenencia().getDescripcion();
                    }
                    case "Asignación_Código":{
                       return listado1.getAsignacionEquipo().getCodigo();
                    }
                    case "Asignación_FechaRegistro":{
                       return listado1.getAsignacionEquipo().getFechaRegistro();
                    }
                    case "Asignación_FechaInicioActividad":{
                       return listado1.getAsignacionEquipo().getFechaHoraInicio();
                    }
                    case "Asignación_FechaFinalizaciónActividad":{
                       return listado1.getAsignacionEquipo().getFechaHoraFin();
                    }
                    case "Asignación_CantidadMinutosProgramados":{
                       return listado1.getAsignacionEquipo().getCantidadMinutosProgramados();
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
    private ProveedorDeDatosDePaginacion<MvtoEquipo> crearProveedorDeDatos(final ArrayList<MvtoEquipo> model) {
        //Obtenemos el listado de registros existentes haciendo una consulta a la base de datos.
        
        //Retornamos un interfaz de tipo ProveedorDeDatosDePaginacion en la cual sobreescribimos sus metodos abtractos
        //1 metodo: obtenemos el numero total de registros agregados al JTable.
        //2 metodo: obtenemos una subLista la cual será mostrada en el JTable, seria nuestra pagina actual.
        return new ProveedorDeDatosDePaginacion<MvtoEquipo>() {
            @Override
            public int getTotalRowCount() {
                return model.size();
            }

            @Override
            public List<MvtoEquipo> getRows(int startIndex, int endIndex) {
                return model.subList(startIndex, endIndex);
            }
        };
    }
    public void validarSeleccionCampos(){
        encabezadoTabla= new ArrayList<>();
        if(selectMvtoEquipo_codigo.isSelected()){
                encabezadoTabla.add("Código");
        }
        if(selectMvtoEquipo_mes.isSelected()){
                encabezadoTabla.add("MES");
        }
        if(selectMvtoEquipo_fechaRegistro.isSelected()){
                encabezadoTabla.add("Fecha_Registro");
        }
        if(selectAsignacion_equipoCodigo.isSelected()){
                encabezadoTabla.add("Equipo_Código");
        }
        if(selectAsignacion_equipoTipo.isSelected()){
                encabezadoTabla.add("Equipo_Tipo");
        }
        if(selectAsignacion_equipoMarca.isSelected()){
                encabezadoTabla.add("Equipo_Marca");
        }
        if(selectAsignacion_equipoModelo.isSelected()){
                encabezadoTabla.add("Equipo_Modelo");
        }
        if(selectAsignacion_equipoDescripcion.isSelected()){
                encabezadoTabla.add("Equipo_Descripción");
        }
        if(selectMvtoEquipo_proveedorEquipoNit.isSelected()){
                encabezadoTabla.add("ProveedorEquipo_NIT");
        }
        if(selectMvtoEquipo_proveedorEquipoNombre.isSelected()){
                encabezadoTabla.add("ProveedorEquipo_Nombre");
        }
        if(selectAsignacion_equipoPertenencia.isSelected()){
                encabezadoTabla.add("Equipo_Pertenencia");
        }
        if(selectMvtoEquipo_numeroOrden.isSelected()){
                encabezadoTabla.add("Número_Orden");
        }
        if(selectMvtoEquipo_centroOperacion.isSelected()){
                encabezadoTabla.add("Centro_Operación");
        }
        if(selectMvtoEquipo_subcentroCosto.isSelected()){
                encabezadoTabla.add("Subcentro_Costo");
        }
        if(selectMvtoEquipo_centroCostoAxiliarOrigen.isSelected()){
                encabezadoTabla.add("Centro_Costo_Auxiliar_Origen");
        }
        if(selectMvtoEquipo_centroCostoAuxiliarDestino.isSelected()){
                encabezadoTabla.add("Centro_Costo_Auxiliar_Destino");
        }
        if(selectMvtoEquipo_centroCosto.isSelected()){
                encabezadoTabla.add("Centro_Costo");
        }
        if(selectMvtoEquipo_centroCostoMayor.isSelected()){
                encabezadoTabla.add("Centro_Costo_Mayor");
        }
        if(selectMvtoEquipo_laborRealizada.isSelected()){
                encabezadoTabla.add("Labor_Realizada");
        }
        if(selectMvtoEquipo_clienteCodigo.isSelected()){
                encabezadoTabla.add("Cliente_Código");
        }
        if(selectMvtoEquipo_clienteNombre.isSelected()){
                encabezadoTabla.add("Cliente_Nombre");
        }
        if(selectMvtoEquipo_articuloCodigo.isSelected()){
                encabezadoTabla.add("Artículo_Código");
        }
        if(selectMvtoEquipo_articuloDescripcion.isSelected()){
                encabezadoTabla.add("Artículo_Nombre");
        }
        if(selectMvtoEquipo_motonaveCodigo.isSelected()){
                encabezadoTabla.add("Motonave_Código");
        }
        if(selectMvtoEquipo_motonaveDescripción.isSelected()){
                encabezadoTabla.add("Motonave_Descripción");
        }
        if(selectMvtoEquipo_Recobro.isSelected()){
                encabezadoTabla.add("Recobro");
        }
        if(selectMvtoEquipo_fechaInicioDescargue.isSelected()){
                encabezadoTabla.add("FechaInicio_Actividad");
        }
        if(selectMvtoEquipo_fechaFinDescargue.isSelected()){
                encabezadoTabla.add("FechaFinalización_Actividad");
        }
        if(selectMvtoEquipo_cantidadMinutosDescargue.isSelected()){
                encabezadoTabla.add("Duración_Actividad(Minutos)");
        }
        if(selectMvtoEquipo_ValorHoraEquipo.isSelected()){
                encabezadoTabla.add("Valor_Hora");
        }
        if(selectMvtoEquipo_costoTotal.isSelected()){
                encabezadoTabla.add("Costo_Total");
        }
        if(selectMvtoEquipo_observacion.isSelected()){
                encabezadoTabla.add("MvtoEquipo_Observación");
        }
        if(selectMvtoEquipo_estado.isSelected()){
                encabezadoTabla.add("Estado");
        }
        if(selectMvtoEquipo_DesdeCarbon.isSelected()){
                encabezadoTabla.add("Desde_Carbón");
        }  
        if(selectMvtoEquipo_UsuarioRegistraMvto_codigo.isSelected()){
                encabezadoTabla.add("UsuarioQuienRegistra_Código");
        }
        if(selectMvtoEquipo_UsuarioRegistraMvto_nombre.isSelected()){
                encabezadoTabla.add("UsuarioQuienRegistra_Nombre");
        }
        if(selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.isSelected()){
                encabezadoTabla.add("UsuarioQuienAutoriza_Código");
        }
        if(selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.isSelected()){
                encabezadoTabla.add("UsuarioQuienAutoriza_Nombre");
        }
        if(selectMvtoEquipo_autorizacionRecobro.isSelected()){
                encabezadoTabla.add("Autorización_Recobro");
        }
        if(selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.isSelected()){
                encabezadoTabla.add("Observación_Autorización_Recobro");
        }
        if(selectMvtoEquipo_Inactividad.isSelected()){
                encabezadoTabla.add("Inactividad");
        }
        if(selectMvtoEquipo_CausalInactividad.isSelected()){
                encabezadoTabla.add("Causal_Inactividad");
        }
        if(selectMvtoEquipo_UsuarioInactividad_codigo.isSelected()){
                encabezadoTabla.add("UsuarioQuienInactiva_Código");
        }
        if(selectMvtoEquipo_UsuarioInactividad_nombre.isSelected()){
                encabezadoTabla.add("UsuarioQuienInactiva_Nombre");
        }
        if(selectMvtoEquipo_MotivoParada.isSelected()){
                encabezadoTabla.add("Motivo_Parada");
        }
        if(selectAsignacion_codigo.isSelected()){
                encabezadoTabla.add("Asignación_Código");
        }
        if(selectAsignacion_fechaRegistro.isSelected()){
                encabezadoTabla.add("Asignación_FechaRegistro");
        }
        if(selectAsignacion_fechaInicio.isSelected()){
                encabezadoTabla.add("Asignación_FechaInicioActividad");
        }
        if(selectAsignacion_fechaFinalizacion.isSelected()){
                encabezadoTabla.add("Asignación_FechaFinalizaciónActividad");
        }
        if(selectAsignacion_cantidadMinutos_Programados.isSelected()){
                encabezadoTabla.add("Asignación_CantidadMinutosProgramados");
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
