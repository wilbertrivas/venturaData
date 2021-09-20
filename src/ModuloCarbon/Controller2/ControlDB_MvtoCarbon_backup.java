package ModuloCarbon.Controller2; 

import ConnectionDB.Conexion_DB_ccargaGP;
import ConnectionDB.Conexion_DB_costos_vg;
import Catalogo.Model.Cliente;
import Catalogo.Model.Articulo; 
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import ModuloCarbon.Model.EstadoMvtoCarbon;
import Catalogo.Model.Motonave;
import ModuloCarbon.Model.MvtoCarbon;
import ModuloCarbon.Model.MvtoCarbon_ListadoEquipos;
import Catalogo.Model.Transportadora;
import Catalogo.Controller.ControlDB_Equipo;
import Catalogo.Model.BaseDatos;
import Catalogo.Model.CentroCosto;
import Catalogo.Model.CentroCostoMayor;
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
import Catalogo.Model.MotivoNoLavado;
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
import ConnectionDB.Conexion_DB_ccargaOPP;
import ModuloCarbon.Model.PlantillaConectorMvtoCarbon;
import ModuloCarbon.Model.PlantillaInformeNoLavadoVehiculos;
import ModuloCarbon.Model.PlantillaInformeRecaudoLavadoVehiculo_PorEquipo;
import ModuloCarbon.Model.PlantillaInformeRecaudoPorLavadoVehiculo;
import ModuloCarbon.Model.PlantillaInformeRecaudoPorUsuario;
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

public class ControlDB_MvtoCarbon_backup {
    
    private Connection conexion=null;
    private Connection conexion2=null;
    private final String tipoConexion;
    
    public                                          ControlDB_MvtoCarbon_backup(String tipoConexion) { 
        this.tipoConexion= tipoConexion;
    }  
    public int                                      modificarMvtoCarbon(MvtoCarbon mvtocarbon,  ArrayList<MvtoCarbon_ListadoEquipos> mvtoCarbon_ListadoEquipos, Usuario us, String scriptDB_MvtoCarbon, String scriptDB_MvtoEquipo,String scriptAuditoria_mtvoCarbon, String scriptAuditoria_mtvoEquipo,String razonModificacion) throws FileNotFoundException, UnknownHostException, SocketException{
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        int result=0;
        try{  
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();           
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_carbon] SET "+scriptDB_MvtoCarbon+" WHERE [mc_cdgo]=?");
            queryActualizar.setString(1,mvtocarbon.getCodigo());
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
                        "           ,'MODULO_CARBON_MODIFICAR_REGISTROS'" +
                        "           ,CONCAT('Se realizó la siguiente modificación sobre el registro  Código:',?,' Modificación: ',?,' Razon de activación: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, mvtocarbon.getCodigo());
                Query_Auditoria.setString(6, mvtocarbon.getCodigo());
                Query_Auditoria.setString(7, scriptAuditoria_mtvoCarbon);
                Query_Auditoria.setString(8, razonModificacion);
                Query_Auditoria.execute();
                result=1;
                if(result==1){
                    if(!scriptDB_MvtoEquipo.equals("")){
                        for(MvtoCarbon_ListadoEquipos listado : mvtoCarbon_ListadoEquipos){
                            try{
                                System.out.println("UPDATE ["+DB+"].[dbo].[mvto_equipo] SET "+scriptDB_MvtoEquipo+" WHERE [me_cdgo]="+listado.getMvtoEquipo().getCodigo()+";");
                                PreparedStatement queryModificarMvtoEquipo= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_equipo] SET "+scriptDB_MvtoEquipo+" WHERE [me_cdgo]=?");
                                queryModificarMvtoEquipo.setString(1,listado.getMvtoEquipo().getCodigo());
                                queryModificarMvtoEquipo.execute();
                                result=1;
                                if(result==1){
                                    result=0;
                                    PreparedStatement Query_Auditoria2= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[auditoria]([au_cdgo]\n" +
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
                                    Query_Auditoria2.setString(1, us.getCodigo());
                                    Query_Auditoria2.setString(2, namePc);
                                    Query_Auditoria2.setString(3, ipPc);
                                    Query_Auditoria2.setString(4, macPC);
                                    Query_Auditoria2.setString(5, listado.getMvtoEquipo().getCodigo());
                                    Query_Auditoria2.setString(6, listado.getMvtoEquipo().getCodigo());
                                    Query_Auditoria2.setString(7, scriptAuditoria_mtvoEquipo);
                                    Query_Auditoria2.setString(8, razonModificacion);
                                    Query_Auditoria2.execute();
                                    result=1;
                                } 
                            }catch (SQLException sqlException ){   
                                result=0;
                                JOptionPane.showMessageDialog(null, "ERROR al querer insertar los datos.");
                                sqlException.printStackTrace();
                            }  
                        }
                    }
                }
            } 
        }catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "ERROR al querer insertar los datos.");
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    } 
    public int                                      modificarEquipoLavado_MvtoCarbon(MvtoCarbon mvtocarbon ,Usuario us, String scriptDB_MvtoCarbon,String scriptAuditoria_mtvoCarbon, String razonModificacion) throws FileNotFoundException, UnknownHostException, SocketException{
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        int result=0;
        try{  
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();           
            System.out.println("UPDATE ["+DB+"].[dbo].[mvto_carbon] SET "+scriptDB_MvtoCarbon+" WHERE [mc_cdgo]="+mvtocarbon.getCodigo()+";");
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_carbon] SET "+scriptDB_MvtoCarbon+" WHERE [mc_cdgo]=?");
            queryActualizar.setString(1,mvtocarbon.getCodigo());
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
                        "           ,'MODULO_CARBON_MODIFICAR_REGISTROS'" +
                        "           ,CONCAT('Se realizó la siguiente modificación sobre el registro  Código:',?,' Modificación: ',?,' Motivo Modificación: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, mvtocarbon.getCodigo());
                Query_Auditoria.setString(6, mvtocarbon.getCodigo());
                Query_Auditoria.setString(7, scriptAuditoria_mtvoCarbon);
                Query_Auditoria.setString(8, razonModificacion);
                Query_Auditoria.execute();
                result=1;
            }
        }catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "ERROR al querer actualizar los datos");
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    } 
    public ArrayList<MvtoCarbon_ListadoEquipos>     buscarMvtoCarbon(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<MvtoCarbon_ListadoEquipos> listadoObjetos = new ArrayList();
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT [mcle_cdgo]--1\n" +
                                                                "      ,[mcle_mvto_carbon_cdgo]--2\n" +
                                                                "			,[mc_cdgo]--3\n" +
                                                                "			,[mc_cntro_oper_cdgo]--4\n" +
                                                                "				,mc_cntro_oper.[co_cdgo]--5\n" +
                                                                "				,mc_cntro_oper.[co_desc]--6\n" +
                                                                "			,[mc_cntro_cost_auxiliar_cdgo]--7\n" +
                                                                "				,mc_cntro_cost_auxiliar.[cca_cdgo]--8\n" +
                                                                "				,mc_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo]--9\n" +
                                                                "					,mc_cntro_cost_subcentro.[ccs_cdgo]--10\n" +
                                                                "					,mc_cntro_cost_subcentro.[ccs_desc]--11\n" +
                                                                "					,mc_cntro_cost_subcentro.[ccs_cntro_cost_rquiere_labor_realizda]--12\n" +
                                                                "				,mc_cntro_cost_auxiliar.[cca_desc]--13\n" +
                                                                "			,[mc_cntro_cost_cdgo]--14\n" +
                                                                "					,mc_cntro_cost.[cc_cdgo]--15\n" +
                                                                "					,mc_cntro_cost.[cc_descripcion]--16\n" +
                                                                "			,[mc_labor_realizada_cdgo]--17\n" +
                                                                "					,mc_labor_realizada.[lr_cdgo]--18\n" +
                                                                "					,mc_labor_realizada.[lr_desc]	--19\n" +
                                                                "			,[mc_articulo_cdgo]--20\n" +
                                                                "					,mc_articulo.[ar_cdgo]--21\n" +
                                                                "					,mc_articulo.[ar_tipo_articulo_cdgo]--22\n" +
                                                                "						,mc_tipo_articulo.[tar_cdgo]--23\n" +
                                                                "						,mc_tipo_articulo.[tar_desc]--24\n" +
                                                                "						,mc_tipo_articulo.[tar_cdgo_erp]--25\n" +
                                                                "						,mc_tipo_articulo.[tar_undad_ngcio]--26\n" +
                                                                "					,mc_articulo.[ar_desc]--27\n" +
                                                                "			,[mc_cliente_cdgo]--28\n" +
                                                                "				,mc_cliente.[cl_cdgo]--29\n" +
                                                                "				,mc_cliente.[cl_desc]--30\n" +
                                                                "			,[mc_transprtdora_cdgo]--31\n" +
                                                                "				,[tr_cdgo]--32\n" +
                                                                "				,[tr_nit]--33\n" +
                                                                "				,[tr_desc]--34\n" +
                                                                "			,datename (MONTH ,[mc_fecha])--35\n" +
                                                                "			,[mc_fecha]--36\n" +
                                                                "			,[mc_num_orden]--37\n" +
                                                                "			,[mc_deposito]--38\n" +
                                                                "			,[mc_consecutivo_tqute]--39\n" +
                                                                "			,[mc_placa_vehiculo]--40\n" +
                                                                "			,[mc_peso_vacio]--41\n" +
                                                                "			,[mc_peso_lleno]--42\n" +
                                                                "			,[mc_peso_neto]--43\n" +
                                                                "			,[mc_fecha_entrad]--44\n" +
                                                                "			,[mc_fecha_salid]--45\n" +
                                                                "			,[mc_fecha_inicio_descargue]--46\n" +
                                                                "			,[mc_fecha_fin_descargue]--47\n" +
                                                                "			,[mc_usuario_cdgo]--48\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_cdgo]--49\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_nombres]--50\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_apellidos]--51\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_correo]--52\n" +
                                                                "			,[mc_observ]--53\n" +
                                                                "			,[mc_estad_mvto_carbon_cdgo]--54\n" +
                                                                "				,[emc_cdgo]--55\n" +
                                                                "				,[emc_desc]--56\n" +
                                                                "			,[mc_conexion_peso_ccarga]--57\n" +
                                                                "			,[mc_registro_manual]--58\n" +
                                                                "			,[mc_usuario_registro_manual_cdgo]--59\n" +
                                                                "				,mc_usuario_registro_manual.[us_cdgo]--60\n" +
                                                                "				,mc_usuario_registro_manual.[us_nombres]--61\n" +
                                                                "				,mc_usuario_registro_manual.[us_apellidos]--62\n" +
                                                                "				,mc_usuario_registro_manual.[us_correo]--63\n" +
                                                                "			,[mc_cntro_cost_auxiliarDestino_cdgo]--64\n" +
                                                                "				,mc_cntro_cost_auxiliarDestino.[cca_cdgo]--65\n" +
                                                                "				,mc_cntro_cost_auxiliarDestino.[cca_desc]--66\n" +
                                                                "			,[mc_cntro_cost_mayor_cdgo]--67\n" +
                                                                "				,mc_cntro_cost_mayor.[ccm_cdgo]--68\n" +
                                                                "				,mc_cntro_cost_mayor.[ccm_desc]--69\n" +
                                                                "      ,[mcle_asignacion_equipo_cdgo]--70\n" +
                                                                "      ,[mcle_mvto_equipo_cdgo]--71\n" +
                                                                "			,[me_cdgo] --72\n" +
                                                                "			,[me_asignacion_equipo_cdgo] --73\n" +
                                                                "				,[ae_cdgo] --74\n" +
                                                                "				,[ae_equipo_cdgo] --75\n" +
                                                                "					,[eq_cdgo] --76\n" +
                                                                "					,[eq_tipo_equipo_cdgo] --77\n" +
                                                                "						,[te_cdgo]  --78\n" +
                                                                "						,[te_desc] --79\n" +
                                                                "					,[eq_marca] --80\n" +
                                                                "					,[eq_modelo] --81\n" +
                                                                "					,[eq_desc]	 	--82\n" +
                                                                "				,[ae_fecha_registro] --83\n" +
                                                                "				,[ae_fecha_hora_inicio] --84\n" +
                                                                "				,[ae_fecha_hora_fin] --85\n" +
                                                                "				,[ae_cant_minutos] --86\n" +
                                                                "				,[ae_equipo_pertenencia_cdgo] --87\n" +
                                                                "					,[ep_cdgo] --88\n" +
                                                                "					,[ep_desc] --89\n" +
                                                                "					,[ep_estad] --90\n" +
                                                                "			,datename (MONTH ,[me_fecha])  -- 91\n" +
                                                                "			,[me_fecha] --92\n" +
                                                                "			,[me_proveedor_equipo_cdgo] --93\n" +
                                                                "				,[pe_cdgo] --94\n" +
                                                                "				,[pe_nit] --95\n" +
                                                                "				,[pe_desc] --96\n" +
                                                                "			,[me_num_orden] --97\n" +
                                                                "			,[me_cntro_oper_cdgo] --98\n" +
                                                                "				,me_cntro_oper.[co_cdgo] --99\n" +
                                                                "				,me_cntro_oper.[co_desc] --100\n" +
                                                                "			,[me_cntro_cost_auxiliar_cdgo] --101\n" +
                                                                "				,cca_origen.[cca_cdgo] --102\n" +
                                                                "				,cca_origen.[cca_cntro_cost_subcentro_cdgo] --103\n" +
                                                                "					,ccs_origen.[ccs_cdgo] --104\n" +
                                                                "					,ccs_origen.[ccs_desc] --105\n" +
                                                                "					,ccs_origen.[ccs_cntro_oper_cdgo] --106\n" +
                                                                "					,ccs_origen.[ccs_cntro_cost_rquiere_labor_realizda] --107\n" +
                                                                "				,cca_origen.[cca_desc] --108\n" +
                                                                "			,[me_cntro_cost_cdgo] --109\n" +
                                                                "				,me_cntro_cost.[cc_cdgo] --110\n" +
                                                                "				,me_cntro_cost.[cc_descripcion] --111\n" +
                                                                "			,[me_labor_realizada_cdgo] --112\n" +
                                                                "				,me_labor_realizada.[lr_cdgo] --113\n" +
                                                                "				,me_labor_realizada.[lr_desc] --114\n" +
                                                                "				,me_labor_realizada.[lr_operativa] --115\n" +
                                                                "				,me_labor_realizada.[lr_parada] --116\n" +
                                                                "			,[me_cliente_cdgo] --117\n" +
                                                                "				,me_cliente.[cl_cdgo] --118\n" +
                                                                "				,me_cliente.[cl_desc] --119\n" +
                                                                "			,[me_articulo_cdgo] --120\n" +
                                                                "				,me_articulo.[ar_cdgo] --121\n" +
                                                                "				,me_articulo.[ar_tipo_articulo_cdgo]--122\n" +
                                                                "					,me_tipo_articulo.[tar_cdgo] --123\n" +
                                                                "					,me_tipo_articulo.[tar_desc] --124\n" +
                                                                "					,me_tipo_articulo.[tar_cdgo_erp] --125\n" +
                                                                "					,me_tipo_articulo.[tar_undad_ngcio] --126\n" +
                                                                "				,me_articulo.[ar_desc] --127\n" +
                                                                "			,[me_motonave_cdgo] --128\n" +
                                                                "				,[mn_cdgo] --129\n" +
                                                                "				,[mn_desc] --130\n" +
                                                                "			,[me_fecha_hora_inicio] --131\n" +
                                                                "			,[me_fecha_hora_fin] --132\n" +
                                                                "			,[me_total_minutos] --133\n" +
                                                                "			,[me_valor_hora] --134\n" +
                                                                "			,[me_costo_total] --135\n" +
                                                                "			,[me_recobro_cdgo] --136\n" +
                                                                "				,[rc_cdgo] --137\n" +
                                                                "				,[rc_desc] --138\n" +
                                                                "				,[rc_estad] --139\n" +
                                                                "			,[me_cliente_recobro_cdgo] --140\n" +
                                                                "			,[me_costo_total_recobro_cliente] --141\n" +
                                                                "			,[me_usuario_registro_cdgo] --142\n" +
                                                                "				,us_registro.[us_cdgo] --143\n" +
                                                                "				,us_registro.[us_nombres] --144\n" +
                                                                "				,us_registro.[us_apellidos] --145\n" +
                                                                "				,us_registro.[us_correo] --146\n" +
                                                                "			,[me_usuario_autorizacion_cdgo] --147\n" +
                                                                "				,us_autoriza.[us_cdgo] --148\n" +
                                                                "				,us_autoriza.[us_nombres] --149\n" +
                                                                "				,us_autoriza.[us_apellidos] --150\n" +
                                                                "				,us_autoriza.[us_correo] --151\n" +
                                                                "			,[me_autorizacion_recobro_cdgo] --152\n" +
                                                                "				,[are_cdgo] --153\n" +
                                                                "				,[are_desc] --154\n" +
                                                                "				,[are_estad] --155\n" +
                                                                "			,[me_observ_autorizacion] --156\n" +
                                                                "			,[me_inactividad] --157\n" +
                                                                "			,[me_causa_inactividad_cdgo] --158\n" +
                                                                "				,[ci_cdgo] --159\n" +
                                                                "				,[ci_desc] --160\n" +
                                                                "				,[ci_estad] --161\n" +
                                                                "			,[me_usuario_inactividad_cdgo] --162\n" +
                                                                "				,us_inactividad.[us_cdgo] --163\n" +
                                                                "				,us_inactividad.[us_nombres] --164\n" +
                                                                "				,us_inactividad.[us_apellidos] --165\n" +
                                                                "				,us_inactividad.[us_correo] --166\n" +
                                                                "			,[me_motivo_parada_estado] --167\n" +
                                                                "			,[me_motivo_parada_cdgo] --168\n" +
                                                                "				,[mpa_cdgo] --169\n" +
                                                                "				,[mpa_desc] --170\n" +
                                                                "			,[me_observ] --171\n" +
                                                                "			,CASE [me_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_estado] --172\n" +
                                                                "			,CASE [me_desde_mvto_carbon] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [me_desde_mvto_carbon] --173\n" +
                                                                "			,[me_cntro_cost] --174\n" +
                                                                "			,[me_cntro_cost_auxiliarDestino_cdgo] --175\n" +
                                                                "				,cca_destino.[cca_cdgo] --176\n" +
                                                                "				,cca_destino.[cca_cntro_cost_subcentro_cdgo] --177\n" +
                                                                "					,ccs_destino.[ccs_cdgo] --178\n" +
                                                                "					,ccs_destino.[ccs_desc] --179\n" +
                                                                "					,ccs_destino.[ccs_cntro_oper_cdgo] --180\n" +
                                                                "					,ccs_destino.[ccs_cntro_cost_rquiere_labor_realizda] --181\n" +
                                                                "				,cca_destino.[cca_desc] --182\n" +
                                                                "			,[me_cntro_cost_mayor_cdgo] --183\n" +
                                                                "				,me_cntro_cost_mayor.[ccm_cdgo] --184\n" +
                                                                "				,me_cntro_cost_mayor.[ccm_desc]  --  185    \n" +
                                                                "				,Tiempo_Vehiculo_Descargue=(SELECT (DATEDIFF (MINUTE,  [mc_fecha_inicio_descargue], [mc_fecha_fin_descargue])))  --186\n" +
                                                                "				,Tiempo_Equipo_Descargue=(SELECT (DATEDIFF (MINUTE,  [me_fecha_hora_inicio], [me_fecha_hora_fin])))  --187                                        \n" +
                                                                "      ,CASE [mcle_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [mcle_estado]--188\n" +
                                                                "      ,[mc_equipo_lavado_cdgo]--189 \n" +
                                                                "      ,[mc_lavado_vehiculo]--190 \n" +
                                                                "      ,[mc_costoLavadoVehiculo]--191 \n" +
                                                                "      ,[mc_valorRecaudoEmpresa_lavadoVehiculo]--192 \n" +
                                                                "      ,[mc_valorRecaudoEquipo_lavadoVehiculo]--193 \n" +
                    
                                                                "      ,mc_cntro_cost_base_datos.[bd_cdgo]   --194 \n" +
                                                                "      ,mc_articulo_base_datos.[bd_cdgo]   --195 \n" +
                                                                "      ,mc_cliente_base_datos.[bd_cdgo]   --196 \n" +
                                                                "      ,mc_transprtdora_base_datos.[bd_cdgo]   --197 \n" +
                                                                "      ,mc_cntro_cost_mayor_base_datos.[bd_cdgo]   --198 \n" +
                    
                                                                "      ,me_cntro_cost_base_datos.[bd_cdgo]   --199 \n" +
                                                                "      ,me_cliente_base_datos.[bd_cdgo]   --200 \n" +
                                                                "      ,me_articulo_base_datos.[bd_cdgo]   --201 \n" +
                                                                "      ,me_motonave_base_datos.[bd_cdgo]   --202 \n" +
                                                                "      ,me_cntro_cost_mayor_base_datos.[bd_cdgo]  --203 \n" +
                    
                                                                "  FROM ["+DB+"].[dbo].[mvto_carbon_listado_equipo]\n" +
                                                                "		INNER JOIN ["+DB+"].[dbo].[mvto_carbon] ON [mcle_mvto_carbon_cdgo]=[mc_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_oper] mc_cntro_oper ON [mc_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                "		LEFT JOIN  ["+DB+"].[dbo].[cntro_cost_auxiliar] mc_cntro_cost_auxiliar ON [mc_cntro_cost_auxiliar_cdgo]=mc_cntro_cost_auxiliar.[cca_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] mc_cntro_cost_subcentro ON mc_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo]=mc_cntro_cost_subcentro.[ccs_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost] mc_cntro_cost ON [mc_cntro_cost_cdgo]=mc_cntro_cost.[cc_cdgo]\n" +      
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cntro_cost_base_datos ON mc_cntro_cost.[cc_cliente_base_datos_cdgo]=mc_cntro_cost_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[labor_realizada] mc_labor_realizada ON [mc_labor_realizada_cdgo] =  mc_labor_realizada.[lr_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[articulo] mc_articulo ON [mc_articulo_cdgo]=mc_articulo.[ar_cdgo] AND mc_articulo.[ar_base_datos_cdgo]=[mc_articulo_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_articulo_base_datos ON mc_articulo.[ar_base_datos_cdgo]=mc_articulo_base_datos.[bd_cdgo] \n"+
                                                                        "		LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] mc_tipo_articulo ON mc_articulo.[ar_tipo_articulo_cdgo]=mc_tipo_articulo.[tar_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente] mc_cliente ON [mc_cliente_cdgo]=mc_cliente.[cl_cdgo] AND mc_cliente.[cl_base_datos_cdgo]=[mc_cliente_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cliente_base_datos ON mc_cliente.[cl_base_datos_cdgo]=mc_cliente_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[transprtdora] ON [mc_transprtdora_cdgo]=[tr_cdgo] AND [tr_base_datos_cdgo]=[mc_transprtdora_base_datos_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_transprtdora_base_datos ON [tr_base_datos_cdgo]=mc_transprtdora_base_datos.[bd_cdgo] \n"+    
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] mc_usuarioQuienRegistra ON [mc_usuario_cdgo]=mc_usuarioQuienRegistra.[us_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[estad_mvto_carbon] ON [mc_estad_mvto_carbon_cdgo]=[emc_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] mc_usuario_registro_manual ON [mc_usuario_registro_manual_cdgo]=mc_usuario_registro_manual.[us_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] mc_cntro_cost_auxiliarDestino ON [mc_cntro_cost_auxiliarDestino_cdgo]=mc_cntro_cost_auxiliarDestino.[cca_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor] mc_cntro_cost_mayor ON [mc_cntro_cost_mayor_cdgo]=mc_cntro_cost_mayor.[ccm_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cntro_cost_mayor_base_datos ON [ccm_cliente_base_datos_cdgo]=mc_cntro_cost_mayor_base_datos.[bd_cdgo] \n"+  
                                                                
                                                                
                                                                
                                                                    
                                                                                                                                               
                                                                "               LEFT JOIN ["+DB+"].[dbo].[mvto_equipo] ON [mcle_mvto_equipo_cdgo]=[me_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [me_asignacion_equipo_cdgo]=[ae_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [me_proveedor_equipo_cdgo]=[pe_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_oper] me_cntro_oper ON [me_cntro_oper_cdgo]=me_cntro_oper.[co_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_origen ON  [me_cntro_cost_auxiliar_cdgo]=cca_origen.[cca_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_origen ON cca_origen.[cca_cntro_cost_subcentro_cdgo]=ccs_origen.[ccs_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost] me_cntro_cost ON [me_cntro_cost_cdgo]=me_cntro_cost.[cc_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_base_datos ON me_cntro_cost.[cc_cliente_base_datos_cdgo]=me_cntro_cost_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[labor_realizada] me_labor_realizada ON [me_labor_realizada_cdgo]=me_labor_realizada.[lr_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente] me_cliente ON [me_cliente_cdgo]=me_cliente.[cl_cdgo] AND me_cliente.[cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON me_cliente.[cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[articulo] me_articulo ON [me_articulo_cdgo]=me_articulo.[ar_cdgo] AND	me_articulo.[ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON me_articulo.[ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] me_tipo_articulo ON me_articulo.[ar_tipo_articulo_cdgo]=me_tipo_articulo.[tar_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[me_motonave_base_datos_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_motonave_base_datos ON  [mn_base_datos_cdgo]=me_motonave_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] us_registro ON [me_usuario_registro_cdgo]=us_registro.[us_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] us_autoriza ON [me_usuario_autorizacion_cdgo]=us_autoriza.[us_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[autorizacion_recobro] ON [me_autorizacion_recobro_cdgo]=[are_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[causa_inactividad] ON [me_causa_inactividad_cdgo]=[ci_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] us_inactividad ON [me_usuario_inactividad_cdgo]=us_inactividad.[us_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[motivo_parada] ON [me_motivo_parada_cdgo]=[mpa_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_destino ON [me_cntro_cost_auxiliarDestino_cdgo]=cca_destino.[cca_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_destino ON cca_destino.[cca_cntro_cost_subcentro_cdgo]=ccs_destino.[ccs_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor] me_cntro_cost_mayor ON [me_cntro_cost_mayor_cdgo]=me_cntro_cost_mayor.[ccm_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_mayor_base_datos ON me_cntro_cost_mayor.[ccm_cliente_base_datos_cdgo]=me_cntro_cost_mayor_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON [ae_equipo_pertenencia_cdgo]=[ep_cdgo]"+
                                                                "	WHERE [mc_fecha] BETWEEN ? AND ? AND [me_inactividad]=0 AND [mc_estad_mvto_carbon_cdgo]=1 ORDER BY [mcle_mvto_carbon_cdgo] DESC");
            query.setString(1, DatetimeInicio);
            query.setString(2, DatetimeFin);
            System.out.println(query.toString());
            ResultSet resultSet; resultSet= query.executeQuery();    
            while(resultSet.next()){ 
                try{
                    MvtoCarbon_ListadoEquipos mvto_listEquipo = new MvtoCarbon_ListadoEquipos();
                    mvto_listEquipo.setCodigo(resultSet.getString(1));
                        MvtoCarbon mvtoCarbon = new MvtoCarbon();
                        mvtoCarbon.setCodigo(resultSet.getString(3));
                        mvtoCarbon.setCentroOperacion(new CentroOperacion(Integer.parseInt(resultSet.getString(5)),resultSet.getString(6),""));
                        mvtoCarbon.setCentroCostoAuxiliar(new CentroCostoAuxiliar(Integer.parseInt(resultSet.getString(8)),new CentroCostoSubCentro(Integer.parseInt(resultSet.getString(10)),resultSet.getString(11),""),resultSet.getString(13),""));

                            CentroCosto mc_CentroCosto = new CentroCosto();
                                mc_CentroCosto.setCodigo(resultSet.getString(15));
                                mc_CentroCosto.setDescripción(resultSet.getString(16));
                                
                                mc_CentroCosto.setClienteBaseDatos(resultSet.getString(194));
                        mvtoCarbon.setCentroCosto(mc_CentroCosto);
                            LaborRealizada mc_LaborRealizada = new LaborRealizada();
                                mc_LaborRealizada.setCodigo(resultSet.getString(18));
                                mc_LaborRealizada.setDescripcion(resultSet.getString(19));
                        mvtoCarbon.setLaborRealizada(mc_LaborRealizada);
                            Articulo mc_Articulo = new Articulo();
                                mc_Articulo.setCodigo(resultSet.getString(21));
                                    TipoArticulo mc_TipoArticulo = new TipoArticulo();
                                    mc_TipoArticulo.setCodigo(resultSet.getString(23));
                                    mc_TipoArticulo.setDescripcion(resultSet.getString(24));
                                    mc_TipoArticulo.setCodigoERP(resultSet.getString(25));
                                    mc_TipoArticulo.setUnidadNegocio(resultSet.getString(26));
                                mc_Articulo.setTipoArticulo(mc_TipoArticulo);
                                mc_Articulo.setDescripcion(resultSet.getString(27));
                                mc_Articulo.setBaseDatos(new BaseDatos(resultSet.getString(195)));
                        mvtoCarbon.setArticulo(mc_Articulo);
                            Cliente mc_cliente = new Cliente();
                            mc_cliente.setCodigo(resultSet.getString(29));
                            mc_cliente.setDescripcion(resultSet.getString(30));
                            mc_cliente.setBaseDatos(new BaseDatos(resultSet.getString(196)));
                        
                        mvtoCarbon.setCliente(mc_cliente);
                            Transportadora mc_trTransportadora = new Transportadora();
                            mc_trTransportadora.setCodigo(resultSet.getString(32));
                            mc_trTransportadora.setNit(resultSet.getString(33));
                            mc_trTransportadora.setDescripcion(resultSet.getString(34));
                            mc_trTransportadora.setBaseDatos(new BaseDatos(resultSet.getString(197)));
                        mvtoCarbon.setTransportadora(mc_trTransportadora);
                        mvtoCarbon.setMes(resultSet.getString(35));
                        mvtoCarbon.setFechaRegistro(resultSet.getString(36));
                        mvtoCarbon.setNumero_orden(resultSet.getString(37));
                        mvtoCarbon.setDeposito(resultSet.getString(38));
                        mvtoCarbon.setConsecutivo(resultSet.getString(39));
                        mvtoCarbon.setPlaca(resultSet.getString(40));
                        mvtoCarbon.setPesoVacio(resultSet.getString(41));
                        mvtoCarbon.setPesoLleno(resultSet.getString(42));
                        mvtoCarbon.setPesoNeto(resultSet.getString(43));
                        mvtoCarbon.setFechaEntradaVehiculo(resultSet.getString(44));
                        mvtoCarbon.setFecha_SalidaVehiculo(resultSet.getString(45));    
                        mvtoCarbon.setFechaInicioDescargue(resultSet.getString(46));    
                        mvtoCarbon.setFechaFinDescargue(resultSet.getString(47));      
                            Usuario mc_usuarioQuienRegistra = new Usuario();           
                            mc_usuarioQuienRegistra.setCodigo(resultSet.getString(49));
                            mc_usuarioQuienRegistra.setNombres(resultSet.getString(50));
                            mc_usuarioQuienRegistra.setApellidos(resultSet.getString(51));
                            mc_usuarioQuienRegistra.setCorreo(resultSet.getString(52));
                        mvtoCarbon.setUsuarioRegistroMovil(mc_usuarioQuienRegistra);
                        mvtoCarbon.setObservacion(resultSet.getString(53));
                            EstadoMvtoCarbon estadMvtoCarbon = new EstadoMvtoCarbon();
                            estadMvtoCarbon.setCodigo(resultSet.getString(55));
                            estadMvtoCarbon.setDescripcion(resultSet.getString(56));
                        mvtoCarbon.setEstadoMvtoCarbon(estadMvtoCarbon);
                        mvtoCarbon.setConexionPesoCcarga(resultSet.getString(57));
                        mvtoCarbon.setRegistroManual(resultSet.getString(58));
                            Usuario mc_usuarioRegManual = new Usuario();           
                            mc_usuarioRegManual.setCodigo(resultSet.getString(60));
                            mc_usuarioRegManual.setNombres(resultSet.getString(61));
                            mc_usuarioRegManual.setApellidos(resultSet.getString(62));
                            mc_usuarioRegManual.setCorreo(resultSet.getString(63));
                        mvtoCarbon.setUsuarioRegistraManual(mc_usuarioRegManual);
                        CentroCostoAuxiliar mc_CentroCostosAuxilarDestino = new CentroCostoAuxiliar();
                        if(resultSet.getString(65) != null){
                            mc_CentroCostosAuxilarDestino.setCodigo(Integer.parseInt(resultSet.getString(65)));
                            mc_CentroCostosAuxilarDestino.setDescripcion(resultSet.getString(66));
                        
                        }else{                         
                            mc_CentroCostosAuxilarDestino.setCodigo(-1);
                            mc_CentroCostosAuxilarDestino.setDescripcion(null);
                        }
                        mvtoCarbon.setCentroCostoAuxiliarDestino(mc_CentroCostosAuxilarDestino); 
                        
                        mvtoCarbon.setCantidadHorasDescargue(resultSet.getString(186));
                            CentroCostoMayor mc_CentroCostoMayor = new CentroCostoMayor();
                            mc_CentroCostoMayor.setCodigo(resultSet.getString(68));
                            mc_CentroCostoMayor.setDescripcion(resultSet.getString(69));  
                            mc_CentroCostoMayor.setClienteBaseDatos(resultSet.getString(198));
                        mvtoCarbon.setCentroCostoMayor(mc_CentroCostoMayor);
                        Equipo equipoTem1 = new Equipo();
                        equipoTem1.setCodigo(resultSet.getString(189));
                        mvtoCarbon.setEquipoLavadoVehiculo(equipoTem1);
                        mvtoCarbon.setLavadoVehiculo(resultSet.getString(190));
                        mvtoCarbon.setCostoLavadoVehiculo(resultSet.getString(191));
                        mvtoCarbon.setValorRecaudoEmpresa_lavadoVehiculo(resultSet.getString(192));
                        mvtoCarbon.setValorRecaudoEquipo_lavadoVehiculo(resultSet.getString(193));
                    mvto_listEquipo.setMvtoCarbon(mvtoCarbon);
                        AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                        asignacionEquipo.setCodigo(resultSet.getString(74));
                        asignacionEquipo.setFechaRegistro(resultSet.getString(83));
                        asignacionEquipo.setFechaHoraInicio(resultSet.getString(84));
                        asignacionEquipo.setFechaHoraFin(resultSet.getString(85));
                        asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(86));
                            Equipo equipo = new Equipo(); 
                            equipo.setCodigo(resultSet.getString(76));
                            equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(78),resultSet.getString(79),""));
                            equipo.setMarca(resultSet.getString(80));
                            equipo.setModelo(resultSet.getString(81));
                            equipo.setDescripcion(resultSet.getString(82));
                            equipo.setPertenenciaEquipo(new Pertenencia(resultSet.getString(88),resultSet.getString(89),resultSet.getString(90)));
                        asignacionEquipo.setEquipo(equipo);
                        asignacionEquipo.setPertenencia(new Pertenencia(resultSet.getString(88),resultSet.getString(89),resultSet.getString(90)));
                    mvto_listEquipo.setAsignacionEquipo(asignacionEquipo);
                        MvtoEquipo mvtoEquipo = new MvtoEquipo();
                        mvtoEquipo.setCodigo(resultSet.getString(72));
                        mvtoEquipo.setAsignacionEquipo(asignacionEquipo);
                        mvtoEquipo.setMes(resultSet.getString(91));
                        mvtoEquipo.setFechaRegistro(resultSet.getString(92));
                        mvtoEquipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(94),resultSet.getString(95),resultSet.getString(96),""));
                        mvtoEquipo.setNumeroOrden(resultSet.getString(97));
                            CentroOperacion me_co= new CentroOperacion();
                            me_co.setCodigo(Integer.parseInt(resultSet.getString(99)));
                            me_co.setDescripcion(resultSet.getString(100));
                        mvtoEquipo.setCentroOperacion(me_co);
                            CentroCostoSubCentro centroCostoSubCentro_mvtoEquipo = new CentroCostoSubCentro();
                            centroCostoSubCentro_mvtoEquipo.setCodigo(resultSet.getInt(104));
                            centroCostoSubCentro_mvtoEquipo.setDescripcion(resultSet.getString(105));
                            centroCostoSubCentro_mvtoEquipo.setCentroCostoRequiereLaborRealizada(resultSet.getString(107));
                            CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipo = new CentroCostoAuxiliar();
                            centroCostoAuxiliar_mvtoEquipo.setCodigo(resultSet.getInt(102));
                            centroCostoAuxiliar_mvtoEquipo.setDescripcion(resultSet.getString(108));
                            centroCostoAuxiliar_mvtoEquipo.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipo);
                        mvtoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipo);
                            CentroCosto centroCosto = new CentroCosto();
                            centroCosto.setCodigo(resultSet.getString(110));
                            centroCosto.setDescripción(resultSet.getString(111));
                            centroCosto.setClienteBaseDatos(resultSet.getString(199));
                        mvtoEquipo.setCentroCosto(centroCosto);
                            LaborRealizada laborRealizadaT = new LaborRealizada();
                            laborRealizadaT.setCodigo(resultSet.getString(113));
                            laborRealizadaT.setDescripcion(resultSet.getString(114));
                            laborRealizadaT.setEs_operativa(resultSet.getString(115));
                            laborRealizadaT.setEs_parada(resultSet.getString(116));
                        mvtoEquipo.setLaborRealizada(laborRealizadaT);
                        Cliente me_cliente = new Cliente();
                        me_cliente.setCodigo(resultSet.getString(118));
                        me_cliente.setDescripcion(resultSet.getString(119));
                        me_cliente.setBaseDatos(new BaseDatos( resultSet.getString(200)));
                        
                        mvtoEquipo.setCliente(me_cliente);
                            TipoArticulo tipoArticulo = new TipoArticulo();
                                    tipoArticulo.setCodigo(resultSet.getString(123));
                                    tipoArticulo.setDescripcion(resultSet.getString(124));
                                    tipoArticulo.setCodigoERP(resultSet.getString(125));
                                    tipoArticulo.setUnidadNegocio(resultSet.getString(126));
                            Articulo articulo = new Articulo();
                            articulo.setCodigo(resultSet.getString(121));
                            articulo.setDescripcion(resultSet.getString(127));
                            articulo.setTipoArticulo(tipoArticulo);
                            articulo.setBaseDatos(new BaseDatos( resultSet.getString(201)));
                        mvtoEquipo.setArticulo(articulo);
                        Motonave motonave = new Motonave();
                        motonave.setCodigo(resultSet.getString(129));
                        motonave.setDescripcion(resultSet.getString(130));
                        motonave.setBaseDatos(new BaseDatos( resultSet.getString(202)));
                    mvtoEquipo.setMotonave(motonave);
                    mvtoEquipo.setFechaHoraInicio(resultSet.getString(131));
                    mvtoEquipo.setFechaHoraFin(resultSet.getString(132));
                    mvtoEquipo.setTotalMinutos(resultSet.getString(133));
                    mvtoEquipo.setValorHora(resultSet.getString(134));
                    mvtoEquipo.setCostoTotal(resultSet.getString(135));
                            Recobro recobro = new Recobro();
                            recobro.setCodigo(resultSet.getString(137));
                            recobro.setDescripcion(resultSet.getString(138));
                    mvtoEquipo.setRecobro(recobro);
                            Usuario usuario_me_registra = new Usuario();
                            usuario_me_registra.setCodigo(resultSet.getString(143));
                            usuario_me_registra.setNombres(resultSet.getString(144));
                            usuario_me_registra.setApellidos(resultSet.getString(145));
                            usuario_me_registra.setCorreo(resultSet.getString(146));
                        mvtoEquipo.setUsuarioQuieRegistra(usuario_me_registra);
                            Usuario usuario_me_autoriza = new Usuario();
                            usuario_me_autoriza.setCodigo(resultSet.getString(148));
                            usuario_me_autoriza.setNombres(resultSet.getString(149));
                            usuario_me_autoriza.setApellidos(resultSet.getString(150));
                            usuario_me_autoriza.setCorreo(resultSet.getString(151));
                        mvtoEquipo.setUsuarioAutorizaRecobro(usuario_me_autoriza);
                            AutorizacionRecobro autorizacionRecobro = new AutorizacionRecobro();
                            autorizacionRecobro.setCodigo(resultSet.getString(153));
                            autorizacionRecobro.setDescripcion(resultSet.getString(154));
                            autorizacionRecobro.setEstado(resultSet.getString(155));
                        mvtoEquipo.setAutorizacionRecobro(autorizacionRecobro);
                        mvtoEquipo.setObservacionAutorizacion(resultSet.getString(156));
                        mvtoEquipo.setInactividad(resultSet.getString(157));
                            CausaInactividad causaInactividad = new CausaInactividad();
                            causaInactividad.setCodigo(resultSet.getString(159));
                            causaInactividad.setDescripcion(resultSet.getString(160));
                            causaInactividad.setEstado(resultSet.getString(161));
                        mvtoEquipo.setCausaInactividad(causaInactividad);
                            Usuario usuario_me_us_inactividad = new Usuario();
                            usuario_me_us_inactividad.setCodigo(resultSet.getString(163));
                            usuario_me_us_inactividad.setNombres(resultSet.getString(164));
                            usuario_me_us_inactividad.setApellidos(resultSet.getString(165));
                            usuario_me_us_inactividad.setCorreo(resultSet.getString(166));
                        mvtoEquipo.setUsuarioInactividad(usuario_me_us_inactividad);
                            MotivoParada motivoParada= new MotivoParada();
                            motivoParada.setCodigo(resultSet.getString(169));
                            motivoParada.setDescripcion(resultSet.getString(170));
                        mvtoEquipo.setMotivoParada(motivoParada);
                        mvtoEquipo.setObservacionMvtoEquipo(resultSet.getString(171));
                        mvtoEquipo.setEstado(resultSet.getString(172));
                        mvtoEquipo.setDesdeCarbon(resultSet.getString(173));
                        mvtoEquipo.setCentroCostoDescripción(resultSet.getString(174));
                        CentroCostoSubCentro centroCostoSubCentro_mvtoEquipoDestino = new CentroCostoSubCentro();
                            centroCostoSubCentro_mvtoEquipoDestino.setCodigo(resultSet.getInt(178));
                            centroCostoSubCentro_mvtoEquipoDestino.setDescripcion(resultSet.getString(179));
                            centroCostoSubCentro_mvtoEquipoDestino.setCentroCostoRequiereLaborRealizada(resultSet.getString(181));
                            CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipoDestino = new CentroCostoAuxiliar();
                            centroCostoAuxiliar_mvtoEquipoDestino.setCodigo(resultSet.getInt(176));
                            centroCostoAuxiliar_mvtoEquipoDestino.setDescripcion(resultSet.getString(182));
                            centroCostoAuxiliar_mvtoEquipoDestino.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipoDestino);
                        mvtoEquipo.setCentroCostoAuxiliarDestino(centroCostoAuxiliar_mvtoEquipoDestino);
                            CentroCostoMayor centroCostoMayor = new CentroCostoMayor();
                            centroCostoMayor.setCodigo(resultSet.getString(184));
                            centroCostoMayor.setDescripcion(resultSet.getString(185));
                            centroCostoMayor.setClienteBaseDatos(resultSet.getString(203));
                        mvtoEquipo.setCentroCostoMayor(centroCostoMayor);    
                        //mvtoEquipo.setTotalMinutos(resultSet.getString(187)); 
                    mvto_listEquipo.setMvtoEquipo(mvtoEquipo);  
                    mvto_listEquipo.setEstado(resultSet.getString(188));
                    try{
                        TarifaEquipo tarifa=new ControlDB_Equipo(tipoConexion).buscarTarifaEquipoPorAño(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getCodigo(),mvto_listEquipo.getMvtoEquipo().getFechaHoraInicio());
                        if(tarifa !=null){
                            if(mvto_listEquipo.getMvtoEquipo().getFechaHoraFin() != null){
                                mvto_listEquipo.getMvtoEquipo().setValorHora(tarifa.getValorHoraOperacion());
                                try{
                                    double des=Double.parseDouble(mvto_listEquipo.getMvtoEquipo().getTotalMinutos());
                                    double totalHoras = Double.parseDouble(""+(des/60));
                                    double valorHora =Double.parseDouble(tarifa.getValorHoraOperacion());
                                    //mvto_listEquipo.getMvtoEquipo().setCostoTotal(""+(totalHoras * valorHora ));  
                                    DecimalFormat formato2 = new DecimalFormat("0.00");
                                    mvto_listEquipo.getMvtoEquipo().setCostoTotal(formato2.format((totalHoras * valorHora )));
                                    //mvto_listEquipo.getMvtoEquipo().setCostoTotalRecobroCliente(formato2.format((totalHoras * valorHora )));
                                    mvto_listEquipo.getMvtoEquipo().setCostoTotalRecobroCliente("0");
                                    mvto_listEquipo.getMvtoCarbon().setCostoLavadoVehiculo(tarifa.getCostoLavadoVehiculo());
                                    mvto_listEquipo.getMvtoCarbon().setValorRecaudoEmpresa_lavadoVehiculo(tarifa.getValorRecaudoEmpresa());
                                    mvto_listEquipo.getMvtoCarbon().setValorRecaudoEquipo_lavadoVehiculo(tarifa.getValorRecaudoEquipo());
                                }catch(Exception e){
                                    //mvto_listEquipo.getMvtoEquipo().setCostoTotal("NO PUDE");
                                }
                            }
                        }
                        
                    }catch(Exception e){
                       System.out.println("Error al procesar tarifa");
                    }

                    listadoObjetos.add(mvto_listEquipo);    
                }catch(Exception e){
                    e.printStackTrace();
                }
                
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los Movimientos de Carbon");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }
    public ArrayList<MvtoCarbon_ListadoEquipos>     buscarMvtoCarbonProgramado(String modalidad, String rango) throws SQLException{
        ArrayList<MvtoCarbon_ListadoEquipos> listadoObjetos = new ArrayList();
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT [mcle_cdgo]--1\n" +
                                                                "      ,[mcle_mvto_carbon_cdgo]--2\n" +
                                                                "			,[mc_cdgo]--3\n" +
                                                                "			,[mc_cntro_oper_cdgo]--4\n" +
                                                                "				,mc_cntro_oper.[co_cdgo]--5\n" +
                                                                "				,mc_cntro_oper.[co_desc]--6\n" +
                                                                "			,[mc_cntro_cost_auxiliar_cdgo]--7\n" +
                                                                "				,mc_cntro_cost_auxiliar.[cca_cdgo]--8\n" +
                                                                "				,mc_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo]--9\n" +
                                                                "					,mc_cntro_cost_subcentro.[ccs_cdgo]--10\n" +
                                                                "					,mc_cntro_cost_subcentro.[ccs_desc]--11\n" +
                                                                "					,mc_cntro_cost_subcentro.[ccs_cntro_cost_rquiere_labor_realizda]--12\n" +
                                                                "				,mc_cntro_cost_auxiliar.[cca_desc]--13\n" +
                                                                "			,[mc_cntro_cost_cdgo]--14\n" +
                                                                "					,mc_cntro_cost.[cc_cdgo]--15\n" +
                                                                "					,mc_cntro_cost.[cc_descripcion]--16\n" +
                                                                "			,[mc_labor_realizada_cdgo]--17\n" +
                                                                "					,mc_labor_realizada.[lr_cdgo]--18\n" +
                                                                "					,mc_labor_realizada.[lr_desc]	--19\n" +
                                                                "			,[mc_articulo_cdgo]--20\n" +
                                                                "					,mc_articulo.[ar_cdgo]--21\n" +
                                                                "					,mc_articulo.[ar_tipo_articulo_cdgo]--22\n" +
                                                                "						,mc_tipo_articulo.[tar_cdgo]--23\n" +
                                                                "						,mc_tipo_articulo.[tar_desc]--24\n" +
                                                                "						,mc_tipo_articulo.[tar_cdgo_erp]--25\n" +
                                                                "						,mc_tipo_articulo.[tar_undad_ngcio]--26\n" +
                                                                "					,mc_articulo.[ar_desc]--27\n" +
                                                                "			,[mc_cliente_cdgo]--28\n" +
                                                                "				,mc_cliente.[cl_cdgo]--29\n" +
                                                                "				,mc_cliente.[cl_desc]--30\n" +
                                                                "			,[mc_transprtdora_cdgo]--31\n" +
                                                                "				,[tr_cdgo]--32\n" +
                                                                "				,[tr_nit]--33\n" +
                                                                "				,[tr_desc]--34\n" +
                                                                "			,datename (MONTH ,[mc_fecha])--35\n" +
                                                                "			,[mc_fecha]--36\n" +
                                                                "			,[mc_num_orden]--37\n" +
                                                                "			,[mc_deposito]--38\n" +
                                                                "			,[mc_consecutivo_tqute]--39\n" +
                                                                "			,[mc_placa_vehiculo]--40\n" +
                                                                "			,[mc_peso_vacio]--41\n" +
                                                                "			,[mc_peso_lleno]--42\n" +
                                                                "			,[mc_peso_neto]--43\n" +
                                                                "			,[mc_fecha_entrad]--44\n" +
                                                                "			,[mc_fecha_salid]--45\n" +
                                                                "			,[mc_fecha_inicio_descargue]--46\n" +
                                                                "			,[mc_fecha_fin_descargue]--47\n" +
                                                                "			,[mc_usuario_cdgo]--48\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_cdgo]--49\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_nombres]--50\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_apellidos]--51\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_correo]--52\n" +
                                                                "			,[mc_observ]--53\n" +
                                                                "			,[mc_estad_mvto_carbon_cdgo]--54\n" +
                                                                "				,[emc_cdgo]--55\n" +
                                                                "				,[emc_desc]--56\n" +
                                                                "			,[mc_conexion_peso_ccarga]--57\n" +
                                                                "			,[mc_registro_manual]--58\n" +
                                                                "			,[mc_usuario_registro_manual_cdgo]--59\n" +
                                                                "				,mc_usuario_registro_manual.[us_cdgo]--60\n" +
                                                                "				,mc_usuario_registro_manual.[us_nombres]--61\n" +
                                                                "				,mc_usuario_registro_manual.[us_apellidos]--62\n" +
                                                                "				,mc_usuario_registro_manual.[us_correo]--63\n" +
                                                                "			,[mc_cntro_cost_auxiliarDestino_cdgo]--64\n" +
                                                                "				,mc_cntro_cost_auxiliarDestino.[cca_cdgo]--65\n" +
                                                                "				,mc_cntro_cost_auxiliarDestino.[cca_desc]--66\n" +
                                                                "			,[mc_cntro_cost_mayor_cdgo]--67\n" +
                                                                "				,mc_cntro_cost_mayor.[ccm_cdgo]--68\n" +
                                                                "				,mc_cntro_cost_mayor.[ccm_desc]--69\n" +
                                                                "      ,[mcle_asignacion_equipo_cdgo]--70\n" +
                                                                "      ,[mcle_mvto_equipo_cdgo]--71\n" +
                                                                "			,[me_cdgo] --72\n" +
                                                                "			,[me_asignacion_equipo_cdgo] --73\n" +
                                                                "				,[ae_cdgo] --74\n" +
                                                                "				,[ae_equipo_cdgo] --75\n" +
                                                                "					,[eq_cdgo] --76\n" +
                                                                "					,[eq_tipo_equipo_cdgo] --77\n" +
                                                                "						,[te_cdgo]  --78\n" +
                                                                "						,[te_desc] --79\n" +
                                                                "					,[eq_marca] --80\n" +
                                                                "					,[eq_modelo] --81\n" +
                                                                "					,[eq_desc]	 	--82\n" +
                                                                "				,[ae_fecha_registro] --83\n" +
                                                                "				,[ae_fecha_hora_inicio] --84\n" +
                                                                "				,[ae_fecha_hora_fin] --85\n" +
                                                                "				,[ae_cant_minutos] --86\n" +
                                                                "				,[ae_equipo_pertenencia_cdgo] --87\n" +
                                                                "					,[ep_cdgo] --88\n" +
                                                                "					,[ep_desc] --89\n" +
                                                                "					,[ep_estad] --90\n" +
                                                                "			,datename (MONTH ,[me_fecha])  -- 91\n" +
                                                                "			,[me_fecha] --92\n" +
                                                                "			,[me_proveedor_equipo_cdgo] --93\n" +
                                                                "				,[pe_cdgo] --94\n" +
                                                                "				,[pe_nit] --95\n" +
                                                                "				,[pe_desc] --96\n" +
                                                                "			,[me_num_orden] --97\n" +
                                                                "			,[me_cntro_oper_cdgo] --98\n" +
                                                                "				,me_cntro_oper.[co_cdgo] --99\n" +
                                                                "				,me_cntro_oper.[co_desc] --100\n" +
                                                                "			,[me_cntro_cost_auxiliar_cdgo] --101\n" +
                                                                "				,cca_origen.[cca_cdgo] --102\n" +
                                                                "				,cca_origen.[cca_cntro_cost_subcentro_cdgo] --103\n" +
                                                                "					,ccs_origen.[ccs_cdgo] --104\n" +
                                                                "					,ccs_origen.[ccs_desc] --105\n" +
                                                                "					,ccs_origen.[ccs_cntro_oper_cdgo] --106\n" +
                                                                "					,ccs_origen.[ccs_cntro_cost_rquiere_labor_realizda] --107\n" +
                                                                "				,cca_origen.[cca_desc] --108\n" +
                                                                "			,[me_cntro_cost_cdgo] --109\n" +
                                                                "				,me_cntro_cost.[cc_cdgo] --110\n" +
                                                                "				,me_cntro_cost.[cc_descripcion] --111\n" +
                                                                "			,[me_labor_realizada_cdgo] --112\n" +
                                                                "				,me_labor_realizada.[lr_cdgo] --113\n" +
                                                                "				,me_labor_realizada.[lr_desc] --114\n" +
                                                                "				,me_labor_realizada.[lr_operativa] --115\n" +
                                                                "				,me_labor_realizada.[lr_parada] --116\n" +
                                                                "			,[me_cliente_cdgo] --117\n" +
                                                                "				,me_cliente.[cl_cdgo] --118\n" +
                                                                "				,me_cliente.[cl_desc] --119\n" +
                                                                "			,[me_articulo_cdgo] --120\n" +
                                                                "				,me_articulo.[ar_cdgo] --121\n" +
                                                                "				,me_articulo.[ar_tipo_articulo_cdgo]--122\n" +
                                                                "					,me_tipo_articulo.[tar_cdgo] --123\n" +
                                                                "					,me_tipo_articulo.[tar_desc] --124\n" +
                                                                "					,me_tipo_articulo.[tar_cdgo_erp] --125\n" +
                                                                "					,me_tipo_articulo.[tar_undad_ngcio] --126\n" +
                                                                "				,me_articulo.[ar_desc] --127\n" +
                                                                "			,[me_motonave_cdgo] --128\n" +
                                                                "				,[mn_cdgo] --129\n" +
                                                                "				,[mn_desc] --130\n" +
                                                                "			,[me_fecha_hora_inicio] --131\n" +
                                                                "			,[me_fecha_hora_fin] --132\n" +
                                                                "			,[me_total_minutos] --133\n" +
                                                                "			,[me_valor_hora] --134\n" +
                                                                "			,[me_costo_total] --135\n" +
                                                                "			,[me_recobro_cdgo] --136\n" +
                                                                "				,[rc_cdgo] --137\n" +
                                                                "				,[rc_desc] --138\n" +
                                                                "				,[rc_estad] --139\n" +
                                                                "			,[me_cliente_recobro_cdgo] --140\n" +
                                                                "			,[me_costo_total_recobro_cliente] --141\n" +
                                                                "			,[me_usuario_registro_cdgo] --142\n" +
                                                                "				,us_registro.[us_cdgo] --143\n" +
                                                                "				,us_registro.[us_nombres] --144\n" +
                                                                "				,us_registro.[us_apellidos] --145\n" +
                                                                "				,us_registro.[us_correo] --146\n" +
                                                                "			,[me_usuario_autorizacion_cdgo] --147\n" +
                                                                "				,us_autoriza.[us_cdgo] --148\n" +
                                                                "				,us_autoriza.[us_nombres] --149\n" +
                                                                "				,us_autoriza.[us_apellidos] --150\n" +
                                                                "				,us_autoriza.[us_correo] --151\n" +
                                                                "			,[me_autorizacion_recobro_cdgo] --152\n" +
                                                                "				,[are_cdgo] --153\n" +
                                                                "				,[are_desc] --154\n" +
                                                                "				,[are_estad] --155\n" +
                                                                "			,[me_observ_autorizacion] --156\n" +
                                                                "			,[me_inactividad] --157\n" +
                                                                "			,[me_causa_inactividad_cdgo] --158\n" +
                                                                "				,[ci_cdgo] --159\n" +
                                                                "				,[ci_desc] --160\n" +
                                                                "				,[ci_estad] --161\n" +
                                                                "			,[me_usuario_inactividad_cdgo] --162\n" +
                                                                "				,us_inactividad.[us_cdgo] --163\n" +
                                                                "				,us_inactividad.[us_nombres] --164\n" +
                                                                "				,us_inactividad.[us_apellidos] --165\n" +
                                                                "				,us_inactividad.[us_correo] --166\n" +
                                                                "			,[me_motivo_parada_estado] --167\n" +
                                                                "			,[me_motivo_parada_cdgo] --168\n" +
                                                                "				,[mpa_cdgo] --169\n" +
                                                                "				,[mpa_desc] --170\n" +
                                                                "			,[me_observ] --171\n" +
                                                                "			,CASE [me_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_estado] --172\n" +
                                                                "			,CASE [me_desde_mvto_carbon] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [me_desde_mvto_carbon] --173\n" +
                                                                "			,[me_cntro_cost] --174\n" +
                                                                "			,[me_cntro_cost_auxiliarDestino_cdgo] --175\n" +
                                                                "				,cca_destino.[cca_cdgo] --176\n" +
                                                                "				,cca_destino.[cca_cntro_cost_subcentro_cdgo] --177\n" +
                                                                "					,ccs_destino.[ccs_cdgo] --178\n" +
                                                                "					,ccs_destino.[ccs_desc] --179\n" +
                                                                "					,ccs_destino.[ccs_cntro_oper_cdgo] --180\n" +
                                                                "					,ccs_destino.[ccs_cntro_cost_rquiere_labor_realizda] --181\n" +
                                                                "				,cca_destino.[cca_desc] --182\n" +
                                                                "			,[me_cntro_cost_mayor_cdgo] --183\n" +
                                                                "				,me_cntro_cost_mayor.[ccm_cdgo] --184\n" +
                                                                "				,me_cntro_cost_mayor.[ccm_desc]  --  185    \n" +
                                                                "				,Tiempo_Vehiculo_Descargue=(SELECT (DATEDIFF (MINUTE,  [mc_fecha_inicio_descargue], [mc_fecha_fin_descargue])))  --186\n" +
                                                                "				,Tiempo_Equipo_Descargue=(SELECT (DATEDIFF (MINUTE,  [me_fecha_hora_inicio], [me_fecha_hora_fin])))  --187                                        \n" +
                                                                "      ,CASE [mcle_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [mcle_estado]--188\n" +
                                                                "      ,[mc_equipo_lavado_cdgo]--189 \n" +
                                                                "      ,[mc_lavado_vehiculo]--190 \n" +
                                                                "      ,[mc_costoLavadoVehiculo]--191 \n" +
                                                                "      ,[mc_valorRecaudoEmpresa_lavadoVehiculo]--192 \n" +
                                                                "      ,[mc_valorRecaudoEquipo_lavadoVehiculo]--193 \n" +
                    
                                                                "      ,mc_cntro_cost_base_datos.[bd_cdgo]   --194 \n" +
                                                                "      ,mc_articulo_base_datos.[bd_cdgo]   --195 \n" +
                                                                "      ,mc_cliente_base_datos.[bd_cdgo]   --196 \n" +
                                                                "      ,mc_transprtdora_base_datos.[bd_cdgo]   --197 \n" +
                                                                "      ,mc_cntro_cost_mayor_base_datos.[bd_cdgo]   --198 \n" +
                    
                                                                "      ,me_cntro_cost_base_datos.[bd_cdgo]   --199 \n" +
                                                                "      ,me_cliente_base_datos.[bd_cdgo]   --200 \n" +
                                                                "      ,me_articulo_base_datos.[bd_cdgo]   --201 \n" +
                                                                "      ,me_motonave_base_datos.[bd_cdgo]   --202 \n" +
                                                                "      ,me_cntro_cost_mayor_base_datos.[bd_cdgo]  --203 \n" +
                    
                                                                "  FROM ["+DB+"].[dbo].[mvto_carbon_listado_equipo]\n" +
                                                                "		INNER JOIN ["+DB+"].[dbo].[mvto_carbon] ON [mcle_mvto_carbon_cdgo]=[mc_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_oper] mc_cntro_oper ON [mc_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                "		LEFT JOIN  ["+DB+"].[dbo].[cntro_cost_auxiliar] mc_cntro_cost_auxiliar ON [mc_cntro_cost_auxiliar_cdgo]=mc_cntro_cost_auxiliar.[cca_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] mc_cntro_cost_subcentro ON mc_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo]=mc_cntro_cost_subcentro.[ccs_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost] mc_cntro_cost ON [mc_cntro_cost_cdgo]=mc_cntro_cost.[cc_cdgo]\n" +      
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cntro_cost_base_datos ON mc_cntro_cost.[cc_cliente_base_datos_cdgo]=mc_cntro_cost_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[labor_realizada] mc_labor_realizada ON [mc_labor_realizada_cdgo] =  mc_labor_realizada.[lr_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[articulo] mc_articulo ON [mc_articulo_cdgo]=mc_articulo.[ar_cdgo] AND mc_articulo.[ar_base_datos_cdgo]=[mc_articulo_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_articulo_base_datos ON mc_articulo.[ar_base_datos_cdgo]=mc_articulo_base_datos.[bd_cdgo] \n"+
                                                                        "		LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] mc_tipo_articulo ON mc_articulo.[ar_tipo_articulo_cdgo]=mc_tipo_articulo.[tar_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente] mc_cliente ON [mc_cliente_cdgo]=mc_cliente.[cl_cdgo] AND mc_cliente.[cl_base_datos_cdgo]=[mc_cliente_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cliente_base_datos ON mc_cliente.[cl_base_datos_cdgo]=mc_cliente_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[transprtdora] ON [mc_transprtdora_cdgo]=[tr_cdgo] AND [tr_base_datos_cdgo]=[mc_transprtdora_base_datos_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_transprtdora_base_datos ON [tr_base_datos_cdgo]=mc_transprtdora_base_datos.[bd_cdgo] \n"+    
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] mc_usuarioQuienRegistra ON [mc_usuario_cdgo]=mc_usuarioQuienRegistra.[us_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[estad_mvto_carbon] ON [mc_estad_mvto_carbon_cdgo]=[emc_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] mc_usuario_registro_manual ON [mc_usuario_registro_manual_cdgo]=mc_usuario_registro_manual.[us_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] mc_cntro_cost_auxiliarDestino ON [mc_cntro_cost_auxiliarDestino_cdgo]=mc_cntro_cost_auxiliarDestino.[cca_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor] mc_cntro_cost_mayor ON [mc_cntro_cost_mayor_cdgo]=mc_cntro_cost_mayor.[ccm_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cntro_cost_mayor_base_datos ON [ccm_cliente_base_datos_cdgo]=mc_cntro_cost_mayor_base_datos.[bd_cdgo] \n"+  
                                                                
                                                                
                                                                
                                                                    
                                                                                                                                               
                                                                "               LEFT JOIN ["+DB+"].[dbo].[mvto_equipo] ON [mcle_mvto_equipo_cdgo]=[me_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [me_asignacion_equipo_cdgo]=[ae_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [me_proveedor_equipo_cdgo]=[pe_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_oper] me_cntro_oper ON [me_cntro_oper_cdgo]=me_cntro_oper.[co_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_origen ON  [me_cntro_cost_auxiliar_cdgo]=cca_origen.[cca_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_origen ON cca_origen.[cca_cntro_cost_subcentro_cdgo]=ccs_origen.[ccs_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost] me_cntro_cost ON [me_cntro_cost_cdgo]=me_cntro_cost.[cc_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_base_datos ON me_cntro_cost.[cc_cliente_base_datos_cdgo]=me_cntro_cost_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[labor_realizada] me_labor_realizada ON [me_labor_realizada_cdgo]=me_labor_realizada.[lr_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente] me_cliente ON [me_cliente_cdgo]=me_cliente.[cl_cdgo] AND me_cliente.[cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON me_cliente.[cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[articulo] me_articulo ON [me_articulo_cdgo]=me_articulo.[ar_cdgo] AND	me_articulo.[ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON me_articulo.[ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] me_tipo_articulo ON me_articulo.[ar_tipo_articulo_cdgo]=me_tipo_articulo.[tar_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[me_motonave_base_datos_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_motonave_base_datos ON  [mn_base_datos_cdgo]=me_motonave_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] us_registro ON [me_usuario_registro_cdgo]=us_registro.[us_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] us_autoriza ON [me_usuario_autorizacion_cdgo]=us_autoriza.[us_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[autorizacion_recobro] ON [me_autorizacion_recobro_cdgo]=[are_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[causa_inactividad] ON [me_causa_inactividad_cdgo]=[ci_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] us_inactividad ON [me_usuario_inactividad_cdgo]=us_inactividad.[us_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[motivo_parada] ON [me_motivo_parada_cdgo]=[mpa_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_destino ON [me_cntro_cost_auxiliarDestino_cdgo]=cca_destino.[cca_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_destino ON cca_destino.[cca_cntro_cost_subcentro_cdgo]=ccs_destino.[ccs_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor] me_cntro_cost_mayor ON [me_cntro_cost_mayor_cdgo]=me_cntro_cost_mayor.[ccm_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_mayor_base_datos ON me_cntro_cost_mayor.[ccm_cliente_base_datos_cdgo]=me_cntro_cost_mayor_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON [ae_equipo_pertenencia_cdgo]=[ep_cdgo]"+
                                                                    "	WHERE [mc_fecha] BETWEEN (SELECT DATEADD("+modalidad+", -"+rango+",  (SELECT SYSDATETIME()))) AND (SELECT SYSDATETIME()) AND [me_inactividad]=0 AND [mc_estad_mvto_carbon_cdgo]=1 ORDER BY [mcle_mvto_carbon_cdgo] DESC");
            //query.setString(1, DatetimeInicio);
            //query.setString(2, DatetimeFin);
            
            ResultSet resultSet; resultSet= query.executeQuery();              
            while(resultSet.next()){ 
                try{
                    MvtoCarbon_ListadoEquipos mvto_listEquipo = new MvtoCarbon_ListadoEquipos();
                    mvto_listEquipo.setCodigo(resultSet.getString(1));
                        MvtoCarbon mvtoCarbon = new MvtoCarbon();
                        mvtoCarbon.setCodigo(resultSet.getString(3));
                        mvtoCarbon.setCentroOperacion(new CentroOperacion(Integer.parseInt(resultSet.getString(5)),resultSet.getString(6),""));
                        mvtoCarbon.setCentroCostoAuxiliar(new CentroCostoAuxiliar(Integer.parseInt(resultSet.getString(8)),new CentroCostoSubCentro(Integer.parseInt(resultSet.getString(10)),resultSet.getString(11),""),resultSet.getString(13),""));

                            CentroCosto mc_CentroCosto = new CentroCosto();
                                mc_CentroCosto.setCodigo(resultSet.getString(15));
                                mc_CentroCosto.setDescripción(resultSet.getString(16));
                                
                                mc_CentroCosto.setClienteBaseDatos(resultSet.getString(194));
                        mvtoCarbon.setCentroCosto(mc_CentroCosto);
                            LaborRealizada mc_LaborRealizada = new LaborRealizada();
                                mc_LaborRealizada.setCodigo(resultSet.getString(18));
                                mc_LaborRealizada.setDescripcion(resultSet.getString(19));
                        mvtoCarbon.setLaborRealizada(mc_LaborRealizada);
                            Articulo mc_Articulo = new Articulo();
                                mc_Articulo.setCodigo(resultSet.getString(21));
                                    TipoArticulo mc_TipoArticulo = new TipoArticulo();
                                    mc_TipoArticulo.setCodigo(resultSet.getString(23));
                                    mc_TipoArticulo.setDescripcion(resultSet.getString(24));
                                    mc_TipoArticulo.setCodigoERP(resultSet.getString(25));
                                    mc_TipoArticulo.setUnidadNegocio(resultSet.getString(26));
                                mc_Articulo.setTipoArticulo(mc_TipoArticulo);
                                mc_Articulo.setDescripcion(resultSet.getString(27));
                                mc_Articulo.setBaseDatos(new BaseDatos(resultSet.getString(195)));
                        mvtoCarbon.setArticulo(mc_Articulo);
                            Cliente mc_cliente = new Cliente();
                            mc_cliente.setCodigo(resultSet.getString(29));
                            mc_cliente.setDescripcion(resultSet.getString(30));
                            mc_cliente.setBaseDatos(new BaseDatos(resultSet.getString(196)));
                        
                        mvtoCarbon.setCliente(mc_cliente);
                            Transportadora mc_trTransportadora = new Transportadora();
                            mc_trTransportadora.setCodigo(resultSet.getString(32));
                            mc_trTransportadora.setNit(resultSet.getString(33));
                            mc_trTransportadora.setDescripcion(resultSet.getString(34));
                            mc_trTransportadora.setBaseDatos(new BaseDatos(resultSet.getString(197)));
                        mvtoCarbon.setTransportadora(mc_trTransportadora);
                        mvtoCarbon.setMes(resultSet.getString(35));
                        mvtoCarbon.setFechaRegistro(resultSet.getString(36));
                        mvtoCarbon.setNumero_orden(resultSet.getString(37));
                        mvtoCarbon.setDeposito(resultSet.getString(38));
                        mvtoCarbon.setConsecutivo(resultSet.getString(39));
                        mvtoCarbon.setPlaca(resultSet.getString(40));
                        mvtoCarbon.setPesoVacio(resultSet.getString(41));
                        mvtoCarbon.setPesoLleno(resultSet.getString(42));
                        mvtoCarbon.setPesoNeto(resultSet.getString(43));
                        mvtoCarbon.setFechaEntradaVehiculo(resultSet.getString(44));
                        mvtoCarbon.setFecha_SalidaVehiculo(resultSet.getString(45));    
                        mvtoCarbon.setFechaInicioDescargue(resultSet.getString(46));    
                        mvtoCarbon.setFechaFinDescargue(resultSet.getString(47));      
                            Usuario mc_usuarioQuienRegistra = new Usuario();           
                            mc_usuarioQuienRegistra.setCodigo(resultSet.getString(49));
                            mc_usuarioQuienRegistra.setNombres(resultSet.getString(50));
                            mc_usuarioQuienRegistra.setApellidos(resultSet.getString(51));
                            mc_usuarioQuienRegistra.setCorreo(resultSet.getString(52));
                        mvtoCarbon.setUsuarioRegistroMovil(mc_usuarioQuienRegistra);
                        mvtoCarbon.setObservacion(resultSet.getString(53));
                            EstadoMvtoCarbon estadMvtoCarbon = new EstadoMvtoCarbon();
                            estadMvtoCarbon.setCodigo(resultSet.getString(55));
                            estadMvtoCarbon.setDescripcion(resultSet.getString(56));
                        mvtoCarbon.setEstadoMvtoCarbon(estadMvtoCarbon);
                        mvtoCarbon.setConexionPesoCcarga(resultSet.getString(57));
                        mvtoCarbon.setRegistroManual(resultSet.getString(58));
                            Usuario mc_usuarioRegManual = new Usuario();           
                            mc_usuarioRegManual.setCodigo(resultSet.getString(60));
                            mc_usuarioRegManual.setNombres(resultSet.getString(61));
                            mc_usuarioRegManual.setApellidos(resultSet.getString(62));
                            mc_usuarioRegManual.setCorreo(resultSet.getString(63));
                        mvtoCarbon.setUsuarioRegistraManual(mc_usuarioRegManual);
                        CentroCostoAuxiliar mc_CentroCostosAuxilarDestino = new CentroCostoAuxiliar();
                        if(resultSet.getString(65) != null){
                            mc_CentroCostosAuxilarDestino.setCodigo(Integer.parseInt(resultSet.getString(65)));
                            mc_CentroCostosAuxilarDestino.setDescripcion(resultSet.getString(66));
                        
                        }else{                         
                            mc_CentroCostosAuxilarDestino.setCodigo(-1);
                            mc_CentroCostosAuxilarDestino.setDescripcion(null);
                        }
                        mvtoCarbon.setCentroCostoAuxiliarDestino(mc_CentroCostosAuxilarDestino); 
                        
                        mvtoCarbon.setCantidadHorasDescargue(resultSet.getString(186));
                            CentroCostoMayor mc_CentroCostoMayor = new CentroCostoMayor();
                            mc_CentroCostoMayor.setCodigo(resultSet.getString(68));
                            mc_CentroCostoMayor.setDescripcion(resultSet.getString(69));  
                            mc_CentroCostoMayor.setClienteBaseDatos(resultSet.getString(198));
                        mvtoCarbon.setCentroCostoMayor(mc_CentroCostoMayor);
                        Equipo equipoTem1 = new Equipo();
                        equipoTem1.setCodigo(resultSet.getString(189));
                        mvtoCarbon.setEquipoLavadoVehiculo(equipoTem1);
                        mvtoCarbon.setLavadoVehiculo(resultSet.getString(190));
                        mvtoCarbon.setCostoLavadoVehiculo(resultSet.getString(191));
                        mvtoCarbon.setValorRecaudoEmpresa_lavadoVehiculo(resultSet.getString(192));
                        mvtoCarbon.setValorRecaudoEquipo_lavadoVehiculo(resultSet.getString(193));
                    mvto_listEquipo.setMvtoCarbon(mvtoCarbon);
                        AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                        asignacionEquipo.setCodigo(resultSet.getString(74));
                        asignacionEquipo.setFechaRegistro(resultSet.getString(83));
                        asignacionEquipo.setFechaHoraInicio(resultSet.getString(84));
                        asignacionEquipo.setFechaHoraFin(resultSet.getString(85));
                        asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(86));
                            Equipo equipo = new Equipo(); 
                            equipo.setCodigo(resultSet.getString(76));
                            equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(78),resultSet.getString(79),""));
                            equipo.setMarca(resultSet.getString(80));
                            equipo.setModelo(resultSet.getString(81));
                            equipo.setDescripcion(resultSet.getString(82));
                            equipo.setPertenenciaEquipo(new Pertenencia(resultSet.getString(88),resultSet.getString(89),resultSet.getString(90)));
                        asignacionEquipo.setEquipo(equipo);
                        asignacionEquipo.setPertenencia(new Pertenencia(resultSet.getString(88),resultSet.getString(89),resultSet.getString(90)));
                    mvto_listEquipo.setAsignacionEquipo(asignacionEquipo);
                        MvtoEquipo mvtoEquipo = new MvtoEquipo();
                        mvtoEquipo.setCodigo(resultSet.getString(72));
                        mvtoEquipo.setAsignacionEquipo(asignacionEquipo);
                        mvtoEquipo.setMes(resultSet.getString(91));
                        mvtoEquipo.setFechaRegistro(resultSet.getString(92));
                        mvtoEquipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(94),resultSet.getString(95),resultSet.getString(96),""));
                        mvtoEquipo.setNumeroOrden(resultSet.getString(97));
                            CentroOperacion me_co= new CentroOperacion();
                            me_co.setCodigo(Integer.parseInt(resultSet.getString(99)));
                            me_co.setDescripcion(resultSet.getString(100));
                        mvtoEquipo.setCentroOperacion(me_co);
                            CentroCostoSubCentro centroCostoSubCentro_mvtoEquipo = new CentroCostoSubCentro();
                            centroCostoSubCentro_mvtoEquipo.setCodigo(resultSet.getInt(104));
                            centroCostoSubCentro_mvtoEquipo.setDescripcion(resultSet.getString(105));
                            centroCostoSubCentro_mvtoEquipo.setCentroCostoRequiereLaborRealizada(resultSet.getString(107));
                            CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipo = new CentroCostoAuxiliar();
                            centroCostoAuxiliar_mvtoEquipo.setCodigo(resultSet.getInt(102));
                            centroCostoAuxiliar_mvtoEquipo.setDescripcion(resultSet.getString(108));
                            centroCostoAuxiliar_mvtoEquipo.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipo);
                        mvtoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipo);
                            CentroCosto centroCosto = new CentroCosto();
                            centroCosto.setCodigo(resultSet.getString(110));
                            centroCosto.setDescripción(resultSet.getString(111));
                            centroCosto.setClienteBaseDatos(resultSet.getString(199));
                        mvtoEquipo.setCentroCosto(centroCosto);
                            LaborRealizada laborRealizadaT = new LaborRealizada();
                            laborRealizadaT.setCodigo(resultSet.getString(113));
                            laborRealizadaT.setDescripcion(resultSet.getString(114));
                            laborRealizadaT.setEs_operativa(resultSet.getString(115));
                            laborRealizadaT.setEs_parada(resultSet.getString(116));
                        mvtoEquipo.setLaborRealizada(laborRealizadaT);
                        Cliente me_cliente = new Cliente();
                        me_cliente.setCodigo(resultSet.getString(118));
                        me_cliente.setDescripcion(resultSet.getString(119));
                        me_cliente.setBaseDatos(new BaseDatos( resultSet.getString(200)));
                        
                        mvtoEquipo.setCliente(me_cliente);
                            TipoArticulo tipoArticulo = new TipoArticulo();
                                    tipoArticulo.setCodigo(resultSet.getString(123));
                                    tipoArticulo.setDescripcion(resultSet.getString(124));
                                    tipoArticulo.setCodigoERP(resultSet.getString(125));
                                    tipoArticulo.setUnidadNegocio(resultSet.getString(126));
                            Articulo articulo = new Articulo();
                            articulo.setCodigo(resultSet.getString(121));
                            articulo.setDescripcion(resultSet.getString(127));
                            articulo.setTipoArticulo(tipoArticulo);
                            articulo.setBaseDatos(new BaseDatos( resultSet.getString(201)));
                        mvtoEquipo.setArticulo(articulo);
                        Motonave motonave = new Motonave();
                        motonave.setCodigo(resultSet.getString(129));
                        motonave.setDescripcion(resultSet.getString(130));
                        motonave.setBaseDatos(new BaseDatos( resultSet.getString(202)));
                    mvtoEquipo.setMotonave(motonave);
                    mvtoEquipo.setFechaHoraInicio(resultSet.getString(131));
                    mvtoEquipo.setFechaHoraFin(resultSet.getString(132));
                    mvtoEquipo.setTotalMinutos(resultSet.getString(133));
                    mvtoEquipo.setValorHora(resultSet.getString(134));
                    mvtoEquipo.setCostoTotal(resultSet.getString(135));
                            Recobro recobro = new Recobro();
                            recobro.setCodigo(resultSet.getString(137));
                            recobro.setDescripcion(resultSet.getString(138));
                    mvtoEquipo.setRecobro(recobro);
                            Usuario usuario_me_registra = new Usuario();
                            usuario_me_registra.setCodigo(resultSet.getString(143));
                            usuario_me_registra.setNombres(resultSet.getString(144));
                            usuario_me_registra.setApellidos(resultSet.getString(145));
                            usuario_me_registra.setCorreo(resultSet.getString(146));
                        mvtoEquipo.setUsuarioQuieRegistra(usuario_me_registra);
                            Usuario usuario_me_autoriza = new Usuario();
                            usuario_me_autoriza.setCodigo(resultSet.getString(148));
                            usuario_me_autoriza.setNombres(resultSet.getString(149));
                            usuario_me_autoriza.setApellidos(resultSet.getString(150));
                            usuario_me_autoriza.setCorreo(resultSet.getString(151));
                        mvtoEquipo.setUsuarioAutorizaRecobro(usuario_me_autoriza);
                            AutorizacionRecobro autorizacionRecobro = new AutorizacionRecobro();
                            autorizacionRecobro.setCodigo(resultSet.getString(153));
                            autorizacionRecobro.setDescripcion(resultSet.getString(154));
                            autorizacionRecobro.setEstado(resultSet.getString(155));
                        mvtoEquipo.setAutorizacionRecobro(autorizacionRecobro);
                        mvtoEquipo.setObservacionAutorizacion(resultSet.getString(156));
                        mvtoEquipo.setInactividad(resultSet.getString(157));
                            CausaInactividad causaInactividad = new CausaInactividad();
                            causaInactividad.setCodigo(resultSet.getString(159));
                            causaInactividad.setDescripcion(resultSet.getString(160));
                            causaInactividad.setEstado(resultSet.getString(161));
                        mvtoEquipo.setCausaInactividad(causaInactividad);
                            Usuario usuario_me_us_inactividad = new Usuario();
                            usuario_me_us_inactividad.setCodigo(resultSet.getString(163));
                            usuario_me_us_inactividad.setNombres(resultSet.getString(164));
                            usuario_me_us_inactividad.setApellidos(resultSet.getString(165));
                            usuario_me_us_inactividad.setCorreo(resultSet.getString(166));
                        mvtoEquipo.setUsuarioInactividad(usuario_me_us_inactividad);
                            MotivoParada motivoParada= new MotivoParada();
                            motivoParada.setCodigo(resultSet.getString(169));
                            motivoParada.setDescripcion(resultSet.getString(170));
                        mvtoEquipo.setMotivoParada(motivoParada);
                        mvtoEquipo.setObservacionMvtoEquipo(resultSet.getString(171));
                        mvtoEquipo.setEstado(resultSet.getString(172));
                        mvtoEquipo.setDesdeCarbon(resultSet.getString(173));
                        mvtoEquipo.setCentroCostoDescripción(resultSet.getString(174));
                        CentroCostoSubCentro centroCostoSubCentro_mvtoEquipoDestino = new CentroCostoSubCentro();
                            centroCostoSubCentro_mvtoEquipoDestino.setCodigo(resultSet.getInt(178));
                            centroCostoSubCentro_mvtoEquipoDestino.setDescripcion(resultSet.getString(179));
                            centroCostoSubCentro_mvtoEquipoDestino.setCentroCostoRequiereLaborRealizada(resultSet.getString(181));
                            CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipoDestino = new CentroCostoAuxiliar();
                            centroCostoAuxiliar_mvtoEquipoDestino.setCodigo(resultSet.getInt(176));
                            centroCostoAuxiliar_mvtoEquipoDestino.setDescripcion(resultSet.getString(182));
                            centroCostoAuxiliar_mvtoEquipoDestino.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipoDestino);
                        mvtoEquipo.setCentroCostoAuxiliarDestino(centroCostoAuxiliar_mvtoEquipoDestino);
                            CentroCostoMayor centroCostoMayor = new CentroCostoMayor();
                            centroCostoMayor.setCodigo(resultSet.getString(184));
                            centroCostoMayor.setDescripcion(resultSet.getString(185));
                            centroCostoMayor.setClienteBaseDatos(resultSet.getString(203));
                        mvtoEquipo.setCentroCostoMayor(centroCostoMayor);    
                        //mvtoEquipo.setTotalMinutos(resultSet.getString(187)); 
                    mvto_listEquipo.setMvtoEquipo(mvtoEquipo);  
                    mvto_listEquipo.setEstado(resultSet.getString(188));
                    try{
                        TarifaEquipo tarifa=new ControlDB_Equipo(tipoConexion).buscarTarifaEquipoPorAño(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getCodigo(),mvto_listEquipo.getMvtoEquipo().getFechaHoraInicio());
                        if(tarifa !=null){
                            if(mvto_listEquipo.getMvtoEquipo().getFechaHoraFin() != null){
                                mvto_listEquipo.getMvtoEquipo().setValorHora(tarifa.getValorHoraOperacion());
                                try{
                                    double des=Double.parseDouble(mvto_listEquipo.getMvtoEquipo().getTotalMinutos());
                                    double totalHoras = Double.parseDouble(""+(des/60));
                                    double valorHora =Double.parseDouble(tarifa.getValorHoraOperacion());
                                    //mvto_listEquipo.getMvtoEquipo().setCostoTotal(""+(totalHoras * valorHora ));  
                                    DecimalFormat formato2 = new DecimalFormat("0.00");
                                    mvto_listEquipo.getMvtoEquipo().setCostoTotal(formato2.format((totalHoras * valorHora )));
                                    //mvto_listEquipo.getMvtoEquipo().setCostoTotalRecobroCliente(formato2.format((totalHoras * valorHora )));
                                    mvto_listEquipo.getMvtoEquipo().setCostoTotalRecobroCliente("0");
                                    mvto_listEquipo.getMvtoCarbon().setCostoLavadoVehiculo(tarifa.getCostoLavadoVehiculo());
                                    mvto_listEquipo.getMvtoCarbon().setValorRecaudoEmpresa_lavadoVehiculo(tarifa.getValorRecaudoEmpresa());
                                    mvto_listEquipo.getMvtoCarbon().setValorRecaudoEquipo_lavadoVehiculo(tarifa.getValorRecaudoEquipo());
                                }catch(Exception e){
                                    //mvto_listEquipo.getMvtoEquipo().setCostoTotal("NO PUDE");
                                }
                            }
                        }
                        
                    }catch(Exception e){
                       System.out.println("Error al procesar tarifa");
                    }

                    listadoObjetos.add(mvto_listEquipo);    
                }catch(Exception e){
                    e.printStackTrace();
                } 
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los Movimientos de Carbon");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }
    public ArrayList<MvtoCarbon_ListadoEquipos>     buscarMvtoCarbonParticular(MvtoCarbon mvtoCarbonI) throws SQLException{
        ArrayList<MvtoCarbon_ListadoEquipos> listadoObjetos = new ArrayList();
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT [mcle_cdgo]--1\n" +
                                                                "      ,[mcle_mvto_carbon_cdgo]--2\n" +
                                                                "			,[mc_cdgo]--3\n" +
                                                                "			,[mc_cntro_oper_cdgo]--4\n" +
                                                                "				,mc_cntro_oper.[co_cdgo]--5\n" +
                                                                "				,mc_cntro_oper.[co_desc]--6\n" +
                                                                "			,[mc_cntro_cost_auxiliar_cdgo]--7\n" +
                                                                "				,mc_cntro_cost_auxiliar.[cca_cdgo]--8\n" +
                                                                "				,mc_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo]--9\n" +
                                                                "					,mc_cntro_cost_subcentro.[ccs_cdgo]--10\n" +
                                                                "					,mc_cntro_cost_subcentro.[ccs_desc]--11\n" +
                                                                "					,mc_cntro_cost_subcentro.[ccs_cntro_cost_rquiere_labor_realizda]--12\n" +
                                                                "				,mc_cntro_cost_auxiliar.[cca_desc]--13\n" +
                                                                "			,[mc_cntro_cost_cdgo]--14\n" +
                                                                "					,mc_cntro_cost.[cc_cdgo]--15\n" +
                                                                "					,mc_cntro_cost.[cc_descripcion]--16\n" +
                                                                "			,[mc_labor_realizada_cdgo]--17\n" +
                                                                "					,mc_labor_realizada.[lr_cdgo]--18\n" +
                                                                "					,mc_labor_realizada.[lr_desc]	--19\n" +
                                                                "			,[mc_articulo_cdgo]--20\n" +
                                                                "					,mc_articulo.[ar_cdgo]--21\n" +
                                                                "					,mc_articulo.[ar_tipo_articulo_cdgo]--22\n" +
                                                                "						,mc_tipo_articulo.[tar_cdgo]--23\n" +
                                                                "						,mc_tipo_articulo.[tar_desc]--24\n" +
                                                                "						,mc_tipo_articulo.[tar_cdgo_erp]--25\n" +
                                                                "						,mc_tipo_articulo.[tar_undad_ngcio]--26\n" +
                                                                "					,mc_articulo.[ar_desc]--27\n" +
                                                                "			,[mc_cliente_cdgo]--28\n" +
                                                                "				,mc_cliente.[cl_cdgo]--29\n" +
                                                                "				,mc_cliente.[cl_desc]--30\n" +
                                                                "			,[mc_transprtdora_cdgo]--31\n" +
                                                                "				,[tr_cdgo]--32\n" +
                                                                "				,[tr_nit]--33\n" +
                                                                "				,[tr_desc]--34\n" +
                                                                "			,datename (MONTH ,[mc_fecha])--35\n" +
                                                                "			,[mc_fecha]--36\n" +
                                                                "			,[mc_num_orden]--37\n" +
                                                                "			,[mc_deposito]--38\n" +
                                                                "			,[mc_consecutivo_tqute]--39\n" +
                                                                "			,[mc_placa_vehiculo]--40\n" +
                                                                "			,[mc_peso_vacio]--41\n" +
                                                                "			,[mc_peso_lleno]--42\n" +
                                                                "			,[mc_peso_neto]--43\n" +
                                                                "			,[mc_fecha_entrad]--44\n" +
                                                                "			,[mc_fecha_salid]--45\n" +
                                                                "			,[mc_fecha_inicio_descargue]--46\n" +
                                                                "			,[mc_fecha_fin_descargue]--47\n" +
                                                                "			,[mc_usuario_cdgo]--48\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_cdgo]--49\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_nombres]--50\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_apellidos]--51\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_correo]--52\n" +
                                                                "			,[mc_observ]--53\n" +
                                                                "			,[mc_estad_mvto_carbon_cdgo]--54\n" +
                                                                "				,[emc_cdgo]--55\n" +
                                                                "				,[emc_desc]--56\n" +
                                                                "			,[mc_conexion_peso_ccarga]--57\n" +
                                                                "			,[mc_registro_manual]--58\n" +
                                                                "			,[mc_usuario_registro_manual_cdgo]--59\n" +
                                                                "				,mc_usuario_registro_manual.[us_cdgo]--60\n" +
                                                                "				,mc_usuario_registro_manual.[us_nombres]--61\n" +
                                                                "				,mc_usuario_registro_manual.[us_apellidos]--62\n" +
                                                                "				,mc_usuario_registro_manual.[us_correo]--63\n" +
                                                                "			,[mc_cntro_cost_auxiliarDestino_cdgo]--64\n" +
                                                                "				,mc_cntro_cost_auxiliarDestino.[cca_cdgo]--65\n" +
                                                                "				,mc_cntro_cost_auxiliarDestino.[cca_desc]--66\n" +
                                                                "			,[mc_cntro_cost_mayor_cdgo]--67\n" +
                                                                "				,mc_cntro_cost_mayor.[ccm_cdgo]--68\n" +
                                                                "				,mc_cntro_cost_mayor.[ccm_desc]--69\n" +
                                                                "      ,[mcle_asignacion_equipo_cdgo]--70\n" +
                                                                "      ,[mcle_mvto_equipo_cdgo]--71\n" +
                                                                "			,[me_cdgo] --72\n" +
                                                                "			,[me_asignacion_equipo_cdgo] --73\n" +
                                                                "				,[ae_cdgo] --74\n" +
                                                                "				,[ae_equipo_cdgo] --75\n" +
                                                                "					,[eq_cdgo] --76\n" +
                                                                "					,[eq_tipo_equipo_cdgo] --77\n" +
                                                                "						,[te_cdgo]  --78\n" +
                                                                "						,[te_desc] --79\n" +
                                                                "					,[eq_marca] --80\n" +
                                                                "					,[eq_modelo] --81\n" +
                                                                "					,[eq_desc]	 	--82\n" +
                                                                "				,[ae_fecha_registro] --83\n" +
                                                                "				,[ae_fecha_hora_inicio] --84\n" +
                                                                "				,[ae_fecha_hora_fin] --85\n" +
                                                                "				,[ae_cant_minutos] --86\n" +
                                                                "				,[ae_equipo_pertenencia_cdgo] --87\n" +
                                                                "					,[ep_cdgo] --88\n" +
                                                                "					,[ep_desc] --89\n" +
                                                                "					,[ep_estad] --90\n" +
                                                                "			,datename (MONTH ,[me_fecha])  -- 91\n" +
                                                                "			,[me_fecha] --92\n" +
                                                                "			,[me_proveedor_equipo_cdgo] --93\n" +
                                                                "				,[pe_cdgo] --94\n" +
                                                                "				,[pe_nit] --95\n" +
                                                                "				,[pe_desc] --96\n" +
                                                                "			,[me_num_orden] --97\n" +
                                                                "			,[me_cntro_oper_cdgo] --98\n" +
                                                                "				,me_cntro_oper.[co_cdgo] --99\n" +
                                                                "				,me_cntro_oper.[co_desc] --100\n" +
                                                                "			,[me_cntro_cost_auxiliar_cdgo] --101\n" +
                                                                "				,cca_origen.[cca_cdgo] --102\n" +
                                                                "				,cca_origen.[cca_cntro_cost_subcentro_cdgo] --103\n" +
                                                                "					,ccs_origen.[ccs_cdgo] --104\n" +
                                                                "					,ccs_origen.[ccs_desc] --105\n" +
                                                                "					,ccs_origen.[ccs_cntro_oper_cdgo] --106\n" +
                                                                "					,ccs_origen.[ccs_cntro_cost_rquiere_labor_realizda] --107\n" +
                                                                "				,cca_origen.[cca_desc] --108\n" +
                                                                "			,[me_cntro_cost_cdgo] --109\n" +
                                                                "				,me_cntro_cost.[cc_cdgo] --110\n" +
                                                                "				,me_cntro_cost.[cc_descripcion] --111\n" +
                                                                "			,[me_labor_realizada_cdgo] --112\n" +
                                                                "				,me_labor_realizada.[lr_cdgo] --113\n" +
                                                                "				,me_labor_realizada.[lr_desc] --114\n" +
                                                                "				,me_labor_realizada.[lr_operativa] --115\n" +
                                                                "				,me_labor_realizada.[lr_parada] --116\n" +
                                                                "			,[me_cliente_cdgo] --117\n" +
                                                                "				,me_cliente.[cl_cdgo] --118\n" +
                                                                "				,me_cliente.[cl_desc] --119\n" +
                                                                "			,[me_articulo_cdgo] --120\n" +
                                                                "				,me_articulo.[ar_cdgo] --121\n" +
                                                                "				,me_articulo.[ar_tipo_articulo_cdgo]--122\n" +
                                                                "					,me_tipo_articulo.[tar_cdgo] --123\n" +
                                                                "					,me_tipo_articulo.[tar_desc] --124\n" +
                                                                "					,me_tipo_articulo.[tar_cdgo_erp] --125\n" +
                                                                "					,me_tipo_articulo.[tar_undad_ngcio] --126\n" +
                                                                "				,me_articulo.[ar_desc] --127\n" +
                                                                "			,[me_motonave_cdgo] --128\n" +
                                                                "				,[mn_cdgo] --129\n" +
                                                                "				,[mn_desc] --130\n" +
                                                                "			,[me_fecha_hora_inicio] --131\n" +
                                                                "			,[me_fecha_hora_fin] --132\n" +
                                                                "			,[me_total_minutos] --133\n" +
                                                                "			,[me_valor_hora] --134\n" +
                                                                "			,[me_costo_total] --135\n" +
                                                                "			,[me_recobro_cdgo] --136\n" +
                                                                "				,[rc_cdgo] --137\n" +
                                                                "				,[rc_desc] --138\n" +
                                                                "				,[rc_estad] --139\n" +
                                                                "			,[me_cliente_recobro_cdgo] --140\n" +
                                                                "			,[me_costo_total_recobro_cliente] --141\n" +
                                                                "			,[me_usuario_registro_cdgo] --142\n" +
                                                                "				,us_registro.[us_cdgo] --143\n" +
                                                                "				,us_registro.[us_nombres] --144\n" +
                                                                "				,us_registro.[us_apellidos] --145\n" +
                                                                "				,us_registro.[us_correo] --146\n" +
                                                                "			,[me_usuario_autorizacion_cdgo] --147\n" +
                                                                "				,us_autoriza.[us_cdgo] --148\n" +
                                                                "				,us_autoriza.[us_nombres] --149\n" +
                                                                "				,us_autoriza.[us_apellidos] --150\n" +
                                                                "				,us_autoriza.[us_correo] --151\n" +
                                                                "			,[me_autorizacion_recobro_cdgo] --152\n" +
                                                                "				,[are_cdgo] --153\n" +
                                                                "				,[are_desc] --154\n" +
                                                                "				,[are_estad] --155\n" +
                                                                "			,[me_observ_autorizacion] --156\n" +
                                                                "			,[me_inactividad] --157\n" +
                                                                "			,[me_causa_inactividad_cdgo] --158\n" +
                                                                "				,[ci_cdgo] --159\n" +
                                                                "				,[ci_desc] --160\n" +
                                                                "				,[ci_estad] --161\n" +
                                                                "			,[me_usuario_inactividad_cdgo] --162\n" +
                                                                "				,us_inactividad.[us_cdgo] --163\n" +
                                                                "				,us_inactividad.[us_nombres] --164\n" +
                                                                "				,us_inactividad.[us_apellidos] --165\n" +
                                                                "				,us_inactividad.[us_correo] --166\n" +
                                                                "			,[me_motivo_parada_estado] --167\n" +
                                                                "			,[me_motivo_parada_cdgo] --168\n" +
                                                                "				,[mpa_cdgo] --169\n" +
                                                                "				,[mpa_desc] --170\n" +
                                                                "			,[me_observ] --171\n" +
                                                                "			,CASE [me_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_estado] --172\n" +
                                                                "			,CASE [me_desde_mvto_carbon] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [me_desde_mvto_carbon] --173\n" +
                                                                "			,[me_cntro_cost] --174\n" +
                                                                "			,[me_cntro_cost_auxiliarDestino_cdgo] --175\n" +
                                                                "				,cca_destino.[cca_cdgo] --176\n" +
                                                                "				,cca_destino.[cca_cntro_cost_subcentro_cdgo] --177\n" +
                                                                "					,ccs_destino.[ccs_cdgo] --178\n" +
                                                                "					,ccs_destino.[ccs_desc] --179\n" +
                                                                "					,ccs_destino.[ccs_cntro_oper_cdgo] --180\n" +
                                                                "					,ccs_destino.[ccs_cntro_cost_rquiere_labor_realizda] --181\n" +
                                                                "				,cca_destino.[cca_desc] --182\n" +
                                                                "			,[me_cntro_cost_mayor_cdgo] --183\n" +
                                                                "				,me_cntro_cost_mayor.[ccm_cdgo] --184\n" +
                                                                "				,me_cntro_cost_mayor.[ccm_desc]  --  185    \n" +
                                                                "				,Tiempo_Vehiculo_Descargue=(SELECT (DATEDIFF (MINUTE,  [mc_fecha_inicio_descargue], [mc_fecha_fin_descargue])))  --186\n" +
                                                                "				,Tiempo_Equipo_Descargue=(SELECT (DATEDIFF (MINUTE,  [me_fecha_hora_inicio], [me_fecha_hora_fin])))  --187                                        \n" +
                                                                "      ,CASE [mcle_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [mcle_estado]--188\n" +
                                                                "      ,[mc_equipo_lavado_cdgo]--189 \n" +
                                                                "      ,[mc_lavado_vehiculo]--190 \n" +
                                                                "      ,[mc_costoLavadoVehiculo]--191 \n" +
                                                                "      ,[mc_valorRecaudoEmpresa_lavadoVehiculo]--192 \n" +
                                                                "      ,[mc_valorRecaudoEquipo_lavadoVehiculo]--193 \n" +
                    
                                                                "      ,mc_cntro_cost_base_datos.[bd_cdgo]   --194 \n" +
                                                                "      ,mc_articulo_base_datos.[bd_cdgo]   --195 \n" +
                                                                "      ,mc_cliente_base_datos.[bd_cdgo]   --196 \n" +
                                                                "      ,mc_transprtdora_base_datos.[bd_cdgo]   --197 \n" +
                                                                "      ,mc_cntro_cost_mayor_base_datos.[bd_cdgo]   --198 \n" +
                    
                                                                "      ,me_cntro_cost_base_datos.[bd_cdgo]   --199 \n" +
                                                                "      ,me_cliente_base_datos.[bd_cdgo]   --200 \n" +
                                                                "      ,me_articulo_base_datos.[bd_cdgo]   --201 \n" +
                                                                "      ,me_motonave_base_datos.[bd_cdgo]   --202 \n" +
                                                                "      ,me_cntro_cost_mayor_base_datos.[bd_cdgo]  --203 \n" +
                    
                                                                "  FROM ["+DB+"].[dbo].[mvto_carbon_listado_equipo]\n" +
                                                                "		INNER JOIN ["+DB+"].[dbo].[mvto_carbon] ON [mcle_mvto_carbon_cdgo]=[mc_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_oper] mc_cntro_oper ON [mc_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                "		LEFT JOIN  ["+DB+"].[dbo].[cntro_cost_auxiliar] mc_cntro_cost_auxiliar ON [mc_cntro_cost_auxiliar_cdgo]=mc_cntro_cost_auxiliar.[cca_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] mc_cntro_cost_subcentro ON mc_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo]=mc_cntro_cost_subcentro.[ccs_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost] mc_cntro_cost ON [mc_cntro_cost_cdgo]=mc_cntro_cost.[cc_cdgo]\n" +      
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cntro_cost_base_datos ON mc_cntro_cost.[cc_cliente_base_datos_cdgo]=mc_cntro_cost_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[labor_realizada] mc_labor_realizada ON [mc_labor_realizada_cdgo] =  mc_labor_realizada.[lr_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[articulo] mc_articulo ON [mc_articulo_cdgo]=mc_articulo.[ar_cdgo] AND mc_articulo.[ar_base_datos_cdgo]=[mc_articulo_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_articulo_base_datos ON mc_articulo.[ar_base_datos_cdgo]=mc_articulo_base_datos.[bd_cdgo] \n"+
                                                                        "		LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] mc_tipo_articulo ON mc_articulo.[ar_tipo_articulo_cdgo]=mc_tipo_articulo.[tar_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente] mc_cliente ON [mc_cliente_cdgo]=mc_cliente.[cl_cdgo] AND mc_cliente.[cl_base_datos_cdgo]=[mc_cliente_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cliente_base_datos ON mc_cliente.[cl_base_datos_cdgo]=mc_cliente_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[transprtdora] ON [mc_transprtdora_cdgo]=[tr_cdgo] AND [tr_base_datos_cdgo]=[mc_transprtdora_base_datos_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_transprtdora_base_datos ON [tr_base_datos_cdgo]=mc_transprtdora_base_datos.[bd_cdgo] \n"+    
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] mc_usuarioQuienRegistra ON [mc_usuario_cdgo]=mc_usuarioQuienRegistra.[us_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[estad_mvto_carbon] ON [mc_estad_mvto_carbon_cdgo]=[emc_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] mc_usuario_registro_manual ON [mc_usuario_registro_manual_cdgo]=mc_usuario_registro_manual.[us_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] mc_cntro_cost_auxiliarDestino ON [mc_cntro_cost_auxiliarDestino_cdgo]=mc_cntro_cost_auxiliarDestino.[cca_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor] mc_cntro_cost_mayor ON [mc_cntro_cost_mayor_cdgo]=mc_cntro_cost_mayor.[ccm_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cntro_cost_mayor_base_datos ON [ccm_cliente_base_datos_cdgo]=mc_cntro_cost_mayor_base_datos.[bd_cdgo] \n"+  
                                                                
                                                                
                                                                
                                                                    
                                                                                                                                               
                                                                "               LEFT JOIN ["+DB+"].[dbo].[mvto_equipo] ON [mcle_mvto_equipo_cdgo]=[me_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [me_asignacion_equipo_cdgo]=[ae_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [me_proveedor_equipo_cdgo]=[pe_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_oper] me_cntro_oper ON [me_cntro_oper_cdgo]=me_cntro_oper.[co_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_origen ON  [me_cntro_cost_auxiliar_cdgo]=cca_origen.[cca_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_origen ON cca_origen.[cca_cntro_cost_subcentro_cdgo]=ccs_origen.[ccs_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost] me_cntro_cost ON [me_cntro_cost_cdgo]=me_cntro_cost.[cc_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_base_datos ON me_cntro_cost.[cc_cliente_base_datos_cdgo]=me_cntro_cost_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[labor_realizada] me_labor_realizada ON [me_labor_realizada_cdgo]=me_labor_realizada.[lr_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente] me_cliente ON [me_cliente_cdgo]=me_cliente.[cl_cdgo] AND me_cliente.[cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON me_cliente.[cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[articulo] me_articulo ON [me_articulo_cdgo]=me_articulo.[ar_cdgo] AND	me_articulo.[ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON me_articulo.[ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] me_tipo_articulo ON me_articulo.[ar_tipo_articulo_cdgo]=me_tipo_articulo.[tar_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[me_motonave_base_datos_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_motonave_base_datos ON  [mn_base_datos_cdgo]=me_motonave_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] us_registro ON [me_usuario_registro_cdgo]=us_registro.[us_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] us_autoriza ON [me_usuario_autorizacion_cdgo]=us_autoriza.[us_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[autorizacion_recobro] ON [me_autorizacion_recobro_cdgo]=[are_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[causa_inactividad] ON [me_causa_inactividad_cdgo]=[ci_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] us_inactividad ON [me_usuario_inactividad_cdgo]=us_inactividad.[us_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[motivo_parada] ON [me_motivo_parada_cdgo]=[mpa_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_destino ON [me_cntro_cost_auxiliarDestino_cdgo]=cca_destino.[cca_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_destino ON cca_destino.[cca_cntro_cost_subcentro_cdgo]=ccs_destino.[ccs_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor] me_cntro_cost_mayor ON [me_cntro_cost_mayor_cdgo]=me_cntro_cost_mayor.[ccm_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_mayor_base_datos ON me_cntro_cost_mayor.[ccm_cliente_base_datos_cdgo]=me_cntro_cost_mayor_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON [ae_equipo_pertenencia_cdgo]=[ep_cdgo]"
                                                                + "	WHERE [mc_cdgo]=? AND [me_inactividad]=0 AND [mc_estad_mvto_carbon_cdgo]=1 ORDER BY [mcle_mvto_carbon_cdgo] DESC");
            query.setString(1, mvtoCarbonI.getCodigo());
            ResultSet resultSet; resultSet= query.executeQuery();    
            while(resultSet.next()){ 
                try{
                    MvtoCarbon_ListadoEquipos mvto_listEquipo = new MvtoCarbon_ListadoEquipos();
                    mvto_listEquipo.setCodigo(resultSet.getString(1));
                        MvtoCarbon mvtoCarbon = new MvtoCarbon();
                        mvtoCarbon.setCodigo(resultSet.getString(3));
                        mvtoCarbon.setCentroOperacion(new CentroOperacion(Integer.parseInt(resultSet.getString(5)),resultSet.getString(6),""));
                        mvtoCarbon.setCentroCostoAuxiliar(new CentroCostoAuxiliar(Integer.parseInt(resultSet.getString(8)),new CentroCostoSubCentro(Integer.parseInt(resultSet.getString(10)),resultSet.getString(11),""),resultSet.getString(13),""));

                            CentroCosto mc_CentroCosto = new CentroCosto();
                                mc_CentroCosto.setCodigo(resultSet.getString(15));
                                mc_CentroCosto.setDescripción(resultSet.getString(16));
                                
                                mc_CentroCosto.setClienteBaseDatos(resultSet.getString(194));
                        mvtoCarbon.setCentroCosto(mc_CentroCosto);
                            LaborRealizada mc_LaborRealizada = new LaborRealizada();
                                mc_LaborRealizada.setCodigo(resultSet.getString(18));
                                mc_LaborRealizada.setDescripcion(resultSet.getString(19));
                        mvtoCarbon.setLaborRealizada(mc_LaborRealizada);
                            Articulo mc_Articulo = new Articulo();
                                mc_Articulo.setCodigo(resultSet.getString(21));
                                    TipoArticulo mc_TipoArticulo = new TipoArticulo();
                                    mc_TipoArticulo.setCodigo(resultSet.getString(23));
                                    mc_TipoArticulo.setDescripcion(resultSet.getString(24));
                                    mc_TipoArticulo.setCodigoERP(resultSet.getString(25));
                                    mc_TipoArticulo.setUnidadNegocio(resultSet.getString(26));
                                mc_Articulo.setTipoArticulo(mc_TipoArticulo);
                                mc_Articulo.setDescripcion(resultSet.getString(27));
                                mc_Articulo.setBaseDatos(new BaseDatos(resultSet.getString(195)));
                        mvtoCarbon.setArticulo(mc_Articulo);
                            Cliente mc_cliente = new Cliente();
                            mc_cliente.setCodigo(resultSet.getString(29));
                            mc_cliente.setDescripcion(resultSet.getString(30));
                            mc_cliente.setBaseDatos(new BaseDatos(resultSet.getString(196)));
                        
                        mvtoCarbon.setCliente(mc_cliente);
                            Transportadora mc_trTransportadora = new Transportadora();
                            mc_trTransportadora.setCodigo(resultSet.getString(32));
                            mc_trTransportadora.setNit(resultSet.getString(33));
                            mc_trTransportadora.setDescripcion(resultSet.getString(34));
                            mc_trTransportadora.setBaseDatos(new BaseDatos(resultSet.getString(197)));
                        mvtoCarbon.setTransportadora(mc_trTransportadora);
                        mvtoCarbon.setMes(resultSet.getString(35));
                        mvtoCarbon.setFechaRegistro(resultSet.getString(36));
                        mvtoCarbon.setNumero_orden(resultSet.getString(37));
                        mvtoCarbon.setDeposito(resultSet.getString(38));
                        mvtoCarbon.setConsecutivo(resultSet.getString(39));
                        mvtoCarbon.setPlaca(resultSet.getString(40));
                        mvtoCarbon.setPesoVacio(resultSet.getString(41));
                        mvtoCarbon.setPesoLleno(resultSet.getString(42));
                        mvtoCarbon.setPesoNeto(resultSet.getString(43));
                        mvtoCarbon.setFechaEntradaVehiculo(resultSet.getString(44));
                        mvtoCarbon.setFecha_SalidaVehiculo(resultSet.getString(45));    
                        mvtoCarbon.setFechaInicioDescargue(resultSet.getString(46));    
                        mvtoCarbon.setFechaFinDescargue(resultSet.getString(47));      
                            Usuario mc_usuarioQuienRegistra = new Usuario();           
                            mc_usuarioQuienRegistra.setCodigo(resultSet.getString(49));
                            mc_usuarioQuienRegistra.setNombres(resultSet.getString(50));
                            mc_usuarioQuienRegistra.setApellidos(resultSet.getString(51));
                            mc_usuarioQuienRegistra.setCorreo(resultSet.getString(52));
                        mvtoCarbon.setUsuarioRegistroMovil(mc_usuarioQuienRegistra);
                        mvtoCarbon.setObservacion(resultSet.getString(53));
                            EstadoMvtoCarbon estadMvtoCarbon = new EstadoMvtoCarbon();
                            estadMvtoCarbon.setCodigo(resultSet.getString(55));
                            estadMvtoCarbon.setDescripcion(resultSet.getString(56));
                        mvtoCarbon.setEstadoMvtoCarbon(estadMvtoCarbon);
                        mvtoCarbon.setConexionPesoCcarga(resultSet.getString(57));
                        mvtoCarbon.setRegistroManual(resultSet.getString(58));
                            Usuario mc_usuarioRegManual = new Usuario();           
                            mc_usuarioRegManual.setCodigo(resultSet.getString(60));
                            mc_usuarioRegManual.setNombres(resultSet.getString(61));
                            mc_usuarioRegManual.setApellidos(resultSet.getString(62));
                            mc_usuarioRegManual.setCorreo(resultSet.getString(63));
                        mvtoCarbon.setUsuarioRegistraManual(mc_usuarioRegManual);
                        CentroCostoAuxiliar mc_CentroCostosAuxilarDestino = new CentroCostoAuxiliar();
                        if(resultSet.getString(65) != null){
                            mc_CentroCostosAuxilarDestino.setCodigo(Integer.parseInt(resultSet.getString(65)));
                            mc_CentroCostosAuxilarDestino.setDescripcion(resultSet.getString(66));
                        
                        }else{                         
                            mc_CentroCostosAuxilarDestino.setCodigo(-1);
                            mc_CentroCostosAuxilarDestino.setDescripcion(null);
                        }
                        mvtoCarbon.setCentroCostoAuxiliarDestino(mc_CentroCostosAuxilarDestino); 
                        
                        mvtoCarbon.setCantidadHorasDescargue(resultSet.getString(186));
                            CentroCostoMayor mc_CentroCostoMayor = new CentroCostoMayor();
                            mc_CentroCostoMayor.setCodigo(resultSet.getString(68));
                            mc_CentroCostoMayor.setDescripcion(resultSet.getString(69));  
                            mc_CentroCostoMayor.setClienteBaseDatos(resultSet.getString(198));
                        mvtoCarbon.setCentroCostoMayor(mc_CentroCostoMayor);
                        Equipo equipoTem1 = new Equipo();
                        equipoTem1.setCodigo(resultSet.getString(189));
                        mvtoCarbon.setEquipoLavadoVehiculo(equipoTem1);
                        mvtoCarbon.setLavadoVehiculo(resultSet.getString(190));
                        mvtoCarbon.setCostoLavadoVehiculo(resultSet.getString(191));
                        mvtoCarbon.setValorRecaudoEmpresa_lavadoVehiculo(resultSet.getString(192));
                        mvtoCarbon.setValorRecaudoEquipo_lavadoVehiculo(resultSet.getString(193));
                    mvto_listEquipo.setMvtoCarbon(mvtoCarbon);
                        AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                        asignacionEquipo.setCodigo(resultSet.getString(74));
                        asignacionEquipo.setFechaRegistro(resultSet.getString(83));
                        asignacionEquipo.setFechaHoraInicio(resultSet.getString(84));
                        asignacionEquipo.setFechaHoraFin(resultSet.getString(85));
                        asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(86));
                            Equipo equipo = new Equipo(); 
                            equipo.setCodigo(resultSet.getString(76));
                            equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(78),resultSet.getString(79),""));
                            equipo.setMarca(resultSet.getString(80));
                            equipo.setModelo(resultSet.getString(81));
                            equipo.setDescripcion(resultSet.getString(82));
                            equipo.setPertenenciaEquipo(new Pertenencia(resultSet.getString(88),resultSet.getString(89),resultSet.getString(90)));
                        asignacionEquipo.setEquipo(equipo);
                        asignacionEquipo.setPertenencia(new Pertenencia(resultSet.getString(88),resultSet.getString(89),resultSet.getString(90)));
                    mvto_listEquipo.setAsignacionEquipo(asignacionEquipo);
                        MvtoEquipo mvtoEquipo = new MvtoEquipo();
                        mvtoEquipo.setCodigo(resultSet.getString(72));
                        mvtoEquipo.setAsignacionEquipo(asignacionEquipo);
                        mvtoEquipo.setMes(resultSet.getString(91));
                        mvtoEquipo.setFechaRegistro(resultSet.getString(92));
                        mvtoEquipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(94),resultSet.getString(95),resultSet.getString(96),""));
                        mvtoEquipo.setNumeroOrden(resultSet.getString(97));
                            CentroOperacion me_co= new CentroOperacion();
                            me_co.setCodigo(Integer.parseInt(resultSet.getString(99)));
                            me_co.setDescripcion(resultSet.getString(100));
                        mvtoEquipo.setCentroOperacion(me_co);
                            CentroCostoSubCentro centroCostoSubCentro_mvtoEquipo = new CentroCostoSubCentro();
                            centroCostoSubCentro_mvtoEquipo.setCodigo(resultSet.getInt(104));
                            centroCostoSubCentro_mvtoEquipo.setDescripcion(resultSet.getString(105));
                            centroCostoSubCentro_mvtoEquipo.setCentroCostoRequiereLaborRealizada(resultSet.getString(107));
                            CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipo = new CentroCostoAuxiliar();
                            centroCostoAuxiliar_mvtoEquipo.setCodigo(resultSet.getInt(102));
                            centroCostoAuxiliar_mvtoEquipo.setDescripcion(resultSet.getString(108));
                            centroCostoAuxiliar_mvtoEquipo.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipo);
                        mvtoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipo);
                            CentroCosto centroCosto = new CentroCosto();
                            centroCosto.setCodigo(resultSet.getString(110));
                            centroCosto.setDescripción(resultSet.getString(111));
                            centroCosto.setClienteBaseDatos(resultSet.getString(199));
                        mvtoEquipo.setCentroCosto(centroCosto);
                            LaborRealizada laborRealizadaT = new LaborRealizada();
                            laborRealizadaT.setCodigo(resultSet.getString(113));
                            laborRealizadaT.setDescripcion(resultSet.getString(114));
                            laborRealizadaT.setEs_operativa(resultSet.getString(115));
                            laborRealizadaT.setEs_parada(resultSet.getString(116));
                        mvtoEquipo.setLaborRealizada(laborRealizadaT);
                        Cliente me_cliente = new Cliente();
                        me_cliente.setCodigo(resultSet.getString(118));
                        me_cliente.setDescripcion(resultSet.getString(119));
                        me_cliente.setBaseDatos(new BaseDatos( resultSet.getString(200)));
                        
                        mvtoEquipo.setCliente(me_cliente);
                            TipoArticulo tipoArticulo = new TipoArticulo();
                                    tipoArticulo.setCodigo(resultSet.getString(123));
                                    tipoArticulo.setDescripcion(resultSet.getString(124));
                                    tipoArticulo.setCodigoERP(resultSet.getString(125));
                                    tipoArticulo.setUnidadNegocio(resultSet.getString(126));
                            Articulo articulo = new Articulo();
                            articulo.setCodigo(resultSet.getString(121));
                            articulo.setDescripcion(resultSet.getString(127));
                            articulo.setTipoArticulo(tipoArticulo);
                            articulo.setBaseDatos(new BaseDatos( resultSet.getString(201)));
                        mvtoEquipo.setArticulo(articulo);
                        Motonave motonave = new Motonave();
                        motonave.setCodigo(resultSet.getString(129));
                        motonave.setDescripcion(resultSet.getString(130));
                        motonave.setBaseDatos(new BaseDatos( resultSet.getString(202)));
                    mvtoEquipo.setMotonave(motonave);
                    mvtoEquipo.setFechaHoraInicio(resultSet.getString(131));
                    mvtoEquipo.setFechaHoraFin(resultSet.getString(132));
                    mvtoEquipo.setTotalMinutos(resultSet.getString(133));
                    mvtoEquipo.setValorHora(resultSet.getString(134));
                    mvtoEquipo.setCostoTotal(resultSet.getString(135));
                            Recobro recobro = new Recobro();
                            recobro.setCodigo(resultSet.getString(137));
                            recobro.setDescripcion(resultSet.getString(138));
                    mvtoEquipo.setRecobro(recobro);
                            Usuario usuario_me_registra = new Usuario();
                            usuario_me_registra.setCodigo(resultSet.getString(143));
                            usuario_me_registra.setNombres(resultSet.getString(144));
                            usuario_me_registra.setApellidos(resultSet.getString(145));
                            usuario_me_registra.setCorreo(resultSet.getString(146));
                        mvtoEquipo.setUsuarioQuieRegistra(usuario_me_registra);
                            Usuario usuario_me_autoriza = new Usuario();
                            usuario_me_autoriza.setCodigo(resultSet.getString(148));
                            usuario_me_autoriza.setNombres(resultSet.getString(149));
                            usuario_me_autoriza.setApellidos(resultSet.getString(150));
                            usuario_me_autoriza.setCorreo(resultSet.getString(151));
                        mvtoEquipo.setUsuarioAutorizaRecobro(usuario_me_autoriza);
                            AutorizacionRecobro autorizacionRecobro = new AutorizacionRecobro();
                            autorizacionRecobro.setCodigo(resultSet.getString(153));
                            autorizacionRecobro.setDescripcion(resultSet.getString(154));
                            autorizacionRecobro.setEstado(resultSet.getString(155));
                        mvtoEquipo.setAutorizacionRecobro(autorizacionRecobro);
                        mvtoEquipo.setObservacionAutorizacion(resultSet.getString(156));
                        mvtoEquipo.setInactividad(resultSet.getString(157));
                            CausaInactividad causaInactividad = new CausaInactividad();
                            causaInactividad.setCodigo(resultSet.getString(159));
                            causaInactividad.setDescripcion(resultSet.getString(160));
                            causaInactividad.setEstado(resultSet.getString(161));
                        mvtoEquipo.setCausaInactividad(causaInactividad);
                            Usuario usuario_me_us_inactividad = new Usuario();
                            usuario_me_us_inactividad.setCodigo(resultSet.getString(163));
                            usuario_me_us_inactividad.setNombres(resultSet.getString(164));
                            usuario_me_us_inactividad.setApellidos(resultSet.getString(165));
                            usuario_me_us_inactividad.setCorreo(resultSet.getString(166));
                        mvtoEquipo.setUsuarioInactividad(usuario_me_us_inactividad);
                            MotivoParada motivoParada= new MotivoParada();
                            motivoParada.setCodigo(resultSet.getString(169));
                            motivoParada.setDescripcion(resultSet.getString(170));
                        mvtoEquipo.setMotivoParada(motivoParada);
                        mvtoEquipo.setObservacionMvtoEquipo(resultSet.getString(171));
                        mvtoEquipo.setEstado(resultSet.getString(172));
                        mvtoEquipo.setDesdeCarbon(resultSet.getString(173));
                        mvtoEquipo.setCentroCostoDescripción(resultSet.getString(174));
                        CentroCostoSubCentro centroCostoSubCentro_mvtoEquipoDestino = new CentroCostoSubCentro();
                            centroCostoSubCentro_mvtoEquipoDestino.setCodigo(resultSet.getInt(178));
                            centroCostoSubCentro_mvtoEquipoDestino.setDescripcion(resultSet.getString(179));
                            centroCostoSubCentro_mvtoEquipoDestino.setCentroCostoRequiereLaborRealizada(resultSet.getString(181));
                            CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipoDestino = new CentroCostoAuxiliar();
                            centroCostoAuxiliar_mvtoEquipoDestino.setCodigo(resultSet.getInt(176));
                            centroCostoAuxiliar_mvtoEquipoDestino.setDescripcion(resultSet.getString(182));
                            centroCostoAuxiliar_mvtoEquipoDestino.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipoDestino);
                        mvtoEquipo.setCentroCostoAuxiliarDestino(centroCostoAuxiliar_mvtoEquipoDestino);
                            CentroCostoMayor centroCostoMayor = new CentroCostoMayor();
                            centroCostoMayor.setCodigo(resultSet.getString(184));
                            centroCostoMayor.setDescripcion(resultSet.getString(185));
                            centroCostoMayor.setClienteBaseDatos(resultSet.getString(203));
                        mvtoEquipo.setCentroCostoMayor(centroCostoMayor);    
                        //mvtoEquipo.setTotalMinutos(resultSet.getString(187)); 
                    mvto_listEquipo.setMvtoEquipo(mvtoEquipo);  
                    mvto_listEquipo.setEstado(resultSet.getString(188));
                    try{
                        TarifaEquipo tarifa=new ControlDB_Equipo(tipoConexion).buscarTarifaEquipoPorAño(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getCodigo(),mvto_listEquipo.getMvtoEquipo().getFechaHoraInicio());
                        if(tarifa !=null){
                            if(mvto_listEquipo.getMvtoEquipo().getFechaHoraFin() != null){
                                mvto_listEquipo.getMvtoEquipo().setValorHora(tarifa.getValorHoraOperacion());
                                try{
                                    double des=Double.parseDouble(mvto_listEquipo.getMvtoEquipo().getTotalMinutos());
                                    double totalHoras = Double.parseDouble(""+(des/60));
                                    double valorHora =Double.parseDouble(tarifa.getValorHoraOperacion());
                                    //mvto_listEquipo.getMvtoEquipo().setCostoTotal(""+(totalHoras * valorHora ));  
                                    DecimalFormat formato2 = new DecimalFormat("0.00");
                                    mvto_listEquipo.getMvtoEquipo().setCostoTotal(formato2.format((totalHoras * valorHora )));
                                    //mvto_listEquipo.getMvtoEquipo().setCostoTotalRecobroCliente(formato2.format((totalHoras * valorHora )));
                                    mvto_listEquipo.getMvtoEquipo().setCostoTotalRecobroCliente("0");
                                    mvto_listEquipo.getMvtoCarbon().setCostoLavadoVehiculo(tarifa.getCostoLavadoVehiculo());
                                    mvto_listEquipo.getMvtoCarbon().setValorRecaudoEmpresa_lavadoVehiculo(tarifa.getValorRecaudoEmpresa());
                                    mvto_listEquipo.getMvtoCarbon().setValorRecaudoEquipo_lavadoVehiculo(tarifa.getValorRecaudoEquipo());
                                }catch(Exception e){
                                    //mvto_listEquipo.getMvtoEquipo().setCostoTotal("NO PUDE");
                                }
                            }
                        }
                    }catch(Exception e){
                       System.out.println("Error al procesar tarifa");
                    }

                    listadoObjetos.add(mvto_listEquipo);    
                }catch(Exception e){
                    e.printStackTrace();
                }
                
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los Movimientos de Carbon");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }
    public ArrayList<MvtoCarbon_ListadoEquipos>     buscarMvtoCarbonUnicos_Inactivos(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<MvtoCarbon_ListadoEquipos> listadoObjetos = new ArrayList();
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT [mcle_cdgo]--1\n" +
                                                                "      ,[mcle_mvto_carbon_cdgo]--2\n" +
                                                                "			,[mc_cdgo]--3\n" +
                                                                "			,[mc_cntro_oper_cdgo]--4\n" +
                                                                "				,mc_cntro_oper.[co_cdgo]--5\n" +
                                                                "				,mc_cntro_oper.[co_desc]--6\n" +
                                                                "			,[mc_cntro_cost_auxiliar_cdgo]--7\n" +
                                                                "				,mc_cntro_cost_auxiliar.[cca_cdgo]--8\n" +
                                                                "				,mc_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo]--9\n" +
                                                                "					,mc_cntro_cost_subcentro.[ccs_cdgo]--10\n" +
                                                                "					,mc_cntro_cost_subcentro.[ccs_desc]--11\n" +
                                                                "					,mc_cntro_cost_subcentro.[ccs_cntro_cost_rquiere_labor_realizda]--12\n" +
                                                                "				,mc_cntro_cost_auxiliar.[cca_desc]--13\n" +
                                                                "			,[mc_cntro_cost_cdgo]--14\n" +
                                                                "					,mc_cntro_cost.[cc_cdgo]--15\n" +
                                                                "					,mc_cntro_cost.[cc_descripcion]--16\n" +
                                                                "			,[mc_labor_realizada_cdgo]--17\n" +
                                                                "					,mc_labor_realizada.[lr_cdgo]--18\n" +
                                                                "					,mc_labor_realizada.[lr_desc]	--19\n" +
                                                                "			,[mc_articulo_cdgo]--20\n" +
                                                                "					,mc_articulo.[ar_cdgo]--21\n" +
                                                                "					,mc_articulo.[ar_tipo_articulo_cdgo]--22\n" +
                                                                "						,mc_tipo_articulo.[tar_cdgo]--23\n" +
                                                                "						,mc_tipo_articulo.[tar_desc]--24\n" +
                                                                "						,mc_tipo_articulo.[tar_cdgo_erp]--25\n" +
                                                                "						,mc_tipo_articulo.[tar_undad_ngcio]--26\n" +
                                                                "					,mc_articulo.[ar_desc]--27\n" +
                                                                "			,[mc_cliente_cdgo]--28\n" +
                                                                "				,mc_cliente.[cl_cdgo]--29\n" +
                                                                "				,mc_cliente.[cl_desc]--30\n" +
                                                                "			,[mc_transprtdora_cdgo]--31\n" +
                                                                "				,[tr_cdgo]--32\n" +
                                                                "				,[tr_nit]--33\n" +
                                                                "				,[tr_desc]--34\n" +
                                                                "			,datename (MONTH ,[mc_fecha])--35\n" +
                                                                "			,[mc_fecha]--36\n" +
                                                                "			,[mc_num_orden]--37\n" +
                                                                "			,[mc_deposito]--38\n" +
                                                                "			,[mc_consecutivo_tqute]--39\n" +
                                                                "			,[mc_placa_vehiculo]--40\n" +
                                                                "			,[mc_peso_vacio]--41\n" +
                                                                "			,[mc_peso_lleno]--42\n" +
                                                                "			,[mc_peso_neto]--43\n" +
                                                                "			,[mc_fecha_entrad]--44\n" +
                                                                "			,[mc_fecha_salid]--45\n" +
                                                                "			,[mc_fecha_inicio_descargue]--46\n" +
                                                                "			,[mc_fecha_fin_descargue]--47\n" +
                                                                "			,[mc_usuario_cdgo]--48\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_cdgo]--49\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_nombres]--50\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_apellidos]--51\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_correo]--52\n" +
                                                                "			,[mc_observ]--53\n" +
                                                                "			,[mc_estad_mvto_carbon_cdgo]--54\n" +
                                                                "				,[emc_cdgo]--55\n" +
                                                                "				,[emc_desc]--56\n" +
                                                                "			,[mc_conexion_peso_ccarga]--57\n" +
                                                                "			,[mc_registro_manual]--58\n" +
                                                                "			,[mc_usuario_registro_manual_cdgo]--59\n" +
                                                                "				,mc_usuario_registro_manual.[us_cdgo]--60\n" +
                                                                "				,mc_usuario_registro_manual.[us_nombres]--61\n" +
                                                                "				,mc_usuario_registro_manual.[us_apellidos]--62\n" +
                                                                "				,mc_usuario_registro_manual.[us_correo]--63\n" +
                                                                "			,[mc_cntro_cost_auxiliarDestino_cdgo]--64\n" +
                                                                "				,mc_cntro_cost_auxiliarDestino.[cca_cdgo]--65\n" +
                                                                "				,mc_cntro_cost_auxiliarDestino.[cca_desc]--66\n" +
                                                                "			,[mc_cntro_cost_mayor_cdgo]--67\n" +
                                                                "				,mc_cntro_cost_mayor.[ccm_cdgo]--68\n" +
                                                                "				,mc_cntro_cost_mayor.[ccm_desc]--69\n" +
                                                                "      ,[mcle_asignacion_equipo_cdgo]--70\n" +
                                                                "      ,[mcle_mvto_equipo_cdgo]--71\n" +
                                                                "			,[me_cdgo] --72\n" +
                                                                "			,[me_asignacion_equipo_cdgo] --73\n" +
                                                                "				,[ae_cdgo] --74\n" +
                                                                "				,[ae_equipo_cdgo] --75\n" +
                                                                "					,[eq_cdgo] --76\n" +
                                                                "					,[eq_tipo_equipo_cdgo] --77\n" +
                                                                "						,[te_cdgo]  --78\n" +
                                                                "						,[te_desc] --79\n" +
                                                                "					,[eq_marca] --80\n" +
                                                                "					,[eq_modelo] --81\n" +
                                                                "					,[eq_desc]	 	--82\n" +
                                                                "				,[ae_fecha_registro] --83\n" +
                                                                "				,[ae_fecha_hora_inicio] --84\n" +
                                                                "				,[ae_fecha_hora_fin] --85\n" +
                                                                "				,[ae_cant_minutos] --86\n" +
                                                                "				,[ae_equipo_pertenencia_cdgo] --87\n" +
                                                                "					,[ep_cdgo] --88\n" +
                                                                "					,[ep_desc] --89\n" +
                                                                "					,[ep_estad] --90\n" +
                                                                "			,datename (MONTH ,[me_fecha])  -- 91\n" +
                                                                "			,[me_fecha] --92\n" +
                                                                "			,[me_proveedor_equipo_cdgo] --93\n" +
                                                                "				,[pe_cdgo] --94\n" +
                                                                "				,[pe_nit] --95\n" +
                                                                "				,[pe_desc] --96\n" +
                                                                "			,[me_num_orden] --97\n" +
                                                                "			,[me_cntro_oper_cdgo] --98\n" +
                                                                "				,me_cntro_oper.[co_cdgo] --99\n" +
                                                                "				,me_cntro_oper.[co_desc] --100\n" +
                                                                "			,[me_cntro_cost_auxiliar_cdgo] --101\n" +
                                                                "				,cca_origen.[cca_cdgo] --102\n" +
                                                                "				,cca_origen.[cca_cntro_cost_subcentro_cdgo] --103\n" +
                                                                "					,ccs_origen.[ccs_cdgo] --104\n" +
                                                                "					,ccs_origen.[ccs_desc] --105\n" +
                                                                "					,ccs_origen.[ccs_cntro_oper_cdgo] --106\n" +
                                                                "					,ccs_origen.[ccs_cntro_cost_rquiere_labor_realizda] --107\n" +
                                                                "				,cca_origen.[cca_desc] --108\n" +
                                                                "			,[me_cntro_cost_cdgo] --109\n" +
                                                                "				,me_cntro_cost.[cc_cdgo] --110\n" +
                                                                "				,me_cntro_cost.[cc_descripcion] --111\n" +
                                                                "			,[me_labor_realizada_cdgo] --112\n" +
                                                                "				,me_labor_realizada.[lr_cdgo] --113\n" +
                                                                "				,me_labor_realizada.[lr_desc] --114\n" +
                                                                "				,me_labor_realizada.[lr_operativa] --115\n" +
                                                                "				,me_labor_realizada.[lr_parada] --116\n" +
                                                                "			,[me_cliente_cdgo] --117\n" +
                                                                "				,me_cliente.[cl_cdgo] --118\n" +
                                                                "				,me_cliente.[cl_desc] --119\n" +
                                                                "			,[me_articulo_cdgo] --120\n" +
                                                                "				,me_articulo.[ar_cdgo] --121\n" +
                                                                "				,me_articulo.[ar_tipo_articulo_cdgo]--122\n" +
                                                                "					,me_tipo_articulo.[tar_cdgo] --123\n" +
                                                                "					,me_tipo_articulo.[tar_desc] --124\n" +
                                                                "					,me_tipo_articulo.[tar_cdgo_erp] --125\n" +
                                                                "					,me_tipo_articulo.[tar_undad_ngcio] --126\n" +
                                                                "				,me_articulo.[ar_desc] --127\n" +
                                                                "			,[me_motonave_cdgo] --128\n" +
                                                                "				,[mn_cdgo] --129\n" +
                                                                "				,[mn_desc] --130\n" +
                                                                "			,[me_fecha_hora_inicio] --131\n" +
                                                                "			,[me_fecha_hora_fin] --132\n" +
                                                                "			,[me_total_minutos] --133\n" +
                                                                "			,[me_valor_hora] --134\n" +
                                                                "			,[me_costo_total] --135\n" +
                                                                "			,[me_recobro_cdgo] --136\n" +
                                                                "				,[rc_cdgo] --137\n" +
                                                                "				,[rc_desc] --138\n" +
                                                                "				,[rc_estad] --139\n" +
                                                                "			,[me_cliente_recobro_cdgo] --140\n" +
                                                                "			,[me_costo_total_recobro_cliente] --141\n" +
                                                                "			,[me_usuario_registro_cdgo] --142\n" +
                                                                "				,us_registro.[us_cdgo] --143\n" +
                                                                "				,us_registro.[us_nombres] --144\n" +
                                                                "				,us_registro.[us_apellidos] --145\n" +
                                                                "				,us_registro.[us_correo] --146\n" +
                                                                "			,[me_usuario_autorizacion_cdgo] --147\n" +
                                                                "				,us_autoriza.[us_cdgo] --148\n" +
                                                                "				,us_autoriza.[us_nombres] --149\n" +
                                                                "				,us_autoriza.[us_apellidos] --150\n" +
                                                                "				,us_autoriza.[us_correo] --151\n" +
                                                                "			,[me_autorizacion_recobro_cdgo] --152\n" +
                                                                "				,[are_cdgo] --153\n" +
                                                                "				,[are_desc] --154\n" +
                                                                "				,[are_estad] --155\n" +
                                                                "			,[me_observ_autorizacion] --156\n" +
                                                                "			,[me_inactividad] --157\n" +
                                                                "			,[me_causa_inactividad_cdgo] --158\n" +
                                                                "				,[ci_cdgo] --159\n" +
                                                                "				,[ci_desc] --160\n" +
                                                                "				,[ci_estad] --161\n" +
                                                                "			,[me_usuario_inactividad_cdgo] --162\n" +
                                                                "				,us_inactividad.[us_cdgo] --163\n" +
                                                                "				,us_inactividad.[us_nombres] --164\n" +
                                                                "				,us_inactividad.[us_apellidos] --165\n" +
                                                                "				,us_inactividad.[us_correo] --166\n" +
                                                                "			,[me_motivo_parada_estado] --167\n" +
                                                                "			,[me_motivo_parada_cdgo] --168\n" +
                                                                "				,[mpa_cdgo] --169\n" +
                                                                "				,[mpa_desc] --170\n" +
                                                                "			,[me_observ] --171\n" +
                                                                "			,CASE [me_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_estado] --172\n" +
                                                                "			,CASE [me_desde_mvto_carbon] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [me_desde_mvto_carbon] --173\n" +
                                                                "			,[me_cntro_cost] --174\n" +
                                                                "			,[me_cntro_cost_auxiliarDestino_cdgo] --175\n" +
                                                                "				,cca_destino.[cca_cdgo] --176\n" +
                                                                "				,cca_destino.[cca_cntro_cost_subcentro_cdgo] --177\n" +
                                                                "					,ccs_destino.[ccs_cdgo] --178\n" +
                                                                "					,ccs_destino.[ccs_desc] --179\n" +
                                                                "					,ccs_destino.[ccs_cntro_oper_cdgo] --180\n" +
                                                                "					,ccs_destino.[ccs_cntro_cost_rquiere_labor_realizda] --181\n" +
                                                                "				,cca_destino.[cca_desc] --182\n" +
                                                                "			,[me_cntro_cost_mayor_cdgo] --183\n" +
                                                                "				,me_cntro_cost_mayor.[ccm_cdgo] --184\n" +
                                                                "				,me_cntro_cost_mayor.[ccm_desc]  --  185    \n" +
                                                                "				,Tiempo_Vehiculo_Descargue=(SELECT (DATEDIFF (MINUTE,  [mc_fecha_inicio_descargue], [mc_fecha_fin_descargue])))  --186\n" +
                                                                "				,Tiempo_Equipo_Descargue=(SELECT (DATEDIFF (MINUTE,  [me_fecha_hora_inicio], [me_fecha_hora_fin])))  --187                                        \n" +
                                                                "      ,CASE [mcle_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [mcle_estado]--188\n" +
                                                                "      ,[mc_equipo_lavado_cdgo]--189 \n" +
                                                                "      ,[mc_lavado_vehiculo]--190 \n" +
                                                                "      ,[mc_costoLavadoVehiculo]--191 \n" +
                                                                "      ,[mc_valorRecaudoEmpresa_lavadoVehiculo]--192 \n" +
                                                                "      ,[mc_valorRecaudoEquipo_lavadoVehiculo]--193 \n" +
                    
                                                                "      ,mc_cntro_cost_base_datos.[bd_cdgo]   --194 \n" +
                                                                "      ,mc_articulo_base_datos.[bd_cdgo]   --195 \n" +
                                                                "      ,mc_cliente_base_datos.[bd_cdgo]   --196 \n" +
                                                                "      ,mc_transprtdora_base_datos.[bd_cdgo]   --197 \n" +
                                                                "      ,mc_cntro_cost_mayor_base_datos.[bd_cdgo]   --198 \n" +
                    
                                                                "      ,me_cntro_cost_base_datos.[bd_cdgo]   --199 \n" +
                                                                "      ,me_cliente_base_datos.[bd_cdgo]   --200 \n" +
                                                                "      ,me_articulo_base_datos.[bd_cdgo]   --201 \n" +
                                                                "      ,me_motonave_base_datos.[bd_cdgo]   --202 \n" +
                                                                "      ,me_cntro_cost_mayor_base_datos.[bd_cdgo]  --203 \n" +
                    
                                                                "  FROM ["+DB+"].[dbo].[mvto_carbon_listado_equipo]\n" +
                                                                "		INNER JOIN ["+DB+"].[dbo].[mvto_carbon] ON [mcle_mvto_carbon_cdgo]=[mc_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_oper] mc_cntro_oper ON [mc_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                "		LEFT JOIN  ["+DB+"].[dbo].[cntro_cost_auxiliar] mc_cntro_cost_auxiliar ON [mc_cntro_cost_auxiliar_cdgo]=mc_cntro_cost_auxiliar.[cca_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] mc_cntro_cost_subcentro ON mc_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo]=mc_cntro_cost_subcentro.[ccs_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost] mc_cntro_cost ON [mc_cntro_cost_cdgo]=mc_cntro_cost.[cc_cdgo]\n" +      
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cntro_cost_base_datos ON mc_cntro_cost.[cc_cliente_base_datos_cdgo]=mc_cntro_cost_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[labor_realizada] mc_labor_realizada ON [mc_labor_realizada_cdgo] =  mc_labor_realizada.[lr_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[articulo] mc_articulo ON [mc_articulo_cdgo]=mc_articulo.[ar_cdgo] AND mc_articulo.[ar_base_datos_cdgo]=[mc_articulo_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_articulo_base_datos ON mc_articulo.[ar_base_datos_cdgo]=mc_articulo_base_datos.[bd_cdgo] \n"+
                                                                        "		LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] mc_tipo_articulo ON mc_articulo.[ar_tipo_articulo_cdgo]=mc_tipo_articulo.[tar_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente] mc_cliente ON [mc_cliente_cdgo]=mc_cliente.[cl_cdgo] AND mc_cliente.[cl_base_datos_cdgo]=[mc_cliente_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cliente_base_datos ON mc_cliente.[cl_base_datos_cdgo]=mc_cliente_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[transprtdora] ON [mc_transprtdora_cdgo]=[tr_cdgo] AND [tr_base_datos_cdgo]=[mc_transprtdora_base_datos_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_transprtdora_base_datos ON [tr_base_datos_cdgo]=mc_transprtdora_base_datos.[bd_cdgo] \n"+    
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] mc_usuarioQuienRegistra ON [mc_usuario_cdgo]=mc_usuarioQuienRegistra.[us_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[estad_mvto_carbon] ON [mc_estad_mvto_carbon_cdgo]=[emc_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] mc_usuario_registro_manual ON [mc_usuario_registro_manual_cdgo]=mc_usuario_registro_manual.[us_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] mc_cntro_cost_auxiliarDestino ON [mc_cntro_cost_auxiliarDestino_cdgo]=mc_cntro_cost_auxiliarDestino.[cca_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor] mc_cntro_cost_mayor ON [mc_cntro_cost_mayor_cdgo]=mc_cntro_cost_mayor.[ccm_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cntro_cost_mayor_base_datos ON [ccm_cliente_base_datos_cdgo]=mc_cntro_cost_mayor_base_datos.[bd_cdgo] \n"+  
                                                                
                                                                
                                                                
                                                                    
                                                                                                                                               
                                                                "               LEFT JOIN ["+DB+"].[dbo].[mvto_equipo] ON [mcle_mvto_equipo_cdgo]=[me_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [me_asignacion_equipo_cdgo]=[ae_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [me_proveedor_equipo_cdgo]=[pe_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_oper] me_cntro_oper ON [me_cntro_oper_cdgo]=me_cntro_oper.[co_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_origen ON  [me_cntro_cost_auxiliar_cdgo]=cca_origen.[cca_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_origen ON cca_origen.[cca_cntro_cost_subcentro_cdgo]=ccs_origen.[ccs_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost] me_cntro_cost ON [me_cntro_cost_cdgo]=me_cntro_cost.[cc_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_base_datos ON me_cntro_cost.[cc_cliente_base_datos_cdgo]=me_cntro_cost_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[labor_realizada] me_labor_realizada ON [me_labor_realizada_cdgo]=me_labor_realizada.[lr_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente] me_cliente ON [me_cliente_cdgo]=me_cliente.[cl_cdgo] AND me_cliente.[cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON me_cliente.[cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[articulo] me_articulo ON [me_articulo_cdgo]=me_articulo.[ar_cdgo] AND	me_articulo.[ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON me_articulo.[ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] me_tipo_articulo ON me_articulo.[ar_tipo_articulo_cdgo]=me_tipo_articulo.[tar_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[me_motonave_base_datos_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_motonave_base_datos ON  [mn_base_datos_cdgo]=me_motonave_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] us_registro ON [me_usuario_registro_cdgo]=us_registro.[us_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] us_autoriza ON [me_usuario_autorizacion_cdgo]=us_autoriza.[us_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[autorizacion_recobro] ON [me_autorizacion_recobro_cdgo]=[are_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[causa_inactividad] ON [me_causa_inactividad_cdgo]=[ci_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] us_inactividad ON [me_usuario_inactividad_cdgo]=us_inactividad.[us_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[motivo_parada] ON [me_motivo_parada_cdgo]=[mpa_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_destino ON [me_cntro_cost_auxiliarDestino_cdgo]=cca_destino.[cca_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_destino ON cca_destino.[cca_cntro_cost_subcentro_cdgo]=ccs_destino.[ccs_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor] me_cntro_cost_mayor ON [me_cntro_cost_mayor_cdgo]=me_cntro_cost_mayor.[ccm_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_mayor_base_datos ON me_cntro_cost_mayor.[ccm_cliente_base_datos_cdgo]=me_cntro_cost_mayor_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON [ae_equipo_pertenencia_cdgo]=[ep_cdgo]"
                                                                + "	WHERE [mc_fecha] BETWEEN ? AND ? AND [me_inactividad]=1 AND [mc_estad_mvto_carbon_cdgo]=0 ORDER BY [mcle_mvto_carbon_cdgo] DESC");
            query.setString(1, DatetimeInicio);
            query.setString(2, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();    
            while(resultSet.next()){ 
                try{
                    MvtoCarbon_ListadoEquipos mvto_listEquipo = new MvtoCarbon_ListadoEquipos();
                    mvto_listEquipo.setCodigo(resultSet.getString(1));
                       MvtoCarbon mvtoCarbon = new MvtoCarbon();
                        mvtoCarbon.setCodigo(resultSet.getString(3));
                        mvtoCarbon.setCentroOperacion(new CentroOperacion(Integer.parseInt(resultSet.getString(5)),resultSet.getString(6),""));
                        mvtoCarbon.setCentroCostoAuxiliar(new CentroCostoAuxiliar(Integer.parseInt(resultSet.getString(8)),new CentroCostoSubCentro(Integer.parseInt(resultSet.getString(10)),resultSet.getString(11),""),resultSet.getString(13),""));

                            CentroCosto mc_CentroCosto = new CentroCosto();
                                mc_CentroCosto.setCodigo(resultSet.getString(15));
                                mc_CentroCosto.setDescripción(resultSet.getString(16));
                                
                                mc_CentroCosto.setClienteBaseDatos(resultSet.getString(194));
                        mvtoCarbon.setCentroCosto(mc_CentroCosto);
                            LaborRealizada mc_LaborRealizada = new LaborRealizada();
                                mc_LaborRealizada.setCodigo(resultSet.getString(18));
                                mc_LaborRealizada.setDescripcion(resultSet.getString(19));
                        mvtoCarbon.setLaborRealizada(mc_LaborRealizada);
                            Articulo mc_Articulo = new Articulo();
                                mc_Articulo.setCodigo(resultSet.getString(21));
                                    TipoArticulo mc_TipoArticulo = new TipoArticulo();
                                    mc_TipoArticulo.setCodigo(resultSet.getString(23));
                                    mc_TipoArticulo.setDescripcion(resultSet.getString(24));
                                    mc_TipoArticulo.setCodigoERP(resultSet.getString(25));
                                    mc_TipoArticulo.setUnidadNegocio(resultSet.getString(26));
                                mc_Articulo.setTipoArticulo(mc_TipoArticulo);
                                mc_Articulo.setDescripcion(resultSet.getString(27));
                                mc_Articulo.setBaseDatos(new BaseDatos(resultSet.getString(195)));
                        mvtoCarbon.setArticulo(mc_Articulo);
                            Cliente mc_cliente = new Cliente();
                            mc_cliente.setCodigo(resultSet.getString(29));
                            mc_cliente.setDescripcion(resultSet.getString(30));
                            mc_cliente.setBaseDatos(new BaseDatos(resultSet.getString(196)));
                        
                        mvtoCarbon.setCliente(mc_cliente);
                            Transportadora mc_trTransportadora = new Transportadora();
                            mc_trTransportadora.setCodigo(resultSet.getString(32));
                            mc_trTransportadora.setNit(resultSet.getString(33));
                            mc_trTransportadora.setDescripcion(resultSet.getString(34));
                            mc_trTransportadora.setBaseDatos(new BaseDatos(resultSet.getString(197)));
                        mvtoCarbon.setTransportadora(mc_trTransportadora);
                        mvtoCarbon.setMes(resultSet.getString(35));
                        mvtoCarbon.setFechaRegistro(resultSet.getString(36));
                        mvtoCarbon.setNumero_orden(resultSet.getString(37));
                        mvtoCarbon.setDeposito(resultSet.getString(38));
                        mvtoCarbon.setConsecutivo(resultSet.getString(39));
                        mvtoCarbon.setPlaca(resultSet.getString(40));
                        mvtoCarbon.setPesoVacio(resultSet.getString(41));
                        mvtoCarbon.setPesoLleno(resultSet.getString(42));
                        mvtoCarbon.setPesoNeto(resultSet.getString(43));
                        mvtoCarbon.setFechaEntradaVehiculo(resultSet.getString(44));
                        mvtoCarbon.setFecha_SalidaVehiculo(resultSet.getString(45));    
                        mvtoCarbon.setFechaInicioDescargue(resultSet.getString(46));    
                        mvtoCarbon.setFechaFinDescargue(resultSet.getString(47));      
                            Usuario mc_usuarioQuienRegistra = new Usuario();           
                            mc_usuarioQuienRegistra.setCodigo(resultSet.getString(49));
                            mc_usuarioQuienRegistra.setNombres(resultSet.getString(50));
                            mc_usuarioQuienRegistra.setApellidos(resultSet.getString(51));
                            mc_usuarioQuienRegistra.setCorreo(resultSet.getString(52));
                        mvtoCarbon.setUsuarioRegistroMovil(mc_usuarioQuienRegistra);
                        mvtoCarbon.setObservacion(resultSet.getString(53));
                            EstadoMvtoCarbon estadMvtoCarbon = new EstadoMvtoCarbon();
                            estadMvtoCarbon.setCodigo(resultSet.getString(55));
                            estadMvtoCarbon.setDescripcion(resultSet.getString(56));
                        mvtoCarbon.setEstadoMvtoCarbon(estadMvtoCarbon);
                        mvtoCarbon.setConexionPesoCcarga(resultSet.getString(57));
                        mvtoCarbon.setRegistroManual(resultSet.getString(58));
                            Usuario mc_usuarioRegManual = new Usuario();           
                            mc_usuarioRegManual.setCodigo(resultSet.getString(60));
                            mc_usuarioRegManual.setNombres(resultSet.getString(61));
                            mc_usuarioRegManual.setApellidos(resultSet.getString(62));
                            mc_usuarioRegManual.setCorreo(resultSet.getString(63));
                        mvtoCarbon.setUsuarioRegistraManual(mc_usuarioRegManual);
                        CentroCostoAuxiliar mc_CentroCostosAuxilarDestino = new CentroCostoAuxiliar();
                        if(resultSet.getString(65) != null){
                            mc_CentroCostosAuxilarDestino.setCodigo(Integer.parseInt(resultSet.getString(65)));
                            mc_CentroCostosAuxilarDestino.setDescripcion(resultSet.getString(66));
                        
                        }else{                         
                            mc_CentroCostosAuxilarDestino.setCodigo(-1);
                            mc_CentroCostosAuxilarDestino.setDescripcion(null);
                        }
                        mvtoCarbon.setCentroCostoAuxiliarDestino(mc_CentroCostosAuxilarDestino); 
                        
                        mvtoCarbon.setCantidadHorasDescargue(resultSet.getString(186));
                            CentroCostoMayor mc_CentroCostoMayor = new CentroCostoMayor();
                            mc_CentroCostoMayor.setCodigo(resultSet.getString(68));
                            mc_CentroCostoMayor.setDescripcion(resultSet.getString(69));  
                            mc_CentroCostoMayor.setClienteBaseDatos(resultSet.getString(198));
                        mvtoCarbon.setCentroCostoMayor(mc_CentroCostoMayor);
                        Equipo equipoTem1 = new Equipo();
                        equipoTem1.setCodigo(resultSet.getString(189));
                        mvtoCarbon.setEquipoLavadoVehiculo(equipoTem1);
                        mvtoCarbon.setLavadoVehiculo(resultSet.getString(190));
                        mvtoCarbon.setCostoLavadoVehiculo(resultSet.getString(191));
                        mvtoCarbon.setValorRecaudoEmpresa_lavadoVehiculo(resultSet.getString(192));
                        mvtoCarbon.setValorRecaudoEquipo_lavadoVehiculo(resultSet.getString(193));
                    mvto_listEquipo.setMvtoCarbon(mvtoCarbon);
                        AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                        asignacionEquipo.setCodigo(resultSet.getString(74));
                        asignacionEquipo.setFechaRegistro(resultSet.getString(83));
                        asignacionEquipo.setFechaHoraInicio(resultSet.getString(84));
                        asignacionEquipo.setFechaHoraFin(resultSet.getString(85));
                        asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(86));
                            Equipo equipo = new Equipo(); 
                            equipo.setCodigo(resultSet.getString(76));
                            equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(78),resultSet.getString(79),""));
                            equipo.setMarca(resultSet.getString(80));
                            equipo.setModelo(resultSet.getString(81));
                            equipo.setDescripcion(resultSet.getString(82));
                            equipo.setPertenenciaEquipo(new Pertenencia(resultSet.getString(88),resultSet.getString(89),resultSet.getString(90)));
                        asignacionEquipo.setEquipo(equipo);
                        asignacionEquipo.setPertenencia(new Pertenencia(resultSet.getString(88),resultSet.getString(89),resultSet.getString(90)));
                    mvto_listEquipo.setAsignacionEquipo(asignacionEquipo);
                        MvtoEquipo mvtoEquipo = new MvtoEquipo();
                        mvtoEquipo.setCodigo(resultSet.getString(72));
                        mvtoEquipo.setAsignacionEquipo(asignacionEquipo);
                        mvtoEquipo.setMes(resultSet.getString(91));
                        mvtoEquipo.setFechaRegistro(resultSet.getString(92));
                        mvtoEquipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(94),resultSet.getString(95),resultSet.getString(96),""));
                        mvtoEquipo.setNumeroOrden(resultSet.getString(97));
                            CentroOperacion me_co= new CentroOperacion();
                            me_co.setCodigo(Integer.parseInt(resultSet.getString(99)));
                            me_co.setDescripcion(resultSet.getString(100));
                        mvtoEquipo.setCentroOperacion(me_co);
                            CentroCostoSubCentro centroCostoSubCentro_mvtoEquipo = new CentroCostoSubCentro();
                            centroCostoSubCentro_mvtoEquipo.setCodigo(resultSet.getInt(104));
                            centroCostoSubCentro_mvtoEquipo.setDescripcion(resultSet.getString(105));
                            centroCostoSubCentro_mvtoEquipo.setCentroCostoRequiereLaborRealizada(resultSet.getString(107));
                            CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipo = new CentroCostoAuxiliar();
                            centroCostoAuxiliar_mvtoEquipo.setCodigo(resultSet.getInt(102));
                            centroCostoAuxiliar_mvtoEquipo.setDescripcion(resultSet.getString(108));
                            centroCostoAuxiliar_mvtoEquipo.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipo);
                        mvtoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipo);
                            CentroCosto centroCosto = new CentroCosto();
                            centroCosto.setCodigo(resultSet.getString(110));
                            centroCosto.setDescripción(resultSet.getString(111));
                            centroCosto.setClienteBaseDatos(resultSet.getString(199));
                        mvtoEquipo.setCentroCosto(centroCosto);
                            LaborRealizada laborRealizadaT = new LaborRealizada();
                            laborRealizadaT.setCodigo(resultSet.getString(113));
                            laborRealizadaT.setDescripcion(resultSet.getString(114));
                            laborRealizadaT.setEs_operativa(resultSet.getString(115));
                            laborRealizadaT.setEs_parada(resultSet.getString(116));
                        mvtoEquipo.setLaborRealizada(laborRealizadaT);
                        Cliente me_cliente = new Cliente();
                        me_cliente.setCodigo(resultSet.getString(118));
                        me_cliente.setDescripcion(resultSet.getString(119));
                        me_cliente.setBaseDatos(new BaseDatos( resultSet.getString(200)));
                        
                        mvtoEquipo.setCliente(me_cliente);
                            TipoArticulo tipoArticulo = new TipoArticulo();
                                    tipoArticulo.setCodigo(resultSet.getString(123));
                                    tipoArticulo.setDescripcion(resultSet.getString(124));
                                    tipoArticulo.setCodigoERP(resultSet.getString(125));
                                    tipoArticulo.setUnidadNegocio(resultSet.getString(126));
                            Articulo articulo = new Articulo();
                            articulo.setCodigo(resultSet.getString(121));
                            articulo.setDescripcion(resultSet.getString(127));
                            articulo.setTipoArticulo(tipoArticulo);
                            articulo.setBaseDatos(new BaseDatos( resultSet.getString(201)));
                        mvtoEquipo.setArticulo(articulo);
                        Motonave motonave = new Motonave();
                        motonave.setCodigo(resultSet.getString(129));
                        motonave.setDescripcion(resultSet.getString(130));
                        motonave.setBaseDatos(new BaseDatos( resultSet.getString(202)));
                    mvtoEquipo.setMotonave(motonave);
                    mvtoEquipo.setFechaHoraInicio(resultSet.getString(131));
                    mvtoEquipo.setFechaHoraFin(resultSet.getString(132));
                    mvtoEquipo.setTotalMinutos(resultSet.getString(133));
                    mvtoEquipo.setValorHora(resultSet.getString(134));
                    mvtoEquipo.setCostoTotal(resultSet.getString(135));
                            Recobro recobro = new Recobro();
                            recobro.setCodigo(resultSet.getString(137));
                            recobro.setDescripcion(resultSet.getString(138));
                    mvtoEquipo.setRecobro(recobro);
                            Usuario usuario_me_registra = new Usuario();
                            usuario_me_registra.setCodigo(resultSet.getString(143));
                            usuario_me_registra.setNombres(resultSet.getString(144));
                            usuario_me_registra.setApellidos(resultSet.getString(145));
                            usuario_me_registra.setCorreo(resultSet.getString(146));
                        mvtoEquipo.setUsuarioQuieRegistra(usuario_me_registra);
                            Usuario usuario_me_autoriza = new Usuario();
                            usuario_me_autoriza.setCodigo(resultSet.getString(148));
                            usuario_me_autoriza.setNombres(resultSet.getString(149));
                            usuario_me_autoriza.setApellidos(resultSet.getString(150));
                            usuario_me_autoriza.setCorreo(resultSet.getString(151));
                        mvtoEquipo.setUsuarioAutorizaRecobro(usuario_me_autoriza);
                            AutorizacionRecobro autorizacionRecobro = new AutorizacionRecobro();
                            autorizacionRecobro.setCodigo(resultSet.getString(153));
                            autorizacionRecobro.setDescripcion(resultSet.getString(154));
                            autorizacionRecobro.setEstado(resultSet.getString(155));
                        mvtoEquipo.setAutorizacionRecobro(autorizacionRecobro);
                        mvtoEquipo.setObservacionAutorizacion(resultSet.getString(156));
                        mvtoEquipo.setInactividad(resultSet.getString(157));
                            CausaInactividad causaInactividad = new CausaInactividad();
                            causaInactividad.setCodigo(resultSet.getString(159));
                            causaInactividad.setDescripcion(resultSet.getString(160));
                            causaInactividad.setEstado(resultSet.getString(161));
                        mvtoEquipo.setCausaInactividad(causaInactividad);
                            Usuario usuario_me_us_inactividad = new Usuario();
                            usuario_me_us_inactividad.setCodigo(resultSet.getString(163));
                            usuario_me_us_inactividad.setNombres(resultSet.getString(164));
                            usuario_me_us_inactividad.setApellidos(resultSet.getString(165));
                            usuario_me_us_inactividad.setCorreo(resultSet.getString(166));
                        mvtoEquipo.setUsuarioInactividad(usuario_me_us_inactividad);
                            MotivoParada motivoParada= new MotivoParada();
                            motivoParada.setCodigo(resultSet.getString(169));
                            motivoParada.setDescripcion(resultSet.getString(170));
                        mvtoEquipo.setMotivoParada(motivoParada);
                        mvtoEquipo.setObservacionMvtoEquipo(resultSet.getString(171));
                        mvtoEquipo.setEstado(resultSet.getString(172));
                        mvtoEquipo.setDesdeCarbon(resultSet.getString(173));
                        mvtoEquipo.setCentroCostoDescripción(resultSet.getString(174));
                        CentroCostoSubCentro centroCostoSubCentro_mvtoEquipoDestino = new CentroCostoSubCentro();
                            centroCostoSubCentro_mvtoEquipoDestino.setCodigo(resultSet.getInt(178));
                            centroCostoSubCentro_mvtoEquipoDestino.setDescripcion(resultSet.getString(179));
                            centroCostoSubCentro_mvtoEquipoDestino.setCentroCostoRequiereLaborRealizada(resultSet.getString(181));
                            CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipoDestino = new CentroCostoAuxiliar();
                            centroCostoAuxiliar_mvtoEquipoDestino.setCodigo(resultSet.getInt(176));
                            centroCostoAuxiliar_mvtoEquipoDestino.setDescripcion(resultSet.getString(182));
                            centroCostoAuxiliar_mvtoEquipoDestino.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipoDestino);
                        mvtoEquipo.setCentroCostoAuxiliarDestino(centroCostoAuxiliar_mvtoEquipoDestino);
                            CentroCostoMayor centroCostoMayor = new CentroCostoMayor();
                            centroCostoMayor.setCodigo(resultSet.getString(184));
                            centroCostoMayor.setDescripcion(resultSet.getString(185));
                            centroCostoMayor.setClienteBaseDatos(resultSet.getString(203));
                        mvtoEquipo.setCentroCostoMayor(centroCostoMayor);    
                        //mvtoEquipo.setTotalMinutos(resultSet.getString(187)); 
                    mvto_listEquipo.setMvtoEquipo(mvtoEquipo);  
                    mvto_listEquipo.setEstado(resultSet.getString(188));
                    try{
                        TarifaEquipo tarifa=new ControlDB_Equipo(tipoConexion).buscarTarifaEquipoPorAño(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getCodigo(),mvto_listEquipo.getMvtoEquipo().getFechaHoraInicio());
                        if(tarifa !=null){
                            if(mvto_listEquipo.getMvtoEquipo().getFechaHoraFin() != null){
                                mvto_listEquipo.getMvtoEquipo().setValorHora(tarifa.getValorHoraOperacion());
                                try{
                                    double des=Double.parseDouble(mvto_listEquipo.getMvtoEquipo().getTotalMinutos());
                                    double totalHoras = Double.parseDouble(""+(des/60));
                                    double valorHora =Double.parseDouble(tarifa.getValorHoraOperacion());
                                    //mvto_listEquipo.getMvtoEquipo().setCostoTotal(""+(totalHoras * valorHora ));  
                                    DecimalFormat formato2 = new DecimalFormat("0.00");
                                    mvto_listEquipo.getMvtoEquipo().setCostoTotal(formato2.format((totalHoras * valorHora )));
                                    //mvto_listEquipo.getMvtoEquipo().setCostoTotalRecobroCliente(formato2.format((totalHoras * valorHora )));
                                    mvto_listEquipo.getMvtoEquipo().setCostoTotalRecobroCliente("0");
                                    mvto_listEquipo.getMvtoCarbon().setCostoLavadoVehiculo(tarifa.getCostoLavadoVehiculo());
                                    mvto_listEquipo.getMvtoCarbon().setValorRecaudoEmpresa_lavadoVehiculo(tarifa.getValorRecaudoEmpresa());
                                    mvto_listEquipo.getMvtoCarbon().setValorRecaudoEquipo_lavadoVehiculo(tarifa.getValorRecaudoEquipo());
                                }catch(Exception e){
                                    //mvto_listEquipo.getMvtoEquipo().setCostoTotal("NO PUDE");
                                }
                            }
                        }
                    }catch(Exception e){
                       System.out.println("Error al procesar tarifa");
                    }

                    listadoObjetos.add(mvto_listEquipo);    
                }catch(Exception e){
                    e.printStackTrace();
                }
                
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los Movimientos de Carbon");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }
    public ArrayList<MvtoCarbon_ListadoEquipos>     buscarMvtoCarbon_GenerarMatriz(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<MvtoCarbon_ListadoEquipos> listadoObjetos = new ArrayList();
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            Statement stmt = conexion.createStatement(
                                      ResultSet.TYPE_SCROLL_INSENSITIVE,
                                   
            ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = stmt.executeQuery("SELECT [mcle_cdgo]--1\n" +
                                                                "      ,[mcle_mvto_carbon_cdgo]--2\n" +
                                                                "			,[mc_cdgo]--3\n" +
                                                                "			,[mc_cntro_oper_cdgo]--4\n" +
                                                                "				,mc_cntro_oper.[co_cdgo]--5\n" +
                                                                "				,mc_cntro_oper.[co_desc]--6\n" +
                                                                "			,[mc_cntro_cost_auxiliar_cdgo]--7\n" +
                                                                "				,mc_cntro_cost_auxiliar.[cca_cdgo]--8\n" +
                                                                "				,mc_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo]--9\n" +
                                                                "					,mc_cntro_cost_subcentro.[ccs_cdgo]--10\n" +
                                                                "					,mc_cntro_cost_subcentro.[ccs_desc]--11\n" +
                                                                "					,mc_cntro_cost_subcentro.[ccs_cntro_cost_rquiere_labor_realizda]--12\n" +
                                                                "				,mc_cntro_cost_auxiliar.[cca_desc]--13\n" +
                                                                "			,[mc_cntro_cost_cdgo]--14\n" +
                                                                "					,mc_cntro_cost.[cc_cdgo]--15\n" +
                                                                "					,mc_cntro_cost.[cc_descripcion]--16\n" +
                                                                "			,[mc_labor_realizada_cdgo]--17\n" +
                                                                "					,mc_labor_realizada.[lr_cdgo]--18\n" +
                                                                "					,mc_labor_realizada.[lr_desc]	--19\n" +
                                                                "			,[mc_articulo_cdgo]--20\n" +
                                                                "					,mc_articulo.[ar_cdgo]--21\n" +
                                                                "					,mc_articulo.[ar_tipo_articulo_cdgo]--22\n" +
                                                                "						,mc_tipo_articulo.[tar_cdgo]--23\n" +
                                                                "						,mc_tipo_articulo.[tar_desc]--24\n" +
                                                                "						,mc_tipo_articulo.[tar_cdgo_erp]--25\n" +
                                                                "						,mc_tipo_articulo.[tar_undad_ngcio]--26\n" +
                                                                "					,mc_articulo.[ar_desc]--27\n" +
                                                                "			,[mc_cliente_cdgo]--28\n" +
                                                                "				,mc_cliente.[cl_cdgo]--29\n" +
                                                                "				,mc_cliente.[cl_desc]--30\n" +
                                                                "			,[mc_transprtdora_cdgo]--31\n" +
                                                                "				,[tr_cdgo]--32\n" +
                                                                "				,[tr_nit]--33\n" +
                                                                "				,[tr_desc]--34\n" +
                                                                "			,datename (MONTH ,[mc_fecha])--35\n" +
                                                                "			,[mc_fecha]--36\n" +
                                                                "			,[mc_num_orden]--37\n" +
                                                                "			,[mc_deposito]--38\n" +
                                                                "			,[mc_consecutivo_tqute]--39\n" +
                                                                "			,[mc_placa_vehiculo]--40\n" +
                                                                "			,[mc_peso_vacio]--41\n" +
                                                                "			,[mc_peso_lleno]--42\n" +
                                                                "			,[mc_peso_neto]--43\n" +
                                                                "			,[mc_fecha_entrad]--44\n" +
                                                                "			,[mc_fecha_salid]--45\n" +
                                                                "			,[mc_fecha_inicio_descargue]--46\n" +
                                                                "			,[mc_fecha_fin_descargue]--47\n" +
                                                                "			,[mc_usuario_cdgo]--48\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_cdgo]--49\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_nombres]--50\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_apellidos]--51\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_correo]--52\n" +
                                                                "			,[mc_observ]--53\n" +
                                                                "			,[mc_estad_mvto_carbon_cdgo]--54\n" +
                                                                "				,[emc_cdgo]--55\n" +
                                                                "				,[emc_desc]--56\n" +
                                                                "			,[mc_conexion_peso_ccarga]--57\n" +
                                                                "			,[mc_registro_manual]--58\n" +
                                                                "			,[mc_usuario_registro_manual_cdgo]--59\n" +
                                                                "				,mc_usuario_registro_manual.[us_cdgo]--60\n" +
                                                                "				,mc_usuario_registro_manual.[us_nombres]--61\n" +
                                                                "				,mc_usuario_registro_manual.[us_apellidos]--62\n" +
                                                                "				,mc_usuario_registro_manual.[us_correo]--63\n" +
                                                                "			,[mc_cntro_cost_auxiliarDestino_cdgo]--64\n" +
                                                                "				,mc_cntro_cost_auxiliarDestino.[cca_cdgo]--65\n" +
                                                                "				,mc_cntro_cost_auxiliarDestino.[cca_desc]--66\n" +
                                                                "			,[mc_cntro_cost_mayor_cdgo]--67\n" +
                                                                "				,mc_cntro_cost_mayor.[ccm_cdgo]--68\n" +
                                                                "				,mc_cntro_cost_mayor.[ccm_desc]--69\n" +
                                                                "      ,[mcle_asignacion_equipo_cdgo]--70\n" +
                                                                "      ,[mcle_mvto_equipo_cdgo]--71\n" +
                                                                "			,[me_cdgo] --72\n" +
                                                                "			,[me_asignacion_equipo_cdgo] --73\n" +
                                                                "				,[ae_cdgo] --74\n" +
                                                                "				,[ae_equipo_cdgo] --75\n" +
                                                                "					,equipo_ae.[eq_cdgo] --76\n" +
                                                                "					,equipo_ae.[eq_tipo_equipo_cdgo] --77\n" +
                                                                "						,tipo_equipo_ae.[te_cdgo]  --78\n" +
                                                                "						,tipo_equipo_ae.[te_desc] --79\n" +
                                                                "					,equipo_ae.[eq_marca] --80\n" +
                                                                "					,equipo_ae.[eq_modelo] --81\n" +
                                                                "					,equipo_ae.[eq_desc]	 	--82\n" +
                                                                "				,[ae_fecha_registro] --83\n" +
                                                                "				,[ae_fecha_hora_inicio] --84\n" +
                                                                "				,[ae_fecha_hora_fin] --85\n" +
                                                                "				,[ae_cant_minutos] --86\n" +
                                                                "				,[ae_equipo_pertenencia_cdgo] --87\n" +
                                                                "					,[ep_cdgo] --88\n" +
                                                                "					,[ep_desc] --89\n" +
                                                                "					,[ep_estad] --90\n" +
                                                                "			,datename (MONTH ,[me_fecha])  -- 91\n" +
                                                                "			,[me_fecha] --92\n" +
                                                                "			,[me_proveedor_equipo_cdgo] --93\n" +
                                                                "				,[pe_cdgo] --94\n" +
                                                                "				,[pe_nit] --95\n" +
                                                                "				,[pe_desc] --96\n" +
                                                                "			,[me_num_orden] --97\n" +
                                                                "			,[me_cntro_oper_cdgo] --98\n" +
                                                                "				,me_cntro_oper.[co_cdgo] --99\n" +
                                                                "				,me_cntro_oper.[co_desc] --100\n" +
                                                                "			,[me_cntro_cost_auxiliar_cdgo] --101\n" +
                                                                "				,cca_origen.[cca_cdgo] --102\n" +
                                                                "				,cca_origen.[cca_cntro_cost_subcentro_cdgo] --103\n" +
                                                                "					,ccs_origen.[ccs_cdgo] --104\n" +
                                                                "					,ccs_origen.[ccs_desc] --105\n" +
                                                                "					,ccs_origen.[ccs_cntro_oper_cdgo] --106\n" +
                                                                "					,ccs_origen.[ccs_cntro_cost_rquiere_labor_realizda] --107\n" +
                                                                "				,cca_origen.[cca_desc] --108\n" +
                                                                "			,[me_cntro_cost_cdgo] --109\n" +
                                                                "				,me_cntro_cost.[cc_cdgo] --110\n" +
                                                                "				,me_cntro_cost.[cc_descripcion] --111\n" +
                                                                "			,[me_labor_realizada_cdgo] --112\n" +
                                                                "				,me_labor_realizada.[lr_cdgo] --113\n" +
                                                                "				,me_labor_realizada.[lr_desc] --114\n" +
                                                                "				,me_labor_realizada.[lr_operativa] --115\n" +
                                                                "				,me_labor_realizada.[lr_parada] --116\n" +
                                                                "			,[me_cliente_cdgo] --117\n" +
                                                                "				,me_cliente.[cl_cdgo] --118\n" +
                                                                "				,me_cliente.[cl_desc] --119\n" +
                                                                "			,[me_articulo_cdgo] --120\n" +
                                                                "				,me_articulo.[ar_cdgo] --121\n" +
                                                                "				,me_articulo.[ar_tipo_articulo_cdgo]--122\n" +
                                                                "					,me_tipo_articulo.[tar_cdgo] --123\n" +
                                                                "					,me_tipo_articulo.[tar_desc] --124\n" +
                                                                "					,me_tipo_articulo.[tar_cdgo_erp] --125\n" +
                                                                "					,me_tipo_articulo.[tar_undad_ngcio] --126\n" +
                                                                "				,me_articulo.[ar_desc] --127\n" +
                                                                "			,[me_motonave_cdgo] --128\n" +
                                                                "				,[mn_cdgo] --129\n" +
                                                                "				,[mn_desc] --130\n" +
                                                                "			,[me_fecha_hora_inicio] --131\n" +
                                                                "			,[me_fecha_hora_fin] --132\n" +
                                                                "			,[me_total_minutos] --133\n" +
                                                                "			,[me_valor_hora] --134\n" +
                                                                "			,[me_costo_total] --135\n" +
                                                                "			,[me_recobro_cdgo] --136\n" +
                                                                "				,[rc_cdgo] --137\n" +
                                                                "				,[rc_desc] --138\n" +
                                                                "				,[rc_estad] --139\n" +
                                                                "			,[me_cliente_recobro_cdgo] --140\n" +
                                                                "			,[me_costo_total_recobro_cliente] --141\n" +
                                                                "			,[me_usuario_registro_cdgo] --142\n" +
                                                                "				,us_registro.[us_cdgo] --143\n" +
                                                                "				,us_registro.[us_nombres] --144\n" +
                                                                "				,us_registro.[us_apellidos] --145\n" +
                                                                "				,us_registro.[us_correo] --146\n" +
                                                                "			,[me_usuario_autorizacion_cdgo] --147\n" +
                                                                "				,us_autoriza.[us_cdgo] --148\n" +
                                                                "				,us_autoriza.[us_nombres] --149\n" +
                                                                "				,us_autoriza.[us_apellidos] --150\n" +
                                                                "				,us_autoriza.[us_correo] --151\n" +
                                                                "			,[me_autorizacion_recobro_cdgo] --152\n" +
                                                                "				,[are_cdgo] --153\n" +
                                                                "				,[are_desc] --154\n" +
                                                                "				,[are_estad] --155\n" +
                                                                "			,[me_observ_autorizacion] --156\n" +
                                                                "			,[me_inactividad] --157\n" +
                                                                "			,[me_causa_inactividad_cdgo] --158\n" +
                                                                "				,[ci_cdgo] --159\n" +
                                                                "				,[ci_desc] --160\n" +
                                                                "				,[ci_estad] --161\n" +
                                                                "			,[me_usuario_inactividad_cdgo] --162\n" +
                                                                "				,us_inactividad.[us_cdgo] --163\n" +
                                                                "				,us_inactividad.[us_nombres] --164\n" +
                                                                "				,us_inactividad.[us_apellidos] --165\n" +
                                                                "				,us_inactividad.[us_correo] --166\n" +
                                                                "			,[me_motivo_parada_estado] --167\n" +
                                                                "			,[me_motivo_parada_cdgo] --168\n" +
                                                                "				,[mpa_cdgo] --169\n" +
                                                                "				,[mpa_desc] --170\n" +
                                                                "			,[me_observ] --171\n" +
                                                                "			,CASE [me_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_estado] --172\n" +
                                                                "			,CASE [me_desde_mvto_carbon] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [me_desde_mvto_carbon] --173\n" +
                                                                "			,[me_cntro_cost] --174\n" +
                                                                "			,[me_cntro_cost_auxiliarDestino_cdgo] --175\n" +
                                                                "				,cca_destino.[cca_cdgo] --176\n" +
                                                                "				,cca_destino.[cca_cntro_cost_subcentro_cdgo] --177\n" +
                                                                "					,ccs_destino.[ccs_cdgo] --178\n" +
                                                                "					,ccs_destino.[ccs_desc] --179\n" +
                                                                "					,ccs_destino.[ccs_cntro_oper_cdgo] --180\n" +
                                                                "					,ccs_destino.[ccs_cntro_cost_rquiere_labor_realizda] --181\n" +
                                                                "				,cca_destino.[cca_desc] --182\n" +
                                                                "			,[me_cntro_cost_mayor_cdgo] --183\n" +
                                                                "				,me_cntro_cost_mayor.[ccm_cdgo] --184\n" +
                                                                "				,me_cntro_cost_mayor.[ccm_desc]  --  185    \n" +
                                                                "				,Tiempo_Vehiculo_Descargue=(SELECT (DATEDIFF (MINUTE,  [mc_fecha_inicio_descargue], [mc_fecha_fin_descargue])))  --186\n" +
                                                                "				,Tiempo_Equipo_Descargue=(SELECT (DATEDIFF (MINUTE,  [me_fecha_hora_inicio], [me_fecha_hora_fin])))  --187                                        \n" +
                                                                "      ,CASE [mcle_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [mcle_estado]--188\n" +
                                                                "      ,CASE [mc_lavado_vehiculo] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [mc_lavado_vehiculo]--189\n" +
                                                                "      ,[mc_lavado_vehiculo_observacion] --190 \n"
                                                                + "      ,[mc_motivo_nolavado_vehiculo_cdgo]--191\n" +
                                                                "		,[mnlv_cdgo]--192\n" +
                                                                "		,[mnlv_desc]--193\n" +
                                                                "      ,[mc_equipo_lavado_cdgo]--194\n" +
                                                                "		,equipo_lavado.[eq_cdgo]--195\n" +
                                                                "		,equipo_lavado.[eq_tipo_equipo_cdgo]--196\n" +
                                                                "			,tipo_equipo_lavado.[te_cdgo]--197\n" +
                                                                "			,tipo_equipo_lavado.[te_desc]--198\n" +
                                                                "		,equipo_lavado.[eq_marca]--199\n" +
                                                                "		,equipo_lavado.[eq_modelo]--200\n" +
                                                                "		,equipo_lavado.[eq_desc]	--201\n" +
                                                                "	   ,[mc_costoLavadoVehiculo]--202\n" +
                                                                "	   ,datename (DAY ,[mc_fecha])--203\n" +
                                                                "      ,[mc_valorRecaudoEmpresa_lavadoVehiculo]--204 \n" +
                                                                "      ,[mc_valorRecaudoEquipo_lavadoVehiculo]--205 \n" +
                                                                "      ,mc_usuario_cierre.[us_cdgo]--206 \n" +
                                                                "      ,mc_usuario_cierre.[us_nombres]--207 \n" +
                                                                "      ,mc_usuario_cierre.[us_apellidos]--208 \n" +
                                                                "      ,me_usuario_cierre.[us_cdgo]--209 \n" +
                                                                "      ,me_usuario_cierre.[us_nombres]--210 \n" +
                                                                "      ,me_usuario_cierre.[us_apellidos]--211 \n" +
                    
                                                                "      ,mc_cntro_cost_base_datos.[bd_cdgo]   --212 \n" +
                                                                "      ,mc_articulo_base_datos.[bd_cdgo]   --213 \n" +
                                                                "      ,mc_cliente_base_datos.[bd_cdgo]   --214 \n" +
                                                                "      ,mc_transprtdora_base_datos.[bd_cdgo]   --215 \n" +
                                                                "      ,mc_cntro_cost_mayor_base_datos.[bd_cdgo]   --216 \n" +
                    
                                                                "      ,me_cntro_cost_base_datos.[bd_cdgo]   --217 \n" +
                                                                "      ,me_cliente_base_datos.[bd_cdgo]   --218 \n" +
                                                                "      ,me_articulo_base_datos.[bd_cdgo]   --219 \n" +
                                                                "      ,me_motonave_base_datos.[bd_cdgo]   --220 \n" +
                                                                "      ,me_cntro_cost_mayor_base_datos.[bd_cdgo]  --221 \n" +
                    
                                                                "  FROM ["+DB+"].[dbo].[mvto_carbon_listado_equipo]\n" +
                                                                "		INNER JOIN ["+DB+"].[dbo].[mvto_carbon] ON [mcle_mvto_carbon_cdgo]=[mc_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_oper] mc_cntro_oper ON [mc_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                "		LEFT JOIN  ["+DB+"].[dbo].[cntro_cost_auxiliar] mc_cntro_cost_auxiliar ON [mc_cntro_cost_auxiliar_cdgo]=mc_cntro_cost_auxiliar.[cca_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] mc_cntro_cost_subcentro ON mc_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo]=mc_cntro_cost_subcentro.[ccs_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost] mc_cntro_cost ON [mc_cntro_cost_cdgo]=mc_cntro_cost.[cc_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cntro_cost_base_datos ON mc_cntro_cost.[cc_cliente_base_datos_cdgo]=mc_cntro_cost_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[labor_realizada] mc_labor_realizada ON [mc_labor_realizada_cdgo] =  mc_labor_realizada.[lr_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[articulo] mc_articulo ON [mc_articulo_cdgo]=mc_articulo.[ar_cdgo] AND mc_articulo.[ar_base_datos_cdgo]=[mc_articulo_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_articulo_base_datos ON mc_articulo.[ar_base_datos_cdgo]=mc_articulo_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] mc_tipo_articulo ON mc_articulo.[ar_tipo_articulo_cdgo]=mc_tipo_articulo.[tar_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente] mc_cliente ON [mc_cliente_cdgo]=mc_cliente.[cl_cdgo] AND mc_cliente.[cl_base_datos_cdgo]=[mc_cliente_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cliente_base_datos ON mc_cliente.[cl_base_datos_cdgo]=mc_cliente_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[transprtdora] ON [mc_transprtdora_cdgo]=[tr_cdgo] AND [tr_base_datos_cdgo]=[mc_transprtdora_base_datos_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_transprtdora_base_datos ON [tr_base_datos_cdgo]=mc_transprtdora_base_datos.[bd_cdgo] \n"+ 
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] mc_usuarioQuienRegistra ON [mc_usuario_cdgo]=mc_usuarioQuienRegistra.[us_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[estad_mvto_carbon] ON [mc_estad_mvto_carbon_cdgo]=[emc_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] mc_usuario_registro_manual ON [mc_usuario_registro_manual_cdgo]=mc_usuario_registro_manual.[us_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] mc_cntro_cost_auxiliarDestino ON [mc_cntro_cost_auxiliarDestino_cdgo]=mc_cntro_cost_auxiliarDestino.[cca_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor] mc_cntro_cost_mayor ON [mc_cntro_cost_mayor_cdgo]=mc_cntro_cost_mayor.[ccm_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cntro_cost_mayor_base_datos ON [ccm_cliente_base_datos_cdgo]=mc_cntro_cost_mayor_base_datos.[bd_cdgo] \n"+  
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] mc_usuario_cierre ON mc_usuario_cierre_cdgo=mc_usuario_cierre.[us_cdgo]\n" +
                                                                "		INNER JOIN ["+DB+"].[dbo].[mvto_equipo] ON [mcle_mvto_equipo_cdgo]=[me_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [me_asignacion_equipo_cdgo]=[ae_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[equipo] equipo_ae ON [ae_equipo_cdgo]=equipo_ae.[eq_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [me_proveedor_equipo_cdgo]=[pe_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_oper] me_cntro_oper ON [me_cntro_oper_cdgo]=me_cntro_oper.[co_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_origen ON  [me_cntro_cost_auxiliar_cdgo]=cca_origen.[cca_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_origen ON cca_origen.[cca_cntro_cost_subcentro_cdgo]=ccs_origen.[ccs_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost] me_cntro_cost ON [me_cntro_cost_cdgo]=me_cntro_cost.[cc_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_base_datos ON me_cntro_cost.[cc_cliente_base_datos_cdgo]=me_cntro_cost_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[labor_realizada] me_labor_realizada ON [me_labor_realizada_cdgo]=me_labor_realizada.[lr_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente] me_cliente ON [me_cliente_cdgo]=me_cliente.[cl_cdgo] AND me_cliente.[cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON me_cliente.[cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[articulo] me_articulo ON [me_articulo_cdgo]=me_articulo.[ar_cdgo] AND	me_articulo.[ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON me_articulo.[ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] me_tipo_articulo ON me_articulo.[ar_tipo_articulo_cdgo]=me_tipo_articulo.[tar_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[me_motonave_base_datos_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_motonave_base_datos ON  [mn_base_datos_cdgo]=me_motonave_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] us_registro ON [me_usuario_registro_cdgo]=us_registro.[us_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] us_autoriza ON [me_usuario_autorizacion_cdgo]=us_autoriza.[us_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[autorizacion_recobro] ON [me_autorizacion_recobro_cdgo]=[are_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[causa_inactividad] ON [me_causa_inactividad_cdgo]=[ci_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] us_inactividad ON [me_usuario_inactividad_cdgo]=us_inactividad.[us_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[motivo_parada] ON [me_motivo_parada_cdgo]=[mpa_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_destino ON [me_cntro_cost_auxiliarDestino_cdgo]=cca_destino.[cca_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_destino ON cca_destino.[cca_cntro_cost_subcentro_cdgo]=ccs_destino.[ccs_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor] me_cntro_cost_mayor ON [me_cntro_cost_mayor_cdgo]=me_cntro_cost_mayor.[ccm_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_mayor_base_datos ON me_cntro_cost_mayor.[ccm_cliente_base_datos_cdgo]=me_cntro_cost_mayor_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] tipo_equipo_ae ON equipo_ae.[eq_tipo_equipo_cdgo]=tipo_equipo_ae.[te_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON [ae_equipo_pertenencia_cdgo]=[ep_cdgo]\n"+
                                                                "               LEFT JOIN ["+DB+"].[dbo].[motivo_nolavado_vehiculo] ON [mc_motivo_nolavado_vehiculo_cdgo]=[mnlv_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[equipo] equipo_lavado ON [mc_equipo_lavado_cdgo]=equipo_lavado.[eq_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] tipo_equipo_lavado ON equipo_lavado.[eq_tipo_equipo_cdgo]=tipo_equipo_lavado.[te_cdgo]"+
                                                                "               LEFT JOIN ["+DB+"].[dbo].[usuario] me_usuario_cierre ON [me_usuario_cierre_cdgo]=me_usuario_cierre.[us_cdgo]\n"+
                                                                "               WHERE [mc_fecha] BETWEEN '"+DatetimeInicio+"' AND '"+DatetimeFin+"' AND [me_inactividad]=0 AND [mc_estad_mvto_carbon_cdgo]=1 "+
                                                                "	 ORDER BY [mcle_mvto_carbon_cdgo] DESC");
            //query.setString(1, DatetimeInicio);
            //query.setString(2, DatetimeFin);
            //ResultSet resultSet; resultSet= query.executeQuery();    
            while(resultSet.next()){ 
                try{
                    MvtoCarbon_ListadoEquipos mvto_listEquipo = new MvtoCarbon_ListadoEquipos();
                    mvto_listEquipo.setCodigo(resultSet.getString(1));
                        MvtoCarbon mvtoCarbon = new MvtoCarbon();
                        mvtoCarbon.setCodigo(resultSet.getString(3));
                        mvtoCarbon.setCentroOperacion(new CentroOperacion(Integer.parseInt(resultSet.getString(5)),resultSet.getString(6),""));
                        mvtoCarbon.setCentroCostoAuxiliar(new CentroCostoAuxiliar(Integer.parseInt(resultSet.getString(8)),new CentroCostoSubCentro(Integer.parseInt(resultSet.getString(10)),resultSet.getString(11),""),resultSet.getString(13),""));

                            CentroCosto mc_CentroCosto = new CentroCosto();
                                mc_CentroCosto.setCodigo(resultSet.getString(15));
                                mc_CentroCosto.setDescripción(resultSet.getString(16));
                                 mc_CentroCosto.setClienteBaseDatos(resultSet.getString(212));
                        mvtoCarbon.setCentroCosto(mc_CentroCosto);
                            LaborRealizada mc_LaborRealizada = new LaborRealizada();
                                mc_LaborRealizada.setCodigo(resultSet.getString(18));
                                mc_LaborRealizada.setDescripcion(resultSet.getString(19));
                        mvtoCarbon.setLaborRealizada(mc_LaborRealizada);
                            Articulo mc_Articulo = new Articulo();
                                mc_Articulo.setCodigo(resultSet.getString(21));
                                    TipoArticulo mc_TipoArticulo = new TipoArticulo();
                                    mc_TipoArticulo.setCodigo(resultSet.getString(23));
                                    mc_TipoArticulo.setDescripcion(resultSet.getString(24));
                                    mc_TipoArticulo.setCodigoERP(resultSet.getString(25));
                                    mc_TipoArticulo.setUnidadNegocio(resultSet.getString(26));
                                mc_Articulo.setTipoArticulo(mc_TipoArticulo);
                                mc_Articulo.setDescripcion(resultSet.getString(27));
                                mc_Articulo.setBaseDatos(new BaseDatos(resultSet.getString(213)));
                        mvtoCarbon.setArticulo(mc_Articulo);
                        
                        Cliente mc_cliente = new Cliente();
                            mc_cliente.setCodigo(resultSet.getString(29));
                            mc_cliente.setDescripcion(resultSet.getString(30));
                            mc_cliente.setBaseDatos(new BaseDatos(resultSet.getString(214)));
                        
                        mvtoCarbon.setCliente(mc_cliente);
                        
                        
                        //mvtoCarbon.setCliente(new Cliente(resultSet.getString(29),resultSet.getString(30),""));
                            Transportadora mc_trTransportadora = new Transportadora();
                            mc_trTransportadora.setCodigo(resultSet.getString(32));
                            mc_trTransportadora.setNit(resultSet.getString(33));
                            mc_trTransportadora.setDescripcion(resultSet.getString(34));
                            mc_trTransportadora.setBaseDatos(new BaseDatos(resultSet.getString(215)));
                        mvtoCarbon.setTransportadora(mc_trTransportadora);
                        mvtoCarbon.setMes(resultSet.getString(35));
                        mvtoCarbon.setDia(resultSet.getString(203));
                        mvtoCarbon.setFechaRegistro(resultSet.getString(36));
                        mvtoCarbon.setNumero_orden(resultSet.getString(37));
                        mvtoCarbon.setDeposito(resultSet.getString(38));
                        mvtoCarbon.setConsecutivo(resultSet.getString(39));
                        mvtoCarbon.setPlaca(resultSet.getString(40));
                        mvtoCarbon.setPesoVacio(resultSet.getString(41));
                        mvtoCarbon.setPesoLleno(resultSet.getString(42));
                        mvtoCarbon.setPesoNeto(resultSet.getString(43));
                        mvtoCarbon.setFechaEntradaVehiculo(resultSet.getString(44));
                        mvtoCarbon.setFecha_SalidaVehiculo(resultSet.getString(45));    
                        mvtoCarbon.setFechaInicioDescargue(resultSet.getString(46));    
                        mvtoCarbon.setFechaFinDescargue(resultSet.getString(47));      
                            Usuario mc_usuarioQuienRegistra = new Usuario();           
                            mc_usuarioQuienRegistra.setCodigo(resultSet.getString(49));
                            mc_usuarioQuienRegistra.setNombres(resultSet.getString(50));
                            mc_usuarioQuienRegistra.setApellidos(resultSet.getString(51));
                            mc_usuarioQuienRegistra.setCorreo(resultSet.getString(52));
                        mvtoCarbon.setUsuarioRegistroMovil(mc_usuarioQuienRegistra);
                        mvtoCarbon.setObservacion(resultSet.getString(53));
                            EstadoMvtoCarbon estadMvtoCarbon = new EstadoMvtoCarbon();
                            estadMvtoCarbon.setCodigo(resultSet.getString(55));
                            estadMvtoCarbon.setDescripcion(resultSet.getString(56));
                        mvtoCarbon.setEstadoMvtoCarbon(estadMvtoCarbon);
                        mvtoCarbon.setConexionPesoCcarga(resultSet.getString(57));
                        mvtoCarbon.setRegistroManual(resultSet.getString(58));
                            Usuario mc_usuarioRegManual = new Usuario();           
                            mc_usuarioRegManual.setCodigo(resultSet.getString(60));
                            mc_usuarioRegManual.setNombres(resultSet.getString(61));
                            mc_usuarioRegManual.setApellidos(resultSet.getString(62));
                            mc_usuarioRegManual.setCorreo(resultSet.getString(63));
                        mvtoCarbon.setUsuarioRegistraManual(mc_usuarioRegManual);
                        CentroCostoAuxiliar mc_CentroCostosAuxilarDestino = new CentroCostoAuxiliar();
                        if(resultSet.getString(65) != null){
                            mc_CentroCostosAuxilarDestino.setCodigo(Integer.parseInt(resultSet.getString(65)));
                            mc_CentroCostosAuxilarDestino.setDescripcion(resultSet.getString(66));
                        
                        }else{                         
                            mc_CentroCostosAuxilarDestino.setCodigo(-1);
                            mc_CentroCostosAuxilarDestino.setDescripcion(null);
                        }
                        mvtoCarbon.setCentroCostoAuxiliarDestino(mc_CentroCostosAuxilarDestino); 
                        mvtoCarbon.setCantidadHorasDescargue(resultSet.getString(186));
                            CentroCostoMayor mc_CentroCostoMayor = new CentroCostoMayor();
                            mc_CentroCostoMayor.setCodigo(resultSet.getString(68));
                            mc_CentroCostoMayor.setDescripcion(resultSet.getString(69));    
                            mc_CentroCostoMayor.setClienteBaseDatos(resultSet.getString(216));
                        mvtoCarbon.setCentroCostoMayor(mc_CentroCostoMayor);
                        mvtoCarbon.setLavadoVehiculo(resultSet.getString(189));
                        mvtoCarbon.setLavadoVehiculo_Observacion(resultSet.getString(190));
                            MotivoNoLavado motivoNoLavado = new MotivoNoLavado();
                            motivoNoLavado.setDescripcion(resultSet.getString(193));
                        mvtoCarbon.setMotivoNoLavado(motivoNoLavado);
                            Equipo equipoLavado= new Equipo();
                            equipoLavado.setCodigo(resultSet.getString(195));
                                TipoEquipo tipoEquipo_lavado = new TipoEquipo();
                                tipoEquipo_lavado.setCodigo(resultSet.getString(197));
                                tipoEquipo_lavado.setDescripcion(resultSet.getString(198));
                            equipoLavado.setTipoEquipo(tipoEquipo_lavado);
                            equipoLavado.setMarca(resultSet.getString(199));
                            equipoLavado.setModelo(resultSet.getString(200));
                            equipoLavado.setDescripcion(resultSet.getString(201));
                        mvtoCarbon.setEquipoLavadoVehiculo(equipoLavado);
                        mvtoCarbon.setCostoLavadoVehiculo(resultSet.getString(202));
                        mvtoCarbon.setValorRecaudoEmpresa_lavadoVehiculo(resultSet.getString(204));
                        mvtoCarbon.setValorRecaudoEquipo_lavadoVehiculo(resultSet.getString(205));
                        
                        
                        mvtoCarbon.setUsuarioRegistroMovil(mc_usuarioQuienRegistra);
                        
                        Usuario mc_usuarioCierre = new Usuario(); 
                        
                        if(resultSet.getString(206) != null){//Usuario de Cierrre diferente de nulo         
                            mc_usuarioCierre.setCodigo(resultSet.getString(206));
                            mc_usuarioCierre.setNombres(resultSet.getString(207));
                            mc_usuarioCierre.setApellidos(resultSet.getString(208));  
                        }else{       
                            mc_usuarioCierre.setCodigo(null);
                            mc_usuarioCierre.setNombres(null);
                            mc_usuarioCierre.setApellidos(null);  
                        }
                        mvtoCarbon.setUsuarioCierraRegistro(mc_usuarioCierre);
                    mvto_listEquipo.setMvtoCarbon(mvtoCarbon);
                        AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                        asignacionEquipo.setCodigo(resultSet.getString(74));
                        asignacionEquipo.setFechaRegistro(resultSet.getString(83));
                        asignacionEquipo.setFechaHoraInicio(resultSet.getString(84));
                        asignacionEquipo.setFechaHoraFin(resultSet.getString(85));
                        asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(86));
                            Equipo equipo = new Equipo(); 
                            equipo.setCodigo(resultSet.getString(76));
                            equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(78),resultSet.getString(79),""));
                            equipo.setMarca(resultSet.getString(80));
                            equipo.setModelo(resultSet.getString(81));
                            equipo.setDescripcion(resultSet.getString(82)+ " "+ resultSet.getString(81));
                            equipo.setPertenenciaEquipo(new Pertenencia(resultSet.getString(88),resultSet.getString(89),resultSet.getString(90)));
                        asignacionEquipo.setEquipo(equipo);
                        asignacionEquipo.setPertenencia(new Pertenencia(resultSet.getString(88),resultSet.getString(89),resultSet.getString(90)));
                    mvto_listEquipo.setAsignacionEquipo(asignacionEquipo);
                        MvtoEquipo mvtoEquipo = new MvtoEquipo();
                        mvtoEquipo.setCodigo(resultSet.getString(72));
                        mvtoEquipo.setAsignacionEquipo(asignacionEquipo);
                        mvtoEquipo.setMes(getMes(resultSet.getString(91)));
                        mvtoEquipo.setFechaRegistro(resultSet.getString(92));
                        mvtoEquipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(94),resultSet.getString(95),resultSet.getString(96),""));
                        mvtoEquipo.setNumeroOrden(resultSet.getString(97));
                            CentroOperacion me_co= new CentroOperacion();
                            me_co.setCodigo(Integer.parseInt(resultSet.getString(99)));
                            me_co.setDescripcion(resultSet.getString(100));
                        mvtoEquipo.setCentroOperacion(me_co);
                            CentroCostoSubCentro centroCostoSubCentro_mvtoEquipo = new CentroCostoSubCentro();
                            centroCostoSubCentro_mvtoEquipo.setCodigo(resultSet.getInt(104));
                            centroCostoSubCentro_mvtoEquipo.setDescripcion(resultSet.getString(105));
                            centroCostoSubCentro_mvtoEquipo.setCentroCostoRequiereLaborRealizada(resultSet.getString(107));
                            CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipo = new CentroCostoAuxiliar();
                            centroCostoAuxiliar_mvtoEquipo.setCodigo(resultSet.getInt(102));
                            centroCostoAuxiliar_mvtoEquipo.setDescripcion(resultSet.getString(108));
                            centroCostoAuxiliar_mvtoEquipo.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipo);
                        mvtoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipo);
                            CentroCosto centroCosto = new CentroCosto();
                            centroCosto.setCodigo(resultSet.getString(110));
                            centroCosto.setDescripción(resultSet.getString(111));
                            centroCosto.setClienteBaseDatos(resultSet.getString(217));
                        mvtoEquipo.setCentroCosto(centroCosto);
                            LaborRealizada laborRealizadaT = new LaborRealizada();
                            laborRealizadaT.setCodigo(resultSet.getString(113));
                            laborRealizadaT.setDescripcion(resultSet.getString(114));
                            laborRealizadaT.setEs_operativa(resultSet.getString(115));
                            laborRealizadaT.setEs_parada(resultSet.getString(116));
                        mvtoEquipo.setLaborRealizada(laborRealizadaT);
                        
                        Cliente me_cliente = new Cliente();
                        me_cliente.setCodigo(resultSet.getString(118));
                        me_cliente.setDescripcion(resultSet.getString(119));
                        me_cliente.setBaseDatos(new BaseDatos( resultSet.getString(218)));
                        mvtoEquipo.setCliente(me_cliente);
                        //mvtoEquipo.setCliente(new Cliente(resultSet.getString(118),resultSet.getString(119),""));
                            TipoArticulo tipoArticulo = new TipoArticulo();
                                    tipoArticulo.setCodigo(resultSet.getString(123));
                                    tipoArticulo.setDescripcion(resultSet.getString(124));
                                    tipoArticulo.setCodigoERP(resultSet.getString(125));
                                    tipoArticulo.setUnidadNegocio(resultSet.getString(126));
                            Articulo articulo = new Articulo();
                            articulo.setCodigo(resultSet.getString(121));
                            articulo.setDescripcion(resultSet.getString(127));
                            articulo.setTipoArticulo(tipoArticulo);
                            articulo.setBaseDatos(new BaseDatos( resultSet.getString(219)));
                        mvtoEquipo.setArticulo(articulo);
                        Motonave motonave = new Motonave();
                        motonave.setCodigo(resultSet.getString(129));
                        motonave.setDescripcion(resultSet.getString(130));
                        motonave.setBaseDatos(new BaseDatos( resultSet.getString(220)));
                    mvtoEquipo.setMotonave(motonave);
                    mvtoEquipo.setFechaHoraInicio(resultSet.getString(131));
                    mvtoEquipo.setFechaHoraFin(resultSet.getString(132));
                    mvtoEquipo.setTotalMinutos(resultSet.getString(133));
                    mvtoEquipo.setValorHora(resultSet.getString(134));
                    mvtoEquipo.setCostoTotal(resultSet.getString(135));
                            Recobro recobro = new Recobro();
                            recobro.setCodigo(resultSet.getString(137));
                            recobro.setDescripcion(resultSet.getString(138));
                    mvtoEquipo.setRecobro(recobro);
                            Usuario usuario_me_registra = new Usuario();
                            usuario_me_registra.setCodigo(resultSet.getString(143));
                            usuario_me_registra.setNombres(resultSet.getString(144));
                            usuario_me_registra.setApellidos(resultSet.getString(145));
                            usuario_me_registra.setCorreo(resultSet.getString(146));
                        mvtoEquipo.setUsuarioQuieRegistra(usuario_me_registra);
                            Usuario usuario_me_autoriza = new Usuario();
                            usuario_me_autoriza.setCodigo(resultSet.getString(148));
                            usuario_me_autoriza.setNombres(resultSet.getString(149));
                            usuario_me_autoriza.setApellidos(resultSet.getString(150));
                            usuario_me_autoriza.setCorreo(resultSet.getString(151));
                        mvtoEquipo.setUsuarioAutorizaRecobro(usuario_me_autoriza);
                            AutorizacionRecobro autorizacionRecobro = new AutorizacionRecobro();
                            autorizacionRecobro.setCodigo(resultSet.getString(153));
                            autorizacionRecobro.setDescripcion(resultSet.getString(154));
                            autorizacionRecobro.setEstado(resultSet.getString(155));
                        mvtoEquipo.setAutorizacionRecobro(autorizacionRecobro);
                        mvtoEquipo.setObservacionAutorizacion(resultSet.getString(156));
                        mvtoEquipo.setInactividad(resultSet.getString(157));
                            CausaInactividad causaInactividad = new CausaInactividad();
                            causaInactividad.setCodigo(resultSet.getString(159));
                            causaInactividad.setDescripcion(resultSet.getString(160));
                            causaInactividad.setEstado(resultSet.getString(161));
                        mvtoEquipo.setCausaInactividad(causaInactividad);
                            Usuario usuario_me_us_inactividad = new Usuario();
                            usuario_me_us_inactividad.setCodigo(resultSet.getString(163));
                            usuario_me_us_inactividad.setNombres(resultSet.getString(164));
                            usuario_me_us_inactividad.setApellidos(resultSet.getString(165));
                            usuario_me_us_inactividad.setCorreo(resultSet.getString(166));
                        mvtoEquipo.setUsuarioInactividad(usuario_me_us_inactividad);
                            MotivoParada motivoParada= new MotivoParada();
                            motivoParada.setCodigo(resultSet.getString(169));
                            motivoParada.setDescripcion(resultSet.getString(170));
                        mvtoEquipo.setMotivoParada(motivoParada);
                        mvtoEquipo.setObservacionMvtoEquipo(resultSet.getString(171));
                        mvtoEquipo.setEstado(resultSet.getString(172));
                        mvtoEquipo.setDesdeCarbon(resultSet.getString(173));
                        mvtoEquipo.setCentroCostoDescripción(resultSet.getString(174));
                        CentroCostoSubCentro centroCostoSubCentro_mvtoEquipoDestino = new CentroCostoSubCentro();
                            centroCostoSubCentro_mvtoEquipoDestino.setCodigo(resultSet.getInt(178));
                            centroCostoSubCentro_mvtoEquipoDestino.setDescripcion(resultSet.getString(179));
                            centroCostoSubCentro_mvtoEquipoDestino.setCentroCostoRequiereLaborRealizada(resultSet.getString(181));
                            CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipoDestino = new CentroCostoAuxiliar();
                            centroCostoAuxiliar_mvtoEquipoDestino.setCodigo(resultSet.getInt(176));
                            centroCostoAuxiliar_mvtoEquipoDestino.setDescripcion(resultSet.getString(182));
                            centroCostoAuxiliar_mvtoEquipoDestino.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipoDestino);
                        mvtoEquipo.setCentroCostoAuxiliarDestino(centroCostoAuxiliar_mvtoEquipoDestino);
                            CentroCostoMayor centroCostoMayor = new CentroCostoMayor();
                            centroCostoMayor.setCodigo(resultSet.getString(184));
                            centroCostoMayor.setDescripcion(resultSet.getString(185));
                            centroCostoMayor.setClienteBaseDatos(resultSet.getString(221));
                        mvtoEquipo.setCentroCostoMayor(centroCostoMayor);    
                            Usuario me_usuarioCierre = new Usuario();    
                            if(resultSet.getString(209) != null){
                                me_usuarioCierre.setCodigo(resultSet.getString(209));
                                me_usuarioCierre.setNombres(resultSet.getString(210));
                                me_usuarioCierre.setApellidos(resultSet.getString(211));  
                            }else{
                                me_usuarioCierre.setCodigo(null);
                                me_usuarioCierre.setNombres(null);
                                me_usuarioCierre.setApellidos(null);  
                            }
                        mvtoEquipo.setUsuarioQuienCierra(me_usuarioCierre);
                        //mvtoEquipo.setTotalMinutos(resultSet.getString(187)); 
                    mvto_listEquipo.setMvtoEquipo(mvtoEquipo);  
                    mvto_listEquipo.setEstado(resultSet.getString(188));
                    
                resultSet.next();
                try{
                    while(mvtoCarbon.getCodigo().equals(resultSet.getString(3))){
                        mvto_listEquipo.getAsignacionEquipo().setCodigo(mvto_listEquipo.getAsignacionEquipo().getCodigo()+"\n"+resultSet.getString(74));
                        mvto_listEquipo.getAsignacionEquipo().setFechaRegistro(mvto_listEquipo.getAsignacionEquipo().getFechaRegistro()+"\n"+resultSet.getString(83));
                        mvto_listEquipo.getAsignacionEquipo().setFechaHoraInicio(mvto_listEquipo.getAsignacionEquipo().getFechaHoraInicio()+"\n"+resultSet.getString(84));
                        mvto_listEquipo.getAsignacionEquipo().setFechaHoraFin(mvto_listEquipo.getAsignacionEquipo().getFechaHoraFin()+"\n"+resultSet.getString(85));
                        mvto_listEquipo.getAsignacionEquipo().setCantidadMinutosProgramados(mvto_listEquipo.getAsignacionEquipo().getCantidadMinutosProgramados()+"\n"+resultSet.getString(86));
                        
                        
                        mvto_listEquipo.getAsignacionEquipo().getEquipo().setCodigo(mvto_listEquipo.getAsignacionEquipo().getEquipo().getCodigo()+"\n"+resultSet.getString(76));
                        System.out.println("Fue------>"+mvto_listEquipo.getAsignacionEquipo().getEquipo().getCodigo()+" "+resultSet.getString(76));
                        mvto_listEquipo.getAsignacionEquipo().getEquipo().getTipoEquipo().setCodigo(mvto_listEquipo.getAsignacionEquipo().getEquipo().getTipoEquipo().getCodigo()+"\n"+resultSet.getString(78));
                        mvto_listEquipo.getAsignacionEquipo().getEquipo().getTipoEquipo().setDescripcion(mvto_listEquipo.getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion()+"\n"+resultSet.getString(79));
                        mvto_listEquipo.getAsignacionEquipo().getEquipo().setMarca(mvto_listEquipo.getAsignacionEquipo().getEquipo().getMarca()+"\n"+resultSet.getString(80));
                        mvto_listEquipo.getAsignacionEquipo().getEquipo().setModelo(mvto_listEquipo.getAsignacionEquipo().getEquipo().getModelo()+"\n"+resultSet.getString(81));
                        mvto_listEquipo.getAsignacionEquipo().getEquipo().setDescripcion(mvto_listEquipo.getAsignacionEquipo().getEquipo().getDescripcion()+"\n"+resultSet.getString(82)+ " "+resultSet.getString(81));
                        mvto_listEquipo.getAsignacionEquipo().getEquipo().getPertenenciaEquipo().setCodigo(mvto_listEquipo.getAsignacionEquipo().getEquipo().getPertenenciaEquipo().getCodigo()+"\n"+resultSet.getString(88));
                        mvto_listEquipo.getAsignacionEquipo().getEquipo().getPertenenciaEquipo().setDescripcion(mvto_listEquipo.getAsignacionEquipo().getEquipo().getPertenenciaEquipo().getDescripcion()+"\n"+resultSet.getString(89));
                        mvto_listEquipo.getAsignacionEquipo().getEquipo().getPertenenciaEquipo().setEstado(mvto_listEquipo.getAsignacionEquipo().getEquipo().getPertenenciaEquipo().getEstado()+"\n"+resultSet.getString(90));
                        
                        /*mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().setCodigo(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getCodigo()+"\n"+resultSet.getString(76));
                        mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getTipoEquipo().setCodigo(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getTipoEquipo().getCodigo()+"\n"+resultSet.getString(78));
                        mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getTipoEquipo().setDescripcion(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getTipoEquipo().getDescripcion()+"\n"+resultSet.getString(79));
                        mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().setMarca(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getMarca()+"\n"+resultSet.getString(80));
                        mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().setModelo(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getModelo()+"\n"+resultSet.getString(81));
                        mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().setDescripcion(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getDescripcion()+"\n"+resultSet.getString(82)+" "+resultSet.getString(81));
                        mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getPertenenciaEquipo().setCodigo(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getPertenenciaEquipo().getCodigo()+"\n"+resultSet.getString(88));
                        mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getPertenenciaEquipo().setDescripcion(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getPertenenciaEquipo().getDescripcion()+"\n"+resultSet.getString(89));
                        mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getPertenenciaEquipo().setEstado(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getPertenenciaEquipo().getEstado()+"\n"+resultSet.getString(90));
                        */
                        
                        
                        mvto_listEquipo.getMvtoEquipo().setCodigo(mvto_listEquipo.getMvtoEquipo().getCodigo()+"\n"+resultSet.getString(72));
                        mvto_listEquipo.getMvtoEquipo().setMes(mvto_listEquipo.getMvtoEquipo().getMes()+"\n"+getMes(resultSet.getString(91)));
                        mvto_listEquipo.getMvtoEquipo().setFechaRegistro(mvto_listEquipo.getMvtoEquipo().getFechaRegistro()+"\n"+resultSet.getString(92));
                        mvto_listEquipo.getMvtoEquipo().getProveedorEquipo().setCodigo(mvto_listEquipo.getMvtoEquipo().getProveedorEquipo().getCodigo()+"\n"+resultSet.getString(94));
                        mvto_listEquipo.getMvtoEquipo().getProveedorEquipo().setNit(mvto_listEquipo.getMvtoEquipo().getProveedorEquipo().getNit()+"\n"+resultSet.getString(95));
                        mvto_listEquipo.getMvtoEquipo().getProveedorEquipo().setDescripcion(mvto_listEquipo.getMvtoEquipo().getProveedorEquipo().getDescripcion()+"\n"+resultSet.getString(96));
                        mvto_listEquipo.getMvtoEquipo().setNumeroOrden(mvto_listEquipo.getMvtoEquipo().getNumeroOrden()+"\n"+resultSet.getString(97));
                        
                        //mvto_listEquipo.getMvtoEquipo().getCentroOperacion().setCodigo(mvto_listEquipo.getMvtoEquipo().getCentroOperacion().getCodigo()+"\n"+resultSet.getString(94));
                        mvto_listEquipo.getMvtoEquipo().getCentroOperacion().setDescripcion(mvto_listEquipo.getMvtoEquipo().getCentroOperacion().getDescripcion()+"\n"+resultSet.getString(100));
                        
                        mvto_listEquipo.getMvtoEquipo().getCentroCostoAuxiliar().getCentroCostoSubCentro().setDescripcion(mvto_listEquipo.getMvtoEquipo().getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion()+"\n"+resultSet.getString(105));
                        mvto_listEquipo.getMvtoEquipo().getCentroCostoAuxiliar().setDescripcion(mvto_listEquipo.getMvtoEquipo().getCentroCostoAuxiliar().getDescripcion()+"\n"+resultSet.getString(108));
                        mvto_listEquipo.getMvtoEquipo().getCentroCosto().setCodigo(mvto_listEquipo.getMvtoEquipo().getCentroCosto().getCodigo()+"\n"+resultSet.getString(110));
                        mvto_listEquipo.getMvtoEquipo().getCentroCosto().setDescripción(mvto_listEquipo.getMvtoEquipo().getCentroCosto().getDescripción()+"\n"+resultSet.getString(111));
                        
                        mvto_listEquipo.getMvtoEquipo().getLaborRealizada().setCodigo(mvto_listEquipo.getMvtoEquipo().getLaborRealizada().getCodigo()+"\n"+resultSet.getString(113));
                        mvto_listEquipo.getMvtoEquipo().getLaborRealizada().setDescripcion(mvto_listEquipo.getMvtoEquipo().getLaborRealizada().getDescripcion()+"\n"+resultSet.getString(114));
                        mvto_listEquipo.getMvtoEquipo().getLaborRealizada().setEs_operativa(mvto_listEquipo.getMvtoEquipo().getLaborRealizada().getEs_operativa()+"\n"+resultSet.getString(115));
                        mvto_listEquipo.getMvtoEquipo().getLaborRealizada().setEs_parada(mvto_listEquipo.getMvtoEquipo().getLaborRealizada().getEs_parada()+"\n"+resultSet.getString(116));
                        
                        mvto_listEquipo.getMvtoEquipo().getCliente().setCodigo(mvto_listEquipo.getMvtoEquipo().getCliente().getCodigo()+"\n"+resultSet.getString(118));
                        mvto_listEquipo.getMvtoEquipo().getCliente().setDescripcion(mvto_listEquipo.getMvtoEquipo().getCliente().getDescripcion()+"\n"+resultSet.getString(119));
                         
                         
                        mvto_listEquipo.getMvtoEquipo().getArticulo().getTipoArticulo().setCodigo(mvto_listEquipo.getMvtoEquipo().getArticulo().getTipoArticulo().getCodigo()+"\n"+resultSet.getString(123));
                        mvto_listEquipo.getMvtoEquipo().getArticulo().getTipoArticulo().setDescripcion(mvto_listEquipo.getMvtoEquipo().getArticulo().getTipoArticulo().getDescripcion()+"\n"+resultSet.getString(124));
                        mvto_listEquipo.getMvtoEquipo().getArticulo().getTipoArticulo().setCodigoERP(mvto_listEquipo.getMvtoEquipo().getArticulo().getTipoArticulo().getCodigoERP()+"\n"+resultSet.getString(125));
                        mvto_listEquipo.getMvtoEquipo().getArticulo().getTipoArticulo().setUnidadNegocio(mvto_listEquipo.getMvtoEquipo().getArticulo().getTipoArticulo().getUnidadNegocio()+"\n"+resultSet.getString(126));
                        
                        mvto_listEquipo.getMvtoEquipo().getArticulo().setCodigo(mvto_listEquipo.getMvtoEquipo().getArticulo().getCodigo()+"\n"+resultSet.getString(121));
                        mvto_listEquipo.getMvtoEquipo().getArticulo().setDescripcion(mvto_listEquipo.getMvtoEquipo().getArticulo().getDescripcion()+"\n"+resultSet.getString(127));
                        
                        mvto_listEquipo.getMvtoEquipo().getMotonave().setCodigo(mvto_listEquipo.getMvtoEquipo().getMotonave().getCodigo()+"\n"+resultSet.getString(129));
                        mvto_listEquipo.getMvtoEquipo().getMotonave().setDescripcion(mvto_listEquipo.getMvtoEquipo().getMotonave().getDescripcion()+"\n"+resultSet.getString(130));
                        
                        mvto_listEquipo.getMvtoEquipo().setFechaHoraInicio(mvto_listEquipo.getMvtoEquipo().getFechaHoraInicio()+"\n"+resultSet.getString(131));
                        mvto_listEquipo.getMvtoEquipo().setFechaHoraFin(mvto_listEquipo.getMvtoEquipo().getFechaHoraFin()+"\n"+resultSet.getString(132));
                        mvto_listEquipo.getMvtoEquipo().setTotalMinutos(mvto_listEquipo.getMvtoEquipo().getTotalMinutos()+"\n"+resultSet.getString(133));
                        mvto_listEquipo.getMvtoEquipo().setValorHora(mvto_listEquipo.getMvtoEquipo().getValorHora()+"\n"+resultSet.getString(134));
                        mvto_listEquipo.getMvtoEquipo().setCostoTotal(mvto_listEquipo.getMvtoEquipo().getCostoTotal()+"\n"+resultSet.getString(135));
                        
                        mvto_listEquipo.getMvtoEquipo().getRecobro().setCodigo(mvto_listEquipo.getMvtoEquipo().getRecobro().getCodigo()+"\n"+resultSet.getString(137));
                        mvto_listEquipo.getMvtoEquipo().getRecobro().setDescripcion(mvto_listEquipo.getMvtoEquipo().getRecobro().getDescripcion()+"\n"+resultSet.getString(138));
                        
                        mvto_listEquipo.getMvtoEquipo().getUsuarioQuieRegistra().setCodigo(mvto_listEquipo.getMvtoEquipo().getUsuarioQuieRegistra().getCodigo()+"\n"+resultSet.getString(143));
                        mvto_listEquipo.getMvtoEquipo().getUsuarioQuieRegistra().setNombres(mvto_listEquipo.getMvtoEquipo().getUsuarioQuieRegistra().getNombres()+"\n"+resultSet.getString(144)+" "+resultSet.getString(145));
                        mvto_listEquipo.getMvtoEquipo().getUsuarioQuieRegistra().setApellidos(mvto_listEquipo.getMvtoEquipo().getUsuarioQuieRegistra().getApellidos()+"\n"+resultSet.getString(145));
                        mvto_listEquipo.getMvtoEquipo().getUsuarioQuieRegistra().setCorreo(mvto_listEquipo.getMvtoEquipo().getUsuarioQuieRegistra().getCorreo()+"\n"+resultSet.getString(146));

                        mvto_listEquipo.getMvtoEquipo().getUsuarioAutorizaRecobro().setCodigo(mvto_listEquipo.getMvtoEquipo().getUsuarioAutorizaRecobro().getCodigo()+"\n"+resultSet.getString(148));
                        mvto_listEquipo.getMvtoEquipo().getUsuarioAutorizaRecobro().setNombres(mvto_listEquipo.getMvtoEquipo().getUsuarioAutorizaRecobro().getNombres()+"\n"+resultSet.getString(149)+" "+resultSet.getString(150));
                        mvto_listEquipo.getMvtoEquipo().getUsuarioAutorizaRecobro().setApellidos(mvto_listEquipo.getMvtoEquipo().getUsuarioAutorizaRecobro().getApellidos()+"\n"+resultSet.getString(150));
                        mvto_listEquipo.getMvtoEquipo().getUsuarioAutorizaRecobro().setCorreo(mvto_listEquipo.getMvtoEquipo().getUsuarioAutorizaRecobro().getCorreo()+"\n"+resultSet.getString(151));
                         
                        mvto_listEquipo.getMvtoEquipo().getUsuarioQuienCierra().setCodigo(mvto_listEquipo.getMvtoEquipo().getUsuarioQuienCierra().getCodigo()+"\n"+resultSet.getString(209));
                        mvto_listEquipo.getMvtoEquipo().getUsuarioQuienCierra().setNombres(mvto_listEquipo.getMvtoEquipo().getUsuarioQuienCierra().getNombres()+"\n"+resultSet.getString(210)+" "+resultSet.getString(211));
                        mvto_listEquipo.getMvtoEquipo().getUsuarioQuienCierra().setApellidos(mvto_listEquipo.getMvtoEquipo().getUsuarioQuienCierra().getApellidos()+"\n"+resultSet.getString(211));
                        
                        
                        mvto_listEquipo.getMvtoEquipo().getAutorizacionRecobro().setCodigo(mvto_listEquipo.getMvtoEquipo().getAutorizacionRecobro().getCodigo()+"\n"+resultSet.getString(153));
                        mvto_listEquipo.getMvtoEquipo().getAutorizacionRecobro().setDescripcion(mvto_listEquipo.getMvtoEquipo().getAutorizacionRecobro().getDescripcion()+"\n"+resultSet.getString(154));
                        
                        mvto_listEquipo.getMvtoEquipo().setObservacionAutorizacion(mvto_listEquipo.getMvtoEquipo().getObservacionAutorizacion()+"\n"+resultSet.getString(156));
                        mvto_listEquipo.getMvtoEquipo().setInactividad(mvto_listEquipo.getMvtoEquipo().getInactividad()+"\n"+resultSet.getString(157));
                        
                        
                        mvto_listEquipo.getMvtoEquipo().getCausaInactividad().setCodigo(mvto_listEquipo.getMvtoEquipo().getCausaInactividad().getCodigo()+"\n"+resultSet.getString(159));
                        mvto_listEquipo.getMvtoEquipo().getCausaInactividad().setDescripcion(mvto_listEquipo.getMvtoEquipo().getCausaInactividad().getDescripcion()+"\n"+resultSet.getString(160));
                        mvto_listEquipo.getMvtoEquipo().getCausaInactividad().setEstado(mvto_listEquipo.getMvtoEquipo().getCausaInactividad().getEstado()+"\n"+resultSet.getString(161));
                        
                        mvto_listEquipo.getMvtoEquipo().getUsuarioInactividad().setCodigo(mvto_listEquipo.getMvtoEquipo().getUsuarioInactividad().getCodigo()+"\n"+resultSet.getString(163));
                        mvto_listEquipo.getMvtoEquipo().getUsuarioInactividad().setNombres(mvto_listEquipo.getMvtoEquipo().getUsuarioInactividad().getNombres()+"\n"+resultSet.getString(164)+" "+resultSet.getString(165));
                        mvto_listEquipo.getMvtoEquipo().getUsuarioInactividad().setApellidos(mvto_listEquipo.getMvtoEquipo().getUsuarioInactividad().getApellidos()+"\n"+resultSet.getString(165));
                        mvto_listEquipo.getMvtoEquipo().getUsuarioInactividad().setCorreo(mvto_listEquipo.getMvtoEquipo().getUsuarioInactividad().getCorreo()+"\n"+resultSet.getString(166));
                         
                        mvto_listEquipo.getMvtoEquipo().getMotivoParada().setCodigo(mvto_listEquipo.getMvtoEquipo().getMotivoParada().getCodigo()+"\n"+resultSet.getString(169));
                        mvto_listEquipo.getMvtoEquipo().getMotivoParada().setDescripcion(mvto_listEquipo.getMvtoEquipo().getMotivoParada().getDescripcion()+"\n"+resultSet.getString(170));

                        
                        mvto_listEquipo.getMvtoEquipo().setObservacionMvtoEquipo(mvto_listEquipo.getMvtoEquipo().getObservacionMvtoEquipo()+"\n"+resultSet.getString(171));         
                        mvto_listEquipo.getMvtoEquipo().setEstado(mvto_listEquipo.getMvtoEquipo().getEstado()+"\n"+resultSet.getString(172));
                        mvto_listEquipo.getMvtoEquipo().setDesdeCarbon(mvto_listEquipo.getMvtoEquipo().getDesdeCarbon()+"\n"+resultSet.getString(173));
                        mvto_listEquipo.getMvtoEquipo().setCentroCostoDescripción(mvto_listEquipo.getMvtoEquipo().getCentroCostoDescripción()+"\n"+resultSet.getString(174));
                          
                        mvto_listEquipo.getMvtoEquipo().getCentroCostoAuxiliarDestino().getCentroCostoSubCentro().setDescripcion(mvto_listEquipo.getMvtoEquipo().getCentroCostoAuxiliarDestino().getCentroCostoSubCentro().getDescripcion()+"\n"+resultSet.getString(179));
                        mvto_listEquipo.getMvtoEquipo().getCentroCostoAuxiliarDestino().setDescripcion(mvto_listEquipo.getMvtoEquipo().getCentroCostoAuxiliarDestino().getDescripcion()+"\n"+resultSet.getString(182));
                        mvto_listEquipo.getMvtoEquipo().getCentroCostoMayor().setCodigo(mvto_listEquipo.getMvtoEquipo().getCentroCostoMayor().getCodigo()+"\n"+resultSet.getString(184));
                        mvto_listEquipo.getMvtoEquipo().getCentroCostoMayor().setDescripcion(mvto_listEquipo.getMvtoEquipo().getCentroCostoMayor().getDescripcion()+"\n"+resultSet.getString(185));
                        resultSet.next();
                    }
                    resultSet.previous();
                }catch(Exception e){
                    //e.printStackTrace();
                }
                //

                    listadoObjetos.add(mvto_listEquipo);    
                }catch(Exception e){
                    e.printStackTrace();
                }
                
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los Movimientos de Carbon");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }
    public ArrayList<PlantillaConectorMvtoCarbon>   conectorMvtoCarbon(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<PlantillaConectorMvtoCarbon> listadoObjetos = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("DECLARE @pesoNetoTotal BIGINT;\n" +
                                                                "\n" +
                                                                "  SET @pesoNetoTotal=(SELECT  SUM ([mc_peso_neto]) AS total \n" +
                                                                "        						FROM ["+DB+"].[dbo].[mvto_carbon]\n" +
                                                                "										INNER JOIN ["+DB+"].[dbo].[mvto_carbon_listado_equipo] ON [mcle_mvto_carbon_cdgo]= [mc_cdgo]\n" +
                                                                "										INNER JOIN ["+DB+"].[dbo].[mvto_equipo] ON [mcle_mvto_equipo_cdgo]= [me_cdgo]\n" +
                                                                "										INNER JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [ae_cdgo]=[me_asignacion_equipo_cdgo]\n" +
                                                                "										INNER JOIN ["+DB+"].[dbo].[equipo] ON [eq_cdgo]=[ae_equipo_cdgo]\n" +
                                                                "										INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [cca_cdgo]=[mc_cntro_cost_auxiliar_cdgo]\n" +
                                                                "										INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON  [ccs_cdgo]=[cca_cntro_cost_subcentro_cdgo]\n" +
                                                                "										INNER JOIN ["+DB+"].[dbo].[cliente] ON [mc_cliente_cdgo]=[cl_cdgo] AND [cl_base_datos_cdgo]=[mc_cliente_base_datos_cdgo]\n" +
                                                                "										LEFT JOIN ["+DB+"].[dbo].[cntro_cost] ON [cc_cdgo]=[mc_cntro_cost_cdgo]\n" +
                                                                "										INNER JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=[mc_articulo_cdgo] AND [ar_base_datos_cdgo]=[mc_articulo_base_datos_cdgo]\n" +
                                                                "										INNER JOIN ["+DB+"].[dbo].[tipo_articulo] ON [tar_cdgo]=[ar_tipo_articulo_cdgo]\n" +
                                                                "        						WHERE [mc_fecha] BETWEEN '"+DatetimeInicio+"' AND '"+DatetimeFin+"' AND [me_inactividad]=0 AND [mc_estad_mvto_carbon_cdgo]=1);\n" +
                                                                "	\n" +
                                                                "	--SELECT @pesoNetoTotal;			\n" +
                                                                "\n" +
                                                                "	SELECT  CONCAT ([eq_desc],' ',[eq_modelo]) AS Equipo,[cl_desc],[ccs_desc],[cca_desc],[cc_descripcion],[tar_undad_ngcio], SUM([mc_peso_neto]) AS total,  (( CONVERT (\n" +
                                                                "																															decimal(38,2),\n" +
                                                                "																															(SUM([mc_peso_neto]))\n" +
                                                                "																															)) * (100 /  (CONVERT (decimal(38,2), @pesoNetoTotal)))) AS porcentaje\n" +
                                                                "        						FROM ["+DB+"].[dbo].[mvto_carbon]\n" +
                                                                "										INNER JOIN ["+DB+"].[dbo].[mvto_carbon_listado_equipo] ON [mcle_mvto_carbon_cdgo]= [mc_cdgo]\n" +
                                                                "										INNER JOIN ["+DB+"].[dbo].[mvto_equipo] ON [mcle_mvto_equipo_cdgo]= [me_cdgo]\n" +
                                                                "										INNER JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [ae_cdgo]=[me_asignacion_equipo_cdgo]\n" +
                                                                "										INNER JOIN ["+DB+"].[dbo].[equipo] ON [eq_cdgo]=[ae_equipo_cdgo]\n" +
                                                                "										INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [cca_cdgo]=[mc_cntro_cost_auxiliar_cdgo]\n" +
                                                                "										INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON  [ccs_cdgo]=[cca_cntro_cost_subcentro_cdgo]\n" +
                                                                "										INNER JOIN ["+DB+"].[dbo].[cliente] ON [mc_cliente_cdgo]=[cl_cdgo] AND [cl_base_datos_cdgo]=[mc_cliente_base_datos_cdgo]\n" +
                                                                "										LEFT JOIN ["+DB+"].[dbo].[cntro_cost] ON [cc_cdgo]=[mc_cntro_cost_cdgo]\n" +
                                                                "										INNER JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=[mc_articulo_cdgo] AND [ar_base_datos_cdgo]=[mc_articulo_base_datos_cdgo]\n" +
                                                                "										INNER JOIN ["+DB+"].[dbo].[tipo_articulo] ON [tar_cdgo]=[ar_tipo_articulo_cdgo]\n" +
"        						WHERE [mc_fecha] BETWEEN '"+DatetimeInicio+"' AND '"+DatetimeFin+"' AND [me_inactividad]=0 AND [mc_estad_mvto_carbon_cdgo]=1\n" +
"								GROUP BY [eq_desc],[eq_modelo],[cl_desc],[cca_desc],[ccs_desc],[cc_descripcion],[tar_undad_ngcio] ORDER BY CONCAT ([eq_desc],' ',[eq_modelo]),[cl_desc],[ccs_desc],[cc_descripcion]");
            //query.setString(1, DatetimeInicio);
            //query.setString(2, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();    
            boolean validator=true;
            while(resultSet.next()){
                if(validator){
                    listadoObjetos = new ArrayList();
                    validator=false;
                }
                PlantillaConectorMvtoCarbon plantillaConectorMvtoCarbon = new PlantillaConectorMvtoCarbon();
                    plantillaConectorMvtoCarbon.setEquipo(resultSet.getString(1));
                    plantillaConectorMvtoCarbon.setCliente(resultSet.getString(2));
                    plantillaConectorMvtoCarbon.setSubcentro(resultSet.getString(3));
                    plantillaConectorMvtoCarbon.setCentroCostosAuxiliar(resultSet.getString(4));
                    plantillaConectorMvtoCarbon.setCentroCosto(resultSet.getString(5));
                    plantillaConectorMvtoCarbon.setUnidadNegocio(resultSet.getString(6));
                    plantillaConectorMvtoCarbon.setTotal(resultSet.getString(7));
                    plantillaConectorMvtoCarbon.setPorcentaje(resultSet.getString(8));
                listadoObjetos.add(plantillaConectorMvtoCarbon);
            }
        }catch (SQLException sqlException) {
            System.out.println("Error al tratar al consultar");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public ArrayList<PlantillaInformeRecaudoPorLavadoVehiculo>   informeRecaudoPorLavadoVehiculo(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<PlantillaInformeRecaudoPorLavadoVehiculo> listadoObjetos = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("DECLARE @fechaIni datetime, @fechaFin datetime;\n" +
                                                                "SET @fechaIni='"+DatetimeInicio+"';\n" +
                                                                "SET @fechaFin='"+DatetimeFin+"'\n" +
                                                                "\n" +
                                                                "\n" +
                                                                "--################################################################################\n" +
                                                                "DECLARE @totalRecaudo BIGINT;\n" +
                                                                "SET @totalRecaudo=(\n" +
                                                                "SELECT	SUM(costo_lavado.RECAUDO)\n" +
                                                                "FROM \n" +
                                                                "	(\n" +
                                                                "		SELECT \n" +
                                                                "			[zt_cdgo],[zt_desc] as ZONA ,CASE WHEN (SUM(mc_valorRecaudoEmpresa_lavadoVehiculo) IS NULL) THEN 0 ELSE SUM(mc_valorRecaudoEmpresa_lavadoVehiculo) END AS RECAUDO\n" +
                                                                "		FROM ["+DB+"].[dbo].[mvto_carbon]\n" +
                                                                "			INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [mc_cntro_cost_auxiliar_cdgo] = [cca_cdgo]\n" +
                                                                "			INNER JOIN ["+DB+"].[dbo].[listado_zona_trabajo] ON [lzt_cntro_cost_auxiliar_cdgo]=[mc_cntro_cost_auxiliar_cdgo]\n" +
                                                                "			INNER JOIN ["+DB+"].[dbo].[zona_trabajo] ON [lzt_zona_trabajo_cdgo]=[zt_cdgo]\n" +
                                                                "		WHERE [mc_fecha_inicio_descargue] BETWEEN @fechaIni AND @fechaFin AND [mc_lavado_vehiculo]=1 AND [mc_estad_mvto_carbon_cdgo]=1 \n" +
                                                                "				GROUP BY [zt_cdgo],[zt_desc]\n" +
                                                                "	) AS costo_lavado\n" +
                                                                "  LEFT JOIN (\n" +
                                                                "				SELECT \n" +
                                                                "					[zt_cdgo],[zt_desc], CASE WHEN (SUM([dlv_valor])IS NULL) THEN 0 ELSE  SUM([dlv_valor]) END AS DEBITO\n" +
                                                                "				FROM ["+DB+"].[dbo].[zona_trabajo]\n" +
                                                                "					LEFT JOIN ["+DB+"].[dbo].[debito_lavado_vehiculo] ON [dlv_zona_trabajo_cdgo]=[zt_cdgo]\n" +
                                                                "				WHERE [dlv_fecha_debito] BETWEEN @fechaIni AND @fechaFin\n" +
                                                                "					GROUP BY [zt_cdgo],[zt_desc]\n" +
                                                                "  ) AS debito_lavado ON costo_lavado.[zt_cdgo] = debito_lavado.[zt_cdgo]\n" +
                                                                ")\n" +
                                                                "--################################################################################\n" +
                                                                "DECLARE @totalDebito BIGINT;\n" +
                                                                "SET @totalDebito=(\n" +
                                                                "SELECT	SUM(CASE WHEN (debito_lavado.DEBITO IS NULL) THEN 0 ELSE debito_lavado.DEBITO END )AS DEBITO\n" +
                                                                "FROM \n" +
                                                                "	(\n" +
                                                                "		SELECT \n" +
                                                                "			[zt_cdgo],[zt_desc] as ZONA ,CASE WHEN (SUM(mc_valorRecaudoEmpresa_lavadoVehiculo) IS NULL) THEN 0 ELSE SUM(mc_valorRecaudoEmpresa_lavadoVehiculo) END AS RECAUDO\n" +
                                                                "		FROM ["+DB+"].[dbo].[mvto_carbon]\n" +
                                                                "			INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [mc_cntro_cost_auxiliar_cdgo] = [cca_cdgo]\n" +
                                                                "			INNER JOIN ["+DB+"].[dbo].[listado_zona_trabajo] ON [lzt_cntro_cost_auxiliar_cdgo]=[mc_cntro_cost_auxiliar_cdgo]\n" +
                                                                "			INNER JOIN ["+DB+"].[dbo].[zona_trabajo] ON [lzt_zona_trabajo_cdgo]=[zt_cdgo]\n" +
                                                                "		WHERE [mc_fecha_inicio_descargue] BETWEEN @fechaIni AND @fechaFin AND [mc_lavado_vehiculo]=1 AND [mc_estad_mvto_carbon_cdgo]=1 \n" +
                                                                "				GROUP BY [zt_cdgo],[zt_desc]\n" +
                                                                "	) AS costo_lavado\n" +
                                                                "  LEFT JOIN (\n" +
                                                                "				SELECT \n" +
                                                                "					[zt_cdgo],[zt_desc], CASE WHEN (SUM([dlv_valor])IS NULL) THEN 0 ELSE  SUM([dlv_valor]) END AS DEBITO\n" +
                                                                "				FROM ["+DB+"].[dbo].[zona_trabajo]\n" +
                                                                "					LEFT JOIN ["+DB+"].[dbo].[debito_lavado_vehiculo] ON [dlv_zona_trabajo_cdgo]=[zt_cdgo]\n" +
                                                                "				WHERE [dlv_fecha_debito] BETWEEN @fechaIni AND @fechaFin\n" +
                                                                "					GROUP BY [zt_cdgo],[zt_desc]\n" +
                                                                "  ) AS debito_lavado ON costo_lavado.[zt_cdgo] = debito_lavado.[zt_cdgo]\n" +
                                                                ")\n" +
                                                                "\n" +
                                                                "--################################################################################\n" +
                                                                "(\n" +
                                                                "	SELECT	costo_lavado.ZONA, \n" +
                                                                "			costo_lavado.RECAUDO, \n" +
                                                                "			CASE WHEN (debito_lavado.DEBITO IS NULL) THEN 0 ELSE debito_lavado.DEBITO END AS DEBITO, \n" +
                                                                "			(costo_lavado.RECAUDO - (CASE WHEN (debito_lavado.DEBITO IS NULL) THEN 0 ELSE debito_lavado.DEBITO END )) AS TOTAL_RECAUDO\n" +
                                                                "	FROM \n" +
                                                                "		(\n" +
                                                                "			SELECT \n" +
                                                                "				[zt_cdgo],[zt_desc] as ZONA ,CASE WHEN (SUM(mc_valorRecaudoEmpresa_lavadoVehiculo) IS NULL) THEN 0 ELSE SUM(mc_valorRecaudoEmpresa_lavadoVehiculo) END AS RECAUDO\n" +
                                                                "			FROM ["+DB+"].[dbo].[mvto_carbon]\n" +
                                                                "				INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [mc_cntro_cost_auxiliar_cdgo] = [cca_cdgo]\n" +
                                                                "				INNER JOIN ["+DB+"].[dbo].[listado_zona_trabajo] ON [lzt_cntro_cost_auxiliar_cdgo]=[mc_cntro_cost_auxiliar_cdgo]\n" +
                                                                "				INNER JOIN ["+DB+"].[dbo].[zona_trabajo] ON [lzt_zona_trabajo_cdgo]=[zt_cdgo]\n" +
                                                                "			WHERE [mc_fecha_inicio_descargue] BETWEEN @fechaIni AND @fechaFin AND [mc_lavado_vehiculo]=1 AND [mc_estad_mvto_carbon_cdgo]=1 \n" +
                                                                "					GROUP BY [zt_cdgo],[zt_desc]\n" +
                                                                "		) AS costo_lavado\n" +
                                                                "	  LEFT JOIN (\n" +
                                                                "					SELECT \n" +
                                                                "						[zt_cdgo],[zt_desc], CASE WHEN (SUM([dlv_valor])IS NULL) THEN 0 ELSE  SUM([dlv_valor]) END AS DEBITO\n" +
                                                                "					FROM ["+DB+"].[dbo].[zona_trabajo]\n" +
                                                                "						LEFT JOIN ["+DB+"].[dbo].[debito_lavado_vehiculo] ON [dlv_zona_trabajo_cdgo]=[zt_cdgo]\n" +
                                                                "					WHERE [dlv_fecha_debito] BETWEEN @fechaIni AND @fechaFin\n" +
                                                                "						GROUP BY [zt_cdgo],[zt_desc]\n" +
                                                                "	  ) AS debito_lavado ON costo_lavado.[zt_cdgo] = debito_lavado.[zt_cdgo]\n" +
                                                                "  )\n" +
                                                                "  UNION \n" +
                                                                "  (\n" +
                                                                "	SELECT '_TOTAL',@totalRecaudo,@totalDebito,(SELECT	 \n" +
                                                                "		SUM (costo_lavado.RECAUDO - (CASE WHEN (debito_lavado.DEBITO IS NULL) THEN 0 ELSE debito_lavado.DEBITO END ))\n" +
                                                                "	FROM \n" +
                                                                "	(\n" +
                                                                "		SELECT \n" +
                                                                "			[zt_cdgo],[zt_desc] as ZONA ,CASE WHEN (SUM(mc_valorRecaudoEmpresa_lavadoVehiculo) IS NULL) THEN 0 ELSE SUM(mc_valorRecaudoEmpresa_lavadoVehiculo) END AS RECAUDO\n" +
                                                                "		FROM ["+DB+"].[dbo].[mvto_carbon]\n" +
                                                                "			INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [mc_cntro_cost_auxiliar_cdgo] = [cca_cdgo]\n" +
                                                                "			INNER JOIN ["+DB+"].[dbo].[listado_zona_trabajo] ON [lzt_cntro_cost_auxiliar_cdgo]=[mc_cntro_cost_auxiliar_cdgo]\n" +
                                                                "			INNER JOIN ["+DB+"].[dbo].[zona_trabajo] ON [lzt_zona_trabajo_cdgo]=[zt_cdgo]\n" +
                                                                "		WHERE [mc_fecha_inicio_descargue] BETWEEN @fechaIni AND @fechaFin AND [mc_lavado_vehiculo]=1 AND [mc_estad_mvto_carbon_cdgo]=1 \n" +
                                                                "				GROUP BY [zt_cdgo],[zt_desc]\n" +
                                                                "	) AS costo_lavado\n" +
                                                                "	  LEFT JOIN (\n" +
                                                                "					SELECT \n" +
                                                                "						[zt_cdgo],[zt_desc], CASE WHEN (SUM([dlv_valor])IS NULL) THEN 0 ELSE  SUM([dlv_valor]) END AS DEBITO\n" +
                                                                "					FROM ["+DB+"].[dbo].[zona_trabajo]\n" +
                                                                "						LEFT JOIN ["+DB+"].[dbo].[debito_lavado_vehiculo] ON [dlv_zona_trabajo_cdgo]=[zt_cdgo]\n" +
                                                                "					WHERE [dlv_fecha_debito] BETWEEN @fechaIni AND @fechaFin\n" +
                                                                "						GROUP BY [zt_cdgo],[zt_desc]\n" +
                                                                "	  ) AS debito_lavado ON costo_lavado.[zt_cdgo] = debito_lavado.[zt_cdgo]) \n" +
                                                                "  )\n" +
                                                                "\n" +
                                                                "  ORDER BY ZONA DESC");
            //1query.setString(1, DatetimeInicio);
            //query.setString(2, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();    
            boolean validator=true;
            while(resultSet.next()){
                if(validator){
                    listadoObjetos = new ArrayList();
                    validator=false;
                }
                PlantillaInformeRecaudoPorLavadoVehiculo plantillaInformeRecaudoPorLavadoVehiculo = new PlantillaInformeRecaudoPorLavadoVehiculo();
                    plantillaInformeRecaudoPorLavadoVehiculo.setZona(resultSet.getString(1));
                    plantillaInformeRecaudoPorLavadoVehiculo.setRecaudo(resultSet.getString(2));
                    plantillaInformeRecaudoPorLavadoVehiculo.setDebito(resultSet.getString(3));
                    plantillaInformeRecaudoPorLavadoVehiculo.setTotalRecaudo(resultSet.getString(4));  
                listadoObjetos.add(plantillaInformeRecaudoPorLavadoVehiculo);
            }
        }catch (SQLException sqlException) {
            System.out.println("Error al tratar al consultar");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public ArrayList<PlantillaInformeRecaudoLavadoVehiculo_PorEquipo>   informeRecaudoPorLavadoVehiculo_porEquipo(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<PlantillaInformeRecaudoLavadoVehiculo_PorEquipo> listadoObjetos = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("DECLARE @fechaIni datetime, @fechaFin datetime;\n" +
                                                                "DECLARE @cantidadVehiculo BIGINT;\n" +
                                                                "DECLARE @totalRecaudoEmpresa BIGINT;\n" +
                                                                "DECLARE @totalRecaudoEquipo BIGINT;\n" +
                                                                "\n" +
                                                                "SET @fechaIni='"+DatetimeInicio+"';\n" +
                                                                "SET @fechaFin='"+DatetimeFin+"'\n" +
                                                                "SET @cantidadVehiculo=(\n" +
                                                                "							SELECT \n" +
                                                                "								COUNT([eq_cdgo]) AS 'CANT VEHICULOS LAVADOS'\n" +
                                                                "							FROM ["+DB+"].[dbo].[mvto_carbon]\n" +
                                                                "								LEFT JOIN ["+DB+"].[dbo].[cliente] ON [mc_cliente_cdgo]=[cl_cdgo]\n" +
                                                                "								LEFT JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=[mc_articulo_cdgo]\n" +
                                                                "								INNER JOIN ["+DB+"].[dbo].[equipo] ON [eq_cdgo]=[mc_equipo_lavado_cdgo]\n" +
                                                                "								INNER JOIN ["+DB+"].[dbo].[tipo_equipo] ON [te_cdgo]=[eq_tipo_equipo_cdgo]\n" +
                                                                "							WHERE [mc_fecha_inicio_descargue] BETWEEN @fechaIni AND @fechaFin	AND [mc_lavado_vehiculo]=1 AND [mc_estad_mvto_carbon_cdgo]=1\n" +
                                                                "								)\n" +
                                                                "SET @totalRecaudoEmpresa=(\n" +
                                                                "							SELECT \n" +
                                                                "								CASE WHEN (SUM(mc_valorRecaudoEmpresa_lavadoVehiculo) IS NULL) THEN 0 ELSE SUM(mc_valorRecaudoEmpresa_lavadoVehiculo) END AS 'RECAUDO EMPRESA'\n" +
                                                                "							FROM ["+DB+"].[dbo].[mvto_carbon]\n" +
                                                                "								LEFT JOIN ["+DB+"].[dbo].[cliente] ON [mc_cliente_cdgo]=[cl_cdgo]\n" +
                                                                "								LEFT JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=[mc_articulo_cdgo]\n" +
                                                                "								INNER JOIN ["+DB+"].[dbo].[equipo] ON [eq_cdgo]=[mc_equipo_lavado_cdgo]\n" +
                                                                "								INNER JOIN ["+DB+"].[dbo].[tipo_equipo] ON [te_cdgo]=[eq_tipo_equipo_cdgo]\n" +
                                                                "							WHERE [mc_fecha_inicio_descargue] BETWEEN @fechaIni AND @fechaFin	AND [mc_lavado_vehiculo]=1 AND [mc_estad_mvto_carbon_cdgo]=1\n" +
                                                                "								)\n" +
                                                                "SET @totalRecaudoEquipo=(\n" +
                                                                "							SELECT \n" +
                                                                "								CASE WHEN (SUM([mc_valorRecaudoEquipo_lavadoVehiculo]) IS NULL) THEN 0 ELSE SUM([mc_valorRecaudoEquipo_lavadoVehiculo]) END AS 'RECAUDO EQUIPO'\n" +
                                                                "							FROM ["+DB+"].[dbo].[mvto_carbon]\n" +
                                                                "								LEFT JOIN ["+DB+"].[dbo].[cliente] ON [mc_cliente_cdgo]=[cl_cdgo]\n" +
                                                                "								LEFT JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=[mc_articulo_cdgo]\n" +
                                                                "								INNER JOIN ["+DB+"].[dbo].[equipo] ON [eq_cdgo]=[mc_equipo_lavado_cdgo]\n" +
                                                                "								INNER JOIN ["+DB+"].[dbo].[tipo_equipo] ON [te_cdgo]=[eq_tipo_equipo_cdgo]\n" +
                                                                "							WHERE [mc_fecha_inicio_descargue] BETWEEN @fechaIni AND @fechaFin	AND [mc_lavado_vehiculo]=1 AND [mc_estad_mvto_carbon_cdgo]=1\n" +
                                                                "								)\n" +
                                                                "\n" +
                                                                "	SELECT \n" +
                                                                "		[cl_desc] AS 'CLIENTE',\n" +
                                                                "		[ar_desc] AS 'ARTICULO',		\n" +
                                                                "		[te_desc] AS 'TIPO EQUIPO',\n" +
                                                                "		(CASE WHEN ([te_desc] LIKE 'PALERO') THEN CONCAT([eq_desc],' ',[eq_modelo]) ELSE [te_desc] end ) AS EQUIPO,\n" +
                                                                "		COUNT([eq_cdgo]) AS 'CANT VEHICULOS LAVADOS',\n" +
                                                                "		CASE WHEN (SUM(mc_valorRecaudoEmpresa_lavadoVehiculo) IS NULL) THEN 0 ELSE SUM(mc_valorRecaudoEmpresa_lavadoVehiculo) END AS 'RECAUDO_EMPRESA',\n" +
                                                                "		CASE WHEN (SUM([mc_valorRecaudoEquipo_lavadoVehiculo]) IS NULL) THEN 0 ELSE SUM([mc_valorRecaudoEquipo_lavadoVehiculo]) END AS 'RECAUDO_EQUIPO'\n" +
                                                                "	FROM ["+DB+"].[dbo].[mvto_carbon]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente] ON [mc_cliente_cdgo]=[cl_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=[mc_articulo_cdgo]\n" +
                                                                "		INNER JOIN ["+DB+"].[dbo].[equipo] ON [eq_cdgo]=[mc_equipo_lavado_cdgo]\n" +
                                                                "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] ON [te_cdgo]=[eq_tipo_equipo_cdgo]\n" +
                                                                "	WHERE [mc_fecha_inicio_descargue] BETWEEN @fechaIni AND @fechaFin	AND [mc_lavado_vehiculo]=1 AND [mc_estad_mvto_carbon_cdgo]=1\n" +
                                                                "	GROUP BY	\n" +
                                                                "				[cl_cdgo] ,[cl_desc],\n" +
                                                                "				[ar_cdgo],[ar_desc]--,\n" +
                                                                "				,[te_cdgo], [te_desc], (CASE WHEN ([te_desc] LIKE 'PALERO') THEN CONCAT([eq_desc],' ',[eq_modelo]) ELSE [te_desc] end ) 	\n" +
                                                                "UNION\n" +
                                                                "	SELECT '','','','_TOTAL',@cantidadVehiculo,@totalRecaudoEmpresa,@totalRecaudoEquipo ORDER BY 'CLIENTE' DESC ");
            //1query.setString(1, DatetimeInicio);
            //query.setString(2, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();    
            boolean validator=true;
            while(resultSet.next()){
                if(validator){
                    listadoObjetos = new ArrayList();
                    validator=false;
                }
                PlantillaInformeRecaudoLavadoVehiculo_PorEquipo plantillaInformeRecaudoLavadoVehiculo_PorEquipo = new PlantillaInformeRecaudoLavadoVehiculo_PorEquipo();
                    plantillaInformeRecaudoLavadoVehiculo_PorEquipo.setCliente(resultSet.getString(1));
                    plantillaInformeRecaudoLavadoVehiculo_PorEquipo.setArticulo(resultSet.getString(2));
                    plantillaInformeRecaudoLavadoVehiculo_PorEquipo.setTipoEquipo(resultSet.getString(3));
                    plantillaInformeRecaudoLavadoVehiculo_PorEquipo.setEquipo(resultSet.getString(4));  
                    plantillaInformeRecaudoLavadoVehiculo_PorEquipo.setCantidad(resultSet.getString(5));  
                    plantillaInformeRecaudoLavadoVehiculo_PorEquipo.setRecaudoEmpresa(resultSet.getString(6));  
                    plantillaInformeRecaudoLavadoVehiculo_PorEquipo.setRecaudoEquipo(resultSet.getString(7));  
                listadoObjetos.add(plantillaInformeRecaudoLavadoVehiculo_PorEquipo);
            }
        }catch (SQLException sqlException) {
            System.out.println("Error al tratar al consultar");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public ArrayList<PlantillaInformeRecaudoPorUsuario>   informeRecaudoPorLavadoVehiculo_porUsuario(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<PlantillaInformeRecaudoPorUsuario> listadoObjetos = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("DECLARE @fechaIni datetime, @fechaFin datetime;\n" +
                                                                "SET @fechaIni='"+DatetimeInicio+"';\n" +
                                                                "SET @fechaFin='"+DatetimeFin+"'\n" +
                                                                "	SELECT	\n" +
                                                                "			costo_lavado.[us_cdgo],\n" +
                                                                "			costo_lavado.usuario,\n" +
                                                                "			costo_lavado.ZONA, \n" +
                                                                "			costo_lavado.RECAUDO\n" +
                                                                "	FROM \n" +
                                                                "		(\n" +
                                                                "			SELECT \n" +
                                                                "				[us_cdgo],(CONCAT ([us_nombres], ' ', [us_apellidos])) AS usuario,[zt_cdgo],[zt_desc] as ZONA ,CASE WHEN (SUM([mc_valorRecaudoEmpresa_lavadoVehiculo]) IS NULL) THEN 0 ELSE SUM([mc_valorRecaudoEmpresa_lavadoVehiculo]) END AS RECAUDO\n" +
                                                                "			FROM ["+DB+"].[dbo].[mvto_carbon]\n" +
                                                                "				INNER JOIN ["+DB+"].[dbo].[usuario] ON [us_cdgo]=[mc_usuario_cierre_cdgo]\n" +
                                                                "				INNER JOIN ["+DB+"].[dbo].[equipo] ON [eq_cdgo]=[mc_equipo_lavado_cdgo]\n" +
                                                                "				INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [mc_cntro_cost_auxiliar_cdgo] = [cca_cdgo]\n" +
                                                                "				INNER JOIN ["+DB+"].[dbo].[listado_zona_trabajo] ON [lzt_cntro_cost_auxiliar_cdgo]=[mc_cntro_cost_auxiliar_cdgo]\n" +
                                                                "				INNER JOIN ["+DB+"].[dbo].[zona_trabajo] ON [lzt_zona_trabajo_cdgo]=[zt_cdgo]\n" +
                                                                "			WHERE [mc_fecha_inicio_descargue] BETWEEN @fechaIni AND @fechaFin AND [mc_lavado_vehiculo]=1 AND [mc_estad_mvto_carbon_cdgo]=1\n" +
                                                                "					GROUP BY [us_cdgo],(CONCAT ([us_nombres], ' ', [us_apellidos])), [zt_cdgo],[zt_desc]\n" +
                                                                "		) AS costo_lavado\n" +
                                                                "		WHERE costo_lavado.RECAUDO > 0");
            //1query.setString(1, DatetimeInicio);
            //query.setString(2, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();    
            boolean validator=true;
            while(resultSet.next()){
                if(validator){
                    listadoObjetos = new ArrayList();
                    validator=false;
                }
                PlantillaInformeRecaudoPorUsuario plantillaInformeRecaudoPorUsuario = new PlantillaInformeRecaudoPorUsuario();
                    plantillaInformeRecaudoPorUsuario.setCedula(resultSet.getString(1));
                    plantillaInformeRecaudoPorUsuario.setNombre(resultSet.getString(2));
                    plantillaInformeRecaudoPorUsuario.setZonaOperacion(resultSet.getString(3));
                    plantillaInformeRecaudoPorUsuario.setRecaudo(resultSet.getString(4));       
                listadoObjetos.add(plantillaInformeRecaudoPorUsuario);
            }
        }catch (SQLException sqlException) {
            System.out.println("Error al tratar al consultar");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public ArrayList<PlantillaInformeNoLavadoVehiculos>   informeCantidadVehiculosPorConceptoNOLavado(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<PlantillaInformeNoLavadoVehiculos> listadoObjetos = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            /*PreparedStatement query= conexion.prepareStatement("DECLARE @fechaIni datetime, @fechaFin datetime; \n" +
                                                                "DECLARE @cantidadVehiculo BIGINT; \n" +
                                                                "DECLARE @totalRecaudoEmpresa BIGINT; \n" +
                                                                "DECLARE @totalRecaudoEquipo BIGINT; \n" +
                                                                "                                                                 \n" +
                                                                "SET @fechaIni='"+DatetimeInicio+"'; \n" +
                                                                "SET @fechaFin='"+DatetimeFin+"' \n" +
                                                                "SET @cantidadVehiculo=( \n" +
                                                                "						SELECT\n" +
                                                                "							COUNT([mc_lavado_vehiculo])\n" +
                                                                "						FROM ["+DB+"].[dbo].[mvto_carbon] \n" +
                                                                "							LEFT JOIN ["+DB+"].[dbo].[cliente] ON [mc_cliente_cdgo]=[cl_cdgo] \n" +
                                                                "							LEFT JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=[mc_articulo_cdgo] \n" +
                                                                "							LEFT JOIN ["+DB+"].[dbo].[motivo_nolavado_vehiculo] ON [mc_motivo_nolavado_vehiculo_cdgo]=[mnlv_cdgo]\n" +
                                                                "						WHERE [mc_fecha_inicio_descargue] BETWEEN @fechaIni AND @fechaFin	AND [mc_lavado_vehiculo] =0 AND [mc_estad_mvto_carbon_cdgo]=1\n" +
                                                                "						)\n" +
                                                                "\n" +
                                                                "\n" +
                                                                "SELECT  \n" +
                                                                "    [cl_desc] AS 'CLIENTE', \n" +
                                                                "    [ar_desc] AS 'ARTICULO',	\n" +
                                                                "	[zt_desc] AS 'ZONA OPERACIÓN',\n" +
                                                                "	[mnlv_desc] AS 'MOTIVO_NO_LAVADO_VEHÍCULO',\n" +
                                                                "	COUNT ([mc_lavado_vehiculo])	 AS 'CANTIDAD_VEHÍCULOS'\n" +
                                                                "FROM ["+DB+"].[dbo].[mvto_carbon] \n" +
                                                                "       LEFT JOIN ["+DB+"].[dbo].[cliente] ON [mc_cliente_cdgo]=[cl_cdgo] \n" +
                                                                "       LEFT JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=[mc_articulo_cdgo] \n" +
                                                                "       LEFT JOIN ["+DB+"].[dbo].[motivo_nolavado_vehiculo] ON [mc_motivo_nolavado_vehiculo_cdgo]=[mnlv_cdgo]\n" +
                                                                "	INNER JOIN ["+DB+"].[dbo].[listado_zona_trabajo] ON [lzt_cntro_cost_auxiliar_cdgo]=[mc_cntro_cost_auxiliar_cdgo]\n" +
                                                                "       INNER JOIN ["+DB+"].[dbo].[zona_trabajo] ON [lzt_zona_trabajo_cdgo]=[zt_cdgo]\n" +
                                                                "WHERE [mc_fecha_inicio_descargue] BETWEEN @fechaIni AND @fechaFin	AND [mc_lavado_vehiculo] =0 AND [mc_estad_mvto_carbon_cdgo]=1 \n" +
                                                                "GROUP BY	 \n" +
                                                                "            [cl_cdgo] ,[cl_desc], \n" +
                                                                "            [ar_cdgo],[ar_desc],[zt_desc],[mnlv_desc],[zt_cdgo]	 \n" +
                                                                "UNION \n" +
                                                                "SELECT '','','','_TOTAL',@cantidadVehiculo ORDER BY 'CLIENTE' DESC");*/
            PreparedStatement query= conexion.prepareStatement("DECLARE @fechaIni datetime, @fechaFin datetime; \n" +
                                                                "DECLARE @cantidadVehiculo BIGINT; \n" +
                                                                "DECLARE @totalRecaudoEmpresa BIGINT; \n" +
                                                                "DECLARE @totalRecaudoEquipo BIGINT; \n" +
                                                                "                                                                 \n" +
                                                                "SET @fechaIni='"+DatetimeInicio+"'; \n" +
                                                                "SET @fechaFin='"+DatetimeFin+"' \n" +
                                                                "SET @cantidadVehiculo=( \n" +
                                                                "						SELECT\n" +
                                                                "							COUNT([mc_lavado_vehiculo])\n" +
                                                                "						FROM ["+DB+"].[dbo].[mvto_carbon] \n" +
                                                                "							LEFT JOIN ["+DB+"].[dbo].[cliente] ON [mc_cliente_cdgo]=[cl_cdgo] \n" +
                                                                "							LEFT JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=[mc_articulo_cdgo] \n" +
                                                                "							LEFT JOIN ["+DB+"].[dbo].[motivo_nolavado_vehiculo] ON [mc_motivo_nolavado_vehiculo_cdgo]=[mnlv_cdgo]\n" +
                                                                "						WHERE [mc_fecha_inicio_descargue] BETWEEN @fechaIni AND @fechaFin	AND [mc_lavado_vehiculo] =0 AND [mc_estad_mvto_carbon_cdgo]=1\n" +
                                                                "						)\n" +
                                                                "\n" +
                                                                "\n" +
                                                                "SELECT  \n" +
                                                                "    [cl_desc] AS 'CLIENTE', \n" +
                                                                "    [ar_desc] AS 'ARTICULO',	\n" +
                                                                "	[zt_desc] AS 'ZONA OPERACIÓN',\n" +
                                                                "	STRING_AGG( CONCAT ([eq_desc],' ',[eq_modelo]) , ' / ') AS 'EQUIPO',\n" +
                                                                "	[mnlv_desc] AS 'MOTIVO_NO_LAVADO_VEHÍCULO',\n" +
                                                                "	COUNT (distinct([mc_fecha]))	 AS 'CANTIDAD_VEHÍCULOS'\n" +
                                                                "FROM ["+DB+"].[dbo].[mvto_carbon] \n" +
                                                                "       LEFT JOIN ["+DB+"].[dbo].[cliente] ON [mc_cliente_cdgo]=[cl_cdgo] \n" +
                                                                "       LEFT JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=[mc_articulo_cdgo] \n" +
                                                                "       LEFT JOIN ["+DB+"].[dbo].[motivo_nolavado_vehiculo] ON [mc_motivo_nolavado_vehiculo_cdgo]=[mnlv_cdgo]\n" +
                                                                "	INNER JOIN ["+DB+"].[dbo].[listado_zona_trabajo] ON [lzt_cntro_cost_auxiliar_cdgo]=[mc_cntro_cost_auxiliar_cdgo]\n" +
                                                                "       INNER JOIN ["+DB+"].[dbo].[zona_trabajo] ON [lzt_zona_trabajo_cdgo]=[zt_cdgo]"
                                                            +   "         INNER JOIN ["+DB+"].[dbo].[mvto_carbon_listado_equipo] ON [mcle_mvto_carbon_cdgo]= [mc_cdgo]\n" +
                                                                "		INNER JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [ae_cdgo]=[mcle_asignacion_equipo_cdgo]\n" +
                                                                "		INNER JOIN ["+DB+"].[dbo].[equipo] ON [eq_cdgo]=[ae_equipo_cdgo]\n" +
                                                                "   WHERE [mc_fecha_inicio_descargue] BETWEEN @fechaIni AND @fechaFin	AND [mc_lavado_vehiculo] =0 AND [mc_estad_mvto_carbon_cdgo]=1 \n" +
                                                                "   GROUP BY	[cl_cdgo] ,[cl_desc],  \n" +
                                                                "            [ar_cdgo],[ar_desc],[zt_desc],[mnlv_desc],[zt_cdgo],[mc_lavado_vehiculo]\n" +
                                                                "UNION \n" +
                                                                "SELECT '','','','','_TOTAL',@cantidadVehiculo ORDER BY 'CLIENTE' DESC");
            //1query.setString(1, DatetimeInicio);
            //query.setString(2, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();    
            boolean validator=true;
            while(resultSet.next()){
                if(validator){
                    listadoObjetos = new ArrayList();
                    validator=false;
                }
                PlantillaInformeNoLavadoVehiculos plantillaInformeNoLavadoVehiculos = new PlantillaInformeNoLavadoVehiculos();
                    plantillaInformeNoLavadoVehiculos.setCliente(resultSet.getString(1));
                    plantillaInformeNoLavadoVehiculos.setArticulo(resultSet.getString(2));
                    plantillaInformeNoLavadoVehiculos.setZonaOperacion(resultSet.getString(3));
                    plantillaInformeNoLavadoVehiculos.setEquipo(resultSet.getString(4));
                    plantillaInformeNoLavadoVehiculos.setMotivoNoLavado(resultSet.getString(5));       
                    plantillaInformeNoLavadoVehiculos.setCantidadVehículo(resultSet.getString(6));       
                listadoObjetos.add(plantillaInformeNoLavadoVehiculos);
            }
        }catch (SQLException sqlException) {
            System.out.println("Error al tratar al consultar");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public String                               getMes(String mes){
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
    public MvtoCarbon_ListadoEquipos            buscarMvtoCarbonParticularPorCódigoMvtoEquipo(String CodigoMvtoEquipo) throws SQLException{
        Conexion_DB_costos_vg control=null;  
        MvtoCarbon_ListadoEquipos mvto_listEquipo =null;
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT [mcle_cdgo]--1\n" +
                                                                "      ,[mcle_mvto_carbon_cdgo]--2\n" +
                                                                "			,[mc_cdgo]--3\n" +
                                                                "			,[mc_cntro_oper_cdgo]--4\n" +
                                                                "				,mc_cntro_oper.[co_cdgo]--5\n" +
                                                                "				,mc_cntro_oper.[co_desc]--6\n" +
                                                                "			,[mc_cntro_cost_auxiliar_cdgo]--7\n" +
                                                                "				,mc_cntro_cost_auxiliar.[cca_cdgo]--8\n" +
                                                                "				,mc_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo]--9\n" +
                                                                "					,mc_cntro_cost_subcentro.[ccs_cdgo]--10\n" +
                                                                "					,mc_cntro_cost_subcentro.[ccs_desc]--11\n" +
                                                                "					,mc_cntro_cost_subcentro.[ccs_cntro_cost_rquiere_labor_realizda]--12\n" +
                                                                "				,mc_cntro_cost_auxiliar.[cca_desc]--13\n" +
                                                                "			,[mc_cntro_cost_cdgo]--14\n" +
                                                                "					,mc_cntro_cost.[cc_cdgo]--15\n" +
                                                                "					,mc_cntro_cost.[cc_descripcion]--16\n" +
                                                                "			,[mc_labor_realizada_cdgo]--17\n" +
                                                                "					,mc_labor_realizada.[lr_cdgo]--18\n" +
                                                                "					,mc_labor_realizada.[lr_desc]	--19\n" +
                                                                "			,[mc_articulo_cdgo]--20\n" +
                                                                "					,mc_articulo.[ar_cdgo]--21\n" +
                                                                "					,mc_articulo.[ar_tipo_articulo_cdgo]--22\n" +
                                                                "						,mc_tipo_articulo.[tar_cdgo]--23\n" +
                                                                "						,mc_tipo_articulo.[tar_desc]--24\n" +
                                                                "						,mc_tipo_articulo.[tar_cdgo_erp]--25\n" +
                                                                "						,mc_tipo_articulo.[tar_undad_ngcio]--26\n" +
                                                                "					,mc_articulo.[ar_desc]--27\n" +
                                                                "			,[mc_cliente_cdgo]--28\n" +
                                                                "				,mc_cliente.[cl_cdgo]--29\n" +
                                                                "				,mc_cliente.[cl_desc]--30\n" +
                                                                "			,[mc_transprtdora_cdgo]--31\n" +
                                                                "				,[tr_cdgo]--32\n" +
                                                                "				,[tr_nit]--33\n" +
                                                                "				,[tr_desc]--34\n" +
                                                                "			,datename (MONTH ,[mc_fecha])--35\n" +
                                                                "			,[mc_fecha]--36\n" +
                                                                "			,[mc_num_orden]--37\n" +
                                                                "			,[mc_deposito]--38\n" +
                                                                "			,[mc_consecutivo_tqute]--39\n" +
                                                                "			,[mc_placa_vehiculo]--40\n" +
                                                                "			,[mc_peso_vacio]--41\n" +
                                                                "			,[mc_peso_lleno]--42\n" +
                                                                "			,[mc_peso_neto]--43\n" +
                                                                "			,[mc_fecha_entrad]--44\n" +
                                                                "			,[mc_fecha_salid]--45\n" +
                                                                "			,[mc_fecha_inicio_descargue]--46\n" +
                                                                "			,[mc_fecha_fin_descargue]--47\n" +
                                                                "			,[mc_usuario_cdgo]--48\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_cdgo]--49\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_nombres]--50\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_apellidos]--51\n" +
                                                                "				,mc_usuarioQuienRegistra.[us_correo]--52\n" +
                                                                "			,[mc_observ]--53\n" +
                                                                "			,[mc_estad_mvto_carbon_cdgo]--54\n" +
                                                                "				,[emc_cdgo]--55\n" +
                                                                "				,[emc_desc]--56\n" +
                                                                "			,[mc_conexion_peso_ccarga]--57\n" +
                                                                "			,[mc_registro_manual]--58\n" +
                                                                "			,[mc_usuario_registro_manual_cdgo]--59\n" +
                                                                "				,mc_usuario_registro_manual.[us_cdgo]--60\n" +
                                                                "				,mc_usuario_registro_manual.[us_nombres]--61\n" +
                                                                "				,mc_usuario_registro_manual.[us_apellidos]--62\n" +
                                                                "				,mc_usuario_registro_manual.[us_correo]--63\n" +
                                                                "			,[mc_cntro_cost_auxiliarDestino_cdgo]--64\n" +
                                                                "				,mc_cntro_cost_auxiliarDestino.[cca_cdgo]--65\n" +
                                                                "				,mc_cntro_cost_auxiliarDestino.[cca_desc]--66\n" +
                                                                "			,[mc_cntro_cost_mayor_cdgo]--67\n" +
                                                                "				,mc_cntro_cost_mayor.[ccm_cdgo]--68\n" +
                                                                "				,mc_cntro_cost_mayor.[ccm_desc]--69\n" +
                                                                "      ,[mcle_asignacion_equipo_cdgo]--70\n" +
                                                                "      ,[mcle_mvto_equipo_cdgo]--71\n" +
                                                                "			,[me_cdgo] --72\n" +
                                                                "			,[me_asignacion_equipo_cdgo] --73\n" +
                                                                "				,[ae_cdgo] --74\n" +
                                                                "				,[ae_equipo_cdgo] --75\n" +
                                                                "					,[eq_cdgo] --76\n" +
                                                                "					,[eq_tipo_equipo_cdgo] --77\n" +
                                                                "						,[te_cdgo]  --78\n" +
                                                                "						,[te_desc] --79\n" +
                                                                "					,[eq_marca] --80\n" +
                                                                "					,[eq_modelo] --81\n" +
                                                                "					,[eq_desc]	 	--82\n" +
                                                                "				,[ae_fecha_registro] --83\n" +
                                                                "				,[ae_fecha_hora_inicio] --84\n" +
                                                                "				,[ae_fecha_hora_fin] --85\n" +
                                                                "				,[ae_cant_minutos] --86\n" +
                                                                "				,[ae_equipo_pertenencia_cdgo] --87\n" +
                                                                "					,[ep_cdgo] --88\n" +
                                                                "					,[ep_desc] --89\n" +
                                                                "					,[ep_estad] --90\n" +
                                                                "			,datename (MONTH ,[me_fecha])  -- 91\n" +
                                                                "			,[me_fecha] --92\n" +
                                                                "			,[me_proveedor_equipo_cdgo] --93\n" +
                                                                "				,[pe_cdgo] --94\n" +
                                                                "				,[pe_nit] --95\n" +
                                                                "				,[pe_desc] --96\n" +
                                                                "			,[me_num_orden] --97\n" +
                                                                "			,[me_cntro_oper_cdgo] --98\n" +
                                                                "				,me_cntro_oper.[co_cdgo] --99\n" +
                                                                "				,me_cntro_oper.[co_desc] --100\n" +
                                                                "			,[me_cntro_cost_auxiliar_cdgo] --101\n" +
                                                                "				,cca_origen.[cca_cdgo] --102\n" +
                                                                "				,cca_origen.[cca_cntro_cost_subcentro_cdgo] --103\n" +
                                                                "					,ccs_origen.[ccs_cdgo] --104\n" +
                                                                "					,ccs_origen.[ccs_desc] --105\n" +
                                                                "					,ccs_origen.[ccs_cntro_oper_cdgo] --106\n" +
                                                                "					,ccs_origen.[ccs_cntro_cost_rquiere_labor_realizda] --107\n" +
                                                                "				,cca_origen.[cca_desc] --108\n" +
                                                                "			,[me_cntro_cost_cdgo] --109\n" +
                                                                "				,me_cntro_cost.[cc_cdgo] --110\n" +
                                                                "				,me_cntro_cost.[cc_descripcion] --111\n" +
                                                                "			,[me_labor_realizada_cdgo] --112\n" +
                                                                "				,me_labor_realizada.[lr_cdgo] --113\n" +
                                                                "				,me_labor_realizada.[lr_desc] --114\n" +
                                                                "				,me_labor_realizada.[lr_operativa] --115\n" +
                                                                "				,me_labor_realizada.[lr_parada] --116\n" +
                                                                "			,[me_cliente_cdgo] --117\n" +
                                                                "				,me_cliente.[cl_cdgo] --118\n" +
                                                                "				,me_cliente.[cl_desc] --119\n" +
                                                                "			,[me_articulo_cdgo] --120\n" +
                                                                "				,me_articulo.[ar_cdgo] --121\n" +
                                                                "				,me_articulo.[ar_tipo_articulo_cdgo]--122\n" +
                                                                "					,me_tipo_articulo.[tar_cdgo] --123\n" +
                                                                "					,me_tipo_articulo.[tar_desc] --124\n" +
                                                                "					,me_tipo_articulo.[tar_cdgo_erp] --125\n" +
                                                                "					,me_tipo_articulo.[tar_undad_ngcio] --126\n" +
                                                                "				,me_articulo.[ar_desc] --127\n" +
                                                                "			,[me_motonave_cdgo] --128\n" +
                                                                "				,[mn_cdgo] --129\n" +
                                                                "				,[mn_desc] --130\n" +
                                                                "			,[me_fecha_hora_inicio] --131\n" +
                                                                "			,[me_fecha_hora_fin] --132\n" +
                                                                "			,[me_total_minutos] --133\n" +
                                                                "			,[me_valor_hora] --134\n" +
                                                                "			,[me_costo_total] --135\n" +
                                                                "			,[me_recobro_cdgo] --136\n" +
                                                                "				,[rc_cdgo] --137\n" +
                                                                "				,[rc_desc] --138\n" +
                                                                "				,[rc_estad] --139\n" +
                                                                "			,[me_cliente_recobro_cdgo] --140\n" +
                                                                "			,[me_costo_total_recobro_cliente] --141\n" +
                                                                "			,[me_usuario_registro_cdgo] --142\n" +
                                                                "				,us_registro.[us_cdgo] --143\n" +
                                                                "				,us_registro.[us_nombres] --144\n" +
                                                                "				,us_registro.[us_apellidos] --145\n" +
                                                                "				,us_registro.[us_correo] --146\n" +
                                                                "			,[me_usuario_autorizacion_cdgo] --147\n" +
                                                                "				,us_autoriza.[us_cdgo] --148\n" +
                                                                "				,us_autoriza.[us_nombres] --149\n" +
                                                                "				,us_autoriza.[us_apellidos] --150\n" +
                                                                "				,us_autoriza.[us_correo] --151\n" +
                                                                "			,[me_autorizacion_recobro_cdgo] --152\n" +
                                                                "				,[are_cdgo] --153\n" +
                                                                "				,[are_desc] --154\n" +
                                                                "				,[are_estad] --155\n" +
                                                                "			,[me_observ_autorizacion] --156\n" +
                                                                "			,[me_inactividad] --157\n" +
                                                                "			,[me_causa_inactividad_cdgo] --158\n" +
                                                                "				,[ci_cdgo] --159\n" +
                                                                "				,[ci_desc] --160\n" +
                                                                "				,[ci_estad] --161\n" +
                                                                "			,[me_usuario_inactividad_cdgo] --162\n" +
                                                                "				,us_inactividad.[us_cdgo] --163\n" +
                                                                "				,us_inactividad.[us_nombres] --164\n" +
                                                                "				,us_inactividad.[us_apellidos] --165\n" +
                                                                "				,us_inactividad.[us_correo] --166\n" +
                                                                "			,[me_motivo_parada_estado] --167\n" +
                                                                "			,[me_motivo_parada_cdgo] --168\n" +
                                                                "				,[mpa_cdgo] --169\n" +
                                                                "				,[mpa_desc] --170\n" +
                                                                "			,[me_observ] --171\n" +
                                                                "			,CASE [me_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_estado] --172\n" +
                                                                "			,CASE [me_desde_mvto_carbon] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [me_desde_mvto_carbon] --173\n" +
                                                                "			,[me_cntro_cost] --174\n" +
                                                                "			,[me_cntro_cost_auxiliarDestino_cdgo] --175\n" +
                                                                "				,cca_destino.[cca_cdgo] --176\n" +
                                                                "				,cca_destino.[cca_cntro_cost_subcentro_cdgo] --177\n" +
                                                                "					,ccs_destino.[ccs_cdgo] --178\n" +
                                                                "					,ccs_destino.[ccs_desc] --179\n" +
                                                                "					,ccs_destino.[ccs_cntro_oper_cdgo] --180\n" +
                                                                "					,ccs_destino.[ccs_cntro_cost_rquiere_labor_realizda] --181\n" +
                                                                "				,cca_destino.[cca_desc] --182\n" +
                                                                "			,[me_cntro_cost_mayor_cdgo] --183\n" +
                                                                "				,me_cntro_cost_mayor.[ccm_cdgo] --184\n" +
                                                                "				,me_cntro_cost_mayor.[ccm_desc]  --  185    \n" +
                                                                "				,Tiempo_Vehiculo_Descargue=(SELECT (DATEDIFF (MINUTE,  [mc_fecha_inicio_descargue], [mc_fecha_fin_descargue])))  --186\n" +
                                                                "				,Tiempo_Equipo_Descargue=(SELECT (DATEDIFF (MINUTE,  [me_fecha_hora_inicio], [me_fecha_hora_fin])))  --187                                        \n" +
                                                                "      ,CASE [mcle_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [mcle_estado]--188\n" +
                                                                "      ,[mc_equipo_lavado_cdgo]--189 \n" +
                                                                "      ,[mc_lavado_vehiculo]--190 \n" +
                                                                "      ,[mc_costoLavadoVehiculo]--191 \n" +
                                                                "      ,[mc_valorRecaudoEmpresa_lavadoVehiculo]--192 \n" +
                                                                "      ,[mc_valorRecaudoEquipo_lavadoVehiculo]--193 \n" +
                    
                                                                "      ,mc_cntro_cost_base_datos.[bd_cdgo]   --194 \n" +
                                                                "      ,mc_articulo_base_datos.[bd_cdgo]   --195 \n" +
                                                                "      ,mc_cliente_base_datos.[bd_cdgo]   --196 \n" +
                                                                "      ,mc_transprtdora_base_datos.[bd_cdgo]   --197 \n" +
                                                                "      ,mc_cntro_cost_mayor_base_datos.[bd_cdgo]   --198 \n" +
                    
                                                                "      ,me_cntro_cost_base_datos.[bd_cdgo]   --199 \n" +
                                                                "      ,me_cliente_base_datos.[bd_cdgo]   --200 \n" +
                                                                "      ,me_articulo_base_datos.[bd_cdgo]   --201 \n" +
                                                                "      ,me_motonave_base_datos.[bd_cdgo]   --202 \n" +
                                                                "      ,me_cntro_cost_mayor_base_datos.[bd_cdgo]  --203 \n" +
                    
                                                                "  FROM ["+DB+"].[dbo].[mvto_carbon_listado_equipo]\n" +
                                                                "		INNER JOIN ["+DB+"].[dbo].[mvto_carbon] ON [mcle_mvto_carbon_cdgo]=[mc_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_oper] mc_cntro_oper ON [mc_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                "		LEFT JOIN  ["+DB+"].[dbo].[cntro_cost_auxiliar] mc_cntro_cost_auxiliar ON [mc_cntro_cost_auxiliar_cdgo]=mc_cntro_cost_auxiliar.[cca_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] mc_cntro_cost_subcentro ON mc_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo]=mc_cntro_cost_subcentro.[ccs_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost] mc_cntro_cost ON [mc_cntro_cost_cdgo]=mc_cntro_cost.[cc_cdgo]\n" +      
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cntro_cost_base_datos ON mc_cntro_cost.[cc_cliente_base_datos_cdgo]=mc_cntro_cost_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[labor_realizada] mc_labor_realizada ON [mc_labor_realizada_cdgo] =  mc_labor_realizada.[lr_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[articulo] mc_articulo ON [mc_articulo_cdgo]=mc_articulo.[ar_cdgo] AND mc_articulo.[ar_base_datos_cdgo]=[mc_articulo_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_articulo_base_datos ON mc_articulo.[ar_base_datos_cdgo]=mc_articulo_base_datos.[bd_cdgo] \n"+
                                                                        "		LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] mc_tipo_articulo ON mc_articulo.[ar_tipo_articulo_cdgo]=mc_tipo_articulo.[tar_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente] mc_cliente ON [mc_cliente_cdgo]=mc_cliente.[cl_cdgo] AND mc_cliente.[cl_base_datos_cdgo]=[mc_cliente_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cliente_base_datos ON mc_cliente.[cl_base_datos_cdgo]=mc_cliente_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[transprtdora] ON [mc_transprtdora_cdgo]=[tr_cdgo] AND [tr_base_datos_cdgo]=[mc_transprtdora_base_datos_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_transprtdora_base_datos ON [tr_base_datos_cdgo]=mc_transprtdora_base_datos.[bd_cdgo] \n"+    
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] mc_usuarioQuienRegistra ON [mc_usuario_cdgo]=mc_usuarioQuienRegistra.[us_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[estad_mvto_carbon] ON [mc_estad_mvto_carbon_cdgo]=[emc_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] mc_usuario_registro_manual ON [mc_usuario_registro_manual_cdgo]=mc_usuario_registro_manual.[us_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] mc_cntro_cost_auxiliarDestino ON [mc_cntro_cost_auxiliarDestino_cdgo]=mc_cntro_cost_auxiliarDestino.[cca_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor] mc_cntro_cost_mayor ON [mc_cntro_cost_mayor_cdgo]=mc_cntro_cost_mayor.[ccm_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cntro_cost_mayor_base_datos ON [ccm_cliente_base_datos_cdgo]=mc_cntro_cost_mayor_base_datos.[bd_cdgo] \n"+  
                                                                
                                                                
                                                                
                                                                    
                                                                                                                                               
                                                                "               LEFT JOIN ["+DB+"].[dbo].[mvto_equipo] ON [mcle_mvto_equipo_cdgo]=[me_cdgo]\n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [me_asignacion_equipo_cdgo]=[ae_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [me_proveedor_equipo_cdgo]=[pe_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_oper] me_cntro_oper ON [me_cntro_oper_cdgo]=me_cntro_oper.[co_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_origen ON  [me_cntro_cost_auxiliar_cdgo]=cca_origen.[cca_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_origen ON cca_origen.[cca_cntro_cost_subcentro_cdgo]=ccs_origen.[ccs_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost] me_cntro_cost ON [me_cntro_cost_cdgo]=me_cntro_cost.[cc_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_base_datos ON me_cntro_cost.[cc_cliente_base_datos_cdgo]=me_cntro_cost_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[labor_realizada] me_labor_realizada ON [me_labor_realizada_cdgo]=me_labor_realizada.[lr_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente] me_cliente ON [me_cliente_cdgo]=me_cliente.[cl_cdgo] AND me_cliente.[cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON me_cliente.[cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[articulo] me_articulo ON [me_articulo_cdgo]=me_articulo.[ar_cdgo] AND	me_articulo.[ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON me_articulo.[ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] me_tipo_articulo ON me_articulo.[ar_tipo_articulo_cdgo]=me_tipo_articulo.[tar_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[motonave] ON [me_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[me_motonave_base_datos_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_motonave_base_datos ON  [mn_base_datos_cdgo]=me_motonave_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] us_registro ON [me_usuario_registro_cdgo]=us_registro.[us_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] us_autoriza ON [me_usuario_autorizacion_cdgo]=us_autoriza.[us_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[autorizacion_recobro] ON [me_autorizacion_recobro_cdgo]=[are_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[causa_inactividad] ON [me_causa_inactividad_cdgo]=[ci_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[usuario] us_inactividad ON [me_usuario_inactividad_cdgo]=us_inactividad.[us_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[motivo_parada] ON [me_motivo_parada_cdgo]=[mpa_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] cca_destino ON [me_cntro_cost_auxiliarDestino_cdgo]=cca_destino.[cca_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ccs_destino ON cca_destino.[cca_cntro_cost_subcentro_cdgo]=ccs_destino.[ccs_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cntro_cost_mayor] me_cntro_cost_mayor ON [me_cntro_cost_mayor_cdgo]=me_cntro_cost_mayor.[ccm_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cntro_cost_mayor_base_datos ON me_cntro_cost_mayor.[ccm_cliente_base_datos_cdgo]=me_cntro_cost_mayor_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo] \n" +
                                                                "		LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON [ae_equipo_pertenencia_cdgo]=[ep_cdgo]"
                                                                + " WHERE [me_cdgo]=?");
            query.setString(1, CodigoMvtoEquipo);
            ResultSet resultSet; resultSet= query.executeQuery(); 
            boolean validar= true;
            while(resultSet.next()){ 
                try{
                    if(validar){
                        mvto_listEquipo = new MvtoCarbon_ListadoEquipos();
                        validar=false;
                    }
                    
                    mvto_listEquipo.setCodigo(resultSet.getString(1));
                        MvtoCarbon mvtoCarbon = new MvtoCarbon();
                        mvtoCarbon.setCodigo(resultSet.getString(3));
                        mvtoCarbon.setCentroOperacion(new CentroOperacion(Integer.parseInt(resultSet.getString(5)),resultSet.getString(6),""));
                        mvtoCarbon.setCentroCostoAuxiliar(new CentroCostoAuxiliar(Integer.parseInt(resultSet.getString(8)),new CentroCostoSubCentro(Integer.parseInt(resultSet.getString(10)),resultSet.getString(11),""),resultSet.getString(13),""));

                            CentroCosto mc_CentroCosto = new CentroCosto();
                                mc_CentroCosto.setCodigo(resultSet.getString(15));
                                mc_CentroCosto.setDescripción(resultSet.getString(16));
                                
                                mc_CentroCosto.setClienteBaseDatos(resultSet.getString(194));
                        mvtoCarbon.setCentroCosto(mc_CentroCosto);
                            LaborRealizada mc_LaborRealizada = new LaborRealizada();
                                mc_LaborRealizada.setCodigo(resultSet.getString(18));
                                mc_LaborRealizada.setDescripcion(resultSet.getString(19));
                        mvtoCarbon.setLaborRealizada(mc_LaborRealizada);
                            Articulo mc_Articulo = new Articulo();
                                mc_Articulo.setCodigo(resultSet.getString(21));
                                    TipoArticulo mc_TipoArticulo = new TipoArticulo();
                                    mc_TipoArticulo.setCodigo(resultSet.getString(23));
                                    mc_TipoArticulo.setDescripcion(resultSet.getString(24));
                                    mc_TipoArticulo.setCodigoERP(resultSet.getString(25));
                                    mc_TipoArticulo.setUnidadNegocio(resultSet.getString(26));
                                mc_Articulo.setTipoArticulo(mc_TipoArticulo);
                                mc_Articulo.setDescripcion(resultSet.getString(27));
                                mc_Articulo.setBaseDatos(new BaseDatos(resultSet.getString(195)));
                        mvtoCarbon.setArticulo(mc_Articulo);
                            Cliente mc_cliente = new Cliente();
                            mc_cliente.setCodigo(resultSet.getString(29));
                            mc_cliente.setDescripcion(resultSet.getString(30));
                            mc_cliente.setBaseDatos(new BaseDatos(resultSet.getString(196)));
                        
                        mvtoCarbon.setCliente(mc_cliente);
                            Transportadora mc_trTransportadora = new Transportadora();
                            mc_trTransportadora.setCodigo(resultSet.getString(32));
                            mc_trTransportadora.setNit(resultSet.getString(33));
                            mc_trTransportadora.setDescripcion(resultSet.getString(34));
                            mc_trTransportadora.setBaseDatos(new BaseDatos(resultSet.getString(197)));
                        mvtoCarbon.setTransportadora(mc_trTransportadora);
                        mvtoCarbon.setMes(resultSet.getString(35));
                        mvtoCarbon.setFechaRegistro(resultSet.getString(36));
                        mvtoCarbon.setNumero_orden(resultSet.getString(37));
                        mvtoCarbon.setDeposito(resultSet.getString(38));
                        mvtoCarbon.setConsecutivo(resultSet.getString(39));
                        mvtoCarbon.setPlaca(resultSet.getString(40));
                        mvtoCarbon.setPesoVacio(resultSet.getString(41));
                        mvtoCarbon.setPesoLleno(resultSet.getString(42));
                        mvtoCarbon.setPesoNeto(resultSet.getString(43));
                        mvtoCarbon.setFechaEntradaVehiculo(resultSet.getString(44));
                        mvtoCarbon.setFecha_SalidaVehiculo(resultSet.getString(45));    
                        mvtoCarbon.setFechaInicioDescargue(resultSet.getString(46));    
                        mvtoCarbon.setFechaFinDescargue(resultSet.getString(47));      
                            Usuario mc_usuarioQuienRegistra = new Usuario();           
                            mc_usuarioQuienRegistra.setCodigo(resultSet.getString(49));
                            mc_usuarioQuienRegistra.setNombres(resultSet.getString(50));
                            mc_usuarioQuienRegistra.setApellidos(resultSet.getString(51));
                            mc_usuarioQuienRegistra.setCorreo(resultSet.getString(52));
                        mvtoCarbon.setUsuarioRegistroMovil(mc_usuarioQuienRegistra);
                        mvtoCarbon.setObservacion(resultSet.getString(53));
                            EstadoMvtoCarbon estadMvtoCarbon = new EstadoMvtoCarbon();
                            estadMvtoCarbon.setCodigo(resultSet.getString(55));
                            estadMvtoCarbon.setDescripcion(resultSet.getString(56));
                        mvtoCarbon.setEstadoMvtoCarbon(estadMvtoCarbon);
                        mvtoCarbon.setConexionPesoCcarga(resultSet.getString(57));
                        mvtoCarbon.setRegistroManual(resultSet.getString(58));
                            Usuario mc_usuarioRegManual = new Usuario();           
                            mc_usuarioRegManual.setCodigo(resultSet.getString(60));
                            mc_usuarioRegManual.setNombres(resultSet.getString(61));
                            mc_usuarioRegManual.setApellidos(resultSet.getString(62));
                            mc_usuarioRegManual.setCorreo(resultSet.getString(63));
                        mvtoCarbon.setUsuarioRegistraManual(mc_usuarioRegManual);
                        CentroCostoAuxiliar mc_CentroCostosAuxilarDestino = new CentroCostoAuxiliar();
                        if(resultSet.getString(65) != null){
                            mc_CentroCostosAuxilarDestino.setCodigo(Integer.parseInt(resultSet.getString(65)));
                            mc_CentroCostosAuxilarDestino.setDescripcion(resultSet.getString(66));
                        
                        }else{                         
                            mc_CentroCostosAuxilarDestino.setCodigo(-1);
                            mc_CentroCostosAuxilarDestino.setDescripcion(null);
                        }
                        mvtoCarbon.setCentroCostoAuxiliarDestino(mc_CentroCostosAuxilarDestino); 
                        
                        mvtoCarbon.setCantidadHorasDescargue(resultSet.getString(186));
                            CentroCostoMayor mc_CentroCostoMayor = new CentroCostoMayor();
                            mc_CentroCostoMayor.setCodigo(resultSet.getString(68));
                            mc_CentroCostoMayor.setDescripcion(resultSet.getString(69));  
                            mc_CentroCostoMayor.setClienteBaseDatos(resultSet.getString(198));
                        mvtoCarbon.setCentroCostoMayor(mc_CentroCostoMayor);
                        Equipo equipoTem1 = new Equipo();
                        equipoTem1.setCodigo(resultSet.getString(189));
                        mvtoCarbon.setEquipoLavadoVehiculo(equipoTem1);
                        mvtoCarbon.setLavadoVehiculo(resultSet.getString(190));
                        mvtoCarbon.setCostoLavadoVehiculo(resultSet.getString(191));
                        mvtoCarbon.setValorRecaudoEmpresa_lavadoVehiculo(resultSet.getString(192));
                        mvtoCarbon.setValorRecaudoEquipo_lavadoVehiculo(resultSet.getString(193));
                    mvto_listEquipo.setMvtoCarbon(mvtoCarbon);
                        AsignacionEquipo asignacionEquipo = new AsignacionEquipo();
                        asignacionEquipo.setCodigo(resultSet.getString(74));
                        asignacionEquipo.setFechaRegistro(resultSet.getString(83));
                        asignacionEquipo.setFechaHoraInicio(resultSet.getString(84));
                        asignacionEquipo.setFechaHoraFin(resultSet.getString(85));
                        asignacionEquipo.setCantidadMinutosProgramados(resultSet.getString(86));
                            Equipo equipo = new Equipo(); 
                            equipo.setCodigo(resultSet.getString(76));
                            equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(78),resultSet.getString(79),""));
                            equipo.setMarca(resultSet.getString(80));
                            equipo.setModelo(resultSet.getString(81));
                            equipo.setDescripcion(resultSet.getString(82));
                            equipo.setPertenenciaEquipo(new Pertenencia(resultSet.getString(88),resultSet.getString(89),resultSet.getString(90)));
                        asignacionEquipo.setEquipo(equipo);
                        asignacionEquipo.setPertenencia(new Pertenencia(resultSet.getString(88),resultSet.getString(89),resultSet.getString(90)));
                    mvto_listEquipo.setAsignacionEquipo(asignacionEquipo);
                        MvtoEquipo mvtoEquipo = new MvtoEquipo();
                        mvtoEquipo.setCodigo(resultSet.getString(72));
                        mvtoEquipo.setAsignacionEquipo(asignacionEquipo);
                        mvtoEquipo.setMes(resultSet.getString(91));
                        mvtoEquipo.setFechaRegistro(resultSet.getString(92));
                        mvtoEquipo.setProveedorEquipo(new ProveedorEquipo(resultSet.getString(94),resultSet.getString(95),resultSet.getString(96),""));
                        mvtoEquipo.setNumeroOrden(resultSet.getString(97));
                            CentroOperacion me_co= new CentroOperacion();
                            me_co.setCodigo(Integer.parseInt(resultSet.getString(99)));
                            me_co.setDescripcion(resultSet.getString(100));
                        mvtoEquipo.setCentroOperacion(me_co);
                            CentroCostoSubCentro centroCostoSubCentro_mvtoEquipo = new CentroCostoSubCentro();
                            centroCostoSubCentro_mvtoEquipo.setCodigo(resultSet.getInt(104));
                            centroCostoSubCentro_mvtoEquipo.setDescripcion(resultSet.getString(105));
                            centroCostoSubCentro_mvtoEquipo.setCentroCostoRequiereLaborRealizada(resultSet.getString(107));
                            CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipo = new CentroCostoAuxiliar();
                            centroCostoAuxiliar_mvtoEquipo.setCodigo(resultSet.getInt(102));
                            centroCostoAuxiliar_mvtoEquipo.setDescripcion(resultSet.getString(108));
                            centroCostoAuxiliar_mvtoEquipo.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipo);
                        mvtoEquipo.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipo);
                            CentroCosto centroCosto = new CentroCosto();
                            centroCosto.setCodigo(resultSet.getString(110));
                            centroCosto.setDescripción(resultSet.getString(111));
                            centroCosto.setClienteBaseDatos(resultSet.getString(199));
                        mvtoEquipo.setCentroCosto(centroCosto);
                            LaborRealizada laborRealizadaT = new LaborRealizada();
                            laborRealizadaT.setCodigo(resultSet.getString(113));
                            laborRealizadaT.setDescripcion(resultSet.getString(114));
                            laborRealizadaT.setEs_operativa(resultSet.getString(115));
                            laborRealizadaT.setEs_parada(resultSet.getString(116));
                        mvtoEquipo.setLaborRealizada(laborRealizadaT);
                        Cliente me_cliente = new Cliente();
                        me_cliente.setCodigo(resultSet.getString(118));
                        me_cliente.setDescripcion(resultSet.getString(119));
                        me_cliente.setBaseDatos(new BaseDatos( resultSet.getString(200)));
                        
                        mvtoEquipo.setCliente(me_cliente);
                            TipoArticulo tipoArticulo = new TipoArticulo();
                                    tipoArticulo.setCodigo(resultSet.getString(123));
                                    tipoArticulo.setDescripcion(resultSet.getString(124));
                                    tipoArticulo.setCodigoERP(resultSet.getString(125));
                                    tipoArticulo.setUnidadNegocio(resultSet.getString(126));
                            Articulo articulo = new Articulo();
                            articulo.setCodigo(resultSet.getString(121));
                            articulo.setDescripcion(resultSet.getString(127));
                            articulo.setTipoArticulo(tipoArticulo);
                            articulo.setBaseDatos(new BaseDatos( resultSet.getString(201)));
                        mvtoEquipo.setArticulo(articulo);
                        Motonave motonave = new Motonave();
                        motonave.setCodigo(resultSet.getString(129));
                        motonave.setDescripcion(resultSet.getString(130));
                        motonave.setBaseDatos(new BaseDatos( resultSet.getString(202)));
                    mvtoEquipo.setMotonave(motonave);
                    mvtoEquipo.setFechaHoraInicio(resultSet.getString(131));
                    mvtoEquipo.setFechaHoraFin(resultSet.getString(132));
                    mvtoEquipo.setTotalMinutos(resultSet.getString(133));
                    mvtoEquipo.setValorHora(resultSet.getString(134));
                    mvtoEquipo.setCostoTotal(resultSet.getString(135));
                            Recobro recobro = new Recobro();
                            recobro.setCodigo(resultSet.getString(137));
                            recobro.setDescripcion(resultSet.getString(138));
                    mvtoEquipo.setRecobro(recobro);
                            Usuario usuario_me_registra = new Usuario();
                            usuario_me_registra.setCodigo(resultSet.getString(143));
                            usuario_me_registra.setNombres(resultSet.getString(144));
                            usuario_me_registra.setApellidos(resultSet.getString(145));
                            usuario_me_registra.setCorreo(resultSet.getString(146));
                        mvtoEquipo.setUsuarioQuieRegistra(usuario_me_registra);
                            Usuario usuario_me_autoriza = new Usuario();
                            usuario_me_autoriza.setCodigo(resultSet.getString(148));
                            usuario_me_autoriza.setNombres(resultSet.getString(149));
                            usuario_me_autoriza.setApellidos(resultSet.getString(150));
                            usuario_me_autoriza.setCorreo(resultSet.getString(151));
                        mvtoEquipo.setUsuarioAutorizaRecobro(usuario_me_autoriza);
                            AutorizacionRecobro autorizacionRecobro = new AutorizacionRecobro();
                            autorizacionRecobro.setCodigo(resultSet.getString(153));
                            autorizacionRecobro.setDescripcion(resultSet.getString(154));
                            autorizacionRecobro.setEstado(resultSet.getString(155));
                        mvtoEquipo.setAutorizacionRecobro(autorizacionRecobro);
                        mvtoEquipo.setObservacionAutorizacion(resultSet.getString(156));
                        mvtoEquipo.setInactividad(resultSet.getString(157));
                            CausaInactividad causaInactividad = new CausaInactividad();
                            causaInactividad.setCodigo(resultSet.getString(159));
                            causaInactividad.setDescripcion(resultSet.getString(160));
                            causaInactividad.setEstado(resultSet.getString(161));
                        mvtoEquipo.setCausaInactividad(causaInactividad);
                            Usuario usuario_me_us_inactividad = new Usuario();
                            usuario_me_us_inactividad.setCodigo(resultSet.getString(163));
                            usuario_me_us_inactividad.setNombres(resultSet.getString(164));
                            usuario_me_us_inactividad.setApellidos(resultSet.getString(165));
                            usuario_me_us_inactividad.setCorreo(resultSet.getString(166));
                        mvtoEquipo.setUsuarioInactividad(usuario_me_us_inactividad);
                            MotivoParada motivoParada= new MotivoParada();
                            motivoParada.setCodigo(resultSet.getString(169));
                            motivoParada.setDescripcion(resultSet.getString(170));
                        mvtoEquipo.setMotivoParada(motivoParada);
                        mvtoEquipo.setObservacionMvtoEquipo(resultSet.getString(171));
                        mvtoEquipo.setEstado(resultSet.getString(172));
                        mvtoEquipo.setDesdeCarbon(resultSet.getString(173));
                        mvtoEquipo.setCentroCostoDescripción(resultSet.getString(174));
                        CentroCostoSubCentro centroCostoSubCentro_mvtoEquipoDestino = new CentroCostoSubCentro();
                            centroCostoSubCentro_mvtoEquipoDestino.setCodigo(resultSet.getInt(178));
                            centroCostoSubCentro_mvtoEquipoDestino.setDescripcion(resultSet.getString(179));
                            centroCostoSubCentro_mvtoEquipoDestino.setCentroCostoRequiereLaborRealizada(resultSet.getString(181));
                            CentroCostoAuxiliar centroCostoAuxiliar_mvtoEquipoDestino = new CentroCostoAuxiliar();
                            centroCostoAuxiliar_mvtoEquipoDestino.setCodigo(resultSet.getInt(176));
                            centroCostoAuxiliar_mvtoEquipoDestino.setDescripcion(resultSet.getString(182));
                            centroCostoAuxiliar_mvtoEquipoDestino.setCentroCostoSubCentro(centroCostoSubCentro_mvtoEquipoDestino);
                        mvtoEquipo.setCentroCostoAuxiliarDestino(centroCostoAuxiliar_mvtoEquipoDestino);
                            CentroCostoMayor centroCostoMayor = new CentroCostoMayor();
                            centroCostoMayor.setCodigo(resultSet.getString(184));
                            centroCostoMayor.setDescripcion(resultSet.getString(185));
                            centroCostoMayor.setClienteBaseDatos(resultSet.getString(203));
                        mvtoEquipo.setCentroCostoMayor(centroCostoMayor);    
                        //mvtoEquipo.setTotalMinutos(resultSet.getString(187)); 
                    mvto_listEquipo.setMvtoEquipo(mvtoEquipo);  
                    mvto_listEquipo.setEstado(resultSet.getString(188));
                    /*try{
                        TarifaEquipo tarifa=new ControlDB_Equipo(tipoConexion).buscarTarifaEquipoPorAño(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getCodigo(),mvto_listEquipo.getMvtoEquipo().getFechaHoraInicio());
                        if(tarifa !=null){
                            if(mvto_listEquipo.getMvtoEquipo().getFechaHoraFin() != null){
                                mvto_listEquipo.getMvtoEquipo().setValorHora(tarifa.getValorHoraOperacion());
                                try{
                                    double des=Double.parseDouble(mvto_listEquipo.getMvtoEquipo().getTotalMinutos());
                                    double totalHoras = Double.parseDouble(""+(des/60));
                                    double valorHora =Double.parseDouble(tarifa.getValorHoraOperacion());
                                    //mvto_listEquipo.getMvtoEquipo().setCostoTotal(""+(totalHoras * valorHora ));  
                                    DecimalFormat formato2 = new DecimalFormat("0.00");
                                    mvto_listEquipo.getMvtoEquipo().setCostoTotal(formato2.format((totalHoras * valorHora )));
                                    //mvto_listEquipo.getMvtoEquipo().setCostoTotalRecobroCliente(formato2.format((totalHoras * valorHora )));
                                    mvto_listEquipo.getMvtoEquipo().setCostoTotalRecobroCliente("0");
                                }catch(Exception e){
                                    //mvto_listEquipo.getMvtoEquipo().setCostoTotal("NO PUDE");
                                }
                            }
                        }
                    }catch(Exception e){
                       System.out.println("Error al procesar tarifa");
                    }*/

                      
                }catch(Exception e){
                    e.printStackTrace();
                }
                
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los Movimientos de Carbon");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return mvto_listEquipo;
    }
    public int                                  ModificarEstado_lavadoVehiculoEnMvtoCarbon(MvtoCarbon mvtoCarbon, ArrayList<MvtoCarbon_ListadoEquipos> mvtoCarbon_ListadoEquipos, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        int result=0;
        try{  
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryActualizarMvtoCarbon= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_carbon] SET [mc_lavado_vehiculo]=?,[mc_lavado_vehiculo_observacion]=?  WHERE [mc_cdgo]=?");
            queryActualizarMvtoCarbon.setString(1,mvtoCarbon.getLavadoVehiculo());
            queryActualizarMvtoCarbon.setString(2,mvtoCarbon.getLavadoVehiculo_Observacion());
            queryActualizarMvtoCarbon.setString(3,mvtoCarbon.getCodigo());
            queryActualizarMvtoCarbon.execute();
            result=1;
            if(result==1){
                result=0;
                //Extraemos el nombre del Equipo y la IP        
                String namePc=new ControlDB_Config().getNamePC();
                String ipPc=new ControlDB_Config().getIpPc();
                String macPC=new ControlDB_Config().getMacAddress();
                String valor="";
                if(mvtoCarbon.getLavadoVehiculo().equals("1")){
                    valor="SI";
                }else{
                    valor="NO";
                }
                
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
                        "           ,CONCAT('Se cambio el estado del lavado del vehículo a :"+valor+" en el modulo de carbón de Código:',?,' Consecutivo: ',?,' Placa: ',?,' Razon de modificación: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, mvtoCarbon.getCodigo());
                Query_Auditoria.setString(6, mvtoCarbon.getCodigo());
                Query_Auditoria.setString(7, mvtoCarbon.getConsecutivo());
                Query_Auditoria.setString(8, mvtoCarbon.getPlaca()+" Pesos: Vacio:"+mvtoCarbon.getPesoVacio()+" Lleno: "+mvtoCarbon.getPesoLleno()+" Neto:"+mvtoCarbon.getPesoNeto());
                Query_Auditoria.setString(9,mvtoCarbon.getLavadoVehiculo_Observacion());
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
    public ArrayList<MvtoCarbon>                buscarMvtoCarbonUnicos_Activos(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<MvtoCarbon> listadoObjetos = new ArrayList();
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT	 [mc_cdgo] --1 \n" +
                                                                "        ,[mc_cntro_oper_cdgo] --2 \n" +
                                                                "            --Centro Operacion \n" +
                                                                "            ,[co_cdgo] --3 \n" +
                                                                "            ,[co_desc] --4\n" +
                                                                "            ,CASE [co_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [co_estad] --5\n" +
                                                                "        ,[mc_cntro_cost_auxiliar_cdgo] --6 \n" +
                                                                "            --Centro Costo Auxiliar \n" +
                                                                "            ,mc_cntro_cost_auxiliar.[cca_cdgo] --7\n" +
                                                                "            ,mc_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo] --8 \n" +
                                                                "                    --Subcentro de Costo \n" +
                                                                "                    ,mc_cntro_cost_subcentro.[ccs_cdgo] --9 \n" +
                                                                "                    ,mc_cntro_cost_subcentro.[ccs_desc]  --10 \n" +
                                                                "                    ,CASE mc_cntro_cost_subcentro.[ccs_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN NULL ELSE NULL END AS [ccs_estad] --11 \n" +
                                                                "            ,mc_cntro_cost_auxiliar.[cca_desc] --12 \n" +
                                                                "            ,CASE mc_cntro_cost_auxiliar.[cca_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN NULL ELSE NULL END AS [cca_estad] --13\n" +
                                                                "        ,[mc_articulo_cdgo] --14 \n" +
                                                                "            --Articulo \n" +
                                                                "            ,mc_articulo.[ar_cdgo] --15 \n" +
                                                                "            ,mc_articulo.[ar_desc] --16 \n" +
                                                                "            ,CASE mc_articulo.[ar_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ar_estad] --17 \n" +
                                                                "        ,[mc_cliente_cdgo] --18 \n" +
                                                                "            --Cliente \n" +
                                                                "            ,mc_cliente.[cl_cdgo] --19 \n" +
                                                                "            ,mc_cliente.[cl_desc] --20 \n" +
                                                                "            ,CASE mc_cliente.[cl_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cl_estad] --21 \n" +
                                                                "        ,[mc_transprtdora_cdgo] --22 \n" +
                                                                "            --Transportadora \n" +
                                                                "            ,[tr_cdgo] --23 \n" +
                                                                "            ,[tr_nit] --24\n" +
                                                                "            ,[tr_desc] --25 \n" +
                                                                "            ,[tr_observ] --26 \n" +
                                                                "            ,CASE [tr_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [tr_estad] --27 \n" +
                                                                "        ,[mc_fecha] --28 \n" +
                                                                "        ,[mc_num_orden] --29 \n" +
                                                                "        ,[mc_deposito] --30 \n" +
                                                                "        ,[mc_consecutivo_tqute] --31 \n" +
                                                                "        ,[mc_placa_vehiculo] --32 \n" +
                                                                "        ,[mc_peso_vacio] --33 \n" +
                                                                "        ,[mc_peso_lleno] --34 \n" +
                                                                "        ,[mc_peso_neto] --35 \n" +
                                                                "        ,[mc_fecha_entrad] --36 \n" +
                                                                "        ,[mc_fecha_salid] --37\n" +
                                                                "        ,[mc_fecha_inicio_descargue] --38\n" +
                                                                "        ,[mc_fecha_fin_descargue] --39\n" +
                                                                "        ,[mc_usuario_cdgo] --40\n" +
                                                                "            --Usuario Quien Registra desde App Movil \n" +
                                                                "            ,user_registra.[us_cdgo] --41 \n" +
                                                                "            ,user_registra.[us_clave] --42 \n" +
                                                                "            ,user_registra.[us_nombres] --43 \n" +
                                                                "            ,user_registra.[us_apellidos] --44 \n" +
                                                                "            ,user_registra.[us_perfil_cdgo] --45 \n" +
                                                                "                --Perfil Usuario Quien Registra \n" +
                                                                "                ,prf_registra.[prf_cdgo] --46 \n" +
                                                                "                ,prf_registra.[prf_desc] --47 \n" +
                                                                "                ,CASE prf_registra.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --48 \n" +
                                                                "            ,user_registra.[us_correo] --49 \n" +
                                                                "            ,CASE user_registra.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --50 \n" +
                                                                "        ,[mc_observ] --51\n" +
                                                                "        ,[mc_estad_mvto_carbon_cdgo] --52\n" +
                                                                "            --Estado MvtoCarbon \n" +
                                                                "            ,[emc_cdgo] --53\n" +
                                                                "            ,[emc_desc] --54 \n" +
                                                                "            ,CASE [emc_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [emc_estad] --55 \n" +
                                                                "        ,CASE [mc_conexion_peso_ccarga] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [mc_conexion_peso_ccarga] --56 \n" +
                                                                "        ,[mc_registro_manual] --57\n" +
                                                                "        ,[mc_usuario_registro_manual_cdgo] --58\n" +
                                                                "            --Usuario Quien Registra Manual \n" +
                                                                "            ,user_registro_manual.[us_cdgo] --59 \n" +
                                                                "            ,user_registro_manual.[us_clave] --60 \n" +
                                                                "            ,user_registro_manual.[us_nombres] --61 \n" +
                                                                "            ,user_registro_manual.[us_apellidos] --62 \n" +
                                                                "            ,user_registro_manual.[us_perfil_cdgo] --63 \n" +
                                                                "                --Perfil Usuario Quien Registra Manual \n" +
                                                                "                ,prf_registra_manual.[prf_cdgo] --64\n" +
                                                                "                ,prf_registra_manual.[prf_desc] --65 \n" +
                                                                "                ,CASE prf_registra_manual.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --66 \n" +
                                                                "            ,user_registro_manual.[us_correo] --67 \n" +
                                                                "            ,CASE user_registro_manual.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --68 \n" +
                                                                "           ,Tiempo_Vehiculo_Descargue=(SELECT (DATEDIFF (MINUTE,  [mc_fecha_inicio_descargue], [mc_fecha_fin_descargue])))--69\n"+
                                                                "      ,[mc_costoLavadoVehiculo]--70 \n" +
                                                                "      ,[mc_valorRecaudoEmpresa_lavadoVehiculo]--71 \n" +
                                                                "      ,[mc_valorRecaudoEquipo_lavadoVehiculo]--72 \n" +  
                                                                "      ,mc_articulo_base_datos.[bd_cdgo]   --73 \n" +
                                                                "      ,mc_cliente_base_datos.[bd_cdgo]   --74 \n" +
                                                                "      ,mc_transprtdora_base_datos.[bd_cdgo]   --75 \n" +
                                                                " FROM ["+DB+"].[dbo].[mvto_carbon] \n" +
                                                                "        LEFT JOIN ["+DB+"].[dbo].[cntro_oper] ON [mc_cntro_oper_cdgo]=[co_cdgo] \n" +
                                                                "        LEFT JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] mc_cntro_cost_auxiliar ON [mc_cntro_cost_auxiliar_cdgo]=mc_cntro_cost_auxiliar.[cca_cdgo] \n" +
                                                                "        LEFT JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] mc_cntro_cost_subcentro ON [cca_cntro_cost_subcentro_cdgo]= mc_cntro_cost_subcentro.[ccs_cdgo] \n" +
                                                                 "		LEFT JOIN ["+DB+"].[dbo].[articulo] mc_articulo ON [mc_articulo_cdgo]=mc_articulo.[ar_cdgo] AND mc_articulo.[ar_base_datos_cdgo]=[mc_articulo_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_articulo_base_datos ON mc_articulo.[ar_base_datos_cdgo]=mc_articulo_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[cliente] mc_cliente ON [mc_cliente_cdgo]=mc_cliente.[cl_cdgo] AND mc_cliente.[cl_base_datos_cdgo]=[mc_cliente_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cliente_base_datos ON mc_cliente.[cl_base_datos_cdgo]=mc_cliente_base_datos.[bd_cdgo] \n"+
                                                                "		LEFT JOIN ["+DB+"].[dbo].[transprtdora] ON [mc_transprtdora_cdgo]=[tr_cdgo] AND [tr_base_datos_cdgo]=[mc_transprtdora_base_datos_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_transprtdora_base_datos ON [tr_base_datos_cdgo]=mc_transprtdora_base_datos.[bd_cdgo] \n"+ 
                                                                "        LEFT JOIN ["+DB+"].[dbo].[usuario] user_registra ON [mc_usuario_cdgo] = user_registra.[us_cdgo] \n" +
                                                                "        LEFT JOIN ["+DB+"].[dbo].[perfil] prf_registra ON user_registra.[us_perfil_cdgo]=prf_registra.[prf_cdgo] \n" +
                                                                "        LEFT JOIN ["+DB+"].[dbo].[estad_mvto_carbon] ON [mc_estad_mvto_carbon_cdgo]=[emc_cdgo] \n" +
                                                                "        LEFT JOIN ["+DB+"].[dbo].[usuario] user_registro_manual ON [mc_usuario_registro_manual_cdgo] = user_registro_manual.[us_cdgo] \n" +
                                                                "        LEFT JOIN ["+DB+"].[dbo].[perfil] prf_registra_manual ON user_registro_manual.[us_perfil_cdgo]=prf_registra_manual.[prf_cdgo] \n" +
                                                                " WHERE [mc_fecha] BETWEEN ? AND ?  AND [mc_estad_mvto_carbon_cdgo]=1 ORDER BY [mc_cdgo] DESC");
            query.setString(1, DatetimeInicio);
            query.setString(2, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();              
            while(resultSet.next()){ 
                    MvtoCarbon mvtoCarbon = new MvtoCarbon();
                    mvtoCarbon.setCodigo(resultSet.getString(1));
                    mvtoCarbon.setCentroOperacion(new CentroOperacion(Integer.parseInt(resultSet.getString(3)),resultSet.getString(4),resultSet.getString(5)));
                    mvtoCarbon.setCentroCostoAuxiliar(new CentroCostoAuxiliar(Integer.parseInt(resultSet.getString(7)),new CentroCostoSubCentro(Integer.parseInt(resultSet.getString(9)),resultSet.getString(10),resultSet.getString(11)),resultSet.getString(12),resultSet.getString(13)));
                    mvtoCarbon.setArticulo(new Articulo(resultSet.getString(15),resultSet.getString(16),resultSet.getString(17)));
                    mvtoCarbon.getArticulo().setBaseDatos(new BaseDatos(resultSet.getString(73)));
                    mvtoCarbon.setCliente(new Cliente(resultSet.getString(19),resultSet.getString(20),resultSet.getString(21)));
                    mvtoCarbon.getCliente().setBaseDatos(new BaseDatos(resultSet.getString(74)));
                    mvtoCarbon.setTransportadora(new Transportadora(resultSet.getString(23),resultSet.getString(24),resultSet.getString(25),resultSet.getString(26),resultSet.getString(27)));
                    mvtoCarbon.getTransportadora().setBaseDatos(new BaseDatos(resultSet.getString(75)));
                    mvtoCarbon.setFechaRegistro(resultSet.getString(28));
                    mvtoCarbon.setNumero_orden(resultSet.getString(29));
                    mvtoCarbon.setDeposito(resultSet.getString(30));
                    mvtoCarbon.setConsecutivo(resultSet.getString(31));
                    mvtoCarbon.setPlaca(resultSet.getString(32));
                    mvtoCarbon.setPesoVacio(resultSet.getString(33));
                    mvtoCarbon.setPesoLleno(resultSet.getString(34));
                    mvtoCarbon.setPesoNeto(resultSet.getString(35));
                    mvtoCarbon.setFechaEntradaVehiculo(resultSet.getString(36));
                    mvtoCarbon.setFecha_SalidaVehiculo(resultSet.getString(37));    
                    mvtoCarbon.setFechaInicioDescargue(resultSet.getString(38));    
                    mvtoCarbon.setFechaFinDescargue(resultSet.getString(39));      
                        Usuario us = new Usuario();           
                        us.setCodigo(resultSet.getString(41));
                        //us.setClave(resultSet.getString(42));
                        us.setNombres(resultSet.getString(43));
                        us.setApellidos(resultSet.getString(44));
                        us.setPerfilUsuario(new Perfil(resultSet.getString(46),resultSet.getString(47),resultSet.getString(48)));
                        us.setCorreo(resultSet.getString(49));
                        us.setEstado(resultSet.getString(50));
                    mvtoCarbon.setUsuarioRegistroMovil(us);
                    mvtoCarbon.setObservacion(resultSet.getString(51));
                        EstadoMvtoCarbon estadMvtoCarbon = new EstadoMvtoCarbon();
                        estadMvtoCarbon.setCodigo(resultSet.getString(53));
                        estadMvtoCarbon.setDescripcion(resultSet.getString(54));
                        estadMvtoCarbon.setEstado(resultSet.getString(55));
                    mvtoCarbon.setEstadoMvtoCarbon(estadMvtoCarbon);
                    mvtoCarbon.setConexionPesoCcarga(resultSet.getString(56));
                    mvtoCarbon.setRegistroManual(resultSet.getString(57));
                        Usuario usRegManual = new Usuario();           
                        usRegManual.setCodigo(resultSet.getString(59));
                        //usRegManual.setClave(resultSet.getString(60));
                        usRegManual.setNombres(resultSet.getString(61));
                        usRegManual.setApellidos(resultSet.getString(62));
                        usRegManual.setPerfilUsuario(new Perfil(resultSet.getString(64),resultSet.getString(65),resultSet.getString(66)));
                        usRegManual.setCorreo(resultSet.getString(67));
                        usRegManual.setEstado(resultSet.getString(68));
                    mvtoCarbon.setUsuarioRegistraManual(usRegManual);
                    mvtoCarbon.setCantidadHorasDescargue(resultSet.getString(69));
                    mvtoCarbon.setCostoLavadoVehiculo(resultSet.getString(70));
                    mvtoCarbon.setValorRecaudoEmpresa_lavadoVehiculo(resultSet.getString(71));
                    mvtoCarbon.setValorRecaudoEquipo_lavadoVehiculo(resultSet.getString(72));
                listadoObjetos.add(mvtoCarbon);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los Movimientos de Carbon");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }
    public ArrayList<MvtoCarbon_ListadoEquipos> buscarMvtoCarbon_Codigo(String MtvoCarbon_codigo) throws SQLException{
        ArrayList<MvtoCarbon_ListadoEquipos> listadoObjetos = null;
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
                                                                    "				,mc_articulo.[ar_cdgo] --17\n" +
                                                                    "				,mc_articulo.[ar_desc] --18\n" +
                                                                    "				,CASE mc_articulo.[ar_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ar_estad] --19\n" +
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
                                                                    "					,me_articulo.[ar_cdgo] --224\n" +
                                                                    "					,me_articulo.[ar_desc] --225\n" +
                                                                    "					,CASE me_articulo.[ar_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ar_estad] --226\n" +
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
                                                                    "      ,[mc_costoLavadoVehiculo]--309 \n" +
                                                                    "      ,[mc_valorRecaudoEmpresa_lavadoVehiculo]--310 \n" +
                                                                    "      ,[mc_valorRecaudoEquipo_lavadoVehiculo]--311 \n" +
                                                                    "      ,mc_usuario_cierre.[us_cdgo]--312 \n" +
                                                                    "      ,mc_usuario_cierre.[us_nombres]--313 \n" +
                                                                    "      ,mc_usuario_cierre.[us_apellidos]--314 \n" +
                                                                    "      ,me_usuario_cierre.[us_cdgo]--315 \n" +
                                                                    "      ,me_usuario_cierre.[us_nombres]--316 \n" +
                                                                    "      ,me_usuario_cierre.[us_apellidos]--317 \n" +
                                                                    "      ,[mc_equipo_lavado_cdgo]--318 \n" +
                                                                    "      ,ae_sle_mn_base_datos_cdgo--319 \n" +
                                                                    "      ,me_cliente.[cl_base_datos_cdgo]--320 \n" +
                                                                    "      ,me_articulo.[ar_base_datos_cdgo]--321 \n" +
                                                                    "      ,[mc_articulo_base_datos_cdgo]--322 \n" +
                                                                    "      ,[mc_cliente_base_datos_cdgo]--323 \n" +
                                                                    "      ,[mc_transprtdora_base_datos_cdgo]--324 \n" +
                                                                    "  FROM ["+DB+"].[dbo].[mvto_carbon]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [mc_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] mc_cntro_cost_auxiliar ON [mc_cntro_cost_auxiliar_cdgo]=mc_cntro_cost_auxiliar.[cca_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] mc_cntro_cost_subcentro ON [cca_cntro_cost_subcentro_cdgo]= mc_cntro_cost_subcentro.[ccs_cdgo]\n" +
                                                                    
                                                                    "		LEFT JOIN ["+DB+"].[dbo].[articulo] mc_articulo ON [mc_articulo_cdgo]=mc_articulo.[ar_cdgo] AND mc_articulo.[ar_base_datos_cdgo]=[mc_articulo_base_datos_cdgo]\n" +
                                                                    "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_articulo_base_datos ON mc_articulo.[ar_base_datos_cdgo]=mc_articulo_base_datos.[bd_cdgo] \n"+
                                                                    
                                                                            "		LEFT JOIN ["+DB+"].[dbo].[cliente] mc_cliente ON [mc_cliente_cdgo]=mc_cliente.[cl_cdgo] AND mc_cliente.[cl_base_datos_cdgo]=[mc_cliente_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cliente_base_datos ON mc_cliente.[cl_base_datos_cdgo]=mc_cliente_base_datos.[bd_cdgo] \n"+
                                                                    
                                                                        "		LEFT JOIN ["+DB+"].[dbo].[transprtdora] ON [mc_transprtdora_cdgo]=[tr_cdgo] AND [tr_base_datos_cdgo]=[mc_transprtdora_base_datos_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_transprtdora_base_datos ON [tr_base_datos_cdgo]=mc_transprtdora_base_datos.[bd_cdgo] \n"+  
                                                                    "	INNER JOIN ["+DB+"].[dbo].[usuario] user_registra ON [mc_usuario_cdgo] = user_registra.[us_cdgo]\n" +
                                                                    "	LEFT JOIN ["+DB+"].[dbo].[usuario] mc_usuario_cierre ON mc_usuario_cierre_cdgo=mc_usuario_cierre.[us_cdgo]\n" +
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
                                                                    "					  ,[ae_estad] AS ae_estad\n"+
                                                                    "                                      ,mn_base_datos.[bd_cdgo] AS ae_sle_mn_base_datos_cdgo " +
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
                                                                    "							LEFT  JOIN["+DB+"].[dbo].[motonave] ON [sle_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[sle_motonave_base_datos_cdgo]\n" +
                                                                    "                                                   LEFT JOIN ["+DB+"].[dbo].[base_datos] mn_base_datos ON mn_base_datos_cdgo=mn_base_datos.[bd_cdgo] \n"+  
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
                                                                    
                                                                    "   LEFT JOIN ["+DB+"].[dbo].[cliente] me_cliente ON [me_cliente_cdgo]=me_cliente.[cl_cdgo] AND me_cliente.[cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
                                                                    "   LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON me_cliente.[cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                                                                   
                                                                    "	LEFT JOIN ["+DB+"].[dbo].[articulo] me_articulo ON [me_articulo_cdgo]=me_articulo.[ar_cdgo] AND	me_articulo.[ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
                                                                    "   LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON me_articulo.[ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
                                                                    "	INNER JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo]\n" +
                                                                            
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[cliente_recobro] ON [me_cliente_recobro_cdgo]=[clr_cdgo] \n" +
                                                                   "    LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_recobro_base_datos ON clr_cliente_base_datos_cdgo=me_cliente_recobro_base_datos.[bd_cdgo] \n"+
                                                                            
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[cliente] me_clr_cliente ON [clr_cliente_cdgo]=me_clr_cliente.[cl_cdgo] AND me_clr_cliente.[cl_base_datos_cdgo]=clr_cliente_base_datos_cdgo\n" +
                                                                    "   LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_clr_base_datos ON me_clr_cliente.[cl_base_datos_cdgo]=me_cliente_clr_base_datos.[bd_cdgo] \n"+
                                                                            
                                                                            
                                                                     
                                                                            
                                                                                    
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
                                                                    "   LEFT JOIN ["+DB+"].[dbo].[usuario] me_usuario_cierre ON [me_usuario_cierre_cdgo]=me_usuario_cierre.[us_cdgo]"+
                                                                    "	WHERE [mc_cdgo] = ?  ORDER BY [mcle_mvto_carbon_cdgo] DESC");
            query.setString(1, MtvoCarbon_codigo);
            ResultSet resultSet; resultSet= query.executeQuery();      
            int contador=0;
            while(resultSet.next()){ 
                if(contador == 0){
                    listadoObjetos = new ArrayList();
                    contador++;
                }
                MvtoCarbon_ListadoEquipos mvto_listEquipo = new MvtoCarbon_ListadoEquipos();
                mvto_listEquipo.setCodigo(resultSet.getString(1));
                    MvtoCarbon mvtoCarbon = new MvtoCarbon();
                    mvtoCarbon.setCodigo(resultSet.getString(3));
                    mvtoCarbon.setCentroOperacion(new CentroOperacion(Integer.parseInt(resultSet.getString(5)),resultSet.getString(6),resultSet.getString(7)));
                    mvtoCarbon.setCentroCostoAuxiliar(new CentroCostoAuxiliar(Integer.parseInt(resultSet.getString(9)),new CentroCostoSubCentro(Integer.parseInt(resultSet.getString(11)),resultSet.getString(12),resultSet.getString(13)),resultSet.getString(14),resultSet.getString(15)));
                    mvtoCarbon.setArticulo(new Articulo(resultSet.getString(17),resultSet.getString(18),resultSet.getString(19)));
                    mvtoCarbon.getArticulo().setBaseDatos(new BaseDatos(resultSet.getString(322)));
                    mvtoCarbon.setCliente(new Cliente(resultSet.getString(21),resultSet.getString(22),resultSet.getString(23)));
                     mvtoCarbon.getCliente().setBaseDatos(new BaseDatos(resultSet.getString(323)));
                    mvtoCarbon.setTransportadora(new Transportadora(resultSet.getString(25),resultSet.getString(26),resultSet.getString(27),resultSet.getString(28),resultSet.getString(29)));
                    mvtoCarbon.getTransportadora().setBaseDatos(new BaseDatos(resultSet.getString(324)));
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
                    mvtoCarbon.setCostoLavadoVehiculo(resultSet.getString(309));
                    mvtoCarbon.setValorRecaudoEmpresa_lavadoVehiculo(resultSet.getString(310));
                    mvtoCarbon.setValorRecaudoEquipo_lavadoVehiculo(resultSet.getString(311));
                        Usuario mc_usuarioCierre = new Usuario();   
                        if(resultSet.getString(206) != null){//Usuario de Cierrre diferente de nulo         
                            mc_usuarioCierre.setCodigo(resultSet.getString(312));
                            mc_usuarioCierre.setNombres(resultSet.getString(313));
                            mc_usuarioCierre.setApellidos(resultSet.getString(314));  
                        }else{       
                            mc_usuarioCierre.setCodigo(null);
                            mc_usuarioCierre.setNombres(null);
                            mc_usuarioCierre.setApellidos(null);  
                        }
                    mvtoCarbon.setUsuarioCierraRegistro(mc_usuarioCierre);
                        Equipo equipoLavado = new Equipo();
                        equipoLavado.setCodigo(resultSet.getString(318));
                    mvtoCarbon.setEquipoLavadoVehiculo(equipoLavado);
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
                            motonave.setBaseDatos(new BaseDatos(resultSet.getString(319)));
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
                     mvtoEquipo.getCliente().setBaseDatos(new BaseDatos(resultSet.getString(320)));
                    mvtoEquipo.setArticulo(new Articulo(resultSet.getString(224),resultSet.getString(225),resultSet.getString(226)));
                     mvtoEquipo.getArticulo().setBaseDatos(new BaseDatos(resultSet.getString(321)));
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
                    Usuario me_usuarioCierre = new Usuario();    
                            if(resultSet.getString(209) != null){
                                me_usuarioCierre.setCodigo(resultSet.getString(315));
                                me_usuarioCierre.setNombres(resultSet.getString(316));
                                me_usuarioCierre.setApellidos(resultSet.getString(317));  
                            }else{
                                me_usuarioCierre.setCodigo(null);
                                me_usuarioCierre.setNombres(null);
                                me_usuarioCierre.setApellidos(null);  
                            }
                    mvtoEquipo.setUsuarioQuienCierra(me_usuarioCierre);
                    //mvtoEquipo.setTotalMinutos(resultSet.getString(307)); 
                mvto_listEquipo.setMvtoEquipo(mvtoEquipo);  
                mvto_listEquipo.setEstado(resultSet.getString(306));
                try{
                    TarifaEquipo tarifa=new ControlDB_Equipo(tipoConexion).buscarTarifaEquipoPorAño(mvto_listEquipo.getMvtoEquipo().getAsignacionEquipo().getEquipo().getCodigo(),mvto_listEquipo.getMvtoEquipo().getFechaHoraInicio());
                    if(mvto_listEquipo.getMvtoEquipo().getFechaHoraFin() != null){
                        mvto_listEquipo.getMvtoEquipo().setValorHora(tarifa.getValorHoraOperacion());
                        try{
                            double des=Double.parseDouble(mvto_listEquipo.getMvtoEquipo().getTotalMinutos());
                            double totalHoras = Double.parseDouble(""+(des/60));
                            double valorHora =Double.parseDouble(tarifa.getValorHoraOperacion());
                            //mvto_listEquipo.getMvtoEquipo().setCostoTotal(""+(totalHoras * valorHora ));  
                            DecimalFormat formato2 = new DecimalFormat("0.00");
                            mvto_listEquipo.getMvtoEquipo().setCostoTotal(formato2.format((totalHoras * valorHora )));
                            //mvto_listEquipo.getMvtoEquipo().setCostoTotalRecobroCliente(formato2.format((totalHoras * valorHora )));
                            mvto_listEquipo.getMvtoEquipo().setCostoTotalRecobroCliente("0");
                        }catch(Exception e){
                            //mvto_listEquipo.getMvtoEquipo().setCostoTotal("NO PUDE");
                        }
                    }
                }catch(Exception e){
                   System.out.println("Error al procesar tarifa");
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
    public int                                  inactivarVehiculoEnMvtoCarbon(MvtoCarbon mvtoCarbon, ArrayList<MvtoCarbon_ListadoEquipos> mvtoCarbon_ListadoEquipos, Usuario us, String observacion) throws FileNotFoundException, UnknownHostException, SocketException{
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        int result=0;
        try{  
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            
            
            PreparedStatement queryActualizarMvtoCarbon= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_carbon] SET [mc_estad_mvto_carbon_cdgo]=0 WHERE [mc_cdgo]=?");
            queryActualizarMvtoCarbon.setString(1,mvtoCarbon.getCodigo());
            queryActualizarMvtoCarbon.execute();
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
                        "           ,CONCAT('Se inactivo un vehículo en el modulo de carbón de Código:',?,' Consecutivo: ',?,' Placa: ',?,' Razon de activación: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, mvtoCarbon.getCodigo());
                Query_Auditoria.setString(6, mvtoCarbon.getCodigo());
                Query_Auditoria.setString(7, mvtoCarbon.getConsecutivo());
                Query_Auditoria.setString(8, mvtoCarbon.getPlaca()+" Pesos: Vacio:"+mvtoCarbon.getPesoVacio()+" Lleno: "+mvtoCarbon.getPesoLleno()+" Neto:"+mvtoCarbon.getPesoNeto());
                Query_Auditoria.setString(9,observacion);
                Query_Auditoria.execute();
                result=1;
            }
            if(mvtoCarbon_ListadoEquipos !=null){
                for(MvtoCarbon_ListadoEquipos objeto : mvtoCarbon_ListadoEquipos){
                    PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_equipo] SET [me_inactividad]=1,[me_causa_inactividad_cdgo]=?,[me_usuario_inactividad_cdgo]=?, [me_estado]=0 WHERE [me_cdgo]=?");
                    queryActualizar.setString(1,objeto.getMvtoEquipo().getCausaInactividad().getCodigo());
                    queryActualizar.setString(2,us.getCodigo());
                    queryActualizar.setString(3,objeto.getMvtoEquipo().getCodigo());
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
                        Query_Auditoria.setString(5, objeto.getMvtoEquipo().getCodigo());
                        Query_Auditoria.setString(6, objeto.getMvtoEquipo().getCodigo());
                        Query_Auditoria.setString(7, objeto.getMvtoCarbon().getConsecutivo());
                        Query_Auditoria.setString(8, objeto.getMvtoCarbon().getPlaca()+" Pesos: Vacio:"+objeto.getMvtoCarbon().getPesoVacio()+" Lleno: "+objeto.getMvtoCarbon().getPesoLleno()+" Neto:"+objeto.getMvtoCarbon().getPesoNeto());
                        Query_Auditoria.setString(9,observacion);
                        Query_Auditoria.execute();
                        result=1;
                    } 
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
    public int                                  ActivarVehiculoEnMvtoCarbon(MvtoCarbon mvtoCarbon, ArrayList<MvtoCarbon_ListadoEquipos> mvtoCarbon_ListadoEquipos, Usuario us, String observacion) throws FileNotFoundException, UnknownHostException, SocketException{
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        int result=0;
        try{  
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            
            
            PreparedStatement queryActualizarMvtoCarbon= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_carbon] SET [mc_estad_mvto_carbon_cdgo]=1 WHERE [mc_cdgo]=?");
            queryActualizarMvtoCarbon.setString(1,mvtoCarbon.getCodigo());
            queryActualizarMvtoCarbon.execute();
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
                        "           ,CONCAT('Se activo un vehículo en el modulo de carbón de Código:',?,' Consecutivo: ',?,' Placa: ',?,' Razon de activación: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, mvtoCarbon.getCodigo());
                Query_Auditoria.setString(6, mvtoCarbon.getCodigo());
                Query_Auditoria.setString(7, mvtoCarbon.getConsecutivo());
                Query_Auditoria.setString(8, mvtoCarbon.getPlaca()+" Pesos: Vacio:"+mvtoCarbon.getPesoVacio()+" Lleno: "+mvtoCarbon.getPesoLleno()+" Neto:"+mvtoCarbon.getPesoNeto());
                Query_Auditoria.setString(9,observacion);
                Query_Auditoria.execute();
                result=1;
            }
            if(mvtoCarbon_ListadoEquipos !=null){
                for(MvtoCarbon_ListadoEquipos objeto : mvtoCarbon_ListadoEquipos){
                    PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_equipo] SET [me_inactividad]=0,[me_causa_inactividad_cdgo]=NULL,[me_usuario_inactividad_cdgo]=NULL, [me_estado]=1 WHERE [me_cdgo]=?");
                    queryActualizar.setString(1,objeto.getMvtoEquipo().getCodigo());
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
                        Query_Auditoria.setString(5, objeto.getMvtoEquipo().getCodigo());
                        Query_Auditoria.setString(6, objeto.getMvtoEquipo().getCodigo());
                        Query_Auditoria.setString(7, objeto.getMvtoCarbon().getConsecutivo());
                        Query_Auditoria.setString(8, objeto.getMvtoCarbon().getPlaca()+" Pesos: Vacio:"+objeto.getMvtoCarbon().getPesoVacio()+" Lleno: "+objeto.getMvtoCarbon().getPesoLleno()+" Neto:"+objeto.getMvtoCarbon().getPesoNeto());
                        Query_Auditoria.setString(9,observacion);
                        Query_Auditoria.execute();
                        result=1;
                    } 
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
    public ArrayList<MvtoCarbon_ListadoEquipos> buscarMvtoCarbonInactivos(String DatetimeInicio, String DatetimeFin) throws SQLException{
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
                                                                    "				,mc_articulo.[ar_cdgo] --17\n" +
                                                                    "				,mc_articulo.[ar_desc] --18\n" +
                                                                    "				,CASE mc_articulo.[ar_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ar_estad] --19\n" +
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
                                                                    "					,me_articulo.[ar_cdgo] --224\n" +
                                                                    "					,me_articulo.[ar_desc] --225\n" +
                                                                    "					,CASE me_articulo.[ar_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ar_estad] --226\n" +
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
                                                                    "      ,[mc_costoLavadoVehiculo]--309 \n" +
                                                                    "      ,[mc_valorRecaudoEmpresa_lavadoVehiculo]--310 \n" +
                                                                    "      ,[mc_valorRecaudoEquipo_lavadoVehiculo]--311 \n" +
                                                                    "      ,mc_usuario_cierre.[us_cdgo]--312 \n" +
                                                                    "      ,mc_usuario_cierre.[us_nombres]--313 \n" +
                                                                    "      ,mc_usuario_cierre.[us_apellidos]--314 \n" +
                                                                    "      ,me_usuario_cierre.[us_cdgo]--315 \n" +
                                                                    "      ,me_usuario_cierre.[us_nombres]--316 \n" +
                                                                    "      ,me_usuario_cierre.[us_apellidos]--317 \n" +
                                                                    "      ,[mc_equipo_lavado_cdgo]--318 \n" +
                                                                    "      ,ae_sle_mn_base_datos_cdgo--319 \n" +
                                                                    "      ,me_cliente.[cl_base_datos_cdgo]--320 \n" +
                                                                    "      ,me_articulo.[ar_base_datos_cdgo]--321 \n" +
                                                                    "      ,[mc_articulo_base_datos_cdgo]--322 \n" +
                                                                    "      ,[mc_cliente_base_datos_cdgo]--323 \n" +
                                                                    "      ,[mc_transprtdora_base_datos_cdgo]--324 \n" +
                                                                    "  FROM ["+DB+"].[dbo].[mvto_carbon]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [mc_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] mc_cntro_cost_auxiliar ON [mc_cntro_cost_auxiliar_cdgo]=mc_cntro_cost_auxiliar.[cca_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] mc_cntro_cost_subcentro ON [cca_cntro_cost_subcentro_cdgo]= mc_cntro_cost_subcentro.[ccs_cdgo]\n" +
                                                                    
                                                                    "		LEFT JOIN ["+DB+"].[dbo].[articulo] mc_articulo ON [mc_articulo_cdgo]=mc_articulo.[ar_cdgo] AND mc_articulo.[ar_base_datos_cdgo]=[mc_articulo_base_datos_cdgo]\n" +
                                                                    "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_articulo_base_datos ON mc_articulo.[ar_base_datos_cdgo]=mc_articulo_base_datos.[bd_cdgo] \n"+
                                                                    
                                                                            "		LEFT JOIN ["+DB+"].[dbo].[cliente] mc_cliente ON [mc_cliente_cdgo]=mc_cliente.[cl_cdgo] AND mc_cliente.[cl_base_datos_cdgo]=[mc_cliente_base_datos_cdgo]\n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cliente_base_datos ON mc_cliente.[cl_base_datos_cdgo]=mc_cliente_base_datos.[bd_cdgo] \n"+
                                                                    
                                                                        "		LEFT JOIN ["+DB+"].[dbo].[transprtdora] ON [mc_transprtdora_cdgo]=[tr_cdgo] AND [tr_base_datos_cdgo]=[mc_transprtdora_base_datos_cdgo] \n" +
                                                                "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_transprtdora_base_datos ON [tr_base_datos_cdgo]=mc_transprtdora_base_datos.[bd_cdgo] \n"+  
                                                                    "	INNER JOIN ["+DB+"].[dbo].[usuario] user_registra ON [mc_usuario_cdgo] = user_registra.[us_cdgo]\n" +
                                                                    "	LEFT JOIN ["+DB+"].[dbo].[usuario] mc_usuario_cierre ON mc_usuario_cierre_cdgo=mc_usuario_cierre.[us_cdgo]\n" +
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
                                                                    "					  ,[ae_estad] AS ae_estad\n"+
                                                                    "                                      ,mn_base_datos.[bd_cdgo] AS ae_sle_mn_base_datos_cdgo " +
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
                                                                    "							LEFT  JOIN["+DB+"].[dbo].[motonave] ON [sle_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[sle_motonave_base_datos_cdgo]\n" +
                                                                    "                                                   LEFT JOIN ["+DB+"].[dbo].[base_datos] mn_base_datos ON mn_base_datos_cdgo=mn_base_datos.[bd_cdgo] \n"+  
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
                                                                    
                                                                    "   LEFT JOIN ["+DB+"].[dbo].[cliente] me_cliente ON [me_cliente_cdgo]=me_cliente.[cl_cdgo] AND me_cliente.[cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
                                                                    "   LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON me_cliente.[cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                                                                   
                                                                    "	LEFT JOIN ["+DB+"].[dbo].[articulo] me_articulo ON [me_articulo_cdgo]=me_articulo.[ar_cdgo] AND	me_articulo.[ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
                                                                    "   LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON me_articulo.[ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
                                                                    "	INNER JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo]\n" +
                                                                            
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[cliente_recobro] ON [me_cliente_recobro_cdgo]=[clr_cdgo] \n" +
                                                                   "    LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_recobro_base_datos ON clr_cliente_base_datos_cdgo=me_cliente_recobro_base_datos.[bd_cdgo] \n"+
                                                                            
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[cliente] me_clr_cliente ON [clr_cliente_cdgo]=me_clr_cliente.[cl_cdgo] AND me_clr_cliente.[cl_base_datos_cdgo]=clr_cliente_base_datos_cdgo\n" +
                                                                    "   LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_clr_base_datos ON me_clr_cliente.[cl_base_datos_cdgo]=me_cliente_clr_base_datos.[bd_cdgo] \n"+
                                                                            
                                                                            
                                                                     
                                                                            
                                                                                    
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
                                                                    "   LEFT JOIN ["+DB+"].[dbo].[usuario] me_usuario_cierre ON [me_usuario_cierre_cdgo]=me_usuario_cierre.[us_cdgo]"+   
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
                    mvtoCarbon.getArticulo().setBaseDatos(new BaseDatos(resultSet.getString(322)));
                    mvtoCarbon.setCliente(new Cliente(resultSet.getString(21),resultSet.getString(22),resultSet.getString(23)));
                     mvtoCarbon.getCliente().setBaseDatos(new BaseDatos(resultSet.getString(323)));
                    mvtoCarbon.setTransportadora(new Transportadora(resultSet.getString(25),resultSet.getString(26),resultSet.getString(27),resultSet.getString(28),resultSet.getString(29)));
                    mvtoCarbon.getTransportadora().setBaseDatos(new BaseDatos(resultSet.getString(324)));
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
                    mvtoCarbon.setCostoLavadoVehiculo(resultSet.getString(309));
                    mvtoCarbon.setValorRecaudoEmpresa_lavadoVehiculo(resultSet.getString(310));
                    mvtoCarbon.setValorRecaudoEquipo_lavadoVehiculo(resultSet.getString(311));
                        Usuario mc_usuarioCierre = new Usuario();   
                        if(resultSet.getString(206) != null){//Usuario de Cierrre diferente de nulo         
                            mc_usuarioCierre.setCodigo(resultSet.getString(312));
                            mc_usuarioCierre.setNombres(resultSet.getString(313));
                            mc_usuarioCierre.setApellidos(resultSet.getString(314));  
                        }else{       
                            mc_usuarioCierre.setCodigo(null);
                            mc_usuarioCierre.setNombres(null);
                            mc_usuarioCierre.setApellidos(null);  
                        }
                    mvtoCarbon.setUsuarioCierraRegistro(mc_usuarioCierre);
                        Equipo equipoLavado = new Equipo();
                        equipoLavado.setCodigo(resultSet.getString(318));
                    mvtoCarbon.setEquipoLavadoVehiculo(equipoLavado);
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
                            motonave.setBaseDatos(new BaseDatos(resultSet.getString(319)));
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
                     mvtoEquipo.getCliente().setBaseDatos(new BaseDatos(resultSet.getString(320)));
                    mvtoEquipo.setArticulo(new Articulo(resultSet.getString(224),resultSet.getString(225),resultSet.getString(226)));
                     mvtoEquipo.getArticulo().setBaseDatos(new BaseDatos(resultSet.getString(321)));
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
                    Usuario me_usuarioCierre = new Usuario();    
                            if(resultSet.getString(209) != null){
                                me_usuarioCierre.setCodigo(resultSet.getString(315));
                                me_usuarioCierre.setNombres(resultSet.getString(316));
                                me_usuarioCierre.setApellidos(resultSet.getString(317));  
                            }else{
                                me_usuarioCierre.setCodigo(null);
                                me_usuarioCierre.setNombres(null);
                                me_usuarioCierre.setApellidos(null);  
                            }
                    mvtoEquipo.setUsuarioQuienCierra(me_usuarioCierre);
                    //mvtoEquipo.setTotalMinutos(resultSet.getString(307)); 
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
    public ArrayList<MvtoCarbon>                InformeMvtoCarbon(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<MvtoCarbon> listadoObjetos = new ArrayList();
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            Statement stmt = conexion.createStatement(
                                      ResultSet.TYPE_SCROLL_INSENSITIVE,
                                   
            ResultSet.CONCUR_READ_ONLY);
            
            ResultSet resultSet = stmt.executeQuery( "SELECT  \n" +
                        "	[mcle_cdgo] --1 \n" +
                        "		,[mcle_mvto_carbon_cdgo] --2 \n" +
                        "			,[mc_cdgo] --3 \n" +
                        "			,[mc_cntro_oper_cdgo] --4 \n" +
                        "				--Centro Operacion \n" +
                        "				,[co_cdgo] --5 \n" +
                        "				,[co_desc] --6 \n" +
                        "				,CASE [co_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [co_estad] --7 \n" +
                        "			,[mc_cntro_cost_auxiliar_cdgo] --8 \n" +
                        "				--Centro Costo Auxiliar \n" +
                        "				,mc_cntro_cost_auxiliar.[cca_cdgo] --9 \n" +
                        "				,mc_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo] --10 \n" +
                        "						--Subcentro de Costo \n" +
                        "						,mc_cntro_cost_subcentro.[ccs_cdgo] --11 \n" +
                        "						,mc_cntro_cost_subcentro.[ccs_desc]  --12 \n" +
                        "						,CASE mc_cntro_cost_subcentro.[ccs_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN NULL ELSE NULL END AS [ccs_estad] --13 \n" +
                        "				,mc_cntro_cost_auxiliar.[cca_desc] --14 \n" +
                        "				,CASE mc_cntro_cost_auxiliar.[cca_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN NULL ELSE NULL END AS [cca_estad] --15 \n" +
                        "			,[mc_articulo_cdgo] --16 \n" +
                        "				--Articulo \n" +
                        "				,mc_articulo.[ar_cdgo] --17 \n" +
                        "				,mc_articulo.[ar_desc] --18 \n" +
                        "				,CASE mc_articulo.[ar_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ar_estad] --19 \n" +
                        "			,[mc_cliente_cdgo] --20 \n" +
                        "				--Cliente \n" +
                        "				,mc_cliente.[cl_cdgo] --21 \n" +
                        "				,mc_cliente.[cl_desc] --22 \n" +
                        "				,CASE mc_cliente.[cl_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cl_estad] --23 \n" +
                        "			,[mc_transprtdora_cdgo] --24 \n" +
                        "				--Transportadora \n" +
                        "				,[tr_cdgo] --25 \n" +
                        "				,[tr_nit] --26 \n" +
                        "				,[tr_desc] --27 \n" +
                        "				,[tr_observ] --28 \n" +
                        "				,CASE [tr_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [tr_estad] --29 \n" +
                        "			,[mc_fecha] --30 \n" +
                        "			,[mc_num_orden] --31 \n" +
                        "			,[mc_deposito] --32 \n" +
                        "			,[mc_consecutivo_tqute] --33 \n" +
                        "			,[mc_placa_vehiculo] --34 \n" +
                        "			,[mc_peso_vacio] --35 \n" +
                        "			,[mc_peso_lleno] --36 \n" +
                        "			,[mc_peso_neto] --37 \n" +
                        "			,[mc_fecha_entrad] --38 \n" +
                        "			,[mc_fecha_salid] --39 \n" +
                        "			,[mc_fecha_inicio_descargue] --40 \n" +
                        "			,[mc_fecha_fin_descargue] --41 \n" +
                        "			,[mc_usuario_cdgo] --42 \n" +
                        "				--Usuario Quien Registra desde App Movil \n" +
                        "				,user_registra.[us_cdgo] --43 \n" +
                        "				,user_registra.[us_clave] --44 \n" +
                        "				,user_registra.[us_nombres] --45 \n" +
                        "				,user_registra.[us_apellidos] --46 \n" +
                        "				,user_registra.[us_perfil_cdgo] --47 \n" +
                        "					--Perfil Usuario Quien Registra \n" +
                        "					,prf_registra.[prf_cdgo] --48 \n" +
                        "					,prf_registra.[prf_desc] --49 \n" +
                        "					,CASE prf_registra.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --50 \n" +
                        "				,user_registra.[us_correo] --51 \n" +
                        "				,CASE user_registra.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --52 \n" +
                        "			,[mc_observ] --53 \n" +
                        "			,[mc_estad_mvto_carbon_cdgo] --54 \n" +
                        "				--Estado MvtoCarbon \n" +
                        "				,[emc_cdgo] --55 \n" +
                        "				,[emc_desc] --56 \n" +
                        "				,CASE [emc_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [emc_estad] --57 \n" +
                        "			,CASE [mc_conexion_peso_ccarga] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [mc_conexion_peso_ccarga] --58 \n" +
                        "			,[mc_registro_manual] --59 \n" +
                        "			,[mc_usuario_registro_manual_cdgo] --60 \n" +
                        "				--Usuario Quien Registra Manual \n" +
                        "				,user_registro_manual.[us_cdgo] --61 \n" +
                        "				,user_registro_manual.[us_clave] --62 \n" +
                        "				,user_registro_manual.[us_nombres] --63 \n" +
                        "				,user_registro_manual.[us_apellidos] --64 \n" +
                        "				,user_registro_manual.[us_perfil_cdgo] --65 \n" +
                        "					--Perfil Usuario Quien Registra Manual \n" +
                        "					,prf_registra_manual.[prf_cdgo] --66 \n" +
                        "					,prf_registra_manual.[prf_desc] --67 \n" +
                        "					,CASE prf_registra_manual.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --68 \n" +
                        "				,user_registro_manual.[us_correo] --69 \n" +
                        "				,CASE user_registro_manual.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --70 \n" +
                        "		,[mcle_asignacion_equipo_cdgo] --71 \n" +
                        "				,[ae_cdgo] --72 \n" +
                        "				,ae_cntro_oper_cdgo --73 \n" +
                        "					--Centro Operacion \n" +
                        "					,ae_co_cdgo --74 \n" +
                        "					,ae_co_desc --75 \n" +
                        "					,CASE ae_co_estado WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_co_estado --76 \n" +
                        "				,ae_solicitud_listado_equipo_cdgo --77 \n" +
                        "					-- Solicitud Listado Equipo \n" +
                        "					,ae_sle_cdgo --78 \n" +
                        "					,ae_sle_solicitud_equipo_cdgo --79 \n" +
                        "							--Solicitud Equipo \n" +
                        "							,ae_sle_se_cdgo --80 \n" +
                        "							,ae_sle_se_cntro_oper_cdgo --81 \n" +
                        "								--CentroOperación SolicitudEquipo \n" +
                        "								,ae_sle_se_co_cdgo --82 \n" +
                        "								,ae_sle_se_co_desc --83 \n" +
                        "								,CASE ae_sle_se_co_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_co_estad --84 \n" +
                        "							,ae_sle_se_fecha --85 \n" +
                        "							,ae_sle_se_usuario_realiza_cdgo --86 \n" +
                        "								--Usuario SolicitudEquipo \n" +
                        "								,ae_sle_se_us_registra_cdgo --87 \n" +
                        "								,ae_sle_se_us_registra_clave --88 \n" +
                        "								,ae_sle_se_us_registra_nombres --89 \n" +
                        "								,ae_sle_se_us_registra_apellidos --90 \n" +
                        "								,ae_sle_se_us_registra_perfil_cdgo --91 \n" +
                        "										--Perfil Usuario Quien Registra Manual \n" +
                        "										,ae_sle_se_prf_us_registra_cdgo --92 \n" +
                        "										,ae_sle_se_prf_us_registra_desc --93 \n" +
                        "										,CASE ae_sle_se_prf_us_registra_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_prf_us_registra_estad --94 \n" +
                        "								,ae_sle_se_us_registra_correo --95 \n" +
                        "								,CASE ae_sle_se_us_registra_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_us_registra_estad --96 \n" +
                        "							,ae_sle_se_fecha_registro --97 \n" +
                        "							,ae_sle_se_estado_solicitud_equipo_cdgo --98 \n" +
                        "								--Estado de la solicitud \n" +
                        "								,ae_sle_se_ese_cdgo --99 \n" +
                        "								,ae_sle_se_ese_desc --100 \n" +
                        "								,CASE ae_sle_se_ese_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_ese_estad --101 \n" +
                        "							,ae_sle_se_fecha_confirmacion --102 \n" +
                        "							,ae_se_usuario_confirma_cdgo --103 \n" +
                        "								--Usuario SolicitudEquipo \n" +
                        "								,ae_sle_se_us_confirma_cdgo --104 \n" +
                        "								,ae_sle_se_us_confirma_clave --105 \n" +
                        "								,ae_sle_se_us_confirma_nombres --106 \n" +
                        "								,ae_sle_se_us_confirma_apellidos --107 \n" +
                        "								,ae_sle_se_us_confirma_perfil_cdgo --108 \n" +
                        "										--Perfil Usuario Quien Registra Manual \n" +
                        "										,ae_sle_se_prf_us_confirma_cdgo --109 \n" +
                        "										,ae_sle_se_prf_us_confirma_desc --110 \n" +
                        "										,CASE ae_sle_se_prf_us_confirma_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_prf_us_confirma_estad --111 \n" +
                        "								,ae_sle_se_us_confirma_correo --112 \n" +
                        "								,CASE ae_sle_se_us_confirma_estado WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_us_confirma_estado --113 \n" +
                        "							,ae_sle_se_confirmacion_solicitud_equipo_cdgo --114 \n" +
                        "								--Confirmacion solicitudEquipo \n" +
                        "								,ae_sle_se_cse_cdgo --115 \n" +
                        "								,ae_sle_se_ces_desc --116 \n" +
                        "								,CASE ae_sle_se_ces_estado WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_se_ces_estado --117 \n" +
                        "					,ae_sle_tipo_equipo_cdgo --118 \n" +
                        "						--Tipo de Equipo \n" +
                        "						,ae_sle_te_cdgo --119 \n" +
                        "						,ae_sle_te_desc --120 \n" +
                        "						,CASE ae_sle_te_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_te_estad --121 \n" +
                        "					,ae_sle_marca_equipo --122 \n" +
                        "					,ae_sle_modelo_equipo --123 \n" +
                        "					,ae_sle_cant_equip --124 \n" +
                        "					,ae_sle_observ --125 \n" +
                        "					,ae_sle_fecha_hora_inicio --126 \n" +
                        "					,ae_sle_fecha_hora_fin --127 \n" +
                        "					,ae_sle_cant_minutos --128 \n" +
                        "					,ae_sle_labor_realizada_cdgo --129 \n" +
                        "					-- Labor Realizada \n" +
                        "							,ae_sle_lr_cdgo --130 \n" +
                        "							,ae_sle_lr_desc --131 \n" +
                        "							,CASE ae_sle_lr_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_lr_estad --132 \n" +
                        "					,ae_sle_sle_motonave_cdgo --133 \n" +
                        "					--Motonave \n" +
                        "							,ae_sle_mn_cdgo --134 \n" +
                        "							,ae_sle_mn_desc --135 \n" +
                        "							,CASE ae_sle_mn_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_mn_estad --136 \n" +
                        "					,ae_sle_cntro_cost_auxiliar_cdgo --137 \n" +
                        "						--Centro Costo Auxiliar \n" +
                        "						,ae_sle_cca_cdgo --138 \n" +
                        "						,ae_sle_cca_cntro_cost_subcentro_cdgo --139 \n" +
                        "							-- SubCentro de Costo \n" +
                        "							,ae_sle_ccs_cdgo --140 \n" +
                        "							,ae_sle_ccs_desc --141 \n" +
                        "							,CASE ae_sle_ccs_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_ccs_estad --142 \n" +
                        "						,ae_sle_cca_desc --143 \n" +
                        "						,CASE ae_sle_cca_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_cca_estad --144 \n" +
                        "					,ae_sle_compania_cdgo --145 \n" +
                        "						--Compañia \n" +
                        "						,ae_sle_cp_cdgo --146 \n" +
                        "						,ae_sle_cp_desc --147 \n" +
                        "						,CASE ae_sle_cp_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_sle_cp_estad --148 \n" +
                        "				,ae_fecha_registro --149 \n" +
                        "				,ae_fecha_hora_inicio --150 \n" +
                        "				,ae_fecha_hora_fin --151 \n" +
                        "				,ae_cant_minutos --152 \n" +
                        "				,ae_equipo_cdgo --153 \n" +
                        "					--Equipo \n" +
                        "					,ae_eq_cdgo --154 \n" +
                        "					,ae_eq_tipo_equipo_cdgo --155 \n" +
                        "						--Tipo Equipo \n" +
                        "						,ae_eq_te_cdgo --156 \n" +
                        "						,ae_eq_te_desc --157 \n" +
                        "						,CASE ae_eq_te_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_te_estad --158 \n" +
                        "					,ae_eq_codigo_barra --159 \n" +
                        "					,ae_eq_referencia --160 \n" +
                        "					,ae_eq_producto --161 \n" +
                        "					,ae_eq_capacidad --162 \n" +
                        "					,ae_eq_marca --163 \n" +
                        "					,ae_eq_modelo --164 \n" +
                        "					,ae_eq_serial --165 \n" +
                        "					,ae_eq_desc --166 \n" +
                        "					,ae_eq_clasificador1_cdgo --167 \n" +
                        "						-- Clasificador 1 \n" +
                        "						,ae_eq_ce1_cdgo --168 \n" +
                        "						,ae_eq_ce1_desc --169 \n" +
                        "						,CASE ae_eq_ce1_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_ce1_estad --170 \n" +
                        "					,ae_eq_clasificador2_cdgo --171 \n" +
                        "						-- Clasificador 2 \n" +
                        "						,ae_eq_ce2_cdgo --172 \n" +
                        "						,ae_eq_ce2_desc --173 \n" +
                        "						,CASE ae_eq_ce2_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_ce2_estad --174 \n" +
                        "					,ae_eq_proveedor_equipo_cdgo --175 \n" +
                        "						--Proveedor Equipo \n" +
                        "						,ae_eq_pe_cdgo --176 \n" +
                        "						,ae_eq_pe_nit --177 \n" +
                        "						,ae_eq_pe_desc --178 \n" +
                        "						,CASE ae_eq_pe_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_pe_estad --179 \n" +
                        "					,ae_eq_equipo_pertenencia_cdgo --180 \n" +
                        "						-- Equipo Pertenencia \n" +
                        "						,ae_eq_ep_cdgo --181 \n" +
                        "						,ae_eq_ep_desc --182 \n" +
                        "						,CASE ae_eq_ep_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_ep_estad --183 \n" +
                        "					,ae_eq_eq_observ --184 \n" +
                        "					,CASE ae_eq_eq_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_eq_eq_estad --185 \n" +
                        "					,ae_eq_actvo_fijo_id --186 \n" +
                        "					,ae_eq_actvo_fijo_referencia --187 \n" +
                        "					,ae_eq_actvo_fijo_desc --188 \n" +
                        "				,ae_equipo_pertenencia_cdgo --189 \n" +
                        "				-- Equipo Pertenencia \n" +
                        "						,ae_ep_cdgo --190 \n" +
                        "						,ae_ep_desc --191 \n" +
                        "						,CASE ae_ep_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_ep_estad --192 \n" +
                        "				,ae_cant_minutos_operativo --193 \n" +
                        "				,ae_cant_minutos_parada --194 \n" +
                        "				,ae_cant_minutos_total --195 \n" +
                        "				,CASE ae_estad WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS ae_estad --196 \n" +
                        "		,[mcle_mvto_equipo_cdgo] --197 \n" +
                        "				--Movimiento Equipo \n" +
                        "				,[me_cdgo] --198 \n" +
                        "				,[me_asignacion_equipo_cdgo] --199 \n" +
                        "				,[me_fecha] --200 \n" +
                        "				,[me_proveedor_equipo_cdgo] --201 \n" +
                        "					--Proveedor Equipo \n" +
                        "					,[pe_cdgo] AS me_pe_cdgo --202 \n" +
                        "					,[pe_nit] AS me_pe_nit --203 \n" +
                        "					,[pe_desc] AS me_pe_desc --204 \n" +
                        "					,CASE [pe_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS me_pe_estad --205 \n" +
                        "				,[me_num_orden] --206 \n" +
                        "				,[me_cntro_cost_auxiliar_cdgo] --207 \n" +
                        "					-- Centro Costo Auxiliar \n" +
                        "					,me_cntro_cost_auxiliar.[cca_cdgo] AS me_cca_cdgo --208 \n" +
                        "					,me_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo] AS me_cca_cntro_cost_subcentro_cdgo --209 \n" +
                        "						--Centro Costo Subcentro \n" +
                        "						,me_cntro_cost_subcentro.[ccs_cdgo] --210 \n" +
                        "						,me_cntro_cost_subcentro.[ccs_desc] --211 \n" +
                        "						,CASE me_cntro_cost_subcentro.[ccs_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ccs_estad] --212 \n" +
                        "					,me_cntro_cost_auxiliar.[cca_desc] AS me_cca_desc --213 \n" +
                        "					,CASE me_cntro_cost_auxiliar.[cca_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cca_estad] --214 \n" +
                        "				,[me_labor_realizada_cdgo] --215 \n" +
                        "					--Labor Realizada \n" +
                        "					,me_labor_realizada.[lr_cdgo]  --216 \n" +
                        "					,me_labor_realizada.[lr_desc] --217 \n" +
                        "					,CASE me_labor_realizada.[lr_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_lr_estad] --218 \n" +
                        "				,[me_cliente_cdgo] --219 \n" +
                        "					--Cliente  \n" +
                        "					,me_cliente.[cl_cdgo] --220 \n" +
                        "					,me_cliente.[cl_desc] --221 \n" +
                        "					,CASE me_cliente.[cl_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cl_estad] --222 \n" +
                        "				,[me_articulo_cdgo] --223 \n" +
                        "					--Producto \n" +
                        "					,me_articulo.[ar_cdgo] --224 \n" +
                        "					,me_articulo.[ar_desc] --225 \n" +
                        "					,CASE me_articulo.[ar_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ar_estad] --226 \n" +
                        "				,[me_fecha_hora_inicio] --227 \n" +
                        "				,[me_fecha_hora_fin] --228 \n" +
                        "				,[me_total_minutos] --229 \n" +
                        "				,[me_valor_hora] --230 \n" +
                        "				,[me_costo_total] --231 \n" +
                        "				,[me_recobro_cdgo] --232 \n" +
                        "					--Recobro \n" +
                        "					,[rc_cdgo] --233 \n" +
                        "					,[rc_desc] --234 \n" +
                        "					,CASE [rc_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [rc_estad] --235 \n" +
                        "				,[me_cliente_recobro_cdgo] --236 \n" +
                        "					--Cliente Recobro \n" +
                        "					,[clr_cdgo] --237 \n" +
                        "					,[clr_cliente_cdgo] --238 \n" +
                        "						--Cliente \n" +
                        "						,me_clr_cliente.[cl_cdgo] --239 \n" +
                        "						,me_clr_cliente.[cl_desc] --240 \n" +
                        "						,CASE me_clr_cliente.[cl_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [cl_estad] --241 \n" +
                        "					,[clr_usuario_cdgo] --242 \n" +
                        "						--Usuario Quien Registra Recobro \n" +
                        "						,me_clr_usuario.[us_cdgo] --243 \n" +
                        "						,me_clr_usuario.[us_clave] --244 \n" +
                        "						,me_clr_usuario.[us_nombres] --245 \n" +
                        "						,me_clr_usuario.[us_apellidos] --246 \n" +
                        "						,me_clr_usuario.[us_perfil_cdgo] --247 \n" +
                        "							--Perfil Usuario Registra recobro \n" +
                        "							,me_clr_us_perfil.[prf_cdgo] --248 \n" +
                        "							,me_clr_us_perfil.[prf_desc] --249 \n" +
                        "							,CASE me_clr_us_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --250 \n" +
                        "						,me_clr_usuario.[us_correo] --251 \n" +
                        "						,CASE me_clr_usuario.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --252 \n" +
                        "					,[clr_valor_recobro] --253 \n" +
                        "					,[clr_fecha_registro] --254 \n" +
                        "				,[me_costo_total_recobro_cliente] --255 \n" +
                        "				,[me_usuario_registro_cdgo] --256 \n" +
                        "					--Usuario Quien Registra Equipo \n" +
                        "					,me_us_registro.[us_cdgo] --257 \n" +
                        "					,me_us_registro.[us_clave] --258 \n" +
                        "					,me_us_registro.[us_nombres] --259 \n" +
                        "					,me_us_registro.[us_apellidos] --260 \n" +
                        "					,me_us_registro.[us_perfil_cdgo] --261 \n" +
                        "						--Perfil de Usuario Quien Registra Equipo \n" +
                        "						,me_us_regist_perfil.[prf_cdgo] --262 \n" +
                        "						,me_us_regist_perfil.[prf_desc] --263 \n" +
                        "						,CASE me_us_regist_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --264 \n" +
                        "					,me_us_registro.[us_correo] --265 \n" +
                        "					,CASE me_us_registro.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --266 \n" +
                        "				,[me_usuario_autorizacion_cdgo] --267 \n" +
                        "					,me_us_autorizacion.[us_cdgo] --268 \n" +
                        "					,me_us_autorizacion.[us_clave] --269 \n" +
                        "					,me_us_autorizacion.[us_nombres] --270 \n" +
                        "					,me_us_autorizacion.[us_apellidos] --271 \n" +
                        "					,me_us_autorizacion.[us_perfil_cdgo] --272 \n" +
                        "						,me_us_autoriza_perfil.[prf_cdgo] --273 \n" +
                        "						,me_us_autoriza_perfil.[prf_desc] --274 \n" +
                        "						,CASE me_us_autoriza_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --275 \n" +
                        "					,me_us_autorizacion.[us_correo] --276 \n" +
                        "					,CASE me_us_autorizacion.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad] --277 \n" +
                        "				,[me_autorizacion_recobro_cdgo] --278 \n" +
                        "					,[are_cdgo] --279 \n" +
                        "					,[are_desc] --280 \n" +
                        "					,CASE [are_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [are_estad] --281 \n" +
                        "				,[me_observ_autorizacion] --282 \n" +
                        "				,[me_inactividad] --283 \n" +
                        "				,[me_causa_inactividad_cdgo] --284 \n" +
                        "					,[ci_cdgo] --285 \n" +
                        "					,[ci_desc] --286 \n" +
                        "					,CASE [ci_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [ci_estad] --287 \n" +
                        "				,[me_usuario_inactividad_cdgo] --288 \n" +
                        "					,me_us_inactividad.[us_cdgo] --289 \n" +
                        "					,me_us_inactividad.[us_clave] --290 \n" +
                        "					,me_us_inactividad.[us_nombres] --291 \n" +
                        "					,me_us_inactividad.[us_apellidos] --292 \n" +
                        "					,me_us_inactividad.[us_perfil_cdgo] --293 \n" +
                        "						,me_us_inactvdad_perfil.[prf_cdgo] --294 \n" +
                        "						,me_us_inactvdad_perfil.[prf_desc] --295 \n" +
                        "						,CASE me_us_inactvdad_perfil.[prf_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [prf_estad] --296 \n" +
                        "					,me_us_inactividad.[us_correo] --297 \n" +
                        "					,CASE me_us_inactividad.[us_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [us_estad]	 --298 \n" +
                        "							,[me_motivo_parada_cdgo]--299 \n" +
                        "										,[mpa_cdgo]--300 \n" +
                        "										,[mpa_desc]--301 \n" +
                        "										,[mpa_estad]--302 \n" +
                        "							,[me_observ] --303 \n" +
                        "				,CASE [me_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [me_estado]	 --304 \n" +
                        "				,CASE [me_desde_mvto_carbon] WHEN 1 THEN 'SI' WHEN 0 THEN 'NO' ELSE NULL END AS [me_desde_mvto_carbon]	 --305 \n" +
                        "		,CASE [mcle_estado] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [mcle_estado]	 --306 \n" +
                        "			,Tiempo_Vehiculo_Descargue=(SELECT (DATEDIFF (MINUTE,  [mc_fecha_inicio_descargue], [mc_fecha_fin_descargue])))--307 \n" +
                        "			,Tiempo_Equipo_Descargue=(SELECT (DATEDIFF (MINUTE,  [me_fecha_hora_inicio], [me_fecha_hora_fin]))) --308 \n" +
                                    "\t         ,[cc_cdgo] --309\n" +
                                        "      ,[cc_cntro_oper_cdgo] --310\n" +
                                        "      ,[cc_cntro_cost_auxiliar_cdgo] --311\n" +
                                        "      ,[cc_cliente_cdgo]--312\n" +
                                        "      ,[cc_descripcion]--313\n" +
                                        "      ,[cc_estad]--314\n "+
                                        "      ,[tar_cdgo] --315\n" +
                                        "      ,[tar_desc]--316\n" +
                                        "      ,[tar_cdgo_erp]--317\n" +
                                        "      ,[tar_undad_ngcio]--318\n" +
                                        "      ,[tar_estado]--319\n "+
                        "					--Labor Realizada \n" +
                        "					,mc_labor_realizada.[lr_cdgo]  --320 \n" +
                        "					,mc_labor_realizada.[lr_desc] --321 \n" +
                        "					,CASE mc_labor_realizada.[lr_estad] WHEN 1 THEN 'ACTIVO' WHEN 0 THEN 'INACTIVO' ELSE NULL END AS [mc_lr_estad] --322 \n" +
                        ",datename (MONTH ,[mc_fecha]) --323\n"+ 
                        "      ,[mc_costoLavadoVehiculo]--324 \n" +
                        "      ,[mc_valorRecaudoEmpresa_lavadoVehiculo]--325 \n" +
                        "      ,[mc_valorRecaudoEquipo_lavadoVehiculo]--326 \n" +
                                                                    "      ,ae_sle_mn_base_datos_cdgo--327 \n" +
                                                                    "      ,me_cliente.[cl_base_datos_cdgo]--328 \n" +
                                                                    "      ,me_articulo.[ar_base_datos_cdgo]--329 \n" +
                                                                    "      ,[mc_articulo_base_datos_cdgo]--330 \n" +
                                                                    "      ,[mc_cliente_base_datos_cdgo]--331 \n" +
                                                                    "      ,[mc_transprtdora_base_datos_cdgo]--332 \n" +
                        "	FROM ["+DB+"].[dbo].[mvto_carbon] \n" +
                        "	INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [mc_cntro_oper_cdgo]=[co_cdgo] \n" +
                        "	INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] mc_cntro_cost_auxiliar ON [mc_cntro_cost_auxiliar_cdgo]=mc_cntro_cost_auxiliar.[cca_cdgo] \n" +
                        "	INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] mc_cntro_cost_subcentro ON [cca_cntro_cost_subcentro_cdgo]= mc_cntro_cost_subcentro.[ccs_cdgo] \n" +
                        "		LEFT JOIN ["+DB+"].[dbo].[articulo] mc_articulo ON [mc_articulo_cdgo]=mc_articulo.[ar_cdgo] AND mc_articulo.[ar_base_datos_cdgo]=[mc_articulo_base_datos_cdgo]\n" +
                        "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_articulo_base_datos ON mc_articulo.[ar_base_datos_cdgo]=mc_articulo_base_datos.[bd_cdgo] \n"+
                                                                    
                        "		LEFT JOIN ["+DB+"].[dbo].[cliente] mc_cliente ON [mc_cliente_cdgo]=mc_cliente.[cl_cdgo] AND mc_cliente.[cl_base_datos_cdgo]=[mc_cliente_base_datos_cdgo]\n" +
                        "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_cliente_base_datos ON mc_cliente.[cl_base_datos_cdgo]=mc_cliente_base_datos.[bd_cdgo] \n"+
                                                                    
                        "		LEFT JOIN ["+DB+"].[dbo].[transprtdora] ON [mc_transprtdora_cdgo]=[tr_cdgo] AND [tr_base_datos_cdgo]=[mc_transprtdora_base_datos_cdgo] \n" +
                        "               LEFT JOIN ["+DB+"].[dbo].[base_datos] mc_transprtdora_base_datos ON [tr_base_datos_cdgo]=mc_transprtdora_base_datos.[bd_cdgo] \n"+  
                                                                     
                                "	INNER JOIN ["+DB+"].[dbo].[usuario] user_registra ON [mc_usuario_cdgo] = user_registra.[us_cdgo] \n" +
                        "	INNER JOIN ["+DB+"].[dbo].[perfil] prf_registra ON user_registra.[us_perfil_cdgo]=prf_registra.[prf_cdgo] \n" +
                        "	INNER JOIN ["+DB+"].[dbo].[estad_mvto_carbon] ON [mc_estad_mvto_carbon_cdgo]=[emc_cdgo] \n" +
                        "	LEFT JOIN ["+DB+"].[dbo].[usuario] user_registro_manual ON [mc_usuario_registro_manual_cdgo] = user_registro_manual.[us_cdgo] \n" +
                        "	LEFT JOIN ["+DB+"].[dbo].[perfil] prf_registra_manual ON user_registro_manual.[us_perfil_cdgo]=prf_registra_manual.[prf_cdgo] \n" +
                        "	LEFT JOIN ["+DB+"].[dbo].[mvto_carbon_listado_equipo] ON [mcle_mvto_carbon_cdgo]=[mc_cdgo] \n" +
                        "	INNER JOIN (SELECT [ae_cdgo] AS ae_cdgo \n" +
                        "						,[ae_cntro_oper_cdgo] AS ae_cntro_oper_cdgo \n" +
                        "							--Centro Operacion \n" +
                        "							,ae_cntro_oper.[co_cdgo] AS ae_co_cdgo \n" +
                        "							,ae_cntro_oper.[co_desc] AS ae_co_desc \n" +
                        "							,ae_cntro_oper.[co_estad] AS ae_co_estado \n" +
                        "						,[ae_solicitud_listado_equipo_cdgo] AS ae_solicitud_listado_equipo_cdgo \n" +
                        "							-- Solicitud Listado Equipo \n" +
                        "							,[sle_cdgo] AS ae_sle_cdgo \n" +
                        "							,[sle_solicitud_equipo_cdgo] AS ae_sle_solicitud_equipo_cdgo \n" +
                        "									--Solicitud Equipo \n" +
                        "									,[se_cdgo] AS ae_sle_se_cdgo \n" +
                        "									,[se_cntro_oper_cdgo] AS ae_sle_se_cntro_oper_cdgo \n" +
                        "										--CentroOperación SolicitudEquipo \n" +
                        "										,se_cntro_oper.[co_cdgo] AS ae_sle_se_co_cdgo \n" +
                        "										,se_cntro_oper.[co_desc] AS ae_sle_se_co_desc \n" +
                        "										,se_cntro_oper.[co_estad] AS ae_sle_se_co_estad \n" +
                        "									,[se_fecha] AS ae_sle_se_fecha \n" +
                        "									,[se_usuario_realiza_cdgo] AS ae_sle_se_usuario_realiza_cdgo \n" +
                        "										--Usuario SolicitudEquipo \n" +
                        "										,se_usuario_realiza.[us_cdgo] AS ae_sle_se_us_registra_cdgo \n" +
                        "										,se_usuario_realiza.[us_clave] AS ae_sle_se_us_registra_clave \n" +
                        "										,se_usuario_realiza.[us_nombres] AS ae_sle_se_us_registra_nombres \n" +
                        "										,se_usuario_realiza.[us_apellidos] AS ae_sle_se_us_registra_apellidos \n" +
                        "										,se_usuario_realiza.[us_perfil_cdgo] AS ae_sle_se_us_registra_perfil_cdgo \n" +
                        "											--Perfil Usuario Quien Registra Manual \n" +
                        "											,ae_prf_registra.[prf_cdgo] AS ae_sle_se_prf_us_registra_cdgo \n" +
                        "											,ae_prf_registra.[prf_desc] AS ae_sle_se_prf_us_registra_desc \n" +
                        "											,ae_prf_registra.[prf_estad] AS ae_sle_se_prf_us_registra_estad \n" +
                        "										,se_usuario_realiza.[us_correo] AS ae_sle_se_us_registra_correo \n" +
                        "										,se_usuario_realiza.[us_estad] AS ae_sle_se_us_registra_estad \n" +
                        "									,[se_fecha_registro] AS ae_sle_se_fecha_registro \n" +
                        "									,[se_estado_solicitud_equipo_cdgo] AS ae_sle_se_estado_solicitud_equipo_cdgo \n" +
                        "										--Estado de la solicitud \n" +
                        "										,[ese_cdgo] AS ae_sle_se_ese_cdgo \n" +
                        "										,[ese_desc] AS ae_sle_se_ese_desc \n" +
                        "										,[ese_estad] AS ae_sle_se_ese_estad \n" +
                        "									,[se_fecha_confirmacion] AS ae_sle_se_fecha_confirmacion \n" +
                        "									,[se_usuario_confirma_cdgo] AS ae_se_usuario_confirma_cdgo \n" +
                        "										--Usuario SolicitudEquipo \n" +
                        "										,se_usuario_confirma.[us_cdgo] AS ae_sle_se_us_confirma_cdgo \n" +
                        "										,se_usuario_confirma.[us_clave] AS ae_sle_se_us_confirma_clave \n" +
                        "										,se_usuario_confirma.[us_nombres] AS ae_sle_se_us_confirma_nombres \n" +
                        "										,se_usuario_confirma.[us_apellidos] AS ae_sle_se_us_confirma_apellidos \n" +
                        "										,se_usuario_confirma.[us_perfil_cdgo] AS ae_sle_se_us_confirma_perfil_cdgo \n" +
                        "											--Perfil Usuario Quien Registra Manual \n" +
                        "											,ae_prf_registra_confirma.[prf_cdgo] AS ae_sle_se_prf_us_confirma_cdgo \n" +
                        "											,ae_prf_registra_confirma.[prf_desc] AS ae_sle_se_prf_us_confirma_desc \n" +
                        "											,ae_prf_registra_confirma.[prf_estad] AS ae_sle_se_prf_us_confirma_estad \n" +
                        "										,se_usuario_confirma.[us_correo] AS ae_sle_se_us_confirma_correo \n" +
                        "										,se_usuario_confirma.[us_estad] AS ae_sle_se_us_confirma_estado \n" +
                        "									,[se_confirmacion_solicitud_equipo_cdgo] AS ae_sle_se_confirmacion_solicitud_equipo_cdgo \n" +
                        "										--Confirmacion solicitudEquipo \n" +
                        "										,[cse_cdgo] AS ae_sle_se_cse_cdgo \n" +
                        "										,[cse_desc] AS ae_sle_se_ces_desc \n" +
                        "										,[cse_estad] AS ae_sle_se_ces_estado \n" +
                        "							,[sle_tipo_equipo_cdgo] AS ae_sle_tipo_equipo_cdgo \n" +
                        "								--Tipo de Equipo \n" +
                        "								,sle_tipoEquipo.[te_cdgo] AS ae_sle_te_cdgo \n" +
                        "								,sle_tipoEquipo.[te_desc] AS ae_sle_te_desc \n" +
                        "								,sle_tipoEquipo.[te_estad] AS ae_sle_te_estad \n" +
                        "							,[sle_marca_equipo] AS ae_sle_marca_equipo \n" +
                        "							,[sle_modelo_equipo] AS ae_sle_modelo_equipo \n" +
                        "							,[sle_cant_equip] AS ae_sle_cant_equip \n" +
                        "							,[sle_observ] AS ae_sle_observ \n" +
                        "							,[sle_fecha_hora_inicio] AS ae_sle_fecha_hora_inicio \n" +
                        "							,[sle_fecha_hora_fin] AS ae_sle_fecha_hora_fin \n" +
                        "							,[sle_cant_minutos] AS ae_sle_cant_minutos \n" +
                        "							,[sle_labor_realizada_cdgo] AS ae_sle_labor_realizada_cdgo \n" +
                        "							-- Labor Realizada \n" +
                        "									,[lr_cdgo] AS ae_sle_lr_cdgo \n" +
                        "									,[lr_desc] AS ae_sle_lr_desc \n" +
                        "									,[lr_estad] AS ae_sle_lr_estad \n" +
                        "							,[sle_motonave_cdgo] AS ae_sle_sle_motonave_cdgo \n" +
                        "							--Motonave \n" +
                        "									,[mn_cdgo] AS ae_sle_mn_cdgo \n" +
                        "									,[mn_desc] AS ae_sle_mn_desc \n" +
                        "									,[mn_estad] AS ae_sle_mn_estad \n" +
                        "							,[sle_cntro_cost_auxiliar_cdgo] AS ae_sle_cntro_cost_auxiliar_cdgo \n" +
                        "								--Centro Costo Auxiliar \n" +
                        "								,[cca_cdgo] AS ae_sle_cca_cdgo \n" +
                        "								,[cca_cntro_cost_subcentro_cdgo] AS ae_sle_cca_cntro_cost_subcentro_cdgo \n" +
                        "									-- SubCentro de Costo \n" +
                        "									,[ccs_cdgo] AS ae_sle_ccs_cdgo \n" +
                        "									,[ccs_desc] AS ae_sle_ccs_desc \n" +
                        "									,[ccs_estad] AS ae_sle_ccs_estad \n" +
                        "								,[cca_desc] AS ae_sle_cca_desc \n" +
                        "								,[cca_estad] AS ae_sle_cca_estad \n" +
                        "							,[sle_compania_cdgo] AS ae_sle_compania_cdgo \n" +
                        "								--Compañia \n" +
                        "								,[cp_cdgo] AS ae_sle_cp_cdgo \n" +
                        "								,[cp_desc] AS ae_sle_cp_desc \n" +
                        "								,[cp_estad] AS ae_sle_cp_estad \n" +
                        "						,[ae_fecha_registro] AS ae_fecha_registro \n" +
                        "						,[ae_fecha_hora_inicio] AS ae_fecha_hora_inicio \n" +
                        "						,[ae_fecha_hora_fin] AS ae_fecha_hora_fin \n" +
                        "						,[ae_cant_minutos] AS ae_cant_minutos \n" +
                        "						,[ae_equipo_cdgo] AS ae_equipo_cdgo \n" +
                        "							--Equipo \n" +
                        "							,[eq_cdgo] AS ae_eq_cdgo \n" +
                        "							,[eq_tipo_equipo_cdgo] AS ae_eq_tipo_equipo_cdgo \n" +
                        "								--Tipo Equipo \n" +
                        "								,eq_tipo_equipo.[te_cdgo] AS ae_eq_te_cdgo \n" +
                        "								,eq_tipo_equipo.[te_desc] AS ae_eq_te_desc \n" +
                        "								,eq_tipo_equipo.[te_estad] AS ae_eq_te_estad \n" +
                        "							,[eq_codigo_barra] AS ae_eq_codigo_barra \n" +
                        "							,[eq_referencia] AS ae_eq_referencia \n" +
                        "							,[eq_producto] AS ae_eq_producto \n" +
                        "							,[eq_capacidad] AS ae_eq_capacidad \n" +
                        "							,[eq_marca] AS ae_eq_marca \n" +
                        "							,[eq_modelo] AS ae_eq_modelo \n" +
                        "							,[eq_serial] AS ae_eq_serial \n" +
                        "							,[eq_desc] AS ae_eq_desc \n" +
                        "							,[eq_clasificador1_cdgo] AS ae_eq_clasificador1_cdgo \n" +
                        "								-- Clasificador 1 \n" +
                        "								,eq_clasificador1.[ce_cdgo] AS ae_eq_ce1_cdgo \n" +
                        "								,eq_clasificador1.[ce_desc] AS ae_eq_ce1_desc \n" +
                        "								,eq_clasificador1.[ce_estad] AS ae_eq_ce1_estad \n" +
                        "							,[eq_clasificador2_cdgo] AS ae_eq_clasificador2_cdgo \n" +
                        "								-- Clasificador 2 \n" +
                        "								,eq_clasificador2.[ce_cdgo] AS ae_eq_ce2_cdgo \n" +
                        "								,eq_clasificador2.[ce_desc] AS ae_eq_ce2_desc \n" +
                        "								,eq_clasificador2.[ce_estad] AS ae_eq_ce2_estad \n" +
                        "							,[eq_proveedor_equipo_cdgo] AS ae_eq_proveedor_equipo_cdgo \n" +
                        "								--Proveedor Equipo \n" +
                        "								,[pe_cdgo] AS ae_eq_pe_cdgo \n" +
                        "								,[pe_nit] AS ae_eq_pe_nit \n" +
                        "								,[pe_desc] AS ae_eq_pe_desc \n" +
                        "								,[pe_estad] AS ae_eq_pe_estad \n" +
                        "							,[eq_equipo_pertenencia_cdgo] AS ae_eq_equipo_pertenencia_cdgo \n" +
                        "								-- Equipo Pertenencia \n" +
                        "								,eq_pertenencia.[ep_cdgo] AS ae_eq_ep_cdgo \n" +
                        "								,eq_pertenencia.[ep_desc] AS ae_eq_ep_desc \n" +
                        "								,eq_pertenencia.[ep_estad] AS ae_eq_ep_estad \n" +
                        "							,[eq_observ] AS ae_eq_eq_observ \n" +
                        "							,[eq_estad] AS ae_eq_eq_estad \n" +
                        "							,[eq_actvo_fijo_id] AS ae_eq_actvo_fijo_id \n" +
                        "							,[eq_actvo_fijo_referencia] AS ae_eq_actvo_fijo_referencia \n" +
                        "							,[eq_actvo_fijo_desc] AS ae_eq_actvo_fijo_desc \n" +
                        "						,[ae_equipo_pertenencia_cdgo] AS ae_equipo_pertenencia_cdgo \n" +
                        "						-- Equipo Pertenencia \n" +
                        "								,ae_pertenencia.[ep_cdgo] AS ae_ep_cdgo \n" +
                        "								,ae_pertenencia.[ep_desc] AS ae_ep_desc \n" +
                        "								,ae_pertenencia.[ep_estad]	 AS ae_ep_estad \n" +
                        "						,[ae_cant_minutos_operativo] AS ae_cant_minutos_operativo \n" +
                        "						,[ae_cant_minutos_parada] AS ae_cant_minutos_parada \n" +
                        "						,[ae_cant_minutos_total] AS ae_cant_minutos_total \n" +
                        "						,[ae_estad] AS ae_estad \n" +
                                 "                                      ,mn_base_datos.[bd_cdgo] AS ae_sle_mn_base_datos_cdgo " +
                        "						FROM ["+DB+"].[dbo].[asignacion_equipo] \n" +
                        "							INNER JOIN ["+DB+"].[dbo].[solicitud_listado_equipo] ON [ae_solicitud_listado_equipo_cdgo]=[sle_cdgo] \n" +
                        "							INNER JOIN ["+DB+"].[dbo].[cntro_oper] ae_cntro_oper ON [ae_cntro_oper_cdgo]=ae_cntro_oper.[co_cdgo] \n" +
                        "							INNER JOIN ["+DB+"].[dbo].[solicitud_equipo] ON [sle_solicitud_equipo_cdgo]=[se_cdgo] \n" +
                        "							INNER JOIN ["+DB+"].[dbo].[cntro_oper] se_cntro_oper ON [se_cntro_oper_cdgo]=se_cntro_oper.[co_cdgo] \n" +
                        "							INNER JOIN ["+DB+"].[dbo].[usuario] se_usuario_realiza ON [se_usuario_realiza_cdgo]=se_usuario_realiza.[us_cdgo] \n" +
                        "							INNER JOIN ["+DB+"].[dbo].[perfil] ae_prf_registra ON se_usuario_realiza.[us_perfil_cdgo]=ae_prf_registra.[prf_cdgo] \n" +
                        "							INNER JOIN ["+DB+"].[dbo].[estado_solicitud_equipo] ON [se_estado_solicitud_equipo_cdgo]=[ese_cdgo] \n" +
                        "							LEFT JOIN  ["+DB+"].[dbo].[usuario] se_usuario_confirma ON [se_usuario_realiza_cdgo]=se_usuario_confirma.[us_cdgo] \n" +
                        "							LEFT JOIN ["+DB+"].[dbo].[perfil] ae_prf_registra_confirma ON se_usuario_confirma.[us_perfil_cdgo]=ae_prf_registra_confirma.[prf_cdgo] \n" +
                        "							LEFT JOIN  ["+DB+"].[dbo].[confirmacion_solicitud_equipo] ON [se_confirmacion_solicitud_equipo_cdgo]=[cse_cdgo] \n" +
                        "							INNER JOIN ["+DB+"].[dbo].[tipo_equipo] sle_tipoEquipo ON [sle_tipo_equipo_cdgo]=sle_tipoEquipo.[te_cdgo] \n" +
                        "							INNER JOIN ["+DB+"].[dbo].[labor_realizada] ON [sle_labor_realizada_cdgo]=[lr_cdgo] \n" +
                         "							LEFT  JOIN["+DB+"].[dbo].[motonave] ON [sle_motonave_cdgo]=[mn_cdgo] AND [mn_base_datos_cdgo]=[sle_motonave_base_datos_cdgo]\n" +
                        "                                                   LEFT JOIN ["+DB+"].[dbo].[base_datos] mn_base_datos ON mn_base_datos_cdgo=mn_base_datos.[bd_cdgo] \n"+  
                        "							INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [sle_cntro_cost_auxiliar_cdgo]=[cca_cdgo] \n" +
                        "							INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo] \n" +
                        "							INNER JOIN ["+DB+"].[dbo].[compania] ON [sle_compania_cdgo]=[cp_cdgo] \n" +
                        "							INNER JOIN ["+DB+"].[dbo].[equipo] ON [ae_equipo_cdgo]=[eq_cdgo] \n" +
                        "							INNER JOIN ["+DB+"].[dbo].[tipo_equipo] eq_tipo_equipo ON [eq_tipo_equipo_cdgo]=eq_tipo_equipo.[te_cdgo] \n" +
                        "							LEFT JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador1 ON [eq_clasificador1_cdgo]=eq_clasificador1.[ce_cdgo] \n" +
                        "							LEFT JOIN ["+DB+"].[dbo].[clasificador_equipo] eq_clasificador2 ON [eq_clasificador2_cdgo]=eq_clasificador2.[ce_cdgo] \n" +
                        "							INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [eq_proveedor_equipo_cdgo]=[pe_cdgo] \n" +
                        "							LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] eq_pertenencia ON [eq_equipo_pertenencia_cdgo]=eq_pertenencia.[ep_cdgo] \n" +
                        "							LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ae_pertenencia ON [ae_equipo_pertenencia_cdgo]=ae_pertenencia.[ep_cdgo]	 \n" +
                        "	) asignacion_equipo ON [mcle_asignacion_equipo_cdgo]=[ae_cdgo] \n" +
                        "	--Movimiento Equipo \n" +
                        "	INNER JOIN ["+DB+"].[dbo].[mvto_equipo] ON [mcle_mvto_equipo_cdgo]=[me_cdgo] \n" +
                        "	INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [me_proveedor_equipo_cdgo]=[pe_cdgo] \n" +
                        "	INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] me_cntro_cost_auxiliar ON [me_cntro_cost_auxiliar_cdgo]=me_cntro_cost_auxiliar.[cca_cdgo] \n" +
                        "	INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro]  me_cntro_cost_subcentro ON me_cntro_cost_auxiliar.[cca_cntro_cost_subcentro_cdgo]=me_cntro_cost_subcentro.[ccs_cdgo] \n" +
                        "	LEFT JOIN ["+DB+"].[dbo].[labor_realizada] me_labor_realizada ON [me_labor_realizada_cdgo]=me_labor_realizada.[lr_cdgo]  \n" +
                         "   LEFT JOIN ["+DB+"].[dbo].[cliente] me_cliente ON [me_cliente_cdgo]=me_cliente.[cl_cdgo] AND me_cliente.[cl_base_datos_cdgo]=[me_cliente_base_datos_cdgo]\n" +
                                                                    "   LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_base_datos ON me_cliente.[cl_base_datos_cdgo]=me_cliente_base_datos.[bd_cdgo] \n"+
                                                                   
                                                                    "	LEFT JOIN ["+DB+"].[dbo].[articulo] me_articulo ON [me_articulo_cdgo]=me_articulo.[ar_cdgo] AND	me_articulo.[ar_base_datos_cdgo]=[me_articulo_base_datos_cdgo]\n" +
                                                                    "   LEFT JOIN ["+DB+"].[dbo].[base_datos] me_articulo_base_datos ON me_articulo.[ar_base_datos_cdgo]=me_articulo_base_datos.[bd_cdgo] \n"+
                                                                    "	INNER JOIN ["+DB+"].[dbo].[recobro] ON [me_recobro_cdgo]=[rc_cdgo] \n" +
                        "	LEFT JOIN  ["+DB+"].[dbo].[cliente_recobro] ON [me_cliente_recobro_cdgo]=[clr_cdgo] \n" +
                                                                   "    LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_recobro_base_datos ON clr_cliente_base_datos_cdgo=me_cliente_recobro_base_datos.[bd_cdgo] \n"+
                                                                            
                                                                    "	LEFT JOIN  ["+DB+"].[dbo].[cliente] me_clr_cliente ON [clr_cliente_cdgo]=me_clr_cliente.[cl_cdgo] AND me_clr_cliente.[cl_base_datos_cdgo]=clr_cliente_base_datos_cdgo\n" +
                                                                    "   LEFT JOIN ["+DB+"].[dbo].[base_datos] me_cliente_clr_base_datos ON me_clr_cliente.[cl_base_datos_cdgo]=me_cliente_clr_base_datos.[bd_cdgo] \n"+
                                                                     "	LEFT JOIN  ["+DB+"].[dbo].[usuario] me_clr_usuario ON [clr_usuario_cdgo]=me_clr_usuario.[us_cdgo] \n" +
                        "	LEFT JOIN  ["+DB+"].[dbo].[perfil] me_clr_us_perfil ON me_clr_usuario.[us_perfil_cdgo]=me_clr_us_perfil.[prf_cdgo] \n" +
                        "	LEFT JOIN  ["+DB+"].[dbo].[usuario] me_us_registro ON [me_usuario_registro_cdgo]=me_us_registro.[us_cdgo] \n" +
                        "	LEFT JOIN  ["+DB+"].[dbo].[perfil] me_us_regist_perfil ON me_us_registro.[us_perfil_cdgo]=me_us_regist_perfil.[prf_cdgo] \n" +
                        "	LEFT JOIN  ["+DB+"].[dbo].[usuario] me_us_autorizacion ON [me_usuario_autorizacion_cdgo]=me_us_autorizacion.[us_cdgo] \n" +
                        "	LEFT JOIN  ["+DB+"].[dbo].[perfil] me_us_autoriza_perfil ON me_us_autorizacion.[us_perfil_cdgo]=me_us_autoriza_perfil.[prf_cdgo] \n" +
                        "	LEFT JOIN  ["+DB+"].[dbo].[autorizacion_recobro] ON [me_autorizacion_recobro_cdgo]=[are_cdgo] \n" +
                        "	LEFT JOIN  ["+DB+"].[dbo].[causa_inactividad] ON [me_causa_inactividad_cdgo]=[ci_cdgo] \n" +
                        "	LEFT JOIN  ["+DB+"].[dbo].[usuario] me_us_inactividad ON [me_usuario_inactividad_cdgo]=me_us_inactividad.[us_cdgo] \n" +
                        "	LEFT JOIN  ["+DB+"].[dbo].[perfil] me_us_inactvdad_perfil ON me_us_inactividad.[us_perfil_cdgo]=me_us_inactvdad_perfil.[prf_cdgo] \n" +
                        "	LEFT JOIN  ["+DB+"].[dbo].[motivo_parada] ON [me_motivo_parada_cdgo]= [mpa_cdgo]      \n "+
                        "       LEFT JOIN  ["+DB+"].[dbo].[cntro_cost] ON [me_cntro_cost_cdgo] =[cc_cdgo] \n" +
                        "       LEFT JOIN  ["+DB+"].[dbo].[tipo_articulo] ON mc_articulo.[ar_tipo_articulo_cdgo] =[tar_cdgo] \n" +
                        "	LEFT JOIN ["+DB+"].[dbo].[labor_realizada] mc_labor_realizada ON [mc_labor_realizada_cdgo]=mc_labor_realizada.[lr_cdgo]  \n" +
                     " WHERE [mc_fecha] BETWEEN '"+DatetimeInicio+"' AND '"+DatetimeFin+"' AND [me_inactividad]=0 AND [mc_estad_mvto_carbon_cdgo]=1 ORDER BY [mcle_mvto_carbon_cdgo] DESC");
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
                    mvtoCarbon.getArticulo().setBaseDatos(new BaseDatos(resultSet.getString(300)));
                        TipoArticulo tipoArticulo = new TipoArticulo();
                        tipoArticulo.setCodigo(resultSet.getString(315));
                        tipoArticulo.setDescripcion(resultSet.getString(316));
                        tipoArticulo.setCodigoERP(resultSet.getString(317));
                        tipoArticulo.setUnidadNegocio(resultSet.getString(318));
                        tipoArticulo.setEstado(resultSet.getString(319));
                    mvtoCarbon.getArticulo().setTipoArticulo(tipoArticulo);
                    mvtoCarbon.setCliente(new Cliente(resultSet.getString(21),resultSet.getString(22),resultSet.getString(23)));
                    mvtoCarbon.getCliente().setBaseDatos(new BaseDatos(resultSet.getString(331)));
                    mvtoCarbon.setTransportadora(new Transportadora(resultSet.getString(25),resultSet.getString(26),resultSet.getString(27),resultSet.getString(28),resultSet.getString(29)));
                    mvtoCarbon.getTransportadora().setBaseDatos(new BaseDatos(resultSet.getString(332)));
                    LaborRealizada mc_LaborRealizada = new LaborRealizada();
                    mc_LaborRealizada.setCodigo(resultSet.getString(320));
                    mc_LaborRealizada.setDescripcion(resultSet.getString(321));
                    mc_LaborRealizada.setEstado(resultSet.getString(322));
                    mvtoCarbon.setLaborRealizada(mc_LaborRealizada);         
                    mvtoCarbon.setFechaRegistro(resultSet.getString(30));
                    mvtoCarbon.setMes(resultSet.getString(323));
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
                    mvtoCarbon.setCantidadHorasDescargue(resultSet.getString(307));
                    mvtoCarbon.setCostoLavadoVehiculo(resultSet.getString(324));
                    mvtoCarbon.setValorRecaudoEmpresa_lavadoVehiculo(resultSet.getString(325));
                    mvtoCarbon.setValorRecaudoEquipo_lavadoVehiculo(resultSet.getString(326));
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
                            motonaveP.setBaseDatos(new BaseDatos(resultSet.getString(327)));
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
                   
                            
                        CentroCosto centroCostoMayor = new CentroCosto();
                        centroCostoMayor.setCodigo(resultSet.getString(309));
                        centroCostoMayor.setDescripción(resultSet.getString(313));
                        centroCostoMayor.setEstado(resultSet.getString(314));
                        
                    mvtoEquipoP.setCentroCosto(centroCostoMayor);
                    mvtoEquipoP.setCentroCostoAuxiliar(centroCostoAuxiliar_mvtoEquipoP);
                        LaborRealizada laborRealizadaT = new LaborRealizada();
                        laborRealizadaT.setCodigo(resultSet.getString(216));
                        laborRealizadaT.setDescripcion(resultSet.getString(217));
                        laborRealizadaT.setEstado(resultSet.getString(218));
                    mvtoEquipoP.setLaborRealizada(laborRealizadaT);
                    mvtoEquipoP.setCliente(new Cliente(resultSet.getString(220),resultSet.getString(221),resultSet.getString(222)));
                    mvtoEquipoP.getCliente().setBaseDatos(new BaseDatos(resultSet.getString(328)));
                    mvtoEquipoP.setArticulo(new Articulo(resultSet.getString(224),resultSet.getString(225),resultSet.getString(226)));
                    mvtoEquipoP.getArticulo().setBaseDatos(new BaseDatos(resultSet.getString(329)));
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
                //mvtoEquipoP.setTotalMinutos(resultSet.getString(307)); 
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
                        mvtoEquipo.setArticulo(new Articulo(resultSet.getString(224),resultSet.getString(225),resultSet.getString(226)));
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
                        //mvtoEquipo.setTotalMinutos(resultSet.getString(307)); 
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
    public MvtoCarbon_ListadoEquipos            ProcesarEnCcargaGP(MvtoCarbon_ListadoEquipos Objeto, Usuario us) throws UnknownHostException, SocketException, SQLException{
        Conexion_DB_ccargaGP control = new Conexion_DB_ccargaGP(tipoConexion);
        Conexion_DB_ccargaOPP control_opp = new Conexion_DB_ccargaOPP(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        String DB_opp= control_opp.getBaseDeDatos();
        //MvtoCarbon_ListadoEquipos Objeto=Objeto1;
        
        //cargamos los centro de costos y centro de costos mayores
        Objeto.setMvtoCarbon(ProcesarCentroCostos(Objeto.getMvtoCarbon()));
        
        conexion= control.ConectarBaseDatos();
        //if(Objeto.getMvtoCarbon().getConexionPesoCcarga().equals("NO")){
            try{
                //System.out.println("==="+Objeto.getMvtoCarbon().getEquipoLavadoVehiculo().getCodigo()+"<--->"+Objeto.getAsignacionEquipo().getEquipo().getCodigo()+"====================>");
                System.out.println(""
                        +  "DECLARE @cantidadTiqueteMismaTara INT, @placa VARCHAR(50),@fechaInicioDescargue DATETIME, @pesoEntrada INT,@diferenciaMinutosMinimo INT, @fechaEntrada DATETIME;"
                        + "DECLARE @cantidadTiqueteMismaTaraOPP INT, @placaOPP VARCHAR(50),@fechaInicioDescargueOPP DATETIME, @pesoEntradaOPP INT,@diferenciaMinutosMinimoOPP INT, @fechaEntradaOPP DATETIME;\n" +
                        "	SET @placa='"+Objeto.getMvtoCarbon().getPlaca()+"';\n" +
                        "	SET @fechaInicioDescargue='"+Objeto.getMvtoCarbon().getFechaInicioDescargue()+"';\n" +
                        "	SET @pesoEntrada="+Objeto.getMvtoCarbon().getPesoVacio()+";\n" +
                        "	SET @fechaEntrada='"+Objeto.getMvtoCarbon().getFechaEntradaVehiculo()+"';\n" +
                        "	SET @diferenciaMinutosMinimo= (	SELECT MIN(DATEDIFF (MINUTE,@fechaInicioDescargue , [ti_fcha_slda])) \n" +
                        "									FROM ["+DB+"].[dbo].[tqte] \n" +
                        "									WHERE [ti_plca]=@placa AND [ti_pso_entrda]=@pesoEntrada AND [ti_fcha_entrda] = @fechaEntrada AND @fechaInicioDescargue \n" +
                        "										BETWEEN @fechaInicioDescargue AND [ti_fcha_slda]\n" +
                        "								   );\n" +
                        "	SET @placaOPP='"+Objeto.getMvtoCarbon().getPlaca()+"';\n" +
                        "	SET @fechaInicioDescargueOPP='"+Objeto.getMvtoCarbon().getFechaInicioDescargue()+"';\n" +
                        "	SET @pesoEntradaOPP="+Objeto.getMvtoCarbon().getPesoVacio()+";\n" +
                        "	SET @fechaEntradaOPP='"+Objeto.getMvtoCarbon().getFechaEntradaVehiculo()+"';\n" +
                        "	SET @diferenciaMinutosMinimoOPP= (	SELECT MIN(DATEDIFF (MINUTE,@fechaInicioDescargueOPP , [ti_fcha_slda])) \n" +
                        "									FROM ["+DB_opp+"].[dbo].[tqte] \n" +
                        "									WHERE [ti_plca]=@placaOPP AND [ti_pso_entrda]=@pesoEntradaOPP AND [ti_fcha_entrda] = @fechaEntradaOPP AND @fechaInicioDescargueOPP \n" +
                        "										BETWEEN @fechaInicioDescargueOPP AND [ti_fcha_slda]\n" +
                        "								   );\n" +
                                
                        "SELECT [ti_cnsctvo],[ti_fcha_slda],[ti_pso_slda],[ti_pso_nto],[ti_dpsto]  \n" +
                        "	FROM ["+DB+"].[dbo].[tqte] \n" +
                        "WHERE [ti_plca]=@placa AND [ti_pso_entrda]=@pesoEntrada  AND [ti_fcha_entrda] = @fechaEntrada\n" +
                        "	   AND @fechaInicioDescargue BETWEEN [ti_fcha_entrda] AND [ti_fcha_slda] \n" +
                        "       AND (DATEDIFF (MINUTE, @fechaInicioDescargue , [ti_fcha_slda]))=@diferenciaMinutosMinimo   UNION "+
                        "SELECT [ti_cnsctvo],[ti_fcha_slda],[ti_pso_slda],[ti_pso_nto],[ti_dpsto]  \n" +
                        "	FROM ["+DB_opp+"].[dbo].[tqte] \n" +
                        "WHERE [ti_plca]=@placaOPP AND [ti_pso_entrda]=@pesoEntradaOPP  AND [ti_fcha_entrda] = @fechaEntradaOPP\n" +
                        "	   AND @fechaInicioDescargueOPP BETWEEN [ti_fcha_entrda] AND [ti_fcha_slda] \n" +
                        "       AND (DATEDIFF (MINUTE, @fechaInicioDescargueOPP , [ti_fcha_slda]))=@diferenciaMinutosMinimoOPP ");
                PreparedStatement query= conexion.prepareStatement(""
                        + "DECLARE @cantidadTiqueteMismaTara INT, @placa VARCHAR(50),@fechaInicioDescargue DATETIME, @pesoEntrada INT,@diferenciaMinutosMinimo INT, @fechaEntrada DATETIME;"
                        + "DECLARE @cantidadTiqueteMismaTaraOPP INT, @placaOPP VARCHAR(50),@fechaInicioDescargueOPP DATETIME, @pesoEntradaOPP INT,@diferenciaMinutosMinimoOPP INT, @fechaEntradaOPP DATETIME;\n" +
                        "	SET @placa='"+Objeto.getMvtoCarbon().getPlaca()+"';\n" +
                        "	SET @fechaInicioDescargue='"+Objeto.getMvtoCarbon().getFechaInicioDescargue()+"';\n" +
                        "	SET @pesoEntrada="+Objeto.getMvtoCarbon().getPesoVacio()+";\n" +
                        "	SET @fechaEntrada='"+Objeto.getMvtoCarbon().getFechaEntradaVehiculo()+"';\n" +
                        "	SET @diferenciaMinutosMinimo= (	SELECT MIN(DATEDIFF (MINUTE,@fechaInicioDescargue , [ti_fcha_slda])) \n" +
                        "									FROM ["+DB+"].[dbo].[tqte] \n" +
                        "									WHERE [ti_plca]=@placa AND [ti_pso_entrda]=@pesoEntrada AND [ti_fcha_entrda] = @fechaEntrada AND @fechaInicioDescargue \n" +
                        "										BETWEEN @fechaInicioDescargue AND [ti_fcha_slda]\n" +
                        "								   );\n" +
                        "	SET @placaOPP='"+Objeto.getMvtoCarbon().getPlaca()+"';\n" +
                        "	SET @fechaInicioDescargueOPP='"+Objeto.getMvtoCarbon().getFechaInicioDescargue()+"';\n" +
                        "	SET @pesoEntradaOPP="+Objeto.getMvtoCarbon().getPesoVacio()+";\n" +
                        "	SET @fechaEntradaOPP='"+Objeto.getMvtoCarbon().getFechaEntradaVehiculo()+"';\n" +
                        "	SET @diferenciaMinutosMinimoOPP= (	SELECT MIN(DATEDIFF (MINUTE,@fechaInicioDescargueOPP , [ti_fcha_slda])) \n" +
                        "									FROM ["+DB_opp+"].[dbo].[tqte] \n" +
                        "									WHERE [ti_plca]=@placaOPP AND [ti_pso_entrda]=@pesoEntradaOPP AND [ti_fcha_entrda] = @fechaEntradaOPP AND @fechaInicioDescargueOPP \n" +
                        "										BETWEEN @fechaInicioDescargueOPP AND [ti_fcha_slda]\n" +
                        "								   );\n" +
                                
                        "SELECT [ti_cnsctvo],[ti_fcha_slda],[ti_pso_slda],[ti_pso_nto],[ti_dpsto]  \n" +
                        "	FROM ["+DB+"].[dbo].[tqte] \n" +
                        "WHERE [ti_plca]=@placa AND [ti_pso_entrda]=@pesoEntrada  AND [ti_fcha_entrda] = @fechaEntrada\n" +
                        "	   AND @fechaInicioDescargue BETWEEN [ti_fcha_entrda] AND [ti_fcha_slda] \n" +
                        "       AND (DATEDIFF (MINUTE, @fechaInicioDescargue , [ti_fcha_slda]))=@diferenciaMinutosMinimo   UNION "+
                        "SELECT [ti_cnsctvo],[ti_fcha_slda],[ti_pso_slda],[ti_pso_nto],[ti_dpsto]  \n" +
                        "	FROM ["+DB_opp+"].[dbo].[tqte] \n" +
                        "WHERE [ti_plca]=@placaOPP AND [ti_pso_entrda]=@pesoEntradaOPP  AND [ti_fcha_entrda] = @fechaEntradaOPP\n" +
                        "	   AND @fechaInicioDescargueOPP BETWEEN [ti_fcha_entrda] AND [ti_fcha_slda] \n" +
                        "       AND (DATEDIFF (MINUTE, @fechaInicioDescargueOPP , [ti_fcha_slda]))=@diferenciaMinutosMinimoOPP ");
                ResultSet resultSet= query.executeQuery();
                while(resultSet.next()){ 
                    System.out.println("1");
                    Objeto.getMvtoCarbon().setConsecutivo(resultSet.getString(1));              
                    Objeto.getMvtoCarbon().setFecha_SalidaVehiculo(resultSet.getString(2));
                    Objeto.getMvtoCarbon().setPesoLleno(resultSet.getString(3));              
                    Objeto.getMvtoCarbon().setPesoNeto(resultSet.getString(4));
                    Objeto.getMvtoCarbon().setDeposito(resultSet.getString(5));
                    Objeto.getMvtoCarbon().setConexionPesoCcarga("1");
                    //Se proceso en controlCarga validando informacion con tabla Tiquete
                    try {
                        int n=actualizarDatosMvtoCarbonSinAuditoria(Objeto,us);
                        if(n==1){
                             System.out.println("Actualización Exitosa");
                            if(n==1){
                                n=0;
                                Conexion_DB_costos_vg control2 = new Conexion_DB_costos_vg(tipoConexion);
                                conexion2= control2.ConectarBaseDatos();
                                String DB2=control2.getBaseDeDatos();
                                System.out.println("UPDATE ["+DB2+"].[dbo].[mvto_equipo] set "
                                                                                            + "[me_valor_hora]="+Objeto.getMvtoEquipo().getValorHora()+", "
                                                                                            + "[me_costo_total]="+Objeto.getMvtoEquipo().getCostoTotal()+","
                                                                                            + "[me_costo_total_recobro_cliente]="+Objeto.getMvtoEquipo().getCostoTotalRecobroCliente()+""
                                                                                            + " WHERE [me_cdgo]="+Objeto.getMvtoEquipo().getCodigo()+"");
                                PreparedStatement queryActualizarDatos_Equipos= conexion2.prepareStatement("UPDATE ["+DB2+"].[dbo].[mvto_equipo] set "
                                                                                            + "[me_valor_hora]=?, "
                                                                                            + "[me_costo_total]=?,"
                                                                                            + "[me_costo_total_recobro_cliente]=?"
                                                                                            + " WHERE [me_cdgo]=?");
                                queryActualizarDatos_Equipos.setString(1,Objeto.getMvtoEquipo().getValorHora());
                                queryActualizarDatos_Equipos.setString(2,Objeto.getMvtoEquipo().getCostoTotal());
                                queryActualizarDatos_Equipos.setString(3,Objeto.getMvtoEquipo().getCostoTotalRecobroCliente());
                                queryActualizarDatos_Equipos.setString(4,Objeto.getMvtoEquipo().getCodigo());
                                queryActualizarDatos_Equipos.execute();    
                                control2.cerrarConexionBaseDatos();
                                n=1; 
                            }
                        }else{
                            //System.out.println("Algo malo pasó");
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ControlDB_MvtoCarbon_backup.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(Objeto.getMvtoCarbon().getPlaca()+"-"+Objeto.getMvtoCarbon().getPesoLleno()+"-"+Objeto.getMvtoCarbon().getFecha_SalidaVehiculo()+"-"+Objeto.getMvtoCarbon().getPesoNeto());
                }
            }catch (SQLException sqlException){
                System.out.println("Con Error: "+Objeto.getMvtoCarbon().getPlaca()+"-"+Objeto.getMvtoCarbon().getPesoVacio()+"-"+Objeto.getMvtoCarbon().getFechaEntradaVehiculo());
                JOptionPane.showMessageDialog(null, "Error al Tratar de buscar el tiquete");
                sqlException.printStackTrace();
            } 
            catch(Exception e){
                e.printStackTrace();
            }
        //}else{
        //   System.out.println("No entró");
            
        //    Conexion_DB_costos_vg control2 = new Conexion_DB_costos_vg(tipoConexion);
        //    conexion2= control2.ConectarBaseDatos();
        //    String DB2=control2.getBaseDeDatos();
        //    System.out.println(""+"UPDATE ["+DB2+"].[dbo].[mvto_equipo] set "
         //                                                               + "[me_valor_hora]="+Objeto.getMvtoEquipo().getValorHora()+", "
         //                                                               + "[me_costo_total]="+Objeto.getMvtoEquipo().getCostoTotal()+","
         //                                                               + "[me_costo_total_recobro_cliente]="+Objeto.getMvtoEquipo().getCostoTotalRecobroCliente()+""
         //                                                               + " WHERE [me_cdgo]="+Objeto.getMvtoEquipo().getCodigo()+"");
         //   PreparedStatement queryActualizarDatos_Equipos= conexion2.prepareStatement("UPDATE ["+DB2+"].[dbo].[mvto_equipo] set "
         //                                                               + "[me_valor_hora]=?, "
         //                                                               + "[me_costo_total]=?,"
          //                                                              + "[me_costo_total_recobro_cliente]=?"
         //                                                               + " WHERE [me_cdgo]=?");
        //    queryActualizarDatos_Equipos.setString(1,Objeto.getMvtoEquipo().getValorHora());
        //    queryActualizarDatos_Equipos.setString(2,Objeto.getMvtoEquipo().getCostoTotal());
        //    queryActualizarDatos_Equipos.setString(3,Objeto.getMvtoEquipo().getCostoTotalRecobroCliente());
        //    queryActualizarDatos_Equipos.setString(4,Objeto.getMvtoEquipo().getCodigo());
        //    queryActualizarDatos_Equipos.execute();    
       //     control2.cerrarConexionBaseDatos();
        //}
        control.cerrarConexionBaseDatos();
        return Objeto;
    }
    public MvtoCarbon_ListadoEquipos            ProcesarEnCcargaGPAgregar(MvtoCarbon_ListadoEquipos Objeto, Usuario us) throws UnknownHostException, SocketException, SQLException{
        Conexion_DB_ccargaGP control = new Conexion_DB_ccargaGP(tipoConexion);
        Conexion_DB_ccargaOPP control_opp = new Conexion_DB_ccargaOPP(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        String DB_opp= control_opp.getBaseDeDatos();
        //MvtoCarbon_ListadoEquipos Objeto=Objeto1;
        
        //cargamos los centro de costos y centro de costos mayores
        Objeto.setMvtoCarbon(ProcesarCentroCostos(Objeto.getMvtoCarbon()));
        
        conexion= control.ConectarBaseDatos();
        //if(Objeto.getMvtoCarbon().getConexionPesoCcarga().equals("NO")){
            try{
                //System.out.println("==="+Objeto.getMvtoCarbon().getEquipoLavadoVehiculo().getCodigo()+"<--->"+Objeto.getAsignacionEquipo().getEquipo().getCodigo()+"====================>");
                System.out.println(""
                        +  "DECLARE @cantidadTiqueteMismaTara INT, @placa VARCHAR(50),@fechaInicioDescargue DATETIME, @pesoEntrada INT,@diferenciaMinutosMinimo INT, @fechaEntrada DATETIME;"
                        + "DECLARE @cantidadTiqueteMismaTaraOPP INT, @placaOPP VARCHAR(50),@fechaInicioDescargueOPP DATETIME, @pesoEntradaOPP INT,@diferenciaMinutosMinimoOPP INT, @fechaEntradaOPP DATETIME;\n" +
                        "	SET @placa='"+Objeto.getMvtoCarbon().getPlaca()+"';\n" +
                        "	SET @fechaInicioDescargue='"+Objeto.getMvtoCarbon().getFechaInicioDescargue()+"';\n" +
                        "	SET @pesoEntrada="+Objeto.getMvtoCarbon().getPesoVacio()+";\n" +
                        "	SET @fechaEntrada='"+Objeto.getMvtoCarbon().getFechaEntradaVehiculo()+"';\n" +
                        "	SET @diferenciaMinutosMinimo= (	SELECT MIN(DATEDIFF (MINUTE,@fechaInicioDescargue , [ti_fcha_slda])) \n" +
                        "									FROM ["+DB+"].[dbo].[tqte] \n" +
                        "									WHERE [ti_plca]=@placa AND [ti_pso_entrda]=@pesoEntrada AND [ti_fcha_entrda] = @fechaEntrada AND @fechaInicioDescargue \n" +
                        "										BETWEEN @fechaInicioDescargue AND [ti_fcha_slda]\n" +
                        "								   );\n" +
                        "	SET @placaOPP='"+Objeto.getMvtoCarbon().getPlaca()+"';\n" +
                        "	SET @fechaInicioDescargueOPP='"+Objeto.getMvtoCarbon().getFechaInicioDescargue()+"';\n" +
                        "	SET @pesoEntradaOPP="+Objeto.getMvtoCarbon().getPesoVacio()+";\n" +
                        "	SET @fechaEntradaOPP='"+Objeto.getMvtoCarbon().getFechaEntradaVehiculo()+"';\n" +
                        "	SET @diferenciaMinutosMinimoOPP= (	SELECT MIN(DATEDIFF (MINUTE,@fechaInicioDescargueOPP , [ti_fcha_slda])) \n" +
                        "									FROM ["+DB_opp+"].[dbo].[tqte] \n" +
                        "									WHERE [ti_plca]=@placaOPP AND [ti_pso_entrda]=@pesoEntradaOPP AND [ti_fcha_entrda] = @fechaEntradaOPP AND @fechaInicioDescargueOPP \n" +
                        "										BETWEEN @fechaInicioDescargueOPP AND [ti_fcha_slda]\n" +
                        "								   );\n" +
                                
                        "SELECT [ti_cnsctvo],[ti_fcha_slda],[ti_pso_slda],[ti_pso_nto],[ti_dpsto]  \n" +
                        "	FROM ["+DB+"].[dbo].[tqte] \n" +
                        "WHERE [ti_plca]=@placa AND [ti_pso_entrda]=@pesoEntrada  AND [ti_fcha_entrda] = @fechaEntrada\n" +
                        "	   AND @fechaInicioDescargue BETWEEN [ti_fcha_entrda] AND [ti_fcha_slda] \n" +
                        "       AND (DATEDIFF (MINUTE, @fechaInicioDescargue , [ti_fcha_slda]))=@diferenciaMinutosMinimo   UNION "+
                        "SELECT [ti_cnsctvo],[ti_fcha_slda],[ti_pso_slda],[ti_pso_nto],[ti_dpsto]  \n" +
                        "	FROM ["+DB_opp+"].[dbo].[tqte] \n" +
                        "WHERE [ti_plca]=@placaOPP AND [ti_pso_entrda]=@pesoEntradaOPP  AND [ti_fcha_entrda] = @fechaEntradaOPP\n" +
                        "	   AND @fechaInicioDescargueOPP BETWEEN [ti_fcha_entrda] AND [ti_fcha_slda] \n" +
                        "       AND (DATEDIFF (MINUTE, @fechaInicioDescargueOPP , [ti_fcha_slda]))=@diferenciaMinutosMinimoOPP ");
                PreparedStatement query= conexion.prepareStatement(""
                        + "DECLARE @cantidadTiqueteMismaTara INT, @placa VARCHAR(50),@fechaInicioDescargue DATETIME, @pesoEntrada INT,@diferenciaMinutosMinimo INT, @fechaEntrada DATETIME;"
                        + "DECLARE @cantidadTiqueteMismaTaraOPP INT, @placaOPP VARCHAR(50),@fechaInicioDescargueOPP DATETIME, @pesoEntradaOPP INT,@diferenciaMinutosMinimoOPP INT, @fechaEntradaOPP DATETIME;\n" +
                        "	SET @placa='"+Objeto.getMvtoCarbon().getPlaca()+"';\n" +
                        "	SET @fechaInicioDescargue='"+Objeto.getMvtoCarbon().getFechaInicioDescargue()+"';\n" +
                        "	SET @pesoEntrada="+Objeto.getMvtoCarbon().getPesoVacio()+";\n" +
                        "	SET @fechaEntrada='"+Objeto.getMvtoCarbon().getFechaEntradaVehiculo()+"';\n" +
                        "	SET @diferenciaMinutosMinimo= (	SELECT MIN(DATEDIFF (MINUTE,@fechaInicioDescargue , [ti_fcha_slda])) \n" +
                        "									FROM ["+DB+"].[dbo].[tqte] \n" +
                        "									WHERE [ti_plca]=@placa AND [ti_pso_entrda]=@pesoEntrada AND [ti_fcha_entrda] = @fechaEntrada AND @fechaInicioDescargue \n" +
                        "										BETWEEN @fechaInicioDescargue AND [ti_fcha_slda]\n" +
                        "								   );\n" +
                        "	SET @placaOPP='"+Objeto.getMvtoCarbon().getPlaca()+"';\n" +
                        "	SET @fechaInicioDescargueOPP='"+Objeto.getMvtoCarbon().getFechaInicioDescargue()+"';\n" +
                        "	SET @pesoEntradaOPP="+Objeto.getMvtoCarbon().getPesoVacio()+";\n" +
                        "	SET @fechaEntradaOPP='"+Objeto.getMvtoCarbon().getFechaEntradaVehiculo()+"';\n" +
                        "	SET @diferenciaMinutosMinimoOPP= (	SELECT MIN(DATEDIFF (MINUTE,@fechaInicioDescargueOPP , [ti_fcha_slda])) \n" +
                        "									FROM ["+DB_opp+"].[dbo].[tqte] \n" +
                        "									WHERE [ti_plca]=@placaOPP AND [ti_pso_entrda]=@pesoEntradaOPP AND [ti_fcha_entrda] = @fechaEntradaOPP AND @fechaInicioDescargueOPP \n" +
                        "										BETWEEN @fechaInicioDescargueOPP AND [ti_fcha_slda]\n" +
                        "								   );\n" +
                                
                        "SELECT [ti_cnsctvo],[ti_fcha_slda],[ti_pso_slda],[ti_pso_nto],[ti_dpsto]  \n" +
                        "	FROM ["+DB+"].[dbo].[tqte] \n" +
                        "WHERE [ti_plca]=@placa AND [ti_pso_entrda]=@pesoEntrada  AND [ti_fcha_entrda] = @fechaEntrada\n" +
                        "	   AND @fechaInicioDescargue BETWEEN [ti_fcha_entrda] AND [ti_fcha_slda] \n" +
                        "       AND (DATEDIFF (MINUTE, @fechaInicioDescargue , [ti_fcha_slda]))=@diferenciaMinutosMinimo   UNION "+
                        "SELECT [ti_cnsctvo],[ti_fcha_slda],[ti_pso_slda],[ti_pso_nto],[ti_dpsto]  \n" +
                        "	FROM ["+DB_opp+"].[dbo].[tqte] \n" +
                        "WHERE [ti_plca]=@placaOPP AND [ti_pso_entrda]=@pesoEntradaOPP  AND [ti_fcha_entrda] = @fechaEntradaOPP\n" +
                        "	   AND @fechaInicioDescargueOPP BETWEEN [ti_fcha_entrda] AND [ti_fcha_slda] \n" +
                        "       AND (DATEDIFF (MINUTE, @fechaInicioDescargueOPP , [ti_fcha_slda]))=@diferenciaMinutosMinimoOPP ");
                ResultSet resultSet= query.executeQuery();
                while(resultSet.next()){ 
                    System.out.println("1");
                    Objeto.getMvtoCarbon().setConsecutivo(resultSet.getString(1));              
                    Objeto.getMvtoCarbon().setFecha_SalidaVehiculo(resultSet.getString(2));
                    Objeto.getMvtoCarbon().setPesoLleno(resultSet.getString(3));              
                    Objeto.getMvtoCarbon().setPesoNeto(resultSet.getString(4));
                    Objeto.getMvtoCarbon().setDeposito(resultSet.getString(5));
                    Objeto.getMvtoCarbon().setConexionPesoCcarga("1");
                    //Se proceso en controlCarga validando informacion con tabla Tiquete
                    try {
                        int n=actualizarDatosMvtoCarbonSinAuditoria(Objeto,us);
                        if(n==1){
                             System.out.println("Actualización Exitosa");
                            if(n==1){
                                n=0;
                                Conexion_DB_costos_vg control2 = new Conexion_DB_costos_vg(tipoConexion);
                                conexion2= control2.ConectarBaseDatos();
                                String DB2=control2.getBaseDeDatos();
                                System.out.println("UPDATE ["+DB2+"].[dbo].[mvto_equipo] set "
                                                                                            + "[me_valor_hora]="+Objeto.getMvtoEquipoAgregar().getValorHora()+", "
                                                                                            + "[me_costo_total]="+Objeto.getMvtoEquipoAgregar().getCostoTotal()+","
                                                                                            + "[me_costo_total_recobro_cliente]="+Objeto.getMvtoEquipoAgregar().getCostoTotalRecobroCliente()+""
                                                                                            + " WHERE [me_cdgo]="+Objeto.getMvtoEquipoAgregar().getCodigo()+"");
                                PreparedStatement queryActualizarDatos_Equipos= conexion2.prepareStatement("UPDATE ["+DB2+"].[dbo].[mvto_equipo] set "
                                                                                            + "[me_valor_hora]=?, "
                                                                                            + "[me_costo_total]=?,"
                                                                                            + "[me_costo_total_recobro_cliente]=?"
                                                                                            + " WHERE [me_cdgo]=?");
                                queryActualizarDatos_Equipos.setString(1,Objeto.getMvtoEquipoAgregar().getValorHora());
                                queryActualizarDatos_Equipos.setString(2,Objeto.getMvtoEquipoAgregar().getCostoTotal());
                                queryActualizarDatos_Equipos.setString(3,Objeto.getMvtoEquipoAgregar().getCostoTotalRecobroCliente());
                                queryActualizarDatos_Equipos.setString(4,Objeto.getMvtoEquipoAgregar().getCodigo());
                                queryActualizarDatos_Equipos.execute();    
                                control2.cerrarConexionBaseDatos();
                                n=1; 
                            }
                        }else{
                            //System.out.println("Algo malo pasó");
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ControlDB_MvtoCarbon_backup.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(Objeto.getMvtoCarbon().getPlaca()+"-"+Objeto.getMvtoCarbon().getPesoLleno()+"-"+Objeto.getMvtoCarbon().getFecha_SalidaVehiculo()+"-"+Objeto.getMvtoCarbon().getPesoNeto());
                }
            }catch (SQLException sqlException){
                System.out.println("Con Error: "+Objeto.getMvtoCarbon().getPlaca()+"-"+Objeto.getMvtoCarbon().getPesoVacio()+"-"+Objeto.getMvtoCarbon().getFechaEntradaVehiculo());
                JOptionPane.showMessageDialog(null, "Error al Tratar de buscar el tiquete");
                sqlException.printStackTrace();
            } 
            catch(Exception e){
                e.printStackTrace();
            }
        //}else{
        //   System.out.println("No entró");
            
        //    Conexion_DB_costos_vg control2 = new Conexion_DB_costos_vg(tipoConexion);
        //    conexion2= control2.ConectarBaseDatos();
        //    String DB2=control2.getBaseDeDatos();
        //    System.out.println(""+"UPDATE ["+DB2+"].[dbo].[mvto_equipo] set "
         //                                                               + "[me_valor_hora]="+Objeto.getMvtoEquipo().getValorHora()+", "
         //                                                               + "[me_costo_total]="+Objeto.getMvtoEquipo().getCostoTotal()+","
         //                                                               + "[me_costo_total_recobro_cliente]="+Objeto.getMvtoEquipo().getCostoTotalRecobroCliente()+""
         //                                                               + " WHERE [me_cdgo]="+Objeto.getMvtoEquipo().getCodigo()+"");
         //   PreparedStatement queryActualizarDatos_Equipos= conexion2.prepareStatement("UPDATE ["+DB2+"].[dbo].[mvto_equipo] set "
         //                                                               + "[me_valor_hora]=?, "
         //                                                               + "[me_costo_total]=?,"
          //                                                              + "[me_costo_total_recobro_cliente]=?"
         //                                                               + " WHERE [me_cdgo]=?");
        //    queryActualizarDatos_Equipos.setString(1,Objeto.getMvtoEquipo().getValorHora());
        //    queryActualizarDatos_Equipos.setString(2,Objeto.getMvtoEquipo().getCostoTotal());
        //    queryActualizarDatos_Equipos.setString(3,Objeto.getMvtoEquipo().getCostoTotalRecobroCliente());
        //    queryActualizarDatos_Equipos.setString(4,Objeto.getMvtoEquipo().getCodigo());
        //    queryActualizarDatos_Equipos.execute();    
       //     control2.cerrarConexionBaseDatos();
        //}
        control.cerrarConexionBaseDatos();
        return Objeto;
    }
    public MvtoCarbon                           ProcesarCentroCostos(MvtoCarbon Objeto1) throws UnknownHostException, SocketException, SQLException{
        Conexion_DB_costos_vg control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        CentroCosto centroCosto=null;
        CentroCostoMayor centroCostoMayor=null;
        //Cargamos el Centro de costo
        try{
            if(Objeto1 != null){
                PreparedStatement query= conexion.prepareStatement("SELECT [cc_cdgo]  FROM ["+DB+"].[dbo].[cntro_cost] WHERE [cc_cntro_oper_cdgo]="+Objeto1.getCentroOperacion().getCodigo()+" AND [cc_cntro_cost_auxiliar_cdgo]="+Objeto1.getCentroCostoAuxiliar().getCodigo()+" AND [cc_cliente_cdgo] LIKE '"+Objeto1.getCliente().getCodigo()+"' AND [cc_cliente_base_datos_cdgo]= "+Objeto1.getCliente().getBaseDatos().getCodigo()+";");
                ResultSet resultSet= query.executeQuery();
               
                boolean validator=true;
                 while(resultSet.next()){ 
                    if(validator){
                        centroCosto= new CentroCosto();
                        centroCosto.setCodigo(resultSet.getString(1));
                        validator=false;
                    }
                }  
                if(centroCosto == null){
                    centroCosto= new CentroCosto();
                    centroCosto.setCodigo("NULL");
                }
                Objeto1.setCentroCosto(centroCosto);
            }
        }catch(Exception e){
        }  
        //Cargamos el Centro de costo Mayor
        try{
            if(Objeto1 != null){
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT  [ccm_cdgo]   FROM ["+DB+"].[dbo].[cntro_cost_mayor] WHERE [ccm_cliente_cdgo] LIKE '"+Objeto1.getCliente().getCodigo()+"' AND [ccm_cntro_cost_subcentro_cdgo]="+Objeto1.getCentroCostoAuxiliar().getCentroCostoSubCentro().getCodigo()+" AND [ccm_cliente_base_datos_cdgo]= "+Objeto1.getCliente().getBaseDatos().getCodigo()+";");
                ResultSet resultSetBuscar= queryBuscar.executeQuery();
                boolean validator=true;
                while(resultSetBuscar.next()){
                    if(validator){
                        centroCostoMayor= new CentroCostoMayor();
                        centroCostoMayor.setCodigo(resultSetBuscar.getString(1));
                        validator=false;
                    }
                }  
                if(centroCostoMayor == null){
                    centroCostoMayor= new CentroCostoMayor();
                    centroCostoMayor.setCodigo("NULL");
                }
                Objeto1.setCentroCostoMayor(centroCostoMayor);
            }
        }catch(Exception e){}
        control.cerrarConexionBaseDatos();
        return Objeto1;
    }
    public int                                  actualizarDatosMvtoCarbon(MvtoCarbon_ListadoEquipos mvtoCarbon, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        int result=0;
        try{  
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            String data="";
            if(mvtoCarbon.getMvtoCarbon().getCentroCostoAuxiliar().getCentroCostoSubCentro().getCodigo()==1){//El registro es un recibo
                if(mvtoCarbon.getMvtoCarbon().getLavadoVehiculo() != null){
                    if(mvtoCarbon.getMvtoCarbon().getLavadoVehiculo().equals("1")){//El lavado es SI
                        if(mvtoCarbon.getMvtoCarbon().getEquipoLavadoVehiculo().getCodigo().equals(mvtoCarbon.getAsignacionEquipo().getEquipo().getCodigo())) {  //El equipo de la asignacion registrado es el mismo que el seleccionado para el lavado, por tal motivo se carga la tarifa de lavado del vehículo.
                            data=",[mc_costoLavadoVehiculo]="+mvtoCarbon.getMvtoCarbon().getCostoLavadoVehiculo()+" "
                                + ",[mc_valorRecaudoEmpresa_lavadoVehiculo]="+mvtoCarbon.getMvtoCarbon().getValorRecaudoEmpresa_lavadoVehiculo()+""
                                + ",[mc_valorRecaudoEquipo_lavadoVehiculo]="+mvtoCarbon.getMvtoCarbon().getValorRecaudoEquipo_lavadoVehiculo()+"";
                            System.out.println("==="+mvtoCarbon.getMvtoCarbon().getEquipoLavadoVehiculo().getCodigo()+"<--->"+mvtoCarbon.getAsignacionEquipo().getEquipo().getCodigo()+"====================>"+data);
                        }
                    }
                }
            }
                 
            System.out.println(""+"UPDATE ["+DB+"].[dbo].[mvto_carbon] set [mc_consecutivo_tqute]="+mvtoCarbon.getMvtoCarbon().getConsecutivo()+",[mc_deposito]="+mvtoCarbon.getMvtoCarbon().getDeposito()+", [mc_peso_lleno]="+mvtoCarbon.getMvtoCarbon().getPesoLleno()+",[mc_peso_neto]="+mvtoCarbon.getMvtoCarbon().getPesoNeto()+",[mc_fecha_salid]='"+mvtoCarbon.getMvtoCarbon().getFecha_SalidaVehiculo()+"',[mc_conexion_peso_ccarga]='"+mvtoCarbon.getMvtoCarbon().getConexionPesoCcarga()+"',[mc_cntro_cost_cdgo]="+mvtoCarbon.getMvtoCarbon().getCentroCosto().getCodigo()+" ,[mc_cntro_cost_mayor_cdgo]="+mvtoCarbon.getMvtoCarbon().getCentroCostoMayor().getCodigo()+" "+data+" WHERE [mc_cdgo]="+mvtoCarbon.getMvtoCarbon().getCodigo()+"");
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_carbon] set [mc_consecutivo_tqute]=?,[mc_deposito]=?, [mc_peso_lleno]=?,[mc_peso_neto]=?,[mc_fecha_salid]='"+mvtoCarbon.getMvtoCarbon().getFecha_SalidaVehiculo()+"',[mc_conexion_peso_ccarga]='"+mvtoCarbon.getMvtoCarbon().getConexionPesoCcarga()+"',[mc_cntro_cost_cdgo]="+mvtoCarbon.getMvtoCarbon().getCentroCosto().getCodigo()+" ,[mc_cntro_cost_mayor_cdgo]="+mvtoCarbon.getMvtoCarbon().getCentroCostoMayor().getCodigo()+" "+data+" WHERE [mc_cdgo]=?");
            queryActualizar.setString(1,mvtoCarbon.getMvtoCarbon().getConsecutivo());
            queryActualizar.setString(2,mvtoCarbon.getMvtoCarbon().getDeposito());
            queryActualizar.setString(3,mvtoCarbon.getMvtoCarbon().getPesoLleno());
            queryActualizar.setString(4,mvtoCarbon.getMvtoCarbon().getPesoNeto());
            //queryActualizar.setString(5,""+mvtoCarbon.getMvtoCarbon().getCentroCosto().getCodigo());
            //queryActualizar.setString(6,""+mvtoCarbon.getMvtoCarbon().getCentroCostoMayor().getCodigo());
           // queryActualizar.setString(4,"'"+mvtoCarbon.getFechaSalida()+"'");
            queryActualizar.setString(5,mvtoCarbon.getMvtoCarbon().getCodigo());
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
                Query_Auditoria.setString(8, mvtoCarbon.getMvtoCarbon().getPlaca()+" Pesos: Vacio:"+mvtoCarbon.getMvtoCarbon().getPesoVacio()+" Lleno: "+mvtoCarbon.getMvtoCarbon().getPesoLleno()+" Neto:"+mvtoCarbon.getMvtoCarbon().getPesoNeto()+"CentroCentro: "+mvtoCarbon.getMvtoCarbon().getCentroCosto().getCodigo()+"CentroCosto_Mayor: "+mvtoCarbon.getMvtoCarbon().getCentroCostoMayor().getCodigo());
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
    public int                                  actualizarDatosMvtoCarbonSinAuditoria(MvtoCarbon_ListadoEquipos mvtoCarbon, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        int result=0;
        try{  
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            String data="";
            if(mvtoCarbon.getMvtoCarbon().getCentroCostoAuxiliar().getCentroCostoSubCentro().getCodigo()==1){//El registro es un recibo
                if(mvtoCarbon.getMvtoCarbon().getLavadoVehiculo() != null){
                    if(mvtoCarbon.getMvtoCarbon().getLavadoVehiculo().equals("1")){//El lavado es SI
                        if(mvtoCarbon.getMvtoCarbon().getEquipoLavadoVehiculo().getCodigo().equals(mvtoCarbon.getAsignacionEquipo().getEquipo().getCodigo())) {  //El equipo de la asignacion registrado es el mismo que el seleccionado para el lavado, por tal motivo se carga la tarifa de lavado del vehículo.
                            data=",[mc_costoLavadoVehiculo]="+mvtoCarbon.getMvtoCarbon().getCostoLavadoVehiculo()+" "
                                + ",[mc_valorRecaudoEmpresa_lavadoVehiculo]="+mvtoCarbon.getMvtoCarbon().getValorRecaudoEmpresa_lavadoVehiculo()+""
                                + ",[mc_valorRecaudoEquipo_lavadoVehiculo]="+mvtoCarbon.getMvtoCarbon().getValorRecaudoEquipo_lavadoVehiculo()+"";
                            
                            System.out.println("==="+mvtoCarbon.getMvtoCarbon().getEquipoLavadoVehiculo().getCodigo()+
                                    "<--->"+mvtoCarbon.getAsignacionEquipo().getEquipo().getCodigo()+"====================>"+data);
                        }
                    }
                }
            }
                 
            System.out.println(""+"UPDATE ["+DB+"].[dbo].[mvto_carbon] set [mc_consecutivo_tqute]="+mvtoCarbon.getMvtoCarbon().getConsecutivo()+",[mc_deposito]="+mvtoCarbon.getMvtoCarbon().getDeposito()+", [mc_peso_lleno]="+mvtoCarbon.getMvtoCarbon().getPesoLleno()+",[mc_peso_neto]="+mvtoCarbon.getMvtoCarbon().getPesoNeto()+",[mc_fecha_salid]='"+mvtoCarbon.getMvtoCarbon().getFecha_SalidaVehiculo()+"',[mc_conexion_peso_ccarga]='"+mvtoCarbon.getMvtoCarbon().getConexionPesoCcarga()+"',[mc_cntro_cost_cdgo]="+mvtoCarbon.getMvtoCarbon().getCentroCosto().getCodigo()+" ,[mc_cntro_cost_mayor_cdgo]="+mvtoCarbon.getMvtoCarbon().getCentroCostoMayor().getCodigo()+" "+data+" WHERE [mc_cdgo]="+mvtoCarbon.getMvtoCarbon().getCodigo()+"");
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[mvto_carbon] set [mc_consecutivo_tqute]=?,[mc_deposito]=?, [mc_peso_lleno]=?,[mc_peso_neto]=?,[mc_fecha_salid]='"+mvtoCarbon.getMvtoCarbon().getFecha_SalidaVehiculo()+"',[mc_conexion_peso_ccarga]='"+mvtoCarbon.getMvtoCarbon().getConexionPesoCcarga()+"',[mc_cntro_cost_cdgo]="+mvtoCarbon.getMvtoCarbon().getCentroCosto().getCodigo()+" ,[mc_cntro_cost_mayor_cdgo]="+mvtoCarbon.getMvtoCarbon().getCentroCostoMayor().getCodigo()+" "+data+" WHERE [mc_cdgo]=?");
            queryActualizar.setString(1,mvtoCarbon.getMvtoCarbon().getConsecutivo());
            queryActualizar.setString(2,mvtoCarbon.getMvtoCarbon().getDeposito());
            queryActualizar.setString(3,mvtoCarbon.getMvtoCarbon().getPesoLleno());
            queryActualizar.setString(4,mvtoCarbon.getMvtoCarbon().getPesoNeto());
            //queryActualizar.setString(5,""+mvtoCarbon.getMvtoCarbon().getCentroCosto().getCodigo());
            //queryActualizar.setString(6,""+mvtoCarbon.getMvtoCarbon().getCentroCostoMayor().getCodigo());
           // queryActualizar.setString(4,"'"+mvtoCarbon.getFechaSalida()+"'");
            queryActualizar.setString(5,mvtoCarbon.getMvtoCarbon().getCodigo());
            queryActualizar.execute();
            result=1;
            /*if(result==1){
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
                Query_Auditoria.setString(8, mvtoCarbon.getMvtoCarbon().getPlaca()+" Pesos: Vacio:"+mvtoCarbon.getMvtoCarbon().getPesoVacio()+" Lleno: "+mvtoCarbon.getMvtoCarbon().getPesoLleno()+" Neto:"+mvtoCarbon.getMvtoCarbon().getPesoNeto()+"CentroCentro: "+mvtoCarbon.getMvtoCarbon().getCentroCosto().getCodigo()+"CentroCosto_Mayor: "+mvtoCarbon.getMvtoCarbon().getCentroCostoMayor().getCodigo());
                Query_Auditoria.execute();
                result=1;
            } */
        }
        catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "ERROR al querer insertar los datos.");
            sqlException.printStackTrace();
        }  
        catch(Exception e){
            e.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return result;
    }
    public ArrayList<Equipo>                    buscarEquipos(String tipoEquipo, String marcaEquipo) throws SQLException {
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        ArrayList<Equipo> listadoObjeto = null;
        conexion= control.ConectarBaseDatos();
        try{
            ResultSet resultSetBuscar;

                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT \n" +
                                                            "	   [eq_cdgo] --1\n" +
                                                            "      ,[eq_tipo_equipo_cdgo] --2\n" +
                                                            "           ,[te_cdgo] --3\n" +
                                                            "           ,[te_desc] --4\n" +
                                                            "           ,[te_estad] --5\n" +
                                                            "      ,[eq_referencia] --6\n" +
                                                            "      ,[eq_producto] --7\n" +
                                                            "      ,[eq_capacidad] --8\n" +
                                                            "      ,[eq_marca] --9\n" +    
                                                            "      ,[eq_modelo] --10\n" +    
                                                            "      ,[eq_serial] --11\n" +       
                                                            "      ,[eq_desc]  --12\n" +
                                                            "      ,[eq_clasificador1_cdgo] --13\n" +
                                                            "           ,clasificador1.[ce_cdgo] AS clasificador1_cdgo --14\n" +
                                                            "           ,clasificador1.[ce_desc] AS clasificador1_desc  --15\n" +
                                                            "           ,clasificador1.[ce_estad] AS clasificador1_estad --16\n" +
                                                            "      ,[eq_clasificador2_cdgo] --17\n" +
                                                            "           ,clasificador2.[ce_cdgo] AS clasificador2_cdgo --18\n" +
                                                            "           ,clasificador2.[ce_desc] AS clasificador2_desc --19\n" +
                                                            "           ,clasificador2.[ce_estad] AS clasificador2_estad --20\n" +
                                                            "      ,[eq_proveedor_equipo_cdgo] --21\n" +
                                                            "      ,[eq_proveedor_equipo_cdgo] --22\n" +
                                                            "           ,[pe_cdgo] --23\n" +
                                                            "           ,[pe_desc] --24\n" +
                                                            "           ,[pe_estad] --25\n" +
                                                            "      ,[eq_equipo_pertenencia_cdgo] --26\n" +
                                                            "           ,[ep_cdgo] --27\n" +
                                                            "           ,[ep_desc] --28\n" +
                                                            "           ,[ep_estad] --29\n" +
                                                            "      ,[eq_observ] --30\n" +
                                                            "      ,[eq_codigo_barra] --31\n" +
                                                            "      ,CASE WHEN (eq_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS eq_estad --32\n" +
                                                            "  FROM ["+DB+"].[dbo].[equipo]\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] ON te_cdgo = eq_tipo_equipo_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON pe_cdgo= eq_proveedor_equipo_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador1 ON clasificador1.ce_cdgo = eq_clasificador1_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador2 ON clasificador2.ce_cdgo = eq_clasificador2_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON ep_cdgo=eq_equipo_pertenencia_cdgo "+
                                                            " WHERE eq_estad=1 AND te_cdgo=? AND eq_marca=?;");
                queryBuscar.setString(1, tipoEquipo);
                queryBuscar.setString(2, marcaEquipo);
                resultSetBuscar= queryBuscar.executeQuery();
            listadoObjeto= new ArrayList();
            while(resultSetBuscar.next()){
                Equipo Objeto = new Equipo();
                Objeto.setCodigo(resultSetBuscar.getString(1));
                Objeto.setTipoEquipo(new TipoEquipo(resultSetBuscar.getString(3),resultSetBuscar.getString(4),resultSetBuscar.getString(5)));
                Objeto.setCodigo_barra(resultSetBuscar.getString(31));
                Objeto.setReferencia(resultSetBuscar.getString(6));
                Objeto.setProducto(resultSetBuscar.getString(7));
                Objeto.setCapacidad(resultSetBuscar.getString(8));
                Objeto.setMarca(resultSetBuscar.getString(9));
                Objeto.setModelo(resultSetBuscar.getString(10));
                Objeto.setSerial(resultSetBuscar.getString(11));
                Objeto.setDescripcion(resultSetBuscar.getString(12));
                Objeto.setClasificador1(new ClasificadorEquipo(resultSetBuscar.getString(14),resultSetBuscar.getString(15),resultSetBuscar.getString(16)));
                Objeto.setClasificador2(new ClasificadorEquipo(resultSetBuscar.getString(18),resultSetBuscar.getString(19),resultSetBuscar.getString(20)));
                //Objeto.setValorHora(resultSetBuscar.getString(21));
                Objeto.setProveedorEquipo(new ProveedorEquipo(resultSetBuscar.getString(23),resultSetBuscar.getString(24),resultSetBuscar.getString(25)));
                Objeto.setPertenenciaEquipo(new Pertenencia(resultSetBuscar.getString(27),resultSetBuscar.getString(28),resultSetBuscar.getString(29)));
                Objeto.setObservacion(resultSetBuscar.getString(30));
                Objeto.setEstado(resultSetBuscar.getString(32));
                listadoObjeto.add(Objeto);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return listadoObjeto;
    }
    public ArrayList<CentroCostoAuxiliar>       buscarCentroCostoAuxiliar(String SubCentroCosto) throws SQLException{
        ArrayList<CentroCostoAuxiliar> listadoObjeto = null;
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        conexion= control.ConectarBaseDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT cca_cdgo, cca_desc, ccs_cdgo, ccs_desc, ccs_estad, CASE WHEN (cca_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS cca_estad "
                    + " FROM ["+DB+"].[dbo].[cntro_cost_auxiliar] "
                    + " INNER JOIN [cntro_cost_subcentro]  ON ccs_cdgo=cca_cntro_cost_subcentro_cdgo WHERE [cca_estad]=1 AND ccs_cdgo=?;");
            queryBuscar.setString(1, SubCentroCosto);
            ResultSet resultSetBuscar= queryBuscar.executeQuery();
            boolean validator=true;
            while(resultSetBuscar.next()){
                if(validator){
                    listadoObjeto = new ArrayList();
                    validator= false;
                }
                CentroCostoAuxiliar Objeto = new CentroCostoAuxiliar();
                Objeto.setCodigo(resultSetBuscar.getInt(1));
                Objeto.setDescripcion(resultSetBuscar.getString(2));
                Objeto.setCentroCostoSubCentro(new CentroCostoSubCentro(resultSetBuscar.getInt(3),resultSetBuscar.getString(4),resultSetBuscar.getString(5)));
                Objeto.setEstado(resultSetBuscar.getString(6));
                listadoObjeto.add(Objeto);
            }
        }catch (SQLException sqlException) {
            //JOptionPane.showMessageDialog(null, "Error al tratar de consultar los centro de costros Auxiliares");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return listadoObjeto;
    }
    //Validaciones de existencias
    public boolean                              validarExistenciaArticulo(Articulo Objeto){
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        conexion= control.ConectarBaseDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[articulo] WHERE [ar_cdgo] like ? AND [ar_base_datos_cdgo] =?;");
            query.setString(1, Objeto.getCodigo());
            query.setString(2, Objeto.getBaseDatos().getCodigo());
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){
                retorno =true;
            }
        }catch (SQLException sqlException){
            System.out.println("Error al tratar de Buscar la Existencia del Articulo");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return retorno;
    }
    public boolean                              validarExistenciaTransportadora(Transportadora Objeto){
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        conexion= control.ConectarBaseDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[transprtdora] WHERE [tr_cdgo] like ? AND [tr_base_datos_cdgo] =?;");
            query.setString(1, Objeto.getCodigo());
            query.setString(2, Objeto.getBaseDatos().getCodigo());
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){
                retorno =true;
            }
        }catch (SQLException sqlException){
            System.out.println("Error al tratar de Buscar la Existencia de la Transportadora");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return retorno;
    }
    public boolean                              validarExistenciaMotonave(Motonave Objeto){
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        conexion= control.ConectarBaseDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[motonave] WHERE [mn_cdgo] like ? AND [mn_base_datos_cdgo]=?;");
            query.setString(1, Objeto.getCodigo());
            query.setString(2, Objeto.getBaseDatos().getCodigo());
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){
                retorno =true;
            }
        }catch (SQLException sqlException){
            System.out.println("Error al tratar de Buscar la Existencia de la Motonave");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return retorno;
    }
    public boolean                              validarExistenciaCliente(Cliente Objeto){
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        conexion= control.ConectarBaseDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cliente] WHERE [cl_cdgo] like ? AND [cl_base_datos_cdgo]=?;");
            query.setString(1, Objeto.getCodigo());
            query.setString(2, Objeto.getBaseDatos().getCodigo());
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){
                retorno =true;
            }
        }catch (SQLException sqlException){
            System.out.println("Error al tratar de Buscar la Existencia del Cliente");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return retorno;
    }
    public boolean                              validarExistenciaMvtoCarbon(MvtoCarbon Objeto){
        Conexion_DB_costos_vg control = new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        conexion= control.ConectarBaseDatos();
        boolean retorno=false;
        try{
            System.out.println("SELECT * FROM ["+DB+"].[dbo].[mvto_carbon] WHERE  [mc_estad_mvto_carbon_cdgo] =1 AND [mc_placa_vehiculo]= '"+Objeto.getPlaca()+"' AND [mc_peso_vacio]="+Objeto.getPesoVacio()+" AND [mc_fecha_entrad]=(SELECT CONVERT(smalldatetime, '"+Objeto.getFechaEntradaVehiculo()+"'));");
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[mvto_carbon] WHERE  [mc_estad_mvto_carbon_cdgo] =1 AND [mc_placa_vehiculo]= '"+Objeto.getPlaca()+"' AND [mc_peso_vacio]="+Objeto.getPesoVacio()+" AND [mc_fecha_entrad]=(SELECT CONVERT(smalldatetime, '"+Objeto.getFechaEntradaVehiculo()+"'));");
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){
                retorno =true;
            }
        }catch (SQLException sqlException){
            System.out.println("Error al tratar de Buscar la Existencia del Descargue de carbón");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        System.out.println(""+retorno);
        return retorno;
    }
    //Registros que no poseen existencia
    public int                                  registrarArticulo(Articulo Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        try{
            conexion= control.ConectarBaseDatos();
            String estado="";
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                estado="ACTIVO";
            }else{
                estado="INACTIVO";
            }
            if(!validarExistenciaArticulo(Objeto)){
                conexion= control.ConectarBaseDatos();
                PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[articulo] ([ar_cdgo],[ar_desc],[ar_estad],[ar_base_datos_cdgo]) VALUES (?,?,?,?);");
                Query.setString(1, Objeto.getCodigo());
                Query.setString(2, Objeto.getDescripcion());
                Query.setString(3, Objeto.getEstado());
                Query.setString(4, Objeto.getBaseDatos().getCodigo());
                Query.execute();
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
                        "           ,'ARTICULO'" +
                        "           ,ONCAT (?,?,' Nombre: ',?,' Estado: ',?,' Base Datos: ',?));");
                    Query_Auditoria.setString(1, us.getCodigo());
                    Query_Auditoria.setString(2, namePc);
                    Query_Auditoria.setString(3, ipPc);
                    Query_Auditoria.setString(4, macPC);
                    Query_Auditoria.setString(5, Objeto.getCodigo());
                    Query_Auditoria.setString(6, "Se registró un nuevo articulo en el sistema, con Código: ");
                    Query_Auditoria.setString(7, Objeto.getCodigo());
                    Query_Auditoria.setString(8, Objeto.getDescripcion());
                    Query_Auditoria.setString(9, estado);
                    Query_Auditoria.setString(10, Objeto.getBaseDatos().getCodigo());
                    Query_Auditoria.execute();
                    result=1;
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
    public int                                  registrarTransportadora(Transportadora Objeto, Usuario us)  throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        try{
            conexion= control.ConectarBaseDatos();
            String estado="";
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                estado="ACTIVO";
            }else{
                estado="INACTIVO";
            }
            if(!validarExistenciaTransportadora(Objeto)){
                conexion= control.ConectarBaseDatos();
                PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[transprtdora] ([tr_cdgo],[tr_nit],[tr_desc],[tr_observ],[tr_estad],[tr_base_datos_cdgo]) VALUES (?,?,?,?,?,?);");
                Query.setString(1, Objeto.getCodigo());
                Query.setString(2, Objeto.getNit());
                Query.setString(3, Objeto.getDescripcion());
                Query.setString(4, Objeto.getObservacion());
                Query.setString(5, Objeto.getEstado());
                 Query.setString(6, Objeto.getBaseDatos().getCodigo());
                Query.execute();
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
                        "           ,'TRANSPORTADORA'" +
                        "           ,CONCAT (?,?,' Nit: ',?,' Estado: ',?, 'Base Datos: ',?));");
                    Query_Auditoria.setString(1, us.getCodigo());
                    Query_Auditoria.setString(2, namePc);
                    Query_Auditoria.setString(3, ipPc);
                    Query_Auditoria.setString(4, macPC);
                    Query_Auditoria.setString(5, Objeto.getCodigo());
                    Query_Auditoria.setString(6, "Se registró una nueva transportadora en el sistema, con Código: ");
                    Query_Auditoria.setString(7, Objeto.getCodigo());
                    Query_Auditoria.setString(8, Objeto.getNit()+" Nombre: "+Objeto.getDescripcion()+" Obervacion:"+Objeto.getObservacion());
                    Query_Auditoria.setString(9, estado);
                     Query_Auditoria.setString(10, Objeto.getBaseDatos().getCodigo());
                    Query_Auditoria.execute();
                    result=1;
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
    public int                                  registrarMotonave(Motonave Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        try{
            conexion= control.ConectarBaseDatos();
            String estado="";
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                estado="ACTIVO";
            }else{
                estado="INACTIVO";
            }
            if(!validarExistenciaMotonave(Objeto)){
                conexion= control.ConectarBaseDatos();
                PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[motonave] ([mn_cdgo],[mn_desc],[mn_estad],[mn_base_datos_cdgo]) VALUES (?,?,?,?);");
                Query.setString(1, Objeto.getCodigo());
                Query.setString(2, Objeto.getDescripcion());
                Query.setString(3, Objeto.getEstado());
                Query.setString(4, Objeto.getBaseDatos().getCodigo());
                Query.execute();
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
                        "           ,'MOTONAVE'" +
                        "           ,CONCAT (?,?,' Nombre: ',?,' Estado: ',?,' BaseDatos: ',?));");
                    Query_Auditoria.setString(1, us.getCodigo());
                    Query_Auditoria.setString(2, namePc);
                    Query_Auditoria.setString(3, ipPc);
                    Query_Auditoria.setString(4, macPC);
                    Query_Auditoria.setString(5, Objeto.getCodigo());
                    Query_Auditoria.setString(6, "Se registró una nueva motonave en el sistema, con Código: ");
                    Query_Auditoria.setString(7, Objeto.getCodigo());
                    Query_Auditoria.setString(8, Objeto.getDescripcion());
                    Query_Auditoria.setString(9, estado);
                    Query_Auditoria.setString(10, Objeto.getBaseDatos().getCodigo());
                    Query_Auditoria.execute();
                    result=1;
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
    public int                                  registrarCliente(Cliente Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        try{
            conexion= control.ConectarBaseDatos();
            String estadoObjeto="";
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                estadoObjeto="ACTIVO";
            }else{
                if(Objeto.getEstado().equalsIgnoreCase("0")){
                    estadoObjeto="INACTIVO";
                }else{
                    estadoObjeto="NULL";
                }
            }
            if(!validarExistenciaCliente(Objeto)){
                conexion= control.ConectarBaseDatos();
                if(Objeto.getCodigo().equalsIgnoreCase("") || Objeto.getDescripcion().equalsIgnoreCase("") ||
                        Objeto.getEstado() ==null || Objeto.getEstado().equalsIgnoreCase("")){
                }else{
                    PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[cliente] ([cl_cdgo],[cl_desc],[cl_estad],[cl_base_datos_cdgo]) VALUES (?,?,?,?);");
                    Query.setString(1, Objeto.getCodigo());
                    Query.setString(2, Objeto.getDescripcion());
                    Query.setString(3, Objeto.getEstado());
                    Query.setString(4, Objeto.getBaseDatos().getCodigo());
                    Query.execute();
                    result=1;
                    if(result==1){
                        System.out.println("Registrado");
                        try{
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
                        "           ,'CLIENTE'" +
                        "           ,CONCAT (?,?,' Nombre: ',?,' Estado: ',?,' BaseDatos: ',?));");
                            Query_Auditoria.setString(1, us.getCodigo());
                            Query_Auditoria.setString(2, namePc);
                            Query_Auditoria.setString(3, ipPc);
                            Query_Auditoria.setString(4, macPC);
                            Query_Auditoria.setString(5, Objeto.getCodigo());
                            Query_Auditoria.setString(6, "Se registró un nuevo cliente en el sistema, con Código: ");
                            Query_Auditoria.setString(7, Objeto.getCodigo());
                            Query_Auditoria.setString(8, Objeto.getDescripcion());
                            Query_Auditoria.setString(9, estadoObjeto);
                            Query_Auditoria.setString(10, Objeto.getBaseDatos().getCodigo());
                            Query_Auditoria.execute();
                            result=1;
                            if(result==1){
                                registrarCliente_recobro(Objeto,us);
                            }
                        }catch (SQLException sqlException){
                            //JOptionPane.showMessageDialog(null, "Error al Tratar de buscar");
                            sqlException.printStackTrace();
                            System.exit(1);
                        }
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
    public int                                  registrarCliente_recobro(Cliente Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        try{
            conexion= control.ConectarBaseDatos();
            PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[cliente_recobro] ([clr_cdgo],[clr_cliente_cdgo],[clr_usuario_cdgo],[clr_valor_recobro],[clr_fecha_registro],[clr_cliente_base_datos_cdgo]) VALUES ((SELECT (CASE WHEN (MAX([clr_cdgo]) IS NULL)\n" +
                                                                                                    " THEN 1\n" +
                                                                                                    " ELSE (MAX([clr_cdgo])+1) END)AS [clr_cdgo]\n" +
                                                                                " FROM ["+DB+"].[dbo].[cliente_recobro]),?,?,?,(SELECT SYSDATETIME()),?);");
            Query.setString(1, Objeto.getCodigo());
            Query.setString(2, us.getCodigo());
            Query.setInt(3, Objeto.getValorRecobro());
            Query.setString(4, Objeto.getBaseDatos().getCodigo());
            Query.execute();
            result=1;
            //Procedemos a registrar en la tabla Auditoria
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
                                        "           ,'CLIENTE'" +
                                        "           ,CONCAT (?,?,' Nombre: ',?,' Estado: ',?, ' BaseDatos: ',?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, Objeto.getCodigo());
                Query_Auditoria.setString(6, "Se registró un recobro en el sistema para el cliente Código: ");
                Query_Auditoria.setString(7, Objeto.getCodigo());
                Query_Auditoria.setString(8, Objeto.getDescripcion());
                Query_Auditoria.setInt(9, Objeto.getValorRecobro());
                Query_Auditoria.setString(10, Objeto.getBaseDatos().getCodigo());
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
    public String                               comparadorFechaActual(String ano,String mes, String dia, String hora, String minuto){
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
    public ArrayList<MvtoCarbon>                buscarVehiculosAnadirVehiculos(String placa,String DatetimeInicio, String DatetimeFin) throws SQLException{
        Conexion_DB_ccargaGP control_GP = new Conexion_DB_ccargaGP(tipoConexion);
        Conexion_DB_ccargaOPP control_OPP = new Conexion_DB_ccargaOPP(tipoConexion);
        String DB_GP=control_GP.getBaseDeDatos();
        String DB_OPP=control_OPP.getBaseDeDatos();
        ArrayList<MvtoCarbon> listadoObjeto = null;
        conexion = control_GP.ConectarBaseDatos();
        try{
            System.out.println("DECLARE @placa VARCHAR(50),@fechaTara DATETIME, @fechaDestare DATETIME;"+
                        " SET @placa='%"+placa+"%';\n" +
                        " SET @fechaTara='"+DatetimeInicio+"';\n" +
                        " SET @fechaDestare='"+DatetimeFin+"';\n" +
                        "              SELECT TOP 4000 \n" +
                        "           PRODUCTO_CODIGO=ar_cdgo,--1 \n" +
                        "           PRODUCTO=ar_nmbre, --2\n" +
                        "           CLIENTE_CODIGO=cl_cdgo, --3 \n" +
                        "           CLIENTE=cl_nmbre,  --4\n" +
                        "           TRANSPORTADORA_CODIGO=[ti_trnsprtdra],--5 \n" +
                        "           TRANSPORTADORA_NOMBRE=tr_nmbre,--6 \n" +
                        "           ORDEN=[ti_orden],--7\n" +
                        "		DEPOSITO=[ti_dpsto], --8\n" +
                        "		CONSECUTIVO=[ti_cnsctvo],--9\n" +
                        "           PLACA=[ti_plca], --10\n" +
                        "           PESO_VACIO=[ti_pso_entrda], --11\n" +
                        "		PESO_LLENO=[ti_pso_slda], --12\n" +
                        "		PESO_NETO=[ti_pso_nto],--13\n" +
                        "           FECHA_ENTRADA=[ti_fcha_entrda],--14\n" +
                        "		FECHA_SALIDA=[ti_fcha_slda],--15\n" +
                        "           MN_CODIGO=mo_cdgo, --16\n" +
                        "           MN=mo_nmbre ,--17\n" +
                        "		FECHA_TIQUETE=  [ti_fcha], --18\n" +
                        "		1 AS db --19\n" +
                        "      ,[ti_cncpto]\n" +
                        "      ,[ti_orden_intrna]\n" +
                        "      ,[ti_slctud_rtro]\n" +
                        "      ,[ti_trnsprtdra]\n" +
                        "      ,[ti_cnfgrcion_vhclar]\n" +
                        "      ,[ti_cdla]\n" +
                        "      ,[ti_nmbre_cndctor]\n" +
                        "      ,[ti_bdga]\n" +
                        "      ,[ti_pso_entrda]\n" +
                        "      ,[ti_pso_slda]\n" +
                        "      ,[ti_pso_auxliar]\n" +
                        "      ,[ti_pso_empque]\n" +
                        "      ,[ti_pso_nto]\n" +
                        "      ,[ti_unddes]\n" +
                        "      ,[ti_unddes_a_dspchar]\n" +
                        "      ,[ti_bscla_entrda]\n" +
                        "      ,[ti_bscla_slda]\n" +
                        "      ,[ti_rmlque]\n" +
                        "      ,[ti_esctlla]\n" +
                        "      ,[ti_id_bulk]\n" +
                        "      ,[ti_mdldad_dscrgue]\n" +
                        "      ,[ti_empque]\n" +
                        "      ,[ti_id_scdad]\n" +
                        "      ,[ti_fcha_llgda]\n" +
                        "      ,[ti_cnsctvo_crte]\n" +
                        "      ,[ti_pso_entrda_mnual]\n" +
                        "      ,[ti_pso_slda_mnual]\n" +
                        "      ,[ti_obsrvcnes]\n" +
                        "      ,[ti_usrio]\n" +
                        "      ,[ti_actvo]\n" +
                        "      ,[ti_dclrcion]\n" +
                        "      ,[ti_sbrepso]\n" +
                        "      ,[ti_rgstro_drcto]\n" +
                        "      ,[ti_dfrncia_klos]\n" +
                        "      ,[ti_fcha_incio_crgue]\n" +
                        "      ,[ti_fcha_fin_crgue]\n" +
                        "      ,[ti_rgstro_imprtdo]\n" +
                        "      ,[ti_psdo_sin_rdcar]\n" +
                        "      ,[ti_fcha_entrda_scdad]\n" +
                        "      ,[ti_fcha_slda_scdad]\n" +
                        "      ,[ti_usrio_entrda_scdad]\n" +
                        "      ,[ti_usrio_slda_scdad]\n" +
                        "      ,[ti_fcha_llgda_crgue]\n" +
                        "      ,[ti_rgstro_crgue_sin_hlla]\n" +
                        "      ,[ti_envdo_sstma_dspcho]\n" +
                        "      ,[ti_bscla_dspcho]\n" +
                        "      ,[ti_pso_a_crgar]\n" +
                        "      ,[ti_idrow]\n" +
                        "      ,[ti_rmsion_cnsctvo]\n" +
                        "      ,[ti_id_trnsccion_extrna]\n" +
                        "      ,[ti_id_crre]\n" +
                        "      ,[ti_id_trnsccion_orgnal]\n" +
                        "      ,[ti_prvdor]\n" +
                        "      ,[ti_cdad]\n" +
                        "      ,[ti_plca_ocr_entrda]\n" +
                        "      ,[ti_plca_ocr_slda]\n" +
                        "      ,[ti_cmprtdo]\n" +
                        "      ,[ti_pso_a_cmbiar]\n" +
                        "      ,[ti_ptio]\n" +
                        "      ,[ti_usrio_rgstro_crgue]\n" +
                        "      ,[ti_asntdo_erp]\n" +
                        "      ,[ti_excldo_erp]\n" +
                        "      ,[ti_cnsctvo_erp]\n" +
                        "      ,[ti_arrbo_mtnve_dspcho]\n" +
                        "      ,[ti_dstnddo]\n" +
                        "      ,[ti_rgstro_exprtdo]\n" +
                        "  FROM ["+DB_GP+"].[dbo].[tqte]\n" +
                        "  INNER JOIN ["+DB_GP+"].[dbo].dpsto  ON de_cdgo=[ti_dpsto] \n" +
                        "                     INNER JOIN ["+DB_GP+"].[dbo].clnte ON de_clnte= cl_cdgo \n" +
                        "                     INNER JOIN ["+DB_GP+"].[dbo].[arrbo_mtnve] ON de_arrbo_mtnve=[am_cdgo] \n" +
                        "                     INNER JOIN ["+DB_GP+"].[dbo].mtnve ON [am_mtnve]=mo_cdgo \n" +
                        "                                         INNER JOIN ["+DB_GP+"].[dbo].artclo ON de_artclo = ar_cdgo \n" +
                        "                                         INNER JOIN  ["+DB_GP+"].[dbo].trnsprtdra ON tr_cdgo=[ti_trnsprtdra] \n" +
                        "                                         INNER JOIN ["+DB_GP+"].[dbo].cncpto ON  cn_cdgo= [ti_cncpto]  \n" +
                        "WHERE ti_fcha BETWEEN @fechaTara AND @fechaDestare AND ti_plca LIKE @placa "
               + " UNION "
               + " SELECT TOP 4000 \n" +
                    "           PRODUCTO_CODIGO=ar_cdgo,--1 \n" +
                    "           PRODUCTO=ar_nmbre, --2\n" +
                    "           CLIENTE_CODIGO=cl_cdgo, --3 \n" +
                    "           CLIENTE=cl_nmbre,  --4\n" +
                    "           TRANSPORTADORA_CODIGO=[ti_trnsprtdra],--5 \n" +
                    "           TRANSPORTADORA_NOMBRE=tr_nmbre,--6 \n" +
                    "           ORDEN=[ti_orden],--7\n" +
                    "		DEPOSITO=[ti_dpsto], --8\n" +
                    "		CONSECUTIVO=[ti_cnsctvo],--9\n" +
                    "           PLACA=[ti_plca], --10\n" +
                    "           PESO_VACIO=[ti_pso_entrda], --11\n" +
                    "		PESO_LLENO=[ti_pso_slda], --12\n" +
                    "		PESO_NETO=[ti_pso_nto],--13\n" +
                    "           FECHA_ENTRADA=[ti_fcha_entrda],--14\n" +
                    "		FECHA_SALIDA=[ti_fcha_slda],--15\n" +
                    "           MN_CODIGO=mo_cdgo, --16\n" +
                    "           MN=mo_nmbre ,--17\n" +
                    "		FECHA_TIQUETE=  [ti_fcha], --18\n" +
                    "		2 AS db --19\n" +
                    "      ,[ti_cncpto]\n" +
                    "      ,[ti_orden_intrna]\n" +
                    "      ,[ti_slctud_rtro]\n" +
                    "      ,[ti_trnsprtdra]\n" +
                    "      ,[ti_cnfgrcion_vhclar]\n" +
                    "      ,[ti_cdla]\n" +
                    "      ,[ti_nmbre_cndctor]\n" +
                    "      ,[ti_bdga]\n" +
                    "      ,[ti_pso_entrda]\n" +
                    "      ,[ti_pso_slda]\n" +
                    "      ,[ti_pso_auxliar]\n" +
                    "      ,[ti_pso_empque]\n" +
                    "      ,[ti_pso_nto]\n" +
                    "      ,[ti_unddes]\n" +
                    "      ,[ti_unddes_a_dspchar]\n" +
                    "      ,[ti_bscla_entrda]\n" +
                    "      ,[ti_bscla_slda]\n" +
                    "      ,[ti_rmlque]\n" +
                    "      ,[ti_esctlla]\n" +
                    "      ,[ti_id_bulk]\n" +
                    "      ,[ti_mdldad_dscrgue]\n" +
                    "      ,[ti_empque]\n" +
                    "      ,[ti_id_scdad]\n" +
                    "      ,[ti_fcha_llgda]\n" +
                    "      ,[ti_cnsctvo_crte]\n" +
                    "      ,[ti_pso_entrda_mnual]\n" +
                    "      ,[ti_pso_slda_mnual]\n" +
                    "      ,[ti_obsrvcnes]\n" +
                    "      ,[ti_usrio]\n" +
                    "      ,[ti_actvo]\n" +
                    "      ,[ti_dclrcion]\n" +
                    "      ,[ti_sbrepso]\n" +
                    "      ,[ti_rgstro_drcto]\n" +
                    "      ,[ti_dfrncia_klos]\n" +
                    "      ,[ti_fcha_incio_crgue]\n" +
                    "      ,[ti_fcha_fin_crgue]\n" +
                    "      ,[ti_rgstro_imprtdo]\n" +
                    "      ,[ti_psdo_sin_rdcar]\n" +
                    "      ,[ti_fcha_entrda_scdad]\n" +
                    "      ,[ti_fcha_slda_scdad]\n" +
                    "      ,[ti_usrio_entrda_scdad]\n" +
                    "      ,[ti_usrio_slda_scdad]\n" +
                    "      ,[ti_fcha_llgda_crgue]\n" +
                    "      ,[ti_rgstro_crgue_sin_hlla]\n" +
                    "      ,[ti_envdo_sstma_dspcho]\n" +
                    "      ,[ti_bscla_dspcho]\n" +
                    "      ,[ti_pso_a_crgar]\n" +
                    "      ,[ti_idrow]\n" +
                    "      ,[ti_rmsion_cnsctvo]\n" +
                    "      ,[ti_id_trnsccion_extrna]\n" +
                    "      ,[ti_id_crre]\n" +
                    "      ,[ti_id_trnsccion_orgnal]\n" +
                    "      ,[ti_prvdor]\n" +
                    "      ,[ti_cdad]\n" +
                    "      ,[ti_plca_ocr_entrda]\n" +
                    "      ,[ti_plca_ocr_slda]\n" +
                    "      ,[ti_cmprtdo]\n" +
                    "      ,[ti_pso_a_cmbiar]\n" +
                    "      ,[ti_ptio]\n" +
                    "      ,[ti_usrio_rgstro_crgue]\n" +
                    "      ,[ti_asntdo_erp]\n" +
                    "      ,[ti_excldo_erp]\n" +
                    "      ,[ti_cnsctvo_erp]\n" +
                    "      ,[ti_arrbo_mtnve_dspcho]\n" +
                    "      ,[ti_dstnddo]\n" +
                    "      ,[ti_rgstro_exprtdo]\n" +
                    "  FROM ["+DB_OPP+"].[dbo].[tqte]\n" +
                    "  INNER JOIN ["+DB_OPP+"].[dbo].dpsto  ON de_cdgo=[ti_dpsto] \n" +
                    "                     INNER JOIN ["+DB_OPP+"].[dbo].clnte ON de_clnte= cl_cdgo \n" +
                    "                     INNER JOIN ["+DB_OPP+"].[dbo].[arrbo_mtnve] ON de_arrbo_mtnve=[am_cdgo] \n" +
                    "                     INNER JOIN ["+DB_OPP+"].[dbo].mtnve ON [am_mtnve]=mo_cdgo \n" +
                    "                                         INNER JOIN ["+DB_OPP+"].[dbo].artclo ON de_artclo = ar_cdgo \n" +
                    "                                         INNER JOIN  ["+DB_OPP+"].[dbo].trnsprtdra ON tr_cdgo=[ti_trnsprtdra] \n" +
                    "                                         INNER JOIN ["+DB_OPP+"].[dbo].cncpto ON  cn_cdgo= [ti_cncpto]  \n" +
                    " WHERE ti_fcha BETWEEN @fechaTara AND @fechaDestare AND ti_plca LIKE @placa  ORDER BY ti_plca ASC");
                        PreparedStatement queryBuscar = conexion.prepareStatement("DECLARE @placa VARCHAR(50),@fechaTara DATETIME, @fechaDestare DATETIME;"+
                        " SET @placa='%"+placa+"%';\n" +
                        " SET @fechaTara='"+DatetimeInicio+"';\n" +
                        " SET @fechaDestare='"+DatetimeFin+"';\n" +
                        "  SELECT TOP 4000 \n" +
                        "           PRODUCTO_CODIGO=ar_cdgo,--1 \n" +
                        "           PRODUCTO=ar_nmbre, --2\n" +
                        "           CLIENTE_CODIGO=cl_cdgo, --3 \n" +
                        "           CLIENTE=cl_nmbre,  --4\n" +
                        "           TRANSPORTADORA_CODIGO=[ti_trnsprtdra],--5 \n" +
                        "           TRANSPORTADORA_NOMBRE=tr_nmbre,--6 \n" +
                        "           ORDEN=[ti_orden],--7\n" +
                        "           DEPOSITO=[ti_dpsto], --8\n" +
                        "           CONSECUTIVO=[ti_cnsctvo],--9\n" +
                        "           PLACA=[ti_plca], --10\n" +
                        "           PESO_VACIO=[ti_pso_entrda], --11\n" +
                        "           PESO_LLENO=[ti_pso_slda], --12\n" +
                        "           PESO_NETO=[ti_pso_nto],--13\n" +
                        "           FECHA_ENTRADA=[ti_fcha_entrda],--14\n" +
                        "           FECHA_SALIDA=[ti_fcha_slda],--15\n" +
                        "           MN_CODIGO=mo_cdgo, --16\n" +
                        "           MN=mo_nmbre ,--17\n" +
                        "           FECHA_TIQUETE=  [ti_fcha], --18\n" +
                        "           1 AS db --19\n" +
                        "      ,[ti_cncpto]\n" +
                        "      ,[ti_orden_intrna]\n" +
                        "      ,[ti_slctud_rtro]\n" +
                        "      ,[ti_trnsprtdra]\n" +
                        "      ,[ti_cnfgrcion_vhclar]\n" +
                        "      ,[ti_cdla]\n" +
                        "      ,[ti_nmbre_cndctor]\n" +
                        "      ,[ti_bdga]\n" +
                        "      ,[ti_pso_entrda]\n" +
                        "      ,[ti_pso_slda]\n" +
                        "      ,[ti_pso_auxliar]\n" +
                        "      ,[ti_pso_empque]\n" +
                        "      ,[ti_pso_nto]\n" +
                        "      ,[ti_unddes]\n" +
                        "      ,[ti_unddes_a_dspchar]\n" +
                        "      ,[ti_bscla_entrda]\n" +
                        "      ,[ti_bscla_slda]\n" +
                        "      ,[ti_rmlque]\n" +
                        "      ,[ti_esctlla]\n" +
                        "      ,[ti_id_bulk]\n" +
                        "      ,[ti_mdldad_dscrgue]\n" +
                        "      ,[ti_empque]\n" +
                        "      ,[ti_id_scdad]\n" +
                        "      ,[ti_fcha_llgda]\n" +
                        "      ,[ti_cnsctvo_crte]\n" +
                        "      ,[ti_pso_entrda_mnual]\n" +
                        "      ,[ti_pso_slda_mnual]\n" +
                        "      ,[ti_obsrvcnes]\n" +
                        "      ,[ti_usrio]\n" +
                        "      ,[ti_actvo]\n" +
                        "      ,[ti_dclrcion]\n" +
                        "      ,[ti_sbrepso]\n" +
                        "      ,[ti_rgstro_drcto]\n" +
                        "      ,[ti_dfrncia_klos]\n" +
                        "      ,[ti_fcha_incio_crgue]\n" +
                        "      ,[ti_fcha_fin_crgue]\n" +
                        "      ,[ti_rgstro_imprtdo]\n" +
                        "      ,[ti_psdo_sin_rdcar]\n" +
                        "      ,[ti_fcha_entrda_scdad]\n" +
                        "      ,[ti_fcha_slda_scdad]\n" +
                        "      ,[ti_usrio_entrda_scdad]\n" +
                        "      ,[ti_usrio_slda_scdad]\n" +
                        "      ,[ti_fcha_llgda_crgue]\n" +
                        "      ,[ti_rgstro_crgue_sin_hlla]\n" +
                        "      ,[ti_envdo_sstma_dspcho]\n" +
                        "      ,[ti_bscla_dspcho]\n" +
                        "      ,[ti_pso_a_crgar]\n" +
                        "      ,[ti_idrow]\n" +
                        "      ,[ti_rmsion_cnsctvo]\n" +
                        "      ,[ti_id_trnsccion_extrna]\n" +
                        "      ,[ti_id_crre]\n" +
                        "      ,[ti_id_trnsccion_orgnal]\n" +
                        "      ,[ti_prvdor]\n" +
                        "      ,[ti_cdad]\n" +
                        "      ,[ti_plca_ocr_entrda]\n" +
                        "      ,[ti_plca_ocr_slda]\n" +
                        "      ,[ti_cmprtdo]\n" +
                        "      ,[ti_pso_a_cmbiar]\n" +
                        "      ,[ti_ptio]\n" +
                        "      ,[ti_usrio_rgstro_crgue]\n" +
                        "      ,[ti_asntdo_erp]\n" +
                        "      ,[ti_excldo_erp]\n" +
                        "      ,[ti_cnsctvo_erp]\n" +
                        "      ,[ti_arrbo_mtnve_dspcho]\n" +
                        "      ,[ti_dstnddo]\n" +
                        "      ,[ti_rgstro_exprtdo]\n" +
                        "  FROM ["+DB_GP+"].[dbo].[tqte]\n" +
                        "  INNER JOIN ["+DB_GP+"].[dbo].dpsto  ON de_cdgo=[ti_dpsto] \n" +
                        "                     INNER JOIN ["+DB_GP+"].[dbo].clnte ON de_clnte= cl_cdgo \n" +
                        "                     INNER JOIN ["+DB_GP+"].[dbo].[arrbo_mtnve] ON de_arrbo_mtnve=[am_cdgo] \n" +
                        "                     INNER JOIN ["+DB_GP+"].[dbo].mtnve ON [am_mtnve]=mo_cdgo \n" +
                        "                                         INNER JOIN ["+DB_GP+"].[dbo].artclo ON de_artclo = ar_cdgo \n" +
                        "                                         INNER JOIN  ["+DB_GP+"].[dbo].trnsprtdra ON tr_cdgo=[ti_trnsprtdra] \n" +
                        "                                         INNER JOIN ["+DB_GP+"].[dbo].cncpto ON  cn_cdgo= [ti_cncpto]  \n" +
                        " WHERE ti_fcha BETWEEN @fechaTara AND @fechaDestare AND ti_plca LIKE @placa "
               + " UNION "
               + " SELECT TOP 4000 \n" +
                    "           PRODUCTO_CODIGO=ar_cdgo,--1 \n" +
                    "           PRODUCTO=ar_nmbre, --2\n" +
                    "           CLIENTE_CODIGO=cl_cdgo, --3 \n" +
                    "           CLIENTE=cl_nmbre,  --4\n" +
                    "           TRANSPORTADORA_CODIGO=[ti_trnsprtdra],--5 \n" +
                    "           TRANSPORTADORA_NOMBRE=tr_nmbre,--6 \n" +
                    "           ORDEN=[ti_orden],--7\n" +
                    "		DEPOSITO=[ti_dpsto], --8\n" +
                    "		CONSECUTIVO=[ti_cnsctvo],--9\n" +
                    "           PLACA=[ti_plca], --10\n" +
                    "           PESO_VACIO=[ti_pso_entrda], --11\n" +
                    "		PESO_LLENO=[ti_pso_slda], --12\n" +
                    "		PESO_NETO=[ti_pso_nto],--13\n" +
                    "           FECHA_ENTRADA=[ti_fcha_entrda],--14\n" +
                    "		FECHA_SALIDA=[ti_fcha_slda],--15\n" +
                    "           MN_CODIGO=mo_cdgo, --16\n" +
                    "           MN=mo_nmbre ,--17\n" +
                    "		FECHA_TIQUETE=  [ti_fcha], --18\n" +
                    "		2 AS db --19\n" +
                    "      ,[ti_cncpto]\n" +
                    "      ,[ti_orden_intrna]\n" +
                    "      ,[ti_slctud_rtro]\n" +
                    "      ,[ti_trnsprtdra]\n" +
                    "      ,[ti_cnfgrcion_vhclar]\n" +
                    "      ,[ti_cdla]\n" +
                    "      ,[ti_nmbre_cndctor]\n" +
                    "      ,[ti_bdga]\n" +
                    "      ,[ti_pso_entrda]\n" +
                    "      ,[ti_pso_slda]\n" +
                    "      ,[ti_pso_auxliar]\n" +
                    "      ,[ti_pso_empque]\n" +
                    "      ,[ti_pso_nto]\n" +
                    "      ,[ti_unddes]\n" +
                    "      ,[ti_unddes_a_dspchar]\n" +
                    "      ,[ti_bscla_entrda]\n" +
                    "      ,[ti_bscla_slda]\n" +
                    "      ,[ti_rmlque]\n" +
                    "      ,[ti_esctlla]\n" +
                    "      ,[ti_id_bulk]\n" +
                    "      ,[ti_mdldad_dscrgue]\n" +
                    "      ,[ti_empque]\n" +
                    "      ,[ti_id_scdad]\n" +
                    "      ,[ti_fcha_llgda]\n" +
                    "      ,[ti_cnsctvo_crte]\n" +
                    "      ,[ti_pso_entrda_mnual]\n" +
                    "      ,[ti_pso_slda_mnual]\n" +
                    "      ,[ti_obsrvcnes]\n" +
                    "      ,[ti_usrio]\n" +
                    "      ,[ti_actvo]\n" +
                    "      ,[ti_dclrcion]\n" +
                    "      ,[ti_sbrepso]\n" +
                    "      ,[ti_rgstro_drcto]\n" +
                    "      ,[ti_dfrncia_klos]\n" +
                    "      ,[ti_fcha_incio_crgue]\n" +
                    "      ,[ti_fcha_fin_crgue]\n" +
                    "      ,[ti_rgstro_imprtdo]\n" +
                    "      ,[ti_psdo_sin_rdcar]\n" +
                    "      ,[ti_fcha_entrda_scdad]\n" +
                    "      ,[ti_fcha_slda_scdad]\n" +
                    "      ,[ti_usrio_entrda_scdad]\n" +
                    "      ,[ti_usrio_slda_scdad]\n" +
                    "      ,[ti_fcha_llgda_crgue]\n" +
                    "      ,[ti_rgstro_crgue_sin_hlla]\n" +
                    "      ,[ti_envdo_sstma_dspcho]\n" +
                    "      ,[ti_bscla_dspcho]\n" +
                    "      ,[ti_pso_a_crgar]\n" +
                    "      ,[ti_idrow]\n" +
                    "      ,[ti_rmsion_cnsctvo]\n" +
                    "      ,[ti_id_trnsccion_extrna]\n" +
                    "      ,[ti_id_crre]\n" +
                    "      ,[ti_id_trnsccion_orgnal]\n" +
                    "      ,[ti_prvdor]\n" +
                    "      ,[ti_cdad]\n" +
                    "      ,[ti_plca_ocr_entrda]\n" +
                    "      ,[ti_plca_ocr_slda]\n" +
                    "      ,[ti_cmprtdo]\n" +
                    "      ,[ti_pso_a_cmbiar]\n" +
                    "      ,[ti_ptio]\n" +
                    "      ,[ti_usrio_rgstro_crgue]\n" +
                    "      ,[ti_asntdo_erp]\n" +
                    "      ,[ti_excldo_erp]\n" +
                    "      ,[ti_cnsctvo_erp]\n" +
                    "      ,[ti_arrbo_mtnve_dspcho]\n" +
                    "      ,[ti_dstnddo]\n" +
                    "      ,[ti_rgstro_exprtdo]\n" +
                    "  FROM ["+DB_OPP+"].[dbo].[tqte]\n" +
                    "  INNER JOIN ["+DB_OPP+"].[dbo].dpsto  ON de_cdgo=[ti_dpsto] \n" +
                    "                     INNER JOIN ["+DB_OPP+"].[dbo].clnte ON de_clnte= cl_cdgo \n" +
                    "                     INNER JOIN ["+DB_OPP+"].[dbo].[arrbo_mtnve] ON de_arrbo_mtnve=[am_cdgo] \n" +
                    "                     INNER JOIN ["+DB_OPP+"].[dbo].mtnve ON [am_mtnve]=mo_cdgo \n" +
                    "                                         INNER JOIN ["+DB_OPP+"].[dbo].artclo ON de_artclo = ar_cdgo \n" +
                    "                                         INNER JOIN  ["+DB_OPP+"].[dbo].trnsprtdra ON tr_cdgo=[ti_trnsprtdra] \n" +
                    "                                         INNER JOIN ["+DB_OPP+"].[dbo].cncpto ON  cn_cdgo= [ti_cncpto]  \n" +
                    " WHERE ti_fcha BETWEEN @fechaTara AND @fechaDestare AND ti_plca LIKE @placa   ORDER BY [ti_plca] ASC");
                   //queryBuscar.setString(1, DatetimeInicio);
                   //queryBuscar.setString(2, DatetimeFin);
                   //queryBuscar.setString(3, "%"+placa+"%");
                    ResultSet resultSetBuscar= queryBuscar.executeQuery();  
                int contador=0;
                try{
                    while (resultSetBuscar.next()) {
                        if(contador == 0){
                             listadoObjeto=new ArrayList();
                        }
                        contador++;
                    
                        MvtoCarbon mvtoCarbon = new MvtoCarbon();
                        mvtoCarbon.setArticulo(new Articulo(resultSetBuscar.getString(1),resultSetBuscar.getString(2),"1"));
                        mvtoCarbon.getArticulo().setBaseDatos(new BaseDatos(resultSetBuscar.getString(19)));
                        mvtoCarbon.setArticuloBaseDatos(resultSetBuscar.getString(19)); 

                        mvtoCarbon.setCliente(new Cliente(resultSetBuscar.getString(3),resultSetBuscar.getString(4),"1",0));
                        mvtoCarbon.getCliente().setBaseDatos(new BaseDatos(resultSetBuscar.getString(19)));
                        mvtoCarbon.setClienteBaseDatos(resultSetBuscar.getString(19));
                        
                        
                        mvtoCarbon.setTransportadora(new Transportadora(resultSetBuscar.getString(5),"",resultSetBuscar.getString(6),"","1"));
                        mvtoCarbon.getTransportadora().setBaseDatos(new BaseDatos(resultSetBuscar.getString(19)));
                        mvtoCarbon.setTransportadorBaseDatos(resultSetBuscar.getString(19));
                        
                        //MvtoCarbon.setOrden(resultSetBuscar.getString(7));
                        mvtoCarbon.setDeposito(resultSetBuscar.getString(8));
                        mvtoCarbon.setConsecutivo(resultSetBuscar.getString(9));
                        mvtoCarbon.setPlaca(resultSetBuscar.getString(10));
                        mvtoCarbon.setPesoVacio(resultSetBuscar.getString(11));
                        mvtoCarbon.setPesoLleno(resultSetBuscar.getString(12));
                        mvtoCarbon.setPesoNeto(resultSetBuscar.getString(13));
                        mvtoCarbon.setFechaEntradaVehiculo(resultSetBuscar.getString(14));
                        mvtoCarbon.setFecha_SalidaVehiculo(resultSetBuscar.getString(15));
                        
                        mvtoCarbon.setMotonave(new Motonave(resultSetBuscar.getString(16),resultSetBuscar.getString(17),"1"));
                        mvtoCarbon.getMotonave().setBaseDatos(new BaseDatos(resultSetBuscar.getString(19)));
                        
                        mvtoCarbon.setClienteBaseDatos(resultSetBuscar.getString(19));
                        mvtoCarbon.setTransportadorBaseDatos(resultSetBuscar.getString(19));
                        mvtoCarbon.setArticuloBaseDatos(resultSetBuscar.getString(19));
                        
                        listadoObjeto.add(mvtoCarbon);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }catch(Exception e){
            e.printStackTrace();
        }
       control_GP.cerrarConexionBaseDatos();
       return listadoObjeto;
    } 
    //Registramos El Movimiento Carbon
    public int                                  registrarMvtoCarbonCompleto_functionAdd(MvtoCarbon Objeto, ArrayList<MvtoCarbon_ListadoEquipos> listadoMvtoCarbon_ListadoEquipos, Usuario usuario   /*MvtoCarbon Objeto, AsignacionEquipo asignacionEquipo, Usuario usuario, MvtoEquipo mvtoEquipo*/) throws FileNotFoundException, UnknownHostException, SocketException, SQLException {
        int result=0;
        Conexion_DB_costos_vg control = new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        conexion= control.ConectarBaseDatos();
        if(conexion != null) {
            try {
                String namePc=new ControlDB_Config().getNamePC();
                String ipPc=new ControlDB_Config().getIpPc();
                String macPC=new ControlDB_Config().getMacAddress();
                try {
                    if(!Objeto.getArticulo().getCodigo().equals("NULL")) {
                        if (!validarExistenciaArticulo(Objeto.getArticulo())) {
                            int n = registrarArticulo(Objeto.getArticulo(), Objeto.getUsuarioRegistroMovil());
                            if (n == 1) {
                                System.out.println("Hemos registrado un articulo nuevo en el sistema");
                            }
                        }
                    }
                    if(!Objeto.getCliente().getCodigo().equals("NULL")) {
                        if (!validarExistenciaCliente(Objeto.getCliente())) {
                            int n = registrarCliente(Objeto.getCliente(), Objeto.getUsuarioRegistroMovil());
                            if (n == 1) {
                                System.out.println("Hemos registrado un nuevo cliente en el sistema");
                            }
                        }
                    }
                    if(!Objeto.getTransportadora().getCodigo().equals("NULL")) {
                        if (!validarExistenciaTransportadora(Objeto.getTransportadora())) {
                            int n = registrarTransportadora(Objeto.getTransportadora(), Objeto.getUsuarioRegistroMovil());
                            if (n == 1) {
                                System.out.println("Hemos registrado una nueva transportadora en el sistema");
                            }
                        }
                    }
                    if(!Objeto.getMotonave().getCodigo().equals("NULL")) {
                        if (!validarExistenciaMotonave(Objeto.getMotonave())) {
                            int n = registrarMotonave(Objeto.getMotonave(), Objeto.getUsuarioRegistroMovil());
                            if (n == 1) {
                                System.out.println("Hemos registrado una nueva motonave en el sistema");
                            }
                        }
                    }
                }catch(Exception e){
                    System.out.println("Error validandos datos y registrados nuevos Items, Cliente, Motonave, Transportadora, Articulo");
                }
                conexion= control.ConectarBaseDatos();
                String codigoArticulo="",codigoCliente="",codigoTransportadora="";
                String codigoArticulo_BD="",codigoCliente_BD="",codigoTransportadora_BD="";

                if(!Objeto.getArticulo().getCodigo().equals("NULL")) {
                    codigoArticulo = "'" + Objeto.getArticulo().getCodigo() + "'";
                    codigoArticulo_BD=Objeto.getArticulo().getBaseDatos().getCodigo();
                }else{
                    codigoArticulo = Objeto.getArticulo().getCodigo();
                    codigoArticulo_BD="NULL";
                }

                if(!Objeto.getCliente().getCodigo().equals("NULL")) {
                    codigoCliente = "'" + Objeto.getCliente().getCodigo() + "'";
                    codigoCliente_BD=Objeto.getArticulo().getBaseDatos().getCodigo();
                }else{
                    codigoCliente = Objeto.getCliente().getCodigo();
                    codigoCliente_BD="NULL";
                }

                if(!Objeto.getTransportadora().getCodigo().equals("NULL")) {
                    codigoTransportadora= "'" + Objeto.getTransportadora().getCodigo() + "'";
                    codigoTransportadora_BD=Objeto.getArticulo().getBaseDatos().getCodigo();
                }else{
                    codigoTransportadora = Objeto.getCliente().getCodigo();
                    codigoTransportadora_BD="NULL";
                }
                String codigoCentroAuxiliarDestino="";
                
                if(Objeto.getCentroCostoAuxiliarDestino() != null){
                   codigoCentroAuxiliarDestino=""+Objeto.getCentroCostoAuxiliarDestino().getCodigo();
                }else{
                    codigoCentroAuxiliarDestino= "NULL";
                }
               String codigoMotivoNolavadoVehiculo="NULL";
                if(Objeto.getMotivoNoLavado() != null){
                    codigoMotivoNolavadoVehiculo=""+Objeto.getMotivoNoLavado().getCodigo();
                }else{
                    codigoMotivoNolavadoVehiculo="NULL";
                }   
                
                
                System.out.println(""+
                    "INSERT INTO ["+DB+"].[dbo].[mvto_carbon](   "+
                    "       [mc_cdgo]\n" +
                    "      ,[mc_cntro_oper_cdgo]\n" +
                    "      ,[mc_cntro_cost_auxiliar_cdgo]\n" +
                    "      ,[mc_cntro_cost_cdgo]\n" +
                    "      ,[mc_labor_realizada_cdgo]\n" +
                    "      ,[mc_articulo_cdgo]\n" +
                    "      ,[mc_cliente_cdgo]\n" +
                    "      ,[mc_transprtdora_cdgo]\n" +
                    "      ,[mc_fecha]\n" +
                    "      ,[mc_num_orden]\n" +
                    "      ,[mc_deposito]\n" +
                    "      ,[mc_consecutivo_tqute]\n" +
                    "      ,[mc_placa_vehiculo]\n" +
                    "      ,[mc_peso_vacio]\n" +
                    "      ,[mc_peso_lleno]\n" +
                    "      ,[mc_peso_neto]\n" +
                    "      ,[mc_fecha_entrad]\n" +
                    "      ,[mc_fecha_salid]\n" +
                    "      ,[mc_fecha_inicio_descargue]\n" +
                    "      ,[mc_fecha_fin_descargue]\n" +
                    "      ,[mc_usuario_cdgo]\n" +
                    "      ,[mc_observ]\n" +
                    "      ,[mc_estad_mvto_carbon_cdgo]\n" +
                    "      ,[mc_conexion_peso_ccarga]\n" +
                    "      ,[mc_registro_manual]\n" +
                    "      ,[mc_usuario_registro_manual_cdgo]\n" +
                    "      ,[mc_cntro_cost_auxiliarDestino_cdgo]\n" +
                    "      ,[mc_cntro_cost_mayor_cdgo]\n" +
                    "      ,[mc_lavado_vehiculo]\n" +
                    "      ,[mc_lavado_vehiculo_observacion]\n" +
                    "      ,[mc_motivo_nolavado_vehiculo_cdgo]\n" +
                    "      ,[mc_equipo_lavado_cdgo]\n" +
                    "      ,[mc_usuario_cierre_cdgo]\n" +
                    "      ,[mc_costoLavadoVehiculo]\n" +
                    "      ,[mc_valorRecaudoEmpresa_lavadoVehiculo]\n" +
                    "      ,[mc_valorRecaudoEquipo_lavadoVehiculo]\n" +
                    "      ,[mc_cliente_base_datos_cdgo]\n" +
                    "      ,[mc_transprtdora_base_datos_cdgo]\n" +
                    "      ,[mc_articulo_base_datos_cdgo]) "
                    + " VALUES ( "
                    + "   (SELECT (CASE WHEN (MAX([mc_cdgo]) IS NULL) THEN 1 ELSE (MAX([mc_cdgo])+1) END)AS [mc_cdgo] FROM ["+DB+"].[dbo].[mvto_carbon])         --[mc_cdgo]\n" +
                    "      ,"+Objeto.getCentroOperacion().getCodigo()+"        --[mc_cntro_oper_cdgo]\n" +
                    "      , " + Objeto.getCentroCostoAuxiliar().getCodigo() +"       --[mc_cntro_cost_auxiliar_cdgo]\n" +
                    "      , NULL       --[mc_cntro_cost_cdgo]\n" +
                    "      , " + Objeto.getLaborRealizada().getCodigo() +"       --[mc_labor_realizada_cdgo]\n" +
                    "      , " + codigoArticulo + "     --[mc_articulo_cdgo]\n" +
                    "      , " + codigoCliente + "     --[mc_cliente_cdgo]\n" +
                    "      , " + codigoTransportadora+ "   --[mc_transprtdora_cdgo]\n" +
                    "      , '"+Objeto.getFechaInicioDescargue()+"'   --[mc_fecha]\n" +
                    "      , NULL   --[mc_num_orden]\n" +
                    "      , '" + Objeto.getDeposito() + "'    --[mc_deposito]\n" +
                    "      , '" + Objeto.getConsecutivo() + "'   --[mc_consecutivo_tqute]\n" +
                    "      , '" + Objeto.getPlaca() + "'   --[mc_placa_vehiculo]\n" +
                    "      , " + Objeto.getPesoVacio() + "  --[mc_peso_vacio]\n" +
                    "      , " + Objeto.getPesoLleno() + "  --[mc_peso_lleno]\n" +
                    "      , " + Objeto.getPesoNeto() + "  --[mc_peso_neto]\n" +
                    "      , '"+Objeto.getFechaEntradaVehiculo()+"'  --[mc_fecha_entrad]\n" +
                    "      , '"+Objeto.getFecha_SalidaVehiculo()+"'  --[mc_fecha_salid]\n" +
                    "      , '" +Objeto.getFechaInicioDescargue()+ "'  --[mc_fecha_inicio_descargue]\n" +
                    "      , '" +Objeto.getFechaFinDescargue()+ "'  --[mc_fecha_fin_descargue]\n" +
                    "      , '"+Objeto.getUsuarioRegistroMovil().getCodigo()+"'  --[mc_usuario_cdgo]\n" +
                    "      , '"+Objeto.getObservacion()+"'  --[mc_observ]\n" +
                    "      , "+Objeto.getEstadoMvtoCarbon().getCodigo()+"  --[mc_estad_mvto_carbon_cdgo]\n" +
                    "      , 1 --[mc_conexion_peso_ccarga]\n" +
                    "      , 1 --[mc_registro_manual]\n" +
                    "      , '"+usuario.getCodigo()+"'--[mc_usuario_registro_manual_cdgo]\n" +
                    "      , "+codigoCentroAuxiliarDestino+"  --[mc_cntro_cost_auxiliarDestino_cdgo]\n" +
                    "      , NULL --[mc_cntro_cost_mayor_cdgo]\n" +
                    "      , "+Objeto.getLavadoVehiculo()+" --[mc_lavado_vehiculo]\n" +
                    "      , '"+Objeto.getLavadoVehiculo_Observacion()+"' --[mc_lavado_vehiculo_observacion]\n" +
                    "      , "+codigoMotivoNolavadoVehiculo+" --[mc_motivo_nolavado_vehiculo_cdgo]\n" +
                    "      , "+Objeto.getEquipoLavadoVehiculo().getCodigo()+ " --[mc_equipo_lavado_cdgo]\n" +
                    "      , '"+Objeto.getUsuarioCierraRegistro().getCodigo()+"' --[mc_usuario_cierre_cdgo]\n" +
                    "      , NULL --[mc_costoLavadoVehiculo]\n" +
                    "      , NULL --[mc_valorRecaudoEmpresa_lavadoVehiculo]\n" +
                    "      , NULL--[mc_valorRecaudoEquipo_lavadoVehiculo]\n" +
                    "      , "+codigoCliente_BD+"  --[mc_cliente_base_datos_cdgo]\n" +
                    "      , "+codigoTransportadora_BD+" --[mc_transprtdora_base_datos_cdgo]\n" +
                    "      , "+codigoArticulo_BD+"  --[mc_articulo_base_datos_cdgo]\n);");
                
                PreparedStatement registrarMvtoCarbon = conexion.prepareStatement(""+
                    "INSERT INTO ["+DB+"].[dbo].[mvto_carbon](   "+
                    "       [mc_cdgo]\n" +
                    "      ,[mc_cntro_oper_cdgo]\n" +
                    "      ,[mc_cntro_cost_auxiliar_cdgo]\n" +
                    "      ,[mc_cntro_cost_cdgo]\n" +
                    "      ,[mc_labor_realizada_cdgo]\n" +
                    "      ,[mc_articulo_cdgo]\n" +
                    "      ,[mc_cliente_cdgo]\n" +
                    "      ,[mc_transprtdora_cdgo]\n" +
                    "      ,[mc_fecha]\n" +
                    "      ,[mc_num_orden]\n" +
                    "      ,[mc_deposito]\n" +
                    "      ,[mc_consecutivo_tqute]\n" +
                    "      ,[mc_placa_vehiculo]\n" +
                    "      ,[mc_peso_vacio]\n" +
                    "      ,[mc_peso_lleno]\n" +
                    "      ,[mc_peso_neto]\n" +
                    "      ,[mc_fecha_entrad]\n" +
                    "      ,[mc_fecha_salid]\n" +
                    "      ,[mc_fecha_inicio_descargue]\n" +
                    "      ,[mc_fecha_fin_descargue]\n" +
                    "      ,[mc_usuario_cdgo]\n" +
                    "      ,[mc_observ]\n" +
                    "      ,[mc_estad_mvto_carbon_cdgo]\n" +
                    "      ,[mc_conexion_peso_ccarga]\n" +
                    "      ,[mc_registro_manual]\n" +
                    "      ,[mc_usuario_registro_manual_cdgo]\n" +
                    "      ,[mc_cntro_cost_auxiliarDestino_cdgo]\n" +
                    "      ,[mc_cntro_cost_mayor_cdgo]\n" +
                    "      ,[mc_lavado_vehiculo]\n" +
                    "      ,[mc_lavado_vehiculo_observacion]\n" +
                    "      ,[mc_motivo_nolavado_vehiculo_cdgo]\n" +
                    "      ,[mc_equipo_lavado_cdgo]\n" +
                    "      ,[mc_usuario_cierre_cdgo]\n" +
                    "      ,[mc_costoLavadoVehiculo]\n" +
                    "      ,[mc_valorRecaudoEmpresa_lavadoVehiculo]\n" +
                    "      ,[mc_valorRecaudoEquipo_lavadoVehiculo]\n" +
                    "      ,[mc_cliente_base_datos_cdgo]\n" +
                    "      ,[mc_transprtdora_base_datos_cdgo]\n" +
                    "      ,[mc_articulo_base_datos_cdgo]) "
                    + " VALUES ( "
                    + "   (SELECT (CASE WHEN (MAX([mc_cdgo]) IS NULL) THEN 1 ELSE (MAX([mc_cdgo])+1) END)AS [mc_cdgo] FROM ["+DB+"].[dbo].[mvto_carbon])         --[mc_cdgo]\n" +
                    "      ,"+Objeto.getCentroOperacion().getCodigo()+"        --[mc_cntro_oper_cdgo]\n" +
                    "      , " + Objeto.getCentroCostoAuxiliar().getCodigo() +"       --[mc_cntro_cost_auxiliar_cdgo]\n" +
                    "      , NULL       --[mc_cntro_cost_cdgo]\n" +
                    "      , " + Objeto.getLaborRealizada().getCodigo() +"       --[mc_labor_realizada_cdgo]\n" +
                    "      , " + codigoArticulo + "     --[mc_articulo_cdgo]\n" +
                    "      , " + codigoCliente + "     --[mc_cliente_cdgo]\n" +
                    "      , " + codigoTransportadora+ "   --[mc_transprtdora_cdgo]\n" +
                    "      , '"+Objeto.getFechaInicioDescargue()+"'   --[mc_fecha]\n" +
                    "      , NULL   --[mc_num_orden]\n" +
                    "      , '" + Objeto.getDeposito() + "'    --[mc_deposito]\n" +
                    "      , '" + Objeto.getConsecutivo() + "'   --[mc_consecutivo_tqute]\n" +
                    "      , '" + Objeto.getPlaca() + "'   --[mc_placa_vehiculo]\n" +
                    "      , " + Objeto.getPesoVacio() + "  --[mc_peso_vacio]\n" +
                    "      , " + Objeto.getPesoLleno() + "  --[mc_peso_lleno]\n" +
                    "      , " + Objeto.getPesoNeto() + "  --[mc_peso_neto]\n" +
                    "      , '"+Objeto.getFechaEntradaVehiculo()+"'  --[mc_fecha_entrad]\n" +
                    "      , '"+Objeto.getFecha_SalidaVehiculo()+"'  --[mc_fecha_salid]\n" +
                    "      , '" +Objeto.getFechaInicioDescargue()+ "'  --[mc_fecha_inicio_descargue]\n" +
                    "      , '" +Objeto.getFechaFinDescargue()+ "'  --[mc_fecha_fin_descargue]\n" +
                    "      , '"+Objeto.getUsuarioRegistroMovil().getCodigo()+"'  --[mc_usuario_cdgo]\n" +
                    "      , '"+Objeto.getObservacion()+"'  --[mc_observ]\n" +
                    "      , "+Objeto.getEstadoMvtoCarbon().getCodigo()+"  --[mc_estad_mvto_carbon_cdgo]\n" +
                    "      , 1 --[mc_conexion_peso_ccarga]\n" +
                    "      , 1 --[mc_registro_manual]\n" +
                    "      , '"+usuario.getCodigo()+"'--[mc_usuario_registro_manual_cdgo]\n" +
                    "      , "+codigoCentroAuxiliarDestino+"  --[mc_cntro_cost_auxiliarDestino_cdgo]\n" +
                    "      , NULL --[mc_cntro_cost_mayor_cdgo]\n" +
                    "      , "+Objeto.getLavadoVehiculo()+" --[mc_lavado_vehiculo]\n" +
                    "      , '"+Objeto.getLavadoVehiculo_Observacion()+"' --[mc_lavado_vehiculo_observacion]\n" +
                    "      , "+codigoMotivoNolavadoVehiculo+" --[mc_motivo_nolavado_vehiculo_cdgo]\n" +
                    "      , "+Objeto.getEquipoLavadoVehiculo().getCodigo()+ " --[mc_equipo_lavado_cdgo]\n" +
                    "      , '"+Objeto.getUsuarioCierraRegistro().getCodigo()+"' --[mc_usuario_cierre_cdgo]\n" +
                    "      , NULL --[mc_costoLavadoVehiculo]\n" +
                    "      , NULL --[mc_valorRecaudoEmpresa_lavadoVehiculo]\n" +
                    "      , NULL--[mc_valorRecaudoEquipo_lavadoVehiculo]\n" +
                    "      , "+codigoCliente_BD+"  --[mc_cliente_base_datos_cdgo]\n" +
                    "      , "+codigoTransportadora_BD+" --[mc_transprtdora_base_datos_cdgo]\n" +
                    "      , "+codigoArticulo_BD+"  --[mc_articulo_base_datos_cdgo]\n);");
                registrarMvtoCarbon.execute();
                result=1;
                if(result==1){
                    result=0;
                    //Sacamos el ultimo valor 
                    PreparedStatement queryMaximo= conexion.prepareStatement("SELECT MAX(mc_cdgo) FROM ["+DB+"].[dbo].[mvto_carbon];");
                    ResultSet resultSetMaximo= queryMaximo.executeQuery();
                    while(resultSetMaximo.next()){ 
                        if(resultSetMaximo.getString(1) != null){
                            Objeto.setCodigo(resultSetMaximo.getString(1));
                        }
                    }
                    result=1;
                    int contador=0;
                    for(MvtoCarbon_ListadoEquipos mvtoCarbon_ListadoEquipos: listadoMvtoCarbon_ListadoEquipos){
                        mvtoCarbon_ListadoEquipos.setMvtoCarbon(Objeto);
                        String codigoUsuarioAutorizaRecobro="NULL";
                        String codigoautorizacionRecobro="NULL";
                        if(mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getUsuarioAutorizaRecobro() != null){
                            codigoUsuarioAutorizaRecobro= "'"+mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getUsuarioAutorizaRecobro().getCodigo()+"'";
                            codigoautorizacionRecobro="1";
                        }else{
                            codigoUsuarioAutorizaRecobro="NULL";
                            codigoautorizacionRecobro="NULL";
                        }
                        
                        String codigoCentroCostoAuxiliarDestino_MvtoEquipo= "NULL";
                        if(mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getCentroCostoAuxiliarDestino() != null){
                            codigoCentroCostoAuxiliarDestino_MvtoEquipo=""+mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getCentroCostoAuxiliarDestino().getCodigo();
                        }else{
                            codigoCentroCostoAuxiliarDestino_MvtoEquipo="NULL";
                        }
                        
                        PreparedStatement registrarMvtoEquipo = conexion.prepareStatement(
                            " INSERT INTO ["+DB+"].[dbo].[mvto_equipo] ("+
                            "       [me_cdgo]\n" +
                            "      ,[me_asignacion_equipo_cdgo]\n" +
                            "      ,[me_fecha]\n" +
                            "      ,[me_proveedor_equipo_cdgo]\n" +
                            "      ,[me_num_orden]\n" +
                            "      ,[me_cntro_oper_cdgo]\n" +
                            "      ,[me_cntro_cost_auxiliar_cdgo]\n" +
                            "      ,[me_cntro_cost_cdgo]\n" +
                            "      ,[me_labor_realizada_cdgo]\n" +
                            "      ,[me_cliente_cdgo]\n" +
                            "      ,[me_articulo_cdgo]\n" +
                            "      ,[me_motonave_cdgo]\n" +
                            "      ,[me_fecha_hora_inicio]\n" +
                            "      ,[me_fecha_hora_fin]\n" +
                            "      ,[me_total_minutos]\n" +
                            "      ,[me_valor_hora]\n" +
                            "      ,[me_costo_total]\n" +
                            "      ,[me_recobro_cdgo]\n" +
                            "      ,[me_cliente_recobro_cdgo]\n" +
                            "      ,[me_costo_total_recobro_cliente]\n" +
                            "      ,[me_usuario_registro_cdgo]\n" +
                            "      ,[me_usuario_autorizacion_cdgo]\n" +
                            "      ,[me_autorizacion_recobro_cdgo]\n" +
                            "      ,[me_observ_autorizacion]\n" +
                            "      ,[me_inactividad]\n" +
                            "      ,[me_causa_inactividad_cdgo]\n" +
                            "      ,[me_usuario_inactividad_cdgo]\n" +
                            "      ,[me_motivo_parada_estado]\n" +
                            "      ,[me_motivo_parada_cdgo]\n" +
                            "      ,[me_observ]\n" +
                            "      ,[me_estado]\n" +
                            "      ,[me_desde_mvto_carbon]\n" +
                            "      ,[me_cntro_cost]\n" +
                            "      ,[me_cntro_cost_auxiliarDestino_cdgo]\n" +
                            "      ,[me_cntro_cost_mayor_cdgo]\n" +
                            "      ,[me_usuario_cierre_cdgo]\n" +
                            "      ,[me_cliente_base_datos_cdgo]\n" +
                            "      ,[me_motonave_base_datos_cdgo]\n" +
                            "      ,[me_articulo_base_datos_cdgo])"+
                            " VALUES ("+
                        "       (SELECT (CASE WHEN (MAX([me_cdgo]) IS NULL) THEN 1 ELSE (MAX([me_cdgo])+1) END)AS [mc_cdgo] FROM ["+DB+"].[dbo].[mvto_equipo])  --[me_cdgo]\n" +
                        "      ,"+mvtoCarbon_ListadoEquipos.getAsignacionEquipo().getCodigo()+"  --[me_asignacion_equipo_cdgo]\n" +
                        "      ,'"+mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getFechaHoraInicio()+"'  --[me_fecha]\n" +
                        "      ,'"+mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getProveedorEquipo().getCodigo()+"'  --[me_proveedor_equipo_cdgo]\n" +
                        "      , NULL --[me_num_orden]\n" +
                        "      , "+mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getCentroOperacion().getCodigo()+" --[me_cntro_oper_cdgo]\n" +
                        "      ,"+mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getCentroCostoAuxiliar().getCodigo()+"  --[me_cntro_cost_auxiliar_cdgo]\n" +
                        "      ,NULL  --[me_cntro_cost_cdgo]\n" +
                        "      ,"+mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getLaborRealizada().getCodigo()+"  --[me_labor_realizada_cdgo]\n" +
                        "      , '"+mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getCliente().getCodigo()+"' --[me_cliente_cdgo]\n" +
                        "      ,'"+Objeto.getArticulo().getCodigo()+"'  --[me_articulo_cdgo]\n" +
                        "      , NULL --[me_motonave_cdgo]\n" +
                        "      ,'"+mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getFechaHoraInicio()+"'  --[me_fecha_hora_inicio]\n" +
                        "      , '"+mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getFechaHoraFin()+"' --[me_fecha_hora_fin]\n" +
                        "      , (SELECT DATEDIFF(minute, '"+mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getFechaHoraInicio()+"', '"+mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getFechaHoraFin()+"')) --[me_total_minutos]\n" +
                        "      ,  NULL--[me_valor_hora]\n" +
                        "      ,  NULL--[me_costo_total]\n" +
                        "      , "+mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getRecobro().getCodigo()+" --[me_recobro_cdgo]\n" +
                        "      , NULL --[me_cliente_recobro_cdgo]\n" +
                        "      , NULL --[me_costo_total_recobro_cliente]\n" +
                        "      , '"+mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getUsuarioQuieRegistra().getCodigo()+"' --[me_usuario_registro_cdgo]\n" +
                        "      , "+codigoUsuarioAutorizaRecobro+" --[me_usuario_autorizacion_cdgo]\n" +
                        "      , "+codigoautorizacionRecobro+" --[me_autorizacion_recobro_cdgo]\n" +
                        "      , NULL --[me_observ_autorizacion]\n" +
                        "      ,0  --[me_inactividad]\n" +
                        "      ,NULL  --[me_causa_inactividad_cdgo]\n" +
                        "      ,NULL  --[me_usuario_inactividad_cdgo]\n" +
                        "      ,1  --[me_motivo_parada_estado]\n" +
                        "      , "+mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getMotivoParada().getCodigo()+" --[me_motivo_parada_cdgo]\n" +
                        "      , NULL --[me_observ]\n" +
                        "      ,"+mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getEstado()+"  --[me_estado]\n" +
                        "      ,1  --[me_desde_mvto_carbon]\n" +
                        "      , NULL       --[me_cntro_cost]\n" +
                        "      ,"+codigoCentroCostoAuxiliarDestino_MvtoEquipo+"  --[me_cntro_cost_auxiliarDestino_cdgo]\n" +
                        "      , NULL       --[me_cntro_cost_mayor_cdgo]\n" +
                        "      ,'"+mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getUsuarioQuienCierra().getCodigo()+"'  --[me_usuario_cierre_cdgo]\n" +
                        "      ,"+codigoCliente_BD+"  --[me_cliente_base_datos_cdgo]\n" +
                        "      ,NULL  --[me_motonave_base_datos_cdgo]\n" +
                        "      , "+codigoArticulo_BD+" --[me_articulo_base_datos_cdgo]\n);");  

                        registrarMvtoEquipo.execute();  
                        //Sacamos el ultimo valor 
                        PreparedStatement queryMaximo_MvtoEquipo= conexion.prepareStatement("SELECT MAX(me_cdgo) FROM ["+DB+"].[dbo].[mvto_equipo];");
                        ResultSet resultSetMaximo_MvtoEquipo= queryMaximo_MvtoEquipo.executeQuery();
                        while(resultSetMaximo_MvtoEquipo.next()){ 
                            if(resultSetMaximo_MvtoEquipo.getString(1) != null){
                                mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().setCodigo(resultSetMaximo_MvtoEquipo.getString(1));
                                listadoMvtoCarbon_ListadoEquipos.get(contador).getMvtoEquipoAgregar().setCodigo(resultSetMaximo_MvtoEquipo.getString(1));
                            }
                        }
                        result=1;
                        if(result==1){
                            PreparedStatement registrarMvtoCarbonListadoEquipo = conexion.prepareStatement(" "
                                     + "INSERT INTO ["+DB+"].[dbo].[mvto_carbon_listado_equipo]\n" +
                                        "                                       ([mcle_cdgo]\n" +
                                        "                                       ,[mcle_mvto_carbon_cdgo]\n" +
                                        "                                       ,[mcle_asignacion_equipo_cdgo]\n" +
                                        "                                      ,[mcle_mvto_equipo_cdgo]\n" +
                                        "                                       ,[mcle_estado])\n" +
                                        "                                 VALUES\n" +
                                        "                                       ((SELECT (CASE WHEN (MAX([mcle_cdgo]) IS NULL)\n" +
                                        "                                                         THEN 1 ELSE (MAX([mcle_cdgo])+1) END)AS [mcle_cdgo] \n" +
                                        "                                                        FROM ["+DB+"].[dbo].[mvto_carbon_listado_equipo]) --mcle_cdgo\n" +
                                        "                                       , "+Objeto.getCodigo()+" --mcle_mvto_carbon_cdgo\n" +
                                        "                                       , "+mvtoCarbon_ListadoEquipos.getAsignacionEquipo().getCodigo()+" --mcle_asignacion_equipo_cdgo\n" +
                                        "                                       , "+mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar().getCodigo()+"--mcle_mvto_equipo_cdgo\n" +
                                        "                                       , 1 --mcle_estado\n" +
                                        "                            );\n");

                            registrarMvtoCarbonListadoEquipo.execute();
                        }
                        
                      contador++; 
                    }
                    
                    PreparedStatement Query_Auditoria_AgregarMvtoCarbon= conexion.prepareStatement(""+
                                "INSERT INTO ["+DB+"].[dbo].[auditoria]("+
                                    "[au_cdgo]    "+
                                    ",[au_fecha]"+
                                    ",[au_usuario_cdgo_registro] "+
                                    ",[au_nombre_dispositivo_registro]\n" +
                                    ",[au_ip_dispositivo_registro]"+
                                    ",[au_mac_dispositivo_registro]"+
                                    ",[au_cdgo_mtvo]"+
                                    ",[au_desc_mtvo]\n"+
                                    ",[au_detalle_mtvo])" +
                                "   VALUES("+
                                    " (SELECT (CASE WHEN (MAX([au_cdgo]) IS NULL) THEN 1 ELSE (MAX([au_cdgo])+1) END)AS [au_cdgo] FROM ["+DB+"].[dbo].[auditoria])" +
                                    ",(SELECT SYSDATETIME()) ,?,?,?,?,?,'DESCARGUE_CARBON',CONCAT (?,?,?));");
                    Query_Auditoria_AgregarMvtoCarbon.setString(1, Objeto.getUsuarioRegistroMovil().getCodigo());
                    Query_Auditoria_AgregarMvtoCarbon.setString(2, namePc);
                    Query_Auditoria_AgregarMvtoCarbon.setString(3, ipPc);
                    Query_Auditoria_AgregarMvtoCarbon.setString(4, macPC);
                    Query_Auditoria_AgregarMvtoCarbon.setString(5, Objeto.getCodigo());
                    Query_Auditoria_AgregarMvtoCarbon.setString(6, "Se registró un nuevo Movimiento de Carbon en el sistema desde un Dispositivo Movil, con código: ");
                    Query_Auditoria_AgregarMvtoCarbon.setString(7, Objeto.getCodigo());
                    Query_Auditoria_AgregarMvtoCarbon.setString(8, " PLACA: " + Objeto.getPlaca() + " Orden: " + Objeto.getNumero_orden() + " Deposito: " + Objeto.getDeposito() + " Articulo: " + Objeto.getArticulo().getDescripcion() + " Peso Vacio: " + Objeto.getPesoVacio());
                    Query_Auditoria_AgregarMvtoCarbon.execute();
                    
                    /*if(listadoMvtoCarbon_ListadoEquipos!=null){
                        for(MvtoCarbon_ListadoEquipos mvtoCarbon_ListadoEquipos: listadoMvtoCarbon_ListadoEquipos){
                            mvtoCarbon_ListadoEquipos.setMvtoEquipo(mvtoCarbon_ListadoEquipos.getMvtoEquipoAgregar());
                            new ControlDB_MvtoCarbon(tipoConexion).ProcesarEnCcargaGP(mvtoCarbon_ListadoEquipos,usuario);
                        } 
                    }¨*/
                    
                    result=1;
                    System.out.println("Voy a ProcesarEnCcarga");
                    if(result==1){
                        try{
                            ArrayList<MvtoCarbon_ListadoEquipos> mvtoCarbon_ListadoEquiposTemp= new ControlDB_MvtoCarbon_backup(tipoConexion).buscarMvtoCarbonParticular(Objeto);
                            for(MvtoCarbon_ListadoEquipos listado : mvtoCarbon_ListadoEquiposTemp){
                                new ControlDB_MvtoCarbon_backup(tipoConexion).ProcesarEnCcargaGP(listado,usuario);
                            }
                           result=1;   
                        }catch(Exception e){
                            result=0;
                        }
                    }     
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
    //Agregar MvtoCarbón
    public boolean                              validar2FechasEnRango(String fecha1, String fecha2, String rangoFecha1, String rangoFecha2){
        /*****
         Metodo que permite validar si dos fechas entan dentro del rango de fecha de otras dos fechas.   
        ****/
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("DECLARE @fechaTara SMALLDATETIME;\n" +
                                                                "DECLARE @fechaDestare SMALLDATETIME;\n" +
                                                                "DECLARE @fechaInicioDescargue DATETIME;\n" +
                                                                "DECLARE @fechaFinDescargue DATETIME;\n" +
                                                                "\n" +
                                                                "SET @fechaTara=?;\n" +
                                                                "SET @fechaDestare=?;\n" +
                                                                "SET @fechaInicioDescargue=?;\n" +
                                                                "SET @fechaFinDescargue=?;\n" +
                                                                "\n" +
                                                                "  SELECT CASE\n" +
                                                                "		WHEN (@fechaInicioDescargue BETWEEN @fechaTara AND @fechaDestare) AND (@fechaFinDescargue BETWEEN @fechaTara AND @fechaDestare) THEN 'TRUE'\n" +
                                                                "			ELSE 'FALSE'\n" +
                                                                "		END ;");
            query.setString(1, rangoFecha1);
            query.setString(2, rangoFecha2);
            query.setString(3, fecha1);
            query.setString(4, fecha2);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){
                String data =resultSet.getString(1);
                if(data.equals("TRUE")){
                    retorno =true;
                }else{
                    if(data.equals("FALSE")){
                        retorno =false;
                    }
                }
                
            }
        }catch (SQLException sqlException){
            System.out.println("Error al tratar de Buscar la Existencia del Cliente");
            sqlException.printStackTrace();
            retorno=false;
        }
        control.cerrarConexionBaseDatos();
        return retorno;
    }
}
