package Catalogo.View1;
    
import Catalogo.Controller.ControlDB_Articulo;
import Catalogo.Controller.ControlDB_BaseDatos;
import Catalogo.Controller.ControlDB_TipoArticulo;
import Catalogo.Model.Articulo;
import Catalogo.Model.BaseDatos;
import Catalogo.Model.TipoArticulo;
import ModuloEquipo.View2.Solicitud_Equipos_Registrar;
import Sistema.Model.Usuario;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class Articulo_Registrar extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;
    ArrayList<TipoArticulo> listadoTipoArticulo = new ArrayList();
    ArrayList<BaseDatos> listadoBaseDatos= new ArrayList();
    public Articulo_Registrar(Usuario us,String tipoConexion) {
       
        initComponents();
         user=us;
        this.tipoConexion= tipoConexion;
        
        //Cargamos en la interfaz los tipos de articulos activos
        try {
            listadoTipoArticulo=new ControlDB_TipoArticulo(tipoConexion).buscarActivos();
            for(TipoArticulo tipoArticulo: listadoTipoArticulo){
                Select_TipoArticulo.addItem(tipoArticulo.getDescripcion());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los articulos");
            Logger.getLogger(Articulo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
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

        nombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        codigo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Select_TipoArticulo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        listado_baseDatos = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });
        add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 310, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("TipoArticulo:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 100, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Estado:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 90, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 310, 30));

        btnRegistrar.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Ingresar.png"))); // NOI18N
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 140, 40));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, 140, 40));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 850, 500));
        add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 310, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Nombre:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 80, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Código:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 80, 30));

        Select_TipoArticulo.setToolTipText("");
        add(Select_TipoArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 310, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("REGISTRO DE ARTICULOS");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 540, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Base de Datos:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 120, 30));

        listado_baseDatos.setToolTipText("");
        listado_baseDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listado_baseDatosActionPerformed(evt);
            }
        });
        add(listado_baseDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 310, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 540, 500));
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if(codigo.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "El codigo del articulo no puede estar vacio", "Error!!", JOptionPane.ERROR_MESSAGE);
        }else{
            if(nombre.getText().equals("")){
                JOptionPane.showMessageDialog(null, "El nombre del articulo no puede estar vacio", "Error!!", JOptionPane.ERROR_MESSAGE);
            }else{
                Articulo Objeto = new Articulo();
                Objeto.setCodigo(codigo.getText());
                try{
                    Objeto.setTipoArticulo(listadoTipoArticulo.get(Select_TipoArticulo.getSelectedIndex()));
                }catch(Exception e){
                    Objeto.setTipoArticulo(null);
                }
                
                Objeto.setDescripcion(nombre.getText());
                //Validamos si selecciono activo o inactivo
                if(estado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                    Objeto.setEstado("1");
                }else{
                    Objeto.setEstado("0");
                }
                
                //validamos que Base de datos seleccionó el usuario
                if(listadoBaseDatos != null){
                        Objeto.setBaseDatos(listadoBaseDatos.get(listado_baseDatos.getSelectedIndex()));
                }else{
                    Objeto.setBaseDatos(new BaseDatos("NULL"));
                }   
                try {
                    if(new ControlDB_Articulo(tipoConexion).validarExistencia(Objeto)){
                        JOptionPane.showMessageDialog(null, "El articulo ya se encuentra registrado en el sistema");
                    }else{
                        int respuesta=new ControlDB_Articulo(tipoConexion).registrar(Objeto,user);
                        if(respuesta==1){
                            JOptionPane.showMessageDialog(null, "Se registro el articulo de forma Exitosa");
                            codigo.setText("");
                            Select_TipoArticulo.setSelectedIndex(0);
                            nombre.setText("");
                            estado.setSelectedIndex(0);
                            try{
                                listado_baseDatos.setSelectedIndex(0);
                            }catch(Exception e){
                                e.printStackTrace();
                            }    
                            tabla_Listar("");
                        }else{
                            if(respuesta==0){
                                JOptionPane.showMessageDialog(null, "No se pudo registrar el articulo, valide datos");
                            }
                        }
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Error al registrar el articulo");
                    Logger.getLogger(Articulo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Articulo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(Articulo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SocketException ex) {
                    Logger.getLogger(Articulo_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        codigo.setText("");
        nombre.setText("");
        estado.setSelectedIndex(0);
        try{
            listado_baseDatos.setSelectedIndex(0);
        }catch(Exception e){
            e.printStackTrace();
        }    
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed

    private void listado_baseDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listado_baseDatosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listado_baseDatosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Select_TipoArticulo;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JTextField codigo;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> listado_baseDatos;
    private javax.swing.JTextField nombre;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código","TipoArticulo", "Nombre","CódigoERP","Unidad_Negocio","Estado","Origen de Datos"});  
        ArrayList<Articulo> listado=new ControlDB_Articulo(tipoConexion).buscar(valorConsulta);
        for (Articulo listado1 : listado) {
            String[] registro = new String[7];
            registro[0] = "" + listado1.getCodigo();
            registro[1] = "" + listado1.getTipoArticulo().getDescripcion();
            registro[2] = "" + listado1.getDescripcion();
            registro[3] = "" + listado1.getTipoArticulo().getCodigoERP();
            registro[4] = "" + listado1.getTipoArticulo().getUnidadNegocio();
            registro[5] = "" + listado1.getEstado();
            registro[6]=""+listado1.getBaseDatos().getDescripcion();  
            modelo.addRow(registro);      
        }
        tabla.setModel(modelo);
    }

}
