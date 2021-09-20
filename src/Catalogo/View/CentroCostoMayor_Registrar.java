package Catalogo.View;
  
import Catalogo.Controller.ControlDB_CentroCosto;
import Catalogo.Controller.ControlDB_CentroCostoMayor;
import Catalogo.Controller.ControlDB_CentroCostoSubCentro;
import Catalogo.Controller.ControlDB_CentroOperacion;
import Catalogo.Controller.ControlDB_Cliente;
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.CentroCosto;
import Catalogo.Model.CentroCostoMayor;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import Catalogo.Model.Cliente;
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

public final class CentroCostoMayor_Registrar extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;
    ArrayList<CentroCostoSubCentro> listadoCentroCostoSubCentro = new ArrayList();
    ArrayList<CentroOperacion> listadoCentroOperacion= new ArrayList();
    ArrayList<CentroCostoMayor> listadoCentroCostMayor=new ArrayList();
    ArrayList<Cliente> listadoCliente= new ArrayList();
    Cliente cliente;
    
    public CentroCostoMayor_Registrar(Usuario us,String tipoConexion) {
        initComponents();
        InternaFrame_BuscarClientes.getContentPane().setBackground(Color.white);
        cliente= new Cliente();
        user=us;   
        this.tipoConexion= tipoConexion;
        
        
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            Logger.getLogger(CentroCostoMayor_Registrar.class.getName()).log(Level.SEVERE, null, ex);
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
        InternaFrame_BuscarClientes.show(false);
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
        titulo28 = new javax.swing.JLabel();
        centroOperacion = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

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
        InternaFrame_BuscarClientes.setVisible(true);
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

        InternaFrame_BuscarClientes.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 1300, 500));

        add(InternaFrame_BuscarClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1380, 760));

        clienteCodigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clienteCodigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(clienteCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 470, 30));
        add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 470, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Descripción");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 100, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Estado");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 130, 120, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 130, 480, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("REGISTRO DE CENTRO DE COSTO MAYOR");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1400, 30));

        btn_registrar_insumo1.setBackground(new java.awt.Color(255, 255, 255));
        btn_registrar_insumo1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_registrar_insumo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Ingresar.png"))); // NOI18N
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
        add(btn_registrar_insumo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, 140, 40));

        btn_registrar_insumo2.setBackground(new java.awt.Color(255, 255, 255));
        btn_registrar_insumo2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_registrar_insumo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btn_registrar_insumo2.setText("CANCELAR");
        btn_registrar_insumo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrar_insumo2ActionPerformed(evt);
            }
        });
        add(btn_registrar_insumo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 170, 140, 40));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 1400, 510));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Icono_añadir.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 40, 50));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Código Cliente:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 140, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Cliente Nombre:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 50, 140, 30));

        clienteNombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clienteNombre.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(clienteNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 50, 470, 30));

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
        add(selectSubcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 90, 480, 30));

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

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1400, 210));
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
                    if(nombre.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "El nombre del centro de costo mayor no puede estar vacio","Advertencia",JOptionPane.ERROR_MESSAGE);
                    }else{
                        CentroCostoMayor centroCostoMayor = new CentroCostoMayor();
                        centroCostoMayor.setCliente(cliente);
                        centroCostoMayor.setCentroCostoSubcentro(listadoCentroCostoSubCentro.get(selectSubcentroCosto.getSelectedIndex()));
                        centroCostoMayor.setDescripcion(nombre.getText());
                        //Validamos si selecciono activo o inactivo
                        if(estado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                            centroCostoMayor.setEstado("1");
                        }else{
                            centroCostoMayor.setEstado("0");
                        }
                        centroCostoMayor.setClienteBaseDatos(cliente.getBaseDatos().getCodigo());
                       // ControlDB_CentroCosto controlDB_CentroCostoMayor = new ControlDB_CentroCosto(tipoConexion);
                        if(new ControlDB_CentroCostoMayor(tipoConexion).validarExistencia(centroCostoMayor)){
                            JOptionPane.showMessageDialog(null, "Ya existe un centro de costo Mayor registrado para la información suministrada, verifique información", "Error!!", JOptionPane.ERROR_MESSAGE);
                        }else{
                            int respuesta=0;
                            try {
                                respuesta = new ControlDB_CentroCostoMayor(tipoConexion).registrar(centroCostoMayor, user); 
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(CentroCostoMayor_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (UnknownHostException ex) {
                                Logger.getLogger(CentroCostoMayor_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SocketException ex) {
                                Logger.getLogger(CentroCostoMayor_Registrar.class.getName()).log(Level.SEVERE, null, ex);
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
                                    Logger.getLogger(CentroCostoMayor_Registrar.class.getName()).log(Level.SEVERE, null, ex);
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
       
        String[]  registroCliente;  
        DefaultTableModel modeloCliente;
        String [] tituloCliente= {"Código", "Nombre","Estado", "Valor Recobro","Base De Datos"};
        registroCliente = new String[5]; 
        modeloCliente = new DefaultTableModel(null, tituloCliente);  
        listadoCliente = null;
        try {
            listadoCliente =new  ControlDB_Cliente(tipoConexion).buscar(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(CentroCostoMayor_Registrar.class.getName()).log(Level.SEVERE, null, ex);
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
                } 
            }
      
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_centroOperacionItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JInternalFrame InternaFrame_BuscarClientes;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JButton btn_cancelar_cliente1;
    private javax.swing.JButton btn_consultar_cliente;
    private javax.swing.JButton btn_registrar_insumo1;
    private javax.swing.JButton btn_registrar_insumo2;
    private javax.swing.JComboBox<String> centroOperacion;
    private javax.swing.JLabel clienteCodigo;
    private javax.swing.JLabel clienteNombre;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nombre;
    private javax.swing.JComboBox<String> selectSubcentroCosto;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla_clientes;
    private javax.swing.JLabel titulo20;
    private javax.swing.JLabel titulo28;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException{             
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "CentroOperación","Cliente","SubcentroCosto","Descripción","Base_Datos", "Estado"});  
        listadoCentroCostMayor =new ControlDB_CentroCostoMayor(tipoConexion).buscar(valorConsulta);
        for(CentroCostoMayor Objeto: listadoCentroCostMayor){
            String[] registro = new String[7];
            registro[0]=""+Objeto.getCodigo();
            registro[1]=""+Objeto.getCentroCostoSubcentro().getCentroOperacion().getDescripcion();
            registro[2]=""+Objeto.getCliente().getDescripcion();
            registro[3]=""+Objeto.getCentroCostoSubcentro().getDescripcion();
            registro[4]=""+Objeto.getDescripcion();
            registro[5]=""+Objeto.getCliente().getBaseDatos().getDescripcion();
            if(Objeto.getEstado().equals("1")){
                registro[6]="ACTIVO";
            }else{
                registro[6]="INACTIVO";
            }
            modelo.addRow(registro);   
        }
        tabla.setModel(modelo);
    }
}
