package ModuloEquipo.Controller2;

import ConnectionDB2.Conexion_DB_costos_vg;
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import Catalogo.Model.Motonave;
import ModuloEquipo.Model.AsignacionEquipo;
import Catalogo.Model.ClasificadorEquipo;
import Catalogo.Model.Compañia;
import Catalogo.Model.Equipo;
import Catalogo.Model.LaborRealizada;
import Catalogo.Model.Pertenencia;
import Catalogo.Model.ProveedorEquipo;
import ModuloEquipo.Model.SolicitudEquipo;
import ModuloEquipo.Model.SolicitudListadoEquipo;
import Catalogo.Model.TipoEquipo;
import ModuloEquipo.Model.ConfirmacionSolicitudEquipos;
import ModuloEquipo.Model.EstadoSolicitudEquipos;
import Sistema.Controller.ControlDB_Config;
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
 
public class ControlDB_AsignacionEquipo {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private String tipoConexion;
    public ControlDB_AsignacionEquipo(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    public boolean Asignacion_validarDisponibilidadEquipo(Equipo ObjetoI, String fechaInicio, String fechaFinal){
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);  
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno = false;
        try{
            ResultSet resultSetBuscar;
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT "+
            "       [ae_cdgo]-- 1\n" +
            "      ,[ae_cntro_oper_cdgo]-- 2\n" +
            "			--Centro Operacion\n" +
            "			,ae_cntro_oper.[co_cdgo]-- 3\n" +
            "			,ae_cntro_oper.[co_desc]-- 4\n" +
            "			,ae_cntro_oper.[co_estad]-- 5\n" +
            "      ,[ae_solicitud_listado_equipo_cdgo]-- 6\n" +
            "			-- Solicitud Listado Equipo\n" +
            "			,[sle_cdgo]-- 7\n" +
            "			,[sle_solicitud_equipo_cdgo]-- 8\n" +
            "				  --Solicitud Equipo\n" +
            "				  ,[se_cdgo]-- 9\n" +
            "				  ,[se_cntro_oper_cdgo]-- 10\n" +
            "						--CentroOperación SolicitudEquipo\n" +
            "						,se_cntro_oper.[co_cdgo]-- 11\n" +
            "						,se_cntro_oper.[co_desc]-- 12\n" +
            "						,se_cntro_oper.[co_estad]-- 13\n" +
            "				  ,[se_fecha]-- 14\n" +
            "				  ,[se_usuario_realiza_cdgo]-- 15\n" +
            "						--Usuario SolicitudEquipo\n" +
            "						,se_usuario_realiza.[us_cdgo]-- 16\n" +
            "						,se_usuario_realiza.[us_clave]-- 17\n" +
            "						,se_usuario_realiza.[us_nombres]-- 18\n" +
            "						,se_usuario_realiza.[us_apellidos]-- 19\n" +
            "						,se_usuario_realiza.[us_perfil_cdgo]-- 20\n" +
            "						,se_usuario_realiza.[us_correo]-- 21\n" +
            "						,se_usuario_realiza.[us_estad]-- 22\n" +
            "				  ,[se_fecha_registro]-- 23\n" +
            "				  ,[se_estado_solicitud_equipo_cdgo]-- 24\n" +
            "						--Estado de la solicitud\n" +
            "						,[ese_cdgo]-- 25\n" +
            "						,[ese_desc]-- 26\n" +
            "						,[ese_estad]-- 27\n" +
            "				  ,[se_fecha_confirmacion]-- 28\n" +
            "				  ,[se_usuario_confirma_cdgo]-- 29\n" +
            "						--Usuario SolicitudEquipo\n" +
            "						,se_usuario_confirma.[us_cdgo]--30 \n" +
            "						,se_usuario_confirma.[us_clave]-- 31\n" +
            "						,se_usuario_confirma.[us_nombres]-- 32\n" +
            "						,se_usuario_confirma.[us_apellidos]-- 33\n" +
            "						,se_usuario_confirma.[us_perfil_cdgo]-- 34\n" +
            "						,se_usuario_confirma.[us_correo]-- 35\n" +
            "						,se_usuario_confirma.[us_estad]-- 36\n" +
            "				  ,[se_confirmacion_solicitud_equipo_cdgo]-- 37\n" +
            "						--Confirmacion solicitudEquipo\n" +
            "						,[cse_cdgo]-- 38\n" +
            "						,[cse_desc]-- 39\n" +
            "						,[cse_estad]-- 40\n" +
            "			,[sle_tipo_equipo_cdgo]-- 41\n" +
            "				--Tipo de Equipo\n" +
            "				,sle_tipoEquipo.[te_cdgo]-- 42\n" +
            "				,sle_tipoEquipo.[te_desc]-- 43\n" +
            "				,sle_tipoEquipo.[te_estad]-- 44\n" +
            "			,[sle_marca_equipo]-- 45\n" +
            "			,[sle_modelo_equipo]-- 46\n" +
            "			,[sle_cant_equip]-- 47\n" +
            "			,[sle_observ]-- 48\n" +
            "			,[sle_fecha_hora_inicio]-- 49\n" +
            "			,[sle_fecha_hora_fin]-- 50\n" +
            "			,[sle_cant_minutos]-- 51\n" +
            "			,[sle_labor_realizada_cdgo]-- 52\n" +
            "			-- Labor Realizada\n" +
            "				  ,[lr_cdgo]-- 53\n" +
            "				  ,[lr_desc]-- 54\n" +
            "				  ,[lr_estad]-- 55\n" +
            "			,[sle_motonave_cdgo]-- 56\n" +
            "			--Motonave\n" +
            "				  ,[mn_cdgo]-- 57\n" +
            "				  ,[mn_desc]-- 58\n" +
            "				  ,[mn_estad]-- 59\n" +
            "			,[sle_cntro_cost_auxiliar_cdgo]-- 60\n" +
            "				--Centro Costo Auxiliar\n" +
            "				,[cca_cdgo]-- 61\n" +
            "				,[cca_cntro_cost_subcentro_cdgo]-- 62\n" +
            "					-- SubCentro de Costo\n" +
            "					,[ccs_cdgo]-- 63\n" +
            "					,[ccs_desc]-- 64\n" +
            "					,[ccs_estad]-- 65\n" +
            "				,[cca_desc]-- 66\n" +
            "				,[cca_estad]-- 67\n" +
            "			,[sle_compania_cdgo]-- 68\n" +
            "				--Compañia\n" +
            "				,[cp_cdgo]-- 69\n" +
            "				,[cp_desc]-- 70\n" +
            "				,[cp_estad]-- 71\n" +
            "      ,[ae_fecha_registro]-- 72\n" +
            "      ,[ae_fecha_hora_inicio]-- 73\n" +
            "      ,[ae_fecha_hora_fin]-- 74\n" +
            "      ,[ae_cant_minutos]-- 75\n" +
            "      ,[ae_equipo_cdgo]-- 76\n" +
            "			--Equipo\n" +
            "			,[eq_cdgo]-- 77\n" +
            "			,[eq_tipo_equipo_cdgo]-- 78\n" +
            "				--Tipo Equipo\n" +
            "				,eq_tipo_equipo.[te_cdgo]-- 79\n" +
            "				,eq_tipo_equipo.[te_desc]-- 80\n" +
            "				,eq_tipo_equipo.[te_estad]-- 81\n" +
            "			,[eq_codigo_barra]-- 82\n" +
            "			,[eq_referencia]-- 83\n" +
            "			,[eq_producto]-- 84\n" +
            "			,[eq_capacidad]-- 85\n" +
            "			,[eq_marca]-- 86\n" +
            "			,[eq_modelo]-- 87\n" +
            "			,[eq_serial]-- 88\n" +
            "			,[eq_desc]-- 89\n" +
            "			,[eq_clasificador1_cdgo]-- 90\n" +
            "				-- Clasificador 1\n" +
            "				,eq_clasificador1.[ce_cdgo]-- 91\n" +
            "			    ,eq_clasificador1.[ce_desc]-- 92\n" +
            "			    ,eq_clasificador1.[ce_estad]-- 93\n" +
            "			,[eq_clasificador2_cdgo]-- 94\n" +
            "			    -- Clasificador 2\n" +
            "				,eq_clasificador2.[ce_cdgo]-- 95\n" +
            "			    ,eq_clasificador2.[ce_desc]-- 96\n" +
            "			    ,eq_clasificador2.[ce_estad]-- 97\n" +
            "			,[eq_proveedor_equipo_cdgo]-- 98\n" +
            "				--Proveedor Equipo\n" +
            "				,[pe_cdgo]-- 99\n" +
            "				,[pe_nit]-- 100\n" +
            "				,[pe_desc]-- 101\n" +
            "				,[pe_estad]-- 102\n" +
            "			,[eq_equipo_pertenencia_cdgo]-- 103\n" +
            "				-- Equipo Pertenencia\n" +
            "				,eq_pertenencia.[ep_cdgo]-- 104\n" +
            "				,eq_pertenencia.[ep_desc]-- 105\n" +
            "				,eq_pertenencia.[ep_estad]-- 106\n" +
            "			,[eq_observ]-- 107\n" +
            "			,[eq_estad]-- 108\n" +
            "			,[eq_actvo_fijo_id]-- 109\n" +
            "			,[eq_actvo_fijo_referencia]-- 110\n" +
            "			,[eq_actvo_fijo_desc]-- 111\n" +
            "      ,[ae_equipo_pertenencia_cdgo]-- 112\n" +
            "		-- Equipo Pertenencia\n" +
            "				,ae_pertenencia.[ep_cdgo]-- 113\n" +
            "				,ae_pertenencia.[ep_desc]-- 114\n" +
            "				,ae_pertenencia.[ep_estad]-- 115\n" +
            "      ,[ae_cant_minutos_operativo]--116\n" +
            "      ,[ae_cant_minutos_parada]-- 117\n" +
            "      ,[ae_cant_minutos_total]-- 118\n" +
            "      ,[ae_estad]-- 119\n" +
            "  FROM ["+DB+"].[dbo].[asignacion_equipo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[solicitud_listado_equipo] ON [ae_solicitud_listado_equipo_cdgo]=[sle_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_oper] ae_cntro_oper ON [ae_cntro_oper_cdgo]=ae_cntro_oper.[co_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[solicitud_equipo] ON [sle_solicitud_equipo_cdgo]=[se_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_oper] se_cntro_oper ON [se_cntro_oper_cdgo]=se_cntro_oper.[co_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[usuario] se_usuario_realiza ON [se_usuario_realiza_cdgo]=se_usuario_realiza.[us_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[estado_solicitud_equipo] ON [se_estado_solicitud_equipo_cdgo]=[ese_cdgo]\n" +
            "		LEFT JOIN  ["+DB+"].[dbo].[usuario] se_usuario_confirma ON [se_usuario_realiza_cdgo]=se_usuario_confirma.[us_cdgo]\n" +
            "		LEFT JOIN  ["+DB+"].[dbo].[confirmacion_solicitud_equipo] ON [se_confirmacion_solicitud_equipo_cdgo]=[cse_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] sle_tipoEquipo ON [sle_tipo_equipo_cdgo]=sle_tipoEquipo.[te_cdgo]\n" +
            "		LEFT JOIN ["+DB+"].[dbo].[labor_realizada] ON [sle_labor_realizada_cdgo]=[lr_cdgo]\n" +
            "		LEFT  JOIN["+DB+"].[dbo].[motonave] ON [sle_motonave_cdgo]=[mn_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [sle_cntro_cost_auxiliar_cdgo]=[cca_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[compania] ON [sle_compania_cdgo]=[cp_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] eq_tipo_equipo ON [eq_tipo_equipo_cdgo]=eq_tipo_equipo.[te_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador1 ON [eq_clasificador1_cdgo]=eq_clasificador1.[ce_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador2 ON [eq_clasificador2_cdgo]=eq_clasificador2.[ce_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [eq_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[equipo_pertenencia] eq_pertenencia ON [eq_equipo_pertenencia_cdgo]=eq_pertenencia.[ep_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[equipo_pertenencia] ae_pertenencia ON [ae_equipo_pertenencia_cdgo]=ae_pertenencia.[ep_cdgo]\n" +
            "		 WHERE  [ae_equipo_cdgo]=? AND\n" +
                                                                    "  ( '"+fechaInicio+"' BETWEEN [ae_fecha_hora_inicio] AND [ae_fecha_hora_fin] " +
                                                                    "   OR '"+fechaFinal+"' BETWEEN [ae_fecha_hora_inicio] AND [ae_fecha_hora_fin] "+
                                                                    "   OR [ae_fecha_hora_inicio] BETWEEN '"+fechaInicio+"' AND '"+fechaFinal+"' "+
                                                                    "   OR [ae_fecha_hora_fin] BETWEEN '"+fechaInicio+"' AND '"+fechaFinal+"') AND ([se_confirmacion_solicitud_equipo_cdgo] IS NULL OR [se_confirmacion_solicitud_equipo_cdgo]=1)");
            queryBuscar.setString(1, ObjetoI.getCodigo());
            resultSetBuscar= queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                retorno=true;
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "No se pudo consultar la asignación del equipo","Error", JOptionPane.ERROR_MESSAGE);
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return retorno;
    }
    public boolean Asignacion_validarDisponibilidadEquipoPorListado(ArrayList<AsignacionEquipo> listadoAsignacionEquipo){
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);  
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno = false;
        for(AsignacionEquipo AsignacionEquipo1: listadoAsignacionEquipo){
            try{
                ResultSet resultSetBuscar;
               
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT "+
            "       [ae_cdgo]-- 1\n" +
            "      ,[ae_cntro_oper_cdgo]-- 2\n" +
            "			--Centro Operacion\n" +
            "			,ae_cntro_oper.[co_cdgo]-- 3\n" +
            "			,ae_cntro_oper.[co_desc]-- 4\n" +
            "			,ae_cntro_oper.[co_estad]-- 5\n" +
            "      ,[ae_solicitud_listado_equipo_cdgo]-- 6\n" +
            "			-- Solicitud Listado Equipo\n" +
            "			,[sle_cdgo]-- 7\n" +
            "			,[sle_solicitud_equipo_cdgo]-- 8\n" +
            "				  --Solicitud Equipo\n" +
            "				  ,[se_cdgo]-- 9\n" +
            "				  ,[se_cntro_oper_cdgo]-- 10\n" +
            "						--CentroOperación SolicitudEquipo\n" +
            "						,se_cntro_oper.[co_cdgo]-- 11\n" +
            "						,se_cntro_oper.[co_desc]-- 12\n" +
            "						,se_cntro_oper.[co_estad]-- 13\n" +
            "				  ,[se_fecha]-- 14\n" +
            "				  ,[se_usuario_realiza_cdgo]-- 15\n" +
            "						--Usuario SolicitudEquipo\n" +
            "						,se_usuario_realiza.[us_cdgo]-- 16\n" +
            "						,se_usuario_realiza.[us_clave]-- 17\n" +
            "						,se_usuario_realiza.[us_nombres]-- 18\n" +
            "						,se_usuario_realiza.[us_apellidos]-- 19\n" +
            "						,se_usuario_realiza.[us_perfil_cdgo]-- 20\n" +
            "						,se_usuario_realiza.[us_correo]-- 21\n" +
            "						,se_usuario_realiza.[us_estad]-- 22\n" +
            "				  ,[se_fecha_registro]-- 23\n" +
            "				  ,[se_estado_solicitud_equipo_cdgo]-- 24\n" +
            "						--Estado de la solicitud\n" +
            "						,[ese_cdgo]-- 25\n" +
            "						,[ese_desc]-- 26\n" +
            "						,[ese_estad]-- 27\n" +
            "				  ,[se_fecha_confirmacion]-- 28\n" +
            "				  ,[se_usuario_confirma_cdgo]-- 29\n" +
            "						--Usuario SolicitudEquipo\n" +
            "						,se_usuario_confirma.[us_cdgo]--30 \n" +
            "						,se_usuario_confirma.[us_clave]-- 31\n" +
            "						,se_usuario_confirma.[us_nombres]-- 32\n" +
            "						,se_usuario_confirma.[us_apellidos]-- 33\n" +
            "						,se_usuario_confirma.[us_perfil_cdgo]-- 34\n" +
            "						,se_usuario_confirma.[us_correo]-- 35\n" +
            "						,se_usuario_confirma.[us_estad]-- 36\n" +
            "				  ,[se_confirmacion_solicitud_equipo_cdgo]-- 37\n" +
            "						--Confirmacion solicitudEquipo\n" +
            "						,[cse_cdgo]-- 38\n" +
            "						,[cse_desc]-- 39\n" +
            "						,[cse_estad]-- 40\n" +
            "			,[sle_tipo_equipo_cdgo]-- 41\n" +
            "				--Tipo de Equipo\n" +
            "				,sle_tipoEquipo.[te_cdgo]-- 42\n" +
            "				,sle_tipoEquipo.[te_desc]-- 43\n" +
            "				,sle_tipoEquipo.[te_estad]-- 44\n" +
            "			,[sle_marca_equipo]-- 45\n" +
            "			,[sle_modelo_equipo]-- 46\n" +
            "			,[sle_cant_equip]-- 47\n" +
            "			,[sle_observ]-- 48\n" +
            "			,[sle_fecha_hora_inicio]-- 49\n" +
            "			,[sle_fecha_hora_fin]-- 50\n" +
            "			,[sle_cant_minutos]-- 51\n" +
            "			,[sle_labor_realizada_cdgo]-- 52\n" +
            "			-- Labor Realizada\n" +
            "				  ,[lr_cdgo]-- 53\n" +
            "				  ,[lr_desc]-- 54\n" +
            "				  ,[lr_estad]-- 55\n" +
            "			,[sle_motonave_cdgo]-- 56\n" +
            "			--Motonave\n" +
            "				  ,[mn_cdgo]-- 57\n" +
            "				  ,[mn_desc]-- 58\n" +
            "				  ,[mn_estad]-- 59\n" +
            "			,[sle_cntro_cost_auxiliar_cdgo]-- 60\n" +
            "				--Centro Costo Auxiliar\n" +
            "				,[cca_cdgo]-- 61\n" +
            "				,[cca_cntro_cost_subcentro_cdgo]-- 62\n" +
            "					-- SubCentro de Costo\n" +
            "					,[ccs_cdgo]-- 63\n" +
            "					,[ccs_desc]-- 64\n" +
            "					,[ccs_estad]-- 65\n" +
            "				,[cca_desc]-- 66\n" +
            "				,[cca_estad]-- 67\n" +
            "			,[sle_compania_cdgo]-- 68\n" +
            "				--Compañia\n" +
            "				,[cp_cdgo]-- 69\n" +
            "				,[cp_desc]-- 70\n" +
            "				,[cp_estad]-- 71\n" +
            "      ,[ae_fecha_registro]-- 72\n" +
            "      ,[ae_fecha_hora_inicio]-- 73\n" +
            "      ,[ae_fecha_hora_fin]-- 74\n" +
            "      ,[ae_cant_minutos]-- 75\n" +
            "      ,[ae_equipo_cdgo]-- 76\n" +
            "			--Equipo\n" +
            "			,[eq_cdgo]-- 77\n" +
            "			,[eq_tipo_equipo_cdgo]-- 78\n" +
            "				--Tipo Equipo\n" +
            "				,eq_tipo_equipo.[te_cdgo]-- 79\n" +
            "				,eq_tipo_equipo.[te_desc]-- 80\n" +
            "				,eq_tipo_equipo.[te_estad]-- 81\n" +
            "			,[eq_codigo_barra]-- 82\n" +
            "			,[eq_referencia]-- 83\n" +
            "			,[eq_producto]-- 84\n" +
            "			,[eq_capacidad]-- 85\n" +
            "			,[eq_marca]-- 86\n" +
            "			,[eq_modelo]-- 87\n" +
            "			,[eq_serial]-- 88\n" +
            "			,[eq_desc]-- 89\n" +
            "			,[eq_clasificador1_cdgo]-- 90\n" +
            "				-- Clasificador 1\n" +
            "				,eq_clasificador1.[ce_cdgo]-- 91\n" +
            "			    ,eq_clasificador1.[ce_desc]-- 92\n" +
            "			    ,eq_clasificador1.[ce_estad]-- 93\n" +
            "			,[eq_clasificador2_cdgo]-- 94\n" +
            "			    -- Clasificador 2\n" +
            "				,eq_clasificador2.[ce_cdgo]-- 95\n" +
            "			    ,eq_clasificador2.[ce_desc]-- 96\n" +
            "			    ,eq_clasificador2.[ce_estad]-- 97\n" +
            "			,[eq_proveedor_equipo_cdgo]-- 98\n" +
            "				--Proveedor Equipo\n" +
            "				,[pe_cdgo]-- 99\n" +
            "				,[pe_nit]-- 100\n" +
            "				,[pe_desc]-- 101\n" +
            "				,[pe_estad]-- 102\n" +
            "			,[eq_equipo_pertenencia_cdgo]-- 103\n" +
            "				-- Equipo Pertenencia\n" +
            "				,eq_pertenencia.[ep_cdgo]-- 104\n" +
            "				,eq_pertenencia.[ep_desc]-- 105\n" +
            "				,eq_pertenencia.[ep_estad]-- 106\n" +
            "			,[eq_observ]-- 107\n" +
            "			,[eq_estad]-- 108\n" +
            "			,[eq_actvo_fijo_id]-- 109\n" +
            "			,[eq_actvo_fijo_referencia]-- 110\n" +
            "			,[eq_actvo_fijo_desc]-- 111\n" +
            "      ,[ae_equipo_pertenencia_cdgo]-- 112\n" +
            "		-- Equipo Pertenencia\n" +
            "				,ae_pertenencia.[ep_cdgo]-- 113\n" +
            "				,ae_pertenencia.[ep_desc]-- 114\n" +
            "				,ae_pertenencia.[ep_estad]-- 115\n" +
            "      ,[ae_cant_minutos_operativo]--116\n" +
            "      ,[ae_cant_minutos_parada]-- 117\n" +
            "      ,[ae_cant_minutos_total]-- 118\n" +
            "      ,[ae_estad]-- 119\n" +
            "  FROM ["+DB+"].[dbo].[asignacion_equipo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[solicitud_listado_equipo] ON [ae_solicitud_listado_equipo_cdgo]=[sle_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_oper] ae_cntro_oper ON [ae_cntro_oper_cdgo]=ae_cntro_oper.[co_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[solicitud_equipo] ON [sle_solicitud_equipo_cdgo]=[se_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_oper] se_cntro_oper ON [se_cntro_oper_cdgo]=se_cntro_oper.[co_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[usuario] se_usuario_realiza ON [se_usuario_realiza_cdgo]=se_usuario_realiza.[us_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[estado_solicitud_equipo] ON [se_estado_solicitud_equipo_cdgo]=[ese_cdgo]\n" +
            "		LEFT JOIN  ["+DB+"].[dbo].[usuario] se_usuario_confirma ON [se_usuario_realiza_cdgo]=se_usuario_confirma.[us_cdgo]\n" +
            "		LEFT JOIN  ["+DB+"].[dbo].[confirmacion_solicitud_equipo] ON [se_confirmacion_solicitud_equipo_cdgo]=[cse_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] sle_tipoEquipo ON [sle_tipo_equipo_cdgo]=sle_tipoEquipo.[te_cdgo]\n" +
            "		LEFT JOIN ["+DB+"].[dbo].[labor_realizada] ON [sle_labor_realizada_cdgo]=[lr_cdgo]\n" +
            "		LEFT  JOIN["+DB+"].[dbo].[motonave] ON [sle_motonave_cdgo]=[mn_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [sle_cntro_cost_auxiliar_cdgo]=[cca_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[compania] ON [sle_compania_cdgo]=[cp_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] eq_tipo_equipo ON [eq_tipo_equipo_cdgo]=eq_tipo_equipo.[te_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador1 ON [eq_clasificador1_cdgo]=eq_clasificador1.[ce_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador2 ON [eq_clasificador2_cdgo]=eq_clasificador2.[ce_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [eq_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[equipo_pertenencia] eq_pertenencia ON [eq_equipo_pertenencia_cdgo]=eq_pertenencia.[ep_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[equipo_pertenencia] ae_pertenencia ON [ae_equipo_pertenencia_cdgo]=ae_pertenencia.[ep_cdgo]\n" +
            "		 WHERE  [ae_equipo_cdgo]=? AND\n" +
                                                                    "  ( '"+AsignacionEquipo1.getFechaHoraInicio()+"' BETWEEN [ae_fecha_hora_inicio] AND [ae_fecha_hora_fin] " +
                                                                    "   OR '"+AsignacionEquipo1.getFechaHoraFin()+"' BETWEEN [ae_fecha_hora_inicio] AND [ae_fecha_hora_fin] "+
                                                                    "   OR [ae_fecha_hora_inicio] BETWEEN '"+AsignacionEquipo1.getFechaHoraInicio()+"' AND '"+AsignacionEquipo1.getFechaHoraFin()+"' "+
                                                                    "   OR [ae_fecha_hora_fin] BETWEEN '"+AsignacionEquipo1.getFechaHoraInicio()+"' AND '"+AsignacionEquipo1.getFechaHoraFin()+"') AND ([se_confirmacion_solicitud_equipo_cdgo] IS NULL OR [se_confirmacion_solicitud_equipo_cdgo]=1)");
                queryBuscar.setString(1, AsignacionEquipo1.getEquipo().getCodigo());
                resultSetBuscar= queryBuscar.executeQuery();
                while(resultSetBuscar.next()){ 
                    retorno=true;
                }
            }catch (SQLException sqlException) {
                JOptionPane.showMessageDialog(null, "No se pudo consultar la asignación del equipo","Error", JOptionPane.ERROR_MESSAGE);
                sqlException.printStackTrace();
            } 
        }
        control.cerrarConexionBaseDatos();
        return retorno;
    }
    public int cantidadHorasProgramadas(String anoI,String mesI, String diaI, String horaI, String minutoI, String anoF,String mesF, String diaF, String horaF, String minutoF){
        String fechaInicio="'"+anoI+"-"+mesI+"-"+diaI+" "+horaI+":"+minutoI+":00.0"+"'";
        String fechaFinal="'"+anoF+"-"+mesF+"-"+diaF+" "+horaF+":"+minutoF+":00.0"+"'";
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        int retorno = 0;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT (DATEDIFF (MINUTE,  "+fechaInicio+", "+fechaFinal+"));");
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){
                retorno= resultSet.getInt(1);
                //retorno = retorno /60;
            }
        }catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null,"Error al tratar de comparar las fechas","Advertencia",JOptionPane.ERROR_MESSAGE);
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return retorno;
    }
    public int registrarAsignacion(ArrayList<AsignacionEquipo> listadoAsignacionEquipo, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            if(Asignacion_validarDisponibilidadEquipoPorListado(listadoAsignacionEquipo)){//True=Hay un equipo asignado en ese rango de fecha  False= Los equipos del listado se pueden grabar
                JOptionPane.showMessageDialog(null,"Hay equipos en el listado que ya fueron asignado en ese rango de fecha validar información","Error",JOptionPane.ERROR_MESSAGE);
                conexion= control.ConectarBaseDatos();
            }else{
                conexion= control.ConectarBaseDatos();
                for(AsignacionEquipo AsignacionEquipo1: listadoAsignacionEquipo){
                    PreparedStatement Query1= conexion.prepareStatement( "INSERT INTO ["+DB+"].[dbo].[asignacion_equipo]\n" +
                        "           ([ae_cdgo]\n" +
                        "           ,[ae_cntro_oper_cdgo]\n" +
                        "           ,[ae_solicitud_listado_equipo_cdgo]\n" +
                        "           ,[ae_fecha_registro]\n" +
                        "           ,[ae_fecha_hora_inicio]\n" +
                        "           ,[ae_fecha_hora_fin]\n" +
                        "           ,[ae_cant_minutos]\n" +
                        "           ,[ae_equipo_cdgo]\n" +
                        "           ,[ae_equipo_pertenencia_cdgo]\n" +
                        "           ,[ae_cant_minutos_operativo]\n" +
                        "           ,[ae_cant_minutos_parada]\n" +
                        "           ,[ae_cant_minutos_total]\n" +
                        "           ,[ae_estad])\n" +
                        "     VALUES\n" +
                        "           ((SELECT (CASE WHEN (MAX([ae_cdgo]) IS NULL)\n" +
                        "                 THEN 1\n" +
                        "                 ELSE (MAX([ae_cdgo])+1) END)AS [ae_cdgo] FROM ["+DB+"].[dbo].[asignacion_equipo]),? ,? ,(SELECT CONVERT(DATE, GETDATE())),?,?,? ,? ,? ,?,?,? ,?)");
                    Query1.setInt(1, AsignacionEquipo1.getCentroOperacion().getCodigo());
                    Query1.setString(2, AsignacionEquipo1.getSolicitudListadoEquipo().getCodigo());
                    Query1.setString(3, AsignacionEquipo1.getFechaHoraInicio());
                    Query1.setString(4, AsignacionEquipo1.getFechaHoraFin());
                    Query1.setString(5, AsignacionEquipo1.getCantidadMinutosProgramados());
                    Query1.setString(6, AsignacionEquipo1.getEquipo().getCodigo());
                    Query1.setString(7, AsignacionEquipo1.getPertenencia().getCodigo());
                    Query1.setString(8, "0");//ae_cant_minutos_operativa
                    Query1.setString(9, "0");//ae_cant_minutos_parada
                    Query1.setString(10, "0");//ae_cant_minutos_total
                    Query1.setString(11, "1");//ae_estad 1=ACTIVO  0=INACTIVO
                    Query1.execute();        
                    result=1;
                    if(result==1){
                        result=0;
                     //Sacamos el valor del codigo de la ultima asignación de equipo para registrar en auditoria
                        PreparedStatement Query_Maximo= conexion.prepareStatement("SELECT MAX(ae_cdgo) FROM ["+DB+"].[dbo].[asignacion_equipo];");
                        ResultSet resultSet= Query_Maximo.executeQuery();
                        while(resultSet.next()){ 
                            if(resultSet.getString(1) != null){
                                AsignacionEquipo1.setCodigo(resultSet.getString(1));
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
                                        "           ,'ASIGNACION_EQUIPOS'" +
                                        "           ,CONCAT (?,?));");
                        Query_Auditoria.setString(1, us.getCodigo());
                        Query_Auditoria.setString(2, namePc);
                        Query_Auditoria.setString(3, ipPc);
                        Query_Auditoria.setString(4, macPC);
                        Query_Auditoria.setString(5, AsignacionEquipo1.getCodigo());
                        Query_Auditoria.setString(6, "Se registró una nueva asignación de equipos en el sistema, con Código: ");
                        Query_Auditoria.setString(7, AsignacionEquipo1.getCodigo()+
                                                    " CentroOperacion: "+AsignacionEquipo1.getCentroOperacion().getCodigo()+
                                                    " CódigoSolicitudEquipo: "+AsignacionEquipo1.getSolicitudListadoEquipo().getCodigo()+
                                                    " FechaHoraInicio: "+AsignacionEquipo1.getFechaHoraInicio()+
                                                    " FechaHoraFin: "+AsignacionEquipo1.getFechaHoraFin()+
                                                    " CantidadHorasProgramadas: "+AsignacionEquipo1.getCantidadMinutosProgramados()+
                                                    " CódigoEquipo: "+AsignacionEquipo1.getEquipo().getCodigo()+
                                                    " TipoEquipo: "+AsignacionEquipo1.getEquipo().getTipoEquipo().getDescripcion()+
                                                    " MarcaEquipo: "+AsignacionEquipo1.getEquipo().getMarca()+
                                                    " ModeloEquipo: "+AsignacionEquipo1.getEquipo().getModelo()+
                                                    " DescripciónEquipo: "+AsignacionEquipo1.getEquipo().getDescripcion()+
                                                    " PertenenciaEquipo: "+AsignacionEquipo1.getPertenencia().getDescripcion());
                        Query_Auditoria.execute();
                        result=1;
                    }            
                }   
            }
        }
        catch (SQLException sqlException ){   
            result=0;
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    } 
    public int inactivarAsignación(String codigoAsignacion, Usuario us, String observacion) throws FileNotFoundException, UnknownHostException, SocketException{
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        int result=0;
        try{  
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();           
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[asignacion_equipo] SET [ae_estad]=0 WHERE [ae_cdgo]=?");
            queryActualizar.setString(1,codigoAsignacion);
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
                        "           ,'ASIGNACION_EQUIPOS'" +
                        "           ,CONCAT('Se inactivo una asignación de Código:',?,' Razon de activación: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, codigoAsignacion);
                Query_Auditoria.setString(6, codigoAsignacion);
                Query_Auditoria.setString(7, observacion);
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
    public int activarAsignación(String codigoAsignacion, Usuario us, String observacion) throws FileNotFoundException, UnknownHostException, SocketException{
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        int result=0;
        try{  
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();           
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[asignacion_equipo] SET [ae_estad]=1 WHERE [ae_cdgo]=?");
            queryActualizar.setString(1,codigoAsignacion);
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
                        "           ,'ASIGNACION_EQUIPOS'" +
                        "           ,CONCAT('Se activo una asignación de Código:',?,' Razon de activación: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, codigoAsignacion);
                Query_Auditoria.setString(6, codigoAsignacion);
                Query_Auditoria.setString(7, observacion);
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
    
    public ArrayList<AsignacionEquipo> consultarAsignacion (SolicitudEquipo solicitudEquipo){    
        ArrayList<AsignacionEquipo> listadoAsignacionEquipo = new ArrayList<>();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            PreparedStatement query= conexion.prepareStatement("SELECT "+
            "       [ae_cdgo]-- 1\n" +
            "      ,[ae_cntro_oper_cdgo]-- 2\n" +
            "			--Centro Operacion\n" +
            "			,ae_cntro_oper.[co_cdgo]-- 3\n" +
            "			,ae_cntro_oper.[co_desc]-- 4\n" +
            "			,ae_cntro_oper.[co_estad]-- 5\n" +
            "      ,[ae_solicitud_listado_equipo_cdgo]-- 6\n" +
            "			-- Solicitud Listado Equipo\n" +
            "			,[sle_cdgo]-- 7\n" +
            "			,[sle_solicitud_equipo_cdgo]-- 8\n" +
            "				  --Solicitud Equipo\n" +
            "				  ,[se_cdgo]-- 9\n" +
            "				  ,[se_cntro_oper_cdgo]-- 10\n" +
            "						--CentroOperación SolicitudEquipo\n" +
            "						,se_cntro_oper.[co_cdgo]-- 11\n" +
            "						,se_cntro_oper.[co_desc]-- 12\n" +
            "						,se_cntro_oper.[co_estad]-- 13\n" +
            "				  ,[se_fecha]-- 14\n" +
            "				  ,[se_usuario_realiza_cdgo]-- 15\n" +
            "						--Usuario SolicitudEquipo\n" +
            "						,se_usuario_realiza.[us_cdgo]-- 16\n" +
            "						,se_usuario_realiza.[us_clave]-- 17\n" +
            "						,se_usuario_realiza.[us_nombres]-- 18\n" +
            "						,se_usuario_realiza.[us_apellidos]-- 19\n" +
            "						,se_usuario_realiza.[us_perfil_cdgo]-- 20\n" +
            "						,se_usuario_realiza.[us_correo]-- 21\n" +
            "						,se_usuario_realiza.[us_estad]-- 22\n" +
            "				  ,[se_fecha_registro]-- 23\n" +
            "				  ,[se_estado_solicitud_equipo_cdgo]-- 24\n" +
            "						--Estado de la solicitud\n" +
            "						,[ese_cdgo]-- 25\n" +
            "						,[ese_desc]-- 26\n" +
            "						,[ese_estad]-- 27\n" +
            "				  ,[se_fecha_confirmacion]-- 28\n" +
            "				  ,[se_usuario_confirma_cdgo]-- 29\n" +
            "						--Usuario SolicitudEquipo\n" +
            "						,se_usuario_confirma.[us_cdgo]--30 \n" +
            "						,se_usuario_confirma.[us_clave]-- 31\n" +
            "						,se_usuario_confirma.[us_nombres]-- 32\n" +
            "						,se_usuario_confirma.[us_apellidos]-- 33\n" +
            "						,se_usuario_confirma.[us_perfil_cdgo]-- 34\n" +
            "						,se_usuario_confirma.[us_correo]-- 35\n" +
            "						,se_usuario_confirma.[us_estad]-- 36\n" +
            "				  ,[se_confirmacion_solicitud_equipo_cdgo]-- 37\n" +
            "						--Confirmacion solicitudEquipo\n" +
            "						,[cse_cdgo]-- 38\n" +
            "						,[cse_desc]-- 39\n" +
            "						,[cse_estad]-- 40\n" +
            "			,[sle_tipo_equipo_cdgo]-- 41\n" +
            "				--Tipo de Equipo\n" +
            "				,sle_tipoEquipo.[te_cdgo]-- 42\n" +
            "				,sle_tipoEquipo.[te_desc]-- 43\n" +
            "				,sle_tipoEquipo.[te_estad]-- 44\n" +
            "			,[sle_marca_equipo]-- 45\n" +
            "			,[sle_modelo_equipo]-- 46\n" +
            "			,[sle_cant_equip]-- 47\n" +
            "			,[sle_observ]-- 48\n" +
            "			,[sle_fecha_hora_inicio]-- 49\n" +
            "			,[sle_fecha_hora_fin]-- 50\n" +
            "			,[sle_cant_minutos]-- 51\n" +
            "			,[sle_labor_realizada_cdgo]-- 52\n" +
            "			-- Labor Realizada\n" +
            "				  ,[lr_cdgo]-- 53\n" +
            "				  ,[lr_desc]-- 54\n" +
            "				  ,[lr_estad]-- 55\n" +
            "			,[sle_motonave_cdgo]-- 56\n" +
            "			--Motonave\n" +
            "				  ,[mn_cdgo]-- 57\n" +
            "				  ,[mn_desc]-- 58\n" +
            "				  ,[mn_estad]-- 59\n" +
            "			,[sle_cntro_cost_auxiliar_cdgo]-- 60\n" +
            "				--Centro Costo Auxiliar\n" +
            "				,[cca_cdgo]-- 61\n" +
            "				,[cca_cntro_cost_subcentro_cdgo]-- 62\n" +
            "					-- SubCentro de Costo\n" +
            "					,[ccs_cdgo]-- 63\n" +
            "					,[ccs_desc]-- 64\n" +
            "					,[ccs_estad]-- 65\n" +
            "				,[cca_desc]-- 66\n" +
            "				,[cca_estad]-- 67\n" +
            "			,[sle_compania_cdgo]-- 68\n" +
            "				--Compañia\n" +
            "				,[cp_cdgo]-- 69\n" +
            "				,[cp_desc]-- 70\n" +
            "				,[cp_estad]-- 71\n" +
            "      ,[ae_fecha_registro]-- 72\n" +
            "      ,[ae_fecha_hora_inicio]-- 73\n" +
            "      ,[ae_fecha_hora_fin]-- 74\n" +
            "      ,[ae_cant_minutos]-- 75\n" +
            "      ,[ae_equipo_cdgo]-- 76\n" +
            "			--Equipo\n" +
            "			,[eq_cdgo]-- 77\n" +
            "			,[eq_tipo_equipo_cdgo]-- 78\n" +
            "				--Tipo Equipo\n" +
            "				,eq_tipo_equipo.[te_cdgo]-- 79\n" +
            "				,eq_tipo_equipo.[te_desc]-- 80\n" +
            "				,eq_tipo_equipo.[te_estad]-- 81\n" +
            "			,[eq_codigo_barra]-- 82\n" +
            "			,[eq_referencia]-- 83\n" +
            "			,[eq_producto]-- 84\n" +
            "			,[eq_capacidad]-- 85\n" +
            "			,[eq_marca]-- 86\n" +
            "			,[eq_modelo]-- 87\n" +
            "			,[eq_serial]-- 88\n" +
            "			,[eq_desc]-- 89\n" +
            "			,[eq_clasificador1_cdgo]-- 90\n" +
            "				-- Clasificador 1\n" +
            "				,eq_clasificador1.[ce_cdgo]-- 91\n" +
            "			    ,eq_clasificador1.[ce_desc]-- 92\n" +
            "			    ,eq_clasificador1.[ce_estad]-- 93\n" +
            "			,[eq_clasificador2_cdgo]-- 94\n" +
            "			    -- Clasificador 2\n" +
            "				,eq_clasificador2.[ce_cdgo]-- 95\n" +
            "			    ,eq_clasificador2.[ce_desc]-- 96\n" +
            "			    ,eq_clasificador2.[ce_estad]-- 97\n" +
            "			,[eq_proveedor_equipo_cdgo]-- 98\n" +
            "				--Proveedor Equipo\n" +
            "				,[pe_cdgo]-- 99\n" +
            "				,[pe_nit]-- 100\n" +
            "				,[pe_desc]-- 101\n" +
            "				,[pe_estad]-- 102\n" +
            "			,[eq_equipo_pertenencia_cdgo]-- 103\n" +
            "				-- Equipo Pertenencia\n" +
            "				,eq_pertenencia.[ep_cdgo]-- 104\n" +
            "				,eq_pertenencia.[ep_desc]-- 105\n" +
            "				,eq_pertenencia.[ep_estad]-- 106\n" +
            "			,[eq_observ]-- 107\n" +
            "			,[eq_estad]-- 108\n" +
            "			,[eq_actvo_fijo_id]-- 109\n" +
            "			,[eq_actvo_fijo_referencia]-- 110\n" +
            "			,[eq_actvo_fijo_desc]-- 111\n" +
            "      ,[ae_equipo_pertenencia_cdgo]-- 112\n" +
            "		-- Equipo Pertenencia\n" +
            "				,ae_pertenencia.[ep_cdgo]-- 113\n" +
            "				,ae_pertenencia.[ep_desc]-- 114\n" +
            "				,ae_pertenencia.[ep_estad]-- 115\n" +
            "      ,[ae_cant_minutos_operativo]--116\n" +
            "      ,[ae_cant_minutos_parada]-- 117\n" +
            "      ,[ae_cant_minutos_total]-- 118\n" +
            "      ,[ae_estad]-- 119\n" +
            "  FROM ["+DB+"].[dbo].[asignacion_equipo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[solicitud_listado_equipo] ON [ae_solicitud_listado_equipo_cdgo]=[sle_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_oper] ae_cntro_oper ON [ae_cntro_oper_cdgo]=ae_cntro_oper.[co_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[solicitud_equipo] ON [sle_solicitud_equipo_cdgo]=[se_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_oper] se_cntro_oper ON [se_cntro_oper_cdgo]=se_cntro_oper.[co_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[usuario] se_usuario_realiza ON [se_usuario_realiza_cdgo]=se_usuario_realiza.[us_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[estado_solicitud_equipo] ON [se_estado_solicitud_equipo_cdgo]=[ese_cdgo]\n" +
            "		LEFT JOIN  ["+DB+"].[dbo].[usuario] se_usuario_confirma ON [se_usuario_realiza_cdgo]=se_usuario_confirma.[us_cdgo]\n" +
            "		LEFT JOIN  ["+DB+"].[dbo].[confirmacion_solicitud_equipo] ON [se_confirmacion_solicitud_equipo_cdgo]=[cse_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] sle_tipoEquipo ON [sle_tipo_equipo_cdgo]=sle_tipoEquipo.[te_cdgo]\n" +
            "		LEFT  JOIN ["+DB+"].[dbo].[labor_realizada] ON [sle_labor_realizada_cdgo]=[lr_cdgo]\n" +
            "		LEFT  JOIN["+DB+"].[dbo].[motonave] ON [sle_motonave_cdgo]=[mn_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [sle_cntro_cost_auxiliar_cdgo]=[cca_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[compania] ON [sle_compania_cdgo]=[cp_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] eq_tipo_equipo ON [eq_tipo_equipo_cdgo]=eq_tipo_equipo.[te_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador1 ON [eq_clasificador1_cdgo]=eq_clasificador1.[ce_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador2 ON [eq_clasificador2_cdgo]=eq_clasificador2.[ce_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [eq_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[equipo_pertenencia] eq_pertenencia ON [eq_equipo_pertenencia_cdgo]=eq_pertenencia.[ep_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[equipo_pertenencia] ae_pertenencia ON [ae_equipo_pertenencia_cdgo]=ae_pertenencia.[ep_cdgo]\n" +
            "		 WHERE [se_cdgo]=? ORDER BY   [ae_cdgo] ASC\n");
            query.setString(1, solicitudEquipo.getCodigo());
            resultSet= query.executeQuery();
            while(resultSet.next()){ 
                SolicitudListadoEquipo solicitudListadoEquipo = new SolicitudListadoEquipo();
                solicitudListadoEquipo.setCodigo(resultSet.getString(7));
                    TipoEquipo tipoEquipo = new TipoEquipo();
                    tipoEquipo.setCodigo(resultSet.getString(42));
                    tipoEquipo.setDescripcion(resultSet.getString(43));
                    tipoEquipo.setEstado(resultSet.getString(44));
                solicitudListadoEquipo.setTipoEquipo(tipoEquipo);
                solicitudListadoEquipo.setMarcaEquipo(resultSet.getString(45));
                solicitudListadoEquipo.setModeloEquipo(resultSet.getString(46));
                solicitudListadoEquipo.setCantidad(resultSet.getInt(47));
                solicitudListadoEquipo.setObservacacion(resultSet.getString(48));
                solicitudListadoEquipo.setFechaHoraInicio(resultSet.getString(49));
                solicitudListadoEquipo.setFechaHoraFin(resultSet.getString(50));
                solicitudListadoEquipo.setCantidadMinutos(resultSet.getInt(51));
                    LaborRealizada laborRealizada = new LaborRealizada();
                    laborRealizada.setCodigo(resultSet.getString(53));
                    laborRealizada.setDescripcion(resultSet.getString(54));
                    laborRealizada.setEstado(resultSet.getString(55));
                solicitudListadoEquipo.setLaborRealizada(laborRealizada);
                Motonave motonave= null;
                if(!(resultSet.getString(57)== null)){
                    System.out.println("No fue nulo");
                    motonave= new Motonave();
                    motonave.setCodigo(resultSet.getString(57));
                    motonave.setDescripcion(resultSet.getString(58));
                    motonave.setEstado(resultSet.getString(59)); 
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
                    centroCostoSubCentro.setCodigo(resultSet.getInt(63));
                    centroCostoSubCentro.setDescripcion(resultSet.getString(64));
                    centroCostoSubCentro.setEstado(resultSet.getString(65));
                    CentroCostoAuxiliar centroCostoAuxiliar = new CentroCostoAuxiliar();
                    centroCostoAuxiliar.setCodigo(resultSet.getInt(61));
                    centroCostoAuxiliar.setDescripcion(resultSet.getString(66));
                    centroCostoAuxiliar.setEstado(resultSet.getString(67));
                    centroCostoAuxiliar.setCentroCostoSubCentro(centroCostoSubCentro);
                solicitudListadoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar);    
                    Compañia compañia = new Compañia();
                    compañia.setCodigo(resultSet.getString(69));
                    compañia.setDescripcion(resultSet.getString(70));
                    compañia.setEstado(resultSet.getString(71));
                    //compañia.setEstado("2");
                solicitudListadoEquipo.setCompañia(compañia);
                
                AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                asignacionEquipo.setCodigo(resultSet.getString(1));
                    CentroOperacion CentroOperacionAsignacion= new CentroOperacion();
                    CentroOperacionAsignacion.setCodigo(Integer.parseInt(resultSet.getString(3)));
                    CentroOperacionAsignacion.setDescripcion(resultSet.getString(4));
                    CentroOperacionAsignacion.setEstado(resultSet.getString(5));
                asignacionEquipo.setCentroOperacion(CentroOperacionAsignacion);
                asignacionEquipo.setSolicitudListadoEquipo(solicitudListadoEquipo);
                asignacionEquipo.setFechaRegistro(resultSet.getString(72));
                asignacionEquipo.setFechaHoraInicio(resultSet.getString(73));
                asignacionEquipo.setFechaHoraFin(resultSet.getString(74));
                asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(75));
                    Equipo equipo = new Equipo();
                    equipo.setCodigo(resultSet.getString(77));
                    equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(79),resultSet.getString(80),resultSet.getString(81)));
                    equipo.setCodigo_barra(resultSet.getString(82));
                    equipo.setReferencia(resultSet.getString(83));
                    equipo.setProducto(resultSet.getString(84));
                    equipo.setCapacidad(resultSet.getString(85));
                    equipo.setMarca(resultSet.getString(86));
                    equipo.setModelo(resultSet.getString(87));
                    equipo.setSerial(resultSet.getString(88));
                    equipo.setDescripcion(resultSet.getString(89));
                    equipo.setClasificador1(new ClasificadorEquipo(resultSet.getString(91),resultSet.getString(92),resultSet.getString(93)));
                    equipo.setClasificador2(new ClasificadorEquipo(resultSet.getString(95),resultSet.getString(96),resultSet.getString(97)));
                    equipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(99),resultSet.getString(100),resultSet.getString(101),resultSet.getString(102)));
                    equipo.setPertenenciaEquipo(new Pertenencia(resultSet.getString(104),resultSet.getString(105),resultSet.getString(106)));
                    equipo.setObservacion(resultSet.getString(107));
                    equipo.setEstado(resultSet.getString(108));
                    equipo.setActivoFijo_codigo(resultSet.getString(109));
                    equipo.setActivoFijo_referencia(resultSet.getString(110));
                    equipo.setActivoFijo_descripcion(resultSet.getString(111));
                asignacionEquipo.setEquipo(equipo);
                    Pertenencia pertenencia = new Pertenencia();
                    pertenencia.setCodigo(resultSet.getString(113));
                    pertenencia.setDescripcion(resultSet.getString(114));
                    pertenencia.setEstado(resultSet.getString(115));
                asignacionEquipo.setPertenencia(pertenencia);
                asignacionEquipo.setCantidadMinutosOperativo(resultSet.getString(116));
                asignacionEquipo.setCantidadMinutosParada(resultSet.getString(117));
                asignacionEquipo.setCantidadMinutosTotal(resultSet.getString(118));
                asignacionEquipo.setEstado(resultSet.getString(119));
                listadoAsignacionEquipo.add(asignacionEquipo);   
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las asignaciones de equipos en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoAsignacionEquipo;
    }
    public ArrayList<AsignacionEquipo> consultarProgramacionDeAsignacion (String script){    
        ArrayList<AsignacionEquipo> listadoAsignacionEquipo = new ArrayList<>();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            PreparedStatement query= conexion.prepareStatement("SELECT "+
            "       [ae_cdgo]-- 1\n" +
            "      ,[ae_cntro_oper_cdgo]-- 2\n" +
            "			--Centro Operacion\n" +
            "			,ae_cntro_oper.[co_cdgo]-- 3\n" +
            "			,ae_cntro_oper.[co_desc]-- 4\n" +
            "			,ae_cntro_oper.[co_estad]-- 5\n" +
            "      ,[ae_solicitud_listado_equipo_cdgo]-- 6\n" +
            "			-- Solicitud Listado Equipo\n" +
            "			,[sle_cdgo]-- 7\n" +
            "			,[sle_solicitud_equipo_cdgo]-- 8\n" +
            "				  --Solicitud Equipo\n" +
            "				  ,[se_cdgo]-- 9\n" +
            "				  ,[se_cntro_oper_cdgo]-- 10\n" +
            "						--CentroOperación SolicitudEquipo\n" +
            "						,se_cntro_oper.[co_cdgo]-- 11\n" +
            "						,se_cntro_oper.[co_desc]-- 12\n" +
            "						,se_cntro_oper.[co_estad]-- 13\n" +
            "				  ,[se_fecha]-- 14\n" +
            "				  ,[se_usuario_realiza_cdgo]-- 15\n" +
            "						--Usuario SolicitudEquipo\n" +
            "						,se_usuario_realiza.[us_cdgo]-- 16\n" +
            "						,se_usuario_realiza.[us_clave]-- 17\n" +
            "						,se_usuario_realiza.[us_nombres]-- 18\n" +
            "						,se_usuario_realiza.[us_apellidos]-- 19\n" +
            "						,se_usuario_realiza.[us_perfil_cdgo]-- 20\n" +
            "						,se_usuario_realiza.[us_correo]-- 21\n" +
            "						,se_usuario_realiza.[us_estad]-- 22\n" +
            "				  ,[se_fecha_registro]-- 23\n" +
            "				  ,[se_estado_solicitud_equipo_cdgo]-- 24\n" +
            "						--Estado de la solicitud\n" +
            "						,[ese_cdgo]-- 25\n" +
            "						,[ese_desc]-- 26\n" +
            "						,[ese_estad]-- 27\n" +
            "				  ,[se_fecha_confirmacion]-- 28\n" +
            "				  ,[se_usuario_confirma_cdgo]-- 29\n" +
            "						--Usuario SolicitudEquipo\n" +
            "						,se_usuario_confirma.[us_cdgo]--30 \n" +
            "						,se_usuario_confirma.[us_clave]-- 31\n" +
            "						,se_usuario_confirma.[us_nombres]-- 32\n" +
            "						,se_usuario_confirma.[us_apellidos]-- 33\n" +
            "						,se_usuario_confirma.[us_perfil_cdgo]-- 34\n" +
            "						,se_usuario_confirma.[us_correo]-- 35\n" +
            "						,se_usuario_confirma.[us_estad]-- 36\n" +
            "				  ,[se_confirmacion_solicitud_equipo_cdgo]-- 37\n" +
            "						--Confirmacion solicitudEquipo\n" +
            "						,[cse_cdgo]-- 38\n" +
            "						,[cse_desc]-- 39\n" +
            "						,[cse_estad]-- 40\n" +
            "			,[sle_tipo_equipo_cdgo]-- 41\n" +
            "				--Tipo de Equipo\n" +
            "				,sle_tipoEquipo.[te_cdgo]-- 42\n" +
            "				,sle_tipoEquipo.[te_desc]-- 43\n" +
            "				,sle_tipoEquipo.[te_estad]-- 44\n" +
            "			,[sle_marca_equipo]-- 45\n" +
            "			,[sle_modelo_equipo]-- 46\n" +
            "			,[sle_cant_equip]-- 47\n" +
            "			,[sle_observ]-- 48\n" +
            "			,[sle_fecha_hora_inicio]-- 49\n" +
            "			,[sle_fecha_hora_fin]-- 50\n" +
            "			,[sle_cant_minutos]-- 51\n" +
            "			,[sle_labor_realizada_cdgo]-- 52\n" +
            "			-- Labor Realizada\n" +
            "				  ,[lr_cdgo]-- 53\n" +
            "				  ,[lr_desc]-- 54\n" +
            "				  ,[lr_estad]-- 55\n" +
            "			,[sle_motonave_cdgo]-- 56\n" +
            "			--Motonave\n" +
            "				  ,[mn_cdgo]-- 57\n" +
            "				  ,[mn_desc]-- 58\n" +
            "				  ,[mn_estad]-- 59\n" +
            "			,[sle_cntro_cost_auxiliar_cdgo]-- 60\n" +
            "				--Centro Costo Auxiliar\n" +
            "				,[cca_cdgo]-- 61\n" +
            "				,[cca_cntro_cost_subcentro_cdgo]-- 62\n" +
            "					-- SubCentro de Costo\n" +
            "					,[ccs_cdgo]-- 63\n" +
            "					,[ccs_desc]-- 64\n" +
            "					,[ccs_estad]-- 65\n" +
            "				,[cca_desc]-- 66\n" +
            "				,[cca_estad]-- 67\n" +
            "			,[sle_compania_cdgo]-- 68\n" +
            "				--Compañia\n" +
            "				,[cp_cdgo]-- 69\n" +
            "				,[cp_desc]-- 70\n" +
            "				,[cp_estad]-- 71\n" +
            "      ,[ae_fecha_registro]-- 72\n" +
            "      ,[ae_fecha_hora_inicio]-- 73\n" +
            "      ,[ae_fecha_hora_fin]-- 74\n" +
            "      ,[ae_cant_minutos]-- 75\n" +
            "      ,[ae_equipo_cdgo]-- 76\n" +
            "			--Equipo\n" +
            "			,[eq_cdgo]-- 77\n" +
            "			,[eq_tipo_equipo_cdgo]-- 78\n" +
            "				--Tipo Equipo\n" +
            "				,eq_tipo_equipo.[te_cdgo]-- 79\n" +
            "				,eq_tipo_equipo.[te_desc]-- 80\n" +
            "				,eq_tipo_equipo.[te_estad]-- 81\n" +
            "			,[eq_codigo_barra]-- 82\n" +
            "			,[eq_referencia]-- 83\n" +
            "			,[eq_producto]-- 84\n" +
            "			,[eq_capacidad]-- 85\n" +
            "			,[eq_marca]-- 86\n" +
            "			,[eq_modelo]-- 87\n" +
            "			,[eq_serial]-- 88\n" +
            "			,[eq_desc]-- 89\n" +
            "			,[eq_clasificador1_cdgo]-- 90\n" +
            "				-- Clasificador 1\n" +
            "				,eq_clasificador1.[ce_cdgo]-- 91\n" +
            "			    ,eq_clasificador1.[ce_desc]-- 92\n" +
            "			    ,eq_clasificador1.[ce_estad]-- 93\n" +
            "			,[eq_clasificador2_cdgo]-- 94\n" +
            "			    -- Clasificador 2\n" +
            "				,eq_clasificador2.[ce_cdgo]-- 95\n" +
            "			    ,eq_clasificador2.[ce_desc]-- 96\n" +
            "			    ,eq_clasificador2.[ce_estad]-- 97\n" +
            "			,[eq_proveedor_equipo_cdgo]-- 98\n" +
            "				--Proveedor Equipo\n" +
            "				,[pe_cdgo]-- 99\n" +
            "				,[pe_nit]-- 100\n" +
            "				,[pe_desc]-- 101\n" +
            "				,[pe_estad]-- 102\n" +
            "			,[eq_equipo_pertenencia_cdgo]-- 103\n" +
            "				-- Equipo Pertenencia\n" +
            "				,eq_pertenencia.[ep_cdgo]-- 104\n" +
            "				,eq_pertenencia.[ep_desc]-- 105\n" +
            "				,eq_pertenencia.[ep_estad]-- 106\n" +
            "			,[eq_observ]-- 107\n" +
            "			,[eq_estad]-- 108\n" +
            "			,[eq_actvo_fijo_id]-- 109\n" +
            "			,[eq_actvo_fijo_referencia]-- 110\n" +
            "			,[eq_actvo_fijo_desc]-- 111\n" +
            "      ,[ae_equipo_pertenencia_cdgo]-- 112\n" +
            "		-- Equipo Pertenencia\n" +
            "				,ae_pertenencia.[ep_cdgo]-- 113\n" +
            "				,ae_pertenencia.[ep_desc]-- 114\n" +
            "				,ae_pertenencia.[ep_estad]-- 115\n" +
            "      ,[ae_cant_minutos_operativo]--116\n" +
            "      ,[ae_cant_minutos_parada]-- 117\n" +
            "      ,[ae_cant_minutos_total]-- 118\n" +
            "      ,CASE WHEN ([ae_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS ae_estad -- 119\n" +
            "  FROM ["+DB+"].[dbo].[asignacion_equipo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[solicitud_listado_equipo] ON [ae_solicitud_listado_equipo_cdgo]=[sle_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_oper] ae_cntro_oper ON [ae_cntro_oper_cdgo]=ae_cntro_oper.[co_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[solicitud_equipo] ON [sle_solicitud_equipo_cdgo]=[se_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_oper] se_cntro_oper ON [se_cntro_oper_cdgo]=se_cntro_oper.[co_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[usuario] se_usuario_realiza ON [se_usuario_realiza_cdgo]=se_usuario_realiza.[us_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[estado_solicitud_equipo] ON [se_estado_solicitud_equipo_cdgo]=[ese_cdgo]\n" +
            "		LEFT JOIN  ["+DB+"].[dbo].[usuario] se_usuario_confirma ON [se_usuario_realiza_cdgo]=se_usuario_confirma.[us_cdgo]\n" +
            "		LEFT JOIN  ["+DB+"].[dbo].[confirmacion_solicitud_equipo] ON [se_confirmacion_solicitud_equipo_cdgo]=[cse_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] sle_tipoEquipo ON [sle_tipo_equipo_cdgo]=sle_tipoEquipo.[te_cdgo]\n" +
            "		LEFT JOIN ["+DB+"].[dbo].[labor_realizada] ON [sle_labor_realizada_cdgo]=[lr_cdgo]\n" +
            "		LEFT  JOIN["+DB+"].[dbo].[motonave] ON [sle_motonave_cdgo]=[mn_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [sle_cntro_cost_auxiliar_cdgo]=[cca_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[compania] ON [sle_compania_cdgo]=[cp_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] eq_tipo_equipo ON [eq_tipo_equipo_cdgo]=eq_tipo_equipo.[te_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador1 ON [eq_clasificador1_cdgo]=eq_clasificador1.[ce_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador2 ON [eq_clasificador2_cdgo]=eq_clasificador2.[ce_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [eq_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[equipo_pertenencia] eq_pertenencia ON [eq_equipo_pertenencia_cdgo]=eq_pertenencia.[ep_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[equipo_pertenencia] ae_pertenencia ON [ae_equipo_pertenencia_cdgo]=ae_pertenencia.[ep_cdgo]\n" +
            "		WHERE [ae_estad]=1 AND ([se_confirmacion_solicitud_equipo_cdgo] IS NULL OR [se_confirmacion_solicitud_equipo_cdgo]=1) "+script);
            resultSet= query.executeQuery();
            while(resultSet.next()){ 
                SolicitudEquipo solicitudEquipo= new SolicitudEquipo();
                        solicitudEquipo.setCodigo(resultSet.getString(9));
                            CentroOperacion co_se= new CentroOperacion();
                                co_se.setCodigo(Integer.parseInt(resultSet.getString(11)));
                                co_se.setDescripcion(resultSet.getString(12));
                                co_se.setEstado(resultSet.getString(13));
                        solicitudEquipo.setCentroOperacion(co_se);
                        solicitudEquipo.setFechaSolicitud(resultSet.getString(14));
                            Usuario us_se = new Usuario();
                                us_se.setCodigo(resultSet.getString(16));
                                //us_se.setClave(resultSet.getString(19));
                                us_se.setNombres(resultSet.getString(18));
                                us_se.setApellidos(resultSet.getString(19));
                                us_se.setCorreo(resultSet.getString(21));
                                us_se.setEstado(resultSet.getString(22));
                        solicitudEquipo.setUsuarioRealizaSolicitud(us_se);
                        solicitudEquipo.setFechaRegistro(resultSet.getString(23));
                            EstadoSolicitudEquipos estadoSolicitudEquipos = new EstadoSolicitudEquipos();
                            estadoSolicitudEquipos.setCodigo(resultSet.getString(25));
                            estadoSolicitudEquipos.setDescripcion(resultSet.getString(26));
                            estadoSolicitudEquipos.setEstado(resultSet.getString(27));
                        solicitudEquipo.setEstadoSolicitudEquipo(estadoSolicitudEquipos);
                        solicitudEquipo.setFechaConfirmacion(resultSet.getString(28));
                            Usuario us_se_confirm = new Usuario();
                            us_se_confirm.setCodigo(resultSet.getString(30));
                            //us_se_confirm.setClave(resultSet.getString(105));
                            us_se_confirm.setNombres(resultSet.getString(32));
                            us_se_confirm.setApellidos(resultSet.getString(33));
                            us_se_confirm.setCorreo(resultSet.getString(35));
                            us_se_confirm.setEstado(resultSet.getString(36));
                        solicitudEquipo.setUsuarioConfirmacionSolicitud(us_se_confirm);
                            ConfirmacionSolicitudEquipos confirmacionSolicitudEquipos = new ConfirmacionSolicitudEquipos();
                            confirmacionSolicitudEquipos.setCodigo(resultSet.getString(38));
                            confirmacionSolicitudEquipos.setDescripcion(resultSet.getString(39));
                            confirmacionSolicitudEquipos.setEstado(resultSet.getString(40));
                        solicitudEquipo.setConfirmacionSolicitudEquipo(confirmacionSolicitudEquipos);
                SolicitudListadoEquipo solicitudListadoEquipo = new SolicitudListadoEquipo();
                solicitudListadoEquipo.setSolicitudEquipo(solicitudEquipo);
                solicitudListadoEquipo.setSolicitudEquipo(solicitudEquipo);
                solicitudListadoEquipo.setCodigo(resultSet.getString(7));
                    TipoEquipo tipoEquipo = new TipoEquipo();
                    tipoEquipo.setCodigo(resultSet.getString(42));
                    tipoEquipo.setDescripcion(resultSet.getString(43));
                    tipoEquipo.setEstado(resultSet.getString(44));
                solicitudListadoEquipo.setTipoEquipo(tipoEquipo);
                solicitudListadoEquipo.setMarcaEquipo(resultSet.getString(45));
                solicitudListadoEquipo.setModeloEquipo(resultSet.getString(46));
                solicitudListadoEquipo.setCantidad(resultSet.getInt(47));
                solicitudListadoEquipo.setObservacacion(resultSet.getString(48));
                solicitudListadoEquipo.setFechaHoraInicio(resultSet.getString(49));
                solicitudListadoEquipo.setFechaHoraFin(resultSet.getString(50));
                solicitudListadoEquipo.setCantidadMinutos(resultSet.getInt(51));
                    LaborRealizada laborRealizada = new LaborRealizada();
                    laborRealizada.setCodigo(resultSet.getString(53));
                    laborRealizada.setDescripcion(resultSet.getString(54));
                    laborRealizada.setEstado(resultSet.getString(55));
                solicitudListadoEquipo.setLaborRealizada(laborRealizada);
                Motonave motonave= null;
                if(!(resultSet.getString(57)== null)){
                    System.out.println("No fue nulo");
                    motonave= new Motonave();
                    motonave.setCodigo(resultSet.getString(57));
                    motonave.setDescripcion(resultSet.getString(58));
                    motonave.setEstado(resultSet.getString(59)); 
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
                    centroCostoSubCentro.setCodigo(resultSet.getInt(63));
                    centroCostoSubCentro.setDescripcion(resultSet.getString(64));
                    centroCostoSubCentro.setEstado(resultSet.getString(65));
                    CentroCostoAuxiliar centroCostoAuxiliar = new CentroCostoAuxiliar();
                    centroCostoAuxiliar.setCodigo(resultSet.getInt(61));
                    centroCostoAuxiliar.setDescripcion(resultSet.getString(66));
                    centroCostoAuxiliar.setEstado(resultSet.getString(67));
                    centroCostoAuxiliar.setCentroCostoSubCentro(centroCostoSubCentro);
                solicitudListadoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar);    
                    Compañia compañia = new Compañia();
                    compañia.setCodigo(resultSet.getString(69));
                    compañia.setDescripcion(resultSet.getString(70));
                    compañia.setEstado(resultSet.getString(71));
                    //compañia.setEstado("2");
                solicitudListadoEquipo.setCompañia(compañia);
                
                AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                asignacionEquipo.setCodigo(resultSet.getString(1));
                    CentroOperacion CentroOperacionAsignacion= new CentroOperacion();
                    CentroOperacionAsignacion.setCodigo(Integer.parseInt(resultSet.getString(3)));
                    CentroOperacionAsignacion.setDescripcion(resultSet.getString(4));
                    CentroOperacionAsignacion.setEstado(resultSet.getString(5));
                asignacionEquipo.setCentroOperacion(CentroOperacionAsignacion);
                asignacionEquipo.setSolicitudListadoEquipo(solicitudListadoEquipo);
                asignacionEquipo.setFechaRegistro(resultSet.getString(72));
                asignacionEquipo.setFechaHoraInicio(resultSet.getString(73));
                asignacionEquipo.setFechaHoraFin(resultSet.getString(74));
                asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(75));
                    Equipo equipo = new Equipo();
                    equipo.setCodigo(resultSet.getString(77));
                    equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(79),resultSet.getString(80),resultSet.getString(81)));
                    equipo.setCodigo_barra(resultSet.getString(82));
                    equipo.setReferencia(resultSet.getString(83));
                    equipo.setProducto(resultSet.getString(84));
                    equipo.setCapacidad(resultSet.getString(85));
                    equipo.setMarca(resultSet.getString(86));
                    equipo.setModelo(resultSet.getString(87));
                    equipo.setSerial(resultSet.getString(88));
                    equipo.setDescripcion(resultSet.getString(89));
                    equipo.setClasificador1(new ClasificadorEquipo(resultSet.getString(91),resultSet.getString(92),resultSet.getString(93)));
                    equipo.setClasificador2(new ClasificadorEquipo(resultSet.getString(95),resultSet.getString(96),resultSet.getString(97)));
                    equipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(99),resultSet.getString(100),resultSet.getString(101),resultSet.getString(102)));
                    equipo.setPertenenciaEquipo(new Pertenencia(resultSet.getString(104),resultSet.getString(105),resultSet.getString(106)));
                    equipo.setObservacion(resultSet.getString(107));
                    equipo.setEstado(resultSet.getString(108));
                    equipo.setActivoFijo_codigo(resultSet.getString(109));
                    equipo.setActivoFijo_referencia(resultSet.getString(110));
                    equipo.setActivoFijo_descripcion(resultSet.getString(111));
                asignacionEquipo.setEquipo(equipo);
                    Pertenencia pertenencia = new Pertenencia();
                    pertenencia.setCodigo(resultSet.getString(113));
                    pertenencia.setDescripcion(resultSet.getString(114));
                    pertenencia.setEstado(resultSet.getString(115));
                asignacionEquipo.setPertenencia(pertenencia);
                asignacionEquipo.setCantidadMinutosOperativo(resultSet.getString(116));
                asignacionEquipo.setCantidadMinutosParada(resultSet.getString(117));
                asignacionEquipo.setCantidadMinutosTotal(resultSet.getString(118));
                asignacionEquipo.setEstado(resultSet.getString(119));
                listadoAsignacionEquipo.add(asignacionEquipo);   
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las asignaciones de equipos en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoAsignacionEquipo;
    }
    public ArrayList<AsignacionEquipo> consultarProgramacionActiva (String script){    
        ArrayList<AsignacionEquipo> listadoAsignacionEquipo = new ArrayList<>();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            PreparedStatement query= conexion.prepareStatement("SELECT "+
            "       [ae_cdgo]-- 1\n" +
            "      ,[ae_cntro_oper_cdgo]-- 2\n" +
            "			--Centro Operacion\n" +
            "			,ae_cntro_oper.[co_cdgo]-- 3\n" +
            "			,ae_cntro_oper.[co_desc]-- 4\n" +
            "			,ae_cntro_oper.[co_estad]-- 5\n" +
            "      ,[ae_solicitud_listado_equipo_cdgo]-- 6\n" +
            "			-- Solicitud Listado Equipo\n" +
            "			,[sle_cdgo]-- 7\n" +
            "			,[sle_solicitud_equipo_cdgo]-- 8\n" +
            "				  --Solicitud Equipo\n" +
            "				  ,[se_cdgo]-- 9\n" +
            "				  ,[se_cntro_oper_cdgo]-- 10\n" +
            "						--CentroOperación SolicitudEquipo\n" +
            "						,se_cntro_oper.[co_cdgo]-- 11\n" +
            "						,se_cntro_oper.[co_desc]-- 12\n" +
            "						,se_cntro_oper.[co_estad]-- 13\n" +
            "				  ,[se_fecha]-- 14\n" +
            "				  ,[se_usuario_realiza_cdgo]-- 15\n" +
            "						--Usuario SolicitudEquipo\n" +
            "						,se_usuario_realiza.[us_cdgo]-- 16\n" +
            "						,se_usuario_realiza.[us_clave]-- 17\n" +
            "						,se_usuario_realiza.[us_nombres]-- 18\n" +
            "						,se_usuario_realiza.[us_apellidos]-- 19\n" +
            "						,se_usuario_realiza.[us_perfil_cdgo]-- 20\n" +
            "						,se_usuario_realiza.[us_correo]-- 21\n" +
            "						,se_usuario_realiza.[us_estad]-- 22\n" +
            "				  ,[se_fecha_registro]-- 23\n" +
            "				  ,[se_estado_solicitud_equipo_cdgo]-- 24\n" +
            "						--Estado de la solicitud\n" +
            "						,[ese_cdgo]-- 25\n" +
            "						,[ese_desc]-- 26\n" +
            "						,[ese_estad]-- 27\n" +
            "				  ,[se_fecha_confirmacion]-- 28\n" +
            "				  ,[se_usuario_confirma_cdgo]-- 29\n" +
            "						--Usuario SolicitudEquipo\n" +
            "						,se_usuario_confirma.[us_cdgo]--30 \n" +
            "						,se_usuario_confirma.[us_clave]-- 31\n" +
            "						,se_usuario_confirma.[us_nombres]-- 32\n" +
            "						,se_usuario_confirma.[us_apellidos]-- 33\n" +
            "						,se_usuario_confirma.[us_perfil_cdgo]-- 34\n" +
            "						,se_usuario_confirma.[us_correo]-- 35\n" +
            "						,se_usuario_confirma.[us_estad]-- 36\n" +
            "				  ,[se_confirmacion_solicitud_equipo_cdgo]-- 37\n" +
            "						--Confirmacion solicitudEquipo\n" +
            "						,[cse_cdgo]-- 38\n" +
            "						,[cse_desc]-- 39\n" +
            "						,[cse_estad]-- 40\n" +
            "			,[sle_tipo_equipo_cdgo]-- 41\n" +
            "				--Tipo de Equipo\n" +
            "				,sle_tipoEquipo.[te_cdgo]-- 42\n" +
            "				,sle_tipoEquipo.[te_desc]-- 43\n" +
            "				,sle_tipoEquipo.[te_estad]-- 44\n" +
            "			,[sle_marca_equipo]-- 45\n" +
            "			,[sle_modelo_equipo]-- 46\n" +
            "			,[sle_cant_equip]-- 47\n" +
            "			,[sle_observ]-- 48\n" +
            "			,[sle_fecha_hora_inicio]-- 49\n" +
            "			,[sle_fecha_hora_fin]-- 50\n" +
            "			,[sle_cant_minutos]-- 51\n" +
            "			,[sle_labor_realizada_cdgo]-- 52\n" +
            "			-- Labor Realizada\n" +
            "				  ,[lr_cdgo]-- 53\n" +
            "				  ,[lr_desc]-- 54\n" +
            "				  ,[lr_estad]-- 55\n" +
            "			,[sle_motonave_cdgo]-- 56\n" +
            "			--Motonave\n" +
            "				  ,[mn_cdgo]-- 57\n" +
            "				  ,[mn_desc]-- 58\n" +
            "				  ,[mn_estad]-- 59\n" +
            "			,[sle_cntro_cost_auxiliar_cdgo]-- 60\n" +
            "				--Centro Costo Auxiliar\n" +
            "				,[cca_cdgo]-- 61\n" +
            "				,[cca_cntro_cost_subcentro_cdgo]-- 62\n" +
            "					-- SubCentro de Costo\n" +
            "					,[ccs_cdgo]-- 63\n" +
            "					,[ccs_desc]-- 64\n" +
            "					,[ccs_estad]-- 65\n" +
            "				,[cca_desc]-- 66\n" +
            "				,[cca_estad]-- 67\n" +
            "			,[sle_compania_cdgo]-- 68\n" +
            "				--Compañia\n" +
            "				,[cp_cdgo]-- 69\n" +
            "				,[cp_desc]-- 70\n" +
            "				,[cp_estad]-- 71\n" +
            "      ,[ae_fecha_registro]-- 72\n" +
            "      ,[ae_fecha_hora_inicio]-- 73\n" +
            "      ,[ae_fecha_hora_fin]-- 74\n" +
            "      ,[ae_cant_minutos]-- 75\n" +
            "      ,[ae_equipo_cdgo]-- 76\n" +
            "			--Equipo\n" +
            "			,[eq_cdgo]-- 77\n" +
            "			,[eq_tipo_equipo_cdgo]-- 78\n" +
            "				--Tipo Equipo\n" +
            "				,eq_tipo_equipo.[te_cdgo]-- 79\n" +
            "				,eq_tipo_equipo.[te_desc]-- 80\n" +
            "				,eq_tipo_equipo.[te_estad]-- 81\n" +
            "			,[eq_codigo_barra]-- 82\n" +
            "			,[eq_referencia]-- 83\n" +
            "			,[eq_producto]-- 84\n" +
            "			,[eq_capacidad]-- 85\n" +
            "			,[eq_marca]-- 86\n" +
            "			,[eq_modelo]-- 87\n" +
            "			,[eq_serial]-- 88\n" +
            "			,[eq_desc]-- 89\n" +
            "			,[eq_clasificador1_cdgo]-- 90\n" +
            "				-- Clasificador 1\n" +
            "				,eq_clasificador1.[ce_cdgo]-- 91\n" +
            "			    ,eq_clasificador1.[ce_desc]-- 92\n" +
            "			    ,eq_clasificador1.[ce_estad]-- 93\n" +
            "			,[eq_clasificador2_cdgo]-- 94\n" +
            "			    -- Clasificador 2\n" +
            "				,eq_clasificador2.[ce_cdgo]-- 95\n" +
            "			    ,eq_clasificador2.[ce_desc]-- 96\n" +
            "			    ,eq_clasificador2.[ce_estad]-- 97\n" +
            "			,[eq_proveedor_equipo_cdgo]-- 98\n" +
            "				--Proveedor Equipo\n" +
            "				,[pe_cdgo]-- 99\n" +
            "				,[pe_nit]-- 100\n" +
            "				,[pe_desc]-- 101\n" +
            "				,[pe_estad]-- 102\n" +
            "			,[eq_equipo_pertenencia_cdgo]-- 103\n" +
            "				-- Equipo Pertenencia\n" +
            "				,eq_pertenencia.[ep_cdgo]-- 104\n" +
            "				,eq_pertenencia.[ep_desc]-- 105\n" +
            "				,eq_pertenencia.[ep_estad]-- 106\n" +
            "			,[eq_observ]-- 107\n" +
            "			,[eq_estad]-- 108\n" +
            "			,[eq_actvo_fijo_id]-- 109\n" +
            "			,[eq_actvo_fijo_referencia]-- 110\n" +
            "			,[eq_actvo_fijo_desc]-- 111\n" +
            "      ,[ae_equipo_pertenencia_cdgo]-- 112\n" +
            "		-- Equipo Pertenencia\n" +
            "				,ae_pertenencia.[ep_cdgo]-- 113\n" +
            "				,ae_pertenencia.[ep_desc]-- 114\n" +
            "				,ae_pertenencia.[ep_estad]-- 115\n" +
            "      ,[ae_cant_minutos_operativo]--116\n" +
            "      ,[ae_cant_minutos_parada]-- 117\n" +
            "      ,[ae_cant_minutos_total]-- 118\n" +
            "      ,CASE WHEN ([ae_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS ae_estad -- 119\n" +
            "  FROM ["+DB+"].[dbo].[asignacion_equipo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[solicitud_listado_equipo] ON [ae_solicitud_listado_equipo_cdgo]=[sle_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_oper] ae_cntro_oper ON [ae_cntro_oper_cdgo]=ae_cntro_oper.[co_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[solicitud_equipo] ON [sle_solicitud_equipo_cdgo]=[se_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_oper] se_cntro_oper ON [se_cntro_oper_cdgo]=se_cntro_oper.[co_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[usuario] se_usuario_realiza ON [se_usuario_realiza_cdgo]=se_usuario_realiza.[us_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[estado_solicitud_equipo] ON [se_estado_solicitud_equipo_cdgo]=[ese_cdgo]\n" +
            "		LEFT JOIN  ["+DB+"].[dbo].[usuario] se_usuario_confirma ON [se_usuario_realiza_cdgo]=se_usuario_confirma.[us_cdgo]\n" +
            "		LEFT JOIN  ["+DB+"].[dbo].[confirmacion_solicitud_equipo] ON [se_confirmacion_solicitud_equipo_cdgo]=[cse_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] sle_tipoEquipo ON [sle_tipo_equipo_cdgo]=sle_tipoEquipo.[te_cdgo]\n" +
            "		LEFT JOIN ["+DB+"].[dbo].[labor_realizada] ON [sle_labor_realizada_cdgo]=[lr_cdgo]\n" +
            "		LEFT  JOIN["+DB+"].[dbo].[motonave] ON [sle_motonave_cdgo]=[mn_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [sle_cntro_cost_auxiliar_cdgo]=[cca_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[compania] ON [sle_compania_cdgo]=[cp_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] eq_tipo_equipo ON [eq_tipo_equipo_cdgo]=eq_tipo_equipo.[te_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador1 ON [eq_clasificador1_cdgo]=eq_clasificador1.[ce_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador2 ON [eq_clasificador2_cdgo]=eq_clasificador2.[ce_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [eq_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[equipo_pertenencia] eq_pertenencia ON [eq_equipo_pertenencia_cdgo]=eq_pertenencia.[ep_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[equipo_pertenencia] ae_pertenencia ON [ae_equipo_pertenencia_cdgo]=ae_pertenencia.[ep_cdgo]\n" +
            "		WHERE [ae_estad]=1 AND ([se_confirmacion_solicitud_equipo_cdgo] IS NULL OR [se_confirmacion_solicitud_equipo_cdgo]=1) "+script);
            resultSet= query.executeQuery();
            while(resultSet.next()){ 
                SolicitudEquipo solicitudEquipo= new SolicitudEquipo();
                        solicitudEquipo.setCodigo(resultSet.getString(9));
                            CentroOperacion co_se= new CentroOperacion();
                                co_se.setCodigo(Integer.parseInt(resultSet.getString(11)));
                                co_se.setDescripcion(resultSet.getString(12));
                                co_se.setEstado(resultSet.getString(13));
                        solicitudEquipo.setCentroOperacion(co_se);
                        solicitudEquipo.setFechaSolicitud(resultSet.getString(14));
                            Usuario us_se = new Usuario();
                                us_se.setCodigo(resultSet.getString(16));
                                //us_se.setClave(resultSet.getString(19));
                                us_se.setNombres(resultSet.getString(18));
                                us_se.setApellidos(resultSet.getString(19));
                                us_se.setCorreo(resultSet.getString(21));
                                us_se.setEstado(resultSet.getString(22));
                        solicitudEquipo.setUsuarioRealizaSolicitud(us_se);
                        solicitudEquipo.setFechaRegistro(resultSet.getString(23));
                            EstadoSolicitudEquipos estadoSolicitudEquipos = new EstadoSolicitudEquipos();
                            estadoSolicitudEquipos.setCodigo(resultSet.getString(25));
                            estadoSolicitudEquipos.setDescripcion(resultSet.getString(26));
                            estadoSolicitudEquipos.setEstado(resultSet.getString(27));
                        solicitudEquipo.setEstadoSolicitudEquipo(estadoSolicitudEquipos);
                        solicitudEquipo.setFechaConfirmacion(resultSet.getString(28));
                            Usuario us_se_confirm = new Usuario();
                            us_se_confirm.setCodigo(resultSet.getString(30));
                            //us_se_confirm.setClave(resultSet.getString(105));
                            us_se_confirm.setNombres(resultSet.getString(32));
                            us_se_confirm.setApellidos(resultSet.getString(33));
                            us_se_confirm.setCorreo(resultSet.getString(35));
                            us_se_confirm.setEstado(resultSet.getString(36));
                        solicitudEquipo.setUsuarioConfirmacionSolicitud(us_se_confirm);
                            ConfirmacionSolicitudEquipos confirmacionSolicitudEquipos = new ConfirmacionSolicitudEquipos();
                            confirmacionSolicitudEquipos.setCodigo(resultSet.getString(38));
                            confirmacionSolicitudEquipos.setDescripcion(resultSet.getString(39));
                            confirmacionSolicitudEquipos.setEstado(resultSet.getString(40));
                        solicitudEquipo.setConfirmacionSolicitudEquipo(confirmacionSolicitudEquipos);
                SolicitudListadoEquipo solicitudListadoEquipo = new SolicitudListadoEquipo();
                solicitudListadoEquipo.setSolicitudEquipo(solicitudEquipo);
                solicitudListadoEquipo.setSolicitudEquipo(solicitudEquipo);
                solicitudListadoEquipo.setCodigo(resultSet.getString(7));
                    TipoEquipo tipoEquipo = new TipoEquipo();
                    tipoEquipo.setCodigo(resultSet.getString(42));
                    tipoEquipo.setDescripcion(resultSet.getString(43));
                    tipoEquipo.setEstado(resultSet.getString(44));
                solicitudListadoEquipo.setTipoEquipo(tipoEquipo);
                solicitudListadoEquipo.setMarcaEquipo(resultSet.getString(45));
                solicitudListadoEquipo.setModeloEquipo(resultSet.getString(46));
                solicitudListadoEquipo.setCantidad(resultSet.getInt(47));
                solicitudListadoEquipo.setObservacacion(resultSet.getString(48));
                solicitudListadoEquipo.setFechaHoraInicio(resultSet.getString(49));
                solicitudListadoEquipo.setFechaHoraFin(resultSet.getString(50));
                solicitudListadoEquipo.setCantidadMinutos(resultSet.getInt(51));
                    LaborRealizada laborRealizada = new LaborRealizada();
                    laborRealizada.setCodigo(resultSet.getString(53));
                    laborRealizada.setDescripcion(resultSet.getString(54));
                    laborRealizada.setEstado(resultSet.getString(55));
                solicitudListadoEquipo.setLaborRealizada(laborRealizada);
                Motonave motonave= null;
                if(!(resultSet.getString(57)== null)){
                    System.out.println("No fue nulo");
                    motonave= new Motonave();
                    motonave.setCodigo(resultSet.getString(57));
                    motonave.setDescripcion(resultSet.getString(58));
                    motonave.setEstado(resultSet.getString(59)); 
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
                    centroCostoSubCentro.setCodigo(resultSet.getInt(63));
                    centroCostoSubCentro.setDescripcion(resultSet.getString(64));
                    centroCostoSubCentro.setEstado(resultSet.getString(65));
                    CentroCostoAuxiliar centroCostoAuxiliar = new CentroCostoAuxiliar();
                    centroCostoAuxiliar.setCodigo(resultSet.getInt(61));
                    centroCostoAuxiliar.setDescripcion(resultSet.getString(66));
                    centroCostoAuxiliar.setEstado(resultSet.getString(67));
                    centroCostoAuxiliar.setCentroCostoSubCentro(centroCostoSubCentro);
                solicitudListadoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar);    
                    Compañia compañia = new Compañia();
                    compañia.setCodigo(resultSet.getString(69));
                    compañia.setDescripcion(resultSet.getString(70));
                    compañia.setEstado(resultSet.getString(71));
                    //compañia.setEstado("2");
                solicitudListadoEquipo.setCompañia(compañia);
                
                AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                asignacionEquipo.setCodigo(resultSet.getString(1));
                    CentroOperacion CentroOperacionAsignacion= new CentroOperacion();
                    CentroOperacionAsignacion.setCodigo(Integer.parseInt(resultSet.getString(3)));
                    CentroOperacionAsignacion.setDescripcion(resultSet.getString(4));
                    CentroOperacionAsignacion.setEstado(resultSet.getString(5));
                asignacionEquipo.setCentroOperacion(CentroOperacionAsignacion);
                asignacionEquipo.setSolicitudListadoEquipo(solicitudListadoEquipo);
                asignacionEquipo.setFechaRegistro(resultSet.getString(72));
                asignacionEquipo.setFechaHoraInicio(resultSet.getString(73));
                asignacionEquipo.setFechaHoraFin(resultSet.getString(74));
                asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(75));
                    Equipo equipo = new Equipo();
                    equipo.setCodigo(resultSet.getString(77));
                    equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(79),resultSet.getString(80),resultSet.getString(81)));
                    equipo.setCodigo_barra(resultSet.getString(82));
                    equipo.setReferencia(resultSet.getString(83));
                    equipo.setProducto(resultSet.getString(84));
                    equipo.setCapacidad(resultSet.getString(85));
                    equipo.setMarca(resultSet.getString(86));
                    equipo.setModelo(resultSet.getString(87));
                    equipo.setSerial(resultSet.getString(88));
                    equipo.setDescripcion(resultSet.getString(89));
                    equipo.setClasificador1(new ClasificadorEquipo(resultSet.getString(91),resultSet.getString(92),resultSet.getString(93)));
                    equipo.setClasificador2(new ClasificadorEquipo(resultSet.getString(95),resultSet.getString(96),resultSet.getString(97)));
                    equipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(99),resultSet.getString(100),resultSet.getString(101),resultSet.getString(102)));
                    equipo.setPertenenciaEquipo(new Pertenencia(resultSet.getString(104),resultSet.getString(105),resultSet.getString(106)));
                    equipo.setObservacion(resultSet.getString(107));
                    equipo.setEstado(resultSet.getString(108));
                    equipo.setActivoFijo_codigo(resultSet.getString(109));
                    equipo.setActivoFijo_referencia(resultSet.getString(110));
                    equipo.setActivoFijo_descripcion(resultSet.getString(111));
                asignacionEquipo.setEquipo(equipo);
                    Pertenencia pertenencia = new Pertenencia();
                    pertenencia.setCodigo(resultSet.getString(113));
                    pertenencia.setDescripcion(resultSet.getString(114));
                    pertenencia.setEstado(resultSet.getString(115));
                asignacionEquipo.setPertenencia(pertenencia);
                asignacionEquipo.setCantidadMinutosOperativo(resultSet.getString(116));
                asignacionEquipo.setCantidadMinutosParada(resultSet.getString(117));
                asignacionEquipo.setCantidadMinutosTotal(resultSet.getString(118));
                asignacionEquipo.setEstado(resultSet.getString(119));
                listadoAsignacionEquipo.add(asignacionEquipo);   
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las asignaciones de equipos en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoAsignacionEquipo;
    }
    public ArrayList<AsignacionEquipo> consultarProgramacionInactiva (String script){    
        ArrayList<AsignacionEquipo> listadoAsignacionEquipo = new ArrayList<>();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            PreparedStatement query= conexion.prepareStatement("SELECT "+
            "       [ae_cdgo]-- 1\n" +
            "      ,[ae_cntro_oper_cdgo]-- 2\n" +
            "			--Centro Operacion\n" +
            "			,ae_cntro_oper.[co_cdgo]-- 3\n" +
            "			,ae_cntro_oper.[co_desc]-- 4\n" +
            "			,ae_cntro_oper.[co_estad]-- 5\n" +
            "      ,[ae_solicitud_listado_equipo_cdgo]-- 6\n" +
            "			-- Solicitud Listado Equipo\n" +
            "			,[sle_cdgo]-- 7\n" +
            "			,[sle_solicitud_equipo_cdgo]-- 8\n" +
            "				  --Solicitud Equipo\n" +
            "				  ,[se_cdgo]-- 9\n" +
            "				  ,[se_cntro_oper_cdgo]-- 10\n" +
            "						--CentroOperación SolicitudEquipo\n" +
            "						,se_cntro_oper.[co_cdgo]-- 11\n" +
            "						,se_cntro_oper.[co_desc]-- 12\n" +
            "						,se_cntro_oper.[co_estad]-- 13\n" +
            "				  ,[se_fecha]-- 14\n" +
            "				  ,[se_usuario_realiza_cdgo]-- 15\n" +
            "						--Usuario SolicitudEquipo\n" +
            "						,se_usuario_realiza.[us_cdgo]-- 16\n" +
            "						,se_usuario_realiza.[us_clave]-- 17\n" +
            "						,se_usuario_realiza.[us_nombres]-- 18\n" +
            "						,se_usuario_realiza.[us_apellidos]-- 19\n" +
            "						,se_usuario_realiza.[us_perfil_cdgo]-- 20\n" +
            "						,se_usuario_realiza.[us_correo]-- 21\n" +
            "						,se_usuario_realiza.[us_estad]-- 22\n" +
            "				  ,[se_fecha_registro]-- 23\n" +
            "				  ,[se_estado_solicitud_equipo_cdgo]-- 24\n" +
            "						--Estado de la solicitud\n" +
            "						,[ese_cdgo]-- 25\n" +
            "						,[ese_desc]-- 26\n" +
            "						,[ese_estad]-- 27\n" +
            "				  ,[se_fecha_confirmacion]-- 28\n" +
            "				  ,[se_usuario_confirma_cdgo]-- 29\n" +
            "						--Usuario SolicitudEquipo\n" +
            "						,se_usuario_confirma.[us_cdgo]--30 \n" +
            "						,se_usuario_confirma.[us_clave]-- 31\n" +
            "						,se_usuario_confirma.[us_nombres]-- 32\n" +
            "						,se_usuario_confirma.[us_apellidos]-- 33\n" +
            "						,se_usuario_confirma.[us_perfil_cdgo]-- 34\n" +
            "						,se_usuario_confirma.[us_correo]-- 35\n" +
            "						,se_usuario_confirma.[us_estad]-- 36\n" +
            "				  ,[se_confirmacion_solicitud_equipo_cdgo]-- 37\n" +
            "						--Confirmacion solicitudEquipo\n" +
            "						,[cse_cdgo]-- 38\n" +
            "						,[cse_desc]-- 39\n" +
            "						,[cse_estad]-- 40\n" +
            "			,[sle_tipo_equipo_cdgo]-- 41\n" +
            "				--Tipo de Equipo\n" +
            "				,sle_tipoEquipo.[te_cdgo]-- 42\n" +
            "				,sle_tipoEquipo.[te_desc]-- 43\n" +
            "				,sle_tipoEquipo.[te_estad]-- 44\n" +
            "			,[sle_marca_equipo]-- 45\n" +
            "			,[sle_modelo_equipo]-- 46\n" +
            "			,[sle_cant_equip]-- 47\n" +
            "			,[sle_observ]-- 48\n" +
            "			,[sle_fecha_hora_inicio]-- 49\n" +
            "			,[sle_fecha_hora_fin]-- 50\n" +
            "			,[sle_cant_minutos]-- 51\n" +
            "			,[sle_labor_realizada_cdgo]-- 52\n" +
            "			-- Labor Realizada\n" +
            "				  ,[lr_cdgo]-- 53\n" +
            "				  ,[lr_desc]-- 54\n" +
            "				  ,[lr_estad]-- 55\n" +
            "			,[sle_motonave_cdgo]-- 56\n" +
            "			--Motonave\n" +
            "				  ,[mn_cdgo]-- 57\n" +
            "				  ,[mn_desc]-- 58\n" +
            "				  ,[mn_estad]-- 59\n" +
            "			,[sle_cntro_cost_auxiliar_cdgo]-- 60\n" +
            "				--Centro Costo Auxiliar\n" +
            "				,[cca_cdgo]-- 61\n" +
            "				,[cca_cntro_cost_subcentro_cdgo]-- 62\n" +
            "					-- SubCentro de Costo\n" +
            "					,[ccs_cdgo]-- 63\n" +
            "					,[ccs_desc]-- 64\n" +
            "					,[ccs_estad]-- 65\n" +
            "				,[cca_desc]-- 66\n" +
            "				,[cca_estad]-- 67\n" +
            "			,[sle_compania_cdgo]-- 68\n" +
            "				--Compañia\n" +
            "				,[cp_cdgo]-- 69\n" +
            "				,[cp_desc]-- 70\n" +
            "				,[cp_estad]-- 71\n" +
            "      ,[ae_fecha_registro]-- 72\n" +
            "      ,[ae_fecha_hora_inicio]-- 73\n" +
            "      ,[ae_fecha_hora_fin]-- 74\n" +
            "      ,[ae_cant_minutos]-- 75\n" +
            "      ,[ae_equipo_cdgo]-- 76\n" +
            "			--Equipo\n" +
            "			,[eq_cdgo]-- 77\n" +
            "			,[eq_tipo_equipo_cdgo]-- 78\n" +
            "				--Tipo Equipo\n" +
            "				,eq_tipo_equipo.[te_cdgo]-- 79\n" +
            "				,eq_tipo_equipo.[te_desc]-- 80\n" +
            "				,eq_tipo_equipo.[te_estad]-- 81\n" +
            "			,[eq_codigo_barra]-- 82\n" +
            "			,[eq_referencia]-- 83\n" +
            "			,[eq_producto]-- 84\n" +
            "			,[eq_capacidad]-- 85\n" +
            "			,[eq_marca]-- 86\n" +
            "			,[eq_modelo]-- 87\n" +
            "			,[eq_serial]-- 88\n" +
            "			,[eq_desc]-- 89\n" +
            "			,[eq_clasificador1_cdgo]-- 90\n" +
            "				-- Clasificador 1\n" +
            "				,eq_clasificador1.[ce_cdgo]-- 91\n" +
            "			    ,eq_clasificador1.[ce_desc]-- 92\n" +
            "			    ,eq_clasificador1.[ce_estad]-- 93\n" +
            "			,[eq_clasificador2_cdgo]-- 94\n" +
            "			    -- Clasificador 2\n" +
            "				,eq_clasificador2.[ce_cdgo]-- 95\n" +
            "			    ,eq_clasificador2.[ce_desc]-- 96\n" +
            "			    ,eq_clasificador2.[ce_estad]-- 97\n" +
            "			,[eq_proveedor_equipo_cdgo]-- 98\n" +
            "				--Proveedor Equipo\n" +
            "				,[pe_cdgo]-- 99\n" +
            "				,[pe_nit]-- 100\n" +
            "				,[pe_desc]-- 101\n" +
            "				,[pe_estad]-- 102\n" +
            "			,[eq_equipo_pertenencia_cdgo]-- 103\n" +
            "				-- Equipo Pertenencia\n" +
            "				,eq_pertenencia.[ep_cdgo]-- 104\n" +
            "				,eq_pertenencia.[ep_desc]-- 105\n" +
            "				,eq_pertenencia.[ep_estad]-- 106\n" +
            "			,[eq_observ]-- 107\n" +
            "			,[eq_estad]-- 108\n" +
            "			,[eq_actvo_fijo_id]-- 109\n" +
            "			,[eq_actvo_fijo_referencia]-- 110\n" +
            "			,[eq_actvo_fijo_desc]-- 111\n" +
            "      ,[ae_equipo_pertenencia_cdgo]-- 112\n" +
            "		-- Equipo Pertenencia\n" +
            "				,ae_pertenencia.[ep_cdgo]-- 113\n" +
            "				,ae_pertenencia.[ep_desc]-- 114\n" +
            "				,ae_pertenencia.[ep_estad]-- 115\n" +
            "      ,[ae_cant_minutos_operativo]--116\n" +
            "      ,[ae_cant_minutos_parada]-- 117\n" +
            "      ,[ae_cant_minutos_total]-- 118\n" +
            "      ,CASE WHEN ([ae_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS ae_estad -- 119\n" +
            "  FROM ["+DB+"].[dbo].[asignacion_equipo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[solicitud_listado_equipo] ON [ae_solicitud_listado_equipo_cdgo]=[sle_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_oper] ae_cntro_oper ON [ae_cntro_oper_cdgo]=ae_cntro_oper.[co_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[solicitud_equipo] ON [sle_solicitud_equipo_cdgo]=[se_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_oper] se_cntro_oper ON [se_cntro_oper_cdgo]=se_cntro_oper.[co_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[usuario] se_usuario_realiza ON [se_usuario_realiza_cdgo]=se_usuario_realiza.[us_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[estado_solicitud_equipo] ON [se_estado_solicitud_equipo_cdgo]=[ese_cdgo]\n" +
            "		LEFT JOIN  ["+DB+"].[dbo].[usuario] se_usuario_confirma ON [se_usuario_realiza_cdgo]=se_usuario_confirma.[us_cdgo]\n" +
            "		LEFT JOIN  ["+DB+"].[dbo].[confirmacion_solicitud_equipo] ON [se_confirmacion_solicitud_equipo_cdgo]=[cse_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] sle_tipoEquipo ON [sle_tipo_equipo_cdgo]=sle_tipoEquipo.[te_cdgo]\n" +
            "		LEFT JOIN ["+DB+"].[dbo].[labor_realizada] ON [sle_labor_realizada_cdgo]=[lr_cdgo]\n" +
            "		LEFT  JOIN["+DB+"].[dbo].[motonave] ON [sle_motonave_cdgo]=[mn_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [sle_cntro_cost_auxiliar_cdgo]=[cca_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[compania] ON [sle_compania_cdgo]=[cp_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] eq_tipo_equipo ON [eq_tipo_equipo_cdgo]=eq_tipo_equipo.[te_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador1 ON [eq_clasificador1_cdgo]=eq_clasificador1.[ce_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador2 ON [eq_clasificador2_cdgo]=eq_clasificador2.[ce_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [eq_proveedor_equipo_cdgo]=[pe_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[equipo_pertenencia] eq_pertenencia ON [eq_equipo_pertenencia_cdgo]=eq_pertenencia.[ep_cdgo]\n" +
            "		INNER JOIN ["+DB+"].[dbo].[equipo_pertenencia] ae_pertenencia ON [ae_equipo_pertenencia_cdgo]=ae_pertenencia.[ep_cdgo]\n" +
            "		WHERE [ae_estad]=0 AND ([se_confirmacion_solicitud_equipo_cdgo] IS NULL OR [se_confirmacion_solicitud_equipo_cdgo]=1) "+script);
            resultSet= query.executeQuery();
            while(resultSet.next()){ 
                SolicitudEquipo solicitudEquipo= new SolicitudEquipo();
                        solicitudEquipo.setCodigo(resultSet.getString(9));
                            CentroOperacion co_se= new CentroOperacion();
                                co_se.setCodigo(Integer.parseInt(resultSet.getString(11)));
                                co_se.setDescripcion(resultSet.getString(12));
                                co_se.setEstado(resultSet.getString(13));
                        solicitudEquipo.setCentroOperacion(co_se);
                        solicitudEquipo.setFechaSolicitud(resultSet.getString(14));
                            Usuario us_se = new Usuario();
                                us_se.setCodigo(resultSet.getString(16));
                                //us_se.setClave(resultSet.getString(19));
                                us_se.setNombres(resultSet.getString(18));
                                us_se.setApellidos(resultSet.getString(19));
                                us_se.setCorreo(resultSet.getString(21));
                                us_se.setEstado(resultSet.getString(22));
                        solicitudEquipo.setUsuarioRealizaSolicitud(us_se);
                        solicitudEquipo.setFechaRegistro(resultSet.getString(23));
                            EstadoSolicitudEquipos estadoSolicitudEquipos = new EstadoSolicitudEquipos();
                            estadoSolicitudEquipos.setCodigo(resultSet.getString(25));
                            estadoSolicitudEquipos.setDescripcion(resultSet.getString(26));
                            estadoSolicitudEquipos.setEstado(resultSet.getString(27));
                        solicitudEquipo.setEstadoSolicitudEquipo(estadoSolicitudEquipos);
                        solicitudEquipo.setFechaConfirmacion(resultSet.getString(28));
                            Usuario us_se_confirm = new Usuario();
                            us_se_confirm.setCodigo(resultSet.getString(30));
                            //us_se_confirm.setClave(resultSet.getString(105));
                            us_se_confirm.setNombres(resultSet.getString(32));
                            us_se_confirm.setApellidos(resultSet.getString(33));
                            us_se_confirm.setCorreo(resultSet.getString(35));
                            us_se_confirm.setEstado(resultSet.getString(36));
                        solicitudEquipo.setUsuarioConfirmacionSolicitud(us_se_confirm);
                            ConfirmacionSolicitudEquipos confirmacionSolicitudEquipos = new ConfirmacionSolicitudEquipos();
                            confirmacionSolicitudEquipos.setCodigo(resultSet.getString(38));
                            confirmacionSolicitudEquipos.setDescripcion(resultSet.getString(39));
                            confirmacionSolicitudEquipos.setEstado(resultSet.getString(40));
                        solicitudEquipo.setConfirmacionSolicitudEquipo(confirmacionSolicitudEquipos);
                SolicitudListadoEquipo solicitudListadoEquipo = new SolicitudListadoEquipo();
                solicitudListadoEquipo.setSolicitudEquipo(solicitudEquipo);
                solicitudListadoEquipo.setSolicitudEquipo(solicitudEquipo);
                solicitudListadoEquipo.setCodigo(resultSet.getString(7));
                    TipoEquipo tipoEquipo = new TipoEquipo();
                    tipoEquipo.setCodigo(resultSet.getString(42));
                    tipoEquipo.setDescripcion(resultSet.getString(43));
                    tipoEquipo.setEstado(resultSet.getString(44));
                solicitudListadoEquipo.setTipoEquipo(tipoEquipo);
                solicitudListadoEquipo.setMarcaEquipo(resultSet.getString(45));
                solicitudListadoEquipo.setModeloEquipo(resultSet.getString(46));
                solicitudListadoEquipo.setCantidad(resultSet.getInt(47));
                solicitudListadoEquipo.setObservacacion(resultSet.getString(48));
                solicitudListadoEquipo.setFechaHoraInicio(resultSet.getString(49));
                solicitudListadoEquipo.setFechaHoraFin(resultSet.getString(50));
                solicitudListadoEquipo.setCantidadMinutos(resultSet.getInt(51));
                    LaborRealizada laborRealizada = new LaborRealizada();
                    laborRealizada.setCodigo(resultSet.getString(53));
                    laborRealizada.setDescripcion(resultSet.getString(54));
                    laborRealizada.setEstado(resultSet.getString(55));
                solicitudListadoEquipo.setLaborRealizada(laborRealizada);
                Motonave motonave= null;
                if(!(resultSet.getString(57)== null)){
                    System.out.println("No fue nulo");
                    motonave= new Motonave();
                    motonave.setCodigo(resultSet.getString(57));
                    motonave.setDescripcion(resultSet.getString(58));
                    motonave.setEstado(resultSet.getString(59)); 
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
                    centroCostoSubCentro.setCodigo(resultSet.getInt(63));
                    centroCostoSubCentro.setDescripcion(resultSet.getString(64));
                    centroCostoSubCentro.setEstado(resultSet.getString(65));
                    CentroCostoAuxiliar centroCostoAuxiliar = new CentroCostoAuxiliar();
                    centroCostoAuxiliar.setCodigo(resultSet.getInt(61));
                    centroCostoAuxiliar.setDescripcion(resultSet.getString(66));
                    centroCostoAuxiliar.setEstado(resultSet.getString(67));
                    centroCostoAuxiliar.setCentroCostoSubCentro(centroCostoSubCentro);
                solicitudListadoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar);    
                    Compañia compañia = new Compañia();
                    compañia.setCodigo(resultSet.getString(69));
                    compañia.setDescripcion(resultSet.getString(70));
                    compañia.setEstado(resultSet.getString(71));
                    //compañia.setEstado("2");
                solicitudListadoEquipo.setCompañia(compañia);
                
                AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                asignacionEquipo.setCodigo(resultSet.getString(1));
                    CentroOperacion CentroOperacionAsignacion= new CentroOperacion();
                    CentroOperacionAsignacion.setCodigo(Integer.parseInt(resultSet.getString(3)));
                    CentroOperacionAsignacion.setDescripcion(resultSet.getString(4));
                    CentroOperacionAsignacion.setEstado(resultSet.getString(5));
                asignacionEquipo.setCentroOperacion(CentroOperacionAsignacion);
                asignacionEquipo.setSolicitudListadoEquipo(solicitudListadoEquipo);
                asignacionEquipo.setFechaRegistro(resultSet.getString(72));
                asignacionEquipo.setFechaHoraInicio(resultSet.getString(73));
                asignacionEquipo.setFechaHoraFin(resultSet.getString(74));
                asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(75));
                    Equipo equipo = new Equipo();
                    equipo.setCodigo(resultSet.getString(77));
                    equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(79),resultSet.getString(80),resultSet.getString(81)));
                    equipo.setCodigo_barra(resultSet.getString(82));
                    equipo.setReferencia(resultSet.getString(83));
                    equipo.setProducto(resultSet.getString(84));
                    equipo.setCapacidad(resultSet.getString(85));
                    equipo.setMarca(resultSet.getString(86));
                    equipo.setModelo(resultSet.getString(87));
                    equipo.setSerial(resultSet.getString(88));
                    equipo.setDescripcion(resultSet.getString(89));
                    equipo.setClasificador1(new ClasificadorEquipo(resultSet.getString(91),resultSet.getString(92),resultSet.getString(93)));
                    equipo.setClasificador2(new ClasificadorEquipo(resultSet.getString(95),resultSet.getString(96),resultSet.getString(97)));
                    equipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(99),resultSet.getString(100),resultSet.getString(101),resultSet.getString(102)));
                    equipo.setPertenenciaEquipo(new Pertenencia(resultSet.getString(104),resultSet.getString(105),resultSet.getString(106)));
                    equipo.setObservacion(resultSet.getString(107));
                    equipo.setEstado(resultSet.getString(108));
                    equipo.setActivoFijo_codigo(resultSet.getString(109));
                    equipo.setActivoFijo_referencia(resultSet.getString(110));
                    equipo.setActivoFijo_descripcion(resultSet.getString(111));
                asignacionEquipo.setEquipo(equipo);
                    Pertenencia pertenencia = new Pertenencia();
                    pertenencia.setCodigo(resultSet.getString(113));
                    pertenencia.setDescripcion(resultSet.getString(114));
                    pertenencia.setEstado(resultSet.getString(115));
                asignacionEquipo.setPertenencia(pertenencia);
                asignacionEquipo.setCantidadMinutosOperativo(resultSet.getString(116));
                asignacionEquipo.setCantidadMinutosParada(resultSet.getString(117));
                asignacionEquipo.setCantidadMinutosTotal(resultSet.getString(118));
                asignacionEquipo.setEstado(resultSet.getString(119));
                listadoAsignacionEquipo.add(asignacionEquipo);   
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las asignaciones de equipos en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoAsignacionEquipo;
    }
}
