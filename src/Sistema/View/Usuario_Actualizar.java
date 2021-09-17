package Sistema.View2;

import Sistema.Controller.ControlDB_Usuario;
import Sistema.Model.Perfil;
import Sistema.Model.Usuario;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
 
public final class Usuario_Actualizar extends javax.swing.JPanel {
    Usuario user;
    ArrayList<Usuario> listado;
    private String tipoConexion;
    public Usuario_Actualizar(Usuario u,String tipoConexion) { 
        initComponents();
        user= u;
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
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los usuarios");
            Logger.getLogger(Usuario_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
        us_clave.show(false);
        us_cdgo.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        valorBusqueda = new javax.swing.JTextField();
        consultar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        us_cdgo = new javax.swing.JTextField();
        us_correo = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        us_nombres = new javax.swing.JTextField();
        us_apellidos = new javax.swing.JTextField();
        alerta_us_nombres = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        us_clave = new javax.swing.JPasswordField();
        alerta_us_clave = new javax.swing.JLabel();
        us_estad = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        alerta_us_apellidos = new javax.swing.JLabel();
        us_perfil_usuario_cdgo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        alerta_us_correo = new javax.swing.JLabel();
        restablecerContrasena = new javax.swing.JRadioButton();
        RegistrarUsuario = new javax.swing.JButton();

        Editar.setText("Modificar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("ACTUALIZACIONES DE USUARIO");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 310, 30));

        tabla = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla.setComponentPopupMenu(Seleccionar);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 790, 470));
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 280, 30));

        consultar.setBackground(new java.awt.Color(255, 255, 255));
        consultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consultar.setText("CONSULTAR");
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        add(consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, 140, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Apellidos:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 80, 30));

        us_cdgo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                us_cdgoMouseClicked(evt);
            }
        });
        add(us_cdgo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 280, 30));

        us_correo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                us_correoMouseClicked(evt);
            }
        });
        add(us_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 280, 30));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Correo:");
        add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 80, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Nombres:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 70, 30));

        us_nombres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                us_nombresMouseClicked(evt);
            }
        });
        add(us_nombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 280, 30));

        us_apellidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                us_apellidosMouseClicked(evt);
            }
        });
        add(us_apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 280, 30));

        alerta_us_nombres.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_us_nombres.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_us_nombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 320, 20));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Cedula:");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 140, 30));

        us_clave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                us_claveKeyTyped(evt);
            }
        });
        add(us_clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 280, 30));

        alerta_us_clave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_us_clave.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_us_clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 410, 320, 20));

        us_estad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        add(us_estad, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 280, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Perfil del Usuario:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 120, 20));

        alerta_us_apellidos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_us_apellidos.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_us_apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 320, 20));

        add(us_perfil_usuario_cdgo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 280, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Estado:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 70, 20));

        alerta_us_correo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_us_correo.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_us_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, 320, 20));

        restablecerContrasena.setBackground(new java.awt.Color(255, 255, 255));
        restablecerContrasena.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        restablecerContrasena.setText("Cambio de Password");
        restablecerContrasena.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                restablecerContrasenaStateChanged(evt);
            }
        });
        add(restablecerContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 160, -1));

        RegistrarUsuario.setBackground(new java.awt.Color(255, 255, 255));
        RegistrarUsuario.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        RegistrarUsuario.setText("ACTUALIZAR USUARIO");
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
        add(RegistrarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 420, 210, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        try {
            tabla_Listar(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_consultarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO lo del clik derechoo
        int fila1;
        try{
            fila1=tabla.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                String codigoS=(String)modelo.getValueAt(fila1, 0);
                for(Usuario Objeto: listado){
                    if(Objeto.getCodigo().equals(codigoS)){
                        us_cdgo.setText(Objeto.getCodigo());
                        us_nombres.setText(Objeto.getNombres());
                        us_apellidos.setText(Objeto.getApellidos());
                        us_perfil_usuario_cdgo.setSelectedItem((String)(Objeto.getPerfilUsuario().getCodigo()+":"+Objeto.getPerfilUsuario().getDescripcion()));
                        if(Objeto.getEstado().equalsIgnoreCase("ACTIVO")){
                            us_estad.setSelectedIndex(0);
                        }else{
                            us_estad.setSelectedIndex(1);
                        }
                        us_correo.setText(Objeto.getCorreo());
                    }
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() == 2) {
            // TODO lo del clik derechoo
            int fila1;
            try{
                fila1=tabla.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                    String codigoS=(String)modelo.getValueAt(fila1, 0);
                    for(Usuario Objeto: listado){
                        if(Objeto.getCodigo().equals(codigoS)){
                            us_cdgo.setText(Objeto.getCodigo());
                            us_nombres.setText(Objeto.getNombres());
                            us_apellidos.setText(Objeto.getApellidos());
                            us_perfil_usuario_cdgo.setSelectedItem((String)(Objeto.getPerfilUsuario().getCodigo()+":"+Objeto.getPerfilUsuario().getDescripcion()));
                            if(Objeto.getEstado().equalsIgnoreCase("ACTIVO")){
                                us_estad.setSelectedIndex(0);
                            }else{
                                us_estad.setSelectedIndex(1);
                            }
                            us_correo.setText(Objeto.getCorreo());
                        }
                    }
                }
            }catch(Exception e){
            }
        } 
    }//GEN-LAST:event_tablaMouseClicked

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

    private void us_claveKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_us_claveKeyTyped
        if (us_clave.getText().length()== 15){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
            alerta_us_clave.setText("La longitud total de la contraseña es 15 Caracteres");
        }else{
            alerta_us_clave.setText("");
        }
    }//GEN-LAST:event_us_claveKeyTyped

    private void restablecerContrasenaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_restablecerContrasenaStateChanged
        if(restablecerContrasena.isSelected()){
            us_clave.show(true);
        }else{
            us_clave.show(false);
        }
    }//GEN-LAST:event_restablecerContrasenaStateChanged

    private void RegistrarUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegistrarUsuarioMouseExited
        alerta_us_clave.setText("");
        alerta_us_nombres.setText("");
        alerta_us_apellidos.setText("");
        alerta_us_correo.setText("");
    }//GEN-LAST:event_RegistrarUsuarioMouseExited

    private void RegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarUsuarioActionPerformed
        if(us_cdgo.getText().equals("")){
        }else{
            if(us_nombres.getText().equals("")){
                alerta_us_nombres.setText("Error!!.. El nombre del usuario no puede estar vacio");
            }else{
                if(us_apellidos.getText().equals("")){
                    alerta_us_apellidos.setText("Error!!.. Los apellidos no pueden estar vacio");
                }else{
                    try{
                        Integer.parseInt(us_cdgo.getText());
                        if(!us_correo.getText().equalsIgnoreCase("")){//El usuario ingresó informacion en el campo correo por tal motivo validamos que sea un correo valido
                            // Patrón para validar el email
                            Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

                            // El email a validar
                            String email = us_correo.getText();
                            Matcher mather = pattern.matcher(email);
                            if (mather.find() == true) {
                                if(restablecerContrasena.isSelected()){
                                    if(us_clave.getText().equals("")){
                                        alerta_us_clave.setText("Error!!.. La contraseña no puede estar vacia");
                                    }else{
                                        Usuario us = new Usuario();
                                        us.setCodigo(us_cdgo.getText());
                                        us.setClave(us_clave.getText());
                                        us.setNombres(us_nombres.getText());
                                        us.setApellidos(us_apellidos.getText());
                                        String[]datosPerfil=us_perfil_usuario_cdgo.getSelectedItem().toString().split(":");
                                        us.setPerfilUsuario(new Perfil(datosPerfil[0],datosPerfil[1]));
                                        us.setCorreo(us_correo.getText());

                                        //Validamos si selecciono activo o inactivo
                                        if(us_estad.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                                            us.setEstado("1");
                                        }else{
                                            us.setEstado("0");
                                        }
                                        int result = 0;
                                        try {
                                            result = new ControlDB_Usuario(tipoConexion).actualizarConPassword(us, user);
                                        } catch (FileNotFoundException ex) {
                                            Logger.getLogger(Usuario_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        if(result==1){
                                            JOptionPane.showMessageDialog(null, "Actualización registrada exitosamente");
                                            us_cdgo.setText("");
                                            us_clave.setText("");
                                            us_nombres.setText("");
                                            us_apellidos.setText("");
                                            us_perfil_usuario_cdgo.setSelectedIndex(0);
                                            us_correo.setText("");
                                            us_estad.setSelectedIndex(0);
                                            alerta_us_clave.setText("");
                                            alerta_us_nombres.setText("");
                                            alerta_us_apellidos.setText("");
                                            try {
                                                tabla_Listar("");
                                            } catch (SQLException ex) {
                                                JOptionPane.showMessageDialog(null, "Error al tratar de consultar los usuarios");
                                                Logger.getLogger(Usuario_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }else{
                                            JOptionPane.showMessageDialog(null, "Error Al Registrar el Usuario");
                                        }  
                                    }
                                }else{//No se va a actualiza la contraseña
                                    Usuario us = new Usuario();
                                    us.setCodigo(us_cdgo.getText());
                                    us.setNombres(us_nombres.getText());
                                    us.setApellidos(us_apellidos.getText());
                                    String[]datosPerfil=us_perfil_usuario_cdgo.getSelectedItem().toString().split(":");
                                    us.setPerfilUsuario(new Perfil(datosPerfil[0],datosPerfil[1]));
                                    us.setCorreo(us_correo.getText());
                                    //Validamos si selecciono activo o inactivo
                                    if(us_estad.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                                        us.setEstado("1");
                                    }else{
                                        us.setEstado("0");
                                    }
                                    int result = 0;
                                    try {
                                        result = new ControlDB_Usuario(tipoConexion).actualizarSinPassword(us, user); //Debemos registrar sin contraseña
                                    } catch (FileNotFoundException ex) {
                                        Logger.getLogger(Usuario_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    if(result==1){
                                        JOptionPane.showMessageDialog(null, "Actualización registrada exitosamente");
                                        us_cdgo.setText("");
                                        us_clave.setText("");
                                        us_nombres.setText("");
                                        us_apellidos.setText("");
                                        us_perfil_usuario_cdgo.setSelectedIndex(0);
                                        us_correo.setText("");
                                        us_estad.setSelectedIndex(0);
                                        alerta_us_clave.setText("");
                                        alerta_us_nombres.setText("");
                                        alerta_us_apellidos.setText("");
                                        try {
                                                tabla_Listar("");
                                        } catch (SQLException ex) {
                                            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los usuarios");
                                            Logger.getLogger(Usuario_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Error Al Registrar el Usuario");
                                    }    
                                }                               
                            } else {
                                alerta_us_correo.setText("El email ingresado es inválido.");
                            }
                        }else{//El usuario dejo el correo vacio por tal motivo también se realiza el registro
                            if(restablecerContrasena.isSelected()){
                                if(us_clave.getText().equals("")){
                                    alerta_us_clave.setText("Error!!.. La contraseña no puede estar vacia");
                                }else{
                                    Usuario us = new Usuario();
                                    us.setCodigo(us_cdgo.getText());
                                    us.setClave(us_clave.getText());
                                    us.setNombres(us_nombres.getText());
                                    us.setApellidos(us_apellidos.getText());
                                    String[]datosPerfil=us_perfil_usuario_cdgo.getSelectedItem().toString().split(":");
                                    us.setPerfilUsuario(new Perfil(datosPerfil[0],datosPerfil[1]));
                                    us.setCorreo(us_correo.getText());

                                    //Validamos si selecciono activo o inactivo
                                    if(us_estad.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                                        us.setEstado("1");
                                    }else{
                                        us.setEstado("0");
                                    }
                                    int result = 0;
                                    try {
                                        result = new ControlDB_Usuario(tipoConexion).actualizarConPassword(us, user);
                                    } catch (FileNotFoundException ex) {
                                        Logger.getLogger(Usuario_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    if(result==1){
                                        JOptionPane.showMessageDialog(null, "Actualización registrada exitosamente");
                                        us_cdgo.setText("");
                                        us_clave.setText("");
                                        us_nombres.setText("");
                                        us_apellidos.setText("");
                                        us_perfil_usuario_cdgo.setSelectedIndex(0);
                                        us_correo.setText("");
                                        us_estad.setSelectedIndex(0);
                                        alerta_us_clave.setText("");
                                        alerta_us_nombres.setText("");
                                        alerta_us_apellidos.setText("");
                                        try {
                                                tabla_Listar("");
                                        } catch (SQLException ex) {
                                            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los usuarios");
                                            Logger.getLogger(Usuario_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Error Al Registrar el Usuario");
                                    }  
                                }
                            }else{//No se va a actualiza la contraseña
                                Usuario us = new Usuario();
                                us.setCodigo(us_cdgo.getText());
                                us.setNombres(us_nombres.getText());
                                us.setApellidos(us_apellidos.getText());
                                String[]datosPerfil=us_perfil_usuario_cdgo.getSelectedItem().toString().split(":");
                                us.setPerfilUsuario(new Perfil(datosPerfil[0],datosPerfil[1]));
                                us.setCorreo(us_correo.getText());
                                //Validamos si selecciono activo o inactivo
                                if(us_estad.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                                    us.setEstado("1");
                                }else{
                                    us.setEstado("0");
                                }
                                int result = 0;
                                try {
                                    result = new ControlDB_Usuario(tipoConexion).actualizarSinPassword(us, user); //Debemos registrar sin contraseña
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(Usuario_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if(result==1){
                                    JOptionPane.showMessageDialog(null, "Actualización registrada exitosamente");
                                    us_cdgo.setText("");
                                    us_clave.setText("");
                                    us_nombres.setText("");
                                    us_apellidos.setText("");
                                    us_perfil_usuario_cdgo.setSelectedIndex(0);
                                    us_correo.setText("");
                                    us_estad.setSelectedIndex(0);
                                    alerta_us_clave.setText("");
                                    alerta_us_nombres.setText("");
                                    alerta_us_apellidos.setText("");
                                    try {
                                        tabla_Listar("");
                                    } catch (SQLException ex) {
                                        JOptionPane.showMessageDialog(null, "Error al tratar de consultar los usuarios");
                                        Logger.getLogger(Usuario_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(null, "Error Al Registrar el Usuario");
                                }    
                            }   
                        }
                    }catch(Exception e){
                       JOptionPane.showMessageDialog(null, "Dede de cargar un usuario antes de proceder a actualizar");
                    }
                }
            }   
        }
    }//GEN-LAST:event_RegistrarUsuarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JButton RegistrarUsuario;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JLabel alerta_us_apellidos;
    private javax.swing.JLabel alerta_us_clave;
    private javax.swing.JLabel alerta_us_correo;
    private javax.swing.JLabel alerta_us_nombres;
    private javax.swing.JButton consultar;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton restablecerContrasena;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField us_apellidos;
    private javax.swing.JTextField us_cdgo;
    private javax.swing.JPasswordField us_clave;
    private javax.swing.JTextField us_correo;
    private javax.swing.JComboBox<String> us_estad;
    private javax.swing.JTextField us_nombres;
    private javax.swing.JComboBox<String> us_perfil_usuario_cdgo;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException{
        ControlDB_Usuario  controlDB_Usuario = new ControlDB_Usuario(tipoConexion);        
        DefaultTableModel modelo = new DefaultTableModel(null, new String[] {"Código", "Nombre","Perfil", "Correo","Estado"});  
        listado=controlDB_Usuario.buscar(valorConsulta);
        for(int i =0; i< listado.size(); i++){
            String[]  registro = new String[5]; 
            registro[0]=""+listado.get(i).getCodigo();
            registro[1]=""+listado.get(i).getNombres()+" "+listado.get(i).getApellidos();
            registro[2]=""+listado.get(i).getPerfilUsuario().getDescripcion();            
            registro[3]=""+listado.get(i).getCorreo();
            registro[4]=""+listado.get(i).getEstado();
            modelo.addRow(registro);
        }
        tabla.setModel(modelo);
    }

}
