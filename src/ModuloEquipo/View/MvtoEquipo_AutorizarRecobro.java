package ModuloEquipo.View;

import ModuloEquipo.Model.Recobro;
import ModuloEquipo.Model.MvtoEquipo;
import ModuloEquipo.Model.AutorizacionRecobro;
import ModuloCarbon.Controller2.ControlDB_MvtoCarbon;
import ModuloCarbon.Model.MvtoCarbon_ListadoEquipos;
import Catalogo.Controller.ControlDB_Equipo;
import ModuloEquipo.Controller.ControlDB_MvtoEquipo;
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
 
public final class MvtoEquipo_AutorizarRecobro extends javax.swing.JPanel implements ActionListener, TableModelListener{
    //ArrayList<MvtoEquipo> listado=null;
    private Usuario user;
    MvtoEquipo equipo;
    private ArrayList<AutorizacionRecobro> listadoAutorizacionRecobro= new ArrayList();
    MvtoCarbon_ListadoEquipos mvto_listEquipo = null;
    private String tipoConexion;
    
     ArrayList<MvtoEquipo> listado=null;
    //private final Usuario user;
    //private final String tipoConexion;
    private PaginadorDeTabla<MvtoEquipo> paginadorDeTabla;  
    ProveedorDeDatosDePaginacion<MvtoEquipo> proveedorDeDatos;
    ArrayList<String> encabezadoTabla=null;
    public MvtoEquipo_AutorizarRecobro(Usuario user, String tipoConexion) {
        initComponents();
        this.tipoConexion= tipoConexion;
        this.user= user;
        equipo = null;
        InternalFrameSelectorCampos.getContentPane().setBackground(Color.WHITE);
        InternaFrame_VisualizarMvtoEquipo.getContentPane().setBackground(Color.WHITE);
        InternaFrame_VisualizarMvtoCarbon.getContentPane().setBackground(Color.WHITE);
        InternalFrameSelectorCampos.show(false);
        InternaFrame_VisualizarMvtoEquipo.show(false);
        InternaFrame_VisualizarMvtoCarbon.show(false);
        for(int i = 0; i<= 23; i++){
            if(i<=9){
                horaInicio.addItem("0"+i);
                horaFin.addItem("0"+i);
            }else{
                horaInicio.addItem(""+i);
                horaFin.addItem(""+i);
            }
        }
        for(int i = 0; i<= 59; i++){
            if(i<=9){
                minutoInicio.addItem("0"+i);
                minutoFin.addItem("0"+i);
            }else{
                minutoInicio.addItem(""+i);
                minutoFin.addItem(""+i);
            }
        }
        horaInicio.setSelectedIndex(0);
        minutoInicio.setSelectedIndex(0);
        horaFin.setSelectedIndex(23);
        minutoFin.setSelectedIndex(59);
        
        //Cargamos en la interfaz los estados de Solicitudes de Equipos
        listadoAutorizacionRecobro=new ControlDB_Equipo(tipoConexion).buscarAutorizacionRecobro();
        if(listadoAutorizacionRecobro != null){
            for(AutorizacionRecobro autorizacionRecobro : listadoAutorizacionRecobro ){
                estadoAutorizacion_MvtoCarbon.addItem(autorizacionRecobro.getDescripcion());
                estadoAutorizacion_MvtoEquipo.addItem(autorizacionRecobro.getDescripcion());
                
            }
        } 
        selectorCampoPorDefecto();
        encabezadoTabla= new ArrayList<String>();
        pageJComboBox.show(false);
        //jProgressBar1.show(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Visualizar = new javax.swing.JMenuItem();
        InternaFrame_VisualizarMvtoCarbon = new javax.swing.JInternalFrame();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        mvtEquipo_estado = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jSeparator26 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        MvtCarbon_observacion = new javax.swing.JTextArea();
        jLabel77 = new javax.swing.JLabel();
        mvtEquipo_UsuarioRegistro_Correo = new javax.swing.JLabel();
        mvtEquipo_laborRealizada = new javax.swing.JLabel();
        mvtEquipo_FechaInicioActividad = new javax.swing.JLabel();
        mvtEquipo_FechaFinalActividad = new javax.swing.JLabel();
        mvtEquipo_Minutos = new javax.swing.JLabel();
        mvtEquipo_CantidadHoras = new javax.swing.JLabel();
        mvtEquipo_ActividadFinalizada = new javax.swing.JLabel();
        mvtEquipo_MotivoFinalización = new javax.swing.JLabel();
        MvtCarbon_FechaRegistro = new javax.swing.JLabel();
        mvtEquipo_UsuarioRegistro_Código = new javax.swing.JLabel();
        mvtEquipo_UsuarioRegistro_Nombre = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
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
        mvtEquipo_Equipo_descripción = new javax.swing.JLabel();
        mvtEquipo_Equipo_proveedor = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
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
        MvtCarbon_UsuarioRegistraCodigo = new javax.swing.JLabel();
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
        MvtCarbon_auxiliarCentroCosto = new javax.swing.JLabel();
        MvtCarbon_subCentroOperacion = new javax.swing.JLabel();
        MvtCarbon_centroOperacion = new javax.swing.JLabel();
        MvtCarbon_codigo = new javax.swing.JLabel();
        MvtCarbon_usuarioRegistraNombre = new javax.swing.JLabel();
        titulo37 = new javax.swing.JLabel();
        estadoAutorizacion_MvtoCarbon = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        observacion_autorizacionMvtoCarbon = new javax.swing.JTextArea();
        titulo40 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        InternaFrame_VisualizarMvtoEquipo = new javax.swing.JInternalFrame();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        mvtoEquipo_estado = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
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
        jSeparator11 = new javax.swing.JSeparator();
        jLabel36 = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        mvtoEquipo_UsuarioRegistro_Correo = new javax.swing.JLabel();
        mvtoEquipo_fechaMvto = new javax.swing.JLabel();
        mvtoEquipo_CentroOperacion = new javax.swing.JLabel();
        mvtoEquipo_Subcentro = new javax.swing.JLabel();
        mvtoEquipo_AuxilarCentroOperacion = new javax.swing.JLabel();
        mvtoEquipo_Producto = new javax.swing.JLabel();
        mvtoEquipo_laborRealizada = new javax.swing.JLabel();
        mvtoEquipo_FechaInicioActividad = new javax.swing.JLabel();
        mvtoEquipo_FechaFinalActividad = new javax.swing.JLabel();
        mvtoEquipo_Minutos = new javax.swing.JLabel();
        mvtoEquipo_CantidadHoras = new javax.swing.JLabel();
        mvtoEquipo_ActividadFinalizada = new javax.swing.JLabel();
        mvtoEquipo_MotivoFinalización = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        mvtoEquipo_codigo = new javax.swing.JLabel();
        mvtoEquipo_Cliente_Código = new javax.swing.JLabel();
        mvtoEquipo_Cliente_Nombre = new javax.swing.JLabel();
        mvtoEquipo_UsuarioRegistro_Código = new javax.swing.JLabel();
        mvtoEquipo_UsuarioRegistro_Nombre = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        mvtoEquipo_Equipo_pertenencia = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        mvtoEquipo_Equipo_codigo = new javax.swing.JLabel();
        mvtoEquipo_Equipo_tipo = new javax.swing.JLabel();
        mvtoEquipo_Equipo_producto = new javax.swing.JLabel();
        mvtoEquipo_Equipo_marca = new javax.swing.JLabel();
        mvtoEquipo_Equipo_modelo = new javax.swing.JLabel();
        mvtoEquipo_Equipo_serial = new javax.swing.JLabel();
        mvtoEquipo_Equipo_descripción = new javax.swing.JLabel();
        mvtoEquipo_Equipo_proveedor = new javax.swing.JLabel();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator20 = new javax.swing.JSeparator();
        titulo38 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        estadoAutorizacion_MvtoEquipo = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        observacion_autorizacionMvtoEquipo = new javax.swing.JTextArea();
        titulo39 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
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

        Visualizar.setText("Visualizar");
        Visualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VisualizarActionPerformed(evt);
            }
        });
        Seleccionar.add(Visualizar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InternaFrame_VisualizarMvtoCarbon.setBackground(new java.awt.Color(255, 255, 255));
        InternaFrame_VisualizarMvtoCarbon.setClosable(true);
        InternaFrame_VisualizarMvtoCarbon.setVisible(false);
        InternaFrame_VisualizarMvtoCarbon.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 910, 1040, 10));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(0, 153, 153));
        jLabel54.setText("MOVIMIENTO DE CARBÓN");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, -1, 20));

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(51, 51, 51));
        jLabel55.setText("C.O:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 80, 20));

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(51, 51, 51));
        jLabel56.setText("SubCentro Costo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 140, 20));

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(51, 51, 51));
        jLabel57.setText("Fecha Salida Vehículo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 190, 20));

        mvtEquipo_estado.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtEquipo_estado.setForeground(new java.awt.Color(102, 102, 102));
        mvtEquipo_estado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 410, 400, 20));

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(51, 51, 51));
        jLabel61.setText("Movito Finalización:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 390, 130, 20));

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(51, 51, 51));
        jLabel63.setText("Descripción:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 270, 130, 20));

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(51, 51, 51));
        jLabel65.setText("Fecha Inicio:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 290, 130, 20));

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(51, 51, 51));
        jLabel66.setText("Fecha Finalización:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 310, 130, 20));

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(51, 51, 51));
        jLabel67.setText("Cantidad Minutos:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 330, 130, 20));

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(51, 51, 51));
        jLabel70.setText("Nombre:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 470, 60, 20));

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(51, 51, 51));
        jLabel71.setText("Correo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 490, 60, 20));

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(51, 51, 51));
        jLabel72.setText("Cantidad Horas:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 350, 130, 20));

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(51, 51, 51));
        jLabel73.setText("Conectado Ccarga:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 130, 20));

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(51, 51, 51));
        jLabel74.setText("Estado:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 410, 130, 20));

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(51, 51, 51));
        jLabel75.setText("Actividad Finalizada:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 370, 130, 20));

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(51, 51, 51));
        jLabel76.setText("Pertenencia:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 220, 100, 20));
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jSeparator26, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 330, -1, -1));

        MvtCarbon_observacion.setEditable(false);
        MvtCarbon_observacion.setColumns(20);
        MvtCarbon_observacion.setRows(5);
        jScrollPane3.setViewportView(MvtCarbon_observacion);

        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 480, 280, 110));

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(51, 51, 51));
        jLabel77.setText("Código Mvto:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 100, 20));

        mvtEquipo_UsuarioRegistro_Correo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtEquipo_UsuarioRegistro_Correo.setForeground(new java.awt.Color(102, 102, 102));
        mvtEquipo_UsuarioRegistro_Correo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_UsuarioRegistro_Correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 500, 400, 20));

        mvtEquipo_laborRealizada.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtEquipo_laborRealizada.setForeground(new java.awt.Color(102, 102, 102));
        mvtEquipo_laborRealizada.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_laborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 270, 400, 20));

        mvtEquipo_FechaInicioActividad.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtEquipo_FechaInicioActividad.setForeground(new java.awt.Color(102, 102, 102));
        mvtEquipo_FechaInicioActividad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_FechaInicioActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 290, 400, 20));

        mvtEquipo_FechaFinalActividad.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtEquipo_FechaFinalActividad.setForeground(new java.awt.Color(102, 102, 102));
        mvtEquipo_FechaFinalActividad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_FechaFinalActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 310, 400, 20));

        mvtEquipo_Minutos.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtEquipo_Minutos.setForeground(new java.awt.Color(102, 102, 102));
        mvtEquipo_Minutos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_Minutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 330, 400, 20));

        mvtEquipo_CantidadHoras.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtEquipo_CantidadHoras.setForeground(new java.awt.Color(102, 102, 102));
        mvtEquipo_CantidadHoras.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_CantidadHoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 350, 400, 20));

        mvtEquipo_ActividadFinalizada.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtEquipo_ActividadFinalizada.setForeground(new java.awt.Color(102, 102, 102));
        mvtEquipo_ActividadFinalizada.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_ActividadFinalizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 370, 400, 20));

        mvtEquipo_MotivoFinalización.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtEquipo_MotivoFinalización.setForeground(new java.awt.Color(102, 102, 102));
        mvtEquipo_MotivoFinalización.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_MotivoFinalización, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 390, 400, 20));

        MvtCarbon_FechaRegistro.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtCarbon_FechaRegistro.setForeground(new java.awt.Color(102, 102, 102));
        MvtCarbon_FechaRegistro.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_FechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 370, 20));

        mvtEquipo_UsuarioRegistro_Código.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtEquipo_UsuarioRegistro_Código.setForeground(new java.awt.Color(102, 102, 102));
        mvtEquipo_UsuarioRegistro_Código.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_UsuarioRegistro_Código, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 460, 400, 20));

        mvtEquipo_UsuarioRegistro_Nombre.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtEquipo_UsuarioRegistro_Nombre.setForeground(new java.awt.Color(102, 102, 102));
        mvtEquipo_UsuarioRegistro_Nombre.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_UsuarioRegistro_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 480, 400, 20));

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(51, 51, 51));
        jLabel79.setText("Código:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 450, 60, 20));

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(51, 51, 51));
        jLabel80.setText("Código:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 100, 20));

        MvtCarbon_fechaEntradaVehiculo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtCarbon_fechaEntradaVehiculo.setForeground(new java.awt.Color(102, 102, 102));
        MvtCarbon_fechaEntradaVehiculo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_fechaEntradaVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 370, 20));

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(51, 51, 51));
        jLabel81.setText("Tipo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 80, 100, 20));

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(51, 51, 51));
        jLabel82.setText("Producto:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 100, 20));

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(51, 51, 51));
        jLabel83.setText("Marca:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, 100, 20));

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(51, 51, 51));
        jLabel84.setText("Modelo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, 100, 20));

        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(51, 51, 51));
        jLabel85.setText("Serial:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 160, 100, 20));

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(51, 51, 51));
        jLabel86.setText("Descripción:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 180, 100, 20));

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(51, 51, 51));
        jLabel87.setText("Proveedor:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 200, 100, 20));

        MvtEquipo_codigo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtEquipo_codigo.setForeground(new java.awt.Color(102, 102, 102));
        MvtEquipo_codigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtEquipo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 60, 400, 20));

        MvtEquipo_tipo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtEquipo_tipo.setForeground(new java.awt.Color(102, 102, 102));
        MvtEquipo_tipo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtEquipo_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 80, 400, 20));

        mvtEquipo_Equipo_producto.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtEquipo_Equipo_producto.setForeground(new java.awt.Color(102, 102, 102));
        mvtEquipo_Equipo_producto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_Equipo_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 100, 400, 20));

        mvtEquipo_Equipo_marca.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtEquipo_Equipo_marca.setForeground(new java.awt.Color(102, 102, 102));
        mvtEquipo_Equipo_marca.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_Equipo_marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 120, 400, 20));

        mvtEquipo_Equipo_modelo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtEquipo_Equipo_modelo.setForeground(new java.awt.Color(102, 102, 102));
        mvtEquipo_Equipo_modelo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_Equipo_modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 140, 400, 20));

        mvtEquipo_Equipo_serial.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtEquipo_Equipo_serial.setForeground(new java.awt.Color(102, 102, 102));
        mvtEquipo_Equipo_serial.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_Equipo_serial, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 160, 400, 20));

        mvtEquipo_Equipo_descripción.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtEquipo_Equipo_descripción.setForeground(new java.awt.Color(102, 102, 102));
        mvtEquipo_Equipo_descripción.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_Equipo_descripción, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 180, 400, 20));

        mvtEquipo_Equipo_proveedor.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtEquipo_Equipo_proveedor.setForeground(new java.awt.Color(102, 102, 102));
        mvtEquipo_Equipo_proveedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_Equipo_proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 200, 400, 20));

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(51, 51, 51));
        jLabel88.setText("Auxiliar Centro Costo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 140, 20));

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(51, 51, 51));
        jLabel89.setText("Articulo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 140, 20));

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(51, 51, 51));
        jLabel90.setText("Cliente:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 140, 20));

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(51, 51, 51));
        jLabel91.setText("Placa:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 140, 20));

        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(51, 51, 51));
        jLabel93.setText("Fecha Inicio Descargue:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 190, 20));

        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(51, 51, 51));
        jLabel94.setText("Nom. Usuario Quien Registra Vehículo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 250, 20));

        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(51, 51, 51));
        jLabel95.setText("Fecha Entrada Vehículo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 150, 20));

        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(51, 51, 51));
        jLabel96.setText("Fecha Finalización Descargue:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 190, 20));

        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(51, 51, 51));
        jLabel97.setText("Cod. Usuario Quien Registra Vehículo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 240, 20));

        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(51, 51, 51));
        jLabel98.setText("Transportadora:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 140, 20));

        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(51, 51, 51));
        jLabel99.setText("Deposito:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 140, 20));

        jLabel100.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(51, 51, 51));
        jLabel100.setText("Peso Neto:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 140, 20));

        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(51, 51, 51));
        jLabel101.setText("Consecutivo:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 140, 20));

        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(51, 51, 51));
        jLabel102.setText("Peso Vacio:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 140, 20));

        jLabel103.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(51, 51, 51));
        jLabel103.setText("Peso Lleno:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 140, 20));

        jLabel104.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(51, 51, 51));
        jLabel104.setText("Observación:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 130, 20));

        jLabel106.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(51, 51, 51));
        jLabel106.setText("Fecha Registro");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 140, 20));

        mvtEquipo_Equipo_pertenencia.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtEquipo_Equipo_pertenencia.setForeground(new java.awt.Color(102, 102, 102));
        mvtEquipo_Equipo_pertenencia.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(mvtEquipo_Equipo_pertenencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 220, 400, 20));

        MvtCarbon_conexionCcarga.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtCarbon_conexionCcarga.setForeground(new java.awt.Color(102, 102, 102));
        MvtCarbon_conexionCcarga.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_conexionCcarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 460, 280, 20));

        MvtCarbon_UsuarioRegistraCodigo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtCarbon_UsuarioRegistraCodigo.setForeground(new java.awt.Color(102, 102, 102));
        MvtCarbon_UsuarioRegistraCodigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_UsuarioRegistraCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, 280, 20));

        MvtCarbon_fechaFinalDescargue.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtCarbon_fechaFinalDescargue.setForeground(new java.awt.Color(102, 102, 102));
        MvtCarbon_fechaFinalDescargue.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_fechaFinalDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, 280, 20));

        MvtCarbon_fechaInicioDescargue.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtCarbon_fechaInicioDescargue.setForeground(new java.awt.Color(102, 102, 102));
        MvtCarbon_fechaInicioDescargue.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_fechaInicioDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 380, 280, 20));

        MvtCarbon_fechaSalidaVehiculo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtCarbon_fechaSalidaVehiculo.setForeground(new java.awt.Color(102, 102, 102));
        MvtCarbon_fechaSalidaVehiculo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_fechaSalidaVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 360, 280, 20));

        MvtCarbon_placa.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtCarbon_placa.setForeground(new java.awt.Color(102, 102, 102));
        MvtCarbon_placa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_placa, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 370, 20));

        MvtCarbon_pesoNeto.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtCarbon_pesoNeto.setForeground(new java.awt.Color(102, 102, 102));
        MvtCarbon_pesoNeto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_pesoNeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 370, 20));

        MvtCarbon_pesoLleno.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtCarbon_pesoLleno.setForeground(new java.awt.Color(102, 102, 102));
        MvtCarbon_pesoLleno.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_pesoLleno, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 370, 20));

        MvtCarbon_pesoVacio.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtCarbon_pesoVacio.setForeground(new java.awt.Color(102, 102, 102));
        MvtCarbon_pesoVacio.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_pesoVacio, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 370, 20));

        MvtCarbon_consecutivo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtCarbon_consecutivo.setForeground(new java.awt.Color(102, 102, 102));
        MvtCarbon_consecutivo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_consecutivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 370, 20));

        MvtCarbon_deposito.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtCarbon_deposito.setForeground(new java.awt.Color(102, 102, 102));
        MvtCarbon_deposito.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_deposito, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 370, 20));

        MvtCarbon_transportadora.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtCarbon_transportadora.setForeground(new java.awt.Color(102, 102, 102));
        MvtCarbon_transportadora.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_transportadora, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 370, 20));

        MvtCarbon_cliente.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtCarbon_cliente.setForeground(new java.awt.Color(102, 102, 102));
        MvtCarbon_cliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 380, 20));

        MvtCarbon_articulo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtCarbon_articulo.setForeground(new java.awt.Color(102, 102, 102));
        MvtCarbon_articulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_articulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 370, 20));

        MvtCarbon_auxiliarCentroCosto.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtCarbon_auxiliarCentroCosto.setForeground(new java.awt.Color(102, 102, 102));
        MvtCarbon_auxiliarCentroCosto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_auxiliarCentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 370, 20));

        MvtCarbon_subCentroOperacion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtCarbon_subCentroOperacion.setForeground(new java.awt.Color(102, 102, 102));
        MvtCarbon_subCentroOperacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_subCentroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 370, 20));

        MvtCarbon_centroOperacion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtCarbon_centroOperacion.setForeground(new java.awt.Color(102, 102, 102));
        MvtCarbon_centroOperacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 370, 20));

        MvtCarbon_codigo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtCarbon_codigo.setForeground(new java.awt.Color(102, 102, 102));
        MvtCarbon_codigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 370, 20));

        MvtCarbon_usuarioRegistraNombre.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MvtCarbon_usuarioRegistraNombre.setForeground(new java.awt.Color(102, 102, 102));
        MvtCarbon_usuarioRegistraNombre.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(MvtCarbon_usuarioRegistraNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 440, 280, 20));

        titulo37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo37.setForeground(new java.awt.Color(51, 51, 51));
        titulo37.setText("CONFIRMACIÓN AUTORIZACIÓN:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(titulo37, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 600, 210, 30));

        estadoAutorizacion_MvtoCarbon.setToolTipText("");
        estadoAutorizacion_MvtoCarbon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadoAutorizacion_MvtoCarbonActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(estadoAutorizacion_MvtoCarbon, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 600, 270, 30));

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save.png"))); // NOI18N
        jButton5.setText("REGISTRAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 640, 170, 40));

        observacion_autorizacionMvtoCarbon.setColumns(20);
        observacion_autorizacionMvtoCarbon.setRows(5);
        jScrollPane5.setViewportView(observacion_autorizacionMvtoCarbon);

        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 640, 590, 40));

        titulo40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo40.setForeground(new java.awt.Color(51, 51, 51));
        titulo40.setText("OBSERVACIÓN:");
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(titulo40, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 650, 100, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("INFORMACIÓN DEL USUARIO QUIEN REGISTRÓ LA ACTIVIDAD");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 430, 550, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("INFORMACIÓN DE VEHÍCULO");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 540, 20));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("INFORMACIÓN DE EQUIPO ASOCIADO EN LA OPERACIÓN");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 570, 20));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("INFORMACIÓN DE LA ACTIVIDAD REALIZADA");
        jLabel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 250, 570, 20));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 1110, 90));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 570, 570));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoCarbon.getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 540, 570));

        add(InternaFrame_VisualizarMvtoCarbon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 730));

        InternaFrame_VisualizarMvtoEquipo.setBackground(new java.awt.Color(255, 255, 255));
        InternaFrame_VisualizarMvtoEquipo.setClosable(true);
        InternaFrame_VisualizarMvtoEquipo.setVisible(false);
        InternaFrame_VisualizarMvtoEquipo.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 153, 153));
        jLabel17.setText("MOVIMIENTO DE EQUIPO");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("C.O:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 80, 20));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("SubCentro Costo:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 140, 20));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Auxiliar Centro Costo:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 140, 20));

        mvtoEquipo_estado.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_estado.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_estado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 230, 20));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Código:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 390, 70, 20));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Nombre:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 410, 70, 20));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setText("Movito Finalización:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 130, 20));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setText("Producto:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 130, 20));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("Descripción:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 130, 20));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("Fecha Mvto:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 130, 20));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setText("Fecha Inicio:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 130, 20));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setText("Fecha Finalización:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 130, 20));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("Cantidad Minutos:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 130, 20));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setText("Nombre:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 310, 60, 20));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(51, 51, 51));
        jLabel39.setText("Correo:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 330, 60, 20));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(51, 51, 51));
        jLabel41.setText("Cantidad Horas:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 130, 20));

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("Observación:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 130, 20));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(51, 51, 51));
        jLabel43.setText("Estado:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 130, 20));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(51, 51, 51));
        jLabel44.setText("Actividad Finalizada:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 130, 20));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(51, 51, 51));
        jLabel40.setText("Pertenencia:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 230, 90, 20));
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 330, -1, -1));

        mvtoEquipo_Observación.setEditable(false);
        mvtoEquipo_Observación.setColumns(20);
        mvtoEquipo_Observación.setRows(5);
        jScrollPane2.setViewportView(mvtoEquipo_Observación);

        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 400, 390, 110));
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 400, 10));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Código:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 100, 20));

        jSeparator14.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 10, 300));

        mvtoEquipo_UsuarioRegistro_Correo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_UsuarioRegistro_Correo.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_UsuarioRegistro_Correo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_UsuarioRegistro_Correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 330, 310, 20));

        mvtoEquipo_fechaMvto.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_fechaMvto.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_fechaMvto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_fechaMvto, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 230, 20));

        mvtoEquipo_CentroOperacion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_CentroOperacion.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_CentroOperacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_CentroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 230, 20));

        mvtoEquipo_Subcentro.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Subcentro.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_Subcentro.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Subcentro, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 230, 20));

        mvtoEquipo_AuxilarCentroOperacion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_AuxilarCentroOperacion.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_AuxilarCentroOperacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_AuxilarCentroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 230, 20));

        mvtoEquipo_Producto.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Producto.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_Producto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 230, 20));

        mvtoEquipo_laborRealizada.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_laborRealizada.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_laborRealizada.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_laborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 230, 20));

        mvtoEquipo_FechaInicioActividad.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_FechaInicioActividad.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_FechaInicioActividad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_FechaInicioActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 230, 20));

        mvtoEquipo_FechaFinalActividad.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_FechaFinalActividad.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_FechaFinalActividad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_FechaFinalActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 230, 20));

        mvtoEquipo_Minutos.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Minutos.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_Minutos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Minutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 230, 20));

        mvtoEquipo_CantidadHoras.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_CantidadHoras.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_CantidadHoras.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_CantidadHoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 230, 20));

        mvtoEquipo_ActividadFinalizada.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_ActividadFinalizada.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_ActividadFinalizada.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_ActividadFinalizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 230, 20));

        mvtoEquipo_MotivoFinalización.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_MotivoFinalización.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_MotivoFinalización.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_MotivoFinalización, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 230, 20));

        jSeparator16.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 10, 140));

        mvtoEquipo_codigo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_codigo.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_codigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 230, 20));

        mvtoEquipo_Cliente_Código.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Cliente_Código.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_Cliente_Código.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Cliente_Código, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 390, 310, 20));

        mvtoEquipo_Cliente_Nombre.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Cliente_Nombre.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_Cliente_Nombre.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Cliente_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 410, 310, 20));

        mvtoEquipo_UsuarioRegistro_Código.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_UsuarioRegistro_Código.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_UsuarioRegistro_Código.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_UsuarioRegistro_Código, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 290, 310, 20));

        mvtoEquipo_UsuarioRegistro_Nombre.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_UsuarioRegistro_Nombre.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_UsuarioRegistro_Nombre.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_UsuarioRegistro_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 310, 310, 20));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(51, 51, 51));
        jLabel45.setText("Código:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 290, 60, 20));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(51, 51, 51));
        jLabel46.setText("Código:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 90, 20));

        mvtoEquipo_Equipo_pertenencia.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Equipo_pertenencia.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_Equipo_pertenencia.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Equipo_pertenencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, 310, 20));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(51, 51, 51));
        jLabel47.setText("Tipo:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 90, 20));

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(51, 51, 51));
        jLabel48.setText("Producto:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 110, 90, 20));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(51, 51, 51));
        jLabel49.setText("Marca:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, 90, 20));

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(51, 51, 51));
        jLabel50.setText("Modelo:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 150, 90, 20));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(51, 51, 51));
        jLabel51.setText("Serial:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 170, 90, 20));

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(51, 51, 51));
        jLabel52.setText("Descripción:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 190, 90, 20));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(51, 51, 51));
        jLabel53.setText("Proveedor:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 210, 90, 20));

        mvtoEquipo_Equipo_codigo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Equipo_codigo.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_Equipo_codigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Equipo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, 310, 20));

        mvtoEquipo_Equipo_tipo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Equipo_tipo.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_Equipo_tipo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Equipo_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 90, 310, 20));

        mvtoEquipo_Equipo_producto.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Equipo_producto.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_Equipo_producto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Equipo_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 110, 310, 20));

        mvtoEquipo_Equipo_marca.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Equipo_marca.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_Equipo_marca.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Equipo_marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, 310, 20));

        mvtoEquipo_Equipo_modelo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Equipo_modelo.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_Equipo_modelo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Equipo_modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 150, 310, 20));

        mvtoEquipo_Equipo_serial.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Equipo_serial.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_Equipo_serial.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Equipo_serial, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 170, 310, 20));

        mvtoEquipo_Equipo_descripción.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Equipo_descripción.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_Equipo_descripción.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Equipo_descripción, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 190, 310, 20));

        mvtoEquipo_Equipo_proveedor.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_Equipo_proveedor.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_Equipo_proveedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(mvtoEquipo_Equipo_proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 210, 310, 20));

        jSeparator19.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 380, 10, 140));

        jSeparator20.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 280, 10, 80));

        titulo38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo38.setForeground(new java.awt.Color(51, 51, 51));
        titulo38.setText("OBSERVACIÓN:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(titulo38, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 100, 30));

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save.png"))); // NOI18N
        jButton6.setText("REGISTRAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 590, 160, 40));

        estadoAutorizacion_MvtoEquipo.setToolTipText("");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(estadoAutorizacion_MvtoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 530, 300, 30));

        observacion_autorizacionMvtoEquipo.setColumns(20);
        observacion_autorizacionMvtoEquipo.setRows(5);
        jScrollPane4.setViewportView(observacion_autorizacionMvtoEquipo);

        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 570, 590, 60));

        titulo39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo39.setForeground(new java.awt.Color(51, 51, 51));
        titulo39.setText("CONFIRMACIÓN AUTORIZACIÓN:");
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(titulo39, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 210, 30));

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel59.setText("INFORMACIÓN DEL EQUIPO");
        jLabel59.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 40, 570, 20));

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel60.setText("INFORMAICÓN DEL MOVIMIENTO DEL EQUIPO");
        jLabel60.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 570, 20));

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel62.setText("INFORMACIÓN DE LA ACTIVIDAD REALIZADA");
        jLabel62.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 570, 20));

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel64.setText("INFORMACIÓN DEL USUARIO QUIEN REGISTRÓ LA ACTIVIDAD");
        jLabel64.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 260, 570, 20));

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel78.setText("INFORMACIÓN DEL CLIENTE");
        jLabel78.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 360, 570, 20));

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel58.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 1140, 140));

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel68.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 570, 480));

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel69.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrame_VisualizarMvtoEquipo.getContentPane().add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 40, 570, 480));

        add(InternaFrame_VisualizarMvtoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 720));

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
        titulo.setText("AUTORIZACIÓN DE RECOBROS");
        add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 470, 20));

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
        resizeColumnWidth(tabla);
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
                equipo = listado.get(fila1);
                if(equipo.getDesdeCarbon().equalsIgnoreCase("SI")){
                    InternaFrame_VisualizarMvtoCarbon.show(true);
                    try {
                        mvto_listEquipo = new ControlDB_MvtoCarbon(tipoConexion).buscarMvtoCarbonParticularPorCódigoMvtoEquipo(equipo.getCodigo());
                    } catch (SQLException ex) {
                        Logger.getLogger(MvtoEquipo_AutorizarRecobro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    MvtCarbon_FechaRegistro.setText(mvto_listEquipo.getMvtoCarbon().getFechaRegistro());
                    MvtCarbon_placa.setText(mvto_listEquipo.getMvtoCarbon().getPlaca());
                    MvtCarbon_codigo.setText(mvto_listEquipo.getMvtoCarbon().getCodigo());
                    MvtCarbon_centroOperacion.setText(mvto_listEquipo.getMvtoCarbon().getCentroOperacion().getDescripcion());
                    MvtCarbon_subCentroOperacion.setText(mvto_listEquipo.getMvtoCarbon().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion());
                    MvtCarbon_auxiliarCentroCosto.setText(mvto_listEquipo.getMvtoCarbon().getCentroCostoAuxiliar().getDescripcion());
                    MvtCarbon_articulo.setText(mvto_listEquipo.getMvtoCarbon().getArticulo().getDescripcion());
                    MvtCarbon_cliente.setText(mvto_listEquipo.getMvtoCarbon().getCliente().getDescripcion());
                    MvtCarbon_transportadora.setText(mvto_listEquipo.getMvtoCarbon().getTransportadora().getDescripcion());
                    MvtCarbon_deposito.setText(mvto_listEquipo.getMvtoCarbon().getDeposito());
                    MvtCarbon_consecutivo.setText(mvto_listEquipo.getMvtoCarbon().getConsecutivo());
                    MvtCarbon_pesoVacio.setText(mvto_listEquipo.getMvtoCarbon().getPesoVacio());
                    MvtCarbon_pesoLleno.setText(mvto_listEquipo.getMvtoCarbon().getPesoLleno());
                    MvtCarbon_pesoNeto.setText(mvto_listEquipo.getMvtoCarbon().getPesoNeto());
                    MvtCarbon_fechaEntradaVehiculo.setText(mvto_listEquipo.getMvtoCarbon().getFechaEntradaVehiculo());
                    MvtCarbon_fechaSalidaVehiculo.setText(mvto_listEquipo.getMvtoCarbon().getFecha_SalidaVehiculo());
                    MvtCarbon_fechaInicioDescargue.setText(mvto_listEquipo.getMvtoCarbon().getFechaInicioDescargue());
                    MvtCarbon_fechaFinalDescargue.setText(mvto_listEquipo.getMvtoCarbon().getFechaFinDescargue());
                    MvtCarbon_UsuarioRegistraCodigo.setText(mvto_listEquipo.getMvtoCarbon().getUsuarioRegistroMovil().getCodigo());
                    MvtCarbon_usuarioRegistraNombre.setText(mvto_listEquipo.getMvtoCarbon().getUsuarioRegistroMovil().getNombres()+" "+mvto_listEquipo.getMvtoCarbon().getUsuarioRegistroMovil().getApellidos());
                    MvtCarbon_conexionCcarga.setText(mvto_listEquipo.getMvtoCarbon().getConexionPesoCcarga());
                    MvtCarbon_observacion.setText(mvto_listEquipo.getMvtoCarbon().getObservacion());
                    
                    MvtEquipo_codigo.setText(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getCodigo());
                    MvtEquipo_tipo.setText(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion());
                    mvtEquipo_Equipo_producto.setText(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getProducto());
                    mvtEquipo_Equipo_marca.setText(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getMarca());
                    mvtEquipo_Equipo_modelo.setText(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getModelo());
                    mvtEquipo_Equipo_serial.setText(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getSerial());
                    mvtEquipo_Equipo_descripción.setText(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getDescripcion());
                    mvtEquipo_Equipo_proveedor.setText(mvto_listEquipo.getMvtoEquipo().getProveedorEquipo().getDescripcion());
                    mvtEquipo_Equipo_pertenencia.setText(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getPertenenciaEquipo().getDescripcion());
                    
                    mvtEquipo_laborRealizada.setText(mvto_listEquipo.getMvtoEquipo().getLaborRealizada().getDescripcion());
                    mvtEquipo_FechaInicioActividad.setText(mvto_listEquipo.getMvtoEquipo().getFechaHoraInicio());
                    mvtEquipo_FechaFinalActividad.setText(mvto_listEquipo.getMvtoEquipo().getFechaHoraFin());
                    if(mvto_listEquipo.getMvtoEquipo().getTotalMinutos()!=null){
                        try{
                            mvtEquipo_Minutos.setText(mvto_listEquipo.getMvtoEquipo().getTotalMinutos());
                            double des=Double.parseDouble(mvto_listEquipo.getMvtoEquipo().getTotalMinutos());
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
                    if(mvto_listEquipo.getMvtoEquipo().getMotivoParadaEstado().equals("1")){
                        mvtEquipo_ActividadFinalizada.setText("SI");
                    }else{
                        mvtEquipo_ActividadFinalizada.setText("NO");
                    }
                    mvtEquipo_MotivoFinalización.setText(mvto_listEquipo.getMvtoEquipo().getMotivoParada().getDescripcion());
                    mvtEquipo_estado.setText(mvto_listEquipo.getMvtoEquipo().getEstado());  
                    mvtEquipo_UsuarioRegistro_Código.setText(mvto_listEquipo.getMvtoEquipo().getUsuarioQuieRegistra().getCodigo());
                    mvtEquipo_UsuarioRegistro_Nombre.setText(mvto_listEquipo.getMvtoEquipo().getUsuarioQuieRegistra().getNombres()+" "+mvto_listEquipo.getMvtoEquipo().getUsuarioQuieRegistra().getApellidos());
                    mvtEquipo_UsuarioRegistro_Correo.setText(mvto_listEquipo.getMvtoEquipo().getUsuarioQuieRegistra().getCorreo());
                    
                }else{//No está registrado en el modulo de carbón, solo es MvtoEquipo
                    InternaFrame_VisualizarMvtoEquipo.show(true);
                    mvtoEquipo_codigo.setText(equipo.getCodigo());
                    mvtoEquipo_fechaMvto.setText(equipo.getFechaRegistro());
                    mvtoEquipo_CentroOperacion.setText(equipo.getCentroOperacion().getDescripcion());
                    mvtoEquipo_Subcentro.setText(equipo.getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion());
                    mvtoEquipo_AuxilarCentroOperacion.setText(equipo.getCentroCostoAuxiliar().getDescripcion());
                    mvtoEquipo_Producto.setText(equipo.getArticulo().getDescripcion());
                    mvtoEquipo_laborRealizada.setText(equipo.getLaborRealizada().getDescripcion());
                    mvtoEquipo_FechaInicioActividad.setText(equipo.getFechaHoraInicio());
                    mvtoEquipo_FechaFinalActividad.setText(equipo.getFechaHoraFin());
                    if(equipo.getTotalMinutos()!=null){
                        try{
                            mvtoEquipo_Minutos.setText(equipo.getTotalMinutos());
                            double des=Double.parseDouble(equipo.getTotalMinutos());
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
                    if(equipo.getMotivoParadaEstado().equals("1")){
                        mvtoEquipo_ActividadFinalizada.setText("SI");
                    }else{
                        mvtoEquipo_ActividadFinalizada.setText("NO");
                    }
                    mvtoEquipo_MotivoFinalización.setText(equipo.getMotivoParada().getDescripcion());
                    mvtoEquipo_estado.setText(equipo.getEstado());
                    mvtoEquipo_Observación.setText(equipo.getObservacionMvtoEquipo());
                    mvtoEquipo_Cliente_Código.setText(equipo.getCliente().getCodigo());
                    mvtoEquipo_Cliente_Nombre.setText(equipo.getCliente().getDescripcion());
                    mvtoEquipo_UsuarioRegistro_Código.setText(equipo.getUsuarioQuieRegistra().getCodigo());
                    mvtoEquipo_UsuarioRegistro_Nombre.setText(equipo.getUsuarioQuieRegistra().getNombres()+" "+equipo.getUsuarioQuieRegistra().getApellidos());
                    mvtoEquipo_UsuarioRegistro_Correo.setText(equipo.getUsuarioQuieRegistra().getCorreo());
                    mvtoEquipo_Equipo_codigo.setText(equipo.getAsignacionEquipo().getEquipo().getCodigo());
                    mvtoEquipo_Equipo_tipo.setText(equipo.getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion());
                    mvtoEquipo_Equipo_producto.setText(equipo.getAsignacionEquipo().getEquipo().getProducto());
                    mvtoEquipo_Equipo_marca.setText(equipo.getAsignacionEquipo().getEquipo().getMarca());
                    mvtoEquipo_Equipo_modelo.setText(equipo.getAsignacionEquipo().getEquipo().getModelo());
                    mvtoEquipo_Equipo_serial.setText(equipo.getAsignacionEquipo().getEquipo().getSerial());
                    mvtoEquipo_Equipo_descripción.setText(equipo.getAsignacionEquipo().getEquipo().getDescripcion());
                    mvtoEquipo_Equipo_proveedor.setText(equipo.getProveedorEquipo().getDescripcion());
                    mvtoEquipo_Equipo_pertenencia.setText(equipo.getAsignacionEquipo().getPertenencia().getDescripcion());
                }
                
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo cargar los datos","Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_VisualizarActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         if(observacion_autorizacionMvtoCarbon.getText().equals("")){
            JOptionPane.showMessageDialog(null, "La observación de autorización no puede estar vacia", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
        }else{
            try {
                if(mvto_listEquipo != null){
                    mvto_listEquipo.getMvtoEquipo().setAutorizacionRecobro(listadoAutorizacionRecobro.get(estadoAutorizacion_MvtoCarbon.getSelectedIndex()));
                    mvto_listEquipo.getMvtoEquipo().setObservacionAutorizacion(observacion_autorizacionMvtoEquipo.getText());
                     
                    if( mvto_listEquipo.getMvtoEquipo().getAutorizacionRecobro().getCodigo().equals("1")){
                        Recobro recobro = new Recobro();
                        recobro.setCodigo("1");
                        mvto_listEquipo.getMvtoEquipo().setRecobro(recobro);
                    }else{
                        if( mvto_listEquipo.getMvtoEquipo().getAutorizacionRecobro().getCodigo().equals("0")){
                            Recobro recobro = new Recobro();
                            recobro.setCodigo("0");
                            mvto_listEquipo.getMvtoEquipo().setRecobro(recobro);
                        }
                    }
                    int retorno= new ControlDB_MvtoEquipo(tipoConexion).RegistrarAutorizacionRecobro( mvto_listEquipo.getMvtoEquipo(), user);
                    if(retorno==1){
                        JOptionPane.showMessageDialog(null, "Se registro la autorización", "Registro Exitoso",JOptionPane.INFORMATION_MESSAGE);
                        observacion_autorizacionMvtoCarbon.setText("");
                        generarListadoMvtoCarbon();
                        InternaFrame_VisualizarMvtoCarbon.show(false);    
                    }else{
                        JOptionPane.showMessageDialog(null, "No se puedo registrar la autorización, valide datos", "Error al registrar",JOptionPane.ERROR_MESSAGE);
                    }
                }
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MvtoEquipo_AutorizarRecobro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(MvtoEquipo_AutorizarRecobro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SocketException ex) {
                Logger.getLogger(MvtoEquipo_AutorizarRecobro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(observacion_autorizacionMvtoEquipo.getText().equals("")){
            JOptionPane.showMessageDialog(null, "La observación de autorización no puede estar vacia", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
        }else{
            try {
                equipo.setAutorizacionRecobro(listadoAutorizacionRecobro.get(estadoAutorizacion_MvtoEquipo.getSelectedIndex()));
                equipo.setObservacionAutorizacion(observacion_autorizacionMvtoEquipo.getText());
                if( equipo.getAutorizacionRecobro().getCodigo().equals("1")){
                    Recobro recobro = new Recobro();
                    recobro.setCodigo("1");
                    equipo.setRecobro(recobro);
                }else{
                    if( equipo.getAutorizacionRecobro().getCodigo().equals("0")){
                        Recobro recobro = new Recobro();
                        recobro.setCodigo("0");
                        equipo.setRecobro(recobro);
                    }
                }
                int retorno= new ControlDB_MvtoEquipo(tipoConexion).RegistrarAutorizacionRecobro(equipo, user);
                if(retorno==1){
                    JOptionPane.showMessageDialog(null, "Se registro la autorización", "Registro Exitoso",JOptionPane.INFORMATION_MESSAGE);
                    observacion_autorizacionMvtoEquipo.setText("");
                    generarListadoMvtoCarbon();
                    InternaFrame_VisualizarMvtoEquipo.show(false);    
                }else{
                    JOptionPane.showMessageDialog(null, "No se puedo registrar la autorización, valide datos", "Error al registrar",JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MvtoEquipo_AutorizarRecobro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(MvtoEquipo_AutorizarRecobro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SocketException ex) {
                Logger.getLogger(MvtoEquipo_AutorizarRecobro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
    }//GEN-LAST:event_jButton6ActionPerformed

    private void estadoAutorizacion_MvtoCarbonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadoAutorizacion_MvtoCarbonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estadoAutorizacion_MvtoCarbonActionPerformed

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
                    equipo = listado.get(fila1);
                    if(equipo.getDesdeCarbon().equalsIgnoreCase("SI")){
                        InternaFrame_VisualizarMvtoCarbon.show(true);
                        try {
                            mvto_listEquipo = new ControlDB_MvtoCarbon(tipoConexion).buscarMvtoCarbonParticularPorCódigoMvtoEquipo(equipo.getCodigo());
                        } catch (SQLException ex) {
                            Logger.getLogger(MvtoEquipo_AutorizarRecobro.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        MvtCarbon_FechaRegistro.setText(mvto_listEquipo.getMvtoCarbon().getFechaRegistro());
                        MvtCarbon_placa.setText(mvto_listEquipo.getMvtoCarbon().getPlaca());
                        MvtCarbon_codigo.setText(mvto_listEquipo.getMvtoCarbon().getCodigo());
                        MvtCarbon_centroOperacion.setText(mvto_listEquipo.getMvtoCarbon().getCentroOperacion().getDescripcion());
                        MvtCarbon_subCentroOperacion.setText(mvto_listEquipo.getMvtoCarbon().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion());
                        MvtCarbon_auxiliarCentroCosto.setText(mvto_listEquipo.getMvtoCarbon().getCentroCostoAuxiliar().getDescripcion());
                        MvtCarbon_articulo.setText(mvto_listEquipo.getMvtoCarbon().getArticulo().getDescripcion());
                        MvtCarbon_cliente.setText(mvto_listEquipo.getMvtoCarbon().getCliente().getDescripcion());
                        MvtCarbon_transportadora.setText(mvto_listEquipo.getMvtoCarbon().getTransportadora().getDescripcion());
                        MvtCarbon_deposito.setText(mvto_listEquipo.getMvtoCarbon().getDeposito());
                        MvtCarbon_consecutivo.setText(mvto_listEquipo.getMvtoCarbon().getConsecutivo());
                        MvtCarbon_pesoVacio.setText(mvto_listEquipo.getMvtoCarbon().getPesoVacio());
                        MvtCarbon_pesoLleno.setText(mvto_listEquipo.getMvtoCarbon().getPesoLleno());
                        MvtCarbon_pesoNeto.setText(mvto_listEquipo.getMvtoCarbon().getPesoNeto());
                        MvtCarbon_fechaEntradaVehiculo.setText(mvto_listEquipo.getMvtoCarbon().getFechaEntradaVehiculo());
                        MvtCarbon_fechaSalidaVehiculo.setText(mvto_listEquipo.getMvtoCarbon().getFecha_SalidaVehiculo());
                        MvtCarbon_fechaInicioDescargue.setText(mvto_listEquipo.getMvtoCarbon().getFechaInicioDescargue());
                        MvtCarbon_fechaFinalDescargue.setText(mvto_listEquipo.getMvtoCarbon().getFechaFinDescargue());
                        MvtCarbon_UsuarioRegistraCodigo.setText(mvto_listEquipo.getMvtoCarbon().getUsuarioRegistroMovil().getCodigo());
                        MvtCarbon_usuarioRegistraNombre.setText(mvto_listEquipo.getMvtoCarbon().getUsuarioRegistroMovil().getNombres()+" "+mvto_listEquipo.getMvtoCarbon().getUsuarioRegistroMovil().getApellidos());
                        MvtCarbon_conexionCcarga.setText(mvto_listEquipo.getMvtoCarbon().getConexionPesoCcarga());
                        MvtCarbon_observacion.setText(mvto_listEquipo.getMvtoCarbon().getObservacion());

                        MvtEquipo_codigo.setText(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getCodigo());
                        MvtEquipo_tipo.setText(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion());
                        mvtEquipo_Equipo_producto.setText(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getProducto());
                        mvtEquipo_Equipo_marca.setText(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getMarca());
                        mvtEquipo_Equipo_modelo.setText(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getModelo());
                        mvtEquipo_Equipo_serial.setText(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getSerial());
                        mvtEquipo_Equipo_descripción.setText(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getDescripcion());
                        mvtEquipo_Equipo_proveedor.setText(mvto_listEquipo.getMvtoEquipo().getProveedorEquipo().getDescripcion());
                        mvtEquipo_Equipo_pertenencia.setText(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getPertenenciaEquipo().getDescripcion());

                        mvtEquipo_laborRealizada.setText(mvto_listEquipo.getMvtoEquipo().getLaborRealizada().getDescripcion());
                        mvtEquipo_FechaInicioActividad.setText(mvto_listEquipo.getMvtoEquipo().getFechaHoraInicio());
                        mvtEquipo_FechaFinalActividad.setText(mvto_listEquipo.getMvtoEquipo().getFechaHoraFin());
                        if(mvto_listEquipo.getMvtoEquipo().getTotalMinutos()!=null){
                            try{
                                mvtEquipo_Minutos.setText(mvto_listEquipo.getMvtoEquipo().getTotalMinutos());
                                double des=Double.parseDouble(mvto_listEquipo.getMvtoEquipo().getTotalMinutos());
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
                        if(mvto_listEquipo.getMvtoEquipo().getMotivoParadaEstado().equals("1")){
                            mvtEquipo_ActividadFinalizada.setText("SI");
                        }else{
                            mvtEquipo_ActividadFinalizada.setText("NO");
                        }
                        mvtEquipo_MotivoFinalización.setText(mvto_listEquipo.getMvtoEquipo().getMotivoParada().getDescripcion());
                        mvtEquipo_estado.setText(mvto_listEquipo.getMvtoEquipo().getEstado());  
                        mvtEquipo_UsuarioRegistro_Código.setText(mvto_listEquipo.getMvtoEquipo().getUsuarioQuieRegistra().getCodigo());
                        mvtEquipo_UsuarioRegistro_Nombre.setText(mvto_listEquipo.getMvtoEquipo().getUsuarioQuieRegistra().getNombres()+" "+mvto_listEquipo.getMvtoEquipo().getUsuarioQuieRegistra().getApellidos());
                        mvtEquipo_UsuarioRegistro_Correo.setText(mvto_listEquipo.getMvtoEquipo().getUsuarioQuieRegistra().getCorreo());

                    }else{//No está registrado en el modulo de carbón, solo es MvtoEquipo
                        InternaFrame_VisualizarMvtoEquipo.show(true);
                        mvtoEquipo_codigo.setText(equipo.getCodigo());
                        mvtoEquipo_fechaMvto.setText(equipo.getFechaRegistro());
                        mvtoEquipo_CentroOperacion.setText(equipo.getCentroOperacion().getDescripcion());
                        mvtoEquipo_Subcentro.setText(equipo.getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion());
                        mvtoEquipo_AuxilarCentroOperacion.setText(equipo.getCentroCostoAuxiliar().getDescripcion());
                        mvtoEquipo_Producto.setText(equipo.getArticulo().getDescripcion());
                        mvtoEquipo_laborRealizada.setText(equipo.getLaborRealizada().getDescripcion());
                        mvtoEquipo_FechaInicioActividad.setText(equipo.getFechaHoraInicio());
                        mvtoEquipo_FechaFinalActividad.setText(equipo.getFechaHoraFin());
                        if(equipo.getTotalMinutos()!=null){
                            try{
                                mvtoEquipo_Minutos.setText(equipo.getTotalMinutos());
                                double des=Double.parseDouble(equipo.getTotalMinutos());
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
                        if(equipo.getMotivoParadaEstado().equals("1")){
                            mvtoEquipo_ActividadFinalizada.setText("SI");
                        }else{
                            mvtoEquipo_ActividadFinalizada.setText("NO");
                        }
                        mvtoEquipo_MotivoFinalización.setText(equipo.getMotivoParada().getDescripcion());
                        mvtoEquipo_estado.setText(equipo.getEstado());
                        mvtoEquipo_Observación.setText(equipo.getObservacionMvtoEquipo());
                        mvtoEquipo_Cliente_Código.setText(equipo.getCliente().getCodigo());
                        mvtoEquipo_Cliente_Nombre.setText(equipo.getCliente().getDescripcion());
                        mvtoEquipo_UsuarioRegistro_Código.setText(equipo.getUsuarioQuieRegistra().getCodigo());
                        mvtoEquipo_UsuarioRegistro_Nombre.setText(equipo.getUsuarioQuieRegistra().getNombres()+" "+equipo.getUsuarioQuieRegistra().getApellidos());
                        mvtoEquipo_UsuarioRegistro_Correo.setText(equipo.getUsuarioQuieRegistra().getCorreo());
                        mvtoEquipo_Equipo_codigo.setText(equipo.getAsignacionEquipo().getEquipo().getCodigo());
                        mvtoEquipo_Equipo_tipo.setText(equipo.getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion());
                        mvtoEquipo_Equipo_producto.setText(equipo.getAsignacionEquipo().getEquipo().getProducto());
                        mvtoEquipo_Equipo_marca.setText(equipo.getAsignacionEquipo().getEquipo().getMarca());
                        mvtoEquipo_Equipo_modelo.setText(equipo.getAsignacionEquipo().getEquipo().getModelo());
                        mvtoEquipo_Equipo_serial.setText(equipo.getAsignacionEquipo().getEquipo().getSerial());
                        mvtoEquipo_Equipo_descripción.setText(equipo.getAsignacionEquipo().getEquipo().getDescripcion());
                        mvtoEquipo_Equipo_proveedor.setText(equipo.getProveedorEquipo().getDescripcion());
                        mvtoEquipo_Equipo_pertenencia.setText(equipo.getAsignacionEquipo().getPertenencia().getDescripcion());
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
        //selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
        //selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
        if(selecArchivo.showDialog(null, "Exportar") ==JFileChooser.APPROVE_OPTION){
            archivo=new File(selecArchivo.getSelectedFile()+".xlsx");
            //archivo=selecArchivo.getSelectedFile();
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame InternaFrame_VisualizarMvtoCarbon;
    private javax.swing.JInternalFrame InternaFrame_VisualizarMvtoEquipo;
    private javax.swing.JInternalFrame InternalFrameSelectorCampos;
    private javax.swing.JLabel MvtCarbon_FechaRegistro;
    private javax.swing.JLabel MvtCarbon_UsuarioRegistraCodigo;
    private javax.swing.JLabel MvtCarbon_articulo;
    private javax.swing.JLabel MvtCarbon_auxiliarCentroCosto;
    private javax.swing.JLabel MvtCarbon_centroOperacion;
    private javax.swing.JLabel MvtCarbon_cliente;
    private javax.swing.JLabel MvtCarbon_codigo;
    private javax.swing.JLabel MvtCarbon_conexionCcarga;
    private javax.swing.JLabel MvtCarbon_consecutivo;
    private javax.swing.JLabel MvtCarbon_deposito;
    private javax.swing.JLabel MvtCarbon_fechaEntradaVehiculo;
    private javax.swing.JLabel MvtCarbon_fechaFinalDescargue;
    private javax.swing.JLabel MvtCarbon_fechaInicioDescargue;
    private javax.swing.JLabel MvtCarbon_fechaSalidaVehiculo;
    private javax.swing.JTextArea MvtCarbon_observacion;
    private javax.swing.JLabel MvtCarbon_pesoLleno;
    private javax.swing.JLabel MvtCarbon_pesoNeto;
    private javax.swing.JLabel MvtCarbon_pesoVacio;
    private javax.swing.JLabel MvtCarbon_placa;
    private javax.swing.JLabel MvtCarbon_subCentroOperacion;
    private javax.swing.JLabel MvtCarbon_transportadora;
    private javax.swing.JLabel MvtCarbon_usuarioRegistraNombre;
    private javax.swing.JLabel MvtEquipo_codigo;
    private javax.swing.JLabel MvtEquipo_tipo;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JMenuItem Visualizar;
    private javax.swing.JLabel alerta_fechaFinal;
    private javax.swing.JLabel alerta_fechaInicio;
    private javax.swing.JButton consultar;
    private javax.swing.JComboBox<String> estadoAutorizacion_MvtoCarbon;
    private javax.swing.JComboBox<String> estadoAutorizacion_MvtoEquipo;
    private com.toedter.calendar.JDateChooser fechaFin;
    private com.toedter.calendar.JDateChooser fechaInicio;
    private javax.swing.JComboBox<String> horaFin;
    private javax.swing.JComboBox<String> horaInicio;
    private javax.swing.JLabel horarioTiempoIFinalMvtoCarbon_Procesar;
    private javax.swing.JLabel horarioTiempoInicioMvtoCarbon_Procesar;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
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
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JComboBox<String> minutoFin;
    private javax.swing.JComboBox<String> minutoInicio;
    private javax.swing.JLabel mvtEquipo_ActividadFinalizada;
    private javax.swing.JLabel mvtEquipo_CantidadHoras;
    private javax.swing.JLabel mvtEquipo_Equipo_descripción;
    private javax.swing.JLabel mvtEquipo_Equipo_marca;
    private javax.swing.JLabel mvtEquipo_Equipo_modelo;
    private javax.swing.JLabel mvtEquipo_Equipo_pertenencia;
    private javax.swing.JLabel mvtEquipo_Equipo_producto;
    private javax.swing.JLabel mvtEquipo_Equipo_proveedor;
    private javax.swing.JLabel mvtEquipo_Equipo_serial;
    private javax.swing.JLabel mvtEquipo_FechaFinalActividad;
    private javax.swing.JLabel mvtEquipo_FechaInicioActividad;
    private javax.swing.JLabel mvtEquipo_Minutos;
    private javax.swing.JLabel mvtEquipo_MotivoFinalización;
    private javax.swing.JLabel mvtEquipo_UsuarioRegistro_Correo;
    private javax.swing.JLabel mvtEquipo_UsuarioRegistro_Código;
    private javax.swing.JLabel mvtEquipo_UsuarioRegistro_Nombre;
    private javax.swing.JLabel mvtEquipo_estado;
    private javax.swing.JLabel mvtEquipo_laborRealizada;
    private javax.swing.JLabel mvtoEquipo_ActividadFinalizada;
    private javax.swing.JLabel mvtoEquipo_AuxilarCentroOperacion;
    private javax.swing.JLabel mvtoEquipo_CantidadHoras;
    private javax.swing.JLabel mvtoEquipo_CentroOperacion;
    private javax.swing.JLabel mvtoEquipo_Cliente_Código;
    private javax.swing.JLabel mvtoEquipo_Cliente_Nombre;
    private javax.swing.JLabel mvtoEquipo_Equipo_codigo;
    private javax.swing.JLabel mvtoEquipo_Equipo_descripción;
    private javax.swing.JLabel mvtoEquipo_Equipo_marca;
    private javax.swing.JLabel mvtoEquipo_Equipo_modelo;
    private javax.swing.JLabel mvtoEquipo_Equipo_pertenencia;
    private javax.swing.JLabel mvtoEquipo_Equipo_producto;
    private javax.swing.JLabel mvtoEquipo_Equipo_proveedor;
    private javax.swing.JLabel mvtoEquipo_Equipo_serial;
    private javax.swing.JLabel mvtoEquipo_Equipo_tipo;
    private javax.swing.JLabel mvtoEquipo_FechaFinalActividad;
    private javax.swing.JLabel mvtoEquipo_FechaInicioActividad;
    private javax.swing.JLabel mvtoEquipo_Minutos;
    private javax.swing.JLabel mvtoEquipo_MotivoFinalización;
    private javax.swing.JTextArea mvtoEquipo_Observación;
    private javax.swing.JLabel mvtoEquipo_Producto;
    private javax.swing.JLabel mvtoEquipo_Subcentro;
    private javax.swing.JLabel mvtoEquipo_UsuarioRegistro_Correo;
    private javax.swing.JLabel mvtoEquipo_UsuarioRegistro_Código;
    private javax.swing.JLabel mvtoEquipo_UsuarioRegistro_Nombre;
    private javax.swing.JLabel mvtoEquipo_codigo;
    private javax.swing.JLabel mvtoEquipo_estado;
    private javax.swing.JLabel mvtoEquipo_fechaMvto;
    private javax.swing.JLabel mvtoEquipo_laborRealizada;
    private javax.swing.JTextArea observacion_autorizacionMvtoCarbon;
    private javax.swing.JTextArea observacion_autorizacionMvtoEquipo;
    private javax.swing.JComboBox<Integer> pageJComboBox;
    public javax.swing.JPanel paginationPanel;
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
    private javax.swing.JTable tabla;
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
    private javax.swing.JLabel titulo37;
    private javax.swing.JLabel titulo38;
    private javax.swing.JLabel titulo39;
    private javax.swing.JLabel titulo40;
    // End of variables declaration//GEN-END:variables
    
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
        listado=new ControlDB_MvtoEquipo(tipoConexion).buscar_MvtoConRecobros(data1, data2);
        proveedorDeDatos = crearProveedorDeDatos(listado); 
        paginadorDeTabla = new PaginadorDeTabla(tabla, proveedorDeDatos, new int[]{5, 10, 20, 50, 75, 100}, 10);
        paginadorDeTabla.crearListadoDeFilasPermitidas(this.paginationPanel);
        pageJComboBox = paginadorDeTabla.getComboBoxPage();
        events();
        pageJComboBox.setSelectedItem(Integer.parseInt("50"));
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
