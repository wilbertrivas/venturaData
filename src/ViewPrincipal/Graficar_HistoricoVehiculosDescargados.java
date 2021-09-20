
package ViewPrincipal;

import Sistema.Controller.ControlDB_PanelControl;
import java.awt.Color;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.date.MonthConstants;
import org.jfree.ui.RectangleInsets;

public class Graficar_HistoricoVehiculosDescargados extends javax.swing.JFrame {
    Iniciar_Graficar2 Iniciar12;
    private String typeConnection;
    private int valortime;
    private String modalidadHilo;
    private String script;
    ChartPanel chartPanel;
    
    
    public Graficar_HistoricoVehiculosDescargados(String title) {
        super(title);
        initComponents(); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1093, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 526, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        //JOptionPane.showMessageDialog(null, "Cerrando Frame");
        Iniciar12.stop();
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Graficar_HistoricoVehiculosDescargados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Graficar_HistoricoVehiculosDescargados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Graficar_HistoricoVehiculosDescargados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Graficar_HistoricoVehiculosDescargados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Graficar_HistoricoVehiculosDescargados("").setVisible(true);
            }
        });
    }
    public void inicar(String title, String script, String typeConnection,int valortime, String modalidadHilo ) {
        System.out.println("title: "+title);
        System.out.println("script: "+script);
        System.out.println("typeConnection: "+typeConnection);
        System.out.println("valortime: "+valortime);
        System.out.println("modalidadHilo: "+modalidadHilo);
       // Graficar_Flotante_HistoricoVehiculosDescargados_Backup.Iniciar_Graficar2 Iniciar1;
        chartPanel = (ChartPanel) createDemoPanel(script,typeConnection);
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 500));
        setContentPane(chartPanel);
        
         
        this.typeConnection=typeConnection;
        this.valortime=valortime;
        this.modalidadHilo=modalidadHilo;
        this.script=script;
        
        Iniciar12 = new Iniciar_Graficar2();
        
        //r(String typeConnection, int valortime, String modalidadHilo, String script) {
        //Iniciar12.start();
        
    }
    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "VEHÍCULOS DESCARGADOS POR CLIENTES",  // title
            "CLIENTES",             // x-axis label
            "VEHÍCULOS",   // y-axis label
            dataset,            // data
            true,               // create legend?
            true,               // generate tooltips?
            false               // generate URLs?
        );

        chart.setBackgroundPaint(Color.white);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
            renderer.setDrawSeriesLineAsPath(true);
            
            XYItemLabelGenerator xy= new StandardXYItemLabelGenerator();
            renderer.setBaseItemLabelGenerator( xy );
            renderer.setBaseItemLabelsVisible(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("dd MMM YYYY"));

        return chart;

    }
    private static XYDataset createDataset(String script, String typeConnection) {
        ArrayList<String> listado=null;
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        try {
           listado= new ControlDB_PanelControl(typeConnection).HistoricoVehiculosDescargados(script);
        } catch (SQLException ex) {
            Logger.getLogger(Graficar_HistoricoVehiculosDescargados.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(listado != null){
            String cliente="";
            ArrayList<ArrayList<String>> listadoClientes = null;
            ArrayList<String> historicoCliente= null;
            boolean validator=true;
            for(String data: listado){
                System.out.print(data+"====================================>For\n");
                String[] value= data.split("###");
                if(validator){
                   cliente =value[2];
                   //series1= new TimeSeries(value[2], Day.class);
                   listadoClientes= new ArrayList<>();
                   historicoCliente= new ArrayList<>();
                   validator=false;
                   historicoCliente.add(data);
                }

                if(!cliente.equals(value[2])){ 
                    listadoClientes.add(historicoCliente);
                    historicoCliente= new ArrayList<>();
                    historicoCliente.add(data);
                    cliente =value[2];
                }else{
                    historicoCliente.add(data);  
                }
            }
            listadoClientes.add(historicoCliente);
            int contador= 1;
            for(ArrayList<String> objeto : listadoClientes){
                for(String datax : objeto){
                    System.out.println(datax+"\n");
                }
                System.out.println("Finalizado");
            }

            if(listadoClientes != null){
                TimeSeries series1 = null;
                for(ArrayList<String> objeto : listadoClientes){
                    if(contador==1){
                        boolean inciar= true;
                        for(String data : objeto){
                            String[] value= data.split("###");
                            if(inciar){
                               System.out.println("CLiente======================="+ value[2]);
                                series1 = new TimeSeries(value[2], Day.class);
                                inciar=false;
                            }
                            String[] fecha=value[0].split("-");
                            switch(Integer.parseInt((fecha[1]))){
                                case 1:{//Enero
                                    series1.addOrUpdate(new Day(Integer.parseInt(fecha[2]), MonthConstants.JANUARY, Integer.parseInt(fecha[0])), Integer.parseInt(value[1]));
                                    break;
                                }
                                case 2:{//Febrero
                                    series1.addOrUpdate(new Day(Integer.parseInt(fecha[2]), MonthConstants.FEBRUARY, Integer.parseInt(fecha[0])), Integer.parseInt(value[1]));
                                    break;
                                }
                                case 3:{//Marzo
                                    series1.addOrUpdate(new Day(Integer.parseInt(fecha[2]), MonthConstants.MARCH, Integer.parseInt(fecha[0])), Integer.parseInt(value[1]));
                                    break;
                                }
                                case 4:{//Abril
                                    series1.addOrUpdate(new Day(Integer.parseInt(fecha[2]), MonthConstants.APRIL, Integer.parseInt(fecha[0])), Integer.parseInt(value[1]));
                                    break;
                                }
                                case 5:{//Mayo
                                    series1.addOrUpdate(new Day(Integer.parseInt(fecha[2]), MonthConstants.MAY, Integer.parseInt(fecha[0])), Integer.parseInt(value[1]));
                                    break;
                                }
                                case 6:{//Junio
                                    series1.addOrUpdate(new Day(Integer.parseInt(fecha[2]), MonthConstants.JUNE, Integer.parseInt(fecha[0])), Integer.parseInt(value[1]));
                                    break;
                                }
                                case 7:{//Julio
                                    series1.addOrUpdate(new Day(Integer.parseInt(fecha[2]), MonthConstants.JULY, Integer.parseInt(fecha[0])), Integer.parseInt(value[1]));
                                    break;
                                }
                                case 8:{//Agosto
                                    series1.addOrUpdate(new Day(Integer.parseInt(fecha[2]), MonthConstants.AUGUST, Integer.parseInt(fecha[0])), Integer.parseInt(value[1]));
                                    break;
                                }
                                case 9:{//Septiembre
                                    series1.addOrUpdate(new Day(Integer.parseInt(fecha[2]), MonthConstants.SEPTEMBER, Integer.parseInt(fecha[0])), Integer.parseInt(value[1]));
                                    break;
                                }
                                case 10:{//Octubre
                                    series1.addOrUpdate(new Day(Integer.parseInt(fecha[2]), MonthConstants.OCTOBER, Integer.parseInt(fecha[0])), Integer.parseInt(value[1]));
                                    break;
                                }
                                case 11:{//Noviembre
                                    series1.addOrUpdate(new Day(Integer.parseInt(fecha[2]), MonthConstants.NOVEMBER, Integer.parseInt(fecha[0])), Integer.parseInt(value[1]));
                                    break;
                                }
                                case 12:{//Diciembre
                                    series1.addOrUpdate(new Day(Integer.parseInt(fecha[2]), MonthConstants.DECEMBER, Integer.parseInt(fecha[0])), Integer.parseInt(value[1]));
                                    break;
                                }
                            }
                        }
                        dataset.addSeries(series1);     
                    }
                }  
            }    
        }
        return dataset;
    }
    public static JPanel createDemoPanel(String script, String typeConnection) {
        JFreeChart chart = createChart(createDataset(script,typeConnection));
        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        return panel;
    }
    public void remnover(){
        this.revalidate();
        this.remove(chartPanel);
        //this.repaint();
        chartPanel = (ChartPanel) createDemoPanel(script,typeConnection);
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 500));
        setContentPane(chartPanel);
        this.revalidate();
        
    }
    public  class Iniciar_Graficar2 extends Thread{  
        //public Iniciar_Graficar2(String typeConnection, int valortime, String modalidadHilo, String script) {   
        public Iniciar_Graficar2() {   
        }
	
        @Override
        public void run() {
            while(true){
                try {
                    remnover();
                } catch (Exception e) {
                    e.printStackTrace();
                    //Logger.getLogger(MvtoEquipo_Procesar_Programado.class.getName()).log(Level.SEVERE, null, ex);
                }
                /*if(modalidadHilo.equals("SECOND")){
                    //valortime= valortime * 1000;
                    try {
                        Thread.sleep(valortime * 1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Graficar_HistoricoRecaudoLavadoVehículo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }*/
                if(modalidadHilo.equals("MINUTOS")){
                    //valortime= valortime * 1000* 60;
                    try {
                        Thread.sleep(valortime * 1000* 60);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Graficar_HistoricoRecaudoLavadoVehículo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(modalidadHilo.equals("HORAS")){
                    //valortime= valortime * 1000 * 60 * 60;
                    try {
                        Thread.sleep(valortime * 1000* 60*60);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Graficar_HistoricoRecaudoLavadoVehículo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(modalidadHilo.equals("DÍAS")){
                    //valortime= valortime * 1000 * 60 * 60 * 24;
                    try {
                        Thread.sleep(valortime * 1000* 60*60*24);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Graficar_HistoricoRecaudoLavadoVehículo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
