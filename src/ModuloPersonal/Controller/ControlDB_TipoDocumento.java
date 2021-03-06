/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloPersonal.Controller;

import ConnectionDB.Conexion_DB_costos_vg;
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

public class ControlDB_TipoDocumento {

    private Connection conexion = null;
    private Conexion_DB_costos_vg control = null;
    private String tipoConexion;

    public ControlDB_TipoDocumento(String tipoConexion) {
        this.tipoConexion = tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    
    public int registrar(TipoDocumento Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
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
                    PreparedStatement Query= conexion.prepareStatement("INSERT INTO [tipo_documento] ([tidoc_cdgo],[tidoc_desc],[tidoc_estad]) VALUES ((SELECT (CASE WHEN (MAX([tidoc_cdgo]) IS NULL)\n" +
                                                                                                    " THEN 1\n" +
                                                                                                    " ELSE (MAX([tidoc_cdgo])+1) END)AS [tidoc_cdgo]\n" +
                                                                                " FROM [tipo_documento]),?,?);");
                   
                    Query.setString(1, Objeto.getDescripcion());
                    Query.setString(2, Objeto.getEstado());
                    Query.execute();
                    result=1;
                    if(result==1){
                        result=0;
                        //Sacamos el ultimo valor 
                        PreparedStatement queryMaximo= conexion.prepareStatement("SELECT MAX(tidoc_cdgo) FROM [tipo_documento];");
                        ResultSet resultSetMaximo= queryMaximo.executeQuery();
                        while(resultSetMaximo.next()){ 
                            if(resultSetMaximo.getString(1) != null){
                                Objeto.setCodigo(resultSetMaximo.getString(1));
                            }
                        }
                        //Extraemos el nombre del Equipo y la IP        
                        String namePc = new ControlDB_Config().getNamePC();
                        String ipPc = new ControlDB_Config().getIpPc();
                        String macPC = new ControlDB_Config().getMacAddress();
                        PreparedStatement Query_Auditoria = conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[auditoria]([au_cdgo]\n" +
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
                        "           ,'[TIPO DOCUMENTO]'" +
                        "           ,CONCAT (?,?,' Nombre: ',?,' Estado: ',?));");
                        Query_Auditoria.setString(1, us.getCodigo());
                        Query_Auditoria.setString(2, namePc);
                        Query_Auditoria.setString(3, ipPc);
                        Query_Auditoria.setString(4, macPC);
                        Query_Auditoria.setString(5, Objeto.getCodigo());
                        Query_Auditoria.setString(6, "'Se registr?? un nuevo tipo de documento en el sistema, con C??digo: '");
                        Query_Auditoria.setString(7, Objeto.getCodigo());
                        Query_Auditoria.setString(8, Objeto.getDescripcion());
                        Query_Auditoria.setString(9, estado);
                        Query_Auditoria.execute();
                        result=1;
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Ya existe un tipo de documento con ese c??digo");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Ya existe un tipo de documento registrado con ese nombre");
            }
        }
        catch (SQLException sqlException ){   
            result=0;
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    } 
    
    
    public ArrayList<TipoDocumento> buscar(String valorConsulta) throws SQLException{
        ArrayList<TipoDocumento> listadoObjetos = new ArrayList();
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            if(valorConsulta.equals("")){
                PreparedStatement query= conexion.prepareStatement("SELECT tidoc_cdgo, tidoc_desc, CASE WHEN (tidoc_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS tidoc_estad FROM ["+DB+"].[dbo].[tipo_documento];");
                resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexion.prepareStatement("SELECT tidoc_cdgo, tidoc_desc, CASE WHEN (tidoc_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS tidoc_estad FROM ["+DB+"].[dbo].[tipo_documento] WHERE [tidoc_desc] like ?;");
                query.setString(1, "%"+valorConsulta+"%");
                resultSet= query.executeQuery();              
            }
            while(resultSet.next()){  
                TipoDocumento Objeto = new TipoDocumento(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los tipos de documento");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public TipoDocumento buscarEspecifico(String codigo) throws SQLException {
        TipoDocumento Objeto = null;
        conexion = control.ConectarBaseDatos();
        String DB = control.getBaseDeDatos();
        try {
            PreparedStatement query = conexion.prepareStatement("SELECT tidoc_cdgo, tidoc_desc, CASE WHEN (tidoc_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS tidoc_estad FROM [" + DB + "].[dbo].[tipo_documento] WHERE [tidoc_cdgo] = ?;");
            query.setString(1, codigo);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                Objeto = new TipoDocumento();
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
            }
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los tipos de documento");
        }
        control.cerrarConexionBaseDatos();
        return Objeto;
    }

    public ArrayList<TipoDocumento> buscarActivos() throws SQLException {
        ArrayList<TipoDocumento> listadoObjetos = null;
        conexion = control.ConectarBaseDatos();
        String DB = control.getBaseDeDatos();
        try {
            PreparedStatement query = conexion.prepareStatement("SELECT tidoc_cdgo, tidoc_desc, CASE WHEN (tidoc_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS tidoc_estad FROM [" + DB + "].[dbo].[tipo_documento] WHERE [tidoc_estad]=1;");
            ResultSet resultSet = query.executeQuery();
            boolean validator = true;
            while (resultSet.next()) {
                if (validator) {
                    listadoObjetos = new ArrayList();
                    validator = false;
                }
                TipoDocumento Objetos = new TipoDocumento();
                Objetos.setCodigo(resultSet.getString(1));
                Objetos.setDescripcion(resultSet.getString(2));
                Objetos.setEstado(resultSet.getString(3));
                listadoObjetos.add(Objetos);
            }
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los tipos de documento");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }

    public String buscar_nombre(String nombre) throws SQLException {
        conexion = control.ConectarBaseDatos();
        String DB = control.getBaseDeDatos();
        try {
            PreparedStatement query = conexion.prepareStatement("SELECT * FROM [" + DB + "].[dbo].[tipo_documento] WHERE [tidoc_desc] like ?;");
            query.setString(1, nombre);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los tipos de contrato");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return "";
    }

    public String buscar_Id(String id) throws SQLException {
        conexion = control.ConectarBaseDatos();
        String DB = control.getBaseDeDatos();
        try {
            PreparedStatement query = conexion.prepareStatement("SELECT * FROM [" + DB + "].[dbo].[tipo_documento] WHERE [tidoc_cdgo] =?;");
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                return resultSet.getString(2);
            }
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los tipos de documento");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return "";
    }

    public boolean validarPorDescripcion(TipoDocumento Objeto) {
        conexion = control.ConectarBaseDatos();
        String DB = control.getBaseDeDatos();
        boolean retorno = false;
        try {
            PreparedStatement query = conexion.prepareStatement("SELECT * FROM [" + DB + "].[dbo].[tipo_documento] WHERE [tidoc_desc] like ?;");
            query.setString(1, Objeto.getDescripcion());
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                retorno = true;
            }
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de buscar");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return retorno;
    }

    public boolean validarPorCodigo(TipoDocumento Objeto) {
        conexion = control.ConectarBaseDatos();
        String DB = control.getBaseDeDatos();
        boolean retorno = false;
        try {
            PreparedStatement query = conexion.prepareStatement("SELECT * FROM [" + DB + "].[dbo].[tipo_documento] WHERE [tidoc_cdgo] like ?;");
            query.setString(1, Objeto.getCodigo());
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                retorno = true;
            }
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al Tratar de buscar");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return retorno;
    }

    public boolean validarExistenciaActualizar(TipoDocumento Objeto) {
        conexion = control.ConectarBaseDatos();
        String DB = control.getBaseDeDatos();
        boolean retorno = false;
        try {
            PreparedStatement query = conexion.prepareStatement("SELECT * FROM [" + DB + "].[dbo].[tipo_documento] WHERE [tidoc_desc] like ? AND [tidoc_cdgo]<> ?;");
            query.setString(1, Objeto.getDescripcion());
            query.setString(2, Objeto.getCodigo());
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                retorno = true;
            }
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de buscar");
        }
        control.cerrarConexionBaseDatos();
        return retorno;
    }

    public int actualizar(TipoDocumento Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException {
        int result = 0;
        try {

            TipoDocumento tipoDocumentoAnterior = buscarEspecifico("" + Objeto.getCodigo());
            String estado = "";
            if (Objeto.getEstado().equalsIgnoreCase("1")) {
                estado = "ACTIVO";
            } else {
                estado = "INACTIVO";
            }
            conexion = control.ConectarBaseDatos();
            String DB = control.getBaseDeDatos();
            PreparedStatement query = conexion.prepareStatement("UPDATE [" + DB + "].[dbo].[tipo_documento] set [tidoc_desc]=?, [tidoc_estad]=? WHERE [tidoc_cdgo]=?");
            query.setString(1, Objeto.getDescripcion());
            query.setString(2, Objeto.getEstado());
            query.setString(3, Objeto.getCodigo());
            query.execute();
            result = 1;
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
                        + "           ,'TIPO DOCUMENTO'"
                        + "           ,CONCAT('Se registr?? una nueva actualizaci??n en el sistema sobre ',?,' C??digo: ',?,' Nombre: ',?,' Estado: ',?,"
                        + "' actualizando la siguiente informacion a C??digo: ',?,' Nombre: ',?,' Estado: ',?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, tipoDocumentoAnterior.getCodigo());
                Query_Auditoria.setString(6, " el Tipo de Documento, ");
                Query_Auditoria.setString(7, tipoDocumentoAnterior.getCodigo());
                Query_Auditoria.setString(8, tipoDocumentoAnterior.getDescripcion());
                Query_Auditoria.setString(9, tipoDocumentoAnterior.getEstado());
                Query_Auditoria.setString(10, Objeto.getCodigo());
                Query_Auditoria.setString(11, Objeto.getDescripcion());
                Query_Auditoria.setString(12, estado);
                Query_Auditoria.execute();
                result = 1;
            }
        } catch (SQLException sqlException) {
            result = 0;
            JOptionPane.showMessageDialog(null, "ERROR al querer insertar los datos.");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return result;
    }

}
