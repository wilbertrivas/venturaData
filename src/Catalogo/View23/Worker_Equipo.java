package Catalogo.View23;
import Catalogo.Controller.ControlDB_Equipo;
import Catalogo.Model.Equipo;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
 
public class Worker_Equipo extends SwingWorker{
    private final JProgressBar progreso;
    private final ArrayList<Equipo> listadoEquipo;
    private final Usuario us;
    private String tipoConexion;
    Worker_Equipo(JProgressBar barra, ArrayList<Equipo> listadoEquipo, Usuario user,String tipoConexion) {
        progreso = barra;
        progreso.setStringPainted(true);
        this.listadoEquipo=listadoEquipo;
        this.us=user;
        this.tipoConexion= tipoConexion;
    }
    @Override
    public Double doInBackground() throws Exception {
        double value= ((double)100/(double)listadoEquipo.size());
        double contador=0;
        for(int i=0; i< listadoEquipo.size();i++){
            contador += value;           
            publish((int)contador);
            new ControlDB_Equipo(tipoConexion).registrar(listadoEquipo.get(i),us);
            System.out.println(""+contador);
        }
        return 100.00;
    }
    @Override
    protected void done() {
        progreso.setValue(100);
        progreso.setBackground(Color.GREEN);
    }
    @Override
    protected void process(List chunks) {
        progreso.setValue((int) chunks.get(0));
    }
}
