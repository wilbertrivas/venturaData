package Catalogo.View23;
import Catalogo.Controller.ControlDB_Equipo;
import Catalogo.Model.Equipo;
import Catalogo.Model.TarifaEquipo;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
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

public final class Equipo_Tarifa_Backup extends javax.swing.JPanel {
    ArrayList<Equipo> listado=null;
    DefaultTableModel modeloCliente;
    Usuario user;
    private String tipoConexion;
    public Equipo_Tarifa_Backup(Usuario us,String tipoConexion) {
        initComponents();
        this.tipoConexion= tipoConexion;
        user=us;
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
            Logger.getLogger(Equipo_Tarifa_Backup.class.getName()).log(Level.SEVERE, null, ex);
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
        
        jLabel12.show(false);
        jLabel5.show(false);
        
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
        selectEquipo_ActivoFijo_Código = new javax.swing.JRadioButton();
        selectEquipo_ActivoFijo_Referecia = new javax.swing.JRadioButton();
        selectEquipo_ActivoFijo_Descripción = new javax.swing.JRadioButton();
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
        jLabel12 = new javax.swing.JLabel();
        tarifa_valorHoraOperacion = new javax.swing.JTextField();
        tarifa_valorHoraAlquiler = new javax.swing.JTextField();
        tarifa_año = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btn_consultar_cliente1 = new javax.swing.JButton();
        btn_consultar_cliente2 = new javax.swing.JButton();

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

        selectPertenencia_Estado.setBackground(new java.awt.Color(255, 255, 255));
        selectPertenencia_Estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectPertenencia_Estado.setForeground(new java.awt.Color(51, 51, 51));
        selectPertenencia_Estado.setText("Pertenencia_Estado");
        selectPertenencia_Estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectPertenencia_Estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 190, -1));
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

        add(InternalFrameSelectorCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1130, 570));

        titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo.setText("REGISTRO DE TARIFAS EN LOS EQUIPOS");
        add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 590, 30));

        tabla = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                if (colIndex == 9 || colIndex == 10 || colIndex ==11)  {
                    return true;
                }else{
                    return false;
                }
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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 1240, 340));

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
        add(btn_consultar_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 140, 140, 30));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/ExportarExcel.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 190, 50, 40));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Exportar");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 170, 50, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("VALOR HORA ALQUILER:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 190, 160, 30));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCampo.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 190, 50, 40));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Campos");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 170, 40, 30));

        tarifa_valorHoraOperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tarifa_valorHoraOperacionActionPerformed(evt);
            }
        });
        add(tarifa_valorHoraOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, 100, 30));
        add(tarifa_valorHoraAlquiler, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 190, 100, 30));
        add(tarifa_año, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 100, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("AÑO DE LA TARIFA:");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 140, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("VALOR HORA OPERACIÓN:");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, 180, 30));

        btn_consultar_cliente1.setBackground(new java.awt.Color(255, 255, 255));
        btn_consultar_cliente1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_consultar_cliente1.setText("Aplicar Valores");
        btn_consultar_cliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultar_cliente1ActionPerformed(evt);
            }
        });
        add(btn_consultar_cliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 190, 140, 30));

        btn_consultar_cliente2.setBackground(new java.awt.Color(255, 255, 255));
        btn_consultar_cliente2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_consultar_cliente2.setText("REGISTRAR TARIFAS");
        btn_consultar_cliente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultar_cliente2ActionPerformed(evt);
            }
        });
        add(btn_consultar_cliente2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 590, 200, 30));
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
        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
        if(selecArchivo.showDialog(null, "Exportar") ==JFileChooser.APPROVE_OPTION){
            archivo=selecArchivo.getSelectedFile();
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

    private void btn_consultar_cliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultar_cliente1ActionPerformed
        try{
            if(!tarifa_año.getText().equals("")){
                Integer.parseInt(tarifa_año.getText());
                try{
                    if(!tarifa_valorHoraOperacion.getText().equals("")){
                        Integer.parseInt(tarifa_valorHoraOperacion.getText());
                        try{
                            if(!tarifa_valorHoraAlquiler.getText().equals("")){
                                Integer.parseInt(tarifa_valorHoraAlquiler.getText());
                                for(Equipo objeto : listado){
                                    TarifaEquipo tarifaEquipoNueva = new TarifaEquipo();
                                    tarifaEquipoNueva.setAño(tarifa_año.getText());
                                    tarifaEquipoNueva.setValorHoraOperacion(tarifa_valorHoraOperacion.getText());
                                    tarifaEquipoNueva.setValorHoraAlquiler(tarifa_valorHoraAlquiler.getText());
                                    objeto.setTarifaEquipoNueva(tarifaEquipoNueva);
                                }
                                try{
                                    aplicarTarifaGeneral();
                                }catch(Exception e){
                                    JOptionPane.showMessageDialog(null,"No se pudo aplicar la tarifa a los equipos del listado, valide datos","Error!!",JOptionPane.ERROR_MESSAGE);  
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
            }else{
                JOptionPane.showMessageDialog(null,"El año no puede estar vacio","Error!!",JOptionPane.ERROR_MESSAGE);  
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"El año para aplicar el valor de la tarifa debe ser númerico y no contener punto","Error!!",JOptionPane.ERROR_MESSAGE);  
        }
    }//GEN-LAST:event_btn_consultar_cliente1ActionPerformed

    private void tarifa_valorHoraOperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tarifa_valorHoraOperacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tarifa_valorHoraOperacionActionPerformed

    private void btn_consultar_cliente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultar_cliente2ActionPerformed
        try{
            int filasTable = tabla.getRowCount();
            DefaultTableModel modeloX=(DefaultTableModel)tabla.getModel();
            for(int i=0; i< filasTable; i++){
                int anoNuevo=Integer.parseInt((String)modeloX.getValueAt(i, 9));
                int valorOperacionNuevo=Integer.parseInt((String)modeloX.getValueAt(i, 10));
                int valorAlquilerNuevo=Integer.parseInt((String)modeloX.getValueAt(i, 11));
                //JOptionPane.showMessageDialog(null,"anoNuevo "+anoNuevo+" valorOperacionNuevo "+valorOperacionNuevo+" valorAlquilerNuevo "+valorAlquilerNuevo);

                TarifaEquipo tarifaEquip = new TarifaEquipo();
                tarifaEquip.setAño(""+anoNuevo);
                tarifaEquip.setValorHoraOperacion(""+valorOperacionNuevo);
                tarifaEquip.setValorHoraAlquiler(""+valorAlquilerNuevo);
                tarifaEquip.setCodigoEquipo(listado.get(i).getCodigo());
                listado.get(i).setTarifaEquipoNueva(tarifaEquip);
                //JOptionPane.showMessageDialog(null,"anoNuevo "+listado.get(i).getTarifaEquipoNueva().getAño()+" valorOperacionNuevo "+listado.get(i).getTarifaEquipoNueva().getValorHoraOperacion()+" valorAlquilerNuevo "+listado.get(i).getTarifaEquipoNueva().getValorHoraAlquiler());

            }
            int respuesta=new ControlDB_Equipo(tipoConexion).registrarTarifaEquipo(listado, user);
            if(respuesta==1){
                JOptionPane.showMessageDialog(null, "Se registro las tarifas de los equipos de forma exitosa");
                try {
                    generarListadoEquipo();
                } catch (SQLException ex) {
                    Logger.getLogger(Equipo_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                if(respuesta==0){
                    JOptionPane.showMessageDialog(null, "No se pudo registrar la tarifa de los equipos, valide datos", "Advertencia", JOptionPane.ERROR_MESSAGE);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error!!. valide datos ingresados, recuerde que deben ser númericos y no contener punto","Error al procesar datos", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_consultar_cliente2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame InternalFrameSelectorCampos;
    private javax.swing.JButton btn_consultar_cliente;
    private javax.swing.JButton btn_consultar_cliente1;
    private javax.swing.JButton btn_consultar_cliente2;
    private javax.swing.JRadioButton especifico;
    private javax.swing.JRadioButton estado;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JTextField tarifa_año;
    private javax.swing.JTextField tarifa_valorHoraAlquiler;
    private javax.swing.JTextField tarifa_valorHoraOperacion;
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
        if(selectEquipo_Código.isSelected()){
            Stitulo +="Código"+":";
            contador++;
        }
        if(selectEquipo_CódigoBarra.isSelected()){
            Stitulo +="CódigoBarra"+":";
            contador++;
        }
        if(selectEquipo_Referencia.isSelected()){
            Stitulo +="Referencia"+":";
            contador++;
        }
        if(selectEquipo_Producto.isSelected()){
            Stitulo +="Producto"+":";
            contador++;
        }
        if(selectEquipo_Capacidad.isSelected()){
            Stitulo +="Capacidad"+":";
            contador++;
        }
        if(selectEquipo_Marca.isSelected()){
            Stitulo +="Marca"+":";
            contador++;
        }
        if(selectEquipo_Modelo.isSelected()){
            Stitulo +="Modelo"+":";
            contador++;
        }
        if(selectEquipo_Serial.isSelected()){
            Stitulo +="Serial"+":";
            contador++;
        }
        if(selectEquipo_Descripción.isSelected()){
            Stitulo +="Descripción"+":";
            contador++;
        }
        if(selectEquipo_Observación.isSelected()){
            Stitulo +="Observación"+":";
            contador++;
        }
        if(selectEquipo_Estado.isSelected()){
            Stitulo +="Estado"+":";
            contador++;
        }
        if(selectEquipo_ActivoFijo_Código.isSelected()){
            Stitulo +="ActivoFijo_Código"+":";
            contador++;
        }
        if(selectEquipo_ActivoFijo_Referecia.isSelected()){
            Stitulo +="ActivoFijo_Referecia"+":";
            contador++;
        }
        if(selectEquipo_ActivoFijo_Descripción.isSelected()){
            Stitulo +="ActivoFijo_Descripción"+":";
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
        Stitulo +="Año Anterior:ValorHoraOperación Anterior:ValorHoraAlquiler Anterior:Año Nuevo:ValorHoraOperación Nuevo:ValorHoraAlquiler Nuevo";
            contador=contador + 6;
        
        DefaultTableModel modelo = new DefaultTableModel(null, Stitulo.split(":"));  
        
        listado=new ControlDB_Equipo(tipoConexion).buscarEquiposEnAplicacionInterna(sql,busquedaEspecifica);
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
            //Año de la tarifa Anterior
                registro[contadorRegistro] = ""+ listado1.getTarifaEquipoAnterior().getAño();
                contadorRegistro++;

                //Tarifa Valor de la Hora por operación Anterior
                registro[contadorRegistro] = ""+ listado1.getTarifaEquipoAnterior().getValorHoraOperacion();
                contadorRegistro++;

                 //Tarifa Valor de la Hora por alquiler Anterior
                registro[contadorRegistro] = ""+ listado1.getTarifaEquipoAnterior().getValorHoraAlquiler();
                contadorRegistro++;
                
                //Año de la tarifa Nuevo
                registro[contadorRegistro] = ""+ listado1.getTarifaEquipoNueva().getAño();
                contadorRegistro++;

                //Tarifa Valor de la Hora por operación Nuevo
                registro[contadorRegistro] = ""+ listado1.getTarifaEquipoNueva().getValorHoraOperacion();
                contadorRegistro++;

                 //Tarifa Valor de la Hora por alquiler Nuevo
                registro[contadorRegistro] = ""+ listado1.getTarifaEquipoNueva().getValorHoraAlquiler();
                contadorRegistro++;
            
            modelo.addRow(registro);      
            }
        tabla.setModel(modelo);
        //tabla.setFillsViewportHeight(true);
    }
    public void aplicarTarifaGeneral() throws SQLException{
        if(listado != null){
            String Stitulo="";
            int contador=0;
            if(selectEquipo_Código.isSelected()){
                Stitulo +="Código"+":";
                contador++;
            }
            if(selectEquipo_CódigoBarra.isSelected()){
                Stitulo +="CódigoBarra"+":";
                contador++;
            }
            if(selectEquipo_Referencia.isSelected()){
                Stitulo +="Referencia"+":";
                contador++;
            }
            if(selectEquipo_Producto.isSelected()){
                Stitulo +="Producto"+":";
                contador++;
            }
            if(selectEquipo_Capacidad.isSelected()){
                Stitulo +="Capacidad"+":";
                contador++;
            }
            if(selectEquipo_Marca.isSelected()){
                Stitulo +="Marca"+":";
                contador++;
            }
            if(selectEquipo_Modelo.isSelected()){
                Stitulo +="Modelo"+":";
                contador++;
            }
            if(selectEquipo_Serial.isSelected()){
                Stitulo +="Serial"+":";
                contador++;
            }
            if(selectEquipo_Descripción.isSelected()){
                Stitulo +="Descripción"+":";
                contador++;
            }
            if(selectEquipo_Observación.isSelected()){
                Stitulo +="Observación"+":";
                contador++;
            }
            if(selectEquipo_Estado.isSelected()){
                Stitulo +="Estado"+":";
                contador++;
            }
            if(selectEquipo_ActivoFijo_Código.isSelected()){
                Stitulo +="ActivoFijo_Código"+":";
                contador++;
            }
            if(selectEquipo_ActivoFijo_Referecia.isSelected()){
                Stitulo +="ActivoFijo_Referecia"+":";
                contador++;
            }
            if(selectEquipo_ActivoFijo_Descripción.isSelected()){
                Stitulo +="ActivoFijo_Descripción"+":";
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
            Stitulo +="Año Anterior:ValorHoraOperación Anterior:ValorHoraAlquiler Anterior:Año Nuevo:ValorHoraOperación Nuevo:ValorHoraAlquiler Nuevo";
            contador=contador + 6;

            DefaultTableModel modelo = new DefaultTableModel(null, Stitulo.split(":"));  
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
                //Año de la tarifa Anterior
                registro[contadorRegistro] = ""+ listado1.getTarifaEquipoAnterior().getAño();
                contadorRegistro++;

                //Tarifa Valor de la Hora por operación Anterior
                registro[contadorRegistro] = ""+ listado1.getTarifaEquipoAnterior().getValorHoraOperacion();
                contadorRegistro++;

                 //Tarifa Valor de la Hora por alquiler Anterior
                registro[contadorRegistro] = ""+ listado1.getTarifaEquipoAnterior().getValorHoraAlquiler();
                contadorRegistro++;
                
                //Año de la tarifa Nuevo
                registro[contadorRegistro] = ""+ listado1.getTarifaEquipoNueva().getAño();
                contadorRegistro++;

                //Tarifa Valor de la Hora por operación Nuevo
                registro[contadorRegistro] = ""+ listado1.getTarifaEquipoNueva().getValorHoraOperacion();
                contadorRegistro++;

                 //Tarifa Valor de la Hora por alquiler Nuevo
                registro[contadorRegistro] = ""+ listado1.getTarifaEquipoNueva().getValorHoraAlquiler();
                contadorRegistro++;

                modelo.addRow(registro);      
                }
            tabla.setModel(modelo);
            //tabla.setFillsViewportHeight(true);
        }
        
    }
    public void selectorCampoPorDefecto(){
        //Campos Activos
        selectEquipo_Código.setSelected(true);
        selectEquipo_Producto.setSelected(true);
        selectEquipo_Capacidad.setSelected(false);
        selectEquipo_Marca.setSelected(true);
        selectEquipo_Modelo.setSelected(true);
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
