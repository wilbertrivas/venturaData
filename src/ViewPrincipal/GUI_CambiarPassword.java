package ViewPrincipal;

import Sistema.Controller.ControlDB_Sistema;
import Sistema.Controller.ControlDB_Usuario;
import Sistema.Model.Usuario;
import Sistema.View.EncriptarPassword;
import Sistema.View.Permiso_Usuario;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class GUI_CambiarPassword extends javax.swing.JFrame {

    private Usuario user;
    private String tipoConexion;
    
    public GUI_CambiarPassword() {
        initComponents();  
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
    }
    public void cargarUsuario(Usuario userT, String tipoConexion) throws ParseException, UnknownHostException, SocketException{
        try {
            user = new ControlDB_Usuario(tipoConexion).buscarUsuarioEspecifico(userT.getCodigo());
        } catch (SQLException ex) {
            Logger.getLogger(GUI_CambiarPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        codigo.setText(user.getCodigo());
        nombre.setText(user.getNombres()+" "+user.getApellidos());
        
        this.tipoConexion=tipoConexion;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombre = new javax.swing.JLabel();
        us_clave_actual = new javax.swing.JPasswordField();
        jLabel30 = new javax.swing.JLabel();
        us_clave_nueva1 = new javax.swing.JPasswordField();
        jLabel27 = new javax.swing.JLabel();
        us_clave_nueva2 = new javax.swing.JPasswordField();
        viewPassword23 = new javax.swing.JCheckBox();
        RegistrarUsuario = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        codigo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nombre.setText("..................................");
        getContentPane().add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 550, 30));

        us_clave_actual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                us_clave_actualKeyTyped(evt);
            }
        });
        getContentPane().add(us_clave_actual, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 320, 30));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Contraseña Nueva:");
        getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 130, 30));

        us_clave_nueva1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                us_clave_nueva1KeyTyped(evt);
            }
        });
        getContentPane().add(us_clave_nueva1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 320, 30));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Repetir Contraseña  Nueva:");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 170, 30));

        us_clave_nueva2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                us_clave_nueva2KeyTyped(evt);
            }
        });
        getContentPane().add(us_clave_nueva2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 320, 30));

        viewPassword23.setBackground(new java.awt.Color(255, 255, 255));
        viewPassword23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        viewPassword23.setText("Mostrar Contraseña");
        viewPassword23.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                viewPassword23ItemStateChanged(evt);
            }
        });
        getContentPane().add(viewPassword23, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, -1, 30));

        RegistrarUsuario.setBackground(new java.awt.Color(255, 255, 255));
        RegistrarUsuario.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        RegistrarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/save.png"))); // NOI18N
        RegistrarUsuario.setText("CAMBIAR CONTRASEÑA");
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
        getContentPane().add(RegistrarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 210, 50));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("Contraseña Actual:");
        getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 130, 30));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setText("Nombre:");
        getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 60, 30));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("Código:");
        getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 30));

        codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        codigo.setText("..........................");
        getContentPane().add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 550, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void us_clave_actualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_us_clave_actualKeyTyped
        if (us_clave_actual.getText().length()== 15){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"La longitud total de la contraseña es 15 Caracteres", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_us_clave_actualKeyTyped

    private void us_clave_nueva1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_us_clave_nueva1KeyTyped
        if (us_clave_nueva1.getText().length()== 15){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"La longitud máxima permitida de la contraseña es 15 Caracteres", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_us_clave_nueva1KeyTyped

    private void us_clave_nueva2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_us_clave_nueva2KeyTyped
        if (us_clave_nueva2.getText().length()== 15){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"La longitud máxima permitida de la contraseña es 15 Caracteres", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_us_clave_nueva2KeyTyped

    private void viewPassword23ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_viewPassword23ItemStateChanged
        if(viewPassword23.isSelected()){
            us_clave_actual.setEchoChar((char)0); // este método es el que hace visible el texto del jPasswordField
            us_clave_nueva1.setEchoChar((char)0); // este método es el que hace visible el texto del jPasswordField
            us_clave_nueva2.setEchoChar((char)0); // este método es el que hace visible el texto del jPasswordField
        } else {
            us_clave_actual.setEchoChar('*'); // i es el char
            us_clave_nueva1.setEchoChar('*'); // i es el char
            us_clave_nueva2.setEchoChar('*'); // i es el char
        }
    }//GEN-LAST:event_viewPassword23ItemStateChanged

    private void RegistrarUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegistrarUsuarioMouseExited

    }//GEN-LAST:event_RegistrarUsuarioMouseExited

    private void RegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarUsuarioActionPerformed
        if(us_clave_actual.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Error!!.. La contraseña actual no puede estar vacia","Advertencia", JOptionPane.INFORMATION_MESSAGE);
            //alerta_us_clave.setText("Error!!.. La contraseña no puede estar vacia");
        }else{
            String claveActual=us_clave_actual.getText();
            if(new ControlDB_Usuario(tipoConexion).validarContraseña(user.getCodigo(),claveActual)){
                if(us_clave_nueva1.getText().equals("")|| us_clave_nueva2.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Ninguno de los dos campos de contraseñas pueden estar vacio", "Error!!", JOptionPane.ERROR_MESSAGE);
                }else{
                    if(!(us_clave_nueva1.getText().equals(us_clave_nueva2.getText()))){//Las dos contraseñas son diferentes
                        JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden verifique datos", "Error!!", JOptionPane.ERROR_MESSAGE);
                    }else{
                        String claveCifrada= new EncriptarPassword().md5(us_clave_nueva1.getText());
                        if(claveCifrada.equals(user.getClave()) || claveCifrada.equals(user.getClaveAnterior1()) || claveCifrada.equals(user.getClaveAnterior2())){
                            JOptionPane.showMessageDialog(null, "La contraseña digitada ya fue registrada anteriormente debe cambiarla", "Error!!", JOptionPane.ERROR_MESSAGE);
                        }else{
                            if(new ControlDB_Usuario(tipoConexion).validarComplejidadContraseña(us_clave_nueva1.getText())){
                                //Procedemos a registrar tanto el correo, como la nueva contraseña del usuario
                                int result = 0;
                                try {
                                    String contraseñaNueva=us_clave_nueva1.getText();
                                    String contraseñaAnterior1=user.getClave();
                                    String contraseñaAnterior2=user.getClaveAnterior1();
                                    
                                    
                                    System.out.println(""+contraseñaNueva+"#"+contraseñaAnterior1+"#"+contraseñaAnterior2);
                                    user.setClave(contraseñaNueva);
                                    user.setClaveAnterior1(contraseñaAnterior1);
                                    user.setClaveAnterior2(contraseñaAnterior2);
                                    //Usuario userRegistrar= user;
                                    //userRegistrar.setClave(us_clave_nueva1.getText());//Está sin cifrar
                                    //userRegistrar.setClaveAnterior1(user.getClave());//Está cifrada
                                    //userRegistrar.setClaveAnterior2(user.getClaveAnterior1());//Está cifrada
                                    //user=userRegistrar;
                                    
                                    result = new ControlDB_Usuario(tipoConexion).cambiarContraseñaSinCorreo(user);
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(GUI_CambiarPassword.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (UnknownHostException ex) {
                                    Logger.getLogger(GUI_CambiarPassword.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (SocketException ex) {
                                    Logger.getLogger(GUI_CambiarPassword.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if(result==1){
                                    JOptionPane.showMessageDialog(null, "Actualización registrada exitosamente");
                                    GUI_Iniciar guiIniciar = new GUI_Iniciar();
                                    guiIniciar.setVisible(true);
                                    this.setVisible(false); 
                                }else{
                                    JOptionPane.showMessageDialog(null, "Error al actualizar la información suministrada");
                                }
                            }
                        }
                    }
                }
            }else{//Contraseña actual digitada incorrecta
                JOptionPane.showMessageDialog(null, "La contraseña actual es incorrecta");
            }
        }
    }//GEN-LAST:event_RegistrarUsuarioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_CambiarPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_CambiarPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_CambiarPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_CambiarPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_CambiarPassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RegistrarUsuario;
    private javax.swing.JLabel codigo;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel nombre;
    private javax.swing.JPasswordField us_clave_actual;
    private javax.swing.JPasswordField us_clave_nueva1;
    private javax.swing.JPasswordField us_clave_nueva2;
    private javax.swing.JCheckBox viewPassword23;
    // End of variables declaration//GEN-END:variables
}
