package Catalogo.View1;

import Catalogo.Controller.ControlDB_BaseDatos;
import Catalogo.View1.CalcularDigitoVerificacion;
import Catalogo.Controller.ControlDB_Transportadora;
import Catalogo.Model.BaseDatos;
import Catalogo.Model.Transportadora;
import ModuloEquipo.View2.Solicitud_Equipos_Registrar;
import Sistema.Model.Usuario;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
 
public final class Transportadora_Actualizar extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;
    ArrayList<BaseDatos> listadoBaseDatos= new ArrayList();
    ArrayList<Transportadora> listadoTransportadora= null;
    public Transportadora_Actualizar(Usuario us,String tipoConexion) {
        user=us;
        initComponents();
        this.tipoConexion= tipoConexion;
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las transportadoras");
            Logger.getLogger(Transportadora_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Cargamos en la interfaz la lista de las base de datos
        try {
            listadoBaseDatos=new ControlDB_BaseDatos(tipoConexion).buscar();
            if(listadoBaseDatos != null){
                String datosObjeto[]= new String[listadoBaseDatos.size()];
                int contador=0;
                for(BaseDatos objeto : listadoBaseDatos){ 
                    datosObjeto[contador]=objeto.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                listado_baseDatos.setModel(model);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        codigo.setEnabled(false);
        nit.setEnabled(false);
        digitoVerificacion.setEnabled(false);
        nombre.setEnabled(false);
        observacion.setEnabled(false);
        estado.setEnabled(false);
        listado_baseDatos.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        jLabel2 = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        valorBusqueda = new javax.swing.JTextField();
        btnConsultar = new javax.swing.JButton();
        nombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox<>();
        alerta_nombre = new javax.swing.JLabel();
        nit = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        digitoVerificacion = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        alerta_nit = new javax.swing.JLabel();
        codigo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        observacion = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        listado_baseDatos = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();

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
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("ACTUALIZACIÓN DE TRANSPORTADORA");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 550, 30));

        btnActualizar.setBackground(new java.awt.Color(255, 255, 255));
        btnActualizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/actualizar.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnActualizarMouseExited(evt);
            }
        });
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 490, 140, 40));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 490, 140, 40));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 760, 470));
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 410, 40));

        btnConsultar.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        btnConsultar.setText("CONSULTAR");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 50, 340, 40));
        add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 390, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Nit:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 110, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Observación:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 100, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadoActionPerformed(evt);
            }
        });
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 420, 390, 30));

        alerta_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_nombre.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 460, 20));

        nit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nitMouseClicked(evt);
            }
        });
        nit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nitKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nitKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nitKeyTyped(evt);
            }
        });
        add(nit, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 120, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("-");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 10, 30));

        digitoVerificacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(digitoVerificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 60, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Código:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 110, 30));

        alerta_nit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_nit.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_nit, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 460, 20));
        add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 390, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Nombre:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 110, 30));

        observacion.setColumns(20);
        observacion.setRows(5);
        jScrollPane2.setViewportView(observacion);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 390, 110));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Estado:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 120, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Base de Datos:");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, 30));

        listado_baseDatos.setToolTipText("");
        add(listado_baseDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 390, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 530, 470));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 42, 1290, 10));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1290, 90));
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        codigo.setText("");
        nit.setText("");
        digitoVerificacion.setText("");
        nombre.setText("");
        observacion.setText("");
        listado_baseDatos.setSelectedIndex(0);
        estado.setSelectedIndex(0);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if(codigo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Error.. Se debe carga una transportadora", "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
        else{
            try{
                new java.math.BigDecimal(nit.getText());
                if(nombre.getText().equals("")){
                    alerta_nombre.setText("El nombre de la transportador no puede estar vacio");
                }else{
                    Transportadora Objeto = new Transportadora();
                    Objeto.setCodigo(codigo.getText());
                    if(!digitoVerificacion.getText().equals("")){
                        Objeto.setNit(nit.getText()+"-"+digitoVerificacion.getText());
                    }else{
                        Objeto.setNit(nit.getText());
                    }
                    Objeto.setDescripcion(nombre.getText());
                    Objeto.setObservacion(observacion.getText());
                    //Validamos si selecciono activo o inactivo
                    if(listadoBaseDatos !=null){
                        Objeto.setBaseDatos(listadoBaseDatos.get(listado_baseDatos.getSelectedIndex()));
                    }else{
                        Objeto.setBaseDatos(new BaseDatos("NULL"));
                    }
                    if(estado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                        Objeto.setEstado("1");
                    }else{
                        Objeto.setEstado("0");
                    }
                    try {
                        //if(!new ControlDB_Transportadora(tipoConexion).validarExistenciaActualizar(Objeto)){
                            int respuesta=new ControlDB_Transportadora(tipoConexion).actualizar(Objeto, user);
                            if(respuesta==1){
                                JOptionPane.showMessageDialog(null, "Se actualizo la transportadora de forma exitosa");
                                codigo.setText("");
                                nit.setText("");
                                digitoVerificacion.setText("");
                                nombre.setText("");
                                observacion.setText("");
                                listado_baseDatos.setSelectedIndex(0);
                                estado.setSelectedIndex(0);
                                tabla_Listar("");
                                codigo.setEnabled(false);
                                nit.setEnabled(false);
                                nombre.setEnabled(false);
                                observacion.setEnabled(false);
                                //listado_baseDatos.setEnabled(false);
                                estado.setEnabled(false);
                            }else{
                                if(respuesta==0){
                                    JOptionPane.showMessageDialog(null, "No se pudo actualizar la transportadora, valide los datos ingresados");
                                }
                            }
                        //}else{
                          //  JOptionPane.showMessageDialog(null, "Ya existe una transportadora en el sistema con el mismo nombre, valide los datos ingresados", "Advertencia", JOptionPane.ERROR_MESSAGE);
                        //}
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "Error al registrar la transportadora");
                        Logger.getLogger(Transportadora_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Transportadora_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }         
            }catch(Exception e){  
              alerta_nit.setText("Error!!. El nit debe ser númerico");
            }    
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        try {
            tabla_Listar(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Transportadora_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO lo del clik derechoo
        int fila1;
        try{
            fila1=tabla.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                if(listadoTransportadora != null){
                    Transportadora Objeto= listadoTransportadora.get(fila1);
                    //DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                    //codigo.setText((String)modelo.getValueAt(fila1, 0));
                   // String[] data=((String)modelo.getValueAt(fila1, 1)).split("-");
                   codigo.setText(Objeto.getCodigo());
                   String[] data=Objeto.getNit().split("-");
                   // String[] data=((String)modelo.getValueAt(fila1, 1)).split("-");
                    //if(nit.setText)
                    if(data.length==1){//No hay digito de verificación
                        nit.setText(data[0]);
                        digitoVerificacion.setText("");
                    }else{
                        if(data.length ==2){
                            nit.setText(data[0]);
                            digitoVerificacion.setText(data[1]);
                        }else{
                            nit.setText("");
                        }
                    }
                    nombre.setText(Objeto.getDescripcion());
                    observacion.setText(Objeto.getObservacion());
                    if(Objeto.getBaseDatos().getCodigo().equals("1")){//Debemos cargar la Base de datos Grupo
                        listado_baseDatos.setSelectedIndex(0);
                    }else{
                         if(Objeto.getBaseDatos().getCodigo().equals("2")){//Debemos cargar la Base de datos Grupo
                            listado_baseDatos.setSelectedIndex(1);
                        }
                    }
                    
                    if(Objeto.getEstado().equalsIgnoreCase("ACTIVO")){
                        estado.setSelectedIndex(0);
                    }else{
                        estado.setSelectedIndex(1);
                    }
                    nit.setEnabled(true);
                    nombre.setEnabled(true);
                    observacion.setEnabled(true);
                    //listado_baseDatos.setEnabled(true);
                    estado.setEnabled(true);
                }
            }
        }catch(Exception e){
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
                    if(listadoTransportadora != null){
                        Transportadora Objeto= listadoTransportadora.get(fila1);
                        //DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                        //codigo.setText((String)modelo.getValueAt(fila1, 0));
                       // String[] data=((String)modelo.getValueAt(fila1, 1)).split("-");
                       codigo.setText(Objeto.getCodigo());
                       String[] data=Objeto.getNit().split("-");
                       // String[] data=((String)modelo.getValueAt(fila1, 1)).split("-");
                        //if(nit.setText)
                        if(data.length==1){//No hay digito de verificación
                            nit.setText(data[0]);
                            digitoVerificacion.setText("");
                        }else{
                            if(data.length ==2){
                                nit.setText(data[0]);
                                digitoVerificacion.setText(data[1]);
                            }else{
                                nit.setText("");
                            }
                        }
                        nombre.setText(Objeto.getDescripcion());
                        observacion.setText(Objeto.getObservacion());
                        if(Objeto.getBaseDatos().getCodigo().equals("1")){//Debemos cargar la Base de datos Grupo
                            listado_baseDatos.setSelectedIndex(0);
                        }else{
                             if(Objeto.getBaseDatos().getCodigo().equals("2")){//Debemos cargar la Base de datos Grupo
                                listado_baseDatos.setSelectedIndex(1);
                            }
                        }
                        if(Objeto.getEstado().equalsIgnoreCase("ACTIVO")){
                            estado.setSelectedIndex(0);
                        }else{
                            estado.setSelectedIndex(1);
                        }
                        nit.setEnabled(true);
                        nombre.setEnabled(true);
                        observacion.setEnabled(true);
                        //listado_baseDatos.setEnabled(true);
                        estado.setEnabled(true);
                    }
                }
            }catch(Exception e){
            }
        }   
    }//GEN-LAST:event_tablaMouseClicked

    private void btnActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseExited
        alerta_nombre.setText("");
    }//GEN-LAST:event_btnActualizarMouseExited

    private void nitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nitMouseClicked
        alerta_nit.setText("");
    }//GEN-LAST:event_nitMouseClicked

    private void nitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nitKeyPressed
        alerta_nit.setText("");
        try{
            if(nit.getText().equals("")){//El nit está vacio
            }else{
                //Procedemos a validar si es un número entero
                try{
                    new java.math.BigDecimal(nit.getText());
                    String nitS = nit.getText();
                    long nitL = Long.parseLong(nitS);
                    CalcularDigitoVerificacion calDV = new CalcularDigitoVerificacion();
                    digitoVerificacion.setText(""+calDV.generarDv(nitL));
                }catch(Exception e){
                    alerta_nit.setText("El Nit debe ser númerico");
                }
            }
        }catch(Exception e){

        }
    }//GEN-LAST:event_nitKeyPressed

    private void nitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nitKeyReleased
        alerta_nit.setText("");
        try{
            if(nit.getText().equals("")){//El nit está vacio
            }else{
                //Procedemos a validar si es un número entero
                try{
                    new java.math.BigDecimal(nit.getText());
                    String nitS = nit.getText();
                    long nitL = Long.parseLong(nitS);
                    CalcularDigitoVerificacion calDV = new CalcularDigitoVerificacion();
                    digitoVerificacion.setText(""+calDV.generarDv(nitL));
                }catch(Exception e){
                    alerta_nit.setText("El Nit debe ser númerico");
                }
            }
        }catch(Exception e){

        }
    }//GEN-LAST:event_nitKeyReleased

    private void nitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nitKeyTyped
        alerta_nit.setText("");
        try{
            if(nit.getText().equals("")){//El nit está vacio
            }else{
                //Procedemos a validar si es un número entero
                try{
                    new java.math.BigDecimal(nit.getText());
                    String nitS = nit.getText();
                    long nitL = Long.parseLong(nitS);
                    CalcularDigitoVerificacion calDV = new CalcularDigitoVerificacion();
                    digitoVerificacion.setText(""+calDV.generarDv(nitL));
                }catch(Exception e){
                    alerta_nit.setText("El Nit debe ser númerico");
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_nitKeyTyped

    private void estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JLabel alerta_nit;
    private javax.swing.JLabel alerta_nombre;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JTextField codigo;
    private javax.swing.JLabel digitoVerificacion;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox<String> listado_baseDatos;
    private javax.swing.JTextField nit;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextArea observacion;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "Nit","Nombre","Observacion", "Estado","Origen de Datos"});  
        listadoTransportadora=new ControlDB_Transportadora(tipoConexion).buscar(valorConsulta);
        listadoTransportadora.stream().map((listado1) -> {
            String[] registro = new String[6];
            registro[0] = "" + listado1.getCodigo();
            registro[1] = "" + listado1.getNit();
            registro[2] = "" + listado1.getDescripcion();
            registro[3] = "" + listado1.getObservacion();
            registro[4] = "" + listado1.getEstado();
            registro[5] = "" + listado1.getBaseDatos().getDescripcion();
            return registro;
        }).forEachOrdered((registro) -> {
            modelo.addRow(registro);
        });
        tabla.setModel(modelo);
    }

}
