package Catalogo.View23;
   
import Catalogo.Controller.ControlDB_Articulo;
import Catalogo.Controller.ControlDB_TipoArticulo;
import Catalogo.Model.Articulo;
import Catalogo.Model.TipoArticulo;
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

public final class Articulo_Actualizar extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;
    ArrayList<TipoArticulo> listadoTipoArticulo = new ArrayList();
    ArrayList<Articulo> listadoArticulo = new ArrayList();
    public Articulo_Actualizar(Usuario us,String tipoConexion) {
        
        initComponents();
        user=us;
        this.tipoConexion= tipoConexion;
        
         //Cargamos en la interfaz los tipos de articulos activos
        try {
            listadoTipoArticulo=new ControlDB_TipoArticulo(tipoConexion).buscarActivos();
            for(TipoArticulo tipoArticulo: listadoTipoArticulo){
                Select_TipoArticulo.addItem(tipoArticulo.getDescripcion());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulo_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los articulos");
            Logger.getLogger(Articulo_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
        Select_TipoArticulo.setEnabled(false);
        nombre.setEnabled(false);
        estado.setEnabled(false);
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
        jLabel8 = new javax.swing.JLabel();
        Select_TipoArticulo = new javax.swing.JComboBox<>();

        Editar.setText("Modificar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 310, 30));

        codigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 310, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("ACTUALIZACIÓN DE ARTICULOS");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 550, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Estado:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 80, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 310, 30));

        btnActualizar.setBackground(new java.awt.Color(255, 255, 255));
        btnActualizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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
        add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 140, 30));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, 140, 30));

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

        btnConsultar.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultar.setText("CONSULTAR");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, 140, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Nombre:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 80, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Código:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 80, 30));

        alerta_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_nombre.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 380, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("TipoArticulo:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 100, 30));

        Select_TipoArticulo.setToolTipText("");
        add(Select_TipoArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 310, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        codigo.setText("");
        nombre.setText("");
        estado.setSelectedIndex(0);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if(codigo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Error.. Se debe carga un artículo", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
           
                if(nombre.getText().equals("")){
                    alerta_nombre.setText("El nombre del articulo no puede estar vacio");
                }else{
                    Articulo Objeto = new Articulo();
                    Objeto.setCodigo(codigo.getText());
                    try{
                        Objeto.setTipoArticulo(listadoTipoArticulo.get(Select_TipoArticulo.getSelectedIndex()));
                    }catch(Exception e){
                        Objeto.setTipoArticulo(null);
                    }
                    Objeto.setDescripcion(nombre.getText());
                    //Validamos si selecciono activo o inactivo
                    if(estado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                        Objeto.setEstado("1");
                    }else{
                        Objeto.setEstado("0");
                    }
                    try {
                        if(!new ControlDB_Articulo(tipoConexion).validarExistenciaActualizar(Objeto)){
                            int respuesta=new ControlDB_Articulo(tipoConexion).actualizar(Objeto, user);
                            if(respuesta==1){
                                JOptionPane.showMessageDialog(null, "Se actualizo el articulo de forma exitosa");
                                codigo.setText("");
                                Select_TipoArticulo.setSelectedIndex(0);
                                nombre.setText("");
                                estado.setSelectedIndex(0);
                                tabla_Listar("");
                                Select_TipoArticulo.setEnabled(false);
                                nombre.setEnabled(false);
                                estado.setEnabled(false);
                            }else{
                                if(respuesta==0){
                                    JOptionPane.showMessageDialog(null, "No se pudo actualizar el articulo, valide los datos ingresados");
                                }
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Ya existe un articulo con el mismo nombre, valide los datos ingresados", "Advertencia", JOptionPane.ERROR_MESSAGE);
                        }     
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "Error al registrar el articulo");
                        Logger.getLogger(Articulo_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Articulo_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(Articulo_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SocketException ex) {
                        Logger.getLogger(Articulo_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        try {
            tabla_Listar(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Articulo_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO lo del clik derechoo
            int fila1;
            try{
                fila1=tabla.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    //DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                    
                    Articulo articulo=listadoArticulo.get(fila1);
                    codigo.setText(articulo.getCodigo());
                    int contador=0;
                    for(TipoArticulo tip_art : listadoTipoArticulo){
                        if(articulo.getTipoArticulo().getDescripcion().equals(tip_art.getDescripcion())){
                            Select_TipoArticulo.setSelectedIndex(contador);
                        }
                        contador++;
                    }
                    nombre.setText(articulo.getDescripcion());
                    String estadoS=(articulo.getEstado());
                    if(estadoS.equalsIgnoreCase("ACTIVO")){
                        estado.setSelectedIndex(0);
                    }else{
                        estado.setSelectedIndex(1);
                    }
                    Select_TipoArticulo.setSelectedIndex(0);
                    Select_TipoArticulo.setEnabled(true);
                    nombre.setEnabled(true);
                    estado.setEnabled(true);
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
                    //DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                    
                    Articulo articulo=listadoArticulo.get(fila1);
                    codigo.setText(articulo.getCodigo());
                    int contador=0;
                    for(TipoArticulo tip_art : listadoTipoArticulo){
                        if(articulo.getTipoArticulo().getDescripcion().equals(tip_art.getDescripcion())){
                            Select_TipoArticulo.setSelectedIndex(contador);
                        }
                        contador++;
                    }
                    nombre.setText(articulo.getDescripcion());
                    String estadoS=(articulo.getEstado());
                    if(estadoS.equalsIgnoreCase("ACTIVO")){
                        estado.setSelectedIndex(0);
                    }else{
                        estado.setSelectedIndex(1);
                    }
                    Select_TipoArticulo.setSelectedIndex(0);
                    Select_TipoArticulo.setEnabled(true);
                    nombre.setEnabled(true);
                    estado.setEnabled(true);
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
    private javax.swing.JComboBox<String> Select_TipoArticulo;
    private javax.swing.JLabel alerta_nombre;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JLabel codigo;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código","TipoArticulo", "Nombre","CódigoERP","Unidad_Negocio","Estado"});  
        listadoArticulo=new ControlDB_Articulo(tipoConexion).buscar(valorConsulta);
        for (Articulo listado1 : listadoArticulo) {
            String[] registro = new String[6];
            registro[0] = "" + listado1.getCodigo();
            registro[1] = "" + listado1.getTipoArticulo().getDescripcion();
            registro[2] = "" + listado1.getDescripcion();
            registro[3] = "" + listado1.getTipoArticulo().getCodigoERP();
            registro[4] = "" + listado1.getTipoArticulo().getUnidadNegocio();
            registro[5] = "" + listado1.getEstado();
            modelo.addRow(registro);      
        }
        tabla.setModel(modelo);
    }

}
