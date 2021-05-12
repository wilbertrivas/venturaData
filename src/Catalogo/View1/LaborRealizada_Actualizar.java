package Catalogo.View1;

import Catalogo.Controller.ControlDB_CentroCostoSubCentro;
import Catalogo.Controller.ControlDB_CentroOperacion;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Controller.ControlDB_LaborRealizada;
import Catalogo.Model.CentroOperacion;
import Catalogo.Model.LaborRealizada;
import ModuloEquipo.View2.Solicitud_Equipos_Registrar;
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

public final class LaborRealizada_Actualizar extends javax.swing.JPanel {
    Usuario user;
    ArrayList<CentroCostoSubCentro> listadoCentroCostoSubCentro;
    private ArrayList<CentroOperacion> listadoCentroOperacion = null;
    private String tipoConexion;
    
    public LaborRealizada_Actualizar(Usuario us,String tipoConexion) {
        user=us;
        initComponents();
        this.tipoConexion= tipoConexion;
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
        //Cargamos en la interfaz los subcentro de costos activos
        try {
            if(listadoCentroOperacion !=null){
                listadoCentroCostoSubCentro=new ControlDB_CentroCostoSubCentro(tipoConexion).buscarActivos(listadoCentroOperacion.get(centroOperacion.getSelectedIndex()));
                if(listadoCentroCostoSubCentro != null){
                    String datosObjeto[]= new String[listadoCentroCostoSubCentro.size()];
                    int contador=0;
                    for(CentroCostoSubCentro Objeto : listadoCentroCostoSubCentro){ 
                        datosObjeto[contador]=Objeto.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    subcentroCosto.setModel(model);
                } 
            }
      
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las labores realizadas");
            Logger.getLogger(LaborRealizada_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        nombre.setEnabled(false);
        centroOperacion.setEnabled(false);
        subcentroCosto.setEnabled(false);
        estado.setEnabled(false);
        operativa.setEnabled(false);
        bodegaDestion.setEnabled(false);
        parada.setEnabled(false);
        operativa.setSelected(true);
        parada.setSelected(false);
        bodegaDestion.setSelected(false);
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
        subcentroCosto = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        operativa = new javax.swing.JRadioButton();
        parada = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        bodegaDestion = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        titulo28 = new javax.swing.JLabel();
        centroOperacion = new javax.swing.JComboBox<>();

        Editar.setText("Modificar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 310, 30));

        codigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 310, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("ACTUALIZACIÓN DE ACTIVIDADES");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 550, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Estado:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 160, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, 310, 30));

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
        add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, 150, 40));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 150, 40));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, 960, 580));
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 390, 40));

        btnConsultar.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        btnConsultar.setText("CONSULTAR");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 60, 140, 40));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Nombre:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 160, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Código:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 160, 30));

        subcentroCosto.setToolTipText("");
        add(subcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 310, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Subcentro de Costo:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 160, 30));

        operativa.setBackground(new java.awt.Color(255, 255, 255));
        operativa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        operativa.setText("OPERATIVA");
        operativa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                operativaItemStateChanged(evt);
            }
        });
        add(operativa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, -1, -1));

        parada.setBackground(new java.awt.Color(255, 255, 255));
        parada.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        parada.setText("PARADA");
        parada.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                paradaItemStateChanged(evt);
            }
        });
        add(parada, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Tipo actividad:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 160, 30));

        bodegaDestion.setBackground(new java.awt.Color(255, 255, 255));
        bodegaDestion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bodegaDestion.setText("SI");
        bodegaDestion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                bodegaDestionItemStateChanged(evt);
            }
        });
        bodegaDestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bodegaDestionActionPerformed(evt);
            }
        });
        add(bodegaDestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, 140, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Bodega Destino:");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 160, 30));

        titulo28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo28.setForeground(new java.awt.Color(51, 51, 51));
        titulo28.setText("Centro Operación:");
        add(titulo28, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 160, 30));

        centroOperacion.setToolTipText("");
        centroOperacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                centroOperacionItemStateChanged(evt);
            }
        });
        add(centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 310, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        codigo.setText("");
        nombre.setText("");
        estado.setSelectedIndex(0);
        operativa.setSelected(true);
        parada.setSelected(false);
        bodegaDestion.setSelected(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if(codigo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Error.. Se debe carga una labor realizada", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            if(nombre.getText().equals("")){
                JOptionPane.showMessageDialog(null, "El nombre de la labor realizada no puede estar vacia","Advertencia", JOptionPane.INFORMATION_MESSAGE);
            }else{
                LaborRealizada Objeto = new LaborRealizada();
                Objeto.setCodigo(codigo.getText());
                Objeto.setDescripcion(nombre.getText());
                //Validamos si selecciono activo o inactivo
                if(estado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                    Objeto.setEstado("1");
                }else{
                    Objeto.setEstado("0");
                }
                if(operativa.isSelected()){
                    Objeto.setEs_operativa("1");
                }else{
                    Objeto.setEs_operativa("0");
                }
                if(parada.isSelected()){
                    Objeto.setEs_parada("1");
                }else{
                    Objeto.setEs_parada("0");
                }
                if(bodegaDestion.isSelected()){
                    Objeto.setBodegaDestino("1");
                }else{
                    Objeto.setBodegaDestino("0");
                }
                try {
                    //if(!new ControlDB_LaborRealizada().validarExistenciaActualizar(Objeto)){
                        int respuesta=new ControlDB_LaborRealizada(tipoConexion).actualizar(Objeto, user);
                        if(respuesta==1){
                            JOptionPane.showMessageDialog(null, "Se actualizo la labor Realizada");
                            nombre.setText("");
                            subcentroCosto.setSelectedIndex(0);
                            estado.setSelectedIndex(0);
                            tabla_Listar("");
                            codigo.setText("");
                            nombre.setEnabled(false);
                            estado.setEnabled(false);
                            operativa.setEnabled(false);
                            parada.setEnabled(false);
                            bodegaDestion.setEnabled(false);
                            operativa.setSelected(true);
                            parada.setSelected(false);
                            bodegaDestion.setSelected(false);
                        }else{
                            if(respuesta==0){
                                JOptionPane.showMessageDialog(null, "No se pudo actualizar la labor realizada, valide los datos ingresados");
                            }
                        }
                    //}else{
                      //  JOptionPane.showMessageDialog(null, "Ya existe una actividad operacional con el mismo nombre, valide los datos ingresados", "Advertencia", JOptionPane.ERROR_MESSAGE);
                   // }
                    
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Error al registrar la labor realizada,");
                    Logger.getLogger(LaborRealizada_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(LaborRealizada_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(LaborRealizada_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SocketException ex) {
                    Logger.getLogger(LaborRealizada_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        try {
            tabla_Listar(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(LaborRealizada_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
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
                codigo.setText((String)modelo.getValueAt(fila1, 0));
                nombre.setText((String)modelo.getValueAt(fila1, 1));
                subcentroCosto.setSelectedItem((String)modelo.getValueAt(fila1, 2));
                String estadoS=(String)modelo.getValueAt(fila1, 3);
                if(estadoS.equalsIgnoreCase("ACTIVO")){
                    estado.setSelectedIndex(0);
                }else{
                    estado.setSelectedIndex(1);
                }
                String operativaS=(String)modelo.getValueAt(fila1, 4);
                if(operativaS.equalsIgnoreCase("SI")){
                    operativa.setSelected(true);
                }else{
                    operativa.setSelected(false);
                }
                String paradaS=(String)modelo.getValueAt(fila1, 5);
                if(paradaS.equalsIgnoreCase("SI")){
                    parada.setSelected(true);
                }else{
                    parada.setSelected(false);
                }
                String BodegaDestino=(String)modelo.getValueAt(fila1, 6);
                if(BodegaDestino.equalsIgnoreCase("SI")){
                    bodegaDestion.setSelected(true);
                }else{
                    bodegaDestion.setSelected(false);
                }            
                nombre.setEnabled(true);
                estado.setEnabled(true);
                operativa.setEnabled(true);
                parada.setEnabled(true);
                bodegaDestion.setEnabled(true);
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
                    subcentroCosto.setSelectedItem((String)modelo.getValueAt(fila1, 2));
                    String estadoS=(String)modelo.getValueAt(fila1, 3);
                    if(estadoS.equalsIgnoreCase("ACTIVO")){
                        estado.setSelectedIndex(0);
                    }else{
                        estado.setSelectedIndex(1);
                    }
                    String operativaS=(String)modelo.getValueAt(fila1, 4);
                    if(operativaS.equalsIgnoreCase("SI")){
                        operativa.setSelected(true);
                    }else{
                        operativa.setSelected(false);
                    }
                    String paradaS=(String)modelo.getValueAt(fila1, 5);
                    if(paradaS.equalsIgnoreCase("SI")){
                        parada.setSelected(true);
                    }else{
                        parada.setSelected(false);
                    }
                    String BodegaDestino=(String)modelo.getValueAt(fila1, 6);
                    if(BodegaDestino.equalsIgnoreCase("SI")){
                        bodegaDestion.setSelected(true);
                    }else{
                        bodegaDestion.setSelected(false);
                    }            
                    nombre.setEnabled(true);
                    estado.setEnabled(true);
                    operativa.setEnabled(true);
                    parada.setEnabled(true);
                    bodegaDestion.setEnabled(true);
                }
            }catch(Exception e){
            }
        }   
    }//GEN-LAST:event_tablaMouseClicked

    private void btnActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseExited
       
    }//GEN-LAST:event_btnActualizarMouseExited

    private void operativaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_operativaItemStateChanged
        if(operativa.isSelected()){
            operativa.setSelected(true);
            parada.setSelected(false);
        }else{
            operativa.setSelected(false);
            parada.setSelected(true);
        }
    }//GEN-LAST:event_operativaItemStateChanged

    private void paradaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_paradaItemStateChanged
        if(parada.isSelected()){
            parada.setSelected(true);
            operativa.setSelected(false);
        }else{
            parada.setSelected(false);
            operativa.setSelected(true);
        }
    }//GEN-LAST:event_paradaItemStateChanged

    private void bodegaDestionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_bodegaDestionItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_bodegaDestionItemStateChanged

    private void centroOperacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_centroOperacionItemStateChanged
        //Cargamos en la interfaz los subcentro de costos activos
        try {
            if(listadoCentroOperacion !=null){
                listadoCentroCostoSubCentro=new ControlDB_CentroCostoSubCentro(tipoConexion).buscarActivos(listadoCentroOperacion.get(centroOperacion.getSelectedIndex()));
                if(listadoCentroCostoSubCentro != null){
                    String datosObjeto[]= new String[listadoCentroCostoSubCentro.size()];
                    int contador=0;
                    for(CentroCostoSubCentro Objeto : listadoCentroCostoSubCentro){ 
                        datosObjeto[contador]=Objeto.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    subcentroCosto.setModel(model);
                } 
            }
      
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_centroOperacionItemStateChanged

    private void bodegaDestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bodegaDestionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bodegaDestionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JRadioButton bodegaDestion;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JComboBox<String> centroOperacion;
    private javax.swing.JLabel codigo;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre;
    private javax.swing.JRadioButton operativa;
    private javax.swing.JRadioButton parada;
    private javax.swing.JComboBox<String> subcentroCosto;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel titulo28;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "Nombre","SubCentroCostos","Estado","Operativa","Parada","BodegaDestino"});  
        ArrayList<LaborRealizada> listado=new ControlDB_LaborRealizada(tipoConexion).buscar(valorConsulta);
        for (LaborRealizada listado1 : listado) {
            String[] registro = new String[7];
            registro[0] = "" + listado1.getCodigo();
            registro[1] = "" + listado1.getDescripcion();
            registro[2] = "" + listado1.getCentroCostoSubCentro().getDescripcion();
            registro[3] = "" + listado1.getEstado();
            if(listado1.getEs_operativa().equals("1")){
                registro[4] = "SI";
            }else{
                registro[4] = "NO";
            }
            if(listado1.getEs_parada().equals("1")){
                registro[5] = "SI";
            }else{
                registro[5] = "NO";
            }
            if(listado1.getBodegaDestino().equals("1")){
                registro[6] = "SI";
            }else{
                registro[6] = "NO";
            }
            modelo.addRow(registro);      
        }
        tabla.setModel(modelo);
        //resizeColumnWidth(tabla);
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
