package Sistema.Controller;
  
import ConnectionDB2.Conexion_DB_costos_vg;
import Sistema.Model.Perfil;
import Sistema.Model.Permisos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ControlDB_Permiso {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  

    public ControlDB_Permiso(String tipoConexion) {
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    public Perfil CargarPermisos(String codigoPermiso) throws SQLException{
        Perfil perfil = null;
        ArrayList<Permisos> listadoPermisos= new ArrayList<>();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT \n" +
                                                "	  [prf_cdgo]\n" +
                                                "      ,[prf_desc]\n" +
                                                "      ,[prf_estad]\n" +
                                                "	  ,[pm_cdgo]\n" +
                                                "      ,[pm_desc]	   	\n" +
                                                "  FROM ["+DB+"].[dbo].[perfil]\n" +
                                                "	INNER JOIN ["+DB+"].[dbo].perfil_permiso ON prf_cdgo=prfp_perfil_cdgo\n" +
                                                "	INNER JOIN ["+DB+"].[dbo].permiso ON pm_cdgo=prfp_permiso_cdgo"
                                                + " WHERE  [prf_cdgo]=?;");   
            queryBuscar.setString(1, codigoPermiso);
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            int contador=1;
            while(resultSetBuscar.next()){ 
                if(contador==1){
                    perfil= new Perfil();
                    //Cargamos el perfil        
                    perfil.setCodigo(resultSetBuscar.getString(1));
                    perfil.setDescripcion(resultSetBuscar.getString(2));
                    perfil.setEstado(resultSetBuscar.getString(3));
                }
                //Cargamos los diferentes permisos
                Permisos permisos = new Permisos();
                permisos.setCodigo(resultSetBuscar.getString(4));
                permisos.setDescripcion(resultSetBuscar.getString(5));                
                listadoPermisos.add(permisos);  
            }
            if(perfil !=null){
                perfil.setPermisos(listadoPermisos);
            }  
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los perfiles de los usuarios");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return perfil;
    }     
    public String buscar_nombre(String valor) throws SQLException{
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement queryBuscar= conexion.prepareStatement("SELECT * FROM ["+DB+"].[dbo].[permiso] WHERE [pm_desc] like ?;");
            queryBuscar.setString(1, valor);
            ResultSet resultSetBuscar=queryBuscar.executeQuery();
            while(resultSetBuscar.next()){ 
                return resultSetBuscar.getString(1);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar los permisos del perfil");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return "";
    }
    
   
}
