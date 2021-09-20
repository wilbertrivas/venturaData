
package ModuloEquipo.View;

import Catalogo.Controller.ControlDB_Articulo;
import Catalogo.Controller.ControlDB_CentroCostoSubCentro;
import Catalogo.Controller.ControlDB_CentroOperacion;
import Catalogo.Controller.ControlDB_Cliente;
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
import ModuloCarbon.Controller2.ControlDB_MvtoCarbon;
import ModuloCarbon.View.MvtoCarbon_ModificarFinal;
import ModuloEquipo.Controller.ControlDB_MvtoEquipo;
import ModuloEquipo.Model.AsignacionEquipo;
import ModuloEquipo.Model.AutorizacionRecobro;
import ModuloEquipo.Model.MvtoEquipo;
import ModuloEquipo.Model.Recobro;
import Sistema.Controller.ControlDB_Usuario;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MvtoEquipo_Agregar extends javax.swing.JPanel {
    private String tipoConexion;
    private Usuario user;
    private ArrayList<AutorizacionRecobro> listadoAutorizacionRecobro= null;
    ArrayList<Usuario> listadoUsuarioQuienAutorizaRecobro= new ArrayList();
    private ArrayList<Motonave> listadoMotonave= null;
    private ArrayList<Cliente> listadoCliente=null;
    private ArrayList<Articulo> listadoArticulos=null;
    
    MvtoEquipo mvtoEquipo;
    private Motonave motonave=null;
    private Cliente cliente=null;
    private Articulo articulo=null;
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
    
    ArrayList<Usuario> listadoUsuarioInicioRegistro_mvtoEquipo= new ArrayList();
    ArrayList<Usuario> listadoUsuarioCierraRegistro_mvtoEquipo= new ArrayList();   
    
    
    public MvtoEquipo_Agregar(Usuario user, String tipoConexion) {
        initComponents();
        this.tipoConexion= tipoConexion;
        this.user= user;
        mvtoEquipo = null;
        
        
        InternaFrame_buscarMotonave.getContentPane().setBackground(Color.WHITE);
        InternaFrame_buscarCliente.getContentPane().setBackground(Color.WHITE);
        InternaFrame_buscarArticulo.getContentPane().setBackground(Color.WHITE);
        
        InternaFrame_buscarMotonave.show(false);
        InternaFrame_buscarCliente.show(false);
        InternaFrame_buscarArticulo.show(false);
        
        
        Label_mvtoEquipo_autorizadoPor.show(false);
        select_MvtoEquipo_UsuarioQuienAutorizaRecobro.show(false);
        
        //Cargamos las horas y los minutos en las selección de las interfaces.
        for(int i = 0; i<= 23; i++){
            if(i<=9){
                select_MvtoEquipo_HoraInicial.addItem("0"+i);
                select_MvtoEquipo_HoraFinal.addItem("0"+i);
            }else{
                select_MvtoEquipo_HoraInicial.addItem(""+i);
                select_MvtoEquipo_HoraFinal.addItem(""+i);
            }
        }
        for(int i = 0; i<= 59; i++){
            if(i<=9){
                select_MvtoEquipo_MinutosInicial.addItem("0"+i);
                select_MvtoEquipo_MinutosFinal.addItem("0"+i);
            }else{
                select_MvtoEquipo_MinutosInicial.addItem(""+i);
                select_MvtoEquipo_MinutosFinal.addItem(""+i);
            }
        }
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
            listadoRecobros_mvtoEquipo.remove(2);//Eliminamos el Pendiente por confirmación
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
        
         //Cargamos en la interfaz el listado de usuarios
        try {
            listadoUsuarioInicioRegistro_mvtoEquipo=new ControlDB_Usuario(tipoConexion).buscar("");
            listadoUsuarioCierraRegistro_mvtoEquipo=listadoUsuarioInicioRegistro_mvtoEquipo;
            listadoUsuarioQuienAutorizaRecobro=listadoUsuarioInicioRegistro_mvtoEquipo;   
            
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
            if(listadoUsuarioQuienAutorizaRecobro != null){
                String datosObjeto[]= new String[listadoUsuarioQuienAutorizaRecobro.size()];
                int contador=0;
                for(Usuario Objeto : listadoUsuarioQuienAutorizaRecobro){ 
                    datosObjeto[contador]=Objeto.getNombres()+ " "+ Objeto.getApellidos();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                select_MvtoEquipo_UsuarioQuienAutorizaRecobro.setModel(model);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
        }   
        titulo64.show(true);
        titulo65.show(true);
        titulo62.show(true);
        select_MvtoEquipo_HoraInicial.show(true);
        select_MvtoEquipo_HoraFinal.show(true);
        fechaInicialActividad_MvtoEquipo.show(true);
        fechaFinActividad_MvtoEquipo.show(true);
        label_MvtoEquipo_HoraInicial.show(true);
        label_MvtoEquipo_HoraFinal.show(true);
        label_MvtoEquipo_separadorInicial.show(true);
        label_MvtoEquipo_separadorFinal.show(true);
        select_MvtoEquipo_MinutosInicial.show(true);
        select_MvtoEquipo_MinutosFinal.show(true);
        label_MvtoEquipo_ZonaHorariaInicial.show(true);
        label_MvtoEquipo_ZonaHorariaFinal.show(true);
        jButton1.show(true);
        
        
        
        Label_mvtoEquipo_cliente.show(false);
        Label_mvtoEquipo_motonave.show(false);
        Label_mvtoEquipo_subCentroCosto.show(false);
        Label_mvtoEquipo_LaborRealizada.show(false);
        Label_mvtoEquipo_tipoEquipo.show(false);
        Label_mvtoEquipo_equipo.show(false);
        Label_mvtoEquipo_numeroOrden.show(false);
        Label_mvtoEquipo_razonFinalizacion.show(false);
        Label_mvtoEquipo_usuarioQuienInicia.show(false);
        Label_mvtoEquipo_usuarioQuienCierra.show(false);
        Label_mvtoEquipo_ObservaciónActividad.show(false);
        Label_mvtoEquipo_articulo.show(false);
        Label_mvtoEquipo_cntroOperacion.show(false);
        Label_mvtoEquipo_cntroCostoAuxiliarOrigen.show(false);
        Label_mvtoEquipo_cntroCostoAuxiliarDestino.show(false);
        Label_mvtoEquipo_marcaEquipo.show(false);
        Label_mvtoEquipo_recobro.show(false);
        Label_mvtoEquipo_autorizadoPor.show(false);

        icon_buscarClientes.show(false);
        icon_buscarMotonave.show(false);
        icon_buscarArticulo.show(false);


        label_clientes.show(false);
        label_motonave.show(false);
        select_MvtoEquipo_SubcentroCosto.show(false);
        select_MvtoEquipo_laborRealizada.show(false);
        select_MvtoEquipo_tipoEquipo.show(false);
        select_MvtoEquipo_Equipo.show(false);
        mvtoEquipo_numOrden.show(false);
        mvtoEquipo_selectRazonFinalzación.show(false);
        select_MvtoEquipo_UsuarioIniciaRegistro.show(false);
        select_MvtoEquipo_UsuarioCierraRegistro.show(false);
        textArea_MvtoEquipo_Observacion.show(false);
        label_articulo.show(false);
        select_MvtoEquipo_CO.show(false);
        select_MvtoEquipo_CCAuxiliar.show(false);
        select_MvtoEquipo_CCAuxiliarDestino.show(false);
        select_MvtoEquipo_marcaEquipo.show(false);
        select_MvtoEquipo_recobro.show(false);
        select_MvtoEquipo_UsuarioQuienAutorizaRecobro.show(false);
        Agregar_MvtoEquipo.show(false);
        jButton2.show(false);
        
        
        fechaHoraInicioActividad.show(false);
        fechaHoraFinalActividad.show(false);
        fechaHoraInicioActividadS.show(false);
        fechaHoraFinalActividadS.show(false);
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        Agregar_MvtoEquipo = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        textArea_MvtoEquipo_Observacion = new javax.swing.JTextArea();
        Label_mvtoEquipo_ObservaciónActividad = new javax.swing.JLabel();
        select_MvtoEquipo_UsuarioIniciaRegistro = new javax.swing.JComboBox<>();
        select_MvtoEquipo_UsuarioCierraRegistro = new javax.swing.JComboBox<>();
        mvtoEquipo_selectRazonFinalzación = new javax.swing.JComboBox<>();
        Label_mvtoEquipo_razonFinalizacion = new javax.swing.JLabel();
        mvtoEquipo_numOrden = new javax.swing.JTextField();
        select_MvtoEquipo_Equipo = new javax.swing.JComboBox<>();
        titulo62 = new javax.swing.JLabel();
        Label_mvtoEquipo_numeroOrden = new javax.swing.JLabel();
        select_MvtoEquipo_marcaEquipo = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        Label_mvtoEquipo_marcaEquipo = new javax.swing.JLabel();
        Label_mvtoEquipo_cntroCostoAuxiliarDestino = new javax.swing.JLabel();
        select_MvtoEquipo_CCAuxiliarDestino = new javax.swing.JComboBox<>();
        select_MvtoEquipo_laborRealizada = new javax.swing.JComboBox<>();
        select_MvtoEquipo_tipoEquipo = new javax.swing.JComboBox<>();
        Label_mvtoEquipo_tipoEquipo = new javax.swing.JLabel();
        Label_mvtoEquipo_LaborRealizada = new javax.swing.JLabel();
        Label_mvtoEquipo_articulo = new javax.swing.JLabel();
        select_MvtoEquipo_SubcentroCosto = new javax.swing.JComboBox<>();
        Label_mvtoEquipo_cntroCostoAuxiliarOrigen = new javax.swing.JLabel();
        select_MvtoEquipo_CCAuxiliar = new javax.swing.JComboBox<>();
        select_MvtoEquipo_CO = new javax.swing.JComboBox<>();
        Label_mvtoEquipo_cntroOperacion = new javax.swing.JLabel();
        label_articulo = new javax.swing.JLabel();
        icon_buscarArticulo = new javax.swing.JLabel();
        label_motonave = new javax.swing.JLabel();
        label_clientes = new javax.swing.JLabel();
        icon_buscarClientes = new javax.swing.JLabel();
        icon_buscarMotonave = new javax.swing.JLabel();
        fechaFinActividad_MvtoEquipo = new com.toedter.calendar.JDateChooser();
        label_MvtoEquipo_HoraFinal = new javax.swing.JLabel();
        select_MvtoEquipo_HoraFinal = new javax.swing.JComboBox<>();
        label_MvtoEquipo_separadorFinal = new javax.swing.JLabel();
        select_MvtoEquipo_MinutosFinal = new javax.swing.JComboBox<>();
        label_MvtoEquipo_ZonaHorariaFinal = new javax.swing.JLabel();
        label_MvtoEquipo_ZonaHorariaInicial = new javax.swing.JLabel();
        select_MvtoEquipo_MinutosInicial = new javax.swing.JComboBox<>();
        label_MvtoEquipo_separadorInicial = new javax.swing.JLabel();
        select_MvtoEquipo_HoraInicial = new javax.swing.JComboBox<>();
        label_MvtoEquipo_HoraInicial = new javax.swing.JLabel();
        fechaInicialActividad_MvtoEquipo = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        Label_mvtoEquipo_equipo = new javax.swing.JLabel();
        titulo64 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        Label_mvtoEquipo_subCentroCosto = new javax.swing.JLabel();
        Label_mvtoEquipo_usuarioQuienCierra = new javax.swing.JLabel();
        Label_mvtoEquipo_motonave = new javax.swing.JLabel();
        Label_mvtoEquipo_cliente = new javax.swing.JLabel();
        Label_mvtoEquipo_usuarioQuienInicia = new javax.swing.JLabel();
        titulo65 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Label_mvtoEquipo_recobro = new javax.swing.JLabel();
        select_MvtoEquipo_recobro = new javax.swing.JComboBox<>();
        Label_mvtoEquipo_autorizadoPor = new javax.swing.JLabel();
        select_MvtoEquipo_UsuarioQuienAutorizaRecobro = new javax.swing.JComboBox<>();
        fechaHoraFinalActividad = new javax.swing.JLabel();
        fechaHoraInicioActividad = new javax.swing.JLabel();
        fechaHoraInicioActividadS = new javax.swing.JLabel();
        fechaHoraFinalActividadS = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

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

        Agregar_MvtoEquipo.setBackground(new java.awt.Color(255, 255, 255));
        Agregar_MvtoEquipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Agregar_MvtoEquipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save.png"))); // NOI18N
        Agregar_MvtoEquipo.setText("REGISTRAR");
        Agregar_MvtoEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Agregar_MvtoEquipoActionPerformed(evt);
            }
        });
        add(Agregar_MvtoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 720, 180, 50));

        textArea_MvtoEquipo_Observacion.setColumns(20);
        textArea_MvtoEquipo_Observacion.setRows(5);
        jScrollPane7.setViewportView(textArea_MvtoEquipo_Observacion);

        add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 620, 680, 80));

        Label_mvtoEquipo_ObservaciónActividad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Label_mvtoEquipo_ObservaciónActividad.setForeground(new java.awt.Color(51, 51, 51));
        Label_mvtoEquipo_ObservaciónActividad.setText("OBSERVACIÓN DE LA ACTIVIDAD:");
        add(Label_mvtoEquipo_ObservaciónActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, 210, 30));

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
        add(select_MvtoEquipo_UsuarioIniciaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 530, 320, 30));

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
        add(select_MvtoEquipo_UsuarioCierraRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 570, 320, 30));

        mvtoEquipo_selectRazonFinalzación.setToolTipText("");
        mvtoEquipo_selectRazonFinalzación.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mvtoEquipo_selectRazonFinalzaciónItemStateChanged(evt);
            }
        });
        mvtoEquipo_selectRazonFinalzación.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                mvtoEquipo_selectRazonFinalzaciónCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        add(mvtoEquipo_selectRazonFinalzación, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, 320, 30));

        Label_mvtoEquipo_razonFinalizacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Label_mvtoEquipo_razonFinalizacion.setForeground(new java.awt.Color(51, 51, 51));
        Label_mvtoEquipo_razonFinalizacion.setText("RAZÓN DE FINALIZACIÓN:");
        Label_mvtoEquipo_razonFinalizacion.setPreferredSize(new java.awt.Dimension(133, 15));
        add(Label_mvtoEquipo_razonFinalizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 180, 30));
        add(mvtoEquipo_numOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 440, 300, 30));

        select_MvtoEquipo_Equipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_EquipoActionPerformed(evt);
            }
        });
        add(select_MvtoEquipo_Equipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 400, 820, 30));

        titulo62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo62.setForeground(new java.awt.Color(51, 51, 51));
        titulo62.setText("FINALIZACIÓN:");
        titulo62.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo62, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 90, 30));

        Label_mvtoEquipo_numeroOrden.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Label_mvtoEquipo_numeroOrden.setForeground(new java.awt.Color(51, 51, 51));
        Label_mvtoEquipo_numeroOrden.setText("NÚMERO ORDEN:");
        Label_mvtoEquipo_numeroOrden.setPreferredSize(new java.awt.Dimension(133, 15));
        add(Label_mvtoEquipo_numeroOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 140, 30));

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
        add(select_MvtoEquipo_marcaEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 360, 350, 30));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        jButton2.setText("CANCELAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 720, -1, 50));

        Label_mvtoEquipo_marcaEquipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Label_mvtoEquipo_marcaEquipo.setForeground(new java.awt.Color(51, 51, 51));
        Label_mvtoEquipo_marcaEquipo.setText("MARCA DE EQUIPO:");
        Label_mvtoEquipo_marcaEquipo.setPreferredSize(new java.awt.Dimension(133, 15));
        add(Label_mvtoEquipo_marcaEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 360, 130, 30));

        Label_mvtoEquipo_cntroCostoAuxiliarDestino.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Label_mvtoEquipo_cntroCostoAuxiliarDestino.setForeground(new java.awt.Color(51, 51, 51));
        Label_mvtoEquipo_cntroCostoAuxiliarDestino.setText("SITIO DESTINO:");
        Label_mvtoEquipo_cntroCostoAuxiliarDestino.setPreferredSize(new java.awt.Dimension(133, 15));
        add(Label_mvtoEquipo_cntroCostoAuxiliarDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, 170, 30));

        select_MvtoEquipo_CCAuxiliarDestino.setToolTipText("");
        add(select_MvtoEquipo_CCAuxiliarDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 320, 350, 30));

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
        add(select_MvtoEquipo_laborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 300, 30));

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
        add(select_MvtoEquipo_tipoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 360, 300, 30));

        Label_mvtoEquipo_tipoEquipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Label_mvtoEquipo_tipoEquipo.setForeground(new java.awt.Color(51, 51, 51));
        Label_mvtoEquipo_tipoEquipo.setText("TIPO DE EQUIPO:");
        Label_mvtoEquipo_tipoEquipo.setPreferredSize(new java.awt.Dimension(133, 15));
        add(Label_mvtoEquipo_tipoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 120, 30));

        Label_mvtoEquipo_LaborRealizada.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Label_mvtoEquipo_LaborRealizada.setForeground(new java.awt.Color(51, 51, 51));
        Label_mvtoEquipo_LaborRealizada.setText("LABOR A REALIZAR:");
        Label_mvtoEquipo_LaborRealizada.setPreferredSize(new java.awt.Dimension(133, 15));
        add(Label_mvtoEquipo_LaborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 170, 30));

        Label_mvtoEquipo_articulo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Label_mvtoEquipo_articulo.setForeground(new java.awt.Color(51, 51, 51));
        Label_mvtoEquipo_articulo.setText("ARTICULO");
        Label_mvtoEquipo_articulo.setPreferredSize(new java.awt.Dimension(133, 15));
        add(Label_mvtoEquipo_articulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 200, 130, 30));

        select_MvtoEquipo_SubcentroCosto.setToolTipText("");
        select_MvtoEquipo_SubcentroCosto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoEquipo_SubcentroCostoItemStateChanged(evt);
            }
        });
        add(select_MvtoEquipo_SubcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 300, 30));

        Label_mvtoEquipo_cntroCostoAuxiliarOrigen.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Label_mvtoEquipo_cntroCostoAuxiliarOrigen.setForeground(new java.awt.Color(51, 51, 51));
        Label_mvtoEquipo_cntroCostoAuxiliarOrigen.setText("SITIO ORIGEN:");
        Label_mvtoEquipo_cntroCostoAuxiliarOrigen.setPreferredSize(new java.awt.Dimension(133, 15));
        add(Label_mvtoEquipo_cntroCostoAuxiliarOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 280, 130, 30));

        select_MvtoEquipo_CCAuxiliar.setToolTipText("");
        add(select_MvtoEquipo_CCAuxiliar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 280, 350, 30));

        select_MvtoEquipo_CO.setToolTipText("");
        select_MvtoEquipo_CO.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoEquipo_COItemStateChanged(evt);
            }
        });
        add(select_MvtoEquipo_CO, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 240, 350, 30));

        Label_mvtoEquipo_cntroOperacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Label_mvtoEquipo_cntroOperacion.setForeground(new java.awt.Color(51, 51, 51));
        Label_mvtoEquipo_cntroOperacion.setText("CENTRO OPERACIÓN:");
        Label_mvtoEquipo_cntroOperacion.setPreferredSize(new java.awt.Dimension(133, 15));
        add(Label_mvtoEquipo_cntroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 240, 140, 30));

        label_articulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_articulo.setForeground(new java.awt.Color(51, 51, 51));
        label_articulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(label_articulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 200, 340, 30));

        icon_buscarArticulo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        icon_buscarArticulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        icon_buscarArticulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_buscarArticuloMouseClicked(evt);
            }
        });
        add(icon_buscarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, 30, 30));

        label_motonave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_motonave.setForeground(new java.awt.Color(51, 51, 51));
        label_motonave.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(label_motonave, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 300, 30));

        label_clientes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_clientes.setForeground(new java.awt.Color(51, 51, 51));
        label_clientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(label_clientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 300, 30));

        icon_buscarClientes.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        icon_buscarClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        icon_buscarClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_buscarClientesMouseClicked(evt);
            }
        });
        add(icon_buscarClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 30, 30));

        icon_buscarMotonave.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        icon_buscarMotonave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        icon_buscarMotonave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_buscarMotonaveMouseClicked(evt);
            }
        });
        add(icon_buscarMotonave, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 30, 30));

        fechaFinActividad_MvtoEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFinActividad_MvtoEquipoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFinActividad_MvtoEquipoMouseEntered(evt);
            }
        });
        add(fechaFinActividad_MvtoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 170, 30));

        label_MvtoEquipo_HoraFinal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_MvtoEquipo_HoraFinal.setForeground(new java.awt.Color(51, 51, 51));
        label_MvtoEquipo_HoraFinal.setText("Hora");
        add(label_MvtoEquipo_HoraFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 80, -1, 30));

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
        add(select_MvtoEquipo_HoraFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 50, 30));

        label_MvtoEquipo_separadorFinal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        label_MvtoEquipo_separadorFinal.setText(":");
        add(label_MvtoEquipo_separadorFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 80, 10, 30));

        select_MvtoEquipo_MinutosFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_MinutosFinalActionPerformed(evt);
            }
        });
        add(select_MvtoEquipo_MinutosFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 80, 50, 30));

        label_MvtoEquipo_ZonaHorariaFinal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        label_MvtoEquipo_ZonaHorariaFinal.setForeground(new java.awt.Color(102, 102, 102));
        label_MvtoEquipo_ZonaHorariaFinal.setText("AM");
        add(label_MvtoEquipo_ZonaHorariaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 80, 50, 30));

        label_MvtoEquipo_ZonaHorariaInicial.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        label_MvtoEquipo_ZonaHorariaInicial.setForeground(new java.awt.Color(102, 102, 102));
        label_MvtoEquipo_ZonaHorariaInicial.setText("AM");
        add(label_MvtoEquipo_ZonaHorariaInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 50, 30));

        select_MvtoEquipo_MinutosInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_MinutosInicialActionPerformed(evt);
            }
        });
        add(select_MvtoEquipo_MinutosInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 50, 30));

        label_MvtoEquipo_separadorInicial.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        label_MvtoEquipo_separadorInicial.setText(":");
        add(label_MvtoEquipo_separadorInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 10, 30));

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
        add(select_MvtoEquipo_HoraInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 50, 30));

        label_MvtoEquipo_HoraInicial.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_MvtoEquipo_HoraInicial.setForeground(new java.awt.Color(51, 51, 51));
        label_MvtoEquipo_HoraInicial.setText("Hora");
        add(label_MvtoEquipo_HoraInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, -1, 30));

        fechaInicialActividad_MvtoEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicialActividad_MvtoEquipoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicialActividad_MvtoEquipoMouseEntered(evt);
            }
        });
        add(fechaInicialActividad_MvtoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 170, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 153, 153));
        jLabel17.setText("AGREGAR MVTO EQUIPO");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 230, 30));

        Label_mvtoEquipo_equipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Label_mvtoEquipo_equipo.setForeground(new java.awt.Color(51, 51, 51));
        Label_mvtoEquipo_equipo.setText("EQUIPO:");
        Label_mvtoEquipo_equipo.setPreferredSize(new java.awt.Dimension(133, 15));
        add(Label_mvtoEquipo_equipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 120, 30));

        titulo64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo64.setForeground(new java.awt.Color(51, 51, 51));
        titulo64.setText("LAPSO DE TIEMPO DE LA ACTIVIDAD:");
        titulo64.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo64, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 320, 20));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menu_programacion.png"))); // NOI18N
        jButton1.setText("CARGAR PROGRAMACIÓN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 80, 240, 40));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 1230, 10));

        Label_mvtoEquipo_subCentroCosto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Label_mvtoEquipo_subCentroCosto.setForeground(new java.awt.Color(51, 51, 51));
        Label_mvtoEquipo_subCentroCosto.setText("SUBCENTRO COSTO:");
        Label_mvtoEquipo_subCentroCosto.setPreferredSize(new java.awt.Dimension(133, 15));
        add(Label_mvtoEquipo_subCentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 130, 30));

        Label_mvtoEquipo_usuarioQuienCierra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Label_mvtoEquipo_usuarioQuienCierra.setForeground(new java.awt.Color(51, 51, 51));
        Label_mvtoEquipo_usuarioQuienCierra.setText("USUARIO QUIEN CIERRA ACTIVIDAD:");
        Label_mvtoEquipo_usuarioQuienCierra.setPreferredSize(new java.awt.Dimension(133, 15));
        add(Label_mvtoEquipo_usuarioQuienCierra, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 260, 30));

        Label_mvtoEquipo_motonave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Label_mvtoEquipo_motonave.setForeground(new java.awt.Color(51, 51, 51));
        Label_mvtoEquipo_motonave.setText("MOTONAVE");
        Label_mvtoEquipo_motonave.setPreferredSize(new java.awt.Dimension(133, 15));
        add(Label_mvtoEquipo_motonave, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 100, 30));

        Label_mvtoEquipo_cliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Label_mvtoEquipo_cliente.setForeground(new java.awt.Color(51, 51, 51));
        Label_mvtoEquipo_cliente.setText("CLIENTE");
        Label_mvtoEquipo_cliente.setPreferredSize(new java.awt.Dimension(133, 15));
        add(Label_mvtoEquipo_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 100, 30));

        Label_mvtoEquipo_usuarioQuienInicia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Label_mvtoEquipo_usuarioQuienInicia.setForeground(new java.awt.Color(51, 51, 51));
        Label_mvtoEquipo_usuarioQuienInicia.setText("USUARIO QUIEN  INICIA ACTIVIDAD:");
        Label_mvtoEquipo_usuarioQuienInicia.setPreferredSize(new java.awt.Dimension(133, 15));
        add(Label_mvtoEquipo_usuarioQuienInicia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 260, 30));

        titulo65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo65.setForeground(new java.awt.Color(51, 51, 51));
        titulo65.setText("INICIO:");
        titulo65.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo65, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 90, 30));

        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1230, 90));

        Label_mvtoEquipo_recobro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Label_mvtoEquipo_recobro.setForeground(new java.awt.Color(51, 51, 51));
        Label_mvtoEquipo_recobro.setText("RECOBRO:");
        Label_mvtoEquipo_recobro.setPreferredSize(new java.awt.Dimension(133, 15));
        add(Label_mvtoEquipo_recobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 440, 70, 30));

        select_MvtoEquipo_recobro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoEquipo_recobroItemStateChanged(evt);
            }
        });
        select_MvtoEquipo_recobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_recobroActionPerformed(evt);
            }
        });
        add(select_MvtoEquipo_recobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 440, 60, 30));

        Label_mvtoEquipo_autorizadoPor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Label_mvtoEquipo_autorizadoPor.setForeground(new java.awt.Color(51, 51, 51));
        Label_mvtoEquipo_autorizadoPor.setText("AUTORIZADO POR:");
        Label_mvtoEquipo_autorizadoPor.setPreferredSize(new java.awt.Dimension(133, 15));
        add(Label_mvtoEquipo_autorizadoPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 440, 120, 30));

        select_MvtoEquipo_UsuarioQuienAutorizaRecobro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoEquipo_UsuarioQuienAutorizaRecobroItemStateChanged(evt);
            }
        });
        select_MvtoEquipo_UsuarioQuienAutorizaRecobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_UsuarioQuienAutorizaRecobroActionPerformed(evt);
            }
        });
        add(select_MvtoEquipo_UsuarioQuienAutorizaRecobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 440, 370, 30));

        fechaHoraFinalActividad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fechaHoraFinalActividad.setForeground(new java.awt.Color(255, 0, 51));
        fechaHoraFinalActividad.setText("...........................................................................");
        add(fechaHoraFinalActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 150, 310, 20));

        fechaHoraInicioActividad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fechaHoraInicioActividad.setForeground(new java.awt.Color(255, 0, 51));
        fechaHoraInicioActividad.setText(".....................................................");
        add(fechaHoraInicioActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 240, 20));

        fechaHoraInicioActividadS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fechaHoraInicioActividadS.setText("FECHA-HORA INICIO ACTIVIDAD:");
        add(fechaHoraInicioActividadS, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 210, 20));

        fechaHoraFinalActividadS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fechaHoraFinalActividadS.setText("FECHA-HORA FIN ACTIVIDAD:");
        add(fechaHoraFinalActividadS, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 150, 190, 20));

        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 1230, 640));
    }// </editor-fold>//GEN-END:initComponents

    private void Agregar_MvtoEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Agregar_MvtoEquipoActionPerformed
        if(mvtoEquipo != null){
            try{
                if(listadoCentroOperacion_mvtoEquipo != null){
                    if(listadoCentroCostoSubCentro_mvtoEquipo != null){
                        if(listadoCentroCostoAuxiliar_mvtoEquipo != null){
                            if(listadoLaborRealizada_mvtoEquipo != null){
                                if(listadoEquipos_mvtoEquipo != null){
                                    if(listadoRecobros_mvtoEquipo!= null){
                                        if(listadoMotivoParada_mvtoEquipo != null){
                                            if(listadoUsuarioInicioRegistro_mvtoEquipo != null){
                                                if(listadoUsuarioCierraRegistro_mvtoEquipo != null){
                                                    mvtoEquipo.setAsignacionEquipo(listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()));
                                                    //mvtoEquipo.setFechaRegistro("");
                                                    mvtoEquipo.setProveedorEquipo(listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getEquipo().getProveedorEquipo());
                                                    mvtoEquipo.setNumeroOrden(mvtoEquipo_numOrden.getText());
                                                    mvtoEquipo.setCentroOperacion(listadoCentroOperacion_mvtoEquipo.get(select_MvtoEquipo_CO.getSelectedIndex()));
                                                    mvtoEquipo.setCentroCostoAuxiliar(listadoCentroCostoAuxiliar_mvtoEquipo.get(select_MvtoEquipo_CCAuxiliar.getSelectedIndex()));
                                                    mvtoEquipo.setLaborRealizada(listadoLaborRealizada_mvtoEquipo.get(select_MvtoEquipo_laborRealizada.getSelectedIndex()));
                                                    mvtoEquipo.setMotonave(motonave);
                                                    mvtoEquipo.setCliente(cliente);
                                                    mvtoEquipo.setArticulo(articulo);
                                                    if(listadoCentroCostoAuxiliarDestino_mvtoEquipo != null){
                                                        mvtoEquipo.setCentroCostoAuxiliarDestino(listadoCentroCostoAuxiliarDestino_mvtoEquipo.get(select_MvtoEquipo_CCAuxiliarDestino.getSelectedIndex()));  
                                                    }else{
                                                        mvtoEquipo.setCentroCostoAuxiliarDestino(null);
                                                    }
                                                    mvtoEquipo.setRecobro(listadoRecobros_mvtoEquipo.get(select_MvtoEquipo_recobro.getSelectedIndex()));
                                                    mvtoEquipo.setMotivoParada(listadoMotivoParada_mvtoEquipo.get(mvtoEquipo_selectRazonFinalzación.getSelectedIndex()));
                                                    mvtoEquipo.setUsuarioQuieRegistra(listadoUsuarioInicioRegistro_mvtoEquipo.get(select_MvtoEquipo_UsuarioIniciaRegistro.getSelectedIndex()));
                                                    mvtoEquipo.setUsuarioQuienCierra(listadoUsuarioCierraRegistro_mvtoEquipo.get(select_MvtoEquipo_UsuarioIniciaRegistro.getSelectedIndex()));
                                                    mvtoEquipo.setObservacionMvtoEquipo(textArea_MvtoEquipo_Observacion.getText());
                                                    
                                                    if(listadoRecobros_mvtoEquipo.get(select_MvtoEquipo_recobro.getSelectedIndex()).getCodigo().equals("1")){//Seleccionó SI
                                                        mvtoEquipo.setUsuarioAutorizaRecobro(listadoUsuarioQuienAutorizaRecobro.get(select_MvtoEquipo_UsuarioQuienAutorizaRecobro.getSelectedIndex()));
                                                    }else{//Seleccionó NO
                                                       // Usuario usuarioAutorizaRecobro = new Usuario();
                                                        mvtoEquipo.setUsuarioAutorizaRecobro(null);
                                                    }
                                                    mvtoEquipo.setUsuarioQuieRegistra(listadoUsuarioInicioRegistro_mvtoEquipo.get(select_MvtoEquipo_UsuarioIniciaRegistro.getSelectedIndex()));
                                                    mvtoEquipo.setUsuarioQuienCierra(listadoUsuarioCierraRegistro_mvtoEquipo.get(select_MvtoEquipo_UsuarioCierraRegistro.getSelectedIndex()));
                                                    mvtoEquipo.setMotivoParada(listadoMotivoParada_mvtoEquipo.get(mvtoEquipo_selectRazonFinalzación.getSelectedIndex()));
                                                    mvtoEquipo.setEstado("1");
                                                    mvtoEquipo.setDesdeCarbon("0");
                                                    //mvtoEquipo.setUsuarioQuienCierra(user);
                                                    
                                                    int retorno =0;
                                                    try {
                                                        retorno= new ControlDB_MvtoEquipo(tipoConexion).registrarMvtoEquipo( user, mvtoEquipo);
                                                        if(retorno==1){
                                                            JOptionPane.showMessageDialog(null, "Registro exitoso","Registrado", JOptionPane.INFORMATION_MESSAGE);
                                                            titulo64.show(true);
                                                            titulo65.show(true);
                                                            titulo62.show(true);
                                                            select_MvtoEquipo_HoraInicial.show(true);
                                                            select_MvtoEquipo_HoraFinal.show(true);
                                                            fechaInicialActividad_MvtoEquipo.show(true);
                                                            fechaFinActividad_MvtoEquipo.show(true);
                                                            label_MvtoEquipo_HoraInicial.show(true);
                                                            label_MvtoEquipo_HoraFinal.show(true);
                                                            label_MvtoEquipo_separadorInicial.show(true);
                                                            label_MvtoEquipo_separadorFinal.show(true);
                                                            select_MvtoEquipo_MinutosInicial.show(true);
                                                            select_MvtoEquipo_MinutosFinal.show(true);
                                                            label_MvtoEquipo_ZonaHorariaInicial.show(true);
                                                            label_MvtoEquipo_ZonaHorariaFinal.show(true);
                                                            jButton1.show(true);
                                                            
                                                            
                                                            Label_mvtoEquipo_cliente.show(false);
                                                            Label_mvtoEquipo_motonave.show(false);
                                                            Label_mvtoEquipo_subCentroCosto.show(false);
                                                            Label_mvtoEquipo_LaborRealizada.show(false);
                                                            Label_mvtoEquipo_tipoEquipo.show(false);
                                                            Label_mvtoEquipo_equipo.show(false);
                                                            Label_mvtoEquipo_numeroOrden.show(false);
                                                            Label_mvtoEquipo_razonFinalizacion.show(false);
                                                            Label_mvtoEquipo_usuarioQuienInicia.show(false);
                                                            Label_mvtoEquipo_usuarioQuienCierra.show(false);
                                                            Label_mvtoEquipo_ObservaciónActividad.show(false);
                                                            Label_mvtoEquipo_articulo.show(false);
                                                            Label_mvtoEquipo_cntroOperacion.show(false);
                                                            Label_mvtoEquipo_cntroCostoAuxiliarOrigen.show(false);
                                                            Label_mvtoEquipo_cntroCostoAuxiliarDestino.show(false);
                                                            Label_mvtoEquipo_marcaEquipo.show(false);
                                                            Label_mvtoEquipo_recobro.show(false);
                                                            Label_mvtoEquipo_autorizadoPor.show(false);

                                                            icon_buscarClientes.show(false);
                                                            icon_buscarMotonave.show(false);
                                                            icon_buscarArticulo.show(false);


                                                            label_clientes.show(false);
                                                            label_motonave.show(false);
                                                            select_MvtoEquipo_SubcentroCosto.show(false);
                                                            select_MvtoEquipo_laborRealizada.show(false);
                                                            select_MvtoEquipo_tipoEquipo.show(false);
                                                            select_MvtoEquipo_Equipo.show(false);
                                                            mvtoEquipo_numOrden.show(false);
                                                            mvtoEquipo_selectRazonFinalzación.show(false);
                                                            select_MvtoEquipo_UsuarioIniciaRegistro.show(false);
                                                            select_MvtoEquipo_UsuarioCierraRegistro.show(false);
                                                            textArea_MvtoEquipo_Observacion.show(false);
                                                            label_articulo.show(false);
                                                            select_MvtoEquipo_CO.show(false);
                                                            select_MvtoEquipo_CCAuxiliar.show(false);
                                                            select_MvtoEquipo_CCAuxiliarDestino.show(false);
                                                            select_MvtoEquipo_marcaEquipo.show(false);
                                                            select_MvtoEquipo_recobro.show(false);
                                                            select_MvtoEquipo_UsuarioQuienAutorizaRecobro.show(false);
                                                            Agregar_MvtoEquipo.show(false);
                                                            jButton2.show(false);
                                                            
                                                            fechaHoraInicioActividad.show(false);
                                                            fechaHoraFinalActividad.show(false);
                                                            fechaHoraInicioActividadS.show(false);
                                                            fechaHoraFinalActividadS.show(false);
                                                        }
                                                    }catch(Exception e){
                                                         JOptionPane.showMessageDialog(null, "No se puedo hacer el registro del Mvto Equipo","Error!", JOptionPane.INFORMATION_MESSAGE);
                                                        e.printStackTrace();
                                                    }
                                                }else{
                                                    JOptionPane.showMessageDialog(null,"Error!!.. no se encuentra usuario de cierre de actividad activos en el sistema, consulte con el administrador del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                                                }
                                            }else{
                                                JOptionPane.showMessageDialog(null,"Error!!.. no se encuentra usuario de inicio de actividad activos en el sistema, consulte con el administrador del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                                            }
                                        }else{
                                            JOptionPane.showMessageDialog(null,"Error!!.. no se encuentra motivos de finalización activos, consulte con el administrador del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(null,"Error!!.. no se pudo cargar la lista de recobros, consulte con el administrador del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(null,"Error!!.. no se encuentra equipos programados en ese lapso de tiempo, consulte con el administrador del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                                }
                            }else{
                                JOptionPane.showMessageDialog(null,"Error!!.. no se encuentra una labor realizada activa en el sistema, consulte con el administrador del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Error!!.. no se encuentra un centro de costo auxiliar activo en el sistema, consulte con el administrador del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Error!!.. no se encuentra un subcentro de costo activo en el sistema, consulte con el administrador del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Error!!.. no se encuentra un Centro de Operación activo en el sistema, consulte con el administrador del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(null,"Error!!.. Debe cargar programación","Advertencia",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_Agregar_MvtoEquipoActionPerformed

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

    private void mvtoEquipo_selectRazonFinalzaciónItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mvtoEquipo_selectRazonFinalzaciónItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_mvtoEquipo_selectRazonFinalzaciónItemStateChanged

    private void select_MvtoEquipo_EquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_EquipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_EquipoActionPerformed

    private void select_MvtoEquipo_marcaEquipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_marcaEquipoItemStateChanged
        if(mvtoEquipo !=null){
            if(listadoTipoEquipos_mvtoEquipo !=null && listadoMarcaEquipos_mvtoEquipo !=null){
                try {
                    //Cargamos los equipos disponible en el rango de fecha.
                    listadoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarAsignacionEquipos_Por_TipoEquipo_MarcaEquipo(mvtoEquipo.getFechaHoraInicio(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion(),listadoMarcaEquipos_mvtoEquipo.get(select_MvtoEquipo_marcaEquipo.getSelectedIndex()));
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
                    listadoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarAsignacionEquipos_Por_TipoEquipo_MarcaEquipo(mvtoEquipo.getFechaHoraInicio(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion(),listadoMarcaEquipos_mvtoEquipo.get(select_MvtoEquipo_marcaEquipo.getSelectedIndex()));
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

    private void select_MvtoEquipo_laborRealizadaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_laborRealizadaItemStateChanged
       if(listadoLaborRealizada_mvtoEquipo != null){
            if(listadoLaborRealizada_mvtoEquipo.get(select_MvtoEquipo_laborRealizada.getSelectedIndex()).getBodegaDestino().equals("1")){//Fue seleccionada una labor Realizada que tiene marcada Bodega destino por tal motivo cargamos las Bodega destino
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


                    /*//cargamos los motivos de parada según la consulta
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
                    }*/
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

                        /*  //cargamos los motivos de parada según la consulta
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
                        }*/
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

    private void select_MvtoEquipo_laborRealizadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_laborRealizadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_laborRealizadaActionPerformed

    private void select_MvtoEquipo_tipoEquipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_tipoEquipoItemStateChanged
        if(mvtoEquipo !=null){
            //Cargamos las marcas de equipos disponible en el rango de fecha.
            if(listadoTipoEquipos_mvtoEquipo !=null){
                try {
                    listadoMarcaEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarMarcaEquiposProgramados(mvtoEquipo.getFechaHoraInicio(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion());
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
                    listadoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarAsignacionEquipos_Por_TipoEquipo_MarcaEquipo(mvtoEquipo.getFechaHoraInicio(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion(),listadoMarcaEquipos_mvtoEquipo.get(select_MvtoEquipo_marcaEquipo.getSelectedIndex()));
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
                    listadoMarcaEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarMarcaEquiposProgramados(mvtoEquipo.getFechaHoraInicio(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion());
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
                    listadoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarAsignacionEquipos_Por_TipoEquipo_MarcaEquipo(mvtoEquipo.getFechaHoraInicio(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion(),listadoMarcaEquipos_mvtoEquipo.get(select_MvtoEquipo_marcaEquipo.getSelectedIndex()));
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

    private void icon_buscarArticuloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_buscarArticuloMouseClicked
        InternaFrame_buscarArticulo.show(true);
    }//GEN-LAST:event_icon_buscarArticuloMouseClicked

    private void icon_buscarClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_buscarClientesMouseClicked
        InternaFrame_buscarCliente.show(true);
    }//GEN-LAST:event_icon_buscarClientesMouseClicked

    private void icon_buscarMotonaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_buscarMotonaveMouseClicked
        InternaFrame_buscarMotonave.show(true);
        //InternaFrame_buscarMotonave.repaint();
    }//GEN-LAST:event_icon_buscarMotonaveMouseClicked

    private void fechaFinActividad_MvtoEquipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFinActividad_MvtoEquipoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFinActividad_MvtoEquipoMouseClicked

    private void fechaFinActividad_MvtoEquipoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFinActividad_MvtoEquipoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFinActividad_MvtoEquipoMouseEntered

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

    private void select_MvtoEquipo_HoraInicialItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_HoraInicialItemStateChanged
        if(select_MvtoEquipo_HoraInicial.getSelectedIndex()<= 11){
            label_MvtoEquipo_ZonaHorariaInicial.setText("AM");
        }else{
            label_MvtoEquipo_ZonaHorariaInicial.setText("PM");
        }
    }//GEN-LAST:event_select_MvtoEquipo_HoraInicialItemStateChanged

    private void select_MvtoEquipo_HoraInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_HoraInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_HoraInicialActionPerformed

    private void fechaInicialActividad_MvtoEquipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicialActividad_MvtoEquipoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaInicialActividad_MvtoEquipoMouseClicked

    private void fechaInicialActividad_MvtoEquipoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicialActividad_MvtoEquipoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaInicialActividad_MvtoEquipoMouseEntered

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //boolean validarCampos= true;
        String fechaInicio_Actividad="";
        String fechaFinalizacion_Actividad="";
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
                    //validarCampos=false;
                    JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser mayor a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                }else{
                    if(resultDosFechas ==0){
                        //validarCampos=false;
                        JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser Igual a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                    }else{
                        mvtoEquipo= new MvtoEquipo();
                        mvtoEquipo.setFechaHoraInicio(fechaInicio_Actividad);
                        mvtoEquipo.setFechaHoraFin(fechaFinalizacion_Actividad);
                        
                        //Habilitamos todo
                        //Cargamos los equipos disponible en el rango de fecha.
                            listadoTipoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarTipoEquiposProgramados(mvtoEquipo.getFechaHoraInicio());
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
                            listadoMarcaEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarMarcaEquiposProgramados(mvtoEquipo.getFechaHoraInicio(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion());
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
                            listadoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarAsignacionEquipos_Por_TipoEquipo_MarcaEquipo(mvtoEquipo.getFechaHoraInicio(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion(),listadoMarcaEquipos_mvtoEquipo.get(select_MvtoEquipo_marcaEquipo.getSelectedIndex()));
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
                        titulo64.show(false);
                        titulo65.show(false);
                        titulo62.show(false);
                        select_MvtoEquipo_HoraInicial.show(false);
                        select_MvtoEquipo_HoraFinal.show(false);
                        fechaInicialActividad_MvtoEquipo.show(false);
                        fechaFinActividad_MvtoEquipo.show(false);
                        label_MvtoEquipo_HoraInicial.show(false);
                        label_MvtoEquipo_HoraFinal.show(false);
                        label_MvtoEquipo_separadorInicial.show(false);
                        label_MvtoEquipo_separadorFinal.show(false);
                        select_MvtoEquipo_MinutosInicial.show(false);
                        select_MvtoEquipo_MinutosFinal.show(false);
                        label_MvtoEquipo_ZonaHorariaInicial.show(false);
                        label_MvtoEquipo_ZonaHorariaFinal.show(false);
                        jButton1.show(false);
        
        
                        Label_mvtoEquipo_cliente.show(true);
                        Label_mvtoEquipo_motonave.show(true);
                        Label_mvtoEquipo_subCentroCosto.show(true);
                        Label_mvtoEquipo_LaborRealizada.show(true);
                        Label_mvtoEquipo_tipoEquipo.show(true);
                        Label_mvtoEquipo_equipo.show(true);
                        Label_mvtoEquipo_numeroOrden.show(true);
                        Label_mvtoEquipo_razonFinalizacion.show(true);
                        Label_mvtoEquipo_usuarioQuienInicia.show(true);
                        Label_mvtoEquipo_usuarioQuienCierra.show(true);
                        Label_mvtoEquipo_ObservaciónActividad.show(true);
                        Label_mvtoEquipo_articulo.show(true);
                        Label_mvtoEquipo_cntroOperacion.show(true);
                        Label_mvtoEquipo_cntroCostoAuxiliarOrigen.show(true);
                        Label_mvtoEquipo_cntroCostoAuxiliarDestino.show(true);
                        Label_mvtoEquipo_marcaEquipo.show(true);
                        Label_mvtoEquipo_recobro.show(true);
                        Label_mvtoEquipo_autorizadoPor.show(true);

                        icon_buscarClientes.show(true);
                        icon_buscarMotonave.show(true);
                        icon_buscarArticulo.show(true);


                        label_clientes.show(true);
                        label_motonave.show(true);
                        select_MvtoEquipo_SubcentroCosto.show(true);
                        select_MvtoEquipo_laborRealizada.show(true);
                        select_MvtoEquipo_tipoEquipo.show(true);
                        select_MvtoEquipo_Equipo.show(true);
                        mvtoEquipo_numOrden.show(true);
                        mvtoEquipo_selectRazonFinalzación.show(true);
                        select_MvtoEquipo_UsuarioIniciaRegistro.show(true);
                        select_MvtoEquipo_UsuarioCierraRegistro.show(true);
                        textArea_MvtoEquipo_Observacion.show(true);
                        label_articulo.show(true);
                        select_MvtoEquipo_CO.show(true);
                        select_MvtoEquipo_CCAuxiliar.show(true);
                        select_MvtoEquipo_CCAuxiliarDestino.show(true);
                        select_MvtoEquipo_marcaEquipo.show(true);
                        select_MvtoEquipo_recobro.show(true);
                        select_MvtoEquipo_UsuarioQuienAutorizaRecobro.show(true);
                        Agregar_MvtoEquipo.show(true);
                        jButton2.show(true);
                        
                        fechaHoraInicioActividad.setText(fechaInicio_Actividad);
                        fechaHoraFinalActividad.setText(fechaFinalizacion_Actividad);
                        fechaHoraInicioActividad.show(true);
                        fechaHoraFinalActividad.show(true);
                        fechaHoraInicioActividadS.show(true);
                        fechaHoraFinalActividadS.show(true);
                        
                        select_MvtoEquipo_recobro.setSelectedIndex(0);
                        
                    }
                }
            }catch(Exception e){
                //validarCampos=false;
                JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la fecha de finalización","Advertencia",JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            //validarCampos=false;
            JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la fecha de inicio de la actividad","Advertencia",JOptionPane.ERROR_MESSAGE);
        }                        
    
                                
    }//GEN-LAST:event_jButton1ActionPerformed

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
            DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "Nombre","Estado", "BaseDatos"});
            listadoMotonave=new ControlDB_Motonave(tipoConexion).buscar(valorBusqueda.getText());
            for(Motonave listadoObjetos1:listadoMotonave){
                String []registro = new String[4];
                registro[0]=""+listadoObjetos1.getCodigo();
                registro[1]=""+listadoObjetos1.getDescripcion();
                registro[2]=""+listadoObjetos1.getEstado();
                registro[3]=""+listadoObjetos1.getBaseDatos().getDescripcion();
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
            DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "Nombre","Estado","BaseDatos"});
            listadoArticulos=new ControlDB_Articulo(tipoConexion).buscarActivosConParametros(valorBusqueda.getText());
            for(Articulo listadoObjetos1:listadoArticulos){
                String []registro = new String[4];
                registro[0]=""+listadoObjetos1.getCodigo();
                registro[1]=""+listadoObjetos1.getDescripcion();
                registro[2]=""+listadoObjetos1.getEstado();
                registro[3]=""+listadoObjetos1.getBaseDatos().getDescripcion();
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
            DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "Nombre","Estado","BaseDatos"});
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
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarClienteActionPerformed

    private void btnLimpiarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarClienteActionPerformed

    private void mvtoEquipo_selectRazonFinalzaciónCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_mvtoEquipo_selectRazonFinalzaciónCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_mvtoEquipo_selectRazonFinalzaciónCaretPositionChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        titulo64.show(true);
        titulo65.show(true);
        titulo62.show(true);
        select_MvtoEquipo_HoraInicial.show(true);
        select_MvtoEquipo_HoraFinal.show(true);
        fechaInicialActividad_MvtoEquipo.show(true);
        fechaFinActividad_MvtoEquipo.show(true);
        label_MvtoEquipo_HoraInicial.show(true);
        label_MvtoEquipo_HoraFinal.show(true);
        label_MvtoEquipo_separadorInicial.show(true);
        label_MvtoEquipo_separadorFinal.show(true);
        select_MvtoEquipo_MinutosInicial.show(true);
        select_MvtoEquipo_MinutosFinal.show(true);
        label_MvtoEquipo_ZonaHorariaInicial.show(true);
        label_MvtoEquipo_ZonaHorariaFinal.show(true);
        jButton1.show(true);


        Label_mvtoEquipo_cliente.show(false);
        Label_mvtoEquipo_motonave.show(false);
        Label_mvtoEquipo_subCentroCosto.show(false);
        Label_mvtoEquipo_LaborRealizada.show(false);
        Label_mvtoEquipo_tipoEquipo.show(false);
        Label_mvtoEquipo_equipo.show(false);
        Label_mvtoEquipo_numeroOrden.show(false);
        Label_mvtoEquipo_razonFinalizacion.show(false);
        Label_mvtoEquipo_usuarioQuienInicia.show(false);
        Label_mvtoEquipo_usuarioQuienCierra.show(false);
        Label_mvtoEquipo_ObservaciónActividad.show(false);
        Label_mvtoEquipo_articulo.show(false);
        Label_mvtoEquipo_cntroOperacion.show(false);
        Label_mvtoEquipo_cntroCostoAuxiliarOrigen.show(false);
        Label_mvtoEquipo_cntroCostoAuxiliarDestino.show(false);
        Label_mvtoEquipo_marcaEquipo.show(false);
        Label_mvtoEquipo_recobro.show(false);
        Label_mvtoEquipo_autorizadoPor.show(false);

        icon_buscarClientes.show(false);
        icon_buscarMotonave.show(false);
        icon_buscarArticulo.show(false);


        label_clientes.show(false);
        label_motonave.show(false);
        select_MvtoEquipo_SubcentroCosto.show(false);
        select_MvtoEquipo_laborRealizada.show(false);
        select_MvtoEquipo_tipoEquipo.show(false);
        select_MvtoEquipo_Equipo.show(false);
        mvtoEquipo_numOrden.show(false);
        mvtoEquipo_selectRazonFinalzación.show(false);
        select_MvtoEquipo_UsuarioIniciaRegistro.show(false);
        select_MvtoEquipo_UsuarioCierraRegistro.show(false);
        textArea_MvtoEquipo_Observacion.show(false);
        label_articulo.show(false);
        select_MvtoEquipo_CO.show(false);
        select_MvtoEquipo_CCAuxiliar.show(false);
        select_MvtoEquipo_CCAuxiliarDestino.show(false);
        select_MvtoEquipo_marcaEquipo.show(false);
        select_MvtoEquipo_recobro.show(false);
        select_MvtoEquipo_UsuarioQuienAutorizaRecobro.show(false);
        Agregar_MvtoEquipo.show(false);
        jButton2.show(false);
        
        fechaHoraInicioActividad.show(false);
        fechaHoraFinalActividad.show(false);
        fechaHoraInicioActividadS.show(false);
        fechaHoraFinalActividadS.show(false);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void select_MvtoEquipo_recobroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_recobroItemStateChanged
        if(listadoRecobros_mvtoEquipo.get(select_MvtoEquipo_recobro.getSelectedIndex()).getCodigo().equals("1")){//Seleccionó SI al recobro
            Label_mvtoEquipo_autorizadoPor.show(true);
            select_MvtoEquipo_UsuarioQuienAutorizaRecobro.show(true);
        }else{
            if(listadoRecobros_mvtoEquipo.get(select_MvtoEquipo_recobro.getSelectedIndex()).getCodigo().equals("0")){//Seleccionó NO al recobro
                Label_mvtoEquipo_autorizadoPor.show(false);
                select_MvtoEquipo_UsuarioQuienAutorizaRecobro.show(false);
            }   
        }
    }//GEN-LAST:event_select_MvtoEquipo_recobroItemStateChanged

    private void select_MvtoEquipo_recobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_recobroActionPerformed
        if(listadoRecobros_mvtoEquipo.get(select_MvtoEquipo_recobro.getSelectedIndex()).getCodigo().equals("1")){//Seleccionó SI al recobro
            Label_mvtoEquipo_autorizadoPor.show(true);
            select_MvtoEquipo_UsuarioQuienAutorizaRecobro.show(true);
        }else{
            if(listadoRecobros_mvtoEquipo.get(select_MvtoEquipo_recobro.getSelectedIndex()).getCodigo().equals("0")){//Seleccionó NO al recobro
                Label_mvtoEquipo_autorizadoPor.show(false);
                select_MvtoEquipo_UsuarioQuienAutorizaRecobro.show(false);
            }   
        }
    }//GEN-LAST:event_select_MvtoEquipo_recobroActionPerformed

    private void select_MvtoEquipo_UsuarioQuienAutorizaRecobroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_UsuarioQuienAutorizaRecobroItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_UsuarioQuienAutorizaRecobroItemStateChanged

    private void select_MvtoEquipo_UsuarioQuienAutorizaRecobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_UsuarioQuienAutorizaRecobroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_UsuarioQuienAutorizaRecobroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Agregar_MvtoEquipo;
    private javax.swing.JInternalFrame InternaFrame_buscarArticulo;
    private javax.swing.JInternalFrame InternaFrame_buscarCliente;
    private javax.swing.JInternalFrame InternaFrame_buscarMotonave;
    private javax.swing.JLabel Label_mvtoEquipo_LaborRealizada;
    private javax.swing.JLabel Label_mvtoEquipo_ObservaciónActividad;
    private javax.swing.JLabel Label_mvtoEquipo_articulo;
    private javax.swing.JLabel Label_mvtoEquipo_autorizadoPor;
    private javax.swing.JLabel Label_mvtoEquipo_cliente;
    private javax.swing.JLabel Label_mvtoEquipo_cntroCostoAuxiliarDestino;
    private javax.swing.JLabel Label_mvtoEquipo_cntroCostoAuxiliarOrigen;
    private javax.swing.JLabel Label_mvtoEquipo_cntroOperacion;
    private javax.swing.JLabel Label_mvtoEquipo_equipo;
    private javax.swing.JLabel Label_mvtoEquipo_marcaEquipo;
    private javax.swing.JLabel Label_mvtoEquipo_motonave;
    private javax.swing.JLabel Label_mvtoEquipo_numeroOrden;
    private javax.swing.JLabel Label_mvtoEquipo_razonFinalizacion;
    private javax.swing.JLabel Label_mvtoEquipo_recobro;
    private javax.swing.JLabel Label_mvtoEquipo_subCentroCosto;
    private javax.swing.JLabel Label_mvtoEquipo_tipoEquipo;
    private javax.swing.JLabel Label_mvtoEquipo_usuarioQuienCierra;
    private javax.swing.JLabel Label_mvtoEquipo_usuarioQuienInicia;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarArticulo;
    private javax.swing.JButton btnCancelarCliente;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnConsultarArticulo;
    private javax.swing.JButton btnConsultarCliente;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLimpiarArticulo;
    private javax.swing.JButton btnLimpiarCliente;
    private com.toedter.calendar.JDateChooser fechaFinActividad_MvtoEquipo;
    private javax.swing.JLabel fechaHoraFinalActividad;
    private javax.swing.JLabel fechaHoraFinalActividadS;
    private javax.swing.JLabel fechaHoraInicioActividad;
    private javax.swing.JLabel fechaHoraInicioActividadS;
    private com.toedter.calendar.JDateChooser fechaInicialActividad_MvtoEquipo;
    private javax.swing.JLabel icon_buscarArticulo;
    private javax.swing.JLabel icon_buscarClientes;
    private javax.swing.JLabel icon_buscarMotonave;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel label_MvtoEquipo_HoraFinal;
    private javax.swing.JLabel label_MvtoEquipo_HoraInicial;
    private javax.swing.JLabel label_MvtoEquipo_ZonaHorariaFinal;
    private javax.swing.JLabel label_MvtoEquipo_ZonaHorariaInicial;
    private javax.swing.JLabel label_MvtoEquipo_separadorFinal;
    private javax.swing.JLabel label_MvtoEquipo_separadorInicial;
    private javax.swing.JLabel label_articulo;
    private javax.swing.JLabel label_clientes;
    private javax.swing.JLabel label_motonave;
    private javax.swing.JTextField mvtoEquipo_numOrden;
    private javax.swing.JComboBox<String> mvtoEquipo_selectRazonFinalzación;
    private javax.swing.JComboBox<String> select_MvtoEquipo_CCAuxiliar;
    private javax.swing.JComboBox<String> select_MvtoEquipo_CCAuxiliarDestino;
    private javax.swing.JComboBox<String> select_MvtoEquipo_CO;
    private javax.swing.JComboBox<String> select_MvtoEquipo_Equipo;
    private javax.swing.JComboBox<String> select_MvtoEquipo_HoraFinal;
    private javax.swing.JComboBox<String> select_MvtoEquipo_HoraInicial;
    private javax.swing.JComboBox<String> select_MvtoEquipo_MinutosFinal;
    private javax.swing.JComboBox<String> select_MvtoEquipo_MinutosInicial;
    private javax.swing.JComboBox<String> select_MvtoEquipo_SubcentroCosto;
    private javax.swing.JComboBox<String> select_MvtoEquipo_UsuarioCierraRegistro;
    private javax.swing.JComboBox<String> select_MvtoEquipo_UsuarioIniciaRegistro;
    private javax.swing.JComboBox<String> select_MvtoEquipo_UsuarioQuienAutorizaRecobro;
    private javax.swing.JComboBox<String> select_MvtoEquipo_laborRealizada;
    private javax.swing.JComboBox<String> select_MvtoEquipo_marcaEquipo;
    private javax.swing.JComboBox<String> select_MvtoEquipo_recobro;
    private javax.swing.JComboBox<String> select_MvtoEquipo_tipoEquipo;
    private javax.swing.JTable tablaArticulo;
    private javax.swing.JTable tablaCliente;
    private javax.swing.JTable tablaMotonaves;
    private javax.swing.JTextArea textArea_MvtoEquipo_Observacion;
    private javax.swing.JLabel titulo62;
    private javax.swing.JLabel titulo64;
    private javax.swing.JLabel titulo65;
    private javax.swing.JTextField valorBusqueda;
    private javax.swing.JTextField valorBusquedaArticulo;
    private javax.swing.JTextField valorBusquedaCliente;
    // End of variables declaration//GEN-END:variables
}
