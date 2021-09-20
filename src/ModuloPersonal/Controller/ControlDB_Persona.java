/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloPersonal.Controller;

import Catalogo.Model.BaseDatos;
import Catalogo.Model.Compañia;
import Catalogo.Model.Equipo;
import Catalogo.Model.TipoEquipo;
import Catalogo.Model.Transportadora;
import ConnectionDB.Conexion_DB_ccargaGP;
import ConnectionDB.Conexion_DB_ccargaOPP;
import ConnectionDB.Conexion_DB_costos_vg;
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

/**
 *
 * @author sistemas
 */
public class ControlDB_Persona {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private String tipoConexion;
    
    public ControlDB_Persona(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    public int registrar(Persona Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
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
                PreparedStatement Query= conexion.prepareStatement("INSERT INTO ["+DB+"].[dbo].[persona] ([ps_cdgo]\n" +
                    "           ,[ps_tipo_documento_cdgo]\n" +
                    "           ,[ps_nombre]\n" +
                    "           ,[ps_apellidos]\n" +
                    "           ,[ps_telefono]\n" +
                    "           ,[ps_cargo_nomina_cdgo]\n" +
                    "           ,[ps_tipo_contrato_cdgo]\n" +
                    "           ,[ps_compania_cdgo]\n" +
                    "           ,[ps_equipo_cdgo]\n" +
                    "           ,[ps_estado]) VALUES (?,?,?,?,?,?,?,?,?,?);");
                Query.setString(1, Objeto.getCodigo());
                Query.setString(2, Objeto.getTipoDocumento().getCodigo());
                Query.setString(3, Objeto.getNombre());
                Query.setString(4, Objeto.getApellido());
                Query.setString(5, Objeto.getTelefono());
                Query.setString(6, Objeto.getCargoNomina().getCodigo());
                Query.setString(7, Objeto.getTipoContrato().getCodigo());
                Query.setString(8, Objeto.getCompania().getCodigo());
                if(Objeto.getEquipo()==null){
                    Query.setString(9, null);
                }else{
                    Query.setString(9, Objeto.getEquipo().getCodigo());
                }
                Query.setString(10, Objeto.getEstado());
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
                        "           ,'PERSONA'" +
                        "           ,CONCAT (?,?,' Nombre: ',?,' Estado: ',?));");
                    Query_Auditoria.setString(1, us.getCodigo());
                    Query_Auditoria.setString(2, namePc);
                    Query_Auditoria.setString(3, ipPc);
                    Query_Auditoria.setString(4, macPC);
                    Query_Auditoria.setString(5, Objeto.getCodigo());
                    Query_Auditoria.setString(6, "Se registró una nueva persona en el sistema, con Código: ");
                    Query_Auditoria.setString(7, Objeto.getCodigo());
                    if(Objeto.getEquipo()==null){
                        Query_Auditoria.setString(8, Objeto.getNombre()+" Apellidos: "+Objeto.getApellido()+" Telefóno:"+Objeto.getTelefono()+
                            " Cargo Nómina:"+Objeto.getCargoNomina().getDescripcion()+" TipoContrato:"+Objeto.getTipoContrato().getDescripcion()+" Compañia: "+Objeto.getCompania().getDescripcion()+" Estado: ");
                    }else{
                        Query_Auditoria.setString(8, Objeto.getNombre()+" Apellidos: "+Objeto.getApellido()+" Telefóno:"+Objeto.getTelefono()+
                            " Cargo Nómina:"+Objeto.getCargoNomina().getDescripcion()+" TipoContrato:"+Objeto.getTipoContrato().getDescripcion()+" Compañia: "+Objeto.getCompania().getDescripcion()+" EquipoTrabajo: "+Objeto.getEquipo().getDescripcion()+Objeto.getEquipo().getModelo()+ " Estado: ");
                    }
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
    public boolean validarExistencia(Persona Objeto){
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        boolean retorno=false;
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[persona] WHERE [ps_cdgo] like ? AND [ps_tipo_documento_cdgo]=?;");
            query.setString(1, Objeto.getCodigo());
            query.setString(2, Objeto.getTipoDocumento().getCodigo());
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
    public ArrayList<Persona> buscar(String valorConsulta) throws SQLException{
        ArrayList<Persona> listadoObjetos = new ArrayList();
       conexion= control.ConectarBaseDatos();
       String DB=control.getBaseDeDatos();
        try{
            ResultSet resultSet; 
            if(valorConsulta.equals("")){
                PreparedStatement query= conexion.prepareStatement("SELECT  [ps_cdgo]--1\n" +
                                                            "      ,[ps_tipo_documento_cdgo]--2\n" +
                                                            "		  ,[tidoc_cdgo]--3\n" +
                                                            "		  ,[tidoc_desc]--4\n" +
                                                            "		  ,[tidoc_estad]--5\n" +
                                                            "      ,[ps_nombre]--6\n" +
                                                            "      ,[ps_apellidos]--7\n" +
                                                            "      ,[ps_telefono]--8\n" +
                                                            "      ,[ps_cargo_nomina_cdgo]--9\n" +
                                                            "		  ,[cn_cdgo]--10\n" +
                                                            "		  ,[cn_desc]--11\n" +
                                                            "		  ,[cn_estad]--12\n" +
                                                            "      ,[ps_tipo_contrato_cdgo]--13\n" +
                                                            "		  ,[tc_cdgo]--14\n" +
                                                            "		  ,[tc_desc]--15\n" +
                                                            "		  ,[tc_estad]--16\n" +
                                                            "      ,[ps_compania_cdgo]--17\n" +
                                                            "		  ,[cp_cdgo]--18\n" +
                                                            "		  ,[cp_desc]--19\n" +
                                                            "		  ,[cp_estad]--20\n" +
                                                            "      ,[ps_equipo_cdgo]--21\n" +
                                                            "			,[eq_cdgo]--22\n" +
                                                            "			,[eq_marca]--23\n" +
                                                            "			,[eq_modelo]--24\n" +
                                                            "			,[eq_desc]--25\n" +
                                                            "      ,CASE WHEN ([ps_estado]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [ps_estado]--26\n"
                                                                    + ",[te_cdgo]--27\n" +
                                                                "      ,[te_desc]--28\n" +
                                                            "  FROM ["+DB+"].[dbo].[persona]\n" +
                                                            "  INNER JOIN ["+DB+"].[dbo].[tipo_documento] ON [ps_tipo_documento_cdgo]=[tidoc_cdgo]\n" +
                                                            "  INNER JOIN ["+DB+"].[dbo].[cargo_nomina] ON [cn_cdgo]=[ps_cargo_nomina_cdgo]\n" +
                                                            "  INNER JOIN ["+DB+"].[dbo].[tipo_contrato] ON [ps_tipo_contrato_cdgo]=[tc_cdgo]\n" +
                                                            "  INNER JOIN ["+DB+"].[dbo].[compania] ON [cp_cdgo]=[ps_compania_cdgo]\n" +
                                                            "  LEFT JOIN ["+DB+"].[dbo].[equipo] ON [eq_cdgo]=[ps_equipo_cdgo]"
                                                            + " LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo];");
                resultSet= query.executeQuery();
            }else{
                PreparedStatement query= conexion.prepareStatement("SELECT  [ps_cdgo]--1\n" +
                                                        "      ,[ps_tipo_documento_cdgo]--2\n" +
                                                        "		  ,[tidoc_cdgo]--3\n" +
                                                        "		  ,[tidoc_desc]--4\n" +
                                                        "		  ,[tidoc_estad]--5\n" +
                                                        "      ,[ps_nombre]--6\n" +
                                                        "      ,[ps_apellidos]--7\n" +
                                                        "      ,[ps_telefono]--8\n" +
                                                        "      ,[ps_cargo_nomina_cdgo]--9\n" +
                                                        "		  ,[cn_cdgo]--10\n" +
                                                        "		  ,[cn_desc]--11\n" +
                                                        "		  ,[cn_estad]--12\n" +
                                                        "      ,[ps_tipo_contrato_cdgo]--13\n" +
                                                        "		  ,[tc_cdgo]--14\n" +
                                                        "		  ,[tc_desc]--15\n" +
                                                        "		  ,[tc_estad]--16\n" +
                                                        "      ,[ps_compania_cdgo]--17\n" +
                                                        "		  ,[cp_cdgo]--18\n" +
                                                        "		  ,[cp_desc]--19\n" +
                                                        "		  ,[cp_estad]--20\n" +
                                                        "      ,[ps_equipo_cdgo]--21\n" +
                                                        "			,[eq_cdgo]--22\n" +
                                                        "			,[eq_marca]--23\n" +
                                                        "			,[eq_modelo]--24\n" +
                                                        "			,[eq_desc]--25\n" +
                                                        "      ,CASE WHEN ([ps_estado]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [ps_estado]--26\n" +
                                                        ",[te_cdgo]--27\n" +
                                                                "      ,[te_desc]--28\n" +
                                                        "  FROM ["+DB+"].[dbo].[persona]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[tipo_documento] ON [ps_tipo_documento_cdgo]=[tidoc_cdgo]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[cargo_nomina] ON [cn_cdgo]=[ps_cargo_nomina_cdgo]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[tipo_contrato] ON [ps_tipo_contrato_cdgo]=[tc_cdgo]\n" +
                                                        "  INNER JOIN ["+DB+"].[dbo].[compania] ON [cp_cdgo]=[ps_compania_cdgo]\n" +
                                                        "  LEFT JOIN ["+DB+"].[dbo].[equipo] ON [eq_cdgo]=[ps_equipo_cdgo] "
                                                    + " LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo];"
                                                                                + " WHERE (ps_cdgo LIKE ? OR ps_nombre LIKE ? OR [ps_apellidos] LIKE ?);");
                query.setString(1, "%"+valorConsulta+"%");
                query.setString(2, "%"+valorConsulta+"%");
                query.setString(3, "%"+valorConsulta+"%");
                resultSet= query.executeQuery();              
            }
            while(resultSet.next()){  
                Persona Objeto = new Persona(); 
                Objeto.setCodigo(resultSet.getString(1));
                    TipoDocumento tipoDocumento= new TipoDocumento(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                Objeto.setTipoDocumento(tipoDocumento);
                Objeto.setNombre(resultSet.getString(6));
                Objeto.setApellido(resultSet.getString(7));
                Objeto.setTelefono(resultSet.getString(8));
                    CargoNomina cargoNomina= new CargoNomina(resultSet.getString(10), resultSet.getString(11), resultSet.getString(12));
                Objeto.setCargoNomina(cargoNomina);
                    TipoContrato tipoContrato= new TipoContrato(resultSet.getString(14), resultSet.getString(15), resultSet.getString(16));
                Objeto.setTipoContrato(tipoContrato);
                    Compañia compania= new Compañia(resultSet.getString(18), resultSet.getString(19), resultSet.getString(20));
                Objeto.setCompania(compania);
                    Equipo equipo = new Equipo();
                    equipo.setCodigo(resultSet.getString(22));
                    equipo.setMarca(resultSet.getString(23));
                    equipo.setModelo(resultSet.getString(24));
                    equipo.setDescripcion(resultSet.getString(25));
                    TipoEquipo tipoEquipo = new TipoEquipo(resultSet.getString(27), resultSet.getString(28), "");
                    equipo.setTipoEquipo(tipoEquipo);
                Objeto.setEquipo(equipo);
                Objeto.setEstado(resultSet.getString(26));       
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las personas");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    public int actualizar(Persona Objeto, Usuario us) throws FileNotFoundException, UnknownHostException, SocketException{
        int result=0;
        try{
            Persona PersonaAnterior=buscarEspecifico(Objeto);
            String estado="";
            if(Objeto.getEstado().equalsIgnoreCase("1")){
                estado="ACTIVO";
            }else{
                estado="INACTIVO";
            }
            conexion= control.ConectarBaseDatos();
            String DB=control.getBaseDeDatos();
            PreparedStatement query= conexion.prepareStatement("UPDATE ["+DB+"].[dbo].[persona] SET [ps_nombre]=? "
                    + ", [ps_apellidos]=?"
                    + ", [ps_telefono]=?"
                    + ",[ps_cargo_nomina_cdgo]=? "
                    + ",[ps_tipo_contrato_cdgo]=? "
                    + ",[ps_compania_cdgo]=? "
                    + ",[ps_equipo_cdgo]=? "
                    + ",[ps_estado]=? "
                    + " WHERE [ps_cdgo] LIKE ? AND [ps_tipo_documento_cdgo]=?;");
            query.setString(1, Objeto.getNombre());
            query.setString(2, Objeto.getApellido());
            query.setString(3, Objeto.getTelefono());
            query.setString(4, Objeto.getCargoNomina().getCodigo());
            query.setString(5, Objeto.getTipoContrato().getCodigo());
            query.setString(6, Objeto.getCompania().getCodigo());
            if(Objeto.getEquipo()==null){
                query.setString(7, null);
            }else{
               query.setString(7, Objeto.getEquipo().getCodigo());
            }
            query.setString(8, Objeto.getEstado());
            query.setString(9, Objeto.getCodigo());
            query.setString(10, Objeto.getTipoDocumento().getCodigo());
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
                        "           ,'PERSONA'" +
                        "           ,CONCAT('Se registró una nueva actualización en el sistema sobre ',?,' Cedula: ',?,' TipoDocumento: ',?,' Nombre: ',?,' Telefono: ',?,' CargoNomina: ',?,' TipoContrato: ',?,' Compañia: ',?,?,' Estado: ',?,"
                                            + "' actualizando la siguiente informacion a  Cedula: ',?,' TipoDocumento: ',?,' Nombre: ',?,' Telefono: ',?,' CargoNomina: ',?,' TipoContrato: ',?,' Compañia: ',?,?,' Estado: ',?));");
                Query_Auditoria.setString(1, us.getCodigo());
                Query_Auditoria.setString(2, namePc);
                Query_Auditoria.setString(3, ipPc);
                Query_Auditoria.setString(4, macPC);
                Query_Auditoria.setString(5, Objeto.getCodigo());
                Query_Auditoria.setString(6, "la Persona :");
                Query_Auditoria.setString(7, PersonaAnterior.getCodigo());
                Query_Auditoria.setString(8, PersonaAnterior.getTipoDocumento().getCodigo());
                Query_Auditoria.setString(9, PersonaAnterior.getNombre());
                Query_Auditoria.setString(10, PersonaAnterior.getApellido());
                Query_Auditoria.setString(11, PersonaAnterior.getCargoNomina().getCodigo());
                Query_Auditoria.setString(12, PersonaAnterior.getTipoContrato().getCodigo());
                Query_Auditoria.setString(13, PersonaAnterior.getCompania().getCodigo());
                if(PersonaAnterior.getEquipo().getCodigo()!= null){
                    Query_Auditoria.setString(14, "' Equipo: '"+PersonaAnterior.getEquipo().getCodigo());
                }else{
                    Query_Auditoria.setString(14, "' Equipo: null'");
                }
                Query_Auditoria.setString(15, PersonaAnterior.getEstado());
                Query_Auditoria.setString(16, Objeto.getCodigo());
                Query_Auditoria.setString(17, Objeto.getTipoDocumento().getCodigo());
                Query_Auditoria.setString(18, Objeto.getNombre());
                Query_Auditoria.setString(19, Objeto.getApellido());
                Query_Auditoria.setString(20, Objeto.getCargoNomina().getCodigo());
                Query_Auditoria.setString(21, Objeto.getTipoContrato().getCodigo());
                Query_Auditoria.setString(22, Objeto.getCompania().getCodigo());
                if(PersonaAnterior.getEquipo().getCodigo()!= null){
                    Query_Auditoria.setString(23, "' Equipo: '"+PersonaAnterior.getEquipo().getCodigo());
                }else{
                    Query_Auditoria.setString(23, "' Equipo: null'");
                }
                Query_Auditoria.setString(24, Objeto.getEstado()); 
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
    public Persona buscarEspecifico(Persona objeto) throws SQLException{
        Persona Objeto =null; 
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  [ps_cdgo]--1\n" +
                                                            "      ,[ps_tipo_documento_cdgo]--2\n" +
                                                            "		  ,[tidoc_cdgo]--3\n" +
                                                            "		  ,[tidoc_desc]--4\n" +
                                                            "		  ,[tidoc_estad]--5\n" +
                                                            "      ,[ps_nombre]--6\n" +
                                                            "      ,[ps_apellidos]--7\n" +
                                                            "      ,[ps_telefono]--8\n" +
                                                            "      ,[ps_cargo_nomina_cdgo]--9\n" +
                                                            "		  ,[cn_cdgo]--10\n" +
                                                            "		  ,[cn_desc]--11\n" +
                                                            "		  ,[cn_estad]--12\n" +
                                                            "      ,[ps_tipo_contrato_cdgo]--13\n" +
                                                            "		  ,[tc_cdgo]--14\n" +
                                                            "		  ,[tc_desc]--15\n" +
                                                            "		  ,[tc_estad]--16\n" +
                                                            "      ,[ps_compania_cdgo]--17\n" +
                                                            "		  ,[cp_cdgo]--18\n" +
                                                            "		  ,[cp_desc]--19\n" +
                                                            "		  ,[cp_estad]--20\n" +
                                                            "      ,[ps_equipo_cdgo]--21\n" +
                                                            "			,[eq_cdgo]--22\n" +
                                                            "			,[eq_marca]--23\n" +
                                                            "			,[eq_modelo]--24\n" +
                                                            "			,[eq_desc]--25\n" +
                                                            "      ,CASE WHEN ([ps_estado]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [ps_estado]--26\n"
                                                                    + ",[te_cdgo]--27\n" +
                                                                "      ,[te_desc]--28\n" +
                                                            "  FROM ["+DB+"].[dbo].[persona]\n" +
                                                            "  INNER JOIN ["+DB+"].[dbo].[tipo_documento] ON [ps_tipo_documento_cdgo]=[tidoc_cdgo]\n" +
                                                            "  INNER JOIN ["+DB+"].[dbo].[cargo_nomina] ON [cn_cdgo]=[ps_cargo_nomina_cdgo]\n" +
                                                            "  INNER JOIN ["+DB+"].[dbo].[tipo_contrato] ON [ps_tipo_contrato_cdgo]=[tc_cdgo]\n" +
                                                            "  INNER JOIN ["+DB+"].[dbo].[compania] ON [cp_cdgo]=[ps_compania_cdgo]\n" +
                                                            "  LEFT JOIN ["+DB+"].[dbo].[equipo] ON [eq_cdgo]=[ps_equipo_cdgo]"
                                                            + " LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo] WHERE ps_cdgo LIKE ? AND  ps_tipo_documento_cdgo LIKE ?;"); 
            query.setString(1, objeto.getCodigo());
            query.setString(2, objeto.getTipoDocumento().getCodigo());
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                Objeto= new Persona();
                Objeto.setCodigo(resultSet.getString(1));
                    TipoDocumento tipoDocumento= new TipoDocumento(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                Objeto.setTipoDocumento(tipoDocumento);
                Objeto.setNombre(resultSet.getString(6));
                Objeto.setApellido(resultSet.getString(7));
                Objeto.setTelefono(resultSet.getString(8));
                    CargoNomina cargoNomina= new CargoNomina(resultSet.getString(10), resultSet.getString(11), resultSet.getString(12));
                Objeto.setCargoNomina(cargoNomina);
                    TipoContrato tipoContrato= new TipoContrato(resultSet.getString(14), resultSet.getString(15), resultSet.getString(16));
                Objeto.setTipoContrato(tipoContrato);
                    Compañia compania= new Compañia(resultSet.getString(18), resultSet.getString(19), resultSet.getString(20));
                Objeto.setCompania(compania);
                    Equipo equipo = new Equipo();
                    equipo.setCodigo(resultSet.getString(22));
                    equipo.setMarca(resultSet.getString(23));
                    equipo.setModelo(resultSet.getString(24));
                    equipo.setDescripcion(resultSet.getString(25));
                    TipoEquipo tipoEquipo = new TipoEquipo(resultSet.getString(27), resultSet.getString(28), "");
                    equipo.setTipoEquipo(tipoEquipo);
                Objeto.setEquipo(equipo);
                Objeto.setEstado(resultSet.getString(26));  
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las transportadora");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return Objeto;
    } 
    public ArrayList<Persona> buscarActivos() throws SQLException{
        ArrayList<Persona> listadoObjetos = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  [ps_cdgo]--1\n" +
                                                            "      ,[ps_tipo_documento_cdgo]--2\n" +
                                                            "		  ,[tidoc_cdgo]--3\n" +
                                                            "		  ,[tidoc_desc]--4\n" +
                                                            "		  ,[tidoc_estad]--5\n" +
                                                            "      ,[ps_nombre]--6\n" +
                                                            "      ,[ps_apellidos]--7\n" +
                                                            "      ,[ps_telefono]--8\n" +
                                                            "      ,[ps_cargo_nomina_cdgo]--9\n" +
                                                            "		  ,[cn_cdgo]--10\n" +
                                                            "		  ,[cn_desc]--11\n" +
                                                            "		  ,[cn_estad]--12\n" +
                                                            "      ,[ps_tipo_contrato_cdgo]--13\n" +
                                                            "		  ,[tc_cdgo]--14\n" +
                                                            "		  ,[tc_desc]--15\n" +
                                                            "		  ,[tc_estad]--16\n" +
                                                            "      ,[ps_compania_cdgo]--17\n" +
                                                            "		  ,[cp_cdgo]--18\n" +
                                                            "		  ,[cp_desc]--19\n" +
                                                            "		  ,[cp_estad]--20\n" +
                                                            "      ,[ps_equipo_cdgo]--21\n" +
                                                            "			,[eq_cdgo]--22\n" +
                                                            "			,[eq_marca]--23\n" +
                                                            "			,[eq_modelo]--24\n" +
                                                            "			,[eq_desc]--25\n" +
                                                            "      ,CASE WHEN ([ps_estado]=1) THEN 'ACTIVO' ELSE 'INACTIVO' END AS [ps_estado]--26\n"
                                                                    + ",[te_cdgo]--27\n" +
                                                                "      ,[te_desc]--28\n" +
                                                            "  FROM ["+DB+"].[dbo].[persona]\n" +
                                                            "  INNER JOIN ["+DB+"].[dbo].[tipo_documento] ON [ps_tipo_documento_cdgo]=[tidoc_cdgo]\n" +
                                                            "  INNER JOIN ["+DB+"].[dbo].[cargo_nomina] ON [cn_cdgo]=[ps_cargo_nomina_cdgo]\n" +
                                                            "  INNER JOIN ["+DB+"].[dbo].[tipo_contrato] ON [ps_tipo_contrato_cdgo]=[tc_cdgo]\n" +
                                                            "  INNER JOIN ["+DB+"].[dbo].[compania] ON [cp_cdgo]=[ps_compania_cdgo]\n" +
                                                            "  LEFT JOIN ["+DB+"].[dbo].[equipo] ON [eq_cdgo]=[ps_equipo_cdgo]"
                                                            + " LEFT JOIN ["+DB+"].[dbo].[tipo_equipo] ON [eq_tipo_equipo_cdgo]=[te_cdgo] WHERE [ps_estado]=1;");
            ResultSet resultSet= query.executeQuery();
            while(resultSet.next()){ 
                Persona Objeto = new Persona(); 
                Objeto.setCodigo(resultSet.getString(1));
                    TipoDocumento tipoDocumento= new TipoDocumento(resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                Objeto.setTipoDocumento(tipoDocumento);
                Objeto.setNombre(resultSet.getString(6));
                Objeto.setApellido(resultSet.getString(7));
                Objeto.setTelefono(resultSet.getString(8));
                    CargoNomina cargoNomina= new CargoNomina(resultSet.getString(10), resultSet.getString(11), resultSet.getString(12));
                Objeto.setCargoNomina(cargoNomina);
                    TipoContrato tipoContrato= new TipoContrato(resultSet.getString(14), resultSet.getString(15), resultSet.getString(16));
                Objeto.setTipoContrato(tipoContrato);
                    Compañia compania= new Compañia(resultSet.getString(18), resultSet.getString(19), resultSet.getString(20));
                Objeto.setCompania(compania);
                    Equipo equipo = new Equipo();
                    equipo.setCodigo(resultSet.getString(22));
                    equipo.setMarca(resultSet.getString(23));
                    equipo.setModelo(resultSet.getString(24));
                    equipo.setDescripcion(resultSet.getString(25));
                    TipoEquipo tipoEquipo = new TipoEquipo(resultSet.getString(27), resultSet.getString(28), "");
                    equipo.setTipoEquipo(tipoEquipo);
                Objeto.setEquipo(equipo);
                Objeto.setEstado(resultSet.getString(26));  
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar de consultar las personas");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
     
    

    
}
