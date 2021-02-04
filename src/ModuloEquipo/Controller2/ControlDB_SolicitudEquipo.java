package ModuloEquipo.Controller2;

import ConnectionDB2.Conexion_DB_costos_vg;
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import Catalogo.Model.Motonave;
import Catalogo.Model.Compañia;
import ModuloEquipo.Model.ConfirmacionSolicitudEquipos;
import ModuloEquipo.Model.EstadoSolicitudEquipos;
import Catalogo.Model.LaborRealizada;
import ModuloEquipo.Model.SolicitudEquipo;
import ModuloEquipo.Model.SolicitudListadoEquipo;
import Catalogo.Model.TipoEquipo;
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
import java.util.ArrayList;
import javax.swing.JOptionPane;
 
public class ControlDB_SolicitudEquipo {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private String tipoConexion;
    public ControlDB_SolicitudEquipo(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    public int registrar(SolicitudEquipo Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            conexion= control.ConectarBaseDatos();
            conexion= control.ConectarBaseDatos();
            PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[solicitud_equipo]\n" +
            "           ([se_cdgo],[se_cntro_oper_cdgo],[se_fecha]\n" +
            "           ,[se_usuario_realiza_cdgo],[se_fecha_registro]\n" +
            "           ,[se_estado_solicitud_equipo_cdgo],[se_fecha_confirmacion]\n" +
            "           ,[se_usuario_confirma_cdgo],[se_confirmacion_solicitud_equipo_cdgo])\n" +
                        "     VALUES\n" +
                        "           ((SELECT (CASE WHEN (MAX([se_cdgo]) IS NULL)\n" +
                                                            " THEN 1\n" +
                                                            " ELSE (MAX([se_cdgo])+1) END)AS [se_cdgo]\n" +
                                        " FROM ["+DB+"].[dbo].[solicitud_equipo]),?,(SELECT CONVERT(DATE, GETDATE())),?,(SELECT SYSDATETIME()),?,NULL,NULL,NULL)");
            Query.setInt(1, Objeto.getCentroOperacion().getCodigo());
            Query.setString(2, Objeto.getUsuarioRealizaSolicitud().getCodigo());
            Query.setString(3, Objeto.getEstadoSolicitudEquipo().getCodigo());
            Query.execute();
            result=1;
            if(result==1){
                result=0;
                //Sacamos el valor del codigo de la ultima compra
                    PreparedStatement Query_Maximo= conexion.prepareStatement("SELECT MAX(se_cdgo) FROM ["+DB+"].[dbo].[solicitud_equipo];");
                    ResultSet resultSet= Query_Maximo.executeQuery();
                    while(resultSet.next()){ 
                        if(resultSet.getString(1) != null){
                            Objeto.setCodigo(resultSet.getString(1));
                        }
                    }
                for (SolicitudListadoEquipo ListadoSolicitudesEquipos1 : Objeto.getListadoSolicitudesEquipos()) {
                    if(ListadoSolicitudesEquipos1.getMotonave() != null){
                        PreparedStatement QuerySolicitudListadoEquipo= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[solicitud_listado_equipo]\n" +
                                                "           ([sle_cdgo],[sle_solicitud_equipo_cdgo],[sle_tipo_equipo_cdgo],[sle_marca_equipo]\n" +
                                                "           ,[sle_modelo_equipo] ,[sle_cant_equip],[sle_observ],[sle_fecha_hora_inicio]\n" +
                                                "           ,[sle_fecha_hora_fin],[sle_cant_minutos],[sle_labor_realizada_cdgo]\n" +
                                                "           ,[sle_motonave_cdgo],[sle_cntro_cost_auxiliar_cdgo]\n" +
                                                "           ,[sle_compania_cdgo])\n" +
                                                "     VALUES\n" +
                                                "           ((SELECT (CASE WHEN (MAX([sle_cdgo]) IS NULL)\n" +
                                                                        " THEN 1\n" +
                                                                        " ELSE (MAX([sle_cdgo])+1) END)AS [sle_cdgo]\n" +
                                                    " FROM ["+DB+"].[dbo].[solicitud_listado_equipo]),?,?,?,?,?,?,?,?,?,?,?,?,?)");
                        QuerySolicitudListadoEquipo.setString(1, Objeto.getCodigo());
                        QuerySolicitudListadoEquipo.setString(2, ListadoSolicitudesEquipos1.getTipoEquipo().getCodigo());
                        QuerySolicitudListadoEquipo.setString(3, ListadoSolicitudesEquipos1.getMarcaEquipo());
                        QuerySolicitudListadoEquipo.setString(4, ListadoSolicitudesEquipos1.getModeloEquipo());
                        QuerySolicitudListadoEquipo.setInt(5, ListadoSolicitudesEquipos1.getCantidad());
                        QuerySolicitudListadoEquipo.setString(6, ListadoSolicitudesEquipos1.getObservacacion());
                        QuerySolicitudListadoEquipo.setString(7, ListadoSolicitudesEquipos1.getFechaHoraInicio());
                        QuerySolicitudListadoEquipo.setString(8, ListadoSolicitudesEquipos1.getFechaHoraFin());
                        QuerySolicitudListadoEquipo.setFloat(9, ListadoSolicitudesEquipos1.getCantidadMinutos());
                        QuerySolicitudListadoEquipo.setString(10, ListadoSolicitudesEquipos1.getLaborRealizada().getCodigo());
                        QuerySolicitudListadoEquipo.setString(11, ListadoSolicitudesEquipos1.getMotonave().getCodigo());
                        QuerySolicitudListadoEquipo.setInt(12, ListadoSolicitudesEquipos1.getCentroCostoAuxiliar().getCodigo());
                        QuerySolicitudListadoEquipo.setString(13, ListadoSolicitudesEquipos1.getCompañia().getCodigo());
                        QuerySolicitudListadoEquipo.execute();
                    }else{
                        System.out.println("INSERT INTO ["+DB+"].[dbo].[solicitud_listado_equipo]\n" +
                                                                                "           ([sle_cdgo],[sle_solicitud_equipo_cdgo],[sle_tipo_equipo_cdgo],[sle_marca_equipo]\n" +
                                                                                "           ,[sle_modelo_equipo] ,[sle_cant_equip],[sle_observ],[sle_fecha_hora_inicio]\n" +
                                                                                "           ,[sle_fecha_hora_fin],[sle_cant_minutos],[sle_labor_realizada_cdgo]\n" +
                                                                                "           ,[sle_motonave_cdgo],[sle_cntro_cost_auxiliar_cdgo]\n" +
                                                                                "           ,[sle_compania_cdgo])\n" +
                                                                                "     VALUES\n" +
                                                                                "           ((SELECT (CASE WHEN (MAX([sle_cdgo]) IS NULL)\n" +
                                                                                                        " THEN 1\n" +
                                                                                                        " ELSE (MAX([sle_cdgo])+1) END)AS [sle_cdgo]\n" +
                                                                                    " FROM ["+DB+"].[dbo].[solicitud_listado_equipo]),"+Objeto.getCodigo()+","+ListadoSolicitudesEquipos1.getTipoEquipo().getCodigo()+","+ListadoSolicitudesEquipos1.getMarcaEquipo()+","+ListadoSolicitudesEquipos1.getModeloEquipo()+","+ListadoSolicitudesEquipos1.getCantidad()+","+ListadoSolicitudesEquipos1.getObservacacion()+","+ListadoSolicitudesEquipos1.getFechaHoraInicio()+","+ListadoSolicitudesEquipos1.getFechaHoraFin()+","+ListadoSolicitudesEquipos1.getCantidadMinutos()+",NULL,"+ListadoSolicitudesEquipos1.getLaborRealizada().getCodigo()+","+ListadoSolicitudesEquipos1.getCentroCostoAuxiliar().getCodigo()+","+ListadoSolicitudesEquipos1.getCompañia().getCodigo()+")");
                        PreparedStatement QuerySolicitudListadoEquipo= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[solicitud_listado_equipo]\n" +
                                                                                "           ([sle_cdgo],[sle_solicitud_equipo_cdgo],[sle_tipo_equipo_cdgo],[sle_marca_equipo]\n" +
                                                                                "           ,[sle_modelo_equipo] ,[sle_cant_equip],[sle_observ],[sle_fecha_hora_inicio]\n" +
                                                                                "           ,[sle_fecha_hora_fin],[sle_cant_minutos],[sle_labor_realizada_cdgo]\n" +
                                                                                "           ,[sle_motonave_cdgo],[sle_cntro_cost_auxiliar_cdgo]\n" +
                                                                                "           ,[sle_compania_cdgo])\n" +
                                                                                "     VALUES\n" +
                                                                                "           ((SELECT (CASE WHEN (MAX([sle_cdgo]) IS NULL)\n" +
                                                                                                        " THEN 1\n" +
                                                                                                        " ELSE (MAX([sle_cdgo])+1) END)AS [sle_cdgo]\n" +
                                                                                    " FROM ["+DB+"].[dbo].[solicitud_listado_equipo]),?,?,?,?,?,?,?,?,?,?,NULL,?,?)");
                        QuerySolicitudListadoEquipo.setString(1, Objeto.getCodigo());
                        QuerySolicitudListadoEquipo.setString(2, ListadoSolicitudesEquipos1.getTipoEquipo().getCodigo());
                        QuerySolicitudListadoEquipo.setString(3, ListadoSolicitudesEquipos1.getMarcaEquipo());
                        QuerySolicitudListadoEquipo.setString(4, ListadoSolicitudesEquipos1.getModeloEquipo());
                        QuerySolicitudListadoEquipo.setInt(5, ListadoSolicitudesEquipos1.getCantidad());
                        QuerySolicitudListadoEquipo.setString(6, ListadoSolicitudesEquipos1.getObservacacion());
                        QuerySolicitudListadoEquipo.setString(7, ListadoSolicitudesEquipos1.getFechaHoraInicio());
                        QuerySolicitudListadoEquipo.setString(8, ListadoSolicitudesEquipos1.getFechaHoraFin());
                        QuerySolicitudListadoEquipo.setFloat(9, ListadoSolicitudesEquipos1.getCantidadMinutos());
                        QuerySolicitudListadoEquipo.setString(10, ListadoSolicitudesEquipos1.getLaborRealizada().getCodigo());
                        //QuerySolicitudListadoEquipo.setString(11, "NULL");
                        QuerySolicitudListadoEquipo.setInt(11, ListadoSolicitudesEquipos1.getCentroCostoAuxiliar().getCodigo());
                        QuerySolicitudListadoEquipo.setString(12, ListadoSolicitudesEquipos1.getCompañia().getCodigo());
                        QuerySolicitudListadoEquipo.execute();
                    }
                }
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
                "           ,'SOLICITUD_EQUIPO'" +
                "           ,CONCAT (?,?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, Objeto.getCodigo());
                Query_Auditoria.setString(6, "Se registró una nueva solicitud de equipos en el sistema, con Código: ");
                Query_Auditoria.setString(7, Objeto.getCodigo());
                Query_Auditoria.execute();
                result=1;
            }
        }
        catch (SQLException sqlException ){   
            result=0;
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    } 
    public int solicitudEquipo_ActualizarEstado_General(SolicitudEquipo Objeto, Usuario us, int valorEstado, String mensaje) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement query;
            if(valorEstado==5){//debemos de cambiar el estado de confirmación a 0=RECHAZADA
                query= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[solicitud_equipo] set [se_estado_solicitud_equipo_cdgo]=?, [se_confirmacion_solicitud_equipo_cdgo]=0 WHERE [se_cdgo]=?");
            }else{
                query= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[solicitud_equipo] set [se_estado_solicitud_equipo_cdgo]=? WHERE [se_cdgo]=?");
            }
            query.setInt(1, valorEstado);
            query.setString(2, Objeto.getCodigo());
            query.execute();
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
                        "           ,'SOLICITUD_EQUIPO'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre ',?,' Código: ',?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, Objeto.getCodigo());
                Query_Auditoria.setString(6, " la Solicitud Equipo, ");
                Query_Auditoria.setString(7, Objeto.getCodigo()+" se actualizó a estado de "+mensaje);          
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
    /*
      @Modulo de Busqueda de las solicitudes.    
    */
    public ArrayList<SolicitudEquipo> buscarSolicitudes(String sql) throws SQLException{
        ArrayList<SolicitudEquipo> listadoObjetos = null;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            PreparedStatement query= conexion.prepareStatement("SELECT TOP 5000 "+
                                                            "       [se_cdgo] -- 1\n" +
                                                            "           -- Centro Operacion\n" +
                                                            "	        ,[se_cntro_oper_cdgo]-- 2\n" + 
                                                            "	        ,[co_cdgo]-- 3\n" +
                                                            "           ,[co_desc]-- 4\n" +
                                                            "	        ,CASE WHEN ([co_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [co_estad] --5\n" +
                                                            "      ,[se_fecha]-- 6\n" +
                                                            "	        -- Usuario\n" +
                                                            "           ,[se_usuario_realiza_cdgo]-- 7\n" +
                                                            "	        ,[us_cdgo]-- 8\n" +
                                                            "           ,[us_clave]-- 9\n" +
                                                            "           ,[us_nombres]-- 10\n" +
                                                            "           ,[us_apellidos]-- 11\n" +
                                                            "               -- Perfil de Usuario\n" +
                                                            "	            ,[us_perfil_cdgo]-- 12\n" +
                                                            "               ,[prf_cdgo]--13\n" +
                                                            "               ,[prf_desc]--14\n" +
                                                            "               ,CASE WHEN ([prf_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [prf_estad] --15\n" +
                                                            "	        ,[us_correo]-- 16\n" +
                                                            "	        ,CASE WHEN ([us_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [us_estad] --17\n" +
                                                            "      ,[se_fecha_registro]-- 18\n" +
                                                            "	        -- Estado de la solicitud\n" +
                                                            "           ,[se_estado_solicitud_equipo_cdgo]-- 19\n" +
                                                            "           ,[ese_cdgo]-- 20\n" +
                                                            "           ,[ese_desc]-- 21\n" +
                                                            "	        ,CASE WHEN ([ese_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [ese_estad] --22\n" +
                                                            "	   ,[se_fecha_confirmacion] --23\n"+
                                                            "      ,[se_usuario_confirma_cdgo]-- 24\n" +
                                                            "	   ,[se_confirmacion_solicitud_equipo_cdgo]-- 25\n" +
                                                            "  FROM ["+DB+"].[dbo].[solicitud_equipo]\n" +
                                                            "	  INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [se_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                            "	  INNER JOIN ["+DB+"].[dbo].[usuario] ON [se_usuario_realiza_cdgo]=[us_cdgo]\n" +
                                                            "	  INNER JOIN ["+DB+"].[dbo].[estado_solicitud_equipo] ON [se_estado_solicitud_equipo_cdgo]=[ese_cdgo]\n" +
                                                            "	  INNER JOIN ["+DB+"].[dbo].[perfil] ON [us_perfil_cdgo]=[prf_cdgo] "+ sql);
            resultSet= query.executeQuery();
            boolean validator=true;
            while(resultSet.next()){ 
                if(validator){
                    listadoObjetos = new ArrayList();
                    validator=false;
                }
                SolicitudEquipo Objeto = new SolicitudEquipo(); 
                Objeto.setCodigo(resultSet.getString(1));
                    CentroOperacion centroOperacion = new CentroOperacion();
                    centroOperacion.setCodigo(Integer.parseInt(resultSet.getString(3)));
                    centroOperacion.setDescripcion(resultSet.getString(4));
                    centroOperacion.setEstado(resultSet.getString(5));
                Objeto.setCentroOperacion(centroOperacion);
                Objeto.setFechaSolicitud(resultSet.getString(6));
                    Usuario usuario = new Usuario();
                    usuario.setCodigo(resultSet.getString(8));
                    usuario.setNombres(resultSet.getString(10));
                    usuario.setApellidos(resultSet.getString(11));
                    Perfil perfil = new Perfil();
                    perfil.setCodigo(resultSet.getString(13));
                    perfil.setDescripcion(resultSet.getString(14));
                    perfil.setEstado(resultSet.getString(15));
                    usuario.setPerfilUsuario(perfil);
                    usuario.setCorreo(resultSet.getString(16));
                    usuario.setEstado(resultSet.getString(17));              
                Objeto.setUsuarioRealizaSolicitud(usuario);
                Objeto.setFechaRegistro(resultSet.getString(18));
                
                    EstadoSolicitudEquipos estadoSolicitudEquipos = new EstadoSolicitudEquipos();
                    estadoSolicitudEquipos.setCodigo(resultSet.getString(20));
                    estadoSolicitudEquipos.setDescripcion(resultSet.getString(21));
                    estadoSolicitudEquipos.setEstado(resultSet.getString(22));             
                Objeto.setEstadoSolicitudEquipo(estadoSolicitudEquipos);
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las solicitudes", "Error", JOptionPane.ERROR_MESSAGE);
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }    
    public ArrayList<SolicitudEquipo> buscarSolicitudesPorUsuario(String sql,Usuario user) throws SQLException{
        ArrayList<SolicitudEquipo> listadoObjetos = null;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            PreparedStatement query= conexion.prepareStatement("SELECT TOP 5000 "+
                                                            "       [se_cdgo] -- 1\n" +
                                                            "           -- Centro Operacion\n" +
                                                            "	        ,[se_cntro_oper_cdgo]-- 2\n" + 
                                                            "	        ,[co_cdgo]-- 3\n" +
                                                            "           ,[co_desc]-- 4\n" +
                                                            "	        ,CASE WHEN ([co_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [co_estad] --5\n" +
                                                            "      ,[se_fecha]-- 6\n" +
                                                            "	        -- Usuario\n" +
                                                            "           ,[se_usuario_realiza_cdgo]-- 7\n" +
                                                            "	        ,[us_cdgo]-- 8\n" +
                                                            "           ,[us_clave]-- 9\n" +
                                                            "           ,[us_nombres]-- 10\n" +
                                                            "           ,[us_apellidos]-- 11\n" +
                                                            "               -- Perfil de Usuario\n" +
                                                            "	            ,[us_perfil_cdgo]-- 12\n" +
                                                            "               ,[prf_cdgo]--13\n" +
                                                            "               ,[prf_desc]--14\n" +
                                                            "               ,CASE WHEN ([prf_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [prf_estad] --15\n" +
                                                            "	        ,[us_correo]-- 16\n" +
                                                            "	        ,CASE WHEN ([us_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [us_estad] --17\n" +
                                                            "      ,[se_fecha_registro]-- 18\n" +
                                                            "	        -- Estado de la solicitud\n" +
                                                            "           ,[se_estado_solicitud_equipo_cdgo]-- 19\n" +
                                                            "           ,[ese_cdgo]-- 20\n" +
                                                            "           ,[ese_desc]-- 21\n" +
                                                            "	        ,CASE WHEN ([ese_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [ese_estad] --22\n" +
                                                            "	   ,[se_fecha_confirmacion] --23\n"+
                                                            "      ,[se_usuario_confirma_cdgo]-- 24\n" +
                                                            "	   ,[se_confirmacion_solicitud_equipo_cdgo]-- 25\n" +
                                                            "  FROM ["+DB+"].[dbo].[solicitud_equipo]\n" +
                                                            "	  INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [se_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                            "	  INNER JOIN ["+DB+"].[dbo].[usuario] ON [se_usuario_realiza_cdgo]=[us_cdgo]\n" +
                                                            "	  INNER JOIN ["+DB+"].[dbo].[estado_solicitud_equipo] ON [se_estado_solicitud_equipo_cdgo]=[ese_cdgo]\n" +
                                                            "	  INNER JOIN ["+DB+"].[dbo].[perfil] ON [us_perfil_cdgo]=[prf_cdgo] "+ sql+  " AND [se_usuario_realiza_cdgo]=?");
            query.setString(1, user.getCodigo());
            resultSet= query.executeQuery();
            boolean validator=true;
            while(resultSet.next()){ 
                if(validator){
                    listadoObjetos = new ArrayList();
                    validator=false;
                }
                SolicitudEquipo Objeto = new SolicitudEquipo(); 
                Objeto.setCodigo(resultSet.getString(1));
                    CentroOperacion centroOperacion = new CentroOperacion();
                    centroOperacion.setCodigo(Integer.parseInt(resultSet.getString(3)));
                    centroOperacion.setDescripcion(resultSet.getString(4));
                    centroOperacion.setEstado(resultSet.getString(5));
                Objeto.setCentroOperacion(centroOperacion);
                Objeto.setFechaSolicitud(resultSet.getString(6));
                    Usuario usuario = new Usuario();
                    usuario.setCodigo(resultSet.getString(8));
                    usuario.setNombres(resultSet.getString(10));
                    usuario.setApellidos(resultSet.getString(11));
                    Perfil perfil = new Perfil();
                    perfil.setCodigo(resultSet.getString(13));
                    perfil.setDescripcion(resultSet.getString(14));
                    perfil.setEstado(resultSet.getString(15));
                    usuario.setPerfilUsuario(perfil);
                    usuario.setCorreo(resultSet.getString(16));
                    usuario.setEstado(resultSet.getString(17));              
                Objeto.setUsuarioRealizaSolicitud(usuario);
                Objeto.setFechaRegistro(resultSet.getString(18));
                
                    EstadoSolicitudEquipos estadoSolicitudEquipos = new EstadoSolicitudEquipos();
                    estadoSolicitudEquipos.setCodigo(resultSet.getString(20));
                    estadoSolicitudEquipos.setDescripcion(resultSet.getString(21));
                    estadoSolicitudEquipos.setEstado(resultSet.getString(22));             
                Objeto.setEstadoSolicitudEquipo(estadoSolicitudEquipos);
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las solicitudes", "Error", JOptionPane.ERROR_MESSAGE);
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }
    public SolicitudEquipo buscarSolicitudEquipoParticular(SolicitudEquipo solicitudEquipo){
        SolicitudEquipo Objeto = null;
        ArrayList<SolicitudListadoEquipo> listadoObjetos = new ArrayList<>();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            PreparedStatement query= conexion.prepareStatement("SELECT	 [se_cdgo] -- 1\n" +
                        "		,[se_cntro_oper_cdgo]-- 2\n" +
                        "				-- Centro Operacion\n" +
                        "				,[co_cdgo]-- 3\n" +
                        "				,[co_desc]-- 4\n" +
                        "				,CASE WHEN ([co_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [co_estad] --5\n" +
                        "		,[se_fecha]-- 6\n" +
                        "		-- Usuario\n" +
                        "		,[se_usuario_realiza_cdgo]-- 7\n" +
                        "				,[us_cdgo]-- 8\n" +
                        "				,[us_clave]-- 9\n" +
                        "				,[us_nombres]-- 10\n" +
                        "				,[us_apellidos]-- 11\n" +
                        "				-- Perfil de Usuario\n" +
                        "				,[us_perfil_cdgo]-- 12\n" +
                        "						,[prf_cdgo]--13\n" +
                        "						,[prf_desc]--14\n" +
                        "						,CASE WHEN ([prf_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [prf_estad] --15\n" +
                        "				,[us_correo]-- 16\n" +
                        "				,CASE WHEN ([us_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [us_estad] --17\n" +
                        "		,[se_usuario_confirma_cdgo]-- 18\n" +
                        "		,[se_fecha_registro]-- 19\n" +
                        "		,[se_fecha_confirmacion]-- 20\n" +
                        "		,[se_estado_solicitud_equipo_cdgo]-- 21\n" +
                        "				-- Estado de la solicitud\n" +
                        "				,[ese_cdgo]-- 22\n" +
                        "				,[ese_desc]-- 23\n" +
                        "				,CASE WHEN ([ese_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [ese_estad] --24\n" +
                        "		-- Solicitudes_Listado_Equipos\n" +
                        "		,[sle_cdgo] --25\n" +
                        "		,[sle_solicitud_equipo_cdgo]--26\n" +
                        "		,[sle_tipo_equipo_cdgo]--27\n" +
                        "				,[te_cdgo]--28\n" +
                        "				,[te_desc]--29\n" +
                        "				,CASE WHEN ([te_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [te_estad]--30\n" +
                        "		,[sle_marca_equipo]--31\n" +
                        "		,[sle_modelo_equipo]--32\n" +
                        "		,[sle_cant_equip]--33\n" +
                        "		,[sle_observ]--34\n" +
                        "		,[sle_fecha_hora_inicio]--35\n" +
                        "		,[sle_fecha_hora_fin]--36\n" +
                        "		,[sle_cant_minutos]--37\n" +
                        "		,[sle_labor_realizada_cdgo]--38\n" +
                        "				-- Labor Realizada\n" +
                        "				,[lr_cdgo]--39\n" +
                        "				,[lr_desc]--40\n" +
                        "				,CASE WHEN ([lr_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [lr_estad]	--41		\n" +
                        "		,[sle_motonave_cdgo]--42\n" +
                        "				-- Motonave\n" +
                        "				,CASE WHEN ([mn_cdgo] IS NULL) THEN 'NULL' ELSE [mn_cdgo] END AS [mn_cdgo]	--43\n" +
                        "                        	,CASE WHEN ([mn_desc] IS NULL) THEN 'NULL' ELSE [mn_desc] END AS [mn_desc]	--44\n" +
                        "				,CASE WHEN ([mn_estad]IS NULL) \n" +
                        "						THEN 'NULL' ELSE \n" +
                        "                                               CASE WHEN ([mn_estad]=1) \n" +
                        "						THEN 'ACTIVO' \n" +
                        "						ELSE 'INACTIVO' END END AS [mn_estad]	--45 \n" +
                        "		,[sle_cntro_cost_auxiliar_cdgo]--46\n" +
                        "				-- Centro de Costo Auxiliar\n" +
                        "				,[cca_cdgo]--47\n"+
                        "				,[cca_cntro_cost_subcentro_cdgo]--48\n" +
                        "						-- Centro de Costo Subcentro\n" +
                        "						,[ccs_cdgo]--49\n" +
                        "						,[ccs_desc]--50\n" +
                        "						,CASE WHEN ([ccs_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [ccs_estad]	--51\n" +
                        "				,[cca_desc]--52\n" +
                        "				,CASE WHEN ([cca_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [cca_estad]	--53\n" +
                        "		,[sle_compania_cdgo]--54\n" +
                        "				-- Compañia\n" +
                        "				,[cp_cdgo]--55\n" +
                        "				,[cp_desc]--56\n" +
                        "				,CASE WHEN ([cp_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [cp_estad]	--57\n" +
                        "  FROM ["+DB+"].[dbo].[solicitud_equipo]\n" +
                        "		INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [se_cntro_oper_cdgo]=[co_cdgo]\n" +
                        "		INNER JOIN ["+DB+"].[dbo].[usuario] ON [se_usuario_realiza_cdgo]=[us_cdgo]\n" +
                        "		INNER JOIN ["+DB+"].[dbo].[estado_solicitud_equipo] ON [se_estado_solicitud_equipo_cdgo]=[ese_cdgo]\n" +
                        "		INNER JOIN ["+DB+"].[dbo].[perfil] ON [us_perfil_cdgo]=[prf_cdgo]\n" +
                        "		INNER JOIN ["+DB+"].[dbo].[solicitud_listado_equipo] ON [sle_solicitud_equipo_cdgo]=[se_cdgo]\n" +
                        "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] ON [sle_tipo_equipo_cdgo]=[te_cdgo]\n" +
                        "		INNER JOIN ["+DB+"].[dbo].[labor_realizada] ON [sle_labor_realizada_cdgo]=[lr_cdgo]\n" +
                        "		LEFT  JOIN ["+DB+"].[dbo].[motonave] ON [sle_motonave_cdgo]=[mn_cdgo] \n" +
                        "		INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [sle_cntro_cost_auxiliar_cdgo]=[cca_cdgo]\n" +
                        "		INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
                        "		INNER JOIN ["+DB+"].[dbo].[compania] ON [sle_compania_cdgo]=[cp_cdgo] WHERE [se_cdgo]=?");
            query.setString(1, solicitudEquipo.getCodigo());
            resultSet= query.executeQuery();
            boolean validator=true;
            while(resultSet.next()){ 
                if(validator){
                    validator=false;
                    Objeto = new SolicitudEquipo();
                    Objeto.setCodigo(resultSet.getString(1));
                        CentroOperacion centroOperacion = new CentroOperacion();
                        centroOperacion.setCodigo(Integer.parseInt(resultSet.getString(3)));
                        centroOperacion.setDescripcion(resultSet.getString(4));
                        centroOperacion.setEstado(resultSet.getString(5));
                    Objeto.setCentroOperacion(centroOperacion);
                    Objeto.setFechaSolicitud(resultSet.getString(6));
                        Usuario usuario = new Usuario();
                        usuario.setCodigo(resultSet.getString(8));
                        usuario.setNombres(resultSet.getString(10));
                        usuario.setApellidos(resultSet.getString(11));
                        Perfil perfil = new Perfil();
                        perfil.setCodigo(resultSet.getString(13));
                        perfil.setDescripcion(resultSet.getString(14));
                        perfil.setEstado(resultSet.getString(15));
                        usuario.setPerfilUsuario(perfil);
                        usuario.setCorreo(resultSet.getString(16));
                        usuario.setEstado(resultSet.getString(17));              
                    Objeto.setUsuarioRealizaSolicitud(usuario);
                    Objeto.setFechaRegistro(resultSet.getString(19));
                        EstadoSolicitudEquipos estadoSolicitudEquipos = new EstadoSolicitudEquipos();
                        estadoSolicitudEquipos.setCodigo(resultSet.getString(22));
                        estadoSolicitudEquipos.setDescripcion(resultSet.getString(23));
                        estadoSolicitudEquipos.setEstado(resultSet.getString(24));             
                    Objeto.setEstadoSolicitudEquipo(estadoSolicitudEquipos);
                }
                SolicitudListadoEquipo solicitudListadoEquipo = new SolicitudListadoEquipo();
                solicitudListadoEquipo.setCodigo(resultSet.getString(25));
                    TipoEquipo tipoEquipo = new TipoEquipo();
                    tipoEquipo.setCodigo(resultSet.getString(28));
                    tipoEquipo.setDescripcion(resultSet.getString(29));
                    tipoEquipo.setEstado(resultSet.getString(30));
                solicitudListadoEquipo.setTipoEquipo(tipoEquipo);
                solicitudListadoEquipo.setMarcaEquipo(resultSet.getString(31));
                solicitudListadoEquipo.setModeloEquipo(resultSet.getString(32));
                solicitudListadoEquipo.setCantidad(resultSet.getInt(33));
                solicitudListadoEquipo.setObservacacion(resultSet.getString(34));
                solicitudListadoEquipo.setFechaHoraInicio(resultSet.getString(35));
                solicitudListadoEquipo.setFechaHoraFin(resultSet.getString(36));
                solicitudListadoEquipo.setCantidadMinutos(resultSet.getInt(37));
                    LaborRealizada laborRealizada = new LaborRealizada();
                    laborRealizada.setCodigo(resultSet.getString(39));
                    laborRealizada.setDescripcion(resultSet.getString(40));
                    laborRealizada.setEstado(resultSet.getString(41));
                solicitudListadoEquipo.setLaborRealizada(laborRealizada);
                Motonave motonave= null;
                if(!(resultSet.getString(43).equals("NULL"))){
                    System.out.println("No fue nulo");
                    motonave= new Motonave();
                    motonave.setCodigo(resultSet.getString(43));
                    motonave.setDescripcion(resultSet.getString(44));
                    motonave.setEstado(resultSet.getString(45)); 
                }else{
                    System.out.println("fue nulo");
                    motonave= new Motonave();
                    motonave.setCodigo("NULL");
                    motonave.setDescripcion("NULL");
                    motonave.setEstado("NULL"); 
                }
                solicitudListadoEquipo.setMotonave(motonave);
                    CentroCostoSubCentro centroCostoSubCentro = new CentroCostoSubCentro();
                    //System.out.println("ccs_>"+resultSet.getString(49));
                    //centroCostoSubCentro.setCodigo(3);
                    centroCostoSubCentro.setCodigo(resultSet.getInt(49));
                    centroCostoSubCentro.setDescripcion(resultSet.getString(50));
                    centroCostoSubCentro.setEstado(resultSet.getString(51));
                    CentroCostoAuxiliar centroCostoAuxiliar = new CentroCostoAuxiliar();
                    centroCostoAuxiliar.setCodigo(resultSet.getInt(47));
                    centroCostoAuxiliar.setDescripcion(resultSet.getString(52));
                    centroCostoAuxiliar.setEstado(resultSet.getString(53));
                    centroCostoAuxiliar.setCentroCostoSubCentro(centroCostoSubCentro);
                solicitudListadoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar);    
                    Compañia compañia = new Compañia();
                    compañia.setCodigo(resultSet.getString(55));
                    compañia.setDescripcion(resultSet.getString(56));
                    compañia.setEstado(resultSet.getString(57));
                    //compañia.setEstado("2");
                solicitudListadoEquipo.setCompañia(compañia);
                listadoObjetos.add(solicitudListadoEquipo);   
            }
            if(Objeto != null){
                Objeto.setListadoSolicitudesEquipos(listadoObjetos);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las solicitudes de Equipos en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return Objeto;
    }
    
    /**
     * Modulo de Confirmación de Solicitudes Equipos
     * @return 
    **/
    public ArrayList<ConfirmacionSolicitudEquipos> buscarConfirmaciones(){
        ArrayList<ConfirmacionSolicitudEquipos> listadoConfirmacionSolicitudEquipos = null;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            PreparedStatement query= conexion.prepareStatement("SELECT  [cse_cdgo]\n" +
                                                                "      ,[cse_desc]\n" +
                                                                "      ,[cse_estad]\n" +
                                                                "  FROM ["+DB+"].[dbo].[confirmacion_solicitud_equipo] ORDER BY  [cse_cdgo] DESC");
            resultSet= query.executeQuery();
            boolean validator=true;
            while(resultSet.next()){ 
                if(validator){
                    listadoConfirmacionSolicitudEquipos = new ArrayList();
                    validator=false;
                }
                ConfirmacionSolicitudEquipos confirmacionSolicitudEquipos = new ConfirmacionSolicitudEquipos(); 
                confirmacionSolicitudEquipos.setCodigo(resultSet.getString(1));
                confirmacionSolicitudEquipos.setDescripcion(resultSet.getString(2));
                confirmacionSolicitudEquipos.setEstado(resultSet.getString(3));             
                listadoConfirmacionSolicitudEquipos.add(confirmacionSolicitudEquipos);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las confirmaciones de Solicitudes de Equipos", "Error", JOptionPane.ERROR_MESSAGE);
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoConfirmacionSolicitudEquipos;
    }    
    public int modificarEstadoConfirmacion(SolicitudEquipo SolicitudEquipo1, Usuario us, ConfirmacionSolicitudEquipos ConfirmacionSolicitudEquipos1) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement query;//(SELECT SYSDATETIME())
            
            query= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[solicitud_equipo] "+
                            "SET [se_fecha_confirmacion]=(SELECT SYSDATETIME()), [se_usuario_confirma_cdgo]=?, [se_confirmacion_solicitud_equipo_cdgo]=? "+
                            " WHERE [se_cdgo]=?");
            
            query.setString(1, us.getCodigo());
            query.setString(2, ConfirmacionSolicitudEquipos1.getCodigo());
            query.setString(3, SolicitudEquipo1.getCodigo());
            query.execute();
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
                        "           ,'ACTIVIDAD_OPERACION'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre ',?,' Código: ',?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, SolicitudEquipo1.getCodigo());
                Query_Auditoria.setString(6, " la Solicitud Equipo, ");
                Query_Auditoria.setString(7, SolicitudEquipo1.getCodigo()+" se actualizó a estado de "+ConfirmacionSolicitudEquipos1.getDescripcion()+" por "+us.getCodigo()+" Nombre: "+us.getNombres()+" "+us.getApellidos());          
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
}
