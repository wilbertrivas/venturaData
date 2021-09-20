package ModuloPalero.Controller;
  
import Catalogo.Controller.*;
import ConnectionDB.Conexion_DB_ccargaGP;
import ConnectionDB.Conexion_DB_costos_vg;
import Catalogo.Model.Articulo;
import Catalogo.Model.BaseDatos;
import Catalogo.Model.Compañia;
import Catalogo.Model.Equipo;
import Catalogo.Model.TipoArticulo;
import Catalogo.Model.TipoEquipo;
import ConnectionDB.Conexion_DB_ccargaOPP;
import ModuloPalero.Model.EquipoLiquidacion;
import ModuloPalero.Model.MarcacionArchivo;
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

public class ControlDB_EquipoLiquidacion {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private String tipoConexion;
    
    public ControlDB_EquipoLiquidacion(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    public boolean validarExistencia(Equipo Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[equipos_liquidacion] WHERE [el_equipo_cdgo] LIKE ? ;");
            query.setString(1, Objeto.getCodigo());
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                retorno =true;               
            }
        }catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null, "Error al Tratar de buscar");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return retorno;
    }   
    public int registrar(EquipoLiquidacion Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            /*String estado="";
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                estado="ACTIVO";
            }else{
                estado="INACTIVO";
            }  */
            if(!validarExistencia(Objeto.getEquipo())){
                conexion= control.ConectarBaseDatos();
                PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[equipos_liquidacion] ([el_cdgo] ,[el_equipo_cdgo],[el_estad]) VALUES ("
                        + "(SELECT (CASE WHEN (MAX([el_cdgo]) IS NULL) THEN 1 ELSE (MAX([el_cdgo])+1) END)AS [el_cdgo] FROM ["+DB+"].[dbo].[equipos_liquidacion]),?,?);");
                Query.setString(1, Objeto.getEquipo().getCodigo());
                Query.setString(2, Objeto.getEstado());
                Query.execute();
                result=1;
                if(result==1){
                    result=0;
                    //Sacamos el ultimo valor 
                    PreparedStatement queryConsultarMaximo= conexion.prepareStatement("SELECT MAX(el_cdgo) FROM ["+DB+"].[dbo].[equipos_liquidacion];");
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
                        "           ,'EQUIPO_LIQUIDACIÓN'" +
                        "           ,CONCAT (?,?,' Equipo_Código: ',?,' Descripción: ',?,' Modelo: ',?));");
                    Query_Auditoria.setString(1, us.getCodigo());
                    Query_Auditoria.setString(2, namePc);
                    Query_Auditoria.setString(3, ipPc);
                    Query_Auditoria.setString(4, macPC);
                    Query_Auditoria.setString(5, Objeto.getCodigo());
                    Query_Auditoria.setString(6, "Se registró un nuevo equipo para la liquidación de paleros, con Código: ");
                    Query_Auditoria.setString(7, Objeto.getCodigo());
                    Query_Auditoria.setString(8, Objeto.getEquipo().getCodigo());
                    Query_Auditoria.setString(9, Objeto.getEquipo().getDescripcion());
                    Query_Auditoria.setString(10, Objeto.getEquipo().getModelo());
                    Query_Auditoria.execute();
                    result=1;
                }
            }else{
                result=3;
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
    public ArrayList<EquipoLiquidacion> buscar(String valorConsulta) throws SQLException{
        ArrayList<EquipoLiquidacion> listadoObjetos = null;
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            if(valorConsulta.equals("")){
                PreparedStatement query= conexion.prepareStatement("SELECT [el_cdgo] --1\n" +
                                                                    "      ,[el_equipo_cdgo] --2\n" +
                                                                    "		  ,[eq_cdgo] --3\n" +
                                                                    "		  ,[eq_tipo_equipo_cdgo] --4\n" +
                                                                    "			,[te_cdgo] --5\n" +
                                                                    "			  ,[te_desc] --6\n" +
                                                                    "			  ,[te_estad] --7\n" +
                                                                    "		  ,[eq_codigo_barra] --8\n" +
                                                                    "		  ,[eq_referencia] --9\n" +
                                                                    "		  ,[eq_producto] --10\n" +
                                                                    "		  ,[eq_capacidad] --11\n" +
                                                                    "		  ,[eq_marca] --12\n" +
                                                                    "		  ,[eq_modelo] --13\n" +
                                                                    "		  ,[eq_serial] --14\n" +
                                                                    "		  ,[eq_desc] --15\n" +
                                                                    "      ,CASE WHEN (el_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS el_estad --16\n" +
                                                                    "  FROM ["+DB+"].[dbo].[equipos_liquidacion]\n" +
                                                                    "  INNER JOIN ["+DB+"].[dbo].[equipo] ON [eq_cdgo]=[el_equipo_cdgo]\n" +
                                                                    "  INNER JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo] ;");
                resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexion.prepareStatement("SELECT [el_cdgo] --1\n" +
                                                                    "      ,[el_equipo_cdgo] --2\n" +
                                                                    "		  ,[eq_cdgo] --3\n" +
                                                                    "		  ,[eq_tipo_equipo_cdgo] --4\n" +
                                                                    "			,[te_cdgo] --5\n" +
                                                                    "			  ,[te_desc] --6\n" +
                                                                    "			  ,[te_estad] --7\n" +
                                                                    "		  ,[eq_codigo_barra] --8\n" +
                                                                    "		  ,[eq_referencia] --9\n" +
                                                                    "		  ,[eq_producto] --10\n" +
                                                                    "		  ,[eq_capacidad] --11\n" +
                                                                    "		  ,[eq_marca] --12\n" +
                                                                    "		  ,[eq_modelo] --13\n" +
                                                                    "		  ,[eq_serial] --14\n" +
                                                                    "		  ,[eq_desc] --15\n" +
                                                                    "      ,CASE WHEN (el_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS el_estad --16\n" +
                                                                    "  FROM ["+DB+"].[dbo].[equipos_liquidacion]\n" +
                                                                    "  INNER JOIN ["+DB+"].[dbo].[equipo] ON [eq_cdgo]=[el_equipo_cdgo]\n" +
                                                                    "  INNER JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo]  WHERE ([eq_desc] LIKE ? OR [eq_modelo] LIKE ?) ;");
                query.setString(1, "%"+valorConsulta+"%");
                query.setString(2, "%"+valorConsulta+"%");
                resultSet= query.executeQuery();              
            }
            boolean validar=true;
            while(resultSet.next()){
                if(validar){
                    listadoObjetos= new ArrayList<>();
                    validar=false;
                }
                
                EquipoLiquidacion Objeto = new EquipoLiquidacion(); 
                Objeto.setCodigo(resultSet.getString(1));
                Equipo equipo = new Equipo();
                    equipo.setCodigo(resultSet.getString(3));
                    equipo.setTipoEquipo(new TipoEquipo(resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)));
                    equipo.setCodigo_barra(resultSet.getString(8));
                    equipo.setReferencia(resultSet.getString(9));
                    equipo.setProducto(resultSet.getString(10));
                    equipo.setCapacidad(resultSet.getString(11));
                    equipo.setMarca(resultSet.getString(12));
                    equipo.setModelo(resultSet.getString(13));
                    equipo.setSerial(resultSet.getString(14));
                    equipo.setDescripcion(resultSet.getString(15));
                  Objeto.setEquipo(equipo);
                  Objeto.setEstado(resultSet.getString(16));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los equipos registrados para la liquidación de paleros");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public ArrayList<Persona> consultarPersonasPorEquipoLiquidacion(EquipoLiquidacion equipoLiquidacion) throws SQLException{
        ArrayList<Persona> listadoObjetos = new ArrayList();
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            PreparedStatement query= conexion.prepareStatement("SELECT  [ps_cdgo]--1\n" +
                                                        "      ,[ps_tipo_documento_cdgo]--2\n" +
                                                        "		  ,[tidoc_cdgo]--3\n" +
                                                        "		  ,[tidoc_desc]--4\n" +
                                                        "		  ,[tidoc_estad]--5\n" +
                                                        "      ,[ps_nombre]--6\n" +
                                                        "      ,[ps_apellidos]--7\n" +
                                                        "      ,[ps_telefono]--8\n" +
                                                        "      ,[ps_cargo_nomina_cdgo]--9\n" +
                                                        "		  ,[cn_cdgo]--10\n" +
                                                        "		  ,[cn_desc]--11\n" +
                                                        "		  ,[cn_estad]--12\n" +
                                                        "      ,[ps_tipo_contrato_cdgo]--13\n" +
                                                        "		  ,[tc_cdgo]--14\n" +
                                                        "		  ,[tc_desc]--15\n" +
                                                        "		  ,[tc_estad]--16\n" +
                                                        "      ,[ps_compania_cdgo]--17\n" +
                                                        "		  ,[cp_cdgo]--18\n" +
                                                        "		  ,[cp_desc]--19\n" +
                                                        "		  ,[cp_estad]--20\n" +
                                                        "      ,[ps_equipo_cdgo]--21\n" +
                                                        "			,[eq_cdgo]--22\n" +
                                                        "			,[eq_marca]--23\n" +
                                                        "			,[eq_modelo]--24\n" +
                                                        "			,[eq_desc]--25\n" +
                                                        "      ,CASE WHEN ([ps_estado]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [ps_estado]--26\n" +
                                                        ",[te_cdgo]--27\n" +
                                                                "      ,[te_desc]--28\n" +
                                                        "  FROM ["+DB+"].[dbo].[persona]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[tipo_documento] ON [ps_tipo_documento_cdgo]=[tidoc_cdgo]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[cargo_nomina] ON [cn_cdgo]=[ps_cargo_nomina_cdgo]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[tipo_contrato] ON [ps_tipo_contrato_cdgo]=[tc_cdgo]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[compania] ON [cp_cdgo]=[ps_compania_cdgo]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[equipo] ON [eq_cdgo]=[ps_equipo_cdgo] "
                                                        + " LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo] "
                                                                                + " WHERE [eq_cdgo] LIKE ?;");
                query.setString(1, equipoLiquidacion.getEquipo().getCodigo());
                resultSet= query.executeQuery();               
            while(resultSet.next()){  
                Persona Objeto = new Persona(); 
                Objeto.setCodigo(resultSet.getString(1));
                    TipoDocumento tipoDocumento= new TipoDocumento(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                Objeto.setTipoDocumento(tipoDocumento);
                Objeto.setNombre(resultSet.getString(6));
                Objeto.setApellido(resultSet.getString(7));
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
        return listadoObjetos;
    } 
    public int actualizar(EquipoLiquidacion Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
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
            query= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[equipos_liquidacion] SET [el_estad]=? WHERE [el_cdgo] = ?");
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
                        "           ,'EQUIPO_LIQUIDACION'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre el estado de un equipo para liquidacion con Código: ',?,' Pasando de estado:',?,' a estado : ',?));");
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
