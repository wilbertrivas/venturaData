package Sistema.View2;
  
import Sistema.Controller.ControlDB_Perfil;
import Sistema.Model.Perfil;
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

public final class Perfil_Actualizar extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;
    public Perfil_Actualizar(Usuario u, String tipoConexion) { 
        initComponents();
        this.tipoConexion= tipoConexion;
         user= u;
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los perfiles");
            Logger.getLogger(Perfil_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        nombre = new javax.swing.JTextField();
        codigo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox<>();
        actualizar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        valorBusqueda = new javax.swing.JTextField();
        consultar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
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
        add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 310, 30));

        codigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 310, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("ACTUALIZACIONES DE PERFILES");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 310, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Estado:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 80, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 310, 30));

        actualizar.setBackground(new java.awt.Color(255, 255, 255));
        actualizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        actualizar.setText("ACTUALIZAR");
        actualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                actualizarMouseExited(evt);
            }
        });
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });
        add(actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 140, 30));

        cancelar.setBackground(new java.awt.Color(255, 255, 255));
        cancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cancelar.setText("CANCELAR");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        add(cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 140, 30));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 620, 470));
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 280, 30));

        consultar.setBackground(new java.awt.Color(255, 255, 255));
        consultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consultar.setText("CONSULTAR");
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        add(consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, 140, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Nombre:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 80, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Código:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 80, 30));

        alerta_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_nombre.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 380, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        nombre.setText("");
        estado.setSelectedIndex(0);
    }//GEN-LAST:event_cancelarActionPerformed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        if(nombre.getText().equals("")){
            alerta_nombre.setText("El nombre del perfil no puede estar vacio");
        }else{
            ControlDB_Perfil  controlDB_Perfil = new ControlDB_Perfil(tipoConexion);
            Perfil perfil = new Perfil();
            perfil.setCodigo(codigo.getText());
            perfil.setDescripcion(nombre.getText());
            //Validamos si selecciono activo o inactivo
            if(estado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                perfil.setEstado("1");
            }else{
                perfil.setEstado("0");
            }
            //Procedemos a actualizar
            try {
                if(!controlDB_Perfil.validarExistenciaActualizar(perfil)){
                    int respuesta = 0;
                    try {
                        respuesta = controlDB_Perfil.actualizar(perfil,user);
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(Perfil_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SocketException ex) {
                        Logger.getLogger(Perfil_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(respuesta==1){
                        JOptionPane.showMessageDialog(null, "Se actualizo el perfil de forma exitosa");
                        nombre.setText("");
                        estado.setSelectedIndex(0);
                        tabla_Listar("");
                    }else{
                        if(respuesta==0){
                            JOptionPane.showMessageDialog(null, "No se pudo actualizar el perfil, valide los datos ingresados");
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Ya existe un perfil con el mismo nombre", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                }
                
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar el perfil");
                Logger.getLogger(Perfil_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Perfil_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_actualizarActionPerformed

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        try {
            tabla_Listar(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Perfil_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_consultarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO lo del clik derechoo
        int fila1;
        try{
            fila1=tabla.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                codigo.setText((String)modelo.getValueAt(fila1, 0));
                nombre.setText((String)modelo.getValueAt(fila1, 1));
                String estadoS=(String)modelo.getValueAt(fila1, 2);
                if(estadoS.equalsIgnoreCase("ACTIVO")){
                    estado.setSelectedIndex(0);
                }else{
                    estado.setSelectedIndex(1);
                }
                codigo.setEnabled(false);
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
                    DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                    codigo.setText((String)modelo.getValueAt(fila1, 0));
                    nombre.setText((String)modelo.getValueAt(fila1, 1));
                    String estadoS=(String)modelo.getValueAt(fila1, 2);
                    if(estadoS.equalsIgnoreCase("ACTIVO")){
                        estado.setSelectedIndex(0);
                    }else{
                        estado.setSelectedIndex(1);
                    }
                    codigo.setEnabled(false);
                }
            }catch(Exception e){
        }
        }
         
    }//GEN-LAST:event_tablaMouseClicked

    private void actualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actualizarMouseExited
        alerta_nombre.setText("");
    }//GEN-LAST:event_actualizarMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JButton actualizar;
    private javax.swing.JLabel alerta_nombre;
    private javax.swing.JButton cancelar;
    private javax.swing.JLabel codigo;
    private javax.swing.JButton consultar;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException{
        ControlDB_Perfil  controlDB_Perfil = new ControlDB_Perfil(tipoConexion);        
        DefaultTableModel modelo = new DefaultTableModel(null, new String[] {"Código", "Nombre","Estado"});  
        ArrayList<Perfil> listadoPerfiles=controlDB_Perfil.buscar(valorConsulta);
        for(int i =0; i< listadoPerfiles.size(); i++){
            String[]  registro = new String[3]; 
            registro[0]=""+listadoPerfiles.get(i).getCodigo();
            registro[1]=""+listadoPerfiles.get(i).getDescripcion();
            registro[2]=""+listadoPerfiles.get(i).getEstado();            
            modelo.addRow(registro);
        }
         tabla.setModel(modelo);
    }

}
