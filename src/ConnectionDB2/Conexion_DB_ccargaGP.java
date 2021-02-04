package ConnectionDB2;
    
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion_DB_ccargaGP {
    private Connection conexion=null;
    // private String login="";
    //private String pass="";
    private String ruta="jdbc:sqlserver://";
    public int result;
    private String servidor="";
    private String puertoConexion="";
    private String baseDeDatos="";
    private String usuario="";
    private String contrasena="";
    
    public Conexion_DB_ccargaGP(String tipoConexion){
       if(tipoConexion.equals("publico")){
            publico();
        }else{
            if(tipoConexion.equals("privado")){
                privado();
            }
        }       
    }
    public void publico(){
        //this.servidor = "190.90.121.101";
        this.servidor = "190.131.213.43";   
        this.puertoConexion = "3341";
        //this.baseDeDatos = "venus";
        this.baseDeDatos = "venus_venturaData";
        this.usuario = "sa";
        this.contrasena = "root";
        this.ruta="jdbc:sqlserver://"+servidor+":"+puertoConexion+";databaseName="+baseDeDatos+";";
    }
    public void privado(){
        this.servidor = "ccarga.goib.com";
        this.puertoConexion = "3341";
        //this.baseDeDatos = "venus";
        this.baseDeDatos = "venus_venturaData";
        this.usuario = "sa";
        this.contrasena = "root";
        this.ruta="jdbc:sqlserver://"+servidor+":"+puertoConexion+";databaseName="+baseDeDatos+";";
    }
    
    public Connection ConectarBaseDatos(){
        try{
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
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
