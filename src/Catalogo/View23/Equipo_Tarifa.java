package Catalogo.View23;
import Catalogo.Controller.ControlDB_Equipo;
import Catalogo.Model.Equipo;
import Catalogo.Model.TarifaEquipo;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
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

public final class Equipo_Tarifa extends javax.swing.JPanel implements ActionListener, TableModelListener{
    ArrayList<Equipo> listado=null;
    Usuario user;
    private String tipoConexion;
    Equipo equipo=null;
    
    private PaginadorDeTabla<Equipo> paginadorDeTabla;  
    ProveedorDeDatosDePaginacion<Equipo> proveedorDeDatos;
    ArrayList<String> encabezadoTabla=null;
    
    public Equipo_Tarifa(Usuario us,String tipoConexion) {
        initComponents();
        this.tipoConexion= tipoConexion;
        user=us;
        InternalFrameSelectorCampos.getContentPane().setBackground(Color.WHITE);
        InternalFrameSelectorCampos.show(false);
        InternalFrame_RegistroTarifa.getContentPane().setBackground(Color.WHITE);
        InternalFrame_RegistroTarifa.show(false);
        
        
        todos.setSelected(true);
        tipo.setSelected(false);
        marca.setSelected(false);
        especifico.setSelected(false);
        estado.setSelected(false);
        
        //Cargamos en la interfaz los tipos de equipos
        try {
             ArrayList<String> listadoTiposEquipo = new ArrayList();
            listadoTiposEquipo=new ControlDB_Equipo(tipoConexion).buscarTipoEquiposEnAplicacionInterna();
            for(int i=0; i< listadoTiposEquipo.size(); i++){
                selectTipo.addItem(""+listadoTiposEquipo.get(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Equipo_Tarifa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Cargamos en la interfaz las Marcas de equipos
        try {
             ArrayList<String> listadoMarcaEquipo = new ArrayList();
            listadoMarcaEquipo=new ControlDB_Equipo(tipoConexion).buscarMarcasEquiposEnAplicacionInterna();
            for(int i=0; i< listadoMarcaEquipo.size(); i++){
                selectMarca.addItem(""+listadoMarcaEquipo.get(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Equipo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
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
        //jLabel12.show(false);
        //jLabel5.show(false);
        
        selectorCampoPorDefecto();
        
        encabezadoTabla= new ArrayList<String>();
        pageJComboBox.show(false);
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        InternalFrame_RegistroTarifa = new javax.swing.JInternalFrame();
        equipo_modelo = new javax.swing.JLabel();
        titulo38 = new javax.swing.JLabel();
        titulo39 = new javax.swing.JLabel();
        fechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        horaInicio = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        minutoInicio = new javax.swing.JComboBox<>();
        horarioTiempoInicioSolicitudEquiposRegistrar = new javax.swing.JLabel();
        fechaFin = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        horaFin = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        minutoFin = new javax.swing.JComboBox<>();
        horarioTiempoIFinalSolicitudEquiposRegistrar = new javax.swing.JLabel();
        titulo40 = new javax.swing.JLabel();
        btn_Registrar = new javax.swing.JButton();
        titulo42 = new javax.swing.JLabel();
        titulo45 = new javax.swing.JLabel();
        titulo46 = new javax.swing.JLabel();
        titulo47 = new javax.swing.JLabel();
        equipo_codigo = new javax.swing.JLabel();
        equipo_tipoEquipo = new javax.swing.JLabel();
        equipo_marca = new javax.swing.JLabel();
        equipo_descripción = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        equipo_valorHoraOperación = new javax.swing.JTextField();
        equipo_valorHoraAlquiler = new javax.swing.JTextField();
        titulo49 = new javax.swing.JLabel();
        titulo50 = new javax.swing.JLabel();
        titulo48 = new javax.swing.JLabel();
        titulo41 = new javax.swing.JLabel();
        titulo43 = new javax.swing.JLabel();
        equipo_tarifaAnterior_fechaFinal = new javax.swing.JLabel();
        titulo51 = new javax.swing.JLabel();
        titulo53 = new javax.swing.JLabel();
        equipo_tarifaAnterior_valorAlquiler = new javax.swing.JLabel();
        equipo_tarifaAnterior_fechaInicio = new javax.swing.JLabel();
        titulo56 = new javax.swing.JLabel();
        equipo_tarifaAnterior_valorOperacion = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        InternalFrameSelectorCampos = new javax.swing.JInternalFrame();
        titulo1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        selectAll = new javax.swing.JRadioButton();
        jSeparator5 = new javax.swing.JSeparator();
        selectEquipo_Código = new javax.swing.JRadioButton();
        selectEquipo_CódigoBarra = new javax.swing.JRadioButton();
        selectEquipo_Capacidad = new javax.swing.JRadioButton();
        selectEquipo_Marca = new javax.swing.JRadioButton();
        selectEquipo_Modelo = new javax.swing.JRadioButton();
        selectEquipo_Serial = new javax.swing.JRadioButton();
        selectEquipo_Descripción = new javax.swing.JRadioButton();
        selectEquipo_Producto = new javax.swing.JRadioButton();
        selectEquipo_Observación = new javax.swing.JRadioButton();
        selectProveedorEquipo_Código = new javax.swing.JRadioButton();
        selectPertenencia_Código = new javax.swing.JRadioButton();
        selectClasificador2_Código = new javax.swing.JRadioButton();
        selectClasificador2_Descripción = new javax.swing.JRadioButton();
        selectClasificador1_Código = new javax.swing.JRadioButton();
        selectClasificador1_Descripción = new javax.swing.JRadioButton();
        selectTipoEquipo_Nombre = new javax.swing.JRadioButton();
        selectTipoEquipo_Código = new javax.swing.JRadioButton();
        selectEquipo_Estado = new javax.swing.JRadioButton();
        titulo2 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        titulo3 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        titulo4 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        titulo5 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        titulo6 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        titulo13 = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator20 = new javax.swing.JSeparator();
        jSeparator21 = new javax.swing.JSeparator();
        jSeparator22 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        selectEquipo_Referencia = new javax.swing.JRadioButton();
        selectProveedorEquipo_Nit = new javax.swing.JRadioButton();
        selectProveedorEquipo_Nombre = new javax.swing.JRadioButton();
        selectPertenencia_Nombre = new javax.swing.JRadioButton();
        selectEquipo_ActivoFijo_Código = new javax.swing.JRadioButton();
        selectEquipo_ActivoFijo_Referecia = new javax.swing.JRadioButton();
        selectEquipo_ActivoFijo_Descripción = new javax.swing.JRadioButton();
        Tarifa_ValorHora_Alquiler = new javax.swing.JRadioButton();
        Tarifa_Año = new javax.swing.JRadioButton();
        Tarifa_Fecha_Inicio = new javax.swing.JRadioButton();
        Tarifa_Fecha_Final = new javax.swing.JRadioButton();
        Tarifa_ValorHora_Operación = new javax.swing.JRadioButton();
        titulo7 = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        todos = new javax.swing.JRadioButton();
        tipo = new javax.swing.JRadioButton();
        marca = new javax.swing.JRadioButton();
        selectMarca = new javax.swing.JComboBox<>();
        selectTipo = new javax.swing.JComboBox<>();
        especifico = new javax.swing.JRadioButton();
        valorBusquedaEspecifica = new javax.swing.JTextField();
        estado = new javax.swing.JRadioButton();
        selectEstado = new javax.swing.JComboBox<>();
        btnConsultarPrincipal = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        paginationPanel = new javax.swing.JPanel();
        pageJComboBox = new javax.swing.JComboBox<>();

        Editar.setText("Seleccionar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InternalFrame_RegistroTarifa.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrame_RegistroTarifa.setClosable(true);
        InternalFrame_RegistroTarifa.setVisible(false);
        InternalFrame_RegistroTarifa.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        equipo_modelo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipo_modelo.setForeground(new java.awt.Color(102, 102, 102));
        equipo_modelo.setText("XXXXXXXXXXXXXXXXXXXXXXXXXX");
        InternalFrame_RegistroTarifa.getContentPane().add(equipo_modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 270, 30));

        titulo38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo38.setForeground(new java.awt.Color(0, 102, 102));
        titulo38.setText("MARCA:");
        InternalFrame_RegistroTarifa.getContentPane().add(titulo38, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 100, 30));

        titulo39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo39.setForeground(new java.awt.Color(0, 102, 102));
        titulo39.setText("MODELO:");
        InternalFrame_RegistroTarifa.getContentPane().add(titulo39, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 100, 30));

        fechaInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicioMouseEntered(evt);
            }
        });
        InternalFrame_RegistroTarifa.getContentPane().add(fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 170, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("INICIO:");
        InternalFrame_RegistroTarifa.getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 70, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Hora");
        InternalFrame_RegistroTarifa.getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, -1, 30));

        horaInicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                horaInicioItemStateChanged(evt);
            }
        });
        InternalFrame_RegistroTarifa.getContentPane().add(horaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 260, 50, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText(":");
        InternalFrame_RegistroTarifa.getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 260, 20, 30));

        minutoInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoInicioActionPerformed(evt);
            }
        });
        InternalFrame_RegistroTarifa.getContentPane().add(minutoInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 260, 50, 30));

        horarioTiempoInicioSolicitudEquiposRegistrar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoInicioSolicitudEquiposRegistrar.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoInicioSolicitudEquiposRegistrar.setText("AM");
        InternalFrame_RegistroTarifa.getContentPane().add(horarioTiempoInicioSolicitudEquiposRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 260, 40, 30));

        fechaFin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFinMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFinMouseEntered(evt);
            }
        });
        InternalFrame_RegistroTarifa.getContentPane().add(fechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 260, 170, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("FINALIZACIÓN:");
        InternalFrame_RegistroTarifa.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 260, 110, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Hora");
        InternalFrame_RegistroTarifa.getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 260, -1, 30));

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
        InternalFrame_RegistroTarifa.getContentPane().add(horaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 260, 50, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel24.setText(":");
        InternalFrame_RegistroTarifa.getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 260, 20, 30));

        minutoFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoFinActionPerformed(evt);
            }
        });
        InternalFrame_RegistroTarifa.getContentPane().add(minutoFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 260, 50, 30));

        horarioTiempoIFinalSolicitudEquiposRegistrar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoIFinalSolicitudEquiposRegistrar.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoIFinalSolicitudEquiposRegistrar.setText("AM");
        InternalFrame_RegistroTarifa.getContentPane().add(horarioTiempoIFinalSolicitudEquiposRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 260, 50, 30));

        titulo40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo40.setForeground(new java.awt.Color(102, 102, 102));
        titulo40.setText("A");
        InternalFrame_RegistroTarifa.getContentPane().add(titulo40, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 110, 40, 30));

        btn_Registrar.setBackground(new java.awt.Color(255, 255, 255));
        btn_Registrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save.png"))); // NOI18N
        btn_Registrar.setText("REGISTRAR TARIFA");
        btn_Registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegistrarActionPerformed(evt);
            }
        });
        InternalFrame_RegistroTarifa.getContentPane().add(btn_Registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 450, 240, 40));

        titulo42.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo42.setForeground(new java.awt.Color(0, 102, 102));
        titulo42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo42.setText("TARIFA DE EQUIPO");
        titulo42.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrame_RegistroTarifa.getContentPane().add(titulo42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1290, 30));

        titulo45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo45.setForeground(new java.awt.Color(0, 102, 102));
        titulo45.setText("DATOS DEL EQUIPO");
        titulo45.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrame_RegistroTarifa.getContentPane().add(titulo45, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 1290, 30));

        titulo46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo46.setForeground(new java.awt.Color(0, 102, 102));
        titulo46.setText("VALORES DE LA NUEVA TARIFA");
        titulo46.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrame_RegistroTarifa.getContentPane().add(titulo46, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 1290, 30));

        titulo47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo47.setForeground(new java.awt.Color(0, 102, 102));
        titulo47.setText("Valores de la tarifa ($):");
        InternalFrame_RegistroTarifa.getContentPane().add(titulo47, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 210, 30));

        equipo_codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipo_codigo.setForeground(new java.awt.Color(102, 102, 102));
        equipo_codigo.setText("XXXXXXXXXXXXXXXXXXXXXXXXXX");
        InternalFrame_RegistroTarifa.getContentPane().add(equipo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 270, 30));

        equipo_tipoEquipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipo_tipoEquipo.setForeground(new java.awt.Color(102, 102, 102));
        equipo_tipoEquipo.setText("XXXXXXXXXXXXXXXXXXXXXXXXXX");
        InternalFrame_RegistroTarifa.getContentPane().add(equipo_tipoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 270, 30));

        equipo_marca.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipo_marca.setForeground(new java.awt.Color(102, 102, 102));
        equipo_marca.setText("XXXXXXXXXXXXXXXXXXXXXXXXXX");
        InternalFrame_RegistroTarifa.getContentPane().add(equipo_marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 220, 30));

        equipo_descripción.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipo_descripción.setForeground(new java.awt.Color(102, 102, 102));
        equipo_descripción.setText("XXXXXXXXXXXXXXXXXXXXXXXXXX");
        InternalFrame_RegistroTarifa.getContentPane().add(equipo_descripción, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 80, 680, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("VALOR HORA OPERACIÓN:");
        InternalFrame_RegistroTarifa.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 310, 170, 30));

        equipo_valorHoraOperación.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        equipo_valorHoraOperación.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equipo_valorHoraOperaciónActionPerformed(evt);
            }
        });
        InternalFrame_RegistroTarifa.getContentPane().add(equipo_valorHoraOperación, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 310, 210, 30));

        equipo_valorHoraAlquiler.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        InternalFrame_RegistroTarifa.getContentPane().add(equipo_valorHoraAlquiler, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 350, 210, 30));

        titulo49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo49.setForeground(new java.awt.Color(0, 102, 102));
        titulo49.setText("TIPO:");
        InternalFrame_RegistroTarifa.getContentPane().add(titulo49, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 80, 30));

        titulo50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo50.setForeground(new java.awt.Color(0, 102, 102));
        titulo50.setText("CÓDIGO:");
        InternalFrame_RegistroTarifa.getContentPane().add(titulo50, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 80, 30));

        titulo48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo48.setForeground(new java.awt.Color(0, 102, 102));
        titulo48.setText("Período de la tarifa:");
        InternalFrame_RegistroTarifa.getContentPane().add(titulo48, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 210, 30));

        titulo41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo41.setForeground(new java.awt.Color(0, 102, 102));
        titulo41.setText("DESCRIPCIÓN:");
        InternalFrame_RegistroTarifa.getContentPane().add(titulo41, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 100, 30));

        titulo43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo43.setForeground(new java.awt.Color(0, 102, 102));
        titulo43.setText("VALORES DE TARIFA ANTERIOR:");
        InternalFrame_RegistroTarifa.getContentPane().add(titulo43, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, 220, 30));

        equipo_tarifaAnterior_fechaFinal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipo_tarifaAnterior_fechaFinal.setForeground(new java.awt.Color(102, 102, 102));
        equipo_tarifaAnterior_fechaFinal.setText("xxxxxxxxxxxxxxxxxxxxxxxx");
        InternalFrame_RegistroTarifa.getContentPane().add(equipo_tarifaAnterior_fechaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 110, 180, 30));

        titulo51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo51.setForeground(new java.awt.Color(102, 102, 102));
        titulo51.setText("Alquiler:");
        InternalFrame_RegistroTarifa.getContentPane().add(titulo51, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, 110, 30));

        titulo53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo53.setForeground(new java.awt.Color(0, 102, 102));
        titulo53.setText("PERÍODO DE TARIFA ANTERIOR:");
        InternalFrame_RegistroTarifa.getContentPane().add(titulo53, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 210, 30));

        equipo_tarifaAnterior_valorAlquiler.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipo_tarifaAnterior_valorAlquiler.setForeground(new java.awt.Color(102, 102, 102));
        equipo_tarifaAnterior_valorAlquiler.setText("xxxxxxxxxxxxxxxxxxxxxxxx");
        InternalFrame_RegistroTarifa.getContentPane().add(equipo_tarifaAnterior_valorAlquiler, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 170, 180, 30));

        equipo_tarifaAnterior_fechaInicio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipo_tarifaAnterior_fechaInicio.setForeground(new java.awt.Color(102, 102, 102));
        equipo_tarifaAnterior_fechaInicio.setText("xxxxxxxxxxxxxxxxxxxxxxxx");
        InternalFrame_RegistroTarifa.getContentPane().add(equipo_tarifaAnterior_fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, 180, 30));

        titulo56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo56.setForeground(new java.awt.Color(102, 102, 102));
        titulo56.setText("Operación:");
        InternalFrame_RegistroTarifa.getContentPane().add(titulo56, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 140, 110, 30));

        equipo_tarifaAnterior_valorOperacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipo_tarifaAnterior_valorOperacion.setForeground(new java.awt.Color(102, 102, 102));
        equipo_tarifaAnterior_valorOperacion.setText("xxxxxxxxxxxxxxxxxxxxxxxx");
        InternalFrame_RegistroTarifa.getContentPane().add(equipo_tarifaAnterior_valorOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 140, 180, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("VALOR HORA ALQUILER:");
        InternalFrame_RegistroTarifa.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, 160, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrame_RegistroTarifa.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 1290, 70));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrame_RegistroTarifa.getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 1290, 170));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrame_RegistroTarifa.getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 1290, 230));

        add(InternalFrame_RegistroTarifa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1360, 680));

        InternalFrameSelectorCampos.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrameSelectorCampos.setClosable(true);
        InternalFrameSelectorCampos.setVisible(false);
        InternalFrameSelectorCampos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo1.setText("TIPO EQUIPO");
        InternalFrameSelectorCampos.getContentPane().add(titulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 190, 20));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternalFrameSelectorCampos.getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 50, 10, 290));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCamposAplicarConfig.png"))); // NOI18N
        jButton2.setText("Aplicar Configuración");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, 230, 30));

        selectAll.setBackground(new java.awt.Color(255, 255, 255));
        selectAll.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        selectAll.setText("Todos");
        selectAll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectAll.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectAllItemStateChanged(evt);
            }
        });
        selectAll.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                selectAllStateChanged(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 7, 110, 20));
        InternalFrameSelectorCampos.getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 940, 10));

        selectEquipo_Código.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_Código.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_Código.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_Código.setText("Equipo_Código");
        selectEquipo_Código.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_Código, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 190, -1));

        selectEquipo_CódigoBarra.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_CódigoBarra.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_CódigoBarra.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_CódigoBarra.setText("Equipo_CódigoBarra");
        selectEquipo_CódigoBarra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_CódigoBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 190, -1));

        selectEquipo_Capacidad.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_Capacidad.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_Capacidad.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_Capacidad.setText("Equipo_Capacidad");
        selectEquipo_Capacidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_Capacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 190, -1));

        selectEquipo_Marca.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_Marca.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_Marca.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_Marca.setText("Equipo_Marca");
        selectEquipo_Marca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_Marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 190, -1));

        selectEquipo_Modelo.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_Modelo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_Modelo.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_Modelo.setText("Equipo_Modelo");
        selectEquipo_Modelo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_Modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 190, -1));

        selectEquipo_Serial.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_Serial.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_Serial.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_Serial.setText("Equipo_Serial");
        selectEquipo_Serial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_Serial, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 190, -1));

        selectEquipo_Descripción.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_Descripción.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_Descripción.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_Descripción.setText("Equipo_Descripción");
        selectEquipo_Descripción.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_Descripción, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 190, -1));

        selectEquipo_Producto.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_Producto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_Producto.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_Producto.setText("Equipo_Producto");
        selectEquipo_Producto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_Producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 190, -1));

        selectEquipo_Observación.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_Observación.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_Observación.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_Observación.setText("Equipo_Observación");
        selectEquipo_Observación.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_Observación, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 190, -1));

        selectProveedorEquipo_Código.setBackground(new java.awt.Color(255, 255, 255));
        selectProveedorEquipo_Código.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectProveedorEquipo_Código.setForeground(new java.awt.Color(51, 51, 51));
        selectProveedorEquipo_Código.setText("ProveedorEquipo_Código");
        selectProveedorEquipo_Código.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectProveedorEquipo_Código, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 190, -1));

        selectPertenencia_Código.setBackground(new java.awt.Color(255, 255, 255));
        selectPertenencia_Código.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectPertenencia_Código.setForeground(new java.awt.Color(51, 51, 51));
        selectPertenencia_Código.setText("Pertenencia_Código");
        selectPertenencia_Código.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectPertenencia_Código, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 190, -1));

        selectClasificador2_Código.setBackground(new java.awt.Color(255, 255, 255));
        selectClasificador2_Código.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectClasificador2_Código.setForeground(new java.awt.Color(51, 51, 51));
        selectClasificador2_Código.setText("Clasificador2_Código");
        selectClasificador2_Código.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectClasificador2_Código, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, 190, -1));

        selectClasificador2_Descripción.setBackground(new java.awt.Color(255, 255, 255));
        selectClasificador2_Descripción.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectClasificador2_Descripción.setForeground(new java.awt.Color(51, 51, 51));
        selectClasificador2_Descripción.setText("Clasificador2_Descripción");
        selectClasificador2_Descripción.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectClasificador2_Descripción, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, 190, -1));

        selectClasificador1_Código.setBackground(new java.awt.Color(255, 255, 255));
        selectClasificador1_Código.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectClasificador1_Código.setForeground(new java.awt.Color(51, 51, 51));
        selectClasificador1_Código.setText("Clasificador1_Código");
        selectClasificador1_Código.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectClasificador1_Código, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 190, -1));

        selectClasificador1_Descripción.setBackground(new java.awt.Color(255, 255, 255));
        selectClasificador1_Descripción.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectClasificador1_Descripción.setForeground(new java.awt.Color(51, 51, 51));
        selectClasificador1_Descripción.setText("Clasificador1_Descripción");
        selectClasificador1_Descripción.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectClasificador1_Descripción, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, 190, -1));

        selectTipoEquipo_Nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectTipoEquipo_Nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectTipoEquipo_Nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectTipoEquipo_Nombre.setText("TipoEquipo_Nombre");
        selectTipoEquipo_Nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectTipoEquipo_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 190, -1));

        selectTipoEquipo_Código.setBackground(new java.awt.Color(255, 255, 255));
        selectTipoEquipo_Código.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectTipoEquipo_Código.setForeground(new java.awt.Color(51, 51, 51));
        selectTipoEquipo_Código.setText("TipoEquipo_Código");
        selectTipoEquipo_Código.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectTipoEquipo_Código, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 190, -1));

        selectEquipo_Estado.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_Estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_Estado.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_Estado.setText("Equipo_Estado");
        selectEquipo_Estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_Estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 190, -1));

        titulo2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo2.setText("SELECTOR DE CAMPOS");
        titulo2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 940, 30));
        InternalFrameSelectorCampos.getContentPane().add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 240, 10));

        titulo3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo3.setText("CLASIFICADOR 2");
        InternalFrameSelectorCampos.getContentPane().add(titulo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 190, 20));
        InternalFrameSelectorCampos.getContentPane().add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 240, 10));

        titulo4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo4.setText("CLASIFICADOR 1");
        InternalFrameSelectorCampos.getContentPane().add(titulo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 190, 20));
        InternalFrameSelectorCampos.getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 240, 10));

        titulo5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo5.setText("PROVEEDOR EQUIPO");
        InternalFrameSelectorCampos.getContentPane().add(titulo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 190, 20));
        InternalFrameSelectorCampos.getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 940, 10));

        titulo6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo6.setText("TARIFA");
        InternalFrameSelectorCampos.getContentPane().add(titulo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, 190, 20));
        InternalFrameSelectorCampos.getContentPane().add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 240, 10));
        InternalFrameSelectorCampos.getContentPane().add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, 240, 10));

        titulo13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo13.setText("EQUIPOS DE ERP");
        InternalFrameSelectorCampos.getContentPane().add(titulo13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 190, 20));
        InternalFrameSelectorCampos.getContentPane().add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 230, 10));

        jSeparator18.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternalFrameSelectorCampos.getContentPane().add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 50, 10, 70));

        jSeparator19.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternalFrameSelectorCampos.getContentPane().add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 10, 320));

        jSeparator20.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternalFrameSelectorCampos.getContentPane().add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 10, 320));

        jSeparator21.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternalFrameSelectorCampos.getContentPane().add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 10, 320));
        InternalFrameSelectorCampos.getContentPane().add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, 240, 10));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCamposDefauls.png"))); // NOI18N
        jButton3.setText("Campos Predeterminados");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 230, 30));

        selectEquipo_Referencia.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_Referencia.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_Referencia.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_Referencia.setText("Equipo_Referencia");
        selectEquipo_Referencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_Referencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 190, -1));

        selectProveedorEquipo_Nit.setBackground(new java.awt.Color(255, 255, 255));
        selectProveedorEquipo_Nit.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectProveedorEquipo_Nit.setForeground(new java.awt.Color(51, 51, 51));
        selectProveedorEquipo_Nit.setText("ProveedorEquipo_Nit");
        selectProveedorEquipo_Nit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectProveedorEquipo_Nit, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 310, 190, -1));

        selectProveedorEquipo_Nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectProveedorEquipo_Nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectProveedorEquipo_Nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectProveedorEquipo_Nombre.setText("ProveedorEquipo_Nombre");
        selectProveedorEquipo_Nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectProveedorEquipo_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, 190, -1));

        selectPertenencia_Nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectPertenencia_Nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectPertenencia_Nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectPertenencia_Nombre.setText("Pertenencia_Nombre");
        selectPertenencia_Nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectPertenencia_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 190, -1));

        selectEquipo_ActivoFijo_Código.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_ActivoFijo_Código.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_ActivoFijo_Código.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_ActivoFijo_Código.setText("Equipo_ActivoFijo_Código");
        selectEquipo_ActivoFijo_Código.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_ActivoFijo_Código, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 190, -1));

        selectEquipo_ActivoFijo_Referecia.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_ActivoFijo_Referecia.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_ActivoFijo_Referecia.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_ActivoFijo_Referecia.setText("Equipo_ActivoFijo_Referecia");
        selectEquipo_ActivoFijo_Referecia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_ActivoFijo_Referecia, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 190, -1));

        selectEquipo_ActivoFijo_Descripción.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_ActivoFijo_Descripción.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_ActivoFijo_Descripción.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_ActivoFijo_Descripción.setText("Equipo_ActivoFijo_Descripción");
        selectEquipo_ActivoFijo_Descripción.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_ActivoFijo_Descripción, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 190, -1));

        Tarifa_ValorHora_Alquiler.setBackground(new java.awt.Color(255, 255, 255));
        Tarifa_ValorHora_Alquiler.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Tarifa_ValorHora_Alquiler.setForeground(new java.awt.Color(51, 51, 51));
        Tarifa_ValorHora_Alquiler.setText("Tarifa_ValorHora_Alquiler");
        Tarifa_ValorHora_Alquiler.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Tarifa_ValorHora_Alquiler, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 250, 190, -1));

        Tarifa_Año.setBackground(new java.awt.Color(255, 255, 255));
        Tarifa_Año.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Tarifa_Año.setForeground(new java.awt.Color(51, 51, 51));
        Tarifa_Año.setText("Tarifa_Año");
        Tarifa_Año.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Tarifa_Año, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, 190, -1));

        Tarifa_Fecha_Inicio.setBackground(new java.awt.Color(255, 255, 255));
        Tarifa_Fecha_Inicio.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Tarifa_Fecha_Inicio.setForeground(new java.awt.Color(51, 51, 51));
        Tarifa_Fecha_Inicio.setText("Tarifa_Fecha_Inicio");
        Tarifa_Fecha_Inicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Tarifa_Fecha_Inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 190, 190, -1));

        Tarifa_Fecha_Final.setBackground(new java.awt.Color(255, 255, 255));
        Tarifa_Fecha_Final.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Tarifa_Fecha_Final.setForeground(new java.awt.Color(51, 51, 51));
        Tarifa_Fecha_Final.setText("Tarifa_Fecha_Final");
        Tarifa_Fecha_Final.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Tarifa_Fecha_Final, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, 190, -1));

        Tarifa_ValorHora_Operación.setBackground(new java.awt.Color(255, 255, 255));
        Tarifa_ValorHora_Operación.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Tarifa_ValorHora_Operación.setForeground(new java.awt.Color(51, 51, 51));
        Tarifa_ValorHora_Operación.setText("Tarifa_ValorHora_Operación");
        Tarifa_ValorHora_Operación.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Tarifa_ValorHora_Operación, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, 190, -1));

        titulo7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo7.setText("PERTENENCIA");
        InternalFrameSelectorCampos.getContentPane().add(titulo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 190, 20));

        add(InternalFrameSelectorCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1130, 570));

        titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo.setText("REGISTRO DE TARIFAS EN LOS EQUIPOS");
        add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 590, 30));

        tabla = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                /*if (colIndex == 9 || colIndex == 10 || colIndex ==11)  {
                    return true;
                }else{
                    return false;
                } */
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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 1240, 390));

        todos.setBackground(new java.awt.Color(255, 255, 255));
        todos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        todos.setText("Todos");
        todos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                todosStateChanged(evt);
            }
        });
        add(todos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 30));

        tipo.setBackground(new java.awt.Color(255, 255, 255));
        tipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tipo.setText("Tipo");
        tipo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tipoStateChanged(evt);
            }
        });
        add(tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, 30));

        marca.setBackground(new java.awt.Color(255, 255, 255));
        marca.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        marca.setText("Marca");
        marca.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                marcaStateChanged(evt);
            }
        });
        add(marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, 30));

        selectMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMarcaActionPerformed(evt);
            }
        });
        add(selectMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 390, 30));

        selectTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectTipoActionPerformed(evt);
            }
        });
        add(selectTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 390, 30));

        especifico.setBackground(new java.awt.Color(255, 255, 255));
        especifico.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        especifico.setText("Especifico");
        especifico.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                especificoStateChanged(evt);
            }
        });
        add(especifico, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, -1, 30));
        add(valorBusquedaEspecifica, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 50, 370, 30));

        estado.setBackground(new java.awt.Color(255, 255, 255));
        estado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        estado.setText("Estado");
        estado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                estadoStateChanged(evt);
            }
        });
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, -1, 30));

        selectEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        add(selectEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 90, 370, 30));

        btnConsultarPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultarPrincipal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnConsultarPrincipal.setText("CONSULTAR");
        btnConsultarPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarPrincipalActionPerformed(evt);
            }
        });
        add(btnConsultarPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 90, 140, 30));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/ExportarExcel.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 80, 50, 40));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Exportar");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 60, 50, 30));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCampo.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 80, 50, 40));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Campos");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 60, 40, 30));

        paginationPanel.setBackground(new java.awt.Color(255, 255, 255));
        add(paginationPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 1240, 70));

        add(pageJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 10, 30, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void todosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_todosStateChanged
        if(todos.isSelected()){
            tipo.setSelected(false);
            marca.setSelected(false);
            especifico.setSelected(false);
            estado.setSelected(false);
        }else{
        }
    }//GEN-LAST:event_todosStateChanged

    private void tipoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tipoStateChanged
        if(tipo.isSelected() || marca.isSelected()|| especifico.isSelected() || estado.isSelected()){
            todos.setSelected(false);
        }
    }//GEN-LAST:event_tipoStateChanged

    private void marcaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_marcaStateChanged
        if(tipo.isSelected() || marca.isSelected()|| especifico.isSelected() || estado.isSelected()){
            todos.setSelected(false);
        }
    }//GEN-LAST:event_marcaStateChanged

    private void selectMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMarcaActionPerformed

    private void selectTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectTipoActionPerformed

    private void especificoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_especificoStateChanged
        if(tipo.isSelected() || marca.isSelected()|| especifico.isSelected() || estado.isSelected()){
            todos.setSelected(false);
        }
    }//GEN-LAST:event_especificoStateChanged

    private void estadoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_estadoStateChanged
        if(tipo.isSelected() || marca.isSelected()|| especifico.isSelected() || estado.isSelected()){
            todos.setSelected(false);
        }
    }//GEN-LAST:event_estadoStateChanged

    private void btnConsultarPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarPrincipalActionPerformed
        paginationPanel.removeAll();
        validarSeleccionCampos();
        try {
            generarListadoEquipo();
        } catch (SQLException ex) {
            Logger.getLogger(Equipo_Tarifa.class.getName()).log(Level.SEVERE, null, ex);
        }
        resizeColumnWidth(tabla);
    }//GEN-LAST:event_btnConsultarPrincipalActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        if(listado != null){
           //ArrayList<String> colmnas={"Hola", "Hola", "Hola"});
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
                    listado.size();
                   
                    //int numFila=tabla.getRowCount(), numColumna=tabla.getColumnCount();
                    int numFila=listado.size();
                    int numColumna=encabezadoTabla.size();
                    if(archivo.getName().endsWith("xls")){
                        wb = new HSSFWorkbook();
                    }else{
                        wb= new XSSFWorkbook();
                    }
                    Sheet hoja= wb.createSheet("Listado de Tarifas");
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
                                        case "TipoEquipo_Código":{
                                            data=""+listado.get(i).getTipoEquipo().getCodigo();
                                            break;
                                         }
                                         case "TipoEquipo_Nombre":{
                                            data=""+listado.get(i).getTipoEquipo().getDescripcion();
                                            break;
                                         }
                                         case "CódigoBarra":{
                                            data=""+listado.get(i).getCodigo_barra();
                                            break;
                                         }
                                         case "Referencia":{
                                            data=""+listado.get(i).getReferencia();
                                            break;
                                         }
                                         case "Producto":{
                                            data=""+listado.get(i).getProducto();
                                            break;
                                         }
                                         case "Capacidad":{
                                            data=""+listado.get(i).getCapacidad();
                                            break;
                                         }
                                         case "Marca":{
                                            data=""+listado.get(i).getMarca();
                                            break;
                                         }
                                         case "Modelo":{
                                            data=""+listado.get(i).getModelo();
                                            break;
                                         }
                                         case "Serial":{
                                            data=""+listado.get(i).getSerial();
                                            break;
                                         }
                                         case "Descripción":{
                                            data=""+listado.get(i).getDescripcion();
                                            break;
                                         }
                                         case "Observación":{
                                            data=""+listado.get(i).getObservacion();
                                            break;
                                         }
                                         case "Estado":{
                                            data=""+listado.get(i).getEstado();
                                            break;
                                         }
                                         case "ActivoFijo_Código":{
                                            data=""+listado.get(i).getActivoFijo_codigo();
                                            break;
                                         }
                                         case "ActivoFijo_Referecia":{
                                            data=""+listado.get(i).getActivoFijo_referencia();
                                            break;
                                         }
                                         case "ActivoFijo_Descripción":{
                                            data=""+listado.get(i).getActivoFijo_descripcion();
                                            break;
                                         }
                                         case "Clasificador1_Código":{
                                            data=""+listado.get(i).getClasificador1().getCodigo();
                                            break;
                                         }
                                         case "Clasificador1_Descripción":{
                                            data=""+listado.get(i).getClasificador1().getDescripcion();
                                            break;
                                         }
                                         case "Clasificador2_Código":{
                                            data=""+listado.get(i).getClasificador2().getCodigo();
                                            break;
                                         }
                                         case "Clasificador2_Descripción":{
                                            data=""+listado.get(i).getClasificador2().getDescripcion();
                                            break;
                                         }
                                         case "ProveedorEquipo_Código":{
                                            data=""+listado.get(i).getProveedorEquipo().getCodigo();
                                            break;
                                         }
                                         case "ProveedorEquipo_Nit":{
                                            data=""+listado.get(i).getProveedorEquipo().getNit();
                                            break;
                                         }
                                         case "ProveedorEquipo_Nombre":{
                                            data=""+listado.get(i).getProveedorEquipo().getDescripcion();
                                            break;
                                         }
                                         case "Pertenencia_Código":{
                                            data=""+listado.get(i).getPertenenciaEquipo().getCodigo();
                                            break;
                                         }
                                         case "Pertenencia_Nombre":{
                                            data=""+listado.get(i).getPertenenciaEquipo().getDescripcion();
                                            break;
                                         }
                                         case "Tarifa_Año":{
                                            data=""+listado.get(i).getTarifaEquipoAnterior().getAño();
                                            break;
                                         }
                                         case "Tarifa_Fecha_Inicio":{
                                            data=""+listado.get(i).getTarifaEquipoAnterior().getFechaHoraInicio();
                                            break;
                                         }
                                         case "Tarifa_Fecha_Final":{
                                            data=""+listado.get(i).getTarifaEquipoAnterior().getFechaHoraFin();
                                            break;
                                         }
                                         case "Tarifa_ValorHora_Operación":{
                                            data=""+listado.get(i).getTarifaEquipoAnterior().getValorHoraOperacion();
                                            break;
                                         }
                                         case "Tarifa_ValorHora_Alquiler":{
                                            data=""+listado.get(i).getTarifaEquipoAnterior().getValorHoraAlquiler();
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
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        InternalFrameSelectorCampos.show(true);
    }//GEN-LAST:event_jLabel5MouseClicked

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

    private void selectAllStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_selectAllStateChanged
        if(selectAll.isSelected()){
            selectorCampoTodos();
        }else{
            selectorCampoNinguno();
        }
    }//GEN-LAST:event_selectAllStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        selectorCampoPorDefecto();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void equipo_valorHoraOperaciónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equipo_valorHoraOperaciónActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_equipo_valorHoraOperaciónActionPerformed

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

    private void btn_RegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegistrarActionPerformed
         String fechaInicioTarifa="";
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
            fechaInicioTarifa=anoI+"-"+mesI+"-"+diaI+" "+horaInicio.getSelectedItem().toString()+":"+minutoInicio.getSelectedItem().toString()+":00.0";
            String fechaFinTarifa="";
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
                fechaFinTarifa=anoF+"-"+mesF+"-"+diaF+" "+horaFin.getSelectedItem().toString()+":"+minutoFin.getSelectedItem().toString()+":00.0";
                int resultDosFechas=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorEntreDosFechas(anoI, mesI, diaI, horaInicio.getSelectedItem().toString(), minutoInicio.getSelectedItem().toString(), anoF, mesF, diaF, horaFin.getSelectedItem().toString(), minutoFin.getSelectedItem().toString()));
                if(!fechaInicioTarifa.equals(fechaFinTarifa)){
                    if(resultDosFechas < 0){
                        JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser mayor a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                    }else{
                        try{
                            if(!equipo_valorHoraOperación.getText().equals("")){
                                Integer.parseInt(equipo_valorHoraOperación.getText());
                                try{
                                    if(!equipo_valorHoraAlquiler.getText().equals("")){
                                        Integer.parseInt(equipo_valorHoraAlquiler.getText());

                                        TarifaEquipo tarifaEquipoNueva = new TarifaEquipo();
                                        tarifaEquipoNueva.setCodigoEquipo(equipo.getCodigo());
                                        tarifaEquipoNueva.setFechaHoraInicio(fechaInicioTarifa);
                                        tarifaEquipoNueva.setFechaHoraFin(fechaFinTarifa);
                                        tarifaEquipoNueva.setValorHoraOperacion(equipo_valorHoraOperación.getText());
                                        tarifaEquipoNueva.setValorHoraAlquiler(equipo_valorHoraAlquiler.getText());
                                        equipo.setTarifaEquipoNueva(tarifaEquipoNueva);

                                        int respuesta=new ControlDB_Equipo(tipoConexion).registrarTarifaParaUnEquipo(equipo, user);
                                        if(respuesta==1){
                                            JOptionPane.showMessageDialog(null, "Se registro la tarifa de manera exitosa");
                                            try {
                                                generarListadoEquipo();
                                                InternalFrame_RegistroTarifa.show(false);
                                            } catch (SQLException ex) {
                                                Logger.getLogger(Equipo_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }else{
                                        if(respuesta==0){
                                            JOptionPane.showMessageDialog(null, "No se pudo registrar la tarifa del equipos, valide datos", "Advertencia", JOptionPane.ERROR_MESSAGE);
                                        }
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(null,"El valor de la hora por alquiler del equipo no puede estar vacio","Error!!",JOptionPane.ERROR_MESSAGE);  
                                    }
                                }catch(Exception e){
                                    JOptionPane.showMessageDialog(null,"El valor de la hora del alquiler debe ser númerico y no puede contener punto","Error!!",JOptionPane.ERROR_MESSAGE);  
                                }
                            }else{
                                JOptionPane.showMessageDialog(null,"El valor de la hora por operación del equipo no puede estar vacio","Error!!",JOptionPane.ERROR_MESSAGE);  
                            }
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(null,"El valor de la hora de Operación debe ser númerico y no puede contener punto","Error!!",JOptionPane.ERROR_MESSAGE);  
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"La fecha de incio y fecha de finalización no pueden ser iguales","Error!!",JOptionPane.ERROR_MESSAGE);  
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Finalización","Advertencia",JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la Fecha de Inicio","Advertencia",JOptionPane.ERROR_MESSAGE);
        }  
    }//GEN-LAST:event_btn_RegistrarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        int fila1;
        try{
            fila1=tabla.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                int posicion = paginadorDeTabla.getPosicionador();
                if(posicion != -1){
                    fila1 = fila1 +posicion;
                }
                if(listado != null){
                    //cargamos los datos del equipo seleccionado
                    equipo= listado.get(fila1);
                    
                    //borramos la información anterior de la interfaz
                    equipo_codigo.setText("");
                    equipo_tipoEquipo.setText("");
                    equipo_modelo.setText("");
                    equipo_marca.setText("");
                    equipo_descripción.setText("");  
                    equipo_tarifaAnterior_fechaInicio.setText("");
                    equipo_tarifaAnterior_fechaFinal.setText("");
                    equipo_tarifaAnterior_valorOperacion.setText("");
                    equipo_tarifaAnterior_valorAlquiler.setText("");
                    
                    //cargamos la nueva información en la interfaz
                    equipo_codigo.setText(equipo.getCodigo());
                    equipo_tipoEquipo.setText(equipo.getTipoEquipo().getDescripcion());
                    equipo_modelo.setText(equipo.getModelo());
                    equipo_marca.setText(equipo.getMarca());
                    equipo_descripción.setText(equipo.getDescripcion());  
                    equipo_tarifaAnterior_fechaInicio.setText(equipo.getTarifaEquipoAnterior().getFechaHoraInicio());
                    equipo_tarifaAnterior_fechaFinal.setText(equipo.getTarifaEquipoAnterior().getFechaHoraFin());
                    equipo_tarifaAnterior_valorOperacion.setText(equipo.getTarifaEquipoAnterior().getValorHoraOperacion());
                    equipo_tarifaAnterior_valorAlquiler.setText(equipo.getTarifaEquipoAnterior().getValorHoraAlquiler());

                    //Mostramos la ventana interna
                    InternalFrame_RegistroTarifa.show(true);
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() == 2) {
            int fila1;
            try{
                fila1=tabla.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    int posicion = paginadorDeTabla.getPosicionador();
                    if(posicion != -1){
                        fila1 = fila1 +posicion;
                    }
                    if(listado != null){
                        //cargamos los datos del equipo seleccionado
                        equipo= listado.get(fila1);

                        //borramos la información anterior de la interfaz
                        equipo_codigo.setText("");
                        equipo_tipoEquipo.setText("");
                        equipo_modelo.setText("");
                        equipo_marca.setText("");
                        equipo_descripción.setText("");  
                        equipo_tarifaAnterior_fechaInicio.setText("");
                        equipo_tarifaAnterior_fechaFinal.setText("");
                        equipo_tarifaAnterior_valorOperacion.setText("");
                        equipo_tarifaAnterior_valorAlquiler.setText("");

                        //cargamos la nueva información en la interfaz
                        equipo_codigo.setText(equipo.getCodigo());
                        equipo_tipoEquipo.setText(equipo.getTipoEquipo().getDescripcion());
                        equipo_modelo.setText(equipo.getModelo());
                        equipo_marca.setText(equipo.getMarca());
                        equipo_descripción.setText(equipo.getDescripcion());  
                        equipo_tarifaAnterior_fechaInicio.setText(equipo.getTarifaEquipoAnterior().getFechaHoraInicio());
                        equipo_tarifaAnterior_fechaFinal.setText(equipo.getTarifaEquipoAnterior().getFechaHoraFin());
                        equipo_tarifaAnterior_valorOperacion.setText(equipo.getTarifaEquipoAnterior().getValorHoraOperacion());
                        equipo_tarifaAnterior_valorAlquiler.setText(equipo.getTarifaEquipoAnterior().getValorHoraAlquiler());

                        //Mostramos la ventana interna
                        InternalFrame_RegistroTarifa.show(true);
                    }
                }
            }catch(Exception e){
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JInternalFrame InternalFrameSelectorCampos;
    private javax.swing.JInternalFrame InternalFrame_RegistroTarifa;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JRadioButton Tarifa_Año;
    private javax.swing.JRadioButton Tarifa_Fecha_Final;
    private javax.swing.JRadioButton Tarifa_Fecha_Inicio;
    private javax.swing.JRadioButton Tarifa_ValorHora_Alquiler;
    private javax.swing.JRadioButton Tarifa_ValorHora_Operación;
    private javax.swing.JButton btnConsultarPrincipal;
    private javax.swing.JButton btn_Registrar;
    private javax.swing.JLabel equipo_codigo;
    private javax.swing.JLabel equipo_descripción;
    private javax.swing.JLabel equipo_marca;
    private javax.swing.JLabel equipo_modelo;
    private javax.swing.JLabel equipo_tarifaAnterior_fechaFinal;
    private javax.swing.JLabel equipo_tarifaAnterior_fechaInicio;
    private javax.swing.JLabel equipo_tarifaAnterior_valorAlquiler;
    private javax.swing.JLabel equipo_tarifaAnterior_valorOperacion;
    private javax.swing.JLabel equipo_tipoEquipo;
    private javax.swing.JTextField equipo_valorHoraAlquiler;
    private javax.swing.JTextField equipo_valorHoraOperación;
    private javax.swing.JRadioButton especifico;
    private javax.swing.JRadioButton estado;
    private com.toedter.calendar.JDateChooser fechaFin;
    private com.toedter.calendar.JDateChooser fechaInicio;
    private javax.swing.JComboBox<String> horaFin;
    private javax.swing.JComboBox<String> horaInicio;
    private javax.swing.JLabel horarioTiempoIFinalSolicitudEquiposRegistrar;
    private javax.swing.JLabel horarioTiempoInicioSolicitudEquiposRegistrar;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JRadioButton marca;
    private javax.swing.JComboBox<String> minutoFin;
    private javax.swing.JComboBox<String> minutoInicio;
    private javax.swing.JComboBox<Integer> pageJComboBox;
    public javax.swing.JPanel paginationPanel;
    private javax.swing.JRadioButton selectAll;
    private javax.swing.JRadioButton selectClasificador1_Código;
    private javax.swing.JRadioButton selectClasificador1_Descripción;
    private javax.swing.JRadioButton selectClasificador2_Código;
    private javax.swing.JRadioButton selectClasificador2_Descripción;
    private javax.swing.JRadioButton selectEquipo_ActivoFijo_Código;
    private javax.swing.JRadioButton selectEquipo_ActivoFijo_Descripción;
    private javax.swing.JRadioButton selectEquipo_ActivoFijo_Referecia;
    private javax.swing.JRadioButton selectEquipo_Capacidad;
    private javax.swing.JRadioButton selectEquipo_Código;
    private javax.swing.JRadioButton selectEquipo_CódigoBarra;
    private javax.swing.JRadioButton selectEquipo_Descripción;
    private javax.swing.JRadioButton selectEquipo_Estado;
    private javax.swing.JRadioButton selectEquipo_Marca;
    private javax.swing.JRadioButton selectEquipo_Modelo;
    private javax.swing.JRadioButton selectEquipo_Observación;
    private javax.swing.JRadioButton selectEquipo_Producto;
    private javax.swing.JRadioButton selectEquipo_Referencia;
    private javax.swing.JRadioButton selectEquipo_Serial;
    private javax.swing.JComboBox<String> selectEstado;
    private javax.swing.JComboBox<String> selectMarca;
    private javax.swing.JRadioButton selectPertenencia_Código;
    private javax.swing.JRadioButton selectPertenencia_Nombre;
    private javax.swing.JRadioButton selectProveedorEquipo_Código;
    private javax.swing.JRadioButton selectProveedorEquipo_Nit;
    private javax.swing.JRadioButton selectProveedorEquipo_Nombre;
    private javax.swing.JComboBox<String> selectTipo;
    private javax.swing.JRadioButton selectTipoEquipo_Código;
    private javax.swing.JRadioButton selectTipoEquipo_Nombre;
    private javax.swing.JTable tabla;
    private javax.swing.JRadioButton tipo;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel titulo1;
    private javax.swing.JLabel titulo13;
    private javax.swing.JLabel titulo2;
    private javax.swing.JLabel titulo3;
    private javax.swing.JLabel titulo38;
    private javax.swing.JLabel titulo39;
    private javax.swing.JLabel titulo4;
    private javax.swing.JLabel titulo40;
    private javax.swing.JLabel titulo41;
    private javax.swing.JLabel titulo42;
    private javax.swing.JLabel titulo43;
    private javax.swing.JLabel titulo45;
    private javax.swing.JLabel titulo46;
    private javax.swing.JLabel titulo47;
    private javax.swing.JLabel titulo48;
    private javax.swing.JLabel titulo49;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo50;
    private javax.swing.JLabel titulo51;
    private javax.swing.JLabel titulo53;
    private javax.swing.JLabel titulo56;
    private javax.swing.JLabel titulo6;
    private javax.swing.JLabel titulo7;
    private javax.swing.JRadioButton todos;
    private javax.swing.JTextField valorBusquedaEspecifica;
    // End of variables declaration//GEN-END:variables
    public void generarListadoEquipo() throws SQLException{
        String sql="";
        int contador=0;
        if(todos.isSelected()){
            try {
                tabla_Listar(sql,"");
            } catch (SQLException ex) {
                Logger.getLogger(Equipo_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            if(tipo.isSelected()){
                if(contador==0){
                    sql += " WHERE [te_desc] LIKE '"+selectTipo.getSelectedItem().toString()+"'";
                    contador++;
                }else{
                    sql += " AND [te_desc] LIKE '"+selectTipo.getSelectedItem().toString()+"'";
                    contador++;
                }
            }
            if(marca.isSelected()){
                if(contador==0){
                    sql += " WHERE [eq_marca] LIKE '"+selectMarca.getSelectedItem().toString()+"'";
                    contador++;
                }else{
                    sql += " AND [eq_marca] LIKE '"+selectMarca.getSelectedItem().toString()+"'";
                    contador++;
                }
            }
            if(estado.isSelected()){
                if(contador==0){
                    if(selectEstado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                        sql += " WHERE [eq_estad]=1 ";
                    }else{
                        if(selectEstado.getSelectedItem().toString().equalsIgnoreCase("INACTIVO")){
                            sql += " WHERE [eq_estad]=0 ";
                        }
                    }   
                    contador++;
                }else{
                    if(selectEstado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                        sql += " AND [eq_estad]=1 ";
                    }else{
                        if(selectEstado.getSelectedItem().toString().equalsIgnoreCase("INACTIVO")){
                            sql += " AND [eq_estad]=0 ";
                        }
                    }   
                    contador++;
                }
            }
            String busquedaEspecifica="";
            if(especifico.isSelected()){
                if(contador==0){
                    sql += " WHERE ([eq_desc] LIKE ? OR [eq_cdgo] LIKE '%"+valorBusquedaEspecifica.getText()+"%')";
                    busquedaEspecifica="%"+valorBusquedaEspecifica.getText()+"%";
                    contador++;
                }else{
                    sql += " AND ([eq_desc] LIKE ? OR [eq_cdgo] LIKE '%"+valorBusquedaEspecifica.getText()+"%')";
                    busquedaEspecifica="%"+valorBusquedaEspecifica.getText()+"%";
                    contador++;
                }
            }
            try {
                tabla_Listar(sql,busquedaEspecifica);
            } catch (SQLException ex) {
                Logger.getLogger(Equipo_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   
    public void selectorCampoPorDefecto(){
        //Campos Activos
        selectEquipo_Código.setSelected(true);
        selectEquipo_Producto.setSelected(false);
        selectEquipo_Capacidad.setSelected(false);
        selectEquipo_Marca.setSelected(false);
        selectEquipo_Modelo.setSelected(false);
        selectEquipo_Serial.setSelected(false);
        selectEquipo_Descripción.setSelected(true);
        selectTipoEquipo_Nombre.setSelected(true);
        selectPertenencia_Nombre.setSelected(true);
        //Campos Inactivos
        selectEquipo_CódigoBarra.setSelected(false);
        selectEquipo_Referencia.setSelected(false);
        selectEquipo_Observación.setSelected(false);
        selectEquipo_Estado.setSelected(false);
        selectEquipo_ActivoFijo_Código.setSelected(false);
        selectEquipo_ActivoFijo_Referecia.setSelected(false);
        selectEquipo_ActivoFijo_Descripción.setSelected(false);
        selectTipoEquipo_Código.setSelected(false);
        selectClasificador1_Código.setSelected(false);
        selectClasificador1_Descripción.setSelected(false);
        selectClasificador2_Código.setSelected(false);
        selectClasificador2_Descripción.setSelected(false);
        selectProveedorEquipo_Código.setSelected(false);
        selectProveedorEquipo_Nit.setSelected(false);
        selectProveedorEquipo_Nombre.setSelected(false);
        selectPertenencia_Código.setSelected(false);
        selectPertenencia_Nombre.setSelected(false);
        Tarifa_Año.setSelected(true); 
        Tarifa_Fecha_Inicio.setSelected(true); 
        Tarifa_Fecha_Final.setSelected(true); 
        Tarifa_ValorHora_Operación.setSelected(true); 
        Tarifa_ValorHora_Alquiler.setSelected(true); 
    }
    public void selectorCampoTodos(){
        //Campos Activos
        selectEquipo_Código.setSelected(true);
        selectEquipo_Producto.setSelected(true);
        selectEquipo_Capacidad.setSelected(true);
        selectEquipo_Marca.setSelected(true);
        selectEquipo_Modelo.setSelected(true);
        selectEquipo_Serial.setSelected(true);
        selectEquipo_Descripción.setSelected(true);
        selectTipoEquipo_Nombre.setSelected(true);
        selectPertenencia_Nombre.setSelected(true);
        selectEquipo_CódigoBarra.setSelected(true);
        selectEquipo_Referencia.setSelected(true);
        selectEquipo_Observación.setSelected(true);
        selectEquipo_Estado.setSelected(true);
        selectEquipo_ActivoFijo_Código.setSelected(true);
        selectEquipo_ActivoFijo_Referecia.setSelected(true);
        selectEquipo_ActivoFijo_Descripción.setSelected(true);
        selectTipoEquipo_Código.setSelected(true);
        selectClasificador1_Código.setSelected(true);
        selectClasificador1_Descripción.setSelected(true);
        selectClasificador2_Código.setSelected(true);
        selectClasificador2_Descripción.setSelected(true);
        selectProveedorEquipo_Código.setSelected(true);
        selectProveedorEquipo_Nit.setSelected(true);
        selectProveedorEquipo_Nombre.setSelected(true);
        selectPertenencia_Código.setSelected(true);
        selectPertenencia_Nombre.setSelected(true);
        Tarifa_Año.setSelected(true); 
        Tarifa_Fecha_Inicio.setSelected(true); 
        Tarifa_Fecha_Final.setSelected(true); 
        Tarifa_ValorHora_Operación.setSelected(true); 
        Tarifa_ValorHora_Alquiler.setSelected(true); 
    }
    public void selectorCampoNinguno(){
        selectEquipo_Código.setSelected(false);
        selectEquipo_Producto.setSelected(false);
        selectEquipo_Capacidad.setSelected(false);
        selectEquipo_Marca.setSelected(false);
        selectEquipo_Modelo.setSelected(false);
        selectEquipo_Serial.setSelected(false);
        selectEquipo_Descripción.setSelected(false);
        selectTipoEquipo_Nombre.setSelected(false);
        selectPertenencia_Nombre.setSelected(false);
        selectEquipo_CódigoBarra.setSelected(false);
        selectEquipo_Referencia.setSelected(false);
        selectEquipo_Observación.setSelected(false);
        selectEquipo_Estado.setSelected(false);
        selectEquipo_ActivoFijo_Código.setSelected(false);
        selectEquipo_ActivoFijo_Referecia.setSelected(false);
        selectEquipo_ActivoFijo_Descripción.setSelected(false);
        selectTipoEquipo_Código.setSelected(false);
        selectClasificador1_Código.setSelected(false);
        selectClasificador1_Descripción.setSelected(false);
        selectClasificador2_Código.setSelected(false);
        selectClasificador2_Descripción.setSelected(false);
        selectProveedorEquipo_Código.setSelected(false);
        selectProveedorEquipo_Nit.setSelected(false);
        selectProveedorEquipo_Nombre.setSelected(false);
        selectPertenencia_Código.setSelected(false);
        selectPertenencia_Nombre.setSelected(false);    
        Tarifa_Año.setSelected(false); 
        Tarifa_Fecha_Inicio.setSelected(false); 
        Tarifa_Fecha_Final.setSelected(false); 
        Tarifa_ValorHora_Operación.setSelected(false); 
        Tarifa_ValorHora_Alquiler.setSelected(false); 
    }  
    public void tabla_Listar(String sql, String busquedaEspecifica) throws SQLException{
        tabla.setModel(crearModeloDeTabla());
        listado=new ControlDB_Equipo(tipoConexion).buscarEquiposEnAplicacionInterna(sql,busquedaEspecifica);
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
        return new ModeloDeTabla<Equipo>() {            
            @Override
            public Object getValueAt(Equipo listado1, int columnas) {
               switch(encabezadoTabla.get(columnas)){
                    case "Código":{
                       return listado1.getCodigo();
                    }
                    case "TipoEquipo_Código":{
                       return listado1.getTipoEquipo().getCodigo();
                    }
                    case "TipoEquipo_Nombre":{
                       return listado1.getTipoEquipo().getDescripcion();
                    }
                    case "CódigoBarra":{
                       return listado1.getCodigo_barra();
                    }
                    case "Referencia":{
                       return listado1.getReferencia();
                    }
                    case "Producto":{
                       return listado1.getProducto();
                    }
                    case "Capacidad":{
                       return listado1.getCapacidad();
                    }
                    case "Marca":{
                       return listado1.getMarca();
                    }
                    case "Modelo":{
                       return listado1.getModelo();
                    }
                    case "Serial":{
                       return listado1.getSerial();
                    }
                    case "Descripción":{
                       return listado1.getDescripcion();
                    }
                    case "Observación":{
                       return listado1.getObservacion();
                    }
                    case "Estado":{
                       return listado1.getEstado();
                    }
                    case "ActivoFijo_Código":{
                       return listado1.getActivoFijo_codigo();
                    }
                    case "ActivoFijo_Referecia":{
                       return listado1.getActivoFijo_referencia();
                    }
                    case "ActivoFijo_Descripción":{
                       return listado1.getActivoFijo_descripcion();
                    }
                    case "Clasificador1_Código":{
                       return listado1.getClasificador1().getCodigo();
                    }
                    case "Clasificador1_Descripción":{
                       return listado1.getClasificador1().getDescripcion();
                    }
                    case "Clasificador2_Código":{
                       return listado1.getClasificador2().getCodigo();
                    }
                    case "Clasificador2_Descripción":{
                       return listado1.getClasificador2().getDescripcion();
                    }
                    case "ProveedorEquipo_Código":{
                       return listado1.getProveedorEquipo().getCodigo();
                    }
                    case "ProveedorEquipo_Nit":{
                       return listado1.getProveedorEquipo().getNit();
                    }
                    case "ProveedorEquipo_Nombre":{
                       return listado1.getProveedorEquipo().getDescripcion();
                    }
                    case "Pertenencia_Código":{
                       return listado1.getPertenenciaEquipo().getCodigo();
                    }
                    case "Pertenencia_Nombre":{
                       return listado1.getPertenenciaEquipo().getDescripcion();
                    }
                    case "Tarifa_Año":{
                       return listado1.getTarifaEquipoAnterior().getAño();
                    }
                    case "Tarifa_Fecha_Inicio":{
                       return listado1.getTarifaEquipoAnterior().getFechaHoraInicio();
                    }
                    case "Tarifa_Fecha_Final":{
                       return listado1.getTarifaEquipoAnterior().getFechaHoraFin();
                    }
                    case "Tarifa_ValorHora_Operación":{
                       return listado1.getTarifaEquipoAnterior().getValorHoraOperacion();
                    }
                    case "Tarifa_ValorHora_Alquiler":{
                       return listado1.getTarifaEquipoAnterior().getValorHoraAlquiler();
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
    private ProveedorDeDatosDePaginacion<Equipo> crearProveedorDeDatos(final ArrayList<Equipo> model) {
        //Obtenemos el listado de registros existentes haciendo una consulta a la base de datos.
        
        //Retornamos un interfaz de tipo ProveedorDeDatosDePaginacion en la cual sobreescribimos sus metodos abtractos
        //1 metodo: obtenemos el numero total de registros agregados al JTable.
        //2 metodo: obtenemos una subLista la cual será mostrada en el JTable, seria nuestra pagina actual.
        return new ProveedorDeDatosDePaginacion<Equipo>() {
            @Override
            public int getTotalRowCount() {
                return model.size();
            }

            @Override
            public List<Equipo> getRows(int startIndex, int endIndex) {
                return model.subList(startIndex, endIndex);
            }
        };
    }
    public void validarSeleccionCampos(){
        encabezadoTabla= new ArrayList<>();
        
        if(selectEquipo_Código.isSelected()){
                encabezadoTabla.add("Código");
        }
        if(selectTipoEquipo_Código.isSelected()){
                encabezadoTabla.add("TipoEquipo_Código");
        }
        if(selectTipoEquipo_Nombre.isSelected()){
                encabezadoTabla.add("TipoEquipo_Nombre");
        }
        if(selectEquipo_CódigoBarra.isSelected()){
                encabezadoTabla.add("CódigoBarra");
        }
        if(selectEquipo_Referencia.isSelected()){
                encabezadoTabla.add("Referencia");
        }
        if(selectEquipo_Producto.isSelected()){
                encabezadoTabla.add("Producto");
        }
        if(selectEquipo_Capacidad.isSelected()){
                encabezadoTabla.add("Capacidad");
        }
        if(selectEquipo_Marca.isSelected()){
                encabezadoTabla.add("Marca");
        }
        if(selectEquipo_Modelo.isSelected()){
                encabezadoTabla.add("Modelo");
        }
        if(selectEquipo_Serial.isSelected()){
                encabezadoTabla.add("Serial");
        }
        if(selectEquipo_Descripción.isSelected()){
                encabezadoTabla.add("Descripción");
        }
        if(selectEquipo_Observación.isSelected()){
                encabezadoTabla.add("Observación");
        }
        if(selectEquipo_Estado.isSelected()){
                encabezadoTabla.add("Estado");
        }
        if(selectEquipo_ActivoFijo_Código.isSelected()){
                encabezadoTabla.add("ActivoFijo_Código");
        }
        if(selectEquipo_ActivoFijo_Referecia.isSelected()){
                encabezadoTabla.add("ActivoFijo_Referecia");
        }
        if(selectEquipo_ActivoFijo_Descripción.isSelected()){
                encabezadoTabla.add("ActivoFijo_Descripción");
        }
        if(selectClasificador1_Código.isSelected()){
                encabezadoTabla.add("Clasificador1_Código");
        }
        if(selectClasificador1_Descripción.isSelected()){
                encabezadoTabla.add("Clasificador1_Descripción");
        }
        if(selectClasificador2_Código.isSelected()){
                encabezadoTabla.add("Clasificador2_Código");
        }
        if(selectClasificador2_Descripción.isSelected()){
                encabezadoTabla.add("Clasificador2_Descripción");
        }
        if(selectProveedorEquipo_Código.isSelected()){
                encabezadoTabla.add("ProveedorEquipo_Código");
        }
        if(selectProveedorEquipo_Nit.isSelected()){
                encabezadoTabla.add("ProveedorEquipo_Nit");
        }
        if(selectProveedorEquipo_Nombre.isSelected()){
                encabezadoTabla.add("ProveedorEquipo_Nombre");
        }
        if(selectPertenencia_Código.isSelected()){
                encabezadoTabla.add("Pertenencia_Código");
        }
        if(selectPertenencia_Nombre.isSelected()){
                encabezadoTabla.add("Pertenencia_Nombre");
        }
        if(Tarifa_Año.isSelected()){
                encabezadoTabla.add("Tarifa_Año");
        }
        if(Tarifa_Fecha_Inicio.isSelected()){
                encabezadoTabla.add("Tarifa_Fecha_Inicio");
        }
        if(Tarifa_Fecha_Final.isSelected()){
                encabezadoTabla.add("Tarifa_Fecha_Final");
        }
        if(Tarifa_ValorHora_Operación.isSelected()){
                encabezadoTabla.add("Tarifa_ValorHora_Operación");
        }
        if(Tarifa_ValorHora_Alquiler.isSelected()){
                encabezadoTabla.add("Tarifa_ValorHora_Alquiler");
        }
    }
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
