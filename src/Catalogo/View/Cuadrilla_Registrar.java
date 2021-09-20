package Catalogo.View;

import Catalogo.Controller.ControlDB_Equipo;
import Catalogo.Controller.ControlDB_PertenenciaEquipo;
import Catalogo.Controller.ControlDB_ProveedorEquipo;
import Catalogo.Model.ClasificadorEquipo;
import Catalogo.Model.Equipo;
import Catalogo.Model.Pertenencia;
import Catalogo.Model.ProveedorEquipo;
import Catalogo.Model.TipoEquipo;
import Sistema.Model.Usuario;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Cuadrilla_Registrar extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;
    public Cuadrilla_Registrar(Usuario us, String tipoConexion) throws SQLException {
        initComponents();
        user=us;
        this.tipoConexion= tipoConexion;
        //Cargamos las cuadrillas en la interfaz de Registro de cuadrilla
        for(int i = 1; i<= 50; i++){
           cuadrilla.addItem(""+i);  
        }

        //Cargamos el consecutivo para registro de paleros
        consecutivo.setText(""+ new ControlDB_Equipo(tipoConexion).CargarConsecutivoSiguiente());

        //Cargamos en la interfaz los proveedores de equipos
        try {
             ArrayList<ProveedorEquipo> listadoProveedorEquipo = new ArrayList();
            listadoProveedorEquipo=new ControlDB_ProveedorEquipo(tipoConexion).buscarActivos();
            for(int i=0; i< listadoProveedorEquipo.size(); i++){
                equipoProveedorEquipo.addItem(""+listadoProveedorEquipo.get(i).getCodigo()+":"+listadoProveedorEquipo.get(i).getDescripcion());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cuadrilla_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
         //Cargamos en la interfaz las pertenencias de Equipos
        try {
             ArrayList<Pertenencia> listadoPertenencia = new ArrayList();
            listadoPertenencia=new ControlDB_PertenenciaEquipo(tipoConexion).buscarActivos();
            for(int i=0; i< listadoPertenencia.size(); i++){
                equipoPertenencia.addItem(""+listadoPertenencia.get(i).getCodigo()+":"+listadoPertenencia.get(i).getDescripcion());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cuadrilla_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        equipoEstado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btn_registrar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        equipoObservacion = new javax.swing.JTextPane();
        consecutivo = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        equipoProveedorEquipo = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        equipoPertenencia = new javax.swing.JComboBox<>();
        cuadrilla = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        Editar.setText("Modificar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Observación:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 100, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Estado:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 120, 30));

        equipoEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        equipoEstado.setToolTipText("");
        add(equipoEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 370, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("REGISTRO DE CUADRILLAS");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 440, 30));

        btn_registrar.setBackground(new java.awt.Color(255, 255, 255));
        btn_registrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_registrar.setText("REGISTRAR");
        btn_registrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_registrarMouseExited(evt);
            }
        });
        btn_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrarActionPerformed(evt);
            }
        });
        add(btn_registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 430, 140, 30));

        btn_cancelar.setBackground(new java.awt.Color(255, 255, 255));
        btn_cancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cancelar.setText("CANCELAR");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 430, 140, 30));

        jScrollPane2.setViewportView(equipoObservacion);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 370, 110));

        consecutivo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        consecutivo.setText("____________");
        add(consecutivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 110, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Proveedor Equipo:");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 140, 30));

        equipoProveedorEquipo.setToolTipText("");
        add(equipoProveedorEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 370, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Pertenencia:");
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 140, 30));

        equipoPertenencia.setToolTipText("");
        add(equipoPertenencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 370, 30));

        cuadrilla.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cuadrillaItemStateChanged(evt);
            }
        });
        add(cuadrilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 110, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Cuadrilla #:");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 110, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Código:");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 110, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
       
    }//GEN-LAST:event_EditarActionPerformed

    private void btn_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrarActionPerformed
        Equipo Objeto = new Equipo();
        Objeto.setCodigo(consecutivo.getText());    
        Objeto.setTipoEquipo(new TipoEquipo("0"));    
        Objeto.setCodigo_barra("N/A");
        Objeto.setReferencia("N/A");
        Objeto.setProducto("CUADRILLA");
        Objeto.setCapacidad("N/A");
        Objeto.setMarca("CUADRILLA");
        Objeto.setModelo(cuadrilla.getSelectedItem().toString());
        Objeto.setSerial("N/A");
        Objeto.setDescripcion("CUADRILLA");
        Objeto.setClasificador1(new ClasificadorEquipo("0"));
        Objeto.setClasificador2(new ClasificadorEquipo("0"));
        String[] datosProveedorEquipo= equipoProveedorEquipo.getSelectedItem().toString().split(":");
        Objeto.setProveedorEquipo(new ProveedorEquipo(datosProveedorEquipo[0],datosProveedorEquipo[1],"ACTIVO"));
        String[] datosPertenenciaEquipo= equipoPertenencia.getSelectedItem().toString().split(":");
        Objeto.setPertenenciaEquipo(new Pertenencia(datosPertenenciaEquipo[0],datosPertenenciaEquipo[1],"ACTIVO"));
        Objeto.setObservacion(equipoObservacion.getText());
        if(equipoEstado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
            Objeto.setEstado("1");
        }else{
            Objeto.setEstado("0");
        }
        int respuesta=0;
        try {
            respuesta = new ControlDB_Equipo(tipoConexion).registrarPalero(Objeto, user);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cuadrilla_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cuadrilla_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(Cuadrilla_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(respuesta==1){
            JOptionPane.showMessageDialog(null, "Se registro el equipo de manera exitosa");
            
            try {
                //Cargamos el consecutivo para registro de paleros
                consecutivo.setText(""+ new ControlDB_Equipo(tipoConexion).CargarConsecutivoSiguiente());
            } catch (SQLException ex) {
                Logger.getLogger(Cuadrilla_Registrar.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            cuadrilla.setSelectedIndex(0);
            equipoProveedorEquipo.setSelectedIndex(0);
            equipoPertenencia.setSelectedIndex(0);
            equipoEstado.setSelectedIndex(0);
            equipoObservacion.setText("");
            //tabla_Listar("");
        }else{
            if(respuesta==0){
                JOptionPane.showMessageDialog(null, "No se pudo registrar la cuadrilla, valide información suministrada", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }
                                                                           
    }//GEN-LAST:event_btn_registrarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        try {
            //Cargamos el consecutivo para registro de paleros
            consecutivo.setText(""+ new ControlDB_Equipo(tipoConexion).CargarConsecutivoSiguiente());
        } catch (SQLException ex) {
            Logger.getLogger(Cuadrilla_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }  
        cuadrilla.setSelectedIndex(0);
        equipoProveedorEquipo.setSelectedIndex(0);
        equipoPertenencia.setSelectedIndex(0);
        equipoEstado.setSelectedIndex(0);
        equipoObservacion.setText("");
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_registrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_registrarMouseExited
        
    }//GEN-LAST:event_btn_registrarMouseExited

    private void cuadrillaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cuadrillaItemStateChanged
        
    }//GEN-LAST:event_cuadrillaItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_registrar;
    private javax.swing.JLabel consecutivo;
    private javax.swing.JComboBox<String> cuadrilla;
    private javax.swing.JComboBox<String> equipoEstado;
    private javax.swing.JTextPane equipoObservacion;
    private javax.swing.JComboBox<String> equipoPertenencia;
    private javax.swing.JComboBox<String> equipoProveedorEquipo;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
    
}
