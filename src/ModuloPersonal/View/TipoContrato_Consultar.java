/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloPersonal.View;


import ModuloPersonal.Controller.ControlDB_TipoContrato;
import ModuloPersonal.Model.TipoContrato;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sistemas
 */
public class TipoContrato_Consultar extends javax.swing.JPanel {
    private String tipoConexion;
    public TipoContrato_Consultar(String tipoConexion) {
        initComponents();
        this.tipoConexion= tipoConexion;
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las tipos de contrato");
            Logger.getLogger(TipoContrato_Consultar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        valorBusqueda = new javax.swing.JTextField();
        tituloCargNom = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        consultar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        valorBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorBusquedaActionPerformed(evt);
            }
        });
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 280, 30));

        tituloCargNom.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tituloCargNom.setText("CONSULTAR  TIPOS DE CONTRATO");
        add(tituloCargNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 720, 30));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1180, 500));

        consultar.setBackground(new java.awt.Color(255, 255, 255));
        consultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consultar.setText("CONSULTAR");
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        add(consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 140, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        try {
            tabla_Listar(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(TipoContrato_Consultar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_consultarActionPerformed

    private void valorBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valorBusquedaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton consultar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel tituloCargNom;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables

 public void tabla_Listar(String valorConsulta) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "Nombre","Estado"});  
        ArrayList<TipoContrato> listadoTiposContrato =new ControlDB_TipoContrato(tipoConexion).buscar(valorConsulta);
        for (TipoContrato object : listadoTiposContrato) {
            String[] registro = new String[3];
            registro[0] = "" + object.getCodigo();
            registro[1] = "" + object.getDescripcion();
            registro[2] = "" + object.getEstado();
            modelo.addRow(registro);      
        }
        tabla.setModel(modelo);
    }

}
