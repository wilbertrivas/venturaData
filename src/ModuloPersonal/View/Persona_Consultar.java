package ModuloPersonal.View;

import ModuloPersonal.Controller.ControlDB_Persona;
import ModuloPersonal.Model.Persona;
import Sistema.Model.Usuario;
import java.awt.Component;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import java.text.SimpleDateFormat;
import java.util.Date;

public class Persona_Consultar extends javax.swing.JPanel {

    private String tipoConexion;
    private ArrayList<Persona> listado;
    private Usuario user;

    public Persona_Consultar(String tipoConexion,Usuario us) {
        
        initComponents();
        user= us;
        listado = null;
        this.tipoConexion = tipoConexion;
        try {
            tabla_Listar("");
            resizeColumnWidth(tabla);
        } catch (SQLException ex) {
            Logger.getLogger(Persona_Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        valorBusqueda = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnConsultar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_cancelar_cliente1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        valorBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorBusquedaActionPerformed(evt);
            }
        });
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 360, 40));

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
        jScrollPane1.setViewportView(tabla);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 1340, 520));

        btnConsultar.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        btnConsultar.setText("CONSULTAR");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 150, 40));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/ExportarExcel.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, 50, 40));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 102));
        jLabel14.setText("Exportar");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 40, 60, 10));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 102));
        jLabel16.setText("Correo");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, 90, 10));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/enviar_mail.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 50, 40, 40));

        btn_cancelar_cliente1.setBackground(new java.awt.Color(255, 255, 255));
        btn_cancelar_cliente1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_cancelar_cliente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cancelar.png"))); // NOI18N
        btn_cancelar_cliente1.setText("CANCELAR");
        btn_cancelar_cliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_cliente1ActionPerformed(evt);
            }
        });
        add(btn_cancelar_cliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 40, 140, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CONSULTAR PERSONAL");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1330, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1340, 100));
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        try {
            tabla_Listar(valorBusqueda.getText());
            resizeColumnWidth(tabla);
        } catch (SQLException ex) {
            Logger.getLogger(SituacionMedica_Consultar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void valorBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valorBusquedaActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        JFileChooser selecArchivo= new JFileChooser();
        File archivo;
        if(selecArchivo.showDialog(null, "Exportar") ==JFileChooser.APPROVE_OPTION){
            archivo=new File(selecArchivo.getSelectedFile()+".xlsx");
            if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
                Workbook wb;
                String respuesta="No se realiz?? con exito la exportacion";

                //int numFila=tabla.getRowCount(), numColumna=tabla.getColumnCount();
                int numFila=tabla.getRowCount(), numColumna=tabla.getColumnCount();
                if(archivo.getName().endsWith("xls")){
                    wb = new HSSFWorkbook();
                }else{
                    wb= new XSSFWorkbook();
                }
                Sheet hoja= wb.createSheet("PersonalVenturaData");
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

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        if(user.getCorreo().equals("")){
            JOptionPane.showMessageDialog(null, "El usuario no tiene un correo configurado para el envio de correo, favor actualizar los datos");
        }else{
            File archivo;
            //archivo= new File( "reportes/"+user.getCodigo()+"_"+"reporte_Personal.xlsx");
            archivo= new File( System.getProperty("java.io.tmpdir")+user.getCodigo()+"_"+"reporte_Personal.xlsx");
            Workbook wb;
            String respuesta="No se realiz?? con exito la exportacion";
            int numFila=tabla.getRowCount(), numColumna=tabla.getColumnCount();
            if(archivo.getName().endsWith("xls")){
                wb = new HSSFWorkbook();
            }else{
                wb= new XSSFWorkbook();
            }
            Sheet hoja= wb.createSheet("ReporteVenturaData_Personal");
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
                respuesta= "Env??o exitoso a "+user.getCorreo();
                String remitente = "venturadatavg";  //Para la direcci??n nomcuenta@gmail.com
                String clave = "VG#V3ntur4D4t4!#";  //Para la direcci??n nomcuenta@gmail.com

                Properties props = System.getProperties();
                props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
                props.put("mail.smtp.user", remitente);
                props.put("mail.smtp.clave", clave);    //La clave de la cuenta
                props.put("mail.smtp.auth", "true");    //Usar autenticaci??n mediante usuario y clave
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
                    adjunto.setFileName("ReporteVenturaData_Personal.xlsx");
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

    private void btn_cancelar_cliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_cliente1ActionPerformed
        valorBusqueda.setText("");
    }//GEN-LAST:event_btn_cancelar_cliente1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btn_cancelar_cliente1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables

    public void tabla_Listar(String valorConsulta) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"TipoDocumento", "N??mero", "Nombre", "Telefono", "CargoNomina", "TipoContrato", "Compa??ia", "Equipo", "Estado"});
        listado = new ControlDB_Persona(tipoConexion).buscar(valorConsulta);
        for (Persona objeto : listado) {
            String[] registro = new String[9];
            registro[0] = "" + objeto.getTipoDocumento().getDescripcion();
            registro[1] = "" + objeto.getCodigo();
            registro[2] = "" + objeto.getNombre() + " " + objeto.getApellido();
            registro[3] = "" + objeto.getTelefono();
            registro[4] = "" + objeto.getCargoNomina().getDescripcion();
            registro[5] = "" + objeto.getTipoContrato().getDescripcion();
            registro[6] = "" + objeto.getCompania().getDescripcion();
            registro[7] = "" + objeto.getEquipo().getDescripcion() + " " + objeto.getEquipo().getModelo();
            registro[8] = "" + objeto.getEstado();
            modelo.addRow(registro);
        }
        tabla.setModel(modelo);
    }
    //Ajustar aNcho de las tablas de acuerdo al contenido
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
