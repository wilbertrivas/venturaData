package Catalogo.View;
 
import Catalogo.Controller.ControlDB_BaseDatos;
import Catalogo.View.CalcularDigitoVerificacion;
import Catalogo.Controller.ControlDB_Transportadora;
import Catalogo.Model.BaseDatos;
import Catalogo.Model.Transportadora;
import ModuloEquipo.View.Solicitud_Equipos_Registrar;
import Sistema.Model.Usuario;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
 
public final class Transportadora_Registrar extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;
    ArrayList<BaseDatos> listadoBaseDatos= new ArrayList();
    
    public Transportadora_Registrar(Usuario us,String tipoConexion) {
        user=us;
        initComponents();
        this.tipoConexion= tipoConexion;
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las transportadoras");
            Logger.getLogger(Transportadora_Registrar.class.getName()).log(Level.SEVERE, null, ex);
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
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        nombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
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
        alerta_codigo = new javax.swing.JLabel();
        listado_baseDatos = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 42, 730, 10));
        add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 560, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Nit:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 110, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("REGISTRO DE TRANSPORTADORA");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 730, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Observación:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 100, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, 310, 30));

        btnRegistrar.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Ingresar.png"))); // NOI18N
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 430, 140, -1));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 430, 140, -1));

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
        jScrollPane1.setViewportView(tabla);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 850, 550));

        alerta_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_nombre.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 460, 20));

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
        add(nit, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 120, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("-");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 10, 30));

        digitoVerificacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(digitoVerificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 60, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Código:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 110, 30));

        alerta_nit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_nit.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_nit, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 460, 20));
        add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 560, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Nombre:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 110, 30));

        observacion.setColumns(20);
        observacion.setRows(5);
        jScrollPane2.setViewportView(observacion);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 560, 110));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Estado:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 110, 30));

        alerta_codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_codigo.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 460, 20));

        listado_baseDatos.setToolTipText("");
        add(listado_baseDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 560, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Base de Datos:");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 110, 30));

        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 730, 550));
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if(codigo.getText().isEmpty()){
            alerta_codigo.setText("Error el código de la transportadora no puede estar vacio");
        }else{
           if(nit.getText().equals("")){
                alerta_nit.setText("Error el Nit no Puede estar vacio");
            }else{
                try{
                    new java.math.BigDecimal(nit.getText());
                    if(nombre.getText().equals("")){
                        alerta_nombre.setText("El nombre de la transportadora de equipo no puede estar vacio");
                    }else{
                        Transportadora Objeto = new Transportadora();
                        Objeto.setCodigo(codigo.getText());
                        Objeto.setNit(nit.getText()+"-"+digitoVerificacion.getText());
                        Objeto.setDescripcion(nombre.getText());
                        Objeto.setObservacion(observacion.getText());       
                        //Validamos si selecciono activo o inactivo
                        if(estado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                            Objeto.setEstado("1");
                        }else{
                            Objeto.setEstado("0");
                        }
                        if(listadoBaseDatos != null){
                            Objeto.setBaseDatos(listadoBaseDatos.get(listado_baseDatos.getSelectedIndex()));
                        }else{
                            Objeto.setBaseDatos(new BaseDatos("NULL"));
                        }    
                        try {
                            if(new ControlDB_Transportadora(tipoConexion).validarExistencia(Objeto)){
                                JOptionPane.showMessageDialog(null, "La transportadora ya se encuentra registrada en el sistema");
                            }else{
                                int respuesta=new ControlDB_Transportadora(tipoConexion).registrar(Objeto,user);
                                if(respuesta==1){
                                    JOptionPane.showMessageDialog(null, "Se registro la transportadora de forma exitosa");
                                    codigo.setText("");
                                    nit.setText("");
                                    digitoVerificacion.setText("");                                  
                                    nombre.setText("");
                                    observacion.setText("");
                                    estado.setSelectedIndex(0);
                                    listado_baseDatos.setSelectedIndex(0);
                                    tabla_Listar("");
                                }else{
                                    if(respuesta==0){
                                        JOptionPane.showMessageDialog(null, "No se pudo registrar la transportadora, valide datos");
                                    }
                                }
                            }
                        } catch (FileNotFoundException ex) {
                            JOptionPane.showMessageDialog(null, "Error al registrar la transportadora");
                            Logger.getLogger(Transportadora_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(Transportadora_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "El nit debe ser númerico", "Advertencia", JOptionPane.ERROR_MESSAGE);
                }
            }     
        }  
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        codigo.setText("");
        nit.setText("");
        digitoVerificacion.setText("");
        nombre.setText("");
        observacion.setText("");
        estado.setSelectedIndex(0);
        listado_baseDatos.setSelectedIndex(0);
    }//GEN-LAST:event_btnCancelarActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alerta_codigo;
    private javax.swing.JLabel alerta_nit;
    private javax.swing.JLabel alerta_nombre;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JTextField codigo;
    private javax.swing.JLabel digitoVerificacion;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "Nit","Nombre","Observacion", "Estado","Origen de Datos"});  
        ArrayList<Transportadora> listado=new ControlDB_Transportadora(tipoConexion).buscar(valorConsulta);
        for (Transportadora listado1 : listado) {
            String[] registro = new String[6];
            registro[0] = "" + listado1.getCodigo();
            registro[1] = "" + listado1.getNit();
            registro[2] = "" + listado1.getDescripcion();
            registro[3] = "" + listado1.getObservacion();
            registro[4] = "" + listado1.getEstado();
            registro[5] = "" + listado1.getBaseDatos().getDescripcion();
            modelo.addRow(registro);      
        }
        tabla.setModel(modelo);
    }

}
