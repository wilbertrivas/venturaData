package Catalogo.Controller;
    
import ConnectionDB.Conexion_DB_costos_vg;
import Catalogo.Model.CentroCostoAuxiliar;
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

public class ControlDB_CentroCostoAuxiliar {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private String tipoConexion;

    public ControlDB_CentroCostoAuxiliar(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);

    }
    public ArrayList<CentroCostoAuxiliar> buscar(String valorConsulta) throws SQLException{
        ArrayList<CentroCostoAuxiliar> listadoObjeto = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            if(valorConsulta.equals("")){
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
                resultSetBuscar= queryBuscar.executeQuery();
            }else{
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
                                                                        "	   INNER JOIN ["+DB+"].[dbo].cntro_oper ON ccs_cntro_oper_cdgo=co_cdgo WHERE [cca_desc] like ?;");
                queryBuscar.setString(1, "%"+valorConsulta+"%");
                resultSetBuscar= queryBuscar.executeQuery();
            }
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
    } 
    
    
    
    public int registrar(CentroCostoAuxiliar Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            String estado;
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                estado="ACTIVO";
            }else{
                estado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryRegistrar= conexion.prepareStatement(" INSERT INTO ["+DB+"].[dbo].[cntro_cost_auxiliar] ([cca_cdgo],[cca_cntro_cost_subcentro_cdgo],[cca_desc],[cca_estad]) VALUES ((SELECT (CASE WHEN (MAX([cca_cdgo]) IS NULL)\n" +
                                                                                                    " THEN 1\n" +
                                                                                                    " ELSE (MAX([cca_cdgo])+1) END)AS [cca_cdgo]\n" +
                                                                                " FROM ["+DB+"].[dbo].[cntro_cost_auxiliar]),?,?,?);");
            queryRegistrar.setInt(1, Objeto.getCentroCostoSubCentro().getCodigo());
            queryRegistrar.setString(2, Objeto.getDescripcion());
            queryRegistrar.setString(3, Objeto.getEstado());
            queryRegistrar.execute();
            result=1;
            if(result==1){
                result=0;
                //Sacamos el ultimo valor 
                PreparedStatement queryMaximo= conexion.prepareStatement("SELECT MAX(cca_cdgo) FROM ["+DB+"].[dbo].[cntro_cost_auxiliar];");
                ResultSet resultSetMaximo= queryMaximo.executeQuery();
                while(resultSetMaximo.next()){ 
                    if(resultSetMaximo.getString(1) != null){
                        Objeto.setCodigo(resultSetMaximo.getInt(1));
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
                        "           ,'CENTROCOSTO_AUXILIAR'" +
                        "           ,CONCAT (?,?,' Nombre: ',?,' Subcentro: ',?, ' Estado:',?));");
                Query_AuditoriaInsert.setString(1, us.getCodigo());
                Query_AuditoriaInsert.setString(2, namePc);
                Query_AuditoriaInsert.setString(3, ipPc);
                Query_AuditoriaInsert.setString(4, macPC);
                Query_AuditoriaInsert.setString(5, ""+Objeto.getCodigo());
                Query_AuditoriaInsert.setString(6, "Se registró un nuevo centro de costos auxiliar en el sistema, con Código: ");
                Query_AuditoriaInsert.setInt(7, Objeto.getCodigo());
                Query_AuditoriaInsert.setString(8, Objeto.getDescripcion());
                Query_AuditoriaInsert.setString(9, Objeto.getCentroCostoSubCentro().getDescripcion());
                Query_AuditoriaInsert.setString(10, estado);
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
    public boolean validarExistencia(CentroCostoAuxiliar Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement queryValidarExistencia= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cntro_cost_auxiliar] WHERE [cca_desc] =? AND [cca_cntro_cost_subcentro_cdgo]=?;");
            queryValidarExistencia.setString(1, Objeto.getDescripcion());
            queryValidarExistencia.setInt(2, Objeto.getCentroCostoSubCentro().getCodigo());
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
    public boolean validarExistenciaActualizar(CentroCostoAuxiliar Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement queryValidarExistencia= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cntro_cost_auxiliar] WHERE [cca_desc] =? AND [cca_cntro_cost_subcentro_cdgo]=? AND [cca_cdgo]<> ?;");
            queryValidarExistencia.setString(1, Objeto.getDescripcion());
            queryValidarExistencia.setInt(2, Objeto.getCentroCostoSubCentro().getCodigo());
            queryValidarExistencia.setInt(3, Objeto.getCodigo());
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
    public int buscar_nombre(String nombre) throws SQLException{
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscarNombre= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cntro_cost_auxiliar] WHERE [cca_desc] like ?;");
            queryBuscarNombre.setString(1, nombre);
            ResultSet resultSetBuscarNombre= queryBuscarNombre.executeQuery();
            while(resultSetBuscarNombre.next()){ 
                return resultSetBuscarNombre.getInt(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los auxiliares de centro de costos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return 0;
    } 
    
    public CentroCostoAuxiliar buscarEspecifico(String codigo) throws SQLException{
        CentroCostoAuxiliar Objeto = null; 
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT cca_cdgo, cca_desc, ccs_cdgo, ccs_desc, ccs_estad, CASE WHEN (cca_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS cca_estad "
                                                + " FROM ["+DB+"].[dbo].[cntro_cost_auxiliar] "
                                                + " INNER JOIN [cntro_cost_subcentro]  ON ccs_cdgo=cca_cntro_cost_subcentro_cdgo WHERE [cca_cdgo] =?;");
            queryBuscar.setString(1, codigo);
            ResultSet resultSetBuscar= queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                Objeto = new CentroCostoAuxiliar(); 
                Objeto.setCodigo(resultSetBuscar.getInt(1));
                Objeto.setDescripcion(resultSetBuscar.getString(2));
                Objeto.setCentroCostoSubCentro(new CentroCostoSubCentro(resultSetBuscar.getInt(3),resultSetBuscar.getString(4),resultSetBuscar.getString(5)));
                Objeto.setEstado(resultSetBuscar.getString(6));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los centro de costros Auxiliares");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return Objeto;
    }
    public CentroCostoAuxiliar buscarId(int codigo) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        CentroCostoAuxiliar Objeto = new CentroCostoAuxiliar(); 
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT cca_cdgo, cca_desc, ccs_cdgo, ccs_desc, ccs_estad, CASE WHEN (cca_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS cca_estad "
                                                + " FROM ["+DB+"].[dbo].[cntro_cost_auxiliar] "
                                                + " INNER JOIN [cntro_cost_subcentro]  ON ccs_cdgo=cca_cntro_cost_subcentro_cdgo  WHERE [cca_cdgo] = ?;");
            queryBuscar.setInt(1, codigo);
            ResultSet resultSetBuscar= queryBuscar.executeQuery();
            while(resultSetBuscar.next()){  
                Objeto.setCodigo(resultSetBuscar.getInt(1));
                Objeto.setDescripcion(resultSetBuscar.getString(2));
                Objeto.setCentroCostoSubCentro(new CentroCostoSubCentro(resultSetBuscar.getInt(3),resultSetBuscar.getString(4),resultSetBuscar.getString(5)));
                Objeto.setEstado(resultSetBuscar.getString(6));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los centro de costos auxiliares");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return Objeto;
    } 
    public ArrayList<CentroCostoAuxiliar> buscarActivos() throws SQLException{
        ArrayList<CentroCostoAuxiliar> listadoObjetos = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT cca_cdgo, cca_desc, ccs_cdgo, ccs_desc, ccs_estad, CASE WHEN (cca_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS cca_estad "
                                                + " FROM ["+DB+"].[dbo].[cntro_cost_auxiliar] "
                                                + " INNER JOIN [cntro_cost_subcentro]  ON ccs_cdgo=cca_cntro_cost_subcentro_cdgo  WHERE [cca_estad]=1;");
            ResultSet resultSetBuscar= queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                CentroCostoAuxiliar Objeto = new CentroCostoAuxiliar(); 
                Objeto.setCodigo(resultSetBuscar.getInt(1));
                Objeto.setDescripcion(resultSetBuscar.getString(2));
                Objeto.setCentroCostoSubCentro(new CentroCostoSubCentro(resultSetBuscar.getInt(3),resultSetBuscar.getString(4),resultSetBuscar.getString(5)));
                Objeto.setEstado(resultSetBuscar.getString(6));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los centro de costos auxiliares");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public int actualizar(CentroCostoAuxiliar Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{ 
            CentroCostoAuxiliar CentroCostoAuxiliarAnterior=buscarEspecifico(""+Objeto.getCodigo());
            String valorEstado="";          
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                valorEstado="ACTIVO";
            }else{
                valorEstado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[cntro_cost_auxiliar] SET  [cca_desc]=?,[cca_estad] =?,[cca_cntro_cost_subcentro_cdgo]=? WHERE [cca_cdgo]=?;");
            queryActualizar.setString(1, Objeto.getDescripcion());
            queryActualizar.setString(2, Objeto.getEstado());
            queryActualizar.setInt(3, Objeto.getCentroCostoSubCentro().getCodigo());
            queryActualizar.setInt(4, Objeto.getCodigo());
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
                        "           ,'CENTROCOSTO_AUXILIAR'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre el centro de costo auxiliar Codigo:',?,' Nombre: ',?,' Subcentro: ',?,' Estado: ',?,' actualizando la siguiente informacion a Código: ',?,' Nombre: ',?,' Subcentro: ',?,' Estado: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, ""+CentroCostoAuxiliarAnterior.getCodigo());
                Query_Auditoria.setInt(6, CentroCostoAuxiliarAnterior.getCodigo());
                Query_Auditoria.setString(7, CentroCostoAuxiliarAnterior.getDescripcion());
                Query_Auditoria.setString(8, CentroCostoAuxiliarAnterior.getCentroCostoSubCentro().getDescripcion());
                Query_Auditoria.setString(9, CentroCostoAuxiliarAnterior.getEstado());
                Query_Auditoria.setInt(10, Objeto.getCodigo());
                Query_Auditoria.setString(11, Objeto.getDescripcion());
                Query_Auditoria.setString(12, Objeto.getCentroCostoSubCentro().getDescripcion());
                Query_Auditoria.setString(13, valorEstado);
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
