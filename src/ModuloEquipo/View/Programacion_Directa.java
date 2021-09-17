/**
 * Esta clase tiene como objetivo:
 *  1. Programar n equipo en un periodo de tiempo. con esta programación los equipos ya pueden registrar actividades dentro de los patios, teniendo en cuenta que realiza
 *     una solicitud, una asignación y posterior una confirmación al mismo tiempo.
 **/

package ModuloEquipo.View2;

import Catalogo.Controller.ControlDB_CentroOperacion;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import Sistema.Model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import Catalogo.Controller.ControlDB_CentroCostoSubCentro;
import Catalogo.Controller.ControlDB_Compañia;
import Catalogo.Controller.ControlDB_Equipo;
import Catalogo.Controller.ControlDB_LaborRealizada;
import Catalogo.Controller.ControlDB_Motonave;
import Catalogo.Controller.ControlDB_TipoEquipo;
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.Compañia;
import Catalogo.Model.Equipo;
import Catalogo.Model.LaborRealizada;
import Catalogo.Model.Motonave;
import Catalogo.Model.TipoEquipo;
import Correos.mail;
import ModuloCarbon.Controller2.ControlDB_MvtoCarbon;
import ModuloEquipo.Controller2.ControlDB_AsignacionEquipo;
import ModuloEquipo.Controller2.ControlDB_SolicitudEquipo;
import ModuloEquipo.Model.AsignacionEquipo;
import ModuloEquipo.Model.ConfirmacionSolicitudEquipos;
import ModuloEquipo.Model.EstadoSolicitudEquipos;
import ModuloEquipo.Model.SolicitudEquipo;
import ModuloEquipo.Model.SolicitudListadoEquipo;
import java.awt.Color;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;


public class Programacion_Directa extends javax.swing.JPanel {
    private Usuario user;
    private String tipoConexion;
    private ArrayList<CentroOperacion> listadoCentroOperacion = new ArrayList();
    private ArrayList<CentroCostoSubCentro> listadoCentroCostoSubCentro = new ArrayList();
    private ArrayList<CentroCostoAuxiliar> listadoCentroCostoAuxiliar = new ArrayList();
    private ArrayList<LaborRealizada> listadoLaborRealizada = new ArrayList();
    private ArrayList<Compañia> listadoCompañia = new ArrayList();
    private ArrayList<String> listadoTiposEquipo = new ArrayList();
    private ArrayList<String> listadoMarcaEquipo =new ArrayList<>();
    private ArrayList<String> listadoModelosEquipo = new ArrayList();
    private ArrayList<Equipo> listadoEquipo = new ArrayList();
    
    private Motonave motonave=null;
    private ArrayList<Motonave> listadoMotonave=new ArrayList<>();
    
    //Asignacion
    private ArrayList<AsignacionEquipo> listadoAsignacionEquipo_Temp = new ArrayList();
    private ArrayList<AsignacionEquipo> listadoAsignacionEquipo = new ArrayList();
    
    
    private SolicitudEquipo solicitudEquipo = new SolicitudEquipo();
    
    /*private ArrayList<CentroOperacion> listadoCentroOperacion = new ArrayList();
    
    private ArrayList<CentroCostoSubCentro> listadoCentroCostoSubCentro = new ArrayList();
    
    
    //private Usuario user;
    private ArrayList<SolicitudListadoEquipo> ListadoSolicitudesEquipos=new ArrayList<>();
    private ArrayList<EstadoSolicitudEquipos> listadoEstadoSolicitud= new ArrayList();
    private ArrayList<Motonave> listadoMotonave=new ArrayList<>();
    private Motonave motonave=null;
    //private ArrayList<CentroOperacion> listadoCentroOperacion = new ArrayList();
    private ArrayList<String> listadoMarcaEquipo =new ArrayList<>();
    private ArrayList<String> listadoTiposEquipo = new ArrayList();
    private ArrayList<String> listadoModelosEquipo = new ArrayList();
    //private ArrayList<CentroCostoSubCentro> listadoCentroCostoSubCentro = new ArrayList();
    private ArrayList<LaborRealizada> listadoLaborRealizada = new ArrayList();
    private ArrayList<CentroCostoAuxiliar> listadoCentroCostoAuxiliar = new ArrayList();
    private ArrayList<Compañia> listadoCompañia = new ArrayList();
    //private String tipoConexion;*/
    
    public Programacion_Directa(Usuario us,String tipoConexion)  {
        
        initComponents();   
        this.tipoConexion= tipoConexion;
        //Asignamos el usuario que viene desde la interfaz para almacenarlo internamente.
        user = us;
        
        //Cargamos los datos del usuario logueado en la interfaz
        Usuario_codigo.setText("Cedula: "+user.getCodigo());
        usuario_nombre.setText("Nombre: "+user.getNombres()+" "+ user.getApellidos());
        
        //Colocamos los paneles de color Blanco
        InternalFrame_ProgramadorEquipos.getContentPane().setBackground(Color.WHITE);
        InternaFrame_buscarMotonave.getContentPane().setBackground(Color.WHITE);
        InternaFrameCronograma.getContentPane().setBackground(Color.WHITE);
        panelCronograma.setViewportView(new AsignacionCronograma(us,tipoConexion));
        
        
        
        //Cargamos la fecha del sistema
        try{
            fechaSolicitud.setText(new ControlDB_Equipo(tipoConexion).retornarFechaDelSistema());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo cargar la fecha del sistema");
        }
        //Cargamos en la interfaz los centros de Operaciones
        try {
            listadoCentroOperacion=new ControlDB_CentroOperacion(tipoConexion).buscarActivos();
            if(listadoCentroOperacion != null){
                String datosObjeto[]= new String[listadoCentroOperacion.size()];
                int contador=0;
                for(CentroOperacion listadoCentroOperacion1 : listadoCentroOperacion){ 
                    datosObjeto[contador]=listadoCentroOperacion1.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                centroOperacion.setModel(model);
            }else{
                centroOperacion.removeAllItems();
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }  
        //Cargamos en la interfaz los subcentro de costos activos
        try {
          
            listadoCentroCostoSubCentro=new ControlDB_CentroCostoSubCentro(tipoConexion).buscarActivos(listadoCentroOperacion.get(centroOperacion.getSelectedIndex()));
            if(listadoCentroCostoSubCentro != null){
                String datosObjeto[]= new String[listadoCentroCostoSubCentro.size()];
                int contador=0;
                for(CentroCostoSubCentro Objeto : listadoCentroCostoSubCentro){ 
                    datosObjeto[contador]=Objeto.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                selectSubcentroCosto.setModel(model);
            }else{
                selectSubcentroCosto.removeAllItems();
            } 
      
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
        //Cargamos los la interfaz los auxiliares de costos segun selección de SubCentro de costos
        try {
            if(listadoCentroCostoSubCentro!= null){
                listadoCentroCostoAuxiliar=new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro.get(selectSubcentroCosto.getSelectedIndex()).getCodigo());
                if(listadoCentroCostoAuxiliar != null){
                    String datosObjeto[]= new String[listadoCentroCostoAuxiliar.size()];
                    int contador=0;
                    for(CentroCostoAuxiliar Objeto : listadoCentroCostoAuxiliar){ 
                        datosObjeto[contador]=Objeto.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    selectCentroCostoAuxiliar.setModel(model);
                }else{
                    selectCentroCostoAuxiliar.removeAllItems();
                }  
            }
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Cargamos en la interfaz las labores Realizadas
        if(listadoCentroCostoSubCentro != null){
            try {
                listadoLaborRealizada=new ControlDB_LaborRealizada(tipoConexion).buscarPorSubcentroCosto(listadoCentroCostoSubCentro.get(selectSubcentroCosto.getSelectedIndex()));
                if(listadoLaborRealizada != null){
                    String datosObjeto[]= new String[listadoLaborRealizada.size()];
                    int contador=0;
                    for(LaborRealizada Objeto : listadoLaborRealizada){ 
                        datosObjeto[contador]=Objeto.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    selectLaborRealizada.setModel(model);
                }else{
                    selectLaborRealizada.removeAllItems();
                } 
            } catch (SQLException ex) {
                Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        centroOperacion.setEnabled(true);
        //Cargamos en la interfaz las compañias activas en el sistema
        try {
            listadoCompañia=new ControlDB_Compañia(tipoConexion).buscarActivos();
            if(listadoCompañia != null){
                String datosObjeto[]= new String[listadoCompañia.size()];
                int contador=0;
                for(Compañia Objeto : listadoCompañia){ 
                    datosObjeto[contador]=Objeto.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                selectCompañia.setModel(model);
            }else{
                selectCompañia.removeAllItems();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }  
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
        
         //Cargamos las horas y minutos
        for(int i = 0; i<= 23; i++){
            if(i<=9){
                horaInicioAsignacion.addItem("0"+i);
                horaFinAsignacion.addItem("0"+i);
            }else{
                horaInicioAsignacion.addItem(""+i);
                horaFinAsignacion.addItem(""+i);
            }
        }
        for(int i = 0; i<= 59; i++){
            if(i<=9){
                minutoInicioAsignacion.addItem("0"+i);
                minutoFinAsignacion.addItem("0"+i);
            }else{
                minutoInicioAsignacion.addItem(""+i);
                minutoFinAsignacion.addItem(""+i);
            }
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EliminarAsignacion = new javax.swing.JPopupMenu();
        EliminarAsignacion1 = new javax.swing.JMenuItem();
        InternaFrameCronograma = new javax.swing.JInternalFrame();
        panelCronograma = new javax.swing.JScrollPane();
        InternaFrame_buscarMotonave = new javax.swing.JInternalFrame();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        valorBusqueda = new javax.swing.JTextField();
        btnConsultar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        InternalFrame_ProgramadorEquipos = new javax.swing.JInternalFrame();
        titulo40 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        titulo61 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        titulo64 = new javax.swing.JLabel();
        selectTipo = new javax.swing.JComboBox<>();
        titulo65 = new javax.swing.JLabel();
        selectMarca = new javax.swing.JComboBox<>();
        titulo66 = new javax.swing.JLabel();
        selectModelo = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        fechaInicioAsignacion = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        horaInicioAsignacion = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        minutoInicioAsignacion = new javax.swing.JComboBox<>();
        horarioTiempoInicioSolicitudEquiposRegistrar1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        fechaFinAsignacion = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        horaFinAsignacion = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        minutoFinAsignacion = new javax.swing.JComboBox<>();
        horarioTiempoIFinalSolicitudEquiposRegistrar1 = new javax.swing.JLabel();
        selectEquipos = new javax.swing.JComboBox<>();
        titulo67 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla_ListadoAsignacionTemp = new javax.swing.JTable();
        registrar = new javax.swing.JButton();
        cargarEquiposAsignados = new javax.swing.JButton();
        titulo68 = new javax.swing.JLabel();
        titulo69 = new javax.swing.JLabel();
        titulo2 = new javax.swing.JLabel();
        selectSubcentroCosto = new javax.swing.JComboBox<>();
        titulo70 = new javax.swing.JLabel();
        selectCompañia = new javax.swing.JComboBox<>();
        titulo7 = new javax.swing.JLabel();
        selectCentroCostoAuxiliar = new javax.swing.JComboBox<>();
        checkMotonave = new javax.swing.JRadioButton();
        icon_buscarMotonave = new javax.swing.JLabel();
        titulo72 = new javax.swing.JLabel();
        selectLaborRealizada = new javax.swing.JComboBox<>();
        titulo73 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        observacion = new javax.swing.JTextArea();
        label_motonave = new javax.swing.JLabel();
        titulo35 = new javax.swing.JLabel();
        titulo36 = new javax.swing.JLabel();
        fechaSolicitud = new javax.swing.JLabel();
        titulo28 = new javax.swing.JLabel();
        centroOperacion = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        Usuario_codigo = new javax.swing.JLabel();
        usuario_nombre = new javax.swing.JLabel();
        titulo6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        titulo30 = new javax.swing.JLabel();
        titulo49 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabla_ListadoAsignacion = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

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
        InternaFrameCronograma.getContentPane().add(panelCronograma, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1440, 770));

        add(InternaFrameCronograma, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1460, 800));

        InternaFrame_buscarMotonave.setClosable(true);
        InternaFrame_buscarMotonave.setVisible(false);
        InternaFrame_buscarMotonave.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jScrollPane6.setViewportView(tabla1);

        InternaFrame_buscarMotonave.getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1460, 740));
        InternaFrame_buscarMotonave.getContentPane().add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 290, 40));

        btnConsultar.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        btnConsultar.setToolTipText("CONSULTAR MOTONAVES");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        InternaFrame_buscarMotonave.getContentPane().add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 60, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("CONSULTAR MOTONAVE:");
        InternaFrame_buscarMotonave.getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 190, 40));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btnCancelar.setToolTipText("CANCELAR BUSQUEDA");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        InternaFrame_buscarMotonave.getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 0, 60, 40));

        btnLimpiar.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/clean.png"))); // NOI18N
        btnLimpiar.setToolTipText("BORRAR RESULTADOS");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        InternaFrame_buscarMotonave.getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 60, 40));

        add(InternaFrame_buscarMotonave, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1410, 760));

        InternalFrame_ProgramadorEquipos.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrame_ProgramadorEquipos.setClosable(true);
        InternalFrame_ProgramadorEquipos.setVisible(false);
        InternalFrame_ProgramadorEquipos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo40.setForeground(new java.awt.Color(102, 102, 102));
        titulo40.setText("Seleccione el Equipo a Programar:");
        InternalFrame_ProgramadorEquipos.getContentPane().add(titulo40, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 250, 20));
        InternalFrame_ProgramadorEquipos.getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 1130, 10));

        titulo61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo61.setForeground(new java.awt.Color(102, 102, 102));
        titulo61.setText("Seleccione Datos de programación:");
        InternalFrame_ProgramadorEquipos.getContentPane().add(titulo61, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 250, 20));
        InternalFrame_ProgramadorEquipos.getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 1130, 10));

        titulo64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo64.setForeground(new java.awt.Color(51, 51, 51));
        titulo64.setText("Tipo Equipo:");
        InternalFrame_ProgramadorEquipos.getContentPane().add(titulo64, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 100, 30));

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
        InternalFrame_ProgramadorEquipos.getContentPane().add(selectTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 390, 30));

        titulo65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo65.setForeground(new java.awt.Color(51, 51, 51));
        titulo65.setText("Equipo:");
        InternalFrame_ProgramadorEquipos.getContentPane().add(titulo65, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 80, 60, 30));

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
        InternalFrame_ProgramadorEquipos.getContentPane().add(selectMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 40, 500, 30));

        titulo66.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo66.setForeground(new java.awt.Color(51, 51, 51));
        titulo66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/reporte.png"))); // NOI18N
        titulo66.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                titulo66MouseClicked(evt);
            }
        });
        InternalFrame_ProgramadorEquipos.getContentPane().add(titulo66, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, 40, 40));

        selectModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectModeloActionPerformed(evt);
            }
        });
        InternalFrame_ProgramadorEquipos.getContentPane().add(selectModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 390, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Fecha/Hora Inicio:");
        InternalFrame_ProgramadorEquipos.getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 130, 30));

        fechaInicioAsignacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicioAsignacionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicioAsignacionMouseEntered(evt);
            }
        });
        InternalFrame_ProgramadorEquipos.getContentPane().add(fechaInicioAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 170, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Hora");
        InternalFrame_ProgramadorEquipos.getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, -1, 30));

        horaInicioAsignacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                horaInicioAsignacionItemStateChanged(evt);
            }
        });
        InternalFrame_ProgramadorEquipos.getContentPane().add(horaInicioAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, 50, 30));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText(":");
        InternalFrame_ProgramadorEquipos.getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, 20, 30));

        minutoInicioAsignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoInicioAsignacionActionPerformed(evt);
            }
        });
        InternalFrame_ProgramadorEquipos.getContentPane().add(minutoInicioAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, 50, 30));

        horarioTiempoInicioSolicitudEquiposRegistrar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        horarioTiempoInicioSolicitudEquiposRegistrar1.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoInicioSolicitudEquiposRegistrar1.setText("AM");
        InternalFrame_ProgramadorEquipos.getContentPane().add(horarioTiempoInicioSolicitudEquiposRegistrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, 40, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Fecha /Hora Fin:");
        InternalFrame_ProgramadorEquipos.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, -1, 30));

        fechaFinAsignacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFinAsignacionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFinAsignacionMouseEntered(evt);
            }
        });
        InternalFrame_ProgramadorEquipos.getContentPane().add(fechaFinAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 170, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Hora");
        InternalFrame_ProgramadorEquipos.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 120, -1, 30));

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
        InternalFrame_ProgramadorEquipos.getContentPane().add(horaFinAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 120, 50, 30));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel27.setText(":");
        InternalFrame_ProgramadorEquipos.getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 120, 20, 30));

        minutoFinAsignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoFinAsignacionActionPerformed(evt);
            }
        });
        InternalFrame_ProgramadorEquipos.getContentPane().add(minutoFinAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 120, 50, 30));

        horarioTiempoIFinalSolicitudEquiposRegistrar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        horarioTiempoIFinalSolicitudEquiposRegistrar1.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoIFinalSolicitudEquiposRegistrar1.setText("AM");
        InternalFrame_ProgramadorEquipos.getContentPane().add(horarioTiempoIFinalSolicitudEquiposRegistrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 120, 50, 30));

        selectEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectEquiposActionPerformed(evt);
            }
        });
        InternalFrame_ProgramadorEquipos.getContentPane().add(selectEquipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, 500, 30));

        titulo67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo67.setForeground(new java.awt.Color(51, 51, 51));
        titulo67.setText("Marca:");
        InternalFrame_ProgramadorEquipos.getContentPane().add(titulo67, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, 100, 30));

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
        tabla_ListadoAsignacionTemp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_ListadoAsignacionTempMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabla_ListadoAsignacionTemp);

        InternalFrame_ProgramadorEquipos.getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 1130, 200));

        registrar.setBackground(new java.awt.Color(255, 255, 255));
        registrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Icono_añadir.png"))); // NOI18N
        registrar.setText("AGREGAR EQUIPO");
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });
        InternalFrame_ProgramadorEquipos.getContentPane().add(registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 190, 40));

        cargarEquiposAsignados.setBackground(new java.awt.Color(255, 255, 255));
        cargarEquiposAsignados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/registrar.png"))); // NOI18N
        cargarEquiposAsignados.setText("CARGAR EQUIPOS ASIGNADOS");
        cargarEquiposAsignados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarEquiposAsignadosActionPerformed(evt);
            }
        });
        InternalFrame_ProgramadorEquipos.getContentPane().add(cargarEquiposAsignados, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 670, -1, -1));

        titulo68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo68.setForeground(new java.awt.Color(51, 51, 51));
        titulo68.setText("Modelo:");
        InternalFrame_ProgramadorEquipos.getContentPane().add(titulo68, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 110, 30));

        titulo69.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo69.setForeground(new java.awt.Color(51, 51, 51));
        titulo69.setText("Ver Asignación");
        InternalFrame_ProgramadorEquipos.getContentPane().add(titulo69, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 400, 100, 20));

        titulo2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo2.setForeground(new java.awt.Color(102, 102, 102));
        titulo2.setText("SUBCENTRO DE COSTO:");
        InternalFrame_ProgramadorEquipos.getContentPane().add(titulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 180, 30));

        selectSubcentroCosto.setToolTipText("");
        selectSubcentroCosto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectSubcentroCostoItemStateChanged(evt);
            }
        });
        InternalFrame_ProgramadorEquipos.getContentPane().add(selectSubcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, 400, 30));

        titulo70.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo70.setForeground(new java.awt.Color(102, 102, 102));
        titulo70.setText("COMPAÑIA:");
        InternalFrame_ProgramadorEquipos.getContentPane().add(titulo70, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 250, 170, 30));

        selectCompañia.setToolTipText("");
        InternalFrame_ProgramadorEquipos.getContentPane().add(selectCompañia, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 250, 360, 30));

        titulo7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo7.setForeground(new java.awt.Color(102, 102, 102));
        titulo7.setText("CENTROCOSTO AUXILIAR:");
        InternalFrame_ProgramadorEquipos.getContentPane().add(titulo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 210, 170, 30));

        selectCentroCostoAuxiliar.setToolTipText("");
        InternalFrame_ProgramadorEquipos.getContentPane().add(selectCentroCostoAuxiliar, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 360, 30));

        checkMotonave.setBackground(new java.awt.Color(255, 255, 255));
        checkMotonave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        checkMotonave.setForeground(new java.awt.Color(102, 102, 102));
        checkMotonave.setText("MOTONAVE:");
        checkMotonave.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        checkMotonave.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkMotonaveItemStateChanged(evt);
            }
        });
        InternalFrame_ProgramadorEquipos.getContentPane().add(checkMotonave, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 130, 30));

        icon_buscarMotonave.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        icon_buscarMotonave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        icon_buscarMotonave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_buscarMotonaveMouseClicked(evt);
            }
        });
        InternalFrame_ProgramadorEquipos.getContentPane().add(icon_buscarMotonave, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 30, 30));

        titulo72.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo72.setForeground(new java.awt.Color(102, 102, 102));
        titulo72.setText("ACTIVIDAD A REALIZAR:");
        InternalFrame_ProgramadorEquipos.getContentPane().add(titulo72, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 180, 30));

        selectLaborRealizada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectLaborRealizadaActionPerformed(evt);
            }
        });
        InternalFrame_ProgramadorEquipos.getContentPane().add(selectLaborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, 400, 30));

        titulo73.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo73.setForeground(new java.awt.Color(102, 102, 102));
        titulo73.setText("OBSERVACIÓN:");
        InternalFrame_ProgramadorEquipos.getContentPane().add(titulo73, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 180, 20));

        observacion.setColumns(20);
        observacion.setRows(5);
        jScrollPane2.setViewportView(observacion);

        InternalFrame_ProgramadorEquipos.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, 950, 60));

        label_motonave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_motonave.setForeground(new java.awt.Color(51, 51, 51));
        InternalFrame_ProgramadorEquipos.getContentPane().add(label_motonave, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 400, 30));

        add(InternalFrame_ProgramadorEquipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1310, 800));

        titulo35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo35.setForeground(new java.awt.Color(102, 102, 102));
        titulo35.setText("PROGRAMACIÓN DIRECTA");
        add(titulo35, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 340, 20));

        titulo36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo36.setForeground(new java.awt.Color(51, 51, 51));
        titulo36.setText("Fecha:");
        add(titulo36, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 120, 30));

        fechaSolicitud.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        fechaSolicitud.setForeground(new java.awt.Color(51, 51, 51));
        add(fechaSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 390, 30));

        titulo28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo28.setForeground(new java.awt.Color(51, 51, 51));
        titulo28.setText("Centro Operación:");
        add(titulo28, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 140, 30));

        centroOperacion.setToolTipText("");
        centroOperacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                centroOperacionItemStateChanged(evt);
            }
        });
        centroOperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                centroOperacionActionPerformed(evt);
            }
        });
        add(centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 390, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/login.png"))); // NOI18N
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 80, 70, 70));

        Usuario_codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Usuario_codigo.setForeground(new java.awt.Color(51, 51, 51));
        add(Usuario_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 90, 510, 30));

        usuario_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        usuario_nombre.setForeground(new java.awt.Color(51, 51, 51));
        add(usuario_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 120, 510, 30));

        titulo6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo6.setForeground(new java.awt.Color(51, 153, 0));
        titulo6.setText("Usuario Quien Realiza Solicitud");
        add(titulo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 60, 220, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/agregar.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 70, 80, 70));

        titulo30.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        titulo30.setForeground(new java.awt.Color(51, 51, 51));
        titulo30.setText("Agregar Equipo");
        add(titulo30, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, 100, 20));

        titulo49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo49.setForeground(new java.awt.Color(0, 102, 102));
        titulo49.setText("LISTA DE EQUIPOS PROGRAMADOS");
        add(titulo49, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 260, 30));
        add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 1380, 10));

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
        tabla_ListadoAsignacion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla_ListadoAsignacion.setComponentPopupMenu(EliminarAsignacion);
        tabla_ListadoAsignacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_ListadoAsignacionMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tabla_ListadoAsignacion);

        add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 1380, 250));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save.png"))); // NOI18N
        jButton1.setText("GUARDAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 440, 200, 50));
        add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 1380, 10));

        jLabel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 153, 153), null, null));
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, 640, 90));

        jLabel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 153, 153), null, null));
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1380, 160));
    }// </editor-fold>//GEN-END:initComponents

    private void centroOperacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_centroOperacionItemStateChanged
       //Cargamos en la interfaz los subcentro de costos activos
        try {
            if(listadoCentroOperacion !=null){
                listadoCentroCostoSubCentro=new ControlDB_CentroCostoSubCentro(tipoConexion).buscarActivos(listadoCentroOperacion.get(centroOperacion.getSelectedIndex()));
                if(listadoCentroCostoSubCentro != null){
                    String datosObjeto[]= new String[listadoCentroCostoSubCentro.size()];
                    int contador=0;
                    for(CentroCostoSubCentro Objeto : listadoCentroCostoSubCentro){ 
                        datosObjeto[contador]=Objeto.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    selectSubcentroCosto.setModel(model);
                }else{
                    selectSubcentroCosto.removeAllItems();
                }
            }else{
                centroOperacion.removeAllItems();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
        //Cargaos los auxiliares
        if(listadoCentroCostoSubCentro != null){
            try {
                listadoCentroCostoAuxiliar = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro.get(selectSubcentroCosto.getSelectedIndex()).getCodigo());
                if(listadoCentroCostoAuxiliar != null){
                    String datosObjeto[] = new String[listadoCentroCostoAuxiliar.size()];
                    int contador = 0;
                    for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliar) {
                        datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    selectCentroCostoAuxiliar.setModel(model);
                }else{
                    selectCentroCostoAuxiliar.removeAllItems();
                } 
            }catch (SQLException e){ 
            }
            //Cargamos las labores realizadas
            try {
                listadoLaborRealizada=new ControlDB_LaborRealizada(tipoConexion).buscarPorSubcentroCosto(listadoCentroCostoSubCentro.get(selectSubcentroCosto.getSelectedIndex()));
                if(listadoLaborRealizada != null){
                    String datosObjeto[]= new String[listadoLaborRealizada.size()];
                    int contador=0;
                    for(LaborRealizada Objeto : listadoLaborRealizada){ 
                        datosObjeto[contador]=Objeto.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    selectLaborRealizada.setModel(model);
                }else{
                    selectLaborRealizada.removeAllItems();
                } 
            } catch (SQLException ex) {
                Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }else{
            selectSubcentroCosto.removeAllItems();
            selectCentroCostoAuxiliar.removeAllItems();
        } 
    }//GEN-LAST:event_centroOperacionItemStateChanged

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        if(listadoCentroOperacion != null){
            //Eliminar todos los elementos de una tabla de programción temporal
            DefaultTableModel modeloAsignTemp =(DefaultTableModel)tabla_ListadoAsignacionTemp.getModel();
            int CantEliminarAsigTemp= tabla_ListadoAsignacionTemp.getRowCount() -1;
            for(int i =CantEliminarAsigTemp; i>=0; i--){
                modeloAsignTemp.removeRow(i);
            }



            InternalFrame_ProgramadorEquipos.show(true);
            //Cargamos en la interfaz los subcentro de costos activos
            try {

                listadoCentroCostoSubCentro=new ControlDB_CentroCostoSubCentro(tipoConexion).buscarActivos(listadoCentroOperacion.get(centroOperacion.getSelectedIndex()));
                if(listadoCentroCostoSubCentro != null){
                    String datosObjeto[]= new String[listadoCentroCostoSubCentro.size()];
                    int contador=0;
                    for(CentroCostoSubCentro Objeto : listadoCentroCostoSubCentro){ 
                        datosObjeto[contador]=Objeto.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    selectSubcentroCosto.setModel(model);
                } else{
                    selectSubcentroCosto.removeAllItems();
                }

            } catch (SQLException ex) {
                Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            } 
            //Cargamos los la interfaz los auxiliares de costos segun selección de SubCentro de costos
            try {
                if(listadoCentroCostoSubCentro!= null){
                    listadoCentroCostoAuxiliar=new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro.get(selectSubcentroCosto.getSelectedIndex()).getCodigo());
                    if(listadoCentroCostoAuxiliar != null){
                        String datosObjeto[]= new String[listadoCentroCostoAuxiliar.size()];
                        int contador=0;
                        for(CentroCostoAuxiliar Objeto : listadoCentroCostoAuxiliar){ 
                            datosObjeto[contador]=Objeto.getDescripcion();
                            contador++;
                        }
                        final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                        selectCentroCostoAuxiliar.setModel(model);
                    }else{
                        selectCentroCostoAuxiliar.removeAllItems();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Cargamos en la interfaz las labores Realizadas
            if(listadoCentroCostoSubCentro != null){
                try {
                    listadoLaborRealizada=new ControlDB_LaborRealizada(tipoConexion).buscarPorSubcentroCosto(listadoCentroCostoSubCentro.get(selectSubcentroCosto.getSelectedIndex()));
                    if(listadoLaborRealizada != null){
                        String datosObjeto[]= new String[listadoLaborRealizada.size()];
                        int contador=0;
                        for(LaborRealizada Objeto : listadoLaborRealizada){ 
                            datosObjeto[contador]=Objeto.getDescripcion();
                            contador++;
                        }
                        final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                        selectLaborRealizada.setModel(model);
                    }else{
                        selectLaborRealizada.removeAllItems();
                    } 
                } catch (SQLException ex) {
                    Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }
            //Validamos items seleccionado
            if(listadoCentroCostoSubCentro != null){
                try {
                    listadoCentroCostoAuxiliar = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro.get(selectSubcentroCosto.getSelectedIndex()).getCodigo());
                    if(listadoCentroCostoAuxiliar != null){
                        String datosObjeto[] = new String[listadoCentroCostoAuxiliar.size()];
                        int contador = 0;
                        for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliar) {
                            datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                            contador++;
                        }
                        final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                        selectCentroCostoAuxiliar.setModel(model);
                    }else{
                        selectCentroCostoAuxiliar.removeAllItems();
                    } 
                    try {
                        listadoLaborRealizada=new ControlDB_LaborRealizada(tipoConexion).buscarPorSubcentroCosto(listadoCentroCostoSubCentro.get(selectSubcentroCosto.getSelectedIndex()));
                        if(listadoLaborRealizada != null){
                            String datosObjeto[]= new String[listadoLaborRealizada.size()];
                            int contador=0;
                            for(LaborRealizada Objeto : listadoLaborRealizada){ 
                                datosObjeto[contador]=Objeto.getDescripcion();
                                contador++;
                            }
                            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                            selectLaborRealizada.setModel(model);
                        }else{
                            selectLaborRealizada.removeAllItems();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                }catch (SQLException e){ 
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Error!!!, Debe existir al menos un Centro de Operacion para ser seleccionado y proceder con la programación", "Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jLabel1MouseClicked

    private void centroOperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_centroOperacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_centroOperacionActionPerformed

    private void tabla_ListadoAsignacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ListadoAsignacionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_ListadoAsignacionMouseClicked

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
                    dataEquipo[i]=""+(listadoEquipo.get(i).getCodigo()+" "+listadoEquipo.get(i).getDescripcion());
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
                        dataEquipo[i]=""+(listadoEquipo.get(i).getCodigo()+" "+listadoEquipo.get(i).getDescripcion());
                    }    
                    final DefaultComboBoxModel modelListadoEquipos = new DefaultComboBoxModel(dataEquipo);
                    selectEquipos.setModel(modelListadoEquipos);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsignacionEquipo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_selectMarcaActionPerformed

    private void titulo66MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titulo66MouseClicked
        InternaFrameCronograma.show(true);
    }//GEN-LAST:event_titulo66MouseClicked

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
        if(horaInicioAsignacion.getSelectedIndex()<= 11){
            horarioTiempoInicioSolicitudEquiposRegistrar1.setText("AM");
        }else{
            horarioTiempoInicioSolicitudEquiposRegistrar1.setText("PM");
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
        if(horaFinAsignacion.getSelectedIndex()<= 11){
            horarioTiempoIFinalSolicitudEquiposRegistrar1.setText("AM");
        }else{
            horarioTiempoIFinalSolicitudEquiposRegistrar1.setText("PM");
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
         if(listadoTiposEquipo !=null){
            if(listadoMarcaEquipo != null){
                if(listadoModelosEquipo != null){
                    if(listadoEquipo !=null){
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
                                //int valorFechaI=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorFechaActual(anoI, mesI, diaI, horaInicioAsignacion.getSelectedItem().toString(), minutoInicioAsignacion.getSelectedItem().toString()));
                                //if(valorFechaI<0){   
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
                                            //int valorFechaF=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorFechaActual(anoF, mesF, diaF, horaFinAsignacion.getSelectedItem().toString(), minutoFinAsignacion.getSelectedItem().toString()));
                                            //if(valorFechaF<0){
                                                fechaFin_Asignacion_Equipo=anoF+"-"+mesF+"-"+diaF+" "+horaFinAsignacion.getSelectedItem().toString()+":"+minutoFinAsignacion.getSelectedItem().toString()+":00.0";
                                                int resultDosFechas=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorEntreDosFechas(anoI, mesI, diaI, horaInicioAsignacion.getSelectedItem().toString(), minutoInicioAsignacion.getSelectedItem().toString(), anoF, mesF, diaF, horaFinAsignacion.getSelectedItem().toString(), minutoFinAsignacion.getSelectedItem().toString()));
                                                if(resultDosFechas < 0){
                                                    JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser mayor a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                                                }else{
                                                    if(resultDosFechas ==0){
                                                        JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser Igual a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                                                    }else{
                                                        if(listadoLaborRealizada !=null){
                                                            if(listadoCentroCostoSubCentro !=null){
                                                                if(listadoCentroCostoAuxiliar !=null){
                                                                    if(listadoCompañia !=null){
                                                                        //Motonave motonave=null;
                                                                        boolean validar= false;
                                                                        if(checkMotonave.isSelected()){
                                                                            if(listadoMotonave !=null){
                                                                                //motonave = listadoMotonave.get(selectMotonave.getSelectedIndex());
                                                                                validar=true;
                                                                            }else{
                                                                                JOptionPane.showMessageDialog(null, "Debe seleccionar una Motonave", "Advertencia",JOptionPane.ERROR_MESSAGE);
                                                                                validar=false;
                                                                            }
                                                                        }else{
                                                                            motonave=null;
                                                                            label_motonave.setText("");
                                                                            validar=true;
                                                                        }
                                                                        if(validar){
                                                                                    Equipo equipoAsignacion = listadoEquipo.get(selectEquipos.getSelectedIndex());
                                                                                    //Cargamos la fecha de inicio de movimientos de la solicitud
                                                                                    //String fechaInicio_Asignacion_Equipo="";
                                                                                    //try{
                                                                                        /*Calendar fechaI = fechaInicioAsignacion.getCalendar();
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
                                                                                        try{*/
                                                                                            //int valorFechaI=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorFechaActual(anoI, mesI, diaI, horaInicioAsignacion.getSelectedItem().toString(), minutoInicioAsignacion.getSelectedItem().toString()));
                                                                                            //if(valorFechaI<0){   
                                                                                                //fechaInicio_Asignacion_Equipo=anoI+"-"+mesI+"-"+diaI+" "+horaInicioAsignacion.getSelectedItem().toString()+":"+minutoInicioAsignacion.getSelectedItem().toString()+":00.0";
                                                                                                //Cargamos la fecha de inicio de movimientos de la solicitud
                                                                                                //String fechaFin_Asignacion_Equipo="";
                                                                                                //try{
                                                                                                    /*Calendar fechaF = fechaFinAsignacion.getCalendar();
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
                                                                                                    }*/
                                                                                                    //try{
                                                                                                       // int valorFechaF=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorFechaActual(anoF, mesF, diaF, horaFinAsignacion.getSelectedItem().toString(), minutoFinAsignacion.getSelectedItem().toString()));
                                                                                                       // if(valorFechaF<0){
                                                                                                            //fechaFin_Asignacion_Equipo=anoF+"-"+mesF+"-"+diaF+" "+horaFinAsignacion.getSelectedItem().toString()+":"+minutoFinAsignacion.getSelectedItem().toString()+":00.0";
                                                                                                            //int resultDosFechas=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorEntreDosFechas(anoI, mesI, diaI, horaInicioAsignacion.getSelectedItem().toString(), minutoInicioAsignacion.getSelectedItem().toString(), anoF, mesF, diaF, horaFinAsignacion.getSelectedItem().toString(), minutoFinAsignacion.getSelectedItem().toString()));
                                                                                                            //if(resultDosFechas < 0){
                                                                                                                //JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser mayor a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                                                                                                            //}else{
                                                                                                                //if(resultDosFechas ==0){
                                                                                                                  //  JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser Igual a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                                                                                                                //}else{
                                                                                                                    //Validamos si el equipo ya se encuentra programado
                                                                                                                    if(new ControlDB_AsignacionEquipo(tipoConexion).Asignacion_validarDisponibilidadEquipo(equipoAsignacion, fechaInicio_Asignacion_Equipo, fechaFin_Asignacion_Equipo)){//El equipo no se encuentra asignado en ese horario
                                                                                                                        JOptionPane.showMessageDialog(null,"El equipo ya se encuentra programado en ese rango de fecha", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                                                                                                    }else{//El equipo no se encuentra asignado en ese horario
                                                                                                                        //Validamos si el equipo ya fue cargado en la interfaz tempora o en la principal
                                                                                                                        boolean validator_EquipoCargado= false;
                                                                                                                        for(AsignacionEquipo asignacionEquipo: listadoAsignacionEquipo_Temp){
                                                                                                                            if(equipoAsignacion.getCodigo().equals(asignacionEquipo.getEquipo().getCodigo())){
                                                                                                                                validator_EquipoCargado=true;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        for(AsignacionEquipo asignacionEquipo: listadoAsignacionEquipo){
                                                                                                                            if(equipoAsignacion.getCodigo().equals(asignacionEquipo.getEquipo().getCodigo())){
                                                                                                                                validator_EquipoCargado=true;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        if(validator_EquipoCargado){
                                                                                                                            JOptionPane.showMessageDialog(null,"El equipo ya fue añadido, seleccione uno diferente", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                                                                                                        }else{                                                                                                                        


                                                                                                                            solicitudEquipo.setCentroOperacion(listadoCentroOperacion.get(centroOperacion.getSelectedIndex()));
                                                                                                                            solicitudEquipo.setUsuarioRealizaSolicitud(user);
                                                                                                                            solicitudEquipo.setUsuarioConfirmacionSolicitud(user);
                                                                                                                            solicitudEquipo.setEstadoSolicitudEquipo(new EstadoSolicitudEquipos("3"));//3=APROBADA   Cambiamos el estado de la solicitud a aprobada proque es programación directa.
                                                                                                                            solicitudEquipo.setConfirmacionSolicitudEquipo(new ConfirmacionSolicitudEquipos("1"));//1=ACEPTAR Confirmación es aceptada porque es programación directa
                                                                                                                            
                                                                                                                            
                                                                                                                            SolicitudListadoEquipo solicitudListadoEquipo = new SolicitudListadoEquipo();

                                                                                                                                String codigoTipoEquipo = new ControlDB_TipoEquipo(tipoConexion).buscar_nombre(selectTipo.getSelectedItem().toString());
                                                                                                                                TipoEquipo tipoEquipo = new TipoEquipo();
                                                                                                                                tipoEquipo.setCodigo(codigoTipoEquipo);
                                                                                                                                tipoEquipo.setDescripcion(selectTipo.getSelectedItem().toString());
                                                                                                                            solicitudListadoEquipo.setTipoEquipo(tipoEquipo);
                                                                                                                            solicitudListadoEquipo.setMarcaEquipo(listadoMarcaEquipo.get(selectMarca.getSelectedIndex()));
                                                                                                                            solicitudListadoEquipo.setModeloEquipo(listadoModelosEquipo.get(selectModelo.getSelectedIndex()));
                                                                                                                            solicitudListadoEquipo.setCantidad(1);
                                                                                                                            solicitudListadoEquipo.setFechaHoraInicio(fechaInicio_Asignacion_Equipo);
                                                                                                                            solicitudListadoEquipo.setFechaHoraFin(fechaFin_Asignacion_Equipo);
                                                                                                                            solicitudListadoEquipo.setLaborRealizada(listadoLaborRealizada.get(selectLaborRealizada.getSelectedIndex()));
                                                                                                                            solicitudListadoEquipo.setMotonave(motonave);
                                                                                                                            if(motonave != null){
                                                                                                                                solicitudListadoEquipo.setMotonaveBaseDatos(motonave.getBaseDatos().getCodigo());
                                                                                                                            }else{
                                                                                                                                solicitudListadoEquipo.setMotonaveBaseDatos(null);
                                                                                                                            }
                                                                                                                            solicitudListadoEquipo.setCentroCostoAuxiliar(listadoCentroCostoAuxiliar.get(selectCentroCostoAuxiliar.getSelectedIndex()));
                                                                                                                            solicitudListadoEquipo.setCompañia(listadoCompañia.get(selectCompañia.getSelectedIndex()));
                                                                                                                            solicitudListadoEquipo.setObservacacion(observacion.getText());      
                                                                                                                            try{
                                                                                                                                solicitudListadoEquipo.setCantidadMinutos(new ControlDB_Equipo(tipoConexion).DiferenciasMinutosEntreDosFechas(anoI, mesI, diaI, horaInicioAsignacion.getSelectedItem().toString(), minutoInicioAsignacion.getSelectedItem().toString(), anoF, mesF, diaF, horaFinAsignacion.getSelectedItem().toString(), minutoFinAsignacion.getSelectedItem().toString()));
                                                                                                                            }catch(Exception e){

                                                                                                                            }
                                                                                                                            //if( validarExistenciaEnListadoEquipos(solicitudListadoEquipo)){
                                                                                                                                //JOptionPane.showMessageDialog(null,"Ya se agrego esta clasificación de Equipo, valide datos","Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                                                                                                            //}else{
                                                                                                                                //ListadoSolicitudesEquipos.add(solicitudListadoEquipo);

                                                                                                                                //if(ListadoSolicitudesEquipos.size()>= 1){//Si la cantidad de equipos cargador es mayor o igual a 1 inactivamos el CentrodeOperación para que el usuario no lo pueda cambiar
                                                                                                                                  // centroOperacion.setEnabled(false);
                                                                                                                               // }
                                                                                                                                //CargarListadoEquipos();
                                                                                                                               // InternalFrameSelectorEquipo3.show(false);
                                                                                                                               AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                                                                                                                               asignacionEquipo.setCentroOperacion(solicitudEquipo.getCentroOperacion());
                                                                                                                               asignacionEquipo.setSolicitudListadoEquipo(solicitudListadoEquipo);
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
                                                                                                                            //}
                                                                                                                        }
                                                                                                                    }
                                                                                                                //}
                                                                                                            //}
                                                                                                        //}else{
                                                                                                        //    JOptionPane.showMessageDialog(null,"Error!!.. No puede cargar una fecha en el pasado, la fecha de Finalización de la Operación debe ser futura, verifique fecha","Advertencia",JOptionPane.ERROR_MESSAGE);
                                                                                                        //}
                                                                                                   // }catch(Exception e){
                                                                                                    //    JOptionPane.showMessageDialog(null,"Error!!.. Al trata de validar fecha actual del registro con fecha del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                                                                                                    //}
                                                                                                //}catch(Exception e){
                                                                                                //    JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Finalización","Advertencia",JOptionPane.ERROR_MESSAGE);
                                                                                                //}
                                                                                            //}else{
                                                                                              //  JOptionPane.showMessageDialog(null,"Error!!.. No puede cargar una fecha en el pasado, la fecha de Inicio de Operación debe ser futura, verifique fecha","Advertencia",JOptionPane.ERROR_MESSAGE);
                                                                                            //}
                                                                                       // }catch(Exception e){
                                                                                         //   JOptionPane.showMessageDialog(null,"Error!!.. Al trata de validar fecha actual del registro con fecha del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                                                                                        //}
                                                                                    //}catch(Exception e){
                                                                                     //   JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Inicio","Advertencia",JOptionPane.ERROR_MESSAGE);
                                                                                    //}
                                                                                }
                                                                    }else{
                                                                        JOptionPane.showMessageDialog(null, "Debe seleccionar una compañia", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                                                    }
                                                                }else{
                                                                    JOptionPane.showMessageDialog(null, "Debe seleccionar un auxiliar de centro de costo", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                                                }     
                                                            }else{
                                                                JOptionPane.showMessageDialog(null, "Debe seleccionar un Subcentro de Operación", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                                            }
                                                        }else{
                                                            JOptionPane.showMessageDialog(null, "Debe seleccionar una Labor Realizada", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                                        }
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
                            }catch(Exception e){
                                JOptionPane.showMessageDialog(null,"Error!!.. Al trata de validar fecha actual del registro con fecha del sistema","Advertencia",JOptionPane.ERROR_MESSAGE);
                            }
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Inicio","Advertencia",JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Debe seleccionar un Equipo para ser programado", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un Modelo de Equipo", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Debe seleccionar una Marca de Equipo", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar una Tipo de Equipo", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
        }                     
    }//GEN-LAST:event_registrarActionPerformed

    private void cargarEquiposAsignadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarEquiposAsignadosActionPerformed
        centroOperacion.setEnabled(false);
        for(AsignacionEquipo AsignacionEquipo1: listadoAsignacionEquipo_Temp){
            listadoAsignacionEquipo.add(AsignacionEquipo1);
        }
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"C.O","Subcentro","AuxiliarCentroCosto","LaborRealizada","Motonave","Equipo_Código","Equipo_Tipo","Equipo_Descripción","Fecha Inicio","Fecha Finalización", "Horas Programadas"});      
        for (AsignacionEquipo AsignacionEquipo1 : listadoAsignacionEquipo) {
            String[] registro = new String[11];
            registro[0] = "" + AsignacionEquipo1.getCentroOperacion().getDescripcion();
            registro[1] = "" + AsignacionEquipo1.getSolicitudListadoEquipo().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
            registro[2] = "" + AsignacionEquipo1.getSolicitudListadoEquipo().getCentroCostoAuxiliar().getDescripcion();
            registro[3] = "" + AsignacionEquipo1.getSolicitudListadoEquipo().getLaborRealizada().getDescripcion();
            if(AsignacionEquipo1.getSolicitudListadoEquipo().getMotonave()==null){
                 registro[4] = "";
            }else{
                registro[4] = "" + AsignacionEquipo1.getSolicitudListadoEquipo().getMotonave().getDescripcion();
            }
            registro[5] = "" + AsignacionEquipo1.getEquipo().getCodigo();
            registro[6] = "" + AsignacionEquipo1.getEquipo().getTipoEquipo().getDescripcion();
            registro[7] = "" + AsignacionEquipo1.getEquipo().getDescripcion()+" "+AsignacionEquipo1.getEquipo().getModelo();
            registro[8] = "" + AsignacionEquipo1.getFechaHoraInicio();
            registro[9] = "" + AsignacionEquipo1.getFechaHoraFin();   
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
                registro[10] = "" + horasCant+":"+minutoCantS;
            }catch(Exception e){
                registro[10] ="";
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
        InternalFrame_ProgramadorEquipos.show(false);
        
        resizeColumnWidth(tabla_ListadoAsignacion);
        /*removeAll();
        revalidate();
        repaint();*/
        
    }//GEN-LAST:event_cargarEquiposAsignadosActionPerformed

    private void selectSubcentroCostoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectSubcentroCostoItemStateChanged
        if(listadoCentroCostoSubCentro != null){
            try {
                listadoCentroCostoAuxiliar = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro.get(selectSubcentroCosto.getSelectedIndex()).getCodigo());
                if(listadoCentroCostoAuxiliar != null){
                    String datosObjeto[] = new String[listadoCentroCostoAuxiliar.size()];
                    int contador = 0;
                    for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliar) {
                        datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    selectCentroCostoAuxiliar.setModel(model);
                }else{
                    selectCentroCostoAuxiliar.removeAllItems();
                } 
                try {
                    listadoLaborRealizada=new ControlDB_LaborRealizada(tipoConexion).buscarPorSubcentroCosto(listadoCentroCostoSubCentro.get(selectSubcentroCosto.getSelectedIndex()));
                    if(listadoLaborRealizada != null){
                        String datosObjeto[]= new String[listadoLaborRealizada.size()];
                        int contador=0;
                        for(LaborRealizada Objeto : listadoLaborRealizada){ 
                            datosObjeto[contador]=Objeto.getDescripcion();
                            contador++;
                        }
                        final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                        selectLaborRealizada.setModel(model);
                    }else{
                        selectLaborRealizada.removeAllItems();
                    } 
                } catch (SQLException ex) {
                    Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }catch (SQLException e){ 
            }
        }else{
            selectSubcentroCosto.removeAllItems();
            selectLaborRealizada.removeAllItems();
            selectCentroCostoAuxiliar.removeAllItems();
        }
    }//GEN-LAST:event_selectSubcentroCostoItemStateChanged

    private void checkMotonaveItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkMotonaveItemStateChanged
        if(checkMotonave.isSelected()){
            //selectMotonave.setEnabled(true);
            icon_buscarMotonave.show(true);
            label_motonave.show(true);
        }else{
            //selectMotonave.setEnabled(false);
            icon_buscarMotonave.show(false);
            label_motonave.show(false);
        }
    }//GEN-LAST:event_checkMotonaveItemStateChanged

    private void icon_buscarMotonaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_buscarMotonaveMouseClicked
        InternaFrame_buscarMotonave.show(true);
    }//GEN-LAST:event_icon_buscarMotonaveMouseClicked

    private void selectLaborRealizadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectLaborRealizadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectLaborRealizadaActionPerformed

    private void tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla1MouseClicked
        if (evt.getClickCount() == 2) {
            int fila1;
            try{
                fila1=tabla1.getSelectedRow();
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
    }//GEN-LAST:event_tabla1MouseClicked

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        try {
            tabla_Listar(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        InternaFrame_buscarMotonave.show(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        boolean validar = false;
        if(listadoAsignacionEquipo.size() >= 1 ){
            validar=true;
        }
        if(validar){
            try {
                //try {
                // ArrayList<SolicitudListadoEquipo> listadoSolicitud
                ArrayList<SolicitudListadoEquipo> lista= new ArrayList<>();
                for(AsignacionEquipo objeto: listadoAsignacionEquipo){
                    lista.add(objeto.getSolicitudListadoEquipo());      
                }
                solicitudEquipo.setListadoSolicitudesEquipos(lista);
                
                
                
                int retorno=new ControlDB_AsignacionEquipo(tipoConexion).registrarProgramacionDirecta(listadoAsignacionEquipo,solicitudEquipo, user);
                if(retorno==1){
                        //Cambiamos el estado de la solicitud
                        JOptionPane.showMessageDialog(null, "Se registro la programación directa de forma exitosa","Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                        removeAll();
                        revalidate();
                        repaint();
                    }else{
                        JOptionPane.showMessageDialog(null, "No se pude registrar la la programación directa, verifique datos","Errores al registrar", JOptionPane.ERROR_MESSAGE);
                    }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Programacion_Directa.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Programacion_Directa.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SocketException ex) {
                Logger.getLogger(Programacion_Directa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Error! Debe agregar al menos un equipo a ser programado","Advertencia",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
                    DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"C.O","Subcentro","AuxiliarCentroCosto","LaborRealizada","Motonave","Equipo_Código","Equipo_Tipo","Equipo_Descripción","Fecha Inicio","Fecha Finalización", "Horas Programadas"});      
                    for (AsignacionEquipo AsignacionEquipo1 : listadoAsignacionEquipo) {
                        String[] registro = new String[11];
                        registro[0] = "" + AsignacionEquipo1.getCentroOperacion().getDescripcion();
                        registro[1] = "" + AsignacionEquipo1.getSolicitudListadoEquipo().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                        registro[2] = "" + AsignacionEquipo1.getSolicitudListadoEquipo().getCentroCostoAuxiliar().getDescripcion();
                        registro[3] = "" + AsignacionEquipo1.getSolicitudListadoEquipo().getLaborRealizada().getDescripcion();
                        if(AsignacionEquipo1.getSolicitudListadoEquipo().getMotonave()==null){
                             registro[4] = "";
                        }else{
                            registro[4] = "" + AsignacionEquipo1.getSolicitudListadoEquipo().getMotonave().getDescripcion();
                        }
                        registro[5] = "" + AsignacionEquipo1.getEquipo().getCodigo();
                        registro[6] = "" + AsignacionEquipo1.getEquipo().getTipoEquipo().getDescripcion();
                        registro[7] = "" + AsignacionEquipo1.getEquipo().getDescripcion()+" "+AsignacionEquipo1.getEquipo().getModelo();
                        registro[8] = "" + AsignacionEquipo1.getFechaHoraInicio();
                        registro[9] = "" + AsignacionEquipo1.getFechaHoraFin();   
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
                            registro[10] = "" + horasCant+":"+minutoCantS;
                        }catch(Exception e){
                            registro[10] ="";
                        }
                        //registro[8] = "" + AsignacionEquipo1.getCantidadMinutosProgramados();
                        modelo.addRow(registro);      
                    }
                    tabla_ListadoAsignacion.setModel(modelo);
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_EliminarAsignacion1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu EliminarAsignacion;
    private javax.swing.JMenuItem EliminarAsignacion1;
    private javax.swing.JInternalFrame InternaFrameCronograma;
    private javax.swing.JInternalFrame InternaFrame_buscarMotonave;
    private javax.swing.JInternalFrame InternalFrame_ProgramadorEquipos;
    private javax.swing.JLabel Usuario_codigo;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton cargarEquiposAsignados;
    private javax.swing.JComboBox<String> centroOperacion;
    private javax.swing.JRadioButton checkMotonave;
    private com.toedter.calendar.JDateChooser fechaFinAsignacion;
    private com.toedter.calendar.JDateChooser fechaInicioAsignacion;
    private javax.swing.JLabel fechaSolicitud;
    private javax.swing.JComboBox<String> horaFinAsignacion;
    private javax.swing.JComboBox<String> horaInicioAsignacion;
    private javax.swing.JLabel horarioTiempoIFinalSolicitudEquiposRegistrar1;
    private javax.swing.JLabel horarioTiempoInicioSolicitudEquiposRegistrar1;
    private javax.swing.JLabel icon_buscarMotonave;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel label_motonave;
    private javax.swing.JComboBox<String> minutoFinAsignacion;
    private javax.swing.JComboBox<String> minutoInicioAsignacion;
    private javax.swing.JTextArea observacion;
    private javax.swing.JScrollPane panelCronograma;
    private javax.swing.JButton registrar;
    private javax.swing.JComboBox<String> selectCentroCostoAuxiliar;
    private javax.swing.JComboBox<String> selectCompañia;
    private javax.swing.JComboBox<String> selectEquipos;
    private javax.swing.JComboBox<String> selectLaborRealizada;
    private javax.swing.JComboBox<String> selectMarca;
    private javax.swing.JComboBox<String> selectModelo;
    private javax.swing.JComboBox<String> selectSubcentroCosto;
    private javax.swing.JComboBox<String> selectTipo;
    private javax.swing.JTable tabla1;
    private javax.swing.JTable tabla_ListadoAsignacion;
    private javax.swing.JTable tabla_ListadoAsignacionTemp;
    private javax.swing.JLabel titulo2;
    private javax.swing.JLabel titulo28;
    private javax.swing.JLabel titulo30;
    private javax.swing.JLabel titulo35;
    private javax.swing.JLabel titulo36;
    private javax.swing.JLabel titulo40;
    private javax.swing.JLabel titulo49;
    private javax.swing.JLabel titulo6;
    private javax.swing.JLabel titulo61;
    private javax.swing.JLabel titulo64;
    private javax.swing.JLabel titulo65;
    private javax.swing.JLabel titulo66;
    private javax.swing.JLabel titulo67;
    private javax.swing.JLabel titulo68;
    private javax.swing.JLabel titulo69;
    private javax.swing.JLabel titulo7;
    private javax.swing.JLabel titulo70;
    private javax.swing.JLabel titulo72;
    private javax.swing.JLabel titulo73;
    private javax.swing.JLabel usuario_nombre;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
     public void tabla_Listar(String valorConsulta) throws SQLException{      
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "Nombre","Estado","BaseDeDatos"});  
        listadoMotonave=new ControlDB_Motonave(tipoConexion).buscar(valorConsulta);
        for(Motonave listadoObjetos1:listadoMotonave){
            String []registro = new String[4];
            registro[0]=""+listadoObjetos1.getCodigo();
            registro[1]=""+listadoObjetos1.getDescripcion();
            registro[2]=""+listadoObjetos1.getEstado();            
            registro[3]=""+listadoObjetos1.getBaseDatos().getDescripcion();            
            modelo.addRow(registro);
        }
        tabla1.setModel(modelo);
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
