package ModuloPalero.Controller;
  
import ConnectionDB.Conexion_DB_costos_vg;
import Catalogo.Model.Articulo;
import Catalogo.Model.BaseDatos;
import Catalogo.Model.Compañia;
import Catalogo.Model.Equipo;
import Catalogo.Model.TipoArticulo;
import Catalogo.Model.TipoEquipo;
import ModuloPalero.Model.ConfiguracionLiquidacion;
import ModuloPalero.Model.EquipoLiquidacion;
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
import javax.swing.JOptionPane;

public class ControlDB_ConfiguracionLiquidacion {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private final String tipoConexion;
    
    public ControlDB_ConfiguracionLiquidacion(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }  
    public int registrar(ConfiguracionLiquidacion Objeto) throws FileNotFoundException, UnknownHostException, SocketException, SQLException{
        int result=0;
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            String estado="";
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                estado="ACTIVO";
            }else{
                estado="INACTIVO";
            }  

            // conexion= control.ConectarBaseDatos();
            PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[config_liquidacion] ("
                    + "         [cl_cdgo]\n" +
                        "      ,[cl_cargo_nomina_cdgo]\n" +
                        "      ,[cl_fecha_inicio]\n" +
                        "      ,[cl_fecha_fin]\n" +
                        "      ,[cl_cant_dias]\n" +
                        "      ,[cl_ano]\n" +
                        "      ,[cl_desc]\n" +
                        "      ,[cl_valor_tonelada]\n" +
                        "      ,[cl_cant_tonelada_salario]\n" +
                        "      ,[cl_usuario_registra_cdgo]\n" +
                        "      ,[cl_estad]) "
                    + "VALUES ("
                    + "(SELECT (CASE WHEN (MAX([cl_cdgo]) IS NULL) THEN 1 ELSE (MAX([cl_cdgo])+1) END)AS [cl_cdgo] FROM ["+DB+"].[dbo].[config_liquidacion]),"
                            + "?,?,?,((DATEDIFF(DAY, ?, ?))+1),YEAR(?),?,?,?,?,?);");
            Query.setString(1, Objeto.getCargoNomina().getCodigo());
            Query.setString(2, Objeto.getFechaInicio());
            Query.setString(3, Objeto.getFechaFinalizacion());
            Query.setString(4, Objeto.getFechaInicio());
            Query.setString(5, Objeto.getFechaFinalizacion());
            Query.setString(6, Objeto.getFechaInicio());
            Query.setString(7, Objeto.getDescripcion());
            Query.setInt(8, Objeto.getValorTonelada());
            Query.setInt(9, Objeto.getCantidadToneladaSalario());
            Query.setString(10, Objeto.getUsuario().getCodigo());
            Query.setString(11, Objeto.getEstado());
            Query.execute();
            result=1;
            if(result==1){
                result=0;
                //Sacamos el ultimo valor 
                PreparedStatement queryConsultarMaximo= conexion.prepareStatement("SELECT MAX(cl_cdgo) FROM ["+DB+"].[dbo].[config_liquidacion];");
                ResultSet resultSetMaximo= queryConsultarMaximo.executeQuery();
                while(resultSetMaximo.next()){ 
                    if(resultSetMaximo.getString(1) != null){
                        Objeto.setCodigo(resultSetMaximo.getString(1));
                    }
                }
                result=1;
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
                    "           ,'CONFIGURACION_LIQUIDACION'" +
                    "           ,CONCAT (?,?,'Para el cargo:',?, '  Descripcion: ',?,' ValorTonelada: ',?,' CantidadToneladaSalario: ',? ,' Estado: ',?));");
                Query_Auditoria.setString(1, Objeto.getUsuario().getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, Objeto.getCodigo());
                Query_Auditoria.setString(6, "Se registró un nueva configuracion de liquidacion con Código: ");
                Query_Auditoria.setString(7, Objeto.getCodigo());
                Query_Auditoria.setString(8, Objeto.getCargoNomina().getDescripcion());
                Query_Auditoria.setString(9, Objeto.getDescripcion());
                Query_Auditoria.setInt(10, Objeto.getValorTonelada());
                Query_Auditoria.setInt(11, Objeto.getCantidadToneladaSalario());
                Query_Auditoria.setString(12, estado);
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
    public ArrayList<ConfiguracionLiquidacion>     buscarConfiguracionesLiquidaciones (String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<ConfiguracionLiquidacion> listadoObjetos = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT [cl_cdgo] --1\n" +
                                                            "      ,[cl_cargo_nomina_cdgo]--2\n" +
                                                            "		  ,[cn_cdgo]--3\n" +
                                                            "		  ,[cn_desc]--4\n" +
                                                            "		  ,[cn_estad]--5\n" +
                                                            "      ,[cl_fecha_inicio]--6\n" +
                                                            "      ,[cl_fecha_fin]--7\n" +
                                                            "      ,[cl_cant_dias]--8\n" +
                                                            "      ,[cl_ano]--9\n" +
                                                            "      ,[cl_desc]--10\n" +
                                                            "      ,[cl_valor_tonelada]--11\n" +
                                                            "      ,[cl_cant_tonelada_salario]--12\n" +
                                                            "      ,[cl_usuario_registra_cdgo]--13\n" +
                                                            "		  ,[us_cdgo]--14\n" +
                                                            "		  ,[us_nombres]--15\n" +
                                                            "		  ,[us_apellidos]--16\n" +
                                                            "		  ,[us_correo]--17\n" +
                                                            "  ,CASE WHEN (cl_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS cl_estad --18\n" +
                                                            "  FROM ["+DB+"].[dbo].[config_liquidacion]\n" +
                                                            "  INNER JOIN ["+DB+"].[dbo].[cargo_nomina] ON [cl_cargo_nomina_cdgo]=[cn_cdgo]\n" +
                                                            "  INNER JOIN ["+DB+"].[dbo].[usuario] ON [cl_usuario_registra_cdgo]=[us_cdgo] "
                                                            + " WHERE (([cl_fecha_inicio] BETWEEN ? AND ?) OR (? BETWEEN [cl_fecha_inicio] AND [cl_fecha_fin]) OR  (? BETWEEN [cl_fecha_inicio] AND [cl_fecha_fin]))ORDER BY [cl_cdgo] DESC");
            query.setString(1, DatetimeInicio);
            query.setString(2, DatetimeFin);
            query.setString(3, DatetimeInicio);
            query.setString(4, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();    
            boolean validar= true;
            while(resultSet.next()){ 
                try{
                    if(validar){
                        listadoObjetos= new ArrayList<>();
                        validar= false;
                    }
                    ConfiguracionLiquidacion configuracionLiquidacion = new ConfiguracionLiquidacion();
                    configuracionLiquidacion.setCodigo(resultSet.getString(1));
                    configuracionLiquidacion.setCargoNomina(new CargoNomina(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
                    configuracionLiquidacion.setFechaInicio(resultSet.getString(6));
                    configuracionLiquidacion.setFechaFinalizacion(resultSet.getString(7));
                    configuracionLiquidacion.setCantidadDias(Integer.parseInt(resultSet.getString(8)));
                    configuracionLiquidacion.setCantidadDias(Integer.parseInt(resultSet.getString(8)));
                    configuracionLiquidacion.setAno(resultSet.getString(9));
                    configuracionLiquidacion.setDescripcion(resultSet.getString(10));
                    configuracionLiquidacion.setValorTonelada(Integer.parseInt(resultSet.getString(11)));
                    configuracionLiquidacion.setCantidadToneladaSalario(Integer.parseInt(resultSet.getString(12)));
                        Usuario usuario = new Usuario();
                            usuario.setCodigo(resultSet.getString(14));
                            usuario.setNombres(resultSet.getString(15));
                            usuario.setApellidos(resultSet.getString(16));
                            usuario.setCorreo(resultSet.getString(17));
                    configuracionLiquidacion.setUsuario(usuario);
                    configuracionLiquidacion.setEstado(resultSet.getString(18));           
                    listadoObjetos.add(configuracionLiquidacion);    
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las configuraciones de liquidación");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }
    public ArrayList<ConfiguracionLiquidacion>     buscarConfiguracionesLiquidacionesActivas (String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<ConfiguracionLiquidacion> listadoObjetos = null;
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT [cl_cdgo] --1\n" +
                                                            "      ,[cl_cargo_nomina_cdgo]--2\n" +
                                                            "		  ,[cn_cdgo]--3\n" +
                                                            "		  ,[cn_desc]--4\n" +
                                                            "		  ,[cn_estad]--5\n" +
                                                            "      ,[cl_fecha_inicio]--6\n" +
                                                            "      ,[cl_fecha_fin]--7\n" +
                                                            "      ,[cl_cant_dias]--8\n" +
                                                            "      ,[cl_ano]--9\n" +
                                                            "      ,[cl_desc]--10\n" +
                                                            "      ,[cl_valor_tonelada]--11\n" +
                                                            "      ,[cl_cant_tonelada_salario]--12\n" +
                                                            "      ,[cl_usuario_registra_cdgo]--13\n" +
                                                            "		  ,[us_cdgo]--14\n" +
                                                            "		  ,[us_nombres]--15\n" +
                                                            "		  ,[us_apellidos]--16\n" +
                                                            "		  ,[us_correo]--17\n" +
                                                            "  ,CASE WHEN (cl_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS cl_estad --18\n" +
                                                            "  FROM ["+DB+"].[dbo].[config_liquidacion]\n" +
                                                            "  INNER JOIN ["+DB+"].[dbo].[cargo_nomina] ON [cl_cargo_nomina_cdgo]=[cn_cdgo]\n" +
                                                            "  INNER JOIN ["+DB+"].[dbo].[usuario] ON [cl_usuario_registra_cdgo]=[us_cdgo] "
                                                            + " WHERE [cl_estad]= 1 AND (([cl_fecha_inicio] BETWEEN ? AND ?) OR (? BETWEEN [cl_fecha_inicio] AND [cl_fecha_fin]) OR  (? BETWEEN [cl_fecha_inicio] AND [cl_fecha_fin]))ORDER BY [cl_cdgo] DESC");
            query.setString(1, DatetimeInicio);
            query.setString(2, DatetimeFin);
            query.setString(3, DatetimeInicio);
            query.setString(4, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();    
            boolean validar= true;
            while(resultSet.next()){ 
                try{
                    if(validar){
                        listadoObjetos= new ArrayList<>();
                        validar= false;
                    }
                    ConfiguracionLiquidacion configuracionLiquidacion = new ConfiguracionLiquidacion();
                    configuracionLiquidacion.setCodigo(resultSet.getString(1));
                    configuracionLiquidacion.setCargoNomina(new CargoNomina(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
                    configuracionLiquidacion.setFechaInicio(resultSet.getString(6));
                    configuracionLiquidacion.setFechaFinalizacion(resultSet.getString(7));
                    configuracionLiquidacion.setCantidadDias(Integer.parseInt(resultSet.getString(8)));
                    configuracionLiquidacion.setCantidadDias(Integer.parseInt(resultSet.getString(8)));
                    configuracionLiquidacion.setAno(resultSet.getString(9));
                    configuracionLiquidacion.setDescripcion(resultSet.getString(10));
                    configuracionLiquidacion.setValorTonelada(Integer.parseInt(resultSet.getString(11)));
                    configuracionLiquidacion.setCantidadToneladaSalario(Integer.parseInt(resultSet.getString(12)));
                        Usuario usuario = new Usuario();
                            usuario.setCodigo(resultSet.getString(14));
                            usuario.setNombres(resultSet.getString(15));
                            usuario.setApellidos(resultSet.getString(16));
                            usuario.setCorreo(resultSet.getString(17));
                    configuracionLiquidacion.setUsuario(usuario);
                    configuracionLiquidacion.setEstado(resultSet.getString(18));           
                    listadoObjetos.add(configuracionLiquidacion);    
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las configuraciones de liquidación");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }
    public int actualizar(ConfiguracionLiquidacion Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{

            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();      
            PreparedStatement query;
            String valorEstado="";
            if(Objeto.getEstado().equals("ACTIVO")){
                valorEstado="0";
            }else{
                valorEstado="1";
            }
            query= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[config_liquidacion] SET [cl_estad]=? WHERE [cl_cdgo] = ?");
            query.setString(1, valorEstado);
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
                        "           ,'CONFIGURACION_LIQUIDACION'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre el estado de la configuración de liquidación de Código: ',?,' Pasadon de estado:',?,' a estado : ',?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, Objeto.getCodigo());
                Query_Auditoria.setString(6, Objeto.getCodigo());
                if(valorEstado.equals("0")){
                    Query_Auditoria.setString(7, "ACTIVO");
                    Query_Auditoria.setString(8, "INACTIVO");
                }else{
                    Query_Auditoria.setString(7, "INACTIVO");
                    Query_Auditoria.setString(8, "ACTIVO");
                }
                Query_Auditoria.execute();
                result=1;
            }
        }
        catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "ERROR al querer actualizar los datos.");
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    } 
}
