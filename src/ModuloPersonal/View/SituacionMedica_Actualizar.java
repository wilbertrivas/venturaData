package ModuloPersonal.View;
  

import Consumo.View.Unidad_Registrar;
import ModuloPersonal.Controller.ControlDB_SituacionMedica;
import ModuloPersonal.Model.SituacionMedica;
import Sistema.Model.Usuario;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class SituacionMedica_Actualizar extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;
    ArrayList<SituacionMedica> listadoSituacionnMedica = null;
    public SituacionMedica_Actualizar(Usuario us, String tipoConexion) {
        user=us;
        this.tipoConexion = tipoConexion;
        initComponents();
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las situaciones medicas");
            Logger.getLogger(Unidad_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        nombre.setEnabled(false);
        estado.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
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

        Editar.setText("Modificar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });
        add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 310, 30));

        codigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 310, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("ACTUALIZACI??N DE SITUACI??N M??DICA");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 550, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Estado:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 80, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 310, 30));

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
        add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 140, 30));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 140, 30));

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
        tabla.setColumnSelectionAllowed(true);
        tabla.setComponentPopupMenu(Seleccionar);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 620, 470));

        valorBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorBusquedaActionPerformed(evt);
            }
        });
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 280, 30));

        btnConsultar.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultar.setText("CONSULTAR");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, 140, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Nombre:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 80, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("C??digo:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 80, 30));

        alerta_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_nombre.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 380, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        codigo.setText("");
        nombre.setText("");
        estado.setSelectedIndex(0);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if(codigo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Error.. Se debe cargar una situaci??n m??dica", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }else{
            if(nombre.getText().equals("")){
                alerta_nombre.setText("El nombre de la situaci??n m??dica no puede estar vacio");
            }else{
                SituacionMedica situacionMedica = new SituacionMedica();
                situacionMedica.setCodigo(codigo.getText());
                situacionMedica.setDescripcion(nombre.getText());
                //Validamos si selecciono activo o inactivo
                if(estado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                    situacionMedica.setEstado("1");
                }else{
                    situacionMedica.setEstado("0");
                }
                try {
                    if(!new ControlDB_SituacionMedica(tipoConexion).validarExistenciaActualizar(situacionMedica)){
                        int respuesta=new ControlDB_SituacionMedica(tipoConexion).actualizar(situacionMedica, user);
                        if(respuesta==1){
                            JOptionPane.showMessageDialog(null, "Se actualiz?? la situaci??n m??dica de forma exitosa");
                            nombre.setText("");
                            estado.setSelectedIndex(0);
                            tabla_Listar("");
                            codigo.setText("");
                            nombre.setEnabled(false);
                            estado.setEnabled(false);
                        }else{
                            if(respuesta==0){
                                JOptionPane.showMessageDialog(null, "No se pudo actualizar la situaci??n m??dica, valide los datos ingresados");
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Ya existe una situaci??n m??dica con ese mismo nombre, valide informaci??n", "Advertencia",JOptionPane.ERROR_MESSAGE);
                    }
                    
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Error al registrar la situaci??n m??dica");
                    Logger.getLogger(SituacionMedica_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(SituacionMedica_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(SituacionMedica_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SocketException ex) {
                    Logger.getLogger(SituacionMedica_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        try {
            tabla_Listar(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(SituacionMedica_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO lo del clik derechoo
        int fila1;
        try{
            fila1=tabla.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                codigo.setText(listadoSituacionnMedica.get(fila1).getCodigo());
                nombre.setText(listadoSituacionnMedica.get(fila1).getDescripcion());
                if(listadoSituacionnMedica.get(fila1).getEstado().equals("ACTIVO")){
                    estado.setSelectedIndex(0);
                }else{
                    estado.setSelectedIndex(1);
                }
                nombre.setEnabled(true);
                estado.setEnabled(true);
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() == 2) {
            int fila1;
            try{
                fila1=tabla.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    codigo.setText(listadoSituacionnMedica.get(fila1).getCodigo());
                    nombre.setText(listadoSituacionnMedica.get(fila1).getDescripcion());
                    if(listadoSituacionnMedica.get(fila1).getEstado().equals("ACTIVO")){
                        estado.setSelectedIndex(0);
                    }else{
                        estado.setSelectedIndex(1);
                    }
                    nombre.setEnabled(true);
                    estado.setEnabled(true);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }   
    }//GEN-LAST:event_tablaMouseClicked

    private void btnActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseExited
        alerta_nombre.setText("");
    }//GEN-LAST:event_btnActualizarMouseExited

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed

    private void valorBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valorBusquedaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu Seleccionar;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"C??digo", "Nombre","Estado"});  
        listadoSituacionnMedica=new ControlDB_SituacionMedica(tipoConexion).buscar(valorConsulta);
        for (SituacionMedica object : listadoSituacionnMedica) {
            String[] registro = new String[3];
            registro[0] = "" + object.getCodigo();
            registro[1] = "" + object.getDescripcion();
            registro[2] = "" + object.getEstado();
            modelo.addRow(registro);      
        }
        tabla.setModel(modelo);
    }

}
