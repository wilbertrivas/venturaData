package Catalogo.View;
import Catalogo.Controller.ControlDB_Equipo;
import Catalogo.Model.Equipo;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class Equipo_Consultar extends javax.swing.JPanel {
    ArrayList<Equipo> listado=null;
    DefaultTableModel modeloCliente;
    private String tipoConexion;
   // Usuario user;<
    public Equipo_Consultar(String tipoConexion) {
        initComponents();
        this.tipoConexion= tipoConexion;
        InternalFrameSelectorCampos.getContentPane().setBackground(Color.WHITE);
        InternalFrameSelectorCampos.show(false);
        selectorCampoPorDefecto();
        
        todos.setSelected(true);
        tipo.setSelected(false);
        marca.setSelected(false);
        especifico.setSelected(false);
        estado.setSelected(false);
        try {
           generarListadoEquipo();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los Equipos");
            Logger.getLogger(Equipo_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Cargamos en la interfaz los tipos de equipos
        try {
             ArrayList<String> listadoTiposEquipo = new ArrayList();
            listadoTiposEquipo=new ControlDB_Equipo(tipoConexion).buscarTipoEquiposEnAplicacionInterna();
            for(int i=0; i< listadoTiposEquipo.size(); i++){
                selectTipo.addItem(""+listadoTiposEquipo.get(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Equipo_Consultar.class.getName()).log(Level.SEVERE, null, ex);
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
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        InternalFrameSelectorCampos = new javax.swing.JInternalFrame();
        titulo1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        selectAll = new javax.swing.JRadioButton();
        selectPertenencia_Estado = new javax.swing.JRadioButton();
        jSeparator5 = new javax.swing.JSeparator();
        selectEquipo_C??digo = new javax.swing.JRadioButton();
        selectEquipo_C??digoBarra = new javax.swing.JRadioButton();
        selectEquipo_Capacidad = new javax.swing.JRadioButton();
        selectEquipo_Marca = new javax.swing.JRadioButton();
        selectEquipo_Modelo = new javax.swing.JRadioButton();
        selectEquipo_Serial = new javax.swing.JRadioButton();
        selectEquipo_Descripci??n = new javax.swing.JRadioButton();
        selectEquipo_Producto = new javax.swing.JRadioButton();
        selectEquipo_Observaci??n = new javax.swing.JRadioButton();
        selectProveedorEquipo_C??digo = new javax.swing.JRadioButton();
        selectProveedorEquipo_Estado = new javax.swing.JRadioButton();
        selectPertenencia_C??digo = new javax.swing.JRadioButton();
        selectClasificador2_C??digo = new javax.swing.JRadioButton();
        selectClasificador2_Descripci??n = new javax.swing.JRadioButton();
        selectClasificador1_C??digo = new javax.swing.JRadioButton();
        selectClasificador1_Descripci??n = new javax.swing.JRadioButton();
        selectTipoEquipo_Estado = new javax.swing.JRadioButton();
        selectTipoEquipo_Nombre = new javax.swing.JRadioButton();
        selectTipoEquipo_C??digo = new javax.swing.JRadioButton();
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
        selectEquipo_ActivoFijo_C??digo = new javax.swing.JRadioButton();
        selectEquipo_ActivoFijo_Referecia = new javax.swing.JRadioButton();
        selectEquipo_ActivoFijo_Descripci??n = new javax.swing.JRadioButton();
        selectCentroCosto_Estado = new javax.swing.JRadioButton();
        selectCentroCosto_Descripcion = new javax.swing.JRadioButton();
        selectCentroCosto_CodigoInterno = new javax.swing.JRadioButton();
        selectCentroCosto_Codigo = new javax.swing.JRadioButton();
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
        btn_consultar_cliente = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jButton2.setText("Aplicar Configuraci??n");
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

        selectPertenencia_Estado.setBackground(new java.awt.Color(255, 255, 255));
        selectPertenencia_Estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectPertenencia_Estado.setForeground(new java.awt.Color(51, 51, 51));
        selectPertenencia_Estado.setText("Pertenencia_Estado");
        selectPertenencia_Estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectPertenencia_Estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 190, -1));
        InternalFrameSelectorCampos.getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 940, 10));

        selectEquipo_C??digo.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_C??digo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_C??digo.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_C??digo.setText("Equipo_C??digo");
        selectEquipo_C??digo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_C??digo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 190, -1));

        selectEquipo_C??digoBarra.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_C??digoBarra.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_C??digoBarra.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_C??digoBarra.setText("Equipo_C??digoBarra");
        selectEquipo_C??digoBarra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_C??digoBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 190, -1));

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

        selectEquipo_Descripci??n.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_Descripci??n.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_Descripci??n.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_Descripci??n.setText("Equipo_Descripci??n");
        selectEquipo_Descripci??n.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_Descripci??n, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 190, -1));

        selectEquipo_Producto.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_Producto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_Producto.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_Producto.setText("Equipo_Producto");
        selectEquipo_Producto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_Producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 190, -1));

        selectEquipo_Observaci??n.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_Observaci??n.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_Observaci??n.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_Observaci??n.setText("Equipo_Observaci??n");
        selectEquipo_Observaci??n.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_Observaci??n, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 190, -1));

        selectProveedorEquipo_C??digo.setBackground(new java.awt.Color(255, 255, 255));
        selectProveedorEquipo_C??digo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectProveedorEquipo_C??digo.setForeground(new java.awt.Color(51, 51, 51));
        selectProveedorEquipo_C??digo.setText("ProveedorEquipo_C??digo");
        selectProveedorEquipo_C??digo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectProveedorEquipo_C??digo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 190, -1));

        selectProveedorEquipo_Estado.setBackground(new java.awt.Color(255, 255, 255));
        selectProveedorEquipo_Estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectProveedorEquipo_Estado.setForeground(new java.awt.Color(51, 51, 51));
        selectProveedorEquipo_Estado.setText("ProveedorEquipo_Estado");
        selectProveedorEquipo_Estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectProveedorEquipo_Estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 350, 190, -1));

        selectPertenencia_C??digo.setBackground(new java.awt.Color(255, 255, 255));
        selectPertenencia_C??digo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectPertenencia_C??digo.setForeground(new java.awt.Color(51, 51, 51));
        selectPertenencia_C??digo.setText("Pertenencia_C??digo");
        selectPertenencia_C??digo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectPertenencia_C??digo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 190, -1));

        selectClasificador2_C??digo.setBackground(new java.awt.Color(255, 255, 255));
        selectClasificador2_C??digo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectClasificador2_C??digo.setForeground(new java.awt.Color(51, 51, 51));
        selectClasificador2_C??digo.setText("Clasificador2_C??digo");
        selectClasificador2_C??digo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectClasificador2_C??digo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, 190, -1));

        selectClasificador2_Descripci??n.setBackground(new java.awt.Color(255, 255, 255));
        selectClasificador2_Descripci??n.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectClasificador2_Descripci??n.setForeground(new java.awt.Color(51, 51, 51));
        selectClasificador2_Descripci??n.setText("Clasificador2_Descripci??n");
        selectClasificador2_Descripci??n.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectClasificador2_Descripci??n, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, 190, -1));

        selectClasificador1_C??digo.setBackground(new java.awt.Color(255, 255, 255));
        selectClasificador1_C??digo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectClasificador1_C??digo.setForeground(new java.awt.Color(51, 51, 51));
        selectClasificador1_C??digo.setText("Clasificador1_C??digo");
        selectClasificador1_C??digo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectClasificador1_C??digo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 190, -1));

        selectClasificador1_Descripci??n.setBackground(new java.awt.Color(255, 255, 255));
        selectClasificador1_Descripci??n.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectClasificador1_Descripci??n.setForeground(new java.awt.Color(51, 51, 51));
        selectClasificador1_Descripci??n.setText("Clasificador1_Descripci??n");
        selectClasificador1_Descripci??n.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectClasificador1_Descripci??n, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, 190, -1));

        selectTipoEquipo_Estado.setBackground(new java.awt.Color(255, 255, 255));
        selectTipoEquipo_Estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectTipoEquipo_Estado.setForeground(new java.awt.Color(51, 51, 51));
        selectTipoEquipo_Estado.setText("TipoEquipo_Estado");
        selectTipoEquipo_Estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectTipoEquipo_Estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 190, -1));

        selectTipoEquipo_Nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectTipoEquipo_Nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectTipoEquipo_Nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectTipoEquipo_Nombre.setText("TipoEquipo_Nombre");
        selectTipoEquipo_Nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectTipoEquipo_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 190, -1));

        selectTipoEquipo_C??digo.setBackground(new java.awt.Color(255, 255, 255));
        selectTipoEquipo_C??digo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectTipoEquipo_C??digo.setForeground(new java.awt.Color(51, 51, 51));
        selectTipoEquipo_C??digo.setText("TipoEquipo_C??digo");
        selectTipoEquipo_C??digo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectTipoEquipo_C??digo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 190, -1));

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
        titulo6.setText("PERTENENCIA");
        InternalFrameSelectorCampos.getContentPane().add(titulo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 190, 20));
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

        selectEquipo_ActivoFijo_C??digo.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_ActivoFijo_C??digo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_ActivoFijo_C??digo.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_ActivoFijo_C??digo.setText("Equipo_ActivoFijo_C??digo");
        selectEquipo_ActivoFijo_C??digo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_ActivoFijo_C??digo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 190, -1));

        selectEquipo_ActivoFijo_Referecia.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_ActivoFijo_Referecia.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_ActivoFijo_Referecia.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_ActivoFijo_Referecia.setText("Equipo_ActivoFijo_Referecia");
        selectEquipo_ActivoFijo_Referecia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_ActivoFijo_Referecia, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 190, -1));

        selectEquipo_ActivoFijo_Descripci??n.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_ActivoFijo_Descripci??n.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_ActivoFijo_Descripci??n.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_ActivoFijo_Descripci??n.setText("Equipo_ActivoFijo_Descripci??n");
        selectEquipo_ActivoFijo_Descripci??n.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_ActivoFijo_Descripci??n, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 190, -1));

        selectCentroCosto_Estado.setBackground(new java.awt.Color(255, 255, 255));
        selectCentroCosto_Estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectCentroCosto_Estado.setForeground(new java.awt.Color(51, 51, 51));
        selectCentroCosto_Estado.setText("CentroCosto_Estado");
        selectCentroCosto_Estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectCentroCosto_Estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 230, 190, -1));

        selectCentroCosto_Descripcion.setBackground(new java.awt.Color(255, 255, 255));
        selectCentroCosto_Descripcion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectCentroCosto_Descripcion.setForeground(new java.awt.Color(51, 51, 51));
        selectCentroCosto_Descripcion.setText("CentroCosto_Descripci??n");
        selectCentroCosto_Descripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectCentroCosto_Descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, 190, -1));

        selectCentroCosto_CodigoInterno.setBackground(new java.awt.Color(255, 255, 255));
        selectCentroCosto_CodigoInterno.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectCentroCosto_CodigoInterno.setForeground(new java.awt.Color(51, 51, 51));
        selectCentroCosto_CodigoInterno.setText("CentroCosto_C??digoInterno");
        selectCentroCosto_CodigoInterno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectCentroCosto_CodigoInterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, 190, -1));

        selectCentroCosto_Codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectCentroCosto_Codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectCentroCosto_Codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectCentroCosto_Codigo.setText("CentroCosto_C??digo");
        selectCentroCosto_Codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectCentroCosto_Codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 190, -1));

        titulo7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo7.setText("CENTRO COSTO EQUIPO");
        InternalFrameSelectorCampos.getContentPane().add(titulo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, 190, 20));

        add(InternalFrameSelectorCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1130, 570));

        titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo.setText("CONSULTA DE EQUIPOS");
        add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 590, 30));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 1280, 450));

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

        btn_consultar_cliente.setBackground(new java.awt.Color(255, 255, 255));
        btn_consultar_cliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_consultar_cliente.setText("CONSULTAR");
        btn_consultar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultar_clienteActionPerformed(evt);
            }
        });
        add(btn_consultar_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 140, 30));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/ExportarExcel.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 50, 40));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Exportar");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 50, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Campos");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 40, 30));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCampo.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 50, 40));
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

    private void btn_consultar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultar_clienteActionPerformed
        try {
            generarListadoEquipo();
        } catch (SQLException ex) {
            Logger.getLogger(Equipo_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_consultar_clienteActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
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
                String respuesta="No se realiz?? con exito la exportacion";
                int numFila=tabla.getRowCount(), numColumna=tabla.getColumnCount();
                System.out.println("Fila: "+numFila+" columna:"+numColumna);
                if(archivo.getName().endsWith("xls")){
                    wb = new HSSFWorkbook();
                }else{
                    wb= new XSSFWorkbook();
                }
                Sheet hoja= wb.createSheet("Listado_Equipos");
                try{
                    for(int i= -1; i < numFila; i++ ){
                        Row fila= hoja.createRow(i+1);
                        
                        for(int j=0; j< numColumna; j++){
                            System.out.println("i="+i+" j="+j);
                            Cell celda= fila.createCell(j);
                            if(i==-1){
                                celda.setCellValue(String.valueOf(tabla.getColumnName(j)));
                            }else{
                                //inicio
                                try{
                                    System.out.print(String.valueOf(tabla.getValueAt(i, j))+"\n");
                                    celda.setCellValue(String.valueOf(tabla.getValueAt(i, j)));   
                                }catch(Exception e){
                                    System.out.print("Error->"+String.valueOf(tabla.getValueAt(i, j))+"\n");
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
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        InternalFrameSelectorCampos.show(true);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        InternalFrameSelectorCampos.show(false);
        try {
            generarListadoEquipo();
        } catch (SQLException ex) {
            Logger.getLogger(Equipo_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame InternalFrameSelectorCampos;
    private javax.swing.JButton btn_consultar_cliente;
    private javax.swing.JRadioButton especifico;
    private javax.swing.JRadioButton estado;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JRadioButton selectAll;
    private javax.swing.JRadioButton selectCentroCosto_Codigo;
    private javax.swing.JRadioButton selectCentroCosto_CodigoInterno;
    private javax.swing.JRadioButton selectCentroCosto_Descripcion;
    private javax.swing.JRadioButton selectCentroCosto_Estado;
    private javax.swing.JRadioButton selectClasificador1_C??digo;
    private javax.swing.JRadioButton selectClasificador1_Descripci??n;
    private javax.swing.JRadioButton selectClasificador2_C??digo;
    private javax.swing.JRadioButton selectClasificador2_Descripci??n;
    private javax.swing.JRadioButton selectEquipo_ActivoFijo_C??digo;
    private javax.swing.JRadioButton selectEquipo_ActivoFijo_Descripci??n;
    private javax.swing.JRadioButton selectEquipo_ActivoFijo_Referecia;
    private javax.swing.JRadioButton selectEquipo_Capacidad;
    private javax.swing.JRadioButton selectEquipo_C??digo;
    private javax.swing.JRadioButton selectEquipo_C??digoBarra;
    private javax.swing.JRadioButton selectEquipo_Descripci??n;
    private javax.swing.JRadioButton selectEquipo_Estado;
    private javax.swing.JRadioButton selectEquipo_Marca;
    private javax.swing.JRadioButton selectEquipo_Modelo;
    private javax.swing.JRadioButton selectEquipo_Observaci??n;
    private javax.swing.JRadioButton selectEquipo_Producto;
    private javax.swing.JRadioButton selectEquipo_Referencia;
    private javax.swing.JRadioButton selectEquipo_Serial;
    private javax.swing.JComboBox<String> selectEstado;
    private javax.swing.JComboBox<String> selectMarca;
    private javax.swing.JRadioButton selectPertenencia_C??digo;
    private javax.swing.JRadioButton selectPertenencia_Estado;
    private javax.swing.JRadioButton selectPertenencia_Nombre;
    private javax.swing.JRadioButton selectProveedorEquipo_C??digo;
    private javax.swing.JRadioButton selectProveedorEquipo_Estado;
    private javax.swing.JRadioButton selectProveedorEquipo_Nit;
    private javax.swing.JRadioButton selectProveedorEquipo_Nombre;
    private javax.swing.JComboBox<String> selectTipo;
    private javax.swing.JRadioButton selectTipoEquipo_C??digo;
    private javax.swing.JRadioButton selectTipoEquipo_Estado;
    private javax.swing.JRadioButton selectTipoEquipo_Nombre;
    private javax.swing.JTable tabla;
    private javax.swing.JRadioButton tipo;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel titulo1;
    private javax.swing.JLabel titulo13;
    private javax.swing.JLabel titulo2;
    private javax.swing.JLabel titulo3;
    private javax.swing.JLabel titulo4;
    private javax.swing.JLabel titulo5;
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
    public void tabla_Listar(String sql, String busquedaEspecifica) throws SQLException{
        String Stitulo="";
        int contador=0;
        if(selectEquipo_C??digo.isSelected()){
            Stitulo +="Equipo_C??digo"+":";
            contador++;
        }
        if(selectEquipo_C??digoBarra.isSelected()){
            Stitulo +="Equipo_C??digoBarra"+":";
            contador++;
        }
        if(selectEquipo_Referencia.isSelected()){
            Stitulo +="Equipo_Referencia"+":";
            contador++;
        }
        if(selectEquipo_Producto.isSelected()){
            Stitulo +="Equipo_Producto"+":";
            contador++;
        }
        if(selectEquipo_Capacidad.isSelected()){
            Stitulo +="Equipo_Capacidad"+":";
            contador++;
        }
        if(selectEquipo_Marca.isSelected()){
            Stitulo +="Equipo_Marca"+":";
            contador++;
        }
        if(selectEquipo_Modelo.isSelected()){
            Stitulo +="Equipo_Modelo"+":";
            contador++;
        }
        if(selectEquipo_Serial.isSelected()){
            Stitulo +="Equipo_Serial"+":";
            contador++;
        }
        if(selectEquipo_Descripci??n.isSelected()){
            Stitulo +="Equipo_Descripci??n"+":";
            contador++;
        }
        if(selectEquipo_Observaci??n.isSelected()){
            Stitulo +="Equipo_Observaci??n"+":";
            contador++;
        }
        if(selectEquipo_Estado.isSelected()){
            Stitulo +="Equipo_Estado"+":";
            contador++;
        }
        if(selectEquipo_ActivoFijo_C??digo.isSelected()){
            Stitulo +="Equipo_ActivoFijo_C??digo"+":";
            contador++;
        }
        if(selectEquipo_ActivoFijo_Referecia.isSelected()){
            Stitulo +="Equipo_ActivoFijo_Referecia"+":";
            contador++;
        }
        if(selectEquipo_ActivoFijo_Descripci??n.isSelected()){
            Stitulo +="Equipo_ActivoFijo_Descripci??n"+":";
            contador++;
        }
        if(selectTipoEquipo_C??digo.isSelected()){
            Stitulo +="TipoEquipo_C??digo"+":";
            contador++;
        }
        if(selectTipoEquipo_Nombre.isSelected()){
            Stitulo +="TipoEquipo_Nombre"+":";
            contador++;
        }
        if(selectTipoEquipo_Estado.isSelected()){
            Stitulo +="TipoEquipo_Estado"+":";
            contador++;
        }
        
        //TRANSPORTADORA
        if(selectClasificador1_C??digo.isSelected()){
            Stitulo +="Clasificador1_C??digo"+":";
            contador++;
        }
        if(selectClasificador1_Descripci??n.isSelected()){
            Stitulo +="Clasificador1_Descripci??n"+":";
            contador++;
        }
        if(selectClasificador2_C??digo.isSelected()){
            Stitulo +="Clasificador2_C??digo"+":";
            contador++;
        }
        
        //CLIENTE
        if(selectClasificador2_Descripci??n.isSelected()){
            Stitulo +="Clasificador2_Descripci??n"+":";
            contador++;
        }
        if(selectProveedorEquipo_C??digo.isSelected()){
            Stitulo +="ProveedorEquipo_C??digo"+":";
            contador++;
        }
        
        //ARTICULO
        if(selectProveedorEquipo_Nit.isSelected()){
            Stitulo +="ProveedorEquipo_Nit"+":";
            contador++;
        }
        if(selectProveedorEquipo_Nombre.isSelected()){
            Stitulo +="ProveedorEquipo_Nombre"+":";
            contador++;
        }
        
        //CENTRO DE OPERACION
        if(selectProveedorEquipo_Estado.isSelected()){
            Stitulo +="ProveedorEquipo_Estado"+":";
            contador++;
        }
        if(selectPertenencia_C??digo.isSelected()){
            Stitulo +="Pertenencia_C??digo"+":";
            contador++;
        }
        
        //CENTRO COSTO AUXILIAR
        if(selectPertenencia_Nombre.isSelected()){
            Stitulo +="Pertenencia_Nombre"+":";
            contador++;
        }
        if(selectPertenencia_Estado.isSelected()){
            Stitulo +="Pertenencia_Estado"+":";
            contador++;
        }
        
        //CENTRO COSTO EQUIPO
        if(selectCentroCosto_Codigo.isSelected()){
            Stitulo +="CentroCosto_c??digo"+":";
            contador++;
        }
        if(selectCentroCosto_CodigoInterno.isSelected()){
            Stitulo +="CentroCosto_C??digoInterno"+":";
            contador++;
        }
        if(selectCentroCosto_Descripcion.isSelected()){
            Stitulo +="CentroCosto_Descripci??n"+":";
            contador++;
        }
        if(selectCentroCosto_Estado.isSelected()){
            Stitulo +="CentroCosto_Estado"+":";
            contador++;
        }   
        DefaultTableModel modelo = new DefaultTableModel(null, Stitulo.split(":"));  
        
        listado=new ControlDB_Equipo(tipoConexion).buscarEquiposEnAplicacionInterna(sql,busquedaEspecifica);
        for (Equipo listado1 : listado) {
            Object[] registro = new String[contador];
            int contadorRegistro=0;
            
            if(selectEquipo_C??digo.isSelected()){
                registro[contadorRegistro] = "" + listado1.getCodigo();
                contadorRegistro++;
            }
            
            if(selectEquipo_C??digoBarra.isSelected()){
                registro[contadorRegistro] = "" + listado1.getCodigo_barra();
                contadorRegistro++;
            }
            if(selectEquipo_Referencia.isSelected()){
                registro[contadorRegistro] = "" + listado1.getReferencia();
                contadorRegistro++;
            }
            if(selectEquipo_Producto.isSelected()){
                registro[contadorRegistro] = "" + listado1.getProducto();
                contadorRegistro++;
            }
            if(selectEquipo_Capacidad.isSelected()){
                registro[contadorRegistro] = "" + listado1.getCapacidad();
                contadorRegistro++;
            }
            if(selectEquipo_Marca.isSelected()){
                registro[contadorRegistro] = "" + listado1.getMarca();
                contadorRegistro++;
            }
            if(selectEquipo_Modelo.isSelected()){
                registro[contadorRegistro] = "" + listado1.getModelo();
                contadorRegistro++;
            }
            if(selectEquipo_Serial.isSelected()){
                registro[contadorRegistro] = "" + listado1.getSerial();
                contadorRegistro++;
            }
            if(selectEquipo_Descripci??n.isSelected()){
                registro[contadorRegistro] = "" + listado1.getDescripcion();
                contadorRegistro++;
            }
            if(selectEquipo_Observaci??n.isSelected()){
                registro[contadorRegistro] = "" + listado1.getObservacion();
                contadorRegistro++;
            }
            if(selectEquipo_Estado.isSelected()){
                if(listado1.getEstado().equals("1")){
                    registro[contadorRegistro] = "ACTIVO";
                }else{
                    if(listado1.getEstado().equals("0")){
                        registro[contadorRegistro] = "INACTIVO";
                    }else{
                        registro[contadorRegistro] = "" + listado1.getEstado();
                    }
                }
                contadorRegistro++;
            }
            if(selectEquipo_ActivoFijo_C??digo.isSelected()){
                registro[contadorRegistro] = "" + listado1.getActivoFijo_codigo();
                contadorRegistro++;
            }
            if(selectEquipo_ActivoFijo_Referecia.isSelected()){
                registro[contadorRegistro] = "" + listado1.getActivoFijo_referencia();
                contadorRegistro++;
            }
            if(selectEquipo_ActivoFijo_Descripci??n.isSelected()){
                registro[contadorRegistro] = "" + listado1.getActivoFijo_descripcion();
                contadorRegistro++;
            }
            // Tipo Equipo
            if(selectTipoEquipo_C??digo.isSelected()){
                registro[contadorRegistro] = "" + listado1.getTipoEquipo().getCodigo();
                contadorRegistro++;
            }
            if(selectTipoEquipo_Nombre.isSelected()){
                registro[contadorRegistro] = "" + listado1.getTipoEquipo().getDescripcion();
                contadorRegistro++;
            }
            if(selectTipoEquipo_Estado.isSelected()){
                if(listado1.getTipoEquipo().getEstado().equals("1")){
                    registro[contadorRegistro] = "ACTIVO";
                }else{
                    if(listado1.getTipoEquipo().getEstado().equals("0")){
                        registro[contadorRegistro] = "INACTIVO";
                    }else{
                        registro[contadorRegistro] = "" + listado1.getTipoEquipo().getEstado();
                    }
                }
                contadorRegistro++;
            }

            
            //Clasificador 1
            if(selectClasificador1_C??digo.isSelected()){
                registro[contadorRegistro] = "" + listado1.getClasificador1().getCodigo();
                contadorRegistro++;
            }
            if(selectClasificador1_Descripci??n.isSelected()){
                registro[contadorRegistro] = "" + listado1.getClasificador1().getDescripcion();
                contadorRegistro++;
            }
            
            //Clasificador 2
            if(selectClasificador2_C??digo.isSelected()){
                registro[contadorRegistro] = "" + listado1.getClasificador2().getCodigo();
                contadorRegistro++;
            }
            if(selectClasificador2_Descripci??n.isSelected()){
                registro[contadorRegistro] = "" + listado1.getClasificador2().getDescripcion();
                contadorRegistro++;
            }

            //CLIENTE
            if(selectProveedorEquipo_C??digo.isSelected()){
                registro[contadorRegistro] = "" + listado1.getProveedorEquipo().getCodigo();
                contadorRegistro++;
            }
            if(selectProveedorEquipo_Nit.isSelected()){
                registro[contadorRegistro] = "" + listado1.getProveedorEquipo().getNit();
                contadorRegistro++;
            }
            if(selectProveedorEquipo_Nombre.isSelected()){
                registro[contadorRegistro] = "" + listado1.getProveedorEquipo().getDescripcion();
                contadorRegistro++;
            }
            if(selectProveedorEquipo_Estado.isSelected()){
                registro[contadorRegistro] = "" + listado1.getProveedorEquipo().getEstado();
                contadorRegistro++;
            }

            //Pertenencia
            if(selectPertenencia_C??digo.isSelected()){
                registro[contadorRegistro] = "" + listado1.getPertenenciaEquipo().getCodigo();
                contadorRegistro++;
            }
            if(selectPertenencia_Nombre.isSelected()){
                if(listado1.getPertenenciaEquipo().getDescripcion().equals("1")){
                    registro[contadorRegistro] = "Equipo Propio";
                }else{
                    if(listado1.getPertenenciaEquipo().getDescripcion().equals("2")){
                        registro[contadorRegistro] = "Equipo Alquilado";
                    }else{
                        registro[contadorRegistro] = "" + listado1.getPertenenciaEquipo().getDescripcion();
                    }
                }
                contadorRegistro++;
            }
            if(selectPertenencia_Estado.isSelected()){
                if(listado1.getPertenenciaEquipo().getEstado().equals("1")){
                    registro[contadorRegistro] = "ACTIVO";
                }else{
                    if(listado1.getPertenenciaEquipo().getEstado().equals("0")){
                        registro[contadorRegistro] = "INACTIVO";
                    }else{
                        registro[contadorRegistro] = "" + listado1.getPertenenciaEquipo().getEstado();
                    }
                }
                contadorRegistro++;
            }
            //CentroCosto Equipo
            if(selectCentroCosto_Codigo.isSelected()){
                registro[contadorRegistro] = "" + listado1.getCentroCostoEquipo().getCodigo();
                contadorRegistro++;
            }
            if(selectCentroCosto_CodigoInterno.isSelected()){
                registro[contadorRegistro] = "" + listado1.getCentroCostoEquipo().getCodigoInterno();
                contadorRegistro++;
            }
            if(selectCentroCosto_Descripcion.isSelected()){
                registro[contadorRegistro] = "" + listado1.getCentroCostoEquipo().getDescripcion();
                contadorRegistro++;
            }
            if(selectCentroCosto_Estado.isSelected()){
                registro[contadorRegistro] = "" + listado1.getCentroCostoEquipo().getEstado();
                contadorRegistro++;
            }
            modelo.addRow(registro);      
            }
        tabla.setModel(modelo);
        //tabla.setFillsViewportHeight(true);
    }
    public void selectorCampoPorDefecto(){
        //Campos Activos
        selectEquipo_C??digo.setSelected(true);
        selectEquipo_Producto.setSelected(true);
        selectEquipo_Capacidad.setSelected(true);
        selectEquipo_Marca.setSelected(true);
        selectEquipo_Modelo.setSelected(true);
        selectEquipo_Serial.setSelected(true);
        selectEquipo_Descripci??n.setSelected(true);
        selectTipoEquipo_Nombre.setSelected(true);
        selectPertenencia_Nombre.setSelected(true);
        //Campos Inactivos
        selectEquipo_C??digoBarra.setSelected(false);
        selectEquipo_Referencia.setSelected(false);
        selectEquipo_Observaci??n.setSelected(false);
        selectEquipo_Estado.setSelected(false);
        selectEquipo_ActivoFijo_C??digo.setSelected(false);
        selectEquipo_ActivoFijo_Referecia.setSelected(false);
        selectEquipo_ActivoFijo_Descripci??n.setSelected(false);
        selectTipoEquipo_C??digo.setSelected(false);
        selectTipoEquipo_Estado.setSelected(false);
        selectClasificador1_C??digo.setSelected(false);
        selectClasificador1_Descripci??n.setSelected(false);
        selectClasificador2_C??digo.setSelected(false);
        selectClasificador2_Descripci??n.setSelected(false);
        selectProveedorEquipo_C??digo.setSelected(false);
        selectProveedorEquipo_Nit.setSelected(false);
        selectProveedorEquipo_Nombre.setSelected(false);
        selectProveedorEquipo_Estado.setSelected(false);
        selectPertenencia_C??digo.setSelected(false);
        selectPertenencia_Nombre.setSelected(false);
        selectPertenencia_Estado.setSelected(false); 
        selectCentroCosto_Codigo.setSelected(false);
        selectCentroCosto_CodigoInterno.setSelected(true);
        selectCentroCosto_Descripcion.setSelected(true);
        selectCentroCosto_Estado.setSelected(false);
    }
    public void selectorCampoTodos(){
        //Campos Activos
        selectEquipo_C??digo.setSelected(true);
        selectEquipo_Producto.setSelected(true);
        selectEquipo_Capacidad.setSelected(true);
        selectEquipo_Marca.setSelected(true);
        selectEquipo_Modelo.setSelected(true);
        selectEquipo_Serial.setSelected(true);
        selectEquipo_Descripci??n.setSelected(true);
        selectTipoEquipo_Nombre.setSelected(true);
        selectPertenencia_Nombre.setSelected(true);
        selectEquipo_C??digoBarra.setSelected(true);
        selectEquipo_Referencia.setSelected(true);
        selectEquipo_Observaci??n.setSelected(true);
        selectEquipo_Estado.setSelected(true);
        selectEquipo_ActivoFijo_C??digo.setSelected(true);
        selectEquipo_ActivoFijo_Referecia.setSelected(true);
        selectEquipo_ActivoFijo_Descripci??n.setSelected(true);
        selectTipoEquipo_C??digo.setSelected(true);
        selectTipoEquipo_Estado.setSelected(true);
        selectClasificador1_C??digo.setSelected(true);
        selectClasificador1_Descripci??n.setSelected(true);
        selectClasificador2_C??digo.setSelected(true);
        selectClasificador2_Descripci??n.setSelected(true);
        selectProveedorEquipo_C??digo.setSelected(true);
        selectProveedorEquipo_Nit.setSelected(true);
        selectProveedorEquipo_Nombre.setSelected(true);
        selectProveedorEquipo_Estado.setSelected(true);
        selectPertenencia_C??digo.setSelected(true);
        selectPertenencia_Nombre.setSelected(true);
        selectPertenencia_Estado.setSelected(true);   
         selectCentroCosto_Codigo.setSelected(true);
        selectCentroCosto_CodigoInterno.setSelected(true);
        selectCentroCosto_Descripcion.setSelected(true);
        selectCentroCosto_Estado.setSelected(true);  
    }
    public void selectorCampoNinguno(){
        selectEquipo_C??digo.setSelected(false);
        selectEquipo_Producto.setSelected(false);
        selectEquipo_Capacidad.setSelected(false);
        selectEquipo_Marca.setSelected(false);
        selectEquipo_Modelo.setSelected(false);
        selectEquipo_Serial.setSelected(false);
        selectEquipo_Descripci??n.setSelected(false);
        selectTipoEquipo_Nombre.setSelected(false);
        selectPertenencia_Nombre.setSelected(false);
        selectEquipo_C??digoBarra.setSelected(false);
        selectEquipo_Referencia.setSelected(false);
        selectEquipo_Observaci??n.setSelected(false);
        selectEquipo_Estado.setSelected(false);
        selectEquipo_ActivoFijo_C??digo.setSelected(false);
        selectEquipo_ActivoFijo_Referecia.setSelected(false);
        selectEquipo_ActivoFijo_Descripci??n.setSelected(false);
        selectTipoEquipo_C??digo.setSelected(false);
        selectTipoEquipo_Estado.setSelected(false);
        selectClasificador1_C??digo.setSelected(false);
        selectClasificador1_Descripci??n.setSelected(false);
        selectClasificador2_C??digo.setSelected(false);
        selectClasificador2_Descripci??n.setSelected(false);
        selectProveedorEquipo_C??digo.setSelected(false);
        selectProveedorEquipo_Nit.setSelected(false);
        selectProveedorEquipo_Nombre.setSelected(false);
        selectProveedorEquipo_Estado.setSelected(false);
        selectPertenencia_C??digo.setSelected(false);
        selectPertenencia_Nombre.setSelected(false);
        selectPertenencia_Estado.setSelected(false);    
        selectCentroCosto_Codigo.setSelected(false);
        selectCentroCosto_CodigoInterno.setSelected(false);
        selectCentroCosto_Descripcion.setSelected(false);
        selectCentroCosto_Estado.setSelected(false); 
    }  
}
