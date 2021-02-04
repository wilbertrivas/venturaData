package ConnectionDB2;
     
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion_DB_ERP {
    private Connection conexion=null;
    private String ruta="jdbc:sqlserver://";
    private String servidor="";
    private String puertoConexion="";
    private String baseDeDatos="";
    private String usuario="";
    private String contrasena="";
    
    public Conexion_DB_ERP(String tipoConexion){
        if(tipoConexion.equals("publico")){
            publico();
        }else{
            if(tipoConexion.equals("privado")){
                privado();
            }
        }       
    }
    public void publico(){
        this.servidor = "cobra.goib.com";
        this.puertoConexion = "1433";
        this.baseDeDatos = "UnoEE";
        this.usuario = "sa";
        this.contrasena = "Ventura.2016";
        this.ruta="jdbc:sqlserver://"+servidor+":"+puertoConexion+";databaseName="+baseDeDatos+";";
    }
    public void privado(){
        this.servidor = "cobra.goib.com";
        this.puertoConexion = "1433";
        this.baseDeDatos = "UnoEE";
        this.usuario = "sa";
        this.contrasena = "Ventura.2016";
        this.ruta="jdbc:sqlserver://"+servidor+":"+puertoConexion+";databaseName="+baseDeDatos+";";
    }
    public Connection ConectarBaseDatos(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            conexion=DriverManager.getConnection(ruta+"user="+usuario+";password="+contrasena);
            if(conexion!=null){
               return conexion;      
            }
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se encuetra la Clase para conectar con SQLSERVER");
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,  "Error al querer conectar con SQLSERVER");
        }
        finally{
            return conexion;
        }
    }
    public void cerrarConexionBaseDatos(){
        try{
            conexion.close();
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cerrar la Conexion a la Base de Datos");
        }
    }
    public String getBaseDeDatos() {
        return baseDeDatos;
    }
    
}
