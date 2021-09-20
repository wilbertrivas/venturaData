package Catalogo.View;
     
import Catalogo.Controller.ControlDB_Articulo;
import Catalogo.Controller.ControlDB_BaseDatos;
import Catalogo.Controller.ControlDB_Cliente;
import Catalogo.Model.Articulo;
import Catalogo.Model.BaseDatos;
import Catalogo.Model.Cliente;
import ModuloEquipo.View.Solicitud_Equipos_Registrar;
import Sistema.Model.Usuario;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
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

public final class Articulo_RegistroSincronizado extends javax.swing.JPanel {
    ArrayList<Articulo> listado;
    Usuario user;
    private String tipoConexion;
    ArrayList<BaseDatos> listadoBaseDatos= new ArrayList();
    public Articulo_RegistroSincronizado(Usuario u,String tipoConexion) {
        initComponents();
        user= u;
        this.tipoConexion= tipoConexion;
        
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
        if(listadoBaseDatos != null){
            try {
                tabla_Listar("",listadoBaseDatos.get(listado_baseDatos.getSelectedIndex()));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al tratar de consultar los clientes");
                Logger.getLogger(Cliente_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        valorBusqueda = new javax.swing.JTextField();
        consultar = new javax.swing.JButton();
        sincronizarCcarga = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        listado_baseDatos = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btn_cancelar_cliente1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        Editar.setText("Sincronizar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 1460, 670));
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, 320, 40));

        consultar.setBackground(new java.awt.Color(255, 255, 255));
        consultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consultar.setText("CONSULTAR");
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        add(consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 50, 140, 40));

        sincronizarCcarga.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sincronizarCcarga.setText("SINCRONIZAR CON CCARGA");
        sincronizarCcarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sincronizarCcargaActionPerformed(evt);
            }
        });
        add(sincronizarCcarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 50, 250, 40));

        jProgressBar1.setStringPainted(true);
        add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 1420, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 153));
        jLabel4.setText("CONSULTAR ARTICULOS EN CONTROLCARGA");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 450, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Base de Datos:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 110, 40));

        listado_baseDatos.setToolTipText("");
        add(listado_baseDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 300, 40));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/enviar_mail.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 60, 40, 40));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ExportarExcel.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 60, 50, 40));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 102));
        jLabel14.setText("Exportar");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 50, 60, 10));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 102));
        jLabel16.setText("Correo");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 50, 90, 10));

        btn_cancelar_cliente1.setBackground(new java.awt.Color(255, 255, 255));
        btn_cancelar_cliente1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cancelar_cliente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btn_cancelar_cliente1.setText("CANCELAR");
        btn_cancelar_cliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_cliente1ActionPerformed(evt);
            }
        });
        add(btn_cancelar_cliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 50, 140, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1460, 130));
    }// </editor-fold>//GEN-END:initComponents

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        try {
            tabla_Listar(valorBusqueda.getText(), listadoBaseDatos.get(listado_baseDatos.getSelectedIndex()));
        } catch (SQLException ex) {
            Logger.getLogger(Articulo_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_consultarActionPerformed

    private void sincronizarCcargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sincronizarCcargaActionPerformed
        Worker_Articulo worker = new Worker_Articulo (jProgressBar1,listado,user,tipoConexion);
        worker.execute();
    }//GEN-LAST:event_sincronizarCcargaActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO lo del clik derechoo
        int fila1;
        try{
            fila1=tabla.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                //DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "Nombre","Estado"});  
                //modelo=(DefaultTableModel)tabla.getModel();
               // Articulo Objeto = new Articulo();
                if (listado != null){
                    Articulo Objeto= listado.get(fila1);
                    int retorno=new ControlDB_Articulo(tipoConexion).registrar(Objeto, user);
                    if(retorno ==1){
                        JOptionPane.showMessageDialog(null, "Se sincronizó el cliente de forma exitosa");
                    }else{
                        JOptionPane.showMessageDialog(null, "No se pudo sincronizar el cliente valide si ya existe en el sistema");
                    }
                
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
                    //DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código", "Nombre","Estado"});  
                    //modelo=(DefaultTableModel)tabla.getModel();
                   // Articulo Objeto = new Articulo();
                    if (listado != null){
                        Articulo Objeto= listado.get(fila1);
                        int retorno=new ControlDB_Articulo(tipoConexion).registrar(Objeto, user);
                        if(retorno ==1){
                            JOptionPane.showMessageDialog(null, "Se sincronizó el cliente de forma exitosa");
                        }else{
                            JOptionPane.showMessageDialog(null, "No se pudo sincronizar el cliente valide si ya existe en el sistema");
                        }

                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        if(user.getCorreo().equals("")){
            JOptionPane.showMessageDialog(null, "El usuario no tiene un correo configurado para el envio de correo, favor actualizar los datos");
        }else{
            File archivo;
            //archivo= new File( "reportes/"+user.getCodigo()+"_"+"reporte_Artículos.xlsx");
            archivo= new File( System.getProperty("java.io.tmpdir")+user.getCodigo()+"_"+"reporte_Artículos.xlsx");
            Workbook wb;
            String respuesta="No se realizó con exito la exportacion";
            int numFila=tabla.getRowCount(), numColumna=tabla.getColumnCount();
            if(archivo.getName().endsWith("xls")){
                wb = new HSSFWorkbook();
            }else{
                wb= new XSSFWorkbook();
            }
            Sheet hoja= wb.createSheet("ReporteVenturaData_ArtículosCcarga");
            int costoTotalApuntador=300000;
            try{
                for(int i= -1; i < numFila; i++ ){
                    Row fila= hoja.createRow(i+1);
                    for(int j=0; j< numColumna; j++){
                        Cell celda= fila.createCell(j);
                        if(i==-1){
                            celda.setCellValue(String.valueOf(tabla.getColumnName(j)));
                            String nameColumn=String.valueOf(tabla.getColumnName(j));
                            if(nameColumn.equals("ME_CostoTotal")){
                                costoTotalApuntador=j;
                            }
                        }else{
                            try{
                                String data=tabla.getValueAt(i, j).toString();
                                String[] valor=String.valueOf(tabla.getValueAt(i, j)).split("-");

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
                                celda.setCellValue(String.valueOf(tabla.getValueAt(i, j)));
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
                    adjunto.setFileName("reporte_VenturaData_ArtículosCCARGA.xlsx");
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

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        JFileChooser selecArchivo= new JFileChooser();
        File archivo;
        if(selecArchivo.showDialog(null, "Exportar") ==JFileChooser.APPROVE_OPTION){
            archivo=new File(selecArchivo.getSelectedFile()+".xlsx");
            if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
                Workbook wb;
                String respuesta="No se realizó con exito la exportacion";

                //int numFila=tabla.getRowCount(), numColumna=tabla.getColumnCount();
                int numFila=tabla.getRowCount(), numColumna=tabla.getColumnCount();
                if(archivo.getName().endsWith("xls")){
                    wb = new HSSFWorkbook();
                }else{
                    wb= new XSSFWorkbook();
                }
                Sheet hoja= wb.createSheet("ArtículosCCARGA");
                int costoTotalApuntador=300000;
                try{
                    for(int i= -1; i < numFila; i++ ){
                        Row fila= hoja.createRow(i+1);
                        for(int j=0; j< numColumna; j++){
                            Cell celda= fila.createCell(j);
                            if(i==-1){
                                celda.setCellValue(String.valueOf(tabla.getColumnName(j)));
                                String nameColumn=String.valueOf(tabla.getColumnName(j));
                                if(nameColumn.equals("ME_CostoTotal")){
                                    costoTotalApuntador=j;
                                }
                            }else{
                                try{
                                    String data=tabla.getValueAt(i, j).toString();
                                    String[] valor=String.valueOf(tabla.getValueAt(i, j)).split("-");

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
                                            // celda.setCellValue(Integer.parseInt(data));
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
                                    celda.setCellValue(String.valueOf(tabla.getValueAt(i, j)));
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

    private void btn_cancelar_cliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_cliente1ActionPerformed
        valorBusqueda.setText("");
    }//GEN-LAST:event_btn_cancelar_cliente1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JButton btn_cancelar_cliente1;
    private javax.swing.JButton consultar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> listado_baseDatos;
    private javax.swing.JButton sincronizarCcarga;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables
    public void tabla_Listar(String valorConsulta, BaseDatos db) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"Código","TipoArticulo", "Nombre","CódigoERP","Unidad_Negocio","Estado","BaseDatos"});  
        //listado=new ControlDB_Articulo(tipoConexion).buscarArticuloGP(valorConsulta);
        if(db.getCodigo().equals("1")){//Base de datos grupoportuario
            listado=new ControlDB_Articulo(tipoConexion).buscarArticuloGP(valorConsulta);
        }else{
            if(db.getCodigo().equals("2")){//Base de datos oppgraneles
                listado=new ControlDB_Articulo(tipoConexion).buscarArticuloOPP(valorConsulta);
            }
        }
        for (Articulo listado1 : listado) {
            String[] registro = new String[7];
            registro[0] = "" + listado1.getCodigo();
            registro[1] = "" + listado1.getTipoArticulo().getDescripcion();
            registro[2] = "" + listado1.getDescripcion();
            registro[3] = "" + listado1.getTipoArticulo().getCodigoERP();
            registro[4] = "" + listado1.getTipoArticulo().getUnidadNegocio();
            if(listado1.getEstado().equals("1")){
                registro[5] = "ACTIVO";
            }else{
                if(listado1.getEstado().equals("0")){
                    registro[5] = "INACTIVO";
                }else{
                    registro[5] = null;
                }
            }
            registro[6]=""+listadoBaseDatos.get(listado_baseDatos.getSelectedIndex()).getDescripcion();  
            modelo.addRow(registro);      
        }
        tabla.setModel(modelo);
    }

}
