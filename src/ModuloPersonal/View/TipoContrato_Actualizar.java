package ModuloPersonal.View;

import Consumo.View.Unidad_Registrar;

import ModuloPersonal.Controller.ControlDB_TipoContrato;
import ModuloPersonal.Model.TipoContrato;
import Sistema.Model.Usuario;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class TipoContrato_Actualizar extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;
    ArrayList<TipoContrato> listadosTiposContrato = null;

    public TipoContrato_Actualizar(Usuario us, String tipoConexion) {
        user = us;
        this.tipoConexion = tipoConexion;
        initComponents();
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los tipos de contrato");
            Logger.getLogger(Unidad_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        nombre.setEnabled(false);
        estado.setEnabled(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        seleccionar1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        nombre = new javax.swing.JTextField();
        codigo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox<>();
        btnActualizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        valorBusqueda = new javax.swing.JTextField();
        btnConsultar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        alerta_nombre = new javax.swing.JLabel();

        jMenuItem1.setText("seleccionar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        seleccionar1.add(jMenuItem1);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 310, 30));

        codigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 310, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("ACTUALIZAR TIPO DE CONTRATO");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 550, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Estado:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 80, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        jPanel1.add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 310, 30));

        btnActualizar.setBackground(new java.awt.Color(255, 255, 255));
        btnActualizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnActualizarMouseExited(evt);
            }
        });
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 140, 30));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 140, 30));

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
        tabla.setComponentPopupMenu(seleccionar1);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 620, 470));
        jPanel1.add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 280, 30));

        btnConsultar.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultar.setText("CONSULTAR");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        jPanel1.add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, 140, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Nombre:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 80, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Código:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 80, 30));

        alerta_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_nombre.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(alerta_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 380, 20));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 3, 1204, 610));
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseExited
        alerta_nombre.setText("");
    }//GEN-LAST:event_btnActualizarMouseExited

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if(codigo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Error.. Se debe cargar un tipo de contrato", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }else{
            if(nombre.getText().equals("")){
                alerta_nombre.setText("El nombre del tipo de contrato no puede estar vacio");
            }else{
                TipoContrato tipoContrato = new TipoContrato();
                tipoContrato.setCodigo(codigo.getText());
                tipoContrato.setDescripcion(nombre.getText());
                //Validamos si selecciono activo o inactivo
                if(estado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                    tipoContrato.setEstado("1");
                }else{
                    tipoContrato.setEstado("0");
                }
                try {
                    if(!new ControlDB_TipoContrato(tipoConexion).validarExistenciaActualizar(tipoContrato)){
                        int respuesta=new ControlDB_TipoContrato(tipoConexion).actualizar(tipoContrato, user);
                        if(respuesta==1){
                            JOptionPane.showMessageDialog(null, "Se actualizó el tipo de contrato de forma exitosa");
                            nombre.setText("");
                            estado.setSelectedIndex(0);
                            tabla_Listar("");
                            codigo.setText("");
                            nombre.setEnabled(false);
                            estado.setEnabled(false);
                        }else{
                            if(respuesta==0){
                                JOptionPane.showMessageDialog(null, "No se pudo actualizar el tipo de contrato, valide los datos ingresados");
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Ya existe una tipo de contrato con ese mismo nombre, valide información", "Advertencia",JOptionPane.ERROR_MESSAGE);
                    }

                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Error al registrar el tipo de contrato");
                    Logger.getLogger(TipoContrato_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException | UnknownHostException | SocketException ex) {
                    Logger.getLogger(TipoContrato_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        codigo.setText("");
        nombre.setText("");
        estado.setSelectedIndex(0);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() == 2) {
            int fila1;
            try{
                fila1=tabla.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    codigo.setText(listadosTiposContrato.get(fila1).getCodigo());
                    nombre.setText(listadosTiposContrato.get(fila1).getDescripcion());
                    if(listadosTiposContrato.get(fila1).getEstado().equals("ACTIVO")){
                        estado.setSelectedIndex(0);
                    }else{
                        estado.setSelectedIndex(1);
                    }
                    nombre.setEnabled(true);
                    estado.setEnabled(true);
                }
            }catch(HeadlessException e){
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        try {
            tabla_Listar(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(TipoContrato_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int fila1;
        try {
            fila1 = tabla.getSelectedRow();
            if (fila1 == -1) {
                JOptionPane.showMessageDialog(null, "no se ha seleccionando ninguna fila");
            } else {
                codigo.setText(listadosTiposContrato.get(fila1).getCodigo());
                nombre.setText(listadosTiposContrato.get(fila1).getDescripcion());
                if (listadosTiposContrato.get(fila1).getEstado().equals("ACTIVO")) {
                    estado.setSelectedIndex(0);
                } else {
                    estado.setSelectedIndex(1);
                }
                nombre.setEnabled(true);
                estado.setEnabled(true);
            }
        } catch (HeadlessException e) {
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alerta_nombre;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JLabel codigo;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre;
    private javax.swing.JPopupMenu seleccionar1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables

public void tabla_Listar(String valorConsulta) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "Nombre","Estado"});  
        listadosTiposContrato=new ControlDB_TipoContrato(tipoConexion).buscar(valorConsulta);
        for (TipoContrato object : listadosTiposContrato) {
            String[] registro = new String[3];
            registro[0] = "" + object.getCodigo();
            registro[1] = "" + object.getDescripcion();
            registro[2] = "" + object.getEstado();
            modelo.addRow(registro);      
        }
        tabla.setModel(modelo);
    }

}
