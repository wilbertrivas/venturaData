package Catalogo.View1;

import Catalogo.Controller.ControlDB_Articulo;
import Catalogo.Model.Articulo;
import Sistema.Model.Usuario;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
 
public class Worker_Articulo extends SwingWorker{
    private final JProgressBar progreso;
    private final ArrayList<Articulo> listadoArticulo;
    private final Usuario user;
    private String tipoConexion;
    Worker_Articulo(JProgressBar barra, ArrayList<Articulo> listadoArticulo, Usuario user, String tipoConexion) {
        progreso = barra;
        this.tipoConexion= tipoConexion;
        progreso.setStringPainted(true);
        this.listadoArticulo=listadoArticulo;
        this.user= user;
    }
    @Override
    public Double doInBackground() throws Exception {
        double value= ((double)100/(double)listadoArticulo.size());
        double contador=0;
        for (Articulo listadoArticulo1 : listadoArticulo) {
            contador += value;           
            publish((int)contador);
            try {
                if(listadoArticulo1.getCodigo().equalsIgnoreCase("")){
                }else{
                    if(listadoArticulo1.getDescripcion().equalsIgnoreCase("")){
                    }else{
                        if(listadoArticulo1.getEstado()== null){  
                        }else{
                            System.out.println(""+contador);
                            new ControlDB_Articulo(tipoConexion).registrar(listadoArticulo1, user);
                        }
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Articulo_RegistroSincronizado.class.getName()).log(Level.SEVERE, null, ex);
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
