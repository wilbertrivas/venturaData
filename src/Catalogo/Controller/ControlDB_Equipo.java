package Catalogo.Controller;
 
import Catalogo.Model.CentroCostoEquipo;
import ConnectionDB.Conexion_DB_ERP;
import ConnectionDB.Conexion_DB_costos_vg;
import ModuloEquipo.Model.AutorizacionRecobro;
import Catalogo.Model.ClasificadorEquipo;
import Catalogo.Model.Equipo;
import Catalogo.Model.Pertenencia;
import Catalogo.Model.ProveedorEquipo;
import Catalogo.Model.TarifaEquipo;
import Catalogo.Model.TipoEquipo;
import ModuloPersonal.Model.Persona;
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
 
public class ControlDB_Equipo {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private String tipoConexion;
    
    public ControlDB_Equipo(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }   
    public ArrayList<AutorizacionRecobro> buscarAutorizacionRecobro(){
        ArrayList<AutorizacionRecobro> listadoAutorizacionRecobro = null;
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            PreparedStatement query= conexion.prepareStatement("SELECT  [are_cdgo]\n" +
                                                                "      ,[are_desc]\n" +
                                                                "      ,[are_estad]\n" +
                                                                "  FROM ["+DB+"].[dbo].[autorizacion_recobro] ORDER BY  [are_cdgo] DESC");
            resultSet= query.executeQuery();
            boolean validator=true;
            while(resultSet.next()){ 
                if(validator){
                    listadoAutorizacionRecobro = new ArrayList();
                    validator=false;
                }
                AutorizacionRecobro autorizacionRecobro = new AutorizacionRecobro(); 
                autorizacionRecobro.setCodigo(resultSet.getString(1));
                autorizacionRecobro.setDescripcion(resultSet.getString(2));
                autorizacionRecobro.setEstado(resultSet.getString(3));             
                listadoAutorizacionRecobro.add(autorizacionRecobro);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las Autotiraziones Recobros", "Error", JOptionPane.ERROR_MESSAGE);
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoAutorizacionRecobro;
    } 
    public int registrar(Equipo Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            if(validarExistenciaPorCodigo(Objeto)){
                System.out.println("Ya existe este Equipo en el sistema");
                //Validamos si el tipo de equipo existe en el sistema.
                if(!new ControlDB_TipoEquipo(tipoConexion).validarPorCodigo(Objeto.getTipoEquipo())){
                    //Procedemos a registrar el tipo de equipo
                    new ControlDB_TipoEquipo(tipoConexion).registrar(Objeto.getTipoEquipo(), us);
                }
                //Validamos si el Clasificador Equipo existe en el sistema.
                if(!(Objeto.getClasificador1().getCodigo() == null)){
                    if(!new ControlDB_ClasificadorEquipo(tipoConexion).validarPorCodigo(Objeto.getClasificador1())){
                        //Procedemos a registrar el tipo de equipo
                        new ControlDB_ClasificadorEquipo(tipoConexion).registrar(Objeto.getClasificador1(), us);
                    }
                }else{
                    ClasificadorEquipo clasificadorEquipo = new ClasificadorEquipo();
                    clasificadorEquipo.setCodigo("0");
                    clasificadorEquipo.setDescripcion("0");
                    clasificadorEquipo.setEstado("0");
                    Objeto.setClasificador1(clasificadorEquipo);
                }
                
                //Validamos si el Clasificador Equipo existe en el sistema.
                if(!(Objeto.getClasificador2().getCodigo() == null)){
                    if(!new ControlDB_ClasificadorEquipo(tipoConexion).validarPorCodigo(Objeto.getClasificador2())){
                        //Procedemos a registrar el tipo de equipo
                        new ControlDB_ClasificadorEquipo(tipoConexion).registrar(Objeto.getClasificador2(), us);
                    }
                }else{
                    ClasificadorEquipo clasificadorEquipo = new ClasificadorEquipo();
                    clasificadorEquipo.setCodigo("0");
                    clasificadorEquipo.setDescripcion("0");
                    clasificadorEquipo.setEstado("0");
                    Objeto.setClasificador2(clasificadorEquipo);
                }
                //Validamos si el Proveedor de equipo existe en el sistema.
                if(!new ControlDB_ProveedorEquipo(tipoConexion).validarPorCodigo(Objeto.getProveedorEquipo())){
                    //Procedemos a registrar el proveedor de Equipo
                    new ControlDB_ProveedorEquipo(tipoConexion).registrar(Objeto.getProveedorEquipo(), us);
                }
                 //Validamos si la pertenencia de equipo existe en el sistema.
                if(!new ControlDB_PertenenciaEquipo(tipoConexion).validarPorCodigo(Objeto.getPertenenciaEquipo())){
                    //Procedemos a registrar el proveedor de Equipo
                    new ControlDB_PertenenciaEquipo(tipoConexion).registrar(Objeto.getPertenenciaEquipo(), us);
                }
                //validamos si el centro de costo del equipo existe
                if(!new ControlDB_CentroCostoEquipo(tipoConexion).validarPorCodigo(Objeto.getCentroCostoEquipo())){
                    //Procedemos a registrar el proveedor de Equipo
                    new ControlDB_CentroCostoEquipo(tipoConexion).registrar(Objeto.getCentroCostoEquipo(), us);
                }else{//procedemos a actualizar el Centro de Costo
                    new ControlDB_CentroCostoEquipo(tipoConexion).actualizar(Objeto.getCentroCostoEquipo(), us);
                }
                actualizarSincronizadoERP(Objeto, us);

            }else{
                //Validamos si el tipo de equipo existe en el sistema.
                if(!new ControlDB_TipoEquipo(tipoConexion).validarPorCodigo(Objeto.getTipoEquipo())){
                    //Procedemos a registrar el tipo de equipo
                    new ControlDB_TipoEquipo(tipoConexion).registrar(Objeto.getTipoEquipo(), us);
                }
                //Validamos si el Clasificador Equipo existe en el sistema.
                if(!(Objeto.getClasificador1().getCodigo() == null)){
                    if(!new ControlDB_ClasificadorEquipo(tipoConexion).validarPorCodigo(Objeto.getClasificador1())){
                        //Procedemos a registrar el tipo de equipo
                        new ControlDB_ClasificadorEquipo(tipoConexion).registrar(Objeto.getClasificador1(), us);
                    }
                }else{
                    ClasificadorEquipo clasificadorEquipo = new ClasificadorEquipo();
                    clasificadorEquipo.setCodigo("0");
                    clasificadorEquipo.setDescripcion("0");
                    clasificadorEquipo.setEstado("0");
                    Objeto.setClasificador1(clasificadorEquipo);
                }
                
                //Validamos si el Clasificador Equipo existe en el sistema.
                if(!(Objeto.getClasificador2().getCodigo() == null)){
                    if(!new ControlDB_ClasificadorEquipo(tipoConexion).validarPorCodigo(Objeto.getClasificador2())){
                        //Procedemos a registrar el tipo de equipo
                        new ControlDB_ClasificadorEquipo(tipoConexion).registrar(Objeto.getClasificador2(), us);
                    }
                }else{
                    ClasificadorEquipo clasificadorEquipo = new ClasificadorEquipo();
                    clasificadorEquipo.setCodigo("0");
                    clasificadorEquipo.setDescripcion("0");
                    clasificadorEquipo.setEstado("0");
                    Objeto.setClasificador2(clasificadorEquipo);
                }
                //Validamos si el Proveedor de equipo existe en el sistema.
                if(!new ControlDB_ProveedorEquipo(tipoConexion).validarPorCodigo(Objeto.getProveedorEquipo())){
                    //Procedemos a registrar el proveedor de Equipo
                    new ControlDB_ProveedorEquipo(tipoConexion).registrar(Objeto.getProveedorEquipo(), us);
                }
                 //Validamos si la pertenencia de equipo existe en el sistema.
                if(!new ControlDB_PertenenciaEquipo(tipoConexion).validarPorCodigo(Objeto.getPertenenciaEquipo())){
                    //Procedemos a registrar el proveedor de Equipo
                    new ControlDB_PertenenciaEquipo(tipoConexion).registrar(Objeto.getPertenenciaEquipo(), us);
                }
                //validamos si el centro de costo del equipo existe
                if(Objeto.getCentroCostoEquipo().getCodigo() != null){
                    if(!new ControlDB_CentroCostoEquipo(tipoConexion).validarPorCodigo(Objeto.getCentroCostoEquipo())){
                        //Procedemos a registrar el proveedor de Equipo
                        new ControlDB_CentroCostoEquipo(tipoConexion).registrar(Objeto.getCentroCostoEquipo(), us);
                    }else{//procedemos a actualizar el Centro de Costo
                        new ControlDB_CentroCostoEquipo(tipoConexion).actualizar(Objeto.getCentroCostoEquipo(), us);
                    }
               }
                
                String estado;
                if(Objeto.getEstado().equalsIgnoreCase("1")){
                    estado="ACTIVO";
                }else{
                    estado="INACTIVO";
                }
                conexion= control.ConectarBaseDatos();
                String DB=control.getBaseDeDatos();
                PreparedStatement queryRegistrar= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[equipo]\n" +
                                                                                "           ([eq_cdgo]\n" +
                                                                                "           ,[eq_tipo_equipo_cdgo]\n" +
                                                                                "           ,[eq_codigo_barra]\n" +
                                                                                "           ,[eq_referencia]\n" +
                                                                                "           ,[eq_producto]\n" +
                                                                                "           ,[eq_capacidad]\n" +
                                                                                "           ,[eq_marca]\n" +
                                                                                "           ,[eq_modelo]\n" +
                                                                                "           ,[eq_serial]\n" +
                                                                                "           ,[eq_desc]\n" +
                                                                                "           ,[eq_clasificador1_cdgo]\n" +
                                                                                "           ,[eq_clasificador2_cdgo]\n" +
                                                                                "           ,[eq_proveedor_equipo_cdgo]\n" +
                                                                                "           ,[eq_equipo_pertenencia_cdgo]\n" +
                                                                                "           ,[eq_observ]\n" +
                                                                                "           ,[eq_estad]\n" +
                                                                                "           ,[eq_actvo_fijo_id]\n" +
                                                                                "           ,[eq_actvo_fijo_referencia]\n" +
                                                                                "           ,[eq_actvo_fijo_desc])\n" +
                                                                                "     VALUES\n" +
                                                                                "           (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

                queryRegistrar.setString(1, Objeto.getCodigo());
                queryRegistrar.setString(2, Objeto.getTipoEquipo().getCodigo());
                queryRegistrar.setString(3, Objeto.getCodigo_barra());
                queryRegistrar.setString(4, Objeto.getReferencia());
                queryRegistrar.setString(5, Objeto.getProducto());
                queryRegistrar.setString(6, Objeto.getCapacidad());
                queryRegistrar.setString(7, Objeto.getMarca());
                queryRegistrar.setString(8, Objeto.getModelo());
                queryRegistrar.setString(9, Objeto.getSerial());
                queryRegistrar.setString(10, Objeto.getDescripcion());
                queryRegistrar.setString(11, Objeto.getClasificador1().getCodigo());
                queryRegistrar.setString(12, Objeto.getClasificador2().getCodigo());
                queryRegistrar.setString(13, Objeto.getProveedorEquipo().getCodigo());
                queryRegistrar.setString(14, Objeto.getPertenenciaEquipo().getCodigo());
                queryRegistrar.setString(15, Objeto.getObservacion());
                queryRegistrar.setString(16, Objeto.getEstado());
                queryRegistrar.setString(17, Objeto.getActivoFijo_codigo());
                queryRegistrar.setString(18, Objeto.getActivoFijo_referencia());
                queryRegistrar.setString(19, Objeto.getActivoFijo_descripcion());
                queryRegistrar.execute();
                result=1;
                if(result==1){
                    result=0;
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
                        "           ,'EQUIPO'" +
                        "           ,CONCAT (?,?,?));");
                    Query_AuditoriaInsert.setString(1, us.getCodigo());
                    Query_AuditoriaInsert.setString(2, namePc);
                    Query_AuditoriaInsert.setString(3, ipPc);
                    Query_AuditoriaInsert.setString(4, macPC);
                    Query_AuditoriaInsert.setString(5, Objeto.getCodigo()); 
                    Query_AuditoriaInsert.setString(6, "Se registró un nuevo equipo en el sistema, con Código: ");
                    Query_AuditoriaInsert.setString(7, Objeto.getCodigo());
                    Query_AuditoriaInsert.setString(8,  " Tipo: "+Objeto.getTipoEquipo().getDescripcion()+
                                                        " Referencia: "+Objeto.getReferencia()+
                                                        " Código_Barra: "+Objeto.getCodigo_barra()+
                                                        " Producto: "+Objeto.getProducto()+
                                                        " Capacidad: "+Objeto.getCapacidad()+ 
                                                        " Marca: "+Objeto.getMarca()+
                                                        " Modelo: "+Objeto.getModelo()+
                                                        " Serial: "+Objeto.getSerial()+
                                                        " Descripcion: "+Objeto.getDescripcion()+
                                                        " Clasificador1: "+Objeto.getClasificador1().getDescripcion()+
                                                        " Clasificador2: "+Objeto.getClasificador2().getDescripcion()+
                                                        " ProveedorEquipo: "+Objeto.getProveedorEquipo().getDescripcion()+
                                                        " PertenenciaEquipo: "+Objeto.getPertenenciaEquipo().getDescripcion()+
                                                        " Observación: "+Objeto.getObservacion()+
                                                        " Estado: "+estado+
                                                        " ActivoFijo_código: "+Objeto.getActivoFijo_codigo()+
                                                        " ActivoFijo_referencia: "+Objeto.getActivoFijo_referencia()+
                                                        " ActivoFijo_descripción: "+Objeto.getActivoFijo_descripcion());
                    Query_AuditoriaInsert.execute();
                    result=1; 
                }
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
    public boolean validarExistencia(Equipo Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement queryValidarExistencia= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[equipo] WHERE [eq_desc] =? AND [eq_tipo_equipo_cdgo]=? AND eq_marca=?;");
            queryValidarExistencia.setString(1, Objeto.getDescripcion());
            queryValidarExistencia.setString(2, Objeto.getTipoEquipo().getCodigo());
            queryValidarExistencia.setString(3, Objeto.getMarca());
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
    public boolean validarExistenciaPorCodigo(Equipo Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement queryValidarExistencia= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[equipo] WHERE [eq_cdgo] =?;");
            queryValidarExistencia.setString(1, Objeto.getCodigo());
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
    public boolean validarExistenciaTipoEquipo(TipoEquipo Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement queryValidarExistencia= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[equipo] WHERE [eq_cdgo] =?;");
            queryValidarExistencia.setString(1, Objeto.getCodigo());
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
    public boolean validarExistenciaActualizar(Equipo Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement queryValidarExistencia= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[equipo] WHERE [eq_desc] =? AND [eq_tipo_equipo_cdgo]=? AND eq_marca=? AND [eq_cdgo]<> ?;");
            queryValidarExistencia.setString(1, Objeto.getDescripcion());
            queryValidarExistencia.setString(2, Objeto.getTipoEquipo().getCodigo());
            queryValidarExistencia.setString(3, Objeto.getMarca());
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
    public int buscar_nombre(String nombre) throws SQLException{
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscarNombre= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[equipo] WHERE [eq_desc] like ?;");
            queryBuscarNombre.setString(1, nombre);
            ResultSet resultSetBuscarNombre= queryBuscarNombre.executeQuery();
            while(resultSetBuscarNombre.next()){ 
                return resultSetBuscarNombre.getInt(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return 0;
    } 
    public ArrayList<Equipo> buscar(String valorConsulta) throws SQLException{
        ArrayList<Equipo> listadoObjeto = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            if(valorConsulta.equals("")){
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT "+
                                                            " [eq_cdgo] --1\n" +
                                                            "      ,[eq_tipo_equipo_cdgo] --2\n" +
                                                            "           ,[te_cdgo] --3\n" +
                                                            "           ,[te_desc] --4\n" +
                                                            "           ,[te_estad] --5\n" +
                                                            "      ,[eq_referencia] --6\n" +
                                                            "      ,[eq_producto] --7\n" +
                                                            "      ,[eq_capacidad] --8\n" +
                                                            "      ,[eq_marca] --9\n" +    
                                                            "      ,[eq_modelo] --10\n" +    
                                                            "      ,[eq_serial] --11\n" +       
                                                            "      ,[eq_desc]  --12\n" +
                                                            "      ,[eq_clasificador1_cdgo] --13\n" +
                                                            "           ,clasificador1.[ce_cdgo] AS clasificador1_cdgo --14\n" +
                                                            "           ,clasificador1.[ce_desc] AS clasificador1_desc  --15\n" +
                                                            "           ,clasificador1.[ce_estad] AS clasificador1_estad --16\n" +
                                                            "      ,[eq_clasificador2_cdgo] --17\n" +
                                                            "           ,clasificador2.[ce_cdgo] AS clasificador2_cdgo --18\n" +
                                                            "           ,clasificador2.[ce_desc] AS clasificador2_desc --19\n" +
                                                            "           ,clasificador2.[ce_estad] AS clasificador2_estad --20\n" +
                                                            "      ,[eq_proveedor_equipo_cdgo] --21\n" +
                                                            "      ,[eq_proveedor_equipo_cdgo] --22\n" +
                                                            "           ,[pe_cdgo] --23\n" +
                                                            "           ,[pe_desc] --24\n" +
                                                            "           ,[pe_estad] --25\n" +
                                                            "      ,[eq_equipo_pertenencia_cdgo] --26\n" +
                                                            "           ,[ep_cdgo] --27\n" +
                                                            "           ,[ep_desc] --28\n" +
                                                            "           ,[ep_estad] --29\n" +
                                                            "      ,[eq_observ] --30\n" +
                                                            "      ,[eq_codigo_barra] --31\n" +
                                                            "      ,CASE WHEN (eq_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS eq_estad --32\n" +
                                                            "  ,[eq_actvo_fijo_id]"+
                                                            "  ,[eq_actvo_fijo_referencia]"+
                                                            "  ,[eq_actvo_fijo_desc]"+
                                                            "  FROM ["+DB+"].[dbo].[equipo]\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] ON te_cdgo = eq_tipo_equipo_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON pe_cdgo= eq_proveedor_equipo_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador1 ON clasificador1.ce_cdgo = eq_clasificador1_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador2 ON clasificador2.ce_cdgo = eq_clasificador2_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON ep_cdgo=eq_equipo_pertenencia_cdgo;");
                resultSetBuscar= queryBuscar.executeQuery();
            }else{
                PreparedStatement queryBuscar= conexion.prepareStatement("SELECT \n" +
                                                            "	   [eq_cdgo] --1\n" +
                                                            "      ,[eq_tipo_equipo_cdgo] --2\n" +
                                                            "           ,[te_cdgo] --3\n" +
                                                            "           ,[te_desc] --4\n" +
                                                            "           ,[te_estad] --5\n" +
                                                            "      ,[eq_referencia] --6\n" +
                                                            "      ,[eq_producto] --7\n" +
                                                            "      ,[eq_capacidad] --8\n" +
                                                            "      ,[eq_marca] --9\n" +    
                                                            "      ,[eq_modelo] --10\n" +    
                                                            "      ,[eq_serial] --11\n" +       
                                                            "      ,[eq_desc]  --12\n" +
                                                            "      ,[eq_clasificador1_cdgo] --13\n" +
                                                            "           ,clasificador1.[ce_cdgo] AS clasificador1_cdgo --14\n" +
                                                            "           ,clasificador1.[ce_desc] AS clasificador1_desc  --15\n" +
                                                            "           ,clasificador1.[ce_estad] AS clasificador1_estad --16\n" +
                                                            "      ,[eq_clasificador2_cdgo] --17\n" +
                                                            "           ,clasificador2.[ce_cdgo] AS clasificador2_cdgo --18\n" +
                                                            "           ,clasificador2.[ce_desc] AS clasificador2_desc --19\n" +
                                                            "           ,clasificador2.[ce_estad] AS clasificador2_estad --20\n" +
                                                            "      ,[eq_proveedor_equipo_cdgo] --21\n" +
                                                            "      ,[eq_proveedor_equipo_cdgo] --22\n" +
                                                            "           ,[pe_cdgo] --23\n" +
                                                            "           ,[pe_desc] --24\n" +
                                                            "           ,[pe_estad] --25\n" +
                                                            "      ,[eq_equipo_pertenencia_cdgo] --26\n" +
                                                            "           ,[ep_cdgo] --27\n" +
                                                            "           ,[ep_desc] --28\n" +
                                                            "           ,[ep_estad] --29\n" +
                                                            "      ,[eq_observ] --30\n" +
                                                            "      ,[eq_codigo_barra] --31\n" +
                                                            "      ,CASE WHEN (eq_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS eq_estad --32\n" +
                                                            "  ,[eq_actvo_fijo_id]"+
                                                            "  ,[eq_actvo_fijo_referencia]"+
                                                            "  ,[eq_actvo_fijo_desc]"+
                                                            "  FROM ["+DB+"].[dbo].[equipo]\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] ON te_cdgo = eq_tipo_equipo_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON pe_cdgo= eq_proveedor_equipo_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador1 ON clasificador1.ce_cdgo = eq_clasificador1_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador2 ON clasificador2.ce_cdgo = eq_clasificador2_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON ep_cdgo=eq_equipo_pertenencia_cdgo "+
                                                            "   WHERE [eq_desc] like ?;");
                queryBuscar.setString(1, "%"+valorConsulta+"%");
                resultSetBuscar= queryBuscar.executeQuery();
            }
            while(resultSetBuscar.next()){ 
                Equipo Objeto = new Equipo(); 
                Objeto.setCodigo(resultSetBuscar.getString(1));
                Objeto.setTipoEquipo(new TipoEquipo(resultSetBuscar.getString(3),resultSetBuscar.getString(4),resultSetBuscar.getString(5)));
                Objeto.setCodigo_barra(resultSetBuscar.getString(31));
                Objeto.setReferencia(resultSetBuscar.getString(6));
                Objeto.setProducto(resultSetBuscar.getString(7));
                Objeto.setCapacidad(resultSetBuscar.getString(8));
                Objeto.setMarca(resultSetBuscar.getString(9));
                Objeto.setModelo(resultSetBuscar.getString(10));
                Objeto.setSerial(resultSetBuscar.getString(11));
                Objeto.setDescripcion(resultSetBuscar.getString(12));
                Objeto.setClasificador1(new ClasificadorEquipo(resultSetBuscar.getString(14),resultSetBuscar.getString(15),resultSetBuscar.getString(16)));
                Objeto.setClasificador2(new ClasificadorEquipo(resultSetBuscar.getString(18),resultSetBuscar.getString(19),resultSetBuscar.getString(20)));
                Objeto.setProveedorEquipo(new ProveedorEquipo(resultSetBuscar.getString(23),resultSetBuscar.getString(24),resultSetBuscar.getString(25)));
                Objeto.setPertenenciaEquipo(new Pertenencia(resultSetBuscar.getString(27),resultSetBuscar.getString(28),resultSetBuscar.getString(29)));
                Objeto.setObservacion(resultSetBuscar.getString(30));
                Objeto.setEstado(resultSetBuscar.getString(32));
                Objeto.setActivoFijo_codigo(resultSetBuscar.getString(33));
                Objeto.setActivoFijo_referencia(resultSetBuscar.getString(34));
                Objeto.setActivoFijo_descripcion(resultSetBuscar.getString(35));
                listadoObjeto.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjeto;
    } 
    public Equipo buscarEspecifico(String codigo) throws SQLException{
        Equipo Objeto = null; 
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT [eq_cdgo] --1\n" +
                                                            "      ,[eq_tipo_equipo_cdgo] --2\n" +
                                                            "           ,[te_cdgo] --3\n" +
                                                            "           ,[te_desc] --4\n" +
                                                            "           ,[te_estad] --5\n" +
                                                            "      ,[eq_referencia] --6\n" +
                                                            "      ,[eq_producto] --7\n" +
                                                            "      ,[eq_capacidad] --8\n" +
                                                            "      ,[eq_marca] --9\n" +    
                                                            "      ,[eq_modelo] --10\n" +    
                                                            "      ,[eq_serial] --11\n" +       
                                                            "      ,[eq_desc]  --12\n" +
                                                            "      ,[eq_clasificador1_cdgo] --13\n" +
                                                            "           ,clasificador1.[ce_cdgo] AS clasificador1_cdgo --14\n" +
                                                            "           ,clasificador1.[ce_desc] AS clasificador1_desc  --15\n" +
                                                            "           ,clasificador1.[ce_estad] AS clasificador1_estad --16\n" +
                                                            "      ,[eq_clasificador2_cdgo] --17\n" +
                                                            "           ,clasificador2.[ce_cdgo] AS clasificador2_cdgo --18\n" +
                                                            "           ,clasificador2.[ce_desc] AS clasificador2_desc --19\n" +
                                                            "           ,clasificador2.[ce_estad] AS clasificador2_estad --20\n" +
                                                            "      ,[eq_proveedor_equipo_cdgo] --21\n" +
                                                            "      ,[eq_proveedor_equipo_cdgo] --22\n" +
                                                            "           ,[pe_cdgo] --23\n" +
                                                            "           ,[pe_desc] --24\n" +
                                                            "           ,[pe_estad] --25\n" +
                                                            "      ,[eq_equipo_pertenencia_cdgo] --26\n" +
                                                            "           ,[ep_cdgo] --27\n" +
                                                            "           ,[ep_desc] --28\n" +
                                                            "           ,[ep_estad] --29\n" +
                                                            "      ,[eq_observ] --30\n" +
                                                            "      ,[eq_codigo_barra] --31\n" +
                                                            "      ,CASE WHEN (eq_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS eq_estad --32\n" +
                                                            "  ,[eq_actvo_fijo_id]"+
                                                            "  ,[eq_actvo_fijo_referencia]"+
                                                            "  ,[eq_actvo_fijo_desc]"+
                                                            "  FROM ["+DB+"].[dbo].[equipo]\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] ON te_cdgo = eq_tipo_equipo_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON pe_cdgo= eq_proveedor_equipo_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador1 ON clasificador1.ce_cdgo = eq_clasificador1_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador2 ON clasificador2.ce_cdgo = eq_clasificador2_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON ep_cdgo=eq_equipo_pertenencia_cdgo "
                                                                    + "  WHERE [eq_cdgo] =?;");
            queryBuscar.setString(1, codigo);
            ResultSet resultSetBuscar= queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                Objeto = new Equipo(); 
                Objeto.setCodigo(resultSetBuscar.getString(1));
                Objeto.setTipoEquipo(new TipoEquipo(resultSetBuscar.getString(3),resultSetBuscar.getString(4),resultSetBuscar.getString(5)));
                Objeto.setCodigo_barra(resultSetBuscar.getString(31));
                Objeto.setReferencia(resultSetBuscar.getString(6));
                Objeto.setProducto(resultSetBuscar.getString(7));
                Objeto.setCapacidad(resultSetBuscar.getString(8));
                Objeto.setMarca(resultSetBuscar.getString(9));
                Objeto.setModelo(resultSetBuscar.getString(10));
                Objeto.setSerial(resultSetBuscar.getString(11));
                Objeto.setDescripcion(resultSetBuscar.getString(12));
                Objeto.setClasificador1(new ClasificadorEquipo(resultSetBuscar.getString(14),resultSetBuscar.getString(15),resultSetBuscar.getString(16)));
                Objeto.setClasificador2(new ClasificadorEquipo(resultSetBuscar.getString(18),resultSetBuscar.getString(19),resultSetBuscar.getString(20)));
                Objeto.setProveedorEquipo(new ProveedorEquipo(resultSetBuscar.getString(23),resultSetBuscar.getString(24),resultSetBuscar.getString(25)));
                Objeto.setPertenenciaEquipo(new Pertenencia(resultSetBuscar.getString(27),resultSetBuscar.getString(28),resultSetBuscar.getString(29)));
                Objeto.setObservacion(resultSetBuscar.getString(30));
                Objeto.setEstado(resultSetBuscar.getString(32));
                Objeto.setActivoFijo_codigo(resultSetBuscar.getString(33));
                Objeto.setActivoFijo_referencia(resultSetBuscar.getString(34));
                Objeto.setActivoFijo_descripcion(resultSetBuscar.getString(35));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return Objeto;
    }
    public Equipo buscarId(int codigo) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        Equipo Objeto = new Equipo(); 
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT [eq_cdgo] --1\n" +
                                                            "      ,[eq_tipo_equipo_cdgo] --2\n" +
                                                            "           ,[te_cdgo] --3\n" +
                                                            "           ,[te_desc] --4\n" +
                                                            "           ,[te_estad] --5\n" +
                                                            "      ,[eq_referencia] --6\n" +
                                                            "      ,[eq_producto] --7\n" +
                                                            "      ,[eq_capacidad] --8\n" +
                                                            "      ,[eq_marca] --9\n" +    
                                                            "      ,[eq_modelo] --10\n" +    
                                                            "      ,[eq_serial] --11\n" +       
                                                            "      ,[eq_desc]  --12\n" +
                                                            "      ,[eq_clasificador1_cdgo] --13\n" +
                                                            "           ,clasificador1.[ce_cdgo] AS clasificador1_cdgo --14\n" +
                                                            "           ,clasificador1.[ce_desc] AS clasificador1_desc  --15\n" +
                                                            "           ,clasificador1.[ce_estad] AS clasificador1_estad --16\n" +
                                                            "      ,[eq_clasificador2_cdgo] --17\n" +
                                                            "           ,clasificador2.[ce_cdgo] AS clasificador2_cdgo --18\n" +
                                                            "           ,clasificador2.[ce_desc] AS clasificador2_desc --19\n" +
                                                            "           ,clasificador2.[ce_estad] AS clasificador2_estad --20\n" +
                                                            "      ,[eq_proveedor_equipo_cdgo] --21\n" +
                                                            "      ,[eq_proveedor_equipo_cdgo] --22\n" +
                                                            "           ,[pe_cdgo] --23\n" +
                                                            "           ,[pe_desc] --24\n" +
                                                            "           ,[pe_estad] --25\n" +
                                                            "      ,[eq_equipo_pertenencia_cdgo] --26\n" +
                                                            "           ,[ep_cdgo] --27\n" +
                                                            "           ,[ep_desc] --28\n" +
                                                            "           ,[ep_estad] --29\n" +
                                                            "      ,[eq_observ] --30\n" +
                                                            "      ,[eq_codigo_barra] --31\n" +
                                                            "      ,CASE WHEN (eq_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS eq_estad --32\n" +
                                                            "  ,[eq_actvo_fijo_id]"+
                                                            "  ,[eq_actvo_fijo_referencia]"+
                                                            "  ,[eq_actvo_fijo_desc]"+
                                                            "  FROM ["+DB+"].[dbo].[equipo]\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] ON te_cdgo = eq_tipo_equipo_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON pe_cdgo= eq_proveedor_equipo_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador1 ON clasificador1.ce_cdgo = eq_clasificador1_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador2 ON clasificador2.ce_cdgo = eq_clasificador2_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON ep_cdgo=eq_equipo_pertenencia_cdgo "
                                                                    + "   WHERE [eq_cdgo] =?;");
            queryBuscar.setString(1, ""+codigo);
            ResultSet resultSetBuscar= queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                Objeto = new Equipo(); 
                Objeto.setCodigo(resultSetBuscar.getString(1));
                Objeto.setTipoEquipo(new TipoEquipo(resultSetBuscar.getString(3),resultSetBuscar.getString(4),resultSetBuscar.getString(5)));
                Objeto.setCodigo_barra(resultSetBuscar.getString(31));
                Objeto.setReferencia(resultSetBuscar.getString(6));
                Objeto.setProducto(resultSetBuscar.getString(7));
                Objeto.setCapacidad(resultSetBuscar.getString(8));
                Objeto.setMarca(resultSetBuscar.getString(9));
                Objeto.setModelo(resultSetBuscar.getString(10));
                Objeto.setSerial(resultSetBuscar.getString(11));
                Objeto.setDescripcion(resultSetBuscar.getString(12));
                Objeto.setClasificador1(new ClasificadorEquipo(resultSetBuscar.getString(14),resultSetBuscar.getString(15),resultSetBuscar.getString(16)));
                Objeto.setClasificador2(new ClasificadorEquipo(resultSetBuscar.getString(18),resultSetBuscar.getString(19),resultSetBuscar.getString(20)));
                Objeto.setProveedorEquipo(new ProveedorEquipo(resultSetBuscar.getString(23),resultSetBuscar.getString(24),resultSetBuscar.getString(25)));
                Objeto.setPertenenciaEquipo(new Pertenencia(resultSetBuscar.getString(27),resultSetBuscar.getString(28),resultSetBuscar.getString(29)));
                Objeto.setObservacion(resultSetBuscar.getString(30));
                Objeto.setEstado(resultSetBuscar.getString(32));
                Objeto.setActivoFijo_codigo(resultSetBuscar.getString(33));
                Objeto.setActivoFijo_referencia(resultSetBuscar.getString(34));
                Objeto.setActivoFijo_descripcion(resultSetBuscar.getString(35));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return Objeto;
    } 
    public ArrayList<Equipo> buscarActivos() throws SQLException{
        ArrayList<Equipo> listadoObjetos = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT [eq_cdgo] --1\n" +
                                                            "      ,[eq_tipo_equipo_cdgo] --2\n" +
                                                            "           ,[te_cdgo] --3\n" +
                                                            "           ,[te_desc] --4\n" +
                                                            "           ,[te_estad] --5\n" +
                                                            "      ,[eq_referencia] --6\n" +
                                                            "      ,[eq_producto] --7\n" +
                                                            "      ,[eq_capacidad] --8\n" +
                                                            "      ,[eq_marca] --9\n" +    
                                                            "      ,[eq_modelo] --10\n" +    
                                                            "      ,[eq_serial] --11\n" +       
                                                            "      ,[eq_desc]  --12\n" +
                                                            "      ,[eq_clasificador1_cdgo] --13\n" +
                                                            "           ,clasificador1.[ce_cdgo] AS clasificador1_cdgo --14\n" +
                                                            "           ,clasificador1.[ce_desc] AS clasificador1_desc  --15\n" +
                                                            "           ,clasificador1.[ce_estad] AS clasificador1_estad --16\n" +
                                                            "      ,[eq_clasificador2_cdgo] --17\n" +
                                                            "           ,clasificador2.[ce_cdgo] AS clasificador2_cdgo --18\n" +
                                                            "           ,clasificador2.[ce_desc] AS clasificador2_desc --19\n" +
                                                            "           ,clasificador2.[ce_estad] AS clasificador2_estad --20\n" +
                                                            "      ,[eq_proveedor_equipo_cdgo] --21\n" +
                                                            "      ,[eq_proveedor_equipo_cdgo] --22\n" +
                                                            "           ,[pe_cdgo] --23\n" +
                                                            "           ,[pe_desc] --24\n" +
                                                            "           ,[pe_estad] --25\n" +
                                                            "      ,[eq_equipo_pertenencia_cdgo] --26\n" +
                                                            "           ,[ep_cdgo] --27\n" +
                                                            "           ,[ep_desc] --28\n" +
                                                            "           ,[ep_estad] --29\n" +
                                                            "      ,[eq_observ] --30\n" +
                                                            "      ,[eq_codigo_barra] --31\n" +
                                                            "      ,CASE WHEN (eq_estad=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS eq_estad --32\n" +
                                                            "  ,[eq_actvo_fijo_id]"+
                                                            "  ,[eq_actvo_fijo_referencia]"+
                                                            "  ,[eq_actvo_fijo_desc]"+
                                                            " FROM ["+DB+"].[dbo].[equipo]\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] ON te_cdgo = eq_tipo_equipo_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON pe_cdgo= eq_proveedor_equipo_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador1 ON clasificador1.ce_cdgo = eq_clasificador1_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador2 ON clasificador2.ce_cdgo = eq_clasificador2_cdgo\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON ep_cdgo=eq_equipo_pertenencia_cdgo "
                                                                    + " WHERE [eq_estad]=1;");
           
            ResultSet resultSetBuscar= queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                Equipo Objeto = new Equipo(); 
                Objeto.setCodigo(resultSetBuscar.getString(1));
                Objeto.setTipoEquipo(new TipoEquipo(resultSetBuscar.getString(3),resultSetBuscar.getString(4),resultSetBuscar.getString(5)));
                Objeto.setCodigo_barra(resultSetBuscar.getString(31));
                Objeto.setReferencia(resultSetBuscar.getString(6));
                Objeto.setProducto(resultSetBuscar.getString(7));
                Objeto.setCapacidad(resultSetBuscar.getString(8));
                Objeto.setMarca(resultSetBuscar.getString(9));
                Objeto.setModelo(resultSetBuscar.getString(10));
                Objeto.setSerial(resultSetBuscar.getString(11));
                Objeto.setDescripcion(resultSetBuscar.getString(12));
                Objeto.setClasificador1(new ClasificadorEquipo(resultSetBuscar.getString(14),resultSetBuscar.getString(15),resultSetBuscar.getString(16)));
                Objeto.setClasificador2(new ClasificadorEquipo(resultSetBuscar.getString(18),resultSetBuscar.getString(19),resultSetBuscar.getString(20)));
                Objeto.setProveedorEquipo(new ProveedorEquipo(resultSetBuscar.getString(23),resultSetBuscar.getString(24),resultSetBuscar.getString(25)));
                Objeto.setPertenenciaEquipo(new Pertenencia(resultSetBuscar.getString(27),resultSetBuscar.getString(28),resultSetBuscar.getString(29)));
                Objeto.setObservacion(resultSetBuscar.getString(30));
                Objeto.setEstado(resultSetBuscar.getString(32));
                Objeto.setActivoFijo_codigo(resultSetBuscar.getString(33));
                Objeto.setActivoFijo_referencia(resultSetBuscar.getString(34));
                Objeto.setActivoFijo_descripcion(resultSetBuscar.getString(35));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public int actualizar(Equipo Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{ 
            Equipo EquipoAnterior=buscarEspecifico(""+Objeto.getCodigo());
            String valorEstado="";          
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                valorEstado="ACTIVO";
            }else{
                valorEstado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[equipo] SET \n" +
                                                                                        "      [eq_tipo_equipo_cdgo]=?\n" +
                                                                                        "      ,[eq_codigo_barra]=?\n" +
                                                                                        "      ,[eq_referencia]=?\n" +
                                                                                        "      ,[eq_producto]=?\n" +
                                                                                        "      ,[eq_capacidad]=?\n" +
                                                                                        "      ,[eq_marca]=?\n" +
                                                                                        "      ,[eq_modelo]=?\n" +
                                                                                        "      ,[eq_serial]=?\n" +
                                                                                        "      ,[eq_desc]=?\n" +
                                                                                        "      ,[eq_clasificador1_cdgo]=?\n" +
                                                                                        "      ,[eq_clasificador2_cdgo]=?\n" +
                                                                                        "      ,[eq_proveedor_equipo_cdgo]=?\n" +
                                                                                        "      ,[eq_equipo_pertenencia_cdgo]=?\n" +
                                                                                        "      ,[eq_observ]=?\n" +
                                                                                        "      ,[eq_estad]=?\n" +
                                                                                        "      ,[eq_actvo_fijo_id]=?\n" +
                                                                                        "      ,[eq_actvo_fijo_referencia]=?\n" +
                                                                                        "      ,[eq_actvo_fijo_desc]=? WHERE [eq_cdgo]=?;");
            queryActualizar.setString(1, Objeto.getTipoEquipo().getCodigo());
            queryActualizar.setString(2, Objeto.getCodigo_barra());
            queryActualizar.setString(3, Objeto.getReferencia());
            queryActualizar.setString(4, Objeto.getProducto());
            queryActualizar.setString(5, Objeto.getCapacidad());
            queryActualizar.setString(6, Objeto.getMarca());
            queryActualizar.setString(7, Objeto.getModelo());
            queryActualizar.setString(8, Objeto.getSerial());
            queryActualizar.setString(9, Objeto.getDescripcion());
            queryActualizar.setString(10, Objeto.getClasificador1().getCodigo());
            queryActualizar.setString(11, Objeto.getClasificador2().getCodigo());
            queryActualizar.setString(12, Objeto.getProveedorEquipo().getCodigo());
            queryActualizar.setString(13, Objeto.getPertenenciaEquipo().getCodigo());
            queryActualizar.setString(14, Objeto.getObservacion());
            queryActualizar.setString(15, Objeto.getEstado());
            queryActualizar.setString(16, Objeto.getActivoFijo_codigo());
            queryActualizar.setString(17, Objeto.getActivoFijo_referencia());
            queryActualizar.setString(18, Objeto.getActivoFijo_descripcion());
            queryActualizar.setString(19, Objeto.getCodigo());
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
                        "           ,'EQUIPO'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre el equipo, con',?,' actualizando la siguiente informacion a ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, EquipoAnterior.getCodigo());   
                Query_Auditoria.setString(6, 
                        " Equipo_Código: "+EquipoAnterior.getCodigo()+
                        " Equipo_CódigoBarra: "+EquipoAnterior.getCodigo_barra()+
                        " Equipo_Referencia: "+EquipoAnterior.getReferencia()+
                        " Equipo_Producto: "+EquipoAnterior.getProducto()+
                        " Equipo_Capacidad: "+EquipoAnterior.getCapacidad()+
                        " Equipo_Marca: "+EquipoAnterior.getMarca()+
                        " Equipo_Clasificador1: "+EquipoAnterior.getClasificador1().getDescripcion()+
                        " Equipo_Clasificador2: "+EquipoAnterior.getClasificador2().getDescripcion()+
                        " Equipo_Proveedor_Código: "+EquipoAnterior.getProveedorEquipo().getCodigo()+
                        " Equipo_Proveedor_Nombre: "+EquipoAnterior.getProveedorEquipo().getDescripcion()+
                        " Equipo_Pertenencia: "+EquipoAnterior.getPertenenciaEquipo().getDescripcion()+
                        " Equipo_Observación: "+EquipoAnterior.getObservacion()+
                        " Equipo_Estado: "+EquipoAnterior.getEstado()+
                        " Equipo_ActivoFijo_Código: "+EquipoAnterior.getActivoFijo_codigo()+
                        " Equipo_ActivoFijo_Referencia: "+EquipoAnterior.getActivoFijo_referencia()+
                        " Equipo_ActivoFijo_Descripción: "+EquipoAnterior.getActivoFijo_descripcion());
                Query_Auditoria.setString(7,  
                       " Equipo_Código: "+Objeto.getCodigo()+
                        " Equipo_Tipo: "+Objeto.getTipoEquipo().getDescripcion()+
                        " Equipo_CódigoBarra: "+Objeto.getCodigo_barra()+
                        " Equipo_Referencia: "+Objeto.getReferencia()+
                        " Equipo_Producto: "+Objeto.getProducto()+
                        " Equipo_Capacidad: "+Objeto.getCapacidad()+
                        " Equipo_Marca: "+Objeto.getMarca()+
                        " Equipo_Clasificador1: "+Objeto.getClasificador1().getDescripcion()+
                        " Equipo_Clasificador2: "+Objeto.getClasificador2().getDescripcion()+
                        " Equipo_Proveedor_Código: "+Objeto.getProveedorEquipo().getCodigo()+
                        " Equipo_Proveedor_Nombre: "+Objeto.getProveedorEquipo().getDescripcion()+
                        " Equipo_Pertenencia: "+Objeto.getPertenenciaEquipo().getDescripcion()+
                        " Equipo_Observación: "+Objeto.getObservacion()+
                        " Equipo_Estado: "+valorEstado+
                        " Equipo_ActivoFijo_Código: "+Objeto.getActivoFijo_codigo()+
                        " Equipo_ActivoFijo_Referencia: "+Objeto.getActivoFijo_referencia()+
                        " Equipo_ActivoFijo_Descripción: "+Objeto.getActivoFijo_descripcion());
                Query_Auditoria.execute();
                result=1;
            }
        }
        catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "Error al tratar de actualizar el equipo");
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    }  
    public int actualizarSincronizadoERP(Equipo Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{ 
            //validamos si el centro de costo del equipo existe
            if(!new ControlDB_CentroCostoEquipo(tipoConexion).validarPorCodigo(Objeto.getCentroCostoEquipo())){
                //Procedemos a registrar el proveedor de Equipo
                new ControlDB_CentroCostoEquipo(tipoConexion).registrar(Objeto.getCentroCostoEquipo(), us);
            }else{//procedemos a actualizar el Centro de Costo
                new ControlDB_CentroCostoEquipo(tipoConexion).actualizar(Objeto.getCentroCostoEquipo(), us);
            }
            
            Equipo EquipoAnterior=buscarEspecifico(""+Objeto.getCodigo());
            String valorEstado="";          
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                valorEstado="ACTIVO";
            }else{
                valorEstado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement queryActualizar= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[equipo] SET \n" +
                                                                                        "      [eq_tipo_equipo_cdgo]=?\n" +
                                                                                        "      ,[eq_codigo_barra]=?\n" +
                                                                                        "      ,[eq_referencia]=?\n" +
                                                                                        "      ,[eq_producto]=?\n" +
                                                                                        "      ,[eq_capacidad]=?\n" +
                                                                                        "      ,[eq_marca]=?\n" +
                                                                                        "      ,[eq_modelo]=?\n" +
                                                                                        "      ,[eq_serial]=?\n" +
                                                                                        "      ,[eq_desc]=?\n" +
                                                                                        "      ,[eq_clasificador1_cdgo]=?\n" +
                                                                                        "      ,[eq_clasificador2_cdgo]=?\n" +
                                                                                        "      ,[eq_proveedor_equipo_cdgo]=?\n" +
                                                                                        "      ,[eq_equipo_pertenencia_cdgo]=?\n" +
                                                                                        "      ,[eq_observ]=?\n" +
                                                                                        "      ,[eq_estad]=?\n" +
                                                                                        "      ,[eq_actvo_fijo_id]=?\n" +
                                                                                        "      ,[eq_actvo_fijo_referencia]=?\n" +
                                                                                        "      ,[eq_actvo_fijo_desc]=? \n"+
                                                                                        "      ,[eq_cntro_cost_equipo_cdgo]=? \n"+
                                                                                        " WHERE [eq_cdgo]=?;");
            queryActualizar.setString(1, Objeto.getTipoEquipo().getCodigo());
            queryActualizar.setString(2, Objeto.getCodigo_barra());
            queryActualizar.setString(3, Objeto.getReferencia());
            queryActualizar.setString(4, Objeto.getProducto());
            queryActualizar.setString(5, Objeto.getCapacidad());
            queryActualizar.setString(6, Objeto.getMarca());
            queryActualizar.setString(7, Objeto.getModelo());
            queryActualizar.setString(8, Objeto.getSerial());
            queryActualizar.setString(9, Objeto.getDescripcion());
            queryActualizar.setString(10, Objeto.getClasificador1().getCodigo());
            queryActualizar.setString(11, Objeto.getClasificador2().getCodigo());
            queryActualizar.setString(12, Objeto.getProveedorEquipo().getCodigo());
            queryActualizar.setString(13, Objeto.getPertenenciaEquipo().getCodigo());
            queryActualizar.setString(14, Objeto.getObservacion());
            queryActualizar.setString(15, Objeto.getEstado());
            queryActualizar.setString(16, Objeto.getActivoFijo_codigo());
            queryActualizar.setString(17, Objeto.getActivoFijo_referencia());
            queryActualizar.setString(18, Objeto.getActivoFijo_descripcion());
            if(Objeto.getCentroCostoEquipo().getCodigo() != null){
                queryActualizar.setString(19, Objeto.getCentroCostoEquipo().getCodigo());
            }else{
                queryActualizar.setString(19, "NULL");
            }
            queryActualizar.setString(20, Objeto.getCodigo());
            queryActualizar.execute();
            result=1;
            /*if(result==1){
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
                        "           ,'EQUIPO'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre el equipo, con',?,' actualizando la siguiente informacion a ',?));"); 
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, EquipoAnterior.getCodigo());   
                Query_Auditoria.setString(6, 
                        " Equipo_Código: "+EquipoAnterior.getCodigo()+
                        " Equipo_CódigoBarra: "+EquipoAnterior.getCodigo_barra()+
                        " Equipo_Referencia: "+EquipoAnterior.getReferencia()+
                        " Equipo_Producto: "+EquipoAnterior.getProducto()+
                        " Equipo_Capacidad: "+EquipoAnterior.getCapacidad()+
                        " Equipo_Marca: "+EquipoAnterior.getMarca()+
                        " Equipo_Clasificador1: "+EquipoAnterior.getClasificador1().getDescripcion()+
                        " Equipo_Clasificador2: "+EquipoAnterior.getClasificador2().getDescripcion()+
                        " Equipo_Proveedor_Código: "+EquipoAnterior.getProveedorEquipo().getCodigo()+
                        " Equipo_Proveedor_Nombre: "+EquipoAnterior.getProveedorEquipo().getDescripcion()+
                        " Equipo_Pertenencia: "+EquipoAnterior.getPertenenciaEquipo().getDescripcion()+
                        " Equipo_Observación: "+EquipoAnterior.getObservacion()+
                        " Equipo_Estado: "+EquipoAnterior.getEstado()+
                        " Equipo_ActivoFijo_Código: "+EquipoAnterior.getActivoFijo_codigo()+
                        " Equipo_ActivoFijo_Referencia: "+EquipoAnterior.getActivoFijo_referencia()+
                        " Equipo_ActivoFijo_Descripción: "+EquipoAnterior.getActivoFijo_descripcion());
                Query_Auditoria.setString(7,  
                       " Equipo_Código: "+Objeto.getCodigo()+
                        " Equipo_Tipo: "+Objeto.getTipoEquipo().getDescripcion()+
                        " Equipo_CódigoBarra: "+Objeto.getCodigo_barra()+
                        " Equipo_Referencia: "+Objeto.getReferencia()+
                        " Equipo_Producto: "+Objeto.getProducto()+
                        " Equipo_Capacidad: "+Objeto.getCapacidad()+
                        " Equipo_Marca: "+Objeto.getMarca()+
                        " Equipo_Clasificador1: "+Objeto.getClasificador1().getDescripcion()+
                        " Equipo_Clasificador2: "+Objeto.getClasificador2().getDescripcion()+
                        " Equipo_Proveedor_Código: "+Objeto.getProveedorEquipo().getCodigo()+
                        " Equipo_Proveedor_Nombre: "+Objeto.getProveedorEquipo().getDescripcion()+
                        " Equipo_Pertenencia: "+Objeto.getPertenenciaEquipo().getDescripcion()+
                        " Equipo_Observación: "+Objeto.getObservacion()+
                        " Equipo_Estado: "+valorEstado+
                        " Equipo_ActivoFijo_Código: "+Objeto.getActivoFijo_codigo()+
                        " Equipo_ActivoFijo_Referencia: "+Objeto.getActivoFijo_referencia()+
                        " Equipo_ActivoFijo_Descripción: "+Objeto.getActivoFijo_descripcion());
                Query_Auditoria.execute();
                result=1;
            }*/
        }
        catch (SQLException sqlException ){   
            result=0;
            JOptionPane.showMessageDialog(null, "Error al tratar de actualizar el equipo");
            sqlException.printStackTrace();
        }  
        control.cerrarConexionBaseDatos();
        return result;
    }  
    
    
    //Equipos En ERP
    public ArrayList<Equipo> buscarEquiposEnERP(String sql) throws SQLException{
        ArrayList<Equipo> listadoObjeto = new ArrayList();
        Conexion_DB_ERP control_ERP= new Conexion_DB_ERP(tipoConexion);  
        conexion= control_ERP.ConectarBaseDatos();
        String DB=control_ERP.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT \n" +
                                                        "		 [c0800_id]											AS Equipo_id					-- 1 Id Equipo\n" +
                                                        "		,[c0803_rowid]										AS Equipo_TipoEquipo_cdgo		-- 2 Tipo Equipo codigo\n" +
                                                        "		,[c0803_id]											AS Equipo_TipoEquipo_desc		-- 3 Tipo Equipo descripcion\n" +
                                                        "		,[c0800_cod_barras]									AS Equipo_CodigoBarra			-- 4 Codigo Barra\n" +
                                                        "		,[c0800_referencia]									AS Equipo_Referencia			-- 5 Referencia\n" +
                                                        "		,[c0800_producto]									AS Equipo_Producto				-- 6 Producto\n" +
                                                        "		,[c0800_capacidad]									AS Equipo_Capacidad				-- 7 Capacidad Equipo\n" +
                                                        "		,[c0800_marca]										AS Equipo_marca					-- 8 Marca Equipo\n" +
                                                        "		,[c0800_modelo]										AS Equipo_Modelo				-- 9 Modelo Equipo\n" +
                                                        "		,[c0800_serial]										AS Equipo_Serial				-- 10 Serial Equipo\n" +
                                                        "	    ,[c0800_descripcion]								AS Equipo_descripcion			-- 11 Descripcion Equipo\n" +
                                                        "	    ,Clasificador1.[c0810_rowid]                            AS Equipo_clasificador1_id		-- 12 Clasificador 1\n" +
                                                        "		,Clasificador1.[c0810_id]                           AS Equipo_clasificador1_desc	-- 13 Clasificador 1\n" +
                                                        "        ,Clasificador2.[c0810_rowid]			            AS Equipo_clasificador2_id		-- 14 Clasificador 2\n" +
                                                        "		,Clasificador2.[c0810_id]		            AS Equipo_clasificador2_desc    -- 15 Clasificador 2\n" +
                                                        "		,0                                                  AS Equipo_Valor_Hora			-- 16 Valor_Hora\n" +
                                                        "		,[f010_id]                                          AS Equipo_ProveedorEquipo_cdgo  -- 17 Clasificador 2\n" +
                                                        "		,[f010_nit]                                         AS Equipo_ProveedorEquipo_nit   -- 18 Clasificador 2\n" +
                                                        "		,[f010_razon_social]                                AS Equipo_ProveedorEquipo_desc  -- 19 Clasificador 2\n" +
                                                        "		,[c0800_ind_pertenecia]                             AS Equipo_Pertenecia_cdgo       -- 20 Pertenencia_cdgo\n" +
                                                        "		,[c0800_ind_pertenecia]                             AS Equipo_Pertenecia_desc	    -- 21 Pertenencia_desc\n" +
                                                        "		,''						    AS Equipo_observacion	    -- 22 Equipo_Observacion\n" +
                                                        "	        ,[c0800_ind_estado]                                 AS Equipo_Estado	            -- 23 Equipo_Estado	\n" +
                                                        "	        ,[f262_id] -- CASE WHEN ([f262_id] IS NULL)  THEN 'NULL' ELSE [f262_id]  END  AS Equipo_ActivoFijo_cdgo	    -- 24 Equipo_ActivoFijo_cdgo\n" +
                                                        "	        ,[f262_referencia] -- CASE WHEN ([f262_referencia] IS NULL)  THEN 'NULL' ELSE [f262_referencia]  END AS Equipo_ActivoFijo_referencia  -- 25 Equipo_ActivoFijo_referencia\n" +
                                                        "	        ,[f262_descripcion] -- CASE WHEN ([f262_descripcion] IS NULL)  THEN 'NULL' ELSE [f262_descripcion]  END AS Equipo_ActivoFijo_descripcion -- 26 Equipo_ActivoFijo_descripcion\n" +
                                                        "               ,[f284_rowid] --27 codigo_CentroCosto_equipo(ID)\n" +
                                                        "               ,[f284_id] --28 codigoInterno_CentroCosto_equipo(requerido pero se repite por compañias)\n" +
                                                        "               ,[f284_descripcion] --29 descripción del centroCosto_equipo \n"+
                                                        "               ,[f284_ind_estado] --30 estado del centroCosto_equipo \n"
                                                        + " FROM ["+DB+"].[dbo].[w0800_equipos] \n" +
                                                        "		INNER JOIN ["+DB+"].[dbo].[w0803_tipos_equipos] ON [c0800_rowid_tipo_equipo]=[c0803_rowid]\n" +
                                                        "		LEFT JOIN ["+DB+"].[dbo].[w0810_clases_equipos] Clasificador1 ON [c0800_rowid_clase_equipo1]=Clasificador1.[c0810_rowid]\n" +
                                                        "               LEFT JOIN ["+DB+"].[dbo].[w0810_clases_equipos] Clasificador2 ON [c0800_rowid_clase_equipo2]=Clasificador2.[c0810_rowid]\n" +
                                                        "		INNER JOIN ["+DB+"].[dbo].[t010_mm_companias] ON [f010_id] =[c0800_id_cia] \n"+
                                                        "               LEFT JOIN ["+DB+"].[dbo].[t262_af_activos_fijos] ON [c0800_rowid_activo_fijo]=[f262_rowid] \n"+
                                                        "               LEFT JOIN ["+DB+"].[dbo].[t284_co_ccosto]  ON [f284_rowid]=[c0800_rowid_ccosto] \n "+sql);
            resultSetBuscar= queryBuscar.executeQuery();
          
            while(resultSetBuscar.next()){ 
                Equipo Objeto = new Equipo(); 
                Objeto.setCodigo(resultSetBuscar.getString(1));
                Objeto.setTipoEquipo(new TipoEquipo(resultSetBuscar.getString(2),resultSetBuscar.getString(3),"1"));
                Objeto.setCodigo_barra(resultSetBuscar.getString(4));
                Objeto.setReferencia(resultSetBuscar.getString(5));
                Objeto.setProducto(resultSetBuscar.getString(6));
                Objeto.setCapacidad(resultSetBuscar.getString(7));
                Objeto.setMarca(resultSetBuscar.getString(8));
                Objeto.setModelo(resultSetBuscar.getString(9));
                Objeto.setSerial(resultSetBuscar.getString(10));
                Objeto.setDescripcion(resultSetBuscar.getString(11));
                Objeto.setClasificador1(new ClasificadorEquipo(resultSetBuscar.getString(12),resultSetBuscar.getString(13),"1"));
                Objeto.setClasificador2(new ClasificadorEquipo(resultSetBuscar.getString(14),resultSetBuscar.getString(15),"1"));
                Objeto.setProveedorEquipo(new ProveedorEquipo(resultSetBuscar.getString(17),resultSetBuscar.getString(18),resultSetBuscar.getString(19),"1"));
                Objeto.setPertenenciaEquipo(new Pertenencia(resultSetBuscar.getString(20),resultSetBuscar.getString(21),"1"));
                Objeto.setObservacion(resultSetBuscar.getString(22));
                Objeto.setEstado(resultSetBuscar.getString(23));
                Objeto.setActivoFijo_codigo(resultSetBuscar.getString(24));
                Objeto.setActivoFijo_referencia(resultSetBuscar.getString(25));
                Objeto.setActivoFijo_descripcion(resultSetBuscar.getString(26));
                    CentroCostoEquipo centroCostoEquipo = new CentroCostoEquipo();
                        centroCostoEquipo.setCodigo(resultSetBuscar.getString(27));
                        centroCostoEquipo.setCodigoInterno(resultSetBuscar.getString(28));
                        centroCostoEquipo.setDescripcion(resultSetBuscar.getString(29));
                        centroCostoEquipo.setEstado(resultSetBuscar.getString(30));
                Objeto.setCentroCostoEquipo(centroCostoEquipo);
                listadoObjeto.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los equipos");
            sqlException.printStackTrace();
        } 
        control_ERP.cerrarConexionBaseDatos();
        return listadoObjeto;
    } 
    public Equipo buscarEquiposParticularEnERP(Equipo equipo) throws SQLException{
        Equipo Objeto = null;
        Conexion_DB_ERP control_ERP= new Conexion_DB_ERP(tipoConexion);  
        conexion= control_ERP.ConectarBaseDatos();
        String DB=control_ERP.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT \n" +
                                                        "		 [c0800_id]											AS Equipo_id					-- 1 Id Equipo\n" +
                                                        "		,[c0803_rowid]										AS Equipo_TipoEquipo_cdgo		-- 2 Tipo Equipo codigo\n" +
                                                        "		,[c0803_id]											AS Equipo_TipoEquipo_desc		-- 3 Tipo Equipo descripcion\n" +
                                                        "		,[c0800_cod_barras]									AS Equipo_CodigoBarra			-- 4 Codigo Barra\n" +
                                                        "		,[c0800_referencia]									AS Equipo_Referencia			-- 5 Referencia\n" +
                                                        "		,[c0800_producto]									AS Equipo_Producto				-- 6 Producto\n" +
                                                        "		,[c0800_capacidad]									AS Equipo_Capacidad				-- 7 Capacidad Equipo\n" +
                                                        "		,[c0800_marca]										AS Equipo_marca					-- 8 Marca Equipo\n" +
                                                        "		,[c0800_modelo]										AS Equipo_Modelo				-- 9 Modelo Equipo\n" +
                                                        "		,[c0800_serial]										AS Equipo_Serial				-- 10 Serial Equipo\n" +
                                                        "	    ,[c0800_descripcion]								AS Equipo_descripcion			-- 11 Descripcion Equipo\n" +
                                                        "	    ,Clasificador1.[c0810_rowid]                            AS Equipo_clasificador1_id		-- 12 Clasificador 1\n" +
                                                        "		,Clasificador1.[c0810_id]                           AS Equipo_clasificador1_desc	-- 13 Clasificador 1\n" +
                                                        "        ,Clasificador2.[c0810_rowid]			            AS Equipo_clasificador2_id		-- 14 Clasificador 2\n" +
                                                        "		,Clasificador2.[c0810_id]		            AS Equipo_clasificador2_desc    -- 15 Clasificador 2\n" +
                                                        "		,0                                                  AS Equipo_Valor_Hora			-- 16 Valor_Hora\n" +
                                                        "		,[f010_id]                                          AS Equipo_ProveedorEquipo_cdgo  -- 17 Clasificador 2\n" +
                                                        "		,[f010_nit]                                         AS Equipo_ProveedorEquipo_nit   -- 18 Clasificador 2\n" +
                                                        "		,[f010_razon_social]                                AS Equipo_ProveedorEquipo_desc  -- 19 Clasificador 2\n" +
                                                        "		,[c0800_ind_pertenecia]                             AS Equipo_Pertenecia_cdgo       -- 20 Pertenencia_cdgo\n" +
                                                        "		,[c0800_ind_pertenecia]                             AS Equipo_Pertenecia_desc	    -- 21 Pertenencia_desc\n" +
                                                        "		,''						    AS Equipo_observacion	    -- 22 Equipo_Observacion\n" +
                                                        "	        ,[c0800_ind_estado]                                 AS Equipo_Estado	            -- 23 Equipo_Estado	\n" +
                                                        "	        ,[f262_id] -- CASE WHEN ([f262_id] IS NULL)  THEN 'NULL' ELSE [f262_id]  END  AS Equipo_ActivoFijo_cdgo	    -- 24 Equipo_ActivoFijo_cdgo\n" +
                                                        "	        ,[f262_referencia] -- CASE WHEN ([f262_referencia] IS NULL)  THEN 'NULL' ELSE [f262_referencia]  END AS Equipo_ActivoFijo_referencia  -- 25 Equipo_ActivoFijo_referencia\n" +
                                                        "	        ,[f262_descripcion] -- CASE WHEN ([f262_descripcion] IS NULL)  THEN 'NULL' ELSE [f262_descripcion]  END AS Equipo_ActivoFijo_descripcion -- 26 Equipo_ActivoFijo_descripcion\n" +
                                                        "               ,[f284_rowid] --27 codigo_CentroCosto_equipo(ID)\n" +
                                                        "               ,[f284_id] --28 codigoInterno_CentroCosto_equipo(requerido pero se repite por compañias)\n" +
                                                        "               ,[f284_descripcion] --29 descripción del centroCosto_equipo \n"+
                                                        "               ,[f284_ind_estado] --30 estado del centroCosto_equipo \n"      +                              
                                                        " FROM ["+DB+"].[dbo].[w0800_equipos] \n" +
                                                        "		INNER JOIN ["+DB+"].[dbo].[w0803_tipos_equipos] ON [c0800_rowid_tipo_equipo]=[c0803_rowid]\n" +
                                                        "		LEFT JOIN ["+DB+"].[dbo].[w0810_clases_equipos] Clasificador1 ON [c0800_rowid_clase_equipo1]=Clasificador1.[c0810_rowid]\n" +
                                                        "               LEFT JOIN ["+DB+"].[dbo].[w0810_clases_equipos] Clasificador2 ON [c0800_rowid_clase_equipo2]=Clasificador2.[c0810_rowid]\n" +
                                                        "		INNER JOIN ["+DB+"].[dbo].[t010_mm_companias] ON [f010_id] =[c0800_id_cia] \n"+
                                                        "               LEFT JOIN ["+DB+"].[dbo].[t262_af_activos_fijos] ON [c0800_rowid_activo_fijo]=[f262_rowid] WHERE [c0800_id]=?");
            queryBuscar.setString(1, equipo.getCodigo());
            resultSetBuscar= queryBuscar.executeQuery();
          
            while(resultSetBuscar.next()){ 
                Objeto = new Equipo(); 
                Objeto.setCodigo(resultSetBuscar.getString(1));
                Objeto.setTipoEquipo(new TipoEquipo(resultSetBuscar.getString(2),resultSetBuscar.getString(3),"1"));
                Objeto.setCodigo_barra(resultSetBuscar.getString(4));
                Objeto.setReferencia(resultSetBuscar.getString(5));
                Objeto.setProducto(resultSetBuscar.getString(6));
                Objeto.setCapacidad(resultSetBuscar.getString(7));
                Objeto.setMarca(resultSetBuscar.getString(8));
                Objeto.setModelo(resultSetBuscar.getString(9));
                Objeto.setSerial(resultSetBuscar.getString(10));
                Objeto.setDescripcion(resultSetBuscar.getString(11));
                Objeto.setClasificador1(new ClasificadorEquipo(resultSetBuscar.getString(12),resultSetBuscar.getString(13),"1"));
                Objeto.setClasificador2(new ClasificadorEquipo(resultSetBuscar.getString(14),resultSetBuscar.getString(15),"1"));
                Objeto.setProveedorEquipo(new ProveedorEquipo(resultSetBuscar.getString(17),resultSetBuscar.getString(18),resultSetBuscar.getString(19),"1"));
                Objeto.setPertenenciaEquipo(new Pertenencia(resultSetBuscar.getString(20),resultSetBuscar.getString(21),"1"));
                Objeto.setObservacion(resultSetBuscar.getString(22));
                Objeto.setEstado(resultSetBuscar.getString(23));
                Objeto.setActivoFijo_codigo(resultSetBuscar.getString(24));
                Objeto.setActivoFijo_referencia(resultSetBuscar.getString(25));
                Objeto.setActivoFijo_descripcion(resultSetBuscar.getString(26));
                CentroCostoEquipo centroCostoEquipo = new CentroCostoEquipo();
                        centroCostoEquipo.setCodigo(resultSetBuscar.getString(27));
                        centroCostoEquipo.setCodigoInterno(resultSetBuscar.getString(28));
                        centroCostoEquipo.setDescripcion(resultSetBuscar.getString(29));
                        centroCostoEquipo.setEstado(resultSetBuscar.getString(30));
                Objeto.setCentroCostoEquipo(centroCostoEquipo);
                
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los equipos");
            sqlException.printStackTrace();
        } 
        control_ERP.cerrarConexionBaseDatos();
        return Objeto;
    } 
    public ArrayList<String> buscarMarcasEquiposEnERP() throws SQLException{
        ArrayList<String> listadoObjeto = new ArrayList();
        Conexion_DB_ERP control_ERP= new Conexion_DB_ERP(tipoConexion);  
        conexion= control_ERP.ConectarBaseDatos();
        String DB=control_ERP.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT \n" +
                                                            "		[c0800_marca]	AS Equipo_marca	-- Marca Equipo       \n" +
                                                            "  FROM ["+DB+"].[dbo].[w0800_equipos] \n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[w0803_tipos_equipos] ON [c0800_rowid_tipo_equipo]=[c0803_rowid]\n" +
                                                            "		INNER JOIN ["+DB+"].[dbo].[w0810_clases_equipos] Clasificador1 ON [c0800_rowid_clase_equipo1]=Clasificador1.[c0810_rowid]\n" +
                                                            "        INNER JOIN ["+DB+"].[dbo].[w0810_clases_equipos] Clasificador2 ON [c0800_rowid_clase_equipo2]=Clasificador2.[c0810_rowid]\n" +
                                                            "   GROUP BY [c0800_marca]	");
            resultSetBuscar= queryBuscar.executeQuery();
          
            while(resultSetBuscar.next()){ 
                listadoObjeto.add(resultSetBuscar.getString(1));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las marcas de equipos");
            sqlException.printStackTrace();
        } 
        control_ERP.cerrarConexionBaseDatos();
        return listadoObjeto;
    } 
    public ArrayList<String> buscarTipoEquiposEnERP() throws SQLException{
        ArrayList<String> listadoObjeto = new ArrayList();
        Conexion_DB_ERP control_ERP= new Conexion_DB_ERP(tipoConexion);  
        conexion= control_ERP.ConectarBaseDatos();
        String DB=control_ERP.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT \n" +
                                                                "		[c0803_id]											AS Equipo_TipoEquipo		--Tipo Equipo\n" +
                                                                " FROM ["+DB+"].[dbo].[w0800_equipos] \n" +
                                                                "		INNER JOIN ["+DB+"].[dbo].[w0803_tipos_equipos] ON [c0800_rowid_tipo_equipo]=[c0803_rowid]\n" +
                                                                "		INNER JOIN ["+DB+"].[dbo].[w0810_clases_equipos] Clasificador1 ON [c0800_rowid_clase_equipo1]=Clasificador1.[c0810_rowid]\n" +
                                                                "        INNER JOIN ["+DB+"].[dbo].[w0810_clases_equipos] Clasificador2 ON [c0800_rowid_clase_equipo2]=Clasificador2.[c0810_rowid]\n" +
                                                                " GROUP BY [c0803_id]	");
            resultSetBuscar= queryBuscar.executeQuery();
          
            while(resultSetBuscar.next()){ 
                listadoObjeto.add(resultSetBuscar.getString(1));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los tipos de equipos");
            sqlException.printStackTrace();
        } 
        control_ERP.cerrarConexionBaseDatos();
        return listadoObjeto;
    } 
    
    public ArrayList<Equipo> buscarEquiposEnAplicacionInterna(String sql, String busquedaEspecifica) throws SQLException{
        ArrayList<Equipo> listadoObjeto = new ArrayList();
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);  
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            System.out.println("SELECT  [eq_cdgo]						--1 Equipo_codigo \n" +
                                "                        ,[eq_tipo_equipo_cdgo]					--2 Tipo_Equipo_Codigo \n" +
                                "                            ,[te_cdgo]							--3 Tipo_Equipo_Codigo \n" +
                                "                            ,[te_desc]							--4 Tipo_Equipo_Descripcion \n" +
                                "                            ,[te_estad]							--5 Tipo_Equipo_Estado \n" +
                                "                        ,[eq_codigo_barra]						--6 Equipo_CodigoBarra \n" +
                                "                        ,[eq_referencia]							--7 Equipo_Referencia \n" +
                                "                        ,[eq_producto]							--8 Equipo_Producto \n" +
                                "                        ,[eq_capacidad]							--9 Equipo_Capacidad \n" +
                                "                        ,[eq_marca]								--10 Equipo_Marca \n" +
                                "                        ,[eq_modelo]								--11 Equipo_Modelo \n" +
                                "                        ,[eq_serial]								--12 Equipo_Serial \n" +
                                "                        ,[eq_desc]								--13 Equipo_Descripcion \n" +
                                "                        ,[eq_clasificador1_cdgo]					--14 Equipo_Clasificador1_Código \n" +
                                "                            ,clasificador1.[ce_cdgo]				--15 Equipo_Clasificador1_Código \n" +
                                "                            ,clasificador1.[ce_desc]				--16 Equipo_Clasificador1_Descripción \n" +
                                "                            ,clasificador1.[ce_estad]				--17 Equipo_Clasificador1_Estado \n" +
                                "                        ,[eq_clasificador2_cdgo]					--18 Equipo_Clasificador2_Código \n" +
                                "                            ,clasificador2.[ce_cdgo]				--19 Equipo_Clasificador2_Código \n" +
                                "                            ,clasificador2.[ce_desc]				--20 Equipo_Clasificador2_Descripción \n" +
                                "                            ,clasificador2.[ce_estad]				--21 Equipo_Clasificador2_Estado \n" +
                                "                        ,[eq_proveedor_equipo_cdgo]							--22 Equipo_valorHora \n" +
                                "                        ,[eq_proveedor_equipo_cdgo]				--23 Equipo_ProveedorEquipo_Código \n" +
                                "                            ,[pe_cdgo]							--24 Equipo_ProveedorEquipo_Código \n" +
                                "                            ,[pe_nit]								--25 Equipo_ProveedorEquipo_NIT \n" +
                                "                            ,[pe_desc]							--26 Equipo_ProveedorEquipo_Descripción \n" +
                                "                            ,[pe_estad]							--27 Equipo_ProveedorEquipo_Estado \n" +
                                "                        ,[eq_equipo_pertenencia_cdgo]				--28 Equipo_Pertenencia_Código \n" +
                                "                            ,[ep_cdgo]							--29 Equipo_Pertenencia_Código \n" +
                                "                            ,[ep_desc]							--30 Equipo_Pertenencia_Descripción \n" +
                                "                            ,[ep_estad]							--31 Equipo_Pertenencia_Estado \n" +
                                "                        ,[eq_observ]								--32 Equipo_Observación \n" +
                                "                        ,[eq_estad]								--33 Equipo_Estado \n" +
                                "                        ,[eq_actvo_fijo_id]								--34 Equipo_ActivoFijo_cdgo \n" +
                                "                        ,[eq_actvo_fijo_referencia]								--35 Equipo_ActivoFijo_referencia \n" +
                                "                        ,[eq_actvo_fijo_desc]								--36 Equipo_ActivoFijo_descripcion \n" +
                                "						,[tarifa_equipo].[tae_ano]                   --37 tarifa año\n" +
                                "						,[tarifa_equipo].[tae_valorhoraOperacion]		--38 tarifa valorOperacion\n" +
                                "						,[tarifa_equipo].[tae_valorhoraAlquiler]		--39 tarifa valorAlquiler\n" +
                                "						,[tarifa_equipo].[tae_fecha_hora_inicio] --40 \n" +
                                "						,[tarifa_equipo].[tae_fecha_hora_fin] --41 \n" +
                                "						,[tarifa_equipo].[tae_costoLavadoVehiculo] --42 \n"+
                                "                         ,[eq_cntro_cost_equipo_cdgo]--43\n" +
                                "                               ,[cce_cdgo]--44\n" +
                                "                               ,[cce_cdgo_intern]--45\n" +
                                "                               ,[cce_desc]--46\n" +
                                "                               ,[cce_estad] --47\n" +
                                "                               ,[tarifa_equipo].[tae_valorRecaudoEmpresa] --48\n" +
                                "                               ,[tarifa_equipo].[tae_valorRecaudoEquipo] --49\n" +
                                "                    FROM ["+DB+"].[dbo].[equipo] \n" +
                                "                        LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo] \n" +
                                "                        LEFT JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador1 ON clasificador1.ce_cdgo =[eq_clasificador1_cdgo] \n" +
                                "                        LEFT JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador2 ON clasificador2.ce_cdgo =[eq_clasificador2_cdgo] \n" +
                                "                        LEFT JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [pe_cdgo] =[eq_proveedor_equipo_cdgo] \n" +
                                "                        LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON [eq_equipo_pertenencia_cdgo]=[ep_cdgo] \n" +
                                "                        LEFT JOIN ["+DB+"].[dbo].[cntro_cost_equipo] ON [eq_cntro_cost_equipo_cdgo]=[cce_cdgo] \n" +
                                "						LEFT JOIN (  SELECT  [tarifa_equipo].[tae_cdgo]\n" +
                                "								  ,[tarifa_equipo].[tae_ano]\n" +
                                "								  ,[tarifa_equipo].[tae_equipo_cdgo]\n" +
                                "								  ,[tarifa_equipo].[tae_valorhoraOperacion]\n" +
                                "								  ,[tarifa_equipo].[tae_valorhoraAlquiler]\n" +
                                "								  ,[tarifa_equipo].[tae_fecha_hora_inicio]\n" +
                                "								  ,[tarifa_equipo].[tae_fecha_hora_fin]\n" +
                                "								  ,[tarifa_equipo].[tae_costoLavadoVehiculo]\n" +
                                "								  ,[tarifa_equipo].[tae_valorRecaudoEmpresa]\n" +
                                "								  ,[tarifa_equipo].[tae_valorRecaudoEquipo]\n" +
                                "							  FROM ["+DB+"].[dbo].[tarifa_equipo]\n" +
                                "							  INNER JOIN (SELECT MAX( [tae_cdgo]) AS [tae_cdgo]\n" +
                                "								  ,[tae_equipo_cdgo] \n" +
                                "							  FROM ["+DB+"].[dbo].[tarifa_equipo] GROUP BY [tae_equipo_cdgo]) AS t ON t.[tae_cdgo]=[tarifa_equipo].[tae_cdgo])AS\n" +
                                "							  [tarifa_equipo] ON [tarifa_equipo].[tae_equipo_cdgo]=[equipo].[eq_cdgo] "+sql);
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT  [eq_cdgo]						--1 Equipo_codigo \n" +
                                "                        ,[eq_tipo_equipo_cdgo]					--2 Tipo_Equipo_Codigo \n" +
                                "                            ,[te_cdgo]							--3 Tipo_Equipo_Codigo \n" +
                                "                            ,[te_desc]							--4 Tipo_Equipo_Descripcion \n" +
                                "                            ,[te_estad]							--5 Tipo_Equipo_Estado \n" +
                                "                        ,[eq_codigo_barra]						--6 Equipo_CodigoBarra \n" +
                                "                        ,[eq_referencia]							--7 Equipo_Referencia \n" +
                                "                        ,[eq_producto]							--8 Equipo_Producto \n" +
                                "                        ,[eq_capacidad]							--9 Equipo_Capacidad \n" +
                                "                        ,[eq_marca]								--10 Equipo_Marca \n" +
                                "                        ,[eq_modelo]								--11 Equipo_Modelo \n" +
                                "                        ,[eq_serial]								--12 Equipo_Serial \n" +
                                "                        ,[eq_desc]								--13 Equipo_Descripcion \n" +
                                "                        ,[eq_clasificador1_cdgo]					--14 Equipo_Clasificador1_Código \n" +
                                "                            ,clasificador1.[ce_cdgo]				--15 Equipo_Clasificador1_Código \n" +
                                "                            ,clasificador1.[ce_desc]				--16 Equipo_Clasificador1_Descripción \n" +
                                "                            ,clasificador1.[ce_estad]				--17 Equipo_Clasificador1_Estado \n" +
                                "                        ,[eq_clasificador2_cdgo]					--18 Equipo_Clasificador2_Código \n" +
                                "                            ,clasificador2.[ce_cdgo]				--19 Equipo_Clasificador2_Código \n" +
                                "                            ,clasificador2.[ce_desc]				--20 Equipo_Clasificador2_Descripción \n" +
                                "                            ,clasificador2.[ce_estad]				--21 Equipo_Clasificador2_Estado \n" +
                                "                        ,[eq_proveedor_equipo_cdgo]							--22 Equipo_valorHora \n" +
                                "                        ,[eq_proveedor_equipo_cdgo]				--23 Equipo_ProveedorEquipo_Código \n" +
                                "                            ,[pe_cdgo]							--24 Equipo_ProveedorEquipo_Código \n" +
                                "                            ,[pe_nit]								--25 Equipo_ProveedorEquipo_NIT \n" +
                                "                            ,[pe_desc]							--26 Equipo_ProveedorEquipo_Descripción \n" +
                                "                            ,[pe_estad]							--27 Equipo_ProveedorEquipo_Estado \n" +
                                "                        ,[eq_equipo_pertenencia_cdgo]				--28 Equipo_Pertenencia_Código \n" +
                                "                            ,[ep_cdgo]							--29 Equipo_Pertenencia_Código \n" +
                                "                            ,[ep_desc]							--30 Equipo_Pertenencia_Descripción \n" +
                                "                            ,[ep_estad]							--31 Equipo_Pertenencia_Estado \n" +
                                "                        ,[eq_observ]								--32 Equipo_Observación \n" +
                                "                        ,[eq_estad]								--33 Equipo_Estado \n" +
                                "                        ,[eq_actvo_fijo_id]								--34 Equipo_ActivoFijo_cdgo \n" +
                                "                        ,[eq_actvo_fijo_referencia]								--35 Equipo_ActivoFijo_referencia \n" +
                                "                        ,[eq_actvo_fijo_desc]								--36 Equipo_ActivoFijo_descripcion \n" +
                                "						,[tarifa_equipo].[tae_ano]                   --37 tarifa año\n" +
                                "						,[tarifa_equipo].[tae_valorhoraOperacion]		--38 tarifa valorOperacion\n" +
                                "						,[tarifa_equipo].[tae_valorhoraAlquiler]		--39 tarifa valorAlquiler\n" +
                                "						,[tarifa_equipo].[tae_fecha_hora_inicio] --40 \n" +
                                "						,[tarifa_equipo].[tae_fecha_hora_fin] --41 \n" +
                                "						,[tarifa_equipo].[tae_costoLavadoVehiculo] --42 \n"+
                                "                         ,[eq_cntro_cost_equipo_cdgo]--43\n" +
                                "                               ,[cce_cdgo]--44\n" +
                                "                               ,[cce_cdgo_intern]--45\n" +
                                "                               ,[cce_desc]--46\n" +
                                "                               ,[cce_estad] --47\n" +
                                "                               ,[tarifa_equipo].[tae_valorRecaudoEmpresa] --48\n" +
                                "                               ,[tarifa_equipo].[tae_valorRecaudoEquipo] --49\n" +
                                "                    FROM ["+DB+"].[dbo].[equipo] \n" +
                                "                        LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo] \n" +
                                "                        LEFT JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador1 ON clasificador1.ce_cdgo =[eq_clasificador1_cdgo] \n" +
                                "                        LEFT JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador2 ON clasificador2.ce_cdgo =[eq_clasificador2_cdgo] \n" +
                                "                        LEFT JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [pe_cdgo] =[eq_proveedor_equipo_cdgo] \n" +
                                "                        LEFT JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON [eq_equipo_pertenencia_cdgo]=[ep_cdgo] \n" +
                                "                        LEFT JOIN ["+DB+"].[dbo].[cntro_cost_equipo] ON [eq_cntro_cost_equipo_cdgo]=[cce_cdgo] \n" +
                                "						LEFT JOIN (  SELECT  [tarifa_equipo].[tae_cdgo]\n" +
                                "								  ,[tarifa_equipo].[tae_ano]\n" +
                                "								  ,[tarifa_equipo].[tae_equipo_cdgo]\n" +
                                "								  ,[tarifa_equipo].[tae_valorhoraOperacion]\n" +
                                "								  ,[tarifa_equipo].[tae_valorhoraAlquiler]\n" +
                                "								  ,[tarifa_equipo].[tae_fecha_hora_inicio]\n" +
                                "								  ,[tarifa_equipo].[tae_fecha_hora_fin]\n" +
                                "								  ,[tarifa_equipo].[tae_costoLavadoVehiculo]\n" +
                                "								  ,[tarifa_equipo].[tae_valorRecaudoEmpresa]\n" +
                                "								  ,[tarifa_equipo].[tae_valorRecaudoEquipo]\n" +
                                "							  FROM ["+DB+"].[dbo].[tarifa_equipo]\n" +
                                "							  INNER JOIN (SELECT MAX( [tae_cdgo]) AS [tae_cdgo]\n" +
                                "								  ,[tae_equipo_cdgo] \n" +
                                "							  FROM ["+DB+"].[dbo].[tarifa_equipo] GROUP BY [tae_equipo_cdgo]) AS t ON t.[tae_cdgo]=[tarifa_equipo].[tae_cdgo])AS\n" +
                                "							  [tarifa_equipo] ON [tarifa_equipo].[tae_equipo_cdgo]=[equipo].[eq_cdgo] "+sql);
            if(!busquedaEspecifica.equals("")){
                 queryBuscar.setString(1, busquedaEspecifica);
            }
            resultSetBuscar= queryBuscar.executeQuery();
          
            while(resultSetBuscar.next()){ 
                Equipo Objeto = new Equipo(); 
                Objeto.setCodigo(resultSetBuscar.getString(1));
                Objeto.setTipoEquipo(new TipoEquipo(resultSetBuscar.getString(3),resultSetBuscar.getString(4),resultSetBuscar.getString(5)));
                Objeto.setCodigo_barra(resultSetBuscar.getString(6));
                Objeto.setReferencia(resultSetBuscar.getString(7));
                Objeto.setProducto(resultSetBuscar.getString(8));
                Objeto.setCapacidad(resultSetBuscar.getString(9));
                Objeto.setMarca(resultSetBuscar.getString(10));
                Objeto.setModelo(resultSetBuscar.getString(11));
                Objeto.setSerial(resultSetBuscar.getString(12));
                Objeto.setDescripcion(resultSetBuscar.getString(13));
                Objeto.setClasificador1(new ClasificadorEquipo(resultSetBuscar.getString(15),resultSetBuscar.getString(16),resultSetBuscar.getString(17)));
                Objeto.setClasificador2(new ClasificadorEquipo(resultSetBuscar.getString(19),resultSetBuscar.getString(20),resultSetBuscar.getString(21)));
                Objeto.setProveedorEquipo(new ProveedorEquipo(resultSetBuscar.getString(24),resultSetBuscar.getString(25),resultSetBuscar.getString(26),resultSetBuscar.getString(27)));
                Objeto.setPertenenciaEquipo(new Pertenencia(resultSetBuscar.getString(29),resultSetBuscar.getString(30),resultSetBuscar.getString(31)));
                Objeto.setObservacion(resultSetBuscar.getString(32));
                Objeto.setEstado(resultSetBuscar.getString(33));
                Objeto.setActivoFijo_codigo(resultSetBuscar.getString(34));
                Objeto.setActivoFijo_referencia(resultSetBuscar.getString(35));
                Objeto.setActivoFijo_descripcion(resultSetBuscar.getString(36));
                
                CentroCostoEquipo centroCostoEquipo = new CentroCostoEquipo();
                        centroCostoEquipo.setCodigo(resultSetBuscar.getString(44));
                        centroCostoEquipo.setCodigoInterno(resultSetBuscar.getString(45));
                        centroCostoEquipo.setDescripcion(resultSetBuscar.getString(46));
                        centroCostoEquipo.setEstado(resultSetBuscar.getString(47));
                Objeto.setCentroCostoEquipo(centroCostoEquipo);
                
                TarifaEquipo tarifaEquipoAnterior = new TarifaEquipo();
                tarifaEquipoAnterior.setCodigo("");
                tarifaEquipoAnterior.setAño(resultSetBuscar.getString(37));
                tarifaEquipoAnterior.setCodigoEquipo(resultSetBuscar.getString(1));
                tarifaEquipoAnterior.setValorHoraOperacion(resultSetBuscar.getString(38));
                tarifaEquipoAnterior.setValorHoraAlquiler(resultSetBuscar.getString(39));
                tarifaEquipoAnterior.setFechaHoraInicio(resultSetBuscar.getString(40));
                tarifaEquipoAnterior.setFechaHoraFin(resultSetBuscar.getString(41));
                tarifaEquipoAnterior.setCostoLavadoVehiculo(resultSetBuscar.getString(42));
                tarifaEquipoAnterior.setValorRecaudoEmpresa(resultSetBuscar.getString(48));
                tarifaEquipoAnterior.setValorRecaudoEquipo(resultSetBuscar.getString(49));
                Objeto.setTarifaEquipoAnterior(tarifaEquipoAnterior);
                
                TarifaEquipo tarifaEquipoNuevo = new TarifaEquipo();
                tarifaEquipoNuevo.setCodigo("");
                tarifaEquipoNuevo.setAño("");
                tarifaEquipoNuevo.setCodigoEquipo(resultSetBuscar.getString(1));
                tarifaEquipoNuevo.setValorHoraAlquiler("");
                tarifaEquipoNuevo.setValorHoraOperacion("");
                tarifaEquipoNuevo.setFechaHoraInicio("");
                tarifaEquipoNuevo.setFechaHoraFin("");
                Objeto.setTarifaEquipoNueva(tarifaEquipoNuevo);
                
                listadoObjeto.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjeto;
    } 
    public TarifaEquipo buscarTarifaEquipoPorAño(String codigoEquipo,String fecha) throws SQLException{
        TarifaEquipo Objeto= null;
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);  
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            /*PreparedStatement queryBuscar= conexion.prepareStatement("SELECT \n" +
            "  [tae_cdgo]\n" +
            "      ,[tae_ano]\n" +
            "      ,[tae_equipo_cdgo]\n" +
            "      ,[tae_valorhoraOperacion]\n" +
            "      ,[tae_valorhoraAlquiler]\n" +
            "	  FROM ["+DB+"].[dbo].[tarifa_equipo]\n" +
            "		INNER JOIN (SELECT  \n" +
            "						MAX([tae_cdgo]) AS ta_cdgo\n" +
            "					FROM ["+DB+"].[dbo].[tarifa_equipo] \n" +
            "					WHERE [tae_equipo_cdgo]="+codigoEquipo+" AND [tae_ano]=YEAR('"+fecha+"')) AS tarifa\n" +
            "					ON tarifa.ta_cdgo =[tae_cdgo];");*/
            
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT \n" +
                "    [tae_cdgo]\n" +
                "        ,[tae_ano]\n" +
                "        ,[tae_equipo_cdgo]\n" +
                "        ,[tae_valorhoraOperacion]\n" +
                "        ,[tae_valorhoraAlquiler]\n" +
                "		,[tae_fecha_hora_inicio]\n" +
                "		,[tae_fecha_hora_fin],[tae_costoLavadoVehiculo],[tae_valorRecaudoEmpresa],[tae_valorRecaudoEquipo]\n" +
                "        FROM ["+DB+"].[dbo].[tarifa_equipo]\n" +
                "        INNER JOIN (SELECT  \n" +
                "            			MAX([tae_cdgo]) AS ta_cdgo\n" +
                "            		FROM ["+DB+"].[dbo].[tarifa_equipo] \n" +
                "            		WHERE [tae_equipo_cdgo]="+codigoEquipo+" AND '"+fecha+"' BETWEEN [tae_fecha_hora_inicio] AND [tae_fecha_hora_fin])  AS tarifa\n" +
                "            		ON tarifa.ta_cdgo =[tae_cdgo];");
            
                 //queryBuscar.setString(1, codigoEquipo);
                 //queryBuscar.setString(2, fecha);
            resultSetBuscar= queryBuscar.executeQuery();
          
            while(resultSetBuscar.next()){ 
                Objeto = new TarifaEquipo(); 
                Objeto.setCodigo(resultSetBuscar.getString(1));
                Objeto.setAño(resultSetBuscar.getString(2));
                Objeto.setCodigoEquipo(resultSetBuscar.getString(3));
                Objeto.setValorHoraOperacion(resultSetBuscar.getString(4));
                Objeto.setValorHoraAlquiler(resultSetBuscar.getString(5));
                Objeto.setCostoLavadoVehiculo(resultSetBuscar.getString(8));
                Objeto.setValorRecaudoEmpresa(resultSetBuscar.getString(9));
                Objeto.setValorRecaudoEquipo(resultSetBuscar.getString(10));
                //System.out.println(" "+resultSetBuscar.getString(1)+" "+resultSetBuscar.getString(2)+" ");
             }
        }catch (SQLException sqlException) {
            //JOptionPane.showMessageDialog(null, "Error al tratar la tarifa para el equipo");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return Objeto;
    }
    
    
    /*public ArrayList<Equipo> buscarEquiposEnAplicacionInterna(String sql, String busquedaEspecifica) throws SQLException{
        ArrayList<Equipo> listadoObjeto = new ArrayList();
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg();  
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT  [eq_cdgo]						--1 Equipo_codigo\n" +
                                                                    "      ,[eq_tipo_equipo_cdgo]					--2 Tipo_Equipo_Codigo\n" +
                                                                    "		  ,[te_cdgo]							--3 Tipo_Equipo_Codigo\n" +
                                                                    "		  ,[te_desc]							--4 Tipo_Equipo_Descripcion\n" +
                                                                    "		  ,[te_estad]							--5 Tipo_Equipo_Estado\n" +
                                                                    "      ,[eq_codigo_barra]						--6 Equipo_CodigoBarra\n" +
                                                                    "      ,[eq_referencia]							--7 Equipo_Referencia\n" +
                                                                    "      ,[eq_producto]							--8 Equipo_Producto\n" +
                                                                    "      ,[eq_capacidad]							--9 Equipo_Capacidad\n" +
                                                                    "      ,[eq_marca]								--10 Equipo_Marca\n" +
                                                                    "      ,[eq_modelo]								--11 Equipo_Modelo\n" +
                                                                    "      ,[eq_serial]								--12 Equipo_Serial\n" +
                                                                    "      ,[eq_desc]								--13 Equipo_Descripcion\n" +
                                                                    "      ,[eq_clasificador1_cdgo]					--14 Equipo_Clasificador1_Código\n" +
                                                                    "		  ,clasificador1.[ce_cdgo]				--15 Equipo_Clasificador1_Código\n" +
                                                                    "		  ,clasificador1.[ce_desc]				--16 Equipo_Clasificador1_Descripción\n" +
                                                                    "		  ,clasificador1.[ce_estad]				--17 Equipo_Clasificador1_Estado\n" +
                                                                    "      ,[eq_clasificador2_cdgo]					--18 Equipo_Clasificador2_Código\n" +
                                                                    "		  ,clasificador2.[ce_cdgo]				--19 Equipo_Clasificador2_Código\n" +
                                                                    "		  ,clasificador2.[ce_desc]				--20 Equipo_Clasificador2_Descripción\n" +
                                                                    "		  ,clasificador2.[ce_estad]				--21 Equipo_Clasificador2_Estado\n" +
                                                                    "      ,[eq_proveedor_equipo_cdgo]							--22 Equipo_valorHora\n" +
                                                                    "      ,[eq_proveedor_equipo_cdgo]				--23 Equipo_ProveedorEquipo_Código\n" +
                                                                    "		  ,[pe_cdgo]							--24 Equipo_ProveedorEquipo_Código\n" +
                                                                    "		  ,[pe_nit]								--25 Equipo_ProveedorEquipo_NIT\n" +
                                                                    "		  ,[pe_desc]							--26 Equipo_ProveedorEquipo_Descripción\n" +
                                                                    "		  ,[pe_estad]							--27 Equipo_ProveedorEquipo_Estado\n" +
                                                                    "      ,[eq_equipo_pertenencia_cdgo]				--28 Equipo_Pertenencia_Código\n" +
                                                                    "		  ,[ep_cdgo]							--29 Equipo_Pertenencia_Código\n" +
                                                                    "		  ,[ep_desc]							--30 Equipo_Pertenencia_Descripción\n" +
                                                                    "		  ,[ep_estad]							--31 Equipo_Pertenencia_Estado\n" +
                                                                    "      ,[eq_observ]								--32 Equipo_Observación\n" +
                                                                    "      ,[eq_estad]								--33 Equipo_Estado\n" +
                                                                    "      ,[eq_actvo_fijo_id]								--34 Equipo_ActivoFijo_cdgo\n" +
                                                                    "      ,[eq_actvo_fijo_referencia]								--35 Equipo_ActivoFijo_referencia\n" +
                                                                    "      ,[eq_actvo_fijo_desc]								--36 Equipo_ActivoFijo_descripcion\n" +
                                                                    "  FROM ["+DB+"].[dbo].[equipo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador1 ON clasificador1.ce_cdgo =[eq_clasificador1_cdgo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador2 ON clasificador2.ce_cdgo =[eq_clasificador2_cdgo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [pe_cdgo] =[eq_proveedor_equipo_cdgo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON [eq_equipo_pertenencia_cdgo]=[ep_cdgo] "+sql);
            if(!busquedaEspecifica.equals("")){
                 queryBuscar.setString(1, busquedaEspecifica);
            }
            resultSetBuscar= queryBuscar.executeQuery();
          
            while(resultSetBuscar.next()){ 
                Equipo Objeto = new Equipo(); 
                Objeto.setCodigo(resultSetBuscar.getString(1));
                Objeto.setTipoEquipo(new TipoEquipo(resultSetBuscar.getString(3),resultSetBuscar.getString(4),resultSetBuscar.getString(5)));
                Objeto.setCodigo_barra(resultSetBuscar.getString(6));
                Objeto.setReferencia(resultSetBuscar.getString(7));
                Objeto.setProducto(resultSetBuscar.getString(8));
                Objeto.setCapacidad(resultSetBuscar.getString(9));
                Objeto.setMarca(resultSetBuscar.getString(10));
                Objeto.setModelo(resultSetBuscar.getString(11));
                Objeto.setSerial(resultSetBuscar.getString(12));
                Objeto.setDescripcion(resultSetBuscar.getString(13));
                Objeto.setClasificador1(new ClasificadorEquipo(resultSetBuscar.getString(15),resultSetBuscar.getString(16),resultSetBuscar.getString(17)));
                Objeto.setClasificador2(new ClasificadorEquipo(resultSetBuscar.getString(19),resultSetBuscar.getString(20),resultSetBuscar.getString(21)));
                Objeto.setProveedorEquipo(new ProveedorEquipo(resultSetBuscar.getString(24),resultSetBuscar.getString(25),resultSetBuscar.getString(26),resultSetBuscar.getString(27)));
                Objeto.setPertenenciaEquipo(new Pertenencia(resultSetBuscar.getString(29),resultSetBuscar.getString(30),resultSetBuscar.getString(31)));
                Objeto.setObservacion(resultSetBuscar.getString(32));
                Objeto.setEstado(resultSetBuscar.getString(33));
                Objeto.setActivoFijo_codigo(resultSetBuscar.getString(34));
                Objeto.setActivoFijo_referencia(resultSetBuscar.getString(35));
                Objeto.setActivoFijo_descripcion(resultSetBuscar.getString(36));
                TarifaEquipo tarifaEquipo = new TarifaEquipo();
                tarifaEquipo.setCodigo("");
                tarifaEquipo.setAño("");
                tarifaEquipo.setCodigoEquipo(resultSetBuscar.getString(1));
                tarifaEquipo.setValorHoraAlquiler("");
                tarifaEquipo.setValorHoraOperacion("");
                Objeto.setTarifaEquipo(tarifaEquipo);
                listadoObjeto.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjeto;
    } */
    
    public ArrayList<String> buscarTipoEquiposEnAplicacionInterna() throws SQLException{
        ArrayList<String> listadoObjeto = new ArrayList();
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);  
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT [te_desc]\n" +
                                                                " FROM ["+DB+"].[dbo].[equipo]\n" +
                                                                "	INNER JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo]\n" +
                                                                "	WHERE [eq_estad]=1 \n" +
                                                                "	GROUP BY [te_desc]");
            resultSetBuscar= queryBuscar.executeQuery();
          
            while(resultSetBuscar.next()){ 
                listadoObjeto.add(resultSetBuscar.getString(1));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los tipos de equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjeto;
    }
    public ArrayList<String> buscarMarcasEquiposEnAplicacionInterna() throws SQLException{
        ArrayList<String> listadoObjeto = new ArrayList();
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);  
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT [eq_marca] FROM ["+DB+"].[dbo].[equipo] GROUP BY [eq_marca]");
            resultSetBuscar= queryBuscar.executeQuery();
          
            while(resultSetBuscar.next()){ 
                listadoObjeto.add(resultSetBuscar.getString(1));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las marcas de equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjeto;
    }
    public ArrayList<String> buscarModelosEquiposEnAplicacionInterna() throws SQLException{
        ArrayList<String> listadoObjeto = new ArrayList();
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);  
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT [eq_modelo] FROM ["+DB+"].[dbo].[equipo] GROUP BY [eq_modelo]");
            resultSetBuscar= queryBuscar.executeQuery();
          
            while(resultSetBuscar.next()){ 
                listadoObjeto.add(resultSetBuscar.getString(1));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los Modelos de equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjeto;
    }
    
    
    
    //Registro Paleros
    public int CargarConsecutivoSiguiente() throws SQLException{
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
       int consecutivo=0;
        try{
            PreparedStatement queryBuscarNombre= conexion.prepareStatement("SELECT (CASE WHEN (MAX([eq_cdgo]) IS NULL) THEN 1 ELSE (MAX([eq_cdgo])+1) END)AS [eq_cdgo]\n" +
                                                                                " FROM ["+DB+"].[dbo].[equipo];");
            ResultSet resultSetBuscarNombre= queryBuscarNombre.executeQuery();
            while(resultSetBuscarNombre.next()){
                consecutivo = resultSetBuscarNombre.getInt(1);
                if(consecutivo < 100000){
                    consecutivo=100000;
                }
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al consultar el consecutivo para paleros");
        } 
        control.cerrarConexionBaseDatos();
        return consecutivo;
    } 
    public int registrarPalero(Equipo Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            if(validarExistenciaPalero(Objeto)){
                JOptionPane.showMessageDialog(null, "La cuadrilla ya se encuentra registrada en el sistema");
            }else{
                String estado;
                if(Objeto.getEstado().equalsIgnoreCase("1")){
                    estado="ACTIVO";
                }else{
                    estado="INACTIVO";
                }
                conexion= control.ConectarBaseDatos();
                Objeto.setCodigo(""+CargarConsecutivoSiguiente());
                conexion= control.ConectarBaseDatos();
                String DB=control.getBaseDeDatos();
                PreparedStatement queryRegistrar= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[equipo]\n" +
                                                                                "           ([eq_cdgo]\n" +
                                                                                "           ,[eq_tipo_equipo_cdgo]\n" +
                                                                                "           ,[eq_codigo_barra]\n" +
                                                                                "           ,[eq_referencia]\n" +
                                                                                "           ,[eq_producto]\n" +
                                                                                "           ,[eq_capacidad]\n" +
                                                                                "           ,[eq_marca]\n" +
                                                                                "           ,[eq_modelo]\n" +
                                                                                "           ,[eq_serial]\n" +
                                                                                "           ,[eq_desc]\n" +
                                                                                "           ,[eq_clasificador1_cdgo]\n" +
                                                                                "           ,[eq_clasificador2_cdgo]\n" +
                                                                                "           ,[eq_proveedor_equipo_cdgo]\n" +
                                                                                "           ,[eq_equipo_pertenencia_cdgo]\n" +
                                                                                "           ,[eq_observ]\n" +
                                                                                "           ,[eq_estad]\n" +
                                                                                "           ,[eq_actvo_fijo_id]\n" +
                                                                                "           ,[eq_actvo_fijo_referencia]\n" +
                                                                                "           ,[eq_actvo_fijo_desc])\n" +
                                                                                "     VALUES\n" +
                                                                                "           (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

                queryRegistrar.setString(1, Objeto.getCodigo());
                queryRegistrar.setString(2, Objeto.getTipoEquipo().getCodigo());
                queryRegistrar.setString(3, Objeto.getCodigo_barra());
                queryRegistrar.setString(4, Objeto.getReferencia());
                queryRegistrar.setString(5, Objeto.getProducto());
                queryRegistrar.setString(6, Objeto.getCapacidad());
                queryRegistrar.setString(7, Objeto.getMarca());
                queryRegistrar.setString(8, Objeto.getModelo());
                queryRegistrar.setString(9, Objeto.getSerial());
                queryRegistrar.setString(10, Objeto.getDescripcion());
                queryRegistrar.setString(11, Objeto.getClasificador1().getCodigo());
                queryRegistrar.setString(12, Objeto.getClasificador2().getCodigo());
                queryRegistrar.setString(13, Objeto.getProveedorEquipo().getCodigo());
                queryRegistrar.setString(14, Objeto.getPertenenciaEquipo().getCodigo());
                queryRegistrar.setString(15, Objeto.getObservacion());
                queryRegistrar.setString(16, Objeto.getEstado());
                queryRegistrar.setString(17, Objeto.getActivoFijo_codigo());
                queryRegistrar.setString(18, Objeto.getActivoFijo_referencia());
                queryRegistrar.setString(19, Objeto.getActivoFijo_descripcion());
                queryRegistrar.execute();
                result=1;
                if(result==1){
                    result=0;
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
                        "           ,'EQUIPO'" +
                        "           ,CONCAT (?,?,?));");
                    Query_AuditoriaInsert.setString(1, us.getCodigo());
                    Query_AuditoriaInsert.setString(2, namePc);
                    Query_AuditoriaInsert.setString(3, ipPc);
                    Query_AuditoriaInsert.setString(4, macPC);
                    Query_AuditoriaInsert.setString(5, Objeto.getCodigo()); 
                    Query_AuditoriaInsert.setString(6, "Se registró una nueva cuadrilla en el sistema, con Código: ");
                    Query_AuditoriaInsert.setString(7, Objeto.getCodigo());
                    Query_AuditoriaInsert.setString(8,  " Producto: "+Objeto.getProducto()+
                                                        " Marca: "+Objeto.getMarca()+
                                                        " Modelo: "+Objeto.getModelo()+
                                                        " Descripcion: "+Objeto.getDescripcion()+
                                                        " ProveedorEquipo: "+Objeto.getProveedorEquipo().getDescripcion()+
                                                        " PertenenciaEquipo: "+Objeto.getPertenenciaEquipo().getDescripcion()+
                                                        " Observación: "+Objeto.getObservacion()+
                                                        " Estado: "+estado + " ActivoFijo_Codigo: "+Objeto.getActivoFijo_codigo()+
                                                        " ActivoFijo_referencia: "+Objeto.getActivoFijo_referencia()+
                                                        " ActivoFijo_descripcion: "+Objeto.getActivoFijo_descripcion());
                    Query_AuditoriaInsert.execute();
                    result=1; 
                }
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
    public boolean validarExistenciaPalero(Equipo Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{ 
            PreparedStatement queryValidarExistencia= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[equipo] WHERE [eq_desc] =? AND [eq_modelo]=?;");
            queryValidarExistencia.setString(1, Objeto.getDescripcion());
            queryValidarExistencia.setString(2, Objeto.getModelo());
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
    public ArrayList<Equipo> buscarCuadrillasEnAplicacionInterna(String sql, String busquedaEspecifica) throws SQLException{
        ArrayList<Equipo> listadoObjeto = new ArrayList();
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);  
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT  [eq_cdgo]						--1 Equipo_codigo\n" +
                                                                    "      ,[eq_tipo_equipo_cdgo]					--2 Tipo_Equipo_Codigo\n" +
                                                                    "		  ,[te_cdgo]							--3 Tipo_Equipo_Codigo\n" +
                                                                    "		  ,[te_desc]							--4 Tipo_Equipo_Descripcion\n" +
                                                                    "		  ,[te_estad]							--5 Tipo_Equipo_Estado\n" +
                                                                    "      ,[eq_codigo_barra]						--6 Equipo_CodigoBarra\n" +
                                                                    "      ,[eq_referencia]							--7 Equipo_Referencia\n" +
                                                                    "      ,[eq_producto]							--8 Equipo_Producto\n" +
                                                                    "      ,[eq_capacidad]							--9 Equipo_Capacidad\n" +
                                                                    "      ,[eq_marca]								--10 Equipo_Marca\n" +
                                                                    "      ,[eq_modelo]								--11 Equipo_Modelo\n" +
                                                                    "      ,[eq_serial]								--12 Equipo_Serial\n" +
                                                                    "      ,[eq_desc]								--13 Equipo_Descripcion\n" +
                                                                    "      ,[eq_clasificador1_cdgo]					--14 Equipo_Clasificador1_Código\n" +
                                                                    "		  ,clasificador1.[ce_cdgo]				--15 Equipo_Clasificador1_Código\n" +
                                                                    "		  ,clasificador1.[ce_desc]				--16 Equipo_Clasificador1_Descripción\n" +
                                                                    "		  ,clasificador1.[ce_estad]				--17 Equipo_Clasificador1_Estado\n" +
                                                                    "      ,[eq_clasificador2_cdgo]					--18 Equipo_Clasificador2_Código\n" +
                                                                    "		  ,clasificador2.[ce_cdgo]				--19 Equipo_Clasificador2_Código\n" +
                                                                    "		  ,clasificador2.[ce_desc]				--20 Equipo_Clasificador2_Descripción\n" +
                                                                    "		  ,clasificador2.[ce_estad]				--21 Equipo_Clasificador2_Estado\n" +
                                                                    "      ,[eq_proveedor_equipo_cdgo]							--22 Equipo_valorHora\n" +
                                                                    "      ,[eq_proveedor_equipo_cdgo]				--23 Equipo_ProveedorEquipo_Código\n" +
                                                                    "		  ,[pe_cdgo]							--24 Equipo_ProveedorEquipo_Código\n" +
                                                                    "		  ,[pe_nit]								--25 Equipo_ProveedorEquipo_NIT\n" +
                                                                    "		  ,[pe_desc]							--26 Equipo_ProveedorEquipo_Descripción\n" +
                                                                    "		  ,[pe_estad]							--27 Equipo_ProveedorEquipo_Estado\n" +
                                                                    "      ,[eq_equipo_pertenencia_cdgo]				--28 Equipo_Pertenencia_Código\n" +
                                                                    "		  ,[ep_cdgo]							--29 Equipo_Pertenencia_Código\n" +
                                                                    "		  ,[ep_desc]							--30 Equipo_Pertenencia_Descripción\n" +
                                                                    "		  ,[ep_estad]							--31 Equipo_Pertenencia_Estado\n" +
                                                                    "      ,[eq_observ]								--32 Equipo_Observación\n" +
                                                                    "      ,[eq_estad]								--33 Equipo_Estado\n" +
                                                                    "      ,[eq_actvo_fijo_id]							--34 Equipo_ActivoFijo_cdgo\n" +
                                                                    "      ,[eq_actvo_fijo_referencia]						--35 Equipo_ActivoFijo_referencia\n" +
                                                                    "      ,[eq_actvo_fijo_desc]						--36 Equipo_ActivoFijo_descripcion\n" +
                                                                    "  FROM ["+DB+"].[dbo].[equipo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador1 ON clasificador1.ce_cdgo =[eq_clasificador1_cdgo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador2 ON clasificador2.ce_cdgo =[eq_clasificador2_cdgo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [pe_cdgo] =[eq_proveedor_equipo_cdgo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON [eq_equipo_pertenencia_cdgo]=[ep_cdgo] "+sql);
            if(!busquedaEspecifica.equals("")){
                 queryBuscar.setString(1, busquedaEspecifica);
            }
            resultSetBuscar= queryBuscar.executeQuery();
          
            while(resultSetBuscar.next()){ 
                Equipo Objeto = new Equipo(); 
                Objeto.setCodigo(resultSetBuscar.getString(1));
                Objeto.setTipoEquipo(new TipoEquipo(resultSetBuscar.getString(3),resultSetBuscar.getString(4),resultSetBuscar.getString(5)));
                Objeto.setCodigo_barra(resultSetBuscar.getString(6));
                Objeto.setReferencia(resultSetBuscar.getString(7));
                Objeto.setProducto(resultSetBuscar.getString(8));
                Objeto.setCapacidad(resultSetBuscar.getString(9));
                Objeto.setMarca(resultSetBuscar.getString(10));
                Objeto.setModelo(resultSetBuscar.getString(11));
                Objeto.setSerial(resultSetBuscar.getString(12));
                Objeto.setDescripcion(resultSetBuscar.getString(13));
                Objeto.setClasificador1(new ClasificadorEquipo(resultSetBuscar.getString(15),resultSetBuscar.getString(16),resultSetBuscar.getString(17)));
                Objeto.setClasificador2(new ClasificadorEquipo(resultSetBuscar.getString(19),resultSetBuscar.getString(20),resultSetBuscar.getString(21)));
                Objeto.setProveedorEquipo(new ProveedorEquipo(resultSetBuscar.getString(24),resultSetBuscar.getString(25),resultSetBuscar.getString(26),resultSetBuscar.getString(27)));
                Objeto.setPertenenciaEquipo(new Pertenencia(resultSetBuscar.getString(29),resultSetBuscar.getString(30),resultSetBuscar.getString(31)));
                Objeto.setObservacion(resultSetBuscar.getString(32));
                Objeto.setEstado(resultSetBuscar.getString(33));
                Objeto.setActivoFijo_codigo(resultSetBuscar.getString(34));
                Objeto.setActivoFijo_referencia(resultSetBuscar.getString(35));
                Objeto.setActivoFijo_descripcion(resultSetBuscar.getString(36));
                listadoObjeto.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjeto;
    } 
    
    //Solicitud Equipos
    public ArrayList<String> cargarMarcasEquiposEnAplicacionInterna(String tipo_Equipo) throws SQLException{
        ArrayList<String> listadoObjeto = null;
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);  
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT [eq_marca]\n" +
                                                        "			FROM ["+DB+"].[dbo].[equipo]\n" +
                                                        "				INNER JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo]\n" +
                                                        "	WHERE [eq_estad]=1  AND [te_desc] LIKE ?\n" +
                                                        "	GROUP BY [eq_marca]");
            queryBuscar.setString(1, tipo_Equipo);
            resultSetBuscar= queryBuscar.executeQuery();
            boolean validator=true;
            while(resultSetBuscar.next()){
                if(validator){
                    listadoObjeto = new ArrayList();
                    validator= false;
                }
                listadoObjeto.add(resultSetBuscar.getString(1));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las marcas de equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjeto;
    }
    public ArrayList<String> cargarModelosEquiposEnAplicacionInterna(String tipo_Equipo, String marca_Equipo) throws SQLException{
        ArrayList<String> listadoObjeto = new ArrayList();
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);  
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT [eq_modelo]\n" +
                                                "			FROM ["+DB+"].[dbo].[equipo]\n" +
                                                "				INNER JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo]	\n" +
                                                "	WHERE [eq_estad]=1  AND [te_desc] LIKE ?  AND [eq_marca] LIKE ? \n" +
                                                "	GROUP BY [eq_modelo]");
            queryBuscar.setString(1, tipo_Equipo);
            queryBuscar.setString(2, marca_Equipo);
            resultSetBuscar= queryBuscar.executeQuery();
          
            while(resultSetBuscar.next()){ 
                listadoObjeto.add(resultSetBuscar.getString(1));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los Modelos de equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjeto;
    }
    public int contarCantidadesEquiposEnAplicacionInterna(String tipo_Equipo, String marca_Equipo, String Modelo_Equipo) throws SQLException{
        int result=0;
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);  
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT COUNT([eq_modelo])\n" +
                                                "			FROM ["+DB+"].[dbo].[equipo]\n" +
                                                "				INNER JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo]	\n" +
                                                "	WHERE [eq_estad]=1  AND [te_desc] LIKE ?  AND [eq_marca] LIKE ? AND [eq_modelo]=? \n" +
                                                "	");
            queryBuscar.setString(1, tipo_Equipo);
            queryBuscar.setString(2, marca_Equipo);
            queryBuscar.setString(3, Modelo_Equipo);
            resultSetBuscar= queryBuscar.executeQuery();
          
            while(resultSetBuscar.next()){ 
                result=resultSetBuscar.getInt(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los Modelos de equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return result;
    }
    
    public String comparadorFechaActual(String ano,String mes, String dia, String hora, String minuto){
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        conexion= control.ConectarBaseDatos();
        String retorno = "";
        String fechaOrigen="'"+ano+"-"+mes+"-"+dia+" "+hora+":"+minuto+":00.0"+"'";
        try{
            //PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[articulo] WHERE [ar_cdgo] like ?;");
            PreparedStatement query= conexion.prepareStatement(" Declare @fechaActual datetime2= SYSDATETIME() ;\n" +
                                                    "            SELECT DATEDIFF(minute, "+fechaOrigen+", @fechaActual) as DiferenciaFecha;");
            //query.setString(1, fechaOrigen);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){
                retorno= resultSet.getString(1);
            }
        }catch (SQLException sqlException){
            System.out.println("Error al tratar de comparar las fechas");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return retorno;
        
        /*Declare @fechaEntrada datetime2 = '2020-02-18 14:50:00.0';   
            Declare @fechaActual datetime2= SYSDATETIME() ;
            SELECT DATEDIFF(minute, @fechaEntrada, @fechaActual) as DiferenciaFecha;  
        */
    
    }
    public String comparadorEntreDosFechas(String anoI,String mesI, String diaI, String horaI, String minutoI, String anoF,String mesF, String diaF, String horaF, String minutoF){
        /**
         Metodo Retorna un Número Negativo si fechaInicio es Mayor que fechaFinal
        **/
        String fechaInicio="'"+anoI+"-"+mesI+"-"+diaI+" "+horaI+":"+minutoI+":00.0"+"'";
        String fechaFinal="'"+anoF+"-"+mesF+"-"+diaF+" "+horaF+":"+minutoF+":00.0"+"'";
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        conexion= control.ConectarBaseDatos();
        String retorno = "";
        try{

            PreparedStatement query= conexion.prepareStatement(" SELECT DATEDIFF(minute, "+fechaInicio+", "+fechaFinal+") as DiferenciaFecha;");
            //query.setString(1, fechaOrigen);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){
                retorno= resultSet.getString(1);
            }
        }catch (SQLException sqlException){
            System.out.println("Error al tratar de comparar las fechas");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        System.out.println(""+retorno);
        return retorno;
        
        /*Declare @fechaEntrada datetime2 = '2020-02-18 14:50:00.0';   
            Declare @fechaActual datetime2= SYSDATETIME() ;
            SELECT DATEDIFF(minute, @fechaEntrada, @fechaActual) as DiferenciaFecha;  
        */
    
    }
    public String comparadorEntreDosFechas(String fechaInicio, String fechaFinal){
        /**
         Metodo Retorna un Número Negativo si fechaInicio es Mayor que fechaFinal
        **/
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String retorno = "";
        try{
            System.out.println(" SELECT DATEDIFF(minute, "+fechaInicio+", "+fechaFinal+") as DiferenciaFecha;");
            PreparedStatement query= conexion.prepareStatement(" SELECT DATEDIFF(minute, "+fechaInicio+", "+fechaFinal+") as DiferenciaFecha;");
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){
                retorno= resultSet.getString(1);
            }
        }catch (SQLException sqlException){
            System.out.println("Error al tratar de comparar las fechas");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return retorno; 
    }
    public int DiferenciasMinutosEntreDosFechas(String anoI,String mesI, String diaI, String horaI, String minutoI, String anoF,String mesF, String diaF, String horaF, String minutoF){
        /**
         Metodo Retorna un Número Negativo si fechaInicio es Mayor que fechaFinal
        **/
        String fechaInicio="'"+anoI+"-"+mesI+"-"+diaI+" "+horaI+":"+minutoI+":00.0"+"'";
        String fechaFinal="'"+anoF+"-"+mesF+"-"+diaF+" "+horaF+":"+minutoF+":00.0"+"'";
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        conexion= control.ConectarBaseDatos();
        int retorno = 0;
        try{

            //PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[articulo] WHERE [ar_cdgo] like ?;");
            PreparedStatement query= conexion.prepareStatement("SELECT (DATEDIFF (MINUTE,  "+fechaInicio+", "+fechaFinal+"));");
            //query.setString(1, fechaOrigen);
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){
                retorno= resultSet.getInt(1);
                //retorno = retorno /60;
            }
        }catch (SQLException sqlException){
            System.out.println("Error al tratar de comparar las fechas");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        System.out.println(""+retorno);
        return retorno;
    }
    public String retornarFechaDelSistema(){
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);
        String DB=control.getBaseDeDatos();
        conexion= control.ConectarBaseDatos();
        String fechaRetorno="";
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT CONVERT(VARCHAR(10), GETDATE(), 103);");
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){
                fechaRetorno= resultSet.getString(1);
            }
        }catch (SQLException sqlException){
            System.out.println("Error al retornar la Fecha");
            sqlException.printStackTrace();
        }
        control.cerrarConexionBaseDatos();
        return fechaRetorno;
    } 
    //Asignacion Equipos
    public ArrayList<Equipo> Asignacion_buscarEquiposEnAplicacionInterna(String tipo_Equipo, String marca_Equipo, String Modelo_Equipo) throws SQLException{
        ArrayList<Equipo> listadoObjeto = new ArrayList();
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);  
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT  [eq_cdgo]						--1 Equipo_codigo\n" +
                                                                    "      ,[eq_tipo_equipo_cdgo]					--2 Tipo_Equipo_Codigo\n" +
                                                                    "		  ,[te_cdgo]							--3 Tipo_Equipo_Codigo\n" +
                                                                    "		  ,[te_desc]							--4 Tipo_Equipo_Descripcion\n" +
                                                                    "		  ,[te_estad]							--5 Tipo_Equipo_Estado\n" +
                                                                    "      ,[eq_codigo_barra]						--6 Equipo_CodigoBarra\n" +
                                                                    "      ,[eq_referencia]							--7 Equipo_Referencia\n" +
                                                                    "      ,[eq_producto]							--8 Equipo_Producto\n" +
                                                                    "      ,[eq_capacidad]							--9 Equipo_Capacidad\n" +
                                                                    "      ,[eq_marca]								--10 Equipo_Marca\n" +
                                                                    "      ,[eq_modelo]								--11 Equipo_Modelo\n" +
                                                                    "      ,[eq_serial]								--12 Equipo_Serial\n" +
                                                                    "      ,[eq_desc]								--13 Equipo_Descripcion\n" +
                                                                    "      ,[eq_clasificador1_cdgo]					--14 Equipo_Clasificador1_Código\n" +
                                                                    "		  ,clasificador1.[ce_cdgo]				--15 Equipo_Clasificador1_Código\n" +
                                                                    "		  ,clasificador1.[ce_desc]				--16 Equipo_Clasificador1_Descripción\n" +
                                                                    "		  ,clasificador1.[ce_estad]				--17 Equipo_Clasificador1_Estado\n" +
                                                                    "      ,[eq_clasificador2_cdgo]					--18 Equipo_Clasificador2_Código\n" +
                                                                    "		  ,clasificador2.[ce_cdgo]				--19 Equipo_Clasificador2_Código\n" +
                                                                    "		  ,clasificador2.[ce_desc]				--20 Equipo_Clasificador2_Descripción\n" +
                                                                    "		  ,clasificador2.[ce_estad]				--21 Equipo_Clasificador2_Estado\n" +
                                                                    "      ,[eq_proveedor_equipo_cdgo]							--22 Equipo_valorHora\n" +
                                                                    "      ,[eq_proveedor_equipo_cdgo]				--23 Equipo_ProveedorEquipo_Código\n" +
                                                                    "		  ,[pe_cdgo]							--24 Equipo_ProveedorEquipo_Código\n" +
                                                                    "		  ,[pe_nit]								--25 Equipo_ProveedorEquipo_NIT\n" +
                                                                    "		  ,[pe_desc]							--26 Equipo_ProveedorEquipo_Descripción\n" +
                                                                    "		  ,[pe_estad]							--27 Equipo_ProveedorEquipo_Estado\n" +
                                                                    "      ,[eq_equipo_pertenencia_cdgo]				--28 Equipo_Pertenencia_Código\n" +
                                                                    "		  ,[ep_cdgo]							--29 Equipo_Pertenencia_Código\n" +
                                                                    "		  ,[ep_desc]							--30 Equipo_Pertenencia_Descripción\n" +
                                                                    "		  ,[ep_estad]							--31 Equipo_Pertenencia_Estado\n" +
                                                                    "      ,[eq_observ]								--32 Equipo_Observación\n" +
                                                                    "      ,[eq_estad]								--33 Equipo_Estado\n" +
                                                                    "      ,[eq_actvo_fijo_id]								--34 Equipo_ActivoFijo_cdgo\n" +
                                                                    "      ,[eq_actvo_fijo_referencia]								--35 Equipo_ActivoFijo_referencia\n" +
                                                                    "      ,[eq_actvo_fijo_desc]								--36 Equipo_ActivoFijo_descripcion\n" +
                                                                    "  FROM ["+DB+"].[dbo].[equipo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador1 ON clasificador1.ce_cdgo =[eq_clasificador1_cdgo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[clasificador_equipo] clasificador2 ON clasificador2.ce_cdgo =[eq_clasificador2_cdgo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[proveedor_equipo] ON [pe_cdgo] =[eq_proveedor_equipo_cdgo]\n" +
                                                                    "		INNER JOIN ["+DB+"].[dbo].[equipo_pertenencia] ON [eq_equipo_pertenencia_cdgo]=[ep_cdgo] WHERE [eq_estad]=1 AND [te_desc] LIKE ? AND [eq_marca] LIKE ? AND [eq_modelo] LIKE ?");
            
            queryBuscar.setString(1, tipo_Equipo);
            queryBuscar.setString(2, marca_Equipo);
            queryBuscar.setString(3, Modelo_Equipo);
            resultSetBuscar= queryBuscar.executeQuery();
          
            while(resultSetBuscar.next()){ 
                Equipo Objeto = new Equipo(); 
                Objeto.setCodigo(resultSetBuscar.getString(1));
                Objeto.setTipoEquipo(new TipoEquipo(resultSetBuscar.getString(3),resultSetBuscar.getString(4),resultSetBuscar.getString(5)));
                Objeto.setCodigo_barra(resultSetBuscar.getString(6));
                Objeto.setReferencia(resultSetBuscar.getString(7));
                Objeto.setProducto(resultSetBuscar.getString(8));
                Objeto.setCapacidad(resultSetBuscar.getString(9));
                Objeto.setMarca(resultSetBuscar.getString(10));
                Objeto.setModelo(resultSetBuscar.getString(11));
                Objeto.setSerial(resultSetBuscar.getString(12));
                Objeto.setDescripcion(resultSetBuscar.getString(13));
                Objeto.setClasificador1(new ClasificadorEquipo(resultSetBuscar.getString(15),resultSetBuscar.getString(16),resultSetBuscar.getString(17)));
                Objeto.setClasificador2(new ClasificadorEquipo(resultSetBuscar.getString(19),resultSetBuscar.getString(20),resultSetBuscar.getString(21)));
                Objeto.setProveedorEquipo(new ProveedorEquipo(resultSetBuscar.getString(24),resultSetBuscar.getString(25),resultSetBuscar.getString(26),resultSetBuscar.getString(27)));
                Objeto.setPertenenciaEquipo(new Pertenencia(resultSetBuscar.getString(29),resultSetBuscar.getString(30),resultSetBuscar.getString(31)));
                Objeto.setObservacion(resultSetBuscar.getString(32));
                Objeto.setEstado(resultSetBuscar.getString(33));
                Objeto.setActivoFijo_codigo(resultSetBuscar.getString(34));
                Objeto.setActivoFijo_referencia(resultSetBuscar.getString(35));
                Objeto.setActivoFijo_descripcion(resultSetBuscar.getString(36));
                listadoObjeto.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjeto;
    }
    public int registrarTarifaEquipo(ArrayList<Equipo> listadoObjeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            for (Equipo Objeto : listadoObjeto){
                PreparedStatement queryRegistrar= conexion.prepareStatement(" INSERT INTO ["+DB+"].[dbo].[tarifa_equipo]\n" +
                                                                                "           ([tae_cdgo]\n" +
                                                                                "           ,[tae_ano]\n" +
                                                                                "           ,[tae_equipo_cdgo]\n" +
                                                                                "           ,[tae_valorhoraOperacion]\n" +
                                                                                "           ,[tae_valorhoraAlquiler])\n" +
                                                                                "     VALUES\n" +
                                                                                "           ((SELECT (CASE WHEN (MAX([tae_cdgo]) IS NULL) THEN 1 ELSE (MAX([tae_cdgo])+1) END)AS [tae_cdgo] FROM ["+DB+"].[dbo].[tarifa_equipo]),?,?,?,?);\n");
                queryRegistrar.setString(1, Objeto.getTarifaEquipoNueva().getAño());
                queryRegistrar.setString(2, Objeto.getTarifaEquipoNueva().getCodigoEquipo());
                queryRegistrar.setString(3, Objeto.getTarifaEquipoNueva().getValorHoraOperacion());
                queryRegistrar.setString(4, Objeto.getTarifaEquipoNueva().getValorHoraAlquiler());
                queryRegistrar.execute();
                result=1;
                if(result==1){
                    result=0;
                    //Sacamos el ultimo valor 
                    PreparedStatement queryMaximo= conexion.prepareStatement("SELECT MAX(tae_cdgo) FROM ["+DB+"].[dbo].[tarifa_equipo];");
                    ResultSet resultSetMaximo= queryMaximo.executeQuery();
                    while(resultSetMaximo.next()){ 
                        if(resultSetMaximo.getString(1) != null){
                            Objeto.getTarifaEquipoNueva().setCodigo(resultSetMaximo.getString(1));
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
                        "           ,'TARIFAEQUIPO'" +
                        "           ,CONCAT (?,?,?));");
                    Query_AuditoriaInsert.setString(1, us.getCodigo());
                    Query_AuditoriaInsert.setString(2, namePc);
                    Query_AuditoriaInsert.setString(3, ipPc);
                    Query_AuditoriaInsert.setString(4, macPC);
                    Query_AuditoriaInsert.setString(5, Objeto.getTarifaEquipoNueva().getCodigo()); 
                    Query_AuditoriaInsert.setString(6, "Se registró una nueva tarifa de equipo en el sistema, con Código de tarifa: ");
                    Query_AuditoriaInsert.setString(7, Objeto.getTarifaEquipoNueva().getCodigo());
                    Query_AuditoriaInsert.setString(8,  " Equipo Código: "+Objeto.getTipoEquipo().getDescripcion()+
                                                        " Referencia: "+Objeto.getReferencia()+
                                                        " Código_Barra: "+Objeto.getCodigo_barra()+
                                                        " Producto: "+Objeto.getProducto()+
                                                        " Capacidad: "+Objeto.getCapacidad()+ 
                                                        " Marca: "+Objeto.getMarca()+
                                                        " Modelo: "+Objeto.getModelo()+
                                                        " Serial: "+Objeto.getSerial()+
                                                        " Descripcion: "+Objeto.getDescripcion()+
                                                        /*" Clasificador1: "+Objeto.getClasificador1().getDescripcion()+
                                                        " Clasificador2: "+Objeto.getClasificador2().getDescripcion()+
                                                        " ProveedorEquipo: "+Objeto.getProveedorEquipo().getDescripcion()+
                                                        " PertenenciaEquipo: "+Objeto.getPertenenciaEquipo().getDescripcion()+
                                                        " Observación: "+Objeto.getObservacion()+
                                                        " ActivoFijo_código: "+Objeto.getActivoFijo_codigo()+
                                                        " ActivoFijo_referencia: "+Objeto.getActivoFijo_referencia()+
                                                        " ActivoFijo_descripción: "+Objeto.getActivoFijo_descripcion()+*/
                                                        " Tarifa Año:"+Objeto.getTarifaEquipoNueva().getAño()+
                                                        " Tarifa ValorHoraOperacion:"+Objeto.getTarifaEquipoNueva().getValorHoraOperacion()+
                                                        " Tarifa ValorHoraAlquiler:"+Objeto.getTarifaEquipoNueva().getValorHoraAlquiler());
                    Query_AuditoriaInsert.execute();
                    result=1; 
                }
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
    public int registrarTarifaParaUnEquipo(Equipo equipo, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
           //System.out.println("valor lavado vehículo fue: "+equipo.getTarifaEquipoNueva().getValorLavadoVehiculo());
                PreparedStatement queryRegistrar= conexion.prepareStatement(" INSERT INTO ["+DB+"].[dbo].[tarifa_equipo]\n" +
                                                                                "           ([tae_cdgo]\n" +
                                                                                "           ,[tae_ano]\n" +
                                                                                "           ,[tae_equipo_cdgo]\n" +
                                                                                "           ,[tae_valorhoraOperacion]\n" +
                                                                                "           ,[tae_valorhoraAlquiler]\n" +
                                                                                "           ,[tae_fecha_hora_inicio]\n" +
                                                                                "           ,[tae_fecha_hora_fin],[tae_costoLavadoVehiculo]\n" +
                                                                                "      ,[tae_valorRecaudoEmpresa]\n" +
                                                                                "      ,[tae_valorRecaudoEquipo])\n" +
                                                                                "     VALUES\n" +
                                                                                "           ((SELECT (CASE WHEN (MAX([tae_cdgo]) IS NULL) THEN 1 ELSE (MAX([tae_cdgo])+1) END)AS [tae_cdgo] FROM ["+DB+"].[dbo].[tarifa_equipo]), (SELECT YEAR (?)),?,?,?,?,?,?,?,?);\n");
                
                
                queryRegistrar.setString(1, equipo.getTarifaEquipoNueva().getFechaHoraInicio());
                queryRegistrar.setString(2, equipo.getTarifaEquipoNueva().getCodigoEquipo());
                queryRegistrar.setString(3, equipo.getTarifaEquipoNueva().getValorHoraOperacion());
                queryRegistrar.setString(4, equipo.getTarifaEquipoNueva().getValorHoraAlquiler());
                queryRegistrar.setString(5, equipo.getTarifaEquipoNueva().getFechaHoraInicio());
                queryRegistrar.setString(6, equipo.getTarifaEquipoNueva().getFechaHoraFin());
                queryRegistrar.setString(7, equipo.getTarifaEquipoNueva().getCostoLavadoVehiculo());
                queryRegistrar.setString(8, equipo.getTarifaEquipoNueva().getValorRecaudoEmpresa());
                queryRegistrar.setString(9, equipo.getTarifaEquipoNueva().getValorRecaudoEquipo());
                queryRegistrar.execute();
                result=1;
                if(result==1){
                    result=0;
                    //Sacamos el ultimo valor 
                    PreparedStatement queryMaximo= conexion.prepareStatement("SELECT MAX(tae_cdgo) FROM ["+DB+"].[dbo].[tarifa_equipo];");
                    ResultSet resultSetMaximo= queryMaximo.executeQuery();
                    while(resultSetMaximo.next()){ 
                        if(resultSetMaximo.getString(1) != null){
                            equipo.getTarifaEquipoNueva().setCodigo(resultSetMaximo.getString(1));
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
                        "           ,'TARIFAEQUIPO'" +
                        "           ,CONCAT (?,?,?));");
                    Query_AuditoriaInsert.setString(1, us.getCodigo());
                    Query_AuditoriaInsert.setString(2, namePc);
                    Query_AuditoriaInsert.setString(3, ipPc);
                    Query_AuditoriaInsert.setString(4, macPC);
                    Query_AuditoriaInsert.setString(5, equipo.getTarifaEquipoNueva().getCodigo()); 
                    Query_AuditoriaInsert.setString(6, "Se registró una nueva tarifa de equipo en el sistema, con Código de tarifa: ");
                    Query_AuditoriaInsert.setString(7, equipo.getTarifaEquipoNueva().getCodigo());
                    Query_AuditoriaInsert.setString(8,  " Equipo Código: "+equipo.getTipoEquipo().getDescripcion()+
                                                        " Referencia: "+equipo.getReferencia()+
                                                        " Código_Barra: "+equipo.getCodigo_barra()+
                                                        " Producto: "+equipo.getProducto()+
                                                        " Capacidad: "+equipo.getCapacidad()+ 
                                                        " Marca: "+equipo.getMarca()+
                                                        " Modelo: "+equipo.getModelo()+
                                                        " Serial: "+equipo.getSerial()+
                                                        " Descripcion: "+equipo.getDescripcion()+
                                                        " Año:"+equipo.getTarifaEquipoNueva().getAño()+
                                                        " ValorHoraOperacion:"+equipo.getTarifaEquipoNueva().getValorHoraOperacion()+
                                                        " ValorHoraAlquiler:"+equipo.getTarifaEquipoNueva().getValorHoraAlquiler()+
                                                        " CostoLavadoVehículo:"+equipo.getTarifaEquipoNueva().getCostoLavadoVehiculo()+
                                                        " RecaudoEmpresa:"+equipo.getTarifaEquipoNueva().getValorRecaudoEmpresa()+
                                                        " RecaudoEquipo:"+equipo.getTarifaEquipoNueva().getValorRecaudoEquipo());
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
    
    
    public Equipo retornoEquipoTrabajo(Persona persona){
        Equipo equipo = null;
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);  
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        Equipo Objeto =null;
        try{
            ResultSet resultSetBuscar;
            PreparedStatement queryBuscar= conexion.prepareStatement(" SELECT \n" +
                                                                        "[eq_cdgo]\n" +
                                                                        "      ,[eq_modelo]\n" +
                                                                        "      ,[eq_desc]\n" +
                                                                        "  FROM ["+DB+"].[dbo].[persona]\n" +
                                                                        "  INNER JOIN ["+DB+"].[dbo].[equipo] ON [eq_cdgo]=[ps_equipo_cdgo]\n" +
                                                                        "  WHERE [ps_cdgo] LIKE '"+persona.getCodigo()+"' AND [ps_tipo_documento_cdgo]=?");
            //queryBuscar.setString(1, "'"+persona.getCodigo()+"'");
            queryBuscar.setString(1, persona.getTipoDocumento().getCodigo());
            resultSetBuscar= queryBuscar.executeQuery();
            boolean validar=true;
            while(resultSetBuscar.next()){ 
                if(validar){
                    Objeto = new Equipo(); 
                    validar=false;
                } 
                Objeto.setCodigo(resultSetBuscar.getString(1));             
                Objeto.setModelo(resultSetBuscar.getString(2));
                Objeto.setDescripcion(resultSetBuscar.getString(3));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return Objeto;
    }
    public ArrayList<String> retornoPersonalEquiposEquipos(){
        Conexion_DB_costos_vg control= new Conexion_DB_costos_vg(tipoConexion);  
        conexion= control.ConectarBaseDatos();
        ArrayList<String> listado=null;
        String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSetBuscar;
            PreparedStatement queryBuscar= conexion.prepareStatement(" SELECT \n" +
                                                                "	CONCAT([ps_cdgo],'-',[ps_tipo_documento_cdgo],'@@',[eq_cdgo]) as documento\n" +
                                                                " FROM ["+DB+"].[dbo].[persona]\n" +
                                                                " INNER JOIN ["+DB+"].[dbo].[equipo] ON [eq_cdgo]=[ps_equipo_cdgo]");
            resultSetBuscar= queryBuscar.executeQuery();
            boolean validar=true;
            while(resultSetBuscar.next()){ 
                if(validar){
                    listado = new ArrayList<>(); 
                    validar=false;
                } 
                listado.add(resultSetBuscar.getString(1));
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar los equipos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listado;
    }
}
