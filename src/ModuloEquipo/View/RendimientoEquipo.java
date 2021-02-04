/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloEquipo.View;

import Catalogo.Controller.ControlDB_Equipo;
import Catalogo.Model.CentroOperacion;
import Catalogo.Model.Equipo;
import ModuloEquipo.Controller2.ControlDB_RendiminentoEquipo;
import ModuloEquipo.Model.AsignacionEquipo;
import ModuloEquipo.Model.EstadoSolicitudEquipos;
import ModuloEquipo.Model.SolicitudEquipo;
import ModuloEquipo.Model.SolicitudListadoEquipo;
import Sistema.Model.Usuario;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author wrivas
 */
public class RendimientoEquipo extends javax.swing.JPanel {

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
    public RendimientoEquipo(String tipoConexion) {
        initComponents();
        this.tipoConexion= tipoConexion;
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
                        dataEquipo[i]=""+(listadoEquipo.get(i).getCodigo()+" "+listadoEquipo.get(i).getDescripcion()+ " "+ listadoEquipo.get(i).getModelo());   
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
        jButton1 = new javax.swing.JButton();
        titulo57 = new javax.swing.JLabel();
        selectTipo = new javax.swing.JComboBox<>();
        titulo60 = new javax.swing.JLabel();
        selectMarca = new javax.swing.JComboBox<>();
        titulo64 = new javax.swing.JLabel();
        selectModelo = new javax.swing.JComboBox<>();
        titulo58 = new javax.swing.JLabel();
        selectEquipos = new javax.swing.JComboBox<>();
        panel = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        parada_minutos = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        consulta_horas = new javax.swing.JLabel();
        consulta_minutos = new javax.swing.JLabel();
        operativa_horas = new javax.swing.JLabel();
        operativa_minutos = new javax.swing.JLabel();
        parada_horas = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        equipo_descripcion = new javax.swing.JLabel();
        equipo_modelo = new javax.swing.JLabel();
        equipo_codigo = new javax.swing.JLabel();
        Cronograma_titulo33 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        noRegistrada_horas = new javax.swing.JLabel();
        noRegistrada_minutos = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        parada_costo = new javax.swing.JLabel();
        operativa_costo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Fecha/Hora Inicio:");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 120, 30));

        fechaInicioAsignacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicioAsignacionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicioAsignacionMouseEntered(evt);
            }
        });
        add(fechaInicioAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 170, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Hora");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, -1, 30));

        horaInicioAsignacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                horaInicioAsignacionItemStateChanged(evt);
            }
        });
        add(horaInicioAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 50, 30));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText(":");
        add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 20, 30));

        minutoInicioAsignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoInicioAsignacionActionPerformed(evt);
            }
        });
        add(minutoInicioAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 50, 30));

        horarioTiempoInicioSolicitudEquiposRegistrar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        horarioTiempoInicioSolicitudEquiposRegistrar1.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoInicioSolicitudEquiposRegistrar1.setText("AM");
        add(horarioTiempoInicioSolicitudEquiposRegistrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 40, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Fecha /Hora Fin");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 120, 30));

        fechaFinAsignacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFinAsignacionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFinAsignacionMouseEntered(evt);
            }
        });
        add(fechaFinAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 170, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Hora");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 40, -1, 30));

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
        add(horaFinAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 50, 30));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel27.setText(":");
        add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 40, 20, 30));

        minutoFinAsignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoFinAsignacionActionPerformed(evt);
            }
        });
        add(minutoFinAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 40, 50, 30));

        horarioTiempoIFinalSolicitudEquiposRegistrar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        horarioTiempoIFinalSolicitudEquiposRegistrar1.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoIFinalSolicitudEquiposRegistrar1.setText("AM");
        add(horarioTiempoIFinalSolicitudEquiposRegistrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 40, 50, 30));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton1.setText("CALCULAR RENDIMIENTO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 110, 190, 40));

        titulo57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo57.setForeground(new java.awt.Color(51, 51, 51));
        titulo57.setText("Tipo Equipo:");
        add(titulo57, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 120, 30));

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
        add(selectTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 390, 30));

        titulo60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo60.setForeground(new java.awt.Color(51, 51, 51));
        titulo60.setText("Marca:");
        add(titulo60, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, 140, 30));

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
        add(selectMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, 450, 30));

        titulo64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo64.setForeground(new java.awt.Color(51, 51, 51));
        titulo64.setText("Modelo:");
        add(titulo64, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 120, 30));

        selectModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectModeloActionPerformed(evt);
            }
        });
        add(selectModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 390, 30));

        titulo58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo58.setForeground(new java.awt.Color(51, 51, 51));
        titulo58.setText("Equipo:");
        add(titulo58, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, 60, 30));

        selectEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectEquiposActionPerformed(evt);
            }
        });
        add(selectEquipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 120, 450, 30));
        add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 790, 460));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("EQUIPO");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 180, 560, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Equivalente en Minutos");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 280, 200, 20));

        parada_minutos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        parada_minutos.setForeground(new java.awt.Color(102, 102, 102));
        parada_minutos.setText("..........");
        add(parada_minutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 390, 140, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("PARADA:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 390, 90, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("MODELO:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 250, 130, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("OPERATIVO:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 340, 110, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("TIEMPO");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 280, 90, 20));

        consulta_horas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consulta_horas.setForeground(new java.awt.Color(102, 102, 102));
        consulta_horas.setText("..........");
        add(consulta_horas, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 310, 250, 20));

        consulta_minutos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consulta_minutos.setForeground(new java.awt.Color(102, 102, 102));
        consulta_minutos.setText("..........");
        add(consulta_minutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 310, 130, 20));

        operativa_horas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        operativa_horas.setForeground(new java.awt.Color(102, 102, 102));
        operativa_horas.setText("..........");
        add(operativa_horas, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 340, 250, 20));

        operativa_minutos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        operativa_minutos.setForeground(new java.awt.Color(102, 102, 102));
        operativa_minutos.setText("..........");
        add(operativa_minutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 340, 130, 20));

        parada_horas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        parada_horas.setForeground(new java.awt.Color(102, 102, 102));
        parada_horas.setText("..........");
        add(parada_horas, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 390, 250, 20));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 1300, 10));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 480, 560, 10));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 340, 560, 10));
        add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 310, 560, 10));
        add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 390, 560, 10));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("TOTAL:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 310, 120, 20));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("CÓDIGO:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 210, 120, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("DESCRIPCIÓN:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 230, 120, 20));

        equipo_descripcion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipo_descripcion.setForeground(new java.awt.Color(102, 102, 102));
        equipo_descripcion.setText("..........");
        add(equipo_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 230, 420, 20));

        equipo_modelo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipo_modelo.setForeground(new java.awt.Color(102, 102, 102));
        equipo_modelo.setText("..........");
        add(equipo_modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 250, 420, 20));

        equipo_codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipo_codigo.setForeground(new java.awt.Color(102, 102, 102));
        equipo_codigo.setText("..........");
        add(equipo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 210, 420, 20));

        Cronograma_titulo33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Cronograma_titulo33.setForeground(new java.awt.Color(102, 102, 102));
        Cronograma_titulo33.setText("RENDIMIENTO DE EQUIPO");
        add(Cronograma_titulo33, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 250, 30));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 210, 30, 270));
        add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 280, 560, 20));

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 30, 270));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("NO REGISTRADO");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 440, 120, 20));

        noRegistrada_horas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        noRegistrada_horas.setForeground(new java.awt.Color(102, 102, 102));
        noRegistrada_horas.setText("..........");
        add(noRegistrada_horas, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 440, 240, 20));

        noRegistrada_minutos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        noRegistrada_minutos.setForeground(new java.awt.Color(102, 102, 102));
        noRegistrada_minutos.setText("..........");
        add(noRegistrada_minutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 440, 130, 20));
        add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 440, 560, 10));

        parada_costo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        parada_costo.setForeground(new java.awt.Color(102, 102, 102));
        parada_costo.setText("..........");
        add(parada_costo, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 410, 250, 20));

        operativa_costo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        operativa_costo.setForeground(new java.awt.Color(102, 102, 102));
        operativa_costo.setText("..........");
        add(operativa_costo, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 360, 250, 20));
    }// </editor-fold>//GEN-END:initComponents

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
                dataEquipo[i]=""+(listadoEquipo.get(i).getCodigo()+" "+listadoEquipo.get(i).getDescripcion()+ " "+ listadoEquipo.get(i).getModelo());
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String script ="";
        String fechaInicio="";
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
                String fechaFin="";  
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
                
                Equipo equipo = listadoEquipo.get(selectEquipos.getSelectedIndex());
                String rendimiento= new ControlDB_RendiminentoEquipo(tipoConexion).consultarRendimientoEquipo(equipo.getCodigo(),fechaInicio,fechaFin);     
                try{
                    String[] data=rendimiento.split(":");

                    equipo_codigo.setText(""+equipo.getCodigo());
                    equipo_descripcion.setText(""+equipo.getDescripcion());
                    equipo_modelo.setText(""+equipo.getModelo());
                    
                    
                    
                    String consultaT= ""+((Integer.parseInt(data[0])/60)/24)+" días \n"+((Integer.parseInt(data[0])/60) % 24)+" Horas \n"+ (Integer.parseInt(data[0])%60)+" Minutos";
                    String operativaT= ""+((Integer.parseInt(data[1])/60)/24)+" días \n"+((Integer.parseInt(data[1])/60) % 24)+" Horas \n"+ (Integer.parseInt(data[1])%60)+" Minutos";
                    String paradaT= ""+((Integer.parseInt(data[2])/60)/24)+" días \n"+((Integer.parseInt(data[2])/60) % 24)+" Horas \n"+ (Integer.parseInt(data[2])%60)+" Minutos";
                    String NoregistradaT= ""+((Integer.parseInt(data[3])/60)/24)+" días \n"+((Integer.parseInt(data[3])/60) % 24)+" Horas \n"+ (Integer.parseInt(data[3])%60)+" Minutos";
                    
                    
                    consulta_horas.setText(""+consultaT);
                    consulta_minutos.setText(data[0]);
                    
                    operativa_horas.setText(""+operativaT);
                    operativa_minutos.setText(data[1]);
                    
                    parada_horas.setText(""+paradaT);
                    parada_minutos.setText(data[2]);
                    
                    noRegistrada_horas.setText(""+NoregistradaT);
                    noRegistrada_minutos.setText(data[3]);
                    
                    double num1=(Double.parseDouble(data[0])/60);
                    double num2=(Double.parseDouble(data[1])/60);
                    double num3=(Double.parseDouble(data[2])/60);
                    
                    //Se realiza este proceso en caso que el que (Tiempo Operativo + Tiempo de Parada sean mayor que el tiempo consultado, producto de doble registros, con el
                    //fin de no generar tiempo negativo
                    double num4=(Double.parseDouble(data[3])/60);
                    if(num4 < 0){
                        num4=0;
                    }
                    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                    DecimalFormat formatea = new DecimalFormat("###,###.##");
                    String costoOperativo="";
                    String costoParada="";
                    try{
                         costoOperativo = formatea.format(Double.parseDouble(data[4]));
                         costoParada = formatea.format(Double.parseDouble(data[5]));
                    }catch(Exception e){}
                    //System.out.println("Formateado es "+formatea.format(Double.parseDouble(data[4]) +" y "+formatea.format(data[5]));
                    operativa_costo.setText("Costo: "+costoOperativo);
                    parada_costo.setText("Costo: "+costoParada);  
                    dataset.setValue(num1, "", "Total");
                    dataset.setValue(num2, "", "Operativo");
                    dataset.setValue(num3, "", "Parada");
                    dataset.setValue(num4, "", "No Registrado");
                    
                    JFreeChart barChart = ChartFactory.createBarChart(
                            "Código: "+equipo.getCodigo()+"\nDescripción: "+equipo.getDescripcion()+" "+equipo.getModelo(),
                            "Fecha_Inicio: "+fechaInicio+"         Fecha_Final: "+fechaFin,
                            "HORAS",
                            dataset,
                            PlotOrientation.VERTICAL,
                            false, true, false);

                    ChartPanel frame= new ChartPanel( barChart);
                    //frame.setVisible(true);
                    frame.setSize(450,500);
                    panel.setViewportView(frame); 
                }catch(Exception e){
                   JOptionPane.showMessageDialog(null,"No se pudo generar la grafica, verifique información","Error!!..",JOptionPane.ERROR_MESSAGE);    
                 }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de finalización","Advertencia",JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Inicio","Advertencia",JOptionPane.ERROR_MESSAGE);
        }
               
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cronograma_titulo33;
    private javax.swing.JLabel consulta_horas;
    private javax.swing.JLabel consulta_minutos;
    private javax.swing.JLabel equipo_codigo;
    private javax.swing.JLabel equipo_descripcion;
    private javax.swing.JLabel equipo_modelo;
    private com.toedter.calendar.JDateChooser fechaFinAsignacion;
    private com.toedter.calendar.JDateChooser fechaInicioAsignacion;
    private javax.swing.JComboBox<String> horaFinAsignacion;
    private javax.swing.JComboBox<String> horaInicioAsignacion;
    private javax.swing.JLabel horarioTiempoIFinalSolicitudEquiposRegistrar1;
    private javax.swing.JLabel horarioTiempoInicioSolicitudEquiposRegistrar1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JComboBox<String> minutoFinAsignacion;
    private javax.swing.JComboBox<String> minutoInicioAsignacion;
    private javax.swing.JLabel noRegistrada_horas;
    private javax.swing.JLabel noRegistrada_minutos;
    private javax.swing.JLabel operativa_costo;
    private javax.swing.JLabel operativa_horas;
    private javax.swing.JLabel operativa_minutos;
    private javax.swing.JScrollPane panel;
    private javax.swing.JLabel parada_costo;
    private javax.swing.JLabel parada_horas;
    private javax.swing.JLabel parada_minutos;
    private javax.swing.JComboBox<String> selectEquipos;
    private javax.swing.JComboBox<String> selectMarca;
    private javax.swing.JComboBox<String> selectModelo;
    private javax.swing.JComboBox<String> selectTipo;
    private javax.swing.JLabel titulo57;
    private javax.swing.JLabel titulo58;
    private javax.swing.JLabel titulo60;
    private javax.swing.JLabel titulo64;
    // End of variables declaration//GEN-END:variables
}
