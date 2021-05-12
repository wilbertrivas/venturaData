package Catalogo.Controller;
  
import ConnectionDB2.Conexion_DB_costos_vg;
import Catalogo.Model.TipoArticulo;
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

public class ControlDB_TipoArticulo {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private String tipoConexion;
    
    public ControlDB_TipoArticulo(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    public int registrar(TipoArticulo Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
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
            if(!validarExistencia(Objeto)){
                conexion= control.ConectarBaseDatos();
                PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[tipo_articulo] ([tar_cdgo],[tar_desc],[tar_cdgo_erp],[tar_undad_ngcio],[tar_estado]) VALUES (?,?,?,?,?);");
                Query.setString(1, Objeto.getCodigo());
                Query.setString(2, Objeto.getDescripcion());
                Query.setString(3, Objeto.getCodigoERP());
                Query.setString(4, Objeto.getUnidadNegocio());
                Query.setString(5, Objeto.getEstado());
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
                        "           ,'TIPO_ARTICULO'" +
                        "           ,CONCAT (?,?,'Nombre: ',?,' Código_Erp: ',?,' Unidad_Negocio: ',?,' Estado: ',?));");
                    Query_Auditoria.setString(1, us.getCodigo());
                    Query_Auditoria.setString(2, namePc);
                    Query_Auditoria.setString(3, ipPc);
                    Query_Auditoria.setString(4, macPC);
                    Query_Auditoria.setString(5, Objeto.getCodigo());
                    Query_Auditoria.setString(6, "Se registró un nuevo tipo_articulo en el sistema, con Código: ");
                    Query_Auditoria.setString(7, Objeto.getCodigo());
                    Query_Auditoria.setString(8, Objeto.getDescripcion());
                    Query_Auditoria.setString(9, Objeto.getCodigoERP());
                    Query_Auditoria.setString(10, Objeto.getUnidadNegocio());
                    Query_Auditoria.setString(11, estado);
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
    public ArrayList<TipoArticulo> buscar(String valorConsulta) throws SQLException{
        ArrayList<TipoArticulo> listadoObjetos = new ArrayList();
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            if(valorConsulta.equals("")){
                PreparedStatement query= conexion.prepareStatement("SELECT [tar_cdgo],[tar_desc],[tar_cdgo_erp],[tar_undad_ngcio], CASE WHEN (tar_estado=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS tar_estado FROM ["+DB+"].[dbo].[tipo_articulo];");
                resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexion.prepareStatement("SELECT [tar_cdgo],[tar_desc],[tar_cdgo_erp],[tar_undad_ngcio], CASE WHEN (tar_estado=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS tar_estado FROM ["+DB+"].[dbo].[tipo_articulo] WHERE ([tar_cdgo] LIKE ? OR [tar_desc] like ?);");
                query.setString(1, "%"+valorConsulta+"%");
                query.setString(2, "%"+valorConsulta+"%");
                resultSet= query.executeQuery();              
            }
            while(resultSet.next()){  
                TipoArticulo Objeto = new TipoArticulo(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setCodigoERP(resultSet.getString(3));
                Objeto.setUnidadNegocio(resultSet.getString(4));
                Objeto.setEstado(resultSet.getString(5));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los tipos de articulos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public TipoArticulo buscarEspecifico(String codigo) throws SQLException{
        TipoArticulo Objeto =null; 
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT [tar_cdgo],[tar_desc],[tar_cdgo_erp],[tar_undad_ngcio], CASE WHEN (tar_estado=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS tar_estado FROM ["+DB+"].[dbo].[tipo_articulo] WHERE [tar_cdgo] = ?;"); 
            query.setString(1, codigo);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                Objeto = new TipoArticulo(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setCodigoERP(resultSet.getString(3));
                Objeto.setUnidadNegocio(resultSet.getString(4));
                Objeto.setEstado(resultSet.getString(5));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar el tipo de articulo");
            sqlException.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return Objeto;
    } 
    public ArrayList<TipoArticulo> buscarActivos() throws SQLException{
        ArrayList<TipoArticulo> listadoObjetos = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT [tar_cdgo],[tar_desc],[tar_cdgo_erp],[tar_undad_ngcio], CASE WHEN (tar_estado=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS tar_estado FROM ["+DB+"].[dbo].[tipo_articulo] WHERE [tar_estado]=1;");
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                TipoArticulo Objeto = new TipoArticulo(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setCodigoERP(resultSet.getString(3));
                Objeto.setUnidadNegocio(resultSet.getString(4));
                Objeto.setEstado(resultSet.getString(5));
                listadoObjetos.add(Objeto); 
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los tipos articulos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public String buscar_nombre(String nombre) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[tipo_articulo] WHERE [tar_desc] like ?;");
            query.setString(1, nombre);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                return resultSet.getString(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar el tipo de articulo");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } 
    public String buscar_Id(String id) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[tipo_articulo] WHERE [tar_cdgo] =?;");
            query.setString(1, id);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                return resultSet.getString(2);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los tipos de articulos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } 
    public boolean validarExistencia(TipoArticulo Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[tipo_articulo] WHERE [tar_cdgo] like ?;");
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
    public boolean validarExistenciaActualizar(TipoArticulo Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[tipo_articulo] WHERE [tar_desc] like ? AND [tar_cdgo]<> ?;");
            query.setString(1, Objeto.getDescripcion());
            query.setString(2, Objeto.getCodigo());
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
    public int actualizar(TipoArticulo Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            TipoArticulo TipoArticuloAnterior=buscarEspecifico(""+Objeto.getCodigo());
            String estado="";
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                estado="ACTIVO";
            }else{
                estado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement query= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[tipo_articulo] SET tar_desc=?, tar_cdgo_erp=?, tar_undad_ngcio=?, [tar_estado]=? WHERE [tar_cdgo]=?");
           
            query.setString(1, Objeto.getDescripcion());
            query.setString(2, Objeto.getCodigoERP());
            query.setString(3, Objeto.getUnidadNegocio());
            query.setString(4, Objeto.getEstado());
            query.setString(5, Objeto.getCodigo());
            query.execute();
            result=1;
            if(result==1){
                if(TipoArticuloAnterior != null){
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
                            "           ,'TIPO_ARTICULO'" +
                            "           ,CONCAT('Se registró una nueva actualización en el sistema sobre ',?,' Código: ',?,' Nombre: ',?,'Código_ERP:',?,'Unidad_Negocio:',?,' Estado: ',?,"
                                                + "' actualizando la siguiente informacion a  Código: ',?,' Nombre: ',?,'Código_ERP:',?,'Unidad_Negocio:',?,' Estado: ',?));");
                    Query_Auditoria.setString(1, us.getCodigo());
                    Query_Auditoria.setString(2, namePc);
                    Query_Auditoria.setString(3, ipPc);
                    Query_Auditoria.setString(4, macPC);
                    Query_Auditoria.setString(5, TipoArticuloAnterior.getCodigo());
                    Query_Auditoria.setString(6, "el tipo_articulo :");
                    Query_Auditoria.setString(7, TipoArticuloAnterior.getCodigo());
                    Query_Auditoria.setString(8, TipoArticuloAnterior.getDescripcion());
                    Query_Auditoria.setString(9, TipoArticuloAnterior.getCodigoERP());
                    Query_Auditoria.setString(10, TipoArticuloAnterior.getUnidadNegocio());
                    Query_Auditoria.setString(11, TipoArticuloAnterior.getEstado());
                    Query_Auditoria.setString(12, Objeto.getCodigo());
                    Query_Auditoria.setString(13, Objeto.getDescripcion());
                    Query_Auditoria.setString(14, Objeto.getCodigoERP());
                    Query_Auditoria.setString(15, Objeto.getUnidadNegocio());
                    Query_Auditoria.setString(16, estado);
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
}
