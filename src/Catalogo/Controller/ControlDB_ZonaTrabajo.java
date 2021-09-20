package Catalogo.Controller;

import Catalogo.Model.Articulo;
import ConnectionDB.Conexion_DB_costos_vg;
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import Catalogo.Model.ListadoSitiosZonaTrabajo;
import Catalogo.Model.ZonaTrabajo;
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
import ModuloCarbon.Model.DebitoZonaTrabajo;

public class ControlDB_ZonaTrabajo {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private String tipoConexion;
    
    public ControlDB_ZonaTrabajo(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }  
    public ArrayList<ZonaTrabajo> listarZonaTrabajo(){
         ArrayList<ZonaTrabajo> listadoObjetos = null;
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT [zt_cdgo]\n" +
                                                                "      ,[zt_desc]\n" +
                                                                "      ,[zt_estad]\n" +
                                                                "  FROM ["+DB+"].[dbo].[zona_trabajo]  WHERE [zt_estad]=1;");
              ResultSet resultSet= query.executeQuery();
              boolean validar= true;
            while(resultSet.next()){
                if(validar){
                    listadoObjetos= new ArrayList<>();
                    validar=false;
                }
                
                ZonaTrabajo Objeto= new ZonaTrabajo();
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los articulo");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }    
    public ArrayList<ZonaTrabajo> listarZonaTrabajoPorCentroOperacion(CentroOperacion ctrOpera){
         ArrayList<ZonaTrabajo> listadoObjetos = null;
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT [zt_cdgo]\n" +
                        "   ,[zt_desc]\n" +
                        "   ,[zt_estad] "+
                        " FROM [costos_vg].[dbo].[listado_zona_trabajo]\n" +
                        "	INNER JOIN  [costos_vg].[dbo].[cntro_cost_auxiliar] ON [lzt_cntro_cost_auxiliar_cdgo]=[cca_cdgo]\n" +
                        "	INNER JOIN [costos_vg].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
                        "	INNER JOIN [costos_vg].[dbo].[cntro_oper]			ON [ccs_cntro_oper_cdgo]=[co_cdgo]\n" +
                        "	INNER JOIN [costos_vg].[dbo].[zona_trabajo]			 ON [lzt_zona_trabajo_cdgo]=[zt_cdgo]\n" +
                        " WHERE [co_cdgo]=? AND [zt_estad]=1 GROUP BY [zt_cdgo],[zt_desc],[zt_estad];");
                    query.setString(1, ""+ctrOpera.getCodigo()); 
            ResultSet resultSet= query.executeQuery();
              boolean validar= true;
            while(resultSet.next()){
                if(validar){
                    listadoObjetos= new ArrayList<>();
                    validar=false;
                }
                
                ZonaTrabajo Objeto= new ZonaTrabajo();
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los articulo");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }    
    public int registrarDebito(DebitoZonaTrabajo Objeto) throws FileNotFoundException, UnknownHostException, SocketException, SQLException{
        int result=0;
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement Query = null;
            Query = conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[debito_lavado_vehiculo]\n" +
                "           ([dlv_cdgo]\n" +
                "           ,[dlv_fecha_debito]\n" +
                "           ,[dlv_zona_trabajo_cdgo]\n" +
                "           ,[dlv_valor]\n" +
                "           ,[dlv_desc]\n" +
                "           ,[dlv_estad]\n" +
                "           ,[dlv_usuario_registra]\n" +
                "           ,[dlv_fecha_registro])\n" +
                "     VALUES\n" +
                "           ((SELECT (CASE WHEN (MAX([dlv_cdgo]) IS NULL) THEN 1 ELSE (MAX([dlv_cdgo])+1) END)AS [dlv_cdgo] FROM ["+DB+"].[dbo].[debito_lavado_vehiculo])\n" +
                "           ,?,?,?,?,1,?,(SELECT SYSDATETIME()));");
                Query.setString(1, Objeto.getFechaMvomiento());
                Query.setString(2, Objeto.getZonaTrabajo().getCodigo());
                Query.setString(3, Objeto.getValor());
                Query.setString(4, Objeto.getDescripcion()+"'");
                Query.setString(5,Objeto.getUsuarioQuienRegistra().getCodigo());
                Query.execute();
                result=1;
            if(result==1){
                result=0;
                //Sacamos el ultimo valor 
                PreparedStatement queryMaximo= conexion.prepareStatement("SELECT MAX(dlv_cdgo) FROM ["+DB+"].[dbo].[debito_lavado_vehiculo];");
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
                    "           ,'DEBITO'" +
                    "           ,CONCAT (?,?,'Valor:',?,' ZonaTrabajo: ',?,' Motivo: ',?,' Estado: ',? ));");
                Query_Auditoria.setString(1, Objeto.getUsuarioQuienRegistra().getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, Objeto.getCodigo());
                Query_Auditoria.setString(6, "Se registró un nuevo debito con código: ");
                Query_Auditoria.setString(7, Objeto.getCodigo());
                Query_Auditoria.setString(8, Objeto.getValor());
                Query_Auditoria.setString(9,Objeto.getZonaTrabajo().getDescripcion());
                Query_Auditoria.setString(10,Objeto.getDescripcion());
                Query_Auditoria.setString(11, "Activo");
                Query_Auditoria.execute();
                result=1;
            }
          
        }catch (SQLException sqlException ){   
            result=0;
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    } 
    public int registrar(ZonaTrabajo Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
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
            conexion= control.ConectarBaseDatos();

            PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[zona_trabajo] ([zt_cdgo],[zt_desc],[zt_estad]) VALUES ("
                    + "(SELECT (CASE WHEN (MAX([zt_cdgo]) IS NULL) THEN 1 ELSE (MAX([zt_cdgo])+1) END)AS [zt_cdgo] FROM ["+DB+"].[dbo].[zona_trabajo]),"
                            + "?,?);");
            Query.setString(1, Objeto.getDescripcion());
            Query.setString(2, Objeto.getEstado());
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
                    "           ,(SELECT (CASE WHEN (MAX([zt_cdgo]) IS NULL) THEN 1 ELSE (MAX([zt_cdgo])) END)AS [zt_cdgo] FROM ["+DB+"].[dbo].[zona_trabajo])"+
                    "           ,'ZONA_TRABAJO'" +
                    "           ,CONCAT (?,(SELECT (CASE WHEN (MAX([zt_cdgo]) IS NULL) THEN 1 ELSE (MAX([zt_cdgo])) END)AS [zt_cdgo] FROM ["+DB+"].[dbo].[zona_trabajo]),' Nombre: ',?,' Estado: ',?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, "Se registró una zona de trabajo, con Código: ");
                Query_Auditoria.setString(6, Objeto.getDescripcion());
                Query_Auditoria.setString(7, estado);
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
    public boolean validarExistencia(ZonaTrabajo Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[zona_trabajo] WHERE [zt_desc] LIKE ?;");
            query.setString(1, Objeto.getDescripcion());
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                retorno =true;               
            }
        }catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null, "Error al Tratar de buscar la zona de trabajo");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return retorno;
    }  
    public ArrayList<ZonaTrabajo> buscar(String valorConsulta) throws SQLException{
        ArrayList<ZonaTrabajo> listadoObjetos = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            if(valorConsulta.equals("")){
                PreparedStatement query= conexion.prepareStatement("SELECT [zt_cdgo]\n" +
                                                                "     ,[zt_desc]\n" +
                                                                "     ,CASE WHEN (zt_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS zt_estad\n" +
                                                                "  FROM ["+DB+"].[dbo].[zona_trabajo]    ORDER BY [zt_desc] ASC;");
                resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexion.prepareStatement("SELECT [zt_cdgo]\n" +
                                                                "      ,[zt_desc]\n" +
                                                                "      ,CASE WHEN (zt_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS zt_estad\n" +
                                                                "  FROM ["+DB+"].[dbo].[zona_trabajo]   WHERE ([zt_cdgo] LIKE ? OR [zt_desc] LIKE ?);");
                query.setString(1, "%"+valorConsulta+"%");
                query.setString(2, "%"+valorConsulta+"%");
                resultSet= query.executeQuery();              
            }
            while(resultSet.next()){  
                ZonaTrabajo Objeto= new ZonaTrabajo();
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las zonas de trabajo");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public ZonaTrabajo buscarZonaTrabajoParticular(String codigo) throws SQLException{
        ZonaTrabajo Objeto =null; 
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT [zt_cdgo]\n" +
                                                                "     ,[zt_desc]\n" +
                                                                "     ,CASE WHEN (zt_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS zt_estad\n" +
                                                                "  FROM ["+DB+"].[dbo].[zona_trabajo]  WHERE [zt_cdgo] = ?;"); 
            query.setString(1, codigo);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                Objeto = new ZonaTrabajo(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));    
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar la zona de trabajo");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return Objeto;
    }
    public int actualizar(ZonaTrabajo Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException {
        int result=0;
        try{
            ZonaTrabajo ZonaTrabajoAnterior=buscarZonaTrabajoParticular(""+Objeto.getCodigo());
            String estado="";
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                estado="ACTIVO";
            }else{
                estado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();      
            PreparedStatement query= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[zona_trabajo] SET  [zt_desc]=?, [zt_estad]=? WHERE [zt_cdgo] = ?");
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
                        "           ,'ZONA_TRABAJO'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre la zona de trabajo: Código: ',?,' Nombre: ',?,' Estado: ',?,"
                                            + "' actualizando la siguiente informacion a Código: ',?,' Nombre: ',?,' Estado: ',?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, ZonaTrabajoAnterior.getCodigo());
                Query_Auditoria.setString(6, ZonaTrabajoAnterior.getCodigo());
                Query_Auditoria.setString(7, ZonaTrabajoAnterior.getDescripcion());
                Query_Auditoria.setString(8, ZonaTrabajoAnterior.getEstado());
                Query_Auditoria.setString(9, Objeto.getCodigo());
                Query_Auditoria.setString(10, Objeto.getDescripcion());
                Query_Auditoria.setString(11, estado);
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
    
    /*public ArrayList<CentroCostoAuxiliar> buscarCentroCostoAuxiliar(ZonaTrabajo zonaTrabajo) throws SQLException{
        ArrayList<CentroCostoAuxiliar> listadoObjeto = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("  SELECT \n" +
                                                                        "	  [co_cdgo],\n" +
                                                                        "      [co_desc],\n" +
                                                                        "	  CASE WHEN ([co_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [co_estad] ,\n" +
                                                                        "	  [ccs_cdgo], \n" +
                                                                        "	  [ccs_desc], \n" +
                                                                        "	  CASE WHEN ([ccs_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [ccs_estad] ,\n" +
                                                                        "	  [cca_cdgo], \n" +
                                                                        "	  [cca_desc], \n" +
                                                                        "	  CASE WHEN (cca_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS cca_estad \n" +
                                                                        "  FROM ["+DB+"].[dbo].[cntro_cost_auxiliar]\n" +
                                                                        "       INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro]  ON ccs_cdgo=cca_cntro_cost_subcentro_cdgo\n" +
                                                                        "	   INNER JOIN ["+DB+"].[dbo].cntro_oper ON ccs_cntro_oper_cdgo=co_cdgo;");
                ResultSet resultSetBuscar= queryBuscar.executeQuery();
           
                queryBuscar.setString(1, "%"+valorConsulta+"%");
                resultSetBuscar= queryBuscar.executeQuery();
            
            while(resultSetBuscar.next()){ 
                CentroOperacion centroOperacion = new CentroOperacion(); 
                    centroOperacion.setCodigo(resultSetBuscar.getInt(1));
                    centroOperacion.setDescripcion(resultSetBuscar.getString(2));
                    centroOperacion.setEstado(resultSetBuscar.getString(3));
                CentroCostoSubCentro centroCostoSubCentro = new CentroCostoSubCentro(); 
                    centroCostoSubCentro.setCodigo(resultSetBuscar.getInt(4));
                    centroCostoSubCentro.setDescripcion(resultSetBuscar.getString(5));
                    centroCostoSubCentro.setEstado(resultSetBuscar.getString(6));
                    centroCostoSubCentro.setCentroOperacion(centroOperacion);
                CentroCostoAuxiliar Objeto = new CentroCostoAuxiliar(); 
                Objeto.setCodigo(resultSetBuscar.getInt(7));
                Objeto.setDescripcion(resultSetBuscar.getString(8));
                Objeto.setCentroCostoSubCentro(centroCostoSubCentro);
                Objeto.setEstado(resultSetBuscar.getString(9));
                listadoObjeto.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los centro de costros Auxiliares");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjeto;
    } */
    public ArrayList<ZonaTrabajo> listaZonaTrabajoConSitios(String valorConsulta){
        ArrayList<ZonaTrabajo> listadoObjetos = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            if(valorConsulta.equals("")){
                PreparedStatement query= conexion.prepareStatement("SELECT [zt_cdgo]\n" +
                                                                "     ,[zt_desc]\n" +
                                                                "     ,CASE WHEN (zt_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS zt_estad\n" +
                                                                "  FROM ["+DB+"].[dbo].[zona_trabajo]    ORDER BY [zt_desc] ASC;");
                resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexion.prepareStatement("SELECT [zt_cdgo]\n" +
                                                                "      ,[zt_desc]\n" +
                                                                "      ,CASE WHEN (zt_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS zt_estad\n" +
                                                                "  FROM ["+DB+"].[dbo].[zona_trabajo]   WHERE ([zt_cdgo] LIKE ? OR [zt_desc] LIKE ?);");
                query.setString(1, "%"+valorConsulta+"%");
                query.setString(2, "%"+valorConsulta+"%");
                resultSet= query.executeQuery();              
            }
            while(resultSet.next()){  
                ZonaTrabajo Objeto= new ZonaTrabajo();
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las zonas de trabajo");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }
    
    public boolean validarExistenciaListadoZonaTrabajo(ZonaTrabajo Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[listado_zona_trabajo] WHERE [lzt_zona_trabajo_cdgo]=? AND [lzt_cntro_cost_auxiliar_cdgo]=?;");
            query.setString(1, Objeto.getCodigo());
            query.setInt(2, Objeto.getListadoCentroCostoAuxiliar().get(0).getCodigo());
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                retorno =true;               
            }
        }catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null, "Error al tratar de buscar la zona de trabajo");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return retorno;
    }
    public int registrarEnListadoZonaTrabajo(ZonaTrabajo Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
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
            conexion= control.ConectarBaseDatos();

            PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[listado_zona_trabajo] ([lzt_zona_trabajo_cdgo],[lzt_cntro_cost_auxiliar_cdgo],[lzt_estad]) VALUES (?,?,?);");
            Query.setString(1, Objeto.getCodigo());
            Query.setInt(2, Objeto.getListadoCentroCostoAuxiliar().get(0).getCodigo());
            Query.setString(3, Objeto.getEstado());
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
                    "           ,CONCAT('zt','"+Objeto.getCodigo()+"','cc_aux','"+Objeto.getListadoCentroCostoAuxiliar().get(0).getCodigo()+"')"+
                    "           ,'LISTADO_ZONA_TRABAJO_CC_AUXILIAR'" +
                    "           ,CONCAT ('zt','"+Objeto.getCodigo()+"','cc_aux','"+Objeto.getListadoCentroCostoAuxiliar().get(0).getCodigo()+"',' Nombre_ZonaTrabajo: ',?,' Nombre_ZonaTrabajo: ',?,' Estado: ',?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, Objeto.getDescripcion());
                Query_Auditoria.setString(6, Objeto.getListadoCentroCostoAuxiliar().get(0).getDescripcion());
                Query_Auditoria.setString(7, estado);
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
    public ArrayList<ListadoSitiosZonaTrabajo> listarLista_Cc_auxiliar_ZonaTrabajo(String consulta){
         ArrayList<ListadoSitiosZonaTrabajo> listadoObjetos = null;
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT \n" +
                                                                "		[lzt_zona_trabajo_cdgo]--1\n" +
                                                                "			,[zt_cdgo]--2\n" +
                                                                "		  ,[zt_desc]--3\n" +
                                                                "		  ,[zt_estad]--4\n" +
                                                                "      ,[lzt_cntro_cost_auxiliar_cdgo]--5\n" +
                                                                "			,[cca_cdgo]--6\n" +
                                                                "			,[cca_cntro_cost_subcentro_cdgo]--7\n" +
                                                                "					,[ccs_cdgo]--8\n" +
                                                                "				  ,[ccs_desc]--9\n" +
                                                                "				  ,[ccs_estad]--10\n" +
                                                                "				  ,[ccs_cntro_oper_cdgo]--11\n" +
                                                                "					  ,[co_cdgo]--12\n" +
                                                                "					  ,[co_desc]--13\n" +
                                                                "					  ,[co_estad]--14\n" +
                                                                "				  ,[ccs_cntro_cost_rquiere_labor_realizda]--15\n" +
                                                                "			,[cca_desc]--16\n" +
                                                                "			,[cca_estad]--17\n" +
                                                                "      ,[lzt_estad]--18\n" +
                                                                "  FROM ["+DB+"].[dbo].[listado_zona_trabajo]\n" +
                                                                "  INNER JOIN ["+DB+"].[dbo].[zona_trabajo] ON [lzt_zona_trabajo_cdgo]= [zt_cdgo]\n" +
                                                                "  INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [lzt_cntro_cost_auxiliar_cdgo]=[cca_cdgo]\n" +
                                                                "  INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
                                                                "  INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [ccs_cntro_oper_cdgo]=[co_cdgo] WHERE [zt_desc] LIKE ? OR [ccs_desc] LIKE ? OR [cca_desc] LIKE ?;");
              query.setString(1, "%"+consulta+"%");
              query.setString(2, "%"+consulta+"%");
              query.setString(3, "%"+consulta+"%");
            ResultSet resultSet= query.executeQuery();
              boolean validar= true;
            while(resultSet.next()){
                if(validar){
                    listadoObjetos= new ArrayList<>();
                    validar=false;
                }    
                CentroOperacion centroOperacion = new CentroOperacion();
                    centroOperacion.setCodigo(resultSet.getInt(12));
                    centroOperacion.setDescripcion(resultSet.getString(13));
                    centroOperacion.setEstado(resultSet.getString(14));
                CentroCostoSubCentro centroCostoSubCentro = new CentroCostoSubCentro();
                    centroCostoSubCentro.setCodigo(resultSet.getInt(8));
                    centroCostoSubCentro.setDescripcion(resultSet.getString(9));
                    centroCostoSubCentro.setCentroOperacion(centroOperacion);
                    centroCostoSubCentro.setCentroCostoRequiereLaborRealizada(resultSet.getString(15));
                    centroCostoSubCentro.setEstado(resultSet.getString(10));
                CentroCostoAuxiliar centroCostoAuxiliar = new CentroCostoAuxiliar();
                centroCostoAuxiliar.setCodigo(resultSet.getInt(6));
                centroCostoAuxiliar.setCentroCostoSubCentro(centroCostoSubCentro);
                centroCostoAuxiliar.setDescripcion(resultSet.getString(16));
                centroCostoAuxiliar.setEstado(resultSet.getString(17));
                       
                ZonaTrabajo Objeto= new ZonaTrabajo();
                Objeto.setCodigo(resultSet.getString(2));
                Objeto.setDescripcion(resultSet.getString(3));
                Objeto.setEstado(resultSet.getString(4));
                
                ListadoSitiosZonaTrabajo listadoSitiosZonaTrabajo = new  ListadoSitiosZonaTrabajo();
                listadoSitiosZonaTrabajo.setCentroCostoAuxiliar(centroCostoAuxiliar);
                listadoSitiosZonaTrabajo.setZonaTrabajo(Objeto);
                listadoSitiosZonaTrabajo.setEstado(resultSet.getString(18));
                
                listadoObjetos.add(listadoSitiosZonaTrabajo);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los articulo");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }   
    public int actualizarSitioZonaTrabajo(ListadoSitiosZonaTrabajo Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            String estadoAnterior="";
            String estadoNuevo="";
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                estadoAnterior="INACTIVO";
                estadoNuevo="ACTIVO";
            }else{
                estadoAnterior="ACTIVO";
                estadoNuevo="INACTIVO";
            }
            
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();      
            PreparedStatement query= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[listado_zona_trabajo] SET [lzt_estad]=? "
                    + "WHERE [lzt_zona_trabajo_cdgo] = ? AND [lzt_cntro_cost_auxiliar_cdgo]=?");
            query.setString(1, Objeto.getEstado());
            query.setString(2, Objeto.getZonaTrabajo().getCodigo());
            query.setInt(3, Objeto.getCentroCostoAuxiliar().getCodigo());                
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
                        "           ,'LISTADOSITIOS_ZONATRABAJO'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre ',?,'Código_ZonaTrabajo: ',?,' Descripción_ZonaTrabajo: ',?,'Código_CentroCostoAuxiliar: ',?,' Descripción_CentroCostoAuxiliar: ',?,' Estado_Anterior: ',?,' Estado_Nuevo: ',?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, Objeto.getZonaTrabajo().getCodigo()+":"+Objeto.getCentroCostoAuxiliar().getCodigo());
                Query_Auditoria.setString(6, "Un sitio en una zona de Operación :");
                Query_Auditoria.setString(7, Objeto.getZonaTrabajo().getCodigo());
                Query_Auditoria.setString(8, Objeto.getZonaTrabajo().getDescripcion());
                Query_Auditoria.setInt(9, Objeto.getCentroCostoAuxiliar().getCodigo());
                Query_Auditoria.setString(10, Objeto.getCentroCostoAuxiliar().getDescripcion());
                Query_Auditoria.setString(11, estadoAnterior);
                Query_Auditoria.setString(12, estadoNuevo);
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
    } }
