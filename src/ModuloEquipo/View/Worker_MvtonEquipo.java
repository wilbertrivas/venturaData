package ModuloEquipo.View;
 
import ModuloCarbon.Controller2.ControlDB_MvtoCarbon;
import ModuloCarbon.Model.MvtoCarbon;
import ModuloCarbon.Model.MvtoCarbon_ListadoEquipos;
import ModuloEquipo.Controller2.ControlDB_MvtoEquipo;
import ModuloEquipo.Model.MvtoEquipo;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
 
public class Worker_MvtonEquipo extends SwingWorker{
    private final JProgressBar progreso;
    private final ArrayList<MvtoEquipo> listadoMvtoEquipo;
    private final Usuario us;
    private String tipoConexion;
    Worker_MvtonEquipo(JProgressBar barra, ArrayList<MvtoEquipo> listadoMvtoEquipo, Usuario user,String tipoConexion) {
        progreso = barra;
        progreso.setStringPainted(true);
        this.listadoMvtoEquipo=listadoMvtoEquipo;
        this.us=user;
        this.tipoConexion= tipoConexion;
    }
    @Override
    public Double doInBackground() throws Exception {
        double value= ((double)100/(double)listadoMvtoEquipo.size());
        double contador=0;
        for(int i=0; i< listadoMvtoEquipo.size();i++){
            contador += value;           
            publish((int)contador);
            try{
             listadoMvtoEquipo.set(i, new ControlDB_MvtoEquipo(tipoConexion).Procesar_MvtoEquipo(listadoMvtoEquipo.get(i),us));
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Error con: "+listadoMvtoEquipo.get(i).getCodigo());
            }
            System.out.println("\n\n\n");
            System.out.println(""+contador);
        }
        return 100.00;
    }

    @Override
    protected void done() {
        progreso.setValue(100);
        //progreso.setString("Sincronización Finalizada");
        progreso.setBackground(Color.GREEN);
        progreso.show(false);
    }
    @Override
    protected void process(List chunks) {
        progreso.setValue((int) chunks.get(0));
    }
   
}
