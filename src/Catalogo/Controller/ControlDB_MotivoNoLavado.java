package Catalogo.Controller;
  
import ConnectionDB.Conexion_DB_costos_vg;
import Catalogo.Model.MotivoNoLavado;
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

public class ControlDB_MotivoNoLavado {
    private Connection conexion=null;
    private Conexion_DB_costos_vg control=null;  
    private String tipoConexion;   
    
    public ControlDB_MotivoNoLavado(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    public int registrar(MotivoNoLavado objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            String estadoCo="";
            if(objeto.getEstado().equalsIgnoreCase("1")){
                estadoCo="ACTIVO";
            }else{
                estadoCo="INACTIVO";
            }  
            PreparedStatement queryRegistrar= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[motivo_nolavado_vehiculo] ([mnlv_cdgo],[mnlv_desc],[mnlv_estad]) VALUES ((SELECT (CASE WHEN (MAX([mnlv_cdgo]) IS NULL)\n" +
                                                                                                    " THEN 1\n" +
                                                                                                    " ELSE (MAX([mnlv_cdgo])+1) END)AS [mnlv_cdgo]\n" +
                                                                                " FROM ["+DB+"].[dbo].[motivo_nolavado_vehiculo]),?,?);");
            queryRegistrar.setString(1, objeto.getDescripcion());
            queryRegistrar.setString(2, objeto.getEstado());
            queryRegistrar.execute();
            result=1;
            if(result==1){
                result=0;
                //Sacamos el ultimo valor 
                PreparedStatement queryMaximo= conexion.prepareStatement("SELECT MAX(mnlv_cdgo) FROM ["+DB+"].[dbo].[motivo_nolavado_vehiculo];");
                ResultSet resultSetMaximo= queryMaximo.executeQuery();
                while(resultSetMaximo.next()){ 
                    if(resultSetMaximo.getString(1) != null){
                        objeto.setCodigo(resultSetMaximo.getInt(1));
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
                        "           ,'MTOVITONOLAVADO_REGISTRAR'" +
                        "           ,CONCAT (?,?,' Nombre: ',?,' Estado: ',?));");
                Query_AuditoriaInsert.setString(1, us.getCodigo());
                Query_AuditoriaInsert.setString(2, namePc);
                Query_AuditoriaInsert.setString(3, ipPc);
                Query_AuditoriaInsert.setString(4, macPC);
                Query_AuditoriaInsert.setString(5, ""+objeto.getCodigo());
                Query_AuditoriaInsert.setString(6, "Se registró un nuevo motivo de no lavado de vehículo, con Código: ");
                Query_AuditoriaInsert.setInt(7, objeto.getCodigo());
                Query_AuditoriaInsert.setString(8, objeto.getDescripcion());
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
    public ArrayList<MotivoNoLavado> buscar(String valorConsulta) throws SQLException{
        ArrayList<MotivoNoLavado> listadoMotivoNoLavado = new ArrayList();
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            if(valorConsulta.equals("")){
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT mnlv_cdgo, mnlv_desc, CASE WHEN (mnlv_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS mnlv_estad FROM ["+DB+"].[dbo].[motivo_nolavado_vehiculo];");
                resultSetBuscar=queryBuscar.executeQuery();
            }else{
               PreparedStatement queryBuscar= conexion.prepareStatement("SELECT mnlv_cdgo, mnlv_desc, CASE WHEN (mnlv_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS mnlv_estad FROM ["+DB+"].[dbo].[motivo_nolavado_vehiculo] WHERE [mnlv_desc] like ?;");
               queryBuscar.setString(1, "%"+valorConsulta+"%");
               resultSetBuscar=queryBuscar.executeQuery();
            }
            while(resultSetBuscar.next()){ 
                MotivoNoLavado objeto = new MotivoNoLavado(); 
                objeto.setCodigo(resultSetBuscar.getInt(1));
                objeto.setDescripcion(resultSetBuscar.getString(2));
                objeto.setEstado(resultSetBuscar.getString(3));
                listadoMotivoNoLavado.add(objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los motivo de no lavado de vehículos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoMotivoNoLavado;
    } 
    public ArrayList<MotivoNoLavado> buscarActivos() throws SQLException{
        ArrayList<MotivoNoLavado> listadoMotivoNoLavado = null;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT mnlv_cdgo, mnlv_desc, CASE WHEN (mnlv_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS mnlv_estad FROM ["+DB+"].[dbo].[motivo_nolavado_vehiculo] WHERE mnlv_estad=1;");
            resultSetBuscar=queryBuscar.executeQuery();
             boolean validator=true;
            while(resultSetBuscar.next()){ 
                if(validator){
                    listadoMotivoNoLavado = new ArrayList();
                    validator= false;
                }
                //listadoMotivoNoLavado = new ArrayList();
                MotivoNoLavado objeto = new MotivoNoLavado(); 
                objeto.setCodigo(resultSetBuscar.getInt(1));
                objeto.setDescripcion(resultSetBuscar.getString(2));
                objeto.setEstado(resultSetBuscar.getString(3));
                listadoMotivoNoLavado.add(objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los motivos de no lavado de vehículos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoMotivoNoLavado;
    } 
    public MotivoNoLavado buscarMotivoNoLavadoEspecifico(String codigoMotivoNoLavado) throws SQLException{
        MotivoNoLavado objeto =null; 
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT mnlv_cdgo, mnlv_desc, CASE WHEN (mnlv_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS mnlv_estad FROM ["+DB+"].[dbo].[motivo_nolavado_vehiculo] WHERE [mnlv_cdgo] like ?;"); 
            queryBuscar.setString(1, codigoMotivoNoLavado);
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                objeto = new MotivoNoLavado(); 
                objeto.setCodigo(resultSetBuscar.getInt(1));
                objeto.setDescripcion(resultSetBuscar.getString(2));
                objeto.setEstado(resultSetBuscar.getString(3));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los motivos de no lavado de vehículos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return objeto;
    } 
    public ArrayList<MotivoNoLavado> buscarMotivoNoLavadoActivos() throws SQLException{
        ArrayList<MotivoNoLavado> listadoMotivoNoLavado = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT mnlv_cdgo, mnlv_desc, CASE WHEN (mnlv_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS mnlv_estad FROM ["+DB+"].[dbo].[motivo_nolavado_vehiculo] WHERE [mnlv_estad]=1;");
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                MotivoNoLavado objeto = new MotivoNoLavado(); 
                objeto.setCodigo(resultSetBuscar.getInt(1));
                objeto.setDescripcion(resultSetBuscar.getString(2));
                objeto.setEstado(resultSetBuscar.getString(3));
                listadoMotivoNoLavado.add(objeto); 
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los motivos de no lavado de vehículos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoMotivoNoLavado;
    } 
    public String buscar_nombre(String nombre) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[motivo_nolavado_vehiculo] WHERE [mnlv_desc] like ?;");
            queryBuscar.setString(1, nombre);
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                return resultSetBuscar.getString(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los motivos de no lavado de vehículoss");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } 
    public String buscar_Id(String id) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[motivo_nolavado_vehiculo] WHERE [mnlv_cdgo] =?;");
            queryBuscar.setString(1, id);
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                return resultSetBuscar.getString(2);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los motivos de no lavado de vehículos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } 
    public boolean validarExistencia(MotivoNoLavado ccs){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[motivo_nolavado_vehiculo] WHERE [mnlv_desc] like ?;");
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
    public boolean validarExistenciaActualizar(MotivoNoLavado objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[motivo_nolavado_vehiculo] WHERE [mnlv_desc] like ? AND [mnlv_cdgo]<> ?;");
            queryBuscar.setString(1, objeto.getDescripcion());
            queryBuscar.setInt(2, objeto.getCodigo());
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
    public int actualizar(MotivoNoLavado objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            MotivoNoLavado MotivoNoLavadoAnterior=buscarMotivoNoLavadoEspecifico(""+objeto.getCodigo());
            String valorEstado="";
            if(objeto.getEstado().equalsIgnoreCase("1")){
                valorEstado="ACTIVO";
            }else{
                valorEstado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryBuscar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[motivo_nolavado_vehiculo] set [mnlv_desc]=?, [mnlv_estad]=? WHERE [mnlv_cdgo]=?;");
            queryBuscar.setString(1,objeto.getDescripcion());
            queryBuscar.setString(2,objeto.getEstado());
            queryBuscar.setInt(3,objeto.getCodigo());
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
                        "           ,'MOTIVONOLAVADOVEHICULO_ACTUALIZAR'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre el motivo de no lavado de vehículo, Código:',?,' Nombre: ',?,' Estado: ',?,' actualizando la siguiente informacion a Código:',?,' Nombre: ',?,' Estado: ',?));"); 
                    Query_Auditoria.setString(1, us.getCodigo());
                    Query_Auditoria.setString(2, namePc);
                    Query_Auditoria.setString(3, ipPc);
                    Query_Auditoria.setString(4, macPC);
                    Query_Auditoria.setString(5, ""+MotivoNoLavadoAnterior.getCodigo());
                    Query_Auditoria.setInt(6, MotivoNoLavadoAnterior.getCodigo());
                    Query_Auditoria.setString(7, MotivoNoLavadoAnterior.getDescripcion());
                    Query_Auditoria.setString(8, MotivoNoLavadoAnterior.getEstado());
                    Query_Auditoria.setInt(9, objeto.getCodigo());
                    Query_Auditoria.setString(10, objeto.getDescripcion());
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
