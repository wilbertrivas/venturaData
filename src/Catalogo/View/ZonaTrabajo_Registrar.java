package Catalogo.View;
  
import Catalogo.Controller.ControlDB_ZonaTrabajo;
import ModuloEquipo.Controller.ControlDB_Producto;
import Catalogo.Model.Producto;
import Catalogo.Model.ZonaTrabajo;
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

public  class ZonaTrabajo_Registrar extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;
    public ZonaTrabajo_Registrar(Usuario us,String tipoConexion) {
        user=us;
        this.tipoConexion= tipoConexion;
        initComponents();
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las zonas de trabajo");
            Logger.getLogger(ZonaTrabajo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        alerta_nombre = new javax.swing.JLabel();
        alerta_codigo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 310, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Nombre:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 80, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("REGISTRAR ZONA DE TRABAJO");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 630, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Estado:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 80, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 310, 30));

        btnRegistrar.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 140, 30));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 140, 30));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 850, 320));

        alerta_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_nombre.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 750, 20));

        alerta_codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_codigo.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 750, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if(nombre.getText().equals("")){
            alerta_nombre.setText("El nombre de la zona de trabajo no puede estar vacia");
        }else{
            ZonaTrabajo zonaTrabajo = new ZonaTrabajo();
            zonaTrabajo.setDescripcion(nombre.getText());
            //Validamos si selecciono activo o inactivo
            if(estado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                zonaTrabajo.setEstado("1");
            }else{
                zonaTrabajo.setEstado("0");
            }
            try {
                if(new ControlDB_ZonaTrabajo(tipoConexion).validarExistencia(zonaTrabajo)){
                    JOptionPane.showMessageDialog(null, "La zona de trabajo con esa descripción ya fue registrada, valide datos", "Error!!", JOptionPane.ERROR_MESSAGE);
                }else{             
                    int respuesta=new ControlDB_ZonaTrabajo(tipoConexion).registrar(zonaTrabajo,user);
                    if(respuesta==1){
                        JOptionPane.showMessageDialog(null, "Se registro la zona de trabajo de forma exitosa");
                        nombre.setText("");
                        estado.setSelectedIndex(0);
                        tabla_Listar("");
                    }else{
                        if(respuesta==0){
                            JOptionPane.showMessageDialog(null, "No se pudo registrar la zona de trabajo, valide datos");
                        }
                    }
                }
                
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error al registrar la zona de trabajo");
                Logger.getLogger(ZonaTrabajo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ZonaTrabajo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(ZonaTrabajo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SocketException ex) {
                Logger.getLogger(ZonaTrabajo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        nombre.setText("");
        estado.setSelectedIndex(0);
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alerta_codigo;
    private javax.swing.JLabel alerta_nombre;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "Nombre","Estado"});  
        ArrayList<ZonaTrabajo> listado=new ControlDB_ZonaTrabajo(tipoConexion).buscar(valorConsulta);
        for (ZonaTrabajo listado1 : listado) {
            String[] registro = new String[3];
            registro[0] = "" + listado1.getCodigo();
            registro[1] = "" + listado1.getDescripcion();
            registro[2] = "" + listado1.getEstado();
            modelo.addRow(registro);      
        }
        tabla.setModel(modelo);
    }

}
