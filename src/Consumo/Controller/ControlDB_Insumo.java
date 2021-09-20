package Consumo.Controller;
 
import ConnectionDB.Conexion_DB_costos_vg;
import Consumo.Model.Insumo;
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

public class ControlDB_Insumo {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  

    public ControlDB_Insumo(String tipoConexion) {
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos(); 
    }   
    public int registrar(Insumo in, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        String DB=control.getBaseDeDatos();
        int result=0;
        try{
            Connection conexion= control.ConectarBaseDatos();
            PreparedStatement queryRegistrar= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[insumo] ([is_cdgo],[is_desc],[is_unidad_cdgo],[is_cant],[is_estad] ) VALUES ((SELECT (CASE WHEN (MAX([is_cdgo]) IS NULL)\n" +
                                                                                                    " THEN 1\n" +
                                                                                                    " ELSE (MAX([is_cdgo])+1) END)AS [is_cdgo]\n" +
                                                                                " FROM ["+DB+"].[dbo].[insumo]),?,?,?,?);");
            queryRegistrar.setString(1, in.getDescripcion());
            queryRegistrar.setInt(2, in.getUnidad().getCodigo());
            queryRegistrar.setInt(3, 0);
            queryRegistrar.setString(4, in.getEstado());
            queryRegistrar.execute();
            result=1;
            if(result==1){
                result=0;
                //Sacamos el valor del codigo de la ultima compra
                PreparedStatement queryMaximo= conexion.prepareStatement("SELECT MAX(is_cdgo) FROM ["+DB+"].[dbo].[insumo];");
                ResultSet resultSetMaximo= queryMaximo.executeQuery();
                while(resultSetMaximo.next()){ 
                    if(resultSetMaximo.getString(1) != null){
                        in.setCodigo(resultSetMaximo.getInt(1));
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
                                        "           ,'INSUMO'" +
                                        "           ,CONCAT (?,?,' Nombre: ',?,' Estado: ',?));");
                Query_AuditoriaInsert.setString(1, us.getCodigo());
                Query_AuditoriaInsert.setString(2, namePc);
                Query_AuditoriaInsert.setString(3, ipPc);
                Query_AuditoriaInsert.setString(4, macPC);
                Query_AuditoriaInsert.setInt(5, in.getCodigo());
                Query_AuditoriaInsert.setString(6, "Se registró un nuevo insumo en el sistema, con Código: ");
                Query_AuditoriaInsert.setInt(7, in.getCodigo());
                Query_AuditoriaInsert.setString(8, in.getDescripcion());
                Query_AuditoriaInsert.setString(9, in.getUnidad().getDescripcion());
                Query_AuditoriaInsert.execute();
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
    public boolean validarExistencia(Insumo i){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement queryValidarExistencia= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[insumo] WHERE [is_desc] =? AND [is_unidad_cdgo]=?;");
            queryValidarExistencia.setString(1, i.getDescripcion());
            queryValidarExistencia.setInt(2, i.getUnidad().getCodigo());
            ResultSet resultSetValidarExistencia= queryValidarExistencia.executeQuery();
            while(resultSetValidarExistencia.next()){ 
                retorno =true;               
            }
        }catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null, "Error al Tratar de buscar");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return retorno;
    }  
    public boolean validarExistenciaActualizar(Insumo i){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement queryValidarExistencia= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[insumo] WHERE [is_desc] =? AND [is_unidad_cdgo]=? AND [is_cdgo]<> ?;");
            queryValidarExistencia.setString(1, i.getDescripcion());
            queryValidarExistencia.setInt(2, i.getUnidad().getCodigo());
            queryValidarExistencia.setInt(3, i.getCodigo());
            ResultSet resultSetValidarExistencia= queryValidarExistencia.executeQuery();
            while(resultSetValidarExistencia.next()){ 
                retorno =true;               
            }
        }catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null, "Error al Tratar de buscar");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return retorno;
    } 
    public int buscar_nombre(String nombreInsumo) throws SQLException{
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscarNombre= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[insumo] WHERE [is_desc] like ?;");
            queryBuscarNombre.setString(1, nombreInsumo);
            ResultSet resultSetBuscarNombre= queryBuscarNombre.executeQuery();
            while(resultSetBuscarNombre.next()){ 
                return resultSetBuscarNombre.getInt(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los insumos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return 0;
    } 
    public ArrayList<Insumo> buscar(String valorConsulta) throws SQLException{
        ArrayList<Insumo> listadoInsumo = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            if(valorConsulta.equals("")){
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT is_cdgo, is_desc, un_cdgo, un_desc, un_estad, is_cant, CASE WHEN (is_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS is_estado FROM ["+DB+"].[dbo].[insumo]  INNER JOIN [unidad]  ON insumo.is_unidad_cdgo = unidad.un_cdgo;");
                resultSetBuscar=queryBuscar.executeQuery();
            }else{
               PreparedStatement queryBuscar= conexion.prepareStatement("SELECT is_cdgo, is_desc, un_cdgo, un_desc, un_estad, is_cant, CASE WHEN (is_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS is_estado FROM ["+DB+"].[dbo].[insumo]  INNER JOIN [unidad]  ON insumo.is_unidad_cdgo = unidad.un_cdgo WHERE [is_desc] like ?;");
               queryBuscar.setString(1, "%"+valorConsulta+"%");
               resultSetBuscar=queryBuscar.executeQuery();
            }
            while(resultSetBuscar.next()){ 
                Insumo in = new Insumo(); 
                in.setCodigo(resultSetBuscar.getInt(1));
                in.setDescripcion(resultSetBuscar.getString(2));
                in.setUnidad(new Unidad(resultSetBuscar.getInt(3),resultSetBuscar.getString(4),resultSetBuscar.getString(5)));
                in.setCantidad(resultSetBuscar.getInt(6));
                in.setEstado(resultSetBuscar.getString(7));
                listadoInsumo.add(in);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las unidades");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoInsumo;
    } 
    public Insumo buscarInsumoEspecifico(int insumo_cdgo) throws SQLException{
        Insumo in = new Insumo(); 
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscarInsumoEspecifico= conexion.prepareStatement("SELECT is_cdgo, is_desc, un_cdgo, un_desc, un_estad, is_cant, CASE WHEN (is_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS is_estado FROM ["+DB+"].[dbo].[insumo]  INNER JOIN [unidad]  ON insumo.is_unidad_cdgo = unidad.un_cdgo WHERE [is_cdgo] like ?;");
            queryBuscarInsumoEspecifico.setInt(1, insumo_cdgo);
            ResultSet resultSetBuscarInsumoEspecifico=queryBuscarInsumoEspecifico.executeQuery();
            while(resultSetBuscarInsumoEspecifico.next()){  
                in.setCodigo(resultSetBuscarInsumoEspecifico.getInt(1));
                in.setDescripcion(resultSetBuscarInsumoEspecifico.getString(2));
                in.setUnidad(new Unidad(resultSetBuscarInsumoEspecifico.getInt(3),resultSetBuscarInsumoEspecifico.getString(4),resultSetBuscarInsumoEspecifico.getString(5)));
                in.setCantidad(resultSetBuscarInsumoEspecifico.getInt(6));
                in.setEstado(resultSetBuscarInsumoEspecifico.getString(7));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los insumos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return in;
    } 
    public ArrayList<Insumo> buscarInsumosActivos() throws SQLException{
        ArrayList<Insumo> listadoInsumo = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscarBuscarInsumosActivos= conexion.prepareStatement("SELECT is_cdgo, is_desc, un_cdgo, un_desc, un_estad, is_cant, CASE WHEN (is_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS is_estado FROM ["+DB+"].[dbo].[insumo]  INNER JOIN [unidad]  ON insumo.is_unidad_cdgo = unidad.un_cdgo WHERE [is_estad]=1;");
            ResultSet resultSetBuscarInsumosActivos=queryBuscarBuscarInsumosActivos.executeQuery();
            while(resultSetBuscarInsumosActivos.next()){ 
                Insumo in = new Insumo(); 
                in.setCodigo(resultSetBuscarInsumosActivos.getInt(1));
                in.setDescripcion(resultSetBuscarInsumosActivos.getString(2));
                in.setUnidad(new Unidad(resultSetBuscarInsumosActivos.getInt(3),resultSetBuscarInsumosActivos.getString(4),resultSetBuscarInsumosActivos.getString(5)));
                in.setCantidad(resultSetBuscarInsumosActivos.getInt(6));
                in.setEstado(resultSetBuscarInsumosActivos.getString(7));
                listadoInsumo.add(in);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los insumos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoInsumo;
    } 
    public int actualizar( Insumo is, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        int result=0;
        try{
            Insumo insumoAnterior=buscarInsumoEspecifico(is.getCodigo());
            String valorEstadoInsumo="";
            if(is.getEstado().equalsIgnoreCase("1")){
                valorEstadoInsumo="ACTIVO";
            }else{
                valorEstadoInsumo="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[insumo] SET  [is_desc]=?, [is_estad] =?,[is_unidad_cdgo]=? WHERE [is_cdgo]=?");
            queryActualizar.setString(1, is.getDescripcion());
            queryActualizar.setString(2, is.getEstado());
            queryActualizar.setInt(3, is.getUnidad().getCodigo());
            queryActualizar.setInt(4, is.getCodigo());
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
                        "           ,'INSUMO'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre el insumo de Código: ',?,' Nombre: ',?,' ',?,' Estado: ',?,' actualizando la siguiente informacion a Código: ',?,' Nombre: ',?,' ',?,' Estado: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setInt(5, insumoAnterior.getCodigo());
                Query_Auditoria.setInt(6, insumoAnterior.getCodigo());
                Query_Auditoria.setString(7, insumoAnterior.getDescripcion());
                Query_Auditoria.setString(8, insumoAnterior.getUnidad().getDescripcion());
                Query_Auditoria.setString(9, insumoAnterior.getEstado());
                Query_Auditoria.setInt(10, is.getCodigo());
                Query_Auditoria.setString(11, is.getDescripcion());
                Query_Auditoria.setString(12, is.getUnidad().getDescripcion());
                Query_Auditoria.setString(13, valorEstadoInsumo);
                Query_Auditoria.execute();
                result=1;
            }
        }
        catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "Error al tratar de actualizar verifique los valores ingresados");
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    }  
}
