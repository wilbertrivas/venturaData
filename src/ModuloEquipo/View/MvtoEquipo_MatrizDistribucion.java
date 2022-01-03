package ModuloEquipo.View;
  
import ModuloCarbon.Controller2.ControlDB_MvtoCarbon;
import ModuloCarbon.Model.MvtoCarbon_ListadoEquipos;
import ModuloEquipo.Controller.ControlDB_MvtoEquipo;
import ModuloEquipo.Model.PlantillaConectorMvtoEquipo;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
//import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utilities.ModeloDeTabla;
import utilities.PaginadorDeTabla;
import utilities.ProveedorDeDatosDePaginacion;
 
public final class MvtoEquipo_MatrizDistribucion extends javax.swing.JPanel implements ActionListener, TableModelListener{
    ArrayList<PlantillaConectorMvtoEquipo> listado=null;
    private Usuario user;
    private String tipoConexion;
    private PaginadorDeTabla<PlantillaConectorMvtoEquipo> paginadorDeTabla;  
    ProveedorDeDatosDePaginacion<PlantillaConectorMvtoEquipo> proveedorDeDatos;
    ArrayList<String> encabezadoTabla=null;
    public MvtoEquipo_MatrizDistribucion(Usuario user, String tipoConexion) {
        initComponents();
        //Ocultamos opción de exportar
        //icon_exportar.show(false);
        //label_exportar.show(false); 
        
        this.user= user;
        this.tipoConexion= tipoConexion;
        for(int i = 0; i<= 23; i++){
            if(i<=9){
                horaInicio.addItem("0"+i);
                horaFin.addItem("0"+i);
            }else{
                horaInicio.addItem(""+i);
                horaFin.addItem(""+i);
            }
        }
        for(int i = 0; i<= 59; i++){
            if(i<=9){
                minutoInicio.addItem("0"+i);
                minutoFin.addItem("0"+i);
            }else{
                minutoInicio.addItem(""+i);
                minutoFin.addItem(""+i);
            }
        }
        horaInicio.setSelectedIndex(0);
        minutoInicio.setSelectedIndex(0);
        horaFin.setSelectedIndex(23);
        minutoFin.setSelectedIndex(59);
        Dimension dim=super.getToolkit().getScreenSize();      
        encabezadoTabla= new ArrayList<String>();
        pageJComboBox.show(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        consultar = new javax.swing.JButton();
        fechaInicio = new com.toedter.calendar.JDateChooser();
        minutoInicio = new javax.swing.JComboBox<>();
        horaInicio = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        fechaFin = new com.toedter.calendar.JDateChooser();
        minutoFin = new javax.swing.JComboBox<>();
        horaFin = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        alerta_fechaFinal = new javax.swing.JLabel();
        alerta_fechaInicio = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        icon_exportar = new javax.swing.JLabel();
        label_exportar = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        horarioTiempoIFinalMvtoCarbon_Procesar = new javax.swing.JLabel();
        horarioTiempoInicioMvtoCarbon_Procesar = new javax.swing.JLabel();
        paginationPanel = new javax.swing.JPanel();
        pageJComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo.setForeground(new java.awt.Color(0, 153, 153));
        titulo.setText("DISTRIBUCIÓN CENTRO DE COSTOS");
        add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 470, 30));

        tabla = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tabla.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 1390, 590));

        consultar.setBackground(new java.awt.Color(255, 255, 255));
        consultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        consultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/consultar.png"))); // NOI18N
        consultar.setText("CONSULTAR");
        consultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                consultarMouseExited(evt);
            }
        });
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        add(consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, 210, 35));

        fechaInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicioMouseEntered(evt);
            }
        });
        add(fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 170, 30));

        minutoInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoInicioActionPerformed(evt);
            }
        });
        add(minutoInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 50, 30));

        horaInicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                horaInicioItemStateChanged(evt);
            }
        });
        add(horaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 50, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Hora");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, -1, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("FECHA/HORA FIN");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, -1, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 65)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("|");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, 40, 70));

        fechaFin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFinMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFinMouseEntered(evt);
            }
        });
        add(fechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 170, 30));

        minutoFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minutoFinActionPerformed(evt);
            }
        });
        add(minutoFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 70, 50, 30));

        horaFin.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                horaFinItemStateChanged(evt);
            }
        });
        horaFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horaFinActionPerformed(evt);
            }
        });
        add(horaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, 50, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Hora");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 70, -1, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("FECHA/HORA INICIO");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 30));

        alerta_fechaFinal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_fechaFinal.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_fechaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 480, 20));

        alerta_fechaInicio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_fechaInicio.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 430, 20));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 1270, 10));

        icon_exportar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_exportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/ExportarExcel.png"))); // NOI18N
        icon_exportar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_exportarMouseClicked(evt);
            }
        });
        add(icon_exportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 60, 50, 40));

        label_exportar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        label_exportar.setForeground(new java.awt.Color(51, 51, 51));
        label_exportar.setText("Exportar");
        add(label_exportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 50, 60, 10));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel24.setText(":");
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 70, 20, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel25.setText(":");
        add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 20, 30));

        horarioTiempoIFinalMvtoCarbon_Procesar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoIFinalMvtoCarbon_Procesar.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoIFinalMvtoCarbon_Procesar.setText("AM");
        add(horarioTiempoIFinalMvtoCarbon_Procesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 70, 50, 30));

        horarioTiempoInicioMvtoCarbon_Procesar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        horarioTiempoInicioMvtoCarbon_Procesar.setForeground(new java.awt.Color(102, 102, 102));
        horarioTiempoInicioMvtoCarbon_Procesar.setText("AM");
        add(horarioTiempoInicioMvtoCarbon_Procesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 50, 30));

        paginationPanel.setBackground(new java.awt.Color(255, 255, 255));
        add(paginationPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 710, 1300, 80));

        add(pageJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 10, 60, 40));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/enviar_mail.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 60, 40, 40));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Correo");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 50, 80, 10));
    }// </editor-fold>//GEN-END:initComponents

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        paginationPanel.removeAll();
        validarSeleccionCampos();
        generarListadoMvtoCarbon();
        resizeColumnWidth(tabla);
    }//GEN-LAST:event_consultarActionPerformed

    private void fechaInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicioMouseClicked
        alerta_fechaInicio.setText("");
    }//GEN-LAST:event_fechaInicioMouseClicked

    private void fechaInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaInicioMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaInicioMouseEntered

    private void minutoInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minutoInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minutoInicioActionPerformed

    private void fechaFinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFinMouseClicked
       alerta_fechaFinal.setText("");
    }//GEN-LAST:event_fechaFinMouseClicked

    private void fechaFinMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFinMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFinMouseEntered

    private void minutoFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minutoFinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minutoFinActionPerformed

    private void consultarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultarMouseExited
        alerta_fechaInicio.setText("");
        alerta_fechaFinal.setText("");
    }//GEN-LAST:event_consultarMouseExited

    private void icon_exportarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_exportarMouseClicked
         if(listado != null){
            JFileChooser selecArchivo= new JFileChooser();
            File archivo;
            if(selecArchivo.showDialog(null, "Exportar") ==JFileChooser.APPROVE_OPTION){
                archivo=new File(selecArchivo.getSelectedFile()+".xlsx");
                if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
                    //JOptionPane.showMessageDialog(null, Exportar(archivo));
                    Workbook wb;
                    String respuesta="No se realizó con exito la exportacion";
                    listado.size();
                   
                    //int numFila=tabla.getRowCount(), numColumna=tabla.getColumnCount();
                    int numFila=listado.size();
                    int numColumna=encabezadoTabla.size();
                    if(archivo.getName().endsWith("xls")){
                        wb = new HSSFWorkbook();
                    }else{
                        wb= new XSSFWorkbook();
                    }
                    Sheet hoja= wb.createSheet("Matriz_Distribución");
                    int costoTotalApuntadorA=300000;
                    int costoTotalApuntadorB=300000;
                    try{
                        for(int i= -1; i < numFila; i++ ){
                            Row fila= hoja.createRow(i+1);
                            for(int j=0; j< numColumna; j++){
                                boolean validarUnidadNegocio = false;
                                Cell celda= fila.createCell(j);
                                if(i==-1){
                                    //celda.setCellValue(String.valueOf(tabla.getColumnName(j)));
                                    celda.setCellValue(encabezadoTabla.get(j));
                                    //String nameColumn=String.valueOf(tabla.getColumnName(j));
                                    if(encabezadoTabla.get(j).equals("Total")){
                                        costoTotalApuntadorA=j;
                                    }
                                    if(encabezadoTabla.get(j).equals("%")){
                                        costoTotalApuntadorB=j;
                                    }
                                }else{
                                    String data="";
                                    switch(encabezadoTabla.get(j)){
                                        case "Subcentro":{
                                            data=""+  listado.get(i).getSubcentro();
                                            break;
                                        }
                                        case "Centro_CostoAuxiliar":{
                                            data=""+  listado.get(i).getCentroCostosAuxiliar();
                                            break;
                                        }
                                        case "Cliente":{
                                            data=""+  listado.get(i).getCliente();
                                            break;
                                        }
                                        /*case "Artículo":{
                                            data=""+  listado.get(i).getArticulo();
                                            break;
                                        }*/
                                        case "Unidad_Negocio":{
                                            validarUnidadNegocio=true;
                                            data=""+  listado.get(i).getUnidadNegocio();
                                            break;
                                        }
                                        case "Centro_Costo":{
                                            data=""+  listado.get(i).getCentroCosto();
                                            break;
                                        }
                                        case "Total":{
                                            data=""+  listado.get(i).getTotal();
                                            break;
                                        }
                                        case "%":{
                                            data=""+  listado.get(i).getPorcentaje();
                                            break;
                                        }  
                                    }                              
                                    try{
                                        if(validarUnidadNegocio){
                                            celda.setCellValue(String.valueOf(data));
                                            validarUnidadNegocio=false;
                                        }else{
                                            String[] valor=String.valueOf(data).split("-");
                                            if(valor.length==3){
                                                String[] valor2=valor[2].split(":");
                                               if(valor2.length >= 3){
                                                   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                   Date fechaM = dateFormat.parse(String.valueOf(data));
                                                   CellStyle cellStyle = wb.createCellStyle();
                                                   CreationHelper createHelper = wb.getCreationHelper();
                                                   cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yy h:mm:ss"));
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
                                               }
                                            }else{
                                                try{
                                                    celda.setCellValue(Integer.parseInt(data));
                                                }catch(Exception e){
                                                    try{
                                                        if(costoTotalApuntadorA == j || costoTotalApuntadorB == j ){
                                                            celda.setCellValue(Double.parseDouble(String.valueOf(data).replace(",", ".")));
                                                        }else{
                                                            celda.setCellValue(String.valueOf(data));
                                                        }
                                                    }catch(Exception ex){
                                                        celda.setCellValue(String.valueOf(data));
                                                    }
                                                }
                                            }   
                                        }
                                    }   
                                    catch(Exception e){
                                        //celda.setCellValue(String.valueOf(tabla.getValueAt(i, j)));
                                        celda.setCellValue(String.valueOf(data));
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
       }  
    }//GEN-LAST:event_icon_exportarMouseClicked

    private void horaInicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_horaInicioItemStateChanged
        if(horaInicio.getSelectedIndex()<= 11){
            horarioTiempoInicioMvtoCarbon_Procesar.setText("AM");
        }else{
            horarioTiempoInicioMvtoCarbon_Procesar.setText("PM");
        }
    }//GEN-LAST:event_horaInicioItemStateChanged

    private void horaFinItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_horaFinItemStateChanged
        if(horaFin.getSelectedIndex()<= 11){
            horarioTiempoIFinalMvtoCarbon_Procesar.setText("AM");
        }else{
            horarioTiempoIFinalMvtoCarbon_Procesar.setText("PM");
        }
    }//GEN-LAST:event_horaFinItemStateChanged

    private void horaFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horaFinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_horaFinActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        if(user.getCorreo().equals("")){
            JOptionPane.showMessageDialog(null, "El usuario no tiene un correo configurado para el envio de correo, favor actualizar los datos");
        }else{
            if(listado != null){
                File archivo;
                //archivo= new File( "reportes/"+user.getCodigo()+"_"+"reporteDistribución.xlsx");
                archivo= new File( System.getProperty("java.io.tmpdir")+user.getCodigo()+"_"+"reporteDistribución.xlsx");
                Workbook wb;
                String respuesta="No se realizó con exito la exportacion";
                listado.size();

                        int numFila=listado.size();
                    int numColumna=encabezadoTabla.size();
                    if(archivo.getName().endsWith("xls")){
                        wb = new HSSFWorkbook();
                    }else{
                        wb= new XSSFWorkbook();
                    }
                    Sheet hoja= wb.createSheet("Matriz_Distribución");
                    int costoTotalApuntadorA=300000;
                    int costoTotalApuntadorB=300000;
                    try{ 
                        for(int i= -1; i < numFila; i++ ){
                            Row fila= hoja.createRow(i+1);
                            for(int j=0; j< numColumna; j++){
                                boolean validarUnidadNegocio = false;
                                Cell celda= fila.createCell(j);
                                if(i==-1){
                                    //celda.setCellValue(String.valueOf(tabla.getColumnName(j)));
                                    celda.setCellValue(encabezadoTabla.get(j));
                                    //String nameColumn=String.valueOf(tabla.getColumnName(j));
                                    if(encabezadoTabla.get(j).equals("Total")){
                                        costoTotalApuntadorA=j;
                                    }
                                    if(encabezadoTabla.get(j).equals("%")){
                                        costoTotalApuntadorB=j;
                                    }
                                }else{
                                    String data="";
                                    switch(encabezadoTabla.get(j)){
                                        case "Subcentro":{
                                            data=""+  listado.get(i).getSubcentro();
                                            break;
                                        }
                                        case "Centro_CostoAuxiliar":{
                                            data=""+  listado.get(i).getCentroCostosAuxiliar();
                                            break;
                                        }
                                        case "Cliente":{
                                            data=""+  listado.get(i).getCliente();
                                            break;
                                        }
                                        /*case "Artículo":{
                                            data=""+  listado.get(i).getArticulo();
                                            break;
                                        }*/
                                        case "Unidad_Negocio":{
                                            validarUnidadNegocio=true;
                                            data=""+  listado.get(i).getUnidadNegocio();
                                            break;
                                        }
                                        case "Centro_Costo":{
                                            data=""+  listado.get(i).getCentroCosto();
                                            break;
                                        }
                                        case "Total":{
                                            data=""+  listado.get(i).getTotal();
                                            break;
                                        }
                                        case "%":{
                                            data=""+  listado.get(i).getPorcentaje();
                                            break;
                                        }  
                                    }                              
                                    try{
                                        if(validarUnidadNegocio){
                                            celda.setCellValue(String.valueOf(data));
                                            validarUnidadNegocio=false;
                                        }else{
                                            String[] valor=String.valueOf(data).split("-");
                                            if(valor.length==3){
                                                String[] valor2=valor[2].split(":");
                                               if(valor2.length >= 3){
                                                   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                   Date fechaM = dateFormat.parse(String.valueOf(data));
                                                   CellStyle cellStyle = wb.createCellStyle();
                                                   CreationHelper createHelper = wb.getCreationHelper();
                                                   cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yy h:mm:ss"));
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
                                               }
                                            }else{
                                                try{
                                                    celda.setCellValue(Integer.parseInt(data));
                                                }catch(Exception e){
                                                    try{
                                                        if(costoTotalApuntadorA == j || costoTotalApuntadorB == j ){
                                                            celda.setCellValue(Double.parseDouble(String.valueOf(data).replace(",", ".")));
                                                        }else{
                                                            celda.setCellValue(String.valueOf(data));
                                                        }
                                                    }catch(Exception ex){
                                                        celda.setCellValue(String.valueOf(data));
                                                    }
                                                }
                                            }   
                                        }
                                    }   
                                    catch(Exception e){
                                        //celda.setCellValue(String.valueOf(tabla.getValueAt(i, j)));
                                        celda.setCellValue(String.valueOf(data));
                                    }
                                }
                            }
                        }
                    for(int j=0; j<=numColumna; j++){
                        hoja.autoSizeColumn(j,true);
                    }
                    wb.write(new FileOutputStream(archivo));
                    respuesta= "Envío exitoso";
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
                        texto.setText("Archivo de distribución generado desde VenturaData");
                        BodyPart adjunto = new MimeBodyPart();
                        //adjunto.setDataHandler(new DataHandler(new FileDataSource("d:/futbol.png")));
                        adjunto.setDataHandler(new DataHandler(new FileDataSource(archivo)));
                        adjunto.setFileName("Distribución_Equipos.xlsx");
                        MimeMultipart multiParte = new MimeMultipart();
                        multiParte.addBodyPart(texto);
                        multiParte.addBodyPart(adjunto);
                        message.setSubject("Distribución Equipos");
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
            }else{
                JOptionPane.showMessageDialog(null,"Elija un formato valido");
            }
        }
    }//GEN-LAST:event_jLabel5MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alerta_fechaFinal;
    private javax.swing.JLabel alerta_fechaInicio;
    private javax.swing.JButton consultar;
    private com.toedter.calendar.JDateChooser fechaFin;
    private com.toedter.calendar.JDateChooser fechaInicio;
    private javax.swing.JComboBox<String> horaFin;
    private javax.swing.JComboBox<String> horaInicio;
    private javax.swing.JLabel horarioTiempoIFinalMvtoCarbon_Procesar;
    private javax.swing.JLabel horarioTiempoInicioMvtoCarbon_Procesar;
    private javax.swing.JLabel icon_exportar;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel label_exportar;
    private javax.swing.JComboBox<String> minutoFin;
    private javax.swing.JComboBox<String> minutoInicio;
    private javax.swing.JComboBox<Integer> pageJComboBox;
    public javax.swing.JPanel paginationPanel;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
    
    public void tabla_Listar(String data1, String data2) throws SQLException{
        tabla.setModel(crearModeloDeTabla());
        listado=new ControlDB_MvtoEquipo(tipoConexion).conectorMvtoEquipo(data1, data2);
        proveedorDeDatos = crearProveedorDeDatos(listado); 
        paginadorDeTabla = new PaginadorDeTabla(tabla, proveedorDeDatos, new int[]{5, 10, 20, 50, 75, 100}, 10);
        paginadorDeTabla.crearListadoDeFilasPermitidas(this.paginationPanel);
        pageJComboBox = paginadorDeTabla.getComboBoxPage();
        events();
        pageJComboBox.setSelectedItem(Integer.parseInt("50"));
    }
    public void generarListadoMvtoCarbon(){
        try{
            String fechaMvtoCarbon_Inicial="";
            String fechaMvtoCarbon_Final="";
            int contador=0;
            try{
                Calendar fechaI = fechaInicio.getCalendar();
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
                fechaMvtoCarbon_Inicial=anoI+"-"+mesI+"-"+diaI;
                contador++;
            }catch(Exception e){
                alerta_fechaInicio.setText("Verifique la fecha de Inicio");
            }
            try{
                Calendar fechaF = fechaFin.getCalendar();
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
                fechaMvtoCarbon_Final=anoF+"-"+mesF+"-"+diaF;
                contador++;
            }catch(Exception e){
                alerta_fechaFinal.setText("Verifique la fecha Final");
            } 
            if(contador==2){//Se validaron las dos fechas y contienen formato correcto
                fechaMvtoCarbon_Inicial = fechaMvtoCarbon_Inicial+" "+horaInicio.getSelectedItem().toString()+":"+minutoInicio.getSelectedItem().toString();
                fechaMvtoCarbon_Final = fechaMvtoCarbon_Final+" "+horaFin.getSelectedItem().toString()+":"+minutoFin.getSelectedItem().toString();
                try {
                    tabla_Listar(fechaMvtoCarbon_Inicial,fechaMvtoCarbon_Final);
                } catch (SQLException ex) {
                    Logger.getLogger(MvtoEquipo_MatrizDistribucion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"No se puedo procesar el descargue de Carbon", "Advertencia",JOptionPane.ERROR_MESSAGE);
        }   
    }
    
    
    public final void events(){
       pageJComboBox.addActionListener(this);
       tabla.getModel().addTableModelListener(this);
    }
    private ModeloDeTabla crearModeloDeTabla() {
        return new ModeloDeTabla<PlantillaConectorMvtoEquipo>() {            
            @Override
            public Object getValueAt(PlantillaConectorMvtoEquipo listado1, int columnas) {
               switch(encabezadoTabla.get(columnas)){
                    case "Subcentro":{
                       return listado1.getSubcentro();
                    }
                    case "Centro_CostoAuxiliar":{
                       return listado1.getCentroCostosAuxiliar();
                    }
                    case "Cliente":{
                       return listado1.getCliente();
                    }
                    /*case "Artículo":{
                       return listado1.getArticulo();
                    }*/
                    case "Unidad_Negocio":{
                       return listado1.getUnidadNegocio();
                    }
                    case "Centro_Costo":{
                       return listado1.getCentroCosto();
                    }
                    case "Total":{
                       return listado1.getTotal();
                    }
                    case "%":{
                       return listado1.getPorcentaje();
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
    private ProveedorDeDatosDePaginacion<PlantillaConectorMvtoEquipo> crearProveedorDeDatos(final ArrayList<PlantillaConectorMvtoEquipo> model) {
        //Obtenemos el listado de registros existentes haciendo una consulta a la base de datos.
        
        //Retornamos un interfaz de tipo ProveedorDeDatosDePaginacion en la cual sobreescribimos sus metodos abtractos
        //1 metodo: obtenemos el numero total de registros agregados al JTable.
        //2 metodo: obtenemos una subLista la cual será mostrada en el JTable, seria nuestra pagina actual.
        return new ProveedorDeDatosDePaginacion<PlantillaConectorMvtoEquipo>() {
            @Override
            public int getTotalRowCount() {
                return model.size();
            }

            @Override
            public List<PlantillaConectorMvtoEquipo> getRows(int startIndex, int endIndex) {
                return model.subList(startIndex, endIndex);
            }
        };
    }
    public void validarSeleccionCampos(){
        encabezadoTabla= new ArrayList<>();
        encabezadoTabla.add("Subcentro");
        encabezadoTabla.add("Centro_CostoAuxiliar");
        encabezadoTabla.add("Cliente");
        //encabezadoTabla.add("Artículo");
        encabezadoTabla.add("Unidad_Negocio");
        encabezadoTabla.add("Centro_Costo");
        encabezadoTabla.add("Total");
        encabezadoTabla.add("%");
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
