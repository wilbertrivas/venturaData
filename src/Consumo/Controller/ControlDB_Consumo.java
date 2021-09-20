package Consumo.Controller;
 
import ConnectionDB.Conexion_DB_costos_vg;
import Consumo.Model.Consumo;
import Sistema.Controller.ControlDB_Config;
import Sistema.Model.Usuario;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ControlDB_Consumo {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  

    public ControlDB_Consumo(String tipoConexion) {
        control = new Conexion_DB_costos_vg(tipoConexion);
    } 
    public int registrar(Consumo cs, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        String DB=control.getBaseDeDatos();
        int result=0;
        conexion= control.ConectarBaseDatos();
        try{
            PreparedStatement queryGetCantidadExistencia= conexion.prepareStatement("SELECT  [is_cant] FROM ["+DB+"].[dbo].[insumo] WHERE is_cdgo=?;");
            queryGetCantidadExistencia.setInt(1, cs.getInsumo().getCodigo());
            ResultSet resultSet= queryGetCantidadExistencia.executeQuery();
            int cantidadExisitenciaInsumo=0;
            while(resultSet.next()){ 
                cantidadExisitenciaInsumo= resultSet.getInt(1);
            }
            if(cantidadExisitenciaInsumo != 0){// validamos que cargo un valor de existencia
                if(cantidadExisitenciaInsumo >=cs.getCantidad()){// la cantidad solicitada se encuentra en inventario por tal motivo procedemos a realizar el consumo
                    PreparedStatement queryInsert= conexion.prepareStatement("  INSERT INTO ["+DB+"].[dbo].[consumo]" +
                                                                    "                   ([cs_cdgo],[cs_fecha],[cs_cliente_cdgo],[cs_insumo_cdgo],[cs_usuario_cdgo],[cs_cant],[cs_observ],[cs_fecha_registro])" +
                                                                    "           VALUES ((SELECT (CASE WHEN (MAX([cs_cdgo]) IS NULL)\n" +
                                                                                                    " THEN 1\n" +
                                                                                                    " ELSE (MAX([cs_cdgo])+1) END)AS [cs_cdgo]\n" +
                                                                                " FROM ["+DB+"].[dbo].[consumo]),?,?,?,?,?,?,(SELECT SYSDATETIME()))");
                    queryInsert.setString(1, cs.getFecha());
                    queryInsert.setString(2, cs.getCliente().getCodigo());
                    queryInsert.setInt(3, cs.getInsumo().getCodigo());
                    queryInsert.setString(4, cs.getUsuario().getCodigo());
                    queryInsert.setInt(5, cs.getCantidad());
                    queryInsert.setString(6, cs.getObservacion());
                    queryInsert.execute();
                    result=1;
                    if(result == 1){//La inserción a la tabla consumo se dio de forma exitosa, por tal motivo descontamos las cantidades del inventario                      
                        result=0;
                        cantidadExisitenciaInsumo=cantidadExisitenciaInsumo-cs.getCantidad(); 
                        //Procedemos a actualizar el nuevo valor de inventario de los insumos                                 
                        PreparedStatement queryActualizarNuevoInventario= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[insumo] SET [is_cant] = ? WHERE [is_cdgo] = ?;");       
                        queryActualizarNuevoInventario.setInt(1, cantidadExisitenciaInsumo);
                        queryActualizarNuevoInventario.setInt(2, cs.getInsumo().getCodigo());
                        queryActualizarNuevoInventario.execute();                
                        result=1; 
                        if(result==1){
                            result=0;
                            //Sacamos el valor del codigo de la ultima compra
                            PreparedStatement QuerySelectMaximo= conexion.prepareStatement("SELECT MAX(cs_cdgo) FROM ["+DB+"].[dbo].[consumo];");
                            ResultSet resultSetQuerySelectMaximo= QuerySelectMaximo.executeQuery();
                            while(resultSetQuerySelectMaximo.next()){ 
                                if(resultSetQuerySelectMaximo.getString(1) != null){
                                    cs.setCodigo(resultSetQuerySelectMaximo.getInt(1));
                                }
                            }
                            //Procedemos a registrar en la tabla Auditoria    
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
                                        "           ,'CONSUMO'" +
                                        "           ,CONCAT('Se actualizó en el sistema ',?,' Código: ',?,' al Insumo de Nombre:',?,' ',?,' Cantidad: ',?, ' Quedando en Inventario:',?));");
                            Query_Auditoria.setString(1, us.getCodigo());
                            Query_Auditoria.setString(2, namePc);
                            Query_Auditoria.setString(3, ipPc);
                            Query_Auditoria.setString(4, macPC);
                            Query_Auditoria.setInt(5, cs.getCodigo());
                            Query_Auditoria.setString(6, "el consumo:");
                            Query_Auditoria.setInt(7, cs.getCodigo());
                            Query_Auditoria.setString(8, cs.getInsumo().getDescripcion());
                            Query_Auditoria.setString(9, cs.getInsumo().getUnidad().getDescripcion());
                            Query_Auditoria.setInt(10, cs.getCantidad());
                            Query_Auditoria.setInt(11, cantidadExisitenciaInsumo);
                            Query_Auditoria.execute();
                            result=1;
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "La cantidad del insumo que solicito no se encuentra en inventario, valide existencia");
                }
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar la existencia el insumo");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return result;       
    } 
}
