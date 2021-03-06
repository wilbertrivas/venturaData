package ModuloCarbon.View;
  
import ModuloCarbon.Controller2.ControlDB_MvtoCarbon;
import ModuloCarbon.Model.MvtoCarbon_ListadoEquipos;
import ModuloEquipo.View.MvtoEquipo_Procesar_Programado;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import utilities.ModeloDeTabla;
import utilities.PaginadorDeTabla;
import utilities.ProveedorDeDatosDePaginacion;
 
public final class MvtoCarbon_ProcesarProgramado extends javax.swing.JPanel implements ActionListener, TableModelListener{
    ArrayList<MvtoCarbon_ListadoEquipos> listado=null;
    private Usuario user;
    private String tipoConexion;
    Iniciar Iniciar1 = new Iniciar();
    //HiloCronometro iniciarCronometro;
    private PaginadorDeTabla<MvtoCarbon_ListadoEquipos> paginadorDeTabla;  
    ProveedorDeDatosDePaginacion<MvtoCarbon_ListadoEquipos> proveedorDeDatos;
    ArrayList<String> encabezadoTabla=null;
    public MvtoCarbon_ProcesarProgramado(Usuario user, String tipoConexion) {
        initComponents();
        this.user= user;
        this.tipoConexion= tipoConexion;
        InternalFrameSelectorCampos.getContentPane().setBackground(Color.WHITE);
        InternalFrameSelectorCampos.show(false);
        //iniciarCronometro= new HiloCronometro();
        for(int i=1; i<=365; i++){
            time.addItem(""+i);
            rango.addItem(""+i);
        }
        iniciar.setEnabled(true);
        parar.setEnabled(false);
        selectorCampoPorDefecto();
        Dimension dim=super.getToolkit().getScreenSize();      
        selectorCampoPorDefecto();
        encabezadoTabla= new ArrayList<String>();
        pageJComboBox.show(false);
        jProgressBar1.show(true);
        //iniciarCronometro.start();
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
        selectMvtoEquipo_clienteCodigo1 = new javax.swing.JRadioButton();
        selectAsignacion_equipoCodigo = new javax.swing.JRadioButton();
        selectAsignacion_equipoMarca = new javax.swing.JRadioButton();
        selectAsignacion_equipoModelo = new javax.swing.JRadioButton();
        selectMvtoEquipo_fechaRegistro = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioRegistraMvto_nombre = new javax.swing.JRadioButton();
        selectMvtoEquipo_cantidadMinutosDescargue = new javax.swing.JRadioButton();
        selectMvtoEquipo_numeroOrden = new javax.swing.JRadioButton();
        selectAsignacion_equipoTipo = new javax.swing.JRadioButton();
        selectMvtoEquipo_clienteNombre1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_motonaveDescripci??n1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroOperacion1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_subcentroCosto1 = new javax.swing.JRadioButton();
        selectAsignacion_fechaInicio = new javax.swing.JRadioButton();
        selectAsignacion_fechaFinalizacion = new javax.swing.JRadioButton();
        selectAsignacion_cantidadMinutos_Programados = new javax.swing.JRadioButton();
        selectAsignacion_equipoPertenencia = new javax.swing.JRadioButton();
        selectMvtoEquipo_proveedorEquipoNit1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_proveedorEquipoNombre1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_laborRealizada1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_fechaInicioDescargue = new javax.swing.JRadioButton();
        selectMvtoEquipo_fechaFinDescargue1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_ValorHoraEquipo = new javax.swing.JRadioButton();
        selectMvtoEquipo_costoTotal = new javax.swing.JRadioButton();
        selectMvtoEquipo_Recobro = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioRegistraMvto_codigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_CausalInactividad = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre = new javax.swing.JRadioButton();
        selectMvtoEquipo_autorizacionRecobro = new javax.swing.JRadioButton();
        selectMvtoEquipo_MotivoParada1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion = new javax.swing.JRadioButton();
        selectMvtoEquipo_Inactividad = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioInactividad_codigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_DesdeCarbon = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioInactividad_nombre = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroCostoMayor1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_observacion = new javax.swing.JRadioButton();
        selectMvtoEquipo_estado = new javax.swing.JRadioButton();
        selectAsignacion_equipoDescripcion = new javax.swing.JRadioButton();
        selectAsignacion_codigo1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroCosto1 = new javax.swing.JRadioButton();
        selectAsignacion_fechaRegistro1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_articuloCodigo1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_articuloDescripcion1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_motonaveCodigo1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroCostoAxiliarOrigen1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroCostoAuxiliarDestino1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_mes = new javax.swing.JRadioButton();
        selectMvtoEquipo_codigo1 = new javax.swing.JRadioButton();
        selectDescargue_usuarioQuienRegistraVehiculoCorreo = new javax.swing.JRadioButton();
        titulo12 = new javax.swing.JLabel();
        titulo21 = new javax.swing.JLabel();
        titulo27 = new javax.swing.JLabel();
        titulo28 = new javax.swing.JLabel();
        titulo29 = new javax.swing.JLabel();
        titulo30 = new javax.swing.JLabel();
        titulo31 = new javax.swing.JLabel();
        titulo32 = new javax.swing.JLabel();
        titulo33 = new javax.swing.JLabel();
        selectDescargue_centroCostoAuxiliarDestino = new javax.swing.JRadioButton();
        selectDescargue_subcentroCosto = new javax.swing.JRadioButton();
        titulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        alerta_fechaFinal = new javax.swing.JLabel();
        alerta_fechaInicio = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        InternalFrameSelectorCampos1 = new javax.swing.JInternalFrame();
        jButton4 = new javax.swing.JButton();
        selectAll1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_clienteCodigo = new javax.swing.JRadioButton();
        titulo9 = new javax.swing.JLabel();
        titulo17 = new javax.swing.JLabel();
        jSeparator22 = new javax.swing.JSeparator();
        jButton5 = new javax.swing.JButton();
        selectAsignacion_equipoCodigo1 = new javax.swing.JRadioButton();
        selectAsignacion_equipoMarca1 = new javax.swing.JRadioButton();
        selectAsignacion_equipoModelo1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_fechaRegistro1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioRegistraMvto_nombre1 = new javax.swing.JRadioButton();
        titulo23 = new javax.swing.JLabel();
        selectMvtoEquipo_cantidadMinutosDescargue1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_numeroOrden1 = new javax.swing.JRadioButton();
        selectAsignacion_equipoTipo1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_clienteNombre = new javax.swing.JRadioButton();
        selectMvtoEquipo_motonaveDescripci??n = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroOperacion = new javax.swing.JRadioButton();
        selectMvtoEquipo_subcentroCosto = new javax.swing.JRadioButton();
        selectAsignacion_fechaInicio1 = new javax.swing.JRadioButton();
        selectAsignacion_fechaFinalizacion1 = new javax.swing.JRadioButton();
        selectAsignacion_cantidadMinutos_Programados1 = new javax.swing.JRadioButton();
        selectAsignacion_equipoPertenencia1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_proveedorEquipoNit = new javax.swing.JRadioButton();
        selectMvtoEquipo_proveedorEquipoNombre = new javax.swing.JRadioButton();
        selectMvtoEquipo_laborRealizada = new javax.swing.JRadioButton();
        selectMvtoEquipo_fechaInicioDescargue1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_fechaFinDescargue = new javax.swing.JRadioButton();
        selectMvtoEquipo_ValorHoraEquipo1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_costoTotal1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_Recobro1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioRegistraMvto_codigo1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_CausalInactividad1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_autorizacionRecobro1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_MotivoParada = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_Inactividad1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioInactividad_codigo1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_DesdeCarbon1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioInactividad_nombre1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroCostoMayor = new javax.swing.JRadioButton();
        selectMvtoEquipo_observacion1 = new javax.swing.JRadioButton();
        selectMvtoEquipo_estado1 = new javax.swing.JRadioButton();
        selectAsignacion_equipoDescripcion1 = new javax.swing.JRadioButton();
        titulo19 = new javax.swing.JLabel();
        selectAsignacion_codigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroCosto = new javax.swing.JRadioButton();
        selectAsignacion_fechaRegistro = new javax.swing.JRadioButton();
        selectMvtoEquipo_articuloCodigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_articuloDescripcion = new javax.swing.JRadioButton();
        selectMvtoEquipo_motonaveCodigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroCostoAxiliarOrigen = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroCostoAuxiliarDestino = new javax.swing.JRadioButton();
        selectMvtoEquipo_codigo = new javax.swing.JRadioButton();
        titulo20 = new javax.swing.JLabel();
        titulo24 = new javax.swing.JLabel();
        titulo25 = new javax.swing.JLabel();
        titulo26 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        time = new javax.swing.JComboBox<>();
        iniciar = new javax.swing.JButton();
        parar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        rango = new javax.swing.JComboBox<>();
        modalidad = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        modalidadHilo = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        paginationPanel = new javax.swing.JPanel();
        pageJComboBox = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InternalFrameSelectorCampos.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrameSelectorCampos.setClosable(true);
        InternalFrameSelectorCampos.setVisible(false);
        InternalFrameSelectorCampos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCamposAplicarConfig.png"))); // NOI18N
        jButton2.setText("Aplicar Configuraci??n");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 510, 230, 30));

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
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 190, -1));

        selectDescargue_fechaRegistro.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_fechaRegistro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_fechaRegistro.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_fechaRegistro.setText("Fecha_Registro");
        selectDescargue_fechaRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_fechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, 190, -1));

        selectDescargue_deposito.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_deposito.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_deposito.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_deposito.setText("Deposito");
        selectDescargue_deposito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_deposito, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 190, -1));

        selectDescargue_consecutivo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_consecutivo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_consecutivo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_consecutivo.setText("Consecutivo");
        selectDescargue_consecutivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_consecutivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 190, -1));

        selectDescargue_placa.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_placa.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_placa.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_placa.setText("Placa");
        selectDescargue_placa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_placa, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 190, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_pesoVacio, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 190, -1));

        selectDescargue_pesoLleno.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_pesoLleno.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_pesoLleno.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_pesoLleno.setText("Peso_Lleno");
        selectDescargue_pesoLleno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_pesoLleno, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 190, -1));

        selectDescargue_pesoNeto.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_pesoNeto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_pesoNeto.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_pesoNeto.setText("Peso_Neto");
        selectDescargue_pesoNeto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_pesoNeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 190, -1));

        selectDescargue_numOrden.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_numOrden.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_numOrden.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_numOrden.setText("N??mero Orden");
        selectDescargue_numOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_numOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, 190, -1));

        selectDescargue_fechaEntradaVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_fechaEntradaVehiculo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_fechaEntradaVehiculo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_fechaEntradaVehiculo.setText("Fecha_Entrada_Vehiculo");
        selectDescargue_fechaEntradaVehiculo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_fechaEntradaVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 190, -1));

        selectDescargue_registroManual.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_registroManual.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_registroManual.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_registroManual.setText("RegistroManual");
        selectDescargue_registroManual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_registroManual, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, 190, -1));

        selectDescargue_clienteCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_clienteCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_clienteCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_clienteCodigo.setText("Cliente_C??digo");
        selectDescargue_clienteCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_clienteCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 190, -1));

        selectDescargue_transportadoraNit.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_transportadoraNit.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_transportadoraNit.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_transportadoraNit.setText("Transportadora_NIT");
        selectDescargue_transportadoraNit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_transportadoraNit, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 190, -1));

        selectDescargue_transportadoraCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_transportadoraCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_transportadoraCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_transportadoraCodigo.setText("Transportadora_C??digo");
        selectDescargue_transportadoraCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_transportadoraCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 190, -1));

        selectDescargue_usuarioRegistroManualCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_usuarioRegistroManualCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_usuarioRegistroManualCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_usuarioRegistroManualCodigo.setText("UsuarioRegistr??Manual_C??digo");
        selectDescargue_usuarioRegistroManualCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioRegistroManualCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 440, 190, -1));

        selectDescargue_usuarioRegistroManualNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_usuarioRegistroManualNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_usuarioRegistroManualNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_usuarioRegistroManualNombre.setText("UsuarioRegistr??Manual_Nombre");
        selectDescargue_usuarioRegistroManualNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioRegistroManualNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 460, 190, -1));

        selectDescargue_fechaSalidaVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_fechaSalidaVehiculo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_fechaSalidaVehiculo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_fechaSalidaVehiculo.setText("Fecha_Salida_Vehiculo");
        selectDescargue_fechaSalidaVehiculo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_fechaSalidaVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 190, -1));

        selectDescargue_fechaInicioDescargue.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_fechaInicioDescargue.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_fechaInicioDescargue.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_fechaInicioDescargue.setText("Fecha_Inicio_Descargue");
        selectDescargue_fechaInicioDescargue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_fechaInicioDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 190, -1));

        selectDescargue_cantidadMinutosDescargue.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_cantidadMinutosDescargue.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_cantidadMinutosDescargue.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_cantidadMinutosDescargue.setText("Cantidad_Minutos_Descargue");
        selectDescargue_cantidadMinutosDescargue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_cantidadMinutosDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 190, -1));

        titulo2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo2.setText("SELECTOR DE CAMPOS");
        titulo2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1200, 40));

        titulo13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo13.setText("DESCARGUE DE CARBON");
        titulo13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 480, 20));

        jSeparator21.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternalFrameSelectorCampos.getContentPane().add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 0, 430));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCamposDefauls.png"))); // NOI18N
        jButton3.setText("Campos Predeterminados");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 510, 230, 30));

        selectDescargue_mes.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_mes.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_mes.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_mes.setText("Mes");
        selectDescargue_mes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, 190, -1));

        selectDescargue_clienteNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_clienteNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_clienteNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_clienteNombre.setText("Cliente_Nombre");
        selectDescargue_clienteNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_clienteNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 190, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_articuloCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 190, -1));

        selectDescargue_centroOperacion.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_centroOperacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_centroOperacion.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_centroOperacion.setText("Centro Operaci??n");
        selectDescargue_centroOperacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 190, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_centroCostoMayor, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 190, -1));

        selectDescargue_observaci??n.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_observaci??n.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_observaci??n.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_observaci??n.setText("Observaci??n");
        selectDescargue_observaci??n.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_observaci??n, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 190, -1));

        selectDescargue_estado.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_estado.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_estado.setText("Estado");
        selectDescargue_estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 190, -1));

        selectDescargue_conexionPesoCcarga.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_conexionPesoCcarga.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_conexionPesoCcarga.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_conexionPesoCcarga.setText("Conexion_Peso_Ccarga");
        selectDescargue_conexionPesoCcarga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_conexionPesoCcarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 400, 190, -1));

        selectDescargue_fechaFinDescargue.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_fechaFinDescargue.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_fechaFinDescargue.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_fechaFinDescargue.setText("Fecha_Final_Descargue");
        selectDescargue_fechaFinDescargue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_fechaFinDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 190, -1));

        selectDescargue_centroCostoAuxiliarOrigen.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_centroCostoAuxiliarOrigen.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_centroCostoAuxiliarOrigen.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_centroCostoAuxiliarOrigen.setText("Centro Costo Auxiliar_Origen");
        selectDescargue_centroCostoAuxiliarOrigen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_centroCostoAuxiliarOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 190, -1));

        selectDescargue_centroCosto.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_centroCosto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_centroCosto.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_centroCosto.setText("Centro Costo");
        selectDescargue_centroCosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_centroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 190, -1));

        selectDescargue_articuloUnidadNegocio.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_articuloUnidadNegocio.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_articuloUnidadNegocio.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_articuloUnidadNegocio.setText("Art??culo_Unidad_Negocio");
        selectDescargue_articuloUnidadNegocio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_articuloUnidadNegocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 190, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_laborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 190, -1));

        selectDescargue_articuloNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_articuloNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_articuloNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_articuloNombre.setText("Art??culo_Nombre");
        selectDescargue_articuloNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_articuloNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 190, -1));

        selectDescargue_articuloCodigoERP.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_articuloCodigoERP.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_articuloCodigoERP.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_articuloCodigoERP.setText("Art??culo_C??digo_ERP");
        selectDescargue_articuloCodigoERP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_articuloCodigoERP, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 190, -1));

        selectDescargue_articuloTipo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_articuloTipo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_articuloTipo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_articuloTipo.setText("Art??culo_Tipo");
        selectDescargue_articuloTipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_articuloTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 190, -1));

        selectDescargue_transportadoraNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_transportadoraNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_transportadoraNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_transportadoraNombre.setText("Transportadora_Nombre");
        selectDescargue_transportadoraNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_transportadoraNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, 190, -1));

        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setText("UsuarioQuienRegistr??Veh??culo_C??digo");
        selectDescargue_usuarioQuienRegistraVehiculoCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioQuienRegistraVehiculoCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 210, -1));

        selectDescargue_usuarioQuienRegistraVehiculoNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_usuarioQuienRegistraVehiculoNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_usuarioQuienRegistraVehiculoNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_usuarioQuienRegistraVehiculoNombre.setText("UsuarioQuienRegistr??Veh??culo_Nombre");
        selectDescargue_usuarioQuienRegistraVehiculoNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioQuienRegistraVehiculoNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, 220, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioRegistroManualCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 190, -1));

        selectMvtoEquipo_clienteCodigo1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_clienteCodigo1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_clienteCodigo1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_clienteCodigo1.setText("Cliente C??digo");
        selectMvtoEquipo_clienteCodigo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_clienteCodigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 80, 200, -1));

        selectAsignacion_equipoCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoCodigo.setText("Equipo C??digo");
        selectAsignacion_equipoCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 190, -1));

        selectAsignacion_equipoMarca.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoMarca.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoMarca.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoMarca.setText("Equipo Marca");
        selectAsignacion_equipoMarca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 190, -1));

        selectAsignacion_equipoModelo.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoModelo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoModelo.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoModelo.setText("Equipo Modelo");
        selectAsignacion_equipoModelo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, 190, -1));

        selectMvtoEquipo_fechaRegistro.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_fechaRegistro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_fechaRegistro.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_fechaRegistro.setText("Fecha_Registro");
        selectMvtoEquipo_fechaRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_fechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 280, 190, -1));

        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setText("Nombre_Usuario_Registra_MvtoEquipo");
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioRegistraMvto_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 340, 220, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_cantidadMinutosDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 240, 190, -1));

        selectMvtoEquipo_numeroOrden.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_numeroOrden.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_numeroOrden.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_numeroOrden.setText("N??mero Orden");
        selectMvtoEquipo_numeroOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_numeroOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 340, 190, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, 190, -1));

        selectMvtoEquipo_clienteNombre1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_clienteNombre1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_clienteNombre1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_clienteNombre1.setText("Cliente Nombre");
        selectMvtoEquipo_clienteNombre1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_clienteNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 100, 200, -1));

        selectMvtoEquipo_motonaveDescripci??n1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_motonaveDescripci??n1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_motonaveDescripci??n1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_motonaveDescripci??n1.setText("Motonave Descripci??n");
        selectMvtoEquipo_motonaveDescripci??n1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_motonaveDescripci??n1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_motonaveDescripci??n1ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_motonaveDescripci??n1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 180, 200, -1));

        selectMvtoEquipo_centroOperacion1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroOperacion1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroOperacion1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroOperacion1.setText("Centro Operaci??n");
        selectMvtoEquipo_centroOperacion1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroOperacion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 360, 200, -1));

        selectMvtoEquipo_subcentroCosto1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_subcentroCosto1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_subcentroCosto1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_subcentroCosto1.setText("Subcentro Costo");
        selectMvtoEquipo_subcentroCosto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_subcentroCosto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_subcentroCosto1ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_subcentroCosto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 380, 200, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 270, 190, -1));

        selectAsignacion_fechaFinalizacion.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_fechaFinalizacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_fechaFinalizacion.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_fechaFinalizacion.setText("Fecha_Finalizaci??n");
        selectAsignacion_fechaFinalizacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_fechaFinalizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 290, 190, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_cantidadMinutos_Programados, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 310, 190, -1));

        selectAsignacion_equipoPertenencia.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoPertenencia.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoPertenencia.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoPertenencia.setText("Equipo Pertenencia");
        selectAsignacion_equipoPertenencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoPertenencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, 190, -1));

        selectMvtoEquipo_proveedorEquipoNit1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_proveedorEquipoNit1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_proveedorEquipoNit1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_proveedorEquipoNit1.setText("Proveedor Equipo NIT");
        selectMvtoEquipo_proveedorEquipoNit1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_proveedorEquipoNit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, 190, -1));

        selectMvtoEquipo_proveedorEquipoNombre1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_proveedorEquipoNombre1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_proveedorEquipoNombre1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_proveedorEquipoNombre1.setText("Proveedor Equipo Nombre");
        selectMvtoEquipo_proveedorEquipoNombre1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_proveedorEquipoNombre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_proveedorEquipoNombre1ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_proveedorEquipoNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 320, 190, -1));

        selectMvtoEquipo_laborRealizada1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_laborRealizada1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_laborRealizada1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_laborRealizada1.setText("Labor Realizada");
        selectMvtoEquipo_laborRealizada1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_laborRealizada1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_laborRealizada1ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_laborRealizada1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 60, 190, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_fechaInicioDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 200, 190, -1));

        selectMvtoEquipo_fechaFinDescargue1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_fechaFinDescargue1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_fechaFinDescargue1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_fechaFinDescargue1.setText("Fecha Final_Actividad");
        selectMvtoEquipo_fechaFinDescargue1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_fechaFinDescargue1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_fechaFinDescargue1ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_fechaFinDescargue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 220, 190, -1));

        selectMvtoEquipo_ValorHoraEquipo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_ValorHoraEquipo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_ValorHoraEquipo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_ValorHoraEquipo.setText("Valor_Hora_Equipo");
        selectMvtoEquipo_ValorHoraEquipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_ValorHoraEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 260, 190, -1));

        selectMvtoEquipo_costoTotal.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_costoTotal.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_costoTotal.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_costoTotal.setText("Costo_Total");
        selectMvtoEquipo_costoTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_costoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 280, 190, -1));

        selectMvtoEquipo_Recobro.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_Recobro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_Recobro.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_Recobro.setText("Recobro");
        selectMvtoEquipo_Recobro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_Recobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 300, 190, -1));

        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setText("C??digo_Usuario_Registra_MvtoEquipo");
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioRegistraMvto_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 320, 210, -1));

        selectMvtoEquipo_CausalInactividad.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_CausalInactividad.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_CausalInactividad.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_CausalInactividad.setText("Causal_Inactividad");
        selectMvtoEquipo_CausalInactividad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_CausalInactividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 60, 200, -1));

        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setText("C??digo_Usuario_Autoriza_Recobro");
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioAutorizaRecobro_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 360, 210, -1));

        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setText("Nombre_Usuario_Autoriza_Recobro");
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioAutorizaRecobro_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 380, 220, -1));

        selectMvtoEquipo_autorizacionRecobro.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_autorizacionRecobro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_autorizacionRecobro.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_autorizacionRecobro.setText("Autorizaci??n_Recobro");
        selectMvtoEquipo_autorizacionRecobro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_autorizacionRecobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 400, 220, -1));

        selectMvtoEquipo_MotivoParada1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_MotivoParada1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_MotivoParada1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_MotivoParada1.setText("Motivo_Parada");
        selectMvtoEquipo_MotivoParada1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_MotivoParada1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 120, 200, -1));

        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setText("Observaci??n_Autorizaci??n_Recobro");
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioAutorizaRecobro_observacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 420, 220, -1));

        selectMvtoEquipo_Inactividad.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_Inactividad.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_Inactividad.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_Inactividad.setText("Inactividad");
        selectMvtoEquipo_Inactividad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_Inactividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 440, 220, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioInactividad_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 80, 190, -1));

        selectMvtoEquipo_DesdeCarbon.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_DesdeCarbon.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_DesdeCarbon.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_DesdeCarbon.setText("Desde_Carb??n");
        selectMvtoEquipo_DesdeCarbon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_DesdeCarbon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 180, 200, -1));

        selectMvtoEquipo_UsuarioInactividad_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioInactividad_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioInactividad_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioInactividad_nombre.setText("Nombre_Usuario_Inactividad");
        selectMvtoEquipo_UsuarioInactividad_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioInactividad_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 100, 200, -1));

        selectMvtoEquipo_centroCostoMayor1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCostoMayor1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCostoMayor1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCostoMayor1.setText("CentroCosto Mayor");
        selectMvtoEquipo_centroCostoMayor1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroCostoMayor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 460, 200, -1));

        selectMvtoEquipo_observacion.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_observacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_observacion.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_observacion.setText("Observaci??n");
        selectMvtoEquipo_observacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_observacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 140, 200, -1));

        selectMvtoEquipo_estado.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_estado.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_estado.setText("Estado");
        selectMvtoEquipo_estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 160, 200, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, 190, -1));

        selectAsignacion_codigo1.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_codigo1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_codigo1.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_codigo1.setText("C??digo");
        selectAsignacion_codigo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_codigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 230, 190, -1));

        selectMvtoEquipo_centroCosto1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCosto1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCosto1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCosto1.setText("Centro Costo");
        selectMvtoEquipo_centroCosto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroCosto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 440, 190, -1));

        selectAsignacion_fechaRegistro1.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_fechaRegistro1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_fechaRegistro1.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_fechaRegistro1.setText("Fecha_Registro");
        selectAsignacion_fechaRegistro1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_fechaRegistro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 250, 190, -1));

        selectMvtoEquipo_articuloCodigo1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_articuloCodigo1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_articuloCodigo1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_articuloCodigo1.setText("Articulo c??digo");
        selectMvtoEquipo_articuloCodigo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_articuloCodigo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_articuloCodigo1ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_articuloCodigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 120, 200, -1));

        selectMvtoEquipo_articuloDescripcion1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_articuloDescripcion1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_articuloDescripcion1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_articuloDescripcion1.setText("Articulo Descripci??n");
        selectMvtoEquipo_articuloDescripcion1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_articuloDescripcion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_articuloDescripcion1ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_articuloDescripcion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 140, 200, -1));

        selectMvtoEquipo_motonaveCodigo1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_motonaveCodigo1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_motonaveCodigo1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_motonaveCodigo1.setText("Motonave C??digo");
        selectMvtoEquipo_motonaveCodigo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_motonaveCodigo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_motonaveCodigo1ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_motonaveCodigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 160, 200, -1));

        selectMvtoEquipo_centroCostoAxiliarOrigen1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCostoAxiliarOrigen1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCostoAxiliarOrigen1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCostoAxiliarOrigen1.setText("Auxiliar Centro Costo Origen");
        selectMvtoEquipo_centroCostoAxiliarOrigen1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroCostoAxiliarOrigen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 400, 200, -1));

        selectMvtoEquipo_centroCostoAuxiliarDestino1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCostoAuxiliarDestino1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCostoAuxiliarDestino1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCostoAuxiliarDestino1.setText("Auxiliar Centro Costo Destino");
        selectMvtoEquipo_centroCostoAuxiliarDestino1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroCostoAuxiliarDestino1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 420, 200, -1));

        selectMvtoEquipo_mes.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_mes.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_mes.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_mes.setText("Mes_Registro");
        selectMvtoEquipo_mes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 260, 190, -1));

        selectMvtoEquipo_codigo1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_codigo1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_codigo1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_codigo1.setText("C??digo");
        selectMvtoEquipo_codigo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_codigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 240, 190, -1));

        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setText("UsuarioQuienRegistr??Veh??culo_Correo");
        selectDescargue_usuarioQuienRegistraVehiculoCorreo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_usuarioQuienRegistraVehiculoCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 220, -1));

        titulo12.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        titulo12.setText("PROGRAMACI??N (ASIGNACI??N)");
        titulo12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo12, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 200, 240, 20));

        titulo21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo21, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 50, 240, 450));

        titulo27.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        titulo27.setText("DATOS DEL EQUIPO");
        titulo27.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo27, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 240, 20));

        titulo28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo28.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo28, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 70, 240, 0));

        titulo29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo29.setText("Mvto Equipo");
        titulo29.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo29, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, 240, 20));

        titulo30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo30.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo30, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 50, 240, 450));

        titulo31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo31.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo31, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 240, 450));

        titulo32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo32.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 240, 430));

        titulo33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo33.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo33, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 240, 430));

        selectDescargue_centroCostoAuxiliarDestino.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_centroCostoAuxiliarDestino.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_centroCostoAuxiliarDestino.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_centroCostoAuxiliarDestino.setText("Centro Costo Auxiliar_Destino");
        selectDescargue_centroCostoAuxiliarDestino.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_centroCostoAuxiliarDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 190, -1));

        selectDescargue_subcentroCosto.setBackground(new java.awt.Color(255, 255, 255));
        selectDescargue_subcentroCosto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectDescargue_subcentroCosto.setForeground(new java.awt.Color(51, 51, 51));
        selectDescargue_subcentroCosto.setText("Subcentro Costo");
        selectDescargue_subcentroCosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectDescargue_subcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 190, -1));

        add(InternalFrameSelectorCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1560, 740));

        titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo.setText("PROCESAR DESCARGUE DE VEHICULOS DE CARB??N");
        add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 470, 30));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 1300, 540));

        alerta_fechaFinal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_fechaFinal.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_fechaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 370, 480, 20));

        alerta_fechaInicio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alerta_fechaInicio.setForeground(new java.awt.Color(255, 0, 51));
        add(alerta_fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 430, 20));

        jProgressBar1.setStringPainted(true);
        add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 1010, 30));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCampo.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 140, 50, 40));

        InternalFrameSelectorCampos1.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrameSelectorCampos1.setClosable(true);
        InternalFrameSelectorCampos1.setVisible(false);
        InternalFrameSelectorCampos1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCamposAplicarConfig.png"))); // NOI18N
        jButton4.setText("Aplicar Configuraci??n");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos1.getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 490, 230, 30));

        selectAll1.setBackground(new java.awt.Color(255, 255, 255));
        selectAll1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        selectAll1.setText("Todos");
        selectAll1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectAll1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectAll1ItemStateChanged(evt);
            }
        });
        selectAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAll1ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos1.getContentPane().add(selectAll1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 110, 20));

        selectMvtoEquipo_clienteCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_clienteCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_clienteCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_clienteCodigo.setText("Cliente C??digo");
        selectMvtoEquipo_clienteCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_clienteCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 200, -1));

        titulo9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo9.setText("SELECTOR DE CAMPOS");
        titulo9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos1.getContentPane().add(titulo9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 720, 40));

        titulo17.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        titulo17.setText("PROGRAMACI??N (ASIGNACI??N)");
        titulo17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos1.getContentPane().add(titulo17, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 240, 20));

        jSeparator22.setOrientation(javax.swing.SwingConstants.VERTICAL);
        InternalFrameSelectorCampos1.getContentPane().add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 0, 430));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCamposDefauls.png"))); // NOI18N
        jButton5.setText("Campos Predeterminados");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos1.getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 490, 230, 30));

        selectAsignacion_equipoCodigo1.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoCodigo1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoCodigo1.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoCodigo1.setText("Equipo C??digo");
        selectAsignacion_equipoCodigo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectAsignacion_equipoCodigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 190, -1));

        selectAsignacion_equipoMarca1.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoMarca1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoMarca1.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoMarca1.setText("Equipo Marca");
        selectAsignacion_equipoMarca1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectAsignacion_equipoMarca1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, 190, -1));

        selectAsignacion_equipoModelo1.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoModelo1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoModelo1.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoModelo1.setText("Equipo Modelo");
        selectAsignacion_equipoModelo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectAsignacion_equipoModelo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 130, 190, -1));

        selectMvtoEquipo_fechaRegistro1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_fechaRegistro1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_fechaRegistro1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_fechaRegistro1.setText("Fecha_Registro");
        selectMvtoEquipo_fechaRegistro1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_fechaRegistro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 190, -1));

        selectMvtoEquipo_UsuarioRegistraMvto_nombre1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioRegistraMvto_nombre1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioRegistraMvto_nombre1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioRegistraMvto_nombre1.setText("Nombre_Usuario_Registra_MvtoEquipo");
        selectMvtoEquipo_UsuarioRegistraMvto_nombre1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_UsuarioRegistraMvto_nombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 220, -1));

        titulo23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo23.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos1.getContentPane().add(titulo23, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 240, 20));

        selectMvtoEquipo_cantidadMinutosDescargue1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_cantidadMinutosDescargue1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_cantidadMinutosDescargue1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_cantidadMinutosDescargue1.setText("Cantidad_Minutos_Descargue");
        selectMvtoEquipo_cantidadMinutosDescargue1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_cantidadMinutosDescargue1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_cantidadMinutosDescargue1ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_cantidadMinutosDescargue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 190, -1));

        selectMvtoEquipo_numeroOrden1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_numeroOrden1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_numeroOrden1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_numeroOrden1.setText("N??mero Orden");
        selectMvtoEquipo_numeroOrden1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_numeroOrden1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 190, -1));

        selectAsignacion_equipoTipo1.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoTipo1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoTipo1.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoTipo1.setText("Equipo Tipo");
        selectAsignacion_equipoTipo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectAsignacion_equipoTipo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsignacion_equipoTipo1ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos1.getContentPane().add(selectAsignacion_equipoTipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, 190, -1));

        selectMvtoEquipo_clienteNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_clienteNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_clienteNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_clienteNombre.setText("Cliente Nombre");
        selectMvtoEquipo_clienteNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_clienteNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 200, -1));

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
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_motonaveDescripci??n, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 200, -1));

        selectMvtoEquipo_centroOperacion.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroOperacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroOperacion.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroOperacion.setText("Centro Operaci??n");
        selectMvtoEquipo_centroOperacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 200, -1));

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
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_subcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 200, -1));

        selectAsignacion_fechaInicio1.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_fechaInicio1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_fechaInicio1.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_fechaInicio1.setText("Fecha_inicio");
        selectAsignacion_fechaInicio1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectAsignacion_fechaInicio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsignacion_fechaInicio1ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos1.getContentPane().add(selectAsignacion_fechaInicio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 270, 190, -1));

        selectAsignacion_fechaFinalizacion1.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_fechaFinalizacion1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_fechaFinalizacion1.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_fechaFinalizacion1.setText("Fecha_Finalizaci??n");
        selectAsignacion_fechaFinalizacion1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectAsignacion_fechaFinalizacion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 290, 190, -1));

        selectAsignacion_cantidadMinutos_Programados1.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_cantidadMinutos_Programados1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_cantidadMinutos_Programados1.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_cantidadMinutos_Programados1.setText("Cantidad_Minutos_Programadas");
        selectAsignacion_cantidadMinutos_Programados1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectAsignacion_cantidadMinutos_Programados1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsignacion_cantidadMinutos_Programados1ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos1.getContentPane().add(selectAsignacion_cantidadMinutos_Programados1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 310, 190, -1));

        selectAsignacion_equipoPertenencia1.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoPertenencia1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoPertenencia1.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoPertenencia1.setText("Equipo Pertenencia");
        selectAsignacion_equipoPertenencia1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectAsignacion_equipoPertenencia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, 190, -1));

        selectMvtoEquipo_proveedorEquipoNit.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_proveedorEquipoNit.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_proveedorEquipoNit.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_proveedorEquipoNit.setText("Proveedor Equipo NIT");
        selectMvtoEquipo_proveedorEquipoNit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_proveedorEquipoNit, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 190, -1));

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
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_proveedorEquipoNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 190, -1));

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
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_laborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 190, -1));

        selectMvtoEquipo_fechaInicioDescargue1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_fechaInicioDescargue1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_fechaInicioDescargue1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_fechaInicioDescargue1.setText("Fecha Inicio_Actividad");
        selectMvtoEquipo_fechaInicioDescargue1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_fechaInicioDescargue1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_fechaInicioDescargue1ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_fechaInicioDescargue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 190, -1));

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
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_fechaFinDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 190, -1));

        selectMvtoEquipo_ValorHoraEquipo1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_ValorHoraEquipo1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_ValorHoraEquipo1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_ValorHoraEquipo1.setText("Valor_Hora_Equipo");
        selectMvtoEquipo_ValorHoraEquipo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_ValorHoraEquipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 190, -1));

        selectMvtoEquipo_costoTotal1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_costoTotal1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_costoTotal1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_costoTotal1.setText("Costo_Total");
        selectMvtoEquipo_costoTotal1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_costoTotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 190, -1));

        selectMvtoEquipo_Recobro1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_Recobro1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_Recobro1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_Recobro1.setText("Recobro");
        selectMvtoEquipo_Recobro1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_Recobro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 190, -1));

        selectMvtoEquipo_UsuarioRegistraMvto_codigo1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioRegistraMvto_codigo1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioRegistraMvto_codigo1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioRegistraMvto_codigo1.setText("C??digo_Usuario_Registra_MvtoEquipo");
        selectMvtoEquipo_UsuarioRegistraMvto_codigo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_UsuarioRegistraMvto_codigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, 210, -1));

        selectMvtoEquipo_CausalInactividad1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_CausalInactividad1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_CausalInactividad1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_CausalInactividad1.setText("Causal_Inactividad");
        selectMvtoEquipo_CausalInactividad1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_CausalInactividad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 310, 220, -1));

        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo1.setText("C??digo_Usuario_Autoriza_Recobro");
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_UsuarioAutorizaRecobro_codigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, 210, -1));

        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre1.setText("Nombre_Usuario_Autoriza_Recobro");
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_UsuarioAutorizaRecobro_nombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, 220, -1));

        selectMvtoEquipo_autorizacionRecobro1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_autorizacionRecobro1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_autorizacionRecobro1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_autorizacionRecobro1.setText("Autorizaci??n_Recobro");
        selectMvtoEquipo_autorizacionRecobro1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_autorizacionRecobro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, 220, -1));

        selectMvtoEquipo_MotivoParada.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_MotivoParada.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_MotivoParada.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_MotivoParada.setText("Motivo_Parada");
        selectMvtoEquipo_MotivoParada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_MotivoParada, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 220, -1));

        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion1.setText("Observaci??n_Autorizaci??n_Recobro");
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_UsuarioAutorizaRecobro_observacion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, 220, -1));

        selectMvtoEquipo_Inactividad1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_Inactividad1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_Inactividad1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_Inactividad1.setText("Inactividad");
        selectMvtoEquipo_Inactividad1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_Inactividad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 220, -1));

        selectMvtoEquipo_UsuarioInactividad_codigo1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioInactividad_codigo1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioInactividad_codigo1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioInactividad_codigo1.setText("C??digo_Usuario_Inactividad");
        selectMvtoEquipo_UsuarioInactividad_codigo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_UsuarioInactividad_codigo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_UsuarioInactividad_codigo1ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_UsuarioInactividad_codigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, 210, -1));

        selectMvtoEquipo_DesdeCarbon1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_DesdeCarbon1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_DesdeCarbon1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_DesdeCarbon1.setText("Desde_Carb??n");
        selectMvtoEquipo_DesdeCarbon1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_DesdeCarbon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 430, 220, -1));

        selectMvtoEquipo_UsuarioInactividad_nombre1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioInactividad_nombre1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioInactividad_nombre1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioInactividad_nombre1.setText("Nombre_Usuario_Inactividad");
        selectMvtoEquipo_UsuarioInactividad_nombre1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_UsuarioInactividad_nombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 350, 220, -1));

        selectMvtoEquipo_centroCostoMayor.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCostoMayor.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCostoMayor.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCostoMayor.setText("CentroCosto Mayor");
        selectMvtoEquipo_centroCostoMayor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_centroCostoMayor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 200, -1));

        selectMvtoEquipo_observacion1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_observacion1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_observacion1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_observacion1.setText("Observaci??n");
        selectMvtoEquipo_observacion1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_observacion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, 220, -1));

        selectMvtoEquipo_estado1.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_estado1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_estado1.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_estado1.setText("Estado");
        selectMvtoEquipo_estado1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_estado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 410, 220, -1));

        selectAsignacion_equipoDescripcion1.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoDescripcion1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoDescripcion1.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoDescripcion1.setText("Equipo Descripci??n");
        selectAsignacion_equipoDescripcion1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectAsignacion_equipoDescripcion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsignacion_equipoDescripcion1ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos1.getContentPane().add(selectAsignacion_equipoDescripcion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 150, 190, -1));

        titulo19.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        titulo19.setText("DATOS DEL EQUIPO");
        titulo19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos1.getContentPane().add(titulo19, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 240, 20));

        selectAsignacion_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_codigo.setText("C??digo");
        selectAsignacion_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectAsignacion_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, 190, -1));

        selectMvtoEquipo_centroCosto.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCosto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCosto.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCosto.setText("Centro Costo");
        selectMvtoEquipo_centroCosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_centroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 190, -1));

        selectAsignacion_fechaRegistro.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_fechaRegistro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_fechaRegistro.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_fechaRegistro.setText("Fecha_Registro");
        selectAsignacion_fechaRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectAsignacion_fechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 250, 190, -1));

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
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_articuloCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 200, -1));

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
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_articuloDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 200, -1));

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
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_motonaveCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 200, -1));

        selectMvtoEquipo_centroCostoAxiliarOrigen.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCostoAxiliarOrigen.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCostoAxiliarOrigen.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCostoAxiliarOrigen.setText("Auxiliar Centro Costo Origen");
        selectMvtoEquipo_centroCostoAxiliarOrigen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_centroCostoAxiliarOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 200, -1));

        selectMvtoEquipo_centroCostoAuxiliarDestino.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCostoAuxiliarDestino.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCostoAuxiliarDestino.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCostoAuxiliarDestino.setText("Auxiliar Centro Costo Destino");
        selectMvtoEquipo_centroCostoAuxiliarDestino.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_centroCostoAuxiliarDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 200, -1));

        selectMvtoEquipo_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_codigo.setText("C??digo");
        selectMvtoEquipo_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos1.getContentPane().add(selectMvtoEquipo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 190, -1));

        titulo20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos1.getContentPane().add(titulo20, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 240, 430));

        titulo24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo24.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos1.getContentPane().add(titulo24, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 240, 410));

        titulo25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo25.setText("Mvto Equipo");
        titulo25.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos1.getContentPane().add(titulo25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 240, 20));

        titulo26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo26.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos1.getContentPane().add(titulo26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 240, 410));

        add(InternalFrameSelectorCampos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 0, 850, 610));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 1290, 10));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Campos");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 110, -1, 30));

        time.setToolTipText("");
        add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 80, 30));

        iniciar.setMnemonic('T');
        iniciar.setText("INICIAR");
        iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarActionPerformed(evt);
            }
        });
        add(iniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 80, 30));

        parar.setText("PARAR");
        parar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pararActionPerformed(evt);
            }
        });
        add(parar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 80, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Tiempo de ejecuci??n:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 160, 30));

        rango.setToolTipText("");
        add(rango, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 100, 30));

        modalidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DAY", "HOUR", "MINUTE", "SECOND" }));
        modalidad.setSelectedIndex(1);
        modalidad.setToolTipText("");
        add(modalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 130, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("en el pasado.");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 520, 30));

        modalidadHilo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SECOND", "MINUTE", "HOUR", "DAY" }));
        modalidadHilo.setToolTipText("");
        add(modalidadHilo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 80, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Procesar datos desde fecha actual del sistema a ");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 520, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1290, 140));

        paginationPanel.setBackground(new java.awt.Color(255, 255, 255));
        add(paginationPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 740, 1300, 80));

        add(pageJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 10, 60, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        InternalFrameSelectorCampos.show(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        InternalFrameSelectorCampos.show(false);
        /*try {
            generarListadoMvtoCarbon();
        } catch (SQLException ex) {
            Logger.getLogger(MvtoEquipo_Procesar_Programado.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_jButton4ActionPerformed

    private void selectAll1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectAll1ItemStateChanged
        if(selectAll.isSelected()){
            selectorCampoTodos();
        }else{
            selectorCampoNinguno();
        }
    }//GEN-LAST:event_selectAll1ItemStateChanged

    private void selectAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAll1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAll1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        selectorCampoPorDefecto();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void selectMvtoEquipo_cantidadMinutosDescargue1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_cantidadMinutosDescargue1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_cantidadMinutosDescargue1ActionPerformed

    private void selectAsignacion_equipoTipo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAsignacion_equipoTipo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAsignacion_equipoTipo1ActionPerformed

    private void selectMvtoEquipo_motonaveDescripci??nActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_motonaveDescripci??nActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_motonaveDescripci??nActionPerformed

    private void selectMvtoEquipo_subcentroCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_subcentroCostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_subcentroCostoActionPerformed

    private void selectAsignacion_fechaInicio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAsignacion_fechaInicio1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAsignacion_fechaInicio1ActionPerformed

    private void selectAsignacion_cantidadMinutos_Programados1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAsignacion_cantidadMinutos_Programados1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAsignacion_cantidadMinutos_Programados1ActionPerformed

    private void selectMvtoEquipo_proveedorEquipoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_proveedorEquipoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_proveedorEquipoNombreActionPerformed

    private void selectMvtoEquipo_laborRealizadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_laborRealizadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_laborRealizadaActionPerformed

    private void selectMvtoEquipo_fechaInicioDescargue1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_fechaInicioDescargue1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_fechaInicioDescargue1ActionPerformed

    private void selectMvtoEquipo_fechaFinDescargueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_fechaFinDescargueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_fechaFinDescargueActionPerformed

    private void selectMvtoEquipo_UsuarioInactividad_codigo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_UsuarioInactividad_codigo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_UsuarioInactividad_codigo1ActionPerformed

    private void selectAsignacion_equipoDescripcion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAsignacion_equipoDescripcion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAsignacion_equipoDescripcion1ActionPerformed

    private void selectMvtoEquipo_articuloCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_articuloCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_articuloCodigoActionPerformed

    private void selectMvtoEquipo_articuloDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_articuloDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_articuloDescripcionActionPerformed

    private void selectMvtoEquipo_motonaveCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_motonaveCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_motonaveCodigoActionPerformed

    private void iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarActionPerformed
        Iniciar1 = new Iniciar();
        Iniciar1.start();
        iniciar.setEnabled(false);
        parar.setEnabled(true);
    }//GEN-LAST:event_iniciarActionPerformed

    private void pararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pararActionPerformed
        Iniciar1.stop();
        Iniciar1 = new Iniciar();
        iniciar.setEnabled(true);
        parar.setEnabled(false);
    }//GEN-LAST:event_pararActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        InternalFrameSelectorCampos.show(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void selectAllItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectAllItemStateChanged
        if(selectAll.isSelected()){
            selectorCampoTodos();
        }else{
            selectorCampoNinguno();
        }
    }//GEN-LAST:event_selectAllItemStateChanged

    private void selectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAllActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAllActionPerformed

    private void selectDescargue_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDescargue_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectDescargue_codigoActionPerformed

    private void selectDescargue_pesoVacioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDescargue_pesoVacioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectDescargue_pesoVacioActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        selectorCampoPorDefecto();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void selectDescargue_articuloCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDescargue_articuloCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectDescargue_articuloCodigoActionPerformed

    private void selectDescargue_centroCostoMayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDescargue_centroCostoMayorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectDescargue_centroCostoMayorActionPerformed

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

    private void selectMvtoEquipo_motonaveDescripci??n1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_motonaveDescripci??n1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_motonaveDescripci??n1ActionPerformed

    private void selectMvtoEquipo_subcentroCosto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_subcentroCosto1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_subcentroCosto1ActionPerformed

    private void selectAsignacion_fechaInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAsignacion_fechaInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAsignacion_fechaInicioActionPerformed

    private void selectAsignacion_cantidadMinutos_ProgramadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAsignacion_cantidadMinutos_ProgramadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAsignacion_cantidadMinutos_ProgramadosActionPerformed

    private void selectMvtoEquipo_proveedorEquipoNombre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_proveedorEquipoNombre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_proveedorEquipoNombre1ActionPerformed

    private void selectMvtoEquipo_laborRealizada1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_laborRealizada1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_laborRealizada1ActionPerformed

    private void selectMvtoEquipo_fechaInicioDescargueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_fechaInicioDescargueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_fechaInicioDescargueActionPerformed

    private void selectMvtoEquipo_fechaFinDescargue1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_fechaFinDescargue1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_fechaFinDescargue1ActionPerformed

    private void selectMvtoEquipo_UsuarioInactividad_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_UsuarioInactividad_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_UsuarioInactividad_codigoActionPerformed

    private void selectAsignacion_equipoDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAsignacion_equipoDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAsignacion_equipoDescripcionActionPerformed

    private void selectMvtoEquipo_articuloCodigo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_articuloCodigo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_articuloCodigo1ActionPerformed

    private void selectMvtoEquipo_articuloDescripcion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_articuloDescripcion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_articuloDescripcion1ActionPerformed

    private void selectMvtoEquipo_motonaveCodigo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_motonaveCodigo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_motonaveCodigo1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame InternalFrameSelectorCampos;
    private javax.swing.JInternalFrame InternalFrameSelectorCampos1;
    private javax.swing.JLabel alerta_fechaFinal;
    private javax.swing.JLabel alerta_fechaInicio;
    private javax.swing.JButton iniciar;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JComboBox<String> modalidad;
    private javax.swing.JComboBox<String> modalidadHilo;
    private javax.swing.JComboBox<Integer> pageJComboBox;
    public javax.swing.JPanel paginationPanel;
    private javax.swing.JButton parar;
    private javax.swing.JComboBox<String> rango;
    private javax.swing.JRadioButton selectAll;
    private javax.swing.JRadioButton selectAll1;
    private javax.swing.JRadioButton selectAsignacion_cantidadMinutos_Programados;
    private javax.swing.JRadioButton selectAsignacion_cantidadMinutos_Programados1;
    private javax.swing.JRadioButton selectAsignacion_codigo;
    private javax.swing.JRadioButton selectAsignacion_codigo1;
    private javax.swing.JRadioButton selectAsignacion_equipoCodigo;
    private javax.swing.JRadioButton selectAsignacion_equipoCodigo1;
    private javax.swing.JRadioButton selectAsignacion_equipoDescripcion;
    private javax.swing.JRadioButton selectAsignacion_equipoDescripcion1;
    private javax.swing.JRadioButton selectAsignacion_equipoMarca;
    private javax.swing.JRadioButton selectAsignacion_equipoMarca1;
    private javax.swing.JRadioButton selectAsignacion_equipoModelo;
    private javax.swing.JRadioButton selectAsignacion_equipoModelo1;
    private javax.swing.JRadioButton selectAsignacion_equipoPertenencia;
    private javax.swing.JRadioButton selectAsignacion_equipoPertenencia1;
    private javax.swing.JRadioButton selectAsignacion_equipoTipo;
    private javax.swing.JRadioButton selectAsignacion_equipoTipo1;
    private javax.swing.JRadioButton selectAsignacion_fechaFinalizacion;
    private javax.swing.JRadioButton selectAsignacion_fechaFinalizacion1;
    private javax.swing.JRadioButton selectAsignacion_fechaInicio;
    private javax.swing.JRadioButton selectAsignacion_fechaInicio1;
    private javax.swing.JRadioButton selectAsignacion_fechaRegistro;
    private javax.swing.JRadioButton selectAsignacion_fechaRegistro1;
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
    private javax.swing.JRadioButton selectDescargue_estado;
    private javax.swing.JRadioButton selectDescargue_fechaEntradaVehiculo;
    private javax.swing.JRadioButton selectDescargue_fechaFinDescargue;
    private javax.swing.JRadioButton selectDescargue_fechaInicioDescargue;
    private javax.swing.JRadioButton selectDescargue_fechaRegistro;
    private javax.swing.JRadioButton selectDescargue_fechaSalidaVehiculo;
    private javax.swing.JRadioButton selectDescargue_laborRealizada;
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
    private javax.swing.JRadioButton selectDescargue_usuarioQuienRegistraVehiculoCodigo;
    private javax.swing.JRadioButton selectDescargue_usuarioQuienRegistraVehiculoCorreo;
    private javax.swing.JRadioButton selectDescargue_usuarioQuienRegistraVehiculoNombre;
    private javax.swing.JRadioButton selectDescargue_usuarioRegistroManualCodigo;
    private javax.swing.JRadioButton selectDescargue_usuarioRegistroManualCorreo;
    private javax.swing.JRadioButton selectDescargue_usuarioRegistroManualNombre;
    private javax.swing.JRadioButton selectMvtoEquipo_CausalInactividad;
    private javax.swing.JRadioButton selectMvtoEquipo_CausalInactividad1;
    private javax.swing.JRadioButton selectMvtoEquipo_DesdeCarbon;
    private javax.swing.JRadioButton selectMvtoEquipo_DesdeCarbon1;
    private javax.swing.JRadioButton selectMvtoEquipo_Inactividad;
    private javax.swing.JRadioButton selectMvtoEquipo_Inactividad1;
    private javax.swing.JRadioButton selectMvtoEquipo_MotivoParada;
    private javax.swing.JRadioButton selectMvtoEquipo_MotivoParada1;
    private javax.swing.JRadioButton selectMvtoEquipo_Recobro;
    private javax.swing.JRadioButton selectMvtoEquipo_Recobro1;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioAutorizaRecobro_codigo;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioAutorizaRecobro_codigo1;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioAutorizaRecobro_nombre;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioAutorizaRecobro_nombre1;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioAutorizaRecobro_observacion;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioAutorizaRecobro_observacion1;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioInactividad_codigo;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioInactividad_codigo1;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioInactividad_nombre;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioInactividad_nombre1;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioRegistraMvto_codigo;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioRegistraMvto_codigo1;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioRegistraMvto_nombre;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioRegistraMvto_nombre1;
    private javax.swing.JRadioButton selectMvtoEquipo_ValorHoraEquipo;
    private javax.swing.JRadioButton selectMvtoEquipo_ValorHoraEquipo1;
    private javax.swing.JRadioButton selectMvtoEquipo_articuloCodigo;
    private javax.swing.JRadioButton selectMvtoEquipo_articuloCodigo1;
    private javax.swing.JRadioButton selectMvtoEquipo_articuloDescripcion;
    private javax.swing.JRadioButton selectMvtoEquipo_articuloDescripcion1;
    private javax.swing.JRadioButton selectMvtoEquipo_autorizacionRecobro;
    private javax.swing.JRadioButton selectMvtoEquipo_autorizacionRecobro1;
    private javax.swing.JRadioButton selectMvtoEquipo_cantidadMinutosDescargue;
    private javax.swing.JRadioButton selectMvtoEquipo_cantidadMinutosDescargue1;
    private javax.swing.JRadioButton selectMvtoEquipo_centroCosto;
    private javax.swing.JRadioButton selectMvtoEquipo_centroCosto1;
    private javax.swing.JRadioButton selectMvtoEquipo_centroCostoAuxiliarDestino;
    private javax.swing.JRadioButton selectMvtoEquipo_centroCostoAuxiliarDestino1;
    private javax.swing.JRadioButton selectMvtoEquipo_centroCostoAxiliarOrigen;
    private javax.swing.JRadioButton selectMvtoEquipo_centroCostoAxiliarOrigen1;
    private javax.swing.JRadioButton selectMvtoEquipo_centroCostoMayor;
    private javax.swing.JRadioButton selectMvtoEquipo_centroCostoMayor1;
    private javax.swing.JRadioButton selectMvtoEquipo_centroOperacion;
    private javax.swing.JRadioButton selectMvtoEquipo_centroOperacion1;
    private javax.swing.JRadioButton selectMvtoEquipo_clienteCodigo;
    private javax.swing.JRadioButton selectMvtoEquipo_clienteCodigo1;
    private javax.swing.JRadioButton selectMvtoEquipo_clienteNombre;
    private javax.swing.JRadioButton selectMvtoEquipo_clienteNombre1;
    private javax.swing.JRadioButton selectMvtoEquipo_codigo;
    private javax.swing.JRadioButton selectMvtoEquipo_codigo1;
    private javax.swing.JRadioButton selectMvtoEquipo_costoTotal;
    private javax.swing.JRadioButton selectMvtoEquipo_costoTotal1;
    private javax.swing.JRadioButton selectMvtoEquipo_estado;
    private javax.swing.JRadioButton selectMvtoEquipo_estado1;
    private javax.swing.JRadioButton selectMvtoEquipo_fechaFinDescargue;
    private javax.swing.JRadioButton selectMvtoEquipo_fechaFinDescargue1;
    private javax.swing.JRadioButton selectMvtoEquipo_fechaInicioDescargue;
    private javax.swing.JRadioButton selectMvtoEquipo_fechaInicioDescargue1;
    private javax.swing.JRadioButton selectMvtoEquipo_fechaRegistro;
    private javax.swing.JRadioButton selectMvtoEquipo_fechaRegistro1;
    private javax.swing.JRadioButton selectMvtoEquipo_laborRealizada;
    private javax.swing.JRadioButton selectMvtoEquipo_laborRealizada1;
    private javax.swing.JRadioButton selectMvtoEquipo_mes;
    private javax.swing.JRadioButton selectMvtoEquipo_motonaveCodigo;
    private javax.swing.JRadioButton selectMvtoEquipo_motonaveCodigo1;
    private javax.swing.JRadioButton selectMvtoEquipo_motonaveDescripci??n;
    private javax.swing.JRadioButton selectMvtoEquipo_motonaveDescripci??n1;
    private javax.swing.JRadioButton selectMvtoEquipo_numeroOrden;
    private javax.swing.JRadioButton selectMvtoEquipo_numeroOrden1;
    private javax.swing.JRadioButton selectMvtoEquipo_observacion;
    private javax.swing.JRadioButton selectMvtoEquipo_observacion1;
    private javax.swing.JRadioButton selectMvtoEquipo_proveedorEquipoNit;
    private javax.swing.JRadioButton selectMvtoEquipo_proveedorEquipoNit1;
    private javax.swing.JRadioButton selectMvtoEquipo_proveedorEquipoNombre;
    private javax.swing.JRadioButton selectMvtoEquipo_proveedorEquipoNombre1;
    private javax.swing.JRadioButton selectMvtoEquipo_subcentroCosto;
    private javax.swing.JRadioButton selectMvtoEquipo_subcentroCosto1;
    private javax.swing.JTable tabla;
    private javax.swing.JComboBox<String> time;
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
    private javax.swing.JLabel titulo25;
    private javax.swing.JLabel titulo26;
    private javax.swing.JLabel titulo27;
    private javax.swing.JLabel titulo28;
    private javax.swing.JLabel titulo29;
    private javax.swing.JLabel titulo30;
    private javax.swing.JLabel titulo31;
    private javax.swing.JLabel titulo32;
    private javax.swing.JLabel titulo33;
    private javax.swing.JLabel titulo9;
    // End of variables declaration//GEN-END:variables
    
    public void tabla_Listar() throws SQLException{
        tabla.setModel(crearModeloDeTabla());
        listado=new ControlDB_MvtoCarbon(tipoConexion).buscarMvtoCarbonProgramado(modalidad.getSelectedItem().toString(),rango.getSelectedItem().toString());
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
    public void generarListadoMvtoCarbon() throws SQLException{
        tabla_Listar();          
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
        selectDescargue_fechaRegistro.setSelected(true);
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
        selectDescargue_fechaRegistro.setSelected(true);
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
        selectDescargue_fechaRegistro.setSelected(false);
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
                        if(listado1.getMvtoCarbon().getArticulo().getTipoArticulo().getDescripcion() != null){
                            return listado1.getMvtoCarbon().getArticulo().getTipoArticulo().getDescripcion();
                        }else{
                            return "";
                        }   
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
                    case "Fecha_Registro":{
                       return listado1.getMvtoCarbon().getFechaRegistro();
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
                       return listado1.getMvtoCarbon().getUsuarioRegistroMovil().getNombres()+" "+listado1.getMvtoCarbon().getUsuarioRegistroMovil().getApellidos();
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
                       return listado1.getMvtoCarbon().getUsuarioRegistraManual().getNombres()+" "+listado1.getMvtoCarbon().getUsuarioRegistraManual().getApellidos();
                    }
                    case "Usuario_Quien_RegistraManual_Correo":{
                       return listado1.getMvtoCarbon().getUsuarioRegistraManual().getCorreo();
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
                       return listado1.getMvtoEquipo().getUsuarioQuieRegistra().getNombres()+" "+listado1.getMvtoEquipo().getUsuarioQuieRegistra().getApellidos();
                    }
                    case "MvtoEquipo_UsuarioQuienAutoriza_C??digo":{
                       return listado1.getMvtoEquipo().getUsuarioAutorizaRecobro().getCodigo();
                    }
                    case "MvtoEquipo_UsuarioQuienAutoriza_Nombre":{
                       return listado1.getMvtoEquipo().getUsuarioAutorizaRecobro().getNombres()+" "+listado1.getMvtoEquipo().getUsuarioAutorizaRecobro().getApellidos();
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
                       return listado1.getMvtoEquipo().getAsignacionEquipo().getEquipo().getDescripcion()+" "+listado1.getMvtoEquipo().getAsignacionEquipo().getEquipo().getModelo();
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
            default: mes="";
        }
        return mes;
    }
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
    public  class Iniciar extends Thread{  
        public Iniciar() {
        }
		
        @Override
        public void run() {
            while(true){
                //Se duerme durante valorTime
                int valortime=Integer.parseInt(time.getSelectedItem().toString());
                if(modalidadHilo.getSelectedItem().toString().equals("SECOND")){
                    valortime= valortime * 1000;
                }
                if(modalidadHilo.getSelectedItem().toString().equals("MINUTE")){
                    valortime= valortime * 1000* 60;
                }
                if(modalidadHilo.getSelectedItem().toString().equals("HOUR")){
                    valortime= valortime * 1000 * 60 * 60;
                }
                if(modalidadHilo.getSelectedItem().toString().equals("DAY")){
                    valortime= valortime * 1000 * 60 * 60 * 24;
                }

                try {
                    
                    Thread.sleep(valortime); 
                    
                    //iniciarCronometro.stop();
                    //iniciarCronometro = new HiloCronometro();
                    //iniciarCronometro.start();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MvtoEquipo_Procesar_Programado.class.getName()).log(Level.SEVERE, null, ex);
                }
             
                try {
                    paginationPanel.removeAll();
                    validarSeleccionCampos();
                    generarListadoMvtoCarbon();
                } catch (SQLException ex) {
                    Logger.getLogger(MvtoCarbon_ProcesarProgramado.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(listado!=null){
                    Worker_MvtonCarbon  worker = new Worker_MvtonCarbon (jProgressBar1,listado, user,tipoConexion);
                    worker.execute();
                }
          }
        }
    }  /*
    public  class HiloCronometro extends Thread{  
        private int hora=0;
        private int minute=0;
        private int seconds=0;
        public HiloCronometro() {
            hora=0;
            minute=0;
            seconds=0;
        }	
        @Override
        public void run() {
            while(true){
                try {
                    String time="";
                    if(hora <= 9){
                        time += "0"+hora+":";
                    }else{
                        time += hora+":";
                    }
                    if(minute <= 9){
                        time += "0"+minute+":";
                    }else{
                        time += minute+":";
                    }
                    if(seconds <= 9){
                        time += "0"+seconds;
                    }else{
                        time += seconds;
                    }
                    timer.setText(time);  
                    Thread.sleep(1000);
                    if(seconds < 59){
                        seconds++;
                    }else{
                        if(minute < 59){
                            minute++;
                            seconds=0;
                        }else{
                            hora++;
                            minute=0;
                            seconds=0;
                        }
                    }
                    if(hora <= 9){
                        time += "0"+hora+":";
                    }else{
                        time += hora+":";
                    }
                    if(minute <= 9){
                        time += "0"+minute+":";
                    }else{
                        time += minute+":";
                    }
                    if(seconds <= 9){
                        time += "0"+seconds;
                    }else{
                        time += seconds;
                    }
                    timer.setText(time);  
                } catch (InterruptedException ex) {
                    Logger.getLogger(MvtoEquipo_Procesar_Programado.class.getName()).log(Level.SEVERE, null, ex);
                }
          }
        }
    }  */
}
