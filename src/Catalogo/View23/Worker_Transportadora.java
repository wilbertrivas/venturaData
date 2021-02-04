package Catalogo.View23;
 
import Catalogo.Controller.ControlDB_Transportadora;
import Catalogo.Model.Transportadora;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
 
public class Worker_Transportadora extends SwingWorker{
    private final JProgressBar progreso;
    private final ArrayList<Transportadora> listadoTransportadora;
    private final Usuario user;
    private String tipoConexion;
    Worker_Transportadora(JProgressBar barra, ArrayList<Transportadora> listadoTransportadora, Usuario user,String tipoConexion) {
        progreso = barra;
        progreso.setStringPainted(true);
        this.tipoConexion= tipoConexion;
        this.listadoTransportadora=listadoTransportadora;
        this.user= user;
    }
    @Override
    public Double doInBackground() throws Exception {
        double value= ((double)100/(double)listadoTransportadora.size());
        double contador=0;
        for (Transportadora listadoTransportadora1 : listadoTransportadora) {
            contador += value;           
            publish((int)contador);
            try {
                if(listadoTransportadora1.getCodigo().equalsIgnoreCase("")){
                }else{
                    if(listadoTransportadora1.getDescripcion().equalsIgnoreCase("")){
                    }else{
                        if(listadoTransportadora1.getEstado()== null){  
                        }else{
                            System.out.println(""+contador);
                            new ControlDB_Transportadora(tipoConexion).registrar(listadoTransportadora1, user);
                        }
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Transportadora_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
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
