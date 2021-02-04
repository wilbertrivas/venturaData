package Catalogo.Controller;
    
import Catalogo.Model.CentroCosto;
import Catalogo.Model.CentroCostoAuxiliar;
import ConnectionDB2.Conexion_DB_costos_vg;
import Catalogo.Model.CentroCostoMayor;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import Catalogo.Model.Cliente;
import Catalogo.Model.LaborRealizada;
import ModuloEquipo.Model.MvtoEquipo;
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

public class ControlDB_CentroCostoMayor {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private final String tipoConexion;

    public ControlDB_CentroCostoMayor(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);

    }   
    public int registrar(CentroCostoMayor Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            String estado;
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                estado="ACTIVO";
            }else{
                estado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            if(validarExistencia(Objeto)){
                result=0;
                JOptionPane.showMessageDialog(null, "Ya existe un centro de costo Mayor registrado y activo para ese SubcentroCosto", "Error!!", JOptionPane.ERROR_MESSAGE);
            }else{
                conexion= control.ConectarBaseDatos();
                String DB=control.getBaseDeDatos();
                PreparedStatement queryRegistrar= conexion.prepareStatement(" INSERT INTO ["+DB+"].[dbo].[cntro_cost_mayor] ( [ccm_cdgo]\n" +
                                                                                                                "      ,[ccm_cliente_cdgo]\n" +
                                                                                                                "      ,[ccm_cntro_cost_subcentro_cdgo]\n" +
                                                                                                                "      ,[ccm_desc]\n" +
                                                                                                                "      ,[ccm_estad]) VALUES ((SELECT (CASE WHEN (MAX([ccm_cdgo]) IS NULL)\n" +
                                                                                                        " THEN 1\n" +
                                                                                                        " ELSE (MAX([ccm_cdgo])+1) END)AS [ccm_cdgo]\n" +
                                                                                    " FROM ["+DB+"].[dbo].[cntro_cost_mayor]),?,?,?,?);");
                queryRegistrar.setString(1, Objeto.getCliente().getCodigo());
                queryRegistrar.setInt(2, Objeto.getCentroCostoSubcentro().getCodigo());
                queryRegistrar.setString(3, Objeto.getDescripcion());
                queryRegistrar.setString(4, Objeto.getEstado());
                queryRegistrar.execute();
                result=1;
                if(result==1){
                    result=0;
                    //Sacamos el ultimo valor 
                    PreparedStatement queryMaximo= conexion.prepareStatement("SELECT MAX(ccm_cdgo) FROM ["+DB+"].[dbo].[cntro_cost_mayor];");
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
                            "           ,'CENTROCOSTO_MAYOR'" +
                            "           ,CONCAT (?,?,' Nombre: ',?,' Cliente: ',?, ' SubcentroCosto:',?, ' Estado:',?));");
                    Query_AuditoriaInsert.setString(1, us.getCodigo());
                    Query_AuditoriaInsert.setString(2, namePc);
                    Query_AuditoriaInsert.setString(3, ipPc);
                    Query_AuditoriaInsert.setString(4, macPC);
                    Query_AuditoriaInsert.setString(5, ""+Objeto.getCodigo());
                    Query_AuditoriaInsert.setString(6, "Se registró un nuevo centro de costos mayor en el sistema, con Código: ");
                    Query_AuditoriaInsert.setString(7, Objeto.getCodigo());
                    Query_AuditoriaInsert.setString(8, Objeto.getDescripcion());
                    Query_AuditoriaInsert.setString(9, Objeto.getCliente().getDescripcion());
                    Query_AuditoriaInsert.setString(10, Objeto.getCentroCostoSubcentro().getDescripcion());
                    Query_AuditoriaInsert.setString(11, Objeto.getEstado());
                    Query_AuditoriaInsert.execute();
                    result=1; 
                }
            }
        }catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "ERROR al querer insertar los datos.");
            sqlException.printStackTrace();
        }   
        control.cerrarConexionBaseDatos();
        return result;
    }
    public boolean validarExistencia(CentroCostoMayor Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement queryValidarExistencia= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cntro_cost_mayor] WHERE "
                                                                                                + " [ccm_cliente_cdgo] =? "
                                                                                                + " AND [ccm_cntro_cost_subcentro_cdgo]=? "
                                                                                                + " AND [ccm_estad]=1 ;");
            queryValidarExistencia.setString(1, Objeto.getCliente().getCodigo());
            queryValidarExistencia.setInt(2, Objeto.getCentroCostoSubcentro().getCodigo());
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
    public ArrayList<CentroCostoMayor> buscar(String valorConsulta) throws SQLException{
        ArrayList<CentroCostoMayor> listadoObjeto = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            if(valorConsulta.equals("")){
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT [ccm_cdgo] --1\n" +
                                                                        "      ,[ccm_cliente_cdgo] --2\n" +
                                                                        "		  ,[cl_cdgo]--3\n" +
                                                                        "		  ,[cl_desc]--4\n" +
                                                                        "		  ,[cl_estad]--5\n" +
                                                                        "      ,[ccm_cntro_cost_subcentro_cdgo]--6\n" +
                                                                        "		  ,[ccs_cdgo]--7\n" +
                                                                        "		  ,[ccs_desc]--8\n" +
                                                                        "		  ,[ccs_estad]--9\n" +
                                                                        "		  ,[ccs_cntro_oper_cdgo]--10\n" +
                                                                        "				,[co_cdgo]--11\n" +
                                                                        "				,[co_desc]--12\n" +
                                                                        "				,[co_estad]--13\n" +
                                                                        "      ,[ccm_desc]--14\n" +
                                                                        "      ,[ccm_estad]--15\n" +
                                                                        "  FROM ["+DB+"].[dbo].[cntro_cost_mayor]\n" +
                                                                        "  INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [ccm_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
                                                                        "  INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [ccs_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                        "  INNER JOIN ["+DB+"].[dbo].[cliente] ON [ccm_cliente_cdgo]=[cl_cdgo];");
                resultSetBuscar= queryBuscar.executeQuery();
            }else{
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT [ccm_cdgo] --1\n" +
                                                                        "      ,[ccm_cliente_cdgo] --2\n" +
                                                                        "		  ,[cl_cdgo]--3\n" +
                                                                        "		  ,[cl_desc]--4\n" +
                                                                        "		  ,[cl_estad]--5\n" +
                                                                        "      ,[ccm_cntro_cost_subcentro_cdgo]--6\n" +
                                                                        "		  ,[ccs_cdgo]--7\n" +
                                                                        "		  ,[ccs_desc]--8\n" +
                                                                        "		  ,[ccs_estad]--9\n" +
                                                                        "		  ,[ccs_cntro_oper_cdgo]--10\n" +
                                                                        "				,[co_cdgo]--11\n" +
                                                                        "				,[co_desc]--12\n" +
                                                                        "				,[co_estad]--13\n" +
                                                                        "      ,[ccm_desc]--14\n" +
                                                                        "      ,[ccm_estad]--15\n" +
                                                                        "  FROM ["+DB+"].[dbo].[cntro_cost_mayor]\n" +
                                                                        "  INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [ccm_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
                                                                        "  INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [ccs_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                        "  INNER JOIN ["+DB+"].[dbo].[cliente] ON [ccm_cliente_cdgo]=[cl_cdgo] WHERE [ccm_desc] like ?;");
                queryBuscar.setString(1, "%"+valorConsulta+"%");
                resultSetBuscar= queryBuscar.executeQuery();
            }
            while(resultSetBuscar.next()){ 
                CentroCostoMayor Objeto = new CentroCostoMayor();
                Objeto.setCodigo(resultSetBuscar.getString(1));
                    Cliente cliente = new Cliente();
                        cliente.setCodigo(resultSetBuscar.getString(3));
                        cliente.setDescripcion(resultSetBuscar.getString(4));
                        cliente.setEstado(resultSetBuscar.getString(5));
                Objeto.setCliente(cliente);        
                        CentroCostoSubCentro centroCostoSubCentro = new CentroCostoSubCentro();
                            centroCostoSubCentro.setCodigo(resultSetBuscar.getInt(7));
                            centroCostoSubCentro.setDescripcion(resultSetBuscar.getString(8));
                            centroCostoSubCentro.setEstado(resultSetBuscar.getString(9));
                                CentroOperacion co= new CentroOperacion();
                                co.setCodigo(resultSetBuscar.getInt(11));
                                co.setDescripcion(resultSetBuscar.getString(12));
                                co.setEstado(resultSetBuscar.getString(13));
                            centroCostoSubCentro.setCentroOperacion(co);     
                Objeto.setCentroCostoSubcentro(centroCostoSubCentro);                    
                Objeto.setDescripcion(resultSetBuscar.getString(14));
                Objeto.setEstado(resultSetBuscar.getString(15));
                listadoObjeto.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los centro de costros mayores");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjeto;
    } 
    public boolean validarExistenciaActualizar(CentroCostoMayor Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement queryValidarExistencia= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cntro_cost_mayor] WHERE "
                                                                                                + " [ccm_cliente_cdgo] =? "
                                                                                                + " AND [ccm_cntro_cost_subcentro_cdgo]=? "
                                                                                                + " AND [ccm_desc]  LIKE ? "
                                                                                                + " AND [ccm_estad]=1 AND ccm_cdgo <> ?;");
            queryValidarExistencia.setString(1, Objeto.getCliente().getCodigo());
            queryValidarExistencia.setInt(2, Objeto.getCentroCostoSubcentro().getCodigo());
            queryValidarExistencia.setString(3, Objeto.getDescripcion());
            queryValidarExistencia.setString(4, Objeto.getCodigo());
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
    public int actualizar(CentroCostoMayor Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{ 
            CentroCostoMayor centroCostoMayor=buscarEspecifico(""+Objeto.getCodigo());
            String valorEstado="";          
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                valorEstado="ACTIVO";
            }else{
                valorEstado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[cntro_cost_mayor] SET  [ccm_desc]=?,[ccm_estad] =? WHERE [ccm_cdgo]=?;");
            queryActualizar.setString(1, Objeto.getDescripcion());
            queryActualizar.setString(2, Objeto.getEstado());
            queryActualizar.setString(3, Objeto.getCodigo());
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
                        "           ,'CENTROCOSTO MAYOR'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre el centro de costo mayor Codigo:',?,' Descripción: ',?,' Estado: ',?,' actualizando la siguiente informacion a Código: ',?,' Descripción: ',?,' Estado: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, ""+centroCostoMayor.getCodigo());
                Query_Auditoria.setString(6, ""+centroCostoMayor.getCodigo());
                Query_Auditoria.setString(7, centroCostoMayor.getDescripcion());
                Query_Auditoria.setString(8, centroCostoMayor.getEstado());
                Query_Auditoria.setString(9, Objeto.getCodigo());
                Query_Auditoria.setString(10, Objeto.getDescripcion());
                Query_Auditoria.setString(11, valorEstado);
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
    public CentroCostoMayor buscarEspecifico(String codigo) throws SQLException{
        CentroCostoMayor Objeto = null; 
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT [ccm_cdgo] --1\n" +
                                                                        "      ,[ccm_cliente_cdgo] --2\n" +
                                                                        "		  ,[cl_cdgo]--3\n" +
                                                                        "		  ,[cl_desc]--4\n" +
                                                                        "		  ,[cl_estad]--5\n" +
                                                                        "      ,[ccm_cntro_cost_subcentro_cdgo]--6\n" +
                                                                        "		  ,[ccs_cdgo]--7\n" +
                                                                        "		  ,[ccs_desc]--8\n" +
                                                                        "		  ,[ccs_estad]--9\n" +
                                                                        "		  ,[ccs_cntro_oper_cdgo]--10\n" +
                                                                        "				,[co_cdgo]--11\n" +
                                                                        "				,[co_desc]--12\n" +
                                                                        "				,[co_estad]--13\n" +
                                                                        "      ,[ccm_desc]--14\n" +
                                                                        "      ,[ccm_estad]--15\n" +
                                                                        "  FROM ["+DB+"].[dbo].[cntro_cost_mayor]\n" +
                                                                        "  INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [ccm_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
                                                                        "  INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [ccs_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                        "  INNER JOIN ["+DB+"].[dbo].[cliente] ON [ccm_cliente_cdgo]=[cl_cdgo] WHERE [ccm_cdgo] =?;");
            queryBuscar.setString(1, codigo);
            ResultSet resultSetBuscar= queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                Objeto = new CentroCostoMayor();
                Objeto.setCodigo(resultSetBuscar.getString(1));
                    Cliente cliente = new Cliente();
                        cliente.setCodigo(resultSetBuscar.getString(3));
                        cliente.setDescripcion(resultSetBuscar.getString(4));
                        cliente.setEstado(resultSetBuscar.getString(5));
                Objeto.setCliente(cliente);        
                        CentroCostoSubCentro centroCostoSubCentro = new CentroCostoSubCentro();
                            centroCostoSubCentro.setCodigo(resultSetBuscar.getInt(7));
                            centroCostoSubCentro.setDescripcion(resultSetBuscar.getString(8));
                            centroCostoSubCentro.setEstado(resultSetBuscar.getString(9));
                                CentroOperacion co= new CentroOperacion();
                                co.setCodigo(resultSetBuscar.getInt(11));
                                co.setDescripcion(resultSetBuscar.getString(12));
                                co.setEstado(resultSetBuscar.getString(13));
                            centroCostoSubCentro.setCentroOperacion(co);     
                Objeto.setCentroCostoSubcentro(centroCostoSubCentro);                    
                Objeto.setDescripcion(resultSetBuscar.getString(14));
                Objeto.setEstado(resultSetBuscar.getString(15));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar el centro de costo Mayor");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return Objeto;
    }
    
    public CentroCostoMayor buscarCentroCostoMayorProcesar(MvtoEquipo mvtoEquipo) throws SQLException{
        
        CentroCostoMayor Objeto = null; 
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        ResultSet resultSetBuscar= null;
        Cliente clienteP = null;
        clienteP=mvtoEquipo.getCliente();
        CentroCostoSubCentro centroCostoSubCentroP=null;
        centroCostoSubCentroP= mvtoEquipo.getCentroCostoAuxiliar().getCentroCostoSubCentro();
        if(clienteP != null && centroCostoSubCentroP != null){
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT [ccm_cdgo] --1\n" +
                                                                    "      ,[ccm_cliente_cdgo] --2\n" +
                                                                    "		  ,[cl_cdgo]--3\n" +
                                                                    "		  ,[cl_desc]--4\n" +
                                                                    "		  ,[cl_estad]--5\n" +
                                                                    "      ,[ccm_cntro_cost_subcentro_cdgo]--6\n" +
                                                                    "		  ,[ccs_cdgo]--7\n" +
                                                                    "		  ,[ccs_desc]--8\n" +
                                                                    "		  ,[ccs_estad]--9\n" +
                                                                    "		  ,[ccs_cntro_oper_cdgo]--10\n" +
                                                                    "				,[co_cdgo]--11\n" +
                                                                    "				,[co_desc]--12\n" +
                                                                    "				,[co_estad]--13\n" +
                                                                    "      ,[ccm_desc]--14\n" +
                                                                    "      ,[ccm_estad]--15\n" +
                                                                    "  FROM ["+DB+"].[dbo].[cntro_cost_mayor]\n" +
                                                                    "  INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [ccm_cntro_cost_subcentro_cdgo]=[ccs_cdgo]\n" +
                                                                    "  INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [ccs_cntro_oper_cdgo]=[co_cdgo]\n" +
                                                                    "  INNER JOIN ["+DB+"].[dbo].[cliente] ON [ccm_cliente_cdgo]=[cl_cdgo] WHERE"
                                                                                + " [ccm_cliente_cdgo]=? AND"
                                                                                + " [ccm_cntro_cost_subcentro_cdgo]=? ;");
            queryBuscar.setString(1, mvtoEquipo.getCliente().getCodigo());
            queryBuscar.setInt(2, mvtoEquipo.getCentroCostoAuxiliar().getCentroCostoSubCentro().getCodigo());
            resultSetBuscar= queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                Objeto = new CentroCostoMayor();
                Objeto.setCodigo(resultSetBuscar.getString(1));
                    Cliente cliente = new Cliente();
                        cliente.setCodigo(resultSetBuscar.getString(3));
                        cliente.setDescripcion(resultSetBuscar.getString(4));
                        cliente.setEstado(resultSetBuscar.getString(5));
                Objeto.setCliente(cliente);        
                        CentroCostoSubCentro centroCostoSubCentro = new CentroCostoSubCentro();
                            centroCostoSubCentro.setCodigo(resultSetBuscar.getInt(7));
                            centroCostoSubCentro.setDescripcion(resultSetBuscar.getString(8));
                            centroCostoSubCentro.setEstado(resultSetBuscar.getString(9));
                                CentroOperacion co= new CentroOperacion();
                                co.setCodigo(resultSetBuscar.getInt(11));
                                co.setDescripcion(resultSetBuscar.getString(12));
                                co.setEstado(resultSetBuscar.getString(13));
                            centroCostoSubCentro.setCentroOperacion(co);     
                Objeto.setCentroCostoSubcentro(centroCostoSubCentro);                    
                Objeto.setDescripcion(resultSetBuscar.getString(14));
                Objeto.setEstado(resultSetBuscar.getString(15));
            }
        }
        control.cerrarConexionBaseDatos(); 
        System.out.println("Mayor:"+mvtoEquipo.getCodigo());
        return Objeto;
    } 
}
