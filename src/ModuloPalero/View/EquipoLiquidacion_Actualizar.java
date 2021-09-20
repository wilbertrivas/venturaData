package ModuloPalero.View;

import Catalogo.Controller.ControlDB_TipoEquipo;
import Catalogo.Model.TipoEquipo;
import Catalogo.View.*;
import ModuloPalero.Controller.ControlDB_EquipoLiquidacion;
import ModuloPalero.Controller.ControlDB_Marcacion;
import ModuloPalero.Model.EquipoLiquidacion;
import ModuloPersonal.Model.Persona;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class EquipoLiquidacion_Actualizar extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;  
    ArrayList<EquipoLiquidacion> listadoEquipoLiquidacion=null;
    EquipoLiquidacion equipoLiquidacion;
    ArrayList<TipoEquipo> listadoTipoEquipo = new ArrayList();
    public EquipoLiquidacion_Actualizar(Usuario us, String tipoConexion) throws SQLException {
        user = us;
        initComponents();
        this.tipoConexion = tipoConexion;
        listadoTipoEquipo = new ControlDB_TipoEquipo(tipoConexion).buscarActivos();
        //Cargamos en la interfaz los TiposEquipos activos
        for (TipoEquipo TipoEquipo1 : listadoTipoEquipo) {
            equipoTipoEquipo.addItem(TipoEquipo1.getDescripcion());
        }

        try {
            tabla_Listar2("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los Equipos para liquidación");
            Logger.getLogger(Equipo_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
        }
        equipoCodigo.setEditable(false);
        equipoTipoEquipo.setEnabled(false);
        equipoCodigoBarra.setEditable(false);
        equipoReferencia.setEditable(false);
        equipoProducto.setEditable(false);
        equipoCapacidad.setEditable(false);
        equipoMarca.setEditable(false);
        equipoModelo.setEditable(false);
        equipoSerial.setEditable(false);
        equipoDescripcion.setEditable(false);
        actualizarEstado.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        valorBusqueda = new javax.swing.JTextField();
        consultar4 = new javax.swing.JButton();
        consultar5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        equipoCodigo = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        equipoModelo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        equipoTipoEquipo = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        equipoSerial = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        equipoCodigoBarra = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        equipoDescripcion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        equipoReferencia = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        equipoCapacidad = new javax.swing.JTextField();
        equipoMarca = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        equipoProducto = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        titulo = new javax.swing.JLabel();
        btn_Actualizar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        actualizarEstado = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();

        Editar.setText("Seleccionar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        valorBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorBusquedaActionPerformed(evt);
            }
        });
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, 340, 40));

        consultar4.setBackground(new java.awt.Color(255, 255, 255));
        consultar4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consultar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        consultar4.setText("CONSULTAR");
        consultar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultar4ActionPerformed(evt);
            }
        });
        add(consultar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 40, 140, 40));

        consultar5.setBackground(new java.awt.Color(255, 255, 255));
        consultar5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consultar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/clean.png"))); // NOI18N
        consultar5.setText("LIMPIAR");
        consultar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultar5ActionPerformed(evt);
            }
        });
        add(consultar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 40, 140, 40));

        tabla1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla1.setComponentPopupMenu(Seleccionar);
        tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 80, 620, 530));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("BUSQUEDA DE EQUIPOS PROGRAMADOS PARA LIQUIDAR");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, 620, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Código:");
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 110, 30));

        equipoCodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 290, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Modelo:");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 80, 30));

        equipoModelo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 310, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Tipo Equipo:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 110, 30));

        equipoTipoEquipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipoTipoEquipo.setToolTipText("");
        add(equipoTipoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 290, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Serial:");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 80, 30));

        equipoSerial.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoSerial, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, 310, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Código Barra:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 110, 30));

        equipoCodigoBarra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoCodigoBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 290, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Descripción:");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 80, 30));

        equipoDescripcion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 130, 310, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Equipo Referencia:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 110, 30));

        equipoReferencia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoReferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 290, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Capacidad:");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, 80, 30));

        equipoCapacidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, 310, 30));

        equipoMarca.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, 310, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Marca:");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, 80, 30));

        equipoProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(equipoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 290, 30));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Producto:");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 110, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 153));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("LISTADO DE PERSONAS ASIGNADAS AL EQUIPO DE LIQUIDACIÓN");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 810, 30));
        add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 800, -1));

        tabla2 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla2);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 810, 200));

        titulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo.setForeground(new java.awt.Color(0, 102, 102));
        titulo.setText("DETALLE DE EQUIPO PARA LIQUIDACIÓN");
        titulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 820, 30));

        btn_Actualizar.setBackground(new java.awt.Color(255, 255, 255));
        btn_Actualizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_Actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/registrar.png"))); // NOI18N
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
        add(btn_Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 530, 140, 40));

        btn_cancelar.setBackground(new java.awt.Color(255, 255, 255));
        btn_cancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btn_cancelar.setText("CANCELAR");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 530, 140, 40));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 810, 10));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("ESTADO:");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 70, 30));

        actualizarEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        actualizarEstado.setToolTipText("");
        add(actualizarEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 530, 270, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 820, 600));
    }// </editor-fold>//GEN-END:initComponents

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        int fila1;
        try {
            fila1 = tabla1.getSelectedRow();
            if (fila1 == -1) {
                JOptionPane.showMessageDialog(null, "no se ha seleccionando ningun Vehiculo");
            } else {
                
                equipoLiquidacion=null;
                equipoLiquidacion = listadoEquipoLiquidacion.get(fila1);
                equipoCodigo.setText(equipoLiquidacion.getEquipo().getCodigo());
                equipoTipoEquipo.setSelectedItem(equipoLiquidacion.getEquipo().getTipoEquipo().getDescripcion());
                equipoCodigoBarra.setText(equipoLiquidacion.getEquipo().getCodigo_barra());
                equipoReferencia.setText(equipoLiquidacion.getEquipo().getReferencia());
                equipoProducto.setText(equipoLiquidacion.getEquipo().getProducto());
                equipoCapacidad.setText(equipoLiquidacion.getEquipo().getCapacidad());
                equipoMarca.setText(equipoLiquidacion.getEquipo().getMarca());
                equipoModelo.setText(equipoLiquidacion.getEquipo().getModelo());
                equipoSerial.setText(equipoLiquidacion.getEquipo().getSerial());
                equipoDescripcion.setText(equipoLiquidacion.getEquipo().getDescripcion());
                if(equipoLiquidacion.getEstado().equals("ACTIVO")){//El equipo de liquidación está activo
                    actualizarEstado.setSelectedIndex(0);
                }else{//El equipoLiquidación está inactivo
                    actualizarEstado.setSelectedIndex(1);
                }
                actualizarEstado.setEnabled(true);
                //Hacemos una consulta para saber que personal está asociado este equipo para tema de liquidación
                //como primera opción vamos a limpiar los datos que se encuentra en la tabla donde se carga el personal.
                DefaultTableModel md =(DefaultTableModel)tabla2.getModel();
                int CantEliminar= tabla2.getRowCount() -1;
                for(int i =CantEliminar; i>=0; i--){
                        md.removeRow(i);
                }
                //como segunda opción vamos a validar que personas se encuentran asociada al equipo que estamos cargando en la interfaz.
                tabla_BuscarPersonasPorEquipoLiquidacion(equipoLiquidacion);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar el equipo", "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void consultar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultar4ActionPerformed
        try {
            tabla_Listar2(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(EquipoLiquidacion_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_consultar4ActionPerformed

    private void consultar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultar5ActionPerformed
        //Eliminar todos los elementos de una tabla 
        DefaultTableModel md =(DefaultTableModel)tabla1.getModel();
        int CantEliminar= tabla1.getRowCount() -1;
        for(int i =CantEliminar; i>=0; i--){
                md.removeRow(i);
        }
        valorBusqueda.setText("");
    }//GEN-LAST:event_consultar5ActionPerformed

    private void tabla2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla2MouseClicked
        
    }//GEN-LAST:event_tabla2MouseClicked

    private void tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla1MouseClicked
        if(evt.getClickCount()==2){
            int fila1;
            try {
                fila1 = tabla1.getSelectedRow();
                if (fila1 == -1) {
                    JOptionPane.showMessageDialog(null, "no se ha seleccionando ningun Vehiculo");
                } else {

                    equipoLiquidacion=null;
                    equipoLiquidacion = listadoEquipoLiquidacion.get(fila1);
                    equipoCodigo.setText(equipoLiquidacion.getEquipo().getCodigo());
                    equipoTipoEquipo.setSelectedItem(equipoLiquidacion.getEquipo().getTipoEquipo().getDescripcion());
                    equipoCodigoBarra.setText(equipoLiquidacion.getEquipo().getCodigo_barra());
                    equipoReferencia.setText(equipoLiquidacion.getEquipo().getReferencia());
                    equipoProducto.setText(equipoLiquidacion.getEquipo().getProducto());
                    equipoCapacidad.setText(equipoLiquidacion.getEquipo().getCapacidad());
                    equipoMarca.setText(equipoLiquidacion.getEquipo().getMarca());
                    equipoModelo.setText(equipoLiquidacion.getEquipo().getModelo());
                    equipoSerial.setText(equipoLiquidacion.getEquipo().getSerial());
                    equipoDescripcion.setText(equipoLiquidacion.getEquipo().getDescripcion());
                    if(equipoLiquidacion.getEstado().equals("ACTIVO")){//El equipo de liquidación está activo
                        actualizarEstado.setSelectedIndex(0);
                    }else{//El equipoLiquidación está inactivo
                        actualizarEstado.setSelectedIndex(1);
                    }
                    actualizarEstado.setEnabled(true);
                    //Hacemos una consulta para saber que personal está asociado este equipo para tema de liquidación
                    //como primera opción vamos a limpiar los datos que se encuentra en la tabla donde se carga el personal.
                    DefaultTableModel md =(DefaultTableModel)tabla2.getModel();
                    int CantEliminar= tabla2.getRowCount() -1;
                    for(int i =CantEliminar; i>=0; i--){
                            md.removeRow(i);
                    }
                    //como segunda opción vamos a validar que personas se encuentran asociada al equipo que estamos cargando en la interfaz.
                    tabla_BuscarPersonasPorEquipoLiquidacion(equipoLiquidacion);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo cargar el equipo", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_tabla1MouseClicked

    private void valorBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valorBusquedaActionPerformed

    private void btn_ActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ActualizarMouseExited

    }//GEN-LAST:event_btn_ActualizarMouseExited

    private void btn_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ActualizarActionPerformed
        if (equipoCodigo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Error!, debe de cargar un equipo de liquidación para proceder con la actualización", "Error!", JOptionPane.ERROR_MESSAGE);
        } else {
            if (equipoLiquidacion.getEstado().equals("ACTIVO") && actualizarEstado.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "No se realizó cambio sobre el estado del equipo para liquidación, no es necesario actualizar", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (equipoLiquidacion.getEstado().equals("INACTIVO") && actualizarEstado.getSelectedIndex() == 1) {
                    JOptionPane.showMessageDialog(null, "No se realizó cambio sobre el estado del equipo para liquidación, no es necesario actualizar", "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {//Se realizó cambio sobre el estado del equipo para liquidación por tal motivo si podemos proceder a actualizar
                    int respuesta;
                    try {
                        respuesta = new ControlDB_EquipoLiquidacion(tipoConexion).actualizar(equipoLiquidacion, user);
                        if (respuesta == 1) {
                            JOptionPane.showMessageDialog(null, "Se actualizo el equipo para liquidación de forma exitosa");
                            equipoLiquidacion = null;
                            equipoCodigo.setText("");
                            equipoTipoEquipo.setSelectedIndex(0);
                            equipoCodigoBarra.setText("");
                            equipoReferencia.setText("");
                            equipoProducto.setText("");
                            equipoCapacidad.setText("");
                            equipoMarca.setText("");
                            equipoModelo.setText("");
                            equipoSerial.setText("");
                            equipoDescripcion.setText("");
                            actualizarEstado.setSelectedIndex(0);
                            actualizarEstado.setEnabled(false);

                            //como primera opción vamos a limpiar los datos que se encuentra en la tabla donde se carga el personal.
                            DefaultTableModel md =(DefaultTableModel)tabla2.getModel();
                            int CantEliminar= tabla2.getRowCount() -1;
                            for(int i =CantEliminar; i>=0; i--){
                                    md.removeRow(i);
                            }
                    
                            //Actualizamos la tabla con los nuevos cambios
                            try {
                                tabla_Listar2(valorBusqueda.getText());
                            } catch (SQLException ex) {
                                Logger.getLogger(EquipoLiquidacion_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            if (respuesta == 0) {
                                JOptionPane.showMessageDialog(null, "No se pudo actualizar el estado apra el equipo de liquidación seleccionado, valide la información suministrada","Error!", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Marcacion_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(Marcacion_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SocketException ex) {
                        Logger.getLogger(Marcacion_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                }
            }
        }   
    }//GEN-LAST:event_btn_ActualizarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        //como primera opción vamos a limpiar los datos que se encuentra en la tabla donde se carga el personal.
        DefaultTableModel md =(DefaultTableModel)tabla2.getModel();
        int CantEliminar= tabla2.getRowCount() -1;
        for(int i =CantEliminar; i>=0; i--){
                md.removeRow(i);
        }
        
        equipoLiquidacion = null;
        equipoCodigo.setText("");
        equipoTipoEquipo.setSelectedIndex(0);
        equipoCodigoBarra.setText("");
        equipoReferencia.setText("");
        equipoProducto.setText("");
        equipoCapacidad.setText("");
        equipoMarca.setText("");
        equipoModelo.setText("");
        equipoSerial.setText("");
        equipoDescripcion.setText("");
        actualizarEstado.setSelectedIndex(0);
        actualizarEstado.setEnabled(false);
    }//GEN-LAST:event_btn_cancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JComboBox<String> actualizarEstado;
    private javax.swing.JButton btn_Actualizar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton consultar4;
    private javax.swing.JButton consultar5;
    private javax.swing.JTextField equipoCapacidad;
    private javax.swing.JTextField equipoCodigo;
    private javax.swing.JTextField equipoCodigoBarra;
    private javax.swing.JTextField equipoDescripcion;
    private javax.swing.JTextField equipoMarca;
    private javax.swing.JTextField equipoModelo;
    private javax.swing.JTextField equipoProducto;
    private javax.swing.JTextField equipoReferencia;
    private javax.swing.JTextField equipoSerial;
    private javax.swing.JComboBox<String> equipoTipoEquipo;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable tabla1;
    private javax.swing.JTable tabla2;
    private javax.swing.JLabel titulo;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar2(String valorConsulta) throws SQLException{
        //Eliminar todos los elementos de una tabla 
        DefaultTableModel md =(DefaultTableModel)tabla1.getModel();
        int CantEliminar= tabla1.getRowCount() -1;
        for(int i =CantEliminar; i>=0; i--){
                md.removeRow(i);
        }
        DefaultTableModel modelo2 = new DefaultTableModel(null, new String[]{"Código","Código_Equipo", "Descripción_Equipo","Estado"});  
        listadoEquipoLiquidacion=new ControlDB_EquipoLiquidacion(tipoConexion).buscar(valorConsulta);
        if(listadoEquipoLiquidacion != null){
            for (EquipoLiquidacion listado1 : listadoEquipoLiquidacion) {
                String[] registro = new String[4];
                registro[0] = "" + listado1.getCodigo();
                registro[1] = "" + listado1.getEquipo().getCodigo();
                registro[2] = "" + listado1.getEquipo().getDescripcion()+" "+listado1.getEquipo().getModelo();
                registro[3] = "" + listado1.getEstado();
                modelo2.addRow(registro);      
            }
            tabla1.setModel(modelo2);
        }   
    }
     public void tabla_BuscarPersonasPorEquipoLiquidacion(EquipoLiquidacion equipoLiquidacion) throws SQLException {
        //como primera opción vamos a limpiar los datos que se encuentra en la tabla
        DefaultTableModel md =(DefaultTableModel)tabla2.getModel();
        int CantEliminar= tabla2.getRowCount() -1;
        for(int i =CantEliminar; i>=0; i--){
                md.removeRow(i);
        }
         
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"TipoDocumento", "Número", "Nombre", "Telefono", "CargoNomina", "TipoContrato", "Compañia", "Equipo", "Estado"});
        ArrayList<Persona> listadoPersona = new ControlDB_EquipoLiquidacion(tipoConexion).consultarPersonasPorEquipoLiquidacion(equipoLiquidacion);
        if(listadoPersona != null){
            for (Persona objeto : listadoPersona) {
                String[] registro = new String[9];
                registro[0] = "" + objeto.getTipoDocumento().getDescripcion();
                registro[1] = "" + objeto.getCodigo();
                registro[2] = "" + objeto.getNombre() + " " + objeto.getApellido();
                registro[3] = "" + objeto.getTelefono();
                registro[4] = "" + objeto.getCargoNomina().getDescripcion();
                registro[5] = "" + objeto.getTipoContrato().getDescripcion();
                registro[6] = "" + objeto.getCompania().getDescripcion();
                registro[7] = "" + objeto.getEquipo().getDescripcion() + " " + objeto.getEquipo().getModelo();
                registro[8] = "" + objeto.getEstado();
                modelo.addRow(registro);
            }
            tabla2.setModel(modelo);
        }
        
    }
}
