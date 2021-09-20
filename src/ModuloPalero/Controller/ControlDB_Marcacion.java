package ModuloPalero.Controller;

import ConnectionDB.Conexion_DB_costos_vg;
import Catalogo.Model.Compañia;
import Catalogo.Model.Equipo;
import Catalogo.Model.TipoEquipo;
import ModuloPalero.Model.MarcacionArchivo;
import ModuloPalero.Model.MarcacionPersona;
import ModuloPalero.Model.MvtoCarbon_ListadoEquipos_LiquidacionPaleros;
import ModuloPersonal.Model.CargoNomina;
import ModuloPersonal.Model.Persona;
import ModuloPersonal.Model.TipoContrato;
import ModuloPersonal.Model.TipoDocumento;
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

public class ControlDB_Marcacion {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private String tipoConexion;   
    public ControlDB_Marcacion(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    public int registrar(MarcacionArchivo marcacionArchivo) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try {
            if (marcacionArchivo.getListadoMarcacionPersona() != null) {
                result=0;
                conexion = control.ConectarBaseDatos();
                String DB = control.getBaseDeDatos();
                conexion = control.ConectarBaseDatos();
                //Procedemos a registrar en la tabla marcacion archivo
                PreparedStatement QueryMarcacionArchivo = conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[marcacion_archivo] ([mar_cdgo]\n" +
                                                                                "      ,[mar_fecha]\n" +
                                                                                "      ,[mar_usuario_cdgo]\n" +
                                                                                "      ,[mar_estad]) VALUES ((SELECT (CASE WHEN (MAX([mar_cdgo]) IS NULL)\n" +
                                                                                                        " THEN 1\n" +
                                                                                                        " ELSE (MAX([mar_cdgo])+1) END)AS [mar_cdgo]\n" +
                                                                                    " FROM ["+DB+"].[dbo].[marcacion_archivo]),(SELECT SYSDATETIME()),?,1);");
                QueryMarcacionArchivo.setString(1, marcacionArchivo.getUsuario().getCodigo());
                QueryMarcacionArchivo.execute();
                result=1;
                if(result==1){
                    result=0;
                    //Sacamos el ultimo valor 
                    PreparedStatement queryConsultarMaximo= conexion.prepareStatement("SELECT MAX(mar_cdgo) FROM ["+DB+"].[dbo].[marcacion_archivo];");
                    ResultSet resultSetMaximo= queryConsultarMaximo.executeQuery();
                    while(resultSetMaximo.next()){ 
                        if(resultSetMaximo.getString(1) != null){
                            marcacionArchivo.setCodigo(resultSetMaximo.getString(1));
                        }
                    }
                }
                for (MarcacionPersona Objeto : marcacionArchivo.getListadoMarcacionPersona()) {
                    System.out.println("INSERT INTO ["+DB+"].[dbo].[marcacion_persona] ("
                            + "[mpa_cdgo],[map_marcacion_archivo_cdgo]\n" +
                        "      ,[mpa_persona_cdgo]\n" +
                        "      ,[mpa_persona_tipo_documento_cdgo]\n" +
                        "      ,[mpa_fecha_inicio]\n" +
                        "      ,[mpa_hora_inicio]\n" +
                        "      ,[mpa_fecha_fin]\n" +
                        "      ,[mpa_hora_fin]\n" +
                        "      ,[mpa_estad]) "
                            + "VALUES ((SELECT (CASE WHEN (MAX([mpa_cdgo]) IS NULL)\n" +
                                                    " THEN 1\n" +
                                                    " ELSE (MAX([mpa_cdgo])+1) END)AS [mpa_cdgo]\n" +
                                " FROM ["+DB+"].[dbo].[marcacion_persona]),'"+marcacionArchivo.getCodigo()+"','"+Objeto.getPersona().getCodigo()+"',"+Objeto.getPersona().getTipoDocumento().getCodigo()+""
                                        + ",'"+Objeto.getFechaInicio()+"','"+Objeto.getHoraInicio()+"','"+Objeto.getFechaFin()+"','"+Objeto.getHoraFin()+"',"+Objeto.getEstado()+");");
                    PreparedStatement QueryRegistrarMarcacionPersona = conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[marcacion_persona] ("
                            + "[mpa_cdgo],[map_marcacion_archivo_cdgo]\n" +
                        "      ,[mpa_persona_cdgo]\n" +
                        "      ,[mpa_persona_tipo_documento_cdgo]\n" +
                        "      ,[mpa_fecha_inicio]\n" +
                        "      ,[mpa_hora_inicio]\n" +
                        "      ,[mpa_fecha_fin]\n" +
                        "      ,[mpa_hora_fin]\n" +
                        "      ,[mpa_estad]) "
                            + "VALUES ((SELECT (CASE WHEN (MAX([mpa_cdgo]) IS NULL)\n" +
                                                    " THEN 1\n" +
                                                    " ELSE (MAX([mpa_cdgo])+1) END)AS [mpa_cdgo]\n" +
                                " FROM ["+DB+"].[dbo].[marcacion_persona]),?,?,?,?,?,?,?,?);");
                    QueryRegistrarMarcacionPersona.setString(1, marcacionArchivo.getCodigo());
                    QueryRegistrarMarcacionPersona.setString(2, Objeto.getPersona().getCodigo());
                    QueryRegistrarMarcacionPersona.setString(3, Objeto.getPersona().getTipoDocumento().getCodigo());
                    QueryRegistrarMarcacionPersona.setString(4, Objeto.getFechaInicio());
                    QueryRegistrarMarcacionPersona.setString(5, Objeto.getHoraInicio());
                    QueryRegistrarMarcacionPersona.setString(6, Objeto.getFechaFin());
                    QueryRegistrarMarcacionPersona.setString(7, Objeto.getHoraFin());
                    QueryRegistrarMarcacionPersona.setString(8, Objeto.getEstado());
                    QueryRegistrarMarcacionPersona.execute();
                    result = 1;
                   
                }
                if (result == 1) {
                    result = 0;
                    //Extraemos el nombre del Equipo y la IP        
                    String namePc = new ControlDB_Config().getNamePC();
                    String ipPc = new ControlDB_Config().getIpPc();
                    String macPC = new ControlDB_Config().getMacAddress();

                    PreparedStatement Query_Auditoria = conexion.prepareStatement("INSERT INTO [" + DB + "].[dbo].[auditoria]([au_cdgo]\n"
                            + "      ,[au_fecha]\n"
                            + "      ,[au_usuario_cdgo_registro]\n"
                            + "      ,[au_nombre_dispositivo_registro]\n"
                            + "      ,[au_ip_dispositivo_registro]\n"
                            + "      ,[au_mac_dispositivo_registro]\n"
                            + "      ,[au_cdgo_mtvo]\n"
                            + "      ,[au_desc_mtvo]\n"
                            + "      ,[au_detalle_mtvo])\n"
                            + "     VALUES("
                            + "           (SELECT (CASE WHEN (MAX([au_cdgo]) IS NULL) THEN 1 ELSE (MAX([au_cdgo])+1) END)AS [au_cdgo] FROM [" + DB + "].[dbo].[auditoria])"
                            + "           ,(SELECT SYSDATETIME())"
                            + "           ,?"
                            + "           ,?"
                            + "           ,?"
                            + "           ,?"
                            + "           ,?"
                            + "           ,'MARCACIÓN'"
                            + "           ,CONCAT (?,?));");
                    Query_Auditoria.setString(1, marcacionArchivo.getUsuario().getCodigo());
                    Query_Auditoria.setString(2, namePc);
                    Query_Auditoria.setString(3, ipPc);
                    Query_Auditoria.setString(4, macPC);
                    Query_Auditoria.setString(5, marcacionArchivo.getCodigo());
                    Query_Auditoria.setString(6, "Se subió una nueva marcacion al sistema, con Código: ");
                    Query_Auditoria.setString(7, marcacionArchivo.getCodigo());
                    Query_Auditoria.execute();
                    result = 1;
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
    public ArrayList<MarcacionArchivo>  buscarArchivosMarcacion(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<MarcacionArchivo> listadoObjetos = null;
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 

                PreparedStatement query= conexion.prepareStatement("SELECT  [mar_cdgo],"
                                                                        + "[mar_fecha],"
                                                                        + "[mar_usuario_cdgo],"
                                                                        + "[us_cdgo],"
                                                                        + "[us_nombres],"
                                                                        + "[us_apellidos],"
                                                                        + " CASE WHEN (mar_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS mar_estad \n" +
                                                                    "  FROM ["+DB+"].[dbo].[marcacion_archivo]    "
                                                                            + " INNER JOIN ["+DB+"].[dbo].[usuario] ON [mar_usuario_cdgo]=[us_cdgo]"
                                                                            + " WHERE [mar_fecha] BETWEEN ? AND ? ORDER BY [mar_cdgo] DESC;");
                query.setString(1, DatetimeInicio);
                query.setString(2, DatetimeFin);
                resultSet= query.executeQuery(); 
                boolean validar= true;
            while(resultSet.next()){  
                if(validar){
                    listadoObjetos= new ArrayList<>();
                    validar=false;
                }
                MarcacionArchivo Objeto = new MarcacionArchivo(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setFecha(resultSet.getString(2));
                Usuario usuario = new Usuario();
                usuario.setCodigo(resultSet.getString(4));
                usuario.setNombres(resultSet.getString(5));
                usuario.setApellidos(resultSet.getString(6));
                Objeto.setUsuario(usuario);
                Objeto.setEstado(resultSet.getString(7));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los archivos de marcaciones");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public MarcacionArchivo  buscarArchivosMarcacionRegistros(MarcacionArchivo marcacionArchivo) throws SQLException{
        ArrayList<MarcacionPersona> listadoObjetos = null;
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 

                PreparedStatement query= conexion.prepareStatement("SELECT  [mpa_cdgo] -- 1\n" +
                                                                    "      ,[map_marcacion_archivo_cdgo]-- 2\n" +
                                                                    "		  ,[mar_cdgo]-- 3\n" +
                                                                    "		  ,[mar_fecha]-- 4\n" +
                                                                    "		  ,[mar_usuario_cdgo]-- 5\n" +
                                                                    "		  ,CASE WHEN ([mar_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [mar_estad]--6 \n" +
                                                                    "      ,[mpa_persona_cdgo]-- 7\n" +
                                                                    "      ,[mpa_persona_tipo_documento_cdgo]--8 \n" +
                                                                    "		  ,[ps_cdgo]-- 9\n" +
                                                                    "		  ,[ps_tipo_documento_cdgo]-- 10\n" +
                                                                    "		  ,[ps_nombre]-- 11\n" +
                                                                    "		  ,[ps_apellidos]-- 12\n" +
                                                                    "		  ,[ps_telefono]-- 13\n" +
                                                                    "		  ,[ps_cargo_nomina_cdgo]-- 14\n" +
                                                                    "				,[cn_cdgo]-- 15\n" +
                                                                    "			  ,[cn_desc]-- 16\n" +
                                                                    "		  ,[ps_tipo_contrato_cdgo]-- 17\n" +
                                                                    "				,[tc_cdgo]-- 18\n" +
                                                                    "			  ,[tc_desc]-- 19\n" +
                                                                    "		  ,[ps_compania_cdgo]-- 20\n" +
                                                                    "				,[cp_cdgo]-- 21\n" +
                                                                    "			  ,[cp_desc]-- 22\n" +
                                                                    "		  ,[ps_equipo_cdgo]-- 23\n" +
                                                                    "				,[eq_cdgo]-- 24\n" +
                                                                    "			  ,[eq_modelo]-- 25\n" +
                                                                    "			  ,[eq_desc]-- 26\n" +
                                                                    "		  ,CASE WHEN ([ps_estado]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [ps_estado]-- 27\n" +
                                                                    "      ,[mpa_fecha_inicio]-- 28\n" +
                                                                    "      ,[mpa_hora_inicio]-- 29\n" +
                                                                    "      ,[mpa_fecha_fin]-- 30\n" +
                                                                    "      ,[mpa_hora_fin]-- 31\n" +
                                                                    "	  ,CASE WHEN ([mpa_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [mpa_estad]-- 32\n"+
                                                                    "     ,[tidoc_desc] --33\n" +
                                                                    "  FROM ["+DB+"].[dbo].[marcacion_persona]\n" +
                                                                    "	  INNER JOIN  ["+DB+"].[dbo].[marcacion_archivo] ON [map_marcacion_archivo_cdgo]=[mar_cdgo]\n" +
                                                                    "	  INNER JOIN ["+DB+"].[dbo].[persona] ON [mpa_persona_cdgo]=[ps_cdgo] AND [mpa_persona_tipo_documento_cdgo]=[ps_tipo_documento_cdgo]\n" +
                                                                    "	  INNER JOIN ["+DB+"].[dbo].[cargo_nomina] ON [ps_cargo_nomina_cdgo]=[cn_cdgo]\n" +
                                                                    "	  INNER JOIN ["+DB+"].[dbo].[tipo_contrato] ON [ps_tipo_contrato_cdgo]=[tc_cdgo]\n" +
                                                                    "	  INNER JOIN ["+DB+"].[dbo].[compania] ON [ps_compania_cdgo]=[cp_cdgo]\n" +
                                                                    "	  LEFT JOIN ["+DB+"].[dbo].[equipo] ON [ps_equipo_cdgo]=[eq_cdgo] "+
                                                                    "     INNER JOIN ["+DB+"].[dbo].[tipo_documento] ON [mpa_persona_tipo_documento_cdgo]=[tidoc_cdgo]\n" +
                                                                    "	  WHERE [map_marcacion_archivo_cdgo]=?;");
                query.setString(1, marcacionArchivo.getCodigo());
                resultSet= query.executeQuery(); 
                boolean validar= true;
            while(resultSet.next()){  
                if(validar){
                    listadoObjetos= new ArrayList<>();
                    validar=false;
                }
                MarcacionPersona Objeto = new MarcacionPersona(); 
                Objeto.setCodigo(resultSet.getString(1));
                
                Persona persona = new Persona();
                  persona.setCodigo(resultSet.getString(9));
                  persona.setTipoDocumento(new TipoDocumento(resultSet.getString(8), resultSet.getString(33), "1"));
                  persona.setNombre(resultSet.getString(11));
                  persona.setApellido(resultSet.getString(12));
                  persona.setTelefono(resultSet.getString(13));
                  persona.setCargoNomina(new CargoNomina(resultSet.getString(15), resultSet.getString(16), "1"));
                  persona.setTipoContrato(new TipoContrato(resultSet.getString(18), resultSet.getString(19), "1"));
                  persona.setCompania(new Compañia(resultSet.getString(21), resultSet.getString(22), "1"));
                    Equipo equipo =new Equipo();
                    equipo.setCodigo(resultSet.getString(24));
                    equipo.setModelo(resultSet.getString(25));
                    equipo.setDescripcion(resultSet.getString(26));
                    
                    
                  persona.setEquipo(equipo);
                  
                Objeto.setPersona(persona);
                Objeto.setFechaInicio(resultSet.getString(28));
                Objeto.setHoraInicio(resultSet.getString(29));
                Objeto.setFechaFin(resultSet.getString(30));
                Objeto.setHoraFin(resultSet.getString(31));
                Objeto.setEstado(resultSet.getString(32));
                
                
                listadoObjetos.add(Objeto);
            }
            marcacionArchivo.setListadoMarcacionPersona(listadoObjetos);
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las personas para el archivo de marcaicón");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return marcacionArchivo;
    }
    public int actualizar(MarcacionArchivo Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{

            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();      
            PreparedStatement query;
            String valorEstado="";
            if(Objeto.getEstado().equals("ACTIVO")){
                valorEstado="0";
            }else{
                valorEstado="1";
            }
            query= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[marcacion_archivo] SET [mar_estad]=? WHERE [mar_cdgo] = ?");
            query.setString(1, valorEstado);
            query.setString(2, Objeto.getCodigo());
            query.execute();
            result=1;
            if(result ==1){
                PreparedStatement queryPersonal=conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[marcacion_persona] SET [mpa_estad]=? WHERE [map_marcacion_archivo_cdgo] = ?");
                queryPersonal.setString(1, valorEstado);
                queryPersonal.setString(2, Objeto.getCodigo());
                queryPersonal.execute();
                result=1;
            }
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
                        "           ,'MARCACION'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre el estado de la marcaicón de Código: ',?,' Pasadon de estado:',?,' a estado : ',?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, Objeto.getCodigo());
                Query_Auditoria.setString(6, Objeto.getCodigo());
                if(valorEstado.equals("0")){
                    Query_Auditoria.setString(7, "ACTIVO");
                    Query_Auditoria.setString(8, "INACTIVO");
                }else{
                    Query_Auditoria.setString(7, "INACTIVO");
                    Query_Auditoria.setString(8, "ACTIVO");
                }
                Query_Auditoria.execute();
                result=1;
            }
        }
        catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "ERROR al querer actualizar los datos.");
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    } 
    public boolean validarMarcacionEquipoPersona(Persona persona, String fecha) throws SQLException{
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
       boolean asistio=false;
        try{
            ResultSet resultSet; 
            PreparedStatement query= conexion.prepareStatement("SELECT  [mar_cdgo]\n" +
                                                                "      ,[mar_fecha]\n" +
                                                                "      ,[mar_usuario_cdgo]\n" +
                                                                "      ,[mar_estad]\n" +
                                                                "			,[mpa_cdgo]\n" +
                                                                "			,[map_marcacion_archivo_cdgo]\n" +
                                                                "			,[mpa_persona_cdgo]\n" +
                                                                "			,[mpa_persona_tipo_documento_cdgo]\n" +
                                                                "			,[mpa_fecha_inicio]\n" +
                                                                "			,[mpa_hora_inicio]\n" +
                                                                "			,[mpa_fecha_fin]\n" +
                                                                "			,[mpa_hora_fin]\n" +
                                                                "			,[mpa_estad]\n" +
                                                                "  FROM ["+DB+"].[dbo].[marcacion_archivo]\n" +
                                                                "  INNER JOIN["+DB+"].[dbo].[marcacion_persona] ON [mar_cdgo]=[map_marcacion_archivo_cdgo]  \n" +
                                                                "  WHERE [mpa_persona_cdgo] LIKE ? AND [mpa_persona_tipo_documento_cdgo]=? AND [mar_estad]=1 AND [mpa_estad]=1 AND\n" +
                                                                "  (((convert(date ,?))) = [mpa_fecha_inicio] OR ((convert(date ,?)))= [mpa_fecha_fin] );");
                query.setString(1, persona.getCodigo());
                query.setString(2, persona.getTipoDocumento().getCodigo());
                query.setString(3, fecha);
                query.setString(4, fecha);
                resultSet= query.executeQuery();               
            while(resultSet.next()){  
                asistio=true;  
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar si la persona asistío o no");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return asistio;
    } 
    
}
