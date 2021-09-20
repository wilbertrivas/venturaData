/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloPersonal.View;

import ModuloPersonal.Controller.ControlDB_CargoNomina;
import ModuloPersonal.Controller.ControlDB_TipoContrato;
import ModuloPersonal.Model.CargoNomina;
import ModuloPersonal.Model.TipoContrato;
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

/**
 *
 * @author sistemas
 */
public class TipoContrato_Registrar extends javax.swing.JPanel {
    private Usuario user;
    private String tipoConexion;
           

    /**
     * Creates new form TipoContrato_Registrar
     */
    public TipoContrato_Registrar(Usuario usu, String tipoConexion) {
        initComponents();
        this.user = usu;
        this.tipoConexion = tipoConexion;
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar al leer los tipos de contrato");
            Logger.getLogger(SituacionMedica_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        //resizeColumnWidth(tabla);
        //resizeColumnWidth(tabla);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        descripcion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cancelar = new javax.swing.JButton();
        registrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla1.setName("tabla"); // NOI18N
        jScrollPane2.setViewportView(tabla1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 670, 560));
        jPanel1.add(descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 300, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Estado:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        estado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO", " " }));
        jPanel1.add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 300, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Nombre:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 80, 30));

        cancelar.setBackground(new java.awt.Color(255, 255, 255));
        cancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        cancelar.setText("CANCELAR");
        jPanel1.add(cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, 160, 40));

        registrar.setBackground(new java.awt.Color(255, 255, 255));
        registrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Ingresar.png"))); // NOI18N
        registrar.setText("REGISTRAR");
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });
        jPanel1.add(registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("REGISTRAR TIPO DE CONTRATO");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
       
        if (descripcion.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "La descripcion no puede ser vacia", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            TipoContrato tipoContrato = new TipoContrato();
            tipoContrato.setDescripcion(descripcion.getText());
            if (estado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")) {
                tipoContrato.setEstado("1");
            } else {
                tipoContrato.setEstado("0");
            }
            try {
                if (new ControlDB_TipoContrato(tipoConexion).validarPorDescripcion(tipoContrato)) {
                    JOptionPane.showMessageDialog(null, "El tipo de contrato ya se encuentra registrado en el sistema");
                } else {
                    int respuesta = new ControlDB_TipoContrato(tipoConexion).registrar(tipoContrato, user);
                    if (respuesta == 1) {
                        JOptionPane.showMessageDialog(null, "Se registro el tipo de contrato de forma exitosa");
                        descripcion.setText("");;
                        estado.setSelectedIndex(0);
                        tabla_Listar("");

                    } else {
                        if (respuesta == 0) {
                            JOptionPane.showMessageDialog(null, "No se pudo registrar el tipo de contrato, valide datos");
                        }
                    }

                }

            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error al registrar el cargo de nomina");
                Logger.getLogger(TipoContrato_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(TipoContrato_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(TipoContrato_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SocketException ex) {
                Logger.getLogger(TipoContrato_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        
    }//GEN-LAST:event_registrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelar;
    private javax.swing.JTextField descripcion;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton registrar;
    private javax.swing.JTable tabla1;
    // End of variables declaration//GEN-END:variables

  public void tabla_Listar(String valorConsulta) throws SQLException{
      DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código","Descripción","Estado"});
      ArrayList<TipoContrato> listado = new ControlDB_TipoContrato(tipoConexion).buscar(valorConsulta);
      for (TipoContrato listado1 : listado) {
          String[] registro = new String[3];
          registro[0] = "" + listado1.getCodigo();
          registro[1] = "" + listado1.getDescripcion();
          registro[2] = "" + listado1.getEstado();

            modelo.addRow(registro);      
        }
        tabla1.setModel(modelo);
    }
}