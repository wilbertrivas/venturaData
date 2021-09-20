package Catalogo.Controller;
  
import ConnectionDB.Conexion_DB_costos_vg;
import Catalogo.Model.CentroOperacion;
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

public class ControlDB_CentroOperacion {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private String tipoConexion;   
    
    public ControlDB_CentroOperacion(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    public int registrar(CentroOperacion co, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            String estadoCo="";
            if(co.getEstado().equalsIgnoreCase("1")){
                estadoCo="ACTIVO";
            }else{
                estadoCo="INACTIVO";
            }  
            PreparedStatement queryRegistrar= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[cntro_oper] ([co_cdgo],[co_desc],[co_estad]) VALUES ((SELECT (CASE WHEN (MAX([co_cdgo]) IS NULL)\n" +
                                                                                                    " THEN 1\n" +
                                                                                                    " ELSE (MAX([co_cdgo])+1) END)AS [co_cdgo]\n" +
                                                                                " FROM ["+DB+"].[dbo].[cntro_oper]),?,?);");
            queryRegistrar.setString(1, co.getDescripcion());
            queryRegistrar.setString(2, co.getEstado());
            queryRegistrar.execute();
            result=1;
            if(result==1){
                result=0;
                //Sacamos el ultimo valor 
                PreparedStatement queryMaximo= conexion.prepareStatement("SELECT MAX(co_cdgo) FROM ["+DB+"].[dbo].[cntro_oper];");
                ResultSet resultSetMaximo= queryMaximo.executeQuery();
                while(resultSetMaximo.next()){ 
                    if(resultSetMaximo.getString(1) != null){
                        co.setCodigo(resultSetMaximo.getInt(1));
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
                        "           ,'CENTRO_OPERACION'" +
                        "           ,CONCAT (?,?,' Nombre: ',?,' Estado: ',?));");
                Query_AuditoriaInsert.setString(1, us.getCodigo());
                Query_AuditoriaInsert.setString(2, namePc);
                Query_AuditoriaInsert.setString(3, ipPc);
                Query_AuditoriaInsert.setString(4, macPC);
                Query_AuditoriaInsert.setString(5, ""+co.getCodigo());
                Query_AuditoriaInsert.setString(6, "Se registró un nuevo centro de Operación, con Código: ");
                Query_AuditoriaInsert.setInt(7, co.getCodigo());
                Query_AuditoriaInsert.setString(8, co.getDescripcion());
                Query_AuditoriaInsert.setString(9, estadoCo);
                Query_AuditoriaInsert.execute();
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
    public ArrayList<CentroOperacion> buscar(String valorConsulta) throws SQLException{
        ArrayList<CentroOperacion> listadoCentroOperacion = new ArrayList();
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            if(valorConsulta.equals("")){
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT co_cdgo, co_desc, CASE WHEN (co_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS co_estad FROM ["+DB+"].[dbo].[cntro_oper];");
                resultSetBuscar=queryBuscar.executeQuery();
            }else{
               PreparedStatement queryBuscar= conexion.prepareStatement("SELECT co_cdgo, co_desc, CASE WHEN (co_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS co_estad FROM ["+DB+"].[dbo].[cntro_oper] WHERE [co_desc] like ?;");
               queryBuscar.setString(1, "%"+valorConsulta+"%");
               resultSetBuscar=queryBuscar.executeQuery();
            }
            while(resultSetBuscar.next()){ 
                CentroOperacion co = new CentroOperacion(); 
                co.setCodigo(resultSetBuscar.getInt(1));
                co.setDescripcion(resultSetBuscar.getString(2));
                co.setEstado(resultSetBuscar.getString(3));
                listadoCentroOperacion.add(co);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los centros de operaciones");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoCentroOperacion;
    } 
    public ArrayList<CentroOperacion> buscarActivos() throws SQLException{
        ArrayList<CentroOperacion> listadoCentroOperacion = null;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT co_cdgo, co_desc, CASE WHEN (co_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS co_estad FROM ["+DB+"].[dbo].[cntro_oper] WHERE co_estad=1;");
            resultSetBuscar=queryBuscar.executeQuery();
             boolean validator=true;
            while(resultSetBuscar.next()){ 
                if(validator){
                    listadoCentroOperacion = new ArrayList();
                    validator= false;
                }
                //listadoCentroOperacion = new ArrayList();
                CentroOperacion co = new CentroOperacion(); 
                co.setCodigo(resultSetBuscar.getInt(1));
                co.setDescripcion(resultSetBuscar.getString(2));
                co.setEstado(resultSetBuscar.getString(3));
                listadoCentroOperacion.add(co);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los centros de operaciones");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoCentroOperacion;
    } 
    public CentroOperacion buscarCentroOperacionEspecifico(String codigoCentroOperacion) throws SQLException{
        CentroOperacion co =null; 
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT co_cdgo, co_desc, CASE WHEN (co_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS co_estad FROM ["+DB+"].[dbo].[cntro_oper] WHERE [co_cdgo] like ?;"); 
            queryBuscar.setString(1, codigoCentroOperacion);
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                co = new CentroOperacion(); 
                co.setCodigo(resultSetBuscar.getInt(1));
                co.setDescripcion(resultSetBuscar.getString(2));
                co.setEstado(resultSetBuscar.getString(3));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los centros de operaciones");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return co;
    } 
    public ArrayList<CentroOperacion> buscarCentroOperacionActivos() throws SQLException{
        ArrayList<CentroOperacion> listadoCentroOperacion = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT co_cdgo, co_desc, CASE WHEN (co_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS co_estad FROM ["+DB+"].[dbo].[cntro_oper] WHERE [co_estad]=1;");
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                CentroOperacion co = new CentroOperacion(); 
                co.setCodigo(resultSetBuscar.getInt(1));
                co.setDescripcion(resultSetBuscar.getString(2));
                co.setEstado(resultSetBuscar.getString(3));
                listadoCentroOperacion.add(co); 
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los centros de operaciones");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoCentroOperacion;
    } 
    public String buscar_nombre(String nombre) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cntro_oper] WHERE [co_desc] like ?;");
            queryBuscar.setString(1, nombre);
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                return resultSetBuscar.getString(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los centros de operaciones");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } 
    public String buscar_Id(String id) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cntro_oper] WHERE [co_cdgo] =?;");
            queryBuscar.setString(1, id);
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                return resultSetBuscar.getString(2);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los centros de operaciones");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } 
    public boolean validarExistencia(CentroOperacion ccs){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cntro_oper] WHERE [co_desc] like ?;");
            queryBuscar.setString(1, ccs.getDescripcion());
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
    public boolean validarExistenciaActualizar(CentroOperacion ccs){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cntro_oper] WHERE [co_desc] like ? AND [co_cdgo]<> ?;");
            queryBuscar.setString(1, ccs.getDescripcion());
            queryBuscar.setInt(2, ccs.getCodigo());
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
    public int actualizar(CentroOperacion co, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            CentroOperacion CentroOperacionAnterior=buscarCentroOperacionEspecifico(""+co.getCodigo());
            String valorEstado="";
            if(co.getEstado().equalsIgnoreCase("1")){
                valorEstado="ACTIVO";
            }else{
                valorEstado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryBuscar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[cntro_oper] set [co_desc]=?, [co_estad]=? WHERE [co_cdgo]=?;");
            queryBuscar.setString(1,co.getDescripcion());
            queryBuscar.setString(2,co.getEstado());
            queryBuscar.setInt(3,co.getCodigo());
            queryBuscar.execute();
            result=1;
            if(result==1){
                    result=0;
                    //Extraemos el nombre del Equipo y la IP        
                    String namePc=new ControlDB_Config().getNamePC();
                    String ipPc=new ControlDB_Config().getIpPc();
                    String macPC=new ControlDB_Config().getMacAddress();
                    
                    PreparedStatement Query_Auditoria= conexion.prepareStatement(""
                        + "INSERT INTO ["+DB+"].[dbo].[auditoria]([au_cdgo]\n" +
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
                        "           ,'CENTRO_OPERACION'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre el centro de operaciones Código:',?,' Nombre: ',?,' Estado: ',?,' actualizando la siguiente informacion a Código:',?,' Nombre: ',?,' Estado: ',?));"); 
                    Query_Auditoria.setString(1, us.getCodigo());
                    Query_Auditoria.setString(2, namePc);
                    Query_Auditoria.setString(3, ipPc);
                    Query_Auditoria.setString(4, macPC);
                    Query_Auditoria.setString(5, ""+CentroOperacionAnterior.getCodigo());
                    Query_Auditoria.setInt(6, CentroOperacionAnterior.getCodigo());
                    Query_Auditoria.setString(7, CentroOperacionAnterior.getDescripcion());
                    Query_Auditoria.setString(8, CentroOperacionAnterior.getEstado());
                    Query_Auditoria.setInt(9, co.getCodigo());
                    Query_Auditoria.setString(10, co.getDescripcion());
                    Query_Auditoria.setString(11, valorEstado);
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
