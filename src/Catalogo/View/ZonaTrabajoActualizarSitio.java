package Catalogo.View;
   
import Catalogo.Controller.ControlDB_Articulo;
import Catalogo.Controller.ControlDB_BaseDatos;
import Catalogo.Controller.ControlDB_CentroCostoSubCentro;
import Catalogo.Controller.ControlDB_CentroOperacion;
import Catalogo.Controller.ControlDB_TipoArticulo;
import Catalogo.Controller.ControlDB_ZonaTrabajo;
import Catalogo.Model.Articulo;
import Catalogo.Model.BaseDatos;
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import Catalogo.Model.ListadoSitiosZonaTrabajo;
import Catalogo.Model.TipoArticulo;
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

public final class ZonaTrabajoActualizarSitio extends javax.swing.JPanel {
    Usuario user;
    private String tipoConexion;
    private ArrayList<CentroOperacion> listadoCentroOperacion = null;
    private ArrayList<CentroCostoSubCentro> listadoCentroCostoSubCentro = null;
    private ArrayList<CentroCostoAuxiliar> listadoCentroCostoAuxiliar =null;
    private ArrayList<ZonaTrabajo> list_zonaTrabajo =null;
    private ArrayList<ListadoSitiosZonaTrabajo> listarLista_Cc_auxiliar_ZonaTrabajo;
    private ListadoSitiosZonaTrabajo sitio=null;
    public ZonaTrabajoActualizarSitio(Usuario us,String tipoConexion) {
        
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
        
        select_MvtoCarbon_CO.setEnabled(false);
        select_MvtoCarbon_SubcentroCosto.setEnabled(false);
        select_MvtoCarbon_CCAuxiliar.setEnabled(false);
        select_ZonaTrabajo.setEnabled(false);
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
        alerta_nombre = new javax.swing.JLabel();
        titulo29 = new javax.swing.JLabel();
        select_MvtoCarbon_CO = new javax.swing.JComboBox<>();
        titulo26 = new javax.swing.JLabel();
        select_MvtoCarbon_SubcentroCosto = new javax.swing.JComboBox<>();
        titulo45 = new javax.swing.JLabel();
        select_MvtoCarbon_CCAuxiliar = new javax.swing.JComboBox<>();
        titulo25 = new javax.swing.JLabel();
        select_ZonaTrabajo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ACTUALIZACIÓN DE SITIOS POR ZONA DE TRABAJO");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1290, 30));

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
        add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, 140, 40));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 360, 140, 40));

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
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 620, 40));

        btnConsultar.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        btnConsultar.setText("CONSULTAR");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 50, 140, 40));

        alerta_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_nombre.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 460, 380, 20));

        titulo29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo29.setForeground(new java.awt.Color(51, 51, 51));
        titulo29.setText("CENTRO OPERACIÓN:");
        titulo29.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, 30));

        select_MvtoCarbon_CO.setToolTipText("");
        select_MvtoCarbon_CO.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoCarbon_COItemStateChanged(evt);
            }
        });
        add(select_MvtoCarbon_CO, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 320, 30));

        titulo26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo26.setForeground(new java.awt.Color(51, 51, 51));
        titulo26.setText("SUBCENTRO COSTO:");
        titulo26.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 130, 30));

        select_MvtoCarbon_SubcentroCosto.setToolTipText("");
        select_MvtoCarbon_SubcentroCosto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_MvtoCarbon_SubcentroCostoItemStateChanged(evt);
            }
        });
        add(select_MvtoCarbon_SubcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 320, 30));

        titulo45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo45.setForeground(new java.awt.Color(51, 51, 51));
        titulo45.setText("C.C. AUXILIAR ORIGEN:");
        titulo45.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo45, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 150, 30));

        select_MvtoCarbon_CCAuxiliar.setToolTipText("");
        add(select_MvtoCarbon_CCAuxiliar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 320, 30));

        titulo25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo25.setForeground(new java.awt.Color(51, 51, 51));
        titulo25.setText("ZONA DE TRABAJO:");
        titulo25.setPreferredSize(new java.awt.Dimension(133, 15));
        add(titulo25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 130, 30));

        select_ZonaTrabajo.setToolTipText("");
        select_ZonaTrabajo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                select_ZonaTrabajoItemStateChanged(evt);
            }
        });
        add(select_ZonaTrabajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 320, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Estado");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 120, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 320, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1290, 60));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 530, 470));
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        valorBusqueda.setText("");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if(sitio == null){
            JOptionPane.showMessageDialog(null, "Error.. Se debe carga un sitio a ser actualizado", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            boolean validar= true;
            if(estado.getSelectedIndex()==0){//Seleccionó activo
                if(sitio.getEstado().equals("0")){//El estado incial estaba inactivo, por tal motivo podemos actualizar porque hay cambios
                    validar=true;
                    sitio.setEstado("1");
                }else{
                    validar=false;
                }
            }else{//Seleccionó inactivo
                if(sitio.getEstado().equals("1")){//El estado incial estaba activo, por tal motivo podemos actualizar porque hay cambios
                    validar=true;
                    sitio.setEstado("0");
                }else{
                    validar=false;
                }
            }
            if(validar){//Procedemos a actualizar el estado dle sitio
                try{
                    int respuesta=new ControlDB_ZonaTrabajo(tipoConexion).actualizarSitioZonaTrabajo(sitio, user);
                    if(respuesta==1){
                        try {
                            tabla_Listar(valorBusqueda.getText());
                        } catch (SQLException ex) {
                            Logger.getLogger(ZonaTrabajoActualizarSitio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JOptionPane.showMessageDialog(null, "Se actualizo el sitio de forma exitosa");
                    }else{
                        if(respuesta==0){
                            JOptionPane.showMessageDialog(null, "No se pudo actualizar el sitio, valide los datos ingresados");
                        }
                    }    
                }catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Error al registrar el articulo");
                    Logger.getLogger(ZonaTrabajoActualizarSitio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(ZonaTrabajoActualizarSitio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SocketException ex) {
                    Logger.getLogger(ZonaTrabajoActualizarSitio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "No se han realizado cambios para proceder a actualizar", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            }
       }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        try {
            tabla_Listar(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(ZonaTrabajoActualizarSitio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        int fila1;
        try{
            fila1=tabla.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                //DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                sitio=listarLista_Cc_auxiliar_ZonaTrabajo.get(fila1);
                //cargamos el Centro de Operación según la selección.
                try{
                    int contador=0;
                    for(CentroOperacion centroOperacion: listadoCentroOperacion){
                        if(centroOperacion.getCodigo()== sitio.getCentroCostoAuxiliar().getCentroCostoSubCentro().getCentroOperacion().getCodigo()){
                              select_MvtoCarbon_CO.setSelectedIndex(contador);  
                        }
                        contador++;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                 //cargamos el subcentro de costo según la consulta
                try{
                    int contador=0;
                    for(CentroCostoSubCentro centroCostoSubCentro: listadoCentroCostoSubCentro){
                        if(centroCostoSubCentro.getCodigo()== sitio.getCentroCostoAuxiliar().getCentroCostoSubCentro().getCodigo()){
                              select_MvtoCarbon_SubcentroCosto.setSelectedIndex(contador);  
                        }
                        contador++;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                //cargamos los auxiliares de Centro de costos según la consulta
                try{
                    int contador=0;
                    for(CentroCostoAuxiliar centroCostoAuxiliar: listadoCentroCostoAuxiliar){
                        if(centroCostoAuxiliar.getCodigo() ==  sitio.getCentroCostoAuxiliar().getCodigo()){
                              select_MvtoCarbon_CCAuxiliar.setSelectedIndex(contador);  
                        }
                        contador++;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                
                //cargamos los auxiliares de Centro de costos según la consulta
                try{
                    int contador=0;
                    for(ZonaTrabajo zonaTrabajo: list_zonaTrabajo){
                        if(zonaTrabajo.getCodigo().equals(sitio.getZonaTrabajo().getCodigo())){
                              select_ZonaTrabajo.setSelectedIndex(contador);  
                        }
                        contador++;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                //Cargamos el estado
                if(sitio.getEstado().equals("1")){
                    estado.setSelectedIndex(0);
                }else{
                    estado.setSelectedIndex(1);
                } 
                estado.setEnabled(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }    
    }//GEN-LAST:event_EditarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if(evt.getClickCount() == 2) {
            int fila1;
            try{
                fila1=tabla.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    //DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                    sitio=listarLista_Cc_auxiliar_ZonaTrabajo.get(fila1);
                    //cargamos el Centro de Operación según la selección.
                    try{
                        int contador=0;
                        for(CentroOperacion centroOperacion: listadoCentroOperacion){
                            if(centroOperacion.getCodigo()== sitio.getCentroCostoAuxiliar().getCentroCostoSubCentro().getCentroOperacion().getCodigo()){
                                  select_MvtoCarbon_CO.setSelectedIndex(contador);  
                            }
                            contador++;
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                     //cargamos el subcentro de costo según la consulta
                    try{
                        int contador=0;
                        for(CentroCostoSubCentro centroCostoSubCentro: listadoCentroCostoSubCentro){
                            if(centroCostoSubCentro.getCodigo()== sitio.getCentroCostoAuxiliar().getCentroCostoSubCentro().getCodigo()){
                                  select_MvtoCarbon_SubcentroCosto.setSelectedIndex(contador);  
                            }
                            contador++;
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    //cargamos los auxiliares de Centro de costos según la consulta
                    try{
                        int contador=0;
                        for(CentroCostoAuxiliar centroCostoAuxiliar: listadoCentroCostoAuxiliar){
                            if(centroCostoAuxiliar.getCodigo() ==  sitio.getCentroCostoAuxiliar().getCodigo()){
                                  select_MvtoCarbon_CCAuxiliar.setSelectedIndex(contador);  
                            }
                            contador++;
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                    //cargamos los auxiliares de Centro de costos según la consulta
                    try{
                        int contador=0;
                        for(ZonaTrabajo zonaTrabajo: list_zonaTrabajo){
                            if(zonaTrabajo.getCodigo().equals(sitio.getZonaTrabajo().getCodigo())){
                                  select_ZonaTrabajo.setSelectedIndex(contador);  
                            }
                            contador++;
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    //Cargamos el estado
                    if(sitio.getEstado().equals("1")){
                        estado.setSelectedIndex(0);
                    }else{
                        estado.setSelectedIndex(1);
                    } 
                    estado.setEnabled(true);
                }
            }catch(Exception e){
                e.printStackTrace();
            }     
        }   
    }//GEN-LAST:event_tablaMouseClicked

    private void btnActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseExited
        alerta_nombre.setText("");
    }//GEN-LAST:event_btnActualizarMouseExited

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
    private javax.swing.JLabel alerta_nombre;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JTextField valorBusqueda;
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
