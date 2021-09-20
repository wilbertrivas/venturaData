package Catalogo.Controller;
     
import Catalogo.Model.BaseDatos;
import ConnectionDB.Conexion_DB_ccargaGP;
import ConnectionDB.Conexion_DB_costos_vg;
import Catalogo.Model.Cliente;
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
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ControlDB_Cliente {
    private Conexion_DB_costos_vg  control_vg;
    private Conexion_DB_ccargaGP  control_gp;
    private Conexion_DB_ccargaOPP  control_opp;
    private Connection conexion=null;
    
    public ControlDB_Cliente(String tipoConexion){
        control_vg = new Conexion_DB_costos_vg(tipoConexion);
        control_gp = new Conexion_DB_ccargaGP(tipoConexion);
        control_opp = new Conexion_DB_ccargaOPP(tipoConexion);
    }
    public int registrar(Cliente Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        String DB=control_vg.getBaseDeDatos();
        int result=0;
        try{
            conexion= control_vg.ConectarBaseDatos();
            String estadoObjeto="";
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                estadoObjeto="ACTIVO";
            }else{
                if(Objeto.getEstado().equalsIgnoreCase("0")){
                    estadoObjeto="INACTIVO";
                }else{     
                    estadoObjeto="NULL";
                }
            }
            if(!validarExistencia(Objeto)){
                conexion= control_vg.ConectarBaseDatos();
                if(Objeto.getCodigo().equalsIgnoreCase("") || Objeto.getDescripcion().equalsIgnoreCase("") || 
                   Objeto.getEstado() ==null || Objeto.getEstado().equalsIgnoreCase("")){
                }else{
                    PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[cliente] ([cl_cdgo],[cl_desc],[cl_estad],[cl_base_datos_cdgo]) VALUES (?,?,?,?);");
                    Query.setString(1, Objeto.getCodigo());
                    Query.setString(2, Objeto.getDescripcion());
                    Query.setString(3, Objeto.getEstado());
                    Query.setString(4, Objeto.getBaseDatos().getCodigo());
                    Query.execute();
                    result=1;
                    if(result==1){
                        System.out.println("Registrado");
                        try{
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
                        "           ,'CLIENTE'" +
                        "           ,CONCAT (?,?,' Nombre: ',?,' Estado: ',?, 'BaseDatos: ',?));");
                            Query_Auditoria.setString(1, us.getCodigo());
                            Query_Auditoria.setString(2, namePc);
                            Query_Auditoria.setString(3, ipPc);
                            Query_Auditoria.setString(4, macPC);
                            Query_Auditoria.setString(5, Objeto.getCodigo());
                            Query_Auditoria.setString(6, "Se registró un nuevo cliente en el sistema, con Código: ");
                            Query_Auditoria.setString(7, Objeto.getCodigo());
                            Query_Auditoria.setString(8, Objeto.getDescripcion());
                            Query_Auditoria.setString(9, estadoObjeto);
                            Query_Auditoria.setString(10, Objeto.getBaseDatos().getNombre());
                            Query_Auditoria.execute();
                            result=1;
                            if(result==1){
                                registrarCliente_recobro(Objeto,us);
                            }
                        }catch (SQLException sqlException){
                            JOptionPane.showMessageDialog(null, "Error al Tratar de buscar");
                            sqlException.printStackTrace();
                            System.exit(1);
                        } 
                    }
                }
            }  
        }
        catch (SQLException sqlException ){   
            result=0;
            sqlException.printStackTrace();
        }  
        control_vg.cerrarConexionBaseDatos();
        return result;
    } 
    public int registrarCliente_recobro(Cliente Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        String DB=control_vg.getBaseDeDatos();
        int result=0;
        try{
            conexion= control_vg.ConectarBaseDatos();
            PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[cliente_recobro] ([clr_cdgo],[clr_cliente_cdgo],[clr_usuario_cdgo],[clr_valor_recobro],[clr_fecha_registro],[clr_cliente_base_datos_cdgo]) VALUES ((SELECT (CASE WHEN (MAX([clr_cdgo]) IS NULL)\n" +
                                                            " THEN 1\n" +
                                                            " ELSE (MAX([clr_cdgo])+1) END)AS [clr_cdgo]\n" +
                                                            " FROM ["+DB+"].[dbo].[cliente_recobro]),?,?,?,(SELECT SYSDATETIME()),?);");
            Query.setString(1, Objeto.getCodigo());
            Query.setString(2, us.getCodigo());
            Query.setInt(3, Objeto.getValorRecobro());
            Query.setString(4, Objeto.getBaseDatos().getCodigo());
            Query.execute();
            result=1;
            //Procedemos a registrar en la tabla Auditoria
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
                        "           ,'CLIENTE'" +
                        "           ,CONCAT (?,?,' Nombre: ',?,' ValorRecobro: ',?,'BaseDatos: ',?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, Objeto.getCodigo());
                Query_Auditoria.setString(6, "Se registró un recobro en el sistema para el cliente Código: ");
                Query_Auditoria.setString(7, Objeto.getCodigo());
                Query_Auditoria.setString(8, Objeto.getDescripcion());
                Query_Auditoria.setInt(9, Objeto.getValorRecobro());
                Query_Auditoria.setString(10, Objeto.getBaseDatos().getNombre());
                Query_Auditoria.execute();
                result=1;
            }
        }
        catch (SQLException sqlException ){   
            result=0;
            sqlException.printStackTrace();
        }  
        control_vg.cerrarConexionBaseDatos();
        return result;
    } 
    public ArrayList<Cliente> buscar(String valorConsulta) throws SQLException{
        String DB=control_vg.getBaseDeDatos();
        ArrayList<Cliente> listadoCliente = new ArrayList();
        conexion= control_vg.ConectarBaseDatos();
        try{
            ResultSet resultSet;
            if(valorConsulta.equals("")){
                PreparedStatement query= conexion.prepareStatement("  SELECT\n" +
                                                                    "    cl_cdgo, --1\n" +
                                                                    "    cl_desc, --2\n" +
                                                                    "    (CASE WHEN (cl_estad=1) THEN 'ACTIVO'  ELSE 'INACTIVO' END) AS cl_estad, --3\n" +
                                                                    "    m_clr_valor_recobro as Valor_Recobro, --4\n" +
                                                                    "    [cl_base_datos_cdgo],  --5\n" +
                                                                    "	[bd_cdgo],--6\n" +
                                                                    "    [bd_name],--7\n" +
                                                                    "    [bd_desc],--8\n" +
                                                                    "    [bd_estad]  --9        \n" +
                                                                    "FROM  (SELECT DISTINCT [clr_cliente_cdgo] AS m_clr_cliente_cdgo, MAX([clr_valor_recobro]) AS m_clr_valor_recobro\n" +
                                                                    "		FROM ["+DB+"].[dbo].[cliente_recobro] GROUP BY (clr_cliente_cdgo))  tableMayorValor  \n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cliente] ON [cliente].cl_cdgo = tableMayorValor.m_clr_cliente_cdgo\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[base_datos] ON [cl_base_datos_cdgo]=[bd_cdgo]");
                resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexion.prepareStatement(" SELECT\n" +
                                                                    "    cl_cdgo, --1\n" +
                                                                    "    cl_desc, --2\n" +
                                                                    "    (CASE WHEN (cl_estad=1) THEN 'ACTIVO'  ELSE 'INACTIVO' END) AS cl_estad, --3\n" +
                                                                    "    m_clr_valor_recobro as Valor_Recobro, --4\n" +
                                                                    "    [cl_base_datos_cdgo],  --5\n" +
                                                                    "	[bd_cdgo],--6\n" +
                                                                    "    [bd_name],--7\n" +
                                                                    "    [bd_desc],--8\n" +
                                                                    "    [bd_estad]  --9        \n" +
                                                                    " FROM  (SELECT DISTINCT [clr_cliente_cdgo] AS m_clr_cliente_cdgo, MAX([clr_valor_recobro]) AS m_clr_valor_recobro\n" +
                                                                    "   FROM ["+DB+"].[dbo].[cliente_recobro] GROUP BY (clr_cliente_cdgo))  tableMayorValor  \n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cliente] ON [cliente].cl_cdgo = tableMayorValor.m_clr_cliente_cdgo\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[base_datos] ON [cl_base_datos_cdgo]=[bd_cdgo] WHERE [cl_desc] LIKE ? OR [cl_cdgo] LIKE ?;");
                query.setString(1, "%"+valorConsulta+"%");
                query.setString(2, "%"+valorConsulta+"%");
                resultSet= query.executeQuery();
            }
            while(resultSet.next()){ 
                Cliente Objeto = new Cliente(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                Objeto.setValorRecobro(resultSet.getInt(4));
                    BaseDatos baseDatos = new BaseDatos();
                        baseDatos.setCodigo(resultSet.getString(6));
                        baseDatos.setNombre(resultSet.getString(7));
                        baseDatos.setDescripcion(resultSet.getString(8));
                        baseDatos.setEstado(resultSet.getString(9));
                Objeto.setBaseDatos(baseDatos);
                listadoCliente.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los clientes");
            sqlException.printStackTrace();
        } 
        control_vg.cerrarConexionBaseDatos();
        return listadoCliente;
    } 
    public Cliente buscarClienteEspecifico(String cdgoCliente) throws SQLException{
        String DB=control_vg.getBaseDeDatos();
        Cliente Objeto = null; 
        conexion= control_vg.ConectarBaseDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT\n" +
                                                                    "    cl_cdgo, --1\n" +
                                                                    "    cl_desc, --2\n" +
                                                                    "    (CASE WHEN (cl_estad=1) THEN 'ACTIVO'  ELSE 'INACTIVO' END) AS cl_estad, --3\n" +
                                                                    "    m_clr_valor_recobro as Valor_Recobro, --4\n" +
                                                                    "    [cl_base_datos_cdgo],  --5\n" +
                                                                    "	[bd_cdgo],--6\n" +
                                                                    "    [bd_name],--7\n" +
                                                                    "    [bd_desc],--8\n" +
                                                                    "    [bd_estad]  --9        \n" +
                                                                    " FROM  (SELECT DISTINCT [clr_cliente_cdgo] AS m_clr_cliente_cdgo, MAX([clr_valor_recobro]) AS m_clr_valor_recobro\n" +
                                                                    "   FROM ["+DB+"].[dbo].[cliente_recobro] GROUP BY (clr_cliente_cdgo))  tableMayorValor  \n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cliente] ON [cliente].cl_cdgo = tableMayorValor.m_clr_cliente_cdgo\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[base_datos] ON [cl_base_datos_cdgo]=[bd_cdgo] WHERE [cl_cdgo] = ?;");
            query.setString(1, cdgoCliente);
            ResultSet resultSet= query.executeQuery();  
            while(resultSet.next()){ 
                Objeto = new Cliente(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                Objeto.setValorRecobro(resultSet.getInt(4));
                    BaseDatos baseDatos = new BaseDatos();
                        baseDatos.setCodigo(resultSet.getString(6));
                        baseDatos.setNombre(resultSet.getString(7));
                        baseDatos.setDescripcion(resultSet.getString(8));
                        baseDatos.setEstado(resultSet.getString(9));
                Objeto.setBaseDatos(baseDatos);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los clientes");
            sqlException.printStackTrace();
        } 
        control_vg.cerrarConexionBaseDatos();
        return Objeto;
    } 
    public ArrayList<Cliente> buscarCliente_EstadoActivo(String valorBusqueda) throws SQLException{
        String DB=control_vg.getBaseDeDatos();
        ArrayList<Cliente> listadoCliente = new ArrayList();
        conexion= control_vg.ConectarBaseDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT\n" +
                                                                    "    cl_cdgo, --1\n" +
                                                                    "    cl_desc, --2\n" +
                                                                    "    (CASE WHEN (cl_estad=1) THEN 'ACTIVO'  ELSE 'INACTIVO' END) AS cl_estad, --3\n" +
                                                                    "    m_clr_valor_recobro as Valor_Recobro, --4\n" +
                                                                    "    [cl_base_datos_cdgo],  --5\n" +
                                                                    "	[bd_cdgo],--6\n" +
                                                                    "    [bd_name],--7\n" +
                                                                    "    [bd_desc],--8\n" +
                                                                    "    [bd_estad]  --9        \n" +
                                                                    " FROM  (SELECT DISTINCT [clr_cliente_cdgo] AS m_clr_cliente_cdgo, MAX([clr_valor_recobro]) AS m_clr_valor_recobro\n" +
                                                                    "   FROM ["+DB+"].[dbo].[cliente_recobro] GROUP BY (clr_cliente_cdgo))  tableMayorValor  \n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cliente] ON [cliente].cl_cdgo = tableMayorValor.m_clr_cliente_cdgo\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[base_datos] ON [cl_base_datos_cdgo]=[bd_cdgo]  WHERE [cl_estad] =1 AND [cl_desc] LIKE ? ;");
            query.setString(1, "%"+valorBusqueda+"%");
            ResultSet resultSet= query.executeQuery();  
            
            while(resultSet.next()){ 
                Cliente Objeto = new Cliente(); 
                Objeto = new Cliente(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                Objeto.setValorRecobro(resultSet.getInt(4));
                    BaseDatos baseDatos = new BaseDatos();
                        baseDatos.setCodigo(resultSet.getString(6));
                        baseDatos.setNombre(resultSet.getString(7));
                        baseDatos.setDescripcion(resultSet.getString(8));
                        baseDatos.setEstado(resultSet.getString(9));
                Objeto.setBaseDatos(baseDatos);
                listadoCliente.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los clientes");
            sqlException.printStackTrace();
        } 
        control_vg.cerrarConexionBaseDatos();
        return listadoCliente;
    } 
    public ArrayList<Cliente> buscarClienteActivos() throws SQLException{
        String DB=control_vg.getBaseDeDatos();
        ArrayList<Cliente> listadoCliente = new ArrayList();
        conexion= control_vg.ConectarBaseDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT\n" +
                                                                    "    cl_cdgo, --1\n" +
                                                                    "    cl_desc, --2\n" +
                                                                    "    (CASE WHEN (cl_estad=1) THEN 'ACTIVO'  ELSE 'INACTIVO' END) AS cl_estad, --3\n" +
                                                                    "    m_clr_valor_recobro as Valor_Recobro, --4\n" +
                                                                    "    [cl_base_datos_cdgo],  --5\n" +
                                                                    "	[bd_cdgo],--6\n" +
                                                                    "    [bd_name],--7\n" +
                                                                    "    [bd_desc],--8\n" +
                                                                    "    [bd_estad]  --9        \n" +
                                                                    " FROM  (SELECT DISTINCT [clr_cliente_cdgo] AS m_clr_cliente_cdgo, MAX([clr_valor_recobro]) AS m_clr_valor_recobro\n" +
                                                                    "   FROM ["+DB+"].[dbo].[cliente_recobro] GROUP BY (clr_cliente_cdgo))  tableMayorValor  \n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[cliente] ON [cliente].cl_cdgo = tableMayorValor.m_clr_cliente_cdgo\n" +
                                                                    "	INNER JOIN ["+DB+"].[dbo].[base_datos] ON [cl_base_datos_cdgo]=[bd_cdgo] WHERE [cl_estad] like 'ACTIVO';");
            ResultSet resultSet= query.executeQuery();  
            while(resultSet.next()){ 
                Cliente Objeto = new Cliente(); 
                Objeto = new Cliente(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                Objeto.setValorRecobro(resultSet.getInt(4));
                    BaseDatos baseDatos = new BaseDatos();
                        baseDatos.setCodigo(resultSet.getString(6));
                        baseDatos.setNombre(resultSet.getString(7));
                        baseDatos.setDescripcion(resultSet.getString(8));
                        baseDatos.setEstado(resultSet.getString(9));
                Objeto.setBaseDatos(baseDatos);
                listadoCliente.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los clientes");
            sqlException.printStackTrace();
        } 
        control_vg.cerrarConexionBaseDatos();
        return listadoCliente;
    } 
    /*public String buscar_nombre(String nombreCliente) throws SQLException{
        String DB=control_vg.getBaseDeDatos();
        conexion= control_vg.ConectarBaseDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cliente] WHERE [cl_desc] like ?;");
            query.setString(1, "%"+nombreCliente+"%");
            ResultSet resultSet= query.executeQuery();
            
            while(resultSet.next()){ 
                return resultSet.getString(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los clientes");
            sqlException.printStackTrace();
        } 
        control_vg.cerrarConexionBaseDatos();
        return "";
    } */
    /*public String buscar_Id(String id) throws SQLException{
        String DB=control_vg.getBaseDeDatos();
        conexion= control_vg.ConectarBaseDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cliente] WHERE [cl_cdgo] =?;");
            query.setString(1, id);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                return resultSet.getString(2);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los clientes");
            sqlException.printStackTrace();
        } 
        control_vg.cerrarConexionBaseDatos();
        return "";
    } */
    public boolean validarExistencia(Cliente Objeto){
        String DB=control_vg.getBaseDeDatos();
        conexion= control_vg.ConectarBaseDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cliente] WHERE [cl_cdgo] like ? AND [cl_base_datos_cdgo]=?;");
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
        control_vg.cerrarConexionBaseDatos();
        return retorno;
    }  
    public int actualizar(Cliente cl, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        String DB=control_vg.getBaseDeDatos();
        conexion= control_vg.ConectarBaseDatos();
        int result=0;
        try{
            Cliente clienteAnterior= buscarClienteEspecifico(cl.getCodigo());
            if(clienteAnterior !=null){
                PreparedStatement query= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[cliente] set [cl_desc]=?', [cl_estad]=? WHERE [cl_cdgo]=? AND [cl_base_datos_cdgo]=?;");
                query.setString(1, cl.getDescripcion());
                query.setString(2, cl.getEstado());
                query.setString(3, cl.getCodigo());
                query.setString(4, cl.getBaseDatos().getCodigo());
                query.execute();
                result=1;
                //Procedemos a registrar en la tabla Auditoria
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
                        "           ,'CLIENTE'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre ',?,' Código: ',?,' Nombre: ',?,' Estado: ',?,"
                                            + "' actualizando la siguiente informacion a Código: ',?,' Nombre: ',?,' Estado: ',?, ' BaseDatos: ',?));");
                    Query_Auditoria.setString(1, us.getCodigo());
                    Query_Auditoria.setString(2, namePc);
                    Query_Auditoria.setString(3, ipPc);
                    Query_Auditoria.setString(4, macPC);
                    Query_Auditoria.setString(5, clienteAnterior.getCodigo());
                    Query_Auditoria.setString(6, "el cliente con");
                    Query_Auditoria.setString(7, clienteAnterior.getCodigo());
                    Query_Auditoria.setString(8, clienteAnterior.getDescripcion());
                    Query_Auditoria.setString(9, clienteAnterior.getEstado());
                    Query_Auditoria.setString(10, cl.getCodigo());
                    Query_Auditoria.setString(11, cl.getDescripcion());
                    Query_Auditoria.setString(12, cl.getEstado());
                    Query_Auditoria.setString(13, cl.getBaseDatos().getCodigo());
                    Query_Auditoria.execute();
                    result=1;
                }
            }
        }
        catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "ERROR al querer actualizar los datos.");
            sqlException.printStackTrace();
        }  
        control_vg.cerrarConexionBaseDatos();
        return result;
    } 
    public ArrayList<String> buscarHistorialRecobros(String valorConsulta) throws SQLException{
        String DB=control_vg.getBaseDeDatos();
        ArrayList<String> historicoRecobro = new ArrayList();
        conexion= control_vg.ConectarBaseDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT TOP 1000 CONCAT(us.us_nombres,' ', us.us_apellidos) AS nombre_usuario, clr.clr_valor_recobro, clr.clr_fecha_registro	" +
                                                                    "  FROM ["+DB+"].[dbo].[cliente_recobro] clr " +
                                                                    "	INNER JOIN "+DB+".dbo.cliente cl ON clr.clr_cliente_cdgo=cl.cl_cdgo " +
                                                                    "	INNER JOIN "+DB+".dbo.usuario us ON clr.clr_usuario_cdgo=us.us_cdgo  " +
                                                                    "  WHERE clr_cliente_cdgo=? ORDER BY [clr_fecha_registro] DESC");
            query.setString(1, valorConsulta);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                String nombreUsuario=resultSet.getString(1);
                String valorRecobro=resultSet.getString(2);
                String fechaRegistroRecobro=resultSet.getString(3);      
                historicoRecobro.add(nombreUsuario+"#"+valorRecobro+"#"+fechaRegistroRecobro);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los clientes");
            sqlException.printStackTrace();
        } 
        control_vg.cerrarConexionBaseDatos();
        return historicoRecobro;
    } 
    
    //Consultas Clientes en Ccarga GP
    public ArrayList<Cliente> buscarClientesGP(String valor) throws SQLException{
        ArrayList<Cliente> listadoCliente = new ArrayList();
        String DB=control_gp.getBaseDeDatos();
        Connection conexionGP=null;
        conexionGP= control_gp.ConectarBaseDatos();
        try{
            ResultSet resultSet;
            if(valor.equalsIgnoreCase("")){
                PreparedStatement query= conexionGP.prepareStatement("SELECT [cl_cdgo]\n" +
                                                        "      ,[cl_nmbre],[cl_actvo]\n" +
                                                        "      ,[cl_drccion]\n" +
                                                        "      ,[cl_email]\n" +
                                                        "      ,[cl_nit]\n" +
                                                        "      ,[cl_srvcio_psje]\n" +
                                                        "      ,[cl_tlfno]\n" +
                                                        "      ,[cl_fax]\n" +
                                                        "      ,[cl_cdad]\n" +
                                                        "      ,[cl_rprsntnte_lgal]\n" +
                                                        "      ,[cl_rzon_scial]\n" +
                                                        "      ,[cl_fcha_crcion]\n" +
                                                        "      ,[cl_nmbre_cntcto]\n" +
                                                        "      ,[cl_id_grpo]\n" +
                                                        "      ,[cl_id_grpo2]\n" +
                                                        "      ,[cl_es_clnte]\n" +
                                                        "      ,[cl_es_agnte_mrtmo]\n" +
                                                        "      ,[cl_es_asgrdra]\n" +
                                                        "      ,[cl_es_prvdor]\n" +
                                                        "      ,[cl_trcro]\n" +
                                                        "      ,[cl_crpta_dcmntos]\n" +
                                                        "      ,[cl_grpo_clnte]\n" +
                                                        "  FROM ["+DB+"].[dbo].[clnte] ORDER BY cl_cdgo ASC");
                    resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexionGP.prepareStatement("SELECT [cl_cdgo]\n" +
                                                        "      ,[cl_nmbre],[cl_actvo]\n" +
                                                        "      ,[cl_drccion]\n" +
                                                        "      ,[cl_email]\n" +
                                                        "      ,[cl_nit]\n" +
                                                        "      ,[cl_srvcio_psje]\n" +
                                                        "      ,[cl_tlfno]\n" +
                                                        "      ,[cl_fax]\n" +
                                                        "      ,[cl_cdad]\n" +
                                                        "      ,[cl_rprsntnte_lgal]\n" +
                                                        "      ,[cl_rzon_scial]\n" +
                                                        "      ,[cl_fcha_crcion]\n" +
                                                        "      ,[cl_nmbre_cntcto]\n" +
                                                        "      ,[cl_id_grpo]\n" +
                                                        "      ,[cl_id_grpo2]\n" +
                                                        "      ,[cl_es_clnte]\n" +
                                                        "      ,[cl_es_agnte_mrtmo]\n" +
                                                        "      ,[cl_es_asgrdra]\n" +
                                                        "      ,[cl_es_prvdor]\n" +
                                                        "      ,[cl_trcro]\n" +
                                                        "      ,[cl_crpta_dcmntos]\n" +
                                                        "      ,[cl_grpo_clnte]\n" +
                                                        "  FROM ["+DB+"].[dbo].[clnte] WHERE ([cl_cdgo] LIKE ? OR [cl_nmbre] LIKE ?) ORDER BY cl_cdgo ASC");
                query.setString(1, "%"+valor+"%");
                query.setString(2, "%"+valor+"%");
                resultSet= query.executeQuery();
            }
            while(resultSet.next()){ 
                Cliente Objeto = new Cliente(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                Objeto.setValorRecobro(0);
                Objeto.setBaseDatos(new BaseDatos("1"));
                listadoCliente.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los clientes en ccarga GP");
            sqlException.printStackTrace();
        } 
        control_gp.cerrarConexionBaseDatos();
        return listadoCliente;
    } 
    public ArrayList<Cliente> buscarClientesOPP(String valor) throws SQLException{
        ArrayList<Cliente> listadoCliente = new ArrayList();
        String DB=control_opp.getBaseDeDatos();
        Connection conexionGP=null;
        conexionGP= control_opp.ConectarBaseDatos();
        try{
            ResultSet resultSet;
            if(valor.equalsIgnoreCase("")){
                PreparedStatement query= conexionGP.prepareStatement("SELECT [cl_cdgo]\n" +
                                                        "      ,[cl_nmbre],[cl_actvo]\n" +
                                                        "      ,[cl_drccion]\n" +
                                                        "      ,[cl_email]\n" +
                                                        "      ,[cl_nit]\n" +
                                                        "      ,[cl_srvcio_psje]\n" +
                                                        "      ,[cl_tlfno]\n" +
                                                        "      ,[cl_fax]\n" +
                                                        "      ,[cl_cdad]\n" +
                                                        "      ,[cl_rprsntnte_lgal]\n" +
                                                        "      ,[cl_rzon_scial]\n" +
                                                        "      ,[cl_fcha_crcion]\n" +
                                                        "      ,[cl_nmbre_cntcto]\n" +
                                                        "      ,[cl_id_grpo]\n" +
                                                        "      ,[cl_id_grpo2]\n" +
                                                        "      ,[cl_es_clnte]\n" +
                                                        "      ,[cl_es_agnte_mrtmo]\n" +
                                                        "      ,[cl_es_asgrdra]\n" +
                                                        "      ,[cl_es_prvdor]\n" +
                                                        "      ,[cl_trcro]\n" +
                                                        "      ,[cl_crpta_dcmntos]\n" +
                                                        "      ,[cl_grpo_clnte]\n" +
                                                        "  FROM ["+DB+"].[dbo].[clnte] ORDER BY cl_cdgo ASC");
                    resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexionGP.prepareStatement("SELECT [cl_cdgo]\n" +
                                                        "      ,[cl_nmbre],[cl_actvo]\n" +
                                                        "      ,[cl_drccion]\n" +
                                                        "      ,[cl_email]\n" +
                                                        "      ,[cl_nit]\n" +
                                                        "      ,[cl_srvcio_psje]\n" +
                                                        "      ,[cl_tlfno]\n" +
                                                        "      ,[cl_fax]\n" +
                                                        "      ,[cl_cdad]\n" +
                                                        "      ,[cl_rprsntnte_lgal]\n" +
                                                        "      ,[cl_rzon_scial]\n" +
                                                        "      ,[cl_fcha_crcion]\n" +
                                                        "      ,[cl_nmbre_cntcto]\n" +
                                                        "      ,[cl_id_grpo]\n" +
                                                        "      ,[cl_id_grpo2]\n" +
                                                        "      ,[cl_es_clnte]\n" +
                                                        "      ,[cl_es_agnte_mrtmo]\n" +
                                                        "      ,[cl_es_asgrdra]\n" +
                                                        "      ,[cl_es_prvdor]\n" +
                                                        "      ,[cl_trcro]\n" +
                                                        "      ,[cl_crpta_dcmntos]\n" +
                                                        "      ,[cl_grpo_clnte]\n" +
                                                        "  FROM ["+DB+"].[dbo].[clnte] WHERE ([cl_cdgo] LIKE ? OR [cl_nmbre] LIKE ?) ORDER BY cl_cdgo ASC");
                query.setString(1, "%"+valor+"%");
                query.setString(2, "%"+valor+"%");
                resultSet= query.executeQuery();
            }
            while(resultSet.next()){ 
                Cliente Objeto = new Cliente(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setDescripcion(resultSet.getString(2));
                Objeto.setEstado(resultSet.getString(3));
                Objeto.setValorRecobro(0);
                Objeto.setBaseDatos(new BaseDatos("2"));
                listadoCliente.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los clientes en ccarga GP");
            sqlException.printStackTrace();
        } 
        control_opp.cerrarConexionBaseDatos();
        return listadoCliente;
    } 
}
