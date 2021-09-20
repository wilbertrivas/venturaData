package Auditoria.Controller;

import Auditoria.Model.Auditoria;
import ConnectionDB.Conexion_DB_costos_vg;
import Sistema.Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane; 

public class Controller_Auditoria {
    private Connection conexion=null;
    private  Conexion_DB_costos_vg control=null;  
    private String tipoConexion;

    public Controller_Auditoria(String tipoConexion) {
        this.tipoConexion= tipoConexion;
        control = new Conexion_DB_costos_vg(tipoConexion);
        
    }
    public ArrayList<Auditoria> buscarPorFecha(String DatetimeInicio, String DatetimeFin) throws SQLException{
        ArrayList<Auditoria> listadoObjetos = new ArrayList();
        Conexion_DB_costos_vg control=null;  
        control = new Conexion_DB_costos_vg(tipoConexion);
        conexion= control.ConectarBaseDatos();
        String DB=control.getBaseDeDatos();
        try{
            PreparedStatement query= conexion.prepareStatement("SELECT  [au_cdgo]                                       -- 1\n" +
                                                            "      ,[au_fecha]                                          -- 2\n" +
                                                            "      ,[au_usuario_cdgo_registro]                          -- 3\n" +
                                                            "		   ,[us_cdgo]					-- 4\n" +
                                                            "		  ,[us_nombres]					-- 5\n" +
                                                            "		  ,[us_apellidos]				-- 6\n" +
                                                            "		  ,[us_perfil_cdgo]                             -- 7\n" +
                                                            "		  ,[us_correo]					-- 8\n" +
                                                            "		  , CASE [us_estad] \n" +
                                                            "			WHEN 1 THEN 'ACTIVO'\n" +
                                                            "			WHEN 0 THEN 'INACTIVO'\n" +
                                                            "			ELSE '' END AS [us_estad] 		-- 9\n" +
                                                            "      ,[au_nombre_dispositivo_registro]                    -- 10\n" +
                                                            "      ,[au_ip_dispositivo_registro]			-- 11\n" +
                                                            "      ,[au_mac_dispositivo_registro]			-- 12\n" +
                                                            "      ,[au_cdgo_mtvo]						-- 13\n" +
                                                            "      ,[au_desc_mtvo]					-- 14\n" +
                                                            "      ,[au_detalle_mtvo]                      -- 15\n" +
                                                            "  FROM ["+DB+"].[dbo].[auditoria]\n" +
                                                            "	INNER JOIN ["+DB+"].[dbo].[usuario] ON [au_usuario_cdgo_registro]=[us_cdgo] "+
                                                                "  WHERE [au_fecha] BETWEEN ? AND ? ORDER BY [au_cdgo] DESC");
            query.setString(1, DatetimeInicio);
            query.setString(2, DatetimeFin);
            ResultSet resultSet; resultSet= query.executeQuery();              
            while(resultSet.next()){ 
                //Creamos un Objeto MvtoCarbon para agregar al lsitado
                Auditoria Objeto = new Auditoria(); 
                //Cargamos cada atributo del Objeto
                Objeto.setCodigo(resultSet.getString(1));
                Objeto.setFecha(resultSet.getString(2));
                Usuario ObjetoUsuario = new Usuario();
                    ObjetoUsuario.setCodigo(resultSet.getString(4));
                    ObjetoUsuario.setNombres(resultSet.getString(5));
                    ObjetoUsuario.setApellidos(resultSet.getString(6));
                    ObjetoUsuario.setCorreo(resultSet.getString(8));
                    ObjetoUsuario.setEstado(resultSet.getString(9));
                Objeto.setUsuarioQuienRegistra(ObjetoUsuario);
                Objeto.setNombreDispositivoDondeRegistra(resultSet.getString(10));
                Objeto.setIpDispositivoDondeRegistra(resultSet.getString(11));
                Objeto.setMacDispositivoDondeRegistra(resultSet.getString(12));
                Objeto.setCodigoMovimiento(resultSet.getString(13));
                Objeto.setDescMovimiento(resultSet.getString(14));
                Objeto.setDetalleMovimiento(resultSet.getString(15));
                listadoObjetos.add(Objeto);
            }
        }catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error al tratar al consultar las auditorias");
            sqlException.printStackTrace();
        } 
        control.cerrarConexionBaseDatos();
        return listadoObjetos;
    }
}
