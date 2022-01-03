 package ViewPrincipal;

import Auditoria.View.Auditoria_Consultar;
import Catalogo.View.Cliente_Consultar;
import Catalogo.View.Cliente_Registrar;
import Catalogo.View.Cliente_RegistroSincronizado; 
import Catalogo.View.Articulo_Actualizar;
import Catalogo.View.Articulo_Consultar;
import Catalogo.View.CentroCostoAuxiliar_Actualizar;
import Catalogo.View.CentroCostoAuxiliar_Consultar; 
import Sistema.View.Usuario_Registrar;
import Sistema.Model.Usuario; 
import Catalogo.View.CentroCostoAuxiliar_Registrar;
import Catalogo.View.CentroCostoSubCentro_Registrar;
import Catalogo.View.CentroCostoSubcentro_Actualizar;
import Catalogo.View.CentroCostoSubcentro_Consultar;
import Catalogo.View.CentroOperacion_Actualizar;
import Catalogo.View.CentroOperacion_Consultar;
import Catalogo.View.CentroOperacion_Registrar;
import Catalogo.View.Motonave_Actualizar;
import Catalogo.View.Motonave_Consultar;
import Catalogo.View.Motonave_Registrar;
import Catalogo.View.Motonave_RegistroSincronizado;
import Catalogo.View.Articulo_Registrar;
import Catalogo.View.Articulo_RegistroSincronizado;
import Catalogo.View.Cuadrilla_Consultar;
import Catalogo.View.Cuadrilla_Registrar;
import ModuloCarbon.View.MvtoCarbon_Procesar;
import Catalogo.View.Transportadora_Actualizar;
import Catalogo.View.Transportadora_Consultar;
import Catalogo.View.Transportadora_Registrar;
import Catalogo.View.Transportadora_RegistroSincronizado;
import Catalogo.View.Compañia_Registrar;
import Catalogo.View.Compañias_Actualizar;
import Catalogo.View.Compañias_Consultar;
import Catalogo.View.Equipo_Actualizar;
import Catalogo.View.Equipo_Consultar;
import Catalogo.View.Equipo_Registrar;
import Catalogo.View.Equipo_RegistroSincronizado;
import Catalogo.View.PertenenciaEquipo_Actualizar;
import Catalogo.View.PertenenciaEquipo_Consultar;
import Catalogo.View.ActividadOperacion_Registrar;
import Catalogo.View.CausaInactividad_Actualizar;
import Catalogo.View.CausaInactividad_Consultar;
import Catalogo.View.CausaInactividad_Registrar;
import Catalogo.View.CentroCostoMayor_Actualizar;
import Catalogo.View.CentroCostoMayor_Consultar;
import Catalogo.View.CentroCostoMayor_Registrar;
import Catalogo.View.CentroCosto_Actualizar;
import Catalogo.View.CentroCosto_Consultar;
import Catalogo.View.CentroCosto_Registrar;
import ModuloEquipo.View.AsignacionEquipo_Inactivar;
import ModuloEquipo.View.AsignacionCronograma;
import ModuloEquipo.View.Solicitud_Equipos_Confirmacion;
import ModuloEquipo.View.AsignacionEquipo_Registrar;
import Catalogo.View.Equipo_Tarifa;
import Catalogo.View.LaborRealizada_Actualizar;
import Catalogo.View.LaborRealizada_Consultar;
import Catalogo.View.LaborRealizada_Registrar;
import Catalogo.View.MotivoNoLavado_Actualizar;
import Catalogo.View.MotivoNoLavado_Consultar;
import Catalogo.View.MotivoParada_Actualizar;
import Catalogo.View.MotivoParada_Consultar;
import Catalogo.View.MotivoParada_Registrar;
import ModuloEquipo.View.MvtoEquipo_AutorizarRecobro;
import Catalogo.View.ProveedorEquipo_Actualizar;
import Catalogo.View.ProveedorEquipo_Consultar;
import Catalogo.View.ProveedorEquipo_Registrar;
import ModuloEquipo.View.Solicitud_Equipos_Registrar;
import Catalogo.View.TipoEquipo_Actualizar;
import Catalogo.View.TipoEquipo_Consultar;
import Catalogo.View.TipoEquipo_Registrar;
import Catalogo.View.ZonaTrabajoActualizarSitio;
import Catalogo.View.ZonaTrabajoPorCentroCostoAuxiliar_Registrar;
import Catalogo.View.ZonaTrabajo_Actualizar;
import Catalogo.View.ZonaTrabajo_Consultar;
import Catalogo.View.ZonaTrabajo_Registrar;
import ConnectionDB.Conexion_DB_costos_vg;
import ModuloCarbon.View.MvtoCarbon_Activar_Final;
import ModuloCarbon.View.MvtoCarbon_AgregarRegistro;
import ModuloCarbon.View.MvtoCarbon_DebitoZonaTrabajo;
import ModuloCarbon.View.MvtoCarbon_GenerarMatriz;
import ModuloCarbon.View.MvtoCarbon_Inactivar_Final;
import ModuloCarbon.View.MvtoCarbon_InformeRecaudoLavadoVehiculo;
import ModuloCarbon.View.MvtoCarbon_MatrizDistribucion;
import ModuloCarbon.View.MvtoCarbon_ModificarFinal;
import ModuloCarbon.View.MvtoCarbon_ProcesarProgramado;
import ModuloEquipo.View.AsignacionEquipo_Activar;
import ModuloEquipo.View.AsignacionEquipo_Consultar;
import ModuloEquipo.View.MvtoEquipo_ActivarEquipo;
import ModuloEquipo.View.MvtoEquipo_Agregar;
import ModuloEquipo.View.MvtoEquipo_InactivarEquipo;
import ModuloEquipo.View.MvtoEquipo_InformeMatriz;
import ModuloEquipo.View.MvtoEquipo_MatrizDistribucion;
import ModuloEquipo.View.MvtoEquipo_ModificarFinal;
import ModuloEquipo.View.MvtoEquipo_Procesar_Programado;
import ModuloEquipo.View.MvtoEquipo_Procesar;
import ModuloEquipo.View.Programacion_Directa;
import ModuloEquipo.View.RendimientoEquipo;
import ModuloEquipo.View.Rendimiento_InformeEquipo;
import ModuloPalero.View.ConfiguracionLiquidacion_Actualizar;
import ModuloPalero.View.ConfiguracionLiquidacion_Consultar;
import ModuloPalero.View.ConfiguracionLiquidacion_Registrar;
import ModuloPalero.View.EquipoLiquidacion_Actualizar;
import ModuloPalero.View.EquipoLiquidacion_Consultar;
import ModuloPalero.View.EquipoLiquidacion_Registrar;
import ModuloPalero.View.LiquidacionPalero_Consultar;
import ModuloPalero.View.LiquidacionPalero_ConsultarConsolidadoPorPeriodo;
import ModuloPalero.View.LiquidacionPalero_Registrar;
import ModuloPalero.View.PreLiquidacionPalero_Consultar;
import ModuloPalero.View.PreLiquidacionPalero_Registrar;
import ModuloPalero.View.Marcacion_Actualizar;
import ModuloPalero.View.Marcacion_Registrar;
import ModuloPalero.View.Marcacion_Consultar;
import ModuloPersonal.View.CargoNomina_Actualizar;
import ModuloPersonal.View.CargoNomina_Consultar;
import ModuloPersonal.View.CargoNomina_Registrar;
import ModuloPersonal.View.Jornada_Actualizar;
import ModuloPersonal.View.Jornada_Consultar;
import ModuloPersonal.View.Jornada_Registrar;
import ModuloPersonal.View.Persona_Actualizar;
import ModuloPersonal.View.Persona_Consultar;
import ModuloPersonal.View.Persona_Registrar;
import ModuloPersonal.View.SituacionMedica_Actualizar;
import ModuloPersonal.View.SituacionMedica_Consultar;
import ModuloPersonal.View.SituacionMedica_Registrar;
import ModuloPersonal.View.TipoContrato_Actualizar;
import ModuloPersonal.View.TipoContrato_Consultar;
import ModuloPersonal.View.TipoContrato_Registrar;
import ModuloPersonal.View.TipoDocumento_Consultar;
import ModuloPersonal.View.TipoDocumento_Registrar;
import Sistema.Controller.ControlDB_Sistema;
import Sistema.View.Perfil_Actualizar;
import Sistema.View.Perfil_Consultar;
import Sistema.View.Perfil_Registrar;
import Sistema.View.Permiso_Usuario;
import Sistema.View.Usuario_Actualizar;
import Sistema.View.Usuario_CambiarClave;
import Sistema.View.Usuario_Consultar;
import java.awt.Color;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

public final class GUI_Principal extends javax.swing.JFrame {
    private Dimension dim;
    private Usuario user;
    private String tipoConexion;
    private String cadena="";
    public GUI_Principal() {
        initComponents();
        tipoConexion="";
        
        this.setTitle("VENTURADATA                                            DB:"+new Conexion_DB_costos_vg(tipoConexion).getBaseDeDatos() /*+ " TypeConnection: "+this.tipoConexion*/);
        //con esto obtienes en tamano en en x y y de tu monitor
        dim=super.getToolkit().getScreenSize();
        //super.setSize(dim); 
        //super.setUndecorated(true);
        //super.setVisible(true);  
        this.getContentPane().setBackground(Color.WHITE);
        //panel.setViewportView(new PanelControl(tipoConexion));
        ocultarMenu();//Inactiva todos los menu
        this.setSize(dim.width, dim.height);
        
        UIManager.put("jMenuBar1.background", Color.ORANGE);
        //Cierra procesos al cerrar la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        /*TEMPORALES*/
        /*jMenu48.show(false);
        jMenu49.show(false);
        jMenu50.show(false);
        jMenu51.show(false);
        jMenu52.show(false);
        jMenu53.show(false);*/
    }
    
    public void cargarUsuario(Usuario userT, String tipoConexion) throws ParseException, UnknownHostException, SocketException{
        user = userT;
        this.tipoConexion=tipoConexion;
        InetAddress localHost;
        String NamePc="",Ip="";
        try {
            localHost = InetAddress.getLocalHost();
            NamePc=localHost.getHostName();
            Ip=localHost.getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(GUI_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        cadena="Cedula: "+ user.getCodigo()+"     Nombre: "+ user.getNombres()+" "+ user.getApellidos()+"     Perfil del Usuario: "+user.getPerfilUsuario().getDescripcion()+"     Computador: "+NamePc+ "       IP:"+Ip;
        panel.setViewportView(new PanelControl_Menu(cadena,tipoConexion));
        //panel.setViewportView(new PanelControl_old(tipoConexion,"Cedula: "+ user.getCodigo()+"     Nombre: "+ user.getNombres()+" "+ user.getApellidos()+"     Perfil del Usuario: "+user.getPerfilUsuario().getDescripcion()+"     Computador: "+NamePc+ "       IP:"+Ip));
    
        //userOnline.setText("Cedula: "+ user.getCodigo()+"     Nombre: "+ user.getNombres()+" "+ user.getApellidos()+"     Perfil del Usuario: "+user.getPerfilUsuario().getDescripcion()+"     Computador: "+NamePc+ "       IP:"+Ip);   
        
        
        /*****************************************************************/
          //Registramos el ingreso al sistema
            try {
                new ControlDB_Sistema(this.tipoConexion).ingresoSistema(user);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GUI_Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
       /************************************************************************************/
        
        //  userOnline.setText(""+userOnline.getText()+" Tipo Conexión:"+tipoConexion+"      Conexión Día:"+new ControlDB_Sistema(this.tipoConexion).contadorIngresoDiario(user));
        validarMenu(user);
    }
    public void cargaMenu(){
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu41 = new javax.swing.JMenu();
        jMenu39 = new javax.swing.JMenu();
        jMenu31 = new javax.swing.JMenu();
        SOLICITUD_EQUIPOS_REGISTRAR = new javax.swing.JMenuItem();
        jMenu32 = new javax.swing.JMenu();
        ASIGNACION_EQUIPOS_REGISTRAR = new javax.swing.JMenuItem();
        ASIGNACION_EQUIPOS_CONFIRMACION = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        PROGRAMACION_EQUIPOS_CONSULTAR = new javax.swing.JMenuItem();
        PROGRAMACION_EQUIPOS_INACTIVAR = new javax.swing.JMenuItem();
        PROGRAMACION_EQUIPOS_ACTIVAR = new javax.swing.JMenuItem();
        PROGRAMACION_EQUIPOS_DIRECTA = new javax.swing.JMenuItem();
        ASIGNACION_EQUIPOS_EDITAR = new javax.swing.JMenuItem();
        SOLICITUD_EQUIPOS_EDITAR = new javax.swing.JMenuItem();
        jMenu15 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        MODULO_CARBON_PROCESAR_REGISTROS = new javax.swing.JMenuItem();
        MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS = new javax.swing.JMenuItem();
        MODULO_CARBON_INACTIVAR_REGISTRO = new javax.swing.JMenuItem();
        MODULO_CARBON_ACTIVAR_REGISTRO = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        MODULO_CARBON_GENERAR_MATRIZ = new javax.swing.JMenuItem();
        MODULO_CARBON_AGREGAR_REGISTRO = new javax.swing.JMenuItem();
        MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO = new javax.swing.JMenuItem();
        MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO = new javax.swing.JMenuItem();
        MODULO_CARBON_GENERAR_DISTRIBUCION = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        MODULO_EQUIPO_PROCESAR_REGISTROS = new javax.swing.JMenuItem();
        MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS = new javax.swing.JMenuItem();
        MODULO_EQUIPO_AUTORIZAR_RECOBRO = new javax.swing.JMenuItem();
        MODULO_EQUIPO_ACTIVAR_REGISTROS = new javax.swing.JMenuItem();
        MODULO_EQUIPO_INACTIVAR_REGISTROS = new javax.swing.JMenuItem();
        jMenu30 = new javax.swing.JMenu();
        MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO = new javax.swing.JMenuItem();
        MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        MODULO_EQUIPO_GENERAR_MATRIZ = new javax.swing.JMenuItem();
        MODULO_EQUIPO_AGREGAR_REGISTRO = new javax.swing.JMenuItem();
        MODULO_EQUIPO_GENERAR_DISTRIBUCION = new javax.swing.JMenuItem();
        jMenu34 = new javax.swing.JMenu();
        jMenu42 = new javax.swing.JMenu();
        jMenu45 = new javax.swing.JMenu();
        jMenu46 = new javax.swing.JMenu();
        MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_REGISTRAR = new javax.swing.JMenuItem();
        MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_CONSULTAR = new javax.swing.JMenuItem();
        MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_ACTUALIZAR = new javax.swing.JMenuItem();
        jMenu54 = new javax.swing.JMenu();
        MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_REGISTRAR = new javax.swing.JMenuItem();
        MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_CONSULTAR = new javax.swing.JMenuItem();
        MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_ACTUALIZAR = new javax.swing.JMenuItem();
        jMenu44 = new javax.swing.JMenu();
        MODULO_LIQUIDACION_PALERO_MARCACION_REGISTRAR = new javax.swing.JMenuItem();
        MODULO_LIQUIDACION_PALERO_MARCACION_CONSULTAR = new javax.swing.JMenuItem();
        MODULO_LIQUIDACION_PALERO_MARCACION_ACTUALIZAR = new javax.swing.JMenuItem();
        jMenu43 = new javax.swing.JMenu();
        MODULO_LIQUIDACION_PALERO_LIQUIDACION_GENERAR = new javax.swing.JMenuItem();
        MODULO_LIQUIDACION_PALERO_LIQUIDACION_CONSULTAR = new javax.swing.JMenuItem();
        jMenu47 = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenu12 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu48 = new javax.swing.JMenu();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenu49 = new javax.swing.JMenu();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenu50 = new javax.swing.JMenu();
        jMenuItem32 = new javax.swing.JMenuItem();
        jMenuItem33 = new javax.swing.JMenuItem();
        jMenuItem34 = new javax.swing.JMenuItem();
        jMenu51 = new javax.swing.JMenu();
        jMenuItem35 = new javax.swing.JMenuItem();
        jMenuItem36 = new javax.swing.JMenuItem();
        jMenuItem37 = new javax.swing.JMenuItem();
        jMenu52 = new javax.swing.JMenu();
        jMenuItem38 = new javax.swing.JMenuItem();
        jMenuItem39 = new javax.swing.JMenuItem();
        jMenuItem40 = new javax.swing.JMenuItem();
        jMenu53 = new javax.swing.JMenu();
        jMenuItem41 = new javax.swing.JMenuItem();
        jMenuItem42 = new javax.swing.JMenuItem();
        jMenuItem43 = new javax.swing.JMenuItem();
        jMenu14 = new javax.swing.JMenu();
        AUDITORIA_CONSULTAR = new javax.swing.JMenuItem();
        jMenu13 = new javax.swing.JMenu();
        jMenu16 = new javax.swing.JMenu();
        PERFIL_REGISTRAR = new javax.swing.JMenuItem();
        PERFIL_CONSULTAR = new javax.swing.JMenuItem();
        PERFIL_ACTUALIZAR = new javax.swing.JMenuItem();
        PERFIL_PERMISOS = new javax.swing.JMenuItem();
        jMenu18 = new javax.swing.JMenu();
        USUARIO_REGISTRAR = new javax.swing.JMenuItem();
        USUARIO_CONSULTAR = new javax.swing.JMenuItem();
        USUARIO_ACTUALIZAR = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        CENTROOPERACION_REGISTRAR = new javax.swing.JMenuItem();
        CENTROOPERACION_CONSULTAR = new javax.swing.JMenuItem();
        CENTROOPERACION_ACTUALIZAR = new javax.swing.JMenuItem();
        jMenu20 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        SUBCENTROOPERACION_REGISTRAR = new javax.swing.JMenuItem();
        SUBCENTROOPERACION_CONSULTAR = new javax.swing.JMenuItem();
        SUBCENTROOPERACION_ACTUALIZAR = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        CENTROCOSTOAUXILIAR_REGISTRAR = new javax.swing.JMenuItem();
        CENTROCOSTOAUXILIAR_CONSULTAR = new javax.swing.JMenuItem();
        CENTROCOSTOAUXILIAR_ACTUALIZAR = new javax.swing.JMenuItem();
        jMenu38 = new javax.swing.JMenu();
        CENTROCOSTO_REGISTRAR = new javax.swing.JMenuItem();
        CENTROCOSTO_CONSULTAR = new javax.swing.JMenuItem();
        CENTROCOSTO_ACTUALIZAR = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        CENTROCOSTOMAYOR_REGISTRAR = new javax.swing.JMenuItem();
        CENTROCOSTOMAYOR_CONSULTAR = new javax.swing.JMenuItem();
        CENTROCOSTOMAYOR_ACTUALIZAR = new javax.swing.JMenuItem();
        jMenu33 = new javax.swing.JMenu();
        COMPANIA_REGISTRAR = new javax.swing.JMenuItem();
        COMPANIA_CONSULTAR = new javax.swing.JMenuItem();
        COMPANIA_ACTUALIZAR = new javax.swing.JMenuItem();
        jMenu36 = new javax.swing.JMenu();
        LABOR_REALIZADA_REGISTRAR = new javax.swing.JMenuItem();
        LABOR_REALIZADA_CONSULTAR = new javax.swing.JMenuItem();
        LABOR_REALIZADA_ACTUALIZAR = new javax.swing.JMenuItem();
        jMenu37 = new javax.swing.JMenu();
        MOTIVO_PARADA_REGISTRAR = new javax.swing.JMenuItem();
        MOTIVO_PARADA_CONSULTAR = new javax.swing.JMenuItem();
        MOTIVO_PARADA_ACTUALIZAR = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        CLIENTE_REGISTRAR = new javax.swing.JMenuItem();
        CLIENTE_CONSULTAR = new javax.swing.JMenuItem();
        CLIENTE_ACTUALIZAR = new javax.swing.JMenuItem();
        CLIENTE_REGISTRO_CCARGA = new javax.swing.JMenuItem();
        jMenu26 = new javax.swing.JMenu();
        MOTONAVE_REGISTRAR = new javax.swing.JMenuItem();
        MOTONAVE_CONSULTAR = new javax.swing.JMenuItem();
        MOTONAVE_ACTUALIZAR = new javax.swing.JMenuItem();
        MOTONAVE_REGISTRO_CCARGA = new javax.swing.JMenuItem();
        jMenu27 = new javax.swing.JMenu();
        TRANSPORTADORA_REGISTRAR = new javax.swing.JMenuItem();
        TRANSPORTADORA_CONSULTAR = new javax.swing.JMenuItem();
        TRANSPORTADORA_ACTUALIZAR = new javax.swing.JMenuItem();
        TRANSPORTADORA_REGISTRO_CCARGA = new javax.swing.JMenuItem();
        jMenu28 = new javax.swing.JMenu();
        ARTICULO_REGISTRAR = new javax.swing.JMenuItem();
        ARTICULO_CONSULTAR = new javax.swing.JMenuItem();
        ARTICULO_ACTUALIZAR = new javax.swing.JMenuItem();
        ARTICULO_REGISTRO_CCARGA = new javax.swing.JMenuItem();
        jMenu22 = new javax.swing.JMenu();
        CUADRILLA_REGISTRAR = new javax.swing.JMenuItem();
        CUADRILLA_CONSULTAR = new javax.swing.JMenuItem();
        CUADRILLA_ACTUALIZAR = new javax.swing.JMenuItem();
        jMenu19 = new javax.swing.JMenu();
        EQUIPO_REGISTRAR = new javax.swing.JMenuItem();
        EQUIPO_CONSULTAR = new javax.swing.JMenuItem();
        EQUIPO_ACTUALIZAR = new javax.swing.JMenuItem();
        jMenu23 = new javax.swing.JMenu();
        EQUIPO_PERTENENCIA_REGISTRAR = new javax.swing.JMenuItem();
        EQUIPO_PERTENENCIA_CONSULTAR = new javax.swing.JMenuItem();
        EQUIPO_PERTENENCIA_ACTUALIZAR = new javax.swing.JMenuItem();
        jMenu24 = new javax.swing.JMenu();
        EQUIPO_PROVEEDOR_REGISTRAR = new javax.swing.JMenuItem();
        EQUIPO_PROVEEDOR_CONSULTAR = new javax.swing.JMenuItem();
        EQUIPO_PROVEEDOR_ACTUALIZAR = new javax.swing.JMenuItem();
        jMenu25 = new javax.swing.JMenu();
        EQUIPO_TIPO_REGISTRAR = new javax.swing.JMenuItem();
        EQUIPO_TIPO_CONSULTAR = new javax.swing.JMenuItem();
        EQUIPO_TIPO_ACTUALIZAR = new javax.swing.JMenuItem();
        EQUIPO_ERP = new javax.swing.JMenuItem();
        jMenu40 = new javax.swing.JMenu();
        EQUIPO_TARIFAS_REGISTRAR = new javax.swing.JMenuItem();
        jMenu35 = new javax.swing.JMenu();
        CAUSA_INACTIVIDAD_REGISTRAR = new javax.swing.JMenuItem();
        CAUSA_INACTIVIDAD_CONSULTAR = new javax.swing.JMenuItem();
        CAUSA_INACTIVIDAD_ACTUALIZAR = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu21 = new javax.swing.JMenu();
        jMenu17 = new javax.swing.JMenu();
        Zoom50 = new javax.swing.JMenuItem();
        Zoom70 = new javax.swing.JMenuItem();
        Zoom100 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu29 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CONTROL DE COSTOS");
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        panel.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(panel);

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(327, 35));

        jMenu41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_programacion3.png"))); // NOI18N
        jMenu41.setText("PROGRAMACIÓN");

        jMenu39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_equipoMovil.png"))); // NOI18N
        jMenu39.setText("Equipo");
        jMenu39.setPreferredSize(new java.awt.Dimension(150, 30));

        jMenu31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_solicitud2.png"))); // NOI18N
        jMenu31.setText("Solicitud");
        jMenu31.setPreferredSize(new java.awt.Dimension(350, 30));

        SOLICITUD_EQUIPOS_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        SOLICITUD_EQUIPOS_REGISTRAR.setText("Registrar");
        SOLICITUD_EQUIPOS_REGISTRAR.setPreferredSize(new java.awt.Dimension(350, 30));
        SOLICITUD_EQUIPOS_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SOLICITUD_EQUIPOS_REGISTRARActionPerformed(evt);
            }
        });
        jMenu31.add(SOLICITUD_EQUIPOS_REGISTRAR);

        jMenu39.add(jMenu31);

        jMenu32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_asignacion3.png"))); // NOI18N
        jMenu32.setText("Asignación");
        jMenu32.setPreferredSize(new java.awt.Dimension(180, 30));
        jMenu32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu32ActionPerformed(evt);
            }
        });

        ASIGNACION_EQUIPOS_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        ASIGNACION_EQUIPOS_REGISTRAR.setText("Registrar");
        ASIGNACION_EQUIPOS_REGISTRAR.setPreferredSize(new java.awt.Dimension(350, 30));
        ASIGNACION_EQUIPOS_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ASIGNACION_EQUIPOS_REGISTRARActionPerformed(evt);
            }
        });
        jMenu32.add(ASIGNACION_EQUIPOS_REGISTRAR);

        ASIGNACION_EQUIPOS_CONFIRMACION.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_confirmacionAsignacion.png"))); // NOI18N
        ASIGNACION_EQUIPOS_CONFIRMACION.setText("Confirmación");
        ASIGNACION_EQUIPOS_CONFIRMACION.setPreferredSize(new java.awt.Dimension(150, 30));
        ASIGNACION_EQUIPOS_CONFIRMACION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ASIGNACION_EQUIPOS_CONFIRMACIONActionPerformed(evt);
            }
        });
        jMenu32.add(ASIGNACION_EQUIPOS_CONFIRMACION);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        jMenuItem7.setText("Consultar");
        jMenuItem7.setPreferredSize(new java.awt.Dimension(150, 30));
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu32.add(jMenuItem7);

        jMenu39.add(jMenu32);

        PROGRAMACION_EQUIPOS_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_consultarProgramacion2.png"))); // NOI18N
        PROGRAMACION_EQUIPOS_CONSULTAR.setText("Consultar Programación");
        PROGRAMACION_EQUIPOS_CONSULTAR.setPreferredSize(new java.awt.Dimension(180, 30));
        PROGRAMACION_EQUIPOS_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PROGRAMACION_EQUIPOS_CONSULTARActionPerformed(evt);
            }
        });
        jMenu39.add(PROGRAMACION_EQUIPOS_CONSULTAR);

        PROGRAMACION_EQUIPOS_INACTIVAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_inactivar.png"))); // NOI18N
        PROGRAMACION_EQUIPOS_INACTIVAR.setText("Inactivar Programación");
        PROGRAMACION_EQUIPOS_INACTIVAR.setPreferredSize(new java.awt.Dimension(180, 30));
        PROGRAMACION_EQUIPOS_INACTIVAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PROGRAMACION_EQUIPOS_INACTIVARActionPerformed(evt);
            }
        });
        jMenu39.add(PROGRAMACION_EQUIPOS_INACTIVAR);

        PROGRAMACION_EQUIPOS_ACTIVAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_activar.png"))); // NOI18N
        PROGRAMACION_EQUIPOS_ACTIVAR.setText("Activar Programación");
        PROGRAMACION_EQUIPOS_ACTIVAR.setPreferredSize(new java.awt.Dimension(180, 30));
        PROGRAMACION_EQUIPOS_ACTIVAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PROGRAMACION_EQUIPOS_ACTIVARActionPerformed(evt);
            }
        });
        jMenu39.add(PROGRAMACION_EQUIPOS_ACTIVAR);

        PROGRAMACION_EQUIPOS_DIRECTA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_programacionDirecta.png"))); // NOI18N
        PROGRAMACION_EQUIPOS_DIRECTA.setText("Programación directa");
        PROGRAMACION_EQUIPOS_DIRECTA.setPreferredSize(new java.awt.Dimension(180, 30));
        PROGRAMACION_EQUIPOS_DIRECTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PROGRAMACION_EQUIPOS_DIRECTAActionPerformed(evt);
            }
        });
        jMenu39.add(PROGRAMACION_EQUIPOS_DIRECTA);

        ASIGNACION_EQUIPOS_EDITAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_editarAsignacion.png"))); // NOI18N
        ASIGNACION_EQUIPOS_EDITAR.setText("Editar Asignación");
        ASIGNACION_EQUIPOS_EDITAR.setPreferredSize(new java.awt.Dimension(180, 30));
        jMenu39.add(ASIGNACION_EQUIPOS_EDITAR);

        SOLICITUD_EQUIPOS_EDITAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_editarAsignacion.png"))); // NOI18N
        SOLICITUD_EQUIPOS_EDITAR.setText("Editar Solicitudes");
        SOLICITUD_EQUIPOS_EDITAR.setPreferredSize(new java.awt.Dimension(180, 30));
        jMenu39.add(SOLICITUD_EQUIPOS_EDITAR);

        jMenu41.add(jMenu39);

        jMenu15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_programacion_personal.png"))); // NOI18N
        jMenu15.setText("Personal");
        jMenu15.setPreferredSize(new java.awt.Dimension(150, 30));

        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        jMenuItem15.setText("Registrar");
        jMenuItem15.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenu15.add(jMenuItem15);

        jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        jMenuItem16.setText("Consultar");
        jMenuItem16.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenu15.add(jMenuItem16);

        jMenuItem17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        jMenuItem17.setText("Actualizar");
        jMenuItem17.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenu15.add(jMenuItem17);

        jMenu41.add(jMenu15);

        jMenuBar1.add(jMenu41);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/descargue.png"))); // NOI18N
        jMenu4.setText("MODULO CARBÓN");
        jMenu4.setPreferredSize(new java.awt.Dimension(180, 20));

        MODULO_CARBON_PROCESAR_REGISTROS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_procesar.png"))); // NOI18N
        MODULO_CARBON_PROCESAR_REGISTROS.setText("Procesar Registros");
        MODULO_CARBON_PROCESAR_REGISTROS.setPreferredSize(new java.awt.Dimension(350, 30));
        MODULO_CARBON_PROCESAR_REGISTROS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_CARBON_PROCESAR_REGISTROSActionPerformed(evt);
            }
        });
        jMenu4.add(MODULO_CARBON_PROCESAR_REGISTROS);

        MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_procesarProgramado2.png"))); // NOI18N
        MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS.setText("Programar Procesamiento Registros");
        MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS.setPreferredSize(new java.awt.Dimension(350, 30));
        MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROSActionPerformed(evt);
            }
        });
        jMenu4.add(MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS);

        MODULO_CARBON_INACTIVAR_REGISTRO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_inactivar2.png"))); // NOI18N
        MODULO_CARBON_INACTIVAR_REGISTRO.setText("Inactivar Registros MvtoCarbón");
        MODULO_CARBON_INACTIVAR_REGISTRO.setPreferredSize(new java.awt.Dimension(350, 30));
        MODULO_CARBON_INACTIVAR_REGISTRO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_CARBON_INACTIVAR_REGISTROActionPerformed(evt);
            }
        });
        jMenu4.add(MODULO_CARBON_INACTIVAR_REGISTRO);

        MODULO_CARBON_ACTIVAR_REGISTRO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_activar3.png"))); // NOI18N
        MODULO_CARBON_ACTIVAR_REGISTRO.setText("Activar Registros MvtoCarbón");
        MODULO_CARBON_ACTIVAR_REGISTRO.setPreferredSize(new java.awt.Dimension(350, 30));
        MODULO_CARBON_ACTIVAR_REGISTRO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_CARBON_ACTIVAR_REGISTROActionPerformed(evt);
            }
        });
        jMenu4.add(MODULO_CARBON_ACTIVAR_REGISTRO);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_editarAsignacion.png"))); // NOI18N
        jMenuItem3.setText("Modificar Registros");
        jMenuItem3.setPreferredSize(new java.awt.Dimension(350, 30));
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        MODULO_CARBON_GENERAR_MATRIZ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Matriz2.png"))); // NOI18N
        MODULO_CARBON_GENERAR_MATRIZ.setText("Generar Matriz MvtoCarbón");
        MODULO_CARBON_GENERAR_MATRIZ.setPreferredSize(new java.awt.Dimension(350, 30));
        MODULO_CARBON_GENERAR_MATRIZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_CARBON_GENERAR_MATRIZActionPerformed(evt);
            }
        });
        jMenu4.add(MODULO_CARBON_GENERAR_MATRIZ);

        MODULO_CARBON_AGREGAR_REGISTRO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_agregar.png"))); // NOI18N
        MODULO_CARBON_AGREGAR_REGISTRO.setText("Agregar MvtoCarbón");
        MODULO_CARBON_AGREGAR_REGISTRO.setPreferredSize(new java.awt.Dimension(350, 30));
        MODULO_CARBON_AGREGAR_REGISTRO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_CARBON_AGREGAR_REGISTROActionPerformed(evt);
            }
        });
        jMenu4.add(MODULO_CARBON_AGREGAR_REGISTRO);

        MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Debito.png"))); // NOI18N
        MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO.setText("Registrar Debito En ZonaTrabajo");
        MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO.setPreferredSize(new java.awt.Dimension(350, 30));
        MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJOActionPerformed(evt);
            }
        });
        jMenu4.add(MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO);

        MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Recaudo.png"))); // NOI18N
        MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO.setText("Informe Recaudo x Lavado Vehículo");
        MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO.setPreferredSize(new java.awt.Dimension(350, 30));
        MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULOActionPerformed(evt);
            }
        });
        jMenu4.add(MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO);

        MODULO_CARBON_GENERAR_DISTRIBUCION.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_distribucion.png"))); // NOI18N
        MODULO_CARBON_GENERAR_DISTRIBUCION.setText("Distribución MvtoCarbón");
        MODULO_CARBON_GENERAR_DISTRIBUCION.setPreferredSize(new java.awt.Dimension(350, 30));
        MODULO_CARBON_GENERAR_DISTRIBUCION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_CARBON_GENERAR_DISTRIBUCIONActionPerformed(evt);
            }
        });
        jMenu4.add(MODULO_CARBON_GENERAR_DISTRIBUCION);

        jMenuBar1.add(jMenu4);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_equipoMovil.png"))); // NOI18N
        jMenu3.setText("MODULO EQUIPO");
        jMenu3.setPreferredSize(new java.awt.Dimension(150, 20));

        MODULO_EQUIPO_PROCESAR_REGISTROS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_procesar.png"))); // NOI18N
        MODULO_EQUIPO_PROCESAR_REGISTROS.setText("Procesar Registros MvtoEquipo");
        MODULO_EQUIPO_PROCESAR_REGISTROS.setPreferredSize(new java.awt.Dimension(350, 30));
        MODULO_EQUIPO_PROCESAR_REGISTROS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_EQUIPO_PROCESAR_REGISTROSActionPerformed(evt);
            }
        });
        jMenu3.add(MODULO_EQUIPO_PROCESAR_REGISTROS);

        MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_procesarProgramado2.png"))); // NOI18N
        MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS.setText("Programar Procesamiento Registros");
        MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS.setPreferredSize(new java.awt.Dimension(350, 30));
        MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROSActionPerformed(evt);
            }
        });
        jMenu3.add(MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS);

        MODULO_EQUIPO_AUTORIZAR_RECOBRO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/select_check.png"))); // NOI18N
        MODULO_EQUIPO_AUTORIZAR_RECOBRO.setText("Autorización Recobro");
        MODULO_EQUIPO_AUTORIZAR_RECOBRO.setPreferredSize(new java.awt.Dimension(350, 30));
        MODULO_EQUIPO_AUTORIZAR_RECOBRO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_EQUIPO_AUTORIZAR_RECOBROActionPerformed(evt);
            }
        });
        jMenu3.add(MODULO_EQUIPO_AUTORIZAR_RECOBRO);

        MODULO_EQUIPO_ACTIVAR_REGISTROS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_activar3.png"))); // NOI18N
        MODULO_EQUIPO_ACTIVAR_REGISTROS.setText("Activar Registros MvtoEquipo");
        MODULO_EQUIPO_ACTIVAR_REGISTROS.setPreferredSize(new java.awt.Dimension(350, 30));
        MODULO_EQUIPO_ACTIVAR_REGISTROS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_EQUIPO_ACTIVAR_REGISTROSActionPerformed(evt);
            }
        });
        jMenu3.add(MODULO_EQUIPO_ACTIVAR_REGISTROS);

        MODULO_EQUIPO_INACTIVAR_REGISTROS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_inactivar2.png"))); // NOI18N
        MODULO_EQUIPO_INACTIVAR_REGISTROS.setText("Inactivar Registros MvtoEquipo");
        MODULO_EQUIPO_INACTIVAR_REGISTROS.setPreferredSize(new java.awt.Dimension(350, 30));
        MODULO_EQUIPO_INACTIVAR_REGISTROS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_EQUIPO_INACTIVAR_REGISTROSActionPerformed(evt);
            }
        });
        jMenu3.add(MODULO_EQUIPO_INACTIVAR_REGISTROS);

        jMenu30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_rendimiento3.png"))); // NOI18N
        jMenu30.setText("Rendimiento Equipos");
        jMenu30.setPreferredSize(new java.awt.Dimension(350, 30));

        MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_graficaRendimientoEquipo.png"))); // NOI18N
        MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO.setText("Graficar Rendimiento Equipos");
        MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO.setPreferredSize(new java.awt.Dimension(350, 30));
        MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPOActionPerformed(evt);
            }
        });
        jMenu30.add(MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO);

        MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_reporteRendimientoEquipo.png"))); // NOI18N
        MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO.setText("Informe Rendimiento Equipos");
        MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO.setPreferredSize(new java.awt.Dimension(350, 30));
        MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPOActionPerformed(evt);
            }
        });
        jMenu30.add(MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO);

        jMenu3.add(jMenu30);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_editarAsignacion.png"))); // NOI18N
        jMenuItem2.setText("Modificar Registros");
        jMenuItem2.setPreferredSize(new java.awt.Dimension(350, 30));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        MODULO_EQUIPO_GENERAR_MATRIZ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Matriz2.png"))); // NOI18N
        MODULO_EQUIPO_GENERAR_MATRIZ.setText("Generar Matriz MvtoEquipo");
        MODULO_EQUIPO_GENERAR_MATRIZ.setPreferredSize(new java.awt.Dimension(350, 30));
        MODULO_EQUIPO_GENERAR_MATRIZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_EQUIPO_GENERAR_MATRIZActionPerformed(evt);
            }
        });
        jMenu3.add(MODULO_EQUIPO_GENERAR_MATRIZ);

        MODULO_EQUIPO_AGREGAR_REGISTRO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_agregar.png"))); // NOI18N
        MODULO_EQUIPO_AGREGAR_REGISTRO.setText("Agregar MvtoEquipo");
        MODULO_EQUIPO_AGREGAR_REGISTRO.setPreferredSize(new java.awt.Dimension(350, 30));
        MODULO_EQUIPO_AGREGAR_REGISTRO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_EQUIPO_AGREGAR_REGISTROActionPerformed(evt);
            }
        });
        jMenu3.add(MODULO_EQUIPO_AGREGAR_REGISTRO);

        MODULO_EQUIPO_GENERAR_DISTRIBUCION.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_distribucion.png"))); // NOI18N
        MODULO_EQUIPO_GENERAR_DISTRIBUCION.setText("Distribución MvtoEquipo");
        MODULO_EQUIPO_GENERAR_DISTRIBUCION.setPreferredSize(new java.awt.Dimension(350, 30));
        MODULO_EQUIPO_GENERAR_DISTRIBUCION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_EQUIPO_GENERAR_DISTRIBUCIONActionPerformed(evt);
            }
        });
        jMenu3.add(MODULO_EQUIPO_GENERAR_DISTRIBUCION);

        jMenuBar1.add(jMenu3);

        jMenu34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_liquidacion.png"))); // NOI18N
        jMenu34.setText("LIQUIDACIÓN");

        jMenu42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_liquidacion_palero.png"))); // NOI18N
        jMenu42.setText("Palero");
        jMenu42.setPreferredSize(new java.awt.Dimension(180, 30));

        jMenu45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_procesar.png"))); // NOI18N
        jMenu45.setText("Parámetros");
        jMenu45.setPreferredSize(new java.awt.Dimension(250, 30));

        jMenu46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_configuracionLiquidacion.png"))); // NOI18N
        jMenu46.setText("Equipos Liquidación");
        jMenu46.setPreferredSize(new java.awt.Dimension(250, 30));

        MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_REGISTRAR.setText("Registrar");
        MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_REGISTRAR.setPreferredSize(new java.awt.Dimension(250, 30));
        MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_REGISTRARActionPerformed(evt);
            }
        });
        jMenu46.add(MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_REGISTRAR);

        MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_CONSULTAR.setText("Consultar");
        MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_CONSULTAR.setPreferredSize(new java.awt.Dimension(250, 30));
        MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_CONSULTAR.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_CONSULTARAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_CONSULTARActionPerformed(evt);
            }
        });
        jMenu46.add(MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_CONSULTAR);

        MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_ACTUALIZAR.setText("Actualizar");
        MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_ACTUALIZARActionPerformed(evt);
            }
        });
        jMenu46.add(MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_ACTUALIZAR);

        jMenu45.add(jMenu46);

        jMenu54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_procesar.png"))); // NOI18N
        jMenu54.setText("Configuración Liquidación");
        jMenu54.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenu54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu54ActionPerformed(evt);
            }
        });

        MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_REGISTRAR.setText("Registrar");
        MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_REGISTRAR.setPreferredSize(new java.awt.Dimension(250, 30));
        MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_REGISTRARActionPerformed(evt);
            }
        });
        jMenu54.add(MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_REGISTRAR);

        MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_CONSULTAR.setText("Consultar");
        MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_CONSULTAR.setPreferredSize(new java.awt.Dimension(250, 30));
        MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_CONSULTARActionPerformed(evt);
            }
        });
        jMenu54.add(MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_CONSULTAR);

        MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_ACTUALIZAR.setText("Actualizar");
        MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(250, 30));
        MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_ACTUALIZARActionPerformed(evt);
            }
        });
        jMenu54.add(MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_ACTUALIZAR);

        jMenu45.add(jMenu54);

        jMenu42.add(jMenu45);

        jMenu44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/marcacion.png"))); // NOI18N
        jMenu44.setText("Marcación");
        jMenu44.setPreferredSize(new java.awt.Dimension(250, 30));

        MODULO_LIQUIDACION_PALERO_MARCACION_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        MODULO_LIQUIDACION_PALERO_MARCACION_REGISTRAR.setText("Registrar");
        MODULO_LIQUIDACION_PALERO_MARCACION_REGISTRAR.setPreferredSize(new java.awt.Dimension(250, 30));
        MODULO_LIQUIDACION_PALERO_MARCACION_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_LIQUIDACION_PALERO_MARCACION_REGISTRARActionPerformed(evt);
            }
        });
        jMenu44.add(MODULO_LIQUIDACION_PALERO_MARCACION_REGISTRAR);

        MODULO_LIQUIDACION_PALERO_MARCACION_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        MODULO_LIQUIDACION_PALERO_MARCACION_CONSULTAR.setText("Consultar");
        MODULO_LIQUIDACION_PALERO_MARCACION_CONSULTAR.setPreferredSize(new java.awt.Dimension(250, 30));
        MODULO_LIQUIDACION_PALERO_MARCACION_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_LIQUIDACION_PALERO_MARCACION_CONSULTARActionPerformed(evt);
            }
        });
        jMenu44.add(MODULO_LIQUIDACION_PALERO_MARCACION_CONSULTAR);

        MODULO_LIQUIDACION_PALERO_MARCACION_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        MODULO_LIQUIDACION_PALERO_MARCACION_ACTUALIZAR.setText("Actualizar");
        MODULO_LIQUIDACION_PALERO_MARCACION_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(250, 30));
        MODULO_LIQUIDACION_PALERO_MARCACION_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_LIQUIDACION_PALERO_MARCACION_ACTUALIZARActionPerformed(evt);
            }
        });
        jMenu44.add(MODULO_LIQUIDACION_PALERO_MARCACION_ACTUALIZAR);

        jMenu42.add(jMenu44);

        jMenu43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_liquidación2.png"))); // NOI18N
        jMenu43.setText("Preliquidación");
        jMenu43.setPreferredSize(new java.awt.Dimension(270, 30));

        MODULO_LIQUIDACION_PALERO_LIQUIDACION_GENERAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        MODULO_LIQUIDACION_PALERO_LIQUIDACION_GENERAR.setText("Generar");
        MODULO_LIQUIDACION_PALERO_LIQUIDACION_GENERAR.setPreferredSize(new java.awt.Dimension(250, 30));
        MODULO_LIQUIDACION_PALERO_LIQUIDACION_GENERAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_LIQUIDACION_PALERO_LIQUIDACION_GENERARActionPerformed(evt);
            }
        });
        jMenu43.add(MODULO_LIQUIDACION_PALERO_LIQUIDACION_GENERAR);

        MODULO_LIQUIDACION_PALERO_LIQUIDACION_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        MODULO_LIQUIDACION_PALERO_LIQUIDACION_CONSULTAR.setText("Consultar");
        MODULO_LIQUIDACION_PALERO_LIQUIDACION_CONSULTAR.setPreferredSize(new java.awt.Dimension(250, 30));
        MODULO_LIQUIDACION_PALERO_LIQUIDACION_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MODULO_LIQUIDACION_PALERO_LIQUIDACION_CONSULTARActionPerformed(evt);
            }
        });
        jMenu43.add(MODULO_LIQUIDACION_PALERO_LIQUIDACION_CONSULTAR);

        jMenu42.add(jMenu43);

        jMenu47.setText("Liquidación");

        jMenuItem18.setText("Generar");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu47.add(jMenuItem18);

        jMenuItem19.setText("Consultar");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu47.add(jMenuItem19);

        jMenuItem20.setText("Consolidado x Periodo");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu47.add(jMenuItem20);

        jMenu42.add(jMenu47);

        jMenu34.add(jMenu42);

        jMenuBar1.add(jMenu34);

        jMenu12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Agua.png"))); // NOI18N
        jMenu12.setText("AGUA");
        jMenu12.setPreferredSize(new java.awt.Dimension(130, 20));
        jMenuBar1.add(jMenu12);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_personal.png"))); // NOI18N
        jMenu5.setText("PERSONAL");
        jMenu5.setPreferredSize(new java.awt.Dimension(130, 20));

        jMenu48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_tipoContrato.png"))); // NOI18N
        jMenu48.setText("Tipo Contrato");
        jMenu48.setPreferredSize(new java.awt.Dimension(250, 30));

        jMenuItem26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        jMenuItem26.setText("Registrar");
        jMenuItem26.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu48.add(jMenuItem26);

        jMenuItem27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        jMenuItem27.setText("Consultar");
        jMenuItem27.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu48.add(jMenuItem27);

        jMenuItem28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        jMenuItem28.setText("Actualizar");
        jMenuItem28.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu48.add(jMenuItem28);

        jMenu5.add(jMenu48);

        jMenu49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_jornada.png"))); // NOI18N
        jMenu49.setText("Jornada");
        jMenu49.setPreferredSize(new java.awt.Dimension(250, 30));

        jMenuItem29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        jMenuItem29.setText("Registrar");
        jMenuItem29.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jMenu49.add(jMenuItem29);

        jMenuItem30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        jMenuItem30.setText("Consultar");
        jMenuItem30.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu49.add(jMenuItem30);

        jMenuItem31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        jMenuItem31.setText("Actualizar");
        jMenuItem31.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        jMenu49.add(jMenuItem31);

        jMenu5.add(jMenu49);

        jMenu50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_situacionMedica.png"))); // NOI18N
        jMenu50.setText("Situacion_Medica");
        jMenu50.setPreferredSize(new java.awt.Dimension(250, 30));

        jMenuItem32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        jMenuItem32.setText("Registrar");
        jMenuItem32.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem32ActionPerformed(evt);
            }
        });
        jMenu50.add(jMenuItem32);

        jMenuItem33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        jMenuItem33.setText("Consultar");
        jMenuItem33.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem33ActionPerformed(evt);
            }
        });
        jMenu50.add(jMenuItem33);

        jMenuItem34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        jMenuItem34.setText("Actualizar");
        jMenuItem34.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenuItem34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem34ActionPerformed(evt);
            }
        });
        jMenu50.add(jMenuItem34);

        jMenu5.add(jMenu50);

        jMenu51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_cargoNomina.png"))); // NOI18N
        jMenu51.setText("Cargo Nomina");
        jMenu51.setPreferredSize(new java.awt.Dimension(250, 30));

        jMenuItem35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        jMenuItem35.setText("Registrar");
        jMenuItem35.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem35ActionPerformed(evt);
            }
        });
        jMenu51.add(jMenuItem35);

        jMenuItem36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        jMenuItem36.setText("Consultar");
        jMenuItem36.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem36ActionPerformed(evt);
            }
        });
        jMenu51.add(jMenuItem36);

        jMenuItem37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        jMenuItem37.setText("Actualizar");
        jMenuItem37.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenuItem37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem37ActionPerformed(evt);
            }
        });
        jMenu51.add(jMenuItem37);

        jMenu5.add(jMenu51);

        jMenu52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_personal.png"))); // NOI18N
        jMenu52.setText("Personal");
        jMenu52.setPreferredSize(new java.awt.Dimension(250, 30));

        jMenuItem38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        jMenuItem38.setText("Registrar");
        jMenuItem38.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenuItem38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem38ActionPerformed(evt);
            }
        });
        jMenu52.add(jMenuItem38);

        jMenuItem39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        jMenuItem39.setText("Consultar");
        jMenuItem39.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenuItem39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem39ActionPerformed(evt);
            }
        });
        jMenu52.add(jMenuItem39);

        jMenuItem40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        jMenuItem40.setText("Actualizar");
        jMenuItem40.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenuItem40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem40ActionPerformed(evt);
            }
        });
        jMenu52.add(jMenuItem40);

        jMenu5.add(jMenu52);

        jMenu53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_tipoDocumento.png"))); // NOI18N
        jMenu53.setText("Tipo Documento");
        jMenu53.setPreferredSize(new java.awt.Dimension(250, 30));

        jMenuItem41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        jMenuItem41.setText("Registrar");
        jMenuItem41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem41ActionPerformed(evt);
            }
        });
        jMenu53.add(jMenuItem41);

        jMenuItem42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        jMenuItem42.setText("Consultar");
        jMenuItem42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem42ActionPerformed(evt);
            }
        });
        jMenu53.add(jMenuItem42);

        jMenuItem43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        jMenuItem43.setText("Actualizar");
        jMenuItem43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem43ActionPerformed(evt);
            }
        });
        jMenu53.add(jMenuItem43);

        jMenu5.add(jMenu53);

        jMenuBar1.add(jMenu5);

        jMenu14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Auditoria.png"))); // NOI18N
        jMenu14.setText("AUDITORIA");
        jMenu14.setPreferredSize(new java.awt.Dimension(160, 20));

        AUDITORIA_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        AUDITORIA_CONSULTAR.setText("Consultar");
        AUDITORIA_CONSULTAR.setPreferredSize(new java.awt.Dimension(250, 30));
        AUDITORIA_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AUDITORIA_CONSULTARActionPerformed(evt);
            }
        });
        jMenu14.add(AUDITORIA_CONSULTAR);

        jMenuBar1.add(jMenu14);

        jMenu13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Configuraciones.png"))); // NOI18N
        jMenu13.setText("CATALOGOS");
        jMenu13.setPreferredSize(new java.awt.Dimension(120, 20));

        jMenu16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_perfil1.png"))); // NOI18N
        jMenu16.setText("Perfil");
        jMenu16.setPreferredSize(new java.awt.Dimension(250, 30));

        PERFIL_REGISTRAR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        PERFIL_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        PERFIL_REGISTRAR.setText("Registrar");
        PERFIL_REGISTRAR.setPreferredSize(new java.awt.Dimension(350, 30));
        PERFIL_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PERFIL_REGISTRARActionPerformed(evt);
            }
        });
        jMenu16.add(PERFIL_REGISTRAR);

        PERFIL_CONSULTAR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        PERFIL_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        PERFIL_CONSULTAR.setText("Consultar");
        PERFIL_CONSULTAR.setPreferredSize(new java.awt.Dimension(150, 30));
        PERFIL_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PERFIL_CONSULTARActionPerformed(evt);
            }
        });
        jMenu16.add(PERFIL_CONSULTAR);

        PERFIL_ACTUALIZAR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        PERFIL_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        PERFIL_ACTUALIZAR.setText("Actualizar");
        PERFIL_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(150, 30));
        PERFIL_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PERFIL_ACTUALIZARActionPerformed(evt);
            }
        });
        jMenu16.add(PERFIL_ACTUALIZAR);

        PERFIL_PERMISOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Submenu.png"))); // NOI18N
        PERFIL_PERMISOS.setText("Permisos");
        PERFIL_PERMISOS.setPreferredSize(new java.awt.Dimension(150, 30));
        PERFIL_PERMISOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PERFIL_PERMISOSActionPerformed(evt);
            }
        });
        jMenu16.add(PERFIL_PERMISOS);

        jMenu13.add(jMenu16);

        jMenu18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_usuariof.png"))); // NOI18N
        jMenu18.setText("Usuario");
        jMenu18.setPreferredSize(new java.awt.Dimension(150, 30));

        USUARIO_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        USUARIO_REGISTRAR.setText("Registrar");
        USUARIO_REGISTRAR.setPreferredSize(new java.awt.Dimension(350, 30));
        USUARIO_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                USUARIO_REGISTRARActionPerformed(evt);
            }
        });
        jMenu18.add(USUARIO_REGISTRAR);

        USUARIO_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        USUARIO_CONSULTAR.setText("Consultar");
        USUARIO_CONSULTAR.setPreferredSize(new java.awt.Dimension(350, 30));
        USUARIO_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                USUARIO_CONSULTARActionPerformed(evt);
            }
        });
        jMenu18.add(USUARIO_CONSULTAR);

        USUARIO_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        USUARIO_ACTUALIZAR.setText("Actualizar");
        USUARIO_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(350, 30));
        USUARIO_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                USUARIO_ACTUALIZARActionPerformed(evt);
            }
        });
        jMenu18.add(USUARIO_ACTUALIZAR);

        jMenu13.add(jMenu18);

        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_centroOperacion.png"))); // NOI18N
        jMenu8.setText("Centro Operacion");
        jMenu8.setPreferredSize(new java.awt.Dimension(150, 30));

        CENTROOPERACION_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        CENTROOPERACION_REGISTRAR.setText("Registrar");
        CENTROOPERACION_REGISTRAR.setPreferredSize(new java.awt.Dimension(350, 30));
        CENTROOPERACION_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CENTROOPERACION_REGISTRARActionPerformed(evt);
            }
        });
        jMenu8.add(CENTROOPERACION_REGISTRAR);

        CENTROOPERACION_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        CENTROOPERACION_CONSULTAR.setText("Consultar");
        CENTROOPERACION_CONSULTAR.setPreferredSize(new java.awt.Dimension(350, 30));
        CENTROOPERACION_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CENTROOPERACION_CONSULTARActionPerformed(evt);
            }
        });
        jMenu8.add(CENTROOPERACION_CONSULTAR);

        CENTROOPERACION_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        CENTROOPERACION_ACTUALIZAR.setText("Actualizar");
        CENTROOPERACION_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(350, 30));
        CENTROOPERACION_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CENTROOPERACION_ACTUALIZARActionPerformed(evt);
            }
        });
        jMenu8.add(CENTROOPERACION_ACTUALIZAR);

        jMenu13.add(jMenu8);

        jMenu20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_centroCosto.png"))); // NOI18N
        jMenu20.setText("Centro Costo");
        jMenu20.setPreferredSize(new java.awt.Dimension(150, 30));

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Submenu.png"))); // NOI18N
        jMenu2.setText("SubCentro");
        jMenu2.setPreferredSize(new java.awt.Dimension(150, 30));

        SUBCENTROOPERACION_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        SUBCENTROOPERACION_REGISTRAR.setText("Registrar");
        SUBCENTROOPERACION_REGISTRAR.setPreferredSize(new java.awt.Dimension(350, 30));
        SUBCENTROOPERACION_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SUBCENTROOPERACION_REGISTRARActionPerformed(evt);
            }
        });
        jMenu2.add(SUBCENTROOPERACION_REGISTRAR);

        SUBCENTROOPERACION_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        SUBCENTROOPERACION_CONSULTAR.setText("Consultar");
        SUBCENTROOPERACION_CONSULTAR.setPreferredSize(new java.awt.Dimension(350, 30));
        SUBCENTROOPERACION_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SUBCENTROOPERACION_CONSULTARActionPerformed(evt);
            }
        });
        jMenu2.add(SUBCENTROOPERACION_CONSULTAR);

        SUBCENTROOPERACION_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        SUBCENTROOPERACION_ACTUALIZAR.setText("Actualizar");
        SUBCENTROOPERACION_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(350, 30));
        SUBCENTROOPERACION_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SUBCENTROOPERACION_ACTUALIZARActionPerformed(evt);
            }
        });
        jMenu2.add(SUBCENTROOPERACION_ACTUALIZAR);

        jMenu20.add(jMenu2);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Submenu.png"))); // NOI18N
        jMenu1.setText("Auxiliares");
        jMenu1.setPreferredSize(new java.awt.Dimension(150, 30));
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        CENTROCOSTOAUXILIAR_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        CENTROCOSTOAUXILIAR_REGISTRAR.setText("Registrar");
        CENTROCOSTOAUXILIAR_REGISTRAR.setPreferredSize(new java.awt.Dimension(250, 30));
        CENTROCOSTOAUXILIAR_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CENTROCOSTOAUXILIAR_REGISTRARActionPerformed(evt);
            }
        });
        jMenu1.add(CENTROCOSTOAUXILIAR_REGISTRAR);

        CENTROCOSTOAUXILIAR_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        CENTROCOSTOAUXILIAR_CONSULTAR.setText("Consultar");
        CENTROCOSTOAUXILIAR_CONSULTAR.setPreferredSize(new java.awt.Dimension(150, 30));
        CENTROCOSTOAUXILIAR_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CENTROCOSTOAUXILIAR_CONSULTARActionPerformed(evt);
            }
        });
        jMenu1.add(CENTROCOSTOAUXILIAR_CONSULTAR);

        CENTROCOSTOAUXILIAR_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        CENTROCOSTOAUXILIAR_ACTUALIZAR.setText("Actualizar");
        CENTROCOSTOAUXILIAR_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(150, 30));
        CENTROCOSTOAUXILIAR_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CENTROCOSTOAUXILIAR_ACTUALIZARActionPerformed(evt);
            }
        });
        jMenu1.add(CENTROCOSTOAUXILIAR_ACTUALIZAR);

        jMenu20.add(jMenu1);

        jMenu38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Submenu.png"))); // NOI18N
        jMenu38.setText("CentroCosto");
        jMenu38.setPreferredSize(new java.awt.Dimension(150, 30));

        CENTROCOSTO_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        CENTROCOSTO_REGISTRAR.setText("Registrar");
        CENTROCOSTO_REGISTRAR.setPreferredSize(new java.awt.Dimension(250, 30));
        CENTROCOSTO_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CENTROCOSTO_REGISTRARActionPerformed(evt);
            }
        });
        jMenu38.add(CENTROCOSTO_REGISTRAR);

        CENTROCOSTO_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        CENTROCOSTO_CONSULTAR.setText("Consultar");
        CENTROCOSTO_CONSULTAR.setPreferredSize(new java.awt.Dimension(250, 30));
        CENTROCOSTO_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CENTROCOSTO_CONSULTARActionPerformed(evt);
            }
        });
        jMenu38.add(CENTROCOSTO_CONSULTAR);

        CENTROCOSTO_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        CENTROCOSTO_ACTUALIZAR.setText("Actualizar");
        CENTROCOSTO_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(250, 30));
        CENTROCOSTO_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CENTROCOSTO_ACTUALIZARActionPerformed(evt);
            }
        });
        jMenu38.add(CENTROCOSTO_ACTUALIZAR);

        jMenu20.add(jMenu38);

        jMenu6.setText("CentroCostoMayor");
        jMenu6.setPreferredSize(new java.awt.Dimension(150, 30));

        CENTROCOSTOMAYOR_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        CENTROCOSTOMAYOR_REGISTRAR.setText("Registrar");
        CENTROCOSTOMAYOR_REGISTRAR.setPreferredSize(new java.awt.Dimension(250, 30));
        CENTROCOSTOMAYOR_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CENTROCOSTOMAYOR_REGISTRARActionPerformed(evt);
            }
        });
        jMenu6.add(CENTROCOSTOMAYOR_REGISTRAR);

        CENTROCOSTOMAYOR_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        CENTROCOSTOMAYOR_CONSULTAR.setText("Consultar");
        CENTROCOSTOMAYOR_CONSULTAR.setPreferredSize(new java.awt.Dimension(250, 30));
        CENTROCOSTOMAYOR_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CENTROCOSTOMAYOR_CONSULTARActionPerformed(evt);
            }
        });
        jMenu6.add(CENTROCOSTOMAYOR_CONSULTAR);

        CENTROCOSTOMAYOR_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        CENTROCOSTOMAYOR_ACTUALIZAR.setText("Actualizar");
        CENTROCOSTOMAYOR_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(250, 30));
        CENTROCOSTOMAYOR_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CENTROCOSTOMAYOR_ACTUALIZARActionPerformed(evt);
            }
        });
        jMenu6.add(CENTROCOSTOMAYOR_ACTUALIZAR);

        jMenu20.add(jMenu6);

        jMenu13.add(jMenu20);

        jMenu33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_companiaF.png"))); // NOI18N
        jMenu33.setText("Compañia");
        jMenu33.setPreferredSize(new java.awt.Dimension(150, 30));

        COMPANIA_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        COMPANIA_REGISTRAR.setText("Registrar");
        COMPANIA_REGISTRAR.setPreferredSize(new java.awt.Dimension(350, 30));
        COMPANIA_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                COMPANIA_REGISTRARActionPerformed(evt);
            }
        });
        jMenu33.add(COMPANIA_REGISTRAR);

        COMPANIA_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        COMPANIA_CONSULTAR.setText("Consultar");
        COMPANIA_CONSULTAR.setPreferredSize(new java.awt.Dimension(350, 30));
        COMPANIA_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                COMPANIA_CONSULTARActionPerformed(evt);
            }
        });
        jMenu33.add(COMPANIA_CONSULTAR);

        COMPANIA_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        COMPANIA_ACTUALIZAR.setText("Actualizar");
        COMPANIA_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(350, 30));
        COMPANIA_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                COMPANIA_ACTUALIZARActionPerformed(evt);
            }
        });
        jMenu33.add(COMPANIA_ACTUALIZAR);

        jMenu13.add(jMenu33);

        jMenu36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_laborRealizadaF.png"))); // NOI18N
        jMenu36.setText("Labor Realizadas");
        jMenu36.setPreferredSize(new java.awt.Dimension(150, 30));

        LABOR_REALIZADA_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        LABOR_REALIZADA_REGISTRAR.setText("Registrar");
        LABOR_REALIZADA_REGISTRAR.setPreferredSize(new java.awt.Dimension(350, 30));
        LABOR_REALIZADA_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LABOR_REALIZADA_REGISTRARActionPerformed(evt);
            }
        });
        jMenu36.add(LABOR_REALIZADA_REGISTRAR);

        LABOR_REALIZADA_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        LABOR_REALIZADA_CONSULTAR.setText("Consultar");
        LABOR_REALIZADA_CONSULTAR.setPreferredSize(new java.awt.Dimension(350, 30));
        LABOR_REALIZADA_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LABOR_REALIZADA_CONSULTARActionPerformed(evt);
            }
        });
        jMenu36.add(LABOR_REALIZADA_CONSULTAR);

        LABOR_REALIZADA_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        LABOR_REALIZADA_ACTUALIZAR.setText("Actualizar");
        LABOR_REALIZADA_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(350, 30));
        LABOR_REALIZADA_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LABOR_REALIZADA_ACTUALIZARActionPerformed(evt);
            }
        });
        jMenu36.add(LABOR_REALIZADA_ACTUALIZAR);

        jMenu13.add(jMenu36);

        jMenu37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_motivoParada.png"))); // NOI18N
        jMenu37.setText("Motivo Parada");
        jMenu37.setPreferredSize(new java.awt.Dimension(150, 30));

        MOTIVO_PARADA_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        MOTIVO_PARADA_REGISTRAR.setText("Registrar");
        MOTIVO_PARADA_REGISTRAR.setPreferredSize(new java.awt.Dimension(350, 30));
        MOTIVO_PARADA_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MOTIVO_PARADA_REGISTRARActionPerformed(evt);
            }
        });
        jMenu37.add(MOTIVO_PARADA_REGISTRAR);

        MOTIVO_PARADA_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        MOTIVO_PARADA_CONSULTAR.setText("Consultar");
        MOTIVO_PARADA_CONSULTAR.setPreferredSize(new java.awt.Dimension(350, 30));
        MOTIVO_PARADA_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MOTIVO_PARADA_CONSULTARActionPerformed(evt);
            }
        });
        jMenu37.add(MOTIVO_PARADA_CONSULTAR);

        MOTIVO_PARADA_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        MOTIVO_PARADA_ACTUALIZAR.setText("Actualizar");
        MOTIVO_PARADA_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(350, 30));
        MOTIVO_PARADA_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MOTIVO_PARADA_ACTUALIZARActionPerformed(evt);
            }
        });
        jMenu37.add(MOTIVO_PARADA_ACTUALIZAR);

        jMenu13.add(jMenu37);

        jMenu11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_client.png"))); // NOI18N
        jMenu11.setText("Cliente");
        jMenu11.setPreferredSize(new java.awt.Dimension(150, 30));

        CLIENTE_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        CLIENTE_REGISTRAR.setText("Registrar");
        CLIENTE_REGISTRAR.setPreferredSize(new java.awt.Dimension(250, 30));
        CLIENTE_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLIENTE_REGISTRARActionPerformed(evt);
            }
        });
        jMenu11.add(CLIENTE_REGISTRAR);

        CLIENTE_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        CLIENTE_CONSULTAR.setText("Consultar");
        CLIENTE_CONSULTAR.setPreferredSize(new java.awt.Dimension(250, 30));
        CLIENTE_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLIENTE_CONSULTARActionPerformed(evt);
            }
        });
        jMenu11.add(CLIENTE_CONSULTAR);

        CLIENTE_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        CLIENTE_ACTUALIZAR.setText("Actualizar");
        CLIENTE_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenu11.add(CLIENTE_ACTUALIZAR);

        CLIENTE_REGISTRO_CCARGA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_sincronizarControlCarga.png"))); // NOI18N
        CLIENTE_REGISTRO_CCARGA.setText("Clientes Ccarga");
        CLIENTE_REGISTRO_CCARGA.setPreferredSize(new java.awt.Dimension(250, 30));
        CLIENTE_REGISTRO_CCARGA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLIENTE_REGISTRO_CCARGAActionPerformed(evt);
            }
        });
        jMenu11.add(CLIENTE_REGISTRO_CCARGA);

        jMenu13.add(jMenu11);

        jMenu26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_motonaveF_.png"))); // NOI18N
        jMenu26.setText("Motonave");
        jMenu26.setPreferredSize(new java.awt.Dimension(150, 30));

        MOTONAVE_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        MOTONAVE_REGISTRAR.setText("Registrar");
        MOTONAVE_REGISTRAR.setPreferredSize(new java.awt.Dimension(350, 30));
        MOTONAVE_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MOTONAVE_REGISTRARActionPerformed(evt);
            }
        });
        jMenu26.add(MOTONAVE_REGISTRAR);

        MOTONAVE_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        MOTONAVE_CONSULTAR.setText("Consultar");
        MOTONAVE_CONSULTAR.setPreferredSize(new java.awt.Dimension(150, 30));
        MOTONAVE_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MOTONAVE_CONSULTARActionPerformed(evt);
            }
        });
        jMenu26.add(MOTONAVE_CONSULTAR);

        MOTONAVE_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        MOTONAVE_ACTUALIZAR.setText("Actualizar");
        MOTONAVE_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(150, 30));
        MOTONAVE_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MOTONAVE_ACTUALIZARActionPerformed(evt);
            }
        });
        jMenu26.add(MOTONAVE_ACTUALIZAR);

        MOTONAVE_REGISTRO_CCARGA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_sincronizarControlCarga.png"))); // NOI18N
        MOTONAVE_REGISTRO_CCARGA.setText("Motonaves Ccarga GP");
        MOTONAVE_REGISTRO_CCARGA.setPreferredSize(new java.awt.Dimension(150, 30));
        MOTONAVE_REGISTRO_CCARGA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MOTONAVE_REGISTRO_CCARGAActionPerformed(evt);
            }
        });
        jMenu26.add(MOTONAVE_REGISTRO_CCARGA);

        jMenu13.add(jMenu26);

        jMenu27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_transportadora.png"))); // NOI18N
        jMenu27.setText("Transportadora");
        jMenu27.setPreferredSize(new java.awt.Dimension(150, 30));

        TRANSPORTADORA_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        TRANSPORTADORA_REGISTRAR.setText("Registrar");
        TRANSPORTADORA_REGISTRAR.setPreferredSize(new java.awt.Dimension(350, 30));
        TRANSPORTADORA_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TRANSPORTADORA_REGISTRARActionPerformed(evt);
            }
        });
        jMenu27.add(TRANSPORTADORA_REGISTRAR);

        TRANSPORTADORA_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        TRANSPORTADORA_CONSULTAR.setText("Consultar");
        TRANSPORTADORA_CONSULTAR.setPreferredSize(new java.awt.Dimension(150, 30));
        TRANSPORTADORA_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TRANSPORTADORA_CONSULTARActionPerformed(evt);
            }
        });
        jMenu27.add(TRANSPORTADORA_CONSULTAR);

        TRANSPORTADORA_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        TRANSPORTADORA_ACTUALIZAR.setText("Actualizar");
        TRANSPORTADORA_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(150, 30));
        TRANSPORTADORA_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TRANSPORTADORA_ACTUALIZARActionPerformed(evt);
            }
        });
        jMenu27.add(TRANSPORTADORA_ACTUALIZAR);

        TRANSPORTADORA_REGISTRO_CCARGA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_sincronizarControlCarga.png"))); // NOI18N
        TRANSPORTADORA_REGISTRO_CCARGA.setText("Transportadoras Ccarga GP");
        TRANSPORTADORA_REGISTRO_CCARGA.setPreferredSize(new java.awt.Dimension(150, 30));
        TRANSPORTADORA_REGISTRO_CCARGA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TRANSPORTADORA_REGISTRO_CCARGAActionPerformed(evt);
            }
        });
        jMenu27.add(TRANSPORTADORA_REGISTRO_CCARGA);

        jMenu13.add(jMenu27);

        jMenu28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_product.png"))); // NOI18N
        jMenu28.setText("Articulo");
        jMenu28.setPreferredSize(new java.awt.Dimension(150, 30));

        ARTICULO_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        ARTICULO_REGISTRAR.setText("Registrar");
        ARTICULO_REGISTRAR.setPreferredSize(new java.awt.Dimension(350, 30));
        ARTICULO_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ARTICULO_REGISTRARActionPerformed(evt);
            }
        });
        jMenu28.add(ARTICULO_REGISTRAR);

        ARTICULO_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        ARTICULO_CONSULTAR.setText("Consultar");
        ARTICULO_CONSULTAR.setPreferredSize(new java.awt.Dimension(150, 30));
        ARTICULO_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ARTICULO_CONSULTARActionPerformed(evt);
            }
        });
        jMenu28.add(ARTICULO_CONSULTAR);

        ARTICULO_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        ARTICULO_ACTUALIZAR.setText("Actualizar");
        ARTICULO_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(150, 30));
        ARTICULO_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ARTICULO_ACTUALIZARActionPerformed(evt);
            }
        });
        jMenu28.add(ARTICULO_ACTUALIZAR);

        ARTICULO_REGISTRO_CCARGA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_sincronizarControlCarga.png"))); // NOI18N
        ARTICULO_REGISTRO_CCARGA.setText("Articulos Ccarga GP");
        ARTICULO_REGISTRO_CCARGA.setPreferredSize(new java.awt.Dimension(150, 30));
        ARTICULO_REGISTRO_CCARGA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ARTICULO_REGISTRO_CCARGAActionPerformed(evt);
            }
        });
        jMenu28.add(ARTICULO_REGISTRO_CCARGA);

        jMenu13.add(jMenu28);

        jMenu22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_cuadrilla.png"))); // NOI18N
        jMenu22.setText("Cuadrillas");
        jMenu22.setPreferredSize(new java.awt.Dimension(150, 30));

        CUADRILLA_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        CUADRILLA_REGISTRAR.setText("Registrar");
        CUADRILLA_REGISTRAR.setPreferredSize(new java.awt.Dimension(350, 30));
        CUADRILLA_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CUADRILLA_REGISTRARActionPerformed(evt);
            }
        });
        jMenu22.add(CUADRILLA_REGISTRAR);

        CUADRILLA_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        CUADRILLA_CONSULTAR.setText("Consultar");
        CUADRILLA_CONSULTAR.setPreferredSize(new java.awt.Dimension(150, 30));
        CUADRILLA_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CUADRILLA_CONSULTARActionPerformed(evt);
            }
        });
        jMenu22.add(CUADRILLA_CONSULTAR);

        CUADRILLA_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        CUADRILLA_ACTUALIZAR.setText("Actualizar");
        CUADRILLA_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(150, 30));
        jMenu22.add(CUADRILLA_ACTUALIZAR);

        jMenu13.add(jMenu22);

        jMenu19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_equipoMovil.png"))); // NOI18N
        jMenu19.setText("Equipo");
        jMenu19.setPreferredSize(new java.awt.Dimension(150, 30));

        EQUIPO_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        EQUIPO_REGISTRAR.setText("Registrar");
        EQUIPO_REGISTRAR.setPreferredSize(new java.awt.Dimension(240, 30));
        EQUIPO_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EQUIPO_REGISTRARActionPerformed(evt);
            }
        });
        jMenu19.add(EQUIPO_REGISTRAR);

        EQUIPO_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        EQUIPO_CONSULTAR.setText("Consultar");
        EQUIPO_CONSULTAR.setPreferredSize(new java.awt.Dimension(240, 30));
        EQUIPO_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EQUIPO_CONSULTARActionPerformed(evt);
            }
        });
        jMenu19.add(EQUIPO_CONSULTAR);

        EQUIPO_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        EQUIPO_ACTUALIZAR.setText("Actualizar");
        EQUIPO_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(240, 30));
        EQUIPO_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EQUIPO_ACTUALIZARActionPerformed(evt);
            }
        });
        jMenu19.add(EQUIPO_ACTUALIZAR);

        jMenu23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Submenu.png"))); // NOI18N
        jMenu23.setText("Pertenencia Equipos");
        jMenu23.setPreferredSize(new java.awt.Dimension(240, 30));

        EQUIPO_PERTENENCIA_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        EQUIPO_PERTENENCIA_REGISTRAR.setText("Registrar");
        EQUIPO_PERTENENCIA_REGISTRAR.setPreferredSize(new java.awt.Dimension(350, 30));
        EQUIPO_PERTENENCIA_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EQUIPO_PERTENENCIA_REGISTRARActionPerformed(evt);
            }
        });
        jMenu23.add(EQUIPO_PERTENENCIA_REGISTRAR);

        EQUIPO_PERTENENCIA_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        EQUIPO_PERTENENCIA_CONSULTAR.setText("Consultar");
        EQUIPO_PERTENENCIA_CONSULTAR.setPreferredSize(new java.awt.Dimension(150, 30));
        EQUIPO_PERTENENCIA_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EQUIPO_PERTENENCIA_CONSULTARActionPerformed(evt);
            }
        });
        jMenu23.add(EQUIPO_PERTENENCIA_CONSULTAR);

        EQUIPO_PERTENENCIA_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        EQUIPO_PERTENENCIA_ACTUALIZAR.setText("Actualizar");
        EQUIPO_PERTENENCIA_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(150, 30));
        EQUIPO_PERTENENCIA_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EQUIPO_PERTENENCIA_ACTUALIZARActionPerformed(evt);
            }
        });
        jMenu23.add(EQUIPO_PERTENENCIA_ACTUALIZAR);

        jMenu19.add(jMenu23);

        jMenu24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Submenu.png"))); // NOI18N
        jMenu24.setText("Proveedor");
        jMenu24.setPreferredSize(new java.awt.Dimension(240, 30));

        EQUIPO_PROVEEDOR_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        EQUIPO_PROVEEDOR_REGISTRAR.setText("Registrar");
        EQUIPO_PROVEEDOR_REGISTRAR.setPreferredSize(new java.awt.Dimension(350, 30));
        EQUIPO_PROVEEDOR_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EQUIPO_PROVEEDOR_REGISTRARActionPerformed(evt);
            }
        });
        jMenu24.add(EQUIPO_PROVEEDOR_REGISTRAR);

        EQUIPO_PROVEEDOR_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        EQUIPO_PROVEEDOR_CONSULTAR.setText("Consultar");
        EQUIPO_PROVEEDOR_CONSULTAR.setPreferredSize(new java.awt.Dimension(150, 30));
        EQUIPO_PROVEEDOR_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EQUIPO_PROVEEDOR_CONSULTARActionPerformed(evt);
            }
        });
        jMenu24.add(EQUIPO_PROVEEDOR_CONSULTAR);

        EQUIPO_PROVEEDOR_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        EQUIPO_PROVEEDOR_ACTUALIZAR.setText("Actualizar");
        EQUIPO_PROVEEDOR_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(150, 30));
        EQUIPO_PROVEEDOR_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EQUIPO_PROVEEDOR_ACTUALIZARActionPerformed(evt);
            }
        });
        jMenu24.add(EQUIPO_PROVEEDOR_ACTUALIZAR);

        jMenu19.add(jMenu24);

        jMenu25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Submenu.png"))); // NOI18N
        jMenu25.setText("Tipo");
        jMenu25.setPreferredSize(new java.awt.Dimension(240, 30));

        EQUIPO_TIPO_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        EQUIPO_TIPO_REGISTRAR.setText("Registrar");
        EQUIPO_TIPO_REGISTRAR.setPreferredSize(new java.awt.Dimension(350, 30));
        EQUIPO_TIPO_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EQUIPO_TIPO_REGISTRARActionPerformed(evt);
            }
        });
        jMenu25.add(EQUIPO_TIPO_REGISTRAR);

        EQUIPO_TIPO_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        EQUIPO_TIPO_CONSULTAR.setText("Consultar");
        EQUIPO_TIPO_CONSULTAR.setPreferredSize(new java.awt.Dimension(150, 30));
        EQUIPO_TIPO_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EQUIPO_TIPO_CONSULTARActionPerformed(evt);
            }
        });
        jMenu25.add(EQUIPO_TIPO_CONSULTAR);

        EQUIPO_TIPO_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        EQUIPO_TIPO_ACTUALIZAR.setText("Actualizar");
        EQUIPO_TIPO_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(150, 30));
        EQUIPO_TIPO_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EQUIPO_TIPO_ACTUALIZARActionPerformed(evt);
            }
        });
        jMenu25.add(EQUIPO_TIPO_ACTUALIZAR);

        jMenu19.add(jMenu25);

        EQUIPO_ERP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Submenu.png"))); // NOI18N
        EQUIPO_ERP.setText("Equipos ERP");
        EQUIPO_ERP.setPreferredSize(new java.awt.Dimension(240, 30));
        EQUIPO_ERP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EQUIPO_ERPActionPerformed(evt);
            }
        });
        jMenu19.add(EQUIPO_ERP);

        jMenu40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Submenu.png"))); // NOI18N
        jMenu40.setText("Tarifas en Equipos");
        jMenu40.setPreferredSize(new java.awt.Dimension(240, 30));

        EQUIPO_TARIFAS_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        EQUIPO_TARIFAS_REGISTRAR.setText("Registrar");
        EQUIPO_TARIFAS_REGISTRAR.setPreferredSize(new java.awt.Dimension(240, 30));
        EQUIPO_TARIFAS_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EQUIPO_TARIFAS_REGISTRARActionPerformed(evt);
            }
        });
        jMenu40.add(EQUIPO_TARIFAS_REGISTRAR);

        jMenu19.add(jMenu40);

        jMenu13.add(jMenu19);

        jMenu35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_causalInactividad.png"))); // NOI18N
        jMenu35.setText("Causa Inactividad");
        jMenu35.setPreferredSize(new java.awt.Dimension(150, 30));

        CAUSA_INACTIVIDAD_REGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        CAUSA_INACTIVIDAD_REGISTRAR.setText("Registrar");
        CAUSA_INACTIVIDAD_REGISTRAR.setPreferredSize(new java.awt.Dimension(240, 30));
        CAUSA_INACTIVIDAD_REGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CAUSA_INACTIVIDAD_REGISTRARActionPerformed(evt);
            }
        });
        jMenu35.add(CAUSA_INACTIVIDAD_REGISTRAR);

        CAUSA_INACTIVIDAD_CONSULTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        CAUSA_INACTIVIDAD_CONSULTAR.setText("Consultar");
        CAUSA_INACTIVIDAD_CONSULTAR.setPreferredSize(new java.awt.Dimension(240, 30));
        CAUSA_INACTIVIDAD_CONSULTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CAUSA_INACTIVIDAD_CONSULTARActionPerformed(evt);
            }
        });
        jMenu35.add(CAUSA_INACTIVIDAD_CONSULTAR);

        CAUSA_INACTIVIDAD_ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Submenu.png"))); // NOI18N
        CAUSA_INACTIVIDAD_ACTUALIZAR.setText("Actualizar");
        CAUSA_INACTIVIDAD_ACTUALIZAR.setPreferredSize(new java.awt.Dimension(240, 30));
        CAUSA_INACTIVIDAD_ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CAUSA_INACTIVIDAD_ACTUALIZARActionPerformed(evt);
            }
        });
        jMenu35.add(CAUSA_INACTIVIDAD_ACTUALIZAR);

        jMenu13.add(jMenu35);

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_motiveNoLavado.png"))); // NOI18N
        jMenu7.setText("Motivos_NoLavadoVehículo");
        jMenu7.setPreferredSize(new java.awt.Dimension(150, 30));

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        jMenuItem4.setText("Registrar");
        jMenuItem4.setPreferredSize(new java.awt.Dimension(240, 30));
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem4);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        jMenuItem5.setText("Consultar");
        jMenuItem5.setPreferredSize(new java.awt.Dimension(150, 30));
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem5);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        jMenuItem6.setText("Actualizar");
        jMenuItem6.setPreferredSize(new java.awt.Dimension(150, 30));
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem6);

        jMenu13.add(jMenu7);

        jMenu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_zonaTrabajo2.png"))); // NOI18N
        jMenu9.setText("Zona_Trabajo");
        jMenu9.setPreferredSize(new java.awt.Dimension(150, 30));

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_registrar.png"))); // NOI18N
        jMenuItem8.setText("Registrar");
        jMenuItem8.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem8);

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_consultar.png"))); // NOI18N
        jMenuItem9.setText("Consultar");
        jMenuItem9.setPreferredSize(new java.awt.Dimension(150, 30));
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem9);

        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_actualizar.png"))); // NOI18N
        jMenuItem11.setText("Actualizar");
        jMenuItem11.setPreferredSize(new java.awt.Dimension(150, 30));
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem11);

        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Submenu.png"))); // NOI18N
        jMenuItem12.setText("Añadir_Sitio a ZonaTrabajo");
        jMenuItem12.setPreferredSize(new java.awt.Dimension(150, 30));
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem12);

        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Submenu.png"))); // NOI18N
        jMenuItem13.setText("Actualizar_Sitio");
        jMenuItem13.setPreferredSize(new java.awt.Dimension(150, 30));
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem13);

        jMenu13.add(jMenu9);

        jMenuBar1.add(jMenu13);

        jMenu10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_rendimiento3.png"))); // NOI18N
        jMenu10.setText("REPORTES");

        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_panelControl.png"))); // NOI18N
        jMenuItem14.setText("GRAFICAS");
        jMenuItem14.setPreferredSize(new java.awt.Dimension(250, 30));
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem14);

        jMenuBar1.add(jMenu10);

        jMenu21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_sistema.png"))); // NOI18N
        jMenu21.setText("SISTEMA");
        jMenu21.setPreferredSize(new java.awt.Dimension(120, 40));

        jMenu17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_zoom_f.png"))); // NOI18N
        jMenu17.setText("Zoom");
        jMenu17.setPreferredSize(new java.awt.Dimension(150, 30));

        Zoom50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Submenu.png"))); // NOI18N
        Zoom50.setText("50%");
        Zoom50.setPreferredSize(new java.awt.Dimension(150, 30));
        Zoom50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Zoom50ActionPerformed(evt);
            }
        });
        jMenu17.add(Zoom50);

        Zoom70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Submenu.png"))); // NOI18N
        Zoom70.setText("70%");
        Zoom70.setPreferredSize(new java.awt.Dimension(150, 30));
        Zoom70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Zoom70ActionPerformed(evt);
            }
        });
        jMenu17.add(Zoom70);

        Zoom100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Menu_Submenu.png"))); // NOI18N
        Zoom100.setText("100%");
        Zoom100.setPreferredSize(new java.awt.Dimension(150, 30));
        Zoom100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Zoom100ActionPerformed(evt);
            }
        });
        jMenu17.add(Zoom100);

        jMenu21.add(jMenu17);

        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_cambiarContrasena.png"))); // NOI18N
        jMenuItem10.setText("Cambiar Contraseña");
        jMenuItem10.setPreferredSize(new java.awt.Dimension(230, 30));
        jMenuItem10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jMenuItem10ItemStateChanged(evt);
            }
        });
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu21.add(jMenuItem10);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/salirGUI.png"))); // NOI18N
        jMenuItem1.setText("Cerrar Sesión");
        jMenuItem1.setPreferredSize(new java.awt.Dimension(230, 30));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu21.add(jMenuItem1);

        jMenuBar1.add(jMenu21);

        jMenu29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menuPrincipal/menu_exit.png"))); // NOI18N
        jMenu29.setText("SALIR");
        jMenu29.setPreferredSize(new java.awt.Dimension(91, 32));
        jMenu29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu29MouseClicked(evt);
            }
        });
        jMenu29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu29ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu29);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Zoom50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Zoom50ActionPerformed
        super.setSize(((dim.width /100)*50),((dim.height /100)*50));
        panel.setSize(((dim.width /100)*50),((dim.height /100)*50));
        
    }//GEN-LAST:event_Zoom50ActionPerformed

    private void Zoom70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Zoom70ActionPerformed
        super.setSize(((dim.width /100)*70),((dim.height /100)*70)); 
        panel.setSize(((dim.width /100)*70),((dim.height /100)*70)); 
    }//GEN-LAST:event_Zoom70ActionPerformed

    private void Zoom100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Zoom100ActionPerformed
        super.setSize(dim.width,dim.height); 
        panel.setSize(dim.width,dim.height);   
    }//GEN-LAST:event_Zoom100ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        GUI_Iniciar gui_Iniciar = new GUI_Iniciar();
        gui_Iniciar.setVisible(true);
        this.removeAll();
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void CLIENTE_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLIENTE_REGISTRARActionPerformed
        panel.setViewportView(new Cliente_Registrar(user,tipoConexion));
    }//GEN-LAST:event_CLIENTE_REGISTRARActionPerformed

    private void CLIENTE_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLIENTE_CONSULTARActionPerformed
        panel.setViewportView(new Cliente_Consultar(user,tipoConexion));
    }//GEN-LAST:event_CLIENTE_CONSULTARActionPerformed

    private void SUBCENTROOPERACION_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SUBCENTROOPERACION_REGISTRARActionPerformed
       panel.setViewportView(new CentroCostoSubCentro_Registrar(user,tipoConexion));
    }//GEN-LAST:event_SUBCENTROOPERACION_REGISTRARActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void CENTROCOSTOAUXILIAR_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CENTROCOSTOAUXILIAR_REGISTRARActionPerformed
         panel.setViewportView(new CentroCostoAuxiliar_Registrar(user,tipoConexion));
    }//GEN-LAST:event_CENTROCOSTOAUXILIAR_REGISTRARActionPerformed

    private void PERFIL_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PERFIL_REGISTRARActionPerformed
        panel.setViewportView(new Perfil_Registrar(user,tipoConexion));
    }//GEN-LAST:event_PERFIL_REGISTRARActionPerformed

    private void PERFIL_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PERFIL_CONSULTARActionPerformed
        panel.setViewportView(new Perfil_Consultar(tipoConexion));
    }//GEN-LAST:event_PERFIL_CONSULTARActionPerformed

    private void PERFIL_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PERFIL_ACTUALIZARActionPerformed
        panel.setViewportView(new Perfil_Actualizar(user,tipoConexion));
    }//GEN-LAST:event_PERFIL_ACTUALIZARActionPerformed

    private void USUARIO_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_USUARIO_REGISTRARActionPerformed
        panel.setViewportView(new Usuario_Registrar(tipoConexion));
    }//GEN-LAST:event_USUARIO_REGISTRARActionPerformed

    private void PERFIL_PERMISOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PERFIL_PERMISOSActionPerformed
        try {
            panel.setViewportView(new Permiso_Usuario(user,tipoConexion));
        } catch (SQLException ex) {
            Logger.getLogger(GUI_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_PERFIL_PERMISOSActionPerformed

    private void CENTROCOSTOAUXILIAR_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CENTROCOSTOAUXILIAR_ACTUALIZARActionPerformed
        panel.setViewportView(new CentroCostoAuxiliar_Actualizar(user,tipoConexion));
    }//GEN-LAST:event_CENTROCOSTOAUXILIAR_ACTUALIZARActionPerformed

    private void CENTROCOSTOAUXILIAR_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CENTROCOSTOAUXILIAR_CONSULTARActionPerformed
        panel.setViewportView(new CentroCostoAuxiliar_Consultar(tipoConexion)); 
    }//GEN-LAST:event_CENTROCOSTOAUXILIAR_CONSULTARActionPerformed

    private void SUBCENTROOPERACION_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SUBCENTROOPERACION_CONSULTARActionPerformed
        panel.setViewportView(new CentroCostoSubcentro_Consultar(tipoConexion));
    }//GEN-LAST:event_SUBCENTROOPERACION_CONSULTARActionPerformed

    private void SUBCENTROOPERACION_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SUBCENTROOPERACION_ACTUALIZARActionPerformed
        panel.setViewportView(new CentroCostoSubcentro_Actualizar(user,tipoConexion));
    }//GEN-LAST:event_SUBCENTROOPERACION_ACTUALIZARActionPerformed

    private void CENTROOPERACION_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CENTROOPERACION_REGISTRARActionPerformed
        panel.setViewportView(new CentroOperacion_Registrar(user,tipoConexion));
    }//GEN-LAST:event_CENTROOPERACION_REGISTRARActionPerformed

    private void CENTROOPERACION_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CENTROOPERACION_CONSULTARActionPerformed
        panel.setViewportView(new CentroOperacion_Consultar(tipoConexion));
    }//GEN-LAST:event_CENTROOPERACION_CONSULTARActionPerformed

    private void CENTROOPERACION_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CENTROOPERACION_ACTUALIZARActionPerformed
        panel.setViewportView(new CentroOperacion_Actualizar(user,tipoConexion));
    }//GEN-LAST:event_CENTROOPERACION_ACTUALIZARActionPerformed

    private void EQUIPO_PERTENENCIA_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EQUIPO_PERTENENCIA_REGISTRARActionPerformed
        panel.setViewportView(new ActividadOperacion_Registrar(user,tipoConexion));
    }//GEN-LAST:event_EQUIPO_PERTENENCIA_REGISTRARActionPerformed

    private void EQUIPO_PERTENENCIA_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EQUIPO_PERTENENCIA_CONSULTARActionPerformed
        panel.setViewportView(new PertenenciaEquipo_Consultar(tipoConexion));
    }//GEN-LAST:event_EQUIPO_PERTENENCIA_CONSULTARActionPerformed

    private void EQUIPO_PERTENENCIA_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EQUIPO_PERTENENCIA_ACTUALIZARActionPerformed
        panel.setViewportView(new PertenenciaEquipo_Actualizar(user,tipoConexion));
    }//GEN-LAST:event_EQUIPO_PERTENENCIA_ACTUALIZARActionPerformed

    private void EQUIPO_PROVEEDOR_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EQUIPO_PROVEEDOR_REGISTRARActionPerformed
        panel.setViewportView(new ProveedorEquipo_Registrar(user,tipoConexion)); 
    }//GEN-LAST:event_EQUIPO_PROVEEDOR_REGISTRARActionPerformed

    private void EQUIPO_PROVEEDOR_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EQUIPO_PROVEEDOR_CONSULTARActionPerformed
        panel.setViewportView(new ProveedorEquipo_Consultar(tipoConexion));
    }//GEN-LAST:event_EQUIPO_PROVEEDOR_CONSULTARActionPerformed

    private void EQUIPO_PROVEEDOR_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EQUIPO_PROVEEDOR_ACTUALIZARActionPerformed
        panel.setViewportView(new ProveedorEquipo_Actualizar(user,tipoConexion));
    }//GEN-LAST:event_EQUIPO_PROVEEDOR_ACTUALIZARActionPerformed

    private void EQUIPO_TIPO_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EQUIPO_TIPO_REGISTRARActionPerformed
        panel.setViewportView(new TipoEquipo_Registrar(user,tipoConexion));
    }//GEN-LAST:event_EQUIPO_TIPO_REGISTRARActionPerformed

    private void EQUIPO_TIPO_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EQUIPO_TIPO_CONSULTARActionPerformed
        panel.setViewportView(new TipoEquipo_Consultar(tipoConexion));
    }//GEN-LAST:event_EQUIPO_TIPO_CONSULTARActionPerformed
 
    private void EQUIPO_TIPO_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EQUIPO_TIPO_ACTUALIZARActionPerformed
        panel.setViewportView(new TipoEquipo_Actualizar(user,tipoConexion));
    }//GEN-LAST:event_EQUIPO_TIPO_ACTUALIZARActionPerformed

    private void EQUIPO_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EQUIPO_REGISTRARActionPerformed
        try {
            panel.setViewportView(new Equipo_Registrar(user,tipoConexion));
        } catch (SQLException ex) {
            Logger.getLogger(GUI_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_EQUIPO_REGISTRARActionPerformed

    private void EQUIPO_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EQUIPO_CONSULTARActionPerformed
        panel.setViewportView(new Equipo_Consultar(tipoConexion));
    }//GEN-LAST:event_EQUIPO_CONSULTARActionPerformed

    private void EQUIPO_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EQUIPO_ACTUALIZARActionPerformed
        try {
            panel.setViewportView(new Equipo_Actualizar(user,tipoConexion));
        } catch (SQLException ex) {
            Logger.getLogger(GUI_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_EQUIPO_ACTUALIZARActionPerformed

    private void CLIENTE_REGISTRO_CCARGAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLIENTE_REGISTRO_CCARGAActionPerformed
        panel.setViewportView(new Cliente_RegistroSincronizado(user,tipoConexion));
    }//GEN-LAST:event_CLIENTE_REGISTRO_CCARGAActionPerformed

    private void MOTONAVE_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MOTONAVE_REGISTRARActionPerformed
        panel.setViewportView(new Motonave_Registrar(user,tipoConexion));
    }//GEN-LAST:event_MOTONAVE_REGISTRARActionPerformed

    private void MOTONAVE_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MOTONAVE_CONSULTARActionPerformed
        panel.setViewportView(new Motonave_Consultar(user, tipoConexion));
    }//GEN-LAST:event_MOTONAVE_CONSULTARActionPerformed

    private void MOTONAVE_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MOTONAVE_ACTUALIZARActionPerformed
         panel.setViewportView(new Motonave_Actualizar(user,tipoConexion));
    }//GEN-LAST:event_MOTONAVE_ACTUALIZARActionPerformed

    private void MOTONAVE_REGISTRO_CCARGAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MOTONAVE_REGISTRO_CCARGAActionPerformed
        panel.setViewportView(new Motonave_RegistroSincronizado(user,tipoConexion));
    }//GEN-LAST:event_MOTONAVE_REGISTRO_CCARGAActionPerformed

    private void TRANSPORTADORA_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TRANSPORTADORA_REGISTRARActionPerformed
        panel.setViewportView(new Transportadora_Registrar(user,tipoConexion));
    }//GEN-LAST:event_TRANSPORTADORA_REGISTRARActionPerformed

    private void TRANSPORTADORA_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TRANSPORTADORA_CONSULTARActionPerformed
        panel.setViewportView(new Transportadora_Consultar(user, tipoConexion));
    }//GEN-LAST:event_TRANSPORTADORA_CONSULTARActionPerformed

    private void TRANSPORTADORA_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TRANSPORTADORA_ACTUALIZARActionPerformed
        panel.setViewportView(new Transportadora_Actualizar(user,tipoConexion));
    }//GEN-LAST:event_TRANSPORTADORA_ACTUALIZARActionPerformed

    private void TRANSPORTADORA_REGISTRO_CCARGAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TRANSPORTADORA_REGISTRO_CCARGAActionPerformed
        panel.setViewportView(new Transportadora_RegistroSincronizado(user,tipoConexion));
    }//GEN-LAST:event_TRANSPORTADORA_REGISTRO_CCARGAActionPerformed

    private void ARTICULO_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ARTICULO_REGISTRARActionPerformed
        panel.setViewportView(new Articulo_Registrar(user,tipoConexion));
    }//GEN-LAST:event_ARTICULO_REGISTRARActionPerformed

    private void ARTICULO_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ARTICULO_CONSULTARActionPerformed
        panel.setViewportView(new Articulo_Consultar(user, tipoConexion));
    }//GEN-LAST:event_ARTICULO_CONSULTARActionPerformed

    private void ARTICULO_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ARTICULO_ACTUALIZARActionPerformed
        panel.setViewportView(new Articulo_Actualizar(user,tipoConexion));
    }//GEN-LAST:event_ARTICULO_ACTUALIZARActionPerformed

    private void ARTICULO_REGISTRO_CCARGAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ARTICULO_REGISTRO_CCARGAActionPerformed
        panel.setViewportView(new Articulo_RegistroSincronizado(user,tipoConexion));
    }//GEN-LAST:event_ARTICULO_REGISTRO_CCARGAActionPerformed

    private void MODULO_CARBON_PROCESAR_REGISTROSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_CARBON_PROCESAR_REGISTROSActionPerformed
        panel.setViewportView(new MvtoCarbon_Procesar(user,tipoConexion));
    }//GEN-LAST:event_MODULO_CARBON_PROCESAR_REGISTROSActionPerformed

    private void USUARIO_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_USUARIO_ACTUALIZARActionPerformed
        panel.setViewportView(new Usuario_Actualizar(user,tipoConexion));
    }//GEN-LAST:event_USUARIO_ACTUALIZARActionPerformed

    private void USUARIO_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_USUARIO_CONSULTARActionPerformed
        panel.setViewportView(new Usuario_Consultar(tipoConexion));
    }//GEN-LAST:event_USUARIO_CONSULTARActionPerformed

    private void jMenu29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu29ActionPerformed
        
    }//GEN-LAST:event_jMenu29ActionPerformed

    private void jMenu29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu29MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jMenu29MouseClicked

    private void MODULO_CARBON_INACTIVAR_REGISTROActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_CARBON_INACTIVAR_REGISTROActionPerformed
        panel.setViewportView(new MvtoCarbon_Inactivar_Final(user,tipoConexion));
    }//GEN-LAST:event_MODULO_CARBON_INACTIVAR_REGISTROActionPerformed

    private void MODULO_CARBON_ACTIVAR_REGISTROActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_CARBON_ACTIVAR_REGISTROActionPerformed
        panel.setViewportView(new MvtoCarbon_Activar_Final(user,tipoConexion));
    }//GEN-LAST:event_MODULO_CARBON_ACTIVAR_REGISTROActionPerformed

    private void EQUIPO_ERPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EQUIPO_ERPActionPerformed
        panel.setViewportView(new Equipo_RegistroSincronizado(user,tipoConexion));
    }//GEN-LAST:event_EQUIPO_ERPActionPerformed

    private void AUDITORIA_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AUDITORIA_CONSULTARActionPerformed
        panel.setViewportView(new Auditoria_Consultar(user,tipoConexion));
    }//GEN-LAST:event_AUDITORIA_CONSULTARActionPerformed

    private void CUADRILLA_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CUADRILLA_REGISTRARActionPerformed
        try {
            panel.setViewportView(new Cuadrilla_Registrar(user,tipoConexion));
        } catch (SQLException ex) {
            Logger.getLogger(GUI_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CUADRILLA_REGISTRARActionPerformed

    private void jMenu32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu32ActionPerformed

    private void CUADRILLA_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CUADRILLA_CONSULTARActionPerformed
        panel.setViewportView(new Cuadrilla_Consultar(tipoConexion));
    }//GEN-LAST:event_CUADRILLA_CONSULTARActionPerformed

    private void COMPANIA_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_COMPANIA_REGISTRARActionPerformed
        panel.setViewportView(new Compañia_Registrar(user,tipoConexion));
    }//GEN-LAST:event_COMPANIA_REGISTRARActionPerformed

    private void COMPANIA_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_COMPANIA_CONSULTARActionPerformed
        panel.setViewportView(new Compañias_Consultar(tipoConexion));
    }//GEN-LAST:event_COMPANIA_CONSULTARActionPerformed

    private void COMPANIA_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_COMPANIA_ACTUALIZARActionPerformed
        panel.setViewportView(new Compañias_Actualizar(user,tipoConexion));
    }//GEN-LAST:event_COMPANIA_ACTUALIZARActionPerformed

    private void SOLICITUD_EQUIPOS_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SOLICITUD_EQUIPOS_REGISTRARActionPerformed
        panel.setViewportView(new Solicitud_Equipos_Registrar(user,tipoConexion));
    }//GEN-LAST:event_SOLICITUD_EQUIPOS_REGISTRARActionPerformed

    private void ASIGNACION_EQUIPOS_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ASIGNACION_EQUIPOS_REGISTRARActionPerformed
        try {
            panel.setViewportView(new AsignacionEquipo_Registrar(user,tipoConexion));
        } catch (SQLException ex) {
            Logger.getLogger(GUI_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ASIGNACION_EQUIPOS_REGISTRARActionPerformed

    private void ASIGNACION_EQUIPOS_CONFIRMACIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ASIGNACION_EQUIPOS_CONFIRMACIONActionPerformed
       try {
            panel.setViewportView(new Solicitud_Equipos_Confirmacion(user,tipoConexion));
        } catch (SQLException ex) {
            Logger.getLogger(GUI_Principal.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_ASIGNACION_EQUIPOS_CONFIRMACIONActionPerformed

    private void MODULO_CARBON_GENERAR_MATRIZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_CARBON_GENERAR_MATRIZActionPerformed
        panel.setViewportView(new MvtoCarbon_GenerarMatriz(user,tipoConexion));
        //panel.setViewportView(new MvtoCarbon_InformeGeneral(user,tipoConexion));
    }//GEN-LAST:event_MODULO_CARBON_GENERAR_MATRIZActionPerformed

    private void PROGRAMACION_EQUIPOS_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PROGRAMACION_EQUIPOS_CONSULTARActionPerformed
        panel.setViewportView(new AsignacionCronograma(user,tipoConexion));
    }//GEN-LAST:event_PROGRAMACION_EQUIPOS_CONSULTARActionPerformed

    private void LABOR_REALIZADA_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LABOR_REALIZADA_REGISTRARActionPerformed
        panel.setViewportView(new LaborRealizada_Registrar(user,tipoConexion));
    }//GEN-LAST:event_LABOR_REALIZADA_REGISTRARActionPerformed

    private void LABOR_REALIZADA_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LABOR_REALIZADA_CONSULTARActionPerformed
        panel.setViewportView(new LaborRealizada_Consultar(tipoConexion));
    }//GEN-LAST:event_LABOR_REALIZADA_CONSULTARActionPerformed

    private void LABOR_REALIZADA_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LABOR_REALIZADA_ACTUALIZARActionPerformed
        panel.setViewportView(new LaborRealizada_Actualizar(user,tipoConexion));
    }//GEN-LAST:event_LABOR_REALIZADA_ACTUALIZARActionPerformed

    private void MOTIVO_PARADA_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MOTIVO_PARADA_REGISTRARActionPerformed
        panel.setViewportView(new MotivoParada_Registrar(user,tipoConexion));
    }//GEN-LAST:event_MOTIVO_PARADA_REGISTRARActionPerformed

    private void MOTIVO_PARADA_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MOTIVO_PARADA_CONSULTARActionPerformed
        panel.setViewportView(new MotivoParada_Consultar(tipoConexion));
    }//GEN-LAST:event_MOTIVO_PARADA_CONSULTARActionPerformed

    private void MOTIVO_PARADA_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MOTIVO_PARADA_ACTUALIZARActionPerformed
        panel.setViewportView(new MotivoParada_Actualizar(user,tipoConexion));
    }//GEN-LAST:event_MOTIVO_PARADA_ACTUALIZARActionPerformed

    private void EQUIPO_TARIFAS_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EQUIPO_TARIFAS_REGISTRARActionPerformed
        panel.setViewportView(new Equipo_Tarifa(user,tipoConexion));
    }//GEN-LAST:event_EQUIPO_TARIFAS_REGISTRARActionPerformed

    private void MODULO_EQUIPO_PROCESAR_REGISTROSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_EQUIPO_PROCESAR_REGISTROSActionPerformed
        panel.setViewportView(new MvtoEquipo_Procesar(user,tipoConexion));
    }//GEN-LAST:event_MODULO_EQUIPO_PROCESAR_REGISTROSActionPerformed

    private void MODULO_EQUIPO_AUTORIZAR_RECOBROActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_EQUIPO_AUTORIZAR_RECOBROActionPerformed
        panel.setViewportView(new MvtoEquipo_AutorizarRecobro(user,tipoConexion));
    }//GEN-LAST:event_MODULO_EQUIPO_AUTORIZAR_RECOBROActionPerformed

    private void MODULO_EQUIPO_GENERAR_MATRIZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_EQUIPO_GENERAR_MATRIZActionPerformed
        panel.setViewportView(new MvtoEquipo_InformeMatriz(user,tipoConexion));
    }//GEN-LAST:event_MODULO_EQUIPO_GENERAR_MATRIZActionPerformed

    private void PROGRAMACION_EQUIPOS_INACTIVARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PROGRAMACION_EQUIPOS_INACTIVARActionPerformed
        panel.setViewportView(new AsignacionEquipo_Inactivar(user,tipoConexion));
    }//GEN-LAST:event_PROGRAMACION_EQUIPOS_INACTIVARActionPerformed

    private void CENTROCOSTO_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CENTROCOSTO_REGISTRARActionPerformed
        panel.setViewportView(new CentroCosto_Registrar(user,tipoConexion));
    }//GEN-LAST:event_CENTROCOSTO_REGISTRARActionPerformed

    private void CENTROCOSTO_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CENTROCOSTO_CONSULTARActionPerformed
        panel.setViewportView(new CentroCosto_Consultar(user, tipoConexion));
    }//GEN-LAST:event_CENTROCOSTO_CONSULTARActionPerformed

    private void CENTROCOSTO_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CENTROCOSTO_ACTUALIZARActionPerformed
        panel.setViewportView(new CentroCosto_Actualizar(user,tipoConexion));
    }//GEN-LAST:event_CENTROCOSTO_ACTUALIZARActionPerformed

    private void MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPOActionPerformed
        panel.setViewportView(new RendimientoEquipo(tipoConexion));
    }//GEN-LAST:event_MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPOActionPerformed

    private void MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPOActionPerformed
        panel.setViewportView(new Rendimiento_InformeEquipo(tipoConexion));
    }//GEN-LAST:event_MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPOActionPerformed

    private void CAUSA_INACTIVIDAD_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CAUSA_INACTIVIDAD_REGISTRARActionPerformed
        panel.setViewportView(new CausaInactividad_Registrar(user,tipoConexion));
    }//GEN-LAST:event_CAUSA_INACTIVIDAD_REGISTRARActionPerformed

    private void CAUSA_INACTIVIDAD_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CAUSA_INACTIVIDAD_CONSULTARActionPerformed
        panel.setViewportView(new CausaInactividad_Consultar(tipoConexion));
    }//GEN-LAST:event_CAUSA_INACTIVIDAD_CONSULTARActionPerformed

    private void CAUSA_INACTIVIDAD_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CAUSA_INACTIVIDAD_ACTUALIZARActionPerformed
        panel.setViewportView(new CausaInactividad_Actualizar(user,tipoConexion));
    }//GEN-LAST:event_CAUSA_INACTIVIDAD_ACTUALIZARActionPerformed

    private void MODULO_EQUIPO_INACTIVAR_REGISTROSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_EQUIPO_INACTIVAR_REGISTROSActionPerformed
        panel.setViewportView(new MvtoEquipo_InactivarEquipo(user,tipoConexion));
    }//GEN-LAST:event_MODULO_EQUIPO_INACTIVAR_REGISTROSActionPerformed

    private void MODULO_EQUIPO_ACTIVAR_REGISTROSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_EQUIPO_ACTIVAR_REGISTROSActionPerformed
        panel.setViewportView(new MvtoEquipo_ActivarEquipo(user,tipoConexion));
    }//GEN-LAST:event_MODULO_EQUIPO_ACTIVAR_REGISTROSActionPerformed

    private void PROGRAMACION_EQUIPOS_ACTIVARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PROGRAMACION_EQUIPOS_ACTIVARActionPerformed
        panel.setViewportView(new AsignacionEquipo_Activar(user,tipoConexion));
    }//GEN-LAST:event_PROGRAMACION_EQUIPOS_ACTIVARActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       panel.setViewportView(new MvtoEquipo_ModificarFinal(user,tipoConexion));
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void CENTROCOSTOMAYOR_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CENTROCOSTOMAYOR_REGISTRARActionPerformed
        panel.setViewportView(new CentroCostoMayor_Registrar(user,tipoConexion));
    }//GEN-LAST:event_CENTROCOSTOMAYOR_REGISTRARActionPerformed

    private void CENTROCOSTOMAYOR_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CENTROCOSTOMAYOR_CONSULTARActionPerformed
        panel.setViewportView(new CentroCostoMayor_Consultar(user,tipoConexion));
    }//GEN-LAST:event_CENTROCOSTOMAYOR_CONSULTARActionPerformed

    private void CENTROCOSTOMAYOR_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CENTROCOSTOMAYOR_ACTUALIZARActionPerformed
        panel.setViewportView(new CentroCostoMayor_Actualizar(user,tipoConexion));
    }//GEN-LAST:event_CENTROCOSTOMAYOR_ACTUALIZARActionPerformed

    private void MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROSActionPerformed
        panel.setViewportView(new MvtoCarbon_ProcesarProgramado(user,tipoConexion));
    }//GEN-LAST:event_MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROSActionPerformed

    private void MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROSActionPerformed
        panel.setViewportView(new MvtoEquipo_Procesar_Programado(user,tipoConexion));
    }//GEN-LAST:event_MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROSActionPerformed

    private void jMenuItem10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jMenuItem10ItemStateChanged
        
    }//GEN-LAST:event_jMenuItem10ItemStateChanged

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
       panel.setViewportView(new Usuario_CambiarClave(user,tipoConexion));
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        panel.setViewportView(new MvtoCarbon_ModificarFinal(user,tipoConexion));
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        //panel.setViewportView(new MotivoNoLavado_Registrar(user,tipoConexion));
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        panel.setViewportView(new MotivoNoLavado_Consultar(tipoConexion));
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        panel.setViewportView(new MotivoNoLavado_Actualizar(user,tipoConexion));
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        try {
            panel.setViewportView(new AsignacionEquipo_Consultar(user,tipoConexion));
        } catch (SQLException ex) {
            Logger.getLogger(GUI_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void PROGRAMACION_EQUIPOS_DIRECTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PROGRAMACION_EQUIPOS_DIRECTAActionPerformed
        panel.setViewportView(new Programacion_Directa(user,tipoConexion));
    }//GEN-LAST:event_PROGRAMACION_EQUIPOS_DIRECTAActionPerformed

    private void MODULO_CARBON_AGREGAR_REGISTROActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_CARBON_AGREGAR_REGISTROActionPerformed
        panel.setViewportView(new MvtoCarbon_AgregarRegistro(user,tipoConexion));
    }//GEN-LAST:event_MODULO_CARBON_AGREGAR_REGISTROActionPerformed

    private void MODULO_EQUIPO_AGREGAR_REGISTROActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_EQUIPO_AGREGAR_REGISTROActionPerformed
        panel.setViewportView(new MvtoEquipo_Agregar(user,tipoConexion));
    }//GEN-LAST:event_MODULO_EQUIPO_AGREGAR_REGISTROActionPerformed

    private void MODULO_EQUIPO_GENERAR_DISTRIBUCIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_EQUIPO_GENERAR_DISTRIBUCIONActionPerformed
        panel.setViewportView(new MvtoEquipo_MatrizDistribucion(user, tipoConexion));
    }//GEN-LAST:event_MODULO_EQUIPO_GENERAR_DISTRIBUCIONActionPerformed

    private void MODULO_CARBON_GENERAR_DISTRIBUCIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_CARBON_GENERAR_DISTRIBUCIONActionPerformed
        panel.setViewportView(new MvtoCarbon_MatrizDistribucion(user, tipoConexion));
    }//GEN-LAST:event_MODULO_CARBON_GENERAR_DISTRIBUCIONActionPerformed

    private void MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJOActionPerformed
        panel.setViewportView(new MvtoCarbon_DebitoZonaTrabajo(user, tipoConexion));
    }//GEN-LAST:event_MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJOActionPerformed

    private void MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULOActionPerformed
        panel.setViewportView(new MvtoCarbon_InformeRecaudoLavadoVehiculo(user, tipoConexion));
    }//GEN-LAST:event_MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULOActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        panel.setViewportView(new ZonaTrabajo_Registrar(user, tipoConexion));
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        panel.setViewportView(new ZonaTrabajo_Consultar(user, tipoConexion));
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        panel.setViewportView(new ZonaTrabajo_Actualizar(user, tipoConexion));
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        panel.setViewportView(new ZonaTrabajoPorCentroCostoAuxiliar_Registrar(user, tipoConexion));
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        panel.setViewportView(new ZonaTrabajoActualizarSitio(user, tipoConexion));
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        panel.setViewportView(new PanelControl_Menu(cadena,tipoConexion));
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
         panel.setViewportView(new TipoContrato_Actualizar(user, tipoConexion));
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        panel.setViewportView(new Jornada_Actualizar(user, tipoConexion));
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jMenuItem34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem34ActionPerformed
        panel.setViewportView(new SituacionMedica_Actualizar(user, tipoConexion));
    }//GEN-LAST:event_jMenuItem34ActionPerformed

    private void jMenuItem37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem37ActionPerformed
        panel.setViewportView(new CargoNomina_Actualizar(user, tipoConexion));
    }//GEN-LAST:event_jMenuItem37ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        panel.setViewportView(new TipoContrato_Registrar(user, tipoConexion));
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
         panel.setViewportView(new TipoContrato_Consultar(tipoConexion));
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
         panel.setViewportView(new Jornada_Registrar(user, tipoConexion));
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        panel.setViewportView(new Jornada_Consultar(tipoConexion));
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed
        panel.setViewportView(new SituacionMedica_Registrar(user, tipoConexion));
    }//GEN-LAST:event_jMenuItem32ActionPerformed

    private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem33ActionPerformed
        panel.setViewportView(new SituacionMedica_Consultar(tipoConexion));
    }//GEN-LAST:event_jMenuItem33ActionPerformed

    private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed
        panel.setViewportView(new CargoNomina_Registrar(user, tipoConexion));
    }//GEN-LAST:event_jMenuItem35ActionPerformed

    private void jMenuItem36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem36ActionPerformed
        panel.setViewportView(new CargoNomina_Consultar(tipoConexion));
    }//GEN-LAST:event_jMenuItem36ActionPerformed

    private void jMenuItem38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem38ActionPerformed
        panel.setViewportView(new Persona_Registrar(user, tipoConexion));
    }//GEN-LAST:event_jMenuItem38ActionPerformed

    private void jMenuItem41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem41ActionPerformed
        panel.setViewportView(new TipoDocumento_Registrar(user, tipoConexion));
    }//GEN-LAST:event_jMenuItem41ActionPerformed

    private void jMenuItem42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem42ActionPerformed
        panel.setViewportView(new TipoDocumento_Consultar(tipoConexion));
    }//GEN-LAST:event_jMenuItem42ActionPerformed

    private void jMenuItem43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem43ActionPerformed
        //panel.setViewportView(new TipoDocumento(tipoConexion));
    }//GEN-LAST:event_jMenuItem43ActionPerformed

    private void jMenuItem39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem39ActionPerformed
        panel.setViewportView(new Persona_Consultar(tipoConexion, user));
    }//GEN-LAST:event_jMenuItem39ActionPerformed

    private void jMenuItem40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem40ActionPerformed
        panel.setViewportView(new Persona_Actualizar(user, tipoConexion));
    }//GEN-LAST:event_jMenuItem40ActionPerformed

    private void MODULO_LIQUIDACION_PALERO_MARCACION_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_LIQUIDACION_PALERO_MARCACION_REGISTRARActionPerformed
        panel.setViewportView(new Marcacion_Registrar(user, tipoConexion));
    }//GEN-LAST:event_MODULO_LIQUIDACION_PALERO_MARCACION_REGISTRARActionPerformed

    private void MODULO_LIQUIDACION_PALERO_MARCACION_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_LIQUIDACION_PALERO_MARCACION_CONSULTARActionPerformed
        panel.setViewportView(new Marcacion_Consultar(user, tipoConexion));
    }//GEN-LAST:event_MODULO_LIQUIDACION_PALERO_MARCACION_CONSULTARActionPerformed

    private void MODULO_LIQUIDACION_PALERO_MARCACION_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_LIQUIDACION_PALERO_MARCACION_ACTUALIZARActionPerformed
         panel.setViewportView(new Marcacion_Actualizar(user, tipoConexion));
    }//GEN-LAST:event_MODULO_LIQUIDACION_PALERO_MARCACION_ACTUALIZARActionPerformed

    private void jMenu54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu54ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu54ActionPerformed

    private void MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_REGISTRARActionPerformed
        try {
            panel.setViewportView(new EquipoLiquidacion_Registrar(user, tipoConexion));
        } catch (SQLException ex) {
            Logger.getLogger(GUI_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_REGISTRARActionPerformed

    private void MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_CONSULTARAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_CONSULTARAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_CONSULTARAncestorAdded

    private void MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_CONSULTARActionPerformed
        try {
            panel.setViewportView(new EquipoLiquidacion_Consultar(user, tipoConexion));
        } catch (SQLException ex) {
            Logger.getLogger(GUI_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_CONSULTARActionPerformed

    private void MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_ACTUALIZARActionPerformed
        try {
            panel.setViewportView(new EquipoLiquidacion_Actualizar(user, tipoConexion));
        } catch (SQLException ex) {
            Logger.getLogger(GUI_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_ACTUALIZARActionPerformed

    private void MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_REGISTRARActionPerformed
        panel.setViewportView(new ConfiguracionLiquidacion_Registrar(user, tipoConexion));
    }//GEN-LAST:event_MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_REGISTRARActionPerformed

    private void MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_CONSULTARActionPerformed
        panel.setViewportView(new ConfiguracionLiquidacion_Consultar(user, tipoConexion));
    }//GEN-LAST:event_MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_CONSULTARActionPerformed

    private void MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_ACTUALIZARActionPerformed
        panel.setViewportView(new ConfiguracionLiquidacion_Actualizar(user, tipoConexion));
    }//GEN-LAST:event_MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_ACTUALIZARActionPerformed

    private void MODULO_LIQUIDACION_PALERO_LIQUIDACION_GENERARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_LIQUIDACION_PALERO_LIQUIDACION_GENERARActionPerformed
        panel.setViewportView(new PreLiquidacionPalero_Registrar(user, tipoConexion));
    }//GEN-LAST:event_MODULO_LIQUIDACION_PALERO_LIQUIDACION_GENERARActionPerformed

    private void MODULO_LIQUIDACION_PALERO_LIQUIDACION_CONSULTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MODULO_LIQUIDACION_PALERO_LIQUIDACION_CONSULTARActionPerformed
        panel.setViewportView(new PreLiquidacionPalero_Consultar(user, tipoConexion));
    }//GEN-LAST:event_MODULO_LIQUIDACION_PALERO_LIQUIDACION_CONSULTARActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        panel.setViewportView(new LiquidacionPalero_Registrar(user, tipoConexion));
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        panel.setViewportView(new LiquidacionPalero_Consultar(user, tipoConexion));
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        panel.setViewportView(new LiquidacionPalero_ConsultarConsolidadoPorPeriodo(user, tipoConexion));
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ARTICULO_ACTUALIZAR;
    private javax.swing.JMenuItem ARTICULO_CONSULTAR;
    private javax.swing.JMenuItem ARTICULO_REGISTRAR;
    private javax.swing.JMenuItem ARTICULO_REGISTRO_CCARGA;
    private javax.swing.JMenuItem ASIGNACION_EQUIPOS_CONFIRMACION;
    private javax.swing.JMenuItem ASIGNACION_EQUIPOS_EDITAR;
    private javax.swing.JMenuItem ASIGNACION_EQUIPOS_REGISTRAR;
    private javax.swing.JMenuItem AUDITORIA_CONSULTAR;
    private javax.swing.JMenuItem CAUSA_INACTIVIDAD_ACTUALIZAR;
    private javax.swing.JMenuItem CAUSA_INACTIVIDAD_CONSULTAR;
    private javax.swing.JMenuItem CAUSA_INACTIVIDAD_REGISTRAR;
    private javax.swing.JMenuItem CENTROCOSTOAUXILIAR_ACTUALIZAR;
    private javax.swing.JMenuItem CENTROCOSTOAUXILIAR_CONSULTAR;
    private javax.swing.JMenuItem CENTROCOSTOAUXILIAR_REGISTRAR;
    private javax.swing.JMenuItem CENTROCOSTOMAYOR_ACTUALIZAR;
    private javax.swing.JMenuItem CENTROCOSTOMAYOR_CONSULTAR;
    private javax.swing.JMenuItem CENTROCOSTOMAYOR_REGISTRAR;
    private javax.swing.JMenuItem CENTROCOSTO_ACTUALIZAR;
    private javax.swing.JMenuItem CENTROCOSTO_CONSULTAR;
    private javax.swing.JMenuItem CENTROCOSTO_REGISTRAR;
    private javax.swing.JMenuItem CENTROOPERACION_ACTUALIZAR;
    private javax.swing.JMenuItem CENTROOPERACION_CONSULTAR;
    private javax.swing.JMenuItem CENTROOPERACION_REGISTRAR;
    private javax.swing.JMenuItem CLIENTE_ACTUALIZAR;
    private javax.swing.JMenuItem CLIENTE_CONSULTAR;
    private javax.swing.JMenuItem CLIENTE_REGISTRAR;
    private javax.swing.JMenuItem CLIENTE_REGISTRO_CCARGA;
    private javax.swing.JMenuItem COMPANIA_ACTUALIZAR;
    private javax.swing.JMenuItem COMPANIA_CONSULTAR;
    private javax.swing.JMenuItem COMPANIA_REGISTRAR;
    private javax.swing.JMenuItem CUADRILLA_ACTUALIZAR;
    private javax.swing.JMenuItem CUADRILLA_CONSULTAR;
    private javax.swing.JMenuItem CUADRILLA_REGISTRAR;
    private javax.swing.JMenuItem EQUIPO_ACTUALIZAR;
    private javax.swing.JMenuItem EQUIPO_CONSULTAR;
    private javax.swing.JMenuItem EQUIPO_ERP;
    private javax.swing.JMenuItem EQUIPO_PERTENENCIA_ACTUALIZAR;
    private javax.swing.JMenuItem EQUIPO_PERTENENCIA_CONSULTAR;
    private javax.swing.JMenuItem EQUIPO_PERTENENCIA_REGISTRAR;
    private javax.swing.JMenuItem EQUIPO_PROVEEDOR_ACTUALIZAR;
    private javax.swing.JMenuItem EQUIPO_PROVEEDOR_CONSULTAR;
    private javax.swing.JMenuItem EQUIPO_PROVEEDOR_REGISTRAR;
    private javax.swing.JMenuItem EQUIPO_REGISTRAR;
    private javax.swing.JMenuItem EQUIPO_TARIFAS_REGISTRAR;
    private javax.swing.JMenuItem EQUIPO_TIPO_ACTUALIZAR;
    private javax.swing.JMenuItem EQUIPO_TIPO_CONSULTAR;
    private javax.swing.JMenuItem EQUIPO_TIPO_REGISTRAR;
    private javax.swing.JMenuItem LABOR_REALIZADA_ACTUALIZAR;
    private javax.swing.JMenuItem LABOR_REALIZADA_CONSULTAR;
    private javax.swing.JMenuItem LABOR_REALIZADA_REGISTRAR;
    private javax.swing.JMenuItem MODULO_CARBON_ACTIVAR_REGISTRO;
    private javax.swing.JMenuItem MODULO_CARBON_AGREGAR_REGISTRO;
    private javax.swing.JMenuItem MODULO_CARBON_GENERAR_DISTRIBUCION;
    private javax.swing.JMenuItem MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO;
    private javax.swing.JMenuItem MODULO_CARBON_GENERAR_MATRIZ;
    private javax.swing.JMenuItem MODULO_CARBON_INACTIVAR_REGISTRO;
    private javax.swing.JMenuItem MODULO_CARBON_PROCESAR_REGISTROS;
    private javax.swing.JMenuItem MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS;
    private javax.swing.JMenuItem MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO;
    private javax.swing.JMenuItem MODULO_EQUIPO_ACTIVAR_REGISTROS;
    private javax.swing.JMenuItem MODULO_EQUIPO_AGREGAR_REGISTRO;
    private javax.swing.JMenuItem MODULO_EQUIPO_AUTORIZAR_RECOBRO;
    private javax.swing.JMenuItem MODULO_EQUIPO_GENERAR_DISTRIBUCION;
    private javax.swing.JMenuItem MODULO_EQUIPO_GENERAR_MATRIZ;
    private javax.swing.JMenuItem MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO;
    private javax.swing.JMenuItem MODULO_EQUIPO_INACTIVAR_REGISTROS;
    private javax.swing.JMenuItem MODULO_EQUIPO_PROCESAR_REGISTROS;
    private javax.swing.JMenuItem MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS;
    private javax.swing.JMenuItem MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO;
    private javax.swing.JMenuItem MODULO_LIQUIDACION_PALERO_LIQUIDACION_CONSULTAR;
    private javax.swing.JMenuItem MODULO_LIQUIDACION_PALERO_LIQUIDACION_GENERAR;
    private javax.swing.JMenuItem MODULO_LIQUIDACION_PALERO_MARCACION_ACTUALIZAR;
    private javax.swing.JMenuItem MODULO_LIQUIDACION_PALERO_MARCACION_CONSULTAR;
    private javax.swing.JMenuItem MODULO_LIQUIDACION_PALERO_MARCACION_REGISTRAR;
    private javax.swing.JMenuItem MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_ACTUALIZAR;
    private javax.swing.JMenuItem MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_CONSULTAR;
    private javax.swing.JMenuItem MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_REGISTRAR;
    private javax.swing.JMenuItem MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_ACTUALIZAR;
    private javax.swing.JMenuItem MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_CONSULTAR;
    private javax.swing.JMenuItem MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_REGISTRAR;
    private javax.swing.JMenuItem MOTIVO_PARADA_ACTUALIZAR;
    private javax.swing.JMenuItem MOTIVO_PARADA_CONSULTAR;
    private javax.swing.JMenuItem MOTIVO_PARADA_REGISTRAR;
    private javax.swing.JMenuItem MOTONAVE_ACTUALIZAR;
    private javax.swing.JMenuItem MOTONAVE_CONSULTAR;
    private javax.swing.JMenuItem MOTONAVE_REGISTRAR;
    private javax.swing.JMenuItem MOTONAVE_REGISTRO_CCARGA;
    private javax.swing.JMenuItem PERFIL_ACTUALIZAR;
    private javax.swing.JMenuItem PERFIL_CONSULTAR;
    private javax.swing.JMenuItem PERFIL_PERMISOS;
    private javax.swing.JMenuItem PERFIL_REGISTRAR;
    private javax.swing.JMenuItem PROGRAMACION_EQUIPOS_ACTIVAR;
    private javax.swing.JMenuItem PROGRAMACION_EQUIPOS_CONSULTAR;
    private javax.swing.JMenuItem PROGRAMACION_EQUIPOS_DIRECTA;
    private javax.swing.JMenuItem PROGRAMACION_EQUIPOS_INACTIVAR;
    private javax.swing.JMenuItem SOLICITUD_EQUIPOS_EDITAR;
    private javax.swing.JMenuItem SOLICITUD_EQUIPOS_REGISTRAR;
    private javax.swing.JMenuItem SUBCENTROOPERACION_ACTUALIZAR;
    private javax.swing.JMenuItem SUBCENTROOPERACION_CONSULTAR;
    private javax.swing.JMenuItem SUBCENTROOPERACION_REGISTRAR;
    private javax.swing.JMenuItem TRANSPORTADORA_ACTUALIZAR;
    private javax.swing.JMenuItem TRANSPORTADORA_CONSULTAR;
    private javax.swing.JMenuItem TRANSPORTADORA_REGISTRAR;
    private javax.swing.JMenuItem TRANSPORTADORA_REGISTRO_CCARGA;
    private javax.swing.JMenuItem USUARIO_ACTUALIZAR;
    private javax.swing.JMenuItem USUARIO_CONSULTAR;
    private javax.swing.JMenuItem USUARIO_REGISTRAR;
    private javax.swing.JMenuItem Zoom100;
    private javax.swing.JMenuItem Zoom50;
    private javax.swing.JMenuItem Zoom70;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenu jMenu17;
    private javax.swing.JMenu jMenu18;
    private javax.swing.JMenu jMenu19;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu20;
    private javax.swing.JMenu jMenu21;
    private javax.swing.JMenu jMenu22;
    private javax.swing.JMenu jMenu23;
    private javax.swing.JMenu jMenu24;
    private javax.swing.JMenu jMenu25;
    private javax.swing.JMenu jMenu26;
    private javax.swing.JMenu jMenu27;
    private javax.swing.JMenu jMenu28;
    private javax.swing.JMenu jMenu29;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu30;
    private javax.swing.JMenu jMenu31;
    private javax.swing.JMenu jMenu32;
    private javax.swing.JMenu jMenu33;
    private javax.swing.JMenu jMenu34;
    private javax.swing.JMenu jMenu35;
    private javax.swing.JMenu jMenu36;
    private javax.swing.JMenu jMenu37;
    private javax.swing.JMenu jMenu38;
    private javax.swing.JMenu jMenu39;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu40;
    private javax.swing.JMenu jMenu41;
    private javax.swing.JMenu jMenu42;
    private javax.swing.JMenu jMenu43;
    private javax.swing.JMenu jMenu44;
    private javax.swing.JMenu jMenu45;
    private javax.swing.JMenu jMenu46;
    private javax.swing.JMenu jMenu47;
    private javax.swing.JMenu jMenu48;
    private javax.swing.JMenu jMenu49;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu50;
    private javax.swing.JMenu jMenu51;
    private javax.swing.JMenu jMenu52;
    private javax.swing.JMenu jMenu53;
    private javax.swing.JMenu jMenu54;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem37;
    private javax.swing.JMenuItem jMenuItem38;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem40;
    private javax.swing.JMenuItem jMenuItem41;
    private javax.swing.JMenuItem jMenuItem42;
    private javax.swing.JMenuItem jMenuItem43;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane panel;
    // End of variables declaration//GEN-END:variables
    public void validarMenu(Usuario us){        
        for(int i=0; i< us.getPerfilUsuario().getPermisos().size();i++){
            //System.out.println(""+us.getPerfilUsuario().getPermisos().get(i).getDescripcion());
            switch(us.getPerfilUsuario().getPermisos().get(i).getDescripcion()){
                case "SOLICITUD_EQUIPOS_REGISTRAR":{
                    SOLICITUD_EQUIPOS_REGISTRAR.show(true);
                    break;
                }
                case "ASIGNACION_EQUIPOS_REGISTRAR":{
                    ASIGNACION_EQUIPOS_REGISTRAR.show(true);
                    break;
                }
                case "ASIGNACION_EQUIPOS_CONFIRMACION":{
                    ASIGNACION_EQUIPOS_CONFIRMACION.show(true);
                    break;
                }
                case "PROGRAMACION_EQUIPOS_CONSULTAR":{
                    PROGRAMACION_EQUIPOS_CONSULTAR.show(true);
                    break;
                }
                case "PROGRAMACION_EQUIPOS_INACTIVAR":{
                    PROGRAMACION_EQUIPOS_INACTIVAR.show(true);
                    break;
                }
                case "PROGRAMACION_EQUIPOS_ACTIVAR":{
                    PROGRAMACION_EQUIPOS_ACTIVAR.show(true);
                    break;
                }
                
                case "MODULO_CARBON_PROCESAR_REGISTROS":{
                    MODULO_CARBON_PROCESAR_REGISTROS.show(true);
                    break;
                }
                case "MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS":{
                    MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS.show(true);
                    break;
                }
                case "MODULO_CARBON_INACTIVAR_REGISTRO":{
                    MODULO_CARBON_INACTIVAR_REGISTRO.show(true);
                    break;
                }
                case "MODULO_CARBON_ACTIVAR_REGISTRO":{
                    MODULO_CARBON_ACTIVAR_REGISTRO.show(true);
                    break;
                }
                /*case "MODULO_CARBON_LAVADO_VEHICULOS":{
                    MODULO_CARBON_LAVADO_VEHICULOS.show(true);
                    break;
                }*/
                case "MODULO_CARBON_GENERAR_MATRIZ":{
                    MODULO_CARBON_GENERAR_MATRIZ.show(true);
                    break;
                }
                case "MODULO_EQUIPO_PROCESAR_REGISTROS":{
                    MODULO_EQUIPO_PROCESAR_REGISTROS.show(true);
                    break;
                }
                case "MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS":{
                    MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS.show(true);
                    break;
                }
                case "MODULO_EQUIPO_AUTORIZAR_RECOBRO":{
                    MODULO_EQUIPO_AUTORIZAR_RECOBRO.show(true);
                    break;
                }
                case "MODULO_EQUIPO_ACTIVAR_REGISTROS":{
                    MODULO_EQUIPO_ACTIVAR_REGISTROS.show(true);
                    break;
                }
                case "MODULO_EQUIPO_INACTIVAR_REGISTROS":{
                    MODULO_EQUIPO_INACTIVAR_REGISTROS.show(true);
                    break;
                }
                case "MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO":{
                    MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO.show(true);
                    break;
                }
                case "MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO":{
                    MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO.show(true);
                    break;
                }
                case "MODULO_EQUIPO_GENERAR_MATRIZ":{
                    MODULO_EQUIPO_GENERAR_MATRIZ.show(true);
                    break;
                }
                case "AUDITORIA_CONSULTAR":{
                    AUDITORIA_CONSULTAR.show(true);
                    break;
                }
                case "PERFIL_REGISTRAR":{
                    PERFIL_REGISTRAR.show(true);
                    break;
                }
                case "PERFIL_CONSULTAR":{
                    PERFIL_CONSULTAR.show(true);
                    break;
                }
                case "PERFIL_ACTUALIZAR":{
                    PERFIL_ACTUALIZAR.show(true);
                    break;
                }
                case "PERFIL_PERMISOS":{
                    PERFIL_PERMISOS.show(true);
                    break;
                }
                case "USUARIO_REGISTRAR":{
                    USUARIO_REGISTRAR.show(true);
                    break;
                }
                case "USUARIO_CONSULTAR":{
                    USUARIO_CONSULTAR.show(true);
                    break;
                }
                case "USUARIO_ACTUALIZAR":{
                    USUARIO_ACTUALIZAR.show(true);
                    break;
                }
                case "CENTROOPERACION_REGISTRAR":{
                    CENTROOPERACION_REGISTRAR.show(true);
                    break;
                }
                case "CENTROOPERACION_CONSULTAR":{
                    CENTROOPERACION_CONSULTAR.show(true);
                    break;
                }
                case "CENTROOPERACION_ACTUALIZAR":{
                    CENTROOPERACION_ACTUALIZAR.show(true);
                    break;
                }
                case "SUBCENTROOPERACION_REGISTRAR":{
                    SUBCENTROOPERACION_REGISTRAR.show(true);
                    break;
                }
                case "SUBCENTROOPERACION_CONSULTAR":{
                    SUBCENTROOPERACION_CONSULTAR.show(true);
                    break;
                }
                case "SUBCENTROOPERACION_ACTUALIZAR":{
                    SUBCENTROOPERACION_ACTUALIZAR.show(true);
                    break;
                }
                case "CENTROCOSTOAUXILIAR_REGISTRAR":{
                    CENTROCOSTOAUXILIAR_REGISTRAR.show(true);
                    break;
                }
                case "CENTROCOSTOAUXILIAR_CONSULTAR":{
                    CENTROCOSTOAUXILIAR_CONSULTAR.show(true);
                    break;
                }
                case "CENTROCOSTOAUXILIAR_ACTUALIZAR":{
                    CENTROCOSTOAUXILIAR_ACTUALIZAR.show(true);
                    break;
                }
                case "CENTROCOSTO_REGISTRAR":{
                    CENTROCOSTO_REGISTRAR.show(true);
                    break;
                }
                case "CENTROCOSTO_CONSULTAR":{
                    CENTROCOSTO_CONSULTAR.show(true);
                    break;
                }
                case "CENTROCOSTO_ACTUALIZAR":{
                    CENTROCOSTO_ACTUALIZAR.show(true);
                    break;
                }
                case "CENTROCOSTOMAYOR_REGISTRAR":{
                    CENTROCOSTOMAYOR_REGISTRAR.show(true);
                    break;
                }
                case "CENTROCOSTOMAYOR_CONSULTAR":{
                    CENTROCOSTOMAYOR_CONSULTAR.show(true);
                    break;
                }
                case "CENTROCOSTOMAYOR_ACTUALIZAR":{
                    CENTROCOSTOMAYOR_ACTUALIZAR.show(true);
                    break;
                }  
                case "COMPANIA_REGISTRAR":{
                    COMPANIA_REGISTRAR.show(true);
                    break;
                }
                case "COMPANIA_CONSULTAR":{
                    COMPANIA_CONSULTAR.show(true);
                    break;
                }
                case "COMPANIA_ACTUALIZAR":{
                    COMPANIA_ACTUALIZAR.show(true);
                    break;
                }
                case "LABOR_REALIZADA_REGISTRAR":{
                    LABOR_REALIZADA_REGISTRAR.show(true);
                    break;
                }
                case "LABOR_REALIZADA_CONSULTAR":{
                    LABOR_REALIZADA_CONSULTAR.show(true);
                    break;
                }
                case "LABOR_REALIZADA_ACTUALIZAR":{
                    LABOR_REALIZADA_ACTUALIZAR.show(true);
                    break;
                }
                case "MOTIVO_PARADA_REGISTRAR":{
                    MOTIVO_PARADA_REGISTRAR.show(true);
                    break;
                }
                case "MOTIVO_PARADA_CONSULTAR":{
                    MOTIVO_PARADA_CONSULTAR.show(true);
                    break;
                }
                case "MOTIVO_PARADA_ACTUALIZAR":{
                    MOTIVO_PARADA_ACTUALIZAR.show(true);
                    break;
                }
                case "CLIENTE_REGISTRAR":{
                    CLIENTE_REGISTRAR.show(true);
                    break;
                }
                case "CLIENTE_CONSULTAR":{
                    CLIENTE_CONSULTAR.show(true);
                    break;
                }
                case "CLIENTE_ACTUALIZAR":{
                    CLIENTE_ACTUALIZAR.show(true);
                    break;
                }
                case "CLIENTE_REGISTRO_CCARGA":{
                    CLIENTE_REGISTRO_CCARGA.show(true);
                    break;
                }
                case "MOTONAVE_REGISTRAR":{
                    MOTONAVE_REGISTRAR.show(true);
                    break;
                }
                case "MOTONAVE_CONSULTAR":{
                    MOTONAVE_CONSULTAR.show(true);
                    break;
                }
                case "MOTONAVE_ACTUALIZAR":{
                    MOTONAVE_ACTUALIZAR.show(true);
                    break;
                }
                case "MOTONAVE_REGISTRO_CCARGA":{
                    MOTONAVE_REGISTRO_CCARGA.show(true);
                    break;
                }
                case "TRANSPORTADORA_REGISTRAR":{
                    TRANSPORTADORA_REGISTRAR.show(true);
                    break;
                }
                case "TRANSPORTADORA_CONSULTAR":{
                    TRANSPORTADORA_CONSULTAR.show(true);
                    break;
                }
                case "TRANSPORTADORA_ACTUALIZAR":{
                    TRANSPORTADORA_ACTUALIZAR.show(true);
                    break;
                }
                case "TRANSPORTADORA_REGISTRO_CCARGA":{
                    TRANSPORTADORA_REGISTRO_CCARGA.show(true);
                    break;
                }
                case "ARTICULO_REGISTRAR":{
                    ARTICULO_REGISTRAR.show(true);
                    break;
                }
                case "ARTICULO_CONSULTAR":{
                    ARTICULO_CONSULTAR.show(true);
                    break;
                }
                case "ARTICULO_ACTUALIZAR":{
                    ARTICULO_ACTUALIZAR.show(true);
                    break;
                }
                case "ARTICULO_REGISTRO_CCARGA":{
                    ARTICULO_REGISTRO_CCARGA.show(true);
                    break;
                }
                case "CUADRILLA_REGISTRAR":{
                    CUADRILLA_REGISTRAR.show(true);
                    break;
                }
                case "CUADRILLA_CONSULTAR":{
                    CUADRILLA_CONSULTAR.show(true);
                    break;
                }
                case "CUADRILLA_ACTUALIZAR":{
                    CUADRILLA_ACTUALIZAR.show(true);
                    break;
                }
                case "EQUIPO_REGISTRAR":{
                    EQUIPO_REGISTRAR.show(true);
                    break;
                }
                case "EQUIPO_CONSULTAR":{
                    EQUIPO_CONSULTAR.show(true);
                    break;
                }
                case "EQUIPO_ACTUALIZAR":{
                    EQUIPO_ACTUALIZAR.show(true);
                    break;
                }
                case "EQUIPO_PERTENENCIA_REGISTRAR":{
                    EQUIPO_PERTENENCIA_REGISTRAR.show(true);
                    break;
                }
                case "EQUIPO_PERTENENCIA_CONSULTAR":{
                    EQUIPO_PERTENENCIA_CONSULTAR.show(true);
                    break;
                }
                case "EQUIPO_PERTENENCIA_ACTUALIZAR":{
                    EQUIPO_PERTENENCIA_ACTUALIZAR.show(true);
                    break;
                }
                case "EQUIPO_PROVEEDOR_REGISTRAR":{
                    EQUIPO_PROVEEDOR_REGISTRAR.show(true);
                    break;
                }
                case "EQUIPO_PROVEEDOR_CONSULTAR":{
                    EQUIPO_PROVEEDOR_CONSULTAR.show(true);
                    break;
                }
                case "EQUIPO_PROVEEDOR_ACTUALIZAR":{
                    EQUIPO_PROVEEDOR_ACTUALIZAR.show(true);
                    break;
                }
                case "EQUIPO_TIPO_REGISTRAR":{
                    EQUIPO_TIPO_REGISTRAR.show(true);
                    break;
                }
                case "EQUIPO_TIPO_CONSULTAR":{
                    EQUIPO_TIPO_CONSULTAR.show(true);
                    break;
                }
                case "EQUIPO_TIPO_ACTUALIZAR":{
                    EQUIPO_TIPO_ACTUALIZAR.show(true);
                    break;
                }
                case "EQUIPO_ERP":{
                    EQUIPO_ERP.show(true);
                    break;
                }
                case "EQUIPO_TARIFAS_REGISTRAR":{
                    EQUIPO_TARIFAS_REGISTRAR.show(true);
                    break;
                }
                case "CAUSA_INACTIVIDAD_REGISTRAR":{
                    CAUSA_INACTIVIDAD_REGISTRAR.show(true);
                    break;
                }
                case "CAUSA_INACTIVIDAD_CONSULTAR":{
                    CAUSA_INACTIVIDAD_CONSULTAR.show(true);
                    break;
                }
                case "CAUSA_INACTIVIDAD_ACTUALIZAR":{
                    CAUSA_INACTIVIDAD_ACTUALIZAR.show(true);
                    break;
                }
                
                case "ASIGNACION_EQUIPOS_EDITAR":{
                    ASIGNACION_EQUIPOS_EDITAR.show(true);
                    break;
                }
                case "SOLICITUD_EQUIPOS_EDITAR":{
                    SOLICITUD_EQUIPOS_EDITAR.show(true);
                    break;
                }
                case "PROGRAMACION_EQUIPOS_DIRECTA":{
                    PROGRAMACION_EQUIPOS_DIRECTA.show(true);
                    break;
                }
                case "MODULO_CARBON_AGREGAR_REGISTRO":{
                    MODULO_CARBON_AGREGAR_REGISTRO.show(true);
                    break;
                }
                case "MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO":{
                    MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO.show(true);
                    break;
                }
                case "MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO":{
                    MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO.show(true);
                    break;
                }
                case "MODULO_CARBON_GENERAR_DISTRIBUCION":{
                    MODULO_CARBON_GENERAR_DISTRIBUCION.show(true);
                    break;
                }
                case "MODULO_EQUIPO_AGREGAR_REGISTRO":{
                    MODULO_EQUIPO_AGREGAR_REGISTRO.show(true);
                    break;
                }
                case "MODULO_EQUIPO_GENERAR_DISTRIBUCION":{
                    MODULO_EQUIPO_GENERAR_DISTRIBUCION.show(true);
                    break;
                }
                
                case "MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_REGISTRAR":{
                    MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_REGISTRAR.show(true);
                    break;
                }
                case "MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_CONSULTAR":{
                    MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_CONSULTAR.show(true);
                    break;
                }
                case "MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_ACTUALIZAR":{
                    MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_ACTUALIZAR.show(true);
                    break;
                }
                case "MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_REGISTRAR":{
                    MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_REGISTRAR.show(true);
                    break;
                }
                case "MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_CONSULTAR":{
                    MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_CONSULTAR.show(true);
                    break;
                }
                case "MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_ACTUALIZAR":{
                    MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_ACTUALIZAR.show(true);
                    break;
                }
                case "MODULO_LIQUIDACION_PALERO_MARCACION_REGISTRAR":{
                    MODULO_LIQUIDACION_PALERO_MARCACION_REGISTRAR.show(true);
                    break;
                }
                case "MODULO_LIQUIDACION_PALERO_MARCACION_CONSULTAR":{
                    MODULO_LIQUIDACION_PALERO_MARCACION_CONSULTAR.show(true);
                    break;
                }
                case "MODULO_LIQUIDACION_PALERO_MARCACION_ACTUALIZAR":{
                    MODULO_LIQUIDACION_PALERO_MARCACION_ACTUALIZAR.show(true);
                    break;
                }
                case "MODULO_LIQUIDACION_PALERO_LIQUIDACION_GENERAR":{
                    MODULO_LIQUIDACION_PALERO_LIQUIDACION_GENERAR.show(true);
                    break;
                }
                case "MODULO_LIQUIDACION_PALERO_LIQUIDACION_CONSULTAR":{
                    MODULO_LIQUIDACION_PALERO_LIQUIDACION_CONSULTAR.show(true);
                    break;
                }
                default:{
                    break;
                }
            }
        }
    }
    public void ocultarMenu(){
        SOLICITUD_EQUIPOS_REGISTRAR.show(false);
        ASIGNACION_EQUIPOS_REGISTRAR.show(false);
        ASIGNACION_EQUIPOS_CONFIRMACION.show(false);
        PROGRAMACION_EQUIPOS_CONSULTAR.show(false);
        PROGRAMACION_EQUIPOS_INACTIVAR.show(false);
        PROGRAMACION_EQUIPOS_ACTIVAR.show(false);
        PROGRAMACION_EQUIPOS_DIRECTA.show(false);
        MODULO_CARBON_PROCESAR_REGISTROS.show(false);
        MODULO_CARBON_PROGRAMAR_PROCESAMIENTO_REGISTROS.show(false);
        MODULO_CARBON_INACTIVAR_REGISTRO.show(false);
        MODULO_CARBON_ACTIVAR_REGISTRO.show(false);
        //MODULO_CARBON_LAVADO_VEHICULOS.show(false);
        MODULO_CARBON_GENERAR_MATRIZ.show(false);
        MODULO_EQUIPO_PROCESAR_REGISTROS.show(false);
        MODULO_EQUIPO_PROGRAMAR_PROCESAMIENTO_REGISTROS.show(false);
        MODULO_EQUIPO_AUTORIZAR_RECOBRO.show(false);
        MODULO_EQUIPO_ACTIVAR_REGISTROS.show(false);
        MODULO_EQUIPO_INACTIVAR_REGISTROS.show(false);
        MODULO_EQUIPO_GRAFICAR_RENDIMIENTO_EQUIPO.show(false);
        MODULO_EQUIPO_REPORTE_RENDIMIENTO_EQUIPO.show(false);
        MODULO_EQUIPO_GENERAR_MATRIZ.show(false);
        AUDITORIA_CONSULTAR.show(false);
        PERFIL_REGISTRAR.show(false);
        PERFIL_CONSULTAR.show(false);
        PERFIL_ACTUALIZAR.show(false);
        PERFIL_PERMISOS.show(false);
        USUARIO_REGISTRAR.show(false);
        USUARIO_CONSULTAR.show(false);
        USUARIO_ACTUALIZAR.show(false);
        CENTROOPERACION_REGISTRAR.show(false);
        CENTROOPERACION_CONSULTAR.show(false);
        CENTROOPERACION_ACTUALIZAR.show(false);
        SUBCENTROOPERACION_REGISTRAR.show(false);
        SUBCENTROOPERACION_CONSULTAR.show(false);
        SUBCENTROOPERACION_ACTUALIZAR.show(false);
        CENTROCOSTOAUXILIAR_REGISTRAR.show(false);
        CENTROCOSTOAUXILIAR_CONSULTAR.show(false);
        CENTROCOSTOAUXILIAR_ACTUALIZAR.show(false);
        CENTROCOSTO_REGISTRAR.show(false);
        CENTROCOSTO_CONSULTAR.show(false);
        CENTROCOSTO_ACTUALIZAR.show(false);
        CENTROCOSTOMAYOR_REGISTRAR.show(false);
        CENTROCOSTOMAYOR_CONSULTAR.show(false);
        CENTROCOSTOMAYOR_ACTUALIZAR.show(false);
        COMPANIA_REGISTRAR.show(false);
        COMPANIA_CONSULTAR.show(false);
        COMPANIA_ACTUALIZAR.show(false);
        LABOR_REALIZADA_REGISTRAR.show(false);
        LABOR_REALIZADA_CONSULTAR.show(false);
        LABOR_REALIZADA_ACTUALIZAR.show(false);
        MOTIVO_PARADA_REGISTRAR.show(false);
        MOTIVO_PARADA_CONSULTAR.show(false);
        MOTIVO_PARADA_ACTUALIZAR.show(false);
        CLIENTE_REGISTRAR.show(false);
        CLIENTE_CONSULTAR.show(false);
        CLIENTE_ACTUALIZAR.show(false);
        CLIENTE_REGISTRO_CCARGA.show(false);
        MOTONAVE_REGISTRAR.show(false);
        MOTONAVE_CONSULTAR.show(false);
        MOTONAVE_ACTUALIZAR.show(false);
        MOTONAVE_REGISTRO_CCARGA.show(false);
        TRANSPORTADORA_REGISTRAR.show(false);
        TRANSPORTADORA_CONSULTAR.show(false);
        TRANSPORTADORA_ACTUALIZAR.show(false);
        TRANSPORTADORA_REGISTRO_CCARGA.show(false);
        ARTICULO_REGISTRAR.show(false);
        ARTICULO_CONSULTAR.show(false);
        ARTICULO_ACTUALIZAR.show(false);
        ARTICULO_REGISTRO_CCARGA.show(false);
        CUADRILLA_REGISTRAR.show(false);
        CUADRILLA_CONSULTAR.show(false);
        CUADRILLA_ACTUALIZAR.show(false);
        EQUIPO_REGISTRAR.show(false);
        EQUIPO_CONSULTAR.show(false);
        EQUIPO_ACTUALIZAR.show(false);
        EQUIPO_PERTENENCIA_REGISTRAR.show(false);
        EQUIPO_PERTENENCIA_CONSULTAR.show(false);
        EQUIPO_PERTENENCIA_ACTUALIZAR.show(false);
        EQUIPO_PROVEEDOR_REGISTRAR.show(false);
        EQUIPO_PROVEEDOR_CONSULTAR.show(false);
        EQUIPO_PROVEEDOR_ACTUALIZAR.show(false);
        EQUIPO_TIPO_REGISTRAR.show(false);
        EQUIPO_TIPO_CONSULTAR.show(false);
        EQUIPO_TIPO_ACTUALIZAR.show(false);
        EQUIPO_ERP.show(false);
        EQUIPO_TARIFAS_REGISTRAR.show(false);
        CAUSA_INACTIVIDAD_REGISTRAR.show(false);
        CAUSA_INACTIVIDAD_CONSULTAR.show(false);
        CAUSA_INACTIVIDAD_ACTUALIZAR.show(false);
        
        
        
        ASIGNACION_EQUIPOS_EDITAR.show(false);
        SOLICITUD_EQUIPOS_EDITAR.show(false);
        PROGRAMACION_EQUIPOS_DIRECTA.show(false);
        MODULO_CARBON_AGREGAR_REGISTRO.show(false);
        MODULO_CARBON_REGISTRAR_DEBITO_ZONATRABAJO.show(false);
        MODULO_CARBON_GENERAR_INFORME_RECAUDO_LAVADOVEHICULO.show(false);
        MODULO_CARBON_GENERAR_DISTRIBUCION.show(false);
        MODULO_EQUIPO_AGREGAR_REGISTRO.show(false);
        MODULO_EQUIPO_GENERAR_DISTRIBUCION.show(false);
        
        
        MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_REGISTRAR.show(false);
        MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_CONSULTAR.show(false);
        MODULO_LIQUIDACION_PALERO_PARAMETROS_EQUIPOLIQUIDACION_ACTUALIZAR.show(false);
        MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_REGISTRAR.show(false);
        MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_CONSULTAR.show(false);
        MODULO_LIQUIDACION_PALERO_PARAMETROS_CONFIGURACIONLIQUIDACION_ACTUALIZAR.show(false);
        MODULO_LIQUIDACION_PALERO_MARCACION_REGISTRAR.show(false);
        MODULO_LIQUIDACION_PALERO_MARCACION_CONSULTAR.show(false);
        MODULO_LIQUIDACION_PALERO_MARCACION_ACTUALIZAR.show(false);
        MODULO_LIQUIDACION_PALERO_LIQUIDACION_GENERAR.show(false);
        MODULO_LIQUIDACION_PALERO_LIQUIDACION_CONSULTAR.show(false);
    }
}
