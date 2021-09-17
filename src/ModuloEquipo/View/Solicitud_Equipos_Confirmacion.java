package ModuloEquipo.View2;

import Catalogo.Controller.ControlDB_CentroOperacion;
import Catalogo.Model.CentroOperacion;
import Correos.mail;
import ModuloEquipo.Controller2.ControlDB_AsignacionEquipo;
import ModuloEquipo.Controller2.ControlDB_SolicitudEquipo;
import ModuloEquipo.Model.AsignacionEquipo;
import ModuloEquipo.Model.ConfirmacionSolicitudEquipos;
import ModuloEquipo.Model.SolicitudEquipo;
import ModuloEquipo.Model.SolicitudListadoEquipo;
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Solicitud_Equipos_Confirmacion extends javax.swing.JPanel {
    
    private ArrayList<CentroOperacion> listadoCentroOperacion = new ArrayList();
    private ArrayList<SolicitudEquipo> listadoSolicitudEquipo = new ArrayList();
    private ArrayList<Usuario> listadoUsuario = new ArrayList();
    private Usuario user;
    SolicitudEquipo solicitudEquipo= new SolicitudEquipo();
    SolicitudListadoEquipo solicitudListadoEquipo = new SolicitudListadoEquipo();
    int valorSelect_SolicitudListadoEquipo=-1;
    //Asignacion
    private ArrayList<AsignacionEquipo> listadoAsignacionEquipo = new ArrayList();
    private ArrayList<ConfirmacionSolicitudEquipos> listadoEstadoConfirmacion= new ArrayList();
    private String tipoConexion;
    
    public Solicitud_Equipos_Confirmacion(Usuario us, String tipoConexion) throws SQLException {
        initComponents(); 
        this.tipoConexion= tipoConexion;
        
        InternalFrame_SolicitudesEquipos.getContentPane().setBackground(Color.WHITE);
        InternalFrameSelectorCampos.getContentPane().setBackground(Color.WHITE);
        
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
        selectorCampoPorDefecto();
        //Ocultamos la ventana de consulta de solicitudes
        InternalFrame_SolicitudesEquipos.show(false);
        InternalFrameSelectorCampos.show(false);
        
        //Cargamos en la interfaz los estados de Solicitudes de Equipos
        listadoEstadoConfirmacion=new ControlDB_SolicitudEquipo(tipoConexion).buscarConfirmaciones();
        if(listadoEstadoConfirmacion != null){
            for(ConfirmacionSolicitudEquipos ConfirmacionSolicitudEquipos1 : listadoEstadoConfirmacion ){
                estadoConfirmacion.addItem(ConfirmacionSolicitudEquipos1.getDescripcion());
            }
        } 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Cargar = new javax.swing.JPopupMenu();
        Cargar1 = new javax.swing.JMenuItem();
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
        estadoConfirmacion = new javax.swing.JComboBox<>();

        Cargar1.setText("Cargar");
        Cargar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cargar1ActionPerformed(evt);
            }
        });
        Cargar.add(Cargar1);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InternalFrame_SolicitudesEquipos.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrame_SolicitudesEquipos.setClosable(true);
        InternalFrame_SolicitudesEquipos.setVisible(false);
        InternalFrame_SolicitudesEquipos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo34.setForeground(new java.awt.Color(102, 102, 102));
        titulo34.setText("CONSULTA DE SOLICITUDES");
        InternalFrame_SolicitudesEquipos.getContentPane().add(titulo34, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 230, 30));

        checkFechaSolicitud.setBackground(new java.awt.Color(255, 255, 255));
        checkFechaSolicitud.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        checkFechaSolicitud.setForeground(new java.awt.Color(51, 51, 51));
        checkFechaSolicitud.setText("Fecha Solicitud:");
        checkFechaSolicitud.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        checkFechaSolicitud.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkFechaSolicitudItemStateChanged(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(checkFechaSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 130, 30));

        selectCentroOperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectCentroOperacionActionPerformed(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(selectCentroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 300, 30));

        checkCentroOperacion.setBackground(new java.awt.Color(255, 255, 255));
        checkCentroOperacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        checkCentroOperacion.setForeground(new java.awt.Color(51, 51, 51));
        checkCentroOperacion.setText("C.O");
        checkCentroOperacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        checkCentroOperacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkCentroOperacionItemStateChanged(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(checkCentroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 110, 30));

        fechaInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicioMouseEntered(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 170, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Hora");
        InternalFrame_SolicitudesEquipos.getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, -1, 30));

        horaInicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                horaInicioItemStateChanged(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(horaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 50, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText(":");
        InternalFrame_SolicitudesEquipos.getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 20, 30));

        minutoInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoInicioActionPerformed(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(minutoInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 50, 30));

        horarioTiempoInicioSolicitudEquiposRegistrar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoInicioSolicitudEquiposRegistrar.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoInicioSolicitudEquiposRegistrar.setText("AM");
        InternalFrame_SolicitudesEquipos.getContentPane().add(horarioTiempoInicioSolicitudEquiposRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 40, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Inicio:");
        InternalFrame_SolicitudesEquipos.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 60, 30));

        fechaFin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFinMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFinMouseEntered(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(fechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 70, 170, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Hora");
        InternalFrame_SolicitudesEquipos.getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 70, -1, 30));

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
        InternalFrame_SolicitudesEquipos.getContentPane().add(horaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 70, 50, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel24.setText(":");
        InternalFrame_SolicitudesEquipos.getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 70, 20, 30));

        minutoFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoFinActionPerformed(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(minutoFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 70, 50, 30));

        horarioTiempoIFinalSolicitudEquiposRegistrar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoIFinalSolicitudEquiposRegistrar.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoIFinalSolicitudEquiposRegistrar.setText("AM");
        InternalFrame_SolicitudesEquipos.getContentPane().add(horarioTiempoIFinalSolicitudEquiposRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 70, 50, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Fin:");
        InternalFrame_SolicitudesEquipos.getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, 40, 30));

        checkUsuario.setBackground(new java.awt.Color(255, 255, 255));
        checkUsuario.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        checkUsuario.setForeground(new java.awt.Color(51, 51, 51));
        checkUsuario.setText("Usuario Solicitud:");
        checkUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        checkUsuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkUsuarioItemStateChanged(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(checkUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, 110, 30));

        selectUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectUsuarioActionPerformed(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(selectUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, 300, 30));

        jButton2.setText("CONSULTAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        InternalFrame_SolicitudesEquipos.getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 130, 30));

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

        InternalFrame_SolicitudesEquipos.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 1070, 290));

        add(InternalFrame_SolicitudesEquipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 640));

        InternalFrameSelectorCampos.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrameSelectorCampos.setClosable(true);
        InternalFrameSelectorCampos.setVisible(false);
        InternalFrameSelectorCampos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        add(InternalFrameSelectorCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 360));

        titulo35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo35.setForeground(new java.awt.Color(102, 102, 102));
        titulo35.setText("ASIGNACIÓN EQUIPOS (CONFIRMACIÓN)");
        add(titulo35, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 330, 30));

        titulo36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo36.setForeground(new java.awt.Color(51, 51, 51));
        titulo36.setText("Usuario Correo:");
        add(titulo36, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, 160, 20));

        titulo28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo28.setForeground(new java.awt.Color(51, 51, 51));
        titulo28.setText("Centro Operación:");
        add(titulo28, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 130, 20));

        titulo37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo37.setForeground(new java.awt.Color(51, 51, 51));
        titulo37.setText("Confirmación Asignación:");
        add(titulo37, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, 170, 20));

        solicitud_estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        solicitud_estado.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_estado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(solicitud_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 210, 20));

        solicitud_fecha.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        solicitud_fecha.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_fecha.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(solicitud_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 210, 20));

        solicitud_centroOperacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        solicitud_centroOperacion.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_centroOperacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(solicitud_centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 210, 20));

        solicitud_codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        solicitud_codigo.setForeground(new java.awt.Color(51, 51, 51));
        solicitud_codigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(solicitud_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 210, 20));

        titulo39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo39.setForeground(new java.awt.Color(51, 51, 51));
        titulo39.setText("Usuario Cedula:");
        add(titulo39, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 160, 20));

        titulo40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo40.setForeground(new java.awt.Color(51, 51, 51));
        titulo40.setText("Usuario Nombre:");
        add(titulo40, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 160, 20));

        solicitud_usuarioCorreo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        solicitud_usuarioCorreo.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_usuarioCorreo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(solicitud_usuarioCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 410, 20));

        solicitud_usuarioCedula.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        solicitud_usuarioCedula.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_usuarioCedula.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(solicitud_usuarioCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 410, 20));

        solicitud_usuarioNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        solicitud_usuarioNombre.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_usuarioNombre.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(solicitud_usuarioNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, 410, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/agregar.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 70, 80, 60));

        titulo31.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        titulo31.setForeground(new java.awt.Color(51, 51, 51));
        titulo31.setText("Cargar");
        add(titulo31, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 120, 50, 30));

        titulo41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo41.setForeground(new java.awt.Color(102, 102, 102));
        titulo41.setText("Listado de Equipos Asignados:");
        add(titulo41, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 381, 220, -1));

        titulo42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo42.setForeground(new java.awt.Color(51, 51, 51));
        titulo42.setText("Fecha Elaboración:");
        add(titulo42, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 130, 20));

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
        tabla_ListadoEquiposSolicitud.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_ListadoEquiposSolicitudMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabla_ListadoEquiposSolicitud);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 1260, 180));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 1170, 10));

        titulo43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo43.setForeground(new java.awt.Color(51, 51, 51));
        titulo43.setText("Solicitud Equipos #");
        add(titulo43, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 130, 20));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCampo.png"))); // NOI18N
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, -1, -1));

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
        tabla_ListadoAsignacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_ListadoAsignacionMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tabla_ListadoAsignacion);

        add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 1260, 200));

        titulo32.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        titulo32.setForeground(new java.awt.Color(51, 51, 51));
        titulo32.setText("Solicitud Equipos");
        add(titulo32, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 130, 90, 30));

        jButton1.setText("REGISTRAR ASIGNACIÓN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 610, -1, 30));

        titulo61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo61.setForeground(new java.awt.Color(102, 102, 102));
        titulo61.setText("Datos de la asignación:");
        add(titulo61, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 220, 20));

        titulo62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo62.setForeground(new java.awt.Color(102, 102, 102));
        titulo62.setText("Listado de Equipos Solicitados:");
        add(titulo62, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 220, 20));

        titulo63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo63.setForeground(new java.awt.Color(51, 51, 51));
        titulo63.setText("Estado Solicitud:");
        add(titulo63, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 130, 20));

        estadoConfirmacion.setToolTipText("");
        add(estadoConfirmacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 610, 330, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void checkFechaSolicitudItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkFechaSolicitudItemStateChanged
       
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
            Logger.getLogger(Solicitud_Equipos_Confirmacion.class.getName()).log(Level.SEVERE, null, ex);
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
                        listadoAsignacionEquipo = new ArrayList<>();
                        listadoAsignacionEquipo=new ControlDB_AsignacionEquipo(tipoConexion).consultarAsignacion(solicitudEquipo);
                        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"C.O","Subcentro Costo","C.C. Aux.","Código","Tipo", "Marca","Modelo","Descripción","Fecha_Inicio","Fecha_Fin", "Horas Programadas"});      
                        for (AsignacionEquipo AsignacionEquipo1 : listadoAsignacionEquipo) {
                            String[] registro = new String[11];
                            registro[0] = "" + AsignacionEquipo1.getCentroOperacion().getDescripcion();
                            registro[1] = "" + AsignacionEquipo1.getSolicitudListadoEquipo().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                            registro[2] = "" + AsignacionEquipo1.getSolicitudListadoEquipo().getCentroCostoAuxiliar().getDescripcion();
                            registro[3] = "" + AsignacionEquipo1.getEquipo().getCodigo();
                            registro[4] = "" + AsignacionEquipo1.getEquipo().getTipoEquipo().getDescripcion();
                            registro[5] = "" + AsignacionEquipo1.getEquipo().getMarca();
                            registro[6] = "" + AsignacionEquipo1.getEquipo().getModelo();
                            registro[7] = "" + AsignacionEquipo1.getEquipo().getDescripcion();
                            registro[8] = "" + AsignacionEquipo1.getFechaHoraInicio();
                            registro[9] = "" + AsignacionEquipo1.getFechaHoraFin();
                            
                             try{
                                //Calculamos la cantidad de Horas a partir de cierta cantidad de minutos
                                String minutoCantS ="";
                                int minutoCant=(Integer.parseInt(AsignacionEquipo1.getCantidadMinutosProgramados()) % 60);
                                int horasCant=(Integer.parseInt(AsignacionEquipo1.getCantidadMinutosProgramados()) /60);
                                if(minutoCant<9){
                                    minutoCantS="0"+minutoCant;
                                }else{
                                    minutoCantS=""+minutoCant;
                                }
                                registro[10] = "" + horasCant+":"+minutoCantS;
                            }catch(Exception e){
                                registro[10] =""+AsignacionEquipo1.getCantidadMinutosProgramados();
                            } 
                            //registro[10] = "" + AsignacionEquipo1.getCantidadMinutosProgramados();
                            modelo.addRow(registro);      
                        }
                        tabla_ListadoAsignacion.setModel(modelo);
                    }catch(Exception e){
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "No se pudo cambiar el estado de la solicitud de Equipos a Revisión","Advertencia",JOptionPane.INFORMATION_MESSAGE);
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
            sql +=" WHERE ([ese_cdgo] = 3 OR [ese_cdgo] = 4) AND [se_confirmacion_solicitud_equipo_cdgo] IS NULL  ";
        }else{
            sql +=" AND ([ese_cdgo] = 3 OR [ese_cdgo] = 4) AND [se_confirmacion_solicitud_equipo_cdgo] IS NULL ";
        }
        try {
            DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"C.O","Fecha", "Usuario_Cedula","Usuario_Nombre","Fecha_Registro","Estado"});      
            listadoSolicitudEquipo=new ControlDB_SolicitudEquipo(tipoConexion).buscarSolicitudesPorUsuario(sql,user);
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
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Confirmacion.class.getName()).log(Level.SEVERE, null, ex);
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
                    listadoAsignacionEquipo = new ArrayList<>();
                    listadoAsignacionEquipo=new ControlDB_AsignacionEquipo(tipoConexion).consultarAsignacion(solicitudEquipo);
                    DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"C.O","Equipo_Tipo", "Equipo_Marca","Equipo_Modelo","Equipo_Descripción","Fecha_Inicio","Fecha_Final", "Horas Programadas"});      
                    for (AsignacionEquipo AsignacionEquipo1 : listadoAsignacionEquipo) {
                        String[] registro = new String[8];
                        registro[0] = "" + AsignacionEquipo1.getCentroOperacion().getDescripcion();
                        registro[1] = "" + AsignacionEquipo1.getEquipo().getTipoEquipo().getDescripcion();
                        registro[2] = "" + AsignacionEquipo1.getEquipo().getMarca();
                        registro[3] = "" + AsignacionEquipo1.getEquipo().getModelo();
                        registro[4] = "" + AsignacionEquipo1.getEquipo().getDescripcion();
                        registro[5] = "" + AsignacionEquipo1.getFechaHoraInicio();
                        registro[6] = "" + AsignacionEquipo1.getFechaHoraFin();
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
                            registro[7] = "" + horasCant+":"+minutoCantS;
                        }catch(Exception e){
                            registro[7] =  AsignacionEquipo1.getCantidadMinutosProgramados();
                        }
            
                        
                        modelo.addRow(registro);      
                    }
                    tabla_ListadoAsignacion.setModel(modelo);
                }catch(Exception e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "No se pudo cambiar el estado de la solicitud de Equipo a Revisión","Advertencia",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_Cargar1ActionPerformed

    private void tabla_ListadoEquiposSolicitudMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ListadoEquiposSolicitudMouseClicked
        
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

    private void tabla_ListadoAsignacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ListadoAsignacionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_ListadoAsignacionMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            ConfirmacionSolicitudEquipos confirmacionSolicitudEquipos1=listadoEstadoConfirmacion.get(estadoConfirmacion.getSelectedIndex());
            
            int retorno=new ControlDB_SolicitudEquipo(tipoConexion).modificarEstadoConfirmacion(solicitudEquipo, user, confirmacionSolicitudEquipos1);
            if(retorno==1){
                //mandamos correo a los usuarios con permiso para asignar equipos
                try {
                    new mail(tipoConexion).ConfirmaciónAsignacionEquipos(user, solicitudEquipo, confirmacionSolicitudEquipos1);
                } catch (SQLException ex) {
                   Logger.getLogger(Solicitud_Equipos_Confirmacion.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "La solicitud No. "+solicitudEquipo.getCodigo()+"  Se registro correctamente","Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                removeAll();
                revalidate();
                repaint();
            }else{
                JOptionPane.showMessageDialog(null, "No se pude registrar la confirmacion de la solicitud verifique datos","Error al registrar", JOptionPane.ERROR_MESSAGE);
            }  
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Solicitud_Equipos_Confirmacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Solicitud_Equipos_Confirmacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(Solicitud_Equipos_Confirmacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu Cargar;
    private javax.swing.JMenuItem Cargar1;
    private javax.swing.JInternalFrame InternalFrameSelectorCampos;
    private javax.swing.JInternalFrame InternalFrame_SolicitudesEquipos;
    private javax.swing.JRadioButton checkCentroOperacion;
    private javax.swing.JRadioButton checkFechaSolicitud;
    private javax.swing.JRadioButton checkUsuario;
    private javax.swing.JComboBox<String> estadoConfirmacion;
    private com.toedter.calendar.JDateChooser fechaFin;
    private com.toedter.calendar.JDateChooser fechaInicio;
    private javax.swing.JComboBox<String> horaFin;
    private javax.swing.JComboBox<String> horaInicio;
    private javax.swing.JLabel horarioTiempoIFinalSolicitudEquiposRegistrar;
    private javax.swing.JLabel horarioTiempoInicioSolicitudEquiposRegistrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JComboBox<String> minutoFin;
    private javax.swing.JComboBox<String> minutoInicio;
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
    private javax.swing.JRadioButton selectMotonave_codigo;
    private javax.swing.JRadioButton selectMotonave_estado;
    private javax.swing.JRadioButton selectMotonave_nombre;
    private javax.swing.JRadioButton selectSolicitudEquipo_cantidad;
    private javax.swing.JRadioButton selectSolicitudEquipo_cantidadHoras;
    private javax.swing.JRadioButton selectSolicitudEquipo_codigo;
    private javax.swing.JRadioButton selectSolicitudEquipo_fechaFinalizacion;
    private javax.swing.JRadioButton selectSolicitudEquipo_fechaInicio;
    private javax.swing.JRadioButton selectSolicitudEquipo_marca;
    private javax.swing.JRadioButton selectSolicitudEquipo_modelo;
    private javax.swing.JRadioButton selectSolicitudEquipo_observacion;
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
    private javax.swing.JLabel titulo34;
    private javax.swing.JLabel titulo35;
    private javax.swing.JLabel titulo36;
    private javax.swing.JLabel titulo37;
    private javax.swing.JLabel titulo39;
    private javax.swing.JLabel titulo40;
    private javax.swing.JLabel titulo41;
    private javax.swing.JLabel titulo42;
    private javax.swing.JLabel titulo43;
    private javax.swing.JLabel titulo6;
    private javax.swing.JLabel titulo61;
    private javax.swing.JLabel titulo62;
    private javax.swing.JLabel titulo63;
    private javax.swing.JLabel titulo7;
    private javax.swing.JLabel titulo9;
    // End of variables declaration//GEN-END:variables
    
    public void tabla_Listar() throws SQLException{
        System.out.println("Hola");
        String Stitulo="";
        int contador=0;
        if(selectSolicitudEquipo_codigo.isSelected()){
            Stitulo +="SE_Código"+":";
            contador++;
        }
        if(selectSolicitudEquipo_marca.isSelected()){
            Stitulo +="SE_Marca"+":";
            contador++;
        }
        if(selectSolicitudEquipo_modelo.isSelected()){
            Stitulo +="SE_Modelo"+":";
            contador++;
        }
        if(selectSolicitudEquipo_cantidad.isSelected()){
            Stitulo +="SE_CantidadEquipos"+":";
            contador++;
        }
        if(selectSolicitudEquipo_observacion.isSelected()){
            Stitulo +="SE_Observación"+":";
            contador++;
        }
        if(selectSolicitudEquipo_fechaInicio.isSelected()){
            Stitulo +="SE_FechaInicio"+":";
            contador++;
        }
        if(selectSolicitudEquipo_fechaFinalizacion.isSelected()){
            Stitulo +="SE_FechaFinal"+":";
            contador++;
        }
        if(selectSolicitudEquipo_cantidadHoras.isSelected()){
            Stitulo +="SE_CantidadHoras"+":";
            contador++;
        }
        if(selectCentroCostoAuxiliar_codigo.isSelected()){
            Stitulo +="C.C Aux. Codigo"+":";
            contador++;
        }
        if(selectCentroCostoAuxiliar_nombre.isSelected()){
            Stitulo +="C.C Aux. Nombre"+":";
            contador++;
        }
        if(selectCentroCostoAuxiliar_estado.isSelected()){
            Stitulo +="C.C Aux. Estado"+":";
            contador++;
        }
        if(selectCentroCostoSubcentro_codigo.isSelected()){
            Stitulo +="SubcentroCosto_Código"+":";
            contador++;
        }
        if(selectCentroCostoSubcentro_nombre.isSelected()){
            Stitulo +="SubcentroCosto_Nombre"+":";
            contador++;
        }
        if(selectCentroCostoSubcentro_estado.isSelected()){
            Stitulo +="SubcentroCosto_Estado"+":";
            contador++;
        }
        if(selectTipoEquipo_codigo.isSelected()){
            Stitulo +="TipoEquipo_Código"+":";
            contador++;
        }
        if(selectTipoEquipo_nombre.isSelected()){
            Stitulo +="TipoEquipo_Nombre"+":";
            contador++;
        }
        if(selectTipoEquipo_estado.isSelected()){
            Stitulo +="TipoEquipo_Estado"+":";
            contador++;
        }
        if(selectActividadOperacion_codigo.isSelected()){
            Stitulo +="Actividad_Código"+":";
            contador++;
        }
        if(selectActividadOperacion_nombre.isSelected()){
            Stitulo +="Actividad_Nombre"+":";
            contador++;
        }
        if(selectActividadOperacion_estado.isSelected()){
            Stitulo +="Actividad_Estado"+":";
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
               // System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" +(Object)listado1.getCodigo();
                contadorRegistro++;
            }
            if(selectSolicitudEquipo_marca.isSelected()){
                //System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" + listado1.getMarcaEquipo();
                contadorRegistro++; 
            }
            if(selectSolicitudEquipo_modelo.isSelected()){
                //System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" + listado1.getModeloEquipo();
                contadorRegistro++;
            }
            if(selectSolicitudEquipo_cantidad.isSelected()){
                //System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" + listado1.getCantidad();
                contadorRegistro++;
            }
            if(selectSolicitudEquipo_observacion.isSelected()){
                //contadorw++;
                //System.out.println("w="+contadorw);
                registro[contadorRegistro] = "" + listado1.getObservacacion();
                contadorRegistro++;
            }
            if(selectSolicitudEquipo_fechaInicio.isSelected()){
               // System.out.println("w="+contadorw++);
                registro[contadorRegistro] =""+listado1.getFechaHoraInicio();
                contadorRegistro++;
            }
            if(selectSolicitudEquipo_fechaFinalizacion.isSelected()){
                System.out.println("w="+contadorw++);
                registro[contadorRegistro] =""+listado1.getFechaHoraFin();
                contadorRegistro++;
            }
            if(selectSolicitudEquipo_cantidadHoras.isSelected()){
                System.out.println("w="+contadorw++);
                try{//Calculamos la cantidad de Horas a partir de cierta cantidad de minutos
                    String minutoCantS ="";
                    int minutoCant=(listado1.getCantidadMinutos() % 60);
                    int horasCant=(listado1.getCantidadMinutos()/60);
                    if(minutoCant<9){
                        minutoCantS="0"+minutoCant;
                    }else{
                        minutoCantS=""+minutoCant;
                    }
                    registro[contadorRegistro] ="" + horasCant+":"+minutoCantS;
                }catch(Exception e){
                    registro[contadorRegistro] =""+listado1.getCantidadMinutos();
                }
                contadorRegistro++;
            }
            if(selectCentroCostoAuxiliar_codigo.isSelected()){
                System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" + listado1.getCentroCostoAuxiliar().getCodigo();
                contadorRegistro++;
            }
            if(selectCentroCostoAuxiliar_nombre.isSelected()){
                System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" + listado1.getCentroCostoAuxiliar().getDescripcion();
                contadorRegistro++;
            }
            if(selectCentroCostoAuxiliar_estado.isSelected()){
                System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" + listado1.getCentroCostoAuxiliar().getEstado();
                contadorRegistro++;
            }
            if(selectCentroCostoSubcentro_codigo.isSelected()){
                System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" + listado1.getCentroCostoAuxiliar().getCentroCostoSubCentro().getCodigo();
                contadorRegistro++;
            }
            if(selectCentroCostoSubcentro_nombre.isSelected()){
                System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" + listado1.getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                contadorRegistro++;
            }
            if(selectCentroCostoSubcentro_estado.isSelected()){
                System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" + listado1.getCentroCostoAuxiliar().getCentroCostoSubCentro().getEstado();
                contadorRegistro++;
            }
            if(selectTipoEquipo_codigo.isSelected()){
                System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" + listado1.getTipoEquipo().getCodigo();
                contadorRegistro++;
            }
            if(selectTipoEquipo_nombre.isSelected()){
                System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" + listado1.getTipoEquipo().getDescripcion();
                contadorRegistro++;
            }
            if(selectTipoEquipo_estado.isSelected()){
                System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" + listado1.getTipoEquipo().getEstado();
                contadorRegistro++;
            }
            if(selectActividadOperacion_codigo.isSelected()){
                System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" + listado1.getLaborRealizada().getCodigo();
                contadorRegistro++;
            }
            if(selectActividadOperacion_nombre.isSelected()){
                System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" + listado1.getLaborRealizada().getDescripcion();
                contadorRegistro++;
            }
            if(selectActividadOperacion_estado.isSelected()){
                System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" + listado1.getLaborRealizada().getEstado();
                contadorRegistro++;
            }
            if(selectMotonave_codigo.isSelected()){
                System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" + listado1.getMotonave().getCodigo();
                contadorRegistro++;
            }
            if(selectMotonave_nombre.isSelected()){
                System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" + listado1.getMotonave().getDescripcion();
                contadorRegistro++;
            }
            if(selectMotonave_estado.isSelected()){
                System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" + listado1.getMotonave().getEstado();
                contadorRegistro++;
            }
            if(selectCompañia_codigo.isSelected()){
                System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" + listado1.getCompañia().getCodigo();
                contadorRegistro++;
            }
            if(selectCompañia_nombre.isSelected()){
                System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" + listado1.getCompañia().getDescripcion();
                contadorRegistro++;
            }
            if(selectCompañia_estado.isSelected()){
                System.out.println("w="+contadorw++);
                registro[contadorRegistro] = "" + listado1.getCompañia().getEstado();
                contadorRegistro++;
            }
            modelo.addRow(registro);      
        }
        tabla_ListadoEquiposSolicitud.setModel(modelo);
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
        selectCentroCostoAuxiliar_nombre.setSelected(true);
        selectCentroCostoAuxiliar_estado.setSelected(false);
        selectCentroCostoSubcentro_codigo.setSelected(false);
        selectCentroCostoSubcentro_nombre.setSelected(true);
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
}
