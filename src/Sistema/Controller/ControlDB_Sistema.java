package Sistema.Controller;

import ConnectionDB.Conexion_DB_costos_vg;
import Sistema.Model.Usuario;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ControlDB_Sistema {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  

    public ControlDB_Sistema(String tipoConexion) {
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    public boolean ingresoSistema(Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        boolean result= false;
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
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
                        "           ,(SELECT (CASE WHEN (MAX([au_cdgo]) IS NULL) THEN 1 ELSE (MAX([au_cdgo])+1) END)AS [au_cdgo] FROM ["+DB+"].[dbo].[auditoria])"+
                        "           ,'INGRESO'" +
                        "           ,CONCAT('El siguiente usuario ingresó al sistema, Código: ',?,' Nombre: ',?,' ',?,' Correo: ',?));");
            
            Query_Auditoria.setString(1, us.getCodigo());
            Query_Auditoria.setString(2, namePc);
            Query_Auditoria.setString(3, ipPc);
            Query_Auditoria.setString(4, macPC);
            Query_Auditoria.setString(5, us.getCodigo());
            Query_Auditoria.setString(6, us.getNombres());
            Query_Auditoria.setString(7, us.getApellidos());
            Query_Auditoria.setString(8, us.getCorreo());
            Query_Auditoria.execute();
            result=true;      
        }
        catch (SQLException sqlException ){   
            result=false;
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    } 
    public String contadorIngresoDiario(Usuario us) throws SQLException{
        String contadorIngreso="0";
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        String fecheServer=retornarFechaDelServidor();
        
        String[] fechaS= fecheServer.split("-");
        fecheServer= fechaS[0]+"-"+fechaS[1]+"-"+fechaS[2];
        try{
            conexion= control.ConectarBaseDatos();
            PreparedStatement QueryContarIngresos= conexion.prepareStatement("SELECT COUNT(au_usuario_cdgo_registro) FROM ["+DB+"].[dbo].[auditoria] "
                                                + "     WHERE [au_usuario_cdgo_registro]=? AND [au_desc_mtvo] LIKE 'INGRESO' AND au_fecha BETWEEN  (SELECT CONCAT('"+fecheServer+"',' 00:00:00')) "
                                                        + "AND (SELECT CONCAT('"+fecheServer+"',' 23:59:00'))");
            QueryContarIngresos.setString(1, us.getCodigo());
            ResultSet ResultSetContarIngresos=QueryContarIngresos.executeQuery(); 
            while(ResultSetContarIngresos.next()){ 
                contadorIngreso=ResultSetContarIngresos.getString(1); 
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar en número de ingreso diario");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return contadorIngreso;
    }
    public String retornarFechaDelServidor() throws SQLException{
        String fechaServidor="";
        conexion= control.ConectarBaseDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT CONVERT (date, GETDATE());"); 
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                fechaServidor=resultSet.getString(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar la fecha del Servidor");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return fechaServidor;
    } 
   
}
