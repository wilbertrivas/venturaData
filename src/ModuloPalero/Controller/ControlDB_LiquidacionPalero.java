package ModuloPalero.Controller;
  
import ConnectionDB.Conexion_DB_costos_vg;
import Catalogo.Model.Articulo;
import Catalogo.Model.BaseDatos;
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import Catalogo.Model.Cliente;
import Catalogo.Model.Compañia;
import Catalogo.Model.Equipo;
import Catalogo.Model.LaborRealizada;
import Catalogo.Model.TipoArticulo;
import Catalogo.Model.TipoEquipo;
import Catalogo.Model.Transportadora;
import ModuloCarbon.Model.MvtoCarbon;
import ModuloEquipo.Model.AsignacionEquipo;
import ModuloEquipo.Model.MvtoEquipo;
import ModuloPalero.Model.ConfiguracionLiquidacion;
import ModuloPalero.Model.EquipoLiquidacion;
import ModuloPalero.Model.MvtoCarbon_ListadoEquipos_LiquidacionPaleros;
import ModuloPalero.Model.MvtoPaleroPreliquidacionTEMP;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
                for(MvtoCarbon_ListadoEquipos_LiquidacionPaleros objeto : listadoObjetos){
                    if(objeto.getColor()==null){
                        if(Collections.frequency(listadoCodigoMvtoCarbon, objeto.getMvtoCarbon().getCodigo())> 1){
                            objeto.setColor("ROJO");
                        }else{
                            objeto.setColor("VERDE");
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
}
