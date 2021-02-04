package Consumo.View;
  
import Consumo.Controller2.ControlDB_Unidad;
import Consumo.Model2.Unidad;
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

public final class Unidad_Actualizar extends javax.swing.JPanel {
    
    DefaultTableModel modeloUnidad;
    String [] tituloUnidad= {"Unidad_Codigo", "Unidad_Nombre","Unidad_Estado"};
    String[]  registroUnidad;   
    Usuario user;
    private String tipoConexion;
    public Unidad_Actualizar(Usuario us, String tipoConexion) {
        
        initComponents();
        user=us;
        this.tipoConexion= tipoConexion;
        try {
            tabla_ListarUnidades("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las unidades");
            Logger.getLogger(Unidad_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        unidad_nombre = new javax.swing.JTextField();
        unidad_codigo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        unidad_estado = new javax.swing.JComboBox<>();
        btn_actualizar_unidad = new javax.swing.JButton();
        btn_cancelar_unidad = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_unidades = new javax.swing.JTable();
        valorBusqueda = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
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
        add(unidad_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 310, 30));

        unidad_codigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(unidad_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 310, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("ACTUALIZACIÓN DE UNIDADES");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 310, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Estado:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 80, 30));

        unidad_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        unidad_estado.setToolTipText("");
        add(unidad_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 310, 30));

        btn_actualizar_unidad.setBackground(new java.awt.Color(255, 255, 255));
        btn_actualizar_unidad.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_actualizar_unidad.setText("ACTUALIZAR");
        btn_actualizar_unidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_actualizar_unidadMouseExited(evt);
            }
        });
        btn_actualizar_unidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizar_unidadActionPerformed(evt);
            }
        });
        add(btn_actualizar_unidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 140, 30));

        btn_cancelar_unidad.setBackground(new java.awt.Color(255, 255, 255));
        btn_cancelar_unidad.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cancelar_unidad.setText("CANCELAR");
        btn_cancelar_unidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_unidadActionPerformed(evt);
            }
        });
        add(btn_cancelar_unidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 140, 30));

        tabla_unidades = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla_unidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla_unidades.setComponentPopupMenu(Seleccionar);
        tabla_unidades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_unidadesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_unidades);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 620, 470));
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 280, 30));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("CONSULTAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, 140, 30));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("GENERAR REPORTE");
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 60, 160, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Nombre:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 80, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Código:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 80, 30));

        alerta_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_nombre.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 380, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cancelar_unidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_unidadActionPerformed
        unidad_nombre.setText("");
        unidad_estado.setSelectedIndex(0);
    }//GEN-LAST:event_btn_cancelar_unidadActionPerformed

    private void btn_actualizar_unidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizar_unidadActionPerformed
        if(unidad_nombre.getText().equals("")){
            alerta_nombre.setText("El nombre de la unidad no puede estar vacio");
        }else{
            ControlDB_Unidad  controlDB_Unidad = new ControlDB_Unidad(tipoConexion);
            Unidad unidad = new Unidad();
            unidad.setCodigo(Integer.parseInt(unidad_codigo.getText()));
            unidad.setDescripcion(unidad_nombre.getText());
            //Validamos si selecciono activo o inactivo
            if(unidad_estado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                unidad.setEstado("1");
            }else{
                unidad.setEstado("0");
            }
            //Procedemos a actualizar la unidad en la base de datos
            try {
                if(!controlDB_Unidad.validarExistenciaActualizar(unidad)){
                    int respuesta=controlDB_Unidad.actualizar(unidad, user);
                    if(respuesta==1){
                        JOptionPane.showMessageDialog(null, "Se actualizo la unidad");
                        unidad_nombre.setText("");
                        unidad_estado.setSelectedIndex(0);
                        tabla_ListarUnidades("");
                    }else{
                        if(respuesta==0){
                            JOptionPane.showMessageDialog(null, "No se pudo actualizar la unidad, valide los datos ingresados");
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Ya existe en el sistema una unidad con el mismo Nombre, debe cambiar el nombre de la unidad", "Adventencia", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error al registrar la unidad");
                Logger.getLogger(Insumo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Insumo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Unidad_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SocketException ex) {
                Logger.getLogger(Unidad_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_actualizar_unidadActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            tabla_ListarUnidades(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Unidad_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO lo del clik derechoo
        int fila1;
        try{
            fila1=tabla_unidades.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                modeloUnidad=(DefaultTableModel)tabla_unidades.getModel();
                unidad_codigo.setText((String)modeloUnidad.getValueAt(fila1, 0));
                unidad_nombre.setText((String)modeloUnidad.getValueAt(fila1, 1));
                String estado=(String)modeloUnidad.getValueAt(fila1, 2);
                if(estado.equalsIgnoreCase("ACTIVO")){
                    unidad_estado.setSelectedIndex(0);
                }else{
                    unidad_estado.setSelectedIndex(1);
                }
                unidad_codigo.setEnabled(false);
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void tabla_unidadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_unidadesMouseClicked
        if (evt.getClickCount() == 2) {
            // TODO lo del clik derechoo
            int fila1;
            try{
                fila1=tabla_unidades.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    modeloUnidad=(DefaultTableModel)tabla_unidades.getModel();
                    unidad_codigo.setText((String)modeloUnidad.getValueAt(fila1, 0));
                    unidad_nombre.setText((String)modeloUnidad.getValueAt(fila1, 1));
                    String estado=(String)modeloUnidad.getValueAt(fila1, 2);
                    if(estado.equalsIgnoreCase("ACTIVO")){
                        unidad_estado.setSelectedIndex(0);
                    }else{
                        unidad_estado.setSelectedIndex(1);
                    }
                    unidad_codigo.setEnabled(false);
                }
            }catch(Exception e){
        }
        }
         
    }//GEN-LAST:event_tabla_unidadesMouseClicked

    private void btn_actualizar_unidadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_actualizar_unidadMouseExited
        alerta_nombre.setText("");
    }//GEN-LAST:event_btn_actualizar_unidadMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JLabel alerta_nombre;
    private javax.swing.JButton btn_actualizar_unidad;
    private javax.swing.JButton btn_cancelar_unidad;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_unidades;
    private javax.swing.JLabel unidad_codigo;
    private javax.swing.JComboBox<String> unidad_estado;
    private javax.swing.JTextField unidad_nombre;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_ListarUnidades(String valorConsulta) throws SQLException{
        ControlDB_Unidad  controlDB_Unidad = new ControlDB_Unidad(tipoConexion);        
        registroUnidad = new String[3]; 
        modeloUnidad = new DefaultTableModel(null, tituloUnidad);  
        ArrayList<Unidad> listadoUnidad=controlDB_Unidad.buscar(valorConsulta);
        for(int i =0; i< listadoUnidad.size(); i++){
            registroUnidad[0]=""+listadoUnidad.get(i).getCodigo();
            registroUnidad[1]=""+listadoUnidad.get(i).getDescripcion();
            registroUnidad[2]=""+listadoUnidad.get(i).getEstado();            
            modeloUnidad.addRow(registroUnidad);
            tabla_unidades.setModel(modeloUnidad);
        }
    }

}
