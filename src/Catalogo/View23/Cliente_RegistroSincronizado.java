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
 
public final class Cliente_RegistroSincronizado extends javax.swing.JPanel {

    DefaultTableModel modeloCliente;
    DefaultTableModel modeloInformacionHistoricoRecobro;
    String [] tituloCliente= {"Código", "Nombre","Estado", "Valor Recobro"};
    String [] tituloInformacionHistoricoRecobro= {"Valor del recobro", "Fecha de Registro del valor del recobro","Usuario Quien registró recobro"};
    String[]  registroCliente;  
    String[]  registroInformacionHistoricoRecobro;  
    Usuario user;
    ArrayList<Cliente> listadoCliente;
    double  contador=0;
    private String tipoConexion;
    
    public Cliente_RegistroSincronizado(Usuario u,String tipoConexion) {
        initComponents();
        user= u;
        this.tipoConexion= tipoConexion;
        try {
            tabla_ListarClientes("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los clientes");
            Logger.getLogger(Cliente_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        btn_consultar_cliente = new javax.swing.JButton();
        btn_cancelar_cliente1 = new javax.swing.JButton();
        valorBusqueda = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_clientes = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        sincronizarCcarga = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();

        Editar.setText("Sincronizar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_consultar_cliente.setBackground(new java.awt.Color(255, 255, 255));
        btn_consultar_cliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_consultar_cliente.setText("CONSULTAR");
        btn_consultar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultar_clienteActionPerformed(evt);
            }
        });
        add(btn_consultar_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 140, 30));

        btn_cancelar_cliente1.setBackground(new java.awt.Color(255, 255, 255));
        btn_cancelar_cliente1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cancelar_cliente1.setText("CANCELAR");
        btn_cancelar_cliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_cliente1ActionPerformed(evt);
            }
        });
        add(btn_cancelar_cliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, 140, 30));
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 310, 30));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 1300, 460));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("CONSULTAR CLIENTES EN CCARGA GP");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 370, 30));

        sincronizarCcarga.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sincronizarCcarga.setText("SINCRONIZAR CON CCARGA");
        sincronizarCcarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sincronizarCcargaActionPerformed(evt);
            }
        });
        add(sincronizarCcarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 250, 30));

        jProgressBar1.setStringPainted(true);
        add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 710, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_consultar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultar_clienteActionPerformed
        try {
            tabla_ListarClientes(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Cliente_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
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
                Cliente c = new Cliente();
                c.setCodigo((String)modeloCliente.getValueAt(fila1, 0));
                c.setDescripcion((String)modeloCliente.getValueAt(fila1, 1));
                c.setEstado((String)modeloCliente.getValueAt(fila1, 2));
                c.setValorRecobro(Integer.parseInt((String)modeloCliente.getValueAt(fila1, 3)));
                int retorno=new ControlDB_Cliente(tipoConexion).registrar(c, user);
                if(retorno ==1){
                    JOptionPane.showMessageDialog(null, "Se sincronizó el cliente de forma exitosa");
                }else{
                    JOptionPane.showMessageDialog(null, "No se pudo sincronizar el cliente valide si ya existe en el sistema");
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_EditarActionPerformed

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
                    Cliente c = new Cliente();
                    c.setCodigo((String)modeloCliente.getValueAt(fila1, 0));
                    c.setDescripcion((String)modeloCliente.getValueAt(fila1, 1));
                    c.setEstado((String)modeloCliente.getValueAt(fila1, 2));
                    c.setValorRecobro(Integer.parseInt((String)modeloCliente.getValueAt(fila1, 3)));
                    int retorno=new ControlDB_Cliente(tipoConexion).registrar(c, user);
                    if(retorno ==1){
                        JOptionPane.showMessageDialog(null, "Se sincronizó el cliente de forma exitosa");
                    }else{
                        JOptionPane.showMessageDialog(null, "No se pudo sincronizar el cliente valide si ya existe en el sistema");
                    }
                }
            }catch(Exception e){
            }
        }
    }//GEN-LAST:event_tabla_clientesMouseClicked
    
    private void sincronizarCcargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sincronizarCcargaActionPerformed
        Worker_Cliente  worker = new Worker_Cliente (jProgressBar1,listadoCliente,user,tipoConexion);
        worker.execute();
        
    }//GEN-LAST:event_sincronizarCcargaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JButton btn_cancelar_cliente1;
    private javax.swing.JButton btn_consultar_cliente;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton sincronizarCcarga;
    private javax.swing.JTable tabla_clientes;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_ListarClientes(String valorConsulta) throws SQLException{
        ControlDB_Cliente  controlDB_Cliente = new ControlDB_Cliente(tipoConexion);        
        registroCliente = new String[4]; 
        modeloCliente = new DefaultTableModel(null, tituloCliente);  
        //ArrayList<Cliente> listadoCliente=controlDB_Cliente.buscar(valorConsulta);
        listadoCliente=controlDB_Cliente.buscarClientesGP(valorConsulta);
        for(int i =0; i< listadoCliente.size(); i++){
            registroCliente[0]=""+listadoCliente.get(i).getCodigo();
            registroCliente[1]=""+listadoCliente.get(i).getDescripcion();
            
            if(listadoCliente.get(i).getEstado()== null){
                registroCliente[2]="NULL";  
            }else{
                if(listadoCliente.get(i).getEstado().equalsIgnoreCase("1")){
                    registroCliente[2]="ACTIVO";
                }else{
                    if(listadoCliente.get(i).getEstado().equalsIgnoreCase("0")){
                        registroCliente[2]="INACTIVO";
                    }else{
                        registroCliente[2]=""+listadoCliente.get(i).getEstado(); 
                    }
                }
            }  
            registroCliente[3]=""+listadoCliente.get(i).getValorRecobro();  
            modeloCliente.addRow(registroCliente);
            tabla_clientes.setModel(modeloCliente);
        }
    }  
}   
