package ModuloPalero.View;

import Catalogo.Controller.ControlDB_Equipo;
import Catalogo.Model.BaseDatos;
import Catalogo.Model.TipoArticulo;
import ModuloCarbon.View.MvtoCarbon_GenerarMatriz;
import ModuloPalero.Controller.ControlDB_ConfiguracionLiquidacion;
import ModuloPalero.Controller.ControlDB_LiquidacionPalero;
import ModuloPalero.Model.ConfiguracionLiquidacion;
import ModuloPalero.Model.Liquidacion;
import ModuloPalero.Model.MvtoCarbon_ListadoEquipos_LiquidacionPaleros;
import ModuloPalero.Model.PlantillaArchivoLiquidacion;
import ModuloPersonal.Model.CargoNomina;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utilities.ModeloDeTabla;
import utilities.PaginadorDeTabla;
import utilities.ProveedorDeDatosDePaginacion;

public class PreLiquidacionPalero_Consultar extends javax.swing.JPanel implements ActionListener, TableModelListener {
    Usuario user;
    private final String tipoConexion;
    ArrayList<TipoArticulo> listadoTipoArticulo = new ArrayList();
    ArrayList<BaseDatos> listadoBaseDatos = new ArrayList();
    private ArrayList<CargoNomina> listadoCargoNomina;
    private PaginadorDeTabla<ConfiguracionLiquidacion> paginadorDeTabla;
    ProveedorDeDatosDePaginacion<ConfiguracionLiquidacion> proveedorDeDatos;
    ArrayList<String> encabezadoTabla = null;
    ArrayList<ConfiguracionLiquidacion> listado = null;
    ConfiguracionLiquidacion configuracionLiquidacion = null;
    ConfiguracionLiquidacion configuracionLiquidacionliquidar = null;
    ArrayList<MvtoCarbon_ListadoEquipos_LiquidacionPaleros> listadoMvtoCarbon_ListadoEquipos_LiquidacionPaleros = null;
    ArrayList<PlantillaArchivoLiquidacion> listadoPlantillaArchivoLiquidacion = null;
    ArrayList<String> listadoPreliquidacion=null;
    Worker_PaleroMarcacionPersonas  worker;
    Worker_LiquidacionNivel2  worker2;
    boolean validarNext = true;
    int level = 0;

    public PreLiquidacionPalero_Consultar(Usuario us, String tipoConexion) {
        initComponents();
        user = us;
        this.tipoConexion = tipoConexion;
        encabezadoTabla = new ArrayList<String>();
        pageJComboBox.show(false);
        InternaFrame_buscarConfiguracionLiquidacion.getContentPane().setBackground(Color.WHITE);
        InternaFrame_buscarConfiguracionLiquidacion.show(false);
        senMailLabel.show(false);
        sendMailIcon.show(false);
        label_exportar.show(false);
        icon_exportar.show(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Enable = new javax.swing.JMenuItem();
        InternaFrame_buscarConfiguracionLiquidacion = new javax.swing.JInternalFrame();
        jLabel15 = new javax.swing.JLabel();
        fechaInicio_Consulta = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        fechaFin_Consulta = new com.toedter.calendar.JDateChooser();
        consultar1 = new javax.swing.JButton();
        pageJComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        paginationPanel = new javax.swing.JPanel();
        pageJComboBox1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        icon_exportar = new javax.swing.JLabel();
        label_exportar = new javax.swing.JLabel();
        sendMailIcon = new javax.swing.JLabel();
        senMailLabel = new javax.swing.JLabel();
        icon_buscarClientes = new javax.swing.JLabel();
        icon_buscarClientes1 = new javax.swing.JLabel();
        label_configuracionLiquidacion = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaListadoVehiculosPorEquipoLiquidacion = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        Enable.setText("Seleccionar");
        Enable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnableActionPerformed(evt);
            }
        });
        Seleccionar.add(Enable);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InternaFrame_buscarConfiguracionLiquidacion.setBackground(new java.awt.Color(255, 255, 255));
        InternaFrame_buscarConfiguracionLiquidacion.setClosable(true);
        InternaFrame_buscarConfiguracionLiquidacion.setVisible(false);
        InternaFrame_buscarConfiguracionLiquidacion.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 102));
        jLabel15.setText("BUSQUEDAS DE CONFIGURACIONES DE LIQUIDACIÓN");
        InternaFrame_buscarConfiguracionLiquidacion.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 740, 30));

        fechaInicio_Consulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaInicio_ConsultaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaInicio_ConsultaMouseEntered(evt);
            }
        });
        InternaFrame_buscarConfiguracionLiquidacion.getContentPane().add(fechaInicio_Consulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 170, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("FECHA INICIO:");
        InternaFrame_buscarConfiguracionLiquidacion.getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 65)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("|");
        InternaFrame_buscarConfiguracionLiquidacion.getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 30, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("FECHA FIN:");
        InternaFrame_buscarConfiguracionLiquidacion.getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, -1, 30));

        fechaFin_Consulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaFin_ConsultaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fechaFin_ConsultaMouseEntered(evt);
            }
        });
        InternaFrame_buscarConfiguracionLiquidacion.getContentPane().add(fechaFin_Consulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, 170, 30));

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
        InternaFrame_buscarConfiguracionLiquidacion.getContentPane().add(consultar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, 160, 40));

        pageJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pageJComboBoxActionPerformed(evt);
            }
        });
        InternaFrame_buscarConfiguracionLiquidacion.getContentPane().add(pageJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 10, 60, 40));

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
        tabla.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        InternaFrame_buscarConfiguracionLiquidacion.getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 1180, 360));

        paginationPanel.setBackground(new java.awt.Color(255, 255, 255));
        InternaFrame_buscarConfiguracionLiquidacion.getContentPane().add(paginationPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 1180, 80));

        pageJComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pageJComboBox1ActionPerformed(evt);
            }
        });
        InternaFrame_buscarConfiguracionLiquidacion.getContentPane().add(pageJComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1410, 10, 60, 40));

        add(InternaFrame_buscarConfiguracionLiquidacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1300, 490));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 102));
        jLabel8.setText("SELECCIONE PERIODO DE LIQUIDACIÓN:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 260, 30));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 60, 110, 10));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 40, 10, 70));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 40, 10, 70));

        icon_exportar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_exportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/ExportarExcel.png"))); // NOI18N
        icon_exportar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_exportarMouseClicked(evt);
            }
        });
        add(icon_exportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 60, 50, 50));

        label_exportar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        label_exportar.setForeground(new java.awt.Color(0, 102, 102));
        label_exportar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_exportar.setText("Exportar");
        add(label_exportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 40, 50, 20));

        sendMailIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sendMailIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/enviar_mail.png"))); // NOI18N
        sendMailIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sendMailIconMouseClicked(evt);
            }
        });
        add(sendMailIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 60, 40, 50));

        senMailLabel.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        senMailLabel.setForeground(new java.awt.Color(0, 102, 102));
        senMailLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        senMailLabel.setText("Correo");
        add(senMailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 40, 50, 20));

        icon_buscarClientes.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        icon_buscarClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        icon_buscarClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_buscarClientesMouseClicked(evt);
            }
        });
        add(icon_buscarClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 30, 30));

        icon_buscarClientes1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        icon_buscarClientes1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/limpiar.png"))); // NOI18N
        icon_buscarClientes1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icon_buscarClientes1MouseClicked(evt);
            }
        });
        add(icon_buscarClientes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 50, 30, 30));

        label_configuracionLiquidacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_configuracionLiquidacion.setForeground(new java.awt.Color(102, 0, 51));
        label_configuracionLiquidacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(label_configuracionLiquidacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 520, 30));

        tablaListadoVehiculosPorEquipoLiquidacion = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }

        };
        tablaListadoVehiculosPorEquipoLiquidacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaListadoVehiculosPorEquipoLiquidacion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaListadoVehiculosPorEquipoLiquidacion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaListadoVehiculosPorEquipoLiquidacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaListadoVehiculosPorEquipoLiquidacionMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaListadoVehiculosPorEquipoLiquidacion);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 1390, 520));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("CONSULTAS DE PRELIQUIDACIONES DE PALEROS");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1390, 30));

        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 1120, 70));

        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 40, 270, 70));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 102));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 1390, 110));
    }// </editor-fold>//GEN-END:initComponents

    private void icon_buscarClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_buscarClientesMouseClicked
        InternaFrame_buscarConfiguracionLiquidacion.show(true);
    }//GEN-LAST:event_icon_buscarClientesMouseClicked

    private void icon_buscarClientes1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_buscarClientes1MouseClicked
        configuracionLiquidacion = null;
        configuracionLiquidacionliquidar = null;
        label_configuracionLiquidacion.setText("");
        label_exportar.show(false);
        icon_exportar.show(false);
        senMailLabel.show(false);
        sendMailIcon.show(false);
    }//GEN-LAST:event_icon_buscarClientes1MouseClicked

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

    private void pageJComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pageJComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pageJComboBox1ActionPerformed

    private void EnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnableActionPerformed
        // TODO lo del clik derechoo
        int fila1;
        try {
            fila1 = tabla.getSelectedRow();
            if (fila1 == -1) {
                JOptionPane.showMessageDialog(null, "no se ha seleccionando ninguna configuración de liquidación");
            } else {
                int posicion = paginadorDeTabla.getPosicionador();
                if (posicion != -1) {
                    fila1 = fila1 + posicion;
                }
                if (listado != null) {
                    configuracionLiquidacion = null;
                    configuracionLiquidacion = listado.get(fila1);
                    configuracionLiquidacionliquidar=configuracionLiquidacion;

                    //Limpiamos todos los registro de la tabla
                    DefaultTableModel md = (DefaultTableModel) tablaListadoVehiculosPorEquipoLiquidacion.getModel();
                    int CantEliminar = tablaListadoVehiculosPorEquipoLiquidacion.getRowCount() - 1;
                    for (int i = CantEliminar; i >= 0; i--) {
                        md.removeRow(i);
                    }
                    listadoPlantillaArchivoLiquidacion= new ControlDB_LiquidacionPalero(tipoConexion).consultar_PreLiquidacion(configuracionLiquidacion);
                    if(listadoPlantillaArchivoLiquidacion!= null){
                       
                        DefaultTableModel modeloT = new DefaultTableModel(null, new String[]{"FECHA INICIO PERIODO", "FECHA FIN PERIODO", "CUADRILLA", "DOCUMENTO", "EMPLEADO", "KILOS", "DIAS LABORADOS", "DCTO TONELADA MES","VALOR TONELADA","TONELADA SALARIO","TONELADAS A PAGAR","TOTAL COMISION"});

                        //Cargamos la nueva información
                        for(PlantillaArchivoLiquidacion objeto : listadoPlantillaArchivoLiquidacion){
                            ///DecimalFormat formato2 = new DecimalFormat("0.00");
                            String[] registro = new String[12];
                            registro[0] = "" + objeto.getFechaInicio();
                            registro[1] = "" + objeto.getFechaFin();
                            registro[2] = "" + objeto.getEquipo();
                            registro[3] = "" + objeto.getPersonaDocumento();
                            registro[4] = "" + objeto.getPersonaNombre();
                            registro[5] = "" + objeto.getCantidadAcumuladasKilos();
                            registro[6] = "" + objeto.getCantidadDias();
                            registro[7] = "" + objeto.getToneladaMes();
                            registro[8] = "" + objeto.getValorTonelada();
                            registro[9] = "" + objeto.getToneladaSalario();
                            registro[10] = "" + objeto.getToneladaAPagar();
                            registro[11] = "" + objeto.getTotalComision();
                            modeloT.addRow(registro);
                        }
                        tablaListadoVehiculosPorEquipoLiquidacion.setModel(modeloT);
                        tablaListadoVehiculosPorEquipoLiquidacion.setDefaultRenderer(Object.class, new Myrender_LiquidacionPalero_Registrar(2));
                        resizeColumnWidth(tablaListadoVehiculosPorEquipoLiquidacion);
                        senMailLabel.show(true);
                        sendMailIcon.show(true);
                        label_exportar.show(true);
                        icon_exportar.show(true);
                        label_configuracionLiquidacion.setText(configuracionLiquidacion.getDescripcion());
                        InternaFrame_buscarConfiguracionLiquidacion.show(false);
                    }    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_EnableActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() == 2) {
            // TODO lo del clik derechoo
            int fila1;
            try {
                fila1 = tabla.getSelectedRow();
                if (fila1 == -1) {
                    JOptionPane.showMessageDialog(null, "no se ha seleccionando ninguna configuración de liquidación");
                } else {
                    int posicion = paginadorDeTabla.getPosicionador();
                    if (posicion != -1) {
                        fila1 = fila1 + posicion;
                    }
                    if (listado != null) {
                        configuracionLiquidacion = null;
                        configuracionLiquidacion = listado.get(fila1);
                        configuracionLiquidacionliquidar=configuracionLiquidacion;

                        //Limpiamos todos los registro de la tabla
                        DefaultTableModel md = (DefaultTableModel) tablaListadoVehiculosPorEquipoLiquidacion.getModel();
                        int CantEliminar = tablaListadoVehiculosPorEquipoLiquidacion.getRowCount() - 1;
                        for (int i = CantEliminar; i >= 0; i--) {
                            md.removeRow(i);
                        }
                        listadoPlantillaArchivoLiquidacion= new ControlDB_LiquidacionPalero(tipoConexion).consultar_PreLiquidacion(configuracionLiquidacion);
                        if(listadoPlantillaArchivoLiquidacion!= null){

                            DefaultTableModel modeloT = new DefaultTableModel(null, new String[]{"FECHA INICIO PERIODO", "FECHA FIN PERIODO", "CUADRILLA", "DOCUMENTO", "EMPLEADO", "KILOS", "DIAS LABORADOS", "DCTO TONELADA MES","VALOR TONELADA","TONELADA SALARIO","TONELADAS A PAGAR","TOTAL COMISION"});

                            //Cargamos la nueva información
                            for(PlantillaArchivoLiquidacion objeto : listadoPlantillaArchivoLiquidacion){
                                ///DecimalFormat formato2 = new DecimalFormat("0.00");
                                String[] registro = new String[12];
                                registro[0] = "" + objeto.getFechaInicio();
                                registro[1] = "" + objeto.getFechaFin();
                                registro[2] = "" + objeto.getEquipo();
                                registro[3] = "" + objeto.getPersonaDocumento();
                                registro[4] = "" + objeto.getPersonaNombre();
                                registro[5] = "" + objeto.getCantidadAcumuladasKilos();
                                registro[6] = "" + objeto.getCantidadDias();
                                registro[7] = "" + objeto.getToneladaMes();
                                registro[8] = "" + objeto.getValorTonelada();
                                registro[9] = "" + objeto.getToneladaSalario();
                                registro[10] = "" + objeto.getToneladaAPagar();
                                registro[11] = "" + objeto.getTotalComision();

                                modeloT.addRow(registro);
                            }
                            tablaListadoVehiculosPorEquipoLiquidacion.setModel(modeloT);
                            tablaListadoVehiculosPorEquipoLiquidacion.setDefaultRenderer(Object.class, new Myrender_LiquidacionPalero_Registrar(2));
                            resizeColumnWidth(tablaListadoVehiculosPorEquipoLiquidacion);
                            senMailLabel.show(true);
                            sendMailIcon.show(true);
                            label_exportar.show(true);
                            icon_exportar.show(true);
                            label_configuracionLiquidacion.setText(configuracionLiquidacion.getDescripcion());
                            InternaFrame_buscarConfiguracionLiquidacion.show(false);

                        }    
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void tablaListadoVehiculosPorEquipoLiquidacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaListadoVehiculosPorEquipoLiquidacionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaListadoVehiculosPorEquipoLiquidacionMouseClicked

    private void icon_exportarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_exportarMouseClicked
        //listadoMvtoCarbon_ListadoEquipos_LiquidacionPaleros = worker.getListado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros();
        //si  listadoPreliquidacion
        ArrayList<String> encabezadoTabla= new ArrayList<>();
        encabezadoTabla.add("FECHA INICIO PERIODO");
        encabezadoTabla.add("FECHA FIN PERIODO");
        encabezadoTabla.add("CUADRILLA");
        encabezadoTabla.add("DOCUMENTO");
        encabezadoTabla.add("EMPLEADO");
        encabezadoTabla.add("KILOS");
        encabezadoTabla.add("DIAS LABORADOS");
        encabezadoTabla.add("DCTO TONELADA MES");
        encabezadoTabla.add("VALOR TONELADA");
        encabezadoTabla.add("TONELADA SALARIO");
        encabezadoTabla.add("TONELADAS A PAGAR");
        encabezadoTabla.add("TOTAL COMISION");

        JFileChooser selecArchivo= new JFileChooser();
        File archivo;
        DecimalFormat formato2 = new DecimalFormat("0.00");
        //selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
        //selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
        if(selecArchivo.showDialog(null, "Exportar") ==JFileChooser.APPROVE_OPTION){
            archivo=new File(selecArchivo.getSelectedFile()+".xlsx");
            //archivo=selecArchivo.getSelectedFile();
            if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
                //JOptionPane.showMessageDialog(null, Exportar(archivo));
                Workbook wb;
                String respuesta="No se realizó con exito la exportacion";
                listadoPlantillaArchivoLiquidacion.size();

                //int numFila=tabla.getRowCount(), numColumna=tabla.getColumnCount();
                int numFila=listadoPlantillaArchivoLiquidacion.size();
                int numColumna=encabezadoTabla.size();
                if(archivo.getName().endsWith("xls")){
                    wb = new HSSFWorkbook();
                }else{
                    wb= new XSSFWorkbook();
                }
                Sheet hoja= wb.createSheet("Reporte_Preliquidacion_Level_3");
                int costoTotalApuntador=300000;
                try{
                    Row fila2= hoja.createRow(0);
                    Cell celda2= fila2.createCell(0);
                    celda2.setCellValue("PRE"+configuracionLiquidacionliquidar.getDescripcion());
                    hoja.addMergedRegion(new CellRangeAddress(
                            0, //first row (0-based)
                            1, //last row  (0-based)
                            0, //first column (0-based)
                            11  //last column  (0-based)
                    ));
                    for(int i= -1; i < numFila; i++ ){
                        Row fila= hoja.createRow(i+3);
                        for(int j=0; j< numColumna; j++){
                            //boolean validarUnidadNegocio = false;
                            Cell celda= fila.createCell(j);
                            if(i==-1){
                                //celda.setCellValue(String.valueOf(tabla.getColumnName(j)));
                                celda.setCellValue(encabezadoTabla.get(j));
                                //String nameColumn=String.valueOf(tabla.getColumnName(j));
                                if(encabezadoTabla.get(j).equals("Costo_Total")){
                                    costoTotalApuntador=j;
                                }
                            }else{
                                String data="";
                                //DecimalFormat formato2 = new DecimalFormat("0.00");
                                switch(encabezadoTabla.get(j)){

                                    case "FECHA INICIO PERIODO":{
                                        data=""+  listadoPlantillaArchivoLiquidacion.get(i).getFechaInicio();
                                        break;
                                    }
                                    case "FECHA FIN PERIODO":{
                                        data=""+    listadoPlantillaArchivoLiquidacion.get(i).getFechaFin();
                                        break;
                                    }
                                    case "CUADRILLA":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getEquipo();
                                        break;
                                    }
                                    case "DOCUMENTO":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getPersonaDocumento();
                                        break;
                                    }
                                    case "EMPLEADO":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getPersonaNombre();
                                        break;
                                    }

                                //formato2.format(Double.parseDouble(informacion[6]));

                                //data=""+ formato2.format((Double.parseDouble(listadoMvtoCarbon_ListadoEquipos_LiquidacionPaleros.get(i).getMvtoCarbon().getPesoNeto()) / listadoMvtoCarbon_ListadoEquipos_LiquidacionPaleros.get(i).getMvtoEquipo().getAsignacionEquipo().getEquipo().getListadoPersonas().size()));

                                    case "KILOS":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getCantidadAcumuladasKilos();
                                        break;
                                    } 
                                    case "DIAS LABORADOS":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getCantidadDias();
                                        break;
                                    }
                                    case "DCTO TONELADA MES":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getToneladaMes();
                                        break;
                                    }  
                                    case "VALOR TONELADA":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getValorTonelada();
                                        break;
                                    }  
                                    case "TONELADA SALARIO":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getToneladaSalario();
                                        break;
                                    } 
                                    case "TONELADAS A PAGAR":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getToneladaAPagar();
                                        break;
                                    } 
                                    case "TOTAL COMISION":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getTotalComision();
                                        break;
                                    } 
                                }
                                try{
                                    /*if(validarUnidadNegocio){
                                        celda.setCellValue(String.valueOf(data));
                                        validarUnidadNegocio=false;
                                    }else{*/
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
                                                    //celda.setCellValue(Double.parseDouble(String.valueOf(data)));
                                                    //if(costoTotalApuntador == j){
                                                        celda.setCellValue(Double.parseDouble(String.valueOf(data).replace(",", ".")));
                                                    //}else{
                                                      //  celda.setCellValue(String.valueOf(data));
                                                    //}
                                                }catch(Exception ex){
                                                    celda.setCellValue(String.valueOf(data));
                                                }
                                            }
                                        }
                                    //}
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
                    
                    CellStyle estilo =  wb.createCellStyle();
                     //estilo.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
                    estilo.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                    estilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    //.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
                    // Establece el estilo de color de primer plano de una celda, modo de punto negro grande
                    // estilo.setFillPattern(FillPatternType.BIG_SPOTS);
                    estilo.setAlignment(HorizontalAlignment.CENTER);
                    estilo.setVerticalAlignment(VerticalAlignment.CENTER);
                    estilo.setBorderBottom(BorderStyle.THIN);
                    estilo.setBorderLeft(BorderStyle.THIN);
                    estilo.setBorderRight(BorderStyle.THIN);
                    estilo.setBorderTop(BorderStyle.THIN);
                    //estilo.setFont(new Font());
                    
                    Font font= wb.createFont();
                    font.setFontHeightInPoints((short)10);
                    font.setFontName("Arial");
                    font.setColor(IndexedColors.BLACK.getIndex());
                    font.setBold(true);
                    font.setItalic(false);
                    estilo.setFont(font);  
                    
		    hoja.getRow(0).getCell(0).setCellStyle(estilo);
		    hoja.getRow(2).getCell(0).setCellStyle(estilo);
		    hoja.getRow(2).getCell(1).setCellStyle(estilo);
		    hoja.getRow(2).getCell(2).setCellStyle(estilo);
		    hoja.getRow(2).getCell(3).setCellStyle(estilo);
		    hoja.getRow(2).getCell(4).setCellStyle(estilo);
		    hoja.getRow(2).getCell(5).setCellStyle(estilo);
		    hoja.getRow(2).getCell(6).setCellStyle(estilo);
		    hoja.getRow(2).getCell(7).setCellStyle(estilo);
		    hoja.getRow(2).getCell(8).setCellStyle(estilo);
		    hoja.getRow(2).getCell(9).setCellStyle(estilo);
		    hoja.getRow(2).getCell(10).setCellStyle(estilo);
		    hoja.getRow(2).getCell(11).setCellStyle(estilo);

                    wb.write(new FileOutputStream(archivo));
                    respuesta= "Exportacion exitosa";
                }catch(Exception e){}
                JOptionPane.showMessageDialog(null, respuesta);
            }else{
                JOptionPane.showMessageDialog(null,"Elija un formato valido");
            }
        }
    }//GEN-LAST:event_icon_exportarMouseClicked

    private void sendMailIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendMailIconMouseClicked
        if(configuracionLiquidacionliquidar != null){
            if(user.getCorreo().equals("")){
                JOptionPane.showMessageDialog(null, "El usuario no tiene un correo configurado para el envio de correo, favor actualizar los datos");
            }else{
                ArrayList<String> encabezadoTabla= new ArrayList<>();
                encabezadoTabla.add("FECHA INICIO PERIODO");
                encabezadoTabla.add("FECHA FIN PERIODO");
                encabezadoTabla.add("CUADRILLA");
                encabezadoTabla.add("DOCUMENTO");
                encabezadoTabla.add("EMPLEADO");
                encabezadoTabla.add("KILOS");
                encabezadoTabla.add("DIAS LABORADOS");
                encabezadoTabla.add("DCTO TONELADA MES");
                encabezadoTabla.add("VALOR TONELADA");
                encabezadoTabla.add("TONELADA SALARIO");
                encabezadoTabla.add("TONELADAS A PAGAR");
                encabezadoTabla.add("TOTAL COMISION");

                File archivo;
                //archivo= new File( "reportes/"+user.getCodigo()+"_"+"reporteCarbon.xlsx");
                archivo= new File(System.getProperty("java.io.tmpdir")+user.getCodigo()+"_"+"PRELIQUIDACIÓN_"+configuracionLiquidacionliquidar.getDescripcion()+"_+.xlsx");
                Workbook wb;
                String respuesta="No se realizó con exito la exportacion";
                listadoPlantillaArchivoLiquidacion.size();

                //int numFila=tabla.getRowCount(), numColumna=tabla.getColumnCount();
                int numFila=listadoPlantillaArchivoLiquidacion.size();
                int numColumna=encabezadoTabla.size();
                if(archivo.getName().endsWith("xls")){
                    wb = new HSSFWorkbook();
                }else{
                    wb= new XSSFWorkbook();
                }
                Sheet hoja= wb.createSheet("PreLiquidacionPalero");
                int costoTotalApuntador=300000;
                try{
                    Row fila2= hoja.createRow(0);
                    Cell celda2= fila2.createCell(0);
                    celda2.setCellValue("PRE"+configuracionLiquidacionliquidar.getDescripcion());
                    hoja.addMergedRegion(new CellRangeAddress(
                            0, //first row (0-based)
                            1, //last row  (0-based)
                            0, //first column (0-based)
                            11  //last column  (0-based)
                    ));
                    for(int i= -1; i < numFila; i++ ){
                        Row fila= hoja.createRow(i+3);
                        for(int j=0; j< numColumna; j++){
                            boolean validarUnidadNegocio = false;
                            Cell celda= fila.createCell(j);
                            if(i==-1){
                                //celda.setCellValue(String.valueOf(tabla.getColumnName(j)));
                                celda.setCellValue(encabezadoTabla.get(j));
                                //String nameColumn=String.valueOf(tabla.getColumnName(j));
                            }else{
                                String data="";
                                switch(encabezadoTabla.get(j)){
                                    case "FECHA INICIO PERIODO":{
                                        data=""+  listadoPlantillaArchivoLiquidacion.get(i).getFechaInicio();
                                        break;
                                    }
                                    case "FECHA FIN PERIODO":{
                                        data=""+    listadoPlantillaArchivoLiquidacion.get(i).getFechaFin();
                                        break;
                                    }
                                    case "CUADRILLA":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getEquipo();
                                        break;
                                    }
                                    case "DOCUMENTO":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getPersonaDocumento();
                                        break;
                                    }
                                    case "EMPLEADO":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getPersonaNombre();
                                        break;
                                    }
                                    case "KILOS":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getCantidadAcumuladasKilos();
                                        break;
                                    } 
                                    case "DIAS LABORADOS":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getCantidadDias();
                                        break;
                                    }
                                    case "DCTO TONELADA MES":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getToneladaMes();
                                        break;
                                    }  
                                    case "VALOR TONELADA":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getValorTonelada();
                                        break;
                                    }  
                                    case "TONELADA SALARIO":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getToneladaSalario();
                                        break;
                                    } 
                                    case "TONELADAS A PAGAR":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getToneladaAPagar();
                                        break;
                                    } 
                                    case "TOTAL COMISION":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getTotalComision();
                                        break;
                                    } 
                                }
                                try{
    //                              if(validarUnidadNegocio){
//                                      celda.setCellValue(String.valueOf(data));
    //                                  validarUnidadNegocio=false;
    //                              }else{
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
                                                //if(costoTotalApuntador == j){
                                                    celda.setCellValue(Double.parseDouble(String.valueOf(data).replace(",", ".")));
                                                //}else{
                                                  //  celda.setCellValue(String.valueOf(data));
                                                //}
                                            }catch(Exception ex){
                                                celda.setCellValue(String.valueOf(data));
                                            }
                                        }
    //                                        }
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
                    CellStyle estilo =  wb.createCellStyle();
                     //estilo.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
                    estilo.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                    estilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    //.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
                    // Establece el estilo de color de primer plano de una celda, modo de punto negro grande
                    // estilo.setFillPattern(FillPatternType.BIG_SPOTS);
                    estilo.setAlignment(HorizontalAlignment.CENTER);
                    estilo.setVerticalAlignment(VerticalAlignment.CENTER);
                    estilo.setBorderBottom(BorderStyle.THIN);
                    estilo.setBorderLeft(BorderStyle.THIN);
                    estilo.setBorderRight(BorderStyle.THIN);
                    estilo.setBorderTop(BorderStyle.THIN);
                    //estilo.setFont(new Font());
                    
                    Font font= wb.createFont();
                    font.setFontHeightInPoints((short)10);
                    font.setFontName("Arial");
                    font.setColor(IndexedColors.BLACK.getIndex());
                    font.setBold(true);
                    font.setItalic(false);
                    estilo.setFont(font);
                    
		    hoja.getRow(0).getCell(0).setCellStyle(estilo);
		    hoja.getRow(2).getCell(0).setCellStyle(estilo);
		    hoja.getRow(2).getCell(1).setCellStyle(estilo);
		    hoja.getRow(2).getCell(2).setCellStyle(estilo);
		    hoja.getRow(2).getCell(3).setCellStyle(estilo);
		    hoja.getRow(2).getCell(4).setCellStyle(estilo);
		    hoja.getRow(2).getCell(5).setCellStyle(estilo);
		    hoja.getRow(2).getCell(6).setCellStyle(estilo);
		    hoja.getRow(2).getCell(7).setCellStyle(estilo);
		    hoja.getRow(2).getCell(8).setCellStyle(estilo);
		    hoja.getRow(2).getCell(9).setCellStyle(estilo);
		    hoja.getRow(2).getCell(10).setCellStyle(estilo);
		    hoja.getRow(2).getCell(11).setCellStyle(estilo);
                    
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
                        texto.setText("PreLiquidación generada desde VenturaData");
                        BodyPart adjunto = new MimeBodyPart();
                        //adjunto.setDataHandler(new DataHandler(new FileDataSource("d:/futbol.png")));
                        adjunto.setDataHandler(new DataHandler(new FileDataSource(archivo)));
                        adjunto.setFileName("PRE"+configuracionLiquidacionliquidar.getDescripcion()+".xlsx");
                        MimeMultipart multiParte = new MimeMultipart();
                        multiParte.addBodyPart(texto);
                        multiParte.addBodyPart(adjunto);
                        message.setSubject("Preliquidación Paleros");
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
        }else{
            JOptionPane.showMessageDialog(null,"Debe cargar una configuración de liquidación", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
        /*if(configuracionLiquidacionliquidar != null){
            if(user.getCorreo().equals("")){
                JOptionPane.showMessageDialog(null, "El usuario no tiene un correo configurado para el envio de correo, favor actualizar los datos");
            }else{
                ArrayList<String> encabezadoTabla= new ArrayList<>();
                encabezadoTabla.add("FECHA INICIO PERIODO");
                encabezadoTabla.add("FECHA FIN PERIODO");
                encabezadoTabla.add("CUADRILLA");
                encabezadoTabla.add("DOCUMENTO");
                encabezadoTabla.add("EMPLEADO");
                encabezadoTabla.add("KILOS");
                encabezadoTabla.add("DIAS LABORADOS");
                encabezadoTabla.add("DCTO TONELADA MES");
                encabezadoTabla.add("VALOR TONELADA");
                encabezadoTabla.add("TONELADA SALARIO");
                encabezadoTabla.add("TONELADAS A PAGAR");
                encabezadoTabla.add("TOTAL COMISION");

                File archivo;
                //archivo= new File( "reportes/"+user.getCodigo()+"_"+"reporteCarbon.xlsx");
                archivo= new File(System.getProperty("java.io.tmpdir")+user.getCodigo()+"_"+"LIQUIDACIÓN_"+configuracionLiquidacionliquidar.getDescripcion()+"_+.xlsx");
                Workbook wb;
                String respuesta="No se realizó con exito la exportacion";
                listadoPlantillaArchivoLiquidacion.size();

                //int numFila=tabla.getRowCount(), numColumna=tabla.getColumnCount();
                int numFila=listadoPlantillaArchivoLiquidacion.size();
                int numColumna=encabezadoTabla.size();
                if(archivo.getName().endsWith("xls")){
                    wb = new HSSFWorkbook();
                }else{
                    wb= new XSSFWorkbook();
                }
                Sheet hoja= wb.createSheet("LiquidacionPalero");
                int costoTotalApuntador=300000;
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
                            }else{
                                String data="";
                                switch(encabezadoTabla.get(j)){
                                    case "FECHA INICIO PERIODO":{
                                        data=""+  listadoPlantillaArchivoLiquidacion.get(i).getFechaInicio();
                                        break;
                                    }
                                    case "FECHA FIN PERIODO":{
                                        data=""+    listadoPlantillaArchivoLiquidacion.get(i).getFechaFin();
                                        break;
                                    }
                                    case "CUADRILLA":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getEquipo();
                                        break;
                                    }
                                    case "DOCUMENTO":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getPersonaDocumento();
                                        break;
                                    }
                                    case "EMPLEADO":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getPersonaNombre();
                                        break;
                                    }
                                    case "KILOS":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getCantidadAcumuladasKilos();
                                        break;
                                    } 
                                    case "DIAS LABORADOS":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getCantidadDias();
                                        break;
                                    }
                                    case "DCTO TONELADA MES":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getToneladaMes();
                                        break;
                                    }  
                                    case "VALOR TONELADA":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getValorTonelada();
                                        break;
                                    }  
                                    case "TONELADA SALARIO":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getToneladaSalario();
                                        break;
                                    } 
                                    case "TONELADAS A PAGAR":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getToneladaAPagar();
                                        break;
                                    } 
                                    case "TOTAL COMISION":{
                                        data=""+   listadoPlantillaArchivoLiquidacion.get(i).getTotalComision();
                                        break;
                                    } 
                                }
                                try{
    //                                    if(validarUnidadNegocio){
    //                                        celda.setCellValue(String.valueOf(data));
    //                                        validarUnidadNegocio=false;
    //                                    }else{
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
                                                    //if(costoTotalApuntador == j){
                                                        celda.setCellValue(Double.parseDouble(String.valueOf(data).replace(",", ".")));
                                                    //}else{
                                                      //  celda.setCellValue(String.valueOf(data));
                                                    //}
                                                }catch(Exception ex){
                                                    celda.setCellValue(String.valueOf(data));
                                                }
                                            }
    //                                        }
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
                        texto.setText("Liquidación generada desde VenturaData");
                        BodyPart adjunto = new MimeBodyPart();
                        //adjunto.setDataHandler(new DataHandler(new FileDataSource("d:/futbol.png")));
                        adjunto.setDataHandler(new DataHandler(new FileDataSource(archivo)));
                        adjunto.setFileName("liquidacion "+configuracionLiquidacionliquidar.getDescripcion()+".xlsx");
                        MimeMultipart multiParte = new MimeMultipart();
                        multiParte.addBodyPart(texto);
                        multiParte.addBodyPart(adjunto);
                        message.setSubject("Liquidación Paleros");
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
        }else{
            JOptionPane.showMessageDialog(null,"Debe cargar una configuración de liquidación", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }*/
    }//GEN-LAST:event_sendMailIconMouseClicked
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Enable;
    private javax.swing.JInternalFrame InternaFrame_buscarConfiguracionLiquidacion;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JButton consultar1;
    private com.toedter.calendar.JDateChooser fechaFin_Consulta;
    private com.toedter.calendar.JDateChooser fechaInicio_Consulta;
    private javax.swing.JLabel icon_buscarClientes;
    private javax.swing.JLabel icon_buscarClientes1;
    private javax.swing.JLabel icon_exportar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel label_configuracionLiquidacion;
    private javax.swing.JLabel label_exportar;
    private javax.swing.JComboBox<Integer> pageJComboBox;
    private javax.swing.JComboBox<Integer> pageJComboBox1;
    public javax.swing.JPanel paginationPanel;
    private javax.swing.JLabel senMailLabel;
    private javax.swing.JLabel sendMailIcon;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tablaListadoVehiculosPorEquipoLiquidacion;
    // End of variables declaration//GEN-END:variables

    public void generarListadoMvtoCarbon() {
        try {
            String fecha_Inicial = "";
            String fecha_Final = "";
            int contador = 0;
            try {
                Calendar fechaI = fechaInicio_Consulta.getCalendar();
                String anoI = "" + fechaI.get(Calendar.YEAR);
                String mesI = "";
                if ((fechaI.get(Calendar.MONTH) + 1) <= 9) {
                    mesI = "0" + (fechaI.get(Calendar.MONTH) + 1);
                } else {
                    mesI = "" + (fechaI.get(Calendar.MONTH) + 1);
                }
                String diaI = "";
                if (fechaI.get(Calendar.DAY_OF_MONTH) <= 9) {
                    diaI = "0" + fechaI.get(Calendar.DAY_OF_MONTH);
                } else {
                    diaI = "" + fechaI.get(Calendar.DAY_OF_MONTH);
                }
                fecha_Inicial = anoI + "-" + mesI + "-" + diaI;
                contador++;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Verifique la fecha de inicio de la consulta", "Error!!.", JOptionPane.ERROR_MESSAGE);
            }
            try {
                Calendar fechaF = fechaFin_Consulta.getCalendar();
                String anoF = "" + fechaF.get(Calendar.YEAR);
                String mesF = "";
                if ((fechaF.get(Calendar.MONTH) + 1) <= 9) {
                    mesF = "0" + (fechaF.get(Calendar.MONTH) + 1);
                } else {
                    mesF = "" + (fechaF.get(Calendar.MONTH) + 1);
                }
                String diaF = "";
                if (fechaF.get(Calendar.DAY_OF_MONTH) <= 9) {
                    diaF = "0" + fechaF.get(Calendar.DAY_OF_MONTH);
                } else {
                    diaF = "" + fechaF.get(Calendar.DAY_OF_MONTH);
                }
                fecha_Final = anoF + "-" + mesF + "-" + diaF;
                contador++;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Verifique la fecha de fin de la consulta", "Error!!.", JOptionPane.ERROR_MESSAGE);
            }
            if (contador == 2) {//Se validaron las dos fechas y contienen formato correcto
                try {
                    int resultDosFechas = Integer.parseInt(new ControlDB_Equipo(tipoConexion).comparadorEntreDosFechas("'" + fecha_Inicial + "'", "'" + fecha_Final + "'"));
                    if (resultDosFechas < 0) {
                        JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser mayor a la fecha de Finalización", "Advertencia", JOptionPane.ERROR_MESSAGE);
                    } else {
                        /*if(resultDosFechas ==0){
                            JOptionPane.showMessageDialog(null, "Error!!.. La fecha de Inicio no puede ser Igual a la fecha de Finalización","Advertencia", JOptionPane.ERROR_MESSAGE );
                        }else{*/
                        tabla_Listar(fecha_Inicial, fecha_Final);
                        //}
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MvtoCarbon_GenerarMatriz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se puedo procesar el descargue de Carbon", "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void tabla_Listar(String data1, String data2) throws SQLException {
        tabla.setModel(crearModeloDeTabla());
        listado = new ControlDB_ConfiguracionLiquidacion(tipoConexion).buscarConfiguracionesLiquidacionesSinLiquidar(data1, data2);
        if (listado != null) {
            proveedorDeDatos = crearProveedorDeDatos(listado);
            paginadorDeTabla = new PaginadorDeTabla(tabla, proveedorDeDatos, new int[]{5, 10, 20, 50, 75, 100}, 10);
            paginadorDeTabla.crearListadoDeFilasPermitidas(this.paginationPanel);
            pageJComboBox = paginadorDeTabla.getComboBoxPage();
            events();
            pageJComboBox.setSelectedItem(Integer.parseInt("50"));
            resizeColumnWidth(tabla);
        }

    }

    public final void events() {
        pageJComboBox.addActionListener(this);
        tabla.getModel().addTableModelListener(this);
    }

    private ModeloDeTabla crearModeloDeTabla() {
        return new ModeloDeTabla<ConfiguracionLiquidacion>() {
            @Override
            public Object getValueAt(ConfiguracionLiquidacion listado1, int columnas) {
                switch (encabezadoTabla.get(columnas)) {
                    case "Código": {
                        return listado1.getCodigo();
                    }
                    case "CargoNomina": {
                        return listado1.getCargoNomina().getDescripcion();
                    }
                    case "Fecha_Inicio": {
                        return listado1.getFechaInicio();
                    }
                    case "Fecha_Fin": {
                        return listado1.getFechaFinalizacion();
                    }
                    case "Cantidad_Días": {
                        return listado1.getCantidadDias();
                    }
                    case "Descripción": {
                        return listado1.getDescripcion();
                    }
                    case "Valor_Tonelada": {
                        return listado1.getValorTonelada();
                    }
                    case "Cantiddad_Tonelada_Salario": {
                        return listado1.getCantidadToneladaSalario();
                    }
                    case "Usuario": {
                        return listado1.getUsuario().getNombres() + " " + listado1.getUsuario().getApellidos();
                    }
                    case "Estado": {
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

    public void validar() {
        encabezadoTabla = new ArrayList<>();
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
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
