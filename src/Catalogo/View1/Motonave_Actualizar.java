package Catalogo.View1;
  
import Catalogo.Controller.ControlDB_BaseDatos;
import Catalogo.Controller.ControlDB_Motonave;
import Catalogo.Model.BaseDatos;
import Catalogo.Model.Motonave;
import Sistema.Model.Usuario;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class Motonave_Actualizar extends javax.swing.JPanel {
    Usuario user;
    ArrayList<BaseDatos> listadoBaseDatos= new ArrayList();
    ArrayList<Motonave> listadoObjetos=null;
    
private String tipoConexion;
    public Motonave_Actualizar(Usuario us,String tipoConexion) {
        user=us;
        this.tipoConexion= tipoConexion;
        initComponents();
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las motonaves");
            Logger.getLogger(Motonave_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Cargamos en la interfaz la lista de las base de datos
        try {
            listadoBaseDatos=new ControlDB_BaseDatos(tipoConexion).buscar();
            if(listadoBaseDatos != null){
                String datosObjeto[]= new String[listadoBaseDatos.size()];
                int contador=0;
                for(BaseDatos objeto : listadoBaseDatos){ 
                    datosObjeto[contador]=objeto.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                listado_baseDatos.setModel(model);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Motonave_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }  
        codigo.setEnabled(false);
        nombre.setEnabled(false);
        estado.setEnabled(false);
        listado_baseDatos.setEnabled(false);
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
        btnActualizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        valorBusqueda = new javax.swing.JTextField();
        btnConsultar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        alerta_nombre = new javax.swing.JLabel();
        listado_baseDatos = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        Editar.setText("Modificar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 370, 30));

        codigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 370, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ACTUALIZACIÓN DE MOTONAVES");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1290, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Estado:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 80, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 370, 30));

        btnActualizar.setBackground(new java.awt.Color(255, 255, 255));
        btnActualizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/actualizar.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnActualizarMouseExited(evt);
            }
        });
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 160, 40));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 320, 180, 40));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 760, 470));
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 480, 40));

        btnConsultar.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        btnConsultar.setText("CONSULTAR");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 50, 270, 40));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Nombre:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 80, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Código:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 80, 30));

        alerta_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_nombre.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 360, 20));

        listado_baseDatos.setToolTipText("");
        add(listado_baseDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 370, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Base de Datos:");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1290, 90));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 530, 470));
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        codigo.setText("");
        nombre.setText("");
        estado.setSelectedIndex(0);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if(codigo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Error.. Se debe carga una Motonave", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            if(nombre.getText().equals("")){
                alerta_nombre.setText("El nombre de la motonave no puede estar vacio");
            }else{
                Motonave Objeto = new Motonave();
                Objeto.setCodigo(codigo.getText());
                Objeto.setDescripcion(nombre.getText());
                //Validamos si selecciono activo o inactivo
                if(estado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                    Objeto.setEstado("1");
                }else{
                    Objeto.setEstado("0");
                }
                if(listadoBaseDatos !=null){
                        Objeto.setBaseDatos(listadoBaseDatos.get(listado_baseDatos.getSelectedIndex()));
                }else{
                    Objeto.setBaseDatos(new BaseDatos("NULL"));
                }
                try {
                    //if(!new ControlDB_Motonave(tipoConexion).validarExistenciaActualizar(Objeto)){
                    int respuesta=new ControlDB_Motonave(tipoConexion).actualizar(Objeto, user);
                    if(respuesta==1){
                        JOptionPane.showMessageDialog(null, "Se actualizo la motonave de forma exitosa");
                        codigo.setText("");
                        nombre.setText("");
                        listado_baseDatos.setSelectedIndex(0);
                        estado.setSelectedIndex(0);
                        tabla_Listar("");
                        nombre.setEnabled(false);
                        estado.setEnabled(false);
                    }else{
                        if(respuesta==0){
                            JOptionPane.showMessageDialog(null, "No se pudo actualizar la motonave, valide los datos ingresados");
                        }
                    }
                    //}else{
                      //  JOptionPane.showMessageDialog(null, "Ya existe una motonave con el mismo nombre, valide los datos ingresados", "Advertencia", JOptionPane.ERROR_MESSAGE);
                    //}     
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Error al registrar la motonave");
                    Logger.getLogger(Motonave_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Motonave_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(Motonave_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SocketException ex) {
                    Logger.getLogger(Motonave_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        try {
            tabla_Listar(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Motonave_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        int fila1;
        try{
            fila1=tabla.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                if(listadoObjetos != null){
                    Motonave objeto=listadoObjetos.get(fila1);
                    //DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                    codigo.setText(objeto.getCodigo());
                    nombre.setText(objeto.getDescripcion());
                    String estadoS=objeto.getEstado();
                    if(estadoS.equalsIgnoreCase("ACTIVO")){
                        estado.setSelectedIndex(0);
                    }else{
                        estado.setSelectedIndex(1);
                    }
                    int contador=0;
                    for(BaseDatos db : listadoBaseDatos){
                        if(db.getCodigo().equals(objeto.getBaseDatos().getCodigo())){
                                listado_baseDatos.setSelectedIndex(contador);
                        }
                        contador++;
                    }
                    nombre.setEnabled(true);
                    estado.setEnabled(true);
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() == 2) {
            int fila1;
            try{
                fila1=tabla.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    if(listadoObjetos != null){
                        Motonave objeto=listadoObjetos.get(fila1);
                        codigo.setText(objeto.getCodigo());
                        nombre.setText(objeto.getDescripcion());
                        String estadoS=objeto.getEstado();
                        if(estadoS.equalsIgnoreCase("ACTIVO")){
                            estado.setSelectedIndex(0);
                        }else{
                            estado.setSelectedIndex(1);
                        }
                        int contador=0;
                        for(BaseDatos db : listadoBaseDatos){
                            if(db.getCodigo().equals(objeto.getBaseDatos().getCodigo())){
                                    listado_baseDatos.setSelectedIndex(contador);
                            }
                            contador++;
                        }
                        nombre.setEnabled(true);
                        estado.setEnabled(true);
                    }
                }
            }catch(Exception e){
            }
        }   
    }//GEN-LAST:event_tablaMouseClicked

    private void btnActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseExited
        alerta_nombre.setText("");
    }//GEN-LAST:event_btnActualizarMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JLabel alerta_nombre;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JLabel codigo;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> listado_baseDatos;
    private javax.swing.JTextField nombre;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException{      
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "Nombre","Estado","Origen de Datos"});  
        listadoObjetos=new ControlDB_Motonave(tipoConexion).buscar(valorConsulta);
        for(Motonave Objeto:listadoObjetos){
            String []registro = new String[4];
            registro[0]=""+Objeto.getCodigo();
            registro[1]=""+Objeto.getDescripcion();
            registro[2]=""+Objeto.getEstado();            
            registro[3]=""+Objeto.getBaseDatos().getDescripcion();            
            modelo.addRow(registro);
        }
        tabla.setModel(modelo);
    }

}
