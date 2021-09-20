package Catalogo.Controller;
  
import ConnectionDB.Conexion_DB_costos_vg;
import Catalogo.Model.ClasificadorEquipo;
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

public class ControlDB_ClasificadorEquipo {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;    
    private String tipoConexion;
    
    public ControlDB_ClasificadorEquipo(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    public int registrar(ClasificadorEquipo Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
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
                PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[clasificador_equipo] ([ce_cdgo],[ce_desc],[ce_estad]) VALUES (?,?,?);");
                Query.setString(1, Objeto.getCodigo());
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
                        "           ,?"+
                        "           ,'CLASIFICADOR_EQUIPO'" +
                        "           ,CONCAT (?,?,' Nombre: ',?,' Estado: ',?));");
                    Query_Auditoria.setString(1, us.getCodigo());
                    Query_Auditoria.setString(2, namePc);
                    Query_Auditoria.setString(3, ipPc);
                    Query_Auditoria.setString(4, macPC);
                    Query_Auditoria.setString(5, Objeto.getCodigo());  
                    Query_Auditoria.setString(6, "Se registró una nueva clasificación de Equipo en el sistema, con Código: ");
                    Query_Auditoria.setString(7, Objeto.getCodigo());
                    Query_Auditoria.setString(8, Objeto.getDescripcion());
                    Query_Auditoria.setString(9, estado);
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
    public ArrayList<ClasificadorEquipo> buscar(String valorConsulta) throws SQLException{
        ArrayList<ClasificadorEquipo> listadoObjetos = new ArrayList();
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            if(valorConsulta.equals("")){
                PreparedStatement query= conexion.prepareStatement("SELECT ce_cdgo, ce_desc, CASE WHEN (ce_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS ce_estad FROM ["+DB+"].[dbo].[clasificador_equipo];");
                resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexion.prepareStatement("SELECT ce_cdgo, ce_desc, CASE WHEN (ce_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS ce_estad FROM ["+DB+"].[dbo].[clasificador_equipo] WHERE ([ce_cdgo] LIKE ? OR [ce_desc] like ?);");
                query.setString(1, "%"+valorConsulta+"%");
                query.setString(2, "%"+valorConsulta+"%");
                resultSet= query.executeQuery();              
            }
            while(resultSet.next()){  
                ClasificadorEquipo Objeto = new ClasificadorEquipo(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los clasificadores de Equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public ClasificadorEquipo buscarEspecifico(String codigo) throws SQLException{
        ClasificadorEquipo Objeto =null; 
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT ce_cdgo, ce_desc, CASE WHEN (ce_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS ce_estad FROM ["+DB+"].[dbo].[clasificador_equipo] WHERE [ce_cdgo] = ?;"); 
            query.setString(1, codigo);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                Objeto = new ClasificadorEquipo(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los clasificadores de equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return Objeto;
    } 
    public ArrayList<ClasificadorEquipo> buscarActivos() throws SQLException{
        ArrayList<ClasificadorEquipo> listadoObjetos = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT ce_cdgo, ce_desc, CASE WHEN (ce_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS ce_estad FROM ["+DB+"].[dbo].[clasificador_equipo] WHERE [ce_estad]=1;");
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                ClasificadorEquipo Objeto = new ClasificadorEquipo(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                listadoObjetos.add(Objeto); 
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los clasificadores de equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public String buscar_nombre(String nombre) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[clasificador_equipo] WHERE [ce_desc] like ?;");
            query.setString(1, nombre);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                return resultSet.getString(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los clasificadores de Equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } 
    public String buscar_Id(String id) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[clasificador_equipo] WHERE [ce_cdgo] =?;");
            query.setString(1, id);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                return resultSet.getString(2);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los clasificadores de Equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } 
    public boolean validarExistencia(ClasificadorEquipo Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[clasificador_equipo] WHERE [ce_cdgo] like ?;");
            query.setString(1, Objeto.getCodigo());
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                retorno =true;               
            }
        }catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null, "Error al Tratar de buscar los Clasificadores de Equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return retorno;
    }  
    public boolean validarPorCodigo(ClasificadorEquipo Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[clasificador_equipo] WHERE [ce_cdgo] like ?;");
            query.setString(1, Objeto.getCodigo());
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                retorno =true;               
            }
        }catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null, "Error al Tratar de buscar los Clasificadores de Equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return retorno;
    }  
    public boolean validarExistenciaActualizar(ClasificadorEquipo Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[clasificador_equipo] WHERE [ce_desc] like ? AND [ce_cdgo]<> ?;");
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
    public int actualizar(ClasificadorEquipo Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            ClasificadorEquipo ClasificadorEquipoAnterior=buscarEspecifico(""+Objeto.getCodigo());
            String estado="";
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                estado="ACTIVO";
            }else{
                estado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement query= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[clasificador_equipo] set [ce_desc]=?, [ce_estad]=? WHERE [ce_cdgo]=?");
            query.setString(1, Objeto.getDescripcion());
            query.setString(2, Objeto.getEstado());
            query.setString(3, Objeto.getCodigo());
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
                        "           ,'CLASIFICADOR_EQUIPO'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre ',?,' Código: ',?,' Nombre: ',?,' Estado: ',?,"
                                            + "' actualizando la siguiente informacion a Código: ',?,' Nombre: ',?,' Estado: ',?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, ClasificadorEquipoAnterior.getCodigo()); 
                Query_Auditoria.setString(6, "el clasificador de Equipo :");
                Query_Auditoria.setString(7, ClasificadorEquipoAnterior.getCodigo());
                Query_Auditoria.setString(8, ClasificadorEquipoAnterior.getDescripcion());
                Query_Auditoria.setString(9, ClasificadorEquipoAnterior.getEstado());
                Query_Auditoria.setString(10, Objeto.getCodigo());
                Query_Auditoria.setString(11, Objeto.getDescripcion());
                Query_Auditoria.setString(12, estado);
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
