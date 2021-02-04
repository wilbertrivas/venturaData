package Catalogo.Controller;
    
import ConnectionDB2.Conexion_DB_costos_vg;
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.CentroCosto;
import Catalogo.Model.CentroCostoSubCentro;
import Catalogo.Model.CentroOperacion;
import Catalogo.Model.Cliente;
import Catalogo.Model.LaborRealizada;
import ConnectionDB2.Conexion_DB_ccargaGP;
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

public class ControlDB_CentroCosto {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private final String tipoConexion;

    public ControlDB_CentroCosto(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }   
    public int registrar(CentroCosto Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
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
                JOptionPane.showMessageDialog(null, "Ya existe un centro de costo Mayor registrado para la información suministrada, verifique información", "Error!!", JOptionPane.ERROR_MESSAGE);
            }else{
                conexion= control.ConectarBaseDatos();
                String DB=control.getBaseDeDatos();
                PreparedStatement queryRegistrar= conexion.prepareStatement(" INSERT INTO ["+DB+"].[dbo].[cntro_cost] ([cc_cdgo]\n" +
                                                                                                                    "      ,[cc_cntro_oper_cdgo]\n" +
                                                                                                                    "      ,[cc_cntro_cost_auxiliar_cdgo]\n" +
                                                                                                                    "      ,[cc_cliente_cdgo]\n" +
                                                                                                                    "      ,[cc_descripcion]\n" +
                                                                                                                    "      ,[cc_estad],[cc_labor_realizada_cdgo]) VALUES ((SELECT (CASE WHEN (MAX([cc_cdgo]) IS NULL)\n" +
                                                                                                        " THEN 1\n" +
                                                                                                        " ELSE (MAX([cc_cdgo])+1) END)AS [cc_cdgo]\n" +
                                                                                    " FROM ["+DB+"].[dbo].[cntro_cost]),?,?,?,?,?,?);");
                queryRegistrar.setInt(1, Objeto.getCentroOperacion().getCodigo());
                queryRegistrar.setInt(2, Objeto.getCentroCostoAuxiliar().getCodigo());
                queryRegistrar.setString(3, Objeto.getCliente().getCodigo());
                queryRegistrar.setString(4, Objeto.getDescripción());
                queryRegistrar.setString(5, Objeto.getEstado());
                if(Objeto.getLaborRealizada() != null){
                    queryRegistrar.setString(6, Objeto.getLaborRealizada().getCodigo());
                }else{
                    queryRegistrar.setString(6, "NULL");
                }
                queryRegistrar.execute();
                result=1;
                if(result==1){
                    result=0;
                    //Sacamos el ultimo valor 
                    PreparedStatement queryMaximo= conexion.prepareStatement("SELECT MAX(cc_cdgo) FROM ["+DB+"].[dbo].[cntro_cost];");
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
                            "           ,CONCAT (?,?,' Nombre: ',?,' CentroOperación: ',?, ' SubcentroCosto:',?, ' AuxiliarCentroCosto:',?, ' Cliente:',?, ' Estado:',?,' LaborRealizada: ',?));");
                    Query_AuditoriaInsert.setString(1, us.getCodigo());
                    Query_AuditoriaInsert.setString(2, namePc);
                    Query_AuditoriaInsert.setString(3, ipPc);
                    Query_AuditoriaInsert.setString(4, macPC);
                    Query_AuditoriaInsert.setString(5, ""+Objeto.getCodigo());
                    Query_AuditoriaInsert.setString(6, "Se registró un nuevo centro de costos mayor en el sistema, con Código: ");
                    Query_AuditoriaInsert.setString(7, Objeto.getCodigo());
                    Query_AuditoriaInsert.setString(8, Objeto.getDescripción());
                    Query_AuditoriaInsert.setString(9, Objeto.getCentroOperacion().getDescripcion());
                    Query_AuditoriaInsert.setString(10, Objeto.getCentroCostoAuxiliar().getCentroCostoSubCentro().getDescripcion());
                    Query_AuditoriaInsert.setString(11, Objeto.getCentroCostoAuxiliar().getDescripcion());
                    Query_AuditoriaInsert.setString(12, Objeto.getCliente().getDescripcion());
                    Query_AuditoriaInsert.setString(13, estado);
                    if(Objeto.getLaborRealizada().getCodigo() != null){
                        Query_AuditoriaInsert.setString(14, Objeto.getLaborRealizada().getCodigo());
                    }else{
                        Query_AuditoriaInsert.setString(14, "NULL");
                    }
                    
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
    public boolean validarExistencia(CentroCosto Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement queryValidarExistencia= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cntro_cost] WHERE "
                                                                                                + " [cc_cntro_oper_cdgo] =? "
                                                                                                + " AND [cc_cntro_cost_auxiliar_cdgo]=? "
                                                                                                + " AND [cc_cliente_cdgo]=? "
                                                                                                + " AND [cc_estad]=1 "
                                                                                                + " AND [cc_labor_realizada_cdgo]=?;");
            queryValidarExistencia.setInt(1, Objeto.getCentroOperacion().getCodigo());
            queryValidarExistencia.setInt(2, Objeto.getCentroCostoAuxiliar().getCodigo());
            queryValidarExistencia.setString(3, Objeto.getCliente().getCodigo());
            if(Objeto.getLaborRealizada().getCodigo() !=null){
                queryValidarExistencia.setString(4, Objeto.getLaborRealizada().getCodigo());
            }else{
                queryValidarExistencia.setString(4, "NULL");
            }  
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
    public ArrayList<CentroCosto> buscar(String valorConsulta) throws SQLException{
        ArrayList<CentroCosto> listadoObjeto = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            if(valorConsulta.equals("")){
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT    [cc_cdgo] --1 \n" +
                                                            "                      ,[cc_cntro_oper_cdgo] --2 \n" +
                                                            "                		  ,[co_cdgo] --3 \n" +
                                                            "                		  ,[co_desc] --4 \n" +
                                                            "                		  ,[co_estad] --5 \n" +
                                                            "                     ,[cc_cntro_cost_auxiliar_cdgo] --6 \n" +
                                                            "                		  ,[cca_cdgo] --7 \n" +
                                                            "                		  ,[cca_cntro_cost_subcentro_cdgo] --8 \n" +
                                                            "                			  ,[ccs_cdgo] --9 \n" +
                                                            "                			  ,[ccs_desc] --10 \n" +
                                                            "                			  ,[ccs_estad] --11 \n" +
                                                            "                		  ,[cca_desc] --12 \n" +
                                                            "                		  ,[cca_estad] --13 \n" +
                                                            "                      ,[cc_cliente_cdgo] --14 \n" +
                                                            "                		  ,[cl_cdgo] --15 \n" +
                                                            "                		  ,[cl_desc] --16 \n" +
                                                            "                		  ,[cl_estad] --17 \n" +
                                                            "                      ,[cc_descripcion] --18 \n" +
                                                            "                      , CASE WHEN (cc_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS cc_estad --19 \n" +
                                                            "                     ,[cc_labor_realizada_cdgo]--20\n" +
                                                            "						,[lr_cdgo]--21\n" +
                                                            "						,[lr_desc]--22\n" +
                                                            "						,[lr_cntro_cost_subcentro_cdgo]--23\n" +
                                                            "						,[lr_estad]--24\n" +
                                                            "						,[lr_operativa]--25\n" +
                                                            "						,[lr_parada]--26\n" +
                                                            "						,[lr_bodegaDestino]--27\n" +
                                                            "                  FROM ["+DB+"].[dbo].[cntro_cost] \n" +
                                                            "                  INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [cc_cntro_oper_cdgo]=[co_cdgo] \n" +
                                                            "                  INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [cc_cntro_cost_auxiliar_cdgo]=[cca_cdgo] \n" +
                                                            "                  INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo] \n" +
                                                            "                  INNER JOIN ["+DB+"].[dbo].[cliente] ON [cc_cliente_cdgo]=[cl_cdgo]\n" +
                                                            "				  LEFT JOIN ["+DB+"].[dbo].[labor_realizada] ON [cc_labor_realizada_cdgo]=[lr_cdgo];");
                resultSetBuscar= queryBuscar.executeQuery();
            }else{
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT    [cc_cdgo] --1 \n" +
                                                            "                      ,[cc_cntro_oper_cdgo] --2 \n" +
                                                            "                		  ,[co_cdgo] --3 \n" +
                                                            "                		  ,[co_desc] --4 \n" +
                                                            "                		  ,[co_estad] --5 \n" +
                                                            "                     ,[cc_cntro_cost_auxiliar_cdgo] --6 \n" +
                                                            "                		  ,[cca_cdgo] --7 \n" +
                                                            "                		  ,[cca_cntro_cost_subcentro_cdgo] --8 \n" +
                                                            "                			  ,[ccs_cdgo] --9 \n" +
                                                            "                			  ,[ccs_desc] --10 \n" +
                                                            "                			  ,[ccs_estad] --11 \n" +
                                                            "                		  ,[cca_desc] --12 \n" +
                                                            "                		  ,[cca_estad] --13 \n" +
                                                            "                      ,[cc_cliente_cdgo] --14 \n" +
                                                            "                		  ,[cl_cdgo] --15 \n" +
                                                            "                		  ,[cl_desc] --16 \n" +
                                                            "                		  ,[cl_estad] --17 \n" +
                                                            "                      ,[cc_descripcion] --18 \n" +
                                                            "                      , CASE WHEN (cc_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS cc_estad --19 \n" +
                                                            "                     ,[cc_labor_realizada_cdgo]--20\n" +
                                                            "						,[lr_cdgo]--21\n" +
                                                            "						,[lr_desc]--22\n" +
                                                            "						,[lr_cntro_cost_subcentro_cdgo]--23\n" +
                                                            "						,[lr_estad]--24\n" +
                                                            "						,[lr_operativa]--25\n" +
                                                            "						,[lr_parada]--26\n" +
                                                            "						,[lr_bodegaDestino]--27\n" +
                                                            "                  FROM ["+DB+"].[dbo].[cntro_cost] \n" +
                                                            "                  INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [cc_cntro_oper_cdgo]=[co_cdgo] \n" +
                                                            "                  INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [cc_cntro_cost_auxiliar_cdgo]=[cca_cdgo] \n" +
                                                            "                  INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo] \n" +
                                                            "                  INNER JOIN ["+DB+"].[dbo].[cliente] ON [cc_cliente_cdgo]=[cl_cdgo]\n" +
                                                            "		       LEFT JOIN ["+DB+"].[dbo].[labor_realizada] ON [cc_labor_realizada_cdgo]=[lr_cdgo] WHERE [cc_descripcion] like ?;");
                queryBuscar.setString(1, "%"+valorConsulta+"%");
                resultSetBuscar= queryBuscar.executeQuery();
            }
            while(resultSetBuscar.next()){ 
                CentroCosto Objeto = new CentroCosto();
                Objeto.setCodigo(resultSetBuscar.getString(1));
                    Cliente cliente = new Cliente();
                        cliente.setCodigo(resultSetBuscar.getString(15));
                        cliente.setDescripcion(resultSetBuscar.getString(16));
                        cliente.setEstado(resultSetBuscar.getString(17));
                Objeto.setCliente(cliente);        
                        CentroOperacion centroOperacion = new CentroOperacion();
                        centroOperacion.setCodigo(resultSetBuscar.getInt(3));
                        centroOperacion.setDescripcion(resultSetBuscar.getString(4));
                        centroOperacion.setEstado(resultSetBuscar.getString(5));
                Objeto.setCentroOperacion(centroOperacion);          
                    CentroCostoSubCentro centroCostoSubCentro = new CentroCostoSubCentro();
                        centroCostoSubCentro.setCodigo(resultSetBuscar.getInt(9));
                        centroCostoSubCentro.setDescripcion(resultSetBuscar.getString(10));
                        centroCostoSubCentro.setEstado(resultSetBuscar.getString(11));
                    CentroCostoAuxiliar centroCostoAuxiliar = new CentroCostoAuxiliar();
                        centroCostoAuxiliar.setCodigo(resultSetBuscar.getInt(7));
                        centroCostoAuxiliar.setDescripcion(resultSetBuscar.getString(12));
                        centroCostoAuxiliar.setEstado(resultSetBuscar.getString(13));
                        centroCostoAuxiliar.setCentroCostoSubCentro(centroCostoSubCentro);
                Objeto.setCentroCostoAuxiliar(centroCostoAuxiliar);
                Objeto.setDescripción(resultSetBuscar.getString(18));
                Objeto.setEstado(resultSetBuscar.getString(19));
                    LaborRealizada laborRealizada = new LaborRealizada();
                    laborRealizada.setCodigo(resultSetBuscar.getString(21));
                    laborRealizada.setDescripcion(resultSetBuscar.getString(22));
                    laborRealizada.setEstado(resultSetBuscar.getString(24));       
                    laborRealizada.setEs_operativa(resultSetBuscar.getString(25));
                    laborRealizada.setEs_parada(resultSetBuscar.getString(26));
                    laborRealizada.setBodegaDestino(resultSetBuscar.getString(27));     
                Objeto.setLaborRealizada(laborRealizada);
                listadoObjeto.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los centro de costos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjeto;
    } 
    public boolean validarExistenciaActualizar(CentroCosto Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement queryValidarExistencia= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[cntro_cost] WHERE "
                                                                                                + " [cc_cntro_oper_cdgo] =? "
                                                                                                + " AND [cc_cntro_cost_auxiliar_cdgo]=? "
                                                                                                + " AND [cc_cliente_cdgo]=? "
                                                                                                + " AND [cc_estad]=1 AND [cc_labor_realizada_cdgo]=? AND cc_cdgo <> ?;");
            queryValidarExistencia.setInt(1, Objeto.getCentroOperacion().getCodigo());
            queryValidarExistencia.setInt(2, Objeto.getCentroCostoAuxiliar().getCodigo());
            queryValidarExistencia.setString(3, Objeto.getCliente().getCodigo());
             if(Objeto.getLaborRealizada().getCodigo() != null){
                queryValidarExistencia.setString(4, Objeto.getLaborRealizada().getCodigo());
            }else{
                queryValidarExistencia.setString(4, "NULL");
            }
            queryValidarExistencia.setString(5, Objeto.getCodigo());
            
           
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
    public int actualizar(CentroCosto Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{ 
            CentroCosto centroCostoMayor=buscarEspecifico(""+Objeto.getCodigo());
            String valorEstado="";          
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                valorEstado="ACTIVO";
            }else{
                valorEstado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[cntro_cost] SET  [cc_descripcion]=?,[cc_estad] =?, [cc_labor_realizada_cdgo]=? WHERE [cc_cdgo]=?;");
            queryActualizar.setString(1, Objeto.getDescripción());
            queryActualizar.setString(2, Objeto.getEstado());
            if(Objeto.getLaborRealizada().getCodigo() !=null){
                queryActualizar.setString(3, Objeto.getLaborRealizada().getCodigo());
            }else{
                queryActualizar.setString(3, "NULL");
            }  
            queryActualizar.setString(4, Objeto.getCodigo());
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
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre el centro de costo mayor Codigo:',?,' Descripción: ',?,' Estado: ',?,'laborRealizada',?,' actualizando la siguiente informacion a Código: ',?,' Descripción: ',?,' Estado: ',?,'LaborRealizada: ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, ""+centroCostoMayor.getCodigo());
                Query_Auditoria.setString(6, ""+centroCostoMayor.getCodigo());
                Query_Auditoria.setString(7, centroCostoMayor.getDescripción());
                Query_Auditoria.setString(8, centroCostoMayor.getEstado());
                if(centroCostoMayor.getLaborRealizada().getCodigo() != null){
                    Query_Auditoria.setString(9, centroCostoMayor.getLaborRealizada().getCodigo());
                }else{
                    Query_Auditoria.setString(9, "NULL");
                }
                Query_Auditoria.setString(10, Objeto.getCodigo());
                Query_Auditoria.setString(11, Objeto.getDescripción());
                Query_Auditoria.setString(12, valorEstado);
                if(Objeto.getLaborRealizada().getCodigo() != null){
                    Query_Auditoria.setString(13, Objeto.getLaborRealizada().getCodigo());
                }else{
                    Query_Auditoria.setString(13, "NULL");
                }
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
    public CentroCosto buscarEspecifico(String codigo) throws SQLException{
        CentroCosto Objeto = null; 
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT    [cc_cdgo] --1 \n" +
                                                            "                      ,[cc_cntro_oper_cdgo] --2 \n" +
                                                            "                		  ,[co_cdgo] --3 \n" +
                                                            "                		  ,[co_desc] --4 \n" +
                                                            "                		  ,[co_estad] --5 \n" +
                                                            "                     ,[cc_cntro_cost_auxiliar_cdgo] --6 \n" +
                                                            "                		  ,[cca_cdgo] --7 \n" +
                                                            "                		  ,[cca_cntro_cost_subcentro_cdgo] --8 \n" +
                                                            "                			  ,[ccs_cdgo] --9 \n" +
                                                            "                			  ,[ccs_desc] --10 \n" +
                                                            "                			  ,[ccs_estad] --11 \n" +
                                                            "                		  ,[cca_desc] --12 \n" +
                                                            "                		  ,[cca_estad] --13 \n" +
                                                            "                      ,[cc_cliente_cdgo] --14 \n" +
                                                            "                		  ,[cl_cdgo] --15 \n" +
                                                            "                		  ,[cl_desc] --16 \n" +
                                                            "                		  ,[cl_estad] --17 \n" +
                                                            "                      ,[cc_descripcion] --18 \n" +
                                                            "                      , CASE WHEN (cc_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS cc_estad --19 \n" +
                                                            "                     ,[cc_labor_realizada_cdgo]--20\n" +
                                                            "						,[lr_cdgo]--21\n" +
                                                            "						,[lr_desc]--22\n" +
                                                            "						,[lr_cntro_cost_subcentro_cdgo]--23\n" +
                                                            "						,[lr_estad]--24\n" +
                                                            "						,[lr_operativa]--25\n" +
                                                            "						,[lr_parada]--26\n" +
                                                            "						,[lr_bodegaDestino]--27\n" +
                                                            "                  FROM ["+DB+"].[dbo].[cntro_cost] \n" +
                                                            "                  INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [cc_cntro_oper_cdgo]=[co_cdgo] \n" +
                                                            "                  INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [cc_cntro_cost_auxiliar_cdgo]=[cca_cdgo] \n" +
                                                            "                  INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo] \n" +
                                                            "                  INNER JOIN ["+DB+"].[dbo].[cliente] ON [cc_cliente_cdgo]=[cl_cdgo]\n" +
                                                            "				  LEFT JOIN ["+DB+"].[dbo].[labor_realizada] ON [cc_labor_realizada_cdgo]=[lr_cdgo] WHERE [cc_cdgo]=?;");
            queryBuscar.setString(1, codigo);
            ResultSet resultSetBuscar= queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                Objeto = new CentroCosto();
                Objeto.setCodigo(resultSetBuscar.getString(1));
                    Cliente cliente = new Cliente();
                        cliente.setCodigo(resultSetBuscar.getString(15));
                        cliente.setDescripcion(resultSetBuscar.getString(16));
                        cliente.setEstado(resultSetBuscar.getString(17));
                Objeto.setCliente(cliente);        
                        CentroOperacion centroOperacion = new CentroOperacion();
                        centroOperacion.setCodigo(resultSetBuscar.getInt(3));
                        centroOperacion.setDescripcion(resultSetBuscar.getString(4));
                        centroOperacion.setEstado(resultSetBuscar.getString(5));
                Objeto.setCentroOperacion(centroOperacion);          
                    CentroCostoSubCentro centroCostoSubCentro = new CentroCostoSubCentro();
                        centroCostoSubCentro.setCodigo(resultSetBuscar.getInt(9));
                        centroCostoSubCentro.setDescripcion(resultSetBuscar.getString(10));
                        centroCostoSubCentro.setEstado(resultSetBuscar.getString(11));
                    CentroCostoAuxiliar centroCostoAuxiliar = new CentroCostoAuxiliar();
                        centroCostoAuxiliar.setCodigo(resultSetBuscar.getInt(7));
                        centroCostoAuxiliar.setDescripcion(resultSetBuscar.getString(12));
                        centroCostoAuxiliar.setEstado(resultSetBuscar.getString(13));
                        centroCostoAuxiliar.setCentroCostoSubCentro(centroCostoSubCentro);
                Objeto.setCentroCostoAuxiliar(centroCostoAuxiliar);
                Objeto.setDescripción(resultSetBuscar.getString(18));
                Objeto.setEstado(resultSetBuscar.getString(19));
                    LaborRealizada laborRealizada = new LaborRealizada();
                    laborRealizada.setCodigo(resultSetBuscar.getString(21));
                    laborRealizada.setDescripcion(resultSetBuscar.getString(22));
                    laborRealizada.setEstado(resultSetBuscar.getString(24));       
                    laborRealizada.setEs_operativa(resultSetBuscar.getString(25));
                    laborRealizada.setEs_parada(resultSetBuscar.getString(26));
                    laborRealizada.setBodegaDestino(resultSetBuscar.getString(27));     
                Objeto.setLaborRealizada(laborRealizada);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar el centro de costo Mayor");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return Objeto;
    }
    
    public CentroCosto buscarCentroCostoProcesar(MvtoEquipo mvtoEquipo) throws SQLException{ 
        CentroCosto Objeto = null; 
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        ResultSet resultSetBuscar= null;
        
        CentroOperacion centroOperacionP=null;
        centroOperacionP=mvtoEquipo.getCentroOperacion();

        CentroCostoAuxiliar centroCostoAuxiliarP=null;
        centroCostoAuxiliarP=mvtoEquipo.getCentroCostoAuxiliar();

        Cliente clienteP = null;
        clienteP=mvtoEquipo.getCliente();
        
        LaborRealizada laborRealizadaP=null;
        laborRealizadaP=mvtoEquipo.getLaborRealizada();
        if(mvtoEquipo.getCentroCostoAuxiliar().getCentroCostoSubCentro().getCentroCostoRequiereLaborRealizada().equals("1")){//El CentroCostos depende de la labor realizada
            if((centroOperacionP != null && centroCostoAuxiliarP != null && clienteP != null && laborRealizadaP != null)){   
                try{
                    PreparedStatement queryBuscar= conexion.prepareStatement("SELECT    [cc_cdgo] --1 \n" +
                                                                "                      ,[cc_cntro_oper_cdgo] --2 \n" +
                                                                "                		  ,[co_cdgo] --3 \n" +
                                                                "                		  ,[co_desc] --4 \n" +
                                                                "                		  ,[co_estad] --5 \n" +
                                                                "                     ,[cc_cntro_cost_auxiliar_cdgo] --6 \n" +
                                                                "                		  ,[cca_cdgo] --7 \n" +
                                                                "                		  ,[cca_cntro_cost_subcentro_cdgo] --8 \n" +
                                                                "                			  ,[ccs_cdgo] --9 \n" +
                                                                "                			  ,[ccs_desc] --10 \n" +
                                                                "                			  ,[ccs_estad] --11 \n" +
                                                                "                		  ,[cca_desc] --12 \n" +
                                                                "                		  ,[cca_estad] --13 \n" +
                                                                "                      ,[cc_cliente_cdgo] --14 \n" +
                                                                "                		  ,[cl_cdgo] --15 \n" +
                                                                "                		  ,[cl_desc] --16 \n" +
                                                                "                		  ,[cl_estad] --17 \n" +
                                                                "                      ,[cc_descripcion] --18 \n" +
                                                                "                      , CASE WHEN (cc_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS cc_estad --19 \n" +
                                                                "                     ,[cc_labor_realizada_cdgo]--20\n" +
                                                                "						,[lr_cdgo]--21\n" +
                                                                "						,[lr_desc]--22\n" +
                                                                "						,[lr_cntro_cost_subcentro_cdgo]--23\n" +
                                                                "						,[lr_estad]--24\n" +
                                                                "						,[lr_operativa]--25\n" +
                                                                "						,[lr_parada]--26\n" +
                                                                "						,[lr_bodegaDestino]--27\n" +
                                                                "                  FROM ["+DB+"].[dbo].[cntro_cost] \n" +
                                                                "                  INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [cc_cntro_oper_cdgo]=[co_cdgo] \n" +
                                                                "                  INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [cc_cntro_cost_auxiliar_cdgo]=[cca_cdgo] \n" +
                                                                "                  INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo] \n" +
                                                                "                  INNER JOIN ["+DB+"].[dbo].[cliente] ON [cc_cliente_cdgo]=[cl_cdgo]\n" +
                                                                "		       LEFT JOIN ["+DB+"].[dbo].[labor_realizada] ON [cc_labor_realizada_cdgo]=[lr_cdgo] WHERE"
                                                                                        + " [cc_cntro_oper_cdgo]=? AND"
                                                                                        + " [cc_cntro_cost_auxiliar_cdgo]=? AND "
                                                                                        + " [cc_cliente_cdgo] =? AND [cc_labor_realizada_cdgo]=?;");
                    queryBuscar.setInt(1, mvtoEquipo.getCentroOperacion().getCodigo());
                    queryBuscar.setInt(2, mvtoEquipo.getCentroCostoAuxiliar().getCodigo());
                    queryBuscar.setString(3, mvtoEquipo.getCliente().getCodigo());
                    queryBuscar.setString(4, mvtoEquipo.getLaborRealizada().getCodigo());
                    resultSetBuscar= queryBuscar.executeQuery();

                }catch (SQLException sqlException) {
                    JOptionPane.showMessageDialog(null, "Error al tratar de consultar el centro de costo");
                    sqlException.printStackTrace();
                }  
            }
        }else{//El centro de costo no depende de la Labor Realizada
            if((centroOperacionP != null && centroCostoAuxiliarP != null && clienteP != null)){   
                try{
                    PreparedStatement queryBuscar= conexion.prepareStatement("SELECT    [cc_cdgo] --1 \n" +
                                                                "                      ,[cc_cntro_oper_cdgo] --2 \n" +
                                                                "                		  ,[co_cdgo] --3 \n" +
                                                                "                		  ,[co_desc] --4 \n" +
                                                                "                		  ,[co_estad] --5 \n" +
                                                                "                     ,[cc_cntro_cost_auxiliar_cdgo] --6 \n" +
                                                                "                		  ,[cca_cdgo] --7 \n" +
                                                                "                		  ,[cca_cntro_cost_subcentro_cdgo] --8 \n" +
                                                                "                			  ,[ccs_cdgo] --9 \n" +
                                                                "                			  ,[ccs_desc] --10 \n" +
                                                                "                			  ,[ccs_estad] --11 \n" +
                                                                "                		  ,[cca_desc] --12 \n" +
                                                                "                		  ,[cca_estad] --13 \n" +
                                                                "                      ,[cc_cliente_cdgo] --14 \n" +
                                                                "                		  ,[cl_cdgo] --15 \n" +
                                                                "                		  ,[cl_desc] --16 \n" +
                                                                "                		  ,[cl_estad] --17 \n" +
                                                                "                      ,[cc_descripcion] --18 \n" +
                                                                "                      , CASE WHEN (cc_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS cc_estad --19 \n" +
                                                                "                     ,[cc_labor_realizada_cdgo]--20\n" +
                                                                "						,[lr_cdgo]--21\n" +
                                                                "						,[lr_desc]--22\n" +
                                                                "						,[lr_cntro_cost_subcentro_cdgo]--23\n" +
                                                                "						,[lr_estad]--24\n" +
                                                                "						,[lr_operativa]--25\n" +
                                                                "						,[lr_parada]--26\n" +
                                                                "						,[lr_bodegaDestino]--27\n" +
                                                                "                  FROM ["+DB+"].[dbo].[cntro_cost] \n" +
                                                                "                  INNER JOIN ["+DB+"].[dbo].[cntro_oper] ON [cc_cntro_oper_cdgo]=[co_cdgo] \n" +
                                                                "                  INNER JOIN ["+DB+"].[dbo].[cntro_cost_auxiliar] ON [cc_cntro_cost_auxiliar_cdgo]=[cca_cdgo] \n" +
                                                                "                  INNER JOIN ["+DB+"].[dbo].[cntro_cost_subcentro] ON [cca_cntro_cost_subcentro_cdgo]=[ccs_cdgo] \n" +
                                                                "                  INNER JOIN ["+DB+"].[dbo].[cliente] ON [cc_cliente_cdgo]=[cl_cdgo]\n" +
                                                                "		       LEFT JOIN ["+DB+"].[dbo].[labor_realizada] ON [cc_labor_realizada_cdgo]=[lr_cdgo] WHERE"
                                                                                        + " [cc_cntro_oper_cdgo]=? AND"
                                                                                        + " [cc_cntro_cost_auxiliar_cdgo]=? AND "
                                                                                        + " [cc_cliente_cdgo] =?;");
                    queryBuscar.setInt(1, mvtoEquipo.getCentroOperacion().getCodigo());
                    queryBuscar.setInt(2, mvtoEquipo.getCentroCostoAuxiliar().getCodigo());
                    queryBuscar.setString(3, mvtoEquipo.getCliente().getCodigo());
                    resultSetBuscar= queryBuscar.executeQuery();              
                }catch (SQLException sqlException) {
                    JOptionPane.showMessageDialog(null, "Error al tratar de consultar el centro de costo");
                    sqlException.printStackTrace();
                }  
            }
        }
        if(resultSetBuscar !=null){
            while(resultSetBuscar.next()){ 
                Objeto = new CentroCosto();
                Objeto.setCodigo(resultSetBuscar.getString(1));
                    Cliente cliente = new Cliente();
                        cliente.setCodigo(resultSetBuscar.getString(15));
                        cliente.setDescripcion(resultSetBuscar.getString(16));
                        cliente.setEstado(resultSetBuscar.getString(17));
                Objeto.setCliente(cliente);        
                        CentroOperacion centroOperacion = new CentroOperacion();
                        centroOperacion.setCodigo(resultSetBuscar.getInt(3));
                        centroOperacion.setDescripcion(resultSetBuscar.getString(4));
                        centroOperacion.setEstado(resultSetBuscar.getString(5));
                Objeto.setCentroOperacion(centroOperacion);          
                    CentroCostoSubCentro centroCostoSubCentro = new CentroCostoSubCentro();
                        centroCostoSubCentro.setCodigo(resultSetBuscar.getInt(9));
                        centroCostoSubCentro.setDescripcion(resultSetBuscar.getString(10));
                        centroCostoSubCentro.setEstado(resultSetBuscar.getString(11));
                    CentroCostoAuxiliar centroCostoAuxiliar = new CentroCostoAuxiliar();
                        centroCostoAuxiliar.setCodigo(resultSetBuscar.getInt(7));
                        centroCostoAuxiliar.setDescripcion(resultSetBuscar.getString(12));
                        centroCostoAuxiliar.setEstado(resultSetBuscar.getString(13));
                        centroCostoAuxiliar.setCentroCostoSubCentro(centroCostoSubCentro);
                Objeto.setCentroCostoAuxiliar(centroCostoAuxiliar);
                Objeto.setDescripción(resultSetBuscar.getString(18));
                Objeto.setEstado(resultSetBuscar.getString(19));
                /*if(resultSetBuscar.getString(21).){
                    Objeto.setLaborRealizada(null);
                }else{
                    LaborRealizada laborRealizada = new LaborRealizada();
                    laborRealizada.setCodigo(resultSetBuscar.getString(21));
                    laborRealizada.setDescripcion(resultSetBuscar.getString(22));
                    laborRealizada.setEstado(resultSetBuscar.getString(24));       
                    laborRealizada.setEs_operativa(resultSetBuscar.getString(25));
                    laborRealizada.setEs_parada(resultSetBuscar.getString(26));
                    laborRealizada.setBodegaDestino(resultSetBuscar.getString(27));     
                    Objeto.setLaborRealizada(laborRealizada);
                }    */  
            }
        }
        control.cerrarConexionBaseDatos();   
        if(Objeto ==null){//El centro de costo no se encontró, validamos si es embarque y lo buscamos en controlcarga en arribo de motonave
            if(mvtoEquipo.getCentroCostoAuxiliar().getCentroCostoSubCentro().getCodigo()==3){//El subcentro de costo es Embarque por tal motivo validamos si cargo una motonave
               if(!(mvtoEquipo.getMotonave().getCodigo()== null)){     
                   Conexion_DB_ccargaGP control_gp= new Conexion_DB_ccargaGP(tipoConexion);
                    Connection conexion_gp=null;
                    conexion_gp= control_gp.ConectarBaseDatos();
                    if(conexion_gp != null){
                        String DB_vg=control_gp.getBaseDeDatos();
                        ResultSet resultSetCentroCostoGP= null;
                        System.out.println("SELECT \n" +
                                                        "	  [am_cntro_csto]\n" +
                                                        "  FROM ["+DB_vg+"].[dbo].[arrbo_mtnve]\n" +
                                                        "  WHERE '"+mvtoEquipo.getFechaRegistro()+"' BETWEEN [am_fcha_arrbo] AND [am_fcha_fnlzcion] AND [am_mtnve] LIK '"+mvtoEquipo.getMotonave().getCodigo()+"';");
                        PreparedStatement queryBuscarCentroCostoGP= conexion_gp.prepareStatement("SELECT \n" +
                                                        "	  [am_cntro_csto]\n" +
                                                        "  FROM ["+DB_vg+"].[dbo].[arrbo_mtnve]\n" +
                                                        "  WHERE ? BETWEEN [am_fcha_arrbo] AND [am_fcha_fnlzcion] AND [am_mtnve] LIKE ?;");
                        queryBuscarCentroCostoGP.setString(1, mvtoEquipo.getFechaRegistro());
                        queryBuscarCentroCostoGP.setString(2, mvtoEquipo.getMotonave().getCodigo());
                        resultSetCentroCostoGP= queryBuscarCentroCostoGP.executeQuery();
                        while(resultSetCentroCostoGP.next()){ 
                            Objeto = new CentroCosto();
                            Objeto.setCodigo("NULL"); 
                            Objeto.setDescripción(resultSetCentroCostoGP.getString(1));
                        }   
                    }
                    control_gp.cerrarConexionBaseDatos();   
                }     
            }
        }
        System.out.println("CentroCosto:"+mvtoEquipo.getCodigo());
        return Objeto;
    }
}
