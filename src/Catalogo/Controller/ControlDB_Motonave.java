package Catalogo.Controller;
  
import Catalogo.Model.BaseDatos;
import ConnectionDB.Conexion_DB_ccargaGP;
import ConnectionDB.Conexion_DB_costos_vg;
import Catalogo.Model.Motonave;
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

public class ControlDB_Motonave {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private String tipoConexion;

    public ControlDB_Motonave(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    public int registrar(Motonave Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
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
                PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[motonave] ([mn_cdgo],[mn_desc],[mn_estad],[mn_base_datos_cdgo]) VALUES (?,?,?,?);");
                Query.setString(1, Objeto.getCodigo());
                Query.setString(2, Objeto.getDescripcion());
                Query.setString(3, Objeto.getEstado());
                Query.setString(4, Objeto.getBaseDatos().getCodigo());
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
                                        "           ,'MOTONAVE'" +
                                        "           ,CONCAT (?,?,' Nombre: ',?,' Estado: ',?,' Base Datos: ',?));");
                    Query_Auditoria.setString(1, us.getCodigo());
                    Query_Auditoria.setString(2, namePc);
                    Query_Auditoria.setString(3, ipPc);
                    Query_Auditoria.setString(4, macPC);
                    Query_Auditoria.setString(5, Objeto.getCodigo());
                    Query_Auditoria.setString(6, "Se registró una nueva motonave en el sistema, con Código: ");
                    Query_Auditoria.setString(7, Objeto.getCodigo());
                    Query_Auditoria.setString(8, Objeto.getDescripcion());
                    Query_Auditoria.setString(9, estado);
                    Query_Auditoria.setString(10, Objeto.getBaseDatos().getNombre());
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
    public ArrayList<Motonave> buscar(String valorConsulta) throws SQLException{
        ArrayList<Motonave> listadoObjetos = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            if(valorConsulta.equals("")){
                PreparedStatement query= conexion.prepareStatement("SELECT  [mn_cdgo]--1\n" +
                                                                    "      ,[mn_desc]--2\n" +
                                                                    "	  ,CASE WHEN (mn_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [mn_estad]--3\n" +
                                                                    "	  ,[bd_cdgo]--4\n" +
                                                                    "      ,[bd_name]--5\n" +
                                                                    "      ,[bd_desc]--6\n" +
                                                                    "      ,[bd_estad]--7\n" +
                                                                    "  FROM ["+DB+"].[dbo].[motonave]\n" +
                                                                    "  INNER JOIN  ["+DB+"].[dbo].[base_datos] ON [mn_base_datos_cdgo]=[bd_cdgo];");
                resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexion.prepareStatement("SELECT  [mn_cdgo]--1\n" +
                                                                    "      ,[mn_desc]--2\n" +
                                                                    "	  ,CASE WHEN (mn_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [mn_estad]--3\n" +
                                                                    "	  ,[bd_cdgo]--4\n" +
                                                                    "      ,[bd_name]--5\n" +
                                                                    "      ,[bd_desc]--6\n" +
                                                                    "      ,[bd_estad]--7\n" +
                                                                    "  FROM ["+DB+"].[dbo].[motonave]\n" +
                                                                    "  INNER JOIN  ["+DB+"].[dbo].[base_datos] ON [mn_base_datos_cdgo]=[bd_cdgo] WHERE [mn_desc] LIKE ? OR [mn_cdgo] LIKE ? ;");
                query.setString(1, "%"+valorConsulta+"%");
                query.setString(2, "%"+valorConsulta+"%");
                resultSet= query.executeQuery();              
            }
            while(resultSet.next()){  
                Motonave Objeto = new Motonave(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                  BaseDatos baseDatos = new BaseDatos();
                        baseDatos.setCodigo(resultSet.getString(4));
                        baseDatos.setNombre(resultSet.getString(5));
                        baseDatos.setDescripcion(resultSet.getString(6));
                        baseDatos.setEstado(resultSet.getString(7));
                Objeto.setBaseDatos(baseDatos);
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las motonaves");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public Motonave buscarEspecifico(String codigo) throws SQLException{
        Motonave Objeto =null; 
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  [mn_cdgo]--1\n" +
                                                                    "      ,[mn_desc]--2\n" +
                                                                    "	  ,CASE WHEN (mn_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [mn_estad]--3\n" +
                                                                    "	  ,[bd_cdgo]--4\n" +
                                                                    "      ,[bd_name]--5\n" +
                                                                    "      ,[bd_desc]--6\n" +
                                                                    "      ,[bd_estad]--7\n" +
                                                                    "  FROM ["+DB+"].[dbo].[motonave]\n" +
                                                                    "  INNER JOIN  ["+DB+"].[dbo].[base_datos] ON [mn_base_datos_cdgo]=[bd_cdgo] WHERE [mn_cdgo] = ?;"); 
            query.setString(1, codigo);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                Objeto = new Motonave(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                  BaseDatos baseDatos = new BaseDatos();
                        baseDatos.setCodigo(resultSet.getString(4));
                        baseDatos.setNombre(resultSet.getString(5));
                        baseDatos.setDescripcion(resultSet.getString(6));
                        baseDatos.setEstado(resultSet.getString(7));
                Objeto.setBaseDatos(baseDatos);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las motonaves");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return Objeto;
    } 
    public ArrayList<Motonave> buscarActivos() throws SQLException{
        ArrayList<Motonave> listadoObjetos = null;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  [mn_cdgo]--1\n" +
                                                                    "      ,[mn_desc]--2\n" +
                                                                    "	  ,CASE WHEN (mn_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [mn_estad]--3\n" +
                                                                    "	  ,[bd_cdgo]--4\n" +
                                                                    "      ,[bd_name]--5\n" +
                                                                    "      ,[bd_desc]--6\n" +
                                                                    "      ,[bd_estad]--7\n" +
                                                                    "  FROM ["+DB+"].[dbo].[motonave]\n" +
                                                                    "  INNER JOIN  ["+DB+"].[dbo].[base_datos] ON [mn_base_datos_cdgo]=[bd_cdgo] WHERE [mn_estad]=1 ORDER BY mn_desc ASC;");
            ResultSet resultSet= query.executeQuery();
            boolean validator=true;
            while(resultSet.next()){ 
                if(validator){
                    listadoObjetos = new ArrayList();
                    validator= false;
                }
                Motonave Objeto = new Motonave(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                  BaseDatos baseDatos = new BaseDatos();
                        baseDatos.setCodigo(resultSet.getString(4));
                        baseDatos.setNombre(resultSet.getString(5));
                        baseDatos.setDescripcion(resultSet.getString(6));
                        baseDatos.setEstado(resultSet.getString(7));
                Objeto.setBaseDatos(baseDatos);
                listadoObjetos.add(Objeto); 
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las motonaves");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public ArrayList<Motonave> buscarActivos(String valorConsulta) throws SQLException{
        ArrayList<Motonave> listadoObjetos = null;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            if(valorConsulta.equals("")){
                PreparedStatement query= conexion.prepareStatement("SELECT  [mn_cdgo]--1\n" +
                                                                    "      ,[mn_desc]--2\n" +
                                                                    "	  ,CASE WHEN (mn_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [mn_estad]--3\n" +
                                                                    "	  ,[bd_cdgo]--4\n" +
                                                                    "      ,[bd_name]--5\n" +
                                                                    "      ,[bd_desc]--6\n" +
                                                                    "      ,[bd_estad]--7\n" +
                                                                    "  FROM ["+DB+"].[dbo].[motonave]\n" +
                                                                    "  INNER JOIN  ["+DB+"].[dbo].[base_datos] ON [mn_base_datos_cdgo]=[bd_cdgo] WHERE [mn_estad]=1;");
                resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexion.prepareStatement("SELECT  [mn_cdgo]--1\n" +
                                                                    "      ,[mn_desc]--2\n" +
                                                                    "	  ,CASE WHEN (mn_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [mn_estad]--3\n" +
                                                                    "	  ,[bd_cdgo]--4\n" +
                                                                    "      ,[bd_name]--5\n" +
                                                                    "      ,[bd_desc]--6\n" +
                                                                    "      ,[bd_estad]--7\n" +
                                                                    "  FROM ["+DB+"].[dbo].[motonave]\n" +
                                                                    "  INNER JOIN  ["+DB+"].[dbo].[base_datos] ON [mn_base_datos_cdgo]=[bd_cdgo] WHERE [mn_estad]=1 AND [mn_desc] like ?;");
                query.setString(1, "%"+valorConsulta+"%");
                resultSet= query.executeQuery();              
            }
            while(resultSet.next()){  
                Motonave Objeto = new Motonave(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                  BaseDatos baseDatos = new BaseDatos();
                        baseDatos.setCodigo(resultSet.getString(4));
                        baseDatos.setNombre(resultSet.getString(5));
                        baseDatos.setDescripcion(resultSet.getString(6));
                        baseDatos.setEstado(resultSet.getString(7));
                Objeto.setBaseDatos(baseDatos);
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las motonaves");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    /*public String buscar_nombre(String nombre) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[motonave] WHERE [mn_desc] like ?;");
            query.setString(1, nombre);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                return resultSet.getString(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las motonaves");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } */
    /*public String buscar_Id(String id) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[motonave] WHERE [mn_cdgo] =?;");
            query.setString(1, id);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                return resultSet.getString(2);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las motonaves");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } */
    public boolean validarExistencia(Motonave Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[motonave] WHERE [mn_cdgo] like ? AND [mn_base_datos_cdgo]=?;");
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
    /*public boolean validarExistenciaActualizar(Motonave Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[motonave] WHERE [mn_desc] like ? AND [mn_cdgo]<> ? AND [mn_base_datos_cdgo]=?;");
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
    public int actualizar(Motonave Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            Motonave MotonaveAnterior=buscarEspecifico(""+Objeto.getCodigo());
            String estado="";
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                estado="ACTIVO";
            }else{
                estado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement query= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[motonave] set [mn_desc]=?, [mn_estad]=? WHERE [mn_cdgo]=? AND [mn_base_datos_cdgo]=?");
            query.setString(1, Objeto.getDescripcion());
            query.setString(2, Objeto.getEstado());
            query.setString(3, Objeto.getCodigo());
            query.setString(4, Objeto.getBaseDatos().getCodigo());
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
                        "           ,'MOTONAVE'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre ',?,' Código: ',?,' Nombre: ',?,' Estado: ',?,"
                                            + "' actualizando la siguiente informacion a Código: ',?,' Nombre: ',?,' Estado: ',?,' BaseDatos: ',?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, MotonaveAnterior.getCodigo());
                Query_Auditoria.setString(6, "la motonave :");
                Query_Auditoria.setString(7, MotonaveAnterior.getCodigo());
                Query_Auditoria.setString(8, MotonaveAnterior.getDescripcion());
                Query_Auditoria.setString(9, MotonaveAnterior.getEstado());
                Query_Auditoria.setString(10, Objeto.getCodigo());
                Query_Auditoria.setString(11, Objeto.getDescripcion());
                Query_Auditoria.setString(12, estado);
                Query_Auditoria.setString(13, Objeto.getBaseDatos().getCodigo());
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

     //Consultas Motonave en Ccarga GP
    public ArrayList<Motonave> buscarMotonaveGP(String valor) throws SQLException{
        ArrayList<Motonave> listadoMotonave = new ArrayList();
        Conexion_DB_ccargaGP controlGP = new Conexion_DB_ccargaGP(tipoConexion);
        Connection conexionGP=null;
        conexionGP= controlGP.ConectarBaseDatos();
        String DB=controlGP.getBaseDeDatos();
        try{
            ResultSet resultSet;
            if(valor.equalsIgnoreCase("")){
                PreparedStatement query= conexionGP.prepareStatement("SELECT [mo_cdgo]\n" +
                                                "      ,[mo_nmbre]\n" +
                                                "      ,[mo_fcha_crcion]\n" +
                                                "      ,[mo_eslra]\n" +
                                                "      ,[mo_mtrcla]\n" +
                                                "      ,[mo_bndra]\n" +
                                                "      ,[mo_nmro_bdgas]\n" +
                                                "      ,[mo_nmro_gruas]\n" +
                                                "      ,[mo_cpcdad_gruas]\n" +
                                                "  FROM ["+DB+"].[dbo].[mtnve] ORDER BY mo_cdgo ASC");
                    resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexionGP.prepareStatement("SELECT [mo_cdgo]\n" +
                                                "      ,[mo_nmbre]\n" +
                                                "      ,[mo_fcha_crcion]\n" +
                                                "      ,[mo_eslra]\n" +
                                                "      ,[mo_mtrcla]\n" +
                                                "      ,[mo_bndra]\n" +
                                                "      ,[mo_nmro_bdgas]\n" +
                                                "      ,[mo_nmro_gruas]\n" +
                                                "      ,[mo_cpcdad_gruas]\n" +
                                                "  FROM ["+DB+"].[dbo].[mtnve] WHERE ([mo_cdgo] LIKE ? OR [mo_nmbre] LIKE ?) ORDER BY mo_nmbre ASC");
                query.setString(1, "%"+valor+"%");
                query.setString(2, "%"+valor+"%");
                resultSet= query.executeQuery();
            }
            while(resultSet.next()){ 
                Motonave Objeto = new Motonave(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado("1");
                Objeto.setBaseDatos(new BaseDatos("1"));
                listadoMotonave.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las motonaves en ccarga GP");
            sqlException.printStackTrace();
        } 
        controlGP.cerrarConexionBaseDatos();
        return listadoMotonave;
    } 
     public ArrayList<Motonave> buscarMotonaveOPP(String valor) throws SQLException{
        ArrayList<Motonave> listadoMotonave = new ArrayList();
        Conexion_DB_ccargaOPP controlOPP = new Conexion_DB_ccargaOPP(tipoConexion);
        Connection conexionOPP=null;
        conexionOPP= controlOPP.ConectarBaseDatos();
        String DB=controlOPP.getBaseDeDatos();
        try{
            ResultSet resultSet;
            if(valor.equalsIgnoreCase("")){
                PreparedStatement query= conexionOPP.prepareStatement("SELECT [mo_cdgo]\n" +
                                                "      ,[mo_nmbre]\n" +
                                                "      ,[mo_fcha_crcion]\n" +
                                                "      ,[mo_eslra]\n" +
                                                "      ,[mo_mtrcla]\n" +
                                                "      ,[mo_bndra]\n" +
                                                "      ,[mo_nmro_bdgas]\n" +
                                                "      ,[mo_nmro_gruas]\n" +
                                                "      ,[mo_cpcdad_gruas]\n" +
                                                "  FROM ["+DB+"].[dbo].[mtnve] ORDER BY mo_cdgo ASC");
                resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexionOPP.prepareStatement("SELECT [mo_cdgo]\n" +
                                                "      ,[mo_nmbre]\n" +
                                                "      ,[mo_fcha_crcion]\n" +
                                                "      ,[mo_eslra]\n" +
                                                "      ,[mo_mtrcla]\n" +
                                                "      ,[mo_bndra]\n" +
                                                "      ,[mo_nmro_bdgas]\n" +
                                                "      ,[mo_nmro_gruas]\n" +
                                                "      ,[mo_cpcdad_gruas]\n" +
                                                "  FROM ["+DB+"].[dbo].[mtnve] WHERE ([mo_cdgo] LIKE ? OR [mo_nmbre] LIKE ?) ORDER BY mo_nmbre ASC");
                query.setString(1, "%"+valor+"%");
                query.setString(2, "%"+valor+"%");
                resultSet= query.executeQuery();
            }
            while(resultSet.next()){ 
                Motonave Objeto = new Motonave(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado("1");
                Objeto.setBaseDatos(new BaseDatos("2"));
                listadoMotonave.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las motonaves en ccarga OPP");
            sqlException.printStackTrace();
        } 
        controlOPP.cerrarConexionBaseDatos();
        return listadoMotonave;
    } 
}
