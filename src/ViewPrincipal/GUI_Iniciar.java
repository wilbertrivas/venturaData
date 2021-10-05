package ViewPrincipal;
         
import Sistema.Controller.ControlDB_Usuario;
import Sistema.Model.Usuario;
import java.awt.Color; 
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class GUI_Iniciar extends javax.swing.JFrame {
    int contador=1;
    String tipoConexionS="";
    boolean verPassword=false;
    public GUI_Iniciar() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        
        //usuario.setText ("1111786243");
        //password.setText("Wr1v4s1992$");
        
        semaforoVerde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo.png")));
        semaforoAmarillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo.png")));
        semaforoRojo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo.png")));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        favicon = new javax.swing.JLabel();
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
        semaforoRojo = new javax.swing.JLabel();
        semaforoVerde = new javax.swing.JLabel();
        semaforoAmarillo = new javax.swing.JLabel();
        verPasswordIcon = new javax.swing.JLabel();

        setTitle("VENTURADATA");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        favicon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        favicon.setForeground(new java.awt.Color(51, 51, 51));
        favicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/login/favicon.png"))); // NOI18N
        favicon.setText("INICIAR SESIÓN");
        getContentPane().add(favicon, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 230, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("CONEXIÓN:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, 80, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("USUARIO:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, 80, 30));

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
        getContentPane().add(registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 150, 40));

        usuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        usuario.setForeground(new java.awt.Color(102, 102, 102));
        usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usuarioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                usuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                usuarioMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                usuarioMouseReleased(evt);
            }
        });
        usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usuarioKeyPressed(evt);
            }
        });
        getContentPane().add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, 240, 30));

        alertaUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        alertaUsuario.setForeground(new java.awt.Color(255, 0, 51));
        getContentPane().add(alertaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 450, 620, 20));

        password.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        password.setForeground(new java.awt.Color(102, 102, 102));
        password.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        password.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                passwordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                passwordMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                passwordMouseReleased(evt);
            }
        });
        password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordKeyPressed(evt);
            }
        });
        getContentPane().add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, 240, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/LogoPrincipal.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 340, 290));

        tipoConexion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PRIVADA", "PUBLICA" }));
        getContentPane().add(tipoConexion, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 420, 240, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("CLAVE:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 80, 30));

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
        getContentPane().add(registrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 480, 150, 40));

        semaforoRojo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo_verde_f.png"))); // NOI18N
        getContentPane().add(semaforoRojo, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 530, 30, 30));

        semaforoVerde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo_verde_f.png"))); // NOI18N
        getContentPane().add(semaforoVerde, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 530, 30, 30));

        semaforoAmarillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo_verde_f.png"))); // NOI18N
        getContentPane().add(semaforoAmarillo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 530, 30, 30));

        verPasswordIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/login/ocultarPassword.png"))); // NOI18N
        verPasswordIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verPasswordIconMouseClicked(evt);
            }
        });
        getContentPane().add(verPasswordIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 380, 60, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioMouseClicked

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        Usuario u = new Usuario();
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
                
                u.setCodigo(usuario.getText());
                u.setClave(password.getText());    
                try {
                    new ControlDB_Usuario(tipoConexionS).cambiarLenguaje();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GUI_Iniciar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(GUI_Iniciar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SocketException ex) {
                    Logger.getLogger(GUI_Iniciar.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                if( new ControlDB_Usuario((tipoConexionS)).validarUsuario_Bloqueado(u)){//El usuario se encuentra bloqueado por tal motivo, pedimos que se comunique con el adminsitrador del sistema
                    JOptionPane.showMessageDialog(null, "Su usuario se encuentra bloqueado, favor comunicarse con el administrador del sistema","Error!!.",JOptionPane.INFORMATION_MESSAGE);
                    semaforoVerde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo.png")));
                    semaforoAmarillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo.png")));
                    semaforoRojo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo_rojo_f.png")));
                }else{
                    if(!(new ControlDB_Usuario((tipoConexionS)).validarUsuario_CambiarClave(u))){//!(true)= no está pidiendo cambio de Clave  
                        //if(!(new ControlDB_Usuario((tipoConexionS)).validarUsuario_cantidadIntento(u))){//!(true)= la cantidad de intento es menor de 4
                            if(new ControlDB_Usuario((tipoConexionS)).validarUsuario_PorCodigo(u)){//El codigo del usuario si existe en la base de datos
                                Usuario user = new ControlDB_Usuario(tipoConexionS).validarUsuario(u); 
                                if(user != null){//El usuario y la contraseña fueron correctas
                                    int validarVencimientoClave=1;
                                    try {
                                        validarVencimientoClave = new ControlDB_Usuario(tipoConexionS).validarCambioContraseña(u);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(GUI_Iniciar.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    if((validarVencimientoClave==0)){
                                        if(user.getEstado().equalsIgnoreCase("ACTIVO")){
                                            if(contador==1) { 
                                                //Actualizamos los intentos a cero
                                                new ControlDB_Usuario(tipoConexionS).resetIntentos(user);
                                                
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
                                            JOptionPane.showMessageDialog(null, "Error!.. El usuario no se encuentra activo en el sistema", "Error!!. valide con el administrador",JOptionPane.INFORMATION_MESSAGE);
                                            semaforoVerde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo.png")));
                                            semaforoAmarillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo.png")));
                                            semaforoRojo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo_rojo_f.png")));
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Su contraseña lleva más de 3 meses sin cambiarla, debe cambiar su contraseña inmediatamente por seguridad", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                                        //####################################### 
                                        GUI_CambiarPassword guiCambiarPassword = new GUI_CambiarPassword();
                                        try {//tipoConexion
                                            guiCambiarPassword.cargarUsuario(u,tipoConexionS);
                                        } catch (ParseException ex) {
                                            Logger.getLogger(GUI_CambiarPassword.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (UnknownHostException ex) {
                                            Logger.getLogger(GUI_CambiarPassword.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (SocketException ex) {
                                            Logger.getLogger(GUI_CambiarPassword.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        guiCambiarPassword.setVisible(true);
                                        this.setVisible(false); 
                                    }
                                }else{
                                    //actualizamos la cantidad de intento sumando 1, si cantIntento=5, sesiónBloqueda=1, de lo contrario cantIntento +1
                                    new ControlDB_Usuario(tipoConexionS).ingresoContraseñaIncorrecta(u);
                                    try {
                                        //consultamos la cantidad de intentos
                                        int cantidadIntentos=new ControlDB_Usuario(tipoConexionS).consultarCantidadIntento(u);
                                        if(cantidadIntentos<= 4){
                                            JOptionPane.showMessageDialog(null, "Contraseña incorrecta, intento restantes "+(5 - cantidadIntentos)+", verifique información","Error!!.",JOptionPane.INFORMATION_MESSAGE);
                                            if(cantidadIntentos <= 2){
                                                semaforoVerde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo_verde_f.png")));
                                                semaforoAmarillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo.png")));
                                                semaforoRojo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo.png")));
                                            }else{
                                                if(cantidadIntentos <= 4){
                                                    semaforoVerde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo.png")));
                                                    semaforoAmarillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo_amarillo_f.png")));
                                                    semaforoRojo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo.png")));
                                                }else{
                                                    semaforoVerde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo.png")));
                                                    semaforoAmarillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo.png")));
                                                    semaforoRojo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo_rojo_f.png")));
                                                }
                                            }
                                        }else{//La cantidad de intentos = 5, el usuario fue bloqueado
                                            JOptionPane.showMessageDialog(null, "Contraseña incorrecta, su cuenta fue bloqueada, consulte con el administrador del sistema","Error!!.",JOptionPane.INFORMATION_MESSAGE);
                                            semaforoVerde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo.png")));
                                            semaforoAmarillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo.png")));
                                            semaforoRojo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/login/semaforo_rojo_f.png"))); 
                                        }
                                    } catch (SQLException ex) {
                                        Logger.getLogger(GUI_Iniciar.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }else{//El código del usuario no existe en la base de datos, por tal motivo no se toma como un intento
                                JOptionPane.showMessageDialog(null, "El código ingresado no existe en la base de datos, valide nuevamente","Error!!.",JOptionPane.INFORMATION_MESSAGE);
                            } 
                        //}else{
                            
                        //}
                    }else{//El sistema está pidiendo cambio de clave por tal motivo mandamos a la interfaz de cambio de password
                        //####################################### 
                        //mandamos a la interfaz de cambiar clave
                        JOptionPane.showMessageDialog(null, "Debe cambiar su contraseña inmediatamente","Advertencia!!.",JOptionPane.INFORMATION_MESSAGE);                            
                        GUI_CambiarPassword guiCambiarPassword = new GUI_CambiarPassword();
                        try {//tipoConexion
                            guiCambiarPassword.cargarUsuario(u,tipoConexionS);
                        } catch (ParseException ex) {
                            Logger.getLogger(GUI_CambiarPassword.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (UnknownHostException ex) {
                            Logger.getLogger(GUI_CambiarPassword.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SocketException ex) {
                            Logger.getLogger(GUI_CambiarPassword.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        guiCambiarPassword.setVisible(true);
                        this.setVisible(false); 
                    }
                }
            }
        }
                
                /*
                
                
                
                
                
                //Buscamos en nuestra base de datos si el usuario y password con correcto
                Usuario u = new Usuario();
                 
                u.setCodigo(usuario.getText());
                u.setClave(password.getText());       
                if(new ControlDB_Usuario((tipoConexionS)).validarUsuario_PorCodigo(u)){//El codigo del usuario si existe en la base de datos
                    Usuario user = controlDB_Usuario.validarUsuario(u); 
                    if(user != null){
                        //Validamos si el usuario está bloqueado o Pide cambio de clave
                        if( new ControlDB_Usuario((tipoConexionS)).validarUsuario_Bloqueado(u)){//El usuario se encuentra bloqueado por tal motivo, pedimos que se comunique con el adminsitrador del sistema
                            JOptionPane.showMessageDialog(null, "Su usuario se encuentra bloqueado, favor comunicarse con el administrador del sistema","Error!!.",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            //Validamos si el sistema está pidiendo cambio de clave
                            if( new ControlDB_Usuario((tipoConexionS)).validarUsuario_CambiarClave(u)){//El sistema está pidiendo cambio de clave por tal motivo mandamos a la interfaz de cambio de password
                               //INSERT AQUI EL CODIGO PARA MANDAR A LA INTERFAZ DE CAMBIAS CLAVE 
                                
                            }else{
                                
                            }
                        }
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta, verifique información","Error!!.",JOptionPane.INFORMATION_MESSAGE);
                        /**/
                        //alertaUsuario.setText("Error!.. Contraseña incorrecta verifique datos");
                    /*}
                }else{//El código del usuario no existe en la base de datos
                
                }
                
                
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
        }*/
    }//GEN-LAST:event_registrarActionPerformed

    private void registrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_registrarKeyPressed
       
    }//GEN-LAST:event_registrarKeyPressed

    private void registrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrar1ActionPerformed
         System.exit(0);
    }//GEN-LAST:event_registrar1ActionPerformed

    private void registrar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_registrar1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_registrar1KeyPressed

    private void verPasswordIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verPasswordIconMouseClicked
        if(verPassword){
            verPassword=false;
        }else{
            verPassword=true;
        }
        
        if(verPassword){//True
            verPasswordIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/login/verPassword.png")));
            password.setEchoChar((char)0);
        }else{
            verPasswordIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/login/ocultarPassword.png")));
             password.setEchoChar('*'); // i es el char
        }
    }//GEN-LAST:event_verPasswordIconMouseClicked

    private void usuarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioMouseReleased
 
    }//GEN-LAST:event_usuarioMouseReleased

    private void passwordMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordMouseReleased

    }//GEN-LAST:event_passwordMouseReleased

    private void usuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioMouseEntered
        boolean capsAtivo = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        if(capsAtivo){//Bloa Mayus está activo
            alertaUsuario.setText("                             Bloq Mayús activado");
        }else{
            alertaUsuario.setText("                             Bloq Mayús inactivo");
        }
    }//GEN-LAST:event_usuarioMouseEntered

    private void usuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioMouseExited
        alertaUsuario.setText("");
    }//GEN-LAST:event_usuarioMouseExited

    private void usuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioKeyPressed
        boolean capsAtivo = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        if(capsAtivo){//Bloa Mayus está activo
            alertaUsuario.setText("                             Bloq Mayús activado");
        }else{
            alertaUsuario.setText("                             Bloq Mayús inactivo");
        }
    }//GEN-LAST:event_usuarioKeyPressed

    private void passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyPressed
        boolean capsAtivo = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        if(capsAtivo){//Bloa Mayus está activo
            alertaUsuario.setText("                             Bloq Mayús activado");
        }else{
            alertaUsuario.setText("                             Bloq Mayús inactivo");
        }
    }//GEN-LAST:event_passwordKeyPressed

    private void passwordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordMouseEntered
        boolean capsAtivo = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        if(capsAtivo){//Bloa Mayus está activo
            alertaUsuario.setText("                             Bloq Mayús activado");
        }else{
            alertaUsuario.setText("                             Bloq Mayús inactivo");
        }
    }//GEN-LAST:event_passwordMouseEntered

    private void passwordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordMouseExited
        alertaUsuario.setText("");
    }//GEN-LAST:event_passwordMouseExited

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
    private javax.swing.JLabel favicon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField password;
    private javax.swing.JButton registrar;
    private javax.swing.JButton registrar1;
    private javax.swing.JLabel semaforoAmarillo;
    private javax.swing.JLabel semaforoRojo;
    private javax.swing.JLabel semaforoVerde;
    private javax.swing.JComboBox<String> tipoConexion;
    private javax.swing.JTextField usuario;
    private javax.swing.JLabel verPasswordIcon;
    // End of variables declaration//GEN-END:variables
}
