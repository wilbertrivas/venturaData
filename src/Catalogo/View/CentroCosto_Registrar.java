package Catalogo.View;
  
import Catalogo.Controller.ControlDB_CentroCosto;
import Catalogo.Controller.ControlDB_CentroCostoSubCentro;
import Catalogo.Controller.ControlDB_CentroOperacion;
import Catalogo.Controller.ControlDB_Cliente;
import Catalogo.Controller.ControlDB_LaborRealizada;
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.CentroCosto;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import Catalogo.Model.Cliente;
import Catalogo.Model.LaborRealizada;
import ModuloCarbon.Controller2.ControlDB_MvtoCarbon;
import ModuloEquipo.View.Solicitud_Equipos_Registrar;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class CentroCosto_Registrar extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;
    ArrayList<CentroCostoSubCentro> listadoCentroCostoSubCentro = new ArrayList();
    ArrayList<CentroCostoAuxiliar> listadoCentroCostoAuxiliar= new ArrayList();
    ArrayList<CentroOperacion> listadoCentroOperacion= new ArrayList();
    ArrayList<CentroCosto> listadoCentroCosto=new ArrayList();
    ArrayList<Cliente> listadoCliente= new ArrayList();
    private ArrayList<LaborRealizada> listadoLaborRealizada = new ArrayList();
    Cliente cliente;
    
    public CentroCosto_Registrar(Usuario us,String tipoConexion) {
        initComponents();
        InternaFrame_BuscarClientes.getContentPane().setBackground(Color.white);
        cliente= new Cliente();
        user=us;   
        this.tipoConexion= tipoConexion;
        
        
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            Logger.getLogger(CentroCosto_Registrar.class.getName()).log(Level.SEVERE, null, ex);
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
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }  
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
                } 
            }
      
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
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
            if(listadoCentroCostoSubCentro != null){
                if(listadoCentroCostoSubCentro.get(selectSubcentroCosto.getSelectedIndex()).getCentroCostoRequiereLaborRealizada().equals("1")){
                    try {
                        titulo41.show(true);
                        selectLaborRealizada.show(true);
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
                    titulo41.show(false);
                    selectLaborRealizada.show(false);
                    listadoLaborRealizada=null;
                    selectLaborRealizada.removeAllItems();
                }
            }
        }catch(SQLException e){ 
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        InternaFrame_BuscarClientes = new javax.swing.JInternalFrame();
        jLabel12 = new javax.swing.JLabel();
        valorBusqueda = new javax.swing.JTextField();
        btn_consultar_cliente = new javax.swing.JButton();
        btn_cancelar_cliente1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_clientes = new javax.swing.JTable();
        clienteCodigo = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btn_registrar_insumo1 = new javax.swing.JButton();
        btn_registrar_insumo2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        clienteNombre = new javax.swing.JLabel();
        titulo20 = new javax.swing.JLabel();
        selectSubcentroCosto = new javax.swing.JComboBox<>();
        titulo43 = new javax.swing.JLabel();
        selectCentroCostoAuxiliar = new javax.swing.JComboBox<>();
        titulo28 = new javax.swing.JLabel();
        centroOperacion = new javax.swing.JComboBox<>();
        titulo41 = new javax.swing.JLabel();
        selectLaborRealizada = new javax.swing.JComboBox<>();
        check_CentroCostoAuxiliarTodos = new javax.swing.JRadioButton();

        Editar.setText("Modificar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InternaFrame_BuscarClientes.setClosable(true);
        InternaFrame_BuscarClientes.setVisible(false);
        InternaFrame_BuscarClientes.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("CONSULTAR CLIENTES");
        InternaFrame_BuscarClientes.getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 310, 30));
        InternaFrame_BuscarClientes.getContentPane().add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 310, 40));

        btn_consultar_cliente.setBackground(new java.awt.Color(255, 255, 255));
        btn_consultar_cliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_consultar_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        btn_consultar_cliente.setText("CONSULTAR");
        btn_consultar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultar_clienteActionPerformed(evt);
            }
        });
        InternaFrame_BuscarClientes.getContentPane().add(btn_consultar_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 140, 40));

        btn_cancelar_cliente1.setBackground(new java.awt.Color(255, 255, 255));
        btn_cancelar_cliente1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cancelar_cliente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btn_cancelar_cliente1.setText("CANCELAR");
        btn_cancelar_cliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_cliente1ActionPerformed(evt);
            }
        });
        InternaFrame_BuscarClientes.getContentPane().add(btn_cancelar_cliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 140, 40));

        tabla_clientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_clientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_clientes);

        InternaFrame_BuscarClientes.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 1380, 500));

        add(InternaFrame_BuscarClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1430, 760));

        clienteCodigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clienteCodigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(clienteCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 110, 30));
        add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 130, 470, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Descripción");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 130, 100, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Estado");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 170, 120, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 170, 470, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("REGISTRO DE CENTRO DE COSTO");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 440, 30));

        btn_registrar_insumo1.setBackground(new java.awt.Color(255, 255, 255));
        btn_registrar_insumo1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_registrar_insumo1.setText("REGISTRAR");
        btn_registrar_insumo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_registrar_insumo1MouseExited(evt);
            }
        });
        btn_registrar_insumo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrar_insumo1ActionPerformed(evt);
            }
        });
        add(btn_registrar_insumo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, 140, 30));

        btn_registrar_insumo2.setBackground(new java.awt.Color(255, 255, 255));
        btn_registrar_insumo2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_registrar_insumo2.setText("CANCELAR");
        btn_registrar_insumo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrar_insumo2ActionPerformed(evt);
            }
        });
        add(btn_registrar_insumo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, 140, 30));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 1400, 470));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Icono_añadir.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 40, 40));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Código Cliente:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 140, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Cliente Nombre:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 140, 30));

        clienteNombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clienteNombre.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(clienteNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 470, 30));

        titulo20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo20.setForeground(new java.awt.Color(51, 51, 51));
        titulo20.setText("SubCentro Costo:");
        add(titulo20, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 90, 150, 30));

        selectSubcentroCosto.setToolTipText("");
        selectSubcentroCosto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectSubcentroCostoItemStateChanged(evt);
            }
        });
        add(selectSubcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 90, 470, 30));

        titulo43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo43.setForeground(new java.awt.Color(51, 51, 51));
        titulo43.setText("C.C. Auxiliar:");
        add(titulo43, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 130, 30));

        selectCentroCostoAuxiliar.setToolTipText("");
        selectCentroCostoAuxiliar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectCentroCostoAuxiliarActionPerformed(evt);
            }
        });
        add(selectCentroCostoAuxiliar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 380, 30));

        titulo28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo28.setForeground(new java.awt.Color(51, 51, 51));
        titulo28.setText("Centro Operación:");
        add(titulo28, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 150, 30));

        centroOperacion.setToolTipText("");
        centroOperacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                centroOperacionItemStateChanged(evt);
            }
        });
        add(centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 470, 30));

        titulo41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo41.setForeground(new java.awt.Color(51, 51, 51));
        titulo41.setText("Labor a Realizar:");
        add(titulo41, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 150, 30));

        selectLaborRealizada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectLaborRealizadaActionPerformed(evt);
            }
        });
        add(selectLaborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 470, 30));

        check_CentroCostoAuxiliarTodos.setBackground(new java.awt.Color(255, 255, 255));
        check_CentroCostoAuxiliarTodos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        check_CentroCostoAuxiliarTodos.setText("TODOS");
        check_CentroCostoAuxiliarTodos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                check_CentroCostoAuxiliarTodosItemStateChanged(evt);
            }
        });
        add(check_CentroCostoAuxiliarTodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 70, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        int fila1;
        try{
            fila1=tabla_clientes.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                cliente=listadoCliente.get(fila1);
                clienteCodigo.setText(cliente.getCodigo());
                clienteNombre.setText(cliente.getDescripcion());
                InternaFrame_BuscarClientes.show(false);
            }
        }catch(HeadlessException e){
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void btn_registrar_insumo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrar_insumo1ActionPerformed
        
            if(clienteNombre.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Debe Carga un cliente","Advertencia",JOptionPane.ERROR_MESSAGE);
            }else{
                if(listadoCentroOperacion == null){
                    JOptionPane.showMessageDialog(null, "Debe de carga un Centro de Operacion","Advertencia",JOptionPane.ERROR_MESSAGE);
                }else{
                    if(listadoCentroCostoSubCentro == null){
                        JOptionPane.showMessageDialog(null, "Debe de carga un Suncentro de costo","Advertencia",JOptionPane.ERROR_MESSAGE);
                    }else{
                        if(listadoCentroCostoAuxiliar == null){
                            JOptionPane.showMessageDialog(null, "Debe de carga un Centro de costo Auxiliar","Advertencia",JOptionPane.ERROR_MESSAGE);
                        }else{
                            if(nombre.getText().equals("")){
                                JOptionPane.showMessageDialog(null, "El nombre del centro de costo no puede estar vacio","Advertencia",JOptionPane.ERROR_MESSAGE);
                            }else{
                                if(check_CentroCostoAuxiliarTodos.isSelected()){//Se registra el centro de costo para todos los centro de costos Auxiliares cargados en el select CentroCostoAuxiliar
                                    for(CentroCostoAuxiliar centroCostoAuxiliar: listadoCentroCostoAuxiliar){
                                        CentroOperacion centroOperacionI = new CentroOperacion();
                                        centroOperacionI = listadoCentroOperacion.get(centroOperacion.getSelectedIndex());
                                        CentroCosto centroCosto = new CentroCosto();
                                        centroCosto.setCliente(cliente);
                                        centroCosto.setCentroOperacion(centroOperacionI);
                                        centroCosto.setCentroCostoAuxiliar(centroCostoAuxiliar);
                                        centroCosto.setDescripción(nombre.getText());
                                        //Validamos si selecciono activo o inactivo
                                        if(estado.getSelectedItem().toString().equalsIgnoreCase("Activo")){
                                            centroCosto.setEstado("1");
                                        }else{
                                            centroCosto.setEstado("0");
                                        }
                                        if(listadoLaborRealizada !=null){
                                            centroCosto.setLaborRealizada(listadoLaborRealizada.get(selectLaborRealizada.getSelectedIndex()));
                                        }else{
                                            centroCosto.setLaborRealizada(null);
                                            centroCosto.getLaborRealizada().setCodigo(null);
                                        }
                                        centroCosto.setClienteBaseDatos(cliente.getBaseDatos().getCodigo());
                                       // ControlDB_CentroCosto controlDB_CentroCosto = new ControlDB_CentroCosto(tipoConexion);
                                        if(new ControlDB_CentroCosto(tipoConexion).validarExistencia(centroCosto)){
                                            JOptionPane.showMessageDialog(null, "Ya existe un centro de costo  registrado para la información suministrada, verifique información", "Error!!", JOptionPane.ERROR_MESSAGE);
                                        }else{
                                            int respuesta=0;
                                            try {
                                                respuesta = new ControlDB_CentroCosto(tipoConexion).registrar(centroCosto, user);

                                            } catch (FileNotFoundException ex) {
                                                Logger.getLogger(CentroCosto_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                                            } catch (UnknownHostException ex) {
                                                Logger.getLogger(CentroCosto_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                                            } catch (SocketException ex) {
                                                Logger.getLogger(CentroCosto_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            if(respuesta==1){
                                                JOptionPane.showMessageDialog(null, "Se registro el centro de costo de manera exitosa para el C.C Auxiliar "+centroCostoAuxiliar.getDescripcion());  
                                            }else{
                                                if(respuesta==0){
                                                    JOptionPane.showMessageDialog(null, "No se pudo registrar el centro de costo para el C.C Auxiliar "+centroCostoAuxiliar.getDescripcion()+", valide datos");
                                                }
                                            }
                                        }
                                    }
                                    
                                    nombre.setText("");
                                    try {
                                        tabla_Listar("");
                                    } catch (SQLException ex) {
                                        Logger.getLogger(CentroCosto_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }else{//Se registra el centro de costo para un unico Centro de Costo Auxiliar
                                    CentroOperacion centroOperacionI = new CentroOperacion();
                                    centroOperacionI = listadoCentroOperacion.get(centroOperacion.getSelectedIndex());

                                    CentroCostoAuxiliar centroCostoAuxiliarI = new CentroCostoAuxiliar();
                                    centroCostoAuxiliarI= listadoCentroCostoAuxiliar.get(selectCentroCostoAuxiliar.getSelectedIndex());

                                    CentroCosto centroCosto = new CentroCosto();
                                    centroCosto.setCliente(cliente);
                                    centroCosto.setCentroOperacion(centroOperacionI);
                                    centroCosto.setCentroCostoAuxiliar(centroCostoAuxiliarI);
                                    centroCosto.setDescripción(nombre.getText());
                                    //Validamos si selecciono activo o inactivo
                                    if(estado.getSelectedItem().toString().equalsIgnoreCase("Activo")){
                                        centroCosto.setEstado("1");
                                    }else{
                                        centroCosto.setEstado("0");
                                    }
                                    if(listadoLaborRealizada !=null){
                                        centroCosto.setLaborRealizada(listadoLaborRealizada.get(selectLaborRealizada.getSelectedIndex()));
                                    }else{
                                        centroCosto.setLaborRealizada(null);
                                    }
                                     centroCosto.setClienteBaseDatos(cliente.getBaseDatos().getCodigo());
                                   // ControlDB_CentroCosto controlDB_CentroCosto = new ControlDB_CentroCosto(tipoConexion);
                                    if(new ControlDB_CentroCosto(tipoConexion).validarExistencia(centroCosto)){
                                        JOptionPane.showMessageDialog(null, "Ya existe un centro de costo  registrado para la información suministrada, verifique información", "Error!!", JOptionPane.ERROR_MESSAGE);
                                    }else{
                                        int respuesta=0;
                                        try {
                                            respuesta = new ControlDB_CentroCosto(tipoConexion).registrar(centroCosto, user);

                                        } catch (FileNotFoundException ex) {
                                            Logger.getLogger(CentroCosto_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (UnknownHostException ex) {
                                            Logger.getLogger(CentroCosto_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (SocketException ex) {
                                            Logger.getLogger(CentroCosto_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        if(respuesta==1){
                                            JOptionPane.showMessageDialog(null, "Se registro el centro de costo mayor de manera exitosa");
                                            //clienteCodigo.setText("");
                                            //clienteNombre.setText("");
                                            nombre.setText("");
                                            //centroOperacion.setSelectedIndex(0);
                                            //selectSubcentroCosto.setSelectedIndex(0);
                                            //selectCentroCostoAuxiliar.setSelectedIndex(0);
                                            //estado.setSelectedIndex(0);
                                            try {
                                                tabla_Listar("");
                                            } catch (SQLException ex) {
                                                Logger.getLogger(CentroCosto_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }else{
                                            if(respuesta==0){
                                                JOptionPane.showMessageDialog(null, "No se pudo registrar el centro de costo mayor, valide datos");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        
    }//GEN-LAST:event_btn_registrar_insumo1ActionPerformed

    private void btn_registrar_insumo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrar_insumo2ActionPerformed
        nombre.setText("");
        //subcentroCosto.setSelectedIndex(0);
        estado.setSelectedIndex(0);
    }//GEN-LAST:event_btn_registrar_insumo2ActionPerformed

    private void btn_registrar_insumo1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_registrar_insumo1MouseExited
       // alerta_nombre.setText("");
    }//GEN-LAST:event_btn_registrar_insumo1MouseExited

    private void btn_consultar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultar_clienteActionPerformed
        ControlDB_Cliente  controlDB_Cliente = new ControlDB_Cliente(tipoConexion);        
        String[]  registroCliente;  
        DefaultTableModel modeloCliente;
        String [] tituloCliente= {"Código", "Nombre","Estado", "Valor Recobro","Base De Datos"};
        registroCliente = new String[5]; 
        modeloCliente = new DefaultTableModel(null, tituloCliente);  
        listadoCliente = null;
        try {
            listadoCliente = controlDB_Cliente.buscar(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(CentroCosto_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(listadoCliente != null){
            for(int i =0; i< listadoCliente.size(); i++){
                registroCliente[0]=""+listadoCliente.get(i).getCodigo();
                registroCliente[1]=""+listadoCliente.get(i).getDescripcion();
                registroCliente[2]=""+listadoCliente.get(i).getEstado();  
                registroCliente[3]=""+listadoCliente.get(i).getValorRecobro();  
                registroCliente[4]=""+listadoCliente.get(i).getBaseDatos().getDescripcion();  
                modeloCliente.addRow(registroCliente);
                tabla_clientes.setModel(modeloCliente);
            }   
        }
       
    }//GEN-LAST:event_btn_consultar_clienteActionPerformed

    private void btn_cancelar_cliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_cliente1ActionPerformed
        valorBusqueda.setText("");
    }//GEN-LAST:event_btn_cancelar_cliente1ActionPerformed

    private void tabla_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_clientesMouseClicked
        if (evt.getClickCount() == 2) {
            int fila1;
        try{
            fila1=tabla_clientes.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                cliente=listadoCliente.get(fila1);
                clienteCodigo.setText(cliente.getCodigo());
                clienteNombre.setText(cliente.getDescripcion());
                InternaFrame_BuscarClientes.show(false);
            }
        }catch(Exception e){
        }
        }
    }//GEN-LAST:event_tabla_clientesMouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        InternaFrame_BuscarClientes.show(true);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void selectSubcentroCostoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectSubcentroCostoItemStateChanged
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
            if(listadoCentroCostoSubCentro.get(selectSubcentroCosto.getSelectedIndex()).getCentroCostoRequiereLaborRealizada().equals("1")){
                try {
                    titulo41.show(true);
                    selectLaborRealizada.show(true);
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
                titulo41.show(false);
                selectLaborRealizada.show(false);
                listadoLaborRealizada=null;
                selectLaborRealizada.removeAllItems();
            }
        }catch(SQLException e){ 
        }
    }//GEN-LAST:event_selectSubcentroCostoItemStateChanged

    private void centroOperacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_centroOperacionItemStateChanged
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
            }
      
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
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
    }//GEN-LAST:event_centroOperacionItemStateChanged

    private void selectLaborRealizadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectLaborRealizadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectLaborRealizadaActionPerformed

    private void selectCentroCostoAuxiliarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectCentroCostoAuxiliarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectCentroCostoAuxiliarActionPerformed

    private void check_CentroCostoAuxiliarTodosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_check_CentroCostoAuxiliarTodosItemStateChanged
        if(check_CentroCostoAuxiliarTodos.isSelected()){
            selectCentroCostoAuxiliar.show(false);
        }else{
            selectCentroCostoAuxiliar.show(true);
        }
    }//GEN-LAST:event_check_CentroCostoAuxiliarTodosItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JInternalFrame InternaFrame_BuscarClientes;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JButton btn_cancelar_cliente1;
    private javax.swing.JButton btn_consultar_cliente;
    private javax.swing.JButton btn_registrar_insumo1;
    private javax.swing.JButton btn_registrar_insumo2;
    private javax.swing.JComboBox<String> centroOperacion;
    private javax.swing.JRadioButton check_CentroCostoAuxiliarTodos;
    private javax.swing.JLabel clienteCodigo;
    private javax.swing.JLabel clienteNombre;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nombre;
    private javax.swing.JComboBox<String> selectCentroCostoAuxiliar;
    private javax.swing.JComboBox<String> selectLaborRealizada;
    private javax.swing.JComboBox<String> selectSubcentroCosto;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla_clientes;
    private javax.swing.JLabel titulo20;
    private javax.swing.JLabel titulo28;
    private javax.swing.JLabel titulo41;
    private javax.swing.JLabel titulo43;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException{             
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "Cliente","CentroOperación","SubcentroCosto","C.C. Auxiliar","LaborRealizada","Descripción","Base_Datos", "Estado"});  
        listadoCentroCosto =new ControlDB_CentroCosto(tipoConexion).buscar(valorConsulta);
        for(CentroCosto Objeto: listadoCentroCosto){
            String[] registro = new String[9];
            registro[0]=""+Objeto.getCodigo();
            registro[1]=""+Objeto.getCliente().getDescripcion();
            registro[2]=""+Objeto.getCentroOperacion().getDescripcion();
            registro[3]=""+Objeto.getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
            registro[4]=""+Objeto.getCentroCostoAuxiliar().getDescripcion();
            registro[5]=""+Objeto.getLaborRealizada().getDescripcion();
            registro[6]=""+Objeto.getDescripción();
            registro[7]=""+Objeto.getCliente().getBaseDatos().getDescripcion();
            
            
            /*if(Objeto.getClienteBaseDatos().equals("1")){
                 registro[7]="venus";
            }else{
                if(Objeto.getClienteBaseDatos().equals("2")){
                     registro[7]="venus_opp";
                }
            }*/
            registro[8]=""+Objeto.getEstado();
            modelo.addRow(registro);   
        }
        tabla.setModel(modelo);
    }
}
