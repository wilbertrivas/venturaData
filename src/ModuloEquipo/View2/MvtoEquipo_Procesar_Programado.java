package ModuloEquipo.View2;
  
import ModuloEquipo.Controller2.ControlDB_MvtoEquipo;
import ModuloEquipo.Model.MvtoEquipo;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import utilities.ModeloDeTabla;
import utilities.PaginadorDeTabla;
import utilities.ProveedorDeDatosDePaginacion;
 
public final class MvtoEquipo_Procesar_Programado extends javax.swing.JPanel implements ActionListener, TableModelListener{
    ArrayList<MvtoEquipo> listado=null;
    private Usuario user;
    private String tipoConexion;
    Iniciar Iniciar1 = new Iniciar();
     //ArrayList<MvtoEquipo> listado=null;
    //private final Usuario user;
    //private final String tipoConexion;
    private PaginadorDeTabla<MvtoEquipo> paginadorDeTabla;  
    ProveedorDeDatosDePaginacion<MvtoEquipo> proveedorDeDatos;
    ArrayList<String> encabezadoTabla=null;
    public MvtoEquipo_Procesar_Programado(Usuario user,String tipoConexion) {
        initComponents();
        this.tipoConexion= tipoConexion;
        this.user= user;
        InternalFrameSelectorCampos.getContentPane().setBackground(Color.WHITE);
        InternalFrameSelectorCampos.show(false);
        for(int i=1; i<=365; i++){
            time.addItem(""+i);
            rango.addItem(""+i);
        }
        
        iniciar.setEnabled(true);
        parar.setEnabled(false);
        selectorCampoPorDefecto();
        jProgressBar1.show(false); 
        encabezadoTabla= new ArrayList<String>();
        pageJComboBox.show(false);
        jProgressBar1.show(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        InternalFrameSelectorCampos = new javax.swing.JInternalFrame();
        jButton2 = new javax.swing.JButton();
        selectAll = new javax.swing.JRadioButton();
        selectMvtoEquipo_clienteCodigo = new javax.swing.JRadioButton();
        titulo2 = new javax.swing.JLabel();
        titulo12 = new javax.swing.JLabel();
        jSeparator21 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        selectAsignacion_equipoCodigo = new javax.swing.JRadioButton();
        selectAsignacion_equipoMarca = new javax.swing.JRadioButton();
        selectAsignacion_equipoModelo = new javax.swing.JRadioButton();
        selectMvtoEquipo_fechaRegistro = new javax.swing.JRadioButton();
        selectMvtoEquipo_UsuarioRegistraMvto_nombre = new javax.swing.JRadioButton();
        titulo21 = new javax.swing.JLabel();
        selectMvtoEquipo_cantidadMinutosDescargue = new javax.swing.JRadioButton();
        selectMvtoEquipo_numeroOrden = new javax.swing.JRadioButton();
        selectAsignacion_equipoTipo = new javax.swing.JRadioButton();
        selectMvtoEquipo_clienteNombre = new javax.swing.JRadioButton();
        selectMvtoEquipo_motonaveDescripción = new javax.swing.JRadioButton();
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
        titulo13 = new javax.swing.JLabel();
        selectAsignacion_codigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroCosto = new javax.swing.JRadioButton();
        selectAsignacion_fechaRegistro = new javax.swing.JRadioButton();
        selectMvtoEquipo_articuloCodigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_articuloDescripcion = new javax.swing.JRadioButton();
        selectMvtoEquipo_motonaveCodigo = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroCostoAxiliarOrigen = new javax.swing.JRadioButton();
        selectMvtoEquipo_centroCostoAuxiliarDestino = new javax.swing.JRadioButton();
        selectMvtoEquipo_codigo = new javax.swing.JRadioButton();
        titulo19 = new javax.swing.JLabel();
        titulo20 = new javax.swing.JLabel();
        titulo23 = new javax.swing.JLabel();
        titulo18 = new javax.swing.JLabel();
        selectMvtoEquipo_mes = new javax.swing.JRadioButton();
        titulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jProgressBar1 = new javax.swing.JProgressBar();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
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
        pageJComboBox = new javax.swing.JComboBox<>();
        paginationPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InternalFrameSelectorCampos.setBackground(new java.awt.Color(255, 255, 255));
        InternalFrameSelectorCampos.setClosable(true);
        InternalFrameSelectorCampos.setVisible(false);
        InternalFrameSelectorCampos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCamposAplicarConfig.png"))); // NOI18N
        jButton2.setText("Aplicar Configuración");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 490, 230, 30));

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
        InternalFrameSelectorCampos.getContentPane().add(selectAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, 110, 20));

        selectMvtoEquipo_clienteCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_clienteCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_clienteCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_clienteCodigo.setText("Cliente Código");
        selectMvtoEquipo_clienteCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_clienteCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 200, -1));

        titulo2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo2.setText("SELECTOR DE CAMPOS");
        titulo2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 720, 40));

        titulo12.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        titulo12.setText("PROGRAMACIÓN (ASIGNACIÓN)");
        titulo12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo12, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 200, 240, 20));

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
        InternalFrameSelectorCampos.getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 490, 230, 30));

        selectAsignacion_equipoCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoCodigo.setText("Equipo Código");
        selectAsignacion_equipoCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 70, 190, -1));

        selectAsignacion_equipoMarca.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoMarca.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoMarca.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoMarca.setText("Equipo Marca");
        selectAsignacion_equipoMarca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 110, 190, -1));

        selectAsignacion_equipoModelo.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoModelo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoModelo.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoModelo.setText("Equipo Modelo");
        selectAsignacion_equipoModelo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 130, 190, -1));

        selectMvtoEquipo_fechaRegistro.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_fechaRegistro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_fechaRegistro.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_fechaRegistro.setText("Fecha_Registro");
        selectMvtoEquipo_fechaRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_fechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 190, -1));

        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setText("Nombre_Usuario_Registra_MvtoEquipo");
        selectMvtoEquipo_UsuarioRegistraMvto_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioRegistraMvto_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 190, 220, -1));

        titulo21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo21, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 240, 20));

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
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_cantidadMinutosDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 190, -1));

        selectMvtoEquipo_numeroOrden.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_numeroOrden.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_numeroOrden.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_numeroOrden.setText("Número Orden");
        selectMvtoEquipo_numeroOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_numeroOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, 190, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 90, 190, -1));

        selectMvtoEquipo_clienteNombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_clienteNombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_clienteNombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_clienteNombre.setText("Cliente Nombre");
        selectMvtoEquipo_clienteNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_clienteNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, 200, -1));

        selectMvtoEquipo_motonaveDescripción.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_motonaveDescripción.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_motonaveDescripción.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_motonaveDescripción.setText("Motonave Descripción");
        selectMvtoEquipo_motonaveDescripción.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_motonaveDescripción.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_motonaveDescripciónActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_motonaveDescripción, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 430, 200, -1));

        selectMvtoEquipo_centroOperacion.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroOperacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroOperacion.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroOperacion.setText("Centro Operación");
        selectMvtoEquipo_centroOperacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 200, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_subcentroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 200, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 270, 190, -1));

        selectAsignacion_fechaFinalizacion.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_fechaFinalizacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_fechaFinalizacion.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_fechaFinalizacion.setText("Fecha_Finalización");
        selectAsignacion_fechaFinalizacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_fechaFinalizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 290, 190, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_cantidadMinutos_Programados, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 310, 190, -1));

        selectAsignacion_equipoPertenencia.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoPertenencia.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoPertenencia.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoPertenencia.setText("Equipo Pertenencia");
        selectAsignacion_equipoPertenencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoPertenencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 170, 190, -1));

        selectMvtoEquipo_proveedorEquipoNit.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_proveedorEquipoNit.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_proveedorEquipoNit.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_proveedorEquipoNit.setText("Proveedor Equipo NIT");
        selectMvtoEquipo_proveedorEquipoNit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_proveedorEquipoNit, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 190, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_proveedorEquipoNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 190, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_laborRealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 190, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_fechaInicioDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 450, 190, -1));

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
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_fechaFinDescargue, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, 190, -1));

        selectMvtoEquipo_ValorHoraEquipo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_ValorHoraEquipo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_ValorHoraEquipo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_ValorHoraEquipo.setText("Valor_Hora_Equipo");
        selectMvtoEquipo_ValorHoraEquipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_ValorHoraEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, 190, -1));

        selectMvtoEquipo_costoTotal.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_costoTotal.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_costoTotal.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_costoTotal.setText("Costo_Total");
        selectMvtoEquipo_costoTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_costoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 190, -1));

        selectMvtoEquipo_Recobro.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_Recobro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_Recobro.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_Recobro.setText("Recobro");
        selectMvtoEquipo_Recobro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_Recobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, 190, -1));

        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setText("Código_Usuario_Registra_MvtoEquipo");
        selectMvtoEquipo_UsuarioRegistraMvto_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioRegistraMvto_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, 210, -1));

        selectMvtoEquipo_CausalInactividad.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_CausalInactividad.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_CausalInactividad.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_CausalInactividad.setText("Causal_Inactividad");
        selectMvtoEquipo_CausalInactividad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_CausalInactividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 310, 220, -1));

        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setText("Código_Usuario_Autoriza_Recobro");
        selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioAutorizaRecobro_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 210, 210, -1));

        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setText("Nombre_Usuario_Autoriza_Recobro");
        selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioAutorizaRecobro_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 220, -1));

        selectMvtoEquipo_autorizacionRecobro.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_autorizacionRecobro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_autorizacionRecobro.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_autorizacionRecobro.setText("Autorización_Recobro");
        selectMvtoEquipo_autorizacionRecobro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_autorizacionRecobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, 220, -1));

        selectMvtoEquipo_MotivoParada.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_MotivoParada.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_MotivoParada.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_MotivoParada.setText("Motivo_Parada");
        selectMvtoEquipo_MotivoParada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_MotivoParada, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, 220, -1));

        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setText("Observación_Autorización_Recobro");
        selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioAutorizaRecobro_observacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 270, 220, -1));

        selectMvtoEquipo_Inactividad.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_Inactividad.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_Inactividad.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_Inactividad.setText("Inactividad");
        selectMvtoEquipo_Inactividad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_Inactividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, 220, -1));

        selectMvtoEquipo_UsuarioInactividad_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioInactividad_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioInactividad_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioInactividad_codigo.setText("Código_Usuario_Inactividad");
        selectMvtoEquipo_UsuarioInactividad_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_UsuarioInactividad_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_UsuarioInactividad_codigoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioInactividad_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 330, 210, -1));

        selectMvtoEquipo_DesdeCarbon.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_DesdeCarbon.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_DesdeCarbon.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_DesdeCarbon.setText("Desde_Carbón");
        selectMvtoEquipo_DesdeCarbon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_DesdeCarbon, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 430, 220, -1));

        selectMvtoEquipo_UsuarioInactividad_nombre.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_UsuarioInactividad_nombre.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_UsuarioInactividad_nombre.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_UsuarioInactividad_nombre.setText("Nombre_Usuario_Inactividad");
        selectMvtoEquipo_UsuarioInactividad_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_UsuarioInactividad_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 350, 220, -1));

        selectMvtoEquipo_centroCostoMayor.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCostoMayor.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCostoMayor.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCostoMayor.setText("CentroCosto Mayor");
        selectMvtoEquipo_centroCostoMayor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroCostoMayor, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 290, 200, -1));

        selectMvtoEquipo_observacion.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_observacion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_observacion.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_observacion.setText("Observación");
        selectMvtoEquipo_observacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_observacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 390, 220, -1));

        selectMvtoEquipo_estado.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_estado.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_estado.setText("Estado");
        selectMvtoEquipo_estado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 410, 220, -1));

        selectAsignacion_equipoDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_equipoDescripcion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_equipoDescripcion.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_equipoDescripcion.setText("Equipo Descripción");
        selectAsignacion_equipoDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectAsignacion_equipoDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAsignacion_equipoDescripcionActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_equipoDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 150, 190, -1));

        titulo13.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        titulo13.setText("DATOS DEL EQUIPO");
        titulo13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo13, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 40, 240, 20));

        selectAsignacion_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_codigo.setText("Código");
        selectAsignacion_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 230, 190, -1));

        selectMvtoEquipo_centroCosto.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCosto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCosto.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCosto.setText("Centro Costo");
        selectMvtoEquipo_centroCosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, 190, -1));

        selectAsignacion_fechaRegistro.setBackground(new java.awt.Color(255, 255, 255));
        selectAsignacion_fechaRegistro.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectAsignacion_fechaRegistro.setForeground(new java.awt.Color(51, 51, 51));
        selectAsignacion_fechaRegistro.setText("Fecha_Registro");
        selectAsignacion_fechaRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectAsignacion_fechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 250, 190, -1));

        selectMvtoEquipo_articuloCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_articuloCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_articuloCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_articuloCodigo.setText("Articulo código");
        selectMvtoEquipo_articuloCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_articuloCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_articuloCodigoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_articuloCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 370, 200, -1));

        selectMvtoEquipo_articuloDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_articuloDescripcion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_articuloDescripcion.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_articuloDescripcion.setText("Articulo Descripción");
        selectMvtoEquipo_articuloDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_articuloDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_articuloDescripcionActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_articuloDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, 200, -1));

        selectMvtoEquipo_motonaveCodigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_motonaveCodigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_motonaveCodigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_motonaveCodigo.setText("Motonave Código");
        selectMvtoEquipo_motonaveCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        selectMvtoEquipo_motonaveCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMvtoEquipo_motonaveCodigoActionPerformed(evt);
            }
        });
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_motonaveCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 410, 200, -1));

        selectMvtoEquipo_centroCostoAxiliarOrigen.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCostoAxiliarOrigen.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCostoAxiliarOrigen.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCostoAxiliarOrigen.setText("Auxiliar Centro Costo Origen");
        selectMvtoEquipo_centroCostoAxiliarOrigen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroCostoAxiliarOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 200, -1));

        selectMvtoEquipo_centroCostoAuxiliarDestino.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_centroCostoAuxiliarDestino.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_centroCostoAuxiliarDestino.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_centroCostoAuxiliarDestino.setText("Auxiliar Centro Costo Destino");
        selectMvtoEquipo_centroCostoAuxiliarDestino.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_centroCostoAuxiliarDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, 200, -1));

        selectMvtoEquipo_codigo.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_codigo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_codigo.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_codigo.setText("Código");
        selectMvtoEquipo_codigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 190, -1));

        titulo19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo19, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 40, 240, 430));

        titulo20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo20, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 240, 410));

        titulo23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo23.setText("Mvto Equipo");
        titulo23.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo23, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 240, 20));

        titulo18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        InternalFrameSelectorCampos.getContentPane().add(titulo18, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 240, 410));

        selectMvtoEquipo_mes.setBackground(new java.awt.Color(255, 255, 255));
        selectMvtoEquipo_mes.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        selectMvtoEquipo_mes.setForeground(new java.awt.Color(51, 51, 51));
        selectMvtoEquipo_mes.setText("Mes_Registro");
        selectMvtoEquipo_mes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InternalFrameSelectorCampos.getContentPane().add(selectMvtoEquipo_mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 190, -1));

        add(InternalFrameSelectorCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 760));

        titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo.setText("PROGRAMAR PROCESAMIENTO DE DATOS EN (MVTO EQUIPO).");
        add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 900, 30));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 1290, 570));

        jProgressBar1.setStringPainted(true);
        add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 1200, 20));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 1290, 10));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Img/selectorCampo.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 130, 50, 40));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Campos");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 110, -1, 30));

        time.setToolTipText("");
        add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 80, 30));

        iniciar.setMnemonic('T');
        iniciar.setText("INICIAR");
        iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarActionPerformed(evt);
            }
        });
        add(iniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 80, 30));

        parar.setText("PARAR");
        parar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pararActionPerformed(evt);
            }
        });
        add(parar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 80, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Tiempo de ejecución:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 160, 30));

        rango.setToolTipText("");
        add(rango, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 100, 30));

        modalidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DAY", "HOUR", "MINUTE", "SECOND" }));
        modalidad.setSelectedIndex(1);
        modalidad.setToolTipText("");
        add(modalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, 130, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("en el pasado.");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 50, 520, 30));

        modalidadHilo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SECOND", "MINUTE", "HOUR", "DAY" }));
        modalidadHilo.setToolTipText("");
        add(modalidadHilo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, 80, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Procesar datos desde fecha actual del sistema a ");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 520, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 1290, 130));

        add(pageJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 10, 40, 40));

        paginationPanel.setBackground(new java.awt.Color(255, 255, 255));
        add(paginationPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 750, 1290, 70));
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        InternalFrameSelectorCampos.show(true);
    }//GEN-LAST:event_jLabel3MouseClicked

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

    private void selectMvtoEquipo_motonaveCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_motonaveCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_motonaveCodigoActionPerformed

    private void selectMvtoEquipo_articuloDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_articuloDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_articuloDescripcionActionPerformed

    private void selectMvtoEquipo_articuloCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_articuloCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_articuloCodigoActionPerformed

    private void selectAsignacion_equipoDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAsignacion_equipoDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAsignacion_equipoDescripcionActionPerformed

    private void selectMvtoEquipo_UsuarioInactividad_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_UsuarioInactividad_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_UsuarioInactividad_codigoActionPerformed

    private void selectMvtoEquipo_fechaFinDescargueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_fechaFinDescargueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_fechaFinDescargueActionPerformed

    private void selectMvtoEquipo_fechaInicioDescargueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_fechaInicioDescargueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_fechaInicioDescargueActionPerformed

    private void selectMvtoEquipo_laborRealizadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_laborRealizadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_laborRealizadaActionPerformed

    private void selectMvtoEquipo_proveedorEquipoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_proveedorEquipoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_proveedorEquipoNombreActionPerformed

    private void selectAsignacion_cantidadMinutos_ProgramadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAsignacion_cantidadMinutos_ProgramadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAsignacion_cantidadMinutos_ProgramadosActionPerformed

    private void selectAsignacion_fechaInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAsignacion_fechaInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAsignacion_fechaInicioActionPerformed

    private void selectMvtoEquipo_subcentroCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_subcentroCostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_subcentroCostoActionPerformed

    private void selectMvtoEquipo_motonaveDescripciónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_motonaveDescripciónActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_motonaveDescripciónActionPerformed

    private void selectAsignacion_equipoTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAsignacion_equipoTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAsignacion_equipoTipoActionPerformed

    private void selectMvtoEquipo_cantidadMinutosDescargueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMvtoEquipo_cantidadMinutosDescargueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectMvtoEquipo_cantidadMinutosDescargueActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        selectorCampoPorDefecto();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void selectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAllActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectAllActionPerformed

    private void selectAllItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectAllItemStateChanged
        if(selectAll.isSelected()){
            selectorCampoTodos();
        }else{
            selectorCampoNinguno();
        }
    }//GEN-LAST:event_selectAllItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        InternalFrameSelectorCampos.show(false);
        /*try {
            generarListadoMvtoCarbon();
        } catch (SQLException ex) {
            Logger.getLogger(MvtoEquipo_Procesar_Programado.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame InternalFrameSelectorCampos;
    private javax.swing.JButton iniciar;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JComboBox<String> modalidad;
    private javax.swing.JComboBox<String> modalidadHilo;
    private javax.swing.JComboBox<Integer> pageJComboBox;
    public javax.swing.JPanel paginationPanel;
    private javax.swing.JButton parar;
    private javax.swing.JComboBox<String> rango;
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
    private javax.swing.JRadioButton selectMvtoEquipo_CausalInactividad;
    private javax.swing.JRadioButton selectMvtoEquipo_DesdeCarbon;
    private javax.swing.JRadioButton selectMvtoEquipo_Inactividad;
    private javax.swing.JRadioButton selectMvtoEquipo_MotivoParada;
    private javax.swing.JRadioButton selectMvtoEquipo_Recobro;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioAutorizaRecobro_codigo;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioAutorizaRecobro_nombre;
    private javax.swing.JRadioButton selectMvtoEquipo_UsuarioAutorizaRecobro_observacion;
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
    private javax.swing.JRadioButton selectMvtoEquipo_motonaveDescripción;
    private javax.swing.JRadioButton selectMvtoEquipo_numeroOrden;
    private javax.swing.JRadioButton selectMvtoEquipo_observacion;
    private javax.swing.JRadioButton selectMvtoEquipo_proveedorEquipoNit;
    private javax.swing.JRadioButton selectMvtoEquipo_proveedorEquipoNombre;
    private javax.swing.JRadioButton selectMvtoEquipo_subcentroCosto;
    private javax.swing.JTable tabla;
    private javax.swing.JComboBox<String> time;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel titulo12;
    private javax.swing.JLabel titulo13;
    private javax.swing.JLabel titulo18;
    private javax.swing.JLabel titulo19;
    private javax.swing.JLabel titulo2;
    private javax.swing.JLabel titulo20;
    private javax.swing.JLabel titulo21;
    private javax.swing.JLabel titulo23;
    // End of variables declaration//GEN-END:variables
    
    public void tabla_Listar() throws SQLException{
        tabla.setModel(crearModeloDeTabla());
        listado=new ControlDB_MvtoEquipo(tipoConexion).buscar_mvtoEquiposActivosGeneralProgramados(modalidad.getSelectedItem().toString(),rango.getSelectedItem().toString());
        if(listado != null){
            proveedorDeDatos = crearProveedorDeDatos(listado); 
            paginadorDeTabla = new PaginadorDeTabla(tabla, proveedorDeDatos, new int[]{5, 10, 20, 50, 75, 100}, 10);
            paginadorDeTabla.crearListadoDeFilasPermitidas(this.paginationPanel);
            pageJComboBox = paginadorDeTabla.getComboBoxPage();
            events();
            pageJComboBox.setSelectedItem(Integer.parseInt("50")); 
        }
    }
    public void generarListadoMvtoCarbon() throws SQLException{
        tabla_Listar();
    }
    public void selectorCampoPorDefecto(){
        selectMvtoEquipo_codigo.setSelected(true);
        selectMvtoEquipo_mes.setSelected(true);
        selectMvtoEquipo_fechaRegistro.setSelected(true);
        selectMvtoEquipo_proveedorEquipoNit.setSelected(false);
        selectMvtoEquipo_proveedorEquipoNombre.setSelected(true);
        selectMvtoEquipo_numeroOrden.setSelected(true);
        selectMvtoEquipo_centroOperacion.setSelected(true);
        selectMvtoEquipo_subcentroCosto.setSelected(true);
        selectMvtoEquipo_centroCostoAxiliarOrigen.setSelected(true);
        selectMvtoEquipo_centroCostoAuxiliarDestino.setSelected(true);
        selectMvtoEquipo_centroCosto.setSelected(true);
        selectMvtoEquipo_centroCostoMayor.setSelected(true);
        selectMvtoEquipo_laborRealizada.setSelected(true);
        selectMvtoEquipo_clienteCodigo.setSelected(false);
        selectMvtoEquipo_clienteNombre.setSelected(true);
        selectMvtoEquipo_articuloCodigo.setSelected(false);
        selectMvtoEquipo_articuloDescripcion.setSelected(true);
        selectMvtoEquipo_motonaveCodigo.setSelected(false);
        selectMvtoEquipo_motonaveDescripción.setSelected(true);
        selectMvtoEquipo_fechaInicioDescargue.setSelected(true);
        selectMvtoEquipo_fechaFinDescargue.setSelected(true);
        selectMvtoEquipo_cantidadMinutosDescargue.setSelected(true);
        selectMvtoEquipo_ValorHoraEquipo.setSelected(true);
        selectMvtoEquipo_costoTotal.setSelected(true);
        selectMvtoEquipo_Recobro.setSelected(true);
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
        selectMvtoEquipo_estado.setSelected(true);
        selectMvtoEquipo_DesdeCarbon.setSelected(false);
        selectAsignacion_equipoCodigo.setSelected(true);
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
    }
    public void selectorCampoTodos(){
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
        selectMvtoEquipo_motonaveDescripción.setSelected(true);
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

    }
    public void selectorCampoNinguno(){
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
        selectMvtoEquipo_motonaveDescripción.setSelected(false);
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
    } 
    public final void events(){
       pageJComboBox.addActionListener(this);
       tabla.getModel().addTableModelListener(this);
    }
    private ModeloDeTabla crearModeloDeTabla() {
        return new ModeloDeTabla<MvtoEquipo>() {            
            @Override
            public Object getValueAt(MvtoEquipo listado1, int columnas) {
               switch(encabezadoTabla.get(columnas)){
                    case "Código":{
                       return listado1.getCodigo();
                    }
                    case "MES":{
                       return getMes(listado1.getMes());
                    }
                    case "Fecha_Registro":{
                       return listado1.getFechaRegistro();
                    }
                    case "ProveedorEquipo_NIT":{
                       return listado1.getProveedorEquipo().getNit();
                    }
                    case "ProveedorEquipo_Nombre":{
                       return listado1.getProveedorEquipo().getDescripcion();
                    }
                    case "Número_Orden":{
                       return listado1.getNumeroOrden();
                    }
                    case "Centro_Operación":{
                       return listado1.getCentroOperacion().getDescripcion();
                    }
                    case "Subcentro_Costo":{
                       return listado1.getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion();
                    }
                    case "Centro_Costo_Auxiliar_Origen":{
                       return listado1.getCentroCostoAuxiliar().getDescripcion();
                    }
                    case "Centro_Costo_Auxiliar_Destino":{
                       return listado1.getCentroCostoAuxiliarDestino().getDescripcion();
                    }
                    case "Centro_Costo":{
                       return listado1.getCentroCosto().getDescripción();
                    }
                    case "Centro_Costo_Mayor":{
                       return listado1.getCentroCostoMayor().getDescripcion();
                    }
                    case "Labor_Realizada":{
                       return listado1.getLaborRealizada().getDescripcion();
                    }
                    case "Cliente_Código":{
                       return listado1.getCliente().getCodigo();
                    }
                    case "Cliente_Nombre":{
                       return listado1.getCliente().getDescripcion();
                    }
                    case "Artículo_Código":{
                       return listado1.getArticulo().getCodigo();
                    }
                    case "Artículo_Nombre":{
                       return listado1.getArticulo().getDescripcion();
                    }
                    case "Motonave_Código":{
                       return listado1.getMotonave().getCodigo();
                    }
                    case "Motonave_Descripción":{
                       return listado1.getMotonave().getDescripcion();
                    }
                    case "FechaInicio_Actividad":{
                       return listado1.getFechaHoraInicio();
                    }
                    case "FechaFinalización_Actividad":{
                       return listado1.getFechaHoraFin();
                    }
                    case "Duración_Actividad(Minutos)":{
                       return listado1.getTotalMinutos();
                    }
                    case "Valor_Hora":{
                       return listado1.getValorHora();
                    }
                    case "Costo_Total":{
                       return listado1.getCostoTotal();
                    }
                    case "Recobro":{
                       return listado1.getRecobro().getDescripcion();
                    }
                    case "UsuarioQuienRegistra_Código":{
                       return listado1.getUsuarioQuieRegistra().getCodigo();
                    }
                    case "UsuarioQuienRegistra_Nombre":{
                       return listado1.getUsuarioQuieRegistra().getNombres()+" "+listado1.getUsuarioQuieRegistra().getApellidos();
                    }
                    case "UsuarioQuienAutoriza_Código":{
                       return listado1.getUsuarioAutorizaRecobro().getCodigo();
                    }
                    case "UsuarioQuienAutoriza_Nombre":{
                       return listado1.getUsuarioAutorizaRecobro().getNombres()+" "+listado1.getUsuarioAutorizaRecobro().getApellidos();
                    }
                    case "Autorización_Recobro":{
                       return listado1.getAutorizacionRecobro().getDescripcion();
                    }
                    case "Observación_Autorización_Recobro":{
                       return listado1.getObservacionAutorizacion();
                    }
                    case "Inactividad":{
                       return listado1.getInactividad();
                    }
                    case "Causal_Inactividad":{
                       return listado1.getCausaInactividad().getDescripcion();
                    }
                    case "UsuarioQuienInactiva_Código":{
                       return listado1.getUsuarioInactividad().getCodigo();
                    }
                    case "UsuarioQuienInactiva_Nombre":{
                       return listado1.getUsuarioInactividad().getNombres();
                    }
                    case "Motivo_Parada":{
                       return listado1.getMotivoParada().getDescripcion();
                    }
                    case "MvtoEquipo_Observación":{
                       return listado1.getObservacionMvtoEquipo();
                    }
                    case "Estado":{
                       return listado1.getEstado();
                    }
                    case "Desde_Carbón":{
                       return listado1.getDesdeCarbon();
                    }
                    case "Equipo_Código":{
                       return listado1.getAsignacionEquipo().getEquipo().getCodigo();
                    }
                    case "Equipo_Tipo":{
                       return listado1.getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion();
                    }
                    case "Equipo_Marca":{
                       return listado1.getAsignacionEquipo().getEquipo().getMarca();
                    }
                    case "Equipo_Modelo":{
                       return listado1.getAsignacionEquipo().getEquipo().getModelo();
                    }
                    case "Equipo_Descripción":{
                       return listado1.getAsignacionEquipo().getEquipo().getDescripcion()+" "+listado1.getAsignacionEquipo().getEquipo().getModelo();
                    }
                    case "Equipo_Pertenencia":{
                       return listado1.getAsignacionEquipo().getPertenencia().getDescripcion();
                    }
                    case "Asignación_Código":{
                       return listado1.getAsignacionEquipo().getCodigo();
                    }
                    case "Asignación_FechaRegistro":{
                       return listado1.getAsignacionEquipo().getFechaRegistro();
                    }
                    case "Asignación_FechaInicioActividad":{
                       return listado1.getAsignacionEquipo().getFechaHoraInicio();
                    }
                    case "Asignación_FechaFinalizaciónActividad":{
                       return listado1.getAsignacionEquipo().getFechaHoraFin();
                    }
                    case "Asignación_CantidadMinutosProgramados":{
                       return listado1.getAsignacionEquipo().getCantidadMinutosProgramados();
                    }
                     
               }
               return null;
            }

            @Override
            public String getColumnName(int columnas) {
                /*switch (columnas) {
                    case 0:
                        return "Id";
                    case 1:
                        return "Descripción";
                    case 2:
                        return "Código ERP";
                    case 3:
                        return "Unidad Negocio";
                }
                return null;    */
                return encabezadoTabla.get(columnas);
            }

            @Override
            public int getColumnCount() {
                //return 4;
                return encabezadoTabla.size();
            }

        }; 
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object evt = e.getSource();
        paginadorDeTabla.eventCombobBox(pageJComboBox);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void tableChanged(TableModelEvent e) {
        paginadorDeTabla.refreshPageButtonPanel();
       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private ProveedorDeDatosDePaginacion<MvtoEquipo> crearProveedorDeDatos(final ArrayList<MvtoEquipo> model) {
        //Obtenemos el listado de registros existentes haciendo una consulta a la base de datos.
        
        //Retornamos un interfaz de tipo ProveedorDeDatosDePaginacion en la cual sobreescribimos sus metodos abtractos
        //1 metodo: obtenemos el numero total de registros agregados al JTable.
        //2 metodo: obtenemos una subLista la cual será mostrada en el JTable, seria nuestra pagina actual.
        return new ProveedorDeDatosDePaginacion<MvtoEquipo>() {
            @Override
            public int getTotalRowCount() {
                return model.size();
            }

            @Override
            public List<MvtoEquipo> getRows(int startIndex, int endIndex) {
                return model.subList(startIndex, endIndex);
            }
        };
    }
    public void validarSeleccionCampos(){
        encabezadoTabla= new ArrayList<>();
        if(selectMvtoEquipo_codigo.isSelected()){
                encabezadoTabla.add("Código");
        }
        if(selectMvtoEquipo_mes.isSelected()){
                encabezadoTabla.add("MES");
        }
        if(selectMvtoEquipo_fechaRegistro.isSelected()){
                encabezadoTabla.add("Fecha_Registro");
        }
        if(selectAsignacion_equipoCodigo.isSelected()){
                encabezadoTabla.add("Equipo_Código");
        }
        if(selectAsignacion_equipoTipo.isSelected()){
                encabezadoTabla.add("Equipo_Tipo");
        }
        if(selectAsignacion_equipoMarca.isSelected()){
                encabezadoTabla.add("Equipo_Marca");
        }
        if(selectAsignacion_equipoModelo.isSelected()){
                encabezadoTabla.add("Equipo_Modelo");
        }
        if(selectAsignacion_equipoDescripcion.isSelected()){
                encabezadoTabla.add("Equipo_Descripción");
        }
        if(selectMvtoEquipo_proveedorEquipoNit.isSelected()){
                encabezadoTabla.add("ProveedorEquipo_NIT");
        }
        if(selectMvtoEquipo_proveedorEquipoNombre.isSelected()){
                encabezadoTabla.add("ProveedorEquipo_Nombre");
        }
        if(selectAsignacion_equipoPertenencia.isSelected()){
                encabezadoTabla.add("Equipo_Pertenencia");
        }
        if(selectMvtoEquipo_numeroOrden.isSelected()){
                encabezadoTabla.add("Número_Orden");
        }
        if(selectMvtoEquipo_centroOperacion.isSelected()){
                encabezadoTabla.add("Centro_Operación");
        }
        if(selectMvtoEquipo_subcentroCosto.isSelected()){
                encabezadoTabla.add("Subcentro_Costo");
        }
        if(selectMvtoEquipo_centroCostoAxiliarOrigen.isSelected()){
                encabezadoTabla.add("Centro_Costo_Auxiliar_Origen");
        }
        if(selectMvtoEquipo_centroCostoAuxiliarDestino.isSelected()){
                encabezadoTabla.add("Centro_Costo_Auxiliar_Destino");
        }
        if(selectMvtoEquipo_centroCosto.isSelected()){
                encabezadoTabla.add("Centro_Costo");
        }
        if(selectMvtoEquipo_centroCostoMayor.isSelected()){
                encabezadoTabla.add("Centro_Costo_Mayor");
        }
        if(selectMvtoEquipo_laborRealizada.isSelected()){
                encabezadoTabla.add("Labor_Realizada");
        }
        if(selectMvtoEquipo_clienteCodigo.isSelected()){
                encabezadoTabla.add("Cliente_Código");
        }
        if(selectMvtoEquipo_clienteNombre.isSelected()){
                encabezadoTabla.add("Cliente_Nombre");
        }
        if(selectMvtoEquipo_articuloCodigo.isSelected()){
                encabezadoTabla.add("Artículo_Código");
        }
        if(selectMvtoEquipo_articuloDescripcion.isSelected()){
                encabezadoTabla.add("Artículo_Nombre");
        }
        if(selectMvtoEquipo_motonaveCodigo.isSelected()){
                encabezadoTabla.add("Motonave_Código");
        }
        if(selectMvtoEquipo_motonaveDescripción.isSelected()){
                encabezadoTabla.add("Motonave_Descripción");
        }
        if(selectMvtoEquipo_Recobro.isSelected()){
                encabezadoTabla.add("Recobro");
        }
        if(selectMvtoEquipo_fechaInicioDescargue.isSelected()){
                encabezadoTabla.add("FechaInicio_Actividad");
        }
        if(selectMvtoEquipo_fechaFinDescargue.isSelected()){
                encabezadoTabla.add("FechaFinalización_Actividad");
        }
        if(selectMvtoEquipo_cantidadMinutosDescargue.isSelected()){
                encabezadoTabla.add("Duración_Actividad(Minutos)");
        }
        if(selectMvtoEquipo_ValorHoraEquipo.isSelected()){
                encabezadoTabla.add("Valor_Hora");
        }
        if(selectMvtoEquipo_costoTotal.isSelected()){
                encabezadoTabla.add("Costo_Total");
        }
        if(selectMvtoEquipo_observacion.isSelected()){
                encabezadoTabla.add("MvtoEquipo_Observación");
        }
        if(selectMvtoEquipo_estado.isSelected()){
                encabezadoTabla.add("Estado");
        }
        if(selectMvtoEquipo_DesdeCarbon.isSelected()){
                encabezadoTabla.add("Desde_Carbón");
        }  
        if(selectMvtoEquipo_UsuarioRegistraMvto_codigo.isSelected()){
                encabezadoTabla.add("UsuarioQuienRegistra_Código");
        }
        if(selectMvtoEquipo_UsuarioRegistraMvto_nombre.isSelected()){
                encabezadoTabla.add("UsuarioQuienRegistra_Nombre");
        }
        if(selectMvtoEquipo_UsuarioAutorizaRecobro_codigo.isSelected()){
                encabezadoTabla.add("UsuarioQuienAutoriza_Código");
        }
        if(selectMvtoEquipo_UsuarioAutorizaRecobro_nombre.isSelected()){
                encabezadoTabla.add("UsuarioQuienAutoriza_Nombre");
        }
        if(selectMvtoEquipo_autorizacionRecobro.isSelected()){
                encabezadoTabla.add("Autorización_Recobro");
        }
        if(selectMvtoEquipo_UsuarioAutorizaRecobro_observacion.isSelected()){
                encabezadoTabla.add("Observación_Autorización_Recobro");
        }
        if(selectMvtoEquipo_Inactividad.isSelected()){
                encabezadoTabla.add("Inactividad");
        }
        if(selectMvtoEquipo_CausalInactividad.isSelected()){
                encabezadoTabla.add("Causal_Inactividad");
        }
        if(selectMvtoEquipo_UsuarioInactividad_codigo.isSelected()){
                encabezadoTabla.add("UsuarioQuienInactiva_Código");
        }
        if(selectMvtoEquipo_UsuarioInactividad_nombre.isSelected()){
                encabezadoTabla.add("UsuarioQuienInactiva_Nombre");
        }
        if(selectMvtoEquipo_MotivoParada.isSelected()){
                encabezadoTabla.add("Motivo_Parada");
        }

        if(selectAsignacion_codigo.isSelected()){
                encabezadoTabla.add("Asignación_Código");
        }
        if(selectAsignacion_fechaRegistro.isSelected()){
                encabezadoTabla.add("Asignación_FechaRegistro");
        }
        if(selectAsignacion_fechaInicio.isSelected()){
                encabezadoTabla.add("Asignación_FechaInicioActividad");
        }
        if(selectAsignacion_fechaFinalizacion.isSelected()){
                encabezadoTabla.add("Asignación_FechaFinalizaciónActividad");
        }
        if(selectAsignacion_cantidadMinutos_Programados.isSelected()){
                encabezadoTabla.add("Asignación_CantidadMinutosProgramados");
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
                } catch (InterruptedException ex) {
                    Logger.getLogger(MvtoEquipo_Procesar_Programado.class.getName()).log(Level.SEVERE, null, ex);
                }
             
                try {
                    paginationPanel.removeAll();
                    validarSeleccionCampos();
                    generarListadoMvtoCarbon();
                    resizeColumnWidth(tabla);
                } catch (SQLException ex) {
                    Logger.getLogger(MvtoEquipo_Procesar_Programado.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(listado!=null){
                    jProgressBar1.show(true);
                    System.out.println("Hay "+ listado.size()+" registros");
                    Worker_MvtonEquipo  worker = new Worker_MvtonEquipo (jProgressBar1,listado, user,tipoConexion);
                    worker.execute();
                }
          }
        }
    }  
    
}
