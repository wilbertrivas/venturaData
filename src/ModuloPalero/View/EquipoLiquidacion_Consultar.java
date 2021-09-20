package ModuloPalero.View;

import Catalogo.Controller.ControlDB_TipoEquipo;
import Catalogo.Model.TipoEquipo;
import Catalogo.View.*;
import ModuloPalero.Controller.ControlDB_EquipoLiquidacion;
import ModuloPalero.Model.EquipoLiquidacion;
import ModuloPersonal.Model.Persona;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class EquipoLiquidacion_Consultar extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;  
    ArrayList<EquipoLiquidacion> listadoEquipoLiquidacion=null;
    EquipoLiquidacion equipoLiquidacion;
    ArrayList<TipoEquipo> listadoTipoEquipo = new ArrayList();
    public EquipoLiquidacion_Consultar(Usuario us, String tipoConexion) throws SQLException {
        user = us;
        initComponents();
        InternalFrameMasDetalle.getContentPane().setBackground(Color.WHITE);
        InternalFrameMasDetalle.show(false);
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
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        InternalFrameMasDetalle = new javax.swing.JInternalFrame();
        titulo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        equipoCodigo = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        equipoModelo = new javax.swing.JTextField();
        equipoSerial = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        equipoTipoEquipo = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        equipoCodigoBarra = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        equipoDescripcion = new javax.swing.JTextField();
        equipoCapacidad = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        equipoReferencia = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        equipoProducto = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        equipoMarca = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        valorBusqueda = new javax.swing.JTextField();
        consultar4 = new javax.swing.JButton();
        consultar5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        Editar.setText("MasDetalle");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InternalFrameMasDetalle.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrameMasDetalle.setClosable(true);
        InternalFrameMasDetalle.setVisible(false);
        InternalFrameMasDetalle.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo.setForeground(new java.awt.Color(0, 102, 102));
        titulo.setText("DETALLE DE EQUIPO PARA LIQUIDACIÓN");
        InternalFrameMasDetalle.getContentPane().add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 590, 30));

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

        InternalFrameMasDetalle.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 1180, 190));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Código:");
        InternalFrameMasDetalle.getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 120, 30));

        equipoCodigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        InternalFrameMasDetalle.getContentPane().add(equipoCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 290, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Modelo:");
        InternalFrameMasDetalle.getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 80, 30));

        equipoModelo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        InternalFrameMasDetalle.getContentPane().add(equipoModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 310, 30));

        equipoSerial.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        InternalFrameMasDetalle.getContentPane().add(equipoSerial, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, 310, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Serial:");
        InternalFrameMasDetalle.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 80, 30));

        equipoTipoEquipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equipoTipoEquipo.setToolTipText("");
        InternalFrameMasDetalle.getContentPane().add(equipoTipoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 290, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Tipo Equipo:");
        InternalFrameMasDetalle.getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 120, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Código Barra:");
        InternalFrameMasDetalle.getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 120, 30));

        equipoCodigoBarra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        InternalFrameMasDetalle.getContentPane().add(equipoCodigoBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 290, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Descripción:");
        InternalFrameMasDetalle.getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 80, 30));

        equipoDescripcion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        InternalFrameMasDetalle.getContentPane().add(equipoDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 310, 30));

        equipoCapacidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        InternalFrameMasDetalle.getContentPane().add(equipoCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 160, 310, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Capacidad:");
        InternalFrameMasDetalle.getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 80, 30));

        equipoReferencia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        InternalFrameMasDetalle.getContentPane().add(equipoReferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 290, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Equipo Referencia:");
        InternalFrameMasDetalle.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 120, 30));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Producto:");
        InternalFrameMasDetalle.getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 120, 30));

        equipoProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        InternalFrameMasDetalle.getContentPane().add(equipoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 290, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Marca:");
        InternalFrameMasDetalle.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, 80, 30));

        equipoMarca.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        InternalFrameMasDetalle.getContentPane().add(equipoMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, 310, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 153));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("LISTADO DE PERSONAS ASIGNADAS AL EQUIPO DE LIQUIDACIÓN");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameMasDetalle.getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 1180, 30));
        InternalFrameMasDetalle.getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 1180, 10));
        InternalFrameMasDetalle.getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 1180, 10));

        add(InternalFrameMasDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 550));
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 440, 40));

        consultar4.setBackground(new java.awt.Color(255, 255, 255));
        consultar4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consultar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        consultar4.setText("CONSULTAR");
        consultar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultar4ActionPerformed(evt);
            }
        });
        add(consultar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 140, 40));

        consultar5.setBackground(new java.awt.Color(255, 255, 255));
        consultar5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consultar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/clean.png"))); // NOI18N
        consultar5.setText("LIMPIAR");
        consultar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultar5ActionPerformed(evt);
            }
        });
        add(consultar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 140, 40));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 1050, 430));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("LISTADO DE EQUIPOS PROGRAMADOS PARA LIQUIDAR");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1080, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 1080, 490));
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
                InternalFrameMasDetalle.show(true);
                
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
            Logger.getLogger(EquipoLiquidacion_Consultar.class.getName()).log(Level.SEVERE, null, ex);
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
                    InternalFrameMasDetalle.show(true);

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JInternalFrame InternalFrameMasDetalle;
    private javax.swing.JPopupMenu Seleccionar;
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
