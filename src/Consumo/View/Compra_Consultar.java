package Consumo.View;
 
import Consumo.Controller2.ControlDB_Compra;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class Compra_Consultar extends javax.swing.JPanel {

    DefaultTableModel modeloCompra;
    String [] tituloCompra= {"CODIGO_COMPRA", "FECHA_COMPRA","NOMBRE_PRODUCTO", "UNIDAD_MEDIDA","CANTIDAD_COMPRADA","EXISTENCIA","CEDULA_QUIEN_REALIZA_COMPRA","NOMBRE_QUIEN_REALIZA_COMPRA","OBSERVACION_COMRA"};
    Usuario user;
    private String tipoConexion;
    
    public Compra_Consultar(Usuario u, String tipoConexion) {
        initComponents();
        user= u;
        this.tipoConexion= tipoConexion;
        ventanaInterna_MasDetalles.getContentPane().setBackground(Color.WHITE);
        ventanaInterna_MasDetalles.show(false);
        compraObservacion.setEditable(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        ventanaInterna_MasDetalles = new javax.swing.JInternalFrame();
        compraProducto = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        compraFecha = new javax.swing.JLabel();
        CompraConsecutivo = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        CompraNombreUsuario = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        compraCantidad = new javax.swing.JLabel();
        compraExistenciaProducto = new javax.swing.JLabel();
        compraCedulaUsuario = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        compraObservacion = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        btn_consultar_cliente = new javax.swing.JButton();
        btn_cancelar_cliente1 = new javax.swing.JButton();
        valorBusqueda = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_compras = new javax.swing.JTable();

        Editar.setText("Mas Detalles");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ventanaInterna_MasDetalles.setBackground(new java.awt.Color(255, 255, 255));
        ventanaInterna_MasDetalles.setClosable(true);
        ventanaInterna_MasDetalles.setVisible(true);
        ventanaInterna_MasDetalles.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                ventanaInterna_MasDetallesInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        ventanaInterna_MasDetalles.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        compraProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ventanaInterna_MasDetalles.getContentPane().add(compraProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 720, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("OBSERVACION DE LA COMPRA:");
        ventanaInterna_MasDetalles.getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 220, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("FECHA COMPRA:");
        ventanaInterna_MasDetalles.getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 130, 30));

        compraFecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ventanaInterna_MasDetalles.getContentPane().add(compraFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 720, 30));

        CompraConsecutivo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        CompraConsecutivo.setText("DETALLES DE COMPRA No.");
        ventanaInterna_MasDetalles.getContentPane().add(CompraConsecutivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 260, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("NOMBRE DEL PRODUCTO COMPRADO:");
        ventanaInterna_MasDetalles.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 240, 30));

        CompraNombreUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ventanaInterna_MasDetalles.getContentPane().add(CompraNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 720, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("CANTIDAD COMPRADA:");
        ventanaInterna_MasDetalles.getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 180, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("EXISTENCIA ACTUAL:");
        ventanaInterna_MasDetalles.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 180, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("CEDULA QUIEN REALIZÓ COMPRA:");
        ventanaInterna_MasDetalles.getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 240, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("NOMBRE QUIEN REALIZÓ COMPRA:");
        ventanaInterna_MasDetalles.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 220, 30));

        compraCantidad.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ventanaInterna_MasDetalles.getContentPane().add(compraCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 720, 30));

        compraExistenciaProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ventanaInterna_MasDetalles.getContentPane().add(compraExistenciaProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 720, 30));

        compraCedulaUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ventanaInterna_MasDetalles.getContentPane().add(compraCedulaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 720, 30));

        compraObservacion.setColumns(20);
        compraObservacion.setRows(5);
        jScrollPane2.setViewportView(compraObservacion);

        ventanaInterna_MasDetalles.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 720, 130));

        add(ventanaInterna_MasDetalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1180, 670));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("CONSULTAR COMPRAS");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 310, 30));

        btn_consultar_cliente.setBackground(new java.awt.Color(255, 255, 255));
        btn_consultar_cliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_consultar_cliente.setText("CONSULTAR");
        btn_consultar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultar_clienteActionPerformed(evt);
            }
        });
        add(btn_consultar_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 140, 30));

        btn_cancelar_cliente1.setBackground(new java.awt.Color(255, 255, 255));
        btn_cancelar_cliente1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cancelar_cliente1.setText("CANCELAR");
        btn_cancelar_cliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_cliente1ActionPerformed(evt);
            }
        });
        add(btn_cancelar_cliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 140, 30));
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 310, 30));

        tabla_compras = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla_compras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla_compras.setComponentPopupMenu(Seleccionar);
        tabla_compras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_comprasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_compras);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 1450, 450));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_consultar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultar_clienteActionPerformed
        try {
            tabla_consultarCompras(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Compra_Consultar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_consultar_clienteActionPerformed

    private void btn_cancelar_cliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_cliente1ActionPerformed
        valorBusqueda.setText("");
    }//GEN-LAST:event_btn_cancelar_cliente1ActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO lo del clik derechoo
        int fila1;
        try{
            fila1=tabla_compras.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                modeloCompra=(DefaultTableModel)tabla_compras.getModel();
                CompraConsecutivo.setText("DETALLES DE COMPRA No."+(String)modeloCompra.getValueAt(fila1, 0));
                compraFecha.setText((String)modeloCompra.getValueAt(fila1, 1));
                compraProducto.setText((String)modeloCompra.getValueAt(fila1, 2)+" "+(String)modeloCompra.getValueAt(fila1, 3));     
                compraCantidad.setText((String)modeloCompra.getValueAt(fila1, 4));   
                compraExistenciaProducto.setText((String)modeloCompra.getValueAt(fila1, 5));     
                compraCedulaUsuario.setText((String)modeloCompra.getValueAt(fila1, 6));     
                CompraNombreUsuario.setText((String)modeloCompra.getValueAt(fila1, 7));     
                compraObservacion.setText((String)modeloCompra.getValueAt(fila1, 8));     
                ventanaInterna_MasDetalles.show(true);
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void ventanaInterna_MasDetallesInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_ventanaInterna_MasDetallesInternalFrameClosed
        
    }//GEN-LAST:event_ventanaInterna_MasDetallesInternalFrameClosed

    private void tabla_comprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_comprasMouseClicked
       if (evt.getClickCount() == 2) {
           // TODO lo del clik derechoo
            int fila1;
            try{
                fila1=tabla_compras.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    modeloCompra=(DefaultTableModel)tabla_compras.getModel();
                    CompraConsecutivo.setText("DETALLES DE COMPRA No."+(String)modeloCompra.getValueAt(fila1, 0));
                    compraFecha.setText((String)modeloCompra.getValueAt(fila1, 1));
                    compraProducto.setText((String)modeloCompra.getValueAt(fila1, 2)+" "+(String)modeloCompra.getValueAt(fila1, 3));     
                    compraCantidad.setText((String)modeloCompra.getValueAt(fila1, 4));   
                    compraExistenciaProducto.setText((String)modeloCompra.getValueAt(fila1, 5));     
                    compraCedulaUsuario.setText((String)modeloCompra.getValueAt(fila1, 6));     
                    CompraNombreUsuario.setText((String)modeloCompra.getValueAt(fila1, 7));     
                    compraObservacion.setText((String)modeloCompra.getValueAt(fila1, 8));     
                    ventanaInterna_MasDetalles.show(true);
                }
            }catch(Exception e){
            }
       }
    }//GEN-LAST:event_tabla_comprasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CompraConsecutivo;
    private javax.swing.JLabel CompraNombreUsuario;
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JButton btn_cancelar_cliente1;
    private javax.swing.JButton btn_consultar_cliente;
    private javax.swing.JLabel compraCantidad;
    private javax.swing.JLabel compraCedulaUsuario;
    private javax.swing.JLabel compraExistenciaProducto;
    private javax.swing.JLabel compraFecha;
    private javax.swing.JTextArea compraObservacion;
    private javax.swing.JLabel compraProducto;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla_compras;
    private javax.swing.JTextField valorBusqueda;
    private javax.swing.JInternalFrame ventanaInterna_MasDetalles;
    // End of variables declaration//GEN-END:variables
    public void tabla_consultarCompras(String valorConsulta) throws SQLException{
        ControlDB_Compra controlDB_Compra = new ControlDB_Compra(tipoConexion);        
        modeloCompra = new DefaultTableModel(null, tituloCompra);  
        ArrayList<String> listadoCompras=controlDB_Compra.consultarCompras(valorConsulta);
        for(int i =0; i< listadoCompras.size(); i++){
            String []data= listadoCompras.get(i).split("@#@");
            modeloCompra.addRow(data);    
            tabla_compras.setModel(modeloCompra); 
        }
    }
}
