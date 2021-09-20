package Catalogo.View;
  
import Catalogo.Controller.ControlDB_CentroCostoSubCentro;
import Catalogo.Controller.ControlDB_CentroOperacion;
import Catalogo.Controller.ControlDB_ZonaTrabajo;
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import Catalogo.Model.ListadoSitiosZonaTrabajo;
import Catalogo.Model.ZonaTrabajo;
import ModuloCarbon.Controller2.ControlDB_MvtoCarbon;
import ModuloCarbon.View.MvtoCarbon_ModificarFinal;
import ModuloEquipo.View.Solicitud_Equipos_Registrar;
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

public final class ZonaTrabajoPorCentroCostoAuxiliar_Registrar extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;
    private ArrayList<CentroOperacion> listadoCentroOperacion = null;
    private ArrayList<CentroCostoSubCentro> listadoCentroCostoSubCentro = null;
    private ArrayList<CentroCostoAuxiliar> listadoCentroCostoAuxiliar =null;
    private ArrayList<ZonaTrabajo> list_zonaTrabajo =null;
    private ArrayList<ListadoSitiosZonaTrabajo> listarLista_Cc_auxiliar_ZonaTrabajo;
    
    public ZonaTrabajoPorCentroCostoAuxiliar_Registrar(Usuario us,String tipoConexion) {
        initComponents();
        user=us;   
        this.tipoConexion= tipoConexion;
        
        
        try {
            tabla_Listar("");
        } catch (SQLException ex) {
            Logger.getLogger(ZonaTrabajoPorCentroCostoAuxiliar_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Cargamos en la interfaz los centros de Operaciones
        try {
            listadoCentroOperacion=new ControlDB_CentroOperacion(tipoConexion).buscarActivos();
            //listadoCentroOperacion_mvtoEquipo=listadoCentroOperacion;
            if(listadoCentroOperacion != null){  
                String datosObjeto[]= new String[listadoCentroOperacion.size()];
                int contador=0;
                for(CentroOperacion listadoCentroOperacion1 : listadoCentroOperacion){ 
                    datosObjeto[contador]=listadoCentroOperacion1.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                select_MvtoCarbon_CO.setModel(model);
                //select_MvtoEquipo_CO.setModel(model);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        //Cargamos en la interfaz los Subcentro de costos según el centro de Operación
        try {
            listadoCentroCostoSubCentro=new ControlDB_CentroCostoSubCentro(tipoConexion).buscarActivos(listadoCentroOperacion.get(select_MvtoCarbon_CO.getSelectedIndex()));
        } catch (SQLException ex) {
            Logger.getLogger(MvtoCarbon_ModificarFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
        //listadoCentroCostoSubCentro_mvtoEquipo=listadoCentroCostoSubCentro_mvtoEquipo;
        if(listadoCentroCostoSubCentro != null){
            String datosObjeto[]= new String[listadoCentroCostoSubCentro.size()];
            int contador=0;
            for(CentroCostoSubCentro Objeto : listadoCentroCostoSubCentro){
                datosObjeto[contador]=Objeto.getDescripcion();
                contador++;
            }
            final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
            select_MvtoCarbon_SubcentroCosto.setModel(model);
            //select_MvtoEquipo_SubcentroCosto.setModel(model);
        } 
        
        //Cargamos los la interfaz los auxiliares de costos según selección de SubCentro de costos
        try {
            if(listadoCentroCostoSubCentro!= null){
                listadoCentroCostoAuxiliar=new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro.get(select_MvtoCarbon_SubcentroCosto.getSelectedIndex()).getCodigo());
                //listadoCentroCostoAuxiliar_mvtoEquipo=listadoCentroCostoAuxiliar;
                if(listadoCentroCostoAuxiliar != null){
                    String datosObjeto[]= new String[listadoCentroCostoAuxiliar.size()];
                    int contador=0;
                    for(CentroCostoAuxiliar Objeto : listadoCentroCostoAuxiliar){ 
                        datosObjeto[contador]=Objeto.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    //select_MvtoCarbon_CCAuxiliar.setModel(model);
                    select_MvtoCarbon_CCAuxiliar.setModel(model);
                } 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_Equipos_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Cargamos las zona de Trabajo (Operación)
        try {
            list_zonaTrabajo= new ControlDB_ZonaTrabajo(tipoConexion).buscar("");
            if(list_zonaTrabajo != null){
                String datosObjeto[]= new String[list_zonaTrabajo.size()];
                int contador=0;
                for(ZonaTrabajo Objeto : list_zonaTrabajo){ 
                    datosObjeto[contador]=Objeto.getDescripcion();
                    contador++;
                }
                final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                //select_MvtoCarbon_CCAuxiliar.setModel(model);
                select_ZonaTrabajo.setModel(model);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(ZonaTrabajoPorCentroCostoAuxiliar_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        estado.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        jLabel6 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btn_registrar_insumo1 = new javax.swing.JButton();
        btn_registrar_insumo2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        titulo29 = new javax.swing.JLabel();
        select_MvtoCarbon_CO = new javax.swing.JComboBox<>();
        titulo25 = new javax.swing.JLabel();
        select_MvtoCarbon_SubcentroCosto = new javax.swing.JComboBox<>();
        titulo45 = new javax.swing.JLabel();
        select_MvtoCarbon_CCAuxiliar = new javax.swing.JComboBox<>();
        titulo26 = new javax.swing.JLabel();
        select_ZonaTrabajo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        Editar.setText("Modificar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Estado");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 120, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 320, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("REGISTRAR CENTRO AUXILIARES POR ZONA DE TRABAJO");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1400, 30));

        btn_registrar_insumo1.setBackground(new java.awt.Color(255, 255, 255));
        btn_registrar_insumo1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_registrar_insumo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Ingresar.png"))); // NOI18N
        btn_registrar_insumo1.setText("REGISTRAR");
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
        add(btn_registrar_insumo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 140, 40));

        btn_registrar_insumo2.setBackground(new java.awt.Color(255, 255, 255));
        btn_registrar_insumo2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_registrar_insumo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btn_registrar_insumo2.setText("CANCELAR");
        btn_registrar_insumo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrar_insumo2ActionPerformed(evt);
            }
        });
        add(btn_registrar_insumo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 210, 140, 40));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 1400, 440));

        titulo29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo29.setForeground(new java.awt.Color(51, 51, 51));
        titulo29.setText("CENTRO OPERACIÓN:");
        titulo29.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo29, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 170, 30));

        select_MvtoCarbon_CO.setToolTipText("");
        select_MvtoCarbon_CO.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoCarbon_COItemStateChanged(evt);
            }
        });
        add(select_MvtoCarbon_CO, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 320, 30));

        titulo25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo25.setForeground(new java.awt.Color(51, 51, 51));
        titulo25.setText("ZONA DE TRABAJO:");
        titulo25.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo25, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, 130, 30));

        select_MvtoCarbon_SubcentroCosto.setToolTipText("");
        select_MvtoCarbon_SubcentroCosto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoCarbon_SubcentroCostoItemStateChanged(evt);
            }
        });
        add(select_MvtoCarbon_SubcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, 350, 30));

        titulo45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo45.setForeground(new java.awt.Color(51, 51, 51));
        titulo45.setText("C.C. AUXILIAR ORIGEN:");
        titulo45.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo45, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 150, 30));

        select_MvtoCarbon_CCAuxiliar.setToolTipText("");
        add(select_MvtoCarbon_CCAuxiliar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 320, 30));

        titulo26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo26.setForeground(new java.awt.Color(51, 51, 51));
        titulo26.setText("SUBCENTRO COSTO:");
        titulo26.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo26, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, 130, 30));

        select_ZonaTrabajo.setToolTipText("");
        select_ZonaTrabajo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_ZonaTrabajoItemStateChanged(evt);
            }
        });
        add(select_ZonaTrabajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 110, 350, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 1400, 220));
    }// </editor-fold>//GEN-END:initComponents

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
       
    }//GEN-LAST:event_EditarActionPerformed

    private void btn_registrar_insumo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrar_insumo1ActionPerformed
        if(listadoCentroOperacion != null){
            if(list_zonaTrabajo != null){
                ZonaTrabajo zonaTrabajo= new ZonaTrabajo();
                zonaTrabajo.setCodigo(list_zonaTrabajo.get(select_ZonaTrabajo.getSelectedIndex()).getCodigo());
                ArrayList<CentroCostoAuxiliar> lstCentroCostoAuxiliar = new ArrayList<>();
                lstCentroCostoAuxiliar.add(listadoCentroCostoAuxiliar.get(select_MvtoCarbon_CCAuxiliar.getSelectedIndex()));
                zonaTrabajo.setCodigo(list_zonaTrabajo.get(select_ZonaTrabajo.getSelectedIndex()).getCodigo());
                zonaTrabajo.setListadoCentroCostoAuxiliar(lstCentroCostoAuxiliar);
                if(estado.getSelectedIndex() == 0){//El usuario seleccionó activo
                    zonaTrabajo.setEstado("1");
                }else{//El usuario seleccionó inactivo
                    zonaTrabajo.setEstado("0");
                }
                if(!new ControlDB_ZonaTrabajo(tipoConexion).validarExistenciaListadoZonaTrabajo(zonaTrabajo)){
                    try {
                        //Procedemos a registrar la relación zonaTrabajo-CentroCosto Auxiliar
                        int respuesta=new ControlDB_ZonaTrabajo(tipoConexion).registrarEnListadoZonaTrabajo(zonaTrabajo,user);
                        if(respuesta==1){
                            JOptionPane.showMessageDialog(null, "Se registró la relación CentroCosto_Auxiliar-ZonaTrabajo de forma Exitosa");
                        }else{
                            if(respuesta==0){
                                JOptionPane.showMessageDialog(null, "No se pudo registrar la relación CentroCosto_Auxiliar-ZonaTrabajo, valide datos");
                            }
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ZonaTrabajoPorCentroCostoAuxiliar_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(ZonaTrabajoPorCentroCostoAuxiliar_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SocketException ex) {
                        Logger.getLogger(ZonaTrabajoPorCentroCostoAuxiliar_Registrar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                   JOptionPane.showMessageDialog(null, "Ya está registrada esta relación de ZonaTrabajo-CentroCostoAuxiliar en el sistema, valide si se encuentra inactivo.","Advertencia",JOptionPane.ERROR_MESSAGE);  
                }
            }else{
               JOptionPane.showMessageDialog(null, "Debe seleccionar una zona de trabajo","Advertencia",JOptionPane.ERROR_MESSAGE);  
            }
        }else{
           JOptionPane.showMessageDialog(null, "Debe seleccionar un CentroCostoAuxiliar","Advertencia",JOptionPane.ERROR_MESSAGE); 
        }
    }//GEN-LAST:event_btn_registrar_insumo1ActionPerformed

    private void btn_registrar_insumo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrar_insumo2ActionPerformed
        select_MvtoCarbon_CO.setSelectedIndex(0);
        select_MvtoCarbon_SubcentroCosto.setSelectedIndex(0);
        select_ZonaTrabajo.setSelectedIndex(0);
        estado.setSelectedIndex(0);
    }//GEN-LAST:event_btn_registrar_insumo2ActionPerformed

    private void btn_registrar_insumo1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_registrar_insumo1MouseExited
       // alerta_nombre.setText("");
    }//GEN-LAST:event_btn_registrar_insumo1MouseExited

    private void select_MvtoCarbon_COItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_COItemStateChanged
        //Cargamos en la interfaz los subcentro de costos activos
        listadoCentroCostoAuxiliar=null;
        listadoCentroCostoSubCentro=null;
        try {
            if(listadoCentroOperacion != null){
                listadoCentroCostoSubCentro=new ControlDB_CentroCostoSubCentro(tipoConexion).buscarActivos(listadoCentroOperacion.get(select_MvtoCarbon_CO.getSelectedIndex()));
                if(listadoCentroCostoSubCentro != null){
                    String datosObjeto[]= new String[listadoCentroCostoSubCentro.size()];
                    int contador=0;
                    for(CentroCostoSubCentro Objeto : listadoCentroCostoSubCentro){
                        datosObjeto[contador]=Objeto.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_MvtoCarbon_SubcentroCosto.setModel(model);
                }else{
                    listadoCentroCostoSubCentro=null;
                    select_MvtoCarbon_SubcentroCosto.removeAllItems();

                    listadoCentroCostoAuxiliar=null;
                    select_MvtoCarbon_CCAuxiliar.removeAllItems();
                }
            }else{

                listadoCentroOperacion=null;
                select_MvtoCarbon_CO.removeAllItems();

                listadoCentroCostoSubCentro=null;
                select_MvtoCarbon_SubcentroCosto.removeAllItems();

                listadoCentroCostoAuxiliar=null;
                select_MvtoCarbon_CCAuxiliar.removeAllItems();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZonaTrabajoPorCentroCostoAuxiliar_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Cargamos los auxiliares
        if(listadoCentroCostoSubCentro != null){
            try {
                listadoCentroCostoAuxiliar = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro.get(select_MvtoCarbon_SubcentroCosto.getSelectedIndex()).getCodigo());
                if(listadoCentroCostoAuxiliar != null){
                    String datosObjeto[] = new String[listadoCentroCostoAuxiliar.size()];
                    int contador = 0;
                    for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliar) {
                        datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_MvtoCarbon_CCAuxiliar.setModel(model);
                }else{
                    listadoCentroCostoAuxiliar=null;
                    select_MvtoCarbon_CCAuxiliar.removeAllItems();

                }
            }catch (SQLException e){
            }
        }else{
            listadoCentroCostoSubCentro=null;
            select_MvtoCarbon_SubcentroCosto.removeAllItems();

            listadoCentroCostoAuxiliar=null;
            select_MvtoCarbon_CCAuxiliar.removeAllItems();
        }
    }//GEN-LAST:event_select_MvtoCarbon_COItemStateChanged

    private void select_MvtoCarbon_SubcentroCostoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_MvtoCarbon_SubcentroCostoItemStateChanged
        if(listadoCentroCostoSubCentro != null){
            try {
                listadoCentroCostoAuxiliar = new ControlDB_MvtoCarbon(tipoConexion).buscarCentroCostoAuxiliar(""+listadoCentroCostoSubCentro.get(select_MvtoCarbon_SubcentroCosto.getSelectedIndex()).getCodigo());
                if(listadoCentroCostoAuxiliar != null){
                    String datosObjeto[] = new String[listadoCentroCostoAuxiliar.size()];
                    int contador = 0;
                    for (CentroCostoAuxiliar listadoCentroCostoAuxiliar1 : listadoCentroCostoAuxiliar) {
                        datosObjeto[contador] = listadoCentroCostoAuxiliar1.getDescripcion();
                        contador++;
                    }
                    final DefaultComboBoxModel model = new DefaultComboBoxModel(datosObjeto);
                    select_MvtoCarbon_CCAuxiliar.setModel(model);
                }else{
                    listadoCentroCostoAuxiliar=null;
                    select_MvtoCarbon_CCAuxiliar.removeAllItems();
                }
            }catch (SQLException e){
            }
        }else{
            listadoCentroCostoSubCentro=null;
            select_MvtoCarbon_SubcentroCosto.removeAllItems();
            listadoCentroCostoAuxiliar=null;
            select_MvtoCarbon_CCAuxiliar.removeAllItems();
        }
    }//GEN-LAST:event_select_MvtoCarbon_SubcentroCostoItemStateChanged

    private void select_ZonaTrabajoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_select_ZonaTrabajoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_select_ZonaTrabajoItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JButton btn_registrar_insumo1;
    private javax.swing.JButton btn_registrar_insumo2;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> select_MvtoCarbon_CCAuxiliar;
    private javax.swing.JComboBox<String> select_MvtoCarbon_CO;
    private javax.swing.JComboBox<String> select_MvtoCarbon_SubcentroCosto;
    private javax.swing.JComboBox<String> select_ZonaTrabajo;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel titulo25;
    private javax.swing.JLabel titulo26;
    private javax.swing.JLabel titulo29;
    private javax.swing.JLabel titulo45;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta) throws SQLException{      
    DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Centro Operación","Zona Trabajo","CentroCosto_Subcentro","Centro_Costo_Auxiliar","Estado"});  
        listarLista_Cc_auxiliar_ZonaTrabajo =new ControlDB_ZonaTrabajo(tipoConexion).listarLista_Cc_auxiliar_ZonaTrabajo(valorConsulta);
        for(ListadoSitiosZonaTrabajo Objeto: listarLista_Cc_auxiliar_ZonaTrabajo){
            String[] registro = new String[5];
            registro[0]=""+Objeto.getCentroCostoAuxiliar().getCentroCostoSubCentro().getCentroOperacion().getDescripcion();
            registro[1]=""+Objeto.getZonaTrabajo().getDescripcion();
            registro[2]=""+Objeto.getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
            registro[3]=""+Objeto.getCentroCostoAuxiliar().getDescripcion();
            if(Objeto.getEstado().equals("1")){
                registro[4]="ACTIVO";
            }else{
                registro[4]="INACTIVO";
            }
            modelo.addRow(registro);   
        }
        tabla.setModel(modelo);
    }
}
