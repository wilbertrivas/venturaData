package Catalogo.Controller;
  
import Catalogo.Model.CentroCostoEquipo;
import ConnectionDB.Conexion_DB_costos_vg;
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

public class ControlDB_CentroCostoEquipo {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;    
    private String tipoConexion;
    
    public ControlDB_CentroCostoEquipo(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    public int registrar(CentroCostoEquipo Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
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
                PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[cntro_cost_equipo] ([cce_cdgo],[cce_cdgo_intern],[cce_desc],[cce_estad]) VALUES (?,?,?,?);");
                Query.setString(1, Objeto.getCodigo());
                Query.setString(2, Objeto.getCodigoInterno());
                Query.setString(3, Objeto.getDescripcion());
                Query.setString(4, Objeto.getEstado());
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
                        "           ,'CENTROCOSTO_EQUIPO'" +
                        "           ,CONCAT (?,?,' Nombre: ',?,' Estado: ',?));");
                    Query_Auditoria.setString(1, us.getCodigo());
                    Query_Auditoria.setString(2, namePc);
                    Query_Auditoria.setString(3, ipPc);
                    Query_Auditoria.setString(4, macPC);
                    Query_Auditoria.setString(5, Objeto.getCodigo());  
                    Query_Auditoria.setString(6, "Se registró un nuevo centro de costo de equipo en el sistema, con código: ");
                    Query_Auditoria.setString(7, Objeto.getCodigo()+" Código_Interno: "+ Objeto.getCodigo());
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
    public ArrayList<CentroCostoEquipo> buscar(String valorConsulta) throws SQLException{
        ArrayList<CentroCostoEquipo> listadoObjetos = new ArrayList();
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            if(valorConsulta.equals("")){
                PreparedStatement query= conexion.prepareStatement("SELECT cce_cdgo,cce_cdgo_intern, cce_desc, CASE WHEN (cce_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS cce_estad FROM ["+DB+"].[dbo].[cntro_cost_equipo];");
                resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexion.prepareStatement("SELECT cce_cdgo,cce_cdgo_intern, cce_desc, CASE WHEN (cce_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS cce_estad FROM ["+DB+"].[dbo].[cntro_cost_equipo] WHERE ([cce_cdgo] LIKE ? OR [cce_desc] like ?);");
                query.setString(1, "%"+valorConsulta+"%");
                query.setString(2, "%"+valorConsulta+"%");
                resultSet= query.executeQuery();              
            }
            while(resultSet.next()){  
                CentroCostoEquipo Objeto = new CentroCostoEquipo(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setCodigoInterno(resultSet.getString(2));
                Objeto.setDescripcion(resultSet.getString(3));
                Objeto.setEstado(resultSet.getString(4));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los centro de costos de equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public CentroCostoEquipo buscarEspecifico(String codigo) throws SQLException{
        CentroCostoEquipo Objeto =null; 
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT cce_cdgo,cce_cdgo_intern, cce_desc, CASE WHEN (cce_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS cce_estad FROM ["+DB+"].[dbo].[cntro_cost_equipo] WHERE [cce_cdgo] = ?;"); 
            query.setString(1, codigo);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                Objeto = new CentroCostoEquipo(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setCodigoInterno(resultSet.getString(2));
                Objeto.setDescripcion(resultSet.getString(3));
                Objeto.setEstado(resultSet.getString(4));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los centro de costos de equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return Objeto;
    } 
    public ArrayList<CentroCostoEquipo> buscarActivos() throws SQLException{
        ArrayList<CentroCostoEquipo> listadoObjetos = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT cce_cdgo,cce_cdgo_intern, cce_desc, CASE WHEN (cce_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS cce_estad FROM ["+DB+"].[dbo].[cntro_cost_equipo] WHERE [cce_estad]=1;");
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                CentroCostoEquipo Objeto = new CentroCostoEquipo(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setCodigoInterno(resultSet.getString(2));
                Objeto.setDescripcion(resultSet.getString(3));
                Objeto.setEstado(resultSet.getString(4));
                listadoObjetos.add(Objeto); 
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los centro de costos de equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public String buscar_nombre(String nombre) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cntro_cost_equipo] WHERE [cce_desc] like ?;");
            query.setString(1, nombre);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                return resultSet.getString(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los centros de costos de equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } 
    public String buscar_Id(String id) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cntro_cost_equipo] WHERE [cce_cdgo] =?;");
            query.setString(1, id);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                return resultSet.getString(2);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los centro de costos de equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } 
    public boolean validarExistencia(CentroCostoEquipo Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cntro_cost_equipo] WHERE [cce_cdgo] like ?;");
            query.setString(1, Objeto.getCodigo());
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                retorno =true;               
            }
        }catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null, "Error al Tratar de buscar los centro de costos de equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return retorno;
    }  
    public boolean validarPorCodigo(CentroCostoEquipo Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cntro_cost_equipo] WHERE [cce_cdgo] like ?;");
            query.setString(1, Objeto.getCodigo());
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                retorno =true;               
            }
        }catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null, "Error al Tratar de buscar los centros de costos de equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return retorno;
    }  
    /*public boolean validarExistenciaActualizar(CentroCostoEquipo Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cntro_cost_equipo] WHERE [cce_desc] like ? AND [cce_cdgo]<> ?;");
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
    }  */
    public int actualizar(CentroCostoEquipo Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            CentroCostoEquipo CentroCostoEquipoAnterior=buscarEspecifico(""+Objeto.getCodigo());
            String estado="";
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                estado="ACTIVO";
            }else{
                estado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement query= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[cntro_cost_equipo] SET [cce_cdgo_intern]=?, [cce_desc]=?, [cce_estad]=? WHERE [cce_cdgo]=?");
            query.setString(1, Objeto.getCodigoInterno());
            query.setString(2, Objeto.getDescripcion());
            query.setString(3, Objeto.getEstado());
            query.setString(4, Objeto.getCodigo());
            query.execute();
            result=1;
            if(result==1){
                /*result=0;
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
                        "           ,'CENTROCOSTO_EQUIPO'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre ',?,' Código: ',?,'CódigoInterno: ',?,' Nombre: ',?,' Estado: ',?,"
                                            + "' actualizando la siguiente informacion a Código: ',?,'CódigoInterno: ',?,' Nombre: ',?,' Estado: ',?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, CentroCostoEquipoAnterior.getCodigo()); 
                Query_Auditoria.setString(6, "el clasificador de Equipo :");
                Query_Auditoria.setString(7, CentroCostoEquipoAnterior.getCodigo());
                Query_Auditoria.setString(8, CentroCostoEquipoAnterior.getCodigoInterno());
                Query_Auditoria.setString(9, CentroCostoEquipoAnterior.getDescripcion());
                Query_Auditoria.setString(10, CentroCostoEquipoAnterior.getEstado());
                Query_Auditoria.setString(11, Objeto.getCodigo());
                Query_Auditoria.setString(12, Objeto.getCodigoInterno());
                Query_Auditoria.setString(13, Objeto.getDescripcion());
                Query_Auditoria.setString(14, estado);
                Query_Auditoria.execute();
                result=1;*/
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
