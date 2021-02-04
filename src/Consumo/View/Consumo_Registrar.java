package Consumo.View;
 
import Catalogo.View23.Cliente_Consultar;
import Catalogo.Controller.ControlDB_Cliente;
import Consumo.Controller2.ControlDB_Consumo;
import Consumo.Controller2.ControlDB_Insumo;
import Catalogo.Model.Cliente;
import Consumo.Model2.Consumo;
import Consumo.Model2.Insumo;
import Consumo.Model2.Unidad;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Consumo_Registrar extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;
    public Consumo_Registrar(Usuario u, String tipoConexion) {
        initComponents();
        user= u; 
        this.tipoConexion= tipoConexion;
        internalFrameClientes.show(false);
        internalFrameInsumos.show(false);
        
        internalFrameInsumos.getContentPane().setBackground(Color.WHITE);
        internalFrameClientes.getContentPane().setBackground(Color.WHITE);
        usuario.setText(user.getNombres()+" "+user.getApellidos());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SeleccionarCliente = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        SeleccionarInsumo = new javax.swing.JPopupMenu();
        Editar1 = new javax.swing.JMenuItem();
        internalFrameInsumos = new javax.swing.JInternalFrame();
        valorBusquedaInsumo = new javax.swing.JTextField();
        BotonConsultarInsumo = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_ListadoInsumo = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        internalFrameClientes = new javax.swing.JInternalFrame();
        jLabel5 = new javax.swing.JLabel();
        valorBusqueda = new javax.swing.JTextField();
        btn_consultar_cliente = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_clientes = new javax.swing.JTable();
        consumo_fecha = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        usuario = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        consumo_observacion = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_registrar_unidad = new javax.swing.JButton();
        producto_cantidad = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        insumo_unidad = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cliente_codigo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cliente_nombre = new javax.swing.JLabel();
        insumo_codigo = new javax.swing.JLabel();
        insumo_nombre = new javax.swing.JLabel();
        alerta_producto = new javax.swing.JLabel();
        alerta_fecha = new javax.swing.JLabel();
        alerta_cliente = new javax.swing.JLabel();

        Editar.setText("Seleccionar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        SeleccionarCliente.add(Editar);

        Editar1.setText("Seleccionar");
        Editar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Editar1ActionPerformed(evt);
            }
        });
        SeleccionarInsumo.add(Editar1);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        internalFrameInsumos.setBackground(new java.awt.Color(255, 255, 255));
        internalFrameInsumos.setClosable(true);
        internalFrameInsumos.setTitle("AGREGAR INSUMO");
        internalFrameInsumos.setVisible(true);
        internalFrameInsumos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        internalFrameInsumos.getContentPane().add(valorBusquedaInsumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 280, 30));

        BotonConsultarInsumo.setBackground(new java.awt.Color(255, 255, 255));
        BotonConsultarInsumo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BotonConsultarInsumo.setText("CONSULTAR");
        BotonConsultarInsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonConsultarInsumoActionPerformed(evt);
            }
        });
        internalFrameInsumos.getContentPane().add(BotonConsultarInsumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 140, 30));

        tabla_ListadoInsumo = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla_ListadoInsumo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla_ListadoInsumo.setComponentPopupMenu(SeleccionarInsumo);
        tabla_ListadoInsumo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_ListadoInsumoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabla_ListadoInsumo);

        internalFrameInsumos.getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 950, 510));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("CONSULTAR INSUMO:");
        internalFrameInsumos.getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 30));

        add(internalFrameInsumos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        internalFrameClientes.setBackground(new java.awt.Color(255, 255, 255));
        internalFrameClientes.setClosable(true);
        internalFrameClientes.setTitle("AGREGAR CLIENTE");
        internalFrameClientes.setVisible(true);
        internalFrameClientes.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("CONSULTAR CLIENTES");
        internalFrameClientes.getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 30));
        internalFrameClientes.getContentPane().add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 310, 30));

        btn_consultar_cliente.setBackground(new java.awt.Color(255, 255, 255));
        btn_consultar_cliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_consultar_cliente.setText("CONSULTAR");
        btn_consultar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultar_clienteActionPerformed(evt);
            }
        });
        internalFrameClientes.getContentPane().add(btn_consultar_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 140, 30));

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
        tabla_clientes.setComponentPopupMenu(SeleccionarCliente);
        tabla_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_clientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_clientes);

        internalFrameClientes.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 910, 460));

        add(internalFrameClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        consumo_fecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                consumo_fechaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                consumo_fechaMouseEntered(evt);
            }
        });
        add(consumo_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 440, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Usuario quien registra consumo:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 230, 30));

        usuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        usuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 350, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("FECHA:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 100, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("CANTIDAD A CONSUMIR:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 170, 30));

        consumo_observacion.setColumns(20);
        consumo_observacion.setRows(5);
        jScrollPane1.setViewportView(consumo_observacion);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 440, 500, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("OBSERVACION:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 150, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("CARGAR INSUMO");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 160, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("REGISTRO DE CONSUMO DE INSUMOS");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 410, 30));

        btn_registrar_unidad.setBackground(new java.awt.Color(255, 255, 255));
        btn_registrar_unidad.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_registrar_unidad.setText("REGISTRAR");
        btn_registrar_unidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_registrar_unidadMouseExited(evt);
            }
        });
        btn_registrar_unidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrar_unidadActionPerformed(evt);
            }
        });
        add(btn_registrar_unidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 600, 140, 30));

        producto_cantidad.setToolTipText("");
        add(producto_cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 400, 310, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/Icono_añadir.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 50, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Unidad:");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, 110, 30));

        insumo_unidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        insumo_unidad.setForeground(new java.awt.Color(102, 102, 102));
        add(insumo_unidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 350, 440, 30));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Código:");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 110, 30));

        cliente_codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cliente_codigo.setForeground(new java.awt.Color(102, 102, 102));
        add(cliente_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, 440, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/Icono_añadir.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 40, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("CARGAR CLIENTE");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 160, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Nombre:");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, 110, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Código:");
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, 110, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Nombre:");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 310, 110, 30));

        cliente_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cliente_nombre.setForeground(new java.awt.Color(102, 102, 102));
        add(cliente_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, 440, 30));

        insumo_codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        insumo_codigo.setForeground(new java.awt.Color(102, 102, 102));
        add(insumo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 440, 30));

        insumo_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        insumo_nombre.setForeground(new java.awt.Color(102, 102, 102));
        add(insumo_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 310, 440, 30));

        alerta_producto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_producto.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, 660, 20));

        alerta_fecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_fecha.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 660, 20));

        alerta_cliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_cliente.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, 660, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void consumo_fechaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consumo_fechaMouseClicked
       
    }//GEN-LAST:event_consumo_fechaMouseClicked

    private void consumo_fechaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consumo_fechaMouseEntered
       
    }//GEN-LAST:event_consumo_fechaMouseEntered

    private void btn_registrar_unidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrar_unidadActionPerformed
         try{
            Calendar fecha = consumo_fecha.getCalendar();
            String ano = ""+fecha.get(Calendar.YEAR);
            String mes = "";
            if((fecha.get(Calendar.MONTH) +1) <=9){
                mes = "0"+(fecha.get(Calendar.MONTH) + 1);
            }else{
                mes = ""+(fecha.get(Calendar.MONTH) + 1);
            }
            String dia = "";
            if(fecha.get(Calendar.DAY_OF_MONTH) <=9){
                dia = "0"+fecha.get(Calendar.DAY_OF_MONTH);
            }else{
                dia = ""+fecha.get(Calendar.DAY_OF_MONTH);
            }
            String SfechaConsumo=ano+"-"+mes+"-"+dia;
            if(cliente_codigo.getText().equalsIgnoreCase("")){
                alerta_cliente.setText("ERROR!!!. debe de cargar un cliente");
            }else{
                if(insumo_codigo.getText().equalsIgnoreCase("")){
                    alerta_producto.setText("ERROR!!!. debe de cargar un insumo");
                }else{
                    Consumo consumo = new Consumo();
                    consumo.setFecha(SfechaConsumo);
                    consumo.setCliente(new Cliente(cliente_codigo.getText(),cliente_nombre.getText(),"",0));
                    consumo.setInsumo(new Insumo(Integer.parseInt(insumo_codigo.getText()), insumo_nombre.getText(), new Unidad(0, insumo_unidad.getText(), "1"), 0, "Activo"));
                    consumo.setUsuario(user);
                    consumo.setCantidad(Integer.parseInt(producto_cantidad.getSelectedItem().toString()));
                    consumo.setObservacion(consumo_observacion.getText());
                    
                    //registramos el consumo, pero antes validamos que la cantidad solicitada este en existencia
                    ControlDB_Consumo controlDB_Consumo = new ControlDB_Consumo(tipoConexion);
                    int retorno;
                    retorno = controlDB_Consumo.registrar(consumo,user);
                    if(retorno ==1){//Se registro el consumo de manera exitosa
                        JOptionPane.showMessageDialog(null, "Se registro el consumo con éxito"); 
                        cliente_codigo.setText("");
                        cliente_nombre.setText("");
                        insumo_codigo.setText("");
                        insumo_nombre.setText("");
                        insumo_unidad.setText("");
                        
                        //Eliminamos todos los item de las cantidades para cargar nuevas cantidades
                        producto_cantidad.removeAllItems();
                        
                        consumo_observacion.setText("");
                        valorBusquedaInsumo.setText("");
                        valorBusqueda.setText("");
                        try {
                            tabla_ListarInsumos("");
                            tabla_ListarClientes("");
                        } catch (SQLException ex) {
                            Logger.getLogger(Insumo_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "No se pudo registrar el consumo valide la información suministrada e intente nu");  
                    }
                }
            }       
        }catch(Exception e){
            alerta_fecha.setText("ERROR!!!. verifique la fecha debe tener un formato valido");
        }
    }//GEN-LAST:event_btn_registrar_unidadActionPerformed

    private void btn_consultar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultar_clienteActionPerformed
        try {
            tabla_ListarClientes(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Cliente_Consultar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_consultar_clienteActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        try{
            int fila1=tabla_clientes.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                DefaultTableModel modeloCliente=(DefaultTableModel)tabla_clientes.getModel();
                cliente_codigo.setText((String)modeloCliente.getValueAt(fila1, 0));
                cliente_nombre.setText((String)modeloCliente.getValueAt(fila1, 1));
                internalFrameClientes.show(false);
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        internalFrameClientes.show(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        internalFrameClientes.show(true);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void BotonConsultarInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonConsultarInsumoActionPerformed
        try {
            tabla_ListarInsumos(valorBusquedaInsumo.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Insumo_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotonConsultarInsumoActionPerformed

    private void Editar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Editar1ActionPerformed
        try{
            int fila1=tabla_ListadoInsumo.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                DefaultTableModel modeloInsumno=(DefaultTableModel)tabla_ListadoInsumo.getModel();
                insumo_codigo.setText((String)modeloInsumno.getValueAt(fila1, 0));
                insumo_nombre.setText((String)modeloInsumno.getValueAt(fila1, 1));
                insumo_unidad.setText((String)modeloInsumno.getValueAt(fila1, 2));   
                int productoCantidadInventario=Integer.parseInt((String)modeloInsumno.getValueAt(fila1, 3));
                
                //Eliminamos todos los item de las cantidades para cargar nuevas cantidades
                producto_cantidad.removeAllItems();
                
                //Cargamos la cantidad de unidades en existencia
                for(int i=1; i<=productoCantidadInventario;i++){
                    producto_cantidad.addItem(""+i);
                }
                internalFrameInsumos.show(false);
            }
                      
        }catch(HeadlessException | NumberFormatException e){
        }
    }//GEN-LAST:event_Editar1ActionPerformed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        internalFrameInsumos.show(true);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        internalFrameInsumos.show(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void tabla_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_clientesMouseClicked
        if (evt.getClickCount() == 2) {
            try{
                int fila1=tabla_clientes.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    DefaultTableModel modeloCliente=(DefaultTableModel)tabla_clientes.getModel();
                    cliente_codigo.setText((String)modeloCliente.getValueAt(fila1, 0));
                    cliente_nombre.setText((String)modeloCliente.getValueAt(fila1, 1));
                    internalFrameClientes.show(false);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error al cargar cliente");
            }
        }
    }//GEN-LAST:event_tabla_clientesMouseClicked

    private void tabla_ListadoInsumoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ListadoInsumoMouseClicked
        if(evt.getClickCount()==2){
            try{
                int fila1=tabla_ListadoInsumo.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    DefaultTableModel modeloInsumno=(DefaultTableModel)tabla_ListadoInsumo.getModel();
                    insumo_codigo.setText((String)modeloInsumno.getValueAt(fila1, 0));
                    insumo_nombre.setText((String)modeloInsumno.getValueAt(fila1, 1));
                    insumo_unidad.setText((String)modeloInsumno.getValueAt(fila1, 2));   
                    int productoCantidadInventario=Integer.parseInt((String)modeloInsumno.getValueAt(fila1, 3));

                    //Eliminamos todos los item de las cantidades para cargar nuevas cantidades
                    producto_cantidad.removeAllItems();

                    //Cargamos la cantidad de unidades en existencia
                    for(int i=1; i<=productoCantidadInventario;i++){
                        producto_cantidad.addItem(""+i);
                    }
                    internalFrameInsumos.show(false);
                }
            }catch(HeadlessException | NumberFormatException e){
            }
        }
    }//GEN-LAST:event_tabla_ListadoInsumoMouseClicked

    private void btn_registrar_unidadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_registrar_unidadMouseExited
        alerta_cliente.setText("");
        alerta_fecha.setText("");
        alerta_producto.setText("");
    }//GEN-LAST:event_btn_registrar_unidadMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonConsultarInsumo;
    private javax.swing.JMenuItem Editar;
    private javax.swing.JMenuItem Editar1;
    private javax.swing.JPopupMenu SeleccionarCliente;
    private javax.swing.JPopupMenu SeleccionarInsumo;
    private javax.swing.JLabel alerta_cliente;
    private javax.swing.JLabel alerta_fecha;
    private javax.swing.JLabel alerta_producto;
    private javax.swing.JButton btn_consultar_cliente;
    private javax.swing.JButton btn_registrar_unidad;
    private javax.swing.JLabel cliente_codigo;
    private javax.swing.JLabel cliente_nombre;
    private com.toedter.calendar.JDateChooser consumo_fecha;
    private javax.swing.JTextArea consumo_observacion;
    private javax.swing.JLabel insumo_codigo;
    private javax.swing.JLabel insumo_nombre;
    private javax.swing.JLabel insumo_unidad;
    private javax.swing.JInternalFrame internalFrameClientes;
    private javax.swing.JInternalFrame internalFrameInsumos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox<String> producto_cantidad;
    private javax.swing.JTable tabla_ListadoInsumo;
    private javax.swing.JTable tabla_clientes;
    private javax.swing.JLabel usuario;
    private javax.swing.JTextField valorBusqueda;
    private javax.swing.JTextField valorBusquedaInsumo;
    // End of variables declaration//GEN-END:variables
    public void tabla_ListarClientes(String valorConsulta) throws SQLException{
        DefaultTableModel modeloCliente;
        String [] tituloCliente= {"Código", "Nombre","Estado", "Valor Recobro"};
        String[]  registroCliente;  
        ControlDB_Cliente  controlDB_Cliente = new ControlDB_Cliente(tipoConexion);        
        registroCliente = new String[4]; 
        modeloCliente = new DefaultTableModel(null, tituloCliente);  
        ArrayList<Cliente> listadoCliente=controlDB_Cliente.buscar(valorConsulta);
        for(int i =0; i< listadoCliente.size(); i++){
            registroCliente[0]=""+listadoCliente.get(i).getCodigo();
            registroCliente[1]=""+listadoCliente.get(i).getDescripcion();
            registroCliente[2]=""+listadoCliente.get(i).getEstado();  
            registroCliente[3]=""+listadoCliente.get(i).getValorRecobro();  
            modeloCliente.addRow(registroCliente);
            tabla_clientes.setModel(modeloCliente);
        }
    }
    public void tabla_ListarInsumos(String valorConsulta) throws SQLException{
        DefaultTableModel modeloInsumno;
        String [] tituloInsumo= {"Codigo", "Nombre","Unidad", "Cantidad", "Estado"};
        String[]  registroInsumo;   
        ControlDB_Insumo  controlDB_Insumo = new ControlDB_Insumo(tipoConexion);        
        registroInsumo = new String[5]; 
        modeloInsumno = new DefaultTableModel(null, tituloInsumo);  
        ArrayList<Insumo> listadoInsumo=controlDB_Insumo.buscar(valorConsulta);
        for(int i =0; i< listadoInsumo.size(); i++){
            registroInsumo[0]=""+listadoInsumo.get(i).getCodigo();
            registroInsumo[1]=""+listadoInsumo.get(i).getDescripcion();
            registroInsumo[2]=""+listadoInsumo.get(i).getUnidad().getDescripcion();
            registroInsumo[3]=""+listadoInsumo.get(i).getCantidad();
            registroInsumo[4]=""+listadoInsumo.get(i).getEstado();            
            if(listadoInsumo.get(i).getCantidad()>0 && listadoInsumo.get(i).getEstado().equalsIgnoreCase("ACTIVO")){//Validamos que la cantidades de los Insumos sean mayor a cero y se encuentren activas, con el fin que cargue solo los insumos que tienen existencia
                modeloInsumno.addRow(registroInsumo);    
            }    
        }
        tabla_ListadoInsumo.setModel(modeloInsumno);
    }
}
