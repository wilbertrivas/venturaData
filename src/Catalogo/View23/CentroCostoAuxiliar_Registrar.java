package Catalogo.View23;
  
import Catalogo.Controller.ControlDB_CentroCostoAuxiliar;
import Catalogo.Controller.ControlDB_CentroCostoSubCentro;
import Catalogo.Controller.ControlDB_CentroOperacion;
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import ModuloEquipo.View.Solicitud_Equipos_Registrar;
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

public class CentroCostoAuxiliar_Registrar extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;
    private ArrayList<CentroOperacion> listadoCentroOperacion = new ArrayList();
    private ArrayList<CentroCostoSubCentro> listadoCentroCostoSubCentro = new ArrayList();
    
    public CentroCostoAuxiliar_Registrar(Usuario us,String tipoConexion) {
        initComponents();
        user=us;   
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
                    for(CentroCostoSubCentro listadoCentroCostoSubCentro : listadoCentroCostoSubCentro){ 
                        datosObjeto[contador]=listadoCentroCostoSubCentro.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    subcentroCosto.setModel(model);
                } 
            }  
        } catch (SQLException ex) {
            Logger.getLogger(CentroCostoAuxiliar_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            Logger.getLogger(CentroCostoAuxiliar_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        jLabel4 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        subcentroCosto = new javax.swing.JComboBox<>();
        estado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btn_registrar_insumo1 = new javax.swing.JButton();
        btn_registrar_insumo2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
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

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Subcentro de Costo:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 160, 30));
        add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 330, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Nombre:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 100, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Estado:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 120, 30));

        subcentroCosto.setToolTipText("");
        add(subcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 330, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadoActionPerformed(evt);
            }
        });
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 310, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("REGISTRO DE CENTRO DE COSTO AUXILIARES");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 440, 30));

        btn_registrar_insumo1.setBackground(new java.awt.Color(255, 255, 255));
        btn_registrar_insumo1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_registrar_insumo1.setText("REGISTRAR");
        btn_registrar_insumo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_registrar_insumo1MouseExited(evt);
            }
        });
        btn_registrar_insumo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrar_insumo1ActionPerformed(evt);
            }
        });
        add(btn_registrar_insumo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, 140, 30));

        btn_registrar_insumo2.setBackground(new java.awt.Color(255, 255, 255));
        btn_registrar_insumo2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_registrar_insumo2.setText("CANCELAR");
        btn_registrar_insumo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrar_insumo2ActionPerformed(evt);
            }
        });
        add(btn_registrar_insumo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, 140, 30));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 1000, 240));

        titulo28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo28.setForeground(new java.awt.Color(51, 51, 51));
        titulo28.setText("Centro Operación:");
        add(titulo28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 150, 30));

        centroOperacion.setToolTipText("");
        centroOperacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                centroOperacionItemStateChanged(evt);
            }
        });
        add(centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 330, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
       
    }//GEN-LAST:event_EditarActionPerformed

    private void btn_registrar_insumo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrar_insumo1ActionPerformed
        if(listadoCentroCostoSubCentro != null){
            if(nombre.getText().equals("")){
                JOptionPane.showMessageDialog(null,"El nombre del auxiliar Centro de Costo no puede estar vacio","Advertencia", JOptionPane.ERROR_MESSAGE);
            }else{
                CentroCostoAuxiliar centroCostoAuxiliar = new CentroCostoAuxiliar();
                centroCostoAuxiliar.setCentroCostoSubCentro(listadoCentroCostoSubCentro.get(subcentroCosto.getSelectedIndex()));
                centroCostoAuxiliar.setDescripcion(nombre.getText());
                //Validamos si selecciono activo o inactivo
                if(estado.getSelectedItem().toString().equalsIgnoreCase("Activo")){
                    centroCostoAuxiliar.setEstado("1");
                }else{
                    centroCostoAuxiliar.setEstado("0");
                }
                try {
                    if(new ControlDB_CentroCostoAuxiliar(tipoConexion).validarExistencia(centroCostoAuxiliar)){
                        JOptionPane.showMessageDialog(null, "El centro de costo auxiliar ya exite en el sistema");
                    }else{
                        int respuesta= new ControlDB_CentroCostoAuxiliar(tipoConexion).registrar(centroCostoAuxiliar, user);
                        if(respuesta==1){
                            JOptionPane.showMessageDialog(null, "Se registro el centro de costo auxiliar de manera exitosa");
                            nombre.setText("");
                            centroOperacion.setSelectedIndex(0);
                            subcentroCosto.setSelectedIndex(0);
                            estado.setSelectedIndex(0);
                            tabla_Listar("");
                        }else{
                            if(respuesta==0){
                                JOptionPane.showMessageDialog(null, "No se pudo registrar el centro de costo auxiliar, valide datos");
                            }
                        }
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Error al registrar el centro de costo auxiliar");
                    Logger.getLogger(CentroCostoAuxiliar_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CentroCostoAuxiliar_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(CentroCostoAuxiliar_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SocketException ex) {
                    Logger.getLogger(CentroCostoAuxiliar_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null,"Se debe cargar un Subcentro de costo para hacer el registro","Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_registrar_insumo1ActionPerformed

    private void btn_registrar_insumo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrar_insumo2ActionPerformed
        centroOperacion.setSelectedIndex(0);
        subcentroCosto.setSelectedIndex(0);
        nombre.setText("");
        estado.setSelectedIndex(0);
    }//GEN-LAST:event_btn_registrar_insumo2ActionPerformed

    private void btn_registrar_insumo1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_registrar_insumo1MouseExited
       
    }//GEN-LAST:event_btn_registrar_insumo1MouseExited

    private void centroOperacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_centroOperacionItemStateChanged
        //Cargamos en la interfaz los subcentro de costos activos
        try {
            if(listadoCentroOperacion !=null){
                listadoCentroCostoSubCentro=new ControlDB_CentroCostoSubCentro(tipoConexion).buscarActivos(listadoCentroOperacion.get(centroOperacion.getSelectedIndex()));
                if(listadoCentroCostoSubCentro != null){
                    String datosObjeto[]= new String[listadoCentroCostoSubCentro.size()];
                    int contador=0;
                    for(CentroCostoSubCentro listadoCentroCostoSubCentro : listadoCentroCostoSubCentro){ 
                        datosObjeto[contador]=listadoCentroCostoSubCentro.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    subcentroCosto.setModel(model);
                } 
            }  
        } catch (SQLException ex) {
            Logger.getLogger(CentroCostoAuxiliar_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_centroOperacionItemStateChanged

    private void estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JButton btn_registrar_insumo1;
    private javax.swing.JButton btn_registrar_insumo2;
    private javax.swing.JComboBox<String> centroOperacion;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre;
    private javax.swing.JComboBox<String> subcentroCosto;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel titulo28;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException{             
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "CentroOperación","Subcentro Costo", "Centro CostoAuxiliar", "Estado"});  
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
