package Catalogo.View23;
 
import Catalogo.Controller.ControlDB_Cliente;
import Catalogo.Model.Cliente;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class Cliente_Consultar extends javax.swing.JPanel {

    DefaultTableModel modeloCliente;
    DefaultTableModel modeloInformacionHistoricoRecobro;
    String [] tituloCliente= {"Código", "Nombre","Estado", "Valor Recobro"};
    String [] tituloInformacionHistoricoRecobro= {"Valor del recobro", "Fecha de Registro del valor del recobro","Usuario Quien registró recobro"};
    String[]  registroCliente;  
    String[]  registroInformacionHistoricoRecobro;  
    Usuario user;
    private String tipoConexion;
    public Cliente_Consultar(Usuario u,String tipoConexion) {
        initComponents();
        user= u;
        this.tipoConexion= tipoConexion;
        try {
            tabla_ListarClientes("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los clientes");
            Logger.getLogger(Cliente_Consultar.class.getName()).log(Level.SEVERE, null, ex);
        }
        ventanaInterna_InformacionCliente.getContentPane().setBackground(Color.white);
        ventanaInterna_InformacionCliente.show(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        ventanaInterna_InformacionCliente = new javax.swing.JInternalFrame();
        informacionCliente_nombre = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        informacionCliente_codigo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_InformacionRecobroClientes = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        informacionCliente_estado = new javax.swing.JLabel();
        btn_consultar_cliente = new javax.swing.JButton();
        btn_cancelar_cliente1 = new javax.swing.JButton();
        valorBusqueda = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_clientes = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        Editar.setText("Mas Detalles");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ventanaInterna_InformacionCliente.setBackground(new java.awt.Color(255, 255, 255));
        ventanaInterna_InformacionCliente.setClosable(true);
        ventanaInterna_InformacionCliente.setVisible(true);
        ventanaInterna_InformacionCliente.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                ventanaInterna_InformacionClienteInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        ventanaInterna_InformacionCliente.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        informacionCliente_nombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ventanaInterna_InformacionCliente.getContentPane().add(informacionCliente_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 720, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("ESTADO:");
        ventanaInterna_InformacionCliente.getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 90, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("CÓDIGO:");
        ventanaInterna_InformacionCliente.getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 80, 30));

        informacionCliente_codigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ventanaInterna_InformacionCliente.getContentPane().add(informacionCliente_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 720, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("INFORMACION DEL CLIENTE");
        ventanaInterna_InformacionCliente.getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 310, 30));

        tabla_InformacionRecobroClientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla_InformacionRecobroClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_InformacionRecobroClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_InformacionRecobroClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_InformacionRecobroClientes);

        ventanaInterna_InformacionCliente.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 182, 910, 370));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("NOMBRE:");
        ventanaInterna_InformacionCliente.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 90, 30));

        informacionCliente_estado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ventanaInterna_InformacionCliente.getContentPane().add(informacionCliente_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 720, 30));

        add(ventanaInterna_InformacionCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1180, 670));

        btn_consultar_cliente.setBackground(new java.awt.Color(255, 255, 255));
        btn_consultar_cliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_consultar_cliente.setText("CONSULTAR");
        btn_consultar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultar_clienteActionPerformed(evt);
            }
        });
        add(btn_consultar_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 140, 30));

        btn_cancelar_cliente1.setBackground(new java.awt.Color(255, 255, 255));
        btn_cancelar_cliente1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cancelar_cliente1.setText("CANCELAR");
        btn_cancelar_cliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_cliente1ActionPerformed(evt);
            }
        });
        add(btn_cancelar_cliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 140, 30));
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 310, 30));

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
        tabla_clientes.setComponentPopupMenu(Seleccionar);
        tabla_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_clientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_clientes);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 1300, 450));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("CONSULTAR CLIENTES");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 310, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_consultar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultar_clienteActionPerformed
        try {
            tabla_ListarClientes(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Cliente_Consultar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_consultar_clienteActionPerformed

    private void btn_cancelar_cliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_cliente1ActionPerformed
        valorBusqueda.setText("");
    }//GEN-LAST:event_btn_cancelar_cliente1ActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO lo del clik derechoo
        int fila1;
        try{
            fila1=tabla_clientes.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                modeloCliente=(DefaultTableModel)tabla_clientes.getModel();
                informacionCliente_codigo.setText((String)modeloCliente.getValueAt(fila1, 0));
                informacionCliente_nombre.setText((String)modeloCliente.getValueAt(fila1, 1));
                informacionCliente_estado.setText((String)modeloCliente.getValueAt(fila1, 2));     
                tabla_consultarHistoricoRecobros((String)modeloCliente.getValueAt(fila1, 0));
                ventanaInterna_InformacionCliente.show(true);
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void ventanaInterna_InformacionClienteInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_ventanaInterna_InformacionClienteInternalFrameClosed
        
    }//GEN-LAST:event_ventanaInterna_InformacionClienteInternalFrameClosed

    private void tabla_InformacionRecobroClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_InformacionRecobroClientesMouseClicked
        
    }//GEN-LAST:event_tabla_InformacionRecobroClientesMouseClicked

    private void tabla_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_clientesMouseClicked
        if (evt.getClickCount() == 2) {
            // TODO lo del clik derechoo
            int fila1;
            try{
                fila1=tabla_clientes.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    modeloCliente=(DefaultTableModel)tabla_clientes.getModel();
                    informacionCliente_codigo.setText((String)modeloCliente.getValueAt(fila1, 0));
                    informacionCliente_nombre.setText((String)modeloCliente.getValueAt(fila1, 1));
                    informacionCliente_estado.setText((String)modeloCliente.getValueAt(fila1, 2));     
                    tabla_consultarHistoricoRecobros((String)modeloCliente.getValueAt(fila1, 0));
                    ventanaInterna_InformacionCliente.show(true);
                }
            }catch(Exception e){
            }
        }
    }//GEN-LAST:event_tabla_clientesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JButton btn_cancelar_cliente1;
    private javax.swing.JButton btn_consultar_cliente;
    private javax.swing.JLabel informacionCliente_codigo;
    private javax.swing.JLabel informacionCliente_estado;
    private javax.swing.JLabel informacionCliente_nombre;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla_InformacionRecobroClientes;
    private javax.swing.JTable tabla_clientes;
    private javax.swing.JTextField valorBusqueda;
    private javax.swing.JInternalFrame ventanaInterna_InformacionCliente;
    // End of variables declaration//GEN-END:variables
    public void tabla_ListarClientes(String valorConsulta) throws SQLException{
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
    public void tabla_consultarHistoricoRecobros(String valorConsulta) throws SQLException{
        //Eliminamos la Busqueda Actual#######################################################
        DefaultTableModel md =(DefaultTableModel)tabla_InformacionRecobroClientes.getModel();
        int CantEliminar= tabla_InformacionRecobroClientes.getRowCount() -1;
        for(int i =CantEliminar; i>=0; i--){
            md.removeRow(i);
        }
        
        
        ControlDB_Cliente  controlDB_Cliente = new ControlDB_Cliente(tipoConexion);        
        registroInformacionHistoricoRecobro = new String[3]; 
        modeloInformacionHistoricoRecobro = new DefaultTableModel(null, tituloInformacionHistoricoRecobro);  
        ArrayList<String> historicoRecobro=controlDB_Cliente.buscarHistorialRecobros(valorConsulta);
        for(int i =0; i< historicoRecobro.size(); i++){
            String []data= historicoRecobro.get(i).split("#");      
            registroInformacionHistoricoRecobro[0]=data[1];//Valor del recobro
            registroInformacionHistoricoRecobro[1]=data[2];//Fecha de registro del recobro
            registroInformacionHistoricoRecobro[2]=data[0];//Usuario quien rgistro recobro
            modeloInformacionHistoricoRecobro.addRow(registroInformacionHistoricoRecobro); 
        }
        tabla_InformacionRecobroClientes.setModel(modeloInformacionHistoricoRecobro);
    }
    
}   
