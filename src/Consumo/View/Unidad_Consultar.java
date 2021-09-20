package Consumo.View;
  
import Consumo.Controller.ControlDB_Unidad;
import Consumo.Model.Unidad;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class Unidad_Consultar extends javax.swing.JPanel {
    
    DefaultTableModel modeloUnidad;
    String [] tituloUnidad= {"Unidad_Codigo", "Unidad_Nombre","Unidad_Estado"};
    String[]  registroUnidad;   
    private String tipoConexion;
    public Unidad_Consultar(String tipoConexion) {
        initComponents();
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

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_unidades = new javax.swing.JTable();
        valorBusqueda = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("CONSULTAR UNIDADES");
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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1180, 540));
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 280, 30));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("CONSULTAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 140, 30));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("GENERAR REPORTE");
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 160, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            tabla_ListarUnidades(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Unidad_Consultar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_unidades;
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
