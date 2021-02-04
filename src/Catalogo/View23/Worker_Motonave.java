package Catalogo.View23;
 
import Catalogo.Controller.ControlDB_Motonave;
import Catalogo.Model.Motonave;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
 
public class Worker_Motonave extends SwingWorker{
    private final JProgressBar progreso;
    private final ArrayList<Motonave> listadoMotonave;
    private final Usuario user;
    private String tipoConexion;
    Worker_Motonave(JProgressBar barra, ArrayList<Motonave> listadoMotonave, Usuario user, String tipoConexion) {
        progreso = barra;
        progreso.setStringPainted(true);
        this.tipoConexion= tipoConexion;
        this.listadoMotonave=listadoMotonave;
        this.user= user;
    }
    @Override
    public Double doInBackground() throws Exception {
        double value= ((double)100/(double)listadoMotonave.size());
        double contador=0;
        for (Motonave listadoMotonave1 : listadoMotonave) {
            contador += value;           
            publish((int)contador);
            try {
                if(listadoMotonave1.getCodigo().equalsIgnoreCase("")){
                }else{
                    if(listadoMotonave1.getDescripcion().equalsIgnoreCase("")){
                    }else{
                        if(listadoMotonave1.getEstado()== null){  
                        }else{
                            System.out.println(""+contador);
                            new ControlDB_Motonave(tipoConexion).registrar(listadoMotonave1, user);
                        }
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Motonave_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
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
