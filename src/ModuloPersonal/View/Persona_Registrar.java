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

public class Persona_Registrar extends javax.swing.JPanel {
    private Usuario user;
    private String tipoConexion;
    private ArrayList<TipoDocumento> listadoTipoDocumento;
    private ArrayList<CargoNomina> listadoCargoNomina;
    private ArrayList<TipoContrato> listadoTipoContratos;
    private ArrayList<Compañia> listadoCompanias;
    private ArrayList<Equipo> listadoEquipos;
    private ArrayList<Cliente> listadoCliente=null;
    private Equipo equipo;
    private ArrayList<String> listadoMarcaEquipo =new ArrayList<>();
    private ArrayList<String> listadoTiposEquipo = new ArrayList();
    private ArrayList<String> listadoModelosEquipo = new ArrayList();
    private ArrayList<Equipo> listadoEquipo = new ArrayList();
    public Persona_Registrar(Usuario us,String tipoConexion) {
        initComponents();
        this.user = us;
        this.tipoConexion = tipoConexion;
        equipo=null;
        
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        telefono = new javax.swing.JTextField();
        label_equipoTrabajo = new javax.swing.JLabel();
        select_Compania = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        select_tipoDocumento = new javax.swing.JComboBox<>();
        select_cargoNomina = new javax.swing.JComboBox<>();
        select_tipoContrato = new javax.swing.JComboBox<>();
        numDocumento = new javax.swing.JTextField();
        nombres = new javax.swing.JTextField();
        apellidos = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        icon_buscarClientes1 = new javax.swing.JLabel();
        icon_buscarClientes = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        consultar = new javax.swing.JButton();
        valorBusqueda1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

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
        tabla.setName("tabla"); // NOI18N
        jScrollPane1.setViewportView(tabla);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, 700, 570));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 510, 170, 40));

        btnRegistrar.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Ingresar.png"))); // NOI18N
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 510, 160, 40));

        telefono.setName(""); // NOI18N
        telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonoActionPerformed(evt);
            }
        });
        add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 350, 30));

        label_equipoTrabajo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(label_equipoTrabajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 310, 30));

        select_Compania.setToolTipText("");
        add(select_Compania, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 350, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Teléfono:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 130, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Tipo Documento:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 130, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Nombres:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 130, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Apellidos:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 130, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Número Documento:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 160, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Cargo Nomina:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 150, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Tipo Contrato:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 150, 30));

        select_tipoDocumento.setToolTipText("");
        add(select_tipoDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 350, 30));

        select_cargoNomina.setToolTipText("");
        add(select_cargoNomina, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 350, 30));

        select_tipoContrato.setToolTipText("");
        add(select_tipoContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, 350, 30));

        numDocumento.setName(""); // NOI18N
        numDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numDocumentoActionPerformed(evt);
            }
        });
        add(numDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 350, 30));

        nombres.setName(""); // NOI18N
        nombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombresActionPerformed(evt);
            }
        });
        add(nombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 350, 30));

        apellidos.setName(""); // NOI18N
        apellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidosActionPerformed(evt);
            }
        });
        add(apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 350, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Compañia:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 150, 30));

        icon_buscarClientes1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        icon_buscarClientes1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/limpiar.png"))); // NOI18N
        icon_buscarClientes1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_buscarClientes1MouseClicked(evt);
            }
        });
        add(icon_buscarClientes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 380, 30, 30));

        icon_buscarClientes.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        icon_buscarClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        icon_buscarClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_buscarClientesMouseClicked(evt);
            }
        });
        add(icon_buscarClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 30, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Estado:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 150, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Equipo de Trabajo:");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 150, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, 350, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 550, 610));

        consultar.setBackground(new java.awt.Color(255, 255, 255));
        consultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        consultar.setText("CONSULTAR");
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        add(consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 40, 140, 40));
        add(valorBusqueda1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 560, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("REGISTRAR PERSONA");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1250, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
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
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if(numDocumento.equals("")){
           JOptionPane.showMessageDialog(null, "El número de documento no puede estar vacio", "ERROR", JOptionPane.ERROR_MESSAGE); 
        } else {
            try {
                new java.math.BigDecimal(numDocumento.getText());
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
                                    Persona persona = new Persona();
                                    persona.setCodigo(numDocumento.getText());
                                    persona.setTipoDocumento(listadoTipoDocumento.get(select_tipoDocumento.getSelectedIndex()));
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
                                        if (new ControlDB_Persona(tipoConexion).validarExistencia(persona)) {
                                            JOptionPane.showMessageDialog(null, "La persona ya fue registrada en el sistema");
                                        } else {
                                            int respuesta = new ControlDB_Persona(tipoConexion).registrar(persona, user);
                                            if (respuesta == 1) {
                                                JOptionPane.showMessageDialog(null, "Se registro la persona de forma Exitosa");
                                                numDocumento.setText("");
                                                nombres.setText("");
                                                apellidos.setText("");
                                                telefono.setText("");
                                                estado.setSelectedIndex(0);
                                                try {
                                                    tabla_Listar(valorBusqueda1.getText());
                                                    resizeColumnWidth(tabla);
                                                } catch (SQLException ex) {
                                                    Logger.getLogger(Persona_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            } else {
                                                if (respuesta == 0) {
                                                    JOptionPane.showMessageDialog(null, "No se pudo registrar la persona, valide datos");
                                                }
                                            }
                                        }
                                    } catch (FileNotFoundException ex) {
                                        JOptionPane.showMessageDialog(null, "Error al registrar la persona");
                                        Logger.getLogger(Persona_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (UnknownHostException ex) {
                                        Logger.getLogger(Persona_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (SocketException ex) {
                                        Logger.getLogger(Persona_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "El número de documento debe ser númerico", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_telefonoActionPerformed

    private void numDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numDocumentoActionPerformed

    private void nombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombresActionPerformed

    private void apellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidosActionPerformed

    private void icon_buscarClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_buscarClientesMouseClicked
        InternaFrame_buscarEquipo.show(true);
    }//GEN-LAST:event_icon_buscarClientesMouseClicked

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        try {
            tabla_Listar(valorBusqueda1.getText());
            resizeColumnWidth(tabla);
        } catch (SQLException ex) {
            Logger.getLogger(Persona_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_consultarActionPerformed

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

    private void icon_buscarClientes1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_buscarClientes1MouseClicked
        equipo=null;
        label_equipoTrabajo.setText("");
    }//GEN-LAST:event_icon_buscarClientes1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame InternaFrame_buscarEquipo;
    private javax.swing.JTextField apellidos;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton consultar;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel icon_buscarClientes;
    private javax.swing.JLabel icon_buscarClientes1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_equipoTrabajo;
    private javax.swing.JTextField nombres;
    private javax.swing.JTextField numDocumento;
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
    private javax.swing.JTextField valorBusqueda1;
    // End of variables declaration//GEN-END:variables

     public void tabla_Listar(String valorConsulta) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"TipoDocumento","Número", "Nombre","Telefono","CargoNomina","TipoContrato","Compañia","Equipo","Estado"});  
        ArrayList<Persona> listado=new ControlDB_Persona(tipoConexion).buscar(valorConsulta);
        for (Persona objeto : listado) {
            String[] registro = new String[9];
            registro[0] = "" + objeto.getTipoDocumento().getDescripcion();
            registro[1] = "" + objeto.getCodigo();
            registro[2] = "" + objeto.getNombre()+" "+objeto.getApellido();
            registro[3] = "" + objeto.getTelefono();
            registro[4] = "" + objeto.getCargoNomina().getDescripcion();
            registro[5] = "" + objeto.getTipoContrato().getDescripcion();
            registro[6] = "" + objeto.getCompania().getDescripcion();
            registro[7] = "" + objeto.getEquipo().getDescripcion()+" "+ objeto.getEquipo().getModelo();
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
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            if(width > 300)
                width=300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }


}
