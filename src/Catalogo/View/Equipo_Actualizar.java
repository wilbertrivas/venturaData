package Catalogo.View;

import Catalogo.Controller.ControlDB_ClasificadorEquipo;
import Catalogo.Controller.ControlDB_Equipo;
import Catalogo.Controller.ControlDB_PertenenciaEquipo;
import Catalogo.Controller.ControlDB_ProveedorEquipo;
import Catalogo.Controller.ControlDB_TipoEquipo;
import Catalogo.Model.ClasificadorEquipo;
import Catalogo.Model.Equipo;
import Catalogo.Model.Pertenencia;
import Catalogo.Model.ProveedorEquipo;
import Catalogo.Model.TipoEquipo;
import Sistema.Model.Usuario;
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

public final class Equipo_Actualizar extends javax.swing.JPanel {
    Usuario user;
    ArrayList<Equipo> listado=null;
    Equipo equipo=null;
    ArrayList<TipoEquipo> listadoTipoEquipo = new ArrayList();
    ArrayList<ClasificadorEquipo> listadoClasificadorEquipo = new ArrayList();
    ArrayList<ProveedorEquipo> listadoProveedorEquipo = new ArrayList();
    ArrayList<Pertenencia> listadoPertenencia = new ArrayList();
    ArrayList<String> listadoMarcaEquipo = new ArrayList();
    private String tipoConexion;
    public Equipo_Actualizar(Usuario us,String tipoConexion) throws SQLException {
        user=us;
        initComponents();
        this.tipoConexion= tipoConexion;
        listadoTipoEquipo=new ControlDB_TipoEquipo(tipoConexion).buscarActivos();
        listadoClasificadorEquipo=new ControlDB_ClasificadorEquipo(tipoConexion).buscarActivos();
        listadoProveedorEquipo=new ControlDB_ProveedorEquipo(tipoConexion).buscarActivos();
        listadoPertenencia=new ControlDB_PertenenciaEquipo(tipoConexion).buscarActivos();
        listadoMarcaEquipo=new ControlDB_Equipo(tipoConexion).buscarMarcasEquiposEnAplicacionInterna();
        
        InternalFrameSelectorCampos.getContentPane().setBackground(Color.WHITE);
        InternalFrameConsultarEquipos.getContentPane().setBackground(Color.WHITE);
        InternalFrameSelectorCampos.show(false);
        InternalFrameConsultarEquipos.show(false);
        selectorCampoPorDefecto();
        
        todos.setSelected(true);
        tipo.setSelected(false);
        marca.setSelected(false);
        especifico.setSelected(false);
        estado1.setSelected(false);
        equipoCodigo.setEnabled(false);
        try {
           generarListadoEquipo();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los Equipos");
            Logger.getLogger(Equipo_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Cargamos en la interfaz los TiposEquipos activos
        for(TipoEquipo TipoEquipo1: listadoTipoEquipo){
            equipoTipoEquipo.addItem(TipoEquipo1.getDescripcion());
            selectTipo.addItem(TipoEquipo1.getDescripcion());
        }
        //Cargamos en la interfaz los clasificadores 1 de equipos
        for (ClasificadorEquipo ClasificadorEquipo1: listadoClasificadorEquipo) {
            equipoClasificador1.addItem(ClasificadorEquipo1.getDescripcion());
        }
        
        //Cargamos en la interfaz los clasificadores 2 de equipos 
        for (ClasificadorEquipo ClasificadorEquipo1: listadoClasificadorEquipo) {
             equipoClasificador2.addItem(ClasificadorEquipo1.getDescripcion());
         }
        
        //Cargamos en la interfaz los proveedores de equipos
        for(ProveedorEquipo ProveedorEquipo1: listadoProveedorEquipo){
            equipoProveedorEquipo.addItem(ProveedorEquipo1.getDescripcion());
        }

        //Cargamos en la interfaz las pertenencias de Equipos
        for(Pertenencia Pertenencia1: listadoPertenencia){
            equipoPertenencia.addItem(Pertenencia1.getDescripcion());
        }
        
        //Cargamos en la interfaz las Marcas de equipos
        for(int i=0; i< listadoMarcaEquipo.size(); i++){
            selectMarca.addItem(""+listadoMarcaEquipo.get(i));
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        InternalFrameSelectorCampos = new javax.swing.JInternalFrame();
        titulo1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        selectAll = new javax.swing.JRadioButton();
        selectPertenencia_Estado = new javax.swing.JRadioButton();
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
        selectProveedorEquipo_Estado = new javax.swing.JRadioButton();
        selectPertenencia_Código = new javax.swing.JRadioButton();
        selectClasificador2_Código = new javax.swing.JRadioButton();
        selectClasificador2_Descripción = new javax.swing.JRadioButton();
        selectClasificador1_Código = new javax.swing.JRadioButton();
        selectClasificador1_Descripción = new javax.swing.JRadioButton();
        selectTipoEquipo_Estado = new javax.swing.JRadioButton();
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
        selectEquipo_ActivoFijo_Descripción = new javax.swing.JRadioButton();
        selectEquipo_ActivoFijo_Referecia = new javax.swing.JRadioButton();
        selectEquipo_ActivoFijo_Código = new javax.swing.JRadioButton();
        InternalFrameConsultarEquipos = new javax.swing.JInternalFrame();
        titulo = new javax.swing.JLabel();
        todos = new javax.swing.JRadioButton();
        tipo = new javax.swing.JRadioButton();
        marca = new javax.swing.JRadioButton();
        selectTipo = new javax.swing.JComboBox<>();
        selectMarca = new javax.swing.JComboBox<>();
        especifico = new javax.swing.JRadioButton();
        valorBusquedaEspecifica = new javax.swing.JTextField();
        selectEstado = new javax.swing.JComboBox<>();
        estado1 = new javax.swing.JRadioButton();
        btn_consultar_cliente = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btn_Actualizar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        equipoMarca = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        equipoTipoEquipo = new javax.swing.JComboBox<>();
        equipoEstado = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        equipoProducto = new javax.swing.JTextField();
        equipoClasificador2 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        equipoObservacion = new javax.swing.JTextPane();
        jLabel12 = new javax.swing.JLabel();
        equipoCodigo = new javax.swing.JTextField();
        equipoCodigoBarra = new javax.swing.JTextField();
        equipoReferencia = new javax.swing.JTextField();
        equipoCapacidad = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        equipoModelo = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        equipoSerial = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        equipoDescripcion = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        equipoClasificador1 = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        equipoProveedorEquipo = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        equipoPertenencia = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        selectActivoFijo = new javax.swing.JRadioButton();
        ActivoFijo_separador1 = new javax.swing.JSeparator();
        equipoActivoFijo_codigo = new javax.swing.JTextField();
        L_equipoActivoFijo_codigo = new javax.swing.JLabel();
        L_equipoActivoFijo_referencia = new javax.swing.JLabel();
        equipoActivoFijo_referencia = new javax.swing.JTextField();
        equipoActivoFijo_descripcion = new javax.swing.JTextField();
        L_equipoActivoFijo_descripcion = new javax.swing.JLabel();
        ActivoFijo_separador2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();

        Editar.setText("Modificar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

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
        InternalFrameSelectorCampos.getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 30, 10, 340));

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
        InternalFrameSelectorCampos.getContentPane().add(selectAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 90, -1));

        selectPertenencia_Estado.setBackground(new java.awt.Color(255, 255, 255));
        selectPertenencia_Estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectPertenencia_Estado.setForeground(new java.awt.Color(51, 51, 51));
        selectPertenencia_Estado.setText("Pertenencia_Estado");
        selectPertenencia_Estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectPertenencia_Estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 190, -1));
        InternalFrameSelectorCampos.getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 710, 10));

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

        selectProveedorEquipo_Estado.setBackground(new java.awt.Color(255, 255, 255));
        selectProveedorEquipo_Estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectProveedorEquipo_Estado.setForeground(new java.awt.Color(51, 51, 51));
        selectProveedorEquipo_Estado.setText("ProveedorEquipo_Estado");
        selectProveedorEquipo_Estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectProveedorEquipo_Estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 350, 190, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(titulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 710, 30));
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
        InternalFrameSelectorCampos.getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 710, 10));

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

        selectEquipo_ActivoFijo_Descripción.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_ActivoFijo_Descripción.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_ActivoFijo_Descripción.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_ActivoFijo_Descripción.setText("Equipo_ActivoFijo_Descripción");
        selectEquipo_ActivoFijo_Descripción.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_ActivoFijo_Descripción, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 190, -1));

        selectEquipo_ActivoFijo_Referecia.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_ActivoFijo_Referecia.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_ActivoFijo_Referecia.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_ActivoFijo_Referecia.setText("Equipo_ActivoFijo_Referecia");
        selectEquipo_ActivoFijo_Referecia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_ActivoFijo_Referecia, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 190, -1));

        selectEquipo_ActivoFijo_Código.setBackground(new java.awt.Color(255, 255, 255));
        selectEquipo_ActivoFijo_Código.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectEquipo_ActivoFijo_Código.setForeground(new java.awt.Color(51, 51, 51));
        selectEquipo_ActivoFijo_Código.setText("Equipo_ActivoFijo_Código");
        selectEquipo_ActivoFijo_Código.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectEquipo_ActivoFijo_Código, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 190, -1));

        add(InternalFrameSelectorCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1130, 570));

        InternalFrameConsultarEquipos.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrameConsultarEquipos.setClosable(true);
        InternalFrameConsultarEquipos.setVisible(true);
        InternalFrameConsultarEquipos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo.setText("CONSULTAR EQUIPOS");
        InternalFrameConsultarEquipos.getContentPane().add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 590, 30));

        todos.setBackground(new java.awt.Color(255, 255, 255));
        todos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        todos.setText("Todos");
        todos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                todosStateChanged(evt);
            }
        });
        InternalFrameConsultarEquipos.getContentPane().add(todos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 30));

        tipo.setBackground(new java.awt.Color(255, 255, 255));
        tipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tipo.setText("Tipo");
        tipo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tipoStateChanged(evt);
            }
        });
        InternalFrameConsultarEquipos.getContentPane().add(tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, 30));

        marca.setBackground(new java.awt.Color(255, 255, 255));
        marca.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        marca.setText("Marca");
        marca.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                marcaStateChanged(evt);
            }
        });
        InternalFrameConsultarEquipos.getContentPane().add(marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, 30));

        selectTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectTipoActionPerformed(evt);
            }
        });
        InternalFrameConsultarEquipos.getContentPane().add(selectTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 390, 30));

        selectMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMarcaActionPerformed(evt);
            }
        });
        InternalFrameConsultarEquipos.getContentPane().add(selectMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 390, 30));

        especifico.setBackground(new java.awt.Color(255, 255, 255));
        especifico.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        especifico.setText("Especifico");
        especifico.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                especificoStateChanged(evt);
            }
        });
        InternalFrameConsultarEquipos.getContentPane().add(especifico, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, -1, 30));
        InternalFrameConsultarEquipos.getContentPane().add(valorBusquedaEspecifica, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 50, 370, 30));

        selectEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        InternalFrameConsultarEquipos.getContentPane().add(selectEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 90, 370, 30));

        estado1.setBackground(new java.awt.Color(255, 255, 255));
        estado1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        estado1.setText("Estado");
        estado1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                estado1StateChanged(evt);
            }
        });
        InternalFrameConsultarEquipos.getContentPane().add(estado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, -1, 30));

        btn_consultar_cliente.setBackground(new java.awt.Color(255, 255, 255));
        btn_consultar_cliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_consultar_cliente.setText("CONSULTAR");
        btn_consultar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultar_clienteActionPerformed(evt);
            }
        });
        InternalFrameConsultarEquipos.getContentPane().add(btn_consultar_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 140, 30));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCampo.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        InternalFrameConsultarEquipos.getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 50, 40));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Campos");
        InternalFrameConsultarEquipos.getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 40, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Exportar");
        InternalFrameConsultarEquipos.getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 50, 30));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/ExportarExcel.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        InternalFrameConsultarEquipos.getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 50, 40));

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
        tabla.setComponentPopupMenu(Seleccionar);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabla);

        InternalFrameConsultarEquipos.getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 1360, 340));

        add(InternalFrameConsultarEquipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 550));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("ACTUALIZACIÓN DE EQUIPOS");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 520, 30));

        btn_Actualizar.setBackground(new java.awt.Color(255, 255, 255));
        btn_Actualizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_Actualizar.setText("ACTUALIZAR");
        btn_Actualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ActualizarMouseExited(evt);
            }
        });
        btn_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ActualizarActionPerformed(evt);
            }
        });
        add(btn_Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 590, 140, 30));

        btn_cancelar.setBackground(new java.awt.Color(255, 255, 255));
        btn_cancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cancelar.setText("CANCELAR");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 590, 140, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Código Barra:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 120, 30));

        equipoMarca.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 370, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Observación:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 120, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Estado:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 390, 120, 30));

        equipoTipoEquipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipoTipoEquipo.setToolTipText("");
        add(equipoTipoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 370, 30));

        equipoEstado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipoEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        equipoEstado.setToolTipText("");
        add(equipoEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 390, 370, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel7.setText("CARGAR EQUIPO");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 90, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Equipo Referencia:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 120, 30));

        equipoProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 370, 30));

        equipoClasificador2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipoClasificador2.setToolTipText("");
        add(equipoClasificador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 270, 370, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Capacidad:");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 120, 30));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Producto:");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 120, 30));

        equipoObservacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jScrollPane2.setViewportView(equipoObservacion);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, 370, 110));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Tipo Equipo:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 120, 30));

        equipoCodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 370, 30));

        equipoCodigoBarra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoCodigoBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 370, 30));

        equipoReferencia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoReferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 370, 30));

        equipoCapacidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 370, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Marca:");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 120, 30));

        equipoModelo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 110, 370, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Modelo:");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, 110, 30));

        equipoSerial.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoSerial, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 150, 370, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Serial:");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, 110, 30));

        equipoDescripcion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 190, 370, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Descripción:");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 190, 110, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Clasificador 1:");
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 230, 110, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Clasificador 2:");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 270, 110, 30));

        equipoClasificador1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipoClasificador1.setToolTipText("");
        add(equipoClasificador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 370, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Proveedor Equipo:");
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 310, 120, 30));

        equipoProveedorEquipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipoProveedorEquipo.setToolTipText("");
        add(equipoProveedorEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 310, 370, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Pertenencia:");
        add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 350, 140, 30));

        equipoPertenencia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipoPertenencia.setToolTipText("");
        add(equipoPertenencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 350, 370, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/agregar.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 80, 60));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Código:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 120, 30));

        selectActivoFijo.setBackground(new java.awt.Color(255, 255, 255));
        selectActivoFijo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        selectActivoFijo.setText("Activo Fijo:");
        selectActivoFijo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                selectActivoFijoStateChanged(evt);
            }
        });
        add(selectActivoFijo, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 420, -1, 30));
        add(ActivoFijo_separador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 450, 490, 10));

        equipoActivoFijo_codigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(equipoActivoFijo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 460, 370, 30));

        L_equipoActivoFijo_codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        L_equipoActivoFijo_codigo.setText("Código:");
        add(L_equipoActivoFijo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 460, 70, 30));

        L_equipoActivoFijo_referencia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        L_equipoActivoFijo_referencia.setText("Referencia:");
        add(L_equipoActivoFijo_referencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 490, 70, 30));

        equipoActivoFijo_referencia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(equipoActivoFijo_referencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 490, 370, 30));

        equipoActivoFijo_descripcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(equipoActivoFijo_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 520, 370, 30));

        L_equipoActivoFijo_descripcion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        L_equipoActivoFijo_descripcion.setText("Descripción:");
        add(L_equipoActivoFijo_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 520, 80, 30));
        add(ActivoFijo_separador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 560, 490, 10));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Siesa.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 140, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        /*tipoEquipo.setSelectedIndex(0);
        marcaEquipo.setSelectedIndex(0);
        descripcion.setText("");
        valorHora.setText("");
        proveedorEquipo.setSelectedIndex(0);
        procedenciaEquipo.setSelectedIndex(0);
        observacion.setText("");
        estado.setSelectedIndex(0);*/
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ActualizarActionPerformed
       if(equipoCodigo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"El código del equipo no puede estar vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }else{
            try{
                Integer.parseInt(equipoCodigo.getText());
                if(equipoCodigoBarra.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"El código de barra no puede estar vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    if(equipoReferencia.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null,"La Referencia del equipo no puede estar vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        if(equipoProducto.getText().isEmpty()){
                            JOptionPane.showMessageDialog(null,"El nombre del Producto no puede estar Vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            if(equipoCapacidad.getText().isEmpty()){
                                JOptionPane.showMessageDialog(null,"La capacidad del equipo no puede estar vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                if(equipoMarca.getText().isEmpty()){
                                    JOptionPane.showMessageDialog(null,"La marca del equipo no puede estar vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                                }else{    
                                    if(equipoModelo.getText().isEmpty()){
                                        JOptionPane.showMessageDialog(null,"El modelo del Equipo no puede estar vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                                    }else{ 
                                        if(equipoSerial.getText().isEmpty()){
                                            JOptionPane.showMessageDialog(null,"El serial del Equipo no puede estar vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                                        }else{     
                                            if(equipoDescripcion.getText().isEmpty()){
                                                JOptionPane.showMessageDialog(null,"La descripción del equipo no puede estar vacia", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                                            }else{                                                                       
                                                Equipo Objeto = new Equipo();
                                                Objeto.setCodigo(equipoCodigo.getText());    
                                                Objeto.setTipoEquipo(listadoTipoEquipo.get(equipoTipoEquipo.getSelectedIndex()));
                                                Objeto.setCodigo_barra(equipoCodigoBarra.getText());
                                                Objeto.setReferencia(equipoReferencia.getText());
                                                Objeto.setProducto(equipoProducto.getText());
                                                Objeto.setCapacidad(equipoCapacidad.getText());
                                                Objeto.setMarca(equipoMarca.getText());
                                                Objeto.setModelo(equipoModelo.getText());
                                                Objeto.setSerial(equipoSerial.getText());
                                                Objeto.setDescripcion(equipoDescripcion.getText());
                                                Objeto.setClasificador1(listadoClasificadorEquipo.get(equipoClasificador1.getSelectedIndex()));
                                                Objeto.setClasificador2(listadoClasificadorEquipo.get(equipoClasificador2.getSelectedIndex()));
                                                Objeto.setProveedorEquipo(listadoProveedorEquipo.get(equipoProveedorEquipo.getSelectedIndex()));
                                                Objeto.setPertenenciaEquipo(listadoPertenencia.get(equipoPertenencia.getSelectedIndex()));
                                                Objeto.setObservacion(equipoObservacion.getText());
                                                if(equipoEstado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                                                    Objeto.setEstado("1");
                                                }else{
                                                    Objeto.setEstado("0");
                                                }
                                                boolean validador=true;
                                                if(selectActivoFijo.isSelected()){
                                                    try{
                                                        Integer.parseInt(equipoActivoFijo_codigo.getText());
                                                        Objeto.setActivoFijo_codigo(equipoActivoFijo_codigo.getText());
                                                        Objeto.setActivoFijo_referencia(equipoActivoFijo_referencia.getText());
                                                        Objeto.setActivoFijo_descripcion(equipoActivoFijo_descripcion.getText());
                                                    }catch(Exception e){
                                                        JOptionPane.showMessageDialog(null, "El código de activo fijo debe ser númerico", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                                                        validador=false;
                                                    }
                                                }else{
                                                    Objeto.setActivoFijo_codigo(null);
                                                    Objeto.setActivoFijo_referencia(null);
                                                    Objeto.setActivoFijo_descripcion(null);
                                                }
                                                if(validador){
                                                    int respuesta=new ControlDB_Equipo(tipoConexion).actualizar(Objeto, user);
                                                    if(respuesta==1){
                                                        JOptionPane.showMessageDialog(null, "Se actualizó en equipo de manera exitosa", "Registro Exitoso",JOptionPane.INFORMATION_MESSAGE);
                                                        equipoCodigo.setText("");
                                                        equipoCodigoBarra.setText("");
                                                        equipoReferencia.setText("");
                                                        equipoProducto.setText("");
                                                        equipoCapacidad.setText("");
                                                        equipoMarca.setText("");
                                                        equipoModelo.setText("");
                                                        equipoSerial.setText("");
                                                        equipoDescripcion.setText("");
                                                        equipoObservacion.setText("");
                                                        equipoActivoFijo_codigo.setText("");
                                                        equipoActivoFijo_referencia.setText("");
                                                        equipoActivoFijo_descripcion.setText("");

                                                        equipoTipoEquipo.setSelectedIndex(0);
                                                        equipoClasificador1.setSelectedIndex(0);
                                                        equipoClasificador2.setSelectedIndex(0);
                                                        equipoProveedorEquipo.setSelectedIndex(0);
                                                        equipoPertenencia.setSelectedIndex(0);
                                                        equipoEstado.setSelectedIndex(0);
                                                        //Actualizamos el listado de Equipos
                                                        try {
                                                            generarListadoEquipo();
                                                        } catch (SQLException ex) {
                                                            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los Equipos");
                                                            Logger.getLogger(Equipo_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
                                                        }
                                                    }else{
                                                        if(respuesta==0){
                                                            JOptionPane.showMessageDialog(null, "No se pudo actualizó el equipo, valide datos", "Advertencia", JOptionPane.ERROR_MESSAGE);
                                                        }
                                                    }     
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"El código del equipo debe ser númerico", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_ActualizarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        int fila1;
        try{
            fila1=tabla.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ningun Vehiculo");
            }
            else{         
                equipo= listado.get(fila1);
                equipoCodigo.setText(equipo.getCodigo());
                equipoTipoEquipo.setSelectedItem(equipo.getDescripcion());
                equipoCodigoBarra.setText(equipo.getCodigo_barra());
                equipoReferencia.setText(equipo.getReferencia());
                equipoProducto.setText(equipo.getProducto());
                equipoCapacidad.setText(equipo.getCapacidad());
                equipoMarca.setText(equipo.getMarca());
                equipoObservacion.setText(equipo.getObservacion());
                equipoModelo.setText(equipo.getModelo());
                equipoSerial.setText(equipo.getSerial());
                equipoDescripcion.setText(equipo.getDescripcion());
                equipoClasificador1.setSelectedItem(equipo.getClasificador1().getDescripcion());
                equipoClasificador2.setSelectedItem(equipo.getClasificador2().getDescripcion());
                equipoProveedorEquipo.setSelectedItem(equipo.getProveedorEquipo().getDescripcion());
                equipoPertenencia.setSelectedItem(equipo.getPertenenciaEquipo().getDescripcion());
                if(equipo.getEstado().equals("1")){
                    equipoEstado.setSelectedItem("ACTIVO");
                }else{
                    equipoEstado.setSelectedItem("INACTIVO");
                }
                equipoActivoFijo_codigo.setText(equipo.getActivoFijo_codigo());
                equipoActivoFijo_referencia.setText(equipo.getActivoFijo_referencia());
                equipoActivoFijo_descripcion.setText(equipo.getActivoFijo_descripcion());
                InternalFrameConsultarEquipos.show(false);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo cargar el equipo","Advertencia", JOptionPane.ERROR_MESSAGE);   
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void btn_ActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ActualizarMouseExited

    }//GEN-LAST:event_btn_ActualizarMouseExited

    private void todosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_todosStateChanged
        if(todos.isSelected()){
            tipo.setSelected(false);
            marca.setSelected(false);
            especifico.setSelected(false);
            estado1.setSelected(false);
        }else{
        }
    }//GEN-LAST:event_todosStateChanged

    private void tipoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tipoStateChanged
        if(tipo.isSelected() || marca.isSelected()|| especifico.isSelected() || estado1.isSelected()){
            todos.setSelected(false);
        }
    }//GEN-LAST:event_tipoStateChanged

    private void marcaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_marcaStateChanged
        if(tipo.isSelected() || marca.isSelected()|| especifico.isSelected() || estado1.isSelected()){
            todos.setSelected(false);
        }
    }//GEN-LAST:event_marcaStateChanged

    private void selectTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectTipoActionPerformed

    private void selectMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMarcaActionPerformed

    private void especificoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_especificoStateChanged
        if(tipo.isSelected() || marca.isSelected()|| especifico.isSelected() || estado1.isSelected()){
            todos.setSelected(false);
        }
    }//GEN-LAST:event_especificoStateChanged

    private void estado1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_estado1StateChanged
        if(tipo.isSelected() || marca.isSelected()|| especifico.isSelected() || estado1.isSelected()){
            todos.setSelected(false);
        }
    }//GEN-LAST:event_estado1StateChanged

    private void btn_consultar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultar_clienteActionPerformed
        try {
            generarListadoEquipo();
        } catch (SQLException ex) {
            Logger.getLogger(Equipo_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_consultar_clienteActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        InternalFrameSelectorCampos.show(true);
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
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
    }//GEN-LAST:event_jLabel10MouseClicked

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

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        InternalFrameConsultarEquipos.show(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void selectActivoFijoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_selectActivoFijoStateChanged
        if(selectActivoFijo.isSelected()){
            ActivoFijo_separador1.show(true);
            L_equipoActivoFijo_codigo.show(true);
            equipoActivoFijo_codigo.show(true);
            L_equipoActivoFijo_referencia.show(true);
            equipoActivoFijo_referencia.show(true);
            L_equipoActivoFijo_descripcion.show(true);
            equipoActivoFijo_descripcion.show(true);
            ActivoFijo_separador2.show(true);
        }else{
            ActivoFijo_separador1.show(false);
            L_equipoActivoFijo_codigo.show(false);
            equipoActivoFijo_codigo.show(false);
            L_equipoActivoFijo_referencia.show(false);
            equipoActivoFijo_referencia.show(false);
            L_equipoActivoFijo_descripcion.show(false);
            equipoActivoFijo_descripcion.show(false);
            ActivoFijo_separador2.show(false);
        }
    }//GEN-LAST:event_selectActivoFijoStateChanged

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() == 2) {
            int fila1;
            try{
                fila1=tabla.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ningun Vehiculo");
                }
                else{         
                    equipo= listado.get(fila1);
                    equipoCodigo.setText(equipo.getCodigo());
                    equipoTipoEquipo.setSelectedItem(equipo.getDescripcion());
                    equipoCodigoBarra.setText(equipo.getCodigo_barra());
                    equipoReferencia.setText(equipo.getReferencia());
                    equipoProducto.setText(equipo.getProducto());
                    equipoCapacidad.setText(equipo.getCapacidad());
                    equipoMarca.setText(equipo.getMarca());
                    equipoObservacion.setText(equipo.getObservacion());
                    equipoModelo.setText(equipo.getModelo());
                    equipoSerial.setText(equipo.getSerial());
                    equipoDescripcion.setText(equipo.getDescripcion());
                    equipoClasificador1.setSelectedItem(equipo.getClasificador1().getDescripcion());
                    equipoClasificador2.setSelectedItem(equipo.getClasificador2().getDescripcion());
                    equipoProveedorEquipo.setSelectedItem(equipo.getProveedorEquipo().getDescripcion());
                    equipoPertenencia.setSelectedItem(equipo.getPertenenciaEquipo().getDescripcion());
                    if(equipo.getEstado().equals("1")){
                        equipoEstado.setSelectedItem("ACTIVO");
                    }else{
                        equipoEstado.setSelectedItem("INACTIVO");
                    }
                    equipoActivoFijo_codigo.setText(equipo.getActivoFijo_codigo());
                    equipoActivoFijo_referencia.setText(equipo.getActivoFijo_referencia());
                    equipoActivoFijo_descripcion.setText(equipo.getActivoFijo_descripcion());
                    InternalFrameConsultarEquipos.show(false);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo cargar el equipo","Advertencia", JOptionPane.ERROR_MESSAGE);   
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        int opcion=JOptionPane.showConfirmDialog(null, "Esta seguro que desea cargar del ERP\nCódigoBarra,Referencia,Código de ActivoFijo,Referencia de ActivoFijo,Descripcion deActivoFijo?","Advertencia",JOptionPane.INFORMATION_MESSAGE);
        System.out.println(""+opcion);
        if(opcion==0){
            if(!equipoCodigo.getText().isEmpty()){
                Equipo equipoB = new Equipo();
                equipoB.setCodigo(equipoCodigo.getText());
                try {
                    equipoB=new ControlDB_Equipo(tipoConexion).buscarEquiposParticularEnERP(equipoB);
                    if(equipoB != null){
                        equipoCodigoBarra.setText(equipoB.getCodigo_barra());
                        equipoReferencia.setText(equipoB.getReferencia());
                        equipoActivoFijo_codigo.setText(equipoB.getActivoFijo_codigo());
                        equipoActivoFijo_referencia.setText(equipoB.getActivoFijo_referencia());
                        equipoActivoFijo_descripcion.setText(equipoB.getActivoFijo_descripcion());
                    }else{
                        JOptionPane.showMessageDialog(null, "El equipo buscado no existe","Advertencia",JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Equipo_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Deber cargar un Equipo, verifique datos", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            }
        }      
    }//GEN-LAST:event_jLabel3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator ActivoFijo_separador1;
    private javax.swing.JSeparator ActivoFijo_separador2;
    private javax.swing.JMenuItem Editar;
    private javax.swing.JInternalFrame InternalFrameConsultarEquipos;
    private javax.swing.JInternalFrame InternalFrameSelectorCampos;
    private javax.swing.JLabel L_equipoActivoFijo_codigo;
    private javax.swing.JLabel L_equipoActivoFijo_descripcion;
    private javax.swing.JLabel L_equipoActivoFijo_referencia;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JButton btn_Actualizar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_consultar_cliente;
    private javax.swing.JTextField equipoActivoFijo_codigo;
    private javax.swing.JTextField equipoActivoFijo_descripcion;
    private javax.swing.JTextField equipoActivoFijo_referencia;
    private javax.swing.JTextField equipoCapacidad;
    private javax.swing.JComboBox<String> equipoClasificador1;
    private javax.swing.JComboBox<String> equipoClasificador2;
    private javax.swing.JTextField equipoCodigo;
    private javax.swing.JTextField equipoCodigoBarra;
    private javax.swing.JTextField equipoDescripcion;
    private javax.swing.JComboBox<String> equipoEstado;
    private javax.swing.JTextField equipoMarca;
    private javax.swing.JTextField equipoModelo;
    private javax.swing.JTextPane equipoObservacion;
    private javax.swing.JComboBox<String> equipoPertenencia;
    private javax.swing.JTextField equipoProducto;
    private javax.swing.JComboBox<String> equipoProveedorEquipo;
    private javax.swing.JTextField equipoReferencia;
    private javax.swing.JTextField equipoSerial;
    private javax.swing.JComboBox<String> equipoTipoEquipo;
    private javax.swing.JRadioButton especifico;
    private javax.swing.JRadioButton estado1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
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
    private javax.swing.JRadioButton selectActivoFijo;
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
    private javax.swing.JRadioButton selectPertenencia_Estado;
    private javax.swing.JRadioButton selectPertenencia_Nombre;
    private javax.swing.JRadioButton selectProveedorEquipo_Código;
    private javax.swing.JRadioButton selectProveedorEquipo_Estado;
    private javax.swing.JRadioButton selectProveedorEquipo_Nit;
    private javax.swing.JRadioButton selectProveedorEquipo_Nombre;
    private javax.swing.JComboBox<String> selectTipo;
    private javax.swing.JRadioButton selectTipoEquipo_Código;
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
            if(estado1.isSelected()){
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
                    sql += " WHERE [eq_desc] LIKE ?";
                    busquedaEspecifica="%"+valorBusquedaEspecifica.getText()+"%";
                    contador++;
                }else{
                    sql += " AND [eq_desc] LIKE ?";
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
        if(selectEquipo_Código.isSelected()){
            Stitulo +="Equipo_Código"+":";
            contador++;
        }
        if(selectEquipo_CódigoBarra.isSelected()){
            Stitulo +="Equipo_CódigoBarra"+":";
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
        if(selectEquipo_Descripción.isSelected()){
            Stitulo +="Equipo_Descripción"+":";
            contador++;
        }
        if(selectEquipo_Observación.isSelected()){
            Stitulo +="Equipo_Observación"+":";
            contador++;
        }
        if(selectEquipo_Estado.isSelected()){
            Stitulo +="Equipo_Estado"+":";
            contador++;
        }
        if(selectEquipo_ActivoFijo_Código.isSelected()){
            Stitulo +="Equipo_ActivoFijo_Código"+":";
            contador++;
        }
        if(selectEquipo_ActivoFijo_Referecia.isSelected()){
            Stitulo +="Equipo_ActivoFijo_Referecia"+":";
            contador++;
        }
        if(selectEquipo_ActivoFijo_Descripción.isSelected()){
            Stitulo +="Equipo_ActivoFijo_Descripción"+":";
            contador++;
        }
        if(selectTipoEquipo_Código.isSelected()){
            Stitulo +="TipoEquipo_Código"+":";
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
        if(selectClasificador1_Código.isSelected()){
            Stitulo +="Clasificador1_Código"+":";
            contador++;
        }
        if(selectClasificador1_Descripción.isSelected()){
            Stitulo +="Clasificador1_Descripción"+":";
            contador++;
        }
        if(selectClasificador2_Código.isSelected()){
            Stitulo +="Clasificador2_Código"+":";
            contador++;
        }
        
        //CLIENTE
        if(selectClasificador2_Descripción.isSelected()){
            Stitulo +="Clasificador2_Descripción"+":";
            contador++;
        }
        if(selectProveedorEquipo_Código.isSelected()){
            Stitulo +="ProveedorEquipo_Código"+":";
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
        if(selectPertenencia_Código.isSelected()){
            Stitulo +="Pertenencia_Código"+":";
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
        DefaultTableModel modelo = new DefaultTableModel(null, Stitulo.split(":"));  
        
        listado=new ControlDB_Equipo(tipoConexion).buscarEquiposEnAplicacionInterna(sql, busquedaEspecifica);
        for (Equipo listado1 : listado) {
            Object[] registro = new String[contador];
            int contadorRegistro=0;
            
            if(selectEquipo_Código.isSelected()){
                registro[contadorRegistro] = "" + listado1.getCodigo();
                contadorRegistro++;
            }
            
            if(selectEquipo_CódigoBarra.isSelected()){
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
            if(selectEquipo_Descripción.isSelected()){
                registro[contadorRegistro] = "" + listado1.getDescripcion();
                contadorRegistro++;
            }
            if(selectEquipo_Observación.isSelected()){
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
             if(selectEquipo_ActivoFijo_Código.isSelected()){
                registro[contadorRegistro] = "" + listado1.getActivoFijo_codigo();
                contadorRegistro++;
            }
            if(selectEquipo_ActivoFijo_Referecia.isSelected()){
                registro[contadorRegistro] = "" + listado1.getActivoFijo_referencia();
                contadorRegistro++;
            }
            if(selectEquipo_ActivoFijo_Descripción.isSelected()){
                registro[contadorRegistro] = "" + listado1.getActivoFijo_descripcion();
                contadorRegistro++;
            }
            // Tipo Equipo
            if(selectTipoEquipo_Código.isSelected()){
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
            if(selectClasificador1_Código.isSelected()){
                registro[contadorRegistro] = "" + listado1.getClasificador1().getCodigo();
                contadorRegistro++;
            }
            if(selectClasificador1_Descripción.isSelected()){
                registro[contadorRegistro] = "" + listado1.getClasificador1().getDescripcion();
                contadorRegistro++;
            }
            
            //Clasificador 2
            if(selectClasificador2_Código.isSelected()){
                registro[contadorRegistro] = "" + listado1.getClasificador2().getCodigo();
                contadorRegistro++;
            }
            if(selectClasificador2_Descripción.isSelected()){
                registro[contadorRegistro] = "" + listado1.getClasificador2().getDescripcion();
                contadorRegistro++;
            }

            //CLIENTE
            if(selectProveedorEquipo_Código.isSelected()){
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
            if(selectPertenencia_Código.isSelected()){
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
            modelo.addRow(registro);      
            }
        tabla.setModel(modelo);
        //tabla.setFillsViewportHeight(true);
    }
    public void selectorCampoPorDefecto(){
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
        //Campos Inactivos
        selectEquipo_CódigoBarra.setSelected(false);
        selectEquipo_Referencia.setSelected(false);
        selectEquipo_Observación.setSelected(false);
        selectEquipo_Estado.setSelected(false);
        selectEquipo_ActivoFijo_Código.setSelected(false);
        selectEquipo_ActivoFijo_Referecia.setSelected(false);
        selectEquipo_ActivoFijo_Descripción.setSelected(false);
        selectTipoEquipo_Código.setSelected(false);
        selectTipoEquipo_Estado.setSelected(false);
        selectClasificador1_Código.setSelected(false);
        selectClasificador1_Descripción.setSelected(false);
        selectClasificador2_Código.setSelected(false);
        selectClasificador2_Descripción.setSelected(false);
        selectProveedorEquipo_Código.setSelected(false);
        selectProveedorEquipo_Nit.setSelected(false);
        selectProveedorEquipo_Nombre.setSelected(false);
        selectProveedorEquipo_Estado.setSelected(false);
        selectPertenencia_Código.setSelected(false);
        selectPertenencia_Nombre.setSelected(false);
        selectPertenencia_Estado.setSelected(false); 
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
        selectTipoEquipo_Estado.setSelected(true);
        selectClasificador1_Código.setSelected(true);
        selectClasificador1_Descripción.setSelected(true);
        selectClasificador2_Código.setSelected(true);
        selectClasificador2_Descripción.setSelected(true);
        selectProveedorEquipo_Código.setSelected(true);
        selectProveedorEquipo_Nit.setSelected(true);
        selectProveedorEquipo_Nombre.setSelected(true);
        selectProveedorEquipo_Estado.setSelected(true);
        selectPertenencia_Código.setSelected(true);
        selectPertenencia_Nombre.setSelected(true);
        selectPertenencia_Estado.setSelected(true);       
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
        selectTipoEquipo_Estado.setSelected(false);
        selectClasificador1_Código.setSelected(false);
        selectClasificador1_Descripción.setSelected(false);
        selectClasificador2_Código.setSelected(false);
        selectClasificador2_Descripción.setSelected(false);
        selectProveedorEquipo_Código.setSelected(false);
        selectProveedorEquipo_Nit.setSelected(false);
        selectProveedorEquipo_Nombre.setSelected(false);
        selectProveedorEquipo_Estado.setSelected(false);
        selectPertenencia_Código.setSelected(false);
        selectPertenencia_Nombre.setSelected(false);
        selectPertenencia_Estado.setSelected(false);        
    }  

}
