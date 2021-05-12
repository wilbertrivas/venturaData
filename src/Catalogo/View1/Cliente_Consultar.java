package Catalogo.View1;
 
import Catalogo.Controller.ControlDB_Cliente;
import Catalogo.Model.Cliente;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public final class Cliente_Consultar extends javax.swing.JPanel {

    DefaultTableModel modeloCliente;
    DefaultTableModel modeloInformacionHistoricoRecobro;
    String [] tituloCliente= {"Código", "Nombre","Estado", "Valor Recobro","Origen de Datos"};
    String [] tituloInformacionHistoricoRecobro= {"Valor del recobro", "Fecha de Registro del valor del recobro","Usuario Quien registró recobro"};
    String[]  registroCliente;  
    String[]  registroInformacionHistoricoRecobro;  
    Usuario user;
    private String tipoConexion;
    public Cliente_Consultar(Usuario u,String tipoConexion) {
        initComponents();
        user= u;
        this.tipoConexion= tipoConexion;
        try {
            tabla_ListarClientes("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los clientes");
            Logger.getLogger(Cliente_Consultar.class.getName()).log(Level.SEVERE, null, ex);
        }
        ventanaInterna_InformacionCliente.getContentPane().setBackground(Color.white);
        ventanaInterna_InformacionCliente.show(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        ventanaInterna_InformacionCliente = new javax.swing.JInternalFrame();
        informacionCliente_nombre = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        informacionCliente_codigo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_InformacionRecobroClientes = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        informacionCliente_estado = new javax.swing.JLabel();
        btn_consultar_cliente = new javax.swing.JButton();
        btn_cancelar_cliente1 = new javax.swing.JButton();
        valorBusqueda = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_clientes = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        Editar.setText("Mas Detalles");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 1450, 10));

        ventanaInterna_InformacionCliente.setBackground(new java.awt.Color(255, 255, 255));
        ventanaInterna_InformacionCliente.setClosable(true);
        ventanaInterna_InformacionCliente.setVisible(false);
        ventanaInterna_InformacionCliente.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                ventanaInterna_InformacionClienteInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        ventanaInterna_InformacionCliente.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        informacionCliente_nombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ventanaInterna_InformacionCliente.getContentPane().add(informacionCliente_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 720, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("ESTADO:");
        ventanaInterna_InformacionCliente.getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 90, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("CÓDIGO:");
        ventanaInterna_InformacionCliente.getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 80, 30));

        informacionCliente_codigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ventanaInterna_InformacionCliente.getContentPane().add(informacionCliente_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 720, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("INFORMACION DEL CLIENTE");
        ventanaInterna_InformacionCliente.getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 310, 30));

        tabla_InformacionRecobroClientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla_InformacionRecobroClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_InformacionRecobroClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_InformacionRecobroClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_InformacionRecobroClientes);

        ventanaInterna_InformacionCliente.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 182, 910, 370));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("NOMBRE:");
        ventanaInterna_InformacionCliente.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 90, 30));

        informacionCliente_estado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ventanaInterna_InformacionCliente.getContentPane().add(informacionCliente_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 720, 30));

        add(ventanaInterna_InformacionCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1180, 670));

        btn_consultar_cliente.setBackground(new java.awt.Color(255, 255, 255));
        btn_consultar_cliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_consultar_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        btn_consultar_cliente.setText("CONSULTAR");
        btn_consultar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultar_clienteActionPerformed(evt);
            }
        });
        add(btn_consultar_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, 140, 40));

        btn_cancelar_cliente1.setBackground(new java.awt.Color(255, 255, 255));
        btn_cancelar_cliente1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cancelar_cliente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btn_cancelar_cliente1.setText("CANCELAR");
        btn_cancelar_cliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_cliente1ActionPerformed(evt);
            }
        });
        add(btn_cancelar_cliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 140, 40));
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 450, 40));

        tabla_clientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla_clientes.setComponentPopupMenu(Seleccionar);
        tabla_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_clientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_clientes);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 1450, 590));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CONSULTAR CLIENTES");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 1450, 30));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ExportarExcel.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 70, 50, 40));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 102));
        jLabel14.setText("Exportar");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 60, 60, 10));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 102));
        jLabel16.setText("Correo");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 60, 90, 10));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/enviar_mail.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 70, 40, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 1450, 100));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_consultar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultar_clienteActionPerformed
        try {
            tabla_ListarClientes(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Cliente_Consultar.class.getName()).log(Level.SEVERE, null, ex);
        }
        //resizeColumnWidth(tabla_clientes);
    }//GEN-LAST:event_btn_consultar_clienteActionPerformed

    private void btn_cancelar_cliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_cliente1ActionPerformed
        valorBusqueda.setText("");
    }//GEN-LAST:event_btn_cancelar_cliente1ActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO lo del clik derechoo
        int fila1;
        try{
            fila1=tabla_clientes.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                modeloCliente=(DefaultTableModel)tabla_clientes.getModel();
                informacionCliente_codigo.setText((String)modeloCliente.getValueAt(fila1, 0));
                informacionCliente_nombre.setText((String)modeloCliente.getValueAt(fila1, 1));
                informacionCliente_estado.setText((String)modeloCliente.getValueAt(fila1, 2));     
                tabla_consultarHistoricoRecobros((String)modeloCliente.getValueAt(fila1, 0));
                ventanaInterna_InformacionCliente.show(true);
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void ventanaInterna_InformacionClienteInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_ventanaInterna_InformacionClienteInternalFrameClosed
        
    }//GEN-LAST:event_ventanaInterna_InformacionClienteInternalFrameClosed

    private void tabla_InformacionRecobroClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_InformacionRecobroClientesMouseClicked
        
    }//GEN-LAST:event_tabla_InformacionRecobroClientesMouseClicked

    private void tabla_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_clientesMouseClicked
        if (evt.getClickCount() == 2) {
            // TODO lo del clik derechoo
            int fila1;
            try{
                fila1=tabla_clientes.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    modeloCliente=(DefaultTableModel)tabla_clientes.getModel();
                    informacionCliente_codigo.setText((String)modeloCliente.getValueAt(fila1, 0));
                    informacionCliente_nombre.setText((String)modeloCliente.getValueAt(fila1, 1));
                    informacionCliente_estado.setText((String)modeloCliente.getValueAt(fila1, 2));     
                    tabla_consultarHistoricoRecobros((String)modeloCliente.getValueAt(fila1, 0));
                    ventanaInterna_InformacionCliente.show(true);
                }
            }catch(Exception e){
            }
        }
    }//GEN-LAST:event_tabla_clientesMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        JFileChooser selecArchivo= new JFileChooser();
        File archivo;
        if(selecArchivo.showDialog(null, "Exportar") ==JFileChooser.APPROVE_OPTION){
            archivo=new File(selecArchivo.getSelectedFile()+".xlsx");
            if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
                Workbook wb;
                String respuesta="No se realizó con exito la exportacion";

                //int numFila=tabla.getRowCount(), numColumna=tabla.getColumnCount();
                int numFila=tabla_clientes.getRowCount(), numColumna=tabla_clientes.getColumnCount();
                if(archivo.getName().endsWith("xls")){
                    wb = new HSSFWorkbook();
                }else{
                    wb= new XSSFWorkbook();
                }
                Sheet hoja= wb.createSheet("ClientesVenturaData");
                int costoTotalApuntador=300000;
                try{
                    for(int i= -1; i < numFila; i++ ){
                        Row fila= hoja.createRow(i+1);
                        for(int j=0; j< numColumna; j++){
                            Cell celda= fila.createCell(j);
                            if(i==-1){
                                celda.setCellValue(String.valueOf(tabla_clientes.getColumnName(j)));
                                String nameColumn=String.valueOf(tabla_clientes.getColumnName(j));
                                if(nameColumn.equals("ME_CostoTotal")){
                                    costoTotalApuntador=j;
                                }
                            }else{
                                try{
                                    String data=tabla_clientes.getValueAt(i, j).toString();
                                    String[] valor=String.valueOf(tabla_clientes.getValueAt(i, j)).split("-");

                                    if(valor.length==3){
                                        String[] valor2=valor[2].split(":");
                                        if(valor2.length >= 3){
                                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                            Date fechaM = dateFormat.parse(String.valueOf(data));
                                            CellStyle cellStyle = wb.createCellStyle();
                                            CreationHelper createHelper = wb.getCreationHelper();
                                            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yy h:mm:ss"));
                                            //cell = row.createCell(1);
                                            celda.setCellValue(fechaM);
                                            celda.setCellStyle(cellStyle);
                                        }else{
                                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                            Date fechaM = dateFormat.parse(String.valueOf(data));
                                            CellStyle cellStyle = wb.createCellStyle();
                                            CreationHelper createHelper = wb.getCreationHelper();
                                            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yy"));
                                            //cell = row.createCell(1);
                                            celda.setCellValue(fechaM);
                                            celda.setCellStyle(cellStyle);

                                            /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                            Date fechaM = dateFormat.parse(tabla.getValueAt(i, j).toString());
                                            celda.setCellValue(fechaM);*/
                                        }
                                    }else{
                                        //try{
                                          //  celda.setCellValue(Integer.parseInt(data));
                                        //}catch(Exception e){
                                            try{
                                                if(costoTotalApuntador == j){
                                                    celda.setCellValue(Double.parseDouble(String.valueOf(data).replace(",", ".")));
                                                }else{
                                                    celda.setCellValue(String.valueOf(data));
                                                }
                                            }catch(Exception ex){
                                                celda.setCellValue(String.valueOf(data));
                                            }
                                        //}
                                    }
                                }
                                catch(Exception e){
                                    celda.setCellValue(String.valueOf(tabla_clientes.getValueAt(i, j)));
                                }
                            }
                        }
                    }
                    for(int j=0; j<=numColumna; j++){
                        hoja.autoSizeColumn(j,true);
                    }
                    wb.write(new FileOutputStream(archivo));
                    respuesta= "Exportacion exitosa";
                }catch(Exception e){}
                JOptionPane.showMessageDialog(null, respuesta);
            }else{
                JOptionPane.showMessageDialog(null,"Elija un formato valido");
            }
        }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        if(user.getCorreo().equals("")){
            JOptionPane.showMessageDialog(null, "El usuario no tiene un correo configurado para el envio de correo, favor actualizar los datos");
        }else{
            File archivo;
            archivo= new File( "reportes/"+user.getCodigo()+"_"+"reporte_Clientes.xlsx");
            Workbook wb;
            String respuesta="No se realizó con exito la exportacion";
            int numFila=tabla_clientes.getRowCount(), numColumna=tabla_clientes.getColumnCount();
            if(archivo.getName().endsWith("xls")){
                wb = new HSSFWorkbook();
            }else{
                wb= new XSSFWorkbook();
            }
            Sheet hoja= wb.createSheet("ReporteVenturaData_Clientes");
            int costoTotalApuntador=300000;
            try{
                for(int i= -1; i < numFila; i++ ){
                    Row fila= hoja.createRow(i+1);
                    for(int j=0; j< numColumna; j++){
                        Cell celda= fila.createCell(j);
                        if(i==-1){
                            celda.setCellValue(String.valueOf(tabla_clientes.getColumnName(j)));
                            String nameColumn=String.valueOf(tabla_clientes.getColumnName(j));
                            if(nameColumn.equals("ME_CostoTotal")){
                                costoTotalApuntador=j;
                            }
                        }else{
                            try{
                                String data=tabla_clientes.getValueAt(i, j).toString();
                                String[] valor=String.valueOf(tabla_clientes.getValueAt(i, j)).split("-");

                                if(valor.length==3){
                                    String[] valor2=valor[2].split(":");
                                    if(valor2.length >= 3){
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        Date fechaM = dateFormat.parse(String.valueOf(data));
                                        CellStyle cellStyle = wb.createCellStyle();
                                        CreationHelper createHelper = wb.getCreationHelper();
                                        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yy h:mm:ss"));
                                        //cell = row.createCell(1);
                                        celda.setCellValue(fechaM);
                                        celda.setCellStyle(cellStyle);
                                    }else{
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                        Date fechaM = dateFormat.parse(String.valueOf(data));
                                        CellStyle cellStyle = wb.createCellStyle();
                                        CreationHelper createHelper = wb.getCreationHelper();
                                        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yy"));
                                        //cell = row.createCell(1);
                                        celda.setCellValue(fechaM);
                                        celda.setCellStyle(cellStyle);

                                        /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                        Date fechaM = dateFormat.parse(tabla.getValueAt(i, j).toString());
                                        celda.setCellValue(fechaM);*/
                                    }
                                }else{
                                    //try{
                                        //celda.setCellValue(Integer.parseInt(data));
                                    //}catch(Exception e){
                                        try{
                                            if(costoTotalApuntador == j){
                                                celda.setCellValue(Double.parseDouble(String.valueOf(data).replace(",", ".")));
                                            }else{
                                                celda.setCellValue(String.valueOf(data));
                                            }
                                        }catch(Exception ex){
                                            celda.setCellValue(String.valueOf(data));
                                        }
                                    //}
                                }
                            }
                            catch(Exception e){
                                celda.setCellValue(String.valueOf(tabla_clientes.getValueAt(i, j)));
                            }
                        }
                    }
                }
                for(int j=0; j<=numColumna; j++){
                    hoja.autoSizeColumn(j,true);
                }
                wb.write(new FileOutputStream(archivo));
                //respuesta= "Exportacion exitosa";
                respuesta= "Envío exitoso a "+user.getCorreo();
                String remitente = "venturadatavg";  //Para la dirección nomcuenta@gmail.com
                String clave = "VG#V3ntur4D4t4!#";  //Para la dirección nomcuenta@gmail.com

                Properties props = System.getProperties();
                props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
                props.put("mail.smtp.user", remitente);
                props.put("mail.smtp.clave", clave);    //La clave de la cuenta
                props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
                props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
                props.put("mail.smtp.port", "587");//El puerto SMTP seguro de Google

                Session session = Session.getDefaultInstance(props);
                MimeMessage message = new MimeMessage(session);

                try {
                    message.setFrom(new InternetAddress(remitente));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getCorreo()));
                    BodyPart texto = new MimeBodyPart();
                    texto.setText("Archivo generado desde VenturaData");
                    BodyPart adjunto = new MimeBodyPart();
                    //adjunto.setDataHandler(new DataHandler(new FileDataSource("d:/futbol.png")));
                    adjunto.setDataHandler(new DataHandler(new FileDataSource(archivo)));
                    adjunto.setFileName("reporte_VenturaData_Clientes.xlsx");
                    MimeMultipart multiParte = new MimeMultipart();
                    multiParte.addBodyPart(texto);
                    multiParte.addBodyPart(adjunto);
                    message.setSubject("Reporte");
                    message.setContent(multiParte);
                    Transport transport = session.getTransport("smtps");
                    transport.connect("smtp.gmail.com", remitente, clave);
                    transport.sendMessage(message, message.getAllRecipients());
                    transport.close();
                }
                catch (MessagingException me) {
                    me.printStackTrace();   //Si se produce un error
                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }catch(Exception e){}
            JOptionPane.showMessageDialog(null, respuesta);
        }
    }//GEN-LAST:event_jLabel6MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JButton btn_cancelar_cliente1;
    private javax.swing.JButton btn_consultar_cliente;
    private javax.swing.JLabel informacionCliente_codigo;
    private javax.swing.JLabel informacionCliente_estado;
    private javax.swing.JLabel informacionCliente_nombre;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tabla_InformacionRecobroClientes;
    private javax.swing.JTable tabla_clientes;
    private javax.swing.JTextField valorBusqueda;
    private javax.swing.JInternalFrame ventanaInterna_InformacionCliente;
    // End of variables declaration//GEN-END:variables
    public void tabla_ListarClientes(String valorConsulta) throws SQLException{
        ControlDB_Cliente  controlDB_Cliente = new ControlDB_Cliente(tipoConexion);        
        registroCliente = new String[5]; 
        modeloCliente = new DefaultTableModel(null, tituloCliente);  
        ArrayList<Cliente> listadoCliente=controlDB_Cliente.buscar(valorConsulta);
        for(int i =0; i< listadoCliente.size(); i++){
            registroCliente[0]=""+listadoCliente.get(i).getCodigo();
            registroCliente[1]=""+listadoCliente.get(i).getDescripcion();
            registroCliente[2]=""+listadoCliente.get(i).getEstado();  
            registroCliente[3]=""+listadoCliente.get(i).getValorRecobro();  
            registroCliente[4]=""+listadoCliente.get(i).getBaseDatos().getDescripcion(); 
            modeloCliente.addRow(registroCliente);
            tabla_clientes.setModel(modeloCliente);
        }
    }
    public void tabla_consultarHistoricoRecobros(String valorConsulta) throws SQLException{
        //Eliminamos la Busqueda Actual#######################################################
        DefaultTableModel md =(DefaultTableModel)tabla_InformacionRecobroClientes.getModel();
        int CantEliminar= tabla_InformacionRecobroClientes.getRowCount() -1;
        for(int i =CantEliminar; i>=0; i--){
            md.removeRow(i);
        }
        
        
        ControlDB_Cliente  controlDB_Cliente = new ControlDB_Cliente(tipoConexion);        
        registroInformacionHistoricoRecobro = new String[3]; 
        modeloInformacionHistoricoRecobro = new DefaultTableModel(null, tituloInformacionHistoricoRecobro);  
        ArrayList<String> historicoRecobro=controlDB_Cliente.buscarHistorialRecobros(valorConsulta);
        for(int i =0; i< historicoRecobro.size(); i++){
            String []data= historicoRecobro.get(i).split("#");      
            registroInformacionHistoricoRecobro[0]=data[1];//Valor del recobro
            registroInformacionHistoricoRecobro[1]=data[2];//Fecha de registro del recobro
            registroInformacionHistoricoRecobro[2]=data[0];//Usuario quien rgistro recobro
            modeloInformacionHistoricoRecobro.addRow(registroInformacionHistoricoRecobro); 
        }
        tabla_InformacionRecobroClientes.setModel(modeloInformacionHistoricoRecobro);
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
