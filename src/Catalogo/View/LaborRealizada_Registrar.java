package Catalogo.View;
  
import Catalogo.Controller.ControlDB_CentroCostoSubCentro;
import Catalogo.Controller.ControlDB_CentroOperacion;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Controller.ControlDB_LaborRealizada;
import Catalogo.Model.CentroOperacion;
import Catalogo.Model.LaborRealizada;
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

public  class LaborRealizada_Registrar extends javax.swing.JPanel { 
    Usuario user;
    ArrayList<CentroCostoSubCentro> listadoCentroCostoSubCentro;
    private String tipoConexion;
    private ArrayList<CentroOperacion> listadoCentroOperacion = null;
    
    public LaborRealizada_Registrar(Usuario us, String tipoConexion) {
        user=us;
        initComponents();
        this.tipoConexion= tipoConexion;
        listadoCentroCostoSubCentro= new ArrayList();
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
        //Listamos las Labores realizadas
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las labores realizadas");
            Logger.getLogger(LaborRealizada_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        operativa.setSelected(true);
        parada.setSelected(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        subcentroCosto = new javax.swing.JComboBox<>();
        parada = new javax.swing.JRadioButton();
        operativa = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        bodegaDestion = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        titulo28 = new javax.swing.JLabel();
        centroOperacion = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 330, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Nombre:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 210, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("LISTADO DE ACTIVIDADES");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 20, 340, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Bodega Destino:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 210, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadoActionPerformed(evt);
            }
        });
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 330, 30));

        btnRegistrar.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Ingresar.png"))); // NOI18N
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, 150, 40));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 150, 40));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 770, 580));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Subcentro de Costo:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 210, 30));

        subcentroCosto.setToolTipText("");
        add(subcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 330, 30));

        parada.setBackground(new java.awt.Color(255, 255, 255));
        parada.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        parada.setText("PARADA");
        parada.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                paradaItemStateChanged(evt);
            }
        });
        add(parada, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, 80, 30));

        operativa.setBackground(new java.awt.Color(255, 255, 255));
        operativa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        operativa.setText("OPERATIVA");
        operativa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                operativaItemStateChanged(evt);
            }
        });
        add(operativa, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 100, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Estado:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 210, 30));

        bodegaDestion.setBackground(new java.awt.Color(255, 255, 255));
        bodegaDestion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bodegaDestion.setText("SI");
        bodegaDestion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                bodegaDestionItemStateChanged(evt);
            }
        });
        add(bodegaDestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 90, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Tipo Labor Realizada:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 210, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("REGISTRO DE ACTIVIDADES");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 350, 30));

        titulo28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo28.setForeground(new java.awt.Color(51, 51, 51));
        titulo28.setText("Centro Operación:");
        add(titulo28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 210, 30));

        centroOperacion.setToolTipText("");
        centroOperacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                centroOperacionItemStateChanged(evt);
            }
        });
        add(centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 330, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if(nombre.getText().equals("")){
            JOptionPane.showMessageDialog(null, "El nombre de la labor realizada no puede estar vacia","Advertencia", JOptionPane.ERROR_MESSAGE);
        }else{    
            LaborRealizada laborRealizada = new LaborRealizada();
            laborRealizada.setDescripcion(nombre.getText());
            try{
                laborRealizada.setCentroCostoSubCentro(listadoCentroCostoSubCentro.get(subcentroCosto.getSelectedIndex()));
                //Validamos si selecciono activo o inactivo
                if(estado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                    laborRealizada.setEstado("1");
                }else{
                    laborRealizada.setEstado("0");
                }
                if(operativa.isSelected()){
                    laborRealizada.setEs_operativa("1");
                }else{
                    laborRealizada.setEs_operativa("0");
                }
                if(parada.isSelected()){
                    laborRealizada.setEs_parada("1");
                }else{
                    laborRealizada.setEs_parada("0");
                }
                if(bodegaDestion.isSelected()){
                    laborRealizada.setBodegaDestino("1");
                }else{
                    laborRealizada.setBodegaDestino("0");
                }
                try {
                    int respuesta=new ControlDB_LaborRealizada(tipoConexion).registrar(laborRealizada,user);
                    if(respuesta==1){
                        JOptionPane.showMessageDialog(null, "Se registro la labor realizada de forma exitosa");
                        nombre.setText("");
                        estado.setSelectedIndex(0);
                        operativa.setSelected(true);
                        parada.setSelected(false);
                        tabla_Listar("");
                    }else{
                        if(respuesta==0){
                            JOptionPane.showMessageDialog(null, "No se pudo registrar la labor realizada, valide datos");
                        }else{
                            if(respuesta==3){
                                JOptionPane.showMessageDialog(null, "Ya existe una labor Realizada con ese Nombre para ese Subcentro de Costo, valide datos");
                            } 
                        }
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Error al registrar la labor realizada");
                    Logger.getLogger(LaborRealizada_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(LaborRealizada_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(LaborRealizada_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SocketException ex) {
                    Logger.getLogger(LaborRealizada_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Valide el Subcentro de Costo");
            }
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
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
        //Listamos las Labores realizadas
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las labores realizadas");
            Logger.getLogger(LaborRealizada_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        operativa.setSelected(true);
        parada.setSelected(false);
        nombre.setText("");
        estado.setSelectedIndex(0);
        bodegaDestion.setSelected(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

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

    private void estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bodegaDestion;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> centroOperacion;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre;
    private javax.swing.JRadioButton operativa;
    private javax.swing.JRadioButton parada;
    private javax.swing.JComboBox<String> subcentroCosto;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel titulo28;
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
