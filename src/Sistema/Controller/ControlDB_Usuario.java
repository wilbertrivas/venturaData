package Sistema.Controller;
 
import ConnectionDB.Conexion_DB_costos_vg;
import Sistema.Model.Perfil;
import Sistema.Model.Permisos;
import Sistema.Model.Usuario;
import Sistema.View.EncriptarPassword;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ControlDB_Usuario {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    
    public                      ControlDB_Usuario(String tipoConexion) {
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    public ArrayList<Perfil>    cargarPerfil(){
        ArrayList<Perfil> listadoPerfilUsuario = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT TOP 1000 [prf_cdgo]\n" +
                                                        "      ,[prf_desc]\n" +
                                                        "      ,[prf_estad]\n" +
                                                        "  FROM ["+DB+"].[dbo].[perfil] WHERE [prf_estad]=1; ");
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                Perfil perfilUsuario = new Perfil(); 
                perfilUsuario.setCodigo(resultSetBuscar.getString(1));
                perfilUsuario.setDescripcion(resultSetBuscar.getString(2));
                perfilUsuario.setEstado(resultSetBuscar.getString(3));
                listadoPerfilUsuario.add(perfilUsuario);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los perfiles para los usuarios");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoPerfilUsuario;
    }
    

    public void cambiarLenguaje() throws FileNotFoundException, UnknownHostException, SocketException{
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryRegistrar= conexion.prepareStatement("USE ["+DB+"] ALTER LOGIN [sa] WITH DEFAULT_LANGUAGE = English ");
            queryRegistrar.execute();
        }
        catch (SQLException sqlException ){   
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
    }
    
    
    public int                  registrarUsuario(Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            EncriptarPassword cripto = new EncriptarPassword();
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryRegistrar= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[usuario] \n" +
                                                "           ([us_cdgo]--1\n" +
                                                "           ,[us_clave]--2\n" +
                                                "           ,[us_nombres]--3\n" +
                                                "           ,[us_apellidos]--4\n" +
                                                "           ,[us_perfil_cdgo]--5\n" +
                                                "           ,[us_correo]--6\n" +
                                                "           ,[us_estad]--7\n"+
                                                "           ,[us_fecha_clave]--8\n" +
                                                "           ,[us_sesion_bloqueada]--9\n" +
                                                "           ,[us_cant_intento]--10\n" +
                                                "           ,[us_cambiar_clave])--11\n" +
                                                "     VALUES \n" +
                                                "           (?,?,?,?,?,?,?,(SELECT CONVERT (DATETIME,(SELECT SYSDATETIME()))),?,?,?);");
            queryRegistrar.setString(1, us.getCodigo());
            queryRegistrar.setString(2, cripto.md5(us.getClave()));
            queryRegistrar.setString(3, us.getNombres());
            queryRegistrar.setString(4, us.getApellidos());
            queryRegistrar.setString(5, us.getPerfilUsuario().getCodigo());
            queryRegistrar.setString(6, us.getCorreo());
            queryRegistrar.setString(7, us.getEstado());
            queryRegistrar.setString(8, us.getSesionBloqueada());
            queryRegistrar.setInt(9, us.getCantidadIntento());
            queryRegistrar.setString(10, us.getCambiarClave());
            queryRegistrar.execute();
            result=1;
            if(result==1){
                result=0;
                String estadoU;
                if(us.getEstado().equalsIgnoreCase("1")){
                    estadoU="ACTIVO";
                }else{
                    estadoU="INACTIVO";
                }
                //Extraemos el nombre del Equipo y la IP        
                String namePc=new ControlDB_Config().getNamePC();
                String ipPc=new ControlDB_Config().getIpPc();
                String macPC=new ControlDB_Config().getMacAddress();
                PreparedStatement Query_AuditoriaInsert= conexion.prepareStatement(""
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
                                                "           ,'USUARIO'" +
                                                "           ,CONCAT (?,?,' Nombre: ',?,' ',?,' Estado: ',?));");
                Query_AuditoriaInsert.setString(1, us.getCodigo());
                Query_AuditoriaInsert.setString(2, namePc);
                Query_AuditoriaInsert.setString(3, ipPc);
                Query_AuditoriaInsert.setString(4, macPC);
                Query_AuditoriaInsert.setString(5, us.getCodigo());
                Query_AuditoriaInsert.setString(6, "Se registró un nuevo usuario en el sistema, con Código: ");
                Query_AuditoriaInsert.setString(7, us.getCodigo());
                Query_AuditoriaInsert.setString(8, us.getNombres());
                Query_AuditoriaInsert.setString(9, us.getApellidos());
                Query_AuditoriaInsert.setString(10, estadoU);
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
    public Usuario              validarUsuario(Usuario u){
        conexion=control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        Usuario user = null;
        if(conexion !=null){
            try{
                EncriptarPassword cripto = new EncriptarPassword();
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT  [us_cdgo],[us_clave],[us_nombres],[us_apellidos],[us_perfil_cdgo],[us_correo]"
                        + ",CASE WHEN (us_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS us_estad,[prf_cdgo],[prf_desc]"
                        + ",CASE WHEN (prf_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS prf_estad"
                        + " ,[pm_cdgo],[pm_desc]"
                        + "FROM ["+DB+"].[dbo].[usuario]"
                        + " INNER JOIN ["+DB+"].[dbo].[perfil] ON us_perfil_cdgo= prf_cdgo\n" +
                                                    "	INNER JOIN ["+DB+"].[dbo].perfil_permiso ON prf_cdgo=prfp_perfil_cdgo\n" +
                                                    "	INNER JOIN ["+DB+"].[dbo].permiso ON pm_cdgo=prfp_permiso_cdgo"+
                                                     " WHERE [us_cdgo]="+u.getCodigo()+" AND [us_clave]= '"+cripto.md5(u.getClave())+"';");
               // queryBuscar.setString(1, u.getCodigo());
                //queryBuscar.setString(2, cripto.md5(u.getClave()));
                ResultSet resultSetBuscar=queryBuscar.executeQuery();
                Usuario usuario = null;
                Perfil perfil =new Perfil();
                ArrayList<Permisos> listadoPermisos= new ArrayList<>();
                int contador=1;
                while(resultSetBuscar.next()){ 
                    if(cripto.md5(u.getClave()).equals(resultSetBuscar.getString(2))){
                        if(contador==1){
                            //cargamos el usuario
                            usuario = new Usuario();
                            usuario.setCodigo(resultSetBuscar.getString(1));
                            usuario.setClave(resultSetBuscar.getString(2));
                            usuario.setNombres(resultSetBuscar.getString(3));
                            usuario.setApellidos(resultSetBuscar.getString(4));
                            usuario.setCorreo(resultSetBuscar.getString(6));
                            usuario.setEstado(resultSetBuscar.getString(7));
                            //Cargamos el perfil
                            perfil.setCodigo(resultSetBuscar.getString(8));
                            perfil.setDescripcion(resultSetBuscar.getString(9));
                            perfil.setEstado(resultSetBuscar.getString(10));
                        }
                        //Cargamos los diferentes permisos
                        Permisos permisos = new Permisos();
                        permisos.setCodigo(resultSetBuscar.getString(11));
                        permisos.setDescripcion(resultSetBuscar.getString(12));
                        listadoPermisos.add(permisos);       
                    }
                }
                if(usuario != null){
                    perfil.setPermisos(listadoPermisos);
                    usuario.setPerfilUsuario(perfil);
                }
                return usuario;
            }catch (SQLException sqlException){
                JOptionPane.showMessageDialog(null, "Error al consultar el Usuario");
                sqlException.printStackTrace();
            } 
           control.cerrarConexionBaseDatos();
        }else{
            JOptionPane.showMessageDialog(null, "No hay conexión con el servidor, revise parámetros de red.");
        }
        return user;
        
        
        /*
        
        conexion=control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        Usuario user = null;
        if(conexion !=null){
            try{
                EncriptarPassword cripto = new EncriptarPassword();
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT \n" +
                                                            "   [us_cdgo]\n" +
                                                        "      ,[us_clave]\n" +
                                                        "      ,[us_nombres]\n" +
                                                        "      ,[us_apellidos]\n" +
                                                        "      ,[us_perfil_cdgo]\n" +
                                                        "      ,[us_correo]\n" +
                                                        "      ,CASE WHEN (us_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS us_estad"+
                                                    "          ,[prf_cdgo]\n" +
                                                        "      ,[prf_desc]\n" +
                                                     "       ,CASE WHEN (prf_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS prf_estad"+
                                                    "          ,[pm_cdgo]\n" +
                                                    "          ,[pm_desc]	   	\n" +
                                                    "  FROM ["+DB+"].[dbo].[usuario]\n" +
                                                    "	INNER JOIN ["+DB+"].[dbo].[perfil] ON us_perfil_cdgo= prf_cdgo\n" +
                                                    "	INNER JOIN ["+DB+"].[dbo].perfil_permiso ON prf_cdgo=prfp_perfil_cdgo\n" +
                                                    "	INNER JOIN ["+DB+"].[dbo].permiso ON pm_cdgo=prfp_permiso_cdgo"+
                                                     " WHERE [us_cdgo]=? AND [us_clave]= ?;"
                        
                       /* "SELECT " +
                                                            "   [us_cdgo] " +
                                                        "      ,[us_clave] " +
                                                        "      ,[us_nombres] " +
                                                        "      ,[us_apellidos] " +
                                                        "      ,[us_perfil_cdgo] " +
                                                        "      ,[us_correo] " +
                                                        "      ,CASE WHEN (us.us_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS us_estad "+
                                                        "          ,[prf_cdgo]" +
                                                        "      ,[prf_desc]" +
                                                        "       ,CASE WHEN (prf_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS prf_estad"+
                                                        "          ,[pm_cdgo]" +
                                                        "          ,[pm_desc]	   	"+
                                                  /*  "        ,[us_clave1] --13\n" +
                                                    "        ,[us_clave2] --14\n" +
                                                    "        ,[us_fecha_clave] --15\n" +
                                                    "        ,[us_sesion_bloqueada] --16\n" +
                                                    "        ,[us_cant_intento] --17\n" +
                                                    "        ,[us_cambiar_clave] --18\n" +*//*
                                                    "  FROM ["+DB+"].[dbo].[usuario] us \n" +
                                                    "	INNER JOIN ["+DB+"].[dbo].[perfil] ON us_perfil_cdgo= prf_cdgo\n" +
                                                    "	INNER JOIN ["+DB+"].[dbo].perfil_permiso ON prf_cdgo=prfp_perfil_cdgo\n" +
                                                    "	INNER JOIN ["+DB+"].[dbo].permiso ON pm_cdgo=prfp_permiso_cdgo"+
                                                     " WHERE [us_cdgo]=? AND [us_clave]= ?;"*//*);
                queryBuscar.setString(1, u.getCodigo());
                queryBuscar.setString(2, cripto.md5(u.getClave()));
                ResultSet resultSetBuscar=queryBuscar.executeQuery();
                Usuario usuario = null;
                Perfil perfil =new Perfil();
                ArrayList<Permisos> listadoPermisos= new ArrayList<>();
                int contador=1;
                while(resultSetBuscar.next()){          
                    if(cripto.md5(u.getClave()).equals(resultSetBuscar.getString(2))){
                        if(contador==1){
                            //cargamos el usuario
                            usuario = new Usuario();
                            usuario.setCodigo(resultSetBuscar.getString(1));
                            usuario.setClave(resultSetBuscar.getString(2));
                            usuario.setNombres(resultSetBuscar.getString(3));
                            usuario.setApellidos(resultSetBuscar.getString(4));
                            usuario.setCorreo(resultSetBuscar.getString(6));
                            usuario.setEstado(resultSetBuscar.getString(7));
                            
                            //Cargamos el perfil
                            perfil.setCodigo(resultSetBuscar.getString(8));
                            perfil.setDescripcion(resultSetBuscar.getString(9));
                            perfil.setEstado(resultSetBuscar.getString(10));
                            
                          /*  usuario.setClaveAnterior1(resultSetBuscar.getString(13));
                            usuario.setClaveAnterior2(resultSetBuscar.getString(14));
                            usuario.setFechaCambioClave(resultSetBuscar.getString(15));
                            usuario.setSesionBloqueada(resultSetBuscar.getString(16));
                            usuario.setCantidadIntento(Integer.parseInt(resultSetBuscar.getString(17)));
                            usuario.setCambiarClave(resultSetBuscar.getString(18));*/
                        //}
                        //Cargamos los diferentes permisos
                        /*Permisos permisos = new Permisos();
                        permisos.setCodigo(resultSetBuscar.getString(11));
                        permisos.setDescripcion(resultSetBuscar.getString(12));
                        listadoPermisos.add(permisos);       
                    }
                }
                if(usuario != null){
                    perfil.setPermisos(listadoPermisos);
                    usuario.setPerfilUsuario(perfil);
                }
                return usuario;
            }catch (SQLException sqlException){
                JOptionPane.showMessageDialog(null, "Error al consultar el Usuario");
                sqlException.printStackTrace();
            } 
           control.cerrarConexionBaseDatos();
        }else{
            JOptionPane.showMessageDialog(null, "No hay conexión con el servidor, revise parámetros de red.");
        }
        return user;*/
    }
    public boolean              validarUsuario_PorCodigo(Usuario u){
        boolean respuesta=false;
        conexion=control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        if(conexion !=null){
            try{
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT  [us_cdgo],[us_clave],[us_nombres],[us_apellidos],[us_perfil_cdgo],[us_correo]"
                        + ",CASE WHEN (us_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS us_estad,[prf_cdgo],[prf_desc]"
                        + ",CASE WHEN (prf_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS prf_estad"
                        + " ,[pm_cdgo],[pm_desc]"
                        + "FROM ["+DB+"].[dbo].[usuario]"
                        + " INNER JOIN ["+DB+"].[dbo].[perfil] ON us_perfil_cdgo= prf_cdgo\n" +
                                                    "	INNER JOIN ["+DB+"].[dbo].perfil_permiso ON prf_cdgo=prfp_perfil_cdgo\n" +
                                                    "	INNER JOIN ["+DB+"].[dbo].permiso ON pm_cdgo=prfp_permiso_cdgo"+
                                                     " WHERE [us_cdgo] LIKE ?;");
                queryBuscar.setString(1, u.getCodigo());
                ResultSet resultSetBuscar=queryBuscar.executeQuery();
                while(resultSetBuscar.next()){ 
                       respuesta=true; 
                }
            }catch (SQLException sqlException){
                JOptionPane.showMessageDialog(null, "Error al consultar el Usuario");
                sqlException.printStackTrace();
                respuesta=false;
            } 
           control.cerrarConexionBaseDatos();
        }else{
            JOptionPane.showMessageDialog(null, "No hay conexión con el servidor, revise parámetros de red.");
            respuesta=false;
        }
        return respuesta;
    }
    public boolean              validarUsuario_Bloqueado(Usuario u){
        boolean respuesta=false;
        conexion=control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        if(conexion !=null){
            try{
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT  [us_cdgo],[us_clave],[us_nombres],[us_apellidos],[us_perfil_cdgo],[us_correo]"
                        + ",CASE WHEN (us_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS us_estad,[prf_cdgo],[prf_desc]"
                        + ",CASE WHEN (prf_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS prf_estad"
                        + " ,[pm_cdgo],[pm_desc]"
                        + "FROM ["+DB+"].[dbo].[usuario]"
                        + " INNER JOIN ["+DB+"].[dbo].[perfil] ON us_perfil_cdgo= prf_cdgo\n" +
                                                    "	INNER JOIN ["+DB+"].[dbo].perfil_permiso ON prf_cdgo=prfp_perfil_cdgo\n" +
                                                    "	INNER JOIN ["+DB+"].[dbo].permiso ON pm_cdgo=prfp_permiso_cdgo"+
                                                     " WHERE [us_cdgo] LIKE ? AND [us_sesion_bloqueada]=1;");
                queryBuscar.setString(1, u.getCodigo());
                ResultSet resultSetBuscar=queryBuscar.executeQuery();
                while(resultSetBuscar.next()){ 
                       respuesta=true; 
                }
            }catch (SQLException sqlException){
                JOptionPane.showMessageDialog(null, "Error al consultar el Usuario");
                sqlException.printStackTrace();
                respuesta=false;
            } 
           control.cerrarConexionBaseDatos();
        }else{
            JOptionPane.showMessageDialog(null, "No hay conexión con el servidor, revise parámetros de red.");
            respuesta=false;
        }
        return respuesta;
    }
    public boolean              validarUsuario_CambiarClave(Usuario u){
        boolean respuesta=false;
        conexion=control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        if(conexion !=null){
            try{
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT  [us_cdgo],[us_clave],[us_nombres],[us_apellidos],[us_perfil_cdgo],[us_correo]"
                        + ",CASE WHEN (us_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS us_estad,[prf_cdgo],[prf_desc]"
                        + ",CASE WHEN (prf_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS prf_estad"
                        + " ,[pm_cdgo],[pm_desc]"
                        + "FROM ["+DB+"].[dbo].[usuario]"
                        + " INNER JOIN ["+DB+"].[dbo].[perfil] ON us_perfil_cdgo= prf_cdgo\n" +
                                                    "	INNER JOIN ["+DB+"].[dbo].perfil_permiso ON prf_cdgo=prfp_perfil_cdgo\n" +
                                                    "	INNER JOIN ["+DB+"].[dbo].permiso ON pm_cdgo=prfp_permiso_cdgo"+
                                                     " WHERE [us_cdgo] LIKE ? AND [us_cambiar_clave]=1;");
                queryBuscar.setString(1, u.getCodigo());
                ResultSet resultSetBuscar=queryBuscar.executeQuery();
                while(resultSetBuscar.next()){ 
                       respuesta=true; 
                }
            }catch (SQLException sqlException){
                JOptionPane.showMessageDialog(null, "Error al consultar el Usuario");
                sqlException.printStackTrace();
                respuesta=false;
            } 
           control.cerrarConexionBaseDatos();
        }else{
            JOptionPane.showMessageDialog(null, "No hay conexión con el servidor, revise parámetros de red.");
            respuesta=false;
        }
        return respuesta;
    }
    public boolean              validarUsuario_cantidadIntento(Usuario u){
        boolean respuesta=false;
        conexion=control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        if(conexion !=null){
            try{
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT  [us_cdgo],[us_clave],[us_nombres],[us_apellidos],[us_perfil_cdgo],[us_correo]"
                        + ",CASE WHEN (us_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS us_estad,[prf_cdgo],[prf_desc]"
                        + ",CASE WHEN (prf_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS prf_estad"
                        + " ,[pm_cdgo],[pm_desc]"
                        + "FROM ["+DB+"].[dbo].[usuario]"
                        + " INNER JOIN ["+DB+"].[dbo].[perfil] ON us_perfil_cdgo= prf_cdgo\n" +
                                                    "	INNER JOIN ["+DB+"].[dbo].perfil_permiso ON prf_cdgo=prfp_perfil_cdgo\n" +
                                                    "	INNER JOIN ["+DB+"].[dbo].permiso ON pm_cdgo=prfp_permiso_cdgo"+
                                                     " WHERE [us_cdgo] LIKE ? AND [us_cant_intento]>=4;");
                queryBuscar.setString(1, u.getCodigo());
                ResultSet resultSetBuscar=queryBuscar.executeQuery();
                while(resultSetBuscar.next()){ 
                       respuesta=true; 
                }
            }catch (SQLException sqlException){
                JOptionPane.showMessageDialog(null, "Error al consultar el Usuario");
                sqlException.printStackTrace();
                respuesta=false;
            } 
           control.cerrarConexionBaseDatos();
        }else{
            JOptionPane.showMessageDialog(null, "No hay conexión con el servidor, revise parámetros de red.");
            respuesta=false;
        }
        return respuesta;
    }
    public boolean              validarContraseña(String codigoUsuario, String clave){
        boolean retorno=false;
        conexion=control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        if(conexion !=null){
            try{
                EncriptarPassword cripto = new EncriptarPassword();
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT \n" +
                                                            "   [us_cdgo]\n" +
                                                        "      ,[us_clave]\n" +
                                                        "      ,[us_nombres]\n" +
                                                        "      ,[us_apellidos]\n" +
                                                        "      ,[us_perfil_cdgo]\n" +
                                                        "      ,[us_correo]\n" +
                                                     "  FROM ["+DB+"].[dbo].[usuario]\n " +
                                                    "	WHERE [us_cdgo]=? AND [us_clave]= ?;");
                queryBuscar.setString(1, codigoUsuario);
                queryBuscar.setString(2, cripto.md5(clave));
                ResultSet resultSetBuscar=queryBuscar.executeQuery();
                while(resultSetBuscar.next()){     
                    retorno=true;
                    /*if(cripto.md5(u.getClave()).equals(resultSetBuscar.getString(2))){
                        if(contador==1){
                            //cargamos el usuario
                            usuario = new Usuario();
                            usuario.setCodigo(resultSetBuscar.getString(1));
                            usuario.setClave(resultSetBuscar.getString(2));
                            usuario.setNombres(resultSetBuscar.getString(3));
                            usuario.setApellidos(resultSetBuscar.getString(4));
                            usuario.setCorreo(resultSetBuscar.getString(6));
                            usuario.setEstado(resultSetBuscar.getString(7));
                            //Cargamos el perfil
                            perfil.setCodigo(resultSetBuscar.getString(8));
                            perfil.setDescripcion(resultSetBuscar.getString(9));
                            perfil.setEstado(resultSetBuscar.getString(10));
                        }
                        //Cargamos los diferentes permisos
                        Permisos permisos = new Permisos();
                        permisos.setCodigo(resultSetBuscar.getString(11));
                        permisos.setDescripcion(resultSetBuscar.getString(12));
                        listadoPermisos.add(permisos);       
                    }*/
                }
                
                return retorno;
            }catch (SQLException sqlException){
                JOptionPane.showMessageDialog(null, "Error al consultar el Usuario");
                sqlException.printStackTrace();
            } 
           control.cerrarConexionBaseDatos();
        }else{
            JOptionPane.showMessageDialog(null, "No hay conexión con el servidor, revise parámetros de red.");
        }
        return retorno;
    }
    public int                  cambiarContraseñaConCorreo(Usuario usuario, String correoAnterior) throws FileNotFoundException, UnknownHostException, SocketException{
        EncriptarPassword cripto = new EncriptarPassword();
        int result=0;
        try{ 
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[usuario]\n" +
                                                                                "   SET  [us_fecha_clave]=(SELECT CONVERT (DATETIME,(SELECT SYSDATETIME())))\n" +
                                                                                "   ,[us_cambiar_clave]=0"+
                                                                                "   ,[us_clave] =?\n" +
                                                                                "   ,[us_clave1] =?\n" +
                                                                                "   ,[us_clave2] =?\n" +
                                                                                "   ,[us_correo] = ?\n" +
                                                                                " WHERE [us_cdgo] = ?;");
            queryActualizar.setString(1,cripto.md5(usuario.getClave()));
            queryActualizar.setString(2,usuario.getClaveAnterior1());
            queryActualizar.setString(3,usuario.getClaveAnterior2());
            queryActualizar.setString(4,usuario.getCorreo());
            queryActualizar.setString(5,usuario.getCodigo());
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
                                                "           ,'USUARIO',CONCAT('Se registró una nueva actualización en el sistema sobre el usuario de Código:',?,' Nombre: ',?,' Perfil: ',?,' Correo: ',?,' Estado: ',?,' actualizando la siguiente informacion a Correo: ',?,' y contraseña'));"); 
                Query_Auditoria.setString(1, usuario.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, usuario.getCodigo());
                Query_Auditoria.setString(6, usuario.getCodigo());
                Query_Auditoria.setString(7, usuario.getNombres()+" "+usuario.getApellidos());
                Query_Auditoria.setString(8, usuario.getPerfilUsuario().getDescripcion());
                Query_Auditoria.setString(9, correoAnterior);
                Query_Auditoria.setString(10, usuario.getEstado());
                Query_Auditoria.setString(11, usuario.getCorreo());
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
    public int                  cambiarCorreo(Usuario usuario, String correoAnterior) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{ 
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[usuario]\n" +
                                                                                "   SET [us_correo] = ?\n" +
                                                                                " WHERE [us_cdgo] = ?;");
            queryActualizar.setString(1,usuario.getCorreo());
            queryActualizar.setString(2,usuario.getCodigo());
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
                                                "           ,'USUARIO',CONCAT('Se registró una nueva actualización en el sistema sobre el usuario de Código:',?,' Nombre: ',?,' Perfil: ',?,' Correo: ',?,' Estado: ',?,' actualizando la siguiente informacion a Correo: ',?));"); 
                Query_Auditoria.setString(1, usuario.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, usuario.getCodigo());
                Query_Auditoria.setString(6, usuario.getCodigo());
                Query_Auditoria.setString(7, usuario.getNombres()+" "+usuario.getApellidos());
                Query_Auditoria.setString(8, usuario.getPerfilUsuario().getDescripcion());
                Query_Auditoria.setString(9, correoAnterior);
                Query_Auditoria.setString(10, usuario.getEstado());
                Query_Auditoria.setString(11, usuario.getCorreo());
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
    public int                  cambiarContraseñaSinCorreo(Usuario usuario) throws FileNotFoundException, UnknownHostException, SocketException{
        EncriptarPassword cripto = new EncriptarPassword();
        int result=0;
        try{ 
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            System.out.println("UPDATE ["+DB+"].[dbo].[usuario]\n" +
                                                                                "   SET  [us_fecha_clave]=(SELECT CONVERT (DATETIME,(SELECT SYSDATETIME())))\n" +
                                                                                "   ,[us_cambiar_clave]=0"+
                                                                                "   ,[us_clave] ='"+cripto.md5(usuario.getClave())+"'\n" +
                                                                                "    ,[us_clave1] =?'"+usuario.getClaveAnterior1()+"'\n" +
                                                                                "    ,[us_clave2] ='"+usuario.getClaveAnterior2()+"'\n" +
                                                                                " WHERE [us_cdgo] = '"+usuario.getCodigo()+"';");
            
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[usuario]\n" +
                                                                                "   SET  [us_fecha_clave]=(SELECT CONVERT (DATETIME,(SELECT SYSDATETIME())))\n" +
                                                                                "   ,[us_cambiar_clave]=0"+
                                                                                "   ,[us_clave] =?\n" +
                                                                                "    ,[us_clave1] =?\n" +
                                                                                "    ,[us_clave2] =?\n" +
                                                                                " WHERE [us_cdgo] = ?;");
            queryActualizar.setString(1,cripto.md5(usuario.getClave()));
            queryActualizar.setString(2,usuario.getClaveAnterior1());
            queryActualizar.setString(3,usuario.getClaveAnterior2());
            queryActualizar.setString(4,usuario.getCodigo());
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
                                                "           ,'USUARIO',CONCAT('Se realizó cambio de contraseña sobre el usuario Código:',?,' Nombre: ',?));"); 
                Query_Auditoria.setString(1, usuario.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, usuario.getCodigo());
                Query_Auditoria.setString(6, usuario.getCodigo());
                Query_Auditoria.setString(7, usuario.getNombres()+" "+usuario.getApellidos());
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
    public ArrayList<Usuario>   buscar(String valorConsulta) throws SQLException{
        ArrayList<Usuario> listadoObjetos = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            if(valorConsulta.equals("")){
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT [us_cdgo]--1\n" +
                                                                "      ,[us_clave]--2\n" +
                                                                "      ,[us_nombres]--3\n" +
                                                                "      ,[us_apellidos]--4\n" +
                                                                "      ,[us_perfil_cdgo]--5\n" +
                                                                "	  ,[prf_cdgo]--6\n" +
                                                                "      ,[prf_desc]--7\n" +
                                                                "      ,CASE WHEN ([prf_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [prf_estad]--8\n" +
                                                                "      ,[us_correo]--9\n" +
                                                                "      ,CASE WHEN ([us_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [us_estad]--10\n" +
                                                                "        ,[us_clave1] --11\n" +
                                                                "        ,[us_clave2] --12\n" +
                                                                "        ,[us_fecha_clave] --13\n" +
                                                                "        ,[us_sesion_bloqueada] --14\n" +
                                                                "        ,[us_cant_intento] --15\n" +
                                                                "        ,[us_cambiar_clave] --16\n" +
                                                                "  FROM ["+DB+"].[dbo].[usuario] \n" +
                                                                "       INNER JOIN ["+DB+"].[dbo].[perfil]  ON [prf_cdgo]=[us_perfil_cdgo];");
                resultSetBuscar=queryBuscar.executeQuery();
            }else{
                valorConsulta = "%"+valorConsulta+"%";
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT [us_cdgo]--1\n" +
                                                                "      ,[us_clave]--2\n" +
                                                                "      ,[us_nombres]--3\n" +
                                                                "      ,[us_apellidos]--4\n" +
                                                                "      ,[us_perfil_cdgo]--5\n" +
                                                                "	  ,[prf_cdgo]--6\n" +
                                                                "      ,[prf_desc]--7\n" +
                                                                "      ,CASE WHEN ([prf_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [prf_estad]--8\n" +
                                                                "      ,[us_correo]--9\n" +
                                                                "      ,CASE WHEN ([us_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [us_estad]--10\n" +
                                                                "        ,[us_clave1] --11\n" +
                                                                "        ,[us_clave2] --12\n" +
                                                                "        ,[us_fecha_clave] --13\n" +
                                                                "        ,[us_sesion_bloqueada] --14\n" +
                                                                "        ,[us_cant_intento] --15\n" +
                                                                "        ,[us_cambiar_clave] --16\n" +
                                                                "  FROM ["+DB+"].[dbo].[usuario] \n" +
                                       "   INNER JOIN ["+DB+"].[dbo].[perfil]  ON [prf_cdgo]=[us_perfil_cdgo] "
                                        + " WHERE ([us_cdgo] LIKE ? OR [us_nombres] LIKE ? OR [us_apellidos] LIKE ?);");
                queryBuscar.setString(1, valorConsulta);
                queryBuscar.setString(2, valorConsulta);
                queryBuscar.setString(3, valorConsulta);
                resultSetBuscar=queryBuscar.executeQuery();
            }
            while(resultSetBuscar.next()){ 
                Usuario Objeto = new Usuario(); 
                Objeto.setCodigo(resultSetBuscar.getString(1));
                Objeto.setClave(resultSetBuscar.getString(2));
                Objeto.setNombres(resultSetBuscar.getString(3));
                Objeto.setApellidos(resultSetBuscar.getString(4));
                Objeto.setPerfilUsuario(new Perfil(resultSetBuscar.getString(6),resultSetBuscar.getString(7),resultSetBuscar.getString(8)));
                Objeto.setCorreo(resultSetBuscar.getString(9));
                Objeto.setEstado(resultSetBuscar.getString(10));
                Objeto.setClaveAnterior1(resultSetBuscar.getString(11));
                Objeto.setClaveAnterior2(resultSetBuscar.getString(12));
                Objeto.setFechaCambioClave(resultSetBuscar.getString(13));
                Objeto.setSesionBloqueada(resultSetBuscar.getString(14));
                Objeto.setCantidadIntento(Integer.parseInt(resultSetBuscar.getString(15)));
                Objeto.setCambiarClave(resultSetBuscar.getString(16));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los usuarios");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }   
    public ArrayList<Usuario>   buscarUsuario_Permiso_AsignarEquipos() throws SQLException{
        ArrayList<Usuario> listadoObjetos=null;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
         
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT  [us_cdgo]--1\n" +
                                                                "		,[us_nombres]--2\n" +
                                                                "		,[us_apellidos]--3\n" +
                                                                "		,[us_perfil_cdgo]--4\n" +
                                                                "		  ,[prf_cdgo]--5\n" +
                                                                "		  ,[prf_desc]--6\n" +
                                                                "		  ,CASE WHEN ([prf_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [prf_estad]--7\n" +
                                                                "      ,[us_correo]--8\n" +
                                                                "	  ,CASE WHEN ([us_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [us_estad]--9\n" +
                                                                "      --,[prfp_perfil_cdgo]\n" +
                                                                "	  --,[prfp_permiso_cdgo]\n" +
                                                                "	  --,[pm_cdgo]\n" +
                                                                "      --,[pm_desc]\n" +
                                                                "  FROM ["+DB+"].[dbo].[usuario]\n" +
                                                                "	  INNER JOIN ["+DB+"].[dbo].[perfil] ON [us_perfil_cdgo]=[prf_cdgo]\n" +
                                                                "	  INNER JOIN ["+DB+"].[dbo].[perfil_permiso] ON [prfp_perfil_cdgo]=[us_perfil_cdgo]\n" +
                                                                "	  INNER JOIN ["+DB+"].[dbo].[permiso] ON [prfp_permiso_cdgo]=[pm_cdgo]\n" +
                                                                "  WHERE [pm_desc] LIKE 'ASIGNACION_EQUIPOS_REGISTRAR';");
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            boolean validador= true;
            while(resultSetBuscar.next()){ 
                if(validador){
                    listadoObjetos = new ArrayList();
                    validador=false;
                }
                Usuario Objeto = new Usuario(); 
                Objeto.setCodigo(resultSetBuscar.getString(1));
                Objeto.setNombres(resultSetBuscar.getString(2));
                Objeto.setApellidos(resultSetBuscar.getString(3));
                Objeto.setPerfilUsuario(new Perfil(resultSetBuscar.getString(5),resultSetBuscar.getString(6),resultSetBuscar.getString(7)));
                Objeto.setCorreo(resultSetBuscar.getString(8));
                Objeto.setEstado(resultSetBuscar.getString(9));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los usuarios");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }
    public Usuario              buscarUsuarioEspecifico(String codigoUsuario) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        Usuario Objeto = new Usuario(); 
        try{
            ResultSet resultSetBuscar;
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT [us_cdgo]--1\n" +
                                                                "      ,[us_clave]--2\n" +
                                                                "      ,[us_nombres]--3\n" +
                                                                "      ,[us_apellidos]--4\n" +
                                                                "      ,[us_perfil_cdgo]--5\n" +
                                                                "	  ,[prf_cdgo]--6\n" +
                                                                "      ,[prf_desc]--7\n" +
                                                                "      ,CASE WHEN ([prf_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [prf_estad]--8\n" +
                                                                "      ,[us_correo]--9\n" +
                                                                "      ,CASE WHEN ([us_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [us_estad]--10\n" +
                                                                "        ,[us_clave1] --11\n" +
                                                                "        ,[us_clave2] --12\n" +
                                                                "        ,[us_fecha_clave] --13\n" +
                                                                "        ,[us_sesion_bloqueada] --14\n" +
                                                                "        ,[us_cant_intento] --15\n" +
                                                                "        ,[us_cambiar_clave] --16\n" +
                                                                "  FROM ["+DB+"].[dbo].[usuario] \n" +
                                                                "       INNER JOIN ["+DB+"].[dbo].[perfil]  ON [prf_cdgo]=[us_perfil_cdgo] "
                                                                    + " WHERE [us_cdgo] = ?;");
            queryBuscar.setString(1, codigoUsuario);
            resultSetBuscar=queryBuscar.executeQuery(); 
            while(resultSetBuscar.next()){ 
                Objeto.setCodigo(resultSetBuscar.getString(1));
                Objeto.setClave(resultSetBuscar.getString(2));
                Objeto.setNombres(resultSetBuscar.getString(3));
                Objeto.setApellidos(resultSetBuscar.getString(4));
                Objeto.setPerfilUsuario(new Perfil(resultSetBuscar.getString(6),resultSetBuscar.getString(7),resultSetBuscar.getString(8)));
                Objeto.setCorreo(resultSetBuscar.getString(9));
                Objeto.setEstado(resultSetBuscar.getString(10));
                Objeto.setClaveAnterior1(resultSetBuscar.getString(11));
                Objeto.setClaveAnterior2(resultSetBuscar.getString(12));
                Objeto.setFechaCambioClave(resultSetBuscar.getString(13));
                Objeto.setSesionBloqueada(resultSetBuscar.getString(14));
                Objeto.setCantidadIntento(Integer.parseInt(resultSetBuscar.getString(15)));
                Objeto.setCambiarClave(resultSetBuscar.getString(16));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los usuarios");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return Objeto;
    }
    public int                  actualizarConPassword(Usuario usuarioActualizado, Usuario usuarioQuienRegistraMovimiento) throws FileNotFoundException, UnknownHostException, SocketException{
        EncriptarPassword cripto = new EncriptarPassword();
        int result=0;
        try{
            Usuario usuarioAnterior =buscarUsuarioEspecifico(usuarioActualizado.getCodigo());
            String valorEstado="";
            
            if(usuarioActualizado.getEstado().equalsIgnoreCase("1")){
                valorEstado="ACTIVO";
            }else{
                valorEstado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[usuario]\n" +
                                                                                "  SET  [us_clave]=?" +
                                                                            "           ,[us_nombres]=?" +
                                                                            "           ,[us_apellidos]=?" +
                                                                            "           ,[us_perfil_cdgo]=?" +
                                                                            "           ,[us_correo]=?" +
                                                                            "           ,[us_estad]=?"+
                                                                            "           ,[us_clave1]=?"+
                                                                            "           ,[us_clave2]=?"+
                                                                            "           ,[us_fecha_clave]=(SELECT CONVERT (DATETIME,(SELECT SYSDATETIME())))" +
                                                                            "           ,[us_sesion_bloqueada]=?" +
                                                                            "           ,[us_cant_intento]=?" +
                                                                            "           ,[us_cambiar_clave]=? " +
                                                                            "       WHERE [us_cdgo] = ?;");
            queryActualizar.setString(1,cripto.md5(usuarioActualizado.getClave()));
            queryActualizar.setString(2,usuarioActualizado.getNombres());
            queryActualizar.setString(3,usuarioActualizado.getApellidos());
            queryActualizar.setString(4,usuarioActualizado.getPerfilUsuario().getCodigo());
            queryActualizar.setString(5,usuarioActualizado.getCorreo());
            queryActualizar.setString(6,usuarioActualizado.getEstado());
            queryActualizar.setString(7,usuarioActualizado.getClaveAnterior1());
            queryActualizar.setString(8,usuarioActualizado.getClaveAnterior2());
            queryActualizar.setString(9,usuarioActualizado.getSesionBloqueada());
            queryActualizar.setString(10, "0");//Se coloca cantidad de intento en cero porque está cambiando al contraseña
            queryActualizar.setString(11,usuarioActualizado.getCambiarClave());
            queryActualizar.setString(12,usuarioActualizado.getCodigo());
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
                                                "           ,'USUARIO',CONCAT('Se registró una nueva actualización en el sistema sobre el usuario de Código:',?,' Nombre: ',?,' Perfil: ',?,' Correo: ',?,' Estado: ',?,' actualizando la siguiente informacion a Código:',?,' Nombre: ',?,' Perfil: ',?,' Correo: ',?,' Estado: ',?));"); 
                Query_Auditoria.setString(1, usuarioQuienRegistraMovimiento.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, usuarioAnterior.getCodigo());
                Query_Auditoria.setString(6, usuarioAnterior.getCodigo());
                Query_Auditoria.setString(7, usuarioAnterior.getNombres()+" "+usuarioAnterior.getApellidos());
                Query_Auditoria.setString(8, usuarioAnterior.getPerfilUsuario().getDescripcion());
                Query_Auditoria.setString(9, usuarioAnterior.getCorreo());
                Query_Auditoria.setString(10, usuarioAnterior.getEstado());
                Query_Auditoria.setString(11, usuarioActualizado.getCodigo());
                Query_Auditoria.setString(12, usuarioActualizado.getNombres()+" "+usuarioAnterior.getApellidos());
                Query_Auditoria.setString(13, usuarioActualizado.getPerfilUsuario().getDescripcion());
                Query_Auditoria.setString(14, usuarioActualizado.getCorreo());
                Query_Auditoria.setString(15, valorEstado);
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
    public int                  actualizarSinPassword(Usuario usuarioActualizado, Usuario usuarioQuienRegistraMovimiento) throws FileNotFoundException, UnknownHostException, SocketException{
        //conexion= control.ConectarBaseDatos();
        int result=0;
        try{
            Usuario usuarioAnterior =buscarUsuarioEspecifico(usuarioActualizado.getCodigo());
            String valorEstado="";
            
            if(usuarioActualizado.getEstado().equalsIgnoreCase("1")){
                valorEstado="ACTIVO";
            }else{
                valorEstado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[usuario]\n" +
                                                                                "   SET [us_nombres] = ?\n" +
                                                                                "      ,[us_apellidos] = ?\n" +
                                                                                "      ,[us_perfil_cdgo] = ?\n" +
                                                                                "      ,[us_correo] = ?\n" +
                                                                                "      ,[us_estad] = ?"
                                                                              + "      ,[us_sesion_bloqueada]=? \n" +
                                                                                "      ,[us_cant_intento]=? \n" +
                                                                                "      ,[us_cambiar_clave]=? \n" +
                                                                                " WHERE [us_cdgo] = ?;");
            queryActualizar.setString(1,usuarioActualizado.getNombres());
            queryActualizar.setString(2,usuarioActualizado.getApellidos());
            queryActualizar.setString(3,usuarioActualizado.getPerfilUsuario().getCodigo());
            queryActualizar.setString(4,usuarioActualizado.getCorreo());
            queryActualizar.setString(5,usuarioActualizado.getEstado());
            queryActualizar.setString(6,usuarioActualizado.getSesionBloqueada());
            queryActualizar.setInt(7, usuarioActualizado.getCantidadIntento());
            queryActualizar.setString(8,usuarioActualizado.getCambiarClave());
            queryActualizar.setString(9,usuarioActualizado.getCodigo());
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
                                                "           ,'USUARIO',CONCAT('Se registró una nueva actualización en el sistema sobre el usuario de Código:',?,' Nombre: ',?,' Perfil: ',?,' Correo: ',?,' Estado: ',?,' actualizando la siguiente informacion a Código:',?,' Nombre: ',?,' Perfil: ',?,' Correo: ',?,' Estado: ',?));"); 
                Query_Auditoria.setString(1, usuarioQuienRegistraMovimiento.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, usuarioAnterior.getCodigo());
                Query_Auditoria.setString(6, usuarioAnterior.getCodigo());
                Query_Auditoria.setString(7, usuarioAnterior.getNombres()+" "+usuarioAnterior.getApellidos());
                Query_Auditoria.setString(8, usuarioAnterior.getPerfilUsuario().getDescripcion());
                Query_Auditoria.setString(9, usuarioAnterior.getCorreo());
                Query_Auditoria.setString(10, usuarioAnterior.getEstado());
                Query_Auditoria.setString(11, usuarioActualizado.getCodigo());
                Query_Auditoria.setString(12, usuarioActualizado.getNombres()+" "+usuarioAnterior.getApellidos());
                Query_Auditoria.setString(13, usuarioActualizado.getPerfilUsuario().getDescripcion());
                Query_Auditoria.setString(14, usuarioActualizado.getCorreo());
                Query_Auditoria.setString(15, valorEstado);
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
    public ArrayList<Usuario>   buscarActivos() throws SQLException{
        ArrayList<Usuario> listadoObjetos = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT [us_cdgo]\n" +
                                                            "      ,[us_clave]\n" +
                                                            "      ,[us_nombres]\n" +
                                                            "      ,[us_apellidos]\n" +
                                                            "      ,[us_perfil_cdgo]\n" +
                                                            "	  ,[prf_cdgo]\n" +
                                                            "      ,[prf_desc]\n" +
                                                            "      ,CASE WHEN ([prf_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [prf_estad]\n" +
                                                            "      ,[us_correo]\n" +
                                                            "      ,CASE WHEN ([us_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [us_estad]\n" +
                                                            "  FROM ["+DB+"].[dbo].[usuario] \n" +
                                                            "       INNER JOIN ["+DB+"].[dbo].[perfil]  ON [prf_cdgo]=[us_perfil_cdgo]"
                                                                    + " WHERE [us_estad]=1;");
            resultSetBuscar=queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                Usuario Objeto = new Usuario(); 
                Objeto.setCodigo(resultSetBuscar.getString(1));
                Objeto.setClave(resultSetBuscar.getString(2));
                Objeto.setNombres(resultSetBuscar.getString(3));
                Objeto.setApellidos(resultSetBuscar.getString(4));
                Objeto.setPerfilUsuario(new Perfil(resultSetBuscar.getString(6),resultSetBuscar.getString(7),resultSetBuscar.getString(8)));
                Objeto.setCorreo(resultSetBuscar.getString(9));
                Objeto.setEstado(resultSetBuscar.getString(10));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los usuarios");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }
    public boolean              validarCambioContraseña(){
        //1= Debe cambiar la contraseña, porque ha trascurrido mas de 60 días desde el ultimo cambio
        //0= la contraseña sigue activa porque no ha pasado 60 días
        String query="  SELECT   CASE WHEN (DATEDIFF(DAY, '2021-04-28'/*Fecha cambioPassword*/,GETDATE()/* '2021-04-28'Actual SQL SERVER*/)>= 90) THEN 1 ELSE 0 END;";
        return true;
    }
    public  int                 ingresoContraseñaIncorrecta(Usuario us){
        int result=0;
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[usuario] SET\n" +
                                                                        "		[us_cant_intento]=(CASE WHEN ([us_cant_intento] <= 4) THEN [us_cant_intento]+1 ELSE [us_cant_intento] END),\n" +
                                                                        "		[us_sesion_bloqueada]=(CASE WHEN ([us_cant_intento] >= 4 ) THEN 1 ELSE 0 END)\n" +
                                                                        "WHERE  us_cdgo like ?;");
            queryActualizar.setString(1,us.getCodigo());
            queryActualizar.execute();
            result=1; 
        }
        catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "ERROR al aumentar el intento");
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    }
    public  void                 resetIntentos(Usuario us){
        int result=0;
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[usuario] SET [us_cant_intento]=0 WHERE  us_cdgo LIKE ?;");
            queryActualizar.setString(1,us.getCodigo());
            queryActualizar.execute();
            //result=1; 
        }
        catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "ERROR al aumentar el intento");
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
       // return result;
    }
    public  int                 consultarCantidadIntento(Usuario us) throws SQLException{
        int result=0;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT [us_cant_intento] FROM ["+DB+"].[dbo].[usuario] WHERE [us_cdgo] LIKE ? ;");
            queryBuscar.setString(1, us.getCodigo());
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                result=resultSetBuscar.getInt(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los intentos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return result;
    } 
    public  int                 validarCambioContraseña(Usuario us) throws SQLException{
        int result=0;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT  \n" +
                                                                    "		CASE WHEN (DATEDIFF(DAY, [us_fecha_clave] ,GETDATE())>= 90) THEN 1 ELSE 0 END\n" +
                                                                    "	FROM ["+DB+"].[dbo].[usuario] where us_cdgo LIKE ?;");
            queryBuscar.setString(1, us.getCodigo());
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                result=resultSetBuscar.getInt(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los intentos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return result;
    } 
    public boolean validarComplejidadContraseña(String clave){
        boolean numero=false;
        boolean mayuscula=false;
        boolean caracterEspecial=false;
        boolean miniscula=false;
        boolean espacio=false;
        int logitudClave=0;
       // int Arroba = 0;                     // solo la arroba -.-!
         for (int x = 0; x < clave.length(); x++) {
            if ((clave.charAt(x) >= 48 && clave.charAt(x) <= 57) //numeros
                    || (clave.charAt(x) >= 65 && clave.charAt(x) <= 90) //mayusculas
                    || (clave.charAt(x) >= 33 && clave.charAt(x) <= 47) //signos
                    || (clave.charAt(x) >= 97 && clave.charAt(x) <= 122)) {  //minusculas
            } 
            if (clave.charAt(x) >= 48 && clave.charAt(x) <= 57) { // validamos que exista un número
                numero=true;
            }
            if (clave.charAt(x) >= 65 && clave.charAt(x) <= 90) { // Valida que exista una mayuscula
                mayuscula=true;
            }
            if (clave.charAt(x) >= 33 && clave.charAt(x) <= 47) { // valida que exista un caracter especial
                caracterEspecial=true;
            }
            if (clave.charAt(x) >= 97 && clave.charAt(x) <= 122) { // valida que exista una letra minuscula
                miniscula=true;
            }
            if(clave.charAt(x) == 32){
                espacio=true;
            }
            logitudClave += 1;
        } 
        boolean validar= false;
        boolean retorno=false;
        String mensaje="";
        if(!numero){
            mensaje += " un número,";
            validar= true;
        }
        if(!mayuscula){
            mensaje += " una mayúscula,";
            validar= true;
        }
        if(!caracterEspecial){
            mensaje += " un carácter especial,";
            validar= true;
        }
        if(!miniscula){
            mensaje += " una minúsculas,";
            validar= true;
        }
        if(logitudClave < 8){ 
           JOptionPane.showMessageDialog(null, "La contraseña debe de tener una longitud entre 8 a 15 caracteres", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
           retorno=false;
        }else{
            if(espacio){
                JOptionPane.showMessageDialog(null, "La contraseña no puede tener espacios", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                retorno=false;
            }else{
                if(validar){
                    JOptionPane.showMessageDialog(null, "La contraseña debe de tener al menos: "+mensaje, "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                    retorno=false;
                }else{
                    retorno=true;
                }
            }
        }
        return retorno; 
    }
    
}
