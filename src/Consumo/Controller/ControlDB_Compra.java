package Consumo.Controller2;
  
import Consumo.Model2.Compra;
import Consumo.Model2.Insumo;
import ConnectionDB2.Conexion_DB_costos_vg;
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
 
public class ControlDB_Compra {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private String tipoConexion;
    public ControlDB_Compra(String tipoConexion) {
        control = new Conexion_DB_costos_vg(tipoConexion);
        this.tipoConexion= tipoConexion;
    }
    public int registrar(Compra cm, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        String DB=control.getBaseDeDatos();
        int result=0;
        try{
            conexion= control.ConectarBaseDatos();
            PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[compra] \n" +
                "           ([cm_cdgo],[cm_insumo_cdgo] \n" +
                "           ,[cm_fecha] \n" +
                "           ,[cm_cant] \n" +
                "           ,[cm_observ] \n" +
                "           ,[cm_usuario_cdgo],[cm_fecha_registro]) \n" +
                "     VALUES \n" +
                "           ((SELECT (CASE WHEN (MAX([cm_cdgo]) IS NULL)\n" +
                                " THEN 1\n" +
                                " ELSE (MAX([cm_cdgo])+1) END)AS [cm_cdgo]\n" +
            " FROM ["+DB+"].[dbo].[compra]),?,?,?,?,?,(SELECT SYSDATETIME()));");
            Query.setInt(1, cm.getInsumo().getCodigo());
            Query.setString(2, cm.getFechaCompra());
            Query.setInt(3, cm.getCantidad());
            Query.setString(4, cm.getObservacion());
            Query.setString(5, cm.getUsuario().getCodigo());
            Query.execute();
            result=1;
            if(result==1){
                result=0;
                //Si el insert se da de manera correcta procedemos a sumarle la cantidad de la compra a la existencia del producto
                ControlDB_Insumo controlDB_Insumo = new ControlDB_Insumo(tipoConexion);
                Insumo in= null;
                in=controlDB_Insumo.buscarInsumoEspecifico(cm.getInsumo().getCodigo());
                if(in != null){
                    int insumoNuevaCantidad=in.getCantidad()+cm.getCantidad();
                    try{
                        PreparedStatement Query_Update= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[insumo] SET [is_cant] = ? WHERE [is_cdgo] = ?;");
                        Query_Update.setInt(1, insumoNuevaCantidad);
                        Query_Update.setInt(2, cm.getInsumo().getCodigo());
                        Query_Update.execute();
                        result=1; 
                        if(result==1){
                            result=0;
                            //Sacamos el valor del codigo de la ultima compra
                            PreparedStatement Query_Maximo= conexion.prepareStatement("SELECT MAX(cm_cdgo) FROM ["+DB+"].[dbo].[compra];");
                            ResultSet resultSet= Query_Maximo.executeQuery();
                            while(resultSet.next()){ 
                                if(resultSet.getString(1) != null){
                                    cm.setCodigo(resultSet.getInt(1));
                                }
                            }
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
                        "           ,'COMPRA'" +
                        "           ,CONCAT (?,?,' Nombre: ',?,' Cantidad: ',?,' Quedando en Inventario:',?));");
                            Query_Auditoria.setString(1, us.getCodigo());
                            Query_Auditoria.setString(2, namePc);
                            Query_Auditoria.setString(3, ipPc);
                            Query_Auditoria.setString(4, macPC);
                            Query_Auditoria.setInt(5, cm.getCodigo());
                            Query_Auditoria.setString(6, "Se registró una nueva compra en el sistema con Código: ");
                            Query_Auditoria.setInt(7, cm.getCodigo());
                            Query_Auditoria.setString(8, in.getDescripcion()+" "+in.getUnidad().getDescripcion());
                            Query_Auditoria.setInt(9, cm.getCantidad());
                            Query_Auditoria.setInt(10, insumoNuevaCantidad);
                            Query_Auditoria.execute();
                            result=1;
                        }
                    }
                    catch (SQLException sqlException ){   
                        result=0;
                        JOptionPane.showMessageDialog(null, "ERROR al querer actualizar la nueva cantidad del insumo");
                        sqlException.printStackTrace();
                    }  
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
    public ArrayList<String> consultarCompras(String valorConsulta) throws SQLException{
        String DB=control.getBaseDeDatos();
        ArrayList<String> historicoRecobro = new ArrayList();
        conexion= control.ConectarBaseDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT TOP 1000 " +
                                "		cm_cdgo		AS CODIGO_COMPRA," +
                                "		cm_fecha	AS FECHA_COMPRA," +
                                "		is_desc		AS NOMBRE_PRODUCTO," +
                                "		un.un_desc	AS UNIDAD_MEDIDA,	" +
                                "		cm_cant		AS CANTIDAD_COMPRADA," +
                                "		is_cant		AS EXISTENCIA," +
                                "		us_cdgo		AS CEDULA_QUIEN_REALIZA_COMPRA," +
                                "		CONCAT(us_nombres,' ',us_apellidos) AS NOMBRE_QUIEN_REALIZA_COMPRA," +
                                "		cm_observ	AS OBSERVACION_COMPRA" +
                                "		" +
                                "FROM ["+DB+"].[dbo].[compra] cm" +
                                "	INNER JOIN ["+DB+"].[dbo].usuario us ON cm.cm_usuario_cdgo = us.us_cdgo" +
                                "	INNER JOIN ["+DB+"].[dbo].insumo im ON cm.cm_insumo_cdgo = im.is_cdgo" +
                                "	INNER JOIN ["+DB+"].[dbo].unidad un ON un.un_cdgo= im.is_unidad_cdgo" +
                                "	WHERE cm_cdgo LIKE ?  " +
                                "ORDER BY cm_fecha DESC");
            query.setString(1, "%"+valorConsulta+"%");
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                String codigoCompra=resultSet.getString(1);
                String fechaCompra=resultSet.getString(2);
                String nombreProducto=resultSet.getString(3);
                String unidadMedida=resultSet.getString(4);
                String cantidadComprada=resultSet.getString(5);
                String existencia=resultSet.getString(6);
                String cedulaQuienRealizaCompra=resultSet.getString(7);
                String nombreQuienRealizaCompra=resultSet.getString(8);
                String observacionCompra=resultSet.getString(9);            
                historicoRecobro.add(codigoCompra+"@#@"+fechaCompra+"@#@"+nombreProducto+"@#@"+unidadMedida
                                    +"@#@"+cantidadComprada+"@#@"+existencia+"@#@"+cedulaQuienRealizaCompra
                                    +"@#@"+nombreQuienRealizaCompra+"@#@"+observacionCompra);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las compras");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return historicoRecobro;
    } 

}
