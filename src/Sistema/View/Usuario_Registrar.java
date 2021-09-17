package Sistema.View2;

import Sistema.Controller.ControlDB_Usuario;
import Sistema.Model.Perfil;
import Sistema.Model.Usuario;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Usuario_Registrar extends javax.swing.JPanel {
    private String tipoConexion;
    public Usuario_Registrar(String tipoConexion) {
        initComponents();
        this.tipoConexion= tipoConexion;
        try{
            //Cargamos en la interfaz los Perfiles activos activos
            ControlDB_Usuario controlDB_Usuario = new ControlDB_Usuario(tipoConexion);
            ArrayList<Perfil> listadoPerfilUsuario = new ArrayList();
            listadoPerfilUsuario=controlDB_Usuario.cargarPerfil();
            for(int i=0; i< listadoPerfilUsuario.size(); i++){
                us_perfil_usuario_cdgo.addItem(listadoPerfilUsuario.get(i).getCodigo()+":"+listadoPerfilUsuario.get(i).getDescripcion());
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al cargar los perfiles activos");
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        RegistrarUsuario = new javax.swing.JButton();
        us_cdgo = new javax.swing.JTextField();
        us_correo = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        us_nombres = new javax.swing.JTextField();
        us_apellidos = new javax.swing.JTextField();
        alerta_us_nombres = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        us_clave = new javax.swing.JPasswordField();
        alerta_us_clave = new javax.swing.JLabel();
        us_estad = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        alerta_us_cdgo = new javax.swing.JLabel();
        alerta_us_apellidos = new javax.swing.JLabel();
        us_perfil_usuario_cdgo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        alerta_us_correo = new javax.swing.JLabel();

        Editar.setText("Seleccionar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("REGISTRO DE USUARIO");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 450, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Apellidos:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 80, 30));

        RegistrarUsuario.setBackground(new java.awt.Color(255, 255, 255));
        RegistrarUsuario.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        RegistrarUsuario.setText("REGISTRAR USUARIO");
        RegistrarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RegistrarUsuarioMouseExited(evt);
            }
        });
        RegistrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarUsuarioActionPerformed(evt);
            }
        });
        add(RegistrarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 210, 50));

        us_cdgo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                us_cdgoMouseClicked(evt);
            }
        });
        add(us_cdgo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 280, 30));

        us_correo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                us_correoMouseClicked(evt);
            }
        });
        add(us_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 280, 30));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Correo:");
        add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, 70, 30));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("Contraseña:");
        add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, 80, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Nombres:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 70, 30));

        us_nombres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                us_nombresMouseClicked(evt);
            }
        });
        add(us_nombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 280, 30));

        us_apellidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                us_apellidosMouseClicked(evt);
            }
        });
        add(us_apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 280, 30));

        alerta_us_nombres.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_us_nombres.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_us_nombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 320, 20));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Cedula:");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 140, 30));

        us_clave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                us_claveKeyTyped(evt);
            }
        });
        add(us_clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 280, 30));

        alerta_us_clave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_us_clave.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_us_clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, 320, 20));

        us_estad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        add(us_estad, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 280, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Perfil del Usuario:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 120, 20));

        alerta_us_cdgo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_us_cdgo.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_us_cdgo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 320, 20));

        alerta_us_apellidos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_us_apellidos.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_us_apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, 320, 20));

        add(us_perfil_usuario_cdgo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 280, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Estado:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 70, 20));

        alerta_us_correo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_us_correo.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_us_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, 320, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
       
    }//GEN-LAST:event_EditarActionPerformed

    private void RegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarUsuarioActionPerformed
        if(us_cdgo.getText().equals("")){
            alerta_us_cdgo.setText("Error!!.. La cedula del usuario no puede estar vacia");
        }else{
            if(us_clave.getText().equals("")){
                alerta_us_clave.setText("Error!!.. La contraseña no puede estar vacia");
            }
            else{
                if(us_nombres.getText().equals("")){
                    alerta_us_nombres.setText("Error!!.. El nombre del usuario no puede estar vacio");
                }else{
                    if(us_apellidos.getText().equals("")){
                        alerta_us_apellidos.setText("Error!!.. Los apellidos no pueden estar vacio");
                    }else{
                        try{                            
                            ControlDB_Usuario controlDB_Usuario = new ControlDB_Usuario(tipoConexion);
                            Integer.parseInt(us_cdgo.getText());
                        
                            if(!us_correo.getText().equalsIgnoreCase("")){//El usuario ingresó informacion en el campo correo por tal motivo validamos que sea un correo valido
                                // Patrón para validar el email
                                Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

                                // El email a validar
                                String email = us_correo.getText();
                                Matcher mather = pattern.matcher(email);
                                if (mather.find() == true) {
                                    Usuario us = new Usuario();
                                    us.setCodigo(us_cdgo.getText());   
                                    us.setClave(us_clave.getText());   
                                    us.setNombres(us_nombres.getText());   
                                    us.setApellidos(us_apellidos.getText());   
                                    
                                    String[]datosPerfil=us_perfil_usuario_cdgo.getSelectedItem().toString().split(":");
                                    us.setPerfilUsuario(new Perfil(datosPerfil[0]));
                                    us.setCorreo(us_correo.getText());   

                                    //Validamos si selecciono activo o inactivo
                                    if(us_estad.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                                        us.setEstado("1");
                                    }else{
                                        us.setEstado("0");
                                    }
                                    int result=controlDB_Usuario.registrarUsuario(us);
                                    if(result==1){
                                     JOptionPane.showMessageDialog(null, "Registro de Usuario Exitoso");
                                            us_cdgo.setText("");
                                            us_clave.setText("");
                                            us_nombres.setText("");
                                            us_apellidos.setText("");
                                            us_perfil_usuario_cdgo.setSelectedIndex(0);
                                            us_correo.setText("");
                                            us_estad.setSelectedIndex(0);
                                            alerta_us_cdgo.setText("");
                                            alerta_us_clave.setText("");
                                            alerta_us_nombres.setText("");
                                            alerta_us_apellidos.setText("");  
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Error Al Registrar el Usuario");
                                    } 
                                } else {
                                    alerta_us_correo.setText("El email ingresado es inválido.");
                                }
                            }else{//El usuario dejo el correo vacio por tal motivo también se realiza el registro
                                Usuario us = new Usuario();
                                us.setCodigo(us_cdgo.getText());   
                                us.setClave(us_clave.getText());   
                                us.setNombres(us_nombres.getText());   
                                us.setApellidos(us_apellidos.getText());   
                                String[]datosPerfil=us_perfil_usuario_cdgo.getSelectedItem().toString().split(":");
                                us.setPerfilUsuario(new Perfil(datosPerfil[0]));
                                us.setCorreo(us_correo.getText());   

                                //Validamos si selecciono activo o inactivo
                                if(us_estad.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                                    us.setEstado("1");
                                }else{
                                    us.setEstado("0");
                                }
                                int result=controlDB_Usuario.registrarUsuario(us);
                                if(result==1){
                                 JOptionPane.showMessageDialog(null, "Registro de Usuario Exitoso");
                                        us_cdgo.setText("");
                                        us_clave.setText("");
                                        us_nombres.setText("");
                                        us_apellidos.setText("");
                                        us_perfil_usuario_cdgo.setSelectedIndex(0);
                                        us_correo.setText("");
                                        us_estad.setSelectedIndex(0);
                                        alerta_us_cdgo.setText("");
                                        alerta_us_clave.setText("");
                                        alerta_us_nombres.setText("");
                                        alerta_us_apellidos.setText("");  
                                }else{
                                    JOptionPane.showMessageDialog(null, "Error Al Registrar el Usuario");
                                }  
                            }    
                        }catch(Exception e){
                            alerta_us_cdgo.setText("Error!!.. La cedula debe ser númerica");
                        }
                    }
                }
            }
        }            
    }//GEN-LAST:event_RegistrarUsuarioActionPerformed

    private void us_cdgoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_us_cdgoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_us_cdgoMouseClicked

    private void us_correoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_us_correoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_us_correoMouseClicked

    private void RegistrarUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegistrarUsuarioMouseExited
        alerta_us_cdgo.setText("");
        alerta_us_clave.setText("");
        alerta_us_nombres.setText("");
        alerta_us_apellidos.setText("");
        alerta_us_correo.setText("");
    }//GEN-LAST:event_RegistrarUsuarioMouseExited

    private void us_nombresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_us_nombresMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_us_nombresMouseClicked

    private void us_apellidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_us_apellidosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_us_apellidosMouseClicked

    private void us_claveKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_us_claveKeyTyped
        if (us_clave.getText().length()== 15){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
            alerta_us_clave.setText("La longitud total de la contraseña es 15 Caracteres");
        }else{
            alerta_us_clave.setText("");
        }
    }//GEN-LAST:event_us_claveKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JButton RegistrarUsuario;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JLabel alerta_us_apellidos;
    private javax.swing.JLabel alerta_us_cdgo;
    private javax.swing.JLabel alerta_us_clave;
    private javax.swing.JLabel alerta_us_correo;
    private javax.swing.JLabel alerta_us_nombres;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField us_apellidos;
    private javax.swing.JTextField us_cdgo;
    private javax.swing.JPasswordField us_clave;
    private javax.swing.JTextField us_correo;
    private javax.swing.JComboBox<String> us_estad;
    private javax.swing.JTextField us_nombres;
    private javax.swing.JComboBox<String> us_perfil_usuario_cdgo;
    // End of variables declaration//GEN-END:variables
}
