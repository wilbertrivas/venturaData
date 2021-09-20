package Sistema.View;

import Sistema.Controller.ControlDB_Usuario;
import Sistema.Model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
 
public final class Usuario_Consultar extends javax.swing.JPanel {
    private String tipoConexion;
    public Usuario_Consultar(String tipoConexion) {
        initComponents();
        this.tipoConexion= tipoConexion;
        /*try {
            tabla_Listar("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los usuarios");
            Logger.getLogger(Usuario_Consultar.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        valorBusqueda = new javax.swing.JTextField();
        consultar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("CONSULTAR USUARIOS");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 310, 30));

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
            Logger.getLogger(Usuario_Consultar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_consultarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton consultar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException{    
        DefaultTableModel modelo = new DefaultTableModel(null, new String[] {"Código", "Nombre","Perfil", "Correo","Estado"});  
        ArrayList<Usuario> listado=new ControlDB_Usuario(tipoConexion).buscar(valorConsulta);
        for(int i =0; i< listado.size(); i++){
            String[]  registro = new String[5]; 
            registro[0]=""+listado.get(i).getCodigo();
            registro[1]=""+listado.get(i).getNombres()+" "+listado.get(i).getApellidos();
            registro[2]=""+listado.get(i).getPerfilUsuario().getDescripcion();            
            registro[3]=""+listado.get(i).getCorreo();
            registro[4]=""+listado.get(i).getEstado();
            modelo.addRow(registro);
        }
        tabla.setModel(modelo);
    }

}
