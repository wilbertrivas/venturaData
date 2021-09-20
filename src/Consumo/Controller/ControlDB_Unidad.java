package Consumo.Controller;
 
import ConnectionDB.Conexion_DB_costos_vg;
import Consumo.Model.Unidad;
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

public class ControlDB_Unidad {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  

    public ControlDB_Unidad(String tipoConexion) {
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    public int registrar(Unidad un, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            String estadoU;
            if(un.getEstado().equalsIgnoreCase("1")){
                estadoU="ACTIVO";
            }else{
                estadoU="INACTIVO";
            }
            PreparedStatement queryRegistrar= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[unidad] ([un_cdgo],[un_desc],[un_estad]) VALUES ((SELECT (CASE WHEN (MAX([un_cdgo]) IS NULL)\n" +
                                                                                                    " THEN 1\n" +
                                                                                                    " ELSE (MAX([un_cdgo])+1) END)AS [un_cdgo]\n" +
                                                                                " FROM ["+DB+"].[dbo].[unidad]),?,?);");
            queryRegistrar.setString(1, un.getDescripcion());
            queryRegistrar.setString(2, un.getEstado());
            queryRegistrar.execute();
            result=1;
            if(result==1){
                result=0;
                //Sacamos el ultimo valor
                PreparedStatement queryMaximo= conexion.prepareStatement("SELECT MAX(un_cdgo) FROM ["+DB+"].[dbo].[unidad];");
                ResultSet resultSetMaximo= queryMaximo.executeQuery();
                while(resultSetMaximo.next()){ 
                    if(resultSetMaximo.getString(1) != null){
                        un.setCodigo(resultSetMaximo.getInt(1));
                    }
                }
                //Extraemos el nombre del Equipo y la IP        
                String namePc=new ControlDB_Config().getNamePC();
                String ipPc=new ControlDB_Config().getIpPc();
                String macPC=new ControlDB_Config().getMacAddress();
                PreparedStatement Query_AuditoriaInsert= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[auditoria]([au_cdgo]\n" +
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
                        "           ,'UNIDAD'" +
                        "           ,CONCAT (?,?,' Nombre: ',?,' Estado: ',?));");
                Query_AuditoriaInsert.setString(1, us.getCodigo());
                Query_AuditoriaInsert.setString(2, namePc);
                Query_AuditoriaInsert.setString(3, ipPc);
                Query_AuditoriaInsert.setString(4, macPC);
                Query_AuditoriaInsert.setInt(5, un.getCodigo());
                Query_AuditoriaInsert.setString(6, "Se registró un nueva unidad en el sistema, con Código: ");
                Query_AuditoriaInsert.setInt(7, un.getCodigo());
                Query_AuditoriaInsert.setString(8, un.getDescripcion());
                Query_AuditoriaInsert.setString(9, estadoU);
                Query_AuditoriaInsert.execute();
                result=1; 
            }
        }
        catch (SQLException sqlException ){   
            result=0;
            //JOptionPane.showMessageDialog(null, "ERROR al querer insertar los datos.");
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    } 
    public ArrayList<Unidad> buscar(String valorConsulta) throws SQLException{
        ArrayList<Unidad> listadoUnidad = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            if(valorConsulta.equals("")){
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT un_cdgo, un_desc, CASE WHEN (un_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS un_estad FROM ["+DB+"].[dbo].[unidad];");
                resultSetBuscar=queryBuscar.executeQuery();
            }else{
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT un_cdgo, un_desc, CASE WHEN (un_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS un_estad FROM ["+DB+"].[dbo].[unidad] WHERE [un_desc] like ?;");
                queryBuscar.setString(1, "%"+valorConsulta+"%");
                resultSetBuscar=queryBuscar.executeQuery();
            }
            while(resultSetBuscar.next()){ 
                Unidad un = new Unidad(); 
                un.setCodigo(resultSetBuscar.getInt(1));
                un.setDescripcion(resultSetBuscar.getString(2));
                un.setEstado(resultSetBuscar.getString(3));
                listadoUnidad.add(un);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las unidades");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoUnidad;
    } 
    public Unidad buscarUnidadEspecifica(String codigoUnidad) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        Unidad un = new Unidad();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT un_cdgo, un_desc, CASE WHEN (un_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS un_estad FROM ["+DB+"].[dbo].[unidad] WHERE [un_cdgo] = ?;");
            queryBuscar.setString(1, codigoUnidad);
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                un.setCodigo(resultSetBuscar.getInt(1));
                un.setDescripcion(resultSetBuscar.getString(2));
                un.setEstado(resultSetBuscar.getString(3));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las unidades");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return un;
    }
    public ArrayList<Unidad> buscarUnidadesActivas() throws SQLException{
        ArrayList<Unidad> listadoUnidad = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT un_cdgo, un_desc, CASE WHEN (un_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS un_estad FROM ["+DB+"].[dbo].[unidad] WHERE [un_estad]=1;");
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                Unidad un = new Unidad(); 
                un.setCodigo(resultSetBuscar.getInt(1));
                un.setDescripcion(resultSetBuscar.getString(2));
                un.setEstado(resultSetBuscar.getString(3));
                listadoUnidad.add(un);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las unidades");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoUnidad;
    } 
    public String buscar_nombre(String nombreUnidad) throws SQLException{
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[unidad] WHERE [un_desc] like ?;");
            queryBuscar.setString(1, nombreUnidad);
            ResultSet resultSetBuscar=queryBuscar.executeQuery(); 
            while(resultSetBuscar.next()){ 
                return resultSetBuscar.getString(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las unidades");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } 
    public String buscar_Id(String id) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[unidad] WHERE [un_cdgo] =?;");
            queryBuscar.setString(1, id);
            ResultSet resultSetBuscar=queryBuscar.executeQuery(); 
            while(resultSetBuscar.next()){ 
                return resultSetBuscar.getString(2);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las unidades");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } 
    public boolean validarExistencia(Unidad u){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[unidad] WHERE [un_desc] like ?;");
            queryBuscar.setString(1, u.getDescripcion());
            ResultSet resultSetBuscar=queryBuscar.executeQuery(); 
            while(resultSetBuscar.next()){ 
                retorno =true;               
            }
        }catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null, "Error al Tratar de buscar");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return retorno;
    }  
    public boolean validarExistenciaActualizar(Unidad u){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[unidad] WHERE [un_desc] like ? AND [un_cdgo]<> ?;");
            queryBuscar.setString(1, u.getDescripcion());
            queryBuscar.setInt(2, u.getCodigo());
            ResultSet resultSetBuscar=queryBuscar.executeQuery(); 
            while(resultSetBuscar.next()){ 
                retorno =true;               
            }
        }catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null, "Error al Tratar de buscar");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return retorno;
    }
    public int actualizar(Unidad u, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            Unidad unidadAnterior=buscarUnidadEspecifica(""+u.getCodigo());
            String valorEstado="";
            if(u.getEstado().equalsIgnoreCase("1")){
                valorEstado="ACTIVO";
            }else{
                valorEstado="INACTIVO";
            }    
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[unidad] set [un_desc]=?, [un_estad]=? WHERE [un_cdgo]=?");
            queryActualizar.setString(1,u.getDescripcion());
            queryActualizar.setString(2,u.getEstado());
            queryActualizar.setInt(3,u.getCodigo());
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
                        "           ,'UNIDAD'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre la unidad de Código:',?,' Nombre: ',?,' Estado: ',?,' actualizando la siguiente informacion a Código:',?,' Nombre: ',?,' Estado: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setInt(5, unidadAnterior.getCodigo());
                Query_Auditoria.setInt(4, unidadAnterior.getCodigo());
                Query_Auditoria.setString(5, unidadAnterior.getDescripcion());
                Query_Auditoria.setString(6, unidadAnterior.getEstado());
                Query_Auditoria.setInt(7, u.getCodigo());
                Query_Auditoria.setString(8, u.getDescripcion());
                Query_Auditoria.setString(9, valorEstado);
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
