package ModuloPersonal.View;

import Catalogo.Controller.ControlDB_Compañia;
import Catalogo.Controller.ControlDB_Equipo;
import Catalogo.Model.Cliente;
import Catalogo.Model.Compañia;
import Catalogo.Model.Equipo;
import ModuloEquipo.View.AsignacionEquipo_Registrar;
import ModuloEquipo.View.Solicitud_Equipos_Registrar;
import ModuloPersonal.Controller.ControlDB_CargoNomina;
import ModuloPersonal.Controller.ControlDB_Persona;
import ModuloPersonal.Controller.ControlDB_TipoContrato;
import ModuloPersonal.Controller.ControlDB_TipoDocumento;
import ModuloPersonal.Model.CargoNomina;
import ModuloPersonal.Model.Persona;
import ModuloPersonal.Model.TipoContrato;
import ModuloPersonal.Model.TipoDocumento;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class Persona_Actualizar extends javax.swing.JPanel {
    private Usuario user;
    private String tipoConexion;
    private ArrayList<Persona> listado;
    private ArrayList<TipoDocumento> listadoTipoDocumento;
    private ArrayList<CargoNomina> listadoCargoNomina;
    private ArrayList<TipoContrato> listadoTipoContratos;
    private ArrayList<Compañia> listadoCompanias;
    private ArrayList<Cliente> listadoCliente=null;
    private Equipo equipo;
    private ArrayList<String> listadoMarcaEquipo =new ArrayList<>();
    private ArrayList<String> listadoTiposEquipo = new ArrayList();
    private ArrayList<String> listadoModelosEquipo = new ArrayList();
    private ArrayList<Equipo> listadoEquipo = new ArrayList();
    private Persona persona;
    public Persona_Actualizar(Usuario us, String tipoConexion) { 
        initComponents();
        user=us;
        this.tipoConexion= tipoConexion;
        equipo=null;
        persona=null;
        listado=null;
        
        //Cargamos en la interfaz los tipos de documentos
        try {
            listadoTipoDocumento=new ControlDB_TipoDocumento(tipoConexion).buscarActivos();
            for(TipoDocumento objeto: listadoTipoDocumento){
                select_tipoDocumento.addItem(objeto.getDescripcion());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Persona_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Cargamos en la interfaz los tipo de cargos según nomina
        try {
            listadoCargoNomina=new ControlDB_CargoNomina(tipoConexion).buscarActivos();
            for(CargoNomina objeto: listadoCargoNomina){
                select_cargoNomina.addItem(objeto.getDescripcion());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Persona_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
        //Cargamos en la interfaz los tipos de contratos
        try {
            listadoTipoContratos=new ControlDB_TipoContrato(tipoConexion).buscarActivos();
            for(TipoContrato objeto: listadoTipoContratos){
                select_tipoContrato.addItem(objeto.getDescripcion());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Persona_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Cargamos en la interfaz las compañias activas
        try {
            listadoCompanias=new ControlDB_Compañia(tipoConexion).buscarActivos();
            for(Compañia objeto: listadoCompanias){
                select_Compania.addItem(objeto.getDescripcion());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Persona_Registrar.class.getName()).log(Level.SEVERE, null, ex);
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
        InternaFrame_buscarEquipo.getContentPane().setBackground(Color.WHITE);
        InternaFrame_buscarEquipo.show(false);
        try {
            tabla_Listar("");
            resizeColumnWidth(tabla);
        } catch (SQLException ex) {
            Logger.getLogger(Persona_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        numDocumento.setEnabled(false);
        select_tipoDocumento.setEnabled(false);
        
        nombres.setEnabled(false);
        apellidos.setEnabled(false);
        telefono.setEnabled(false);
        select_cargoNomina.setEnabled(false);
        select_tipoContrato.setEnabled(false);
        select_Compania.setEnabled(false);
        icon_buscarEquipo.show(false);
        icon_borrarEquipo.show(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        seleccionar1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        InternaFrame_buscarEquipo = new javax.swing.JInternalFrame();
        jLabel15 = new javax.swing.JLabel();
        selectTipo = new javax.swing.JComboBox<>();
        titulo57 = new javax.swing.JLabel();
        titulo60 = new javax.swing.JLabel();
        selectMarca = new javax.swing.JComboBox<>();
        titulo64 = new javax.swing.JLabel();
        selectModelo = new javax.swing.JComboBox<>();
        titulo58 = new javax.swing.JLabel();
        selectEquipos = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        icon_borrarEquipo = new javax.swing.JLabel();
        select_cargoNomina = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        valorBusqueda = new javax.swing.JTextField();
        numDocumento = new javax.swing.JTextField();
        select_tipoDocumento = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nombres = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        select_tipoContrato = new javax.swing.JComboBox<>();
        apellidos = new javax.swing.JTextField();
        estado = new javax.swing.JComboBox<>();
        alerta_nombre = new javax.swing.JLabel();
        icon_buscarEquipo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        label_equipoTrabajo = new javax.swing.JLabel();
        btnCancelar1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        telefono = new javax.swing.JTextField();
        select_Compania = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        jMenuItem1.setText("seleccionar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        seleccionar1.add(jMenuItem1);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InternaFrame_buscarEquipo.setBackground(new java.awt.Color(255, 255, 255));
        InternaFrame_buscarEquipo.setClosable(true);
        InternaFrame_buscarEquipo.setVisible(false);
        InternaFrame_buscarEquipo.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 102));
        jLabel15.setText("SELECCIONAR EQUIPO DE TRABAJO.");
        InternaFrame_buscarEquipo.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 380, 40));

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
        InternaFrame_buscarEquipo.getContentPane().add(selectTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 450, 30));

        titulo57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo57.setForeground(new java.awt.Color(51, 51, 51));
        titulo57.setText("TIPO DE EQUIPO:");
        InternaFrame_buscarEquipo.getContentPane().add(titulo57, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 120, 30));

        titulo60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo60.setForeground(new java.awt.Color(51, 51, 51));
        titulo60.setText("MARCA:");
        InternaFrame_buscarEquipo.getContentPane().add(titulo60, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 120, 30));

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
        InternaFrame_buscarEquipo.getContentPane().add(selectMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 450, 30));

        titulo64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo64.setForeground(new java.awt.Color(51, 51, 51));
        titulo64.setText("MODELO:");
        InternaFrame_buscarEquipo.getContentPane().add(titulo64, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 120, 30));

        selectModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectModeloActionPerformed(evt);
            }
        });
        InternaFrame_buscarEquipo.getContentPane().add(selectModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 450, 30));

        titulo58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo58.setForeground(new java.awt.Color(51, 51, 51));
        titulo58.setText("EQUIPO:");
        InternaFrame_buscarEquipo.getContentPane().add(titulo58, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 120, 30));

        selectEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectEquiposActionPerformed(evt);
            }
        });
        InternaFrame_buscarEquipo.getContentPane().add(selectEquipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 450, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Ingresar.png"))); // NOI18N
        jButton1.setText("SELECCIONAR EQUIPO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        InternaFrame_buscarEquipo.getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, 210, 40));

        add(InternaFrame_buscarEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 660));

        btnRegistrar.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Ingresar.png"))); // NOI18N
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 560, 160, 40));

        icon_borrarEquipo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        icon_borrarEquipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/limpiar.png"))); // NOI18N
        icon_borrarEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_borrarEquipoMouseClicked(evt);
            }
        });
        add(icon_borrarEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 430, 30, 30));

        select_cargoNomina.setToolTipText("");
        add(select_cargoNomina, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 350, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Teléfono:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 130, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Equipo de Trabajo:");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 150, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Apellidos:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 130, 30));

        valorBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorBusquedaActionPerformed(evt);
            }
        });
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 860, 40));

        numDocumento.setName(""); // NOI18N
        numDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numDocumentoActionPerformed(evt);
            }
        });
        add(numDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 350, 30));

        select_tipoDocumento.setToolTipText("");
        add(select_tipoDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 350, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Tipo Contrato:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 150, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Número Documento:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 160, 30));

        nombres.setName(""); // NOI18N
        nombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombresActionPerformed(evt);
            }
        });
        add(nombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 350, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Estado:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 150, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Tipo Documento:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 130, 30));

        select_tipoContrato.setToolTipText("");
        add(select_tipoContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 350, 30));

        apellidos.setName(""); // NOI18N
        apellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidosActionPerformed(evt);
            }
        });
        add(apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 350, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 470, 350, 30));

        alerta_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_nombre.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 380, 20));

        icon_buscarEquipo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        icon_buscarEquipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        icon_buscarEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_buscarEquipoMouseClicked(evt);
            }
        });
        add(icon_buscarEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, 30, 30));

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
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla.setComponentPopupMenu(seleccionar1);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 1040, 510));

        label_equipoTrabajo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(label_equipoTrabajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 430, 310, 30));

        btnCancelar1.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btnCancelar1.setText("CANCELAR");
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });
        add(btnCancelar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 560, 170, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Compañia:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 150, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Nombres:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 130, 30));

        telefono.setName(""); // NOI18N
        telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonoActionPerformed(evt);
            }
        });
        add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 350, 30));

        select_Compania.setToolTipText("");
        add(select_Compania, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 390, 350, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Cargo Nomina:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 150, 30));

        btnConsultar.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        btnConsultar.setText("CONSULTAR");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 60, 170, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ACTUALIZACIÓN DE PERSONA");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1590, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1590, 90));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 550, 510));
    }// </editor-fold>//GEN-END:initComponents
   
    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() == 2) {
            try{
                int fila1=tabla.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    if(listado != null){
                        try{
                            persona=listado.get(fila1);
                            numDocumento.setText(persona.getCodigo());
                            nombres.setText(persona.getNombre());
                            apellidos.setText(persona.getApellido());
                            telefono.setText(persona.getTelefono());
                            int contador=0;
                            for(TipoDocumento objeto: listadoTipoDocumento){
                                if(persona.getTipoDocumento().getCodigo().equals(objeto.getCodigo())){
                                    select_tipoDocumento.setSelectedIndex(contador);
                                }
                                contador++;
                            }
                            contador=0;
                            for(CargoNomina objeto: listadoCargoNomina){
                                if(persona.getCargoNomina().getCodigo().equals(objeto.getCodigo())){
                                    select_cargoNomina.setSelectedIndex(contador);
                                }
                                contador++;
                            }
                            contador=0;
                            for(TipoContrato objeto: listadoTipoContratos){
                                if(persona.getTipoContrato().getCodigo().equals(objeto.getCodigo())){
                                    select_tipoContrato.setSelectedIndex(contador);
                                }
                                contador++;
                            }
                            contador=0;
                            for(Compañia objeto: listadoCompanias){
                                if(persona.getCompania().getCodigo().equals(objeto.getCodigo())){
                                    select_Compania.setSelectedIndex(contador);
                                }
                                contador++;
                            }
                            if(persona.getEquipo().getDescripcion() != null){
                                equipo=persona.getEquipo();
                                label_equipoTrabajo.setText(persona.getEquipo().getDescripcion()+" "+persona.getEquipo().getModelo());
                            }else{
                                equipo=null;
                                label_equipoTrabajo.setText("");
                            }
                            if(persona.getEstado().equals("ACTIVO")){
                                estado.setSelectedIndex(0);
                            }else{
                                estado.setSelectedIndex(1);
                            }
                            contador=0;
                            for(String data: listadoTiposEquipo){
                                if(data.equals(persona.getEquipo().getTipoEquipo().getDescripcion())){
                                    selectTipo.setSelectedIndex(contador);
                                }
                                contador++;
                            }
                            contador=0;
                            for(String data: listadoMarcaEquipo){
                                if(data.equals(persona.getEquipo().getMarca())){
                                    selectMarca.setSelectedIndex(contador);
                                }
                                contador++;
                            }
                            contador=0;
                            for(String data: listadoModelosEquipo){
                                if(data.equals(persona.getEquipo().getModelo())){
                                    selectModelo.setSelectedIndex(contador);
                                }
                                contador++;
                            }
                            contador=0;
                            for(Equipo data: listadoEquipo){
                                String data1=data.getCodigo()+" "+data.getDescripcion()+ " "+data.getModelo();
                                if(data1.equals(persona.getEquipo().getCodigo()+" "+persona.getEquipo().getDescripcion()+ " "+persona.getEquipo().getModelo())){
                                    selectEquipos.setSelectedIndex(contador);
                                }
                                contador++;
                            }
                            nombres.setEnabled(true);
                            apellidos.setEnabled(true);
                            telefono.setEnabled(true);
                            select_cargoNomina.setEnabled(true);
                            select_tipoContrato.setEnabled(true);
                            select_Compania.setEnabled(true);
                            icon_buscarEquipo.show(true);
                            icon_borrarEquipo.show(true); 
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(null, "No se pudo cargar la persona", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try{
            int fila1=tabla.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                if(listado != null){
                    try{
                        persona=listado.get(fila1);
                        numDocumento.setText(persona.getCodigo());
                        nombres.setText(persona.getNombre());
                        apellidos.setText(persona.getApellido());
                        telefono.setText(persona.getTelefono());
                        int contador=0;
                        for(TipoDocumento objeto: listadoTipoDocumento){
                            if(persona.getTipoDocumento().getCodigo().equals(objeto.getCodigo())){
                                select_tipoDocumento.setSelectedIndex(contador);
                            }
                            contador++;
                        }
                        contador=0;
                        for(CargoNomina objeto: listadoCargoNomina){
                            if(persona.getCargoNomina().getCodigo().equals(objeto.getCodigo())){
                                select_cargoNomina.setSelectedIndex(contador);
                            }
                            contador++;
                        }
                        contador=0;
                        for(TipoContrato objeto: listadoTipoContratos){
                            if(persona.getTipoContrato().getCodigo().equals(objeto.getCodigo())){
                                select_tipoContrato.setSelectedIndex(contador);
                            }
                            contador++;
                        }
                        contador=0;
                        for(Compañia objeto: listadoCompanias){
                            if(persona.getCompania().getCodigo().equals(objeto.getCodigo())){
                                select_Compania.setSelectedIndex(contador);
                            }
                            contador++;
                        }
                        if(persona.getEquipo().getDescripcion() != null){
                            equipo=persona.getEquipo();
                            label_equipoTrabajo.setText(persona.getEquipo().getDescripcion()+" "+persona.getEquipo().getModelo());
                        }else{
                            equipo=null;
                            label_equipoTrabajo.setText("");
                        }
                        if(persona.getEstado().equals("ACTIVO")){
                            estado.setSelectedIndex(0);
                        }else{
                            estado.setSelectedIndex(1);
                        }
                        contador=0;
                        for(String data: listadoTiposEquipo){
                            if(data.equals(persona.getEquipo().getTipoEquipo().getDescripcion())){
                                selectTipo.setSelectedIndex(contador);
                            }
                            contador++;
                        }
                        contador=0;
                        for(String data: listadoMarcaEquipo){
                            if(data.equals(persona.getEquipo().getMarca())){
                                selectMarca.setSelectedIndex(contador);
                            }
                            contador++;
                        }
                        contador=0;
                        for(String data: listadoModelosEquipo){
                            if(data.equals(persona.getEquipo().getModelo())){
                                selectModelo.setSelectedIndex(contador);
                            }
                            contador++;
                        }
                        contador=0;
                        for(Equipo data: listadoEquipo){
                            String data1=data.getCodigo()+" "+data.getDescripcion()+ " "+data.getModelo();
                            if(data1.equals(persona.getEquipo().getCodigo()+" "+persona.getEquipo().getDescripcion()+ " "+persona.getEquipo().getModelo())){
                                selectEquipos.setSelectedIndex(contador);
                            }
                            contador++;
                        }
                        nombres.setEnabled(true);
                        apellidos.setEnabled(true);
                        telefono.setEnabled(true);
                        select_cargoNomina.setEnabled(true);
                        select_tipoContrato.setEnabled(true);
                        select_Compania.setEnabled(true);
                        icon_buscarEquipo.show(true);
                        icon_borrarEquipo.show(true); 
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null, "No se pudo cargar la persona", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void valorBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valorBusquedaActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        try {
            tabla_Listar(valorBusqueda.getText());
            resizeColumnWidth(tabla);
        } catch (SQLException ex) {
            Logger.getLogger(SituacionMedica_Consultar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void numDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numDocumentoActionPerformed

    private void nombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombresActionPerformed

    private void apellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidosActionPerformed

    private void telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonoActionPerformed

    private void icon_buscarEquipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_buscarEquipoMouseClicked
        InternaFrame_buscarEquipo.show(true);
    }//GEN-LAST:event_icon_buscarEquipoMouseClicked

    private void icon_borrarEquipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_borrarEquipoMouseClicked
        equipo=null;
        label_equipoTrabajo.setText("");
    }//GEN-LAST:event_icon_borrarEquipoMouseClicked

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if (nombres.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El nombre de la persona a registrar no puede estar vacio", "ERROR", JOptionPane.ERROR_MESSAGE);
        }else{
            if(apellidos.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Los apellidos de la persona a registrar no puede estar vacio", "ERROR", JOptionPane.ERROR_MESSAGE);
            }else {
                if(listadoCargoNomina == null){
                    JOptionPane.showMessageDialog(null, "No existen cargos de nomina activos en el sistema", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else{
                    if(listadoTipoContratos == null){
                        JOptionPane.showMessageDialog(null, "No existen tipos de contratos activos en el sistema", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }else{
                        if(listadoCompanias == null){
                            JOptionPane.showMessageDialog(null, "No existen compañías activas en el sistema", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }else{
                            persona.setNombre(nombres.getText());
                            persona.setApellido(apellidos.getText());
                            persona.setTelefono(telefono.getText());
                            persona.setCargoNomina(listadoCargoNomina.get(select_cargoNomina.getSelectedIndex()));
                            persona.setTipoContrato(listadoTipoContratos.get(select_tipoContrato.getSelectedIndex()));
                            persona.setCompania(listadoCompanias.get(select_Compania.getSelectedIndex()));
                            if(equipo != null){
                                persona.setEquipo(equipo);
                            }else{
                                persona.setEquipo(null);
                            }
                            if(estado.getSelectedIndex()==0){
                                persona.setEstado("1");
                            }else{
                                persona.setEstado("0");           
                            }
                            try {
                                int respuesta = new ControlDB_Persona(tipoConexion).actualizar(persona, user);
                                if (respuesta == 1) {
                                    JOptionPane.showMessageDialog(null, "Se actualizó el registro de forma Exitosa");
                                    equipo=null;
                                    numDocumento.setText("");
                                    nombres.setText("");
                                    apellidos.setText("");
                                    telefono.setText("");
                                    label_equipoTrabajo.setText("");
                                    select_tipoDocumento.setSelectedIndex(0);
                                    select_cargoNomina.setSelectedIndex(0);
                                    select_tipoContrato.setSelectedIndex(0);
                                    select_Compania.setSelectedIndex(0);
                                    estado.setSelectedIndex(0);
                                    numDocumento.setEnabled(false);
                                    select_tipoDocumento.setEnabled(false);

                                    nombres.setEnabled(false);
                                    apellidos.setEnabled(false);
                                    telefono.setEnabled(false);
                                    select_cargoNomina.setEnabled(false);
                                    select_tipoContrato.setEnabled(false);
                                    select_Compania.setEnabled(false);
                                    icon_buscarEquipo.show(false);
                                    icon_borrarEquipo.show(false);
                                    try {
                                        tabla_Listar(valorBusqueda.getText());
                                        resizeColumnWidth(tabla);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(Persona_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    if (respuesta == 0) {
                                        JOptionPane.showMessageDialog(null, "No se pudo actualizar la persona, valide datos");
                                    }
                                } 
                            } catch (FileNotFoundException ex) {
                                JOptionPane.showMessageDialog(null, "Error al registrar la persona");
                                Logger.getLogger(Persona_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (UnknownHostException ex) {
                                Logger.getLogger(Persona_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SocketException ex) {
                                Logger.getLogger(Persona_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        }  
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        equipo=null;
        numDocumento.setText("");
        nombres.setText("");
        apellidos.setText("");
        telefono.setText("");
        label_equipoTrabajo.setText("");
        select_tipoDocumento.setSelectedIndex(0);
        select_cargoNomina.setSelectedIndex(0);
        select_tipoContrato.setSelectedIndex(0);
        select_Compania.setSelectedIndex(0);
        estado.setSelectedIndex(0);
        numDocumento.setEnabled(false);
        select_tipoDocumento.setEnabled(false);
        
        nombres.setEnabled(false);
        apellidos.setEnabled(false);
        telefono.setEnabled(false);
        select_cargoNomina.setEnabled(false);
        select_tipoContrato.setEnabled(false);
        select_Compania.setEnabled(false);
        icon_buscarEquipo.show(false);
        icon_borrarEquipo.show(false);
    }//GEN-LAST:event_btnCancelar1ActionPerformed

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
                    dataEquipo[i]=""+(listadoEquipo.get(i).getCodigo()+" "+listadoEquipo.get(i).getDescripcion()+ " "+ listadoEquipo.get(i).getModelo());
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
                        dataEquipo[i]=""+(listadoEquipo.get(i).getCodigo()+" "+listadoEquipo.get(i).getDescripcion()+ " "+ listadoEquipo.get(i).getModelo());
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
        if(listadoEquipo != null){
            equipo= listadoEquipo.get(selectEquipos.getSelectedIndex());
            label_equipoTrabajo.setText(equipo.getDescripcion()+" "+ equipo.getModelo());
            InternaFrame_buscarEquipo.show(false);
        }else{
            JOptionPane.showMessageDialog(null, "No se pudo carga el equipo de trabajo", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame InternaFrame_buscarEquipo;
    private javax.swing.JLabel alerta_nombre;
    private javax.swing.JTextField apellidos;
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel icon_borrarEquipo;
    private javax.swing.JLabel icon_buscarEquipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_equipoTrabajo;
    private javax.swing.JTextField nombres;
    private javax.swing.JTextField numDocumento;
    private javax.swing.JPopupMenu seleccionar1;
    private javax.swing.JComboBox<String> selectEquipos;
    private javax.swing.JComboBox<String> selectMarca;
    private javax.swing.JComboBox<String> selectModelo;
    private javax.swing.JComboBox<String> selectTipo;
    private javax.swing.JComboBox<String> select_Compania;
    private javax.swing.JComboBox<String> select_cargoNomina;
    private javax.swing.JComboBox<String> select_tipoContrato;
    private javax.swing.JComboBox<String> select_tipoDocumento;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField telefono;
    private javax.swing.JLabel titulo57;
    private javax.swing.JLabel titulo58;
    private javax.swing.JLabel titulo60;
    private javax.swing.JLabel titulo64;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"TipoDocumento", "Número", "Nombre", "Telefono", "CargoNomina", "TipoContrato", "Compañia", "Equipo", "Estado"});
        listado = new ControlDB_Persona(tipoConexion).buscar(valorConsulta);
        for (Persona objeto : listado) {
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
        tabla.setModel(modelo);
    }
    //Ajustar aNcho de las tablas de acuerdo al contenido
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

}
