package Catalogo.Controller;

import ConnectionDB.Conexion_DB_costos_vg;
import Catalogo.Model.BaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ControlDB_BaseDatos {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private String tipoConexion;
    
    public ControlDB_BaseDatos(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
    }
    
    public ArrayList<BaseDatos> buscar() throws SQLException{
        ArrayList<BaseDatos> listadoObjetos = new ArrayList();
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  [bd_cdgo]\n" +
                                                                    "      ,[bd_name]\n" +
                                                                    "      ,[bd_desc]\n" +
                                                                    "      ,[bd_estad]\n" +
                                                                    "  FROM ["+DB+"].[dbo].[base_datos] WHERE [bd_estad]=1;");
            ResultSet resultSet= query.executeQuery();                 
            while(resultSet.next()){  
                BaseDatos Objeto = new BaseDatos(); 
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setNombre(resultSet.getString(2));
                Objeto.setDescripcion(resultSet.getString(3));  
                Objeto.setEstado(resultSet.getString(4));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las base de datos");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    } 
    
}
