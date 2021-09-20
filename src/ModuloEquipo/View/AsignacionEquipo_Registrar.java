package ModuloEquipo.View;

import Catalogo.Controller.ControlDB_CentroOperacion;
import Catalogo.Model.CentroOperacion;
import ModuloEquipo.Controller.ControlDB_AsignacionEquipo;
import Catalogo.Controller.ControlDB_Equipo;
import ModuloEquipo.Controller.ControlDB_EstadoSolicitudEquipos;
import ModuloEquipo.Controller.ControlDB_SolicitudEquipo;
import ModuloEquipo.Model.AsignacionEquipo;
import Catalogo.Model.Equipo;
import Correos.mail;
import ModuloEquipo.Model.EstadoSolicitudEquipos;
import ModuloEquipo.Model.SolicitudEquipo;
import ModuloEquipo.Model.SolicitudListadoEquipo;
import Sistema.Controller.ControlDB_Usuario;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class AsignacionEquipo_Registrar extends javax.swing.JPanel {
    
    private ArrayList<CentroOperacion> listadoCentroOperacion = new ArrayList();
    private ArrayList<SolicitudEquipo> listadoSolicitudEquipo = new ArrayList();
    private ArrayList<Usuario> listadoUsuario = new ArrayList();
    private ArrayList<String> listadoMarcaEquipo =new ArrayList<>();
    private ArrayList<String> listadoTiposEquipo = new ArrayList();
    private ArrayList<String> listadoModelosEquipo = new ArrayList();
    private Usuario user;
    private ArrayList<Equipo> listadoEquipo = new ArrayList();
    SolicitudEquipo solicitudEquipo= new SolicitudEquipo();
    SolicitudListadoEquipo solicitudListadoEquipo = new SolicitudListadoEquipo();
    int valorSelect_SolicitudListadoEquipo=-1;
    //Asignacion
    private ArrayList<AsignacionEquipo> listadoAsignacionEquipo_Temp = new ArrayList();
    private ArrayList<AsignacionEquipo> listadoAsignacionEquipo = new ArrayList();
    private ArrayList<EstadoSolicitudEquipos> listadoEstadoSolicitud= new ArrayList();
    private String tipoConexion;
    public AsignacionEquipo_Registrar(Usuario us,String tipoConexion) throws SQLException {
        initComponents();   
        this.tipoConexion= tipoConexion;
        InternalFrame_AsignacionEquipos.getContentPane().setBackground(Color.WHITE);
        InternalFrame_SolicitudesEquipos.getContentPane().setBackground(Color.WHITE);
        InternalFrameSelectorCampos.getContentPane().setBackground(Color.WHITE);
        InternaFrameCronograma.getContentPane().setBackground(Color.WHITE);
        panelCronograma.setViewportView(new AsignacionCronograma(us,tipoConexion));
        
        user = us;
        listadoCentroOperacion=new ControlDB_CentroOperacion(tipoConexion).buscarActivos();
        listadoUsuario=new ControlDB_Usuario(tipoConexion).buscar("");
        
        //Cargamos en la interfaz los centros de Operaciones
        for(CentroOperacion CentroOperacion1: listadoCentroOperacion){
            selectCentroOperacion.addItem(CentroOperacion1.getDescripcion());
        }
        
        //Cargamos en la interfaz un listado de los usuarios
        for(Usuario Usuario1: listadoUsuario){
            selectUsuario.addItem(Usuario1.getNombres()+" "+Usuario1.getApellidos());
        }
            
        //Cargamos las horas y minutos
        for(int i = 0; i<= 23; i++){
            if(i<=9){
                horaInicio.addItem("0"+i);
                horaInicioAsignacion.addItem("0"+i);
                horaFin.addItem("0"+i);
                horaFinAsignacion.addItem("0"+i);
            }else{
                horaInicio.addItem(""+i);
                horaInicioAsignacion.addItem(""+i);
                horaFin.addItem(""+i);
                horaFinAsignacion.addItem(""+i);
            }
        }
        for(int i = 0; i<= 59; i++){
            if(i<=9){
                minutoInicio.addItem("0"+i);
                minutoInicioAsignacion.addItem("0"+i);
                minutoFin.addItem("0"+i);
                minutoFinAsignacion.addItem("0"+i);
            }else{
                minutoInicio.addItem(""+i);
                minutoInicioAsignacion.addItem(""+i);
                minutoFin.addItem(""+i);
                minutoFinAsignacion.addItem(""+i);
            }
        }
        selectorCampoPorDefecto();
        //Ocultamos la ventana de consulta de solicitudes
        InternalFrame_SolicitudesEquipos.show(true);
        InternalFrameSelectorCampos.show(false);
        InternalFrame_AsignacionEquipos.show(false);
        InternaFrameCronograma.show(false);
        
        //Cargamos datos de la InternaFrame
       
        //Cargamos en la interfaz los tipos de equipos
        try {
            listadoTiposEquipo=new ControlDB_Equipo(tipoConexion).buscarTipoEquiposEnAplicacionInterna();
            if(listadoTiposEquipo != null){
                String datosObjeto[]= new String[listadoTiposEquipo.size()];
                int contador=0;
                for(String listadoTiposEquipo1 : listadoTiposEquipo){ 
                    datosObjeto[contador]=listadoTiposEquipo1;
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                selectTipo.setModel(model);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
        //cargamos las marcas
        try {
            if(listadoTiposEquipo != null){
                listadoMarcaEquipo=new ControlDB_Equipo(tipoConexion).cargarMarcasEquiposEnAplicacionInterna(listadoTiposEquipo.get(selectTipo.getSelectedIndex()));
                if(listadoMarcaEquipo != null){
                    String datosObjeto[]= new String[listadoMarcaEquipo.size()];
                    int contador=0;
                    for(String Objeto : listadoMarcaEquipo){ 
                        datosObjeto[contador]=Objeto;
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    selectMarca.setModel(model);
                } 
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
        try {
            if(listadoTiposEquipo != null && listadoMarcaEquipo != null ){
                listadoModelosEquipo=new ControlDB_Equipo(tipoConexion).cargarModelosEquiposEnAplicacionInterna(listadoTiposEquipo.get(selectTipo.getSelectedIndex()),listadoMarcaEquipo.get(selectMarca.getSelectedIndex()));
                //listadoMarcaEquipo=new ControlDB_Equipo().cargarMarcasEquiposEnAplicacionInterna(listadoTiposEquipo.get(selectTipo.getSelectedIndex()));
                if(listadoModelosEquipo != null){
                    String datosObjeto[]= new String[listadoModelosEquipo.size()];
                    int contador=0;
                    for(String Objeto : listadoModelosEquipo){ 
                        datosObjeto[contador]=Objeto;
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    selectModelo.setModel(model);
                    
                    //Cargamos el selector de Equipos
                    listadoEquipo=new ControlDB_Equipo(tipoConexion).Asignacion_buscarEquiposEnAplicacionInterna(selectTipo.getSelectedItem().toString(),selectMarca.getSelectedItem().toString(), selectModelo.getSelectedItem().toString());              
                    String dataEquipo[]= new String[listadoEquipo.size()];
                    for(int i = 0; i< dataEquipo.length; i++){
                        dataEquipo[i]=""+(listadoEquipo.get(i).getCodigo()+" "+listadoEquipo.get(i).getDescripcion()+ " "+ listadoEquipo.get(i).getModelo());   
                    }    
                    final DefaultComboBoxModel modelListadoEquipos = new DefaultComboBoxModel(dataEquipo);
                    selectEquipos.setModel(modelListadoEquipos);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsignacionEquipo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
        //Cargamos en la interfaz los estados de Solicitudes de Equipos
        try {
            listadoEstadoSolicitud=new ControlDB_EstadoSolicitudEquipos(tipoConexion).buscarActivosProcesarAsignacion();
            if(listadoEstadoSolicitud != null){
                String datosObjeto[]= new String[listadoEstadoSolicitud.size()];
                int contador=0;
                for(EstadoSolicitudEquipos listadoEstadoSolicitud1 : listadoEstadoSolicitud){ 
                    //System.out.println(""+listadoEstadoSolicitud1.getDescripcion());
                    datosObjeto[contador]=listadoEstadoSolicitud1.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                estadoSolicitud.setModel(model);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(AsignacionEquipo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        //Ocultamos las fechas
        viewFechas(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Cargar = new javax.swing.JPopupMenu();
        Cargar1 = new javax.swing.JMenuItem();
        SeleccionarSolicitud = new javax.swing.JPopupMenu();
        selectSolicitud = new javax.swing.JMenuItem();
        EliminarAsignacionTemporal = new javax.swing.JPopupMenu();
        EliminarAsignacionTemporal1 = new javax.swing.JMenuItem();
        EliminarAsignacion = new javax.swing.JPopupMenu();
        EliminarAsignacion1 = new javax.swing.JMenuItem();
        InternaFrameCronograma = new javax.swing.JInternalFrame();
        panelCronograma = new javax.swing.JScrollPane();
        InternalFrame_SolicitudesEquipos = new javax.swing.JInternalFrame();
        titulo34 = new javax.swing.JLabel();
        checkFechaSolicitud = new javax.swing.JRadioButton();
        selectCentroOperacion = new javax.swing.JComboBox<>();
        checkCentroOperacion = new javax.swing.JRadioButton();
        fechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        horaInicio = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        minutoInicio = new javax.swing.JComboBox<>();
        horarioTiempoInicioSolicitudEquiposRegistrar = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        fechaFin = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        horaFin = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        minutoFin = new javax.swing.JComboBox<>();
        horarioTiempoIFinalSolicitudEquiposRegistrar = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        checkUsuario = new javax.swing.JRadioButton();
        selectUsuario = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_ListadoSolicitudes = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        titulo66 = new javax.swing.JLabel();
        InternalFrame_AsignacionEquipos = new javax.swing.JInternalFrame();
        titulo44 = new javax.swing.JLabel();
        asignacion_tipoEquipo = new javax.swing.JLabel();
        titulo38 = new javax.swing.JLabel();
        titulo45 = new javax.swing.JLabel();
        asignacion_marcaEquipo = new javax.swing.JLabel();
        titulo46 = new javax.swing.JLabel();
        asignacion_modeloEquipo = new javax.swing.JLabel();
        titulo47 = new javax.swing.JLabel();
        asignacion_cantidadEquipo = new javax.swing.JLabel();
        titulo48 = new javax.swing.JLabel();
        asignacion_fechaHoraFin = new javax.swing.JLabel();
        titulo49 = new javax.swing.JLabel();
        asignacion_fechaHoraInicio = new javax.swing.JLabel();
        titulo50 = new javax.swing.JLabel();
        titulo51 = new javax.swing.JLabel();
        titulo52 = new javax.swing.JLabel();
        titulo53 = new javax.swing.JLabel();
        asignacion_laborRealizada = new javax.swing.JLabel();
        asignacion_subcentroCosto = new javax.swing.JLabel();
        asignacion_Auxiliar = new javax.swing.JLabel();
        asignacion_compañia = new javax.swing.JLabel();
        asignacion_motonave = new javax.swing.JLabel();
        titulo54 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        titulo55 = new javax.swing.JLabel();
        titulo56 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        titulo57 = new javax.swing.JLabel();
        selectTipo = new javax.swing.JComboBox<>();
        titulo58 = new javax.swing.JLabel();
        selectMarca = new javax.swing.JComboBox<>();
        titulo59 = new javax.swing.JLabel();
        selectModelo = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        fechaInicioAsignacion = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        horaInicioAsignacion = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        minutoInicioAsignacion = new javax.swing.JComboBox<>();
        horarioTiempoInicioSolicitudEquiposRegistrar1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        fechaFinAsignacion = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        horaFinAsignacion = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        minutoFinAsignacion = new javax.swing.JComboBox<>();
        horarioTiempoIFinalSolicitudEquiposRegistrar1 = new javax.swing.JLabel();
        selectEquipos = new javax.swing.JComboBox<>();
        titulo60 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla_ListadoAsignacionTemp = new javax.swing.JTable();
        registrar = new javax.swing.JButton();
        cargarEquiposAsignados = new javax.swing.JButton();
        titulo64 = new javax.swing.JLabel();
        titulo65 = new javax.swing.JLabel();
        InternalFrameSelectorCampos = new javax.swing.JInternalFrame();
        jButton3 = new javax.swing.JButton();
        selectAll = new javax.swing.JRadioButton();
        selectCentroCostoAuxiliar_estado = new javax.swing.JRadioButton();
        selectSolicitudEquipo_codigo = new javax.swing.JRadioButton();
        selectSolicitudEquipo_marca = new javax.swing.JRadioButton();
        selectSolicitudEquipo_observacion = new javax.swing.JRadioButton();
        selectSolicitudEquipo_fechaInicio = new javax.swing.JRadioButton();
        selectSolicitudEquipo_fechaFinalizacion = new javax.swing.JRadioButton();
        selectSolicitudEquipo_cantidadHoras = new javax.swing.JRadioButton();
        selectSolicitudEquipo_cantidad = new javax.swing.JRadioButton();
        selectCentroCostoAuxiliar_codigo = new javax.swing.JRadioButton();
        selectCentroCostoSubcentro_codigo = new javax.swing.JRadioButton();
        selectCentroCostoSubcentro_estado = new javax.swing.JRadioButton();
        selectTipoEquipo_codigo = new javax.swing.JRadioButton();
        selectTipoEquipo_estado = new javax.swing.JRadioButton();
        titulo2 = new javax.swing.JLabel();
        titulo6 = new javax.swing.JLabel();
        titulo7 = new javax.swing.JLabel();
        titulo9 = new javax.swing.JLabel();
        titulo13 = new javax.swing.JLabel();
        jSeparator21 = new javax.swing.JSeparator();
        jButton4 = new javax.swing.JButton();
        selectSolicitudEquipo_modelo = new javax.swing.JRadioButton();
        selectTipoEquipo_nombre = new javax.swing.JRadioButton();
        selectCentroCostoAuxiliar_nombre = new javax.swing.JRadioButton();
        selectCentroCostoSubcentro_nombre = new javax.swing.JRadioButton();
        selectMotonave_codigo = new javax.swing.JRadioButton();
        selectMotonave_nombre = new javax.swing.JRadioButton();
        selectMotonave_estado = new javax.swing.JRadioButton();
        titulo23 = new javax.swing.JLabel();
        selectActividadOperacion_codigo = new javax.swing.JRadioButton();
        selectActividadOperacion_nombre = new javax.swing.JRadioButton();
        selectActividadOperacion_estado = new javax.swing.JRadioButton();
        titulo24 = new javax.swing.JLabel();
        selectCompañia_codigo = new javax.swing.JRadioButton();
        selectCompañia_nombre = new javax.swing.JRadioButton();
        selectCompañia_estado = new javax.swing.JRadioButton();
        titulo25 = new javax.swing.JLabel();
        titulo26 = new javax.swing.JLabel();
        titulo18 = new javax.swing.JLabel();
        titulo15 = new javax.swing.JLabel();
        titulo27 = new javax.swing.JLabel();
        titulo67 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        equiposSugeridos = new javax.swing.JTextArea();
        titulo35 = new javax.swing.JLabel();
        titulo36 = new javax.swing.JLabel();
        titulo28 = new javax.swing.JLabel();
        titulo37 = new javax.swing.JLabel();
        solicitud_estado = new javax.swing.JLabel();
        solicitud_fecha = new javax.swing.JLabel();
        solicitud_centroOperacion = new javax.swing.JLabel();
        solicitud_codigo = new javax.swing.JLabel();
        titulo39 = new javax.swing.JLabel();
        titulo40 = new javax.swing.JLabel();
        solicitud_usuarioCorreo = new javax.swing.JLabel();
        solicitud_usuarioCedula = new javax.swing.JLabel();
        solicitud_usuarioNombre = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        titulo31 = new javax.swing.JLabel();
        titulo41 = new javax.swing.JLabel();
        titulo42 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_ListadoEquiposSolicitud = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        titulo43 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabla_ListadoAsignacion = new javax.swing.JTable();
        titulo32 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        titulo61 = new javax.swing.JLabel();
        titulo62 = new javax.swing.JLabel();
        titulo63 = new javax.swing.JLabel();
        estadoSolicitud = new javax.swing.JComboBox<>();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        titulo33 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();

        Cargar1.setText("Cargar");
        Cargar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cargar1ActionPerformed(evt);
            }
        });
        Cargar.add(Cargar1);

        selectSolicitud.setText("Seleccionar");
        selectSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectSolicitudActionPerformed(evt);
            }
        });
        SeleccionarSolicitud.add(selectSolicitud);

        EliminarAsignacionTemporal1.setText("Eliminar");
        EliminarAsignacionTemporal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarAsignacionTemporal1ActionPerformed(evt);
            }
        });
        EliminarAsignacionTemporal.add(EliminarAsignacionTemporal1);

        EliminarAsignacion1.setText("Eliminar");
        EliminarAsignacion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarAsignacion1ActionPerformed(evt);
            }
        });
        EliminarAsignacion.add(EliminarAsignacion1);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InternaFrameCronograma.setClosable(true);
        InternaFrameCronograma.setVisible(false);
        InternaFrameCronograma.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        InternaFrameCronograma.getContentPane().add(panelCronograma, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 670));

        add(InternaFrameCronograma, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1330, 650));

        InternalFrame_SolicitudesEquipos.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrame_SolicitudesEquipos.setClosable(true);
        InternalFrame_SolicitudesEquipos.setVisible(false);
        InternalFrame_SolicitudesEquipos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo34.setForeground(new java.awt.Color(102, 102, 102));
        titulo34.setText("FILTROS");
        InternalFrame_SolicitudesEquipos.getContentPane().add(titulo34, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 80, 30));

        checkFechaSolicitud.setBackground(new java.awt.Color(255, 255, 255));
        checkFechaSolicitud.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        checkFechaSolicitud.setForeground(new java.awt.Color(51, 51, 51));
        checkFechaSolicitud.setText("FECHA DE SOLICITUD:");
        checkFechaSolicitud.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        checkFechaSolicitud.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkFechaSolicitudItemStateChanged(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(checkFechaSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 160, 30));

        selectCentroOperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectCentroOperacionActionPerformed(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(selectCentroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 300, 30));

        checkCentroOperacion.setBackground(new java.awt.Color(255, 255, 255));
        checkCentroOperacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        checkCentroOperacion.setForeground(new java.awt.Color(51, 51, 51));
        checkCentroOperacion.setText("CENTRO DE OPERACIÓN:");
        checkCentroOperacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        checkCentroOperacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkCentroOperacionItemStateChanged(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(checkCentroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 200, 30));

        fechaInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicioMouseEntered(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 170, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Hora");
        InternalFrame_SolicitudesEquipos.getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 60, -1, 30));

        horaInicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                horaInicioItemStateChanged(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(horaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 60, 50, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText(":");
        InternalFrame_SolicitudesEquipos.getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 60, 20, 30));

        minutoInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoInicioActionPerformed(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(minutoInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 60, 50, 30));

        horarioTiempoInicioSolicitudEquiposRegistrar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoInicioSolicitudEquiposRegistrar.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoInicioSolicitudEquiposRegistrar.setText("AM");
        InternalFrame_SolicitudesEquipos.getContentPane().add(horarioTiempoInicioSolicitudEquiposRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 60, 40, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Inicio:");
        InternalFrame_SolicitudesEquipos.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, 60, 30));

        fechaFin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFinMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFinMouseEntered(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(fechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 170, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Hora");
        InternalFrame_SolicitudesEquipos.getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 100, -1, 30));

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
        InternalFrame_SolicitudesEquipos.getContentPane().add(horaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 100, 50, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel24.setText(":");
        InternalFrame_SolicitudesEquipos.getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 100, 20, 30));

        minutoFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoFinActionPerformed(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(minutoFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 100, 50, 30));

        horarioTiempoIFinalSolicitudEquiposRegistrar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoIFinalSolicitudEquiposRegistrar.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoIFinalSolicitudEquiposRegistrar.setText("AM");
        InternalFrame_SolicitudesEquipos.getContentPane().add(horarioTiempoIFinalSolicitudEquiposRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 100, 50, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Finalización:");
        InternalFrame_SolicitudesEquipos.getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, 120, 30));

        checkUsuario.setBackground(new java.awt.Color(255, 255, 255));
        checkUsuario.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        checkUsuario.setForeground(new java.awt.Color(51, 51, 51));
        checkUsuario.setText("USUARIO QUIEN LA REALIZÓ:");
        checkUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        checkUsuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkUsuarioItemStateChanged(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(checkUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 180, 30));

        selectUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectUsuarioActionPerformed(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(selectUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 300, 30));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        jButton2.setText("CONSULTAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 180, 40));

        tabla_ListadoSolicitudes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla_ListadoSolicitudes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla_ListadoSolicitudes.setComponentPopupMenu(Cargar);
        tabla_ListadoSolicitudes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_ListadoSolicitudesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_ListadoSolicitudes);

        InternalFrame_SolicitudesEquipos.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 1260, 350));
        InternalFrame_SolicitudesEquipos.getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 1260, 10));
        InternalFrame_SolicitudesEquipos.getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 1260, 10));

        titulo66.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo66.setForeground(new java.awt.Color(102, 102, 102));
        titulo66.setText("CONSULTAR SOLICITUDES");
        InternalFrame_SolicitudesEquipos.getContentPane().add(titulo66, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 450, 30));

        add(InternalFrame_SolicitudesEquipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1330, 750));

        InternalFrame_AsignacionEquipos.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrame_AsignacionEquipos.setClosable(true);
        InternalFrame_AsignacionEquipos.setVisible(false);
        InternalFrame_AsignacionEquipos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo44.setForeground(new java.awt.Color(51, 51, 51));
        titulo44.setText("Tipo Equipo:");
        InternalFrame_AsignacionEquipos.getContentPane().add(titulo44, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 160, 20));

        asignacion_tipoEquipo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        asignacion_tipoEquipo.setForeground(new java.awt.Color(102, 102, 102));
        InternalFrame_AsignacionEquipos.getContentPane().add(asignacion_tipoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 300, 20));

        titulo38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo38.setForeground(new java.awt.Color(102, 102, 102));
        titulo38.setText("Datos de Asignación:");
        InternalFrame_AsignacionEquipos.getContentPane().add(titulo38, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 250, 20));

        titulo45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo45.setForeground(new java.awt.Color(51, 51, 51));
        titulo45.setText("Marca Equipo:");
        InternalFrame_AsignacionEquipos.getContentPane().add(titulo45, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 160, 20));

        asignacion_marcaEquipo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        asignacion_marcaEquipo.setForeground(new java.awt.Color(102, 102, 102));
        InternalFrame_AsignacionEquipos.getContentPane().add(asignacion_marcaEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 300, 20));

        titulo46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo46.setForeground(new java.awt.Color(51, 51, 51));
        titulo46.setText("Modelo Equipo:");
        InternalFrame_AsignacionEquipos.getContentPane().add(titulo46, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 160, 20));

        asignacion_modeloEquipo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        asignacion_modeloEquipo.setForeground(new java.awt.Color(102, 102, 102));
        InternalFrame_AsignacionEquipos.getContentPane().add(asignacion_modeloEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 300, 20));

        titulo47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo47.setForeground(new java.awt.Color(51, 51, 51));
        titulo47.setText("Cantidad Equipo:");
        InternalFrame_AsignacionEquipos.getContentPane().add(titulo47, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 160, 20));

        asignacion_cantidadEquipo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        asignacion_cantidadEquipo.setForeground(new java.awt.Color(102, 102, 102));
        InternalFrame_AsignacionEquipos.getContentPane().add(asignacion_cantidadEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 300, 20));

        titulo48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo48.setForeground(new java.awt.Color(51, 51, 51));
        titulo48.setText("Equipos Sugeridos:");
        InternalFrame_AsignacionEquipos.getContentPane().add(titulo48, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, 160, 20));

        asignacion_fechaHoraFin.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        asignacion_fechaHoraFin.setForeground(new java.awt.Color(102, 102, 102));
        InternalFrame_AsignacionEquipos.getContentPane().add(asignacion_fechaHoraFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 300, 20));

        titulo49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo49.setForeground(new java.awt.Color(51, 51, 51));
        titulo49.setText("Fecha /Hora Inicio:");
        InternalFrame_AsignacionEquipos.getContentPane().add(titulo49, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 160, 20));

        asignacion_fechaHoraInicio.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        asignacion_fechaHoraInicio.setForeground(new java.awt.Color(102, 102, 102));
        InternalFrame_AsignacionEquipos.getContentPane().add(asignacion_fechaHoraInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 300, 20));

        titulo50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo50.setForeground(new java.awt.Color(51, 51, 51));
        titulo50.setText("Fecha /Hora Fin:");
        InternalFrame_AsignacionEquipos.getContentPane().add(titulo50, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 160, 20));

        titulo51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo51.setForeground(new java.awt.Color(51, 51, 51));
        titulo51.setText("Actividad a Desarrollar:");
        InternalFrame_AsignacionEquipos.getContentPane().add(titulo51, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 160, 20));

        titulo52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo52.setForeground(new java.awt.Color(51, 51, 51));
        titulo52.setText("SubCentro Costo:");
        InternalFrame_AsignacionEquipos.getContentPane().add(titulo52, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 160, 20));

        titulo53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo53.setForeground(new java.awt.Color(51, 51, 51));
        titulo53.setText("C.C Auxiliar:");
        InternalFrame_AsignacionEquipos.getContentPane().add(titulo53, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 160, 20));

        asignacion_laborRealizada.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        asignacion_laborRealizada.setForeground(new java.awt.Color(102, 102, 102));
        InternalFrame_AsignacionEquipos.getContentPane().add(asignacion_laborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 40, 290, 20));

        asignacion_subcentroCosto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        asignacion_subcentroCosto.setForeground(new java.awt.Color(102, 102, 102));
        InternalFrame_AsignacionEquipos.getContentPane().add(asignacion_subcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, 290, 20));

        asignacion_Auxiliar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        asignacion_Auxiliar.setForeground(new java.awt.Color(102, 102, 102));
        InternalFrame_AsignacionEquipos.getContentPane().add(asignacion_Auxiliar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, 290, 20));

        asignacion_compañia.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        asignacion_compañia.setForeground(new java.awt.Color(102, 102, 102));
        InternalFrame_AsignacionEquipos.getContentPane().add(asignacion_compañia, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 100, 290, 20));

        asignacion_motonave.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        asignacion_motonave.setForeground(new java.awt.Color(102, 102, 102));
        InternalFrame_AsignacionEquipos.getContentPane().add(asignacion_motonave, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 120, 290, 20));

        titulo54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo54.setForeground(new java.awt.Color(51, 51, 51));
        titulo54.setText("C.C Compañía:");
        InternalFrame_AsignacionEquipos.getContentPane().add(titulo54, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 160, 20));
        InternalFrame_AsignacionEquipos.getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 1080, 10));

        titulo55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo55.setForeground(new java.awt.Color(102, 102, 102));
        titulo55.setText("Añadir Asignación:");
        InternalFrame_AsignacionEquipos.getContentPane().add(titulo55, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 250, 20));

        titulo56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo56.setForeground(new java.awt.Color(102, 102, 102));
        titulo56.setText("Datos de solicitud:");
        InternalFrame_AsignacionEquipos.getContentPane().add(titulo56, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 250, 20));
        InternalFrame_AsignacionEquipos.getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 910, 10));

        titulo57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo57.setForeground(new java.awt.Color(51, 51, 51));
        titulo57.setText("Tipo Equipo:");
        InternalFrame_AsignacionEquipos.getContentPane().add(titulo57, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 120, 30));

        selectTipo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        selectTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectTipoItemStateChanged(evt);
            }
        });
        selectTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectTipoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                selectTipoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                selectTipoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                selectTipoMousePressed(evt);
            }
        });
        selectTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectTipoActionPerformed(evt);
            }
        });
        InternalFrame_AsignacionEquipos.getContentPane().add(selectTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 390, 30));

        titulo58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo58.setForeground(new java.awt.Color(51, 51, 51));
        titulo58.setText("Equipo:");
        InternalFrame_AsignacionEquipos.getContentPane().add(titulo58, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 250, 60, 30));

        selectMarca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectMarcaItemStateChanged(evt);
            }
        });
        selectMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectMarcaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                selectMarcaMouseExited(evt);
            }
        });
        selectMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMarcaActionPerformed(evt);
            }
        });
        InternalFrame_AsignacionEquipos.getContentPane().add(selectMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 210, 450, 30));

        titulo59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo59.setForeground(new java.awt.Color(51, 51, 51));
        titulo59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/reporte.png"))); // NOI18N
        titulo59.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                titulo59MouseClicked(evt);
            }
        });
        InternalFrame_AsignacionEquipos.getContentPane().add(titulo59, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 360, 40, 40));

        selectModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectModeloActionPerformed(evt);
            }
        });
        InternalFrame_AsignacionEquipos.getContentPane().add(selectModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 390, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Fecha/Hora Inicio:");
        InternalFrame_AsignacionEquipos.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 120, 30));

        fechaInicioAsignacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicioAsignacionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicioAsignacionMouseEntered(evt);
            }
        });
        InternalFrame_AsignacionEquipos.getContentPane().add(fechaInicioAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 170, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Hora");
        InternalFrame_AsignacionEquipos.getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 290, -1, 30));

        horaInicioAsignacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                horaInicioAsignacionItemStateChanged(evt);
            }
        });
        InternalFrame_AsignacionEquipos.getContentPane().add(horaInicioAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, 50, 30));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText(":");
        InternalFrame_AsignacionEquipos.getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, 20, 30));

        minutoInicioAsignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoInicioAsignacionActionPerformed(evt);
            }
        });
        InternalFrame_AsignacionEquipos.getContentPane().add(minutoInicioAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, 50, 30));

        horarioTiempoInicioSolicitudEquiposRegistrar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        horarioTiempoInicioSolicitudEquiposRegistrar1.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoInicioSolicitudEquiposRegistrar1.setText("AM");
        InternalFrame_AsignacionEquipos.getContentPane().add(horarioTiempoInicioSolicitudEquiposRegistrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 290, 40, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Fecha /Hora Fin");
        InternalFrame_AsignacionEquipos.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, 120, 30));

        fechaFinAsignacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFinAsignacionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFinAsignacionMouseEntered(evt);
            }
        });
        InternalFrame_AsignacionEquipos.getContentPane().add(fechaFinAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 290, 170, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Hora");
        InternalFrame_AsignacionEquipos.getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 290, -1, 30));

        horaFinAsignacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                horaFinAsignacionItemStateChanged(evt);
            }
        });
        horaFinAsignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horaFinAsignacionActionPerformed(evt);
            }
        });
        InternalFrame_AsignacionEquipos.getContentPane().add(horaFinAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 290, 50, 30));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel27.setText(":");
        InternalFrame_AsignacionEquipos.getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 290, 20, 30));

        minutoFinAsignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoFinAsignacionActionPerformed(evt);
            }
        });
        InternalFrame_AsignacionEquipos.getContentPane().add(minutoFinAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 290, 50, 30));

        horarioTiempoIFinalSolicitudEquiposRegistrar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        horarioTiempoIFinalSolicitudEquiposRegistrar1.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoIFinalSolicitudEquiposRegistrar1.setText("AM");
        InternalFrame_AsignacionEquipos.getContentPane().add(horarioTiempoIFinalSolicitudEquiposRegistrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 290, 50, 30));

        selectEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectEquiposActionPerformed(evt);
            }
        });
        InternalFrame_AsignacionEquipos.getContentPane().add(selectEquipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 250, 450, 30));

        titulo60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo60.setForeground(new java.awt.Color(51, 51, 51));
        titulo60.setText("Marca:");
        InternalFrame_AsignacionEquipos.getContentPane().add(titulo60, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 140, 30));

        tabla_ListadoAsignacionTemp = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla_ListadoAsignacionTemp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla_ListadoAsignacionTemp.setComponentPopupMenu(EliminarAsignacionTemporal);
        tabla_ListadoAsignacionTemp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_ListadoAsignacionTempMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabla_ListadoAsignacionTemp);

        InternalFrame_AsignacionEquipos.getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 1100, 140));

        registrar.setBackground(new java.awt.Color(255, 255, 255));
        registrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Icono_añadir.png"))); // NOI18N
        registrar.setText("AGREGAR EQUIPO");
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });
        InternalFrame_AsignacionEquipos.getContentPane().add(registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, 190, 40));

        cargarEquiposAsignados.setBackground(new java.awt.Color(255, 255, 255));
        cargarEquiposAsignados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/registrar.png"))); // NOI18N
        cargarEquiposAsignados.setText("CARGAR EQUIPOS ASIGNADOS");
        cargarEquiposAsignados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarEquiposAsignadosActionPerformed(evt);
            }
        });
        InternalFrame_AsignacionEquipos.getContentPane().add(cargarEquiposAsignados, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 550, -1, 30));

        titulo64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo64.setForeground(new java.awt.Color(51, 51, 51));
        titulo64.setText("Modelo:");
        InternalFrame_AsignacionEquipos.getContentPane().add(titulo64, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 120, 30));

        titulo65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo65.setForeground(new java.awt.Color(51, 51, 51));
        titulo65.setText("Ver Asignación");
        InternalFrame_AsignacionEquipos.getContentPane().add(titulo65, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 350, 100, 20));

        InternalFrameSelectorCampos.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrameSelectorCampos.setClosable(true);
        InternalFrameSelectorCampos.setVisible(false);
        InternalFrameSelectorCampos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCamposAplicarConfig.png"))); // NOI18N
        jButton3.setText("Aplicar Configuración");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 260, 230, 30));

        selectAll.setBackground(new java.awt.Color(255, 255, 255));
        selectAll.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        selectAll.setText("Todos");
        selectAll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectAll.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectAllItemStateChanged(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 110, 20));

        selectCentroCostoAuxiliar_estado.setBackground(new java.awt.Color(255, 255, 255));
        selectCentroCostoAuxiliar_estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectCentroCostoAuxiliar_estado.setForeground(new java.awt.Color(51, 51, 51));
        selectCentroCostoAuxiliar_estado.setText("Centro Costo Auxiliar Estado");
        selectCentroCostoAuxiliar_estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectCentroCostoAuxiliar_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 190, -1));

        selectSolicitudEquipo_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectSolicitudEquipo_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectSolicitudEquipo_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectSolicitudEquipo_codigo.setText("Solicitud Código");
        selectSolicitudEquipo_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectSolicitudEquipo_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectSolicitudEquipo_codigoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectSolicitudEquipo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 190, -1));

        selectSolicitudEquipo_marca.setBackground(new java.awt.Color(255, 255, 255));
        selectSolicitudEquipo_marca.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectSolicitudEquipo_marca.setForeground(new java.awt.Color(51, 51, 51));
        selectSolicitudEquipo_marca.setText("Solicitud Marca");
        selectSolicitudEquipo_marca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectSolicitudEquipo_marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 190, -1));

        selectSolicitudEquipo_observacion.setBackground(new java.awt.Color(255, 255, 255));
        selectSolicitudEquipo_observacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectSolicitudEquipo_observacion.setForeground(new java.awt.Color(51, 51, 51));
        selectSolicitudEquipo_observacion.setText("Solicitud Observación");
        selectSolicitudEquipo_observacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectSolicitudEquipo_observacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 190, -1));

        selectSolicitudEquipo_fechaInicio.setBackground(new java.awt.Color(255, 255, 255));
        selectSolicitudEquipo_fechaInicio.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectSolicitudEquipo_fechaInicio.setForeground(new java.awt.Color(51, 51, 51));
        selectSolicitudEquipo_fechaInicio.setText("Solicitud Fecha Inicio");
        selectSolicitudEquipo_fechaInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectSolicitudEquipo_fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 190, -1));

        selectSolicitudEquipo_fechaFinalizacion.setBackground(new java.awt.Color(255, 255, 255));
        selectSolicitudEquipo_fechaFinalizacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectSolicitudEquipo_fechaFinalizacion.setForeground(new java.awt.Color(51, 51, 51));
        selectSolicitudEquipo_fechaFinalizacion.setText("Solicitud Fecha Finalización");
        selectSolicitudEquipo_fechaFinalizacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectSolicitudEquipo_fechaFinalizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectSolicitudEquipo_fechaFinalizacionActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectSolicitudEquipo_fechaFinalizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 190, -1));

        selectSolicitudEquipo_cantidadHoras.setBackground(new java.awt.Color(255, 255, 255));
        selectSolicitudEquipo_cantidadHoras.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectSolicitudEquipo_cantidadHoras.setForeground(new java.awt.Color(51, 51, 51));
        selectSolicitudEquipo_cantidadHoras.setText("Solicitud Cantidad Horas");
        selectSolicitudEquipo_cantidadHoras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectSolicitudEquipo_cantidadHoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectSolicitudEquipo_cantidadHorasActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectSolicitudEquipo_cantidadHoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 190, -1));

        selectSolicitudEquipo_cantidad.setBackground(new java.awt.Color(255, 255, 255));
        selectSolicitudEquipo_cantidad.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectSolicitudEquipo_cantidad.setForeground(new java.awt.Color(51, 51, 51));
        selectSolicitudEquipo_cantidad.setText("Solicitud Cantidad");
        selectSolicitudEquipo_cantidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectSolicitudEquipo_cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 190, -1));

        selectCentroCostoAuxiliar_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectCentroCostoAuxiliar_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectCentroCostoAuxiliar_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectCentroCostoAuxiliar_codigo.setText("Centro Costo Auxiliar Código");
        selectCentroCostoAuxiliar_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectCentroCostoAuxiliar_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 190, -1));

        selectCentroCostoSubcentro_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectCentroCostoSubcentro_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectCentroCostoSubcentro_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectCentroCostoSubcentro_codigo.setText("Centro Costo Subcentro Código");
        selectCentroCostoSubcentro_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectCentroCostoSubcentro_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 190, -1));

        selectCentroCostoSubcentro_estado.setBackground(new java.awt.Color(255, 255, 255));
        selectCentroCostoSubcentro_estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectCentroCostoSubcentro_estado.setForeground(new java.awt.Color(51, 51, 51));
        selectCentroCostoSubcentro_estado.setText("Centro Costo Subcentro Estado");
        selectCentroCostoSubcentro_estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectCentroCostoSubcentro_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 190, -1));

        selectTipoEquipo_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectTipoEquipo_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectTipoEquipo_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectTipoEquipo_codigo.setText("Tipo Equipo Código");
        selectTipoEquipo_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectTipoEquipo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 190, -1));

        selectTipoEquipo_estado.setBackground(new java.awt.Color(255, 255, 255));
        selectTipoEquipo_estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectTipoEquipo_estado.setForeground(new java.awt.Color(51, 51, 51));
        selectTipoEquipo_estado.setText("Tipo Equipo Estado");
        selectTipoEquipo_estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectTipoEquipo_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, 190, -1));

        titulo2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo2.setText("SELECTOR DE CAMPOS");
        titulo2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 960, 40));

        titulo6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo6.setText("CENTRO COSTO AUXILIAR");
        titulo6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 240, 20));

        titulo7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo7.setText("CENTRO COSTO SUBCENTRO");
        titulo7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 240, 20));

        titulo9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo9.setText("TIPO EQUIPO");
        titulo9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo9, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 240, 20));

        titulo13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo13.setText("SOLICITUD EQUIPO");
        titulo13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 240, 20));

        jSeparator21.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternalFrameSelectorCampos.getContentPane().add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 0, 430));

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCamposDefauls.png"))); // NOI18N
        jButton4.setText("Campos Predeterminados");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 230, 30));

        selectSolicitudEquipo_modelo.setBackground(new java.awt.Color(255, 255, 255));
        selectSolicitudEquipo_modelo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectSolicitudEquipo_modelo.setForeground(new java.awt.Color(51, 51, 51));
        selectSolicitudEquipo_modelo.setText("Solicitud Modelo");
        selectSolicitudEquipo_modelo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectSolicitudEquipo_modelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectSolicitudEquipo_modeloActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectSolicitudEquipo_modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 190, -1));

        selectTipoEquipo_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectTipoEquipo_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectTipoEquipo_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectTipoEquipo_nombre.setText("Tipo Equipo Nombre");
        selectTipoEquipo_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectTipoEquipo_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, 190, -1));

        selectCentroCostoAuxiliar_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectCentroCostoAuxiliar_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectCentroCostoAuxiliar_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectCentroCostoAuxiliar_nombre.setText("Centro Costo Auxiliar Nombre");
        selectCentroCostoAuxiliar_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectCentroCostoAuxiliar_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 190, -1));

        selectCentroCostoSubcentro_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectCentroCostoSubcentro_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectCentroCostoSubcentro_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectCentroCostoSubcentro_nombre.setText("Centro Costo Subcentro Nombre");
        selectCentroCostoSubcentro_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectCentroCostoSubcentro_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 190, -1));

        selectMotonave_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMotonave_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMotonave_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMotonave_codigo.setText("Motonave Código");
        selectMotonave_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMotonave_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 70, 190, -1));

        selectMotonave_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMotonave_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMotonave_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMotonave_nombre.setText("Motonave Nombre");
        selectMotonave_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMotonave_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 90, 190, 20));

        selectMotonave_estado.setBackground(new java.awt.Color(255, 255, 255));
        selectMotonave_estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMotonave_estado.setForeground(new java.awt.Color(51, 51, 51));
        selectMotonave_estado.setText("Motonave Estado");
        selectMotonave_estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMotonave_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 110, 190, -1));

        titulo23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo23.setText("ACTIVIDAD OPERACIÓN");
        titulo23.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo23, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, 240, 20));

        selectActividadOperacion_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectActividadOperacion_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectActividadOperacion_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectActividadOperacion_codigo.setText("Actividad Operación Código");
        selectActividadOperacion_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectActividadOperacion_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 160, 190, -1));

        selectActividadOperacion_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectActividadOperacion_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectActividadOperacion_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectActividadOperacion_nombre.setText("Actividad Operación Nombre");
        selectActividadOperacion_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectActividadOperacion_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, 190, -1));

        selectActividadOperacion_estado.setBackground(new java.awt.Color(255, 255, 255));
        selectActividadOperacion_estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectActividadOperacion_estado.setForeground(new java.awt.Color(51, 51, 51));
        selectActividadOperacion_estado.setText("Actividad Operación Estado");
        selectActividadOperacion_estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectActividadOperacion_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, 190, -1));

        titulo24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo24.setText("COMPAÑIA");
        titulo24.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo24, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 130, 240, 20));

        selectCompañia_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectCompañia_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectCompañia_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectCompañia_codigo.setText("Compañia NIT");
        selectCompañia_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectCompañia_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 160, 190, -1));

        selectCompañia_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectCompañia_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectCompañia_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectCompañia_nombre.setText("Compañia Nombre");
        selectCompañia_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectCompañia_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 180, 190, -1));

        selectCompañia_estado.setBackground(new java.awt.Color(255, 255, 255));
        selectCompañia_estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectCompañia_estado.setForeground(new java.awt.Color(51, 51, 51));
        selectCompañia_estado.setText("Compañia Estado");
        selectCompañia_estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectCompañia_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 200, 190, -1));

        titulo25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo25.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 240, 210));

        titulo26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo26.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo26, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 240, 210));

        titulo18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo18.setText("MOTONAVE");
        titulo18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo18, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, 240, 20));

        titulo15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo15, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, 240, 210));

        titulo27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo27.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo27, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 240, 210));

        InternalFrame_AsignacionEquipos.getContentPane().add(InternalFrameSelectorCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 360));

        titulo67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo67.setForeground(new java.awt.Color(51, 51, 51));
        titulo67.setText("Motonave:");
        InternalFrame_AsignacionEquipos.getContentPane().add(titulo67, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, 160, 20));

        equiposSugeridos.setEditable(false);
        equiposSugeridos.setColumns(20);
        equiposSugeridos.setRows(5);
        jScrollPane1.setViewportView(equiposSugeridos);

        InternalFrame_AsignacionEquipos.getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 140, 460, 60));

        add(InternalFrame_AsignacionEquipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1310, 700));

        titulo35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo35.setForeground(new java.awt.Color(102, 102, 102));
        titulo35.setText("ASIGNACIÓN EQUIPOS (REGISTRAR)");
        add(titulo35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 340, 30));

        titulo36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo36.setForeground(new java.awt.Color(0, 102, 102));
        titulo36.setText("Usuario Correo:");
        add(titulo36, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, 130, 20));

        titulo28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo28.setForeground(new java.awt.Color(0, 102, 102));
        titulo28.setText("Centro Operación:");
        add(titulo28, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 130, 20));

        titulo37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo37.setForeground(new java.awt.Color(51, 51, 51));
        titulo37.setText("Estado Solicitud:");
        add(titulo37, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 720, 130, 40));

        solicitud_estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        solicitud_estado.setForeground(new java.awt.Color(102, 102, 102));
        add(solicitud_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 210, 20));

        solicitud_fecha.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        solicitud_fecha.setForeground(new java.awt.Color(102, 102, 102));
        add(solicitud_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 210, 20));

        solicitud_centroOperacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        solicitud_centroOperacion.setForeground(new java.awt.Color(102, 102, 102));
        add(solicitud_centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 210, 20));

        solicitud_codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        solicitud_codigo.setForeground(new java.awt.Color(153, 0, 0));
        add(solicitud_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 210, 20));

        titulo39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo39.setForeground(new java.awt.Color(0, 102, 102));
        titulo39.setText("Usuario Cedula:");
        add(titulo39, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 130, 20));

        titulo40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo40.setForeground(new java.awt.Color(0, 102, 102));
        titulo40.setText("Usuario Nombre:");
        add(titulo40, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 130, 20));

        solicitud_usuarioCorreo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        solicitud_usuarioCorreo.setForeground(new java.awt.Color(102, 102, 102));
        add(solicitud_usuarioCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, 410, 20));

        solicitud_usuarioCedula.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        solicitud_usuarioCedula.setForeground(new java.awt.Color(102, 102, 102));
        add(solicitud_usuarioCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, 410, 20));

        solicitud_usuarioNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        solicitud_usuarioNombre.setForeground(new java.awt.Color(102, 102, 102));
        add(solicitud_usuarioNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 410, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/agregar.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 80, 60));

        titulo31.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        titulo31.setForeground(new java.awt.Color(51, 51, 51));
        titulo31.setText("Cargar");
        add(titulo31, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 50, 30));

        titulo41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo41.setForeground(new java.awt.Color(0, 102, 102));
        titulo41.setText("LISTA DE EQUIPOS ASIGNADOS");
        add(titulo41, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 500, 250, 30));

        titulo42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo42.setForeground(new java.awt.Color(0, 102, 102));
        titulo42.setText("Fecha Elaboración:");
        add(titulo42, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 130, 20));

        tabla_ListadoEquiposSolicitud = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla_ListadoEquiposSolicitud.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla_ListadoEquiposSolicitud.setComponentPopupMenu(SeleccionarSolicitud);
        tabla_ListadoEquiposSolicitud.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_ListadoEquiposSolicitudMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabla_ListadoEquiposSolicitud);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 1260, 320));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 1260, 10));

        titulo43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo43.setForeground(new java.awt.Color(0, 102, 102));
        titulo43.setText("Solicitud Equipos #");
        add(titulo43, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 130, 20));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCampo.png"))); // NOI18N
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 130, -1, -1));

        tabla_ListadoAsignacion = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla_ListadoAsignacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla_ListadoAsignacion.setComponentPopupMenu(EliminarAsignacion);
        tabla_ListadoAsignacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_ListadoAsignacionMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tabla_ListadoAsignacion);

        add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 1260, 160));

        titulo32.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        titulo32.setForeground(new java.awt.Color(0, 153, 153));
        titulo32.setText("Selector Campos");
        add(titulo32, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 110, 90, 20));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save.png"))); // NOI18N
        jButton1.setText("GUARDAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 720, 200, 40));

        titulo61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo61.setForeground(new java.awt.Color(51, 51, 51));
        titulo61.setText("INFORMACIÓN DE LA SOLICITUD");
        add(titulo61, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 220, 20));

        titulo62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo62.setForeground(new java.awt.Color(0, 102, 102));
        titulo62.setText("LISTA DE EQUIPOS SOLICITADOS");
        add(titulo62, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, 220, 20));

        titulo63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo63.setForeground(new java.awt.Color(0, 102, 102));
        titulo63.setText("Estado Solicitud:");
        add(titulo63, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 130, 20));

        estadoSolicitud.setToolTipText("");
        add(estadoSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 720, 330, 40));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 20, 70));
        add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 1260, 10));

        titulo33.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        titulo33.setForeground(new java.awt.Color(51, 51, 51));
        titulo33.setText("Solicitud Equipos");
        add(titulo33, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 90, 30));
        add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1230, 10));
        add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 890, 10));

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 80, 10, 70));

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 10, 70));
        add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 890, 10));
    }// </editor-fold>//GEN-END:initComponents

    private void checkFechaSolicitudItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkFechaSolicitudItemStateChanged
        if(checkFechaSolicitud.isSelected()){
            viewFechas(true);
        }else{
            viewFechas(false);
        }
    }//GEN-LAST:event_checkFechaSolicitudItemStateChanged

    private void selectCentroOperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectCentroOperacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectCentroOperacionActionPerformed

    private void checkCentroOperacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkCentroOperacionItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_checkCentroOperacionItemStateChanged

    private void fechaInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicioMouseClicked
        //  alerta_fechaInicio.setText("");
    }//GEN-LAST:event_fechaInicioMouseClicked

    private void fechaInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicioMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaInicioMouseEntered

    private void horaInicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_horaInicioItemStateChanged
        if(horaInicio.getSelectedIndex()<= 11){
            horarioTiempoInicioSolicitudEquiposRegistrar.setText("AM");
        }else{
            horarioTiempoInicioSolicitudEquiposRegistrar.setText("PM");
        }
    }//GEN-LAST:event_horaInicioItemStateChanged

    private void minutoInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minutoInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minutoInicioActionPerformed

    private void fechaFinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFinMouseClicked
        //alerta_fechaFinal.setText("");
    }//GEN-LAST:event_fechaFinMouseClicked

    private void fechaFinMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFinMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFinMouseEntered

    private void horaFinItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_horaFinItemStateChanged
        if(horaFin.getSelectedIndex()<= 11){
            horarioTiempoIFinalSolicitudEquiposRegistrar.setText("AM");
        }else{
            horarioTiempoIFinalSolicitudEquiposRegistrar.setText("PM");
        }
    }//GEN-LAST:event_horaFinItemStateChanged

    private void horaFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horaFinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_horaFinActionPerformed

    private void minutoFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minutoFinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minutoFinActionPerformed

    private void checkUsuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkUsuarioItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_checkUsuarioItemStateChanged

    private void selectUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectUsuarioActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        InternalFrameSelectorCampos.show(false);
        try {
            tabla_Listar();
        } catch (SQLException ex) {
            Logger.getLogger(AsignacionEquipo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void selectAllItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectAllItemStateChanged
        if(selectAll.isSelected()){
            selectorCampoTodos();
        }else{
            selectorCampoNinguno();
        }
    }//GEN-LAST:event_selectAllItemStateChanged

    private void selectSolicitudEquipo_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectSolicitudEquipo_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectSolicitudEquipo_codigoActionPerformed

    private void selectSolicitudEquipo_cantidadHorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectSolicitudEquipo_cantidadHorasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectSolicitudEquipo_cantidadHorasActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        selectorCampoPorDefecto();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void selectSolicitudEquipo_modeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectSolicitudEquipo_modeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectSolicitudEquipo_modeloActionPerformed

    private void tabla_ListadoSolicitudesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ListadoSolicitudesMouseClicked
        if (evt.getClickCount() == 2) {
            int fila1;
            try{
                fila1=tabla_ListadoSolicitudes.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ningun Vehiculo");
                }
                else{
                  //Hacemos una limpieza a las tablas que contienen las asignaciones por si el usuario cambia de solicitud
                    //Eliminar todos los elementos de una tabla Asignación temporal
                    DefaultTableModel modeloAsignTemp =(DefaultTableModel)tabla_ListadoAsignacionTemp.getModel();
                    int CantEliminarAsigTemp= tabla_ListadoAsignacionTemp.getRowCount() -1;
                    for(int i =CantEliminarAsigTemp; i>=0; i--){
                        modeloAsignTemp.removeRow(i);
                    }

                    listadoAsignacionEquipo= new ArrayList(); 
                  //Eliminar todos los elementos de una tabla Asignación
                    DefaultTableModel modeloAsign =(DefaultTableModel)tabla_ListadoAsignacion.getModel();
                    int CantEliminarAsig= tabla_ListadoAsignacion.getRowCount() -1;
                    for(int i =CantEliminarAsig; i>=0; i--){
                            modeloAsign.removeRow(i);
                    }
                  //Limpiamos el ArrayList Temporal para en caso de guardar futuros listado de Equipos
                    listadoAsignacionEquipo= new ArrayList(); 

                  //Procedemos a cargar la nueva solicitud de Equipo    
                    solicitudEquipo= listadoSolicitudEquipo.get(fila1);
                    solicitudEquipo=new ControlDB_SolicitudEquipo(tipoConexion).buscarSolicitudEquipoParticular(solicitudEquipo);
                    try{
                      //Cambiamos el estado de la solicitud de equipos a revisiòn a Revisión
                        int retornoCambioEstadoSolicitudEquipo=new ControlDB_SolicitudEquipo(tipoConexion).solicitudEquipo_ActualizarEstado_General(solicitudEquipo, user, 2,"REVISIÓN");
                        if(retornoCambioEstadoSolicitudEquipo==1){
                           //Cargamos los Datos en la interfaz Principal
                            solicitud_codigo.setText(solicitudEquipo.getCodigo());
                            solicitud_fecha.setText(solicitudEquipo.getFechaSolicitud());
                            solicitud_centroOperacion.setText(solicitudEquipo.getCentroOperacion().getDescripcion());
                            solicitud_estado.setText(solicitudEquipo.getEstadoSolicitudEquipo().getDescripcion());
                            solicitud_usuarioCedula.setText(solicitudEquipo.getUsuarioRealizaSolicitud().getCodigo());
                            solicitud_usuarioNombre.setText(solicitudEquipo.getUsuarioRealizaSolicitud().getNombres()+" "+solicitudEquipo.getUsuarioRealizaSolicitud().getApellidos());
                            solicitud_usuarioCorreo.setText(solicitudEquipo.getUsuarioRealizaSolicitud().getCorreo());
                            //Cargamos los datos en la tabla
                            tabla_Listar();
                            InternalFrame_SolicitudesEquipos.show(false); 
                        }else{
                            JOptionPane.showMessageDialog(null, "No se pudo cambiar el estado de la solicitud de Equipo a Revisión","Advertencia",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null, "No se pudo cambiar el estado de la solicitud de Equipo a Revisión","Advertencia",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }catch(Exception e){
            }
        }
    }//GEN-LAST:event_tabla_ListadoSolicitudesMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String sql="";
        String fecha_Inicial="";
        String fecha_Final="";
        int contadorFecha=0;
        if(checkCentroOperacion.isSelected()){
            if(sql.equals("")){
                sql +=" WHERE [co_cdgo]="+listadoCentroOperacion.get(selectCentroOperacion.getSelectedIndex()).getCodigo();
            }else{
                sql +=" AND [co_cdgo]="+listadoCentroOperacion.get(selectCentroOperacion.getSelectedIndex()).getCodigo();
            }
        }
        if(checkUsuario.isSelected()){
            if(sql.equals("")){
                sql +=" WHERE [us_cdgo]="+listadoUsuario.get(selectUsuario.getSelectedIndex()).getCodigo();
            }else{
                sql +=" AND [us_cdgo]="+listadoUsuario.get(selectUsuario.getSelectedIndex()).getCodigo();
            }
        }
        if(checkFechaSolicitud.isSelected()){
            //fechaB=true;
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
                fecha_Inicial=anoI+"-"+mesI+"-"+diaI;
                contadorFecha++;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Verifique fecha de Inicio","Error", JOptionPane.ERROR_MESSAGE);
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
                fecha_Final=anoF+"-"+mesF+"-"+diaF;
                contadorFecha++;
            }catch(Exception e){
                 JOptionPane.showMessageDialog(null, "Verifique fecha final","Error", JOptionPane.ERROR_MESSAGE);
            }
            if(contadorFecha==2){//Se validaron las dos fechas y contienen formato correcto
                fecha_Inicial = fecha_Inicial+" "+horaInicio.getSelectedItem().toString()+":"+minutoInicio.getSelectedItem().toString();
                fecha_Final = fecha_Final+" "+horaFin.getSelectedItem().toString()+":"+minutoFin.getSelectedItem().toString();
                if(sql.equals("")){
                    sql +=" WHERE [se_fecha_registro] BETWEEN '"+fecha_Inicial+"' AND '"+fecha_Final+"'";
                }else{
                    sql +=" AND [se_fecha_registro] BETWEEN '"+fecha_Inicial+"' AND '"+fecha_Final+"'";
                }
            }   
        } 
        //Validamos que sean solicitudes en estado de elaboración (1)
        if(sql.equals("")){
            sql +=" WHERE ([ese_cdgo] = 1 OR [ese_cdgo] = 2) AND [se_confirmacion_solicitud_equipo_cdgo] IS NULL  ORDER BY [se_fecha_registro] DESC";
        }else{
            sql +=" AND ([ese_cdgo] = 1 OR [ese_cdgo] = 2) AND [se_confirmacion_solicitud_equipo_cdgo] IS NULL ORDER BY [se_fecha_registro] DESC";
        }
        try {
            DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"C.O","Fecha", "Usuario_Cedula","Usuario_Nombre","Fecha_Registro","Estado"});      
            listadoSolicitudEquipo=new ControlDB_SolicitudEquipo(tipoConexion).buscarSolicitudes(sql);
            if(listadoSolicitudEquipo != null){
                for (SolicitudEquipo solicutdEquipo1 : listadoSolicitudEquipo) {
                    String[] registro = new String[6];
                    registro[0] = "" + solicutdEquipo1.getCentroOperacion().getDescripcion();
                    registro[1] = "" + solicutdEquipo1.getFechaSolicitud();
                    registro[2] = "" + solicutdEquipo1.getUsuarioRealizaSolicitud().getCodigo();
                    registro[3] = "" + solicutdEquipo1.getUsuarioRealizaSolicitud().getNombres()+" "+solicutdEquipo1.getUsuarioRealizaSolicitud().getApellidos();
                    registro[4] = "" + solicutdEquipo1.getFechaRegistro();
                    registro[5] = "" + solicutdEquipo1.getEstadoSolicitudEquipo().getDescripcion();
                    modelo.addRow(registro);      
                }
            }
            tabla_ListadoSolicitudes.setModel(modelo);
            //resizeColumnWidth(tabla_ListadoSolicitudes);
        } catch (SQLException ex) {
            Logger.getLogger(AsignacionEquipo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Cargar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cargar1ActionPerformed
        int fila1;
        try{
            fila1=tabla_ListadoSolicitudes.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ningun Vehiculo");
            }
            else{
              //Hacemos una limpieza a las tablas que contienen las asignaciones por si el usuario cambia de solicitud
                //Eliminar todos los elementos de una tabla Asignación temporal
                DefaultTableModel modeloAsignTemp =(DefaultTableModel)tabla_ListadoAsignacionTemp.getModel();
                int CantEliminarAsigTemp= tabla_ListadoAsignacionTemp.getRowCount() -1;
                for(int i =CantEliminarAsigTemp; i>=0; i--){
                    modeloAsignTemp.removeRow(i);
                }

                listadoAsignacionEquipo= new ArrayList(); 
              //Eliminar todos los elementos de una tabla Asignación
                DefaultTableModel modeloAsign =(DefaultTableModel)tabla_ListadoAsignacion.getModel();
                int CantEliminarAsig= tabla_ListadoAsignacion.getRowCount() -1;
                for(int i =CantEliminarAsig; i>=0; i--){
                        modeloAsign.removeRow(i);
                }
              //Limpiamos el ArrayList Temporal para en caso de guardar futuros listado de Equipos
                listadoAsignacionEquipo= new ArrayList(); 
                
              //Procedemos a cargar la nueva solicitud de Equipo    
                solicitudEquipo= listadoSolicitudEquipo.get(fila1);
                solicitudEquipo=new ControlDB_SolicitudEquipo(tipoConexion).buscarSolicitudEquipoParticular(solicitudEquipo);
                try{
                  //Cambiamos el estado de la solicitud de equipos a revisiòn a Revisión
                    int retornoCambioEstadoSolicitudEquipo=new ControlDB_SolicitudEquipo(tipoConexion).solicitudEquipo_ActualizarEstado_General(solicitudEquipo, user, 2, "REVISIÓN");
                    if(retornoCambioEstadoSolicitudEquipo==1){
                       //Cargamos los Datos en la interfaz Principal
                        solicitud_codigo.setText(solicitudEquipo.getCodigo());
                        solicitud_fecha.setText(solicitudEquipo.getFechaSolicitud());
                        solicitud_centroOperacion.setText(solicitudEquipo.getCentroOperacion().getDescripcion());
                        solicitud_estado.setText(solicitudEquipo.getEstadoSolicitudEquipo().getDescripcion());
                        solicitud_usuarioCedula.setText(solicitudEquipo.getUsuarioRealizaSolicitud().getCodigo());
                        solicitud_usuarioNombre.setText(solicitudEquipo.getUsuarioRealizaSolicitud().getNombres()+" "+solicitudEquipo.getUsuarioRealizaSolicitud().getApellidos());
                        solicitud_usuarioCorreo.setText(solicitudEquipo.getUsuarioRealizaSolicitud().getCorreo());
                        //Cargamos los datos en la tabla
                        tabla_Listar();
                        InternalFrame_SolicitudesEquipos.show(false); 
                    }else{
                        JOptionPane.showMessageDialog(null, "No se pudo cambiar el estado de la solicitud de Equipo a Revisión","Advertencia",JOptionPane.INFORMATION_MESSAGE);
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "No se pudo cambiar el estado de la solicitud de Equipo a Revisión","Advertencia",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_Cargar1ActionPerformed

    private void tabla_ListadoEquiposSolicitudMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ListadoEquiposSolicitudMouseClicked
        if (evt.getClickCount() == 2) {
            int fila1;
            try{
                fila1=tabla_ListadoEquiposSolicitud.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ningun Vehiculo");
                }
                else{
                    solicitudListadoEquipo= solicitudEquipo.getListadoSolicitudesEquipos().get(tabla_ListadoEquiposSolicitud.getSelectedRow());
                    asignacion_tipoEquipo.setText(solicitudListadoEquipo.getTipoEquipo().getDescripcion());
                    asignacion_marcaEquipo.setText(solicitudListadoEquipo.getMarcaEquipo());
                    asignacion_modeloEquipo.setText(solicitudListadoEquipo.getModeloEquipo());
                    asignacion_cantidadEquipo.setText(""+solicitudListadoEquipo.getCantidad());
                    asignacion_fechaHoraInicio.setText(solicitudListadoEquipo.getFechaHoraInicio());
                    asignacion_fechaHoraFin.setText(solicitudListadoEquipo.getFechaHoraFin());
                    asignacion_laborRealizada.setText(solicitudListadoEquipo.getLaborRealizada().getDescripcion());
                    asignacion_subcentroCosto.setText(solicitudListadoEquipo.getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion());
                    asignacion_Auxiliar.setText(solicitudListadoEquipo.getCentroCostoAuxiliar().getDescripcion());
                    asignacion_compañia.setText(solicitudListadoEquipo.getCompañia().getDescripcion());
                    asignacion_motonave.setText(solicitudListadoEquipo.getMotonave().getDescripcion());
                    equiposSugeridos.setText(solicitudListadoEquipo.getObservacacion());
                    InternalFrame_AsignacionEquipos.show(true);
                  //Cargamos la fecha y la hora de Inicio      
                    String[] fechaInicioA= solicitudListadoEquipo.getFechaHoraInicio().split(" ");
                    String[] horaInicioB= fechaInicioA[1].split(":");
                    SimpleDateFormat dateFormatInicio = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechaInicioM=dateFormatInicio.parse(fechaInicioA[0]);
                    fechaInicioAsignacion.setDate(fechaInicioM);
                    horaInicioAsignacion.setSelectedItem(horaInicioB[0]);
                    minutoInicioAsignacion.setSelectedItem(horaInicioB[1]);
                  //Cargamos la fecha y la hora de Finalización      
                    String[] fechaFinalA= solicitudListadoEquipo.getFechaHoraFin().split(" ");
                    String[] horaFinalB= fechaFinalA[1].split(":");
                    SimpleDateFormat dateFormatFinal = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechaFinalM=dateFormatFinal.parse(fechaFinalA[0]);
                    fechaFinAsignacion.setDate(fechaFinalM);
                    horaFinAsignacion.setSelectedItem(horaFinalB[0]);
                    minutoFinAsignacion.setSelectedItem(horaFinalB[1]);
                  //Cargamos por defectos los select según selección
                    selectTipo.setSelectedItem((Object)solicitudListadoEquipo.getTipoEquipo().getDescripcion());
                    selectMarca.setSelectedItem((Object)solicitudListadoEquipo.getMarcaEquipo());
                    selectModelo.setSelectedItem((Object)solicitudListadoEquipo.getModeloEquipo());
                }
            }catch(Exception e){
            }
        }
    }//GEN-LAST:event_tabla_ListadoEquiposSolicitudMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        InternalFrame_SolicitudesEquipos.show(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void selectSolicitudEquipo_fechaFinalizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectSolicitudEquipo_fechaFinalizacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectSolicitudEquipo_fechaFinalizacionActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        InternalFrameSelectorCampos.show(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void selectSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectSolicitudActionPerformed
        int fila1;
        try{
            fila1=tabla_ListadoEquiposSolicitud.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna solicitud");
            }
            else{
                solicitudListadoEquipo= solicitudEquipo.getListadoSolicitudesEquipos().get(tabla_ListadoEquiposSolicitud.getSelectedRow());
                asignacion_tipoEquipo.setText(solicitudListadoEquipo.getTipoEquipo().getDescripcion());
                asignacion_marcaEquipo.setText(solicitudListadoEquipo.getMarcaEquipo());
                asignacion_modeloEquipo.setText(solicitudListadoEquipo.getModeloEquipo());
                asignacion_cantidadEquipo.setText(""+solicitudListadoEquipo.getCantidad());
                asignacion_fechaHoraInicio.setText(solicitudListadoEquipo.getFechaHoraInicio());
                asignacion_fechaHoraFin.setText(solicitudListadoEquipo.getFechaHoraFin());
                asignacion_laborRealizada.setText(solicitudListadoEquipo.getLaborRealizada().getDescripcion());
                asignacion_subcentroCosto.setText(solicitudListadoEquipo.getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion());
                asignacion_Auxiliar.setText(solicitudListadoEquipo.getCentroCostoAuxiliar().getDescripcion());
                asignacion_compañia.setText(solicitudListadoEquipo.getCompañia().getDescripcion());
                asignacion_motonave.setText(solicitudListadoEquipo.getMotonave().getDescripcion());
                equiposSugeridos.setText(solicitudListadoEquipo.getObservacacion());
                InternalFrame_AsignacionEquipos.show(true);
              //Cargamos la fecha y la hora de Inicio      
                String[] fechaInicioA= solicitudListadoEquipo.getFechaHoraInicio().split(" ");
                String[] horaInicioB= fechaInicioA[1].split(":");
                SimpleDateFormat dateFormatInicio = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaInicioM=dateFormatInicio.parse(fechaInicioA[0]);
                fechaInicioAsignacion.setDate(fechaInicioM);
                horaInicioAsignacion.setSelectedItem(horaInicioB[0]);
                minutoInicioAsignacion.setSelectedItem(horaInicioB[1]);
              //Cargamos la fecha y la hora de Finalización      
                String[] fechaFinalA= solicitudListadoEquipo.getFechaHoraFin().split(" ");
                String[] horaFinalB= fechaFinalA[1].split(":");
                SimpleDateFormat dateFormatFinal = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaFinalM=dateFormatFinal.parse(fechaFinalA[0]);
                fechaFinAsignacion.setDate(fechaFinalM);
                horaFinAsignacion.setSelectedItem(horaFinalB[0]);
                minutoFinAsignacion.setSelectedItem(horaFinalB[1]);
              //Cargamos por defectos los select según selección
                selectTipo.setSelectedItem((Object)solicitudListadoEquipo.getTipoEquipo().getDescripcion());
                selectMarca.setSelectedItem((Object)solicitudListadoEquipo.getMarcaEquipo());
                selectModelo.setSelectedItem((Object)solicitudListadoEquipo.getModeloEquipo());
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_selectSolicitudActionPerformed

    private void selectTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectTipoItemStateChanged

    }//GEN-LAST:event_selectTipoItemStateChanged

    private void selectTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectTipoMouseClicked

    }//GEN-LAST:event_selectTipoMouseClicked

    private void selectTipoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectTipoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_selectTipoMouseEntered

    private void selectTipoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectTipoMouseExited

    }//GEN-LAST:event_selectTipoMouseExited

    private void selectTipoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectTipoMousePressed

    }//GEN-LAST:event_selectTipoMousePressed

    private void selectTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectTipoActionPerformed
        try {
            listadoMarcaEquipo = new ControlDB_Equipo(tipoConexion).cargarMarcasEquiposEnAplicacionInterna(selectTipo.getSelectedItem().toString());
            if (listadoMarcaEquipo != null) {
                String datosObjeto[] = new String[listadoMarcaEquipo.size()];
                int contador = 0;
                for (String listadoMarcaEquipo1 : listadoMarcaEquipo) {
                    datosObjeto[contador] = listadoMarcaEquipo1;
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                selectMarca.setModel(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            listadoModelosEquipo = new ControlDB_Equipo(tipoConexion).cargarModelosEquiposEnAplicacionInterna(selectTipo.getSelectedItem().toString(),selectMarca.getSelectedItem().toString());
            if (listadoModelosEquipo != null) {
                String datosObjeto[] = new String[listadoModelosEquipo.size()];
                int contador = 0;
                for (String listadoModelosEquipo1 : listadoModelosEquipo) {
                    datosObjeto[contador] = listadoModelosEquipo1;
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                selectModelo.setModel(model);
                
                //Cargamos el selector de Equipos
                listadoEquipo=new ControlDB_Equipo(tipoConexion).Asignacion_buscarEquiposEnAplicacionInterna(selectTipo.getSelectedItem().toString(),selectMarca.getSelectedItem().toString(), selectModelo.getSelectedItem().toString());              
                String dataEquipo[]= new String[listadoEquipo.size()];
                for(int i = 0; i< dataEquipo.length; i++){
                    //dataEquipo[i]=""+(listadoEquipo.get(i).getDescripcion());  
                    dataEquipo[i]=""+(listadoEquipo.get(i).getCodigo()+" "+listadoEquipo.get(i).getDescripcion()+ " "+ listadoEquipo.get(i).getModelo());
                }    
                final DefaultComboBoxModel modelListadoEquipos = new DefaultComboBoxModel(dataEquipo);
                selectEquipos.setModel(modelListadoEquipos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsignacionEquipo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_selectTipoActionPerformed

    private void selectMarcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectMarcaItemStateChanged

    }//GEN-LAST:event_selectMarcaItemStateChanged

    private void selectMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMarcaMouseClicked

    }//GEN-LAST:event_selectMarcaMouseClicked

    private void selectMarcaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMarcaMouseExited

    }//GEN-LAST:event_selectMarcaMouseExited

    private void selectMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMarcaActionPerformed
        try {
            if(listadoTiposEquipo != null && listadoMarcaEquipo != null ){
                listadoModelosEquipo=new ControlDB_Equipo(tipoConexion).cargarModelosEquiposEnAplicacionInterna(listadoTiposEquipo.get(selectTipo.getSelectedIndex()),listadoMarcaEquipo.get(selectMarca.getSelectedIndex()));
                //listadoMarcaEquipo=new ControlDB_Equipo().cargarMarcasEquiposEnAplicacionInterna(listadoTiposEquipo.get(selectTipo.getSelectedIndex()));
                if(listadoModelosEquipo != null){
                    String datosObjeto[]= new String[listadoModelosEquipo.size()];
                    int contador=0;
                    for(String Objeto : listadoModelosEquipo){
                        datosObjeto[contador]=Objeto;
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    selectModelo.setModel(model);
                    
                    //Cargamos el selector de Equipos
                    listadoEquipo=new ControlDB_Equipo(tipoConexion).Asignacion_buscarEquiposEnAplicacionInterna(selectTipo.getSelectedItem().toString(),selectMarca.getSelectedItem().toString(), selectModelo.getSelectedItem().toString());              
                    String dataEquipo[]= new String[listadoEquipo.size()];
                    for(int i = 0; i< dataEquipo.length; i++){
                        //dataEquipo[i]=""+(listadoEquipo.get(i).getDescripcion());  
                        dataEquipo[i]=""+(listadoEquipo.get(i).getCodigo()+" "+listadoEquipo.get(i).getDescripcion()+ " "+ listadoEquipo.get(i).getModelo());
                    }    
                    final DefaultComboBoxModel modelListadoEquipos = new DefaultComboBoxModel(dataEquipo);
                    selectEquipos.setModel(modelListadoEquipos);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsignacionEquipo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_selectMarcaActionPerformed

    private void selectModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectModeloActionPerformed
        try {
            //Cargamos el selector de Equipos
            listadoEquipo=new ControlDB_Equipo(tipoConexion).Asignacion_buscarEquiposEnAplicacionInterna(selectTipo.getSelectedItem().toString(),selectMarca.getSelectedItem().toString(), selectModelo.getSelectedItem().toString());              
            String dataEquipo[]= new String[listadoEquipo.size()];
            for(int i = 0; i< dataEquipo.length; i++){
                dataEquipo[i]=""+(listadoEquipo.get(i).getCodigo()+" "+listadoEquipo.get(i).getDescripcion()+ " "+ listadoEquipo.get(i).getModelo());   
            }    
            final DefaultComboBoxModel modelListadoEquipos = new DefaultComboBoxModel(dataEquipo);
            selectEquipos.setModel(modelListadoEquipos);
        } catch (SQLException ex) {
            Logger.getLogger(AsignacionEquipo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_selectModeloActionPerformed

    private void fechaInicioAsignacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicioAsignacionMouseClicked
        //  alerta_fechaInicio.setText("");
    }//GEN-LAST:event_fechaInicioAsignacionMouseClicked

    private void fechaInicioAsignacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicioAsignacionMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaInicioAsignacionMouseEntered

    private void horaInicioAsignacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_horaInicioAsignacionItemStateChanged
        if(horaInicio.getSelectedIndex()<= 11){
            horarioTiempoInicioSolicitudEquiposRegistrar.setText("AM");
        }else{
            horarioTiempoInicioSolicitudEquiposRegistrar.setText("PM");
        }
    }//GEN-LAST:event_horaInicioAsignacionItemStateChanged

    private void minutoInicioAsignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minutoInicioAsignacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minutoInicioAsignacionActionPerformed

    private void fechaFinAsignacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFinAsignacionMouseClicked
        //alerta_fechaFinal.setText("");
    }//GEN-LAST:event_fechaFinAsignacionMouseClicked

    private void fechaFinAsignacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFinAsignacionMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFinAsignacionMouseEntered

    private void horaFinAsignacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_horaFinAsignacionItemStateChanged
        if(horaFin.getSelectedIndex()<= 11){
            horarioTiempoIFinalSolicitudEquiposRegistrar.setText("AM");
        }else{
            horarioTiempoIFinalSolicitudEquiposRegistrar.setText("PM");
        }
    }//GEN-LAST:event_horaFinAsignacionItemStateChanged

    private void horaFinAsignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horaFinAsignacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_horaFinAsignacionActionPerformed

    private void minutoFinAsignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minutoFinAsignacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minutoFinAsignacionActionPerformed

    private void selectEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectEquiposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectEquiposActionPerformed

    private void tabla_ListadoAsignacionTempMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ListadoAsignacionTempMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_ListadoAsignacionTempMouseClicked

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        Equipo equipoAsignacion = listadoEquipo.get(selectEquipos.getSelectedIndex());
        //Cargamos la fecha de inicio de movimientos de la solicitud
        String fechaInicio_Asignacion_Equipo="";
        try{
            Calendar fechaI = fechaInicioAsignacion.getCalendar();
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
                int valorFechaI=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorFechaActual(anoI, mesI, diaI, horaInicioAsignacion.getSelectedItem().toString(), minutoInicioAsignacion.getSelectedItem().toString()));
                if(valorFechaI<0){   
                    fechaInicio_Asignacion_Equipo=anoI+"-"+mesI+"-"+diaI+" "+horaInicioAsignacion.getSelectedItem().toString()+":"+minutoInicioAsignacion.getSelectedItem().toString()+":00.0";
                    //Cargamos la fecha de inicio de movimientos de la solicitud
                    String fechaFin_Asignacion_Equipo="";
                    try{
                        Calendar fechaF = fechaFinAsignacion.getCalendar();
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
                            int valorFechaF=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorFechaActual(anoF, mesF, diaF, horaFinAsignacion.getSelectedItem().toString(), minutoFinAsignacion.getSelectedItem().toString()));
                            if(valorFechaF<0){
                                fechaFin_Asignacion_Equipo=anoF+"-"+mesF+"-"+diaF+" "+horaFinAsignacion.getSelectedItem().toString()+":"+minutoFinAsignacion.getSelectedItem().toString()+":00.0";
                                int resultDosFechas=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorEntreDosFechas(anoI, mesI, diaI, horaInicioAsignacion.getSelectedItem().toString(), minutoInicioAsignacion.getSelectedItem().toString(), anoF, mesF, diaF, horaFinAsignacion.getSelectedItem().toString(), minutoFinAsignacion.getSelectedItem().toString()));
                                if(resultDosFechas < 0){
                                    JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser mayor a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                                }else{
                                    if(resultDosFechas ==0){
                                        JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser Igual a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                                    }else{
                                        //Validamos si el equipo ya se encuentra programado
                                        if(new ControlDB_AsignacionEquipo(tipoConexion).Asignacion_validarDisponibilidadEquipo(equipoAsignacion, fechaInicio_Asignacion_Equipo, fechaFin_Asignacion_Equipo)){//El equipo no se encuentra asignado en ese horario
                                            JOptionPane.showMessageDialog(null,"El equipo ya se encuentra programado en ese rango de fecha", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                        }else{//El equipo no se encuentra asignado en ese horario
                                            AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                                            asignacionEquipo.setSolicitudListadoEquipo(solicitudListadoEquipo);
                                            asignacionEquipo.setCentroOperacion(solicitudEquipo.getCentroOperacion());
                                            asignacionEquipo.setFechaHoraInicio(fechaInicio_Asignacion_Equipo);
                                            asignacionEquipo.setFechaHoraFin(fechaFin_Asignacion_Equipo);
                                            int cantMinutosProgramados=0;
                                            cantMinutosProgramados=new ControlDB_AsignacionEquipo(tipoConexion).cantidadHorasProgramadas(anoI, mesI, diaI, horaInicioAsignacion.getSelectedItem().toString(), minutoInicioAsignacion.getSelectedItem().toString(), anoF, mesF, diaF, horaFinAsignacion.getSelectedItem().toString(), minutoFinAsignacion.getSelectedItem().toString());
                                            if(cantMinutosProgramados !=0){
                                                asignacionEquipo.setCantidadMinutosProgramados(""+cantMinutosProgramados);
                                                asignacionEquipo.setEquipo(equipoAsignacion);
                                                asignacionEquipo.setPertenencia(equipoAsignacion.getPertenenciaEquipo());
                                                asignacionEquipo.setEstado("1");
                                                if(validarCargueEquiposInternos(equipoAsignacion)){
                                                    JOptionPane.showMessageDialog(null, "Error!!.. El equipo ya se encuentra cargado, verifique datos","Advertencia", JOptionPane.ERROR_MESSAGE );
                                                }else{
                                                    listadoAsignacionEquipo_Temp.add(asignacionEquipo);
                                                    DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"C.O","Equipo_Código","Equipo_Tipo", "Equipo_Marca","Equipo_Modelo","Equipo_Descripción","Fecha_Y_Hora_Inicio","Fecha_Y_Hora_Fin", "Horas Programadas"});      
                                                    for (AsignacionEquipo AsignacionEquipo1 : listadoAsignacionEquipo_Temp) {
                                                        String[] registro = new String[9];
                                                        registro[0] = "" + AsignacionEquipo1.getCentroOperacion().getDescripcion();
                                                        registro[1] = "" + AsignacionEquipo1.getEquipo().getCodigo();
                                                        registro[2] = "" + AsignacionEquipo1.getEquipo().getTipoEquipo().getDescripcion();
                                                        registro[3] = "" + AsignacionEquipo1.getEquipo().getMarca();
                                                        registro[4] = "" + AsignacionEquipo1.getEquipo().getModelo();
                                                        registro[5] = "" + AsignacionEquipo1.getEquipo().getDescripcion();
                                                        registro[6] = "" + AsignacionEquipo1.getFechaHoraInicio();
                                                        registro[7] = "" + AsignacionEquipo1.getFechaHoraFin();
                                                        
                                                        try{
                                                            //Calculamos la cantidad de Horas a partir de cierta cantidad de minutos
                                                            String minutoCantS ="";
                                                            int minutoCant=(Integer.parseInt(AsignacionEquipo1.getCantidadMinutosProgramados()) % 60);
                                                            int horasCant=(Integer.parseInt(AsignacionEquipo1.getCantidadMinutosProgramados())/60);
                                                            if(minutoCant<9){
                                                                minutoCantS="0"+minutoCant;
                                                            }else{
                                                                minutoCantS=""+minutoCant;
                                                            }
                                                            registro[8] = "" + horasCant+":"+minutoCantS;
                                                        }catch(Exception e){
                                                            registro[8] ="";
                                                        }
                                                        //registro[8] = "" + AsignacionEquipo1.getCantidadMinutosProgramados();
                                                        modelo.addRow(registro);      
                                                    }
                                                    tabla_ListadoAsignacionTemp.setModel(modelo);
                                                }
                                            }else{
                                                JOptionPane.showMessageDialog(null,"No se pudo calcular la cantidad de horas Programadas", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                            }
                                        }
                                    }
                                }
                            }else{
                                JOptionPane.showMessageDialog(null,"Error!!.. No puede cargar una fecha en el pasado, la fecha de Finalización de la Operación debe ser futura, verifique fecha","Advertencia",JOptionPane.ERROR_MESSAGE);
                            }
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(null,"Error!!.. Al trata de validar fecha actual del registro con fecha del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                        }
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Finalización","Advertencia",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Error!!.. No puede cargar una fecha en el pasado, la fecha de Inicio de Operación debe ser futura, verifique fecha","Advertencia",JOptionPane.ERROR_MESSAGE);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Error!!.. Al trata de validar fecha actual del registro con fecha del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Inicio","Advertencia",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_registrarActionPerformed

    private void cargarEquiposAsignadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarEquiposAsignadosActionPerformed
        for(AsignacionEquipo AsignacionEquipo1: listadoAsignacionEquipo_Temp){
            listadoAsignacionEquipo.add(AsignacionEquipo1);
        }
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"C.O","Equipo_Código","Equipo_Tipo", "Equiupo_Marca","Equipo_Modelo","Equipo_Descripción","Fecha Inicio","Fecha Finalización", "Horas Programadas"});      
        for (AsignacionEquipo AsignacionEquipo1 : listadoAsignacionEquipo) {
            String[] registro = new String[9];
            registro[0] = "" + AsignacionEquipo1.getCentroOperacion().getDescripcion();
            registro[1] = "" + AsignacionEquipo1.getEquipo().getTipoEquipo().getDescripcion();
            registro[2] = "" + AsignacionEquipo1.getEquipo().getCodigo();
            registro[3] = "" + AsignacionEquipo1.getEquipo().getMarca();
            registro[4] = "" + AsignacionEquipo1.getEquipo().getModelo();
            registro[5] = "" + AsignacionEquipo1.getEquipo().getDescripcion();
            registro[6] = "" + AsignacionEquipo1.getFechaHoraInicio();
            registro[7] = "" + AsignacionEquipo1.getFechaHoraFin();   
            try{
                //Calculamos la cantidad de Horas a partir de cierta cantidad de minutos
                String minutoCantS ="";
                int minutoCant=(Integer.parseInt(AsignacionEquipo1.getCantidadMinutosProgramados()) % 60);
                int horasCant=(Integer.parseInt(AsignacionEquipo1.getCantidadMinutosProgramados())/60);
                if(minutoCant<9){
                    minutoCantS="0"+minutoCant;
                }else{
                    minutoCantS=""+minutoCant;
                }
                registro[8] = "" + horasCant+":"+minutoCantS;
            }catch(Exception e){
                registro[8] ="";
            }
            //registro[8] = "" + AsignacionEquipo1.getCantidadMinutosProgramados();
            modelo.addRow(registro);      
        }
        tabla_ListadoAsignacion.setModel(modelo);
        //Eliminar todos los elementos de una tabla temporal
        DefaultTableModel md =(DefaultTableModel)tabla_ListadoAsignacionTemp.getModel();
        int CantEliminar= tabla_ListadoAsignacionTemp.getRowCount() -1;
        for(int i =CantEliminar; i>=0; i--){
                md.removeRow(i);
        }
        //Limpiamos el ArrayList Temporal para en caso de guardar futuros listado de Equipos
        listadoAsignacionEquipo_Temp= new ArrayList(); 
        
        //Procedemos a cerrar la ventana
        InternalFrame_AsignacionEquipos.show(false);
        
        /*removeAll();
        revalidate();
        repaint();*/
    }//GEN-LAST:event_cargarEquiposAsignadosActionPerformed

    private void tabla_ListadoAsignacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ListadoAsignacionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_ListadoAsignacionMouseClicked

    private void EliminarAsignacionTemporal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarAsignacionTemporal1ActionPerformed
        int fila1;
        try{
            fila1=tabla_ListadoAsignacionTemp.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ningún registro");
            }
            else{
                int opcion = JOptionPane.showConfirmDialog(null, "Esta seguro que desea quitar este registro?","Advertencia", JOptionPane.INFORMATION_MESSAGE);
                if(opcion ==0){//Procedemos a eliminar el registro
                    listadoAsignacionEquipo_Temp.remove(fila1);
                    DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"C.O","Equipo_Código","Equipo_Tipo", "Equiupo_Marca","Equipo_Modelo","Equipo_Descripción","Fecha Inicio","Fecha Finalización", "Horas Programadas"});      
                    for (AsignacionEquipo AsignacionEquipo1 : listadoAsignacionEquipo_Temp) {
                        String[] registro = new String[9];
                        registro[0] = "" + AsignacionEquipo1.getCentroOperacion().getDescripcion();
                        registro[1] = "" + AsignacionEquipo1.getEquipo().getTipoEquipo().getDescripcion();
                        registro[2] = "" + AsignacionEquipo1.getEquipo().getCodigo();
                        registro[3] = "" + AsignacionEquipo1.getEquipo().getMarca();
                        registro[4] = "" + AsignacionEquipo1.getEquipo().getModelo();
                        registro[5] = "" + AsignacionEquipo1.getEquipo().getDescripcion();
                        registro[6] = "" + AsignacionEquipo1.getFechaHoraInicio();
                        registro[7] = "" + AsignacionEquipo1.getFechaHoraFin();   
                        try{
                            //Calculamos la cantidad de Horas a partir de cierta cantidad de minutos
                            String minutoCantS ="";
                            int minutoCant=(Integer.parseInt(AsignacionEquipo1.getCantidadMinutosProgramados()) % 60);
                            int horasCant=(Integer.parseInt(AsignacionEquipo1.getCantidadMinutosProgramados())/60);
                            if(minutoCant<9){
                                minutoCantS="0"+minutoCant;
                            }else{
                                minutoCantS=""+minutoCant;
                            }
                            registro[8] = "" + horasCant+":"+minutoCantS;
                        }catch(Exception e){
                            registro[8] ="";
                        }
                        //registro[7] = "" + AsignacionEquipo1.getCantidadMinutosProgramados();
                        
                        modelo.addRow(registro);      
                    }
                    tabla_ListadoAsignacionTemp.setModel(modelo);
                } 
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_EliminarAsignacionTemporal1ActionPerformed

    private void EliminarAsignacion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarAsignacion1ActionPerformed
        int fila1;
        try{
            fila1=tabla_ListadoAsignacion.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ningún registro");
            }
            else{
                int opcion = JOptionPane.showConfirmDialog(null, "Esta seguro que desea quitar este registro?","Advertencia", JOptionPane.INFORMATION_MESSAGE);
                if(opcion ==0){//Procedemos a eliminar el registro
                    listadoAsignacionEquipo.remove(fila1);
                    DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"C.O","Equipo_Código","Equipo_Tipo", "Equiupo_Marca","Equipo_Modelo","Equipo_Descripción","Fecha Inicio","Fecha Finalización", "Horas Programadas"});      
                    for (AsignacionEquipo AsignacionEquipo1 : listadoAsignacionEquipo) {
                        String[] registro = new String[9];
                        registro[0] = "" + AsignacionEquipo1.getCentroOperacion().getDescripcion();
                        registro[1] = "" + AsignacionEquipo1.getEquipo().getTipoEquipo().getDescripcion();
                        registro[2] = "" + AsignacionEquipo1.getEquipo().getCodigo();
                        registro[3] = "" + AsignacionEquipo1.getEquipo().getMarca();
                        registro[4] = "" + AsignacionEquipo1.getEquipo().getModelo();
                        registro[5] = "" + AsignacionEquipo1.getEquipo().getDescripcion();
                        registro[6] = "" + AsignacionEquipo1.getFechaHoraInicio();
                        registro[7] = "" + AsignacionEquipo1.getFechaHoraFin();   
                        try{
                            //Calculamos la cantidad de Horas a partir de cierta cantidad de minutos
                            String minutoCantS ="";
                            int minutoCant=(Integer.parseInt(AsignacionEquipo1.getCantidadMinutosProgramados()) % 60);
                            int horasCant=(Integer.parseInt(AsignacionEquipo1.getCantidadMinutosProgramados())/60);
                            if(minutoCant<9){
                                minutoCantS="0"+minutoCant;
                            }else{
                                minutoCantS=""+minutoCant;
                            }
                            registro[8] = "" + horasCant+":"+minutoCantS;
                        }catch(Exception e){
                            registro[8] ="";
                        }
                        //registro[7] = "" + AsignacionEquipo1.getCantidadMinutosProgramados();
                        modelo.addRow(registro);      
                    }
                    tabla_ListadoAsignacion.setModel(modelo);
                } 
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_EliminarAsignacion1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        boolean validar = false;
        EstadoSolicitudEquipos estadoSolicitudEquipo=listadoEstadoSolicitud.get(estadoSolicitud.getSelectedIndex());
        if(listadoAsignacionEquipo.size() >= 1 || estadoSolicitudEquipo.getCodigo().equals("5")){
            validar=true;
        }
        if(validar){
            try {
               
                if(estadoSolicitudEquipo.getCodigo().equals("5")){//La solicitud de Equipo fue cancelada por tal motivo no se registra asignacion y se procede a cambar a estado de cancelada
                    //Cambiamos el estado de la solicitud de equipos a CANCELADA
                        int retornoCambioEstadoSolicitudEquipo=new ControlDB_SolicitudEquipo(tipoConexion).solicitudEquipo_ActualizarEstado_General(solicitudEquipo, user, 5, "CANCELADA");
                        if(retornoCambioEstadoSolicitudEquipo==1){
                            //Mandamos el correo de la signación
                            try {
                                new mail(tipoConexion).AsignacionSolicitudEquipos(user, solicitudEquipo.getUsuarioRealizaSolicitud(), listadoAsignacionEquipo, "CANCELADA");
                            } catch (SQLException ex) {
                                Logger.getLogger(AsignacionEquipo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            JOptionPane.showMessageDialog(null, "Se registro la cancelación de forma exitosa","Advertencia",JOptionPane.INFORMATION_MESSAGE);
                            removeAll();
                            revalidate();
                            repaint();
                            //solicitudEquipo=tiene los datos de la solicitud (Quien la realiza)
                            //user= tiene los datos de quien asignado  (usuario logueado)
                            //listadoAsignacionEquipo= contiene los equipos asignados a la solicitud
                            
                        }else{
                            JOptionPane.showMessageDialog(null, "No se pudo cancelar la solicitud de Equipos, verifique datos","Advertencia",JOptionPane.INFORMATION_MESSAGE);
                        }
                }else{
                    int retorno=new ControlDB_AsignacionEquipo(tipoConexion).registrarAsignacion(listadoAsignacionEquipo, user);
                    if(retorno==1){
                        //Cambiamos el estado de la solicitud
                        if(estadoSolicitudEquipo.getCodigo().equals("3")){//Solicitud Aprobada
                            new ControlDB_SolicitudEquipo(tipoConexion).solicitudEquipo_ActualizarEstado_General(solicitudEquipo, user, 3, "APROBADA");
                            //Mandamos el correo de la signación
                            try {
                                new mail(tipoConexion).AsignacionSolicitudEquipos(user, solicitudEquipo.getUsuarioRealizaSolicitud(), listadoAsignacionEquipo, "APROBADA");
                            } catch (SQLException ex) {
                                Logger.getLogger(AsignacionEquipo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }else{
                            if(estadoSolicitudEquipo.getCodigo().equals("4")){//Solicitud Aprobada
                                new ControlDB_SolicitudEquipo(tipoConexion).solicitudEquipo_ActualizarEstado_General(solicitudEquipo, user, 4, "APROBADA CON MODIFICACIÓN");
                                //Mandamos el correo de la signación
                                try {
                                    new mail(tipoConexion).AsignacionSolicitudEquipos(user, solicitudEquipo.getUsuarioRealizaSolicitud(), listadoAsignacionEquipo, "APROBADA CON MODIFICACIÓN");
                                } catch (SQLException ex) {
                                    Logger.getLogger(AsignacionEquipo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Se registro la asignación de forma exitosa","Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                        removeAll();
                        revalidate();
                        repaint();
                    }else{
                        JOptionPane.showMessageDialog(null, "No se pude registrar la asignación verifique datos","Errores al registrar", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AsignacionEquipo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(AsignacionEquipo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SocketException ex) {
                Logger.getLogger(AsignacionEquipo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Error! Debe agregar al menos un equipo asignado","Advertencia",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void titulo59MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titulo59MouseClicked
        InternaFrameCronograma.show(true);
    }//GEN-LAST:event_titulo59MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu Cargar;
    private javax.swing.JMenuItem Cargar1;
    private javax.swing.JPopupMenu EliminarAsignacion;
    private javax.swing.JMenuItem EliminarAsignacion1;
    private javax.swing.JPopupMenu EliminarAsignacionTemporal;
    private javax.swing.JMenuItem EliminarAsignacionTemporal1;
    private javax.swing.JInternalFrame InternaFrameCronograma;
    private javax.swing.JInternalFrame InternalFrameSelectorCampos;
    private javax.swing.JInternalFrame InternalFrame_AsignacionEquipos;
    private javax.swing.JInternalFrame InternalFrame_SolicitudesEquipos;
    private javax.swing.JPopupMenu SeleccionarSolicitud;
    private javax.swing.JLabel asignacion_Auxiliar;
    private javax.swing.JLabel asignacion_cantidadEquipo;
    private javax.swing.JLabel asignacion_compañia;
    private javax.swing.JLabel asignacion_fechaHoraFin;
    private javax.swing.JLabel asignacion_fechaHoraInicio;
    private javax.swing.JLabel asignacion_laborRealizada;
    private javax.swing.JLabel asignacion_marcaEquipo;
    private javax.swing.JLabel asignacion_modeloEquipo;
    private javax.swing.JLabel asignacion_motonave;
    private javax.swing.JLabel asignacion_subcentroCosto;
    private javax.swing.JLabel asignacion_tipoEquipo;
    private javax.swing.JButton cargarEquiposAsignados;
    private javax.swing.JRadioButton checkCentroOperacion;
    private javax.swing.JRadioButton checkFechaSolicitud;
    private javax.swing.JRadioButton checkUsuario;
    private javax.swing.JTextArea equiposSugeridos;
    private javax.swing.JComboBox<String> estadoSolicitud;
    private com.toedter.calendar.JDateChooser fechaFin;
    private com.toedter.calendar.JDateChooser fechaFinAsignacion;
    private com.toedter.calendar.JDateChooser fechaInicio;
    private com.toedter.calendar.JDateChooser fechaInicioAsignacion;
    private javax.swing.JComboBox<String> horaFin;
    private javax.swing.JComboBox<String> horaFinAsignacion;
    private javax.swing.JComboBox<String> horaInicio;
    private javax.swing.JComboBox<String> horaInicioAsignacion;
    private javax.swing.JLabel horarioTiempoIFinalSolicitudEquiposRegistrar;
    private javax.swing.JLabel horarioTiempoIFinalSolicitudEquiposRegistrar1;
    private javax.swing.JLabel horarioTiempoInicioSolicitudEquiposRegistrar;
    private javax.swing.JLabel horarioTiempoInicioSolicitudEquiposRegistrar1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JComboBox<String> minutoFin;
    private javax.swing.JComboBox<String> minutoFinAsignacion;
    private javax.swing.JComboBox<String> minutoInicio;
    private javax.swing.JComboBox<String> minutoInicioAsignacion;
    private javax.swing.JScrollPane panelCronograma;
    private javax.swing.JButton registrar;
    private javax.swing.JRadioButton selectActividadOperacion_codigo;
    private javax.swing.JRadioButton selectActividadOperacion_estado;
    private javax.swing.JRadioButton selectActividadOperacion_nombre;
    private javax.swing.JRadioButton selectAll;
    private javax.swing.JRadioButton selectCentroCostoAuxiliar_codigo;
    private javax.swing.JRadioButton selectCentroCostoAuxiliar_estado;
    private javax.swing.JRadioButton selectCentroCostoAuxiliar_nombre;
    private javax.swing.JRadioButton selectCentroCostoSubcentro_codigo;
    private javax.swing.JRadioButton selectCentroCostoSubcentro_estado;
    private javax.swing.JRadioButton selectCentroCostoSubcentro_nombre;
    private javax.swing.JComboBox<String> selectCentroOperacion;
    private javax.swing.JRadioButton selectCompañia_codigo;
    private javax.swing.JRadioButton selectCompañia_estado;
    private javax.swing.JRadioButton selectCompañia_nombre;
    private javax.swing.JComboBox<String> selectEquipos;
    private javax.swing.JComboBox<String> selectMarca;
    private javax.swing.JComboBox<String> selectModelo;
    private javax.swing.JRadioButton selectMotonave_codigo;
    private javax.swing.JRadioButton selectMotonave_estado;
    private javax.swing.JRadioButton selectMotonave_nombre;
    private javax.swing.JMenuItem selectSolicitud;
    private javax.swing.JRadioButton selectSolicitudEquipo_cantidad;
    private javax.swing.JRadioButton selectSolicitudEquipo_cantidadHoras;
    private javax.swing.JRadioButton selectSolicitudEquipo_codigo;
    private javax.swing.JRadioButton selectSolicitudEquipo_fechaFinalizacion;
    private javax.swing.JRadioButton selectSolicitudEquipo_fechaInicio;
    private javax.swing.JRadioButton selectSolicitudEquipo_marca;
    private javax.swing.JRadioButton selectSolicitudEquipo_modelo;
    private javax.swing.JRadioButton selectSolicitudEquipo_observacion;
    private javax.swing.JComboBox<String> selectTipo;
    private javax.swing.JRadioButton selectTipoEquipo_codigo;
    private javax.swing.JRadioButton selectTipoEquipo_estado;
    private javax.swing.JRadioButton selectTipoEquipo_nombre;
    private javax.swing.JComboBox<String> selectUsuario;
    private javax.swing.JLabel solicitud_centroOperacion;
    private javax.swing.JLabel solicitud_codigo;
    private javax.swing.JLabel solicitud_estado;
    private javax.swing.JLabel solicitud_fecha;
    private javax.swing.JLabel solicitud_usuarioCedula;
    private javax.swing.JLabel solicitud_usuarioCorreo;
    private javax.swing.JLabel solicitud_usuarioNombre;
    private javax.swing.JTable tabla_ListadoAsignacion;
    private javax.swing.JTable tabla_ListadoAsignacionTemp;
    private javax.swing.JTable tabla_ListadoEquiposSolicitud;
    private javax.swing.JTable tabla_ListadoSolicitudes;
    private javax.swing.JLabel titulo13;
    private javax.swing.JLabel titulo15;
    private javax.swing.JLabel titulo18;
    private javax.swing.JLabel titulo2;
    private javax.swing.JLabel titulo23;
    private javax.swing.JLabel titulo24;
    private javax.swing.JLabel titulo25;
    private javax.swing.JLabel titulo26;
    private javax.swing.JLabel titulo27;
    private javax.swing.JLabel titulo28;
    private javax.swing.JLabel titulo31;
    private javax.swing.JLabel titulo32;
    private javax.swing.JLabel titulo33;
    private javax.swing.JLabel titulo34;
    private javax.swing.JLabel titulo35;
    private javax.swing.JLabel titulo36;
    private javax.swing.JLabel titulo37;
    private javax.swing.JLabel titulo38;
    private javax.swing.JLabel titulo39;
    private javax.swing.JLabel titulo40;
    private javax.swing.JLabel titulo41;
    private javax.swing.JLabel titulo42;
    private javax.swing.JLabel titulo43;
    private javax.swing.JLabel titulo44;
    private javax.swing.JLabel titulo45;
    private javax.swing.JLabel titulo46;
    private javax.swing.JLabel titulo47;
    private javax.swing.JLabel titulo48;
    private javax.swing.JLabel titulo49;
    private javax.swing.JLabel titulo50;
    private javax.swing.JLabel titulo51;
    private javax.swing.JLabel titulo52;
    private javax.swing.JLabel titulo53;
    private javax.swing.JLabel titulo54;
    private javax.swing.JLabel titulo55;
    private javax.swing.JLabel titulo56;
    private javax.swing.JLabel titulo57;
    private javax.swing.JLabel titulo58;
    private javax.swing.JLabel titulo59;
    private javax.swing.JLabel titulo6;
    private javax.swing.JLabel titulo60;
    private javax.swing.JLabel titulo61;
    private javax.swing.JLabel titulo62;
    private javax.swing.JLabel titulo63;
    private javax.swing.JLabel titulo64;
    private javax.swing.JLabel titulo65;
    private javax.swing.JLabel titulo66;
    private javax.swing.JLabel titulo67;
    private javax.swing.JLabel titulo7;
    private javax.swing.JLabel titulo9;
    // End of variables declaration//GEN-END:variables
    
    public void tabla_Listar() throws SQLException{
        String Stitulo="";
        int contador=0;
        if(selectSolicitudEquipo_codigo.isSelected()){
            Stitulo +="Código"+":";
            contador++;
        }
        if(selectTipoEquipo_codigo.isSelected()){
            Stitulo +="TipoEquipo Código"+":";
            contador++;
        }
        if(selectTipoEquipo_nombre.isSelected()){
            Stitulo +="TipoEquipo Nombre"+":";
            contador++;
        }
        if(selectSolicitudEquipo_marca.isSelected()){
            Stitulo +="Marca"+":";
            contador++;
        }
        if(selectSolicitudEquipo_modelo.isSelected()){
            Stitulo +="Modelo"+":";
            contador++;
        }
        if(selectSolicitudEquipo_cantidad.isSelected()){
            Stitulo +="Cantidad"+":";
            contador++;
        }
        if(selectSolicitudEquipo_observacion.isSelected()){
            Stitulo +="Observación"+":";
            contador++;
        }
        if(selectSolicitudEquipo_fechaInicio.isSelected()){
            Stitulo +="Fecha Inicio"+":";
            contador++;
        }
        if(selectSolicitudEquipo_fechaFinalizacion.isSelected()){
            Stitulo +="Fecha Final"+":";
            contador++;
        }
        if(selectSolicitudEquipo_cantidadHoras.isSelected()){
            Stitulo +="Cantidad Horas"+":";
            contador++;
        }
        if(selectCentroCostoAuxiliar_codigo.isSelected()){
            Stitulo +="C.C. Auxiliar Codigo."+":";
            contador++;
        }
        if(selectCentroCostoAuxiliar_nombre.isSelected()){
            Stitulo +="C.C. Auxiliar Nombre"+":";
            contador++;
        }
        if(selectCentroCostoAuxiliar_estado.isSelected()){
            Stitulo +="C.C. Auxiliar Estado"+":";
            contador++;
        }
        if(selectCentroCostoSubcentro_codigo.isSelected()){
            Stitulo +="SubcentroCosto Código"+":";
            contador++;
        }
        if(selectCentroCostoSubcentro_nombre.isSelected()){
            Stitulo +="SubcentroCosto Nombre"+":";
            contador++;
        }
        if(selectCentroCostoSubcentro_estado.isSelected()){
            Stitulo +="SubcentroCosto Estado"+":";
            contador++;
        }
        
        if(selectTipoEquipo_estado.isSelected()){
            Stitulo +="TipoEquipo Estado"+":";
            contador++;
        }
        if(selectActividadOperacion_codigo.isSelected()){
            Stitulo +="Actividad Código"+":";
            contador++;
        }
        if(selectActividadOperacion_nombre.isSelected()){
            Stitulo +="Actividad Nombre"+":";
            contador++;
        }
        if(selectActividadOperacion_estado.isSelected()){
            Stitulo +="Actividad Estado"+":";
            contador++;
        }
        if(selectMotonave_codigo.isSelected()){
            Stitulo +="Motonave_Código"+":";
            contador++;
        }
        if(selectMotonave_nombre.isSelected()){
            Stitulo +="Motonave_Nombre"+":";
            contador++;
        }
        if(selectMotonave_estado.isSelected()){
            Stitulo +="Motonave_Estado"+":";
            contador++;
        }
        if(selectCompañia_codigo.isSelected()){
            Stitulo +="Compañia_Código"+":";
            contador++;
        }
        if(selectCompañia_nombre.isSelected()){
            Stitulo +="Compañia_Nombre"+":";
            contador++;
        }
        if(selectCompañia_estado.isSelected()){
           // Stitulo +="Compañia_Estado"+":";
            Stitulo +="Compañia_Estado";
            contador++;
        }
       
        DefaultTableModel modelo = new DefaultTableModel(null, Stitulo.split(":")); 
        int contadorw=0;
        
        for (SolicitudListadoEquipo listado1 : solicitudEquipo.getListadoSolicitudesEquipos()) {
            String[] registro = new String[contador];
            int contadorRegistro=0;
            if(selectSolicitudEquipo_codigo.isSelected()){
                registro[contadorRegistro] = "" +(Object)listado1.getCodigo();
                contadorRegistro++;
            }
             if(selectTipoEquipo_codigo.isSelected()){
                registro[contadorRegistro] = "" + listado1.getTipoEquipo().getCodigo();
                contadorRegistro++;
            }
            if(selectTipoEquipo_nombre.isSelected()){
                registro[contadorRegistro] = "" + listado1.getTipoEquipo().getDescripcion();
                contadorRegistro++;
            }
            if(selectSolicitudEquipo_marca.isSelected()){
                registro[contadorRegistro] = "" + listado1.getMarcaEquipo();
                contadorRegistro++; 
            }
            if(selectSolicitudEquipo_modelo.isSelected()){
                registro[contadorRegistro] = "" + listado1.getModeloEquipo();
                contadorRegistro++;
            }
            if(selectSolicitudEquipo_cantidad.isSelected()){
                registro[contadorRegistro] = "" + listado1.getCantidad();
                contadorRegistro++;
            }
            if(selectSolicitudEquipo_observacion.isSelected()){
                registro[contadorRegistro] = "" + listado1.getObservacacion();
                contadorRegistro++;
            }
            if(selectSolicitudEquipo_fechaInicio.isSelected()){
                registro[contadorRegistro] =""+listado1.getFechaHoraInicio();
                contadorRegistro++;
            }
            if(selectSolicitudEquipo_fechaFinalizacion.isSelected()){
                registro[contadorRegistro] =""+listado1.getFechaHoraFin();
                contadorRegistro++;
            }
            if(selectSolicitudEquipo_cantidadHoras.isSelected()){
               try{
                    //Calculamos la cantidad de Horas a partir de cierta cantidad de minutos
                    String minutoCantS ="";
                    int minutoCant=(listado1.getCantidadMinutos() % 60);
                    int horasCant=(listado1.getCantidadMinutos()/60);
                    if(minutoCant<9){
                        minutoCantS="0"+minutoCant;
                    }else{
                        minutoCantS=""+minutoCant;
                    }
                    registro[contadorRegistro] = "" + horasCant+":"+minutoCantS;
                }catch(Exception e){
                    registro[contadorRegistro] =""+listado1.getCantidadMinutos();
                } 
                //registro[contadorRegistro] =""+listado1.getCantidadMinutos();
                contadorRegistro++;
            }
            if(selectCentroCostoAuxiliar_codigo.isSelected()){
                registro[contadorRegistro] = "" + listado1.getCentroCostoAuxiliar().getCodigo();
                contadorRegistro++;
            }
            if(selectCentroCostoAuxiliar_nombre.isSelected()){
                registro[contadorRegistro] = "" + listado1.getCentroCostoAuxiliar().getDescripcion();
                contadorRegistro++;
            }
            if(selectCentroCostoAuxiliar_estado.isSelected()){
                registro[contadorRegistro] = "" + listado1.getCentroCostoAuxiliar().getEstado();
                contadorRegistro++;
            }
            if(selectCentroCostoSubcentro_codigo.isSelected()){
                registro[contadorRegistro] = "" + listado1.getCentroCostoAuxiliar().getCentroCostoSubCentro().getCodigo();
                contadorRegistro++;
            }
            if(selectCentroCostoSubcentro_nombre.isSelected()){
                registro[contadorRegistro] = "" + listado1.getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                contadorRegistro++;
            }
            if(selectCentroCostoSubcentro_estado.isSelected()){
                registro[contadorRegistro] = "" + listado1.getCentroCostoAuxiliar().getCentroCostoSubCentro().getEstado();
                contadorRegistro++;
            }
           
            if(selectTipoEquipo_estado.isSelected()){
                registro[contadorRegistro] = "" + listado1.getTipoEquipo().getEstado();
                contadorRegistro++;
            }
            if(selectActividadOperacion_codigo.isSelected()){
                registro[contadorRegistro] = "" + listado1.getLaborRealizada().getCodigo();
                contadorRegistro++;
            }
            if(selectActividadOperacion_nombre.isSelected()){
                registro[contadorRegistro] = "" + listado1.getLaborRealizada().getDescripcion();
                contadorRegistro++;
            }
            if(selectActividadOperacion_estado.isSelected()){
                registro[contadorRegistro] = "" + listado1.getLaborRealizada().getEstado();
                contadorRegistro++;
            }
            if(selectMotonave_codigo.isSelected()){
                registro[contadorRegistro] = "" + listado1.getMotonave().getCodigo();
                contadorRegistro++;
            }
            if(selectMotonave_nombre.isSelected()){
                registro[contadorRegistro] = "" + listado1.getMotonave().getDescripcion();
                contadorRegistro++;
            }
            if(selectMotonave_estado.isSelected()){
                registro[contadorRegistro] = "" + listado1.getMotonave().getEstado();
                contadorRegistro++;
            }
            if(selectCompañia_codigo.isSelected()){
                registro[contadorRegistro] = "" + listado1.getCompañia().getCodigo();
                contadorRegistro++;
            }
            if(selectCompañia_nombre.isSelected()){
                registro[contadorRegistro] = "" + listado1.getCompañia().getDescripcion();
                contadorRegistro++;
            }
            if(selectCompañia_estado.isSelected()){
                registro[contadorRegistro] = "" + listado1.getCompañia().getEstado();
                contadorRegistro++;
            }
            modelo.addRow(registro);      
        }
        tabla_ListadoEquiposSolicitud.setModel(modelo);
        //resizeColumnWidth(tabla_ListadoEquiposSolicitud);
    }
    public void selectorCampoPorDefecto(){
        selectSolicitudEquipo_codigo.setSelected(false);
        selectSolicitudEquipo_marca.setSelected(true);
        selectSolicitudEquipo_modelo.setSelected(true);
        selectSolicitudEquipo_cantidad.setSelected(true);
        selectSolicitudEquipo_observacion.setSelected(false);
        selectSolicitudEquipo_fechaInicio.setSelected(true);
        selectSolicitudEquipo_fechaFinalizacion.setSelected(true);
        selectSolicitudEquipo_cantidadHoras.setSelected(true);
        selectCentroCostoAuxiliar_codigo.setSelected(false);
        selectCentroCostoAuxiliar_nombre.setSelected(false);
        selectCentroCostoAuxiliar_estado.setSelected(false);
        selectCentroCostoSubcentro_codigo.setSelected(false);
        selectCentroCostoSubcentro_nombre.setSelected(false);
        selectCentroCostoSubcentro_estado.setSelected(false);
        selectTipoEquipo_codigo.setSelected(false);
        selectTipoEquipo_nombre.setSelected(true);
        selectTipoEquipo_estado.setSelected(false);
        selectActividadOperacion_codigo.setSelected(false);
        selectActividadOperacion_nombre.setSelected(true);  
        selectActividadOperacion_estado.setSelected(false);
        selectMotonave_codigo.setSelected(false);
        selectMotonave_nombre.setSelected(true);
        selectMotonave_estado.setSelected(false);
        selectCompañia_codigo.setSelected(false);
        selectCompañia_nombre.setSelected(false);
        selectCompañia_estado.setSelected(false); 
    }
    public void selectorCampoTodos(){
        selectSolicitudEquipo_codigo.setSelected(true);
        selectSolicitudEquipo_marca.setSelected(true);
        selectSolicitudEquipo_modelo.setSelected(true);
        selectSolicitudEquipo_cantidad.setSelected(true);
        selectSolicitudEquipo_observacion.setSelected(true);
        selectSolicitudEquipo_fechaInicio.setSelected(true);
        selectSolicitudEquipo_fechaFinalizacion.setSelected(true);
        selectSolicitudEquipo_cantidadHoras.setSelected(true);
        selectCentroCostoAuxiliar_codigo.setSelected(true);
        selectCentroCostoAuxiliar_nombre.setSelected(true);
        selectCentroCostoAuxiliar_estado.setSelected(true);
        selectCentroCostoSubcentro_codigo.setSelected(true);
        selectCentroCostoSubcentro_nombre.setSelected(true);
        selectCentroCostoSubcentro_estado.setSelected(true);
        selectTipoEquipo_codigo.setSelected(true);
        selectTipoEquipo_nombre.setSelected(true);
        selectTipoEquipo_estado.setSelected(true);
        selectActividadOperacion_codigo.setSelected(true);
        selectActividadOperacion_nombre.setSelected(true);  
        selectActividadOperacion_estado.setSelected(true);
        selectMotonave_codigo.setSelected(true);
        selectMotonave_nombre.setSelected(true);
        selectMotonave_estado.setSelected(true);
        selectCompañia_codigo.setSelected(true);
        selectCompañia_nombre.setSelected(true);
        selectCompañia_estado.setSelected(true); 
    }
    public void selectorCampoNinguno(){
        selectSolicitudEquipo_codigo.setSelected(false);
        selectSolicitudEquipo_marca.setSelected(false);
        selectSolicitudEquipo_modelo.setSelected(false);
        selectSolicitudEquipo_cantidad.setSelected(false);
        selectSolicitudEquipo_observacion.setSelected(false);
        selectSolicitudEquipo_fechaInicio.setSelected(false);
        selectSolicitudEquipo_fechaFinalizacion.setSelected(false);
        selectSolicitudEquipo_cantidadHoras.setSelected(false);
        selectCentroCostoAuxiliar_codigo.setSelected(false);
        selectCentroCostoAuxiliar_nombre.setSelected(false);
        selectCentroCostoAuxiliar_estado.setSelected(false);
        selectCentroCostoSubcentro_codigo.setSelected(false);
        selectCentroCostoSubcentro_nombre.setSelected(false);
        selectCentroCostoSubcentro_estado.setSelected(false);
        selectTipoEquipo_codigo.setSelected(false);
        selectTipoEquipo_nombre.setSelected(false);
        selectTipoEquipo_estado.setSelected(false);
        selectActividadOperacion_codigo.setSelected(false);
        selectActividadOperacion_nombre.setSelected(false);  
        selectActividadOperacion_estado.setSelected(false);
        selectMotonave_codigo.setSelected(false);
        selectMotonave_nombre.setSelected(false);
        selectMotonave_estado.setSelected(false);
        selectCompañia_codigo.setSelected(false);
        selectCompañia_nombre.setSelected(false);
        selectCompañia_estado.setSelected(false);  
    }    
    public boolean validarCargueEquiposInternos(Equipo equipo){
        /**
         * Metodo con funcionalidad para validar si un equipo ya fue cargado en la asignación para no volverlo a cargar
         * Retorno: True=El equipo ya fue cargado           False=El equipo no ha sido cargado
        **/
        boolean retorno= false;
        for(AsignacionEquipo AsignacionEquipo1: listadoAsignacionEquipo_Temp){
            if(AsignacionEquipo1.getEquipo().getCodigo().equals(equipo.getCodigo())){
                retorno=true;
            } 
        }
        for(AsignacionEquipo AsignacionEquipo1: listadoAsignacionEquipo){
            if(AsignacionEquipo1.getEquipo().getCodigo().equals(equipo.getCodigo())){
                retorno=true;
            } 
        }
        return retorno;
    }
    public void viewFechas(boolean valor){
        jLabel8.show(valor);
        fechaInicio.show(valor);
        jLabel7.show(valor);
        horaInicio.show(valor);
        jLabel25.show(valor);
        minutoInicio.show(valor);
        horarioTiempoInicioSolicitudEquiposRegistrar.show(valor);

        jLabel9.show(valor);
        fechaFin.show(valor);
        jLabel12.show(valor);
        horaFin.show(valor);
        jLabel24.show(valor);
        minutoFin.show(valor);
        horarioTiempoIFinalSolicitudEquiposRegistrar.show(valor);
    }
    //Ajustar aNcho de las tablas de acuerdo al contenido
    /*public void resizeColumnWidth(JTable table) {
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
    }*/
}
