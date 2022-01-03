package Sistema.Controller;
 
import ConnectionDB.Conexion_DB_ccargaGP;
import ConnectionDB.Conexion_DB_costos_vg;
import Sistema.Model.Perfil;
import Sistema.Model.Permisos;
import Sistema.Model.Usuario;
import Sistema.View.EncriptarPassword;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ControlDB_PanelControl {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private Conexion_DB_ccargaGP controlGP=null;
    public                      ControlDB_PanelControl(String tipoConexion) {
        control = new Conexion_DB_costos_vg(tipoConexion);
        controlGP = new Conexion_DB_ccargaGP(tipoConexion);
    }
    
    public ArrayList<String>   HistoricoVehiculosDescargados(String script) throws SQLException{
        ArrayList<String> result = null;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            System.out.print("SELECT \n" +
                                                                    "		CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue])) as Fecha,\n" +
                                                                    "		COUNT (CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue])) ) AS 'CANT VEHÍCULOS' ,cl_desc \n" +
                                                                    "  FROM ["+DB+"].[dbo].[mvto_carbon] \n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[cliente] ON [cl_cdgo]=mc_cliente_cdgo AND [mc_cliente_base_datos_cdgo]=[cl_base_datos_cdgo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=mc_articulo_cdgo AND [mc_articulo_base_datos_cdgo]=[ar_base_datos_cdgo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[listado_zona_trabajo] ON [mc_cntro_cost_auxiliar_cdgo]=[lzt_cntro_cost_auxiliar_cdgo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[zona_trabajo] ON [zt_cdgo]=[lzt_zona_trabajo_cdgo]\n" +
                                                                    "  WHERE mc_estad_mvto_carbon_cdgo=1 "+script+
                                                                    "  GROUP BY CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue])),cl_desc;");
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT \n" +
                                                                    "		CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue])) as Fecha,\n" +
                                                                    "		COUNT (CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue])) ) AS 'CANT VEHÍCULOS' ,cl_desc \n" +
                                                                    "  FROM ["+DB+"].[dbo].[mvto_carbon] \n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[cliente] ON [cl_cdgo]=mc_cliente_cdgo AND [mc_cliente_base_datos_cdgo]=[cl_base_datos_cdgo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=mc_articulo_cdgo AND [mc_articulo_base_datos_cdgo]=[ar_base_datos_cdgo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[listado_zona_trabajo] ON [mc_cntro_cost_auxiliar_cdgo]=[lzt_cntro_cost_auxiliar_cdgo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[zona_trabajo] ON [zt_cdgo]=[lzt_zona_trabajo_cdgo]\n" +
                                                                    "  WHERE mc_estad_mvto_carbon_cdgo=1 "+script+
                                                                    "  GROUP BY CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue])),cl_desc ORDER BY cl_desc;");
            /*PreparedStatement queryBuscar= conexion.prepareStatement("SELECT \n" +
                                                                    "		CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue])) as Fecha,\n" +
                                                                    "		COUNT (CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue])) ) AS 'CANT VEHÍCULOS'\n" +
                                                                    "  FROM ["+DB+"].[dbo].[mvto_carbon] \n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[cliente] ON [cl_cdgo]=mc_cliente_cdgo AND [mc_cliente_base_datos_cdgo]=[cl_base_datos_cdgo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=mc_articulo_cdgo AND [mc_articulo_base_datos_cdgo]=[ar_base_datos_cdgo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[listado_zona_trabajo] ON [mc_cntro_cost_auxiliar_cdgo]=[lzt_cntro_cost_auxiliar_cdgo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[zona_trabajo] ON [zt_cdgo]=[lzt_zona_trabajo_cdgo]\n" +
                                                                    "  WHERE mc_estad_mvto_carbon_cdgo=1 "+script+
                                                                    "  GROUP BY CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue]));");*/
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            boolean validar=true;
            while(resultSetBuscar.next()){  
                if(validar){
                    result=new ArrayList();
                    validar=false;
                }
                result.add(resultSetBuscar.getString(1)+"###"+resultSetBuscar.getString(2)+"###"+resultSetBuscar.getString(3));
                System.out.println(resultSetBuscar.getString(1)+"###"+resultSetBuscar.getString(2)+"###"+resultSetBuscar.getString(3)+"=============>Query");
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las cantidades de vehiculos por fechas");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return result;
    }   
    public ArrayList<String>   HistoricoVehiculosDescargadosVenturaVSControlCargaGP(String scriptVenturaData, String scriptControlcargaGP) throws SQLException{
        ArrayList<String> result = null;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        String DB_GP= controlGP.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("	"
                    + "                 SELECT \n" +
                                    "            CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue])) as Fecha,\n" +
                                    "            COUNT (CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue])) ) AS 'CANT VEHÍCULOS', 'VENTURA_DATA' AS systema\n" +
                                    "	FROM ["+DB+"].[dbo].[mvto_carbon] \n" +
                                    "            INNER JOIN ["+DB+"].[dbo].[cliente] ON [cl_cdgo]=mc_cliente_cdgo AND [mc_cliente_base_datos_cdgo]=[cl_base_datos_cdgo]\n" +
                                    "            INNER JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=mc_articulo_cdgo AND [mc_articulo_base_datos_cdgo]=[ar_base_datos_cdgo]\n" +
                                    "            INNER JOIN ["+DB+"].[dbo].[listado_zona_trabajo] ON [mc_cntro_cost_auxiliar_cdgo]=[lzt_cntro_cost_auxiliar_cdgo]\n" +
                                    "            INNER JOIN ["+DB+"].[dbo].[zona_trabajo] ON [zt_cdgo]=[lzt_zona_trabajo_cdgo]\n" +
                                    "    WHERE mc_estad_mvto_carbon_cdgo=1 "+scriptVenturaData+
                                    "       GROUP BY CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue])) \n" +
                                    "UNION \n" +
                                    "	SELECT \n" +
                                    "            CONCAT(YEAR([ti_fcha_slda]),'-',MONTH([ti_fcha_slda]), '-', Day([ti_fcha_slda])) as Fecha,\n" +
                                    "            COUNT (CONCAT(YEAR([ti_fcha_slda]),'-',MONTH([ti_fcha_slda]), '-', Day([ti_fcha_slda])) ) AS 'CANT VEHÍCULOS', 'CONTROLCARGA_GP' AS systema\n" +
                                    "	FROM ["+DB_GP+"].[dbo].[tqte] \n" +
                                    "   WHERE [ti_actvo]=1 AND [ti_cncpto]=107 AND [ti_dpsto] <> '01' AND [ti_dpsto] <> '03' AND [ti_dpsto] <> '07' "+scriptControlcargaGP+
                                    "       GROUP BY CONCAT(YEAR([ti_fcha_slda]),'-',MONTH([ti_fcha_slda]), '-', Day([ti_fcha_slda]))  order by systema DESC;");
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            boolean validar=true;
            while(resultSetBuscar.next()){  
                if(validar){
                    result=new ArrayList();
                    validar=false;
                }
                result.add(resultSetBuscar.getString(1)+"###"+resultSetBuscar.getString(2)+"###"+resultSetBuscar.getString(3));
                System.out.println(resultSetBuscar.getString(1)+"###"+resultSetBuscar.getString(2)+"###"+resultSetBuscar.getString(3)+"=============>Query");
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las cantidades de vehiculos por fechas");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return result;
    }   
    public ArrayList<String>   HistoricoVehiculosDescargadosVenturaVSControlCargaGP_Backup(String scriptVenturaData, String scriptControlcargaGP) throws SQLException{
        ArrayList<String> result = null;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        String DB_GP= controlGP.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("	"
                    + "                 SELECT \n" +
                                    "            CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue])) as Fecha,\n" +
                                    "            COUNT (CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue])) ) AS 'CANT VEHÍCULOS', 'VENTURA_DATA' AS systema\n" +
                                    "	FROM ["+DB+"].[dbo].[mvto_carbon] \n" +
                                    "            INNER JOIN ["+DB+"].[dbo].[cliente] ON [cl_cdgo]=mc_cliente_cdgo AND [mc_cliente_base_datos_cdgo]=[cl_base_datos_cdgo]\n" +
                                    "            INNER JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=mc_articulo_cdgo AND [mc_articulo_base_datos_cdgo]=[ar_base_datos_cdgo]\n" +
                                    "            INNER JOIN ["+DB+"].[dbo].[listado_zona_trabajo] ON [mc_cntro_cost_auxiliar_cdgo]=[lzt_cntro_cost_auxiliar_cdgo]\n" +
                                    "            INNER JOIN ["+DB+"].[dbo].[zona_trabajo] ON [zt_cdgo]=[lzt_zona_trabajo_cdgo]\n" +
                                    "    WHERE mc_estad_mvto_carbon_cdgo=1 "+scriptVenturaData+
                                    "       GROUP BY CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue])) \n" +
                                    "UNION \n" +
                                    "	SELECT \n" +
                                    "            CONCAT(YEAR([ti_fcha]),'-',MONTH([ti_fcha]), '-', Day([ti_fcha])) as Fecha,\n" +
                                    "            COUNT (CONCAT(YEAR([ti_fcha]),'-',MONTH([ti_fcha]), '-', Day([ti_fcha])) ) AS 'CANT VEHÍCULOS', 'CONTROLCARGA_GP' AS systema\n" +
                                    "	FROM ["+DB_GP+"].[dbo].[tqte] \n" +
                                    "   WHERE [ti_actvo]=1 AND [ti_cncpto]=107 "+scriptControlcargaGP+
                                    "       GROUP BY CONCAT(YEAR([ti_fcha]),'-',MONTH([ti_fcha]), '-', Day([ti_fcha]))  order by systema ASC;");
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            boolean validar=true;
            while(resultSetBuscar.next()){  
                if(validar){
                    result=new ArrayList();
                    validar=false;
                }
                result.add(resultSetBuscar.getString(1)+"###"+resultSetBuscar.getString(2)+"###"+resultSetBuscar.getString(3));
                System.out.println(resultSetBuscar.getString(1)+"###"+resultSetBuscar.getString(2)+"###"+resultSetBuscar.getString(3)+"=============>Query");
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las cantidades de vehiculos por fechas");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return result;
    } 
    public ArrayList<String>   HistoricoVehiculosToneladasDescargadas(String script) throws SQLException{
        ArrayList<String> result = null;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{

            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT \n" +
                                                                    "    CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue])) as Fecha, \n" +
                                                                    "	--SUM([mc_peso_neto]),\n" +
                                                                    "	CONVERT(FLOAT,(SUM([mc_peso_neto])/1000.00)) AS 'TONELADAS',\n" +
                                                                    "	cl_desc\n" +
                                                                    "FROM ["+DB+"].[dbo].[mvto_carbon]  \n" +
                                                                    "    INNER JOIN ["+DB+"].[dbo].[cliente] ON [cl_cdgo]=mc_cliente_cdgo AND [mc_cliente_base_datos_cdgo]=[cl_base_datos_cdgo] \n" +
                                                                    "    INNER JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=mc_articulo_cdgo AND [mc_articulo_base_datos_cdgo]=[ar_base_datos_cdgo] \n" +
                                                                    "    INNER JOIN ["+DB+"].[dbo].[listado_zona_trabajo] ON [mc_cntro_cost_auxiliar_cdgo]=[lzt_cntro_cost_auxiliar_cdgo] \n" +
                                                                    "    INNER JOIN ["+DB+"].[dbo].[zona_trabajo] ON [zt_cdgo]=[lzt_zona_trabajo_cdgo] \n" +
                                                                    "WHERE mc_estad_mvto_carbon_cdgo=1 "+script+
                                                                    "GROUP BY CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue])),cl_desc  ORDER BY cl_desc;");
          ResultSet resultSetBuscar=queryBuscar.executeQuery();
            boolean validar=true;
            while(resultSetBuscar.next()){  
                if(validar){
                    result=new ArrayList();
                    validar=false;
                }
                result.add(resultSetBuscar.getString(1)+"###"+resultSetBuscar.getString(2)+"###"+resultSetBuscar.getString(3));
                System.out.println(resultSetBuscar.getString(1)+"###"+resultSetBuscar.getString(2)+"###"+resultSetBuscar.getString(3)+"=============>Query");
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las cantidades de vehiculos por fechas");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return result;
    }  
    public ArrayList<String>   HistoricoVehiculosDescargadosPorAuxiliar(String script) throws SQLException{
        ArrayList<String> result = null;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT \n" +
                                                                    "    CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue])) as 'FECHA', \n" +
                                                                    "	COUNT ([mc_fecha_fin_descargue]) AS 'VEHICULOS DESCARGADOS',\n" +
                                                                    "	CONCAT([us_nombres]\n" +
                                                                    "      ,[us_apellidos]) AS 'NOMBRE AUXILIAR'\n" +
                                                                    "FROM ["+DB+"].[dbo].[mvto_carbon]  \n" +
                                                                    "    INNER JOIN ["+DB+"].[dbo].[cliente] ON [cl_cdgo]=mc_cliente_cdgo AND [mc_cliente_base_datos_cdgo]=[cl_base_datos_cdgo] \n" +
                                                                    "    INNER JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=mc_articulo_cdgo AND [mc_articulo_base_datos_cdgo]=[ar_base_datos_cdgo] \n" +
                                                                    "    INNER JOIN ["+DB+"].[dbo].[listado_zona_trabajo] ON [mc_cntro_cost_auxiliar_cdgo]=[lzt_cntro_cost_auxiliar_cdgo] \n" +
                                                                    "    INNER JOIN ["+DB+"].[dbo].[zona_trabajo] ON [zt_cdgo]=[lzt_zona_trabajo_cdgo]\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[usuario] ON [us_cdgo]=[mc_usuario_cdgo]\n" +
                                                                    "WHERE mc_estad_mvto_carbon_cdgo=1 "+script+
                                                                    "GROUP BY CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue]))\n" +
                                                                    ",CONCAT([us_nombres]\n" +
                                                                    "      ,[us_apellidos])\n" +
                                                                    " ORDER BY CONCAT([us_nombres],[us_apellidos]);");
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            boolean validar=true;
            while(resultSetBuscar.next()){  
                if(validar){
                    result=new ArrayList();
                    validar=false;
                }
                result.add(resultSetBuscar.getString(1)+"###"+resultSetBuscar.getString(2)+"###"+resultSetBuscar.getString(3));
                System.out.println(resultSetBuscar.getString(1)+"###"+resultSetBuscar.getString(2)+"###"+resultSetBuscar.getString(3)+"=============>Query");
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las cantidades de vehiculos por fechas");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return result;
    }   
    public ArrayList<String>   HistoricoRecaudoLavado(String script) throws SQLException{
        ArrayList<String> result = null;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{

            PreparedStatement queryBuscar= conexion.prepareStatement(""
                    + "SELECT  \n" +
                    "    CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue])) as 'FECHA',   \n" +
                    "    (SUM([mc_valorRecaudoEmpresa_lavadoVehiculo])) AS 'RECAUDO EMPRESA', 'EMPRESA' AS 'QuienRealizaLavado'\n" +
                    "FROM ["+DB+"].[dbo].[mvto_carbon]   \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cliente] ON [cl_cdgo]=mc_cliente_cdgo AND [mc_cliente_base_datos_cdgo]=[cl_base_datos_cdgo]  \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=mc_articulo_cdgo AND [mc_articulo_base_datos_cdgo]=[ar_base_datos_cdgo]  \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[listado_zona_trabajo] ON [mc_cntro_cost_auxiliar_cdgo]=[lzt_cntro_cost_auxiliar_cdgo]  \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[zona_trabajo] ON [zt_cdgo]=[lzt_zona_trabajo_cdgo]  \n" +
                    "WHERE mc_estad_mvto_carbon_cdgo=1 AND [mc_valorRecaudoEmpresa_lavadoVehiculo] IS NOT NULL "+script+
                    "GROUP BY CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue]))\n" +
                    "--ORDER BY CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue]));\n" +
                    "\n" +
                    "UNION\n" +
                    "\n" +
                    "SELECT  \n" +
                    "    CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue])) as 'FECHA',   \n" +
                    "    (SUM([mc_valorRecaudoEquipo_lavadoVehiculo])) AS 'RECAUDO EQUIPO','EXTERNO' AS 'QuienRealizaLavado' \n" +
                    "FROM ["+DB+"].[dbo].[mvto_carbon]   \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[cliente] ON [cl_cdgo]=mc_cliente_cdgo AND [mc_cliente_base_datos_cdgo]=[cl_base_datos_cdgo]  \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=mc_articulo_cdgo AND [mc_articulo_base_datos_cdgo]=[ar_base_datos_cdgo]  \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[listado_zona_trabajo] ON [mc_cntro_cost_auxiliar_cdgo]=[lzt_cntro_cost_auxiliar_cdgo]  \n" +
                    "    INNER JOIN ["+DB+"].[dbo].[zona_trabajo] ON [zt_cdgo]=[lzt_zona_trabajo_cdgo]  \n" +
                    "WHERE mc_estad_mvto_carbon_cdgo=1 AND [mc_valorRecaudoEquipo_lavadoVehiculo] IS NOT NULL "+script+
                    "GROUP BY CONCAT(YEAR([mc_fecha_inicio_descargue]),'-',MONTH([mc_fecha_inicio_descargue]), '-', Day([mc_fecha_inicio_descargue]))\n" +
                    "\n" +
                    "ORDER BY 'QuienRealizaLavado';");
          ResultSet resultSetBuscar=queryBuscar.executeQuery();
            boolean validar=true;
            while(resultSetBuscar.next()){  
                if(validar){
                    result=new ArrayList();
                    validar=false;
                }
                result.add(resultSetBuscar.getString(1)+"###"+resultSetBuscar.getString(2)+"###"+resultSetBuscar.getString(3));
                System.out.println(resultSetBuscar.getString(1)+"###"+resultSetBuscar.getString(2)+"###"+resultSetBuscar.getString(3)+"=============>Query");
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los recaudos pór lavados");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return result;
    }  
    public ArrayList<String>   HistoricoVehiculosDescargadosPorMes(String script) throws SQLException{
        ArrayList<String> result = null;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            /*PreparedStatement queryBuscar= conexion.prepareStatement(""
                        + "  SET LANGUAGE 'SPANISH'\n" +
                        "  SELECT  \n" +
                        "		CONCAT (YEAR([mc_fecha_inicio_descargue]),'-', UPPER(DATENAME(MONTH, [mc_fecha_inicio_descargue]))) AS 'AÑO-MES'\n" +
                        "		,COUNT([mc_placa_vehiculo]) AS 'CANTIDAD VEHÍCULOS'\n" +
                        "  FROM ["+DB+"].[dbo].[mvto_carbon]\n" +
                        "    INNER JOIN ["+DB+"].[dbo].[cliente] ON [cl_cdgo]=mc_cliente_cdgo AND [mc_cliente_base_datos_cdgo]=[cl_base_datos_cdgo]  \n" +
                        "    INNER JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=mc_articulo_cdgo AND [mc_articulo_base_datos_cdgo]=[ar_base_datos_cdgo]  \n" +
                        "    INNER JOIN ["+DB+"].[dbo].[listado_zona_trabajo] ON [mc_cntro_cost_auxiliar_cdgo]=[lzt_cntro_cost_auxiliar_cdgo]  \n" +
                        "    INNER JOIN ["+DB+"].[dbo].[zona_trabajo] ON [zt_cdgo]=[lzt_zona_trabajo_cdgo]  \n" +
                        "  WHERE mc_estad_mvto_carbon_cdgo=1 "+script+
                        "  GROUP BY CONCAT (YEAR([mc_fecha_inicio_descargue]),'-', UPPER(DATENAME(MONTH, [mc_fecha_inicio_descargue]))) \n" +
                        "  ORDER BY CONCAT (YEAR([mc_fecha_inicio_descargue]),'-', UPPER(DATENAME(MONTH, [mc_fecha_inicio_descargue])))");*/
            System.out.println("SELECT  \n" +
                        "		CONCAT (YEAR([mc_fecha_inicio_descargue]),'-', (MONTH([mc_fecha_inicio_descargue]))) AS 'AÑO-MES'\n" +
                        "		,COUNT([mc_placa_vehiculo]) AS 'CANTIDAD VEHÍCULOS'\n" +
                        "  FROM ["+DB+"].[dbo].[mvto_carbon]\n" +
                        "    INNER JOIN ["+DB+"].[dbo].[cliente] ON [cl_cdgo]=mc_cliente_cdgo AND [mc_cliente_base_datos_cdgo]=[cl_base_datos_cdgo]  \n" +
                        "    INNER JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=mc_articulo_cdgo AND [mc_articulo_base_datos_cdgo]=[ar_base_datos_cdgo]  \n" +
                        "    INNER JOIN ["+DB+"].[dbo].[listado_zona_trabajo] ON [mc_cntro_cost_auxiliar_cdgo]=[lzt_cntro_cost_auxiliar_cdgo]  \n" +
                        "    INNER JOIN ["+DB+"].[dbo].[zona_trabajo] ON [zt_cdgo]=[lzt_zona_trabajo_cdgo]  \n" +
                        "  WHERE mc_estad_mvto_carbon_cdgo=1 "+script+
                        "  GROUP BY CONCAT (YEAR([mc_fecha_inicio_descargue]),'-', (MONTH([mc_fecha_inicio_descargue]))) \n" +
                        "  ORDER BY CONCAT (YEAR([mc_fecha_inicio_descargue]),'-', (MONTH([mc_fecha_inicio_descargue])))");
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT  \n" +
                        "		CONCAT (YEAR([mc_fecha_inicio_descargue]),'-', (MONTH([mc_fecha_inicio_descargue]))) AS 'AÑO-MES'\n" +
                        "		,COUNT([mc_placa_vehiculo]) AS 'CANTIDAD VEHÍCULOS'\n" +
                        "  FROM ["+DB+"].[dbo].[mvto_carbon]\n" +
                        "    INNER JOIN ["+DB+"].[dbo].[cliente] ON [cl_cdgo]=mc_cliente_cdgo AND [mc_cliente_base_datos_cdgo]=[cl_base_datos_cdgo]  \n" +
                        "    INNER JOIN ["+DB+"].[dbo].[articulo] ON [ar_cdgo]=mc_articulo_cdgo AND [mc_articulo_base_datos_cdgo]=[ar_base_datos_cdgo]  \n" +
                        "    INNER JOIN ["+DB+"].[dbo].[listado_zona_trabajo] ON [mc_cntro_cost_auxiliar_cdgo]=[lzt_cntro_cost_auxiliar_cdgo]  \n" +
                        "    INNER JOIN ["+DB+"].[dbo].[zona_trabajo] ON [zt_cdgo]=[lzt_zona_trabajo_cdgo]  \n" +
                        "  WHERE mc_estad_mvto_carbon_cdgo=1 "+script+
                        "  GROUP BY CONCAT (YEAR([mc_fecha_inicio_descargue]),'-', (MONTH([mc_fecha_inicio_descargue]))) \n" +
                        "  ORDER BY CONCAT (YEAR([mc_fecha_inicio_descargue]),'-', (MONTH([mc_fecha_inicio_descargue])))");
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            boolean validar=true;
            while(resultSetBuscar.next()){  
                if(validar){
                    result=new ArrayList();
                    validar=false;
                    System.out.println("#########################################################");
                }
                System.out.println(resultSetBuscar.getString(1)+"###"+resultSetBuscar.getString(2)+"\n");
                result.add(resultSetBuscar.getString(1)+"###"+resultSetBuscar.getString(2));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las cantidades de vehiculos por fechas");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return result;
    }   
}
