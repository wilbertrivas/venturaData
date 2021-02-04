package Consumo.View;
 
import Consumo.Controller2.ControlDB_Compra;
import Consumo.Controller2.ControlDB_Insumo;
import Consumo.Model2.Compra;
import Consumo.Model2.Insumo;
import Consumo.Model2.Unidad;
import Sistema.Model.Usuario;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Comprar_Registrar extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;
    
    public Comprar_Registrar(Usuario u,String tipoConexion) {
        initComponents();
        user= u;
        this.tipoConexion= tipoConexion;
        //Cargamos en la interfaz los insumos que esten activos
        ControlDB_Insumo controlDB_Insumo = new ControlDB_Insumo(tipoConexion);
        ArrayList<Insumo> listadoInsumos = new ArrayList();
        try {
            listadoInsumos=controlDB_Insumo.buscarInsumosActivos();
            for(int i=0; i< listadoInsumos.size(); i++){
                insumos.addItem(listadoInsumos.get(i).getCodigo()+": "+   listadoInsumos.get(i).getDescripcion()+" "+listadoInsumos.get(i).getUnidad().getDescripcion());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Comprar_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }   
        usuario.setText(user.getNombres()+" "+user.getApellidos());
        for(int i=1; i<=300;i++){
            cantidad.addItem(""+i);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fechaCompra = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        usuario = new javax.swing.JLabel();
        insumos = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        observacion = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_registrar_unidad = new javax.swing.JButton();
        cantidad = new javax.swing.JComboBox<>();
        alerta_fecha = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fechaCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaCompraMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaCompraMouseEntered(evt);
            }
        });
        add(fechaCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 310, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Usuario quien registra compra:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 230, 30));

        usuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        usuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 450, 30));

        insumos.setToolTipText("");
        add(insumos, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 310, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Fecha de Compra:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 160, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Cantidad:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 160, 30));

        observacion.setColumns(20);
        observacion.setRows(5);
        jScrollPane1.setViewportView(observacion);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 450, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Observación:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 160, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Insumo");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 160, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("REGISTRO DE COMPRA");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 310, 30));

        btn_registrar_unidad.setBackground(new java.awt.Color(255, 255, 255));
        btn_registrar_unidad.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_registrar_unidad.setText("REGISTRAR");
        btn_registrar_unidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_registrar_unidadMouseExited(evt);
            }
        });
        btn_registrar_unidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrar_unidadActionPerformed(evt);
            }
        });
        add(btn_registrar_unidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 390, 140, 30));

        cantidad.setToolTipText("");
        add(cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 310, 30));

        alerta_fecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_fecha.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 370, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void fechaCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaCompraMouseClicked
       
    }//GEN-LAST:event_fechaCompraMouseClicked

    private void fechaCompraMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaCompraMouseEntered
       
    }//GEN-LAST:event_fechaCompraMouseEntered

    private void btn_registrar_unidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrar_unidadActionPerformed
        try{
            Calendar fecha = fechaCompra.getCalendar();
            String ano = ""+fecha.get(Calendar.YEAR);
            String mes = "";
            if((fecha.get(Calendar.MONTH) +1) <=9){
                mes = "0"+(fecha.get(Calendar.MONTH) + 1);
            }else{
                mes = ""+(fecha.get(Calendar.MONTH) + 1);
            }
            String dia = "";
            if(fecha.get(Calendar.DAY_OF_MONTH) <=9){
                dia = "0"+fecha.get(Calendar.DAY_OF_MONTH);
            }else{
                dia = ""+fecha.get(Calendar.DAY_OF_MONTH);
            }
            String SfechaCompra=ano+"-"+mes+"-"+dia;
            
            try{
                Compra cm = new Compra();
                String[] datosInsumos= insumos.getSelectedItem().toString().split(":");
                cm.setInsumo(new Insumo(Integer.parseInt(datosInsumos[0]),"",(new Unidad(1,"","1")),0,"1"));
                cm.setFechaCompra(SfechaCompra);
                cm.setCantidad(Integer.parseInt(cantidad.getSelectedItem().toString()));
                cm.setObservacion(observacion.getText());
                cm.setUsuario(user);
                ControlDB_Compra controlDB_Compra = new ControlDB_Compra(tipoConexion);
                int retorno=0;
        try {
            retorno = controlDB_Compra.registrar(cm,user);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Comprar_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
                if(retorno==1){
                    JOptionPane.showMessageDialog(null,"Se registro al compra");
                    insumos.setSelectedIndex(0);
                    cantidad.setSelectedIndex(0);
                    observacion.setText("");
                }else{
                    JOptionPane.showMessageDialog(null, "Error!!.. al registrar la compra, valide los datos");
                }
            }catch(Exception e){
               JOptionPane.showMessageDialog(null,"Error!!.. Al tratar de capturar la información");
            }
            
        }catch(Exception e){
            alerta_fecha.setText("ERROR!!!. verifique la fecha debe tener un formato valido");
        }
    }//GEN-LAST:event_btn_registrar_unidadActionPerformed

    private void btn_registrar_unidadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_registrar_unidadMouseExited
        alerta_fecha.setText("");
    }//GEN-LAST:event_btn_registrar_unidadMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alerta_fecha;
    private javax.swing.JButton btn_registrar_unidad;
    private javax.swing.JComboBox<String> cantidad;
    private com.toedter.calendar.JDateChooser fechaCompra;
    private javax.swing.JComboBox<String> insumos;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea observacion;
    private javax.swing.JLabel usuario;
    // End of variables declaration//GEN-END:variables
}
