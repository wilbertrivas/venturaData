package Sistema.View;
  
import Sistema.Controller.ControlDB_Perfil;
import Sistema.Controller.ControlDB_Permiso;
import Sistema.Model.Perfil;
import Sistema.Model.Permisos;
import Sistema.Model.Usuario;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public final class Permiso_Usuario extends javax.swing.JPanel {
    Perfil perfil;
    Usuario user;
    private final String tipoConexion;
    public Permiso_Usuario(Usuario u,String tipoConexion) throws SQLException {
        initComponents();
        user= u;
        this.tipoConexion= tipoConexion;
        perfil= new Perfil();
        //desmarcarTodosCheckInterfaz();
        desmarcarTodosCheckInterfaz();
        deshabilitarTodosCheckInterfaz();
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seleccionar = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        jLabel7 = new javax.swing.JLabel();
        valorBusqueda = new javax.swing.JTextField();
        buscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        limpiar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        todos = new javax.swing.JCheckBox();
        perfilSeleccion_codigo = new javax.swing.JLabel();
        actualizar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        perfilSeleccion_Nombre = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Panel_Menu_Programacion = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        PROGRAMACION_EQUIPOS_ACTIVAR = new javax.swing.JCheckBox();
        SOLICITUD_EQUIPOS_REGISTRAR = new javax.swing.JCheckBox();
        ASIGNACION_EQUIPOS_REGISTRAR = new javax.swing.JCheckBox();
        ASIGNACION_EQUIPOS_CONFIRMACION = new javax.swing.JCheckBox();
        PROGRAMACION_EQUIPOS_CONSULTAR = new javax.swing.JCheckBox();
        PROGRAMACION_EQUIPOS_INACTIVAR = new javax.swing.JCheckBox();
        jSeparator7 = new javax.swing.JSeparator();
        PROGRAMACION_EQUIPOS_DIRECTA = new javax.swing.JCheckBox();
        ASIGNACION_EQUIPOS_EDITAR = new javax.swing.JCheckBox();
        SOLICITUD_EQUIPOS_EDITAR = new javax.swing.JCheckBox();
        Panel_Menu_ModuloEquipo = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS = new javax.swing.JCheckBox();
        MODULO_EQUIPO_AUTORIZAR_RECOBRO = new javax.swing.JCheckBox();
        MODULO_EQUIPO_ACTIVAR_REGISTROS = new javax.swing.JCheckBox();
        MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO = new javax.swing.JCheckBox();
        jSeparator11 = new javax.swing.JSeparator();
        MODULO_EQUIPO_INACTIVAR_REGISTROS = new javax.swing.JCheckBox();
        MODULO_EQUIPO_GENERAR_MATRIZ = new javax.swing.JCheckBox();
        MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO = new javax.swing.JCheckBox();
        MODULO_EQUIPO_PROCESAR_REGISTROS = new javax.swing.JCheckBox();
        MODULO_EQUIPO_GENERAR_DISTRIBUCION = new javax.swing.JCheckBox();
        MODULO_EQUIPO_AGREGAR_REGISTRO = new javax.swing.JCheckBox();
        Panel_Menu_ModuloAgua = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        Panel_Menu_ModuloPersonal = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        Panel_Menu_Auditoria = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        AUDITORIA_CONSULTAR = new javax.swing.JCheckBox();
        jSeparator17 = new javax.swing.JSeparator();
        Panel_Menu_AppMovil = new javax.swing.JPanel();
        jSeparator34 = new javax.swing.JSeparator();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jSeparator35 = new javax.swing.JSeparator();
        jSeparator36 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        APPMOVILCARBON_INICAR_DESCARGUE_VEHICULO = new javax.swing.JCheckBox();
        APPMOVILCARBON_INICIAR_CICLO_EQUIPO = new javax.swing.JCheckBox();
        APPMOVILCARBON_CERRAR_DESCARGUE_VEHICULO = new javax.swing.JCheckBox();
        APPMOVILCARBON_CERRAR_CICLO_EQUIPO = new javax.swing.JCheckBox();
        APPMOVILEQUIPO_CERRAR_CICLO_EQUIPO = new javax.swing.JCheckBox();
        APPMOVILEQUIPO_INICIAR_CICLO_EQUIPO = new javax.swing.JCheckBox();
        jLabel20 = new javax.swing.JLabel();
        Panel_Menu_Catalogo = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        PERFIL_REGISTRAR = new javax.swing.JCheckBox();
        PERFIL_CONSULTAR = new javax.swing.JCheckBox();
        PERFIL_ACTUALIZAR = new javax.swing.JCheckBox();
        USUARIO_ACTUALIZAR = new javax.swing.JCheckBox();
        jSeparator19 = new javax.swing.JSeparator();
        PERFIL_PERMISOS = new javax.swing.JCheckBox();
        USUARIO_REGISTRAR = new javax.swing.JCheckBox();
        USUARIO_CONSULTAR = new javax.swing.JCheckBox();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        CENTROOPERACION_REGISTRAR = new javax.swing.JCheckBox();
        CENTROOPERACION_CONSULTAR = new javax.swing.JCheckBox();
        CENTROOPERACION_ACTUALIZAR = new javax.swing.JCheckBox();
        jLabel62 = new javax.swing.JLabel();
        SUBCENTROOPERACION_REGISTRAR = new javax.swing.JCheckBox();
        SUBCENTROOPERACION_CONSULTAR = new javax.swing.JCheckBox();
        SUBCENTROOPERACION_ACTUALIZAR = new javax.swing.JCheckBox();
        CENTROCOSTOAUXILIAR_REGISTRAR = new javax.swing.JCheckBox();
        jLabel63 = new javax.swing.JLabel();
        CENTROCOSTOAUXILIAR_CONSULTAR = new javax.swing.JCheckBox();
        CENTROCOSTOAUXILIAR_ACTUALIZAR = new javax.swing.JCheckBox();
        CENTROCOSTO_REGISTRAR = new javax.swing.JCheckBox();
        jLabel64 = new javax.swing.JLabel();
        CENTROCOSTO_CONSULTAR = new javax.swing.JCheckBox();
        CENTROCOSTO_ACTUALIZAR = new javax.swing.JCheckBox();
        jSeparator21 = new javax.swing.JSeparator();
        jLabel65 = new javax.swing.JLabel();
        COMPANIA_REGISTRAR = new javax.swing.JCheckBox();
        COMPANIA_CONSULTAR = new javax.swing.JCheckBox();
        COMPANIA_ACTUALIZAR = new javax.swing.JCheckBox();
        jLabel67 = new javax.swing.JLabel();
        LABOR_REALIZADA_REGISTRAR = new javax.swing.JCheckBox();
        LABOR_REALIZADA_CONSULTAR = new javax.swing.JCheckBox();
        LABOR_REALIZADA_ACTUALIZAR = new javax.swing.JCheckBox();
        jLabel68 = new javax.swing.JLabel();
        MOTIVO_PARADA_REGISTRAR = new javax.swing.JCheckBox();
        MOTIVO_PARADA_CONSULTAR = new javax.swing.JCheckBox();
        MOTIVO_PARADA_ACTUALIZAR = new javax.swing.JCheckBox();
        jLabel69 = new javax.swing.JLabel();
        CLIENTE_REGISTRAR = new javax.swing.JCheckBox();
        CLIENTE_CONSULTAR = new javax.swing.JCheckBox();
        CLIENTE_REGISTRO_CCARGA = new javax.swing.JCheckBox();
        jLabel70 = new javax.swing.JLabel();
        MOTONAVE_REGISTRAR = new javax.swing.JCheckBox();
        MOTONAVE_CONSULTAR = new javax.swing.JCheckBox();
        MOTONAVE_REGISTRO_CCARGA = new javax.swing.JCheckBox();
        CLIENTE_ACTUALIZAR = new javax.swing.JCheckBox();
        MOTONAVE_ACTUALIZAR = new javax.swing.JCheckBox();
        jLabel71 = new javax.swing.JLabel();
        TRANSPORTADORA_REGISTRAR = new javax.swing.JCheckBox();
        TRANSPORTADORA_CONSULTAR = new javax.swing.JCheckBox();
        TRANSPORTADORA_REGISTRO_CCARGA = new javax.swing.JCheckBox();
        TRANSPORTADORA_ACTUALIZAR = new javax.swing.JCheckBox();
        jLabel72 = new javax.swing.JLabel();
        ARTICULO_CONSULTAR = new javax.swing.JCheckBox();
        ARTICULO_REGISTRO_CCARGA = new javax.swing.JCheckBox();
        ARTICULO_ACTUALIZAR = new javax.swing.JCheckBox();
        jLabel73 = new javax.swing.JLabel();
        CUADRILLA_REGISTRAR = new javax.swing.JCheckBox();
        CUADRILLA_CONSULTAR = new javax.swing.JCheckBox();
        CUADRILLA_ACTUALIZAR = new javax.swing.JCheckBox();
        EQUIPO_REGISTRAR = new javax.swing.JCheckBox();
        EQUIPO_CONSULTAR = new javax.swing.JCheckBox();
        EQUIPO_ACTUALIZAR = new javax.swing.JCheckBox();
        EQUIPO_PERTENENCIA_REGISTRAR = new javax.swing.JCheckBox();
        EQUIPO_PERTENENCIA_CONSULTAR = new javax.swing.JCheckBox();
        EQUIPO_PERTENENCIA_ACTUALIZAR = new javax.swing.JCheckBox();
        EQUIPO_PROVEEDOR_REGISTRAR = new javax.swing.JCheckBox();
        EQUIPO_PROVEEDOR_CONSULTAR = new javax.swing.JCheckBox();
        EQUIPO_PROVEEDOR_ACTUALIZAR = new javax.swing.JCheckBox();
        EQUIPO_TIPO_REGISTRAR = new javax.swing.JCheckBox();
        EQUIPO_TIPO_CONSULTAR = new javax.swing.JCheckBox();
        EQUIPO_TIPO_ACTUALIZAR = new javax.swing.JCheckBox();
        EQUIPO_ERP = new javax.swing.JCheckBox();
        EQUIPO_TARIFAS_REGISTRAR = new javax.swing.JCheckBox();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        ARTICULO_REGISTRAR = new javax.swing.JCheckBox();
        jLabel80 = new javax.swing.JLabel();
        CAUSA_INACTIVIDAD_REGISTRAR = new javax.swing.JCheckBox();
        CAUSA_INACTIVIDAD_CONSULTAR = new javax.swing.JCheckBox();
        CAUSA_INACTIVIDAD_ACTUALIZAR = new javax.swing.JCheckBox();
        jSeparator42 = new javax.swing.JSeparator();
        jLabel83 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        CENTROCOSTOMAYOR_CONSULTAR = new javax.swing.JCheckBox();
        CENTROCOSTOMAYOR_ACTUALIZAR = new javax.swing.JCheckBox();
        CENTROCOSTOMAYOR_REGISTRAR = new javax.swing.JCheckBox();
        jSeparator61 = new javax.swing.JSeparator();
        jSeparator62 = new javax.swing.JSeparator();
        jSeparator63 = new javax.swing.JSeparator();
        jSeparator64 = new javax.swing.JSeparator();
        jSeparator65 = new javax.swing.JSeparator();
        jLabel84 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        cuadradoFondo = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        cuadradoFondo3 = new javax.swing.JLabel();
        cuadradoFondo4 = new javax.swing.JLabel();
        cuadradoFondo5 = new javax.swing.JLabel();
        cuadradoFondo7 = new javax.swing.JLabel();
        cuadradoFondo9 = new javax.swing.JLabel();
        cuadradoFondo10 = new javax.swing.JLabel();
        cuadradoFondo11 = new javax.swing.JLabel();
        cuadradoFondo12 = new javax.swing.JLabel();
        jSeparator46 = new javax.swing.JSeparator();
        jSeparator47 = new javax.swing.JSeparator();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        cuadradoFondo13 = new javax.swing.JLabel();
        cuadradoFondo14 = new javax.swing.JLabel();
        cuadradoFondo15 = new javax.swing.JLabel();
        cuadradoFondo16 = new javax.swing.JLabel();
        cuadradoFondo17 = new javax.swing.JLabel();
        cuadradoFondo18 = new javax.swing.JLabel();
        cuadradoFondo19 = new javax.swing.JLabel();
        cuadradoFondo20 = new javax.swing.JLabel();
        cuadradoFondo21 = new javax.swing.JLabel();
        jSeparator66 = new javax.swing.JSeparator();
        jSeparator67 = new javax.swing.JSeparator();
        jSeparator68 = new javax.swing.JSeparator();
        jSeparator69 = new javax.swing.JSeparator();
        jSeparator48 = new javax.swing.JSeparator();
        jLabel66 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        cuadradoFondo6 = new javax.swing.JLabel();
        cuadradoFondo8 = new javax.swing.JLabel();
        Panel_Menu_DescargueCarbón = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS = new javax.swing.JCheckBox();
        MODULO_CARBON_INACTIVAR_REGISTRO = new javax.swing.JCheckBox();
        MODULO_CARBON_ACTIVAR_REGISTRO = new javax.swing.JCheckBox();
        MODULO_CARBON_GENERAR_MATRIZ = new javax.swing.JCheckBox();
        jSeparator9 = new javax.swing.JSeparator();
        MODULO_CARBON_PROCESAR_REGISTROS = new javax.swing.JCheckBox();
        MODULO_CARBON_LAVADO_VEHICULOS = new javax.swing.JCheckBox();
        MODULO_CARBON_AGREGAR_REGISTRO = new javax.swing.JCheckBox();
        MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO = new javax.swing.JCheckBox();
        MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO = new javax.swing.JCheckBox();
        MODULO_CARBON_GENERAR_DISTRIBUCION = new javax.swing.JCheckBox();

        Editar.setText("Seleccionar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        Seleccionar.add(Editar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("PERMISOS DEL PERFIL");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 240, 30));

        valorBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                valorBusquedaKeyPressed(evt);
            }
        });
        add(valorBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 130, 40));

        buscar.setBackground(new java.awt.Color(255, 255, 255));
        buscar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultar.png"))); // NOI18N
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        add(buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 70, 40));

        tabla = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tabla.setBackground(new java.awt.Color(153, 204, 255));
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
        tabla.setFocusable(false);
        tabla.setGridColor(new java.awt.Color(0, 204, 204));
        tabla.setSelectionBackground(new java.awt.Color(255, 102, 102));
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 260, 370));

        limpiar.setBackground(new java.awt.Color(255, 255, 255));
        limpiar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/clean.png"))); // NOI18N
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });
        add(limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 60, 40));

        jLabel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INFORMACION DEL USUARIO", 2, 0, new java.awt.Font("Adobe Arabic", 1, 14))); // NOI18N
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 790, 0));

        todos.setBackground(new java.awt.Color(255, 255, 255));
        todos.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        todos.setText("Seleccionar Todos");
        todos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                todosStateChanged(evt);
            }
        });
        todos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todosActionPerformed(evt);
            }
        });
        add(todos, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 50, -1, -1));

        perfilSeleccion_codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        perfilSeleccion_codigo.setForeground(new java.awt.Color(102, 102, 102));
        add(perfilSeleccion_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 150, 30));

        actualizar.setBackground(new java.awt.Color(255, 255, 255));
        actualizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Ingresar.png"))); // NOI18N
        actualizar.setText("ACTUALIZAR ");
        actualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actualizarMouseClicked(evt);
            }
        });
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });
        add(actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 50, 150, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("BUSCAR PERFIL:");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 30));

        perfilSeleccion_Nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        perfilSeleccion_Nombre.setForeground(new java.awt.Color(102, 102, 102));
        add(perfilSeleccion_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, 320, 30));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setText("Descripción:");
        add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 90, 30));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setText("Perfil Seleccionado:");
        add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 170, 30));

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel81.setText("Código:");
        add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 60, 30));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setAutoscrolls(true);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        Panel_Menu_Programacion.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Menu_Programacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Panel_Menu_Programacion.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 260, 10));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("EQUIPO");
        Panel_Menu_Programacion.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 310, 30));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menu_programacion.png"))); // NOI18N
        Panel_Menu_Programacion.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 40, 30));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("PERMISOS DE PROGRAMACIÓN");
        Panel_Menu_Programacion.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 310, 30));
        Panel_Menu_Programacion.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 670, 30));

        PROGRAMACION_EQUIPOS_ACTIVAR.setBackground(new java.awt.Color(255, 255, 255));
        PROGRAMACION_EQUIPOS_ACTIVAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        PROGRAMACION_EQUIPOS_ACTIVAR.setText("PROGRAMACION_EQUIPOS_ACTIVAR");
        Panel_Menu_Programacion.add(PROGRAMACION_EQUIPOS_ACTIVAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 370, 30));

        SOLICITUD_EQUIPOS_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        SOLICITUD_EQUIPOS_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        SOLICITUD_EQUIPOS_REGISTRAR.setText("SOLICITUD_EQUIPOS_REGISTRAR");
        Panel_Menu_Programacion.add(SOLICITUD_EQUIPOS_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 260, 30));

        ASIGNACION_EQUIPOS_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        ASIGNACION_EQUIPOS_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        ASIGNACION_EQUIPOS_REGISTRAR.setText("ASIGNACION_EQUIPOS_REGISTRAR");
        ASIGNACION_EQUIPOS_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ASIGNACION_EQUIPOS_REGISTRARActionPerformed(evt);
            }
        });
        Panel_Menu_Programacion.add(ASIGNACION_EQUIPOS_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 240, 30));

        ASIGNACION_EQUIPOS_CONFIRMACION.setBackground(new java.awt.Color(255, 255, 255));
        ASIGNACION_EQUIPOS_CONFIRMACION.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        ASIGNACION_EQUIPOS_CONFIRMACION.setText("ASIGNACION_EQUIPOS_CONFIRMACION");
        Panel_Menu_Programacion.add(ASIGNACION_EQUIPOS_CONFIRMACION, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 270, 30));

        PROGRAMACION_EQUIPOS_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        PROGRAMACION_EQUIPOS_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        PROGRAMACION_EQUIPOS_CONSULTAR.setText("PROGRAMACION_EQUIPOS_CONSULTAR");
        Panel_Menu_Programacion.add(PROGRAMACION_EQUIPOS_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 370, 30));

        PROGRAMACION_EQUIPOS_INACTIVAR.setBackground(new java.awt.Color(255, 255, 255));
        PROGRAMACION_EQUIPOS_INACTIVAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        PROGRAMACION_EQUIPOS_INACTIVAR.setText("PROGRAMACION_EQUIPOS_INACTIVAR");
        Panel_Menu_Programacion.add(PROGRAMACION_EQUIPOS_INACTIVAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 370, 30));
        Panel_Menu_Programacion.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 670, 10));

        PROGRAMACION_EQUIPOS_DIRECTA.setBackground(new java.awt.Color(255, 255, 255));
        PROGRAMACION_EQUIPOS_DIRECTA.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        PROGRAMACION_EQUIPOS_DIRECTA.setText("PROGRAMACION_EQUIPOS_DIRECTA");
        Panel_Menu_Programacion.add(PROGRAMACION_EQUIPOS_DIRECTA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 370, 30));

        ASIGNACION_EQUIPOS_EDITAR.setBackground(new java.awt.Color(255, 255, 255));
        ASIGNACION_EQUIPOS_EDITAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        ASIGNACION_EQUIPOS_EDITAR.setText("ASIGNACION_EQUIPOS_EDITAR");
        Panel_Menu_Programacion.add(ASIGNACION_EQUIPOS_EDITAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 370, 30));

        SOLICITUD_EQUIPOS_EDITAR.setBackground(new java.awt.Color(255, 255, 255));
        SOLICITUD_EQUIPOS_EDITAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        SOLICITUD_EQUIPOS_EDITAR.setText("SOLICITUD_EQUIPOS_EDITAR");
        Panel_Menu_Programacion.add(SOLICITUD_EQUIPOS_EDITAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 370, 30));

        jTabbedPane1.addTab("PROGRAMACIÓN", Panel_Menu_Programacion);

        Panel_Menu_ModuloEquipo.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Menu_ModuloEquipo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_equipoMovil.png"))); // NOI18N
        Panel_Menu_ModuloEquipo.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 40, 30));

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel50.setText("PERMISOS EN MODULO EQUIPO");
        Panel_Menu_ModuloEquipo.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 310, 30));
        Panel_Menu_ModuloEquipo.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 740, 20));

        MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS.setBackground(new java.awt.Color(255, 255, 255));
        MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS.setText("MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS");
        Panel_Menu_ModuloEquipo.add(MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 410, 30));

        MODULO_EQUIPO_AUTORIZAR_RECOBRO.setBackground(new java.awt.Color(255, 255, 255));
        MODULO_EQUIPO_AUTORIZAR_RECOBRO.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MODULO_EQUIPO_AUTORIZAR_RECOBRO.setText("MODULO_EQUIPO_AUTORIZAR_RECOBRO");
        Panel_Menu_ModuloEquipo.add(MODULO_EQUIPO_AUTORIZAR_RECOBRO, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 340, 30));

        MODULO_EQUIPO_ACTIVAR_REGISTROS.setBackground(new java.awt.Color(255, 255, 255));
        MODULO_EQUIPO_ACTIVAR_REGISTROS.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MODULO_EQUIPO_ACTIVAR_REGISTROS.setText("MODULO_EQUIPO_ACTIVAR_REGISTROS");
        Panel_Menu_ModuloEquipo.add(MODULO_EQUIPO_ACTIVAR_REGISTROS, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 340, 30));

        MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO.setBackground(new java.awt.Color(255, 255, 255));
        MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO.setText("MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO");
        Panel_Menu_ModuloEquipo.add(MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 340, 30));
        Panel_Menu_ModuloEquipo.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 760, 10));

        MODULO_EQUIPO_INACTIVAR_REGISTROS.setBackground(new java.awt.Color(255, 255, 255));
        MODULO_EQUIPO_INACTIVAR_REGISTROS.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MODULO_EQUIPO_INACTIVAR_REGISTROS.setText("MODULO_EQUIPO_INACTIVAR_REGISTROS");
        Panel_Menu_ModuloEquipo.add(MODULO_EQUIPO_INACTIVAR_REGISTROS, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 340, 30));

        MODULO_EQUIPO_GENERAR_MATRIZ.setBackground(new java.awt.Color(255, 255, 255));
        MODULO_EQUIPO_GENERAR_MATRIZ.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MODULO_EQUIPO_GENERAR_MATRIZ.setText("MODULO_EQUIPO_GENERAR_MATRIZ");
        Panel_Menu_ModuloEquipo.add(MODULO_EQUIPO_GENERAR_MATRIZ, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 340, 30));

        MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO.setBackground(new java.awt.Color(255, 255, 255));
        MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO.setText("MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO");
        Panel_Menu_ModuloEquipo.add(MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 340, 30));

        MODULO_EQUIPO_PROCESAR_REGISTROS.setBackground(new java.awt.Color(255, 255, 255));
        MODULO_EQUIPO_PROCESAR_REGISTROS.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MODULO_EQUIPO_PROCESAR_REGISTROS.setText("MODULO_EQUIPO_PROCESAR_REGISTROS");
        Panel_Menu_ModuloEquipo.add(MODULO_EQUIPO_PROCESAR_REGISTROS, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 340, 30));

        MODULO_EQUIPO_GENERAR_DISTRIBUCION.setBackground(new java.awt.Color(255, 255, 255));
        MODULO_EQUIPO_GENERAR_DISTRIBUCION.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MODULO_EQUIPO_GENERAR_DISTRIBUCION.setText("MODULO_EQUIPO_GENERAR_DISTRIBUCION");
        Panel_Menu_ModuloEquipo.add(MODULO_EQUIPO_GENERAR_DISTRIBUCION, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 340, 30));

        MODULO_EQUIPO_AGREGAR_REGISTRO.setBackground(new java.awt.Color(255, 255, 255));
        MODULO_EQUIPO_AGREGAR_REGISTRO.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MODULO_EQUIPO_AGREGAR_REGISTRO.setText("MODULO_EQUIPO_AGREGAR_REGISTRO");
        MODULO_EQUIPO_AGREGAR_REGISTRO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_EQUIPO_AGREGAR_REGISTROActionPerformed(evt);
            }
        });
        Panel_Menu_ModuloEquipo.add(MODULO_EQUIPO_AGREGAR_REGISTRO, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 340, 30));

        jTabbedPane1.addTab("MODULO_EQUIPO", Panel_Menu_ModuloEquipo);

        Panel_Menu_ModuloAgua.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Menu_ModuloAgua.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Agua.png"))); // NOI18N
        Panel_Menu_ModuloAgua.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 40, 30));

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel52.setText("PERMISOS EN MODULO AGUA");
        Panel_Menu_ModuloAgua.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 310, 30));
        Panel_Menu_ModuloAgua.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 670, 10));
        Panel_Menu_ModuloAgua.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 670, 10));

        jTabbedPane1.addTab("MODULO_AGUA", Panel_Menu_ModuloAgua);

        Panel_Menu_ModuloPersonal.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Menu_ModuloPersonal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_personal.png"))); // NOI18N
        Panel_Menu_ModuloPersonal.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 40, 30));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel54.setText("PERMISOS EN MODULO  DE PERSONAL");
        Panel_Menu_ModuloPersonal.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 310, 30));
        Panel_Menu_ModuloPersonal.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 670, 10));
        Panel_Menu_ModuloPersonal.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 670, 10));

        jTabbedPane1.addTab("MODULO_PERSONAL", Panel_Menu_ModuloPersonal);

        Panel_Menu_Auditoria.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Menu_Auditoria.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Auditoria.png"))); // NOI18N
        Panel_Menu_Auditoria.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 40, 30));

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel56.setText("PERMISOS EN AUDITORIA");
        Panel_Menu_Auditoria.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 310, 30));
        Panel_Menu_Auditoria.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 670, 10));

        AUDITORIA_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        AUDITORIA_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        AUDITORIA_CONSULTAR.setText("AUDITORIA_CONSULTAR");
        Panel_Menu_Auditoria.add(AUDITORIA_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 340, 30));
        Panel_Menu_Auditoria.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 670, 10));

        jTabbedPane1.addTab("AUDITORIA", Panel_Menu_Auditoria);

        Panel_Menu_AppMovil.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Menu_AppMovil.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Panel_Menu_AppMovil.add(jSeparator34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 260, 10));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setText("APP MOVIL");
        Panel_Menu_AppMovil.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 310, 30));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/movil.png"))); // NOI18N
        Panel_Menu_AppMovil.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 40, 30));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText("PERMISOS APLICACIÓN MOVIL");
        Panel_Menu_AppMovil.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 310, 30));
        Panel_Menu_AppMovil.add(jSeparator35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 670, 10));
        Panel_Menu_AppMovil.add(jSeparator36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 670, 10));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("CARBÓN:");
        Panel_Menu_AppMovil.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 70, 30));

        APPMOVILCARBON_INICAR_DESCARGUE_VEHICULO.setBackground(new java.awt.Color(255, 255, 255));
        APPMOVILCARBON_INICAR_DESCARGUE_VEHICULO.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        APPMOVILCARBON_INICAR_DESCARGUE_VEHICULO.setText("INICAR_DESCARGUE_VEHICULO");
        Panel_Menu_AppMovil.add(APPMOVILCARBON_INICAR_DESCARGUE_VEHICULO, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 200, 30));

        APPMOVILCARBON_INICIAR_CICLO_EQUIPO.setBackground(new java.awt.Color(255, 255, 255));
        APPMOVILCARBON_INICIAR_CICLO_EQUIPO.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        APPMOVILCARBON_INICIAR_CICLO_EQUIPO.setText("INICIAR_CICLO_EQUIPO");
        Panel_Menu_AppMovil.add(APPMOVILCARBON_INICIAR_CICLO_EQUIPO, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 200, 30));

        APPMOVILCARBON_CERRAR_DESCARGUE_VEHICULO.setBackground(new java.awt.Color(255, 255, 255));
        APPMOVILCARBON_CERRAR_DESCARGUE_VEHICULO.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        APPMOVILCARBON_CERRAR_DESCARGUE_VEHICULO.setText("CERRAR_DESCARGUE_VEHICULO");
        Panel_Menu_AppMovil.add(APPMOVILCARBON_CERRAR_DESCARGUE_VEHICULO, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 240, 30));

        APPMOVILCARBON_CERRAR_CICLO_EQUIPO.setBackground(new java.awt.Color(255, 255, 255));
        APPMOVILCARBON_CERRAR_CICLO_EQUIPO.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        APPMOVILCARBON_CERRAR_CICLO_EQUIPO.setText("CERRAR_CICLO_EQUIPO");
        Panel_Menu_AppMovil.add(APPMOVILCARBON_CERRAR_CICLO_EQUIPO, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 200, 30));

        APPMOVILEQUIPO_CERRAR_CICLO_EQUIPO.setBackground(new java.awt.Color(255, 255, 255));
        APPMOVILEQUIPO_CERRAR_CICLO_EQUIPO.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        APPMOVILEQUIPO_CERRAR_CICLO_EQUIPO.setText("CERRAR_CICLO_EQUIPO");
        Panel_Menu_AppMovil.add(APPMOVILEQUIPO_CERRAR_CICLO_EQUIPO, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 250, 30));

        APPMOVILEQUIPO_INICIAR_CICLO_EQUIPO.setBackground(new java.awt.Color(255, 255, 255));
        APPMOVILEQUIPO_INICIAR_CICLO_EQUIPO.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        APPMOVILEQUIPO_INICIAR_CICLO_EQUIPO.setText("INICIAR_CICLO_EQUIPO");
        Panel_Menu_AppMovil.add(APPMOVILEQUIPO_INICIAR_CICLO_EQUIPO, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 190, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("EQUIPO:");
        Panel_Menu_AppMovil.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 70, 30));

        jTabbedPane1.addTab("APP_MOVIL", Panel_Menu_AppMovil);

        Panel_Menu_Catalogo.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Menu_Catalogo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_equipoMovil.png"))); // NOI18N
        Panel_Menu_Catalogo.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 40, 30));

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 51, 51));
        jLabel58.setText("USUARIO");
        Panel_Menu_Catalogo.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 100, 30));

        PERFIL_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        PERFIL_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PERFIL_REGISTRAR.setText("R");
        PERFIL_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PERFIL_REGISTRARActionPerformed(evt);
            }
        });
        Panel_Menu_Catalogo.add(PERFIL_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 60, 20));

        PERFIL_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        PERFIL_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PERFIL_CONSULTAR.setText("C");
        Panel_Menu_Catalogo.add(PERFIL_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 60, 20));

        PERFIL_ACTUALIZAR.setBackground(new java.awt.Color(255, 255, 255));
        PERFIL_ACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        PERFIL_ACTUALIZAR.setText("A");
        Panel_Menu_Catalogo.add(PERFIL_ACTUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 60, 20));

        USUARIO_ACTUALIZAR.setBackground(new java.awt.Color(255, 255, 255));
        USUARIO_ACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        USUARIO_ACTUALIZAR.setText("A");
        Panel_Menu_Catalogo.add(USUARIO_ACTUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 170, 60, 20));
        Panel_Menu_Catalogo.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 1170, 10));

        PERFIL_PERMISOS.setBackground(new java.awt.Color(255, 255, 255));
        PERFIL_PERMISOS.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        PERFIL_PERMISOS.setText("PERMISOS");
        PERFIL_PERMISOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PERFIL_PERMISOSActionPerformed(evt);
            }
        });
        Panel_Menu_Catalogo.add(PERFIL_PERMISOS, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, 120, 20));

        USUARIO_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        USUARIO_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        USUARIO_REGISTRAR.setText("R");
        Panel_Menu_Catalogo.add(USUARIO_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, 60, 20));

        USUARIO_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        USUARIO_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        USUARIO_CONSULTAR.setText("C");
        Panel_Menu_Catalogo.add(USUARIO_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, 60, 20));

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setText("PERMISOS EN CATALOGOS");
        Panel_Menu_Catalogo.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 310, 30));

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 102, 102));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("OTROS");
        jLabel60.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 160, 30));

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 51, 51));
        jLabel61.setText("CENTRO DE OPERACION");
        Panel_Menu_Catalogo.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 200, 30));

        CENTROOPERACION_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        CENTROOPERACION_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CENTROOPERACION_REGISTRAR.setText("R");
        Panel_Menu_Catalogo.add(CENTROOPERACION_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 70, -1));

        CENTROOPERACION_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        CENTROOPERACION_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CENTROOPERACION_CONSULTAR.setText("C");
        Panel_Menu_Catalogo.add(CENTROOPERACION_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 210, 60, 20));

        CENTROOPERACION_ACTUALIZAR.setBackground(new java.awt.Color(255, 255, 255));
        CENTROOPERACION_ACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CENTROOPERACION_ACTUALIZAR.setText("A");
        Panel_Menu_Catalogo.add(CENTROOPERACION_ACTUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 210, 60, 20));

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(0, 51, 51));
        jLabel62.setText("SUBCENTRO OPERACION");
        Panel_Menu_Catalogo.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 200, 30));

        SUBCENTROOPERACION_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        SUBCENTROOPERACION_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        SUBCENTROOPERACION_REGISTRAR.setText("R");
        Panel_Menu_Catalogo.add(SUBCENTROOPERACION_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, 60, 20));

        SUBCENTROOPERACION_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        SUBCENTROOPERACION_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        SUBCENTROOPERACION_CONSULTAR.setText("C");
        Panel_Menu_Catalogo.add(SUBCENTROOPERACION_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, 50, 20));

        SUBCENTROOPERACION_ACTUALIZAR.setBackground(new java.awt.Color(255, 255, 255));
        SUBCENTROOPERACION_ACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        SUBCENTROOPERACION_ACTUALIZAR.setText("A");
        Panel_Menu_Catalogo.add(SUBCENTROOPERACION_ACTUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, 40, 20));

        CENTROCOSTOAUXILIAR_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        CENTROCOSTOAUXILIAR_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CENTROCOSTOAUXILIAR_REGISTRAR.setText("R");
        Panel_Menu_Catalogo.add(CENTROCOSTOAUXILIAR_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 290, 60, 20));

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(0, 51, 51));
        jLabel63.setText("CENTRO COSTO AUXILIAR");
        Panel_Menu_Catalogo.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 200, 30));

        CENTROCOSTOAUXILIAR_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        CENTROCOSTOAUXILIAR_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CENTROCOSTOAUXILIAR_CONSULTAR.setText("C");
        Panel_Menu_Catalogo.add(CENTROCOSTOAUXILIAR_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, 60, 20));

        CENTROCOSTOAUXILIAR_ACTUALIZAR.setBackground(new java.awt.Color(255, 255, 255));
        CENTROCOSTOAUXILIAR_ACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CENTROCOSTOAUXILIAR_ACTUALIZAR.setText("A");
        Panel_Menu_Catalogo.add(CENTROCOSTOAUXILIAR_ACTUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 290, 60, 20));

        CENTROCOSTO_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        CENTROCOSTO_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CENTROCOSTO_REGISTRAR.setText("R");
        Panel_Menu_Catalogo.add(CENTROCOSTO_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 370, 60, 20));

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(0, 51, 51));
        jLabel64.setText("CENTRO COSTO MAYOR");
        Panel_Menu_Catalogo.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 200, 30));

        CENTROCOSTO_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        CENTROCOSTO_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CENTROCOSTO_CONSULTAR.setText("C");
        Panel_Menu_Catalogo.add(CENTROCOSTO_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 370, 60, 20));

        CENTROCOSTO_ACTUALIZAR.setBackground(new java.awt.Color(255, 255, 255));
        CENTROCOSTO_ACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CENTROCOSTO_ACTUALIZAR.setText("A");
        Panel_Menu_Catalogo.add(CENTROCOSTO_ACTUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 370, 60, 20));

        jSeparator21.setOrientation(javax.swing.SwingConstants.VERTICAL);
        Panel_Menu_Catalogo.add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1960, 40, 10, 620));

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 51, 51));
        jLabel65.setText("COMPAÑIA");
        Panel_Menu_Catalogo.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 200, 30));

        COMPANIA_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        COMPANIA_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        COMPANIA_REGISTRAR.setText("R");
        Panel_Menu_Catalogo.add(COMPANIA_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 410, 60, 20));

        COMPANIA_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        COMPANIA_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        COMPANIA_CONSULTAR.setText("C");
        Panel_Menu_Catalogo.add(COMPANIA_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, 60, 20));

        COMPANIA_ACTUALIZAR.setBackground(new java.awt.Color(255, 255, 255));
        COMPANIA_ACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        COMPANIA_ACTUALIZAR.setText("A");
        Panel_Menu_Catalogo.add(COMPANIA_ACTUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 410, 60, 20));

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(0, 51, 51));
        jLabel67.setText("LABOR REALIZADA");
        Panel_Menu_Catalogo.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 200, 30));

        LABOR_REALIZADA_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        LABOR_REALIZADA_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        LABOR_REALIZADA_REGISTRAR.setText("R");
        Panel_Menu_Catalogo.add(LABOR_REALIZADA_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 450, 60, 20));

        LABOR_REALIZADA_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        LABOR_REALIZADA_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        LABOR_REALIZADA_CONSULTAR.setText("C");
        Panel_Menu_Catalogo.add(LABOR_REALIZADA_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 450, 60, 20));

        LABOR_REALIZADA_ACTUALIZAR.setBackground(new java.awt.Color(255, 255, 255));
        LABOR_REALIZADA_ACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        LABOR_REALIZADA_ACTUALIZAR.setText("A");
        Panel_Menu_Catalogo.add(LABOR_REALIZADA_ACTUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 450, 60, 20));

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(0, 51, 51));
        jLabel68.setText("MOTIVO DE PARADA");
        Panel_Menu_Catalogo.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 200, 30));

        MOTIVO_PARADA_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        MOTIVO_PARADA_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MOTIVO_PARADA_REGISTRAR.setText("R");
        Panel_Menu_Catalogo.add(MOTIVO_PARADA_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, 60, 20));

        MOTIVO_PARADA_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        MOTIVO_PARADA_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MOTIVO_PARADA_CONSULTAR.setText("C");
        Panel_Menu_Catalogo.add(MOTIVO_PARADA_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 490, 60, 20));

        MOTIVO_PARADA_ACTUALIZAR.setBackground(new java.awt.Color(255, 255, 255));
        MOTIVO_PARADA_ACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MOTIVO_PARADA_ACTUALIZAR.setText("A");
        Panel_Menu_Catalogo.add(MOTIVO_PARADA_ACTUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 490, 60, 20));

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(0, 51, 51));
        jLabel69.setText("CLIENTE");
        Panel_Menu_Catalogo.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 200, 30));

        CLIENTE_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        CLIENTE_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CLIENTE_REGISTRAR.setText("R");
        Panel_Menu_Catalogo.add(CLIENTE_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 530, 60, 20));

        CLIENTE_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        CLIENTE_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CLIENTE_CONSULTAR.setText("C");
        Panel_Menu_Catalogo.add(CLIENTE_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 530, 60, 20));

        CLIENTE_REGISTRO_CCARGA.setBackground(new java.awt.Color(255, 255, 255));
        CLIENTE_REGISTRO_CCARGA.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CLIENTE_REGISTRO_CCARGA.setText("REGISTRO_CCARGA");
        Panel_Menu_Catalogo.add(CLIENTE_REGISTRO_CCARGA, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 530, 130, 20));

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(0, 51, 51));
        jLabel70.setText("MOTONAVE");
        Panel_Menu_Catalogo.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, 200, 30));

        MOTONAVE_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        MOTONAVE_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MOTONAVE_REGISTRAR.setText("R");
        MOTONAVE_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MOTONAVE_REGISTRARActionPerformed(evt);
            }
        });
        Panel_Menu_Catalogo.add(MOTONAVE_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 570, 60, 20));

        MOTONAVE_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        MOTONAVE_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MOTONAVE_CONSULTAR.setText("C");
        Panel_Menu_Catalogo.add(MOTONAVE_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 570, 60, 20));

        MOTONAVE_REGISTRO_CCARGA.setBackground(new java.awt.Color(255, 255, 255));
        MOTONAVE_REGISTRO_CCARGA.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MOTONAVE_REGISTRO_CCARGA.setText("REGISTRO_CCARGA");
        Panel_Menu_Catalogo.add(MOTONAVE_REGISTRO_CCARGA, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 570, 130, 20));

        CLIENTE_ACTUALIZAR.setBackground(new java.awt.Color(255, 255, 255));
        CLIENTE_ACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CLIENTE_ACTUALIZAR.setText("A");
        Panel_Menu_Catalogo.add(CLIENTE_ACTUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 530, 60, 20));

        MOTONAVE_ACTUALIZAR.setBackground(new java.awt.Color(255, 255, 255));
        MOTONAVE_ACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MOTONAVE_ACTUALIZAR.setText("A");
        Panel_Menu_Catalogo.add(MOTONAVE_ACTUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 570, 60, 20));

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(0, 51, 51));
        jLabel71.setText("TRANSPORTADORA");
        Panel_Menu_Catalogo.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 110, 200, 30));

        TRANSPORTADORA_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        TRANSPORTADORA_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        TRANSPORTADORA_REGISTRAR.setText("R");
        Panel_Menu_Catalogo.add(TRANSPORTADORA_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 120, 50, 20));

        TRANSPORTADORA_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        TRANSPORTADORA_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        TRANSPORTADORA_CONSULTAR.setText("C");
        Panel_Menu_Catalogo.add(TRANSPORTADORA_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 120, 50, 20));

        TRANSPORTADORA_REGISTRO_CCARGA.setBackground(new java.awt.Color(255, 255, 255));
        TRANSPORTADORA_REGISTRO_CCARGA.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        TRANSPORTADORA_REGISTRO_CCARGA.setText("REGISTRO_CCARGA");
        Panel_Menu_Catalogo.add(TRANSPORTADORA_REGISTRO_CCARGA, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 120, -1, 20));

        TRANSPORTADORA_ACTUALIZAR.setBackground(new java.awt.Color(255, 255, 255));
        TRANSPORTADORA_ACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        TRANSPORTADORA_ACTUALIZAR.setText("A");
        Panel_Menu_Catalogo.add(TRANSPORTADORA_ACTUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 120, 50, 20));

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(0, 51, 51));
        jLabel72.setText("ARTICULO");
        Panel_Menu_Catalogo.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 150, 200, 30));

        ARTICULO_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        ARTICULO_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        ARTICULO_CONSULTAR.setText("C");
        Panel_Menu_Catalogo.add(ARTICULO_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 160, 60, 20));

        ARTICULO_REGISTRO_CCARGA.setBackground(new java.awt.Color(255, 255, 255));
        ARTICULO_REGISTRO_CCARGA.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        ARTICULO_REGISTRO_CCARGA.setText("REGISTRO_CCARGA");
        Panel_Menu_Catalogo.add(ARTICULO_REGISTRO_CCARGA, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 160, 130, 20));

        ARTICULO_ACTUALIZAR.setBackground(new java.awt.Color(255, 255, 255));
        ARTICULO_ACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        ARTICULO_ACTUALIZAR.setText("A");
        ARTICULO_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ARTICULO_ACTUALIZARActionPerformed(evt);
            }
        });
        Panel_Menu_Catalogo.add(ARTICULO_ACTUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 160, 60, 20));

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(0, 51, 51));
        jLabel73.setText("CUADRILLA");
        Panel_Menu_Catalogo.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 190, 200, 30));

        CUADRILLA_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        CUADRILLA_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CUADRILLA_REGISTRAR.setText("R");
        Panel_Menu_Catalogo.add(CUADRILLA_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 200, 40, 20));

        CUADRILLA_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        CUADRILLA_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CUADRILLA_CONSULTAR.setText("C");
        Panel_Menu_Catalogo.add(CUADRILLA_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 200, 40, 20));

        CUADRILLA_ACTUALIZAR.setBackground(new java.awt.Color(255, 255, 255));
        CUADRILLA_ACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CUADRILLA_ACTUALIZAR.setText("A");
        Panel_Menu_Catalogo.add(CUADRILLA_ACTUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 200, 60, 20));

        EQUIPO_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        EQUIPO_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        EQUIPO_REGISTRAR.setText("R");
        EQUIPO_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EQUIPO_REGISTRARActionPerformed(evt);
            }
        });
        Panel_Menu_Catalogo.add(EQUIPO_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 240, 50, 20));

        EQUIPO_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        EQUIPO_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        EQUIPO_CONSULTAR.setText("C");
        Panel_Menu_Catalogo.add(EQUIPO_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 240, 50, 20));

        EQUIPO_ACTUALIZAR.setBackground(new java.awt.Color(255, 255, 255));
        EQUIPO_ACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        EQUIPO_ACTUALIZAR.setText("A");
        Panel_Menu_Catalogo.add(EQUIPO_ACTUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 240, 50, 20));

        EQUIPO_PERTENENCIA_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        EQUIPO_PERTENENCIA_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        EQUIPO_PERTENENCIA_REGISTRAR.setText("R");
        Panel_Menu_Catalogo.add(EQUIPO_PERTENENCIA_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 280, 60, 20));

        EQUIPO_PERTENENCIA_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        EQUIPO_PERTENENCIA_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        EQUIPO_PERTENENCIA_CONSULTAR.setText("C");
        Panel_Menu_Catalogo.add(EQUIPO_PERTENENCIA_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 280, 60, 20));

        EQUIPO_PERTENENCIA_ACTUALIZAR.setBackground(new java.awt.Color(255, 255, 255));
        EQUIPO_PERTENENCIA_ACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        EQUIPO_PERTENENCIA_ACTUALIZAR.setText("A");
        Panel_Menu_Catalogo.add(EQUIPO_PERTENENCIA_ACTUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 280, 60, 20));

        EQUIPO_PROVEEDOR_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        EQUIPO_PROVEEDOR_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        EQUIPO_PROVEEDOR_REGISTRAR.setText("R");
        Panel_Menu_Catalogo.add(EQUIPO_PROVEEDOR_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 320, 60, 20));

        EQUIPO_PROVEEDOR_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        EQUIPO_PROVEEDOR_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        EQUIPO_PROVEEDOR_CONSULTAR.setText("C");
        Panel_Menu_Catalogo.add(EQUIPO_PROVEEDOR_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 320, 60, 20));

        EQUIPO_PROVEEDOR_ACTUALIZAR.setBackground(new java.awt.Color(255, 255, 255));
        EQUIPO_PROVEEDOR_ACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        EQUIPO_PROVEEDOR_ACTUALIZAR.setText("A");
        Panel_Menu_Catalogo.add(EQUIPO_PROVEEDOR_ACTUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 320, 60, 20));

        EQUIPO_TIPO_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        EQUIPO_TIPO_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        EQUIPO_TIPO_REGISTRAR.setText("R");
        Panel_Menu_Catalogo.add(EQUIPO_TIPO_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 360, 60, 20));

        EQUIPO_TIPO_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        EQUIPO_TIPO_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        EQUIPO_TIPO_CONSULTAR.setText("C");
        Panel_Menu_Catalogo.add(EQUIPO_TIPO_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 360, 60, 20));

        EQUIPO_TIPO_ACTUALIZAR.setBackground(new java.awt.Color(255, 255, 255));
        EQUIPO_TIPO_ACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        EQUIPO_TIPO_ACTUALIZAR.setText("A");
        Panel_Menu_Catalogo.add(EQUIPO_TIPO_ACTUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 360, 60, 20));

        EQUIPO_ERP.setBackground(new java.awt.Color(255, 255, 255));
        EQUIPO_ERP.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        EQUIPO_ERP.setText("REGISTRO DESDE ERP");
        Panel_Menu_Catalogo.add(EQUIPO_ERP, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 240, 140, 20));

        EQUIPO_TARIFAS_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        EQUIPO_TARIFAS_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        EQUIPO_TARIFAS_REGISTRAR.setText("TARIFAS");
        Panel_Menu_Catalogo.add(EQUIPO_TARIFAS_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1470, 240, 110, 20));

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(0, 51, 51));
        jLabel75.setText("EQUIPOS");
        Panel_Menu_Catalogo.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 230, 200, 30));

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(0, 51, 51));
        jLabel76.setText("PERTENENCIA EQUIPOS");
        Panel_Menu_Catalogo.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 270, 190, 30));

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(0, 51, 51));
        jLabel77.setText("PROVEEDOR");
        Panel_Menu_Catalogo.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 310, 200, 30));

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(0, 51, 51));
        jLabel78.setText("TIPO DE EQUIPO");
        Panel_Menu_Catalogo.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 350, 210, 30));

        ARTICULO_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        ARTICULO_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        ARTICULO_REGISTRAR.setText("R");
        Panel_Menu_Catalogo.add(ARTICULO_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 160, 40, 20));

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(0, 51, 51));
        jLabel80.setText("CAUSA DE INACTIVIDAD");
        Panel_Menu_Catalogo.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 390, 190, 30));

        CAUSA_INACTIVIDAD_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        CAUSA_INACTIVIDAD_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CAUSA_INACTIVIDAD_REGISTRAR.setText("R");
        Panel_Menu_Catalogo.add(CAUSA_INACTIVIDAD_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 400, 40, 20));

        CAUSA_INACTIVIDAD_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        CAUSA_INACTIVIDAD_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CAUSA_INACTIVIDAD_CONSULTAR.setText("C");
        Panel_Menu_Catalogo.add(CAUSA_INACTIVIDAD_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 400, 40, 20));

        CAUSA_INACTIVIDAD_ACTUALIZAR.setBackground(new java.awt.Color(255, 255, 255));
        CAUSA_INACTIVIDAD_ACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CAUSA_INACTIVIDAD_ACTUALIZAR.setText("A");
        Panel_Menu_Catalogo.add(CAUSA_INACTIVIDAD_ACTUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 400, 40, 20));
        Panel_Menu_Catalogo.add(jSeparator42, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 610, 730, 10));

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(0, 102, 102));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText("REGISTRAR");
        jLabel83.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 110, 30));

        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(0, 102, 102));
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setText("CONSULTAR");
        jLabel85.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 110, 30));

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(0, 102, 102));
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel86.setText("ACTUALIZAR");
        jLabel86.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 100, 30));

        CENTROCOSTOMAYOR_CONSULTAR.setBackground(new java.awt.Color(255, 255, 255));
        CENTROCOSTOMAYOR_CONSULTAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CENTROCOSTOMAYOR_CONSULTAR.setText("C");
        Panel_Menu_Catalogo.add(CENTROCOSTOMAYOR_CONSULTAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, 70, 20));

        CENTROCOSTOMAYOR_ACTUALIZAR.setBackground(new java.awt.Color(255, 255, 255));
        CENTROCOSTOMAYOR_ACTUALIZAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CENTROCOSTOMAYOR_ACTUALIZAR.setText("A");
        Panel_Menu_Catalogo.add(CENTROCOSTOMAYOR_ACTUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 330, 60, 20));

        CENTROCOSTOMAYOR_REGISTRAR.setBackground(new java.awt.Color(255, 255, 255));
        CENTROCOSTOMAYOR_REGISTRAR.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        CENTROCOSTOMAYOR_REGISTRAR.setText("R");
        Panel_Menu_Catalogo.add(CENTROCOSTOMAYOR_REGISTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 60, 20));

        jSeparator61.setOrientation(javax.swing.SwingConstants.VERTICAL);
        Panel_Menu_Catalogo.add(jSeparator61, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 10, 230));

        jSeparator62.setOrientation(javax.swing.SwingConstants.VERTICAL);
        Panel_Menu_Catalogo.add(jSeparator62, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 10, 540));

        jSeparator63.setOrientation(javax.swing.SwingConstants.VERTICAL);
        Panel_Menu_Catalogo.add(jSeparator63, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 10, 540));

        jSeparator64.setOrientation(javax.swing.SwingConstants.VERTICAL);
        Panel_Menu_Catalogo.add(jSeparator64, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 60, 10, 370));

        jSeparator65.setOrientation(javax.swing.SwingConstants.VERTICAL);
        Panel_Menu_Catalogo.add(jSeparator65, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 10, 540));

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(0, 51, 51));
        jLabel84.setText("PERFIL");
        Panel_Menu_Catalogo.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 90, 30));

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(0, 102, 102));
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("ITEMS");
        jLabel87.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 240, 30));

        cuadradoFondo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cuadradoFondo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(cuadradoFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 720, 40));

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel88.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 720, 30));

        cuadradoFondo3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cuadradoFondo3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(cuadradoFondo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 720, 40));

        cuadradoFondo4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cuadradoFondo4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(cuadradoFondo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 720, 40));

        cuadradoFondo5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cuadradoFondo5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(cuadradoFondo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 720, 40));

        cuadradoFondo7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cuadradoFondo7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(cuadradoFondo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 720, 40));

        cuadradoFondo9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cuadradoFondo9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(cuadradoFondo9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 720, 40));

        cuadradoFondo10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cuadradoFondo10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(cuadradoFondo10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 720, 40));

        cuadradoFondo11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cuadradoFondo11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(cuadradoFondo11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 720, 40));

        cuadradoFondo12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cuadradoFondo12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(cuadradoFondo12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 720, 40));

        jSeparator46.setOrientation(javax.swing.SwingConstants.VERTICAL);
        Panel_Menu_Catalogo.add(jSeparator46, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 60, 10, 550));

        jSeparator47.setOrientation(javax.swing.SwingConstants.VERTICAL);
        Panel_Menu_Catalogo.add(jSeparator47, new org.netbeans.lib.awtextra.AbsoluteConstraints(1600, 60, 10, 370));

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(0, 102, 102));
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel89.setText("ITEMS");
        jLabel89.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 60, 240, 30));

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(0, 102, 102));
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel90.setText("REGISTRAR");
        jLabel90.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 60, 110, 30));

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(0, 102, 102));
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel91.setText("CONSULTAR");
        jLabel91.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 60, 110, 30));

        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(0, 102, 102));
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel92.setText("ACTUALIZAR");
        jLabel92.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 60, 100, 30));

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(0, 102, 102));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("OTROS");
        jLabel82.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 60, 300, 30));

        cuadradoFondo13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cuadradoFondo13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(cuadradoFondo13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 720, 40));

        cuadradoFondo14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cuadradoFondo14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(cuadradoFondo14, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 110, 860, 40));

        cuadradoFondo15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cuadradoFondo15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(cuadradoFondo15, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 150, 860, 40));

        cuadradoFondo16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cuadradoFondo16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(cuadradoFondo16, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 190, 860, 40));

        cuadradoFondo17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cuadradoFondo17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(cuadradoFondo17, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 230, 860, 40));

        cuadradoFondo18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cuadradoFondo18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(cuadradoFondo18, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 270, 860, 40));

        cuadradoFondo19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cuadradoFondo19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(cuadradoFondo19, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 310, 860, 40));

        cuadradoFondo20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cuadradoFondo20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(cuadradoFondo20, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 350, 860, 40));

        cuadradoFondo21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cuadradoFondo21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(cuadradoFondo21, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 390, 860, 40));

        jSeparator66.setOrientation(javax.swing.SwingConstants.VERTICAL);
        Panel_Menu_Catalogo.add(jSeparator66, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 10, 540));

        jSeparator67.setOrientation(javax.swing.SwingConstants.VERTICAL);
        Panel_Menu_Catalogo.add(jSeparator67, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 60, 10, 370));

        jSeparator68.setOrientation(javax.swing.SwingConstants.VERTICAL);
        Panel_Menu_Catalogo.add(jSeparator68, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 60, 10, 370));

        jSeparator69.setOrientation(javax.swing.SwingConstants.VERTICAL);
        Panel_Menu_Catalogo.add(jSeparator69, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 60, 10, 370));

        jSeparator48.setOrientation(javax.swing.SwingConstants.VERTICAL);
        Panel_Menu_Catalogo.add(jSeparator48, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 60, 10, 500));

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 51, 51));
        jLabel66.setText("CENTRO COSTO AUXILIAR");
        Panel_Menu_Catalogo.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 200, 30));

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(0, 51, 51));
        jLabel74.setText("CENTRO COSTO");
        Panel_Menu_Catalogo.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 200, 20));

        cuadradoFondo6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cuadradoFondo6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(cuadradoFondo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 720, 40));

        cuadradoFondo8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cuadradoFondo8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel_Menu_Catalogo.add(cuadradoFondo8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 720, 40));

        jTabbedPane1.addTab("CATALOGO", Panel_Menu_Catalogo);

        Panel_Menu_DescargueCarbón.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Menu_DescargueCarbón.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/descargue.png"))); // NOI18N
        Panel_Menu_DescargueCarbón.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 40, 30));

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setText("PERMISOS DE DESCARGUE CARBON");
        Panel_Menu_DescargueCarbón.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 310, 30));
        Panel_Menu_DescargueCarbón.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 670, 110));

        MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS.setBackground(new java.awt.Color(255, 255, 255));
        MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS.setText("MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS");
        Panel_Menu_DescargueCarbón.add(MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 380, 30));

        MODULO_CARBON_INACTIVAR_REGISTRO.setBackground(new java.awt.Color(255, 255, 255));
        MODULO_CARBON_INACTIVAR_REGISTRO.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MODULO_CARBON_INACTIVAR_REGISTRO.setText("MODULO_CARBON_INACTIVAR_REGISTRO");
        Panel_Menu_DescargueCarbón.add(MODULO_CARBON_INACTIVAR_REGISTRO, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 260, 30));

        MODULO_CARBON_ACTIVAR_REGISTRO.setBackground(new java.awt.Color(255, 255, 255));
        MODULO_CARBON_ACTIVAR_REGISTRO.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MODULO_CARBON_ACTIVAR_REGISTRO.setText("MODULO_CARBON_ACTIVAR_REGISTRO");
        Panel_Menu_DescargueCarbón.add(MODULO_CARBON_ACTIVAR_REGISTRO, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 260, 30));

        MODULO_CARBON_GENERAR_MATRIZ.setBackground(new java.awt.Color(255, 255, 255));
        MODULO_CARBON_GENERAR_MATRIZ.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MODULO_CARBON_GENERAR_MATRIZ.setText("MODULO_CARBON_GENERAR_MATRIZ");
        Panel_Menu_DescargueCarbón.add(MODULO_CARBON_GENERAR_MATRIZ, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 260, 30));
        Panel_Menu_DescargueCarbón.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 670, 10));

        MODULO_CARBON_PROCESAR_REGISTROS.setBackground(new java.awt.Color(255, 255, 255));
        MODULO_CARBON_PROCESAR_REGISTROS.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MODULO_CARBON_PROCESAR_REGISTROS.setText("MODULO_CARBON_PROCESAR_REGISTROS");
        Panel_Menu_DescargueCarbón.add(MODULO_CARBON_PROCESAR_REGISTROS, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 380, 30));

        MODULO_CARBON_LAVADO_VEHICULOS.setBackground(new java.awt.Color(255, 255, 255));
        MODULO_CARBON_LAVADO_VEHICULOS.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MODULO_CARBON_LAVADO_VEHICULOS.setText("MODULO_CARBON_LAVADO_VEHICULOS");
        Panel_Menu_DescargueCarbón.add(MODULO_CARBON_LAVADO_VEHICULOS, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 260, 30));

        MODULO_CARBON_AGREGAR_REGISTRO.setBackground(new java.awt.Color(255, 255, 255));
        MODULO_CARBON_AGREGAR_REGISTRO.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MODULO_CARBON_AGREGAR_REGISTRO.setText("MODULO_CARBON_AGREGAR_REGISTRO");
        Panel_Menu_DescargueCarbón.add(MODULO_CARBON_AGREGAR_REGISTRO, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 270, 30));

        MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO.setBackground(new java.awt.Color(255, 255, 255));
        MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO.setText("MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO");
        Panel_Menu_DescargueCarbón.add(MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 450, 30));

        MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO.setBackground(new java.awt.Color(255, 255, 255));
        MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO.setText("MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO");
        Panel_Menu_DescargueCarbón.add(MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 410, 30));

        MODULO_CARBON_GENERAR_DISTRIBUCION.setBackground(new java.awt.Color(255, 255, 255));
        MODULO_CARBON_GENERAR_DISTRIBUCION.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        MODULO_CARBON_GENERAR_DISTRIBUCION.setText("MODULO_CARBON_GENERAR_DISTRIBUCION");
        Panel_Menu_DescargueCarbón.add(MODULO_CARBON_GENERAR_DISTRIBUCION, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 270, 30));

        jTabbedPane1.addTab("MODULO_CARBÓN", Panel_Menu_DescargueCarbón);

        jScrollPane2.setViewportView(jTabbedPane1);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 1170, 710));
    }// </editor-fold>//GEN-END:initComponents

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        try {
            tabla_Listar(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Perfil_Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buscarActionPerformed

    private void tablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyPressed
        
    }//GEN-LAST:event_tablaKeyPressed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO lo del clik derechoo
        int fila1;
        try{
            fila1=tabla.getSelectedRow();
            if(fila1==-1){
                JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
            }
            else{
                habilitarTodosCheckInterfaz(); 
                DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                perfilSeleccion_codigo.setText((String)modelo.getValueAt(fila1, 0));
                perfilSeleccion_Nombre.setText((String)modelo.getValueAt(fila1, 1));
                ControlDB_Permiso controlDB_Permiso = new ControlDB_Permiso(tipoConexion);
                perfil=controlDB_Permiso.CargarPermisos((String)modelo.getValueAt(fila1, 0));
                
                //Cargamos los permisos en la interfaz
                if(perfil != null){
                    desmarcarTodosCheckInterfaz(); 
                    habilitarTodosCheckInterfaz();
                    cargarPermisosInterfaz(perfil);
                }else{
                    desmarcarTodosCheckInterfaz(); 
                    habilitarTodosCheckInterfaz();
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        valorBusqueda.setText("");
    }//GEN-LAST:event_limpiarActionPerformed

    private void valorBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valorBusquedaKeyPressed
        tabla.show();
        try {
            tabla_Listar(valorBusqueda.getText());
        } catch (SQLException ex) {
            Logger.getLogger(Permiso_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_valorBusquedaKeyPressed

    private void todosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_todosStateChanged
        
    }//GEN-LAST:event_todosStateChanged

    private void todosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todosActionPerformed
       if(todos.isSelected()){
            marcarTodosCheckInterfaz();
        }else{ 
            desmarcarTodosCheckInterfaz();
        }
    }//GEN-LAST:event_todosActionPerformed

    private void actualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actualizarMouseClicked
      
    }//GEN-LAST:event_actualizarMouseClicked

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
       if(perfilSeleccion_codigo.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe de cargar un Perfil","Advertencia",JOptionPane.INFORMATION_MESSAGE);
       }else{
            Perfil perfil = new Perfil();
            perfil.setCodigo(perfilSeleccion_codigo.getText());
            perfil.setDescripcion(perfilSeleccion_Nombre.getText());
            ArrayList<Permisos> listadoPermisos= new  ArrayList<>();
            if(SOLICITUD_EQUIPOS_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "SOLICITUD_EQUIPOS_REGISTRAR"));
            }
            if(ASIGNACION_EQUIPOS_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "ASIGNACION_EQUIPOS_REGISTRAR"));
            }
            if(ASIGNACION_EQUIPOS_CONFIRMACION.isSelected()){
                listadoPermisos.add(new Permisos("", "ASIGNACION_EQUIPOS_CONFIRMACION"));
            }
            if(PROGRAMACION_EQUIPOS_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "PROGRAMACION_EQUIPOS_CONSULTAR"));
            }
            if(PROGRAMACION_EQUIPOS_INACTIVAR.isSelected()){
                listadoPermisos.add(new Permisos("", "PROGRAMACION_EQUIPOS_INACTIVAR"));
            }
            if(PROGRAMACION_EQUIPOS_ACTIVAR.isSelected()){
                listadoPermisos.add(new Permisos("", "PROGRAMACION_EQUIPOS_ACTIVAR"));
            }
            if(MODULO_CARBON_PROCESAR_REGISTROS.isSelected()){
                listadoPermisos.add(new Permisos("", "MODULO_CARBON_PROCESAR_REGISTROS"));
            }
            if(MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS.isSelected()){
                listadoPermisos.add(new Permisos("", "MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS"));
            }
            if(MODULO_CARBON_INACTIVAR_REGISTRO.isSelected()){
                listadoPermisos.add(new Permisos("", "MODULO_CARBON_INACTIVAR_REGISTRO"));
            }
            if(MODULO_CARBON_ACTIVAR_REGISTRO.isSelected()){
                listadoPermisos.add(new Permisos("", "MODULO_CARBON_ACTIVAR_REGISTRO"));
            }
            if(MODULO_CARBON_LAVADO_VEHICULOS.isSelected()){
                listadoPermisos.add(new Permisos("", "MODULO_CARBON_LAVADO_VEHICULOS"));
            }
            if(MODULO_CARBON_GENERAR_MATRIZ.isSelected()){
                listadoPermisos.add(new Permisos("", "MODULO_CARBON_GENERAR_MATRIZ"));
            }
            if(MODULO_EQUIPO_PROCESAR_REGISTROS.isSelected()){
                listadoPermisos.add(new Permisos("", "MODULO_EQUIPO_PROCESAR_REGISTROS"));
            }
            if(MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS.isSelected()){
                listadoPermisos.add(new Permisos("", "MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS"));
            }
            if(MODULO_EQUIPO_AUTORIZAR_RECOBRO.isSelected()){
                listadoPermisos.add(new Permisos("", "MODULO_EQUIPO_AUTORIZAR_RECOBRO"));
            }
            if(MODULO_EQUIPO_ACTIVAR_REGISTROS.isSelected()){
                listadoPermisos.add(new Permisos("", "MODULO_EQUIPO_ACTIVAR_REGISTROS"));
            }
            if(MODULO_EQUIPO_INACTIVAR_REGISTROS.isSelected()){
                listadoPermisos.add(new Permisos("", "MODULO_EQUIPO_INACTIVAR_REGISTROS"));
            }
            if(MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO.isSelected()){
                listadoPermisos.add(new Permisos("", "MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO"));
            }
            if(MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO.isSelected()){
                listadoPermisos.add(new Permisos("", "MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO"));
            }
            if(MODULO_EQUIPO_GENERAR_MATRIZ.isSelected()){
                listadoPermisos.add(new Permisos("", "MODULO_EQUIPO_GENERAR_MATRIZ"));
            }
            if(AUDITORIA_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "AUDITORIA_CONSULTAR"));
            }
            if(PERFIL_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "PERFIL_REGISTRAR"));
            }
            if(PERFIL_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "PERFIL_CONSULTAR"));
            }
            if(PERFIL_ACTUALIZAR.isSelected()){
                listadoPermisos.add(new Permisos("", "PERFIL_ACTUALIZAR"));
            }
            if(PERFIL_PERMISOS.isSelected()){
                listadoPermisos.add(new Permisos("", "PERFIL_PERMISOS"));
            }
            if(USUARIO_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "USUARIO_REGISTRAR"));
            }
            if(USUARIO_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "USUARIO_CONSULTAR"));
            }
            if(USUARIO_ACTUALIZAR.isSelected()){
                listadoPermisos.add(new Permisos("", "USUARIO_ACTUALIZAR"));
            }
            if(CENTROOPERACION_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "CENTROOPERACION_REGISTRAR"));
            }
            if(CENTROOPERACION_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "CENTROOPERACION_CONSULTAR"));
            }
            if(CENTROOPERACION_ACTUALIZAR.isSelected()){
                listadoPermisos.add(new Permisos("", "CENTROOPERACION_ACTUALIZAR"));
            }
            if(SUBCENTROOPERACION_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "SUBCENTROOPERACION_REGISTRAR"));
            }
            if(SUBCENTROOPERACION_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "SUBCENTROOPERACION_CONSULTAR"));
            }
            if(SUBCENTROOPERACION_ACTUALIZAR.isSelected()){
                listadoPermisos.add(new Permisos("", "SUBCENTROOPERACION_ACTUALIZAR"));
            }
            if(CENTROCOSTOAUXILIAR_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "CENTROCOSTOAUXILIAR_REGISTRAR"));
            }
            if(CENTROCOSTOAUXILIAR_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "CENTROCOSTOAUXILIAR_CONSULTAR"));
            }
            if(CENTROCOSTOAUXILIAR_ACTUALIZAR.isSelected()){
                listadoPermisos.add(new Permisos("", "CENTROCOSTOAUXILIAR_ACTUALIZAR"));
            }
            if(CENTROCOSTO_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "CENTROCOSTO_REGISTRAR"));
            }
            if(CENTROCOSTO_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "CENTROCOSTO_CONSULTAR"));
            }
            if(CENTROCOSTO_ACTUALIZAR.isSelected()){
                listadoPermisos.add(new Permisos("", "CENTROCOSTO_ACTUALIZAR"));
            }
            if(CENTROCOSTOMAYOR_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "CENTROCOSTOMAYOR_REGISTRAR"));
            }
            if(CENTROCOSTOMAYOR_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "CENTROCOSTOMAYOR_CONSULTAR"));
            }
            if(CENTROCOSTOMAYOR_ACTUALIZAR.isSelected()){
                listadoPermisos.add(new Permisos("", "CENTROCOSTOMAYOR_ACTUALIZAR"));
            }
            if(COMPANIA_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "COMPANIA_REGISTRAR"));
            }
            if(COMPANIA_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "COMPANIA_CONSULTAR"));
            }
            if(COMPANIA_ACTUALIZAR.isSelected()){
                listadoPermisos.add(new Permisos("", "COMPANIA_ACTUALIZAR"));
            }
            if(LABOR_REALIZADA_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "LABOR_REALIZADA_REGISTRAR"));
            }
            if(LABOR_REALIZADA_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "LABOR_REALIZADA_CONSULTAR"));
            }
            if(LABOR_REALIZADA_ACTUALIZAR.isSelected()){
                listadoPermisos.add(new Permisos("", "LABOR_REALIZADA_ACTUALIZAR"));
            }
            if(MOTIVO_PARADA_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "MOTIVO_PARADA_REGISTRAR"));
            }
            if(MOTIVO_PARADA_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "MOTIVO_PARADA_CONSULTAR"));
            }
            if(MOTIVO_PARADA_ACTUALIZAR.isSelected()){
                listadoPermisos.add(new Permisos("", "MOTIVO_PARADA_ACTUALIZAR"));
            }
            if(CLIENTE_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "CLIENTE_REGISTRAR"));
            }
            if(CLIENTE_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "CLIENTE_CONSULTAR"));
            }
            if(CLIENTE_ACTUALIZAR.isSelected()){
                listadoPermisos.add(new Permisos("", "CLIENTE_ACTUALIZAR"));
            }
            if(CLIENTE_REGISTRO_CCARGA.isSelected()){
                listadoPermisos.add(new Permisos("", "CLIENTE_REGISTRO_CCARGA"));
            }
            if(MOTONAVE_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "MOTONAVE_REGISTRAR"));
            }
            if(MOTONAVE_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "MOTONAVE_CONSULTAR"));
            }
            if(MOTONAVE_ACTUALIZAR.isSelected()){
                listadoPermisos.add(new Permisos("", "MOTONAVE_ACTUALIZAR"));
            }
            if(MOTONAVE_REGISTRO_CCARGA.isSelected()){
                listadoPermisos.add(new Permisos("", "MOTONAVE_REGISTRO_CCARGA"));
            }
            if(TRANSPORTADORA_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "TRANSPORTADORA_REGISTRAR"));
            }
            if(TRANSPORTADORA_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "TRANSPORTADORA_CONSULTAR"));
            }
            if(TRANSPORTADORA_ACTUALIZAR.isSelected()){
                listadoPermisos.add(new Permisos("", "TRANSPORTADORA_ACTUALIZAR"));
            }
            if(TRANSPORTADORA_REGISTRO_CCARGA.isSelected()){
                listadoPermisos.add(new Permisos("", "TRANSPORTADORA_REGISTRO_CCARGA"));
            }
            if(ARTICULO_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "ARTICULO_REGISTRAR"));
            }
            if(ARTICULO_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "ARTICULO_CONSULTAR"));
            }
            if(ARTICULO_ACTUALIZAR.isSelected()){
                listadoPermisos.add(new Permisos("", "ARTICULO_ACTUALIZAR"));
            }
            if(ARTICULO_REGISTRO_CCARGA.isSelected()){
                listadoPermisos.add(new Permisos("", "ARTICULO_REGISTRO_CCARGA"));
            }
            if(CUADRILLA_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "CUADRILLA_REGISTRAR"));
            }
            if(CUADRILLA_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "CUADRILLA_CONSULTAR"));
            }
            if(CUADRILLA_ACTUALIZAR.isSelected()){
                listadoPermisos.add(new Permisos("", "CUADRILLA_ACTUALIZAR"));
            }
            if(EQUIPO_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "EQUIPO_REGISTRAR"));
            }
            if(EQUIPO_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "EQUIPO_CONSULTAR"));
            }
            if(EQUIPO_ACTUALIZAR.isSelected()){
                listadoPermisos.add(new Permisos("", "EQUIPO_ACTUALIZAR"));
            }
            if(EQUIPO_PERTENENCIA_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "EQUIPO_PERTENENCIA_REGISTRAR"));
            }
            if(EQUIPO_PERTENENCIA_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "EQUIPO_PERTENENCIA_CONSULTAR"));
            }
            if(EQUIPO_PERTENENCIA_ACTUALIZAR.isSelected()){
                listadoPermisos.add(new Permisos("", "EQUIPO_PERTENENCIA_ACTUALIZAR"));
            }
            if(EQUIPO_PROVEEDOR_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "EQUIPO_PROVEEDOR_REGISTRAR"));
            }
            if(EQUIPO_PROVEEDOR_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "EQUIPO_PROVEEDOR_CONSULTAR"));
            }
            if(EQUIPO_PROVEEDOR_ACTUALIZAR.isSelected()){
                listadoPermisos.add(new Permisos("", "EQUIPO_PROVEEDOR_ACTUALIZAR"));
            }
            if(EQUIPO_TIPO_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "EQUIPO_TIPO_REGISTRAR"));
            }
            if(EQUIPO_TIPO_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "EQUIPO_TIPO_CONSULTAR"));
            }
            if(EQUIPO_TIPO_ACTUALIZAR.isSelected()){
                listadoPermisos.add(new Permisos("", "EQUIPO_TIPO_ACTUALIZAR"));
            }
            if(EQUIPO_ERP.isSelected()){
                listadoPermisos.add(new Permisos("", "EQUIPO_ERP"));
            }
            if(EQUIPO_TARIFAS_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "EQUIPO_TARIFAS_REGISTRAR"));
            }
            if(APPMOVILCARBON_INICAR_DESCARGUE_VEHICULO.isSelected()){
                listadoPermisos.add(new Permisos("", "APPMOVILCARBON_INICAR_DESCARGUE_VEHICULO"));
            }
            if(APPMOVILCARBON_CERRAR_DESCARGUE_VEHICULO.isSelected()){
                listadoPermisos.add(new Permisos("", "APPMOVILCARBON_CERRAR_DESCARGUE_VEHICULO"));
            }
            if(APPMOVILCARBON_INICIAR_CICLO_EQUIPO.isSelected()){
                listadoPermisos.add(new Permisos("", "APPMOVILCARBON_INICIAR_CICLO_EQUIPO"));
            }
            if(APPMOVILCARBON_CERRAR_CICLO_EQUIPO.isSelected()){
                listadoPermisos.add(new Permisos("", "APPMOVILCARBON_CERRAR_CICLO_EQUIPO"));
            }
            if(APPMOVILEQUIPO_INICIAR_CICLO_EQUIPO.isSelected()){
                listadoPermisos.add(new Permisos("", "APPMOVILEQUIPO_INICIAR_CICLO_EQUIPO"));
            }
            if(APPMOVILEQUIPO_CERRAR_CICLO_EQUIPO.isSelected()){
                listadoPermisos.add(new Permisos("", "APPMOVILEQUIPO_CERRAR_CICLO_EQUIPO"));
            }     
            if(CAUSA_INACTIVIDAD_REGISTRAR.isSelected()){
                listadoPermisos.add(new Permisos("", "CAUSA_INACTIVIDAD_REGISTRAR"));
            }
            if(CAUSA_INACTIVIDAD_CONSULTAR.isSelected()){
                listadoPermisos.add(new Permisos("", "CAUSA_INACTIVIDAD_CONSULTAR"));
            }
            if(CAUSA_INACTIVIDAD_ACTUALIZAR.isSelected()){
                listadoPermisos.add(new Permisos("", "CAUSA_INACTIVIDAD_ACTUALIZAR"));
            }   
            
            
            
            if(ASIGNACION_EQUIPOS_EDITAR.isSelected()){
                listadoPermisos.add(new Permisos("", "ASIGNACION_EQUIPOS_EDITAR"));
            }  
            if(SOLICITUD_EQUIPOS_EDITAR.isSelected()){
                listadoPermisos.add(new Permisos("", "SOLICITUD_EQUIPOS_EDITAR"));
            }  
            if(PROGRAMACION_EQUIPOS_DIRECTA.isSelected()){
                listadoPermisos.add(new Permisos("", "PROGRAMACION_EQUIPOS_DIRECTA"));
            }  
            if(MODULO_CARBON_AGREGAR_REGISTRO.isSelected()){
                listadoPermisos.add(new Permisos("", "MODULO_CARBON_AGREGAR_REGISTRO"));
            }  
            if(MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO.isSelected()){
                listadoPermisos.add(new Permisos("", "MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO"));
            }  
            if(MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO.isSelected()){
                listadoPermisos.add(new Permisos("", "MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO"));
            }  
            if(MODULO_CARBON_GENERAR_DISTRIBUCION.isSelected()){
                listadoPermisos.add(new Permisos("", "MODULO_CARBON_GENERAR_DISTRIBUCION"));
            }  
            if(MODULO_EQUIPO_AGREGAR_REGISTRO.isSelected()){
                listadoPermisos.add(new Permisos("", "MODULO_EQUIPO_AGREGAR_REGISTRO"));
            }  
            if(MODULO_EQUIPO_GENERAR_DISTRIBUCION.isSelected()){
                listadoPermisos.add(new Permisos("", "MODULO_EQUIPO_GENERAR_DISTRIBUCION"));
            }  
            //cargamos los permisos al objeto perfil
            perfil.setPermisos(listadoPermisos);
            ControlDB_Perfil controlDB_Perfil = new ControlDB_Perfil(tipoConexion);
            try {
                int result=controlDB_Perfil.actualizarPermisosPerfil(perfil, user);
                if(result == 1){
                    JOptionPane.showMessageDialog(null,"Se registraron los permisos con exito","Registro Exitoso",JOptionPane.INFORMATION_MESSAGE);
                    desmarcarTodosCheckInterfaz();
                    deshabilitarTodosCheckInterfaz();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Permiso_Usuario.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Al Carga los permisos","Error",JOptionPane.ERROR_MESSAGE);
            } catch (UnknownHostException ex) {
               Logger.getLogger(Permiso_Usuario.class.getName()).log(Level.SEVERE, null, ex);
           } catch (SocketException ex) {
               Logger.getLogger(Permiso_Usuario.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    }//GEN-LAST:event_actualizarActionPerformed

    private void ASIGNACION_EQUIPOS_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ASIGNACION_EQUIPOS_REGISTRARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ASIGNACION_EQUIPOS_REGISTRARActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() == 2) {        
            // TODO lo del clik derechoo
            int fila1;
            try{
                fila1=tabla.getSelectedRow();
                if(fila1==-1){
                    JOptionPane.showMessageDialog(null,"no se ha seleccionando ninguna fila");
                }
                else{
                    habilitarTodosCheckInterfaz(); 
                    DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
                    perfilSeleccion_codigo.setText((String)modelo.getValueAt(fila1, 0));
                    perfilSeleccion_Nombre.setText((String)modelo.getValueAt(fila1, 1));
                    ControlDB_Permiso controlDB_Permiso = new ControlDB_Permiso(tipoConexion);
                    perfil=controlDB_Permiso.CargarPermisos((String)modelo.getValueAt(fila1, 0));

                    //Cargamos los permisos en la interfaz
                    if(perfil != null){
                        desmarcarTodosCheckInterfaz(); 
                        habilitarTodosCheckInterfaz();
                        cargarPermisosInterfaz(perfil);
                    }else{
                        desmarcarTodosCheckInterfaz(); 
                        habilitarTodosCheckInterfaz();
                    }
                }
            }catch(Exception e){
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void PERFIL_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PERFIL_REGISTRARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PERFIL_REGISTRARActionPerformed

    private void PERFIL_PERMISOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PERFIL_PERMISOSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PERFIL_PERMISOSActionPerformed

    private void MOTONAVE_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MOTONAVE_REGISTRARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MOTONAVE_REGISTRARActionPerformed

    private void ARTICULO_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ARTICULO_ACTUALIZARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ARTICULO_ACTUALIZARActionPerformed

    private void EQUIPO_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EQUIPO_REGISTRARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EQUIPO_REGISTRARActionPerformed

    private void MODULO_EQUIPO_AGREGAR_REGISTROActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_EQUIPO_AGREGAR_REGISTROActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MODULO_EQUIPO_AGREGAR_REGISTROActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox APPMOVILCARBON_CERRAR_CICLO_EQUIPO;
    private javax.swing.JCheckBox APPMOVILCARBON_CERRAR_DESCARGUE_VEHICULO;
    private javax.swing.JCheckBox APPMOVILCARBON_INICAR_DESCARGUE_VEHICULO;
    private javax.swing.JCheckBox APPMOVILCARBON_INICIAR_CICLO_EQUIPO;
    private javax.swing.JCheckBox APPMOVILEQUIPO_CERRAR_CICLO_EQUIPO;
    private javax.swing.JCheckBox APPMOVILEQUIPO_INICIAR_CICLO_EQUIPO;
    private javax.swing.JCheckBox ARTICULO_ACTUALIZAR;
    private javax.swing.JCheckBox ARTICULO_CONSULTAR;
    private javax.swing.JCheckBox ARTICULO_REGISTRAR;
    private javax.swing.JCheckBox ARTICULO_REGISTRO_CCARGA;
    private javax.swing.JCheckBox ASIGNACION_EQUIPOS_CONFIRMACION;
    private javax.swing.JCheckBox ASIGNACION_EQUIPOS_EDITAR;
    private javax.swing.JCheckBox ASIGNACION_EQUIPOS_REGISTRAR;
    private javax.swing.JCheckBox AUDITORIA_CONSULTAR;
    private javax.swing.JCheckBox CAUSA_INACTIVIDAD_ACTUALIZAR;
    private javax.swing.JCheckBox CAUSA_INACTIVIDAD_CONSULTAR;
    private javax.swing.JCheckBox CAUSA_INACTIVIDAD_REGISTRAR;
    private javax.swing.JCheckBox CENTROCOSTOAUXILIAR_ACTUALIZAR;
    private javax.swing.JCheckBox CENTROCOSTOAUXILIAR_CONSULTAR;
    private javax.swing.JCheckBox CENTROCOSTOAUXILIAR_REGISTRAR;
    private javax.swing.JCheckBox CENTROCOSTOMAYOR_ACTUALIZAR;
    private javax.swing.JCheckBox CENTROCOSTOMAYOR_CONSULTAR;
    private javax.swing.JCheckBox CENTROCOSTOMAYOR_REGISTRAR;
    private javax.swing.JCheckBox CENTROCOSTO_ACTUALIZAR;
    private javax.swing.JCheckBox CENTROCOSTO_CONSULTAR;
    private javax.swing.JCheckBox CENTROCOSTO_REGISTRAR;
    private javax.swing.JCheckBox CENTROOPERACION_ACTUALIZAR;
    private javax.swing.JCheckBox CENTROOPERACION_CONSULTAR;
    private javax.swing.JCheckBox CENTROOPERACION_REGISTRAR;
    private javax.swing.JCheckBox CLIENTE_ACTUALIZAR;
    private javax.swing.JCheckBox CLIENTE_CONSULTAR;
    private javax.swing.JCheckBox CLIENTE_REGISTRAR;
    private javax.swing.JCheckBox CLIENTE_REGISTRO_CCARGA;
    private javax.swing.JCheckBox COMPANIA_ACTUALIZAR;
    private javax.swing.JCheckBox COMPANIA_CONSULTAR;
    private javax.swing.JCheckBox COMPANIA_REGISTRAR;
    private javax.swing.JCheckBox CUADRILLA_ACTUALIZAR;
    private javax.swing.JCheckBox CUADRILLA_CONSULTAR;
    private javax.swing.JCheckBox CUADRILLA_REGISTRAR;
    private javax.swing.JCheckBox EQUIPO_ACTUALIZAR;
    private javax.swing.JCheckBox EQUIPO_CONSULTAR;
    private javax.swing.JCheckBox EQUIPO_ERP;
    private javax.swing.JCheckBox EQUIPO_PERTENENCIA_ACTUALIZAR;
    private javax.swing.JCheckBox EQUIPO_PERTENENCIA_CONSULTAR;
    private javax.swing.JCheckBox EQUIPO_PERTENENCIA_REGISTRAR;
    private javax.swing.JCheckBox EQUIPO_PROVEEDOR_ACTUALIZAR;
    private javax.swing.JCheckBox EQUIPO_PROVEEDOR_CONSULTAR;
    private javax.swing.JCheckBox EQUIPO_PROVEEDOR_REGISTRAR;
    private javax.swing.JCheckBox EQUIPO_REGISTRAR;
    private javax.swing.JCheckBox EQUIPO_TARIFAS_REGISTRAR;
    private javax.swing.JCheckBox EQUIPO_TIPO_ACTUALIZAR;
    private javax.swing.JCheckBox EQUIPO_TIPO_CONSULTAR;
    private javax.swing.JCheckBox EQUIPO_TIPO_REGISTRAR;
    private javax.swing.JMenuItem Editar;
    private javax.swing.JCheckBox LABOR_REALIZADA_ACTUALIZAR;
    private javax.swing.JCheckBox LABOR_REALIZADA_CONSULTAR;
    private javax.swing.JCheckBox LABOR_REALIZADA_REGISTRAR;
    private javax.swing.JCheckBox MODULO_CARBON_ACTIVAR_REGISTRO;
    private javax.swing.JCheckBox MODULO_CARBON_AGREGAR_REGISTRO;
    private javax.swing.JCheckBox MODULO_CARBON_GENERAR_DISTRIBUCION;
    private javax.swing.JCheckBox MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO;
    private javax.swing.JCheckBox MODULO_CARBON_GENERAR_MATRIZ;
    private javax.swing.JCheckBox MODULO_CARBON_INACTIVAR_REGISTRO;
    private javax.swing.JCheckBox MODULO_CARBON_LAVADO_VEHICULOS;
    private javax.swing.JCheckBox MODULO_CARBON_PROCESAR_REGISTROS;
    private javax.swing.JCheckBox MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS;
    private javax.swing.JCheckBox MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO;
    private javax.swing.JCheckBox MODULO_EQUIPO_ACTIVAR_REGISTROS;
    private javax.swing.JCheckBox MODULO_EQUIPO_AGREGAR_REGISTRO;
    private javax.swing.JCheckBox MODULO_EQUIPO_AUTORIZAR_RECOBRO;
    private javax.swing.JCheckBox MODULO_EQUIPO_GENERAR_DISTRIBUCION;
    private javax.swing.JCheckBox MODULO_EQUIPO_GENERAR_MATRIZ;
    private javax.swing.JCheckBox MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO;
    private javax.swing.JCheckBox MODULO_EQUIPO_INACTIVAR_REGISTROS;
    private javax.swing.JCheckBox MODULO_EQUIPO_PROCESAR_REGISTROS;
    private javax.swing.JCheckBox MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS;
    private javax.swing.JCheckBox MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO;
    private javax.swing.JCheckBox MOTIVO_PARADA_ACTUALIZAR;
    private javax.swing.JCheckBox MOTIVO_PARADA_CONSULTAR;
    private javax.swing.JCheckBox MOTIVO_PARADA_REGISTRAR;
    private javax.swing.JCheckBox MOTONAVE_ACTUALIZAR;
    private javax.swing.JCheckBox MOTONAVE_CONSULTAR;
    private javax.swing.JCheckBox MOTONAVE_REGISTRAR;
    private javax.swing.JCheckBox MOTONAVE_REGISTRO_CCARGA;
    private javax.swing.JCheckBox PERFIL_ACTUALIZAR;
    private javax.swing.JCheckBox PERFIL_CONSULTAR;
    private javax.swing.JCheckBox PERFIL_PERMISOS;
    private javax.swing.JCheckBox PERFIL_REGISTRAR;
    private javax.swing.JCheckBox PROGRAMACION_EQUIPOS_ACTIVAR;
    private javax.swing.JCheckBox PROGRAMACION_EQUIPOS_CONSULTAR;
    private javax.swing.JCheckBox PROGRAMACION_EQUIPOS_DIRECTA;
    private javax.swing.JCheckBox PROGRAMACION_EQUIPOS_INACTIVAR;
    private javax.swing.JPanel Panel_Menu_AppMovil;
    private javax.swing.JPanel Panel_Menu_Auditoria;
    private javax.swing.JPanel Panel_Menu_Catalogo;
    private javax.swing.JPanel Panel_Menu_DescargueCarbón;
    private javax.swing.JPanel Panel_Menu_ModuloAgua;
    private javax.swing.JPanel Panel_Menu_ModuloEquipo;
    private javax.swing.JPanel Panel_Menu_ModuloPersonal;
    private javax.swing.JPanel Panel_Menu_Programacion;
    private javax.swing.JCheckBox SOLICITUD_EQUIPOS_EDITAR;
    private javax.swing.JCheckBox SOLICITUD_EQUIPOS_REGISTRAR;
    private javax.swing.JCheckBox SUBCENTROOPERACION_ACTUALIZAR;
    private javax.swing.JCheckBox SUBCENTROOPERACION_CONSULTAR;
    private javax.swing.JCheckBox SUBCENTROOPERACION_REGISTRAR;
    private javax.swing.JPopupMenu Seleccionar;
    private javax.swing.JCheckBox TRANSPORTADORA_ACTUALIZAR;
    private javax.swing.JCheckBox TRANSPORTADORA_CONSULTAR;
    private javax.swing.JCheckBox TRANSPORTADORA_REGISTRAR;
    private javax.swing.JCheckBox TRANSPORTADORA_REGISTRO_CCARGA;
    private javax.swing.JCheckBox USUARIO_ACTUALIZAR;
    private javax.swing.JCheckBox USUARIO_CONSULTAR;
    private javax.swing.JCheckBox USUARIO_REGISTRAR;
    private javax.swing.JButton actualizar;
    private javax.swing.JButton buscar;
    private javax.swing.JLabel cuadradoFondo;
    private javax.swing.JLabel cuadradoFondo10;
    private javax.swing.JLabel cuadradoFondo11;
    private javax.swing.JLabel cuadradoFondo12;
    private javax.swing.JLabel cuadradoFondo13;
    private javax.swing.JLabel cuadradoFondo14;
    private javax.swing.JLabel cuadradoFondo15;
    private javax.swing.JLabel cuadradoFondo16;
    private javax.swing.JLabel cuadradoFondo17;
    private javax.swing.JLabel cuadradoFondo18;
    private javax.swing.JLabel cuadradoFondo19;
    private javax.swing.JLabel cuadradoFondo20;
    private javax.swing.JLabel cuadradoFondo21;
    private javax.swing.JLabel cuadradoFondo3;
    private javax.swing.JLabel cuadradoFondo4;
    private javax.swing.JLabel cuadradoFondo5;
    private javax.swing.JLabel cuadradoFondo6;
    private javax.swing.JLabel cuadradoFondo7;
    private javax.swing.JLabel cuadradoFondo8;
    private javax.swing.JLabel cuadradoFondo9;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator34;
    private javax.swing.JSeparator jSeparator35;
    private javax.swing.JSeparator jSeparator36;
    private javax.swing.JSeparator jSeparator42;
    private javax.swing.JSeparator jSeparator46;
    private javax.swing.JSeparator jSeparator47;
    private javax.swing.JSeparator jSeparator48;
    private javax.swing.JSeparator jSeparator61;
    private javax.swing.JSeparator jSeparator62;
    private javax.swing.JSeparator jSeparator63;
    private javax.swing.JSeparator jSeparator64;
    private javax.swing.JSeparator jSeparator65;
    private javax.swing.JSeparator jSeparator66;
    private javax.swing.JSeparator jSeparator67;
    private javax.swing.JSeparator jSeparator68;
    private javax.swing.JSeparator jSeparator69;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton limpiar;
    private javax.swing.JLabel perfilSeleccion_Nombre;
    private javax.swing.JLabel perfilSeleccion_codigo;
    private javax.swing.JTable tabla;
    private javax.swing.JCheckBox todos;
    private javax.swing.JTextField valorBusqueda;
    // End of variables declaration//GEN-END:variables

    public void tabla_Listar(String valorConsulta) throws SQLException{
        ControlDB_Perfil  controlDB_Perfil = new ControlDB_Perfil(tipoConexion);        
        DefaultTableModel modelo = new DefaultTableModel(null, new String[] {"Código", "Nombre","Estado"});  
        ArrayList<Perfil> listadoPerfiles=controlDB_Perfil.buscar(valorConsulta);
        for(int i =0; i< listadoPerfiles.size(); i++){
            String[]  registro = new String[3]; 
            registro[0]=""+listadoPerfiles.get(i).getCodigo();
            registro[1]=""+listadoPerfiles.get(i).getDescripcion();
            registro[2]=""+listadoPerfiles.get(i).getEstado();            
            modelo.addRow(registro);
        }
         tabla.setModel(modelo);
    }
    public boolean validadorPermiso (String nombrePermiso, Perfil perfil){
        for(int i=0; i< perfil.getPermisos().size(); i++){
            if(nombrePermiso.equals(perfil.getPermisos().get(i).getDescripcion())){
                return true;
            }
        }
        return false;
    }
    public void cargarPermisosInterfaz(Perfil perfil){
        for(int i=0; i< perfil.getPermisos().size(); i++){
            switch(perfil.getPermisos().get(i).getDescripcion()){
                case "SOLICITUD_EQUIPOS_REGISTRAR":{
                    SOLICITUD_EQUIPOS_REGISTRAR.setSelected(true);
                    break;
                }
                case "ASIGNACION_EQUIPOS_REGISTRAR":{
                    ASIGNACION_EQUIPOS_REGISTRAR.setSelected(true);
                    break;
                }
                case "ASIGNACION_EQUIPOS_CONFIRMACION":{
                    ASIGNACION_EQUIPOS_CONFIRMACION.setSelected(true);
                    break;
                }
                case "PROGRAMACION_EQUIPOS_CONSULTAR":{
                    PROGRAMACION_EQUIPOS_CONSULTAR.setSelected(true);
                    break;
                }
                case "PROGRAMACION_EQUIPOS_INACTIVAR":{
                    PROGRAMACION_EQUIPOS_INACTIVAR.setSelected(true);
                    break;
                }
                case "PROGRAMACION_EQUIPOS_ACTIVAR":{
                    PROGRAMACION_EQUIPOS_ACTIVAR.setSelected(true);
                    break;
                }
                case "MODULO_CARBON_PROCESAR_REGISTROS":{
                    MODULO_CARBON_PROCESAR_REGISTROS.setSelected(true);
                    break;
                }
                case "MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS":{
                    MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS.setSelected(true);
                    break;
                }
                case "MODULO_CARBON_INACTIVAR_REGISTRO":{
                    MODULO_CARBON_INACTIVAR_REGISTRO.setSelected(true);
                    break;
                }
                case "MODULO_CARBON_ACTIVAR_REGISTRO":{
                    MODULO_CARBON_ACTIVAR_REGISTRO.setSelected(true);
                    break;
                }
                case "MODULO_CARBON_LAVADO_VEHICULOS":{
                    MODULO_CARBON_LAVADO_VEHICULOS.setSelected(true);
                    break;
                }
                case "MODULO_CARBON_GENERAR_MATRIZ":{
                    MODULO_CARBON_GENERAR_MATRIZ.setSelected(true);
                    break;
                }               
                case "MODULO_EQUIPO_PROCESAR_REGISTROS":{
                    MODULO_EQUIPO_PROCESAR_REGISTROS.setSelected(true);
                    break;
                }
                case "MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS":{
                    MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS.setSelected(true);
                    break;
                }
                case "MODULO_EQUIPO_AUTORIZAR_RECOBRO":{
                    MODULO_EQUIPO_AUTORIZAR_RECOBRO.setSelected(true);
                    break;
                }
                case "MODULO_EQUIPO_ACTIVAR_REGISTROS":{
                    MODULO_EQUIPO_ACTIVAR_REGISTROS.setSelected(true);
                    break;
                }
                case "MODULO_EQUIPO_INACTIVAR_REGISTROS":{
                    MODULO_EQUIPO_INACTIVAR_REGISTROS.setSelected(true);
                    break;
                }
                case "MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO":{
                    MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO.setSelected(true);
                    break;
                }
                case "MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO":{
                    MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO.setSelected(true);
                    break;
                }
                case "MODULO_EQUIPO_GENERAR_MATRIZ":{
                    MODULO_EQUIPO_GENERAR_MATRIZ.setSelected(true);
                    break;
                }
                case "AUDITORIA_CONSULTAR":{
                    AUDITORIA_CONSULTAR.setSelected(true);
                    break;
                }
                case "PERFIL_REGISTRAR":{
                    PERFIL_REGISTRAR.setSelected(true);
                    break;
                }
                case "PERFIL_CONSULTAR":{
                    PERFIL_CONSULTAR.setSelected(true);
                    break;
                }
                case "PERFIL_ACTUALIZAR":{
                    PERFIL_ACTUALIZAR.setSelected(true);
                    break;
                }
                case "PERFIL_PERMISOS":{
                    PERFIL_PERMISOS.setSelected(true);
                    break;
                }
                case "USUARIO_REGISTRAR":{
                    USUARIO_REGISTRAR.setSelected(true);
                    break;
                }
                case "USUARIO_CONSULTAR":{
                    USUARIO_CONSULTAR.setSelected(true);
                    break;
                }
                case "USUARIO_ACTUALIZAR":{
                    USUARIO_ACTUALIZAR.setSelected(true);
                    break;
                }
                case "CENTROOPERACION_REGISTRAR":{
                    CENTROOPERACION_REGISTRAR.setSelected(true);
                    break;
                }
                case "CENTROOPERACION_CONSULTAR":{
                    CENTROOPERACION_CONSULTAR.setSelected(true);
                    break;
                }
                case "CENTROOPERACION_ACTUALIZAR":{
                    CENTROOPERACION_ACTUALIZAR.setSelected(true);
                    break;
                }
                case "SUBCENTROOPERACION_REGISTRAR":{
                    SUBCENTROOPERACION_REGISTRAR.setSelected(true);
                    break;
                }
                case "SUBCENTROOPERACION_CONSULTAR":{
                    SUBCENTROOPERACION_CONSULTAR.setSelected(true);
                    break;
                }
                case "SUBCENTROOPERACION_ACTUALIZAR":{
                    SUBCENTROOPERACION_ACTUALIZAR.setSelected(true);
                    break;
                }
                case "CENTROCOSTOAUXILIAR_REGISTRAR":{
                    CENTROCOSTOAUXILIAR_REGISTRAR.setSelected(true);
                    break;
                }
                case "CENTROCOSTOAUXILIAR_CONSULTAR":{
                    CENTROCOSTOAUXILIAR_CONSULTAR.setSelected(true);
                    break;
                }
                case "CENTROCOSTOAUXILIAR_ACTUALIZAR":{
                    CENTROCOSTOAUXILIAR_ACTUALIZAR.setSelected(true);
                    break;
                }
                case "CENTROCOSTO_REGISTRAR":{
                    CENTROCOSTO_REGISTRAR.setSelected(true);
                    break;
                }
                case "CENTROCOSTO_CONSULTAR":{
                    CENTROCOSTO_CONSULTAR.setSelected(true);
                    break;
                }
                case "CENTROCOSTO_ACTUALIZAR":{
                    CENTROCOSTO_ACTUALIZAR.setSelected(true);
                    break;
                }
                case "CENTROCOSTOMAYOR_REGISTRAR":{
                    CENTROCOSTOMAYOR_REGISTRAR.setSelected(true);
                    break;
                }
                case "CENTROCOSTOMAYOR_CONSULTAR":{
                    CENTROCOSTOMAYOR_CONSULTAR.setSelected(true);
                    break;
                }
                case "CENTROCOSTOMAYOR_ACTUALIZAR":{
                    CENTROCOSTOMAYOR_ACTUALIZAR.setSelected(true);
                    break;
                }
                case "COMPANIA_REGISTRAR":{
                    COMPANIA_REGISTRAR.setSelected(true);
                    break;
                }
                case "COMPANIA_CONSULTAR":{
                    COMPANIA_CONSULTAR.setSelected(true);
                    break;
                }
                case "COMPANIA_ACTUALIZAR":{
                    COMPANIA_ACTUALIZAR.setSelected(true);
                    break;
                }
                case "LABOR_REALIZADA_REGISTRAR":{
                    LABOR_REALIZADA_REGISTRAR.setSelected(true);
                    break;
                }
                case "LABOR_REALIZADA_CONSULTAR":{
                    LABOR_REALIZADA_CONSULTAR.setSelected(true);
                    break;
                }
                case "LABOR_REALIZADA_ACTUALIZAR":{
                    LABOR_REALIZADA_ACTUALIZAR.setSelected(true);
                    break;
                }
                case "MOTIVO_PARADA_REGISTRAR":{
                    MOTIVO_PARADA_REGISTRAR.setSelected(true);
                    break;
                }
                case "MOTIVO_PARADA_CONSULTAR":{
                    MOTIVO_PARADA_CONSULTAR.setSelected(true);
                    break;
                }
                case "MOTIVO_PARADA_ACTUALIZAR":{
                    MOTIVO_PARADA_ACTUALIZAR.setSelected(true);
                    break;
                }
                case "CLIENTE_REGISTRAR":{
                    CLIENTE_REGISTRAR.setSelected(true);
                    break;
                }
                case "CLIENTE_CONSULTAR":{
                    CLIENTE_CONSULTAR.setSelected(true);
                    break;
                }
                case "CLIENTE_ACTUALIZAR":{
                    CLIENTE_ACTUALIZAR.setSelected(true);
                    break;
                }
                case "CLIENTE_REGISTRO_CCARGA":{
                    CLIENTE_REGISTRO_CCARGA.setSelected(true);
                    break;
                }
                case "MOTONAVE_REGISTRAR":{
                    MOTONAVE_REGISTRAR.setSelected(true);
                    break;
                }
                case "MOTONAVE_CONSULTAR":{
                    MOTONAVE_CONSULTAR.setSelected(true);
                    break;
                }
                case "MOTONAVE_ACTUALIZAR":{
                    MOTONAVE_ACTUALIZAR.setSelected(true);
                    break;
                }
                case "MOTONAVE_REGISTRO_CCARGA":{
                    MOTONAVE_REGISTRO_CCARGA.setSelected(true);
                    break;
                }
                case "TRANSPORTADORA_REGISTRAR":{
                    TRANSPORTADORA_REGISTRAR.setSelected(true);
                    break;
                }
                case "TRANSPORTADORA_CONSULTAR":{
                    TRANSPORTADORA_CONSULTAR.setSelected(true);
                    break;
                }
                case "TRANSPORTADORA_ACTUALIZAR":{
                    TRANSPORTADORA_ACTUALIZAR.setSelected(true);
                    break;
                }
                case "TRANSPORTADORA_REGISTRO_CCARGA":{
                    TRANSPORTADORA_REGISTRO_CCARGA.setSelected(true);
                    break;
                }
                case "ARTICULO_REGISTRAR":{
                    ARTICULO_REGISTRAR.setSelected(true);
                    break;
                }
                case "ARTICULO_CONSULTAR":{
                    ARTICULO_CONSULTAR.setSelected(true);
                    break;
                }
                case "ARTICULO_ACTUALIZAR":{
                    ARTICULO_ACTUALIZAR.setSelected(true);
                    break;
                }
                case "ARTICULO_REGISTRO_CCARGA":{
                    ARTICULO_REGISTRO_CCARGA.setSelected(true);
                    break;
                }
                case "CUADRILLA_REGISTRAR":{
                    CUADRILLA_REGISTRAR.setSelected(true);
                    break;
                }
                case "CUADRILLA_CONSULTAR":{
                    CUADRILLA_CONSULTAR.setSelected(true);
                    break;
                }
                case "CUADRILLA_ACTUALIZAR":{
                    CUADRILLA_ACTUALIZAR.setSelected(true);
                    break;
                }
                case "EQUIPO_REGISTRAR":{
                    EQUIPO_REGISTRAR.setSelected(true);
                    break;
                }
                case "EQUIPO_CONSULTAR":{
                    EQUIPO_CONSULTAR.setSelected(true);
                    break;
                }
                case "EQUIPO_ACTUALIZAR":{
                    EQUIPO_ACTUALIZAR.setSelected(true);
                    break;
                }
                case "EQUIPO_PERTENENCIA_REGISTRAR":{
                    EQUIPO_PERTENENCIA_REGISTRAR.setSelected(true);
                    break;
                }
                case "EQUIPO_PERTENENCIA_CONSULTAR":{
                    EQUIPO_PERTENENCIA_CONSULTAR.setSelected(true);
                    break;
                }
                case "EQUIPO_PERTENENCIA_ACTUALIZAR":{
                    EQUIPO_PERTENENCIA_ACTUALIZAR.setSelected(true);
                    break;
                }
                case "EQUIPO_PROVEEDOR_REGISTRAR":{
                    EQUIPO_PROVEEDOR_REGISTRAR.setSelected(true);
                    break;
                }
                case "EQUIPO_PROVEEDOR_CONSULTAR":{
                    EQUIPO_PROVEEDOR_CONSULTAR.setSelected(true);
                    break;
                }
                case "EQUIPO_PROVEEDOR_ACTUALIZAR":{
                    EQUIPO_PROVEEDOR_ACTUALIZAR.setSelected(true);
                    break;
                }
                case "EQUIPO_TIPO_REGISTRAR":{
                    EQUIPO_TIPO_REGISTRAR.setSelected(true);
                    break;
                }
                case "EQUIPO_TIPO_CONSULTAR":{
                    EQUIPO_TIPO_CONSULTAR.setSelected(true);
                    break;
                }
                case "EQUIPO_TIPO_ACTUALIZAR":{
                    EQUIPO_TIPO_ACTUALIZAR.setSelected(true);
                    break;
                }
                case "EQUIPO_ERP":{
                    EQUIPO_ERP.setSelected(true);
                    break;
                }
                case "EQUIPO_TARIFAS_REGISTRAR":{
                    EQUIPO_TARIFAS_REGISTRAR.setSelected(true);
                    break;
                }
                case "APPMOVILCARBON_INICAR_DESCARGUE_VEHICULO":{
                    APPMOVILCARBON_INICAR_DESCARGUE_VEHICULO.setSelected(true);
                    break;
                }
                case "APPMOVILCARBON_CERRAR_DESCARGUE_VEHICULO":{
                    APPMOVILCARBON_CERRAR_DESCARGUE_VEHICULO.setSelected(true);
                    break;
                }
                case "APPMOVILCARBON_INICIAR_CICLO_EQUIPO":{
                    APPMOVILCARBON_INICIAR_CICLO_EQUIPO.setSelected(true);
                    break;
                }
                case "APPMOVILCARBON_CERRAR_CICLO_EQUIPO":{
                    APPMOVILCARBON_CERRAR_CICLO_EQUIPO.setSelected(true);
                    break;
                }
                case "APPMOVILEQUIPO_INICIAR_CICLO_EQUIPO":{
                    APPMOVILEQUIPO_INICIAR_CICLO_EQUIPO.setSelected(true);
                    break;
                }
                case "APPMOVILEQUIPO_CERRAR_CICLO_EQUIPO":{
                    APPMOVILEQUIPO_CERRAR_CICLO_EQUIPO.setSelected(true);
                    break;
                }
                case "CAUSA_INACTIVIDAD_REGISTRAR":{
                    CAUSA_INACTIVIDAD_REGISTRAR.setSelected(true);
                    break;
                }
                case "CAUSA_INACTIVIDAD_CONSULTAR":{
                    CAUSA_INACTIVIDAD_CONSULTAR.setSelected(true);
                    break;
                }
                case "CAUSA_INACTIVIDAD_ACTUALIZAR":{
                    CAUSA_INACTIVIDAD_ACTUALIZAR.setSelected(true);
                    break;
                }
                
                
                
                
                case "ASIGNACION_EQUIPOS_EDITAR":{
                    ASIGNACION_EQUIPOS_EDITAR.setSelected(true);
                    break;
                }
                case "SOLICITUD_EQUIPOS_EDITAR":{
                    SOLICITUD_EQUIPOS_EDITAR.setSelected(true);
                    break;
                }
                case "PROGRAMACION_EQUIPOS_DIRECTA":{
                    PROGRAMACION_EQUIPOS_DIRECTA.setSelected(true);
                    break;
                }
                case "MODULO_CARBON_AGREGAR_REGISTRO":{
                    MODULO_CARBON_AGREGAR_REGISTRO.setSelected(true);
                    break;
                }
                case "MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO":{
                    MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO.setSelected(true);
                    break;
                }
                case "MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO":{
                    MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO.setSelected(true);
                    break;
                }
                case "MODULO_CARBON_GENERAR_DISTRIBUCION":{
                    MODULO_CARBON_GENERAR_DISTRIBUCION.setSelected(true);
                    break;
                }
                case "MODULO_EQUIPO_AGREGAR_REGISTRO":{
                    MODULO_EQUIPO_AGREGAR_REGISTRO.setSelected(true);
                    break;
                }
                case "MODULO_EQUIPO_GENERAR_DISTRIBUCION":{
                    MODULO_EQUIPO_GENERAR_DISTRIBUCION.setSelected(true);
                    break;
                }
                default:{
                    break;
                }
            }
        }
    }
    public void desmarcarTodosCheckInterfaz(){
        SOLICITUD_EQUIPOS_REGISTRAR.setSelected(false);
        ASIGNACION_EQUIPOS_REGISTRAR.setSelected(false);
        ASIGNACION_EQUIPOS_CONFIRMACION.setSelected(false);
        PROGRAMACION_EQUIPOS_CONSULTAR.setSelected(false);
        PROGRAMACION_EQUIPOS_INACTIVAR.setSelected(false);
        PROGRAMACION_EQUIPOS_ACTIVAR.setSelected(false);
        MODULO_CARBON_PROCESAR_REGISTROS.setSelected(false);
        MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS.setSelected(false);
        MODULO_CARBON_INACTIVAR_REGISTRO.setSelected(false);
        MODULO_CARBON_ACTIVAR_REGISTRO.setSelected(false);
        MODULO_CARBON_LAVADO_VEHICULOS.setSelected(false);
        MODULO_CARBON_GENERAR_MATRIZ.setSelected(false);
        MODULO_EQUIPO_PROCESAR_REGISTROS.setSelected(false);
        MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS.setSelected(false);
        MODULO_EQUIPO_AUTORIZAR_RECOBRO.setSelected(false);
        MODULO_EQUIPO_ACTIVAR_REGISTROS.setSelected(false);
        MODULO_EQUIPO_INACTIVAR_REGISTROS.setSelected(false);
        MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO.setSelected(false);
        MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO.setSelected(false);
        MODULO_EQUIPO_GENERAR_MATRIZ.setSelected(false);
        AUDITORIA_CONSULTAR.setSelected(false);
        PERFIL_REGISTRAR.setSelected(false);
        PERFIL_CONSULTAR.setSelected(false);
        PERFIL_ACTUALIZAR.setSelected(false);
        PERFIL_PERMISOS.setSelected(false);
        USUARIO_REGISTRAR.setSelected(false);
        USUARIO_CONSULTAR.setSelected(false);
        USUARIO_ACTUALIZAR.setSelected(false);
        CENTROOPERACION_REGISTRAR.setSelected(false);
        CENTROOPERACION_CONSULTAR.setSelected(false);
        CENTROOPERACION_ACTUALIZAR.setSelected(false);
        SUBCENTROOPERACION_REGISTRAR.setSelected(false);
        SUBCENTROOPERACION_CONSULTAR.setSelected(false);
        SUBCENTROOPERACION_ACTUALIZAR.setSelected(false);
        CENTROCOSTOAUXILIAR_REGISTRAR.setSelected(false);
        CENTROCOSTOAUXILIAR_CONSULTAR.setSelected(false);
        CENTROCOSTOAUXILIAR_ACTUALIZAR.setSelected(false);
        CENTROCOSTO_REGISTRAR.setSelected(false);
        CENTROCOSTO_CONSULTAR.setSelected(false);
        CENTROCOSTO_ACTUALIZAR.setSelected(false);
        CENTROCOSTOMAYOR_REGISTRAR.setSelected(false);
        CENTROCOSTOMAYOR_CONSULTAR.setSelected(false);
        CENTROCOSTOMAYOR_ACTUALIZAR.setSelected(false);
        COMPANIA_REGISTRAR.setSelected(false);
        COMPANIA_CONSULTAR.setSelected(false);
        COMPANIA_ACTUALIZAR.setSelected(false);
        LABOR_REALIZADA_REGISTRAR.setSelected(false);
        LABOR_REALIZADA_CONSULTAR.setSelected(false);
        LABOR_REALIZADA_ACTUALIZAR.setSelected(false);
        MOTIVO_PARADA_REGISTRAR.setSelected(false);
        MOTIVO_PARADA_CONSULTAR.setSelected(false);
        MOTIVO_PARADA_ACTUALIZAR.setSelected(false);
        CLIENTE_REGISTRAR.setSelected(false);
        CLIENTE_CONSULTAR.setSelected(false);
        CLIENTE_ACTUALIZAR.setSelected(false);
        CLIENTE_REGISTRO_CCARGA.setSelected(false);
        MOTONAVE_REGISTRAR.setSelected(false);
        MOTONAVE_CONSULTAR.setSelected(false);
        MOTONAVE_ACTUALIZAR.setSelected(false);
        MOTONAVE_REGISTRO_CCARGA.setSelected(false);
        TRANSPORTADORA_REGISTRAR.setSelected(false);
        TRANSPORTADORA_CONSULTAR.setSelected(false);
        TRANSPORTADORA_ACTUALIZAR.setSelected(false);
        TRANSPORTADORA_REGISTRO_CCARGA.setSelected(false);
        ARTICULO_REGISTRAR.setSelected(false);
        ARTICULO_CONSULTAR.setSelected(false);
        ARTICULO_ACTUALIZAR.setSelected(false);
        ARTICULO_REGISTRO_CCARGA.setSelected(false);
        CUADRILLA_REGISTRAR.setSelected(false);
        CUADRILLA_CONSULTAR.setSelected(false);
        CUADRILLA_ACTUALIZAR.setSelected(false);
        EQUIPO_REGISTRAR.setSelected(false);
        EQUIPO_CONSULTAR.setSelected(false);
        EQUIPO_ACTUALIZAR.setSelected(false);
        EQUIPO_PERTENENCIA_REGISTRAR.setSelected(false);
        EQUIPO_PERTENENCIA_CONSULTAR.setSelected(false);
        EQUIPO_PERTENENCIA_ACTUALIZAR.setSelected(false);
        EQUIPO_PROVEEDOR_REGISTRAR.setSelected(false);
        EQUIPO_PROVEEDOR_CONSULTAR.setSelected(false);
        EQUIPO_PROVEEDOR_ACTUALIZAR.setSelected(false);
        EQUIPO_TIPO_REGISTRAR.setSelected(false);
        EQUIPO_TIPO_CONSULTAR.setSelected(false);
        EQUIPO_TIPO_ACTUALIZAR.setSelected(false);
        EQUIPO_ERP.setSelected(false);
        EQUIPO_TARIFAS_REGISTRAR.setSelected(false);
        APPMOVILCARBON_INICAR_DESCARGUE_VEHICULO.setSelected(false);
        APPMOVILCARBON_CERRAR_DESCARGUE_VEHICULO.setSelected(false);
        APPMOVILCARBON_INICIAR_CICLO_EQUIPO.setSelected(false);
        APPMOVILCARBON_CERRAR_CICLO_EQUIPO.setSelected(false);
        APPMOVILEQUIPO_INICIAR_CICLO_EQUIPO.setSelected(false);
        APPMOVILEQUIPO_CERRAR_CICLO_EQUIPO.setSelected(false);     
        CAUSA_INACTIVIDAD_REGISTRAR.setSelected(false);
        CAUSA_INACTIVIDAD_CONSULTAR.setSelected(false);
        CAUSA_INACTIVIDAD_ACTUALIZAR.setSelected(false);   
        
        
        ASIGNACION_EQUIPOS_EDITAR.setSelected(false);
        SOLICITUD_EQUIPOS_EDITAR.setSelected(false);
        PROGRAMACION_EQUIPOS_DIRECTA.setSelected(false);
        MODULO_CARBON_AGREGAR_REGISTRO.setSelected(false);
        MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO.setSelected(false);
        MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO.setSelected(false);     
        MODULO_CARBON_GENERAR_DISTRIBUCION.setSelected(false);
        MODULO_EQUIPO_AGREGAR_REGISTRO.setSelected(false);
        MODULO_EQUIPO_GENERAR_DISTRIBUCION.setSelected(false); 
        
    }
    public void marcarTodosCheckInterfaz(){
        SOLICITUD_EQUIPOS_REGISTRAR.setSelected(true);
        ASIGNACION_EQUIPOS_REGISTRAR.setSelected(true);
        ASIGNACION_EQUIPOS_CONFIRMACION.setSelected(true);
        PROGRAMACION_EQUIPOS_CONSULTAR.setSelected(true);
        PROGRAMACION_EQUIPOS_INACTIVAR.setSelected(true);
        PROGRAMACION_EQUIPOS_ACTIVAR.setSelected(true);
        MODULO_CARBON_PROCESAR_REGISTROS.setSelected(true);
        MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS.setSelected(true);
        MODULO_CARBON_INACTIVAR_REGISTRO.setSelected(true);
        MODULO_CARBON_ACTIVAR_REGISTRO.setSelected(true);
        MODULO_CARBON_LAVADO_VEHICULOS.setSelected(true);
        MODULO_CARBON_GENERAR_MATRIZ.setSelected(true);
        MODULO_EQUIPO_PROCESAR_REGISTROS.setSelected(true);
        MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS.setSelected(true);
        MODULO_EQUIPO_AUTORIZAR_RECOBRO.setSelected(true);
        MODULO_EQUIPO_ACTIVAR_REGISTROS.setSelected(true);
        MODULO_EQUIPO_INACTIVAR_REGISTROS.setSelected(true);
        MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO.setSelected(true);
        MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO.setSelected(true);
        MODULO_EQUIPO_GENERAR_MATRIZ.setSelected(true);
        AUDITORIA_CONSULTAR.setSelected(true);
        PERFIL_REGISTRAR.setSelected(true);
        PERFIL_CONSULTAR.setSelected(true);
        PERFIL_ACTUALIZAR.setSelected(true);
        PERFIL_PERMISOS.setSelected(true);
        USUARIO_REGISTRAR.setSelected(true);
        USUARIO_CONSULTAR.setSelected(true);
        USUARIO_ACTUALIZAR.setSelected(true);
        CENTROOPERACION_REGISTRAR.setSelected(true);
        CENTROOPERACION_CONSULTAR.setSelected(true);
        CENTROOPERACION_ACTUALIZAR.setSelected(true);
        SUBCENTROOPERACION_REGISTRAR.setSelected(true);
        SUBCENTROOPERACION_CONSULTAR.setSelected(true);
        SUBCENTROOPERACION_ACTUALIZAR.setSelected(true);
        CENTROCOSTOAUXILIAR_REGISTRAR.setSelected(true);
        CENTROCOSTOAUXILIAR_CONSULTAR.setSelected(true);
        CENTROCOSTOAUXILIAR_ACTUALIZAR.setSelected(true);
        CENTROCOSTO_REGISTRAR.setSelected(true);
        CENTROCOSTO_CONSULTAR.setSelected(true);
        CENTROCOSTO_ACTUALIZAR.setSelected(true);
        CENTROCOSTOMAYOR_REGISTRAR.setSelected(true);
        CENTROCOSTOMAYOR_CONSULTAR.setSelected(true);
        CENTROCOSTOMAYOR_ACTUALIZAR.setSelected(true);
        COMPANIA_REGISTRAR.setSelected(true);
        COMPANIA_CONSULTAR.setSelected(true);
        COMPANIA_ACTUALIZAR.setSelected(true);
        LABOR_REALIZADA_REGISTRAR.setSelected(true);
        LABOR_REALIZADA_CONSULTAR.setSelected(true);
        LABOR_REALIZADA_ACTUALIZAR.setSelected(true);
        MOTIVO_PARADA_REGISTRAR.setSelected(true);
        MOTIVO_PARADA_CONSULTAR.setSelected(true);
        MOTIVO_PARADA_ACTUALIZAR.setSelected(true);
        CLIENTE_REGISTRAR.setSelected(true);
        CLIENTE_CONSULTAR.setSelected(true);
        CLIENTE_ACTUALIZAR.setSelected(true);
        CLIENTE_REGISTRO_CCARGA.setSelected(true);
        MOTONAVE_REGISTRAR.setSelected(true);
        MOTONAVE_CONSULTAR.setSelected(true);
        MOTONAVE_ACTUALIZAR.setSelected(true);
        MOTONAVE_REGISTRO_CCARGA.setSelected(true);
        TRANSPORTADORA_REGISTRAR.setSelected(true);
        TRANSPORTADORA_CONSULTAR.setSelected(true);
        TRANSPORTADORA_ACTUALIZAR.setSelected(true);
        TRANSPORTADORA_REGISTRO_CCARGA.setSelected(true);
        ARTICULO_REGISTRAR.setSelected(true);
        ARTICULO_CONSULTAR.setSelected(true);
        ARTICULO_ACTUALIZAR.setSelected(true);
        ARTICULO_REGISTRO_CCARGA.setSelected(true);
        CUADRILLA_REGISTRAR.setSelected(true);
        CUADRILLA_CONSULTAR.setSelected(true);
        CUADRILLA_ACTUALIZAR.setSelected(true);
        EQUIPO_REGISTRAR.setSelected(true);
        EQUIPO_CONSULTAR.setSelected(true);
        EQUIPO_ACTUALIZAR.setSelected(true);
        EQUIPO_PERTENENCIA_REGISTRAR.setSelected(true);
        EQUIPO_PERTENENCIA_CONSULTAR.setSelected(true);
        EQUIPO_PERTENENCIA_ACTUALIZAR.setSelected(true);
        EQUIPO_PROVEEDOR_REGISTRAR.setSelected(true);
        EQUIPO_PROVEEDOR_CONSULTAR.setSelected(true);
        EQUIPO_PROVEEDOR_ACTUALIZAR.setSelected(true);
        EQUIPO_TIPO_REGISTRAR.setSelected(true);
        EQUIPO_TIPO_CONSULTAR.setSelected(true);
        EQUIPO_TIPO_ACTUALIZAR.setSelected(true);
        EQUIPO_ERP.setSelected(true);
        EQUIPO_TARIFAS_REGISTRAR.setSelected(true);
        APPMOVILCARBON_INICAR_DESCARGUE_VEHICULO.setSelected(true);
        APPMOVILCARBON_CERRAR_DESCARGUE_VEHICULO.setSelected(true);
        APPMOVILCARBON_INICIAR_CICLO_EQUIPO.setSelected(true);
        APPMOVILCARBON_CERRAR_CICLO_EQUIPO.setSelected(true);
        APPMOVILEQUIPO_INICIAR_CICLO_EQUIPO.setSelected(true);
        APPMOVILEQUIPO_CERRAR_CICLO_EQUIPO.setSelected(true);  
        CAUSA_INACTIVIDAD_REGISTRAR.setSelected(true);
        CAUSA_INACTIVIDAD_CONSULTAR.setSelected(true);
        CAUSA_INACTIVIDAD_ACTUALIZAR.setSelected(true);
        
        
        ASIGNACION_EQUIPOS_EDITAR.setSelected(true);
        SOLICITUD_EQUIPOS_EDITAR.setSelected(true);
        PROGRAMACION_EQUIPOS_DIRECTA.setSelected(true);
        MODULO_CARBON_AGREGAR_REGISTRO.setSelected(true);
        MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO.setSelected(true);
        MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO.setSelected(true);  
        MODULO_CARBON_GENERAR_DISTRIBUCION.setSelected(true);
        MODULO_EQUIPO_AGREGAR_REGISTRO.setSelected(true);
        MODULO_EQUIPO_GENERAR_DISTRIBUCION.setSelected(true);
        
    }
    public void deshabilitarTodosCheckInterfaz(){
        SOLICITUD_EQUIPOS_REGISTRAR.setEnabled(false);
        ASIGNACION_EQUIPOS_REGISTRAR.setEnabled(false);
        ASIGNACION_EQUIPOS_CONFIRMACION.setEnabled(false);
        PROGRAMACION_EQUIPOS_CONSULTAR.setEnabled(false);
        PROGRAMACION_EQUIPOS_INACTIVAR.setEnabled(false);
        PROGRAMACION_EQUIPOS_ACTIVAR.setEnabled(false);
        MODULO_CARBON_PROCESAR_REGISTROS.setEnabled(false);
        MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS.setEnabled(false);
        MODULO_CARBON_INACTIVAR_REGISTRO.setEnabled(false);
        MODULO_CARBON_ACTIVAR_REGISTRO.setEnabled(false);
        MODULO_CARBON_LAVADO_VEHICULOS.setEnabled(false);
        MODULO_CARBON_GENERAR_MATRIZ.setEnabled(false);
        MODULO_EQUIPO_PROCESAR_REGISTROS.setEnabled(false);
        MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS.setEnabled(false);
        MODULO_EQUIPO_AUTORIZAR_RECOBRO.setEnabled(false);
        MODULO_EQUIPO_ACTIVAR_REGISTROS.setEnabled(false);
        MODULO_EQUIPO_INACTIVAR_REGISTROS.setEnabled(false);
        MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO.setEnabled(false);
        MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO.setEnabled(false);
        MODULO_EQUIPO_GENERAR_MATRIZ.setEnabled(false);
        AUDITORIA_CONSULTAR.setEnabled(false);
        PERFIL_REGISTRAR.setEnabled(false);
        PERFIL_CONSULTAR.setEnabled(false);
        PERFIL_ACTUALIZAR.setEnabled(false);
        PERFIL_PERMISOS.setEnabled(false);
        USUARIO_REGISTRAR.setEnabled(false);
        USUARIO_CONSULTAR.setEnabled(false);
        USUARIO_ACTUALIZAR.setEnabled(false);
        CENTROOPERACION_REGISTRAR.setEnabled(false);
        CENTROOPERACION_CONSULTAR.setEnabled(false);
        CENTROOPERACION_ACTUALIZAR.setEnabled(false);
        SUBCENTROOPERACION_REGISTRAR.setEnabled(false);
        SUBCENTROOPERACION_CONSULTAR.setEnabled(false);
        SUBCENTROOPERACION_ACTUALIZAR.setEnabled(false);
        CENTROCOSTOAUXILIAR_REGISTRAR.setEnabled(false);
        CENTROCOSTOAUXILIAR_CONSULTAR.setEnabled(false);
        CENTROCOSTOAUXILIAR_ACTUALIZAR.setEnabled(false);
        CENTROCOSTO_REGISTRAR.setEnabled(false);
        CENTROCOSTO_CONSULTAR.setEnabled(false);
        CENTROCOSTO_ACTUALIZAR.setEnabled(false);
        CENTROCOSTOMAYOR_REGISTRAR.setEnabled(false);
        CENTROCOSTOMAYOR_CONSULTAR.setEnabled(false);
        CENTROCOSTOMAYOR_ACTUALIZAR.setEnabled(false);
        COMPANIA_REGISTRAR.setEnabled(false);
        COMPANIA_CONSULTAR.setEnabled(false);
        COMPANIA_ACTUALIZAR.setEnabled(false);
        LABOR_REALIZADA_REGISTRAR.setEnabled(false);
        LABOR_REALIZADA_CONSULTAR.setEnabled(false);
        LABOR_REALIZADA_ACTUALIZAR.setEnabled(false);
        MOTIVO_PARADA_REGISTRAR.setEnabled(false);
        MOTIVO_PARADA_CONSULTAR.setEnabled(false);
        MOTIVO_PARADA_ACTUALIZAR.setEnabled(false);
        CLIENTE_REGISTRAR.setEnabled(false);
        CLIENTE_CONSULTAR.setEnabled(false);
        CLIENTE_ACTUALIZAR.setEnabled(false);
        CLIENTE_REGISTRO_CCARGA.setEnabled(false);
        MOTONAVE_REGISTRAR.setEnabled(false);
        MOTONAVE_CONSULTAR.setEnabled(false);
        MOTONAVE_ACTUALIZAR.setEnabled(false);
        MOTONAVE_REGISTRO_CCARGA.setEnabled(false);
        TRANSPORTADORA_REGISTRAR.setEnabled(false);
        TRANSPORTADORA_CONSULTAR.setEnabled(false);
        TRANSPORTADORA_ACTUALIZAR.setEnabled(false);
        TRANSPORTADORA_REGISTRO_CCARGA.setEnabled(false);
        ARTICULO_REGISTRAR.setEnabled(false);
        ARTICULO_CONSULTAR.setEnabled(false);
        ARTICULO_ACTUALIZAR.setEnabled(false);
        ARTICULO_REGISTRO_CCARGA.setEnabled(false);
        CUADRILLA_REGISTRAR.setEnabled(false);
        CUADRILLA_CONSULTAR.setEnabled(false);
        CUADRILLA_ACTUALIZAR.setEnabled(false);
        EQUIPO_REGISTRAR.setEnabled(false);
        EQUIPO_CONSULTAR.setEnabled(false);
        EQUIPO_ACTUALIZAR.setEnabled(false);
        EQUIPO_PERTENENCIA_REGISTRAR.setEnabled(false);
        EQUIPO_PERTENENCIA_CONSULTAR.setEnabled(false);
        EQUIPO_PERTENENCIA_ACTUALIZAR.setEnabled(false);
        EQUIPO_PROVEEDOR_REGISTRAR.setEnabled(false);
        EQUIPO_PROVEEDOR_CONSULTAR.setEnabled(false);
        EQUIPO_PROVEEDOR_ACTUALIZAR.setEnabled(false);
        EQUIPO_TIPO_REGISTRAR.setEnabled(false);
        EQUIPO_TIPO_CONSULTAR.setEnabled(false);
        EQUIPO_TIPO_ACTUALIZAR.setEnabled(false);
        EQUIPO_ERP.setEnabled(false);
        EQUIPO_TARIFAS_REGISTRAR.setEnabled(false);
        APPMOVILCARBON_INICAR_DESCARGUE_VEHICULO.setEnabled(false);
        APPMOVILCARBON_CERRAR_DESCARGUE_VEHICULO.setEnabled(false);
        APPMOVILCARBON_INICIAR_CICLO_EQUIPO.setEnabled(false);
        APPMOVILCARBON_CERRAR_CICLO_EQUIPO.setEnabled(false);
        APPMOVILEQUIPO_INICIAR_CICLO_EQUIPO.setEnabled(false);
        APPMOVILEQUIPO_CERRAR_CICLO_EQUIPO.setEnabled(false);     
        CAUSA_INACTIVIDAD_REGISTRAR.setEnabled(false);
        CAUSA_INACTIVIDAD_CONSULTAR.setEnabled(false);
        CAUSA_INACTIVIDAD_ACTUALIZAR.setEnabled(false);  
        
        
        ASIGNACION_EQUIPOS_EDITAR.setEnabled(false);
        SOLICITUD_EQUIPOS_EDITAR.setEnabled(false);
        PROGRAMACION_EQUIPOS_DIRECTA.setEnabled(false);
        MODULO_CARBON_AGREGAR_REGISTRO.setEnabled(false);
        MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO.setEnabled(false);
        MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO.setEnabled(false);     
        MODULO_CARBON_GENERAR_DISTRIBUCION.setEnabled(false);
        MODULO_EQUIPO_AGREGAR_REGISTRO.setEnabled(false);
        MODULO_EQUIPO_GENERAR_DISTRIBUCION.setEnabled(false); 
        
    }
    public void habilitarTodosCheckInterfaz(){
        SOLICITUD_EQUIPOS_REGISTRAR.setEnabled(true);
        ASIGNACION_EQUIPOS_REGISTRAR.setEnabled(true);
        ASIGNACION_EQUIPOS_CONFIRMACION.setEnabled(true);
        PROGRAMACION_EQUIPOS_CONSULTAR.setEnabled(true);
        PROGRAMACION_EQUIPOS_INACTIVAR.setEnabled(true);
        PROGRAMACION_EQUIPOS_ACTIVAR.setEnabled(true);
        MODULO_CARBON_PROCESAR_REGISTROS.setEnabled(true);
        MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS.setEnabled(true);
        MODULO_CARBON_INACTIVAR_REGISTRO.setEnabled(true);
        MODULO_CARBON_ACTIVAR_REGISTRO.setEnabled(true);
        MODULO_CARBON_LAVADO_VEHICULOS.setEnabled(true);
        MODULO_CARBON_GENERAR_MATRIZ.setEnabled(true);
        MODULO_EQUIPO_PROCESAR_REGISTROS.setEnabled(true);
        MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS.setEnabled(true);
        MODULO_EQUIPO_AUTORIZAR_RECOBRO.setEnabled(true);
        MODULO_EQUIPO_ACTIVAR_REGISTROS.setEnabled(true);
        MODULO_EQUIPO_INACTIVAR_REGISTROS.setEnabled(true);
        MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO.setEnabled(true);
        MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO.setEnabled(true);
        MODULO_EQUIPO_GENERAR_MATRIZ.setEnabled(true);
        AUDITORIA_CONSULTAR.setEnabled(true);
        PERFIL_REGISTRAR.setEnabled(true);
        PERFIL_CONSULTAR.setEnabled(true);
        PERFIL_ACTUALIZAR.setEnabled(true);
        PERFIL_PERMISOS.setEnabled(true);
        USUARIO_REGISTRAR.setEnabled(true);
        USUARIO_CONSULTAR.setEnabled(true);
        USUARIO_ACTUALIZAR.setEnabled(true);
        CENTROOPERACION_REGISTRAR.setEnabled(true);
        CENTROOPERACION_CONSULTAR.setEnabled(true);
        CENTROOPERACION_ACTUALIZAR.setEnabled(true);
        SUBCENTROOPERACION_REGISTRAR.setEnabled(true);
        SUBCENTROOPERACION_CONSULTAR.setEnabled(true);
        SUBCENTROOPERACION_ACTUALIZAR.setEnabled(true);
        CENTROCOSTOAUXILIAR_REGISTRAR.setEnabled(true);
        CENTROCOSTOAUXILIAR_CONSULTAR.setEnabled(true);
        CENTROCOSTOAUXILIAR_ACTUALIZAR.setEnabled(true);
        CENTROCOSTO_REGISTRAR.setEnabled(true);
        CENTROCOSTO_CONSULTAR.setEnabled(true);
        CENTROCOSTO_ACTUALIZAR.setEnabled(true);
        CENTROCOSTOMAYOR_REGISTRAR.setEnabled(true);
        CENTROCOSTOMAYOR_CONSULTAR.setEnabled(true);
        CENTROCOSTOMAYOR_ACTUALIZAR.setEnabled(true);
        COMPANIA_REGISTRAR.setEnabled(true);
        COMPANIA_CONSULTAR.setEnabled(true);
        COMPANIA_ACTUALIZAR.setEnabled(true);
        LABOR_REALIZADA_REGISTRAR.setEnabled(true);
        LABOR_REALIZADA_CONSULTAR.setEnabled(true);
        LABOR_REALIZADA_ACTUALIZAR.setEnabled(true);
        MOTIVO_PARADA_REGISTRAR.setEnabled(true);
        MOTIVO_PARADA_CONSULTAR.setEnabled(true);
        MOTIVO_PARADA_ACTUALIZAR.setEnabled(true);
        CLIENTE_REGISTRAR.setEnabled(true);
        CLIENTE_CONSULTAR.setEnabled(true);
        CLIENTE_ACTUALIZAR.setEnabled(true);
        CLIENTE_REGISTRO_CCARGA.setEnabled(true);
        MOTONAVE_REGISTRAR.setEnabled(true);
        MOTONAVE_CONSULTAR.setEnabled(true);
        MOTONAVE_ACTUALIZAR.setEnabled(true);
        MOTONAVE_REGISTRO_CCARGA.setEnabled(true);
        TRANSPORTADORA_REGISTRAR.setEnabled(true);
        TRANSPORTADORA_CONSULTAR.setEnabled(true);
        TRANSPORTADORA_ACTUALIZAR.setEnabled(true);
        TRANSPORTADORA_REGISTRO_CCARGA.setEnabled(true);
        ARTICULO_REGISTRAR.setEnabled(true);
        ARTICULO_CONSULTAR.setEnabled(true);
        ARTICULO_ACTUALIZAR.setEnabled(true);
        ARTICULO_REGISTRO_CCARGA.setEnabled(true);
        CUADRILLA_REGISTRAR.setEnabled(true);
        CUADRILLA_CONSULTAR.setEnabled(true);
        CUADRILLA_ACTUALIZAR.setEnabled(true);
        EQUIPO_REGISTRAR.setEnabled(true);
        EQUIPO_CONSULTAR.setEnabled(true);
        EQUIPO_ACTUALIZAR.setEnabled(true);
        EQUIPO_PERTENENCIA_REGISTRAR.setEnabled(true);
        EQUIPO_PERTENENCIA_CONSULTAR.setEnabled(true);
        EQUIPO_PERTENENCIA_ACTUALIZAR.setEnabled(true);
        EQUIPO_PROVEEDOR_REGISTRAR.setEnabled(true);
        EQUIPO_PROVEEDOR_CONSULTAR.setEnabled(true);
        EQUIPO_PROVEEDOR_ACTUALIZAR.setEnabled(true);
        EQUIPO_TIPO_REGISTRAR.setEnabled(true);
        EQUIPO_TIPO_CONSULTAR.setEnabled(true);
        EQUIPO_TIPO_ACTUALIZAR.setEnabled(true);
        EQUIPO_ERP.setEnabled(true);
        EQUIPO_TARIFAS_REGISTRAR.setEnabled(true);
        APPMOVILCARBON_INICAR_DESCARGUE_VEHICULO.setEnabled(true);
        APPMOVILCARBON_CERRAR_DESCARGUE_VEHICULO.setEnabled(true);
        APPMOVILCARBON_INICIAR_CICLO_EQUIPO.setEnabled(true);
        APPMOVILCARBON_CERRAR_CICLO_EQUIPO.setEnabled(true);
        APPMOVILEQUIPO_INICIAR_CICLO_EQUIPO.setEnabled(true);
        APPMOVILEQUIPO_CERRAR_CICLO_EQUIPO.setEnabled(true);           
        CAUSA_INACTIVIDAD_REGISTRAR.setEnabled(true);
        CAUSA_INACTIVIDAD_CONSULTAR.setEnabled(true);
        CAUSA_INACTIVIDAD_ACTUALIZAR.setEnabled(true);     
        
        
        
        ASIGNACION_EQUIPOS_EDITAR.setEnabled(true);
        SOLICITUD_EQUIPOS_EDITAR.setEnabled(true);
        PROGRAMACION_EQUIPOS_DIRECTA.setEnabled(true);
        MODULO_CARBON_AGREGAR_REGISTRO.setEnabled(true);
        MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO.setEnabled(true);
        MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO.setEnabled(true);           
        MODULO_CARBON_GENERAR_DISTRIBUCION.setEnabled(true);
        MODULO_EQUIPO_AGREGAR_REGISTRO.setEnabled(true);
        MODULO_EQUIPO_GENERAR_DISTRIBUCION.setEnabled(true); 
    }
}    

