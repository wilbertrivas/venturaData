package ModuloPalero.View;
    
import Catalogo.Controller.ControlDB_Equipo;
import Catalogo.Model.BaseDatos;
import Catalogo.Model.TipoArticulo;
import ModuloCarbon.View.MvtoCarbon_GenerarMatriz;
import ModuloPalero.Controller.ControlDB_ConfiguracionLiquidacion;
import ModuloPalero.Controller.ControlDB_Marcacion;
import ModuloPalero.Model.ConfiguracionLiquidacion;
import ModuloPersonal.Controller.ControlDB_CargoNomina;
import ModuloPersonal.Model.CargoNomina;
import ModuloPersonal.View.Persona_Registrar;
import Sistema.Model.Usuario;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import utilities.ModeloDeTabla;
import utilities.PaginadorDeTabla;
import utilities.ProveedorDeDatosDePaginacion;

public final class ConfiguracionLiquidacion_Actualizar extends javax.swing.JPanel implements ActionListener, TableModelListener{
    Usuario user;
    private String tipoConexion;
    ArrayList<TipoArticulo> listadoTipoArticulo = new ArrayList();
    ArrayList<BaseDatos> listadoBaseDatos= new ArrayList();
    private ArrayList<CargoNomina> listadoCargoNomina;
    private PaginadorDeTabla<ConfiguracionLiquidacion> paginadorDeTabla;  
    ProveedorDeDatosDePaginacion<ConfiguracionLiquidacion> proveedorDeDatos;
    ArrayList<String> encabezadoTabla=null;
    ArrayList<ConfiguracionLiquidacion> listado=null;
    ConfiguracionLiquidacion configuracionLiquidacion= null;
    
    
    public ConfiguracionLiquidacion_Actualizar(Usuario us,String tipoConexion) {
       
        initComponents();
         user=us;
        this.tipoConexion= tipoConexion;
        
        //Cargamos en la interfaz los tipo de cargos según nomina
        try {
            listadoCargoNomina=new ControlDB_CargoNomina(tipoConexion).buscarActivos();
            for(CargoNomina objeto: listadoCargoNomina){
                select_cargoNomina.addItem(objeto.getDescripcion());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Persona_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } 
        encabezadoTabla= new ArrayList<String>();
        pageJComboBox.show(false);
        codigoConfiguracion.setEditable(false);
        descripcion.setEditable(false);
        select_cargoNomina.setEnabled(false);
        fechaInicio_Regsitro.setEnabled(false);
        fechaFin_Registro.setEnabled(false);
        valorTonelada.setEditable(false);
        cantidadToneladaQuincena.setEditable(false);
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Enable = new javax.swing.JMenuItem();
        valorTonelada = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        fechaInicio_Regsitro = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        fechaFin_Registro = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        cantidadToneladaQuincena = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        fechaInicio_Consulta = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        fechaFin_Consulta = new com.toedter.calendar.JDateChooser();
        consultar1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        select_cargoNomina = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        pageJComboBox = new javax.swing.JComboBox<>();
        paginationPanel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        codigoConfiguracion = new javax.swing.JTextField();
        descripcion = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        Enable.setText("Seleccionar");
        Enable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnableActionPerformed(evt);
            }
        });
        Seleccionar.add(Enable);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        valorTonelada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorToneladaActionPerformed(evt);
            }
        });
        add(valorTonelada, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 410, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Estado:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 160, 30));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        estado.setToolTipText("");
        add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 410, 30));

        btnRegistrar.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Ingresar.png"))); // NOI18N
        btnRegistrar.setText("ACTUALIZAR");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, 140, 40));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 370, 140, 40));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 90, 880, 370));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Descripción:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 130, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Fecha Inicio:");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 100, 30));

        fechaInicio_Regsitro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicio_RegsitroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicio_RegsitroMouseEntered(evt);
            }
        });
        add(fechaInicio_Regsitro, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 170, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Fecha Fin:");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, -1, 30));

        fechaFin_Registro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFin_RegistroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFin_RegistroMouseEntered(evt);
            }
        });
        add(fechaFin_Registro, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, 170, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Cant. Tonelada Salario:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 180, 30));

        cantidadToneladaQuincena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadToneladaQuincenaActionPerformed(evt);
            }
        });
        add(cantidadToneladaQuincena, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 390, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CONSULTA DE CONFIGURACIONES DE  LIQUIDACIONES REGISTRADAS EN EL SISTEMA");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 880, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("FECHA INICIO:");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, -1, 30));

        fechaInicio_Consulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicio_ConsultaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicio_ConsultaMouseEntered(evt);
            }
        });
        add(fechaInicio_Consulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 50, 170, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 65)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("|");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 50, 30, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("FECHA FIN:");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 50, -1, 30));

        fechaFin_Consulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFin_ConsultaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFin_ConsultaMouseEntered(evt);
            }
        });
        add(fechaFin_Consulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 50, 170, 30));

        consultar1.setBackground(new java.awt.Color(255, 255, 255));
        consultar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consultar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/consultar.png"))); // NOI18N
        consultar1.setText("CONSULTAR");
        consultar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                consultar1MouseExited(evt);
            }
        });
        consultar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultar1ActionPerformed(evt);
            }
        });
        add(consultar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 40, 160, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, 880, 420));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Código:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 150, 30));

        select_cargoNomina.setToolTipText("");
        add(select_cargoNomina, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 410, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("CONFIGURACIÓN LIQUIDACIÓN");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 620, 30));

        pageJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pageJComboBoxActionPerformed(evt);
            }
        });
        add(pageJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(1410, 10, 60, 40));

        paginationPanel.setBackground(new java.awt.Color(255, 255, 255));
        add(paginationPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 460, 740, 80));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Cargo Nomina:");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 150, 30));

        codigoConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoConfiguracionActionPerformed(evt);
            }
        });
        add(codigoConfiguracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 410, 30));

        descripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descripcionActionPerformed(evt);
            }
        });
        add(descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 410, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Valor Tonelada:");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 130, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 620, 420));
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if(codigoConfiguracion.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Debe cargar una configuración de liquidación para poder actualizarla", "Error!", JOptionPane.ERROR_MESSAGE);
        }else{
            if (configuracionLiquidacion.getEstado().equals("ACTIVO") && estado.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "No se realizó cambio sobre la configuración de liquidación, por tal motivo no se actualizará", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (configuracionLiquidacion.getEstado().equals("INACTIVO") && estado.getSelectedIndex() == 1) {
                    JOptionPane.showMessageDialog(null, "No se realizó cambio sobre la configuración de liquidación, por tal motivo no se actualizará", "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    //Se realizo un cambio sobre el estado por este motivo vamos a actualizar el registro de la configuración de liquidación seleccionado.
                    int respuesta;
                    try {
                        respuesta = new ControlDB_ConfiguracionLiquidacion(tipoConexion).actualizar(configuracionLiquidacion, user);
                        if (respuesta == 1) {
                            JOptionPane.showMessageDialog(null, "Se actualizo el archivo de forma exitosa");
                            codigoConfiguracion.setText("");
                            select_cargoNomina.setSelectedIndex(0);
                            fechaInicio_Regsitro.setCalendar(null);
                            fechaFin_Registro.setCalendar(null);
                            descripcion.setText("");
                            valorTonelada.setText("");
                            cantidadToneladaQuincena.setText("");
                            estado.setSelectedIndex(0); 

                            paginationPanel.removeAll();
                            validar();
                            generarListadoMvtoCarbon();
                        } else {
                            if (respuesta == 0) {
                                JOptionPane.showMessageDialog(null, "No se pudo actualizar la configuración de liquidación seleccioanda, valide la información suministrada");
                            }
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Marcacion_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(Marcacion_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SocketException ex) {
                        Logger.getLogger(Marcacion_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }  
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        codigoConfiguracion.setText("");
        select_cargoNomina.setSelectedIndex(0);
        fechaInicio_Regsitro.setCalendar(null);
        fechaFin_Registro.setCalendar(null);
        descripcion.setText("");
        valorTonelada.setText("");
        cantidadToneladaQuincena.setText("");
        estado.setSelectedIndex(0); 
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void fechaInicio_RegsitroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicio_RegsitroMouseClicked
        
    }//GEN-LAST:event_fechaInicio_RegsitroMouseClicked

    private void fechaInicio_RegsitroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicio_RegsitroMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaInicio_RegsitroMouseEntered

    private void fechaFin_RegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFin_RegistroMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFin_RegistroMouseClicked

    private void fechaFin_RegistroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFin_RegistroMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFin_RegistroMouseEntered

    private void valorToneladaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorToneladaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valorToneladaActionPerformed

    private void cantidadToneladaQuincenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadToneladaQuincenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadToneladaQuincenaActionPerformed

    private void fechaInicio_ConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicio_ConsultaMouseClicked

    }//GEN-LAST:event_fechaInicio_ConsultaMouseClicked

    private void fechaInicio_ConsultaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicio_ConsultaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaInicio_ConsultaMouseEntered

    private void fechaFin_ConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFin_ConsultaMouseClicked

    }//GEN-LAST:event_fechaFin_ConsultaMouseClicked

    private void fechaFin_ConsultaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFin_ConsultaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFin_ConsultaMouseEntered

    private void consultar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultar1MouseExited

    }//GEN-LAST:event_consultar1MouseExited

    private void consultar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultar1ActionPerformed
        paginationPanel.removeAll();
        validar();
        generarListadoMvtoCarbon();
    }//GEN-LAST:event_consultar1ActionPerformed

    private void pageJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pageJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pageJComboBoxActionPerformed

    private void codigoConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoConfiguracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoConfiguracionActionPerformed

    private void EnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnableActionPerformed
        // TODO lo del clik derechoo
        int fila1;
        try{
            fila1=tabla.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ningun Vehiculo");
            }
            else{
                int posicion = paginadorDeTabla.getPosicionador();
                if(posicion != -1){
                    fila1 = fila1 +posicion;
                }
                if(listado != null){
                    configuracionLiquidacion=null;
                    //Borramos los textos de los campos
                    codigoConfiguracion.setText("");
                    select_cargoNomina.setSelectedIndex(0);
                    fechaInicio_Regsitro.setCalendar(null);
                    fechaFin_Registro.setCalendar(null);
                    descripcion.setText("");
                    valorTonelada.setText("");
                    cantidadToneladaQuincena.setText("");
                    estado.setSelectedIndex(0);

                    //Procedemos a cargar la información según selección
                    configuracionLiquidacion = listado.get(fila1);
                    if(configuracionLiquidacion != null){
                        //validamos si la configuracionLiquidacion presenta movimiento,
                        boolean validarConfiguracionLiquidacionPresentaMovimiento= new ControlDB_ConfiguracionLiquidacion(tipoConexion).validarConfiguracionLiquidacionPresentaLiquidacion(configuracionLiquidacion);
                        if(validarConfiguracionLiquidacionPresentaMovimiento){//La configuración de liquidación presenta movimiento no se puede modificar
                            JOptionPane.showMessageDialog(null, "La configuración de Liquidación seleccionada ya tiene asociada una liquidación por tal motivo no se puede actualizar","Advertencia",JOptionPane.INFORMATION_MESSAGE);
                        }else{//Al no presentar movimiento de liquidación podemos actualizar los campos.
                            codigoConfiguracion.setText(configuracionLiquidacion.getCodigo());

                            //Cargamos el cargo de nomina según la configuración de liquidación seleccionada
                            int contador =0;
                            for(CargoNomina objeto : listadoCargoNomina){
                                if(objeto.getCodigo().equals(configuracionLiquidacion.getCargoNomina().getCodigo())){
                                    select_cargoNomina.setSelectedIndex(contador);

                                }
                                contador++;
                            }
                            //Cargamos las fechas
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date fechaInicio=dateFormat.parse(configuracionLiquidacion.getFechaInicio());
                            fechaInicio_Regsitro.setDate(fechaInicio);

                            Date fechaFin=dateFormat.parse(configuracionLiquidacion.getFechaFinalizacion());
                            fechaFin_Registro.setDate(fechaFin);
                            valorTonelada.setText(""+configuracionLiquidacion.getValorTonelada());
                            descripcion.setText(""+configuracionLiquidacion.getDescripcion());
                            cantidadToneladaQuincena.setText(""+configuracionLiquidacion.getCantidadToneladaSalario());
                            if(configuracionLiquidacion.getEstado().equals("ACTIVO")){
                                estado.setSelectedIndex(0);
                            }else{
                                estado.setSelectedIndex(1);
                            }
                        }
                    }
                }  
            }   
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_EnableActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() == 2) {
            int fila1;
            try {
                fila1 = tabla.getSelectedRow();
                if (fila1 == -1) {
                    JOptionPane.showMessageDialog(null, "no se ha seleccionando ningun Vehiculo");
                } else {
                    int posicion = paginadorDeTabla.getPosicionador();
                    if (posicion != -1) {
                        fila1 = fila1 + posicion;
                    }
                    if (listado != null) {
                        configuracionLiquidacion = null;
                        
                        //Borramos los textos de los campos
                        codigoConfiguracion.setText("");
                        select_cargoNomina.setSelectedIndex(0);
                        fechaInicio_Regsitro.setCalendar(null);
                        fechaFin_Registro.setCalendar(null);
                        descripcion.setText("");
                        valorTonelada.setText("");
                        cantidadToneladaQuincena.setText("");
                        estado.setSelectedIndex(0);

                        //Procedemos a cargar la información según selección
                        configuracionLiquidacion = listado.get(fila1);
                        //validamos si la configuracionLiquidacion presenta movimiento,
                        boolean validarConfiguracionLiquidacionPresentaMovimiento= new ControlDB_ConfiguracionLiquidacion(tipoConexion).validarConfiguracionLiquidacionPresentaLiquidacion(configuracionLiquidacion);
                        if(validarConfiguracionLiquidacionPresentaMovimiento){//La configuración de liquidación presenta movimiento no se puede modificar
                            JOptionPane.showMessageDialog(null, "La configuración de Liquidación seleccionada ya tiene asociada una liquidación por tal motivo no se puede actualizar","Advertencia",JOptionPane.INFORMATION_MESSAGE);
                        }else{//Al no presentar movimiento de liquidación podemos actualizar los campos.  
                            if (configuracionLiquidacion != null) {
                                codigoConfiguracion.setText(configuracionLiquidacion.getCodigo());

                                //Cargamos el cargo de nomina según la configuración de liquidación seleccionada
                                int contador = 0;
                                for (CargoNomina objeto : listadoCargoNomina) {
                                    if (objeto.getCodigo().equals(configuracionLiquidacion.getCargoNomina().getCodigo())) {
                                        select_cargoNomina.setSelectedIndex(contador);

                                    }
                                    contador++;
                                }
                                //Cargamos las fechas
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                Date fechaInicio = dateFormat.parse(configuracionLiquidacion.getFechaInicio());
                                fechaInicio_Regsitro.setDate(fechaInicio);

                                Date fechaFin = dateFormat.parse(configuracionLiquidacion.getFechaFinalizacion());
                                fechaFin_Registro.setDate(fechaFin);
                                valorTonelada.setText("" + configuracionLiquidacion.getValorTonelada());
                                descripcion.setText("" + configuracionLiquidacion.getDescripcion());
                                cantidadToneladaQuincena.setText("" + configuracionLiquidacion.getCantidadToneladaSalario());
                                if (configuracionLiquidacion.getEstado().equals("ACTIVO")) {
                                    estado.setSelectedIndex(0);
                                } else {
                                    estado.setSelectedIndex(1);
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void descripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descripcionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Enable;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JTextField cantidadToneladaQuincena;
    private javax.swing.JTextField codigoConfiguracion;
    private javax.swing.JButton consultar1;
    private javax.swing.JTextField descripcion;
    private javax.swing.JComboBox<String> estado;
    private com.toedter.calendar.JDateChooser fechaFin_Consulta;
    private com.toedter.calendar.JDateChooser fechaFin_Registro;
    private com.toedter.calendar.JDateChooser fechaInicio_Consulta;
    private com.toedter.calendar.JDateChooser fechaInicio_Regsitro;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<Integer> pageJComboBox;
    public javax.swing.JPanel paginationPanel;
    private javax.swing.JComboBox<String> select_cargoNomina;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField valorTonelada;
    // End of variables declaration//GEN-END:variables
    /*public void tabla_Listar(String valorConsulta) throws SQLException{
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
    }*/
    
    public void generarListadoMvtoCarbon(){
        try{
            String fecha_Inicial="";
            String fecha_Final="";
            int contador=0;
            try{
                Calendar fechaI = fechaInicio_Consulta.getCalendar();
                String anoI = ""+fechaI.get(Calendar.YEAR);
                String mesI = "";
                if((fechaI.get(Calendar.MONTH) +1) <=9){
                    mesI = "0"+(fechaI.get(Calendar.MONTH) + 1);
                }else{
                    mesI = ""+(fechaI.get(Calendar.MONTH) + 1);
                }
                String diaI = "";
                if(fechaI.get(Calendar.DAY_OF_MONTH) <=9){
                    diaI = "0"+fechaI.get(Calendar.DAY_OF_MONTH);
                }else{
                    diaI = ""+fechaI.get(Calendar.DAY_OF_MONTH);
                }
                fecha_Inicial=anoI+"-"+mesI+"-"+diaI;
                contador++;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Verifique la fecha de inicio de la consulta", "Error!!.", JOptionPane.ERROR_MESSAGE);
            }
            try{
                Calendar fechaF = fechaFin_Consulta.getCalendar();
                String anoF = ""+fechaF.get(Calendar.YEAR);
                String mesF = "";
                if((fechaF.get(Calendar.MONTH) +1) <=9){
                                mesF = "0"+(fechaF.get(Calendar.MONTH) + 1);
                }else{
                    mesF = ""+(fechaF.get(Calendar.MONTH) + 1);
                }
                String diaF = "";
                if(fechaF.get(Calendar.DAY_OF_MONTH) <=9){
                    diaF = "0"+fechaF.get(Calendar.DAY_OF_MONTH);
                }else{
                    diaF = ""+fechaF.get(Calendar.DAY_OF_MONTH);
                }
                fecha_Final=anoF+"-"+mesF+"-"+diaF;
                contador++;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Verifique la fecha de fin de la consulta", "Error!!.", JOptionPane.ERROR_MESSAGE);
            } 
            if(contador==2){//Se validaron las dos fechas y contienen formato correcto
                try {
                    int resultDosFechas=Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorEntreDosFechas("'"+fecha_Inicial+"'","'"+fecha_Final+"'"));
                    if(resultDosFechas < 0){
                        JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser mayor a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                    }else{
                        /*if(resultDosFechas ==0){
                            JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser Igual a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                        }else{*/
                            tabla_Listar(fecha_Inicial,fecha_Final);
                        //}
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MvtoCarbon_GenerarMatriz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"No se puedo procesar el descargue de Carbon", "Advertencia",JOptionPane.ERROR_MESSAGE);
        }   
    }
    public void tabla_Listar(String data1, String data2) throws SQLException{
        tabla.setModel(crearModeloDeTabla());
        listado=new ControlDB_ConfiguracionLiquidacion(tipoConexion).buscarConfiguracionesLiquidaciones(data1, data2);
        if(listado != null){
            proveedorDeDatos = crearProveedorDeDatos(listado); 
            paginadorDeTabla = new PaginadorDeTabla(tabla, proveedorDeDatos, new int[]{5, 10, 20, 50, 75, 100}, 10);
            paginadorDeTabla.crearListadoDeFilasPermitidas(this.paginationPanel);
            pageJComboBox = paginadorDeTabla.getComboBoxPage();
            events();
            pageJComboBox.setSelectedItem(Integer.parseInt("50"));
            resizeColumnWidth(tabla);
        }
        
    }
    public final void events(){
       pageJComboBox.addActionListener(this);
       tabla.getModel().addTableModelListener(this);
    }
    private ModeloDeTabla crearModeloDeTabla() {
        return new ModeloDeTabla<ConfiguracionLiquidacion>() {            
            @Override
            public Object getValueAt(ConfiguracionLiquidacion listado1, int columnas) {
               switch(encabezadoTabla.get(columnas)){
                    case "Código":{
                       return listado1.getCodigo();
                    }
                    case "CargoNomina":{
                       return listado1.getCargoNomina().getDescripcion();
                    }
                    case "Fecha_Inicio":{
                       return listado1.getFechaInicio();
                    }
                    case "Fecha_Fin":{
                       return listado1.getFechaFinalizacion();
                    }
                    case "Cantidad_Días":{
                       return listado1.getCantidadDias();
                    }
                    case "Descripción":{
                       return listado1.getDescripcion();
                    }
                    case "Valor_Tonelada":{
                       return listado1.getValorTonelada();
                    }
                    case "Cantiddad_Tonelada_Salario":{
                       return listado1.getCantidadToneladaSalario();
                    }
                    case "Usuario":{
                       return listado1.getUsuario().getNombres()+ " "+ listado1.getUsuario().getApellidos();
                    }
                    case "Estado":{
                       return listado1.getEstado();
                    }
               }
               return null;
            }

            @Override
            public String getColumnName(int columnas) {
                return encabezadoTabla.get(columnas);
            }

            @Override
            public int getColumnCount() {
                return encabezadoTabla.size();
            }

        }; 
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object evt = e.getSource();
        paginadorDeTabla.eventCombobBox(pageJComboBox);
    }
    @Override
    public void tableChanged(TableModelEvent e) {
        paginadorDeTabla.refreshPageButtonPanel();
    }
    public void validar(){
        encabezadoTabla= new ArrayList<>();
        encabezadoTabla.add("Código");
        encabezadoTabla.add("CargoNomina");
        encabezadoTabla.add("Fecha_Inicio");
        encabezadoTabla.add("Fecha_Fin");
        encabezadoTabla.add("Cantidad_Días");
        encabezadoTabla.add("Descripción");
        encabezadoTabla.add("Valor_Tonelada");
        encabezadoTabla.add("Cantiddad_Tonelada_Salario");
        encabezadoTabla.add("Usuario");
        encabezadoTabla.add("Estado");
        
    }
    private ProveedorDeDatosDePaginacion<ConfiguracionLiquidacion> crearProveedorDeDatos(final ArrayList<ConfiguracionLiquidacion> model) {
        //Obtenemos el listado de registros existentes haciendo una consulta a la base de datos.
        
        //Retornamos un interfaz de tipo ProveedorDeDatosDePaginacion en la cual sobreescribimos sus metodos abtractos
        //1 metodo: obtenemos el numero total de registros agregados al JTable.
        //2 metodo: obtenemos una subLista la cual será mostrada en el JTable, seria nuestra pagina actual.
        return new ProveedorDeDatosDePaginacion<ConfiguracionLiquidacion>() {
            @Override
            public int getTotalRowCount() {
                return model.size();
            }

            @Override
            public List<ConfiguracionLiquidacion> getRows(int startIndex, int endIndex) {
                return model.subList(startIndex, endIndex);
            }
        };
    }
    //Ajustar aNcho de las tablas de acuerdo al contenido
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
