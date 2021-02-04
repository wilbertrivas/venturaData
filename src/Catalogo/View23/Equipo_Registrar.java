package Catalogo.View23;
  
import Catalogo.Controller.ControlDB_ClasificadorEquipo;
import Catalogo.Controller.ControlDB_Equipo;
import Catalogo.Controller.ControlDB_PertenenciaEquipo;
import Catalogo.Controller.ControlDB_ProveedorEquipo;
import Catalogo.Controller.ControlDB_TipoEquipo;
import Catalogo.Model.ClasificadorEquipo;
import Catalogo.Model.Equipo;
import Catalogo.Model.Pertenencia;
import Catalogo.Model.ProveedorEquipo;
import Catalogo.Model.TipoEquipo;
import Sistema.Model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Equipo_Registrar extends javax.swing.JPanel {
    Usuario user;
    ArrayList<TipoEquipo> listadoTipoEquipo = new ArrayList();
    ArrayList<ClasificadorEquipo> listadoClasificadorEquipo = new ArrayList();
    ArrayList<ProveedorEquipo> listadoProveedorEquipo = new ArrayList();
    ArrayList<Pertenencia> listadoPertenencia = new ArrayList();
    private String tipoConexion;
    public Equipo_Registrar(Usuario us,String tipoConexion) throws SQLException {
        initComponents();
        user=us;
        this.tipoConexion= tipoConexion;
        listadoTipoEquipo=new ControlDB_TipoEquipo(tipoConexion).buscarActivos();
        listadoClasificadorEquipo=new ControlDB_ClasificadorEquipo(tipoConexion).buscarActivos();
        listadoProveedorEquipo=new ControlDB_ProveedorEquipo(tipoConexion).buscarActivos();
        listadoPertenencia=new ControlDB_PertenenciaEquipo(tipoConexion).buscarActivos();
         
        //Cargamos en la interfaz los TiposEquipos activos
        for(TipoEquipo TipoEquipo1: listadoTipoEquipo){
            equipoTipoEquipo.addItem(TipoEquipo1.getDescripcion());
        }
        //Cargamos en la interfaz los clasificadores 1 de equipos
        for (ClasificadorEquipo ClasificadorEquipo1: listadoClasificadorEquipo) {
            equipoClasificador1.addItem(ClasificadorEquipo1.getDescripcion());
        }
        
        //Cargamos en la interfaz los clasificadores 2 de equipos 
        for (ClasificadorEquipo ClasificadorEquipo1: listadoClasificadorEquipo) {
             equipoClasificador2.addItem(ClasificadorEquipo1.getDescripcion());
         }
        
        //Cargamos en la interfaz los proveedores de equipos
        for(ProveedorEquipo ProveedorEquipo1: listadoProveedorEquipo){
            equipoProveedorEquipo.addItem(ProveedorEquipo1.getDescripcion());
        }

        //Cargamos en la interfaz las pertenencias de Equipos
        for(Pertenencia Pertenencia1: listadoPertenencia){
            equipoPertenencia.addItem(Pertenencia1.getDescripcion());
        }

        //Ocultamos los campos activos fijos
            ActivoFijo_separador1.show(false);
            L_equipoActivoFijo_codigo.show(false);
            equipoActivoFijo_codigo.show(false);
            L_equipoActivoFijo_referencia.show(false);
            equipoActivoFijo_referencia.show(false);
            L_equipoActivoFijo_descripcion.show(false);
            equipoActivoFijo_descripcion.show(false);
            ActivoFijo_separador2.show(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        jLabel4 = new javax.swing.JLabel();
        equipoMarca = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        equipoTipoEquipo = new javax.swing.JComboBox<>();
        equipoEstado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btn_registrar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        equipoProducto = new javax.swing.JTextField();
        equipoClasificador2 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        equipoObservacion = new javax.swing.JTextPane();
        jLabel12 = new javax.swing.JLabel();
        equipoCodigo = new javax.swing.JTextField();
        equipoCodigoBarra = new javax.swing.JTextField();
        equipoReferencia = new javax.swing.JTextField();
        equipoCapacidad = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        equipoModelo = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        equipoSerial = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        equipoDescripcion = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        equipoClasificador1 = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        equipoProveedorEquipo = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        equipoPertenencia = new javax.swing.JComboBox<>();
        L_equipoActivoFijo_codigo = new javax.swing.JLabel();
        equipoActivoFijo_codigo = new javax.swing.JTextField();
        L_equipoActivoFijo_referencia = new javax.swing.JLabel();
        equipoActivoFijo_referencia = new javax.swing.JTextField();
        L_equipoActivoFijo_descripcion = new javax.swing.JLabel();
        equipoActivoFijo_descripcion = new javax.swing.JTextField();
        selectActivoFijo = new javax.swing.JRadioButton();
        ActivoFijo_separador2 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        ActivoFijo_separador1 = new javax.swing.JSeparator();

        Editar.setText("Modificar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Código Barra:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 160, 30));

        equipoMarca.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(equipoMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 370, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Observación:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 100, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Estado:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 350, 120, 30));

        equipoTipoEquipo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        equipoTipoEquipo.setToolTipText("");
        add(equipoTipoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 370, 30));

        equipoEstado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        equipoEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        equipoEstado.setToolTipText("");
        add(equipoEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 350, 370, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("REGISTRO DE EQUIPOS");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 440, 30));

        btn_registrar.setBackground(new java.awt.Color(255, 255, 255));
        btn_registrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_registrar.setText("REGISTRAR");
        btn_registrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_registrarMouseExited(evt);
            }
        });
        btn_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrarActionPerformed(evt);
            }
        });
        add(btn_registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 520, 140, 30));

        btn_cancelar.setBackground(new java.awt.Color(255, 255, 255));
        btn_cancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cancelar.setText("CANCELAR");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 520, 140, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Código:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 160, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Equipo Referencia:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 150, 30));

        equipoProducto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(equipoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 370, 30));

        equipoClasificador2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        equipoClasificador2.setToolTipText("");
        add(equipoClasificador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 230, 370, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Capacidad:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 160, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Producto:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 110, 30));

        equipoObservacion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(equipoObservacion);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 370, 110));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Tipo Equipo:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 160, 30));

        equipoCodigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(equipoCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 370, 30));

        equipoCodigoBarra.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(equipoCodigoBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 370, 30));

        equipoReferencia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(equipoReferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 370, 30));

        equipoCapacidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(equipoCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 370, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Marca:");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 110, 30));

        equipoModelo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(equipoModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 70, 370, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Modelo:");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, 110, 30));

        equipoSerial.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(equipoSerial, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 110, 370, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Serial:");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, 110, 30));

        equipoDescripcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(equipoDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, 370, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Descripción:");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 150, 110, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Clasificador 1:");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 190, 110, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Clasificador 2:");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 230, 110, 30));

        equipoClasificador1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        equipoClasificador1.setToolTipText("");
        add(equipoClasificador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 190, 370, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Proveedor Equipo:");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 270, 140, 30));

        equipoProveedorEquipo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        equipoProveedorEquipo.setToolTipText("");
        add(equipoProveedorEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 270, 370, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Pertenencia:");
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 310, 140, 30));

        equipoPertenencia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        equipoPertenencia.setToolTipText("");
        add(equipoPertenencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 310, 370, 30));

        L_equipoActivoFijo_codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        L_equipoActivoFijo_codigo.setText("Código:");
        add(L_equipoActivoFijo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 430, 70, 30));

        equipoActivoFijo_codigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(equipoActivoFijo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 430, 370, 30));

        L_equipoActivoFijo_referencia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        L_equipoActivoFijo_referencia.setText("Referencia:");
        add(L_equipoActivoFijo_referencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 460, 70, 30));

        equipoActivoFijo_referencia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(equipoActivoFijo_referencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 460, 370, 30));

        L_equipoActivoFijo_descripcion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        L_equipoActivoFijo_descripcion.setText("Descripción:");
        add(L_equipoActivoFijo_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 490, 80, 30));

        equipoActivoFijo_descripcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(equipoActivoFijo_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 490, 370, 30));

        selectActivoFijo.setBackground(new java.awt.Color(255, 255, 255));
        selectActivoFijo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        selectActivoFijo.setText("Activo Fijo:");
        selectActivoFijo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                selectActivoFijoStateChanged(evt);
            }
        });
        add(selectActivoFijo, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 390, -1, 30));
        add(ActivoFijo_separador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 530, 490, 10));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 1000, 20));
        add(ActivoFijo_separador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 420, 490, 10));
    }// </editor-fold>//GEN-END:initComponents

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
       
    }//GEN-LAST:event_EditarActionPerformed

    private void btn_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrarActionPerformed
        if(equipoCodigo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"El código del equipo no puede estar vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }else{
            try{
                Integer.parseInt(equipoCodigo.getText());
                if(equipoCodigoBarra.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"El código de barra no puede estar vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    if(equipoReferencia.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null,"La Referencia del equipo no puede estar vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        if(equipoProducto.getText().isEmpty()){
                            JOptionPane.showMessageDialog(null,"El nombre del Producto no puede estar Vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            if(equipoCapacidad.getText().isEmpty()){
                                JOptionPane.showMessageDialog(null,"La capacidad del equipo no puede estar vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                if(equipoMarca.getText().isEmpty()){
                                    JOptionPane.showMessageDialog(null,"La marca del equipo no puede estar vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                                }else{    
                                    if(equipoModelo.getText().isEmpty()){
                                        JOptionPane.showMessageDialog(null,"El modelo del Equipo no puede estar vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                                    }else{ 
                                        if(equipoSerial.getText().isEmpty()){
                                            JOptionPane.showMessageDialog(null,"El serial del Equipo no puede estar vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                                        }else{     
                                            if(equipoDescripcion.getText().isEmpty()){
                                                JOptionPane.showMessageDialog(null,"La descripción del equipo no puede estar vacia", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                                            }else{                           
                                                Equipo Objeto = new Equipo();
                                                Objeto.setCodigo(equipoCodigo.getText());                                         
                                                Objeto.setTipoEquipo(listadoTipoEquipo.get(equipoTipoEquipo.getSelectedIndex()));
                                                Objeto.setCodigo_barra(equipoCodigoBarra.getText());
                                                Objeto.setReferencia(equipoReferencia.getText());
                                                Objeto.setProducto(equipoProducto.getText());
                                                Objeto.setCapacidad(equipoCapacidad.getText());
                                                Objeto.setMarca(equipoMarca.getText());
                                                Objeto.setModelo(equipoModelo.getText());
                                                Objeto.setSerial(equipoSerial.getText());
                                                Objeto.setDescripcion(equipoDescripcion.getText());
                                                Objeto.setClasificador1(listadoClasificadorEquipo.get(equipoClasificador1.getSelectedIndex()));
                                                Objeto.setClasificador2(listadoClasificadorEquipo.get(equipoClasificador2.getSelectedIndex()));
                                                Objeto.setProveedorEquipo(listadoProveedorEquipo.get(equipoProveedorEquipo.getSelectedIndex()));
                                                Objeto.setPertenenciaEquipo(listadoPertenencia.get(equipoPertenencia.getSelectedIndex()));
                                                Objeto.setObservacion(equipoObservacion.getText());
                                                if(equipoEstado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                                                    Objeto.setEstado("1");
                                                }else{
                                                    Objeto.setEstado("0");
                                                }
                                                boolean validador=true;
                                                if(selectActivoFijo.isSelected()){
                                                    try{
                                                        Integer.parseInt(equipoActivoFijo_codigo.getText());
                                                        Objeto.setActivoFijo_codigo(equipoActivoFijo_codigo.getText());
                                                        Objeto.setActivoFijo_referencia(equipoActivoFijo_referencia.getText());
                                                        Objeto.setActivoFijo_descripcion(equipoActivoFijo_descripcion.getText());
                                                    }catch(Exception e){
                                                        JOptionPane.showMessageDialog(null, "El código de activo fijo debe ser númerico", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                                                        validador=false;
                                                    }
                                                }else{
                                                    Objeto.setActivoFijo_codigo(null);
                                                    Objeto.setActivoFijo_referencia(null);
                                                    Objeto.setActivoFijo_descripcion(null);
                                                }
                                                if(validador){
                                                    if(new ControlDB_Equipo(tipoConexion).validarExistenciaPorCodigo(Objeto)){
                                                        JOptionPane.showMessageDialog(null, "El equipo ya se encuentra registrado en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
                                                    }else{
                                                        int respuesta=new ControlDB_Equipo(tipoConexion).registrar(Objeto, user);
                                                        if(respuesta==1){
                                                            JOptionPane.showMessageDialog(null, "Se registro el equipo de manera exitosa");
                                                            equipoCodigo.setText("");
                                                            equipoCodigoBarra.setText("");
                                                            equipoReferencia.setText("");
                                                            equipoProducto.setText("");
                                                            equipoCapacidad.setText("");
                                                            equipoMarca.setText("");
                                                            equipoModelo.setText("");
                                                            equipoSerial.setText("");
                                                            equipoDescripcion.setText("");
                                                            equipoObservacion.setText("");
                                                            equipoActivoFijo_codigo.setText("");
                                                            equipoActivoFijo_referencia.setText("");
                                                            equipoActivoFijo_descripcion.setText("");

                                                            equipoTipoEquipo.setSelectedIndex(0);
                                                            equipoClasificador1.setSelectedIndex(0);
                                                            equipoClasificador2.setSelectedIndex(0);
                                                            equipoProveedorEquipo.setSelectedIndex(0);
                                                            equipoPertenencia.setSelectedIndex(0);
                                                            equipoEstado.setSelectedIndex(0);
                                                            //tabla_Listar("");
                                                        }else{
                                                            if(respuesta==0){
                                                                JOptionPane.showMessageDialog(null, "No se pudo registrar el equipo, valide datos", "Advertencia", JOptionPane.ERROR_MESSAGE);
                                                            }
                                                        }
                                                    }
                                                }                             
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"El código del equipo debe ser númerico", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_registrarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        equipoCodigo.setText("");
        equipoCodigoBarra.setText("");
        equipoReferencia.setText("");
        equipoProducto.setText("");
        equipoCapacidad.setText("");
        equipoMarca.setText("");
        equipoModelo.setText("");
        equipoSerial.setText("");
        equipoDescripcion.setText("");
        equipoObservacion.setText("");
        equipoActivoFijo_codigo.setText("");
        equipoActivoFijo_referencia.setText("");
        equipoActivoFijo_descripcion.setText("");
        
        equipoTipoEquipo.setSelectedIndex(0);
        equipoClasificador1.setSelectedIndex(0);
        equipoClasificador2.setSelectedIndex(0);
        equipoProveedorEquipo.setSelectedIndex(0);
        equipoPertenencia.setSelectedIndex(0);
        equipoEstado.setSelectedIndex(0);
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_registrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_registrarMouseExited

    }//GEN-LAST:event_btn_registrarMouseExited

    private void selectActivoFijoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_selectActivoFijoStateChanged
        if(selectActivoFijo.isSelected()){
            ActivoFijo_separador1.show(true);
            L_equipoActivoFijo_codigo.show(true);
            equipoActivoFijo_codigo.show(true);
            L_equipoActivoFijo_referencia.show(true);
            equipoActivoFijo_referencia.show(true);
            L_equipoActivoFijo_descripcion.show(true);
            equipoActivoFijo_descripcion.show(true);
            ActivoFijo_separador2.show(true);
        }else{
            ActivoFijo_separador1.show(false);
            L_equipoActivoFijo_codigo.show(false);
            equipoActivoFijo_codigo.show(false);
            L_equipoActivoFijo_referencia.show(false);
            equipoActivoFijo_referencia.show(false);
            L_equipoActivoFijo_descripcion.show(false);
            equipoActivoFijo_descripcion.show(false);
            ActivoFijo_separador2.show(false);
        }
    }//GEN-LAST:event_selectActivoFijoStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator ActivoFijo_separador1;
    private javax.swing.JSeparator ActivoFijo_separador2;
    private javax.swing.JMenuItem Editar;
    private javax.swing.JLabel L_equipoActivoFijo_codigo;
    private javax.swing.JLabel L_equipoActivoFijo_descripcion;
    private javax.swing.JLabel L_equipoActivoFijo_referencia;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_registrar;
    private javax.swing.JTextField equipoActivoFijo_codigo;
    private javax.swing.JTextField equipoActivoFijo_descripcion;
    private javax.swing.JTextField equipoActivoFijo_referencia;
    private javax.swing.JTextField equipoCapacidad;
    private javax.swing.JComboBox<String> equipoClasificador1;
    private javax.swing.JComboBox<String> equipoClasificador2;
    private javax.swing.JTextField equipoCodigo;
    private javax.swing.JTextField equipoCodigoBarra;
    private javax.swing.JTextField equipoDescripcion;
    private javax.swing.JComboBox<String> equipoEstado;
    private javax.swing.JTextField equipoMarca;
    private javax.swing.JTextField equipoModelo;
    private javax.swing.JTextPane equipoObservacion;
    private javax.swing.JComboBox<String> equipoPertenencia;
    private javax.swing.JTextField equipoProducto;
    private javax.swing.JComboBox<String> equipoProveedorEquipo;
    private javax.swing.JTextField equipoReferencia;
    private javax.swing.JTextField equipoSerial;
    private javax.swing.JComboBox<String> equipoTipoEquipo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JRadioButton selectActivoFijo;
    // End of variables declaration//GEN-END:variables
    
}
