package Sistema.Controller;
 
import ConnectionDB2.Conexion_DB_costos_vg;
import Sistema.Model.Perfil;
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

public class ControlDB_Perfil {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private String tipoConexion;

    public ControlDB_Perfil(String tipoConexion) {
        control = new Conexion_DB_costos_vg(tipoConexion);
        this.tipoConexion= tipoConexion;
    }
    public int registrar(Perfil prf,Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            String estado;
            if(prf.getEstado().equalsIgnoreCase("1")){
                estado="ACTIVO";
            }else{
                estado="INACTIVO";
            }
            PreparedStatement queryRegistrar= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[perfil] ([prf_cdgo],[prf_desc],[prf_estad]) VALUES ((SELECT (CASE WHEN (MAX([prf_cdgo]) IS NULL)\n" +
                                                                                                    " THEN 1\n" +
                                                                                                    " ELSE (MAX([prf_cdgo])+1) END)AS [prf_cdgo]\n" +
                                                                                " FROM ["+DB+"].[dbo].[perfil]),?,?);");
            queryRegistrar.setString(1, prf.getDescripcion());
            queryRegistrar.setString(2, prf.getEstado());
            queryRegistrar.execute();
            result=1;
            if(result==1){
                result=0;
                //Sacamos el ultimo valor
                PreparedStatement queryMaximo= conexion.prepareStatement("SELECT MAX(prf_cdgo) FROM ["+DB+"].[dbo].[perfil];");
                ResultSet resultSetMaximo= queryMaximo.executeQuery();
                while(resultSetMaximo.next()){ 
                    if(resultSetMaximo.getString(1) != null){
                        prf.setCodigo(resultSetMaximo.getString(1));
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
                        "           ,?"+
                        "           ,CONCAT (?,?,' Nombre: ',?,' Estado: ',?));");
                Query_AuditoriaInsert.setString(1, us.getCodigo());
                Query_AuditoriaInsert.setString(2, namePc);
                Query_AuditoriaInsert.setString(3, ipPc);
                Query_AuditoriaInsert.setString(4, macPC);
                Query_AuditoriaInsert.setString(5, prf.getCodigo());
                Query_AuditoriaInsert.setString(6, "PERFIL");
                Query_AuditoriaInsert.setString(7, "Se registró un nuevo perfil en el sistema, con Código: ");
                Query_AuditoriaInsert.setString(8, prf.getCodigo());
                Query_AuditoriaInsert.setString(9, prf.getDescripcion());
                Query_AuditoriaInsert.setString(10, estado);
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
    public ArrayList<Perfil> buscar(String valorConsulta) throws SQLException{
        ArrayList<Perfil> listadoPerfilUsuario = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            if(valorConsulta.equals("")){
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT prf_cdgo, prf_desc, CASE WHEN (prf_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS prf_estad FROM ["+DB+"].[dbo].[perfil];");
                resultSetBuscar=queryBuscar.executeQuery();
            }else{
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT prf_cdgo, prf_desc, CASE WHEN (prf_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS prf_estad FROM ["+DB+"].[dbo].[perfil] WHERE [prf_desc] like ?;");
                queryBuscar.setString(1, "%"+valorConsulta+"%");
                resultSetBuscar=queryBuscar.executeQuery();
            }
            while(resultSetBuscar.next()){ 
                Perfil prf = new Perfil(); 
                prf.setCodigo(resultSetBuscar.getString(1));
                prf.setDescripcion(resultSetBuscar.getString(2));
                prf.setEstado(resultSetBuscar.getString(3));
                listadoPerfilUsuario.add(prf);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar el perfil");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoPerfilUsuario;
    }
    public Perfil buscarPerfilEspecifico(String cdgoPerfil) throws SQLException{
        Perfil prf=null;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT prf_cdgo, prf_desc, CASE WHEN (prf_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS prf_estad FROM ["+DB+"].[dbo].[perfil] WHERE [prf_cdgo] like ?;");
            queryBuscar.setString(1, cdgoPerfil);
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                prf = new Perfil(); 
                prf.setCodigo(resultSetBuscar.getString(1));
                prf.setDescripcion(resultSetBuscar.getString(2));
                prf.setEstado(resultSetBuscar.getString(3));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar el perfil");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return prf;
    }
    public ArrayList<Perfil> buscarActivos() throws SQLException{
       ArrayList<Perfil> listadoPerfilUsuario = new ArrayList();
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT prf_cdgo, prf_desc, CASE WHEN (prf_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS prf_estad FROM ["+DB+"].[dbo].[perfil] WHERE [prf_estad]=1;");
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                Perfil prf = new Perfil(); 
                prf.setCodigo(resultSetBuscar.getString(1));
                prf.setDescripcion(resultSetBuscar.getString(2));
                prf.setEstado(resultSetBuscar.getString(3));
                listadoPerfilUsuario.add(prf);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los perfiles activos para los diferentes usuarios");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoPerfilUsuario;
    }     
    public String buscar_nombre(String valor) throws SQLException{
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[perfil] WHERE [prf_desc] like ?;");
            queryBuscar.setString(1, valor);
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                return resultSetBuscar.getString(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los perfiles de los usuarios");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    }
    public String buscar_Id(String id) throws SQLException{
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[perfil] WHERE [prf_cdgo] =?;");
            queryBuscar.setString(1, id);
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                return resultSetBuscar.getString(2);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los perfiles de los usuarios");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } 
    public boolean validarExistencia(Perfil prf){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[perfil] WHERE [prf_desc] like ?;");
            queryBuscar.setString(1, prf.getDescripcion());
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
    public boolean validarExistenciaActualizar(Perfil prf){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[perfil] WHERE [prf_desc] like ? AND [prf_cdgo]<> ?;");
            queryBuscar.setString(1, prf.getDescripcion());
            queryBuscar.setString(2, prf.getCodigo());
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
    public int actualizar(Perfil prf, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        //conexion= control.ConectarBaseDatos();
        int result=0;
        try{
            Perfil perfilAnterior=buscarPerfilEspecifico(prf.getCodigo());
            String valorEstado="";
            if(prf.getEstado().equalsIgnoreCase("1")){
                valorEstado="ACTIVO";
            }else{
                valorEstado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[perfil] set [prf_desc]=?, [prf_estad]=? WHERE [prf_cdgo]=?;");
            queryActualizar.setString(1,prf.getDescripcion());
            queryActualizar.setString(2,prf.getEstado());
            queryActualizar.setString(3,prf.getCodigo());
            queryActualizar.execute();
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
                        "           ,?"+
                        ",CONCAT('Se registró una nueva actualización en el sistema sobre el perfil de Código:',?,' Nombre: ',?,' Estado: ',?,' actualizando la siguiente informacion a Código:',?,' Nombre: ',?,' Estado: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, prf.getCodigo());
                Query_Auditoria.setString(6, "PERFIL");
                Query_Auditoria.setString(7, perfilAnterior.getCodigo());
                Query_Auditoria.setString(8, perfilAnterior.getDescripcion());
                Query_Auditoria.setString(9, perfilAnterior.getEstado());
                Query_Auditoria.setString(10, prf.getCodigo());
                Query_Auditoria.setString(11, prf.getDescripcion());
                Query_Auditoria.setString(12, valorEstado);
                Query_Auditoria.execute();
                result=1;
            }
        }
        catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "ERROR al querer actualizar los datos");
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    } 
    public int actualizarPermisosPerfil(Perfil prf, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ControlDB_Permiso controlDB_Permiso = new ControlDB_Permiso(tipoConexion);
            PreparedStatement QeurydeletePermisosPerfilActual= conexion.prepareStatement("DELETE FROM  ["+DB+"].[dbo].[perfil_permiso] WHERE [prfp_perfil_cdgo]=?;");
            QeurydeletePermisosPerfilActual.setString(1, prf.getCodigo());
            QeurydeletePermisosPerfilActual.execute(); 
             String permisosActualizados="->";
            for(int i=0; i< prf.getPermisos().size(); i++){
                String NuevoPermiso=controlDB_Permiso.buscar_nombre(prf.getPermisos().get(i).getDescripcion());
                permisosActualizados +="-"+NuevoPermiso;
                PreparedStatement queryRegistrar= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[perfil_permiso]([prfp_permiso_cdgo],[prfp_perfil_cdgo])VALUES(?,?);");
                queryRegistrar.setString(1, NuevoPermiso);
                queryRegistrar.setString(2, prf.getCodigo());
                queryRegistrar.execute();
                result=1;
            } 
            permisosActualizados += "<-";
            if(result==1){
                result=0;
                //Extraemos el nombre del Equipo y la IP        
                String namePc=new ControlDB_Config().getNamePC();
                String ipPc=new ControlDB_Config().getIpPc();
                String macPC=new ControlDB_Config().getMacAddress();
                for(int i=0; i< prf.getPermisos().size(); i++){
                    controlDB_Permiso.buscar_nombre(prf.getPermisos().get(i).getDescripcion());
                }
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
                    "           ,?"+
                    ",CONCAT('Se registró una nueva actualización en el sistema sobre los permisos del perfil de Código:',?,' Nombre: ',?,' dandole los siguientes permisos "+permisosActualizados+"'));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, prf.getCodigo());
                Query_Auditoria.setString(6, "PERFIL");
                Query_Auditoria.setString(7, prf.getCodigo());
                Query_Auditoria.setString(8, prf.getDescripcion());
                Query_Auditoria.execute();
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
}
