package ConnectionDB2;
     
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion_DB_costos_vg {
    private Connection conexion=null;
    private String ruta="jdbc:sqlserver://";
    public int result;
    private String servidor="";
    private String puertoConexion="";
    private String baseDeDatos="";
    private String usuario="";
    private String contrasena="";
    
    public Conexion_DB_costos_vg(String tipoConexion){
        if(tipoConexion.equals("publico")){
            publico();
        }else{
            if(tipoConexion.equals("privado")){
                privado();
            }
        }       
        this.puertoConexion = "3341";
        this.baseDeDatos = "costos_vg_test";
        //this.baseDeDatos = "costos_vg";
        this.usuario = "sa";
        this.contrasena = "root";
        this.ruta="jdbc:sqlserver://"+servidor+":"+puertoConexion+";databaseName="+baseDeDatos+";";
    }
    public void publico(){
        this.servidor = "190.131.213.43";
        //this.servidor = "tigre.goib.com";
    }
    public void privado(){
        this.servidor = "ccarga.goib.com";
        //this.servidor = "tigre.goib.com";
    }
    public Connection ConectarBaseDatos(){
        try{
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            conexion=DriverManager.getConnection(ruta+"user="+usuario+";password="+contrasena);
            if(conexion!=null){
                //validamos la configuración Regional en el servidor
                /*
                    DECLARE @validarConfiguracionRegional SMALLDATETIME='2020-02-05 08:45:00';
                    SELECT @validarConfiguracionRegional;
                    --	Respuesta OK: '2020-02-05 08:45:00'
                    --  Respuesta NO: '2020-05-02 08:45:00'
                */
                
                //String DB=control.getBaseDeDatos();
                try{
                    PreparedStatement query= conexion.prepareStatement("DECLARE @validarConfiguracionRegional SMALLDATETIME='2020-02-05 08:45:00';\n" +
                                                                        "                    SELECT @validarConfiguracionRegional;");
                    ResultSet resultSet= query.executeQuery();
                    while(resultSet.next()){ 
                        //System.out.println("Fue===>"+resultSet.getString(1));
                        if(!resultSet.getString(1).equals("2020-02-05 08:45:00.0")){
                            /*System.out.println("Valide configuración regional en el servidor \nDECLARE @validarConfiguracionRegional SMALLDATETIME='2020-02-05 08:45:00';\n" +
                                                "                    \nSELECT @validarConfiguracionRegional;\n" +
                                                "                    \n--	Respuesta OK: '2020-02-05 08:45:00'\n" +
                                                "                    \n--  Respuesta NO: '2020-05-02 08:45:00'");*/
                            JOptionPane.showMessageDialog(null,
                                    "Valide configuración regional en el servidor \nDECLARE @validarConfiguracionRegional SMALLDATETIME='2020-02-05 08:45:00';\n" +
                                    "SELECT @validarConfiguracionRegional;\n" +
                                    "--	Respuesta OK: '2020-02-05 08:45:00'\n" +
                                    "-- Respuesta NO: '2020-05-02 08:45:00'","Error en Configuración Regional", JOptionPane.ERROR_MESSAGE);
                            conexion=null;
                            
                            
                            
                            
                            /*
                              DECLARE @validarConfiguracionRegional SMALLDATETIME='2020-02-05 08:45:00';
                                SELECT @validarConfiguracionRegional;
                                --	Respuesta OK: '2020-02-05 08:45:00'
                                --  Respuesta NO: '2020-05-02 08:45:00'
                                SELECT CONVERT(varchar(20),@validarConfiguracionRegional,120);
                            
                            **/
                        }
                    }
                }catch (SQLException sqlException) {
                    //JOptionPane.showMessageDialog(null, "Error al tratar de consultar los articulos");
                    sqlException.printStackTrace();
                }  
               return conexion;      
            }
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "No se encuetra la Clase para conectar con SQLSERVER");
        }
        catch(SQLException e){
            e.printStackTrace();
           // JOptionPane.showMessageDialog(null,  "Error al querer conectar con SQLSERVER");
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
