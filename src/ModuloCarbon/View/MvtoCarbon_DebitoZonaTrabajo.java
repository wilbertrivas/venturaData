package ModuloCarbon.View;

import Catalogo.Controller.ControlDB_ZonaTrabajo;
import Catalogo.Model.ZonaTrabajo;
import ModuloCarbon.Model.DebitoZonaTrabajo;
import ModuloEquipo.Controller.ControlDB_MvtoEquipo;
import Sistema.Model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MvtoCarbon_DebitoZonaTrabajo extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;
    ArrayList<ZonaTrabajo> listadoZonaTrabajo = null;
    public MvtoCarbon_DebitoZonaTrabajo(Usuario us,String tipoConexion) {
        initComponents();
        user=us;
        this.tipoConexion= tipoConexion;
        
        //Cargamos en la interfaz las zonas de trabajo
        try{
            listadoZonaTrabajo=new ControlDB_ZonaTrabajo(tipoConexion).listarZonaTrabajo();
            for(ZonaTrabajo zonaTrabajo: listadoZonaTrabajo){
                selectZonaTrabajo.addItem(zonaTrabajo.getDescripcion());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        estado.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel58 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        fecha = new com.toedter.calendar.JDateChooser();
        selectZonaTrabajo = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        Descripcion = new javax.swing.JTextArea();
        estado = new javax.swing.JComboBox<>();
        btn_registrar_cliente = new javax.swing.JButton();
        valor = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 153, 153));
        jLabel58.setText("DEBITO EN ZONA TRABAJO");
        add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, -1, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("FECHA DE MOVIMIENTO:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 170, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("ZONA DE TRABAJO:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 160, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("VALOR:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 160, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("MOTIVO DE DEBITO:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 160, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("ESTADO:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 160, 30));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 1040, 20));

        fecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaMouseEntered(evt);
            }
        });
        add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 170, 30));

        selectZonaTrabajo.setToolTipText("");
        selectZonaTrabajo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectZonaTrabajoItemStateChanged(evt);
            }
        });
        add(selectZonaTrabajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 450, 30));

        Descripcion.setColumns(20);
        Descripcion.setRows(5);
        jScrollPane7.setViewportView(Descripcion);

        add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 450, 100));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 310, 30));

        btn_registrar_cliente.setBackground(new java.awt.Color(255, 255, 255));
        btn_registrar_cliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_registrar_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Ingresar.png"))); // NOI18N
        btn_registrar_cliente.setText("REGISTRAR");
        btn_registrar_cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_registrar_clienteMouseExited(evt);
            }
        });
        btn_registrar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrar_clienteActionPerformed(evt);
            }
        });
        add(btn_registrar_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, 140, 40));
        add(valor, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 450, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void fechaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaMouseClicked
      
    }//GEN-LAST:event_fechaMouseClicked

    private void fechaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaMouseEntered

    private void selectZonaTrabajoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectZonaTrabajoItemStateChanged
        
    }//GEN-LAST:event_selectZonaTrabajoItemStateChanged

    private void btn_registrar_clienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_registrar_clienteMouseExited
      
    }//GEN-LAST:event_btn_registrar_clienteMouseExited

    private void btn_registrar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrar_clienteActionPerformed
        DebitoZonaTrabajo debitoZonaTrabajo = new DebitoZonaTrabajo();        
        String fechaMvto="";
        boolean validar= true;
        if(listadoZonaTrabajo != null){
            if(valor.getText().equals("") && validar){
                JOptionPane.showMessageDialog(null,"Error!!.. El valor del debito no puede estar vacio","Advertencia",JOptionPane.ERROR_MESSAGE);
                validar= false;
            }else{
                if(Descripcion.getText().equals("") && validar){
                    JOptionPane.showMessageDialog(null,"Error!!.. El motivo del debito no puede estar vacio","Advertencia",JOptionPane.ERROR_MESSAGE);
                    validar= false;
                }else{
                    try{    //Almacenamos la fecha de inicio de actividad
                        Calendar fechaI = fecha.getCalendar();
                        String anoI = ""+fechaI.get(Calendar.YEAR);
                        String mesI = "";
                        if((fechaI.get(Calendar.MONTH) +1) <=9){
                            mesI = "0"+(fechaI.get(Calendar.MONTH) + 1);
                        }else{
                            mesI = ""+(fechaI.get(Calendar.MONTH) + 1);
                        }
                        String diaI = "";
                        if(fechaI.get(Calendar.DAY_OF_MONTH) <=9){
                            diaI = "0"+fechaI.get(Calendar.DAY_OF_MONTH);
                        }else{
                            diaI = ""+fechaI.get(Calendar.DAY_OF_MONTH);
                        }
                        fechaMvto=anoI+"-"+mesI+"-"+diaI;
                        debitoZonaTrabajo.setFechaMvomiento(fechaMvto);
                        try{
                            debitoZonaTrabajo.setZonaTrabajo(listadoZonaTrabajo.get(selectZonaTrabajo.getSelectedIndex()));
                            Integer.parseInt(valor.getText());
                            debitoZonaTrabajo.setValor(valor.getText());
                            debitoZonaTrabajo.setDescripcion(Descripcion.getText());
                            debitoZonaTrabajo.setUsuarioQuienRegistra(user);
                            debitoZonaTrabajo.setEstado("1");
                        }catch(Exception e){
                            validar= false;
                            JOptionPane.showMessageDialog(null,"Error!!.. El valor del debito debe ser númerico y no debe de tener punto, ni coma","Advertencia",JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace();
                        }
                    }catch(Exception e){
                        //validarCampos=false;
                        JOptionPane.showMessageDialog(null,"Error!!.. Debe verificar la fecha de Movimiento","Advertencia",JOptionPane.ERROR_MESSAGE);
                        validar= false;
                    }   
                }
            }
        }else{
            JOptionPane.showMessageDialog(null,"Error!!.. Debe existir al menos una zona de trabajo activa para registrar el debito","Advertencia",JOptionPane.ERROR_MESSAGE);
            validar= false;
        }
        //Procedemos a hace registro siempre y cuando validar=true, de lo contrario no se registra
        if(validar){
            //registramos la objeto tipo DebitoZonaTrabajo
            int retorno =0;
            try {
                retorno= new ControlDB_ZonaTrabajo(tipoConexion).registrarDebito(debitoZonaTrabajo);
                if(retorno==1){
                    JOptionPane.showMessageDialog(null, "Registro exitoso","Registrado", JOptionPane.INFORMATION_MESSAGE);
                    valor.setText("");
                    Descripcion.setText("");
                }else{
                    JOptionPane.showMessageDialog(null, "No se puedo hacer el registro del debito valide la información suministrada","Error!", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "No se puedo hacer el registro del debito valide la información suministrada","Error!", JOptionPane.INFORMATION_MESSAGE);
                e.printStackTrace(); 
            }      
        }
    }//GEN-LAST:event_btn_registrar_clienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Descripcion;
    private javax.swing.JButton btn_registrar_cliente;
    private javax.swing.JComboBox<String> estado;
    private com.toedter.calendar.JDateChooser fecha;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox<String> selectZonaTrabajo;
    private javax.swing.JTextField valor;
    // End of variables declaration//GEN-END:variables
}
