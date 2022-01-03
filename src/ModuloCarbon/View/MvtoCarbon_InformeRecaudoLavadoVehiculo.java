package ModuloCarbon.View;
  
import ModuloCarbon.Controller2.ControlDB_MvtoCarbon;
import ModuloCarbon.Model.PlantillaInformeNoLavadoVehiculos;
import ModuloCarbon.Model.PlantillaInformeRecaudoLavadoVehiculo_PorEquipo;
import ModuloCarbon.Model.PlantillaInformeRecaudoPorLavadoVehiculo;
import ModuloCarbon.Model.PlantillaInformeRecaudoPorUsuario;
import Sistema.Model.Usuario;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import javax.swing.table.DefaultTableModel;
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
 
public final class MvtoCarbon_InformeRecaudoLavadoVehiculo extends javax.swing.JPanel{
    ArrayList<PlantillaInformeRecaudoPorLavadoVehiculo> listadoZonaOperacion=null;
    ArrayList<PlantillaInformeRecaudoLavadoVehiculo_PorEquipo> listadoPorEquipo=null;
    ArrayList<PlantillaInformeRecaudoPorUsuario> listadoPorUsuario=null;
    ArrayList<PlantillaInformeNoLavadoVehiculos> listadoPorVehiculoNoLavados=null;
    private final Usuario user;
    private final String tipoConexion;
    public MvtoCarbon_InformeRecaudoLavadoVehiculo(Usuario user, String tipoConexion) {
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
        Dimension dim=super.getToolkit().getScreenSize();     
        horaInicio.setSelectedIndex(0);
        minutoInicio.setSelectedIndex(0);
        horaFin.setSelectedIndex(23);
        minutoFin.setSelectedIndex(59);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
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
        alerta_fechaInicio = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        icon_exportar = new javax.swing.JLabel();
        label_exportar = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        horarioTiempoIFinalMvtoCarbon_Procesar = new javax.swing.JLabel();
        horarioTiempoInicioMvtoCarbon_Procesar = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPorEquipo = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaPorUsuario = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaZonaOperacion = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaVehiculosNoLavados = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo.setForeground(new java.awt.Color(0, 153, 153));
        titulo.setText("INFORME DE RECAUDO LAVADO DE VEHÍCULOS");
        add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 510, 30));

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

        alerta_fechaInicio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_fechaInicio.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 430, 20));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 1270, 10));

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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaPorEquipo = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tablaPorEquipo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tablaPorEquipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablaPorEquipo);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 480));

        jTabbedPane1.addTab("RECAUDO POR EQUIPO", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaPorUsuario = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tablaPorUsuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tablaPorUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tablaPorUsuario);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 620));

        jTabbedPane1.addTab("RECAUDO POR USUARIO", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaZonaOperacion = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tablaZonaOperacion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tablaZonaOperacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaZonaOperacion);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1120, 460));

        jTabbedPane1.addTab("RECAUDO POR ZONA OPERACIÓN", jPanel4);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaVehiculosNoLavados = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tablaVehiculosNoLavados.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tablaVehiculosNoLavados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tablaVehiculosNoLavados);

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 1120, 460));

        jTabbedPane1.addTab("VEHÍCULOS NO LAVADO", jPanel2);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 1220, 520));
    }// </editor-fold>//GEN-END:initComponents

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        
        
        //paginationPanel.removeAll();
        //paginationPanelPorEquipo.removeAll();
        //validarSeleccionCampos();
        generarListadoMvtoCarbon();
        //resizeColumnWidth(tabla);
        //resizeColumnWidth(tablaPorEquipo);
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
      
    }//GEN-LAST:event_fechaFinMouseClicked

    private void fechaFinMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaFinMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFinMouseEntered

    private void minutoFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minutoFinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minutoFinActionPerformed

    private void consultarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultarMouseExited
       
    }//GEN-LAST:event_consultarMouseExited

    private void icon_exportarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_exportarMouseClicked
        if(listadoPorEquipo != null || listadoPorUsuario != null || listadoZonaOperacion!= null || listadoPorVehiculoNoLavados!= null){
            int option =jTabbedPane1.getSelectedIndex();
            switch(option){
                case 0:{//Recaudo por Equipo
                    JFileChooser selecArchivo= new JFileChooser();
                    File archivo;
                    if(selecArchivo.showDialog(null, "Exportar") ==JFileChooser.APPROVE_OPTION){
                        archivo=new File(selecArchivo.getSelectedFile()+".xlsx");
                        if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
                            Workbook wb;
                            String respuesta="No se realizó con exito la exportacion";
                            int  numFila=tablaPorEquipo.getRowCount();
                            int numColumna=tablaPorEquipo.getColumnCount();
                            if(archivo.getName().endsWith("xls")){
                                wb = new HSSFWorkbook();
                            }else{
                                wb= new XSSFWorkbook();
                            }
                            Sheet hoja= wb.createSheet("RecuadoPorEquipo");
                            int columnaCantidadVehiculos=300000;
                            int columnaRecaudoEmpresa=300000;
                            int columnaRecuadoEquipo=300000;
                            try{
                                for(int i= -1; i < numFila; i++ ){
                                    Row fila= hoja.createRow(i+1);
                                    for(int j=0; j< numColumna; j++){
                                        Cell celda= fila.createCell(j);
                                        if(i==-1){
                                            celda.setCellValue(String.valueOf(tablaPorEquipo.getColumnName(j)));
                                            String nameColumn=String.valueOf(tablaPorEquipo.getColumnName(j));
                                            if(nameColumn.equals("CANTIDAD VEHÍCULOS")){
                                                columnaCantidadVehiculos=j;
                                            }
                                            if(nameColumn.equals("RECAUDO EMPRESA")){
                                                columnaRecaudoEmpresa=j;
                                            }
                                            if(nameColumn.equals("RECAUDO EQUIPO")){
                                                columnaRecuadoEquipo=j;
                                            }
                                        }else{
                                            try{
                                                String data=tablaPorEquipo.getValueAt(i, j).toString();
                                                String[] valor=String.valueOf(tablaPorEquipo.getValueAt(i, j)).split("-");
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
                                                        celda.setCellValue(fechaM);
                                                        celda.setCellStyle(cellStyle);
                                                    }
                                                }else{
                                                    try{
                                                        if(columnaCantidadVehiculos == j){
                                                            celda.setCellValue(Integer.parseInt(listadoPorEquipo.get(i).getCantidad()));
                                                            //celda.setCellValue(Double.parseDouble(String.valueOf(data).replace(",", ".")));
                                                        }else{
                                                            if(columnaRecaudoEmpresa == j){
                                                                celda.setCellValue(Integer.parseInt(listadoPorEquipo.get(i).getRecaudoEmpresa()));
                                                                //celda.setCellValue(Double.parseDouble(String.valueOf(data).replace(",", ".")));
                                                            }else{
                                                                 if(columnaRecuadoEquipo == j){
                                                                    celda.setCellValue(Integer.parseInt(listadoPorEquipo.get(i).getRecaudoEquipo()));
                                                                    //celda.setCellValue(Double.parseDouble(String.valueOf(data).replace(",", ".")));
                                                                }else{
                                                                    celda.setCellValue(String.valueOf(data));
                                                                }
                                                            }
                                                        }
                                                    }catch(Exception ex){
                                                        celda.setCellValue(String.valueOf(data));
                                                    }
                                                }
                                            }
                                            catch(Exception e){
                                                celda.setCellValue(String.valueOf(tablaPorEquipo.getValueAt(i, j)));
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
                    break;
                }
                case 1:{//Recaudo por Usuario
                    JFileChooser selecArchivo= new JFileChooser();
                    File archivo;
                    if(selecArchivo.showDialog(null, "Exportar") ==JFileChooser.APPROVE_OPTION){
                        archivo=new File(selecArchivo.getSelectedFile()+".xlsx");
                        if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
                            Workbook wb;
                            String respuesta="No se realizó con exito la exportacion";
                            int  numFila=tablaPorUsuario.getRowCount();
                            int numColumna=tablaPorUsuario.getColumnCount();
                            if(archivo.getName().endsWith("xls")){
                                wb = new HSSFWorkbook();
                            }else{
                                wb= new XSSFWorkbook();
                            }
                            Sheet hoja= wb.createSheet("RecuadoPorUsuario");
                            int columnaRecaudo=300000;
                            try{
                                for(int i= -1; i < numFila; i++ ){
                                    Row fila= hoja.createRow(i+1);
                                    for(int j=0; j< numColumna; j++){
                                        Cell celda= fila.createCell(j);
                                        if(i==-1){
                                            celda.setCellValue(String.valueOf(tablaPorUsuario.getColumnName(j)));
                                            String nameColumn=String.valueOf(tablaPorUsuario.getColumnName(j));
                                            if(nameColumn.equals("RECAUDO")){
                                                columnaRecaudo=j;
                                            }
                                        }else{
                                            try{
                                                String data=tablaPorUsuario.getValueAt(i, j).toString();
                                                String[] valor=String.valueOf(tablaPorUsuario.getValueAt(i, j)).split("-");

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
                                                        celda.setCellValue(fechaM);
                                                        celda.setCellStyle(cellStyle);
                                                    }
                                                }else{
                                                    try{
                                                        if(columnaRecaudo == j){
                                                            celda.setCellValue(Integer.parseInt(listadoPorUsuario.get(i).getRecaudo()));
                                                            //celda.setCellValue(Double.parseDouble(String.valueOf(data).replace(",", ".")));
                                                        }else{
                                                            celda.setCellValue(String.valueOf(data));
                                                        }
                                                    }catch(Exception ex){
                                                        celda.setCellValue(String.valueOf(data));
                                                    }
                                                }
                                            }
                                            catch(Exception e){
                                                celda.setCellValue(String.valueOf(tablaPorUsuario.getValueAt(i, j)));
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
                    break;
                }
                case 2:{//Recaudo por ZonaOperación
                    JFileChooser selecArchivo= new JFileChooser();
                    File archivo;
                    if(selecArchivo.showDialog(null, "Exportar") ==JFileChooser.APPROVE_OPTION){
                        archivo=new File(selecArchivo.getSelectedFile()+".xlsx");
                        if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
                            Workbook wb;
                            String respuesta="No se realizó con exito la exportacion";
                            int  numFila=tablaZonaOperacion.getRowCount();
                            int numColumna=tablaZonaOperacion.getColumnCount();
                            if(archivo.getName().endsWith("xls")){
                                wb = new HSSFWorkbook();
                            }else{
                                wb= new XSSFWorkbook();
                            }
                            Sheet hoja= wb.createSheet("RecuadoPorZonaOperación");
                            int columnaRecaudoEmpresa=300000;
                            int columnaDebito=300000;
                            int columnaTotalRecuado=300000;
                            try{
                                for(int i= -1; i < numFila; i++ ){
                                    Row fila= hoja.createRow(i+1);
                                    for(int j=0; j< numColumna; j++){
                                        Cell celda= fila.createCell(j);
                                        if(i==-1){
                                            celda.setCellValue(String.valueOf(tablaZonaOperacion.getColumnName(j)));
                                            String nameColumn=String.valueOf(tablaZonaOperacion.getColumnName(j));
                                            if(nameColumn.equals("VALOR RECAUDADO")){
                                                columnaRecaudoEmpresa=j;
                                            }
                                            if(nameColumn.equals("VALOR DEBITOS")){
                                                columnaDebito=j;
                                            }
                                            if(nameColumn.equals("TOTAL RECAUDADO")){
                                                columnaTotalRecuado=j;
                                            }
                                        }else{
                                            try{
                                                String data=tablaZonaOperacion.getValueAt(i, j).toString();
                                                String[] valor=String.valueOf(tablaZonaOperacion.getValueAt(i, j)).split("-");

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
                                                        celda.setCellValue(fechaM);
                                                        celda.setCellStyle(cellStyle);
                                                    }
                                                }else{
                                                    try{
                                                        if(columnaRecaudoEmpresa == j){
                                                            celda.setCellValue(Integer.parseInt(listadoZonaOperacion.get(i).getRecaudo()));
                                                            //celda.setCellValue(Double.parseDouble(String.valueOf(data).replace(",", ".")));
                                                        }else{
                                                            if(columnaDebito == j){
                                                                celda.setCellValue(Integer.parseInt(listadoZonaOperacion.get(i).getDebito()));
                                                                //celda.setCellValue(Double.parseDouble(String.valueOf(data).replace(",", ".")));
                                                            }else{
                                                                 if(columnaTotalRecuado == j){
                                                                    celda.setCellValue(Integer.parseInt(listadoZonaOperacion.get(i).getTotalRecaudo()));
                                                                    //celda.setCellValue(Double.parseDouble(String.valueOf(data).replace(",", ".")));
                                                                }else{
                                                                    celda.setCellValue(String.valueOf(data));
                                                                }
                                                            }
                                                        }
                                                    }catch(Exception ex){
                                                        celda.setCellValue(String.valueOf(data));
                                                    }
                                                }
                                            }
                                            catch(Exception e){
                                                celda.setCellValue(String.valueOf(tablaZonaOperacion.getValueAt(i, j)));
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
                    break;
                }
                case 3:{//Vehículos no lavados
                    JFileChooser selecArchivo= new JFileChooser();
                    File archivo;
                    if(selecArchivo.showDialog(null, "Exportar") ==JFileChooser.APPROVE_OPTION){
                        archivo=new File(selecArchivo.getSelectedFile()+".xlsx");
                        if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
                            Workbook wb;
                            String respuesta="No se realizó con exito la exportacion";
                            int  numFila=tablaVehiculosNoLavados.getRowCount();
                            int numColumna=tablaVehiculosNoLavados.getColumnCount();
                            if(archivo.getName().endsWith("xls")){
                                wb = new HSSFWorkbook();
                            }else{
                                wb= new XSSFWorkbook();
                            }
                            Sheet hoja= wb.createSheet("InformeVehículosNoLavados");
                            int columnaCantidadVehiculos=300000;
                            try{
                                for(int i= -1; i < numFila; i++ ){
                                    Row fila= hoja.createRow(i+1);
                                    for(int j=0; j< numColumna; j++){
                                        Cell celda= fila.createCell(j);
                                        if(i==-1){
                                            celda.setCellValue(String.valueOf(tablaVehiculosNoLavados.getColumnName(j)));
                                            String nameColumn=String.valueOf(tablaVehiculosNoLavados.getColumnName(j));  
                                            if(nameColumn.equals("CANTIDAD_VEHÍCULOS_NO_LAVADOS")){
                                                columnaCantidadVehiculos=j;
                                            }
                                        }else{
                                            try{
                                                String data=tablaVehiculosNoLavados.getValueAt(i, j).toString();
                                                String[] valor=String.valueOf(tablaVehiculosNoLavados.getValueAt(i, j)).split("-");

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
                                                        celda.setCellValue(fechaM);
                                                        celda.setCellStyle(cellStyle);
                                                    }
                                                }else{
                                                    try{
                                                       if(columnaCantidadVehiculos == j){
                                                            celda.setCellValue(Integer.parseInt(listadoPorVehiculoNoLavados.get(i).getCantidadVehículo()));
                                                            //celda.setCellValue(Double.parseDouble(String.valueOf(data).replace(",", ".")));
                                                        }else{
                                                            celda.setCellValue(String.valueOf(data));
                                                        }
                                                    }catch(Exception ex){
                                                        celda.setCellValue(String.valueOf(data));
                                                    }
                                                }
                                            }
                                            catch(Exception e){
                                                celda.setCellValue(String.valueOf(tablaZonaOperacion.getValueAt(i, j)));
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
                    break;
                }
                default:{
                    break;
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "La consulta fue vacia, no contiene información","Advertencia", JOptionPane.INFORMATION_MESSAGE);
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
        
    }//GEN-LAST:event_jLabel5MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel label_exportar;
    private javax.swing.JComboBox<String> minutoFin;
    private javax.swing.JComboBox<String> minutoInicio;
    private javax.swing.JTable tablaPorEquipo;
    private javax.swing.JTable tablaPorUsuario;
    private javax.swing.JTable tablaVehiculosNoLavados;
    private javax.swing.JTable tablaZonaOperacion;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
    
    
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
                JOptionPane.showMessageDialog(null, "Verifique la fecha Final","Advertencia", JOptionPane.INFORMATION_MESSAGE);
            } 
            if(contador==2){//Se validaron las dos fechas y contienen formato correcto
                fechaMvtoCarbon_Inicial = fechaMvtoCarbon_Inicial+" "+horaInicio.getSelectedItem().toString()+":"+minutoInicio.getSelectedItem().toString();
                fechaMvtoCarbon_Final = fechaMvtoCarbon_Final+" "+horaFin.getSelectedItem().toString()+":"+minutoFin.getSelectedItem().toString();
                try {
                    
                    listadoZonaOperacion=new ControlDB_MvtoCarbon(tipoConexion).informeRecaudoPorLavadoVehiculo(fechaMvtoCarbon_Inicial, fechaMvtoCarbon_Final);
                    DefaultTableModel modeloZona = new DefaultTableModel(null, new String[] {"ZONA OPERACIÓN", "VALOR RECAUDADO","VALOR DEBITOS", "TOTAL RECAUDADO"});  
                    DecimalFormat formatea = new DecimalFormat("###,###.##");
                    if(listadoZonaOperacion != null){
                        for(PlantillaInformeRecaudoPorLavadoVehiculo objeto:  listadoZonaOperacion){
                            String[]  registro = new String[4]; 
                            registro[0]=""+objeto.getZona();
                            
                            if(objeto.getRecaudo() != null){
                                registro[1]="$ "+formatea.format(Double.parseDouble(objeto.getRecaudo()));
                            }else{
                                registro[1]=objeto.getRecaudo();
                            }
                            if(objeto.getDebito()!= null){
                                registro[2]="$ "+formatea.format(Double.parseDouble(objeto.getDebito()));
                            }else{
                                registro[2]=objeto.getDebito();
                            }
                            
                            if(objeto.getTotalRecaudo() != null){
                                registro[3]="$ "+formatea.format(Double.parseDouble(objeto.getTotalRecaudo()));
                            }else{
                                registro[3]=objeto.getTotalRecaudo();
                            }
                            modeloZona.addRow(registro);
                        }
                        
                    }
                    tablaZonaOperacion.setModel(modeloZona);
                    //resizeColumnWidth(tabla);
                    
                    listadoPorEquipo=new ControlDB_MvtoCarbon(tipoConexion).informeRecaudoPorLavadoVehiculo_porEquipo(fechaMvtoCarbon_Inicial, fechaMvtoCarbon_Final);
                    DefaultTableModel modeloPorEquipo = new DefaultTableModel(null, new String[] {"CLIENTE", "ARTÍCULO","TIPO", "EQUIPO","CANTIDAD VEHÍCULOS","RECAUDO EMPRESA","RECAUDO EQUIPO"});  
                    if(listadoPorEquipo != null){
                        for(PlantillaInformeRecaudoLavadoVehiculo_PorEquipo objeto:  listadoPorEquipo){
                            String[]  registro = new String[7]; 
                            registro[0]=""+objeto.getCliente();
                            registro[1]=""+objeto.getArticulo();
                            registro[2]=""+objeto.getTipoEquipo();
                            registro[3]=""+objeto.getEquipo();
                            if(objeto.getCantidad() != null){
                                registro[4]=""+formatea.format(Double.parseDouble(objeto.getCantidad()));
                            }else{
                                registro[4]=objeto.getCantidad();
                            }
                            if(objeto.getRecaudoEmpresa() != null){
                                registro[5]="$ "+formatea.format(Double.parseDouble(objeto.getRecaudoEmpresa()));
                            }else{
                                registro[5]=objeto.getRecaudoEmpresa();
                            }
                            if(objeto.getRecaudoEquipo() != null){
                                registro[6]="$ "+formatea.format(Double.parseDouble(objeto.getRecaudoEquipo()));
                            }else{
                                registro[6]=objeto.getRecaudoEquipo();
                            }
                            modeloPorEquipo.addRow(registro);
                        }
                        
                    }
                    tablaPorEquipo.setModel(modeloPorEquipo);
                    //resizeColumnWidth(tablaPorEquipo);
                    
                    listadoPorUsuario=new ControlDB_MvtoCarbon(tipoConexion).informeRecaudoPorLavadoVehiculo_porUsuario(fechaMvtoCarbon_Inicial, fechaMvtoCarbon_Final);
                    DefaultTableModel modeloPorUsuario = new DefaultTableModel(null, new String[] {"CEDULA", "NOMBRE","ZONA OPERACIÓN", "RECAUDO"});  
                    if(listadoPorUsuario != null){
                        for(PlantillaInformeRecaudoPorUsuario objeto:  listadoPorUsuario){
                            String[]  registro = new String[4]; 
                            registro[0]=""+objeto.getCedula();
                            registro[1]=""+objeto.getNombre();
                            registro[2]=""+objeto.getZonaOperacion();
                            if(objeto.getRecaudo() != null){
                                registro[3]="$ "+formatea.format(Double.parseDouble(objeto.getRecaudo()));
                            }else{
                                registro[3]=objeto.getRecaudo();
                            }
                            modeloPorUsuario.addRow(registro);
                        }
                   
                    }
                    tablaPorUsuario.setModel(modeloPorUsuario);
                    
                    listadoPorVehiculoNoLavados=new ControlDB_MvtoCarbon(tipoConexion).informeCantidadVehiculosPorConceptoNOLavado(fechaMvtoCarbon_Inicial, fechaMvtoCarbon_Final);
                    DefaultTableModel modeloPorVehiculosNoLavados = new DefaultTableModel(null, new String[] {"CLIENTE", "ARTICULO","ZONA OPERACIÓN","EQUIPO", "MOTIVO NO LAVADO","CANTIDAD_VEHÍCULOS_NO_LAVADOS"});  
                    if(listadoPorVehiculoNoLavados != null){
                        for(PlantillaInformeNoLavadoVehiculos objeto:  listadoPorVehiculoNoLavados){
                            String[]  registro = new String[6]; 
                            registro[0]=""+objeto.getCliente();
                            registro[1]=""+objeto.getArticulo();
                            registro[2]=""+objeto.getZonaOperacion();
                            registro[3]=""+objeto.getEquipo();
                            registro[4]=""+objeto.getMotivoNoLavado();
                            if(objeto.getCantidadVehículo() != null){
                                registro[5]=""+formatea.format(Double.parseDouble(objeto.getCantidadVehículo()));
                            }else{
                                registro[5]=objeto.getCantidadVehículo();
                            }
                            modeloPorVehiculosNoLavados.addRow(registro);
                        }
                    }
                    tablaVehiculosNoLavados.setModel(modeloPorVehiculosNoLavados);
                } catch (SQLException ex) {
                    Logger.getLogger(MvtoCarbon_InformeRecaudoLavadoVehiculo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"No se puedo procesar el descargue de Carbon", "Advertencia",JOptionPane.ERROR_MESSAGE);
        }   
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
    
    //Script donde saca tambien los equipos pero no es conveniente
    /*DECLARE @fechaIni datetime, @fechaFin datetime;  
        DECLARE @cantidadVehiculo BIGINT;  
        DECLARE @totalRecaudoEmpresa BIGINT;  
        DECLARE @totalRecaudoEquipo BIGINT;  

        SET @fechaIni='2020-02-09 14:05:42.600';  
        SET @fechaFin='2022-02-09 14:05:42.600'  
        SET @cantidadVehiculo=(  
                                SELECT 
                                    COUNT([mc_lavado_vehiculo]) 
                                FROM [costos_vg_test].[dbo].[mvto_carbon]  
                                    LEFT JOIN [costos_vg_test].[dbo].[cliente] ON [mc_cliente_cdgo]=[cl_cdgo]  
                                    LEFT JOIN [costos_vg_test].[dbo].[articulo] ON [ar_cdgo]=[mc_articulo_cdgo]  
                                    LEFT JOIN [costos_vg_test].[dbo].[motivo_nolavado_vehiculo] ON [mc_motivo_nolavado_vehiculo_cdgo]=[mnlv_cdgo] 
                                WHERE [mc_fecha_inicio_descargue] BETWEEN @fechaIni AND @fechaFin	AND [mc_lavado_vehiculo] =0 AND [mc_estad_mvto_carbon_cdgo]=1 
                                ) 


        SELECT   --[mc_cdgo],
            [cl_desc] AS 'CLIENTE',  
            [ar_desc] AS 'ARTICULO',	 
            [zt_desc] AS 'ZONA OPERACIÓN', 
            [mnlv_desc] AS 'MOTIVO_NO_LAVADO_VEHÍCULO', 
            COUNT (distinct([mc_fecha]))	 AS 'CANTIDAD_VEHÍCULOS',
                STRING_AGG( CONCAT ([eq_desc],' ',[eq_modelo]) , '/n') AS 'EQUIPO'
        FROM [costos_vg_test].[dbo].[mvto_carbon]  
                LEFT JOIN [costos_vg_test].[dbo].[cliente] ON [mc_cliente_cdgo]=[cl_cdgo]  
                LEFT JOIN [costos_vg_test].[dbo].[articulo] ON [ar_cdgo]=[mc_articulo_cdgo]  
                LEFT JOIN [costos_vg_test].[dbo].[motivo_nolavado_vehiculo] ON [mc_motivo_nolavado_vehiculo_cdgo]=[mnlv_cdgo] 
                        INNER JOIN [costos_vg_test].[dbo].[listado_zona_trabajo] ON [lzt_cntro_cost_auxiliar_cdgo]=[mc_cntro_cost_auxiliar_cdgo] 
                INNER JOIN [costos_vg_test].[dbo].[zona_trabajo] ON [lzt_zona_trabajo_cdgo]=[zt_cdgo] 
                        INNER JOIN [costos_vg_test].[dbo].[mvto_carbon_listado_equipo] ON [mcle_mvto_carbon_cdgo]= [mc_cdgo]
                        INNER JOIN [costos_vg_test].[dbo].[asignacion_equipo] ON [ae_cdgo]=[mcle_asignacion_equipo_cdgo]
                        INNER JOIN [costos_vg_test].[dbo].[equipo] ON [eq_cdgo]=[ae_equipo_cdgo]
        WHERE [mc_fecha_inicio_descargue] BETWEEN @fechaIni AND @fechaFin	AND [mc_lavado_vehiculo] =0 AND [mc_estad_mvto_carbon_cdgo]=1  
        GROUP BY	  
                    [mc_cdgo], [cl_cdgo] ,[cl_desc],  
                    [ar_cdgo],[ar_desc],[zt_desc],[mnlv_desc],[zt_cdgo],[mc_lavado_vehiculo]--,CONCAT ([eq_desc],' ',[eq_modelo])  
        --UNION  
        --SELECT '','','','_TOTAL',@cantidadVehiculo ORDER BY 'CLIENTE' DESC*/
}
