package ModuloEquipo.View;

import Catalogo.Controller.ControlDB_CentroCostoSubCentro;
import Catalogo.Controller.ControlDB_CentroOperacion;
import ModuloCarbon.Controller2.ControlDB_MvtoCarbon;
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import ModuloEquipo.Controller2.ControlDB_AsignacionEquipo;
import Catalogo.Controller.ControlDB_Compañia;
import Catalogo.Controller.ControlDB_Equipo;
import ModuloEquipo.Model.AsignacionEquipo;
import Catalogo.Model.Compañia;
import Catalogo.Model.Equipo;
import ModuloEquipo.Model.SolicitudEquipo;
import ModuloEquipo.Model.SolicitudListadoEquipo;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AsignacionEquipo_Activar extends javax.swing.JPanel {

    private ArrayList<CentroOperacion> listadoCentroOperacion = new ArrayList();
    private ArrayList<String> listadoMarcaEquipo =new ArrayList<>();
    private ArrayList<String> listadoTiposEquipo = new ArrayList();
    private ArrayList<String> listadoModelosEquipo = new ArrayList();
    private Usuario user;
    private ArrayList<Equipo> listadoEquipo = new ArrayList();
    SolicitudEquipo solicitudEquipo= new SolicitudEquipo();
    SolicitudListadoEquipo solicitudListadoEquipo = new SolicitudListadoEquipo();
    int valorSelect_SolicitudListadoEquipo=-1;
    //Asignacion
    private ArrayList<AsignacionEquipo> listadoAsignacionEquipo = new ArrayList();
    private ArrayList<CentroCostoSubCentro> listadoCentroCostoSubCentro = new ArrayList();
    private ArrayList<CentroCostoAuxiliar> listadoCentroCostoAuxiliar = new ArrayList();
    private ArrayList<Compañia> listadoCompañia = new ArrayList();
    private String tipoConexion;
    private AsignacionEquipo asignacionEquipo=null;
    String fechaInicio="";
    String fechaFin="";  
    String script ="";
    public AsignacionEquipo_Activar(Usuario us,String tipoConexion) {
        initComponents();
        this.tipoConexion= tipoConexion;
        user = us;
        asignacionEquipo = null;
        
        //Damos color al interna Frame
        InternaFrameAnulacionAsignacion.getContentPane().setBackground(Color.WHITE);
        InternaFrameAnulacionAsignacion.show(false);
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
                } 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
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
                        dataEquipo[i]=""+(listadoEquipo.get(i).getDescripcion());   
                    }    
                    final DefaultComboBoxModel modelListadoEquipos = new DefaultComboBoxModel(dataEquipo);
                    selectEquipos.setModel(modelListadoEquipos);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsignacionEquipo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Visualizar = new javax.swing.JMenuItem();
        InternaFrameAnulacionAsignacion = new javax.swing.JInternalFrame();
        jLabel17 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel36 = new javax.swing.JLabel();
        solicitud_equipoCantidad = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel35 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        asignacionEquipo_equipoCodigo = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        asignacionEquipo_equipoTipoEquipo = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        asignacionEquipo_equipoProducto = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        asignacionEquipo_equipoMarca = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        asignacionEquipo_equipoModelo = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        asignacionEquipo_equipoSerial = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        asignacionEquipo_equipoDescripcion = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        asignacionEquipo_equipoProveedor = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        asignacionEquipo_equipoPetenencia = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        asignacionEquipo_estado = new javax.swing.JLabel();
        solicitud_codigo = new javax.swing.JLabel();
        solicitud_centroOperación = new javax.swing.JLabel();
        solicitud_auxiliarcentrocosto = new javax.swing.JLabel();
        solicitud_subcentrocosto = new javax.swing.JLabel();
        solicitud_fechaInicio = new javax.swing.JLabel();
        solicitud_fechaFinalizacion = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        solicitud_laborRealizada = new javax.swing.JLabel();
        solicitud_compañia = new javax.swing.JLabel();
        mvtoEquipo_codigo10 = new javax.swing.JLabel();
        solicitud_motonave = new javax.swing.JLabel();
        solicitud_equipotipoEquipo = new javax.swing.JLabel();
        solicitud_equipoMarca = new javax.swing.JLabel();
        solicitud_equipoModelo = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        asignacionEquipo_centroOperacion = new javax.swing.JLabel();
        asignacionEquipo_fechaRegistro = new javax.swing.JLabel();
        asignacionEquipo_fechaInicio = new javax.swing.JLabel();
        asignacionEquipo_Finalizacion = new javax.swing.JLabel();
        asignacionEquipo_cantidadHoras = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel69 = new javax.swing.JLabel();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel70 = new javax.swing.JLabel();
        solicitud_usuarioQuienSolicita_codigo = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        solicitud_usuarioQuienSolicita_nombre = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        solicitud_usuarioQuienSolicita_correo = new javax.swing.JLabel();
        jSeparator20 = new javax.swing.JSeparator();
        jSeparator21 = new javax.swing.JSeparator();
        jSeparator23 = new javax.swing.JSeparator();
        jSeparator24 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator25 = new javax.swing.JSeparator();
        jSeparator26 = new javax.swing.JSeparator();
        jSeparator27 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel73 = new javax.swing.JLabel();
        solicitud_estado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        observacion_asignacion = new javax.swing.JTextArea();
        jLabel74 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        asignacionEquipo_codigo = new javax.swing.JLabel();
        selectTipo = new javax.swing.JComboBox<>();
        selectMarca = new javax.swing.JComboBox<>();
        selectModelo = new javax.swing.JComboBox<>();
        selectEquipos = new javax.swing.JComboBox<>();
        Cronograma_jLabel13 = new javax.swing.JLabel();
        fechaInicioAsignacion = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        horaInicioAsignacion = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        minutoInicioAsignacion = new javax.swing.JComboBox<>();
        timeInicio = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        fechaFinAsignacion = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        horaFinAsignacion = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        minutoFinAsignacion = new javax.swing.JComboBox<>();
        timeFin = new javax.swing.JLabel();
        titulo33 = new javax.swing.JLabel();
        check_equipo = new javax.swing.JRadioButton();
        check_centroOperacion = new javax.swing.JRadioButton();
        check_marca = new javax.swing.JRadioButton();
        check_modelo = new javax.swing.JRadioButton();
        Cronograma_jSeparator4 = new javax.swing.JSeparator();
        Cronograma_titulo38 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla_ListadoAsignacion = new javax.swing.JTable();
        Cronograma_registrar = new javax.swing.JButton();
        selectSubcentroCosto = new javax.swing.JComboBox<>();
        selectCentroCostoAuxiliar = new javax.swing.JComboBox<>();
        selectCompañia = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        check_tipoEquipo = new javax.swing.JRadioButton();
        check_auxCentroCosto = new javax.swing.JRadioButton();
        check_Subcentro = new javax.swing.JRadioButton();
        centroOperacion = new javax.swing.JComboBox<>();
        check_compania = new javax.swing.JRadioButton();
        Cronograma_jLabel4 = new javax.swing.JLabel();
        Cronograma_jLabel16 = new javax.swing.JLabel();
        Cronograma_jSeparator5 = new javax.swing.JSeparator();

        Visualizar.setText("Seleccionar");
        Visualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VisualizarActionPerformed(evt);
            }
        });
        Seleccionar.add(Visualizar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InternaFrameAnulacionAsignacion.setBackground(new java.awt.Color(255, 255, 255));
        InternaFrameAnulacionAsignacion.setClosable(true);
        InternaFrameAnulacionAsignacion.setVisible(false);
        InternaFrameAnulacionAsignacion.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("INFORMACIÓN DE LA ASIGNACIÓN");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, -1, 20));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("DATOS DE LA ASIGNACIÓN");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, -1, 20));
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 40, 410, 10));

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 270, 10, 180));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Fecha Inicio:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 130, 20));

        solicitud_equipoCantidad.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        solicitud_equipoCantidad.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_equipoCantidad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(solicitud_equipoCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 400, 310, 20));

        jSeparator16.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 10, 160));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("DATOS DEL EQUIPO");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 240, 20));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(51, 51, 51));
        jLabel46.setText("Código:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 90, 20));

        asignacionEquipo_equipoCodigo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        asignacionEquipo_equipoCodigo.setForeground(new java.awt.Color(102, 102, 102));
        asignacionEquipo_equipoCodigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(asignacionEquipo_equipoCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 310, 20));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(51, 51, 51));
        jLabel47.setText("Tipo:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 90, 20));

        asignacionEquipo_equipoTipoEquipo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        asignacionEquipo_equipoTipoEquipo.setForeground(new java.awt.Color(102, 102, 102));
        asignacionEquipo_equipoTipoEquipo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(asignacionEquipo_equipoTipoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, 310, 20));

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(51, 51, 51));
        jLabel48.setText("Producto:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 90, 20));

        asignacionEquipo_equipoProducto.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        asignacionEquipo_equipoProducto.setForeground(new java.awt.Color(102, 102, 102));
        asignacionEquipo_equipoProducto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(asignacionEquipo_equipoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 310, 20));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(51, 51, 51));
        jLabel49.setText("Marca:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 90, 20));

        asignacionEquipo_equipoMarca.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        asignacionEquipo_equipoMarca.setForeground(new java.awt.Color(102, 102, 102));
        asignacionEquipo_equipoMarca.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(asignacionEquipo_equipoMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, 310, 20));

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(51, 51, 51));
        jLabel50.setText("Modelo:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, 90, 20));

        asignacionEquipo_equipoModelo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        asignacionEquipo_equipoModelo.setForeground(new java.awt.Color(102, 102, 102));
        asignacionEquipo_equipoModelo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(asignacionEquipo_equipoModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, 310, 20));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(51, 51, 51));
        jLabel51.setText("Serial:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 190, 90, 20));

        asignacionEquipo_equipoSerial.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        asignacionEquipo_equipoSerial.setForeground(new java.awt.Color(102, 102, 102));
        asignacionEquipo_equipoSerial.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(asignacionEquipo_equipoSerial, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 190, 310, 20));

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(51, 51, 51));
        jLabel52.setText("Descripción:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 70, 90, 20));

        asignacionEquipo_equipoDescripcion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        asignacionEquipo_equipoDescripcion.setForeground(new java.awt.Color(102, 102, 102));
        asignacionEquipo_equipoDescripcion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(asignacionEquipo_equipoDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 70, 300, 20));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(51, 51, 51));
        jLabel53.setText("Proveedor:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 90, 90, 20));

        asignacionEquipo_equipoProveedor.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        asignacionEquipo_equipoProveedor.setForeground(new java.awt.Color(102, 102, 102));
        asignacionEquipo_equipoProveedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(asignacionEquipo_equipoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 90, 300, 20));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(51, 51, 51));
        jLabel40.setText("Pertenencia:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 110, 90, 20));

        asignacionEquipo_equipoPetenencia.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        asignacionEquipo_equipoPetenencia.setForeground(new java.awt.Color(102, 102, 102));
        asignacionEquipo_equipoPetenencia.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(asignacionEquipo_equipoPetenencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 110, 300, 20));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(51, 51, 51));
        jLabel41.setText("Código:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 130, 20));

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("Fecha Registro:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 130, 20));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(51, 51, 51));
        jLabel43.setText("Estado:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 130, 20));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(51, 51, 51));
        jLabel44.setText("Cantidad Horas:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 130, 20));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(51, 51, 51));
        jLabel54.setText("Fecha Finalización:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 130, 20));

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(51, 51, 51));
        jLabel55.setText("OBSERVACIÓN:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 140, 50));

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(51, 51, 51));
        jLabel56.setText("Centro Operación:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 130, 20));

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(51, 51, 51));
        jLabel57.setText("Motonave:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 320, 90, 20));
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 840, 10));
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 840, 10));
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 1250, 20));

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 10, 180));
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 60, 410, 10));

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel58.setText("DATOS DE LA SOLICITUD");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, -1, 20));

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(51, 51, 51));
        jLabel59.setText("Centro Costo Auxiliar:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 160, 20));

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(51, 51, 51));
        jLabel60.setText("SubCentro Costo:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 160, 20));

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(51, 51, 51));
        jLabel61.setText("Fecha Inicio:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 160, 20));

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(51, 51, 51));
        jLabel62.setText("Fecha Finalización:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 160, 20));

        asignacionEquipo_estado.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        asignacionEquipo_estado.setForeground(new java.awt.Color(102, 102, 102));
        asignacionEquipo_estado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(asignacionEquipo_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 230, 20));

        solicitud_codigo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        solicitud_codigo.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_codigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(solicitud_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 240, 20));

        solicitud_centroOperación.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        solicitud_centroOperación.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_centroOperación.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(solicitud_centroOperación, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 240, 20));

        solicitud_auxiliarcentrocosto.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        solicitud_auxiliarcentrocosto.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_auxiliarcentrocosto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(solicitud_auxiliarcentrocosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 240, 20));

        solicitud_subcentrocosto.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        solicitud_subcentrocosto.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_subcentrocosto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(solicitud_subcentrocosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, 240, 20));

        solicitud_fechaInicio.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        solicitud_fechaInicio.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_fechaInicio.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(solicitud_fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 240, 20));

        solicitud_fechaFinalizacion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        solicitud_fechaFinalizacion.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_fechaFinalizacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(solicitud_fechaFinalizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 400, 240, 20));

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(51, 51, 51));
        jLabel63.setText("Código:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 130, 20));

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(51, 51, 51));
        jLabel64.setText("Cantidad:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 400, 90, 20));

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(51, 51, 51));
        jLabel65.setText("Compañia:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 300, 90, 20));

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(51, 51, 51));
        jLabel66.setText("Tipo:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 340, 90, 20));

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(51, 51, 51));
        jLabel67.setText("Marca:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 360, 90, 20));

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(51, 51, 51));
        jLabel68.setText("Modelo:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 380, 90, 20));

        solicitud_laborRealizada.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        solicitud_laborRealizada.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_laborRealizada.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(solicitud_laborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 240, 20));

        solicitud_compañia.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        solicitud_compañia.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_compañia.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(solicitud_compañia, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, 310, 20));

        mvtoEquipo_codigo10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mvtoEquipo_codigo10.setForeground(new java.awt.Color(102, 102, 102));
        mvtoEquipo_codigo10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(mvtoEquipo_codigo10, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, 310, 20));

        solicitud_motonave.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        solicitud_motonave.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_motonave.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(solicitud_motonave, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 320, 310, 20));

        solicitud_equipotipoEquipo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        solicitud_equipotipoEquipo.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_equipotipoEquipo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(solicitud_equipotipoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 340, 310, 20));

        solicitud_equipoMarca.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        solicitud_equipoMarca.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_equipoMarca.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(solicitud_equipoMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 360, 310, 20));

        solicitud_equipoModelo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        solicitud_equipoModelo.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_equipoModelo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(solicitud_equipoModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 380, 310, 20));

        jSeparator17.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 10, 170));
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 1250, 10));

        jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 290, 10, 160));

        asignacionEquipo_centroOperacion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        asignacionEquipo_centroOperacion.setForeground(new java.awt.Color(102, 102, 102));
        asignacionEquipo_centroOperacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(asignacionEquipo_centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 230, 20));

        asignacionEquipo_fechaRegistro.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        asignacionEquipo_fechaRegistro.setForeground(new java.awt.Color(102, 102, 102));
        asignacionEquipo_fechaRegistro.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(asignacionEquipo_fechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 230, 20));

        asignacionEquipo_fechaInicio.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        asignacionEquipo_fechaInicio.setForeground(new java.awt.Color(102, 102, 102));
        asignacionEquipo_fechaInicio.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(asignacionEquipo_fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 230, 20));

        asignacionEquipo_Finalizacion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        asignacionEquipo_Finalizacion.setForeground(new java.awt.Color(102, 102, 102));
        asignacionEquipo_Finalizacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(asignacionEquipo_Finalizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 230, 20));

        asignacionEquipo_cantidadHoras.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        asignacionEquipo_cantidadHoras.setForeground(new java.awt.Color(102, 102, 102));
        asignacionEquipo_cantidadHoras.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(asignacionEquipo_cantidadHoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 230, 20));

        jSeparator14.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 10, 170));
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 290, 410, 10));

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel69.setText("DATOS DEL USUARIO QUIEN SOLICITÓ EL EQUIPO");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 290, -1, 20));
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 310, 410, 10));

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(51, 51, 51));
        jLabel70.setText("Código:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 320, 60, 20));

        solicitud_usuarioQuienSolicita_codigo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        solicitud_usuarioQuienSolicita_codigo.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_usuarioQuienSolicita_codigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(solicitud_usuarioQuienSolicita_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 320, 300, 20));

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(51, 51, 51));
        jLabel71.setText("Nombre:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 340, 60, 20));

        solicitud_usuarioQuienSolicita_nombre.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        solicitud_usuarioQuienSolicita_nombre.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_usuarioQuienSolicita_nombre.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(solicitud_usuarioQuienSolicita_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 340, 300, 20));

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(51, 51, 51));
        jLabel72.setText("Correo:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 360, 60, 20));

        solicitud_usuarioQuienSolicita_correo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        solicitud_usuarioQuienSolicita_correo.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_usuarioQuienSolicita_correo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(solicitud_usuarioQuienSolicita_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 360, 300, 20));

        jSeparator20.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 60, 10, 170));
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 1250, 10));
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 430, 10));
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator24, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 130, 410, 10));

        jSeparator19.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 290, 10, 160));
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 1250, 10));

        jSeparator26.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator26, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 40, 10, 190));

        jSeparator27.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternaFrameAnulacionAsignacion.getContentPane().add(jSeparator27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 10, 190));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/atras.png"))); // NOI18N
        jButton1.setText("CERRAR");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        InternaFrameAnulacionAsignacion.getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 540, 140, 40));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/registrar.png"))); // NOI18N
        jButton2.setText("ACTIVAR");
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        InternaFrameAnulacionAsignacion.getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 540, 150, 40));

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(51, 51, 51));
        jLabel73.setText("Confirmación:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 420, 100, 20));

        solicitud_estado.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        solicitud_estado.setForeground(new java.awt.Color(102, 102, 102));
        solicitud_estado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(solicitud_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 420, 310, 20));

        observacion_asignacion.setColumns(20);
        observacion_asignacion.setRows(5);
        jScrollPane1.setViewportView(observacion_asignacion);

        InternaFrameAnulacionAsignacion.getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 460, 1100, 60));

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(51, 51, 51));
        jLabel74.setText("Labor Realizada:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 160, 20));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(51, 51, 51));
        jLabel45.setText("Centro Operación:");
        InternaFrameAnulacionAsignacion.getContentPane().add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 130, 20));

        asignacionEquipo_codigo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        asignacionEquipo_codigo.setForeground(new java.awt.Color(102, 102, 102));
        asignacionEquipo_codigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternaFrameAnulacionAsignacion.getContentPane().add(asignacionEquipo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 230, 20));

        add(InternaFrameAnulacionAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1320, 730));

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
        add(selectTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 40, 550, 30));

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
        add(selectMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 80, 550, 30));

        selectModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectModeloActionPerformed(evt);
            }
        });
        add(selectModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 120, 550, 30));

        selectEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectEquiposActionPerformed(evt);
            }
        });
        add(selectEquipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 160, 550, 30));

        Cronograma_jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Cronograma_jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        Cronograma_jLabel13.setText("FECHA:");
        add(Cronograma_jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 50, 30));

        fechaInicioAsignacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicioAsignacionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicioAsignacionMouseEntered(evt);
            }
        });
        add(fechaInicioAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 130, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Hora");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, 30));

        horaInicioAsignacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                horaInicioAsignacionItemStateChanged(evt);
            }
        });
        add(horaInicioAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 50, 30));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText(":");
        add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 20, 30));

        minutoInicioAsignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoInicioAsignacionActionPerformed(evt);
            }
        });
        add(minutoInicioAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 50, 30));

        timeInicio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        timeInicio.setForeground(new java.awt.Color(102, 102, 102));
        timeInicio.setText("AM");
        add(timeInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 30, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Final");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 40, 30));

        fechaFinAsignacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFinAsignacionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFinAsignacionMouseEntered(evt);
            }
        });
        add(fechaFinAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 130, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Hora");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, -1, 30));

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
        add(horaFinAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, 50, 30));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel27.setText(":");
        add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 30, 10, 30));

        minutoFinAsignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoFinAsignacionActionPerformed(evt);
            }
        });
        add(minutoFinAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 30, 50, 30));

        timeFin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        timeFin.setForeground(new java.awt.Color(102, 102, 102));
        timeFin.setText("AM");
        add(timeFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 30, 30, 30));

        titulo33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo33.setForeground(new java.awt.Color(102, 102, 102));
        titulo33.setText("ACTIVACIÓN DE ASIGNACIONES");
        add(titulo33, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 260, 30));

        check_equipo.setBackground(new java.awt.Color(255, 255, 255));
        check_equipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_equipo.setText("Equipo");
        add(check_equipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 160, 110, -1));

        check_centroOperacion.setBackground(new java.awt.Color(255, 255, 255));
        check_centroOperacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_centroOperacion.setText("Centro Operación:");
        add(check_centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 140, 30));

        check_marca.setBackground(new java.awt.Color(255, 255, 255));
        check_marca.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_marca.setText("Marca");
        add(check_marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 80, 110, 30));

        check_modelo.setBackground(new java.awt.Color(255, 255, 255));
        check_modelo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_modelo.setText("Modelo:");
        check_modelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_modeloActionPerformed(evt);
            }
        });
        add(check_modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 120, 110, 30));
        add(Cronograma_jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 1220, 10));

        Cronograma_titulo38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Cronograma_titulo38.setForeground(new java.awt.Color(102, 102, 102));
        Cronograma_titulo38.setText("Listado de Asignaciones");
        add(Cronograma_titulo38, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 250, 30));

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
        tabla_ListadoAsignacion.setComponentPopupMenu(Seleccionar);
        tabla_ListadoAsignacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_ListadoAsignacionMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabla_ListadoAsignacion);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 1470, 500));

        Cronograma_registrar.setBackground(new java.awt.Color(255, 255, 255));
        Cronograma_registrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Cronograma_registrar.setText("CONSULTAR");
        Cronograma_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cronograma_registrarActionPerformed(evt);
            }
        });
        add(Cronograma_registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, 150, 30));

        selectSubcentroCosto.setToolTipText("");
        selectSubcentroCosto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectSubcentroCostoItemStateChanged(evt);
            }
        });
        add(selectSubcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 210, 30));

        selectCentroCostoAuxiliar.setToolTipText("");
        add(selectCentroCostoAuxiliar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 210, 30));

        selectCompañia.setToolTipText("");
        add(selectCompañia, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, 210, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Inicio");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 40, 30));

        check_tipoEquipo.setBackground(new java.awt.Color(255, 255, 255));
        check_tipoEquipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_tipoEquipo.setText("Tipo Equipo:");
        add(check_tipoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 40, 110, 30));

        check_auxCentroCosto.setBackground(new java.awt.Color(255, 255, 255));
        check_auxCentroCosto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_auxCentroCosto.setText("C.C. Auxiliar:");
        add(check_auxCentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 120, 30));

        check_Subcentro.setBackground(new java.awt.Color(255, 255, 255));
        check_Subcentro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_Subcentro.setText("SubCentro Costo:");
        add(check_Subcentro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 150, 30));

        centroOperacion.setToolTipText("");
        centroOperacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                centroOperacionItemStateChanged(evt);
            }
        });
        add(centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 210, 30));

        check_compania.setBackground(new java.awt.Color(255, 255, 255));
        check_compania.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_compania.setText("Compañia:");
        add(check_compania, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, 120, 30));

        Cronograma_jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cronograma_jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/ExportarExcel.png"))); // NOI18N
        Cronograma_jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Cronograma_jLabel4MouseClicked(evt);
            }
        });
        add(Cronograma_jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 170, 50, 40));

        Cronograma_jLabel16.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Cronograma_jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        Cronograma_jLabel16.setText("Exportar");
        add(Cronograma_jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 150, 50, 30));
        add(Cronograma_jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1270, 20));
    }// </editor-fold>//GEN-END:initComponents

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

    private void selectModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectModeloActionPerformed
        try {
            //Cargamos el selector de Equipos
            listadoEquipo=new ControlDB_Equipo(tipoConexion).Asignacion_buscarEquiposEnAplicacionInterna(selectTipo.getSelectedItem().toString(),selectMarca.getSelectedItem().toString(), selectModelo.getSelectedItem().toString());              
            String dataEquipo[]= new String[listadoEquipo.size()];
            for(int i = 0; i< dataEquipo.length; i++){
                dataEquipo[i]=""+(listadoEquipo.get(i).getCodigo()+" "+listadoEquipo.get(i).getDescripcion());   
            }    
            final DefaultComboBoxModel modelListadoEquipos = new DefaultComboBoxModel(dataEquipo);
            selectEquipos.setModel(modelListadoEquipos);
        } catch (SQLException ex) {
            Logger.getLogger(AsignacionEquipo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_selectModeloActionPerformed

    private void selectEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectEquiposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectEquiposActionPerformed

    private void fechaInicioAsignacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicioAsignacionMouseClicked
        //  alerta_fechaInicio.setText("");
    }//GEN-LAST:event_fechaInicioAsignacionMouseClicked

    private void fechaInicioAsignacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicioAsignacionMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaInicioAsignacionMouseEntered

    private void horaInicioAsignacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_horaInicioAsignacionItemStateChanged
        if(horaInicioAsignacion.getSelectedIndex()<= 11){
            timeInicio.setText("AM");
        }else{
            timeInicio.setText("PM");
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
            timeFin.setText("AM");
        }else{
            timeFin.setText("PM");
        }
    }//GEN-LAST:event_horaFinAsignacionItemStateChanged

    private void horaFinAsignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horaFinAsignacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_horaFinAsignacionActionPerformed

    private void minutoFinAsignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minutoFinAsignacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minutoFinAsignacionActionPerformed

    private void tabla_ListadoAsignacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ListadoAsignacionMouseClicked
        if (evt.getClickCount() == 2) {
            int fila1;
            try{
                fila1=tabla_ListadoAsignacion.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna asignación");
                }
                else{
                    InternaFrameAnulacionAsignacion.show(true);
                    asignacionEquipo = listadoAsignacionEquipo.get(fila1);
                    if(asignacionEquipo != null){
                        asignacionEquipo_codigo.setText(asignacionEquipo.getCodigo());
                        asignacionEquipo_centroOperacion.setText(asignacionEquipo.getCentroOperacion().getDescripcion());
                        asignacionEquipo_fechaRegistro.setText(asignacionEquipo.getFechaRegistro());
                        asignacionEquipo_fechaInicio.setText(asignacionEquipo.getFechaHoraInicio());
                        asignacionEquipo_Finalizacion.setText(asignacionEquipo.getFechaHoraFin());
                        asignacionEquipo_cantidadHoras.setText(asignacionEquipo.getCantidadMinutosProgramados());
                        asignacionEquipo_estado.setText(asignacionEquipo.getEstado());
                        asignacionEquipo_equipoCodigo.setText(asignacionEquipo.getEquipo().getCodigo());
                        asignacionEquipo_equipoTipoEquipo.setText(asignacionEquipo.getEquipo().getTipoEquipo().getDescripcion());
                        asignacionEquipo_equipoProducto.setText(asignacionEquipo.getEquipo().getProducto());
                        asignacionEquipo_equipoMarca.setText(asignacionEquipo.getEquipo().getMarca());
                        asignacionEquipo_equipoModelo.setText(asignacionEquipo.getEquipo().getModelo());
                        asignacionEquipo_equipoSerial.setText(asignacionEquipo.getEquipo().getSerial());
                        asignacionEquipo_equipoDescripcion.setText(asignacionEquipo.getEquipo().getDescripcion());
                        asignacionEquipo_equipoProveedor.setText(asignacionEquipo.getEquipo().getProveedorEquipo().getDescripcion());
                        asignacionEquipo_equipoPetenencia.setText(asignacionEquipo.getPertenencia().getDescripcion());

                        solicitud_codigo.setText(asignacionEquipo.getSolicitudListadoEquipo().getSolicitudEquipo().getCodigo());
                        solicitud_centroOperación.setText(asignacionEquipo.getSolicitudListadoEquipo().getSolicitudEquipo().getCentroOperacion().getDescripcion());
                        solicitud_auxiliarcentrocosto.setText(asignacionEquipo.getSolicitudListadoEquipo().getCentroCostoAuxiliar().getDescripcion());
                        solicitud_subcentrocosto.setText(asignacionEquipo.getSolicitudListadoEquipo().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion());
                        solicitud_fechaInicio.setText(asignacionEquipo.getSolicitudListadoEquipo().getFechaHoraInicio());
                        solicitud_fechaFinalizacion.setText(asignacionEquipo.getSolicitudListadoEquipo().getFechaHoraFin());
                        solicitud_laborRealizada.setText(asignacionEquipo.getSolicitudListadoEquipo().getLaborRealizada().getDescripcion());
                        solicitud_compañia.setText(asignacionEquipo.getSolicitudListadoEquipo().getCompañia().getDescripcion());
                        solicitud_motonave.setText(asignacionEquipo.getSolicitudListadoEquipo().getMotonave().getDescripcion());
                        solicitud_equipotipoEquipo.setText(asignacionEquipo.getSolicitudListadoEquipo().getTipoEquipo().getDescripcion());
                        solicitud_equipoMarca.setText(asignacionEquipo.getSolicitudListadoEquipo().getMarcaEquipo());
                        solicitud_equipoModelo.setText(asignacionEquipo.getSolicitudListadoEquipo().getModeloEquipo());
                        solicitud_equipoCantidad.setText(""+asignacionEquipo.getSolicitudListadoEquipo().getCantidad());
                        solicitud_usuarioQuienSolicita_codigo.setText(asignacionEquipo.getSolicitudListadoEquipo().getSolicitudEquipo().getUsuarioRealizaSolicitud().getCodigo());
                        solicitud_usuarioQuienSolicita_nombre.setText(asignacionEquipo.getSolicitudListadoEquipo().getSolicitudEquipo().getUsuarioRealizaSolicitud().getNombres()+" "+asignacionEquipo.getSolicitudListadoEquipo().getSolicitudEquipo().getUsuarioRealizaSolicitud().getApellidos());
                        solicitud_usuarioQuienSolicita_correo.setText(asignacionEquipo.getSolicitudListadoEquipo().getSolicitudEquipo().getUsuarioRealizaSolicitud().getCorreo());
                        solicitud_estado.setText(asignacionEquipo.getSolicitudListadoEquipo().getSolicitudEquipo().getConfirmacionSolicitudEquipo().getDescripcion());
                    }
                }
            }catch(HeadlessException e){
                JOptionPane.showMessageDialog(null, "No se pudo cargar los datos","Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_tabla_ListadoAsignacionMouseClicked

    private void check_modeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_modeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_modeloActionPerformed

    private void Cronograma_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cronograma_registrarActionPerformed
        script ="";    
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
            fechaInicio=anoI+"-"+mesI+"-"+diaI+" "+horaInicioAsignacion.getSelectedItem().toString()+":"+minutoInicioAsignacion.getSelectedItem().toString()+":00.0";      
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
                fechaFin=anoF+"-"+mesF+"-"+diaF+" "+horaFinAsignacion.getSelectedItem().toString()+":"+minutoFinAsignacion.getSelectedItem().toString()+":00.0";
                script +=" AND  ( '"+fechaInicio+"' BETWEEN [ae_fecha_hora_inicio] AND [ae_fecha_hora_fin] " +
                                "   OR '"+fechaFin+"' BETWEEN [ae_fecha_hora_inicio] AND [ae_fecha_hora_fin] "+
                                "   OR [ae_fecha_hora_inicio] BETWEEN '"+fechaInicio+"' AND '"+fechaFin+"' "+
                                "   OR [ae_fecha_hora_fin] BETWEEN '"+fechaInicio+"' AND '"+fechaFin+"') ";   
                if(check_tipoEquipo.isSelected()){
                    script +=" AND eq_tipo_equipo.[te_desc]='"+listadoTiposEquipo.get(selectTipo.getSelectedIndex())+"' ";
                }
                if(check_marca.isSelected()){
                    script +=" AND [eq_marca]='"+listadoMarcaEquipo.get(selectMarca.getSelectedIndex())+"' ";
                }
                if(check_modelo.isSelected()){
                    script +=" AND [eq_modelo]='"+listadoModelosEquipo.get(selectModelo.getSelectedIndex())+"' ";
                }
                if(check_equipo.isSelected()){
                    script +=" AND [eq_cdgo]="+listadoEquipo.get(selectEquipos.getSelectedIndex()).getCodigo()+" ";
                }
                if(check_centroOperacion.isSelected()){
                    script +=" AND [ae_cntro_oper_cdgo]="+listadoCentroOperacion.get(centroOperacion.getSelectedIndex()).getCodigo()+" ";
                }
                if(check_Subcentro.isSelected()){
                    script +=" AND [cca_cntro_cost_subcentro_cdgo]="+listadoCentroCostoSubCentro.get(selectSubcentroCosto.getSelectedIndex()).getCodigo()+" ";
                }
                if(check_auxCentroCosto.isSelected()){
                    script +=" AND [sle_cntro_cost_auxiliar_cdgo]="+listadoCentroCostoAuxiliar.get(selectCentroCostoAuxiliar.getSelectedIndex()).getCodigo()+" ";
                }
                if(check_compania.isSelected()){
                    script +=" AND [sle_compania_cdgo]='"+listadoCompañia.get(selectCompañia.getSelectedIndex()).getCodigo()+"' ";
                }
                listadoAsignacionEquipo = new ArrayList<>();
                    listadoAsignacionEquipo=new ControlDB_AsignacionEquipo(tipoConexion).consultarProgramacionInactiva(script);
                    DefaultTableModel modelo = new DefaultTableModel(null, new String[]
                    {"Asignacion_Cod","Equipo_Cod","Equipo_Marca", "Equipo_Modelo","Equipo_Descripcion","Fecha_Inicio","Fecha_Fin", "Cantidad Horas","C.O","SubCentro","C.C Auxiliar","Estado"});      
                    for (AsignacionEquipo AsignacionEquipo1 : listadoAsignacionEquipo) {
                        String[] registro = new String[12];
                        registro[0] = "" + AsignacionEquipo1.getCodigo();
                        registro[1] = "" + AsignacionEquipo1.getEquipo().getCodigo();
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
                            registro[7] ="";
                        }
                        //registro[7] = "" + AsignacionEquipo1.getCantidadMinutosProgramados();
                        
                        registro[8] = "" + AsignacionEquipo1.getCentroOperacion().getDescripcion();
                        registro[9] = "" + AsignacionEquipo1.getSolicitudListadoEquipo().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                        registro[10] = "" + AsignacionEquipo1.getSolicitudListadoEquipo().getCentroCostoAuxiliar().getDescripcion();
                        registro[11] = "ASIGNADO";
                        modelo.addRow(registro);      
                    }
                    tabla_ListadoAsignacion.setModel(modelo);
            }catch(Exception e){
              JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Finalización de Asingación","Advertencia",JOptionPane.ERROR_MESSAGE);      
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Inicio de Asingación","Advertencia",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_Cronograma_registrarActionPerformed

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
            }catch (SQLException e){ 
            }
        }else{
            selectSubcentroCosto.removeAllItems();
            selectCentroCostoAuxiliar.removeAllItems();
        }
    }//GEN-LAST:event_selectSubcentroCostoItemStateChanged

    private void Cronograma_jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cronograma_jLabel4MouseClicked
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
                int numFila=tabla_ListadoAsignacion.getRowCount(), numColumna=tabla_ListadoAsignacion.getColumnCount();
                if(archivo.getName().endsWith("xls")){
                    wb = new HSSFWorkbook();
                }else{
                    wb= new XSSFWorkbook();
                }
                Sheet hoja= wb.createSheet("Descargue_Carbón");
                try{
                    for(int i= -1; i < numFila; i++ ){
                        Row fila= hoja.createRow(i+1);
                        for(int j=0; j< numColumna; j++){
                            Cell celda= fila.createCell(j);
                            if(i==-1){
                                celda.setCellValue(String.valueOf(tabla_ListadoAsignacion.getColumnName(j)));
                            }else{
                                try{
                                    String[] valor=String.valueOf(tabla_ListadoAsignacion.getValueAt(i, j)).split("-");
                                    if(valor.length==3){
                                        String[] valor2=valor[2].split(":");
                                        if(valor2.length >= 3){
                                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                            Date fechaM = dateFormat.parse(String.valueOf(tabla_ListadoAsignacion.getValueAt(i, j)));
                                            celda.setCellValue(fechaM);
                                        }else{
                                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                            Date fechaM = dateFormat.parse(tabla_ListadoAsignacion.getValueAt(i, j).toString());
                                            celda.setCellValue(fechaM);
                                        }
                                    }else{
                                        try{
                                            celda.setCellValue(Integer.parseInt(tabla_ListadoAsignacion.getValueAt(i, j).toString()));
                                        }catch(Exception e){
                                            celda.setCellValue(String.valueOf(tabla_ListadoAsignacion.getValueAt(i, j)));
                                        }
                                    }
                                }catch(Exception e){
                                    celda.setCellValue(String.valueOf(tabla_ListadoAsignacion.getValueAt(i, j)));
                                }
                            }
                        }
                    }
                    for(int j=0; j<=numColumna; j++){
                        hoja.autoSizeColumn(j,true);
                    }
                    wb.write(new FileOutputStream(archivo));
                    respuesta= "Exportacion exitosa";
                }catch(Exception e){
                }
                JOptionPane.showMessageDialog(null, respuesta);
            }else{
                JOptionPane.showMessageDialog(null,"Elija un formato valido");
            }
        }
    }//GEN-LAST:event_Cronograma_jLabel4MouseClicked

    private void VisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizarActionPerformed
        int fila1;
        try{
            fila1=tabla_ListadoAsignacion.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna asignación");
            }
            else{
                InternaFrameAnulacionAsignacion.show(true);
                asignacionEquipo = listadoAsignacionEquipo.get(fila1);
                if(asignacionEquipo != null){
                    asignacionEquipo_codigo.setText(asignacionEquipo.getCodigo());
                    asignacionEquipo_centroOperacion.setText(asignacionEquipo.getCentroOperacion().getDescripcion());
                    asignacionEquipo_fechaRegistro.setText(asignacionEquipo.getFechaRegistro());
                    asignacionEquipo_fechaInicio.setText(asignacionEquipo.getFechaHoraInicio());
                    asignacionEquipo_Finalizacion.setText(asignacionEquipo.getFechaHoraFin());
                    asignacionEquipo_cantidadHoras.setText(asignacionEquipo.getCantidadMinutosProgramados());
                    asignacionEquipo_estado.setText(asignacionEquipo.getEstado());
                    asignacionEquipo_equipoCodigo.setText(asignacionEquipo.getEquipo().getCodigo());
                    asignacionEquipo_equipoTipoEquipo.setText(asignacionEquipo.getEquipo().getTipoEquipo().getDescripcion());
                    asignacionEquipo_equipoProducto.setText(asignacionEquipo.getEquipo().getProducto());
                    asignacionEquipo_equipoMarca.setText(asignacionEquipo.getEquipo().getMarca());
                    asignacionEquipo_equipoModelo.setText(asignacionEquipo.getEquipo().getModelo());
                    asignacionEquipo_equipoSerial.setText(asignacionEquipo.getEquipo().getSerial());
                    asignacionEquipo_equipoDescripcion.setText(asignacionEquipo.getEquipo().getDescripcion());
                    asignacionEquipo_equipoProveedor.setText(asignacionEquipo.getEquipo().getProveedorEquipo().getDescripcion());
                    asignacionEquipo_equipoPetenencia.setText(asignacionEquipo.getPertenencia().getDescripcion());
                    
                    solicitud_codigo.setText(asignacionEquipo.getSolicitudListadoEquipo().getSolicitudEquipo().getCodigo());
                    solicitud_centroOperación.setText(asignacionEquipo.getSolicitudListadoEquipo().getSolicitudEquipo().getCentroOperacion().getDescripcion());
                    solicitud_auxiliarcentrocosto.setText(asignacionEquipo.getSolicitudListadoEquipo().getCentroCostoAuxiliar().getDescripcion());
                    solicitud_subcentrocosto.setText(asignacionEquipo.getSolicitudListadoEquipo().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion());
                    solicitud_fechaInicio.setText(asignacionEquipo.getSolicitudListadoEquipo().getFechaHoraInicio());
                    solicitud_fechaFinalizacion.setText(asignacionEquipo.getSolicitudListadoEquipo().getFechaHoraFin());
                    solicitud_laborRealizada.setText(asignacionEquipo.getSolicitudListadoEquipo().getLaborRealizada().getDescripcion());
                    solicitud_compañia.setText(asignacionEquipo.getSolicitudListadoEquipo().getCompañia().getDescripcion());
                    solicitud_motonave.setText(asignacionEquipo.getSolicitudListadoEquipo().getMotonave().getDescripcion());
                    solicitud_equipotipoEquipo.setText(asignacionEquipo.getSolicitudListadoEquipo().getTipoEquipo().getDescripcion());
                    solicitud_equipoMarca.setText(asignacionEquipo.getSolicitudListadoEquipo().getMarcaEquipo());
                    solicitud_equipoModelo.setText(asignacionEquipo.getSolicitudListadoEquipo().getModeloEquipo());
                    solicitud_equipoCantidad.setText(""+asignacionEquipo.getSolicitudListadoEquipo().getCantidad());
                    solicitud_usuarioQuienSolicita_codigo.setText(asignacionEquipo.getSolicitudListadoEquipo().getSolicitudEquipo().getUsuarioRealizaSolicitud().getCodigo());
                    solicitud_usuarioQuienSolicita_nombre.setText(asignacionEquipo.getSolicitudListadoEquipo().getSolicitudEquipo().getUsuarioRealizaSolicitud().getNombres()+" "+asignacionEquipo.getSolicitudListadoEquipo().getSolicitudEquipo().getUsuarioRealizaSolicitud().getApellidos());
                    solicitud_usuarioQuienSolicita_correo.setText(asignacionEquipo.getSolicitudListadoEquipo().getSolicitudEquipo().getUsuarioRealizaSolicitud().getCorreo());
                    solicitud_estado.setText(asignacionEquipo.getSolicitudListadoEquipo().getSolicitudEquipo().getConfirmacionSolicitudEquipo().getDescripcion());
                }
            }
        }catch(HeadlessException e){
            JOptionPane.showMessageDialog(null, "No se pudo cargar los datos","Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_VisualizarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(observacion_asignacion.getText().equals("")){
            JOptionPane.showMessageDialog(null, "La observación de la activación de la asignación no puede estar vacia", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
        }else{
            try {
                if(asignacionEquipo != null){ 
                    int retorno= new ControlDB_AsignacionEquipo(tipoConexion).activarAsignación(asignacionEquipo.getCodigo(), user,observacion_asignacion.getText());
                    if(retorno==1){
                        JOptionPane.showMessageDialog(null, "Se activo la asignación", "Registro Exitoso",JOptionPane.INFORMATION_MESSAGE);
                        observacion_asignacion.setText("");
                        listadoAsignacionEquipo = new ArrayList<>();
                        listadoAsignacionEquipo=new ControlDB_AsignacionEquipo(tipoConexion).consultarProgramacionInactiva(script);
                        DefaultTableModel modelo = new DefaultTableModel(null, new String[]
                        {"Asignacion_Cod","Equipo_Cod","Equipo_Marca", "Equipo_Modelo","Equipo_Descripcion","Fecha_Inicio","Fecha_Fin", "Cantidad Horas","C.O","SubCentro","C.C Auxiliar","Estado"});      
                        for (AsignacionEquipo AsignacionEquipo1 : listadoAsignacionEquipo) {
                            String[] registro = new String[12];
                            registro[0] = "" + AsignacionEquipo1.getCodigo();
                            registro[1] = "" + AsignacionEquipo1.getEquipo().getCodigo();
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
                                registro[7] ="";
                            }
                            //registro[7] = "" + AsignacionEquipo1.getCantidadMinutosProgramados();

                            registro[8] = "" + AsignacionEquipo1.getCentroOperacion().getDescripcion();
                            registro[9] = "" + AsignacionEquipo1.getSolicitudListadoEquipo().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                            registro[10] = "" + AsignacionEquipo1.getSolicitudListadoEquipo().getCentroCostoAuxiliar().getDescripcion();
                            registro[11] = "ASIGNADO";
                            modelo.addRow(registro);      
                        }
                        tabla_ListadoAsignacion.setModel(modelo);  
                        InternaFrameAnulacionAsignacion.show(false);    
                    }else{
                        JOptionPane.showMessageDialog(null, "No se puedo registrar la activacion de la asignación, valide datos", "Error al registrar",JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (FileNotFoundException | UnknownHostException | SocketException ex) {
                Logger.getLogger(MvtoEquipo_InactivarEquipo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        InternaFrameAnulacionAsignacion.show(false);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            
        }else{
            selectSubcentroCosto.removeAllItems();
            selectCentroCostoAuxiliar.removeAllItems();
        } 
    }//GEN-LAST:event_centroOperacionItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cronograma_jLabel13;
    private javax.swing.JLabel Cronograma_jLabel16;
    private javax.swing.JLabel Cronograma_jLabel4;
    private javax.swing.JSeparator Cronograma_jSeparator4;
    private javax.swing.JSeparator Cronograma_jSeparator5;
    private javax.swing.JButton Cronograma_registrar;
    private javax.swing.JLabel Cronograma_titulo38;
    private javax.swing.JInternalFrame InternaFrameAnulacionAsignacion;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JMenuItem Visualizar;
    private javax.swing.JLabel asignacionEquipo_Finalizacion;
    private javax.swing.JLabel asignacionEquipo_cantidadHoras;
    private javax.swing.JLabel asignacionEquipo_centroOperacion;
    private javax.swing.JLabel asignacionEquipo_codigo;
    private javax.swing.JLabel asignacionEquipo_equipoCodigo;
    private javax.swing.JLabel asignacionEquipo_equipoDescripcion;
    private javax.swing.JLabel asignacionEquipo_equipoMarca;
    private javax.swing.JLabel asignacionEquipo_equipoModelo;
    private javax.swing.JLabel asignacionEquipo_equipoPetenencia;
    private javax.swing.JLabel asignacionEquipo_equipoProducto;
    private javax.swing.JLabel asignacionEquipo_equipoProveedor;
    private javax.swing.JLabel asignacionEquipo_equipoSerial;
    private javax.swing.JLabel asignacionEquipo_equipoTipoEquipo;
    private javax.swing.JLabel asignacionEquipo_estado;
    private javax.swing.JLabel asignacionEquipo_fechaInicio;
    private javax.swing.JLabel asignacionEquipo_fechaRegistro;
    private javax.swing.JComboBox<String> centroOperacion;
    private javax.swing.JRadioButton check_Subcentro;
    private javax.swing.JRadioButton check_auxCentroCosto;
    private javax.swing.JRadioButton check_centroOperacion;
    private javax.swing.JRadioButton check_compania;
    private javax.swing.JRadioButton check_equipo;
    private javax.swing.JRadioButton check_marca;
    private javax.swing.JRadioButton check_modelo;
    private javax.swing.JRadioButton check_tipoEquipo;
    private com.toedter.calendar.JDateChooser fechaFinAsignacion;
    private com.toedter.calendar.JDateChooser fechaInicioAsignacion;
    private javax.swing.JComboBox<String> horaFinAsignacion;
    private javax.swing.JComboBox<String> horaInicioAsignacion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
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
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator27;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JComboBox<String> minutoFinAsignacion;
    private javax.swing.JComboBox<String> minutoInicioAsignacion;
    private javax.swing.JLabel mvtoEquipo_codigo10;
    private javax.swing.JTextArea observacion_asignacion;
    private javax.swing.JComboBox<String> selectCentroCostoAuxiliar;
    private javax.swing.JComboBox<String> selectCompañia;
    private javax.swing.JComboBox<String> selectEquipos;
    private javax.swing.JComboBox<String> selectMarca;
    private javax.swing.JComboBox<String> selectModelo;
    private javax.swing.JComboBox<String> selectSubcentroCosto;
    private javax.swing.JComboBox<String> selectTipo;
    private javax.swing.JLabel solicitud_auxiliarcentrocosto;
    private javax.swing.JLabel solicitud_centroOperación;
    private javax.swing.JLabel solicitud_codigo;
    private javax.swing.JLabel solicitud_compañia;
    private javax.swing.JLabel solicitud_equipoCantidad;
    private javax.swing.JLabel solicitud_equipoMarca;
    private javax.swing.JLabel solicitud_equipoModelo;
    private javax.swing.JLabel solicitud_equipotipoEquipo;
    private javax.swing.JLabel solicitud_estado;
    private javax.swing.JLabel solicitud_fechaFinalizacion;
    private javax.swing.JLabel solicitud_fechaInicio;
    private javax.swing.JLabel solicitud_laborRealizada;
    private javax.swing.JLabel solicitud_motonave;
    private javax.swing.JLabel solicitud_subcentrocosto;
    private javax.swing.JLabel solicitud_usuarioQuienSolicita_codigo;
    private javax.swing.JLabel solicitud_usuarioQuienSolicita_correo;
    private javax.swing.JLabel solicitud_usuarioQuienSolicita_nombre;
    private javax.swing.JTable tabla_ListadoAsignacion;
    private javax.swing.JLabel timeFin;
    private javax.swing.JLabel timeInicio;
    private javax.swing.JLabel titulo33;
    // End of variables declaration//GEN-END:variables
}
