package Catalogo.Controller;
  
import ConnectionDB.Conexion_DB_costos_vg;
import Catalogo.Model.ProveedorEquipo;
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
 
public class ControlDB_ProveedorEquipo {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private String tipoConexion;
    public ControlDB_ProveedorEquipo(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    public int registrar(ProveedorEquipo Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
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
            PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[proveedor_equipo] ([pe_cdgo],[pe_nit],[pe_desc],[pe_estad]) VALUES ((SELECT (CASE WHEN (MAX([pe_cdgo]) IS NULL) THEN 1 ELSE (MAX([pe_cdgo])+1) END)AS [pe_cdgo] FROM ["+DB+"].[dbo].[proveedor_equipo]),?,?,?);");
            //Query.setString(1, Objeto.getCodigo());
            Query.setString(1, Objeto.getNit());
            Query.setString(2, Objeto.getDescripcion());
            Query.setString(3, Objeto.getEstado());
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
                        "           ,(SELECT MAX([pe_cdgo]) FROM ["+DB+"].[dbo].[proveedor_equipo])"+
                        "           ,'PROVEEDOR_EQUIPO'" +
                        "           ,CONCAT (?,(SELECT MAX([pe_cdgo]) FROM ["+DB+"].[dbo].[proveedor_equipo]),' Nit: ',?,' Nombre: ',?,' Estado: ',?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, "Se registr?? un nuevo proveedor de equipo en el sistema, con C??digo: ");
                Query_Auditoria.setString(6, Objeto.getNit());
                Query_Auditoria.setString(7, Objeto.getDescripcion());
                Query_Auditoria.setString(8, estado);
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
    public ArrayList<ProveedorEquipo> buscar(String valorConsulta) throws SQLException{
        ArrayList<ProveedorEquipo> listadoObjetos = new ArrayList();
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            if(valorConsulta.equals("")){
                PreparedStatement query= conexion.prepareStatement("SELECT pe_cdgo,pe_nit, pe_desc, CASE WHEN (pe_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS pe_estad FROM ["+DB+"].[dbo].[proveedor_equipo];");
                resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexion.prepareStatement("SELECT pe_cdgo,pe_nit, pe_desc, CASE WHEN (pe_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS pe_estad FROM ["+DB+"].[dbo].[proveedor_equipo] WHERE [pe_desc] like ?;");
                query.setString(1, "%"+valorConsulta+"%");
                resultSet= query.executeQuery();              
            }
            while(resultSet.next()){  
                ProveedorEquipo Objeto = new ProveedorEquipo(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setNit(resultSet.getString(2));
                Objeto.setDescripcion(resultSet.getString(3));
                Objeto.setEstado(resultSet.getString(4));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los proveedores de equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public ProveedorEquipo buscarEspecifico(String codigo) throws SQLException{
        ProveedorEquipo Objeto =null; 
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT pe_cdgo,pe_nit, pe_desc, CASE WHEN (pe_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS pe_estad FROM ["+DB+"].[dbo].[proveedor_equipo] WHERE [pe_cdgo] = ?;"); 
            query.setString(1, codigo);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                Objeto = new ProveedorEquipo(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setNit(resultSet.getString(2));
                Objeto.setDescripcion(resultSet.getString(3));
                Objeto.setEstado(resultSet.getString(4));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los proveedores de equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return Objeto;
    } 
    public ArrayList<ProveedorEquipo> buscarActivos() throws SQLException{
        ArrayList<ProveedorEquipo> listadoObjetos = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT pe_cdgo, pe_nit, pe_desc, CASE WHEN (pe_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS pe_estad FROM ["+DB+"].[dbo].[proveedor_equipo] WHERE [pe_estad]=1;");
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                ProveedorEquipo Objeto = new ProveedorEquipo(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setNit(resultSet.getString(2));
                Objeto.setDescripcion(resultSet.getString(3));
                Objeto.setEstado(resultSet.getString(4));
                listadoObjetos.add(Objeto); 
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los proveedores de equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public String buscar_nombre(String nombre) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[proveedor_equipo] WHERE [pe_desc] like ?;");
            query.setString(1, nombre);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                return resultSet.getString(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los proveedores de equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } 
    public String buscar_Id(String id) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[proveedor_equipo] WHERE [pe_cdgo] =?;");
            query.setString(1, id);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                return resultSet.getString(2);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los proveedores de equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } 
    public boolean validarExistencia(ProveedorEquipo Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[proveedor_equipo] WHERE [pe_cdgo] like ?;");
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
    public boolean validarPorCodigo(ProveedorEquipo Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[proveedor_equipo] WHERE [pe_cdgo] like ?;");
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
    public boolean validarExistenciaActualizar(ProveedorEquipo Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[proveedor_equipo] WHERE [pe_nit] like ? AND [pe_cdgo]<> ?;");
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
    public int actualizar(ProveedorEquipo Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            ProveedorEquipo ProveedorEquipoAnterior=buscarEspecifico(""+Objeto.getCodigo());
            String estado="";
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                estado="ACTIVO";
            }else{
                estado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement query= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[proveedor_equipo] set [pe_nit]=?, [pe_desc]=?, [pe_estad]=? WHERE [pe_cdgo]=?");
            query.setString(1, Objeto.getNit());
            query.setString(2, Objeto.getDescripcion());
            query.setString(3, Objeto.getEstado());
            query.setString(4, Objeto.getCodigo());
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
                        "           ,'PROVEEDOR_EQUIPO'" +
                        "           ,CONCAT('Se registr?? una nueva actualizaci??n en el sistema sobre ',?,' C??digo: ',?,' Nit: ',?' Nombre: ',?,' Estado: ',?,"
                                            + "' actualizando la siguiente informacion a C??digo: ',?,' Nit: ',?,' Nombre: ',?,' Estado: ',?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, ProveedorEquipoAnterior.getCodigo());   
                Query_Auditoria.setString(6, "el proveedor de equipo:");
                Query_Auditoria.setString(7, ProveedorEquipoAnterior.getCodigo());
                Query_Auditoria.setString(8, ProveedorEquipoAnterior.getNit());
                Query_Auditoria.setString(9, ProveedorEquipoAnterior.getDescripcion());
                Query_Auditoria.setString(10, ProveedorEquipoAnterior.getEstado());
                Query_Auditoria.setString(11, Objeto.getCodigo());
                Query_Auditoria.setString(12, Objeto.getNit());
                Query_Auditoria.setString(13, Objeto.getDescripcion());
                Query_Auditoria.setString(14, estado);
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
