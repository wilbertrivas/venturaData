package ModuloEquipo.Controller;

import ConnectionDB.Conexion_DB_costos_vg;
import Catalogo.Model.Producto;
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
 
public class ControlDB_Producto {
    private Connection conexion=null;
    private Conexion_DB_costos_vg control=null;  
    private String tipoConexion;
    
    public ControlDB_Producto(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    public int registrar(Producto Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
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
            if(!validarPorDescripcion(Objeto)){
                conexion= control.ConectarBaseDatos();
                if(!validarPorCodigo(Objeto)){
                    conexion= control.ConectarBaseDatos();
                    PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[producto] ([pr_cdgo],[pr_desc],[pr_estad]) VALUES ((SELECT (CASE WHEN (MAX([pr_cdgo]) IS NULL)\n" +
                                                                                                    " THEN 1\n" +
                                                                                                    " ELSE (MAX([pr_cdgo])+1) END)AS [pr_cdgo]\n" +
                                                                                " FROM ["+DB+"].[dbo].[producto]),?,?);");
                    Query.setString(1, Objeto.getDescripcion());
                    Query.setString(2, Objeto.getEstado());
                    Query.execute();
                    result=1;
                    if(result==1){
                        result=0;
                        //Sacamos el ultimo valor 
                        PreparedStatement queryMaximo= conexion.prepareStatement("SELECT MAX(pr_cdgo) FROM ["+DB+"].[dbo].[producto];");
                        ResultSet resultSetMaximo= queryMaximo.executeQuery();
                        while(resultSetMaximo.next()){ 
                            if(resultSetMaximo.getString(1) != null){
                                Objeto.setCodigo(resultSetMaximo.getString(1));
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
                        "           ,'PRODUCTO'" +
                        "           ,CONCAT (?,?,' Nombre: ',?,' Estado: ',?));");
                        Query_Auditoria.setString(1, us.getCodigo());
                        Query_Auditoria.setString(2, namePc);
                        Query_Auditoria.setString(3, ipPc);
                        Query_Auditoria.setString(4, macPC);
                        Query_Auditoria.setString(5, Objeto.getCodigo());
                        Query_Auditoria.setString(6, "Se registró un nuevo producto en el sistema, con Código: ");
                        Query_Auditoria.setString(7, Objeto.getCodigo());
                        Query_Auditoria.setString(8, Objeto.getDescripcion());
                        Query_Auditoria.setString(9, estado);
                        Query_Auditoria.execute();
                        result=1;
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Ya existe un producto registrado con ese código");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Ya existe un producto registrado con ese nombre");
            }
        }
        catch (SQLException sqlException ){   
            result=0;
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    } 
    public ArrayList<Producto> buscar(String valorConsulta) throws SQLException{
        ArrayList<Producto> listadoObjetos = new ArrayList();
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            if(valorConsulta.equals("")){
                PreparedStatement query= conexion.prepareStatement("SELECT pr_cdgo, pr_desc, CASE WHEN (pr_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS pr_estad FROM ["+DB+"].[dbo].[producto];");
                resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexion.prepareStatement("SELECT pr_cdgo, pr_desc, CASE WHEN (pr_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS pr_estad FROM ["+DB+"].[dbo].[producto] WHERE [pr_desc] like ?;");
                query.setString(1, "%"+valorConsulta+"%");
                resultSet= query.executeQuery();              
            }
            while(resultSet.next()){  
                Producto Objeto = new Producto(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los productos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public Producto buscarEspecifico(String codigo) throws SQLException{
        Producto Objeto =null; 
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT pr_cdgo, pr_desc, CASE WHEN (pr_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS pr_estad FROM ["+DB+"].[dbo].[producto] WHERE [pr_cdgo] = ?;"); 
            query.setString(1, codigo);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                Objeto = new Producto(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los productos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return Objeto;
    } 
    public ArrayList<Producto> buscarActivos() throws SQLException{
        ArrayList<Producto> listadoObjetos = null;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT pr_cdgo, pr_desc, CASE WHEN (pr_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS pr_estad FROM ["+DB+"].[dbo].[producto] WHERE [pr_estad]=1;");
            ResultSet resultSet= query.executeQuery();
            boolean validator=true;
            while(resultSet.next()){ 
                if(validator){
                    listadoObjetos = new ArrayList();
                    validator= false;
                }
                Producto Objetos = new Producto(); 
                Objetos.setCodigo(resultSet.getString(1));
                Objetos.setDescripcion(resultSet.getString(2));
                Objetos.setEstado(resultSet.getString(3));
                listadoObjetos.add(Objetos); 
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los productos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public String buscar_nombre(String nombre) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[producto] WHERE [pr_desc] like ?;");
            query.setString(1, nombre);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                return resultSet.getString(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los productos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } 
    public String buscar_Id(String id) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[producto] WHERE [pr_cdgo] =?;");
            query.setString(1, id);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                return resultSet.getString(2);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los productos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } 
    public boolean validarPorDescripcion(Producto Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[producto] WHERE [pr_desc] like ?;");
            query.setString(1, Objeto.getDescripcion());
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
    public boolean validarPorCodigo(Producto Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[producto] WHERE [pr_cdgo] like ?;");
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
    public boolean validarExistenciaActualizar(Producto Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[producto] WHERE [pr_desc] like ? AND [pr_cdgo]<> ?;");
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
    public int actualizar(Producto Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            Producto ProductoAnterior=buscarEspecifico(""+Objeto.getCodigo());
            String estado="";
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                estado="ACTIVO";
            }else{
                estado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement query= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[producto] set [pr_desc]=?, [pr_estad]=? WHERE [pr_cdgo]=?");
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
                        "           ,'PRODUCTO'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre ',?,' Código: ',?,' Nombre: ',?,' Estado: ',?,"
                                            + "' actualizando la siguiente informacion a Código: ',?,' Nombre: ',?,' Estado: ',?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, ProductoAnterior.getCodigo());   
                Query_Auditoria.setString(6, " el producto, ");
                Query_Auditoria.setString(7, ProductoAnterior.getCodigo());
                Query_Auditoria.setString(8, ProductoAnterior.getDescripcion());
                Query_Auditoria.setString(9, ProductoAnterior.getEstado());
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
