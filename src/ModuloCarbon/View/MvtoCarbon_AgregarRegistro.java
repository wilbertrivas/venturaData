package ModuloCarbon.View2;

import Catalogo.Controller.ControlDB_CentroCostoSubCentro;
import Catalogo.Controller.ControlDB_CentroOperacion;
import Catalogo.Controller.ControlDB_Equipo;
import Catalogo.Controller.ControlDB_LaborRealizada;
import Catalogo.Controller.ControlDB_MotivoNoLavado;
import Catalogo.Controller.ControlDB_MotivoParada;
import Catalogo.Controller.ControlDB_Motonave;
import Catalogo.Controller.ControlDB_TipoEquipo;
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import Catalogo.Model.Equipo;
import Catalogo.Model.LaborRealizada;
import Catalogo.Model.MotivoNoLavado;
import Catalogo.Model.MotivoParada;
import Catalogo.Model.Motonave;
import Catalogo.Model.TipoEquipo;
import ModuloCarbon.Controller2.ControlDB_MvtoCarbon;
import ModuloCarbon.Model.EstadoMvtoCarbon;
import ModuloCarbon.Model.MvtoCarbon;
import ModuloCarbon.Model.MvtoCarbon_ListadoEquipos;
import ModuloEquipo.Controller2.ControlDB_AsignacionEquipo;
import ModuloEquipo.Controller2.ControlDB_MvtoEquipo;
import ModuloEquipo.Model.AsignacionEquipo;
import ModuloEquipo.Model.CausaInactividad;
import ModuloEquipo.Model.MvtoEquipo;
import ModuloEquipo.Model.MvtoEquipoAgregar;
import ModuloEquipo.Model.Recobro;
import ModuloEquipo.Model.SolicitudListadoEquipo;
import ModuloEquipo.View2.MvtoEquipo_ModificarFinal;
import ModuloEquipo.View2.Solicitud_Equipos_Registrar;
import Sistema.Controller.ControlDB_Usuario;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MvtoCarbon_AgregarRegistro extends javax.swing.JPanel {

    private Usuario user;
    private String tipoConexion;
    ArrayList<MvtoCarbon_ListadoEquipos> listadoMvtoCarbon_ListadoEquipos=null;
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
    private ArrayList<MvtoCarbon> listadoMvtoCarbon = null;
    
    ArrayList<CausaInactividad> listadoCausaInactividad = new ArrayList();
    ArrayList<MotivoNoLavado> listadoMotivoNolavadoVehiculo = new ArrayList();
    ArrayList<Usuario> listadoUsuarioInicioRegistro_mvtoCarbon= new ArrayList();
    ArrayList<Usuario> listadoUsuarioCierraRegistro_mvtoCarbon= new ArrayList();        
    ArrayList<Usuario> listadoUsuarioInicioRegistro_mvtoEquipo= new ArrayList();
    ArrayList<Usuario> listadoUsuarioQuienAutorizaRecobro= new ArrayList();
    ArrayList<Usuario> listadoUsuarioCierraRegistro_mvtoEquipo= new ArrayList();
    
    MvtoEquipo mvtoEquipo_temp=null;
    MvtoCarbon mvtoCarbon=null;
    public MvtoCarbon_AgregarRegistro(Usuario user, String tipoConexion) {
        initComponents();
        this.user= user;
        this.tipoConexion= tipoConexion;
        VentanaInterna_buscarVehiculo.getContentPane().setBackground(Color.WHITE);
        VentanaInterna_buscarVehiculo.show(false);
        
        
         for(int i = 0; i<= 23; i++){
            if(i<=9){
                select_MvtoCarbon_HoraInicial.addItem("0"+i);
                select_MvtoCarbon_HoraFinal.addItem("0"+i);
                select_MvtoEquipo_HoraInicial.addItem("0"+i);
                select_MvtoEquipo_HoraFinal.addItem("0"+i);
                
                horaInicio_destareVehiculo.addItem("0"+i);
                horaFin_destareVehiculo.addItem("0"+i);
                
                
            }else{
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
                select_MvtoCarbon_MinutosInicial.addItem("0"+i);
                select_MvtoCarbon_MinutosFinal.addItem("0"+i);
                select_MvtoEquipo_MinutosInicial.addItem("0"+i);
                select_MvtoEquipo_MinutosFinal.addItem("0"+i);
                
                
                
                minutoInicio_destareVehiculo.addItem("0"+i);
                minutoFin_destareVehiculo.addItem("0"+i);
                
            }else{
                select_MvtoCarbon_MinutosInicial.addItem(""+i);
                select_MvtoCarbon_MinutosFinal.addItem(""+i);
                select_MvtoEquipo_MinutosInicial.addItem(""+i); 
                select_MvtoEquipo_MinutosFinal.addItem(""+i);
                
                
                minutoInicio_destareVehiculo.addItem(""+i);
                minutoFin_destareVehiculo.addItem(""+i);
            }
        }
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
        
        //Cargamos en la interfaz los Subcentro de costos según el centro de Operación
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
        
        //Cargamos los la interfaz los auxiliares de costos según selección de SubCentro de costos
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
        //Cargamos en la interfaz los centro auxiliares Destino según labor realizada
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
        //Cargamos en la interfaz los motivos de no lavado de vehículos
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
            listadoUsuarioQuienAutorizaRecobro=listadoUsuarioInicioRegistro_mvtoCarbon;
            
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
        
        
        
        //Ocultamos datos del MvtoEquipo
        seleccionadorPlaca.show(false);
        seleccionadorPlaca1.show(false);
        titulo67.show(false);
        fechaInicialActividad_MvtoEquipo.show(false);
        label_MvtoEquipo_HoraInicial.show(false);
        select_MvtoEquipo_HoraInicial.show(false);
        label_MvtoEquipo_separadorInicial.show(false);
        select_MvtoEquipo_MinutosInicial.show(false);
        label_MvtoEquipo_ZonaHorariaInicial.show(false);
        titulo64.show(false);
        fechaFinActividad_MvtoEquipo.show(false);
        label_MvtoEquipo_HoraFinal.show(false);
        select_MvtoEquipo_HoraFinal.show(false);
        label_MvtoEquipo_separadorFinal.show(false);
        select_MvtoEquipo_MinutosFinal.show(false);
        label_MvtoEquipo_ZonaHorariaFinal.show(false);
        titulo65.show(false);
        select_MvtoEquipo_tipoEquipo.show(false);
        titulo61.show(false);
        select_MvtoEquipo_marcaEquipo.show(false);
        titulo62.show(false);
        titulo63.show(false);
        select_MvtoEquipo_Equipo.show(false);
        titulo48.show(false);
        select_MvtoEquipo_recobro.show(false);
        titulo66.show(false);
        select_MvtoEquipo_UsuarioIniciaRegistro.show(false);
        titulo62.show(false);
        select_MvtoEquipo_UsuarioCierraRegistro.show(false);
        titulo30.show(false);
        mvtoEquipo_selectRazonFinalzación.show(false);
        titulo47.show(false);
        select_MvtoEquipo_lavadoVehículo.show(false);
        titulo52.show(false);
        select_MvtoCarbon_motivoNoLavadoVehiculo.show(false);
         
        jButton1.show(false);
        btnRegistrar.show(false);
        
        titulo62.show(false);
        titulo69.show(false);
        titulo70.show(false);
        select_MvtoEquipo_UsuarioQuienAutorizaRecobro.show(false);
        titulo68.show(false);
        tabla_ListadoMvtoCarbonListadoEquipo.show(false);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        EliminarMvtoEquipo = new javax.swing.JPopupMenu();
        EliminarMvtoEquipo2 = new javax.swing.JMenuItem();
        VentanaInterna_buscarVehiculo = new javax.swing.JInternalFrame();
        jLabel6 = new javax.swing.JLabel();
        buscadorPlaca = new javax.swing.JTextField();
        btnConsultar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        fechaInicio_destareVehiculo = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        horaInicio_destareVehiculo = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        minutoInicio_destareVehiculo = new javax.swing.JComboBox<>();
        horarioTiempoInicio_destareVehiculo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        fechaFin_destareVehiculo = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        horaFin_destareVehiculo = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        minutoFin_destareVehiculo = new javax.swing.JComboBox<>();
        horarioTiempoIFinal_destareVehiculo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        titulo = new javax.swing.JLabel();
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
        titulo49 = new javax.swing.JLabel();
        select_MvtoCarbon_CCAuxiliarDestino = new javax.swing.JComboBox<>();
        titulo46 = new javax.swing.JLabel();
        select_MvtoCarbon_laborRealizada = new javax.swing.JComboBox<>();
        select_MvtoCarbon_UsuarioIniciaRegistro = new javax.swing.JComboBox<>();
        select_MvtoCarbon_UsuarioCierraRegistro = new javax.swing.JComboBox<>();
        titulo47 = new javax.swing.JLabel();
        select_MvtoEquipo_lavadoVehículo = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel95 = new javax.swing.JLabel();
        MvtCarbon_fechaEntradaVehiculo = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel91 = new javax.swing.JLabel();
        MvtCarbon_placa = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        MvtCarbon_fechaSalidaVehiculo = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        MvtCarbon_cliente = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        MvtCarbon_transportadora = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        MvtCarbon_deposito = new javax.swing.JLabel();
        MvtCarbon_consecutivo = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        MvtCarbon_pesoVacio = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        MvtCarbon_pesoLleno = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        MvtCarbon_pesoNeto = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        MvtCarbon_articulo = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        seleccionadorPlaca = new javax.swing.JLabel();
        titulo31 = new javax.swing.JLabel();
        titulo65 = new javax.swing.JLabel();
        titulo64 = new javax.swing.JLabel();
        select_MvtoEquipo_tipoEquipo = new javax.swing.JComboBox<>();
        titulo61 = new javax.swing.JLabel();
        select_MvtoEquipo_marcaEquipo = new javax.swing.JComboBox<>();
        titulo62 = new javax.swing.JLabel();
        select_MvtoEquipo_UsuarioQuienAutorizaRecobro = new javax.swing.JComboBox<>();
        select_MvtoEquipo_Equipo = new javax.swing.JComboBox<>();
        titulo48 = new javax.swing.JLabel();
        select_MvtoEquipo_recobro = new javax.swing.JComboBox<>();
        titulo30 = new javax.swing.JLabel();
        mvtoEquipo_selectRazonFinalzación = new javax.swing.JComboBox<>();
        seleccionadorPlaca1 = new javax.swing.JLabel();
        select_MvtoEquipo_UsuarioIniciaRegistro = new javax.swing.JComboBox<>();
        select_MvtoEquipo_UsuarioCierraRegistro = new javax.swing.JComboBox<>();
        fechaInicialActividad_MvtoEquipo = new com.toedter.calendar.JDateChooser();
        label_MvtoEquipo_HoraInicial = new javax.swing.JLabel();
        titulo68 = new javax.swing.JLabel();
        select_MvtoEquipo_HoraInicial = new javax.swing.JComboBox<>();
        label_MvtoEquipo_separadorInicial = new javax.swing.JLabel();
        select_MvtoEquipo_MinutosInicial = new javax.swing.JComboBox<>();
        label_MvtoEquipo_ZonaHorariaInicial = new javax.swing.JLabel();
        fechaFinActividad_MvtoEquipo = new com.toedter.calendar.JDateChooser();
        label_MvtoEquipo_HoraFinal = new javax.swing.JLabel();
        select_MvtoEquipo_HoraFinal = new javax.swing.JComboBox<>();
        label_MvtoEquipo_separadorFinal = new javax.swing.JLabel();
        select_MvtoEquipo_MinutosFinal = new javax.swing.JComboBox<>();
        label_MvtoEquipo_ZonaHorariaFinal = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        select_MvtoCarbon_motivoNoLavadoVehiculo = new javax.swing.JComboBox<>();
        titulo52 = new javax.swing.JLabel();
        titulo32 = new javax.swing.JLabel();
        titulo67 = new javax.swing.JLabel();
        titulo50 = new javax.swing.JLabel();
        titulo63 = new javax.swing.JLabel();
        titulo66 = new javax.swing.JLabel();
        titulo51 = new javax.swing.JLabel();
        titulo69 = new javax.swing.JLabel();
        titulo70 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabla_ListadoMvtoCarbonListadoEquipo = new javax.swing.JTable();
        btnRegistrar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        Editar.setText("Modificar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        EliminarMvtoEquipo2.setText("Eliminar");
        EliminarMvtoEquipo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarMvtoEquipo2ActionPerformed(evt);
            }
        });
        EliminarMvtoEquipo.add(EliminarMvtoEquipo2);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        VentanaInterna_buscarVehiculo.setVisible(false);
        VentanaInterna_buscarVehiculo.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("Seleccione la placa:");
        VentanaInterna_buscarVehiculo.getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 140, 40));

        buscadorPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscadorPlacaActionPerformed(evt);
            }
        });
        VentanaInterna_buscarVehiculo.getContentPane().add(buscadorPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 360, 40));

        btnConsultar.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        btnConsultar.setToolTipText("CONSULTAR MOTONAVES");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        VentanaInterna_buscarVehiculo.getContentPane().add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 60, 40));

        btnLimpiar.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/clean.png"))); // NOI18N
        btnLimpiar.setToolTipText("BORRAR RESULTADOS");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        VentanaInterna_buscarVehiculo.getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, 60, 40));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btnCancelar.setToolTipText("CANCELAR BUSQUEDA");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        VentanaInterna_buscarVehiculo.getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 100, 60, 40));

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
        tabla1.setComponentPopupMenu(Seleccionar);
        tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabla1);

        VentanaInterna_buscarVehiculo.getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 1260, 470));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("INICIO:");
        VentanaInterna_buscarVehiculo.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 60, 30));

        fechaInicio_destareVehiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicio_destareVehiculoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicio_destareVehiculoMouseEntered(evt);
            }
        });
        VentanaInterna_buscarVehiculo.getContentPane().add(fechaInicio_destareVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 170, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Hora");
        VentanaInterna_buscarVehiculo.getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, -1, 30));

        horaInicio_destareVehiculo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                horaInicio_destareVehiculoItemStateChanged(evt);
            }
        });
        VentanaInterna_buscarVehiculo.getContentPane().add(horaInicio_destareVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 50, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText(":");
        VentanaInterna_buscarVehiculo.getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 20, 30));

        minutoInicio_destareVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoInicio_destareVehiculoActionPerformed(evt);
            }
        });
        VentanaInterna_buscarVehiculo.getContentPane().add(minutoInicio_destareVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 50, 30));

        horarioTiempoInicio_destareVehiculo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoInicio_destareVehiculo.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoInicio_destareVehiculo.setText("AM");
        VentanaInterna_buscarVehiculo.getContentPane().add(horarioTiempoInicio_destareVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 40, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("FINALIZACIÓN:");
        VentanaInterna_buscarVehiculo.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, 90, 30));

        fechaFin_destareVehiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFin_destareVehiculoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFin_destareVehiculoMouseEntered(evt);
            }
        });
        VentanaInterna_buscarVehiculo.getContentPane().add(fechaFin_destareVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, 170, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Hora");
        VentanaInterna_buscarVehiculo.getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 60, -1, 30));

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
        VentanaInterna_buscarVehiculo.getContentPane().add(horaFin_destareVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 50, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel24.setText(":");
        VentanaInterna_buscarVehiculo.getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 60, 20, 30));

        minutoFin_destareVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoFin_destareVehiculoActionPerformed(evt);
            }
        });
        VentanaInterna_buscarVehiculo.getContentPane().add(minutoFin_destareVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 60, 50, 30));

        horarioTiempoIFinal_destareVehiculo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoIFinal_destareVehiculo.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoIFinal_destareVehiculo.setText("AM");
        VentanaInterna_buscarVehiculo.getContentPane().add(horarioTiempoIFinal_destareVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 60, 50, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 102));
        jLabel9.setText("CONSULTAR VEHÍCULOS:");
        VentanaInterna_buscarVehiculo.getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 190, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 102));
        jLabel10.setText("Seleccione la fecha del destare del vehículo:");
        VentanaInterna_buscarVehiculo.getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 410, 20));

        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        VentanaInterna_buscarVehiculo.getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 1260, 110));
        VentanaInterna_buscarVehiculo.getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 1260, 10));

        add(VentanaInterna_buscarVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 670));

        titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo.setForeground(new java.awt.Color(0, 153, 153));
        titulo.setText("AGREGAR REGISTRO MVTO CARBÓN");
        add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 410, 20));

        fechaInicialActividad_MvtoCarbon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicialActividad_MvtoCarbonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicialActividad_MvtoCarbonMouseEntered(evt);
            }
        });
        add(fechaInicialActividad_MvtoCarbon, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 170, 30));

        label_MvtoCarbon_HoraInicial.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_MvtoCarbon_HoraInicial.setForeground(new java.awt.Color(51, 51, 51));
        label_MvtoCarbon_HoraInicial.setText("Hora");
        add(label_MvtoCarbon_HoraInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 40, 30));

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
        add(select_MvtoCarbon_HoraInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, 50, 30));

        label_MvtoCarbon_separadorInicial.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        label_MvtoCarbon_separadorInicial.setText(":");
        add(label_MvtoCarbon_separadorInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, -1, 30));

        select_MvtoCarbon_MinutosInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoCarbon_MinutosInicialActionPerformed(evt);
            }
        });
        add(select_MvtoCarbon_MinutosInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, 50, 30));

        label_MvtoCarbon_ZonaHorariaInicial.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        label_MvtoCarbon_ZonaHorariaInicial.setForeground(new java.awt.Color(102, 102, 102));
        label_MvtoCarbon_ZonaHorariaInicial.setText("AM");
        add(label_MvtoCarbon_ZonaHorariaInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 180, 50, 30));

        fechaFinActividad_MvtoCarbon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFinActividad_MvtoCarbonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFinActividad_MvtoCarbonMouseEntered(evt);
            }
        });
        add(fechaFinActividad_MvtoCarbon, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 180, 170, 30));

        label_MvtoCarbon_HoraFinal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_MvtoCarbon_HoraFinal.setForeground(new java.awt.Color(51, 51, 51));
        label_MvtoCarbon_HoraFinal.setText("Hora");
        add(label_MvtoCarbon_HoraFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 180, 40, 30));

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
        add(select_MvtoCarbon_HoraFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 180, 50, 30));

        label_MvtoCarbon_separadorFinal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        label_MvtoCarbon_separadorFinal.setText(":");
        add(label_MvtoCarbon_separadorFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 180, -1, 30));

        select_MvtoCarbon_MinutosFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoCarbon_MinutosFinalActionPerformed(evt);
            }
        });
        add(select_MvtoCarbon_MinutosFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 180, 50, 30));

        label_MvtoCarbon_ZonaHorariaFinal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        label_MvtoCarbon_ZonaHorariaFinal.setForeground(new java.awt.Color(102, 102, 102));
        label_MvtoCarbon_ZonaHorariaFinal.setText("AM");
        add(label_MvtoCarbon_ZonaHorariaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 180, 50, 30));

        titulo29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo29.setForeground(new java.awt.Color(51, 51, 51));
        titulo29.setText("FECHA DE FINALIZACIÓN DESCARGUE:");
        titulo29.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo29, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 180, 260, 30));

        select_MvtoCarbon_CO.setToolTipText("");
        select_MvtoCarbon_CO.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoCarbon_COItemStateChanged(evt);
            }
        });
        add(select_MvtoCarbon_CO, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 210, 30));

        titulo25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo25.setForeground(new java.awt.Color(51, 51, 51));
        titulo25.setText("SUBCENTRO COSTO:");
        titulo25.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo25, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, 130, 30));

        select_MvtoCarbon_SubcentroCosto.setToolTipText("");
        select_MvtoCarbon_SubcentroCosto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoCarbon_SubcentroCostoItemStateChanged(evt);
            }
        });
        add(select_MvtoCarbon_SubcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 220, 240, 30));

        titulo45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo45.setForeground(new java.awt.Color(51, 51, 51));
        titulo45.setText("C.C. AUXILIAR ORIGEN:");
        titulo45.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo45, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 150, 30));

        select_MvtoCarbon_CCAuxiliar.setToolTipText("");
        add(select_MvtoCarbon_CCAuxiliar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 210, 30));

        titulo49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo49.setForeground(new java.awt.Color(51, 51, 51));
        titulo49.setText("USUARIO QUIEN CIERRA ACTIVIDAD:");
        titulo49.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo49, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 260, 30));

        select_MvtoCarbon_CCAuxiliarDestino.setToolTipText("");
        add(select_MvtoCarbon_CCAuxiliarDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 260, 240, 30));

        titulo46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo46.setForeground(new java.awt.Color(51, 51, 51));
        titulo46.setText("LABOR A REALIZAR:");
        titulo46.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo46, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 200, 30));

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
        add(select_MvtoCarbon_laborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 210, 30));

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
        add(select_MvtoCarbon_UsuarioIniciaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 300, 460, 30));

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
        add(select_MvtoCarbon_UsuarioCierraRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, 520, 30));

        titulo47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo47.setForeground(new java.awt.Color(51, 51, 51));
        titulo47.setText("LAVADO DE VEHÍCULO:");
        titulo47.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo47, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 670, 160, 30));

        select_MvtoEquipo_lavadoVehículo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "SI" }));
        select_MvtoEquipo_lavadoVehículo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoEquipo_lavadoVehículoItemStateChanged(evt);
            }
        });
        select_MvtoEquipo_lavadoVehículo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_lavadoVehículoActionPerformed(evt);
            }
        });
        add(select_MvtoEquipo_lavadoVehículo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 670, 70, 30));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_equipoMovil.png"))); // NOI18N
        jButton1.setText("CARGAR EQUIPO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 710, 180, -1));

        jLabel95.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(0, 51, 51));
        jLabel95.setText("Fecha Entrada Vehículo:");
        add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, 170, 20));

        MvtCarbon_fechaEntradaVehiculo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_fechaEntradaVehiculo.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_fechaEntradaVehiculo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(MvtCarbon_fechaEntradaVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, 270, 20));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icon_camion_carbon_7.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 410, 130));

        jLabel91.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(0, 51, 51));
        jLabel91.setText("Placa:");
        add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 150, 20));

        MvtCarbon_placa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_placa.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_placa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(MvtCarbon_placa, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 270, 20));

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 51, 51));
        jLabel57.setText("Fecha Salida Vehículo:");
        add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 160, 20));

        MvtCarbon_fechaSalidaVehiculo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_fechaSalidaVehiculo.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_fechaSalidaVehiculo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(MvtCarbon_fechaSalidaVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, 270, 20));

        jLabel90.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(0, 51, 51));
        jLabel90.setText("Cliente:");
        add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 90, 20));

        MvtCarbon_cliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_cliente.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_cliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(MvtCarbon_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, 270, 20));

        jLabel98.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(0, 51, 51));
        jLabel98.setText("Transportadora:");
        add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 130, 20));

        MvtCarbon_transportadora.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_transportadora.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_transportadora.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(MvtCarbon_transportadora, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 120, 270, 20));

        jLabel99.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(0, 51, 51));
        jLabel99.setText("Deposito:");
        add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 90, 20));

        MvtCarbon_deposito.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_deposito.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_deposito.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(MvtCarbon_deposito, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 80, 270, 20));

        MvtCarbon_consecutivo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_consecutivo.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_consecutivo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(MvtCarbon_consecutivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 100, 250, 20));

        jLabel102.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(0, 51, 51));
        jLabel102.setText("Peso Vacio:");
        add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 40, 80, 20));

        MvtCarbon_pesoVacio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_pesoVacio.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_pesoVacio.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(MvtCarbon_pesoVacio, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 40, 250, 20));

        jLabel103.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(0, 51, 51));
        jLabel103.setText("Peso Lleno:");
        add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 60, 80, 20));

        MvtCarbon_pesoLleno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_pesoLleno.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_pesoLleno.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(MvtCarbon_pesoLleno, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 60, 250, 20));

        jLabel100.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(0, 51, 51));
        jLabel100.setText("Peso Neto:");
        add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 80, 80, 20));

        MvtCarbon_pesoNeto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_pesoNeto.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_pesoNeto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(MvtCarbon_pesoNeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 80, 250, 20));

        jLabel89.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(0, 51, 51));
        jLabel89.setText("Articulo:");
        add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, 80, 20));

        MvtCarbon_articulo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MvtCarbon_articulo.setForeground(new java.awt.Color(51, 51, 51));
        MvtCarbon_articulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(MvtCarbon_articulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 20, 250, 20));

        jLabel101.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(0, 51, 51));
        jLabel101.setText("Consecutivo:");
        add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 100, 90, 20));

        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 800, 150));

        seleccionadorPlaca.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        seleccionadorPlaca.setForeground(new java.awt.Color(0, 102, 102));
        seleccionadorPlaca.setText("PLACA SELECCIONADA:");
        add(seleccionadorPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 430, 290, -1));

        titulo31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo31.setForeground(new java.awt.Color(51, 51, 51));
        titulo31.setText("CENTRO OPERACIÓN:");
        titulo31.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo31, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 200, 30));

        titulo65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo65.setForeground(new java.awt.Color(51, 51, 51));
        titulo65.setText("TIPO:");
        titulo65.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo65, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, 140, 30));

        titulo64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo64.setForeground(new java.awt.Color(51, 51, 51));
        titulo64.setText("FECHA DE FINALIZACIÓN:");
        titulo64.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo64, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 470, 170, 30));

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
        add(select_MvtoEquipo_tipoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 510, 220, 30));

        titulo61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo61.setForeground(new java.awt.Color(51, 51, 51));
        titulo61.setText("MARCA:");
        titulo61.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo61, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 510, 70, 30));

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
        add(select_MvtoEquipo_marcaEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 510, 370, 30));

        titulo62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo62.setForeground(new java.awt.Color(51, 51, 51));
        titulo62.setText("AUTORIZADO POR:");
        titulo62.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo62, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 630, 120, 30));

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
        add(select_MvtoEquipo_UsuarioQuienAutorizaRecobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 630, 370, 30));

        select_MvtoEquipo_Equipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_EquipoActionPerformed(evt);
            }
        });
        add(select_MvtoEquipo_Equipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 550, 1020, 30));

        titulo48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo48.setForeground(new java.awt.Color(51, 51, 51));
        titulo48.setText("RECOBRO:");
        titulo48.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo48, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 630, 70, 30));

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
        add(select_MvtoEquipo_recobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 630, 60, 30));

        titulo30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo30.setForeground(new java.awt.Color(51, 51, 51));
        titulo30.setText("RAZÓN DE FINALIZACIÓN:");
        titulo30.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo30, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 630, 160, 30));

        mvtoEquipo_selectRazonFinalzación.setToolTipText("");
        mvtoEquipo_selectRazonFinalzación.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mvtoEquipo_selectRazonFinalzaciónItemStateChanged(evt);
            }
        });
        add(mvtoEquipo_selectRazonFinalzación, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 630, 380, 30));

        seleccionadorPlaca1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        seleccionadorPlaca1.setForeground(new java.awt.Color(255, 0, 0));
        add(seleccionadorPlaca1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 430, 290, 30));

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
        add(select_MvtoEquipo_UsuarioIniciaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 590, 380, 30));

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
        add(select_MvtoEquipo_UsuarioCierraRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 590, 370, 30));

        fechaInicialActividad_MvtoEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicialActividad_MvtoEquipoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicialActividad_MvtoEquipoMouseEntered(evt);
            }
        });
        add(fechaInicialActividad_MvtoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 470, 170, 30));

        label_MvtoEquipo_HoraInicial.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_MvtoEquipo_HoraInicial.setForeground(new java.awt.Color(51, 51, 51));
        label_MvtoEquipo_HoraInicial.setText("Hora");
        add(label_MvtoEquipo_HoraInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 470, -1, 30));

        titulo68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo68.setForeground(new java.awt.Color(51, 51, 51));
        titulo68.setText("USUARIO QUIEN CIERRA ACTIVIDAD:");
        titulo68.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo68, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 590, 240, 30));

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
        add(select_MvtoEquipo_HoraInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 470, 50, 30));

        label_MvtoEquipo_separadorInicial.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        label_MvtoEquipo_separadorInicial.setText(":");
        add(label_MvtoEquipo_separadorInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 470, 10, 30));

        select_MvtoEquipo_MinutosInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_MinutosInicialActionPerformed(evt);
            }
        });
        add(select_MvtoEquipo_MinutosInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 470, 50, 30));

        label_MvtoEquipo_ZonaHorariaInicial.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        label_MvtoEquipo_ZonaHorariaInicial.setForeground(new java.awt.Color(102, 102, 102));
        label_MvtoEquipo_ZonaHorariaInicial.setText("AM");
        add(label_MvtoEquipo_ZonaHorariaInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 470, 50, 30));

        fechaFinActividad_MvtoEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFinActividad_MvtoEquipoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFinActividad_MvtoEquipoMouseEntered(evt);
            }
        });
        add(fechaFinActividad_MvtoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 470, 170, 30));

        label_MvtoEquipo_HoraFinal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_MvtoEquipo_HoraFinal.setForeground(new java.awt.Color(51, 51, 51));
        label_MvtoEquipo_HoraFinal.setText("Hora");
        add(label_MvtoEquipo_HoraFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 470, -1, 30));

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
        add(select_MvtoEquipo_HoraFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 470, 50, 30));

        label_MvtoEquipo_separadorFinal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        label_MvtoEquipo_separadorFinal.setText(":");
        add(label_MvtoEquipo_separadorFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 470, 10, 30));

        select_MvtoEquipo_MinutosFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MvtoEquipo_MinutosFinalActionPerformed(evt);
            }
        });
        add(select_MvtoEquipo_MinutosFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 470, 50, 30));

        label_MvtoEquipo_ZonaHorariaFinal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        label_MvtoEquipo_ZonaHorariaFinal.setForeground(new java.awt.Color(102, 102, 102));
        label_MvtoEquipo_ZonaHorariaFinal.setText("AM");
        add(label_MvtoEquipo_ZonaHorariaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 470, 50, 30));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/descargue.png"))); // NOI18N
        jButton3.setText("CARGAR VEHÍCULO");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 180, -1));

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
        add(select_MvtoCarbon_motivoNoLavadoVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 670, 250, 30));

        titulo52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo52.setForeground(new java.awt.Color(51, 51, 51));
        titulo52.setText("MOTIVO:");
        titulo52.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo52, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 670, 60, 30));

        titulo32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo32.setForeground(new java.awt.Color(51, 51, 51));
        titulo32.setText("FECHA DE INICIO DESCARGUE:");
        titulo32.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo32, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 200, 30));

        titulo67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo67.setForeground(new java.awt.Color(0, 102, 102));
        titulo67.setText("INFORMACIÓN DEL VEHÍCULO");
        titulo67.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo67, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 570, 20));

        titulo50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo50.setForeground(new java.awt.Color(51, 51, 51));
        titulo50.setText("C.C. AUXILIAR DESTINO:");
        titulo50.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo50, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 260, 160, 30));

        titulo63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo63.setForeground(new java.awt.Color(51, 51, 51));
        titulo63.setText("EQUIPO:");
        titulo63.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo63, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, 60, 30));

        titulo66.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo66.setForeground(new java.awt.Color(51, 51, 51));
        titulo66.setText("USUARIO QUIEN  INICIA:");
        titulo66.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo66, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 590, 160, 30));

        titulo51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo51.setForeground(new java.awt.Color(51, 51, 51));
        titulo51.setText("USUARIO QUIEN  INICIA ACTIVIDAD:");
        titulo51.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo51, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 270, 30));

        titulo69.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo69.setForeground(new java.awt.Color(51, 51, 51));
        titulo69.setText("FECHA DE INICIO:");
        titulo69.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo69, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 140, 30));

        titulo70.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo70.setForeground(new java.awt.Color(0, 102, 102));
        titulo70.setText("INFORMACIÓN DEL EQUIPO RELACIONADO CON EL VEHÍCULO");
        titulo70.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo70, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 580, 20));

        tabla_ListadoMvtoCarbonListadoEquipo = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla_ListadoMvtoCarbonListadoEquipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla_ListadoMvtoCarbonListadoEquipo.setComponentPopupMenu(EliminarMvtoEquipo);
        tabla_ListadoMvtoCarbonListadoEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_ListadoMvtoCarbonListadoEquipoMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tabla_ListadoMvtoCarbonListadoEquipo);

        add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 770, 1210, 80));

        btnRegistrar.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save.png"))); // NOI18N
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 860, 140, 40));

        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 1210, 270));

        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1240, 920));

        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 1210, 330));
    }// </editor-fold>//GEN-END:initComponents

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
                    mvtoEquipo_selectRazonFinalzación.setModel(model);

                    //cargamos los motivos de parada según la consulta
                    try{
                        if(listadoMotivoParada_mvtoEquipo !=null){
                            int contadorX=0;
                            for(MotivoParada motivoParada: listadoMotivoParada_mvtoEquipo){
                                if(motivoParada.getCodigo().equals(mvtoCarbon.getMotivoParada().getCodigo())){
                                    mvtoCarbon_selectRazonFinalzación.setSelectedIndex(contadorX);
                                }
                                contadorX++;
                            }
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    listadoMotivoParada_mvtoEquipo=null;
                    mvtoCarbon_selectRazonFinalzación.removeAllItems();
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
                        mvtoCarbon_selectRazonFinalzación.setModel(model);

                        //cargamos los motivos de parada según la consulta
                        try{
                            if(listadoMotivoParada_mvtoCarbon !=null){
                                int contadorX=0;
                                for(MotivoParada motivoParada: listadoMotivoParada_mvtoCarbon){
                                    if(motivoParada.getCodigo().equals(mvtoCarbon.getMotivoParada().getCodigo())){
                                        mvtoCarbon_selectRazonFinalzación.setSelectedIndex(contadorX);
                                    }
                                    contadorX++;
                                }
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }else{
                        listadoMotivoParada_mvtoCarbon=null;
                        mvtoCarbon_selectRazonFinalzación.removeAllItems();
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

    private void select_MvtoEquipo_lavadoVehículoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_lavadoVehículoItemStateChanged
        if(select_MvtoEquipo_lavadoVehículo.getSelectedIndex()==0){
            titulo52.show(true);
            select_MvtoCarbon_motivoNoLavadoVehiculo.show(true);
        }else{
            if(select_MvtoEquipo_lavadoVehículo.getSelectedIndex()==1){
                titulo52.show(false);
                select_MvtoCarbon_motivoNoLavadoVehiculo.show(false);
            }
        }
    }//GEN-LAST:event_select_MvtoEquipo_lavadoVehículoItemStateChanged

    private void select_MvtoEquipo_lavadoVehículoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_lavadoVehículoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_lavadoVehículoActionPerformed

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
       //if(mvtoEquipo !=null){
            //Cargamos las marcas de equipos disponible en el rango de fecha.
            if(listadoTipoEquipos_mvtoEquipo !=null){
                try {
                    listadoMarcaEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarMarcaEquiposProgramados(mvtoCarbon.getFechaInicioDescargue(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion());
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
                    listadoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarAsignacionEquipos_Por_TipoEquipo_MarcaEquipo(mvtoCarbon.getFechaInicioDescargue(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion(),listadoMarcaEquipos_mvtoEquipo.get(select_MvtoEquipo_marcaEquipo.getSelectedIndex()));
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
        //}
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
      // if(mvtoEquipo !=null){
            if(listadoTipoEquipos_mvtoEquipo !=null && listadoMarcaEquipos_mvtoEquipo !=null){
                try {
                    //Cargamos los equipos disponible en el rango de fecha.
                    listadoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarAsignacionEquipos_Por_TipoEquipo_MarcaEquipo(mvtoCarbon.getFechaInicioDescargue(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion(),listadoMarcaEquipos_mvtoEquipo.get(select_MvtoEquipo_marcaEquipo.getSelectedIndex()));
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
       // }
    }//GEN-LAST:event_select_MvtoEquipo_marcaEquipoActionPerformed

    private void select_MvtoEquipo_EquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_EquipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_EquipoActionPerformed

    private void select_MvtoEquipo_recobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_recobroActionPerformed
        if(listadoRecobros_mvtoEquipo.get(select_MvtoEquipo_recobro.getSelectedIndex()).getCodigo().equals("1")){//Seleccionó SI al recobro
            titulo62.show(true);
            select_MvtoEquipo_UsuarioQuienAutorizaRecobro.show(true);
        }else{
            if(listadoRecobros_mvtoEquipo.get(select_MvtoEquipo_recobro.getSelectedIndex()).getCodigo().equals("0")){//Seleccionó NO al recobro
                titulo62.show(false);
                select_MvtoEquipo_UsuarioQuienAutorizaRecobro.show(false);
            }   
        }
    }//GEN-LAST:event_select_MvtoEquipo_recobroActionPerformed

    private void mvtoEquipo_selectRazonFinalzaciónItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mvtoEquipo_selectRazonFinalzaciónItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_mvtoEquipo_selectRazonFinalzaciónItemStateChanged

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

    private void select_MvtoEquipo_MinutosInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_MinutosInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_MinutosInicialActionPerformed

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

    private void select_MvtoCarbon_motivoNoLavadoVehiculoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_motivoNoLavadoVehiculoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoCarbon_motivoNoLavadoVehiculoItemStateChanged

    private void select_MvtoCarbon_motivoNoLavadoVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_motivoNoLavadoVehiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoCarbon_motivoNoLavadoVehiculoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if(mvtoCarbon != null){
            String fechaInicioActividad="";
            try{
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
                
                    //int valorFechaI=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorFechaActual(anoI, mesI, diaI, horaInicioAsignacion.getSelectedItem().toString(), minutoInicioAsignacion.getSelectedItem().toString()));
                    //if(valorFechaI<0){   
                        fechaInicioActividad=anoI+"-"+mesI+"-"+diaI+" "+select_MvtoEquipo_HoraInicial.getSelectedItem().toString()+":"+select_MvtoEquipo_MinutosInicial.getSelectedItem().toString()+":00.0";
                        //Cargamos la fecha de inicio de movimientos de la solicitud
                        String fechaFinActividad="";
                        try{
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
                            
                                //int valorFechaF=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorFechaActual(anoF, mesF, diaF, horaFinAsignacion.getSelectedItem().toString(), minutoFinAsignacion.getSelectedItem().toString()));
                                //if(valorFechaF<0){
                                    fechaFinActividad=anoF+"-"+mesF+"-"+diaF+" "+select_MvtoEquipo_HoraFinal.getSelectedItem().toString()+":"+select_MvtoEquipo_MinutosFinal.getSelectedItem().toString()+":00.0";
                                    int resultDosFechas=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorEntreDosFechas(anoI, mesI, diaI, select_MvtoEquipo_HoraInicial.getSelectedItem().toString(), select_MvtoEquipo_MinutosInicial.getSelectedItem().toString(), anoF, mesF, diaF, select_MvtoEquipo_HoraFinal.getSelectedItem().toString(), select_MvtoEquipo_MinutosFinal.getSelectedItem().toString()));
                                    if(resultDosFechas < 0){
                                        JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser mayor a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                                    }else{
                                        if(resultDosFechas ==0){
                                            JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser Igual a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                                        }else{
                                            if(!new ControlDB_MvtoCarbon(tipoConexion).validar2FechasEnRango(fechaInicioActividad, fechaFinActividad, mvtoCarbon.getFechaInicioDescargue(), mvtoCarbon.getFechaFinDescargue())){
                                                JOptionPane.showMessageDialog(null, "Error!!.. La fecha de inicio y finalización de la actividad debe estar dentro del rango de Inicio y finalización de descargue","Advertencia", JOptionPane.ERROR_MESSAGE );
                                            }else{
                                                if(listadoEquipos_mvtoEquipo!=null){
                                                        if(listadoRecobros_mvtoEquipo !=null){
                                                            if(listadoUsuarioInicioRegistro_mvtoEquipo !=null){
                                                                //if(listadoCentroCostoAuxiliarDestino_mvtoCarbon !=null){
                                                                    if(listadoUsuarioCierraRegistro_mvtoEquipo != null){
                                                                        if(listadoMotivoParada_mvtoEquipo != null){
                                                                            if(listadoMotivoNolavadoVehiculo != null){
                                                                                boolean validator= true;
                                                                                if(listadoMvtoCarbon_ListadoEquipos == null){
                                                                                    listadoMvtoCarbon_ListadoEquipos= new ArrayList<>();
                                                                                    
                                                                                }else{
                                                                                    //Necesitamos saber si existe un Equipo que tiene asignado el lavado de vehículo
                                                                                    boolean validarLavado = false;
                                                                                    for(MvtoCarbon_ListadoEquipos Objeto: listadoMvtoCarbon_ListadoEquipos){
                                                                                        if(Objeto.getMvtoEquipoAgregar().getLavadoVehiculo().equals("1")){
                                                                                            validarLavado=true;
                                                                                        }
                                                                                    }
                                                                                    if(validarLavado){
                                                                                        if(select_MvtoEquipo_lavadoVehículo.getSelectedIndex()==1){// validamos si la selección actual es igual a SI
                                                                                            validator=false;
                                                                                            JOptionPane.showMessageDialog(null, "No se pueden seleccionar dos equipos con la opción de lavado de vehículos como SI", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                                                                        }
                                                                                    }
                                                                                }
                                                                                if(validator){
                                                                                    btnRegistrar.show(true);
                                                                                    MvtoEquipoAgregar mvtoEquipoAgregar= new  MvtoEquipoAgregar();
                                                                                    mvtoEquipoAgregar.setFechaRegistro("(SELECT SYSDATETIME())");
                                                                                    mvtoEquipoAgregar.setFechaHoraInicio(fechaInicioActividad);
                                                                                    mvtoEquipoAgregar.setFechaHoraFin(fechaFinActividad); 
                                                                                    mvtoEquipoAgregar.setCentroOperacion(mvtoCarbon.getCentroOperacion());
                                                                                    mvtoEquipoAgregar.setCentroCostoAuxiliar(mvtoCarbon.getCentroCostoAuxiliar());
                                                                                    mvtoEquipoAgregar.setLaborRealizada(mvtoCarbon.getLaborRealizada());
                                                                                    if(mvtoCarbon.getCentroCostoAuxiliarDestino() !=null){
                                                                                        mvtoEquipoAgregar.setCentroCostoAuxiliarDestino(mvtoCarbon.getCentroCostoAuxiliarDestino());
                                                                                    }else{
                                                                                        mvtoEquipoAgregar.setCentroCostoAuxiliarDestino(null);
                                                                                    }
                                                                                    mvtoEquipoAgregar.setAsignacionEquipo(listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()));
                                                                                    mvtoEquipoAgregar.setProveedorEquipo(listadoEquipos_mvtoEquipo.get(select_MvtoEquipo_Equipo.getSelectedIndex()).getEquipo().getProveedorEquipo());
                                                                                    mvtoEquipoAgregar.setNumeroOrden("");
                                                                                    mvtoEquipoAgregar.setCliente(mvtoCarbon.getCliente());
                                                                                    mvtoEquipoAgregar.setRecobro(listadoRecobros_mvtoEquipo.get(select_MvtoEquipo_recobro.getSelectedIndex()));
                                                                                    if(listadoRecobros_mvtoEquipo.get(select_MvtoEquipo_recobro.getSelectedIndex()).getCodigo().equals("1")){//Seleccionó SI
                                                                                        mvtoEquipoAgregar.setUsuarioAutorizaRecobro(listadoUsuarioQuienAutorizaRecobro.get(select_MvtoEquipo_UsuarioQuienAutorizaRecobro.getSelectedIndex()));
                                                                                    }else{//Seleccionó NO
                                                                                       // Usuario usuarioAutorizaRecobro = new Usuario();
                                                                                        mvtoEquipoAgregar.setUsuarioAutorizaRecobro(null);
                                                                                    }
                                                                                    mvtoEquipoAgregar.setUsuarioQuieRegistra(listadoUsuarioInicioRegistro_mvtoEquipo.get(select_MvtoEquipo_UsuarioIniciaRegistro.getSelectedIndex()));
                                                                                    mvtoEquipoAgregar.setUsuarioQuienCierra(listadoUsuarioCierraRegistro_mvtoEquipo.get(select_MvtoEquipo_UsuarioCierraRegistro.getSelectedIndex()));
                                                                                    mvtoEquipoAgregar.setMotivoParada(listadoMotivoParada_mvtoEquipo.get(mvtoEquipo_selectRazonFinalzación.getSelectedIndex()));
                                                                                    if(select_MvtoEquipo_lavadoVehículo.getSelectedIndex()==0){//Seleccionó no lavado de vehiculo
                                                                                        mvtoEquipoAgregar.setLavadoVehiculo("0");
                                                                                        mvtoEquipoAgregar.setMotivoNoLavado(listadoMotivoNolavadoVehiculo.get(select_MvtoCarbon_motivoNoLavadoVehiculo.getSelectedIndex()));
                                                                                    }else{
                                                                                        mvtoEquipoAgregar.setLavadoVehiculo("1");//EL usuario seleccionó que si hay lavado de vehículo
                                                                                        mvtoEquipoAgregar.setMotivoNoLavado(null);  
                                                                                    }
                                                                                    mvtoEquipoAgregar.setEstado("1");
                                                                                    mvtoEquipoAgregar.setDesdeCarbon("1");
                                                                                    
                                                                                    MvtoCarbon_ListadoEquipos mvtoCarbon_ListadoEquipos = new MvtoCarbon_ListadoEquipos();
                                                                                    mvtoCarbon_ListadoEquipos.setAsignacionEquipo(mvtoEquipoAgregar.getAsignacionEquipo());
                                                                                    mvtoCarbon_ListadoEquipos.setMvtoCarbon(mvtoCarbon);
                                                                                    mvtoCarbon_ListadoEquipos.setMvtoEquipoAgregar(mvtoEquipoAgregar);
                                                                                    mvtoCarbon_ListadoEquipos.setEstado("1");
                                                                                    listadoMvtoCarbon_ListadoEquipos.add(mvtoCarbon_ListadoEquipos);
                                                                                    
                                                                                    DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"C.O","SubCentro","CentroCostoAuxiliar","Equipo_Descripción","Fecha_Y_Hora_Inicio","Fecha_Y_Hora_Fin","Razón Finalización" ,"Recobro","LavadoVhículo","Motivo No Lavado"});      
                                                                                    for (MvtoCarbon_ListadoEquipos Objeto : listadoMvtoCarbon_ListadoEquipos) {
                                                                                        String[] registro = new String[10];
                                                                                        registro[0] = "" + Objeto.getMvtoEquipoAgregar().getCentroOperacion().getDescripcion();
                                                                                        registro[1] = "" + Objeto.getMvtoEquipoAgregar().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                                                                                        registro[2] = "" + Objeto.getMvtoEquipoAgregar().getCentroCostoAuxiliar().getDescripcion();
                                                                                        registro[3] = "" + Objeto.getMvtoEquipoAgregar().getAsignacionEquipo().getEquipo().getDescripcion()+" "+Objeto.getMvtoEquipoAgregar().getAsignacionEquipo().getEquipo().getModelo();
                                                                                        registro[4] = "" + Objeto.getMvtoEquipoAgregar().getFechaHoraInicio();
                                                                                        registro[5] = "" + Objeto.getMvtoEquipoAgregar().getFechaHoraFin();
                                                                                        registro[6] = "" + Objeto.getMvtoEquipoAgregar().getMotivoParada().getDescripcion();
                                                                                        
                                                                                        registro[7] = Objeto.getMvtoEquipoAgregar().getRecobro().getDescripcion();
                                                                                        if(Objeto.getMvtoEquipoAgregar().getLavadoVehiculo().equals("0")){
                                                                                            registro[8] = "NO";
                                                                                        }else{
                                                                                             registro[8] = "SI";
                                                                                        }
                                                                                        if(Objeto.getMvtoEquipoAgregar().getMotivoNoLavado()!=null){
                                                                                            registro[9] =  "" + Objeto.getMvtoEquipoAgregar().getMotivoNoLavado().getDescripcion();
                                                                                        }else{
                                                                                            registro[9] = "";
                                                                                        }
                                                                                       
                                                                                        modelo.addRow(registro);      
                                                                                    }
                                                                                    tabla_ListadoMvtoCarbonListadoEquipo.setModel(modelo);
                                                                                }
                                                                            }else{
                                                                                JOptionPane.showMessageDialog(null, "Debe seleccionar un motivo de no lavado del vehículo", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                                                            }
                                                                        }else{
                                                                            JOptionPane.showMessageDialog(null, "Debe seleccionar una razón de finalización de la actividad", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                                                        }
                                                                    }else{
                                                                        JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario quien cierra la actividad", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                                                    }
                                                                //}
                                                            }else{
                                                                JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario quien inicia la actividad", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                                            }
                                                        }else{
                                                            JOptionPane.showMessageDialog(null, "Debe seleccionar si hay o no recobro", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                                        }
                                                }else{
                                                    JOptionPane.showMessageDialog(null, "Debe seleccionar un equipo que participa en la operación", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                                }            
                                            }
                                        }
                                    }
                                //}else{
                                  //  JOptionPane.showMessageDialog(null,"Error!!.. No puede cargar una fecha en el pasado, la fecha de Finalización de la Operación debe ser futura, verifique fecha","Advertencia",JOptionPane.ERROR_MESSAGE);
                               // }
                            
                        }catch(Exception e){
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Finalización","Advertencia",JOptionPane.ERROR_MESSAGE);
                        }
                    //}else{
                      //  JOptionPane.showMessageDialog(null,"Error!!.. No puede cargar una fecha en el pasado, la fecha de Inicio de Operación debe ser futura, verifique fecha","Advertencia",JOptionPane.ERROR_MESSAGE);
                    //}
                
            }catch(Exception e){
                 e.printStackTrace();
                JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Inicio","Advertencia",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null,"ERROR!!!, Debe cargar un vehiculo, para proceder a registrar", "Advertencia",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(mvtoCarbon != null){
            String fechaInicioDescargue="";
            try{
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
                try{
                    //int valorFechaI=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorFechaActual(anoI, mesI, diaI, horaInicioAsignacion.getSelectedItem().toString(), minutoInicioAsignacion.getSelectedItem().toString()));
                    //if(valorFechaI<0){   
                        fechaInicioDescargue=anoI+"-"+mesI+"-"+diaI+" "+select_MvtoCarbon_HoraInicial.getSelectedItem().toString()+":"+select_MvtoCarbon_MinutosInicial.getSelectedItem().toString()+":00.0";
                        //Cargamos la fecha de inicio de movimientos de la solicitud
                        String fechaFinDescargue="";
                        try{
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
                            try{
                                //int valorFechaF=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorFechaActual(anoF, mesF, diaF, horaFinAsignacion.getSelectedItem().toString(), minutoFinAsignacion.getSelectedItem().toString()));
                                //if(valorFechaF<0){
                                    fechaFinDescargue=anoF+"-"+mesF+"-"+diaF+" "+select_MvtoCarbon_HoraFinal.getSelectedItem().toString()+":"+select_MvtoCarbon_MinutosFinal.getSelectedItem().toString()+":00.0";
                                    int resultDosFechas=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorEntreDosFechas(anoI, mesI, diaI, select_MvtoCarbon_HoraInicial.getSelectedItem().toString(), select_MvtoCarbon_MinutosInicial.getSelectedItem().toString(), anoF, mesF, diaF, select_MvtoCarbon_HoraFinal.getSelectedItem().toString(), select_MvtoCarbon_MinutosFinal.getSelectedItem().toString()));
                                    if(resultDosFechas < 0){
                                        JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser mayor a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                                    }else{
                                        if(resultDosFechas ==0){
                                            JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser Igual a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                                        }else{
                                            if(!new ControlDB_MvtoCarbon(tipoConexion).validar2FechasEnRango(fechaInicioDescargue, fechaFinDescargue, mvtoCarbon.getFechaEntradaVehiculo(), mvtoCarbon.getFecha_SalidaVehiculo())){
                                                JOptionPane.showMessageDialog(null, "Error!!.. La fecha de inicio de operación y la fecha de finalización de operación deben estar en el rango de fecha de Tara y Destare del vehículo","Advertencia", JOptionPane.ERROR_MESSAGE );
                                            }else{
                                                if(listadoCentroOperacion_mvtoCarbon !=null){
                                                        if(listadoCentroCostoSubCentro_mvtoCarbon !=null){
                                                            if(listadoCentroCostoAuxiliar_mvtoCarbon !=null){
                                                                //if(listadoCentroCostoAuxiliarDestino_mvtoCarbon !=null){
                                                                    if(listadoLaborRealizada_mvtoCarbon != null){
                                                                        if(listadoUsuarioInicioRegistro_mvtoCarbon != null){
                                                                            if(listadoUsuarioCierraRegistro_mvtoCarbon != null){
                                                                                    
                                                                                
                                                                                
                                                                                mvtoCarbon.setCentroOperacion(listadoCentroOperacion_mvtoCarbon.get(select_MvtoCarbon_CO.getSelectedIndex()));
                                                                                mvtoCarbon.setCentroCostoAuxiliar(listadoCentroCostoAuxiliar_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliar.getSelectedIndex()));
                                                                                
                                                                                mvtoCarbon.setLavadoVehiculo("NULL");
                                                                                mvtoCarbon.setObservacion("NULL");
                                                                                
                                                                                mvtoCarbon.setLaborRealizada(listadoLaborRealizada_mvtoCarbon.get(select_MvtoCarbon_laborRealizada.getSelectedIndex()));
                                                                                
                                                                                if(listadoCentroCostoAuxiliarDestino_mvtoCarbon != null){
                                                                                    mvtoCarbon.setCentroCostoAuxiliarDestino(listadoCentroCostoAuxiliarDestino_mvtoCarbon.get(select_MvtoCarbon_CCAuxiliarDestino.getSelectedIndex()));
                                                                                }else{
                                                                                    mvtoCarbon.setCentroCostoAuxiliarDestino(null);
                                                                                }
                                                                                mvtoCarbon.setFechaRegistro("(SELECT SYSDATETIME())");//La fecha de registro es la fecha del sistema donde se encuentra registrada la base de datos 
                                                                                
                                                                                
                                                                                mvtoCarbon.setFechaInicioDescargue(fechaInicioDescargue);
                                                                                mvtoCarbon.setFechaFinDescargue(fechaFinDescargue);
                                                                                mvtoCarbon.setUsuarioRegistroMovil(listadoUsuarioInicioRegistro_mvtoCarbon.get(select_MvtoCarbon_UsuarioIniciaRegistro.getSelectedIndex()));
                                                                                mvtoCarbon.setUsuarioCierraRegistro(listadoUsuarioCierraRegistro_mvtoCarbon.get(select_MvtoCarbon_UsuarioCierraRegistro.getSelectedIndex()));
                                                                                EstadoMvtoCarbon estadoMvtoCarbon = new EstadoMvtoCarbon();
                                                                                estadoMvtoCarbon.setCodigo("1");
                                                                                mvtoCarbon.setEstadoMvtoCarbon(estadoMvtoCarbon);
                                                                                
                                                                                mvtoCarbon.setUsuarioRegistraManual(user);
                                                                                //Deshabilitamos los campos de MvtoCarbon
                                                                                fechaInicialActividad_MvtoCarbon.setEnabled(false);
                                                                                select_MvtoCarbon_HoraInicial.setEnabled(false);
                                                                                select_MvtoCarbon_MinutosInicial.setEnabled(false);
                                                                                select_MvtoCarbon_CO.setEnabled(false);
                                                                                select_MvtoCarbon_CCAuxiliar.setEnabled(false);
                                                                                select_MvtoCarbon_laborRealizada.setEnabled(false);
                                                                                select_MvtoCarbon_UsuarioCierraRegistro.setEnabled(false);

                                                                                fechaFinActividad_MvtoCarbon.setEnabled(false);
                                                                                select_MvtoCarbon_HoraFinal.setEnabled(false);
                                                                                select_MvtoCarbon_MinutosFinal.setEnabled(false);
                                                                                select_MvtoCarbon_SubcentroCosto.setEnabled(false);
                                                                                select_MvtoCarbon_CCAuxiliarDestino.setEnabled(false);
                                                                                select_MvtoCarbon_UsuarioIniciaRegistro.setEnabled(false);
                                                                                jButton3.setEnabled(false);


                                                                                //Mostramos opciones de MvtoEquipo
                                                                                seleccionadorPlaca.show(true);
                                                                                seleccionadorPlaca1.show(true);
                                                                                titulo67.show(true);
                                                                                fechaInicialActividad_MvtoEquipo.show(true);
                                                                                label_MvtoEquipo_HoraInicial.show(true);
                                                                                select_MvtoEquipo_HoraInicial.show(true);
                                                                                label_MvtoEquipo_separadorInicial.show(true);
                                                                                select_MvtoEquipo_MinutosInicial.show(true);
                                                                                label_MvtoEquipo_ZonaHorariaInicial.show(true);
                                                                                titulo64.show(true);
                                                                                fechaFinActividad_MvtoEquipo.show(true);
                                                                                label_MvtoEquipo_HoraFinal.show(true);
                                                                                select_MvtoEquipo_HoraFinal.show(true);
                                                                                label_MvtoEquipo_separadorFinal.show(true);
                                                                                select_MvtoEquipo_MinutosFinal.show(true);
                                                                                label_MvtoEquipo_ZonaHorariaFinal.show(true);
                                                                                titulo65.show(true);
                                                                                select_MvtoEquipo_tipoEquipo.show(true);
                                                                                titulo61.show(true);
                                                                                select_MvtoEquipo_marcaEquipo.show(true);
                                                                                titulo62.show(true);
                                                                                select_MvtoEquipo_Equipo.show(true);
                                                                                titulo48.show(true);
                                                                                titulo63.show(true);
                                                                                select_MvtoEquipo_recobro.show(true);
                                                                                titulo66.show(true);
                                                                                select_MvtoEquipo_UsuarioIniciaRegistro.show(true);
                                                                                titulo62.show(true);
                                                                                select_MvtoEquipo_UsuarioCierraRegistro.show(true);
                                                                                titulo30.show(true);
                                                                                mvtoEquipo_selectRazonFinalzación.show(true);
                                                                                titulo47.show(true);
                                                                                select_MvtoEquipo_lavadoVehículo.show(true);
                                                                                titulo52.show(true);
                                                                                select_MvtoCarbon_motivoNoLavadoVehiculo.show(true);
                                                                                titulo68.show(false);
                                                                                jButton1.show(true);
                                                                                tabla_ListadoMvtoCarbonListadoEquipo.show(true);
                                                                                titulo69.show(true);
                                                                                titulo70.show(true);
                                                                                select_MvtoEquipo_lavadoVehículo.setSelectedIndex(0);
                                                                                select_MvtoEquipo_recobro.setSelectedIndex(0);
                                                                                
                                                                                //Cargamos los equipos programados según fecha de inicio descargue
                                                                                   //Cargamos los equipos disponible en el rango de fecha.
                                                                                listadoTipoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarTipoEquiposProgramados(mvtoCarbon.getFechaInicioDescargue());
                                                                                System.out.println(mvtoCarbon.getFechaInicioDescargue());
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
                                                                                    listadoMarcaEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarMarcaEquiposProgramados(mvtoCarbon.getFechaInicioDescargue(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion());
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
                                                                                    listadoEquipos_mvtoEquipo = new ControlDB_MvtoEquipo(tipoConexion).buscarAsignacionEquipos_Por_TipoEquipo_MarcaEquipo(mvtoCarbon.getFechaInicioDescargue(), listadoTipoEquipos_mvtoEquipo.get(select_MvtoEquipo_tipoEquipo.getSelectedIndex()).getDescripcion(),listadoMarcaEquipos_mvtoEquipo.get(select_MvtoEquipo_marcaEquipo.getSelectedIndex()));
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
                                                                                
                                                                            }else{
                                                                                JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario de cierre de actividad", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                                                            }
                                                                        }else{
                                                                            JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario de inicio de actividad", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                                                        }
                                                                    }else{
                                                                        JOptionPane.showMessageDialog(null, "Debe seleccionar una labor Realizada", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                                                    }
                                                                //}
                                                            }else{
                                                                JOptionPane.showMessageDialog(null, "Debe seleccionar un centro de costo auxiliar", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                                            }
                                                        }else{
                                                            JOptionPane.showMessageDialog(null, "Debe seleccionar un subcentro de operación", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                                        }
                                                }else{
                                                    JOptionPane.showMessageDialog(null, "Debe seleccionar un centro de operación", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                                }            
                                            }
                                        }
                                    }
                                //}else{
                                  //  JOptionPane.showMessageDialog(null,"Error!!.. No puede cargar una fecha en el pasado, la fecha de Finalización de la Operación debe ser futura, verifique fecha","Advertencia",JOptionPane.ERROR_MESSAGE);
                               // }
                            }catch(Exception e){
                                JOptionPane.showMessageDialog(null,"Error!!.. Al trata de validar fecha actual del registro con fecha del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                            }
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Finalización","Advertencia",JOptionPane.ERROR_MESSAGE);
                        }
                    //}else{
                      //  JOptionPane.showMessageDialog(null,"Error!!.. No puede cargar una fecha en el pasado, la fecha de Inicio de Operación debe ser futura, verifique fecha","Advertencia",JOptionPane.ERROR_MESSAGE);
                    //}
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,"Error!!.. Al trata de validar fecha actual del registro con fecha del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Inicio","Advertencia",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null,"ERROR!!!, Debe cargar un vehiculo, para proceder a registrar", "Advertencia",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        VentanaInterna_buscarVehiculo.show(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        //Eliminar todos los elementos de una tabla Asignación temporal
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
                                    JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser mayor a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                                }else{
                                    if(resultDosFechas ==0){
                                        JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser Igual a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                                    }else{
                                        //Validamos que cargue una placa
                                        //if(buscadorPlaca.getText().equals("")){
                                          // JOptionPane.showMessageDialog(null, "Error!!.. Digite una placa, no puede ir un campo vacio","Advertencia", JOptionPane.ERROR_MESSAGE );
                                        //}else{
                                             // DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "Nombre","Estado","BaseDeDatos"});  
                                             DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"PLACA", "FECHA_TARA","FECHA_DESTARE","PESO NETO","CLIENTE","TRANSPORTADORA","MOTONAVE"});  
                                             listadoMvtoCarbon=new ControlDB_MvtoCarbon(tipoConexion).buscarVehiculosAnadirVehiculos(buscadorPlaca.getText(), fechaInicio_consultaVehiculosDestarados, fechaFin_consultaVehiculosDestarado);
                                            if(listadoMvtoCarbon != null){
                                                for(MvtoCarbon  Objeto:listadoMvtoCarbon){
                                                   String []registro = new String[7];
                                                   registro[0]=""+Objeto.getPlaca();
                                                   registro[1]=""+Objeto.getFechaEntradaVehiculo();            
                                                   registro[2]=""+Objeto.getFecha_SalidaVehiculo();  
                                                   registro[3]=""+Objeto.getPesoNeto();   
                                                   registro[4]=""+Objeto.getCliente().getDescripcion();
                                                   registro[5]=""+Objeto.getTransportadora().getDescripcion();            
                                                   registro[6]=""+Objeto.getMotonave().getDescripcion();            
                                                   modelo.addRow(registro);
                                                }
                                                tabla1.setModel(modelo);
                                            }
                                            
                                        //}
                                    }
                                }
                            //}else{
                              //  JOptionPane.showMessageDialog(null,"Error!!.. No puede cargar una fecha en el pasado, la fecha de Finalización de la Operación debe ser futura, verifique fecha","Advertencia",JOptionPane.ERROR_MESSAGE);
                            //}
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(null,"Error!!.. Al trata de validar fecha actual del registro con fecha del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                        }
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Finalización","Advertencia",JOptionPane.ERROR_MESSAGE);
                    }
                //}else{
                  //  JOptionPane.showMessageDialog(null,"Error!!.. No puede cargar una fecha en el pasado, la fecha de Inicio de Operación debe ser futura, verifique fecha","Advertencia",JOptionPane.ERROR_MESSAGE);
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
        VentanaInterna_buscarVehiculo.show(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla1MouseClicked
        if (evt.getClickCount() == 2) {
            int fila1;
            try{
                fila1=tabla1.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    if(listadoMvtoCarbon != null){
                        mvtoCarbon=null;
                        mvtoCarbon=listadoMvtoCarbon.get(fila1);  
                        if(mvtoCarbon != null){
                            if (new ControlDB_MvtoCarbon(tipoConexion).validarExistenciaMvtoCarbon(mvtoCarbon)) {
                                int opcion  =JOptionPane.showConfirmDialog(null,"Ya se registro este vehículo desea registrarlo nuevamente?.");
                                if(opcion==0){//Confirmo que si desea realiza el nuevo registro
                                    //Limpiamos los campos de la interfaz
                                    MvtCarbon_placa.setText("");
                                    MvtCarbon_fechaEntradaVehiculo.setText("");
                                    MvtCarbon_fechaSalidaVehiculo.setText("");
                                    MvtCarbon_deposito.setText("");
                                    MvtCarbon_consecutivo.setText("");
                                    MvtCarbon_cliente.setText("");
                                    MvtCarbon_transportadora.setText("");
                                    MvtCarbon_articulo.setText("");
                                    MvtCarbon_pesoVacio.setText("");
                                    MvtCarbon_pesoLleno.setText("");
                                    MvtCarbon_pesoNeto.setText("");
                                    seleccionadorPlaca.setText("PLACA SELECCIONADA:");
                                    seleccionadorPlaca1.setText("");

                                    //Cargamos los datos en la interfaz
                                    MvtCarbon_placa.setText(mvtoCarbon.getPlaca());
                                    MvtCarbon_fechaEntradaVehiculo.setText(mvtoCarbon.getFechaEntradaVehiculo());
                                    MvtCarbon_fechaSalidaVehiculo.setText(mvtoCarbon.getFecha_SalidaVehiculo());
                                    MvtCarbon_deposito.setText(mvtoCarbon.getDeposito());
                                    MvtCarbon_consecutivo.setText(mvtoCarbon.getConsecutivo());
                                    MvtCarbon_cliente.setText(mvtoCarbon.getCliente().getDescripcion());
                                    MvtCarbon_transportadora.setText(mvtoCarbon.getTransportadora().getDescripcion());
                                    MvtCarbon_articulo.setText(mvtoCarbon.getArticulo().getDescripcion());
                                    MvtCarbon_pesoVacio.setText(mvtoCarbon.getPesoVacio());
                                    MvtCarbon_pesoLleno.setText(mvtoCarbon.getPesoLleno());
                                    MvtCarbon_pesoNeto.setText(mvtoCarbon.getPesoNeto());
                                    seleccionadorPlaca.setText("PLACA SELECCIONADA:");
                                    seleccionadorPlaca1.setText(mvtoCarbon.getPlaca());

                                    showInterfaceSeleccionarVehiculo();
                                    //Ocultamos la venta interna de busqueda de vehículos.
                                    VentanaInterna_buscarVehiculo.show(false);
                                }
                            }else{
                                MvtCarbon_placa.setText("");
                                MvtCarbon_fechaEntradaVehiculo.setText("");
                                MvtCarbon_fechaSalidaVehiculo.setText("");
                                MvtCarbon_deposito.setText("");
                                MvtCarbon_consecutivo.setText("");
                                MvtCarbon_cliente.setText("");
                                MvtCarbon_transportadora.setText("");
                                MvtCarbon_articulo.setText("");
                                MvtCarbon_pesoVacio.setText("");
                                MvtCarbon_pesoLleno.setText("");
                                MvtCarbon_pesoNeto.setText("");
                                seleccionadorPlaca.setText("PLACA SELECCIONADA:");
                                seleccionadorPlaca1.setText("");

                                //Cargamos los datos en la interfaz
                                MvtCarbon_placa.setText(mvtoCarbon.getPlaca());
                                MvtCarbon_fechaEntradaVehiculo.setText(mvtoCarbon.getFechaEntradaVehiculo());
                                MvtCarbon_fechaSalidaVehiculo.setText(mvtoCarbon.getFecha_SalidaVehiculo());
                                MvtCarbon_deposito.setText(mvtoCarbon.getDeposito());
                                MvtCarbon_consecutivo.setText(mvtoCarbon.getConsecutivo());
                                MvtCarbon_cliente.setText(mvtoCarbon.getCliente().getDescripcion());
                                MvtCarbon_transportadora.setText(mvtoCarbon.getTransportadora().getDescripcion());
                                MvtCarbon_articulo.setText(mvtoCarbon.getArticulo().getDescripcion());
                                MvtCarbon_pesoVacio.setText(mvtoCarbon.getPesoVacio());
                                MvtCarbon_pesoLleno.setText(mvtoCarbon.getPesoLleno());
                                MvtCarbon_pesoNeto.setText(mvtoCarbon.getPesoNeto());
                                seleccionadorPlaca.setText("PLACA SELECCIONADA:");
                                seleccionadorPlaca1.setText(mvtoCarbon.getPlaca());

                                showInterfaceSeleccionarVehiculo();
                                //Ocultamos la venta interna de busqueda de vehículos.
                                VentanaInterna_buscarVehiculo.show(false);
                            }
                        }
                    } 
                }
            }catch(Exception e){
                e.printStackTrace();
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

    private void buscadorPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscadorPlacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscadorPlacaActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        int fila1;
        try{
            fila1=tabla1.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                if(listadoMvtoCarbon != null){
                    mvtoCarbon=null;
                    mvtoCarbon=listadoMvtoCarbon.get(fila1);  
                    if(mvtoCarbon != null){
                        if (new ControlDB_MvtoCarbon(tipoConexion).validarExistenciaMvtoCarbon(mvtoCarbon)) {
                            int opcion  =JOptionPane.showConfirmDialog(null,"Ya se registro este vehículo desea registrarlo nuevamente?.");
                            if(opcion==0){//Confirmo que si desea realiza el nuevo registro
                                //Limpiamos los campos de la interfaz
                                MvtCarbon_placa.setText("");
                                MvtCarbon_fechaEntradaVehiculo.setText("");
                                MvtCarbon_fechaSalidaVehiculo.setText("");
                                MvtCarbon_deposito.setText("");
                                MvtCarbon_consecutivo.setText("");
                                MvtCarbon_cliente.setText("");
                                MvtCarbon_transportadora.setText("");
                                MvtCarbon_articulo.setText("");
                                MvtCarbon_pesoVacio.setText("");
                                MvtCarbon_pesoLleno.setText("");
                                MvtCarbon_pesoNeto.setText("");
                                seleccionadorPlaca.setText("PLACA SELECCIONADA:");
                                seleccionadorPlaca1.setText("");

                                //Cargamos los datos en la interfaz
                                MvtCarbon_placa.setText(mvtoCarbon.getPlaca());
                                MvtCarbon_fechaEntradaVehiculo.setText(mvtoCarbon.getFechaEntradaVehiculo());
                                MvtCarbon_fechaSalidaVehiculo.setText(mvtoCarbon.getFecha_SalidaVehiculo());
                                MvtCarbon_deposito.setText(mvtoCarbon.getDeposito());
                                MvtCarbon_consecutivo.setText(mvtoCarbon.getConsecutivo());
                                MvtCarbon_cliente.setText(mvtoCarbon.getCliente().getDescripcion());
                                MvtCarbon_transportadora.setText(mvtoCarbon.getTransportadora().getDescripcion());
                                MvtCarbon_articulo.setText(mvtoCarbon.getArticulo().getDescripcion());
                                MvtCarbon_pesoVacio.setText(mvtoCarbon.getPesoVacio());
                                MvtCarbon_pesoLleno.setText(mvtoCarbon.getPesoLleno());
                                MvtCarbon_pesoNeto.setText(mvtoCarbon.getPesoNeto());
                                seleccionadorPlaca.setText("PLACA SELECCIONADA:");
                                seleccionadorPlaca1.setText(mvtoCarbon.getPlaca());

                                showInterfaceSeleccionarVehiculo();
                                //Ocultamos la venta interna de busqueda de vehículos.
                                VentanaInterna_buscarVehiculo.show(false);
                            }
                        }else{
                            MvtCarbon_placa.setText("");
                            MvtCarbon_fechaEntradaVehiculo.setText("");
                            MvtCarbon_fechaSalidaVehiculo.setText("");
                            MvtCarbon_deposito.setText("");
                            MvtCarbon_consecutivo.setText("");
                            MvtCarbon_cliente.setText("");
                            MvtCarbon_transportadora.setText("");
                            MvtCarbon_articulo.setText("");
                            MvtCarbon_pesoVacio.setText("");
                            MvtCarbon_pesoLleno.setText("");
                            MvtCarbon_pesoNeto.setText("");
                            seleccionadorPlaca.setText("PLACA SELECCIONADA:");
                            seleccionadorPlaca1.setText("");

                            //Cargamos los datos en la interfaz
                            MvtCarbon_placa.setText(mvtoCarbon.getPlaca());
                            MvtCarbon_fechaEntradaVehiculo.setText(mvtoCarbon.getFechaEntradaVehiculo());
                            MvtCarbon_fechaSalidaVehiculo.setText(mvtoCarbon.getFecha_SalidaVehiculo());
                            MvtCarbon_deposito.setText(mvtoCarbon.getDeposito());
                            MvtCarbon_consecutivo.setText(mvtoCarbon.getConsecutivo());
                            MvtCarbon_cliente.setText(mvtoCarbon.getCliente().getDescripcion());
                            MvtCarbon_transportadora.setText(mvtoCarbon.getTransportadora().getDescripcion());
                            MvtCarbon_articulo.setText(mvtoCarbon.getArticulo().getDescripcion());
                            MvtCarbon_pesoVacio.setText(mvtoCarbon.getPesoVacio());
                            MvtCarbon_pesoLleno.setText(mvtoCarbon.getPesoLleno());
                            MvtCarbon_pesoNeto.setText(mvtoCarbon.getPesoNeto());
                            seleccionadorPlaca.setText("PLACA SELECCIONADA:");
                            seleccionadorPlaca1.setText(mvtoCarbon.getPlaca());

                            showInterfaceSeleccionarVehiculo();
                            //Ocultamos la venta interna de busqueda de vehículos.
                            VentanaInterna_buscarVehiculo.show(false);
                        }
                    }
                } 
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void tabla_ListadoMvtoCarbonListadoEquipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ListadoMvtoCarbonListadoEquipoMouseClicked
        /*if (evt.getClickCount() == 2) {
            int fila1;
            try{
                fila1=tabla_ListadoMvtoCarbonListadoEquipo.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ningún registro");
                }
                else{
                    int opcion = JOptionPane.showConfirmDialog(null, "Esta seguro que desea quitar este registro?","Advertencia", JOptionPane.INFORMATION_MESSAGE);
                    if(opcion ==0){//Procedemos a eliminar el registro
                        listadoMvtoCarbon_ListadoEquipos.remove(fila1);
                        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"C.O","SubCentro","CentroCostoAuxiliar","Equipo_Descripción","Fecha_Y_Hora_Inicio","Fecha_Y_Hora_Fin", "LavadoVhículo","Recobro","Motivo No Lavado"});      
                        for (MvtoCarbon_ListadoEquipos Objeto : listadoMvtoCarbon_ListadoEquipos) {
                            String[] registro = new String[9];
                            registro[0] = "" + Objeto.getMvtoEquipoAgregar().getCentroOperacion().getDescripcion();
                            registro[1] = "" + Objeto.getMvtoEquipoAgregar().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                            registro[2] = "" + Objeto.getMvtoEquipoAgregar().getCentroCostoAuxiliar().getDescripcion();
                            registro[3] = "" + Objeto.getMvtoEquipoAgregar().getAsignacionEquipo().getEquipo().getDescripcion()+" "+Objeto.getMvtoEquipoAgregar().getAsignacionEquipo().getEquipo().getModelo();
                            registro[4] = "" + Objeto.getMvtoEquipoAgregar().getFechaHoraInicio();
                            registro[5] = "" + Objeto.getMvtoEquipoAgregar().getFechaHoraFin();
                            if(Objeto.getMvtoEquipoAgregar().getLavadoVehiculo().equals("0")){
                                registro[6] = "NO";
                            }else{
                                 registro[6] = "SI";
                            }
                            registro[7] = Objeto.getMvtoEquipoAgregar().getRecobro().getDescripcion();
                            if(Objeto.getMvtoEquipoAgregar().getMotivoNoLavado()!=null){
                                registro[8] =  "" + Objeto.getMvtoEquipoAgregar().getMotivoNoLavado().getDescripcion();
                            }else{
                                 registro[8] = "";
                            }

                            modelo.addRow(registro);      
                        }
                        tabla_ListadoMvtoCarbonListadoEquipo.setModel(modelo);
                    }
                }
            }catch(Exception e){
            }
        }*/
    }//GEN-LAST:event_tabla_ListadoMvtoCarbonListadoEquipoMouseClicked

    private void EliminarMvtoEquipo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarMvtoEquipo2ActionPerformed
        int fila1;
        try{
            fila1=tabla_ListadoMvtoCarbonListadoEquipo.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ningún registro");
            }
            else{
                int opcion = JOptionPane.showConfirmDialog(null, "Esta seguro que desea quitar este registro?","Advertencia", JOptionPane.INFORMATION_MESSAGE);
                if(opcion ==0){//Procedemos a eliminar el registro
                    listadoMvtoCarbon_ListadoEquipos.remove(fila1);
                    DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"C.O","SubCentro","CentroCostoAuxiliar","Equipo_Descripción","Fecha_Y_Hora_Inicio","Fecha_Y_Hora_Fin","Razón Finalización" ,"Recobro","LavadoVhículo","Motivo No Lavado"});      
                    for (MvtoCarbon_ListadoEquipos Objeto : listadoMvtoCarbon_ListadoEquipos) {
                        String[] registro = new String[10];
                        registro[0] = "" + Objeto.getMvtoEquipoAgregar().getCentroOperacion().getDescripcion();
                        registro[1] = "" + Objeto.getMvtoEquipoAgregar().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                        registro[2] = "" + Objeto.getMvtoEquipoAgregar().getCentroCostoAuxiliar().getDescripcion();
                        registro[3] = "" + Objeto.getMvtoEquipoAgregar().getAsignacionEquipo().getEquipo().getDescripcion()+" "+Objeto.getMvtoEquipoAgregar().getAsignacionEquipo().getEquipo().getModelo();
                        registro[4] = "" + Objeto.getMvtoEquipoAgregar().getFechaHoraInicio();
                        registro[5] = "" + Objeto.getMvtoEquipoAgregar().getFechaHoraFin();
                        registro[6] = "" + Objeto.getMvtoEquipoAgregar().getMotivoParada().getDescripcion();
                        registro[7] = Objeto.getMvtoEquipoAgregar().getRecobro().getDescripcion();
                        if(Objeto.getMvtoEquipoAgregar().getLavadoVehiculo().equals("0")){
                            registro[8] = "NO";
                        }else{
                             registro[8] = "SI";
                        }

                        if(Objeto.getMvtoEquipoAgregar().getMotivoNoLavado()!=null){
                            registro[9] =  "" + Objeto.getMvtoEquipoAgregar().getMotivoNoLavado().getDescripcion();
                        }else{
                            registro[9] = "";
                        }

                        modelo.addRow(registro);      
                    }
                    tabla_ListadoMvtoCarbonListadoEquipo.setModel(modelo);
                    
                    //listadoMvtoCarbon_ListadoEquipos.size();
                    try{    
                        if(listadoMvtoCarbon_ListadoEquipos.size() == 0){
                            btnRegistrar.show(false);
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    //btnRegistrar
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_EliminarMvtoEquipo2ActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        //mvtoCarbon
        boolean validarLavado = false;
        for(MvtoCarbon_ListadoEquipos mvtoCarbon_ListadoEquipos: listadoMvtoCarbon_ListadoEquipos){
            if(mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getLavadoVehiculo().equals("1")){ //Hay marcado un lavado de vehículo como si en al aplicación
                mvtoCarbon.setLavadoVehiculo(mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getLavadoVehiculo());    
                mvtoCarbon.setLavadoVehiculo_Observacion("NULL");
                mvtoCarbon.setEquipoLavadoVehiculo(mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getAsignacionEquipo().getEquipo());
                validarLavado= true;
                mvtoCarbon.setMotivoNoLavado(null);
            }
        }
        if(!validarLavado){//No se encuentro marcado lavado de vehículo por tal motivo procedemos a registrar los campos de no lavado de vehículo
            mvtoCarbon.setLavadoVehiculo("0");
            mvtoCarbon.setLavadoVehiculo_Observacion("NULL");
            Equipo equipo = new Equipo();
            equipo.setCodigo("NULL");
            mvtoCarbon.setEquipoLavadoVehiculo(equipo);
            mvtoCarbon.setMotivoNoLavado(listadoMvtoCarbon_ListadoEquipos.get(0).getMvtoEquipoAgregar().getMotivoNoLavado());// Agregamos el 0 porque debería se seleccionarse cualquiera, todo los registro deben de terner el mismo motivo de no lavado de vehículos     
        }
        int retorno =0;
        try {
            retorno= new ControlDB_MvtoCarbon(tipoConexion).registrarMvtoCarbonCompleto_functionAdd(mvtoCarbon, listadoMvtoCarbon_ListadoEquipos, user);
            if(retorno==1){
                JOptionPane.showMessageDialog(null, "Registro exitoso","Registrado", JOptionPane.INFORMATION_MESSAGE);
                listadoMvtoCarbon_ListadoEquipos = null;
        
                //Eliminar todos los elementos
                DefaultTableModel md =(DefaultTableModel)tabla_ListadoMvtoCarbonListadoEquipo.getModel();
                int CantEliminar= tabla_ListadoMvtoCarbonListadoEquipo.getRowCount() -1;
                for(int i =CantEliminar; i>=0; i--){
                        md.removeRow(i);
                }
                //habilitamos los campos de MvtoCarbon
                fechaInicialActividad_MvtoCarbon.setEnabled(false);
                select_MvtoCarbon_HoraInicial.setEnabled(false);
                select_MvtoCarbon_MinutosInicial.setEnabled(false);
                select_MvtoCarbon_CO.setEnabled(false);
                select_MvtoCarbon_CCAuxiliar.setEnabled(false);
                select_MvtoCarbon_laborRealizada.setEnabled(false);
                select_MvtoCarbon_UsuarioCierraRegistro.setEnabled(false);

                fechaFinActividad_MvtoCarbon.setEnabled(false);
                select_MvtoCarbon_HoraFinal.setEnabled(false);
                select_MvtoCarbon_MinutosFinal.setEnabled(false);
                select_MvtoCarbon_SubcentroCosto.setEnabled(false);
                select_MvtoCarbon_CCAuxiliarDestino.setEnabled(false);
                select_MvtoCarbon_UsuarioIniciaRegistro.setEnabled(false);
                jButton3.setEnabled(false);
        
        
                //Ocultamos datos del MvtoEquipo
                seleccionadorPlaca.show(false);
                seleccionadorPlaca1.show(false);
                titulo67.show(false);
                fechaInicialActividad_MvtoEquipo.show(false);
                label_MvtoEquipo_HoraInicial.show(false);
                select_MvtoEquipo_HoraInicial.show(false);
                label_MvtoEquipo_separadorInicial.show(false);
                select_MvtoEquipo_MinutosInicial.show(false);
                label_MvtoEquipo_ZonaHorariaInicial.show(false);
                titulo64.show(false);
                fechaFinActividad_MvtoEquipo.show(false);
                label_MvtoEquipo_HoraFinal.show(false);
                select_MvtoEquipo_HoraFinal.show(false);
                label_MvtoEquipo_separadorFinal.show(false);
                select_MvtoEquipo_MinutosFinal.show(false);
                label_MvtoEquipo_ZonaHorariaFinal.show(false);
                titulo65.show(false);
                select_MvtoEquipo_tipoEquipo.show(false);
                titulo61.show(false);
                select_MvtoEquipo_marcaEquipo.show(false);
                titulo62.show(false);
                titulo63.show(false);
                select_MvtoEquipo_Equipo.show(false);
                titulo48.show(false);
                select_MvtoEquipo_recobro.show(false);
                titulo66.show(false);
                select_MvtoEquipo_UsuarioIniciaRegistro.show(false);
                titulo62.show(false);
                select_MvtoEquipo_UsuarioCierraRegistro.show(false);
                titulo30.show(false);
                mvtoEquipo_selectRazonFinalzación.show(false);
                titulo47.show(false);
                select_MvtoEquipo_lavadoVehículo.show(false);
                titulo52.show(false);
                select_MvtoCarbon_motivoNoLavadoVehiculo.show(false);

                jButton1.show(false);
                btnRegistrar.show(false);

                titulo62.show(false);
                select_MvtoEquipo_UsuarioQuienAutorizaRecobro.show(false);
                titulo68.show(false);
                titulo69.show(false);
                titulo70.show(false);
                tabla_ListadoMvtoCarbonListadoEquipo.show(false);
                //removeAll();
                //revalidate();
                //repaint();
            }else{
                JOptionPane.showMessageDialog(null, "Error al registrar","Error!!", JOptionPane.ERROR_MESSAGE);
            }
            /**
             * MvtoCarbon_ListadoEquipos mvtoCarbon_ListadoEquipos = new MvtoCarbon_ListadoEquipos();
             * mvtoCarbon_ListadoEquipos.setAsignacionEquipo(mvtoEquipoAgregar.getAsignacionEquipo());
             * mvtoCarbon_ListadoEquipos.setMvtoCarbon(mvtoCarbon);
             * mvtoCarbon_ListadoEquipos.setMvtoEquipoAgregar(mvtoEquipoAgregar);
             * mvtoCarbon_ListadoEquipos.setEstado("1");
             * listadoMvtoCarbon_ListadoEquipos.add(mvtoCarbon_ListadoEquipos);
             * 
             * 
             * 
             * 
             */
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MvtoCarbon_AgregarRegistro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(MvtoCarbon_AgregarRegistro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(MvtoCarbon_AgregarRegistro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MvtoCarbon_AgregarRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void select_MvtoEquipo_UsuarioQuienAutorizaRecobroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_UsuarioQuienAutorizaRecobroItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_UsuarioQuienAutorizaRecobroItemStateChanged

    private void select_MvtoEquipo_UsuarioQuienAutorizaRecobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_UsuarioQuienAutorizaRecobroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_MvtoEquipo_UsuarioQuienAutorizaRecobroActionPerformed

    private void select_MvtoEquipo_recobroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoEquipo_recobroItemStateChanged
        if(listadoRecobros_mvtoEquipo.get(select_MvtoEquipo_recobro.getSelectedIndex()).getCodigo().equals("1")){//Seleccionó SI al recobro
            titulo62.show(true);
            select_MvtoEquipo_UsuarioQuienAutorizaRecobro.show(true);
        }else{
            if(listadoRecobros_mvtoEquipo.get(select_MvtoEquipo_recobro.getSelectedIndex()).getCodigo().equals("0")){//Seleccionó NO al recobro
                titulo62.show(false);
                select_MvtoEquipo_UsuarioQuienAutorizaRecobro.show(false);
            }   
        }
    }//GEN-LAST:event_select_MvtoEquipo_recobroItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu EliminarMvtoEquipo;
    private javax.swing.JMenuItem EliminarMvtoEquipo2;
    private javax.swing.JLabel MvtCarbon_articulo;
    private javax.swing.JLabel MvtCarbon_cliente;
    private javax.swing.JLabel MvtCarbon_consecutivo;
    private javax.swing.JLabel MvtCarbon_deposito;
    private javax.swing.JLabel MvtCarbon_fechaEntradaVehiculo;
    private javax.swing.JLabel MvtCarbon_fechaSalidaVehiculo;
    private javax.swing.JLabel MvtCarbon_pesoLleno;
    private javax.swing.JLabel MvtCarbon_pesoNeto;
    private javax.swing.JLabel MvtCarbon_pesoVacio;
    private javax.swing.JLabel MvtCarbon_placa;
    private javax.swing.JLabel MvtCarbon_transportadora;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JInternalFrame VentanaInterna_buscarVehiculo;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JTextField buscadorPlaca;
    private com.toedter.calendar.JDateChooser fechaFinActividad_MvtoCarbon;
    private com.toedter.calendar.JDateChooser fechaFinActividad_MvtoEquipo;
    private com.toedter.calendar.JDateChooser fechaFin_destareVehiculo;
    private com.toedter.calendar.JDateChooser fechaInicialActividad_MvtoCarbon;
    private com.toedter.calendar.JDateChooser fechaInicialActividad_MvtoEquipo;
    private com.toedter.calendar.JDateChooser fechaInicio_destareVehiculo;
    private javax.swing.JComboBox<String> horaFin_destareVehiculo;
    private javax.swing.JComboBox<String> horaInicio_destareVehiculo;
    private javax.swing.JLabel horarioTiempoIFinal_destareVehiculo;
    private javax.swing.JLabel horarioTiempoInicio_destareVehiculo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
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
    private javax.swing.JComboBox<String> minutoFin_destareVehiculo;
    private javax.swing.JComboBox<String> minutoInicio_destareVehiculo;
    private javax.swing.JComboBox<String> mvtoEquipo_selectRazonFinalzación;
    private javax.swing.JLabel seleccionadorPlaca;
    private javax.swing.JLabel seleccionadorPlaca1;
    private javax.swing.JComboBox<String> select_MvtoCarbon_CCAuxiliar;
    private javax.swing.JComboBox<String> select_MvtoCarbon_CCAuxiliarDestino;
    private javax.swing.JComboBox<String> select_MvtoCarbon_CO;
    private javax.swing.JComboBox<String> select_MvtoCarbon_HoraFinal;
    private javax.swing.JComboBox<String> select_MvtoCarbon_HoraInicial;
    private javax.swing.JComboBox<String> select_MvtoCarbon_MinutosFinal;
    private javax.swing.JComboBox<String> select_MvtoCarbon_MinutosInicial;
    private javax.swing.JComboBox<String> select_MvtoCarbon_SubcentroCosto;
    private javax.swing.JComboBox<String> select_MvtoCarbon_UsuarioCierraRegistro;
    private javax.swing.JComboBox<String> select_MvtoCarbon_UsuarioIniciaRegistro;
    private javax.swing.JComboBox<String> select_MvtoCarbon_laborRealizada;
    private javax.swing.JComboBox<String> select_MvtoCarbon_motivoNoLavadoVehiculo;
    private javax.swing.JComboBox<String> select_MvtoEquipo_Equipo;
    private javax.swing.JComboBox<String> select_MvtoEquipo_HoraFinal;
    private javax.swing.JComboBox<String> select_MvtoEquipo_HoraInicial;
    private javax.swing.JComboBox<String> select_MvtoEquipo_MinutosFinal;
    private javax.swing.JComboBox<String> select_MvtoEquipo_MinutosInicial;
    private javax.swing.JComboBox<String> select_MvtoEquipo_UsuarioCierraRegistro;
    private javax.swing.JComboBox<String> select_MvtoEquipo_UsuarioIniciaRegistro;
    private javax.swing.JComboBox<String> select_MvtoEquipo_UsuarioQuienAutorizaRecobro;
    private javax.swing.JComboBox<String> select_MvtoEquipo_lavadoVehículo;
    private javax.swing.JComboBox<String> select_MvtoEquipo_marcaEquipo;
    private javax.swing.JComboBox<String> select_MvtoEquipo_recobro;
    private javax.swing.JComboBox<String> select_MvtoEquipo_tipoEquipo;
    private javax.swing.JTable tabla1;
    private javax.swing.JTable tabla_ListadoMvtoCarbonListadoEquipo;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel titulo25;
    private javax.swing.JLabel titulo29;
    private javax.swing.JLabel titulo30;
    private javax.swing.JLabel titulo31;
    private javax.swing.JLabel titulo32;
    private javax.swing.JLabel titulo45;
    private javax.swing.JLabel titulo46;
    private javax.swing.JLabel titulo47;
    private javax.swing.JLabel titulo48;
    private javax.swing.JLabel titulo49;
    private javax.swing.JLabel titulo50;
    private javax.swing.JLabel titulo51;
    private javax.swing.JLabel titulo52;
    private javax.swing.JLabel titulo61;
    private javax.swing.JLabel titulo62;
    private javax.swing.JLabel titulo63;
    private javax.swing.JLabel titulo64;
    private javax.swing.JLabel titulo65;
    private javax.swing.JLabel titulo66;
    private javax.swing.JLabel titulo67;
    private javax.swing.JLabel titulo68;
    private javax.swing.JLabel titulo69;
    private javax.swing.JLabel titulo70;
    // End of variables declaration//GEN-END:variables
   
    
    
    
    
    public void showInterfaceSeleccionarVehiculo(){
        
        //habilitamos los campos de MvtoCarbon
        fechaInicialActividad_MvtoCarbon.setEnabled(true);
        select_MvtoCarbon_HoraInicial.setEnabled(true);
        select_MvtoCarbon_MinutosInicial.setEnabled(true);
        select_MvtoCarbon_CO.setEnabled(true);
        select_MvtoCarbon_CCAuxiliar.setEnabled(true);
        select_MvtoCarbon_laborRealizada.setEnabled(true);
        select_MvtoCarbon_UsuarioCierraRegistro.setEnabled(true);

        fechaFinActividad_MvtoCarbon.setEnabled(true);
        select_MvtoCarbon_HoraFinal.setEnabled(true);
        select_MvtoCarbon_MinutosFinal.setEnabled(true);
        select_MvtoCarbon_SubcentroCosto.setEnabled(true);
        select_MvtoCarbon_CCAuxiliarDestino.setEnabled(true);
        select_MvtoCarbon_UsuarioIniciaRegistro.setEnabled(true);
        jButton3.setEnabled(true);


        //Mostramos opciones de MvtoEquipo
        seleccionadorPlaca.show(false);
        seleccionadorPlaca1.show(false);
        titulo67.show(false);
        fechaInicialActividad_MvtoEquipo.show(false);
        label_MvtoEquipo_HoraInicial.show(false);
        select_MvtoEquipo_HoraInicial.show(false);
        label_MvtoEquipo_separadorInicial.show(false);
        select_MvtoEquipo_MinutosInicial.show(false);
        label_MvtoEquipo_ZonaHorariaInicial.show(false);
        titulo64.show(false);
        fechaFinActividad_MvtoEquipo.show(false);
        label_MvtoEquipo_HoraFinal.show(false);
        select_MvtoEquipo_HoraFinal.show(false);
        label_MvtoEquipo_separadorFinal.show(false);
        select_MvtoEquipo_MinutosFinal.show(false);
        label_MvtoEquipo_ZonaHorariaFinal.show(false);
        titulo65.show(false);
        select_MvtoEquipo_tipoEquipo.show(false);
        titulo61.show(false);
        select_MvtoEquipo_marcaEquipo.show(false);
        titulo62.show(false);
        select_MvtoEquipo_Equipo.show(false);
        titulo48.show(false);
        titulo63.show(false);
        select_MvtoEquipo_recobro.show(false);
        titulo66.show(false);
        select_MvtoEquipo_UsuarioIniciaRegistro.show(false);
        titulo62.show(false);
        select_MvtoEquipo_UsuarioCierraRegistro.show(false);
        titulo30.show(false);
        mvtoEquipo_selectRazonFinalzación.show(false);
        titulo47.show(false);
        select_MvtoEquipo_lavadoVehículo.show(false);
        titulo68.show(false);
        jButton1.show(false);
        btnRegistrar.show(false);
        select_MvtoEquipo_UsuarioQuienAutorizaRecobro.show(false);
        select_MvtoCarbon_motivoNoLavadoVehiculo.show(false);
        titulo52.show(false);
        tabla_ListadoMvtoCarbonListadoEquipo.show(false);
        
        listadoMvtoCarbon_ListadoEquipos = null;
        
        //Eliminar todos los elementos
        DefaultTableModel md =(DefaultTableModel)tabla_ListadoMvtoCarbonListadoEquipo.getModel();
        int CantEliminar= tabla_ListadoMvtoCarbonListadoEquipo.getRowCount() -1;
        for(int i =CantEliminar; i>=0; i--){
                md.removeRow(i);
        }
        
    }

}
