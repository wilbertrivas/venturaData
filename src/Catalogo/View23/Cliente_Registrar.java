package Catalogo.View23;
 
import Catalogo.Controller.ControlDB_Cliente;
import Catalogo.Model.Cliente;
import Sistema.Model.Usuario;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class Cliente_Registrar extends javax.swing.JPanel {

    DefaultTableModel modeloCliente;
    String [] tituloCliente= {"Código", "Nombre","Estado", "Valor Recobro"};
    String[]  registroCliente;  
    Usuario user;
    private String tipoConexion;
    public Cliente_Registrar(Usuario u, String tipoConexion) {
        initComponents();
        user= u;
        this.tipoConexion= tipoConexion;
        try {
            tabla_ListarClientes("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los clientes");
            Logger.getLogger(Cliente_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cliente_nombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cliente_estado = new javax.swing.JComboBox<>();
        btn_registrar_cliente = new javax.swing.JButton();
        btn_cancelar_cliente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_clientes = new javax.swing.JTable();
        cliente_valorRecobro = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        alerta_nombre = new javax.swing.JLabel();
        alerta_recobro = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cliente_codigo = new javax.swing.JTextField();
        alerta_codigo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(cliente_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 310, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Código:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 80, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("REGISTRAR CLIENTE");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 310, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Valor Recobro:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 120, 30));

        cliente_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        cliente_estado.setToolTipText("");
        add(cliente_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 310, 30));

        btn_registrar_cliente.setBackground(new java.awt.Color(255, 255, 255));
        btn_registrar_cliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_registrar_cliente.setText("REGISTRAR");
        btn_registrar_cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_registrar_clienteMouseExited(evt);
            }
        });
        btn_registrar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrar_clienteActionPerformed(evt);
            }
        });
        add(btn_registrar_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 140, 30));

        btn_cancelar_cliente.setBackground(new java.awt.Color(255, 255, 255));
        btn_cancelar_cliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cancelar_cliente.setText("CANCELAR");
        btn_cancelar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_clienteActionPerformed(evt);
            }
        });
        add(btn_cancelar_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 140, 30));

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
        jScrollPane1.setViewportView(tabla_clientes);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 940, 270));
        add(cliente_valorRecobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 310, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Estado:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 80, 30));

        alerta_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_nombre.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 370, 20));

        alerta_recobro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_recobro.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_recobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 370, 20));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Nombre:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 80, 30));
        add(cliente_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 310, 30));

        alerta_codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_codigo.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 370, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_registrar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrar_clienteActionPerformed
        if(cliente_codigo.getText().isEmpty()){
            alerta_codigo.setText("El código del cliente no puede estar vacio");
        }else{
            if(cliente_nombre.getText().equals("")){
                alerta_nombre.setText("El nombre del cliente no puede estar vacio");
            }else{
                try{
                    Integer.parseInt(cliente_valorRecobro.getText());

                    ControlDB_Cliente  controlDB_Cliente = new ControlDB_Cliente(tipoConexion);
                    Cliente cliente = new Cliente();
                    cliente.setCodigo(cliente_codigo.getText());
                    cliente.setDescripcion(cliente_nombre.getText());
                    cliente.setValorRecobro(Integer.parseInt(cliente_valorRecobro.getText()));
                    //Validamos si selecciono activo o inactivo
                    if(cliente_estado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                        cliente.setEstado("1");
                    }else{
                        cliente.setEstado("0");
                    }
                    //Procedemos a registrar la unidad en la base de datos
                    try {
                        if(controlDB_Cliente.validarExistencia(cliente)){
                            JOptionPane.showMessageDialog(null, "El cliente ya se encuentra registrado en el sistema");
                        }else{
                            int respuesta=controlDB_Cliente.registrar(cliente,user);
                            if(respuesta==1){
                                JOptionPane.showMessageDialog(null, "Se registro el cliente de manera exitosa");
                                cliente_codigo.setText("");
                                cliente_nombre.setText("");
                                cliente_valorRecobro.setText("");
                                cliente_estado.setSelectedIndex(0);
                                tabla_ListarClientes("");
                            }else{
                                if(respuesta==0){
                                    JOptionPane.showMessageDialog(null, "No se pudo registrar el cliente, valide datos");
                                }
                            }
                        }
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "Error al registrar el cliente");
                        Logger.getLogger(Cliente_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Cliente_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }catch(Exception e){
                    alerta_recobro.setText("Error!!.. El valor del recobro debe ser númerico");
                }
            }
        }  
    }//GEN-LAST:event_btn_registrar_clienteActionPerformed

    private void btn_cancelar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_clienteActionPerformed
        cliente_codigo.setText("");
        cliente_nombre.setText("");
        cliente_estado.setSelectedIndex(0);
    }//GEN-LAST:event_btn_cancelar_clienteActionPerformed

    private void btn_registrar_clienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_registrar_clienteMouseExited
        alerta_codigo.setText("");
        alerta_nombre.setText("");
        alerta_recobro.setText("");
    }//GEN-LAST:event_btn_registrar_clienteMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alerta_codigo;
    private javax.swing.JLabel alerta_nombre;
    private javax.swing.JLabel alerta_recobro;
    private javax.swing.JButton btn_cancelar_cliente;
    private javax.swing.JButton btn_registrar_cliente;
    private javax.swing.JTextField cliente_codigo;
    private javax.swing.JComboBox<String> cliente_estado;
    private javax.swing.JTextField cliente_nombre;
    private javax.swing.JTextField cliente_valorRecobro;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_clientes;
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

}
