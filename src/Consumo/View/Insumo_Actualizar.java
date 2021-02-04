package Consumo.View;

import Consumo.Controller2.ControlDB_Insumo;
import Consumo.Controller2.ControlDB_Unidad;
import Consumo.Model2.Insumo;
import Consumo.Model2.Unidad;
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

public final class Insumo_Actualizar extends javax.swing.JPanel {
    DefaultTableModel modeloInsumno;
    String [] tituloInsumo= {"Codigo", "Nombre","Unidad", "Cantidad", "Estado"};
    String[]  registroInsumo;   
    Usuario user;
    private String tipoConexion;
    public Insumo_Actualizar(Usuario us, String tipoConexion) {
        user=us;
        this.tipoConexion= tipoConexion;
        initComponents();     
        //Cargamos en la interfaz las unidades que esten activas
        ControlDB_Unidad controlDB_Unidad = new ControlDB_Unidad(tipoConexion);
        ArrayList<Unidad> listadoUnidades = new ArrayList();
        try {
            listadoUnidades=controlDB_Unidad.buscar("");
            for(int i=0; i< listadoUnidades.size(); i++){
                insumo_unidad.addItem(listadoUnidades.get(i).getDescripcion());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Insumo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            tabla_ListarInsumos("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las unidades");
            Logger.getLogger(Unidad_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        insumo_unidad.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        insumo_nombre = new javax.swing.JTextField();
        insumo_codigo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        insumo_estado = new javax.swing.JComboBox<>();
        btn_actualizar_insumo = new javax.swing.JButton();
        btn_cancelar_insumo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_unidades = new javax.swing.JTable();
        valorBusqueda = new javax.swing.JTextField();
        consultar = new javax.swing.JButton();
        generarReporte = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        insumo_unidad = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        insumo_cantidad = new javax.swing.JLabel();
        alerta_nombre = new javax.swing.JLabel();

        Editar.setText("Modificar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(insumo_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 310, 30));

        insumo_codigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(insumo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 310, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("ACTUALIZACIÓN DE INSUMOS");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 310, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Estado:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 80, 30));

        insumo_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        insumo_estado.setToolTipText("");
        add(insumo_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 310, 30));

        btn_actualizar_insumo.setBackground(new java.awt.Color(255, 255, 255));
        btn_actualizar_insumo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_actualizar_insumo.setText("ACTUALIZAR");
        btn_actualizar_insumo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_actualizar_insumoMouseExited(evt);
            }
        });
        btn_actualizar_insumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizar_insumoActionPerformed(evt);
            }
        });
        add(btn_actualizar_insumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 140, 30));

        btn_cancelar_insumo.setBackground(new java.awt.Color(255, 255, 255));
        btn_cancelar_insumo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cancelar_insumo.setText("CANCELAR");
        btn_cancelar_insumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_insumoActionPerformed(evt);
            }
        });
        add(btn_cancelar_insumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, 140, 30));

        tabla_unidades = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla_unidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla_unidades.setComponentPopupMenu(Seleccionar);
        tabla_unidades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_unidadesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_unidades);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, 630, 460));
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, 280, 30));

        consultar.setBackground(new java.awt.Color(255, 255, 255));
        consultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consultar.setText("CONSULTAR");
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        add(consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 70, 140, 30));

        generarReporte.setBackground(new java.awt.Color(255, 255, 255));
        generarReporte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        generarReporte.setText("GENERAR REPORTE");
        add(generarReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 70, 160, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Unidad:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 80, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Código:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 80, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Cantidad:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 80, 30));

        insumo_unidad.setToolTipText("");
        add(insumo_unidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 310, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Nombre:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 80, 30));

        insumo_cantidad.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(insumo_cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 310, 30));

        alerta_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_nombre.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 370, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cancelar_insumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_insumoActionPerformed
        insumo_nombre.setText("");
        insumo_estado.setSelectedIndex(0);
    }//GEN-LAST:event_btn_cancelar_insumoActionPerformed

    private void btn_actualizar_insumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizar_insumoActionPerformed
        if(insumo_codigo.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Debe cargar un insumo");
        }else{
            if(insumo_nombre.getText().equals("")){
                alerta_nombre.setText("El nombre del insumo no puede estar vacio");
            }else{
                ControlDB_Insumo  controlDB_Insumo = new ControlDB_Insumo(tipoConexion);
                ControlDB_Unidad  controlDB_Unidad = new ControlDB_Unidad(tipoConexion);
                Insumo insumo = new Insumo();
                try {
                    insumo.setCodigo(Integer.parseInt(insumo_codigo.getText()));
                    insumo.setDescripcion(insumo_nombre.getText());
                    insumo.setUnidad(new Unidad(Integer.parseInt(controlDB_Unidad.buscar_nombre(insumo_unidad.getSelectedItem().toString())),insumo_unidad.getSelectedItem().toString(),"0"));  
                    if(insumo_estado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                        insumo.setEstado("1");
                    }else{
                        insumo.setEstado("0");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Insumo_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Error al tratar de capturar la información solicitada");
                }
                try{
                    int respuesta=0;
                    if(!controlDB_Insumo.validarExistenciaActualizar(insumo))
                    {
                        try {
                            respuesta = controlDB_Insumo.actualizar(insumo, user);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(Insumo_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (UnknownHostException ex) {
                            Logger.getLogger(Insumo_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SocketException ex) {
                            Logger.getLogger(Insumo_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(respuesta==1){
                            JOptionPane.showMessageDialog(null, "Se actualizo el insumo");
                            insumo_nombre.setText("");
                            insumo_estado.setSelectedIndex(0);
                            tabla_ListarInsumos("");
                        }else{
                            if(respuesta==0){
                                JOptionPane.showMessageDialog(null, "No se pudo actualizar el Insumo, valide los datos ingresados");
                            }
                        }  
                    }else{
                        JOptionPane.showMessageDialog(null, "Ya existe un insumo con el mismo nombre en el sistema","Advertencia", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Insumo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btn_actualizar_insumoActionPerformed

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        try {
            tabla_ListarInsumos(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Insumo_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_consultarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO lo del clik derechoo
        int fila1;
        try{
            fila1=tabla_unidades.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                modeloInsumno=(DefaultTableModel)tabla_unidades.getModel();
                
                insumo_codigo.setText((String)modeloInsumno.getValueAt(fila1, 0));
                insumo_nombre.setText((String)modeloInsumno.getValueAt(fila1, 1));
                insumo_unidad.setSelectedItem((String)modeloInsumno.getValueAt(fila1, 2));
                insumo_cantidad.setText((String)modeloInsumno.getValueAt(fila1, 3));
                insumo_estado.setSelectedItem((String)modeloInsumno.getValueAt(fila1, 4));
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void tabla_unidadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_unidadesMouseClicked
        if (evt.getClickCount() == 2) {
            // TODO lo del clik derechoo
            int fila1;
            try{
                fila1=tabla_unidades.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    modeloInsumno=(DefaultTableModel)tabla_unidades.getModel();

                    insumo_codigo.setText((String)modeloInsumno.getValueAt(fila1, 0));
                    insumo_nombre.setText((String)modeloInsumno.getValueAt(fila1, 1));
                    insumo_unidad.setSelectedItem((String)modeloInsumno.getValueAt(fila1, 2));
                    insumo_cantidad.setText((String)modeloInsumno.getValueAt(fila1, 3));
                    insumo_estado.setSelectedItem((String)modeloInsumno.getValueAt(fila1, 4));
                }
            }catch(Exception e){
            }
        }    
    }//GEN-LAST:event_tabla_unidadesMouseClicked

    private void btn_actualizar_insumoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_actualizar_insumoMouseExited
        alerta_nombre.setText("");
    }//GEN-LAST:event_btn_actualizar_insumoMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JLabel alerta_nombre;
    private javax.swing.JButton btn_actualizar_insumo;
    private javax.swing.JButton btn_cancelar_insumo;
    private javax.swing.JButton consultar;
    private javax.swing.JButton generarReporte;
    private javax.swing.JLabel insumo_cantidad;
    private javax.swing.JLabel insumo_codigo;
    private javax.swing.JComboBox<String> insumo_estado;
    private javax.swing.JTextField insumo_nombre;
    private javax.swing.JComboBox<String> insumo_unidad;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_unidades;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_ListarInsumos(String valorConsulta) throws SQLException{
        ControlDB_Insumo  controlDB_Insumo = new ControlDB_Insumo(tipoConexion);        
        registroInsumo = new String[5]; 
        modeloInsumno = new DefaultTableModel(null, tituloInsumo);  
        ArrayList<Insumo> listadoInsumo=controlDB_Insumo.buscar(valorConsulta);
        for(int i =0; i< listadoInsumo.size(); i++){
            registroInsumo[0]=""+listadoInsumo.get(i).getCodigo();
            registroInsumo[1]=""+listadoInsumo.get(i).getDescripcion();
            registroInsumo[2]=""+listadoInsumo.get(i).getUnidad().getDescripcion();
            registroInsumo[3]=""+listadoInsumo.get(i).getCantidad();
            registroInsumo[4]=""+listadoInsumo.get(i).getEstado();
            modeloInsumno.addRow(registroInsumo);
            tabla_unidades.setModel(modeloInsumno);
        }
    }

}
