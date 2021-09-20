package Sistema.View;

import Sistema.Controller.ControlDB_Usuario;
import Sistema.Model.Perfil;
import Sistema.Model.Usuario;
import java.awt.Color;
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
    ArrayList<Usuario> listado=null;
    private String tipoConexion;
    public Usuario_Actualizar(Usuario u,String tipoConexion) { 
        initComponents();
        InternaFrame_BuscarUsuario.getContentPane().setBackground(Color.WHITE);
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
        us_cdgo.setEnabled(false);
        restablecerContrasena.setSelected(false);
        InternaFrame_BuscarUsuario.show(false);
        
        
        textIngreseContrasena.show(false);
        us_clave1.show(false);
        textRepitaContrasena.show(false);
        us_clave2.show(false);
        viewPassword23.show(false);
        
        
        us_nombres.setEnabled(false);
        us_apellidos.setEnabled(false);
        us_perfil_usuario_cdgo.setEnabled(false);
        us_correo.setEnabled(false);
        us_estad.setEnabled(false);
        restablecerContrasena.setEnabled(false);
        cambiarContrasena.setEnabled(false);
        sesionBloqueada.setEnabled(false);
        us_clave2.setEnabled(false);
        viewPassword23.setEnabled(false);
        fechaUltimoCambio.setText("");
        cantidadIntentos.setText("");
        semaforoRojo.setIcon(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        InternaFrame_BuscarUsuario = new javax.swing.JInternalFrame();
        valorBusqueda = new javax.swing.JTextField();
        consultar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btn_registrar_insumo2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        us_cdgo = new javax.swing.JTextField();
        us_correo = new javax.swing.JTextField();
        cantidadIntentos = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        us_nombres = new javax.swing.JTextField();
        us_apellidos = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        us_clave1 = new javax.swing.JPasswordField();
        us_estad = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        us_perfil_usuario_cdgo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        restablecerContrasena = new javax.swing.JRadioButton();
        RegistrarUsuario = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        sesionBloqueada = new javax.swing.JCheckBox();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        cambiarContrasena = new javax.swing.JCheckBox();
        jLabel30 = new javax.swing.JLabel();
        fechaUltimoCambio = new javax.swing.JLabel();
        us_clave2 = new javax.swing.JPasswordField();
        textRepitaContrasena = new javax.swing.JLabel();
        textIngreseContrasena = new javax.swing.JLabel();
        semaforoRojo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        viewPassword23 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        semaforoVerde = new javax.swing.JLabel();
        semaforoAmarillo = new javax.swing.JLabel();

        Editar.setText("Modificar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InternaFrame_BuscarUsuario.setClosable(true);
        InternaFrame_BuscarUsuario.setVisible(false);
        InternaFrame_BuscarUsuario.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        InternaFrame_BuscarUsuario.getContentPane().add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 280, 40));

        consultar.setBackground(new java.awt.Color(255, 255, 255));
        consultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        consultar.setText("CONSULTAR");
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        InternaFrame_BuscarUsuario.getContentPane().add(consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 140, 40));

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

        InternaFrame_BuscarUsuario.getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 1160, 470));

        btn_registrar_insumo2.setBackground(new java.awt.Color(255, 255, 255));
        btn_registrar_insumo2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_registrar_insumo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btn_registrar_insumo2.setText("CANCELAR");
        btn_registrar_insumo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrar_insumo2ActionPerformed(evt);
            }
        });
        InternaFrame_BuscarUsuario.getContentPane().add(btn_registrar_insumo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 140, 40));

        add(InternaFrame_BuscarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1240, 610));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("ACTUALIZACIÓN DE USUARIOS");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 310, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Apellidos:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 80, 30));

        us_cdgo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                us_cdgoMouseClicked(evt);
            }
        });
        add(us_cdgo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 230, 30));

        us_correo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                us_correoMouseClicked(evt);
            }
        });
        add(us_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 230, 30));

        cantidadIntentos.setBackground(new java.awt.Color(255, 255, 255));
        cantidadIntentos.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        cantidadIntentos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(cantidadIntentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 140, 50));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Nombres:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 70, 30));

        us_nombres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                us_nombresMouseClicked(evt);
            }
        });
        add(us_nombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 190, 310, 30));

        us_apellidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                us_apellidosMouseClicked(evt);
            }
        });
        add(us_apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 230, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Cedula:");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 110, 30));

        us_clave1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                us_clave1KeyTyped(evt);
            }
        });
        add(us_clave1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, 230, 30));

        us_estad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        add(us_estad, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 270, 310, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Perfil del Usuario:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 230, 110, 30));

        add(us_perfil_usuario_cdgo, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 230, 310, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Estado:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 270, 110, 30));

        restablecerContrasena.setBackground(new java.awt.Color(255, 255, 255));
        restablecerContrasena.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        restablecerContrasena.setText("Cambiar Contraseña:");
        restablecerContrasena.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                restablecerContrasenaStateChanged(evt);
            }
        });
        add(restablecerContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 170, 30));

        RegistrarUsuario.setBackground(new java.awt.Color(255, 255, 255));
        RegistrarUsuario.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        RegistrarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/actualizar.png"))); // NOI18N
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
        add(RegistrarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 510, 190, 50));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 900, 10));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("BUSCAR USUARIO");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 110, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/buscarUsuario2.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 250, 60));

        sesionBloqueada.setBackground(new java.awt.Color(255, 255, 255));
        sesionBloqueada.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sesionBloqueada.setText("Sesión Bloqueada");
        add(sesionBloqueada, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 350, 170, 30));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("Correo:");
        add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 80, 30));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Fecha de ultimo cambio de clave:");
        add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 210, 30));

        cambiarContrasena.setBackground(new java.awt.Color(255, 255, 255));
        cambiarContrasena.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cambiarContrasena.setText("Cambiar Contraseña");
        add(cambiarContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 320, 170, 30));

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 102, 102));
        jLabel30.setText("Cant. Intentos");
        add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 140, 30));

        fechaUltimoCambio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(fechaUltimoCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 200, 30));

        us_clave2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                us_clave2KeyTyped(evt);
            }
        });
        add(us_clave2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, 230, 30));

        textRepitaContrasena.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        textRepitaContrasena.setText("Repita contraseña:");
        add(textRepitaContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, 120, 30));

        textIngreseContrasena.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        textIngreseContrasena.setText("Ingrese contraseña:");
        add(textIngreseContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 120, 30));
        add(semaforoRojo, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, 90, 80));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 900, 20));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 10, 330));

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 140, 10, 330));

        viewPassword23.setBackground(new java.awt.Color(255, 255, 255));
        viewPassword23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        viewPassword23.setText("Mostrar Contraseña");
        viewPassword23.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                viewPassword23ItemStateChanged(evt);
            }
        });
        add(viewPassword23, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, -1, 30));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        jButton1.setText("CANCELAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 510, 140, 50));
        add(semaforoVerde, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, 90, 80));
        add(semaforoAmarillo, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 60, 90, 80));
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
                if(listado != null){
                    user= null;
                    user=listado.get(fila1);
                    user=new ControlDB_Usuario(tipoConexion).buscarUsuarioEspecifico(user.getCodigo());
                    us_cdgo.setText(user.getCodigo());
                    us_nombres.setText(user.getNombres());
                    us_apellidos.setText(user.getApellidos());
                    us_perfil_usuario_cdgo.setSelectedItem((String)(user.getPerfilUsuario().getCodigo()+":"+user.getPerfilUsuario().getDescripcion()));
                    
                    if(user.getEstado().equalsIgnoreCase("ACTIVO")){
                        us_estad.setSelectedIndex(0);
                    }else{
                        us_estad.setSelectedIndex(1);
                    }
                    us_correo.setText(user.getCorreo());
                    InternaFrame_BuscarUsuario.show(false);

                    if(user.getSesionBloqueada().equals("1")){//El usuario tiene la sesión bloqueada
                        sesionBloqueada.setSelected(true);
                    }else{//El usuario no tiene la sesión bloqueada
                        sesionBloqueada.setSelected(false);
                    }  
                    cantidadIntentos.setText(""+user.getCantidadIntento());  

                    if(user.getCambiarClave().equals("1")){//El usuario tiene activo la opción de cambiar contraseña
                        cambiarContrasena.setSelected(true);
                    }else{//El usuario no tiene activa la opción de cammbiar contraseña
                        cambiarContrasena.setSelected(false);
                    }
                    fechaUltimoCambio.setText(""+user.getFechaCambioClave());
                    if(user.getCantidadIntento() <= 2){
                        cantidadIntentos.setForeground(Color.GREEN);
                        semaforoVerde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/semaforo_verde_f.png")));
                        semaforoAmarillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/semaforo.png")));
                        semaforoRojo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/semaforo.png")));
                    }else{
                        if(user.getCantidadIntento() <= 4){
                            cantidadIntentos.setForeground(Color.YELLOW);
                            semaforoVerde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/semaforo.png")));
                            semaforoAmarillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/semaforo_amarillo_f.png")));
                            semaforoRojo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/semaforo.png")));
                        }else{
                            cantidadIntentos.setForeground(Color.RED);
                            semaforoVerde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/semaforo.png")));
                            semaforoAmarillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/semaforo.png")));
                            semaforoRojo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/semaforo_rojo_f.png")));
                        }
                    }
                    us_nombres.setEnabled(true);
                    us_apellidos.setEnabled(true);
                    us_perfil_usuario_cdgo.setEnabled(true);
                    us_correo.setEnabled(true);
                    us_estad.setEnabled(true);
                    restablecerContrasena.setEnabled(true);
                    cambiarContrasena.setEnabled(true);
                    sesionBloqueada.setEnabled(true);
                    us_clave2.setEnabled(true);
                    viewPassword23.setEnabled(true);
                }            
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() == 2) {
            int fila1;
            try{
                fila1=tabla.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    if(listado != null){
                        user= null;
                        user=listado.get(fila1);
                        user=new ControlDB_Usuario(tipoConexion).buscarUsuarioEspecifico(user.getCodigo());
                        us_cdgo.setText(user.getCodigo());
                        us_nombres.setText(user.getNombres());
                        us_apellidos.setText(user.getApellidos());
                        us_perfil_usuario_cdgo.setSelectedItem((String)(user.getPerfilUsuario().getCodigo()+":"+user.getPerfilUsuario().getDescripcion()));
                        if(user.getEstado().equalsIgnoreCase("ACTIVO")){
                            us_estad.setSelectedIndex(0);
                        }else{
                            us_estad.setSelectedIndex(1);
                        }
                        us_correo.setText(user.getCorreo());
                        InternaFrame_BuscarUsuario.show(false);

                        if(user.getSesionBloqueada().equals("1")){//El usuario tiene la sesión bloqueada
                            sesionBloqueada.setSelected(true);
                        }else{//El usuario no tiene la sesión bloqueada
                            sesionBloqueada.setSelected(false);
                        }  
                        cantidadIntentos.setText(""+user.getCantidadIntento());  

                        if(user.getCambiarClave().equals("1")){//El usuario tiene activo la opción de cambiar contraseña
                            cambiarContrasena.setSelected(true);
                        }else{//El usuario no tiene activa la opción de cammbiar contraseña
                            cambiarContrasena.setSelected(false);
                        }
                        fechaUltimoCambio.setText(""+user.getFechaCambioClave());
                        if(user.getCantidadIntento() <= 2){
                            cantidadIntentos.setForeground(Color.GREEN);
                            semaforoVerde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/semaforo_verde_f.png")));
                            semaforoAmarillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/semaforo.png")));
                            semaforoRojo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/semaforo.png")));
                        }else{
                            if(user.getCantidadIntento() <= 4){
                                cantidadIntentos.setForeground(Color.YELLOW);
                                semaforoVerde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/semaforo.png")));
                                semaforoAmarillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/semaforo_amarillo_f.png")));
                                semaforoRojo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/semaforo.png")));
                            }else{
                                cantidadIntentos.setForeground(Color.RED);
                                semaforoVerde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/semaforo.png")));
                                semaforoAmarillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/semaforo.png")));
                                semaforoRojo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/semaforo/semaforo_rojo_f.png")));
                            }
                        }
                        us_nombres.setEnabled(true);
                        us_apellidos.setEnabled(true);
                        us_perfil_usuario_cdgo.setEnabled(true);
                        us_correo.setEnabled(true);
                        us_estad.setEnabled(true);
                        restablecerContrasena.setEnabled(true);
                        cambiarContrasena.setEnabled(true);
                        sesionBloqueada.setEnabled(true);
                        us_clave2.setEnabled(true);
                        viewPassword23.setEnabled(true);
                    }            
                }
            }catch(Exception e){
                e.printStackTrace();
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

    private void us_clave1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_us_clave1KeyTyped
        if (us_clave1.getText().length()== 15){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "La longitud total de la contraseña es 15 Caracteres", "Error!!", JOptionPane.ERROR_MESSAGE);
        }else{
            //alerta_us_clave.setText("");
        }
    }//GEN-LAST:event_us_clave1KeyTyped

    private void restablecerContrasenaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_restablecerContrasenaStateChanged
        if(restablecerContrasena.isSelected()){
            textIngreseContrasena.show(true);
            textRepitaContrasena.show(true);
            us_clave1.show(true);
            us_clave2.show(true);
            viewPassword23.show(true);   
        }else{
            textIngreseContrasena.show(false);
            textRepitaContrasena.show(false);
            us_clave1.show(false);
            us_clave2.show(false);
            viewPassword23.show(false);
        }
    }//GEN-LAST:event_restablecerContrasenaStateChanged

    private void RegistrarUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegistrarUsuarioMouseExited

    }//GEN-LAST:event_RegistrarUsuarioMouseExited

    private void RegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarUsuarioActionPerformed
        if(us_cdgo.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Error!!.. Debe cargar un usuario para realizar la actualización", "Error!!", JOptionPane.ERROR_MESSAGE);
        }else{
            if(us_nombres.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Error!!.. El nombre del usuario no puede estar vacio", "Error!!", JOptionPane.ERROR_MESSAGE);
            }else{
                if(us_apellidos.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Error!!.. Los apellidos no pueden estar vacio", "Error!!", JOptionPane.ERROR_MESSAGE);
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
                                    if(us_clave1.getText().equals("")|| us_clave2.getText().equals("")){
                                        JOptionPane.showMessageDialog(null, "Ninguno de los dos campos de contraseñas pueden estar vacio", "Error!!", JOptionPane.ERROR_MESSAGE);
                                    }else{
                                        if(!(us_clave1.getText().equals(us_clave2.getText()))){//Las dos contraseñas son diferentes
                                            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden verifique datos", "Error!!", JOptionPane.ERROR_MESSAGE);
                                        }else{
                                            String claveCifrada= new EncriptarPassword().md5(us_clave1.getText());
                                            if(claveCifrada.equals(user.getClave()) || claveCifrada.equals(user.getClaveAnterior1()) || claveCifrada.equals(user.getClaveAnterior2())){
                                                 JOptionPane.showMessageDialog(null, "La contraseña digitada ya fue registrada anteriormente debe cambiarla", "Error!!", JOptionPane.ERROR_MESSAGE);
                                            }else{
                                                if(new ControlDB_Usuario(tipoConexion).validarComplejidadContraseña(us_clave1.getText())){
                                                    Usuario us = new Usuario();
                                                    us.setCodigo(us_cdgo.getText());
                                                    us.setClave(us_clave1.getText());
                                                    us.setClaveAnterior1(user.getClave());
                                                    us.setClaveAnterior2(user.getClaveAnterior1());
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
                                                    if(cambiarContrasena.isSelected()){
                                                        us.setCambiarClave("1");
                                                    }else{
                                                        us.setCambiarClave("0");
                                                    }
                                                    us.setCantidadIntento(0);
                                                    if(sesionBloqueada.isSelected()){
                                                        us.setSesionBloqueada("1");
                                                    }else{
                                                        us.setSesionBloqueada("0");
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
                                                        us_clave1.setText("");
                                                        us_nombres.setText("");
                                                        us_apellidos.setText("");
                                                        us_perfil_usuario_cdgo.setSelectedIndex(0);
                                                        us_correo.setText("");
                                                        us_estad.setSelectedIndex(0);
                                                        
                                                        us_clave1.setText("");
                                                        us_clave2.setText("");
                                                        restablecerContrasena.setSelected(false);
                                                        cambiarContrasena.setSelected(false);
                                                        sesionBloqueada.setSelected(false);
                                                        viewPassword23.setSelected(false);
                                                        
                                                        
                                                        us_nombres.setEnabled(false);
                                                        us_apellidos.setEnabled(false);
                                                        us_perfil_usuario_cdgo.setEnabled(false);
                                                        us_correo.setEnabled(false);
                                                        us_estad.setEnabled(false);
                                                        restablecerContrasena.setEnabled(false);
                                                        cambiarContrasena.setEnabled(false);
                                                        sesionBloqueada.setEnabled(false);
                                                        us_clave2.setEnabled(false);
                                                        viewPassword23.setEnabled(false);
                                                        
                                                        fechaUltimoCambio.setText("");
                                                        cantidadIntentos.setText("");
                                                        semaforoRojo.setIcon(null);
                                                        try {
                                                            tabla_Listar("");
                                                        } catch (SQLException ex) {
                                                            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los usuarios", "Error!!", JOptionPane.ERROR_MESSAGE);
                                                            Logger.getLogger(Usuario_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                                                        }
                                                    }else{
                                                        JOptionPane.showMessageDialog(null, "Error Al Registrar el Usuario", "Error!!", JOptionPane.ERROR_MESSAGE);
                                                    } 
                                                }
                                            }
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
                                    if(cambiarContrasena.isSelected()){
                                        us.setCambiarClave("1");
                                    }else{
                                        us.setCambiarClave("0");
                                    }
                                    
                                    if(sesionBloqueada.isSelected()){
                                        us.setSesionBloqueada("1");
                                        us.setCantidadIntento(user.getCantidadIntento());
                                    }else{
                                        us.setSesionBloqueada("0");
                                        if(user.getSesionBloqueada().equals("1")){
                                            us.setCantidadIntento(0);
                                        }else{
                                            us.setCantidadIntento(user.getCantidadIntento());
                                        }
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
                                        us_clave1.setText("");
                                        us_nombres.setText("");
                                        us_apellidos.setText("");
                                        us_perfil_usuario_cdgo.setSelectedIndex(0);
                                        us_correo.setText("");
                                        us_estad.setSelectedIndex(0);
                                        us_clave1.setText("");
                                        us_clave2.setText("");
                                        restablecerContrasena.setSelected(false);
                                        cambiarContrasena.setSelected(false);
                                        sesionBloqueada.setSelected(false);
                                        viewPassword23.setSelected(false);
                                        
                                        us_nombres.setEnabled(false);
                                        us_apellidos.setEnabled(false);
                                        us_perfil_usuario_cdgo.setEnabled(false);
                                        us_correo.setEnabled(false);
                                        us_estad.setEnabled(false);
                                        restablecerContrasena.setEnabled(false);
                                        cambiarContrasena.setEnabled(false);
                                        sesionBloqueada.setEnabled(false);
                                        us_clave2.setEnabled(false);
                                        viewPassword23.setEnabled(false);
                                        
                                        fechaUltimoCambio.setText("");
                                        cantidadIntentos.setText("");
                                        semaforoRojo.setIcon(null);
                                        try {
                                                tabla_Listar("");
                                        } catch (SQLException ex) {
                                            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los usuarios", "Error!!", JOptionPane.ERROR_MESSAGE);
                                            Logger.getLogger(Usuario_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Error Al Registrar el Usuario", "Error!!", JOptionPane.ERROR_MESSAGE);
                                    }    
                                }                               
                            } else {
                                JOptionPane.showMessageDialog(null, "El email ingresado es inválido.", "Error!!", JOptionPane.ERROR_MESSAGE);
                            }
                        }else{//El usuario dejo el correo vacio por tal motivo también se realiza el registro
                            if(restablecerContrasena.isSelected()){
                                if(us_clave1.getText().equals("")|| us_clave2.getText().equals("")){
                                    JOptionPane.showMessageDialog(null, "Ninguno de los dos campos de contraseñas pueden estar vacio", "Error!!", JOptionPane.ERROR_MESSAGE);
                                }else{
                                    if(!(us_clave1.getText().equals(us_clave2.getText()))){//Las dos contraseñas son diferentes
                                        JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden verifique datos", "Error!!", JOptionPane.ERROR_MESSAGE);
                                    }else{
                                        String claveCifrada= new EncriptarPassword().md5(us_clave1.getText());
                                        if(claveCifrada.equals(user.getClave()) || claveCifrada.equals(user.getClaveAnterior1()) || claveCifrada.equals(user.getClaveAnterior2())){
                                             JOptionPane.showMessageDialog(null, "La contraseña digitada ya fue registrada anteriormente debe cambiarla", "Error!!", JOptionPane.ERROR_MESSAGE);
                                        }else{
                                            if(new ControlDB_Usuario(tipoConexion).validarComplejidadContraseña(us_clave1.getText())){
                                                Usuario us = new Usuario();
                                                us.setCodigo(us_cdgo.getText());
                                                us.setClave(us_clave1.getText());
                                                us.setClaveAnterior1(user.getClave());
                                                us.setClaveAnterior2(user.getClaveAnterior1());
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
                                                if(cambiarContrasena.isSelected()){
                                                    us.setCambiarClave("1");
                                                }else{
                                                    us.setCambiarClave("0");
                                                }
                                                us.setCantidadIntento(0);
                                                if(sesionBloqueada.isSelected()){
                                                    us.setSesionBloqueada("1");
                                                }else{
                                                    us.setSesionBloqueada("0");
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
                                                    us_clave1.setText("");
                                                    us_nombres.setText("");
                                                    us_apellidos.setText("");
                                                    us_perfil_usuario_cdgo.setSelectedIndex(0);
                                                    us_correo.setText("");
                                                    us_estad.setSelectedIndex(0);

                                                    us_clave1.setText("");
                                                    us_clave2.setText("");
                                                    restablecerContrasena.setSelected(false);
                                                    cambiarContrasena.setSelected(false);
                                                    sesionBloqueada.setSelected(false);
                                                    viewPassword23.setSelected(false);
                                                    
                                                    us_nombres.setEnabled(false);
                                                    us_apellidos.setEnabled(false);
                                                    us_perfil_usuario_cdgo.setEnabled(false);
                                                    us_correo.setEnabled(false);
                                                    us_estad.setEnabled(false);
                                                    restablecerContrasena.setEnabled(false);
                                                    cambiarContrasena.setEnabled(false);
                                                    sesionBloqueada.setEnabled(false);
                                                    us_clave2.setEnabled(false);
                                                    viewPassword23.setEnabled(false);
                                                    
                                                    fechaUltimoCambio.setText("");
                                                    cantidadIntentos.setText("");
                                                    semaforoRojo.setIcon(null);
                                                    try {
                                                        tabla_Listar("");
                                                    } catch (SQLException ex) {
                                                        JOptionPane.showMessageDialog(null, "Error al tratar de consultar los usuarios", "Error!!", JOptionPane.ERROR_MESSAGE);
                                                        Logger.getLogger(Usuario_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                }else{
                                                    JOptionPane.showMessageDialog(null, "Error Al Registrar el Usuario", "Error!!", JOptionPane.ERROR_MESSAGE);
                                                } 
                                            }
                                        }
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
                                if(cambiarContrasena.isSelected()){
                                    us.setCambiarClave("1");
                                }else{
                                    us.setCambiarClave("0");
                                }

                                if(sesionBloqueada.isSelected()){
                                    us.setSesionBloqueada("1");
                                    us.setCantidadIntento(user.getCantidadIntento());
                                }else{
                                    us.setSesionBloqueada("0");
                                    if(user.getSesionBloqueada().equals("1")){
                                        us.setCantidadIntento(0);
                                    }else{
                                        us.setCantidadIntento(user.getCantidadIntento());
                                    }
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
                                    us_clave1.setText("");
                                    us_nombres.setText("");
                                    us_apellidos.setText("");
                                    us_perfil_usuario_cdgo.setSelectedIndex(0);
                                    us_correo.setText("");
                                    us_estad.setSelectedIndex(0);
                                    us_clave1.setText("");
                                    us_clave2.setText("");
                                    restablecerContrasena.setSelected(false);
                                    cambiarContrasena.setSelected(false);
                                    sesionBloqueada.setSelected(false);
                                    viewPassword23.setSelected(false);
                                    
                                    us_nombres.setEnabled(false);
                                    us_apellidos.setEnabled(false);
                                    us_perfil_usuario_cdgo.setEnabled(false);
                                    us_correo.setEnabled(false);
                                    us_estad.setEnabled(false);
                                    restablecerContrasena.setEnabled(false);
                                    cambiarContrasena.setEnabled(false);
                                    sesionBloqueada.setEnabled(false);
                                    us_clave2.setEnabled(false);
                                    viewPassword23.setEnabled(false);
                                    
                                    fechaUltimoCambio.setText("");
                                    cantidadIntentos.setText("");
                                    semaforoRojo.setIcon(null);
                                    try {
                                        tabla_Listar("");
                                    } catch (SQLException ex) {
                                        JOptionPane.showMessageDialog(null, "Error al tratar de consultar los usuarios", "Error!!", JOptionPane.ERROR_MESSAGE);
                                        Logger.getLogger(Usuario_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(null, "Error Al Registrar el Usuario", "Error!!", JOptionPane.ERROR_MESSAGE);
                                }  
                            }
                        }
                    }catch(Exception e){
                       JOptionPane.showMessageDialog(null, "Dede de cargar un usuario antes de proceder a actualizar", "Error!!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }   
        }
    }//GEN-LAST:event_RegistrarUsuarioActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        InternaFrame_BuscarUsuario.show(true);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void btn_registrar_insumo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrar_insumo2ActionPerformed
        valorBusqueda.setText("");
        InternaFrame_BuscarUsuario.show(false);
    }//GEN-LAST:event_btn_registrar_insumo2ActionPerformed

    private void us_clave2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_us_clave2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_us_clave2KeyTyped

    private void viewPassword23ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_viewPassword23ItemStateChanged
        if(viewPassword23.isSelected()){
            us_clave1.setEchoChar((char)0); // este método es el que hace visible el texto del jPasswordField
            us_clave2.setEchoChar((char)0); // este método es el que hace visible el texto del jPasswordField
        } else {
            us_clave1.setEchoChar('*'); // i es el char
            us_clave2.setEchoChar('*'); // i es el char
        }
    }//GEN-LAST:event_viewPassword23ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        fechaUltimoCambio.setText("");
        cantidadIntentos.setText("");
        semaforoRojo.setIcon(null);
        
        us_cdgo.setText("");
        us_clave1.setText("");
        us_nombres.setText("");
        us_apellidos.setText("");
        us_perfil_usuario_cdgo.setSelectedIndex(0);
        us_correo.setText("");
        us_estad.setSelectedIndex(0);
        us_clave1.setText("");
        us_clave2.setText("");
        restablecerContrasena.setSelected(false);
        cambiarContrasena.setSelected(false);
        sesionBloqueada.setSelected(false);
        viewPassword23.setSelected(false);
        
        us_nombres.setEnabled(false);
        us_apellidos.setEnabled(false);
        us_perfil_usuario_cdgo.setEnabled(false);
        us_correo.setEnabled(false);
        us_estad.setEnabled(false);
        restablecerContrasena.setEnabled(false);
        cambiarContrasena.setEnabled(false);
        sesionBloqueada.setEnabled(false);
        us_clave2.setEnabled(false);
        viewPassword23.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JInternalFrame InternaFrame_BuscarUsuario;
    private javax.swing.JButton RegistrarUsuario;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JButton btn_registrar_insumo2;
    private javax.swing.JCheckBox cambiarContrasena;
    private javax.swing.JLabel cantidadIntentos;
    private javax.swing.JButton consultar;
    private javax.swing.JLabel fechaUltimoCambio;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JRadioButton restablecerContrasena;
    private javax.swing.JLabel semaforoAmarillo;
    private javax.swing.JLabel semaforoRojo;
    private javax.swing.JLabel semaforoVerde;
    private javax.swing.JCheckBox sesionBloqueada;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel textIngreseContrasena;
    private javax.swing.JLabel textRepitaContrasena;
    private javax.swing.JTextField us_apellidos;
    private javax.swing.JTextField us_cdgo;
    private javax.swing.JPasswordField us_clave1;
    private javax.swing.JPasswordField us_clave2;
    private javax.swing.JTextField us_correo;
    private javax.swing.JComboBox<String> us_estad;
    private javax.swing.JTextField us_nombres;
    private javax.swing.JComboBox<String> us_perfil_usuario_cdgo;
    private javax.swing.JTextField valorBusqueda;
    private javax.swing.JCheckBox viewPassword23;
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
