package Catalogo.View23;
  
import Catalogo.Controller.ControlDB_CentroCostoSubCentro;
import Catalogo.Controller.ControlDB_CentroOperacion;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import ModuloEquipo.View.Solicitud_Equipos_Registrar;
import Sistema.Model.Usuario;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public final class CentroCostoSubcentro_Actualizar extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;
    private ArrayList<CentroOperacion> listadoCentroOperacion = new ArrayList();
    private ArrayList<CentroCostoSubCentro> listadoCentroCostoSubcentro= null;
    
    public CentroCostoSubcentro_Actualizar(Usuario us, String tipoConexion) {
        user=us;
        this.tipoConexion= tipoConexion;
        initComponents();
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los Subcentro de costos");
            Logger.getLogger(CentroCostoSubcentro_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
        nombre.setEnabled(false);
        centroOperacion.setEnabled(false);
        estado.setEnabled(false);
        requiereLaborRealizada.setEnabled(false);
        
        //Cargamos en la interfaz los centros de Operaciones
        try {
            listadoCentroOperacion=new ControlDB_CentroOperacion(tipoConexion).buscarActivos();
            if(listadoCentroOperacion != null){
                String datosObjeto[]= new String[listadoCentroOperacion.size()];
                int contador=0;
                for(CentroOperacion listadoCentroOperacion1 : listadoCentroOperacion){ 
                    datosObjeto[contador]=listadoCentroOperacion1.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                centroOperacion.setModel(model);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
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
        btnActualizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        valorBusqueda = new javax.swing.JTextField();
        btnConsultar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        alerta_nombre = new javax.swing.JLabel();
        titulo28 = new javax.swing.JLabel();
        centroOperacion = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        requiereLaborRealizada = new javax.swing.JComboBox<>();

        Editar.setText("Modificar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 310, 30));

        codigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 330, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("ACTUALIZACIÓN DE SUBCENTROS DE COSTOS");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 550, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Estado:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 130, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 310, 30));

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
        add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 140, 30));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, 140, 30));

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
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla.setComponentPopupMenu(Seleccionar);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, 620, 470));
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, 280, 30));

        btnConsultar.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultar.setText("CONSULTAR");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 60, 140, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Nombre:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 130, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Código:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 130, 30));

        alerta_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_nombre.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 380, 20));

        titulo28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo28.setForeground(new java.awt.Color(51, 51, 51));
        titulo28.setText("Centro Operación:");
        add(titulo28, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 130, 30));

        centroOperacion.setToolTipText("");
        add(centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 310, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("CentroCosto x LaborRealizada:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 230, 30));

        requiereLaborRealizada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "SI" }));
        requiereLaborRealizada.setToolTipText("");
        add(requiereLaborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 310, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        codigo.setText("");
        nombre.setText("");
        estado.setSelectedIndex(0);
        try{
            centroOperacion.setSelectedIndex(0);
            requiereLaborRealizada.setSelectedIndex(0);
        }catch(Exception e){}
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if(codigo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Error.. Se debe carga un Subcentro de costo", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            if(nombre.getText().equals("")){
                alerta_nombre.setText("El nombre del subcentro de costo no puede estar vacio");
            }else{
                CentroCostoSubCentro Objeto = new CentroCostoSubCentro();
                Objeto.setCodigo(Integer.parseInt(codigo.getText()));
                Objeto.setDescripcion(nombre.getText());
                //Validamos si selecciono activo o inactivo
                if(estado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                    Objeto.setEstado("1");
                }else{
                    Objeto.setEstado("0");
                }
                if(requiereLaborRealizada.getSelectedItem().toString().equalsIgnoreCase("NO")){
                    Objeto.setCentroCostoRequiereLaborRealizada("0");
                }else{
                    Objeto.setCentroCostoRequiereLaborRealizada("1");
                }
                try {
                    if(listadoCentroOperacion != null){
                        Objeto.setCentroOperacion(listadoCentroOperacion.get(centroOperacion.getSelectedIndex()));
                        if(!new ControlDB_CentroCostoSubCentro(tipoConexion).validarExistenciaActualizar(Objeto)){
                            int respuesta=new ControlDB_CentroCostoSubCentro(tipoConexion).actualizar(Objeto, user);
                            if(respuesta==1){
                                JOptionPane.showMessageDialog(null, "Se actualizo el subcentro de costo");
                                nombre.setText("");
                                estado.setSelectedIndex(0);
                                tabla_Listar("");
                                codigo.setText("");
                                nombre.setEnabled(false);
                                estado.setEnabled(false);
                                centroOperacion.setEnabled(false);
                            }else{
                                if(respuesta==0){
                                    JOptionPane.showMessageDialog(null, "No se pudo actualizar el subcentro de costo, valide los datos ingresados");
                                }
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Ya existe un subcentro de costo con el mismo nombre, valide los datos ingresados", "Advertencia", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Debe cargar un Centro de Operación, valide los datos ingresados", "Advertencia", JOptionPane.ERROR_MESSAGE);
                    }
                    
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Error al registrar el subcentro de costo");
                    Logger.getLogger(CentroCostoSubcentro_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CentroCostoSubcentro_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(CentroCostoSubcentro_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SocketException ex) {
                    Logger.getLogger(CentroCostoSubcentro_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        try {
            tabla_Listar(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(CentroCostoSubcentro_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
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
                DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                codigo.setText(""+listadoCentroCostoSubcentro.get(fila1).getCodigo());
                nombre.setText(listadoCentroCostoSubcentro.get(fila1).getDescripcion());
                String estadoS=(listadoCentroCostoSubcentro.get(fila1).getEstado());
                if(estadoS.equalsIgnoreCase("ACTIVO")){
                    estado.setSelectedIndex(0);
                }else{
                    estado.setSelectedIndex(1);
                }
                if(listadoCentroCostoSubcentro.get(fila1).getCentroCostoRequiereLaborRealizada().equalsIgnoreCase("0")){
                    requiereLaborRealizada.setSelectedIndex(0);
                }else{
                    requiereLaborRealizada.setSelectedIndex(1);
                }
                int contadorCentroOperacion=0;
                for(CentroOperacion objeto : listadoCentroOperacion){
                    if(objeto.getCodigo() == listadoCentroCostoSubcentro.get(fila1).getCentroOperacion().getCodigo()){
                        centroOperacion.setSelectedIndex(contadorCentroOperacion);
                    }
                    contadorCentroOperacion++;
                }
                nombre.setEnabled(true);
                centroOperacion.setEnabled(true);
                requiereLaborRealizada.setEnabled(true);
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
                    DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                    codigo.setText(""+listadoCentroCostoSubcentro.get(fila1).getCodigo());
                    nombre.setText(listadoCentroCostoSubcentro.get(fila1).getDescripcion());
                    String estadoS=(listadoCentroCostoSubcentro.get(fila1).getEstado());
                    if(estadoS.equalsIgnoreCase("ACTIVO")){
                        estado.setSelectedIndex(0);
                    }else{
                        estado.setSelectedIndex(1);
                    }
                    if(listadoCentroCostoSubcentro.get(fila1).getCentroCostoRequiereLaborRealizada().equalsIgnoreCase("0")){
                        requiereLaborRealizada.setSelectedIndex(0);
                    }else{
                        requiereLaborRealizada.setSelectedIndex(1);
                    }
                    int contadorCentroOperacion=0;
                    for(CentroOperacion objeto : listadoCentroOperacion){
                        if(objeto.getCodigo() == listadoCentroCostoSubcentro.get(fila1).getCentroOperacion().getCodigo()){
                            centroOperacion.setSelectedIndex(contadorCentroOperacion);
                        }
                        contadorCentroOperacion++;
                    }
                    nombre.setEnabled(true);
                    centroOperacion.setEnabled(true);
                    requiereLaborRealizada.setEnabled(true);
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
    private javax.swing.JLabel alerta_nombre;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JComboBox<String> centroOperacion;
    private javax.swing.JLabel codigo;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre;
    private javax.swing.JComboBox<String> requiereLaborRealizada;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel titulo28;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código","Centro Operación","Nombre","CentroCosto_RequiereActividad","Estado"});  
        listadoCentroCostoSubcentro=new ControlDB_CentroCostoSubCentro(tipoConexion).buscar(valorConsulta);
        for (CentroCostoSubCentro listado1 : listadoCentroCostoSubcentro) {
            String[] registro = new String[5];
            registro[0] = "" + listado1.getCodigo();
            registro[1] = "" + listado1.getCentroOperacion().getDescripcion();
            registro[2] = "" + listado1.getDescripcion();
            if(listado1.getCentroCostoRequiereLaborRealizada().equals("1")){
                registro[3] = "SI";
            }else{
                registro[3] = "NO";
            }
            registro[4] = "" + listado1.getEstado();
            modelo.addRow(registro);      
        }
        
        tabla.setModel(modelo);
        resizeColumnWidth(tabla);
    }
    public void resizeColumnWidth(JTable table) {
            final TableColumnModel columnModel = table.getColumnModel();
            for (int column = 0; column < table.getColumnCount(); column++) {
                int width = 15; // Min width
                for (int row = 0; row < table.getRowCount(); row++) {
                    TableCellRenderer renderer = table.getCellRenderer(row, column);
                    Component comp = table.prepareRenderer(renderer, row, column);
                    width = Math.max(comp.getPreferredSize().width +1 , width);
                }
                if(width > 300)
                    width=300;
                columnModel.getColumn(column).setPreferredWidth(width);
            }
    }

}
