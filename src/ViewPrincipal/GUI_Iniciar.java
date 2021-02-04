package ViewPrincipal;
         
import Sistema.Controller.ControlDB_Usuario;
import Sistema.Model.Usuario;
import java.awt.Color; 
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUI_Iniciar extends javax.swing.JFrame {
    int contador=1;
    String tipoConexionS="";
    
    public GUI_Iniciar() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        
        usuario.setText ("1111786243");
        password.setText("1111786243");
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        registrar = new javax.swing.JButton();
        usuario = new javax.swing.JTextField();
        alertaUsuario = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        tipoConexion = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        registrar1 = new javax.swing.JButton();

        setTitle("VENTURADATA");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("CONEXIÓN:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 80, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("USUARIO:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 80, 30));

        registrar.setBackground(new java.awt.Color(255, 255, 255));
        registrar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Ingresar.png"))); // NOI18N
        registrar.setText("INGRESAR");
        registrar.setBorder(null);
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });
        registrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                registrarKeyPressed(evt);
            }
        });
        getContentPane().add(registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 150, 40));

        usuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        usuario.setForeground(new java.awt.Color(102, 102, 102));
        usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usuarioMouseClicked(evt);
            }
        });
        getContentPane().add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, 240, 30));

        alertaUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        alertaUsuario.setForeground(new java.awt.Color(255, 0, 51));
        getContentPane().add(alertaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 410, 620, 20));

        password.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        password.setForeground(new java.awt.Color(102, 102, 102));
        password.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, 240, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/LogoPrincipal.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 340, 290));

        tipoConexion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PRIVADA", "PUBLICA" }));
        getContentPane().add(tipoConexion, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, 240, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("CLAVE:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, 80, 30));

        registrar1.setBackground(new java.awt.Color(255, 255, 255));
        registrar1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        registrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/salirGUI.png"))); // NOI18N
        registrar1.setText("SALIR");
        registrar1.setBorder(null);
        registrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrar1ActionPerformed(evt);
            }
        });
        registrar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                registrar1KeyPressed(evt);
            }
        });
        getContentPane().add(registrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 440, 150, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioMouseClicked

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        if(tipoConexion.getSelectedIndex()==0){//Conexión tipo Privada
            tipoConexionS="privado";
        }else{
            if(tipoConexion.getSelectedIndex()==1){//Conexión tipo Publica
                tipoConexionS="publico";
            }
        }
        if(usuario.getText().equals("")){
            alertaUsuario.setText("Error!.. El usuario no puede estar vacio");
        }else{
            if(password.getText().equals("")){
                alertaUsuario.setText("Error!.. La contraseña no puede estar vacia");
            }else{
                //Buscamos en nuestra base de datos si el usuario y password con correcto
                ControlDB_Usuario controlDB_Usuario = new ControlDB_Usuario(tipoConexionS);
                Usuario u = new Usuario();
                 
                u.setCodigo(usuario.getText());
                u.setClave(password.getText());                     
                Usuario user = controlDB_Usuario.validarUsuario(u);
                
                if(user != null){
                    if(user.getEstado().equalsIgnoreCase("ACTIVO")){
                        if(contador==1){ 
                            GUI_Principal guiPrincipal= new GUI_Principal();
                            try {//tipoConexion
                                guiPrincipal.cargarUsuario(user,tipoConexionS);
                                guiPrincipal.cargaMenu();
                            } catch (ParseException ex) {
                                Logger.getLogger(GUI_Iniciar.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (UnknownHostException ex) {
                                Logger.getLogger(GUI_Iniciar.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SocketException ex) {
                                Logger.getLogger(GUI_Iniciar.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            guiPrincipal.setVisible(true);
                            this.setVisible(false); 
                        }contador++;
                    }else{
                        alertaUsuario.setText("Error!.. El usuario no se encuentra activo en el sistema");
                    }
                }else{
                    alertaUsuario.setText("Error!.. Usuario o Contraseña Incorrecta verifique datos");
                }
            }
        }
    }//GEN-LAST:event_registrarActionPerformed

    private void registrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_registrarKeyPressed
       
    }//GEN-LAST:event_registrarKeyPressed

    private void registrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrar1ActionPerformed
         System.exit(0);
    }//GEN-LAST:event_registrar1ActionPerformed

    private void registrar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_registrar1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_registrar1KeyPressed

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
            java.util.logging.Logger.getLogger(GUI_Iniciar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Iniciar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Iniciar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Iniciar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Iniciar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alertaUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField password;
    private javax.swing.JButton registrar;
    private javax.swing.JButton registrar1;
    private javax.swing.JComboBox<String> tipoConexion;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}
