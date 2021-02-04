package Consumo.View;

import Consumo.Controller2.ControlDB_Insumo;
import Consumo.Controller2.ControlDB_Unidad;
import Consumo.Model2.Insumo;
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

public class Insumo_Registrar extends javax.swing.JPanel {
    DefaultTableModel modeloInsumo;
    String [] tituloInsumo= {"Código", "Nombre","Unidad", "Cantidad", "Estado"};
    String[]  registroInsumo; 
    Usuario user;
    private String tipoConexion;
    
    public Insumo_Registrar(Usuario us,String tipoConexion) {
        user= us;
        initComponents();
        this.tipoConexion= tipoConexion;
        
        //Cargamos en la interfaz las unidades que esten activas
        ControlDB_Unidad controlDB_Unidad = new ControlDB_Unidad(tipoConexion);
        ArrayList<Unidad> listadoUnidades = new ArrayList();
        try {
            listadoUnidades=controlDB_Unidad.buscarUnidadesActivas();
            for(int i=0; i< listadoUnidades.size(); i++){
                unidad.addItem(listadoUnidades.get(i).getDescripcion());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Insumo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            tabla_ListarInsumos("");
        } catch (SQLException ex) {
            Logger.getLogger(Insumo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        jLabel4 = new javax.swing.JLabel();
        nombre_insumo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        unidad = new javax.swing.JComboBox<>();
        estado_insumo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btn_registrar_insumo1 = new javax.swing.JButton();
        btn_registrar_insumo2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_insumo = new javax.swing.JTable();
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

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Unidad:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 100, 30));
        add(nombre_insumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 310, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Nombre:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 100, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Estado:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 100, 30));

        unidad.setToolTipText("");
        add(unidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 310, 30));

        estado_insumo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado_insumo.setToolTipText("");
        add(estado_insumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 310, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("REGISTRAR INSUMO");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 290, 30));

        btn_registrar_insumo1.setBackground(new java.awt.Color(255, 255, 255));
        btn_registrar_insumo1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_registrar_insumo1.setText("REGISTRAR");
        btn_registrar_insumo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_registrar_insumo1MouseExited(evt);
            }
        });
        btn_registrar_insumo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrar_insumo1ActionPerformed(evt);
            }
        });
        add(btn_registrar_insumo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 140, 30));

        btn_registrar_insumo2.setBackground(new java.awt.Color(255, 255, 255));
        btn_registrar_insumo2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_registrar_insumo2.setText("CANCELAR");
        btn_registrar_insumo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrar_insumo2ActionPerformed(evt);
            }
        });
        add(btn_registrar_insumo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, 140, 30));

        tabla_insumo = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla_insumo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla_insumo);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 1000, 460));

        alerta_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_nombre.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 380, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
       
    }//GEN-LAST:event_EditarActionPerformed

    private void btn_registrar_insumo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrar_insumo1ActionPerformed
        if(nombre_insumo.getText().equals("")){
            alerta_nombre.setText("El nombre del insumo no puede estar vacio");
        }else{
            ControlDB_Insumo  controlDB_Insumo = new ControlDB_Insumo(tipoConexion);
            ControlDB_Unidad  controlDB_Unidad = new ControlDB_Unidad(tipoConexion);
            Insumo insumo = new Insumo();
            insumo.setDescripcion(nombre_insumo.getText());
             try {
                 insumo.setUnidad(new Unidad(Integer.parseInt(controlDB_Unidad.buscar_nombre(unidad.getSelectedItem().toString())),unidad.getSelectedItem().toString(),"1") );
             } catch (SQLException ex) {
                 Logger.getLogger(Insumo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
             }
            //Validamos si selecciono activo o inactivo
            if(estado_insumo.getSelectedItem().toString().equalsIgnoreCase("Activo")){
                insumo.setEstado("1");
            }else{
                insumo.setEstado("0");
            }
            //Procedemos a registrar la unidad en la base de datos
            try {
                if(controlDB_Insumo.validarExistencia(insumo)){
                    JOptionPane.showMessageDialog(null, "El insumo ya exite en el sistema");
                }else{
                    int respuesta=controlDB_Insumo.registrar(insumo, user);
                    if(respuesta==1){
                        JOptionPane.showMessageDialog(null, "Se registro el insumo de manera exitosa");
                        nombre_insumo.setText("");
                        unidad.setSelectedIndex(0);
                        estado_insumo.setSelectedIndex(0);
                        tabla_ListarInsumos("");
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
                Logger.getLogger(Insumo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SocketException ex) {
                Logger.getLogger(Insumo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_registrar_insumo1ActionPerformed

    private void btn_registrar_insumo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrar_insumo2ActionPerformed
        nombre_insumo.setText("");
        unidad.setSelectedIndex(0);
        estado_insumo.setSelectedIndex(0);
    }//GEN-LAST:event_btn_registrar_insumo2ActionPerformed

    private void btn_registrar_insumo1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_registrar_insumo1MouseExited
        alerta_nombre.setText("");
    }//GEN-LAST:event_btn_registrar_insumo1MouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JLabel alerta_nombre;
    private javax.swing.JButton btn_registrar_insumo1;
    private javax.swing.JButton btn_registrar_insumo2;
    private javax.swing.JComboBox<String> estado_insumo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre_insumo;
    private javax.swing.JTable tabla_insumo;
    private javax.swing.JComboBox<String> unidad;
    // End of variables declaration//GEN-END:variables
     public void tabla_ListarInsumos(String valorConsulta) throws SQLException{
        ControlDB_Insumo  controlDB_Insumo = new ControlDB_Insumo(tipoConexion);              
        registroInsumo = new String[5]; 
        modeloInsumo = new DefaultTableModel(null, tituloInsumo);  
        ArrayList<Insumo> listadoInsumo=controlDB_Insumo.buscar(valorConsulta);
        for(int i =0; i< listadoInsumo.size(); i++){
            registroInsumo[0]=""+listadoInsumo.get(i).getCodigo();
            registroInsumo[1]=""+listadoInsumo.get(i).getDescripcion();
            registroInsumo[2]=""+listadoInsumo.get(i).getUnidad().getDescripcion();
            registroInsumo[3]=""+listadoInsumo.get(i).getCantidad();
            registroInsumo[4]=""+listadoInsumo.get(i).getEstado();
            modeloInsumo.addRow(registroInsumo);
            tabla_insumo.setModel(modeloInsumo);
        }
    }
}
