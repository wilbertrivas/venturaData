package ModuloCarbon.View;
 
import ModuloCarbon.Controller2.ControlDB_MvtoCarbon;
import ModuloCarbon.Model.MvtoCarbon_ListadoEquipos;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
 
public class Worker_MvtonCarbon extends SwingWorker{
    private final JProgressBar progreso;
    private final ArrayList<MvtoCarbon_ListadoEquipos> listadoMvtoCarbon;
    private final Usuario us;
    private String tipoConexion;
    Worker_MvtonCarbon(JProgressBar barra, ArrayList<MvtoCarbon_ListadoEquipos> listadoMvtoCarbon, Usuario user, String tipoConexion) {
        progreso = barra;
        progreso.setStringPainted(true);
        this.tipoConexion= tipoConexion;
        this.listadoMvtoCarbon=listadoMvtoCarbon;
        this.us=user;
    }
    @Override
    public Double doInBackground() throws Exception {
        double value= ((double)100/(double)listadoMvtoCarbon.size());
        double contador=0;
        for(int i=0; i< listadoMvtoCarbon.size();i++){
            contador += value;           
            publish((int)contador);
            listadoMvtoCarbon.set(i, new ControlDB_MvtoCarbon(tipoConexion).ProcesarEnCcargaGP(listadoMvtoCarbon.get(i),us));
            System.out.println(""+contador);
        }
        
        /*for (Cliente listadoMvtoCarbon1 : listadoMvtoCarbon) {
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
                            new ControlDB_Cliente().registrar(listadoCliente1, user);
                        }
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Cliente_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
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
   /* public ArrayList<MvtoCarbon> retornarListadoMvtoCarbon(){
        return listadoMvtoCarbon;
    }*/
   
}
