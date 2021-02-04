   
package ModuloCarbon.Report;

import ConnectionDB2.Conexion_DB_costos_vg;
import java.awt.Frame;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ModuloCarbon_Reportes {
    private String tipoConexion;
    
    public void Reporte_ModuloCarbon_General(String fechaInicio, String fechaFinal,String tipoConexion) { 
        try {
            this.tipoConexion= tipoConexion;
            Conexion_DB_costos_vg control = new Conexion_DB_costos_vg(tipoConexion);
            String rutaInforme="src//ModuloCarbon//Reportes//ModuloCarbon_ReporteGeneral.jasper";
            Map parametros = new HashMap<String, String>();
            parametros.put("fechaIni", fechaInicio);
            parametros.put("fechaFin", fechaFinal);            
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros,control.ConectarBaseDatos());
            JasperViewer ventanaVisor = new JasperViewer(informe, false);
            ventanaVisor.setTitle("INFORME DE DESCARGUE DE VEHICULOS");
            ventanaVisor.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ModuloCarbon_Reportes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al Generar Reporte");
        }
    }   
   
}
