package Consumo.View;

import Consumo.Controller.ControlDB_Insumo;
import Consumo.Model.Insumo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public final class Insumo_Consultar extends javax.swing.JPanel {
    DefaultTableModel modeloInsumno;
    String [] tituloInsumo= {"Codigo", "Nombre","Unidad", "Cantidad", "Estado"};
    String[]  registroInsumo;   
    private String tipoConexion;
    public Insumo_Consultar(String tipoConexion) {
        initComponents();  
        this.tipoConexion= tipoConexion;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_unidades = new javax.swing.JTable();
        valorBusqueda = new javax.swing.JTextField();
        consultar = new javax.swing.JButton();
        generarReporte = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("CONSULTAR INSUMOS");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 310, 30));

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
        jScrollPane1.setViewportView(tabla_unidades);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1080, 530));
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 280, 30));

        consultar.setBackground(new java.awt.Color(255, 255, 255));
        consultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consultar.setText("CONSULTAR");
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        add(consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 140, 30));

        generarReporte.setBackground(new java.awt.Color(255, 255, 255));
        generarReporte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        generarReporte.setText("GENERAR REPORTE");
        add(generarReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 160, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        try {
            tabla_ListarInsumos(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Insumo_Consultar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_consultarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton consultar;
    private javax.swing.JButton generarReporte;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_unidades;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_ListarInsumos(String valorConsulta) throws SQLException{
        ControlDB_Insumo  controlDB_Insumo = new ControlDB_Insumo(tipoConexion);        
        registroInsumo = new String[5]; 
        modeloInsumno = new DefaultTableModel(null, tituloInsumo);  
        ArrayList<Insumo> listadoInsumo=controlDB_Insumo.buscar(valorConsulta);
        for(int i =0; i< listadoInsumo.size(); i++){
            registroInsumo[0]=""+listadoInsumo.get(i).getCodigo();
            registroInsumo[1]=""+listadoInsumo.get(i).getDescripcion();
            registroInsumo[2]=""+listadoInsumo.get(i).getUnidad().getDescripcion();
            registroInsumo[3]=""+listadoInsumo.get(i).getCantidad();
            registroInsumo[4]=""+listadoInsumo.get(i).getEstado();
            modeloInsumno.addRow(registroInsumo);
            tabla_unidades.setModel(modeloInsumno);
        }
    }

}
