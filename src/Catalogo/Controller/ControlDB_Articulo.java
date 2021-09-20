package Catalogo.Controller;
  
import ConnectionDB.Conexion_DB_ccargaGP;
import ConnectionDB.Conexion_DB_costos_vg;
import Catalogo.Model.Articulo;
import Catalogo.Model.BaseDatos;
import Catalogo.Model.TipoArticulo;
import ConnectionDB.Conexion_DB_ccargaOPP;
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

public class ControlDB_Articulo {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private String tipoConexion;
    
    public ControlDB_Articulo(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    public int registrar(Articulo Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
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
                if(Objeto.getTipoArticulo().getCodigo() != null){
                    if(!new ControlDB_TipoArticulo(tipoConexion).validarExistencia(Objeto.getTipoArticulo())){
                        conexion= control.ConectarBaseDatos();

                        new ControlDB_TipoArticulo(tipoConexion).registrar(Objeto.getTipoArticulo(),us);
                        conexion= control.ConectarBaseDatos();
                    }
                }
                PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[articulo] ([ar_cdgo],[ar_tipo_articulo_cdgo],[ar_desc],[ar_estad],[ar_base_datos_cdgo]) VALUES (?,?,?,?,?);");
                Query.setString(1, Objeto.getCodigo());
                Query.setString(2, Objeto.getTipoArticulo().getCodigo());
                Query.setString(3, Objeto.getDescripcion());
                Query.setString(4, Objeto.getEstado());
                Query.setString(5, Objeto.getBaseDatos().getCodigo());
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
                        "           ,'ARTICULO'" +
                        "           ,CONCAT (?,?,'TipoArticulo:',?,' Nombre: ',?,' Estado: ',?,' BaseDatos: ',? ));");
                    Query_Auditoria.setString(1, us.getCodigo());
                    Query_Auditoria.setString(2, namePc);
                    Query_Auditoria.setString(3, ipPc);
                    Query_Auditoria.setString(4, macPC);
                    Query_Auditoria.setString(5, Objeto.getCodigo());
                    Query_Auditoria.setString(6, "Se registró un nuevo articulo en el sistema, con Código: ");
                    Query_Auditoria.setString(7, Objeto.getCodigo());
                    Query_Auditoria.setString(8, Objeto.getTipoArticulo().getCodigo());
                    Query_Auditoria.setString(9, Objeto.getDescripcion());
                    Query_Auditoria.setString(10, estado);
                    Query_Auditoria.setString(11, Objeto.getBaseDatos().getNombre());
                    Query_Auditoria.execute();
                    result=1;
                }
            }/*else{
                try{
                    Articulo ProductoAnterior=buscarEspecifico(""+Objeto.getCodigo());
                    if(Objeto.getEstado().equalsIgnoreCase("1")){
                        estado="ACTIVO";
                    }else{
                        estado="INACTIVO";
                    }
                    conexion= control.ConectarBaseDatos();
                    new ControlDB_TipoArticulo(tipoConexion).actualizar(Objeto.getTipoArticulo(), us);
                    PreparedStatement query= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[articulo] SET [ar_tipo_articulo_cdgo]=?, [ar_desc]=? --, [ar_estad]=?\n WHERE [ar_cdgo]=? AND ");
                    query.setString(1, Objeto.getTipoArticulo().getCodigo());
                    query.setString(2, Objeto.getDescripcion());
                    //query.setString(3, Objeto.getEstado());
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
                                "           ,'ACTICULO'" +
                                "           ,CONCAT('Se registró una nueva actualización en el sistema sobre ',?,' Código: ',?,' TipoArticulo:',?,' Nombre: ',?,' Estado: ',?,"
                                                    + "' actualizando la siguiente informacion a Código: ',?,' TipoArticulo:',?,' Nombre: ',?,' Estado: ',?));");
                        Query_Auditoria.setString(1, us.getCodigo());
                        Query_Auditoria.setString(2, namePc);
                        Query_Auditoria.setString(3, ipPc);
                        Query_Auditoria.setString(4, macPC);
                        Query_Auditoria.setString(5, ProductoAnterior.getCodigo());
                        Query_Auditoria.setString(6, "el articulo :");
                        Query_Auditoria.setString(7, ProductoAnterior.getCodigo());
                        Query_Auditoria.setString(8, ProductoAnterior.getTipoArticulo().getCodigo());
                        Query_Auditoria.setString(9, ProductoAnterior.getDescripcion());
                        Query_Auditoria.setString(10, ProductoAnterior.getEstado());
                        Query_Auditoria.setString(11, Objeto.getCodigo());
                        Query_Auditoria.setString(12, Objeto.getTipoArticulo().getCodigo());
                        Query_Auditoria.setString(13, Objeto.getDescripcion());
                        Query_Auditoria.setString(14, estado);
                        Query_Auditoria.execute();
                        result=1;
                        System.out.print("INSERT INTO ["+DB+"].[dbo].[auditoria]([au_cdgo]\n" +
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
                                "           ,'ACTICULO'" +
                                "           ,CONCAT('Se registró una nueva actualización en el sistema sobre ',?,' Código: ',?,' TipoArticulo:',?,' Nombre: ',?,' Estado: ',?,"
                                                    + "' actualizando la siguiente informacion a Código: ',?,' TipoArticulo:',?,' Nombre: ',?,' Estado: ',?));");
                    }
                }
                catch (SQLException sqlException ){   
                    result=0;
                    JOptionPane.showMessageDialog(null, "ERROR al querer insertar los datos.");
                    sqlException.printStackTrace();
                }  
                catch(Exception e ){
                    e.printStackTrace();
                }
                control.cerrarConexionBaseDatos();
                return result;
            }*/
        }
        catch (SQLException sqlException ){   
            result=0;
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    } 
    public ArrayList<Articulo> buscar(String valorConsulta) throws SQLException{
        ArrayList<Articulo> listadoObjetos = new ArrayList();
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            if(valorConsulta.equals("")){
                PreparedStatement query= conexion.prepareStatement("SELECT  [ar_cdgo]--1\n" +
                                                                    "      ,[ar_tipo_articulo_cdgo]--2\n" +
                                                                    "		  ,[tar_cdgo]--3\n" +
                                                                    "		  ,[tar_desc]--4\n" +
                                                                    "		  ,[tar_cdgo_erp]--5\n" +
                                                                    "		  ,[tar_undad_ngcio]--6\n" +
                                                                    "		  ,[tar_estado]--7\n" +
                                                                    "      ,[ar_desc]--8\n" +
                                                                    "      ,CASE WHEN (ar_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS ar_estad--9\n" +
                                                                    "      ,[ar_base_datos_cdgo]--10\n" +
                                                                    "		  ,[bd_cdgo]--11\n" +
                                                                    "		  ,[bd_name]--12\n" +
                                                                    "		  ,[bd_desc]--13\n" +
                                                                    "		  ,[bd_estad]--14\n" +
                                                                    "  FROM ["+DB+"].[dbo].[articulo]\n" +
                                                                    "  LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] ON [ar_tipo_articulo_cdgo]=[tar_cdgo]\n" +
                                                                    "  INNER JOIN ["+DB+"].[dbo].[base_datos] ON [ar_base_datos_cdgo]=[bd_cdgo]     ORDER BY [ar_desc] ASC;");
                resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexion.prepareStatement("SELECT  [ar_cdgo]--1\n" +
                                                                    "      ,[ar_tipo_articulo_cdgo]--2\n" +
                                                                    "		  ,[tar_cdgo]--3\n" +
                                                                    "		  ,[tar_desc]--4\n" +
                                                                    "		  ,[tar_cdgo_erp]--5\n" +
                                                                    "		  ,[tar_undad_ngcio]--6\n" +
                                                                    "		  ,[tar_estado]--7\n" +
                                                                    "      ,[ar_desc]--8\n" +
                                                                    "      ,CASE WHEN (ar_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS ar_estad--9\n" +
                                                                    "      ,[ar_base_datos_cdgo]--10\n" +
                                                                    "		  ,[bd_cdgo]--11\n" +
                                                                    "		  ,[bd_name]--12\n" +
                                                                    "		  ,[bd_desc]--13\n" +
                                                                    "		  ,[bd_estad]--14\n" +
                                                                    "  FROM ["+DB+"].[dbo].[articulo]\n" +
                                                                    "  LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] ON [ar_tipo_articulo_cdgo]=[tar_cdgo]\n" +
                                                                    "  INNER JOIN ["+DB+"].[dbo].[base_datos] ON [ar_base_datos_cdgo]=[bd_cdgo]  WHERE ([ar_cdgo] LIKE ? OR [ar_desc] like ?);");
                query.setString(1, "%"+valorConsulta+"%");
                query.setString(2, "%"+valorConsulta+"%");
                resultSet= query.executeQuery();              
            }
            while(resultSet.next()){  
                Articulo Objeto = new Articulo(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(8));
                Objeto.setEstado(resultSet.getString(9));
                    TipoArticulo Objeto2 = new TipoArticulo();
                    Objeto2.setCodigo(resultSet.getString(3));
                    Objeto2.setDescripcion(resultSet.getString(4));
                    Objeto2.setCodigoERP(resultSet.getString(5));
                    Objeto2.setUnidadNegocio(resultSet.getString(6));
                    Objeto2.setEstado(resultSet.getString(7));
                Objeto.setTipoArticulo(Objeto2);
                BaseDatos baseDatos = new BaseDatos();
                        baseDatos.setCodigo(resultSet.getString(11));
                        baseDatos.setNombre(resultSet.getString(12));
                        baseDatos.setDescripcion(resultSet.getString(13));
                        baseDatos.setEstado(resultSet.getString(14));
                Objeto.setBaseDatos(baseDatos);
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los articulo");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public Articulo buscarEspecifico(String codigo, String db) throws SQLException{
        Articulo Objeto =null; 
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  [ar_cdgo]--1\n" +
                                                                    "      ,[ar_tipo_articulo_cdgo]--2\n" +
                                                                    "		  ,[tar_cdgo]--3\n" +
                                                                    "		  ,[tar_desc]--4\n" +
                                                                    "		  ,[tar_cdgo_erp]--5\n" +
                                                                    "		  ,[tar_undad_ngcio]--6\n" +
                                                                    "		  ,[tar_estado]--7\n" +
                                                                    "      ,[ar_desc]--8\n" +
                                                                    "      ,CASE WHEN (ar_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS ar_estad--9\n" +
                                                                    "      ,[ar_base_datos_cdgo]--10\n" +
                                                                    "		  ,[bd_cdgo]--11\n" +
                                                                    "		  ,[bd_name]--12\n" +
                                                                    "		  ,[bd_desc]--13\n" +
                                                                    "		  ,[bd_estad]--14\n" +
                                                                    "  FROM ["+DB+"].[dbo].[articulo]\n" +
                                                                    "  LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] ON [ar_tipo_articulo_cdgo]=[tar_cdgo]\n" +
                                                                    "  INNER JOIN ["+DB+"].[dbo].[base_datos] ON [ar_base_datos_cdgo]=[bd_cdgo]  WHERE [ar_cdgo] LIKE ? AND ar_base_datos_cdgo=?;"); 
            query.setString(1, codigo);
            query.setString(2, db);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                Objeto = new Articulo(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(8));
                Objeto.setEstado(resultSet.getString(9));
                    TipoArticulo Objeto2 = new TipoArticulo();
                    Objeto2.setCodigo(resultSet.getString(3));
                    Objeto2.setDescripcion(resultSet.getString(4));
                    Objeto2.setCodigoERP(resultSet.getString(5));
                    Objeto2.setUnidadNegocio(resultSet.getString(6));
                    Objeto2.setEstado(resultSet.getString(7));
                Objeto.setTipoArticulo(Objeto2);
                BaseDatos baseDatos = new BaseDatos();
                        baseDatos.setCodigo(resultSet.getString(11));
                        baseDatos.setNombre(resultSet.getString(12));
                        baseDatos.setDescripcion(resultSet.getString(13));
                        baseDatos.setEstado(resultSet.getString(14));
                Objeto.setBaseDatos(baseDatos);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los articulos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return Objeto;
    } 
    public ArrayList<Articulo> buscarActivos() throws SQLException{
        ArrayList<Articulo> listadoObjetos = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  [ar_cdgo]--1\n" +
                                                                    "      ,[ar_tipo_articulo_cdgo]--2\n" +
                                                                    "		  ,[tar_cdgo]--3\n" +
                                                                    "		  ,[tar_desc]--4\n" +
                                                                    "		  ,[tar_cdgo_erp]--5\n" +
                                                                    "		  ,[tar_undad_ngcio]--6\n" +
                                                                    "		  ,[tar_estado]--7\n" +
                                                                    "      ,[ar_desc]--8\n" +
                                                                    "      ,CASE WHEN (ar_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS ar_estad--9\n" +
                                                                    "      ,[ar_base_datos_cdgo]--10\n" +
                                                                    "		  ,[bd_cdgo]--11\n" +
                                                                    "		  ,[bd_name]--12\n" +
                                                                    "		  ,[bd_desc]--13\n" +
                                                                    "		  ,[bd_estad]--14\n" +
                                                                    "  FROM ["+DB+"].[dbo].[articulo]\n" +
                                                                    "  LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] ON [ar_tipo_articulo_cdgo]=[tar_cdgo]\n" +
                                                                    "  INNER JOIN ["+DB+"].[dbo].[base_datos] ON [ar_base_datos_cdgo]=[bd_cdgo]  WHERE [ar_estad]=1;");
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                Articulo Objeto = new Articulo(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(8));
                Objeto.setEstado(resultSet.getString(9));
                    TipoArticulo Objeto2 = new TipoArticulo();
                    Objeto2.setCodigo(resultSet.getString(3));
                    Objeto2.setDescripcion(resultSet.getString(4));
                    Objeto2.setCodigoERP(resultSet.getString(5));
                    Objeto2.setUnidadNegocio(resultSet.getString(6));
                    Objeto2.setEstado(resultSet.getString(7));
                Objeto.setTipoArticulo(Objeto2);
                BaseDatos baseDatos = new BaseDatos();
                        baseDatos.setCodigo(resultSet.getString(11));
                        baseDatos.setNombre(resultSet.getString(12));
                        baseDatos.setDescripcion(resultSet.getString(13));
                        baseDatos.setEstado(resultSet.getString(14));
                Objeto.setBaseDatos(baseDatos);
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los articulos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public ArrayList<Articulo> buscarActivosConParametros(String valorBusqueda) throws SQLException{
        ArrayList<Articulo> listadoObjetos = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  [ar_cdgo]--1\n" +
                                                                    "      ,[ar_tipo_articulo_cdgo]--2\n" +
                                                                    "		  ,[tar_cdgo]--3\n" +
                                                                    "		  ,[tar_desc]--4\n" +
                                                                    "		  ,[tar_cdgo_erp]--5\n" +
                                                                    "		  ,[tar_undad_ngcio]--6\n" +
                                                                    "		  ,[tar_estado]--7\n" +
                                                                    "      ,[ar_desc]--8\n" +
                                                                    "      ,CASE WHEN (ar_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS ar_estad--9\n" +
                                                                    "      ,[ar_base_datos_cdgo]--10\n" +
                                                                    "		  ,[bd_cdgo]--11\n" +
                                                                    "		  ,[bd_name]--12\n" +
                                                                    "		  ,[bd_desc]--13\n" +
                                                                    "		  ,[bd_estad]--14\n" +
                                                                    "  FROM ["+DB+"].[dbo].[articulo]\n" +
                                                                    "  LEFT JOIN ["+DB+"].[dbo].[tipo_articulo] ON [ar_tipo_articulo_cdgo]=[tar_cdgo]\n" +
                                                                    "  INNER JOIN ["+DB+"].[dbo].[base_datos] ON [ar_base_datos_cdgo]=[bd_cdgo]  WHERE [ar_estad]=1 AND [ar_desc] LIKE ?;");
            query.setString(1, "%"+valorBusqueda+"%");
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                Articulo Objeto = new Articulo(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(8));
                Objeto.setEstado(resultSet.getString(9));
                    TipoArticulo Objeto2 = new TipoArticulo();
                    Objeto2.setCodigo(resultSet.getString(3));
                    Objeto2.setDescripcion(resultSet.getString(4));
                    Objeto2.setCodigoERP(resultSet.getString(5));
                    Objeto2.setUnidadNegocio(resultSet.getString(6));
                    Objeto2.setEstado(resultSet.getString(7));
                Objeto.setTipoArticulo(Objeto2);
                BaseDatos baseDatos = new BaseDatos();
                        baseDatos.setCodigo(resultSet.getString(11));
                        baseDatos.setNombre(resultSet.getString(12));
                        baseDatos.setDescripcion(resultSet.getString(13));
                        baseDatos.setEstado(resultSet.getString(14));
                Objeto.setBaseDatos(baseDatos);
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los articulos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    /*public String buscar_nombre(String nombre) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[articulo] WHERE [ar_desc] like ?;");
            query.setString(1, nombre);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                return resultSet.getString(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los articulos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } */
   /* public String buscar_Id(String id) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[articulo] WHERE [ar_cdgo] =?;");
            query.setString(1, id);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                return resultSet.getString(3);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los articulos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } */
    public boolean validarExistencia(Articulo Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[articulo] WHERE [ar_cdgo] like ? AND [ar_base_datos_cdgo]=?;");
            query.setString(1, Objeto.getCodigo());
            query.setString(2, Objeto.getBaseDatos().getCodigo());
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
    /*public boolean validarExistenciaActualizar(Articulo Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[articulo] WHERE [ar_desc] like ? AND [ar_cdgo]<> ?;");
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
    public int actualizar(Articulo Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            Articulo ProductoAnterior=buscarEspecifico(""+Objeto.getCodigo(), Objeto.getBaseDatos().getCodigo());
            String estado="";
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                estado="ACTIVO";
            }else{
                estado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();      
            PreparedStatement query;
            if(Objeto.getTipoArticulo().getCodigo().equals("NULL")){
                 query= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[articulo] SET [ar_tipo_articulo_cdgo]=NULL, [ar_desc]=?, [ar_estad]=? "
                    + "WHERE [ar_cdgo] LIKE ? AND [ar_base_datos_cdgo]=?");
                  query.setString(1, Objeto.getDescripcion());
                query.setString(2, Objeto.getEstado());
                query.setString(3, Objeto.getCodigo());
                query.setString(4, Objeto.getBaseDatos().getCodigo());
            }else{
                query= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[articulo] SET [ar_tipo_articulo_cdgo]=?, [ar_desc]=?, [ar_estad]=? "
                    + "WHERE [ar_cdgo] LIKE ? AND [ar_base_datos_cdgo]=?");
                  query.setString(1, Objeto.getTipoArticulo().getCodigo());
                  query.setString(2, Objeto.getDescripcion());
                query.setString(3, Objeto.getEstado());
                query.setString(4, Objeto.getCodigo());
                query.setString(5, Objeto.getBaseDatos().getCodigo());
            }                      
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
                        "           ,'ACTICULO'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre ',?,' Código: ',?,' TipoArticulo:',?,' Nombre: ',?,' Estado: ',?,"
                                            + "' actualizando la siguiente informacion a Código: ',?,' TipoArticulo:',?,' Nombre: ',?,' Estado: ',?,' BaseDatos: ',?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, ProductoAnterior.getCodigo());
                Query_Auditoria.setString(6, "el articulo :");
                Query_Auditoria.setString(7, ProductoAnterior.getCodigo());
                Query_Auditoria.setString(8, ProductoAnterior.getTipoArticulo().getCodigo());
                Query_Auditoria.setString(9, ProductoAnterior.getDescripcion());
                Query_Auditoria.setString(10, ProductoAnterior.getEstado());
                Query_Auditoria.setString(11, Objeto.getCodigo());
                Query_Auditoria.setString(12, Objeto.getTipoArticulo().getCodigo());
                Query_Auditoria.setString(13, Objeto.getDescripcion());
                Query_Auditoria.setString(14, estado);
                Query_Auditoria.setString(15, Objeto.getBaseDatos().getCodigo());
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

     //Consultas Articulo en Ccarga GP
    public ArrayList<Articulo> buscarArticuloGP(String valor) throws SQLException{
        ArrayList<Articulo> listadoArticulo = new ArrayList();
        Conexion_DB_ccargaGP controlGP = new Conexion_DB_ccargaGP(tipoConexion);
        Connection conexionGP=null;
        conexionGP= controlGP.ConectarBaseDatos();
        String DB=controlGP.getBaseDeDatos();
        try{
            ResultSet resultSet;
            if(valor.equalsIgnoreCase("")){
                PreparedStatement query= conexionGP.prepareStatement("SELECT  [ar_cdgo] \n" +
                                                                    "        ,[ar_nmbre] \n" +
                                                                    "		,[ar_actvo] \n" +
                                                                    "		,[ta_cdgo]\n" +
                                                                    "        ,[ta_dscrpcion]\n" +
                                                                    "        ,[ta_cdgos_erp]\n" +
                                                                    "        ,[ta_undad_ngcio]\n" +
                                                                    "		,1 AS ta_estad\n" +
                                                                    "    FROM ["+DB+"].[dbo].[artclo] \n" +
                                                                    "	LEFT JOIN ["+DB+"].[dbo].[tpo_artclo] ON [ar_tpo_artclo] =[ta_cdgo]\n" +
                                                                    "	ORDER BY ar_nmbre ASC");
                resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexionGP.prepareStatement("SELECT  [ar_cdgo] \n" +
                                                                    "        ,[ar_nmbre] \n" +
                                                                    "		,[ar_actvo] \n" +
                                                                    "	     ,[ta_cdgo]\n" +
                                                                    "        ,[ta_dscrpcion]\n" +
                                                                    "        ,[ta_cdgos_erp]\n" +
                                                                    "        ,[ta_undad_ngcio]\n" +
                                                                    "		,1 AS ta_estad\n" +
                                                                    "    FROM ["+DB+"].[dbo].[artclo] \n" +
                                                                    "	LEFT JOIN ["+DB+"].[dbo].[tpo_artclo] ON [ar_tpo_artclo] =[ta_cdgo]"
                                                                            + " WHERE ([ar_cdgo] LIKE ? OR [ar_nmbre] LIKE ?) ORDER BY ar_nmbre ASC");
                query.setString(1, "%"+valor+"%");
                query.setString(2, "%"+valor+"%");
                resultSet= query.executeQuery();
            }
            while(resultSet.next()){ 
                Articulo Objeto = new Articulo(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                    TipoArticulo Objeto2 = new TipoArticulo();
                    Objeto2.setCodigo(resultSet.getString(4));
                    Objeto2.setDescripcion(resultSet.getString(5));
                    Objeto2.setCodigoERP(resultSet.getString(6));
                    Objeto2.setUnidadNegocio(resultSet.getString(7));
                    Objeto2.setEstado(resultSet.getString(8));
                Objeto.setTipoArticulo(Objeto2);
                Objeto.setBaseDatos(new BaseDatos("1"));
                listadoArticulo.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los articulos en ccarga GP");
            sqlException.printStackTrace();
        } 
        controlGP.cerrarConexionBaseDatos();
        return listadoArticulo;
    }
     //Consultas Articulo en Ccarga OPP
    public ArrayList<Articulo> buscarArticuloOPP(String valor) throws SQLException{
        ArrayList<Articulo> listadoArticulo = new ArrayList();
        Conexion_DB_ccargaOPP controlOPP = new Conexion_DB_ccargaOPP(tipoConexion);
        Connection conexionOPP=null;
        conexionOPP= controlOPP.ConectarBaseDatos();
        String DB=controlOPP.getBaseDeDatos();
        try{
            ResultSet resultSet;
            if(valor.equalsIgnoreCase("")){
                PreparedStatement query= conexionOPP.prepareStatement("SELECT  [ar_cdgo] \n" +
                                                                    "        ,[ar_nmbre] \n" +
                                                                    "		,[ar_actvo] \n" +
                                                                    "		,[ta_cdgo]\n" +
                                                                    "        ,[ta_dscrpcion]\n" +
                                                                    "        ,[ta_cdgos_erp]\n" +
                                                                    "        ,[ta_undad_ngcio]\n" +
                                                                    "		,1 AS ta_estad\n" +
                                                                    "    FROM ["+DB+"].[dbo].[artclo] \n" +
                                                                    "	LEFT JOIN ["+DB+"].[dbo].[tpo_artclo] ON [ar_tpo_artclo] =[ta_cdgo]\n" +
                                                                    "	ORDER BY ar_nmbre ASC");
                resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexionOPP.prepareStatement("SELECT  [ar_cdgo] \n" +
                                                                    "        ,[ar_nmbre] \n" +
                                                                    "		,[ar_actvo] \n" +
                                                                    "	     ,[ta_cdgo]\n" +
                                                                    "        ,[ta_dscrpcion]\n" +
                                                                    "        ,[ta_cdgos_erp]\n" +
                                                                    "        ,[ta_undad_ngcio]\n" +
                                                                    "		,1 AS ta_estad\n" +
                                                                    "    FROM ["+DB+"].[dbo].[artclo] \n" +
                                                                    "	LEFT JOIN ["+DB+"].[dbo].[tpo_artclo] ON [ar_tpo_artclo] =[ta_cdgo]"
                                                                            + " WHERE ([ar_cdgo] LIKE ? OR [ar_nmbre] LIKE ?) ORDER BY ar_nmbre ASC");
                query.setString(1, "%"+valor+"%");
                query.setString(2, "%"+valor+"%");
                resultSet= query.executeQuery();
            }
            while(resultSet.next()){ 
                Articulo Objeto = new Articulo(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                    TipoArticulo Objeto2 = new TipoArticulo();
                    Objeto2.setCodigo(resultSet.getString(4));
                    Objeto2.setDescripcion(resultSet.getString(5));
                    Objeto2.setCodigoERP(resultSet.getString(6));
                    Objeto2.setUnidadNegocio(resultSet.getString(7));
                    Objeto2.setEstado(resultSet.getString(8));
                Objeto.setTipoArticulo(Objeto2);
                Objeto.setBaseDatos(new BaseDatos("2"));
                listadoArticulo.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los articulos en ccarga opp");
            sqlException.printStackTrace();
        } 
        controlOPP.cerrarConexionBaseDatos();
        return listadoArticulo;
    }
}
