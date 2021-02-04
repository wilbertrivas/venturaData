package Auditoria.View;
  
import Auditoria.Controller2.Controller_Auditoria;
import Auditoria.Model2.Auditoria;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public final class Auditoria_Consultar extends javax.swing.JPanel {
    ArrayList<Auditoria> listado=null;
    private Usuario user;
    private String tipoConexion;
    public Auditoria_Consultar(Usuario user,String tipoConexion) {
        initComponents();
        this.user= user;
        this.tipoConexion= tipoConexion;
        InternalFrameSelectorCampos.getContentPane().setBackground(Color.WHITE);
        InternalFrameSelectorCampos.show(false);
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
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        InternalFrameSelectorCampos = new javax.swing.JInternalFrame();
        jButton2 = new javax.swing.JButton();
        selectAll = new javax.swing.JRadioButton();
        Auditoria_Código = new javax.swing.JRadioButton();
        Auditoria_Fecha = new javax.swing.JRadioButton();
        Auditoria_IP_Dispositivo = new javax.swing.JRadioButton();
        Auditoria_Mac_Dispositivo = new javax.swing.JRadioButton();
        Auditoria_Código_Movimiento = new javax.swing.JRadioButton();
        Auditoria_Descripción_Movimiento = new javax.swing.JRadioButton();
        Auditoria_Detalle_Movimiento = new javax.swing.JRadioButton();
        Auditoria_NombreDispositivo_Registra = new javax.swing.JRadioButton();
        selectUsuario_estado = new javax.swing.JRadioButton();
        selectUsuario_codigo = new javax.swing.JRadioButton();
        selectUsuario_nombre = new javax.swing.JRadioButton();
        titulo2 = new javax.swing.JLabel();
        titulo12 = new javax.swing.JLabel();
        titulo13 = new javax.swing.JLabel();
        jSeparator21 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        selectUsuario_correo = new javax.swing.JRadioButton();
        titulo15 = new javax.swing.JLabel();
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
        jLabel13 = new javax.swing.JLabel();
        alerta_fechaFinal = new javax.swing.JLabel();
        alerta_fechaInicio = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        horarioTiempoIFinalMvtoCarbon_Procesar = new javax.swing.JLabel();
        horarioTiempoInicioMvtoCarbon_Procesar = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InternalFrameSelectorCampos.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrameSelectorCampos.setClosable(true);
        InternalFrameSelectorCampos.setVisible(true);
        InternalFrameSelectorCampos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCamposAplicarConfig.png"))); // NOI18N
        jButton2.setText("Aplicar Configuración");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, 230, 30));

        selectAll.setBackground(new java.awt.Color(255, 255, 255));
        selectAll.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        selectAll.setText("Todos");
        selectAll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectAll.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectAllItemStateChanged(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 110, 20));

        Auditoria_Código.setBackground(new java.awt.Color(255, 255, 255));
        Auditoria_Código.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Auditoria_Código.setForeground(new java.awt.Color(51, 51, 51));
        Auditoria_Código.setText("Auditoria_Código");
        Auditoria_Código.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Auditoria_Código.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Auditoria_CódigoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(Auditoria_Código, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 190, -1));

        Auditoria_Fecha.setBackground(new java.awt.Color(255, 255, 255));
        Auditoria_Fecha.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Auditoria_Fecha.setForeground(new java.awt.Color(51, 51, 51));
        Auditoria_Fecha.setText("Auditoria_Fecha");
        Auditoria_Fecha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Auditoria_Fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 190, -1));

        Auditoria_IP_Dispositivo.setBackground(new java.awt.Color(255, 255, 255));
        Auditoria_IP_Dispositivo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Auditoria_IP_Dispositivo.setForeground(new java.awt.Color(51, 51, 51));
        Auditoria_IP_Dispositivo.setText("Auditoria_IP_Dispositivo");
        Auditoria_IP_Dispositivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Auditoria_IP_Dispositivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 190, -1));

        Auditoria_Mac_Dispositivo.setBackground(new java.awt.Color(255, 255, 255));
        Auditoria_Mac_Dispositivo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Auditoria_Mac_Dispositivo.setForeground(new java.awt.Color(51, 51, 51));
        Auditoria_Mac_Dispositivo.setText("Auditoria_Mac_Dispositivo");
        Auditoria_Mac_Dispositivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Auditoria_Mac_Dispositivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 190, -1));

        Auditoria_Código_Movimiento.setBackground(new java.awt.Color(255, 255, 255));
        Auditoria_Código_Movimiento.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Auditoria_Código_Movimiento.setForeground(new java.awt.Color(51, 51, 51));
        Auditoria_Código_Movimiento.setText("Auditoria_Código_Movimiento");
        Auditoria_Código_Movimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Auditoria_Código_Movimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 190, -1));

        Auditoria_Descripción_Movimiento.setBackground(new java.awt.Color(255, 255, 255));
        Auditoria_Descripción_Movimiento.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Auditoria_Descripción_Movimiento.setForeground(new java.awt.Color(51, 51, 51));
        Auditoria_Descripción_Movimiento.setText("Auditoria_Descripción_Movimiento");
        Auditoria_Descripción_Movimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Auditoria_Descripción_Movimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Auditoria_Descripción_MovimientoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(Auditoria_Descripción_Movimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 210, -1));

        Auditoria_Detalle_Movimiento.setBackground(new java.awt.Color(255, 255, 255));
        Auditoria_Detalle_Movimiento.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Auditoria_Detalle_Movimiento.setForeground(new java.awt.Color(51, 51, 51));
        Auditoria_Detalle_Movimiento.setText("Auditoria_Detalle_Movimiento");
        Auditoria_Detalle_Movimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Auditoria_Detalle_Movimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 220, -1));

        Auditoria_NombreDispositivo_Registra.setBackground(new java.awt.Color(255, 255, 255));
        Auditoria_NombreDispositivo_Registra.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Auditoria_NombreDispositivo_Registra.setForeground(new java.awt.Color(51, 51, 51));
        Auditoria_NombreDispositivo_Registra.setText("Auditoria_NombreDispositivo_Registra");
        Auditoria_NombreDispositivo_Registra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Auditoria_NombreDispositivo_Registra, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 220, -1));

        selectUsuario_estado.setBackground(new java.awt.Color(255, 255, 255));
        selectUsuario_estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectUsuario_estado.setForeground(new java.awt.Color(51, 51, 51));
        selectUsuario_estado.setText("Usuario Estado");
        selectUsuario_estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectUsuario_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 190, -1));

        selectUsuario_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectUsuario_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectUsuario_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectUsuario_codigo.setText("Usuario Código");
        selectUsuario_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectUsuario_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 190, -1));

        selectUsuario_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectUsuario_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectUsuario_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectUsuario_nombre.setText("Usuario Nombre");
        selectUsuario_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectUsuario_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 190, -1));

        titulo2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo2.setText("SELECTOR DE CAMPOS");
        titulo2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1200, 0));

        titulo12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo12.setText("USUARIO");
        titulo12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo12, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 240, 20));

        titulo13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo13.setText("AUDITORIA");
        titulo13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 240, 20));

        jSeparator21.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternalFrameSelectorCampos.getContentPane().add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 0, 430));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCamposDefauls.png"))); // NOI18N
        jButton3.setText("Campos Predeterminados");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 230, 30));

        selectUsuario_correo.setBackground(new java.awt.Color(255, 255, 255));
        selectUsuario_correo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectUsuario_correo.setForeground(new java.awt.Color(51, 51, 51));
        selectUsuario_correo.setText("Usuario Correo");
        selectUsuario_correo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectUsuario_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 190, -1));

        titulo15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo15, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 240, 260));

        titulo18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 240, 260));

        add(InternalFrameSelectorCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 540, 440));

        titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo.setText("AUDITORIA");
        add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 30));

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
        jScrollPane1.setViewportView(tabla);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 1300, 630));

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
        add(consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, 210, 35));

        fechaInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicioMouseEntered(evt);
            }
        });
        add(fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 170, 30));

        minutoInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoInicioActionPerformed(evt);
            }
        });
        add(minutoInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 50, 30));

        horaInicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                horaInicioItemStateChanged(evt);
            }
        });
        add(horaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 50, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Hora");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("FECHA/HORA FIN");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, -1, 20));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 65)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("|");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 30, 40));

        fechaFin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFinMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFinMouseEntered(evt);
            }
        });
        add(fechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 170, 30));

        minutoFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoFinActionPerformed(evt);
            }
        });
        add(minutoFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, 50, 30));

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
        add(horaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, 50, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Hora");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, -1, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("FECHA/HORA INICIO");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, -1, 20));

        alerta_fechaFinal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_fechaFinal.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_fechaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 370, 20));

        alerta_fechaInicio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_fechaInicio.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 370, 20));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1300, 10));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCampo.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 20, 50, 40));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Campos");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 0, 40, 30));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/ExportarExcel.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 20, 50, 40));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Exportar");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 0, 50, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel24.setText(":");
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, 20, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel25.setText(":");
        add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 20, 30));

        horarioTiempoIFinalMvtoCarbon_Procesar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoIFinalMvtoCarbon_Procesar.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoIFinalMvtoCarbon_Procesar.setText("AM");
        add(horarioTiempoIFinalMvtoCarbon_Procesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 20, 50, 30));

        horarioTiempoInicioMvtoCarbon_Procesar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoInicioMvtoCarbon_Procesar.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoInicioMvtoCarbon_Procesar.setText("AM");
        add(horarioTiempoInicioMvtoCarbon_Procesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 50, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        generarListadoAuditoria();
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        InternalFrameSelectorCampos.show(false);
        generarListadoAuditoria();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void selectAllItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectAllItemStateChanged
        if(selectAll.isSelected()){
            selectorCampoTodos();
        }else{
            selectorCampoNinguno();
        }
    }//GEN-LAST:event_selectAllItemStateChanged

    private void consultarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultarMouseExited
        alerta_fechaInicio.setText("");
        alerta_fechaFinal.setText("");
    }//GEN-LAST:event_consultarMouseExited

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        selectorCampoPorDefecto();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        InternalFrameSelectorCampos.show(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
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
                int numFila=tabla.getRowCount(), numColumna=tabla.getColumnCount();
                if(archivo.getName().endsWith("xls")){
                    wb = new HSSFWorkbook();
                }else{
                    wb= new XSSFWorkbook();
                }
                Sheet hoja= wb.createSheet("Mvto_Equipo");
                int costoTotalApuntador=300000;
                try{
                    for(int i= -1; i < numFila; i++ ){
                        Row fila= hoja.createRow(i+1);
                        for(int j=0; j< numColumna; j++){
                            Cell celda= fila.createCell(j);
                            if(i==-1){
                                
                                celda.setCellValue(String.valueOf(tabla.getColumnName(j)));
                                String nameColumn=String.valueOf(tabla.getColumnName(j));
                                if(nameColumn.equals("ME_CostoTotal")){
                                    costoTotalApuntador=j;
                                }
                            }else{
                                try{
                                    String[] valor=String.valueOf(tabla.getValueAt(i, j)).split("-");
                                    if(valor.length==3){
                                         String[] valor2=valor[2].split(":");
                                        if(valor2.length >= 3){
                                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                            Date fechaM = dateFormat.parse(String.valueOf(tabla.getValueAt(i, j)));
                                            CellStyle cellStyle = wb.createCellStyle();
                                            CreationHelper createHelper = wb.getCreationHelper();
                                            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yy h:mm:ss"));
                                            //cell = row.createCell(1);
                                            celda.setCellValue(fechaM);
                                            celda.setCellStyle(cellStyle);
                                        }else{
                                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                            Date fechaM = dateFormat.parse(String.valueOf(tabla.getValueAt(i, j)));
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
                                            celda.setCellValue(Integer.parseInt(tabla.getValueAt(i, j).toString()));
                                        }catch(Exception e){
                                            try{
                                                if(costoTotalApuntador == j){
                                                    celda.setCellValue(Double.parseDouble(String.valueOf(tabla.getValueAt(i, j)).replace(",", ".")));
                                                }else{
                                                    celda.setCellValue(String.valueOf(tabla.getValueAt(i, j)));
                                                }
                                            }catch(Exception ex){
                                                celda.setCellValue(String.valueOf(tabla.getValueAt(i, j)));
                                            }
                                        }
                                    }   
                                }catch(Exception e){
                                    celda.setCellValue(String.valueOf(tabla.getValueAt(i, j)));
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
    }//GEN-LAST:event_jLabel4MouseClicked

    private void horaInicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_horaInicioItemStateChanged
        if(horaInicio.getSelectedIndex()<= 11){
            //horario.setSelectedIndex(0);
            horarioTiempoInicioMvtoCarbon_Procesar.setText("AM");
        }else{
            //horario.setSelectedIndex(1);
            horarioTiempoInicioMvtoCarbon_Procesar.setText("PM");
        }
    }//GEN-LAST:event_horaInicioItemStateChanged

    private void horaFinItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_horaFinItemStateChanged
        if(horaFin.getSelectedIndex()<= 11){
            //horario.setSelectedIndex(0);
            horarioTiempoIFinalMvtoCarbon_Procesar.setText("AM");
        }else{
            //horario.setSelectedIndex(1);
            horarioTiempoIFinalMvtoCarbon_Procesar.setText("PM");
        }
    }//GEN-LAST:event_horaFinItemStateChanged

    private void horaFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horaFinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_horaFinActionPerformed

    private void Auditoria_Descripción_MovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Auditoria_Descripción_MovimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Auditoria_Descripción_MovimientoActionPerformed

    private void Auditoria_CódigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Auditoria_CódigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Auditoria_CódigoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Auditoria_Código;
    private javax.swing.JRadioButton Auditoria_Código_Movimiento;
    private javax.swing.JRadioButton Auditoria_Descripción_Movimiento;
    private javax.swing.JRadioButton Auditoria_Detalle_Movimiento;
    private javax.swing.JRadioButton Auditoria_Fecha;
    private javax.swing.JRadioButton Auditoria_IP_Dispositivo;
    private javax.swing.JRadioButton Auditoria_Mac_Dispositivo;
    private javax.swing.JRadioButton Auditoria_NombreDispositivo_Registra;
    private javax.swing.JInternalFrame InternalFrameSelectorCampos;
    private javax.swing.JLabel alerta_fechaFinal;
    private javax.swing.JLabel alerta_fechaInicio;
    private javax.swing.JButton consultar;
    private com.toedter.calendar.JDateChooser fechaFin;
    private com.toedter.calendar.JDateChooser fechaInicio;
    private javax.swing.JComboBox<String> horaFin;
    private javax.swing.JComboBox<String> horaInicio;
    private javax.swing.JLabel horarioTiempoIFinalMvtoCarbon_Procesar;
    private javax.swing.JLabel horarioTiempoInicioMvtoCarbon_Procesar;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JComboBox<String> minutoFin;
    private javax.swing.JComboBox<String> minutoInicio;
    private javax.swing.JRadioButton selectAll;
    private javax.swing.JRadioButton selectUsuario_codigo;
    private javax.swing.JRadioButton selectUsuario_correo;
    private javax.swing.JRadioButton selectUsuario_estado;
    private javax.swing.JRadioButton selectUsuario_nombre;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel titulo12;
    private javax.swing.JLabel titulo13;
    private javax.swing.JLabel titulo15;
    private javax.swing.JLabel titulo18;
    private javax.swing.JLabel titulo2;
    // End of variables declaration//GEN-END:variables
    
    public void tabla_Listar(String data1, String data2) throws SQLException{
        String Stitulo="";
        int contador=0;
        if(Auditoria_Código.isSelected()){
            Stitulo +="Código"+":";
            contador++;
        }
        if(Auditoria_Fecha.isSelected()){
            Stitulo +="Fecha"+":";
            contador++;
        }
        if(Auditoria_NombreDispositivo_Registra.isSelected()){
            Stitulo +="Nombre Dispositivo"+":";
            contador++;
        }
        if(Auditoria_IP_Dispositivo.isSelected()){
            Stitulo +="IP"+":";
            contador++;
        }
        if(Auditoria_Mac_Dispositivo.isSelected()){
            Stitulo +="MAC"+":";
            contador++;
        }
        if(Auditoria_Código_Movimiento.isSelected()){
            Stitulo +="Código"+":";
            contador++;
        }
        if(Auditoria_Descripción_Movimiento.isSelected()){
            Stitulo +="Descripción"+":";
            contador++;
        }
        if(Auditoria_Detalle_Movimiento.isSelected()){
            Stitulo +="Detalle"+":";
            contador++;
        }

        //USUARIO
        if(selectUsuario_codigo.isSelected()){
            Stitulo +="Usuario_Código"+":";
            contador++;
        }
        if(selectUsuario_nombre.isSelected()){
            Stitulo +="Usuario_Nombre"+":";
            contador++;
        }
        if(selectUsuario_correo.isSelected()){
            Stitulo +="Usuario_Correo"+":";
            contador++;
        }
        if(selectUsuario_estado.isSelected()){
            Stitulo +="Usuario_Estado"+":";
            contador++;
        }
        DefaultTableModel modelo = new DefaultTableModel(null, Stitulo.split(":"));  
        
        listado=new Controller_Auditoria(tipoConexion).buscarPorFecha(data1, data2);
        for (Auditoria listado1 : listado) {
            Object[] registro = new String[contador];
            int contadorRegistro=0;
            
            if(Auditoria_Código.isSelected()){
                registro[contadorRegistro] = "" + listado1.getCodigo();
                contadorRegistro++;
            }
            if(Auditoria_Fecha.isSelected()){
                try{
                    /*String[] fechaHora=listado1.getFecha().split(" ");
                    String[] formato=fechaHora[0].split("-");
                    registro[contadorRegistro] = formato[0]+"-"+formato[1]+"-"+formato[2]; */
                    registro[contadorRegistro] = listado1.getFecha();
                }catch(Exception e){        
                }
                contadorRegistro++;
            }         
            if(Auditoria_NombreDispositivo_Registra.isSelected()){
                registro[contadorRegistro] = "" + listado1.getNombreDispositivoDondeRegistra();
                contadorRegistro++;
            }
            if(Auditoria_IP_Dispositivo.isSelected()){
                registro[contadorRegistro] = "" + listado1.getIpDispositivoDondeRegistra();
                contadorRegistro++;
            }
            if(Auditoria_Mac_Dispositivo.isSelected()){
                registro[contadorRegistro] = "" + listado1.getMacDispositivoDondeRegistra();
                contadorRegistro++;
            }
            if(Auditoria_Código_Movimiento.isSelected()){
                registro[contadorRegistro] = "" + listado1.getCodigoMovimiento();
                contadorRegistro++;
            }
            if(Auditoria_Descripción_Movimiento.isSelected()){
                registro[contadorRegistro] =listado1.getDescMovimiento();
                contadorRegistro++;
            }
            if(Auditoria_Detalle_Movimiento.isSelected()){
                registro[contadorRegistro] =listado1.getDetalleMovimiento();
                contadorRegistro++;
            }
            //USUARIO
            if(selectUsuario_codigo.isSelected()){
                registro[contadorRegistro] = "" + listado1.getUsuarioQuienRegistra().getCodigo();
                contadorRegistro++;
            }
            if(selectUsuario_nombre.isSelected()){
                registro[contadorRegistro] = "" + listado1.getUsuarioQuienRegistra().getNombres()+" "+listado1.getUsuarioQuienRegistra().getApellidos();
                contadorRegistro++;
            }
            if(selectUsuario_correo.isSelected()){
                registro[contadorRegistro] = "" + listado1.getUsuarioQuienRegistra().getCorreo();
                contadorRegistro++;
            }
            if(selectUsuario_estado.isSelected()){
                registro[contadorRegistro] = "" + listado1.getUsuarioQuienRegistra().getEstado();
                contadorRegistro++;
            }
                modelo.addRow(registro);      
            }
        tabla.setModel(modelo);
        //tabla.setFillsViewportHeight(true);
    }
    public void generarListadoAuditoria(){
        try{
            String fechaAuditoria_Inicial="";
            String fechaAuditoria_Final="";
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
                fechaAuditoria_Inicial=anoI+"-"+mesI+"-"+diaI;
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
                fechaAuditoria_Final=anoF+"-"+mesF+"-"+diaF;
                contador++;
            }catch(Exception e){
                alerta_fechaFinal.setText("Verifique la fecha Final");
            } 
            if(contador==2){//Se validaron las dos fechas y contienen formato correcto
                fechaAuditoria_Inicial = fechaAuditoria_Inicial+" "+horaInicio.getSelectedItem().toString()+":"+minutoInicio.getSelectedItem().toString();
                fechaAuditoria_Final = fechaAuditoria_Final+" "+horaFin.getSelectedItem().toString()+":"+minutoFin.getSelectedItem().toString();
                try {
                    tabla_Listar(fechaAuditoria_Inicial,fechaAuditoria_Final);
                } catch (SQLException ex) {
                    Logger.getLogger(Auditoria_Consultar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"No se puedo generar el listado de procesos", "Advertencia",JOptionPane.ERROR_MESSAGE);
        }   
    }
    public void selectorCampoPorDefecto(){
        Auditoria_Código.setSelected(true);
        Auditoria_Fecha.setSelected(true);
        Auditoria_NombreDispositivo_Registra.setSelected(true);
        Auditoria_IP_Dispositivo.setSelected(true);
        Auditoria_Mac_Dispositivo.setSelected(true);
        Auditoria_Código_Movimiento.setSelected(false);
        Auditoria_Descripción_Movimiento.setSelected(true);
        Auditoria_Detalle_Movimiento.setSelected(true);
        selectUsuario_codigo.setSelected(true);
        selectUsuario_nombre.setSelected(true);
        selectUsuario_correo.setSelected(false);
        selectUsuario_estado.setSelected(false); 
    }
    public void selectorCampoTodos(){
        //Campos Activos
        Auditoria_Código.setSelected(true);
        Auditoria_Fecha.setSelected(true);
        Auditoria_NombreDispositivo_Registra.setSelected(true);
        Auditoria_IP_Dispositivo.setSelected(true);
        Auditoria_Mac_Dispositivo.setSelected(true);
        Auditoria_Código_Movimiento.setSelected(true);
        Auditoria_Descripción_Movimiento.setSelected(true);
        Auditoria_Detalle_Movimiento.setSelected(true);
        selectUsuario_codigo.setSelected(true);
        selectUsuario_nombre.setSelected(true);
        selectUsuario_correo.setSelected(true);
        selectUsuario_estado.setSelected(true);    
        
    }
    public void selectorCampoNinguno(){
        Auditoria_Código.setSelected(false);
        Auditoria_Fecha.setSelected(false);
        Auditoria_NombreDispositivo_Registra.setSelected(false);
        Auditoria_IP_Dispositivo.setSelected(false);
        Auditoria_Mac_Dispositivo.setSelected(false);
        Auditoria_Código_Movimiento.setSelected(false);
        Auditoria_Descripción_Movimiento.setSelected(false);
        Auditoria_Detalle_Movimiento.setSelected(false);
        selectUsuario_codigo.setSelected(false);
        selectUsuario_nombre.setSelected(false);
        selectUsuario_correo.setSelected(false);
        selectUsuario_estado.setSelected(false);  
    } 
}
