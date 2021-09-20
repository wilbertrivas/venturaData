package Sistema.View;

import Sistema.Controller.ControlDB_Usuario;
import Sistema.Model.Perfil;
import Sistema.Model.Usuario;
import ViewPrincipal.GUI_CambiarPassword;
import ViewPrincipal.GUI_Iniciar;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
 
public final class Usuario_CambiarClave extends javax.swing.JPanel {
    Usuario user=null;
    private String tipoConexion;
    public Usuario_CambiarClave(Usuario u,String tipoConexion) { 
        initComponents();
        try {
            user = new ControlDB_Usuario(tipoConexion).buscarUsuarioEspecifico(u.getCodigo());
        } catch (SQLException ex) {
            Logger.getLogger(GUI_CambiarPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.tipoConexion= tipoConexion;
        
        //Deshabilitamos ciertos campos
        us_cdgo.setEnabled(false);
        us_nombres.setEnabled(false);
        us_apellidos.setEnabled(false);
        us_perfil.setEnabled(false);
        estado.setEnabled(false);
        
        //Ocultamos los campos de claves
        jLabel29.show(false);
        jLabel30.show(false);
        jLabel27.show(false);
        us_clave_actual.show(false);
        us_clave_nueva1.show(false);
        us_clave_nueva2.show(false);
        viewPassword23.show(false);
        
        //Si el usuario es diferente de null procedemos a mostrar la información del usuario actual logueado en la aplicación
        if(user != null){
            //Cargamos la información del usuario
            us_cdgo.setText(user.getCodigo());
            us_nombres.setText(user.getNombres());
            us_apellidos.setText(user.getApellidos());
            us_perfil.setText(user.getPerfilUsuario().getDescripcion());
            estado.setText(user.getEstado());
            us_correo.setText(user.getCorreo());
            us_clave_actual.setText("");
            us_clave_nueva1.setText("");
            us_clave_nueva2.setText("");        
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        us_cdgo = new javax.swing.JTextField();
        us_correo = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        us_nombres = new javax.swing.JTextField();
        us_apellidos = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        us_clave_actual = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        restablecerContrasena = new javax.swing.JRadioButton();
        RegistrarUsuario = new javax.swing.JButton();
        us_clave_nueva1 = new javax.swing.JPasswordField();
        us_clave_nueva2 = new javax.swing.JPasswordField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        estado = new javax.swing.JTextField();
        us_perfil = new javax.swing.JTextField();
        viewPassword23 = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("ACTUALIZAR DATOS DEL USUARIO");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 380, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Apellidos:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 80, 30));

        us_cdgo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                us_cdgoMouseClicked(evt);
            }
        });
        add(us_cdgo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 400, 30));

        us_correo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                us_correoMouseClicked(evt);
            }
        });
        add(us_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 140, 400, 30));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Repetir Contraseña  Nueva:");
        add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 170, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Nombres:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, 70, 30));

        us_nombres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                us_nombresMouseClicked(evt);
            }
        });
        add(us_nombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 400, 30));

        us_apellidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                us_apellidosMouseClicked(evt);
            }
        });
        add(us_apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 400, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Cedula:");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 140, 30));

        us_clave_actual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                us_clave_actualKeyTyped(evt);
            }
        });
        add(us_clave_actual, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 190, 320, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Perfil del Usuario:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, 110, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Estado:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 70, 30));

        restablecerContrasena.setBackground(new java.awt.Color(255, 255, 255));
        restablecerContrasena.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        restablecerContrasena.setText("Cambio de Contraseña:");
        restablecerContrasena.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                restablecerContrasenaStateChanged(evt);
            }
        });
        add(restablecerContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 180, -1));

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
        add(RegistrarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 360, 210, 50));

        us_clave_nueva1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                us_clave_nueva1KeyTyped(evt);
            }
        });
        add(us_clave_nueva1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, 320, 30));

        us_clave_nueva2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                us_clave_nueva2KeyTyped(evt);
            }
        });
        add(us_clave_nueva2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 270, 320, 30));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("Correo:");
        add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 140, 80, 30));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Contraseña Actual:");
        add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 130, 30));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Contraseña Nueva:");
        add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 130, 30));

        estado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                estadoMouseClicked(evt);
            }
        });
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 400, 30));

        us_perfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                us_perfilMouseClicked(evt);
            }
        });
        add(us_perfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 400, 30));

        viewPassword23.setBackground(new java.awt.Color(255, 255, 255));
        viewPassword23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        viewPassword23.setText("Mostrar Contraseña");
        viewPassword23.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                viewPassword23ItemStateChanged(evt);
            }
        });
        add(viewPassword23, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 310, -1, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void us_cdgoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_us_cdgoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_us_cdgoMouseClicked

    private void us_correoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_us_correoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_us_correoMouseClicked

    private void us_nombresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_us_nombresMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_us_nombresMouseClicked

    private void us_apellidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_us_apellidosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_us_apellidosMouseClicked

    private void us_clave_actualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_us_clave_actualKeyTyped
        if (us_clave_actual.getText().length()== 15){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"La longitud total de la contraseña es 15 Caracteres", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_us_clave_actualKeyTyped

    private void restablecerContrasenaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_restablecerContrasenaStateChanged
        if(restablecerContrasena.isSelected()){
            jLabel29.show(true);
            jLabel30.show(true);
            jLabel27.show(true);
            us_clave_actual.show(true);
            us_clave_nueva1.show(true);
            us_clave_nueva2.show(true);    
            viewPassword23.show(true);    
        }else{
            jLabel29.show(false);
            jLabel30.show(false);
            jLabel27.show(false);
            us_clave_actual.show(false);
            us_clave_nueva1.show(false);
            us_clave_nueva2.show(false);
            viewPassword23.show(false);
        }
    }//GEN-LAST:event_restablecerContrasenaStateChanged

    private void RegistrarUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegistrarUsuarioMouseExited
        
    }//GEN-LAST:event_RegistrarUsuarioMouseExited

    private void RegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarUsuarioActionPerformed
        boolean validar= true;
        boolean validarClave=false;
        if(us_correo.getText().equals("")){
            validar=true;
        }else{
            // Patrón para validar el email
            Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            // El email a validar
            String email = us_correo.getText();
            Matcher mather = pattern.matcher(email);
            if (mather.find() == true) {
                validar=true;
            }else{
                validar=false;
            }
        }
        if(validar){
            String correoAnterior=user.getCorreo();
            user.setCorreo(us_correo.getText());
            if(restablecerContrasena.isSelected()){
                validarClave=true;
                if(us_clave_actual.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Error!!.. La contraseña actual no puede estar vacia","Advertencia", JOptionPane.INFORMATION_MESSAGE);
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
                                                user.setClave(contraseñaNueva);
                                                user.setClaveAnterior1(contraseñaAnterior1);
                                                user.setClaveAnterior2(contraseñaAnterior2);
                                                if(validarClave){
                                                    result = new ControlDB_Usuario(tipoConexion).cambiarContraseñaConCorreo(user, correoAnterior);
                                                }
                                            } catch (FileNotFoundException ex) {
                                                Logger.getLogger(Usuario_CambiarClave.class.getName()).log(Level.SEVERE, null, ex);
                                            } catch (UnknownHostException ex) {
                                            Logger.getLogger(Usuario_CambiarClave.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (SocketException ex) {
                                            Logger.getLogger(Usuario_CambiarClave.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        if(result==1){
                                          
                                            JOptionPane.showMessageDialog(null, "Actualización registrada exitosamente");
                                            System.exit(0);
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
            }else{
                int result = 0;
                try {
                    result = new ControlDB_Usuario(tipoConexion).cambiarCorreo(user, correoAnterior);
                } catch (FileNotFoundException ex) {
                        Logger.getLogger(Usuario_CambiarClave.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnknownHostException ex) {
                    Logger.getLogger(Usuario_CambiarClave.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SocketException ex) {
                    Logger.getLogger(Usuario_CambiarClave.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(result==1){
                    JOptionPane.showMessageDialog(null, "Actualización registrada exitosamente");
                    System.exit(0);
                }else{
                    JOptionPane.showMessageDialog(null, "Error al actualizar la información suministrada");
                }
            }
        }
    }//GEN-LAST:event_RegistrarUsuarioActionPerformed

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

    private void estadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_estadoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_estadoMouseClicked

    private void us_perfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_us_perfilMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_us_perfilMouseClicked

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RegistrarUsuario;
    private javax.swing.JTextField estado;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton restablecerContrasena;
    private javax.swing.JTextField us_apellidos;
    private javax.swing.JTextField us_cdgo;
    private javax.swing.JPasswordField us_clave_actual;
    private javax.swing.JPasswordField us_clave_nueva1;
    private javax.swing.JPasswordField us_clave_nueva2;
    private javax.swing.JTextField us_correo;
    private javax.swing.JTextField us_nombres;
    private javax.swing.JTextField us_perfil;
    private javax.swing.JCheckBox viewPassword23;
    // End of variables declaration//GEN-END:variables
    /*String resultado = "Muy Buena";    // Resultado de password valido

        int length = 0;                     // Almacenamos numero de caracteres en el pass
        int numCount = 0;                   // Variable usada para almacenar numeros en el password
        int capCount = 0;                   // Variable usada para almacenar mayusculas en el password
        int capSignos = 0;                  // Variable usada para almacenar los signos
        int Arroba = 0;                     // solo la arroba -.-!

        for (int x = 0; x < Password.length(); x++) {
            if ((Password.charAt(x) >= 47 && Password.charAt(x) <= 58) //numeros
                    || (Password.charAt(x) >= 64 && Password.charAt(x) <= 91) //mayusculas
                    || (Password.charAt(x) >= 63 && Password.charAt(x) <= 65) //Arroba
                    || (Password.charAt(x) >= 32 && Password.charAt(x) <= 44) //signos
                    || (Password.charAt(x) >= 97 && Password.charAt(x) <= 122)) 
    {  //minusculas

            }
            if ((Password.charAt(x) > 63 && Password.charAt(x) < 65)) { // Cuenta laS arrobas
                Arroba++;
            }
            if ((Password.charAt(x) > 32 && Password.charAt(x) < 44)) { // Cuenta la cantidad signos
                capSignos++;
            }
            if ((Password.charAt(x) > 47 && Password.charAt(x) < 58)) { // Cuenta la cantidad de numero
                numCount++;
            }

            if ((Password.charAt(x) > 64 && Password.charAt(x) < 91)) { // Cuenta la cantidad de mayuscula
                capCount++;
            }

            length = (x + 1); // Cuenta la longitud del password

        } // Final del bucle

        if (capSignos < 1) {                // Revisa la longitud minima de 8 caracteres del password
            resultado = "no tiene caracteres especiales como ( ! # $ % & ' ( ) + - )";
        }
        if (Arroba < 1) {                // Revisa la longitud minima de 8 caracteres del password
            resultado = "Coloque un @ para mayor seguridad";
        }        
        if (numCount < 1) {              // Revisa que el password contenga minimo 1 numero
            resultado = "Medio";
        }

        if (capCount < 1) {                            // Revisa que el password contenga minimo 1 mayuscula
            resultado = "Facil";
        }

        if (length < 5) {                // Revisa la longitud minima de 8 caracteres del password
            resultado = "Inutilizable: no cumple con el mínimo de caracteres!";
        }

        jLabel2.setText(resultado);*/

}
