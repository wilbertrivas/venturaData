package Catalogo.Controller; 
 
import ConnectionDB2.Conexion_DB_ccargaGP;
import ConnectionDB2.Conexion_DB_costos_vg;
import Catalogo.Model.Transportadora;
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

public class ControlDB_Transportadora {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private String tipoConexion;
    
    public ControlDB_Transportadora(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    public int registrar(Transportadora Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
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
                PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[transprtdora] ([tr_cdgo],[tr_nit],[tr_desc],[tr_observ],[tr_estad]) VALUES (?,?,?,?,?);");
                Query.setString(1, Objeto.getCodigo());
                Query.setString(2, Objeto.getNit());
                Query.setString(3, Objeto.getDescripcion());
                Query.setString(4, Objeto.getObservacion());
                Query.setString(5, Objeto.getEstado());
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
                        "           ,'TRANSPORADORA'" +
                        "           ,CONCAT (?,?,' Nit: ',?,' Estado: ',?));");
                    Query_Auditoria.setString(1, us.getCodigo());
                    Query_Auditoria.setString(2, namePc);
                    Query_Auditoria.setString(3, ipPc);
                    Query_Auditoria.setString(4, macPC);
                    Query_Auditoria.setString(5, Objeto.getCodigo());
                    Query_Auditoria.setString(6, "Se registró una nueva transportadora en el sistema, con Código: ");
                    Query_Auditoria.setString(7, Objeto.getCodigo());
                    Query_Auditoria.setString(8, Objeto.getNit()+" Nombre: "+Objeto.getDescripcion()+" Obervacion:"+Objeto.getObservacion());
                    Query_Auditoria.setString(9, estado);
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
    public ArrayList<Transportadora> buscar(String valorConsulta) throws SQLException{
        ArrayList<Transportadora> listadoObjetos = new ArrayList();
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            if(valorConsulta.equals("")){
                PreparedStatement query= conexion.prepareStatement("SELECT tr_cdgo,tr_nit,tr_desc,tr_observ,CASE WHEN (tr_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS tr_estad FROM ["+DB+"].[dbo].[transprtdora];");
                resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexion.prepareStatement("SELECT tr_cdgo,tr_nit,tr_desc,tr_observ,CASE WHEN (tr_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS tr_estad FROM ["+DB+"].[dbo].[transprtdora] WHERE (tr_cdgo=? OR tr_nit LIKE ? OR [tr_desc] LIKE ?);");
                query.setString(1, valorConsulta);
                query.setString(2, "%"+valorConsulta+"%");
                query.setString(3, "%"+valorConsulta+"%");
                resultSet= query.executeQuery();              
            }
            while(resultSet.next()){  
                Transportadora Objeto = new Transportadora(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setNit(resultSet.getString(2));
                Objeto.setDescripcion(resultSet.getString(3));
                Objeto.setObservacion(resultSet.getString(4));
                Objeto.setEstado(resultSet.getString(5));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las transportadoras");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public Transportadora buscarEspecifico(String codigo) throws SQLException{
        Transportadora Objeto =null; 
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT tr_cdgo,tr_nit,tr_desc,tr_observ,CASE WHEN (tr_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS tr_estad FROM ["+DB+"].[dbo].[transprtdora] WHERE [tr_cdgo] = ?;"); 
            query.setString(1, codigo);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                Objeto= new Transportadora();
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setNit(resultSet.getString(2));
                Objeto.setDescripcion(resultSet.getString(3));
                Objeto.setObservacion(resultSet.getString(4));
                Objeto.setEstado(resultSet.getString(5));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las transportadora");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return Objeto;
    } 
    public ArrayList<Transportadora> buscarActivos() throws SQLException{
        ArrayList<Transportadora> listadoObjetos = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT tr_cdgo,tr_nit,tr_desc,tr_observ,CASE WHEN (tr_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS tr_estad FROM ["+DB+"].[dbo].[transprtdora] WHERE [tr_estad]=1;");
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                Transportadora Objeto = new Transportadora(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setNit(resultSet.getString(2));
                Objeto.setDescripcion(resultSet.getString(3));
                Objeto.setObservacion(resultSet.getString(4));
                Objeto.setEstado(resultSet.getString(5));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las transportadora");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public String buscar_nombre(String nombre) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[transprtdora] WHERE [tr_desc] like ?;");
            query.setString(1, nombre);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                return resultSet.getString(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las transportadoras");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } 
    public String buscar_Id(String id) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[transprtdora] WHERE [tr_cdgo] =?;");
            query.setString(1, id);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                return resultSet.getString(2);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las transportadoras");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    } 
    public boolean validarExistencia(Transportadora Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[transprtdora] WHERE [tr_cdgo] like ?;");
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
    public boolean validarExistenciaActualizar(Transportadora Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[transprtdora] WHERE [tr_desc] like ? AND [tr_cdgo]<> ?;");
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
    public int actualizar(Transportadora Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            Transportadora TransportadoraAnterior=buscarEspecifico(""+Objeto.getCodigo());
            String estado="";
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                estado="ACTIVO";
            }else{
                estado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement query= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[transprtdora] set [tr_nit]=? , [tr_desc]=?, [tr_observ]=?,[tr_estad]=? WHERE [tr_cdgo]=?");
            query.setString(1, Objeto.getNit());
            query.setString(2, Objeto.getDescripcion());
            query.setString(3, Objeto.getObservacion());
            query.setString(4, Objeto.getEstado());
            query.setString(5, Objeto.getCodigo());
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
                        "           ,'TRANSPORADORA'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre ',?,' Código: ',?,' Nit: ',?,' Nombre: ',?,' Estado: ',?,"
                                            + "' actualizando la siguiente informacion a  Código: ',?,' Nit: ',?,' Nombre: ',?,' Estado: ',?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, TransportadoraAnterior.getCodigo());
                Query_Auditoria.setString(6, "la transportadora :");
                Query_Auditoria.setString(7, TransportadoraAnterior.getCodigo());
                Query_Auditoria.setString(8, TransportadoraAnterior.getNit());
                Query_Auditoria.setString(9, TransportadoraAnterior.getDescripcion());
                Query_Auditoria.setString(10, TransportadoraAnterior.getEstado());
                Query_Auditoria.setString(11, Objeto.getCodigo());
                Query_Auditoria.setString(12, Objeto.getNit());
                Query_Auditoria.setString(13, Objeto.getDescripcion());
                Query_Auditoria.setString(14, estado);
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

     //Consultas Transportadora en Ccarga GP
    public ArrayList<Transportadora> buscarTransportadoraGP(String valor) throws SQLException{
        ArrayList<Transportadora> listadoTransportadora = new ArrayList();
        Conexion_DB_ccargaGP controlGP = new Conexion_DB_ccargaGP(tipoConexion);
        Connection conexionGP=null;
        conexionGP= controlGP.ConectarBaseDatos();
        String DB=controlGP.getBaseDeDatos();
        try{
            ResultSet resultSet;
            if(valor.equalsIgnoreCase("")){
                PreparedStatement query= conexionGP.prepareStatement("SELECT [tr_cdgo]\n" +
                                                                    "      ,[tr_nmbre]\n" +
                                                                    "      ,[tr_email]\n" +
                                                                    "      ,[tr_sgla]\n" +
                                                                    "      ,[tr_nit]\n" +
                                                                    "      ,[tr_drccion]\n" +
                                                                    "      ,[tr_fax]\n" +
                                                                    "      ,[tr_tlfno]\n" +
                                                                    "      ,[tr_rprsntnte_lgal]\n" +
                                                                    "      ,[tr_nmbre_cntcto]\n" +
                                                                    "      ,[tr_fcha_crcion]\n" +
                                                                    "      ,[tr_cdad]\n" +
                                                                    "      ,[tr_actvo]\n" +
                                                                    "      ,[tr_cdgo_altrno]\n" +
                                                                    "      ,[tr_ip1]\n" +
                                                                    "      ,[tr_ip2]\n" +
                                                                    "      ,[tr_ip3]\n" +
                                                                    "  FROM ["+DB+"].[dbo].[trnsprtdra] ORDER BY tr_cdgo ASC");
                    resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexionGP.prepareStatement("SELECT [tr_cdgo]\n" +
                                                                    "      ,[tr_nmbre]\n" +
                                                                    "      ,[tr_email]\n" +
                                                                    "      ,[tr_sgla]\n" +
                                                                    "      ,[tr_nit]\n" +
                                                                    "      ,[tr_drccion]\n" +
                                                                    "      ,[tr_fax]\n" +
                                                                    "      ,[tr_tlfno]\n" +
                                                                    "      ,[tr_rprsntnte_lgal]\n" +
                                                                    "      ,[tr_nmbre_cntcto]\n" +
                                                                    "      ,[tr_fcha_crcion]\n" +
                                                                    "      ,[tr_cdad]\n" +
                                                                    "      ,[tr_actvo]\n" +
                                                                    "      ,[tr_cdgo_altrno]\n" +
                                                                    "      ,[tr_ip1]\n" +
                                                                    "      ,[tr_ip2]\n" +
                                                                    "      ,[tr_ip3]\n" +
                                                                    "  FROM ["+DB+"].[dbo].[trnsprtdra] WHERE ([tr_cdgo] LIKE ? OR [tr_nmbre] LIKE ?) ORDER BY tr_nmbre ASC");
                query.setString(1, "%"+valor+"%");
                query.setString(2, "%"+valor+"%");
                resultSet= query.executeQuery();
            }
            while(resultSet.next()){ 
                Transportadora Objeto = new Transportadora(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setNit(resultSet.getString(5));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setObservacion("Cargado desde el ControlCarga de GP");
                Objeto.setEstado("1");
                listadoTransportadora.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las transportadoras en ccarga GP");
            sqlException.printStackTrace();
        } 
        controlGP.cerrarConexionBaseDatos();
        return listadoTransportadora;
    } 
}
