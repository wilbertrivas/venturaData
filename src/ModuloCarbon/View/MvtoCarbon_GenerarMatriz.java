package ModuloCarbon.View;
  
import ModuloCarbon.Controller2.ControlDB_MvtoCarbon;
import ModuloCarbon.Model.MvtoCarbon_ListadoEquipos;
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
 
public final class MvtoCarbon_GenerarMatriz extends javax.swing.JPanel implements ActionListener, TableModelListener{
    ArrayList<MvtoCarbon_ListadoEquipos> listado=null;
    private Usuario user;
    private String tipoConexion;
    private PaginadorDeTabla<MvtoCarbon_ListadoEquipos> paginadorDeTabla;  
    ProveedorDeDatosDePaginacion<MvtoCarbon_ListadoEquipos> proveedorDeDatos;
    ArrayList<String> encabezadoTabla=null;
    public MvtoCarbon_GenerarMatriz(Usuario user, String tipoConexion) {
        initComponents();
        //Ocultamos opci??n de exportar
        //icon_exportar.show(false);
        //label_exportar.show(false); 
        
        this.user= user;
        //Validamos el logue si es Wilber o Lara para habilitar Reporte Especial
        if(user.getCodigo().equals("1111786243") || user.getCodigo().equals("94442665") || user.getCodigo().equals("25288324")){
            reporte_lara.show(true);
        }else{
            reporte_lara.show(false);
        }
        
        this.tipoConexion= tipoConexion;
        InternalFrameSelectorCampos.getContentPane().setBackground(Color.WHITE);
        InternalFrameSelectorCampos.show(false);
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
        selectorCampoPorDefecto();
        Dimension dim=super.getToolkit().getScreenSize();      
        selectorCampoPorDefecto();
        encabezadoTabla= new ArrayList<String>();
        pageJComboBox.show(false);
        horaInicio.setSelectedIndex(0);
        minutoInicio.setSelectedIndex(0);
        horaFin.setSelectedIndex(23);
        minutoFin.setSelectedIndex(59);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        InternalFrameSelectorCampos = new javax.swing.JInternalFrame();
        jButton2 = new javax.swing.JButton();
        selectAll = new javax.swing.JRadioButton();
        selectDescargue_codigo = new javax.swing.JRadioButton();
        selectDescargue_fechaRegistro = new javax.swing.JRadioButton();
        selectDescargue_deposito = new javax.swing.JRadioButton();
        selectDescargue_consecutivo = new javax.swing.JRadioButton();
        selectDescargue_placa = new javax.swing.JRadioButton();
        selectDescargue_pesoVacio = new javax.swing.JRadioButton();
        selectDescargue_pesoLleno = new javax.swing.JRadioButton();
        selectDescargue_pesoNeto = new javax.swing.JRadioButton();
        selectDescargue_numOrden = new javax.swing.JRadioButton();
        selectDescargue_fechaEntradaVehiculo = new javax.swing.JRadioButton();
        selectDescargue_registroManual = new javax.swing.JRadioButton();
        selectDescargue_clienteCodigo = new javax.swing.JRadioButton();
        selectDescargue_transportadoraNit = new javax.swing.JRadioButton();
        selectDescargue_transportadoraCodigo = new javax.swing.JRadioButton();
        selectDescargue_usuarioRegistroManualCodigo = new javax.swing.JRadioButton();
        selectDescargue_usuarioRegistroManualNombre = new javax.swing.JRadioButton();
        selectDescargue_fechaSalidaVehiculo = new javax.swing.JRadioButton();
        selectDescargue_fechaInicioDescargue = new javax.swing.JRadioButton();
        selectDescargue_cantidadMinutosDescargue = new javax.swing.JRadioButton();
        titulo2 = new javax.swing.JLabel();
        titulo13 = new javax.swing.JLabel();
        jSeparator21 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        selectDescargue_mes = new javax.swing.JRadioButton();
        selectDescargue_clienteNombre = new javax.swing.JRadioButton();
        selectDescargue_articuloCodigo = new javax.swing.JRadioButton();
        selectDescargue_centroOperacion = new javax.swing.JRadioButton();
        selectDescargue_centroCostoMayor = new javax.swing.JRadioButton();
        selectDescargue_observaci??n = new javax.swing.JRadioButton();
        selectDescargue_estado = new javax.swing.JRadioButton();
        selectDescargue_conexionPesoCcarga = new javax.swing.JRadioButton();
        selectDescargue_fechaFinDescargue = new javax.swing.JRadioButton();
        selectDescargue_centroCostoAuxiliarOrigen = new javax.swing.JRadioButton();
        selectDescargue_centroCosto = new javax.swing.JRadioButton();
        selectDescargue_articuloUnidadNegocio = new javax.swing.JRadioButton();
        selectDescargue_laborRealizada = new javax.swing.JRadioButton();
        selectDescargue_articuloNombre = new javax.swing.JRadioButton();
        selectDescargue_articuloCodigoERP = new javax.swing.JRadioButton();
        selectDescargue_articuloTipo = new javax.swing.JRadioButton();
        selectDescargue_transportadoraNombre = new javax.swing.JRadioButton();
        selectDescargue_usuarioQuienRegistraVehiculoCodigo = new javax.swing.JRadioButton();
        selectDescargue_usuarioQuienRegistraVehiculoNombre = new javax.swing.JRadioButton();
        selectDescargue_usuarioRegistroManualCorreo = new javax.swing.JRadioButton();
        selectMvtoEquipo_clienteCodigo = new javax.swing.JRadioButton();
        selectAsignacion_equipoCodigo = new javax.swing.JRadioButton();
        selectAsignacion_equipoMarca = new javax.swing.JRadioButton();
        selectAsignacion_equipoModelo = new javax.swing.JRadioButton();
        selectMvtoEquipo_fechaRegistro = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioRegistraMvto_nombre = new javax.swing.JRadioButton();
        selectMvtoEquipo_cantidadMinutosDescargue = new javax.swing.JRadioButton();
        selectMvtoEquipo_numeroOrden = new javax.swing.JRadioButton();
        selectAsignacion_equipoTipo = new javax.swing.JRadioButton();
        selectMvtoEquipo_clienteNombre = new javax.swing.JRadioButton();
        selectMvtoEquipo_motonaveDescripci??n = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroOperacion = new javax.swing.JRadioButton();
        selectMvtoEquipo_subcentroCosto = new javax.swing.JRadioButton();
        selectAsignacion_fechaInicio = new javax.swing.JRadioButton();
        selectAsignacion_fechaFinalizacion = new javax.swing.JRadioButton();
        selectAsignacion_cantidadMinutos_Programados = new javax.swing.JRadioButton();
        selectAsignacion_equipoPertenencia = new javax.swing.JRadioButton();
        selectMvtoEquipo_proveedorEquipoNit = new javax.swing.JRadioButton();
        selectMvtoEquipo_proveedorEquipoNombre = new javax.swing.JRadioButton();
        selectMvtoEquipo_laborRealizada = new javax.swing.JRadioButton();
        selectMvtoEquipo_fechaInicioDescargue = new javax.swing.JRadioButton();
        selectMvtoEquipo_fechaFinDescargue = new javax.swing.JRadioButton();
        selectMvtoEquipo_ValorHoraEquipo = new javax.swing.JRadioButton();
        selectMvtoEquipo_costoTotal = new javax.swing.JRadioButton();
        selectMvtoEquipo_Recobro = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioRegistraMvto_codigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_CausalInactividad = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre = new javax.swing.JRadioButton();
        selectMvtoEquipo_autorizacionRecobro = new javax.swing.JRadioButton();
        selectMvtoEquipo_MotivoParada = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion = new javax.swing.JRadioButton();
        selectMvtoEquipo_Inactividad = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioInactividad_codigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_DesdeCarbon = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioInactividad_nombre = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroCostoMayor = new javax.swing.JRadioButton();
        selectMvtoEquipo_observacion = new javax.swing.JRadioButton();
        selectMvtoEquipo_estado = new javax.swing.JRadioButton();
        selectAsignacion_equipoDescripcion = new javax.swing.JRadioButton();
        selectAsignacion_codigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroCosto = new javax.swing.JRadioButton();
        selectAsignacion_fechaRegistro = new javax.swing.JRadioButton();
        selectMvtoEquipo_articuloCodigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_articuloDescripcion = new javax.swing.JRadioButton();
        selectMvtoEquipo_motonaveCodigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroCostoAxiliarOrigen = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroCostoAuxiliarDestino = new javax.swing.JRadioButton();
        selectMvtoEquipo_mes = new javax.swing.JRadioButton();
        selectMvtoEquipo_codigo = new javax.swing.JRadioButton();
        selectDescargue_usuarioQuienRegistraVehiculoCorreo = new javax.swing.JRadioButton();
        selectDescargue_centroCostoAuxiliarDestino = new javax.swing.JRadioButton();
        selectDescargue_subcentroCosto = new javax.swing.JRadioButton();
        selectDescargue_lavadoVehiculoObservacion = new javax.swing.JRadioButton();
        selectDescargue_lavadoVehiculo = new javax.swing.JRadioButton();
        selectDescargue_usuarioQuienCierraVehiculoCodigo = new javax.swing.JRadioButton();
        selectDescargue_usuarioQuienCierraVehiculoNombre = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioCierraMvto_codigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioCierraMvto_nombre = new javax.swing.JRadioButton();
        Motivo_No_LavadoVeh??culo = new javax.swing.JRadioButton();
        Valor_Recaudo_Equipo_LavadoVeh??culos = new javax.swing.JRadioButton();
        Valor_Recaudo_Empresa_LavadoVeh??culos = new javax.swing.JRadioButton();
        Equipo_Quien_Realiza_LavadoVeh??culo_C??digo = new javax.swing.JRadioButton();
        Costo_Lavado_Veh??culo = new javax.swing.JRadioButton();
        Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo = new javax.swing.JRadioButton();
        Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n = new javax.swing.JRadioButton();
        selectDescargue_dia = new javax.swing.JRadioButton();
        titulo12 = new javax.swing.JLabel();
        titulo21 = new javax.swing.JLabel();
        titulo17 = new javax.swing.JLabel();
        titulo20 = new javax.swing.JLabel();
        titulo23 = new javax.swing.JLabel();
        titulo19 = new javax.swing.JLabel();
        titulo24 = new javax.swing.JLabel();
        titulo26 = new javax.swing.JLabel();
        titulo27 = new javax.swing.JLabel();
        reporte_lara = new javax.swing.JButton();
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
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
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

        InternalFrameSelectorCampos.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrameSelectorCampos.setClosable(true);
        InternalFrameSelectorCampos.setVisible(false);
        InternalFrameSelectorCampos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCamposAplicarConfig.png"))); // NOI18N
        jButton2.setText("Aplicar Configuraci??n");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 610, 230, 40));

        selectAll.setBackground(new java.awt.Color(255, 255, 255));
        selectAll.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        selectAll.setText("Todos");
        selectAll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectAll.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectAllItemStateChanged(evt);
            }
        });
        selectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAllActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, 110, 20));

        selectDescargue_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_codigo.setText("C??digo");
        selectDescargue_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectDescargue_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectDescargue_codigoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 190, -1));

        selectDescargue_fechaRegistro.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_fechaRegistro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_fechaRegistro.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_fechaRegistro.setText("Fecha_Registro");
        selectDescargue_fechaRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_fechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 190, -1));

        selectDescargue_deposito.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_deposito.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_deposito.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_deposito.setText("Deposito");
        selectDescargue_deposito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_deposito, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 190, -1));

        selectDescargue_consecutivo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_consecutivo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_consecutivo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_consecutivo.setText("Consecutivo");
        selectDescargue_consecutivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_consecutivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 190, -1));

        selectDescargue_placa.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_placa.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_placa.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_placa.setText("Placa");
        selectDescargue_placa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_placa, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 190, -1));

        selectDescargue_pesoVacio.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_pesoVacio.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_pesoVacio.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_pesoVacio.setText("Peso_Vacio");
        selectDescargue_pesoVacio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectDescargue_pesoVacio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectDescargue_pesoVacioActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_pesoVacio, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, 190, -1));

        selectDescargue_pesoLleno.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_pesoLleno.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_pesoLleno.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_pesoLleno.setText("Peso_Lleno");
        selectDescargue_pesoLleno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_pesoLleno, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 280, 190, -1));

        selectDescargue_pesoNeto.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_pesoNeto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_pesoNeto.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_pesoNeto.setText("Peso_Neto");
        selectDescargue_pesoNeto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_pesoNeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, 190, -1));

        selectDescargue_numOrden.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_numOrden.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_numOrden.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_numOrden.setText("N??mero Orden");
        selectDescargue_numOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_numOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, 190, -1));

        selectDescargue_fechaEntradaVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_fechaEntradaVehiculo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_fechaEntradaVehiculo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_fechaEntradaVehiculo.setText("Fecha_Entrada_Vehiculo");
        selectDescargue_fechaEntradaVehiculo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_fechaEntradaVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, 190, -1));

        selectDescargue_registroManual.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_registroManual.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_registroManual.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_registroManual.setText("RegistroManual");
        selectDescargue_registroManual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_registroManual, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 540, 190, -1));

        selectDescargue_clienteCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_clienteCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_clienteCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_clienteCodigo.setText("Cliente_C??digo");
        selectDescargue_clienteCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_clienteCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 190, -1));

        selectDescargue_transportadoraNit.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_transportadoraNit.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_transportadoraNit.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_transportadoraNit.setText("Transportadora_NIT");
        selectDescargue_transportadoraNit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_transportadoraNit, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 190, -1));

        selectDescargue_transportadoraCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_transportadoraCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_transportadoraCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_transportadoraCodigo.setText("Transportadora_C??digo");
        selectDescargue_transportadoraCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_transportadoraCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 190, -1));

        selectDescargue_usuarioRegistroManualCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_usuarioRegistroManualCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_usuarioRegistroManualCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_usuarioRegistroManualCodigo.setText("UsuarioRegistr??Manual_C??digo");
        selectDescargue_usuarioRegistroManualCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioRegistroManualCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 560, 190, -1));

        selectDescargue_usuarioRegistroManualNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_usuarioRegistroManualNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_usuarioRegistroManualNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_usuarioRegistroManualNombre.setText("UsuarioRegistr??Manual_Nombre");
        selectDescargue_usuarioRegistroManualNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioRegistroManualNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 580, 190, -1));

        selectDescargue_fechaSalidaVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_fechaSalidaVehiculo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_fechaSalidaVehiculo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_fechaSalidaVehiculo.setText("Fecha_Salida_Vehiculo");
        selectDescargue_fechaSalidaVehiculo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_fechaSalidaVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, 190, -1));

        selectDescargue_fechaInicioDescargue.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_fechaInicioDescargue.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_fechaInicioDescargue.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_fechaInicioDescargue.setText("Fecha_Inicio_Descargue");
        selectDescargue_fechaInicioDescargue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_fechaInicioDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 190, -1));

        selectDescargue_cantidadMinutosDescargue.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_cantidadMinutosDescargue.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_cantidadMinutosDescargue.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_cantidadMinutosDescargue.setText("Cantidad_Minutos_Descargue");
        selectDescargue_cantidadMinutosDescargue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_cantidadMinutosDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 400, 190, -1));

        titulo2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo2.setText("SELECTOR DE CAMPOS");
        titulo2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1320, 40));

        titulo13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo13.setText("DESCARGUE DE CARBON");
        titulo13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 600, 20));

        jSeparator21.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternalFrameSelectorCampos.getContentPane().add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 0, 430));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCamposDefauls.png"))); // NOI18N
        jButton3.setText("Campos Predeterminados");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 610, 230, 40));

        selectDescargue_mes.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_mes.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_mes.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_mes.setText("Mes");
        selectDescargue_mes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 190, -1));

        selectDescargue_clienteNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_clienteNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_clienteNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_clienteNombre.setText("Cliente_Nombre");
        selectDescargue_clienteNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_clienteNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 190, -1));

        selectDescargue_articuloCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_articuloCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_articuloCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_articuloCodigo.setText("Articulo_C??digo");
        selectDescargue_articuloCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectDescargue_articuloCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectDescargue_articuloCodigoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_articuloCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 190, -1));

        selectDescargue_centroOperacion.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_centroOperacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_centroOperacion.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_centroOperacion.setText("Centro Operaci??n");
        selectDescargue_centroOperacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 190, -1));

        selectDescargue_centroCostoMayor.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_centroCostoMayor.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_centroCostoMayor.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_centroCostoMayor.setText("CentroCosto_Mayor");
        selectDescargue_centroCostoMayor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectDescargue_centroCostoMayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectDescargue_centroCostoMayorActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_centroCostoMayor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 190, -1));

        selectDescargue_observaci??n.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_observaci??n.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_observaci??n.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_observaci??n.setText("Observaci??n");
        selectDescargue_observaci??n.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_observaci??n, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 480, 190, -1));

        selectDescargue_estado.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_estado.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_estado.setText("Estado");
        selectDescargue_estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 500, 190, -1));

        selectDescargue_conexionPesoCcarga.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_conexionPesoCcarga.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_conexionPesoCcarga.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_conexionPesoCcarga.setText("Conexion_Peso_Ccarga");
        selectDescargue_conexionPesoCcarga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_conexionPesoCcarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 520, 190, -1));

        selectDescargue_fechaFinDescargue.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_fechaFinDescargue.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_fechaFinDescargue.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_fechaFinDescargue.setText("Fecha_Final_Descargue");
        selectDescargue_fechaFinDescargue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_fechaFinDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, 190, -1));

        selectDescargue_centroCostoAuxiliarOrigen.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_centroCostoAuxiliarOrigen.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_centroCostoAuxiliarOrigen.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_centroCostoAuxiliarOrigen.setText("Centro Costo Auxiliar_Origen");
        selectDescargue_centroCostoAuxiliarOrigen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_centroCostoAuxiliarOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 190, -1));

        selectDescargue_centroCosto.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_centroCosto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_centroCosto.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_centroCosto.setText("Centro Costo");
        selectDescargue_centroCosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_centroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 190, -1));

        selectDescargue_articuloUnidadNegocio.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_articuloUnidadNegocio.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_articuloUnidadNegocio.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_articuloUnidadNegocio.setText("Art??culo_Unidad_Negocio");
        selectDescargue_articuloUnidadNegocio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_articuloUnidadNegocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 190, -1));

        selectDescargue_laborRealizada.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_laborRealizada.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_laborRealizada.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_laborRealizada.setText("Labor Realizada");
        selectDescargue_laborRealizada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectDescargue_laborRealizada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectDescargue_laborRealizadaActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_laborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 190, -1));

        selectDescargue_articuloNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_articuloNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_articuloNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_articuloNombre.setText("Art??culo_Nombre");
        selectDescargue_articuloNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_articuloNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 190, -1));

        selectDescargue_articuloCodigoERP.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_articuloCodigoERP.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_articuloCodigoERP.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_articuloCodigoERP.setText("Art??culo_C??digo_ERP");
        selectDescargue_articuloCodigoERP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_articuloCodigoERP, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 190, -1));

        selectDescargue_articuloTipo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_articuloTipo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_articuloTipo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_articuloTipo.setText("Art??culo_Tipo");
        selectDescargue_articuloTipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_articuloTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 190, -1));

        selectDescargue_transportadoraNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_transportadoraNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_transportadoraNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_transportadoraNombre.setText("Transportadora_Nombre");
        selectDescargue_transportadoraNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_transportadoraNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 190, -1));

        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setText("UsuarioQuienRegistr??Veh??culo_C??digo");
        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioQuienRegistraVehiculoCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 420, 210, -1));

        selectDescargue_usuarioQuienRegistraVehiculoNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_usuarioQuienRegistraVehiculoNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_usuarioQuienRegistraVehiculoNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_usuarioQuienRegistraVehiculoNombre.setText("UsuarioQuienRegistr??Veh??culo_Nombre");
        selectDescargue_usuarioQuienRegistraVehiculoNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioQuienRegistraVehiculoNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 440, 220, -1));

        selectDescargue_usuarioRegistroManualCorreo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_usuarioRegistroManualCorreo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_usuarioRegistroManualCorreo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_usuarioRegistroManualCorreo.setText("UsuarioRegistr??Manual_Correo");
        selectDescargue_usuarioRegistroManualCorreo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectDescargue_usuarioRegistroManualCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectDescargue_usuarioRegistroManualCorreoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioRegistroManualCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 150, 190, -1));

        selectMvtoEquipo_clienteCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_clienteCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_clienteCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_clienteCodigo.setText("Cliente C??digo");
        selectMvtoEquipo_clienteCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_clienteCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 80, 200, -1));

        selectAsignacion_equipoCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoCodigo.setText("Equipo C??digo");
        selectAsignacion_equipoCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 170, 190, -1));

        selectAsignacion_equipoMarca.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoMarca.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoMarca.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoMarca.setText("Equipo Marca");
        selectAsignacion_equipoMarca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 210, 190, -1));

        selectAsignacion_equipoModelo.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoModelo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoModelo.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoModelo.setText("Equipo Modelo");
        selectAsignacion_equipoModelo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 230, 190, -1));

        selectMvtoEquipo_fechaRegistro.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_fechaRegistro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_fechaRegistro.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_fechaRegistro.setText("Fecha_Registro");
        selectMvtoEquipo_fechaRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_fechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 350, 190, -1));

        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setText("Nombre_Usuario_Registra_MvtoEquipo");
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioRegistraMvto_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 340, 220, -1));

        selectMvtoEquipo_cantidadMinutosDescargue.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_cantidadMinutosDescargue.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_cantidadMinutosDescargue.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_cantidadMinutosDescargue.setText("Cantidad_Minutos_Descargue");
        selectMvtoEquipo_cantidadMinutosDescargue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_cantidadMinutosDescargue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_cantidadMinutosDescargueActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_cantidadMinutosDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 240, 190, -1));

        selectMvtoEquipo_numeroOrden.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_numeroOrden.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_numeroOrden.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_numeroOrden.setText("N??mero Orden");
        selectMvtoEquipo_numeroOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_numeroOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 410, 190, -1));

        selectAsignacion_equipoTipo.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoTipo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoTipo.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoTipo.setText("Equipo Tipo");
        selectAsignacion_equipoTipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectAsignacion_equipoTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsignacion_equipoTipoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 190, 190, -1));

        selectMvtoEquipo_clienteNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_clienteNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_clienteNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_clienteNombre.setText("Cliente Nombre");
        selectMvtoEquipo_clienteNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_clienteNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 100, 200, -1));

        selectMvtoEquipo_motonaveDescripci??n.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_motonaveDescripci??n.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_motonaveDescripci??n.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_motonaveDescripci??n.setText("Motonave Descripci??n");
        selectMvtoEquipo_motonaveDescripci??n.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_motonaveDescripci??n.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_motonaveDescripci??nActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_motonaveDescripci??n, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 180, 200, -1));

        selectMvtoEquipo_centroOperacion.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroOperacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroOperacion.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroOperacion.setText("Centro Operaci??n");
        selectMvtoEquipo_centroOperacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 430, 200, -1));

        selectMvtoEquipo_subcentroCosto.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_subcentroCosto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_subcentroCosto.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_subcentroCosto.setText("Subcentro Costo");
        selectMvtoEquipo_subcentroCosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_subcentroCosto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_subcentroCostoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_subcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 450, 200, -1));

        selectAsignacion_fechaInicio.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_fechaInicio.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_fechaInicio.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_fechaInicio.setText("Fecha_inicio");
        selectAsignacion_fechaInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectAsignacion_fechaInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsignacion_fechaInicioActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 270, 190, -1));

        selectAsignacion_fechaFinalizacion.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_fechaFinalizacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_fechaFinalizacion.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_fechaFinalizacion.setText("Fecha_Finalizaci??n");
        selectAsignacion_fechaFinalizacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_fechaFinalizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 290, 190, -1));

        selectAsignacion_cantidadMinutos_Programados.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_cantidadMinutos_Programados.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_cantidadMinutos_Programados.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_cantidadMinutos_Programados.setText("Cantidad_Minutos_Programadas");
        selectAsignacion_cantidadMinutos_Programados.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectAsignacion_cantidadMinutos_Programados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsignacion_cantidadMinutos_ProgramadosActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_cantidadMinutos_Programados, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 310, 190, -1));

        selectAsignacion_equipoPertenencia.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoPertenencia.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoPertenencia.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoPertenencia.setText("Equipo Pertenencia");
        selectAsignacion_equipoPertenencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoPertenencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 270, 190, -1));

        selectMvtoEquipo_proveedorEquipoNit.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_proveedorEquipoNit.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_proveedorEquipoNit.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_proveedorEquipoNit.setText("Proveedor Equipo NIT");
        selectMvtoEquipo_proveedorEquipoNit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_proveedorEquipoNit, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 370, 190, -1));

        selectMvtoEquipo_proveedorEquipoNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_proveedorEquipoNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_proveedorEquipoNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_proveedorEquipoNombre.setText("Proveedor Equipo Nombre");
        selectMvtoEquipo_proveedorEquipoNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_proveedorEquipoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_proveedorEquipoNombreActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_proveedorEquipoNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 390, 190, -1));

        selectMvtoEquipo_laborRealizada.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_laborRealizada.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_laborRealizada.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_laborRealizada.setText("Labor Realizada");
        selectMvtoEquipo_laborRealizada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_laborRealizada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_laborRealizadaActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_laborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 60, 190, -1));

        selectMvtoEquipo_fechaInicioDescargue.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_fechaInicioDescargue.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_fechaInicioDescargue.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_fechaInicioDescargue.setText("Fecha Inicio_Actividad");
        selectMvtoEquipo_fechaInicioDescargue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_fechaInicioDescargue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_fechaInicioDescargueActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_fechaInicioDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 200, 190, -1));

        selectMvtoEquipo_fechaFinDescargue.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_fechaFinDescargue.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_fechaFinDescargue.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_fechaFinDescargue.setText("Fecha Final_Actividad");
        selectMvtoEquipo_fechaFinDescargue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_fechaFinDescargue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_fechaFinDescargueActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_fechaFinDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 220, 190, -1));

        selectMvtoEquipo_ValorHoraEquipo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_ValorHoraEquipo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_ValorHoraEquipo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_ValorHoraEquipo.setText("Valor_Hora_Equipo");
        selectMvtoEquipo_ValorHoraEquipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_ValorHoraEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 260, 190, -1));

        selectMvtoEquipo_costoTotal.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_costoTotal.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_costoTotal.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_costoTotal.setText("Costo_Total");
        selectMvtoEquipo_costoTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_costoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 280, 190, -1));

        selectMvtoEquipo_Recobro.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_Recobro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_Recobro.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_Recobro.setText("Recobro");
        selectMvtoEquipo_Recobro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_Recobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 300, 190, -1));

        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setText("C??digo_Usuario_Registra_MvtoEquipo");
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioRegistraMvto_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 320, 210, -1));

        selectMvtoEquipo_CausalInactividad.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_CausalInactividad.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_CausalInactividad.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_CausalInactividad.setText("Causal_Inactividad");
        selectMvtoEquipo_CausalInactividad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_CausalInactividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 60, 200, -1));

        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setText("C??digo_Usuario_Autoriza_Recobro");
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioAutorizaRecobro_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 360, 210, -1));

        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setText("Nombre_Usuario_Autoriza_Recobro");
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioAutorizaRecobro_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 380, 220, -1));

        selectMvtoEquipo_autorizacionRecobro.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_autorizacionRecobro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_autorizacionRecobro.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_autorizacionRecobro.setText("Autorizaci??n_Recobro");
        selectMvtoEquipo_autorizacionRecobro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_autorizacionRecobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 440, 220, -1));

        selectMvtoEquipo_MotivoParada.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_MotivoParada.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_MotivoParada.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_MotivoParada.setText("Motivo_Parada");
        selectMvtoEquipo_MotivoParada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_MotivoParada, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 120, 200, -1));

        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setText("Observaci??n_Autorizaci??n_Recobro");
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioAutorizaRecobro_observacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 460, 220, -1));

        selectMvtoEquipo_Inactividad.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_Inactividad.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_Inactividad.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_Inactividad.setText("Inactividad");
        selectMvtoEquipo_Inactividad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_Inactividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 480, 220, -1));

        selectMvtoEquipo_UsuarioInactividad_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioInactividad_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioInactividad_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioInactividad_codigo.setText("C??digo_Usuario_Inactividad");
        selectMvtoEquipo_UsuarioInactividad_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_UsuarioInactividad_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_UsuarioInactividad_codigoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioInactividad_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 80, 190, -1));

        selectMvtoEquipo_DesdeCarbon.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_DesdeCarbon.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_DesdeCarbon.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_DesdeCarbon.setText("Desde_Carb??n");
        selectMvtoEquipo_DesdeCarbon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_DesdeCarbon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 180, 200, -1));

        selectMvtoEquipo_UsuarioInactividad_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioInactividad_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioInactividad_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioInactividad_nombre.setText("Nombre_Usuario_Inactividad");
        selectMvtoEquipo_UsuarioInactividad_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioInactividad_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 100, 200, -1));

        selectMvtoEquipo_centroCostoMayor.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCostoMayor.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCostoMayor.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCostoMayor.setText("CentroCosto Mayor");
        selectMvtoEquipo_centroCostoMayor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroCostoMayor, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 530, 200, -1));

        selectMvtoEquipo_observacion.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_observacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_observacion.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_observacion.setText("Observaci??n");
        selectMvtoEquipo_observacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_observacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 140, 200, -1));

        selectMvtoEquipo_estado.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_estado.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_estado.setText("Estado");
        selectMvtoEquipo_estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 160, 200, -1));

        selectAsignacion_equipoDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoDescripcion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoDescripcion.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoDescripcion.setText("Equipo Descripci??n");
        selectAsignacion_equipoDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectAsignacion_equipoDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsignacion_equipoDescripcionActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 250, 190, -1));

        selectAsignacion_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_codigo.setText("C??digo");
        selectAsignacion_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 230, 190, -1));

        selectMvtoEquipo_centroCosto.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCosto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCosto.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCosto.setText("Centro Costo");
        selectMvtoEquipo_centroCosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 510, 190, -1));

        selectAsignacion_fechaRegistro.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_fechaRegistro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_fechaRegistro.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_fechaRegistro.setText("Fecha_Registro");
        selectAsignacion_fechaRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_fechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 250, 190, -1));

        selectMvtoEquipo_articuloCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_articuloCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_articuloCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_articuloCodigo.setText("Articulo c??digo");
        selectMvtoEquipo_articuloCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_articuloCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_articuloCodigoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_articuloCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 120, 200, -1));

        selectMvtoEquipo_articuloDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_articuloDescripcion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_articuloDescripcion.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_articuloDescripcion.setText("Articulo Descripci??n");
        selectMvtoEquipo_articuloDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_articuloDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_articuloDescripcionActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_articuloDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 140, 200, -1));

        selectMvtoEquipo_motonaveCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_motonaveCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_motonaveCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_motonaveCodigo.setText("Motonave C??digo");
        selectMvtoEquipo_motonaveCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_motonaveCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_motonaveCodigoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_motonaveCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 160, 200, -1));

        selectMvtoEquipo_centroCostoAxiliarOrigen.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCostoAxiliarOrigen.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCostoAxiliarOrigen.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCostoAxiliarOrigen.setText("Auxiliar Centro Costo Origen");
        selectMvtoEquipo_centroCostoAxiliarOrigen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroCostoAxiliarOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 470, 200, -1));

        selectMvtoEquipo_centroCostoAuxiliarDestino.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCostoAuxiliarDestino.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCostoAuxiliarDestino.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCostoAuxiliarDestino.setText("Auxiliar Centro Costo Destino");
        selectMvtoEquipo_centroCostoAuxiliarDestino.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroCostoAuxiliarDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 490, 200, -1));

        selectMvtoEquipo_mes.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_mes.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_mes.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_mes.setText("Mes_Registro");
        selectMvtoEquipo_mes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 330, 190, -1));

        selectMvtoEquipo_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_codigo.setText("C??digo");
        selectMvtoEquipo_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 310, 190, -1));

        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setText("UsuarioQuienRegistr??Veh??culo_Correo");
        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioQuienRegistraVehiculoCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 460, 220, -1));

        selectDescargue_centroCostoAuxiliarDestino.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_centroCostoAuxiliarDestino.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_centroCostoAuxiliarDestino.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_centroCostoAuxiliarDestino.setText("Centro Costo Auxiliar_Destino");
        selectDescargue_centroCostoAuxiliarDestino.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_centroCostoAuxiliarDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 190, -1));

        selectDescargue_subcentroCosto.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_subcentroCosto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_subcentroCosto.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_subcentroCosto.setText("Subcentro Costo");
        selectDescargue_subcentroCosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_subcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 190, -1));

        selectDescargue_lavadoVehiculoObservacion.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_lavadoVehiculoObservacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_lavadoVehiculoObservacion.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_lavadoVehiculoObservacion.setText("Lavado Veh??culo Observaci??n");
        selectDescargue_lavadoVehiculoObservacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_lavadoVehiculoObservacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 190, -1));

        selectDescargue_lavadoVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_lavadoVehiculo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_lavadoVehiculo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_lavadoVehiculo.setText("Lavado Veh??culo");
        selectDescargue_lavadoVehiculo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_lavadoVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 190, -1));

        selectDescargue_usuarioQuienCierraVehiculoCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_usuarioQuienCierraVehiculoCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_usuarioQuienCierraVehiculoCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_usuarioQuienCierraVehiculoCodigo.setText("UsuarioQuienCierraVeh??culo_C??digo");
        selectDescargue_usuarioQuienCierraVehiculoCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioQuienCierraVehiculoCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 210, -1));

        selectDescargue_usuarioQuienCierraVehiculoNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_usuarioQuienCierraVehiculoNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_usuarioQuienCierraVehiculoNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_usuarioQuienCierraVehiculoNombre.setText("UsuarioQuienCierraVeh??culo_Nombre");
        selectDescargue_usuarioQuienCierraVehiculoNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioQuienCierraVehiculoNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 90, 220, -1));

        selectMvtoEquipo_UsuarioCierraMvto_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioCierraMvto_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioCierraMvto_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioCierraMvto_codigo.setText("C??digo_Usuario_Cierra_MvtoEquipo");
        selectMvtoEquipo_UsuarioCierraMvto_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioCierraMvto_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 400, 210, -1));

        selectMvtoEquipo_UsuarioCierraMvto_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioCierraMvto_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioCierraMvto_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioCierraMvto_nombre.setText("Nombre_Usuario_Cierra_MvtoEquipo");
        selectMvtoEquipo_UsuarioCierraMvto_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioCierraMvto_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 420, 220, -1));

        Motivo_No_LavadoVeh??culo.setBackground(new java.awt.Color(255, 255, 255));
        Motivo_No_LavadoVeh??culo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Motivo_No_LavadoVeh??culo.setForeground(new java.awt.Color(51, 51, 51));
        Motivo_No_LavadoVeh??culo.setText("Motivo_No_LavadoVeh??culo");
        Motivo_No_LavadoVeh??culo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Motivo_No_LavadoVeh??culo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 560, 190, -1));

        Valor_Recaudo_Equipo_LavadoVeh??culos.setBackground(new java.awt.Color(255, 255, 255));
        Valor_Recaudo_Equipo_LavadoVeh??culos.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Valor_Recaudo_Equipo_LavadoVeh??culos.setForeground(new java.awt.Color(51, 51, 51));
        Valor_Recaudo_Equipo_LavadoVeh??culos.setText("Valor_Recaudo_Equipo_LavadoVeh??culos");
        Valor_Recaudo_Equipo_LavadoVeh??culos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Valor_Recaudo_Equipo_LavadoVeh??culos, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 260, -1));

        Valor_Recaudo_Empresa_LavadoVeh??culos.setBackground(new java.awt.Color(255, 255, 255));
        Valor_Recaudo_Empresa_LavadoVeh??culos.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Valor_Recaudo_Empresa_LavadoVeh??culos.setForeground(new java.awt.Color(51, 51, 51));
        Valor_Recaudo_Empresa_LavadoVeh??culos.setText("Valor_Recaudo_Empresa_LavadoVeh??culos");
        Valor_Recaudo_Empresa_LavadoVeh??culos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Valor_Recaudo_Empresa_LavadoVeh??culos, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, 260, -1));

        Equipo_Quien_Realiza_LavadoVeh??culo_C??digo.setBackground(new java.awt.Color(255, 255, 255));
        Equipo_Quien_Realiza_LavadoVeh??culo_C??digo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Equipo_Quien_Realiza_LavadoVeh??culo_C??digo.setForeground(new java.awt.Color(51, 51, 51));
        Equipo_Quien_Realiza_LavadoVeh??culo_C??digo.setText("Equipo_Quien_Realiza_LavadoVeh??culo_C??digo");
        Equipo_Quien_Realiza_LavadoVeh??culo_C??digo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Equipo_Quien_Realiza_LavadoVeh??culo_C??digo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 260, -1));

        Costo_Lavado_Veh??culo.setBackground(new java.awt.Color(255, 255, 255));
        Costo_Lavado_Veh??culo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Costo_Lavado_Veh??culo.setForeground(new java.awt.Color(51, 51, 51));
        Costo_Lavado_Veh??culo.setText("Costo_Lavado_Veh??culo");
        Costo_Lavado_Veh??culo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Costo_Lavado_Veh??culo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, 260, -1));

        Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo.setBackground(new java.awt.Color(255, 255, 255));
        Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo.setForeground(new java.awt.Color(51, 51, 51));
        Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo.setText("Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo");
        Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 280, -1));

        Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n.setBackground(new java.awt.Color(255, 255, 255));
        Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n.setForeground(new java.awt.Color(51, 51, 51));
        Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n.setText("Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n");
        Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, 280, -1));

        selectDescargue_dia.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_dia.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_dia.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_dia.setText("D??a");
        selectDescargue_dia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_dia, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 190, -1));

        titulo12.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        titulo12.setText("PROGRAMACI??N (ASIGNACI??N)");
        titulo12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 200, 240, 20));

        titulo21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo21, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 50, 240, 550));

        titulo17.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        titulo17.setText("DATOS DEL EQUIPO");
        titulo17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo17, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 120, 240, 20));

        titulo20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo20, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 70, 240, 0));

        titulo23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo23.setText("Mvto Equipo");
        titulo23.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo23, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 290, 240, 20));

        titulo19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 50, 240, 550));

        titulo24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo24.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo24, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, 240, 460));

        titulo26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo26.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 290, 530));

        titulo27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo27.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo27, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 310, 530));

        reporte_lara.setBackground(new java.awt.Color(255, 255, 255));
        reporte_lara.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        reporte_lara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/reporte_2.png"))); // NOI18N
        reporte_lara.setText("Reporte_Lara");
        reporte_lara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporte_laraActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(reporte_lara, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 610, 210, 40));

        add(InternalFrameSelectorCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1560, 740));

        titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo.setForeground(new java.awt.Color(0, 153, 153));
        titulo.setText("GENERAR MATRIZ DE OPERACI??N CON VEH??CULOS");
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

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCampo.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 60, 50, 40));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Campos");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 40, 40, 30));

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

        pageJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pageJComboBoxActionPerformed(evt);
            }
        });
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
        int  valuePaginador=0;
        try{
            valuePaginador=Integer.parseInt(pageJComboBox.getSelectedItem().toString());
        }catch(Exception e){
        }
        
        paginationPanel.removeAll();
        validarSeleccionCampos();
        generarListadoMvtoCarbon();
        resizeColumnWidth(tabla);
        if(valuePaginador != 0){
            pageJComboBox.setSelectedItem(valuePaginador);
        }
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        InternalFrameSelectorCampos.show(false);
        if(listado != null){
            paginationPanel.removeAll();
            validarSeleccionCampos();
            tabla.setModel(crearModeloDeTabla());
            //listado=new ControlDB_MvtoEquipo(tipoConexion).buscar_mvtoEquiposActivosGeneral(data1, data2);
            proveedorDeDatos = crearProveedorDeDatos(listado); 
            paginadorDeTabla = new PaginadorDeTabla(tabla, proveedorDeDatos, new int[]{5, 10, 20, 50, 75, 100}, 10);
            paginadorDeTabla.crearListadoDeFilasPermitidas(this.paginationPanel);
            pageJComboBox = paginadorDeTabla.getComboBoxPage();
            events();
            pageJComboBox.setSelectedItem(Integer.parseInt("50"));
            resizeColumnWidth(tabla);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void selectAllItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectAllItemStateChanged
        if(selectAll.isSelected()){
            selectorCampoTodos();
        }else{
            selectorCampoNinguno();
        }
    }//GEN-LAST:event_selectAllItemStateChanged

    private void consultarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultarMouseExited
        alerta_fechaInicio.setText("");
        alerta_fechaFinal.setText("");
    }//GEN-LAST:event_consultarMouseExited

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        selectorCampoPorDefecto();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        InternalFrameSelectorCampos.show(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void icon_exportarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icon_exportarMouseClicked
         if(listado != null){
           //ArrayList<String> colmnas={"Hola", "Hola", "Hola"});
            JFileChooser selecArchivo= new JFileChooser();
            File archivo;
            //selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
            //selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
            if(selecArchivo.showDialog(null, "Exportar") ==JFileChooser.APPROVE_OPTION){
                archivo=new File(selecArchivo.getSelectedFile()+".xlsx");
                //archivo=selecArchivo.getSelectedFile();
                if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
                    //JOptionPane.showMessageDialog(null, Exportar(archivo));
                    Workbook wb;
                    String respuesta="No se realiz?? con exito la exportacion";
                    listado.size();
                   
                    //int numFila=tabla.getRowCount(), numColumna=tabla.getColumnCount();
                    int numFila=listado.size();
                    int numColumna=encabezadoTabla.size();
                    if(archivo.getName().endsWith("xls")){
                        wb = new HSSFWorkbook();
                    }else{
                        wb= new XSSFWorkbook();
                    }
                    Sheet hoja= wb.createSheet("Matriz_Carbon");
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
                                        if(encabezadoTabla.get(j).equals("Costo_Total")){
                                            costoTotalApuntador=j;
                                        }
                                }else{
                                    String data="";
                                    switch(encabezadoTabla.get(j)){
                                        case "C??digo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getCodigo();
                                            break;
                                        }
                                        case "Centro_Operacion":{
                                            data=""+  listado.get(i).getMvtoCarbon().getCentroOperacion().getDescripcion();
                                            break;
                                        }
                                        case "Subcentro_Costo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                                            break;
                                        }
                                        case "Centro_Costo_Auxiliar_Origen":{
                                            data=""+  listado.get(i).getMvtoCarbon().getCentroCostoAuxiliar().getDescripcion();
                                            break;
                                        }
                                        case "Centro_Costo_Auxiliar_Destino":{
                                            data=""+  listado.get(i).getMvtoCarbon().getCentroCostoAuxiliarDestino().getDescripcion();
                                            break;
                                        }
                                        case "Centro_Costo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getCentroCosto().getDescripci??n();
                                            break;
                                        }
                                        case "Centro_Costo_Mayor":{
                                            data=""+  listado.get(i).getMvtoCarbon().getCentroCostoMayor().getDescripcion();
                                            break;
                                        }
                                        case "Labor_Realizada":{
                                            data=""+  listado.get(i).getMvtoCarbon().getLaborRealizada().getDescripcion();
                                            break;
                                        }
                                        case "Art??culo_C??digo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getArticulo().getCodigo();
                                            break;
                                        }
                                        case "Art??culo_Nombre":{
                                            data=""+  listado.get(i).getMvtoCarbon().getArticulo().getDescripcion();
                                            break;
                                        }
                                        case "Art??culo_Tipo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getArticulo().getTipoArticulo().getDescripcion();
                                            break;
                                        }
                                        case "Art??culo_C??digo_ERP":{
                                            data=""+  listado.get(i).getMvtoCarbon().getArticulo().getTipoArticulo().getCodigoERP();
                                            break;
                                        }
                                        case "Art??culo_Unidad_Negocio":{
                                            validarUnidadNegocio=true;
                                            data=""+  listado.get(i).getMvtoCarbon().getArticulo().getTipoArticulo().getUnidadNegocio();
                                            break;
                                        }
                                        case "Cliente_C??digo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getCliente().getCodigo();
                                            break;
                                        }
                                        case "Cliente_Nombre":{
                                            data=""+  listado.get(i).getMvtoCarbon().getCliente().getDescripcion();
                                            break;
                                        }
                                        case "Transportadora_C??digo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getTransportadora().getCodigo();
                                            break;
                                        }
                                        case "Transportadora_Nit":{
                                            data=""+  listado.get(i).getMvtoCarbon().getTransportadora().getNit();
                                            break;
                                        }
                                        case "Transportadora_Nombre":{
                                            data=""+  listado.get(i).getMvtoCarbon().getTransportadora().getDescripcion();
                                            break;
                                        }
                                        case "Mes":{
                                            data=""+  getMes(listado.get(i).getMvtoCarbon().getMes());
                                            break;
                                        }
                                        case "D??a":{
                                            data=""+  listado.get(i).getMvtoCarbon().getDia();
                                            break;
                                        }                                
                                        case "Fecha_Registro":{
                                            data=""+  listado.get(i).getMvtoCarbon().getFechaRegistro();
                                            break;
                                        }
                                        case "Lavado_Veh??culo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getLavadoVehiculo();
                                            break;
                                         }
                                         case "Lavado_Veh??culo_Observaci??n":{
                                            data=""+  listado.get(i).getMvtoCarbon().getLavadoVehiculo_Observacion();
                                            break;
                                         }
                                         
                                         case "Motivo_No_LavadoVeh??culo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getMotivoNoLavado().getDescripcion();
                                            break;
                                         }
                                         case "Equipo_Quien_Realiza_LavadoVeh??culo_C??digo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getEquipoLavadoVehiculo().getCodigo();
                                            break;
                                         }
                                         case "Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getEquipoLavadoVehiculo().getTipoEquipo().getDescripcion();
                                            break;
                                         }
                                         case "Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n":{
                                            data=""+  listado.get(i).getMvtoCarbon().getEquipoLavadoVehiculo().getDescripcion()+" "+listado.get(i).getMvtoCarbon().getEquipoLavadoVehiculo().getModelo();
                                            break;
                                         }
                                         case "Costo_Lavado_Veh??culo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getCostoLavadoVehiculo();
                                            break;
                                         }
                                         case "Valor_Recaudo_Empresa_LavadoVeh??culos":{
                                            data=""+  listado.get(i).getMvtoCarbon().getValorRecaudoEmpresa_lavadoVehiculo();
                                            break;
                                         }
                                         case "Valor_Recaudo_Equipo_LavadoVeh??culos":{
                                            data=""+  listado.get(i).getMvtoCarbon().getValorRecaudoEquipo_lavadoVehiculo();
                                            break;
                                         }
                                         
                                        case "N??mero_Orden":{
                                            data=""+  listado.get(i).getMvtoCarbon().getNumero_orden();
                                            break;
                                        }
                                        case "Deposito":{
                                            data=""+  listado.get(i).getMvtoCarbon().getDeposito();
                                            break;
                                        }
                                        case "Consecutivo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getConsecutivo();
                                            break;
                                        }
                                        case "Placa":{
                                            data=""+  listado.get(i).getMvtoCarbon().getPlaca();
                                            break;
                                        }
                                        case "PesoVacio":{
                                            data=""+  listado.get(i).getMvtoCarbon().getPesoVacio();
                                            break;
                                        }
                                        case "Peso_Lleno":{
                                            data=""+  listado.get(i).getMvtoCarbon().getPesoLleno();
                                            break;
                                        }
                                        case "Peso_Neto":{
                                            data=""+  listado.get(i).getMvtoCarbon().getPesoNeto();
                                            break;
                                        }
                                        case "Fecha_Entrada_Veh??culo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getFechaEntradaVehiculo();
                                            break;
                                        }
                                        case "Fecha_Salida_Veh??culo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getFecha_SalidaVehiculo();
                                            break;
                                        }
                                        case "Fecha_Inicio_Descargue":{
                                            data=""+  listado.get(i).getMvtoCarbon().getFechaInicioDescargue();
                                            break;
                                        }
                                        case "Fecha_Fin_Descargue":{
                                            data=""+  listado.get(i).getMvtoCarbon().getFechaFinDescargue();
                                            break;
                                        }
                                        case "Cantidad_Minutos":{
                                            data=""+  listado.get(i).getMvtoCarbon().getCantidadHorasDescargue();
                                            break;
                                        }
                                        case "Usuario_Quien_Registra_Veh??culo_C??digo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getUsuarioRegistroMovil().getCodigo();
                                            break;
                                        }
                                        case "Usuario_Quien_Registra_Veh??culo_Nombre":{
                                            data=""+  listado.get(i).getMvtoCarbon().getUsuarioRegistroMovil().getNombres()+" "+listado.get(i).getMvtoCarbon().getUsuarioRegistroMovil().getApellidos();
                                            break;
                                        }
                                        case "Usuario_Quien_Registra_Veh??culo_Correo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getUsuarioRegistroMovil().getCorreo();
                                            break;
                                        }
                                        case "Observaci??n":{
                                            data=""+  listado.get(i).getMvtoCarbon().getObservacion();
                                            break;
                                        }
                                        case "Estado":{
                                            data=""+  listado.get(i).getMvtoCarbon().getEstadoMvtoCarbon().getDescripcion();
                                            break;
                                        }
                                        case "Conexi??n_Peso_CCARGA":{
                                            data=""+  listado.get(i).getMvtoCarbon().getConexionPesoCcarga();
                                            break;
                                        }
                                        case "Registro_Manual":{
                                            data=""+  listado.get(i).getMvtoCarbon().getRegistroManual();
                                            break;
                                        }
                                        case "Usuario_Quien_RegistraManual_C??digo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getUsuarioRegistraManual().getCodigo();
                                            break;
                                        }
                                        case "Usuario_Quien_RegistraManual_Nombre":{
                                            data=""+  listado.get(i).getMvtoCarbon().getUsuarioRegistraManual().getNombres()+" "+listado.get(i).getMvtoCarbon().getUsuarioRegistraManual().getApellidos();
                                            break;
                                        }
                                        case "Usuario_Quien_RegistraManual_Correo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getUsuarioRegistraManual().getCorreo();
                                            break;
                                        }
                                        case "Usuario_Quien_Cierra_Veh??culo_C??digo":{
                                            data=""+  listado.get(i).getMvtoCarbon().getUsuarioCierraRegistro().getCodigo();
                                            break;
                                        }
                                        case "Usuario_Quien_Cierra_Veh??culo_Nombre":{
                                            data=""+  listado.get(i).getMvtoCarbon().getUsuarioCierraRegistro().getNombres()+" "+listado.get(i).getMvtoCarbon().getUsuarioCierraRegistro().getApellidos();
                                            break;
                                        }
                                        case "MvtoEquipo_C??digo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCodigo();
                                            break;
                                        }
                                        case "MvtoEquipo_MES":{
                                            data=""+  getMes(listado.get(i).getMvtoEquipo().getMes());
                                            break;
                                        }
                                        case "MvtoEquipo_Fecha_Registro":{
                                            data=""+  listado.get(i).getMvtoEquipo().getFechaRegistro();
                                            break;
                                        }
                                        case "MvtoEquipo_ProveedorEquipo_NIT":{
                                            data=""+  listado.get(i).getMvtoEquipo().getProveedorEquipo().getNit();
                                            break;
                                        }
                                        case "MvtoEquipo_ProveedorEquipo_Nombre":{
                                            data=""+  listado.get(i).getMvtoEquipo().getProveedorEquipo().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_N??mero_Orden":{
                                            data=""+  listado.get(i).getMvtoEquipo().getNumeroOrden();
                                            break;
                                        }
                                        case "MvtoEquipo_Centro_Operaci??n":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCentroOperacion().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Subcentro_Costo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Centro_Costo_Auxiliar_Origen":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCentroCostoAuxiliar().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Centro_Costo_Auxiliar_Destino":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCentroCostoAuxiliarDestino().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Centro_Costo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCentroCosto().getDescripci??n();
                                            break;
                                        }
                                        case "MvtoEquipo_Centro_Costo_Mayor":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCentroCostoMayor().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Labor_Realizada":{
                                            data=""+  listado.get(i).getMvtoEquipo().getLaborRealizada().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Cliente_C??digo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCliente().getCodigo();
                                            break;
                                        }
                                        case "MvtoEquipo_Cliente_Nombre":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCliente().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Art??culo_C??digo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getArticulo().getCodigo();
                                            break;
                                        }
                                        case "MvtoEquipo_Art??culo_Nombre":{
                                            data=""+  listado.get(i).getMvtoEquipo().getArticulo().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Motonave_C??digo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getMotonave().getCodigo();
                                            break;
                                        }
                                        case "MvtoEquipo_Motonave_Descripci??n":{
                                            data=""+  listado.get(i).getMvtoEquipo().getMotonave().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_FechaInicio_Actividad":{
                                            data=""+  listado.get(i).getMvtoEquipo().getFechaHoraInicio();
                                            break;
                                        }
                                        case "MvtoEquipo_FechaFinalizaci??n_Actividad":{
                                            data=""+  listado.get(i).getMvtoEquipo().getFechaHoraFin();
                                            break;
                                        }
                                        case "MvtoEquipo_Duraci??n_Actividad":{
                                            data=""+  listado.get(i).getMvtoEquipo().getTotalMinutos();
                                            break;
                                        }
                                        case "MvtoEquipo_Valor_Hora":{
                                            data=""+  listado.get(i).getMvtoEquipo().getValorHora();
                                            break;
                                        }
                                        case "MvtoEquipo_Costo_Total":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCostoTotal();
                                            break;
                                        }
                                        case "MvtoEquipo_Recobro":{
                                            data=""+  listado.get(i).getMvtoEquipo().getRecobro().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_UsuarioQuienRegistra_C??digo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getUsuarioQuieRegistra().getCodigo();
                                            break;
                                        }
                                        case "MvtoEquipo_UsuarioQuienRegistra_Nombre":{
                                            data=""+  listado.get(i).getMvtoEquipo().getUsuarioQuieRegistra().getNombres();
                                            break;
                                        }
                                        case "MvtoEquipo_UsuarioQuienAutoriza_C??digo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getUsuarioAutorizaRecobro().getCodigo();
                                            break;
                                        }
                                        case "MvtoEquipo_UsuarioQuienAutoriza_Nombre":{
                                            data=""+  listado.get(i).getMvtoEquipo().getUsuarioAutorizaRecobro().getNombres();
                                            break;
                                        }
                                        case "MvtoEquipo_Autorizaci??n_Recobro":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAutorizacionRecobro().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Observaci??n_Autorizaci??n_Recobro":{
                                            data=""+  listado.get(i).getMvtoEquipo().getObservacionAutorizacion();
                                            break;
                                        }
                                        case "MvtoEquipo_Inactividad":{
                                            data=""+  listado.get(i).getMvtoEquipo().getInactividad();
                                            break;
                                        }
                                        case "MvtoEquipo_Causal_Inactividad":{
                                            data=""+  listado.get(i).getMvtoEquipo().getCausaInactividad().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_UsuarioQuienInactiva_C??digo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getUsuarioInactividad().getCodigo();
                                            break;
                                        }
                                        case "MvtoEquipo_UsuarioQuienInactiva_Nombre":{
                                            data=""+  listado.get(i).getMvtoEquipo().getUsuarioInactividad().getNombres();
                                            break;
                                        }
                                        case "MvtoEquipo_UsuarioCierraMvto_codigo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getUsuarioQuienCierra().getCodigo();
                                            break;
                                        }
                                        case "MvtoEquipo_UsuarioCierraMvto_nombre":{
                                            data=""+  listado.get(i).getMvtoEquipo().getUsuarioQuienCierra().getNombres();
                                            break;
                                        }
                                        case "MvtoEquipo_Motivo_Parada":{
                                            data=""+  listado.get(i).getMvtoEquipo().getMotivoParada().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_MvtoEquipo_Observaci??n":{
                                            data=""+  listado.get(i).getMvtoEquipo().getObservacionMvtoEquipo();
                                            break;
                                        }
                                        case "MvtoEquipo_Estado":{
                                            data=""+  listado.get(i).getMvtoEquipo().getEstado();
                                            break;
                                        }
                                        case "MvtoEquipo_Desde_Carb??n":{
                                            data=""+  listado.get(i).getMvtoEquipo().getDesdeCarbon();
                                            break;
                                        }
                                        case "MvtoEquipo_Equipo_C??digo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getEquipo().getCodigo();
                                            break;
                                        }
                                        case "MvtoEquipo_Equipo_Tipo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Equipo_Marca":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getEquipo().getMarca();
                                            break;
                                        }
                                        case "MvtoEquipo_Equipo_Modelo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getEquipo().getModelo();
                                            break;
                                        }
                                        case "MvtoEquipo_Equipo_Descripci??n":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getEquipo().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Equipo_Pertenencia":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getPertenencia().getDescripcion();
                                            break;
                                        }
                                        case "MvtoEquipo_Asignaci??n_C??digo":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getCodigo();
                                            break;
                                        }
                                        case "MvtoEquipo_Asignaci??n_FechaRegistro":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getFechaRegistro();
                                            break;
                                        }
                                        case "MvtoEquipo_Asignaci??n_FechaInicioActividad":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getFechaHoraInicio();
                                            break;
                                        }
                                        case "MvtoEquipo_Asignaci??n_FechaFinalizaci??nActividad":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getFechaHoraFin();
                                            break;
                                        }
                                        case "MvtoEquipo_Asignaci??n_CantidadMinutosProgramados":{
                                            data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getCantidadMinutosProgramados();
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
                                                        if(costoTotalApuntador == j){
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

    private void selectDescargue_pesoVacioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDescargue_pesoVacioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectDescargue_pesoVacioActionPerformed

    private void selectDescargue_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDescargue_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectDescargue_codigoActionPerformed

    private void selectDescargue_centroCostoMayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDescargue_centroCostoMayorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectDescargue_centroCostoMayorActionPerformed

    private void selectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAllActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAllActionPerformed

    private void selectDescargue_articuloCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDescargue_articuloCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectDescargue_articuloCodigoActionPerformed

    private void selectDescargue_laborRealizadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDescargue_laborRealizadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectDescargue_laborRealizadaActionPerformed

    private void selectDescargue_usuarioRegistroManualCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDescargue_usuarioRegistroManualCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectDescargue_usuarioRegistroManualCorreoActionPerformed

    private void selectMvtoEquipo_cantidadMinutosDescargueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_cantidadMinutosDescargueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_cantidadMinutosDescargueActionPerformed

    private void selectAsignacion_equipoTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAsignacion_equipoTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAsignacion_equipoTipoActionPerformed

    private void selectMvtoEquipo_motonaveDescripci??nActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_motonaveDescripci??nActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_motonaveDescripci??nActionPerformed

    private void selectMvtoEquipo_subcentroCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_subcentroCostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_subcentroCostoActionPerformed

    private void selectAsignacion_fechaInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAsignacion_fechaInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAsignacion_fechaInicioActionPerformed

    private void selectAsignacion_cantidadMinutos_ProgramadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAsignacion_cantidadMinutos_ProgramadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAsignacion_cantidadMinutos_ProgramadosActionPerformed

    private void selectMvtoEquipo_proveedorEquipoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_proveedorEquipoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_proveedorEquipoNombreActionPerformed

    private void selectMvtoEquipo_laborRealizadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_laborRealizadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_laborRealizadaActionPerformed

    private void selectMvtoEquipo_fechaInicioDescargueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_fechaInicioDescargueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_fechaInicioDescargueActionPerformed

    private void selectMvtoEquipo_fechaFinDescargueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_fechaFinDescargueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_fechaFinDescargueActionPerformed

    private void selectMvtoEquipo_UsuarioInactividad_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_UsuarioInactividad_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_UsuarioInactividad_codigoActionPerformed

    private void selectAsignacion_equipoDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAsignacion_equipoDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAsignacion_equipoDescripcionActionPerformed

    private void selectMvtoEquipo_articuloCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_articuloCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_articuloCodigoActionPerformed

    private void selectMvtoEquipo_articuloDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_articuloDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_articuloDescripcionActionPerformed

    private void selectMvtoEquipo_motonaveCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_motonaveCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_motonaveCodigoActionPerformed

    private void reporte_laraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporte_laraActionPerformed
        InternalFrameSelectorCampos.show(false);
        selectorCampoPorDefectoLara();
        if(listado != null){
            paginationPanel.removeAll();
            validarSeleccionCamposLara();
            tabla.setModel(crearModeloDeTabla());
            //listado=new ControlDB_MvtoEquipo(tipoConexion).buscar_mvtoEquiposActivosGeneral(data1, data2);
            proveedorDeDatos = crearProveedorDeDatos(listado); 
            paginadorDeTabla = new PaginadorDeTabla(tabla, proveedorDeDatos, new int[]{5, 10, 20, 50, 75, 100}, 10);
            paginadorDeTabla.crearListadoDeFilasPermitidas(this.paginationPanel);
            pageJComboBox = paginadorDeTabla.getComboBoxPage();
            events();
            pageJComboBox.setSelectedItem(Integer.parseInt("50"));
            resizeColumnWidth(tabla);
        }
    }//GEN-LAST:event_reporte_laraActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        if(user.getCorreo().equals("")){
            JOptionPane.showMessageDialog(null, "El usuario no tiene un correo configurado para el envio de correo, favor actualizar los datos");
        }else{
            if(listado != null){
                File archivo;
                //archivo= new File( "reportes/"+user.getCodigo()+"_"+"reporteCarbon.xlsx");
                archivo= new File(System.getProperty("java.io.tmpdir")+user.getCodigo()+"_"+"reporteCarbon.xlsx");
                Workbook wb;
                String respuesta="No se realiz?? con exito la exportacion";
                listado.size();

                //int numFila=tabla.getRowCount(), numColumna=tabla.getColumnCount();
                int numFila=listado.size();
                int numColumna=encabezadoTabla.size();
                if(archivo.getName().endsWith("xls")){
                    wb = new HSSFWorkbook();
                }else{
                    wb= new XSSFWorkbook();
                }
                Sheet hoja= wb.createSheet("Matriz_Carbon");
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
                                    if(encabezadoTabla.get(j).equals("Costo_Total")){
                                        costoTotalApuntador=j;
                                    }
                            }else{
                                String data="";
                                switch(encabezadoTabla.get(j)){
                                    case "C??digo":{
                                        data=""+  listado.get(i).getMvtoCarbon().getCodigo();
                                        break;
                                    }
                                    case "Centro_Operacion":{
                                        data=""+  listado.get(i).getMvtoCarbon().getCentroOperacion().getDescripcion();
                                        break;
                                    }
                                    case "Subcentro_Costo":{
                                        data=""+  listado.get(i).getMvtoCarbon().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                                        break;
                                    }
                                    case "Centro_Costo_Auxiliar_Origen":{
                                        data=""+  listado.get(i).getMvtoCarbon().getCentroCostoAuxiliar().getDescripcion();
                                        break;
                                    }
                                    case "Centro_Costo_Auxiliar_Destino":{
                                        data=""+  listado.get(i).getMvtoCarbon().getCentroCostoAuxiliarDestino().getDescripcion();
                                        break;
                                    }
                                    case "Centro_Costo":{
                                        data=""+  listado.get(i).getMvtoCarbon().getCentroCosto().getDescripci??n();
                                        break;
                                    }
                                    case "Centro_Costo_Mayor":{
                                        data=""+  listado.get(i).getMvtoCarbon().getCentroCostoMayor().getDescripcion();
                                        break;
                                    }
                                    case "Labor_Realizada":{
                                        data=""+  listado.get(i).getMvtoCarbon().getLaborRealizada().getDescripcion();
                                        break;
                                    }
                                    case "Art??culo_C??digo":{
                                        data=""+  listado.get(i).getMvtoCarbon().getArticulo().getCodigo();
                                        break;
                                    }
                                    case "Art??culo_Nombre":{
                                        data=""+  listado.get(i).getMvtoCarbon().getArticulo().getDescripcion();
                                        break;
                                    }
                                    case "Art??culo_Tipo":{
                                        data=""+  listado.get(i).getMvtoCarbon().getArticulo().getTipoArticulo().getDescripcion();
                                        break;
                                    }
                                    case "Art??culo_C??digo_ERP":{
                                        data=""+  listado.get(i).getMvtoCarbon().getArticulo().getTipoArticulo().getCodigoERP();
                                        break;
                                    }
                                    case "Art??culo_Unidad_Negocio":{
                                        validarUnidadNegocio=true;
                                        data=""+  listado.get(i).getMvtoCarbon().getArticulo().getTipoArticulo().getUnidadNegocio();
                                        break;
                                    }
                                    case "Cliente_C??digo":{
                                        data=""+  listado.get(i).getMvtoCarbon().getCliente().getCodigo();
                                        break;
                                    }
                                    case "Cliente_Nombre":{
                                        data=""+  listado.get(i).getMvtoCarbon().getCliente().getDescripcion();
                                        break;
                                    }
                                    case "Transportadora_C??digo":{
                                        data=""+  listado.get(i).getMvtoCarbon().getTransportadora().getCodigo();
                                        break;
                                    }
                                    case "Transportadora_Nit":{
                                        data=""+  listado.get(i).getMvtoCarbon().getTransportadora().getNit();
                                        break;
                                    }
                                    case "Transportadora_Nombre":{
                                        data=""+  listado.get(i).getMvtoCarbon().getTransportadora().getDescripcion();
                                        break;
                                    }
                                    case "Mes":{
                                        data=""+  getMes(listado.get(i).getMvtoCarbon().getMes());
                                        break;
                                    }
                                    case "D??a":{
                                        data=""+  listado.get(i).getMvtoCarbon().getDia();
                                        break;
                                    }                                
                                    case "Fecha_Registro":{
                                        data=""+  listado.get(i).getMvtoCarbon().getFechaRegistro();
                                        break;
                                    }
                                    case "Lavado_Veh??culo":{
                                        data=""+  listado.get(i).getMvtoCarbon().getLavadoVehiculo();
                                        break;
                                     }
                                     case "Lavado_Veh??culo_Observaci??n":{
                                        data=""+  listado.get(i).getMvtoCarbon().getLavadoVehiculo_Observacion();
                                        break;
                                     }

                                     case "Motivo_No_LavadoVeh??culo":{
                                        data=""+  listado.get(i).getMvtoCarbon().getMotivoNoLavado().getDescripcion();
                                        break;
                                     }
                                     case "Equipo_Quien_Realiza_LavadoVeh??culo_C??digo":{
                                        data=""+  listado.get(i).getMvtoCarbon().getEquipoLavadoVehiculo().getCodigo();
                                        break;
                                     }
                                     case "Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo":{
                                        data=""+  listado.get(i).getMvtoCarbon().getEquipoLavadoVehiculo().getTipoEquipo().getDescripcion();
                                        break;
                                     }
                                     case "Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n":{
                                        data=""+  listado.get(i).getMvtoCarbon().getEquipoLavadoVehiculo().getDescripcion()+" "+listado.get(i).getMvtoCarbon().getEquipoLavadoVehiculo().getModelo();
                                        break;
                                     }
                                     case "Costo_Lavado_Veh??culo":{
                                        data=""+  listado.get(i).getMvtoCarbon().getCostoLavadoVehiculo();
                                        break;
                                     }
                                     case "Valor_Recaudo_Empresa_LavadoVeh??culos":{
                                        data=""+  listado.get(i).getMvtoCarbon().getValorRecaudoEmpresa_lavadoVehiculo();
                                        break;
                                     }
                                     case "Valor_Recaudo_Equipo_LavadoVeh??culos":{
                                        data=""+  listado.get(i).getMvtoCarbon().getValorRecaudoEquipo_lavadoVehiculo();
                                        break;
                                     }

                                    case "N??mero_Orden":{
                                        data=""+  listado.get(i).getMvtoCarbon().getNumero_orden();
                                        break;
                                    }
                                    case "Deposito":{
                                        data=""+  listado.get(i).getMvtoCarbon().getDeposito();
                                        break;
                                    }
                                    case "Consecutivo":{
                                        data=""+  listado.get(i).getMvtoCarbon().getConsecutivo();
                                        break;
                                    }
                                    case "Placa":{
                                        data=""+  listado.get(i).getMvtoCarbon().getPlaca();
                                        break;
                                    }
                                    case "PesoVacio":{
                                        data=""+  listado.get(i).getMvtoCarbon().getPesoVacio();
                                        break;
                                    }
                                    case "Peso_Lleno":{
                                        data=""+  listado.get(i).getMvtoCarbon().getPesoLleno();
                                        break;
                                    }
                                    case "Peso_Neto":{
                                        data=""+  listado.get(i).getMvtoCarbon().getPesoNeto();
                                        break;
                                    }
                                    case "Fecha_Entrada_Veh??culo":{
                                        data=""+  listado.get(i).getMvtoCarbon().getFechaEntradaVehiculo();
                                        break;
                                    }
                                    case "Fecha_Salida_Veh??culo":{
                                        data=""+  listado.get(i).getMvtoCarbon().getFecha_SalidaVehiculo();
                                        break;
                                    }
                                    case "Fecha_Inicio_Descargue":{
                                        data=""+  listado.get(i).getMvtoCarbon().getFechaInicioDescargue();
                                        break;
                                    }
                                    case "Fecha_Fin_Descargue":{
                                        data=""+  listado.get(i).getMvtoCarbon().getFechaFinDescargue();
                                        break;
                                    }
                                    case "Cantidad_Minutos":{
                                        data=""+  listado.get(i).getMvtoCarbon().getCantidadHorasDescargue();
                                        break;
                                    }
                                    case "Usuario_Quien_Registra_Veh??culo_C??digo":{
                                        data=""+  listado.get(i).getMvtoCarbon().getUsuarioRegistroMovil().getCodigo();
                                        break;
                                    }
                                    case "Usuario_Quien_Registra_Veh??culo_Nombre":{
                                        data=""+  listado.get(i).getMvtoCarbon().getUsuarioRegistroMovil().getNombres()+" "+listado.get(i).getMvtoCarbon().getUsuarioRegistroMovil().getApellidos();
                                        break;
                                    }
                                    case "Usuario_Quien_Registra_Veh??culo_Correo":{
                                        data=""+  listado.get(i).getMvtoCarbon().getUsuarioRegistroMovil().getCorreo();
                                        break;
                                    }
                                    case "Observaci??n":{
                                        data=""+  listado.get(i).getMvtoCarbon().getObservacion();
                                        break;
                                    }
                                    case "Estado":{
                                        data=""+  listado.get(i).getMvtoCarbon().getEstadoMvtoCarbon().getDescripcion();
                                        break;
                                    }
                                    case "Conexi??n_Peso_CCARGA":{
                                        data=""+  listado.get(i).getMvtoCarbon().getConexionPesoCcarga();
                                        break;
                                    }
                                    case "Registro_Manual":{
                                        data=""+  listado.get(i).getMvtoCarbon().getRegistroManual();
                                        break;
                                    }
                                    case "Usuario_Quien_RegistraManual_C??digo":{
                                        data=""+  listado.get(i).getMvtoCarbon().getUsuarioRegistraManual().getCodigo();
                                        break;
                                    }
                                    case "Usuario_Quien_RegistraManual_Nombre":{
                                        data=""+  listado.get(i).getMvtoCarbon().getUsuarioRegistraManual().getNombres()+" "+listado.get(i).getMvtoCarbon().getUsuarioRegistraManual().getApellidos();
                                        break;
                                    }
                                    case "Usuario_Quien_RegistraManual_Correo":{
                                        data=""+  listado.get(i).getMvtoCarbon().getUsuarioRegistraManual().getCorreo();
                                        break;
                                    }
                                    case "Usuario_Quien_Cierra_Veh??culo_C??digo":{
                                        data=""+  listado.get(i).getMvtoCarbon().getUsuarioCierraRegistro().getCodigo();
                                        break;
                                    }
                                    case "Usuario_Quien_Cierra_Veh??culo_Nombre":{
                                        data=""+  listado.get(i).getMvtoCarbon().getUsuarioCierraRegistro().getNombres()+" "+listado.get(i).getMvtoCarbon().getUsuarioCierraRegistro().getApellidos();
                                        break;
                                    }
                                    case "MvtoEquipo_C??digo":{
                                        data=""+  listado.get(i).getMvtoEquipo().getCodigo();
                                        break;
                                    }
                                    case "MvtoEquipo_MES":{
                                        data=""+  getMes(listado.get(i).getMvtoEquipo().getMes());
                                        break;
                                    }
                                    case "MvtoEquipo_Fecha_Registro":{
                                        data=""+  listado.get(i).getMvtoEquipo().getFechaRegistro();
                                        break;
                                    }
                                    case "MvtoEquipo_ProveedorEquipo_NIT":{
                                        data=""+  listado.get(i).getMvtoEquipo().getProveedorEquipo().getNit();
                                        break;
                                    }
                                    case "MvtoEquipo_ProveedorEquipo_Nombre":{
                                        data=""+  listado.get(i).getMvtoEquipo().getProveedorEquipo().getDescripcion();
                                        break;
                                    }
                                    case "MvtoEquipo_N??mero_Orden":{
                                        data=""+  listado.get(i).getMvtoEquipo().getNumeroOrden();
                                        break;
                                    }
                                    case "MvtoEquipo_Centro_Operaci??n":{
                                        data=""+  listado.get(i).getMvtoEquipo().getCentroOperacion().getDescripcion();
                                        break;
                                    }
                                    case "MvtoEquipo_Subcentro_Costo":{
                                        data=""+  listado.get(i).getMvtoEquipo().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                                        break;
                                    }
                                    case "MvtoEquipo_Centro_Costo_Auxiliar_Origen":{
                                        data=""+  listado.get(i).getMvtoEquipo().getCentroCostoAuxiliar().getDescripcion();
                                        break;
                                    }
                                    case "MvtoEquipo_Centro_Costo_Auxiliar_Destino":{
                                        data=""+  listado.get(i).getMvtoEquipo().getCentroCostoAuxiliarDestino().getDescripcion();
                                        break;
                                    }
                                    case "MvtoEquipo_Centro_Costo":{
                                        data=""+  listado.get(i).getMvtoEquipo().getCentroCosto().getDescripci??n();
                                        break;
                                    }
                                    case "MvtoEquipo_Centro_Costo_Mayor":{
                                        data=""+  listado.get(i).getMvtoEquipo().getCentroCostoMayor().getDescripcion();
                                        break;
                                    }
                                    case "MvtoEquipo_Labor_Realizada":{
                                        data=""+  listado.get(i).getMvtoEquipo().getLaborRealizada().getDescripcion();
                                        break;
                                    }
                                    case "MvtoEquipo_Cliente_C??digo":{
                                        data=""+  listado.get(i).getMvtoEquipo().getCliente().getCodigo();
                                        break;
                                    }
                                    case "MvtoEquipo_Cliente_Nombre":{
                                        data=""+  listado.get(i).getMvtoEquipo().getCliente().getDescripcion();
                                        break;
                                    }
                                    case "MvtoEquipo_Art??culo_C??digo":{
                                        data=""+  listado.get(i).getMvtoEquipo().getArticulo().getCodigo();
                                        break;
                                    }
                                    case "MvtoEquipo_Art??culo_Nombre":{
                                        data=""+  listado.get(i).getMvtoEquipo().getArticulo().getDescripcion();
                                        break;
                                    }
                                    case "MvtoEquipo_Motonave_C??digo":{
                                        data=""+  listado.get(i).getMvtoEquipo().getMotonave().getCodigo();
                                        break;
                                    }
                                    case "MvtoEquipo_Motonave_Descripci??n":{
                                        data=""+  listado.get(i).getMvtoEquipo().getMotonave().getDescripcion();
                                        break;
                                    }
                                    case "MvtoEquipo_FechaInicio_Actividad":{
                                        data=""+  listado.get(i).getMvtoEquipo().getFechaHoraInicio();
                                        break;
                                    }
                                    case "MvtoEquipo_FechaFinalizaci??n_Actividad":{
                                        data=""+  listado.get(i).getMvtoEquipo().getFechaHoraFin();
                                        break;
                                    }
                                    case "MvtoEquipo_Duraci??n_Actividad":{
                                        data=""+  listado.get(i).getMvtoEquipo().getTotalMinutos();
                                        break;
                                    }
                                    case "MvtoEquipo_Valor_Hora":{
                                        data=""+  listado.get(i).getMvtoEquipo().getValorHora();
                                        break;
                                    }
                                    case "MvtoEquipo_Costo_Total":{
                                        data=""+  listado.get(i).getMvtoEquipo().getCostoTotal();
                                        break;
                                    }
                                    case "MvtoEquipo_Recobro":{
                                        data=""+  listado.get(i).getMvtoEquipo().getRecobro().getDescripcion();
                                        break;
                                    }
                                    case "MvtoEquipo_UsuarioQuienRegistra_C??digo":{
                                        data=""+  listado.get(i).getMvtoEquipo().getUsuarioQuieRegistra().getCodigo();
                                        break;
                                    }
                                    case "MvtoEquipo_UsuarioQuienRegistra_Nombre":{
                                        data=""+  listado.get(i).getMvtoEquipo().getUsuarioQuieRegistra().getNombres();
                                        break;
                                    }
                                    case "MvtoEquipo_UsuarioQuienAutoriza_C??digo":{
                                        data=""+  listado.get(i).getMvtoEquipo().getUsuarioAutorizaRecobro().getCodigo();
                                        break;
                                    }
                                    case "MvtoEquipo_UsuarioQuienAutoriza_Nombre":{
                                        data=""+  listado.get(i).getMvtoEquipo().getUsuarioAutorizaRecobro().getNombres();
                                        break;
                                    }
                                    case "MvtoEquipo_Autorizaci??n_Recobro":{
                                        data=""+  listado.get(i).getMvtoEquipo().getAutorizacionRecobro().getDescripcion();
                                        break;
                                    }
                                    case "MvtoEquipo_Observaci??n_Autorizaci??n_Recobro":{
                                        data=""+  listado.get(i).getMvtoEquipo().getObservacionAutorizacion();
                                        break;
                                    }
                                    case "MvtoEquipo_Inactividad":{
                                        data=""+  listado.get(i).getMvtoEquipo().getInactividad();
                                        break;
                                    }
                                    case "MvtoEquipo_Causal_Inactividad":{
                                        data=""+  listado.get(i).getMvtoEquipo().getCausaInactividad().getDescripcion();
                                        break;
                                    }
                                    case "MvtoEquipo_UsuarioQuienInactiva_C??digo":{
                                        data=""+  listado.get(i).getMvtoEquipo().getUsuarioInactividad().getCodigo();
                                        break;
                                    }
                                    case "MvtoEquipo_UsuarioQuienInactiva_Nombre":{
                                        data=""+  listado.get(i).getMvtoEquipo().getUsuarioInactividad().getNombres();
                                        break;
                                    }
                                    case "MvtoEquipo_UsuarioCierraMvto_codigo":{
                                        data=""+  listado.get(i).getMvtoEquipo().getUsuarioQuienCierra().getCodigo();
                                        break;
                                    }
                                    case "MvtoEquipo_UsuarioCierraMvto_nombre":{
                                        data=""+  listado.get(i).getMvtoEquipo().getUsuarioQuienCierra().getNombres();
                                        break;
                                    }
                                    case "MvtoEquipo_Motivo_Parada":{
                                        data=""+  listado.get(i).getMvtoEquipo().getMotivoParada().getDescripcion();
                                        break;
                                    }
                                    case "MvtoEquipo_MvtoEquipo_Observaci??n":{
                                        data=""+  listado.get(i).getMvtoEquipo().getObservacionMvtoEquipo();
                                        break;
                                    }
                                    case "MvtoEquipo_Estado":{
                                        data=""+  listado.get(i).getMvtoEquipo().getEstado();
                                        break;
                                    }
                                    case "MvtoEquipo_Desde_Carb??n":{
                                        data=""+  listado.get(i).getMvtoEquipo().getDesdeCarbon();
                                        break;
                                    }
                                    case "MvtoEquipo_Equipo_C??digo":{
                                        data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getEquipo().getCodigo();
                                        break;
                                    }
                                    case "MvtoEquipo_Equipo_Tipo":{
                                        data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion();
                                        break;
                                    }
                                    case "MvtoEquipo_Equipo_Marca":{
                                        data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getEquipo().getMarca();
                                        break;
                                    }
                                    case "MvtoEquipo_Equipo_Modelo":{
                                        data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getEquipo().getModelo();
                                        break;
                                    }
                                    case "MvtoEquipo_Equipo_Descripci??n":{
                                        data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getEquipo().getDescripcion();
                                        break;
                                    }
                                    case "MvtoEquipo_Equipo_Pertenencia":{
                                        data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getPertenencia().getDescripcion();
                                        break;
                                    }
                                    case "MvtoEquipo_Asignaci??n_C??digo":{
                                        data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getCodigo();
                                        break;
                                    }
                                    case "MvtoEquipo_Asignaci??n_FechaRegistro":{
                                        data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getFechaRegistro();
                                        break;
                                    }
                                    case "MvtoEquipo_Asignaci??n_FechaInicioActividad":{
                                        data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getFechaHoraInicio();
                                        break;
                                    }
                                    case "MvtoEquipo_Asignaci??n_FechaFinalizaci??nActividad":{
                                        data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getFechaHoraFin();
                                        break;
                                    }
                                    case "MvtoEquipo_Asignaci??n_CantidadMinutosProgramados":{
                                        data=""+  listado.get(i).getMvtoEquipo().getAsignacionEquipo().getCantidadMinutosProgramados();
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
                                                    if(costoTotalApuntador == j){
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
                    respuesta= "Env??o exitoso";
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
                        texto.setText("Matriz de Carb??n generada desde VenturaData");
                        BodyPart adjunto = new MimeBodyPart();
                        //adjunto.setDataHandler(new DataHandler(new FileDataSource("d:/futbol.png")));
                        adjunto.setDataHandler(new DataHandler(new FileDataSource(archivo)));
                        adjunto.setFileName("Matriz_carbon.xlsx");
                        MimeMultipart multiParte = new MimeMultipart();
                        multiParte.addBodyPart(texto);
                        multiParte.addBodyPart(adjunto);
                        message.setSubject("Matriz de la operaci??n de carb??n");
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

    private void pageJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pageJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pageJComboBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Costo_Lavado_Veh??culo;
    private javax.swing.JRadioButton Equipo_Quien_Realiza_LavadoVeh??culo_C??digo;
    private javax.swing.JRadioButton Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n;
    private javax.swing.JRadioButton Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo;
    private javax.swing.JInternalFrame InternalFrameSelectorCampos;
    private javax.swing.JRadioButton Motivo_No_LavadoVeh??culo;
    private javax.swing.JRadioButton Valor_Recaudo_Empresa_LavadoVeh??culos;
    private javax.swing.JRadioButton Valor_Recaudo_Equipo_LavadoVeh??culos;
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JLabel label_exportar;
    private javax.swing.JComboBox<String> minutoFin;
    private javax.swing.JComboBox<String> minutoInicio;
    private javax.swing.JComboBox<Integer> pageJComboBox;
    public javax.swing.JPanel paginationPanel;
    private javax.swing.JButton reporte_lara;
    private javax.swing.JRadioButton selectAll;
    private javax.swing.JRadioButton selectAsignacion_cantidadMinutos_Programados;
    private javax.swing.JRadioButton selectAsignacion_codigo;
    private javax.swing.JRadioButton selectAsignacion_equipoCodigo;
    private javax.swing.JRadioButton selectAsignacion_equipoDescripcion;
    private javax.swing.JRadioButton selectAsignacion_equipoMarca;
    private javax.swing.JRadioButton selectAsignacion_equipoModelo;
    private javax.swing.JRadioButton selectAsignacion_equipoPertenencia;
    private javax.swing.JRadioButton selectAsignacion_equipoTipo;
    private javax.swing.JRadioButton selectAsignacion_fechaFinalizacion;
    private javax.swing.JRadioButton selectAsignacion_fechaInicio;
    private javax.swing.JRadioButton selectAsignacion_fechaRegistro;
    private javax.swing.JRadioButton selectDescargue_articuloCodigo;
    private javax.swing.JRadioButton selectDescargue_articuloCodigoERP;
    private javax.swing.JRadioButton selectDescargue_articuloNombre;
    private javax.swing.JRadioButton selectDescargue_articuloTipo;
    private javax.swing.JRadioButton selectDescargue_articuloUnidadNegocio;
    private javax.swing.JRadioButton selectDescargue_cantidadMinutosDescargue;
    private javax.swing.JRadioButton selectDescargue_centroCosto;
    private javax.swing.JRadioButton selectDescargue_centroCostoAuxiliarDestino;
    private javax.swing.JRadioButton selectDescargue_centroCostoAuxiliarOrigen;
    private javax.swing.JRadioButton selectDescargue_centroCostoMayor;
    private javax.swing.JRadioButton selectDescargue_centroOperacion;
    private javax.swing.JRadioButton selectDescargue_clienteCodigo;
    private javax.swing.JRadioButton selectDescargue_clienteNombre;
    private javax.swing.JRadioButton selectDescargue_codigo;
    private javax.swing.JRadioButton selectDescargue_conexionPesoCcarga;
    private javax.swing.JRadioButton selectDescargue_consecutivo;
    private javax.swing.JRadioButton selectDescargue_deposito;
    private javax.swing.JRadioButton selectDescargue_dia;
    private javax.swing.JRadioButton selectDescargue_estado;
    private javax.swing.JRadioButton selectDescargue_fechaEntradaVehiculo;
    private javax.swing.JRadioButton selectDescargue_fechaFinDescargue;
    private javax.swing.JRadioButton selectDescargue_fechaInicioDescargue;
    private javax.swing.JRadioButton selectDescargue_fechaRegistro;
    private javax.swing.JRadioButton selectDescargue_fechaSalidaVehiculo;
    private javax.swing.JRadioButton selectDescargue_laborRealizada;
    private javax.swing.JRadioButton selectDescargue_lavadoVehiculo;
    private javax.swing.JRadioButton selectDescargue_lavadoVehiculoObservacion;
    private javax.swing.JRadioButton selectDescargue_mes;
    private javax.swing.JRadioButton selectDescargue_numOrden;
    private javax.swing.JRadioButton selectDescargue_observaci??n;
    private javax.swing.JRadioButton selectDescargue_pesoLleno;
    private javax.swing.JRadioButton selectDescargue_pesoNeto;
    private javax.swing.JRadioButton selectDescargue_pesoVacio;
    private javax.swing.JRadioButton selectDescargue_placa;
    private javax.swing.JRadioButton selectDescargue_registroManual;
    private javax.swing.JRadioButton selectDescargue_subcentroCosto;
    private javax.swing.JRadioButton selectDescargue_transportadoraCodigo;
    private javax.swing.JRadioButton selectDescargue_transportadoraNit;
    private javax.swing.JRadioButton selectDescargue_transportadoraNombre;
    private javax.swing.JRadioButton selectDescargue_usuarioQuienCierraVehiculoCodigo;
    private javax.swing.JRadioButton selectDescargue_usuarioQuienCierraVehiculoNombre;
    private javax.swing.JRadioButton selectDescargue_usuarioQuienRegistraVehiculoCodigo;
    private javax.swing.JRadioButton selectDescargue_usuarioQuienRegistraVehiculoCorreo;
    private javax.swing.JRadioButton selectDescargue_usuarioQuienRegistraVehiculoNombre;
    private javax.swing.JRadioButton selectDescargue_usuarioRegistroManualCodigo;
    private javax.swing.JRadioButton selectDescargue_usuarioRegistroManualCorreo;
    private javax.swing.JRadioButton selectDescargue_usuarioRegistroManualNombre;
    private javax.swing.JRadioButton selectMvtoEquipo_CausalInactividad;
    private javax.swing.JRadioButton selectMvtoEquipo_DesdeCarbon;
    private javax.swing.JRadioButton selectMvtoEquipo_Inactividad;
    private javax.swing.JRadioButton selectMvtoEquipo_MotivoParada;
    private javax.swing.JRadioButton selectMvtoEquipo_Recobro;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioAutorizaRecobro_codigo;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioAutorizaRecobro_nombre;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioAutorizaRecobro_observacion;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioCierraMvto_codigo;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioCierraMvto_nombre;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioInactividad_codigo;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioInactividad_nombre;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioRegistraMvto_codigo;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioRegistraMvto_nombre;
    private javax.swing.JRadioButton selectMvtoEquipo_ValorHoraEquipo;
    private javax.swing.JRadioButton selectMvtoEquipo_articuloCodigo;
    private javax.swing.JRadioButton selectMvtoEquipo_articuloDescripcion;
    private javax.swing.JRadioButton selectMvtoEquipo_autorizacionRecobro;
    private javax.swing.JRadioButton selectMvtoEquipo_cantidadMinutosDescargue;
    private javax.swing.JRadioButton selectMvtoEquipo_centroCosto;
    private javax.swing.JRadioButton selectMvtoEquipo_centroCostoAuxiliarDestino;
    private javax.swing.JRadioButton selectMvtoEquipo_centroCostoAxiliarOrigen;
    private javax.swing.JRadioButton selectMvtoEquipo_centroCostoMayor;
    private javax.swing.JRadioButton selectMvtoEquipo_centroOperacion;
    private javax.swing.JRadioButton selectMvtoEquipo_clienteCodigo;
    private javax.swing.JRadioButton selectMvtoEquipo_clienteNombre;
    private javax.swing.JRadioButton selectMvtoEquipo_codigo;
    private javax.swing.JRadioButton selectMvtoEquipo_costoTotal;
    private javax.swing.JRadioButton selectMvtoEquipo_estado;
    private javax.swing.JRadioButton selectMvtoEquipo_fechaFinDescargue;
    private javax.swing.JRadioButton selectMvtoEquipo_fechaInicioDescargue;
    private javax.swing.JRadioButton selectMvtoEquipo_fechaRegistro;
    private javax.swing.JRadioButton selectMvtoEquipo_laborRealizada;
    private javax.swing.JRadioButton selectMvtoEquipo_mes;
    private javax.swing.JRadioButton selectMvtoEquipo_motonaveCodigo;
    private javax.swing.JRadioButton selectMvtoEquipo_motonaveDescripci??n;
    private javax.swing.JRadioButton selectMvtoEquipo_numeroOrden;
    private javax.swing.JRadioButton selectMvtoEquipo_observacion;
    private javax.swing.JRadioButton selectMvtoEquipo_proveedorEquipoNit;
    private javax.swing.JRadioButton selectMvtoEquipo_proveedorEquipoNombre;
    private javax.swing.JRadioButton selectMvtoEquipo_subcentroCosto;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel titulo12;
    private javax.swing.JLabel titulo13;
    private javax.swing.JLabel titulo17;
    private javax.swing.JLabel titulo19;
    private javax.swing.JLabel titulo2;
    private javax.swing.JLabel titulo20;
    private javax.swing.JLabel titulo21;
    private javax.swing.JLabel titulo23;
    private javax.swing.JLabel titulo24;
    private javax.swing.JLabel titulo26;
    private javax.swing.JLabel titulo27;
    // End of variables declaration//GEN-END:variables
    
    public void tabla_Listar(String data1, String data2) throws SQLException{
        tabla.setModel(crearModeloDeTabla());
        listado=new ControlDB_MvtoCarbon(tipoConexion).buscarMvtoCarbon_GenerarMatriz(data1, data2);
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
                    Logger.getLogger(MvtoCarbon_GenerarMatriz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"No se puedo procesar el descargue de Carbon", "Advertencia",JOptionPane.ERROR_MESSAGE);
        }   
    }
    public void selectorCampoPorDefecto(){
        selectDescargue_codigo.setSelected(false);
        selectDescargue_centroOperacion.setSelected(true);
        selectDescargue_subcentroCosto.setSelected(true);
        selectDescargue_centroCostoAuxiliarOrigen.setSelected(true);
        selectDescargue_centroCostoAuxiliarDestino.setSelected(true);
        selectDescargue_centroCosto.setSelected(true);
        selectDescargue_centroCostoMayor.setSelected(true);
        selectDescargue_laborRealizada.setSelected(true);
        selectDescargue_articuloCodigo.setSelected(false);
        selectDescargue_articuloNombre.setSelected(true);
        selectDescargue_articuloTipo.setSelected(true);
        selectDescargue_articuloCodigoERP.setSelected(true);
        selectDescargue_articuloUnidadNegocio.setSelected(true);
        selectDescargue_clienteCodigo.setSelected(false);
        selectDescargue_clienteNombre.setSelected(true);
        selectDescargue_transportadoraCodigo.setSelected(false);
        selectDescargue_transportadoraNit.setSelected(false);
        selectDescargue_transportadoraNombre.setSelected(true);
        selectDescargue_mes.setSelected(true);
        selectDescargue_dia.setSelected(true);
        selectDescargue_fechaRegistro.setSelected(true);
        selectDescargue_lavadoVehiculo.setSelected(true);
        selectDescargue_lavadoVehiculoObservacion.setSelected(false); 
        Motivo_No_LavadoVeh??culo.setSelected(true);
        Equipo_Quien_Realiza_LavadoVeh??culo_C??digo.setSelected(true);
        Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo.setSelected(false);
        Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n.setSelected(true);
        Costo_Lavado_Veh??culo.setSelected(true);
        Valor_Recaudo_Empresa_LavadoVeh??culos.setSelected(true);
        Valor_Recaudo_Equipo_LavadoVeh??culos.setSelected(true);
        selectDescargue_numOrden.setSelected(true);
        selectDescargue_deposito.setSelected(true);
        selectDescargue_consecutivo.setSelected(false);
        selectDescargue_placa.setSelected(true);
        selectDescargue_pesoVacio.setSelected(false);
        selectDescargue_pesoLleno.setSelected(false);
        selectDescargue_pesoNeto.setSelected(true);
        selectDescargue_fechaEntradaVehiculo.setSelected(true);
        selectDescargue_fechaSalidaVehiculo.setSelected(true);
        selectDescargue_fechaInicioDescargue.setSelected(true);
        selectDescargue_fechaFinDescargue.setSelected(true);
        selectDescargue_cantidadMinutosDescargue.setSelected(true);
        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setSelected(false);
        selectDescargue_usuarioQuienRegistraVehiculoNombre.setSelected(true);
        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setSelected(false);
        selectDescargue_observaci??n.setSelected(false);
        selectDescargue_estado.setSelected(false);
        selectDescargue_conexionPesoCcarga.setSelected(false);
        selectDescargue_registroManual.setSelected(false);
        selectDescargue_usuarioRegistroManualCodigo.setSelected(false);
        selectDescargue_usuarioRegistroManualNombre.setSelected(false);
        selectDescargue_usuarioRegistroManualCorreo.setSelected(false);
        selectAsignacion_equipoCodigo.setSelected(false);
        selectAsignacion_equipoTipo.setSelected(false);
        selectAsignacion_equipoMarca.setSelected(false);
        selectAsignacion_equipoModelo.setSelected(false);
        selectAsignacion_equipoDescripcion.setSelected(true);
        selectAsignacion_equipoPertenencia.setSelected(true);
        selectAsignacion_codigo.setSelected(false);
        selectAsignacion_fechaRegistro.setSelected(false);
        selectAsignacion_fechaInicio.setSelected(false);
        selectAsignacion_fechaFinalizacion.setSelected(false);
        selectAsignacion_cantidadMinutos_Programados.setSelected(false);
        selectMvtoEquipo_codigo.setSelected(false);
        selectMvtoEquipo_mes.setSelected(false);
        selectMvtoEquipo_fechaRegistro.setSelected(false);
        selectMvtoEquipo_proveedorEquipoNit.setSelected(false);
        selectMvtoEquipo_proveedorEquipoNombre.setSelected(false);
        selectMvtoEquipo_numeroOrden.setSelected(false);
        selectMvtoEquipo_centroOperacion.setSelected(false);
        selectMvtoEquipo_subcentroCosto.setSelected(false);
        selectMvtoEquipo_centroCostoAxiliarOrigen.setSelected(false);
        selectMvtoEquipo_centroCostoAuxiliarDestino.setSelected(false);
        selectMvtoEquipo_centroCosto.setSelected(false);
        selectMvtoEquipo_centroCostoMayor.setSelected(false);
        selectMvtoEquipo_laborRealizada.setSelected(false);
        selectMvtoEquipo_clienteCodigo.setSelected(false);
        selectMvtoEquipo_clienteNombre.setSelected(false);
        selectMvtoEquipo_articuloCodigo.setSelected(false);
        selectMvtoEquipo_articuloDescripcion.setSelected(false);
        selectMvtoEquipo_motonaveCodigo.setSelected(false);
        selectMvtoEquipo_motonaveDescripci??n.setSelected(false);
        selectMvtoEquipo_fechaInicioDescargue.setSelected(false);
        selectMvtoEquipo_fechaFinDescargue.setSelected(false);
        selectMvtoEquipo_cantidadMinutosDescargue.setSelected(false);
        selectMvtoEquipo_ValorHoraEquipo.setSelected(false);
        selectMvtoEquipo_costoTotal.setSelected(false);
        selectMvtoEquipo_Recobro.setSelected(false);
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setSelected(false);
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setSelected(false);
        selectMvtoEquipo_autorizacionRecobro.setSelected(false);
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setSelected(false);
        selectMvtoEquipo_Inactividad.setSelected(false);
        selectMvtoEquipo_CausalInactividad.setSelected(false);
        selectMvtoEquipo_UsuarioInactividad_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioInactividad_nombre.setSelected(false);
        selectMvtoEquipo_MotivoParada.setSelected(false);
        selectMvtoEquipo_observacion.setSelected(false);
        selectMvtoEquipo_estado.setSelected(false);
        selectMvtoEquipo_DesdeCarbon.setSelected(false);
    }
    public void selectorCampoPorDefectoLara(){
        selectDescargue_codigo.setSelected(false);
        selectDescargue_centroOperacion.setSelected(false);
        selectDescargue_subcentroCosto.setSelected(true);
        selectDescargue_centroCostoAuxiliarOrigen.setSelected(true);
        selectDescargue_centroCostoAuxiliarDestino.setSelected(true);
        selectDescargue_centroCosto.setSelected(false);
        selectDescargue_centroCostoMayor.setSelected(false);
        selectDescargue_laborRealizada.setSelected(true);
        selectDescargue_articuloCodigo.setSelected(false);
        selectDescargue_articuloNombre.setSelected(true);
        selectDescargue_articuloTipo.setSelected(false);
        selectDescargue_articuloCodigoERP.setSelected(false);
        selectDescargue_articuloUnidadNegocio.setSelected(false);
        selectDescargue_clienteCodigo.setSelected(false);
        selectDescargue_clienteNombre.setSelected(true);
        selectDescargue_transportadoraCodigo.setSelected(false);
        selectDescargue_transportadoraNit.setSelected(false);
        selectDescargue_transportadoraNombre.setSelected(false);
        selectDescargue_mes.setSelected(true);
        selectDescargue_dia.setSelected(true);
        selectDescargue_fechaRegistro.setSelected(false);
        selectDescargue_lavadoVehiculo.setSelected(true);
        selectDescargue_lavadoVehiculoObservacion.setSelected(false); 
        Motivo_No_LavadoVeh??culo.setSelected(false);
        Equipo_Quien_Realiza_LavadoVeh??culo_C??digo.setSelected(false);
        Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo.setSelected(false);
        Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n.setSelected(true);
        Costo_Lavado_Veh??culo.setSelected(false); 
        Valor_Recaudo_Empresa_LavadoVeh??culos.setSelected(true); 
        Valor_Recaudo_Equipo_LavadoVeh??culos.setSelected(false); 
        selectDescargue_numOrden.setSelected(false);
        selectDescargue_deposito.setSelected(true);
        selectDescargue_consecutivo.setSelected(true);
        selectDescargue_placa.setSelected(true);
        selectDescargue_pesoVacio.setSelected(true);
        selectDescargue_pesoLleno.setSelected(true);
        selectDescargue_pesoNeto.setSelected(true);
        selectDescargue_fechaEntradaVehiculo.setSelected(true);
        selectDescargue_fechaSalidaVehiculo.setSelected(true);
        selectDescargue_fechaInicioDescargue.setSelected(false);
        selectDescargue_fechaFinDescargue.setSelected(false);
        selectDescargue_cantidadMinutosDescargue.setSelected(false);
        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setSelected(false);
        selectDescargue_usuarioQuienRegistraVehiculoNombre.setSelected(false);
        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setSelected(false);
        selectDescargue_observaci??n.setSelected(false);
        selectDescargue_estado.setSelected(false);
        selectDescargue_conexionPesoCcarga.setSelected(false);
        selectDescargue_registroManual.setSelected(false);
        selectDescargue_usuarioRegistroManualCodigo.setSelected(false);
        selectDescargue_usuarioRegistroManualNombre.setSelected(false);
        selectDescargue_usuarioRegistroManualCorreo.setSelected(false);
        selectAsignacion_equipoCodigo.setSelected(false);
        selectAsignacion_equipoTipo.setSelected(false);
        selectAsignacion_equipoMarca.setSelected(false);
        selectAsignacion_equipoModelo.setSelected(false);
        selectAsignacion_equipoDescripcion.setSelected(false);
        selectAsignacion_equipoPertenencia.setSelected(false);
        selectAsignacion_codigo.setSelected(false);
        selectAsignacion_fechaRegistro.setSelected(false);
        selectAsignacion_fechaInicio.setSelected(false);
        selectAsignacion_fechaFinalizacion.setSelected(false);
        selectAsignacion_cantidadMinutos_Programados.setSelected(false);
        selectMvtoEquipo_codigo.setSelected(false);
        selectMvtoEquipo_mes.setSelected(false);
        selectMvtoEquipo_fechaRegistro.setSelected(false);
        selectMvtoEquipo_proveedorEquipoNit.setSelected(false);
        selectMvtoEquipo_proveedorEquipoNombre.setSelected(false);
        selectMvtoEquipo_numeroOrden.setSelected(false);
        selectMvtoEquipo_centroOperacion.setSelected(false);
        selectMvtoEquipo_subcentroCosto.setSelected(false);
        selectMvtoEquipo_centroCostoAxiliarOrigen.setSelected(false);
        selectMvtoEquipo_centroCostoAuxiliarDestino.setSelected(false);
        selectMvtoEquipo_centroCosto.setSelected(false);
        selectMvtoEquipo_centroCostoMayor.setSelected(false);
        selectMvtoEquipo_laborRealizada.setSelected(false);
        selectMvtoEquipo_clienteCodigo.setSelected(false);
        selectMvtoEquipo_clienteNombre.setSelected(false);
        selectMvtoEquipo_articuloCodigo.setSelected(false);
        selectMvtoEquipo_articuloDescripcion.setSelected(false);
        selectMvtoEquipo_motonaveCodigo.setSelected(false);
        selectMvtoEquipo_motonaveDescripci??n.setSelected(false);
        selectMvtoEquipo_fechaInicioDescargue.setSelected(false);
        selectMvtoEquipo_fechaFinDescargue.setSelected(false);
        selectMvtoEquipo_cantidadMinutosDescargue.setSelected(false);
        selectMvtoEquipo_ValorHoraEquipo.setSelected(false);
        selectMvtoEquipo_costoTotal.setSelected(false);
        selectMvtoEquipo_Recobro.setSelected(false);
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setSelected(false);
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setSelected(false);
        selectDescargue_usuarioQuienCierraVehiculoCodigo.setSelected(false);
        selectDescargue_usuarioQuienCierraVehiculoNombre.setSelected(false);
        selectMvtoEquipo_UsuarioCierraMvto_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioCierraMvto_nombre.setSelected(false);
        selectMvtoEquipo_autorizacionRecobro.setSelected(false);
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setSelected(false);
        selectMvtoEquipo_Inactividad.setSelected(false);
        selectMvtoEquipo_CausalInactividad.setSelected(false);
        selectMvtoEquipo_UsuarioInactividad_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioInactividad_nombre.setSelected(false);
        selectMvtoEquipo_MotivoParada.setSelected(false);
        selectMvtoEquipo_observacion.setSelected(false);
        selectMvtoEquipo_estado.setSelected(false);
        selectMvtoEquipo_DesdeCarbon.setSelected(false);
    }
    public void selectorCampoTodos(){
        selectDescargue_codigo.setSelected(true);
        selectDescargue_centroOperacion.setSelected(true);
        selectDescargue_subcentroCosto.setSelected(true);
        selectDescargue_centroCostoAuxiliarOrigen.setSelected(true);
        selectDescargue_centroCostoAuxiliarDestino.setSelected(true);
        selectDescargue_centroCosto.setSelected(true);
        selectDescargue_centroCostoMayor.setSelected(true);
        selectDescargue_laborRealizada.setSelected(true);
        selectDescargue_articuloCodigo.setSelected(true);
        selectDescargue_articuloNombre.setSelected(true);
        selectDescargue_articuloTipo.setSelected(true);
        selectDescargue_articuloCodigoERP.setSelected(true);
        selectDescargue_articuloUnidadNegocio.setSelected(true);
        selectDescargue_clienteCodigo.setSelected(true);
        selectDescargue_clienteNombre.setSelected(true);
        selectDescargue_transportadoraCodigo.setSelected(true);
        selectDescargue_transportadoraNit.setSelected(true);
        selectDescargue_transportadoraNombre.setSelected(true);
        selectDescargue_mes.setSelected(true);
        selectDescargue_dia.setSelected(true);
        selectDescargue_fechaRegistro.setSelected(true);
        selectDescargue_lavadoVehiculo.setSelected(true);
        selectDescargue_lavadoVehiculoObservacion.setSelected(true);
        
        Motivo_No_LavadoVeh??culo.setSelected(true);
        Equipo_Quien_Realiza_LavadoVeh??culo_C??digo.setSelected(true);
        Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo.setSelected(true);
        Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n.setSelected(true);
        Costo_Lavado_Veh??culo.setSelected(true);
        Valor_Recaudo_Empresa_LavadoVeh??culos.setSelected(true);
        Valor_Recaudo_Equipo_LavadoVeh??culos.setSelected(true);
        
        selectDescargue_numOrden.setSelected(true);
        selectDescargue_deposito.setSelected(true);
        selectDescargue_consecutivo.setSelected(true);
        selectDescargue_placa.setSelected(true);
        selectDescargue_pesoVacio.setSelected(true);
        selectDescargue_pesoLleno.setSelected(true);
        selectDescargue_pesoNeto.setSelected(true);
        selectDescargue_fechaEntradaVehiculo.setSelected(true);
        selectDescargue_fechaSalidaVehiculo.setSelected(true);
        selectDescargue_fechaInicioDescargue.setSelected(true);
        selectDescargue_fechaFinDescargue.setSelected(true);
        selectDescargue_cantidadMinutosDescargue.setSelected(true);
        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setSelected(true);
        selectDescargue_usuarioQuienRegistraVehiculoNombre.setSelected(true);
        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setSelected(true);
        selectDescargue_observaci??n.setSelected(true);
        selectDescargue_estado.setSelected(true);
        selectDescargue_conexionPesoCcarga.setSelected(true);
        selectDescargue_registroManual.setSelected(true);
        selectDescargue_usuarioRegistroManualCodigo.setSelected(true);
        selectDescargue_usuarioRegistroManualNombre.setSelected(true);
        selectDescargue_usuarioRegistroManualCorreo.setSelected(true);
        selectAsignacion_equipoCodigo.setSelected(true);
        selectAsignacion_equipoTipo.setSelected(true);
        selectAsignacion_equipoMarca.setSelected(true);
        selectAsignacion_equipoModelo.setSelected(true);
        selectAsignacion_equipoDescripcion.setSelected(true);
        selectAsignacion_equipoPertenencia.setSelected(true);
        selectAsignacion_codigo.setSelected(true);
        selectAsignacion_fechaRegistro.setSelected(true);
        selectAsignacion_fechaInicio.setSelected(true);
        selectAsignacion_fechaFinalizacion.setSelected(true);
        selectAsignacion_cantidadMinutos_Programados.setSelected(true);
        selectMvtoEquipo_codigo.setSelected(true);
        selectMvtoEquipo_mes.setSelected(true);
        selectMvtoEquipo_fechaRegistro.setSelected(true);
        selectMvtoEquipo_proveedorEquipoNit.setSelected(true);
        selectMvtoEquipo_proveedorEquipoNombre.setSelected(true);
        selectMvtoEquipo_numeroOrden.setSelected(true);
        selectMvtoEquipo_centroOperacion.setSelected(true);
        selectMvtoEquipo_subcentroCosto.setSelected(true);
        selectMvtoEquipo_centroCostoAxiliarOrigen.setSelected(true);
        selectMvtoEquipo_centroCostoAuxiliarDestino.setSelected(true);
        selectMvtoEquipo_centroCosto.setSelected(true);
        selectMvtoEquipo_centroCostoMayor.setSelected(true);
        selectMvtoEquipo_laborRealizada.setSelected(true);
        selectMvtoEquipo_clienteCodigo.setSelected(true);
        selectMvtoEquipo_clienteNombre.setSelected(true);
        selectMvtoEquipo_articuloCodigo.setSelected(true);
        selectMvtoEquipo_articuloDescripcion.setSelected(true);
        selectMvtoEquipo_motonaveCodigo.setSelected(true);
        selectMvtoEquipo_motonaveDescripci??n.setSelected(true);
        selectMvtoEquipo_fechaInicioDescargue.setSelected(true);
        selectMvtoEquipo_fechaFinDescargue.setSelected(true);
        selectMvtoEquipo_cantidadMinutosDescargue.setSelected(true);
        selectMvtoEquipo_ValorHoraEquipo.setSelected(true);
        selectMvtoEquipo_costoTotal.setSelected(true);
        selectMvtoEquipo_Recobro.setSelected(true);
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setSelected(true);
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setSelected(true);
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setSelected(true);
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setSelected(true);
        selectDescargue_usuarioQuienCierraVehiculoCodigo.setSelected(true);
        selectDescargue_usuarioQuienCierraVehiculoNombre.setSelected(true);
        selectMvtoEquipo_UsuarioCierraMvto_codigo.setSelected(true);
        selectMvtoEquipo_UsuarioCierraMvto_nombre.setSelected(true);
        selectMvtoEquipo_autorizacionRecobro.setSelected(true);
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setSelected(true);
        selectMvtoEquipo_Inactividad.setSelected(true);
        selectMvtoEquipo_CausalInactividad.setSelected(true);
        selectMvtoEquipo_UsuarioInactividad_codigo.setSelected(true);
        selectMvtoEquipo_UsuarioInactividad_nombre.setSelected(true);
        selectMvtoEquipo_MotivoParada.setSelected(true);
        selectMvtoEquipo_observacion.setSelected(true);
        selectMvtoEquipo_estado.setSelected(true);
        selectMvtoEquipo_DesdeCarbon.setSelected(true);
        
    }
    public void selectorCampoNinguno(){
        selectDescargue_codigo.setSelected(false);
        selectDescargue_centroOperacion.setSelected(false);
        selectDescargue_subcentroCosto.setSelected(false);
        selectDescargue_centroCostoAuxiliarOrigen.setSelected(false);
        selectDescargue_centroCostoAuxiliarDestino.setSelected(false);
        selectDescargue_centroCosto.setSelected(false);
        selectDescargue_centroCostoMayor.setSelected(false);
        selectDescargue_laborRealizada.setSelected(false);
        selectDescargue_articuloCodigo.setSelected(false);
        selectDescargue_articuloNombre.setSelected(false);
        selectDescargue_articuloTipo.setSelected(false);
        selectDescargue_articuloCodigoERP.setSelected(false);
        selectDescargue_articuloUnidadNegocio.setSelected(false);
        selectDescargue_clienteCodigo.setSelected(false);
        selectDescargue_clienteNombre.setSelected(false);
        selectDescargue_transportadoraCodigo.setSelected(false);
        selectDescargue_transportadoraNit.setSelected(false);
        selectDescargue_transportadoraNombre.setSelected(false);
        selectDescargue_mes.setSelected(false);
        selectDescargue_dia.setSelected(false);
        selectDescargue_fechaRegistro.setSelected(false);
        selectDescargue_lavadoVehiculo.setSelected(false);
        selectDescargue_lavadoVehiculoObservacion.setSelected(false);
        
        Motivo_No_LavadoVeh??culo.setSelected(false);
        Equipo_Quien_Realiza_LavadoVeh??culo_C??digo.setSelected(false);
        Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo.setSelected(false);
        Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n.setSelected(false);
        Costo_Lavado_Veh??culo.setSelected(false);
        Valor_Recaudo_Empresa_LavadoVeh??culos.setSelected(false);
        Valor_Recaudo_Equipo_LavadoVeh??culos.setSelected(false);
        
        selectDescargue_numOrden.setSelected(false);
        selectDescargue_deposito.setSelected(false);
        selectDescargue_consecutivo.setSelected(false);
        selectDescargue_placa.setSelected(false);
        selectDescargue_pesoVacio.setSelected(false);
        selectDescargue_pesoLleno.setSelected(false);
        selectDescargue_pesoNeto.setSelected(false);
        selectDescargue_fechaEntradaVehiculo.setSelected(false);
        selectDescargue_fechaSalidaVehiculo.setSelected(false);
        selectDescargue_fechaInicioDescargue.setSelected(false);
        selectDescargue_fechaFinDescargue.setSelected(false);
        selectDescargue_cantidadMinutosDescargue.setSelected(false);
        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setSelected(false);
        selectDescargue_usuarioQuienRegistraVehiculoNombre.setSelected(false);
        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setSelected(false);
        selectDescargue_observaci??n.setSelected(false);
        selectDescargue_estado.setSelected(false);
        selectDescargue_conexionPesoCcarga.setSelected(false);
        selectDescargue_registroManual.setSelected(false);
        selectDescargue_usuarioRegistroManualCodigo.setSelected(false);
        selectDescargue_usuarioRegistroManualNombre.setSelected(false);
        selectDescargue_usuarioRegistroManualCorreo.setSelected(false);
        selectAsignacion_equipoCodigo.setSelected(false);
        selectAsignacion_equipoTipo.setSelected(false);
        selectAsignacion_equipoMarca.setSelected(false);
        selectAsignacion_equipoModelo.setSelected(false);
        selectAsignacion_equipoDescripcion.setSelected(false);
        selectAsignacion_equipoPertenencia.setSelected(false);
        selectAsignacion_codigo.setSelected(false);
        selectAsignacion_fechaRegistro.setSelected(false);
        selectAsignacion_fechaInicio.setSelected(false);
        selectAsignacion_fechaFinalizacion.setSelected(false);
        selectAsignacion_cantidadMinutos_Programados.setSelected(false);
        selectMvtoEquipo_codigo.setSelected(false);
        selectMvtoEquipo_mes.setSelected(false);
        selectMvtoEquipo_fechaRegistro.setSelected(false);
        selectMvtoEquipo_proveedorEquipoNit.setSelected(false);
        selectMvtoEquipo_proveedorEquipoNombre.setSelected(false);
        selectMvtoEquipo_numeroOrden.setSelected(false);
        selectMvtoEquipo_centroOperacion.setSelected(false);
        selectMvtoEquipo_subcentroCosto.setSelected(false);
        selectMvtoEquipo_centroCostoAxiliarOrigen.setSelected(false);
        selectMvtoEquipo_centroCostoAuxiliarDestino.setSelected(false);
        selectMvtoEquipo_centroCosto.setSelected(false);
        selectMvtoEquipo_centroCostoMayor.setSelected(false);
        selectMvtoEquipo_laborRealizada.setSelected(false);
        selectMvtoEquipo_clienteCodigo.setSelected(false);
        selectMvtoEquipo_clienteNombre.setSelected(false);
        selectMvtoEquipo_articuloCodigo.setSelected(false);
        selectMvtoEquipo_articuloDescripcion.setSelected(false);
        selectMvtoEquipo_motonaveCodigo.setSelected(false);
        selectMvtoEquipo_motonaveDescripci??n.setSelected(false);
        selectMvtoEquipo_fechaInicioDescargue.setSelected(false);
        selectMvtoEquipo_fechaFinDescargue.setSelected(false);
        selectMvtoEquipo_cantidadMinutosDescargue.setSelected(false);
        selectMvtoEquipo_ValorHoraEquipo.setSelected(false);
        selectMvtoEquipo_costoTotal.setSelected(false);
        selectMvtoEquipo_Recobro.setSelected(false);
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setSelected(false);
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setSelected(false);
        selectDescargue_usuarioQuienCierraVehiculoCodigo.setSelected(false);
        selectDescargue_usuarioQuienCierraVehiculoNombre.setSelected(false);
        selectMvtoEquipo_UsuarioCierraMvto_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioCierraMvto_nombre.setSelected(false);
        selectMvtoEquipo_autorizacionRecobro.setSelected(false);
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setSelected(false);
        selectMvtoEquipo_Inactividad.setSelected(false);
        selectMvtoEquipo_CausalInactividad.setSelected(false);
        selectMvtoEquipo_UsuarioInactividad_codigo.setSelected(false);
        selectMvtoEquipo_UsuarioInactividad_nombre.setSelected(false);
        selectMvtoEquipo_MotivoParada.setSelected(false);
        selectMvtoEquipo_observacion.setSelected(false);
        selectMvtoEquipo_estado.setSelected(false);
        selectMvtoEquipo_DesdeCarbon.setSelected(false);
    } 
    public final void events(){
       pageJComboBox.addActionListener(this);
       tabla.getModel().addTableModelListener(this);
    }
    private ModeloDeTabla crearModeloDeTabla() {
        return new ModeloDeTabla<MvtoCarbon_ListadoEquipos>() {            
            @Override
            public Object getValueAt(MvtoCarbon_ListadoEquipos listado1, int columnas) {
               switch(encabezadoTabla.get(columnas)){
                    case "C??digo":{
                       return listado1.getMvtoCarbon().getCodigo();
                    }
                    case "Centro_Operacion":{
                       return listado1.getMvtoCarbon().getCentroOperacion().getDescripcion();
                    }
                    case "Subcentro_Costo":{
                       return listado1.getMvtoCarbon().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                    }
                    case "Centro_Costo_Auxiliar_Origen":{
                       return listado1.getMvtoCarbon().getCentroCostoAuxiliar().getDescripcion();
                    }
                    case "Centro_Costo_Auxiliar_Destino":{
                       return listado1.getMvtoCarbon().getCentroCostoAuxiliarDestino().getDescripcion();
                    }
                    case "Centro_Costo":{
                       return listado1.getMvtoCarbon().getCentroCosto().getDescripci??n();
                    }
                    case "Centro_Costo_Mayor":{
                       return listado1.getMvtoCarbon().getCentroCostoMayor().getDescripcion();
                    }
                    case "Labor_Realizada":{
                       return listado1.getMvtoCarbon().getLaborRealizada().getDescripcion();
                    }
                    case "Art??culo_C??digo":{
                       return listado1.getMvtoCarbon().getArticulo().getCodigo();
                    }
                    case "Art??culo_Nombre":{
                       return listado1.getMvtoCarbon().getArticulo().getDescripcion();
                    }
                    case "Art??culo_Tipo":{
                       return listado1.getMvtoCarbon().getArticulo().getTipoArticulo().getDescripcion();
                    }
                    case "Art??culo_C??digo_ERP":{
                       return listado1.getMvtoCarbon().getArticulo().getTipoArticulo().getCodigoERP();
                    }
                    case "Art??culo_Unidad_Negocio":{
                       return listado1.getMvtoCarbon().getArticulo().getTipoArticulo().getUnidadNegocio();
                    }
                    case "Cliente_C??digo":{
                       return listado1.getMvtoCarbon().getCliente().getCodigo();
                    }
                    case "Cliente_Nombre":{
                       return listado1.getMvtoCarbon().getCliente().getDescripcion();
                    }
                    case "Transportadora_C??digo":{
                       return listado1.getMvtoCarbon().getTransportadora().getCodigo();
                    }
                    case "Transportadora_Nit":{
                       return listado1.getMvtoCarbon().getTransportadora().getNit();
                    }
                    case "Transportadora_Nombre":{
                       return listado1.getMvtoCarbon().getTransportadora().getDescripcion();
                    }
                    case "Mes":{
                       return getMes(listado1.getMvtoCarbon().getMes());
                    }
                    case "D??a":{
                       return listado1.getMvtoCarbon().getDia();
                    }
                    case "Fecha_Registro":{
                       return listado1.getMvtoCarbon().getFechaRegistro();
                    }
                    case "Lavado_Veh??culo":{
                       return listado1.getMvtoCarbon().getLavadoVehiculo();
                    }
                    case "Lavado_Veh??culo_Observaci??n":{
                       return listado1.getMvtoCarbon().getLavadoVehiculo_Observacion();
                    }
                    case "Motivo_No_LavadoVeh??culo":{
                       return listado1.getMvtoCarbon().getMotivoNoLavado().getDescripcion();
                    }
                    case "Equipo_Quien_Realiza_LavadoVeh??culo_C??digo":{
                       return listado1.getMvtoCarbon().getEquipoLavadoVehiculo().getCodigo();
                    }
                    case "Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo":{
                       return listado1.getMvtoCarbon().getEquipoLavadoVehiculo().getTipoEquipo().getDescripcion();
                    }
                    case "Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n":{
                       return listado1.getMvtoCarbon().getEquipoLavadoVehiculo().getDescripcion()+" "+listado1.getMvtoCarbon().getEquipoLavadoVehiculo().getModelo();
                    }
                    case "Costo_Lavado_Veh??culo":{
                       return listado1.getMvtoCarbon().getCostoLavadoVehiculo();
                    }
                    case "Valor_Recaudo_Empresa_LavadoVeh??culos":{
                       return listado1.getMvtoCarbon().getValorRecaudoEmpresa_lavadoVehiculo();
                    }
                    case "Valor_Recaudo_Equipo_LavadoVeh??culos":{
                       return listado1.getMvtoCarbon().getValorRecaudoEquipo_lavadoVehiculo();
                    }
                    case "N??mero_Orden":{
                       return listado1.getMvtoCarbon().getNumero_orden();
                    }
                    case "Deposito":{
                       return listado1.getMvtoCarbon().getDeposito();
                    }
                    case "Consecutivo":{
                       return listado1.getMvtoCarbon().getConsecutivo();
                    }
                    case "Placa":{
                       return listado1.getMvtoCarbon().getPlaca();
                    }
                    case "PesoVacio":{
                       return listado1.getMvtoCarbon().getPesoVacio();
                    }
                    case "Peso_Lleno":{
                       return listado1.getMvtoCarbon().getPesoLleno();
                    }
                    case "Peso_Neto":{
                       return listado1.getMvtoCarbon().getPesoNeto();
                    }
                    case "Fecha_Entrada_Veh??culo":{
                       return listado1.getMvtoCarbon().getFechaEntradaVehiculo();
                    }
                    case "Fecha_Salida_Veh??culo":{
                       return listado1.getMvtoCarbon().getFecha_SalidaVehiculo();
                    }
                    case "Fecha_Inicio_Descargue":{
                       return listado1.getMvtoCarbon().getFechaInicioDescargue();
                    }
                    case "Fecha_Fin_Descargue":{
                       return listado1.getMvtoCarbon().getFechaFinDescargue();
                    }
                    case "Cantidad_Minutos":{
                       return listado1.getMvtoCarbon().getCantidadHorasDescargue();
                    }
                    case "Usuario_Quien_Registra_Veh??culo_C??digo":{
                       return listado1.getMvtoCarbon().getUsuarioRegistroMovil().getCodigo();
                    }
                    case "Usuario_Quien_Registra_Veh??culo_Nombre":{
                       return listado1.getMvtoCarbon().getUsuarioRegistroMovil().getNombres();
                    }
                    case "Usuario_Quien_Registra_Veh??culo_Correo":{
                       return listado1.getMvtoCarbon().getUsuarioRegistroMovil().getCorreo();
                    }
                    case "Observaci??n":{
                       return listado1.getMvtoCarbon().getObservacion();
                    }
                    case "Estado":{
                       return listado1.getMvtoCarbon().getEstadoMvtoCarbon().getDescripcion();
                    }
                    case "Conexi??n_Peso_CCARGA":{
                       return listado1.getMvtoCarbon().getConexionPesoCcarga();
                    }
                    case "Registro_Manual":{
                       return listado1.getMvtoCarbon().getRegistroManual();
                    }
                    case "Usuario_Quien_RegistraManual_C??digo":{
                       return listado1.getMvtoCarbon().getUsuarioRegistraManual().getCodigo();
                    }
                    case "Usuario_Quien_RegistraManual_Nombre":{
                       return listado1.getMvtoCarbon().getUsuarioRegistraManual().getNombres();
                    }
                    case "Usuario_Quien_RegistraManual_Correo":{
                       return listado1.getMvtoCarbon().getUsuarioRegistraManual().getCorreo();
                    }
                    case "Usuario_Quien_Cierra_Veh??culo_C??digo":{
                       return listado1.getMvtoCarbon().getUsuarioCierraRegistro().getCodigo();
                    }
                    case "Usuario_Quien_Cierra_Veh??culo_Nombre":{
                       return listado1.getMvtoCarbon().getUsuarioCierraRegistro().getNombres();
                    }
                    case "MvtoEquipo_C??digo":{
                       return listado1.getMvtoEquipo().getCodigo();
                    }
                    case "MvtoEquipo_MES":{
                       return getMes(listado1.getMvtoEquipo().getMes());
                    }
                    case "MvtoEquipo_Fecha_Registro":{
                       return listado1.getMvtoEquipo().getFechaRegistro();
                    }
                    case "MvtoEquipo_ProveedorEquipo_NIT":{
                       return listado1.getMvtoEquipo().getProveedorEquipo().getNit();
                    }
                    case "MvtoEquipo_ProveedorEquipo_Nombre":{
                       return listado1.getMvtoEquipo().getProveedorEquipo().getDescripcion();
                    }
                    case "MvtoEquipo_N??mero_Orden":{
                       return listado1.getMvtoEquipo().getNumeroOrden();
                    }
                    case "MvtoEquipo_Centro_Operaci??n":{
                       return listado1.getMvtoEquipo().getCentroOperacion().getDescripcion();
                    }
                    case "MvtoEquipo_Subcentro_Costo":{
                       return listado1.getMvtoEquipo().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                    }
                    case "MvtoEquipo_Centro_Costo_Auxiliar_Origen":{
                       return listado1.getMvtoEquipo().getCentroCostoAuxiliar().getDescripcion();
                    }
                    case "MvtoEquipo_Centro_Costo_Auxiliar_Destino":{
                       return listado1.getMvtoEquipo().getCentroCostoAuxiliarDestino().getDescripcion();
                    }
                    case "MvtoEquipo_Centro_Costo":{
                       return listado1.getMvtoEquipo().getCentroCosto().getDescripci??n();
                    }
                    case "MvtoEquipo_Centro_Costo_Mayor":{
                       return listado1.getMvtoEquipo().getCentroCostoMayor().getDescripcion();
                    }
                    case "MvtoEquipo_Labor_Realizada":{
                       return listado1.getMvtoEquipo().getLaborRealizada().getDescripcion();
                    }
                    case "MvtoEquipo_Cliente_C??digo":{
                       return listado1.getMvtoEquipo().getCliente().getCodigo();
                    }
                    case "MvtoEquipo_Cliente_Nombre":{
                       return listado1.getMvtoEquipo().getCliente().getDescripcion();
                    }
                    case "MvtoEquipo_Art??culo_C??digo":{
                       return listado1.getMvtoEquipo().getArticulo().getCodigo();
                    }
                    case "MvtoEquipo_Art??culo_Nombre":{
                       return listado1.getMvtoEquipo().getArticulo().getDescripcion();
                    }
                    case "MvtoEquipo_Motonave_C??digo":{
                       return listado1.getMvtoEquipo().getMotonave().getCodigo();
                    }
                    case "MvtoEquipo_Motonave_Descripci??n":{
                       return listado1.getMvtoEquipo().getMotonave().getDescripcion();
                    }
                    case "MvtoEquipo_FechaInicio_Actividad":{
                       return listado1.getMvtoEquipo().getFechaHoraInicio();
                    }
                    case "MvtoEquipo_FechaFinalizaci??n_Actividad":{
                       return listado1.getMvtoEquipo().getFechaHoraFin();
                    }
                    case "MvtoEquipo_Duraci??n_Actividad(Minutos)":{
                       return listado1.getMvtoEquipo().getTotalMinutos();
                    }
                    case "MvtoEquipo_Valor_Hora":{
                       return listado1.getMvtoEquipo().getValorHora();
                    }
                    case "MvtoEquipo_Costo_Total":{
                       return listado1.getMvtoEquipo().getCostoTotal();
                    }
                    case "MvtoEquipo_Recobro":{
                       return listado1.getMvtoEquipo().getRecobro().getDescripcion();
                    }
                    case "MvtoEquipo_UsuarioQuienRegistra_C??digo":{
                       return listado1.getMvtoEquipo().getUsuarioQuieRegistra().getCodigo();
                    }
                    case "MvtoEquipo_UsuarioQuienRegistra_Nombre":{
                       return listado1.getMvtoEquipo().getUsuarioQuieRegistra().getNombres();
                    }
                    case "MvtoEquipo_UsuarioQuienAutoriza_C??digo":{
                       return listado1.getMvtoEquipo().getUsuarioAutorizaRecobro().getCodigo();
                    }
                    case "MvtoEquipo_UsuarioQuienAutoriza_Nombre":{
                       return listado1.getMvtoEquipo().getUsuarioAutorizaRecobro().getNombres();
                    }
                    case "MvtoEquipo_UsuarioCierraMvto_codigo":{
                       return listado1.getMvtoEquipo().getUsuarioQuienCierra().getCodigo();
                    }
                    case "MvtoEquipo_UsuarioCierraMvto_nombre":{
                       return listado1.getMvtoEquipo().getUsuarioQuienCierra().getNombres();
                    }
                    case "MvtoEquipo_Autorizaci??n_Recobro":{
                       return listado1.getMvtoEquipo().getAutorizacionRecobro().getDescripcion();
                    }
                    case "MvtoEquipo_Observaci??n_Autorizaci??n_Recobro":{
                       return listado1.getMvtoEquipo().getObservacionAutorizacion();
                    }
                    case "MvtoEquipo_Inactividad":{
                       return listado1.getMvtoEquipo().getInactividad();
                    }
                    case "MvtoEquipo_Causal_Inactividad":{
                       return listado1.getMvtoEquipo().getCausaInactividad().getDescripcion();
                    }
                    case "MvtoEquipo_UsuarioQuienInactiva_C??digo":{
                       return listado1.getMvtoEquipo().getUsuarioInactividad().getCodigo();
                    }
                    case "MvtoEquipo_UsuarioQuienInactiva_Nombre":{
                       return listado1.getMvtoEquipo().getUsuarioInactividad().getNombres();
                    }
                    case "MvtoEquipo_Motivo_Parada":{
                       return listado1.getMvtoEquipo().getMotivoParada().getDescripcion();
                    }
                    case "MvtoEquipo_MvtoEquipo_Observaci??n":{
                       return listado1.getMvtoEquipo().getObservacionMvtoEquipo();
                    }
                    case "MvtoEquipo_Estado":{
                       return listado1.getMvtoEquipo().getEstado();
                    }
                    case "MvtoEquipo_Desde_Carb??n":{
                       return listado1.getMvtoEquipo().getDesdeCarbon();
                    }
                    case "MvtoEquipo_Equipo_C??digo":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getEquipo().getCodigo();
                    }
                    case "MvtoEquipo_Equipo_Tipo":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion();
                    }
                    case "MvtoEquipo_Equipo_Marca":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getEquipo().getMarca();
                    }
                    case "MvtoEquipo_Equipo_Modelo":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getEquipo().getModelo();
                    }
                    case "MvtoEquipo_Equipo_Descripci??n":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getEquipo().getDescripcion();
                    }
                    case "MvtoEquipo_Equipo_Pertenencia":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getPertenencia().getDescripcion();
                    }
                    case "MvtoEquipo_Asignaci??n_C??digo":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getCodigo();
                    }
                    case "MvtoEquipo_Asignaci??n_FechaRegistro":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getFechaRegistro();
                    }
                    case "MvtoEquipo_Asignaci??n_FechaInicioActividad":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getFechaHoraInicio();
                    }
                    case "MvtoEquipo_Asignaci??n_FechaFinalizaci??nActividad":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getFechaHoraFin();
                    }
                    case "MvtoEquipo_Asignaci??n_CantidadMinutosProgramados":{
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getCantidadMinutosProgramados();
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
    private ProveedorDeDatosDePaginacion<MvtoCarbon_ListadoEquipos> crearProveedorDeDatos(final ArrayList<MvtoCarbon_ListadoEquipos> model) {
        //Obtenemos el listado de registros existentes haciendo una consulta a la base de datos.
        
        //Retornamos un interfaz de tipo ProveedorDeDatosDePaginacion en la cual sobreescribimos sus metodos abtractos
        //1 metodo: obtenemos el numero total de registros agregados al JTable.
        //2 metodo: obtenemos una subLista la cual ser?? mostrada en el JTable, seria nuestra pagina actual.
        return new ProveedorDeDatosDePaginacion<MvtoCarbon_ListadoEquipos>() {
            @Override
            public int getTotalRowCount() {
                return model.size();
            }

            @Override
            public List<MvtoCarbon_ListadoEquipos> getRows(int startIndex, int endIndex) {
                return model.subList(startIndex, endIndex);
            }
        };
    }
    public void validarSeleccionCampos(){
        encabezadoTabla= new ArrayList<>();
        if(selectDescargue_codigo.isSelected()){
            encabezadoTabla.add("C??digo");
        }
        if(selectDescargue_mes.isSelected()){
            encabezadoTabla.add("Mes");
        }
         if(selectDescargue_dia.isSelected()){
            encabezadoTabla.add("D??a");
        }
        if(selectDescargue_fechaRegistro.isSelected()){
            encabezadoTabla.add("Fecha_Registro");
        }
         if(selectDescargue_clienteCodigo.isSelected()){
            encabezadoTabla.add("Cliente_C??digo");
        }
        if(selectDescargue_clienteNombre.isSelected()){
            encabezadoTabla.add("Cliente_Nombre");
        }
        if(selectDescargue_centroOperacion.isSelected()){
            encabezadoTabla.add("Centro_Operacion");
        }
        if(selectDescargue_subcentroCosto.isSelected()){
            encabezadoTabla.add("Subcentro_Costo");
        }
        if(selectDescargue_centroCostoAuxiliarOrigen.isSelected()){
            encabezadoTabla.add("Centro_Costo_Auxiliar_Origen");
        }
        if(selectDescargue_centroCostoAuxiliarDestino.isSelected()){
            encabezadoTabla.add("Centro_Costo_Auxiliar_Destino");
        }
        if(selectDescargue_centroCosto.isSelected()){
            encabezadoTabla.add("Centro_Costo");
        }
        if(selectDescargue_centroCostoMayor.isSelected()){
            encabezadoTabla.add("Centro_Costo_Mayor");
        }
       
        if(selectDescargue_laborRealizada.isSelected()){
            encabezadoTabla.add("Labor_Realizada");
        }
        if(selectDescargue_articuloCodigo.isSelected()){
            encabezadoTabla.add("Art??culo_C??digo");
        }
        if(selectDescargue_articuloNombre.isSelected()){
            encabezadoTabla.add("Art??culo_Nombre");
        }
        if(selectDescargue_articuloTipo.isSelected()){
            encabezadoTabla.add("Art??culo_Tipo");
        }
        if(selectDescargue_articuloCodigoERP.isSelected()){
            encabezadoTabla.add("Art??culo_C??digo_ERP");
        }
        if(selectDescargue_articuloUnidadNegocio.isSelected()){
            encabezadoTabla.add("Art??culo_Unidad_Negocio");
        }     
        if(selectDescargue_transportadoraCodigo.isSelected()){
            encabezadoTabla.add("Transportadora_C??digo");
        }
        if(selectDescargue_transportadoraNit.isSelected()){
            encabezadoTabla.add("Transportadora_Nit");
        }
        if(selectDescargue_transportadoraNombre.isSelected()){
            encabezadoTabla.add("Transportadora_Nombre");
        }
        if(selectDescargue_lavadoVehiculo.isSelected()){
            encabezadoTabla.add("Lavado_Veh??culo");
        }
        if(selectDescargue_lavadoVehiculoObservacion.isSelected()){
            encabezadoTabla.add("Lavado_Veh??culo_Observaci??n");
        }     
        if(Motivo_No_LavadoVeh??culo.isSelected()){
            encabezadoTabla.add("Motivo_No_LavadoVeh??culo");
        }
        if(Equipo_Quien_Realiza_LavadoVeh??culo_C??digo.isSelected()){
            encabezadoTabla.add("Equipo_Quien_Realiza_LavadoVeh??culo_C??digo");
        }
        if(Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo.isSelected()){
            encabezadoTabla.add("Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo");
        }
        if(Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n.isSelected()){
            encabezadoTabla.add("Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n");
        }
        if(Costo_Lavado_Veh??culo.isSelected()){
            encabezadoTabla.add("Costo_Lavado_Veh??culo");
        }
        if(Valor_Recaudo_Empresa_LavadoVeh??culos.isSelected()){
            encabezadoTabla.add("Valor_Recaudo_Empresa_LavadoVeh??culos");
        }
        if(Valor_Recaudo_Equipo_LavadoVeh??culos.isSelected()){
            encabezadoTabla.add("Valor_Recaudo_Equipo_LavadoVeh??culos");
        }
        if(selectDescargue_numOrden.isSelected()){
            encabezadoTabla.add("N??mero_Orden");
        }
        if(selectDescargue_deposito.isSelected()){
            encabezadoTabla.add("Deposito");
        }
        if(selectDescargue_consecutivo.isSelected()){
            encabezadoTabla.add("Consecutivo");
        }
        if(selectDescargue_placa.isSelected()){
            encabezadoTabla.add("Placa");
        }
        if(selectAsignacion_equipoCodigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Equipo_C??digo");
        }
        if(selectAsignacion_equipoTipo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Equipo_Tipo");
        }
        if(selectAsignacion_equipoMarca.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Equipo_Marca");
        }
        if(selectAsignacion_equipoModelo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Equipo_Modelo");
        }
        if(selectAsignacion_equipoDescripcion.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Equipo_Descripci??n");
        }
        if(selectDescargue_pesoVacio.isSelected()){
            encabezadoTabla.add("PesoVacio");
        }
        if(selectDescargue_pesoLleno.isSelected()){
            encabezadoTabla.add("Peso_Lleno");
        }
        if(selectDescargue_pesoNeto.isSelected()){
            encabezadoTabla.add("Peso_Neto");
        }
        if(selectDescargue_fechaEntradaVehiculo.isSelected()){
            encabezadoTabla.add("Fecha_Entrada_Veh??culo");
        }
        if(selectDescargue_fechaSalidaVehiculo.isSelected()){
            encabezadoTabla.add("Fecha_Salida_Veh??culo");
        }
        if(selectDescargue_fechaInicioDescargue.isSelected()){
            encabezadoTabla.add("Fecha_Inicio_Descargue");
        }
        if(selectDescargue_fechaFinDescargue.isSelected()){
            encabezadoTabla.add("Fecha_Fin_Descargue");
        }
        if(selectDescargue_cantidadMinutosDescargue.isSelected()){
            encabezadoTabla.add("Cantidad_Minutos");
        }
        if(selectDescargue_usuarioQuienRegistraVehiculoCodigo.isSelected()){
            encabezadoTabla.add("Usuario_Quien_Registra_Veh??culo_C??digo");
        }
        if(selectDescargue_usuarioQuienRegistraVehiculoNombre.isSelected()){
            encabezadoTabla.add("Usuario_Quien_Registra_Veh??culo_Nombre");
        }
        if(selectDescargue_usuarioQuienRegistraVehiculoCorreo.isSelected()){
            encabezadoTabla.add("Usuario_Quien_Registra_Veh??culo_Correo");
        }
        if(selectDescargue_observaci??n.isSelected()){
            encabezadoTabla.add("Observaci??n");
        }
        if(selectDescargue_estado.isSelected()){
            encabezadoTabla.add("Estado");
        }
        if(selectDescargue_conexionPesoCcarga.isSelected()){
            encabezadoTabla.add("Conexi??n_Peso_CCARGA");
        }
        if(selectDescargue_registroManual.isSelected()){
            encabezadoTabla.add("Registro_Manual");
        }
        if(selectDescargue_usuarioRegistroManualCodigo.isSelected()){
            encabezadoTabla.add("Usuario_Quien_RegistraManual_C??digo");
        }
        if(selectDescargue_usuarioRegistroManualNombre.isSelected()){
            encabezadoTabla.add("Usuario_Quien_RegistraManual_Nombre");
        }
        if(selectDescargue_usuarioRegistroManualCorreo.isSelected()){
            encabezadoTabla.add("Usuario_Quien_RegistraManual_Correo");
        }
        if(selectDescargue_usuarioQuienCierraVehiculoCodigo.isSelected()){
            encabezadoTabla.add("Usuario_Quien_Cierra_Veh??culo_C??digo");
        }
        if(selectDescargue_usuarioQuienCierraVehiculoNombre.isSelected()){
            encabezadoTabla.add("Usuario_Quien_Cierra_Veh??culo_Nombre");
        }
        if(selectMvtoEquipo_codigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_C??digo");
        }
        if(selectMvtoEquipo_mes.isSelected()){
            encabezadoTabla.add("MvtoEquipo_MES");
        }
        if(selectMvtoEquipo_fechaRegistro.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Fecha_Registro");
        }
        if(selectMvtoEquipo_proveedorEquipoNit.isSelected()){
            encabezadoTabla.add("MvtoEquipo_ProveedorEquipo_NIT");
        }
        if(selectMvtoEquipo_proveedorEquipoNombre.isSelected()){
            encabezadoTabla.add("MvtoEquipo_ProveedorEquipo_Nombre");
        }
        if(selectMvtoEquipo_numeroOrden.isSelected()){
            encabezadoTabla.add("MvtoEquipo_N??mero_Orden");
        }
        if(selectMvtoEquipo_centroOperacion.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Centro_Operaci??n");
        }
        if(selectMvtoEquipo_subcentroCosto.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Subcentro_Costo");
        }
        if(selectMvtoEquipo_centroCostoAxiliarOrigen.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Centro_Costo_Auxiliar_Origen");
        }
        if(selectMvtoEquipo_centroCostoAuxiliarDestino.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Centro_Costo_Auxiliar_Destino");
        }
        if(selectMvtoEquipo_centroCosto.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Centro_Costo");
        }
        if(selectMvtoEquipo_centroCostoMayor.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Centro_Costo_Mayor");
        }
        if(selectMvtoEquipo_laborRealizada.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Labor_Realizada");
        }
        if(selectMvtoEquipo_clienteCodigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Cliente_C??digo");
        }
        if(selectMvtoEquipo_clienteNombre.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Cliente_Nombre");
        }
        if(selectMvtoEquipo_articuloCodigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Art??culo_C??digo");
        }
        if(selectMvtoEquipo_articuloDescripcion.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Art??culo_Nombre");
        }
        if(selectMvtoEquipo_motonaveCodigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Motonave_C??digo");
        }
        if(selectMvtoEquipo_motonaveDescripci??n.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Motonave_Descripci??n");
        }
        if(selectMvtoEquipo_fechaInicioDescargue.isSelected()){
            encabezadoTabla.add("MvtoEquipo_FechaInicio_Actividad");
        }
        if(selectMvtoEquipo_fechaFinDescargue.isSelected()){
            encabezadoTabla.add("MvtoEquipo_FechaFinalizaci??n_Actividad");
        }
        if(selectMvtoEquipo_cantidadMinutosDescargue.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Duraci??n_Actividad(Minutos)");
        }
        if(selectMvtoEquipo_ValorHoraEquipo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Valor_Hora");
        }
        if(selectMvtoEquipo_costoTotal.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Costo_Total");
        }
        if(selectMvtoEquipo_Recobro.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Recobro");
        }
        if(selectMvtoEquipo_UsuarioRegistraMvto_codigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioQuienRegistra_C??digo");
        }
        if(selectMvtoEquipo_UsuarioRegistraMvto_nombre.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioQuienRegistra_Nombre");
        }
        if(selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioQuienAutoriza_C??digo");
        }
        if(selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioQuienAutoriza_Nombre");
        }
        if(selectMvtoEquipo_UsuarioCierraMvto_codigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioCierraMvto_codigo");
        }
        if(selectMvtoEquipo_UsuarioCierraMvto_nombre.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioCierraMvto_nombre");
        }
        
        if(selectMvtoEquipo_autorizacionRecobro.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Autorizaci??n_Recobro");
        }
        if(selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Observaci??n_Autorizaci??n_Recobro");
        }
        if(selectMvtoEquipo_Inactividad.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Inactividad");
        }
        if(selectMvtoEquipo_CausalInactividad.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Causal_Inactividad");
        }
        if(selectMvtoEquipo_UsuarioInactividad_codigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioQuienInactiva_C??digo");
        }
        if(selectMvtoEquipo_UsuarioInactividad_nombre.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioQuienInactiva_Nombre");
        }
        if(selectMvtoEquipo_MotivoParada.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Motivo_Parada");
        }
        if(selectMvtoEquipo_observacion.isSelected()){
            encabezadoTabla.add("MvtoEquipo_MvtoEquipo_Observaci??n");
        }
        if(selectMvtoEquipo_estado.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Estado");
        }
        if(selectMvtoEquipo_DesdeCarbon.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Desde_Carb??n");
        }     
        if(selectAsignacion_equipoPertenencia.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Equipo_Pertenencia");
        }
        if(selectAsignacion_codigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Asignaci??n_C??digo");
        }
        if(selectAsignacion_fechaRegistro.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Asignaci??n_FechaRegistro");
        }
        if(selectAsignacion_fechaInicio.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Asignaci??n_FechaInicioActividad");
        }
        if(selectAsignacion_fechaFinalizacion.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Asignaci??n_FechaFinalizaci??nActividad");
        }
        if(selectAsignacion_cantidadMinutos_Programados.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Asignaci??n_CantidadMinutosProgramados");
        }    
    }
    public void validarSeleccionCamposLara(){
        encabezadoTabla= new ArrayList<>();
        if(selectDescargue_consecutivo.isSelected()){
            encabezadoTabla.add("Consecutivo");
        }
         if(selectDescargue_clienteNombre.isSelected()){
            encabezadoTabla.add("Cliente_Nombre");
        }
         if(selectDescargue_placa.isSelected()){
            encabezadoTabla.add("Placa");
        }
         if(selectDescargue_pesoVacio.isSelected()){
            encabezadoTabla.add("PesoVacio");
        }
        if(selectDescargue_pesoLleno.isSelected()){
            encabezadoTabla.add("Peso_Lleno");
        }
        if(selectDescargue_pesoNeto.isSelected()){
            encabezadoTabla.add("Peso_Neto");
        }
        if(selectDescargue_fechaEntradaVehiculo.isSelected()){
            encabezadoTabla.add("Fecha_Entrada_Veh??culo");
        }
        if(selectDescargue_fechaSalidaVehiculo.isSelected()){
            encabezadoTabla.add("Fecha_Salida_Veh??culo");
        }
        if(selectDescargue_deposito.isSelected()){
            encabezadoTabla.add("Deposito");
        }
        if(selectDescargue_articuloNombre.isSelected()){
            encabezadoTabla.add("Art??culo_Nombre");
        }
        if(selectDescargue_mes.isSelected()){
            encabezadoTabla.add("Mes");
        }
         if(selectDescargue_dia.isSelected()){
            encabezadoTabla.add("D??a");
        }
         if(selectDescargue_centroOperacion.isSelected()){
            encabezadoTabla.add("Centro_Operacion");
        }
        if(selectDescargue_subcentroCosto.isSelected()){
            encabezadoTabla.add("Subcentro_Costo");
        }
        if(selectDescargue_centroCostoAuxiliarOrigen.isSelected()){
            encabezadoTabla.add("Centro_Costo_Auxiliar_Origen");
        }
        if(selectDescargue_centroCostoAuxiliarDestino.isSelected()){
            encabezadoTabla.add("Centro_Costo_Auxiliar_Destino");
        }
        if(selectDescargue_laborRealizada.isSelected()){
            encabezadoTabla.add("Labor_Realizada");
        }
        
        if(Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n.isSelected()){
            encabezadoTabla.add("Equipo_Quien_Realiza_LavadoVeh??culo_Descripci??n");
        }
        /*if(Costo_Lavado_Veh??culo.isSelected()){
            encabezadoTabla.add("Costo_Lavado_Veh??culo");
        }*/
        if(Valor_Recaudo_Empresa_LavadoVeh??culos.isSelected()){
            encabezadoTabla.add("Valor_Recaudo_Empresa_LavadoVeh??culos");
        }
        /*if(Valor_Recaudo_Equipo_LavadoVeh??culos.isSelected()){
            encabezadoTabla.add("Valor_Recaudo_Equipo_LavadoVeh??culos");
        }*/
        if(selectDescargue_lavadoVehiculo.isSelected()){
            encabezadoTabla.add("Lavado_Veh??culo");
        }
        
        //######################################################################################################
        if(selectDescargue_codigo.isSelected()){
            encabezadoTabla.add("C??digo");
        }
        
        if(selectDescargue_fechaRegistro.isSelected()){
            encabezadoTabla.add("Fecha_Registro");
        }
         if(selectDescargue_clienteCodigo.isSelected()){
            encabezadoTabla.add("Cliente_C??digo");
        }
       
       
        if(selectDescargue_centroCosto.isSelected()){
            encabezadoTabla.add("Centro_Costo");
        }
        if(selectDescargue_centroCostoMayor.isSelected()){
            encabezadoTabla.add("Centro_Costo_Mayor");
        }
       
        
        if(selectDescargue_articuloCodigo.isSelected()){
            encabezadoTabla.add("Art??culo_C??digo");
        }
        
        if(selectDescargue_articuloTipo.isSelected()){
            encabezadoTabla.add("Art??culo_Tipo");
        }
        if(selectDescargue_articuloCodigoERP.isSelected()){
            encabezadoTabla.add("Art??culo_C??digo_ERP");
        }
        if(selectDescargue_articuloUnidadNegocio.isSelected()){
            encabezadoTabla.add("Art??culo_Unidad_Negocio");
        }     
        if(selectDescargue_transportadoraCodigo.isSelected()){
            encabezadoTabla.add("Transportadora_C??digo");
        }
        if(selectDescargue_transportadoraNit.isSelected()){
            encabezadoTabla.add("Transportadora_Nit");
        }
        if(selectDescargue_transportadoraNombre.isSelected()){
            encabezadoTabla.add("Transportadora_Nombre");
        }
        
        if(selectDescargue_lavadoVehiculoObservacion.isSelected()){
            encabezadoTabla.add("Lavado_Veh??culo_Observaci??n");
        }     
        if(Motivo_No_LavadoVeh??culo.isSelected()){
            encabezadoTabla.add("Motivo_No_LavadoVeh??culo");
        }
        if(Equipo_Quien_Realiza_LavadoVeh??culo_C??digo.isSelected()){
            encabezadoTabla.add("Equipo_Quien_Realiza_LavadoVeh??culo_C??digo");
        }
        if(Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo.isSelected()){
            encabezadoTabla.add("Equipo_Quien_Realiza_LavadoVeh??culo_TipoEquipo");
        }
        
        if(selectDescargue_numOrden.isSelected()){
            encabezadoTabla.add("N??mero_Orden");
        }
        
        
       
        if(selectAsignacion_equipoCodigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Equipo_C??digo");
        }
        if(selectAsignacion_equipoTipo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Equipo_Tipo");
        }
        if(selectAsignacion_equipoMarca.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Equipo_Marca");
        }
        if(selectAsignacion_equipoModelo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Equipo_Modelo");
        }
        if(selectAsignacion_equipoDescripcion.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Equipo_Descripci??n");
        }
        
        
        if(selectDescargue_fechaInicioDescargue.isSelected()){
            encabezadoTabla.add("Fecha_Inicio_Descargue");
        }
        if(selectDescargue_fechaFinDescargue.isSelected()){
            encabezadoTabla.add("Fecha_Fin_Descargue");
        }
        if(selectDescargue_cantidadMinutosDescargue.isSelected()){
            encabezadoTabla.add("Cantidad_Minutos");
        }
        if(selectDescargue_usuarioQuienRegistraVehiculoCodigo.isSelected()){
            encabezadoTabla.add("Usuario_Quien_Registra_Veh??culo_C??digo");
        }
        if(selectDescargue_usuarioQuienRegistraVehiculoNombre.isSelected()){
            encabezadoTabla.add("Usuario_Quien_Registra_Veh??culo_Nombre");
        }
        if(selectDescargue_usuarioQuienRegistraVehiculoCorreo.isSelected()){
            encabezadoTabla.add("Usuario_Quien_Registra_Veh??culo_Correo");
        }
        if(selectDescargue_observaci??n.isSelected()){
            encabezadoTabla.add("Observaci??n");
        }
        if(selectDescargue_estado.isSelected()){
            encabezadoTabla.add("Estado");
        }
        if(selectDescargue_conexionPesoCcarga.isSelected()){
            encabezadoTabla.add("Conexi??n_Peso_CCARGA");
        }
        if(selectDescargue_registroManual.isSelected()){
            encabezadoTabla.add("Registro_Manual");
        }
        if(selectDescargue_usuarioRegistroManualCodigo.isSelected()){
            encabezadoTabla.add("Usuario_Quien_RegistraManual_C??digo");
        }
        if(selectDescargue_usuarioRegistroManualNombre.isSelected()){
            encabezadoTabla.add("Usuario_Quien_RegistraManual_Nombre");
        }
        if(selectDescargue_usuarioRegistroManualCorreo.isSelected()){
            encabezadoTabla.add("Usuario_Quien_RegistraManual_Correo");
        }
        if(selectDescargue_usuarioQuienCierraVehiculoCodigo.isSelected()){
            encabezadoTabla.add("Usuario_Quien_Cierra_Veh??culo_C??digo");
        }
        if(selectDescargue_usuarioQuienCierraVehiculoNombre.isSelected()){
            encabezadoTabla.add("Usuario_Quien_Cierra_Veh??culo_Nombre");
        }
        if(selectMvtoEquipo_codigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_C??digo");
        }
        if(selectMvtoEquipo_mes.isSelected()){
            encabezadoTabla.add("MvtoEquipo_MES");
        }
        if(selectMvtoEquipo_fechaRegistro.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Fecha_Registro");
        }
        if(selectMvtoEquipo_proveedorEquipoNit.isSelected()){
            encabezadoTabla.add("MvtoEquipo_ProveedorEquipo_NIT");
        }
        if(selectMvtoEquipo_proveedorEquipoNombre.isSelected()){
            encabezadoTabla.add("MvtoEquipo_ProveedorEquipo_Nombre");
        }
        if(selectMvtoEquipo_numeroOrden.isSelected()){
            encabezadoTabla.add("MvtoEquipo_N??mero_Orden");
        }
        if(selectMvtoEquipo_centroOperacion.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Centro_Operaci??n");
        }
        if(selectMvtoEquipo_subcentroCosto.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Subcentro_Costo");
        }
        if(selectMvtoEquipo_centroCostoAxiliarOrigen.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Centro_Costo_Auxiliar_Origen");
        }
        if(selectMvtoEquipo_centroCostoAuxiliarDestino.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Centro_Costo_Auxiliar_Destino");
        }
        if(selectMvtoEquipo_centroCosto.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Centro_Costo");
        }
        if(selectMvtoEquipo_centroCostoMayor.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Centro_Costo_Mayor");
        }
        if(selectMvtoEquipo_laborRealizada.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Labor_Realizada");
        }
        if(selectMvtoEquipo_clienteCodigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Cliente_C??digo");
        }
        if(selectMvtoEquipo_clienteNombre.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Cliente_Nombre");
        }
        if(selectMvtoEquipo_articuloCodigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Art??culo_C??digo");
        }
        if(selectMvtoEquipo_articuloDescripcion.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Art??culo_Nombre");
        }
        if(selectMvtoEquipo_motonaveCodigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Motonave_C??digo");
        }
        if(selectMvtoEquipo_motonaveDescripci??n.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Motonave_Descripci??n");
        }
        if(selectMvtoEquipo_fechaInicioDescargue.isSelected()){
            encabezadoTabla.add("MvtoEquipo_FechaInicio_Actividad");
        }
        if(selectMvtoEquipo_fechaFinDescargue.isSelected()){
            encabezadoTabla.add("MvtoEquipo_FechaFinalizaci??n_Actividad");
        }
        if(selectMvtoEquipo_cantidadMinutosDescargue.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Duraci??n_Actividad(Minutos)");
        }
        if(selectMvtoEquipo_ValorHoraEquipo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Valor_Hora");
        }
        if(selectMvtoEquipo_costoTotal.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Costo_Total");
        }
        if(selectMvtoEquipo_Recobro.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Recobro");
        }
        if(selectMvtoEquipo_UsuarioRegistraMvto_codigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioQuienRegistra_C??digo");
        }
        if(selectMvtoEquipo_UsuarioRegistraMvto_nombre.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioQuienRegistra_Nombre");
        }
        if(selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioQuienAutoriza_C??digo");
        }
        if(selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioQuienAutoriza_Nombre");
        }
        if(selectMvtoEquipo_UsuarioCierraMvto_codigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioCierraMvto_codigo");
        }
        if(selectMvtoEquipo_UsuarioCierraMvto_nombre.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioCierraMvto_nombre");
        }
        if(selectMvtoEquipo_autorizacionRecobro.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Autorizaci??n_Recobro");
        }
        if(selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Observaci??n_Autorizaci??n_Recobro");
        }
        if(selectMvtoEquipo_Inactividad.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Inactividad");
        }
        if(selectMvtoEquipo_CausalInactividad.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Causal_Inactividad");
        }
        if(selectMvtoEquipo_UsuarioInactividad_codigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioQuienInactiva_C??digo");
        }
        if(selectMvtoEquipo_UsuarioInactividad_nombre.isSelected()){
            encabezadoTabla.add("MvtoEquipo_UsuarioQuienInactiva_Nombre");
        }
        if(selectMvtoEquipo_MotivoParada.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Motivo_Parada");
        }
        if(selectMvtoEquipo_observacion.isSelected()){
            encabezadoTabla.add("MvtoEquipo_MvtoEquipo_Observaci??n");
        }
        if(selectMvtoEquipo_estado.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Estado");
        }
        if(selectMvtoEquipo_DesdeCarbon.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Desde_Carb??n");
        }     
        if(selectAsignacion_equipoPertenencia.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Equipo_Pertenencia");
        }
        if(selectAsignacion_codigo.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Asignaci??n_C??digo");
        }
        if(selectAsignacion_fechaRegistro.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Asignaci??n_FechaRegistro");
        }
        if(selectAsignacion_fechaInicio.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Asignaci??n_FechaInicioActividad");
        }
        if(selectAsignacion_fechaFinalizacion.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Asignaci??n_FechaFinalizaci??nActividad");
        }
        if(selectAsignacion_cantidadMinutos_Programados.isSelected()){
            encabezadoTabla.add("MvtoEquipo_Asignaci??n_CantidadMinutosProgramados");
        }    
    }
    public String getMes(String mes){
        switch(mes){
            case "January":{
                mes="Enero";
                break;
            }
            case "February":{
                mes="Febrero";
                break;
            }
            case "March":{
                mes="Marzo";
                break;
            }
            case "April":{
                mes="Abril";
                break;
            }
            case "May":{
                mes="Marzo";
                break;
            }
            case "June":{
                mes="Junio";
                break;
            }
            case "July":{
                mes="Julio";
                break;
            }
            case "August":{
                mes="Agosto";
                break;
            }
            case "September":{
                mes="Septiembre";
                break;
            }
            case "October":{
                mes="Octubre";
                break;
            }
            case "November":{
                mes="Noviembre";
                break;
            }
            case "December":{
                mes="Diciembre";
                break;
            }           
        }
        return mes;
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
