package ModuloEquipo.View;

import Catalogo.Controller.ControlDB_CentroCostoSubCentro;
import Catalogo.Controller.ControlDB_CentroOperacion;
import Catalogo.Controller.ControlDB_Motonave;
import ModuloCarbon.Controller2.ControlDB_MvtoCarbon;
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import Catalogo.Model.Motonave;
import Catalogo.Controller.ControlDB_Compañia;
import Catalogo.Controller.ControlDB_Equipo;
import ModuloEquipo.Controller.ControlDB_EstadoSolicitudEquipos;
import Catalogo.Controller.ControlDB_LaborRealizada;
import ModuloEquipo.Controller.ControlDB_SolicitudEquipo;
import Catalogo.Controller.ControlDB_TipoEquipo;
import Catalogo.Model.Compañia;
import Catalogo.Model.Equipo;
import ModuloEquipo.Model.EstadoSolicitudEquipos;
import Catalogo.Model.LaborRealizada;
import ModuloEquipo.Model.SolicitudEquipo;
import ModuloEquipo.Model.SolicitudListadoEquipo;
import Catalogo.Model.TipoEquipo;
import Correos.mail;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Solicitud_Equipos_Registrar extends javax.swing.JPanel {  
    private Usuario user;
    private ArrayList<SolicitudListadoEquipo> ListadoSolicitudesEquipos=new ArrayList<>();
    private ArrayList<EstadoSolicitudEquipos> listadoEstadoSolicitud= new ArrayList();
    private ArrayList<Motonave> listadoMotonave=new ArrayList<>();
    private Motonave motonave=null;
    private ArrayList<CentroOperacion> listadoCentroOperacion = new ArrayList();
    private ArrayList<String> listadoMarcaEquipo =new ArrayList<>();
    private ArrayList<String> listadoTiposEquipo = new ArrayList();
    private ArrayList<String> listadoModelosEquipo = new ArrayList();
    private ArrayList<Equipo> listadoEquipo = new ArrayList();
    private ArrayList<CentroCostoSubCentro> listadoCentroCostoSubCentro = new ArrayList();
    private ArrayList<LaborRealizada> listadoLaborRealizada = new ArrayList();
    private ArrayList<CentroCostoAuxiliar> listadoCentroCostoAuxiliar = new ArrayList();
    private ArrayList<Compañia> listadoCompañia = new ArrayList();
    private String tipoConexion;
     
    public Solicitud_Equipos_Registrar(Usuario us,String tipoConexion) {   
        initComponents();
        user = us;
        this.tipoConexion= tipoConexion;
        Usuario_codigo.setText("Cedula: "+user.getCodigo());
        usuario_nombre.setText("Nombre: "+user.getNombres()+" "+ user.getApellidos());
        
        InternalFrame_sugerirEquipo.getContentPane().setBackground(Color.WHITE);
        InternalFrameSelectorEquipo3.getContentPane().setBackground(Color.WHITE);
        InternaFrame_buscarMotonave.getContentPane().setBackground(Color.WHITE);
        
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
        //Cargamos en la interfaz los estados de Solicitudes de Equipos
        try {
            listadoEstadoSolicitud=new ControlDB_EstadoSolicitudEquipos(tipoConexion).buscarActivos();
            if(listadoEstadoSolicitud != null){
                String datosObjeto[]= new String[listadoEstadoSolicitud.size()];
                int contador=0;
                for(EstadoSolicitudEquipos listadoEstadoSolicitud1 : listadoEstadoSolicitud){ 
                    datosObjeto[contador]=listadoEstadoSolicitud1.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                estadoSolicitud.setModel(model);
            }else{
                estadoSolicitud.removeAllItems();
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
        estadoSolicitud.setSelectedIndex(0);
        estadoSolicitud.setEnabled(false);
        
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
            }else{
                selectTipo.removeAllItems();
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
                }else{
                    selectMarca.removeAllItems();
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
                    //Cargamos el selector de Cantidades
                    int cantidadModelos=new ControlDB_Equipo(tipoConexion).contarCantidadesEquiposEnAplicacionInterna(selectTipo.getSelectedItem().toString(),selectMarca.getSelectedItem().toString(), selectModelo.getSelectedItem().toString());
                    String dataCantidadesModelos[]= new String[cantidadModelos];
                    for(int i = 0; i< cantidadModelos; i++){
                        dataCantidadesModelos[i]=""+(i+1);   
                    }    
                    final DefaultComboBoxModel modelCantidadesModelos = new DefaultComboBoxModel(dataCantidadesModelos);
                    selectCantidad.setModel(modelCantidadesModelos);
                    
                    //Cargamos el selector de Equipos
                    listadoEquipo=new ControlDB_Equipo(tipoConexion).Asignacion_buscarEquiposEnAplicacionInterna(selectTipo.getSelectedItem().toString(),selectMarca.getSelectedItem().toString(), selectModelo.getSelectedItem().toString());              
                    String dataEquipo[]= new String[listadoEquipo.size()];
                    for(int i = 0; i< dataEquipo.length; i++){
                        dataEquipo[i]=""+(listadoEquipo.get(i).getCodigo()+" "+listadoEquipo.get(i).getDescripcion()+ " "+ listadoEquipo.get(i).getModelo());   
                    }    
                    final DefaultComboBoxModel modelListadoEquipos = new DefaultComboBoxModel(dataEquipo);
                    selectEquipos.setModel(modelListadoEquipos);
                    
                }else{
                    selectCantidad.removeAllItems();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
        //Cargamos las horas
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
      
        /*//Cargamos en la interfaz las motonaves activas en el sistema
        try {
            listadoMotonave=new ControlDB_Motonave(tipoConexion).buscarActivos();
            if(listadoMotonave != null){
                String datosObjeto[]= new String[listadoMotonave.size()];
                int contador=0;
                for(Motonave Objeto : listadoMotonave){ 
                    datosObjeto[contador]=Objeto.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                selectMotonave.setModel(model);
            }else{
                selectMotonave.removeAllItems();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }  */
        
     
        
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
        checkMotonave.setSelected(false);
        InternalFrame_sugerirEquipo.show(false);
        InternalFrameSelectorEquipo3.show(false);
        InternaFrame_buscarMotonave.show(false);
        icon_buscarMotonave.show(false);
        //selectMotonave.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        EliminarS = new javax.swing.JPopupMenu();
        EliminarSolicitud = new javax.swing.JMenuItem();
        InternalFrame_sugerirEquipo = new javax.swing.JInternalFrame();
        titulo58 = new javax.swing.JLabel();
        selectEquipos = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        equipoRecomendado = new javax.swing.JTextPane();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        titulo59 = new javax.swing.JLabel();
        InternaFrame_buscarMotonave = new javax.swing.JInternalFrame();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        valorBusqueda = new javax.swing.JTextField();
        btnConsultar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        InternalFrameSelectorEquipo3 = new javax.swing.JInternalFrame();
        selectTipo = new javax.swing.JComboBox<>();
        selectMarca = new javax.swing.JComboBox<>();
        titulo37 = new javax.swing.JLabel();
        titulo38 = new javax.swing.JLabel();
        selectCantidad = new javax.swing.JComboBox<>();
        titulo39 = new javax.swing.JLabel();
        fechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        horaInicio = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        minutoInicio = new javax.swing.JComboBox<>();
        horarioTiempoInicioSolicitudEquiposRegistrar = new javax.swing.JLabel();
        fechaFin = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        horaFin = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        minutoFin = new javax.swing.JComboBox<>();
        horarioTiempoIFinalSolicitudEquiposRegistrar = new javax.swing.JLabel();
        titulo40 = new javax.swing.JLabel();
        selectModelo = new javax.swing.JComboBox<>();
        titulo41 = new javax.swing.JLabel();
        checkMotonave = new javax.swing.JRadioButton();
        selectLaborRealizada = new javax.swing.JComboBox<>();
        selectSubcentroCosto = new javax.swing.JComboBox<>();
        titulo20 = new javax.swing.JLabel();
        titulo34 = new javax.swing.JLabel();
        selectCentroCostoAuxiliar = new javax.swing.JComboBox<>();
        label_motonave = new javax.swing.JLabel();
        selectCompañia = new javax.swing.JComboBox<>();
        icon_buscarMotonave = new javax.swing.JLabel();
        agregar = new javax.swing.JButton();
        titulo43 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        observacion = new javax.swing.JTextArea();
        titulo44 = new javax.swing.JLabel();
        titulo42 = new javax.swing.JLabel();
        Cronograma_jSeparator8 = new javax.swing.JSeparator();
        titulo45 = new javax.swing.JLabel();
        Cronograma_jSeparator9 = new javax.swing.JSeparator();
        titulo46 = new javax.swing.JLabel();
        Cronograma_jSeparator10 = new javax.swing.JSeparator();
        Cronograma_jSeparator11 = new javax.swing.JSeparator();
        Cronograma_jSeparator12 = new javax.swing.JSeparator();
        Cronograma_jSeparator13 = new javax.swing.JSeparator();
        Cronograma_jSeparator14 = new javax.swing.JSeparator();
        Cronograma_jSeparator15 = new javax.swing.JSeparator();
        Cronograma_jSeparator16 = new javax.swing.JSeparator();
        titulo47 = new javax.swing.JLabel();
        titulo48 = new javax.swing.JLabel();
        titulo49 = new javax.swing.JLabel();
        centroOperacion = new javax.swing.JComboBox<>();
        titulo33 = new javax.swing.JLabel();
        titulo28 = new javax.swing.JLabel();
        titulo30 = new javax.swing.JLabel();
        fechaSolicitud = new javax.swing.JLabel();
        estadoSolicitud = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        titulo35 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        titulo36 = new javax.swing.JLabel();
        Usuario_codigo = new javax.swing.JLabel();
        usuario_nombre = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        titulo6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        registrar = new javax.swing.JButton();
        Cronograma_jSeparator4 = new javax.swing.JSeparator();
        Cronograma_jSeparator5 = new javax.swing.JSeparator();
        Cronograma_jSeparator6 = new javax.swing.JSeparator();
        Cronograma_jSeparator7 = new javax.swing.JSeparator();

        Editar.setText("Modificar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        EliminarSolicitud.setText("Eliminar");
        EliminarSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarSolicitudActionPerformed(evt);
            }
        });
        EliminarS.add(EliminarSolicitud);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InternalFrame_sugerirEquipo.setClosable(true);
        InternalFrame_sugerirEquipo.setVisible(false);
        InternalFrame_sugerirEquipo.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo58.setForeground(new java.awt.Color(51, 51, 51));
        titulo58.setText("SUGERIR EQUIPOS AL ASIGNADOR DE EQUIPOS (PROGRAMADOR)");
        InternalFrame_sugerirEquipo.getContentPane().add(titulo58, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 760, 30));

        selectEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectEquiposActionPerformed(evt);
            }
        });
        InternalFrame_sugerirEquipo.getContentPane().add(selectEquipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 490, 40));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/anular.png"))); // NOI18N
        jButton1.setText("BORRAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        InternalFrame_sugerirEquipo.getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, 140, 40));

        equipoRecomendado.setEditable(false);
        jScrollPane4.setViewportView(equipoRecomendado);

        InternalFrame_sugerirEquipo.getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 880, 190));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Ingresar.png"))); // NOI18N
        jButton2.setText("CARGAR SUGERENCIA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        InternalFrame_sugerirEquipo.getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 260, 40));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Ingresar.png"))); // NOI18N
        jButton3.setText("SUGERIR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        InternalFrame_sugerirEquipo.getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, 140, 40));

        titulo59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo59.setForeground(new java.awt.Color(51, 51, 51));
        titulo59.setText("Equipo:");
        InternalFrame_sugerirEquipo.getContentPane().add(titulo59, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 70, 30));

        add(InternalFrame_sugerirEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1280, 610));

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
        jScrollPane3.setViewportView(tabla1);

        InternaFrame_buscarMotonave.getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 1310, 640));
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

        InternalFrameSelectorEquipo3.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrameSelectorEquipo3.setClosable(true);
        InternalFrameSelectorEquipo3.setVisible(false);
        InternalFrameSelectorEquipo3.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        InternalFrameSelectorEquipo3.getContentPane().add(selectTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 390, 30));

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
        InternalFrameSelectorEquipo3.getContentPane().add(selectMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 170, 380, 30));

        titulo37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo37.setForeground(new java.awt.Color(102, 102, 102));
        titulo37.setText("TIPO:");
        InternalFrameSelectorEquipo3.getContentPane().add(titulo37, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 170, 30));

        titulo38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo38.setForeground(new java.awt.Color(102, 102, 102));
        titulo38.setText("MARCA:");
        InternalFrameSelectorEquipo3.getContentPane().add(titulo38, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 170, 140, 30));

        selectCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectCantidadActionPerformed(evt);
            }
        });
        InternalFrameSelectorEquipo3.getContentPane().add(selectCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 210, 80, 30));

        titulo39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo39.setForeground(new java.awt.Color(102, 102, 102));
        titulo39.setText("MODELO:");
        InternalFrameSelectorEquipo3.getContentPane().add(titulo39, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 180, 30));

        fechaInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicioMouseEntered(evt);
            }
        });
        InternalFrameSelectorEquipo3.getContentPane().add(fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 170, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("INICIO:");
        InternalFrameSelectorEquipo3.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 70, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Hora");
        InternalFrameSelectorEquipo3.getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, -1, 30));

        horaInicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                horaInicioItemStateChanged(evt);
            }
        });
        InternalFrameSelectorEquipo3.getContentPane().add(horaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 50, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText(":");
        InternalFrameSelectorEquipo3.getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 20, 30));

        minutoInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoInicioActionPerformed(evt);
            }
        });
        InternalFrameSelectorEquipo3.getContentPane().add(minutoInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, 50, 30));

        horarioTiempoInicioSolicitudEquiposRegistrar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoInicioSolicitudEquiposRegistrar.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoInicioSolicitudEquiposRegistrar.setText("AM");
        InternalFrameSelectorEquipo3.getContentPane().add(horarioTiempoInicioSolicitudEquiposRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, 40, 30));

        fechaFin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFinMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFinMouseEntered(evt);
            }
        });
        InternalFrameSelectorEquipo3.getContentPane().add(fechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 80, 170, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("FINALIZACIÓN:");
        InternalFrameSelectorEquipo3.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, 110, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Hora");
        InternalFrameSelectorEquipo3.getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 80, -1, 30));

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
        InternalFrameSelectorEquipo3.getContentPane().add(horaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 80, 50, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel24.setText(":");
        InternalFrameSelectorEquipo3.getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 80, 20, 30));

        minutoFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoFinActionPerformed(evt);
            }
        });
        InternalFrameSelectorEquipo3.getContentPane().add(minutoFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 80, 50, 30));

        horarioTiempoIFinalSolicitudEquiposRegistrar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoIFinalSolicitudEquiposRegistrar.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoIFinalSolicitudEquiposRegistrar.setText("AM");
        InternalFrameSelectorEquipo3.getContentPane().add(horarioTiempoIFinalSolicitudEquiposRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 80, 50, 30));

        titulo40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo40.setForeground(new java.awt.Color(102, 102, 102));
        titulo40.setText("CANTIDAD:");
        InternalFrameSelectorEquipo3.getContentPane().add(titulo40, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 210, 130, 30));

        selectModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectModeloActionPerformed(evt);
            }
        });
        InternalFrameSelectorEquipo3.getContentPane().add(selectModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 390, 30));

        titulo41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo41.setForeground(new java.awt.Color(102, 102, 102));
        titulo41.setText("ACTIVIDAD A REALIZAR:");
        InternalFrameSelectorEquipo3.getContentPane().add(titulo41, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 180, 30));

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
        InternalFrameSelectorEquipo3.getContentPane().add(checkMotonave, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 340, 120, 30));

        selectLaborRealizada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectLaborRealizadaActionPerformed(evt);
            }
        });
        InternalFrameSelectorEquipo3.getContentPane().add(selectLaborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, 400, 30));

        selectSubcentroCosto.setToolTipText("");
        selectSubcentroCosto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectSubcentroCostoItemStateChanged(evt);
            }
        });
        InternalFrameSelectorEquipo3.getContentPane().add(selectSubcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, 400, 30));

        titulo20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo20.setForeground(new java.awt.Color(102, 102, 102));
        titulo20.setText("SUBCENTRO DE COSTO:");
        InternalFrameSelectorEquipo3.getContentPane().add(titulo20, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 180, 30));

        titulo34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo34.setForeground(new java.awt.Color(102, 102, 102));
        titulo34.setText("OBSERVACIÓN:");
        InternalFrameSelectorEquipo3.getContentPane().add(titulo34, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 180, 20));

        selectCentroCostoAuxiliar.setToolTipText("");
        InternalFrameSelectorEquipo3.getContentPane().add(selectCentroCostoAuxiliar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, 400, 30));

        label_motonave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_motonave.setForeground(new java.awt.Color(51, 51, 51));
        InternalFrameSelectorEquipo3.getContentPane().add(label_motonave, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 340, 360, 30));

        selectCompañia.setToolTipText("");
        InternalFrameSelectorEquipo3.getContentPane().add(selectCompañia, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 300, 400, 30));

        icon_buscarMotonave.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        icon_buscarMotonave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        icon_buscarMotonave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_buscarMotonaveMouseClicked(evt);
            }
        });
        InternalFrameSelectorEquipo3.getContentPane().add(icon_buscarMotonave, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 340, 30, 30));

        agregar.setBackground(new java.awt.Color(255, 255, 255));
        agregar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Icono_añadir.png"))); // NOI18N
        agregar.setText("AGREGAR SOLICITUD");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });
        InternalFrameSelectorEquipo3.getContentPane().add(agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 550, 240, 40));

        titulo43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo43.setForeground(new java.awt.Color(102, 102, 102));
        titulo43.setText("CENTROCOSTO AUXILIAR:");
        InternalFrameSelectorEquipo3.getContentPane().add(titulo43, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 170, 30));

        observacion.setColumns(20);
        observacion.setRows(5);
        jScrollPane1.setViewportView(observacion);

        InternalFrameSelectorEquipo3.getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 420, 940, -1));

        titulo44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo44.setForeground(new java.awt.Color(102, 102, 102));
        titulo44.setText("COMPAÑIA:");
        InternalFrameSelectorEquipo3.getContentPane().add(titulo44, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 300, 130, 30));

        titulo42.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo42.setForeground(new java.awt.Color(0, 102, 102));
        titulo42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo42.setText("AGREGAR EQUIPO");
        titulo42.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorEquipo3.getContentPane().add(titulo42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1290, 30));
        InternalFrameSelectorEquipo3.getContentPane().add(Cronograma_jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 1290, 10));

        titulo45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo45.setForeground(new java.awt.Color(0, 102, 102));
        titulo45.setText("EQUIPOS");
        InternalFrameSelectorEquipo3.getContentPane().add(titulo45, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 170, 30));
        InternalFrameSelectorEquipo3.getContentPane().add(Cronograma_jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 1290, 10));

        titulo46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo46.setForeground(new java.awt.Color(0, 102, 102));
        titulo46.setText("SITIO  Y LABOR DE TRABAJO:");
        InternalFrameSelectorEquipo3.getContentPane().add(titulo46, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 600, 30));
        InternalFrameSelectorEquipo3.getContentPane().add(Cronograma_jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 1270, 10));

        Cronograma_jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternalFrameSelectorEquipo3.getContentPane().add(Cronograma_jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 20, 500));
        InternalFrameSelectorEquipo3.getContentPane().add(Cronograma_jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 1290, 30));
        InternalFrameSelectorEquipo3.getContentPane().add(Cronograma_jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 1290, 30));
        InternalFrameSelectorEquipo3.getContentPane().add(Cronograma_jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 1270, 30));
        InternalFrameSelectorEquipo3.getContentPane().add(Cronograma_jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 1290, 30));

        Cronograma_jSeparator16.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternalFrameSelectorEquipo3.getContentPane().add(Cronograma_jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 40, 20, 500));

        titulo47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo47.setForeground(new java.awt.Color(0, 102, 102));
        titulo47.setText("FECHA PROGRAMACIÓN:");
        InternalFrameSelectorEquipo3.getContentPane().add(titulo47, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 210, 30));

        titulo48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo48.setForeground(new java.awt.Color(102, 102, 102));
        titulo48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/sugerir_equipo2.png"))); // NOI18N
        titulo48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                titulo48MouseClicked(evt);
            }
        });
        InternalFrameSelectorEquipo3.getContentPane().add(titulo48, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 210, 40, 40));

        titulo49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo49.setForeground(new java.awt.Color(102, 102, 102));
        titulo49.setText("SUGERIR EQUIPO:");
        InternalFrameSelectorEquipo3.getContentPane().add(titulo49, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 210, 110, 40));

        add(InternalFrameSelectorEquipo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 680));

        centroOperacion.setToolTipText("");
        centroOperacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                centroOperacionItemStateChanged(evt);
            }
        });
        add(centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 330, 30));

        titulo33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo33.setForeground(new java.awt.Color(102, 102, 102));
        titulo33.setText("SOLICITUD DE EQUIPOS");
        add(titulo33, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 250, 30));

        titulo28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo28.setForeground(new java.awt.Color(51, 51, 51));
        titulo28.setText("Centro Operación:");
        add(titulo28, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 130, 30));

        titulo30.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        titulo30.setForeground(new java.awt.Color(51, 51, 51));
        titulo30.setText("Agregar Equipo");
        add(titulo30, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 100, 20));

        fechaSolicitud.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        fechaSolicitud.setForeground(new java.awt.Color(51, 51, 51));
        fechaSolicitud.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(fechaSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 220, 30));

        estadoSolicitud.setToolTipText("");
        add(estadoSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 330, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/agregar.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, 80, 70));

        titulo35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo35.setForeground(new java.awt.Color(51, 51, 51));
        titulo35.setText("Estado Solicitud:");
        add(titulo35, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 130, 30));

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
        tabla.setComponentPopupMenu(EliminarS);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 1270, 440));

        titulo36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo36.setForeground(new java.awt.Color(51, 51, 51));
        titulo36.setText("Fecha:");
        add(titulo36, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 110, 30));

        Usuario_codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Usuario_codigo.setForeground(new java.awt.Color(51, 51, 51));
        add(Usuario_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 90, 510, 30));

        usuario_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        usuario_nombre.setForeground(new java.awt.Color(51, 51, 51));
        add(usuario_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 120, 510, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/login.png"))); // NOI18N
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, 70, 70));

        titulo6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo6.setForeground(new java.awt.Color(51, 153, 0));
        titulo6.setText("Usuario Quien Realiza Solicitud");
        add(titulo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, 220, 30));

        jLabel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 153, 153), null, null));
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, 630, 90));

        registrar.setBackground(new java.awt.Color(255, 255, 255));
        registrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Ingresar.png"))); // NOI18N
        registrar.setText("REGISTRAR");
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });
        add(registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 630, 150, 40));
        add(Cronograma_jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 1270, 10));
        add(Cronograma_jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 1270, 10));

        Cronograma_jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(Cronograma_jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 30, 30, 130));

        Cronograma_jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(Cronograma_jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 20, 130));
    }// </editor-fold>//GEN-END:initComponents

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        /*if (evt.getClickCount() == 2) {
            // TODO lo del clik derechoo
            int fila1;
            try{
                fila1=tablaAnadirVehiculo.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ningun Vehiculo");
                }
                else{
                    DefaultTableModel modelo=(DefaultTableModel)tablaAnadirVehiculo.getModel();
                    mvtoCarbon= listadoVehiculosAnadir.get(fila1);
                    //JOptionPane.showMessageDialog(null, ""+mvtoCarbon.getPlaca());
                    System.out.println("Seleccionamos "+fila1+"->"+ mvtoCarbon.getPlaca());
                    cedulaRegistroCargue.setText("Código: "+user.getCodigo());
                    nombreRegistroCargue.setText("Nombre: "+user.getNombres()+" "+user.getApellidos());
                    if(destarado){
                        estadoRegistroCargue.setText("Destarado");
                    }
                    if(tarado){
                        estadoRegistroCargue.setText("Tarado");
                    }
                    placaRegistroCargue.setText(mvtoCarbon.getPlaca());
                    ordenRegistroCargue.setText(mvtoCarbon.getOrden());
                    depositoRegistroCargue.setText(mvtoCarbon.getDeposito());
                    fechaEntradaRegistroCargue.setText(mvtoCarbon.getFechaEntrada());
                    fechaSalidaRegistroCargue.setText(mvtoCarbon.getFechaSalida());
                    pesoLlenoRegistroCargue.setText(mvtoCarbon.getPesoLleno());
                    pesoVacioRegistroCargue.setText(mvtoCarbon.getPesoVacio());
                    pesoNetoRegistroCargue.setText(mvtoCarbon.getPesoNeto());
                    clienteRegistroCargue.setText(mvtoCarbon.getCliente().getDescripcion());
                    transportadoraRegistroCargue.setText(mvtoCarbon.getTransportadora().getDescripcion());
                    InternaFrameRegistroCargue.show(true);
                    InternaFrameAnadirVehiculo.show(false);
                }
            }catch(Exception e){
            }
        }*/
    }//GEN-LAST:event_tablaMouseClicked

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
            }else{
                selectMarca.removeAllItems();
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
                
                observacion.setText("");
            }else{
                selectModelo.removeAllItems();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            selectCantidad.removeAllItems();
            int cantidadModelos;
            cantidadModelos = new ControlDB_Equipo(tipoConexion).contarCantidadesEquiposEnAplicacionInterna(selectTipo.getSelectedItem().toString(),selectMarca.getSelectedItem().toString(), selectModelo.getSelectedItem().toString());
            String dataCantidadesModelos[]= new String[cantidadModelos];
            for(int i = 0; i< cantidadModelos; i++){
                dataCantidadesModelos[i]=""+(i+1);    
            }    
            final DefaultComboBoxModel modelCantidadesModelos = new DefaultComboBoxModel(dataCantidadesModelos);
            selectCantidad.setModel(modelCantidadesModelos);
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_selectTipoActionPerformed

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
                    
                    
                    
                    selectCantidad.removeAllItems();
                    //Cargamos el selector de Cantidades
                    int cantidadModelos=new ControlDB_Equipo(tipoConexion).contarCantidadesEquiposEnAplicacionInterna(selectTipo.getSelectedItem().toString(),selectMarca.getSelectedItem().toString(), selectModelo.getSelectedItem().toString());
                    String dataCantidadesModelos[]= new String[cantidadModelos];
                    for(int i = 0; i< cantidadModelos; i++){
                        dataCantidadesModelos[i]=""+(i+1);   
                    }    
                    final DefaultComboBoxModel modelCantidadesModelos = new DefaultComboBoxModel(dataCantidadesModelos);
                    selectCantidad.setModel(modelCantidadesModelos);
                    
                    //Cargamos el selector de Equipos
                    listadoEquipo=new ControlDB_Equipo(tipoConexion).Asignacion_buscarEquiposEnAplicacionInterna(selectTipo.getSelectedItem().toString(),selectMarca.getSelectedItem().toString(), selectModelo.getSelectedItem().toString());              
                    String dataEquipo[]= new String[listadoEquipo.size()];
                    for(int i = 0; i< dataEquipo.length; i++){
                        //dataEquipo[i]=""+(listadoEquipo.get(i).getDescripcion());  
                        dataEquipo[i]=""+(listadoEquipo.get(i).getCodigo()+" "+listadoEquipo.get(i).getDescripcion());
                    }    
                    final DefaultComboBoxModel modelListadoEquipos = new DefaultComboBoxModel(dataEquipo);
                    selectEquipos.setModel(modelListadoEquipos);
                    observacion.setText("");
                    
                }else{
                    selectModelo.removeAllItems();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_selectMarcaActionPerformed

    private void selectCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectCantidadActionPerformed

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

    private void selectModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectModeloActionPerformed
        //Cargamos el selector de Cantidades
        try {
            selectCantidad.removeAllItems();
            int cantidadModelos;
            cantidadModelos = new ControlDB_Equipo(tipoConexion).contarCantidadesEquiposEnAplicacionInterna(selectTipo.getSelectedItem().toString(),selectMarca.getSelectedItem().toString(), selectModelo.getSelectedItem().toString());
            String dataCantidadesModelos[]= new String[cantidadModelos];
            for(int i = 0; i< cantidadModelos; i++){
                dataCantidadesModelos[i]=""+(i+1);    
            }    
            final DefaultComboBoxModel modelCantidadesModelos = new DefaultComboBoxModel(dataCantidadesModelos);
            selectCantidad.setModel(modelCantidadesModelos);
            
            //Cargamos el selector de Equipos
            listadoEquipo=new ControlDB_Equipo(tipoConexion).Asignacion_buscarEquiposEnAplicacionInterna(selectTipo.getSelectedItem().toString(),selectMarca.getSelectedItem().toString(), selectModelo.getSelectedItem().toString());              
            String dataEquipo[]= new String[listadoEquipo.size()];
            for(int i = 0; i< dataEquipo.length; i++){
                dataEquipo[i]=""+(listadoEquipo.get(i).getCodigo()+" "+listadoEquipo.get(i).getDescripcion()+ " "+ listadoEquipo.get(i).getModelo());   
            }    
            final DefaultComboBoxModel modelListadoEquipos = new DefaultComboBoxModel(dataEquipo);
            selectEquipos.setModel(modelListadoEquipos);
            observacion.setText("");
            
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_selectModeloActionPerformed

    private void selectLaborRealizadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectLaborRealizadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectLaborRealizadaActionPerformed

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

    private void selectTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectTipoItemStateChanged

    }//GEN-LAST:event_selectTipoItemStateChanged

    private void selectMarcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectMarcaItemStateChanged
        
      
    }//GEN-LAST:event_selectMarcaItemStateChanged

    private void selectTipoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectTipoMouseExited
       
    }//GEN-LAST:event_selectTipoMouseExited

    private void selectMarcaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMarcaMouseExited
        
    }//GEN-LAST:event_selectMarcaMouseExited

    private void selectTipoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectTipoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_selectTipoMouseEntered

    private void selectTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectTipoMouseClicked
        
    }//GEN-LAST:event_selectTipoMouseClicked

    private void selectMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMarcaMouseClicked
        
    }//GEN-LAST:event_selectMarcaMouseClicked

    private void selectTipoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectTipoMousePressed
       
    }//GEN-LAST:event_selectTipoMousePressed

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

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        if(listadoTiposEquipo !=null){
            if(listadoMarcaEquipo != null){
                if(listadoModelosEquipo != null){
                    //Cargamos la fecha de inicio de movimientos de la solicitud
                    String fechaInicio_Solicitud_Equipo="";
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
                        try{
                            int valorFechaI=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorFechaActual(anoI, mesI, diaI, horaInicio.getSelectedItem().toString(), minutoInicio.getSelectedItem().toString()));
                            if(valorFechaI<0){   
                                fechaInicio_Solicitud_Equipo=anoI+"-"+mesI+"-"+diaI+" "+horaInicio.getSelectedItem().toString()+":"+minutoInicio.getSelectedItem().toString()+":00.0";
                                //Cargamos la fecha de inicio de movimientos de la solicitud
                                String fechaFin_Solicitud_Equipo="";
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
                                    try{
                                        int valorFechaF=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorFechaActual(anoF, mesF, diaF, horaFin.getSelectedItem().toString(), minutoFin.getSelectedItem().toString()));
                                        if(valorFechaF<0){
                                            fechaFin_Solicitud_Equipo=anoF+"-"+mesF+"-"+diaF+" "+horaFin.getSelectedItem().toString()+":"+minutoFin.getSelectedItem().toString()+":00.0";
                                            int resultDosFechas=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorEntreDosFechas(anoI, mesI, diaI, horaInicio.getSelectedItem().toString(), minutoInicio.getSelectedItem().toString(), anoF, mesF, diaF, horaFin.getSelectedItem().toString(), minutoFin.getSelectedItem().toString()));
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
                                                                        String codigoTipoEquipo = new ControlDB_TipoEquipo(tipoConexion).buscar_nombre(selectTipo.getSelectedItem().toString());
                                                                        TipoEquipo tipoEquipo = new TipoEquipo();
                                                                        tipoEquipo.setCodigo(codigoTipoEquipo);
                                                                        tipoEquipo.setDescripcion(selectTipo.getSelectedItem().toString());


                                                                        SolicitudListadoEquipo solicitudListadoEquipo = new SolicitudListadoEquipo();
                                                                        solicitudListadoEquipo.setTipoEquipo(tipoEquipo);
                                                                        solicitudListadoEquipo.setMarcaEquipo(listadoMarcaEquipo.get(selectMarca.getSelectedIndex()));
                                                                        solicitudListadoEquipo.setModeloEquipo(listadoModelosEquipo.get(selectModelo.getSelectedIndex()));
                                                                        solicitudListadoEquipo.setCantidad(Integer.parseInt(selectCantidad.getSelectedItem().toString()));
                                                                        solicitudListadoEquipo.setFechaHoraInicio(fechaInicio_Solicitud_Equipo);
                                                                        solicitudListadoEquipo.setFechaHoraFin(fechaFin_Solicitud_Equipo);
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
                                                                            solicitudListadoEquipo.setCantidadMinutos(new ControlDB_Equipo(tipoConexion).DiferenciasMinutosEntreDosFechas(anoI, mesI, diaI, horaInicio.getSelectedItem().toString(), minutoInicio.getSelectedItem().toString(), anoF, mesF, diaF, horaFin.getSelectedItem().toString(), minutoFin.getSelectedItem().toString()));
                                                                        }catch(Exception e){

                                                                        }
                                                                        if( validarExistenciaEnListadoEquipos(solicitudListadoEquipo)){
                                                                            JOptionPane.showMessageDialog(null,"Ya se agrego esta clasificación de Equipo, valide datos","Advertencia",JOptionPane.INFORMATION_MESSAGE);
                                                                        }else{
                                                                            ListadoSolicitudesEquipos.add(solicitudListadoEquipo);
                                                                            
                                                                            if(ListadoSolicitudesEquipos.size()>= 1){//Si la cantidad de equipos cargador es mayor o igual a 1 inactivamos el CentrodeOperación para que el usuario no lo pueda cambiar
                                                                               centroOperacion.setEnabled(false);
                                                                            }
                                                                            CargarListadoEquipos();
                                                                            InternalFrameSelectorEquipo3.show(false);
                                                                            observacion.setText("");
                                                                        }
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
                }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un Modelo de Equipo", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Debe seleccionar una Marca de Equipo", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar una Tipo de Equipo", "Advertencia",JOptionPane.INFORMATION_MESSAGE);
        }                                                 
    }//GEN-LAST:event_agregarActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        InternalFrameSelectorEquipo3.show(true);
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
        
    }//GEN-LAST:event_jLabel1MouseClicked

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        try{
            if(listadoCentroOperacion != null){
                if(listadoEstadoSolicitud != null){
                    SolicitudEquipo solicitudEquipo = new SolicitudEquipo();
                    solicitudEquipo.setCentroOperacion(listadoCentroOperacion.get(this.centroOperacion.getSelectedIndex()));
                    solicitudEquipo.setUsuarioRealizaSolicitud(user);
                    solicitudEquipo.setEstadoSolicitudEquipo(listadoEstadoSolicitud.get(this.estadoSolicitud.getSelectedIndex()));
                    solicitudEquipo.setListadoSolicitudesEquipos(ListadoSolicitudesEquipos);
                    int retorno = 0;
                    try {
                        retorno = new ControlDB_SolicitudEquipo(tipoConexion).registrar(solicitudEquipo, user);
                        if(retorno ==1 ){
                            try{
                                mail c = new mail(tipoConexion);
                                c.RegistroSolicitudEquipos(user, solicitudEquipo);
                            }catch(Exception e){
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(null,"Registro Exitoso", "Registrado", JOptionPane.INFORMATION_MESSAGE);
                            }
                            JOptionPane.showMessageDialog(null,"Registro Exitoso", "Registrado", JOptionPane.INFORMATION_MESSAGE);
                            removeAll();
                            revalidate();
                            repaint();
                        }else{
                            JOptionPane.showMessageDialog(null,"Error al Registrar datos, verifique información", "Advertencia", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                    }       
                }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un Estado de Solicitud de Equipo","Advertencia", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Debe seleccionar un Centro de Operación","Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al capturar los datos","Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_registrarActionPerformed

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

    private void icon_buscarMotonaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_buscarMotonaveMouseClicked
        InternaFrame_buscarMotonave.show(true);
    }//GEN-LAST:event_icon_buscarMotonaveMouseClicked

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
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
    }//GEN-LAST:event_EditarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        InternaFrame_buscarMotonave.show(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void EliminarSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarSolicitudActionPerformed
        int fila1;
        try{
            fila1=tabla.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ningún registro");
            }
            else{
                int opcion = JOptionPane.showConfirmDialog(null, "Esta seguro que desea quitar este registro?","Advertencia", JOptionPane.INFORMATION_MESSAGE);
                if(opcion ==0){//Procedemos a eliminar el registro
                    //tabla.remove(fila1);
                    ListadoSolicitudesEquipos.remove(fila1);
                    if(ListadoSolicitudesEquipos.size()< 1){//Si la cantidad de equipos cargador es mayor o igual a 1 inactivamos el CentrodeOperación para que el usuario no lo pueda cambiar
                        centroOperacion.setEnabled(true);
                    }
                    CargarListadoEquipos();
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_EliminarSolicitudActionPerformed

    private void selectEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectEquiposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectEquiposActionPerformed

    private void titulo48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titulo48MouseClicked
        InternalFrame_sugerirEquipo.show(true);
    }//GEN-LAST:event_titulo48MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        equipoRecomendado.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(!observacion.getText().equals("")){
            observacion.setText(observacion.getText()+"\n"+equipoRecomendado.getText());
        }else{
            observacion.setText("EQUIPOS SUGERIDOS: \n"+equipoRecomendado.getText());
        }
       equipoRecomendado.setText("");
       InternalFrame_sugerirEquipo.show(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(!equipoRecomendado.getText().equals("")){
            equipoRecomendado.setText(equipoRecomendado.getText()+"\n"+listadoEquipo.get(selectEquipos.getSelectedIndex()).getCodigo()+" "+listadoEquipo.get(selectEquipos.getSelectedIndex()).getDescripcion()+listadoEquipo.get(selectEquipos.getSelectedIndex()).getModelo());
        }else{
            equipoRecomendado.setText(listadoEquipo.get(selectEquipos.getSelectedIndex()).getCodigo()+" "+listadoEquipo.get(selectEquipos.getSelectedIndex()).getDescripcion()+listadoEquipo.get(selectEquipos.getSelectedIndex()).getModelo());
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator Cronograma_jSeparator10;
    private javax.swing.JSeparator Cronograma_jSeparator11;
    private javax.swing.JSeparator Cronograma_jSeparator12;
    private javax.swing.JSeparator Cronograma_jSeparator13;
    private javax.swing.JSeparator Cronograma_jSeparator14;
    private javax.swing.JSeparator Cronograma_jSeparator15;
    private javax.swing.JSeparator Cronograma_jSeparator16;
    private javax.swing.JSeparator Cronograma_jSeparator4;
    private javax.swing.JSeparator Cronograma_jSeparator5;
    private javax.swing.JSeparator Cronograma_jSeparator6;
    private javax.swing.JSeparator Cronograma_jSeparator7;
    private javax.swing.JSeparator Cronograma_jSeparator8;
    private javax.swing.JSeparator Cronograma_jSeparator9;
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu EliminarS;
    private javax.swing.JMenuItem EliminarSolicitud;
    private javax.swing.JInternalFrame InternaFrame_buscarMotonave;
    private javax.swing.JInternalFrame InternalFrameSelectorEquipo3;
    private javax.swing.JInternalFrame InternalFrame_sugerirEquipo;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JLabel Usuario_codigo;
    private javax.swing.JButton agregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> centroOperacion;
    private javax.swing.JRadioButton checkMotonave;
    private javax.swing.JTextPane equipoRecomendado;
    private javax.swing.JComboBox<String> estadoSolicitud;
    private com.toedter.calendar.JDateChooser fechaFin;
    private com.toedter.calendar.JDateChooser fechaInicio;
    private javax.swing.JLabel fechaSolicitud;
    private javax.swing.JComboBox<String> horaFin;
    private javax.swing.JComboBox<String> horaInicio;
    private javax.swing.JLabel horarioTiempoIFinalSolicitudEquiposRegistrar;
    private javax.swing.JLabel horarioTiempoInicioSolicitudEquiposRegistrar;
    private javax.swing.JLabel icon_buscarMotonave;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel label_motonave;
    private javax.swing.JComboBox<String> minutoFin;
    private javax.swing.JComboBox<String> minutoInicio;
    private javax.swing.JTextArea observacion;
    private javax.swing.JButton registrar;
    private javax.swing.JComboBox<String> selectCantidad;
    private javax.swing.JComboBox<String> selectCentroCostoAuxiliar;
    private javax.swing.JComboBox<String> selectCompañia;
    private javax.swing.JComboBox<String> selectEquipos;
    private javax.swing.JComboBox<String> selectLaborRealizada;
    private javax.swing.JComboBox<String> selectMarca;
    private javax.swing.JComboBox<String> selectModelo;
    private javax.swing.JComboBox<String> selectSubcentroCosto;
    private javax.swing.JComboBox<String> selectTipo;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla1;
    private javax.swing.JLabel titulo20;
    private javax.swing.JLabel titulo28;
    private javax.swing.JLabel titulo30;
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
    private javax.swing.JLabel titulo58;
    private javax.swing.JLabel titulo59;
    private javax.swing.JLabel titulo6;
    private javax.swing.JLabel usuario_nombre;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
  
    public void cargar(int valor){
        switch(valor){
            case 1:{
                //Cargamos en la interfaz las Marcas de equipos
                System.out.println("El tipo_equipo fue:_" +selectTipo.getSelectedItem().toString());
                try {
                    ArrayList<String> listadoMarcaEquipo = new ArrayList();
                    listadoMarcaEquipo=new ControlDB_Equipo(tipoConexion).cargarMarcasEquiposEnAplicacionInterna(selectTipo.getSelectedItem().toString());
                    String data[]= new String[listadoMarcaEquipo.size()];
                    for(int i=0; i< listadoMarcaEquipo.size(); i++){ 
                        //selectMarca.addItem(""+listadoMarcaEquipo.get(i));
                        data[i]=listadoMarcaEquipo.get(i);
                        //System.out.println(listadoMarcaEquipo.get(i));
                    }
                    //String labels[] = { "A", "B", "C", "D", "E" };
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(data);
                    selectMarca.setModel(model);
                } catch (SQLException ex) {
                    Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                }
                //selectMarca.setSelectedIndex(0);
                break;
            }
            case 2:{
                //Cargamos en la interfaz las Marcas de equipos
                try {
                    ArrayList<String> listadoModelosEquipo = new ArrayList();
                    listadoModelosEquipo=new ControlDB_Equipo(tipoConexion).cargarModelosEquiposEnAplicacionInterna(selectTipo.getSelectedItem().toString(),selectMarca.getSelectedItem().toString());
                    for(int i=0; i< listadoModelosEquipo.size(); i++){
                        selectModelo.addItem(""+listadoModelosEquipo.get(i));
                        //System.out.println(listadoModelosEquipo.get(i));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                }
               //selectModelo.setSelectedIndex(0);
                break;
            }default:{
                break;
            }
        }  
    }
    public void CargarListadoEquipos() throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"TipoEquipo", "Marca","Modelo","Cantidad","Fecha-Inicio","Fecha-Fin","Cant-Horas","Labor_Realizada","Motonave","Compañia"});  
        //private ArrayList<SolicitudListadoEquipo> ListadoSolicitudesEquipos=new ArrayList<>();
        for (SolicitudListadoEquipo ListadoSolicitudesEquipos1 : ListadoSolicitudesEquipos) {
            String[] registro = new String[10];
            registro[0] = "" + ListadoSolicitudesEquipos1.getTipoEquipo().getDescripcion();
            registro[1] = "" + ListadoSolicitudesEquipos1.getMarcaEquipo();
            registro[2] = "" + ListadoSolicitudesEquipos1.getModeloEquipo();
            registro[3] = "" + ListadoSolicitudesEquipos1.getCantidad();
            registro[4] = "" + ListadoSolicitudesEquipos1.getFechaHoraInicio();
            registro[5] = "" + ListadoSolicitudesEquipos1.getFechaHoraFin();
            try{
                //Calculamos la cantidad de Horas a partir de cierta cantidad de minutos
                String minutoCantS ="";
                int minutoCant=(ListadoSolicitudesEquipos1.getCantidadMinutos() % 60);
                int horasCant=(ListadoSolicitudesEquipos1.getCantidadMinutos()/60);
                if(minutoCant<9){
                    minutoCantS="0"+minutoCant;
                }else{
                    minutoCantS=""+minutoCant;
                }
                registro[6] = "" + horasCant+":"+minutoCantS;
            }catch(Exception e){
                registro[6] ="";
            }
            registro[7] = "" + ListadoSolicitudesEquipos1.getLaborRealizada().getDescripcion();
            if(ListadoSolicitudesEquipos1.getMotonave() !=null){
                registro[8] = "" + ListadoSolicitudesEquipos1.getMotonave().getDescripcion();
            }else{
                registro[8] = "NULL";
            }
            registro[9] = "" + ListadoSolicitudesEquipos1.getCompañia().getDescripcion();
            modelo.addRow(registro);      
        }
        tabla.setModel(modelo);  
    }
    public boolean validarExistenciaEnListadoEquipos(SolicitudListadoEquipo solicitudListadoEquipo){
        for (SolicitudListadoEquipo ListadoSolicitudesEquipos1 : ListadoSolicitudesEquipos) {
            if(ListadoSolicitudesEquipos1.getTipoEquipo().getDescripcion().equals(solicitudListadoEquipo.getTipoEquipo().getDescripcion())
                    && ListadoSolicitudesEquipos1.getMarcaEquipo().equals(solicitudListadoEquipo.getMarcaEquipo()) && 
                    ListadoSolicitudesEquipos1.getModeloEquipo().equals(solicitudListadoEquipo.getModeloEquipo())
                    ){
                return true;
            }    
        }
        return false;         
    }
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
}
