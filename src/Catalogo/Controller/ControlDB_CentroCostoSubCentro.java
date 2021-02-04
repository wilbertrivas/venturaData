package Catalogo.Controller;
   
import ConnectionDB2.Conexion_DB_costos_vg;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
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

public class ControlDB_CentroCostoSubCentro {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private String tipoConexion;
    
    public ControlDB_CentroCostoSubCentro(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    public int registrar(CentroCostoSubCentro Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
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
            PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[cntro_cost_subcentro] ([ccs_cdgo],[ccs_desc],[ccs_estad],[ccs_cntro_oper_cdgo],[ccs_cntro_cost_rquiere_labor_realizda]) VALUES ((SELECT (CASE WHEN (MAX([ccs_cdgo]) IS NULL)\n" +
                                                                                                    " THEN 1\n" +
                                                                                                    " ELSE (MAX([ccs_cdgo])+1) END)AS [ccs_cdgo]\n" +
                                                                                " FROM ["+DB+"].[dbo].[cntro_cost_subcentro]),?,?,?,?);");
            Query.setString(1, Objeto.getDescripcion());
            Query.setString(2, Objeto.getEstado());
            Query.setInt(3, Objeto.getCentroOperacion().getCodigo());
            Query.setString(4, Objeto.getCentroCostoRequiereLaborRealizada());
            Query.execute();
            result=1;
            if(result==1){
                result=0;
                //Sacamos el ultimo valor       
                PreparedStatement query= conexion.prepareStatement("SELECT MAX(ccs_cdgo) FROM ["+DB+"].[dbo].[cntro_cost_subcentro];");
                ResultSet resultSet= query.executeQuery();
                while(resultSet.next()){ 
                    if(resultSet.getString(1) != null){
                        Objeto.setCodigo(resultSet.getInt(1));
                    }
                }
                //Extraemos el nombre del Equipo y la IP        
                String namePc=new ControlDB_Config().getNamePC();
                String ipPc=new ControlDB_Config().getIpPc();
                String macPC=new ControlDB_Config().getMacAddress();
                
                String requiereLabor="";
                if(Objeto.getCentroCostoRequiereLaborRealizada().equals("1")){
                    requiereLabor="SI";
                }else{
                    requiereLabor="NO";
                }
                
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
                        "           ,'CENTROCOSTO_SUBCENTRO'" +
                        "           ,CONCAT (?,?,' Nombre: ',?,' Estado: ',?,' Centro Operación: ',?, ' CentroCostoRequiereLaborRealizada: ',?));");
               Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, ""+Objeto.getCodigo());
                Query_Auditoria.setString(6, "Se registró un nuevo subcentro de costos en el sistema, con Código: ");
                Query_Auditoria.setInt(7, Objeto.getCodigo());
                Query_Auditoria.setString(8, Objeto.getDescripcion());
                Query_Auditoria.setString(9, estado);
                Query_Auditoria.setString(10, Objeto.getCentroOperacion().getDescripcion());  
                Query_Auditoria.setString(11, requiereLabor);
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
    public ArrayList<CentroCostoSubCentro> buscar(String valorConsulta) throws SQLException{
        ArrayList<CentroCostoSubCentro> listadoObjetos = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            if(valorConsulta.equals("")){
                PreparedStatement query= conexion.prepareStatement("SELECT [ccs_cdgo]\n" +
                                                                    "      ,[ccs_desc]\n" +
                                                                    "	  ,CASE WHEN ([ccs_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [ccs_estad]\n" +
                                                                    "      ,[ccs_cntro_oper_cdgo]\n" +
                                                                    "		  ,[co_cdgo]\n" +
                                                                    "		  ,[co_desc]\n" +
                                                                    "		  ,CASE WHEN ([co_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [co_estad]\n" +
                                                                    "     ,[ccs_cntro_cost_rquiere_labor_realizda]\n"+
                                                                    "  FROM ["+DB+"].[dbo].[cntro_cost_subcentro]\n" +
                                                                    "  INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [ccs_cntro_oper_cdgo]= [co_cdgo];");
                resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexion.prepareStatement("SELECT [ccs_cdgo]\n" +
                                                                    "      ,[ccs_desc]\n" +
                                                                    "	  ,CASE WHEN ([ccs_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [ccs_estad]\n" +
                                                                    "      ,[ccs_cntro_oper_cdgo]\n" +
                                                                    "		  ,[co_cdgo]\n" +
                                                                    "		  ,[co_desc]\n" +
                                                                    "		  ,CASE WHEN ([co_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [co_estad]\n" +
                                                                    "     ,[ccs_cntro_cost_rquiere_labor_realizda]\n"+
                                                                    "  FROM ["+DB+"].[dbo].[cntro_cost_subcentro]\n" +
                                                                    "  INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [ccs_cntro_oper_cdgo]= [co_cdgo] WHERE [ccs_desc] like ?;");
                query.setString(1, "%"+valorConsulta+"%");
                resultSet= query.executeQuery();              
            }
            while(resultSet.next()){  
                CentroCostoSubCentro Objeto = new CentroCostoSubCentro(); 
                    Objeto.setCodigo(resultSet.getInt(1));
                    Objeto.setDescripcion(resultSet.getString(2));
                    Objeto.setEstado(resultSet.getString(3));
                    CentroOperacion centroOperacion = new CentroOperacion();
                    centroOperacion.setCodigo(resultSet.getInt(5));
                    centroOperacion.setDescripcion(resultSet.getString(6));
                    centroOperacion.setEstado(resultSet.getString(7));
                    Objeto.setCentroOperacion(centroOperacion);
                    Objeto.setCentroCostoRequiereLaborRealizada(resultSet.getString(8));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los Subcentro de costos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public CentroCostoSubCentro buscarEspecifico(String codigo) throws SQLException{
        CentroCostoSubCentro Objeto =null; 
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT [ccs_cdgo]\n" +
                                                                    "      ,[ccs_desc]\n" +
                                                                    "	  ,CASE WHEN ([ccs_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [ccs_estad]\n" +
                                                                    "      ,[ccs_cntro_oper_cdgo]\n" +
                                                                    "		  ,[co_cdgo]\n" +
                                                                    "		  ,[co_desc]\n" +
                                                                    "		  ,CASE WHEN ([co_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [co_estad]\n" +
                                                                    "     ,[ccs_cntro_cost_rquiere_labor_realizda]\n"+
                                                                    "  FROM ["+DB+"].[dbo].[cntro_cost_subcentro]\n" +
                                                                    "  INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [ccs_cntro_oper_cdgo]= [co_cdgo] WHERE [ccs_cdgo] = ?;"); 
            query.setString(1, codigo);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                Objeto = new CentroCostoSubCentro(); 
                Objeto.setCodigo(resultSet.getInt(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                CentroOperacion centroOperacion = new CentroOperacion();
                centroOperacion.setCodigo(resultSet.getInt(5));
                centroOperacion.setDescripcion(resultSet.getString(6));
                centroOperacion.setEstado(resultSet.getString(7));
                Objeto.setCentroOperacion(centroOperacion);
                Objeto.setCentroCostoRequiereLaborRealizada(resultSet.getString(8));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los Subcentro de costos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return Objeto;
    } 
    public ArrayList<CentroCostoSubCentro> buscarActivos(CentroOperacion centroOperacion) throws SQLException{
        ArrayList<CentroCostoSubCentro> listadoObjetos = null;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT [ccs_cdgo]\n" +
                                                                    "      ,[ccs_desc]\n" +
                                                                    "	  ,CASE WHEN ([ccs_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [ccs_estad]\n" +
                                                                    "      ,[ccs_cntro_oper_cdgo]\n" +
                                                                    "		  ,[co_cdgo]\n" +
                                                                    "		  ,[co_desc]\n" +
                                                                    "		  ,CASE WHEN ([co_estad]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [co_estad]\n" +
                                                                    "     ,[ccs_cntro_cost_rquiere_labor_realizda]\n"+
                                                                    "  FROM ["+DB+"].[dbo].[cntro_cost_subcentro]\n" +
                                                                    "  INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [ccs_cntro_oper_cdgo]= [co_cdgo] WHERE [ccs_estad]=1 AND ccs_cntro_oper_cdgo=?;");
            
            query.setInt(1, centroOperacion.getCodigo());
            ResultSet resultSet= query.executeQuery();
            boolean validator=true;
            while(resultSet.next()){ 
                if(validator){
                    listadoObjetos = new ArrayList();
                    validator= false;
                }
                CentroCostoSubCentro Objeto = new CentroCostoSubCentro(); 
                Objeto.setCodigo(resultSet.getInt(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                CentroOperacion co = new CentroOperacion();
                co.setCodigo(resultSet.getInt(5));
                co.setDescripcion(resultSet.getString(6));
                co.setEstado(resultSet.getString(7));
                Objeto.setCentroOperacion(co);
                Objeto.setCentroCostoRequiereLaborRealizada(resultSet.getString(8));
                listadoObjetos.add(Objeto); 
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los Subcentros de costos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }  
    public String buscar_nombre(String nombre) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cntro_cost_subcentro] WHERE [ccs_desc] like ?;");
            query.setString(1, nombre);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                return resultSet.getString(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los Subcentros de costos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } 
    public String buscar_Id(String id) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cntro_cost_subcentro] WHERE [ccs_cdgo] =?;");
            query.setString(1, id);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                return resultSet.getString(2);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los Subcentros de costos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } 
    
    
    public boolean validarExistencia(CentroCostoSubCentro Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cntro_cost_subcentro] WHERE [ccs_desc] like ? AND [ccs_cntro_oper_cdgo]=? ;");
            query.setString(1, Objeto.getDescripcion());
            query.setInt(2, Objeto.getCentroOperacion().getCodigo());
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
    public boolean validarExistenciaActualizar(CentroCostoSubCentro Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cntro_cost_subcentro] WHERE [ccs_desc] like ? AND [ccs_cntro_oper_cdgo]=? AND [ccs_cdgo]<> ?;");
            query.setString(1, Objeto.getDescripcion());
            query.setInt(2, Objeto.getCentroOperacion().getCodigo());
            query.setInt(3, Objeto.getCodigo());
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
    public int actualizar(CentroCostoSubCentro Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            CentroCostoSubCentro CentroCostoSubCentroAnterior=buscarEspecifico(""+Objeto.getCodigo());
            String estado="";
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                estado="ACTIVO";
            }else{
                estado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement query= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[cntro_cost_subcentro] SET [ccs_desc]=?, [ccs_estad]=?, [ccs_cntro_oper_cdgo]=?, [ccs_cntro_cost_rquiere_labor_realizda]=? WHERE [ccs_cdgo]=?");
            query.setString(1, Objeto.getDescripcion());
            query.setString(2, Objeto.getEstado());
            query.setInt(3, Objeto.getCentroOperacion().getCodigo());
            query.setString(4, Objeto.getCentroCostoRequiereLaborRealizada());
            query.setInt(5, Objeto.getCodigo());
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
                        "           ,'CENTROCOSTO_SUBCENTRO'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre ',?,' Código: ',?,' Nombre: ',?,' Estado: ',?,' Centro Operacion: ',?,' CentroOperacionRequiereLaborRealizada: ',?,"
                                            + "' actualizando la siguiente informacion a Código: ',?,' Nombre: ',?,' Estado: ',?,' Centro Operacion: ',?,' CentroOperacionRequiereLaborRealizada: ',?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, ""+Objeto.getCodigo());
                Query_Auditoria.setString(6, "el subcentro de costo auxiliar:");
                Query_Auditoria.setInt(7, CentroCostoSubCentroAnterior.getCodigo());
                Query_Auditoria.setString(8, CentroCostoSubCentroAnterior.getDescripcion());
                Query_Auditoria.setString(9, CentroCostoSubCentroAnterior.getEstado());
                Query_Auditoria.setInt(10, CentroCostoSubCentroAnterior.getCentroOperacion().getCodigo());
                Query_Auditoria.setString(11, CentroCostoSubCentroAnterior.getCentroCostoRequiereLaborRealizada());
                Query_Auditoria.setInt(12, Objeto.getCodigo());
                Query_Auditoria.setString(13, Objeto.getDescripcion());
                Query_Auditoria.setString(14, estado);
                Query_Auditoria.setInt(15, Objeto.getCentroOperacion().getCodigo());
                Query_Auditoria.setString(16, Objeto.getCentroCostoRequiereLaborRealizada());
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

