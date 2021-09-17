package Catalogo.View1;
   
import Catalogo.View1.Cliente_RegistroSincronizado;
import Catalogo.Controller.ControlDB_Cliente;
import Catalogo.Model.Cliente;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class Worker_Cliente extends SwingWorker{
    private final JProgressBar progreso;
    private final ArrayList<Cliente> listadoCliente;
    private final Usuario user;
    private String tipoConexion;
    Worker_Cliente(JProgressBar barra, ArrayList<Cliente> listadoCliente, Usuario user, String tipoConexion) {
        progreso = barra;
        progreso.setStringPainted(true);
        this.listadoCliente=listadoCliente;
        this.user= user;
        this.tipoConexion= tipoConexion;
    }
    @Override
    public Double doInBackground() throws Exception {
        double value= ((double)100/(double)listadoCliente.size());
        double contador=0;
        for (Cliente listadoCliente1 : listadoCliente) {
            contador += value;           
            publish((int)contador);
            try {
                if(listadoCliente1.getCodigo().equalsIgnoreCase("")){
                }else{
                    if(listadoCliente1.getDescripcion().equalsIgnoreCase("")){
                    }else{
                        if(listadoCliente1.getEstado()== null){  
                        }else{
                            System.out.println(""+contador);
                            new ControlDB_Cliente(tipoConexion).registrar(listadoCliente1, user);
                        }
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Cliente_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 100.00;
    }

    @Override
    protected void done() {
        progreso.setValue(100);
        //progreso.setString("Sincronización Finalizada");
        progreso.setBackground(Color.GREEN);
    }
    @Override
    protected void process(List chunks) {
        progreso.setValue((int) chunks.get(0));
    }
}
