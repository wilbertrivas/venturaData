package ModuloEquipo.View2;

import Catalogo.Controller.ControlDB_CentroCostoSubCentro;
import Catalogo.Controller.ControlDB_CentroOperacion;
import ModuloCarbon.Controller2.ControlDB_MvtoCarbon;
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import ModuloEquipo.Controller2.ControlDB_AsignacionEquipo;
import Catalogo.Controller.ControlDB_Compañia;
import Catalogo.Controller.ControlDB_Equipo;
import Catalogo.Controller.ControlDB_LaborRealizada;
import ModuloEquipo.Model.AsignacionEquipo;
import Catalogo.Model.Compañia;
import Catalogo.Model.Equipo;
import Catalogo.Model.LaborRealizada;
import ModuloEquipo.Model.SolicitudEquipo;
import ModuloEquipo.Model.SolicitudListadoEquipo;
import Sistema.Model.Usuario;
import java.awt.Component;
import java.io.File;
import java.io.FileOutputStream;
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
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AsignacionCronograma extends javax.swing.JPanel {

    private ArrayList<CentroOperacion> listadoCentroOperacion = new ArrayList();
    private ArrayList<String> listadoMarcaEquipo =new ArrayList<>();
    private ArrayList<String> listadoTiposEquipo = new ArrayList();
    private ArrayList<String> listadoModelosEquipo = new ArrayList();
    private Usuario user;
    private ArrayList<Equipo> listadoEquipo = new ArrayList();
    SolicitudEquipo solicitudEquipo= new SolicitudEquipo();
    SolicitudListadoEquipo solicitudListadoEquipo = new SolicitudListadoEquipo();
    int Cronograma_valorSelect_SolicitudListadoEquipo=-1;
    //Asignacion
    private ArrayList<AsignacionEquipo> listadoAsignacionEquipo = new ArrayList();
    private ArrayList<CentroCostoSubCentro> listadoCentroCostoSubCentro = new ArrayList();
    private ArrayList<CentroCostoAuxiliar> listadoCentroCostoAuxiliar = new ArrayList();
    private ArrayList<Compañia> listadoCompañia = new ArrayList();
    private String tipoConexion;
    
    public AsignacionCronograma(Usuario us,String tipoConexion) {
        initComponents();
        this.tipoConexion= tipoConexion;
        user = us;
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
                select_centroOperacion.setModel(model);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Cargamos en la interfaz los subcentro de costos activos
        try {
          
            listadoCentroCostoSubCentro=new ControlDB_CentroCostoSubCentro(tipoConexion).buscarActivos(listadoCentroOperacion.get(select_centroOperacion.getSelectedIndex()));
            if(listadoCentroCostoSubCentro != null){
                String datosObjeto[]= new String[listadoCentroCostoSubCentro.size()];
                int contador=0;
                for(CentroCostoSubCentro Objeto : listadoCentroCostoSubCentro){ 
                    datosObjeto[contador]=Objeto.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                select_SubcentroCosto.setModel(model);
            } 
      
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
        //Cargamos los la interfaz los auxiliares de costos segun selección de SubCentro de costos
        try {
            if(listadoCentroCostoSubCentro!= null){
                listadoCentroCostoAuxiliar=new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro.get(select_SubcentroCosto.getSelectedIndex()).getCodigo());
                if(listadoCentroCostoAuxiliar != null){
                    String datosObjeto[]= new String[listadoCentroCostoAuxiliar.size()];
                    int contador=0;
                    for(CentroCostoAuxiliar Objeto : listadoCentroCostoAuxiliar){ 
                        datosObjeto[contador]=Objeto.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_CentroCostoAuxiliar.setModel(model);
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
                select_Compañia.setModel(model);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }  
        //Cargamos las horas y minutos
        for(int i = 0; i<= 23; i++){
            if(i<=9){
                Cronograma_horaInicioAsignacion.addItem("0"+i);
                Cronograma_horaFinAsignacion.addItem("0"+i);
            }else{
                Cronograma_horaInicioAsignacion.addItem(""+i);
                Cronograma_horaFinAsignacion.addItem(""+i);
            }
        }
        for(int i = 0; i<= 59; i++){
            if(i<=9){
                Cronograma_minutoInicioAsignacion.addItem("0"+i);
                Cronograma_minutoFinAsignacion.addItem("0"+i);
            }else{
                Cronograma_minutoInicioAsignacion.addItem(""+i);
                Cronograma_minutoFinAsignacion.addItem(""+i);
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
                select_Tipo.setModel(model);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
        //cargamos las marcas
        try {
            if(listadoTiposEquipo != null){
                listadoMarcaEquipo=new ControlDB_Equipo(tipoConexion).cargarMarcasEquiposEnAplicacionInterna(listadoTiposEquipo.get(select_Tipo.getSelectedIndex()));
                if(listadoMarcaEquipo != null){
                    String datosObjeto[]= new String[listadoMarcaEquipo.size()];
                    int contador=0;
                    for(String Objeto : listadoMarcaEquipo){ 
                        datosObjeto[contador]=Objeto;
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_Marca.setModel(model);
                } 
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
        try {
            if(listadoTiposEquipo != null && listadoMarcaEquipo != null ){
                listadoModelosEquipo=new ControlDB_Equipo(tipoConexion).cargarModelosEquiposEnAplicacionInterna(listadoTiposEquipo.get(select_Tipo.getSelectedIndex()),listadoMarcaEquipo.get(select_Marca.getSelectedIndex()));
                //listadoMarcaEquipo=new ControlDB_Equipo().cargarMarcasEquiposEnAplicacionInterna(listadoTiposEquipo.get(selectTipo.getSelectedIndex()));
                if(listadoModelosEquipo != null){
                    String datosObjeto[]= new String[listadoModelosEquipo.size()];
                    int contador=0;
                    for(String Objeto : listadoModelosEquipo){ 
                        datosObjeto[contador]=Objeto;
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_Modelo.setModel(model);
                    
                    //Cargamos el selector de Equipos
                    listadoEquipo=new ControlDB_Equipo(tipoConexion).Asignacion_buscarEquiposEnAplicacionInterna(select_Tipo.getSelectedItem().toString(),select_Marca.getSelectedItem().toString(), select_Modelo.getSelectedItem().toString());              
                    String dataEquipo[]= new String[listadoEquipo.size()];
                    for(int i = 0; i< dataEquipo.length; i++){
                        dataEquipo[i]=""+(listadoEquipo.get(i).getDescripcion());   
                    }    
                    final DefaultComboBoxModel modelListadoEquipos = new DefaultComboBoxModel(dataEquipo);
                    select_Equipos.setModel(modelListadoEquipos);
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

        select_Tipo = new javax.swing.JComboBox<>();
        select_Marca = new javax.swing.JComboBox<>();
        select_Modelo = new javax.swing.JComboBox<>();
        select_Equipos = new javax.swing.JComboBox<>();
        Cronograma_jLabel13 = new javax.swing.JLabel();
        Cronograma_fechaInicioAsignacion = new com.toedter.calendar.JDateChooser();
        Cronograma_jLabel10 = new javax.swing.JLabel();
        Cronograma_horaInicioAsignacion = new javax.swing.JComboBox<>();
        Cronograma_jLabel26 = new javax.swing.JLabel();
        Cronograma_minutoInicioAsignacion = new javax.swing.JComboBox<>();
        Cronograma_timeInicio = new javax.swing.JLabel();
        Cronograma_jLabel11 = new javax.swing.JLabel();
        Cronograma_fechaFinAsignacion = new com.toedter.calendar.JDateChooser();
        Cronograma_jLabel14 = new javax.swing.JLabel();
        Cronograma_horaFinAsignacion = new javax.swing.JComboBox<>();
        Cronograma_jLabel27 = new javax.swing.JLabel();
        Cronograma_minutoFinAsignacion = new javax.swing.JComboBox<>();
        Cronograma_timeFin = new javax.swing.JLabel();
        Cronograma_titulo33 = new javax.swing.JLabel();
        check_equipo = new javax.swing.JRadioButton();
        check_centroOperacion = new javax.swing.JRadioButton();
        check_marca = new javax.swing.JRadioButton();
        check_modelo = new javax.swing.JRadioButton();
        Cronograma_jSeparator4 = new javax.swing.JSeparator();
        Cronograma_titulo38 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla_ListadoAsignacion = new javax.swing.JTable();
        Cronograma_registrar = new javax.swing.JButton();
        select_SubcentroCosto = new javax.swing.JComboBox<>();
        select_CentroCostoAuxiliar = new javax.swing.JComboBox<>();
        select_Compañia = new javax.swing.JComboBox<>();
        Cronograma_jLabel15 = new javax.swing.JLabel();
        check_tipoEquipo = new javax.swing.JRadioButton();
        check_auxCentroCosto = new javax.swing.JRadioButton();
        check_Subcentro = new javax.swing.JRadioButton();
        select_centroOperacion = new javax.swing.JComboBox<>();
        check_compania = new javax.swing.JRadioButton();
        Cronograma_jLabel4 = new javax.swing.JLabel();
        Cronograma_jLabel16 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        select_Tipo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        select_Tipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_TipoItemStateChanged(evt);
            }
        });
        select_Tipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                select_TipoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                select_TipoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                select_TipoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                select_TipoMousePressed(evt);
            }
        });
        select_Tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_TipoActionPerformed(evt);
            }
        });
        add(select_Tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 120, 360, 30));

        select_Marca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MarcaItemStateChanged(evt);
            }
        });
        select_Marca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                select_MarcaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                select_MarcaMouseExited(evt);
            }
        });
        select_Marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_MarcaActionPerformed(evt);
            }
        });
        add(select_Marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 360, 30));

        select_Modelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_ModeloActionPerformed(evt);
            }
        });
        add(select_Modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 120, 290, 30));

        select_Equipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_EquiposActionPerformed(evt);
            }
        });
        add(select_Equipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 160, 360, 30));

        Cronograma_jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Cronograma_jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        Cronograma_jLabel13.setText("FECHA");
        add(Cronograma_jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 40, 30));

        Cronograma_fechaInicioAsignacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Cronograma_fechaInicioAsignacionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Cronograma_fechaInicioAsignacionMouseEntered(evt);
            }
        });
        add(Cronograma_fechaInicioAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 130, 30));

        Cronograma_jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Cronograma_jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        Cronograma_jLabel10.setText("Hora");
        add(Cronograma_jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, -1, 30));

        Cronograma_horaInicioAsignacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Cronograma_horaInicioAsignacionItemStateChanged(evt);
            }
        });
        add(Cronograma_horaInicioAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 50, 30));

        Cronograma_jLabel26.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Cronograma_jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        Cronograma_jLabel26.setText(":");
        add(Cronograma_jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 20, 30));

        Cronograma_minutoInicioAsignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cronograma_minutoInicioAsignacionActionPerformed(evt);
            }
        });
        add(Cronograma_minutoInicioAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 50, 30));

        Cronograma_timeInicio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Cronograma_timeInicio.setForeground(new java.awt.Color(102, 102, 102));
        Cronograma_timeInicio.setText("AM");
        add(Cronograma_timeInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 30, 30));

        Cronograma_jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Cronograma_jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        Cronograma_jLabel11.setText("Final");
        add(Cronograma_jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 40, 30));

        Cronograma_fechaFinAsignacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Cronograma_fechaFinAsignacionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Cronograma_fechaFinAsignacionMouseEntered(evt);
            }
        });
        add(Cronograma_fechaFinAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 130, 30));

        Cronograma_jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Cronograma_jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        Cronograma_jLabel14.setText("Hora");
        add(Cronograma_jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 30, -1, 30));

        Cronograma_horaFinAsignacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Cronograma_horaFinAsignacionItemStateChanged(evt);
            }
        });
        Cronograma_horaFinAsignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cronograma_horaFinAsignacionActionPerformed(evt);
            }
        });
        add(Cronograma_horaFinAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 30, 50, 30));

        Cronograma_jLabel27.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Cronograma_jLabel27.setText(":");
        add(Cronograma_jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 30, 10, 30));

        Cronograma_minutoFinAsignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cronograma_minutoFinAsignacionActionPerformed(evt);
            }
        });
        add(Cronograma_minutoFinAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, 50, 30));

        Cronograma_timeFin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Cronograma_timeFin.setForeground(new java.awt.Color(102, 102, 102));
        Cronograma_timeFin.setText("AM");
        add(Cronograma_timeFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 30, 30, 30));

        Cronograma_titulo33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Cronograma_titulo33.setForeground(new java.awt.Color(102, 102, 102));
        Cronograma_titulo33.setText("CONSULTAR PROGRAMACIÓN DE EQUIPOS");
        add(Cronograma_titulo33, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 360, 30));

        check_equipo.setBackground(new java.awt.Color(255, 255, 255));
        check_equipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_equipo.setText("Equipo");
        add(check_equipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 160, -1, -1));

        check_centroOperacion.setBackground(new java.awt.Color(255, 255, 255));
        check_centroOperacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_centroOperacion.setText("Centro Operación:");
        add(check_centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 140, 30));

        check_marca.setBackground(new java.awt.Color(255, 255, 255));
        check_marca.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_marca.setText("Marca");
        add(check_marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, 30));

        check_modelo.setBackground(new java.awt.Color(255, 255, 255));
        check_modelo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_modelo.setText("Modelo:");
        check_modelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_modeloActionPerformed(evt);
            }
        });
        add(check_modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 120, -1, 30));
        add(Cronograma_jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 1410, 20));

        Cronograma_titulo38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Cronograma_titulo38.setForeground(new java.awt.Color(102, 102, 102));
        Cronograma_titulo38.setText("Listado de Asignaciones");
        add(Cronograma_titulo38, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 180, 40));

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
        jScrollPane4.setViewportView(tabla_ListadoAsignacion);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 1490, 460));

        Cronograma_registrar.setBackground(new java.awt.Color(255, 255, 255));
        Cronograma_registrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Cronograma_registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        Cronograma_registrar.setText("CONSULTAR");
        Cronograma_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cronograma_registrarActionPerformed(evt);
            }
        });
        add(Cronograma_registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, 160, 40));

        select_SubcentroCosto.setToolTipText("");
        select_SubcentroCosto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_SubcentroCostoItemStateChanged(evt);
            }
        });
        add(select_SubcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, 360, 30));

        select_CentroCostoAuxiliar.setToolTipText("");
        add(select_CentroCostoAuxiliar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 80, 290, 30));

        select_Compañia.setToolTipText("");
        add(select_Compañia, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 360, 30));

        Cronograma_jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Cronograma_jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        Cronograma_jLabel15.setText("Inicio");
        add(Cronograma_jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 40, 30));

        check_tipoEquipo.setBackground(new java.awt.Color(255, 255, 255));
        check_tipoEquipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_tipoEquipo.setText("Tipo Equipo:");
        add(check_tipoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 110, 30));

        check_auxCentroCosto.setBackground(new java.awt.Color(255, 255, 255));
        check_auxCentroCosto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_auxCentroCosto.setText("C.C. Auxiliar:");
        add(check_auxCentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 80, 110, 30));

        check_Subcentro.setBackground(new java.awt.Color(255, 255, 255));
        check_Subcentro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_Subcentro.setText("SubCentro Costo:");
        add(check_Subcentro, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, 140, 30));

        select_centroOperacion.setToolTipText("");
        select_centroOperacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_centroOperacionItemStateChanged(evt);
            }
        });
        add(select_centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 360, 30));

        check_compania.setBackground(new java.awt.Color(255, 255, 255));
        check_compania.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_compania.setText("Compañia:");
        add(check_compania, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 130, 30));

        Cronograma_jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cronograma_jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/ExportarExcel.png"))); // NOI18N
        Cronograma_jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Cronograma_jLabel4MouseClicked(evt);
            }
        });
        add(Cronograma_jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, 50, 30));

        Cronograma_jLabel16.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Cronograma_jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        Cronograma_jLabel16.setText("Exportar");
        add(Cronograma_jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, 50, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void select_TipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_TipoItemStateChanged

    }//GEN-LAST:event_select_TipoItemStateChanged

    private void select_TipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_TipoMouseClicked

    }//GEN-LAST:event_select_TipoMouseClicked

    private void select_TipoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_TipoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_select_TipoMouseEntered

    private void select_TipoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_TipoMouseExited

    }//GEN-LAST:event_select_TipoMouseExited

    private void select_TipoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_TipoMousePressed

    }//GEN-LAST:event_select_TipoMousePressed

    private void select_TipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_TipoActionPerformed
        try {
            listadoMarcaEquipo = new ControlDB_Equipo(tipoConexion).cargarMarcasEquiposEnAplicacionInterna(select_Tipo.getSelectedItem().toString());
            if (listadoMarcaEquipo != null) {
                String datosObjeto[] = new String[listadoMarcaEquipo.size()];
                int contador = 0;
                for (String listadoMarcaEquipo1 : listadoMarcaEquipo) {
                    datosObjeto[contador] = listadoMarcaEquipo1;
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                select_Marca.setModel(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            listadoModelosEquipo = new ControlDB_Equipo(tipoConexion).cargarModelosEquiposEnAplicacionInterna(select_Tipo.getSelectedItem().toString(),select_Marca.getSelectedItem().toString());
            if (listadoModelosEquipo != null) {
                String datosObjeto[] = new String[listadoModelosEquipo.size()];
                int contador = 0;
                for (String listadoModelosEquipo1 : listadoModelosEquipo) {
                    datosObjeto[contador] = listadoModelosEquipo1;
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                select_Modelo.setModel(model);
                
                //Cargamos el selector de Equipos
                listadoEquipo=new ControlDB_Equipo(tipoConexion).Asignacion_buscarEquiposEnAplicacionInterna(select_Tipo.getSelectedItem().toString(),select_Marca.getSelectedItem().toString(), select_Modelo.getSelectedItem().toString());              
                String dataEquipo[]= new String[listadoEquipo.size()];
                for(int i = 0; i< dataEquipo.length; i++){
                    //dataEquipo[i]=""+(listadoEquipo.get(i).getDescripcion());  
                    dataEquipo[i]=""+(listadoEquipo.get(i).getCodigo()+" "+listadoEquipo.get(i).getDescripcion());
                }    
                final DefaultComboBoxModel modelListadoEquipos = new DefaultComboBoxModel(dataEquipo);
                select_Equipos.setModel(modelListadoEquipos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsignacionEquipo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_select_TipoActionPerformed

    private void select_MarcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MarcaItemStateChanged

    }//GEN-LAST:event_select_MarcaItemStateChanged

    private void select_MarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_MarcaMouseClicked

    }//GEN-LAST:event_select_MarcaMouseClicked

    private void select_MarcaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_select_MarcaMouseExited

    }//GEN-LAST:event_select_MarcaMouseExited

    private void select_MarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_MarcaActionPerformed
        try {
            if(listadoTiposEquipo != null && listadoMarcaEquipo != null ){
                listadoModelosEquipo=new ControlDB_Equipo(tipoConexion).cargarModelosEquiposEnAplicacionInterna(listadoTiposEquipo.get(select_Tipo.getSelectedIndex()),listadoMarcaEquipo.get(select_Marca.getSelectedIndex()));
                //listadoMarcaEquipo=new ControlDB_Equipo().cargarMarcasEquiposEnAplicacionInterna(listadoTiposEquipo.get(selectTipo.getSelectedIndex()));
                if(listadoModelosEquipo != null){
                    String datosObjeto[]= new String[listadoModelosEquipo.size()];
                    int contador=0;
                    for(String Objeto : listadoModelosEquipo){
                        datosObjeto[contador]=Objeto;
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_Modelo.setModel(model);
                    
                    //Cargamos el selector de Equipos
                    listadoEquipo=new ControlDB_Equipo(tipoConexion).Asignacion_buscarEquiposEnAplicacionInterna(select_Tipo.getSelectedItem().toString(),select_Marca.getSelectedItem().toString(), select_Modelo.getSelectedItem().toString());              
                    String dataEquipo[]= new String[listadoEquipo.size()];
                    for(int i = 0; i< dataEquipo.length; i++){
                        //dataEquipo[i]=""+(listadoEquipo.get(i).getDescripcion());  
                        dataEquipo[i]=""+(listadoEquipo.get(i).getCodigo()+" "+listadoEquipo.get(i).getDescripcion());
                    }    
                    final DefaultComboBoxModel modelListadoEquipos = new DefaultComboBoxModel(dataEquipo);
                    select_Equipos.setModel(modelListadoEquipos);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsignacionEquipo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_select_MarcaActionPerformed

    private void select_ModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_ModeloActionPerformed
        try {
            //Cargamos el selector de Equipos
            listadoEquipo=new ControlDB_Equipo(tipoConexion).Asignacion_buscarEquiposEnAplicacionInterna(select_Tipo.getSelectedItem().toString(),select_Marca.getSelectedItem().toString(), select_Modelo.getSelectedItem().toString());              
            String dataEquipo[]= new String[listadoEquipo.size()];
            for(int i = 0; i< dataEquipo.length; i++){
                dataEquipo[i]=""+(listadoEquipo.get(i).getCodigo()+" "+listadoEquipo.get(i).getDescripcion());   
            }    
            final DefaultComboBoxModel modelListadoEquipos = new DefaultComboBoxModel(dataEquipo);
            select_Equipos.setModel(modelListadoEquipos);
        } catch (SQLException ex) {
            Logger.getLogger(AsignacionEquipo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_select_ModeloActionPerformed

    private void select_EquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_EquiposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_EquiposActionPerformed

    private void Cronograma_fechaInicioAsignacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cronograma_fechaInicioAsignacionMouseClicked
        //  alerta_fechaInicio.setText("");
    }//GEN-LAST:event_Cronograma_fechaInicioAsignacionMouseClicked

    private void Cronograma_fechaInicioAsignacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cronograma_fechaInicioAsignacionMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Cronograma_fechaInicioAsignacionMouseEntered

    private void Cronograma_horaInicioAsignacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Cronograma_horaInicioAsignacionItemStateChanged
        if(Cronograma_horaInicioAsignacion.getSelectedIndex()<= 11){
            Cronograma_timeInicio.setText("AM");
        }else{
            Cronograma_timeInicio.setText("PM");
        }
    }//GEN-LAST:event_Cronograma_horaInicioAsignacionItemStateChanged

    private void Cronograma_minutoInicioAsignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cronograma_minutoInicioAsignacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cronograma_minutoInicioAsignacionActionPerformed

    private void Cronograma_fechaFinAsignacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cronograma_fechaFinAsignacionMouseClicked
        //alerta_fechaFinal.setText("");
    }//GEN-LAST:event_Cronograma_fechaFinAsignacionMouseClicked

    private void Cronograma_fechaFinAsignacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cronograma_fechaFinAsignacionMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Cronograma_fechaFinAsignacionMouseEntered

    private void Cronograma_horaFinAsignacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Cronograma_horaFinAsignacionItemStateChanged
        if(Cronograma_horaFinAsignacion.getSelectedIndex()<= 11){
            Cronograma_timeFin.setText("AM");
        }else{
            Cronograma_timeFin.setText("PM");
        }
    }//GEN-LAST:event_Cronograma_horaFinAsignacionItemStateChanged

    private void Cronograma_horaFinAsignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cronograma_horaFinAsignacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cronograma_horaFinAsignacionActionPerformed

    private void Cronograma_minutoFinAsignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cronograma_minutoFinAsignacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cronograma_minutoFinAsignacionActionPerformed

    private void tabla_ListadoAsignacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ListadoAsignacionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_ListadoAsignacionMouseClicked

    private void check_modeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_modeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_modeloActionPerformed

    private void Cronograma_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cronograma_registrarActionPerformed
        String script ="";
        String fechaInicio="";
        try{
            Calendar fechaI = Cronograma_fechaInicioAsignacion.getCalendar();
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
            fechaInicio=anoI+"-"+mesI+"-"+diaI+" "+Cronograma_horaInicioAsignacion.getSelectedItem().toString()+":"+Cronograma_minutoInicioAsignacion.getSelectedItem().toString()+":00.0";
                    
            try{
                String fechaFin="";  
                Calendar fechaF = Cronograma_fechaFinAsignacion.getCalendar();
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
                fechaFin=anoF+"-"+mesF+"-"+diaF+" "+Cronograma_horaFinAsignacion.getSelectedItem().toString()+":"+Cronograma_minutoFinAsignacion.getSelectedItem().toString()+":00.0";
                script +=" AND  ( '"+fechaInicio+"' BETWEEN [ae_fecha_hora_inicio] AND [ae_fecha_hora_fin] " +
                                "   OR '"+fechaFin+"' BETWEEN [ae_fecha_hora_inicio] AND [ae_fecha_hora_fin] "+
                                "   OR [ae_fecha_hora_inicio] BETWEEN '"+fechaInicio+"' AND '"+fechaFin+"' "+
                                "   OR [ae_fecha_hora_fin] BETWEEN '"+fechaInicio+"' AND '"+fechaFin+"') ";   
                if(check_tipoEquipo.isSelected()){
                    script +=" AND eq_tipo_equipo.[te_desc]='"+listadoTiposEquipo.get(select_Tipo.getSelectedIndex())+"' ";
                }
                if(check_marca.isSelected()){
                    script +=" AND [eq_marca]='"+listadoMarcaEquipo.get(select_Marca.getSelectedIndex())+"' ";
                }
                if(check_modelo.isSelected()){
                    script +=" AND [eq_modelo]='"+listadoModelosEquipo.get(select_Modelo.getSelectedIndex())+"' ";
                }
                if(check_equipo.isSelected()){
                    script +=" AND [eq_cdgo]="+listadoEquipo.get(select_Equipos.getSelectedIndex()).getCodigo()+" ";
                }
                if(check_centroOperacion.isSelected()){
                    script +=" AND [ae_cntro_oper_cdgo]="+listadoCentroOperacion.get(select_centroOperacion.getSelectedIndex()).getCodigo()+" ";
                }
                if(check_Subcentro.isSelected()){
                    script +=" AND [cca_cntro_cost_subcentro_cdgo]="+listadoCentroCostoSubCentro.get(select_SubcentroCosto.getSelectedIndex()).getCodigo()+" ";
                }
                if(check_auxCentroCosto.isSelected()){
                    script +=" AND [sle_cntro_cost_auxiliar_cdgo]="+listadoCentroCostoAuxiliar.get(select_CentroCostoAuxiliar.getSelectedIndex()).getCodigo()+" ";
                }
                if(check_compania.isSelected()){
                    script +=" AND [sle_compania_cdgo]='"+listadoCompañia.get(select_Compañia.getSelectedIndex()).getCodigo()+"' ";
                }
                listadoAsignacionEquipo = new ArrayList<>();
                    listadoAsignacionEquipo=new ControlDB_AsignacionEquipo(tipoConexion).consultarProgramacionDeAsignacion(script);
                    DefaultTableModel modelo = new DefaultTableModel(null, new String[]
                    {"Asignacion_Cod","Equipo_Cod","Equipo_Marca", "Equipo_Modelo","Equipo_Descripcion","Fecha_Inicio","Fecha_Fin", "Cantidad Horas","C.O","SubCentro","C.C Auxiliar","Estado","Estado_Asignacion","Confirmación","EstadoDisponibilidad"});      
                    for (AsignacionEquipo AsignacionEquipo1 : listadoAsignacionEquipo) {
                        String[] registro = new String[15];
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
                        registro[12] =  "" + AsignacionEquipo1.getSolicitudListadoEquipo().getSolicitudEquipo().getEstadoSolicitudEquipo().getDescripcion();
                        if(AsignacionEquipo1.getSolicitudListadoEquipo().getSolicitudEquipo().getConfirmacionSolicitudEquipo().getDescripcion() == null){
                            registro[13] =  "PENDIENTE";
                        }else{
                            if(AsignacionEquipo1.getSolicitudListadoEquipo().getSolicitudEquipo().getConfirmacionSolicitudEquipo().getDescripcion().equals("ACEPTAR")){
                                registro[13] =  "ACEPTADA";
                            }else{
                                registro[13] =  "RECHAZADA";
                            }
                        }
                        
                        registro[14] = AsignacionEquipo1.getColorDisponibilidad();
                        modelo.addRow(registro);      
                    }
                    tabla_ListadoAsignacion.setModel(modelo);
                    tabla_ListadoAsignacion.setDefaultRenderer(Object.class, new Myrender_AsignacionEquipo_Consultar(14));
                    resizeColumnWidth(tabla_ListadoAsignacion);
            }catch(Exception e){
              JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Finalización de Asingación","Advertencia",JOptionPane.ERROR_MESSAGE);      
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Inicio de Asingación","Advertencia",JOptionPane.ERROR_MESSAGE);
        }
        
        
        
        /*
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
                int valorFechaI=Integer.parseInt(new ControlDB_Equipo().comparadorFechaActual(anoI, mesI, diaI, horaInicioAsignacion.getSelectedItem().toString(), minutoInicioAsignacion.getSelectedItem().toString()));
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
                            int valorFechaF=Integer.parseInt(new ControlDB_Equipo().comparadorFechaActual(anoF, mesF, diaF, horaFinAsignacion.getSelectedItem().toString(), minutoFinAsignacion.getSelectedItem().toString()));
                            if(valorFechaF<0){
                                fechaFin_Asignacion_Equipo=anoF+"-"+mesF+"-"+diaF+" "+horaFinAsignacion.getSelectedItem().toString()+":"+minutoFinAsignacion.getSelectedItem().toString()+":00.0";
                                int resultDosFechas=Integer.parseInt(new ControlDB_Equipo().comparadorEntreDosFechas(anoI, mesI, diaI, horaInicioAsignacion.getSelectedItem().toString(), minutoInicioAsignacion.getSelectedItem().toString(), anoF, mesF, diaF, horaFinAsignacion.getSelectedItem().toString(), minutoFinAsignacion.getSelectedItem().toString()));
                                if(resultDosFechas < 0){
                                    JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser mayor a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                                }else{
                                    if(resultDosFechas ==0){
                                        JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser Igual a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                                    }else{
                                        //Validamos si el equipo ya se encuentra programado
                                        if(new ControlDB_AsignacionEquipo().Asignacion_validarDisponibilidadEquipo(equipoAsignacion, fechaInicio_Asignacion_Equipo, fechaFin_Asignacion_Equipo)){//El equipo no se encuentra asignado en ese horario
                                            JOptionPane.showMessageDialog(null,"El equipo ya se encuentra programado en ese rango de fecha", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                        }else{//El equipo no se encuentra asignado en ese horario
                                            AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                                            asignacionEquipo.setSolicitudListadoEquipo(solicitudListadoEquipo);
                                            asignacionEquipo.setCentroOperacion(solicitudEquipo.getCentroOperacion());
                                            asignacionEquipo.setFechaHoraInicio(fechaInicio_Asignacion_Equipo);
                                            asignacionEquipo.setFechaHoraFin(fechaFin_Asignacion_Equipo);
                                            float cantHorasProgramadas=0;
                                            cantHorasProgramadas=new ControlDB_AsignacionEquipo().cantidadHorasProgramadas(anoI, mesI, diaI, horaInicioAsignacion.getSelectedItem().toString(), minutoInicioAsignacion.getSelectedItem().toString(), anoF, mesF, diaF, horaFinAsignacion.getSelectedItem().toString(), minutoFinAsignacion.getSelectedItem().toString());
                                            if(cantHorasProgramadas !=0){
                                                asignacionEquipo.setCantidadHorasProgramadas(""+cantHorasProgramadas);
                                                asignacionEquipo.setEquipo(equipoAsignacion);
                                                asignacionEquipo.setPertenencia(equipoAsignacion.getPertenenciaEquipo());
                                                asignacionEquipo.setEstado("1");
                                                if(validarCargueEquiposInternos(equipoAsignacion)){
                                                    JOptionPane.showMessageDialog(null, "Error!!.. El equipo ya se encuentra cargado, verifique datos","Advertencia", JOptionPane.ERROR_MESSAGE );
                                                }else{
                                                    listadoAsignacionEquipo_Temp.add(asignacionEquipo);
                                                    DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"C.O","Equipo_Código","Equipo_Tipo", "Equipo_Marca","Equipo_Modelo","Equipo_Descripción","Fecha_Y_Hora_Inicio","Fecha_Y_Hora_Fin", "CantidadHorasProgramadas"});
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
                                                        registro[8] = "" + AsignacionEquipo1.getCantidadHorasProgramadas();
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
        }*/
    }//GEN-LAST:event_Cronograma_registrarActionPerformed

    private void select_SubcentroCostoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_SubcentroCostoItemStateChanged
        if(listadoCentroCostoSubCentro != null){
            try {
                listadoCentroCostoAuxiliar = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro.get(select_SubcentroCosto.getSelectedIndex()).getCodigo());
                if(listadoCentroCostoAuxiliar != null){
                    String datosObjeto[] = new String[listadoCentroCostoAuxiliar.size()];
                    int contador = 0;
                    for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliar) {
                        datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_CentroCostoAuxiliar.setModel(model);
                }else{
                    select_CentroCostoAuxiliar.removeAllItems();
                }
            }catch (SQLException e){ 
            }
        }else{
            select_SubcentroCosto.removeAllItems();
        }
    }//GEN-LAST:event_select_SubcentroCostoItemStateChanged

    private void Cronograma_jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cronograma_jLabel4MouseClicked
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

    private void select_centroOperacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_centroOperacionItemStateChanged
               
        //Cargamos en la interfaz los subcentro de costos activos
        try {
            if(listadoCentroOperacion !=null){
                listadoCentroCostoSubCentro=new ControlDB_CentroCostoSubCentro(tipoConexion).buscarActivos(listadoCentroOperacion.get(select_centroOperacion.getSelectedIndex()));
                if(listadoCentroCostoSubCentro != null){
                    String datosObjeto[]= new String[listadoCentroCostoSubCentro.size()];
                    int contador=0;
                    for(CentroCostoSubCentro Objeto : listadoCentroCostoSubCentro){ 
                        datosObjeto[contador]=Objeto.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_SubcentroCosto.setModel(model);
                }else{
                    select_SubcentroCosto.removeAllItems();
                }
            }else{
                select_centroOperacion.removeAllItems();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
        //Cargaos los auxiliares
        if(listadoCentroCostoSubCentro != null){
            try {
                listadoCentroCostoAuxiliar = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro.get(select_SubcentroCosto.getSelectedIndex()).getCodigo());
                if(listadoCentroCostoAuxiliar != null){
                    String datosObjeto[] = new String[listadoCentroCostoAuxiliar.size()];
                    int contador = 0;
                    for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliar) {
                        datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_CentroCostoAuxiliar.setModel(model);
                }else{
                    select_CentroCostoAuxiliar.removeAllItems();
                } 
            }catch (SQLException e){ 
            } 
        }else{
            select_SubcentroCosto.removeAllItems();
            select_CentroCostoAuxiliar.removeAllItems();
        } 
    }//GEN-LAST:event_select_centroOperacionItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Cronograma_fechaFinAsignacion;
    private com.toedter.calendar.JDateChooser Cronograma_fechaInicioAsignacion;
    private javax.swing.JComboBox<String> Cronograma_horaFinAsignacion;
    private javax.swing.JComboBox<String> Cronograma_horaInicioAsignacion;
    private javax.swing.JLabel Cronograma_jLabel10;
    private javax.swing.JLabel Cronograma_jLabel11;
    private javax.swing.JLabel Cronograma_jLabel13;
    private javax.swing.JLabel Cronograma_jLabel14;
    private javax.swing.JLabel Cronograma_jLabel15;
    private javax.swing.JLabel Cronograma_jLabel16;
    private javax.swing.JLabel Cronograma_jLabel26;
    private javax.swing.JLabel Cronograma_jLabel27;
    private javax.swing.JLabel Cronograma_jLabel4;
    private javax.swing.JSeparator Cronograma_jSeparator4;
    private javax.swing.JComboBox<String> Cronograma_minutoFinAsignacion;
    private javax.swing.JComboBox<String> Cronograma_minutoInicioAsignacion;
    private javax.swing.JButton Cronograma_registrar;
    private javax.swing.JLabel Cronograma_timeFin;
    private javax.swing.JLabel Cronograma_timeInicio;
    private javax.swing.JLabel Cronograma_titulo33;
    private javax.swing.JLabel Cronograma_titulo38;
    private javax.swing.JRadioButton check_Subcentro;
    private javax.swing.JRadioButton check_auxCentroCosto;
    private javax.swing.JRadioButton check_centroOperacion;
    private javax.swing.JRadioButton check_compania;
    private javax.swing.JRadioButton check_equipo;
    private javax.swing.JRadioButton check_marca;
    private javax.swing.JRadioButton check_modelo;
    private javax.swing.JRadioButton check_tipoEquipo;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JComboBox<String> select_CentroCostoAuxiliar;
    private javax.swing.JComboBox<String> select_Compañia;
    private javax.swing.JComboBox<String> select_Equipos;
    private javax.swing.JComboBox<String> select_Marca;
    private javax.swing.JComboBox<String> select_Modelo;
    private javax.swing.JComboBox<String> select_SubcentroCosto;
    private javax.swing.JComboBox<String> select_Tipo;
    private javax.swing.JComboBox<String> select_centroOperacion;
    private javax.swing.JTable tabla_ListadoAsignacion;
    // End of variables declaration//GEN-END:variables
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
