package ModuloPalero.Controller;
  
import Catalogo.Controller.ControlDB_Equipo;
import ConnectionDB.Conexion_DB_costos_vg;
import Catalogo.Model.Articulo;
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import Catalogo.Model.Cliente;
import Catalogo.Model.Compañia;
import Catalogo.Model.Equipo;
import Catalogo.Model.LaborRealizada;
import Catalogo.Model.TipoEquipo;
import Catalogo.Model.Transportadora;
import ModuloCarbon.Model.MvtoCarbon;
import ModuloEquipo.Model.AsignacionEquipo;
import ModuloEquipo.Model.MvtoEquipo;
import ModuloPalero.Model.ConfiguracionLiquidacion;
import ModuloPalero.Model.ConsolidadoLiquidacion;
import ModuloPalero.Model.Liquidacion;
import ModuloPalero.Model.MvtoCarbon_ListadoEquipos_LiquidacionPaleros;
import ModuloPalero.Model.MvtoPaleroPreliquidacionTEMP;
import ModuloPalero.Model.PlantillaArchivoLiquidacion;
import ModuloPersonal.Model.CargoNomina;
import ModuloPersonal.Model.Persona;
import ModuloPersonal.Model.TipoContrato;
import ModuloPersonal.Model.TipoDocumento;
import Sistema.Controller.ControlDB_Config;
import Sistema.Model.Usuario;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControlDB_LiquidacionPalero {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private final String tipoConexion;
    
    public ControlDB_LiquidacionPalero(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }  
    public ArrayList<MvtoCarbon_ListadoEquipos_LiquidacionPaleros>     buscarVehiculosPorEquipoLiquidacion (ConfiguracionLiquidacion confLiquidacion) throws SQLException{
        ArrayList<MvtoCarbon_ListadoEquipos_LiquidacionPaleros> listadoObjetos = null;
        ArrayList<String> listadoCodigoMvtoCarbon= null;
        ArrayList<String> listadoTaraVehiculo= null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  [mcle_cdgo]--1\n" +
                                                "			,[mc_cdgo]--2\n" +
                                                "				  ,[co_desc]--3\n" +
                                                "						,[ccs_desc]--4\n" +
                                                "				  ,[cca_desc]--5\n" +
                                                "					,[lr_desc]--6\n" +
                                                "				  ,[ar_desc]--7\n" +
                                                "					,[cl_cdgo]--8\n" +
                                                "				  ,[cl_desc]--9\n" +
                                                "				  ,[tr_cdgo]--10\n" +
                                                "				  ,[tr_nit]--11\n" +
                                                "				  ,[tr_desc]--12\n" +
                                                "			  ,[mc_fecha]--13\n" +
                                                "			  ,YEAR([mc_fecha]) AS ANO--14\n" +
                                                "			  --, DATENAME(MONTH,[mc_fecha]) AS MES--15\n"
                                                + "                         ,CASE MONTH([mc_fecha])\n" +
                                                "					WHEN 01 THEN 'ENERO'\n" +
                                                "					WHEN 02 THEN 'FEBRERO'\n" +
                                                "					WHEN 03 THEN 'MARZO'\n" +
                                                "					WHEN 04 THEN 'ABRIL'\n" +
                                                "					WHEN 05 THEN 'MAYO'\n" +
                                                "					WHEN 06 THEN 'JUNIO'\n" +
                                                "					WHEN 07 THEN 'JULIO'\n" +
                                                "					WHEN 08 THEN 'AGOSTO'\n" +
                                                "					WHEN 09 THEN 'SEPTIEMBRE'\n" +
                                                "					WHEN 10 THEN 'OCTUBRE'\n" +
                                                "					WHEN 11 THEN 'NOVIEMBRE'\n" +
                                                "					WHEN 12 THEN 'DICIEMBRE' END AS MES --15\n" +
                                                "			  ,DAY([mc_fecha]) AS DIA--16\n" +
                                                "			  ,[mc_num_orden]--17\n" +
                                                "			  ,[mc_deposito]--18\n" +
                                                "			  ,[mc_consecutivo_tqute]--19\n" +
                                                "			  ,[mc_placa_vehiculo]--20\n" +
                                                "			  ,[mc_peso_vacio]--21\n" +
                                                "			  ,[mc_peso_lleno]--22\n" +
                                                "			  ,[mc_peso_neto]--23\n" +
                                                "			  ,[mc_fecha_entrad]--24\n" +
                                                "			  ,[mc_fecha_salid]--25\n" +
                                                "			  ,[mc_fecha_inicio_descargue]--26\n" +
                                                "			  ,[mc_fecha_fin_descargue]--27\n" +
                                                "				,mc_usuario_registra.[us_cdgo]--28\n" +
                                                "				,mc_usuario_registra.[us_nombres]--29\n" +
                                                "				,mc_usuario_registra.[us_apellidos]--30\n" +
                                                "				,mc_usuario_registra.[us_correo]--31\n" +
                                                "						,mc_usuario_cierra.[us_cdgo]--32\n" +
                                                "					  ,mc_usuario_cierra.[us_nombres]--33\n" +
                                                "					  ,mc_usuario_cierra.[us_apellidos]--34\n" +
                                                "					  ,mc_usuario_cierra.[us_correo]--35\n" +
                                                "      ,[mcle_asignacion_equipo_cdgo]--36\n" +
                                                "      ,[mcle_mvto_equipo_cdgo]--37\n" +
                                                "				,[me_cdgo]--38\n" +
                                                "			  ,[me_asignacion_equipo_cdgo]--39\n" +
                                                "				,[ae_cdgo]--40\n" +
                                                "				   ,[ae_equipo_cdgo]--41\n" +
                                                "				  --Equipos de liquidacion\n" +
                                                "					,[el_cdgo] --42\n" +
                                                "					,[el_equipo_cdgo]--43\n" +
                                                "						  ,[eq_cdgo]--44\n" +
                                                "						  ,[eq_marca]--45\n" +
                                                "						  ,[eq_modelo]--46\n" +
                                                "						  ,[eq_desc]--47\n" +
                                                "      ,[mcle_estado]--48\n" +
                                                "  FROM ["+DB+"].[dbo].[mvto_carbon_listado_equipo]\n" +
                                                "	  INNER JOIN ["+DB+"].[dbo].[mvto_carbon] ON [mcle_mvto_carbon_cdgo]=[mc_cdgo]\n" +
                                                "	  INNER JOIN ["+DB+"].[dbo].[mvto_equipo] ON [mcle_mvto_equipo_cdgo]= [me_cdgo]\n" +
                                                "	  INNER JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [me_asignacion_equipo_cdgo]=[ae_cdgo]\n" +
                                                "	  INNER JOIN ["+DB+"].[dbo].[equipos_liquidacion] ON [ae_equipo_cdgo]=[el_equipo_cdgo] AND [el_estad]=1\n" +
                                                "	  INNER JOIN ["+DB+"].[dbo].[equipo] ON [el_equipo_cdgo]=[eq_cdgo]\n" +
                                                "	  INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [mc_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                "	  INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [mc_cntro_cost_auxiliar_cdgo]=[cca_cdgo]\n" +
                                                "	  INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
                                                "	  INNER JOIN ["+DB+"].[dbo].[cliente] ON [mc_cliente_cdgo]= [cl_cdgo] AND  [mc_cliente_base_datos_cdgo]= [cl_base_datos_cdgo]\n" +
                                                "	  INNER JOIN ["+DB+"].[dbo].[articulo] ON [mc_articulo_cdgo] = [ar_cdgo] AND [ar_base_datos_cdgo]=[mc_articulo_base_datos_cdgo]\n" +
                                                "	  INNER JOIN ["+DB+"].[dbo].[transprtdora] ON [mc_transprtdora_cdgo]= [tr_cdgo] AND [tr_base_datos_cdgo]= [mc_transprtdora_base_datos_cdgo]\n" +
                                                "	  INNER JOIN ["+DB+"].[dbo].[usuario] mc_usuario_registra ON [mc_usuario_cdgo]=mc_usuario_registra.[us_cdgo]\n" +
                                                "	  INNER JOIN ["+DB+"].[dbo].[usuario] mc_usuario_cierra ON [mc_usuario_cierre_cdgo]= mc_usuario_cierra.[us_cdgo]\n" +
                                                "	  INNER JOIN ["+DB+"].[dbo].[labor_realizada] ON [mc_labor_realizada_cdgo]=[lr_cdgo]\n" +
                                                "  WHERE [el_estad]=1 AND [mc_fecha] BETWEEN '"+confLiquidacion.getFechaInicio()+" 00:00.000' AND '"+confLiquidacion.getFechaFinalizacion()+" 23:59.999' AND [me_inactividad]=0 AND [mc_estad_mvto_carbon_cdgo]=1 order by [mc_fecha] ASC");
            ResultSet resultSet; resultSet= query.executeQuery();    
            boolean validar= true;
            while(resultSet.next()){ 
                try{
                    if(validar){
                        listadoObjetos= new ArrayList<>();
                        listadoCodigoMvtoCarbon= new  ArrayList<>();
                        listadoTaraVehiculo= new  ArrayList<>();
                        validar= false;
                    }
                    MvtoCarbon_ListadoEquipos_LiquidacionPaleros objeto = new MvtoCarbon_ListadoEquipos_LiquidacionPaleros();
                        objeto.setCodigo(resultSet.getString(1));
                        objeto.setMvtoCarbon(new MvtoCarbon());
                        objeto.getMvtoCarbon().setCodigo(resultSet.getString(2));
                        listadoCodigoMvtoCarbon.add(resultSet.getString(2));
                        
                        objeto.getMvtoCarbon().setCentroOperacion(new CentroOperacion());
                        objeto.getMvtoCarbon().getCentroOperacion().setDescripcion(resultSet.getString(3));
                        objeto.getMvtoCarbon().setCentroCostoAuxiliar(new CentroCostoAuxiliar());                    
                        objeto.getMvtoCarbon().getCentroCostoAuxiliar().setCentroCostoSubCentro(new CentroCostoSubCentro());
                        objeto.getMvtoCarbon().getCentroCostoAuxiliar().getCentroCostoSubCentro().setDescripcion(resultSet.getString(4));
                        objeto.getMvtoCarbon().getCentroCostoAuxiliar().setDescripcion(resultSet.getString(5));
                        objeto.getMvtoCarbon().setLaborRealizada(new LaborRealizada());
                        objeto.getMvtoCarbon().getLaborRealizada().setDescripcion(resultSet.getString(6));
                        objeto.getMvtoCarbon().setArticulo(new Articulo());
                        objeto.getMvtoCarbon().getArticulo().setDescripcion(resultSet.getString(7));
                        objeto.getMvtoCarbon().setCliente(new Cliente());
                        objeto.getMvtoCarbon().getCliente().setCodigo(resultSet.getString(8));
                        objeto.getMvtoCarbon().getCliente().setDescripcion(resultSet.getString(9));
                        objeto.getMvtoCarbon().setTransportadora(new Transportadora());
                        objeto.getMvtoCarbon().getTransportadora().setCodigo(resultSet.getString(10));
                        objeto.getMvtoCarbon().getTransportadora().setNit(resultSet.getString(11));
                        objeto.getMvtoCarbon().getTransportadora().setDescripcion(resultSet.getString(12));
                        objeto.getMvtoCarbon().setFechaRegistro(resultSet.getString(13));
                        objeto.setAno(resultSet.getString(14));
                        objeto.setMes(resultSet.getString(15));
                        objeto.setDia(resultSet.getString(16));
                        objeto.getMvtoCarbon().setNumero_orden(resultSet.getString(17));
                        objeto.getMvtoCarbon().setDeposito(resultSet.getString(18));
                        objeto.getMvtoCarbon().setConsecutivo(resultSet.getString(19));
                        objeto.getMvtoCarbon().setPlaca(resultSet.getString(20));
                        objeto.getMvtoCarbon().setPesoVacio(resultSet.getString(21));
                        objeto.getMvtoCarbon().setPesoLleno(resultSet.getString(22));
                        objeto.getMvtoCarbon().setPesoNeto(resultSet.getString(23));
                        objeto.getMvtoCarbon().setFechaEntradaVehiculo(resultSet.getString(24));
                        listadoTaraVehiculo.add(resultSet.getString(20)+":"+resultSet.getString(24));
                        objeto.getMvtoCarbon().setFecha_SalidaVehiculo(resultSet.getString(25));
                        objeto.getMvtoCarbon().setFechaInicioDescargue(resultSet.getString(26));
                        objeto.getMvtoCarbon().setFechaFinDescargue(resultSet.getString(27));
                        objeto.getMvtoCarbon().setUsuarioRegistroMovil(new Usuario());
                        objeto.getMvtoCarbon().getUsuarioRegistroMovil().setCodigo(resultSet.getString(28));
                        objeto.getMvtoCarbon().getUsuarioRegistroMovil().setNombres(resultSet.getString(29));
                        objeto.getMvtoCarbon().getUsuarioRegistroMovil().setApellidos(resultSet.getString(30));
                        objeto.getMvtoCarbon().getUsuarioRegistroMovil().setCorreo(resultSet.getString(31));
                        objeto.getMvtoCarbon().setUsuarioCierraRegistro(new Usuario());
                        objeto.getMvtoCarbon().getUsuarioCierraRegistro().setCodigo(resultSet.getString(32));
                        objeto.getMvtoCarbon().getUsuarioCierraRegistro().setNombres(resultSet.getString(33));
                        objeto.getMvtoCarbon().getUsuarioCierraRegistro().setApellidos(resultSet.getString(34));
                        objeto.getMvtoCarbon().getUsuarioCierraRegistro().setCorreo(resultSet.getString(35));
                        objeto.setAsignacionEquipo(new AsignacionEquipo());
                        objeto.getAsignacionEquipo().setCodigo(resultSet.getString(36));
                        objeto.setMvtoEquipo(new MvtoEquipo());
                        objeto.getMvtoEquipo().setCodigo(resultSet.getString(37));
                        objeto.getMvtoEquipo().setAsignacionEquipo(new AsignacionEquipo());
                        objeto.getMvtoEquipo().getAsignacionEquipo().setCodigo(resultSet.getString(38));
                        objeto.getMvtoEquipo().getAsignacionEquipo().setEquipo(new Equipo());
                        objeto.getMvtoEquipo().getAsignacionEquipo().getEquipo().setCodigo(resultSet.getString(44));
                        objeto.getMvtoEquipo().getAsignacionEquipo().getEquipo().setMarca(resultSet.getString(45));
                        objeto.getMvtoEquipo().getAsignacionEquipo().getEquipo().setModelo(resultSet.getString(46));
                        objeto.getMvtoEquipo().getAsignacionEquipo().getEquipo().setDescripcion(resultSet.getString(47));
                        objeto.setEstado(resultSet.getString(48));
                        if(objeto.getMvtoCarbon().getPesoNeto().equals("0")){
                            objeto.setColor("ROJO");
                        }
                    listadoObjetos.add(objeto);    
                }catch(Exception e){
                    e.printStackTrace();
                }
                int contador=0;
                //hacemos un recorrido de los datos almacenados
                for(MvtoCarbon_ListadoEquipos_LiquidacionPaleros objeto : listadoObjetos){
                    if(objeto.getColor()==null){
                        //validamos si hay un mvto_carbon para dos equipos diferente cargados
                        if(Collections.frequency(listadoCodigoMvtoCarbon, objeto.getMvtoCarbon().getCodigo())> 1){
                            objeto.setColor("ROJO");
                        }else{
                            //validamos si se agrego el mismo vehículo (placa) con la misma fecha de tara, puede ser el mismo día o en otro día diferente por la opción de agregar mvtoCarbón.
                            if(Collections.frequency(listadoTaraVehiculo, objeto.getMvtoCarbon().getPlaca()+":"+objeto.getMvtoCarbon().getFechaEntradaVehiculo())> 1){
                                objeto.setColor("ROJO");
                            }else{
                                objeto.setColor("VERDE");
                            }
                        }
                        listadoObjetos.set(contador, objeto); 
                    }
                    contador++;
                } 
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las vehículos descargados por los equipos que estan configurados para liquidación");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }
    public ArrayList<MvtoCarbon_ListadoEquipos_LiquidacionPaleros>     procesarLiquidacion (ArrayList<MvtoCarbon_ListadoEquipos_LiquidacionPaleros> listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros) throws SQLException{
        /*
            Funcionalidad:  Este metodo recibe como parámetro un listado de MvtoCarbon_ListadoEquipos_LiquidacionPaleros el cual contiene los vehículos descargados en ese periodo de tiempo de liquidación
                            cuya finalidad es validar el equipo de descargue que personas están asociada y posteriormente eliminar a las personas que no asistieron ese día.
        */
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        try{
            //Consultamos todas las personas que pertenecen a los equipos según vehiculos
            int contador =0;
            for(MvtoCarbon_ListadoEquipos_LiquidacionPaleros objeto : listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros){
               listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros.set(contador, consultarPersonasPorEquipoLiquidacion(objeto));
                contador++;
            }
            conexion= control.ConectarBaseDatos();
            for(int i=0; i < listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros.size(); i++){
                if(listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros.get(i).getMvtoEquipo().getAsignacionEquipo().getEquipo().getListadoPersonas()!= null){
                    for(int j=0; j    < listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros.get(i).getMvtoEquipo().getAsignacionEquipo().getEquipo().getListadoPersonas().size(); j++){
                        if(!new ControlDB_Marcacion(tipoConexion).validarMarcacionEquipoPersona(listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros.get(i).getMvtoEquipo().getAsignacionEquipo().getEquipo().getListadoPersonas().get(j),listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros.get(i).getMvtoCarbon().getFechaRegistro())){
                            listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros.get(i).getMvtoEquipo().getAsignacionEquipo().getEquipo().getListadoPersonas().remove(j);
                            j--;
                        }
                    }
                }else{
                    listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros.remove(i);
                    i--;
                }
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de procesar los registros");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros;
    }
    
    public MvtoCarbon_ListadoEquipos_LiquidacionPaleros     procesarLiquidacion2 (MvtoCarbon_ListadoEquipos_LiquidacionPaleros listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros) throws SQLException{
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        try{
            listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros=consultarPersonasPorEquipoLiquidacion(listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros);
            conexion= control.ConectarBaseDatos();
            if(listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros.getMvtoEquipo().getAsignacionEquipo().getEquipo().getListadoPersonas()!= null){
                for(int j=0; j    < listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros.getMvtoEquipo().getAsignacionEquipo().getEquipo().getListadoPersonas().size(); j++){
                    if(!new ControlDB_Marcacion(tipoConexion).validarMarcacionEquipoPersona(listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros.getMvtoEquipo().getAsignacionEquipo().getEquipo().getListadoPersonas().get(j),listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros.getMvtoCarbon().getFechaRegistro())){
                        listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros.getMvtoEquipo().getAsignacionEquipo().getEquipo().getListadoPersonas().remove(j);
                        j--;
                    }
                }
            }else{
                listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros= null;
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de procesar los registros");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listado_mvtoCarbon_ListadoEquipos_LiquidacionPaleros;
    }
    
    public MvtoCarbon_ListadoEquipos_LiquidacionPaleros consultarPersonasPorEquipoLiquidacion(MvtoCarbon_ListadoEquipos_LiquidacionPaleros mvtoCarbon_ListadoEquipos_LiquidacionPaleros) throws SQLException{
        ArrayList<Persona> listadoObjetos =null;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            PreparedStatement query= conexion.prepareStatement("SELECT  \n" +
                                                        "	[ps_cdgo]--1 \n" +
                                                        "              ,[ps_tipo_documento_cdgo]--2 \n" +
                                                        "        		  ,[tidoc_cdgo]--3 \n" +
                                                        "        		  ,[tidoc_desc]--4 \n" +
                                                        "        		  ,[tidoc_estad]--5 \n" +
                                                        "              ,[ps_nombre]--6 \n" +
                                                        "              ,[ps_apellidos]--7 \n" +
                                                        "              ,[ps_telefono]--8 \n" +
                                                        "              ,[ps_cargo_nomina_cdgo]--9 \n" +
                                                        "        		  ,[cn_cdgo]--10 \n" +
                                                        "        		  ,[cn_desc]--11 \n" +
                                                        "        		  ,[cn_estad]--12 \n" +
                                                        "              ,[ps_tipo_contrato_cdgo]--13 \n" +
                                                        "        		  ,[tc_cdgo]--14 \n" +
                                                        "        		  ,[tc_desc]--15 \n" +
                                                        "        		  ,[tc_estad]--16 \n" +
                                                        "              ,[ps_compania_cdgo]--17 \n" +
                                                        "        		  ,[cp_cdgo]--18 \n" +
                                                        "        		  ,[cp_desc]--19 \n" +
                                                        "        		  ,[cp_estad]--20 \n" +
                                                        "              ,[ps_equipo_cdgo]--21 \n" +
                                                        "        			,[eq_cdgo]--22 \n" +
                                                        "        	    	,[eq_marca]--23 \n" +
                                                        "        			,[eq_modelo]--24 \n" +
                                                        "        			,[eq_desc]--25 \n" +
                                                        "              ,CASE WHEN ([ps_estado]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [ps_estado]--26 \n" +
                                                        "				,[te_cdgo]--27 \n" +
                                                        "			,[te_desc]--28 \n" +
                                                        "FROM ["+DB+"].[dbo].[mvto_carbon_listado_equipo]\n" +
                                                        "    INNER JOIN ["+DB+"].[dbo].[mvto_carbon] ON [mcle_mvto_carbon_cdgo]=[mc_cdgo]\n" +
                                                        "	INNER JOIN ["+DB+"].[dbo].[mvto_equipo] ON [mcle_mvto_equipo_cdgo]=[me_cdgo]\n" +
                                                        "	INNER JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [ae_cdgo]=[me_asignacion_equipo_cdgo]\n" +
                                                        "	INNER JOIN ["+DB+"].[dbo].[equipos_liquidacion] ON [ae_equipo_cdgo]=[el_equipo_cdgo]\n" +
                                                        "	INNER JOIN ["+DB+"].[dbo].[equipo] ON  [ae_equipo_cdgo]=[eq_cdgo]\n" +
                                                        "	INNER JOIN ["+DB+"].[dbo].[persona] ON [ps_equipo_cdgo]=[el_equipo_cdgo]\n" +
                                                        "	INNER JOIN ["+DB+"].[dbo].[tipo_documento] ON [ps_tipo_documento_cdgo]=[tidoc_cdgo] \n" +
                                                        "	INNER JOIN ["+DB+"].[dbo].[cargo_nomina] ON [cn_cdgo]=[ps_cargo_nomina_cdgo] \n" +
                                                        "	INNER JOIN ["+DB+"].[dbo].[tipo_contrato] ON [ps_tipo_contrato_cdgo]=[tc_cdgo] \n" +
                                                        "	INNER JOIN ["+DB+"].[dbo].[compania] ON [cp_cdgo]=[ps_compania_cdgo] \n" +
                                                        "	LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo] \n" +
                                                        "WHERE [me_inactividad]=0 AND [mc_estad_mvto_carbon_cdgo]=1 AND [mcle_cdgo]=?   AND [ps_estado]=1\n" +
                                                        "ORDER BY [mcle_cdgo] ASC	;");
                query.setString(1, mvtoCarbon_ListadoEquipos_LiquidacionPaleros.getCodigo());
                resultSet= query.executeQuery();     
                boolean validar= true;
            while(resultSet.next()){  
                if(validar){
                    listadoObjetos= new ArrayList<>();
                    validar=false;  
                }
                Persona Objeto = new Persona(); 
                Objeto.setCodigo(resultSet.getString(1));
                    TipoDocumento tipoDocumento= new TipoDocumento(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                Objeto.setTipoDocumento(tipoDocumento);
                Objeto.setNombre(resultSet.getString(6));
                Objeto.setApellido(resultSet.getString(7));
                System.out.println(""+resultSet.getString(6)+" "+resultSet.getString(7));
                Objeto.setTelefono(resultSet.getString(8));
                    CargoNomina cargoNomina= new CargoNomina(resultSet.getString(10), resultSet.getString(11), resultSet.getString(12));
                Objeto.setCargoNomina(cargoNomina);
                    TipoContrato tipoContrato= new TipoContrato(resultSet.getString(14), resultSet.getString(15), resultSet.getString(16));
                Objeto.setTipoContrato(tipoContrato);
                    Compañia compania= new Compañia(resultSet.getString(18), resultSet.getString(19), resultSet.getString(20));
                Objeto.setCompania(compania);
                    Equipo equipo = new Equipo();
                    equipo.setCodigo(resultSet.getString(22));
                    equipo.setMarca(resultSet.getString(23));
                    equipo.setModelo(resultSet.getString(24));
                    equipo.setDescripcion(resultSet.getString(25));
                    TipoEquipo tipoEquipo = new TipoEquipo(resultSet.getString(27), resultSet.getString(28), "");
                    equipo.setTipoEquipo(tipoEquipo);
                Objeto.setEquipo(equipo);
                Objeto.setEstado(resultSet.getString(26));       
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las personas");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        mvtoCarbon_ListadoEquipos_LiquidacionPaleros.getMvtoEquipo().getAsignacionEquipo().getEquipo().setListadoPersonas(listadoObjetos);
        mvtoCarbon_ListadoEquipos_LiquidacionPaleros.getMvtoEquipo().getAsignacionEquipo().getEquipo().setListadoPersonas(listadoObjetos);
        return mvtoCarbon_ListadoEquipos_LiquidacionPaleros;
    }
    
    public void limpiarRegistroLiquidacion(){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        PreparedStatement Query;
        try {
            Query = conexion.prepareStatement("DELETE FROM ["+DB+"].[dbo].[mvto_palero_preliquidacion_temp];");
            Query.execute();
            Query= conexion.prepareStatement("DELETE FROM ["+DB+"].[dbo].[mvto_vehiculos_paleros_temp];");
            Query.execute();  
        } catch (SQLException ex) {
            Logger.getLogger(ControlDB_LiquidacionPalero.class.getName()).log(Level.SEVERE, null, ex);
        }
        control.cerrarConexionBaseDatos();
    }
    
    
    public int registrar_Preliquidacion(ArrayList<MvtoPaleroPreliquidacionTEMP> listado, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException, SQLException {
        int result=0;
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            for(MvtoPaleroPreliquidacionTEMP objeto: listado){
                PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[mvto_vehiculos_paleros_temp] ("
                + "        [mvpt_cdgo]\n" +
                    "      ,[mvpt_mvto_carbon_listado_equipo_cdgo]\n" +
                    "      ,[mvpt_estad]) "
                + "VALUES ("
                + "(SELECT (CASE WHEN (MAX([mvpt_cdgo]) IS NULL) THEN 1 ELSE (MAX([mvpt_cdgo])+1) END)AS [mvpt_cdgo] FROM ["+DB+"].[dbo].[mvto_vehiculos_paleros_temp]),"
                        + "?,?);");
                Query.setString(1, objeto.getMvtoVehiculoPalerosTEMP().getMvtoCarbon_ListadoEquipos_LiquidacionPaleros().getCodigo());
                Query.setString(2, objeto.getMvtoVehiculoPalerosTEMP().getEstado());           
                Query.execute();
                result=1;
                if(result==1){
                    result=0;
                    //Sacamos el ultimo valor 
                    PreparedStatement queryConsultarMaximo= conexion.prepareStatement("SELECT MAX(mvpt_cdgo) FROM ["+DB+"].[dbo].[mvto_vehiculos_paleros_temp];");
                    ResultSet resultSetMaximo= queryConsultarMaximo.executeQuery();
                    while(resultSetMaximo.next()){ 
                        if(resultSetMaximo.getString(1) != null){
                            objeto.getMvtoVehiculoPalerosTEMP().setCodigo(resultSetMaximo.getString(1));
                        }
                    }
                    result=1;
                    if(result==1){
                        result=0;
                        System.out.println("INSERT INTO ["+DB+"].[dbo].[mvto_palero_preliquidacion_temp]\n" +
                                                                    "           ([mppt_cdgo]\n" +
                                                                    "           ,[mppt_mvto_vehiculos_paleros_temp_cdgo]\n" +
                                                                    "           ,[mppt_config_liquidacion_cdgo]\n" +
                                                                    "           ,[mppt_fecha]\n" +
                                                                    "           ,[mppt_persona_cdgo]\n" +
                                                                    "           ,[mppt_persona_tipo_documento_cdgo]\n" +
                                                                    "           ,[mppt_equipos_liquidacion_cdgo]\n" +
                                                                    "           ,[mppt_peso_articulo]) "
                                                        + "VALUES ("
                                                        + "(SELECT (CASE WHEN (MAX([mppt_cdgo]) IS NULL) THEN 1 ELSE (MAX([mppt_cdgo])+1) END)AS [mppt_cdgo] FROM ["+DB+"].[dbo].[mvto_palero_preliquidacion_temp]),"
                                                                + ""+objeto.getMvtoVehiculoPalerosTEMP().getCodigo()+","

                                                        + ""+objeto.getConfiguracionLiquidacion().getCodigo()+","
                                                        + ""+"'"+objeto.getFecha().split(" ")[0]+"'"+","
                                                        + "'"+objeto.getPersona().getCodigo()+"',"
                                                        + ""+objeto.getPersona().getTipoDocumento().getCodigo()+","
                                                + "(SELECT [el_cdgo] FROM ["+DB+"].[dbo].[equipos_liquidacion] WHERE [el_equipo_cdgo]="+objeto.getEquipoLiquidacion().getEquipo().getCodigo()+"),"
                                                        + ""+"'"+objeto.getPesoAsignado()+"'"+");");
                        PreparedStatement Query2= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[mvto_palero_preliquidacion_temp]\n" +
                                                                    "           ([mppt_cdgo]\n" +
                                                                    "           ,[mppt_mvto_vehiculos_paleros_temp_cdgo]\n" +
                                                                    "           ,[mppt_config_liquidacion_cdgo]\n" +
                                                                    "           ,[mppt_fecha]\n" +
                                                                    "           ,[mppt_persona_cdgo]\n" +
                                                                    "           ,[mppt_persona_tipo_documento_cdgo]\n" +
                                                                    "           ,[mppt_equipos_liquidacion_cdgo]\n" +
                                                                    "           ,[mppt_peso_articulo]) "
                                                        + "VALUES ("
                                                                + "(SELECT (CASE WHEN (MAX([mppt_cdgo]) IS NULL) THEN 1 ELSE (MAX([mppt_cdgo])+1) END)AS [mppt_cdgo] FROM ["+DB+"].[dbo].[mvto_palero_preliquidacion_temp]),"
                                                                        + ""+objeto.getMvtoVehiculoPalerosTEMP().getCodigo()+","

                                                                + ""+objeto.getConfiguracionLiquidacion().getCodigo()+","
                                                                + ""+"'"+objeto.getFecha().split(" ")[0]+"'"+","
                                                                + "'"+objeto.getPersona().getCodigo()+"',"
                                                                + ""+objeto.getPersona().getTipoDocumento().getCodigo()+","
                                                        + "(SELECT [el_cdgo] FROM ["+DB+"].[dbo].[equipos_liquidacion] WHERE [el_equipo_cdgo]="+objeto.getEquipoLiquidacion().getEquipo().getCodigo()+"),"
                                                                + ""+"'"+objeto.getPesoAsignado()+"'"+");");         
                        Query2.execute();
                        result=1;
                    }
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
                    "           ,'PRELIQUIDACION'" +
                    "           ,CONCAT (?,?,?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, listado.get(0).getConfiguracionLiquidacion().getCodigo());
                Query_Auditoria.setString(6, "Se registró un nueva preliquidacion en el sistema para la configuración de liquidación con código: ");
                Query_Auditoria.setString(7, listado.get(0).getConfiguracionLiquidacion().getCodigo());
                Query_Auditoria.setString(8, " Descripción: "+ listado.get(0).getConfiguracionLiquidacion().getDescripcion());
                Query_Auditoria.execute();
                result=1;
            }   
        }
        catch (SQLException sqlException ){   
            result=0;
            sqlException.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return result;
    }
    public int registrar_Preliquidacion2(MvtoPaleroPreliquidacionTEMP objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException, SQLException {
        int result=0;
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();

                PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[mvto_vehiculos_paleros_temp] ("
                + "        [mvpt_cdgo]\n" +
                    "      ,[mvpt_mvto_carbon_listado_equipo_cdgo]\n" +
                    "      ,[mvpt_estad]) "
                + "VALUES ("
                + "(SELECT (CASE WHEN (MAX([mvpt_cdgo]) IS NULL) THEN 1 ELSE (MAX([mvpt_cdgo])+1) END)AS [mvpt_cdgo] FROM ["+DB+"].[dbo].[mvto_vehiculos_paleros_temp]),"
                        + "?,?);");
                Query.setString(1, objeto.getMvtoVehiculoPalerosTEMP().getMvtoCarbon_ListadoEquipos_LiquidacionPaleros().getCodigo());
                Query.setString(2, objeto.getMvtoVehiculoPalerosTEMP().getEstado());           
                Query.execute();
                result=1;
                if(result==1){
                    result=0;
                    //Sacamos el ultimo valor 
                    PreparedStatement queryConsultarMaximo= conexion.prepareStatement("SELECT MAX(mvpt_cdgo) FROM ["+DB+"].[dbo].[mvto_vehiculos_paleros_temp];");
                    ResultSet resultSetMaximo= queryConsultarMaximo.executeQuery();
                    while(resultSetMaximo.next()){ 
                        if(resultSetMaximo.getString(1) != null){
                            objeto.getMvtoVehiculoPalerosTEMP().setCodigo(resultSetMaximo.getString(1));
                        }
                    }
                    result=1;
                    if(result==1){
                        result=0;
                        PreparedStatement Query2= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[mvto_palero_preliquidacion_temp]\n" +
                                                                    "           ([mppt_cdgo]\n" +
                                                                    "           ,[mppt_mvto_vehiculos_paleros_temp_cdgo]\n" +
                                                                    "           ,[mppt_config_liquidacion_cdgo]\n" +
                                                                    "           ,[mppt_fecha]\n" +
                                                                    "           ,[mppt_persona_cdgo]\n" +
                                                                    "           ,[mppt_persona_tipo_documento_cdgo]\n" +
                                                                    "           ,[mppt_equipos_liquidacion_cdgo]\n" +
                                                                    "           ,[mppt_peso_articulo]) "
                                                        + "VALUES ("
                                                                + "(SELECT (CASE WHEN (MAX([mppt_cdgo]) IS NULL) THEN 1 ELSE (MAX([mppt_cdgo])+1) END)AS [mppt_cdgo] FROM ["+DB+"].[dbo].[mvto_palero_preliquidacion_temp]),"
                                                                        + ""+objeto.getMvtoVehiculoPalerosTEMP().getCodigo()+","

                                                                + ""+objeto.getConfiguracionLiquidacion().getCodigo()+","
                                                                + ""+"'"+objeto.getFecha().split(" ")[0]+"'"+","
                                                                + "'"+objeto.getPersona().getCodigo()+"',"
                                                                + ""+objeto.getPersona().getTipoDocumento().getCodigo()+","
                                                        + "(SELECT [el_cdgo] FROM ["+DB+"].[dbo].[equipos_liquidacion] WHERE [el_equipo_cdgo]="+objeto.getEquipoLiquidacion().getEquipo().getCodigo()+"),"
                                                                + ""+"'"+objeto.getPesoAsignado()+"'"+");");         
                        Query2.execute();
                        result=1;
                    }
                }   
            
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
                    "           ,'PRELIQUIDACION'" +
                    "           ,CONCAT (?,?,?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, listado.get(0).getConfiguracionLiquidacion().getCodigo());
                Query_Auditoria.setString(6, "Se registró un nueva preliquidacion en el sistema para la configuración de liquidación con código: ");
                Query_Auditoria.setString(7, listado.get(0).getConfiguracionLiquidacion().getCodigo());
                Query_Auditoria.setString(8, " Descripción: "+ listado.get(0).getConfiguracionLiquidacion().getDescripcion());
                Query_Auditoria.execute();
                result=1;
            } */  
        }
        catch (SQLException sqlException ){   
            result=0;
            sqlException.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return result;
    }
    public int registrar_PreliquidacionAuditoria(MvtoPaleroPreliquidacionTEMP objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException, SQLException {
        int result=0;
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
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
                    "           ,'PRELIQUIDACION'" +
                    "           ,CONCAT (?,?,?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, objeto.getConfiguracionLiquidacion().getCodigo());
                Query_Auditoria.setString(6, "Se registró un nueva preliquidacion en el sistema para la configuración de liquidación con código: ");
                Query_Auditoria.setString(7, objeto.getConfiguracionLiquidacion().getCodigo());
                Query_Auditoria.setString(8, " Descripción: "+ objeto.getConfiguracionLiquidacion().getDescripcion());
                Query_Auditoria.execute();
                result=1;
            } 
        }
        catch (SQLException sqlException ){   
            result=0;
            sqlException.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return result;
    }
    
    
    public ArrayList<String>     consultarPreliquidacion () throws SQLException{
        ArrayList<String> listadoObjetos = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT \n" +
                                                        "      [ps_cdgo] AS 'CEDULA'\n" +
                                                        "	  ,CONCAT([ps_nombre],' ',[ps_apellidos]) AS 'NOMBRE'\n" +
                                                        "	  ,CONCAT([eq_desc],' ',[eq_modelo]) AS 'CUADRILLA'\n" +
                                                        "	 -- ,[mc_fecha]\n" +
                                                        "	  ,CONCAT(YEAR([mc_fecha]),'/',MONTH([mc_fecha]), '/',DAY([mc_fecha])) AS 'FECHA'\n" +
                                                        "	   ,STRING_AGG([mc_placa_vehiculo],'  -  ') AS 'VEHÍCULOS_DESCARGADOS'\n" +
                                                        "	   ,SUM(convert(money, REPLACE([mc_peso_neto], ',','.'))) AS 'TOTAL DESCARGADO EN KG'\n" +
                                                        "	   ,SUM(convert(money, REPLACE([mppt_peso_articulo], ',','.'))) AS 'TOTAL ASIGNADO EN KG'\n" +
                                                        "	   ,COUNT([mc_placa_vehiculo])\n" +
                                                        "	   ,[ps_tipo_documento_cdgo]\n" +
                                                        "	  /*TOP 1000 [mppt_cdgo],[mppt_mvto_vehiculos_paleros_temp_cdgo]\n" +
                                                        "		  ,[mvpt_cdgo]\n" +
                                                        "		  ,[mvpt_mvto_carbon_listado_equipo_cdgo]\n" +
                                                        "				  ,[mcle_cdgo]\n" +
                                                        "				  ,[mcle_mvto_carbon_cdgo]\n" +
                                                        "							,[mc_cdgo]\n" +
                                                        "						  ,[mc_cntro_oper_cdgo]\n" +
                                                        "						  ,[mc_cntro_cost_auxiliar_cdgo]\n" +
                                                        "						  ,[mc_cntro_cost_cdgo]\n" +
                                                        "						  ,[mc_labor_realizada_cdgo]\n" +
                                                        "						  ,[mc_articulo_cdgo]\n" +
                                                        "						  ,[mc_cliente_cdgo]\n" +
                                                        "						  ,[mc_transprtdora_cdgo]\n" +
                                                        "						  ,[mc_fecha]\n" +
                                                        "						  ,[mc_num_orden]\n" +
                                                        "						  ,[mc_deposito]\n" +
                                                        "						  ,[mc_consecutivo_tqute]\n" +
                                                        "						  ,[mc_placa_vehiculo]\n" +
                                                        "						  ,[mc_peso_vacio]\n" +
                                                        "						  ,[mc_peso_lleno]\n" +
                                                        "						  ,[mc_peso_neto]\n" +
                                                        "						  ,[mc_fecha_entrad]\n" +
                                                        "						  ,[mc_fecha_salid]\n" +
                                                        "						  ,[mc_fecha_inicio_descargue]\n" +
                                                        "						  ,[mc_fecha_fin_descargue]\n" +
                                                        "						  ,[mc_usuario_cdgo]\n" +
                                                        "						  ,[mc_observ]\n" +
                                                        "						  ,[mc_estad_mvto_carbon_cdgo]\n" +
                                                        "						  ,[mc_conexion_peso_ccarga]\n" +
                                                        "						  ,[mc_registro_manual]\n" +
                                                        "						  ,[mc_usuario_registro_manual_cdgo]\n" +
                                                        "						  ,[mc_cntro_cost_auxiliarDestino_cdgo]\n" +
                                                        "						  ,[mc_cntro_cost_mayor_cdgo]\n" +
                                                        "						  ,[mc_lavado_vehiculo]\n" +
                                                        "						  ,[mc_lavado_vehiculo_observacion]\n" +
                                                        "						  ,[mc_motivo_nolavado_vehiculo_cdgo]\n" +
                                                        "						  ,[mc_equipo_lavado_cdgo]\n" +
                                                        "						  ,[mc_usuario_cierre_cdgo]\n" +
                                                        "						  ,[mc_costoLavadoVehiculo]\n" +
                                                        "						  ,[mc_valorRecaudoEmpresa_lavadoVehiculo]\n" +
                                                        "						  ,[mc_valorRecaudoEquipo_lavadoVehiculo]\n" +
                                                        "						  ,[mc_cliente_base_datos_cdgo]\n" +
                                                        "						  ,[mc_transprtdora_base_datos_cdgo]\n" +
                                                        "						  ,[mc_articulo_base_datos_cdgo]\n" +
                                                        "				  ,[mcle_asignacion_equipo_cdgo]\n" +
                                                        "						,[ae_cdgo]\n" +
                                                        "					  ,[ae_cntro_oper_cdgo]\n" +
                                                        "					  ,[ae_solicitud_listado_equipo_cdgo]\n" +
                                                        "					  ,[ae_fecha_registro]\n" +
                                                        "					  ,[ae_fecha_hora_inicio]\n" +
                                                        "					  ,[ae_fecha_hora_fin]\n" +
                                                        "					  ,[ae_cant_minutos]\n" +
                                                        "					  ,[ae_equipo_cdgo]\n" +
                                                        "						  ,[el_cdgo]\n" +
                                                        "						  ,[el_equipo_cdgo]\n" +
                                                        "							,[eq_cdgo]\n" +
                                                        "							  ,[eq_tipo_equipo_cdgo]\n" +
                                                        "							  ,[eq_codigo_barra]\n" +
                                                        "							  ,[eq_referencia]\n" +
                                                        "							  ,[eq_producto]\n" +
                                                        "							  ,[eq_capacidad]\n" +
                                                        "							  ,[eq_marca]\n" +
                                                        "							  ,[eq_modelo]\n" +
                                                        "							  ,[eq_serial]\n" +
                                                        "							  ,[eq_desc]\n" +
                                                        "							  ,[eq_clasificador1_cdgo]\n" +
                                                        "							  ,[eq_clasificador2_cdgo]\n" +
                                                        "							  ,[eq_proveedor_equipo_cdgo]\n" +
                                                        "							  ,[eq_equipo_pertenencia_cdgo]\n" +
                                                        "							  ,[eq_observ]\n" +
                                                        "							  ,[eq_estad]\n" +
                                                        "							  ,[eq_actvo_fijo_id]\n" +
                                                        "							  ,[eq_actvo_fijo_referencia]\n" +
                                                        "							  ,[eq_actvo_fijo_desc]\n" +
                                                        "							  ,[eq_cntro_cost_equipo_cdgo]\n" +
                                                        "						  ,[el_estad]\n" +
                                                        "					  ,[ae_equipo_pertenencia_cdgo]\n" +
                                                        "					  ,[ae_cant_minutos_operativo]\n" +
                                                        "					  ,[ae_cant_minutos_parada]\n" +
                                                        "					  ,[ae_cant_minutos_total]\n" +
                                                        "					  ,[ae_estad]\n" +
                                                        "				  ,[mcle_mvto_equipo_cdgo]\n" +
                                                        "				  ,[mcle_estado]\n" +
                                                        "		  ,[mvpt_estad]\n" +
                                                        "      ,[mppt_config_liquidacion_cdgo]\n" +
                                                        "      ,[mppt_fecha]\n" +
                                                        "      ,[mppt_persona_cdgo]\n" +
                                                        "      ,[mppt_persona_tipo_documento_cdgo]\n" +
                                                        "	      ,[ps_cdgo]\n" +
                                                        "		  ,[ps_tipo_documento_cdgo]\n" +
                                                        "		  ,[ps_nombre]\n" +
                                                        "		  ,[ps_apellidos]\n" +
                                                        "		  ,[ps_telefono]\n" +
                                                        "		  ,[ps_cargo_nomina_cdgo]\n" +
                                                        "		  ,[ps_tipo_contrato_cdgo]\n" +
                                                        "		  ,[ps_compania_cdgo]\n" +
                                                        "		  ,[ps_equipo_cdgo]\n" +
                                                        "		  ,[ps_estado]\n" +
                                                        "      ,[mppt_equipos_liquidacion_cdgo]\n" +
                                                        "      ,[mppt_peso_articulo]*/\n" +
                                                        "  FROM ["+DB+"].[dbo].[mvto_palero_preliquidacion_temp]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[mvto_vehiculos_paleros_temp] ON [mppt_mvto_vehiculos_paleros_temp_cdgo]=[mvpt_cdgo]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[mvto_carbon_listado_equipo] ON [mvpt_mvto_carbon_listado_equipo_cdgo]=[mcle_cdgo]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[mvto_carbon] ON [mcle_mvto_carbon_cdgo]=[mc_cdgo]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[persona] ON [mppt_persona_cdgo]=[ps_cdgo] AND [mppt_persona_tipo_documento_cdgo]=[ps_tipo_documento_cdgo]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[asignacion_equipo] ON [mcle_asignacion_equipo_cdgo]=[ae_cdgo]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[equipos_liquidacion] ON [ae_equipo_cdgo]=[el_equipo_cdgo]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[equipo] ON [el_equipo_cdgo]=[eq_cdgo]\n" +
                                                        "  GROUP BY  [ps_cdgo],CONCAT([ps_nombre],' ',[ps_apellidos]),[ps_tipo_documento_cdgo],CONCAT([eq_desc],' ',[eq_modelo]),CONCAT(YEAR([mc_fecha]),'/',MONTH([mc_fecha]), '/',DAY([mc_fecha])) \n" +
                                                        "  \n" +
                                                        "  ORDER BY CONCAT([eq_desc],' ',[eq_modelo]) ASC --CONCAT(YEAR([mc_fecha]),'/',MONTH([mc_fecha]), '/',DAY([mc_fecha]))  ASC");
            ResultSet resultSet; resultSet= query.executeQuery();    
            boolean validar= true;
            while(resultSet.next()){ 
                try{
                    if(validar){
                        listadoObjetos= new ArrayList<>();
                        validar= false;
                    }
                    System.out.println(resultSet.getString(1)+"@@"+resultSet.getString(2)+"@@"+resultSet.getString(3)+"@@"+resultSet.getString(4)+"@@"+resultSet.getString(5)+"@@"+resultSet.getString(6)+"@@"+resultSet.getString(7)+"@@"+resultSet.getString(8)+"@@"+resultSet.getString(9));
                    listadoObjetos.add(resultSet.getString(1)+"@@"+resultSet.getString(2)+"@@"+resultSet.getString(3)+"@@"+resultSet.getString(4)+"@@"+resultSet.getString(5)+"@@"+resultSet.getString(6)+"@@"+resultSet.getString(7)+"@@"+resultSet.getString(8)+"@@"+resultSet.getString(9));
                }catch(Exception e){
                    e.printStackTrace();
                }                
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar la preliquidación");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }
    public int validarDiasLaborados (ConfiguracionLiquidacion configuracionLiquidacion, Persona persona) throws SQLException{
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        int diasLaborados=0;
        try{
             
            PreparedStatement query= conexion.prepareStatement( "DECLARE @fechaInicio DATETIME, @fechaFin DATETIME ,@cedula VARCHAR(45),@tipoDocumento INT  ; \n" +
                                                                "	 SET @fechaInicio='"+configuracionLiquidacion.getFechaInicio()+"';\n" +
                                                                "	 SET @fechaFin='"+configuracionLiquidacion.getFechaFinalizacion()+"';\n" +
                                                                "	 SET @cedula='"+persona.getCodigo()+"';\n" +
                                                                "	 SET @tipoDocumento="+persona.getTipoDocumento().getCodigo()+";\n " +
                        " SELECT COUNT(DISTINCT(table_consult.fecha)) AS cantidadDias FROM (SELECT [mpa_fecha_inicio]  AS fecha FROM ["+DB+"].[dbo].[marcacion_persona]  \n" +
                        "		WHERE [mpa_estad]=1 AND [mpa_persona_cdgo] LIKE @cedula AND [mpa_persona_tipo_documento_cdgo]=@tipoDocumento\n" +
                        "			AND [mpa_fecha_inicio] BETWEEN @fechaInicio AND @fechaFin\n" +
                        "     UNION  SELECT [mpa_fecha_fin] AS fecha FROM ["+DB+"].[dbo].[marcacion_persona] "+
                        "                 WHERE [mpa_estad]=1 AND [mpa_persona_cdgo] LIKE @cedula AND [mpa_persona_tipo_documento_cdgo]=@tipoDocumento "+
                        "                         AND [mpa_fecha_fin] BETWEEN @fechaInicio AND @fechaFin) AS table_consult ");
            ResultSet resultSet= query.executeQuery();    
            while(resultSet.next()){ 
                try{
                    diasLaborados=resultSet.getInt(1);
                }catch(Exception e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "No se pudo consultar los días laborados", "Error!!", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar la preliquidación");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return diasLaborados;
    }

    public ArrayList<Liquidacion> consultarRegistroLiquidacion(ConfiguracionLiquidacion configuracionLiquidacion){
        ArrayList<Liquidacion> listadoObjetos = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT \n" +
                                                "		[cl_fecha_inicio] AS 'FECHA INICIO PERIODO'--1\n" +
                                                "		,[cl_fecha_fin] AS 'FECHA FIN PERIODO'--2\n" +
                                                "		,[mppt_persona_cdgo] AS 'DOCUMENTO'--3\n" +
                                                "		,[mppt_persona_tipo_documento_cdgo]--4\n" +
                                                "		,[ps_nombre]--5\n" +
                                                "		,[ps_apellidos]--6\n" +
                                                "		,[cl_cant_dias] AS 'DÍAS LABORADOS'--7\n" +
                                                "		,(SELECT SYSDATETIME()) AS 'FECHA LIQUIDACION'--8\n" +
                                                "		,[mppt_config_liquidacion_cdgo]--9\n" +
                                                "		,SUM(convert(money, REPLACE(mppt_peso_articulo, ',','.'))) AS 'KILOS'--10\n" +
                                                "		,[cl_cant_tonelada_salario] AS 'DCTO TONELADAS MES'--11\n" +
                                                "		,[cl_valor_tonelada] AS 'VALOR TONELADA'--12\n" +
                                                "		,[cl_cant_tonelada_salario] AS 'TONELADA SALARIO'--13\n" +
                                                "		,(SUM(convert(money, REPLACE(mppt_peso_articulo, ',','.'))) - [cl_cant_tonelada_salario]) AS 'TONELADAS A PAGAR'--14\n" +
                                                "		,(((SUM(convert(money, REPLACE(mppt_peso_articulo, ',','.'))) - [cl_cant_tonelada_salario])/1000) * [cl_valor_tonelada])  AS 'TOTAL COMISION'--15\n" +
                                                "		/*[mppt_cdgo]\n" +
                                                                "      ,[mppt_mvto_vehiculos_paleros_temp_cdgo]\n" +
                                                                "		  ,[mvpt_cdgo]\n" +
                                                                "		  ,[mvpt_mvto_carbon_listado_equipo_cdgo]\n" +
                                                                "		  ,[mvpt_estad]\n" +
                                                                "      ,[mppt_config_liquidacion_cdgo]\n" +
                                                                "			,[cl_cdgo]\n" +
                                                                "			,[cl_cargo_nomina_cdgo]\n" +
                                                                "			,[cl_fecha_inicio]\n" +
                                                                "			,[cl_fecha_fin]\n" +
                                                                "			,[cl_cant_dias]\n" +
                                                                "			,[cl_ano]\n" +
                                                                "			,[cl_desc]\n" +
                                                                "			,[cl_valor_tonelada]\n" +
                                                                "			,[cl_cant_tonelada_salario]\n" +
                                                                "			,[cl_usuario_registra_cdgo]\n" +
                                                                "			,[cl_estad]\n" +
                                                                "      ,[mppt_fecha]\n" +
                                                                "      ,[mppt_persona_cdgo]\n" +
                                                                "      ,[mppt_persona_tipodocumento_cdgo]\n" +
                                                                "		  ,[ps_cdgo]\n" +
                                                                "		  ,[ps_tipo_documento_cdgo]\n" +
                                                                "		  ,[ps_nombre]\n" +
                                                                "		  ,[ps_apellidos]\n" +
                                                                "		  ,[ps_telefono]\n" +
                                                                "		  ,[ps_cargo_nomina_cdgo]\n" +
                                                                "		  ,[ps_tipo_contrato_cdgo]\n" +
                                                                "		  ,[ps_compania_cdgo]\n" +
                                                                "		  ,[ps_equipo_cdgo]\n" +
                                                                "		  ,[ps_estado]\n" +
                                                                "      ,[mppt_equipos_liquidacion_cdgo]\n" +
                                                                "      ,[mppt_peso_articulo]*/\n" +
                                                "  FROM ["+DB+"].[dbo].[mvto_palero_preliquidacion_temp]\n" +
                                                "	INNER JOIN ["+DB+"].[dbo].[mvto_vehiculos_paleros_temp] ON [mppt_mvto_vehiculos_paleros_temp_cdgo]=[mvpt_cdgo]\n" +
                                                "	INNER JOIN ["+DB+"].[dbo].[config_liquidacion] ON [mppt_config_liquidacion_cdgo]=[cl_cdgo]\n" +
                                                "	INNER JOIN ["+DB+"].[dbo].[persona] ON [mppt_persona_cdgo]=[ps_cdgo] AND [mppt_persona_tipo_documento_cdgo]=[ps_tipo_documento_cdgo] WHERE [cl_cdgo]="+configuracionLiquidacion.getCodigo()+"\n" +
                                                "	GROUP BY [mppt_persona_cdgo]\n" +
                                                "      ,[mppt_persona_tipo_documento_cdgo],[ps_nombre]\n" +
                                                "		  ,[ps_apellidos],[mppt_config_liquidacion_cdgo],[cl_fecha_inicio]\n" +
                                                "		,[cl_fecha_fin]\n" +
                                                "		,[cl_cant_dias],[cl_cant_tonelada_salario],[cl_valor_tonelada]");
            ResultSet resultSet; 
            resultSet= query.executeQuery();    
            boolean validar= true;
            while(resultSet.next()){ 
                try{
                    if(validar){
                        listadoObjetos= new ArrayList<>();
                        validar= false;
                    }
                    Liquidacion objeto = new Liquidacion();
                    DecimalFormat formato2 = new DecimalFormat("0.00");
                        objeto.setConfiguracionLiquidacion(configuracionLiquidacion);
                            Persona persona = new Persona();
                            persona.setCodigo(resultSet.getString(3));
                            persona.setTipoDocumento(new TipoDocumento());
                            persona.getTipoDocumento().setCodigo(resultSet.getString(4)); 
                        objeto.setPersona(persona);
                        objeto.setCantidadToneladaAcumulada(""+formato2.format(Double.parseDouble(resultSet.getString(10))));
                        objeto.setCantidadToneladaSalario(resultSet.getString(11));
                        objeto.setCantidadToneladaAPagar(""+formato2.format(Double.parseDouble(resultSet.getString(14))));
                        objeto.setTotalValorAPagar(""+formato2.format(Double.parseDouble(resultSet.getString(15))));
                        objeto.setEstado("1");
                    listadoObjetos.add(objeto);    
                }catch(Exception e){
                    e.printStackTrace();
                }              
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al tratar de generar la liquidación");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    
    }
    public int registrar_preLiquidacion(ArrayList<Liquidacion> listado, Usuario us, ArrayList<String> consolidadoPreliquidacion) throws FileNotFoundException, UnknownHostException, SocketException, SQLException {
        int result=0;
        ArrayList<String> listadoEquipoTrabajador=new ControlDB_Equipo(tipoConexion).retornoPersonalEquiposEquipos();
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            try{
                PreparedStatement queryDelete;
                try {
                    //Eliminamos los consolidados que tenga el Id de la configuración de liquidación.
                    queryDelete = conexion.prepareStatement("DELETE FROM ["+DB+"].[dbo].[preliquidacion] WHERE [pliq_config_liquidacion_cdgo]=?;");
                    queryDelete.setString(1, listado.get(1).getConfiguracionLiquidacion().getCodigo());
                    queryDelete.execute();
                    
                    //Eliminamos la preliquidación que tenga los Id de la configuración de liquidación
                    queryDelete = conexion.prepareStatement("DELETE FROM ["+DB+"].[dbo].[consolidado_preliquidacion] WHERE [cpliq_config_liquidacion_cdgo]=?;");
                    queryDelete.setString(1, listado.get(1).getConfiguracionLiquidacion().getCodigo());
                    queryDelete.execute(); 
                    
                    //Consultamos los días trabajados de acuerdo con el periodo dentro de la configuración de liquidación y lo agregamos al listado.
                    for(int i= 0; i < listado.size(); i++){
                        listado.get(i).setCantidadDiasLaborados(""+validarDiasLaborados(listado.get(i).getConfiguracionLiquidacion(), listado.get(i).getPersona()));
                    }       
                } catch (SQLException ex) {
                    Logger.getLogger(ControlDB_LiquidacionPalero.class.getName()).log(Level.SEVERE, null, ex);
                }       
                //Abrimos la conexión nuevamente
                conexion= control.ConectarBaseDatos();
                for(Liquidacion objeto: listado){
                    if(listadoEquipoTrabajador != null){
                        String codigoTipoDocPersona = objeto.getPersona().getCodigo()+"-"+objeto.getPersona().getTipoDocumento().getCodigo();
                        for(String data : listadoEquipoTrabajador){
                            if(codigoTipoDocPersona.equals(data.split("@@")[0])){
                                Equipo equiTem = new Equipo();
                                equiTem.setCodigo(data.split("@@")[1]);
                                objeto.getPersona().setEquipo(equiTem);
                            }
                        }
                    }
                    
                    //objeto.getPersona().setEquipo(new ControlDB_Equipo(tipoConexion).retornoEquipoTrabajo(objeto.getPersona()));
                    
                    
                    //Abrimos la conexión nuevamente
                    conexion= control.ConectarBaseDatos();
                    String codigoEquipo="";
                    try{
                        if(objeto.getPersona().getEquipo() != null){
                            codigoEquipo="'"+objeto.getPersona().getEquipo().getCodigo()+"'";   
                        }else{
                            codigoEquipo= "NULL";   
                        }    
                    }catch(Exception e){
                        codigoEquipo= "NULL";
                    }
                    PreparedStatement Query= conexion.prepareStatement("INSERT INTO [dbo].[preliquidacion]\n" +
                                                    "           ([pliq_cdgo]\n" +
                                                    "           ,[pliq_config_liquidacion_cdgo]\n" +
                                                    "           ,[pliq_fecha]\n" +
                                                    "           ,[pliq_persona_cdgo]\n" +
                                                    "           ,[pliq_persona_tipo_documento_cdgo]\n" +
                                                    "           ,[pliq_cant_tonelada_acumulada]\n" +
                                                    "           ,[pliq_cant_tonelada_salario]\n" +
                                                    "           ,[pliq_cant_tonelada_pagar]\n" +
                                                    "           ,[pliq_total_valor_pagar]\n" +
                                                    "           ,[pliq_estad],[pliq_dias_laborados],[pliq_equipo_cdgo])\n" +
                                                    "     VALUES( "
                    + "(SELECT (CASE WHEN (MAX([pliq_cdgo]) IS NULL) THEN 1 ELSE (MAX([pliq_cdgo])+1) END)AS [pliq_cdgo] FROM ["+DB+"].[dbo].[preliquidacion]),"
                            + "?,(SELECT SYSDATETIME()),?,?,?,?,?,?,?,?,"+codigoEquipo+");");
                    Query.setString(1, objeto.getConfiguracionLiquidacion().getCodigo());
                    Query.setString(2, objeto.getPersona().getCodigo());           
                    Query.setString(3, objeto.getPersona().getTipoDocumento().getCodigo());           
                    Query.setString(4, objeto.getCantidadToneladaAcumulada());           
                    Query.setString(5, objeto.getCantidadToneladaSalario());           
                    Query.setString(6, objeto.getCantidadToneladaAPagar());           
                    Query.setString(7, objeto.getTotalValorAPagar());           
                    Query.setString(8, objeto.getEstado());           
                    Query.setString(9, objeto.getCantidadDiasLaborados());      
                    Query.execute();
                    result=1;
                }
                
                for(String data: consolidadoPreliquidacion){
                    
                    /**DefaultTableModel modeloT = new DefaultTableModel(null, new String[]{"CEDULA", "NOMBRE", "CUADRILLA", "FECHA", "CANTIDAD_VEHÍCULOS_DESCARGADOS","VEHÍCULOS_DESCARGADOS", "TOTAL DESCARGADO EN KG", "TOTAL ASIGNADO EN KG"});

                    //Cargamos la nueva información
                    for(int a=0; a < listadoPreliquidacion.size(); a++){
                        System.out.println("validar=========================>"+listadoPreliquidacion.get(a)); 
                    }
                    for(String data : listadoPreliquidacion){
                        DecimalFormat formato2 = new DecimalFormat("0.00");
                        String[] informacion = data.split("@@");
                        String[] registro = new String[8];
                        registro[0] = "" + informacion[0];
                        registro[1] = "" + informacion[1];
                        registro[2] = "" + informacion[2];
                        registro[3] = "" + informacion[3];
                        registro[4] = "" + informacion[7];
                        registro[5] = "" + informacion[4];
                        registro[6] = "" + formato2.format(Double.parseDouble(informacion[5]));
                        registro[7] = "" + formato2.format(Double.parseDouble(informacion[6]));
                        modeloT.addRow(registro);
                    }*/
                    String[] informacion = data.split("@@");
                    
                    Persona personaTemp = new Persona();
                    personaTemp.setCodigo(informacion[0]);
                    personaTemp.setTipoDocumento(new TipoDocumento(informacion[8], "", ""));
                    
                    Equipo equiTem = null;
                    if(listadoEquipoTrabajador != null){
                        String codigoTipoDocPersona = personaTemp.getCodigo()+"-"+personaTemp.getTipoDocumento().getCodigo();
                        for(String info : listadoEquipoTrabajador){
                            if(codigoTipoDocPersona.equals(info.split("@@")[0])){
                                equiTem = new Equipo();
                                equiTem.setCodigo(info.split("@@")[1]);
                            }
                        }
                    }
                    
                    
                    //Equipo equipoTemp = new ControlDB_Equipo(tipoConexion).retornoEquipoTrabajo(personaTemp);
                    
                    String codigoEquipoCp="";
                    try{
                        if(equiTem != null){
                            codigoEquipoCp="'"+equiTem.getCodigo()+"'";   
                        }else{
                            codigoEquipoCp= "NULL";   
                        }    
                    }catch(Exception e){
                        codigoEquipoCp= "NULL";
                    }
                    
                    PreparedStatement Query= conexion.prepareStatement("INSERT INTO [dbo].[consolidado_preliquidacion]\n" +
                                                    "           ([cpliq_cdgo]\n" +
                                                    "      ,[cpliq_config_liquidacion_cdgo]\n" +
                                                    "      ,[cpliq_persona_cdgo]\n" +
                                                    "      ,[cpliq_persona_tipo_documento_cdgo]\n" +
                                                    "      ,[cpliq_fecha]\n" +
                                                    "      ,[cpliq_cant_vehiculos_descargados]\n" +
                                                    "      ,[cpliq_desc_vehiculos_descargados]\n" +
                                                    "      ,[cpliq_cant_toneladas_descargadas]\n" +
                                                    "      ,[cpliq_cant_toneladas_asignadas]\n" +
                                                    "      ,[cpliq_estad],[cpliq_equipo_cdgo])\n" +
                                                    "     VALUES( "
                    + "(SELECT (CASE WHEN (MAX([cpliq_cdgo]) IS NULL) THEN 1 ELSE (MAX([cpliq_cdgo])+1) END) AS [cpliq_cdgo] FROM ["+DB+"].[dbo].[consolidado_preliquidacion]),"
                            + "?,?,?,?,?,?,?,?,?,"+codigoEquipoCp+");");
                    Query.setString(1, listado.get(0).getConfiguracionLiquidacion().getCodigo());
                    Query.setString(2, informacion[0]);           
                    Query.setString(3, informacion[8]);           
                    Query.setString(4, informacion[3]);           
                    Query.setString(5, informacion[7]);           
                    Query.setString(6, informacion[4]);           
                    Query.setString(7, informacion[5]);           
                    Query.setString(8, informacion[6]);           
                    Query.setString(9, "1");            
                    Query.execute();
                    result=1;
                }
                    /**DefaultTableModel modeloT = new DefaultTableModel(null, new String[]{"CEDULA", "NOMBRE", "CUADRILLA", "FECHA", "CANTIDAD_VEHÍCULOS_DESCARGADOS","VEHÍCULOS_DESCARGADOS", "TOTAL DESCARGADO EN KG", "TOTAL ASIGNADO EN KG"});

                    //Cargamos la nueva información
                    for(int a=0; a < listadoPreliquidacion.size(); a++){
                        System.out.println("validar=========================>"+listadoPreliquidacion.get(a)); 
                    }
                    for(String data : listadoPreliquidacion){
                        DecimalFormat formato2 = new DecimalFormat("0.00");
                        String[] informacion = data.split("@@");
                        String[] registro = new String[8];
                        registro[0] = "" + informacion[0];
                        registro[1] = "" + informacion[1];
                        registro[2] = "" + informacion[2];
                        registro[3] = "" + informacion[3];
                        registro[4] = "" + informacion[7];
                        registro[5] = "" + informacion[4];
                        registro[6] = "" + formato2.format(Double.parseDouble(informacion[5]));
                        registro[7] = "" + formato2.format(Double.parseDouble(informacion[6]));
                        modeloT.addRow(registro);
                    }*/ 
            }catch(Exception e){
                e.printStackTrace();
                result=0;
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
                    "           ,'PRELIQUIDACION'" +
                    "           ,CONCAT (?,?,?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, listado.get(0).getConfiguracionLiquidacion().getCodigo());
                Query_Auditoria.setString(6, "Se registró un nueva preliquidacion en el sistema para la configuración de liquidación con código: ");
                Query_Auditoria.setString(7, listado.get(0).getConfiguracionLiquidacion().getCodigo());
                Query_Auditoria.setString(8, " Descripción: "+ listado.get(0).getConfiguracionLiquidacion().getDescripcion());
                Query_Auditoria.execute();
                result=1;
                if(result==1){
                    //Limpiamos los registro temporales debido a que ya se registró la liquidación.
                    limpiarRegistroLiquidacion();
              
                    
                }
            }   
        }
        catch (SQLException sqlException ){   
            result=0;
            sqlException.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return result;
    }
    public ArrayList<PlantillaArchivoLiquidacion> consultar_PreLiquidacion(ConfiguracionLiquidacion configuracionLiquidacion){
        ArrayList<PlantillaArchivoLiquidacion> listadoObjetos = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  \n" +
                                                        "		[cl_fecha_inicio] AS 'FECHA INICIO PERIODO'  --1\n" +
                                                        "		,[cl_fecha_fin] AS 'FECHA FIN PERIODO'--2\n" +
                                                        "		,CONCAT([eq_desc],' ',[eq_modelo]) AS 'CUADRILLA'--3\n" +
                                                        "		,[ps_cdgo] AS 'DOCUMENTO'--4\n" +
                                                        "		,CONCAT([ps_nombre],' ',[ps_apellidos])	  AS 'EMPLEADO'--5\n" +
                                                        "		,[pliq_cant_tonelada_acumulada] AS 'KILOS'--6\n" +
                                                        "		,[pliq_dias_laborados] AS 'DIAS LABORADOS'--7\n" +
                                                        "		,[pliq_cant_tonelada_salario] AS 'DCTO TONELADA MES'--8\n" +
                                                        "		,[cl_valor_tonelada] AS 'VALOR TONELADA'--9\n" +
                                                        "		,[pliq_cant_tonelada_salario] AS 'TONELADA SALARIO'--10\n" +
                                                        "		,[pliq_cant_tonelada_pagar] AS 'TONELADAS A PAGAR'--11\n" +
                                                        "		,[pliq_total_valor_pagar] AS 'TOTAL COMISION'--12\n" +
                                                        "               ,[pliq_dias_laborados] AS 'DIAS_LABORADOS'\n" +
                                                        "      /*[liq_cdgo],[liq_config_liquidacion_cdgo]\n" +
                                                        "		  ,[cl_cdgo]\n" +
                                                        "		  ,[cl_cargo_nomina_cdgo]\n" +
                                                        "		  ,[cl_fecha_inicio]\n" +
                                                        "		  ,[cl_fecha_fin]\n" +
                                                        "		  ,[cl_cant_dias]\n" +
                                                        "		  ,[cl_ano]\n" +
                                                        "		  ,[cl_desc]\n" +
                                                        "		  ,[cl_valor_tonelada]\n" +
                                                        "		  ,[cl_cant_tonelada_salario]\n" +
                                                        "		  ,[cl_usuario_registra_cdgo]\n" +
                                                        "		  ,[cl_estad]\n" +
                                                        "      ,[liq_fecha]\n" +
                                                        "      ,[liq_persona_cdgo]\n" +
                                                        "      ,[liq_persona_tipo_documento_cdgo]\n" +
                                                        "			,[ps_cdgo]\n" +
                                                        "			  ,[ps_tipo_documento_cdgo]\n" +
                                                        "			  ,[ps_nombre]\n" +
                                                        "			  ,[ps_apellidos]\n" +
                                                        "			  ,[ps_telefono]\n" +
                                                        "			  ,[ps_cargo_nomina_cdgo]\n" +
                                                        "			  ,[ps_tipo_contrato_cdgo]\n" +
                                                        "			  ,[ps_compania_cdgo]\n" +
                                                        "			  ,[ps_equipo_cdgo]\n" +
                                                        "					,[eq_cdgo]\n" +
                                                        "				  ,[eq_tipo_equipo_cdgo]\n" +
                                                        "				  ,[eq_codigo_barra]\n" +
                                                        "				  ,[eq_referencia]\n" +
                                                        "				  ,[eq_producto]\n" +
                                                        "				  ,[eq_capacidad]\n" +
                                                        "				  ,[eq_marca]\n" +
                                                        "				  ,[eq_modelo]\n" +
                                                        "				  ,[eq_serial]\n" +
                                                        "				  ,[eq_desc]\n" +
                                                        "				  ,[eq_clasificador1_cdgo]\n" +
                                                        "				  ,[eq_clasificador2_cdgo]\n" +
                                                        "				  ,[eq_proveedor_equipo_cdgo]\n" +
                                                        "				  ,[eq_equipo_pertenencia_cdgo]\n" +
                                                        "				  ,[eq_observ]\n" +
                                                        "				  ,[eq_estad]\n" +
                                                        "				  ,[eq_actvo_fijo_id]\n" +
                                                        "				  ,[eq_actvo_fijo_referencia]\n" +
                                                        "				  ,[eq_actvo_fijo_desc]\n" +
                                                        "				  ,[eq_cntro_cost_equipo_cdgo]\n" +
                                                        "			  ,[ps_estado]\n" +
                                                        "      ,[liq_cant_tonelada_acumulada]\n" +
                                                        "      ,[liq_cant_tonelada_salario]\n" +
                                                        "      ,[liq_cant_tonelada_pagar]\n" +
                                                        "      ,[liq_total_valor_pagar]\n" +
                                                        "      ,[liq_estad]*/\n" +
                                                        "  FROM ["+DB+"].[dbo].[preliquidacion]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[persona] ON [pliq_persona_cdgo]=[ps_cdgo] AND [pliq_persona_tipo_documento_cdgo]=[ps_tipo_documento_cdgo]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[config_liquidacion] ON [pliq_config_liquidacion_cdgo]=[cl_cdgo]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[equipo] ON [ps_equipo_cdgo]=[eq_cdgo]\n" +
                                                        "  WHERE [cl_cdgo]= "+configuracionLiquidacion.getCodigo()+" ORDER BY CONCAT([eq_desc],' ',[eq_modelo])  ASC;");
            ResultSet resultSet; 
            resultSet= query.executeQuery();    
            boolean validar= true;
            while(resultSet.next()){ 
                try{
                    if(validar){
                        listadoObjetos= new ArrayList<>();
                        validar= false;
                    }
                    PlantillaArchivoLiquidacion objeto = new PlantillaArchivoLiquidacion();
                        objeto.setFechaInicio(resultSet.getString(1));   
                        objeto.setFechaFin(resultSet.getString(2));
                        objeto.setEquipo(resultSet.getString(3));
                        objeto.setPersonaDocumento(resultSet.getString(4));
                        objeto.setPersonaNombre(resultSet.getString(5));
                        objeto.setCantidadAcumuladasKilos(resultSet.getString(6));
                        objeto.setCantidadDias(resultSet.getString(7));
                        objeto.setToneladaMes(resultSet.getString(8));
                        objeto.setValorTonelada(resultSet.getString(9));
                        objeto.setToneladaSalario(resultSet.getString(10));
                        objeto.setToneladaAPagar(resultSet.getString(11));
                        objeto.setTotalComision(resultSet.getString(12));
                    listadoObjetos.add(objeto);    
                }catch(Exception e){
                    e.printStackTrace();
                }              
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al tratar de generar la liquidación");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }  
    public ArrayList<PlantillaArchivoLiquidacion> consultar_Liquidacion(ConfiguracionLiquidacion configuracionLiquidacion){
        ArrayList<PlantillaArchivoLiquidacion> listadoObjetos = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  \n" +
                                                        "		[cl_fecha_inicio] AS 'FECHA INICIO PERIODO'  --1\n" +
                                                        "		,[cl_fecha_fin] AS 'FECHA FIN PERIODO'--2\n" +
                                                        "		,CONCAT([eq_desc],' ',[eq_modelo]) AS 'CUADRILLA'--3\n" +
                                                        "		,[ps_cdgo] AS 'DOCUMENTO'--4\n" +
                                                        "		,CONCAT([ps_nombre],' ',[ps_apellidos])	  AS 'EMPLEADO'--5\n" +
                                                        "		,[liq_cant_tonelada_acumulada] AS 'KILOS'--6\n" +
                                                        "		,[liq_dias_laborados] AS 'DIAS LABORADOS'--7\n" +
                                                        "		,[liq_cant_tonelada_salario] AS 'DCTO TONELADA MES'--8\n" +
                                                        "		,[cl_valor_tonelada] AS 'VALOR TONELADA'--9\n" +
                                                        "		,[liq_cant_tonelada_salario] AS 'TONELADA SALARIO'--10\n" +
                                                        "		,[liq_cant_tonelada_pagar] AS 'TONELADAS A PAGAR'--11\n" +
                                                        "		,[liq_total_valor_pagar] AS 'TOTAL COMISION'--12\n" +
                                                        "               ,[liq_dias_laborados] AS 'DIAS_LABORADOS'\n" +
                                                        "      /*[liq_cdgo],[liq_config_liquidacion_cdgo]\n" +
                                                        "		  ,[cl_cdgo]\n" +
                                                        "		  ,[cl_cargo_nomina_cdgo]\n" +
                                                        "		  ,[cl_fecha_inicio]\n" +
                                                        "		  ,[cl_fecha_fin]\n" +
                                                        "		  ,[cl_cant_dias]\n" +
                                                        "		  ,[cl_ano]\n" +
                                                        "		  ,[cl_desc]\n" +
                                                        "		  ,[cl_valor_tonelada]\n" +
                                                        "		  ,[cl_cant_tonelada_salario]\n" +
                                                        "		  ,[cl_usuario_registra_cdgo]\n" +
                                                        "		  ,[cl_estad]\n" +
                                                        "      ,[liq_fecha]\n" +
                                                        "      ,[liq_persona_cdgo]\n" +
                                                        "      ,[liq_persona_tipo_documento_cdgo]\n" +
                                                        "			,[ps_cdgo]\n" +
                                                        "			  ,[ps_tipo_documento_cdgo]\n" +
                                                        "			  ,[ps_nombre]\n" +
                                                        "			  ,[ps_apellidos]\n" +
                                                        "			  ,[ps_telefono]\n" +
                                                        "			  ,[ps_cargo_nomina_cdgo]\n" +
                                                        "			  ,[ps_tipo_contrato_cdgo]\n" +
                                                        "			  ,[ps_compania_cdgo]\n" +
                                                        "			  ,[ps_equipo_cdgo]\n" +
                                                        "					,[eq_cdgo]\n" +
                                                        "				  ,[eq_tipo_equipo_cdgo]\n" +
                                                        "				  ,[eq_codigo_barra]\n" +
                                                        "				  ,[eq_referencia]\n" +
                                                        "				  ,[eq_producto]\n" +
                                                        "				  ,[eq_capacidad]\n" +
                                                        "				  ,[eq_marca]\n" +
                                                        "				  ,[eq_modelo]\n" +
                                                        "				  ,[eq_serial]\n" +
                                                        "				  ,[eq_desc]\n" +
                                                        "				  ,[eq_clasificador1_cdgo]\n" +
                                                        "				  ,[eq_clasificador2_cdgo]\n" +
                                                        "				  ,[eq_proveedor_equipo_cdgo]\n" +
                                                        "				  ,[eq_equipo_pertenencia_cdgo]\n" +
                                                        "				  ,[eq_observ]\n" +
                                                        "				  ,[eq_estad]\n" +
                                                        "				  ,[eq_actvo_fijo_id]\n" +
                                                        "				  ,[eq_actvo_fijo_referencia]\n" +
                                                        "				  ,[eq_actvo_fijo_desc]\n" +
                                                        "				  ,[eq_cntro_cost_equipo_cdgo]\n" +
                                                        "			  ,[ps_estado]\n" +
                                                        "      ,[liq_cant_tonelada_acumulada]\n" +
                                                        "      ,[liq_cant_tonelada_salario]\n" +
                                                        "      ,[liq_cant_tonelada_pagar]\n" +
                                                        "      ,[liq_total_valor_pagar]\n" +
                                                        "      ,[liq_estad]*/\n" +
                                                        "  FROM ["+DB+"].[dbo].[liquidacion]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[persona] ON [liq_persona_cdgo]=[ps_cdgo] AND [liq_persona_tipo_documento_cdgo]=[ps_tipo_documento_cdgo]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[config_liquidacion] ON [liq_config_liquidacion_cdgo]=[cl_cdgo]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[equipo] ON [ps_equipo_cdgo]=[eq_cdgo]\n" +
                                                        "  WHERE [cl_cdgo]= "+configuracionLiquidacion.getCodigo()+" ORDER BY CONCAT([eq_desc],' ',[eq_modelo])  ASC;");
            ResultSet resultSet; 
            resultSet= query.executeQuery();    
            boolean validar= true;
            while(resultSet.next()){ 
                try{
                    if(validar){
                        listadoObjetos= new ArrayList<>();
                        validar= false;
                    }
                    PlantillaArchivoLiquidacion objeto = new PlantillaArchivoLiquidacion();
                        objeto.setFechaInicio(resultSet.getString(1));   
                        objeto.setFechaFin(resultSet.getString(2));
                        objeto.setEquipo(resultSet.getString(3));
                        objeto.setPersonaDocumento(resultSet.getString(4));
                        objeto.setPersonaNombre(resultSet.getString(5));
                        objeto.setCantidadAcumuladasKilos(resultSet.getString(6));
                        objeto.setCantidadDias(resultSet.getString(7));
                        objeto.setToneladaMes(resultSet.getString(8));
                        objeto.setValorTonelada(resultSet.getString(9));
                        objeto.setToneladaSalario(resultSet.getString(10));
                        objeto.setToneladaAPagar(resultSet.getString(11));
                        objeto.setTotalComision(resultSet.getString(12));
                    listadoObjetos.add(objeto);    
                }catch(Exception e){
                    e.printStackTrace();
                }              
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al tratar de generar la liquidación");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }  
    public ArrayList<Liquidacion> consultarPreLiquidacion(ConfiguracionLiquidacion configuracionLiquidacion){
        ArrayList<Liquidacion> listadoPreliquidaciones = null;
        Conexion_DB_costos_vg control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT \n" +
                                                        "	  --Preliquidacion\n" +
                                                        "      [pliq_config_liquidacion_cdgo]--1\n" +
                                                        "      ,[pliq_fecha]--2\n" +
                                                        "      ,[pliq_persona_cdgo]--3\n" +
                                                        "      ,[pliq_persona_tipo_documento_cdgo]--4\n" +
                                                        "			 ,[ps_cdgo]--5\n" +
                                                        "		  ,[ps_tipo_documento_cdgo]--6\n" +
                                                        "		  ,[ps_nombre]--7\n" +
                                                        "		  ,[ps_apellidos]--8\n" +
                                                        "		  ,[ps_telefono]--9\n" +
                                                        "		  ,[ps_cargo_nomina_cdgo]--10\n" +
                                                        "				,[cn_cdgo]--11\n" +
                                                        "			  ,[cn_desc]--12\n" +
                                                        "			  ,[cn_estad]--13\n" +
                                                        "		  ,[ps_tipo_contrato_cdgo]--14\n" +
                                                        "			  ,[tc_cdgo]--15\n" +
                                                        "			  ,[tc_desc]--16\n" +
                                                        "			  ,[tc_estad]--17\n" +
                                                        "		  ,[ps_compania_cdgo]--18\n" +
                                                        "			  ,[cp_cdgo]--19\n" +
                                                        "			  ,[cp_desc]--20\n" +
                                                        "			  ,[cp_estad]--21\n" +
                                                        "		  ,[ps_equipo_cdgo]--22\n" +
                                                        "				,[eq_cdgo]--23\n" +
                                                        "				,[eq_marca]--24\n" +
                                                        "				,[eq_modelo]--25\n" +
                                                        "				,[eq_desc]--26\n" +
                                                        "		  ,[ps_estado]--27\n" +
                                                        "      ,[pliq_cant_tonelada_acumulada]--28\n" +
                                                        "      ,[pliq_cant_tonelada_salario]--29\n" +
                                                        "      ,[pliq_cant_tonelada_pagar]--30\n" +
                                                        "      ,[pliq_total_valor_pagar]--31\n" +
                                                        "      ,[pliq_estad]--32\n" +
                                                        "      ,[pliq_dias_laborados]--33\n" +
                                                        " FROM ["+DB+"].[dbo].[config_liquidacion]\n" +
                                                        "	INNER JOIN ["+DB+"].[dbo].[preliquidacion] ON [pliq_config_liquidacion_cdgo]=[cl_cdgo]\n" +
                                                        "	INNER JOIN ["+DB+"].[dbo].[persona] ON [ps_cdgo]=[pliq_persona_cdgo] AND [ps_tipo_documento_cdgo]=[pliq_persona_tipo_documento_cdgo]  \n" +
                                                        "	LEFT JOIN  ["+DB+"].[dbo].[cargo_nomina] ON [ps_cargo_nomina_cdgo]=[cn_cdgo]\n" +
                                                        "	LEFT JOIN  ["+DB+"].[dbo].[tipo_contrato] ON [ps_tipo_contrato_cdgo]=[tc_cdgo]\n" +
                                                        "	LEFT JOIN  ["+DB+"].[dbo].[compania] ON [ps_compania_cdgo]=[cp_cdgo]\n" +
                                                        "	INNER JOIN ["+DB+"].[dbo].[equipo] ON [ps_equipo_cdgo]=[eq_cdgo]\n" +
                                                        " WHERE [cl_cdgo]="+configuracionLiquidacion.getCodigo()+" ORDER BY [eq_modelo] ASC;");
            ResultSet resultSet; 
            resultSet= query.executeQuery();    
            boolean validar= true;
            while(resultSet.next()){ 
                try{
                    if(validar){
                        listadoPreliquidaciones = new ArrayList<>();
                        validar= false;
                    }
                    Liquidacion liquidacion = new Liquidacion();
                    liquidacion.setConfiguracionLiquidacion(configuracionLiquidacion);
                        liquidacion.setFecha(resultSet.getString(2));
                            Persona persona = new Persona();
                            persona.setCodigo(resultSet.getString(5));
                            persona.setTipoDocumento(new TipoDocumento(resultSet.getString(6), "", ""));
                            persona.setNombre(resultSet.getString(7));
                            persona.setApellido(resultSet.getString(8));
                            persona.setTelefono(resultSet.getString(9));
                            persona.setCargoNomina(new CargoNomina(resultSet.getString(11), resultSet.getString(12), resultSet.getString(14)));
                            persona.setTipoContrato(new TipoContrato(resultSet.getString(15), resultSet.getString(16), resultSet.getString(17)));
                            persona.setCompania(new Compañia(resultSet.getString(19), resultSet.getString(20), resultSet.getString(21)));
                                Equipo equipo = new Equipo();
                                equipo.setCodigo(resultSet.getString(23));
                                equipo.setMarca(resultSet.getString(24));
                                equipo.setModelo(resultSet.getString(25));
                                equipo.setDescripcion(resultSet.getString(26));                            
                        liquidacion.setPersona(persona);
                        liquidacion.setCantidadToneladaAcumulada(resultSet.getString(28));
                        liquidacion.setCantidadToneladaSalario(resultSet.getString(29));
                        liquidacion.setCantidadToneladaAPagar(resultSet.getString(30));
                        liquidacion.setTotalValorAPagar(resultSet.getString(31));
                        liquidacion.setEstado(resultSet.getString(32));
                        liquidacion.setCantidadDiasLaborados(resultSet.getString(33));
                    listadoPreliquidaciones.add(liquidacion); 
                }catch(Exception e){
                    e.printStackTrace();
                }              
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las preliquidaciones");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoPreliquidaciones;
    }   
    public ArrayList<ConsolidadoLiquidacion> consultarConsolidadoPreLiquidacion(ConfiguracionLiquidacion configuracionLiquidacion){
        ArrayList<ConsolidadoLiquidacion> listadoConsolidadoPreliquidacion = null;
        Conexion_DB_costos_vg control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  \n" +
                                                            "	  -- Consolidado preliquidacion\n" +
                                                            "	  [cpliq_cdgo]--1\n" +
                                                            "      ,[cpliq_config_liquidacion_cdgo]--2\n" +
                                                            "      ,[cpliq_persona_cdgo]--3\n" +
                                                            "      ,[cpliq_persona_tipo_documento_cdgo]--4\n" +
                                                            "		   ,[ps_cdgo]--5\n" +
                                                            "		  ,[ps_tipo_documento_cdgo]--6\n" +
                                                            "		  ,[ps_nombre]--7\n" +
                                                            "		  ,[ps_apellidos]--8\n" +
                                                            "		  ,[ps_telefono]--9\n" +
                                                            "      ,[cpliq_fecha]--10\n" +
                                                            "      ,[cpliq_cant_vehiculos_descargados]--11\n" +
                                                            "      ,[cpliq_desc_vehiculos_descargados]--12\n" +
                                                            "      ,[cpliq_cant_toneladas_descargadas]--13\n" +
                                                            "      ,[cpliq_cant_toneladas_asignadas]--14\n" +
                                                            "      ,[cpliq_estad]--15\n" +
                                                            "FROM ["+DB+"].[dbo].[config_liquidacion]\n" +
                                                            "	INNER JOIN ["+DB+"].[dbo].[consolidado_preliquidacion] ON [cl_cdgo]=[cpliq_config_liquidacion_cdgo]\n" +
                                                            "	INNER JOIN ["+DB+"].[dbo].[persona] ON [ps_cdgo]=[cpliq_persona_cdgo] AND [ps_tipo_documento_cdgo]=[cpliq_persona_tipo_documento_cdgo]  \n" +
                                                            "WHERE [cl_cdgo]="+configuracionLiquidacion.getCodigo()+" ORDER BY [cpliq_cdgo] ASC;");
            ResultSet resultSet; 
            resultSet= query.executeQuery();    
            boolean validar= true;
            while(resultSet.next()){ 
                try{
                    if(validar){
                        listadoConsolidadoPreliquidacion = new ArrayList<>();
                        validar= false;
                    }
                    ConsolidadoLiquidacion consolidadoLiquidacion = new ConsolidadoLiquidacion();
                        consolidadoLiquidacion.setConfiguracionLiquidacion(configuracionLiquidacion);
                        consolidadoLiquidacion.setFecha(resultSet.getString(10));
                            Persona persona = new Persona();
                            persona.setCodigo(resultSet.getString(5));
                            persona.setTipoDocumento(new TipoDocumento(resultSet.getString(6), "", ""));
                            persona.setNombre(resultSet.getString(7));
                            persona.setApellido(resultSet.getString(8));
                            persona.setTelefono(resultSet.getString(9));                          
                        consolidadoLiquidacion.setPersona(persona);
                        consolidadoLiquidacion.setCantidadVehiculosDescargados(resultSet.getString(11));
                        consolidadoLiquidacion.setDescripcionVehiculosDescargados(resultSet.getString(12));
                        consolidadoLiquidacion.setCantidadToneladasDescargadas(resultSet.getString(13));
                        consolidadoLiquidacion.setCantidadToneladasAsignadas(resultSet.getString(14));
                        consolidadoLiquidacion.setEstado(resultSet.getString(15));
                    listadoConsolidadoPreliquidacion.add(consolidadoLiquidacion); 
                }catch(Exception e){
                    e.printStackTrace();
                }              
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los consolidados de preliquidaciones");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoConsolidadoPreliquidacion;
    } 
    public ArrayList<ConsolidadoLiquidacion> consultarConsolidadoLiquidacion(ConfiguracionLiquidacion configuracionLiquidacion){
        ArrayList<ConsolidadoLiquidacion> listadoConsolidadoliquidacion = null;
        Conexion_DB_costos_vg control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  \n" +
                                                            "	  -- Consolidado preliquidacion\n" +
                                                            "	  [cliq_cdgo]--1\n" +
                                                            "      ,[cliq_config_liquidacion_cdgo]--2\n" +
                                                            "      ,[cliq_persona_cdgo]--3\n" +
                                                            "      ,[cliq_persona_tipo_documento_cdgo]--4\n" +
                                                            "		   ,[ps_cdgo]--5\n" +
                                                            "		  ,[ps_tipo_documento_cdgo]--6\n" +
                                                            "		  ,[ps_nombre]--7\n" +
                                                            "		  ,[ps_apellidos]--8\n" +
                                                            "		  ,[ps_telefono]--9\n" +
                                                            "      ,[cliq_fecha]--10\n" +
                                                            "      ,[cliq_cant_vehiculos_descargados]--11\n" +
                                                            "      ,[cliq_desc_vehiculos_descargados]--12\n" +
                                                            "      ,[cliq_cant_toneladas_descargadas]--13\n" +
                                                            "      ,[cliq_cant_toneladas_asignadas]--14\n" +
                                                            "      ,[cliq_estad]--15\n"
                                                            + ",[cliq_equipo_cdgo]--16\n" +
                                        "			,[eq_cdgo]--17\n" +
                                        "			,[eq_desc]--18\n" +
                                        "			,[eq_modelo]--19\n" +
                                                            "FROM ["+DB+"].[dbo].[config_liquidacion]\n" +
                                                            "	INNER JOIN ["+DB+"].[dbo].[consolidado_liquidacion] ON [cl_cdgo]=[cliq_config_liquidacion_cdgo]\n" +
                                                            "	INNER JOIN ["+DB+"].[dbo].[persona] ON [ps_cdgo]=[cliq_persona_cdgo] AND [ps_tipo_documento_cdgo]=[cliq_persona_tipo_documento_cdgo]  \n" +
                                                            "	INNER JOIN ["+DB+"].[dbo].[equipo] ON [cliq_equipo_cdgo]=[eq_cdgo]\n" +
                                                            "WHERE [cl_cdgo]="+configuracionLiquidacion.getCodigo()+" ORDER BY [cliq_cdgo] ASC;");
            ResultSet resultSet; 
            resultSet= query.executeQuery();    
            boolean validar= true;
            while(resultSet.next()){ 
                try{
                    if(validar){
                        listadoConsolidadoliquidacion = new ArrayList<>();
                        validar= false;
                    }
                    ConsolidadoLiquidacion consolidadoLiquidacion = new ConsolidadoLiquidacion();
                        consolidadoLiquidacion.setConfiguracionLiquidacion(configuracionLiquidacion);
                        consolidadoLiquidacion.setFecha(resultSet.getString(10));
                            Persona persona = new Persona();
                            persona.setCodigo(resultSet.getString(5));
                            persona.setTipoDocumento(new TipoDocumento(resultSet.getString(6), "", ""));
                            persona.setNombre(resultSet.getString(7));
                            persona.setApellido(resultSet.getString(8));
                            persona.setTelefono(resultSet.getString(9)); 
                            Equipo equipo = new Equipo();
                            equipo.setCodigo(resultSet.getString(17));
                            equipo.setDescripcion(resultSet.getString(18));
                            equipo.setModelo(resultSet.getString(19));
                            persona.setEquipo(equipo);
                        
                        consolidadoLiquidacion.setPersona(persona);
                        consolidadoLiquidacion.setCantidadVehiculosDescargados(resultSet.getString(11));
                        consolidadoLiquidacion.setDescripcionVehiculosDescargados(resultSet.getString(12));
                        consolidadoLiquidacion.setCantidadToneladasDescargadas(resultSet.getString(13));
                        consolidadoLiquidacion.setCantidadToneladasAsignadas(resultSet.getString(14));
                        consolidadoLiquidacion.setEstado(resultSet.getString(15));
                    listadoConsolidadoliquidacion.add(consolidadoLiquidacion); 
                }catch(Exception e){
                    e.printStackTrace();
                }              
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los consolidados de liquidaciones");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoConsolidadoliquidacion;
    } 
    public int registrar_Liquidacion(ArrayList<Liquidacion> listadoLiquidacion,ArrayList<ConsolidadoLiquidacion> listadoConsolidadoLiquidacion, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException, SQLException {
        int result=0;
        ArrayList<String> listadoEquipoTrabajador=new ControlDB_Equipo(tipoConexion).retornoPersonalEquiposEquipos();
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            try{
                PreparedStatement queryDelete;
                try {
                    //Eliminamos las liquidaciones que tenga el Id de la configuración de liquidación.
                    queryDelete = conexion.prepareStatement("DELETE FROM ["+DB+"].[dbo].[liquidacion] WHERE [liq_config_liquidacion_cdgo]=?;");
                    queryDelete.setString(1, listadoLiquidacion.get(0).getConfiguracionLiquidacion().getCodigo());
                    queryDelete.execute();
                    
                    //Eliminamos la consolidados que tenga los Id de la configuración de liquidación
                    queryDelete = conexion.prepareStatement("DELETE FROM ["+DB+"].[dbo].[consolidado_liquidacion] WHERE [cliq_config_liquidacion_cdgo]=?;");
                    queryDelete.setString(1, listadoLiquidacion.get(0).getConfiguracionLiquidacion().getCodigo());
                    queryDelete.execute(); 
                      
                } catch (SQLException ex) {
                    Logger.getLogger(ControlDB_LiquidacionPalero.class.getName()).log(Level.SEVERE, null, ex);
                }       
                //Abrimos la conexión nuevamente
                conexion= control.ConectarBaseDatos();
                for(Liquidacion objeto: listadoLiquidacion){
                    if(listadoEquipoTrabajador != null){
                        String codigoTipoDocPersona = objeto.getPersona().getCodigo()+"-"+objeto.getPersona().getTipoDocumento().getCodigo();
                        for(String data : listadoEquipoTrabajador){
                            if(codigoTipoDocPersona.equals(data.split("@@")[0])){
                                Equipo equiTem = new Equipo();
                                equiTem.setCodigo(data.split("@@")[1]);
                                objeto.getPersona().setEquipo(equiTem);
                            }
                        }
                    }
                    //objeto.getPersona().setEquipo(new ControlDB_Equipo(tipoConexion).retornoEquipoTrabajo(objeto.getPersona()));
                    //Abrimos la conexión nuevamente
                    conexion= control.ConectarBaseDatos();
                    
                    String codigoEquipo="";
                    try{
                        if(objeto.getPersona().getEquipo() != null){
                            codigoEquipo="'"+objeto.getPersona().getEquipo().getCodigo()+"'";   
                        }else{
                            codigoEquipo= "NULL";   
                        }    
                    }catch(Exception e){
                        codigoEquipo= "NULL";
                    }
                    
                    
                    PreparedStatement Query= conexion.prepareStatement("INSERT INTO [dbo].[liquidacion]\n" +
                                                    "           ([liq_cdgo]\n" +
                                                    "           ,[liq_config_liquidacion_cdgo]\n" +
                                                    "           ,[liq_fecha]\n" +
                                                    "           ,[liq_persona_cdgo]\n" +
                                                    "           ,[liq_persona_tipo_documento_cdgo]\n" +
                                                    "           ,[liq_cant_tonelada_acumulada]\n" +
                                                    "           ,[liq_cant_tonelada_salario]\n" +
                                                    "           ,[liq_cant_tonelada_pagar]\n" +
                                                    "           ,[liq_total_valor_pagar]\n" +
                                                    "           ,[liq_estad],[liq_dias_laborados],[liq_equipo_cdgo])\n" +
                                                    "     VALUES( "
                    + "(SELECT (CASE WHEN (MAX([liq_cdgo]) IS NULL) THEN 1 ELSE (MAX([liq_cdgo])+1) END)AS [liq_cdgo] FROM ["+DB+"].[dbo].[liquidacion]),"
                            + "?,(SELECT SYSDATETIME()),?,?,?,?,?,?,?,?,"+codigoEquipo+");");
                    Query.setString(1, objeto.getConfiguracionLiquidacion().getCodigo());
                    Query.setString(2, objeto.getPersona().getCodigo());           
                    Query.setString(3, objeto.getPersona().getTipoDocumento().getCodigo());           
                    Query.setString(4, objeto.getCantidadToneladaAcumulada());           
                    Query.setString(5, objeto.getCantidadToneladaSalario());           
                    Query.setString(6, objeto.getCantidadToneladaAPagar());           
                    Query.setString(7, objeto.getTotalValorAPagar());           
                    Query.setString(8, "1");           
                    Query.setString(9, objeto.getCantidadDiasLaborados());  
                    Query.execute();
                    result=1;
                }
                
                for(ConsolidadoLiquidacion objeto: listadoConsolidadoLiquidacion){
                    Equipo equipoTemp = null;
                    if(listadoEquipoTrabajador != null){
                        String codigoTipoDocPersona = objeto.getPersona().getCodigo()+"-"+objeto.getPersona().getTipoDocumento().getCodigo();
                        for(String info : listadoEquipoTrabajador){
                            if(codigoTipoDocPersona.equals(info.split("@@")[0])){
                                equipoTemp = new Equipo();
                                equipoTemp.setCodigo(info.split("@@")[1]);
                            }
                        }
                    }
                    
                    
                    //Equipo equipoTemp = new ControlDB_Equipo(tipoConexion).retornoEquipoTrabajo(objeto.getPersona());
                    String codigoEquipoCL="";
                    try{
                        if(equipoTemp != null){
                            codigoEquipoCL="'"+equipoTemp.getCodigo()+"'";   
                        }else{
                            codigoEquipoCL= "NULL";   
                        }    
                    }catch(Exception e){
                        codigoEquipoCL= "NULL";
                    }
                    
                    PreparedStatement Query= conexion.prepareStatement("INSERT INTO [dbo].[consolidado_liquidacion]\n" +
                                                    "           ([cliq_cdgo]\n" +
                                                    "      ,[cliq_config_liquidacion_cdgo]\n" +
                                                    "      ,[cliq_persona_cdgo]\n" +
                                                    "      ,[cliq_persona_tipo_documento_cdgo]\n" +
                                                    "      ,[cliq_fecha]\n" +
                                                    "      ,[cliq_cant_vehiculos_descargados]\n" +
                                                    "      ,[cliq_desc_vehiculos_descargados]\n" +
                                                    "      ,[cliq_cant_toneladas_descargadas]\n" +
                                                    "      ,[cliq_cant_toneladas_asignadas]\n" +
                                                    "      ,[cliq_estad],[cliq_equipo_cdgo])\n" +
                                                    "     VALUES( "
                    + "(SELECT (CASE WHEN (MAX([cliq_cdgo]) IS NULL) THEN 1 ELSE (MAX([cliq_cdgo])+1) END) AS [cliq_cdgo] FROM ["+DB+"].[dbo].[consolidado_liquidacion]),"
                            + "?,?,?,?,?,?,?,?,?,"+codigoEquipoCL+");");
                    Query.setString(1, objeto.getConfiguracionLiquidacion().getCodigo());
                    Query.setString(2, objeto.getPersona().getCodigo());           
                    Query.setString(3, objeto.getPersona().getTipoDocumento().getCodigo());           
                    Query.setString(4, objeto.getFecha());           
                    Query.setString(5, objeto.getCantidadVehiculosDescargados());           
                    Query.setString(6, objeto.getDescripcionVehiculosDescargados());           
                    Query.setString(7, objeto.getCantidadToneladasDescargadas());           
                    Query.setString(8, objeto.getCantidadToneladasAsignadas());           
                    Query.setString(9, "1");           
                    Query.execute();
                    result=1;
                } 
            }catch(Exception e){
                e.printStackTrace();
                result=0;
            }
            if(result==1){
                result=0;
                
                //Procedemos a inactivar la configuración de liquidación
                ConfiguracionLiquidacion configuracionLiquidacionAInactivar =listadoLiquidacion.get(0).getConfiguracionLiquidacion();
                configuracionLiquidacionAInactivar.setEstado("ACTIVO");
                new ControlDB_ConfiguracionLiquidacion(tipoConexion).actualizar(configuracionLiquidacionAInactivar, us);
                conexion= control.ConectarBaseDatos();     
                
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
                    "           ,'LIQUIDACION'" +
                    "           ,CONCAT (?,?,?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, listadoLiquidacion.get(0).getConfiguracionLiquidacion().getCodigo());
                Query_Auditoria.setString(6, "Se registró un nueva liquidacion en el sistema para la configuración de liquidación con código: ");
                Query_Auditoria.setString(7, listadoLiquidacion.get(0).getConfiguracionLiquidacion().getCodigo());
                Query_Auditoria.setString(8, " Descripción: "+ listadoLiquidacion.get(0).getConfiguracionLiquidacion().getDescripcion());
                Query_Auditoria.execute();
                result=1;
            }   
        }
        catch (SQLException sqlException ){   
            result=0;
            sqlException.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return result;
    }
    
}
