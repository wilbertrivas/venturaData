package Catalogo.View23;
    
import Consumo.View.Unidad_Registrar;
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

public final class CentroCostoAuxiliar_Actualizar extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;
    private ArrayList<CentroOperacion> listadoCentroOperacion = new ArrayList();
    private ArrayList<CentroCostoSubCentro> listadoCentroCostoSubCentro = new ArrayList();
    private ArrayList<CentroCostoAuxiliar> listadoCentroCostoAuxiliar=null;
    
    public CentroCostoAuxiliar_Actualizar(Usuario us, String tipoConexion) {
        user=us;
        initComponents();
        this.tipoConexion= tipoConexion;
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los centro de costos auxiliares");
            Logger.getLogger(Unidad_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        centroOperacion.setEnabled(false);
        subcentroCosto.setEnabled(false);
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
        btn_Actualizar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        valorBusqueda = new javax.swing.JTextField();
        consultar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        subcentroCosto = new javax.swing.JComboBox<>();
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
        add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 330, 30));

        codigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        codigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 330, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("ACTUALIZACIÓN DE CENTRO DE COSTOS AUXILIARES");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 520, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Estado:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 80, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 330, 30));

        btn_Actualizar.setBackground(new java.awt.Color(255, 255, 255));
        btn_Actualizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_Actualizar.setText("ACTUALIZAR");
        btn_Actualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ActualizarMouseExited(evt);
            }
        });
        btn_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ActualizarActionPerformed(evt);
            }
        });
        add(btn_Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 150, 40));

        btn_cancelar.setBackground(new java.awt.Color(255, 255, 255));
        btn_cancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cancelar.setText("CANCELAR");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, 140, 40));

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
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 80, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Código:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 80, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Subcentro de Costo:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 160, 30));

        subcentroCosto.setToolTipText("");
        add(subcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 330, 30));

        titulo28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo28.setForeground(new java.awt.Color(51, 51, 51));
        titulo28.setText("Centro Operación:");
        add(titulo28, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 150, 30));

        centroOperacion.setToolTipText("");
        centroOperacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                centroOperacionItemStateChanged(evt);
            }
        });
        add(centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 330, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        codigo.setText("");
        subcentroCosto.setSelectedIndex(0);
        nombre.setText("");
        estado.setSelectedIndex(0);
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ActualizarActionPerformed
        if(codigo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Debe carga un Auxiliar de centro de costo","Advertencia",JOptionPane.INFORMATION_MESSAGE);
        }else{
            if(nombre.getText().equals("")){
                JOptionPane.showMessageDialog(null,"El nombre del centro de costo auxiliar no puede estar vacio","Advertencia",JOptionPane.INFORMATION_MESSAGE);
            }else{
                CentroCostoAuxiliar centroCostoAuxiliar = new CentroCostoAuxiliar();
                centroCostoAuxiliar.setCodigo(Integer.parseInt(codigo.getText()));
                centroCostoAuxiliar.setDescripcion(nombre.getText());
                centroCostoAuxiliar.setCentroCostoSubCentro(listadoCentroCostoSubCentro.get(subcentroCosto.getSelectedIndex()));
                //Validamos si selecciono activo o inactivo
                if(estado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                    centroCostoAuxiliar.setEstado("1");
                }else{
                    centroCostoAuxiliar.setEstado("0");
                }
                try {
                    if(!new ControlDB_CentroCostoAuxiliar(tipoConexion).validarExistenciaActualizar(centroCostoAuxiliar)){
                        int respuesta = 0;
                        try {
                            respuesta = new ControlDB_CentroCostoAuxiliar(tipoConexion).actualizar(centroCostoAuxiliar, user);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(CentroCostoAuxiliar_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(respuesta==1){
                            JOptionPane.showMessageDialog(null, "Se actualizo el centro de costo auxiliar correctamente");
                            codigo.setText("");
                            centroOperacion.setSelectedIndex(0);
                            subcentroCosto.setSelectedIndex(0);
                            nombre.setText("");
                            estado.setSelectedIndex(0);
                            centroOperacion.setEnabled(false);
                            subcentroCosto.setEnabled(false);
                            nombre.setEnabled(false);
                            estado.setEnabled(false);
                            tabla_Listar("");
                        }else{
                            if(respuesta==0){
                                JOptionPane.showMessageDialog(null, "No se pudo actualizar el centro de costo auxiliar, valide los datos ingresados");
                            }
                        }  
                    }else{
                        JOptionPane.showMessageDialog(null, "Ya existe un Auxiliar de Centro de Costo con el mismo nombre, valide la información","Advertencia", JOptionPane.INFORMATION_MESSAGE);
                    } 
                } catch (SQLException ex) {
                    Logger.getLogger(CentroCostoAuxiliar_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(CentroCostoAuxiliar_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SocketException ex) {
                    Logger.getLogger(CentroCostoAuxiliar_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btn_ActualizarActionPerformed

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        try {
            tabla_Listar(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(CentroCostoAuxiliar_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_consultarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        int fila1;
        try{
            fila1=tabla.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                if(listadoCentroCostoAuxiliar != null){
                    CentroCostoAuxiliar Objeto=listadoCentroCostoAuxiliar.get(fila1);
                    if(Objeto != null){
                        codigo.setText(""+Objeto.getCodigo());
                        int contador=0;
                        for(CentroOperacion centroOperacion1: listadoCentroOperacion){
                            if(centroOperacion1.getCodigo()== Objeto.getCentroCostoSubCentro().getCentroOperacion().getCodigo()){
                                centroOperacion.setSelectedIndex(contador);
                            }
                            contador++;
                        }
                        contador=0;
                        for(CentroCostoSubCentro centroCostoSubCentro: listadoCentroCostoSubCentro){
                            if(centroCostoSubCentro.getCodigo()== Objeto.getCentroCostoSubCentro().getCodigo()){
                                subcentroCosto.setSelectedIndex(contador);
                            }
                            contador++;
                        }
                        nombre.setText(Objeto.getDescripcion());  
                        String estadoS=Objeto.getEstado();
                        if(estadoS.equalsIgnoreCase("ACTIVO")){
                            estado.setSelectedIndex(0);
                        }else{
                            estado.setSelectedIndex(1);
                        }
                        centroOperacion.setEnabled(true);
                        subcentroCosto.setEnabled(true);
                        nombre.setEnabled(true);
                        estado.setEnabled(true);
                    }
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
                    if(listadoCentroCostoAuxiliar != null){
                        CentroCostoAuxiliar Objeto=listadoCentroCostoAuxiliar.get(fila1);
                        if(Objeto != null){
                            codigo.setText(""+Objeto.getCodigo());
                            int contador=0;
                            for(CentroOperacion centroOperacion1: listadoCentroOperacion){
                                if(centroOperacion1.getCodigo()== Objeto.getCentroCostoSubCentro().getCentroOperacion().getCodigo()){
                                    centroOperacion.setSelectedIndex(contador);
                                }
                                contador++;
                            }
                            contador=0;
                            for(CentroCostoSubCentro centroCostoSubCentro: listadoCentroCostoSubCentro){
                                if(centroCostoSubCentro.getCodigo()== Objeto.getCentroCostoSubCentro().getCodigo()){
                                    subcentroCosto.setSelectedIndex(contador);
                                }
                                contador++;
                            }
                            nombre.setText(Objeto.getDescripcion());  
                            String estadoS=Objeto.getEstado();
                            if(estadoS.equalsIgnoreCase("ACTIVO")){
                                estado.setSelectedIndex(0);
                            }else{
                                estado.setSelectedIndex(1);
                            }
                            centroOperacion.setEnabled(true);
                            subcentroCosto.setEnabled(true);
                            nombre.setEnabled(true);
                            estado.setEnabled(true);
                        }
                    }
                }
            }catch(Exception e){
            }
        } 
    }//GEN-LAST:event_tablaMouseClicked

    private void btn_ActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ActualizarMouseExited
        
    }//GEN-LAST:event_btn_ActualizarMouseExited

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JButton btn_Actualizar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JComboBox<String> centroOperacion;
    private javax.swing.JLabel codigo;
    private javax.swing.JButton consultar;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre;
    private javax.swing.JComboBox<String> subcentroCosto;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel titulo28;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException{             
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "CentroOperación","Subcentro Costo", "Centro CostoAuxiliar", "Estado"});  
         listadoCentroCostoAuxiliar=new ControlDB_CentroCostoAuxiliar(tipoConexion).buscar(valorConsulta);
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
