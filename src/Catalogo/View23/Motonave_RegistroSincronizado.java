package Catalogo.View23;
  
import Catalogo.Controller.ControlDB_Motonave;
import Catalogo.Model.Motonave;
import Sistema.Model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class Motonave_RegistroSincronizado extends javax.swing.JPanel {
     ArrayList<Motonave> listado;
     Usuario user;
    private String tipoConexion;
    
    public Motonave_RegistroSincronizado(Usuario u, String tipoConexion) {
        initComponents();
        this.tipoConexion= tipoConexion;
        user= u;
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las motonaves");
            Logger.getLogger(Motonave_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        titulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        valorBusqueda = new javax.swing.JTextField();
        consultar = new javax.swing.JButton();
        sincronizarCcarga = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();

        Editar.setText("Sincronizar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo.setText("CONSULTAR MOTONAVES EN CCARGA GP");
        add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 410, 30));

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
        tabla.setComponentPopupMenu(Seleccionar);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1180, 520));
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 280, 30));

        consultar.setBackground(new java.awt.Color(255, 255, 255));
        consultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consultar.setText("CONSULTAR");
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        add(consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 140, 30));

        sincronizarCcarga.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sincronizarCcarga.setText("SINCRONIZAR CON CCARGA");
        sincronizarCcarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sincronizarCcargaActionPerformed(evt);
            }
        });
        add(sincronizarCcarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 250, 30));

        jProgressBar1.setStringPainted(true);
        add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 710, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        try {
            tabla_Listar(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Motonave_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_consultarActionPerformed

    private void sincronizarCcargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sincronizarCcargaActionPerformed
        Worker_Motonave worker = new Worker_Motonave (jProgressBar1,listado,user,tipoConexion);
        worker.execute();
    }//GEN-LAST:event_sincronizarCcargaActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO lo del clik derechoo
        int fila1;
        try{
            fila1=tabla.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "Nombre","Estado"});  
                modelo=(DefaultTableModel)tabla.getModel();
                Motonave mo = new Motonave();
                mo.setCodigo((String)modelo.getValueAt(fila1, 0));
                mo.setDescripcion((String)modelo.getValueAt(fila1, 1));
                mo.setEstado((String)modelo.getValueAt(fila1, 2));
                int retorno=new ControlDB_Motonave(tipoConexion).registrar(mo, user);
                if(retorno ==1){
                    JOptionPane.showMessageDialog(null, "Se sincronizó la motonave de forma exitosa");
                }else{
                    JOptionPane.showMessageDialog(null, "No se pudo sincronizar la motonave valide si ya existe en el sistema");
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() == 2) {
            // TODO lo del clik derechoo
            int fila1;
            try{
                fila1=tabla.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "Nombre","Estado"});  
                    modelo=(DefaultTableModel)tabla.getModel();
                    Motonave mo = new Motonave();
                    mo.setCodigo((String)modelo.getValueAt(fila1, 0));
                    mo.setDescripcion((String)modelo.getValueAt(fila1, 1));
                    mo.setEstado((String)modelo.getValueAt(fila1, 2));
                    int retorno=new ControlDB_Motonave(tipoConexion).registrar(mo, user);
                    if(retorno ==1){
                        JOptionPane.showMessageDialog(null, "Se sincronizó la motonave de forma exitosa");
                    }else{
                        JOptionPane.showMessageDialog(null, "No se pudo sincronizar la motonave valide si ya existe en el sistema");
                    }
                }
            }catch(Exception e){
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JButton consultar;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton sincronizarCcarga;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel titulo;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "Nombre","Estado"});  
        listado=new ControlDB_Motonave(tipoConexion).buscarMotonaveGP(valorConsulta);
        for (Motonave listado1 : listado) {
            String[] registro = new String[3];
            registro[0] = "" + listado1.getCodigo();
            registro[1] = "" + listado1.getDescripcion();
            if(listado1.getEstado().equalsIgnoreCase("1")){
                registro[2] = "ACTIVO";
            }else{
                registro[2]="INACTIVO";
            }
            modelo.addRow(registro);      
        }
        tabla.setModel(modelo);
    }

}
