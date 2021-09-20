package ModuloEquipo.Controller; 
    import Catalogo.Controller.ControlDB_CentroCosto;
    import Catalogo.Controller.ControlDB_CentroCostoMayor;
    import Catalogo.Controller.ControlDB_Equipo;
    import ConnectionDB.Conexion_DB_ccargaGP;
    import ConnectionDB.Conexion_DB_costos_vg;
    import Catalogo.Model.Cliente;
    import Catalogo.Model.Articulo;
    import Catalogo.Model.BaseDatos;
    import Catalogo.Model.CentroCostoAuxiliar;
    import Catalogo.Model.CentroCosto;
    import Catalogo.Model.CentroCostoEquipo;
    import Catalogo.Model.CentroCostoMayor;
    import Catalogo.Model.CentroCostoSubCentro;
    import Catalogo.Model.CentroOperacion;
    import ModuloCarbon.Model.EstadoMvtoCarbon;
    import Catalogo.Model.Motonave;
    import ModuloCarbon.Model.MvtoCarbon;
    import ModuloCarbon.Model.MvtoCarbon_ListadoEquipos;
    import Catalogo.Model.Transportadora;
    import ModuloEquipo.Model.AsignacionEquipo;
    import ModuloEquipo.Model.AutorizacionRecobro;
    import ModuloEquipo.Model.CausaInactividad;
    import Catalogo.Model.ClasificadorEquipo;
    import Catalogo.Model.ClienteRecobro;
    import Catalogo.Model.Compañia;
    import ModuloEquipo.Model.ConfirmacionSolicitudEquipos;
    import Catalogo.Model.Equipo;
    import ModuloEquipo.Model.EstadoSolicitudEquipos;
    import Catalogo.Model.LaborRealizada;
    import Catalogo.Model.MotivoParada;
    import ModuloEquipo.Model.MvtoEquipo;
    import Catalogo.Model.Pertenencia;
    import Catalogo.Model.ProveedorEquipo;
    import ModuloEquipo.Model.Recobro;
    import ModuloEquipo.Model.SolicitudEquipo;
    import ModuloEquipo.Model.SolicitudListadoEquipo;
    import Catalogo.Model.TarifaEquipo;
    import Catalogo.Model.TipoArticulo;
    import Catalogo.Model.TipoEquipo;
    import ModuloEquipo.Model.PlantillaConectorMvtoEquipo;
    import Sistema.Controller.ControlDB_Config;
    import Sistema.Model.Perfil;
    import Sistema.Model.Usuario;
    import java.io.FileNotFoundException;
    import java.net.SocketException;
    import java.net.UnknownHostException;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    import java.text.DecimalFormat;
    import java.util.ArrayList;
    import java.util.logging.Level;
    import java.util.logging.Logger;
    import javax.swing.JOptionPane;

public class ControlDB_MvtoEquipo {
    private Connection conexion=null;
    private Connection conexion2=null;
    private String tipoConexion;
    
    public ControlDB_MvtoEquipo(String tipoConexion) { 
        this.tipoConexion= tipoConexion;
    }
    //Consultas Optimizadas
    public MvtoEquipo                               buscar_mvtoEquiposParticular(String codigoMvtoEquipo) throws SQLException{
        MvtoEquipo mvtoEquipo = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  [me_cdgo]--1\n" +
                                                                "      ,[me_asignacion_equipo_cdgo]--2\n" +
                                                                "			,[ae_cdgo]--3\n" +
                                                                "			,[ae_equipo_cdgo]--4\n" +
                                                                "				,[eq_cdgo]--5\n" +
                                                                "				,[eq_tipo_equipo_cdgo]--6\n" +
                                                                "				,[eq_marca]--7\n" +
                                                                "				,[eq_modelo]--8\n" +
                                                                "				,[eq_desc]	--9\n" +
                                                                "			,[ae_fecha_registro]--10\n" +
                                                                "			,[ae_fecha_hora_inicio]--11\n" +
                                                                "			,[ae_fecha_hora_fin]--12\n" +
                                                                "			,[ae_cant_minutos]--13\n" +
                                                                "      ,[me_fecha]--14\n" +
                                                                "      ,[me_proveedor_equipo_cdgo]--15\n" +
                                                                "			,[pe_cdgo]--16\n" +
                                                                "			,[pe_nit]--17\n" +
                                                                "			,[pe_desc]--18\n" +
                                                                "      ,[me_num_orden]--19\n" +
                                                                "      ,[me_cntro_oper_cdgo]--20\n" +
                                                                "		  ,[co_cdgo]--21\n" +
                                                                "		  ,[co_desc]--22\n" +
                                                                "      ,[me_cntro_cost_auxiliar_cdgo]--23\n" +
                                                                "		  ,cca_origen.[cca_cdgo]--24\n" +
                                                                "		  ,cca_origen.[cca_cntro_cost_subcentro_cdgo]--25\n" +
                                                                "			,ccs_origen.[ccs_cdgo]--26\n" +
                                                                "			,ccs_origen.[ccs_desc]--27\n" +
                                                                "			,ccs_origen.[ccs_cntro_oper_cdgo]--28\n" +
                                                                "			,ccs_origen.[ccs_cntro_cost_rquiere_labor_realizda]--29\n" +
                                                                "		  ,cca_origen.[cca_desc]--30\n" +
                                                                "      ,[me_cntro_cost_cdgo]--31\n" +
                                                                "		  ,[cc_cdgo]--32\n" +
                                                                "		  ,[cc_descripcion]--33\n" +
                                                                "      ,[me_labor_realizada_cdgo]--34\n" +
                                                                "		  ,[lr_cdgo]--35\n" +
                                                                "		  ,[lr_desc]--36\n" +
                                                                "		  ,[lr_operativa]--37\n" +
                                                                "		  ,[lr_parada]--38\n" +
                                                                "      ,[me_cliente_cdgo]--39\n" +
                                                                "		  ,[cl_cdgo]--40\n" +
                                                                "		  ,[cl_desc]--41\n" +
                                                                "      ,[me_articulo_cdgo]--42\n" +
                                                                "		  ,[ar_cdgo]--43\n" +
                                                                "		  ,[ar_tipo_articulo_cdgo]--44\n" +
                                                                "			  ,[tar_cdgo]--45\n" +
                                                                "			  ,[tar_desc]--46\n" +
                                                                "			  ,[tar_cdgo_erp]--47\n" +
                                                                "			  ,[tar_undad_ngcio]--48\n" +
                                                                "		  ,[ar_desc]--49\n" +
                                                                "      ,[me_motonave_cdgo]--50\n" +
                                                                "		  ,[mn_cdgo]--51\n" +
                                                                "		  ,[mn_desc]--52\n" +
                                                                "      ,[me_fecha_hora_inicio]--53\n" +
                                                                "      ,[me_fecha_hora_fin]--54\n" +
                                                                "      ,[me_total_minutos]--55\n" +
                                                                "      ,[me_valor_hora]--56\n" +
                                                                "      ,[me_costo_total]--57\n" +
                                                                "      ,[me_recobro_cdgo]--58\n" +
                                                                "		  ,[rc_cdgo]--59\n" +
                                                                "		  ,[rc_desc]--60\n" +
                                                                "		  ,[rc_estad]--61\n" +
                                                                "      ,[me_cliente_recobro_cdgo]--62\n" +
                                                                "      ,[me_costo_total_recobro_cliente]--63\n" +
                                                                "      ,[me_usuario_registro_cdgo]--64\n" +
                                                                "			,us_registro.[us_cdgo]--65\n" +
                                                                "			,us_registro.[us_nombres]--66\n" +
                                                                "			,us_registro.[us_apellidos]--67\n" +
                                                                "			,us_registro.[us_correo]--68\n" +
                                                                "      ,[me_usuario_autorizacion_cdgo]--69\n" +
                                                                "			,us_autoriza.[us_cdgo]--70\n" +
                                                                "			,us_autoriza.[us_nombres]--71\n" +
                                                                "			,us_autoriza.[us_apellidos]--72\n" +
                                                                "			,us_autoriza.[us_correo]--73\n" +
                                                                "      ,[me_autorizacion_recobro_cdgo]--74\n" +
                                                                "			,[are_cdgo]--75\n" +
                                                                "			,[are_desc]--76\n" +
                                                                "			,[are_estad]--77\n" +
                                                                "      ,[me_observ_autorizacion]--78\n" +
                                                                "      ,[me_inactividad]--79\n" +
                                                                "      ,[me_causa_inactividad_cdgo]--80\n" +
                                                                "		  ,[ci_cdgo]--81\n" +
                                                                "		  ,[ci_desc]--82\n" +
                                                                "		  ,[ci_estad]--83\n" +
                                                                "      ,[me_usuario_inactividad_cdgo]--84\n" +
                                                                "		  ,us_inactividad.[us_cdgo]--85\n" +
                                                                "		  ,us_inactividad.[us_nombres]--86\n" +
                                                                "		  ,us_inactividad.[us_apellidos]--87\n" +
                                                                "		  ,us_inactividad.[us_correo]--88\n" +
                                                                "      ,[me_motivo_parada_estado]--89\n" +
                                                                "      ,[me_motivo_parada_cdgo]--90\n" +
                                                                "		  ,[mpa_cdgo]--91\n" +
                                                                "		  ,[mpa_desc]--92\n" +
                                                                "      ,[me_observ]--93\n" +
                                                                "      ,CASE [me_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_estado]--94\n" +
                                                                "      ,CASE [me_desde_mvto_carbon] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [me_desde_mvto_carbon]--95\n" +
                                                                "      ,[me_cntro_cost]--96\n" +
                                                                "      ,[me_cntro_cost_auxiliarDestino_cdgo]--97\n" +
                                                                "			,cca_destino.[cca_cdgo]--98\n" +
                                                                "			,cca_destino.[cca_cntro_cost_subcentro_cdgo]--99\n" +
                                                                "				,ccs_destino.[ccs_cdgo]--100\n" +
                                                                "				,ccs_destino.[ccs_desc]--101\n" +
                                                                "				,ccs_destino.[ccs_cntro_oper_cdgo]--102\n" +
                                                                "				,ccs_destino.[ccs_cntro_cost_rquiere_labor_realizda]--103\n" +
                                                                "			,cca_destino.[cca_desc]--104\n" +
                                                                "      ,[me_cntro_cost_mayor_cdgo]--105\n" +
                                                                "		  ,[ccm_cdgo]--106\n" +
                                                                "		  ,[ccm_desc]--107\n" +
                                                                "		  ,[te_cdgo] --108\n" +
                                                                "		  ,[te_desc]--109\n" +
                                                                "		  ,[ae_equipo_pertenencia_cdgo]--110\n" +
                                                                "				,[ep_cdgo]--111\n" +
                                                                "				,[ep_desc]--112\n" +
                                                                "				,[ep_estad]--113\n" +
                                                                "				,datename (MONTH ,[me_fecha])--114\n" +
                                                                "      ,me_cntro_cost_base_datos.[bd_cdgo]   --115 \n" +
                                                                "      ,me_cliente_base_datos.[bd_cdgo]   --116 \n" +
                                                                "      ,me_articulo_base_datos.[bd_cdgo]   --117 \n" +
                                                                "      ,me_motonave_base_datos.[bd_cdgo]   --118 \n" +
                                                                "      ,me_cntro_cost_mayor_base_datos.[bd_cdgo]  --119 \n" +
                                                                "  FROM ["+DB+"].[dbo].[mvto_equipo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [me_asignacion_equipo_cdgo]=[ae_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [me_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_oper] ON [me_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_origen ON  [me_cntro_cost_auxiliar_cdgo]=cca_origen.[cca_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_origen ON cca_origen.[cca_cntro_cost_subcentro_cdgo]=ccs_origen.[ccs_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cntro_cost] ON [me_cntro_cost_cdgo]=[cc_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost]  ON [me_cntro_cost_cdgo]=[cc_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_base_datos ON [cc_cliente_base_datos_cdgo]=me_cntro_cost_base_datos.[bd_cdgo] \n"+  
                                                                "  LEFT JOIN ["+DB+"].[dbo].[labor_realizada] ON [me_labor_realizada_cdgo]=[lr_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cliente] ON [me_cliente_cdgo]=[cl_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente]  ON [me_cliente_cdgo]=[cl_cdgo] AND [cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON [cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                                                                        //"  LEFT JOIN ["+DB+"].[dbo].[articulo] ON [me_articulo_cdgo]=[ar_cdgo]\n" +
        "		LEFT JOIN ["+DB+"].[dbo].[articulo]  ON [me_articulo_cdgo]=[ar_cdgo] AND [ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON [ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] ON [ar_tipo_articulo_cdgo]=[tar_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo]\n" +
                                                                        "		LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[me_motonave_base_datos_cdgo] \n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_motonave_base_datos ON  [mn_base_datos_cdgo]=me_motonave_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_registro ON [me_usuario_registro_cdgo]=us_registro.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_autoriza ON [me_usuario_autorizacion_cdgo]=us_autoriza.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[autorizacion_recobro] ON [me_autorizacion_recobro_cdgo]=[are_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[causa_inactividad] ON [me_causa_inactividad_cdgo]=[ci_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_inactividad ON [me_usuario_inactividad_cdgo]=us_inactividad.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[motivo_parada] ON [me_motivo_parada_cdgo]=[mpa_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_destino ON [me_cntro_cost_auxiliarDestino_cdgo]=cca_destino.[cca_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_destino ON cca_destino.[cca_cntro_cost_subcentro_cdgo]=ccs_destino.[ccs_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor] ON [me_cntro_cost_mayor_cdgo]=[ccm_cdgo]\n" +
                                                                        
"		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor]  ON [me_cntro_cost_mayor_cdgo]=[ccm_cdgo] \n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_mayor_base_datos ON [ccm_cliente_base_datos_cdgo]=me_cntro_cost_mayor_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON [ae_equipo_pertenencia_cdgo]=[ep_cdgo]"
                                                            + " WHERE  [me_cdgo]= ?");
            query.setString(1, codigoMvtoEquipo);
            ResultSet resultSet; resultSet= query.executeQuery();    
            boolean validator=true;
            while(resultSet.next()){
                if(validator){
                    mvtoEquipo = new MvtoEquipo();
                    validator=false;
                }
                AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                asignacionEquipo.setCodigo(resultSet.getString(3));  
                asignacionEquipo.setFechaRegistro(resultSet.getString(10));
                asignacionEquipo.setFechaHoraInicio(resultSet.getString(11));
                asignacionEquipo.setFechaHoraFin(resultSet.getString(12));
                asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(13));
                    Equipo equipo = new Equipo();
                    equipo.setCodigo(resultSet.getString(5));
                    equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(108),resultSet.getString(109),""));
                    equipo.setMarca(resultSet.getString(7));
                    equipo.setModelo(resultSet.getString(8));
                    equipo.setDescripcion(resultSet.getString(9));
                asignacionEquipo.setEquipo(equipo);
                asignacionEquipo.setPertenencia(new Pertenencia(resultSet.getString(111),resultSet.getString(112),""));
                    mvtoEquipo.setCodigo(resultSet.getString(1));
                    mvtoEquipo.setAsignacionEquipo(asignacionEquipo);
                    mvtoEquipo.setFechaRegistro(resultSet.getString(14));
                    mvtoEquipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(16),resultSet.getString(17),resultSet.getString(18),""));
                    mvtoEquipo.setNumeroOrden(resultSet.getString(19));
                        CentroOperacion me_co= new CentroOperacion();
                        me_co.setCodigo(Integer.parseInt(resultSet.getString(21)));
                        me_co.setDescripcion(resultSet.getString(22));
                    mvtoEquipo.setCentroOperacion(me_co);
                        CentroCostoSubCentro centroCostoSubCentro_mvtoEquipo = new CentroCostoSubCentro();
                        centroCostoSubCentro_mvtoEquipo.setCodigo(resultSet.getInt(26));
                        centroCostoSubCentro_mvtoEquipo.setDescripcion(resultSet.getString(27));
                        centroCostoSubCentro_mvtoEquipo.setCentroCostoRequiereLaborRealizada(resultSet.getString(29));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipo = new CentroCostoAuxiliar();
                        centroCostoAuxiliar_mvtoEquipo.setCodigo(resultSet.getInt(24));
                        centroCostoAuxiliar_mvtoEquipo.setDescripcion(resultSet.getString(30));
                        centroCostoAuxiliar_mvtoEquipo.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipo);
                    mvtoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipo);
                        CentroCosto centroCosto = new CentroCosto();
                        centroCosto.setCodigo(resultSet.getString(32));
                        centroCosto.setDescripción(resultSet.getString(33));
                        centroCosto.setClienteBaseDatos(resultSet.getString(115));
                    mvtoEquipo.setCentroCosto(centroCosto);
                        LaborRealizada laborRealizadaT = new LaborRealizada();
                        laborRealizadaT.setCodigo(resultSet.getString(35));
                        laborRealizadaT.setDescripcion(resultSet.getString(36));
                        laborRealizadaT.setEs_operativa(resultSet.getString(37));
                        laborRealizadaT.setEs_parada(resultSet.getString(38));
                    mvtoEquipo.setLaborRealizada(laborRealizadaT);
                    mvtoEquipo.setCliente(new Cliente(resultSet.getString(40),resultSet.getString(41),""));
                    mvtoEquipo.getCliente().setBaseDatos(new BaseDatos(resultSet.getString(116)));
                        TipoArticulo tipoArticulo = new TipoArticulo();
                                tipoArticulo.setCodigo(resultSet.getString(45));
                                tipoArticulo.setDescripcion(resultSet.getString(46));
                                tipoArticulo.setCodigoERP(resultSet.getString(47));
                                tipoArticulo.setUnidadNegocio(resultSet.getString(48));
                        Articulo articulo = new Articulo();
                        articulo.setCodigo(resultSet.getString(43));
                        articulo.setDescripcion(resultSet.getString(49));
                        articulo.setTipoArticulo(tipoArticulo);
                        articulo.setBaseDatos(new BaseDatos( resultSet.getString(117)));
                    mvtoEquipo.setArticulo(articulo);
                    Motonave motonave = new Motonave();
                    motonave.setCodigo(resultSet.getString(51));
                    motonave.setDescripcion(resultSet.getString(52));
                    motonave.setBaseDatos(new BaseDatos( resultSet.getString(118)));
                    mvtoEquipo.setMotonave(motonave);
                    mvtoEquipo.setFechaHoraInicio(resultSet.getString(53));
                mvtoEquipo.setFechaHoraFin(resultSet.getString(54));
                mvtoEquipo.setTotalMinutos(resultSet.getString(55));
                mvtoEquipo.setValorHora(resultSet.getString(56));
                mvtoEquipo.setCostoTotal(resultSet.getString(57));
                        Recobro recobro = new Recobro();
                        recobro.setCodigo(resultSet.getString(59));
                        recobro.setDescripcion(resultSet.getString(60));
                mvtoEquipo.setRecobro(recobro);
                        Usuario usuario_me_registra = new Usuario();
                        usuario_me_registra.setCodigo(resultSet.getString(65));
                        usuario_me_registra.setNombres(resultSet.getString(66));
                        usuario_me_registra.setApellidos(resultSet.getString(67));
                        usuario_me_registra.setCorreo(resultSet.getString(68));
                    mvtoEquipo.setUsuarioQuieRegistra(usuario_me_registra);
                        Usuario usuario_me_autoriza = new Usuario();
                        usuario_me_autoriza.setCodigo(resultSet.getString(70));
                        usuario_me_autoriza.setNombres(resultSet.getString(71));
                        usuario_me_autoriza.setApellidos(resultSet.getString(72));
                        usuario_me_autoriza.setCorreo(resultSet.getString(73));
                    mvtoEquipo.setUsuarioAutorizaRecobro(usuario_me_autoriza);
                        AutorizacionRecobro autorizacionRecobro = new AutorizacionRecobro();
                        autorizacionRecobro.setCodigo(resultSet.getString(75));
                        autorizacionRecobro.setDescripcion(resultSet.getString(76));
                        autorizacionRecobro.setEstado(resultSet.getString(77));
                    mvtoEquipo.setAutorizacionRecobro(autorizacionRecobro);
                    mvtoEquipo.setObservacionAutorizacion(resultSet.getString(78));
                    mvtoEquipo.setInactividad(resultSet.getString(79));
                        CausaInactividad causaInactividad = new CausaInactividad();
                        causaInactividad.setCodigo(resultSet.getString(81));
                        causaInactividad.setDescripcion(resultSet.getString(82));
                        causaInactividad.setEstado(resultSet.getString(83));
                    mvtoEquipo.setCausaInactividad(causaInactividad);
                        Usuario usuario_me_us_inactividad = new Usuario();
                        usuario_me_us_inactividad.setCodigo(resultSet.getString(85));
                        usuario_me_us_inactividad.setNombres(resultSet.getString(86));
                        usuario_me_us_inactividad.setApellidos(resultSet.getString(87));
                        usuario_me_us_inactividad.setCorreo(resultSet.getString(88));
                    mvtoEquipo.setMotivoParadaEstado(resultSet.getString(89));
                    mvtoEquipo.setUsuarioInactividad(usuario_me_us_inactividad);
                        MotivoParada motivoParada= new MotivoParada();
                        motivoParada.setCodigo(resultSet.getString(91));
                        motivoParada.setDescripcion(resultSet.getString(92));
                    mvtoEquipo.setMotivoParada(motivoParada);
                    mvtoEquipo.setObservacionMvtoEquipo(resultSet.getString(93));
                    mvtoEquipo.setEstado(resultSet.getString(94));
                    mvtoEquipo.setDesdeCarbon(resultSet.getString(95));
                    mvtoEquipo.setCentroCostoDescripción(resultSet.getString(96));
                    CentroCostoSubCentro centroCostoSubCentro_mvtoEquipoDestino = new CentroCostoSubCentro();
                        centroCostoSubCentro_mvtoEquipoDestino.setCodigo(resultSet.getInt(100));
                        centroCostoSubCentro_mvtoEquipoDestino.setDescripcion(resultSet.getString(101));
                        centroCostoSubCentro_mvtoEquipoDestino.setCentroCostoRequiereLaborRealizada(resultSet.getString(103));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipoDestino = new CentroCostoAuxiliar();
                        centroCostoAuxiliar_mvtoEquipoDestino.setCodigo(resultSet.getInt(98));
                        centroCostoAuxiliar_mvtoEquipoDestino.setDescripcion(resultSet.getString(104));
                        centroCostoAuxiliar_mvtoEquipoDestino.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipoDestino);
                    mvtoEquipo.setCentroCostoAuxiliarDestino(centroCostoAuxiliar_mvtoEquipoDestino);
                        CentroCostoMayor centroCostoMayor = new CentroCostoMayor();
                        centroCostoMayor.setCodigo(resultSet.getString(106));
                        centroCostoMayor.setDescripcion(resultSet.getString(107));
                        centroCostoMayor.setClienteBaseDatos(resultSet.getString(119));
                    mvtoEquipo.setCentroCostoMayor(centroCostoMayor);
                    mvtoEquipo.setMes(resultSet.getString(114));
                    try{
                        TarifaEquipo tarifa=new ControlDB_Equipo(tipoConexion).buscarTarifaEquipoPorAño(mvtoEquipo.getAsignacionEquipo().getEquipo().getCodigo(),mvtoEquipo.getFechaHoraInicio());
                        if(mvtoEquipo.getFechaHoraFin() != null){
                            if(tarifa != null){
                                mvtoEquipo.setValorHora(tarifa.getValorHoraOperacion());
                                try{
                                    double des=Double.parseDouble(mvtoEquipo.getTotalMinutos());
                                    double totalHoras = Double.parseDouble(""+(des/60));
                                    double valorHora =Double.parseDouble(tarifa.getValorHoraOperacion());
                                    //mvtoEquipo.setCostoTotal(""+(totalHoras * valorHora ));  
                                    DecimalFormat formato2 = new DecimalFormat("0.00");
                                    mvtoEquipo.setCostoTotal(formato2.format((totalHoras * valorHora )));
                                    //mvtoEquipo.setCostoTotalRecobroCliente(formato2.format((totalHoras * valorHora )));
                                    mvtoEquipo.setCostoTotalRecobroCliente("0");
                                }catch(Exception e){
                                    //mvto_listEquipo.getMvtoEquipo().setCostoTotal("NO PUDE");
                                }
                            }
                        }  
                    }catch(Exception e){
                        System.out.println("Error al procesar tarifa");
                    }
            }
        }catch (SQLException sqlException) {
            System.out.println("Error al tratar al consultar los Movimientos de Carbon");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return mvtoEquipo;
    } 
    
    
    public int                                      modificarMvtoEquipo(MvtoEquipo mvtoEquipo, Usuario us, String scriptDB, String scriptAuditoria, String razonModificacion) throws FileNotFoundException, UnknownHostException, SocketException{
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        int result=0;
        try{  
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();           
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_equipo] SET "+scriptDB+" WHERE [me_cdgo]=?");
            queryActualizar.setString(1,mvtoEquipo.getCodigo());
            queryActualizar.execute();
            result=1;
            if(result==1){
                result=0;
                //Extraemos el nombre del Equipo y la IP        
                String namePc=new ControlDB_Config().getNamePC();
                String ipPc=new ControlDB_Config().getIpPc();
                String macPC=new ControlDB_Config().getMacAddress();

                PreparedStatement Query_Auditoria= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[auditoria]([au_cdgo]\n" +
                                        "      ,[au_fecha]\n" +
                                        "      ,[au_usuario_cdgo_registro]\n" +
                                        "      ,[au_nombre_dispositivo_registro]\n" +
                                        "      ,[au_ip_dispositivo_registro]\n" +
                                        "      ,[au_mac_dispositivo_registro]\n" +
                                        "      ,[au_cdgo_mtvo]\n" +
                                        "      ,[au_desc_mtvo]\n" +
                                        "      ,[au_detalle_mtvo])\n" +
                        "     VALUES("+
                        "           (SELECT (CASE WHEN (MAX([au_cdgo]) IS NULL) THEN 1 ELSE (MAX([au_cdgo])+1) END)AS [au_cdgo] FROM ["+DB+"].[dbo].[auditoria])"+
                        "           ,(SELECT SYSDATETIME())"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,'MODULO_EQUIPO_MODIFICAR_REGISTROS'" +
                        "           ,CONCAT('Se realizó la siguiente modificación sobre el registro  Código:',?,' Modificación: ',?,' Razon de activación: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, mvtoEquipo.getCodigo());
                Query_Auditoria.setString(6, mvtoEquipo.getCodigo());
                Query_Auditoria.setString(7, scriptAuditoria);
                Query_Auditoria.setString(8, razonModificacion);
                Query_Auditoria.execute();
                result=1;
            } 
        }
               
        catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "ERROR al querer insertar los datos.");
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    } 
    public int                                      modificarMvtoEquipoCarbon(String scriptDB_Listado,MvtoEquipo mvtoEquipo, Usuario us, String scriptDB, String scriptAuditoria, String razonModificacion) throws FileNotFoundException, UnknownHostException, SocketException{
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        int result=0;
        try{  
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();           
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_equipo] SET "+scriptDB+" WHERE [me_cdgo]=?");
            queryActualizar.setString(1,mvtoEquipo.getCodigo());
            queryActualizar.execute();
            result=1;
            if(result==1){
                if(!scriptDB_Listado.equals("")){
                    PreparedStatement queryActualizar2= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_carbon_listado_equipo] SET "+scriptDB_Listado+" WHERE [mcle_mvto_equipo_cdgo]=?");
                    queryActualizar2.setString(1,mvtoEquipo.getCodigo());
                    queryActualizar2.execute();
                }
                result=0;
                //Extraemos el nombre del Equipo y la IP        
                String namePc=new ControlDB_Config().getNamePC();
                String ipPc=new ControlDB_Config().getIpPc();
                String macPC=new ControlDB_Config().getMacAddress();

                PreparedStatement Query_Auditoria= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[auditoria]([au_cdgo]\n" +
                                        "      ,[au_fecha]\n" +
                                        "      ,[au_usuario_cdgo_registro]\n" +
                                        "      ,[au_nombre_dispositivo_registro]\n" +
                                        "      ,[au_ip_dispositivo_registro]\n" +
                                        "      ,[au_mac_dispositivo_registro]\n" +
                                        "      ,[au_cdgo_mtvo]\n" +
                                        "      ,[au_desc_mtvo]\n" +
                                        "      ,[au_detalle_mtvo])\n" +
                        "     VALUES("+
                        "           (SELECT (CASE WHEN (MAX([au_cdgo]) IS NULL) THEN 1 ELSE (MAX([au_cdgo])+1) END)AS [au_cdgo] FROM ["+DB+"].[dbo].[auditoria])"+
                        "           ,(SELECT SYSDATETIME())"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,'MODULO_EQUIPO_MODIFICAR_REGISTROS'" +
                        "           ,CONCAT('Se realizó la siguiente modificación sobre el registro  Código:',?,' Modificación: ',?,' Razon de activación: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, mvtoEquipo.getCodigo());
                Query_Auditoria.setString(6, mvtoEquipo.getCodigo());
                Query_Auditoria.setString(7, scriptAuditoria);
                Query_Auditoria.setString(8, razonModificacion);
                Query_Auditoria.execute();
                result=1;
            } 
        }
               
        catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "ERROR al querer insertar los datos.");
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    } 
    public ArrayList<MvtoEquipo>                    buscar_MvtoEquipo_Activos (String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<MvtoEquipo> listadoObjetos = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  [me_cdgo]--1\n" +
                                                                "      ,[me_asignacion_equipo_cdgo]--2\n" +
                                                                "			,[ae_cdgo]--3\n" +
                                                                "			,[ae_equipo_cdgo]--4\n" +
                                                                "				,[eq_cdgo]--5\n" +
                                                                "				,[eq_tipo_equipo_cdgo]--6\n" +
                                                                "				,[eq_marca]--7\n" +
                                                                "				,[eq_modelo]--8\n" +
                                                                "				,[eq_desc]	--9\n" +
                                                                "			,[ae_fecha_registro]--10\n" +
                                                                "			,[ae_fecha_hora_inicio]--11\n" +
                                                                "			,[ae_fecha_hora_fin]--12\n" +
                                                                "			,[ae_cant_minutos]--13\n" +
                                                                "      ,[me_fecha]--14\n" +
                                                                "      ,[me_proveedor_equipo_cdgo]--15\n" +
                                                                "			,[pe_cdgo]--16\n" +
                                                                "			,[pe_nit]--17\n" +
                                                                "			,[pe_desc]--18\n" +
                                                                "      ,[me_num_orden]--19\n" +
                                                                "      ,[me_cntro_oper_cdgo]--20\n" +
                                                                "		  ,[co_cdgo]--21\n" +
                                                                "		  ,[co_desc]--22\n" +
                                                                "      ,[me_cntro_cost_auxiliar_cdgo]--23\n" +
                                                                "		  ,cca_origen.[cca_cdgo]--24\n" +
                                                                "		  ,cca_origen.[cca_cntro_cost_subcentro_cdgo]--25\n" +
                                                                "			,ccs_origen.[ccs_cdgo]--26\n" +
                                                                "			,ccs_origen.[ccs_desc]--27\n" +
                                                                "			,ccs_origen.[ccs_cntro_oper_cdgo]--28\n" +
                                                                "			,ccs_origen.[ccs_cntro_cost_rquiere_labor_realizda]--29\n" +
                                                                "		  ,cca_origen.[cca_desc]--30\n" +
                                                                "      ,[me_cntro_cost_cdgo]--31\n" +
                                                                "		  ,[cc_cdgo]--32\n" +
                                                                "		  ,[cc_descripcion]--33\n" +
                                                                "      ,[me_labor_realizada_cdgo]--34\n" +
                                                                "		  ,[lr_cdgo]--35\n" +
                                                                "		  ,[lr_desc]--36\n" +
                                                                "		  ,[lr_operativa]--37\n" +
                                                                "		  ,[lr_parada]--38\n" +
                                                                "      ,[me_cliente_cdgo]--39\n" +
                                                                "		  ,[cl_cdgo]--40\n" +
                                                                "		  ,[cl_desc]--41\n" +
                                                                "      ,[me_articulo_cdgo]--42\n" +
                                                                "		  ,[ar_cdgo]--43\n" +
                                                                "		  ,[ar_tipo_articulo_cdgo]--44\n" +
                                                                "			  ,[tar_cdgo]--45\n" +
                                                                "			  ,[tar_desc]--46\n" +
                                                                "			  ,[tar_cdgo_erp]--47\n" +
                                                                "			  ,[tar_undad_ngcio]--48\n" +
                                                                "		  ,[ar_desc]--49\n" +
                                                                "      ,[me_motonave_cdgo]--50\n" +
                                                                "		  ,[mn_cdgo]--51\n" +
                                                                "		  ,[mn_desc]--52\n" +
                                                                "      ,[me_fecha_hora_inicio]--53\n" +
                                                                "      ,[me_fecha_hora_fin]--54\n" +
                                                                "      ,[me_total_minutos]--55\n" +
                                                                "      ,[me_valor_hora]--56\n" +
                                                                "      ,[me_costo_total]--57\n" +
                                                                "      ,[me_recobro_cdgo]--58\n" +
                                                                "		  ,[rc_cdgo]--59\n" +
                                                                "		  ,[rc_desc]--60\n" +
                                                                "		  ,[rc_estad]--61\n" +
                                                                "      ,[me_cliente_recobro_cdgo]--62\n" +
                                                                "      ,[me_costo_total_recobro_cliente]--63\n" +
                                                                "      ,[me_usuario_registro_cdgo]--64\n" +
                                                                "			,us_registro.[us_cdgo]--65\n" +
                                                                "			,us_registro.[us_nombres]--66\n" +
                                                                "			,us_registro.[us_apellidos]--67\n" +
                                                                "			,us_registro.[us_correo]--68\n" +
                                                                "      ,[me_usuario_autorizacion_cdgo]--69\n" +
                                                                "			,us_autoriza.[us_cdgo]--70\n" +
                                                                "			,us_autoriza.[us_nombres]--71\n" +
                                                                "			,us_autoriza.[us_apellidos]--72\n" +
                                                                "			,us_autoriza.[us_correo]--73\n" +
                                                                "      ,[me_autorizacion_recobro_cdgo]--74\n" +
                                                                "			,[are_cdgo]--75\n" +
                                                                "			,[are_desc]--76\n" +
                                                                "			,[are_estad]--77\n" +
                                                                "      ,[me_observ_autorizacion]--78\n" +
                                                                "      ,[me_inactividad]--79\n" +
                                                                "      ,[me_causa_inactividad_cdgo]--80\n" +
                                                                "		  ,[ci_cdgo]--81\n" +
                                                                "		  ,[ci_desc]--82\n" +
                                                                "		  ,[ci_estad]--83\n" +
                                                                "      ,[me_usuario_inactividad_cdgo]--84\n" +
                                                                "		  ,us_inactividad.[us_cdgo]--85\n" +
                                                                "		  ,us_inactividad.[us_nombres]--86\n" +
                                                                "		  ,us_inactividad.[us_apellidos]--87\n" +
                                                                "		  ,us_inactividad.[us_correo]--88\n" +
                                                                "      ,[me_motivo_parada_estado]--89\n" +
                                                                "      ,[me_motivo_parada_cdgo]--90\n" +
                                                                "		  ,[mpa_cdgo]--91\n" +
                                                                "		  ,[mpa_desc]--92\n" +
                                                                "      ,[me_observ]--93\n" +
                                                                "      ,CASE [me_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_estado]--94\n" +
                                                                "      ,CASE [me_desde_mvto_carbon] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [me_desde_mvto_carbon]--95\n" +
                                                                "      ,[me_cntro_cost]--96\n" +
                                                                "      ,[me_cntro_cost_auxiliarDestino_cdgo]--97\n" +
                                                                "			,cca_destino.[cca_cdgo]--98\n" +
                                                                "			,cca_destino.[cca_cntro_cost_subcentro_cdgo]--99\n" +
                                                                "				,ccs_destino.[ccs_cdgo]--100\n" +
                                                                "				,ccs_destino.[ccs_desc]--101\n" +
                                                                "				,ccs_destino.[ccs_cntro_oper_cdgo]--102\n" +
                                                                "				,ccs_destino.[ccs_cntro_cost_rquiere_labor_realizda]--103\n" +
                                                                "			,cca_destino.[cca_desc]--104\n" +
                                                                "      ,[me_cntro_cost_mayor_cdgo]--105\n" +
                                                                "		  ,[ccm_cdgo]--106\n" +
                                                                "		  ,[ccm_desc]--107\n" +
                                                                "		  ,[te_cdgo] --108\n" +
                                                                "		  ,[te_desc]--109\n" +
                                                                "		  ,[ae_equipo_pertenencia_cdgo]--110\n" +
                                                                "				,[ep_cdgo]--111\n" +
                                                                "				,[ep_desc]--112\n" +
                                                                "				,[ep_estad]--113\n" +
                                                                "				,datename (MONTH ,[me_fecha])--114\n" +
                                                               "				,[cce_cdgo]--115\n" +
                                                                "				,[cce_cdgo_intern]--116\n" +
                                                                "				,[cce_desc]--117\n" +
                                                                "				,[cce_estad]--118\n" +
                                                                "	,[me_usuario_cierre_cdgo] --119\n" +
                                                                "		,us_cierre.[us_cdgo] --120\n" +
                                                                "               ,us_cierre.[us_nombres]--121\n" +
                                                                "               ,us_cierre.[us_apellidos] --122 \n" +
                                                                "      ,me_cntro_cost_base_datos.[bd_cdgo]   --123 \n" +
                                                                "      ,me_cliente_base_datos.[bd_cdgo]   --124 \n" +
                                                                "      ,me_articulo_base_datos.[bd_cdgo]   --125 \n" +
                                                                "      ,me_motonave_base_datos.[bd_cdgo]   --126 \n" +
                                                                "      ,me_cntro_cost_mayor_base_datos.[bd_cdgo]  --127 \n" +
                                                                "  FROM ["+DB+"].[dbo].[mvto_equipo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [me_asignacion_equipo_cdgo]=[ae_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [me_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_oper] ON [me_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_origen ON  [me_cntro_cost_auxiliar_cdgo]=cca_origen.[cca_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_origen ON cca_origen.[cca_cntro_cost_subcentro_cdgo]=ccs_origen.[ccs_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cntro_cost] ON [me_cntro_cost_cdgo]=[cc_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost]  ON [me_cntro_cost_cdgo]=[cc_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_base_datos ON [cc_cliente_base_datos_cdgo]=me_cntro_cost_base_datos.[bd_cdgo] \n"+  
                                                                        "  LEFT JOIN ["+DB+"].[dbo].[labor_realizada] ON [me_labor_realizada_cdgo]=[lr_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cliente] ON [me_cliente_cdgo]=[cl_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente]  ON [me_cliente_cdgo]=[cl_cdgo] AND [cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON [cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                                                                //                "  LEFT JOIN ["+DB+"].[dbo].[articulo] ON [me_articulo_cdgo]=[ar_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[articulo]  ON [me_articulo_cdgo]=[ar_cdgo] AND [ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON [ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+       
                                                                "  LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] ON [ar_tipo_articulo_cdgo]=[tar_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo]\n" +
                                                                 "		LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[me_motonave_base_datos_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_motonave_base_datos ON  [mn_base_datos_cdgo]=me_motonave_base_datos.[bd_cdgo] \n"+       
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_registro ON [me_usuario_registro_cdgo]=us_registro.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_autoriza ON [me_usuario_autorizacion_cdgo]=us_autoriza.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[autorizacion_recobro] ON [me_autorizacion_recobro_cdgo]=[are_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[causa_inactividad] ON [me_causa_inactividad_cdgo]=[ci_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_inactividad ON [me_usuario_inactividad_cdgo]=us_inactividad.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[motivo_parada] ON [me_motivo_parada_cdgo]=[mpa_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_destino ON [me_cntro_cost_auxiliarDestino_cdgo]=cca_destino.[cca_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_destino ON cca_destino.[cca_cntro_cost_subcentro_cdgo]=ccs_destino.[ccs_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor] ON [me_cntro_cost_mayor_cdgo]=[ccm_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor]  ON [me_cntro_cost_mayor_cdgo]=[ccm_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_mayor_base_datos ON [ccm_cliente_base_datos_cdgo]=me_cntro_cost_mayor_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON [ae_equipo_pertenencia_cdgo]=[ep_cdgo]\n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_equipo] ON [cce_cdgo]=[eq_cntro_cost_equipo_cdgo] "+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_cierre ON [me_usuario_cierre_cdgo] =us_cierre.[us_cdgo] \n"
                                                            + "  WHERE  [me_fecha_hora_inicio] BETWEEN ? AND ?  AND [me_inactividad]=0 AND [me_estado]=1 AND [me_desde_mvto_carbon]=0 ORDER BY [me_cdgo] DESC");
            query.setString(1, DatetimeInicio);
            query.setString(2, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();    
            boolean validator=true;
            while(resultSet.next()){
                if(validator){
                    listadoObjetos = new ArrayList();
                    validator=false;
                }    
                AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                asignacionEquipo.setCodigo(resultSet.getString(3));  
                asignacionEquipo.setFechaRegistro(resultSet.getString(10));
                asignacionEquipo.setFechaHoraInicio(resultSet.getString(11));
                asignacionEquipo.setFechaHoraFin(resultSet.getString(12));
                asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(13));
                    Equipo equipo = new Equipo();
                    equipo.setCodigo(resultSet.getString(5));
                    equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(108),resultSet.getString(109),""));
                    equipo.setMarca(resultSet.getString(7));
                    equipo.setModelo(resultSet.getString(8));
                    equipo.setDescripcion(resultSet.getString(9));
                        CentroCostoEquipo centroCostoEquipo = new CentroCostoEquipo();
                            centroCostoEquipo.setCodigo(resultSet.getString(115));
                            centroCostoEquipo.setCodigoInterno(resultSet.getString(116));
                            centroCostoEquipo.setDescripcion(resultSet.getString(117));
                            centroCostoEquipo.setEstado(resultSet.getString(118));
                    equipo.setCentroCostoEquipo(centroCostoEquipo);
                asignacionEquipo.setEquipo(equipo);
                asignacionEquipo.setPertenencia(new Pertenencia(resultSet.getString(111),resultSet.getString(112),""));
                MvtoEquipo mvtoEquipo = new MvtoEquipo();
                    mvtoEquipo.setCodigo(resultSet.getString(1));
                    mvtoEquipo.setAsignacionEquipo(asignacionEquipo);
                    mvtoEquipo.setFechaRegistro(resultSet.getString(14));
                    mvtoEquipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(16),resultSet.getString(17),resultSet.getString(18),""));
                    mvtoEquipo.setNumeroOrden(resultSet.getString(19));
                        CentroOperacion me_co= new CentroOperacion();
                        me_co.setCodigo(Integer.parseInt(resultSet.getString(21)));
                        me_co.setDescripcion(resultSet.getString(22));
                    mvtoEquipo.setCentroOperacion(me_co);
                        CentroCostoSubCentro centroCostoSubCentro_mvtoEquipo = new CentroCostoSubCentro();
                        centroCostoSubCentro_mvtoEquipo.setCodigo(resultSet.getInt(26));
                        centroCostoSubCentro_mvtoEquipo.setDescripcion(resultSet.getString(27));
                        centroCostoSubCentro_mvtoEquipo.setCentroCostoRequiereLaborRealizada(resultSet.getString(29));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipo = new CentroCostoAuxiliar();
                        centroCostoAuxiliar_mvtoEquipo.setCodigo(resultSet.getInt(24));
                        centroCostoAuxiliar_mvtoEquipo.setDescripcion(resultSet.getString(30));
                        centroCostoAuxiliar_mvtoEquipo.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipo);
                    mvtoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipo);
                        CentroCosto centroCosto = new CentroCosto();
                        centroCosto.setCodigo(resultSet.getString(32));
                        centroCosto.setDescripción(resultSet.getString(33));
                        centroCosto.setClienteBaseDatos(resultSet.getString(123));
                    mvtoEquipo.setCentroCosto(centroCosto);
                        LaborRealizada laborRealizadaT = new LaborRealizada();
                        laborRealizadaT.setCodigo(resultSet.getString(35));
                        laborRealizadaT.setDescripcion(resultSet.getString(36));
                        laborRealizadaT.setEs_operativa(resultSet.getString(37));
                        laborRealizadaT.setEs_parada(resultSet.getString(38));
                    mvtoEquipo.setLaborRealizada(laborRealizadaT);
                    mvtoEquipo.setCliente(new Cliente(resultSet.getString(40),resultSet.getString(41),""));
                    mvtoEquipo.getCliente().setBaseDatos(new BaseDatos(resultSet.getString(124)));
                        TipoArticulo tipoArticulo = new TipoArticulo();
                                tipoArticulo.setCodigo(resultSet.getString(45));
                                tipoArticulo.setDescripcion(resultSet.getString(46));
                                tipoArticulo.setCodigoERP(resultSet.getString(47));
                                tipoArticulo.setUnidadNegocio(resultSet.getString(48));
                        Articulo articulo = new Articulo();
                        articulo.setCodigo(resultSet.getString(43));
                        articulo.setDescripcion(resultSet.getString(49));
                        articulo.setBaseDatos(new BaseDatos( resultSet.getString(125)));
                        articulo.setTipoArticulo(tipoArticulo);
                    mvtoEquipo.setArticulo(articulo);
                    Motonave motonave = new Motonave();
                    motonave.setCodigo(resultSet.getString(51));
                    motonave.setDescripcion(resultSet.getString(52));
                    motonave.setBaseDatos(new BaseDatos( resultSet.getString(126)));
                    mvtoEquipo.setMotonave(motonave);
                    mvtoEquipo.setFechaHoraInicio(resultSet.getString(53));
                mvtoEquipo.setFechaHoraFin(resultSet.getString(54));
                mvtoEquipo.setTotalMinutos(resultSet.getString(55));
                mvtoEquipo.setValorHora(resultSet.getString(56));
                mvtoEquipo.setCostoTotal(resultSet.getString(57));
                        Recobro recobro = new Recobro();
                        recobro.setCodigo(resultSet.getString(59));
                        recobro.setDescripcion(resultSet.getString(60));
                mvtoEquipo.setRecobro(recobro);
                        Usuario usuario_me_registra = new Usuario();
                        usuario_me_registra.setCodigo(resultSet.getString(65));
                        usuario_me_registra.setNombres(resultSet.getString(66));
                        usuario_me_registra.setApellidos(resultSet.getString(67));
                        usuario_me_registra.setCorreo(resultSet.getString(68));
                    mvtoEquipo.setUsuarioQuieRegistra(usuario_me_registra);
                        Usuario usuario_me_autoriza = new Usuario();
                        usuario_me_autoriza.setCodigo(resultSet.getString(70));
                        usuario_me_autoriza.setNombres(resultSet.getString(71));
                        usuario_me_autoriza.setApellidos(resultSet.getString(72));
                        usuario_me_autoriza.setCorreo(resultSet.getString(73));
                    mvtoEquipo.setUsuarioAutorizaRecobro(usuario_me_autoriza);
                        AutorizacionRecobro autorizacionRecobro = new AutorizacionRecobro();
                        autorizacionRecobro.setCodigo(resultSet.getString(75));
                        autorizacionRecobro.setDescripcion(resultSet.getString(76));
                        autorizacionRecobro.setEstado(resultSet.getString(77));
                    mvtoEquipo.setAutorizacionRecobro(autorizacionRecobro);
                    mvtoEquipo.setObservacionAutorizacion(resultSet.getString(78));
                    mvtoEquipo.setInactividad(resultSet.getString(79));
                        CausaInactividad causaInactividad = new CausaInactividad();
                        causaInactividad.setCodigo(resultSet.getString(81));
                        causaInactividad.setDescripcion(resultSet.getString(82));
                        causaInactividad.setEstado(resultSet.getString(83));
                    mvtoEquipo.setCausaInactividad(causaInactividad);
                        Usuario usuario_me_us_inactividad = new Usuario();
                        usuario_me_us_inactividad.setCodigo(resultSet.getString(85));
                        usuario_me_us_inactividad.setNombres(resultSet.getString(86));
                        usuario_me_us_inactividad.setApellidos(resultSet.getString(87));
                        usuario_me_us_inactividad.setCorreo(resultSet.getString(88));
                    mvtoEquipo.setMotivoParadaEstado(resultSet.getString(89));
                    mvtoEquipo.setUsuarioInactividad(usuario_me_us_inactividad);
                        MotivoParada motivoParada= new MotivoParada();
                        motivoParada.setCodigo(resultSet.getString(91));
                        motivoParada.setDescripcion(resultSet.getString(92));
                    mvtoEquipo.setMotivoParada(motivoParada);
                    mvtoEquipo.setObservacionMvtoEquipo(resultSet.getString(93));
                    mvtoEquipo.setEstado(resultSet.getString(94));
                    mvtoEquipo.setDesdeCarbon(resultSet.getString(95));
                    mvtoEquipo.setCentroCostoDescripción(resultSet.getString(96));
                    CentroCostoSubCentro centroCostoSubCentro_mvtoEquipoDestino = new CentroCostoSubCentro();
                        centroCostoSubCentro_mvtoEquipoDestino.setCodigo(resultSet.getInt(100));
                        centroCostoSubCentro_mvtoEquipoDestino.setDescripcion(resultSet.getString(101));
                        centroCostoSubCentro_mvtoEquipoDestino.setCentroCostoRequiereLaborRealizada(resultSet.getString(103));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipoDestino = new CentroCostoAuxiliar();
                        centroCostoAuxiliar_mvtoEquipoDestino.setCodigo(resultSet.getInt(98));
                        centroCostoAuxiliar_mvtoEquipoDestino.setDescripcion(resultSet.getString(104));
                        centroCostoAuxiliar_mvtoEquipoDestino.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipoDestino);
                    mvtoEquipo.setCentroCostoAuxiliarDestino(centroCostoAuxiliar_mvtoEquipoDestino);
                        CentroCostoMayor centroCostoMayor = new CentroCostoMayor();
                        centroCostoMayor.setCodigo(resultSet.getString(106));
                        centroCostoMayor.setDescripcion(resultSet.getString(107));
                        centroCostoMayor.setClienteBaseDatos(resultSet.getString(127));
                    mvtoEquipo.setCentroCostoMayor(centroCostoMayor);
                    mvtoEquipo.setMes(resultSet.getString(114));
                        Usuario usuario_me_Cierra = new Usuario();
                            usuario_me_Cierra.setCodigo(resultSet.getString(120));
                            usuario_me_Cierra.setNombres(resultSet.getString(121));
                            usuario_me_Cierra.setApellidos(resultSet.getString(122));
                    mvtoEquipo.setUsuarioQuienCierra(usuario_me_Cierra); 
                listadoObjetos.add(mvtoEquipo);
            }
        }catch (SQLException sqlException) {
            System.out.println("Error al tratar al consultar los Movimientos de Carbon");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public MvtoEquipo                               procesarMvtoEquipoParticular(MvtoEquipo mvtoEquipoI) throws SQLException{
        MvtoEquipo mvtoEquipo = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  [me_cdgo]--1\n" +
                                                                "      ,[me_asignacion_equipo_cdgo]--2\n" +
                                                                "			,[ae_cdgo]--3\n" +
                                                                "			,[ae_equipo_cdgo]--4\n" +
                                                                "				,[eq_cdgo]--5\n" +
                                                                "				,[eq_tipo_equipo_cdgo]--6\n" +
                                                                "				,[eq_marca]--7\n" +
                                                                "				,[eq_modelo]--8\n" +
                                                                "				,[eq_desc]	--9\n" +
                                                                "			,[ae_fecha_registro]--10\n" +
                                                                "			,[ae_fecha_hora_inicio]--11\n" +
                                                                "			,[ae_fecha_hora_fin]--12\n" +
                                                                "			,[ae_cant_minutos]--13\n" +
                                                                "      ,[me_fecha]--14\n" +
                                                                "      ,[me_proveedor_equipo_cdgo]--15\n" +
                                                                "			,[pe_cdgo]--16\n" +
                                                                "			,[pe_nit]--17\n" +
                                                                "			,[pe_desc]--18\n" +
                                                                "      ,[me_num_orden]--19\n" +
                                                                "      ,[me_cntro_oper_cdgo]--20\n" +
                                                                "		  ,[co_cdgo]--21\n" +
                                                                "		  ,[co_desc]--22\n" +
                                                                "      ,[me_cntro_cost_auxiliar_cdgo]--23\n" +
                                                                "		  ,cca_origen.[cca_cdgo]--24\n" +
                                                                "		  ,cca_origen.[cca_cntro_cost_subcentro_cdgo]--25\n" +
                                                                "			,ccs_origen.[ccs_cdgo]--26\n" +
                                                                "			,ccs_origen.[ccs_desc]--27\n" +
                                                                "			,ccs_origen.[ccs_cntro_oper_cdgo]--28\n" +
                                                                "			,ccs_origen.[ccs_cntro_cost_rquiere_labor_realizda]--29\n" +
                                                                "		  ,cca_origen.[cca_desc]--30\n" +
                                                                "      ,[me_cntro_cost_cdgo]--31\n" +
                                                                "		  ,[cc_cdgo]--32\n" +
                                                                "		  ,[cc_descripcion]--33\n" +
                                                                "      ,[me_labor_realizada_cdgo]--34\n" +
                                                                "		  ,[lr_cdgo]--35\n" +
                                                                "		  ,[lr_desc]--36\n" +
                                                                "		  ,[lr_operativa]--37\n" +
                                                                "		  ,[lr_parada]--38\n" +
                                                                "      ,[me_cliente_cdgo]--39\n" +
                                                                "		  ,[cl_cdgo]--40\n" +
                                                                "		  ,[cl_desc]--41\n" +
                                                                "      ,[me_articulo_cdgo]--42\n" +
                                                                "		  ,[ar_cdgo]--43\n" +
                                                                "		  ,[ar_tipo_articulo_cdgo]--44\n" +
                                                                "			  ,[tar_cdgo]--45\n" +
                                                                "			  ,[tar_desc]--46\n" +
                                                                "			  ,[tar_cdgo_erp]--47\n" +
                                                                "			  ,[tar_undad_ngcio]--48\n" +
                                                                "		  ,[ar_desc]--49\n" +
                                                                "      ,[me_motonave_cdgo]--50\n" +
                                                                "		  ,[mn_cdgo]--51\n" +
                                                                "		  ,[mn_desc]--52\n" +
                                                                "      ,[me_fecha_hora_inicio]--53\n" +
                                                                "      ,[me_fecha_hora_fin]--54\n" +
                                                                "      ,[me_total_minutos]--55\n" +
                                                                "      ,[me_valor_hora]--56\n" +
                                                                "      ,[me_costo_total]--57\n" +
                                                                "      ,[me_recobro_cdgo]--58\n" +
                                                                "		  ,[rc_cdgo]--59\n" +
                                                                "		  ,[rc_desc]--60\n" +
                                                                "		  ,[rc_estad]--61\n" +
                                                                "      ,[me_cliente_recobro_cdgo]--62\n" +
                                                                "      ,[me_costo_total_recobro_cliente]--63\n" +
                                                                "      ,[me_usuario_registro_cdgo]--64\n" +
                                                                "			,us_registro.[us_cdgo]--65\n" +
                                                                "			,us_registro.[us_nombres]--66\n" +
                                                                "			,us_registro.[us_apellidos]--67\n" +
                                                                "			,us_registro.[us_correo]--68\n" +
                                                                "      ,[me_usuario_autorizacion_cdgo]--69\n" +
                                                                "			,us_autoriza.[us_cdgo]--70\n" +
                                                                "			,us_autoriza.[us_nombres]--71\n" +
                                                                "			,us_autoriza.[us_apellidos]--72\n" +
                                                                "			,us_autoriza.[us_correo]--73\n" +
                                                                "      ,[me_autorizacion_recobro_cdgo]--74\n" +
                                                                "			,[are_cdgo]--75\n" +
                                                                "			,[are_desc]--76\n" +
                                                                "			,[are_estad]--77\n" +
                                                                "      ,[me_observ_autorizacion]--78\n" +
                                                                "      ,[me_inactividad]--79\n" +
                                                                "      ,[me_causa_inactividad_cdgo]--80\n" +
                                                                "		  ,[ci_cdgo]--81\n" +
                                                                "		  ,[ci_desc]--82\n" +
                                                                "		  ,[ci_estad]--83\n" +
                                                                "      ,[me_usuario_inactividad_cdgo]--84\n" +
                                                                "		  ,us_inactividad.[us_cdgo]--85\n" +
                                                                "		  ,us_inactividad.[us_nombres]--86\n" +
                                                                "		  ,us_inactividad.[us_apellidos]--87\n" +
                                                                "		  ,us_inactividad.[us_correo]--88\n" +
                                                                "      ,[me_motivo_parada_estado]--89\n" +
                                                                "      ,[me_motivo_parada_cdgo]--90\n" +
                                                                "		  ,[mpa_cdgo]--91\n" +
                                                                "		  ,[mpa_desc]--92\n" +
                                                                "      ,[me_observ]--93\n" +
                                                                "      ,CASE [me_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_estado]--94\n" +
                                                                "      ,CASE [me_desde_mvto_carbon] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [me_desde_mvto_carbon]--95\n" +
                                                                "      ,[me_cntro_cost]--96\n" +
                                                                "      ,[me_cntro_cost_auxiliarDestino_cdgo]--97\n" +
                                                                "			,cca_destino.[cca_cdgo]--98\n" +
                                                                "			,cca_destino.[cca_cntro_cost_subcentro_cdgo]--99\n" +
                                                                "				,ccs_destino.[ccs_cdgo]--100\n" +
                                                                "				,ccs_destino.[ccs_desc]--101\n" +
                                                                "				,ccs_destino.[ccs_cntro_oper_cdgo]--102\n" +
                                                                "				,ccs_destino.[ccs_cntro_cost_rquiere_labor_realizda]--103\n" +
                                                                "			,cca_destino.[cca_desc]--104\n" +
                                                                "      ,[me_cntro_cost_mayor_cdgo]--105\n" +
                                                                "		  ,[ccm_cdgo]--106\n" +
                                                                "		  ,[ccm_desc]--107\n" +
                                                                "		  ,[te_cdgo] --108\n" +
                                                                "		  ,[te_desc]--109\n" +
                                                                "		  ,[ae_equipo_pertenencia_cdgo]--110\n" +
                                                                "				,[ep_cdgo]--111\n" +
                                                                "				,[ep_desc]--112\n" +
                                                                "				,[ep_estad]--113\n" +
                                                                "				,datename (MONTH ,[me_fecha])--114\n" +
                                                                "      ,me_cntro_cost_base_datos.[bd_cdgo]   --115 \n" +
                                                                "      ,me_cliente_base_datos.[bd_cdgo]   --116 \n" +
                                                                "      ,me_articulo_base_datos.[bd_cdgo]   --117 \n" +
                                                                "      ,me_motonave_base_datos.[bd_cdgo]   --118 \n" +
                                                                "      ,me_cntro_cost_mayor_base_datos.[bd_cdgo]  --119 \n" +
                                                                "  FROM ["+DB+"].[dbo].[mvto_equipo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [me_asignacion_equipo_cdgo]=[ae_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [me_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_oper] ON [me_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_origen ON  [me_cntro_cost_auxiliar_cdgo]=cca_origen.[cca_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_origen ON cca_origen.[cca_cntro_cost_subcentro_cdgo]=ccs_origen.[ccs_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cntro_cost] ON [me_cntro_cost_cdgo]=[cc_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost]  ON [me_cntro_cost_cdgo]=[cc_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_base_datos ON [cc_cliente_base_datos_cdgo]=me_cntro_cost_base_datos.[bd_cdgo] \n"+    
                                                                "  LEFT JOIN ["+DB+"].[dbo].[labor_realizada] ON [me_labor_realizada_cdgo]=[lr_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cliente] ON [me_cliente_cdgo]=[cl_cdgo]\n" +
                                                                        "		LEFT JOIN ["+DB+"].[dbo].[cliente]  ON [me_cliente_cdgo]=[cl_cdgo] AND [cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON [cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[articulo] ON [me_articulo_cdgo]=[ar_cdgo]\n" +
        "		LEFT JOIN ["+DB+"].[dbo].[articulo]  ON [me_articulo_cdgo]=[ar_cdgo] AND [ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON [ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] ON [ar_tipo_articulo_cdgo]=[tar_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo]\n" +
                                                                        
"		LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[me_motonave_base_datos_cdgo] \n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_motonave_base_datos ON  [mn_base_datos_cdgo]=me_motonave_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_registro ON [me_usuario_registro_cdgo]=us_registro.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_autoriza ON [me_usuario_autorizacion_cdgo]=us_autoriza.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[autorizacion_recobro] ON [me_autorizacion_recobro_cdgo]=[are_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[causa_inactividad] ON [me_causa_inactividad_cdgo]=[ci_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_inactividad ON [me_usuario_inactividad_cdgo]=us_inactividad.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[motivo_parada] ON [me_motivo_parada_cdgo]=[mpa_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_destino ON [me_cntro_cost_auxiliarDestino_cdgo]=cca_destino.[cca_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_destino ON cca_destino.[cca_cntro_cost_subcentro_cdgo]=ccs_destino.[ccs_cdgo]\n" +
                                                               // "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor] ON [me_cntro_cost_mayor_cdgo]=[ccm_cdgo]\n" +
                                                                        "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor]  ON [me_cntro_cost_mayor_cdgo]=[ccm_cdgo] \n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_mayor_base_datos ON [ccm_cliente_base_datos_cdgo]=me_cntro_cost_mayor_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON [ae_equipo_pertenencia_cdgo]=[ep_cdgo]"
                                                            + " WHERE  [me_cdgo]=?");
            query.setString(1, mvtoEquipoI.getCodigo());
            ResultSet resultSet; resultSet= query.executeQuery();    
            while(resultSet.next()){
                AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                asignacionEquipo.setCodigo(resultSet.getString(3));  
                asignacionEquipo.setFechaRegistro(resultSet.getString(10));
                asignacionEquipo.setFechaHoraInicio(resultSet.getString(11));
                asignacionEquipo.setFechaHoraFin(resultSet.getString(12));
                asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(13));
                    Equipo equipo = new Equipo();
                    equipo.setCodigo(resultSet.getString(5));
                    equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(108),resultSet.getString(109),""));
                    equipo.setMarca(resultSet.getString(7));
                    equipo.setModelo(resultSet.getString(8));
                    equipo.setDescripcion(resultSet.getString(9));
                asignacionEquipo.setEquipo(equipo);
                asignacionEquipo.setPertenencia(new Pertenencia(resultSet.getString(111),resultSet.getString(112),""));
                 mvtoEquipo = new MvtoEquipo();
                    mvtoEquipo.setCodigo(resultSet.getString(1));
                    mvtoEquipo.setAsignacionEquipo(asignacionEquipo);
                    mvtoEquipo.setFechaRegistro(resultSet.getString(14));
                    mvtoEquipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(16),resultSet.getString(17),resultSet.getString(18),""));
                    mvtoEquipo.setNumeroOrden(resultSet.getString(19));
                        CentroOperacion me_co= new CentroOperacion();
                        me_co.setCodigo(Integer.parseInt(resultSet.getString(21)));
                        me_co.setDescripcion(resultSet.getString(22));
                    mvtoEquipo.setCentroOperacion(me_co);
                        CentroCostoSubCentro centroCostoSubCentro_mvtoEquipo = new CentroCostoSubCentro();
                        centroCostoSubCentro_mvtoEquipo.setCodigo(resultSet.getInt(26));
                        centroCostoSubCentro_mvtoEquipo.setDescripcion(resultSet.getString(27));
                        centroCostoSubCentro_mvtoEquipo.setCentroCostoRequiereLaborRealizada(resultSet.getString(29));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipo = new CentroCostoAuxiliar();
                        centroCostoAuxiliar_mvtoEquipo.setCodigo(resultSet.getInt(24));
                        centroCostoAuxiliar_mvtoEquipo.setDescripcion(resultSet.getString(30));
                        centroCostoAuxiliar_mvtoEquipo.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipo);
                    mvtoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipo);
                        CentroCosto centroCosto = new CentroCosto();
                        centroCosto.setCodigo(resultSet.getString(32));
                        centroCosto.setDescripción(resultSet.getString(33));
                        centroCosto.setClienteBaseDatos(resultSet.getString(115));
                    mvtoEquipo.setCentroCosto(centroCosto);
                        LaborRealizada laborRealizadaT = new LaborRealizada();
                        laborRealizadaT.setCodigo(resultSet.getString(35));
                        laborRealizadaT.setDescripcion(resultSet.getString(36));
                        laborRealizadaT.setEs_operativa(resultSet.getString(37));
                        laborRealizadaT.setEs_parada(resultSet.getString(38));
                    mvtoEquipo.setLaborRealizada(laborRealizadaT);
                    mvtoEquipo.setCliente(new Cliente(resultSet.getString(40),resultSet.getString(41),""));
                    mvtoEquipo.getCliente().setBaseDatos(new BaseDatos(resultSet.getString(116)));
                        TipoArticulo tipoArticulo = new TipoArticulo();
                                tipoArticulo.setCodigo(resultSet.getString(45));
                                tipoArticulo.setDescripcion(resultSet.getString(46));
                                tipoArticulo.setCodigoERP(resultSet.getString(47));
                                tipoArticulo.setUnidadNegocio(resultSet.getString(48));
                        Articulo articulo = new Articulo();
                        articulo.setCodigo(resultSet.getString(43));
                        articulo.setDescripcion(resultSet.getString(49));
                        articulo.setBaseDatos(new BaseDatos( resultSet.getString(117)));
                        articulo.setTipoArticulo(tipoArticulo);
                    mvtoEquipo.setArticulo(articulo);
                    Motonave motonave = new Motonave();
                    motonave.setCodigo(resultSet.getString(51));
                    motonave.setDescripcion(resultSet.getString(52));
                    motonave.setBaseDatos(new BaseDatos( resultSet.getString(118)));
                    mvtoEquipo.setMotonave(motonave);
                    mvtoEquipo.setFechaHoraInicio(resultSet.getString(53));
                mvtoEquipo.setFechaHoraFin(resultSet.getString(54));
                mvtoEquipo.setTotalMinutos(resultSet.getString(55));
                mvtoEquipo.setValorHora(resultSet.getString(56));
                mvtoEquipo.setCostoTotal(resultSet.getString(57));
                        Recobro recobro = new Recobro();
                        recobro.setCodigo(resultSet.getString(59));
                        recobro.setDescripcion(resultSet.getString(60));
                mvtoEquipo.setRecobro(recobro);
                        Usuario usuario_me_registra = new Usuario();
                        usuario_me_registra.setCodigo(resultSet.getString(65));
                        usuario_me_registra.setNombres(resultSet.getString(66));
                        usuario_me_registra.setApellidos(resultSet.getString(67));
                        usuario_me_registra.setCorreo(resultSet.getString(68));
                mvtoEquipo.setUsuarioQuieRegistra(usuario_me_registra);
                    Usuario usuario_me_autoriza = new Usuario();
                    usuario_me_autoriza.setCodigo(resultSet.getString(70));
                    usuario_me_autoriza.setNombres(resultSet.getString(71));
                    usuario_me_autoriza.setApellidos(resultSet.getString(72));
                    usuario_me_autoriza.setCorreo(resultSet.getString(73));
                mvtoEquipo.setUsuarioAutorizaRecobro(usuario_me_autoriza);
                    AutorizacionRecobro autorizacionRecobro = new AutorizacionRecobro();
                    autorizacionRecobro.setCodigo(resultSet.getString(75));
                    autorizacionRecobro.setDescripcion(resultSet.getString(76));
                    autorizacionRecobro.setEstado(resultSet.getString(77));
                mvtoEquipo.setAutorizacionRecobro(autorizacionRecobro);
                mvtoEquipo.setObservacionAutorizacion(resultSet.getString(78));
                mvtoEquipo.setInactividad(resultSet.getString(79));
                    CausaInactividad causaInactividad = new CausaInactividad();
                    causaInactividad.setCodigo(resultSet.getString(81));
                    causaInactividad.setDescripcion(resultSet.getString(82));
                    causaInactividad.setEstado(resultSet.getString(83));
                mvtoEquipo.setCausaInactividad(causaInactividad);
                    Usuario usuario_me_us_inactividad = new Usuario();
                    usuario_me_us_inactividad.setCodigo(resultSet.getString(85));
                    usuario_me_us_inactividad.setNombres(resultSet.getString(86));
                    usuario_me_us_inactividad.setApellidos(resultSet.getString(87));
                    usuario_me_us_inactividad.setCorreo(resultSet.getString(88));
                mvtoEquipo.setMotivoParadaEstado(resultSet.getString(89));
                mvtoEquipo.setUsuarioInactividad(usuario_me_us_inactividad);
                    MotivoParada motivoParada= new MotivoParada();
                    motivoParada.setCodigo(resultSet.getString(91));
                    motivoParada.setDescripcion(resultSet.getString(92));
                mvtoEquipo.setMotivoParada(motivoParada);
                mvtoEquipo.setObservacionMvtoEquipo(resultSet.getString(93));
                mvtoEquipo.setEstado(resultSet.getString(94));
                mvtoEquipo.setDesdeCarbon(resultSet.getString(95));
                mvtoEquipo.setCentroCostoDescripción(resultSet.getString(96));
                CentroCostoSubCentro centroCostoSubCentro_mvtoEquipoDestino = new CentroCostoSubCentro();
                    centroCostoSubCentro_mvtoEquipoDestino.setCodigo(resultSet.getInt(100));
                    centroCostoSubCentro_mvtoEquipoDestino.setDescripcion(resultSet.getString(101));
                    centroCostoSubCentro_mvtoEquipoDestino.setCentroCostoRequiereLaborRealizada(resultSet.getString(103));
                    CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipoDestino = new CentroCostoAuxiliar();
                    centroCostoAuxiliar_mvtoEquipoDestino.setCodigo(resultSet.getInt(98));
                    centroCostoAuxiliar_mvtoEquipoDestino.setDescripcion(resultSet.getString(104));
                    centroCostoAuxiliar_mvtoEquipoDestino.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipoDestino);
                mvtoEquipo.setCentroCostoAuxiliarDestino(centroCostoAuxiliar_mvtoEquipoDestino);
                    CentroCostoMayor centroCostoMayor = new CentroCostoMayor();
                    centroCostoMayor.setCodigo(resultSet.getString(106));
                    centroCostoMayor.setDescripcion(resultSet.getString(107));
                    centroCostoMayor.setClienteBaseDatos(resultSet.getString(119));
                mvtoEquipo.setCentroCostoMayor(centroCostoMayor);
                mvtoEquipo.setMes(resultSet.getString(114));
                try{
                    TarifaEquipo tarifa=new ControlDB_Equipo(tipoConexion).buscarTarifaEquipoPorAño(mvtoEquipo.getAsignacionEquipo().getEquipo().getCodigo(),mvtoEquipo.getFechaHoraInicio());
                    if(mvtoEquipo.getFechaHoraFin() != null){
                        if(tarifa != null){
                            mvtoEquipo.setValorHora(tarifa.getValorHoraOperacion());
                            try{
                                double des=Double.parseDouble(mvtoEquipo.getTotalMinutos());
                                double totalHoras = Double.parseDouble(""+(des/60));
                                double valorHora =Double.parseDouble(tarifa.getValorHoraOperacion());
                                //mvtoEquipo.setCostoTotal(""+(totalHoras * valorHora ));  
                                DecimalFormat formato2 = new DecimalFormat("0.00");
                                mvtoEquipo.setCostoTotal(formato2.format((totalHoras * valorHora )));
                                //mvtoEquipo.setCostoTotalRecobroCliente(formato2.format((totalHoras * valorHora )));
                                mvtoEquipo.setCostoTotalRecobroCliente("0");
                            }catch(Exception e){
                                //mvto_listEquipo.getMvtoEquipo().setCostoTotal("NO PUDE");
                            }
                        }
                    }  
                }catch(Exception e){
                    System.out.println("Error al procesar tarifa");
                }  
            }
        }catch (SQLException sqlException) {
            System.out.println("Error al tratar al consultar los Movimientos de Equipos");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return mvtoEquipo;
    } 
    public ArrayList<MvtoEquipo>                    buscar_mvtoEquiposActivosGeneral(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<MvtoEquipo> listadoObjetos = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  [me_cdgo]--1\n" +
                                                                "      ,[me_asignacion_equipo_cdgo]--2\n" +
                                                                "			,[ae_cdgo]--3\n" +
                                                                "			,[ae_equipo_cdgo]--4\n" +
                                                                "				,[eq_cdgo]--5\n" +
                                                                "				,[eq_tipo_equipo_cdgo]--6\n" +
                                                                "				,[eq_marca]--7\n" +
                                                                "				,[eq_modelo]--8\n" +
                                                                "				,[eq_desc]	--9\n" +
                                                                "			,[ae_fecha_registro]--10\n" +
                                                                "			,[ae_fecha_hora_inicio]--11\n" +
                                                                "			,[ae_fecha_hora_fin]--12\n" +
                                                                "			,[ae_cant_minutos]--13\n" +
                                                                "      ,[me_fecha]--14\n" +
                                                                "      ,[me_proveedor_equipo_cdgo]--15\n" +
                                                                "			,[pe_cdgo]--16\n" +
                                                                "			,[pe_nit]--17\n" +
                                                                "			,[pe_desc]--18\n" +
                                                                "      ,[me_num_orden]--19\n" +
                                                                "      ,[me_cntro_oper_cdgo]--20\n" +
                                                                "		  ,[co_cdgo]--21\n" +
                                                                "		  ,[co_desc]--22\n" +
                                                                "      ,[me_cntro_cost_auxiliar_cdgo]--23\n" +
                                                                "		  ,cca_origen.[cca_cdgo]--24\n" +
                                                                "		  ,cca_origen.[cca_cntro_cost_subcentro_cdgo]--25\n" +
                                                                "			,ccs_origen.[ccs_cdgo]--26\n" +
                                                                "			,ccs_origen.[ccs_desc]--27\n" +
                                                                "			,ccs_origen.[ccs_cntro_oper_cdgo]--28\n" +
                                                                "			,ccs_origen.[ccs_cntro_cost_rquiere_labor_realizda]--29\n" +
                                                                "		  ,cca_origen.[cca_desc]--30\n" +
                                                                "      ,[me_cntro_cost_cdgo]--31\n" +
                                                                "		  ,[cc_cdgo]--32\n" +
                                                                "		  ,[cc_descripcion]--33\n" +
                                                                "      ,[me_labor_realizada_cdgo]--34\n" +
                                                                "		  ,[lr_cdgo]--35\n" +
                                                                "		  ,[lr_desc]--36\n" +
                                                                "		  ,[lr_operativa]--37\n" +
                                                                "		  ,[lr_parada]--38\n" +
                                                                "      ,[me_cliente_cdgo]--39\n" +
                                                                "		  ,[cl_cdgo]--40\n" +
                                                                "		  ,[cl_desc]--41\n" +
                                                                "      ,[me_articulo_cdgo]--42\n" +
                                                                "		  ,[ar_cdgo]--43\n" +
                                                                "		  ,[ar_tipo_articulo_cdgo]--44\n" +
                                                                "			  ,[tar_cdgo]--45\n" +
                                                                "			  ,[tar_desc]--46\n" +
                                                                "			  ,[tar_cdgo_erp]--47\n" +
                                                                "			  ,[tar_undad_ngcio]--48\n" +
                                                                "		  ,[ar_desc]--49\n" +
                                                                "      ,[me_motonave_cdgo]--50\n" +
                                                                "		  ,[mn_cdgo]--51\n" +
                                                                "		  ,[mn_desc]--52\n" +
                                                                "      ,[me_fecha_hora_inicio]--53\n" +
                                                                "      ,[me_fecha_hora_fin]--54\n" +
                                                                "      ,[me_total_minutos]--55\n" +
                                                                "      ,[me_valor_hora]--56\n" +
                                                                "      ,[me_costo_total]--57\n" +
                                                                "      ,[me_recobro_cdgo]--58\n" +
                                                                "		  ,[rc_cdgo]--59\n" +
                                                                "		  ,[rc_desc]--60\n" +
                                                                "		  ,[rc_estad]--61\n" +
                                                                "      ,[me_cliente_recobro_cdgo]--62\n" +
                                                                "      ,[me_costo_total_recobro_cliente]--63\n" +
                                                                "      ,[me_usuario_registro_cdgo]--64\n" +
                                                                "			,us_registro.[us_cdgo]--65\n" +
                                                                "			,us_registro.[us_nombres]--66\n" +
                                                                "			,us_registro.[us_apellidos]--67\n" +
                                                                "			,us_registro.[us_correo]--68\n" +
                                                                "      ,[me_usuario_autorizacion_cdgo]--69\n" +
                                                                "			,us_autoriza.[us_cdgo]--70\n" +
                                                                "			,us_autoriza.[us_nombres]--71\n" +
                                                                "			,us_autoriza.[us_apellidos]--72\n" +
                                                                "			,us_autoriza.[us_correo]--73\n" +
                                                                "      ,[me_autorizacion_recobro_cdgo]--74\n" +
                                                                "			,[are_cdgo]--75\n" +
                                                                "			,[are_desc]--76\n" +
                                                                "			,[are_estad]--77\n" +
                                                                "      ,[me_observ_autorizacion]--78\n" +
                                                                "      ,[me_inactividad]--79\n" +
                                                                "      ,[me_causa_inactividad_cdgo]--80\n" +
                                                                "		  ,[ci_cdgo]--81\n" +
                                                                "		  ,[ci_desc]--82\n" +
                                                                "		  ,[ci_estad]--83\n" +
                                                                "      ,[me_usuario_inactividad_cdgo]--84\n" +
                                                                "		  ,us_inactividad.[us_cdgo]--85\n" +
                                                                "		  ,us_inactividad.[us_nombres]--86\n" +
                                                                "		  ,us_inactividad.[us_apellidos]--87\n" +
                                                                "		  ,us_inactividad.[us_correo]--88\n" +
                                                                "      ,[me_motivo_parada_estado]--89\n" +
                                                                "      ,[me_motivo_parada_cdgo]--90\n" +
                                                                "		  ,[mpa_cdgo]--91\n" +
                                                                "		  ,[mpa_desc]--92\n" +
                                                                "      ,[me_observ]--93\n" +
                                                                "      ,CASE [me_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_estado]--94\n" +
                                                                "      ,CASE [me_desde_mvto_carbon] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [me_desde_mvto_carbon]--95\n" +
                                                                "      ,[me_cntro_cost]--96\n" +
                                                                "      ,[me_cntro_cost_auxiliarDestino_cdgo]--97\n" +
                                                                "			,cca_destino.[cca_cdgo]--98\n" +
                                                                "			,cca_destino.[cca_cntro_cost_subcentro_cdgo]--99\n" +
                                                                "				,ccs_destino.[ccs_cdgo]--100\n" +
                                                                "				,ccs_destino.[ccs_desc]--101\n" +
                                                                "				,ccs_destino.[ccs_cntro_oper_cdgo]--102\n" +
                                                                "				,ccs_destino.[ccs_cntro_cost_rquiere_labor_realizda]--103\n" +
                                                                "			,cca_destino.[cca_desc]--104\n" +
                                                                "      ,[me_cntro_cost_mayor_cdgo]--105\n" +
                                                                "		  ,[ccm_cdgo]--106\n" +
                                                                "		  ,[ccm_desc]--107\n" +
                                                                "		  ,[te_cdgo] --108\n" +
                                                                "		  ,[te_desc]--109\n" +
                                                                "		  ,[ae_equipo_pertenencia_cdgo]--110\n" +
                                                                "				,[ep_cdgo]--111\n" +
                                                                "				,[ep_desc]--112\n" +
                                                                "				,[ep_estad]--113\n" +
                                                                "				,datename (MONTH ,[me_fecha])--114\n" +
                                                                "      ,me_cntro_cost_base_datos.[bd_cdgo]   --115 \n" +
                                                                "      ,me_cliente_base_datos.[bd_cdgo]   --116 \n" +
                                                                "      ,me_articulo_base_datos.[bd_cdgo]   --117 \n" +
                                                                "      ,me_motonave_base_datos.[bd_cdgo]   --118 \n" +
                                                                "      ,me_cntro_cost_mayor_base_datos.[bd_cdgo]  --119 \n" +
                                                                "  FROM ["+DB+"].[dbo].[mvto_equipo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [me_asignacion_equipo_cdgo]=[ae_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [me_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_oper] ON [me_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_origen ON  [me_cntro_cost_auxiliar_cdgo]=cca_origen.[cca_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_origen ON cca_origen.[cca_cntro_cost_subcentro_cdgo]=ccs_origen.[ccs_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cntro_cost] ON [me_cntro_cost_cdgo]=[cc_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost]  ON [me_cntro_cost_cdgo]=[cc_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_base_datos ON [cc_cliente_base_datos_cdgo]=me_cntro_cost_base_datos.[bd_cdgo] \n"+  
                                                                "  LEFT JOIN ["+DB+"].[dbo].[labor_realizada] ON [me_labor_realizada_cdgo]=[lr_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cliente] ON [me_cliente_cdgo]=[cl_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente]  ON [me_cliente_cdgo]=[cl_cdgo] AND [cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON [cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                                                                        //"  LEFT JOIN ["+DB+"].[dbo].[articulo] ON [me_articulo_cdgo]=[ar_cdgo]\n" +
        "		LEFT JOIN ["+DB+"].[dbo].[articulo]  ON [me_articulo_cdgo]=[ar_cdgo] AND [ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON [ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] ON [ar_tipo_articulo_cdgo]=[tar_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo]\n" +
                                                                        "		LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[me_motonave_base_datos_cdgo] \n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_motonave_base_datos ON  [mn_base_datos_cdgo]=me_motonave_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_registro ON [me_usuario_registro_cdgo]=us_registro.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_autoriza ON [me_usuario_autorizacion_cdgo]=us_autoriza.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[autorizacion_recobro] ON [me_autorizacion_recobro_cdgo]=[are_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[causa_inactividad] ON [me_causa_inactividad_cdgo]=[ci_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_inactividad ON [me_usuario_inactividad_cdgo]=us_inactividad.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[motivo_parada] ON [me_motivo_parada_cdgo]=[mpa_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_destino ON [me_cntro_cost_auxiliarDestino_cdgo]=cca_destino.[cca_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_destino ON cca_destino.[cca_cntro_cost_subcentro_cdgo]=ccs_destino.[ccs_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor] ON [me_cntro_cost_mayor_cdgo]=[ccm_cdgo]\n" +
                                                                        
"		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor]  ON [me_cntro_cost_mayor_cdgo]=[ccm_cdgo] \n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_mayor_base_datos ON [ccm_cliente_base_datos_cdgo]=me_cntro_cost_mayor_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON [ae_equipo_pertenencia_cdgo]=[ep_cdgo]"
                                                            + " WHERE  [me_fecha_hora_inicio] BETWEEN ? AND ?  \n"
                                                            + "--AND [me_inactividad]=0 AND [me_estado]=1 \n"
                                                            + " ORDER BY [me_cdgo] DESC");
            query.setString(1, DatetimeInicio);
            query.setString(2, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();    
            boolean validator=true;
            while(resultSet.next()){
                if(validator){
                    listadoObjetos = new ArrayList();
                    validator=false;
                }
                AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                asignacionEquipo.setCodigo(resultSet.getString(3));  
                asignacionEquipo.setFechaRegistro(resultSet.getString(10));
                asignacionEquipo.setFechaHoraInicio(resultSet.getString(11));
                asignacionEquipo.setFechaHoraFin(resultSet.getString(12));
                asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(13));
                    Equipo equipo = new Equipo();
                    equipo.setCodigo(resultSet.getString(5));
                    equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(108),resultSet.getString(109),""));
                    equipo.setMarca(resultSet.getString(7));
                    equipo.setModelo(resultSet.getString(8));
                    equipo.setDescripcion(resultSet.getString(9));
                asignacionEquipo.setEquipo(equipo);
                asignacionEquipo.setPertenencia(new Pertenencia(resultSet.getString(111),resultSet.getString(112),""));
                MvtoEquipo mvtoEquipo = new MvtoEquipo();
                    mvtoEquipo.setCodigo(resultSet.getString(1));
                    mvtoEquipo.setAsignacionEquipo(asignacionEquipo);
                    mvtoEquipo.setFechaRegistro(resultSet.getString(14));
                    mvtoEquipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(16),resultSet.getString(17),resultSet.getString(18),""));
                    mvtoEquipo.setNumeroOrden(resultSet.getString(19));
                        CentroOperacion me_co= new CentroOperacion();
                        me_co.setCodigo(Integer.parseInt(resultSet.getString(21)));
                        me_co.setDescripcion(resultSet.getString(22));
                    mvtoEquipo.setCentroOperacion(me_co);
                        CentroCostoSubCentro centroCostoSubCentro_mvtoEquipo = new CentroCostoSubCentro();
                        centroCostoSubCentro_mvtoEquipo.setCodigo(resultSet.getInt(26));
                        centroCostoSubCentro_mvtoEquipo.setDescripcion(resultSet.getString(27));
                        centroCostoSubCentro_mvtoEquipo.setCentroCostoRequiereLaborRealizada(resultSet.getString(29));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipo = new CentroCostoAuxiliar();
                        centroCostoAuxiliar_mvtoEquipo.setCodigo(resultSet.getInt(24));
                        centroCostoAuxiliar_mvtoEquipo.setDescripcion(resultSet.getString(30));
                        centroCostoAuxiliar_mvtoEquipo.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipo);
                    mvtoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipo);
                        CentroCosto centroCosto = new CentroCosto();
                        centroCosto.setCodigo(resultSet.getString(32));
                        centroCosto.setDescripción(resultSet.getString(33));
                        centroCosto.setClienteBaseDatos(resultSet.getString(115));
                    mvtoEquipo.setCentroCosto(centroCosto);
                        LaborRealizada laborRealizadaT = new LaborRealizada();
                        laborRealizadaT.setCodigo(resultSet.getString(35));
                        laborRealizadaT.setDescripcion(resultSet.getString(36));
                        laborRealizadaT.setEs_operativa(resultSet.getString(37));
                        laborRealizadaT.setEs_parada(resultSet.getString(38));
                    mvtoEquipo.setLaborRealizada(laborRealizadaT);
                    mvtoEquipo.setCliente(new Cliente(resultSet.getString(40),resultSet.getString(41),""));
                    mvtoEquipo.getCliente().setBaseDatos(new BaseDatos(resultSet.getString(116)));
                        TipoArticulo tipoArticulo = new TipoArticulo();
                                tipoArticulo.setCodigo(resultSet.getString(45));
                                tipoArticulo.setDescripcion(resultSet.getString(46));
                                tipoArticulo.setCodigoERP(resultSet.getString(47));
                                tipoArticulo.setUnidadNegocio(resultSet.getString(48));
                        Articulo articulo = new Articulo();
                        articulo.setCodigo(resultSet.getString(43));
                        articulo.setDescripcion(resultSet.getString(49));
                        articulo.setTipoArticulo(tipoArticulo);
                        articulo.setBaseDatos(new BaseDatos( resultSet.getString(117)));
                    mvtoEquipo.setArticulo(articulo);
                    Motonave motonave = new Motonave();
                    motonave.setCodigo(resultSet.getString(51));
                    motonave.setDescripcion(resultSet.getString(52));
                    motonave.setBaseDatos(new BaseDatos( resultSet.getString(118)));
                    mvtoEquipo.setMotonave(motonave);
                    mvtoEquipo.setFechaHoraInicio(resultSet.getString(53));
                mvtoEquipo.setFechaHoraFin(resultSet.getString(54));
                mvtoEquipo.setTotalMinutos(resultSet.getString(55));
                mvtoEquipo.setValorHora(resultSet.getString(56));
                mvtoEquipo.setCostoTotal(resultSet.getString(57));
                        Recobro recobro = new Recobro();
                        recobro.setCodigo(resultSet.getString(59));
                        recobro.setDescripcion(resultSet.getString(60));
                mvtoEquipo.setRecobro(recobro);
                        Usuario usuario_me_registra = new Usuario();
                        usuario_me_registra.setCodigo(resultSet.getString(65));
                        usuario_me_registra.setNombres(resultSet.getString(66));
                        usuario_me_registra.setApellidos(resultSet.getString(67));
                        usuario_me_registra.setCorreo(resultSet.getString(68));
                    mvtoEquipo.setUsuarioQuieRegistra(usuario_me_registra);
                        Usuario usuario_me_autoriza = new Usuario();
                        usuario_me_autoriza.setCodigo(resultSet.getString(70));
                        usuario_me_autoriza.setNombres(resultSet.getString(71));
                        usuario_me_autoriza.setApellidos(resultSet.getString(72));
                        usuario_me_autoriza.setCorreo(resultSet.getString(73));
                    mvtoEquipo.setUsuarioAutorizaRecobro(usuario_me_autoriza);
                        AutorizacionRecobro autorizacionRecobro = new AutorizacionRecobro();
                        autorizacionRecobro.setCodigo(resultSet.getString(75));
                        autorizacionRecobro.setDescripcion(resultSet.getString(76));
                        autorizacionRecobro.setEstado(resultSet.getString(77));
                    mvtoEquipo.setAutorizacionRecobro(autorizacionRecobro);
                    mvtoEquipo.setObservacionAutorizacion(resultSet.getString(78));
                    mvtoEquipo.setInactividad(resultSet.getString(79));
                        CausaInactividad causaInactividad = new CausaInactividad();
                        causaInactividad.setCodigo(resultSet.getString(81));
                        causaInactividad.setDescripcion(resultSet.getString(82));
                        causaInactividad.setEstado(resultSet.getString(83));
                    mvtoEquipo.setCausaInactividad(causaInactividad);
                        Usuario usuario_me_us_inactividad = new Usuario();
                        usuario_me_us_inactividad.setCodigo(resultSet.getString(85));
                        usuario_me_us_inactividad.setNombres(resultSet.getString(86));
                        usuario_me_us_inactividad.setApellidos(resultSet.getString(87));
                        usuario_me_us_inactividad.setCorreo(resultSet.getString(88));
                    mvtoEquipo.setMotivoParadaEstado(resultSet.getString(89));
                    mvtoEquipo.setUsuarioInactividad(usuario_me_us_inactividad);
                        MotivoParada motivoParada= new MotivoParada();
                        motivoParada.setCodigo(resultSet.getString(91));
                        motivoParada.setDescripcion(resultSet.getString(92));
                    mvtoEquipo.setMotivoParada(motivoParada);
                    mvtoEquipo.setObservacionMvtoEquipo(resultSet.getString(93));
                    mvtoEquipo.setEstado(resultSet.getString(94));
                    mvtoEquipo.setDesdeCarbon(resultSet.getString(95));
                    mvtoEquipo.setCentroCostoDescripción(resultSet.getString(96));
                    CentroCostoSubCentro centroCostoSubCentro_mvtoEquipoDestino = new CentroCostoSubCentro();
                        centroCostoSubCentro_mvtoEquipoDestino.setCodigo(resultSet.getInt(100));
                        centroCostoSubCentro_mvtoEquipoDestino.setDescripcion(resultSet.getString(101));
                        centroCostoSubCentro_mvtoEquipoDestino.setCentroCostoRequiereLaborRealizada(resultSet.getString(103));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipoDestino = new CentroCostoAuxiliar();
                        centroCostoAuxiliar_mvtoEquipoDestino.setCodigo(resultSet.getInt(98));
                        centroCostoAuxiliar_mvtoEquipoDestino.setDescripcion(resultSet.getString(104));
                        centroCostoAuxiliar_mvtoEquipoDestino.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipoDestino);
                    mvtoEquipo.setCentroCostoAuxiliarDestino(centroCostoAuxiliar_mvtoEquipoDestino);
                        CentroCostoMayor centroCostoMayor = new CentroCostoMayor();
                        centroCostoMayor.setCodigo(resultSet.getString(106));
                        centroCostoMayor.setDescripcion(resultSet.getString(107));
                        centroCostoMayor.setClienteBaseDatos(resultSet.getString(119));
                    mvtoEquipo.setCentroCostoMayor(centroCostoMayor);
                    mvtoEquipo.setMes(resultSet.getString(114));
                    try{
                        TarifaEquipo tarifa=new ControlDB_Equipo(tipoConexion).buscarTarifaEquipoPorAño(mvtoEquipo.getAsignacionEquipo().getEquipo().getCodigo(),mvtoEquipo.getFechaHoraInicio());
                        if(mvtoEquipo.getFechaHoraFin() != null){
                            if(tarifa != null){
                                mvtoEquipo.setValorHora(tarifa.getValorHoraOperacion());
                                try{
                                    double des=Double.parseDouble(mvtoEquipo.getTotalMinutos());
                                    double totalHoras = Double.parseDouble(""+(des/60));
                                    double valorHora =Double.parseDouble(tarifa.getValorHoraOperacion());
                                    //mvtoEquipo.setCostoTotal(""+(totalHoras * valorHora ));  
                                    DecimalFormat formato2 = new DecimalFormat("0.00");
                                    mvtoEquipo.setCostoTotal(formato2.format((totalHoras * valorHora )));
                                    //mvtoEquipo.setCostoTotalRecobroCliente(formato2.format((totalHoras * valorHora )));
                                    mvtoEquipo.setCostoTotalRecobroCliente("0");
                                }catch(Exception e){
                                    //mvto_listEquipo.getMvtoEquipo().setCostoTotal("NO PUDE");
                                }
                            }
                        }  
                    }catch(Exception e){
                        System.out.println("Error al procesar tarifa");
                    }
                listadoObjetos.add(mvtoEquipo);
            }
        }catch (SQLException sqlException) {
            System.out.println("Error al tratar al consultar los Movimientos de Carbon");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public ArrayList<MvtoEquipo>                    buscar_mvtoEquiposActivosGeneralProgramados(String modalidad, String rango) throws SQLException{//Busca todos los movimientos del día según fecha actual
        ArrayList<MvtoEquipo> listadoObjetos = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  [me_cdgo]--1\n" +
                                                                "      ,[me_asignacion_equipo_cdgo]--2\n" +
                                                                "			,[ae_cdgo]--3\n" +
                                                                "			,[ae_equipo_cdgo]--4\n" +
                                                                "				,[eq_cdgo]--5\n" +
                                                                "				,[eq_tipo_equipo_cdgo]--6\n" +
                                                                "				,[eq_marca]--7\n" +
                                                                "				,[eq_modelo]--8\n" +
                                                                "				,[eq_desc]	--9\n" +
                                                                "			,[ae_fecha_registro]--10\n" +
                                                                "			,[ae_fecha_hora_inicio]--11\n" +
                                                                "			,[ae_fecha_hora_fin]--12\n" +
                                                                "			,[ae_cant_minutos]--13\n" +
                                                                "      ,[me_fecha]--14\n" +
                                                                "      ,[me_proveedor_equipo_cdgo]--15\n" +
                                                                "			,[pe_cdgo]--16\n" +
                                                                "			,[pe_nit]--17\n" +
                                                                "			,[pe_desc]--18\n" +
                                                                "      ,[me_num_orden]--19\n" +
                                                                "      ,[me_cntro_oper_cdgo]--20\n" +
                                                                "		  ,[co_cdgo]--21\n" +
                                                                "		  ,[co_desc]--22\n" +
                                                                "      ,[me_cntro_cost_auxiliar_cdgo]--23\n" +
                                                                "		  ,cca_origen.[cca_cdgo]--24\n" +
                                                                "		  ,cca_origen.[cca_cntro_cost_subcentro_cdgo]--25\n" +
                                                                "			,ccs_origen.[ccs_cdgo]--26\n" +
                                                                "			,ccs_origen.[ccs_desc]--27\n" +
                                                                "			,ccs_origen.[ccs_cntro_oper_cdgo]--28\n" +
                                                                "			,ccs_origen.[ccs_cntro_cost_rquiere_labor_realizda]--29\n" +
                                                                "		  ,cca_origen.[cca_desc]--30\n" +
                                                                "      ,[me_cntro_cost_cdgo]--31\n" +
                                                                "		  ,[cc_cdgo]--32\n" +
                                                                "		  ,[cc_descripcion]--33\n" +
                                                                "      ,[me_labor_realizada_cdgo]--34\n" +
                                                                "		  ,[lr_cdgo]--35\n" +
                                                                "		  ,[lr_desc]--36\n" +
                                                                "		  ,[lr_operativa]--37\n" +
                                                                "		  ,[lr_parada]--38\n" +
                                                                "      ,[me_cliente_cdgo]--39\n" +
                                                                "		  ,[cl_cdgo]--40\n" +
                                                                "		  ,[cl_desc]--41\n" +
                                                                "      ,[me_articulo_cdgo]--42\n" +
                                                                "		  ,[ar_cdgo]--43\n" +
                                                                "		  ,[ar_tipo_articulo_cdgo]--44\n" +
                                                                "			  ,[tar_cdgo]--45\n" +
                                                                "			  ,[tar_desc]--46\n" +
                                                                "			  ,[tar_cdgo_erp]--47\n" +
                                                                "			  ,[tar_undad_ngcio]--48\n" +
                                                                "		  ,[ar_desc]--49\n" +
                                                                "      ,[me_motonave_cdgo]--50\n" +
                                                                "		  ,[mn_cdgo]--51\n" +
                                                                "		  ,[mn_desc]--52\n" +
                                                                "      ,[me_fecha_hora_inicio]--53\n" +
                                                                "      ,[me_fecha_hora_fin]--54\n" +
                                                                "      ,[me_total_minutos]--55\n" +
                                                                "      ,[me_valor_hora]--56\n" +
                                                                "      ,[me_costo_total]--57\n" +
                                                                "      ,[me_recobro_cdgo]--58\n" +
                                                                "		  ,[rc_cdgo]--59\n" +
                                                                "		  ,[rc_desc]--60\n" +
                                                                "		  ,[rc_estad]--61\n" +
                                                                "      ,[me_cliente_recobro_cdgo]--62\n" +
                                                                "      ,[me_costo_total_recobro_cliente]--63\n" +
                                                                "      ,[me_usuario_registro_cdgo]--64\n" +
                                                                "			,us_registro.[us_cdgo]--65\n" +
                                                                "			,us_registro.[us_nombres]--66\n" +
                                                                "			,us_registro.[us_apellidos]--67\n" +
                                                                "			,us_registro.[us_correo]--68\n" +
                                                                "      ,[me_usuario_autorizacion_cdgo]--69\n" +
                                                                "			,us_autoriza.[us_cdgo]--70\n" +
                                                                "			,us_autoriza.[us_nombres]--71\n" +
                                                                "			,us_autoriza.[us_apellidos]--72\n" +
                                                                "			,us_autoriza.[us_correo]--73\n" +
                                                                "      ,[me_autorizacion_recobro_cdgo]--74\n" +
                                                                "			,[are_cdgo]--75\n" +
                                                                "			,[are_desc]--76\n" +
                                                                "			,[are_estad]--77\n" +
                                                                "      ,[me_observ_autorizacion]--78\n" +
                                                                "      ,[me_inactividad]--79\n" +
                                                                "      ,[me_causa_inactividad_cdgo]--80\n" +
                                                                "		  ,[ci_cdgo]--81\n" +
                                                                "		  ,[ci_desc]--82\n" +
                                                                "		  ,[ci_estad]--83\n" +
                                                                "      ,[me_usuario_inactividad_cdgo]--84\n" +
                                                                "		  ,us_inactividad.[us_cdgo]--85\n" +
                                                                "		  ,us_inactividad.[us_nombres]--86\n" +
                                                                "		  ,us_inactividad.[us_apellidos]--87\n" +
                                                                "		  ,us_inactividad.[us_correo]--88\n" +
                                                                "      ,[me_motivo_parada_estado]--89\n" +
                                                                "      ,[me_motivo_parada_cdgo]--90\n" +
                                                                "		  ,[mpa_cdgo]--91\n" +
                                                                "		  ,[mpa_desc]--92\n" +
                                                                "      ,[me_observ]--93\n" +
                                                                "      ,CASE [me_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_estado]--94\n" +
                                                                "      ,CASE [me_desde_mvto_carbon] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [me_desde_mvto_carbon]--95\n" +
                                                                "      ,[me_cntro_cost]--96\n" +
                                                                "      ,[me_cntro_cost_auxiliarDestino_cdgo]--97\n" +
                                                                "			,cca_destino.[cca_cdgo]--98\n" +
                                                                "			,cca_destino.[cca_cntro_cost_subcentro_cdgo]--99\n" +
                                                                "				,ccs_destino.[ccs_cdgo]--100\n" +
                                                                "				,ccs_destino.[ccs_desc]--101\n" +
                                                                "				,ccs_destino.[ccs_cntro_oper_cdgo]--102\n" +
                                                                "				,ccs_destino.[ccs_cntro_cost_rquiere_labor_realizda]--103\n" +
                                                                "			,cca_destino.[cca_desc]--104\n" +
                                                                "      ,[me_cntro_cost_mayor_cdgo]--105\n" +
                                                                "		  ,[ccm_cdgo]--106\n" +
                                                                "		  ,[ccm_desc]--107\n" +
                                                                "		  ,[te_cdgo] --108\n" +
                                                                "		  ,[te_desc]--109\n" +
                                                                "		  ,[ae_equipo_pertenencia_cdgo]--110\n" +
                                                                "				,[ep_cdgo]--111\n" +
                                                                "				,[ep_desc]--112\n" +
                                                                "				,[ep_estad]--113\n" +
                                                                "				,datename (MONTH ,[me_fecha])--114\n" +
                                                                "      ,me_cntro_cost_base_datos.[bd_cdgo]   --115 \n" +
                                                                "      ,me_cliente_base_datos.[bd_cdgo]   --116 \n" +
                                                                "      ,me_articulo_base_datos.[bd_cdgo]   --117 \n" +
                                                                "      ,me_motonave_base_datos.[bd_cdgo]   --118 \n" +
                                                                "      ,me_cntro_cost_mayor_base_datos.[bd_cdgo]  --119 \n" +
                                                                "  FROM ["+DB+"].[dbo].[mvto_equipo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [me_asignacion_equipo_cdgo]=[ae_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [me_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_oper] ON [me_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_origen ON  [me_cntro_cost_auxiliar_cdgo]=cca_origen.[cca_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_origen ON cca_origen.[cca_cntro_cost_subcentro_cdgo]=ccs_origen.[ccs_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cntro_cost] ON [me_cntro_cost_cdgo]=[cc_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost]  ON [me_cntro_cost_cdgo]=[cc_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_base_datos ON [cc_cliente_base_datos_cdgo]=me_cntro_cost_base_datos.[bd_cdgo] \n"+  
                                                                "  LEFT JOIN ["+DB+"].[dbo].[labor_realizada] ON [me_labor_realizada_cdgo]=[lr_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cliente] ON [me_cliente_cdgo]=[cl_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente]  ON [me_cliente_cdgo]=[cl_cdgo] AND [cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON [cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                                                                        //"  LEFT JOIN ["+DB+"].[dbo].[articulo] ON [me_articulo_cdgo]=[ar_cdgo]\n" +
        "		LEFT JOIN ["+DB+"].[dbo].[articulo]  ON [me_articulo_cdgo]=[ar_cdgo] AND [ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON [ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] ON [ar_tipo_articulo_cdgo]=[tar_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo]\n" +
                                                                        "		LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[me_motonave_base_datos_cdgo] \n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_motonave_base_datos ON  [mn_base_datos_cdgo]=me_motonave_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_registro ON [me_usuario_registro_cdgo]=us_registro.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_autoriza ON [me_usuario_autorizacion_cdgo]=us_autoriza.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[autorizacion_recobro] ON [me_autorizacion_recobro_cdgo]=[are_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[causa_inactividad] ON [me_causa_inactividad_cdgo]=[ci_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_inactividad ON [me_usuario_inactividad_cdgo]=us_inactividad.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[motivo_parada] ON [me_motivo_parada_cdgo]=[mpa_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_destino ON [me_cntro_cost_auxiliarDestino_cdgo]=cca_destino.[cca_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_destino ON cca_destino.[cca_cntro_cost_subcentro_cdgo]=ccs_destino.[ccs_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor] ON [me_cntro_cost_mayor_cdgo]=[ccm_cdgo]\n" +
                                                                        
"		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor]  ON [me_cntro_cost_mayor_cdgo]=[ccm_cdgo] \n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_mayor_base_datos ON [ccm_cliente_base_datos_cdgo]=me_cntro_cost_mayor_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON [ae_equipo_pertenencia_cdgo]=[ep_cdgo]"
                                                            + " WHERE  [me_fecha_hora_inicio] BETWEEN (SELECT DATEADD("+modalidad+", -"+rango+",  (SELECT SYSDATETIME()))) AND (SELECT SYSDATETIME())  \n"
                                                            + "--AND [me_inactividad]=0 AND [me_estado]=1 \n"
                                                            + " ORDER BY [me_cdgo] DESC");
            //query.setString(1, DatetimeInicio);
            //query.setString(2, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();    
            boolean validator=true;
            while(resultSet.next()){
                if(validator){
                    listadoObjetos = new ArrayList();
                    validator=false;
                }
                AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                asignacionEquipo.setCodigo(resultSet.getString(3));  
                asignacionEquipo.setFechaRegistro(resultSet.getString(10));
                asignacionEquipo.setFechaHoraInicio(resultSet.getString(11));
                asignacionEquipo.setFechaHoraFin(resultSet.getString(12));
                asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(13));
                    Equipo equipo = new Equipo();
                    equipo.setCodigo(resultSet.getString(5));
                    equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(108),resultSet.getString(109),""));
                    equipo.setMarca(resultSet.getString(7));
                    equipo.setModelo(resultSet.getString(8));
                    equipo.setDescripcion(resultSet.getString(9));
                asignacionEquipo.setEquipo(equipo);
                asignacionEquipo.setPertenencia(new Pertenencia(resultSet.getString(111),resultSet.getString(112),""));
                MvtoEquipo mvtoEquipo = new MvtoEquipo();
                    mvtoEquipo.setCodigo(resultSet.getString(1));
                    mvtoEquipo.setAsignacionEquipo(asignacionEquipo);
                    mvtoEquipo.setFechaRegistro(resultSet.getString(14));
                    mvtoEquipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(16),resultSet.getString(17),resultSet.getString(18),""));
                    mvtoEquipo.setNumeroOrden(resultSet.getString(19));
                        CentroOperacion me_co= new CentroOperacion();
                        me_co.setCodigo(Integer.parseInt(resultSet.getString(21)));
                        me_co.setDescripcion(resultSet.getString(22));
                    mvtoEquipo.setCentroOperacion(me_co);
                        CentroCostoSubCentro centroCostoSubCentro_mvtoEquipo = new CentroCostoSubCentro();
                        centroCostoSubCentro_mvtoEquipo.setCodigo(resultSet.getInt(26));
                        centroCostoSubCentro_mvtoEquipo.setDescripcion(resultSet.getString(27));
                        centroCostoSubCentro_mvtoEquipo.setCentroCostoRequiereLaborRealizada(resultSet.getString(29));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipo = new CentroCostoAuxiliar();
                        centroCostoAuxiliar_mvtoEquipo.setCodigo(resultSet.getInt(24));
                        centroCostoAuxiliar_mvtoEquipo.setDescripcion(resultSet.getString(30));
                        centroCostoAuxiliar_mvtoEquipo.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipo);
                    mvtoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipo);
                        CentroCosto centroCosto = new CentroCosto();
                        centroCosto.setCodigo(resultSet.getString(32));
                        centroCosto.setDescripción(resultSet.getString(33));
                        centroCosto.setClienteBaseDatos(resultSet.getString(115));
                    mvtoEquipo.setCentroCosto(centroCosto);
                        LaborRealizada laborRealizadaT = new LaborRealizada();
                        laborRealizadaT.setCodigo(resultSet.getString(35));
                        laborRealizadaT.setDescripcion(resultSet.getString(36));
                        laborRealizadaT.setEs_operativa(resultSet.getString(37));
                        laborRealizadaT.setEs_parada(resultSet.getString(38));
                    mvtoEquipo.setLaborRealizada(laborRealizadaT);
                    mvtoEquipo.setCliente(new Cliente(resultSet.getString(40),resultSet.getString(41),""));
                    mvtoEquipo.getCliente().setBaseDatos(new BaseDatos(resultSet.getString(116)));
                        TipoArticulo tipoArticulo = new TipoArticulo();
                                tipoArticulo.setCodigo(resultSet.getString(45));
                                tipoArticulo.setDescripcion(resultSet.getString(46));
                                tipoArticulo.setCodigoERP(resultSet.getString(47));
                                tipoArticulo.setUnidadNegocio(resultSet.getString(48));
                        Articulo articulo = new Articulo();
                        articulo.setCodigo(resultSet.getString(43));
                        articulo.setDescripcion(resultSet.getString(49));
                        articulo.setTipoArticulo(tipoArticulo);
                        articulo.setBaseDatos(new BaseDatos( resultSet.getString(117)));
                    mvtoEquipo.setArticulo(articulo);
                    Motonave motonave = new Motonave();
                    motonave.setCodigo(resultSet.getString(51));
                    motonave.setDescripcion(resultSet.getString(52));
                    motonave.setBaseDatos(new BaseDatos( resultSet.getString(118)));
                    mvtoEquipo.setMotonave(motonave);
                    mvtoEquipo.setFechaHoraInicio(resultSet.getString(53));
                mvtoEquipo.setFechaHoraFin(resultSet.getString(54));
                mvtoEquipo.setTotalMinutos(resultSet.getString(55));
                mvtoEquipo.setValorHora(resultSet.getString(56));
                mvtoEquipo.setCostoTotal(resultSet.getString(57));
                        Recobro recobro = new Recobro();
                        recobro.setCodigo(resultSet.getString(59));
                        recobro.setDescripcion(resultSet.getString(60));
                mvtoEquipo.setRecobro(recobro);
                        Usuario usuario_me_registra = new Usuario();
                        usuario_me_registra.setCodigo(resultSet.getString(65));
                        usuario_me_registra.setNombres(resultSet.getString(66));
                        usuario_me_registra.setApellidos(resultSet.getString(67));
                        usuario_me_registra.setCorreo(resultSet.getString(68));
                    mvtoEquipo.setUsuarioQuieRegistra(usuario_me_registra);
                        Usuario usuario_me_autoriza = new Usuario();
                        usuario_me_autoriza.setCodigo(resultSet.getString(70));
                        usuario_me_autoriza.setNombres(resultSet.getString(71));
                        usuario_me_autoriza.setApellidos(resultSet.getString(72));
                        usuario_me_autoriza.setCorreo(resultSet.getString(73));
                    mvtoEquipo.setUsuarioAutorizaRecobro(usuario_me_autoriza);
                        AutorizacionRecobro autorizacionRecobro = new AutorizacionRecobro();
                        autorizacionRecobro.setCodigo(resultSet.getString(75));
                        autorizacionRecobro.setDescripcion(resultSet.getString(76));
                        autorizacionRecobro.setEstado(resultSet.getString(77));
                    mvtoEquipo.setAutorizacionRecobro(autorizacionRecobro);
                    mvtoEquipo.setObservacionAutorizacion(resultSet.getString(78));
                    mvtoEquipo.setInactividad(resultSet.getString(79));
                        CausaInactividad causaInactividad = new CausaInactividad();
                        causaInactividad.setCodigo(resultSet.getString(81));
                        causaInactividad.setDescripcion(resultSet.getString(82));
                        causaInactividad.setEstado(resultSet.getString(83));
                    mvtoEquipo.setCausaInactividad(causaInactividad);
                        Usuario usuario_me_us_inactividad = new Usuario();
                        usuario_me_us_inactividad.setCodigo(resultSet.getString(85));
                        usuario_me_us_inactividad.setNombres(resultSet.getString(86));
                        usuario_me_us_inactividad.setApellidos(resultSet.getString(87));
                        usuario_me_us_inactividad.setCorreo(resultSet.getString(88));
                    mvtoEquipo.setMotivoParadaEstado(resultSet.getString(89));
                    mvtoEquipo.setUsuarioInactividad(usuario_me_us_inactividad);
                        MotivoParada motivoParada= new MotivoParada();
                        motivoParada.setCodigo(resultSet.getString(91));
                        motivoParada.setDescripcion(resultSet.getString(92));
                    mvtoEquipo.setMotivoParada(motivoParada);
                    mvtoEquipo.setObservacionMvtoEquipo(resultSet.getString(93));
                    mvtoEquipo.setEstado(resultSet.getString(94));
                    mvtoEquipo.setDesdeCarbon(resultSet.getString(95));
                    mvtoEquipo.setCentroCostoDescripción(resultSet.getString(96));
                    CentroCostoSubCentro centroCostoSubCentro_mvtoEquipoDestino = new CentroCostoSubCentro();
                        centroCostoSubCentro_mvtoEquipoDestino.setCodigo(resultSet.getInt(100));
                        centroCostoSubCentro_mvtoEquipoDestino.setDescripcion(resultSet.getString(101));
                        centroCostoSubCentro_mvtoEquipoDestino.setCentroCostoRequiereLaborRealizada(resultSet.getString(103));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipoDestino = new CentroCostoAuxiliar();
                        centroCostoAuxiliar_mvtoEquipoDestino.setCodigo(resultSet.getInt(98));
                        centroCostoAuxiliar_mvtoEquipoDestino.setDescripcion(resultSet.getString(104));
                        centroCostoAuxiliar_mvtoEquipoDestino.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipoDestino);
                    mvtoEquipo.setCentroCostoAuxiliarDestino(centroCostoAuxiliar_mvtoEquipoDestino);
                        CentroCostoMayor centroCostoMayor = new CentroCostoMayor();
                        centroCostoMayor.setCodigo(resultSet.getString(106));
                        centroCostoMayor.setDescripcion(resultSet.getString(107));
                        centroCostoMayor.setClienteBaseDatos(resultSet.getString(119));
                    mvtoEquipo.setCentroCostoMayor(centroCostoMayor);
                    mvtoEquipo.setMes(resultSet.getString(114));
                    try{
                        TarifaEquipo tarifa=new ControlDB_Equipo(tipoConexion).buscarTarifaEquipoPorAño(mvtoEquipo.getAsignacionEquipo().getEquipo().getCodigo(),mvtoEquipo.getFechaHoraInicio());
                        if(mvtoEquipo.getFechaHoraFin() != null){
                            if(tarifa != null){
                                mvtoEquipo.setValorHora(tarifa.getValorHoraOperacion());
                                try{
                                    double des=Double.parseDouble(mvtoEquipo.getTotalMinutos());
                                    double totalHoras = Double.parseDouble(""+(des/60));
                                    double valorHora =Double.parseDouble(tarifa.getValorHoraOperacion());
                                    //mvtoEquipo.setCostoTotal(""+(totalHoras * valorHora ));  
                                    DecimalFormat formato2 = new DecimalFormat("0.00");
                                    mvtoEquipo.setCostoTotal(formato2.format((totalHoras * valorHora )));
                                    //mvtoEquipo.setCostoTotalRecobroCliente(formato2.format((totalHoras * valorHora )));
                                    mvtoEquipo.setCostoTotalRecobroCliente("0");
                                }catch(Exception e){
                                    //mvto_listEquipo.getMvtoEquipo().setCostoTotal("NO PUDE");
                                }
                            }
                        }  
                    }catch(Exception e){
                        System.out.println("Error al procesar tarifa");
                    }
                listadoObjetos.add(mvtoEquipo);
            }
        }catch (SQLException sqlException) {
            System.out.println("Error al tratar al consultar los Movimientos de Carbon");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public ArrayList<MvtoEquipo>                    buscar_MvtoConRecobros(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<MvtoEquipo> listadoObjetos = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  [me_cdgo]--1\n" +
                                                                "      ,[me_asignacion_equipo_cdgo]--2\n" +
                                                                "			,[ae_cdgo]--3\n" +
                                                                "			,[ae_equipo_cdgo]--4\n" +
                                                                "				,[eq_cdgo]--5\n" +
                                                                "				,[eq_tipo_equipo_cdgo]--6\n" +
                                                                "				,[eq_marca]--7\n" +
                                                                "				,[eq_modelo]--8\n" +
                                                                "				,[eq_desc]	--9\n" +
                                                                "			,[ae_fecha_registro]--10\n" +
                                                                "			,[ae_fecha_hora_inicio]--11\n" +
                                                                "			,[ae_fecha_hora_fin]--12\n" +
                                                                "			,[ae_cant_minutos]--13\n" +
                                                                "      ,[me_fecha]--14\n" +
                                                                "      ,[me_proveedor_equipo_cdgo]--15\n" +
                                                                "			,[pe_cdgo]--16\n" +
                                                                "			,[pe_nit]--17\n" +
                                                                "			,[pe_desc]--18\n" +
                                                                "      ,[me_num_orden]--19\n" +
                                                                "      ,[me_cntro_oper_cdgo]--20\n" +
                                                                "		  ,[co_cdgo]--21\n" +
                                                                "		  ,[co_desc]--22\n" +
                                                                "      ,[me_cntro_cost_auxiliar_cdgo]--23\n" +
                                                                "		  ,cca_origen.[cca_cdgo]--24\n" +
                                                                "		  ,cca_origen.[cca_cntro_cost_subcentro_cdgo]--25\n" +
                                                                "			,ccs_origen.[ccs_cdgo]--26\n" +
                                                                "			,ccs_origen.[ccs_desc]--27\n" +
                                                                "			,ccs_origen.[ccs_cntro_oper_cdgo]--28\n" +
                                                                "			,ccs_origen.[ccs_cntro_cost_rquiere_labor_realizda]--29\n" +
                                                                "		  ,cca_origen.[cca_desc]--30\n" +
                                                                "      ,[me_cntro_cost_cdgo]--31\n" +
                                                                "		  ,[cc_cdgo]--32\n" +
                                                                "		  ,[cc_descripcion]--33\n" +
                                                                "      ,[me_labor_realizada_cdgo]--34\n" +
                                                                "		  ,[lr_cdgo]--35\n" +
                                                                "		  ,[lr_desc]--36\n" +
                                                                "		  ,[lr_operativa]--37\n" +
                                                                "		  ,[lr_parada]--38\n" +
                                                                "      ,[me_cliente_cdgo]--39\n" +
                                                                "		  ,[cl_cdgo]--40\n" +
                                                                "		  ,[cl_desc]--41\n" +
                                                                "      ,[me_articulo_cdgo]--42\n" +
                                                                "		  ,[ar_cdgo]--43\n" +
                                                                "		  ,[ar_tipo_articulo_cdgo]--44\n" +
                                                                "			  ,[tar_cdgo]--45\n" +
                                                                "			  ,[tar_desc]--46\n" +
                                                                "			  ,[tar_cdgo_erp]--47\n" +
                                                                "			  ,[tar_undad_ngcio]--48\n" +
                                                                "		  ,[ar_desc]--49\n" +
                                                                "      ,[me_motonave_cdgo]--50\n" +
                                                                "		  ,[mn_cdgo]--51\n" +
                                                                "		  ,[mn_desc]--52\n" +
                                                                "      ,[me_fecha_hora_inicio]--53\n" +
                                                                "      ,[me_fecha_hora_fin]--54\n" +
                                                                "      ,[me_total_minutos]--55\n" +
                                                                "      ,[me_valor_hora]--56\n" +
                                                                "      ,[me_costo_total]--57\n" +
                                                                "      ,[me_recobro_cdgo]--58\n" +
                                                                "		  ,[rc_cdgo]--59\n" +
                                                                "		  ,[rc_desc]--60\n" +
                                                                "		  ,[rc_estad]--61\n" +
                                                                "      ,[me_cliente_recobro_cdgo]--62\n" +
                                                                "      ,[me_costo_total_recobro_cliente]--63\n" +
                                                                "      ,[me_usuario_registro_cdgo]--64\n" +
                                                                "			,us_registro.[us_cdgo]--65\n" +
                                                                "			,us_registro.[us_nombres]--66\n" +
                                                                "			,us_registro.[us_apellidos]--67\n" +
                                                                "			,us_registro.[us_correo]--68\n" +
                                                                "      ,[me_usuario_autorizacion_cdgo]--69\n" +
                                                                "			,us_autoriza.[us_cdgo]--70\n" +
                                                                "			,us_autoriza.[us_nombres]--71\n" +
                                                                "			,us_autoriza.[us_apellidos]--72\n" +
                                                                "			,us_autoriza.[us_correo]--73\n" +
                                                                "      ,[me_autorizacion_recobro_cdgo]--74\n" +
                                                                "			,[are_cdgo]--75\n" +
                                                                "			,[are_desc]--76\n" +
                                                                "			,[are_estad]--77\n" +
                                                                "      ,[me_observ_autorizacion]--78\n" +
                                                                "      ,[me_inactividad]--79\n" +
                                                                "      ,[me_causa_inactividad_cdgo]--80\n" +
                                                                "		  ,[ci_cdgo]--81\n" +
                                                                "		  ,[ci_desc]--82\n" +
                                                                "		  ,[ci_estad]--83\n" +
                                                                "      ,[me_usuario_inactividad_cdgo]--84\n" +
                                                                "		  ,us_inactividad.[us_cdgo]--85\n" +
                                                                "		  ,us_inactividad.[us_nombres]--86\n" +
                                                                "		  ,us_inactividad.[us_apellidos]--87\n" +
                                                                "		  ,us_inactividad.[us_correo]--88\n" +
                                                                "      ,[me_motivo_parada_estado]--89\n" +
                                                                "      ,[me_motivo_parada_cdgo]--90\n" +
                                                                "		  ,[mpa_cdgo]--91\n" +
                                                                "		  ,[mpa_desc]--92\n" +
                                                                "      ,[me_observ]--93\n" +
                                                                "      ,CASE [me_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_estado]--94\n" +
                                                                "      ,CASE [me_desde_mvto_carbon] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [me_desde_mvto_carbon]--95\n" +
                                                                "      ,[me_cntro_cost]--96\n" +
                                                                "      ,[me_cntro_cost_auxiliarDestino_cdgo]--97\n" +
                                                                "			,cca_destino.[cca_cdgo]--98\n" +
                                                                "			,cca_destino.[cca_cntro_cost_subcentro_cdgo]--99\n" +
                                                                "				,ccs_destino.[ccs_cdgo]--100\n" +
                                                                "				,ccs_destino.[ccs_desc]--101\n" +
                                                                "				,ccs_destino.[ccs_cntro_oper_cdgo]--102\n" +
                                                                "				,ccs_destino.[ccs_cntro_cost_rquiere_labor_realizda]--103\n" +
                                                                "			,cca_destino.[cca_desc]--104\n" +
                                                                "      ,[me_cntro_cost_mayor_cdgo]--105\n" +
                                                                "		  ,[ccm_cdgo]--106\n" +
                                                                "		  ,[ccm_desc]--107\n" +
                                                                "		  ,[te_cdgo] --108\n" +
                                                                "		  ,[te_desc]--109\n" +
                                                                "		  ,[ae_equipo_pertenencia_cdgo]--110\n" +
                                                                "				,[ep_cdgo]--111\n" +
                                                                "				,[ep_desc]--112\n" +
                                                                "				,[ep_estad]--113\n" +
                                                                "				,datename (MONTH ,[me_fecha])--114\n" +
                                                                "      ,me_cntro_cost_base_datos.[bd_cdgo]   --115 \n" +
                                                                "      ,me_cliente_base_datos.[bd_cdgo]   --116 \n" +
                                                                "      ,me_articulo_base_datos.[bd_cdgo]   --117 \n" +
                                                                "      ,me_motonave_base_datos.[bd_cdgo]   --118 \n" +
                                                                "      ,me_cntro_cost_mayor_base_datos.[bd_cdgo]  --119 \n" +
                                                                "  FROM ["+DB+"].[dbo].[mvto_equipo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [me_asignacion_equipo_cdgo]=[ae_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [me_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_oper] ON [me_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_origen ON  [me_cntro_cost_auxiliar_cdgo]=cca_origen.[cca_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_origen ON cca_origen.[cca_cntro_cost_subcentro_cdgo]=ccs_origen.[ccs_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cntro_cost] ON [me_cntro_cost_cdgo]=[cc_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost]  ON [me_cntro_cost_cdgo]=[cc_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_base_datos ON [cc_cliente_base_datos_cdgo]=me_cntro_cost_base_datos.[bd_cdgo] \n"+  
                                                                "  LEFT JOIN ["+DB+"].[dbo].[labor_realizada] ON [me_labor_realizada_cdgo]=[lr_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cliente] ON [me_cliente_cdgo]=[cl_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente]  ON [me_cliente_cdgo]=[cl_cdgo] AND [cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON [cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                                                                        //"  LEFT JOIN ["+DB+"].[dbo].[articulo] ON [me_articulo_cdgo]=[ar_cdgo]\n" +
        "		LEFT JOIN ["+DB+"].[dbo].[articulo]  ON [me_articulo_cdgo]=[ar_cdgo] AND [ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON [ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] ON [ar_tipo_articulo_cdgo]=[tar_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo]\n" +
                                                                        "		LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[me_motonave_base_datos_cdgo] \n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_motonave_base_datos ON  [mn_base_datos_cdgo]=me_motonave_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_registro ON [me_usuario_registro_cdgo]=us_registro.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_autoriza ON [me_usuario_autorizacion_cdgo]=us_autoriza.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[autorizacion_recobro] ON [me_autorizacion_recobro_cdgo]=[are_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[causa_inactividad] ON [me_causa_inactividad_cdgo]=[ci_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_inactividad ON [me_usuario_inactividad_cdgo]=us_inactividad.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[motivo_parada] ON [me_motivo_parada_cdgo]=[mpa_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_destino ON [me_cntro_cost_auxiliarDestino_cdgo]=cca_destino.[cca_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_destino ON cca_destino.[cca_cntro_cost_subcentro_cdgo]=ccs_destino.[ccs_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor] ON [me_cntro_cost_mayor_cdgo]=[ccm_cdgo]\n" +
                                                                        
"		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor]  ON [me_cntro_cost_mayor_cdgo]=[ccm_cdgo] \n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_mayor_base_datos ON [ccm_cliente_base_datos_cdgo]=me_cntro_cost_mayor_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON [ae_equipo_pertenencia_cdgo]=[ep_cdgo]"
                                                            + " WHERE  [me_fecha_hora_inicio] BETWEEN ? AND ? AND [me_inactividad]=0 AND [me_recobro_cdgo]=2 AND [me_autorizacion_recobro_cdgo] IS NULL ORDER BY [me_cdgo] DESC");
            query.setString(1, DatetimeInicio);
            query.setString(2, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();    
            boolean validator=true;
            while(resultSet.next()){
                if(validator){
                    listadoObjetos = new ArrayList();
                    validator=false;
                }
                AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                asignacionEquipo.setCodigo(resultSet.getString(3));  
                asignacionEquipo.setFechaRegistro(resultSet.getString(10));
                asignacionEquipo.setFechaHoraInicio(resultSet.getString(11));
                asignacionEquipo.setFechaHoraFin(resultSet.getString(12));
                asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(13));
                    Equipo equipo = new Equipo();
                    equipo.setCodigo(resultSet.getString(5));
                    equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(108),resultSet.getString(109),""));
                    equipo.setMarca(resultSet.getString(7));
                    equipo.setModelo(resultSet.getString(8));
                    equipo.setDescripcion(resultSet.getString(9));
                asignacionEquipo.setEquipo(equipo);
                asignacionEquipo.setPertenencia(new Pertenencia(resultSet.getString(111),resultSet.getString(112),""));
                MvtoEquipo mvtoEquipo = new MvtoEquipo();
                    mvtoEquipo.setCodigo(resultSet.getString(1));
                    mvtoEquipo.setAsignacionEquipo(asignacionEquipo);
                    mvtoEquipo.setFechaRegistro(resultSet.getString(14));
                    mvtoEquipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(16),resultSet.getString(17),resultSet.getString(18),""));
                    mvtoEquipo.setNumeroOrden(resultSet.getString(19));
                        CentroOperacion me_co= new CentroOperacion();
                        me_co.setCodigo(Integer.parseInt(resultSet.getString(21)));
                        me_co.setDescripcion(resultSet.getString(22));
                    mvtoEquipo.setCentroOperacion(me_co);
                        CentroCostoSubCentro centroCostoSubCentro_mvtoEquipo = new CentroCostoSubCentro();
                        centroCostoSubCentro_mvtoEquipo.setCodigo(resultSet.getInt(26));
                        centroCostoSubCentro_mvtoEquipo.setDescripcion(resultSet.getString(27));
                        centroCostoSubCentro_mvtoEquipo.setCentroCostoRequiereLaborRealizada(resultSet.getString(29));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipo = new CentroCostoAuxiliar();
                        centroCostoAuxiliar_mvtoEquipo.setCodigo(resultSet.getInt(24));
                        centroCostoAuxiliar_mvtoEquipo.setDescripcion(resultSet.getString(30));
                        centroCostoAuxiliar_mvtoEquipo.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipo);
                    mvtoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipo);
                        CentroCosto centroCosto = new CentroCosto();
                        centroCosto.setCodigo(resultSet.getString(32));
                        centroCosto.setDescripción(resultSet.getString(33));
                        centroCosto.setClienteBaseDatos(resultSet.getString(115));
                    mvtoEquipo.setCentroCosto(centroCosto);
                        LaborRealizada laborRealizadaT = new LaborRealizada();
                        laborRealizadaT.setCodigo(resultSet.getString(35));
                        laborRealizadaT.setDescripcion(resultSet.getString(36));
                        laborRealizadaT.setEs_operativa(resultSet.getString(37));
                        laborRealizadaT.setEs_parada(resultSet.getString(38));
                    mvtoEquipo.setLaborRealizada(laborRealizadaT);
                    mvtoEquipo.setCliente(new Cliente(resultSet.getString(40),resultSet.getString(41),""));
                    mvtoEquipo.getCliente().setBaseDatos(new BaseDatos(resultSet.getString(116)));
                        TipoArticulo tipoArticulo = new TipoArticulo();
                                tipoArticulo.setCodigo(resultSet.getString(45));
                                tipoArticulo.setDescripcion(resultSet.getString(46));
                                tipoArticulo.setCodigoERP(resultSet.getString(47));
                                tipoArticulo.setUnidadNegocio(resultSet.getString(48));
                        Articulo articulo = new Articulo();
                        articulo.setCodigo(resultSet.getString(43));
                        articulo.setDescripcion(resultSet.getString(49));
                        articulo.setTipoArticulo(tipoArticulo);
                        articulo.setBaseDatos(new BaseDatos( resultSet.getString(117)));
                    mvtoEquipo.setArticulo(articulo);
                    Motonave motonave = new Motonave();
                    motonave.setCodigo(resultSet.getString(51));
                    motonave.setDescripcion(resultSet.getString(52));
                    motonave.setBaseDatos(new BaseDatos( resultSet.getString(118)));
                    mvtoEquipo.setMotonave(motonave);
                    mvtoEquipo.setFechaHoraInicio(resultSet.getString(53));
                mvtoEquipo.setFechaHoraFin(resultSet.getString(54));
                mvtoEquipo.setTotalMinutos(resultSet.getString(55));
                mvtoEquipo.setValorHora(resultSet.getString(56));
                mvtoEquipo.setCostoTotal(resultSet.getString(57));
                        Recobro recobro = new Recobro();
                        recobro.setCodigo(resultSet.getString(59));
                        recobro.setDescripcion(resultSet.getString(60));
                mvtoEquipo.setRecobro(recobro);
                        Usuario usuario_me_registra = new Usuario();
                        usuario_me_registra.setCodigo(resultSet.getString(65));
                        usuario_me_registra.setNombres(resultSet.getString(66));
                        usuario_me_registra.setApellidos(resultSet.getString(67));
                        usuario_me_registra.setCorreo(resultSet.getString(68));
                    mvtoEquipo.setUsuarioQuieRegistra(usuario_me_registra);
                        Usuario usuario_me_autoriza = new Usuario();
                        usuario_me_autoriza.setCodigo(resultSet.getString(70));
                        usuario_me_autoriza.setNombres(resultSet.getString(71));
                        usuario_me_autoriza.setApellidos(resultSet.getString(72));
                        usuario_me_autoriza.setCorreo(resultSet.getString(73));
                    mvtoEquipo.setUsuarioAutorizaRecobro(usuario_me_autoriza);
                        AutorizacionRecobro autorizacionRecobro = new AutorizacionRecobro();
                        autorizacionRecobro.setCodigo(resultSet.getString(75));
                        autorizacionRecobro.setDescripcion(resultSet.getString(76));
                        autorizacionRecobro.setEstado(resultSet.getString(77));
                    mvtoEquipo.setAutorizacionRecobro(autorizacionRecobro);
                    mvtoEquipo.setObservacionAutorizacion(resultSet.getString(78));
                    mvtoEquipo.setInactividad(resultSet.getString(79));
                        CausaInactividad causaInactividad = new CausaInactividad();
                        causaInactividad.setCodigo(resultSet.getString(81));
                        causaInactividad.setDescripcion(resultSet.getString(82));
                        causaInactividad.setEstado(resultSet.getString(83));
                    mvtoEquipo.setCausaInactividad(causaInactividad);
                        Usuario usuario_me_us_inactividad = new Usuario();
                        usuario_me_us_inactividad.setCodigo(resultSet.getString(85));
                        usuario_me_us_inactividad.setNombres(resultSet.getString(86));
                        usuario_me_us_inactividad.setApellidos(resultSet.getString(87));
                        usuario_me_us_inactividad.setCorreo(resultSet.getString(88));
                    mvtoEquipo.setMotivoParadaEstado(resultSet.getString(89));
                    mvtoEquipo.setUsuarioInactividad(usuario_me_us_inactividad);
                        MotivoParada motivoParada= new MotivoParada();
                        motivoParada.setCodigo(resultSet.getString(91));
                        motivoParada.setDescripcion(resultSet.getString(92));
                    mvtoEquipo.setMotivoParada(motivoParada);
                    mvtoEquipo.setObservacionMvtoEquipo(resultSet.getString(93));
                    mvtoEquipo.setEstado(resultSet.getString(94));
                    mvtoEquipo.setDesdeCarbon(resultSet.getString(95));
                    mvtoEquipo.setCentroCostoDescripción(resultSet.getString(96));
                    CentroCostoSubCentro centroCostoSubCentro_mvtoEquipoDestino = new CentroCostoSubCentro();
                        centroCostoSubCentro_mvtoEquipoDestino.setCodigo(resultSet.getInt(100));
                        centroCostoSubCentro_mvtoEquipoDestino.setDescripcion(resultSet.getString(101));
                        centroCostoSubCentro_mvtoEquipoDestino.setCentroCostoRequiereLaborRealizada(resultSet.getString(103));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipoDestino = new CentroCostoAuxiliar();
                        centroCostoAuxiliar_mvtoEquipoDestino.setCodigo(resultSet.getInt(98));
                        centroCostoAuxiliar_mvtoEquipoDestino.setDescripcion(resultSet.getString(104));
                        centroCostoAuxiliar_mvtoEquipoDestino.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipoDestino);
                    mvtoEquipo.setCentroCostoAuxiliarDestino(centroCostoAuxiliar_mvtoEquipoDestino);
                        CentroCostoMayor centroCostoMayor = new CentroCostoMayor();
                        centroCostoMayor.setCodigo(resultSet.getString(106));
                        centroCostoMayor.setDescripcion(resultSet.getString(107));
                        centroCostoMayor.setClienteBaseDatos(resultSet.getString(119));
                    mvtoEquipo.setCentroCostoMayor(centroCostoMayor);
                    mvtoEquipo.setMes(resultSet.getString(114));
                    /*try{
                        TarifaEquipo tarifa=new ControlDB_Equipo(tipoConexion).buscarTarifaEquipoPorAño(mvtoEquipo.getAsignacionEquipo().getEquipo().getCodigo(),mvtoEquipo.getFechaHoraInicio());
                        if(mvtoEquipo.getFechaHoraFin() != null){
                            if(tarifa != null){
                                mvtoEquipo.setValorHora(tarifa.getValorHoraOperacion());
                                try{
                                    double des=Double.parseDouble(mvtoEquipo.getTotalMinutos());
                                    double totalHoras = Double.parseDouble(""+(des/60));
                                    double valorHora =Double.parseDouble(tarifa.getValorHoraOperacion());
                                    //mvtoEquipo.setCostoTotal(""+(totalHoras * valorHora ));  
                                    DecimalFormat formato2 = new DecimalFormat("0.00");
                                    mvtoEquipo.setCostoTotal(formato2.format((totalHoras * valorHora )));
                                    //mvtoEquipo.setCostoTotalRecobroCliente(formato2.format((totalHoras * valorHora )));
                                    mvtoEquipo.setCostoTotalRecobroCliente("0");
                                }catch(Exception e){
                                    //mvto_listEquipo.getMvtoEquipo().setCostoTotal("NO PUDE");
                                }
                            }
                        }  
                    }catch(Exception e){
                        System.out.println("Error al procesar tarifa");
                    }*/
                listadoObjetos.add(mvtoEquipo);
            }
        }catch (SQLException sqlException) {
            System.out.println("Error al tratar al consultar los Movimientos de Carbon");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    
    public ArrayList<MvtoEquipo>                    buscar_MvtoEquipoInactivos(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<MvtoEquipo> listadoObjetos = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  [me_cdgo]--1\n" +
                                                                "      ,[me_asignacion_equipo_cdgo]--2\n" +
                                                                "			,[ae_cdgo]--3\n" +
                                                                "			,[ae_equipo_cdgo]--4\n" +
                                                                "				,[eq_cdgo]--5\n" +
                                                                "				,[eq_tipo_equipo_cdgo]--6\n" +
                                                                "				,[eq_marca]--7\n" +
                                                                "				,[eq_modelo]--8\n" +
                                                                "				,[eq_desc]	--9\n" +
                                                                "			,[ae_fecha_registro]--10\n" +
                                                                "			,[ae_fecha_hora_inicio]--11\n" +
                                                                "			,[ae_fecha_hora_fin]--12\n" +
                                                                "			,[ae_cant_minutos]--13\n" +
                                                                "      ,[me_fecha]--14\n" +
                                                                "      ,[me_proveedor_equipo_cdgo]--15\n" +
                                                                "			,[pe_cdgo]--16\n" +
                                                                "			,[pe_nit]--17\n" +
                                                                "			,[pe_desc]--18\n" +
                                                                "      ,[me_num_orden]--19\n" +
                                                                "      ,[me_cntro_oper_cdgo]--20\n" +
                                                                "		  ,[co_cdgo]--21\n" +
                                                                "		  ,[co_desc]--22\n" +
                                                                "      ,[me_cntro_cost_auxiliar_cdgo]--23\n" +
                                                                "		  ,cca_origen.[cca_cdgo]--24\n" +
                                                                "		  ,cca_origen.[cca_cntro_cost_subcentro_cdgo]--25\n" +
                                                                "			,ccs_origen.[ccs_cdgo]--26\n" +
                                                                "			,ccs_origen.[ccs_desc]--27\n" +
                                                                "			,ccs_origen.[ccs_cntro_oper_cdgo]--28\n" +
                                                                "			,ccs_origen.[ccs_cntro_cost_rquiere_labor_realizda]--29\n" +
                                                                "		  ,cca_origen.[cca_desc]--30\n" +
                                                                "      ,[me_cntro_cost_cdgo]--31\n" +
                                                                "		  ,[cc_cdgo]--32\n" +
                                                                "		  ,[cc_descripcion]--33\n" +
                                                                "      ,[me_labor_realizada_cdgo]--34\n" +
                                                                "		  ,[lr_cdgo]--35\n" +
                                                                "		  ,[lr_desc]--36\n" +
                                                                "		  ,[lr_operativa]--37\n" +
                                                                "		  ,[lr_parada]--38\n" +
                                                                "      ,[me_cliente_cdgo]--39\n" +
                                                                "		  ,[cl_cdgo]--40\n" +
                                                                "		  ,[cl_desc]--41\n" +
                                                                "      ,[me_articulo_cdgo]--42\n" +
                                                                "		  ,[ar_cdgo]--43\n" +
                                                                "		  ,[ar_tipo_articulo_cdgo]--44\n" +
                                                                "			  ,[tar_cdgo]--45\n" +
                                                                "			  ,[tar_desc]--46\n" +
                                                                "			  ,[tar_cdgo_erp]--47\n" +
                                                                "			  ,[tar_undad_ngcio]--48\n" +
                                                                "		  ,[ar_desc]--49\n" +
                                                                "      ,[me_motonave_cdgo]--50\n" +
                                                                "		  ,[mn_cdgo]--51\n" +
                                                                "		  ,[mn_desc]--52\n" +
                                                                "      ,[me_fecha_hora_inicio]--53\n" +
                                                                "      ,[me_fecha_hora_fin]--54\n" +
                                                                "      ,[me_total_minutos]--55\n" +
                                                                "      ,[me_valor_hora]--56\n" +
                                                                "      ,[me_costo_total]--57\n" +
                                                                "      ,[me_recobro_cdgo]--58\n" +
                                                                "		  ,[rc_cdgo]--59\n" +
                                                                "		  ,[rc_desc]--60\n" +
                                                                "		  ,[rc_estad]--61\n" +
                                                                "      ,[me_cliente_recobro_cdgo]--62\n" +
                                                                "      ,[me_costo_total_recobro_cliente]--63\n" +
                                                                "      ,[me_usuario_registro_cdgo]--64\n" +
                                                                "			,us_registro.[us_cdgo]--65\n" +
                                                                "			,us_registro.[us_nombres]--66\n" +
                                                                "			,us_registro.[us_apellidos]--67\n" +
                                                                "			,us_registro.[us_correo]--68\n" +
                                                                "      ,[me_usuario_autorizacion_cdgo]--69\n" +
                                                                "			,us_autoriza.[us_cdgo]--70\n" +
                                                                "			,us_autoriza.[us_nombres]--71\n" +
                                                                "			,us_autoriza.[us_apellidos]--72\n" +
                                                                "			,us_autoriza.[us_correo]--73\n" +
                                                                "      ,[me_autorizacion_recobro_cdgo]--74\n" +
                                                                "			,[are_cdgo]--75\n" +
                                                                "			,[are_desc]--76\n" +
                                                                "			,[are_estad]--77\n" +
                                                                "      ,[me_observ_autorizacion]--78\n" +
                                                                "      ,[me_inactividad]--79\n" +
                                                                "      ,[me_causa_inactividad_cdgo]--80\n" +
                                                                "		  ,[ci_cdgo]--81\n" +
                                                                "		  ,[ci_desc]--82\n" +
                                                                "		  ,[ci_estad]--83\n" +
                                                                "      ,[me_usuario_inactividad_cdgo]--84\n" +
                                                                "		  ,us_inactividad.[us_cdgo]--85\n" +
                                                                "		  ,us_inactividad.[us_nombres]--86\n" +
                                                                "		  ,us_inactividad.[us_apellidos]--87\n" +
                                                                "		  ,us_inactividad.[us_correo]--88\n" +
                                                                "      ,[me_motivo_parada_estado]--89\n" +
                                                                "      ,[me_motivo_parada_cdgo]--90\n" +
                                                                "		  ,[mpa_cdgo]--91\n" +
                                                                "		  ,[mpa_desc]--92\n" +
                                                                "      ,[me_observ]--93\n" +
                                                                "      ,CASE [me_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_estado]--94\n" +
                                                                "      ,CASE [me_desde_mvto_carbon] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [me_desde_mvto_carbon]--95\n" +
                                                                "      ,[me_cntro_cost]--96\n" +
                                                                "      ,[me_cntro_cost_auxiliarDestino_cdgo]--97\n" +
                                                                "			,cca_destino.[cca_cdgo]--98\n" +
                                                                "			,cca_destino.[cca_cntro_cost_subcentro_cdgo]--99\n" +
                                                                "				,ccs_destino.[ccs_cdgo]--100\n" +
                                                                "				,ccs_destino.[ccs_desc]--101\n" +
                                                                "				,ccs_destino.[ccs_cntro_oper_cdgo]--102\n" +
                                                                "				,ccs_destino.[ccs_cntro_cost_rquiere_labor_realizda]--103\n" +
                                                                "			,cca_destino.[cca_desc]--104\n" +
                                                                "      ,[me_cntro_cost_mayor_cdgo]--105\n" +
                                                                "		  ,[ccm_cdgo]--106\n" +
                                                                "		  ,[ccm_desc]--107\n" +
                                                                "		  ,[te_cdgo] --108\n" +
                                                                "		  ,[te_desc]--109\n" +
                                                                "		  ,[ae_equipo_pertenencia_cdgo]--110\n" +
                                                                "				,[ep_cdgo]--111\n" +
                                                                "				,[ep_desc]--112\n" +
                                                                "				,[ep_estad]--113\n" +
                                                                "				,datename (MONTH ,[me_fecha])--114\n" +
                                                                "      ,me_cntro_cost_base_datos.[bd_cdgo]   --115 \n" +
                                                                "      ,me_cliente_base_datos.[bd_cdgo]   --116 \n" +
                                                                "      ,me_articulo_base_datos.[bd_cdgo]   --117 \n" +
                                                                "      ,me_motonave_base_datos.[bd_cdgo]   --118 \n" +
                                                                "      ,me_cntro_cost_mayor_base_datos.[bd_cdgo]  --119 \n" +
                                                                "  FROM ["+DB+"].[dbo].[mvto_equipo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [me_asignacion_equipo_cdgo]=[ae_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [me_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_oper] ON [me_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_origen ON  [me_cntro_cost_auxiliar_cdgo]=cca_origen.[cca_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_origen ON cca_origen.[cca_cntro_cost_subcentro_cdgo]=ccs_origen.[ccs_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cntro_cost] ON [me_cntro_cost_cdgo]=[cc_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost]  ON [me_cntro_cost_cdgo]=[cc_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_base_datos ON [cc_cliente_base_datos_cdgo]=me_cntro_cost_base_datos.[bd_cdgo] \n"+  
                                                                "  LEFT JOIN ["+DB+"].[dbo].[labor_realizada] ON [me_labor_realizada_cdgo]=[lr_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cliente] ON [me_cliente_cdgo]=[cl_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente]  ON [me_cliente_cdgo]=[cl_cdgo] AND [cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON [cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                                                                        //"  LEFT JOIN ["+DB+"].[dbo].[articulo] ON [me_articulo_cdgo]=[ar_cdgo]\n" +
        "		LEFT JOIN ["+DB+"].[dbo].[articulo]  ON [me_articulo_cdgo]=[ar_cdgo] AND [ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON [ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] ON [ar_tipo_articulo_cdgo]=[tar_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo]\n" +
                                                                        "		LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[me_motonave_base_datos_cdgo] \n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_motonave_base_datos ON  [mn_base_datos_cdgo]=me_motonave_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_registro ON [me_usuario_registro_cdgo]=us_registro.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_autoriza ON [me_usuario_autorizacion_cdgo]=us_autoriza.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[autorizacion_recobro] ON [me_autorizacion_recobro_cdgo]=[are_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[causa_inactividad] ON [me_causa_inactividad_cdgo]=[ci_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_inactividad ON [me_usuario_inactividad_cdgo]=us_inactividad.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[motivo_parada] ON [me_motivo_parada_cdgo]=[mpa_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_destino ON [me_cntro_cost_auxiliarDestino_cdgo]=cca_destino.[cca_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_destino ON cca_destino.[cca_cntro_cost_subcentro_cdgo]=ccs_destino.[ccs_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor] ON [me_cntro_cost_mayor_cdgo]=[ccm_cdgo]\n" +
                                                                        
"		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor]  ON [me_cntro_cost_mayor_cdgo]=[ccm_cdgo] \n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_mayor_base_datos ON [ccm_cliente_base_datos_cdgo]=me_cntro_cost_mayor_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON [ae_equipo_pertenencia_cdgo]=[ep_cdgo]"
                                                            + " WHERE [me_fecha_hora_inicio] BETWEEN ? AND ? AND [me_inactividad]=1 AND [me_estado]=0 ORDER BY [me_cdgo] DESC");
            query.setString(1, DatetimeInicio);
            query.setString(2, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();    
            boolean validator=true;
            while(resultSet.next()){
                if(validator){
                    listadoObjetos = new ArrayList();
                    validator=false;
                }
                AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                asignacionEquipo.setCodigo(resultSet.getString(3));  
                asignacionEquipo.setFechaRegistro(resultSet.getString(10));
                asignacionEquipo.setFechaHoraInicio(resultSet.getString(11));
                asignacionEquipo.setFechaHoraFin(resultSet.getString(12));
                asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(13));
                    Equipo equipo = new Equipo();
                    equipo.setCodigo(resultSet.getString(5));
                    equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(108),resultSet.getString(109),""));
                    equipo.setMarca(resultSet.getString(7));
                    equipo.setModelo(resultSet.getString(8));
                    equipo.setDescripcion(resultSet.getString(9));
                asignacionEquipo.setEquipo(equipo);
                asignacionEquipo.setPertenencia(new Pertenencia(resultSet.getString(111),resultSet.getString(112),""));
                MvtoEquipo mvtoEquipo = new MvtoEquipo();
                    mvtoEquipo.setCodigo(resultSet.getString(1));
                    mvtoEquipo.setAsignacionEquipo(asignacionEquipo);
                    mvtoEquipo.setFechaRegistro(resultSet.getString(14));
                    mvtoEquipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(16),resultSet.getString(17),resultSet.getString(18),""));
                    mvtoEquipo.setNumeroOrden(resultSet.getString(19));
                        CentroOperacion me_co= new CentroOperacion();
                        me_co.setCodigo(Integer.parseInt(resultSet.getString(21)));
                        me_co.setDescripcion(resultSet.getString(22));
                    mvtoEquipo.setCentroOperacion(me_co);
                        CentroCostoSubCentro centroCostoSubCentro_mvtoEquipo = new CentroCostoSubCentro();
                        centroCostoSubCentro_mvtoEquipo.setCodigo(resultSet.getInt(26));
                        centroCostoSubCentro_mvtoEquipo.setDescripcion(resultSet.getString(27));
                        centroCostoSubCentro_mvtoEquipo.setCentroCostoRequiereLaborRealizada(resultSet.getString(29));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipo = new CentroCostoAuxiliar();
                        centroCostoAuxiliar_mvtoEquipo.setCodigo(resultSet.getInt(24));
                        centroCostoAuxiliar_mvtoEquipo.setDescripcion(resultSet.getString(30));
                        centroCostoAuxiliar_mvtoEquipo.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipo);
                    mvtoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipo);
                        CentroCosto centroCosto = new CentroCosto();
                        centroCosto.setCodigo(resultSet.getString(32));
                        centroCosto.setDescripción(resultSet.getString(33));
                        centroCosto.setClienteBaseDatos(resultSet.getString(115));
                    mvtoEquipo.setCentroCosto(centroCosto);
                        LaborRealizada laborRealizadaT = new LaborRealizada();
                        laborRealizadaT.setCodigo(resultSet.getString(35));
                        laborRealizadaT.setDescripcion(resultSet.getString(36));
                        laborRealizadaT.setEs_operativa(resultSet.getString(37));
                        laborRealizadaT.setEs_parada(resultSet.getString(38));
                    mvtoEquipo.setLaborRealizada(laborRealizadaT);
                    mvtoEquipo.setCliente(new Cliente(resultSet.getString(40),resultSet.getString(41),""));
                    mvtoEquipo.getCliente().setBaseDatos(new BaseDatos(resultSet.getString(116)));
                        TipoArticulo tipoArticulo = new TipoArticulo();
                                tipoArticulo.setCodigo(resultSet.getString(45));
                                tipoArticulo.setDescripcion(resultSet.getString(46));
                                tipoArticulo.setCodigoERP(resultSet.getString(47));
                                tipoArticulo.setUnidadNegocio(resultSet.getString(48));
                        Articulo articulo = new Articulo();
                        articulo.setCodigo(resultSet.getString(43));
                        articulo.setDescripcion(resultSet.getString(49));
                        articulo.setTipoArticulo(tipoArticulo);
                        articulo.setBaseDatos(new BaseDatos( resultSet.getString(117)));
                    mvtoEquipo.setArticulo(articulo);
                    Motonave motonave = new Motonave();
                    motonave.setCodigo(resultSet.getString(51));
                    motonave.setDescripcion(resultSet.getString(52));
                    motonave.setBaseDatos(new BaseDatos( resultSet.getString(118)));
                    mvtoEquipo.setMotonave(motonave);
                    mvtoEquipo.setFechaHoraInicio(resultSet.getString(53));
                mvtoEquipo.setFechaHoraFin(resultSet.getString(54));
                mvtoEquipo.setTotalMinutos(resultSet.getString(55));
                mvtoEquipo.setValorHora(resultSet.getString(56));
                mvtoEquipo.setCostoTotal(resultSet.getString(57));
                        Recobro recobro = new Recobro();
                        recobro.setCodigo(resultSet.getString(59));
                        recobro.setDescripcion(resultSet.getString(60));
                mvtoEquipo.setRecobro(recobro);
                        Usuario usuario_me_registra = new Usuario();
                        usuario_me_registra.setCodigo(resultSet.getString(65));
                        usuario_me_registra.setNombres(resultSet.getString(66));
                        usuario_me_registra.setApellidos(resultSet.getString(67));
                        usuario_me_registra.setCorreo(resultSet.getString(68));
                    mvtoEquipo.setUsuarioQuieRegistra(usuario_me_registra);
                        Usuario usuario_me_autoriza = new Usuario();
                        usuario_me_autoriza.setCodigo(resultSet.getString(70));
                        usuario_me_autoriza.setNombres(resultSet.getString(71));
                        usuario_me_autoriza.setApellidos(resultSet.getString(72));
                        usuario_me_autoriza.setCorreo(resultSet.getString(73));
                    mvtoEquipo.setUsuarioAutorizaRecobro(usuario_me_autoriza);
                        AutorizacionRecobro autorizacionRecobro = new AutorizacionRecobro();
                        autorizacionRecobro.setCodigo(resultSet.getString(75));
                        autorizacionRecobro.setDescripcion(resultSet.getString(76));
                        autorizacionRecobro.setEstado(resultSet.getString(77));
                    mvtoEquipo.setAutorizacionRecobro(autorizacionRecobro);
                    mvtoEquipo.setObservacionAutorizacion(resultSet.getString(78));
                    mvtoEquipo.setInactividad(resultSet.getString(79));
                        CausaInactividad causaInactividad = new CausaInactividad();
                        causaInactividad.setCodigo(resultSet.getString(81));
                        causaInactividad.setDescripcion(resultSet.getString(82));
                        causaInactividad.setEstado(resultSet.getString(83));
                    mvtoEquipo.setCausaInactividad(causaInactividad);
                        Usuario usuario_me_us_inactividad = new Usuario();
                        usuario_me_us_inactividad.setCodigo(resultSet.getString(85));
                        usuario_me_us_inactividad.setNombres(resultSet.getString(86));
                        usuario_me_us_inactividad.setApellidos(resultSet.getString(87));
                        usuario_me_us_inactividad.setCorreo(resultSet.getString(88));
                    mvtoEquipo.setMotivoParadaEstado(resultSet.getString(89));
                    mvtoEquipo.setUsuarioInactividad(usuario_me_us_inactividad);
                        MotivoParada motivoParada= new MotivoParada();
                        motivoParada.setCodigo(resultSet.getString(91));
                        motivoParada.setDescripcion(resultSet.getString(92));
                    mvtoEquipo.setMotivoParada(motivoParada);
                    mvtoEquipo.setObservacionMvtoEquipo(resultSet.getString(93));
                    mvtoEquipo.setEstado(resultSet.getString(94));
                    mvtoEquipo.setDesdeCarbon(resultSet.getString(95));
                    mvtoEquipo.setCentroCostoDescripción(resultSet.getString(96));
                    CentroCostoSubCentro centroCostoSubCentro_mvtoEquipoDestino = new CentroCostoSubCentro();
                        centroCostoSubCentro_mvtoEquipoDestino.setCodigo(resultSet.getInt(100));
                        centroCostoSubCentro_mvtoEquipoDestino.setDescripcion(resultSet.getString(101));
                        centroCostoSubCentro_mvtoEquipoDestino.setCentroCostoRequiereLaborRealizada(resultSet.getString(103));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipoDestino = new CentroCostoAuxiliar();
                        centroCostoAuxiliar_mvtoEquipoDestino.setCodigo(resultSet.getInt(98));
                        centroCostoAuxiliar_mvtoEquipoDestino.setDescripcion(resultSet.getString(104));
                        centroCostoAuxiliar_mvtoEquipoDestino.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipoDestino);
                    mvtoEquipo.setCentroCostoAuxiliarDestino(centroCostoAuxiliar_mvtoEquipoDestino);
                        CentroCostoMayor centroCostoMayor = new CentroCostoMayor();
                        centroCostoMayor.setCodigo(resultSet.getString(106));
                        centroCostoMayor.setDescripcion(resultSet.getString(107));
                        centroCostoMayor.setClienteBaseDatos(resultSet.getString(119));
                    mvtoEquipo.setCentroCostoMayor(centroCostoMayor);
                    mvtoEquipo.setMes(resultSet.getString(114));
                listadoObjetos.add(mvtoEquipo);
            }
        }catch (SQLException sqlException) {
            System.out.println("Error al tratar al consultar los Movimientos de Carbon");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public ArrayList<MvtoEquipo>                    buscar_MvtoEquipoConMvtoCarbonActivos(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<MvtoEquipo> listadoObjetos = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  [me_cdgo]--1\n" +
                                                                "      ,[me_asignacion_equipo_cdgo]--2\n" +
                                                                "			,[ae_cdgo]--3\n" +
                                                                "			,[ae_equipo_cdgo]--4\n" +
                                                                "				,[eq_cdgo]--5\n" +
                                                                "				,[eq_tipo_equipo_cdgo]--6\n" +
                                                                "				,[eq_marca]--7\n" +
                                                                "				,[eq_modelo]--8\n" +
                                                                "				,[eq_desc]	--9\n" +
                                                                "			,[ae_fecha_registro]--10\n" +
                                                                "			,[ae_fecha_hora_inicio]--11\n" +
                                                                "			,[ae_fecha_hora_fin]--12\n" +
                                                                "			,[ae_cant_minutos]--13\n" +
                                                                "      ,[me_fecha]--14\n" +
                                                                "      ,[me_proveedor_equipo_cdgo]--15\n" +
                                                                "			,[pe_cdgo]--16\n" +
                                                                "			,[pe_nit]--17\n" +
                                                                "			,[pe_desc]--18\n" +
                                                                "      ,[me_num_orden]--19\n" +
                                                                "      ,[me_cntro_oper_cdgo]--20\n" +
                                                                "		  ,[co_cdgo]--21\n" +
                                                                "		  ,[co_desc]--22\n" +
                                                                "      ,[me_cntro_cost_auxiliar_cdgo]--23\n" +
                                                                "		  ,cca_origen.[cca_cdgo]--24\n" +
                                                                "		  ,cca_origen.[cca_cntro_cost_subcentro_cdgo]--25\n" +
                                                                "			,ccs_origen.[ccs_cdgo]--26\n" +
                                                                "			,ccs_origen.[ccs_desc]--27\n" +
                                                                "			,ccs_origen.[ccs_cntro_oper_cdgo]--28\n" +
                                                                "			,ccs_origen.[ccs_cntro_cost_rquiere_labor_realizda]--29\n" +
                                                                "		  ,cca_origen.[cca_desc]--30\n" +
                                                                "      ,[me_cntro_cost_cdgo]--31\n" +
                                                                "		  ,[cc_cdgo]--32\n" +
                                                                "		  ,[cc_descripcion]--33\n" +
                                                                "      ,[me_labor_realizada_cdgo]--34\n" +
                                                                "		  ,[lr_cdgo]--35\n" +
                                                                "		  ,[lr_desc]--36\n" +
                                                                "		  ,[lr_operativa]--37\n" +
                                                                "		  ,[lr_parada]--38\n" +
                                                                "      ,[me_cliente_cdgo]--39\n" +
                                                                "		  ,[cl_cdgo]--40\n" +
                                                                "		  ,[cl_desc]--41\n" +
                                                                "      ,[me_articulo_cdgo]--42\n" +
                                                                "		  ,[ar_cdgo]--43\n" +
                                                                "		  ,[ar_tipo_articulo_cdgo]--44\n" +
                                                                "			  ,[tar_cdgo]--45\n" +
                                                                "			  ,[tar_desc]--46\n" +
                                                                "			  ,[tar_cdgo_erp]--47\n" +
                                                                "			  ,[tar_undad_ngcio]--48\n" +
                                                                "		  ,[ar_desc]--49\n" +
                                                                "      ,[me_motonave_cdgo]--50\n" +
                                                                "		  ,[mn_cdgo]--51\n" +
                                                                "		  ,[mn_desc]--52\n" +
                                                                "      ,[me_fecha_hora_inicio]--53\n" +
                                                                "      ,[me_fecha_hora_fin]--54\n" +
                                                                "      ,[me_total_minutos]--55\n" +
                                                                "      ,[me_valor_hora]--56\n" +
                                                                "      ,[me_costo_total]--57\n" +
                                                                "      ,[me_recobro_cdgo]--58\n" +
                                                                "		  ,[rc_cdgo]--59\n" +
                                                                "		  ,[rc_desc]--60\n" +
                                                                "		  ,[rc_estad]--61\n" +
                                                                "      ,[me_cliente_recobro_cdgo]--62\n" +
                                                                "      ,[me_costo_total_recobro_cliente]--63\n" +
                                                                "      ,[me_usuario_registro_cdgo]--64\n" +
                                                                "			,us_registro.[us_cdgo]--65\n" +
                                                                "			,us_registro.[us_nombres]--66\n" +
                                                                "			,us_registro.[us_apellidos]--67\n" +
                                                                "			,us_registro.[us_correo]--68\n" +
                                                                "      ,[me_usuario_autorizacion_cdgo]--69\n" +
                                                                "			,us_autoriza.[us_cdgo]--70\n" +
                                                                "			,us_autoriza.[us_nombres]--71\n" +
                                                                "			,us_autoriza.[us_apellidos]--72\n" +
                                                                "			,us_autoriza.[us_correo]--73\n" +
                                                                "      ,[me_autorizacion_recobro_cdgo]--74\n" +
                                                                "			,[are_cdgo]--75\n" +
                                                                "			,[are_desc]--76\n" +
                                                                "			,[are_estad]--77\n" +
                                                                "      ,[me_observ_autorizacion]--78\n" +
                                                                "      ,[me_inactividad]--79\n" +
                                                                "      ,[me_causa_inactividad_cdgo]--80\n" +
                                                                "		  ,[ci_cdgo]--81\n" +
                                                                "		  ,[ci_desc]--82\n" +
                                                                "		  ,[ci_estad]--83\n" +
                                                                "      ,[me_usuario_inactividad_cdgo]--84\n" +
                                                                "		  ,us_inactividad.[us_cdgo]--85\n" +
                                                                "		  ,us_inactividad.[us_nombres]--86\n" +
                                                                "		  ,us_inactividad.[us_apellidos]--87\n" +
                                                                "		  ,us_inactividad.[us_correo]--88\n" +
                                                                "      ,[me_motivo_parada_estado]--89\n" +
                                                                "      ,[me_motivo_parada_cdgo]--90\n" +
                                                                "		  ,[mpa_cdgo]--91\n" +
                                                                "		  ,[mpa_desc]--92\n" +
                                                                "      ,[me_observ]--93\n" +
                                                                "      ,CASE [me_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_estado]--94\n" +
                                                                "      ,CASE [me_desde_mvto_carbon] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [me_desde_mvto_carbon]--95\n" +
                                                                "      ,[me_cntro_cost]--96\n" +
                                                                "      ,[me_cntro_cost_auxiliarDestino_cdgo]--97\n" +
                                                                "			,cca_destino.[cca_cdgo]--98\n" +
                                                                "			,cca_destino.[cca_cntro_cost_subcentro_cdgo]--99\n" +
                                                                "				,ccs_destino.[ccs_cdgo]--100\n" +
                                                                "				,ccs_destino.[ccs_desc]--101\n" +
                                                                "				,ccs_destino.[ccs_cntro_oper_cdgo]--102\n" +
                                                                "				,ccs_destino.[ccs_cntro_cost_rquiere_labor_realizda]--103\n" +
                                                                "			,cca_destino.[cca_desc]--104\n" +
                                                                "      ,[me_cntro_cost_mayor_cdgo]--105\n" +
                                                                "		  ,[ccm_cdgo]--106\n" +
                                                                "		  ,[ccm_desc]--107\n" +
                                                                "		  ,[te_cdgo] --108\n" +
                                                                "		  ,[te_desc]--109\n" +
                                                                "		  ,[ae_equipo_pertenencia_cdgo]--110\n" +
                                                                "				,[ep_cdgo]--111\n" +
                                                                "				,[ep_desc]--112\n" +
                                                                "				,[ep_estad]--113\n" +
                                                                "				,datename (MONTH ,[me_fecha])--114\n" +
                                                                "      ,me_cntro_cost_base_datos.[bd_cdgo]   --115 \n" +
                                                                "      ,me_cliente_base_datos.[bd_cdgo]   --116 \n" +
                                                                "      ,me_articulo_base_datos.[bd_cdgo]   --117 \n" +
                                                                "      ,me_motonave_base_datos.[bd_cdgo]   --118 \n" +
                                                                "      ,me_cntro_cost_mayor_base_datos.[bd_cdgo]  --119 \n" +
                                                                "  FROM ["+DB+"].[dbo].[mvto_equipo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [me_asignacion_equipo_cdgo]=[ae_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [me_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_oper] ON [me_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_origen ON  [me_cntro_cost_auxiliar_cdgo]=cca_origen.[cca_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_origen ON cca_origen.[cca_cntro_cost_subcentro_cdgo]=ccs_origen.[ccs_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cntro_cost] ON [me_cntro_cost_cdgo]=[cc_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost]  ON [me_cntro_cost_cdgo]=[cc_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_base_datos ON [cc_cliente_base_datos_cdgo]=me_cntro_cost_base_datos.[bd_cdgo] \n"+  
                                                                "  LEFT JOIN ["+DB+"].[dbo].[labor_realizada] ON [me_labor_realizada_cdgo]=[lr_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cliente] ON [me_cliente_cdgo]=[cl_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente]  ON [me_cliente_cdgo]=[cl_cdgo] AND [cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON [cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                                                                        //"  LEFT JOIN ["+DB+"].[dbo].[articulo] ON [me_articulo_cdgo]=[ar_cdgo]\n" +
        "		LEFT JOIN ["+DB+"].[dbo].[articulo]  ON [me_articulo_cdgo]=[ar_cdgo] AND [ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON [ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] ON [ar_tipo_articulo_cdgo]=[tar_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo]\n" +
                                                                        "		LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[me_motonave_base_datos_cdgo] \n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_motonave_base_datos ON  [mn_base_datos_cdgo]=me_motonave_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_registro ON [me_usuario_registro_cdgo]=us_registro.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_autoriza ON [me_usuario_autorizacion_cdgo]=us_autoriza.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[autorizacion_recobro] ON [me_autorizacion_recobro_cdgo]=[are_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[causa_inactividad] ON [me_causa_inactividad_cdgo]=[ci_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_inactividad ON [me_usuario_inactividad_cdgo]=us_inactividad.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[motivo_parada] ON [me_motivo_parada_cdgo]=[mpa_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_destino ON [me_cntro_cost_auxiliarDestino_cdgo]=cca_destino.[cca_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_destino ON cca_destino.[cca_cntro_cost_subcentro_cdgo]=ccs_destino.[ccs_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor] ON [me_cntro_cost_mayor_cdgo]=[ccm_cdgo]\n" +
                                                                        
"		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor]  ON [me_cntro_cost_mayor_cdgo]=[ccm_cdgo] \n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_mayor_base_datos ON [ccm_cliente_base_datos_cdgo]=me_cntro_cost_mayor_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON [ae_equipo_pertenencia_cdgo]=[ep_cdgo]"
                                                            + "  WHERE  [me_fecha_hora_inicio] BETWEEN ? AND ?  AND [me_inactividad]=0 AND [me_estado]=1 ORDER BY [me_cdgo] DESC");
            query.setString(1, DatetimeInicio);
            query.setString(2, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();    
            boolean validator=true;
            while(resultSet.next()){
                if(validator){
                    listadoObjetos = new ArrayList();
                    validator=false;
                }
                AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                asignacionEquipo.setCodigo(resultSet.getString(3));  
                asignacionEquipo.setFechaRegistro(resultSet.getString(10));
                asignacionEquipo.setFechaHoraInicio(resultSet.getString(11));
                asignacionEquipo.setFechaHoraFin(resultSet.getString(12));
                asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(13));
                    Equipo equipo = new Equipo();
                    equipo.setCodigo(resultSet.getString(5));
                    equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(108),resultSet.getString(109),""));
                    equipo.setMarca(resultSet.getString(7));
                    equipo.setModelo(resultSet.getString(8));
                    equipo.setDescripcion(resultSet.getString(9));
                asignacionEquipo.setEquipo(equipo);
                asignacionEquipo.setPertenencia(new Pertenencia(resultSet.getString(111),resultSet.getString(112),""));
                MvtoEquipo mvtoEquipo = new MvtoEquipo();
                    mvtoEquipo.setCodigo(resultSet.getString(1));
                    mvtoEquipo.setAsignacionEquipo(asignacionEquipo);
                    mvtoEquipo.setFechaRegistro(resultSet.getString(14));
                    mvtoEquipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(16),resultSet.getString(17),resultSet.getString(18),""));
                    mvtoEquipo.setNumeroOrden(resultSet.getString(19));
                        CentroOperacion me_co= new CentroOperacion();
                        me_co.setCodigo(Integer.parseInt(resultSet.getString(21)));
                        me_co.setDescripcion(resultSet.getString(22));
                    mvtoEquipo.setCentroOperacion(me_co);
                        CentroCostoSubCentro centroCostoSubCentro_mvtoEquipo = new CentroCostoSubCentro();
                        centroCostoSubCentro_mvtoEquipo.setCodigo(resultSet.getInt(26));
                        centroCostoSubCentro_mvtoEquipo.setDescripcion(resultSet.getString(27));
                        centroCostoSubCentro_mvtoEquipo.setCentroCostoRequiereLaborRealizada(resultSet.getString(29));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipo = new CentroCostoAuxiliar();
                        centroCostoAuxiliar_mvtoEquipo.setCodigo(resultSet.getInt(24));
                        centroCostoAuxiliar_mvtoEquipo.setDescripcion(resultSet.getString(30));
                        centroCostoAuxiliar_mvtoEquipo.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipo);
                    mvtoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipo);
                        CentroCosto centroCosto = new CentroCosto();
                        centroCosto.setCodigo(resultSet.getString(32));
                        centroCosto.setDescripción(resultSet.getString(33));
                        centroCosto.setClienteBaseDatos(resultSet.getString(115));
                    mvtoEquipo.setCentroCosto(centroCosto);
                        LaborRealizada laborRealizadaT = new LaborRealizada();
                        laborRealizadaT.setCodigo(resultSet.getString(35));
                        laborRealizadaT.setDescripcion(resultSet.getString(36));
                        laborRealizadaT.setEs_operativa(resultSet.getString(37));
                        laborRealizadaT.setEs_parada(resultSet.getString(38));
                    mvtoEquipo.setLaborRealizada(laborRealizadaT);
                    mvtoEquipo.setCliente(new Cliente(resultSet.getString(40),resultSet.getString(41),""));
                    mvtoEquipo.getCliente().setBaseDatos(new BaseDatos(resultSet.getString(116)));
                        TipoArticulo tipoArticulo = new TipoArticulo();
                                tipoArticulo.setCodigo(resultSet.getString(45));
                                tipoArticulo.setDescripcion(resultSet.getString(46));
                                tipoArticulo.setCodigoERP(resultSet.getString(47));
                                tipoArticulo.setUnidadNegocio(resultSet.getString(48));
                        Articulo articulo = new Articulo();
                        articulo.setCodigo(resultSet.getString(43));
                        articulo.setDescripcion(resultSet.getString(49));
                        articulo.setTipoArticulo(tipoArticulo);
                        articulo.setBaseDatos(new BaseDatos( resultSet.getString(117)));
                    mvtoEquipo.setArticulo(articulo);
                    Motonave motonave = new Motonave();
                    motonave.setCodigo(resultSet.getString(51));
                    motonave.setDescripcion(resultSet.getString(52));
                    motonave.setBaseDatos(new BaseDatos( resultSet.getString(118)));
                    mvtoEquipo.setMotonave(motonave);
                    mvtoEquipo.setFechaHoraInicio(resultSet.getString(53));
                mvtoEquipo.setFechaHoraFin(resultSet.getString(54));
                mvtoEquipo.setTotalMinutos(resultSet.getString(55));
                mvtoEquipo.setValorHora(resultSet.getString(56));
                mvtoEquipo.setCostoTotal(resultSet.getString(57));
                        Recobro recobro = new Recobro();
                        recobro.setCodigo(resultSet.getString(59));
                        recobro.setDescripcion(resultSet.getString(60));
                mvtoEquipo.setRecobro(recobro);
                        Usuario usuario_me_registra = new Usuario();
                        usuario_me_registra.setCodigo(resultSet.getString(65));
                        usuario_me_registra.setNombres(resultSet.getString(66));
                        usuario_me_registra.setApellidos(resultSet.getString(67));
                        usuario_me_registra.setCorreo(resultSet.getString(68));
                    mvtoEquipo.setUsuarioQuieRegistra(usuario_me_registra);
                        Usuario usuario_me_autoriza = new Usuario();
                        usuario_me_autoriza.setCodigo(resultSet.getString(70));
                        usuario_me_autoriza.setNombres(resultSet.getString(71));
                        usuario_me_autoriza.setApellidos(resultSet.getString(72));
                        usuario_me_autoriza.setCorreo(resultSet.getString(73));
                    mvtoEquipo.setUsuarioAutorizaRecobro(usuario_me_autoriza);
                        AutorizacionRecobro autorizacionRecobro = new AutorizacionRecobro();
                        autorizacionRecobro.setCodigo(resultSet.getString(75));
                        autorizacionRecobro.setDescripcion(resultSet.getString(76));
                        autorizacionRecobro.setEstado(resultSet.getString(77));
                    mvtoEquipo.setAutorizacionRecobro(autorizacionRecobro);
                    mvtoEquipo.setObservacionAutorizacion(resultSet.getString(78));
                    mvtoEquipo.setInactividad(resultSet.getString(79));
                        CausaInactividad causaInactividad = new CausaInactividad();
                        causaInactividad.setCodigo(resultSet.getString(81));
                        causaInactividad.setDescripcion(resultSet.getString(82));
                        causaInactividad.setEstado(resultSet.getString(83));
                    mvtoEquipo.setCausaInactividad(causaInactividad);
                        Usuario usuario_me_us_inactividad = new Usuario();
                        usuario_me_us_inactividad.setCodigo(resultSet.getString(85));
                        usuario_me_us_inactividad.setNombres(resultSet.getString(86));
                        usuario_me_us_inactividad.setApellidos(resultSet.getString(87));
                        usuario_me_us_inactividad.setCorreo(resultSet.getString(88));
                    mvtoEquipo.setMotivoParadaEstado(resultSet.getString(89));
                    mvtoEquipo.setUsuarioInactividad(usuario_me_us_inactividad);
                        MotivoParada motivoParada= new MotivoParada();
                        motivoParada.setCodigo(resultSet.getString(91));
                        motivoParada.setDescripcion(resultSet.getString(92));
                    mvtoEquipo.setMotivoParada(motivoParada);
                    mvtoEquipo.setObservacionMvtoEquipo(resultSet.getString(93));
                    mvtoEquipo.setEstado(resultSet.getString(94));
                    mvtoEquipo.setDesdeCarbon(resultSet.getString(95));
                    mvtoEquipo.setCentroCostoDescripción(resultSet.getString(96));
                    CentroCostoSubCentro centroCostoSubCentro_mvtoEquipoDestino = new CentroCostoSubCentro();
                        centroCostoSubCentro_mvtoEquipoDestino.setCodigo(resultSet.getInt(100));
                        centroCostoSubCentro_mvtoEquipoDestino.setDescripcion(resultSet.getString(101));
                        centroCostoSubCentro_mvtoEquipoDestino.setCentroCostoRequiereLaborRealizada(resultSet.getString(103));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipoDestino = new CentroCostoAuxiliar();
                        centroCostoAuxiliar_mvtoEquipoDestino.setCodigo(resultSet.getInt(98));
                        centroCostoAuxiliar_mvtoEquipoDestino.setDescripcion(resultSet.getString(104));
                        centroCostoAuxiliar_mvtoEquipoDestino.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipoDestino);
                    mvtoEquipo.setCentroCostoAuxiliarDestino(centroCostoAuxiliar_mvtoEquipoDestino);
                        CentroCostoMayor centroCostoMayor = new CentroCostoMayor();
                        centroCostoMayor.setCodigo(resultSet.getString(106));
                        centroCostoMayor.setDescripcion(resultSet.getString(107));
                        centroCostoMayor.setClienteBaseDatos(resultSet.getString(119));
                    mvtoEquipo.setCentroCostoMayor(centroCostoMayor);
                    mvtoEquipo.setMes(resultSet.getString(114));
                listadoObjetos.add(mvtoEquipo);
            }
        }catch (SQLException sqlException) {
            System.out.println("Error al tratar al consultar los Movimientos de Carbon");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public ArrayList<MvtoEquipo>                    generarInformeFinal_MatrizEquipo(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<MvtoEquipo> listadoObjetos = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  [me_cdgo]--1\n" +
                                                                "      ,[me_asignacion_equipo_cdgo]--2\n" +
                                                                "			,[ae_cdgo]--3\n" +
                                                                "			,[ae_equipo_cdgo]--4\n" +
                                                                "				,[eq_cdgo]--5\n" +
                                                                "				,[eq_tipo_equipo_cdgo]--6\n" +
                                                                "				,[eq_marca]--7\n" +
                                                                "				,[eq_modelo]--8\n" +
                                                                "				,[eq_desc]	--9\n" +
                                                                "			,[ae_fecha_registro]--10\n" +
                                                                "			,[ae_fecha_hora_inicio]--11\n" +
                                                                "			,[ae_fecha_hora_fin]--12\n" +
                                                                "			,[ae_cant_minutos]--13\n" +
                                                                "      ,[me_fecha]--14\n" +
                                                                "      ,[me_proveedor_equipo_cdgo]--15\n" +
                                                                "			,[pe_cdgo]--16\n" +
                                                                "			,[pe_nit]--17\n" +
                                                                "			,[pe_desc]--18\n" +
                                                                "      ,[me_num_orden]--19\n" +
                                                                "      ,[me_cntro_oper_cdgo]--20\n" +
                                                                "		  ,[co_cdgo]--21\n" +
                                                                "		  ,[co_desc]--22\n" +
                                                                "      ,[me_cntro_cost_auxiliar_cdgo]--23\n" +
                                                                "		  ,cca_origen.[cca_cdgo]--24\n" +
                                                                "		  ,cca_origen.[cca_cntro_cost_subcentro_cdgo]--25\n" +
                                                                "			,ccs_origen.[ccs_cdgo]--26\n" +
                                                                "			,ccs_origen.[ccs_desc]--27\n" +
                                                                "			,ccs_origen.[ccs_cntro_oper_cdgo]--28\n" +
                                                                "			,ccs_origen.[ccs_cntro_cost_rquiere_labor_realizda]--29\n" +
                                                                "		  ,cca_origen.[cca_desc]--30\n" +
                                                                "      ,[me_cntro_cost_cdgo]--31\n" +
                                                                "		  ,[cc_cdgo]--32\n" +
                                                                "		  ,[cc_descripcion]--33\n" +
                                                                "      ,[me_labor_realizada_cdgo]--34\n" +
                                                                "		  ,[lr_cdgo]--35\n" +
                                                                "		  ,[lr_desc]--36\n" +
                                                                "		  ,[lr_operativa]--37\n" +
                                                                "		  ,[lr_parada]--38\n" +
                                                                "      ,[me_cliente_cdgo]--39\n" +
                                                                "		  ,[cl_cdgo]--40\n" +
                                                                "		  ,[cl_desc]--41\n" +
                                                                "      ,[me_articulo_cdgo]--42\n" +
                                                                "		  ,[ar_cdgo]--43\n" +
                                                                "		  ,[ar_tipo_articulo_cdgo]--44\n" +
                                                                "			  ,[tar_cdgo]--45\n" +
                                                                "			  ,[tar_desc]--46\n" +
                                                                "			  ,[tar_cdgo_erp]--47\n" +
                                                                "			  ,[tar_undad_ngcio]--48\n" +
                                                                "		  ,[ar_desc]--49\n" +
                                                                "      ,[me_motonave_cdgo]--50\n" +
                                                                "		  ,[mn_cdgo]--51\n" +
                                                                "		  ,[mn_desc]--52\n" +
                                                                "      ,[me_fecha_hora_inicio]--53\n" +
                                                                "      ,[me_fecha_hora_fin]--54\n" +
                                                                "      ,[me_total_minutos]--55\n" +
                                                                "      ,[me_valor_hora]--56\n" +
                                                                "      ,[me_costo_total]--57\n" +
                                                                "      ,[me_recobro_cdgo]--58\n" +
                                                                "		  ,[rc_cdgo]--59\n" +
                                                                "		  ,[rc_desc]--60\n" +
                                                                "		  ,[rc_estad]--61\n" +
                                                                "      ,[me_cliente_recobro_cdgo]--62\n" +
                                                                "      ,[me_costo_total_recobro_cliente]--63\n" +
                                                                "      ,[me_usuario_registro_cdgo]--64\n" +
                                                                "			,us_registro.[us_cdgo]--65\n" +
                                                                "			,us_registro.[us_nombres]--66\n" +
                                                                "			,us_registro.[us_apellidos]--67\n" +
                                                                "			,us_registro.[us_correo]--68\n" +
                                                                "      ,[me_usuario_autorizacion_cdgo]--69\n" +
                                                                "			,us_autoriza.[us_cdgo]--70\n" +
                                                                "			,us_autoriza.[us_nombres]--71\n" +
                                                                "			,us_autoriza.[us_apellidos]--72\n" +
                                                                "			,us_autoriza.[us_correo]--73\n" +
                                                                "      ,[me_autorizacion_recobro_cdgo]--74\n" +
                                                                "			,[are_cdgo]--75\n" +
                                                                "			,[are_desc]--76\n" +
                                                                "			,[are_estad]--77\n" +
                                                                "      ,[me_observ_autorizacion]--78\n" +
                                                                "      ,[me_inactividad]--79\n" +
                                                                "      ,[me_causa_inactividad_cdgo]--80\n" +
                                                                "		  ,[ci_cdgo]--81\n" +
                                                                "		  ,[ci_desc]--82\n" +
                                                                "		  ,[ci_estad]--83\n" +
                                                                "      ,[me_usuario_inactividad_cdgo]--84\n" +
                                                                "		  ,us_inactividad.[us_cdgo]--85\n" +
                                                                "		  ,us_inactividad.[us_nombres]--86\n" +
                                                                "		  ,us_inactividad.[us_apellidos]--87\n" +
                                                                "		  ,us_inactividad.[us_correo]--88\n" +
                                                                "      ,[me_motivo_parada_estado]--89\n" +
                                                                "      ,[me_motivo_parada_cdgo]--90\n" +
                                                                "		  ,[mpa_cdgo]--91\n" +
                                                                "		  ,[mpa_desc]--92\n" +
                                                                "      ,[me_observ]--93\n" +
                                                                "      ,CASE [me_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_estado]--94\n" +
                                                                "      ,CASE [me_desde_mvto_carbon] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [me_desde_mvto_carbon]--95\n" +
                                                                "      ,[me_cntro_cost]--96\n" +
                                                                "      ,[me_cntro_cost_auxiliarDestino_cdgo]--97\n" +
                                                                "			,cca_destino.[cca_cdgo]--98\n" +
                                                                "			,cca_destino.[cca_cntro_cost_subcentro_cdgo]--99\n" +
                                                                "				,ccs_destino.[ccs_cdgo]--100\n" +
                                                                "				,ccs_destino.[ccs_desc]--101\n" +
                                                                "				,ccs_destino.[ccs_cntro_oper_cdgo]--102\n" +
                                                                "				,ccs_destino.[ccs_cntro_cost_rquiere_labor_realizda]--103\n" +
                                                                "			,cca_destino.[cca_desc]--104\n" +
                                                                "      ,[me_cntro_cost_mayor_cdgo]--105\n" +
                                                                "		  ,[ccm_cdgo]--106\n" +
                                                                "		  ,[ccm_desc]--107\n" +
                                                                "		  ,[te_cdgo] --108\n" +
                                                                "		  ,[te_desc]--109\n" +
                                                                "		  ,[ae_equipo_pertenencia_cdgo]--110\n" +
                                                                "				,[ep_cdgo]--111\n" +
                                                                "				,[ep_desc]--112\n" +
                                                                "				,[ep_estad]--113\n" +
                                                                "				,datename (MONTH ,[me_fecha])--114\n" +
                                                                "				,[cce_cdgo]--115\n" +
                                                                "				,[cce_cdgo_intern]--116\n" +
                                                                "				,[cce_desc]--117\n" +
                                                                "				,[cce_estad]--118\n" +
                                                                "	,[me_usuario_cierre_cdgo] --119\n" +
                                                                "		,us_cierre.[us_cdgo] --120\n" +
                                                                "               ,us_cierre.[us_nombres]--121\n" +
                                                                "               ,us_cierre.[us_apellidos] --122 \n" +
                                                                "      ,me_cntro_cost_base_datos.[bd_cdgo]   --123 \n" +
                                                                "      ,me_cliente_base_datos.[bd_cdgo]   --124 \n" +
                                                                "      ,me_articulo_base_datos.[bd_cdgo]   --125 \n" +
                                                                "      ,me_motonave_base_datos.[bd_cdgo]   --126 \n" +
                                                                "      ,me_cntro_cost_mayor_base_datos.[bd_cdgo]  --127 \n" +
                                                                "  FROM ["+DB+"].[dbo].[mvto_equipo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [me_asignacion_equipo_cdgo]=[ae_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [me_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_oper] ON [me_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_origen ON  [me_cntro_cost_auxiliar_cdgo]=cca_origen.[cca_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_origen ON cca_origen.[cca_cntro_cost_subcentro_cdgo]=ccs_origen.[ccs_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cntro_cost] ON [me_cntro_cost_cdgo]=[cc_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost]  ON [me_cntro_cost_cdgo]=[cc_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_base_datos ON [cc_cliente_base_datos_cdgo]=me_cntro_cost_base_datos.[bd_cdgo] \n"+  
                                                                "  LEFT JOIN ["+DB+"].[dbo].[labor_realizada] ON [me_labor_realizada_cdgo]=[lr_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[cliente] ON [me_cliente_cdgo]=[cl_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente]  ON [me_cliente_cdgo]=[cl_cdgo] AND [cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON [cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[articulo] ON [me_articulo_cdgo]=[ar_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[articulo]  ON [me_articulo_cdgo]=[ar_cdgo] AND [ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON [ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
                                                                        "  LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] ON [ar_tipo_articulo_cdgo]=[tar_cdgo]\n" +
                                                                //"  LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo]\n" +
                                                                                "		LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[me_motonave_base_datos_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_motonave_base_datos ON  [mn_base_datos_cdgo]=me_motonave_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_registro ON [me_usuario_registro_cdgo]=us_registro.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_autoriza ON [me_usuario_autorizacion_cdgo]=us_autoriza.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[autorizacion_recobro] ON [me_autorizacion_recobro_cdgo]=[are_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[causa_inactividad] ON [me_causa_inactividad_cdgo]=[ci_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_inactividad ON [me_usuario_inactividad_cdgo]=us_inactividad.[us_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[motivo_parada] ON [me_motivo_parada_cdgo]=[mpa_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_destino ON [me_cntro_cost_auxiliarDestino_cdgo]=cca_destino.[cca_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_destino ON cca_destino.[cca_cntro_cost_subcentro_cdgo]=ccs_destino.[ccs_cdgo]\n" +
                                                               // "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor] ON [me_cntro_cost_mayor_cdgo]=[ccm_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor]  ON [me_cntro_cost_mayor_cdgo]=[ccm_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_mayor_base_datos ON [ccm_cliente_base_datos_cdgo]=me_cntro_cost_mayor_base_datos.[bd_cdgo] \n"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo]\n" +
                                                                "  LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON [ae_equipo_pertenencia_cdgo]=[ep_cdgo]"+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_equipo] ON [cce_cdgo]=[eq_cntro_cost_equipo_cdgo] "+
                                                                "  LEFT JOIN ["+DB+"].[dbo].[usuario] us_cierre ON [me_usuario_cierre_cdgo] =us_cierre.[us_cdgo]"
                                                            + " WHERE  [me_fecha_hora_inicio] BETWEEN ? AND ?   AND [me_inactividad]=0 AND [me_estado]=1  ORDER BY [me_cdgo] DESC");
            query.setString(1, DatetimeInicio);
            query.setString(2, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();    
            boolean validator=true;
            while(resultSet.next()){
                if(validator){
                    listadoObjetos = new ArrayList();
                    validator=false;
                }
                AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                asignacionEquipo.setCodigo(resultSet.getString(3));  
                asignacionEquipo.setFechaRegistro(resultSet.getString(10));
                asignacionEquipo.setFechaHoraInicio(resultSet.getString(11));
                asignacionEquipo.setFechaHoraFin(resultSet.getString(12));
                asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(13));
                    Equipo equipo = new Equipo();
                    equipo.setCodigo(resultSet.getString(5));
                    equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(108),resultSet.getString(109),""));
                    equipo.setMarca(resultSet.getString(7));
                    equipo.setModelo(resultSet.getString(8));
                    equipo.setDescripcion(resultSet.getString(9));
                    CentroCostoEquipo centroCostoEquipo = new CentroCostoEquipo();
                        centroCostoEquipo.setCodigo(resultSet.getString(115));
                        centroCostoEquipo.setCodigoInterno(resultSet.getString(116));
                        centroCostoEquipo.setDescripcion(resultSet.getString(117));
                        centroCostoEquipo.setEstado(resultSet.getString(118));
                        equipo.setCentroCostoEquipo(centroCostoEquipo);
                asignacionEquipo.setEquipo(equipo);
                asignacionEquipo.setPertenencia(new Pertenencia(resultSet.getString(111),resultSet.getString(112),""));
                MvtoEquipo mvtoEquipo = new MvtoEquipo();
                    mvtoEquipo.setCodigo(resultSet.getString(1));
                    mvtoEquipo.setAsignacionEquipo(asignacionEquipo);
                    mvtoEquipo.setFechaRegistro(resultSet.getString(14));
                    mvtoEquipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(16),resultSet.getString(17),resultSet.getString(18),""));
                    mvtoEquipo.setNumeroOrden(resultSet.getString(19));
                        CentroOperacion me_co= new CentroOperacion();
                        me_co.setCodigo(Integer.parseInt(resultSet.getString(21)));
                        me_co.setDescripcion(resultSet.getString(22));
                    mvtoEquipo.setCentroOperacion(me_co);
                        CentroCostoSubCentro centroCostoSubCentro_mvtoEquipo = new CentroCostoSubCentro();
                        centroCostoSubCentro_mvtoEquipo.setCodigo(resultSet.getInt(26));
                        centroCostoSubCentro_mvtoEquipo.setDescripcion(resultSet.getString(27));
                        centroCostoSubCentro_mvtoEquipo.setCentroCostoRequiereLaborRealizada(resultSet.getString(29));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipo = new CentroCostoAuxiliar();
                        centroCostoAuxiliar_mvtoEquipo.setCodigo(resultSet.getInt(24));
                        centroCostoAuxiliar_mvtoEquipo.setDescripcion(resultSet.getString(30));
                        centroCostoAuxiliar_mvtoEquipo.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipo);
                    mvtoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipo);
                        CentroCosto centroCosto = new CentroCosto();
                        centroCosto.setCodigo(resultSet.getString(32));
                        centroCosto.setDescripción(resultSet.getString(33));
                        centroCosto.setClienteBaseDatos(resultSet.getString(123));
                    mvtoEquipo.setCentroCosto(centroCosto);
                        LaborRealizada laborRealizadaT = new LaborRealizada();
                        laborRealizadaT.setCodigo(resultSet.getString(35));
                        laborRealizadaT.setDescripcion(resultSet.getString(36));
                        laborRealizadaT.setEs_operativa(resultSet.getString(37));
                        laborRealizadaT.setEs_parada(resultSet.getString(38));
                    mvtoEquipo.setLaborRealizada(laborRealizadaT);
                    mvtoEquipo.setCliente(new Cliente(resultSet.getString(40),resultSet.getString(41),""));
                    mvtoEquipo.getCliente().setBaseDatos(new BaseDatos(resultSet.getString(124)));
                        TipoArticulo tipoArticulo = new TipoArticulo();
                                tipoArticulo.setCodigo(resultSet.getString(45));
                                tipoArticulo.setDescripcion(resultSet.getString(46));
                                tipoArticulo.setCodigoERP(resultSet.getString(47));
                                tipoArticulo.setUnidadNegocio(resultSet.getString(48));
                        Articulo articulo = new Articulo();
                        articulo.setCodigo(resultSet.getString(43));
                        articulo.setDescripcion(resultSet.getString(49));
                        articulo.setBaseDatos(new BaseDatos( resultSet.getString(125)));
                        articulo.setTipoArticulo(tipoArticulo);
                    mvtoEquipo.setArticulo(articulo);
                    Motonave motonave = new Motonave();
                    motonave.setCodigo(resultSet.getString(51));
                    motonave.setDescripcion(resultSet.getString(52));
                    motonave.setBaseDatos(new BaseDatos( resultSet.getString(126)));
                    mvtoEquipo.setMotonave(motonave);
                    mvtoEquipo.setFechaHoraInicio(resultSet.getString(53));
                mvtoEquipo.setFechaHoraFin(resultSet.getString(54));
                mvtoEquipo.setTotalMinutos(resultSet.getString(55));
                mvtoEquipo.setValorHora(resultSet.getString(56));
                mvtoEquipo.setCostoTotal(resultSet.getString(57));
                        Recobro recobro = new Recobro();
                        recobro.setCodigo(resultSet.getString(59));
                        recobro.setDescripcion(resultSet.getString(60));
                mvtoEquipo.setRecobro(recobro);
                        Usuario usuario_me_registra = new Usuario();
                        usuario_me_registra.setCodigo(resultSet.getString(65));
                        usuario_me_registra.setNombres(resultSet.getString(66));
                        usuario_me_registra.setApellidos(resultSet.getString(67));
                        usuario_me_registra.setCorreo(resultSet.getString(68));
                    mvtoEquipo.setUsuarioQuieRegistra(usuario_me_registra);
                        Usuario usuario_me_autoriza = new Usuario();
                        usuario_me_autoriza.setCodigo(resultSet.getString(70));
                        usuario_me_autoriza.setNombres(resultSet.getString(71));
                        usuario_me_autoriza.setApellidos(resultSet.getString(72));
                        usuario_me_autoriza.setCorreo(resultSet.getString(73));
                    mvtoEquipo.setUsuarioAutorizaRecobro(usuario_me_autoriza);
                        AutorizacionRecobro autorizacionRecobro = new AutorizacionRecobro();
                        autorizacionRecobro.setCodigo(resultSet.getString(75));
                        autorizacionRecobro.setDescripcion(resultSet.getString(76));
                        autorizacionRecobro.setEstado(resultSet.getString(77));
                    mvtoEquipo.setAutorizacionRecobro(autorizacionRecobro);
                    mvtoEquipo.setObservacionAutorizacion(resultSet.getString(78));
                    mvtoEquipo.setInactividad(resultSet.getString(79));
                        CausaInactividad causaInactividad = new CausaInactividad();
                        causaInactividad.setCodigo(resultSet.getString(81));
                        causaInactividad.setDescripcion(resultSet.getString(82));
                        causaInactividad.setEstado(resultSet.getString(83));
                    mvtoEquipo.setCausaInactividad(causaInactividad);
                        Usuario usuario_me_us_inactividad = new Usuario();
                        usuario_me_us_inactividad.setCodigo(resultSet.getString(85));
                        usuario_me_us_inactividad.setNombres(resultSet.getString(86));
                        usuario_me_us_inactividad.setApellidos(resultSet.getString(87));
                        usuario_me_us_inactividad.setCorreo(resultSet.getString(88));
                    mvtoEquipo.setMotivoParadaEstado(resultSet.getString(89));
                    mvtoEquipo.setUsuarioInactividad(usuario_me_us_inactividad);
                        MotivoParada motivoParada= new MotivoParada();
                        motivoParada.setCodigo(resultSet.getString(91));
                        motivoParada.setDescripcion(resultSet.getString(92));
                    mvtoEquipo.setMotivoParada(motivoParada);
                    mvtoEquipo.setObservacionMvtoEquipo(resultSet.getString(93));
                    mvtoEquipo.setEstado(resultSet.getString(94));
                    mvtoEquipo.setDesdeCarbon(resultSet.getString(95));
                    mvtoEquipo.setCentroCostoDescripción(resultSet.getString(96));
                    CentroCostoSubCentro centroCostoSubCentro_mvtoEquipoDestino = new CentroCostoSubCentro();
                        centroCostoSubCentro_mvtoEquipoDestino.setCodigo(resultSet.getInt(100));
                        centroCostoSubCentro_mvtoEquipoDestino.setDescripcion(resultSet.getString(101));
                        centroCostoSubCentro_mvtoEquipoDestino.setCentroCostoRequiereLaborRealizada(resultSet.getString(103));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipoDestino = new CentroCostoAuxiliar();
                        centroCostoAuxiliar_mvtoEquipoDestino.setCodigo(resultSet.getInt(98));
                        centroCostoAuxiliar_mvtoEquipoDestino.setDescripcion(resultSet.getString(104));
                        centroCostoAuxiliar_mvtoEquipoDestino.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipoDestino);
                    mvtoEquipo.setCentroCostoAuxiliarDestino(centroCostoAuxiliar_mvtoEquipoDestino);
                        CentroCostoMayor centroCostoMayor = new CentroCostoMayor();
                        centroCostoMayor.setCodigo(resultSet.getString(106));
                        centroCostoMayor.setDescripcion(resultSet.getString(107));
                        centroCostoMayor.setClienteBaseDatos(resultSet.getString(127));
                    mvtoEquipo.setCentroCostoMayor(centroCostoMayor);
                    mvtoEquipo.setMes(resultSet.getString(114)); 
                        Usuario usuario_me_Cierra = new Usuario();
                            usuario_me_Cierra.setCodigo(resultSet.getString(120));
                            usuario_me_Cierra.setNombres(resultSet.getString(121));
                            usuario_me_Cierra.setApellidos(resultSet.getString(122));
                    mvtoEquipo.setUsuarioQuienCierra(usuario_me_Cierra);    
                    
                listadoObjetos.add(mvtoEquipo);
            }
        }catch (SQLException sqlException) {
            System.out.println("Error al tratar al consultar los Movimientos de Carbon");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public ArrayList<PlantillaConectorMvtoEquipo>   conectorMvtoEquipo(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<PlantillaConectorMvtoEquipo> listadoObjetos = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{/*"DECLARE @costoTotal FLOAT\n" +
                                                            "	SET @costoTotal= (SELECT  SUM (CONVERT (decimal(38,2), REPLACE([me_costo_total],',','.'))) AS total \n" +
                                                            "						FROM ["+DB+"].[dbo].[mvto_equipo]\n" +
                                                            "							INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [cca_cdgo]= [me_cntro_cost_auxiliar_cdgo]\n" +
                                                            "							INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
                                                            "							INNER JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [me_asignacion_equipo_cdgo]=[ae_cdgo]\n" +
                                                            "							INNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo] \n" +
                                                            "							LEFT JOIN ["+DB+"].[dbo].[cntro_cost_equipo] ON [cce_cdgo]=[eq_cntro_cost_equipo_cdgo]\n" +
                                                            "							LEFT JOIN  ["+DB+"].[dbo].[articulo] ON [me_articulo_cdgo] = [ar_cdgo]\n" +
                                                            "							LEFT JOIN  ["+DB+"].[dbo].[tipo_articulo] ON [ar_tipo_articulo_cdgo]=[tar_cdgo]\n" +
                                                            "							LEFT JOIN ["+DB+"].[dbo].[cliente] ON [cl_cdgo]=[me_cliente_cdgo]\n" +
                                                            "						WHERE [me_fecha_hora_inicio] BETWEEN '"+DatetimeInicio+"' AND '"+DatetimeFin+"' AND [me_inactividad]=0 AND [me_estado]=1);\n" +
                                                            "  --SELECT @costoTotal;\n" +
                                                            "  SELECT \n" +
                                                            "		[ccs_desc],[cca_desc],[cl_desc], [ar_desc], [tar_undad_ngcio],[me_cntro_cost], SUM (CONVERT (decimal(38,2), REPLACE([me_costo_total],',','.'))) AS total  , ( (SUM (CONVERT (decimal(38,2), REPLACE([me_costo_total],',','.'))))*(100 / @costoTotal)) AS Porcentaje  --CONVERT (decimal(20,4), [me_costo_total]) \n" +
                                                            "  FROM ["+DB+"].[dbo].[mvto_equipo]\n" +
                                                            "		  INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [cca_cdgo]= [me_cntro_cost_auxiliar_cdgo]\n" +
                                                            "		  INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
                                                            "		  INNER JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [me_asignacion_equipo_cdgo]=[ae_cdgo]\n" +
                                                            "		  INNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo] \n" +
                                                            "		  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_equipo] ON [cce_cdgo]=[eq_cntro_cost_equipo_cdgo]\n" +
                                                            "		  LEFT JOIN  ["+DB+"].[dbo].[articulo] ON [me_articulo_cdgo] = [ar_cdgo]\n" +
                                                            "		  LEFT JOIN  ["+DB+"].[dbo].[tipo_articulo] ON [ar_tipo_articulo_cdgo]=[tar_cdgo]\n" +
                                                            "		  LEFT JOIN ["+DB+"].[dbo].[cliente] ON [cl_cdgo]=[me_cliente_cdgo]\n" +
                                                            "	WHERE [me_fecha_hora_inicio] BETWEEN '"+DatetimeInicio+"' AND '"+DatetimeFin+"' AND [me_inactividad]=0 AND [me_estado]=1--  AND [cce_cdgo_intern] LIKE '110601' AND [ccs_cdgo]=2\n" +
                                                            "		GROUP BY [me_cntro_cost_auxiliar_cdgo],[cca_desc],[me_cliente_cdgo],[tar_undad_ngcio],[me_cntro_cost],[cl_desc],[ccs_desc],[ar_desc]\n" +
                                                            "		ORDER BY [ccs_desc],[cl_desc],[ar_desc]"*/
            PreparedStatement query= conexion.prepareStatement("DECLARE @costoTotal FLOAT\n" +
                                                            "	SET @costoTotal= (SELECT  SUM (CONVERT (decimal(38,2), REPLACE([me_costo_total],',','.'))) AS total \n" +
                                                            "						FROM ["+DB+"].[dbo].[mvto_equipo]\n" +
                                                            "							INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [cca_cdgo]= [me_cntro_cost_auxiliar_cdgo]\n" +
                                                            "							INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
                                                            "							INNER JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [me_asignacion_equipo_cdgo]=[ae_cdgo]\n" +
                                                            "							INNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo] \n" +
                                                            "							LEFT JOIN ["+DB+"].[dbo].[cntro_cost_equipo] ON [cce_cdgo]=[eq_cntro_cost_equipo_cdgo]\n" +
                                                            "							LEFT JOIN  ["+DB+"].[dbo].[articulo] ON [me_articulo_cdgo] = [ar_cdgo]\n" +
                                                            "							LEFT JOIN  ["+DB+"].[dbo].[tipo_articulo] ON [ar_tipo_articulo_cdgo]=[tar_cdgo]\n" +
                                                            "							LEFT JOIN ["+DB+"].[dbo].[cliente] ON [cl_cdgo]=[me_cliente_cdgo]\n" +
                                                            "						WHERE [me_fecha_hora_inicio] BETWEEN '"+DatetimeInicio+"' AND '"+DatetimeFin+"' AND [me_inactividad]=0 AND [me_estado]=1);\n" +
                                                            "  --SELECT @costoTotal;\n" +
                                                            "  SELECT \n" +
                                                            "		[ccs_desc],[cca_desc],[cl_desc], '', [tar_undad_ngcio],[me_cntro_cost], SUM (CONVERT (decimal(38,2), REPLACE([me_costo_total],',','.'))) AS total  , ( (SUM (CONVERT (decimal(38,2), REPLACE([me_costo_total],',','.'))))*(100 / @costoTotal)) AS Porcentaje  --CONVERT (decimal(20,4), [me_costo_total]) \n" +
                                                            "  FROM ["+DB+"].[dbo].[mvto_equipo]\n" +
                                                            "		  INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [cca_cdgo]= [me_cntro_cost_auxiliar_cdgo]\n" +
                                                            "		  INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
                                                            "		  INNER JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [me_asignacion_equipo_cdgo]=[ae_cdgo]\n" +
                                                            "		  INNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo] \n" +
                                                            "		  LEFT JOIN ["+DB+"].[dbo].[cntro_cost_equipo] ON [cce_cdgo]=[eq_cntro_cost_equipo_cdgo]\n" +
                                                            "		  LEFT JOIN  ["+DB+"].[dbo].[articulo] ON [me_articulo_cdgo] = [ar_cdgo]\n" +
                                                            "		  LEFT JOIN  ["+DB+"].[dbo].[tipo_articulo] ON [ar_tipo_articulo_cdgo]=[tar_cdgo]\n" +
                                                            "		  LEFT JOIN ["+DB+"].[dbo].[cliente] ON [cl_cdgo]=[me_cliente_cdgo]\n" +
                                                            "	WHERE [me_fecha_hora_inicio] BETWEEN '"+DatetimeInicio+"' AND '"+DatetimeFin+"' AND [me_inactividad]=0 AND [me_estado]=1\n" +
                                                            "		GROUP BY [me_cntro_cost_auxiliar_cdgo],[cca_desc],[me_cliente_cdgo],[tar_undad_ngcio],[me_cntro_cost],[cl_desc],[ccs_desc]\n" +
                                                            "		ORDER BY [ccs_desc],[cl_desc],[tar_undad_ngcio]");
            //query.setString(1, DatetimeInicio);
            //query.setString(2, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();    
            boolean validator=true;
            while(resultSet.next()){
                if(validator){
                    listadoObjetos = new ArrayList();
                    validator=false;
                }
                PlantillaConectorMvtoEquipo plantillaConectorMvtoEquipo = new PlantillaConectorMvtoEquipo();
                    plantillaConectorMvtoEquipo.setSubcentro(resultSet.getString(1));
                    plantillaConectorMvtoEquipo.setCentroCostosAuxiliar(resultSet.getString(2));
                    plantillaConectorMvtoEquipo.setCliente(resultSet.getString(3));
                    plantillaConectorMvtoEquipo.setArticulo(resultSet.getString(4));
                    plantillaConectorMvtoEquipo.setUnidadNegocio(resultSet.getString(5));
                    plantillaConectorMvtoEquipo.setCentroCosto(resultSet.getString(6));
                    plantillaConectorMvtoEquipo.setTotal(resultSet.getString(7));
                    plantillaConectorMvtoEquipo.setPorcentaje(resultSet.getString(8));
                listadoObjetos.add(plantillaConectorMvtoEquipo);
            }
        }catch (SQLException sqlException) {
            System.out.println("Error al tratar al consultar");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public int                                      RegistrarAutorizacionRecobro(MvtoEquipo equipo, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        int result=0;
        try{  
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            if(equipo.getAutorizacionRecobro().getCodigo().equals("1")){
                PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_equipo] SET\n" +
                    "									[me_cliente_recobro_cdgo]=(SELECT [clr_cdgo]\n" +
                    "										 FROM ["+DB+"].[dbo].[cliente_recobro]\n" +
                    "									WHERE [clr_cdgo]= (SELECT MAX([clr_cdgo]) FROM ["+DB+"].[dbo].[cliente_recobro] WHERE [clr_cliente_cdgo] LIKE ? AND [clr_cliente_base_datos_cdgo]=?)), \n" +
                    "									[me_usuario_autorizacion_cdgo]=?,\n" +
                    "									[me_autorizacion_recobro_cdgo]=?,\n" +
                    "									[me_observ_autorizacion]=?,\n"+
                                                                                        " [me_recobro_cdgo]=? \n" +
                    " WHERE [me_cdgo]=?");
                queryActualizar.setString(1, equipo.getCliente().getCodigo());
                queryActualizar.setString(2, equipo.getCliente().getBaseDatos().getCodigo());
                queryActualizar.setString(3, us.getCodigo());
                queryActualizar.setString(4, equipo.getAutorizacionRecobro().getCodigo());
                queryActualizar.setString(5, equipo.getObservacionAutorizacion());
                queryActualizar.setString(6, equipo.getRecobro().getCodigo());
                queryActualizar.setString(7, equipo.getCodigo());
                queryActualizar.execute();
                result=1;
            }else{
                if(equipo.getAutorizacionRecobro().getCodigo().equals("0")){
                    PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_equipo] SET\n" +
                        "									[me_usuario_autorizacion_cdgo]=?,\n" +
                        "									[me_autorizacion_recobro_cdgo]=?,\n" +
                        "									[me_observ_autorizacion]=?,[me_recobro_cdgo]=? \n" +
                        " WHERE [me_cdgo]=?");
                    queryActualizar.setString(1, us.getCodigo());
                    queryActualizar.setString(2, equipo.getAutorizacionRecobro().getCodigo());
                    queryActualizar.setString(3, equipo.getObservacionAutorizacion());
                    queryActualizar.setString(4, equipo.getRecobro().getCodigo());
                    queryActualizar.setString(5, equipo.getCodigo());
                    queryActualizar.execute();
                    result=1;
                }
            }
            if(result==1){
                result=0;
                 //Extraemos el nombre del Equipo y la IP        
                String namePc=new ControlDB_Config().getNamePC();
                String ipPc=new ControlDB_Config().getIpPc();
                String macPC=new ControlDB_Config().getMacAddress();
                PreparedStatement Query_Auditoria= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[auditoria]([au_cdgo]\n" +
                                        "      ,[au_fecha]\n" +
                                        "      ,[au_usuario_cdgo_registro]\n" +
                                        "      ,[au_nombre_dispositivo_registro]\n" +
                                        "      ,[au_ip_dispositivo_registro]\n" +
                                        "      ,[au_mac_dispositivo_registro]\n" +
                                        "      ,[au_cdgo_mtvo]\n" +
                                        "      ,[au_desc_mtvo]\n" +
                                        "      ,[au_detalle_mtvo])\n" +
                        "     VALUES("+
                        "           (SELECT (CASE WHEN (MAX([au_cdgo]) IS NULL) THEN 1 ELSE (MAX([au_cdgo])+1) END)AS [au_cdgo] FROM ["+DB+"].[dbo].[auditoria])"+
                        "           ,(SELECT SYSDATETIME())"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,'AUTORIZACIÓN_RECOBRO'" +
                        "           ,CONCAT('Se registró una nueva autorización de recobro de Equipo con Código:',?,' Autorizado: ',?,' Observación: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, equipo.getCodigo());
                Query_Auditoria.setString(6, equipo.getCodigo());
                Query_Auditoria.setString(7, equipo.getAutorizacionRecobro().getDescripcion());
                Query_Auditoria.setString(8, equipo.getObservacionAutorizacion());
                Query_Auditoria.execute();
                result=1;
            } 
        }
        catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "ERROR al querer insertar los datos.");
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    }
    public int                                      ModificarMvtoCarbon(MvtoCarbon_ListadoEquipos Objeto, Usuario us, String script, String scriptGeneral_MvtoEquipo, String scriptGeneral_MvtoCarbon,String scriptGeneral_MvtoCarbon_listado, String observacion) throws FileNotFoundException, UnknownHostException, SocketException{
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        int result=0;
        try{  
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            System.out.println(""+"UPDATE ["+DB+"].[dbo].[mvto_equipo] SET "+script+" WHERE [me_cdgo]="+Objeto.getMvtoEquipo().getCodigo());
            if(!script.equals("")){
                PreparedStatement queryActualizar_s= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_equipo] SET "+script+" WHERE [me_cdgo]=?");
                queryActualizar_s.setString(1, Objeto.getMvtoEquipo().getCodigo());
                queryActualizar_s.execute(); 
                result=1;
               System.out.println(result);
            }
            if(result==1){
                if(!scriptGeneral_MvtoEquipo.equals("")){
                    result=0;
                    PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_equipo] SET "+scriptGeneral_MvtoEquipo +" FROM [mvto_equipo] \n" +
                                                                            "		INNER JOIN ["+DB+"].[dbo].[mvto_carbon_listado_equipo] ON [mcle_mvto_equipo_cdgo]= [me_cdgo]\n" +
                                                                            "		INNER JOIN ["+DB+"].[dbo].[mvto_carbon] ON [mcle_mvto_carbon_cdgo] =  [mc_cdgo]\n" +
                                                                            "	WHERE [mcle_mvto_carbon_cdgo]= ?");
                    queryActualizar.setString(1, Objeto.getMvtoCarbon().getCodigo());
                    queryActualizar.execute();
                    result=1;
                }
            }
           // result=;
            if(result==1){
                if(!scriptGeneral_MvtoCarbon.equals("")){
                    result=0;
                    PreparedStatement queryActualizar_MvtoCarbon= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_carbon] SET "+scriptGeneral_MvtoCarbon +" FROM [mvto_carbon] \n" +
                                                "		INNER JOIN ["+DB+"].[dbo].[mvto_carbon_listado_equipo] ON [mcle_mvto_carbon_cdgo]= [mc_cdgo]\n" +
                                                "	WHERE [mcle_mvto_carbon_cdgo]= ?");
                    queryActualizar_MvtoCarbon.setString(1, Objeto.getMvtoCarbon().getCodigo());
                    queryActualizar_MvtoCarbon.execute();
                    result=1;
                }
            }
            if(result==1){
                if(!scriptGeneral_MvtoCarbon_listado.equals("")){
                    result=0;
                    PreparedStatement queryActualizar_MvtoCarbon_listado= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_carbon_listado_equipo] SET "+scriptGeneral_MvtoCarbon_listado +" WHERE [mcle_mvto_carbon_cdgo]= ?");
                    queryActualizar_MvtoCarbon_listado.setString(1, Objeto.getMvtoCarbon().getCodigo());
                    queryActualizar_MvtoCarbon_listado.execute();
                    result=1;
                }
            }
            if(result==1){
                result=0;
                 //Extraemos el nombre del Equipo y la IP        
                String namePc=new ControlDB_Config().getNamePC();
                String ipPc=new ControlDB_Config().getIpPc();
                String macPC=new ControlDB_Config().getMacAddress();
                PreparedStatement Query_Auditoria= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[auditoria]([au_cdgo]\n" +
                                        "      ,[au_fecha]\n" +
                                        "      ,[au_usuario_cdgo_registro]\n" +
                                        "      ,[au_nombre_dispositivo_registro]\n" +
                                        "      ,[au_ip_dispositivo_registro]\n" +
                                        "      ,[au_mac_dispositivo_registro]\n" +
                                        "      ,[au_cdgo_mtvo]\n" +
                                        "      ,[au_desc_mtvo]\n" +
                                        "      ,[au_detalle_mtvo])\n" +
                        "     VALUES("+
                        "           (SELECT (CASE WHEN (MAX([au_cdgo]) IS NULL) THEN 1 ELSE (MAX([au_cdgo])+1) END)AS [au_cdgo] FROM ["+DB+"].[dbo].[auditoria])"+
                        "           ,(SELECT SYSDATETIME())"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,'MODIFICACION_MVTOCARBON'" +
                        "           ,CONCAT('Se registro una modificación en el sistema sobre un MvtoEquipo con código',?,' Razón Modificación: ',?,' Modificando: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, Objeto.getMvtoEquipo().getCodigo());
                Query_Auditoria.setString(6, Objeto.getMvtoEquipo().getCodigo());
                Query_Auditoria.setString(7, observacion);
                Query_Auditoria.setString(8, script);
                Query_Auditoria.execute();
                result=1;
            } 
        }
        catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "ERROR al querer insertar los datos.");
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;  
    } 
    public int                                      inactivarEquipo(MvtoEquipo mvtoEquipo, Usuario us, String observacion) throws FileNotFoundException, UnknownHostException, SocketException{
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        int result=0;
        try{  
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();           
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_equipo] SET [me_inactividad]=1,[me_causa_inactividad_cdgo]=?,[me_usuario_inactividad_cdgo]=?, [me_estado]=0 WHERE [me_cdgo]=?");
            queryActualizar.setString(1,mvtoEquipo.getCausaInactividad().getCodigo());
            queryActualizar.setString(2,us.getCodigo());
            queryActualizar.setString(3,mvtoEquipo.getCodigo());
            queryActualizar.execute();
            result=1;
            if(result==1){
                result=0;
                //Extraemos el nombre del Equipo y la IP        
                String namePc=new ControlDB_Config().getNamePC();
                String ipPc=new ControlDB_Config().getIpPc();
                String macPC=new ControlDB_Config().getMacAddress();

                PreparedStatement Query_Auditoria= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[auditoria]([au_cdgo]\n" +
                                        "      ,[au_fecha]\n" +
                                        "      ,[au_usuario_cdgo_registro]\n" +
                                        "      ,[au_nombre_dispositivo_registro]\n" +
                                        "      ,[au_ip_dispositivo_registro]\n" +
                                        "      ,[au_mac_dispositivo_registro]\n" +
                                        "      ,[au_cdgo_mtvo]\n" +
                                        "      ,[au_desc_mtvo]\n" +
                                        "      ,[au_detalle_mtvo])\n" +
                        "     VALUES("+
                        "           (SELECT (CASE WHEN (MAX([au_cdgo]) IS NULL) THEN 1 ELSE (MAX([au_cdgo])+1) END)AS [au_cdgo] FROM ["+DB+"].[dbo].[auditoria])"+
                        "           ,(SELECT SYSDATETIME())"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,'DESCARGUE_CARBON'" +
                        "           ,CONCAT('Se inactivo un movimiento de un equipo en el sistema de Código:',?,' Descripción: ',?,' Modelo: ',?,' Razon de activación: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, mvtoEquipo.getCodigo());
                Query_Auditoria.setString(6, mvtoEquipo.getCodigo());
                Query_Auditoria.setString(7, mvtoEquipo.getAsignacionEquipo().getEquipo().getDescripcion());
                Query_Auditoria.setString(8, mvtoEquipo.getAsignacionEquipo().getEquipo().getModelo()+" Marca:"+mvtoEquipo.getAsignacionEquipo().getEquipo().getMarca());
                Query_Auditoria.setString(9,observacion);
                Query_Auditoria.execute();
                result=1;
            } 
        }
               
        catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "ERROR al querer insertar los datos.");
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    } 
    public int                                      activarEquipo(MvtoEquipo mvtoEquipo, Usuario us, String observacion) throws FileNotFoundException, UnknownHostException, SocketException{
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        int result=0;
        try{  
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();           
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_equipo] SET [me_inactividad]=0,[me_causa_inactividad_cdgo]=NULL,[me_usuario_inactividad_cdgo]=NULL, [me_estado]=1 WHERE [me_cdgo]=?");
            queryActualizar.setString(1,mvtoEquipo.getCodigo());
            queryActualizar.execute();
            result=1;
            if(result==1){
                result=0;
                //Extraemos el nombre del Equipo y la IP        
                String namePc=new ControlDB_Config().getNamePC();
                String ipPc=new ControlDB_Config().getIpPc();
                String macPC=new ControlDB_Config().getMacAddress();
                PreparedStatement Query_Auditoria= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[auditoria]([au_cdgo]\n" +
                                        "      ,[au_fecha]\n" +
                                        "      ,[au_usuario_cdgo_registro]\n" +
                                        "      ,[au_nombre_dispositivo_registro]\n" +
                                        "      ,[au_ip_dispositivo_registro]\n" +
                                        "      ,[au_mac_dispositivo_registro]\n" +
                                        "      ,[au_cdgo_mtvo]\n" +
                                        "      ,[au_desc_mtvo]\n" +
                                        "      ,[au_detalle_mtvo])\n" +
                        "     VALUES("+
                        "           (SELECT (CASE WHEN (MAX([au_cdgo]) IS NULL) THEN 1 ELSE (MAX([au_cdgo])+1) END)AS [au_cdgo] FROM ["+DB+"].[dbo].[auditoria])"+
                        "           ,(SELECT SYSDATETIME())"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,'DESCARGUE_CARBON'" +
                        "           ,CONCAT('Se activo un movimiento de un equipo en el sistema de Código:',?,' Descripción: ',?,' Modelo: ',?,' Razon de activación: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, mvtoEquipo.getCodigo());
                Query_Auditoria.setString(6, mvtoEquipo.getCodigo());
                Query_Auditoria.setString(7, mvtoEquipo.getAsignacionEquipo().getEquipo().getDescripcion());
                Query_Auditoria.setString(8, mvtoEquipo.getAsignacionEquipo().getEquipo().getModelo()+" Marca:"+mvtoEquipo.getAsignacionEquipo().getEquipo().getMarca());
                Query_Auditoria.setString(9,observacion);
                Query_Auditoria.execute();
                result=1;
            } 
        }
               
        catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "ERROR al querer insertar los datos.");
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    }
    public int                                      activarEquipoCarbon(MvtoCarbon_ListadoEquipos mvtoEquipoCarbon, Usuario us, String observacion) throws FileNotFoundException, UnknownHostException, SocketException{
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        int result=0;
        try{  
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();        
            
            PreparedStatement queryActualizarMvtoCarbon= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_carbon] SET [mc_estad_mvto_carbon_cdgo]=1 WHERE [mc_cdgo]=?");
            queryActualizarMvtoCarbon.setString(1,mvtoEquipoCarbon.getMvtoCarbon().getCodigo());
            queryActualizarMvtoCarbon.execute();
            result=1;
            if(result==1){
                PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_equipo] SET [me_inactividad]=0,[me_causa_inactividad_cdgo]=NULL,[me_usuario_inactividad_cdgo]=NULL, [me_estado]=1 WHERE [me_cdgo]=?");
                queryActualizar.setString(1,mvtoEquipoCarbon.getMvtoEquipo().getCodigo());
                queryActualizar.execute();
                result=1;
                if(result==1){
                    result=0;
                    //Extraemos el nombre del Equipo y la IP        
                    String namePc=new ControlDB_Config().getNamePC();
                    String ipPc=new ControlDB_Config().getIpPc();
                    String macPC=new ControlDB_Config().getMacAddress();
                    PreparedStatement Query_Auditoria= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[auditoria]([au_cdgo]\n" +
                                            "      ,[au_fecha]\n" +
                                            "      ,[au_usuario_cdgo_registro]\n" +
                                            "      ,[au_nombre_dispositivo_registro]\n" +
                                            "      ,[au_ip_dispositivo_registro]\n" +
                                            "      ,[au_mac_dispositivo_registro]\n" +
                                            "      ,[au_cdgo_mtvo]\n" +
                                            "      ,[au_desc_mtvo]\n" +
                                            "      ,[au_detalle_mtvo])\n" +
                            "     VALUES("+
                            "           (SELECT (CASE WHEN (MAX([au_cdgo]) IS NULL) THEN 1 ELSE (MAX([au_cdgo])+1) END)AS [au_cdgo] FROM ["+DB+"].[dbo].[auditoria])"+
                            "           ,(SELECT SYSDATETIME())"+
                            "           ,?"+
                            "           ,?"+
                            "           ,?"+
                            "           ,?"+
                            "           ,?"+
                            "           ,'DESCARGUE_CARBON'" +
                            "           ,CONCAT('Se activo un movimiento de un equipo en el sistema de Código:',?,' Descripción: ',?,' Modelo: ',?,' Razon de activación: ',?));"); 
                    Query_Auditoria.setString(1, us.getCodigo());
                    Query_Auditoria.setString(2, namePc);
                    Query_Auditoria.setString(3, ipPc);
                    Query_Auditoria.setString(4, macPC);
                    Query_Auditoria.setString(5, mvtoEquipoCarbon.getMvtoEquipo().getCodigo());
                    Query_Auditoria.setString(6, mvtoEquipoCarbon.getMvtoEquipo().getCodigo());
                    Query_Auditoria.setString(7, mvtoEquipoCarbon.getMvtoEquipo().getAsignacionEquipo().getEquipo().getDescripcion());
                    Query_Auditoria.setString(8, mvtoEquipoCarbon.getMvtoEquipo().getAsignacionEquipo().getEquipo().getModelo()+" Marca:"+mvtoEquipoCarbon.getMvtoEquipo().getAsignacionEquipo().getEquipo().getMarca());
                    Query_Auditoria.setString(9,observacion);
                    Query_Auditoria.execute();
                    result=1;
                } 
            }
        }
               
        catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "ERROR al querer insertar los datos.");
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    }
    public ArrayList<Recobro>                       listarRecobro() throws SQLException{
        ArrayList<Recobro> listadoRecobro =null;
        Conexion_DB_costos_vg control = new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        conexion= control.ConectarBaseDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[recobro] WHERE [rc_cdgo] <> 2;");
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            boolean validar=true;
            while(resultSetBuscar.next()){
                if(validar){
                    listadoRecobro = new ArrayList();
                    validar=false;
                }
                Recobro rec = new Recobro();
                rec.setCodigo(resultSetBuscar.getString(1));
                rec.setDescripcion(resultSetBuscar.getString(2));
                rec.setEstado(resultSetBuscar.getString(3));
                listadoRecobro.add(rec);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return listadoRecobro; }
    public ArrayList<Recobro>                       listarRecobroTodos() throws SQLException{
        ArrayList<Recobro> listadoRecobro =null;
        Conexion_DB_costos_vg control = new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        conexion= control.ConectarBaseDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[recobro];");
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            boolean validar=true;
            while(resultSetBuscar.next()){
                if(validar){
                    listadoRecobro = new ArrayList();
                    validar=false;
                }
                Recobro rec = new Recobro();
                rec.setCodigo(resultSetBuscar.getString(1));
                rec.setDescripcion(resultSetBuscar.getString(2));
                rec.setEstado(resultSetBuscar.getString(3));
                listadoRecobro.add(rec);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return listadoRecobro; 
    }
    public ArrayList<TipoEquipo>                    buscarTipoEquiposProgramados(String fechaRegistro) throws SQLException {
        ArrayList<TipoEquipo> listadoTipoEquipo = null;
        Conexion_DB_costos_vg control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet;
            System.out.println("SELECT DISTINCT eq_tipo_equipo.[te_desc]  FROM ["+DB+"].[dbo].[asignacion_equipo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[solicitud_listado_equipo] ON [ae_solicitud_listado_equipo_cdgo]=[sle_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_oper] ae_cntro_oper ON [ae_cntro_oper_cdgo]=ae_cntro_oper.[co_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[solicitud_equipo] ON [sle_solicitud_equipo_cdgo]=[se_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_oper] se_cntro_oper ON [se_cntro_oper_cdgo]=se_cntro_oper.[co_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[labor_realizada] ON [sle_labor_realizada_cdgo]=[lr_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [sle_cntro_cost_auxiliar_cdgo]=[cca_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ae_cntro_cost_subcentro ON [cca_cntro_cost_subcentro_cdgo]=ae_cntro_cost_subcentro.[ccs_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[tipo_equipo] eq_tipo_equipo ON [eq_tipo_equipo_cdgo]=eq_tipo_equipo.[te_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [eq_proveedor_equipo_cdgo]=[pe_cdgo] \n" +
                    "    WHERE ([se_estado_solicitud_equipo_cdgo]=3 OR [se_estado_solicitud_equipo_cdgo]=4) AND [se_confirmacion_solicitud_equipo_cdgo]=1 AND\n" +
                    "        '"+fechaRegistro+"' BETWEEN [ae_fecha_hora_inicio] AND [ae_fecha_hora_fin] AND [ae_estad]=1");
            PreparedStatement query= conexion.prepareStatement("SELECT DISTINCT eq_tipo_equipo.[te_desc]  FROM ["+DB+"].[dbo].[asignacion_equipo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[solicitud_listado_equipo] ON [ae_solicitud_listado_equipo_cdgo]=[sle_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_oper] ae_cntro_oper ON [ae_cntro_oper_cdgo]=ae_cntro_oper.[co_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[solicitud_equipo] ON [sle_solicitud_equipo_cdgo]=[se_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_oper] se_cntro_oper ON [se_cntro_oper_cdgo]=se_cntro_oper.[co_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[labor_realizada] ON [sle_labor_realizada_cdgo]=[lr_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [sle_cntro_cost_auxiliar_cdgo]=[cca_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ae_cntro_cost_subcentro ON [cca_cntro_cost_subcentro_cdgo]=ae_cntro_cost_subcentro.[ccs_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[tipo_equipo] eq_tipo_equipo ON [eq_tipo_equipo_cdgo]=eq_tipo_equipo.[te_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [eq_proveedor_equipo_cdgo]=[pe_cdgo] \n" +
                    "    WHERE ([se_estado_solicitud_equipo_cdgo]=3 OR [se_estado_solicitud_equipo_cdgo]=4) AND [se_confirmacion_solicitud_equipo_cdgo]=1 AND\n" +
                    "        '"+fechaRegistro+"' BETWEEN [ae_fecha_hora_inicio] AND [ae_fecha_hora_fin] AND [ae_estad]=1");
                    //"        (SELECT CONVERT (DATETIME,(SELECT SYSDATETIME()))) BETWEEN [ae_fecha_hora_inicio] AND [ae_fecha_hora_fin] AND [ae_estad]=1");
            resultSet= query.executeQuery();
            boolean valor=true;
            while(resultSet.next()){
                if(valor) {
                    listadoTipoEquipo= new ArrayList<>();
                    valor=false;
                }
                TipoEquipo tipoEquipo = new TipoEquipo();
                tipoEquipo.setDescripcion(resultSet.getString(1));
                listadoTipoEquipo.add(tipoEquipo);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        //System.out.println(""+asignacionEquipo.getEquipo().getCodigo());
        return listadoTipoEquipo;
    }
    public ArrayList<String>                        buscarMarcaEquiposProgramados(String fechaRegistro, String tipoEquipo) throws SQLException {
        ArrayList<String> listadoMarcaEquipo = null;
        Conexion_DB_costos_vg control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet;
            System.out.println("SELECT DISTINCT [eq_marca]  FROM ["+DB+"].[dbo].[asignacion_equipo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[solicitud_listado_equipo] ON [ae_solicitud_listado_equipo_cdgo]=[sle_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_oper] ae_cntro_oper ON [ae_cntro_oper_cdgo]=ae_cntro_oper.[co_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[solicitud_equipo] ON [sle_solicitud_equipo_cdgo]=[se_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_oper] se_cntro_oper ON [se_cntro_oper_cdgo]=se_cntro_oper.[co_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[labor_realizada] ON [sle_labor_realizada_cdgo]=[lr_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [sle_cntro_cost_auxiliar_cdgo]=[cca_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ae_cntro_cost_subcentro ON [cca_cntro_cost_subcentro_cdgo]=ae_cntro_cost_subcentro.[ccs_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[tipo_equipo] eq_tipo_equipo ON [eq_tipo_equipo_cdgo]=eq_tipo_equipo.[te_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [eq_proveedor_equipo_cdgo]=[pe_cdgo] \n" +
                    "    WHERE ([se_estado_solicitud_equipo_cdgo]=3 OR [se_estado_solicitud_equipo_cdgo]=4) AND [se_confirmacion_solicitud_equipo_cdgo]=1 AND\n" +
                    "        '"+fechaRegistro+"' BETWEEN [ae_fecha_hora_inicio] AND [ae_fecha_hora_fin] AND [ae_estad]=1 AND eq_tipo_equipo.[te_desc] LIKE '"+tipoEquipo+"'");
            PreparedStatement query= conexion.prepareStatement("SELECT DISTINCT [eq_marca]  FROM ["+DB+"].[dbo].[asignacion_equipo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[solicitud_listado_equipo] ON [ae_solicitud_listado_equipo_cdgo]=[sle_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_oper] ae_cntro_oper ON [ae_cntro_oper_cdgo]=ae_cntro_oper.[co_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[solicitud_equipo] ON [sle_solicitud_equipo_cdgo]=[se_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_oper] se_cntro_oper ON [se_cntro_oper_cdgo]=se_cntro_oper.[co_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[labor_realizada] ON [sle_labor_realizada_cdgo]=[lr_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [sle_cntro_cost_auxiliar_cdgo]=[cca_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ae_cntro_cost_subcentro ON [cca_cntro_cost_subcentro_cdgo]=ae_cntro_cost_subcentro.[ccs_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[tipo_equipo] eq_tipo_equipo ON [eq_tipo_equipo_cdgo]=eq_tipo_equipo.[te_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [eq_proveedor_equipo_cdgo]=[pe_cdgo] \n" +
                    "    WHERE ([se_estado_solicitud_equipo_cdgo]=3 OR [se_estado_solicitud_equipo_cdgo]=4) AND [se_confirmacion_solicitud_equipo_cdgo]=1 AND\n" +
                    "        '"+fechaRegistro+"' BETWEEN [ae_fecha_hora_inicio] AND [ae_fecha_hora_fin] AND [ae_estad]=1 AND eq_tipo_equipo.[te_desc] LIKE ?");
            query.setString(1, tipoEquipo);
            resultSet= query.executeQuery();
            boolean valor=true;
            while(resultSet.next()){
                if(valor) {
                    listadoMarcaEquipo= new ArrayList<>();
                    valor=false;
                }
                listadoMarcaEquipo.add(resultSet.getString(1));
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        //System.out.println(""+asignacionEquipo.getEquipo().getCodigo());
        return listadoMarcaEquipo;
    }
    public ArrayList<AsignacionEquipo>              buscarAsignacionEquipos_Por_TipoEquipo_MarcaEquipo(String fechaRegistro,String tipoEquipo, String marcaEquipo) throws SQLException {
        ArrayList<AsignacionEquipo> listadoAsignacion = null;
        Conexion_DB_costos_vg control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet;
            System.out.println("SELECT \n" +
                    "    [ae_cdgo]-- 1 \n" +
                    "\t,ae_cntro_oper.[co_cdgo]-- 2 \n" +
                    "    ,ae_cntro_oper.[co_desc]-- 3 \n" +
                    "\t,ae_cntro_cost_subcentro.[ccs_cdgo]-- 4\n" +
                    "    ,ae_cntro_cost_subcentro.[ccs_desc]-- 5\n" +
                    "\t,[cca_cdgo]-- 6\n" +
                    "\t,[cca_desc]-- 7\n" +
                    "\t,[ae_fecha_hora_inicio]-- 8\n" +
                    "    ,[ae_fecha_hora_fin]-- 9\n" +
                    "    ,[ae_cant_minutos]-- 10 \n" +
                    "\t,[eq_cdgo]-- 11 \n" +
                    "\t,eq_tipo_equipo.[te_cdgo]-- 12 \n" +
                    "    ,eq_tipo_equipo.[te_desc]-- 13\n" +
                    "\t,[eq_marca]-- 14\n" +
                    "    ,[eq_modelo]-- 15 \n" +
                    "\t,[eq_desc]-- 16 \n" +
                    "\t,[pe_cdgo]-- 17 \n" +
                    "    ,[pe_desc]-- 18\n" +
                    " FROM ["+DB+"].[dbo].[asignacion_equipo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[solicitud_listado_equipo] ON [ae_solicitud_listado_equipo_cdgo]=[sle_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_oper] ae_cntro_oper ON [ae_cntro_oper_cdgo]=ae_cntro_oper.[co_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[solicitud_equipo] ON [sle_solicitud_equipo_cdgo]=[se_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_oper] se_cntro_oper ON [se_cntro_oper_cdgo]=se_cntro_oper.[co_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[labor_realizada] ON [sle_labor_realizada_cdgo]=[lr_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [sle_cntro_cost_auxiliar_cdgo]=[cca_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ae_cntro_cost_subcentro ON [cca_cntro_cost_subcentro_cdgo]=ae_cntro_cost_subcentro.[ccs_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[tipo_equipo] eq_tipo_equipo ON [eq_tipo_equipo_cdgo]=eq_tipo_equipo.[te_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [eq_proveedor_equipo_cdgo]=[pe_cdgo] \n" +
                    "    WHERE ([se_estado_solicitud_equipo_cdgo]=3 OR [se_estado_solicitud_equipo_cdgo]=4) AND [se_confirmacion_solicitud_equipo_cdgo]=1 AND\n" +
                    "         '"+fechaRegistro+"' BETWEEN [ae_fecha_hora_inicio] AND [ae_fecha_hora_fin] AND [ae_estad]=1  AND eq_tipo_equipo.[te_desc] LIKE '"+tipoEquipo+"' AND [eq_marca] LIKE'"+marcaEquipo+"'");
            PreparedStatement query= conexion.prepareStatement("SELECT \n" +
                    "    [ae_cdgo]-- 1 \n" +
                    "\t,ae_cntro_oper.[co_cdgo]-- 2 \n" +
                    "    ,ae_cntro_oper.[co_desc]-- 3 \n" +
                    "\t,ae_cntro_cost_subcentro.[ccs_cdgo]-- 4\n" +
                    "    ,ae_cntro_cost_subcentro.[ccs_desc]-- 5\n" +
                    "\t,[cca_cdgo]-- 6\n" +
                    "\t,[cca_desc]-- 7\n" +
                    "\t,[ae_fecha_hora_inicio]-- 8\n" +
                    "    ,[ae_fecha_hora_fin]-- 9\n" +
                    "    ,[ae_cant_minutos]-- 10 \n" +
                    "\t,[eq_cdgo]-- 11 \n" +
                    "\t,eq_tipo_equipo.[te_cdgo]-- 12 \n" +
                    "    ,eq_tipo_equipo.[te_desc]-- 13\n" +
                    "\t,[eq_marca]-- 14\n" +
                    "    ,[eq_modelo]-- 15 \n" +
                    "\t,[eq_desc]-- 16 \n" +
                    "\t,[pe_cdgo]-- 17 \n" +
                    "    ,[pe_desc]-- 18\n" +
                    " FROM ["+DB+"].[dbo].[asignacion_equipo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[solicitud_listado_equipo] ON [ae_solicitud_listado_equipo_cdgo]=[sle_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_oper] ae_cntro_oper ON [ae_cntro_oper_cdgo]=ae_cntro_oper.[co_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[solicitud_equipo] ON [sle_solicitud_equipo_cdgo]=[se_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_oper] se_cntro_oper ON [se_cntro_oper_cdgo]=se_cntro_oper.[co_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[labor_realizada] ON [sle_labor_realizada_cdgo]=[lr_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [sle_cntro_cost_auxiliar_cdgo]=[cca_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ae_cntro_cost_subcentro ON [cca_cntro_cost_subcentro_cdgo]=ae_cntro_cost_subcentro.[ccs_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[tipo_equipo] eq_tipo_equipo ON [eq_tipo_equipo_cdgo]=eq_tipo_equipo.[te_cdgo] \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [eq_proveedor_equipo_cdgo]=[pe_cdgo] \n" +
                    "    WHERE ([se_estado_solicitud_equipo_cdgo]=3 OR [se_estado_solicitud_equipo_cdgo]=4) AND [se_confirmacion_solicitud_equipo_cdgo]=1 AND\n" +
                    "         '"+fechaRegistro+"' BETWEEN [ae_fecha_hora_inicio] AND [ae_fecha_hora_fin] AND [ae_estad]=1  AND eq_tipo_equipo.[te_desc] LIKE ? AND [eq_marca] LIKE ?");
            query.setString(1, tipoEquipo);
            query.setString(2, marcaEquipo);
            resultSet= query.executeQuery();
            boolean valor=true;
            while(resultSet.next()){
                if(valor) {
                    listadoAsignacion= new ArrayList<>();
                    valor=false;
                }
                AsignacionEquipo asignacionEquipo=new AsignacionEquipo();
                asignacionEquipo.setCodigo(resultSet.getString(1));
                CentroOperacion CentroOperacionAsignacion= new CentroOperacion();
                CentroOperacionAsignacion.setCodigo(Integer.parseInt(resultSet.getString(2)));
                CentroOperacionAsignacion.setDescripcion(resultSet.getString(3));
                asignacionEquipo.setCentroOperacion(CentroOperacionAsignacion);
                SolicitudListadoEquipo solicitudListadoEquipo = new SolicitudListadoEquipo();
                CentroCostoSubCentro centroCostoSubCentro = new CentroCostoSubCentro();
                centroCostoSubCentro.setCodigo(resultSet.getInt(4));
                centroCostoSubCentro.setDescripcion(resultSet.getString(5));
                CentroCostoAuxiliar centroCostoAuxiliar = new CentroCostoAuxiliar();
                centroCostoAuxiliar.setCodigo(resultSet.getInt(6));
                centroCostoAuxiliar.setDescripcion(resultSet.getString(7));
                centroCostoAuxiliar.setCentroCostoSubCentro(centroCostoSubCentro);
                solicitudListadoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar);
                asignacionEquipo.setSolicitudListadoEquipo(solicitudListadoEquipo);
                asignacionEquipo.setFechaHoraInicio(resultSet.getString(8));
                asignacionEquipo.setFechaHoraFin(resultSet.getString(9));
                asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(10));
                Equipo equipo = new Equipo();
                equipo.setCodigo(resultSet.getString(11));
                equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(12),resultSet.getString(13),""));
                equipo.setMarca(resultSet.getString(14));
                equipo.setModelo(resultSet.getString(15));
                equipo.setDescripcion(resultSet.getString(16));
                ProveedorEquipo provEquipo = new ProveedorEquipo();
                provEquipo.setCodigo(resultSet.getString(17));
                provEquipo.setDescripcion(resultSet.getString(18));
                equipo.setProveedorEquipo(provEquipo);
                asignacionEquipo.setEquipo(equipo);
                listadoAsignacion.add(asignacionEquipo);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        //System.out.println(""+asignacionEquipo.getEquipo().getCodigo());
        return listadoAsignacion;
    }
    public ArrayList<MvtoEquipo>                    buscar_MvtoEquipoActivosInformeGeneral(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<MvtoEquipo> listadoObjetos = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT\t [me_cdgo]   -- 1\n" +
                    "\t\t\t,[me_asignacion_equipo_cdgo]   --2\n" +
                    "\t\t\t,[ae_cdgo] --3\n" +
                    "\t\t\t\t,ae_cntro_oper_cdgo --4\n" +
                    "\t\t\t\t\t--Centro Operacion\n" +
                    "\t\t\t\t\t,ae_co_cdgo --5\n" +
                    "\t\t\t\t\t,ae_co_desc --6\n" +
                    "\t\t\t\t\t,CASE ae_co_estado WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_co_estado --7\n" +
                    "\t\t\t\t,ae_solicitud_listado_equipo_cdgo --8\n" +
                    "\t\t\t\t\t-- Solicitud Listado Equipo\n" +
                    "\t\t\t\t\t,ae_sle_cdgo --9\n" +
                    "\t\t\t\t\t,ae_sle_solicitud_equipo_cdgo --10\n" +
                    "\t\t\t\t\t\t\t--Solicitud Equipo\n" +
                    "\t\t\t\t\t\t\t,ae_sle_se_cdgo --11\n" +
                    "\t\t\t\t\t\t\t,ae_sle_se_cntro_oper_cdgo --12\n" +
                    "\t\t\t\t\t\t\t\t--CentroOperación SolicitudEquipo\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_co_cdgo --13\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_co_desc --14\n" +
                    "\t\t\t\t\t\t\t\t,CASE ae_sle_se_co_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_co_estad --15\n" +
                    "\t\t\t\t\t\t\t,ae_sle_se_fecha --16\n" +
                    "\t\t\t\t\t\t\t,ae_sle_se_usuario_realiza_cdgo --17\n" +
                    "\t\t\t\t\t\t\t\t--Usuario SolicitudEquipo\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_registra_cdgo --18\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_registra_clave --19\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_registra_nombres --20\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_registra_apellidos --21\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_registra_perfil_cdgo --22\n" +
                    "\t\t\t\t\t\t\t\t\t\t--Perfil Usuario Quien Registra Manual\n" +
                    "\t\t\t\t\t\t\t\t\t\t,ae_sle_se_prf_us_registra_cdgo --23\n" +
                    "\t\t\t\t\t\t\t\t\t\t,ae_sle_se_prf_us_registra_desc --24\n" +
                    "\t\t\t\t\t\t\t\t\t\t,CASE ae_sle_se_prf_us_registra_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_prf_us_registra_estad --25\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_registra_correo --26\n" +
                    "\t\t\t\t\t\t\t\t,CASE ae_sle_se_us_registra_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_us_registra_estad --27\n" +
                    "\t\t\t\t\t\t\t,ae_sle_se_fecha_registro --28\n" +
                    "\t\t\t\t\t\t\t,ae_sle_se_estado_solicitud_equipo_cdgo --29\n" +
                    "\t\t\t\t\t\t\t\t--Estado de la solicitud\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_ese_cdgo --30\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_ese_desc --31\n" +
                    "\t\t\t\t\t\t\t\t,CASE ae_sle_se_ese_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_ese_estad --32\n" +
                    "\t\t\t\t\t\t\t,ae_sle_se_fecha_confirmacion --33\n" +
                    "\t\t\t\t\t\t\t,ae_se_usuario_confirma_cdgo --34\n" +
                    "\t\t\t\t\t\t\t\t--Usuario SolicitudEquipo\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_confirma_cdgo --35\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_confirma_clave --36\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_confirma_nombres --37\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_confirma_apellidos --38\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_confirma_perfil_cdgo --39\n" +
                    "\t\t\t\t\t\t\t\t\t\t--Perfil Usuario Quien Registra Manual\n" +
                    "\t\t\t\t\t\t\t\t\t\t,ae_sle_se_prf_us_confirma_cdgo --40\n" +
                    "\t\t\t\t\t\t\t\t\t\t,ae_sle_se_prf_us_confirma_desc --41\n" +
                    "\t\t\t\t\t\t\t\t\t\t,CASE ae_sle_se_prf_us_confirma_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_prf_us_confirma_estad --42\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_confirma_correo --43\n" +
                    "\t\t\t\t\t\t\t\t,CASE ae_sle_se_us_confirma_estado WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_us_confirma_estado --44\n" +
                    "\t\t\t\t\t\t\t,ae_sle_se_confirmacion_solicitud_equipo_cdgo --45\n" +
                    "\t\t\t\t\t\t\t\t--Confirmacion solicitudEquipo\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_cse_cdgo --46\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_ces_desc --47\n" +
                    "\t\t\t\t\t\t\t\t,CASE ae_sle_se_ces_estado WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_ces_estado --48\n" +
                    "\t\t\t\t\t,ae_sle_tipo_equipo_cdgo --49\n" +
                    "\t\t\t\t\t\t--Tipo de Equipo\n" +
                    "\t\t\t\t\t\t,ae_sle_te_cdgo --50\n" +
                    "\t\t\t\t\t\t,ae_sle_te_desc --51\n" +
                    "\t\t\t\t\t\t,CASE ae_sle_te_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_te_estad --52\n" +
                    "\t\t\t\t\t,ae_sle_marca_equipo --53\n" +
                    "\t\t\t\t\t,ae_sle_modelo_equipo --54\n" +
                    "\t\t\t\t\t,ae_sle_cant_equip --55\n" +
                    "\t\t\t\t\t,ae_sle_observ --56\n" +
                    "\t\t\t\t\t,ae_sle_fecha_hora_inicio --57\n" +
                    "\t\t\t\t\t,ae_sle_fecha_hora_fin --58\n" +
                    "\t\t\t\t\t,ae_sle_cant_minutos --59\n" +
                    "\t\t\t\t\t,ae_sle_labor_realizada_cdgo --60\n" +
                    "\t\t\t\t\t-- Labor Realizada\n" +
                    "\t\t\t\t\t\t\t,ae_sle_lr_cdgo --61\n" +
                    "\t\t\t\t\t\t\t,ae_sle_lr_desc --62\n" +
                    "\t\t\t\t\t\t\t,CASE ae_sle_lr_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_ao_estad --63\n" +
                    "\t\t\t\t\t,ae_sle_sle_motonave_cdgo --64\n" +
                    "\t\t\t\t\t--Motonave\n" +
                    "\t\t\t\t\t\t\t,ae_sle_mn_cdgo --65\n" +
                    "\t\t\t\t\t\t\t,ae_sle_mn_desc --66\n" +
                    "\t\t\t\t\t\t\t,CASE ae_sle_mn_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_mn_estad --67\n" +
                    "\t\t\t\t\t,ae_sle_cntro_cost_auxiliar_cdgo --68\n" +
                    "\t\t\t\t\t\t--Centro Costo Auxiliar\n" +
                    "\t\t\t\t\t\t,ae_sle_cca_cdgo --69\n" +
                    "\t\t\t\t\t\t,ae_sle_cca_cntro_cost_subcentro_cdgo --70\n" +
                    "\t\t\t\t\t\t\t-- SubCentro de Costo\n" +
                    "\t\t\t\t\t\t\t,ae_sle_ccs_cdgo --71\n" +
                    "\t\t\t\t\t\t\t,ae_sle_ccs_desc --72\n" +
                    "\t\t\t\t\t\t\t,CASE ae_sle_ccs_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_ccs_estad --73\n" +
                    "\t\t\t\t\t\t,ae_sle_cca_desc --74\n" +
                    "\t\t\t\t\t\t,CASE ae_sle_cca_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_cca_estad --75\n" +
                    "\t\t\t\t\t,ae_sle_compania_cdgo --76\n" +
                    "\t\t\t\t\t\t--Compañia\n" +
                    "\t\t\t\t\t\t,ae_sle_cp_cdgo --77\n" +
                    "\t\t\t\t\t\t,ae_sle_cp_desc --78\n" +
                    "\t\t\t\t\t\t,CASE ae_sle_cp_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_cp_estad --79\n" +
                    "\t\t\t\t,ae_fecha_registro --80\n" +
                    "\t\t\t\t,ae_fecha_hora_inicio --81\n" +
                    "\t\t\t\t,ae_fecha_hora_fin --82\n" +
                    "\t\t\t\t,ae_cant_minutos --83\n" +
                    "\t\t\t\t,ae_equipo_cdgo --84\n" +
                    "\t\t\t\t\t--Equipo\n" +
                    "\t\t\t\t\t,ae_eq_cdgo --85\n" +
                    "\t\t\t\t\t,ae_eq_tipo_equipo_cdgo --86\n" +
                    "\t\t\t\t\t\t--Tipo Equipo\n" +
                    "\t\t\t\t\t\t,ae_eq_te_cdgo --87\n" +
                    "\t\t\t\t\t\t,ae_eq_te_desc --88\n" +
                    "\t\t\t\t\t\t,CASE ae_eq_te_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_te_estad --89\n" +
                    "\t\t\t\t\t,ae_eq_codigo_barra --90\n" +
                    "\t\t\t\t\t,ae_eq_referencia --91\n" +
                    "\t\t\t\t\t,ae_eq_producto --92\n" +
                    "\t\t\t\t\t,ae_eq_capacidad --93\n" +
                    "\t\t\t\t\t,ae_eq_marca --94\n" +
                    "\t\t\t\t\t,ae_eq_modelo --95\n" +
                    "\t\t\t\t\t,ae_eq_serial --96\n" +
                    "\t\t\t\t\t,ae_eq_desc --97\n" +
                    "\t\t\t\t\t,ae_eq_clasificador1_cdgo --98\n" +
                    "\t\t\t\t\t\t-- Clasificador 1\n" +
                    "\t\t\t\t\t\t,ae_eq_ce1_cdgo --99\n" +
                    "\t\t\t\t\t\t,ae_eq_ce1_desc --100\n" +
                    "\t\t\t\t\t\t,CASE ae_eq_ce1_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_ce1_estad --101\n" +
                    "\t\t\t\t\t,ae_eq_clasificador2_cdgo --102\n" +
                    "\t\t\t\t\t\t-- Clasificador 2\n" +
                    "\t\t\t\t\t\t,ae_eq_ce2_cdgo --103\n" +
                    "\t\t\t\t\t\t,ae_eq_ce2_desc --104\n" +
                    "\t\t\t\t\t\t,CASE ae_eq_ce2_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_ce2_estad --105\n" +
                    "\t\t\t\t\t,ae_eq_proveedor_equipo_cdgo --106\n" +
                    "\t\t\t\t\t\t--Proveedor Equipo\n" +
                    "\t\t\t\t\t\t,ae_eq_pe_cdgo --107\n" +
                    "\t\t\t\t\t\t,ae_eq_pe_nit --108\n" +
                    "\t\t\t\t\t\t,ae_eq_pe_desc --109\n" +
                    "\t\t\t\t\t\t,CASE ae_eq_pe_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_pe_estad --110\n" +
                    "\t\t\t\t\t,ae_eq_equipo_pertenencia_cdgo --111\n" +
                    "\t\t\t\t\t\t-- Equipo Pertenencia\n" +
                    "\t\t\t\t\t\t,ae_eq_ep_cdgo --112\n" +
                    "\t\t\t\t\t\t,ae_eq_ep_desc --113\n" +
                    "\t\t\t\t\t\t,CASE ae_eq_ep_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_ep_estad --114\n" +
                    "\t\t\t\t\t,ae_eq_eq_observ --115\n" +
                    "\t\t\t\t\t,CASE ae_eq_eq_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_eq_estad --116\n" +
                    "\t\t\t\t\t,ae_eq_actvo_fijo_id --117\n" +
                    "\t\t\t\t\t,ae_eq_actvo_fijo_referencia --118\n" +
                    "\t\t\t\t\t,ae_eq_actvo_fijo_desc --119\n" +
                    "\t\t\t\t,ae_equipo_pertenencia_cdgo --120\n" +
                    "\t\t\t\t-- Equipo Pertenencia\n" +
                    "\t\t\t\t\t\t,ae_ep_cdgo --121\n" +
                    "\t\t\t\t\t\t,ae_ep_desc --122\n" +
                    "\t\t\t\t\t\t,CASE ae_ep_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_ep_estad --123\n" +
                    "\t\t\t\t,ae_cant_hora_operativa --124\n" +
                    "\t\t\t\t,ae_cant_hora_parada --125\n" +
                    "\t\t\t\t,ae_cant_hora_total --126\n" +
                    "\t\t\t\t,CASE ae_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_estad --127\n" +
                    "\t\t\t,[me_fecha]   --128\n" +
                    "\t\t\t,[me_proveedor_equipo_cdgo]   --129\n" +
                    "\t\t\t\t--Proveedor Equipo\n" +
                    "\t\t\t\t,[pe_cdgo] AS me_pe_cdgo   --130\n" +
                    "\t\t\t\t,[pe_nit] AS me_pe_nit   --131\n" +
                    "\t\t\t\t,[pe_desc] AS me_pe_desc   --132\n" +
                    "\t\t\t\t,CASE [pe_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS me_pe_estad   --133\n" +
                    "\t\t\t,[me_num_orden]   --134\n" +
                    "\t\t\t,[me_cntro_cost_auxiliar_cdgo]   --135\n" +
                    "\t\t\t\t-- Centro Costo Auxiliar\n" +
                    "\t\t\t\t,me_cntro_cost_auxiliar.[cca_cdgo] AS me_cca_cdgo   --136\n" +
                    "\t\t\t\t,me_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo] AS me_cca_cntro_cost_subcentro_cdgo   --137\n" +
                    "\t\t\t\t\t--Centro Costo Subcentro\n" +
                    "\t\t\t\t\t,me_cntro_cost_subcentro.[ccs_cdgo]   --138\n" +
                    "\t\t\t\t\t,me_cntro_cost_subcentro.[ccs_desc]   --139\n" +
                    "\t\t\t\t\t,CASE me_cntro_cost_subcentro.[ccs_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ccs_estad]   --140\n" +
                    "\t\t\t\t,me_cntro_cost_auxiliar.[cca_desc] AS me_cca_desc   --141\n" +
                    "\t\t\t\t,CASE me_cntro_cost_auxiliar.[cca_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cca_estad]   --142\n" +
                    "\t\t\t,[me_labor_realizada_cdgo]   --143\n" +
                    "\t\t\t\t--Labor Realizada\n" +
                    "\t\t\t\t,[lr_cdgo] --144\n" +
                    "\t\t\t\t,[lr_desc] --145\n" +
                    "\t\t\t\t,[lr_cntro_cost_subcentro_cdgo] --146\n" +
                    "\t\t\t\t,CASE [lr_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [lr_estad]   --147\n" +
                    "\t\t\t\t,[lr_operativa] --148\n" +
                    "\t\t\t\t,[lr_parada] --149\n" +
                    "\t\t\t,[me_cliente_cdgo]   --150\n" +
                    "\t\t\t\t--Cliente \n" +
                    "\t\t\t\t,me_cliente.[cl_cdgo]   --151\n" +
                    "\t\t\t\t,me_cliente.[cl_desc]   --152\n" +
                    "\t\t\t\t,CASE me_cliente.[cl_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cl_estad]   --153\n" +
                    "\t\t\t,[me_articulo_cdgo]   --154\n" +
                    "\t\t\t\t--Producto\n" +
                    "\t\t\t\t,[ar_cdgo]   --155\n" +
                    "\t\t\t\t,[ar_desc]   --156\n" +
                    "\t\t\t\t,CASE [ar_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ar_estad]   --157\n" +
                    "\t\t\t,[me_fecha_hora_inicio]   --158\n" +
                    "\t\t\t,[me_fecha_hora_fin]   --159\n" +
                    "\t\t\t,[me_total_minutos]   --160\n" +
                    "\t\t\t,[me_valor_hora]   --161\n" +
                    "\t\t\t,[me_costo_total]   --162\n" +
                    "\t\t\t,[me_recobro_cdgo]   --163\n" +
                    "\t\t\t\t--Recobro\n" +
                    "\t\t\t\t,[rc_cdgo]   --164\n" +
                    "\t\t\t\t,[rc_desc]   --165\n" +
                    "\t\t\t\t,CASE [rc_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [rc_estad]   --166\n" +
                    "\t\t\t,[me_cliente_recobro_cdgo]   --167\n" +
                    "\t\t\t\t--Cliente Recobro\n" +
                    "\t\t\t\t,[clr_cdgo]   --168\n" +
                    "\t\t\t\t,[clr_cliente_cdgo]   --169\n" +
                    "\t\t\t\t\t--Cliente\n" +
                    "\t\t\t\t\t,me_clr_cliente.[cl_cdgo]   --170   \n" +
                    "\t\t\t\t\t,me_clr_cliente.[cl_desc]   --171\n" +
                    "\t\t\t\t\t,CASE me_clr_cliente.[cl_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cl_estad]   --172\n" +
                    "\t\t\t\t,[clr_usuario_cdgo]   --173\n" +
                    "\t\t\t\t\t--Usuario Quien Registra Recobro\n" +
                    "\t\t\t\t\t,me_clr_usuario.[us_cdgo]   --174\n" +
                    "\t\t\t\t\t,me_clr_usuario.[us_clave]   --175\n" +
                    "\t\t\t\t\t,me_clr_usuario.[us_nombres]   --176\n" +
                    "\t\t\t\t\t,me_clr_usuario.[us_apellidos]   --177\n" +
                    "\t\t\t\t\t,me_clr_usuario.[us_perfil_cdgo]   --178\n" +
                    "\t\t\t\t\t\t--Perfil Usuario Registra recobro\n" +
                    "\t\t\t\t\t\t,me_clr_us_perfil.[prf_cdgo]   --179\n" +
                    "\t\t\t\t\t\t,me_clr_us_perfil.[prf_desc]   --180\n" +
                    "\t\t\t\t\t\t,CASE me_clr_us_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad]   --181\n" +
                    "\t\t\t\t\t,me_clr_usuario.[us_correo]   --182\n" +
                    "\t\t\t\t\t,CASE me_clr_usuario.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad]   --183\n" +
                    "\t\t\t\t,[clr_valor_recobro]   --184\n" +
                    "\t\t\t\t,[clr_fecha_registro]   --185\n" +
                    "\t\t\t,[me_costo_total_recobro_cliente]   --186\n" +
                    "\t\t\t,[me_usuario_registro_cdgo]   --187\n" +
                    "\t\t\t\t--Usuario Quien Registra Equipo\n" +
                    "\t\t\t\t,me_us_registro.[us_cdgo]   --188\n" +
                    "\t\t\t\t,me_us_registro.[us_clave]   --189\n" +
                    "\t\t\t\t,me_us_registro.[us_nombres]   --190\n" +
                    "\t\t\t\t,me_us_registro.[us_apellidos]   --191\n" +
                    "\t\t\t\t,me_us_registro.[us_perfil_cdgo]   --192\n" +
                    "\t\t\t\t\t--Perfil de Usuario Quien Registra Equipo\n" +
                    "\t\t\t\t\t,me_us_regist_perfil.[prf_cdgo]   --193\n" +
                    "\t\t\t\t\t,me_us_regist_perfil.[prf_desc]   --194\n" +
                    "\t\t\t\t\t,CASE me_us_regist_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad]   --195\n" +
                    "\t\t\t\t,me_us_registro.[us_correo]   --196\n" +
                    "\t\t\t\t,CASE me_us_registro.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad]   --197\n" +
                    "\t\t\t,[me_usuario_autorizacion_cdgo]   --198\n" +
                    "\t\t\t\t--Usuario quien autoriza\n" +
                    "\t\t\t\t,me_us_autorizacion.[us_cdgo]   --199\n" +
                    "\t\t\t\t,me_us_autorizacion.[us_clave]   --200\n" +
                    "\t\t\t\t,me_us_autorizacion.[us_nombres]   --201\n" +
                    "\t\t\t\t,me_us_autorizacion.[us_apellidos]   --202\n" +
                    "\t\t\t\t,me_us_autorizacion.[us_perfil_cdgo]   --203\n" +
                    "\t\t\t\t\t--Perfil quien autoriza\n" +
                    "\t\t\t\t\t,me_us_autoriza_perfil.[prf_cdgo]   --204\n" +
                    "\t\t\t\t\t,me_us_autoriza_perfil.[prf_desc]   --205\n" +
                    "\t\t\t\t\t,CASE me_us_autoriza_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad]   --206\n" +
                    "\t\t\t\t,me_us_autorizacion.[us_correo]   --207\n" +
                    "\t\t\t\t,CASE me_us_autorizacion.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad]   --208\n" +
                    "\t\t\t,[me_autorizacion_recobro_cdgo]   --209\n" +
                    "\t\t\t\t,[are_cdgo]   --210\n" +
                    "\t\t\t\t,[are_desc]   --211\n" +
                    "\t\t\t\t,CASE [are_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [are_estad]   --212\n" +
                    "\t\t\t,[me_observ_autorizacion]   --213\n" +
                    "\t\t\t,[me_inactividad]   --214\n" +
                    "\t\t\t,[me_causa_inactividad_cdgo]   --215\n" +
                    "\t\t\t\t,[ci_cdgo]   --216\n" +
                    "\t\t\t\t,[ci_desc]   --217\n" +
                    "\t\t\t\t,CASE [ci_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ci_estad]   --218\n" +
                    "\t\t\t,[me_usuario_inactividad_cdgo]   --219\n" +
                    "\t\t\t\t--Usuario de Inactividad\n" +
                    "\t\t\t\t,me_us_inactividad.[us_cdgo]   --220\n" +
                    "\t\t\t\t,me_us_inactividad.[us_clave]   --221\n" +
                    "\t\t\t\t,me_us_inactividad.[us_nombres]   --222\n" +
                    "\t\t\t\t,me_us_inactividad.[us_apellidos]   --223\n" +
                    "\t\t\t\t,me_us_inactividad.[us_perfil_cdgo]   --224\n" +
                    "\t\t\t\t\t,me_us_inactvdad_perfil.[prf_cdgo]   --225\n" +
                    "\t\t\t\t\t,me_us_inactvdad_perfil.[prf_desc]   --226\n" +
                    "\t\t\t\t\t,CASE me_us_inactvdad_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad]   --227\n" +
                    "\t\t\t\t,me_us_inactividad.[us_correo]   --228\n" +
                    "\t\t\t\t,CASE me_us_inactividad.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad]\t   --229\n" +
                    "\t\t\t,[me_motivo_parada_estado]   --230\n" +
                    "\t\t\t,[me_motivo_parada_cdgo]   --231\n" +
                    "\t\t\t\t--Motivo de Parada\n" +
                    "\t\t\t\t,[mpa_cdgo]   --232\n" +
                    "\t\t\t\t,[mpa_desc]   --233\n" +
                    "\t\t\t\t,[mpa_estad]   --234\n" +
                    "\t\t\t,[me_observ]   --235\n" +
                    "\t\t\t,CASE [me_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_estado]\t   --236\n" +
                    "\t\t\t,CASE [me_desde_mvto_carbon] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [me_desde_mvto_carbon]   --237\n" +
                    "   ,[co_cdgo] --238\n" +
                    "   ,[co_desc] --239\n" +
                    "  ,CASE [co_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [co_estad]	   --240\n" +
                    "\t         ,[cc_cdgo] --241\n" +
                        "      ,[cc_cntro_oper_cdgo] --242\n" +
                        "      ,[cc_cntro_cost_auxiliar_cdgo] --243\n" +
                        "      ,[cc_cliente_cdgo]--244\n" +
                        "      ,[cc_descripcion]--245\n" +
                        "      ,[cc_estad]--246\n "+
                    "      ,[tar_cdgo] --247\n" +
                    "      ,[tar_desc]--248\n" +
                    "      ,[tar_cdgo_erp]--249\n" +
                    "      ,[tar_undad_ngcio]--250\n" +
                    "      ,[tar_estado]--251\n "+
                    "         ,datename (MONTH ,[me_fecha]) --252\n" +
                    "      ,[ae_sle_mn_base_datos_cdgo]--253\n" +
                    "      ,me_cntro_cost_base_datos.[bd_cdgo]   --254 \n" +
                    "      ,me_cliente_base_datos.[bd_cdgo]   --255 \n" +
                    "      ,me_articulo_base_datos.[bd_cdgo]   --256 \n" +
                    //"      ,me_motonave_base_datos.[bd_cdgo]   --257 \n" +
                    //"      ,me_cntro_cost_mayor_base_datos.[bd_cdgo]  --257 \n" +
                    
                    
                     " FROM ["+DB+"].[dbo].[mvto_equipo]\n" +
                    "\tINNER JOIN (SELECT [ae_cdgo] AS ae_cdgo\n" +
                    "\t\t\t\t\t  ,[ae_cntro_oper_cdgo] AS ae_cntro_oper_cdgo\n" +
                    "\t\t\t\t\t\t\t--Centro Operacion\n" +
                    "\t\t\t\t\t\t\t,ae_cntro_oper.[co_cdgo] AS ae_co_cdgo\n" +
                    "\t\t\t\t\t\t\t,ae_cntro_oper.[co_desc] AS ae_co_desc\n" +
                    "\t\t\t\t\t\t\t,ae_cntro_oper.[co_estad] AS ae_co_estado\n" +
                    "\t\t\t\t\t  ,[ae_solicitud_listado_equipo_cdgo] AS ae_solicitud_listado_equipo_cdgo\n" +
                    "\t\t\t\t\t\t\t-- Solicitud Listado Equipo\n" +
                    "\t\t\t\t\t\t\t,[sle_cdgo] AS ae_sle_cdgo\n" +
                    "\t\t\t\t\t\t\t,[sle_solicitud_equipo_cdgo] AS ae_sle_solicitud_equipo_cdgo\n" +
                    "\t\t\t\t\t\t\t\t  --Solicitud Equipo\n" +
                    "\t\t\t\t\t\t\t\t  ,[se_cdgo] AS ae_sle_se_cdgo\n" +
                    "\t\t\t\t\t\t\t\t  ,[se_cntro_oper_cdgo] AS ae_sle_se_cntro_oper_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t--CentroOperación SolicitudEquipo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_cntro_oper.[co_cdgo] AS ae_sle_se_co_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_cntro_oper.[co_desc] AS ae_sle_se_co_desc\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_cntro_oper.[co_estad] AS ae_sle_se_co_estad\n" +
                    "\t\t\t\t\t\t\t\t  ,[se_fecha] AS ae_sle_se_fecha\n" +
                    "\t\t\t\t\t\t\t\t  ,[se_usuario_realiza_cdgo] AS ae_sle_se_usuario_realiza_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t--Usuario SolicitudEquipo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_realiza.[us_cdgo] AS ae_sle_se_us_registra_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_realiza.[us_clave] AS ae_sle_se_us_registra_clave\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_realiza.[us_nombres] AS ae_sle_se_us_registra_nombres\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_realiza.[us_apellidos] AS ae_sle_se_us_registra_apellidos\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_realiza.[us_perfil_cdgo] AS ae_sle_se_us_registra_perfil_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t--Perfil Usuario Quien Registra Manual\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t,ae_prf_registra.[prf_cdgo] AS ae_sle_se_prf_us_registra_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t,ae_prf_registra.[prf_desc] AS ae_sle_se_prf_us_registra_desc\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t,ae_prf_registra.[prf_estad] AS ae_sle_se_prf_us_registra_estad\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_realiza.[us_correo] AS ae_sle_se_us_registra_correo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_realiza.[us_estad] AS ae_sle_se_us_registra_estad\n" +
                    "\t\t\t\t\t\t\t\t  ,[se_fecha_registro] AS ae_sle_se_fecha_registro\n" +
                    "\t\t\t\t\t\t\t\t  ,[se_estado_solicitud_equipo_cdgo] AS ae_sle_se_estado_solicitud_equipo_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t--Estado de la solicitud\n" +
                    "\t\t\t\t\t\t\t\t\t\t,[ese_cdgo] AS ae_sle_se_ese_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,[ese_desc] AS ae_sle_se_ese_desc\n" +
                    "\t\t\t\t\t\t\t\t\t\t,[ese_estad] AS ae_sle_se_ese_estad\n" +
                    "\t\t\t\t\t\t\t\t  ,[se_fecha_confirmacion] AS ae_sle_se_fecha_confirmacion\n" +
                    "\t\t\t\t\t\t\t\t  ,[se_usuario_confirma_cdgo] AS ae_se_usuario_confirma_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t--Usuario SolicitudEquipo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_confirma.[us_cdgo] AS ae_sle_se_us_confirma_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_confirma.[us_clave] AS ae_sle_se_us_confirma_clave\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_confirma.[us_nombres] AS ae_sle_se_us_confirma_nombres\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_confirma.[us_apellidos] AS ae_sle_se_us_confirma_apellidos\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_confirma.[us_perfil_cdgo] AS ae_sle_se_us_confirma_perfil_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t--Perfil Usuario Quien Registra Manual\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t,ae_prf_registra_confirma.[prf_cdgo] AS ae_sle_se_prf_us_confirma_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t,ae_prf_registra_confirma.[prf_desc] AS ae_sle_se_prf_us_confirma_desc\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t,ae_prf_registra_confirma.[prf_estad] AS ae_sle_se_prf_us_confirma_estad\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_confirma.[us_correo] AS ae_sle_se_us_confirma_correo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_confirma.[us_estad] AS ae_sle_se_us_confirma_estado\n" +
                    "\t\t\t\t\t\t\t\t  ,[se_confirmacion_solicitud_equipo_cdgo] AS ae_sle_se_confirmacion_solicitud_equipo_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t--Confirmacion solicitudEquipo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,[cse_cdgo] AS ae_sle_se_cse_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,[cse_desc] AS ae_sle_se_ces_desc\n" +
                    "\t\t\t\t\t\t\t\t\t\t,[cse_estad] AS ae_sle_se_ces_estado\n" +
                    "\t\t\t\t\t\t\t,[sle_tipo_equipo_cdgo] AS ae_sle_tipo_equipo_cdgo\n" +
                    "\t\t\t\t\t\t\t\t--Tipo de Equipo\n" +
                    "\t\t\t\t\t\t\t\t,sle_tipoEquipo.[te_cdgo] AS ae_sle_te_cdgo\n" +
                    "\t\t\t\t\t\t\t\t,sle_tipoEquipo.[te_desc] AS ae_sle_te_desc\n" +
                    "\t\t\t\t\t\t\t\t,sle_tipoEquipo.[te_estad] AS ae_sle_te_estad\n" +
                    "\t\t\t\t\t\t\t,[sle_marca_equipo] AS ae_sle_marca_equipo\n" +
                    "\t\t\t\t\t\t\t,[sle_modelo_equipo] AS ae_sle_modelo_equipo\n" +
                    "\t\t\t\t\t\t\t,[sle_cant_equip] AS ae_sle_cant_equip\n" +
                    "\t\t\t\t\t\t\t,[sle_observ] AS ae_sle_observ\n" +
                    "\t\t\t\t\t\t\t,[sle_fecha_hora_inicio] AS ae_sle_fecha_hora_inicio\n" +
                    "\t\t\t\t\t\t\t,[sle_fecha_hora_fin] AS ae_sle_fecha_hora_fin\n" +
                    "\t\t\t\t\t\t\t,[sle_cant_minutos] AS   ae_sle_cant_minutos\n" +
                    "\t\t\t\t\t\t\t,[sle_labor_realizada_cdgo] AS ae_sle_labor_realizada_cdgo\n" +
                    "\t\t\t\t\t\t\t-- Labor Realizada\n" +
                    "\t\t\t\t\t\t\t\t  ,[lr_cdgo] AS ae_sle_lr_cdgo\n" +
                    "\t\t\t\t\t\t\t\t  ,[lr_desc] AS ae_sle_lr_desc\n" +
                    "\t\t\t\t\t\t\t\t  ,[lr_estad] AS ae_sle_lr_estad\n" +
                    "\t\t\t\t\t\t\t,[sle_motonave_cdgo] AS ae_sle_sle_motonave_cdgo\n" +
                    "\t\t\t\t\t\t\t--Motonave\n" +
                    "\t\t\t\t\t\t\t\t  ,[mn_cdgo] AS ae_sle_mn_cdgo\n" +
                    "\t\t\t\t\t\t\t\t  ,[mn_desc] AS ae_sle_mn_desc\n" +
                    "\t\t\t\t\t\t\t\t  ,[mn_estad] AS ae_sle_mn_estad\n" +
                    "\t\t\t\t\t\t\t,[sle_cntro_cost_auxiliar_cdgo] AS ae_sle_cntro_cost_auxiliar_cdgo\n" +
                    "\t\t\t\t\t\t\t\t--Centro Costo Auxiliar\n" +
                    "\t\t\t\t\t\t\t\t,[cca_cdgo] AS ae_sle_cca_cdgo\n" +
                    "\t\t\t\t\t\t\t\t,[cca_cntro_cost_subcentro_cdgo] AS ae_sle_cca_cntro_cost_subcentro_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t-- SubCentro de Costo\n" +
                    "\t\t\t\t\t\t\t\t\t,[ccs_cdgo] AS ae_sle_ccs_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t,[ccs_desc] AS ae_sle_ccs_desc\n" +
                    "\t\t\t\t\t\t\t\t\t,[ccs_estad] AS ae_sle_ccs_estad\n" +
                    "\t\t\t\t\t\t\t\t,[cca_desc] AS ae_sle_cca_desc\n" +
                    "\t\t\t\t\t\t\t\t,[cca_estad] AS ae_sle_cca_estad\n" +
                    "\t\t\t\t\t\t\t,[sle_compania_cdgo] AS ae_sle_compania_cdgo\n" +
                    "\t\t\t\t\t\t\t\t--Compañia\n" +
                    "\t\t\t\t\t\t\t\t,[cp_cdgo] AS ae_sle_cp_cdgo\n" +
                    "\t\t\t\t\t\t\t\t,[cp_desc] AS ae_sle_cp_desc\n" +
                    "\t\t\t\t\t\t\t\t,[cp_estad] AS ae_sle_cp_estad\n" +
                    "\t\t\t\t\t  ,[ae_fecha_registro] AS ae_fecha_registro\n" +
                    "\t\t\t\t\t  ,[ae_fecha_hora_inicio] AS ae_fecha_hora_inicio\n" +
                    "\t\t\t\t\t  ,[ae_fecha_hora_fin] AS ae_fecha_hora_fin\n" +
                    "\t\t\t\t\t  ,[ae_cant_minutos] AS ae_cant_minutos\n" +
                    "\t\t\t\t\t  ,[ae_equipo_cdgo] AS ae_equipo_cdgo\n" +
                    "\t\t\t\t\t\t\t--Equipo\n" +
                    "\t\t\t\t\t\t\t,[eq_cdgo] AS ae_eq_cdgo\n" +
                    "\t\t\t\t\t\t\t,[eq_tipo_equipo_cdgo] AS ae_eq_tipo_equipo_cdgo\n" +
                    "\t\t\t\t\t\t\t\t--Tipo Equipo\n" +
                    "\t\t\t\t\t\t\t\t,eq_tipo_equipo.[te_cdgo] AS ae_eq_te_cdgo\n" +
                    "\t\t\t\t\t\t\t\t,eq_tipo_equipo.[te_desc] AS ae_eq_te_desc\n" +
                    "\t\t\t\t\t\t\t\t,eq_tipo_equipo.[te_estad] AS ae_eq_te_estad\n" +
                    "\t\t\t\t\t\t\t,[eq_codigo_barra] AS ae_eq_codigo_barra\n" +
                    "\t\t\t\t\t\t\t,[eq_referencia] AS ae_eq_referencia\n" +
                    "\t\t\t\t\t\t\t,[eq_producto] AS ae_eq_producto\n" +
                    "\t\t\t\t\t\t\t,[eq_capacidad] AS ae_eq_capacidad\n" +
                    "\t\t\t\t\t\t\t,[eq_marca] AS ae_eq_marca\n" +
                    "\t\t\t\t\t\t\t,[eq_modelo] AS ae_eq_modelo\n" +
                    "\t\t\t\t\t\t\t,[eq_serial] AS ae_eq_serial\n" +
                    "\t\t\t\t\t\t\t,[eq_desc] AS ae_eq_desc\n" +
                    "\t\t\t\t\t\t\t,[eq_clasificador1_cdgo] AS ae_eq_clasificador1_cdgo\n" +
                    "\t\t\t\t\t\t\t\t-- Clasificador 1\n" +
                    "\t\t\t\t\t\t\t\t,eq_clasificador1.[ce_cdgo] AS ae_eq_ce1_cdgo\n" +
                    "\t\t\t\t\t\t\t\t,eq_clasificador1.[ce_desc] AS ae_eq_ce1_desc\n" +
                    "\t\t\t\t\t\t\t\t,eq_clasificador1.[ce_estad] AS ae_eq_ce1_estad\n" +
                    "\t\t\t\t\t\t\t,[eq_clasificador2_cdgo] AS ae_eq_clasificador2_cdgo\n" +
                    "\t\t\t\t\t\t\t\t-- Clasificador 2\n" +
                    "\t\t\t\t\t\t\t\t,eq_clasificador2.[ce_cdgo] AS ae_eq_ce2_cdgo\n" +
                    "\t\t\t\t\t\t\t\t,eq_clasificador2.[ce_desc] AS ae_eq_ce2_desc\n" +
                    "\t\t\t\t\t\t\t\t,eq_clasificador2.[ce_estad] AS ae_eq_ce2_estad\n" +
                    "\t\t\t\t\t\t\t,[eq_proveedor_equipo_cdgo] AS ae_eq_proveedor_equipo_cdgo\n" +
                    "\t\t\t\t\t\t\t\t--Proveedor Equipo\n" +
                    "\t\t\t\t\t\t\t\t,[pe_cdgo] AS ae_eq_pe_cdgo\n" +
                    "\t\t\t\t\t\t\t\t,[pe_nit] AS ae_eq_pe_nit\n" +
                    "\t\t\t\t\t\t\t\t,[pe_desc] AS ae_eq_pe_desc\n" +
                    "\t\t\t\t\t\t\t\t,[pe_estad] AS ae_eq_pe_estad\n" +
                    "\t\t\t\t\t\t\t,[eq_equipo_pertenencia_cdgo] AS ae_eq_equipo_pertenencia_cdgo\n" +
                    "\t\t\t\t\t\t\t\t-- Equipo Pertenencia\n" +
                    "\t\t\t\t\t\t\t\t,eq_pertenencia.[ep_cdgo] AS ae_eq_ep_cdgo\n" +
                    "\t\t\t\t\t\t\t\t,eq_pertenencia.[ep_desc] AS ae_eq_ep_desc\n" +
                    "\t\t\t\t\t\t\t\t,eq_pertenencia.[ep_estad] AS ae_eq_ep_estad\n" +
                    "\t\t\t\t\t\t\t,[eq_observ] AS ae_eq_eq_observ\n" +
                    "\t\t\t\t\t\t\t,[eq_estad] AS ae_eq_eq_estad\n" +
                    "\t\t\t\t\t\t\t,[eq_actvo_fijo_id] AS ae_eq_actvo_fijo_id\n" +
                    "\t\t\t\t\t\t\t,[eq_actvo_fijo_referencia] AS ae_eq_actvo_fijo_referencia\n" +
                    "\t\t\t\t\t\t\t,[eq_actvo_fijo_desc] AS ae_eq_actvo_fijo_desc\n" +
                    "\t\t\t\t\t  ,[ae_equipo_pertenencia_cdgo] AS ae_equipo_pertenencia_cdgo\n" +
                    "\t\t\t\t\t\t-- Equipo Pertenencia\n" +
                    "\t\t\t\t\t\t\t\t,ae_pertenencia.[ep_cdgo] AS ae_ep_cdgo\n" +
                    "\t\t\t\t\t\t\t\t,ae_pertenencia.[ep_desc] AS ae_ep_desc\n" +
                    "\t\t\t\t\t\t\t\t,ae_pertenencia.[ep_estad]\t AS ae_ep_estad\n" +
                    "\t\t\t\t\t  ,[ae_cant_minutos_operativo] AS ae_cant_hora_operativa\n" +
                    "\t\t\t\t\t  ,[ae_cant_minutos_parada] AS ae_cant_hora_parada\n" +
                    "\t\t\t\t\t  ,[ae_cant_minutos_total] AS ae_cant_hora_total\n" +
                    "\t\t\t\t\t  ,[ae_estad] AS ae_estad\n" +
                    "\t\t\t\t\t  ,[sle_motonave_base_datos_cdgo] AS   ae_sle_mn_base_datos_cdgo\n" +
                    "\t\t\t\t\t  FROM ["+DB+"].[dbo].[asignacion_equipo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[solicitud_listado_equipo] ON [ae_solicitud_listado_equipo_cdgo]=[sle_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[cntro_oper] ae_cntro_oper ON [ae_cntro_oper_cdgo]=ae_cntro_oper.[co_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[solicitud_equipo] ON [sle_solicitud_equipo_cdgo]=[se_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[cntro_oper] se_cntro_oper ON [se_cntro_oper_cdgo]=se_cntro_oper.[co_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[usuario] se_usuario_realiza ON [se_usuario_realiza_cdgo]=se_usuario_realiza.[us_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[perfil] ae_prf_registra ON se_usuario_realiza.[us_perfil_cdgo]=ae_prf_registra.[prf_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[estado_solicitud_equipo] ON [se_estado_solicitud_equipo_cdgo]=[ese_cdgo]\n" +
                    "\t\t\t\t\t\t\tLEFT JOIN  ["+DB+"].[dbo].[usuario] se_usuario_confirma ON [se_usuario_realiza_cdgo]=se_usuario_confirma.[us_cdgo]\n" +
                    "\t\t\t\t\t\t\tLEFT JOIN ["+DB+"].[dbo].[perfil] ae_prf_registra_confirma ON se_usuario_confirma.[us_perfil_cdgo]=ae_prf_registra_confirma.[prf_cdgo]\n" +
                    "\t\t\t\t\t\t\tLEFT JOIN  ["+DB+"].[dbo].[confirmacion_solicitud_equipo] ON [se_confirmacion_solicitud_equipo_cdgo]=[cse_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[tipo_equipo] sle_tipoEquipo ON [sle_tipo_equipo_cdgo]=sle_tipoEquipo.[te_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[labor_realizada] ON [sle_labor_realizada_cdgo]=[lr_cdgo]\n" +
                    //"\t\t\t\t\t\t\tLEFT  JOIN["+DB+"].[dbo].[motonave] ON [sle_motonave_cdgo]=[mn_cdgo]\n" +
                    "		LEFT JOIN ["+DB+"].[dbo].[motonave] ON [sle_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[sle_motonave_base_datos_cdgo] \n" +
                    "               LEFT JOIN ["+DB+"].[dbo].[base_datos] sle_motonave_base_datos ON  [mn_base_datos_cdgo]=sle_motonave_base_datos.[bd_cdgo] \n"+
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [sle_cntro_cost_auxiliar_cdgo]=[cca_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[compania] ON [sle_compania_cdgo]=[cp_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[tipo_equipo] eq_tipo_equipo ON [eq_tipo_equipo_cdgo]=eq_tipo_equipo.[te_cdgo]\n" +
                    "\t\t\t\t\t\t\tLEFT JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador1 ON [eq_clasificador1_cdgo]=eq_clasificador1.[ce_cdgo]\n" +
                    "\t\t\t\t\t\t\tLEFT JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador2 ON [eq_clasificador2_cdgo]=eq_clasificador2.[ce_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [eq_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
                    "\t\t\t\t\t\t\tLEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] eq_pertenencia ON [eq_equipo_pertenencia_cdgo]=eq_pertenencia.[ep_cdgo]\n" +
                    "\t\t\t\t\t\t\tLEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ae_pertenencia ON [ae_equipo_pertenencia_cdgo]=ae_pertenencia.[ep_cdgo]\t\n" +
                    "\t) asignacion_equipo ON [me_asignacion_equipo_cdgo]=[ae_cdgo]\n" +
                    "\tINNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [me_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
                    "\tINNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [me_cntro_oper_cdgo]=[co_cdgo]\n" +
                    "\tINNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] me_cntro_cost_auxiliar ON [me_cntro_cost_auxiliar_cdgo]=me_cntro_cost_auxiliar.[cca_cdgo]\n" +
                    "\tINNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro]  me_cntro_cost_subcentro ON me_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo]=me_cntro_cost_subcentro.[ccs_cdgo]\n" +
                    "\tINNER JOIN ["+DB+"].[dbo].[labor_realizada] ON [me_labor_realizada_cdgo]=[lr_cdgo] \n" +
                    //"\tLEFT JOIN ["+DB+"].[dbo].[cliente] me_cliente ON [me_cliente_cdgo]=me_cliente.[cl_cdgo]\n" +
                    "		LEFT JOIN ["+DB+"].[dbo].[cliente] me_cliente ON [me_cliente_cdgo]=me_cliente.[cl_cdgo] AND me_cliente.[cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
                    "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON me_cliente.[cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                            //"\tLEFT JOIN  ["+DB+"].[dbo].[articulo] ON [me_articulo_cdgo]=[ar_cdgo]\n" +
                                    
                    "		LEFT JOIN ["+DB+"].[dbo].[articulo]  ON [me_articulo_cdgo]=[ar_cdgo] AND [ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
                    "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON [ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
        
                    "\tINNER JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[cliente_recobro] ON [me_cliente_recobro_cdgo]=[clr_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[cliente] me_clr_cliente ON [clr_cliente_cdgo]=me_clr_cliente.[cl_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[usuario] me_clr_usuario ON [clr_usuario_cdgo]=me_clr_usuario.[us_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[perfil] me_clr_us_perfil ON me_clr_usuario.[us_perfil_cdgo]=me_clr_us_perfil.[prf_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[usuario] me_us_registro ON [me_usuario_registro_cdgo]=me_us_registro.[us_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[perfil] me_us_regist_perfil ON me_us_registro.[us_perfil_cdgo]=me_us_regist_perfil.[prf_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[usuario] me_us_autorizacion ON [me_usuario_autorizacion_cdgo]=me_us_autorizacion.[us_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[perfil] me_us_autoriza_perfil ON me_us_autorizacion.[us_perfil_cdgo]=me_us_autoriza_perfil.[prf_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[autorizacion_recobro] ON [me_autorizacion_recobro_cdgo]=[are_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[causa_inactividad] ON [me_causa_inactividad_cdgo]=[ci_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[usuario] me_us_inactividad ON [me_usuario_inactividad_cdgo]=me_us_inactividad.[us_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[perfil] me_us_inactvdad_perfil ON me_us_inactividad.[us_perfil_cdgo]=me_us_inactvdad_perfil.[prf_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[motivo_parada] ON [me_motivo_parada_cdgo]=[mpa_cdgo]\n" +
                    //"\n LEFT JOIN ["+DB+"].[dbo].[cntro_cost] ON [me_cntro_cost_cdgo] =[cc_cdgo] " +
                    "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost]  ON [me_cntro_cost_cdgo]=[cc_cdgo] \n" +
                    "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_base_datos ON [cc_cliente_base_datos_cdgo]=me_cntro_cost_base_datos.[bd_cdgo] \n"+  
                    " LEFT JOIN  ["+DB+"].[dbo].[tipo_articulo] ON [ar_tipo_articulo_cdgo] =[tar_cdgo] \n" +
                    "\tWHERE  [me_fecha_hora_inicio] BETWEEN ? AND ? AND [me_inactividad]=0 "
                    + " --AND [me_fecha_hora_fin] IS NOT NULL AND --[me_desde_mvto_carbon]=0 "
                    + " ORDER BY [me_cdgo] DESC");
            query.setString(1, DatetimeInicio);
            query.setString(2, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();    
            boolean validator=true;
            while(resultSet.next()){
                if(validator){
                    listadoObjetos = new ArrayList();
                    validator=false;
                }
                AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                asignacionEquipo.setCodigo(resultSet.getString(4));
                    CentroOperacion co= new CentroOperacion();
                    co.setCodigo(Integer.parseInt(resultSet.getString(5)));
                    co.setDescripcion(resultSet.getString(6));
                    co.setEstado(resultSet.getString(7));
                asignacionEquipo.setCentroOperacion(co);
                    SolicitudListadoEquipo solicitudListadoEquipo = new SolicitudListadoEquipo();
                    solicitudListadoEquipo.setCodigo(resultSet.getString(9));
                        SolicitudEquipo solicitudEquipo= new SolicitudEquipo();
                        solicitudEquipo.setCodigo(resultSet.getString(10));
                            CentroOperacion co_se= new CentroOperacion();
                                co_se.setCodigo(Integer.parseInt(resultSet.getString(13)));
                                co_se.setDescripcion(resultSet.getString(14));
                                co_se.setEstado(resultSet.getString(15));
                        solicitudEquipo.setCentroOperacion(co_se);
                        solicitudEquipo.setFechaSolicitud(resultSet.getString(16));
                            Usuario us_se = new Usuario();
                                us_se.setCodigo(resultSet.getString(18));
                                //us_se.setClave(resultSet.getString(19));
                                us_se.setNombres(resultSet.getString(20));
                                us_se.setApellidos(resultSet.getString(21));
                                us_se.setPerfilUsuario(new Perfil(resultSet.getString(23),resultSet.getString(24),resultSet.getString(25)));
                                us_se.setCorreo(resultSet.getString(26));
                                us_se.setEstado(resultSet.getString(27));
                        solicitudEquipo.setUsuarioRealizaSolicitud(us_se);
                        solicitudEquipo.setFechaRegistro(resultSet.getString(28));
                            EstadoSolicitudEquipos estadoSolicitudEquipos = new EstadoSolicitudEquipos();
                            estadoSolicitudEquipos.setCodigo(resultSet.getString(30));
                            estadoSolicitudEquipos.setDescripcion(resultSet.getString(31));
                            estadoSolicitudEquipos.setEstado(resultSet.getString(32));
                        solicitudEquipo.setEstadoSolicitudEquipo(estadoSolicitudEquipos);
                        solicitudEquipo.setFechaConfirmacion(resultSet.getString(33));
                            Usuario us_se_confirm = new Usuario();
                            us_se_confirm.setCodigo(resultSet.getString(35));
                            //us_se_confirm.setClave(resultSet.getString(105));
                            us_se_confirm.setNombres(resultSet.getString(37));
                            us_se_confirm.setApellidos(resultSet.getString(38));
                            us_se_confirm.setPerfilUsuario(new Perfil(resultSet.getString(40),resultSet.getString(41),resultSet.getString(42)));
                            us_se_confirm.setCorreo(resultSet.getString(43));
                            us_se_confirm.setEstado(resultSet.getString(44));
                        solicitudEquipo.setUsuarioConfirmacionSolicitud(us_se_confirm);
                            ConfirmacionSolicitudEquipos confirmacionSolicitudEquipos = new ConfirmacionSolicitudEquipos();
                            confirmacionSolicitudEquipos.setCodigo(resultSet.getString(46));
                            confirmacionSolicitudEquipos.setDescripcion(resultSet.getString(47));
                            confirmacionSolicitudEquipos.setEstado(resultSet.getString(48));
                        solicitudEquipo.setConfirmacionSolicitudEquipo(confirmacionSolicitudEquipos);
                    solicitudListadoEquipo.setSolicitudEquipo(solicitudEquipo);
                        TipoEquipo tipoEquipo = new TipoEquipo();
                        tipoEquipo.setCodigo(resultSet.getString(50));
                        tipoEquipo.setDescripcion(resultSet.getString(51));
                        tipoEquipo.setEstado(resultSet.getString(52));
                    solicitudListadoEquipo.setTipoEquipo(tipoEquipo);
                    solicitudListadoEquipo.setMarcaEquipo(resultSet.getString(53));
                    solicitudListadoEquipo.setModeloEquipo(resultSet.getString(54));
                    solicitudListadoEquipo.setCantidad(Integer.parseInt(resultSet.getString(55)));
                    solicitudListadoEquipo.setObservacacion(resultSet.getString(56));
                    solicitudListadoEquipo.setFechaHoraInicio(resultSet.getString(57));
                    solicitudListadoEquipo.setFechaHoraFin(resultSet.getString(58));
                    solicitudListadoEquipo.setCantidadMinutos(resultSet.getInt(59));
                        LaborRealizada laborRealizada = new LaborRealizada();
                        laborRealizada.setCodigo(resultSet.getString(61));
                        laborRealizada.setDescripcion(resultSet.getString(62));
                        laborRealizada.setEstado(resultSet.getString(63));
                    solicitudListadoEquipo.setLaborRealizada(laborRealizada);
                        Motonave motonave = new Motonave();
                        motonave.setCodigo(resultSet.getString(65));
                        motonave.setDescripcion(resultSet.getString(66));
                        motonave.setEstado(resultSet.getString(67));
                        motonave.setBaseDatos(new BaseDatos( resultSet.getString(253)));
                    solicitudListadoEquipo.setMotonave(motonave);
                        CentroCostoSubCentro centroCostoSubCentro = new CentroCostoSubCentro();
                        centroCostoSubCentro.setCodigo(resultSet.getInt(71));
                        centroCostoSubCentro.setDescripcion(resultSet.getString(72));
                        centroCostoSubCentro.setEstado(resultSet.getString(73));
                        CentroCostoAuxiliar centroCostoAuxiliar = new CentroCostoAuxiliar();
                        centroCostoAuxiliar.setCodigo(resultSet.getInt(69));
                        centroCostoAuxiliar.setDescripcion(resultSet.getString(74));
                        centroCostoAuxiliar.setEstado(resultSet.getString(75));
                        centroCostoAuxiliar.setCentroCostoSubCentro(centroCostoSubCentro);
                    solicitudListadoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar);
                        Compañia compania = new Compañia();
                        compania.setCodigo(resultSet.getString(77));
                        compania.setDescripcion(resultSet.getString(78));
                        compania.setEstado(resultSet.getString(79));
                    solicitudListadoEquipo.setCompañia(compania);
                asignacionEquipo.setSolicitudListadoEquipo(solicitudListadoEquipo);
                asignacionEquipo.setFechaRegistro(resultSet.getString(80));
                asignacionEquipo.setFechaHoraInicio(resultSet.getString(81));
                asignacionEquipo.setFechaHoraFin(resultSet.getString(82));
                asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(83));
                    Equipo equipo = new Equipo();
                    equipo.setCodigo(resultSet.getString(85));
                    equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(87),resultSet.getString(88),resultSet.getString(89)));
                    equipo.setCodigo_barra(resultSet.getString(90));
                    equipo.setReferencia(resultSet.getString(91));
                    equipo.setProducto(resultSet.getString(92));
                    equipo.setCapacidad(resultSet.getString(93));
                    equipo.setMarca(resultSet.getString(94));
                    equipo.setModelo(resultSet.getString(95));
                    equipo.setSerial(resultSet.getString(96));
                    equipo.setDescripcion(resultSet.getString(97));
                    equipo.setClasificador1(new ClasificadorEquipo(resultSet.getString(99),resultSet.getString(100),resultSet.getString(101)));
                    equipo.setClasificador2(new ClasificadorEquipo(resultSet.getString(103),resultSet.getString(104),resultSet.getString(105)));
                    equipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(107),resultSet.getString(108),resultSet.getString(109),resultSet.getString(110)));
                    equipo.setPertenenciaEquipo(new Pertenencia(resultSet.getString(112),resultSet.getString(113),resultSet.getString(114)));
                    equipo.setObservacion(resultSet.getString(115));
                    equipo.setEstado(resultSet.getString(116));
                    equipo.setActivoFijo_codigo(resultSet.getString(117));
                    equipo.setActivoFijo_referencia(resultSet.getString(118));
                    equipo.setActivoFijo_descripcion(resultSet.getString(119));
                asignacionEquipo.setEquipo(equipo);
                asignacionEquipo.setPertenencia(new Pertenencia(resultSet.getString(121),resultSet.getString(122),resultSet.getString(123)));
                asignacionEquipo.setCantidadMinutosOperativo(resultSet.getString(124));
                asignacionEquipo.setCantidadMinutosParada(resultSet.getString(125));
                asignacionEquipo.setCantidadMinutosTotal(resultSet.getString(126));
                asignacionEquipo.setEstado(resultSet.getString(127));
                    //mvto_listEquipo.setAsignacionEquipo(asignacionEquipo);
                MvtoEquipo mvtoEquipo = new MvtoEquipo();
                    mvtoEquipo.setCodigo(resultSet.getString(1));
                    mvtoEquipo.setAsignacionEquipo(asignacionEquipo);
                    mvtoEquipo.setFechaRegistro(resultSet.getString(128));
                    mvtoEquipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(130),resultSet.getString(131),resultSet.getString(132),resultSet.getString(133)));
                    mvtoEquipo.setNumeroOrden(resultSet.getString(134));
                        CentroOperacion me_co= new CentroOperacion();
                        me_co.setCodigo(Integer.parseInt(resultSet.getString(238)));
                        me_co.setDescripcion(resultSet.getString(239));
                        me_co.setEstado(resultSet.getString(240));
                    mvtoEquipo.setCentroOperacion(me_co);
                        CentroCostoSubCentro centroCostoSubCentro_mvtoEquipo = new CentroCostoSubCentro();
                        centroCostoSubCentro_mvtoEquipo.setCodigo(resultSet.getInt(138));
                        centroCostoSubCentro_mvtoEquipo.setDescripcion(resultSet.getString(139));
                        centroCostoSubCentro_mvtoEquipo.setEstado(resultSet.getString(140));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipo = new CentroCostoAuxiliar();
                        centroCostoAuxiliar_mvtoEquipo.setCodigo(resultSet.getInt(136));
                        centroCostoAuxiliar_mvtoEquipo.setDescripcion(resultSet.getString(141));
                        centroCostoAuxiliar_mvtoEquipo.setEstado(resultSet.getString(142));
                        centroCostoAuxiliar_mvtoEquipo.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipo);
      
                        CentroCosto centroCosto = new CentroCosto();
                        centroCosto.setCodigo(resultSet.getString(241));
                        centroCosto.setDescripción(resultSet.getString(245));
                        centroCosto.setEstado(resultSet.getString(246));
                        centroCosto.setClienteBaseDatos(resultSet.getString(254));
                        
                    mvtoEquipo.setCentroCosto(centroCosto);
                        
                    mvtoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipo);
                        LaborRealizada laborRealizadaT = new LaborRealizada();
                        laborRealizadaT.setCodigo(resultSet.getString(144));
                        laborRealizadaT.setDescripcion(resultSet.getString(145));
                        laborRealizadaT.setEstado(resultSet.getString(147));
                        laborRealizadaT.setEs_operativa(resultSet.getString(148));
                        laborRealizadaT.setEs_parada(resultSet.getString(149));
                    mvtoEquipo.setLaborRealizada(laborRealizadaT);
                    mvtoEquipo.setCliente(new Cliente(resultSet.getString(151),resultSet.getString(152),resultSet.getString(153)));
                    mvtoEquipo.getCliente().setBaseDatos(new BaseDatos(resultSet.getString(255)));
                    mvtoEquipo.setArticulo(new Articulo(resultSet.getString(155),resultSet.getString(156),resultSet.getString(157)));
                    mvtoEquipo.getArticulo().setBaseDatos(new BaseDatos( resultSet.getString(256)));
                    TipoArticulo tipoArticulo = new TipoArticulo();
                        tipoArticulo.setCodigo(resultSet.getString(247));
                        tipoArticulo.setDescripcion(resultSet.getString(248));
                        tipoArticulo.setCodigoERP(resultSet.getString(249));
                        tipoArticulo.setUnidadNegocio(resultSet.getString(250));
                        tipoArticulo.setEstado(resultSet.getString(251));
                    mvtoEquipo.getArticulo().setTipoArticulo(tipoArticulo);
                    mvtoEquipo.setFechaHoraInicio(resultSet.getString(158));
                mvtoEquipo.setFechaHoraFin(resultSet.getString(159));
                mvtoEquipo.setTotalMinutos(resultSet.getString(160));
                mvtoEquipo.setValorHora(resultSet.getString(161));
                mvtoEquipo.setCostoTotal(resultSet.getString(162));
                mvtoEquipo.setCostoTotalRecobroCliente(resultSet.getString(186));
                        Recobro recobro = new Recobro();
                        recobro.setCodigo(resultSet.getString(164));
                        recobro.setDescripcion(resultSet.getString(165));
                        recobro.setEstado(resultSet.getString(166));
                mvtoEquipo.setRecobro(recobro);
                        ClienteRecobro ClienteRecobro = new ClienteRecobro();
                        ClienteRecobro.setCodigo(resultSet.getString(168));
                        Cliente cliente_recobro = new Cliente();
                        cliente_recobro.setCodigo(resultSet.getString(170));
                        cliente_recobro.setDescripcion(resultSet.getString(171));
                        cliente_recobro.setEstado(resultSet.getString(172));
                        ClienteRecobro.setCliente(cliente_recobro);
                        Usuario usuario_recobre = new Usuario();
                        usuario_recobre.setCodigo(resultSet.getString(174));
                        //usuario_recobre.setClave(resultSet.getString(244));
                        usuario_recobre.setNombres(resultSet.getString(176));
                        usuario_recobre.setApellidos(resultSet.getString(177));
                        usuario_recobre.setPerfilUsuario(new Perfil(resultSet.getString(179),resultSet.getString(180),resultSet.getString(181)));
                        usuario_recobre.setCorreo(resultSet.getString(182));
                        usuario_recobre.setEstado(resultSet.getString(183));
                        ClienteRecobro.setUsuario(usuario_recobre);
                        ClienteRecobro.setValorRecobro(resultSet.getString(184));
                        ClienteRecobro.setFechaRegistro(resultSet.getString(185));
                    mvtoEquipo.setClienteRecobro(ClienteRecobro);
                    mvtoEquipo.setCostoTotalRecobroCliente(resultSet.getString(186));
                        Usuario usuario_me_registra = new Usuario();
                        usuario_me_registra.setCodigo(resultSet.getString(188));
                        //usuario_me_registra.setClave(resultSet.getString(258));
                        usuario_me_registra.setNombres(resultSet.getString(190));
                        usuario_me_registra.setApellidos(resultSet.getString(191));
                        usuario_me_registra.setPerfilUsuario(new Perfil(resultSet.getString(193),resultSet.getString(194),resultSet.getString(195)));
                        usuario_me_registra.setCorreo(resultSet.getString(196));
                        usuario_me_registra.setEstado(resultSet.getString(197));
                    mvtoEquipo.setUsuarioQuieRegistra(usuario_me_registra);
                        Usuario usuario_me_autoriza = new Usuario();
                        usuario_me_autoriza.setCodigo(resultSet.getString(199));
                        //usuario_me_autoriza.setClave(resultSet.getString(269));
                        usuario_me_autoriza.setNombres(resultSet.getString(201));
                        usuario_me_autoriza.setApellidos(resultSet.getString(202));
                        usuario_me_autoriza.setPerfilUsuario(new Perfil(resultSet.getString(204),resultSet.getString(205),resultSet.getString(206)));
                        usuario_me_autoriza.setCorreo(resultSet.getString(207));
                        usuario_me_autoriza.setEstado(resultSet.getString(208));
                    mvtoEquipo.setUsuarioAutorizaRecobro(usuario_me_autoriza);
                        AutorizacionRecobro autorizacionRecobro = new AutorizacionRecobro();
                        autorizacionRecobro.setCodigo(resultSet.getString(210));
                        autorizacionRecobro.setDescripcion(resultSet.getString(211));
                        autorizacionRecobro.setEstado(resultSet.getString(212));
                    mvtoEquipo.setAutorizacionRecobro(autorizacionRecobro);
                    mvtoEquipo.setObservacionAutorizacion(resultSet.getString(213));
                    mvtoEquipo.setInactividad(resultSet.getString(214));
                        CausaInactividad causaInactividad = new CausaInactividad();
                        causaInactividad.setCodigo(resultSet.getString(216));
                        causaInactividad.setDescripcion(resultSet.getString(217));
                        causaInactividad.setEstado(resultSet.getString(218));
                    mvtoEquipo.setCausaInactividad(causaInactividad);
                        Usuario usuario_me_us_inactividad = new Usuario();
                        usuario_me_us_inactividad.setCodigo(resultSet.getString(220));
                        //usuario_me_us_inactividad.setClave(resultSet.getString(221));
                        usuario_me_us_inactividad.setNombres(resultSet.getString(222));
                        usuario_me_us_inactividad.setApellidos(resultSet.getString(223));
                        usuario_me_us_inactividad.setPerfilUsuario(new Perfil(resultSet.getString(225),resultSet.getString(226),resultSet.getString(227)));
                        usuario_me_us_inactividad.setCorreo(resultSet.getString(228));
                        usuario_me_us_inactividad.setEstado(resultSet.getString(229));
                    mvtoEquipo.setMotivoParadaEstado(resultSet.getString(230));
                        mvtoEquipo.setUsuarioInactividad(usuario_me_us_inactividad);
                        MotivoParada motivoParada= new MotivoParada();
                        motivoParada.setCodigo(resultSet.getString(232));
                        motivoParada.setDescripcion(resultSet.getString(233));
                        motivoParada.setEstado(resultSet.getString(234));
                    mvtoEquipo.setMotivoParada(motivoParada);
                    mvtoEquipo.setObservacionMvtoEquipo(resultSet.getString(235));
                    mvtoEquipo.setEstado(resultSet.getString(236));
                    mvtoEquipo.setDesdeCarbon(resultSet.getString(237));
                    mvtoEquipo.setMes(resultSet.getString(252));
                    //mvtoEquipo.setTotalMinutos(resultSet.getString(307));
                    /*TarifaEquipo tarifa=new ControlDB_Equipo(tipoConexion).buscarTarifaEquipoPorAño(mvtoEquipo.getAsignacionEquipo().getEquipo().getCodigo(),mvtoEquipo.getFechaHoraInicio());
                    if(mvtoEquipo.getFechaHoraFin() != null){
                        mvtoEquipo.setValorHora(tarifa.getValorHoraOperacion());
                        try{
                            double des=Double.parseDouble(mvtoEquipo.getTotalMinutos());
                            double totalHoras = Double.parseDouble(""+(des/60));
                            double valorHora =Double.parseDouble(tarifa.getValorHoraOperacion());
                            //mvtoEquipo.setCostoTotal(""+(totalHoras * valorHora ));  
                            DecimalFormat formato2 = new DecimalFormat("0.00");
                            mvtoEquipo.setCostoTotal(formato2.format((totalHoras * valorHora )));
                            mvtoEquipo.setCostoTotalRecobroCliente(formato2.format((totalHoras * valorHora )));
                        }catch(Exception e){
                            //mvto_listEquipo.getMvtoEquipo().setCostoTotal("NO PUDE");
                        }
                    }  */  
                listadoObjetos.add(mvtoEquipo);
            }
        }catch (SQLException sqlException) {
            System.out.println("Error al tratar al consultar los Movimientos de Carbon");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public ArrayList<MvtoEquipo>                    buscar_MvtoFechas(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<MvtoEquipo> listadoObjetos = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT\t [me_cdgo]   -- 1\n" +
                    "\t\t\t,[me_asignacion_equipo_cdgo]   --2\n" +
                    "\t\t\t,[ae_cdgo] --3\n" +
                    "\t\t\t\t,ae_cntro_oper_cdgo --4\n" +
                    "\t\t\t\t\t--Centro Operacion\n" +
                    "\t\t\t\t\t,ae_co_cdgo --5\n" +
                    "\t\t\t\t\t,ae_co_desc --6\n" +
                    "\t\t\t\t\t,CASE ae_co_estado WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_co_estado --7\n" +
                    "\t\t\t\t,ae_solicitud_listado_equipo_cdgo --8\n" +
                    "\t\t\t\t\t-- Solicitud Listado Equipo\n" +
                    "\t\t\t\t\t,ae_sle_cdgo --9\n" +
                    "\t\t\t\t\t,ae_sle_solicitud_equipo_cdgo --10\n" +
                    "\t\t\t\t\t\t\t--Solicitud Equipo\n" +
                    "\t\t\t\t\t\t\t,ae_sle_se_cdgo --11\n" +
                    "\t\t\t\t\t\t\t,ae_sle_se_cntro_oper_cdgo --12\n" +
                    "\t\t\t\t\t\t\t\t--CentroOperación SolicitudEquipo\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_co_cdgo --13\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_co_desc --14\n" +
                    "\t\t\t\t\t\t\t\t,CASE ae_sle_se_co_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_co_estad --15\n" +
                    "\t\t\t\t\t\t\t,ae_sle_se_fecha --16\n" +
                    "\t\t\t\t\t\t\t,ae_sle_se_usuario_realiza_cdgo --17\n" +
                    "\t\t\t\t\t\t\t\t--Usuario SolicitudEquipo\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_registra_cdgo --18\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_registra_clave --19\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_registra_nombres --20\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_registra_apellidos --21\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_registra_perfil_cdgo --22\n" +
                    "\t\t\t\t\t\t\t\t\t\t--Perfil Usuario Quien Registra Manual\n" +
                    "\t\t\t\t\t\t\t\t\t\t,ae_sle_se_prf_us_registra_cdgo --23\n" +
                    "\t\t\t\t\t\t\t\t\t\t,ae_sle_se_prf_us_registra_desc --24\n" +
                    "\t\t\t\t\t\t\t\t\t\t,CASE ae_sle_se_prf_us_registra_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_prf_us_registra_estad --25\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_registra_correo --26\n" +
                    "\t\t\t\t\t\t\t\t,CASE ae_sle_se_us_registra_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_us_registra_estad --27\n" +
                    "\t\t\t\t\t\t\t,ae_sle_se_fecha_registro --28\n" +
                    "\t\t\t\t\t\t\t,ae_sle_se_estado_solicitud_equipo_cdgo --29\n" +
                    "\t\t\t\t\t\t\t\t--Estado de la solicitud\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_ese_cdgo --30\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_ese_desc --31\n" +
                    "\t\t\t\t\t\t\t\t,CASE ae_sle_se_ese_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_ese_estad --32\n" +
                    "\t\t\t\t\t\t\t,ae_sle_se_fecha_confirmacion --33\n" +
                    "\t\t\t\t\t\t\t,ae_se_usuario_confirma_cdgo --34\n" +
                    "\t\t\t\t\t\t\t\t--Usuario SolicitudEquipo\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_confirma_cdgo --35\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_confirma_clave --36\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_confirma_nombres --37\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_confirma_apellidos --38\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_confirma_perfil_cdgo --39\n" +
                    "\t\t\t\t\t\t\t\t\t\t--Perfil Usuario Quien Registra Manual\n" +
                    "\t\t\t\t\t\t\t\t\t\t,ae_sle_se_prf_us_confirma_cdgo --40\n" +
                    "\t\t\t\t\t\t\t\t\t\t,ae_sle_se_prf_us_confirma_desc --41\n" +
                    "\t\t\t\t\t\t\t\t\t\t,CASE ae_sle_se_prf_us_confirma_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_prf_us_confirma_estad --42\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_us_confirma_correo --43\n" +
                    "\t\t\t\t\t\t\t\t,CASE ae_sle_se_us_confirma_estado WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_us_confirma_estado --44\n" +
                    "\t\t\t\t\t\t\t,ae_sle_se_confirmacion_solicitud_equipo_cdgo --45\n" +
                    "\t\t\t\t\t\t\t\t--Confirmacion solicitudEquipo\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_cse_cdgo --46\n" +
                    "\t\t\t\t\t\t\t\t,ae_sle_se_ces_desc --47\n" +
                    "\t\t\t\t\t\t\t\t,CASE ae_sle_se_ces_estado WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_ces_estado --48\n" +
                    "\t\t\t\t\t,ae_sle_tipo_equipo_cdgo --49\n" +
                    "\t\t\t\t\t\t--Tipo de Equipo\n" +
                    "\t\t\t\t\t\t,ae_sle_te_cdgo --50\n" +
                    "\t\t\t\t\t\t,ae_sle_te_desc --51\n" +
                    "\t\t\t\t\t\t,CASE ae_sle_te_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_te_estad --52\n" +
                    "\t\t\t\t\t,ae_sle_marca_equipo --53\n" +
                    "\t\t\t\t\t,ae_sle_modelo_equipo --54\n" +
                    "\t\t\t\t\t,ae_sle_cant_equip --55\n" +
                    "\t\t\t\t\t,ae_sle_observ --56\n" +
                    "\t\t\t\t\t,ae_sle_fecha_hora_inicio --57\n" +
                    "\t\t\t\t\t,ae_sle_fecha_hora_fin --58\n" +
                    "\t\t\t\t\t,ae_sle_cant_minutos --59\n" +
                    "\t\t\t\t\t,ae_sle_labor_realizada_cdgo --60\n" +
                    "\t\t\t\t\t-- Labor Realizada\n" +
                    "\t\t\t\t\t\t\t,ae_sle_lr_cdgo --61\n" +
                    "\t\t\t\t\t\t\t,ae_sle_lr_desc --62\n" +
                    "\t\t\t\t\t\t\t,CASE ae_sle_lr_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_ao_estad --63\n" +
                    "\t\t\t\t\t,ae_sle_sle_motonave_cdgo --64\n" +
                    "\t\t\t\t\t--Motonave\n" +
                    "\t\t\t\t\t\t\t,ae_sle_mn_cdgo --65\n" +
                    "\t\t\t\t\t\t\t,ae_sle_mn_desc --66\n" +
                    "\t\t\t\t\t\t\t,CASE ae_sle_mn_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_mn_estad --67\n" +
                    "\t\t\t\t\t,ae_sle_cntro_cost_auxiliar_cdgo --68\n" +
                    "\t\t\t\t\t\t--Centro Costo Auxiliar\n" +
                    "\t\t\t\t\t\t,ae_sle_cca_cdgo --69\n" +
                    "\t\t\t\t\t\t,ae_sle_cca_cntro_cost_subcentro_cdgo --70\n" +
                    "\t\t\t\t\t\t\t-- SubCentro de Costo\n" +
                    "\t\t\t\t\t\t\t,ae_sle_ccs_cdgo --71\n" +
                    "\t\t\t\t\t\t\t,ae_sle_ccs_desc --72\n" +
                    "\t\t\t\t\t\t\t,CASE ae_sle_ccs_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_ccs_estad --73\n" +
                    "\t\t\t\t\t\t,ae_sle_cca_desc --74\n" +
                    "\t\t\t\t\t\t,CASE ae_sle_cca_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_cca_estad --75\n" +
                    "\t\t\t\t\t,ae_sle_compania_cdgo --76\n" +
                    "\t\t\t\t\t\t--Compañia\n" +
                    "\t\t\t\t\t\t,ae_sle_cp_cdgo --77\n" +
                    "\t\t\t\t\t\t,ae_sle_cp_desc --78\n" +
                    "\t\t\t\t\t\t,CASE ae_sle_cp_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_cp_estad --79\n" +
                    "\t\t\t\t,ae_fecha_registro --80\n" +
                    "\t\t\t\t,ae_fecha_hora_inicio --81\n" +
                    "\t\t\t\t,ae_fecha_hora_fin --82\n" +
                    "\t\t\t\t,ae_cant_minutos --83\n" +
                    "\t\t\t\t,ae_equipo_cdgo --84\n" +
                    "\t\t\t\t\t--Equipo\n" +
                    "\t\t\t\t\t,ae_eq_cdgo --85\n" +
                    "\t\t\t\t\t,ae_eq_tipo_equipo_cdgo --86\n" +
                    "\t\t\t\t\t\t--Tipo Equipo\n" +
                    "\t\t\t\t\t\t,ae_eq_te_cdgo --87\n" +
                    "\t\t\t\t\t\t,ae_eq_te_desc --88\n" +
                    "\t\t\t\t\t\t,CASE ae_eq_te_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_te_estad --89\n" +
                    "\t\t\t\t\t,ae_eq_codigo_barra --90\n" +
                    "\t\t\t\t\t,ae_eq_referencia --91\n" +
                    "\t\t\t\t\t,ae_eq_producto --92\n" +
                    "\t\t\t\t\t,ae_eq_capacidad --93\n" +
                    "\t\t\t\t\t,ae_eq_marca --94\n" +
                    "\t\t\t\t\t,ae_eq_modelo --95\n" +
                    "\t\t\t\t\t,ae_eq_serial --96\n" +
                    "\t\t\t\t\t,ae_eq_desc --97\n" +
                    "\t\t\t\t\t,ae_eq_clasificador1_cdgo --98\n" +
                    "\t\t\t\t\t\t-- Clasificador 1\n" +
                    "\t\t\t\t\t\t,ae_eq_ce1_cdgo --99\n" +
                    "\t\t\t\t\t\t,ae_eq_ce1_desc --100\n" +
                    "\t\t\t\t\t\t,CASE ae_eq_ce1_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_ce1_estad --101\n" +
                    "\t\t\t\t\t,ae_eq_clasificador2_cdgo --102\n" +
                    "\t\t\t\t\t\t-- Clasificador 2\n" +
                    "\t\t\t\t\t\t,ae_eq_ce2_cdgo --103\n" +
                    "\t\t\t\t\t\t,ae_eq_ce2_desc --104\n" +
                    "\t\t\t\t\t\t,CASE ae_eq_ce2_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_ce2_estad --105\n" +
                    "\t\t\t\t\t,ae_eq_proveedor_equipo_cdgo --106\n" +
                    "\t\t\t\t\t\t--Proveedor Equipo\n" +
                    "\t\t\t\t\t\t,ae_eq_pe_cdgo --107\n" +
                    "\t\t\t\t\t\t,ae_eq_pe_nit --108\n" +
                    "\t\t\t\t\t\t,ae_eq_pe_desc --109\n" +
                    "\t\t\t\t\t\t,CASE ae_eq_pe_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_pe_estad --110\n" +
                    "\t\t\t\t\t,ae_eq_equipo_pertenencia_cdgo --111\n" +
                    "\t\t\t\t\t\t-- Equipo Pertenencia\n" +
                    "\t\t\t\t\t\t,ae_eq_ep_cdgo --112\n" +
                    "\t\t\t\t\t\t,ae_eq_ep_desc --113\n" +
                    "\t\t\t\t\t\t,CASE ae_eq_ep_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_ep_estad --114\n" +
                    "\t\t\t\t\t,ae_eq_eq_observ --115\n" +
                    "\t\t\t\t\t,CASE ae_eq_eq_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_eq_estad --116\n" +
                    "\t\t\t\t\t,ae_eq_actvo_fijo_id --117\n" +
                    "\t\t\t\t\t,ae_eq_actvo_fijo_referencia --118\n" +
                    "\t\t\t\t\t,ae_eq_actvo_fijo_desc --119\n" +
                    "\t\t\t\t,ae_equipo_pertenencia_cdgo --120\n" +
                    "\t\t\t\t-- Equipo Pertenencia\n" +
                    "\t\t\t\t\t\t,ae_ep_cdgo --121\n" +
                    "\t\t\t\t\t\t,ae_ep_desc --122\n" +
                    "\t\t\t\t\t\t,CASE ae_ep_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_ep_estad --123\n" +
                    "\t\t\t\t,ae_cant_hora_operativa --124\n" +
                    "\t\t\t\t,ae_cant_hora_parada --125\n" +
                    "\t\t\t\t,ae_cant_hora_total --126\n" +
                    "\t\t\t\t,CASE ae_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_estad --127\n" +
                    "\t\t\t,[me_fecha]   --128\n" +
                    "\t\t\t,[me_proveedor_equipo_cdgo]   --129\n" +
                    "\t\t\t\t--Proveedor Equipo\n" +
                    "\t\t\t\t,[pe_cdgo] AS me_pe_cdgo   --130\n" +
                    "\t\t\t\t,[pe_nit] AS me_pe_nit   --131\n" +
                    "\t\t\t\t,[pe_desc] AS me_pe_desc   --132\n" +
                    "\t\t\t\t,CASE [pe_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS me_pe_estad   --133\n" +
                    "\t\t\t,[me_num_orden]   --134\n" +
                    "\t\t\t,[me_cntro_cost_auxiliar_cdgo]   --135\n" +
                    "\t\t\t\t-- Centro Costo Auxiliar\n" +
                    "\t\t\t\t,me_cntro_cost_auxiliar.[cca_cdgo] AS me_cca_cdgo   --136\n" +
                    "\t\t\t\t,me_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo] AS me_cca_cntro_cost_subcentro_cdgo   --137\n" +
                    "\t\t\t\t\t--Centro Costo Subcentro\n" +
                    "\t\t\t\t\t,me_cntro_cost_subcentro.[ccs_cdgo]   --138\n" +
                    "\t\t\t\t\t,me_cntro_cost_subcentro.[ccs_desc]   --139\n" +
                    "\t\t\t\t\t,CASE me_cntro_cost_subcentro.[ccs_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ccs_estad]   --140\n" +
                    "\t\t\t\t,me_cntro_cost_auxiliar.[cca_desc] AS me_cca_desc   --141\n" +
                    "\t\t\t\t,CASE me_cntro_cost_auxiliar.[cca_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cca_estad]   --142\n" +
                    "\t\t\t,[me_labor_realizada_cdgo]   --143\n" +
                    "\t\t\t\t--Labor Realizada\n" +
                    "\t\t\t\t,[lr_cdgo] --144\n" +
                    "\t\t\t\t,[lr_desc] --145\n" +
                    "\t\t\t\t,[lr_cntro_cost_subcentro_cdgo] --146\n" +
                    "\t\t\t\t,CASE [lr_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [lr_estad]   --147\n" +
                    "\t\t\t\t,[lr_operativa] --148\n" +
                    "\t\t\t\t,[lr_parada] --149\n" +
                    "\t\t\t,[me_cliente_cdgo]   --150\n" +
                    "\t\t\t\t--Cliente \n" +
                    "\t\t\t\t,me_cliente.[cl_cdgo]   --151\n" +
                    "\t\t\t\t,me_cliente.[cl_desc]   --152\n" +
                    "\t\t\t\t,CASE me_cliente.[cl_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cl_estad]   --153\n" +
                    "\t\t\t,[me_articulo_cdgo]   --154\n" +
                    "\t\t\t\t--Producto\n" +
                    "\t\t\t\t,[ar_cdgo]   --155\n" +
                    "\t\t\t\t,[ar_desc]   --156\n" +
                    "\t\t\t\t,CASE [ar_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ar_estad]   --157\n" +
                    "\t\t\t,[me_fecha_hora_inicio]   --158\n" +
                    "\t\t\t,[me_fecha_hora_fin]   --159\n" +
                    "\t\t\t,[me_total_minutos]   --160\n" +
                    "\t\t\t,[me_valor_hora]   --161\n" +
                    "\t\t\t,[me_costo_total]   --162\n" +
                    "\t\t\t,[me_recobro_cdgo]   --163\n" +
                    "\t\t\t\t--Recobro\n" +
                    "\t\t\t\t,[rc_cdgo]   --164\n" +
                    "\t\t\t\t,[rc_desc]   --165\n" +
                    "\t\t\t\t,CASE [rc_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [rc_estad]   --166\n" +
                    "\t\t\t,[me_cliente_recobro_cdgo]   --167\n" +
                    "\t\t\t\t--Cliente Recobro\n" +
                    "\t\t\t\t,[clr_cdgo]   --168\n" +
                    "\t\t\t\t,[clr_cliente_cdgo]   --169\n" +
                    "\t\t\t\t\t--Cliente\n" +
                    "\t\t\t\t\t,me_clr_cliente.[cl_cdgo]   --170   \n" +
                    "\t\t\t\t\t,me_clr_cliente.[cl_desc]   --171\n" +
                    "\t\t\t\t\t,CASE me_clr_cliente.[cl_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cl_estad]   --172\n" +
                    "\t\t\t\t,[clr_usuario_cdgo]   --173\n" +
                    "\t\t\t\t\t--Usuario Quien Registra Recobro\n" +
                    "\t\t\t\t\t,me_clr_usuario.[us_cdgo]   --174\n" +
                    "\t\t\t\t\t,me_clr_usuario.[us_clave]   --175\n" +
                    "\t\t\t\t\t,me_clr_usuario.[us_nombres]   --176\n" +
                    "\t\t\t\t\t,me_clr_usuario.[us_apellidos]   --177\n" +
                    "\t\t\t\t\t,me_clr_usuario.[us_perfil_cdgo]   --178\n" +
                    "\t\t\t\t\t\t--Perfil Usuario Registra recobro\n" +
                    "\t\t\t\t\t\t,me_clr_us_perfil.[prf_cdgo]   --179\n" +
                    "\t\t\t\t\t\t,me_clr_us_perfil.[prf_desc]   --180\n" +
                    "\t\t\t\t\t\t,CASE me_clr_us_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad]   --181\n" +
                    "\t\t\t\t\t,me_clr_usuario.[us_correo]   --182\n" +
                    "\t\t\t\t\t,CASE me_clr_usuario.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad]   --183\n" +
                    "\t\t\t\t,[clr_valor_recobro]   --184\n" +
                    "\t\t\t\t,[clr_fecha_registro]   --185\n" +
                    "\t\t\t,[me_costo_total_recobro_cliente]   --186\n" +
                    "\t\t\t,[me_usuario_registro_cdgo]   --187\n" +
                    "\t\t\t\t--Usuario Quien Registra Equipo\n" +
                    "\t\t\t\t,me_us_registro.[us_cdgo]   --188\n" +
                    "\t\t\t\t,me_us_registro.[us_clave]   --189\n" +
                    "\t\t\t\t,me_us_registro.[us_nombres]   --190\n" +
                    "\t\t\t\t,me_us_registro.[us_apellidos]   --191\n" +
                    "\t\t\t\t,me_us_registro.[us_perfil_cdgo]   --192\n" +
                    "\t\t\t\t\t--Perfil de Usuario Quien Registra Equipo\n" +
                    "\t\t\t\t\t,me_us_regist_perfil.[prf_cdgo]   --193\n" +
                    "\t\t\t\t\t,me_us_regist_perfil.[prf_desc]   --194\n" +
                    "\t\t\t\t\t,CASE me_us_regist_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad]   --195\n" +
                    "\t\t\t\t,me_us_registro.[us_correo]   --196\n" +
                    "\t\t\t\t,CASE me_us_registro.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad]   --197\n" +
                    "\t\t\t,[me_usuario_autorizacion_cdgo]   --198\n" +
                    "\t\t\t\t--Usuario quien autoriza\n" +
                    "\t\t\t\t,me_us_autorizacion.[us_cdgo]   --199\n" +
                    "\t\t\t\t,me_us_autorizacion.[us_clave]   --200\n" +
                    "\t\t\t\t,me_us_autorizacion.[us_nombres]   --201\n" +
                    "\t\t\t\t,me_us_autorizacion.[us_apellidos]   --202\n" +
                    "\t\t\t\t,me_us_autorizacion.[us_perfil_cdgo]   --203\n" +
                    "\t\t\t\t\t--Perfil quien autoriza\n" +
                    "\t\t\t\t\t,me_us_autoriza_perfil.[prf_cdgo]   --204\n" +
                    "\t\t\t\t\t,me_us_autoriza_perfil.[prf_desc]   --205\n" +
                    "\t\t\t\t\t,CASE me_us_autoriza_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad]   --206\n" +
                    "\t\t\t\t,me_us_autorizacion.[us_correo]   --207\n" +
                    "\t\t\t\t,CASE me_us_autorizacion.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad]   --208\n" +
                    "\t\t\t,[me_autorizacion_recobro_cdgo]   --209\n" +
                    "\t\t\t\t,[are_cdgo]   --210\n" +
                    "\t\t\t\t,[are_desc]   --211\n" +
                    "\t\t\t\t,CASE [are_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [are_estad]   --212\n" +
                    "\t\t\t,[me_observ_autorizacion]   --213\n" +
                    "\t\t\t,[me_inactividad]   --214\n" +
                    "\t\t\t,[me_causa_inactividad_cdgo]   --215\n" +
                    "\t\t\t\t,[ci_cdgo]   --216\n" +
                    "\t\t\t\t,[ci_desc]   --217\n" +
                    "\t\t\t\t,CASE [ci_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ci_estad]   --218\n" +
                    "\t\t\t,[me_usuario_inactividad_cdgo]   --219\n" +
                    "\t\t\t\t--Usuario de Inactividad\n" +
                    "\t\t\t\t,me_us_inactividad.[us_cdgo]   --220\n" +
                    "\t\t\t\t,me_us_inactividad.[us_clave]   --221\n" +
                    "\t\t\t\t,me_us_inactividad.[us_nombres]   --222\n" +
                    "\t\t\t\t,me_us_inactividad.[us_apellidos]   --223\n" +
                    "\t\t\t\t,me_us_inactividad.[us_perfil_cdgo]   --224\n" +
                    "\t\t\t\t\t,me_us_inactvdad_perfil.[prf_cdgo]   --225\n" +
                    "\t\t\t\t\t,me_us_inactvdad_perfil.[prf_desc]   --226\n" +
                    "\t\t\t\t\t,CASE me_us_inactvdad_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad]   --227\n" +
                    "\t\t\t\t,me_us_inactividad.[us_correo]   --228\n" +
                    "\t\t\t\t,CASE me_us_inactividad.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad]\t   --229\n" +
                    "\t\t\t,[me_motivo_parada_estado]   --230\n" +
                    "\t\t\t,[me_motivo_parada_cdgo]   --231\n" +
                    "\t\t\t\t--Motivo de Parada\n" +
                    "\t\t\t\t,[mpa_cdgo]   --232\n" +
                    "\t\t\t\t,[mpa_desc]   --233\n" +
                    "\t\t\t\t,[mpa_estad]   --234\n" +
                    "\t\t\t,[me_observ]   --235\n" +
                    "\t\t\t,CASE [me_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_estado]\t   --236\n" +
                    "\t\t\t,CASE [me_desde_mvto_carbon] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [me_desde_mvto_carbon]   --237\n" +
                    "   ,[co_cdgo] --238\n" +
                    "   ,[co_desc] --239\n" +
                    "  ,CASE [co_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [co_estad]	   --240\n" +
                    "      ,[ae_sle_mn_base_datos_cdgo]--241\n" +
                    "      ,me_cliente_base_datos.[bd_cdgo]   --242 \n" +
                    "      ,me_articulo_base_datos.[bd_cdgo]   --243 \n" +
                    "\t FROM ["+DB+"].[dbo].[mvto_equipo]\n" +
                    "\tINNER JOIN (SELECT [ae_cdgo] AS ae_cdgo\n" +
                    "\t\t\t\t\t  ,[ae_cntro_oper_cdgo] AS ae_cntro_oper_cdgo\n" +
                    "\t\t\t\t\t\t\t--Centro Operacion\n" +
                    "\t\t\t\t\t\t\t,ae_cntro_oper.[co_cdgo] AS ae_co_cdgo\n" +
                    "\t\t\t\t\t\t\t,ae_cntro_oper.[co_desc] AS ae_co_desc\n" +
                    "\t\t\t\t\t\t\t,ae_cntro_oper.[co_estad] AS ae_co_estado\n" +
                    "\t\t\t\t\t  ,[ae_solicitud_listado_equipo_cdgo] AS ae_solicitud_listado_equipo_cdgo\n" +
                    "\t\t\t\t\t\t\t-- Solicitud Listado Equipo\n" +
                    "\t\t\t\t\t\t\t,[sle_cdgo] AS ae_sle_cdgo\n" +
                    "\t\t\t\t\t\t\t,[sle_solicitud_equipo_cdgo] AS ae_sle_solicitud_equipo_cdgo\n" +
                    "\t\t\t\t\t\t\t\t  --Solicitud Equipo\n" +
                    "\t\t\t\t\t\t\t\t  ,[se_cdgo] AS ae_sle_se_cdgo\n" +
                    "\t\t\t\t\t\t\t\t  ,[se_cntro_oper_cdgo] AS ae_sle_se_cntro_oper_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t--CentroOperación SolicitudEquipo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_cntro_oper.[co_cdgo] AS ae_sle_se_co_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_cntro_oper.[co_desc] AS ae_sle_se_co_desc\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_cntro_oper.[co_estad] AS ae_sle_se_co_estad\n" +
                    "\t\t\t\t\t\t\t\t  ,[se_fecha] AS ae_sle_se_fecha\n" +
                    "\t\t\t\t\t\t\t\t  ,[se_usuario_realiza_cdgo] AS ae_sle_se_usuario_realiza_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t--Usuario SolicitudEquipo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_realiza.[us_cdgo] AS ae_sle_se_us_registra_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_realiza.[us_clave] AS ae_sle_se_us_registra_clave\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_realiza.[us_nombres] AS ae_sle_se_us_registra_nombres\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_realiza.[us_apellidos] AS ae_sle_se_us_registra_apellidos\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_realiza.[us_perfil_cdgo] AS ae_sle_se_us_registra_perfil_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t--Perfil Usuario Quien Registra Manual\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t,ae_prf_registra.[prf_cdgo] AS ae_sle_se_prf_us_registra_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t,ae_prf_registra.[prf_desc] AS ae_sle_se_prf_us_registra_desc\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t,ae_prf_registra.[prf_estad] AS ae_sle_se_prf_us_registra_estad\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_realiza.[us_correo] AS ae_sle_se_us_registra_correo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_realiza.[us_estad] AS ae_sle_se_us_registra_estad\n" +
                    "\t\t\t\t\t\t\t\t  ,[se_fecha_registro] AS ae_sle_se_fecha_registro\n" +
                    "\t\t\t\t\t\t\t\t  ,[se_estado_solicitud_equipo_cdgo] AS ae_sle_se_estado_solicitud_equipo_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t--Estado de la solicitud\n" +
                    "\t\t\t\t\t\t\t\t\t\t,[ese_cdgo] AS ae_sle_se_ese_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,[ese_desc] AS ae_sle_se_ese_desc\n" +
                    "\t\t\t\t\t\t\t\t\t\t,[ese_estad] AS ae_sle_se_ese_estad\n" +
                    "\t\t\t\t\t\t\t\t  ,[se_fecha_confirmacion] AS ae_sle_se_fecha_confirmacion\n" +
                    "\t\t\t\t\t\t\t\t  ,[se_usuario_confirma_cdgo] AS ae_se_usuario_confirma_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t--Usuario SolicitudEquipo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_confirma.[us_cdgo] AS ae_sle_se_us_confirma_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_confirma.[us_clave] AS ae_sle_se_us_confirma_clave\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_confirma.[us_nombres] AS ae_sle_se_us_confirma_nombres\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_confirma.[us_apellidos] AS ae_sle_se_us_confirma_apellidos\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_confirma.[us_perfil_cdgo] AS ae_sle_se_us_confirma_perfil_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t--Perfil Usuario Quien Registra Manual\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t,ae_prf_registra_confirma.[prf_cdgo] AS ae_sle_se_prf_us_confirma_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t,ae_prf_registra_confirma.[prf_desc] AS ae_sle_se_prf_us_confirma_desc\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t,ae_prf_registra_confirma.[prf_estad] AS ae_sle_se_prf_us_confirma_estad\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_confirma.[us_correo] AS ae_sle_se_us_confirma_correo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,se_usuario_confirma.[us_estad] AS ae_sle_se_us_confirma_estado\n" +
                    "\t\t\t\t\t\t\t\t  ,[se_confirmacion_solicitud_equipo_cdgo] AS ae_sle_se_confirmacion_solicitud_equipo_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t--Confirmacion solicitudEquipo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,[cse_cdgo] AS ae_sle_se_cse_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t\t,[cse_desc] AS ae_sle_se_ces_desc\n" +
                    "\t\t\t\t\t\t\t\t\t\t,[cse_estad] AS ae_sle_se_ces_estado\n" +
                    "\t\t\t\t\t\t\t,[sle_tipo_equipo_cdgo] AS ae_sle_tipo_equipo_cdgo\n" +
                    "\t\t\t\t\t\t\t\t--Tipo de Equipo\n" +
                    "\t\t\t\t\t\t\t\t,sle_tipoEquipo.[te_cdgo] AS ae_sle_te_cdgo\n" +
                    "\t\t\t\t\t\t\t\t,sle_tipoEquipo.[te_desc] AS ae_sle_te_desc\n" +
                    "\t\t\t\t\t\t\t\t,sle_tipoEquipo.[te_estad] AS ae_sle_te_estad\n" +
                    "\t\t\t\t\t\t\t,[sle_marca_equipo] AS ae_sle_marca_equipo\n" +
                    "\t\t\t\t\t\t\t,[sle_modelo_equipo] AS ae_sle_modelo_equipo\n" +
                    "\t\t\t\t\t\t\t,[sle_cant_equip] AS ae_sle_cant_equip\n" +
                    "\t\t\t\t\t\t\t,[sle_observ] AS ae_sle_observ\n" +
                    "\t\t\t\t\t\t\t,[sle_fecha_hora_inicio] AS ae_sle_fecha_hora_inicio\n" +
                    "\t\t\t\t\t\t\t,[sle_fecha_hora_fin] AS ae_sle_fecha_hora_fin\n" +
                    "\t\t\t\t\t\t\t,[sle_cant_minutos] AS   ae_sle_cant_minutos\n" +
                    "\t\t\t\t\t\t\t,[sle_labor_realizada_cdgo] AS ae_sle_labor_realizada_cdgo\n" +
                    "\t\t\t\t\t\t\t-- Labor Realizada\n" +
                    "\t\t\t\t\t\t\t\t  ,[lr_cdgo] AS ae_sle_lr_cdgo\n" +
                    "\t\t\t\t\t\t\t\t  ,[lr_desc] AS ae_sle_lr_desc\n" +
                    "\t\t\t\t\t\t\t\t  ,[lr_estad] AS ae_sle_lr_estad\n" +
                    "\t\t\t\t\t\t\t,[sle_motonave_cdgo] AS ae_sle_sle_motonave_cdgo\n" +
                    "\t\t\t\t\t\t\t--Motonave\n" +
                    "\t\t\t\t\t\t\t\t  ,[mn_cdgo] AS ae_sle_mn_cdgo\n" +
                    "\t\t\t\t\t\t\t\t  ,[mn_desc] AS ae_sle_mn_desc\n" +
                    "\t\t\t\t\t\t\t\t  ,[mn_estad] AS ae_sle_mn_estad\n" +
                    "\t\t\t\t\t\t\t,[sle_cntro_cost_auxiliar_cdgo] AS ae_sle_cntro_cost_auxiliar_cdgo\n" +
                    "\t\t\t\t\t\t\t\t--Centro Costo Auxiliar\n" +
                    "\t\t\t\t\t\t\t\t,[cca_cdgo] AS ae_sle_cca_cdgo\n" +
                    "\t\t\t\t\t\t\t\t,[cca_cntro_cost_subcentro_cdgo] AS ae_sle_cca_cntro_cost_subcentro_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t-- SubCentro de Costo\n" +
                    "\t\t\t\t\t\t\t\t\t,[ccs_cdgo] AS ae_sle_ccs_cdgo\n" +
                    "\t\t\t\t\t\t\t\t\t,[ccs_desc] AS ae_sle_ccs_desc\n" +
                    "\t\t\t\t\t\t\t\t\t,[ccs_estad] AS ae_sle_ccs_estad\n" +
                    "\t\t\t\t\t\t\t\t,[cca_desc] AS ae_sle_cca_desc\n" +
                    "\t\t\t\t\t\t\t\t,[cca_estad] AS ae_sle_cca_estad\n" +
                    "\t\t\t\t\t\t\t,[sle_compania_cdgo] AS ae_sle_compania_cdgo\n" +
                    "\t\t\t\t\t\t\t\t--Compañia\n" +
                    "\t\t\t\t\t\t\t\t,[cp_cdgo] AS ae_sle_cp_cdgo\n" +
                    "\t\t\t\t\t\t\t\t,[cp_desc] AS ae_sle_cp_desc\n" +
                    "\t\t\t\t\t\t\t\t,[cp_estad] AS ae_sle_cp_estad\n" +
                    "\t\t\t\t\t  ,[ae_fecha_registro] AS ae_fecha_registro\n" +
                    "\t\t\t\t\t  ,[ae_fecha_hora_inicio] AS ae_fecha_hora_inicio\n" +
                    "\t\t\t\t\t  ,[ae_fecha_hora_fin] AS ae_fecha_hora_fin\n" +
                    "\t\t\t\t\t  ,[ae_cant_minutos] AS ae_cant_minutos\n" +
                    "\t\t\t\t\t  ,[ae_equipo_cdgo] AS ae_equipo_cdgo\n" +
                    "\t\t\t\t\t\t\t--Equipo\n" +
                    "\t\t\t\t\t\t\t,[eq_cdgo] AS ae_eq_cdgo\n" +
                    "\t\t\t\t\t\t\t,[eq_tipo_equipo_cdgo] AS ae_eq_tipo_equipo_cdgo\n" +
                    "\t\t\t\t\t\t\t\t--Tipo Equipo\n" +
                    "\t\t\t\t\t\t\t\t,eq_tipo_equipo.[te_cdgo] AS ae_eq_te_cdgo\n" +
                    "\t\t\t\t\t\t\t\t,eq_tipo_equipo.[te_desc] AS ae_eq_te_desc\n" +
                    "\t\t\t\t\t\t\t\t,eq_tipo_equipo.[te_estad] AS ae_eq_te_estad\n" +
                    "\t\t\t\t\t\t\t,[eq_codigo_barra] AS ae_eq_codigo_barra\n" +
                    "\t\t\t\t\t\t\t,[eq_referencia] AS ae_eq_referencia\n" +
                    "\t\t\t\t\t\t\t,[eq_producto] AS ae_eq_producto\n" +
                    "\t\t\t\t\t\t\t,[eq_capacidad] AS ae_eq_capacidad\n" +
                    "\t\t\t\t\t\t\t,[eq_marca] AS ae_eq_marca\n" +
                    "\t\t\t\t\t\t\t,[eq_modelo] AS ae_eq_modelo\n" +
                    "\t\t\t\t\t\t\t,[eq_serial] AS ae_eq_serial\n" +
                    "\t\t\t\t\t\t\t,[eq_desc] AS ae_eq_desc\n" +
                    "\t\t\t\t\t\t\t,[eq_clasificador1_cdgo] AS ae_eq_clasificador1_cdgo\n" +
                    "\t\t\t\t\t\t\t\t-- Clasificador 1\n" +
                    "\t\t\t\t\t\t\t\t,eq_clasificador1.[ce_cdgo] AS ae_eq_ce1_cdgo\n" +
                    "\t\t\t\t\t\t\t\t,eq_clasificador1.[ce_desc] AS ae_eq_ce1_desc\n" +
                    "\t\t\t\t\t\t\t\t,eq_clasificador1.[ce_estad] AS ae_eq_ce1_estad\n" +
                    "\t\t\t\t\t\t\t,[eq_clasificador2_cdgo] AS ae_eq_clasificador2_cdgo\n" +
                    "\t\t\t\t\t\t\t\t-- Clasificador 2\n" +
                    "\t\t\t\t\t\t\t\t,eq_clasificador2.[ce_cdgo] AS ae_eq_ce2_cdgo\n" +
                    "\t\t\t\t\t\t\t\t,eq_clasificador2.[ce_desc] AS ae_eq_ce2_desc\n" +
                    "\t\t\t\t\t\t\t\t,eq_clasificador2.[ce_estad] AS ae_eq_ce2_estad\n" +
                    "\t\t\t\t\t\t\t,[eq_proveedor_equipo_cdgo] AS ae_eq_proveedor_equipo_cdgo\n" +
                    "\t\t\t\t\t\t\t\t--Proveedor Equipo\n" +
                    "\t\t\t\t\t\t\t\t,[pe_cdgo] AS ae_eq_pe_cdgo\n" +
                    "\t\t\t\t\t\t\t\t,[pe_nit] AS ae_eq_pe_nit\n" +
                    "\t\t\t\t\t\t\t\t,[pe_desc] AS ae_eq_pe_desc\n" +
                    "\t\t\t\t\t\t\t\t,[pe_estad] AS ae_eq_pe_estad\n" +
                    "\t\t\t\t\t\t\t,[eq_equipo_pertenencia_cdgo] AS ae_eq_equipo_pertenencia_cdgo\n" +
                    "\t\t\t\t\t\t\t\t-- Equipo Pertenencia\n" +
                    "\t\t\t\t\t\t\t\t,eq_pertenencia.[ep_cdgo] AS ae_eq_ep_cdgo\n" +
                    "\t\t\t\t\t\t\t\t,eq_pertenencia.[ep_desc] AS ae_eq_ep_desc\n" +
                    "\t\t\t\t\t\t\t\t,eq_pertenencia.[ep_estad] AS ae_eq_ep_estad\n" +
                    "\t\t\t\t\t\t\t,[eq_observ] AS ae_eq_eq_observ\n" +
                    "\t\t\t\t\t\t\t,[eq_estad] AS ae_eq_eq_estad\n" +
                    "\t\t\t\t\t\t\t,[eq_actvo_fijo_id] AS ae_eq_actvo_fijo_id\n" +
                    "\t\t\t\t\t\t\t,[eq_actvo_fijo_referencia] AS ae_eq_actvo_fijo_referencia\n" +
                    "\t\t\t\t\t\t\t,[eq_actvo_fijo_desc] AS ae_eq_actvo_fijo_desc\n" +
                    "\t\t\t\t\t  ,[ae_equipo_pertenencia_cdgo] AS ae_equipo_pertenencia_cdgo\n" +
                    "\t\t\t\t\t\t-- Equipo Pertenencia\n" +
                    "\t\t\t\t\t\t\t\t,ae_pertenencia.[ep_cdgo] AS ae_ep_cdgo\n" +
                    "\t\t\t\t\t\t\t\t,ae_pertenencia.[ep_desc] AS ae_ep_desc\n" +
                    "\t\t\t\t\t\t\t\t,ae_pertenencia.[ep_estad]\t AS ae_ep_estad\n" +
                    "\t\t\t\t\t  ,[ae_cant_minutos_operativo] AS ae_cant_hora_operativa\n" +
                    "\t\t\t\t\t  ,[ae_cant_minutos_parada] AS ae_cant_hora_parada\n" +
                    "\t\t\t\t\t  ,[ae_cant_minutos_total] AS ae_cant_hora_total\n" +
                    "\t\t\t\t\t  ,[ae_estad] AS ae_estad\n" +
                      "\t\t\t\t\t  ,[sle_motonave_base_datos_cdgo] AS   ae_sle_mn_base_datos_cdgo\n" +      
                    "\t\t\t\t\t  FROM ["+DB+"].[dbo].[asignacion_equipo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[solicitud_listado_equipo] ON [ae_solicitud_listado_equipo_cdgo]=[sle_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[cntro_oper] ae_cntro_oper ON [ae_cntro_oper_cdgo]=ae_cntro_oper.[co_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[solicitud_equipo] ON [sle_solicitud_equipo_cdgo]=[se_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[cntro_oper] se_cntro_oper ON [se_cntro_oper_cdgo]=se_cntro_oper.[co_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[usuario] se_usuario_realiza ON [se_usuario_realiza_cdgo]=se_usuario_realiza.[us_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[perfil] ae_prf_registra ON se_usuario_realiza.[us_perfil_cdgo]=ae_prf_registra.[prf_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[estado_solicitud_equipo] ON [se_estado_solicitud_equipo_cdgo]=[ese_cdgo]\n" +
                    "\t\t\t\t\t\t\tLEFT JOIN  ["+DB+"].[dbo].[usuario] se_usuario_confirma ON [se_usuario_realiza_cdgo]=se_usuario_confirma.[us_cdgo]\n" +
                    "\t\t\t\t\t\t\tLEFT JOIN ["+DB+"].[dbo].[perfil] ae_prf_registra_confirma ON se_usuario_confirma.[us_perfil_cdgo]=ae_prf_registra_confirma.[prf_cdgo]\n" +
                    "\t\t\t\t\t\t\tLEFT JOIN  ["+DB+"].[dbo].[confirmacion_solicitud_equipo] ON [se_confirmacion_solicitud_equipo_cdgo]=[cse_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[tipo_equipo] sle_tipoEquipo ON [sle_tipo_equipo_cdgo]=sle_tipoEquipo.[te_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[labor_realizada] ON [sle_labor_realizada_cdgo]=[lr_cdgo]\n" +
                    //"\t\t\t\t\t\t\tLEFT  JOIN["+DB+"].[dbo].[motonave] ON [sle_motonave_cdgo]=[mn_cdgo]\n" +
                    
"		LEFT JOIN ["+DB+"].[dbo].[motonave] ON [sle_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[sle_motonave_base_datos_cdgo] \n" +
                    "               LEFT JOIN ["+DB+"].[dbo].[base_datos] sle_motonave_base_datos ON  [mn_base_datos_cdgo]=sle_motonave_base_datos.[bd_cdgo] \n"+
                            "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [sle_cntro_cost_auxiliar_cdgo]=[cca_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[compania] ON [sle_compania_cdgo]=[cp_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[tipo_equipo] eq_tipo_equipo ON [eq_tipo_equipo_cdgo]=eq_tipo_equipo.[te_cdgo]\n" +
                    "\t\t\t\t\t\t\tLEFT JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador1 ON [eq_clasificador1_cdgo]=eq_clasificador1.[ce_cdgo]\n" +
                    "\t\t\t\t\t\t\tLEFT JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador2 ON [eq_clasificador2_cdgo]=eq_clasificador2.[ce_cdgo]\n" +
                    "\t\t\t\t\t\t\tINNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [eq_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
                    "\t\t\t\t\t\t\tLEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] eq_pertenencia ON [eq_equipo_pertenencia_cdgo]=eq_pertenencia.[ep_cdgo]\n" +
                    "\t\t\t\t\t\t\tLEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ae_pertenencia ON [ae_equipo_pertenencia_cdgo]=ae_pertenencia.[ep_cdgo]\t\n" +
                    "\t) asignacion_equipo ON [me_asignacion_equipo_cdgo]=[ae_cdgo]\n" +
                    "\tINNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [me_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
                    "\tINNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [me_cntro_oper_cdgo]=[co_cdgo]\n" +
                    "\tINNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] me_cntro_cost_auxiliar ON [me_cntro_cost_auxiliar_cdgo]=me_cntro_cost_auxiliar.[cca_cdgo]\n" +
                    "\tINNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro]  me_cntro_cost_subcentro ON me_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo]=me_cntro_cost_subcentro.[ccs_cdgo]\n" +
                    "\tINNER JOIN ["+DB+"].[dbo].[labor_realizada] ON [me_labor_realizada_cdgo]=[lr_cdgo] \n" +
                    //"\tINNER JOIN ["+DB+"].[dbo].[cliente] me_cliente ON [me_cliente_cdgo]=me_cliente.[cl_cdgo]\n" +
                    "               LEFT JOIN ["+DB+"].[dbo].[cliente] me_cliente ON [me_cliente_cdgo]=me_cliente.[cl_cdgo] AND me_cliente.[cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
                    "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON me_cliente.[cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                    //"\tLEFT JOIN  ["+DB+"].[dbo].[articulo] ON [me_articulo_cdgo]=[ar_cdgo]\n" +
                    "		LEFT JOIN ["+DB+"].[dbo].[articulo]  ON [me_articulo_cdgo]=[ar_cdgo] AND [ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
                    "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON [ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
                    "\tINNER JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[cliente_recobro] ON [me_cliente_recobro_cdgo]=[clr_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[cliente] me_clr_cliente ON [clr_cliente_cdgo]=me_clr_cliente.[cl_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[usuario] me_clr_usuario ON [clr_usuario_cdgo]=me_clr_usuario.[us_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[perfil] me_clr_us_perfil ON me_clr_usuario.[us_perfil_cdgo]=me_clr_us_perfil.[prf_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[usuario] me_us_registro ON [me_usuario_registro_cdgo]=me_us_registro.[us_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[perfil] me_us_regist_perfil ON me_us_registro.[us_perfil_cdgo]=me_us_regist_perfil.[prf_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[usuario] me_us_autorizacion ON [me_usuario_autorizacion_cdgo]=me_us_autorizacion.[us_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[perfil] me_us_autoriza_perfil ON me_us_autorizacion.[us_perfil_cdgo]=me_us_autoriza_perfil.[prf_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[autorizacion_recobro] ON [me_autorizacion_recobro_cdgo]=[are_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[causa_inactividad] ON [me_causa_inactividad_cdgo]=[ci_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[usuario] me_us_inactividad ON [me_usuario_inactividad_cdgo]=me_us_inactividad.[us_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[perfil] me_us_inactvdad_perfil ON me_us_inactividad.[us_perfil_cdgo]=me_us_inactvdad_perfil.[prf_cdgo]\n" +
                    "\tLEFT JOIN  ["+DB+"].[dbo].[motivo_parada] ON [me_motivo_parada_cdgo]=[mpa_cdgo]\n" +
                    "\n" +
                    "\tWHERE  [me_fecha_hora_inicio] BETWEEN ? AND ? AND [me_inactividad]=0  AND [me_estado]=1 --AND [me_recobro_cdgo]=2 AND [me_autorizacion_recobro_cdgo] IS NULL \n"
                    + "ORDER BY [me_cdgo] DESC");
            query.setString(1, DatetimeInicio);
            query.setString(2, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();    
            boolean validator=true;
            while(resultSet.next()){
                if(validator){
                    listadoObjetos = new ArrayList();
                    validator=false;
                }
                AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                asignacionEquipo.setCodigo(resultSet.getString(4));
                    CentroOperacion co= new CentroOperacion();
                    co.setCodigo(Integer.parseInt(resultSet.getString(5)));
                    co.setDescripcion(resultSet.getString(6));
                    co.setEstado(resultSet.getString(7));
                asignacionEquipo.setCentroOperacion(co);
                    SolicitudListadoEquipo solicitudListadoEquipo = new SolicitudListadoEquipo();
                    solicitudListadoEquipo.setCodigo(resultSet.getString(9));
                        SolicitudEquipo solicitudEquipo= new SolicitudEquipo();
                        solicitudEquipo.setCodigo(resultSet.getString(10));
                            CentroOperacion co_se= new CentroOperacion();
                                co_se.setCodigo(Integer.parseInt(resultSet.getString(13)));
                                co_se.setDescripcion(resultSet.getString(14));
                                co_se.setEstado(resultSet.getString(15));
                        solicitudEquipo.setCentroOperacion(co_se);
                        solicitudEquipo.setFechaSolicitud(resultSet.getString(16));
                            Usuario us_se = new Usuario();
                                us_se.setCodigo(resultSet.getString(18));
                                //us_se.setClave(resultSet.getString(19));
                                us_se.setNombres(resultSet.getString(20));
                                us_se.setApellidos(resultSet.getString(21));
                                us_se.setPerfilUsuario(new Perfil(resultSet.getString(23),resultSet.getString(24),resultSet.getString(25)));
                                us_se.setCorreo(resultSet.getString(26));
                                us_se.setEstado(resultSet.getString(27));
                        solicitudEquipo.setUsuarioRealizaSolicitud(us_se);
                        solicitudEquipo.setFechaRegistro(resultSet.getString(28));
                            EstadoSolicitudEquipos estadoSolicitudEquipos = new EstadoSolicitudEquipos();
                            estadoSolicitudEquipos.setCodigo(resultSet.getString(30));
                            estadoSolicitudEquipos.setDescripcion(resultSet.getString(31));
                            estadoSolicitudEquipos.setEstado(resultSet.getString(32));
                        solicitudEquipo.setEstadoSolicitudEquipo(estadoSolicitudEquipos);
                        solicitudEquipo.setFechaConfirmacion(resultSet.getString(33));
                            Usuario us_se_confirm = new Usuario();
                            us_se_confirm.setCodigo(resultSet.getString(35));
                            //us_se_confirm.setClave(resultSet.getString(105));
                            us_se_confirm.setNombres(resultSet.getString(37));
                            us_se_confirm.setApellidos(resultSet.getString(38));
                            us_se_confirm.setPerfilUsuario(new Perfil(resultSet.getString(40),resultSet.getString(41),resultSet.getString(42)));
                            us_se_confirm.setCorreo(resultSet.getString(43));
                            us_se_confirm.setEstado(resultSet.getString(44));
                        solicitudEquipo.setUsuarioConfirmacionSolicitud(us_se_confirm);
                            ConfirmacionSolicitudEquipos confirmacionSolicitudEquipos = new ConfirmacionSolicitudEquipos();
                            confirmacionSolicitudEquipos.setCodigo(resultSet.getString(46));
                            confirmacionSolicitudEquipos.setDescripcion(resultSet.getString(47));
                            confirmacionSolicitudEquipos.setEstado(resultSet.getString(48));
                        solicitudEquipo.setConfirmacionSolicitudEquipo(confirmacionSolicitudEquipos);
                    solicitudListadoEquipo.setSolicitudEquipo(solicitudEquipo);
                        TipoEquipo tipoEquipo = new TipoEquipo();
                        tipoEquipo.setCodigo(resultSet.getString(50));
                        tipoEquipo.setDescripcion(resultSet.getString(51));
                        tipoEquipo.setEstado(resultSet.getString(52));
                    solicitudListadoEquipo.setTipoEquipo(tipoEquipo);
                    solicitudListadoEquipo.setMarcaEquipo(resultSet.getString(53));
                    solicitudListadoEquipo.setModeloEquipo(resultSet.getString(54));
                    solicitudListadoEquipo.setCantidad(Integer.parseInt(resultSet.getString(55)));
                    solicitudListadoEquipo.setObservacacion(resultSet.getString(56));
                    solicitudListadoEquipo.setFechaHoraInicio(resultSet.getString(57));
                    solicitudListadoEquipo.setFechaHoraFin(resultSet.getString(58));
                    solicitudListadoEquipo.setCantidadMinutos(resultSet.getInt(59));
                        LaborRealizada laborRealizada = new LaborRealizada();
                        laborRealizada.setCodigo(resultSet.getString(61));
                        laborRealizada.setDescripcion(resultSet.getString(62));
                        laborRealizada.setEstado(resultSet.getString(63));
                    solicitudListadoEquipo.setLaborRealizada(laborRealizada);
                        Motonave motonave = new Motonave();
                        motonave.setCodigo(resultSet.getString(65));
                        motonave.setDescripcion(resultSet.getString(66));
                        motonave.setEstado(resultSet.getString(67));
                         motonave.setBaseDatos(new BaseDatos( resultSet.getString(241)));
                    solicitudListadoEquipo.setMotonave(motonave);
                        CentroCostoSubCentro centroCostoSubCentro = new CentroCostoSubCentro();
                        centroCostoSubCentro.setCodigo(resultSet.getInt(71));
                        centroCostoSubCentro.setDescripcion(resultSet.getString(72));
                        centroCostoSubCentro.setEstado(resultSet.getString(73));
                        CentroCostoAuxiliar centroCostoAuxiliar = new CentroCostoAuxiliar();
                        centroCostoAuxiliar.setCodigo(resultSet.getInt(69));
                        centroCostoAuxiliar.setDescripcion(resultSet.getString(74));
                        centroCostoAuxiliar.setEstado(resultSet.getString(75));
                        centroCostoAuxiliar.setCentroCostoSubCentro(centroCostoSubCentro);
                    solicitudListadoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar);
                        Compañia compania = new Compañia();
                        compania.setCodigo(resultSet.getString(77));
                        compania.setDescripcion(resultSet.getString(78));
                        compania.setEstado(resultSet.getString(79));
                    solicitudListadoEquipo.setCompañia(compania);
                asignacionEquipo.setSolicitudListadoEquipo(solicitudListadoEquipo);
                asignacionEquipo.setFechaRegistro(resultSet.getString(80));
                asignacionEquipo.setFechaHoraInicio(resultSet.getString(81));
                asignacionEquipo.setFechaHoraFin(resultSet.getString(82));
                asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(83));
                    Equipo equipo = new Equipo();
                    equipo.setCodigo(resultSet.getString(85));
                    equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(87),resultSet.getString(88),resultSet.getString(89)));
                    equipo.setCodigo_barra(resultSet.getString(90));
                    equipo.setReferencia(resultSet.getString(91));
                    equipo.setProducto(resultSet.getString(92));
                    equipo.setCapacidad(resultSet.getString(93));
                    equipo.setMarca(resultSet.getString(94));
                    equipo.setModelo(resultSet.getString(95));
                    equipo.setSerial(resultSet.getString(96));
                    equipo.setDescripcion(resultSet.getString(97));
                    equipo.setClasificador1(new ClasificadorEquipo(resultSet.getString(99),resultSet.getString(100),resultSet.getString(101)));
                    equipo.setClasificador2(new ClasificadorEquipo(resultSet.getString(103),resultSet.getString(104),resultSet.getString(105)));
                    equipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(107),resultSet.getString(108),resultSet.getString(109),resultSet.getString(110)));
                    equipo.setPertenenciaEquipo(new Pertenencia(resultSet.getString(112),resultSet.getString(113),resultSet.getString(114)));
                    equipo.setObservacion(resultSet.getString(115));
                    equipo.setEstado(resultSet.getString(116));
                    equipo.setActivoFijo_codigo(resultSet.getString(117));
                    equipo.setActivoFijo_referencia(resultSet.getString(118));
                    equipo.setActivoFijo_descripcion(resultSet.getString(119));
                asignacionEquipo.setEquipo(equipo);
                asignacionEquipo.setPertenencia(new Pertenencia(resultSet.getString(121),resultSet.getString(122),resultSet.getString(123)));
                asignacionEquipo.setCantidadMinutosOperativo(resultSet.getString(124));
                asignacionEquipo.setCantidadMinutosParada(resultSet.getString(125));
                asignacionEquipo.setCantidadMinutosTotal(resultSet.getString(126));
                asignacionEquipo.setEstado(resultSet.getString(127));
                    //mvto_listEquipo.setAsignacionEquipo(asignacionEquipo);
                MvtoEquipo mvtoEquipo = new MvtoEquipo();
                    mvtoEquipo.setCodigo(resultSet.getString(1));
                    mvtoEquipo.setAsignacionEquipo(asignacionEquipo);
                    mvtoEquipo.setFechaRegistro(resultSet.getString(128));
                    mvtoEquipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(130),resultSet.getString(131),resultSet.getString(132),resultSet.getString(133)));
                    mvtoEquipo.setNumeroOrden(resultSet.getString(134));
                        CentroOperacion me_co= new CentroOperacion();
                        me_co.setCodigo(Integer.parseInt(resultSet.getString(238)));
                        me_co.setDescripcion(resultSet.getString(239));
                        me_co.setEstado(resultSet.getString(240));
                    mvtoEquipo.setCentroOperacion(me_co);
                        CentroCostoSubCentro centroCostoSubCentro_mvtoEquipo = new CentroCostoSubCentro();
                        centroCostoSubCentro_mvtoEquipo.setCodigo(resultSet.getInt(138));
                        centroCostoSubCentro_mvtoEquipo.setDescripcion(resultSet.getString(139));
                        centroCostoSubCentro_mvtoEquipo.setEstado(resultSet.getString(140));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipo = new CentroCostoAuxiliar();
                        centroCostoAuxiliar_mvtoEquipo.setCodigo(resultSet.getInt(136));
                        centroCostoAuxiliar_mvtoEquipo.setDescripcion(resultSet.getString(141));
                        centroCostoAuxiliar_mvtoEquipo.setEstado(resultSet.getString(142));
                        centroCostoAuxiliar_mvtoEquipo.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipo);
                    mvtoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipo);
                        LaborRealizada laborRealizadaT = new LaborRealizada();
                        laborRealizadaT.setCodigo(resultSet.getString(144));
                        laborRealizadaT.setDescripcion(resultSet.getString(145));
                        laborRealizadaT.setEstado(resultSet.getString(147));
                        laborRealizadaT.setEs_operativa(resultSet.getString(148));
                        laborRealizadaT.setEs_parada(resultSet.getString(149));
                    mvtoEquipo.setLaborRealizada(laborRealizadaT);
                    mvtoEquipo.setCliente(new Cliente(resultSet.getString(151),resultSet.getString(152),resultSet.getString(153)));
                    mvtoEquipo.getCliente().setBaseDatos(new BaseDatos(resultSet.getString(242)));
                    mvtoEquipo.setArticulo(new Articulo(resultSet.getString(155),resultSet.getString(156),resultSet.getString(157)));
                    mvtoEquipo.getArticulo().setBaseDatos(new BaseDatos( resultSet.getString(243)));
                    mvtoEquipo.setFechaHoraInicio(resultSet.getString(158));
                mvtoEquipo.setFechaHoraFin(resultSet.getString(159));
                mvtoEquipo.setTotalMinutos(resultSet.getString(160));
                mvtoEquipo.setValorHora(resultSet.getString(161));
                mvtoEquipo.setCostoTotalRecobroCliente(resultSet.getString(186));
                        Recobro recobro = new Recobro();
                        recobro.setCodigo(resultSet.getString(164));
                        recobro.setDescripcion(resultSet.getString(165));
                        recobro.setEstado(resultSet.getString(166));
                    mvtoEquipo.setRecobro(recobro);
                        ClienteRecobro ClienteRecobro = new ClienteRecobro();
                        ClienteRecobro.setCodigo(resultSet.getString(168));
                        Cliente cliente_recobro = new Cliente();
                        cliente_recobro.setCodigo(resultSet.getString(170));
                        cliente_recobro.setDescripcion(resultSet.getString(171));
                        cliente_recobro.setEstado(resultSet.getString(172));
                        ClienteRecobro.setCliente(cliente_recobro);
                        Usuario usuario_recobre = new Usuario();
                        usuario_recobre.setCodigo(resultSet.getString(174));
                        //usuario_recobre.setClave(resultSet.getString(244));
                        usuario_recobre.setNombres(resultSet.getString(176));
                        usuario_recobre.setApellidos(resultSet.getString(177));
                        usuario_recobre.setPerfilUsuario(new Perfil(resultSet.getString(179),resultSet.getString(180),resultSet.getString(181)));
                        usuario_recobre.setCorreo(resultSet.getString(182));
                        usuario_recobre.setEstado(resultSet.getString(183));
                        ClienteRecobro.setUsuario(usuario_recobre);
                        ClienteRecobro.setValorRecobro(resultSet.getString(184));
                        ClienteRecobro.setFechaRegistro(resultSet.getString(185));
                    mvtoEquipo.setClienteRecobro(ClienteRecobro);
                    mvtoEquipo.setCostoTotalRecobroCliente(resultSet.getString(186));
                        Usuario usuario_me_registra = new Usuario();
                        usuario_me_registra.setCodigo(resultSet.getString(188));
                        //usuario_me_registra.setClave(resultSet.getString(258));
                        usuario_me_registra.setNombres(resultSet.getString(190));
                        usuario_me_registra.setApellidos(resultSet.getString(191));
                        usuario_me_registra.setPerfilUsuario(new Perfil(resultSet.getString(193),resultSet.getString(194),resultSet.getString(195)));
                        usuario_me_registra.setCorreo(resultSet.getString(196));
                        usuario_me_registra.setEstado(resultSet.getString(197));
                    mvtoEquipo.setUsuarioQuieRegistra(usuario_me_registra);
                        Usuario usuario_me_autoriza = new Usuario();
                        usuario_me_autoriza.setCodigo(resultSet.getString(199));
                        //usuario_me_autoriza.setClave(resultSet.getString(269));
                        usuario_me_autoriza.setNombres(resultSet.getString(201));
                        usuario_me_autoriza.setApellidos(resultSet.getString(202));
                        usuario_me_autoriza.setPerfilUsuario(new Perfil(resultSet.getString(204),resultSet.getString(205),resultSet.getString(206)));
                        usuario_me_autoriza.setCorreo(resultSet.getString(207));
                        usuario_me_autoriza.setEstado(resultSet.getString(208));
                    mvtoEquipo.setUsuarioAutorizaRecobro(usuario_me_autoriza);
                        AutorizacionRecobro autorizacionRecobro = new AutorizacionRecobro();
                        autorizacionRecobro.setCodigo(resultSet.getString(210));
                        autorizacionRecobro.setDescripcion(resultSet.getString(211));
                        autorizacionRecobro.setEstado(resultSet.getString(212));
                    mvtoEquipo.setAutorizacionRecobro(autorizacionRecobro);
                    mvtoEquipo.setObservacionAutorizacion(resultSet.getString(213));
                    mvtoEquipo.setInactividad(resultSet.getString(214));
                        CausaInactividad causaInactividad = new CausaInactividad();
                        causaInactividad.setCodigo(resultSet.getString(216));
                        causaInactividad.setDescripcion(resultSet.getString(217));
                        causaInactividad.setEstado(resultSet.getString(218));
                    mvtoEquipo.setCausaInactividad(causaInactividad);
                        Usuario usuario_me_us_inactividad = new Usuario();
                        usuario_me_us_inactividad.setCodigo(resultSet.getString(220));
                        //usuario_me_us_inactividad.setClave(resultSet.getString(221));
                        usuario_me_us_inactividad.setNombres(resultSet.getString(222));
                        usuario_me_us_inactividad.setApellidos(resultSet.getString(223));
                        usuario_me_us_inactividad.setPerfilUsuario(new Perfil(resultSet.getString(225),resultSet.getString(226),resultSet.getString(227)));
                        usuario_me_us_inactividad.setCorreo(resultSet.getString(228));
                        usuario_me_us_inactividad.setEstado(resultSet.getString(229));
                    mvtoEquipo.setMotivoParadaEstado(resultSet.getString(230));
                        mvtoEquipo.setUsuarioInactividad(usuario_me_us_inactividad);
                        MotivoParada motivoParada= new MotivoParada();
                        motivoParada.setCodigo(resultSet.getString(232));
                        motivoParada.setDescripcion(resultSet.getString(233));
                        motivoParada.setEstado(resultSet.getString(234));
                    mvtoEquipo.setMotivoParada(motivoParada);
                    mvtoEquipo.setObservacionMvtoEquipo(resultSet.getString(235));
                    mvtoEquipo.setEstado(resultSet.getString(236));
                    mvtoEquipo.setDesdeCarbon(resultSet.getString(237));
                    //mvtoEquipo.setTotalMinutos(resultSet.getString(307));
                    TarifaEquipo tarifa=new ControlDB_Equipo(tipoConexion).buscarTarifaEquipoPorAño(mvtoEquipo.getAsignacionEquipo().getEquipo().getCodigo(),mvtoEquipo.getFechaHoraInicio());
                    if(mvtoEquipo.getFechaHoraFin() != null){
                        mvtoEquipo.setValorHora(tarifa.getValorHoraOperacion());
                        try{
                            double des=Double.parseDouble(mvtoEquipo.getTotalMinutos());
                            double totalHoras = Double.parseDouble(""+(des/60));
                            double valorHora =Double.parseDouble(tarifa.getValorHoraOperacion());
                            mvtoEquipo.setCostoTotal(""+(totalHoras * valorHora ));  
                            DecimalFormat formato2 = new DecimalFormat("0.00");
                            mvtoEquipo.setCostoTotal(formato2.format((totalHoras * valorHora )));
                        }catch(Exception e){
                            //mvto_listEquipo.getMvtoEquipo().setCostoTotal("NO PUDE");
                        }
                    }    
                listadoObjetos.add(mvtoEquipo);
            }
        }catch (SQLException sqlException) {
            System.out.println("Error al tratar al consultar los Movimientos de Carbon");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    
    public MvtoEquipo                               Procesar_MvtoEquipo(MvtoEquipo Objeto, Usuario us) throws UnknownHostException, SocketException, SQLException{
        Conexion_DB_costos_vg control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
            try{
                 //Actualizamos el centro de costo
                CentroCosto centroCosto= new ControlDB_CentroCosto(tipoConexion).buscarCentroCostoProcesar(Objeto);
                if(centroCosto !=null){
                    //System.out.println("CC FUE=====>no fue NULL");
                }else{
                    //System.out.println("CC FUE=====>Fue NULL");
                }
                if(centroCosto != null){
                    PreparedStatement queryActualizarDatos_Equipos= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_equipo] SET "
                                                                            + " [me_valor_hora]=?, "
                                                                            + " [me_costo_total]=?, "
                                                                             + " [me_cntro_cost_cdgo]="+centroCosto.getCodigo()+", "
                                                                             + " [me_cntro_cost]='"+centroCosto.getDescripción()+"' "
                                                                            //+ "[me_costo_total_recobro_cliente]=?"
                                                                            + " WHERE [me_cdgo]=?");
                    queryActualizarDatos_Equipos.setString(1,Objeto.getValorHora());
                    queryActualizarDatos_Equipos.setString(2,Objeto.getCostoTotal());
                    //queryActualizarDatos_Equipos.setString(3,centroCosto.getCodigo());
                    //queryActualizarDatos_Equipos.setString(4,centroCosto.getDescripción());
                    queryActualizarDatos_Equipos.setString(3,Objeto.getCodigo());
                    queryActualizarDatos_Equipos.execute();    
                }else{
                    PreparedStatement queryActualizarDatos_Equipos= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_equipo] SET "
                                                                            + " [me_valor_hora]=?, "
                                                                            + " [me_costo_total]=? "
                                                                            //+ "[me_costo_total_recobro_cliente]=?"
                                                                            + " WHERE [me_cdgo]=?");
                    queryActualizarDatos_Equipos.setString(1,Objeto.getValorHora());
                    queryActualizarDatos_Equipos.setString(2,Objeto.getCostoTotal());
                    //queryActualizarDatos_Equipos.setString(3,Objeto.getCostoTotalRecobroCliente());
                    queryActualizarDatos_Equipos.setString(3,Objeto.getCodigo());
                    queryActualizarDatos_Equipos.execute();    
                }
                
                //Actualizamos el centro de costo Mayor
                CentroCostoMayor centroCostoMayor = new ControlDB_CentroCostoMayor(tipoConexion).buscarCentroCostoMayorProcesar(Objeto);
                if(centroCostoMayor !=null){
                    System.out.println("CCMayor FUE=====>no fue NULL");
                }else{
                    System.out.println("CCMayor FUE=====>Fue NULL");
                }
                if(centroCostoMayor != null){
                    PreparedStatement queryActualizarDatos_Equipos= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_equipo] SET "
                                                                            + " [me_cntro_cost_mayor_cdgo]=? "
                                                                            + " WHERE [me_cdgo]=?");
                    queryActualizarDatos_Equipos.setString(1,centroCostoMayor.getCodigo());
                    queryActualizarDatos_Equipos.setString(2,Objeto.getCodigo());
                    queryActualizarDatos_Equipos.execute();    
                }else{
                     PreparedStatement queryActualizarDatos_Equipos= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_equipo] SET "
                                                                            + " [me_cntro_cost_mayor_cdgo]=NULL "
                                                                            + " WHERE [me_cdgo]=?");
                    //queryActualizarDatos_Equipos.setString(1,"NULL");
                    queryActualizarDatos_Equipos.setString(1,Objeto.getCodigo());
                    queryActualizarDatos_Equipos.execute();    
                }
                
                control.cerrarConexionBaseDatos();  
                               
            }catch (SQLException sqlException){
               sqlException.printStackTrace();
               
            } 
            catch (Exception e){
               e.printStackTrace();
            } 
        control.cerrarConexionBaseDatos();
        return Objeto;
    }
    public ArrayList<MvtoCarbon_ListadoEquipos>     buscarMvtoCarbon(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<MvtoCarbon_ListadoEquipos> listadoObjetos = new ArrayList();
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT \n" +
                                                                    "[mcle_cdgo] --1\n" +
                                                                    "      ,[mcle_mvto_carbon_cdgo] --2\n" +
                                                                    "		  ,[mc_cdgo] --3\n" +
                                                                    "		  ,[mc_cntro_oper_cdgo] --4\n" +
                                                                    "				--Centro Operacion\n" +
                                                                    "				,[co_cdgo] --5\n" +
                                                                    "				,[co_desc] --6\n" +
                                                                    "				,CASE [co_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [co_estad] --7\n" +
                                                                    "		  ,[mc_cntro_cost_auxiliar_cdgo] --8\n" +
                                                                    "				--Centro Costo Auxiliar\n" +
                                                                    "				,mc_cntro_cost_auxiliar.[cca_cdgo] --9\n" +
                                                                    "				,mc_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo] --10\n" +
                                                                    "						--Subcentro de Costo\n" +
                                                                    "						,mc_cntro_cost_subcentro.[ccs_cdgo] --11\n" +
                                                                    "						,mc_cntro_cost_subcentro.[ccs_desc]  --12\n" +
                                                                    "						,CASE mc_cntro_cost_subcentro.[ccs_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN NULL ELSE NULL END AS [ccs_estad] --13\n" +
                                                                    "				,mc_cntro_cost_auxiliar.[cca_desc] --14\n" +
                                                                    "				,CASE mc_cntro_cost_auxiliar.[cca_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN NULL ELSE NULL END AS [cca_estad] --15\n" +
                                                                    "		  ,[mc_articulo_cdgo] --16\n" +
                                                                    "				--Articulo\n" +
                                                                    "				,[ar_cdgo] --17\n" +
                                                                    "				,[ar_desc] --18\n" +
                                                                    "				,CASE [ar_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ar_estad] --19\n" +
                                                                    "		  ,[mc_cliente_cdgo] --20\n" +
                                                                    "				--Cliente\n" +
                                                                    "				,mc_cliente.[cl_cdgo] --21\n" +
                                                                    "				,mc_cliente.[cl_desc] --22\n" +
                                                                    "				,CASE mc_cliente.[cl_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cl_estad] --23\n" +
                                                                    "		  ,[mc_transprtdora_cdgo] --24\n" +
                                                                    "				--Transportadora\n" +
                                                                    "				,[tr_cdgo] --25\n" +
                                                                    "				,[tr_nit] --26\n" +
                                                                    "				,[tr_desc] --27\n" +
                                                                    "				,[tr_observ] --28\n" +
                                                                    "				,CASE [tr_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [tr_estad] --29\n" +
                                                                    "		  ,[mc_fecha] --30\n" +
                                                                    "		  ,[mc_num_orden] --31\n" +
                                                                    "		  ,[mc_deposito] --32\n" +
                                                                    "		  ,[mc_consecutivo_tqute] --33\n" +
                                                                    "		  ,[mc_placa_vehiculo] --34\n" +
                                                                    "		  ,[mc_peso_vacio] --35\n" +
                                                                    "		  ,[mc_peso_lleno] --36\n" +
                                                                    "		  ,[mc_peso_neto] --37\n" +
                                                                    "		  ,[mc_fecha_entrad] --38\n" +
                                                                    "		  ,[mc_fecha_salid] --39\n" +
                                                                    "		  ,[mc_fecha_inicio_descargue] --40\n" +
                                                                    "		  ,[mc_fecha_fin_descargue] --41\n" +
                                                                    "		  ,[mc_usuario_cdgo] --42\n" +
                                                                    "				--Usuario Quien Registra desde App Movil\n" +
                                                                    "				,user_registra.[us_cdgo] --43\n" +
                                                                    "				,user_registra.[us_clave] --44\n" +
                                                                    "				,user_registra.[us_nombres] --45\n" +
                                                                    "				,user_registra.[us_apellidos] --46\n" +
                                                                    "				,user_registra.[us_perfil_cdgo] --47\n" +
                                                                    "					--Perfil Usuario Quien Registra\n" +
                                                                    "					,prf_registra.[prf_cdgo] --48\n" +
                                                                    "					,prf_registra.[prf_desc] --49\n" +
                                                                    "					,CASE prf_registra.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --50\n" +
                                                                    "				,user_registra.[us_correo] --51\n" +
                                                                    "				,CASE user_registra.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --52\n" +
                                                                    "		  ,[mc_observ] --53\n" +
                                                                    "		  ,[mc_estad_mvto_carbon_cdgo] --54\n" +
                                                                    "				--Estado MvtoCarbon\n" +
                                                                    "				,[emc_cdgo] --55\n" +
                                                                    "				,[emc_desc] --56\n" +
                                                                    "				,CASE [emc_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [emc_estad] --57\n" +
                                                                    "		  ,CASE [mc_conexion_peso_ccarga] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [mc_conexion_peso_ccarga] --58\n" +
                                                                    "		  ,[mc_registro_manual] --59\n" +
                                                                    "		  ,[mc_usuario_registro_manual_cdgo] --60\n" +
                                                                    "				--Usuario Quien Registra Manual\n" +
                                                                    "				,user_registro_manual.[us_cdgo] --61\n" +
                                                                    "				,user_registro_manual.[us_clave] --62\n" +
                                                                    "				,user_registro_manual.[us_nombres] --63\n" +
                                                                    "				,user_registro_manual.[us_apellidos] --64\n" +
                                                                    "				,user_registro_manual.[us_perfil_cdgo] --65\n" +
                                                                    "					--Perfil Usuario Quien Registra Manual\n" +
                                                                    "					,prf_registra_manual.[prf_cdgo] --66\n" +
                                                                    "					,prf_registra_manual.[prf_desc] --67\n" +
                                                                    "					,CASE prf_registra_manual.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --68\n" +
                                                                    "				,user_registro_manual.[us_correo] --69\n" +
                                                                    "				,CASE user_registro_manual.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --70\n" +
                                                                    "		,[mcle_asignacion_equipo_cdgo] --71\n" +
                                                                    "				,[ae_cdgo] --72\n" +
                                                                    "				,ae_cntro_oper_cdgo --73\n" +
                                                                    "					--Centro Operacion\n" +
                                                                    "					,ae_co_cdgo --74\n" +
                                                                    "					,ae_co_desc --75\n" +
                                                                    "					,CASE ae_co_estado WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_co_estado --76\n" +
                                                                    "				,ae_solicitud_listado_equipo_cdgo --77\n" +
                                                                    "					-- Solicitud Listado Equipo\n" +
                                                                    "					,ae_sle_cdgo --78\n" +
                                                                    "					,ae_sle_solicitud_equipo_cdgo --79\n" +
                                                                    "							--Solicitud Equipo\n" +
                                                                    "							,ae_sle_se_cdgo --80\n" +
                                                                    "							,ae_sle_se_cntro_oper_cdgo --81\n" +
                                                                    "								--CentroOperación SolicitudEquipo\n" +
                                                                    "								,ae_sle_se_co_cdgo --82\n" +
                                                                    "								,ae_sle_se_co_desc --83\n" +
                                                                    "								,CASE ae_sle_se_co_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_co_estad --84\n" +
                                                                    "							,ae_sle_se_fecha --85\n" +
                                                                    "							,ae_sle_se_usuario_realiza_cdgo --86\n" +
                                                                    "								--Usuario SolicitudEquipo\n" +
                                                                    "								,ae_sle_se_us_registra_cdgo --87\n" +
                                                                    "								,ae_sle_se_us_registra_clave --88\n" +
                                                                    "								,ae_sle_se_us_registra_nombres --89\n" +
                                                                    "								,ae_sle_se_us_registra_apellidos --90\n" +
                                                                    "								,ae_sle_se_us_registra_perfil_cdgo --91\n" +
                                                                    "										--Perfil Usuario Quien Registra Manual\n" +
                                                                    "										,ae_sle_se_prf_us_registra_cdgo --92\n" +
                                                                    "										,ae_sle_se_prf_us_registra_desc --93\n" +
                                                                    "										,CASE ae_sle_se_prf_us_registra_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_prf_us_registra_estad --94\n" +
                                                                    "								,ae_sle_se_us_registra_correo --95\n" +
                                                                    "								,CASE ae_sle_se_us_registra_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_us_registra_estad --96\n" +
                                                                    "							,ae_sle_se_fecha_registro --97\n" +
                                                                    "							,ae_sle_se_estado_solicitud_equipo_cdgo --98\n" +
                                                                    "								--Estado de la solicitud\n" +
                                                                    "								,ae_sle_se_ese_cdgo --99\n" +
                                                                    "								,ae_sle_se_ese_desc --100\n" +
                                                                    "								,CASE ae_sle_se_ese_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_ese_estad --101\n" +
                                                                    "							,ae_sle_se_fecha_confirmacion --102\n" +
                                                                    "							,ae_se_usuario_confirma_cdgo --103\n" +
                                                                    "								--Usuario SolicitudEquipo\n" +
                                                                    "								,ae_sle_se_us_confirma_cdgo --104\n" +
                                                                    "								,ae_sle_se_us_confirma_clave --105\n" +
                                                                    "								,ae_sle_se_us_confirma_nombres --106\n" +
                                                                    "								,ae_sle_se_us_confirma_apellidos --107\n" +
                                                                    "								,ae_sle_se_us_confirma_perfil_cdgo --108\n" +
                                                                    "										--Perfil Usuario Quien Registra Manual\n" +
                                                                    "										,ae_sle_se_prf_us_confirma_cdgo --109\n" +
                                                                    "										,ae_sle_se_prf_us_confirma_desc --110\n" +
                                                                    "										,CASE ae_sle_se_prf_us_confirma_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_prf_us_confirma_estad --111\n" +
                                                                    "								,ae_sle_se_us_confirma_correo --112\n" +
                                                                    "								,CASE ae_sle_se_us_confirma_estado WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_us_confirma_estado --113\n" +
                                                                    "							,ae_sle_se_confirmacion_solicitud_equipo_cdgo --114\n" +
                                                                    "								--Confirmacion solicitudEquipo\n" +
                                                                    "								,ae_sle_se_cse_cdgo --115\n" +
                                                                    "								,ae_sle_se_ces_desc --116\n" +
                                                                    "								,CASE ae_sle_se_ces_estado WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_ces_estado --117\n" +
                                                                    "					,ae_sle_tipo_equipo_cdgo --118\n" +
                                                                    "						--Tipo de Equipo\n" +
                                                                    "						,ae_sle_te_cdgo --119\n" +
                                                                    "						,ae_sle_te_desc --120\n" +
                                                                    "						,CASE ae_sle_te_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_te_estad --121\n" +
                                                                    "					,ae_sle_marca_equipo --122\n" +
                                                                    "					,ae_sle_modelo_equipo --123\n" +
                                                                    "					,ae_sle_cant_equip --124\n" +
                                                                    "					,ae_sle_observ --125\n" +
                                                                    "					,ae_sle_fecha_hora_inicio --126\n" +
                                                                    "					,ae_sle_fecha_hora_fin --127\n" +
                                                                    "					,ae_sle_cant_minutos --128\n" +
                                                                    "					,ae_sle_labor_realizada_cdgo --129\n" +
                                                                    "					-- Labor Realizada\n" +
                                                                    "							,ae_sle_lr_cdgo --130\n" +
                                                                    "							,ae_sle_lr_desc --131\n" +
                                                                    "							,CASE ae_sle_lr_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_lr_estad --132\n" +
                                                                    "					,ae_sle_sle_motonave_cdgo --133\n" +
                                                                    "					--Motonave\n" +
                                                                    "							,ae_sle_mn_cdgo --134\n" +
                                                                    "							,ae_sle_mn_desc --135\n" +
                                                                    "							,CASE ae_sle_mn_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_mn_estad --136\n" +
                                                                    "					,ae_sle_cntro_cost_auxiliar_cdgo --137\n" +
                                                                    "						--Centro Costo Auxiliar\n" +
                                                                    "						,ae_sle_cca_cdgo --138\n" +
                                                                    "						,ae_sle_cca_cntro_cost_subcentro_cdgo --139\n" +
                                                                    "							-- SubCentro de Costo\n" +
                                                                    "							,ae_sle_ccs_cdgo --140\n" +
                                                                    "							,ae_sle_ccs_desc --141\n" +
                                                                    "							,CASE ae_sle_ccs_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_ccs_estad --142\n" +
                                                                    "						,ae_sle_cca_desc --143\n" +
                                                                    "						,CASE ae_sle_cca_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_cca_estad --144\n" +
                                                                    "					,ae_sle_compania_cdgo --145\n" +
                                                                    "						--Compañia\n" +
                                                                    "						,ae_sle_cp_cdgo --146\n" +
                                                                    "						,ae_sle_cp_desc --147\n" +
                                                                    "						,CASE ae_sle_cp_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_cp_estad --148\n" +
                                                                    "				,ae_fecha_registro --149\n" +
                                                                    "				,ae_fecha_hora_inicio --150\n" +
                                                                    "				,ae_fecha_hora_fin --151\n" +
                                                                    "				,ae_cant_minutos --152\n" +
                                                                    "				,ae_equipo_cdgo --153\n" +
                                                                    "					--Equipo\n" +
                                                                    "					,ae_eq_cdgo --154\n" +
                                                                    "					,ae_eq_tipo_equipo_cdgo --155\n" +
                                                                    "						--Tipo Equipo\n" +
                                                                    "						,ae_eq_te_cdgo --156\n" +
                                                                    "						,ae_eq_te_desc --157\n" +
                                                                    "						,CASE ae_eq_te_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_te_estad --158\n" +
                                                                    "					,ae_eq_codigo_barra --159\n" +
                                                                    "					,ae_eq_referencia --160\n" +
                                                                    "					,ae_eq_producto --161\n" +
                                                                    "					,ae_eq_capacidad --162\n" +
                                                                    "					,ae_eq_marca --163\n" +
                                                                    "					,ae_eq_modelo --164\n" +
                                                                    "					,ae_eq_serial --165\n" +
                                                                    "					,ae_eq_desc --166\n" +
                                                                    "					,ae_eq_clasificador1_cdgo --167\n" +
                                                                    "						-- Clasificador 1\n" +
                                                                    "						,ae_eq_ce1_cdgo --168\n" +
                                                                    "						,ae_eq_ce1_desc --169\n" +
                                                                    "						,CASE ae_eq_ce1_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_ce1_estad --170\n" +
                                                                    "					,ae_eq_clasificador2_cdgo --171\n" +
                                                                    "						-- Clasificador 2\n" +
                                                                    "						,ae_eq_ce2_cdgo --172\n" +
                                                                    "						,ae_eq_ce2_desc --173\n" +
                                                                    "						,CASE ae_eq_ce2_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_ce2_estad --174\n" +
                                                                    "					,ae_eq_proveedor_equipo_cdgo --175\n" +
                                                                    "						--Proveedor Equipo\n" +
                                                                    "						,ae_eq_pe_cdgo --176\n" +
                                                                    "						,ae_eq_pe_nit --177\n" +
                                                                    "						,ae_eq_pe_desc --178\n" +
                                                                    "						,CASE ae_eq_pe_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_pe_estad --179\n" +
                                                                    "					,ae_eq_equipo_pertenencia_cdgo --180\n" +
                                                                    "						-- Equipo Pertenencia\n" +
                                                                    "						,ae_eq_ep_cdgo --181\n" +
                                                                    "						,ae_eq_ep_desc --182\n" +
                                                                    "						,CASE ae_eq_ep_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_ep_estad --183\n" +
                                                                    "					,ae_eq_eq_observ --184\n" +
                                                                    "					,CASE ae_eq_eq_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_eq_estad --185\n" +
                                                                    "					,ae_eq_actvo_fijo_id --186\n" +
                                                                    "					,ae_eq_actvo_fijo_referencia --187\n" +
                                                                    "					,ae_eq_actvo_fijo_desc --188\n" +
                                                                    "				,ae_equipo_pertenencia_cdgo --189\n" +
                                                                    "				-- Equipo Pertenencia\n" +
                                                                    "						,ae_ep_cdgo --190\n" +
                                                                    "						,ae_ep_desc --191\n" +
                                                                    "						,CASE ae_ep_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_ep_estad --192\n" +
                                                                    "				,ae_cant_minutos_operativo --193\n" +
                                                                    "				,ae_cant_minutos_parada --194\n" +
                                                                    "				,ae_cant_minutos_total --195\n" +
                                                                    "				,CASE ae_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_estad --196\n" +
                                                                    "		,[mcle_mvto_equipo_cdgo] --197\n" +
                                                                    "				--Movimiento Equipo\n" +
                                                                    "				,[me_cdgo] --198\n" +
                                                                    "				,[me_asignacion_equipo_cdgo] --199\n" +
                                                                    "				,[me_fecha] --200\n" +
                                                                    "				,[me_proveedor_equipo_cdgo] --201\n" +
                                                                    "					--Proveedor Equipo\n" +
                                                                    "					,[pe_cdgo] AS me_pe_cdgo --202\n" +
                                                                    "					,[pe_nit] AS me_pe_nit --203\n" +
                                                                    "					,[pe_desc] AS me_pe_desc --204\n" +
                                                                    "					,CASE [pe_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS me_pe_estad --205\n" +
                                                                    "				,[me_num_orden] --206\n" +
                                                                    "				,[me_cntro_cost_auxiliar_cdgo] --207\n" +
                                                                    "					-- Centro Costo Auxiliar\n" +
                                                                    "					,me_cntro_cost_auxiliar.[cca_cdgo] AS me_cca_cdgo --208\n" +
                                                                    "					,me_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo] AS me_cca_cntro_cost_subcentro_cdgo --209\n" +
                                                                    "						--Centro Costo Subcentro\n" +
                                                                    "						,me_cntro_cost_subcentro.[ccs_cdgo] --210\n" +
                                                                    "						,me_cntro_cost_subcentro.[ccs_desc] --211\n" +
                                                                    "						,CASE me_cntro_cost_subcentro.[ccs_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ccs_estad] --212\n" +
                                                                    "					,me_cntro_cost_auxiliar.[cca_desc] AS me_cca_desc --213\n" +
                                                                    "					,CASE me_cntro_cost_auxiliar.[cca_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cca_estad] --214\n" +
                                                                    "				,[me_labor_realizada_cdgo] --215\n" +
                                                                    "					--Labor Realizada\n" +
                                                                    "					,[lr_cdgo]  --216\n" +
                                                                    "					,[lr_desc] --217\n" +
                                                                    "					,CASE [lr_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [lr_estad] --218\n" +
                                                                    "				,[me_cliente_cdgo] --219\n" +
                                                                    "					--Cliente \n" +
                                                                    "					,me_cliente.[cl_cdgo] --220\n" +
                                                                    "					,me_cliente.[cl_desc] --221\n" +
                                                                    "					,CASE me_cliente.[cl_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cl_estad] --222\n" +
                                                                    "				,[me_articulo_cdgo] --223\n" +
                                                                    "					--Producto\n" +
                                                                    "					,[ar_cdgo] --224\n" +
                                                                    "					,[ar_desc] --225\n" +
                                                                    "					,CASE [ar_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ar_estad] --226\n" +
                                                                    "				,[me_fecha_hora_inicio] --227\n" +
                                                                    "				,[me_fecha_hora_fin] --228\n" +
                                                                    "				,[me_total_minutos] --229\n" +
                                                                    "				,[me_valor_hora] --230\n" +
                                                                    "				,[me_costo_total] --231\n" +
                                                                    "				,[me_recobro_cdgo] --232\n" +
                                                                    "					--Recobro\n" +
                                                                    "					,[rc_cdgo] --233\n" +
                                                                    "					,[rc_desc] --234\n" +
                                                                    "					,CASE [rc_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [rc_estad] --235\n" +
                                                                    "				,[me_cliente_recobro_cdgo] --236\n" +
                                                                    "					--Cliente Recobro\n" +
                                                                    "					,[clr_cdgo] --237\n" +
                                                                    "					,[clr_cliente_cdgo] --238\n" +
                                                                    "						--Cliente\n" +
                                                                    "						,me_clr_cliente.[cl_cdgo] --239\n" +
                                                                    "						,me_clr_cliente.[cl_desc] --240\n" +
                                                                    "						,CASE me_clr_cliente.[cl_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cl_estad] --241\n" +
                                                                    "					,[clr_usuario_cdgo] --242\n" +
                                                                    "						--Usuario Quien Registra Recobro\n" +
                                                                    "						,me_clr_usuario.[us_cdgo] --243\n" +
                                                                    "						,me_clr_usuario.[us_clave] --244\n" +
                                                                    "						,me_clr_usuario.[us_nombres] --245\n" +
                                                                    "						,me_clr_usuario.[us_apellidos] --246\n" +
                                                                    "						,me_clr_usuario.[us_perfil_cdgo] --247\n" +
                                                                    "							--Perfil Usuario Registra recobro\n" +
                                                                    "							,me_clr_us_perfil.[prf_cdgo] --248\n" +
                                                                    "							,me_clr_us_perfil.[prf_desc] --249\n" +
                                                                    "							,CASE me_clr_us_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --250\n" +
                                                                    "						,me_clr_usuario.[us_correo] --251\n" +
                                                                    "						,CASE me_clr_usuario.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --252\n" +
                                                                    "					,[clr_valor_recobro] --253\n" +
                                                                    "					,[clr_fecha_registro] --254\n" +
                                                                    "				,[me_costo_total_recobro_cliente] --255\n" +
                                                                    "				,[me_usuario_registro_cdgo] --256\n" +
                                                                    "					--Usuario Quien Registra Equipo\n" +
                                                                    "					,me_us_registro.[us_cdgo] --257\n" +
                                                                    "					,me_us_registro.[us_clave] --258\n" +
                                                                    "					,me_us_registro.[us_nombres] --259\n" +
                                                                    "					,me_us_registro.[us_apellidos] --260\n" +
                                                                    "					,me_us_registro.[us_perfil_cdgo] --261\n" +
                                                                    "						--Perfil de Usuario Quien Registra Equipo\n" +
                                                                    "						,me_us_regist_perfil.[prf_cdgo] --262\n" +
                                                                    "						,me_us_regist_perfil.[prf_desc] --263\n" +
                                                                    "						,CASE me_us_regist_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --264\n" +
                                                                    "					,me_us_registro.[us_correo] --265\n" +
                                                                    "					,CASE me_us_registro.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --266\n" +
                                                                    "				,[me_usuario_autorizacion_cdgo] --267\n" +
                                                                    "					,me_us_autorizacion.[us_cdgo] --268\n" +
                                                                    "					,me_us_autorizacion.[us_clave] --269\n" +
                                                                    "					,me_us_autorizacion.[us_nombres] --270\n" +
                                                                    "					,me_us_autorizacion.[us_apellidos] --271\n" +
                                                                    "					,me_us_autorizacion.[us_perfil_cdgo] --272\n" +
                                                                    "						,me_us_autoriza_perfil.[prf_cdgo] --273\n" +
                                                                    "						,me_us_autoriza_perfil.[prf_desc] --274\n" +
                                                                    "						,CASE me_us_autoriza_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --275\n" +
                                                                    "					,me_us_autorizacion.[us_correo] --276\n" +
                                                                    "					,CASE me_us_autorizacion.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --277\n" +
                                                                    "				,[me_autorizacion_recobro_cdgo] --278\n" +
                                                                    "					,[are_cdgo] --279\n" +
                                                                    "					,[are_desc] --280\n" +
                                                                    "					,CASE [are_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [are_estad] --281\n" +
                                                                    "				,[me_observ_autorizacion] --282\n" +
                                                                    "				,[me_inactividad] --283\n" +
                                                                    "				,[me_causa_inactividad_cdgo] --284\n" +
                                                                    "					,[ci_cdgo] --285\n" +
                                                                    "					,[ci_desc] --286\n" +
                                                                    "					,CASE [ci_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ci_estad] --287\n" +
                                                                    "				,[me_usuario_inactividad_cdgo] --288\n" +
                                                                    "					,me_us_inactividad.[us_cdgo] --289\n" +
                                                                    "					,me_us_inactividad.[us_clave] --290\n" +
                                                                    "					,me_us_inactividad.[us_nombres] --291\n" +
                                                                    "					,me_us_inactividad.[us_apellidos] --292\n" +
                                                                    "					,me_us_inactividad.[us_perfil_cdgo] --293\n" +
                                                                    "						,me_us_inactvdad_perfil.[prf_cdgo] --294\n" +
                                                                    "						,me_us_inactvdad_perfil.[prf_desc] --295\n" +
                                                                    "						,CASE me_us_inactvdad_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --296\n" +
                                                                    "					,me_us_inactividad.[us_correo] --297\n" +
                                                                    "					,CASE me_us_inactividad.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad]	 --298\n" +
                                                                    "                           ,[me_motivo_parada_cdgo]--299\n" +
                                                                    "                                       ,[mpa_cdgo]--300\n" +
                                                                    "                                       ,[mpa_desc]--301\n" +
                                                                    "                                       ,[mpa_estad]--302\n" +
                                                                    "                         ,[me_observ] --303\n" +
                                                                    "				,CASE [me_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_estado]	 --304\n" +
                                                                    "				,CASE [me_desde_mvto_carbon] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [me_desde_mvto_carbon]	 --305\n" +
                                                                    "		,CASE [mcle_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [mcle_estado]	 --306\n" +
                                                                    "            ,Tiempo_Vehiculo_Descargue=(SELECT (DATEDIFF (MINUTE,  [mc_fecha_inicio_descargue], [mc_fecha_fin_descargue])))--307\n" +
                                                                    "           ,Tiempo_Equipo_Descargue=(SELECT (DATEDIFF (MINUTE,  [me_fecha_hora_inicio], [me_fecha_hora_fin]))) --308\n" +
                                                                    "      ,[ae_sle_mn_base_datos_cdgo]--309\n" +
                                                                    "      ,mc_articulo_base_datos.[bd_cdgo]   --310 \n" +
                                                                    "      ,mc_cliente_base_datos.[bd_cdgo]   --311 \n" +
                                                                    "      ,mc_transprtdora_base_datos.[bd_cdgo]   --312 \n" +
                                                                    "      ,me_cliente_base_datos.[bd_cdgo]   --313 \n" +
                                                                    "      ,me_articulo_base_datos.[bd_cdgo]   --314 \n" +
                                                                    "  FROM ["+DB+"].[dbo].[mvto_carbon]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [mc_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] mc_cntro_cost_auxiliar ON [mc_cntro_cost_auxiliar_cdgo]=mc_cntro_cost_auxiliar.[cca_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] mc_cntro_cost_subcentro ON [cca_cntro_cost_subcentro_cdgo]= mc_cntro_cost_subcentro.[ccs_cdgo]\n" +
                                                                    //"	LEFT JOIN ["+DB+"].[dbo].[articulo] ON [mc_articulo_cdgo]=[ar_cdgo]\n" +
                                                                            "		LEFT JOIN ["+DB+"].[dbo].[articulo] mc_articulo ON [mc_articulo_cdgo]=mc_articulo.[ar_cdgo] AND mc_articulo.[ar_base_datos_cdgo]=[mc_articulo_base_datos_cdgo]\n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_articulo_base_datos ON mc_articulo.[ar_base_datos_cdgo]=mc_articulo_base_datos.[bd_cdgo] \n"+
                                                                    //"	LEFT JOIN ["+DB+"].[dbo].[cliente] mc_cliente ON [mc_cliente_cdgo]=mc_cliente.[cl_cdgo]\n" +
        "		LEFT JOIN ["+DB+"].[dbo].[cliente] mc_cliente ON [mc_cliente_cdgo]=mc_cliente.[cl_cdgo] AND mc_cliente.[cl_base_datos_cdgo]=[mc_cliente_base_datos_cdgo]\n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cliente_base_datos ON mc_cliente.[cl_base_datos_cdgo]=mc_cliente_base_datos.[bd_cdgo] \n"+
                                                                    //"	LEFT JOIN ["+DB+"].[dbo].[transprtdora] ON [mc_transprtdora_cdgo]=[tr_cdgo]\n" +
                                                                            "		LEFT JOIN ["+DB+"].[dbo].[transprtdora] ON [mc_transprtdora_cdgo]=[tr_cdgo] AND [tr_base_datos_cdgo]=[mc_transprtdora_base_datos_cdgo] \n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_transprtdora_base_datos ON [tr_base_datos_cdgo]=mc_transprtdora_base_datos.[bd_cdgo] \n"+  
                                                                    "	INNER JOIN ["+DB+"].[dbo].[usuario] user_registra ON [mc_usuario_cdgo] = user_registra.[us_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[perfil] prf_registra ON user_registra.[us_perfil_cdgo]=prf_registra.[prf_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[estad_mvto_carbon] ON [mc_estad_mvto_carbon_cdgo]=[emc_cdgo]\n" +
                                                                    "	LEFT JOIN ["+DB+"].[dbo].[usuario] user_registro_manual ON [mc_usuario_registro_manual_cdgo] = user_registro_manual.[us_cdgo]\n" +
                                                                    "	LEFT JOIN ["+DB+"].[dbo].[perfil] prf_registra_manual ON user_registro_manual.[us_perfil_cdgo]=prf_registra_manual.[prf_cdgo]\n" +
                                                                    "	LEFT JOIN ["+DB+"].[dbo].[mvto_carbon_listado_equipo] ON [mcle_mvto_carbon_cdgo]=[mc_cdgo]\n" +
                                                                    "	INNER JOIN (SELECT [ae_cdgo] AS ae_cdgo\n" +
                                                                    "					  ,[ae_cntro_oper_cdgo] AS ae_cntro_oper_cdgo\n" +
                                                                    "							--Centro Operacion\n" +
                                                                    "							,ae_cntro_oper.[co_cdgo] AS ae_co_cdgo\n" +
                                                                    "							,ae_cntro_oper.[co_desc] AS ae_co_desc\n" +
                                                                    "							,ae_cntro_oper.[co_estad] AS ae_co_estado\n" +
                                                                    "					  ,[ae_solicitud_listado_equipo_cdgo] AS ae_solicitud_listado_equipo_cdgo\n" +
                                                                    "							-- Solicitud Listado Equipo\n" +
                                                                    "							,[sle_cdgo] AS ae_sle_cdgo\n" +
                                                                    "							,[sle_solicitud_equipo_cdgo] AS ae_sle_solicitud_equipo_cdgo\n" +
                                                                    "								  --Solicitud Equipo\n" +
                                                                    "								  ,[se_cdgo] AS ae_sle_se_cdgo\n" +
                                                                    "								  ,[se_cntro_oper_cdgo] AS ae_sle_se_cntro_oper_cdgo\n" +
                                                                    "										--CentroOperación SolicitudEquipo\n" +
                                                                    "										,se_cntro_oper.[co_cdgo] AS ae_sle_se_co_cdgo\n" +
                                                                    "										,se_cntro_oper.[co_desc] AS ae_sle_se_co_desc\n" +
                                                                    "										,se_cntro_oper.[co_estad] AS ae_sle_se_co_estad\n" +
                                                                    "								  ,[se_fecha] AS ae_sle_se_fecha\n" +
                                                                    "								  ,[se_usuario_realiza_cdgo] AS ae_sle_se_usuario_realiza_cdgo\n" +
                                                                    "										--Usuario SolicitudEquipo\n" +
                                                                    "										,se_usuario_realiza.[us_cdgo] AS ae_sle_se_us_registra_cdgo\n" +
                                                                    "										,se_usuario_realiza.[us_clave] AS ae_sle_se_us_registra_clave\n" +
                                                                    "										,se_usuario_realiza.[us_nombres] AS ae_sle_se_us_registra_nombres\n" +
                                                                    "										,se_usuario_realiza.[us_apellidos] AS ae_sle_se_us_registra_apellidos\n" +
                                                                    "										,se_usuario_realiza.[us_perfil_cdgo] AS ae_sle_se_us_registra_perfil_cdgo\n" +
                                                                    "											--Perfil Usuario Quien Registra Manual\n" +
                                                                    "											,ae_prf_registra.[prf_cdgo] AS ae_sle_se_prf_us_registra_cdgo\n" +
                                                                    "											,ae_prf_registra.[prf_desc] AS ae_sle_se_prf_us_registra_desc\n" +
                                                                    "											,ae_prf_registra.[prf_estad] AS ae_sle_se_prf_us_registra_estad\n" +
                                                                    "										,se_usuario_realiza.[us_correo] AS ae_sle_se_us_registra_correo\n" +
                                                                    "										,se_usuario_realiza.[us_estad] AS ae_sle_se_us_registra_estad\n" +
                                                                    "								  ,[se_fecha_registro] AS ae_sle_se_fecha_registro\n" +
                                                                    "								  ,[se_estado_solicitud_equipo_cdgo] AS ae_sle_se_estado_solicitud_equipo_cdgo\n" +
                                                                    "										--Estado de la solicitud\n" +
                                                                    "										,[ese_cdgo] AS ae_sle_se_ese_cdgo\n" +
                                                                    "										,[ese_desc] AS ae_sle_se_ese_desc\n" +
                                                                    "										,[ese_estad] AS ae_sle_se_ese_estad\n" +
                                                                    "								  ,[se_fecha_confirmacion] AS ae_sle_se_fecha_confirmacion\n" +
                                                                    "								  ,[se_usuario_confirma_cdgo] AS ae_se_usuario_confirma_cdgo\n" +
                                                                    "										--Usuario SolicitudEquipo\n" +
                                                                    "										,se_usuario_confirma.[us_cdgo] AS ae_sle_se_us_confirma_cdgo\n" +
                                                                    "										,se_usuario_confirma.[us_clave] AS ae_sle_se_us_confirma_clave\n" +
                                                                    "										,se_usuario_confirma.[us_nombres] AS ae_sle_se_us_confirma_nombres\n" +
                                                                    "										,se_usuario_confirma.[us_apellidos] AS ae_sle_se_us_confirma_apellidos\n" +
                                                                    "										,se_usuario_confirma.[us_perfil_cdgo] AS ae_sle_se_us_confirma_perfil_cdgo\n" +
                                                                    "											--Perfil Usuario Quien Registra Manual\n" +
                                                                    "											,ae_prf_registra_confirma.[prf_cdgo] AS ae_sle_se_prf_us_confirma_cdgo\n" +
                                                                    "											,ae_prf_registra_confirma.[prf_desc] AS ae_sle_se_prf_us_confirma_desc\n" +
                                                                    "											,ae_prf_registra_confirma.[prf_estad] AS ae_sle_se_prf_us_confirma_estad\n" +
                                                                    "										,se_usuario_confirma.[us_correo] AS ae_sle_se_us_confirma_correo\n" +
                                                                    "										,se_usuario_confirma.[us_estad] AS ae_sle_se_us_confirma_estado\n" +
                                                                    "								  ,[se_confirmacion_solicitud_equipo_cdgo] AS ae_sle_se_confirmacion_solicitud_equipo_cdgo\n" +
                                                                    "										--Confirmacion solicitudEquipo\n" +
                                                                    "										,[cse_cdgo] AS ae_sle_se_cse_cdgo\n" +
                                                                    "										,[cse_desc] AS ae_sle_se_ces_desc\n" +
                                                                    "										,[cse_estad] AS ae_sle_se_ces_estado\n" +
                                                                    "							,[sle_tipo_equipo_cdgo] AS ae_sle_tipo_equipo_cdgo\n" +
                                                                    "								--Tipo de Equipo\n" +
                                                                    "								,sle_tipoEquipo.[te_cdgo] AS ae_sle_te_cdgo\n" +
                                                                    "								,sle_tipoEquipo.[te_desc] AS ae_sle_te_desc\n" +
                                                                    "								,sle_tipoEquipo.[te_estad] AS ae_sle_te_estad\n" +
                                                                    "							,[sle_marca_equipo] AS ae_sle_marca_equipo\n" +
                                                                    "							,[sle_modelo_equipo] AS ae_sle_modelo_equipo\n" +
                                                                    "							,[sle_cant_equip] AS ae_sle_cant_equip\n" +
                                                                    "							,[sle_observ] AS ae_sle_observ\n" +
                                                                    "							,[sle_fecha_hora_inicio] AS ae_sle_fecha_hora_inicio\n" +
                                                                    "							,[sle_fecha_hora_fin] AS ae_sle_fecha_hora_fin\n" +
                                                                    "							,[sle_cant_minutos] AS ae_sle_cant_minutos\n" +
                                                                    "							,[sle_labor_realizada_cdgo] AS ae_sle_labor_realizada_cdgo\n" +
                                                                    "							-- Labor Realizada\n" +
                                                                    "								  ,[lr_cdgo] AS ae_sle_lr_cdgo\n" +
                                                                    "								  ,[lr_desc] AS ae_sle_lr_desc\n" +
                                                                    "								  ,[lr_estad] AS ae_sle_lr_estad\n" +
                                                                    "							,[sle_motonave_cdgo] AS ae_sle_sle_motonave_cdgo\n" +
                                                                    "							--Motonave\n" +
                                                                    "								  ,[mn_cdgo] AS ae_sle_mn_cdgo\n" +
                                                                    "								  ,[mn_desc] AS ae_sle_mn_desc\n" +
                                                                    "								  ,[mn_estad] AS ae_sle_mn_estad\n" +
                                                                    "							,[sle_cntro_cost_auxiliar_cdgo] AS ae_sle_cntro_cost_auxiliar_cdgo\n" +
                                                                    "								--Centro Costo Auxiliar\n" +
                                                                    "								,[cca_cdgo] AS ae_sle_cca_cdgo\n" +
                                                                    "								,[cca_cntro_cost_subcentro_cdgo] AS ae_sle_cca_cntro_cost_subcentro_cdgo\n" +
                                                                    "									-- SubCentro de Costo\n" +
                                                                    "									,[ccs_cdgo] AS ae_sle_ccs_cdgo\n" +
                                                                    "									,[ccs_desc] AS ae_sle_ccs_desc\n" +
                                                                    "									,[ccs_estad] AS ae_sle_ccs_estad\n" +
                                                                    "								,[cca_desc] AS ae_sle_cca_desc\n" +
                                                                    "								,[cca_estad] AS ae_sle_cca_estad\n" +
                                                                    "							,[sle_compania_cdgo] AS ae_sle_compania_cdgo\n" +
                                                                    "								--Compañia\n" +
                                                                    "								,[cp_cdgo] AS ae_sle_cp_cdgo\n" +
                                                                    "								,[cp_desc] AS ae_sle_cp_desc\n" +
                                                                    "								,[cp_estad] AS ae_sle_cp_estad\n" +
                                                                    "					  ,[ae_fecha_registro] AS ae_fecha_registro\n" +
                                                                    "					  ,[ae_fecha_hora_inicio] AS ae_fecha_hora_inicio\n" +
                                                                    "					  ,[ae_fecha_hora_fin] AS ae_fecha_hora_fin\n" +
                                                                    "					  ,[ae_cant_minutos] AS ae_cant_minutos\n" +
                                                                    "					  ,[ae_equipo_cdgo] AS ae_equipo_cdgo\n" +
                                                                    "							--Equipo\n" +
                                                                    "							,[eq_cdgo] AS ae_eq_cdgo\n" +
                                                                    "							,[eq_tipo_equipo_cdgo] AS ae_eq_tipo_equipo_cdgo\n" +
                                                                    "								--Tipo Equipo\n" +
                                                                    "								,eq_tipo_equipo.[te_cdgo] AS ae_eq_te_cdgo\n" +
                                                                    "								,eq_tipo_equipo.[te_desc] AS ae_eq_te_desc\n" +
                                                                    "								,eq_tipo_equipo.[te_estad] AS ae_eq_te_estad\n" +
                                                                    "							,[eq_codigo_barra] AS ae_eq_codigo_barra\n" +
                                                                    "							,[eq_referencia] AS ae_eq_referencia\n" +
                                                                    "							,[eq_producto] AS ae_eq_producto\n" +
                                                                    "							,[eq_capacidad] AS ae_eq_capacidad\n" +
                                                                    "							,[eq_marca] AS ae_eq_marca\n" +
                                                                    "							,[eq_modelo] AS ae_eq_modelo\n" +
                                                                    "							,[eq_serial] AS ae_eq_serial\n" +
                                                                    "							,[eq_desc] AS ae_eq_desc\n" +
                                                                    "							,[eq_clasificador1_cdgo] AS ae_eq_clasificador1_cdgo\n" +
                                                                    "								-- Clasificador 1\n" +
                                                                    "								,eq_clasificador1.[ce_cdgo] AS ae_eq_ce1_cdgo\n" +
                                                                    "								,eq_clasificador1.[ce_desc] AS ae_eq_ce1_desc\n" +
                                                                    "								,eq_clasificador1.[ce_estad] AS ae_eq_ce1_estad\n" +
                                                                    "							,[eq_clasificador2_cdgo] AS ae_eq_clasificador2_cdgo\n" +
                                                                    "								-- Clasificador 2\n" +
                                                                    "								,eq_clasificador2.[ce_cdgo] AS ae_eq_ce2_cdgo\n" +
                                                                    "								,eq_clasificador2.[ce_desc] AS ae_eq_ce2_desc\n" +
                                                                    "								,eq_clasificador2.[ce_estad] AS ae_eq_ce2_estad\n" +
                                                                    "							,[eq_proveedor_equipo_cdgo] AS ae_eq_proveedor_equipo_cdgo\n" +
                                                                    "								--Proveedor Equipo\n" +
                                                                    "								,[pe_cdgo] AS ae_eq_pe_cdgo\n" +
                                                                    "								,[pe_nit] AS ae_eq_pe_nit\n" +
                                                                    "								,[pe_desc] AS ae_eq_pe_desc\n" +
                                                                    "								,[pe_estad] AS ae_eq_pe_estad\n" +
                                                                    "							,[eq_equipo_pertenencia_cdgo] AS ae_eq_equipo_pertenencia_cdgo\n" +
                                                                    "								-- Equipo Pertenencia\n" +
                                                                    "								,eq_pertenencia.[ep_cdgo] AS ae_eq_ep_cdgo\n" +
                                                                    "								,eq_pertenencia.[ep_desc] AS ae_eq_ep_desc\n" +
                                                                    "								,eq_pertenencia.[ep_estad] AS ae_eq_ep_estad\n" +
                                                                    "							,[eq_observ] AS ae_eq_eq_observ\n" +
                                                                    "							,[eq_estad] AS ae_eq_eq_estad\n" +
                                                                    "							,[eq_actvo_fijo_id] AS ae_eq_actvo_fijo_id\n" +
                                                                    "							,[eq_actvo_fijo_referencia] AS ae_eq_actvo_fijo_referencia\n" +
                                                                    "							,[eq_actvo_fijo_desc] AS ae_eq_actvo_fijo_desc\n" +
                                                                    "					  ,[ae_equipo_pertenencia_cdgo] AS ae_equipo_pertenencia_cdgo\n" +
                                                                    "						-- Equipo Pertenencia\n" +
                                                                    "								,ae_pertenencia.[ep_cdgo] AS ae_ep_cdgo\n" +
                                                                    "								,ae_pertenencia.[ep_desc] AS ae_ep_desc\n" +
                                                                    "								,ae_pertenencia.[ep_estad]	 AS ae_ep_estad\n" +
                                                                    "					  ,[ae_cant_minutos_operativo] AS ae_cant_minutos_operativo\n" +
                                                                    "					  ,[ae_cant_minutos_parada] AS ae_cant_minutos_parada\n" +
                                                                    "					  ,[ae_cant_minutos_total] AS ae_cant_minutos_total\n" +
                                                                    "					  ,[ae_estad] AS ae_estad\n" +
                                                                    "\t\t\t\t\t  ,[sle_motonave_base_datos_cdgo] AS   ae_sle_mn_base_datos_cdgo\n" +
                                                                    "					  FROM ["+DB+"].[dbo].[asignacion_equipo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[solicitud_listado_equipo] ON [ae_solicitud_listado_equipo_cdgo]=[sle_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[cntro_oper] ae_cntro_oper ON [ae_cntro_oper_cdgo]=ae_cntro_oper.[co_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[solicitud_equipo] ON [sle_solicitud_equipo_cdgo]=[se_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[cntro_oper] se_cntro_oper ON [se_cntro_oper_cdgo]=se_cntro_oper.[co_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[usuario] se_usuario_realiza ON [se_usuario_realiza_cdgo]=se_usuario_realiza.[us_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[perfil] ae_prf_registra ON se_usuario_realiza.[us_perfil_cdgo]=ae_prf_registra.[prf_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[estado_solicitud_equipo] ON [se_estado_solicitud_equipo_cdgo]=[ese_cdgo]\n" +
                                                                    "							LEFT JOIN  ["+DB+"].[dbo].[usuario] se_usuario_confirma ON [se_usuario_realiza_cdgo]=se_usuario_confirma.[us_cdgo]\n" +
                                                                    "							LEFT JOIN ["+DB+"].[dbo].[perfil] ae_prf_registra_confirma ON se_usuario_confirma.[us_perfil_cdgo]=ae_prf_registra_confirma.[prf_cdgo]\n" +
                                                                    "							LEFT JOIN  ["+DB+"].[dbo].[confirmacion_solicitud_equipo] ON [se_confirmacion_solicitud_equipo_cdgo]=[cse_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[tipo_equipo] sle_tipoEquipo ON [sle_tipo_equipo_cdgo]=sle_tipoEquipo.[te_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[labor_realizada] ON [sle_labor_realizada_cdgo]=[lr_cdgo]\n" +
                                                                    //"							LEFT  JOIN["+DB+"].[dbo].[motonave] ON [sle_motonave_cdgo]=[mn_cdgo]\n" +
                                                                    "		LEFT JOIN ["+DB+"].[dbo].[motonave] ON [sle_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[sle_motonave_base_datos_cdgo] \n" +
                                                                    "               LEFT JOIN ["+DB+"].[dbo].[base_datos] sle_motonave_base_datos ON  [mn_base_datos_cdgo]=sle_motonave_base_datos.[bd_cdgo] \n"+
                                                                    "							INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [sle_cntro_cost_auxiliar_cdgo]=[cca_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[compania] ON [sle_compania_cdgo]=[cp_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[tipo_equipo] eq_tipo_equipo ON [eq_tipo_equipo_cdgo]=eq_tipo_equipo.[te_cdgo]\n" +
                                                                    "							LEFT JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador1 ON [eq_clasificador1_cdgo]=eq_clasificador1.[ce_cdgo]\n" +
                                                                    "							LEFT JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador2 ON [eq_clasificador2_cdgo]=eq_clasificador2.[ce_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [eq_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
                                                                    "							LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] eq_pertenencia ON [eq_equipo_pertenencia_cdgo]=eq_pertenencia.[ep_cdgo]\n" +
                                                                    "							LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ae_pertenencia ON [ae_equipo_pertenencia_cdgo]=ae_pertenencia.[ep_cdgo]	\n" +
                                                                    "	) asignacion_equipo ON [mcle_asignacion_equipo_cdgo]=[ae_cdgo]\n" +
                                                                    "	--Movimiento Equipo\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[mvto_equipo] ON [mcle_mvto_equipo_cdgo]=[me_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [me_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] me_cntro_cost_auxiliar ON [me_cntro_cost_auxiliar_cdgo]=me_cntro_cost_auxiliar.[cca_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro]  me_cntro_cost_subcentro ON me_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo]=me_cntro_cost_subcentro.[ccs_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[labor_realizada] ON [me_labor_realizada_cdgo]=[lr_cdgo] \n" +
                                                                    //"	INNER JOIN ["+DB+"].[dbo].[cliente] me_cliente ON [me_cliente_cdgo]=me_cliente.[cl_cdgo]\n" +
                                                                   "		LEFT JOIN ["+DB+"].[dbo].[cliente] me_cliente ON [me_cliente_cdgo]=me_cliente.[cl_cdgo] AND me_cliente.[cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
                                                                    "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON me_cliente.[cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                                                                           // "	LEFT JOIN  ["+DB+"].[dbo].[articulo] ON [me_articulo_cdgo]=[ar_cdgo]\n" +
                                                                    "		LEFT JOIN ["+DB+"].[dbo].[articulo]  ON [me_articulo_cdgo]=[ar_cdgo] AND	[ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
                                                                    "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON [ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
                                                                    "	INNER JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[cliente_recobro] ON [me_cliente_recobro_cdgo]=[clr_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[cliente] me_clr_cliente ON [clr_cliente_cdgo]=me_clr_cliente.[cl_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[usuario] me_clr_usuario ON [clr_usuario_cdgo]=me_clr_usuario.[us_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[perfil] me_clr_us_perfil ON me_clr_usuario.[us_perfil_cdgo]=me_clr_us_perfil.[prf_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[usuario] me_us_registro ON [me_usuario_registro_cdgo]=me_us_registro.[us_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[perfil] me_us_regist_perfil ON me_us_registro.[us_perfil_cdgo]=me_us_regist_perfil.[prf_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[usuario] me_us_autorizacion ON [me_usuario_autorizacion_cdgo]=me_us_autorizacion.[us_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[perfil] me_us_autoriza_perfil ON me_us_autorizacion.[us_perfil_cdgo]=me_us_autoriza_perfil.[prf_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[autorizacion_recobro] ON [me_autorizacion_recobro_cdgo]=[are_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[causa_inactividad] ON [me_causa_inactividad_cdgo]=[ci_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[usuario] me_us_inactividad ON [me_usuario_inactividad_cdgo]=me_us_inactividad.[us_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[perfil] me_us_inactvdad_perfil ON me_us_inactividad.[us_perfil_cdgo]=me_us_inactvdad_perfil.[prf_cdgo]\n" +
                                                                    "   LEFT JOIN  ["+DB+"].[dbo].[motivo_parada] ON [me_motivo_parada_cdgo]= [mpa_cdgo]\n"  +     
                                                                    "	WHERE [mc_fecha] BETWEEN ? AND ? AND [me_inactividad]=0 AND [mc_estad_mvto_carbon_cdgo]=1 ORDER BY [mcle_mvto_carbon_cdgo] DESC");
            query.setString(1, DatetimeInicio);
            query.setString(2, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();              
            while(resultSet.next()){ 
                MvtoCarbon_ListadoEquipos mvto_listEquipo = new MvtoCarbon_ListadoEquipos();
                mvto_listEquipo.setCodigo(resultSet.getString(1));
                    MvtoCarbon mvtoCarbon = new MvtoCarbon();
                    mvtoCarbon.setCodigo(resultSet.getString(3));
                    mvtoCarbon.setCentroOperacion(new CentroOperacion(Integer.parseInt(resultSet.getString(5)),resultSet.getString(6),resultSet.getString(7)));
                    mvtoCarbon.setCentroCostoAuxiliar(new CentroCostoAuxiliar(Integer.parseInt(resultSet.getString(9)),new CentroCostoSubCentro(Integer.parseInt(resultSet.getString(11)),resultSet.getString(12),resultSet.getString(13)),resultSet.getString(14),resultSet.getString(15)));
                    mvtoCarbon.setArticulo(new Articulo(resultSet.getString(17),resultSet.getString(18),resultSet.getString(19)));
                    mvtoCarbon.getArticulo().setBaseDatos(new BaseDatos(resultSet.getString(310)));
                    mvtoCarbon.setCliente(new Cliente(resultSet.getString(21),resultSet.getString(22),resultSet.getString(23)));
                    mvtoCarbon.getCliente().setBaseDatos(new BaseDatos(resultSet.getString(311)));
                    mvtoCarbon.setTransportadora(new Transportadora(resultSet.getString(25),resultSet.getString(26),resultSet.getString(27),resultSet.getString(28),resultSet.getString(29)));
                    mvtoCarbon.getTransportadora().setBaseDatos(new BaseDatos(resultSet.getString(312)));
                    mvtoCarbon.setFechaRegistro(resultSet.getString(30));
                    mvtoCarbon.setNumero_orden(resultSet.getString(31));
                    mvtoCarbon.setDeposito(resultSet.getString(32));
                    mvtoCarbon.setConsecutivo(resultSet.getString(33));
                    mvtoCarbon.setPlaca(resultSet.getString(34));
                    mvtoCarbon.setPesoVacio(resultSet.getString(35));
                    mvtoCarbon.setPesoLleno(resultSet.getString(36));
                    mvtoCarbon.setPesoNeto(resultSet.getString(37));
                    mvtoCarbon.setFechaEntradaVehiculo(resultSet.getString(38));
                    mvtoCarbon.setFecha_SalidaVehiculo(resultSet.getString(39));    
                    mvtoCarbon.setFechaInicioDescargue(resultSet.getString(40));    
                    mvtoCarbon.setFechaFinDescargue(resultSet.getString(41));      
                        Usuario us = new Usuario();           
                        us.setCodigo(resultSet.getString(43));
                        //us.setClave(resultSet.getString(44));
                        us.setNombres(resultSet.getString(45));
                        us.setApellidos(resultSet.getString(46));
                        us.setPerfilUsuario(new Perfil(resultSet.getString(48),resultSet.getString(49),resultSet.getString(50)));
                        us.setCorreo(resultSet.getString(51));
                        us.setEstado(resultSet.getString(52));
                    mvtoCarbon.setUsuarioRegistroMovil(us);
                    mvtoCarbon.setObservacion(resultSet.getString(53));
                        EstadoMvtoCarbon estadMvtoCarbon = new EstadoMvtoCarbon();
                        estadMvtoCarbon.setCodigo(resultSet.getString(55));
                        estadMvtoCarbon.setDescripcion(resultSet.getString(56));
                        estadMvtoCarbon.setEstado(resultSet.getString(57));
                    mvtoCarbon.setEstadoMvtoCarbon(estadMvtoCarbon);
                    mvtoCarbon.setConexionPesoCcarga(resultSet.getString(58));
                    mvtoCarbon.setRegistroManual(resultSet.getString(59));
                        Usuario usRegManual = new Usuario();           
                        usRegManual.setCodigo(resultSet.getString(61));
                        //usRegManual.setClave(resultSet.getString(62));
                        usRegManual.setNombres(resultSet.getString(63));
                        usRegManual.setApellidos(resultSet.getString(64));
                        usRegManual.setPerfilUsuario(new Perfil(resultSet.getString(66),resultSet.getString(67),resultSet.getString(68)));
                        usRegManual.setCorreo(resultSet.getString(69));
                        usRegManual.setEstado(resultSet.getString(70));
                    mvtoCarbon.setUsuarioRegistraManual(usRegManual);
                    mvtoCarbon.setCantidadHorasDescargue(resultSet.getString(303));
                mvto_listEquipo.setMvtoCarbon(mvtoCarbon);
                    AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                    asignacionEquipo.setCodigo(resultSet.getString(72));
                        CentroOperacion co= new CentroOperacion();
                        co.setCodigo(Integer.parseInt(resultSet.getString(74)));
                        co.setDescripcion(resultSet.getString(75));
                        co.setEstado(resultSet.getString(76));
                    asignacionEquipo.setCentroOperacion(co);
                        SolicitudListadoEquipo solicitudListadoEquipo = new SolicitudListadoEquipo();
                        solicitudListadoEquipo.setCodigo(resultSet.getString(78));
                            SolicitudEquipo solicitudEquipo= new SolicitudEquipo();
                            solicitudEquipo.setCodigo(resultSet.getString(80));
                                CentroOperacion co_se= new CentroOperacion();
                                co_se.setCodigo(Integer.parseInt(resultSet.getString(82)));
                                co_se.setDescripcion(resultSet.getString(83));
                                co_se.setEstado(resultSet.getString(84));
                            solicitudEquipo.setCentroOperacion(co_se);
                            solicitudEquipo.setFechaSolicitud(resultSet.getString(85));
                                Usuario us_se = new Usuario();           
                                us_se.setCodigo(resultSet.getString(87));
                                //us_se.setClave(resultSet.getString(88));
                                us_se.setNombres(resultSet.getString(89));
                                us_se.setApellidos(resultSet.getString(90));
                                us_se.setPerfilUsuario(new Perfil(resultSet.getString(92),resultSet.getString(93),resultSet.getString(94)));
                                us_se.setCorreo(resultSet.getString(95));
                                us_se.setEstado(resultSet.getString(96));
                            solicitudEquipo.setUsuarioRealizaSolicitud(us_se);
                            solicitudEquipo.setFechaRegistro(resultSet.getString(97));
                                EstadoSolicitudEquipos estadoSolicitudEquipos = new EstadoSolicitudEquipos();
                                estadoSolicitudEquipos.setCodigo(resultSet.getString(99));
                                estadoSolicitudEquipos.setDescripcion(resultSet.getString(100));
                                estadoSolicitudEquipos.setEstado(resultSet.getString(101));
                            solicitudEquipo.setEstadoSolicitudEquipo(estadoSolicitudEquipos);
                            solicitudEquipo.setFechaConfirmacion(resultSet.getString(102));
                                Usuario us_se_confirm = new Usuario();           
                                us_se_confirm.setCodigo(resultSet.getString(104));
                                //us_se_confirm.setClave(resultSet.getString(105));
                                us_se_confirm.setNombres(resultSet.getString(106));
                                us_se_confirm.setApellidos(resultSet.getString(107));
                                us_se_confirm.setPerfilUsuario(new Perfil(resultSet.getString(109),resultSet.getString(110),resultSet.getString(111)));
                                us_se_confirm.setCorreo(resultSet.getString(112));
                                us_se_confirm.setEstado(resultSet.getString(113));
                            solicitudEquipo.setUsuarioConfirmacionSolicitud(us_se_confirm);
                                ConfirmacionSolicitudEquipos confirmacionSolicitudEquipos = new ConfirmacionSolicitudEquipos();
                                confirmacionSolicitudEquipos.setCodigo(resultSet.getString(115));
                                confirmacionSolicitudEquipos.setDescripcion(resultSet.getString(116));
                                confirmacionSolicitudEquipos.setEstado(resultSet.getString(117));
                            solicitudEquipo.setConfirmacionSolicitudEquipo(confirmacionSolicitudEquipos);
                        solicitudListadoEquipo.setSolicitudEquipo(solicitudEquipo);
                            TipoEquipo tipoEquipo = new TipoEquipo();
                            tipoEquipo.setCodigo(resultSet.getString(119));
                            tipoEquipo.setDescripcion(resultSet.getString(120));
                            tipoEquipo.setEstado(resultSet.getString(121));
                        solicitudListadoEquipo.setTipoEquipo(tipoEquipo);
                        solicitudListadoEquipo.setMarcaEquipo(resultSet.getString(122));
                        solicitudListadoEquipo.setModeloEquipo(resultSet.getString(123));
                        solicitudListadoEquipo.setCantidad(Integer.parseInt(resultSet.getString(124)));
                        solicitudListadoEquipo.setObservacacion(resultSet.getString(125));
                        solicitudListadoEquipo.setFechaHoraInicio(resultSet.getString(126));
                        solicitudListadoEquipo.setFechaHoraFin(resultSet.getString(127));  
                        solicitudListadoEquipo.setCantidadMinutos(resultSet.getInt(128));
                            LaborRealizada laborRealizada = new LaborRealizada();
                            laborRealizada.setCodigo(resultSet.getString(130));
                            laborRealizada.setDescripcion(resultSet.getString(131));
                            laborRealizada.setEstado(resultSet.getString(132));
                        solicitudListadoEquipo.setLaborRealizada(laborRealizada);
                            Motonave motonave = new Motonave();
                            motonave.setCodigo(resultSet.getString(134));
                            motonave.setDescripcion(resultSet.getString(135));
                            motonave.setEstado(resultSet.getString(136));
                            motonave.setBaseDatos(new BaseDatos( resultSet.getString(309)));
                        solicitudListadoEquipo.setMotonave(motonave);
                            CentroCostoSubCentro centroCostoSubCentro = new CentroCostoSubCentro();
                            centroCostoSubCentro.setCodigo(resultSet.getInt(140));
                            centroCostoSubCentro.setDescripcion(resultSet.getString(141));
                            centroCostoSubCentro.setEstado(resultSet.getString(142));
                            CentroCostoAuxiliar centroCostoAuxiliar = new CentroCostoAuxiliar();                          
                            centroCostoAuxiliar.setCodigo(resultSet.getInt(138));
                            centroCostoAuxiliar.setDescripcion(resultSet.getString(143));
                            centroCostoAuxiliar.setEstado(resultSet.getString(144));
                            centroCostoAuxiliar.setCentroCostoSubCentro(centroCostoSubCentro);
                        solicitudListadoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar);
                            Compañia compania = new Compañia();
                            compania.setCodigo(resultSet.getString(146));
                            compania.setDescripcion(resultSet.getString(147));
                            compania.setEstado(resultSet.getString(148));
                        solicitudListadoEquipo.setCompañia(compania);
                    asignacionEquipo.setSolicitudListadoEquipo(solicitudListadoEquipo);
                    asignacionEquipo.setFechaRegistro(resultSet.getString(149));
                    asignacionEquipo.setFechaHoraInicio(resultSet.getString(150));
                    asignacionEquipo.setFechaHoraFin(resultSet.getString(151));
                    asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(152));
                        Equipo equipo = new Equipo(); 
                        equipo.setCodigo(resultSet.getString(154));
                        equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(156),resultSet.getString(157),resultSet.getString(158)));
                        equipo.setCodigo_barra(resultSet.getString(159));
                        equipo.setReferencia(resultSet.getString(160));
                        equipo.setProducto(resultSet.getString(161));
                        equipo.setCapacidad(resultSet.getString(162));
                        equipo.setMarca(resultSet.getString(163));
                        equipo.setModelo(resultSet.getString(164));
                        equipo.setSerial(resultSet.getString(165));
                        equipo.setDescripcion(resultSet.getString(166));
                        equipo.setClasificador1(new ClasificadorEquipo(resultSet.getString(168),resultSet.getString(169),resultSet.getString(170)));
                        equipo.setClasificador2(new ClasificadorEquipo(resultSet.getString(172),resultSet.getString(173),resultSet.getString(174)));
                        equipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(176),resultSet.getString(177),resultSet.getString(178),resultSet.getString(179)));
                        equipo.setPertenenciaEquipo(new Pertenencia(resultSet.getString(181),resultSet.getString(182),resultSet.getString(183)));
                        equipo.setObservacion(resultSet.getString(184));
                        equipo.setEstado(resultSet.getString(185));
                        equipo.setActivoFijo_codigo(resultSet.getString(186));
                        equipo.setActivoFijo_referencia(resultSet.getString(187));
                        equipo.setActivoFijo_descripcion(resultSet.getString(188));
                    asignacionEquipo.setEquipo(equipo);
                    asignacionEquipo.setPertenencia(new Pertenencia(resultSet.getString(190),resultSet.getString(191),resultSet.getString(192)));
                    asignacionEquipo.setCantidadMinutosOperativo(resultSet.getString(193));
                    asignacionEquipo.setCantidadMinutosParada(resultSet.getString(194));
                    asignacionEquipo.setCantidadMinutosTotal(resultSet.getString(195));
                    asignacionEquipo.setEstado(resultSet.getString(196));
                mvto_listEquipo.setAsignacionEquipo(asignacionEquipo);
                    MvtoEquipo mvtoEquipo = new MvtoEquipo();
                    mvtoEquipo.setCodigo(resultSet.getString(198));
                    mvtoEquipo.setAsignacionEquipo(asignacionEquipo);
                    mvtoEquipo.setFechaRegistro(resultSet.getString(200));        
                    mvtoEquipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(202),resultSet.getString(203),resultSet.getString(204),resultSet.getString(205)));
                    mvtoEquipo.setNumeroOrden(resultSet.getString(206));        
                        CentroCostoSubCentro centroCostoSubCentro_mvtoEquipo = new CentroCostoSubCentro();
                            centroCostoSubCentro_mvtoEquipo.setCodigo(resultSet.getInt(210));
                            centroCostoSubCentro_mvtoEquipo.setDescripcion(resultSet.getString(211));
                            centroCostoSubCentro_mvtoEquipo.setEstado(resultSet.getString(212));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipo = new CentroCostoAuxiliar();                          
                        centroCostoAuxiliar_mvtoEquipo.setCodigo(resultSet.getInt(208));
                        centroCostoAuxiliar_mvtoEquipo.setDescripcion(resultSet.getString(213));
                        centroCostoAuxiliar_mvtoEquipo.setEstado(resultSet.getString(214));
                        centroCostoAuxiliar_mvtoEquipo.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipo);
                    mvtoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipo);
                        LaborRealizada laborRealizadaT = new LaborRealizada();
                        laborRealizadaT.setCodigo(resultSet.getString(216));
                        laborRealizadaT.setDescripcion(resultSet.getString(217));
                        laborRealizadaT.setEstado(resultSet.getString(218));
                    mvtoEquipo.setLaborRealizada(laborRealizadaT);
                    mvtoEquipo.setCliente(new Cliente(resultSet.getString(220),resultSet.getString(221),resultSet.getString(222)));
                    mvtoEquipo.getCliente().setBaseDatos(new BaseDatos( resultSet.getString(313)));
                    mvtoEquipo.setArticulo(new Articulo(resultSet.getString(224),resultSet.getString(225),resultSet.getString(226)));
                    mvtoEquipo.getArticulo().setBaseDatos(new BaseDatos( resultSet.getString(314)));
                    mvtoEquipo.setFechaHoraInicio(resultSet.getString(227));
                    mvtoEquipo.setFechaHoraFin(resultSet.getString(228));
                    mvtoEquipo.setTotalMinutos(resultSet.getString(229));
                    mvtoEquipo.setCostoTotalRecobroCliente(resultSet.getString(230));
                        Recobro recobro = new Recobro();
                        recobro.setCodigo(resultSet.getString(233));
                        recobro.setDescripcion(resultSet.getString(234));
                        recobro.setEstado(resultSet.getString(235));
                    mvtoEquipo.setRecobro(recobro);
                        ClienteRecobro ClienteRecobro = new ClienteRecobro();
                        ClienteRecobro.setCodigo(resultSet.getString(235));
                            Cliente cliente_recobro = new Cliente();
                            cliente_recobro.setCodigo(resultSet.getString(239));
                            cliente_recobro.setDescripcion(resultSet.getString(240));
                            cliente_recobro.setEstado(resultSet.getString(241));
                        ClienteRecobro.setCliente(cliente_recobro);
                            Usuario usuario_recobre = new Usuario();
                            usuario_recobre.setCodigo(resultSet.getString(243));
                            //usuario_recobre.setClave(resultSet.getString(244));
                            usuario_recobre.setNombres(resultSet.getString(245));
                            usuario_recobre.setApellidos(resultSet.getString(246));
                            usuario_recobre.setPerfilUsuario(new Perfil(resultSet.getString(248),resultSet.getString(249),resultSet.getString(250)));
                            usuario_recobre.setCorreo(resultSet.getString(251));
                            usuario_recobre.setEstado(resultSet.getString(252));
                        ClienteRecobro.setUsuario(usuario_recobre);
                        ClienteRecobro.setValorRecobro(resultSet.getString(253));
                        ClienteRecobro.setFechaRegistro(resultSet.getString(254));
                    mvtoEquipo.setClienteRecobro(ClienteRecobro);
                    mvtoEquipo.setCostoTotalRecobroCliente(resultSet.getString(255));
                        Usuario usuario_me_registra = new Usuario();
                        usuario_me_registra.setCodigo(resultSet.getString(257));
                        //usuario_me_registra.setClave(resultSet.getString(258));
                        usuario_me_registra.setNombres(resultSet.getString(259));
                        usuario_me_registra.setApellidos(resultSet.getString(260));
                        usuario_me_registra.setPerfilUsuario(new Perfil(resultSet.getString(262),resultSet.getString(263),resultSet.getString(264)));
                        usuario_me_registra.setCorreo(resultSet.getString(265));
                        usuario_me_registra.setEstado(resultSet.getString(266));
                    mvtoEquipo.setUsuarioQuieRegistra(usuario_me_registra);
                        Usuario usuario_me_autoriza = new Usuario();
                        usuario_me_autoriza.setCodigo(resultSet.getString(268));
                        //usuario_me_autoriza.setClave(resultSet.getString(269));
                        usuario_me_autoriza.setNombres(resultSet.getString(270));
                        usuario_me_autoriza.setApellidos(resultSet.getString(271));
                        usuario_me_autoriza.setPerfilUsuario(new Perfil(resultSet.getString(273),resultSet.getString(274),resultSet.getString(275)));
                        usuario_me_autoriza.setCorreo(resultSet.getString(276));
                        usuario_me_autoriza.setEstado(resultSet.getString(277));
                    mvtoEquipo.setUsuarioAutorizaRecobro(usuario_me_autoriza);
                        AutorizacionRecobro autorizacionRecobro = new AutorizacionRecobro();
                        autorizacionRecobro.setCodigo(resultSet.getString(279));
                        autorizacionRecobro.setDescripcion(resultSet.getString(280));
                        autorizacionRecobro.setEstado(resultSet.getString(281));
                    mvtoEquipo.setAutorizacionRecobro(autorizacionRecobro);
                    mvtoEquipo.setObservacionAutorizacion(resultSet.getString(282)); 
                    mvtoEquipo.setInactividad(resultSet.getString(283)); 
                        CausaInactividad causaInactividad = new CausaInactividad();
                        causaInactividad.setCodigo(resultSet.getString(285));
                        causaInactividad.setDescripcion(resultSet.getString(286));
                        causaInactividad.setEstado(resultSet.getString(287));
                    mvtoEquipo.setCausaInactividad(causaInactividad);
                        Usuario usuario_me_us_inactividad = new Usuario();
                        usuario_me_us_inactividad.setCodigo(resultSet.getString(289));
                        //usuario_me_us_inactividad.setClave(resultSet.getString(290));
                        usuario_me_us_inactividad.setNombres(resultSet.getString(291));
                        usuario_me_us_inactividad.setApellidos(resultSet.getString(292));
                        usuario_me_us_inactividad.setPerfilUsuario(new Perfil(resultSet.getString(294),resultSet.getString(295),resultSet.getString(296)));
                        usuario_me_us_inactividad.setCorreo(resultSet.getString(297));
                        usuario_me_us_inactividad.setEstado(resultSet.getString(298));
                    mvtoEquipo.setUsuarioInactividad(usuario_me_us_inactividad);
                        MotivoParada motivoParada= new MotivoParada();
                        motivoParada.setCodigo(resultSet.getString(300));
                        motivoParada.setDescripcion(resultSet.getString(301));
                        motivoParada.setEstado(resultSet.getString(302));
                    mvtoEquipo.setMotivoParada(motivoParada);
                    mvtoEquipo.setObservacionMvtoEquipo(resultSet.getString(303)); 
                    mvtoEquipo.setEstado(resultSet.getString(304)); 
                    mvtoEquipo.setDesdeCarbon(resultSet.getString(305)); 
                    mvtoEquipo.setTotalMinutos(resultSet.getString(307)); 
                mvto_listEquipo.setMvtoEquipo(mvtoEquipo);  
                mvto_listEquipo.setEstado(resultSet.getString(306));
              
                    TarifaEquipo tarifa=new ControlDB_Equipo(tipoConexion).buscarTarifaEquipoPorAño(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getCodigo(),mvto_listEquipo.getMvtoEquipo().getFechaHoraInicio());
                    if(mvto_listEquipo.getMvtoEquipo().getFechaHoraFin() != null){
                        mvto_listEquipo.getMvtoEquipo().setValorHora(tarifa.getValorHoraOperacion());
                        try{
                            double des=Double.parseDouble(mvto_listEquipo.getMvtoEquipo().getTotalMinutos());
                            double totalHoras = Double.parseDouble(""+(des/60));
                            double valorHora =Double.parseDouble(tarifa.getValorHoraOperacion());
                            mvto_listEquipo.getMvtoEquipo().setCostoTotal(""+(totalHoras * valorHora ));  
                            DecimalFormat formato2 = new DecimalFormat("#.00");
                            mvto_listEquipo.getMvtoEquipo().setCostoTotal(formato2.format((totalHoras * valorHora )));
                        }catch(Exception e){
                            mvto_listEquipo.getMvtoEquipo().setCostoTotal("NO PUDE");
                        }
                    }       
                listadoObjetos.add(mvto_listEquipo);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los Movimientos de Carbon");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }
    public ArrayList<MvtoCarbon_ListadoEquipos>     buscarMvtoCarbonInactivos(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<MvtoCarbon_ListadoEquipos> listadoObjetos = new ArrayList();
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT \n" +
                                                                    "[mcle_cdgo] --1\n" +
                                                                    "      ,[mcle_mvto_carbon_cdgo] --2\n" +
                                                                    "		  ,[mc_cdgo] --3\n" +
                                                                    "		  ,[mc_cntro_oper_cdgo] --4\n" +
                                                                    "				--Centro Operacion\n" +
                                                                    "				,[co_cdgo] --5\n" +
                                                                    "				,[co_desc] --6\n" +
                                                                    "				,CASE [co_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [co_estad] --7\n" +
                                                                    "		  ,[mc_cntro_cost_auxiliar_cdgo] --8\n" +
                                                                    "				--Centro Costo Auxiliar\n" +
                                                                    "				,mc_cntro_cost_auxiliar.[cca_cdgo] --9\n" +
                                                                    "				,mc_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo] --10\n" +
                                                                    "						--Subcentro de Costo\n" +
                                                                    "						,mc_cntro_cost_subcentro.[ccs_cdgo] --11\n" +
                                                                    "						,mc_cntro_cost_subcentro.[ccs_desc]  --12\n" +
                                                                    "						,CASE mc_cntro_cost_subcentro.[ccs_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN NULL ELSE NULL END AS [ccs_estad] --13\n" +
                                                                    "				,mc_cntro_cost_auxiliar.[cca_desc] --14\n" +
                                                                    "				,CASE mc_cntro_cost_auxiliar.[cca_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN NULL ELSE NULL END AS [cca_estad] --15\n" +
                                                                    "		  ,[mc_articulo_cdgo] --16\n" +
                                                                    "				--Articulo\n" +
                                                                    "				,[ar_cdgo] --17\n" +
                                                                    "				,[ar_desc] --18\n" +
                                                                    "				,CASE [ar_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ar_estad] --19\n" +
                                                                    "		  ,[mc_cliente_cdgo] --20\n" +
                                                                    "				--Cliente\n" +
                                                                    "				,mc_cliente.[cl_cdgo] --21\n" +
                                                                    "				,mc_cliente.[cl_desc] --22\n" +
                                                                    "				,CASE mc_cliente.[cl_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cl_estad] --23\n" +
                                                                    "		  ,[mc_transprtdora_cdgo] --24\n" +
                                                                    "				--Transportadora\n" +
                                                                    "				,[tr_cdgo] --25\n" +
                                                                    "				,[tr_nit] --26\n" +
                                                                    "				,[tr_desc] --27\n" +
                                                                    "				,[tr_observ] --28\n" +
                                                                    "				,CASE [tr_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [tr_estad] --29\n" +
                                                                    "		  ,[mc_fecha] --30\n" +
                                                                    "		  ,[mc_num_orden] --31\n" +
                                                                    "		  ,[mc_deposito] --32\n" +
                                                                    "		  ,[mc_consecutivo_tqute] --33\n" +
                                                                    "		  ,[mc_placa_vehiculo] --34\n" +
                                                                    "		  ,[mc_peso_vacio] --35\n" +
                                                                    "		  ,[mc_peso_lleno] --36\n" +
                                                                    "		  ,[mc_peso_neto] --37\n" +
                                                                    "		  ,[mc_fecha_entrad] --38\n" +
                                                                    "		  ,[mc_fecha_salid] --39\n" +
                                                                    "		  ,[mc_fecha_inicio_descargue] --40\n" +
                                                                    "		  ,[mc_fecha_fin_descargue] --41\n" +
                                                                    "		  ,[mc_usuario_cdgo] --42\n" +
                                                                    "				--Usuario Quien Registra desde App Movil\n" +
                                                                    "				,user_registra.[us_cdgo] --43\n" +
                                                                    "				,user_registra.[us_clave] --44\n" +
                                                                    "				,user_registra.[us_nombres] --45\n" +
                                                                    "				,user_registra.[us_apellidos] --46\n" +
                                                                    "				,user_registra.[us_perfil_cdgo] --47\n" +
                                                                    "					--Perfil Usuario Quien Registra\n" +
                                                                    "					,prf_registra.[prf_cdgo] --48\n" +
                                                                    "					,prf_registra.[prf_desc] --49\n" +
                                                                    "					,CASE prf_registra.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --50\n" +
                                                                    "				,user_registra.[us_correo] --51\n" +
                                                                    "				,CASE user_registra.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --52\n" +
                                                                    "		  ,[mc_observ] --53\n" +
                                                                    "		  ,[mc_estad_mvto_carbon_cdgo] --54\n" +
                                                                    "				--Estado MvtoCarbon\n" +
                                                                    "				,[emc_cdgo] --55\n" +
                                                                    "				,[emc_desc] --56\n" +
                                                                    "				,CASE [emc_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [emc_estad] --57\n" +
                                                                    "		  ,CASE [mc_conexion_peso_ccarga] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [mc_conexion_peso_ccarga] --58\n" +
                                                                    "		  ,[mc_registro_manual] --59\n" +
                                                                    "		  ,[mc_usuario_registro_manual_cdgo] --60\n" +
                                                                    "				--Usuario Quien Registra Manual\n" +
                                                                    "				,user_registro_manual.[us_cdgo] --61\n" +
                                                                    "				,user_registro_manual.[us_clave] --62\n" +
                                                                    "				,user_registro_manual.[us_nombres] --63\n" +
                                                                    "				,user_registro_manual.[us_apellidos] --64\n" +
                                                                    "				,user_registro_manual.[us_perfil_cdgo] --65\n" +
                                                                    "					--Perfil Usuario Quien Registra Manual\n" +
                                                                    "					,prf_registra_manual.[prf_cdgo] --66\n" +
                                                                    "					,prf_registra_manual.[prf_desc] --67\n" +
                                                                    "					,CASE prf_registra_manual.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --68\n" +
                                                                    "				,user_registro_manual.[us_correo] --69\n" +
                                                                    "				,CASE user_registro_manual.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --70\n" +
                                                                    "		,[mcle_asignacion_equipo_cdgo] --71\n" +
                                                                    "				,[ae_cdgo] --72\n" +
                                                                    "				,ae_cntro_oper_cdgo --73\n" +
                                                                    "					--Centro Operacion\n" +
                                                                    "					,ae_co_cdgo --74\n" +
                                                                    "					,ae_co_desc --75\n" +
                                                                    "					,CASE ae_co_estado WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_co_estado --76\n" +
                                                                    "				,ae_solicitud_listado_equipo_cdgo --77\n" +
                                                                    "					-- Solicitud Listado Equipo\n" +
                                                                    "					,ae_sle_cdgo --78\n" +
                                                                    "					,ae_sle_solicitud_equipo_cdgo --79\n" +
                                                                    "							--Solicitud Equipo\n" +
                                                                    "							,ae_sle_se_cdgo --80\n" +
                                                                    "							,ae_sle_se_cntro_oper_cdgo --81\n" +
                                                                    "								--CentroOperación SolicitudEquipo\n" +
                                                                    "								,ae_sle_se_co_cdgo --82\n" +
                                                                    "								,ae_sle_se_co_desc --83\n" +
                                                                    "								,CASE ae_sle_se_co_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_co_estad --84\n" +
                                                                    "							,ae_sle_se_fecha --85\n" +
                                                                    "							,ae_sle_se_usuario_realiza_cdgo --86\n" +
                                                                    "								--Usuario SolicitudEquipo\n" +
                                                                    "								,ae_sle_se_us_registra_cdgo --87\n" +
                                                                    "								,ae_sle_se_us_registra_clave --88\n" +
                                                                    "								,ae_sle_se_us_registra_nombres --89\n" +
                                                                    "								,ae_sle_se_us_registra_apellidos --90\n" +
                                                                    "								,ae_sle_se_us_registra_perfil_cdgo --91\n" +
                                                                    "										--Perfil Usuario Quien Registra Manual\n" +
                                                                    "										,ae_sle_se_prf_us_registra_cdgo --92\n" +
                                                                    "										,ae_sle_se_prf_us_registra_desc --93\n" +
                                                                    "										,CASE ae_sle_se_prf_us_registra_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_prf_us_registra_estad --94\n" +
                                                                    "								,ae_sle_se_us_registra_correo --95\n" +
                                                                    "								,CASE ae_sle_se_us_registra_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_us_registra_estad --96\n" +
                                                                    "							,ae_sle_se_fecha_registro --97\n" +
                                                                    "							,ae_sle_se_estado_solicitud_equipo_cdgo --98\n" +
                                                                    "								--Estado de la solicitud\n" +
                                                                    "								,ae_sle_se_ese_cdgo --99\n" +
                                                                    "								,ae_sle_se_ese_desc --100\n" +
                                                                    "								,CASE ae_sle_se_ese_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_ese_estad --101\n" +
                                                                    "							,ae_sle_se_fecha_confirmacion --102\n" +
                                                                    "							,ae_se_usuario_confirma_cdgo --103\n" +
                                                                    "								--Usuario SolicitudEquipo\n" +
                                                                    "								,ae_sle_se_us_confirma_cdgo --104\n" +
                                                                    "								,ae_sle_se_us_confirma_clave --105\n" +
                                                                    "								,ae_sle_se_us_confirma_nombres --106\n" +
                                                                    "								,ae_sle_se_us_confirma_apellidos --107\n" +
                                                                    "								,ae_sle_se_us_confirma_perfil_cdgo --108\n" +
                                                                    "										--Perfil Usuario Quien Registra Manual\n" +
                                                                    "										,ae_sle_se_prf_us_confirma_cdgo --109\n" +
                                                                    "										,ae_sle_se_prf_us_confirma_desc --110\n" +
                                                                    "										,CASE ae_sle_se_prf_us_confirma_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_prf_us_confirma_estad --111\n" +
                                                                    "								,ae_sle_se_us_confirma_correo --112\n" +
                                                                    "								,CASE ae_sle_se_us_confirma_estado WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_us_confirma_estado --113\n" +
                                                                    "							,ae_sle_se_confirmacion_solicitud_equipo_cdgo --114\n" +
                                                                    "								--Confirmacion solicitudEquipo\n" +
                                                                    "								,ae_sle_se_cse_cdgo --115\n" +
                                                                    "								,ae_sle_se_ces_desc --116\n" +
                                                                    "								,CASE ae_sle_se_ces_estado WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_ces_estado --117\n" +
                                                                    "					,ae_sle_tipo_equipo_cdgo --118\n" +
                                                                    "						--Tipo de Equipo\n" +
                                                                    "						,ae_sle_te_cdgo --119\n" +
                                                                    "						,ae_sle_te_desc --120\n" +
                                                                    "						,CASE ae_sle_te_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_te_estad --121\n" +
                                                                    "					,ae_sle_marca_equipo --122\n" +
                                                                    "					,ae_sle_modelo_equipo --123\n" +
                                                                    "					,ae_sle_cant_equip --124\n" +
                                                                    "					,ae_sle_observ --125\n" +
                                                                    "					,ae_sle_fecha_hora_inicio --126\n" +
                                                                    "					,ae_sle_fecha_hora_fin --127\n" +
                                                                    "					,ae_sle_cant_minutos --128\n" +
                                                                    "					,ae_sle_labor_realizada_cdgo --129\n" +
                                                                    "					-- Labor Realizada\n" +
                                                                    "							,ae_sle_lr_cdgo --130\n" +
                                                                    "							,ae_sle_lr_desc --131\n" +
                                                                    "							,CASE ae_sle_lr_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_lr_estad --132\n" +
                                                                    "					,ae_sle_sle_motonave_cdgo --133\n" +
                                                                    "					--Motonave\n" +
                                                                    "							,ae_sle_mn_cdgo --134\n" +
                                                                    "							,ae_sle_mn_desc --135\n" +
                                                                    "							,CASE ae_sle_mn_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_mn_estad --136\n" +
                                                                    "					,ae_sle_cntro_cost_auxiliar_cdgo --137\n" +
                                                                    "						--Centro Costo Auxiliar\n" +
                                                                    "						,ae_sle_cca_cdgo --138\n" +
                                                                    "						,ae_sle_cca_cntro_cost_subcentro_cdgo --139\n" +
                                                                    "							-- SubCentro de Costo\n" +
                                                                    "							,ae_sle_ccs_cdgo --140\n" +
                                                                    "							,ae_sle_ccs_desc --141\n" +
                                                                    "							,CASE ae_sle_ccs_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_ccs_estad --142\n" +
                                                                    "						,ae_sle_cca_desc --143\n" +
                                                                    "						,CASE ae_sle_cca_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_cca_estad --144\n" +
                                                                    "					,ae_sle_compania_cdgo --145\n" +
                                                                    "						--Compañia\n" +
                                                                    "						,ae_sle_cp_cdgo --146\n" +
                                                                    "						,ae_sle_cp_desc --147\n" +
                                                                    "						,CASE ae_sle_cp_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_cp_estad --148\n" +
                                                                    "				,ae_fecha_registro --149\n" +
                                                                    "				,ae_fecha_hora_inicio --150\n" +
                                                                    "				,ae_fecha_hora_fin --151\n" +
                                                                    "				,ae_cant_minutos --152\n" +
                                                                    "				,ae_equipo_cdgo --153\n" +
                                                                    "					--Equipo\n" +
                                                                    "					,ae_eq_cdgo --154\n" +
                                                                    "					,ae_eq_tipo_equipo_cdgo --155\n" +
                                                                    "						--Tipo Equipo\n" +
                                                                    "						,ae_eq_te_cdgo --156\n" +
                                                                    "						,ae_eq_te_desc --157\n" +
                                                                    "						,CASE ae_eq_te_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_te_estad --158\n" +
                                                                    "					,ae_eq_codigo_barra --159\n" +
                                                                    "					,ae_eq_referencia --160\n" +
                                                                    "					,ae_eq_producto --161\n" +
                                                                    "					,ae_eq_capacidad --162\n" +
                                                                    "					,ae_eq_marca --163\n" +
                                                                    "					,ae_eq_modelo --164\n" +
                                                                    "					,ae_eq_serial --165\n" +
                                                                    "					,ae_eq_desc --166\n" +
                                                                    "					,ae_eq_clasificador1_cdgo --167\n" +
                                                                    "						-- Clasificador 1\n" +
                                                                    "						,ae_eq_ce1_cdgo --168\n" +
                                                                    "						,ae_eq_ce1_desc --169\n" +
                                                                    "						,CASE ae_eq_ce1_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_ce1_estad --170\n" +
                                                                    "					,ae_eq_clasificador2_cdgo --171\n" +
                                                                    "						-- Clasificador 2\n" +
                                                                    "						,ae_eq_ce2_cdgo --172\n" +
                                                                    "						,ae_eq_ce2_desc --173\n" +
                                                                    "						,CASE ae_eq_ce2_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_ce2_estad --174\n" +
                                                                    "					,ae_eq_proveedor_equipo_cdgo --175\n" +
                                                                    "						--Proveedor Equipo\n" +
                                                                    "						,ae_eq_pe_cdgo --176\n" +
                                                                    "						,ae_eq_pe_nit --177\n" +
                                                                    "						,ae_eq_pe_desc --178\n" +
                                                                    "						,CASE ae_eq_pe_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_pe_estad --179\n" +
                                                                    "					,ae_eq_equipo_pertenencia_cdgo --180\n" +
                                                                    "						-- Equipo Pertenencia\n" +
                                                                    "						,ae_eq_ep_cdgo --181\n" +
                                                                    "						,ae_eq_ep_desc --182\n" +
                                                                    "						,CASE ae_eq_ep_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_ep_estad --183\n" +
                                                                    "					,ae_eq_eq_observ --184\n" +
                                                                    "					,CASE ae_eq_eq_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_eq_estad --185\n" +
                                                                    "					,ae_eq_actvo_fijo_id --186\n" +
                                                                    "					,ae_eq_actvo_fijo_referencia --187\n" +
                                                                    "					,ae_eq_actvo_fijo_desc --188\n" +
                                                                    "				,ae_equipo_pertenencia_cdgo --189\n" +
                                                                    "				-- Equipo Pertenencia\n" +
                                                                    "						,ae_ep_cdgo --190\n" +
                                                                    "						,ae_ep_desc --191\n" +
                                                                    "						,CASE ae_ep_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_ep_estad --192\n" +
                                                                    "				,ae_cant_minutos_operativo --193\n" +
                                                                    "				,ae_cant_minutos_parada --194\n" +
                                                                    "				,ae_cant_minutos_total --195\n" +
                                                                    "				,CASE ae_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_estad --196\n" +
                                                                    "		,[mcle_mvto_equipo_cdgo] --197\n" +
                                                                    "				--Movimiento Equipo\n" +
                                                                    "				,[me_cdgo] --198\n" +
                                                                    "				,[me_asignacion_equipo_cdgo] --199\n" +
                                                                    "				,[me_fecha] --200\n" +
                                                                    "				,[me_proveedor_equipo_cdgo] --201\n" +
                                                                    "					--Proveedor Equipo\n" +
                                                                    "					,[pe_cdgo] AS me_pe_cdgo --202\n" +
                                                                    "					,[pe_nit] AS me_pe_nit --203\n" +
                                                                    "					,[pe_desc] AS me_pe_desc --204\n" +
                                                                    "					,CASE [pe_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS me_pe_estad --205\n" +
                                                                    "				,[me_num_orden] --206\n" +
                                                                    "				,[me_cntro_cost_auxiliar_cdgo] --207\n" +
                                                                    "					-- Centro Costo Auxiliar\n" +
                                                                    "					,me_cntro_cost_auxiliar.[cca_cdgo] AS me_cca_cdgo --208\n" +
                                                                    "					,me_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo] AS me_cca_cntro_cost_subcentro_cdgo --209\n" +
                                                                    "						--Centro Costo Subcentro\n" +
                                                                    "						,me_cntro_cost_subcentro.[ccs_cdgo] --210\n" +
                                                                    "						,me_cntro_cost_subcentro.[ccs_desc] --211\n" +
                                                                    "						,CASE me_cntro_cost_subcentro.[ccs_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ccs_estad] --212\n" +
                                                                    "					,me_cntro_cost_auxiliar.[cca_desc] AS me_cca_desc --213\n" +
                                                                    "					,CASE me_cntro_cost_auxiliar.[cca_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cca_estad] --214\n" +
                                                                    "				,[me_labor_realizada_cdgo] --215\n" +
                                                                    "					--Labor Realizada\n" +
                                                                    "					,[lr_cdgo]  --216\n" +
                                                                    "					,[lr_desc] --217\n" +
                                                                    "					,CASE [lr_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [lr_estad] --218\n" +
                                                                    "				,[me_cliente_cdgo] --219\n" +
                                                                    "					--Cliente \n" +
                                                                    "					,me_cliente.[cl_cdgo] --220\n" +
                                                                    "					,me_cliente.[cl_desc] --221\n" +
                                                                    "					,CASE me_cliente.[cl_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cl_estad] --222\n" +
                                                                    "				,[me_articulo_cdgo] --223\n" +
                                                                    "					--Producto\n" +
                                                                    "					,[ar_cdgo] --224\n" +
                                                                    "					,[ar_desc] --225\n" +
                                                                    "					,CASE [ar_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ar_estad] --226\n" +
                                                                    "				,[me_fecha_hora_inicio] --227\n" +
                                                                    "				,[me_fecha_hora_fin] --228\n" +
                                                                    "				,[me_total_minutos] --229\n" +
                                                                    "				,[me_valor_hora] --230\n" +
                                                                    "				,[me_costo_total] --231\n" +
                                                                    "				,[me_recobro_cdgo] --232\n" +
                                                                    "					--Recobro\n" +
                                                                    "					,[rc_cdgo] --233\n" +
                                                                    "					,[rc_desc] --234\n" +
                                                                    "					,CASE [rc_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [rc_estad] --235\n" +
                                                                    "				,[me_cliente_recobro_cdgo] --236\n" +
                                                                    "					--Cliente Recobro\n" +
                                                                    "					,[clr_cdgo] --237\n" +
                                                                    "					,[clr_cliente_cdgo] --238\n" +
                                                                    "						--Cliente\n" +
                                                                    "						,me_clr_cliente.[cl_cdgo] --239\n" +
                                                                    "						,me_clr_cliente.[cl_desc] --240\n" +
                                                                    "						,CASE me_clr_cliente.[cl_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cl_estad] --241\n" +
                                                                    "					,[clr_usuario_cdgo] --242\n" +
                                                                    "						--Usuario Quien Registra Recobro\n" +
                                                                    "						,me_clr_usuario.[us_cdgo] --243\n" +
                                                                    "						,me_clr_usuario.[us_clave] --244\n" +
                                                                    "						,me_clr_usuario.[us_nombres] --245\n" +
                                                                    "						,me_clr_usuario.[us_apellidos] --246\n" +
                                                                    "						,me_clr_usuario.[us_perfil_cdgo] --247\n" +
                                                                    "							--Perfil Usuario Registra recobro\n" +
                                                                    "							,me_clr_us_perfil.[prf_cdgo] --248\n" +
                                                                    "							,me_clr_us_perfil.[prf_desc] --249\n" +
                                                                    "							,CASE me_clr_us_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --250\n" +
                                                                    "						,me_clr_usuario.[us_correo] --251\n" +
                                                                    "						,CASE me_clr_usuario.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --252\n" +
                                                                    "					,[clr_valor_recobro] --253\n" +
                                                                    "					,[clr_fecha_registro] --254\n" +
                                                                    "				,[me_costo_total_recobro_cliente] --255\n" +
                                                                    "				,[me_usuario_registro_cdgo] --256\n" +
                                                                    "					--Usuario Quien Registra Equipo\n" +
                                                                    "					,me_us_registro.[us_cdgo] --257\n" +
                                                                    "					,me_us_registro.[us_clave] --258\n" +
                                                                    "					,me_us_registro.[us_nombres] --259\n" +
                                                                    "					,me_us_registro.[us_apellidos] --260\n" +
                                                                    "					,me_us_registro.[us_perfil_cdgo] --261\n" +
                                                                    "						--Perfil de Usuario Quien Registra Equipo\n" +
                                                                    "						,me_us_regist_perfil.[prf_cdgo] --262\n" +
                                                                    "						,me_us_regist_perfil.[prf_desc] --263\n" +
                                                                    "						,CASE me_us_regist_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --264\n" +
                                                                    "					,me_us_registro.[us_correo] --265\n" +
                                                                    "					,CASE me_us_registro.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --266\n" +
                                                                    "				,[me_usuario_autorizacion_cdgo] --267\n" +
                                                                    "					,me_us_autorizacion.[us_cdgo] --268\n" +
                                                                    "					,me_us_autorizacion.[us_clave] --269\n" +
                                                                    "					,me_us_autorizacion.[us_nombres] --270\n" +
                                                                    "					,me_us_autorizacion.[us_apellidos] --271\n" +
                                                                    "					,me_us_autorizacion.[us_perfil_cdgo] --272\n" +
                                                                    "						,me_us_autoriza_perfil.[prf_cdgo] --273\n" +
                                                                    "						,me_us_autoriza_perfil.[prf_desc] --274\n" +
                                                                    "						,CASE me_us_autoriza_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --275\n" +
                                                                    "					,me_us_autorizacion.[us_correo] --276\n" +
                                                                    "					,CASE me_us_autorizacion.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --277\n" +
                                                                    "				,[me_autorizacion_recobro_cdgo] --278\n" +
                                                                    "					,[are_cdgo] --279\n" +
                                                                    "					,[are_desc] --280\n" +
                                                                    "					,CASE [are_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [are_estad] --281\n" +
                                                                    "				,[me_observ_autorizacion] --282\n" +
                                                                    "				,[me_inactividad] --283\n" +
                                                                    "				,[me_causa_inactividad_cdgo] --284\n" +
                                                                    "					,[ci_cdgo] --285\n" +
                                                                    "					,[ci_desc] --286\n" +
                                                                    "					,CASE [ci_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ci_estad] --287\n" +
                                                                    "				,[me_usuario_inactividad_cdgo] --288\n" +
                                                                    "					,me_us_inactividad.[us_cdgo] --289\n" +
                                                                    "					,me_us_inactividad.[us_clave] --290\n" +
                                                                    "					,me_us_inactividad.[us_nombres] --291\n" +
                                                                    "					,me_us_inactividad.[us_apellidos] --292\n" +
                                                                    "					,me_us_inactividad.[us_perfil_cdgo] --293\n" +
                                                                    "						,me_us_inactvdad_perfil.[prf_cdgo] --294\n" +
                                                                    "						,me_us_inactvdad_perfil.[prf_desc] --295\n" +
                                                                    "						,CASE me_us_inactvdad_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --296\n" +
                                                                    "					,me_us_inactividad.[us_correo] --297\n" +
                                                                    "					,CASE me_us_inactividad.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad]	 --298\n" +
                                                                    "                           ,[me_motivo_parada_cdgo]--299\n" +
                                                                    "                                       ,[mpa_cdgo]--300\n" +
                                                                    "                                       ,[mpa_desc]--301\n" +
                                                                    "                                       ,[mpa_estad]--302\n" +
                                                                    "                         ,[me_observ] --303\n" +
                                                                    "				,CASE [me_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_estado]	 --304\n" +
                                                                    "				,CASE [me_desde_mvto_carbon] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [me_desde_mvto_carbon]	 --305\n" +
                                                                    "		,CASE [mcle_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [mcle_estado]	 --306\n" +
                                                                    "            ,Tiempo_Vehiculo_Descargue=(SELECT (DATEDIFF (MINUTE,  [mc_fecha_inicio_descargue], [mc_fecha_fin_descargue])))--307\n" +
                                                                    "           ,Tiempo_Equipo_Descargue=(SELECT (DATEDIFF (MINUTE,  [me_fecha_hora_inicio], [me_fecha_hora_fin]))) --308\n" +
                                                                    "      ,[ae_sle_mn_base_datos_cdgo]--309\n" +
                                                                    "      ,mc_articulo_base_datos.[bd_cdgo]   --310 \n" +
                                                                    "      ,mc_cliente_base_datos.[bd_cdgo]   --311 \n" +
                                                                    "      ,mc_transprtdora_base_datos.[bd_cdgo]   --312 \n" +
                                                                    "      ,me_cliente_base_datos.[bd_cdgo]   --313 \n" +
                                                                    "      ,me_articulo_base_datos.[bd_cdgo]   --314 \n" +
                                                                    "  FROM ["+DB+"].[dbo].[mvto_carbon]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [mc_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] mc_cntro_cost_auxiliar ON [mc_cntro_cost_auxiliar_cdgo]=mc_cntro_cost_auxiliar.[cca_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] mc_cntro_cost_subcentro ON [cca_cntro_cost_subcentro_cdgo]= mc_cntro_cost_subcentro.[ccs_cdgo]\n" +
                                                                    //"	LEFT JOIN ["+DB+"].[dbo].[articulo] ON [mc_articulo_cdgo]=[ar_cdgo]\n" +
                                                                            "		LEFT JOIN ["+DB+"].[dbo].[articulo] mc_articulo ON [mc_articulo_cdgo]=mc_articulo.[ar_cdgo] AND mc_articulo.[ar_base_datos_cdgo]=[mc_articulo_base_datos_cdgo]\n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_articulo_base_datos ON mc_articulo.[ar_base_datos_cdgo]=mc_articulo_base_datos.[bd_cdgo] \n"+
                                                                    //"	LEFT JOIN ["+DB+"].[dbo].[cliente] mc_cliente ON [mc_cliente_cdgo]=mc_cliente.[cl_cdgo]\n" +
        "		LEFT JOIN ["+DB+"].[dbo].[cliente] mc_cliente ON [mc_cliente_cdgo]=mc_cliente.[cl_cdgo] AND mc_cliente.[cl_base_datos_cdgo]=[mc_cliente_base_datos_cdgo]\n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cliente_base_datos ON mc_cliente.[cl_base_datos_cdgo]=mc_cliente_base_datos.[bd_cdgo] \n"+
                                                                    //"	LEFT JOIN ["+DB+"].[dbo].[transprtdora] ON [mc_transprtdora_cdgo]=[tr_cdgo]\n" +
                                                                            "		LEFT JOIN ["+DB+"].[dbo].[transprtdora] ON [mc_transprtdora_cdgo]=[tr_cdgo] AND [tr_base_datos_cdgo]=[mc_transprtdora_base_datos_cdgo] \n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_transprtdora_base_datos ON [tr_base_datos_cdgo]=mc_transprtdora_base_datos.[bd_cdgo] \n"+  
                                                                    "	INNER JOIN ["+DB+"].[dbo].[usuario] user_registra ON [mc_usuario_cdgo] = user_registra.[us_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[perfil] prf_registra ON user_registra.[us_perfil_cdgo]=prf_registra.[prf_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[estad_mvto_carbon] ON [mc_estad_mvto_carbon_cdgo]=[emc_cdgo]\n" +
                                                                    "	LEFT JOIN ["+DB+"].[dbo].[usuario] user_registro_manual ON [mc_usuario_registro_manual_cdgo] = user_registro_manual.[us_cdgo]\n" +
                                                                    "	LEFT JOIN ["+DB+"].[dbo].[perfil] prf_registra_manual ON user_registro_manual.[us_perfil_cdgo]=prf_registra_manual.[prf_cdgo]\n" +
                                                                    "	LEFT JOIN ["+DB+"].[dbo].[mvto_carbon_listado_equipo] ON [mcle_mvto_carbon_cdgo]=[mc_cdgo]\n" +
                                                                    "	INNER JOIN (SELECT [ae_cdgo] AS ae_cdgo\n" +
                                                                    "					  ,[ae_cntro_oper_cdgo] AS ae_cntro_oper_cdgo\n" +
                                                                    "							--Centro Operacion\n" +
                                                                    "							,ae_cntro_oper.[co_cdgo] AS ae_co_cdgo\n" +
                                                                    "							,ae_cntro_oper.[co_desc] AS ae_co_desc\n" +
                                                                    "							,ae_cntro_oper.[co_estad] AS ae_co_estado\n" +
                                                                    "					  ,[ae_solicitud_listado_equipo_cdgo] AS ae_solicitud_listado_equipo_cdgo\n" +
                                                                    "							-- Solicitud Listado Equipo\n" +
                                                                    "							,[sle_cdgo] AS ae_sle_cdgo\n" +
                                                                    "							,[sle_solicitud_equipo_cdgo] AS ae_sle_solicitud_equipo_cdgo\n" +
                                                                    "								  --Solicitud Equipo\n" +
                                                                    "								  ,[se_cdgo] AS ae_sle_se_cdgo\n" +
                                                                    "								  ,[se_cntro_oper_cdgo] AS ae_sle_se_cntro_oper_cdgo\n" +
                                                                    "										--CentroOperación SolicitudEquipo\n" +
                                                                    "										,se_cntro_oper.[co_cdgo] AS ae_sle_se_co_cdgo\n" +
                                                                    "										,se_cntro_oper.[co_desc] AS ae_sle_se_co_desc\n" +
                                                                    "										,se_cntro_oper.[co_estad] AS ae_sle_se_co_estad\n" +
                                                                    "								  ,[se_fecha] AS ae_sle_se_fecha\n" +
                                                                    "								  ,[se_usuario_realiza_cdgo] AS ae_sle_se_usuario_realiza_cdgo\n" +
                                                                    "										--Usuario SolicitudEquipo\n" +
                                                                    "										,se_usuario_realiza.[us_cdgo] AS ae_sle_se_us_registra_cdgo\n" +
                                                                    "										,se_usuario_realiza.[us_clave] AS ae_sle_se_us_registra_clave\n" +
                                                                    "										,se_usuario_realiza.[us_nombres] AS ae_sle_se_us_registra_nombres\n" +
                                                                    "										,se_usuario_realiza.[us_apellidos] AS ae_sle_se_us_registra_apellidos\n" +
                                                                    "										,se_usuario_realiza.[us_perfil_cdgo] AS ae_sle_se_us_registra_perfil_cdgo\n" +
                                                                    "											--Perfil Usuario Quien Registra Manual\n" +
                                                                    "											,ae_prf_registra.[prf_cdgo] AS ae_sle_se_prf_us_registra_cdgo\n" +
                                                                    "											,ae_prf_registra.[prf_desc] AS ae_sle_se_prf_us_registra_desc\n" +
                                                                    "											,ae_prf_registra.[prf_estad] AS ae_sle_se_prf_us_registra_estad\n" +
                                                                    "										,se_usuario_realiza.[us_correo] AS ae_sle_se_us_registra_correo\n" +
                                                                    "										,se_usuario_realiza.[us_estad] AS ae_sle_se_us_registra_estad\n" +
                                                                    "								  ,[se_fecha_registro] AS ae_sle_se_fecha_registro\n" +
                                                                    "								  ,[se_estado_solicitud_equipo_cdgo] AS ae_sle_se_estado_solicitud_equipo_cdgo\n" +
                                                                    "										--Estado de la solicitud\n" +
                                                                    "										,[ese_cdgo] AS ae_sle_se_ese_cdgo\n" +
                                                                    "										,[ese_desc] AS ae_sle_se_ese_desc\n" +
                                                                    "										,[ese_estad] AS ae_sle_se_ese_estad\n" +
                                                                    "								  ,[se_fecha_confirmacion] AS ae_sle_se_fecha_confirmacion\n" +
                                                                    "								  ,[se_usuario_confirma_cdgo] AS ae_se_usuario_confirma_cdgo\n" +
                                                                    "										--Usuario SolicitudEquipo\n" +
                                                                    "										,se_usuario_confirma.[us_cdgo] AS ae_sle_se_us_confirma_cdgo\n" +
                                                                    "										,se_usuario_confirma.[us_clave] AS ae_sle_se_us_confirma_clave\n" +
                                                                    "										,se_usuario_confirma.[us_nombres] AS ae_sle_se_us_confirma_nombres\n" +
                                                                    "										,se_usuario_confirma.[us_apellidos] AS ae_sle_se_us_confirma_apellidos\n" +
                                                                    "										,se_usuario_confirma.[us_perfil_cdgo] AS ae_sle_se_us_confirma_perfil_cdgo\n" +
                                                                    "											--Perfil Usuario Quien Registra Manual\n" +
                                                                    "											,ae_prf_registra_confirma.[prf_cdgo] AS ae_sle_se_prf_us_confirma_cdgo\n" +
                                                                    "											,ae_prf_registra_confirma.[prf_desc] AS ae_sle_se_prf_us_confirma_desc\n" +
                                                                    "											,ae_prf_registra_confirma.[prf_estad] AS ae_sle_se_prf_us_confirma_estad\n" +
                                                                    "										,se_usuario_confirma.[us_correo] AS ae_sle_se_us_confirma_correo\n" +
                                                                    "										,se_usuario_confirma.[us_estad] AS ae_sle_se_us_confirma_estado\n" +
                                                                    "								  ,[se_confirmacion_solicitud_equipo_cdgo] AS ae_sle_se_confirmacion_solicitud_equipo_cdgo\n" +
                                                                    "										--Confirmacion solicitudEquipo\n" +
                                                                    "										,[cse_cdgo] AS ae_sle_se_cse_cdgo\n" +
                                                                    "										,[cse_desc] AS ae_sle_se_ces_desc\n" +
                                                                    "										,[cse_estad] AS ae_sle_se_ces_estado\n" +
                                                                    "							,[sle_tipo_equipo_cdgo] AS ae_sle_tipo_equipo_cdgo\n" +
                                                                    "								--Tipo de Equipo\n" +
                                                                    "								,sle_tipoEquipo.[te_cdgo] AS ae_sle_te_cdgo\n" +
                                                                    "								,sle_tipoEquipo.[te_desc] AS ae_sle_te_desc\n" +
                                                                    "								,sle_tipoEquipo.[te_estad] AS ae_sle_te_estad\n" +
                                                                    "							,[sle_marca_equipo] AS ae_sle_marca_equipo\n" +
                                                                    "							,[sle_modelo_equipo] AS ae_sle_modelo_equipo\n" +
                                                                    "							,[sle_cant_equip] AS ae_sle_cant_equip\n" +
                                                                    "							,[sle_observ] AS ae_sle_observ\n" +
                                                                    "							,[sle_fecha_hora_inicio] AS ae_sle_fecha_hora_inicio\n" +
                                                                    "							,[sle_fecha_hora_fin] AS ae_sle_fecha_hora_fin\n" +
                                                                    "							,[sle_cant_minutos] AS ae_sle_cant_minutos\n" +
                                                                    "							,[sle_labor_realizada_cdgo] AS ae_sle_labor_realizada_cdgo\n" +
                                                                    "							-- Labor Realizada\n" +
                                                                    "								  ,[lr_cdgo] AS ae_sle_lr_cdgo\n" +
                                                                    "								  ,[lr_desc] AS ae_sle_lr_desc\n" +
                                                                    "								  ,[lr_estad] AS ae_sle_lr_estad\n" +
                                                                    "							,[sle_motonave_cdgo] AS ae_sle_sle_motonave_cdgo\n" +
                                                                    "							--Motonave\n" +
                                                                    "								  ,[mn_cdgo] AS ae_sle_mn_cdgo\n" +
                                                                    "								  ,[mn_desc] AS ae_sle_mn_desc\n" +
                                                                    "								  ,[mn_estad] AS ae_sle_mn_estad\n" +
                                                                    "							,[sle_cntro_cost_auxiliar_cdgo] AS ae_sle_cntro_cost_auxiliar_cdgo\n" +
                                                                    "								--Centro Costo Auxiliar\n" +
                                                                    "								,[cca_cdgo] AS ae_sle_cca_cdgo\n" +
                                                                    "								,[cca_cntro_cost_subcentro_cdgo] AS ae_sle_cca_cntro_cost_subcentro_cdgo\n" +
                                                                    "									-- SubCentro de Costo\n" +
                                                                    "									,[ccs_cdgo] AS ae_sle_ccs_cdgo\n" +
                                                                    "									,[ccs_desc] AS ae_sle_ccs_desc\n" +
                                                                    "									,[ccs_estad] AS ae_sle_ccs_estad\n" +
                                                                    "								,[cca_desc] AS ae_sle_cca_desc\n" +
                                                                    "								,[cca_estad] AS ae_sle_cca_estad\n" +
                                                                    "							,[sle_compania_cdgo] AS ae_sle_compania_cdgo\n" +
                                                                    "								--Compañia\n" +
                                                                    "								,[cp_cdgo] AS ae_sle_cp_cdgo\n" +
                                                                    "								,[cp_desc] AS ae_sle_cp_desc\n" +
                                                                    "								,[cp_estad] AS ae_sle_cp_estad\n" +
                                                                    "					  ,[ae_fecha_registro] AS ae_fecha_registro\n" +
                                                                    "					  ,[ae_fecha_hora_inicio] AS ae_fecha_hora_inicio\n" +
                                                                    "					  ,[ae_fecha_hora_fin] AS ae_fecha_hora_fin\n" +
                                                                    "					  ,[ae_cant_minutos] AS ae_cant_minutos\n" +
                                                                    "					  ,[ae_equipo_cdgo] AS ae_equipo_cdgo\n" +
                                                                    "							--Equipo\n" +
                                                                    "							,[eq_cdgo] AS ae_eq_cdgo\n" +
                                                                    "							,[eq_tipo_equipo_cdgo] AS ae_eq_tipo_equipo_cdgo\n" +
                                                                    "								--Tipo Equipo\n" +
                                                                    "								,eq_tipo_equipo.[te_cdgo] AS ae_eq_te_cdgo\n" +
                                                                    "								,eq_tipo_equipo.[te_desc] AS ae_eq_te_desc\n" +
                                                                    "								,eq_tipo_equipo.[te_estad] AS ae_eq_te_estad\n" +
                                                                    "							,[eq_codigo_barra] AS ae_eq_codigo_barra\n" +
                                                                    "							,[eq_referencia] AS ae_eq_referencia\n" +
                                                                    "							,[eq_producto] AS ae_eq_producto\n" +
                                                                    "							,[eq_capacidad] AS ae_eq_capacidad\n" +
                                                                    "							,[eq_marca] AS ae_eq_marca\n" +
                                                                    "							,[eq_modelo] AS ae_eq_modelo\n" +
                                                                    "							,[eq_serial] AS ae_eq_serial\n" +
                                                                    "							,[eq_desc] AS ae_eq_desc\n" +
                                                                    "							,[eq_clasificador1_cdgo] AS ae_eq_clasificador1_cdgo\n" +
                                                                    "								-- Clasificador 1\n" +
                                                                    "								,eq_clasificador1.[ce_cdgo] AS ae_eq_ce1_cdgo\n" +
                                                                    "								,eq_clasificador1.[ce_desc] AS ae_eq_ce1_desc\n" +
                                                                    "								,eq_clasificador1.[ce_estad] AS ae_eq_ce1_estad\n" +
                                                                    "							,[eq_clasificador2_cdgo] AS ae_eq_clasificador2_cdgo\n" +
                                                                    "								-- Clasificador 2\n" +
                                                                    "								,eq_clasificador2.[ce_cdgo] AS ae_eq_ce2_cdgo\n" +
                                                                    "								,eq_clasificador2.[ce_desc] AS ae_eq_ce2_desc\n" +
                                                                    "								,eq_clasificador2.[ce_estad] AS ae_eq_ce2_estad\n" +
                                                                    "							,[eq_proveedor_equipo_cdgo] AS ae_eq_proveedor_equipo_cdgo\n" +
                                                                    "								--Proveedor Equipo\n" +
                                                                    "								,[pe_cdgo] AS ae_eq_pe_cdgo\n" +
                                                                    "								,[pe_nit] AS ae_eq_pe_nit\n" +
                                                                    "								,[pe_desc] AS ae_eq_pe_desc\n" +
                                                                    "								,[pe_estad] AS ae_eq_pe_estad\n" +
                                                                    "							,[eq_equipo_pertenencia_cdgo] AS ae_eq_equipo_pertenencia_cdgo\n" +
                                                                    "								-- Equipo Pertenencia\n" +
                                                                    "								,eq_pertenencia.[ep_cdgo] AS ae_eq_ep_cdgo\n" +
                                                                    "								,eq_pertenencia.[ep_desc] AS ae_eq_ep_desc\n" +
                                                                    "								,eq_pertenencia.[ep_estad] AS ae_eq_ep_estad\n" +
                                                                    "							,[eq_observ] AS ae_eq_eq_observ\n" +
                                                                    "							,[eq_estad] AS ae_eq_eq_estad\n" +
                                                                    "							,[eq_actvo_fijo_id] AS ae_eq_actvo_fijo_id\n" +
                                                                    "							,[eq_actvo_fijo_referencia] AS ae_eq_actvo_fijo_referencia\n" +
                                                                    "							,[eq_actvo_fijo_desc] AS ae_eq_actvo_fijo_desc\n" +
                                                                    "					  ,[ae_equipo_pertenencia_cdgo] AS ae_equipo_pertenencia_cdgo\n" +
                                                                    "						-- Equipo Pertenencia\n" +
                                                                    "								,ae_pertenencia.[ep_cdgo] AS ae_ep_cdgo\n" +
                                                                    "								,ae_pertenencia.[ep_desc] AS ae_ep_desc\n" +
                                                                    "								,ae_pertenencia.[ep_estad]	 AS ae_ep_estad\n" +
                                                                    "					  ,[ae_cant_minutos_operativo] AS ae_cant_minutos_operativo\n" +
                                                                    "					  ,[ae_cant_minutos_parada] AS ae_cant_minutos_parada\n" +
                                                                    "					  ,[ae_cant_minutos_total] AS ae_cant_minutos_total\n" +
                                                                    "					  ,[ae_estad] AS ae_estad\n" +
                                                                    "\t\t\t\t\t  ,[sle_motonave_base_datos_cdgo] AS   ae_sle_mn_base_datos_cdgo\n" +
                                                                    "					  FROM ["+DB+"].[dbo].[asignacion_equipo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[solicitud_listado_equipo] ON [ae_solicitud_listado_equipo_cdgo]=[sle_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[cntro_oper] ae_cntro_oper ON [ae_cntro_oper_cdgo]=ae_cntro_oper.[co_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[solicitud_equipo] ON [sle_solicitud_equipo_cdgo]=[se_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[cntro_oper] se_cntro_oper ON [se_cntro_oper_cdgo]=se_cntro_oper.[co_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[usuario] se_usuario_realiza ON [se_usuario_realiza_cdgo]=se_usuario_realiza.[us_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[perfil] ae_prf_registra ON se_usuario_realiza.[us_perfil_cdgo]=ae_prf_registra.[prf_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[estado_solicitud_equipo] ON [se_estado_solicitud_equipo_cdgo]=[ese_cdgo]\n" +
                                                                    "							LEFT JOIN  ["+DB+"].[dbo].[usuario] se_usuario_confirma ON [se_usuario_realiza_cdgo]=se_usuario_confirma.[us_cdgo]\n" +
                                                                    "							LEFT JOIN ["+DB+"].[dbo].[perfil] ae_prf_registra_confirma ON se_usuario_confirma.[us_perfil_cdgo]=ae_prf_registra_confirma.[prf_cdgo]\n" +
                                                                    "							LEFT JOIN  ["+DB+"].[dbo].[confirmacion_solicitud_equipo] ON [se_confirmacion_solicitud_equipo_cdgo]=[cse_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[tipo_equipo] sle_tipoEquipo ON [sle_tipo_equipo_cdgo]=sle_tipoEquipo.[te_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[labor_realizada] ON [sle_labor_realizada_cdgo]=[lr_cdgo]\n" +
                                                                    //"							LEFT  JOIN["+DB+"].[dbo].[motonave] ON [sle_motonave_cdgo]=[mn_cdgo]\n" +
                                                                    "		LEFT JOIN ["+DB+"].[dbo].[motonave] ON [sle_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[sle_motonave_base_datos_cdgo] \n" +
                                                                    "               LEFT JOIN ["+DB+"].[dbo].[base_datos] sle_motonave_base_datos ON  [mn_base_datos_cdgo]=sle_motonave_base_datos.[bd_cdgo] \n"+
                                                                    "							INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [sle_cntro_cost_auxiliar_cdgo]=[cca_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[compania] ON [sle_compania_cdgo]=[cp_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[tipo_equipo] eq_tipo_equipo ON [eq_tipo_equipo_cdgo]=eq_tipo_equipo.[te_cdgo]\n" +
                                                                    "							LEFT JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador1 ON [eq_clasificador1_cdgo]=eq_clasificador1.[ce_cdgo]\n" +
                                                                    "							LEFT JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador2 ON [eq_clasificador2_cdgo]=eq_clasificador2.[ce_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [eq_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
                                                                    "							LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] eq_pertenencia ON [eq_equipo_pertenencia_cdgo]=eq_pertenencia.[ep_cdgo]\n" +
                                                                    "							LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ae_pertenencia ON [ae_equipo_pertenencia_cdgo]=ae_pertenencia.[ep_cdgo]	\n" +
                                                                    "	) asignacion_equipo ON [mcle_asignacion_equipo_cdgo]=[ae_cdgo]\n" +
                                                                    "	--Movimiento Equipo\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[mvto_equipo] ON [mcle_mvto_equipo_cdgo]=[me_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [me_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] me_cntro_cost_auxiliar ON [me_cntro_cost_auxiliar_cdgo]=me_cntro_cost_auxiliar.[cca_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro]  me_cntro_cost_subcentro ON me_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo]=me_cntro_cost_subcentro.[ccs_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[labor_realizada] ON [me_labor_realizada_cdgo]=[lr_cdgo] \n" +
                                                                    //"	INNER JOIN ["+DB+"].[dbo].[cliente] me_cliente ON [me_cliente_cdgo]=me_cliente.[cl_cdgo]\n" +
                                                                   "		LEFT JOIN ["+DB+"].[dbo].[cliente] me_cliente ON [me_cliente_cdgo]=me_cliente.[cl_cdgo] AND me_cliente.[cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
                                                                    "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON me_cliente.[cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                                                                           // "	LEFT JOIN  ["+DB+"].[dbo].[articulo] ON [me_articulo_cdgo]=[ar_cdgo]\n" +
                                                                    "		LEFT JOIN ["+DB+"].[dbo].[articulo]  ON [me_articulo_cdgo]=[ar_cdgo] AND	[ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
                                                                    "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON [ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
                                                                    "	INNER JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[cliente_recobro] ON [me_cliente_recobro_cdgo]=[clr_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[cliente] me_clr_cliente ON [clr_cliente_cdgo]=me_clr_cliente.[cl_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[usuario] me_clr_usuario ON [clr_usuario_cdgo]=me_clr_usuario.[us_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[perfil] me_clr_us_perfil ON me_clr_usuario.[us_perfil_cdgo]=me_clr_us_perfil.[prf_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[usuario] me_us_registro ON [me_usuario_registro_cdgo]=me_us_registro.[us_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[perfil] me_us_regist_perfil ON me_us_registro.[us_perfil_cdgo]=me_us_regist_perfil.[prf_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[usuario] me_us_autorizacion ON [me_usuario_autorizacion_cdgo]=me_us_autorizacion.[us_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[perfil] me_us_autoriza_perfil ON me_us_autorizacion.[us_perfil_cdgo]=me_us_autoriza_perfil.[prf_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[autorizacion_recobro] ON [me_autorizacion_recobro_cdgo]=[are_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[causa_inactividad] ON [me_causa_inactividad_cdgo]=[ci_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[usuario] me_us_inactividad ON [me_usuario_inactividad_cdgo]=me_us_inactividad.[us_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[perfil] me_us_inactvdad_perfil ON me_us_inactividad.[us_perfil_cdgo]=me_us_inactvdad_perfil.[prf_cdgo]\n" +
                                                                    "   LEFT JOIN  ["+DB+"].[dbo].[motivo_parada] ON [me_motivo_parada_cdgo]= [mpa_cdgo]\n"  +       
                                                                    "	WHERE [mc_fecha] BETWEEN ? AND ? AND [me_inactividad]=1 AND [mc_estad_mvto_carbon_cdgo]=1 ORDER BY [mcle_mvto_carbon_cdgo] DESC");
            query.setString(1, DatetimeInicio);
            query.setString(2, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();              
            while(resultSet.next()){ 
                MvtoCarbon_ListadoEquipos mvto_listEquipo = new MvtoCarbon_ListadoEquipos();
                mvto_listEquipo.setCodigo(resultSet.getString(1));
                    MvtoCarbon mvtoCarbon = new MvtoCarbon();
                    mvtoCarbon.setCodigo(resultSet.getString(3));
                    mvtoCarbon.setCentroOperacion(new CentroOperacion(Integer.parseInt(resultSet.getString(5)),resultSet.getString(6),resultSet.getString(7)));
                    mvtoCarbon.setCentroCostoAuxiliar(new CentroCostoAuxiliar(Integer.parseInt(resultSet.getString(9)),new CentroCostoSubCentro(Integer.parseInt(resultSet.getString(11)),resultSet.getString(12),resultSet.getString(13)),resultSet.getString(14),resultSet.getString(15)));
                    mvtoCarbon.setArticulo(new Articulo(resultSet.getString(17),resultSet.getString(18),resultSet.getString(19)));
                    mvtoCarbon.getArticulo().setBaseDatos(new BaseDatos(resultSet.getString(310)));
                    mvtoCarbon.setCliente(new Cliente(resultSet.getString(21),resultSet.getString(22),resultSet.getString(23)));
                    mvtoCarbon.getCliente().setBaseDatos(new BaseDatos(resultSet.getString(311)));
                    mvtoCarbon.setTransportadora(new Transportadora(resultSet.getString(25),resultSet.getString(26),resultSet.getString(27),resultSet.getString(28),resultSet.getString(29)));
                    mvtoCarbon.getTransportadora().setBaseDatos(new BaseDatos(resultSet.getString(312)));
                    mvtoCarbon.setFechaRegistro(resultSet.getString(30));
                    mvtoCarbon.setNumero_orden(resultSet.getString(31));
                    mvtoCarbon.setDeposito(resultSet.getString(32));
                    mvtoCarbon.setConsecutivo(resultSet.getString(33));
                    mvtoCarbon.setPlaca(resultSet.getString(34));
                    mvtoCarbon.setPesoVacio(resultSet.getString(35));
                    mvtoCarbon.setPesoLleno(resultSet.getString(36));
                    mvtoCarbon.setPesoNeto(resultSet.getString(37));
                    mvtoCarbon.setFechaEntradaVehiculo(resultSet.getString(38));
                    mvtoCarbon.setFecha_SalidaVehiculo(resultSet.getString(39));    
                    mvtoCarbon.setFechaInicioDescargue(resultSet.getString(40));    
                    mvtoCarbon.setFechaFinDescargue(resultSet.getString(41));      
                        Usuario us = new Usuario();           
                        us.setCodigo(resultSet.getString(43));
                        //us.setClave(resultSet.getString(44));
                        us.setNombres(resultSet.getString(45));
                        us.setApellidos(resultSet.getString(46));
                        us.setPerfilUsuario(new Perfil(resultSet.getString(48),resultSet.getString(49),resultSet.getString(50)));
                        us.setCorreo(resultSet.getString(51));
                        us.setEstado(resultSet.getString(52));
                    mvtoCarbon.setUsuarioRegistroMovil(us);
                    mvtoCarbon.setObservacion(resultSet.getString(53));
                        EstadoMvtoCarbon estadMvtoCarbon = new EstadoMvtoCarbon();
                        estadMvtoCarbon.setCodigo(resultSet.getString(55));
                        estadMvtoCarbon.setDescripcion(resultSet.getString(56));
                        estadMvtoCarbon.setEstado(resultSet.getString(57));
                    mvtoCarbon.setEstadoMvtoCarbon(estadMvtoCarbon);
                    mvtoCarbon.setConexionPesoCcarga(resultSet.getString(58));
                    mvtoCarbon.setRegistroManual(resultSet.getString(59));
                        Usuario usRegManual = new Usuario();           
                        usRegManual.setCodigo(resultSet.getString(61));
                        //usRegManual.setClave(resultSet.getString(62));
                        usRegManual.setNombres(resultSet.getString(63));
                        usRegManual.setApellidos(resultSet.getString(64));
                        usRegManual.setPerfilUsuario(new Perfil(resultSet.getString(66),resultSet.getString(67),resultSet.getString(68)));
                        usRegManual.setCorreo(resultSet.getString(69));
                        usRegManual.setEstado(resultSet.getString(70));
                    mvtoCarbon.setUsuarioRegistraManual(usRegManual);
                    mvtoCarbon.setCantidadHorasDescargue(resultSet.getString(303));
                mvto_listEquipo.setMvtoCarbon(mvtoCarbon);
                    AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                    asignacionEquipo.setCodigo(resultSet.getString(72));
                        CentroOperacion co= new CentroOperacion();
                        co.setCodigo(Integer.parseInt(resultSet.getString(74)));
                        co.setDescripcion(resultSet.getString(75));
                        co.setEstado(resultSet.getString(76));
                    asignacionEquipo.setCentroOperacion(co);
                        SolicitudListadoEquipo solicitudListadoEquipo = new SolicitudListadoEquipo();
                        solicitudListadoEquipo.setCodigo(resultSet.getString(78));
                            SolicitudEquipo solicitudEquipo= new SolicitudEquipo();
                            solicitudEquipo.setCodigo(resultSet.getString(80));
                                CentroOperacion co_se= new CentroOperacion();
                                co_se.setCodigo(Integer.parseInt(resultSet.getString(82)));
                                co_se.setDescripcion(resultSet.getString(83));
                                co_se.setEstado(resultSet.getString(84));
                            solicitudEquipo.setCentroOperacion(co_se);
                            solicitudEquipo.setFechaSolicitud(resultSet.getString(85));
                                Usuario us_se = new Usuario();           
                                us_se.setCodigo(resultSet.getString(87));
                                //us_se.setClave(resultSet.getString(88));
                                us_se.setNombres(resultSet.getString(89));
                                us_se.setApellidos(resultSet.getString(90));
                                us_se.setPerfilUsuario(new Perfil(resultSet.getString(92),resultSet.getString(93),resultSet.getString(94)));
                                us_se.setCorreo(resultSet.getString(95));
                                us_se.setEstado(resultSet.getString(96));
                            solicitudEquipo.setUsuarioRealizaSolicitud(us_se);
                            solicitudEquipo.setFechaRegistro(resultSet.getString(97));
                                EstadoSolicitudEquipos estadoSolicitudEquipos = new EstadoSolicitudEquipos();
                                estadoSolicitudEquipos.setCodigo(resultSet.getString(99));
                                estadoSolicitudEquipos.setDescripcion(resultSet.getString(100));
                                estadoSolicitudEquipos.setEstado(resultSet.getString(101));
                            solicitudEquipo.setEstadoSolicitudEquipo(estadoSolicitudEquipos);
                            solicitudEquipo.setFechaConfirmacion(resultSet.getString(102));
                                Usuario us_se_confirm = new Usuario();           
                                us_se_confirm.setCodigo(resultSet.getString(104));
                                //us_se_confirm.setClave(resultSet.getString(105));
                                us_se_confirm.setNombres(resultSet.getString(106));
                                us_se_confirm.setApellidos(resultSet.getString(107));
                                us_se_confirm.setPerfilUsuario(new Perfil(resultSet.getString(109),resultSet.getString(110),resultSet.getString(111)));
                                us_se_confirm.setCorreo(resultSet.getString(112));
                                us_se_confirm.setEstado(resultSet.getString(113));
                            solicitudEquipo.setUsuarioConfirmacionSolicitud(us_se_confirm);
                                ConfirmacionSolicitudEquipos confirmacionSolicitudEquipos = new ConfirmacionSolicitudEquipos();
                                confirmacionSolicitudEquipos.setCodigo(resultSet.getString(115));
                                confirmacionSolicitudEquipos.setDescripcion(resultSet.getString(116));
                                confirmacionSolicitudEquipos.setEstado(resultSet.getString(117));
                            solicitudEquipo.setConfirmacionSolicitudEquipo(confirmacionSolicitudEquipos);
                        solicitudListadoEquipo.setSolicitudEquipo(solicitudEquipo);
                            TipoEquipo tipoEquipo = new TipoEquipo();
                            tipoEquipo.setCodigo(resultSet.getString(119));
                            tipoEquipo.setDescripcion(resultSet.getString(120));
                            tipoEquipo.setEstado(resultSet.getString(121));
                        solicitudListadoEquipo.setTipoEquipo(tipoEquipo);
                        solicitudListadoEquipo.setMarcaEquipo(resultSet.getString(122));
                        solicitudListadoEquipo.setModeloEquipo(resultSet.getString(123));
                        solicitudListadoEquipo.setCantidad(Integer.parseInt(resultSet.getString(124)));
                        solicitudListadoEquipo.setObservacacion(resultSet.getString(125));
                        solicitudListadoEquipo.setFechaHoraInicio(resultSet.getString(126));
                        solicitudListadoEquipo.setFechaHoraFin(resultSet.getString(127));  
                        solicitudListadoEquipo.setCantidadMinutos(resultSet.getInt(128));
                            LaborRealizada laborRealizada = new LaborRealizada();
                            laborRealizada.setCodigo(resultSet.getString(130));
                            laborRealizada.setDescripcion(resultSet.getString(131));
                            laborRealizada.setEstado(resultSet.getString(132));
                        solicitudListadoEquipo.setLaborRealizada(laborRealizada);
                            Motonave motonave = new Motonave();
                            motonave.setCodigo(resultSet.getString(134));
                            motonave.setDescripcion(resultSet.getString(135));
                            motonave.setEstado(resultSet.getString(136));
                            motonave.setBaseDatos(new BaseDatos( resultSet.getString(309)));
                        solicitudListadoEquipo.setMotonave(motonave);
                            CentroCostoSubCentro centroCostoSubCentro = new CentroCostoSubCentro();
                            centroCostoSubCentro.setCodigo(resultSet.getInt(140));
                            centroCostoSubCentro.setDescripcion(resultSet.getString(141));
                            centroCostoSubCentro.setEstado(resultSet.getString(142));
                            CentroCostoAuxiliar centroCostoAuxiliar = new CentroCostoAuxiliar();                          
                            centroCostoAuxiliar.setCodigo(resultSet.getInt(138));
                            centroCostoAuxiliar.setDescripcion(resultSet.getString(143));
                            centroCostoAuxiliar.setEstado(resultSet.getString(144));
                            centroCostoAuxiliar.setCentroCostoSubCentro(centroCostoSubCentro);
                        solicitudListadoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar);
                            Compañia compania = new Compañia();
                            compania.setCodigo(resultSet.getString(146));
                            compania.setDescripcion(resultSet.getString(147));
                            compania.setEstado(resultSet.getString(148));
                        solicitudListadoEquipo.setCompañia(compania);
                    asignacionEquipo.setSolicitudListadoEquipo(solicitudListadoEquipo);
                    asignacionEquipo.setFechaRegistro(resultSet.getString(149));
                    asignacionEquipo.setFechaHoraInicio(resultSet.getString(150));
                    asignacionEquipo.setFechaHoraFin(resultSet.getString(151));
                    asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(152));
                        Equipo equipo = new Equipo(); 
                        equipo.setCodigo(resultSet.getString(154));
                        equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(156),resultSet.getString(157),resultSet.getString(158)));
                        equipo.setCodigo_barra(resultSet.getString(159));
                        equipo.setReferencia(resultSet.getString(160));
                        equipo.setProducto(resultSet.getString(161));
                        equipo.setCapacidad(resultSet.getString(162));
                        equipo.setMarca(resultSet.getString(163));
                        equipo.setModelo(resultSet.getString(164));
                        equipo.setSerial(resultSet.getString(165));
                        equipo.setDescripcion(resultSet.getString(166));
                        equipo.setClasificador1(new ClasificadorEquipo(resultSet.getString(168),resultSet.getString(169),resultSet.getString(170)));
                        equipo.setClasificador2(new ClasificadorEquipo(resultSet.getString(172),resultSet.getString(173),resultSet.getString(174)));
                        equipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(176),resultSet.getString(177),resultSet.getString(178),resultSet.getString(179)));
                        equipo.setPertenenciaEquipo(new Pertenencia(resultSet.getString(181),resultSet.getString(182),resultSet.getString(183)));
                        equipo.setObservacion(resultSet.getString(184));
                        equipo.setEstado(resultSet.getString(185));
                        equipo.setActivoFijo_codigo(resultSet.getString(186));
                        equipo.setActivoFijo_referencia(resultSet.getString(187));
                        equipo.setActivoFijo_descripcion(resultSet.getString(188));
                    asignacionEquipo.setEquipo(equipo);
                    asignacionEquipo.setPertenencia(new Pertenencia(resultSet.getString(190),resultSet.getString(191),resultSet.getString(192)));
                    asignacionEquipo.setCantidadMinutosOperativo(resultSet.getString(193));
                    asignacionEquipo.setCantidadMinutosParada(resultSet.getString(194));
                    asignacionEquipo.setCantidadMinutosTotal(resultSet.getString(195));
                    asignacionEquipo.setEstado(resultSet.getString(196));
                mvto_listEquipo.setAsignacionEquipo(asignacionEquipo);
                    MvtoEquipo mvtoEquipo = new MvtoEquipo();
                    mvtoEquipo.setCodigo(resultSet.getString(198));
                    mvtoEquipo.setAsignacionEquipo(asignacionEquipo);
                    mvtoEquipo.setFechaRegistro(resultSet.getString(200));        
                    mvtoEquipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(202),resultSet.getString(203),resultSet.getString(204),resultSet.getString(205)));
                    mvtoEquipo.setNumeroOrden(resultSet.getString(206));        
                        CentroCostoSubCentro centroCostoSubCentro_mvtoEquipo = new CentroCostoSubCentro();
                            centroCostoSubCentro_mvtoEquipo.setCodigo(resultSet.getInt(210));
                            centroCostoSubCentro_mvtoEquipo.setDescripcion(resultSet.getString(211));
                            centroCostoSubCentro_mvtoEquipo.setEstado(resultSet.getString(212));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipo = new CentroCostoAuxiliar();                          
                        centroCostoAuxiliar_mvtoEquipo.setCodigo(resultSet.getInt(208));
                        centroCostoAuxiliar_mvtoEquipo.setDescripcion(resultSet.getString(213));
                        centroCostoAuxiliar_mvtoEquipo.setEstado(resultSet.getString(214));
                        centroCostoAuxiliar_mvtoEquipo.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipo);
                    mvtoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipo);
                        LaborRealizada laborRealizadaT = new LaborRealizada();
                        laborRealizadaT.setCodigo(resultSet.getString(216));
                        laborRealizadaT.setDescripcion(resultSet.getString(217));
                        laborRealizadaT.setEstado(resultSet.getString(218));
                    mvtoEquipo.setLaborRealizada(laborRealizadaT);
                    mvtoEquipo.setCliente(new Cliente(resultSet.getString(220),resultSet.getString(221),resultSet.getString(222)));
                    mvtoEquipo.getCliente().setBaseDatos(new BaseDatos( resultSet.getString(313)));
                    mvtoEquipo.setArticulo(new Articulo(resultSet.getString(224),resultSet.getString(225),resultSet.getString(226)));
                    mvtoEquipo.getArticulo().setBaseDatos(new BaseDatos( resultSet.getString(314)));
                    mvtoEquipo.setFechaHoraInicio(resultSet.getString(227));
                    mvtoEquipo.setFechaHoraFin(resultSet.getString(228));
                    mvtoEquipo.setTotalMinutos(resultSet.getString(229));
                    mvtoEquipo.setCostoTotalRecobroCliente(resultSet.getString(230));
                        Recobro recobro = new Recobro();
                        recobro.setCodigo(resultSet.getString(233));
                        recobro.setDescripcion(resultSet.getString(234));
                        recobro.setEstado(resultSet.getString(235));
                    mvtoEquipo.setRecobro(recobro);
                        ClienteRecobro ClienteRecobro = new ClienteRecobro();
                        ClienteRecobro.setCodigo(resultSet.getString(235));
                            Cliente cliente_recobro = new Cliente();
                            cliente_recobro.setCodigo(resultSet.getString(239));
                            cliente_recobro.setDescripcion(resultSet.getString(240));
                            cliente_recobro.setEstado(resultSet.getString(241));
                        ClienteRecobro.setCliente(cliente_recobro);
                            Usuario usuario_recobre = new Usuario();
                            usuario_recobre.setCodigo(resultSet.getString(243));
                            //usuario_recobre.setClave(resultSet.getString(244));
                            usuario_recobre.setNombres(resultSet.getString(245));
                            usuario_recobre.setApellidos(resultSet.getString(246));
                            usuario_recobre.setPerfilUsuario(new Perfil(resultSet.getString(248),resultSet.getString(249),resultSet.getString(250)));
                            usuario_recobre.setCorreo(resultSet.getString(251));
                            usuario_recobre.setEstado(resultSet.getString(252));
                        ClienteRecobro.setUsuario(usuario_recobre);
                        ClienteRecobro.setValorRecobro(resultSet.getString(253));
                        ClienteRecobro.setFechaRegistro(resultSet.getString(254));
                    mvtoEquipo.setClienteRecobro(ClienteRecobro);
                    mvtoEquipo.setCostoTotalRecobroCliente(resultSet.getString(255));
                        Usuario usuario_me_registra = new Usuario();
                        usuario_me_registra.setCodigo(resultSet.getString(257));
                        //usuario_me_registra.setClave(resultSet.getString(258));
                        usuario_me_registra.setNombres(resultSet.getString(259));
                        usuario_me_registra.setApellidos(resultSet.getString(260));
                        usuario_me_registra.setPerfilUsuario(new Perfil(resultSet.getString(262),resultSet.getString(263),resultSet.getString(264)));
                        usuario_me_registra.setCorreo(resultSet.getString(265));
                        usuario_me_registra.setEstado(resultSet.getString(266));
                    mvtoEquipo.setUsuarioQuieRegistra(usuario_me_registra);
                        Usuario usuario_me_autoriza = new Usuario();
                        usuario_me_autoriza.setCodigo(resultSet.getString(268));
                        //usuario_me_autoriza.setClave(resultSet.getString(269));
                        usuario_me_autoriza.setNombres(resultSet.getString(270));
                        usuario_me_autoriza.setApellidos(resultSet.getString(271));
                        usuario_me_autoriza.setPerfilUsuario(new Perfil(resultSet.getString(273),resultSet.getString(274),resultSet.getString(275)));
                        usuario_me_autoriza.setCorreo(resultSet.getString(276));
                        usuario_me_autoriza.setEstado(resultSet.getString(277));
                    mvtoEquipo.setUsuarioAutorizaRecobro(usuario_me_autoriza);
                        AutorizacionRecobro autorizacionRecobro = new AutorizacionRecobro();
                        autorizacionRecobro.setCodigo(resultSet.getString(279));
                        autorizacionRecobro.setDescripcion(resultSet.getString(280));
                        autorizacionRecobro.setEstado(resultSet.getString(281));
                    mvtoEquipo.setAutorizacionRecobro(autorizacionRecobro);
                    mvtoEquipo.setObservacionAutorizacion(resultSet.getString(282)); 
                    mvtoEquipo.setInactividad(resultSet.getString(283)); 
                        CausaInactividad causaInactividad = new CausaInactividad();
                        causaInactividad.setCodigo(resultSet.getString(285));
                        causaInactividad.setDescripcion(resultSet.getString(286));
                        causaInactividad.setEstado(resultSet.getString(287));
                    mvtoEquipo.setCausaInactividad(causaInactividad);
                        Usuario usuario_me_us_inactividad = new Usuario();
                        usuario_me_us_inactividad.setCodigo(resultSet.getString(289));
                        //usuario_me_us_inactividad.setClave(resultSet.getString(290));
                        usuario_me_us_inactividad.setNombres(resultSet.getString(291));
                        usuario_me_us_inactividad.setApellidos(resultSet.getString(292));
                        usuario_me_us_inactividad.setPerfilUsuario(new Perfil(resultSet.getString(294),resultSet.getString(295),resultSet.getString(296)));
                        usuario_me_us_inactividad.setCorreo(resultSet.getString(297));
                        usuario_me_us_inactividad.setEstado(resultSet.getString(298));
                    mvtoEquipo.setUsuarioInactividad(usuario_me_us_inactividad);
                        MotivoParada motivoParada= new MotivoParada();
                        motivoParada.setCodigo(resultSet.getString(300));
                        motivoParada.setDescripcion(resultSet.getString(301));
                        motivoParada.setEstado(resultSet.getString(302));
                    mvtoEquipo.setMotivoParada(motivoParada);
                    mvtoEquipo.setObservacionMvtoEquipo(resultSet.getString(303)); 
                    mvtoEquipo.setEstado(resultSet.getString(304)); 
                    mvtoEquipo.setDesdeCarbon(resultSet.getString(305)); 
                    mvtoEquipo.setTotalMinutos(resultSet.getString(307)); 
                mvto_listEquipo.setMvtoEquipo(mvtoEquipo);  
                mvto_listEquipo.setEstado(resultSet.getString(306));
                listadoObjetos.add(mvto_listEquipo);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los Movimientos de Carbon");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }
    public ArrayList<MvtoCarbon>                    InformeMvtoCarbon(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<MvtoCarbon> listadoObjetos = new ArrayList();
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            Statement stmt = conexion.createStatement(
                                      ResultSet.TYPE_SCROLL_INSENSITIVE,
                                   
            ResultSet.CONCUR_READ_ONLY);
            
            ResultSet resultSet = stmt.executeQuery( "SELECT \n" +
                                                                    "[mcle_cdgo] --1\n" +
                                                                    "      ,[mcle_mvto_carbon_cdgo] --2\n" +
                                                                    "		  ,[mc_cdgo] --3\n" +
                                                                    "		  ,[mc_cntro_oper_cdgo] --4\n" +
                                                                    "				--Centro Operacion\n" +
                                                                    "				,[co_cdgo] --5\n" +
                                                                    "				,[co_desc] --6\n" +
                                                                    "				,CASE [co_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [co_estad] --7\n" +
                                                                    "		  ,[mc_cntro_cost_auxiliar_cdgo] --8\n" +
                                                                    "				--Centro Costo Auxiliar\n" +
                                                                    "				,mc_cntro_cost_auxiliar.[cca_cdgo] --9\n" +
                                                                    "				,mc_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo] --10\n" +
                                                                    "						--Subcentro de Costo\n" +
                                                                    "						,mc_cntro_cost_subcentro.[ccs_cdgo] --11\n" +
                                                                    "						,mc_cntro_cost_subcentro.[ccs_desc]  --12\n" +
                                                                    "						,CASE mc_cntro_cost_subcentro.[ccs_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN NULL ELSE NULL END AS [ccs_estad] --13\n" +
                                                                    "				,mc_cntro_cost_auxiliar.[cca_desc] --14\n" +
                                                                    "				,CASE mc_cntro_cost_auxiliar.[cca_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN NULL ELSE NULL END AS [cca_estad] --15\n" +
                                                                    "		  ,[mc_articulo_cdgo] --16\n" +
                                                                    "				--Articulo\n" +
                                                                    "				,[ar_cdgo] --17\n" +
                                                                    "				,[ar_desc] --18\n" +
                                                                    "				,CASE [ar_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ar_estad] --19\n" +
                                                                    "		  ,[mc_cliente_cdgo] --20\n" +
                                                                    "				--Cliente\n" +
                                                                    "				,mc_cliente.[cl_cdgo] --21\n" +
                                                                    "				,mc_cliente.[cl_desc] --22\n" +
                                                                    "				,CASE mc_cliente.[cl_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cl_estad] --23\n" +
                                                                    "		  ,[mc_transprtdora_cdgo] --24\n" +
                                                                    "				--Transportadora\n" +
                                                                    "				,[tr_cdgo] --25\n" +
                                                                    "				,[tr_nit] --26\n" +
                                                                    "				,[tr_desc] --27\n" +
                                                                    "				,[tr_observ] --28\n" +
                                                                    "				,CASE [tr_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [tr_estad] --29\n" +
                                                                    "		  ,[mc_fecha] --30\n" +
                                                                    "		  ,[mc_num_orden] --31\n" +
                                                                    "		  ,[mc_deposito] --32\n" +
                                                                    "		  ,[mc_consecutivo_tqute] --33\n" +
                                                                    "		  ,[mc_placa_vehiculo] --34\n" +
                                                                    "		  ,[mc_peso_vacio] --35\n" +
                                                                    "		  ,[mc_peso_lleno] --36\n" +
                                                                    "		  ,[mc_peso_neto] --37\n" +
                                                                    "		  ,[mc_fecha_entrad] --38\n" +
                                                                    "		  ,[mc_fecha_salid] --39\n" +
                                                                    "		  ,[mc_fecha_inicio_descargue] --40\n" +
                                                                    "		  ,[mc_fecha_fin_descargue] --41\n" +
                                                                    "		  ,[mc_usuario_cdgo] --42\n" +
                                                                    "				--Usuario Quien Registra desde App Movil\n" +
                                                                    "				,user_registra.[us_cdgo] --43\n" +
                                                                    "				,user_registra.[us_clave] --44\n" +
                                                                    "				,user_registra.[us_nombres] --45\n" +
                                                                    "				,user_registra.[us_apellidos] --46\n" +
                                                                    "				,user_registra.[us_perfil_cdgo] --47\n" +
                                                                    "					--Perfil Usuario Quien Registra\n" +
                                                                    "					,prf_registra.[prf_cdgo] --48\n" +
                                                                    "					,prf_registra.[prf_desc] --49\n" +
                                                                    "					,CASE prf_registra.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --50\n" +
                                                                    "				,user_registra.[us_correo] --51\n" +
                                                                    "				,CASE user_registra.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --52\n" +
                                                                    "		  ,[mc_observ] --53\n" +
                                                                    "		  ,[mc_estad_mvto_carbon_cdgo] --54\n" +
                                                                    "				--Estado MvtoCarbon\n" +
                                                                    "				,[emc_cdgo] --55\n" +
                                                                    "				,[emc_desc] --56\n" +
                                                                    "				,CASE [emc_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [emc_estad] --57\n" +
                                                                    "		  ,CASE [mc_conexion_peso_ccarga] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [mc_conexion_peso_ccarga] --58\n" +
                                                                    "		  ,[mc_registro_manual] --59\n" +
                                                                    "		  ,[mc_usuario_registro_manual_cdgo] --60\n" +
                                                                    "				--Usuario Quien Registra Manual\n" +
                                                                    "				,user_registro_manual.[us_cdgo] --61\n" +
                                                                    "				,user_registro_manual.[us_clave] --62\n" +
                                                                    "				,user_registro_manual.[us_nombres] --63\n" +
                                                                    "				,user_registro_manual.[us_apellidos] --64\n" +
                                                                    "				,user_registro_manual.[us_perfil_cdgo] --65\n" +
                                                                    "					--Perfil Usuario Quien Registra Manual\n" +
                                                                    "					,prf_registra_manual.[prf_cdgo] --66\n" +
                                                                    "					,prf_registra_manual.[prf_desc] --67\n" +
                                                                    "					,CASE prf_registra_manual.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --68\n" +
                                                                    "				,user_registro_manual.[us_correo] --69\n" +
                                                                    "				,CASE user_registro_manual.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --70\n" +
                                                                    "		,[mcle_asignacion_equipo_cdgo] --71\n" +
                                                                    "				,[ae_cdgo] --72\n" +
                                                                    "				,ae_cntro_oper_cdgo --73\n" +
                                                                    "					--Centro Operacion\n" +
                                                                    "					,ae_co_cdgo --74\n" +
                                                                    "					,ae_co_desc --75\n" +
                                                                    "					,CASE ae_co_estado WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_co_estado --76\n" +
                                                                    "				,ae_solicitud_listado_equipo_cdgo --77\n" +
                                                                    "					-- Solicitud Listado Equipo\n" +
                                                                    "					,ae_sle_cdgo --78\n" +
                                                                    "					,ae_sle_solicitud_equipo_cdgo --79\n" +
                                                                    "							--Solicitud Equipo\n" +
                                                                    "							,ae_sle_se_cdgo --80\n" +
                                                                    "							,ae_sle_se_cntro_oper_cdgo --81\n" +
                                                                    "								--CentroOperación SolicitudEquipo\n" +
                                                                    "								,ae_sle_se_co_cdgo --82\n" +
                                                                    "								,ae_sle_se_co_desc --83\n" +
                                                                    "								,CASE ae_sle_se_co_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_co_estad --84\n" +
                                                                    "							,ae_sle_se_fecha --85\n" +
                                                                    "							,ae_sle_se_usuario_realiza_cdgo --86\n" +
                                                                    "								--Usuario SolicitudEquipo\n" +
                                                                    "								,ae_sle_se_us_registra_cdgo --87\n" +
                                                                    "								,ae_sle_se_us_registra_clave --88\n" +
                                                                    "								,ae_sle_se_us_registra_nombres --89\n" +
                                                                    "								,ae_sle_se_us_registra_apellidos --90\n" +
                                                                    "								,ae_sle_se_us_registra_perfil_cdgo --91\n" +
                                                                    "										--Perfil Usuario Quien Registra Manual\n" +
                                                                    "										,ae_sle_se_prf_us_registra_cdgo --92\n" +
                                                                    "										,ae_sle_se_prf_us_registra_desc --93\n" +
                                                                    "										,CASE ae_sle_se_prf_us_registra_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_prf_us_registra_estad --94\n" +
                                                                    "								,ae_sle_se_us_registra_correo --95\n" +
                                                                    "								,CASE ae_sle_se_us_registra_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_us_registra_estad --96\n" +
                                                                    "							,ae_sle_se_fecha_registro --97\n" +
                                                                    "							,ae_sle_se_estado_solicitud_equipo_cdgo --98\n" +
                                                                    "								--Estado de la solicitud\n" +
                                                                    "								,ae_sle_se_ese_cdgo --99\n" +
                                                                    "								,ae_sle_se_ese_desc --100\n" +
                                                                    "								,CASE ae_sle_se_ese_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_ese_estad --101\n" +
                                                                    "							,ae_sle_se_fecha_confirmacion --102\n" +
                                                                    "							,ae_se_usuario_confirma_cdgo --103\n" +
                                                                    "								--Usuario SolicitudEquipo\n" +
                                                                    "								,ae_sle_se_us_confirma_cdgo --104\n" +
                                                                    "								,ae_sle_se_us_confirma_clave --105\n" +
                                                                    "								,ae_sle_se_us_confirma_nombres --106\n" +
                                                                    "								,ae_sle_se_us_confirma_apellidos --107\n" +
                                                                    "								,ae_sle_se_us_confirma_perfil_cdgo --108\n" +
                                                                    "										--Perfil Usuario Quien Registra Manual\n" +
                                                                    "										,ae_sle_se_prf_us_confirma_cdgo --109\n" +
                                                                    "										,ae_sle_se_prf_us_confirma_desc --110\n" +
                                                                    "										,CASE ae_sle_se_prf_us_confirma_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_prf_us_confirma_estad --111\n" +
                                                                    "								,ae_sle_se_us_confirma_correo --112\n" +
                                                                    "								,CASE ae_sle_se_us_confirma_estado WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_us_confirma_estado --113\n" +
                                                                    "							,ae_sle_se_confirmacion_solicitud_equipo_cdgo --114\n" +
                                                                    "								--Confirmacion solicitudEquipo\n" +
                                                                    "								,ae_sle_se_cse_cdgo --115\n" +
                                                                    "								,ae_sle_se_ces_desc --116\n" +
                                                                    "								,CASE ae_sle_se_ces_estado WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_ces_estado --117\n" +
                                                                    "					,ae_sle_tipo_equipo_cdgo --118\n" +
                                                                    "						--Tipo de Equipo\n" +
                                                                    "						,ae_sle_te_cdgo --119\n" +
                                                                    "						,ae_sle_te_desc --120\n" +
                                                                    "						,CASE ae_sle_te_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_te_estad --121\n" +
                                                                    "					,ae_sle_marca_equipo --122\n" +
                                                                    "					,ae_sle_modelo_equipo --123\n" +
                                                                    "					,ae_sle_cant_equip --124\n" +
                                                                    "					,ae_sle_observ --125\n" +
                                                                    "					,ae_sle_fecha_hora_inicio --126\n" +
                                                                    "					,ae_sle_fecha_hora_fin --127\n" +
                                                                    "					,ae_sle_cant_minutos --128\n" +
                                                                    "					,ae_sle_labor_realizada_cdgo --129\n" +
                                                                    "					-- Labor Realizada\n" +
                                                                    "							,ae_sle_lr_cdgo --130\n" +
                                                                    "							,ae_sle_lr_desc --131\n" +
                                                                    "							,CASE ae_sle_lr_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_lr_estad --132\n" +
                                                                    "					,ae_sle_sle_motonave_cdgo --133\n" +
                                                                    "					--Motonave\n" +
                                                                    "							,ae_sle_mn_cdgo --134\n" +
                                                                    "							,ae_sle_mn_desc --135\n" +
                                                                    "							,CASE ae_sle_mn_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_mn_estad --136\n" +
                                                                    "					,ae_sle_cntro_cost_auxiliar_cdgo --137\n" +
                                                                    "						--Centro Costo Auxiliar\n" +
                                                                    "						,ae_sle_cca_cdgo --138\n" +
                                                                    "						,ae_sle_cca_cntro_cost_subcentro_cdgo --139\n" +
                                                                    "							-- SubCentro de Costo\n" +
                                                                    "							,ae_sle_ccs_cdgo --140\n" +
                                                                    "							,ae_sle_ccs_desc --141\n" +
                                                                    "							,CASE ae_sle_ccs_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_ccs_estad --142\n" +
                                                                    "						,ae_sle_cca_desc --143\n" +
                                                                    "						,CASE ae_sle_cca_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_cca_estad --144\n" +
                                                                    "					,ae_sle_compania_cdgo --145\n" +
                                                                    "						--Compañia\n" +
                                                                    "						,ae_sle_cp_cdgo --146\n" +
                                                                    "						,ae_sle_cp_desc --147\n" +
                                                                    "						,CASE ae_sle_cp_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_cp_estad --148\n" +
                                                                    "				,ae_fecha_registro --149\n" +
                                                                    "				,ae_fecha_hora_inicio --150\n" +
                                                                    "				,ae_fecha_hora_fin --151\n" +
                                                                    "				,ae_cant_minutos --152\n" +
                                                                    "				,ae_equipo_cdgo --153\n" +
                                                                    "					--Equipo\n" +
                                                                    "					,ae_eq_cdgo --154\n" +
                                                                    "					,ae_eq_tipo_equipo_cdgo --155\n" +
                                                                    "						--Tipo Equipo\n" +
                                                                    "						,ae_eq_te_cdgo --156\n" +
                                                                    "						,ae_eq_te_desc --157\n" +
                                                                    "						,CASE ae_eq_te_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_te_estad --158\n" +
                                                                    "					,ae_eq_codigo_barra --159\n" +
                                                                    "					,ae_eq_referencia --160\n" +
                                                                    "					,ae_eq_producto --161\n" +
                                                                    "					,ae_eq_capacidad --162\n" +
                                                                    "					,ae_eq_marca --163\n" +
                                                                    "					,ae_eq_modelo --164\n" +
                                                                    "					,ae_eq_serial --165\n" +
                                                                    "					,ae_eq_desc --166\n" +
                                                                    "					,ae_eq_clasificador1_cdgo --167\n" +
                                                                    "						-- Clasificador 1\n" +
                                                                    "						,ae_eq_ce1_cdgo --168\n" +
                                                                    "						,ae_eq_ce1_desc --169\n" +
                                                                    "						,CASE ae_eq_ce1_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_ce1_estad --170\n" +
                                                                    "					,ae_eq_clasificador2_cdgo --171\n" +
                                                                    "						-- Clasificador 2\n" +
                                                                    "						,ae_eq_ce2_cdgo --172\n" +
                                                                    "						,ae_eq_ce2_desc --173\n" +
                                                                    "						,CASE ae_eq_ce2_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_ce2_estad --174\n" +
                                                                    "					,ae_eq_proveedor_equipo_cdgo --175\n" +
                                                                    "						--Proveedor Equipo\n" +
                                                                    "						,ae_eq_pe_cdgo --176\n" +
                                                                    "						,ae_eq_pe_nit --177\n" +
                                                                    "						,ae_eq_pe_desc --178\n" +
                                                                    "						,CASE ae_eq_pe_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_pe_estad --179\n" +
                                                                    "					,ae_eq_equipo_pertenencia_cdgo --180\n" +
                                                                    "						-- Equipo Pertenencia\n" +
                                                                    "						,ae_eq_ep_cdgo --181\n" +
                                                                    "						,ae_eq_ep_desc --182\n" +
                                                                    "						,CASE ae_eq_ep_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_ep_estad --183\n" +
                                                                    "					,ae_eq_eq_observ --184\n" +
                                                                    "					,CASE ae_eq_eq_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_eq_estad --185\n" +
                                                                    "					,ae_eq_actvo_fijo_id --186\n" +
                                                                    "					,ae_eq_actvo_fijo_referencia --187\n" +
                                                                    "					,ae_eq_actvo_fijo_desc --188\n" +
                                                                    "				,ae_equipo_pertenencia_cdgo --189\n" +
                                                                    "				-- Equipo Pertenencia\n" +
                                                                    "						,ae_ep_cdgo --190\n" +
                                                                    "						,ae_ep_desc --191\n" +
                                                                    "						,CASE ae_ep_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_ep_estad --192\n" +
                                                                    "				,ae_cant_minutos_operativo --193\n" +
                                                                    "				,ae_cant_minutos_parada --194\n" +
                                                                    "				,ae_cant_minutos_total --195\n" +
                                                                    "				,CASE ae_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_estad --196\n" +
                                                                    "		,[mcle_mvto_equipo_cdgo] --197\n" +
                                                                    "				--Movimiento Equipo\n" +
                                                                    "				,[me_cdgo] --198\n" +
                                                                    "				,[me_asignacion_equipo_cdgo] --199\n" +
                                                                    "				,[me_fecha] --200\n" +
                                                                    "				,[me_proveedor_equipo_cdgo] --201\n" +
                                                                    "					--Proveedor Equipo\n" +
                                                                    "					,[pe_cdgo] AS me_pe_cdgo --202\n" +
                                                                    "					,[pe_nit] AS me_pe_nit --203\n" +
                                                                    "					,[pe_desc] AS me_pe_desc --204\n" +
                                                                    "					,CASE [pe_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS me_pe_estad --205\n" +
                                                                    "				,[me_num_orden] --206\n" +
                                                                    "				,[me_cntro_cost_auxiliar_cdgo] --207\n" +
                                                                    "					-- Centro Costo Auxiliar\n" +
                                                                    "					,me_cntro_cost_auxiliar.[cca_cdgo] AS me_cca_cdgo --208\n" +
                                                                    "					,me_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo] AS me_cca_cntro_cost_subcentro_cdgo --209\n" +
                                                                    "						--Centro Costo Subcentro\n" +
                                                                    "						,me_cntro_cost_subcentro.[ccs_cdgo] --210\n" +
                                                                    "						,me_cntro_cost_subcentro.[ccs_desc] --211\n" +
                                                                    "						,CASE me_cntro_cost_subcentro.[ccs_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ccs_estad] --212\n" +
                                                                    "					,me_cntro_cost_auxiliar.[cca_desc] AS me_cca_desc --213\n" +
                                                                    "					,CASE me_cntro_cost_auxiliar.[cca_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cca_estad] --214\n" +
                                                                    "				,[me_labor_realizada_cdgo] --215\n" +
                                                                    "					--Labor Realizada\n" +
                                                                    "					,[lr_cdgo]  --216\n" +
                                                                    "					,[lr_desc] --217\n" +
                                                                    "					,CASE [lr_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [lr_estad] --218\n" +
                                                                    "				,[me_cliente_cdgo] --219\n" +
                                                                    "					--Cliente \n" +
                                                                    "					,me_cliente.[cl_cdgo] --220\n" +
                                                                    "					,me_cliente.[cl_desc] --221\n" +
                                                                    "					,CASE me_cliente.[cl_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cl_estad] --222\n" +
                                                                    "				,[me_articulo_cdgo] --223\n" +
                                                                    "					--Producto\n" +
                                                                    "					,[ar_cdgo] --224\n" +
                                                                    "					,[ar_desc] --225\n" +
                                                                    "					,CASE [ar_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ar_estad] --226\n" +
                                                                    "				,[me_fecha_hora_inicio] --227\n" +
                                                                    "				,[me_fecha_hora_fin] --228\n" +
                                                                    "				,[me_total_minutos] --229\n" +
                                                                    "				,[me_valor_hora] --230\n" +
                                                                    "				,[me_costo_total] --231\n" +
                                                                    "				,[me_recobro_cdgo] --232\n" +
                                                                    "					--Recobro\n" +
                                                                    "					,[rc_cdgo] --233\n" +
                                                                    "					,[rc_desc] --234\n" +
                                                                    "					,CASE [rc_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [rc_estad] --235\n" +
                                                                    "				,[me_cliente_recobro_cdgo] --236\n" +
                                                                    "					--Cliente Recobro\n" +
                                                                    "					,[clr_cdgo] --237\n" +
                                                                    "					,[clr_cliente_cdgo] --238\n" +
                                                                    "						--Cliente\n" +
                                                                    "						,me_clr_cliente.[cl_cdgo] --239\n" +
                                                                    "						,me_clr_cliente.[cl_desc] --240\n" +
                                                                    "						,CASE me_clr_cliente.[cl_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cl_estad] --241\n" +
                                                                    "					,[clr_usuario_cdgo] --242\n" +
                                                                    "						--Usuario Quien Registra Recobro\n" +
                                                                    "						,me_clr_usuario.[us_cdgo] --243\n" +
                                                                    "						,me_clr_usuario.[us_clave] --244\n" +
                                                                    "						,me_clr_usuario.[us_nombres] --245\n" +
                                                                    "						,me_clr_usuario.[us_apellidos] --246\n" +
                                                                    "						,me_clr_usuario.[us_perfil_cdgo] --247\n" +
                                                                    "							--Perfil Usuario Registra recobro\n" +
                                                                    "							,me_clr_us_perfil.[prf_cdgo] --248\n" +
                                                                    "							,me_clr_us_perfil.[prf_desc] --249\n" +
                                                                    "							,CASE me_clr_us_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --250\n" +
                                                                    "						,me_clr_usuario.[us_correo] --251\n" +
                                                                    "						,CASE me_clr_usuario.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --252\n" +
                                                                    "					,[clr_valor_recobro] --253\n" +
                                                                    "					,[clr_fecha_registro] --254\n" +
                                                                    "				,[me_costo_total_recobro_cliente] --255\n" +
                                                                    "				,[me_usuario_registro_cdgo] --256\n" +
                                                                    "					--Usuario Quien Registra Equipo\n" +
                                                                    "					,me_us_registro.[us_cdgo] --257\n" +
                                                                    "					,me_us_registro.[us_clave] --258\n" +
                                                                    "					,me_us_registro.[us_nombres] --259\n" +
                                                                    "					,me_us_registro.[us_apellidos] --260\n" +
                                                                    "					,me_us_registro.[us_perfil_cdgo] --261\n" +
                                                                    "						--Perfil de Usuario Quien Registra Equipo\n" +
                                                                    "						,me_us_regist_perfil.[prf_cdgo] --262\n" +
                                                                    "						,me_us_regist_perfil.[prf_desc] --263\n" +
                                                                    "						,CASE me_us_regist_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --264\n" +
                                                                    "					,me_us_registro.[us_correo] --265\n" +
                                                                    "					,CASE me_us_registro.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --266\n" +
                                                                    "				,[me_usuario_autorizacion_cdgo] --267\n" +
                                                                    "					,me_us_autorizacion.[us_cdgo] --268\n" +
                                                                    "					,me_us_autorizacion.[us_clave] --269\n" +
                                                                    "					,me_us_autorizacion.[us_nombres] --270\n" +
                                                                    "					,me_us_autorizacion.[us_apellidos] --271\n" +
                                                                    "					,me_us_autorizacion.[us_perfil_cdgo] --272\n" +
                                                                    "						,me_us_autoriza_perfil.[prf_cdgo] --273\n" +
                                                                    "						,me_us_autoriza_perfil.[prf_desc] --274\n" +
                                                                    "						,CASE me_us_autoriza_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --275\n" +
                                                                    "					,me_us_autorizacion.[us_correo] --276\n" +
                                                                    "					,CASE me_us_autorizacion.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --277\n" +
                                                                    "				,[me_autorizacion_recobro_cdgo] --278\n" +
                                                                    "					,[are_cdgo] --279\n" +
                                                                    "					,[are_desc] --280\n" +
                                                                    "					,CASE [are_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [are_estad] --281\n" +
                                                                    "				,[me_observ_autorizacion] --282\n" +
                                                                    "				,[me_inactividad] --283\n" +
                                                                    "				,[me_causa_inactividad_cdgo] --284\n" +
                                                                    "					,[ci_cdgo] --285\n" +
                                                                    "					,[ci_desc] --286\n" +
                                                                    "					,CASE [ci_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ci_estad] --287\n" +
                                                                    "				,[me_usuario_inactividad_cdgo] --288\n" +
                                                                    "					,me_us_inactividad.[us_cdgo] --289\n" +
                                                                    "					,me_us_inactividad.[us_clave] --290\n" +
                                                                    "					,me_us_inactividad.[us_nombres] --291\n" +
                                                                    "					,me_us_inactividad.[us_apellidos] --292\n" +
                                                                    "					,me_us_inactividad.[us_perfil_cdgo] --293\n" +
                                                                    "						,me_us_inactvdad_perfil.[prf_cdgo] --294\n" +
                                                                    "						,me_us_inactvdad_perfil.[prf_desc] --295\n" +
                                                                    "						,CASE me_us_inactvdad_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --296\n" +
                                                                    "					,me_us_inactividad.[us_correo] --297\n" +
                                                                    "					,CASE me_us_inactividad.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad]	 --298\n" +
                                                                    "                           ,[me_motivo_parada_cdgo]--299\n" +
                                                                    "                                       ,[mpa_cdgo]--300\n" +
                                                                    "                                       ,[mpa_desc]--301\n" +
                                                                    "                                       ,[mpa_estad]--302\n" +
                                                                    "                         ,[me_observ] --303\n" +
                                                                    "				,CASE [me_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_estado]	 --304\n" +
                                                                    "				,CASE [me_desde_mvto_carbon] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [me_desde_mvto_carbon]	 --305\n" +
                                                                    "		,CASE [mcle_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [mcle_estado]	 --306\n" +
                                                                    "            ,Tiempo_Vehiculo_Descargue=(SELECT (DATEDIFF (MINUTE,  [mc_fecha_inicio_descargue], [mc_fecha_fin_descargue])))--307\n" +
                                                                    "           ,Tiempo_Equipo_Descargue=(SELECT (DATEDIFF (MINUTE,  [me_fecha_hora_inicio], [me_fecha_hora_fin]))) --308\n" +
                                                                    "      ,[ae_sle_mn_base_datos_cdgo]--309\n" +
                                                                    "      ,mc_articulo_base_datos.[bd_cdgo]   --310 \n" +
                                                                    "      ,mc_cliente_base_datos.[bd_cdgo]   --311 \n" +
                                                                    "      ,mc_transprtdora_base_datos.[bd_cdgo]   --312 \n" +
                                                                    "      ,me_cliente_base_datos.[bd_cdgo]   --313 \n" +
                                                                    "      ,me_articulo_base_datos.[bd_cdgo]   --314 \n" +
                                                                    "  FROM ["+DB+"].[dbo].[mvto_carbon]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [mc_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] mc_cntro_cost_auxiliar ON [mc_cntro_cost_auxiliar_cdgo]=mc_cntro_cost_auxiliar.[cca_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] mc_cntro_cost_subcentro ON [cca_cntro_cost_subcentro_cdgo]= mc_cntro_cost_subcentro.[ccs_cdgo]\n" +
                                                                    //"	LEFT JOIN ["+DB+"].[dbo].[articulo] ON [mc_articulo_cdgo]=[ar_cdgo]\n" +
                                                                            "		LEFT JOIN ["+DB+"].[dbo].[articulo] mc_articulo ON [mc_articulo_cdgo]=mc_articulo.[ar_cdgo] AND mc_articulo.[ar_base_datos_cdgo]=[mc_articulo_base_datos_cdgo]\n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_articulo_base_datos ON mc_articulo.[ar_base_datos_cdgo]=mc_articulo_base_datos.[bd_cdgo] \n"+
                                                                    //"	LEFT JOIN ["+DB+"].[dbo].[cliente] mc_cliente ON [mc_cliente_cdgo]=mc_cliente.[cl_cdgo]\n" +
        "		LEFT JOIN ["+DB+"].[dbo].[cliente] mc_cliente ON [mc_cliente_cdgo]=mc_cliente.[cl_cdgo] AND mc_cliente.[cl_base_datos_cdgo]=[mc_cliente_base_datos_cdgo]\n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cliente_base_datos ON mc_cliente.[cl_base_datos_cdgo]=mc_cliente_base_datos.[bd_cdgo] \n"+
                                                                    //"	LEFT JOIN ["+DB+"].[dbo].[transprtdora] ON [mc_transprtdora_cdgo]=[tr_cdgo]\n" +
                                                                            "		LEFT JOIN ["+DB+"].[dbo].[transprtdora] ON [mc_transprtdora_cdgo]=[tr_cdgo] AND [tr_base_datos_cdgo]=[mc_transprtdora_base_datos_cdgo] \n" +
"               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_transprtdora_base_datos ON [tr_base_datos_cdgo]=mc_transprtdora_base_datos.[bd_cdgo] \n"+  
                                                                    "	INNER JOIN ["+DB+"].[dbo].[usuario] user_registra ON [mc_usuario_cdgo] = user_registra.[us_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[perfil] prf_registra ON user_registra.[us_perfil_cdgo]=prf_registra.[prf_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[estad_mvto_carbon] ON [mc_estad_mvto_carbon_cdgo]=[emc_cdgo]\n" +
                                                                    "	LEFT JOIN ["+DB+"].[dbo].[usuario] user_registro_manual ON [mc_usuario_registro_manual_cdgo] = user_registro_manual.[us_cdgo]\n" +
                                                                    "	LEFT JOIN ["+DB+"].[dbo].[perfil] prf_registra_manual ON user_registro_manual.[us_perfil_cdgo]=prf_registra_manual.[prf_cdgo]\n" +
                                                                    "	LEFT JOIN ["+DB+"].[dbo].[mvto_carbon_listado_equipo] ON [mcle_mvto_carbon_cdgo]=[mc_cdgo]\n" +
                                                                    "	INNER JOIN (SELECT [ae_cdgo] AS ae_cdgo\n" +
                                                                    "					  ,[ae_cntro_oper_cdgo] AS ae_cntro_oper_cdgo\n" +
                                                                    "							--Centro Operacion\n" +
                                                                    "							,ae_cntro_oper.[co_cdgo] AS ae_co_cdgo\n" +
                                                                    "							,ae_cntro_oper.[co_desc] AS ae_co_desc\n" +
                                                                    "							,ae_cntro_oper.[co_estad] AS ae_co_estado\n" +
                                                                    "					  ,[ae_solicitud_listado_equipo_cdgo] AS ae_solicitud_listado_equipo_cdgo\n" +
                                                                    "							-- Solicitud Listado Equipo\n" +
                                                                    "							,[sle_cdgo] AS ae_sle_cdgo\n" +
                                                                    "							,[sle_solicitud_equipo_cdgo] AS ae_sle_solicitud_equipo_cdgo\n" +
                                                                    "								  --Solicitud Equipo\n" +
                                                                    "								  ,[se_cdgo] AS ae_sle_se_cdgo\n" +
                                                                    "								  ,[se_cntro_oper_cdgo] AS ae_sle_se_cntro_oper_cdgo\n" +
                                                                    "										--CentroOperación SolicitudEquipo\n" +
                                                                    "										,se_cntro_oper.[co_cdgo] AS ae_sle_se_co_cdgo\n" +
                                                                    "										,se_cntro_oper.[co_desc] AS ae_sle_se_co_desc\n" +
                                                                    "										,se_cntro_oper.[co_estad] AS ae_sle_se_co_estad\n" +
                                                                    "								  ,[se_fecha] AS ae_sle_se_fecha\n" +
                                                                    "								  ,[se_usuario_realiza_cdgo] AS ae_sle_se_usuario_realiza_cdgo\n" +
                                                                    "										--Usuario SolicitudEquipo\n" +
                                                                    "										,se_usuario_realiza.[us_cdgo] AS ae_sle_se_us_registra_cdgo\n" +
                                                                    "										,se_usuario_realiza.[us_clave] AS ae_sle_se_us_registra_clave\n" +
                                                                    "										,se_usuario_realiza.[us_nombres] AS ae_sle_se_us_registra_nombres\n" +
                                                                    "										,se_usuario_realiza.[us_apellidos] AS ae_sle_se_us_registra_apellidos\n" +
                                                                    "										,se_usuario_realiza.[us_perfil_cdgo] AS ae_sle_se_us_registra_perfil_cdgo\n" +
                                                                    "											--Perfil Usuario Quien Registra Manual\n" +
                                                                    "											,ae_prf_registra.[prf_cdgo] AS ae_sle_se_prf_us_registra_cdgo\n" +
                                                                    "											,ae_prf_registra.[prf_desc] AS ae_sle_se_prf_us_registra_desc\n" +
                                                                    "											,ae_prf_registra.[prf_estad] AS ae_sle_se_prf_us_registra_estad\n" +
                                                                    "										,se_usuario_realiza.[us_correo] AS ae_sle_se_us_registra_correo\n" +
                                                                    "										,se_usuario_realiza.[us_estad] AS ae_sle_se_us_registra_estad\n" +
                                                                    "								  ,[se_fecha_registro] AS ae_sle_se_fecha_registro\n" +
                                                                    "								  ,[se_estado_solicitud_equipo_cdgo] AS ae_sle_se_estado_solicitud_equipo_cdgo\n" +
                                                                    "										--Estado de la solicitud\n" +
                                                                    "										,[ese_cdgo] AS ae_sle_se_ese_cdgo\n" +
                                                                    "										,[ese_desc] AS ae_sle_se_ese_desc\n" +
                                                                    "										,[ese_estad] AS ae_sle_se_ese_estad\n" +
                                                                    "								  ,[se_fecha_confirmacion] AS ae_sle_se_fecha_confirmacion\n" +
                                                                    "								  ,[se_usuario_confirma_cdgo] AS ae_se_usuario_confirma_cdgo\n" +
                                                                    "										--Usuario SolicitudEquipo\n" +
                                                                    "										,se_usuario_confirma.[us_cdgo] AS ae_sle_se_us_confirma_cdgo\n" +
                                                                    "										,se_usuario_confirma.[us_clave] AS ae_sle_se_us_confirma_clave\n" +
                                                                    "										,se_usuario_confirma.[us_nombres] AS ae_sle_se_us_confirma_nombres\n" +
                                                                    "										,se_usuario_confirma.[us_apellidos] AS ae_sle_se_us_confirma_apellidos\n" +
                                                                    "										,se_usuario_confirma.[us_perfil_cdgo] AS ae_sle_se_us_confirma_perfil_cdgo\n" +
                                                                    "											--Perfil Usuario Quien Registra Manual\n" +
                                                                    "											,ae_prf_registra_confirma.[prf_cdgo] AS ae_sle_se_prf_us_confirma_cdgo\n" +
                                                                    "											,ae_prf_registra_confirma.[prf_desc] AS ae_sle_se_prf_us_confirma_desc\n" +
                                                                    "											,ae_prf_registra_confirma.[prf_estad] AS ae_sle_se_prf_us_confirma_estad\n" +
                                                                    "										,se_usuario_confirma.[us_correo] AS ae_sle_se_us_confirma_correo\n" +
                                                                    "										,se_usuario_confirma.[us_estad] AS ae_sle_se_us_confirma_estado\n" +
                                                                    "								  ,[se_confirmacion_solicitud_equipo_cdgo] AS ae_sle_se_confirmacion_solicitud_equipo_cdgo\n" +
                                                                    "										--Confirmacion solicitudEquipo\n" +
                                                                    "										,[cse_cdgo] AS ae_sle_se_cse_cdgo\n" +
                                                                    "										,[cse_desc] AS ae_sle_se_ces_desc\n" +
                                                                    "										,[cse_estad] AS ae_sle_se_ces_estado\n" +
                                                                    "							,[sle_tipo_equipo_cdgo] AS ae_sle_tipo_equipo_cdgo\n" +
                                                                    "								--Tipo de Equipo\n" +
                                                                    "								,sle_tipoEquipo.[te_cdgo] AS ae_sle_te_cdgo\n" +
                                                                    "								,sle_tipoEquipo.[te_desc] AS ae_sle_te_desc\n" +
                                                                    "								,sle_tipoEquipo.[te_estad] AS ae_sle_te_estad\n" +
                                                                    "							,[sle_marca_equipo] AS ae_sle_marca_equipo\n" +
                                                                    "							,[sle_modelo_equipo] AS ae_sle_modelo_equipo\n" +
                                                                    "							,[sle_cant_equip] AS ae_sle_cant_equip\n" +
                                                                    "							,[sle_observ] AS ae_sle_observ\n" +
                                                                    "							,[sle_fecha_hora_inicio] AS ae_sle_fecha_hora_inicio\n" +
                                                                    "							,[sle_fecha_hora_fin] AS ae_sle_fecha_hora_fin\n" +
                                                                    "							,[sle_cant_minutos] AS ae_sle_cant_minutos\n" +
                                                                    "							,[sle_labor_realizada_cdgo] AS ae_sle_labor_realizada_cdgo\n" +
                                                                    "							-- Labor Realizada\n" +
                                                                    "								  ,[lr_cdgo] AS ae_sle_lr_cdgo\n" +
                                                                    "								  ,[lr_desc] AS ae_sle_lr_desc\n" +
                                                                    "								  ,[lr_estad] AS ae_sle_lr_estad\n" +
                                                                    "							,[sle_motonave_cdgo] AS ae_sle_sle_motonave_cdgo\n" +
                                                                    "							--Motonave\n" +
                                                                    "								  ,[mn_cdgo] AS ae_sle_mn_cdgo\n" +
                                                                    "								  ,[mn_desc] AS ae_sle_mn_desc\n" +
                                                                    "								  ,[mn_estad] AS ae_sle_mn_estad\n" +
                                                                    "							,[sle_cntro_cost_auxiliar_cdgo] AS ae_sle_cntro_cost_auxiliar_cdgo\n" +
                                                                    "								--Centro Costo Auxiliar\n" +
                                                                    "								,[cca_cdgo] AS ae_sle_cca_cdgo\n" +
                                                                    "								,[cca_cntro_cost_subcentro_cdgo] AS ae_sle_cca_cntro_cost_subcentro_cdgo\n" +
                                                                    "									-- SubCentro de Costo\n" +
                                                                    "									,[ccs_cdgo] AS ae_sle_ccs_cdgo\n" +
                                                                    "									,[ccs_desc] AS ae_sle_ccs_desc\n" +
                                                                    "									,[ccs_estad] AS ae_sle_ccs_estad\n" +
                                                                    "								,[cca_desc] AS ae_sle_cca_desc\n" +
                                                                    "								,[cca_estad] AS ae_sle_cca_estad\n" +
                                                                    "							,[sle_compania_cdgo] AS ae_sle_compania_cdgo\n" +
                                                                    "								--Compañia\n" +
                                                                    "								,[cp_cdgo] AS ae_sle_cp_cdgo\n" +
                                                                    "								,[cp_desc] AS ae_sle_cp_desc\n" +
                                                                    "								,[cp_estad] AS ae_sle_cp_estad\n" +
                                                                    "					  ,[ae_fecha_registro] AS ae_fecha_registro\n" +
                                                                    "					  ,[ae_fecha_hora_inicio] AS ae_fecha_hora_inicio\n" +
                                                                    "					  ,[ae_fecha_hora_fin] AS ae_fecha_hora_fin\n" +
                                                                    "					  ,[ae_cant_minutos] AS ae_cant_minutos\n" +
                                                                    "					  ,[ae_equipo_cdgo] AS ae_equipo_cdgo\n" +
                                                                    "							--Equipo\n" +
                                                                    "							,[eq_cdgo] AS ae_eq_cdgo\n" +
                                                                    "							,[eq_tipo_equipo_cdgo] AS ae_eq_tipo_equipo_cdgo\n" +
                                                                    "								--Tipo Equipo\n" +
                                                                    "								,eq_tipo_equipo.[te_cdgo] AS ae_eq_te_cdgo\n" +
                                                                    "								,eq_tipo_equipo.[te_desc] AS ae_eq_te_desc\n" +
                                                                    "								,eq_tipo_equipo.[te_estad] AS ae_eq_te_estad\n" +
                                                                    "							,[eq_codigo_barra] AS ae_eq_codigo_barra\n" +
                                                                    "							,[eq_referencia] AS ae_eq_referencia\n" +
                                                                    "							,[eq_producto] AS ae_eq_producto\n" +
                                                                    "							,[eq_capacidad] AS ae_eq_capacidad\n" +
                                                                    "							,[eq_marca] AS ae_eq_marca\n" +
                                                                    "							,[eq_modelo] AS ae_eq_modelo\n" +
                                                                    "							,[eq_serial] AS ae_eq_serial\n" +
                                                                    "							,[eq_desc] AS ae_eq_desc\n" +
                                                                    "							,[eq_clasificador1_cdgo] AS ae_eq_clasificador1_cdgo\n" +
                                                                    "								-- Clasificador 1\n" +
                                                                    "								,eq_clasificador1.[ce_cdgo] AS ae_eq_ce1_cdgo\n" +
                                                                    "								,eq_clasificador1.[ce_desc] AS ae_eq_ce1_desc\n" +
                                                                    "								,eq_clasificador1.[ce_estad] AS ae_eq_ce1_estad\n" +
                                                                    "							,[eq_clasificador2_cdgo] AS ae_eq_clasificador2_cdgo\n" +
                                                                    "								-- Clasificador 2\n" +
                                                                    "								,eq_clasificador2.[ce_cdgo] AS ae_eq_ce2_cdgo\n" +
                                                                    "								,eq_clasificador2.[ce_desc] AS ae_eq_ce2_desc\n" +
                                                                    "								,eq_clasificador2.[ce_estad] AS ae_eq_ce2_estad\n" +
                                                                    "							,[eq_proveedor_equipo_cdgo] AS ae_eq_proveedor_equipo_cdgo\n" +
                                                                    "								--Proveedor Equipo\n" +
                                                                    "								,[pe_cdgo] AS ae_eq_pe_cdgo\n" +
                                                                    "								,[pe_nit] AS ae_eq_pe_nit\n" +
                                                                    "								,[pe_desc] AS ae_eq_pe_desc\n" +
                                                                    "								,[pe_estad] AS ae_eq_pe_estad\n" +
                                                                    "							,[eq_equipo_pertenencia_cdgo] AS ae_eq_equipo_pertenencia_cdgo\n" +
                                                                    "								-- Equipo Pertenencia\n" +
                                                                    "								,eq_pertenencia.[ep_cdgo] AS ae_eq_ep_cdgo\n" +
                                                                    "								,eq_pertenencia.[ep_desc] AS ae_eq_ep_desc\n" +
                                                                    "								,eq_pertenencia.[ep_estad] AS ae_eq_ep_estad\n" +
                                                                    "							,[eq_observ] AS ae_eq_eq_observ\n" +
                                                                    "							,[eq_estad] AS ae_eq_eq_estad\n" +
                                                                    "							,[eq_actvo_fijo_id] AS ae_eq_actvo_fijo_id\n" +
                                                                    "							,[eq_actvo_fijo_referencia] AS ae_eq_actvo_fijo_referencia\n" +
                                                                    "							,[eq_actvo_fijo_desc] AS ae_eq_actvo_fijo_desc\n" +
                                                                    "					  ,[ae_equipo_pertenencia_cdgo] AS ae_equipo_pertenencia_cdgo\n" +
                                                                    "						-- Equipo Pertenencia\n" +
                                                                    "								,ae_pertenencia.[ep_cdgo] AS ae_ep_cdgo\n" +
                                                                    "								,ae_pertenencia.[ep_desc] AS ae_ep_desc\n" +
                                                                    "								,ae_pertenencia.[ep_estad]	 AS ae_ep_estad\n" +
                                                                    "					  ,[ae_cant_minutos_operativo] AS ae_cant_minutos_operativo\n" +
                                                                    "					  ,[ae_cant_minutos_parada] AS ae_cant_minutos_parada\n" +
                                                                    "					  ,[ae_cant_minutos_total] AS ae_cant_minutos_total\n" +
                                                                    "					  ,[ae_estad] AS ae_estad\n" +
                                                                    "\t\t\t\t\t  ,[sle_motonave_base_datos_cdgo] AS   ae_sle_mn_base_datos_cdgo\n" +
                                                                    "					  FROM ["+DB+"].[dbo].[asignacion_equipo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[solicitud_listado_equipo] ON [ae_solicitud_listado_equipo_cdgo]=[sle_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[cntro_oper] ae_cntro_oper ON [ae_cntro_oper_cdgo]=ae_cntro_oper.[co_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[solicitud_equipo] ON [sle_solicitud_equipo_cdgo]=[se_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[cntro_oper] se_cntro_oper ON [se_cntro_oper_cdgo]=se_cntro_oper.[co_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[usuario] se_usuario_realiza ON [se_usuario_realiza_cdgo]=se_usuario_realiza.[us_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[perfil] ae_prf_registra ON se_usuario_realiza.[us_perfil_cdgo]=ae_prf_registra.[prf_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[estado_solicitud_equipo] ON [se_estado_solicitud_equipo_cdgo]=[ese_cdgo]\n" +
                                                                    "							LEFT JOIN  ["+DB+"].[dbo].[usuario] se_usuario_confirma ON [se_usuario_realiza_cdgo]=se_usuario_confirma.[us_cdgo]\n" +
                                                                    "							LEFT JOIN ["+DB+"].[dbo].[perfil] ae_prf_registra_confirma ON se_usuario_confirma.[us_perfil_cdgo]=ae_prf_registra_confirma.[prf_cdgo]\n" +
                                                                    "							LEFT JOIN  ["+DB+"].[dbo].[confirmacion_solicitud_equipo] ON [se_confirmacion_solicitud_equipo_cdgo]=[cse_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[tipo_equipo] sle_tipoEquipo ON [sle_tipo_equipo_cdgo]=sle_tipoEquipo.[te_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[labor_realizada] ON [sle_labor_realizada_cdgo]=[lr_cdgo]\n" +
                                                                    //"							LEFT  JOIN["+DB+"].[dbo].[motonave] ON [sle_motonave_cdgo]=[mn_cdgo]\n" +
                                                                    "		LEFT JOIN ["+DB+"].[dbo].[motonave] ON [sle_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[sle_motonave_base_datos_cdgo] \n" +
                                                                    "               LEFT JOIN ["+DB+"].[dbo].[base_datos] sle_motonave_base_datos ON  [mn_base_datos_cdgo]=sle_motonave_base_datos.[bd_cdgo] \n"+
                                                                    "							INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [sle_cntro_cost_auxiliar_cdgo]=[cca_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[compania] ON [sle_compania_cdgo]=[cp_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[tipo_equipo] eq_tipo_equipo ON [eq_tipo_equipo_cdgo]=eq_tipo_equipo.[te_cdgo]\n" +
                                                                    "							LEFT JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador1 ON [eq_clasificador1_cdgo]=eq_clasificador1.[ce_cdgo]\n" +
                                                                    "							LEFT JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador2 ON [eq_clasificador2_cdgo]=eq_clasificador2.[ce_cdgo]\n" +
                                                                    "							INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [eq_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
                                                                    "							LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] eq_pertenencia ON [eq_equipo_pertenencia_cdgo]=eq_pertenencia.[ep_cdgo]\n" +
                                                                    "							LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ae_pertenencia ON [ae_equipo_pertenencia_cdgo]=ae_pertenencia.[ep_cdgo]	\n" +
                                                                    "	) asignacion_equipo ON [mcle_asignacion_equipo_cdgo]=[ae_cdgo]\n" +
                                                                    "	--Movimiento Equipo\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[mvto_equipo] ON [mcle_mvto_equipo_cdgo]=[me_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [me_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] me_cntro_cost_auxiliar ON [me_cntro_cost_auxiliar_cdgo]=me_cntro_cost_auxiliar.[cca_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro]  me_cntro_cost_subcentro ON me_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo]=me_cntro_cost_subcentro.[ccs_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[labor_realizada] ON [me_labor_realizada_cdgo]=[lr_cdgo] \n" +
                                                                    //"	INNER JOIN ["+DB+"].[dbo].[cliente] me_cliente ON [me_cliente_cdgo]=me_cliente.[cl_cdgo]\n" +
                                                                    "		LEFT JOIN ["+DB+"].[dbo].[cliente] me_cliente ON [me_cliente_cdgo]=me_cliente.[cl_cdgo] AND me_cliente.[cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
                                                                    "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON me_cliente.[cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                                                                           // "	LEFT JOIN  ["+DB+"].[dbo].[articulo] ON [me_articulo_cdgo]=[ar_cdgo]\n" +
                                                                    "		LEFT JOIN ["+DB+"].[dbo].[articulo]  ON [me_articulo_cdgo]=[ar_cdgo] AND	[ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
                                                                    "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON [ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
                                                                    "	INNER JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[cliente_recobro] ON [me_cliente_recobro_cdgo]=[clr_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[cliente] me_clr_cliente ON [clr_cliente_cdgo]=me_clr_cliente.[cl_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[usuario] me_clr_usuario ON [clr_usuario_cdgo]=me_clr_usuario.[us_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[perfil] me_clr_us_perfil ON me_clr_usuario.[us_perfil_cdgo]=me_clr_us_perfil.[prf_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[usuario] me_us_registro ON [me_usuario_registro_cdgo]=me_us_registro.[us_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[perfil] me_us_regist_perfil ON me_us_registro.[us_perfil_cdgo]=me_us_regist_perfil.[prf_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[usuario] me_us_autorizacion ON [me_usuario_autorizacion_cdgo]=me_us_autorizacion.[us_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[perfil] me_us_autoriza_perfil ON me_us_autorizacion.[us_perfil_cdgo]=me_us_autoriza_perfil.[prf_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[autorizacion_recobro] ON [me_autorizacion_recobro_cdgo]=[are_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[causa_inactividad] ON [me_causa_inactividad_cdgo]=[ci_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[usuario] me_us_inactividad ON [me_usuario_inactividad_cdgo]=me_us_inactividad.[us_cdgo]\n" +
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[perfil] me_us_inactvdad_perfil ON me_us_inactividad.[us_perfil_cdgo]=me_us_inactvdad_perfil.[prf_cdgo]\n" +
                                                                    "   LEFT JOIN  ["+DB+"].[dbo].[motivo_parada] ON [me_motivo_parada_cdgo]= [mpa_cdgo]\n"  +       
                                                                    "	WHERE [mc_fecha] BETWEEN '"+DatetimeInicio+"' AND '"+DatetimeFin+"' AND [me_inactividad]=0 AND [mc_estad_mvto_carbon_cdgo]=1 ORDER BY [mcle_mvto_carbon_cdgo] DESC");
            //query.setString(1, DatetimeInicio);
            //query.setString(2, DatetimeFin);
            
            //ResultSet resultSet; 
            //resultSet.
            //resultSet= query.executeQuery();              
            while(resultSet.next()){ 
                MvtoCarbon mvtoCarbon = new MvtoCarbon();
                    mvtoCarbon.setCodigo(resultSet.getString(3));
                    mvtoCarbon.setCentroOperacion(new CentroOperacion(Integer.parseInt(resultSet.getString(5)),resultSet.getString(6),resultSet.getString(7)));
                    mvtoCarbon.setCentroCostoAuxiliar(new CentroCostoAuxiliar(Integer.parseInt(resultSet.getString(9)),new CentroCostoSubCentro(Integer.parseInt(resultSet.getString(11)),resultSet.getString(12),resultSet.getString(13)),resultSet.getString(14),resultSet.getString(15)));
                    mvtoCarbon.setArticulo(new Articulo(resultSet.getString(17),resultSet.getString(18),resultSet.getString(19)));
                    mvtoCarbon.getArticulo().setBaseDatos(new BaseDatos(resultSet.getString(310)));
                    mvtoCarbon.setCliente(new Cliente(resultSet.getString(21),resultSet.getString(22),resultSet.getString(23)));
                    mvtoCarbon.getCliente().setBaseDatos(new BaseDatos(resultSet.getString(311)));
                    mvtoCarbon.setTransportadora(new Transportadora(resultSet.getString(25),resultSet.getString(26),resultSet.getString(27),resultSet.getString(28),resultSet.getString(29)));
                    mvtoCarbon.getTransportadora().setBaseDatos(new BaseDatos(resultSet.getString(312)));
                    mvtoCarbon.setFechaRegistro(resultSet.getString(30));
                    mvtoCarbon.setNumero_orden(resultSet.getString(31));
                    mvtoCarbon.setDeposito(resultSet.getString(32));
                    mvtoCarbon.setConsecutivo(resultSet.getString(33));
                    mvtoCarbon.setPlaca(resultSet.getString(34));
                    mvtoCarbon.setPesoVacio(resultSet.getString(35));
                    mvtoCarbon.setPesoLleno(resultSet.getString(36));
                    mvtoCarbon.setPesoNeto(resultSet.getString(37));
                    mvtoCarbon.setFechaEntradaVehiculo(resultSet.getString(38));
                    mvtoCarbon.setFecha_SalidaVehiculo(resultSet.getString(39));    
                    mvtoCarbon.setFechaInicioDescargue(resultSet.getString(40));    
                    mvtoCarbon.setFechaFinDescargue(resultSet.getString(41));      
                        Usuario us = new Usuario();           
                        us.setCodigo(resultSet.getString(43));
                        //us.setClave(resultSet.getString(44));
                        us.setNombres(resultSet.getString(45));
                        us.setApellidos(resultSet.getString(46));
                        us.setPerfilUsuario(new Perfil(resultSet.getString(48),resultSet.getString(49),resultSet.getString(50)));
                        us.setCorreo(resultSet.getString(51));
                        us.setEstado(resultSet.getString(52));
                    mvtoCarbon.setUsuarioRegistroMovil(us);
                    mvtoCarbon.setObservacion(resultSet.getString(53));
                        EstadoMvtoCarbon estadMvtoCarbon = new EstadoMvtoCarbon();
                        estadMvtoCarbon.setCodigo(resultSet.getString(55));
                        estadMvtoCarbon.setDescripcion(resultSet.getString(56));
                        estadMvtoCarbon.setEstado(resultSet.getString(57));
                    mvtoCarbon.setEstadoMvtoCarbon(estadMvtoCarbon);
                    mvtoCarbon.setConexionPesoCcarga(resultSet.getString(58));
                    mvtoCarbon.setRegistroManual(resultSet.getString(59));
                        Usuario usRegManual = new Usuario();           
                        usRegManual.setCodigo(resultSet.getString(61));
                        //usRegManual.setClave(resultSet.getString(62));
                        usRegManual.setNombres(resultSet.getString(63));
                        usRegManual.setApellidos(resultSet.getString(64));
                        usRegManual.setPerfilUsuario(new Perfil(resultSet.getString(66),resultSet.getString(67),resultSet.getString(68)));
                        usRegManual.setCorreo(resultSet.getString(69));
                        usRegManual.setEstado(resultSet.getString(70));
                    mvtoCarbon.setUsuarioRegistraManual(usRegManual);
                    mvtoCarbon.setCantidadHorasDescargue(resultSet.getString(303));
                //mvto_listEquipo.setMvtoCarbon(mvtoCarbon);
                ///Primero Equipo
                MvtoCarbon_ListadoEquipos mvto_listEquipoP= new MvtoCarbon_ListadoEquipos();
                    mvto_listEquipoP.setCodigo(resultSet.getString(1)); 
                    AsignacionEquipo asignacionEquipoP = new AsignacionEquipo();
                    asignacionEquipoP.setCodigo(resultSet.getString(72));
                        CentroOperacion coP= new CentroOperacion();
                        coP.setCodigo(Integer.parseInt(resultSet.getString(74)));
                        coP.setDescripcion(resultSet.getString(75));
                        coP.setEstado(resultSet.getString(76));
                    asignacionEquipoP.setCentroOperacion(coP);
                        SolicitudListadoEquipo solicitudListadoEquipoP = new SolicitudListadoEquipo();
                        solicitudListadoEquipoP.setCodigo(resultSet.getString(78));
                            SolicitudEquipo solicitudEquipoP= new SolicitudEquipo();
                            solicitudEquipoP.setCodigo(resultSet.getString(80));
                                CentroOperacion co_seP= new CentroOperacion();
                                co_seP.setCodigo(Integer.parseInt(resultSet.getString(82)));
                                co_seP.setDescripcion(resultSet.getString(83));
                                co_seP.setEstado(resultSet.getString(84));
                            solicitudEquipoP.setCentroOperacion(co_seP);
                            solicitudEquipoP.setFechaSolicitud(resultSet.getString(85));
                                Usuario us_seP = new Usuario();           
                                us_seP.setCodigo(resultSet.getString(87));
                                //us_seP.setClave(resultSet.getString(88));
                                us_seP.setNombres(resultSet.getString(89));
                                us_seP.setApellidos(resultSet.getString(90));
                                us_seP.setPerfilUsuario(new Perfil(resultSet.getString(92),resultSet.getString(93),resultSet.getString(94)));
                                us_seP.setCorreo(resultSet.getString(95));
                                us_seP.setEstado(resultSet.getString(96));
                            solicitudEquipoP.setUsuarioRealizaSolicitud(us_seP);
                            solicitudEquipoP.setFechaRegistro(resultSet.getString(97));
                                EstadoSolicitudEquipos estadoSolicitudEquiposP = new EstadoSolicitudEquipos();
                                estadoSolicitudEquiposP.setCodigo(resultSet.getString(99));
                                estadoSolicitudEquiposP.setDescripcion(resultSet.getString(100));
                                estadoSolicitudEquiposP.setEstado(resultSet.getString(101));
                            solicitudEquipoP.setEstadoSolicitudEquipo(estadoSolicitudEquiposP);
                            solicitudEquipoP.setFechaConfirmacion(resultSet.getString(102));
                                Usuario us_se_confirmP = new Usuario();           
                                us_se_confirmP.setCodigo(resultSet.getString(104));
                                //us_se_confirm.setClave(resultSet.getString(105));
                                us_se_confirmP.setNombres(resultSet.getString(106));
                                us_se_confirmP.setApellidos(resultSet.getString(107));
                                us_se_confirmP.setPerfilUsuario(new Perfil(resultSet.getString(109),resultSet.getString(110),resultSet.getString(111)));
                                us_se_confirmP.setCorreo(resultSet.getString(112));
                                us_se_confirmP.setEstado(resultSet.getString(113));
                            solicitudEquipoP.setUsuarioConfirmacionSolicitud(us_se_confirmP);
                                ConfirmacionSolicitudEquipos confirmacionSolicitudEquiposP = new ConfirmacionSolicitudEquipos();
                                confirmacionSolicitudEquiposP.setCodigo(resultSet.getString(115));
                                confirmacionSolicitudEquiposP.setDescripcion(resultSet.getString(116));
                                confirmacionSolicitudEquiposP.setEstado(resultSet.getString(117));
                            solicitudEquipoP.setConfirmacionSolicitudEquipo(confirmacionSolicitudEquiposP);
                        solicitudListadoEquipoP.setSolicitudEquipo(solicitudEquipoP);
                            TipoEquipo tipoEquipoP = new TipoEquipo();
                            tipoEquipoP.setCodigo(resultSet.getString(119));
                            tipoEquipoP.setDescripcion(resultSet.getString(120));
                            tipoEquipoP.setEstado(resultSet.getString(121));
                        solicitudListadoEquipoP.setTipoEquipo(tipoEquipoP);
                        solicitudListadoEquipoP.setMarcaEquipo(resultSet.getString(122));
                        solicitudListadoEquipoP.setModeloEquipo(resultSet.getString(123));
                        solicitudListadoEquipoP.setCantidad(Integer.parseInt(resultSet.getString(124)));
                        solicitudListadoEquipoP.setObservacacion(resultSet.getString(125));
                        solicitudListadoEquipoP.setFechaHoraInicio(resultSet.getString(126));
                        solicitudListadoEquipoP.setFechaHoraFin(resultSet.getString(127));  
                        solicitudListadoEquipoP.setCantidadMinutos(resultSet.getInt(128));
                            LaborRealizada laborRealizadaP = new LaborRealizada();
                            laborRealizadaP.setCodigo(resultSet.getString(130));
                            laborRealizadaP.setDescripcion(resultSet.getString(131));
                            laborRealizadaP.setEstado(resultSet.getString(132));
                        solicitudListadoEquipoP.setLaborRealizada(laborRealizadaP);
                            Motonave motonaveP = new Motonave();
                            motonaveP.setCodigo(resultSet.getString(134));
                            motonaveP.setDescripcion(resultSet.getString(135));
                            motonaveP.setEstado(resultSet.getString(136));
                            motonaveP.setBaseDatos(new BaseDatos( resultSet.getString(309)));
                        solicitudListadoEquipoP.setMotonave(motonaveP);
                            CentroCostoSubCentro centroCostoSubCentroP = new CentroCostoSubCentro();
                            centroCostoSubCentroP.setCodigo(resultSet.getInt(140));
                            centroCostoSubCentroP.setDescripcion(resultSet.getString(141));
                            centroCostoSubCentroP.setEstado(resultSet.getString(142));
                            CentroCostoAuxiliar centroCostoAuxiliarP = new CentroCostoAuxiliar();                          
                            centroCostoAuxiliarP.setCodigo(resultSet.getInt(138));
                            centroCostoAuxiliarP.setDescripcion(resultSet.getString(143));
                            centroCostoAuxiliarP.setEstado(resultSet.getString(144));
                            centroCostoAuxiliarP.setCentroCostoSubCentro(centroCostoSubCentroP);
                        solicitudListadoEquipoP.setCentroCostoAuxiliar(centroCostoAuxiliarP);
                            Compañia companiaP = new Compañia();
                            companiaP.setCodigo(resultSet.getString(146));
                            companiaP.setDescripcion(resultSet.getString(147));
                            companiaP.setEstado(resultSet.getString(148));
                        solicitudListadoEquipoP.setCompañia(companiaP);
                    asignacionEquipoP.setSolicitudListadoEquipo(solicitudListadoEquipoP);
                    asignacionEquipoP.setFechaRegistro(resultSet.getString(149));
                    asignacionEquipoP.setFechaHoraInicio(resultSet.getString(150));
                    asignacionEquipoP.setFechaHoraFin(resultSet.getString(151));
                    asignacionEquipoP.setCantidadMinutosProgramados(resultSet.getString(152));
                        Equipo equipoP = new Equipo(); 
                        equipoP.setCodigo(resultSet.getString(154));
                        equipoP.setTipoEquipo(new TipoEquipo(resultSet.getString(156),resultSet.getString(157),resultSet.getString(158)));
                        equipoP.setCodigo_barra(resultSet.getString(159));
                        equipoP.setReferencia(resultSet.getString(160));
                        equipoP.setProducto(resultSet.getString(161));
                        equipoP.setCapacidad(resultSet.getString(162));
                        equipoP.setMarca(resultSet.getString(163));
                        equipoP.setModelo(resultSet.getString(164));
                        equipoP.setSerial(resultSet.getString(165));
                        equipoP.setDescripcion(resultSet.getString(166));
                        equipoP.setClasificador1(new ClasificadorEquipo(resultSet.getString(168),resultSet.getString(169),resultSet.getString(170)));
                        equipoP.setClasificador2(new ClasificadorEquipo(resultSet.getString(172),resultSet.getString(173),resultSet.getString(174)));
                        equipoP.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(176),resultSet.getString(177),resultSet.getString(178),resultSet.getString(179)));
                        equipoP.setPertenenciaEquipo(new Pertenencia(resultSet.getString(181),resultSet.getString(182),resultSet.getString(183)));
                        equipoP.setObservacion(resultSet.getString(184));
                        equipoP.setEstado(resultSet.getString(185));
                        equipoP.setActivoFijo_codigo(resultSet.getString(186));
                        equipoP.setActivoFijo_referencia(resultSet.getString(187));
                        equipoP.setActivoFijo_descripcion(resultSet.getString(188));
                    asignacionEquipoP.setEquipo(equipoP);
                    asignacionEquipoP.setPertenencia(new Pertenencia(resultSet.getString(190),resultSet.getString(191),resultSet.getString(192)));
                    asignacionEquipoP.setCantidadMinutosOperativo(resultSet.getString(193));
                    asignacionEquipoP.setCantidadMinutosParada(resultSet.getString(194));
                    asignacionEquipoP.setCantidadMinutosTotal(resultSet.getString(195));
                    asignacionEquipoP.setEstado(resultSet.getString(196));
                mvto_listEquipoP.setAsignacionEquipo(asignacionEquipoP);
                    MvtoEquipo mvtoEquipoP = new MvtoEquipo();
                    mvtoEquipoP.setCodigo(resultSet.getString(198));
                    mvtoEquipoP.setAsignacionEquipo(asignacionEquipoP);
                    mvtoEquipoP.setFechaRegistro(resultSet.getString(200));        
                    mvtoEquipoP.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(202),resultSet.getString(203),resultSet.getString(204),resultSet.getString(205)));
                    mvtoEquipoP.setNumeroOrden(resultSet.getString(206));        
                        CentroCostoSubCentro centroCostoSubCentro_mvtoEquipoP = new CentroCostoSubCentro();
                            centroCostoSubCentro_mvtoEquipoP.setCodigo(resultSet.getInt(210));
                            centroCostoSubCentro_mvtoEquipoP.setDescripcion(resultSet.getString(211));
                            centroCostoSubCentro_mvtoEquipoP.setEstado(resultSet.getString(212));
                        CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipoP = new CentroCostoAuxiliar();                          
                        centroCostoAuxiliar_mvtoEquipoP.setCodigo(resultSet.getInt(208));
                        centroCostoAuxiliar_mvtoEquipoP.setDescripcion(resultSet.getString(213));
                        centroCostoAuxiliar_mvtoEquipoP.setEstado(resultSet.getString(214));
                        centroCostoAuxiliar_mvtoEquipoP.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipoP);
                    mvtoEquipoP.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipoP);
                        LaborRealizada laborRealizadaT = new LaborRealizada();
                        laborRealizadaT.setCodigo(resultSet.getString(216));
                        laborRealizadaT.setDescripcion(resultSet.getString(217));
                        laborRealizadaT.setEstado(resultSet.getString(218));
                    mvtoEquipoP.setLaborRealizada(laborRealizadaT);
                    mvtoEquipoP.setCliente(new Cliente(resultSet.getString(220),resultSet.getString(221),resultSet.getString(222)));
                     mvtoEquipoP.getCliente().setBaseDatos(new BaseDatos( resultSet.getString(313))); 
                    mvtoEquipoP.setArticulo(new Articulo(resultSet.getString(224),resultSet.getString(225),resultSet.getString(226)));
                    mvtoEquipoP.getArticulo().setBaseDatos(new BaseDatos( resultSet.getString(314)));
                    mvtoEquipoP.setFechaHoraInicio(resultSet.getString(227));
                    mvtoEquipoP.setFechaHoraFin(resultSet.getString(228));
                    mvtoEquipoP.setTotalMinutos(resultSet.getString(229));
                    mvtoEquipoP.setCostoTotalRecobroCliente(resultSet.getString(230));
                        Recobro recobroP = new Recobro();
                        recobroP.setCodigo(resultSet.getString(233));
                        recobroP.setDescripcion(resultSet.getString(234));
                        recobroP.setEstado(resultSet.getString(235));
                    mvtoEquipoP.setRecobro(recobroP);
                        ClienteRecobro ClienteRecobroP = new ClienteRecobro();
                        ClienteRecobroP.setCodigo(resultSet.getString(235));
                            Cliente cliente_recobroP = new Cliente();
                            cliente_recobroP.setCodigo(resultSet.getString(239));
                            cliente_recobroP.setDescripcion(resultSet.getString(240));
                            cliente_recobroP.setEstado(resultSet.getString(241));
                        ClienteRecobroP.setCliente(cliente_recobroP);
                            Usuario usuario_recobreP = new Usuario();
                            usuario_recobreP.setCodigo(resultSet.getString(243));
                            //usuario_recobreP.setClave(resultSet.getString(244));
                            usuario_recobreP.setNombres(resultSet.getString(245));
                            usuario_recobreP.setApellidos(resultSet.getString(246));
                            usuario_recobreP.setPerfilUsuario(new Perfil(resultSet.getString(248),resultSet.getString(249),resultSet.getString(250)));
                            usuario_recobreP.setCorreo(resultSet.getString(251));
                            usuario_recobreP.setEstado(resultSet.getString(252));
                        ClienteRecobroP.setUsuario(usuario_recobreP);
                        ClienteRecobroP.setValorRecobro(resultSet.getString(253));
                        ClienteRecobroP.setFechaRegistro(resultSet.getString(254));
                    mvtoEquipoP.setClienteRecobro(ClienteRecobroP);
                    mvtoEquipoP.setCostoTotalRecobroCliente(resultSet.getString(255));
                        Usuario usuario_me_registraP = new Usuario();
                        usuario_me_registraP.setCodigo(resultSet.getString(257));
                        //usuario_me_registraP.setClave(resultSet.getString(258));
                        usuario_me_registraP.setNombres(resultSet.getString(259));
                        usuario_me_registraP.setApellidos(resultSet.getString(260));
                        usuario_me_registraP.setPerfilUsuario(new Perfil(resultSet.getString(262),resultSet.getString(263),resultSet.getString(264)));
                        usuario_me_registraP.setCorreo(resultSet.getString(265));
                        usuario_me_registraP.setEstado(resultSet.getString(266));
                    mvtoEquipoP.setUsuarioQuieRegistra(usuario_me_registraP);
                        Usuario usuario_me_autorizaP = new Usuario();
                        usuario_me_autorizaP.setCodigo(resultSet.getString(268));
                        //usuario_me_autorizaP.setClave(resultSet.getString(269));
                        usuario_me_autorizaP.setNombres(resultSet.getString(270));
                        usuario_me_autorizaP.setApellidos(resultSet.getString(271));
                        usuario_me_autorizaP.setPerfilUsuario(new Perfil(resultSet.getString(273),resultSet.getString(274),resultSet.getString(275)));
                        usuario_me_autorizaP.setCorreo(resultSet.getString(276));
                        usuario_me_autorizaP.setEstado(resultSet.getString(277));
                    mvtoEquipoP.setUsuarioAutorizaRecobro(usuario_me_autorizaP);
                        AutorizacionRecobro autorizacionRecobroP = new AutorizacionRecobro();
                        autorizacionRecobroP.setCodigo(resultSet.getString(279));
                        autorizacionRecobroP.setDescripcion(resultSet.getString(280));
                        autorizacionRecobroP.setEstado(resultSet.getString(281));
                    mvtoEquipoP.setAutorizacionRecobro(autorizacionRecobroP);
                    mvtoEquipoP.setObservacionAutorizacion(resultSet.getString(282)); 
                    mvtoEquipoP.setInactividad(resultSet.getString(283)); 
                        CausaInactividad causaInactividadP = new CausaInactividad();
                        causaInactividadP.setCodigo(resultSet.getString(285));
                        causaInactividadP.setDescripcion(resultSet.getString(286));
                        causaInactividadP.setEstado(resultSet.getString(287));
                    mvtoEquipoP.setCausaInactividad(causaInactividadP);
                        Usuario usuario_me_us_inactividadP = new Usuario();
                        usuario_me_us_inactividadP.setCodigo(resultSet.getString(289));
                        //usuario_me_us_inactividad.setClave(resultSet.getString(290));
                        usuario_me_us_inactividadP.setNombres(resultSet.getString(291));
                        usuario_me_us_inactividadP.setApellidos(resultSet.getString(292));
                        usuario_me_us_inactividadP.setPerfilUsuario(new Perfil(resultSet.getString(294),resultSet.getString(295),resultSet.getString(296)));
                        usuario_me_us_inactividadP.setCorreo(resultSet.getString(297));
                        usuario_me_us_inactividadP.setEstado(resultSet.getString(298));
                mvtoEquipoP.setUsuarioInactividad(usuario_me_us_inactividadP);
                        MotivoParada motivoParada= new MotivoParada();
                        motivoParada.setCodigo(resultSet.getString(300));
                        motivoParada.setDescripcion(resultSet.getString(301));
                        motivoParada.setEstado(resultSet.getString(302));
                mvtoEquipoP.setMotivoParada(motivoParada);
                mvtoEquipoP.setObservacionMvtoEquipo(resultSet.getString(303)); 
                mvtoEquipoP.setEstado(resultSet.getString(304)); 
                mvtoEquipoP.setDesdeCarbon(resultSet.getString(305)); 
                mvtoEquipoP.setTotalMinutos(resultSet.getString(307)); 
                mvto_listEquipoP.setMvtoEquipo(mvtoEquipoP);  
                mvto_listEquipoP.setEstado(resultSet.getString(306));
                
                
                ArrayList<MvtoCarbon_ListadoEquipos> Listado_mvto_listEquipo = new ArrayList();
                Listado_mvto_listEquipo.add(mvto_listEquipoP);
                
                resultSet.next();
                try{
                    while(mvtoCarbon.getCodigo().equals(resultSet.getString(2))){
                        MvtoCarbon_ListadoEquipos mvto_listEquipo= new MvtoCarbon_ListadoEquipos();
                        mvto_listEquipo.setCodigo(resultSet.getString(1)); 
                        AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                        asignacionEquipo.setCodigo(resultSet.getString(72));
                            CentroOperacion co= new CentroOperacion();
                            co.setCodigo(Integer.parseInt(resultSet.getString(74)));
                            co.setDescripcion(resultSet.getString(75));
                            co.setEstado(resultSet.getString(76));
                        asignacionEquipo.setCentroOperacion(co);
                            SolicitudListadoEquipo solicitudListadoEquipo = new SolicitudListadoEquipo();
                            solicitudListadoEquipo.setCodigo(resultSet.getString(78));
                                SolicitudEquipo solicitudEquipo= new SolicitudEquipo();
                                solicitudEquipo.setCodigo(resultSet.getString(80));
                                    CentroOperacion co_se= new CentroOperacion();
                                    co_se.setCodigo(Integer.parseInt(resultSet.getString(82)));
                                    co_se.setDescripcion(resultSet.getString(83));
                                    co_se.setEstado(resultSet.getString(84));
                                solicitudEquipo.setCentroOperacion(co_se);
                                solicitudEquipo.setFechaSolicitud(resultSet.getString(85));
                                    Usuario us_se = new Usuario();           
                                    us_se.setCodigo(resultSet.getString(87));
                                    //us_se.setClave(resultSet.getString(88));
                                    us_se.setNombres(resultSet.getString(89));
                                    us_se.setApellidos(resultSet.getString(90));
                                    us_se.setPerfilUsuario(new Perfil(resultSet.getString(92),resultSet.getString(93),resultSet.getString(94)));
                                    us_se.setCorreo(resultSet.getString(95));
                                    us_se.setEstado(resultSet.getString(96));
                                solicitudEquipo.setUsuarioRealizaSolicitud(us_se);
                                solicitudEquipo.setFechaRegistro(resultSet.getString(97));
                                    EstadoSolicitudEquipos estadoSolicitudEquipos = new EstadoSolicitudEquipos();
                                    estadoSolicitudEquipos.setCodigo(resultSet.getString(99));
                                    estadoSolicitudEquipos.setDescripcion(resultSet.getString(100));
                                    estadoSolicitudEquipos.setEstado(resultSet.getString(101));
                                solicitudEquipo.setEstadoSolicitudEquipo(estadoSolicitudEquipos);
                                solicitudEquipo.setFechaConfirmacion(resultSet.getString(102));
                                    Usuario us_se_confirm = new Usuario();           
                                    us_se_confirm.setCodigo(resultSet.getString(104));
                                    //us_se_confirm.setClave(resultSet.getString(105));
                                    us_se_confirm.setNombres(resultSet.getString(106));
                                    us_se_confirm.setApellidos(resultSet.getString(107));
                                    us_se_confirm.setPerfilUsuario(new Perfil(resultSet.getString(109),resultSet.getString(110),resultSet.getString(111)));
                                    us_se_confirm.setCorreo(resultSet.getString(112));
                                    us_se_confirm.setEstado(resultSet.getString(113));
                                solicitudEquipo.setUsuarioConfirmacionSolicitud(us_se_confirm);
                                    ConfirmacionSolicitudEquipos confirmacionSolicitudEquipos = new ConfirmacionSolicitudEquipos();
                                    confirmacionSolicitudEquipos.setCodigo(resultSet.getString(115));
                                    confirmacionSolicitudEquipos.setDescripcion(resultSet.getString(116));
                                    confirmacionSolicitudEquipos.setEstado(resultSet.getString(117));
                                solicitudEquipo.setConfirmacionSolicitudEquipo(confirmacionSolicitudEquipos);
                            solicitudListadoEquipo.setSolicitudEquipo(solicitudEquipo);
                                TipoEquipo tipoEquipo = new TipoEquipo();
                                tipoEquipo.setCodigo(resultSet.getString(119));
                                tipoEquipo.setDescripcion(resultSet.getString(120));
                                tipoEquipo.setEstado(resultSet.getString(121));
                            solicitudListadoEquipo.setTipoEquipo(tipoEquipo);
                            solicitudListadoEquipo.setMarcaEquipo(resultSet.getString(122));
                            solicitudListadoEquipo.setModeloEquipo(resultSet.getString(123));
                            solicitudListadoEquipo.setCantidad(Integer.parseInt(resultSet.getString(124)));
                            solicitudListadoEquipo.setObservacacion(resultSet.getString(125));
                            solicitudListadoEquipo.setFechaHoraInicio(resultSet.getString(126));
                            solicitudListadoEquipo.setFechaHoraFin(resultSet.getString(127));  
                            solicitudListadoEquipo.setCantidadMinutos(resultSet.getInt(128));
                               LaborRealizada laborRealizada = new LaborRealizada();
                                laborRealizada.setCodigo(resultSet.getString(130));
                                laborRealizada.setDescripcion(resultSet.getString(131));
                                laborRealizada.setEstado(resultSet.getString(132));
                            solicitudListadoEquipo.setLaborRealizada(laborRealizada);
                                Motonave motonave = new Motonave();
                                motonave.setCodigo(resultSet.getString(134));
                                motonave.setDescripcion(resultSet.getString(135));
                                motonave.setEstado(resultSet.getString(136));
                                motonave.setBaseDatos(new BaseDatos( resultSet.getString(309)));
                            solicitudListadoEquipo.setMotonave(motonave);
                                CentroCostoSubCentro centroCostoSubCentro = new CentroCostoSubCentro();
                                centroCostoSubCentro.setCodigo(resultSet.getInt(140));
                                centroCostoSubCentro.setDescripcion(resultSet.getString(141));
                                centroCostoSubCentro.setEstado(resultSet.getString(142));
                                CentroCostoAuxiliar centroCostoAuxiliar = new CentroCostoAuxiliar();                          
                                centroCostoAuxiliar.setCodigo(resultSet.getInt(138));
                                centroCostoAuxiliar.setDescripcion(resultSet.getString(143));
                                centroCostoAuxiliar.setEstado(resultSet.getString(144));
                                centroCostoAuxiliar.setCentroCostoSubCentro(centroCostoSubCentro);
                            solicitudListadoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar);
                                Compañia compania = new Compañia();
                                compania.setCodigo(resultSet.getString(146));
                                compania.setDescripcion(resultSet.getString(147));
                                compania.setEstado(resultSet.getString(148));
                            solicitudListadoEquipo.setCompañia(compania);
                        asignacionEquipo.setSolicitudListadoEquipo(solicitudListadoEquipo);
                        asignacionEquipo.setFechaRegistro(resultSet.getString(149));
                        asignacionEquipo.setFechaHoraInicio(resultSet.getString(150));
                        asignacionEquipo.setFechaHoraFin(resultSet.getString(151));
                        asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(152));
                            Equipo equipo = new Equipo(); 
                            equipo.setCodigo(resultSet.getString(154));
                            equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(156),resultSet.getString(157),resultSet.getString(158)));
                            equipo.setCodigo_barra(resultSet.getString(159));
                            equipo.setReferencia(resultSet.getString(160));
                            equipo.setProducto(resultSet.getString(161));
                            equipo.setCapacidad(resultSet.getString(162));
                            equipo.setMarca(resultSet.getString(163));
                            equipo.setModelo(resultSet.getString(164));
                            equipo.setSerial(resultSet.getString(165));
                            equipo.setDescripcion(resultSet.getString(166));
                            equipo.setClasificador1(new ClasificadorEquipo(resultSet.getString(168),resultSet.getString(169),resultSet.getString(170)));
                            equipo.setClasificador2(new ClasificadorEquipo(resultSet.getString(172),resultSet.getString(173),resultSet.getString(174)));
                            equipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(176),resultSet.getString(177),resultSet.getString(178),resultSet.getString(179)));
                            equipo.setPertenenciaEquipo(new Pertenencia(resultSet.getString(181),resultSet.getString(182),resultSet.getString(183)));
                            equipo.setObservacion(resultSet.getString(184));
                            equipo.setEstado(resultSet.getString(185));
                            equipo.setActivoFijo_codigo(resultSet.getString(186));
                            equipo.setActivoFijo_referencia(resultSet.getString(187));
                            equipo.setActivoFijo_descripcion(resultSet.getString(188));
                        asignacionEquipo.setEquipo(equipo);
                        asignacionEquipo.setPertenencia(new Pertenencia(resultSet.getString(190),resultSet.getString(191),resultSet.getString(192)));
                        asignacionEquipo.setCantidadMinutosOperativo(resultSet.getString(193));
                        asignacionEquipo.setCantidadMinutosParada(resultSet.getString(194));
                        asignacionEquipo.setCantidadMinutosTotal(resultSet.getString(195));
                        asignacionEquipo.setEstado(resultSet.getString(196));
                    mvto_listEquipo.setAsignacionEquipo(asignacionEquipo);
                        MvtoEquipo mvtoEquipo = new MvtoEquipo();
                        mvtoEquipo.setCodigo(resultSet.getString(198));
                        mvtoEquipo.setAsignacionEquipo(asignacionEquipo);
                        mvtoEquipo.setFechaRegistro(resultSet.getString(200));        
                        mvtoEquipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(202),resultSet.getString(203),resultSet.getString(204),resultSet.getString(205)));
                        mvtoEquipo.setNumeroOrden(resultSet.getString(206));        
                            CentroCostoSubCentro centroCostoSubCentro_mvtoEquipo = new CentroCostoSubCentro();
                                centroCostoSubCentro_mvtoEquipo.setCodigo(resultSet.getInt(210));
                                centroCostoSubCentro_mvtoEquipo.setDescripcion(resultSet.getString(211));
                                centroCostoSubCentro_mvtoEquipo.setEstado(resultSet.getString(212));
                            CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipo = new CentroCostoAuxiliar();                          
                            centroCostoAuxiliar_mvtoEquipo.setCodigo(resultSet.getInt(208));
                            centroCostoAuxiliar_mvtoEquipo.setDescripcion(resultSet.getString(213));
                            centroCostoAuxiliar_mvtoEquipo.setEstado(resultSet.getString(214));
                            centroCostoAuxiliar_mvtoEquipo.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipo);
                        mvtoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipo);
                            LaborRealizada laborRealizadaTr = new LaborRealizada();
                            laborRealizadaTr.setCodigo(resultSet.getString(216));
                            laborRealizadaTr.setDescripcion(resultSet.getString(217));
                            laborRealizadaTr.setEstado(resultSet.getString(218));
                        mvtoEquipo.setLaborRealizada(laborRealizadaTr);
                        mvtoEquipo.setCliente(new Cliente(resultSet.getString(220),resultSet.getString(221),resultSet.getString(222)));
                         mvtoEquipo.getCliente().setBaseDatos(new BaseDatos( resultSet.getString(313))); 
                        mvtoEquipo.setArticulo(new Articulo(resultSet.getString(224),resultSet.getString(225),resultSet.getString(226)));
                        mvtoEquipo.getArticulo().setBaseDatos(new BaseDatos( resultSet.getString(314)));
                        mvtoEquipo.setFechaHoraInicio(resultSet.getString(227));
                        mvtoEquipo.setFechaHoraFin(resultSet.getString(228));
                        mvtoEquipo.setTotalMinutos(resultSet.getString(229));
                        mvtoEquipo.setCostoTotalRecobroCliente(resultSet.getString(230));
                            Recobro recobro = new Recobro();
                            recobro.setCodigo(resultSet.getString(233));
                            recobro.setDescripcion(resultSet.getString(234));
                            recobro.setEstado(resultSet.getString(235));
                        mvtoEquipo.setRecobro(recobro);
                            ClienteRecobro ClienteRecobro = new ClienteRecobro();
                            ClienteRecobro.setCodigo(resultSet.getString(235));
                                Cliente cliente_recobro = new Cliente();
                                cliente_recobro.setCodigo(resultSet.getString(239));
                                cliente_recobro.setDescripcion(resultSet.getString(240));
                                cliente_recobro.setEstado(resultSet.getString(241));
                            ClienteRecobro.setCliente(cliente_recobro);
                                Usuario usuario_recobre = new Usuario();
                                usuario_recobre.setCodigo(resultSet.getString(243));
                                //usuario_recobre.setClave(resultSet.getString(244));
                                usuario_recobre.setNombres(resultSet.getString(245));
                                usuario_recobre.setApellidos(resultSet.getString(246));
                                usuario_recobre.setPerfilUsuario(new Perfil(resultSet.getString(248),resultSet.getString(249),resultSet.getString(250)));
                                usuario_recobre.setCorreo(resultSet.getString(251));
                                usuario_recobre.setEstado(resultSet.getString(252));
                            ClienteRecobro.setUsuario(usuario_recobre);
                            ClienteRecobro.setValorRecobro(resultSet.getString(253));
                            ClienteRecobro.setFechaRegistro(resultSet.getString(254));
                        mvtoEquipo.setClienteRecobro(ClienteRecobro);
                        mvtoEquipo.setCostoTotalRecobroCliente(resultSet.getString(255));
                            Usuario usuario_me_registra = new Usuario();
                            usuario_me_registra.setCodigo(resultSet.getString(257));
                            //usuario_me_registra.setClave(resultSet.getString(258));
                            usuario_me_registra.setNombres(resultSet.getString(259));
                            usuario_me_registra.setApellidos(resultSet.getString(260));
                            usuario_me_registra.setPerfilUsuario(new Perfil(resultSet.getString(262),resultSet.getString(263),resultSet.getString(264)));
                            usuario_me_registra.setCorreo(resultSet.getString(265));
                            usuario_me_registra.setEstado(resultSet.getString(266));
                        mvtoEquipo.setUsuarioQuieRegistra(usuario_me_registra);
                            Usuario usuario_me_autoriza = new Usuario();
                            usuario_me_autoriza.setCodigo(resultSet.getString(268));
                            //usuario_me_autoriza.setClave(resultSet.getString(269));
                            usuario_me_autoriza.setNombres(resultSet.getString(270));
                            usuario_me_autoriza.setApellidos(resultSet.getString(271));
                            usuario_me_autoriza.setPerfilUsuario(new Perfil(resultSet.getString(273),resultSet.getString(274),resultSet.getString(275)));
                            usuario_me_autoriza.setCorreo(resultSet.getString(276));
                            usuario_me_autoriza.setEstado(resultSet.getString(277));
                        mvtoEquipo.setUsuarioAutorizaRecobro(usuario_me_autoriza);
                            AutorizacionRecobro autorizacionRecobro = new AutorizacionRecobro();
                            autorizacionRecobro.setCodigo(resultSet.getString(279));
                            autorizacionRecobro.setDescripcion(resultSet.getString(280));
                            autorizacionRecobro.setEstado(resultSet.getString(281));
                        mvtoEquipo.setAutorizacionRecobro(autorizacionRecobro);
                        mvtoEquipo.setObservacionAutorizacion(resultSet.getString(282)); 
                        mvtoEquipo.setInactividad(resultSet.getString(283)); 
                            CausaInactividad causaInactividad = new CausaInactividad();
                            causaInactividad.setCodigo(resultSet.getString(285));
                            causaInactividad.setDescripcion(resultSet.getString(286));
                            causaInactividad.setEstado(resultSet.getString(287));
                        mvtoEquipo.setCausaInactividad(causaInactividad);
                            Usuario usuario_me_us_inactividad = new Usuario();
                            usuario_me_us_inactividad.setCodigo(resultSet.getString(289));
                            //usuario_me_us_inactividad.setClave(resultSet.getString(290));
                            usuario_me_us_inactividad.setNombres(resultSet.getString(291));
                            usuario_me_us_inactividad.setApellidos(resultSet.getString(292));
                            usuario_me_us_inactividad.setPerfilUsuario(new Perfil(resultSet.getString(294),resultSet.getString(295),resultSet.getString(296)));
                            usuario_me_us_inactividad.setCorreo(resultSet.getString(297));
                            usuario_me_us_inactividad.setEstado(resultSet.getString(298));
                        mvtoEquipo.setUsuarioInactividad(usuario_me_us_inactividad);
                            MotivoParada motivoParadaT= new MotivoParada();
                            motivoParadaT.setCodigo(resultSet.getString(300));
                            motivoParadaT.setDescripcion(resultSet.getString(301));
                            motivoParadaT.setEstado(resultSet.getString(302));
                        mvtoEquipo.setMotivoParada(motivoParadaT);
                        mvtoEquipo.setObservacionMvtoEquipo(resultSet.getString(303)); 
                        mvtoEquipo.setEstado(resultSet.getString(304)); 
                        mvtoEquipo.setDesdeCarbon(resultSet.getString(305)); 
                        mvtoEquipo.setTotalMinutos(resultSet.getString(307)); 
                        mvtoEquipo.setMes(resultSet.getString(309)); 
                    mvto_listEquipo.setMvtoEquipo(mvtoEquipo);  
                    mvto_listEquipo.setEstado(resultSet.getString(306));       
                    Listado_mvto_listEquipo.add(mvto_listEquipo);        
                        resultSet.next();
                    }
                }catch(Exception e){
                }
                //Listado_mvto_listEquipo.add(mvto_listEquipoP);
                mvtoCarbon.setListadoMvtoCarbon_ListadoEquipos(Listado_mvto_listEquipo);
                resultSet.previous();
               
                //MvtoCarbon_ListadoEquipos();
                
                listadoObjetos.add(mvtoCarbon);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los Movimientos de Carbon");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }
    public MvtoCarbon_ListadoEquipos                ProcesarEnCcargaGPX(MvtoCarbon_ListadoEquipos Objeto1, Usuario us) throws UnknownHostException, SocketException{
        Conexion_DB_ccargaGP control = new Conexion_DB_ccargaGP(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        MvtoCarbon_ListadoEquipos Objeto=Objeto1;
        if(Objeto1.getMvtoCarbon().getConexionPesoCcarga().equals("NO")){
            try{
                //String[] fechaA=Objeto.getMvtoCarbon().getFechaEntradaVehiculo().split(" ");
                //String[] fechaB=fechaA[0].split("-");
                //String nuevaFecha=fechaB[0]+"-"+fechaB[2]+"-"+fechaB[1]+" "+fechaA[1];
                PreparedStatement query= conexion.prepareStatement("SELECT [ti_cnsctvo],[ti_fcha_slda],[ti_pso_slda],[ti_pso_nto]  FROM ["+DB+"].[dbo].[tqte] WHERE [ti_plca]='"+Objeto.getMvtoCarbon().getPlaca()+"' AND [ti_pso_entrda]="+Objeto.getMvtoCarbon().getPesoVacio()+" AND [ti_fcha_entrda] = '"+Objeto.getMvtoCarbon().getFechaEntradaVehiculo()+"'");
                //PreparedStatement query= conexion.prepareStatement("SELECT [ti_cnsctvo],[ti_fcha_slda],[ti_pso_slda],[ti_pso_nto]  FROM ["+DB+"].[dbo].[tqte] WHERE [ti_plca]='TVB543' AND [ti_pso_entrda]=51750 AND [ti_fcha_entrda] = '2020-06-02 09:43:00.0'");
                                                   // "  FROM ["+DB+"].[dbo].[tqte] WHERE [ti_plca]=? AND [ti_pso_entrda]=? AND [ti_fcha_entrda] = CAST (CONVERT(varchar, '"+nuevaFecha+"',112) as smalldatetime)");
                                                   // "  FROM ["+DB+"].[dbo].[tqte] WHERE [ti_plca]=? AND [ti_pso_entrda]=? AND [ti_fcha_entrda] like '"+Objeto.getFechaEntrada()+"'");
                //query.setString(1, "'"+Objeto.getPlaca()+"'");
                //query.setString(2, Objeto.getPesoVacio());

                //query.setString(3, "'"+nuevaFecha+"'");
                ResultSet resultSet= query.executeQuery();
                //System.out.println("SELECT [ti_cnsctvo],[ti_fcha_slda],[ti_pso_slda],[ti_pso_nto]  FROM ["+DB+"].[dbo].[tqte] WHERE [ti_plca]='"+Objeto.getMvtoCarbon().getPlaca()+"' AND [ti_pso_entrda]="+Objeto.getMvtoCarbon().getPesoVacio()+" AND [ti_fcha_entrda] = '"+Objeto.getMvtoCarbon().getFechaEntradaVehiculo()+"'");
                //System.out.println(""+resultSet.getRow());
                while(resultSet.next()){ 
                    System.out.println("1");
                    Objeto.getMvtoCarbon().setConsecutivo(resultSet.getString(1));              
                    //Objeto.setFechaSalida(resultSet.getString(2));
                    
                    //String[] fechaA_Salida=resultSet.getString(2).split(" ");
                    //String[] fechaB_Salida=fechaA_Salida[0].split("-");
                    //String nuevaFecha_Salida=fechaB_Salida[0]+"-"+fechaB_Salida[2]+"-"+fechaB_Salida[1]+" "+fechaA_Salida[1];
                    //Objeto.getMvtoCarbon().setFecha_SalidaVehiculo(nuevaFecha_Salida);
                    Objeto.getMvtoCarbon().setFecha_SalidaVehiculo(resultSet.getString(2));
                    Objeto.getMvtoCarbon().setPesoLleno(resultSet.getString(3));              
                    Objeto.getMvtoCarbon().setPesoNeto(resultSet.getString(4));
                    Objeto.getMvtoCarbon().setConexionPesoCcarga("1");//Se proceso en controlCarga validando informacion con tabla Tiquete
                    try {
                        int n=actualizarDatosMvtoCarbon(Objeto,us);
                        if(n==1){
                             System.out.println("Actualización Exitosa");
                        }else{
                            System.out.println("Algo malo pasó");
                        }

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ControlDB_MvtoEquipo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(Objeto.getMvtoCarbon().getPlaca()+"-"+Objeto.getMvtoCarbon().getPesoLleno()+"-"+Objeto.getMvtoCarbon().getFecha_SalidaVehiculo()+"-"+Objeto.getMvtoCarbon().getPesoNeto());
                }
            }catch (SQLException sqlException){
                System.out.println("Con Error: "+Objeto.getMvtoCarbon().getPlaca()+"-"+Objeto.getMvtoCarbon().getPesoVacio()+"-"+Objeto.getMvtoCarbon().getFechaEntradaVehiculo());
                JOptionPane.showMessageDialog(null, "Error al Tratar de buscar el tiquete");
                sqlException.printStackTrace();
            } 
        }else{
            System.out.println("No entró");
        }
        control.cerrarConexionBaseDatos();
        return Objeto;
    }
    public int                                      actualizarDatosMvtoCarbon(MvtoCarbon_ListadoEquipos mvtoCarbon, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        int result=0;
        try{  
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_carbon] set [mc_consecutivo_tqute]=?, [mc_peso_lleno]=?,[mc_peso_neto]=?,[mc_fecha_salid]='"+mvtoCarbon.getMvtoCarbon().getFecha_SalidaVehiculo()+"',[mc_conexion_peso_ccarga]='"+mvtoCarbon.getMvtoCarbon().getConexionPesoCcarga()+"' WHERE [mc_cdgo]=?");
            queryActualizar.setString(1,mvtoCarbon.getMvtoCarbon().getConsecutivo());
            queryActualizar.setString(2,mvtoCarbon.getMvtoCarbon().getPesoLleno());
            queryActualizar.setString(3,mvtoCarbon.getMvtoCarbon().getPesoNeto());
           // queryActualizar.setString(4,"'"+mvtoCarbon.getFechaSalida()+"'");
            queryActualizar.setString(4,mvtoCarbon.getMvtoCarbon().getCodigo());
            queryActualizar.execute();
            result=1;
            if(result==1){
                result=0;
                 //Extraemos el nombre del Equipo y la IP        
                String namePc=new ControlDB_Config().getNamePC();
                String ipPc=new ControlDB_Config().getIpPc();
                String macPC=new ControlDB_Config().getMacAddress();
                PreparedStatement Query_Auditoria= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[auditoria]([au_cdgo]\n" +
                                        "      ,[au_fecha]\n" +
                                        "      ,[au_usuario_cdgo_registro]\n" +
                                        "      ,[au_nombre_dispositivo_registro]\n" +
                                        "      ,[au_ip_dispositivo_registro]\n" +
                                        "      ,[au_mac_dispositivo_registro]\n" +
                                        "      ,[au_cdgo_mtvo]\n" +
                                        "      ,[au_desc_mtvo]\n" +
                                        "      ,[au_detalle_mtvo])\n" +
                        "     VALUES("+
                        "           (SELECT (CASE WHEN (MAX([au_cdgo]) IS NULL) THEN 1 ELSE (MAX([au_cdgo])+1) END)AS [au_cdgo] FROM ["+DB+"].[dbo].[auditoria])"+
                        "           ,(SELECT SYSDATETIME())"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,'DESCARGUE_CARBON'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre el descargue de carbon de Código:',?,' Consecutivo: ',?,' Placa: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, mvtoCarbon.getMvtoCarbon().getCodigo());
                Query_Auditoria.setString(6, mvtoCarbon.getMvtoCarbon().getCodigo());
                Query_Auditoria.setString(7, mvtoCarbon.getMvtoCarbon().getConsecutivo());
                Query_Auditoria.setString(8, mvtoCarbon.getMvtoCarbon().getPlaca()+" Pesos: Vacio:"+mvtoCarbon.getMvtoCarbon().getPesoVacio()+" Lleno: "+mvtoCarbon.getMvtoCarbon().getPesoLleno()+" Neto:"+mvtoCarbon.getMvtoCarbon().getPesoNeto());
                Query_Auditoria.execute();
                result=1;
            } 
        }
        catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "ERROR al querer insertar los datos.");
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    } 
    public int                                      activarVehiculoEnMvtoCarbon(MvtoCarbon_ListadoEquipos mvtoCarbon_ListadoEquipos, Usuario us, String observacion) throws FileNotFoundException, UnknownHostException, SocketException{
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        int result=0;
        try{  
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_equipo] set [me_inactividad]=0 WHERE [me_cdgo]=?");
            queryActualizar.setString(1,mvtoCarbon_ListadoEquipos.getMvtoEquipo().getCodigo());
            queryActualizar.execute();
            result=1;
            if(result==1){
                result=0;
                //Extraemos el nombre del Equipo y la IP        
                String namePc=new ControlDB_Config().getNamePC();
                String ipPc=new ControlDB_Config().getIpPc();
                String macPC=new ControlDB_Config().getMacAddress();
                
                PreparedStatement Query_Auditoria= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[auditoria]([au_cdgo]\n" +
                                        "      ,[au_fecha]\n" +
                                        "      ,[au_usuario_cdgo_registro]\n" +
                                        "      ,[au_nombre_dispositivo_registro]\n" +
                                        "      ,[au_ip_dispositivo_registro]\n" +
                                        "      ,[au_mac_dispositivo_registro]\n" +
                                        "      ,[au_cdgo_mtvo]\n" +
                                        "      ,[au_desc_mtvo]\n" +
                                        "      ,[au_detalle_mtvo])\n" +
                        "     VALUES("+
                        "           (SELECT (CASE WHEN (MAX([au_cdgo]) IS NULL) THEN 1 ELSE (MAX([au_cdgo])+1) END)AS [au_cdgo] FROM ["+DB+"].[dbo].[auditoria])"+
                        "           ,(SELECT SYSDATETIME())"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,'DESCARGUE_CARBON'" +
                        "           ,CONCAT('Se activo un equipo que realizó el descargue de un vehículo de carbón en el sistema sobre el descargue de carbon de Código:',?,' Consecutivo: ',?,' Placa: ',?,' Razon de activación: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, mvtoCarbon_ListadoEquipos.getMvtoEquipo().getCodigo());
                Query_Auditoria.setString(6, mvtoCarbon_ListadoEquipos.getMvtoEquipo().getCodigo());
                Query_Auditoria.setString(7, mvtoCarbon_ListadoEquipos.getMvtoCarbon().getConsecutivo());
                Query_Auditoria.setString(8, mvtoCarbon_ListadoEquipos.getMvtoCarbon().getPlaca()+" Pesos: Vacio:"+mvtoCarbon_ListadoEquipos.getMvtoCarbon().getPesoVacio()+" Lleno: "+mvtoCarbon_ListadoEquipos.getMvtoCarbon().getPesoLleno()+" Neto:"+mvtoCarbon_ListadoEquipos.getMvtoCarbon().getPesoNeto());
                Query_Auditoria.setString(9,observacion);
                Query_Auditoria.execute();
                result=1;
            } 
        }
        catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "ERROR al querer insertar los datos.");
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    } 
    public int                                      inactivarVehiculoEnMvtoCarbon(MvtoCarbon_ListadoEquipos mvtoCarbon_ListadoEquipos, Usuario us, String observacion) throws FileNotFoundException, UnknownHostException, SocketException{
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        int result=0;
        try{  
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_equipo] set [me_inactividad]=1 WHERE [me_cdgo]=?");
            queryActualizar.setString(1,mvtoCarbon_ListadoEquipos.getMvtoEquipo().getCodigo());
            queryActualizar.execute();
            result=1;
            if(result==1){
                result=0;
                //Extraemos el nombre del Equipo y la IP        
                String namePc=new ControlDB_Config().getNamePC();
                String ipPc=new ControlDB_Config().getIpPc();
                String macPC=new ControlDB_Config().getMacAddress();
                
                PreparedStatement Query_Auditoria= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[auditoria]([au_cdgo]\n" +
                                        "      ,[au_fecha]\n" +
                                        "      ,[au_usuario_cdgo_registro]\n" +
                                        "      ,[au_nombre_dispositivo_registro]\n" +
                                        "      ,[au_ip_dispositivo_registro]\n" +
                                        "      ,[au_mac_dispositivo_registro]\n" +
                                        "      ,[au_cdgo_mtvo]\n" +
                                        "      ,[au_desc_mtvo]\n" +
                                        "      ,[au_detalle_mtvo])\n" +
                        "     VALUES("+
                        "           (SELECT (CASE WHEN (MAX([au_cdgo]) IS NULL) THEN 1 ELSE (MAX([au_cdgo])+1) END)AS [au_cdgo] FROM ["+DB+"].[dbo].[auditoria])"+
                        "           ,(SELECT SYSDATETIME())"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,?"+
                        "           ,'DESCARGUE_CARBON'" +
                        "           ,CONCAT('Se inactivo un equipo que realizó el descargue de un vehículo de carbón en el sistema sobre el descargue de carbon de Código:',?,' Consecutivo: ',?,' Placa: ',?,' Razon de activación: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, mvtoCarbon_ListadoEquipos.getMvtoEquipo().getCodigo());
                Query_Auditoria.setString(6, mvtoCarbon_ListadoEquipos.getMvtoEquipo().getCodigo());
                Query_Auditoria.setString(7, mvtoCarbon_ListadoEquipos.getMvtoCarbon().getConsecutivo());
                Query_Auditoria.setString(8, mvtoCarbon_ListadoEquipos.getMvtoCarbon().getPlaca()+" Pesos: Vacio:"+mvtoCarbon_ListadoEquipos.getMvtoCarbon().getPesoVacio()+" Lleno: "+mvtoCarbon_ListadoEquipos.getMvtoCarbon().getPesoLleno()+" Neto:"+mvtoCarbon_ListadoEquipos.getMvtoCarbon().getPesoNeto());
                Query_Auditoria.setString(9,observacion);
                Query_Auditoria.execute();
                result=1;
            } 
        }
        catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "ERROR al querer insertar los datos.");
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    } 
    public String                                   comparadorFechaActual(String ano,String mes, String dia, String hora, String minuto){
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        conexion= control.ConectarBaseDatos();
        String retorno = "";
        String fechaOrigen="'"+ano+"-"+mes+"-"+dia+" "+hora+":"+minuto+":00.0"+"'";
        try{
            //PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[articulo] WHERE [ar_cdgo] like ?;");
            PreparedStatement query= conexion.prepareStatement(" Declare @fechaActual datetime2= SYSDATETIME() ;\n" +
                                                    "            SELECT DATEDIFF(minute, "+fechaOrigen+", @fechaActual) as DiferenciaFecha;");
            //query.setString(1, fechaOrigen);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){
                retorno= resultSet.getString(1);
            }
        }catch (SQLException sqlException){
            System.out.println("Error al tratar de comparar las fechas");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return retorno;
        
        /*Declare @fechaEntrada datetime2 = '2020-02-18 14:50:00.0';   
            Declare @fechaActual datetime2= SYSDATETIME() ;
            SELECT DATEDIFF(minute, @fechaEntrada, @fechaActual) as DiferenciaFecha;  
        */
    
    }
    public String                                   comparadorEntreDosFechas(String fechaInicio, String fechaFinal){
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        conexion= control.ConectarBaseDatos();
        String retorno = "";
        try{

            PreparedStatement query= conexion.prepareStatement(" SELECT DATEDIFF(minute, '"+fechaInicio+"', '"+fechaFinal+"') as DiferenciaFecha;");
            //query.setString(1, fechaOrigen);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){
                retorno= resultSet.getString(1);
            }
        }catch (SQLException sqlException){
            System.out.println("Error al tratar de comparar las fechas");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        System.out.println(""+retorno);
        return retorno;
        
        /*Declare @fechaEntrada datetime2 = '2020-02-18 14:50:00.0';   
            Declare @fechaActual datetime2= SYSDATETIME() ;
            SELECT DATEDIFF(minute, @fechaEntrada, @fechaActual) as DiferenciaFecha;  
        */
    
    }    
    public int                                      registrarMvtoEquipo(Usuario usuario, MvtoEquipo mvtoEquipo) throws FileNotFoundException, UnknownHostException, SocketException {
        int result=0;
        Conexion_DB_costos_vg control = new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        /*try {
            if (mvtoEquipo.getRecobro().getCodigo().equals("1")) {
                mvtoEquipo.getRecobro().setCodigo("2");
                mvtoEquipo.getRecobro().setDescripcion("PENDIENTE CONFIRMACIÓN");
            }
        }catch(Exception e){
            System.out.println("Error al procesar el recobro");
        }*/
        conexion= control.ConectarBaseDatos();
        if(conexion != null) {
            try {
                String cdgo_cliente;
                String cdgo_articulo;
                String cdgo_motonave;
                String cdgo_cliente_db;
                String cdgo_articulo_db;
                String cdgo_motonave_db;
                if(mvtoEquipo.getCliente()==null){
                    cdgo_cliente=null;
                    cdgo_cliente_db=null;
                }else{
                    cdgo_cliente="'"+mvtoEquipo.getCliente().getCodigo()+"'";
                    cdgo_cliente_db=mvtoEquipo.getCliente().getBaseDatos().getCodigo();
                }

                if(mvtoEquipo.getArticulo()==null){
                    cdgo_articulo=null;
                    cdgo_articulo_db=null;
                }else{
                    cdgo_articulo="'"+mvtoEquipo.getArticulo().getCodigo()+"'";
                    cdgo_articulo_db=mvtoEquipo.getArticulo().getBaseDatos().getCodigo();
                }

                if(mvtoEquipo.getMotonave()==null){
                    cdgo_motonave=null;
                    cdgo_motonave_db=null;
                }else{
                    /*if(mvtoEquipo.getMotonave().getCodigo() != null) {
                        if (!(new ControlDB_Carbon(tipoConexion).validarExistenciaMotonave(mvtoEquipo.getMotonave()))) {
                            int n = new ControlDB_Carbon(tipoConexion).registrarMotonave(mvtoEquipo.getMotonave(), usuario);
                            if (n == 1) {
                                System.out.println("Hemos registrado una nueva motonave en el sistema");
                            }
                        }
                    }*/
                    cdgo_motonave="'"+mvtoEquipo.getMotonave().getCodigo()+"'";
                    cdgo_motonave_db=mvtoEquipo.getMotonave().getBaseDatos().getCodigo();
                }
                String codigoUsuarioAutorizaRecobro="";
                String codigoAutorizacionRecobro="NULL";
                    if(mvtoEquipo.getUsuarioAutorizaRecobro() != null){
                        codigoUsuarioAutorizaRecobro = "'"+mvtoEquipo.getUsuarioAutorizaRecobro().getCodigo()+"'";
                        codigoAutorizacionRecobro="1";
                    }else{
                        codigoUsuarioAutorizaRecobro="NULL";
                        codigoAutorizacionRecobro="NULL";
                        
                    }
                    String codigoCentroCostoAuxiliarDestino="";
                    if(mvtoEquipo.getCentroCostoAuxiliarDestino()!= null){
                        codigoCentroCostoAuxiliarDestino = "'"+mvtoEquipo.getCentroCostoAuxiliarDestino().getCodigo()+"'";
                    }else{
                        codigoCentroCostoAuxiliarDestino="NULL";
                    }

                PreparedStatement queryRegistrarT = conexion.prepareStatement("" +
                        "DECLARE @consecutivo BIGINT=(SELECT (CASE WHEN (MAX([me_cdgo]) IS NULL) \n" +
                        " THEN 1 ELSE (MAX([me_cdgo])+1) END)AS [mc_cdgo] \n" +
                        " FROM ["+DB+"].[dbo].[mvto_equipo]); " +
                        "  INSERT INTO ["+DB+"].[dbo].[mvto_equipo]\n" +
                        "           ([me_cdgo]"
                                + ",[me_asignacion_equipo_cdgo]"
                                + ",[me_fecha]"
                                + ",[me_proveedor_equipo_cdgo]"
                                + ",[me_num_orden]"
                                + ",[me_cntro_oper_cdgo]"
                                + ",[me_cntro_cost_auxiliar_cdgo]\n"
                                + ",[me_labor_realizada_cdgo]"
                                + ",[me_cliente_cdgo]"
                                + ",[me_articulo_cdgo]"
                                + ",[me_motonave_cdgo]"
                                + ",[me_fecha_hora_inicio]"
                                + ",[me_fecha_hora_fin]"
                                + ",[me_total_minutos]\n"
                                + ",[me_valor_hora]"
                                + ",[me_costo_total]"
                                + ",[me_recobro_cdgo]"
                                + ",[me_cliente_recobro_cdgo]"
                                + ",[me_costo_total_recobro_cliente]"
                                + ",[me_usuario_registro_cdgo]\n" 
                                + ",[me_usuario_autorizacion_cdgo]"
                                + ",[me_autorizacion_recobro_cdgo]"
                                + ",[me_observ_autorizacion]"
                                + ",[me_inactividad]"
                                + ",[me_causa_inactividad_cdgo]\n"
                                + ",[me_usuario_inactividad_cdgo]"
                                + ",[me_motivo_parada_estado]"
                                + ",[me_motivo_parada_cdgo]"
                                + ",[me_observ]"
                                + ",[me_estado]"
                                + ",[me_desde_mvto_carbon]"
                                + ",[me_cntro_cost_auxiliarDestino_cdgo]"
                                + ",[me_cliente_base_datos_cdgo]"
                                + ",[me_motonave_base_datos_cdgo]"
                                + ",[me_articulo_base_datos_cdgo]"
                                + ",[me_usuario_cierre_cdgo])\n" +
                        "     VALUES ((SELECT (CASE WHEN (MAX([me_cdgo]) IS NULL) \n" +
                                        " THEN 1 ELSE (MAX([me_cdgo])+1) END)AS [mc_cdgo] \n" +
                                        " FROM ["+DB+"].[dbo].[mvto_equipo]) --me_cdgo\n" +
                        "           ,"+mvtoEquipo.getAsignacionEquipo().getCodigo()+"\n" +
                        "           ,(SELECT CONVERT (DATETIME,(SELECT SYSDATETIME()))) \n" +
                        "           ,"+mvtoEquipo.getProveedorEquipo().getCodigo()+"\n" +
                        "           ,'"+mvtoEquipo.getNumeroOrden()+"' --me_num_orden\n" +
                        "           ,"+mvtoEquipo.getCentroOperacion().getCodigo()+"\n" +
                        "           ,"+mvtoEquipo.getCentroCostoAuxiliar().getCodigo()+"\n" +
                        "           ,"+mvtoEquipo.getLaborRealizada().getCodigo()+"\n" +
                        "           ,"+cdgo_cliente+"\n" +
                        "           ,"+cdgo_articulo+"\n" +
                        "           ,"+cdgo_motonave+"--me_motonave_cdgo\n" +
                        "           ,'"+mvtoEquipo.getFechaHoraInicio()+"'--me_fecha_hora_inicio\n" +
                        "           ,'"+mvtoEquipo.getFechaHoraFin()+"'--me_fecha_hora_fin\n" +
                        "           ,(SELECT DATEDIFF(minute, '"+mvtoEquipo.getFechaHoraInicio()+"', '"+mvtoEquipo.getFechaHoraFin()+"')) -- \n" +//--me_total_minutos
                        "           ,NULL--\n" +//--me_valor_hora
                        "           ,NULL--\n" +//--me_costo_total
                        "           ,"+mvtoEquipo.getRecobro().getCodigo()+"--\n" +//--me_recobro_cdgo
                        "           ,NULL--\n" +//--me_cliente_recobro_cdgo
                        "           ,NULL--\n" +//--me_costo_total_recobro_cliente
                        "           ,'"+mvtoEquipo.getUsuarioQuieRegistra().getCodigo()+"'--\n" +//--me_usuario_registro_cdgo
                        "           ,"+codigoUsuarioAutorizaRecobro+"--\n" +//--me_usuario_autorizacion_cdgo
                        "           ,"+codigoAutorizacionRecobro+"--\n" +//--me_autorizacion_recobro_cdgo
                        "           ,NULL--\n" +//--me_observ_autorizacion
                        "           ,0   --\n" +//--me_inactividad
                        "           ,NULL--\n" +//--me_causa_inactividad_cdgo
                        "           ,NULL--\n" +//--me_usuario_inactividad_cdgo
                        "           ,1--\n" +//--me_motivo_parada_estado
                        "           ,"+mvtoEquipo.getMotivoParada().getCodigo()+"--\n" +//--me_motivo_parada_cdgo
                        "           ,'"+mvtoEquipo.getObservacionMvtoEquipo()+"'--\n" +//--me_observ
                        "           ,"+mvtoEquipo.getEstado()+"--\n" +//--me_estado
                        "           ,"+mvtoEquipo.getDesdeCarbon()+"--\n" +//--me_desde_mvto_carbon
                        "           ,"+codigoCentroCostoAuxiliarDestino+"\n" +//--me_centrocostoAuxiliar
                        "           ,"+cdgo_cliente_db+"\n" +
                        "           ,"+cdgo_motonave_db+"\n" +
                        "           ,"+cdgo_articulo_db+"\n" +
                        "           ,'"+mvtoEquipo.getUsuarioQuienCierra().getCodigo()+"'\n" +
                        "); \n");
                
                
                queryRegistrarT.execute();
                result=1;
                if (result == 1) {
                    result=0;
                    //Sacamos el ultimo valor 
                    PreparedStatement queryMaximo= conexion.prepareStatement("SELECT MAX(me_cdgo) FROM ["+DB+"].[dbo].[mvto_equipo];");
                    ResultSet resultSetMaximo= queryMaximo.executeQuery();
                    while(resultSetMaximo.next()){ 
                        if(resultSetMaximo.getString(1) != null){
                            mvtoEquipo.setCodigo(resultSetMaximo.getString(1));
                        }
                    }
                    result=1;
                    conexion= control.ConectarBaseDatos();
                    result=0;
                    //Extraemos el nombre del Dispositivo y la IP
                    String namePc=new ControlDB_Config().getNamePC();
                    String ipPc=new ControlDB_Config().getIpPc();
                    String macPC=new ControlDB_Config().getMacAddress();
                    PreparedStatement Query_Auditoria= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[auditoria]([au_cdgo]\n" +
                            "      ,[au_fecha]\n" +
                            "      ,[au_usuario_cdgo_registro]\n" +
                            "      ,[au_nombre_dispositivo_registro]\n" +
                            "      ,[au_ip_dispositivo_registro]\n" +
                            "      ,[au_mac_dispositivo_registro]\n" +
                            "      ,[au_cdgo_mtvo]\n" +
                            "      ,[au_desc_mtvo]\n" +
                            "      ,[au_detalle_mtvo])\n" +
                            "     VALUES("+
                            "           (SELECT (CASE WHEN (MAX([au_cdgo]) IS NULL) THEN 1 ELSE (MAX([au_cdgo])+1) END)AS [au_cdgo] FROM ["+DB+"].[dbo].[auditoria])"+
                            "           ,(SELECT SYSDATETIME())"+
                            "           ,?"+
                            "           ,?"+
                            "           ,?"+
                            "           ,?"+
                            "           ,?"+
                            "           ,'MVTO_EQUIPO'" +
                            "           , CONCAT (?,?,' Asignacion Código: ',?,' Equipo Tipo: ',?,' Equipo Marca: ',?,' Equipo Modelo: ',?,' Equipo Descripción: ',?));");
                    Query_Auditoria.setString(1, usuario.getCodigo());
                    Query_Auditoria.setString(2, namePc);
                    Query_Auditoria.setString(3, ipPc);
                    Query_Auditoria.setString(4, macPC);
                    Query_Auditoria.setString(5, mvtoEquipo.getCodigo());
                    Query_Auditoria.setString(6, "Se registró un nuevo inicio de el modulo de equipo desde un dispositivo Movil, con Mvto_Equipo Código: ");
                    Query_Auditoria.setString(7, mvtoEquipo.getCodigo());
                    Query_Auditoria.setString(8, mvtoEquipo.getAsignacionEquipo().getCodigo());
                    Query_Auditoria.setString(9, mvtoEquipo.getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion());
                    Query_Auditoria.setString(10, mvtoEquipo.getAsignacionEquipo().getEquipo().getMarca());
                    Query_Auditoria.setString(11, mvtoEquipo.getAsignacionEquipo().getEquipo().getModelo());
                    Query_Auditoria.setString(12, mvtoEquipo.getAsignacionEquipo().getEquipo().getDescripcion());
                    Query_Auditoria.execute();
                    result=1;
                    //Consultamos el movimiento completo para procesar y cargar los costos
                    MvtoEquipo mvto_equipoTemp =new ControlDB_MvtoEquipo(tipoConexion).buscar_mvtoEquiposParticular(mvtoEquipo.getCodigo());
                    new ControlDB_MvtoEquipo(tipoConexion).Procesar_MvtoEquipo(mvto_equipoTemp,usuario);
                }
            } catch (SQLException sqlException) {
                result = 0;
                sqlException.printStackTrace();
            }
            control.cerrarConexionBaseDatos();
        }else{
            return 2;
        }
        return result;
    }
    
    
}
