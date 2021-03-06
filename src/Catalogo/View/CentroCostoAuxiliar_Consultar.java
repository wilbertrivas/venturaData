package Catalogo.View;
  
import Consumo.View.Unidad_Registrar;
import Catalogo.Controller.ControlDB_CentroCostoAuxiliar;
import Catalogo.Model.CentroCostoAuxiliar;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class CentroCostoAuxiliar_Consultar extends javax.swing.JPanel {
    private String tipoConexion;
    public CentroCostoAuxiliar_Consultar(String tipoConexion) {
        initComponents();
        this.tipoConexion= tipoConexion;
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las unidades");
            Logger.getLogger(Unidad_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        valorBusqueda = new javax.swing.JTextField();
        consultar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo.setText("CONSULTA DE CENTRO DE COSTOS AUXILIARES");
        add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 590, 30));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1180, 540));
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
    }// </editor-fold>//GEN-END:initComponents

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        try {
            tabla_Listar(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(CentroCostoAuxiliar_Consultar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_consultarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton consultar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel titulo;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException{             
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"C??digo", "CentroOperaci??n","Subcentro Costo", "Centro CostoAuxiliar", "Estado"});  
        ArrayList<CentroCostoAuxiliar> listadoCentroCostoAuxiliar=new ControlDB_CentroCostoAuxiliar(tipoConexion).buscar(valorConsulta);
        for(int i =0; i< listadoCentroCostoAuxiliar.size(); i++){
            String[] registro = new String[5];
            registro[0]=""+listadoCentroCostoAuxiliar.get(i).getCodigo();
            registro[1]=""+listadoCentroCostoAuxiliar.get(i).getCentroCostoSubCentro().getCentroOperacion().getDescripcion();
            registro[2]=""+listadoCentroCostoAuxiliar.get(i).getCentroCostoSubCentro().getDescripcion();
            registro[3]=""+listadoCentroCostoAuxiliar.get(i).getDescripcion();
            registro[4]=""+listadoCentroCostoAuxiliar.get(i).getEstado();
            modelo.addRow(registro);       
        }
        tabla.setModel(modelo);
    }
}
