package Catalogo.View;
  
import Catalogo.Controller.ControlDB_BaseDatos;
import Catalogo.Controller.ControlDB_CentroCostoMayor;
import Catalogo.Controller.ControlDB_CentroCostoSubCentro;
import Catalogo.Controller.ControlDB_CentroOperacion;
import Catalogo.Model.BaseDatos;
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.CentroCostoMayor;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import Catalogo.Model.Cliente;
import ModuloEquipo.View.Solicitud_Equipos_Registrar;
import Sistema.Model.Usuario;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class CentroCostoMayor_Actualizar extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;
    ArrayList<CentroCostoSubCentro> listadoCentroCostoSubCentro = new ArrayList();
    ArrayList<CentroCostoAuxiliar> listadoCentroCostoAuxiliar= new ArrayList();
    ArrayList<CentroOperacion> listadoCentroOperacion= new ArrayList();
    ArrayList<CentroCostoMayor> listadoCentroCostoMayor=new ArrayList();
    CentroCostoMayor centroCostoMayor;
    ArrayList<Cliente> listadoCliente= new ArrayList();
    Cliente cliente;
    ArrayList<BaseDatos> listadoBaseDatos= new ArrayList();
    public CentroCostoMayor_Actualizar(Usuario us,String tipoConexion) {
        initComponents();
        centroOperacion.setEnabled(false);
        selectSubcentroCosto.setEnabled(false);
        nombre.setEnabled(false);
        estado.setEnabled(false);
        
        cliente= new Cliente();
        user=us;   
        this.tipoConexion= tipoConexion;
        listadoCentroCostoMayor=null;
        centroCostoMayor = new CentroCostoMayor();
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            Logger.getLogger(CentroCostoMayor_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //Cargamos en la interfaz los centros de Operaciones
        try {
            listadoCentroOperacion=new ControlDB_CentroOperacion(tipoConexion).buscarActivos();
            if(listadoCentroOperacion != null){
                String datosObjeto[]= new String[listadoCentroOperacion.size()];
                int contador=0;
                for(CentroOperacion listadoCentroOperacion1 : listadoCentroOperacion){ 
                    datosObjeto[contador]=listadoCentroOperacion1.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                centroOperacion.setModel(model);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }  
        //Cargamos en la interfaz los subcentro de costos activos
        try {
            listadoCentroCostoSubCentro=new ControlDB_CentroCostoSubCentro(tipoConexion).buscarActivos(listadoCentroOperacion.get(centroOperacion.getSelectedIndex()));
            if(listadoCentroCostoSubCentro != null){
                String datosObjeto[]= new String[listadoCentroCostoSubCentro.size()];
                int contador=0;
                for(CentroCostoSubCentro Objeto : listadoCentroCostoSubCentro){ 
                    datosObjeto[contador]=Objeto.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                selectSubcentroCosto.setModel(model);
            } 
      
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Motonave_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }  
        listado_baseDatos.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        clienteCodigo = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox<>();
        btn_registrar_insumo1 = new javax.swing.JButton();
        btn_registrar_insumo2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        codigoCentroCosto = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        clienteNombre = new javax.swing.JLabel();
        titulo20 = new javax.swing.JLabel();
        selectSubcentroCosto = new javax.swing.JComboBox<>();
        titulo28 = new javax.swing.JLabel();
        centroOperacion = new javax.swing.JComboBox<>();
        valorBusqueda1 = new javax.swing.JTextField();
        consultar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        listado_baseDatos = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();

        Editar.setText("Modificar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        clienteCodigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(clienteCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 470, 30));

        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });
        add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 470, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Descripción");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 100, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Estado");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 120, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, 470, 30));

        btn_registrar_insumo1.setBackground(new java.awt.Color(255, 255, 255));
        btn_registrar_insumo1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_registrar_insumo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/actualizar.png"))); // NOI18N
        btn_registrar_insumo1.setText("ACTUALIZAR");
        btn_registrar_insumo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_registrar_insumo1MouseExited(evt);
            }
        });
        btn_registrar_insumo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrar_insumo1ActionPerformed(evt);
            }
        });
        add(btn_registrar_insumo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 510, 140, 40));

        btn_registrar_insumo2.setBackground(new java.awt.Color(255, 255, 255));
        btn_registrar_insumo2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_registrar_insumo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btn_registrar_insumo2.setText("CANCELAR");
        btn_registrar_insumo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrar_insumo2ActionPerformed(evt);
            }
        });
        add(btn_registrar_insumo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 510, 140, 40));

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
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla.setComponentPopupMenu(Seleccionar);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 820, 490));

        codigoCentroCosto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(codigoCentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 470, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Cliente Nombre:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 140, 30));

        clienteNombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(clienteNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 470, 30));

        titulo20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo20.setForeground(new java.awt.Color(51, 51, 51));
        titulo20.setText("SubCentro Costo:");
        add(titulo20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 150, 30));

        selectSubcentroCosto.setToolTipText("");
        selectSubcentroCosto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectSubcentroCostoItemStateChanged(evt);
            }
        });
        add(selectSubcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 470, 30));

        titulo28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo28.setForeground(new java.awt.Color(51, 51, 51));
        titulo28.setText("Centro Operación:");
        add(titulo28, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 150, 30));

        centroOperacion.setToolTipText("");
        centroOperacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                centroOperacionItemStateChanged(evt);
            }
        });
        add(centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, 470, 30));
        add(valorBusqueda1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, 670, 40));

        consultar.setBackground(new java.awt.Color(255, 255, 255));
        consultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        consultar.setText("CONSULTAR");
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        add(consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 70, 140, 40));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Código Cliente:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 140, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Código:");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 140, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ACTUALIZAR DE CENTRO DE COSTO MAYOR");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 1460, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 1460, 100));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("BD Cliente:");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 120, 30));

        listado_baseDatos.setToolTipText("");
        add(listado_baseDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 470, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 640, 490));
    }// </editor-fold>//GEN-END:initComponents

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        int fila1;
        try{
            fila1=tabla.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                //DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                centroCostoMayor=listadoCentroCostoMayor.get(fila1);
                clienteCodigo.setText(centroCostoMayor.getCliente().getCodigo());
                clienteNombre.setText(centroCostoMayor.getCliente().getDescripcion());
                codigoCentroCosto.setText(centroCostoMayor.getCodigo());
                nombre.setText(centroCostoMayor.getDescripcion());  
                int n=0;
                for(CentroOperacion objeto: listadoCentroOperacion){
                    if(objeto.getCodigo()== centroCostoMayor.getCentroCostoSubcentro().getCentroOperacion().getCodigo()){
                        centroOperacion.setSelectedIndex(n);
                    }
                    n++;
                }
                n=0;
                for(CentroCostoSubCentro  objeto: listadoCentroCostoSubCentro){
                    if(objeto.getCodigo()== centroCostoMayor.getCentroCostoSubcentro().getCodigo()){
                        selectSubcentroCosto.setSelectedIndex(n);
                    }
                    n++;
                }
                String estadoS=centroCostoMayor.getEstado();
                if(estadoS.equalsIgnoreCase("1")){
                    estado.setSelectedIndex(0);
                }else{
                    estado.setSelectedIndex(1);
                }
                int contador=0;
                for(BaseDatos db : listadoBaseDatos){
                    if(db.getCodigo().equals(centroCostoMayor.getClienteBaseDatos())){
                            listado_baseDatos.setSelectedIndex(contador);
                    }
                    contador++;
                }
                nombre.setEnabled(true);
                estado.setEnabled(true);
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void btn_registrar_insumo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrar_insumo1ActionPerformed
        if(codigoCentroCosto.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Debe cargar un Centro de Costo ","Advertencia",JOptionPane.ERROR_MESSAGE);
        }else{
            if(nombre.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "La descripción del centro de costo  no puede estar vacia","Advertencia",JOptionPane.ERROR_MESSAGE);
            }else{
                centroCostoMayor.setDescripcion(nombre.getText());
                //Validamos si selecciono activo o inactivo
                if(estado.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                    centroCostoMayor.setEstado("1");
                }else{
                    centroCostoMayor.setEstado("0");
                }
                //if(new ControlDB_CentroCostoMayor(tipoConexion).validarExistenciaActualizar(centroCostoMayor)){
                    //JOptionPane.showMessageDialog(null, "Ya existe un Centro de Costo mayor con esas característica y se encuentra activo","Advertencia",JOptionPane.ERROR_MESSAGE);
                //}else{
                    int retorno;
                    try {
                        retorno = new ControlDB_CentroCostoMayor(tipoConexion).actualizar(centroCostoMayor, user);
                        if(retorno==1){
                            JOptionPane.showMessageDialog(null, "Se actualizó el centro de costo mayor de manera exitosa");
                            codigoCentroCosto.setText("");
                            clienteCodigo.setText("");
                            clienteNombre.setText("");
                            nombre.setText("");
                            centroOperacion.setSelectedIndex(0);
                            selectSubcentroCosto.setSelectedIndex(0);
                            estado.setSelectedIndex(0);
                            nombre.setEnabled(false);
                            estado.setEnabled(false);
                            try {
                                tabla_Listar("");
                            } catch (SQLException ex) {
                                Logger.getLogger(CentroCostoMayor_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }else{
                            if(retorno==0){
                                JOptionPane.showMessageDialog(null, "No se pudo actualizar el centro de costo mayor, valide datos");
                            }
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(CentroCostoMayor_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(CentroCostoMayor_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SocketException ex) {
                        Logger.getLogger(CentroCostoMayor_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                //}
            }
        }
    }//GEN-LAST:event_btn_registrar_insumo1ActionPerformed

    private void btn_registrar_insumo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrar_insumo2ActionPerformed
        nombre.setText("");
        //subcentroCosto.setSelectedIndex(0);
        estado.setSelectedIndex(0);
    }//GEN-LAST:event_btn_registrar_insumo2ActionPerformed

    private void btn_registrar_insumo1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_registrar_insumo1MouseExited
       // alerta_nombre.setText("");
    }//GEN-LAST:event_btn_registrar_insumo1MouseExited

    private void selectSubcentroCostoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectSubcentroCostoItemStateChanged
        
    }//GEN-LAST:event_selectSubcentroCostoItemStateChanged

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        try {
            tabla_Listar(valorBusqueda1.getText());
        } catch (SQLException ex) {
            Logger.getLogger(CentroCostoMayor_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_consultarActionPerformed

    private void centroOperacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_centroOperacionItemStateChanged
        //Cargamos en la interfaz los subcentro de costos activos
        try {
            listadoCentroCostoSubCentro=new ControlDB_CentroCostoSubCentro(tipoConexion).buscarActivos(listadoCentroOperacion.get(centroOperacion.getSelectedIndex()));
            if(listadoCentroCostoSubCentro != null){
                String datosObjeto[]= new String[listadoCentroCostoSubCentro.size()];
                int contador=0;
                for(CentroCostoSubCentro Objeto : listadoCentroCostoSubCentro){ 
                    datosObjeto[contador]=Objeto.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                selectSubcentroCosto.setModel(model);
            } 
      
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_centroOperacionItemStateChanged

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() == 2) {
            try{
                int fila1;
                try{
                    fila1=tabla.getSelectedRow();
                    if(fila1==-1){
                        JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                    }
                    else{
                        //DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                        centroCostoMayor=listadoCentroCostoMayor.get(fila1);
                        clienteCodigo.setText(centroCostoMayor.getCliente().getCodigo());
                        clienteNombre.setText(centroCostoMayor.getCliente().getDescripcion());
                        codigoCentroCosto.setText(centroCostoMayor.getCodigo());
                        nombre.setText(centroCostoMayor.getDescripcion());  
                        int n=0;
                        for(CentroOperacion objeto: listadoCentroOperacion){
                            if(objeto.getCodigo()== centroCostoMayor.getCentroCostoSubcentro().getCentroOperacion().getCodigo()){
                                centroOperacion.setSelectedIndex(n);
                            }
                            n++;
                        }
                        n=0;
                        for(CentroCostoSubCentro  objeto: listadoCentroCostoSubCentro){
                            if(objeto.getCodigo()== centroCostoMayor.getCentroCostoSubcentro().getCodigo()){
                                selectSubcentroCosto.setSelectedIndex(n);
                            }
                            n++;
                        }
                        String estadoS=centroCostoMayor.getEstado();
                        if(estadoS.equalsIgnoreCase("1")){
                            estado.setSelectedIndex(0);
                        }else{
                            estado.setSelectedIndex(1);
                        }
                        int contador=0;
                        for(BaseDatos db : listadoBaseDatos){
                            if(db.getCodigo().equals(centroCostoMayor.getClienteBaseDatos())){
                                    listado_baseDatos.setSelectedIndex(contador);
                            }
                            contador++;
                        }
                        nombre.setEnabled(true);
                        estado.setEnabled(true);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JButton btn_registrar_insumo1;
    private javax.swing.JButton btn_registrar_insumo2;
    private javax.swing.JComboBox<String> centroOperacion;
    private javax.swing.JLabel clienteCodigo;
    private javax.swing.JLabel clienteNombre;
    private javax.swing.JLabel codigoCentroCosto;
    private javax.swing.JButton consultar;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> listado_baseDatos;
    private javax.swing.JTextField nombre;
    private javax.swing.JComboBox<String> selectSubcentroCosto;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel titulo20;
    private javax.swing.JLabel titulo28;
    private javax.swing.JTextField valorBusqueda1;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException{             
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "CentroOperación","Cliente","SubcentroCosto","Descripción","Base De Datos", "Estado"});  
        listadoCentroCostoMayor =new ControlDB_CentroCostoMayor(tipoConexion).buscar(valorConsulta);
        for(CentroCostoMayor Objeto: listadoCentroCostoMayor){
            String[] registro = new String[7];
            registro[0]=""+Objeto.getCodigo();
            registro[1]=""+Objeto.getCentroCostoSubcentro().getCentroOperacion().getDescripcion();
            registro[2]=""+Objeto.getCliente().getDescripcion();
            registro[3]=""+Objeto.getCentroCostoSubcentro().getDescripcion();
            registro[4]=""+Objeto.getDescripcion();
            registro[5]=""+Objeto.getCliente().getBaseDatos().getDescripcion();
            if(Objeto.getEstado().equals("1")){
                registro[6]="ACTIVO";
            }else{
                registro[6]="INACTIVO";
            }
            modelo.addRow(registro);   
        }
        tabla.setModel(modelo);
        resizeColumnWidth(tabla);
    }
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            if(width > 300)
                width=300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
