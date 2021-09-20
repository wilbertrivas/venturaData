package ModuloPalero.View;

import Catalogo.View.*;
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
import ModuloPalero.Controller.ControlDB_EquipoLiquidacion;
import ModuloPalero.Model.EquipoLiquidacion;
import ModuloPersonal.Model.Persona;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class EquipoLiquidacion_Registrar extends javax.swing.JPanel {
    Usuario user;
    ArrayList<Equipo> listado = null;
    Equipo equipo = null;
    ArrayList<TipoEquipo> listadoTipoEquipo = new ArrayList();
    ArrayList<ClasificadorEquipo> listadoClasificadorEquipo = new ArrayList();
    ArrayList<ProveedorEquipo> listadoProveedorEquipo = new ArrayList();
    ArrayList<Pertenencia> listadoPertenencia = new ArrayList();
    ArrayList<String> listadoMarcaEquipo = new ArrayList();
    private String tipoConexion;
    ArrayList<EquipoLiquidacion> listadoEquipoLiquidacion;
    
    public EquipoLiquidacion_Registrar(Usuario us, String tipoConexion) throws SQLException {
        user = us;
        initComponents();
        this.tipoConexion = tipoConexion;
        listadoTipoEquipo = new ControlDB_TipoEquipo(tipoConexion).buscarActivos();
        listadoClasificadorEquipo = new ControlDB_ClasificadorEquipo(tipoConexion).buscarActivos();
        listadoProveedorEquipo = new ControlDB_ProveedorEquipo(tipoConexion).buscarActivos();
        listadoPertenencia = new ControlDB_PertenenciaEquipo(tipoConexion).buscarActivos();
        listadoMarcaEquipo = new ControlDB_Equipo(tipoConexion).buscarMarcasEquiposEnAplicacionInterna();
        InternalFrameConsultarEquipos.getContentPane().setBackground(Color.WHITE);
        InternalFrameConsultarEquipos.show(false);
        InternalFrameMasDetalle.getContentPane().setBackground(Color.WHITE);
        InternalFrameMasDetalle.show(false);

        todos.setSelected(true);
        tipo.setSelected(false);
        marca.setSelected(false);
        especifico.setSelected(false);
        estado1.setSelected(false);
        equipoCodigo.setEnabled(false);
        try {
            tabla_Listar2("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los Equipos");
            Logger.getLogger(Equipo_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Cargamos en la interfaz los TiposEquipos activos
        for (TipoEquipo TipoEquipo1 : listadoTipoEquipo) {
            equipoTipoEquipo.addItem(TipoEquipo1.getDescripcion());
            selectTipo.addItem(TipoEquipo1.getDescripcion());
            equipoTipoEquipo1.addItem(TipoEquipo1.getDescripcion());
        }

        //Cargamos en la interfaz las Marcas de equipos
        for (int i = 0; i < listadoMarcaEquipo.size(); i++) {
            selectMarca.addItem("" + listadoMarcaEquipo.get(i));
        }
        equipoCodigo.setEditable(false);
        equipoTipoEquipo.setEnabled(false);
        equipoCodigoBarra.setEditable(false);
        equipoReferencia.setEditable(false);
        equipoProducto.setEditable(false);
        equipoCapacidad.setEditable(false);
        equipoMarca.setEditable(false);
        equipoModelo.setEditable(false);
        equipoSerial.setEditable(false);
        equipoDescripcion.setEditable(false);
        
        equipoCodigo1.setEditable(false);
        equipoTipoEquipo1.setEnabled(false);
        equipoCodigoBarra1.setEditable(false);
        equipoReferencia1.setEditable(false);
        equipoProducto1.setEditable(false);
        equipoCapacidad1.setEditable(false);
        equipoMarca1.setEditable(false);
        equipoModelo1.setEditable(false);
        equipoSerial1.setEditable(false);
        equipoDescripcion1.setEditable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        Seleccionar1 = new javax.swing.JPopupMenu();
        Editar1 = new javax.swing.JMenuItem();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        InternalFrameMasDetalle = new javax.swing.JInternalFrame();
        titulo1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla3 = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        equipoCodigo1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        equipoModelo1 = new javax.swing.JTextField();
        equipoSerial1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        equipoTipoEquipo1 = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        equipoCodigoBarra1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        equipoDescripcion1 = new javax.swing.JTextField();
        equipoCapacidad1 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        equipoReferencia1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        equipoProducto1 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        equipoMarca1 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        btn_Actualizar = new javax.swing.JButton();
        valorBusqueda = new javax.swing.JTextField();
        consultar4 = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        equipoMarca = new javax.swing.JTextField();
        equipoTipoEquipo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        equipoProducto = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();

        Editar.setText("Seleccionar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        Editar1.setText("MasDetalle");
        Editar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Editar1ActionPerformed(evt);
            }
        });
        Seleccionar1.add(Editar1);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InternalFrameConsultarEquipos.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrameConsultarEquipos.setClosable(true);
        InternalFrameConsultarEquipos.setVisible(false);
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

        tabla2 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla2.setComponentPopupMenu(Seleccionar);
        tabla2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla2);

        InternalFrameConsultarEquipos.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 1300, 340));

        add(InternalFrameConsultarEquipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 550));

        InternalFrameMasDetalle.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrameMasDetalle.setClosable(true);
        InternalFrameMasDetalle.setVisible(false);
        InternalFrameMasDetalle.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo1.setForeground(new java.awt.Color(0, 102, 102));
        titulo1.setText("DETALLE DE EQUIPO PARA LIQUIDACIÓN");
        InternalFrameMasDetalle.getContentPane().add(titulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 590, 30));

        tabla3 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabla3);

        InternalFrameMasDetalle.getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 1180, 190));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Código:");
        InternalFrameMasDetalle.getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 120, 30));

        equipoCodigo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        InternalFrameMasDetalle.getContentPane().add(equipoCodigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 290, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Modelo:");
        InternalFrameMasDetalle.getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 80, 30));

        equipoModelo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        InternalFrameMasDetalle.getContentPane().add(equipoModelo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 310, 30));

        equipoSerial1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        InternalFrameMasDetalle.getContentPane().add(equipoSerial1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, 310, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Serial:");
        InternalFrameMasDetalle.getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 80, 30));

        equipoTipoEquipo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipoTipoEquipo1.setToolTipText("");
        InternalFrameMasDetalle.getContentPane().add(equipoTipoEquipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 290, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Tipo Equipo:");
        InternalFrameMasDetalle.getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 120, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Código Barra:");
        InternalFrameMasDetalle.getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 120, 30));

        equipoCodigoBarra1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        InternalFrameMasDetalle.getContentPane().add(equipoCodigoBarra1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 290, 30));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Descripción:");
        InternalFrameMasDetalle.getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 80, 30));

        equipoDescripcion1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        InternalFrameMasDetalle.getContentPane().add(equipoDescripcion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 310, 30));

        equipoCapacidad1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        InternalFrameMasDetalle.getContentPane().add(equipoCapacidad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 160, 310, 30));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Capacidad:");
        InternalFrameMasDetalle.getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 80, 30));

        equipoReferencia1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        InternalFrameMasDetalle.getContentPane().add(equipoReferencia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 290, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Equipo Referencia:");
        InternalFrameMasDetalle.getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 120, 30));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("Producto:");
        InternalFrameMasDetalle.getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 120, 30));

        equipoProducto1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        InternalFrameMasDetalle.getContentPane().add(equipoProducto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 290, 30));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Marca:");
        InternalFrameMasDetalle.getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, 80, 30));

        equipoMarca1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        InternalFrameMasDetalle.getContentPane().add(equipoMarca1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, 310, 30));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 153, 153));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("LISTADO DE PERSONAS ASIGNADAS AL EQUIPO DE LIQUIDACIÓN");
        jLabel30.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameMasDetalle.getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 1180, 30));
        InternalFrameMasDetalle.getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 1180, 10));
        InternalFrameMasDetalle.getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 1180, 10));

        add(InternalFrameMasDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 550));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("REGISTRO DE EQUIPO PARA LIQUIDACIÓN");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 520, 30));

        btn_Actualizar.setBackground(new java.awt.Color(255, 255, 255));
        btn_Actualizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_Actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/registrar.png"))); // NOI18N
        btn_Actualizar.setText("REGISTRAR");
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
        add(btn_Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 350, 140, 40));
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, 580, 40));

        consultar4.setBackground(new java.awt.Color(255, 255, 255));
        consultar4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consultar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        consultar4.setText("CONSULTAR");
        consultar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultar4ActionPerformed(evt);
            }
        });
        add(consultar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 60, 140, -1));

        btn_cancelar.setBackground(new java.awt.Color(255, 255, 255));
        btn_cancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btn_cancelar.setText("CANCELAR");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 350, 140, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Código Barra:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 120, 30));

        equipoMarca.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, 240, 30));

        equipoTipoEquipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipoTipoEquipo.setToolTipText("");
        add(equipoTipoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 220, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel7.setText("CARGAR EQUIPO");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 90, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Equipo Referencia:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 120, 30));

        equipoProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 220, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Capacidad:");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, 80, 30));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Producto:");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 120, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Tipo Equipo:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 120, 30));

        equipoCodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 220, 30));

        equipoCodigoBarra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoCodigoBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 220, 30));

        equipoReferencia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoReferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 220, 30));

        equipoCapacidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, 240, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Marca:");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, 80, 30));

        equipoModelo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, 240, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Modelo:");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 80, 30));

        equipoSerial.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoSerial, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 170, 240, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Serial:");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, 80, 30));

        equipoDescripcion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 240, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Descripción:");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 210, 80, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/agregar.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 70, 60));

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
        tabla1.setComponentPopupMenu(Seleccionar1);
        jScrollPane1.setViewportView(tabla1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 100, 740, 400));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("LISTADO DE EQUIPOS PROGRAMADOS PARA LIQUIDAR");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, 750, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Código:");
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 120, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, 750, 490));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 670, 10));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 700, 490));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        equipo= null;
        equipoCodigo.setText("");
        equipoCodigoBarra.setText("");
        equipoReferencia.setText("");
        equipoProducto.setText("");
        equipoCapacidad.setText("");
        equipoMarca.setText("");
        equipoModelo.setText("");
        equipoSerial.setText("");
        equipoDescripcion.setText("");
        equipoTipoEquipo.setSelectedIndex(0);
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ActualizarActionPerformed
        if(equipoCodigo.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Error!, debe de cargar un equipo para proceder con el registro", "Error!", JOptionPane.ERROR_MESSAGE);
        }else{
            EquipoLiquidacion equipoLiquidacion = new EquipoLiquidacion();
            equipoLiquidacion.setEquipo(equipo);
            equipoLiquidacion.setEstado("1");
            try {
                int result = new ControlDB_EquipoLiquidacion(tipoConexion).registrar(equipoLiquidacion, user);
                if(result==1){
                    JOptionPane.showMessageDialog(null, "Se registro el equipo de forma exitosa", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                    equipo= null;
                    equipoCodigo.setText("");
                    equipoCodigoBarra.setText("");
                    equipoReferencia.setText("");
                    equipoProducto.setText("");
                    equipoCapacidad.setText("");
                    equipoMarca.setText("");
                    equipoModelo.setText("");
                    equipoSerial.setText("");
                    equipoDescripcion.setText("");
                    equipoTipoEquipo.setSelectedIndex(0);
                    try {
                        tabla_Listar2("");
                    } catch (SQLException ex) {
                        Logger.getLogger(EquipoLiquidacion_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    if(result==3){
                        JOptionPane.showMessageDialog(null, "El equipo de liquidación a registrar ya se encuentra registrado en el sistema, valide informaicón", "Error!!.",JOptionPane.ERROR_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "No se puedo registrar el equipo para liquidación");
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EquipoLiquidacion_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(EquipoLiquidacion_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SocketException ex) {
                Logger.getLogger(EquipoLiquidacion_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }//GEN-LAST:event_btn_ActualizarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        int fila1;
        try {
            fila1 = tabla2.getSelectedRow();
            if (fila1 == -1) {
                JOptionPane.showMessageDialog(null, "no se ha seleccionando ningun Vehiculo");
            } else {
                equipo=null;
                equipo = listado.get(fila1);
                equipoCodigo.setText(equipo.getCodigo());
                equipoTipoEquipo.setSelectedItem(equipo.getDescripcion());
                equipoCodigoBarra.setText(equipo.getCodigo_barra());
                equipoReferencia.setText(equipo.getReferencia());
                equipoProducto.setText(equipo.getProducto());
                equipoCapacidad.setText(equipo.getCapacidad());
                equipoMarca.setText(equipo.getMarca());
                equipoModelo.setText(equipo.getModelo());
                equipoSerial.setText(equipo.getSerial());
                equipoDescripcion.setText(equipo.getDescripcion());
                InternalFrameConsultarEquipos.show(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar el equipo", "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void btn_ActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ActualizarMouseExited

    }//GEN-LAST:event_btn_ActualizarMouseExited

    private void todosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_todosStateChanged
        if (todos.isSelected()) {
            tipo.setSelected(false);
            marca.setSelected(false);
            especifico.setSelected(false);
            estado1.setSelected(false);
        } else {
        }
    }//GEN-LAST:event_todosStateChanged

    private void tipoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tipoStateChanged
        if (tipo.isSelected() || marca.isSelected() || especifico.isSelected() || estado1.isSelected()) {
            todos.setSelected(false);
        }
    }//GEN-LAST:event_tipoStateChanged

    private void marcaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_marcaStateChanged
        if (tipo.isSelected() || marca.isSelected() || especifico.isSelected() || estado1.isSelected()) {
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
        if (tipo.isSelected() || marca.isSelected() || especifico.isSelected() || estado1.isSelected()) {
            todos.setSelected(false);
        }
    }//GEN-LAST:event_especificoStateChanged

    private void estado1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_estado1StateChanged
        if (tipo.isSelected() || marca.isSelected() || especifico.isSelected() || estado1.isSelected()) {
            todos.setSelected(false);
        }
    }//GEN-LAST:event_estado1StateChanged

    private void btn_consultar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultar_clienteActionPerformed
        try {
            generarListadoEquipo();
        } catch (SQLException ex) {
            Logger.getLogger(EquipoLiquidacion_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_consultar_clienteActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        InternalFrameConsultarEquipos.show(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void consultar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultar4ActionPerformed
        try {
            tabla_Listar2(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(EquipoLiquidacion_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_consultar4ActionPerformed

    private void tabla2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla2MouseClicked
        if (evt.getClickCount() == 2) {
            int fila1;
            try {
                fila1 = tabla2.getSelectedRow();
                if (fila1 == -1) {
                    JOptionPane.showMessageDialog(null, "no se ha seleccionando ningun Vehiculo");
                } else {
                    equipo=null;
                    equipo = listado.get(fila1);
                    equipoCodigo.setText(equipo.getCodigo());
                    equipoTipoEquipo.setSelectedItem(equipo.getDescripcion());
                    equipoCodigoBarra.setText(equipo.getCodigo_barra());
                    equipoReferencia.setText(equipo.getReferencia());
                    equipoProducto.setText(equipo.getProducto());
                    equipoCapacidad.setText(equipo.getCapacidad());
                    equipoMarca.setText(equipo.getMarca());
                    equipoModelo.setText(equipo.getModelo());
                    equipoSerial.setText(equipo.getSerial());
                    equipoDescripcion.setText(equipo.getDescripcion());
                    InternalFrameConsultarEquipos.show(false);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo cargar el equipo", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }  
        }
    }//GEN-LAST:event_tabla2MouseClicked

    private void tabla3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla3MouseClicked

    }//GEN-LAST:event_tabla3MouseClicked

    private void Editar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Editar1ActionPerformed
        int fila1;
        try {
            fila1 = tabla1.getSelectedRow();
            if (fila1 == -1) {
                JOptionPane.showMessageDialog(null, "no se ha seleccionando ningun Vehiculo");
            } else {

                EquipoLiquidacion equipoLiquidacion=null;
                equipoLiquidacion = listadoEquipoLiquidacion.get(fila1);
                equipoCodigo1.setText(equipoLiquidacion.getEquipo().getCodigo());
                equipoTipoEquipo1.setSelectedItem(equipoLiquidacion.getEquipo().getTipoEquipo().getDescripcion());
                equipoCodigoBarra1.setText(equipoLiquidacion.getEquipo().getCodigo_barra());
                equipoReferencia1.setText(equipoLiquidacion.getEquipo().getReferencia());
                equipoProducto1.setText(equipoLiquidacion.getEquipo().getProducto());
                equipoCapacidad1.setText(equipoLiquidacion.getEquipo().getCapacidad());
                equipoMarca1.setText(equipoLiquidacion.getEquipo().getMarca());
                equipoModelo1.setText(equipoLiquidacion.getEquipo().getModelo());
                equipoSerial1.setText(equipoLiquidacion.getEquipo().getSerial());
                equipoDescripcion1.setText(equipoLiquidacion.getEquipo().getDescripcion());
                InternalFrameMasDetalle.show(true);

                //Hacemos una consulta para saber que personal está asociado este equipo para tema de liquidación
                //como primera opción vamos a limpiar los datos que se encuentra en la tabla donde se carga el personal.
                DefaultTableModel md =(DefaultTableModel)tabla2.getModel();
                int CantEliminar= tabla2.getRowCount() -1;
                for(int i =CantEliminar; i>=0; i--){
                    md.removeRow(i);
                }
                //como segunda opción vamos a validar que personas se encuentran asociada al equipo que estamos cargando en la interfaz.
                tabla_BuscarPersonasPorEquipoLiquidacion(equipoLiquidacion);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar el equipo", "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_Editar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JMenuItem Editar1;
    private javax.swing.JInternalFrame InternalFrameConsultarEquipos;
    private javax.swing.JInternalFrame InternalFrameMasDetalle;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JPopupMenu Seleccionar1;
    private javax.swing.JButton btn_Actualizar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_consultar_cliente;
    private javax.swing.JButton consultar4;
    private javax.swing.JTextField equipoCapacidad;
    private javax.swing.JTextField equipoCapacidad1;
    private javax.swing.JTextField equipoCodigo;
    private javax.swing.JTextField equipoCodigo1;
    private javax.swing.JTextField equipoCodigoBarra;
    private javax.swing.JTextField equipoCodigoBarra1;
    private javax.swing.JTextField equipoDescripcion;
    private javax.swing.JTextField equipoDescripcion1;
    private javax.swing.JTextField equipoMarca;
    private javax.swing.JTextField equipoMarca1;
    private javax.swing.JTextField equipoModelo;
    private javax.swing.JTextField equipoModelo1;
    private javax.swing.JTextField equipoProducto;
    private javax.swing.JTextField equipoProducto1;
    private javax.swing.JTextField equipoReferencia;
    private javax.swing.JTextField equipoReferencia1;
    private javax.swing.JTextField equipoSerial;
    private javax.swing.JTextField equipoSerial1;
    private javax.swing.JComboBox<String> equipoTipoEquipo;
    private javax.swing.JComboBox<String> equipoTipoEquipo1;
    private javax.swing.JRadioButton especifico;
    private javax.swing.JRadioButton estado1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JRadioButton marca;
    private javax.swing.JComboBox<String> selectEstado;
    private javax.swing.JComboBox<String> selectMarca;
    private javax.swing.JComboBox<String> selectTipo;
    private javax.swing.JTable tabla1;
    private javax.swing.JTable tabla2;
    private javax.swing.JTable tabla3;
    private javax.swing.JRadioButton tipo;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel titulo1;
    private javax.swing.JRadioButton todos;
    private javax.swing.JTextField valorBusqueda;
    private javax.swing.JTextField valorBusquedaEspecifica;
    // End of variables declaration//GEN-END:variables
   public void generarListadoEquipo() throws SQLException {
        String sql = "";
        int contador = 0;
        if (todos.isSelected()) {
            try {
                tabla_Listar(sql, "");
            } catch (SQLException ex) {
                Logger.getLogger(EquipoLiquidacion_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (tipo.isSelected()) {
                if (contador == 0) {
                    sql += " WHERE [te_desc] LIKE '" + selectTipo.getSelectedItem().toString() + "'";
                    contador++;
                } else {
                    sql += " AND [te_desc] LIKE '" + selectTipo.getSelectedItem().toString() + "'";
                    contador++;
                }
            }
            if (marca.isSelected()) {
                if (contador == 0) {
                    sql += " WHERE [eq_marca] LIKE '" + selectMarca.getSelectedItem().toString() + "'";
                    contador++;
                } else {
                    sql += " AND [eq_marca] LIKE '" + selectMarca.getSelectedItem().toString() + "'";
                    contador++;
                }
            }
            if (estado1.isSelected()) {
                if (contador == 0) {
                    if (selectEstado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")) {
                        sql += " WHERE [eq_estad]=1 ";
                    } else {
                        if (selectEstado.getSelectedItem().toString().equalsIgnoreCase("INACTIVO")) {
                            sql += " WHERE [eq_estad]=0 ";
                        }
                    }
                    contador++;
                } else {
                    if (selectEstado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")) {
                        sql += " AND [eq_estad]=1 ";
                    } else {
                        if (selectEstado.getSelectedItem().toString().equalsIgnoreCase("INACTIVO")) {
                            sql += " AND [eq_estad]=0 ";
                        }
                    }
                    contador++;
                }
            }
            String busquedaEspecifica = "";
            if (especifico.isSelected()) {
                if (contador == 0) {
                    sql += " WHERE [eq_desc] LIKE ?";
                    busquedaEspecifica = "%" + valorBusquedaEspecifica.getText() + "%";
                    contador++;
                } else {
                    sql += " AND [eq_desc] LIKE ?";
                    busquedaEspecifica = "%" + valorBusquedaEspecifica.getText() + "%";
                    contador++;
                }
            }
            try {
                tabla_Listar(sql, busquedaEspecifica);
            } catch (SQLException ex) {
                Logger.getLogger(EquipoLiquidacion_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void tabla_Listar(String sql, String busquedaEspecifica) throws SQLException {
        DefaultTableModel modeloX = new DefaultTableModel(null, new String[]{"Equipo_Código","Equipo_CódigoBarra", "Equipo_Referencia","Equipo_Producto",
        "Equipo_Capacidad","Equipo_Marca","Equipo_Modelo","Equipo_Serial","Equipo_Descripción","TipoEquipo_Código","TipoEquipo_Nombre","TipoEquipo_Estado"});
        listado = new ControlDB_Equipo(tipoConexion).buscarEquiposEnAplicacionInterna(sql, busquedaEspecifica);
        if(listado != null){
            for (Equipo listado1 : listado) {
                String[] registro = new String[12];
                System.out.println(listado1.getCodigo());
                registro[0] = "" + listado1.getCodigo();
                registro[1] = "" + listado1.getCodigo_barra();
                registro[2] = "" + listado1.getReferencia();
                registro[3] = "" + listado1.getProducto();
                registro[4] = "" + listado1.getCapacidad();
                registro[5] = "" + listado1.getMarca();
                registro[6] = "" + listado1.getModelo();
                registro[7] = "" + listado1.getSerial();
                registro[8] = "" + listado1.getDescripcion();
                registro[9] = "" + listado1.getTipoEquipo().getCodigo();
                registro[10] = "" + listado1.getTipoEquipo().getDescripcion();
                if (listado1.getTipoEquipo().getEstado().equals("1")) {
                    registro[11] = "ACTIVO";
                } else {
                    if (listado1.getTipoEquipo().getEstado().equals("0")) {
                        registro[11] = "INACTIVO";
                    } else {
                        registro[11] = "" + listado1.getTipoEquipo().getEstado();
                    }
                };
                modeloX.addRow(registro);
            }
            tabla2.setModel(modeloX);
        }
    }
    public void tabla_Listar2(String valorConsulta) throws SQLException{
        DefaultTableModel modelo2 = new DefaultTableModel(null, new String[]{"Código","Código_Equipo", "Descripción_Equipo","Estado"});  
        listadoEquipoLiquidacion=new ControlDB_EquipoLiquidacion(tipoConexion).buscar(valorConsulta);
        if(listadoEquipoLiquidacion != null){
            for (EquipoLiquidacion listado1 : listadoEquipoLiquidacion) {
                String[] registro = new String[4];
                
                registro[0] = "" + listado1.getCodigo();
                registro[1] = "" + listado1.getEquipo().getCodigo();
                registro[2] = "" + listado1.getEquipo().getDescripcion()+" "+listado1.getEquipo().getModelo();
                registro[3] = "" + listado1.getEstado();
                modelo2.addRow(registro);      
            }
            tabla1.setModel(modelo2);
        }
        
    }
    public void tabla_BuscarPersonasPorEquipoLiquidacion(EquipoLiquidacion equipoLiquidacion) throws SQLException {
        //como primera opción vamos a limpiar los datos que se encuentra en la tabla
        DefaultTableModel md =(DefaultTableModel)tabla3.getModel();
        int CantEliminar= tabla3.getRowCount() -1;
        for(int i =CantEliminar; i>=0; i--){
                md.removeRow(i);
        }      
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"TipoDocumento", "Número", "Nombre", "Telefono", "CargoNomina", "TipoContrato", "Compañia", "Equipo", "Estado"});
        ArrayList<Persona> listadoPersona = new ControlDB_EquipoLiquidacion(tipoConexion).consultarPersonasPorEquipoLiquidacion(equipoLiquidacion);
        if(listadoPersona != null){
            for (Persona objeto : listadoPersona) {
                String[] registro = new String[9];
                registro[0] = "" + objeto.getTipoDocumento().getDescripcion();
                registro[1] = "" + objeto.getCodigo();
                registro[2] = "" + objeto.getNombre() + " " + objeto.getApellido();
                registro[3] = "" + objeto.getTelefono();
                registro[4] = "" + objeto.getCargoNomina().getDescripcion();
                registro[5] = "" + objeto.getTipoContrato().getDescripcion();
                registro[6] = "" + objeto.getCompania().getDescripcion();
                registro[7] = "" + objeto.getEquipo().getDescripcion() + " " + objeto.getEquipo().getModelo();
                registro[8] = "" + objeto.getEstado();
                modelo.addRow(registro);
            }
            tabla3.setModel(modelo);
        }   
    }

}
