package Consumo.View;
  
import Consumo.Controller.ControlDB_Unidad;
import Consumo.Model.Unidad;
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

public final class Unidad_Registrar extends javax.swing.JPanel {

    DefaultTableModel modeloUnidad;
    String [] tituloUnidad= {"Unidad_Codigo", "Unidad_Nombre","Unidad_Estado"};
    String[]  registroUnidad;   
    Usuario user;
    private String tipoConexion;
    
    
    public Unidad_Registrar(Usuario us, String tipoConexion) {
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

        unidad_nombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        unidad_estado = new javax.swing.JComboBox<>();
        btn_registrar_unidad = new javax.swing.JButton();
        btn_cancelar_unidad = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_unides = new javax.swing.JTable();
        alerta_nombre = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(unidad_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 310, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Nombre:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 80, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("REGISTRAR UNIDADES");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 310, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Estado:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 80, 30));

        unidad_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        unidad_estado.setToolTipText("");
        add(unidad_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 310, 30));

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
        add(btn_registrar_unidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 140, 30));

        btn_cancelar_unidad.setBackground(new java.awt.Color(255, 255, 255));
        btn_cancelar_unidad.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cancelar_unidad.setText("CANCELAR");
        btn_cancelar_unidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_unidadActionPerformed(evt);
            }
        });
        add(btn_cancelar_unidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 140, 30));

        tabla_unides = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla_unides.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla_unides);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 940, 360));

        alerta_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_nombre.setForeground(new java.awt.Color(255, 0, 51));
        alerta_nombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                alerta_nombreMouseExited(evt);
            }
        });
        add(alerta_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 510, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_registrar_unidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrar_unidadActionPerformed
        if(unidad_nombre.getText().equals("")){
            alerta_nombre.setText("El nombre de la unidad no puede estar vacio");
        }else{
            ControlDB_Unidad  controlDB_Unidad = new ControlDB_Unidad(tipoConexion);
            Unidad unidad = new Unidad();
            unidad.setDescripcion(unidad_nombre.getText());
            //Validamos si selecciono activo o inactivo
            if(unidad_estado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                unidad.setEstado("1");
            }else{
                unidad.setEstado("0");
            }
            //Procedemos a registrar la unidad en la base de datos
            try {
                if(controlDB_Unidad.validarExistencia(unidad)){
                    JOptionPane.showMessageDialog(null, "La unidad ya se encuentra registrada en el sistema");
                }else{
                    int respuesta=controlDB_Unidad.registrar(unidad, user);
                    if(respuesta==1){
                        JOptionPane.showMessageDialog(null, "Se registro la unidad de manera exitosa");
                        unidad_nombre.setText("");
                        unidad_estado.setSelectedIndex(0);
                        tabla_ListarUnidades("");
                    }else{
                        if(respuesta==0){
                            JOptionPane.showMessageDialog(null, "No se pudo registrar la unidad, valide datos");
                        }
                    }
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error al registrar la unidad");
                Logger.getLogger(Insumo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Insumo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Unidad_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SocketException ex) {
                Logger.getLogger(Unidad_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_registrar_unidadActionPerformed

    private void btn_cancelar_unidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_unidadActionPerformed
        unidad_nombre.setText("");
        unidad_estado.setSelectedIndex(0);
    }//GEN-LAST:event_btn_cancelar_unidadActionPerformed

    private void alerta_nombreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alerta_nombreMouseExited
        
    }//GEN-LAST:event_alerta_nombreMouseExited

    private void btn_registrar_unidadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_registrar_unidadMouseExited
        alerta_nombre.setText("");
    }//GEN-LAST:event_btn_registrar_unidadMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alerta_nombre;
    private javax.swing.JButton btn_cancelar_unidad;
    private javax.swing.JButton btn_registrar_unidad;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_unides;
    private javax.swing.JComboBox<String> unidad_estado;
    private javax.swing.JTextField unidad_nombre;
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
            tabla_unides.setModel(modeloUnidad);
        }
    }

}
