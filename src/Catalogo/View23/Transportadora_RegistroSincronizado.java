package Catalogo.View23;
 
import Catalogo.Controller.ControlDB_Transportadora;
import Catalogo.Model.Transportadora;
import Sistema.Model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
 
public final class Transportadora_RegistroSincronizado extends javax.swing.JPanel {
    DefaultTableModel modelo;
    String [] titulo= {"Código", "Nit","Nombre", "Observacion","Estado"};
    String[]  registro;  
    Usuario user;
    ArrayList<Transportadora> listadoTransportadora;
    double  contador=0;
    private String tipoConexion;
    public Transportadora_RegistroSincronizado(Usuario u,String tipoConexion) {
        initComponents();
        user= u;
        this.tipoConexion= tipoConexion;
        try {
            tabla_ListarClientes("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los clientes");
            Logger.getLogger(Transportadora_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
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
        tabla = new javax.swing.JTable();
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
        add(btn_consultar_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 140, 30));

        btn_cancelar_cliente1.setBackground(new java.awt.Color(255, 255, 255));
        btn_cancelar_cliente1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cancelar_cliente1.setText("CANCELAR");
        btn_cancelar_cliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_cliente1ActionPerformed(evt);
            }
        });
        add(btn_cancelar_cliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 10, 140, 30));
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 310, 30));

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
        tabla.setComponentPopupMenu(Seleccionar);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 1300, 460));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("CONSULTAR TRANSPORTADORAS EN CCARGA GP");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 480, 30));

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
            Logger.getLogger(Transportadora_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_consultar_clienteActionPerformed

    private void btn_cancelar_cliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_cliente1ActionPerformed
        valorBusqueda.setText("");
    }//GEN-LAST:event_btn_cancelar_cliente1ActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO lo del clik derechoo
        int fila1;
        try{
            fila1=tabla.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                modelo=(DefaultTableModel)tabla.getModel();
                Transportadora tr = new Transportadora();
                tr.setCodigo((String)modelo.getValueAt(fila1, 0));
                tr.setNit((String)modelo.getValueAt(fila1, 1));
                tr.setDescripcion((String)modelo.getValueAt(fila1, 2));
                tr.setObservacion((String)modelo.getValueAt(fila1, 3));
                tr.setEstado((String)modelo.getValueAt(fila1, 4));
                int retorno=new ControlDB_Transportadora(tipoConexion).registrar(tr, user);
                if(retorno ==1){
                    JOptionPane.showMessageDialog(null, "Se sincronizó la transportadora de forma exitosa");
                }else{
                    JOptionPane.showMessageDialog(null, "No se pudo sincronizar la transportadora valide si ya existe en el sistema");
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() == 2) {
            // TODO lo del clik derechoo
            int fila1;
            try{
                fila1=tabla.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    modelo=(DefaultTableModel)tabla.getModel();
                    Transportadora tr = new Transportadora();
                    tr.setCodigo((String)modelo.getValueAt(fila1, 0));
                    tr.setNit((String)modelo.getValueAt(fila1, 1));
                    tr.setDescripcion((String)modelo.getValueAt(fila1, 2));
                    tr.setObservacion((String)modelo.getValueAt(fila1, 3));
                    tr.setEstado((String)modelo.getValueAt(fila1, 4));
                    int retorno=new ControlDB_Transportadora(tipoConexion).registrar(tr, user);
                    if(retorno ==1){
                        JOptionPane.showMessageDialog(null, "Se sincronizó la transportadora de forma exitosa");
                    }else{
                        JOptionPane.showMessageDialog(null, "No se pudo sincronizar la transportadora valide si ya existe en el sistema");
                    }
                }
            }catch(Exception e){
            }
        }
    }//GEN-LAST:event_tablaMouseClicked
    
    private void sincronizarCcargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sincronizarCcargaActionPerformed
         Worker_Transportadora  worker = new Worker_Transportadora (jProgressBar1,listadoTransportadora,user,tipoConexion);
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
    private javax.swing.JTable tabla;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_ListarClientes(String valorConsulta) throws SQLException{
        ControlDB_Transportadora  controlDB_Objeto = new ControlDB_Transportadora(tipoConexion);        
        registro = new String[5]; 
        modelo = new DefaultTableModel(null, titulo);  
        //ArrayList<Cliente> listadoCliente=controlDB_Cliente.buscar(valorConsulta);
        listadoTransportadora=controlDB_Objeto.buscarTransportadoraGP(valorConsulta);
        for(int i =0; i< listadoTransportadora.size(); i++){
            registro[0]=""+listadoTransportadora.get(i).getCodigo();
            registro[1]=""+listadoTransportadora.get(i).getNit();
            registro[2]=""+listadoTransportadora.get(i).getDescripcion();
            registro[3]=""+listadoTransportadora.get(i).getObservacion();
            //registro[4]=""+listadoTransportadora.get(i).getEstado(); 
            if(listadoTransportadora.get(i).getEstado()== null){
                registro[4]="NULL";  
            }else{
                if(listadoTransportadora.get(i).getEstado().equalsIgnoreCase("1")){
                    registro[4]="ACTIVO";
                }else{
                    if(listadoTransportadora.get(i).getEstado().equalsIgnoreCase("0")){
                        registro[4]="INACTIVO";
                    }else{
                        registro[4]=""+listadoTransportadora.get(i).getEstado(); 
                    }
                }
            }  
            modelo.addRow(registro);
            tabla.setModel(modelo);
        }
    }  
}   
